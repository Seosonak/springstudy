package com.myshop.entity;

import java.time.LocalDateTime;


import javax.persistence.*;
import com.myshop.constant.ItemSellStatus;
import com.myshop.dto.ItemFormDto;

import lombok.*;

@Entity // 클래스를 엔티티로 선언
@Table(name = "item") // 테이블명 설정(설정안할시 클래스이름으로 된다), 엔티티와 매핑할 테이블 지정
@Getter
@Setter
@ToString
public class Item extends BaseEntity{
//not null이 아닐때는 필드타입을 객체로 지정해야함 (ex . int -> Integer)
	
	@Id //pk
	@Column(name="item_id")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id; // 상품코드

	@Column(nullable = false, length = 50)
	private String itemNm; // 상품명

	@Column(nullable = false, name = "price")
	private int price; // 가격

	@Column(nullable = false)
	private int stockNumber; // 재고수량

	@Lob //대용량 데이터타입으로 지정  
	@Column(nullable = false)
	private String itemDetail; // 상품상세설명

	@Enumerated(EnumType.STRING)
	private ItemSellStatus itemSellStatus; // 상품 판매 상태 (열거형클래스)

	//업데이트
	public void updateItem(ItemFormDto itemFormDto) {
		this.itemNm = itemFormDto.getItemNm();
		this.price = itemFormDto.getPrice();
		this.stockNumber = itemFormDto.getStockNumber();
		this.itemDetail = itemFormDto.getItemDetail();
		this.itemSellStatus = itemFormDto.getItemSellStatus();
	}
//	private LocalDateTime regTime; // 등록 시간
//
//	private LocalDateTime updateTime; // 수정 시간

}
