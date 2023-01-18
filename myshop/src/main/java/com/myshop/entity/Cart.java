package com.myshop.entity;


import javax.persistence.*;
import lombok.*;

@Entity // 클래스를 엔티티로 선언
@Table(name = "cart") // 테이블명 설정(설정안할시 클래스이름으로 된다), 엔티티와 매핑할 테이블 지정
@Getter
@Setter
@ToString
public class Cart {
	@Id
	@Column(name="cart_id")
	 
	private Long id;
	
	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="member_id")
	private Member member;
	
}
