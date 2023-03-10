package com.myshop.controller;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.*;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.myshop.dto.MemberFormDto;
import com.myshop.entity.Member;
import com.myshop.service.MemberService;

import lombok.RequiredArgsConstructor;

@RequestMapping("/members")
@Controller
@RequiredArgsConstructor
public class MemberController {
	private final MemberService memberService;
	private final PasswordEncoder passwordEncoder;
	
	
	//회원가입 화면을 보여주는 곳
	@GetMapping(value = "/new")
	public String memberForm(Model model) {
		model.addAttribute("memberFormDto", new MemberFormDto());
		return "member/memberForm";
		
	}
	
	//회원가입 버튼을 눌렀을때 실행되는 메소드
	@PostMapping(value = "/new")
	//@Valid : 유효성을 검증하려는 객체앞에 붙인다.
	//BindingResult : 유효성 검증 후에 결과를 넣어준다. 
	public String memberForm(@Valid MemberFormDto memberFormDto, BindingResult bindingResult, Model model) {
		
		if (bindingResult.hasErrors()) {
			return "member/memberForm";
		}
		try {
		Member member = Member.createMember(memberFormDto, passwordEncoder);
		memberService.saveMember(member);
		} catch (IllegalStateException e) {
			model.addAttribute("errorMessage", e.getMessage());
			return "member/memberForm";
		}
		return "redirect:/";  //insert,update할땐 redirect를 해줘야함! (안해주면 데이터가 또 들어갈수있음)
		
	}

	//로그인화면
	@GetMapping(value = "/login")
	public String loginMember() {
		return "member/memberLoginForm";
		
	}
	
	//세션 매니저 (의존성주입 )
	private final SessionManager sessionManager;
	
	/*
	//쿠키, 세션테스트
	@PostMapping(value = "/login2")
	public String loginMember2(HttpServletResponse response, HttpSession session, @RequestParam String email) {
		System.out.println("email:" + email);
		Cookie idCookie = new Cookie("userCookieId2", email);
		response.addCookie(idCookie);
		
	//	session.setAttribute("userSessionId2", email);
		
		sessionManager.createSession(email, response);
		
		return "member/memberLoginForm";
	}
	*/
	
	
	//로그인 실패했을때 화면
	@GetMapping(value = "/login/error")
	public String loginError(Model model) {
		model.addAttribute("loginErrorMsg", "아이디 또는 비밀번호를 확인해주세요.");
		return "member/memberLoginForm";
		
	}
}
