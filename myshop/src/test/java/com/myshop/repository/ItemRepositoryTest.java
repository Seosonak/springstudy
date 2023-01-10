package com.myshop.repository;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDateTime;
import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.*;
import org.springframework.test.context.TestPropertySource;

import com.myshop.constant.ItemSellStatus;
import com.myshop.entity.Item;

@SpringBootTest
@TestPropertySource(locations = "classpath:application-test.properties")
class ItemRepositoryTest {

	@Autowired // 생성된 해당 클래스 타입 객체의 인스턴스연결
	ItemRepository itemRepository;

//	@Test
//	@DisplayName("상품 저장 테스트용")
//	public void createItemTest() {
//		Item item = new Item();
//		item.setItemNm("테스트 상품");
//		item.setPrice(10000);
//		item.setItemDetail("테스트 상품 상세 설명 ");
//		item.setItemSellStatus(ItemSellStatus.SELL);
//		item.setStockNumber(100);
//		item.setRegTime(LocalDateTime.now());
//		item.setUpdateTime(LocalDateTime.now());
//		
//		Item savedItem = itemRepository.save(item); // save =insert / update 의 기능이 들어간애
//		System.out.println(savedItem.toString());
//		
//		
//	}
//
		@Test
	public void createItemTest() {
		for (int i = 1; i < 10; i++) {
			Item item = new Item();
			item.setItemNm("테스트 상품" + i);
			item.setPrice(10000 + i);
			item.setItemDetail("테스트 상품 상세 설명" + i);
			item.setItemSellStatus(ItemSellStatus.SELL);
			item.setStockNumber(100);
			item.setRegTime(LocalDateTime.now());
			item.setUpdateTime(LocalDateTime.now());

			Item savedItem = itemRepository.save(item); // save =insert / update 의 기능이 들어간애

		}

	}

	@Test
	@DisplayName("상품명 조회 테스트용")
	public void findByItemNmTest() {
		//this.createItemTest(); // item 테이블에 insert
		List<Item> itemList = itemRepository.findByItemNm("테스트 상품2");
		for (Item item : itemList) {
			System.out.println(item.toString());
		}
	}
	
	@Test
	@DisplayName("상품명, 상품상세설명 or 테스트")
	public void findByItemNmorItemDetailTest() {
//		this.createItemTest();
		List<Item> itemList = itemRepository.findByItemNmOrItemDetail("테스트 상품1", "테스트 상품 상세설명5");
		for (Item item : itemList) {
			System.out.println(item.toString());
		}
	}
	
	@Test
	@DisplayName("가격 LessThan 테스트")
	public void findByPriceLessThanTest() {
//		this.createItemTest();
		List<Item> itemList = itemRepository.findByPriceLessThan(10005);
		for (Item item : itemList) {
			System.out.println(item.toString());
		}
	}
	
	
	@Test
	@DisplayName("가격 내림차순 조회 테스트")
	public void indByPriceLessThanOrderByPriceDescTest() {
		//this.createItemTest();
		List<Item> itemList = itemRepository.findByPriceLessThanOrderByPriceDesc(10005);
		for (Item item : itemList) {
			System.out.println(item.toString());
		}
	}
	
//    퀴즈 1.	
	/*
	@Test
	@DisplayName("itemNm : <테스트상품1>이자, Status : Sell인 것")
	public void findByItemNmAndItemSellStatus() {
		this.createItemTest();
		List<Item> itemList = itemRepository.findByItemNmAndItemSellStatus("테스트 상품1", ItemSellStatus.SELL);
		for (Item item : itemList) {
			System.out.println(item.toString());
		}
	}
	*/
	
	//퀴즈2.price가 10004~ 10008 사이인 레코드
	/*
	@Test
	@DisplayName("가격이 10004~10008사이")
	public void findByPriceBetween() {
		this.createItemTest();
		List<Item> itemList = itemRepository.findByPriceBetween(10004, 10008); 
				for (Item item : itemList) {
					System.out.println(item.toString());
				}
		
	}
	*/
	
	/*
	@Test
	@DisplayName("2023-1-1 12:12:44 이후의 레코드!")
	public void findByRegTimeAfter() {
		this.createItemTest();
		List<Item> itemList = itemRepository.findByRegTimeAfter(LocalDateTime.of(2023, 1, 1, 12, 12, 44));
		for (Item item : itemList) {
			System.out.println(item.toString());
		}
	}
	*/
	
	/*
	@Test
	@DisplayName("Null아닌거 ")
	public void findByItemSellStatusNotNull() {
		this.createItemTest();
		List<Item> itemList = itemRepository.findByItemSellStatusNotNull();
		for (Item item : itemList) {
			System.out.println(item.toString());
		}
	}
	*/
	
	
	@Test
	@DisplayName("디테일 설명1로 끝나는 레코드")
	public void findByItemDetailLike() {
		this.createItemTest();
		List<Item> itemList = itemRepository.findByItemDetailEndingWith("1");
		for (Item item : itemList) {
			System.out.println(item.toString());
		}
	}
	
}
