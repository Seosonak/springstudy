package com.myshop;

import org.springframework.boot.SpringApplication;

import org.springframework.boot.autoconfigure.SpringBootApplication;


// 보이는 코드는 단순하지만 해당 어노테이션 안에는 많은 기능들이 들어가있다.
// 스프링부트의 자동설정, Bean읽기 생성 모두 자동으로 설정되어있다. 
// 해당 어노테이션이 있는 클래스위치의 설정부터 읽어가기때문에 
// 다른 컨트롤러가 존재해도 해당 클래스가 메인클래스의 역할을 하는 것이다.
// 그러므로 @SpringBootApplication어노테이션이 붙어있는 클래스는 !!항상 프로젝트 최상단!! 에 위치해야한

@SpringBootApplication
public class MyshopApplication {


	//메인어플리케이션. 프로그램 구동시키는곳? 컨트롤러같은느낌? 근ㄷ ㅔ컨트롤러가 따로있는디 ..
	public static void main(String[] args) {
		SpringApplication.run(MyshopApplication.class, args);
		
	}
}
