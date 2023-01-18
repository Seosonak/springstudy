package com.myshop.entity;

import javax.persistence.*;

import lombok.*;

// orders와 order_item중에선 orderitem이 주인
@Entity // 클래스를 엔티티로 선언
@Table(name = "order_item") // 테이블명 설정(설정안할시 클래스이름으로 된다), 엔티티와 매핑할 테이블 지정
@Getter
@Setter
@ToString
public class OrderItem {

	@Id
	@Column(name="order_item_id")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@ManyToOne(fetch = FetchType.LAZY) //fetchType.LAZY : 지연로딩 (ManyToOne에 붙여주면됨!)
	@JoinColumn(name="order_id")
	private Order order;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="item_id")
	private Item item;
	
	private int orderPrice; //주문가격
	
	private int count;  //주문수량
}
