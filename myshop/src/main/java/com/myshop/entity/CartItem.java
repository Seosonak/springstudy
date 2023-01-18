package com.myshop.entity;

import javax.persistence.*;

import lombok.*;

@Entity // 클래스를 엔티티로 선언
@Table(name = "cart_item") // 테이블명 설정(설정안할시 클래스이름으로 된다), 엔티티와 매핑할 테이블 지정
@Getter
@Setter
@ToString
public class CartItem {
	
	@Id
	@Column(name="cart_item_id")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="cart_id")
	private Cart cart;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="item_id")
	private Item item;
	
	private int count;
	

}
