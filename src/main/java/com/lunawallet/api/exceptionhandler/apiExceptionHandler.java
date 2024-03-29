package com.lunawallet.api.exceptionhandler;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.TypeMismatchException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.NoHandlerFoundException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.fasterxml.jackson.databind.exc.InvalidFormatException;
import com.fasterxml.jackson.databind.exc.PropertyBindingException;
import com.lunawallet.domain.exception.EntidadeDuplicadaException;
import com.lunawallet.domain.exception.EntidadeEmUsoException;
import com.lunawallet.domain.exception.EntidadeNaoEncontradaException;
import com.lunawallet.domain.exception.NegocioException;
import com.lunawallet.domain.exception.ProporcaoIncorretaException;

@ControllerAdvice
public class apiExceptionHandler extends ResponseEntityExceptionHandler{

	@Autowired
	private MessageSource messageSource;

	private final static String MSG_SYSTEM_ERRO = "Ocorreu um erro interno inesperado no sistema. "
            + "Tente novamente e se o problema persistir, entre em contato "
            + "com o administrador do sistema.";

	@ExceptionHandler(Exception.class)
	public ResponseEntity<Object> handleUncaught(Exception ex, WebRequest request) {
	    HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR;
	    ProblemType problemType = ProblemType.ERRO_DE_SISTEMA;
	    String detail = MSG_SYSTEM_ERRO;
	    ex.printStackTrace();

	    Problem problem = createProblemBuilder(status, problemType, detail)
	    		.userMessage(detail)
	    		.build();
	    return handleExceptionInternal(ex, problem, new HttpHeaders(), status, request);
	}

	@ExceptionHandler(EntidadeNaoEncontradaException.class)
	public ResponseEntity<?> handleUsuarioNaoEncontrado(EntidadeNaoEncontradaException ex, WebRequest request) {

		HttpStatus status = HttpStatus.NOT_FOUND;
		ProblemType problemType = ProblemType.RECURSO_NAO_ENCONTRADO;
		String body = ex.getMessage();
		Problem problem = this.createProblemBuilder(status, problemType, body)
				.userMessage(body)
				.build();
		return this.handleExceptionInternal(ex, problem, new HttpHeaders(), status, request);
	}

	@ExceptionHandler(NegocioException.class)
	public ResponseEntity<?> handleNegocio(NegocioException ex, WebRequest request) {

		HttpStatus status = HttpStatus.BAD_REQUEST;
		ProblemType problemType = ProblemType.ERRO_NEGOCIO;
		String body = ex.getMessage();
		Problem problem = this.createProblemBuilder(status, problemType, body)
				.userMessage(MSG_SYSTEM_ERRO)
				.build();
		return this.handleExceptionInternal(ex, problem, new HttpHeaders(), status, request);
	}

	@ExceptionHandler(EntidadeEmUsoException.class)
	public ResponseEntity<?> handleEntidadeEmUso(EntidadeEmUsoException ex, WebRequest request) {

		HttpStatus status = HttpStatus.CONFLICT;
		ProblemType problemType = ProblemType.ENTIDADE_EM_USO;
		String body = ex.getMessage();
		Problem problem = this.createProblemBuilder(status, problemType, body)
				.userMessage(body)
				.build();
		return this.handleExceptionInternal(ex, problem, new HttpHeaders(), status, request);
	}

