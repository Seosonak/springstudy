package com.example;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class StudentController {
	
	@GetMapping(value = "/student1")
	public StudentDto student1() {
		StudentDto sDto = new StudentDto();
		sDto.setName("yuna");
		sDto.setAge(20);
		sDto.setJavaGrade("A+");
		sDto.setOracleGrade("C");
		return sDto;
	}

	@GetMapping(value = "/student2")
	public StudentDto student2() {
		StudentDto sDto = new StudentDto();
		sDto.setName("jimin");
		sDto.setAge(21);
		sDto.setJavaGrade("B+");
		sDto.setOracleGrade("F");
		return sDto;
	}
}
