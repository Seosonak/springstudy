package com.example;

// lombok,어노테이션을  이용하면 getset을일일히 안해줘도된당!! 
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString  //객체의 정보를 출력할때 사용  

public class UserDto {
// dto 객체데이터를 쓰기위한 클래스
	private String name;
	private int age;
	
	
	
}
