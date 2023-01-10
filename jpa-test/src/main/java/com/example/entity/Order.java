package com.example.entity;

import lombok.*;

import java.util.Date;

import javax.persistence.*;
import javax.transaction.Status;

@Entity
@Getter
@Setter
@ToString
@Table(name="ORDERS")

public class Order {

	@Id
	@Column(name="ORDER_ID")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	private Long member_id;
	
	private Date orderdate;
	
	private Status status;
}
