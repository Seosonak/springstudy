package com.crud.entity;

import java.time.LocalDateTime;

import javax.persistence.*;

import org.springframework.data.annotation.CreatedDate;

import lombok.*;

@Entity
@Table(name = "crudboard")
@Getter
@Setter
@ToString
public class CrudEntity {

	@Id
	@Column(name="user")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;  //작성자(유저아이디)
	
	@Column(name="number")
	private int boardNm; //글번호
	
	@Column(name="title", nullable = false)
	private String title; //글제목
	
	@Lob
	@Column(name="content", nullable = false)
	private String content; //글내용
	
	@CreatedDate
	@Column(updatable = false, name="regtime")
	private LocalDateTime regTime; //게시글 등록날짜
}
