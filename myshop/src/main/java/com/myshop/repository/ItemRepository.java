package com.myshop.repository;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

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
	
	
	/*

	List<Item> findByItemNmAndItemSellStatus(String itemNm, ItemSellStatus itemSellStatus);
	
	List<Item> findByPriceBetween(Integer startprice, Integer endprice);
	//선생님 풀이 : List<Item> findByPriceBetween(Integer price1, Integer price2);
	
	List<Item> findByRegTimeAfter(LocalDateTime regTime);

	List<Item> findByItemSellStatusNotNull();

	List<Item> findByItemDetailEndingWith(String itemDetail);

	*/
	
	// jpql~! 이름에규칙이 있는건 아니지만 비슷하게작성해주는게 좋다 
	
	/*// @Param 주는 방법 1
	@Query("select i from Item i where i.itemDetail like %:itemDetail% order by i.price desc") // 별칭(i) 지어줘야함! 
	List<Item> findByItemDetail(@Param("itemDetail") String itemDetail); //@Param : 
	*/
	
	// @Param 방법2 (숫자) %?1% : 첫번째 파라미터라는 뜻
	@Query("select i from Item i where i.itemDetail like %?1% order by i.price desc") // 별칭(i) 지어줘야함! 
	List<Item> findByItemDetail(String itemDetail);
	
	//네이티브 쿼리 
	@Query(value = "select * from item i where i.item_detail like %:itemDetail% order by i.price desc", nativeQuery = true)
	List<Item> findByItemDetailByNative(@Param("itemDetail") String itemDetail);
	
	/*
	//퀴즈2-1 
	@Query("select i from Item i where i.price >= :price")
	List<Item> findByItemPrice(@Param("price") Integer price);
	
	//퀴즈2-2
	@Query("select i from Item i where i.itemNm = :itemNm and i.itemSellStatus = :sell")
	List<Item> findByNmSell(@Param("itemNm") String itemNm, @Param("sell") ItemSellStatus sell);
	/*
	@Query(value = "select * from item i where i.itemNm like %:itemNm% and i.itemSellStatus", nativeQuery = true)
	List<Item> findByitemNmSell(@Param("itemNm, itemSellStatus") String itemNm, ItemSellStatus itemSellStatus);
*/

}