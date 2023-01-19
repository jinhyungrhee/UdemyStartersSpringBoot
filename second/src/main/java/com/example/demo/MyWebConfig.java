package com.example.demo;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

// 설정파일임을 알려주는 어노테이션
@Configuration
public class MyWebConfig implements WebMvcConfigurer {
	
	// <resources mapping="/upload/**"  location="file:///c:/upload/" />
	
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("/upload/**").addResourceLocations("file:///c:/upload/");
			
	}
}
