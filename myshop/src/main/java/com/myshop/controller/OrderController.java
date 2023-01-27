package com.myshop.controller;


import java.security.Principal;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import com.myshop.dto.OrderDto;
import com.myshop.dto.OrderHistDto;
import com.myshop.service.OrderService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class OrderController {
	private final OrderService orderService;
	/*
	 비동기 처리 : 본문에 담긴 내용을 자바객체로 전달
	 리퀘스트바디 item dtl.html의 파람데이터를 전달하는거임
	 리스폰스바디는 자바객체를 http요청의 바디로 전달
	 리퀘스트 헤더는 요청에 대한 요약본같은것
	  
	  비동기처리시 컨트롤러에서 두가지를 써줘야한다 
	  
	  */
	
	/*
	 ResponseEntity
	 http요청 또는 응답에 해당하는http헤더 바디를 포함하는 클래스
	리턴해줄값을 담는다..?  앞단에다 보내준다(뷰페이지에) 
	  */
	
	/*
	 Principal
	 로그인한 사용자에 대한 정보를 얻을수있는 객체.
	 */
    @PostMapping(value = "/order")
    public @ResponseBody ResponseEntity order(@RequestBody @Valid OrderDto orderDto
            , BindingResult bindingResult, Principal principal){

        if(bindingResult.hasErrors()){
            StringBuilder sb = new StringBuilder();
            List<FieldError> fieldErrors = bindingResult.getFieldErrors();

            for (FieldError fieldError : fieldErrors) {
                sb.append(fieldError.getDefaultMessage());
            }

            return new ResponseEntity<String>(sb.toString(), HttpStatus.BAD_REQUEST);
        }

        String email = principal.getName();
        Long orderId;

        try {
            orderId = orderService.order(orderDto, email);
        } catch(Exception e){
            return new ResponseEntity<String>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<Long>(orderId, HttpStatus.OK);
    }
    
    @GetMapping(value = {"/orders", "/orders/{page}"})
    public String orderHist(@PathVariable("page") Optional<Integer> page, Principal principal, Model model ) {
    	Pageable pageable = PageRequest.of(page.isPresent() ? page.get() : 0, 4); 
    	Page<OrderHistDto> orderHistDtoList = orderService.getOrderList(principal.getName(), pageable);
    	
    	model.addAttribute("orders", orderHistDtoList);
    	model.addAttribute("page", pageable.getPageNumber()); //현재 페이지
    	model.addAttribute("maxPage", 5);
    	
    	return "order/orderHist";
    }
    
    @PostMapping("/order/{orderId}/cancel")
    public @ResponseBody ResponseEntity cancelOrder(@PathVariable("orderId") Long orderId, Principal principal) {
    	if(!orderService.validateOrder(orderId, principal.getName())) {
    		return new ResponseEntity<String>("주문 취소 권한이 없습니다", HttpStatus.FORBIDDEN);
    	}
    	
    	orderService.cancelOrder(orderId);
    	return new ResponseEntity<Long>(orderId, HttpStatus.OK);
    	
    }
    
}