package com.example.demo;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

// @SpringBootApplicaiton : 설정 파일 +실행 파일
@SpringBootApplication // 내가 설정 클래스 파일 (com.example.demo만 componentScan하는 경우)
// default(basePackages="com.example.demo")와 새로운 패키지 모두 추가하는 경우
@ComponentScan
@ComponentScan(basePackages="upload")
@ComponentScan(basePackages="spring.mybatis")
@MapperScan(basePackages="spring.mybatis")
@ComponentScan(basePackages="websocket")
public class SecondApplication {

	public static void main(String[] args) {
		SpringApplication.run(SecondApplication.class, args);
	}

}
