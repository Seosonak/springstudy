package com.myshop.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.myshop.dto.ItemSearchDto;
import com.myshop.dto.MainItemDto;
import com.myshop.entity.Item;

// 1. 사용자정의 인터페이스
public interface ItemRepositoryCustom {

	// 상품 관리 페이지 아이템을 가져온다
	Page<Item> getAdminItemPage(ItemSearchDto itemSearchDto, Pageable pageable);

	// 메인화면에 뿌리는 아이템을 가져온다
	Page<MainItemDto> getMainItemPage(ItemSearchDto itemSearchDto, Pageable pageable);
	
}
