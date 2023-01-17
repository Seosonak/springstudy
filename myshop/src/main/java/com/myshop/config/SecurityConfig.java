package com.myshop.config;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.context.annotation.Bean;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import com.myshop.service.MemberService;

@Configuration //스프링에서 설정클래스로 사용하겠다고 선언하는 어노테이션
@EnableWebSecurity  // 스프링시큐리티필터체인이 자동으로 포함됨 
public class SecurityConfig {
	
	@Autowired
	MemberService memberService;
	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		//로그인에 대한 설정
		http.formLogin()
			.loginPage("/members/login") //로그인 페이지 URL설정
			.defaultSuccessUrl("/") //로그인 성공시 이동할 페이지
			.usernameParameter("email") //로그인 사용할 파라메터 이름
			.failureUrl("/members/login/error") //로그인 실패시 이동할URL 
			.and()
			.logout()
			.logoutRequestMatcher(new AntPathRequestMatcher("/members/logout")) //로그아웃  URL주소 
			.logoutSuccessUrl("/"); //로그아웃 성공시 이동할 URL
		
		//권한에관련된것 (누가 어떤페이지에 접근하고 그런것..) 회원인경우,관리자인경우 나눠서  
		//페이지의 접근에 관한 설정 
		http.authorizeRequests()
		.mvcMatchers("/css/**", "/js/**", "/img/**").permitAll() 
			.mvcMatchers("/", "/members/**", "/item/**", "/images/**").permitAll() //모든사용자가 로그인(인증) 없이 접근할 수 있도록 설정
			.mvcMatchers("/admin/**").hasRole("ADMIN") // '/admin'으로 시작하는 경로는 계정이 ADMIN role일 경우에만 접근 가능하도록 설정 
			.anyRequest().authenticated(); //그외의 페이지는 인증(=로그인)을 받아야한다.
		
		//인증되지 않은 사용자가 리소스(페이지,이미지등)에 접근했을때 설정
	    http.exceptionHandling().authenticationEntryPoint(new CustomAuthenticationEntryPoint());
		
		
		return http.build();
		
	}

	
	
	@Bean
	public PasswordEncoder passwordEncoder() { //비밀번호 암호화를 위해서 사용하는 Bean(스프링에서 사용하는객체)
		 return new BCryptPasswordEncoder();  //암호화를하기위해 쓰는 함수라고 생각하면 됨 (만들어지는걸 사용하는 것)
	 }
}
