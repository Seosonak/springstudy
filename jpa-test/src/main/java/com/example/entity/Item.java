package com.example.entity;

import lombok.*;


import javax.persistence.*;

@Entity
@Getter
@Setter
@ToString
@Table(name="ITEM")

public class Item {
	
	@Id
	@Column(name="ITEM_ID")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	private String name;
	
	private int price;
	
	@Column(name="STOCKQUANTITY")
	private int stock;

}
