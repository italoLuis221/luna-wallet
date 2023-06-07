package com.lunawallet.core.security;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;


@Configuration
@ComponentScan
@EnableWebMvc
public class WebMvcConfig implements WebMvcConfigurer {

   @Override
	public void addViewControllers(ViewControllerRegistry registry) {
		WebMvcConfigurer.super.addViewControllers(registry);
		registry.addViewController("/login").setViewName("pages/login");
		registry.setOrder(Ordered.HIGHEST_PRECEDENCE);
		
	}
   
   @Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
	   registry.addResourceHandler("/**")
       .addResourceLocations("classpath:/static/");
	}

}
