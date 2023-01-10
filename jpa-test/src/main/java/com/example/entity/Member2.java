package com.example.entity;

import javax.persistence.*;

import lombok.*;

@Entity
@Getter
@Setter
@Table(name = "MEMBER")

public class Member2 {

	@Id
	@Column(name="MEMBER_ID")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@Column
	private String name;
	
	private String city;
	
	private String street;
	
	private String zipcode;
	
	
}
