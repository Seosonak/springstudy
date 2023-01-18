package com.myshop.entity;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;
import com.myshop.constant.OrderStatus;

import lombok.*;

@Entity // 클래스를 엔티티로 선언
@Table(name = "orders") // 테이블명 설정(설정안할시 클래스이름으로 된다), 엔티티와 매핑할 테이블 지정
@Getter
@Setter
@ToString
public class Order {

	
	@Id
	@Column(name="order_id")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="member_id")
	private Member member;
	
	private LocalDateTime orderDate; //주문일

	@Enumerated(EnumType.STRING)
	private OrderStatus orderStatus; //주문상태
	
	//오더아이템은 여러개여서 리스트에 담아야함
	@OneToMany(mappedBy ="order", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY) // "order" : 오더아이템안에 있는 오더를 말함
	private List<OrderItem> orderItems = new ArrayList<>();
}
