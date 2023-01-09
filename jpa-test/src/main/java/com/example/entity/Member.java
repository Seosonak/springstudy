package com.example.entity;

import java.util.Date;

import javax.persistence.*;

import com.example.constant.MemberClass;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter


public class Member {

	
	@Id
// column  - db에서 사용할컬럼정의 id,name,memberclass,date는 컬럼이 될것!
// name="member_id" 처럼 지정해주면 컬럼이름이지정해준이름으로되고 지정해주지않으면 그냥 아래적힌이름..
	@Column(name="member_id")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@Column
	private String name;
	
	@Enumerated(EnumType.STRING) //열거형클래스쓸거면 무조건 써줘야함!
	@Column
	private MemberClass memberclass;
	
	@Temporal(TemporalType.TIMESTAMP) //날짜타입 맵핑할때사용 
	private Date date;
}
