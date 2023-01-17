package com.myshop.controller;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
@AutoConfigureMockMvc 
@TestPropertySource(locations = "classpath:application-test.properties")
class ItemControllerTest {
	@Autowired
	MockMvc mockMvc;
	
	@Test
	@DisplayName("상품 등록 페이지 권한 테스트")
	@WithMockUser(username = "admin", roles ="ADMIN") //유저가 로그인된 상태로 테스트할수있게해줌
	public void itemFormTest() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.get("/admin/item/new")) // get방식으로 "/" 해당페이지로 리퀘스트함
			   .andDo(print()) // 리퀘스트를하면 응답이온다.요청과 응답메시지를 확인(콘솔에서출력)
			   .andExpect(status().isOk()); // 응답상태코드가 정상인지 확인(200이 정상 maybe... )후 정상이면 통과
			   
		
	}
	
	
	@Test
	@DisplayName("상품 등록 페이지 일반회원 접근 테스트")
	@WithMockUser(username = "user", roles ="USER") // 유저가 로그인된 상태로 테스트할수있게해줌
	public void itemFormNotAdminTest() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.get("/admin/item/new")) // get방식으로 "/" 해당페이지로 리퀘스트함
			   .andDo(print()) // 리퀘스트를하면 응답이온다.요청과 응답메시지를 확인(콘솔에서출력)
			   .andExpect(status().isForbidden()); // isForbidden(403)으로 나와야 테스트 통과
			   
		
	}
}
