package com.example.entity;

import lombok.*;
import javax.persistence.*;

@Entity
@Getter
@Setter
@ToString
@Table(name="ORDER_ITEM")
public class OrderItem {
	
	@Id
	@Column(name="ORDER_ITEM_ID")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	private Long orderid;
	
	private Long itemid;
	
	
	@Column(name="ORDERPRICE")
	private int price;
	
	private int count;

}