	@Override
	protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex,
			HttpHeaders headers, HttpStatusCode status, WebRequest request) {

		Throwable rootCause = ex.getRootCause();

		if(rootCause instanceof InvalidFormatException) {
			return handleInvalidFormat((InvalidFormatException)rootCause, headers, status, request);
		} else if(rootCause instanceof PropertyBindingException) {
			return handlePropertyBinding((PropertyBindingException) rootCause, headers, status, request);
		}

		ProblemType problemType = ProblemType.MENSAGEM_ILEGIVEL;
		String body = "O Corpo da requisição está inválido. Verifique erro de sintaxe";
		Problem problem = this.createProblemBuilder(status, problemType, body)
				.userMessage(MSG_SYSTEM_ERRO)
				.build();
		return this.handleExceptionInternal(ex, problem, new HttpHeaders(), status, request);
	}


	protected ResponseEntity<Object> handlePropertyBinding(PropertyBindingException ex,
			HttpHeaders headers, HttpStatusCode status, WebRequest request) {

		String path = ex.getPath().stream().map(map -> map.getFieldName()).collect(Collectors.joining("."));
		ProblemType problemType = ProblemType.MENSAGEM_ILEGIVEL;
		String body = String.format("A Propriedade %s não é válida. Altere ou remova e tente novamento.", path);
		Problem problem = this.createProblemBuilder(status, problemType, body)
				.userMessage(MSG_SYSTEM_ERRO)
				.build();
		return this.handleExceptionInternal(ex, problem, new HttpHeaders(), status, request);
	}

	protected ResponseEntity<Object> handleInvalidFormat(InvalidFormatException ex,
			HttpHeaders headers, HttpStatusCode status, WebRequest request) {

		String path = ex.getPath().stream().map(ref -> ref.getFieldName()).collect(Collectors.joining("."));
		ProblemType problemType = ProblemType.MENSAGEM_ILEGIVEL;
		String body = String.format("A propriedade %s recebeu o valor %s, que é um tipo inválido. "
				+ "Corrija informando um valor compatível com o tipo %s.", path, ex.getValue(), ex.getTargetType().getSimpleName());
		Problem problem = this.createProblemBuilder(status, problemType, body)
				.userMessage(MSG_SYSTEM_ERRO)
				.build();
		return this.handleExceptionInternal(ex, problem, new HttpHeaders(), status, request);
	}

	@Override
	protected ResponseEntity<Object> handleTypeMismatch(TypeMismatchException ex, HttpHeaders headers,
			HttpStatusCode status, WebRequest request) {

		if(ex instanceof MethodArgumentTypeMismatchException) {
			return handleMethodArgumentTypeMismatch((MethodArgumentTypeMismatchException) ex, headers, status, request);
		}
		return super.handleTypeMismatch(ex, headers, status, request);
	}

	protected ResponseEntity<Object> handleMethodArgumentTypeMismatch(MethodArgumentTypeMismatchException ex,
			HttpHeaders headers, HttpStatusCode status, WebRequest request) {

		ProblemType problemType = ProblemType.PARAMETRO_INVALIDO;
		String body = String.format("O parâmetro de URL %s recebeu o valor %s, que é de um tipo inválido. "
				+ "Corrija informando um valor compatível com o tipo %s", ex.getName(), ex.getValue(), ex.getRequiredType().getSimpleName());
		Problem problem = this.createProblemBuilder(status, problemType, body)
				.userMessage(MSG_SYSTEM_ERRO)
				.build();
		return this.handleExceptionInternal(ex, problem, new HttpHeaders(), status, request);
	}

	@Override
	protected ResponseEntity<Object> handleNoHandlerFoundException(NoHandlerFoundException ex, HttpHeaders headers,
			HttpStatusCode status, WebRequest request) {

		ProblemType problemType = ProblemType.RECURSO_NAO_ENCONTRADO;
		String body = String.format("O recurso %s, que você tentou acessar, é inexistente.", ex.getRequestURL());
		Problem problem = this.createProblemBuilder(status, problemType, body)
				.userMessage(MSG_SYSTEM_ERRO)
				.build();
		return this.handleExceptionInternal(ex, problem, new HttpHeaders(), status, request);
	}

	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
			HttpHeaders headers, HttpStatusCode status, WebRequest request) {
		ProblemType problemType = ProblemType.DADOS_INCORRETOS;
		String body = "Um ou mais campos estão inválidos. Faça o preenchimento correto e tente novamente.";

		BindingResult bindingResult = ex.getBindingResult();
		List<Problem.Field> problemFields = bindingResult.getFieldErrors().stream()
				.map(field -> {
					String message = this.messageSource.getMessage(field, LocaleContextHolder.getLocale());

					return Problem.Field.builder()
							.name(field.getField())
							.userMessage(message)
							.build();
				}).collect(Collectors.toList());

		Problem problem = this.createProblemBuilder(status, problemType, body)
				.userMessage(body)
				.fields(problemFields)
				.build();
		return this.handleExceptionInternal(ex, problem, new HttpHeaders(), status, request);
	}

	@ExceptionHandler(ProporcaoIncorretaException.class)
	public ResponseEntity<?> handleProporcaoIncorreta(ProporcaoIncorretaException ex, WebRequest request) {

		HttpStatus status = HttpStatus.BAD_REQUEST;
		ProblemType problemType = ProblemType.PROPORCAO_DA_CARTEIRA_INCORRETA;
		String body = ex.getMessage();
		Problem problem = this.createProblemBuilder(status, problemType, body)
				.userMessage(body)
				.build();
		return this.handleExceptionInternal(ex, problem, new HttpHeaders(), status, request);
	}

	@ExceptionHandler(EntidadeDuplicadaException.class)
	public ResponseEntity<?> handleEntidadeDuplicada(EntidadeDuplicadaException ex, WebRequest request) {

		HttpStatus status = HttpStatus.BAD_REQUEST;
		ProblemType problemType = ProblemType.ENTIDADE_DUPLICADA;
		String body = ex.getMessage();
		Problem problem = this.createProblemBuilder(status, problemType, body)
				.userMessage(body)
				.build();
		return this.handleExceptionInternal(ex, problem, new HttpHeaders(), status, request);
	}

	@Override
	protected ResponseEntity<Object> handleExceptionInternal(Exception ex, Object body, HttpHeaders headers,
			HttpStatusCode status, WebRequest request) {

		if(body == null) {
			body = Problem.builder()
					.status(status.value())
					.title(HttpStatus.valueOf(status.value()).getReasonPhrase())
					.timeStamps(LocalDateTime.now())
					.userMessage(MSG_SYSTEM_ERRO)
					.build();
		} else if(body instanceof String) {
			body = Problem.builder()
					.status(status.value())
					.title((String) body)
					.timeStamps(LocalDateTime.now())
					.userMessage(MSG_SYSTEM_ERRO)
					.build();
		}

		return super.handleExceptionInternal(ex, body, headers, status, request);
	}



	private Problem.ProblemBuilder createProblemBuilder(HttpStatusCode status, ProblemType problemType, String detail) {
		return  Problem.builder()
				.status(status.value())
				.timeStamps(LocalDateTime.now())
				.type(problemType.getUri())
				.title(problemType.getTitle())
				.detail(detail);
	}
}
