package com.example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

//메인
//rest = ResponseBody _ Controller <mvc에서의 컨트롤러!!   : 컨트롤러의 역할을 하면서 동시에 입력..?뭐라고하셨지..까뭇땅...ㅎㅎ..

@RestController
@SpringBootApplication
public class SpringDemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringDemoApplication.class, args);
	}
	
	@GetMapping(value = "/") //경로를 맵핑해주는 기능 
	public String Helloworld() {
		return "Hello world! 80이라고했는데 왜 8080이지ㅎㅎ..";
	}

}
