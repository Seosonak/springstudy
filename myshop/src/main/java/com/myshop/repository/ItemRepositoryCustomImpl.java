package com.myshop.repository;

import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.EntityManager;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.thymeleaf.util.StringUtils;

import com.myshop.constant.ItemSellStatus;
import com.myshop.dto.ItemSearchDto;
import com.myshop.entity.Item;
import com.myshop.entity.QItem;
import com.querydsl.core.QueryResults;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;

public class ItemRepositoryCustomImpl implements ItemRepositoryCustom {

	private JPAQueryFactory queryFactory;

	public ItemRepositoryCustomImpl(EntityManager em) {
		this.queryFactory = new JPAQueryFactory(em);
	}

	private BooleanExpression regDtsAfter(String searchDateType) {
		LocalDateTime dateTime = LocalDateTime.now();

		if (StringUtils.equals("all", searchDateType) || searchDateType == null)
			return null;
		else if (StringUtils.equals("1d", searchDateType))
			dateTime = dateTime.minusDays(1);
		else if (StringUtils.equals("1w", searchDateType))
			dateTime = dateTime.minusWeeks(1);
		else if (StringUtils.equals("1m", searchDateType))
			dateTime = dateTime.minusMonths(1);
		else if (StringUtils.equals("6m,", searchDateType))
			dateTime = dateTime.minusMonths(6);

		return QItem.item.regTime.after(dateTime); // 이후의 시간

	}

	private BooleanExpression searchSellStatusEq(ItemSellStatus searchSellStatus) {
		return searchSellStatus == null ? null : QItem.item.itemSellStatus.eq(searchSellStatus);

	}

	private BooleanExpression searchByLike(String searchBy, String searchQuery) {
		if (StringUtils.equals("itemNm", searchBy)) { // itemNm이 들어오면
			return QItem.item.itemNm.like(""); // itemNm LIKE %청바지%
		} else if (StringUtils.equals("createdBy", searchBy)) { // createdBy LIKE %test.com%
			return QItem.item.createdBy.like("");
		}
		return null;
	}

	@Override
	public Page<Item> getAdminItemPage(ItemSearchDto itemSearchDto, Pageable pageable) {
		List<Item> content = queryFactory.selectFrom(QItem.item) // = select * from item
				.where(regDtsAfter(itemSearchDto.getSearchDateType()), // where reg_time = ?
						searchSellStatusEq(itemSearchDto.getSearchSellStatus()), // and sell_status = ?
						searchByLike(itemSearchDto.getSearchBy(), itemSearchDto.getSearchQuery())) // and itemNm LIKE %검색어%
				.orderBy(QItem.item.id.desc())
				.offset(pageable.getOffset()) //데이터를 가져올 시작 인덱스
				.limit(pageable.getPageSize()) //한번에 가지고 올 갯수 
				.fetch();

		//전체 데이터의 갯수를 구함 (사이즈로 가져옴)
		long total = content.size();
	    	
		return new PageImpl<>(content, pageable, total);
		
	}

}
