package com.myshop.repository;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.apache.tomcat.jni.Time;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.TestPropertySource;

import com.myshop.constant.ItemSellStatus;
import com.myshop.entity.Item;
import com.myshop.entity.QItem;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.QueryFactory;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.querydsl.*;

import org.thymeleaf.*;
import org.thymeleaf.util.StringUtils;

@SpringBootTest
@TestPropertySource(locations = "classpath:application-test.properties")
class ItemRepositoryTest {

	@Autowired // 생성된 해당 클래스 타입 객체의 인스턴스연결
	ItemRepository itemRepository;

	@PersistenceContext // 영속성컨텍스트를 사용하기 위해 선언
	EntityManager em; // 엔티티 매니저 (어노테이션으로선언하면 바로 사용할수있어용)

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
	public void createItemTest2() {

		for (int i = 1; i <= 5; i++) {
			Item item = new Item();
			item.setItemNm("테스트 상품" + i);
			item.setPrice(10000 + i);
			item.setItemDetail("테스트 상품 상세 설명" + i);
			item.setItemSellStatus(ItemSellStatus.SELL);
			item.setStockNumber(100);
			item.setRegTime(LocalDateTime.now());
			item.setUpdateTime(LocalDateTime.now());
			Item savedItem = itemRepository.save(item); // data insert
		}

		for (int i = 6; i <= 10; i++) {
			Item item = new Item();
			item.setItemNm("테스트 상품" + i);
			item.setPrice(10000 + i);
			item.setItemDetail("테스트 상품 상세 설명" + i);
			item.setItemSellStatus(ItemSellStatus.SOLD_OUT);
			item.setStockNumber(0);
			item.setRegTime(LocalDateTime.now());
			item.setUpdateTime(LocalDateTime.now());
			Item savedItem = itemRepository.save(item); // data insert
		}
	}

	@Test
	@DisplayName("상품명 조회 테스트용")
	public void findByItemNmTest() {
		// this.createItemTest(); // item 테이블에 insert
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
		// this.createItemTest();
		List<Item> itemList = itemRepository.findByPriceLessThanOrderByPriceDesc(10005);
		for (Item item : itemList) {
			System.out.println(item.toString());
		}
	}
	/*
	 * // 퀴즈 1. itemNm이 “테스트 상품1” 이고 ItemSellStatus가 Sell인 레코드
	 * 
	 * @Test
	 * 
	 * @DisplayName("itemNm : <테스트상품1>이자, Status : Sell인 것") public void
	 * findByItemNmAndItemSellStatus() { this.createItemTest(); List<Item> itemList
	 * = itemRepository.findByItemNmAndItemSellStatus("테스트 상품1",
	 * ItemSellStatus.SELL); for (Item item : itemList) {
	 * System.out.println(item.toString()); } }
	 * 
	 * 
	 * //퀴즈2.price가 10004 ~ 10008 사이인 레코드
	 * 
	 * @Test
	 * 
	 * @DisplayName("가격이 10004~10008사이") public void findByPriceBetween() {
	 * this.createItemTest(); List<Item> itemList =
	 * itemRepository.findByPriceBetween(10004, 10008); for (Item item : itemList) {
	 * System.out.println(item.toString()); }
	 * 
	 * }
	 * 
	 * 
	 * 
	 * @Test
	 * 
	 * @DisplayName("2023-1-1 12:12:44 이후의 레코드!") public void findByRegTimeAfter() {
	 * this.createItemTest(); List<Item> itemList =
	 * itemRepository.findByRegTimeAfter(LocalDateTime.of(2023, 1, 1, 12, 12, 44));
	 * for (Item item : itemList) { System.out.println(item.toString()); } }
	 * 
	 * 
	 * @Test
	 * 
	 * @DisplayName("Null아닌거 ") public void findByItemSellStatusNotNull() {
	 * this.createItemTest(); List<Item> itemList =
	 * itemRepository.findByItemSellStatusNotNull(); for (Item item : itemList) {
	 * System.out.println(item.toString()); } }
	 * 
	 * 
	 * @Test
	 * 
	 * @DisplayName("디테일 설명1로 끝나는 레코드") public void findByItemDetailLike() {
	 * this.createItemTest(); List<Item> itemList =
	 * itemRepository.findByItemDetailEndingWith("1"); for (Item item : itemList) {
	 * System.out.println(item.toString()); } }
	 * 
	 */

	@Test
	@DisplayName("@Query를 이용한 상품 조회 테스트 ")
	public void findByItemDetailTest() {
		this.createItemTest();
		List<Item> itemList = itemRepository.findByItemDetail("테스트 상품 상세 설명");
		for (Item item : itemList) {
			System.out.println(item.toString());
		}
	}

	@Test
	@DisplayName("네이티브 쿼리를 이용한 상품 조회 테스트 ")
	public void findByItemDetailByNativeTest() {
		this.createItemTest();
		List<Item> itemList = itemRepository.findByItemDetailByNative("테스트 상품 상세 설명");
		for (Item item : itemList) {
			System.out.println(item.toString());
		}
	}

