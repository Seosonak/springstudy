package com.example;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

	@GetMapping(value = "/test")
	public UserDto test() {
		UserDto userDto = new UserDto();
		userDto.setAge(12);
		userDto.setName("김봄");
		
		System.out.println("객체 정보:" + userDto.toString());
		userDto.toString();
	
		return userDto;
		
	}
	
}
