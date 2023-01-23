package com.myshop.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.myshop.entity.ItemImg;

public interface ItemImgRepository extends JpaRepository<ItemImg, Long> {
	 List<ItemImg> findByItemIdOrderByIdAsc(Long itemId); //itemId로 찾는거라서 itemId매개변수를 쥬기 '~'
	 //id가 2일경우 ! localhost/admin/new/2 로접속이되어야수정페이지에 접속가능
}