	// 퀴즈
	/*
	 * @Test
	 * 
	 * @DisplayName("@Query퀴즈2-1") public void findByItemPriceTest() {
	 * this.createItemTest(); List<Item> itemList =
	 * itemRepository.findByItemPrice(10005); for(Item item : itemList) {
	 * System.out.println(item.toString()); } }
	 * 
	 * @Test
	 * 
	 * @DisplayName("@Query퀴즈2-2") public void findByNmSellTest() {
	 * this.createItemTest(); List<Item> itemList =
	 * itemRepository.findByNmSell("테스트 상품1", ItemSellStatus.SELL); for(Item item :
	 * itemList) { System.out.println(item.toString()); } }
	 */

	@Test
	@DisplayName("DSL 조회 테스트")
	public void queryDslTest() {
		this.createItemTest();
		JPAQueryFactory qf = new JPAQueryFactory(em); // 쿼리를 동적으로 생성하기 위한 객체 (
		QItem qItem = QItem.item;

		// 쿼리문을 만든것!
		JPAQuery<Item> query = qf.selectFrom(qItem) // [select * from item]
				.where(qItem.itemSellStatus.eq(ItemSellStatus.SELL)) // eq : 이퀄의 약자 [ where itemSellStatus = 'SELL']
				.where(qItem.itemDetail.like("%테스트 상품 상세 설명%")) // 쿼리에서의 like
				.orderBy(qItem.price.desc()); // 가격 내림차순으로 정렬

		// 쿼리문 실행 (데이터 결과를 반환하는 메소드)
		List<Item> itemList = query.fetch();
		for (Item item : itemList) {
			System.out.println(item.toString());
		}

	}

	// 검색기능만드는거라고 생각하면 됨 !
	@Test
	@DisplayName("DSL 조회 테스트2")
	public void queryDslTest2() {
		this.createItemTest2();

		JPAQueryFactory qf = new JPAQueryFactory(em);
		QItem qItem = QItem.item;
		Pageable page = PageRequest.of(0, 2); // of(조회할 페이지의 번호, 페이지당 조회할 데이터의 갯수) 
		//조건에 맞는데이터가 3개여도 조회할 데이터갯수를 2개로 지정했으면 2개만 보여줌(앞의것부터순차적으로)

		JPAQuery<Item> query = qf.selectFrom(qItem) // [select * from item]
				.where(qItem.itemSellStatus.eq(ItemSellStatus.SELL)) // eq : 이퀄의 약자 [ where itemSellStatus = 'SELL']
				.where(qItem.itemDetail.like("%테스트 상품 상세 설명%")) // 쿼리에서의 like
				.where(qItem.price.gt(10003)) // 10003보다 큰
				.offset(page.getOffset()).limit(page.getPageSize());

		List<Item> itemList = query.fetch();
		for (Item item : itemList) {
			System.out.println(item.toString());
		}

	}
	
	//퀴즈1 
	@Test
	@DisplayName("DSL 퀴즈1")
	public void DslquizTest() {
		this.createItemTest2();
		JPAQueryFactory qf = new JPAQueryFactory(em);
		QItem qItem = QItem.item;
		
		JPAQuery<Item> query = qf.selectFrom(qItem)
								.where(qItem.itemNm.like("%테스트 상품1"))
								.where(qItem.itemSellStatus.eq(ItemSellStatus.SELL));
		
		List<Item> itemList = query.fetch();
		for (Item item : itemList) {
			System.out.println(item.toString());
		}
	}
	
	
	
	
	@Test
	@DisplayName("DSL 퀴즈2")
	public void Dslquiz2Test() {
		this.createItemTest2();
		JPAQueryFactory qf = new JPAQueryFactory(em);
		QItem qItem = QItem.item;
		
		JPAQuery<Item> query = qf.selectFrom(qItem)
								  .where(qItem.price.between(10004, 10008));
		
		List<Item> itemList = query.fetch();
		for (Item item : itemList) {
			System.out.println(item.toString());
		}
	}
	
	@Test
	@DisplayName("DSL 퀴즈3")
	public void Dslquiz3Test() {
		this.createItemTest2();
		JPAQueryFactory qf = new JPAQueryFactory(em);
		QItem qItem = QItem.item;
		
		JPAQuery<Item> query = qf.selectFrom(qItem)
								.where(qItem.regTime.after(LocalDateTime.of(2023, 1, 1, 12, 12, 44)));
		
		List<Item> itemList = query.fetch();
		for (Item item : itemList) {
			System.out.println(item.toString());
		}
	}
	
	@Test
	@DisplayName("DSL 퀴즈4")
	public void Dslquiz4Test() {
		this.createItemTest2();
		JPAQueryFactory qf = new JPAQueryFactory(em);
		QItem qItem = QItem.item;
		
		JPAQuery<Item> query = qf.selectFrom(qItem)
								 .where(qItem.itemSellStatus.isNotNull());
		List<Item> itemList = query.fetch();
		for (Item item : itemList) {
			System.out.println(item.toString());
		}
	}
	
	@Test
	@DisplayName("DSL 퀴즈5")
	public void Dslquiz5Test() {
		this.createItemTest2();
		JPAQueryFactory qf = new JPAQueryFactory(em);
		QItem qItem = QItem.item;
		
		JPAQuery<Item> query = qf.selectFrom(qItem)
								 .where(qItem.itemDetail.endsWith("1"));
		List<Item> itemList = query.fetch();
		for (Item item : itemList) {
			System.out.println(item.toString());
		}
	}
	

}
