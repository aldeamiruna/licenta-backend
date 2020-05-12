package com.inventory.configuration;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@EnableWebMvc
public class WebConfig implements WebMvcConfigurer {

	@Bean
	public ModelMapper modelMapper() {
		ModelMapper modelMapper = new ModelMapper();
		return modelMapper;
	}

	@Override
	public void addCorsMappings(CorsRegistry registry) {
		utilMapping(registry, "/items/**");
		utilMapping(registry, "/types/**");
		utilMapping(registry, "/rooms/**");
		utilMapping(registry, "/states/**");
		utilMapping(registry, "/users/**");
		utilMapping(registry, "/document/**");
		utilMapping(registry, "/history/**");

	}

	private void utilMapping(CorsRegistry registry, String path) {
		registry.addMapping(path).allowedOrigins("http://localhost:4200")
				.allowedMethods("OPTIONS", "GET", "PUT", "POST", "DELETE").allowedHeaders("*");
	}
}
