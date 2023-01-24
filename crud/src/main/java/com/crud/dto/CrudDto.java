package com.crud.dto;

import java.time.LocalDateTime;

import lombok.*;

@Getter
@Setter
public class CrudDto {

	private Long id;
	
	private int boardNm;
	
	private String title;
	
	private String content;
	
	private LocalDateTime regTime;
	
}
