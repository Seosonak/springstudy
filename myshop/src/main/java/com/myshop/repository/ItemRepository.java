package com.myshop.repository;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.myshop.constant.ItemSellStatus;
import com.myshop.entity.Item;

//인터페이스 DAO역할을 함 
// JpaRepository <사용할 엔티티 클래스, 사용할 엔티티클래스의 기본키타입> 
// JpaRepository  :  기본적인 CRUD 및 페이징 처리를 위한 메소드가 정의 되어있다.
public interface ItemRepository extends JpaRepository<Item, Long> {
	//select * from item where item_nm = ? 
	List<Item> findByItemNm(String itemNm);
	
	//select * from item where item_nm = ? or item_detail = ?
	List<Item> findByItemNmOrItemDetail(String itemNm, String itemDetail);
	
	//select * from item where price < ? 
	List<Item> findByPriceLessThan(Integer price);
	
	//select * from item where price < ? order by price desc;
	List<Item> findByPriceLessThanOrderByPriceDesc(Integer price);
	
	// 1. itemNm이 “테스트 상품1” 이고 ItemSellStatus가 Sell인 레코드를 구하는 Junit 테스트 코드를 완성하시오.
	//List<Item> findByItemNmAndItemSellStatus(String itemNm, ItemSellStatus itemSellStatus);
	
	
	// 2. price가 10004~ 10008 사이인 레코드 
	//List<Item> findByPriceBetween(Integer startprice, Integer endprice);
	
	// 3.regTime이 2023-1-1 12:12:44 이후의 레코드
	//List<Item> findByRegTimeAfter(LocalDateTime regTime);

	
	// 4. itemSellStatus가 null이 아닌 레코드
	//List<Item> findByItemSellStatusNotNull();

	// 5.itemDetail이 설명1로 끝나는 레코드
	List<Item> findByItemDetailEndingWith(String itemDetail);

}