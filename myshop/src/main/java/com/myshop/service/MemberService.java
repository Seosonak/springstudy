package com.myshop.service;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.myshop.repository.MemberRepository;
import com.myshop.entity.Member;
import lombok.RequiredArgsConstructor;

@Service //서비스 클래스의 역할 (서비스와 레파지토리가 DAO라고 생각하면 됨)
@Transactional //서비스 클래스에서 로직을 처리하다가 에러가 발생하면 로직을 수행하기 이전상태로 되돌려줌
@RequiredArgsConstructor //
public class MemberService implements UserDetailsService{ //UserDetailsService :로그인시 리퀘스트에서 넘어온 사용자 정보를 받음

	private final MemberRepository memberRepository; //의존성 주입
	
	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		Member member = memberRepository.findByEmail(email);
		
		if(member == null) {
			throw new UsernameNotFoundException(email);
		}
		
		//userDetails의 객체를 반환
		return User.builder()
				.username(member.getEmail())
				.password(member.getPassword())
				.roles(member.getRole().toString())
				.build();
	}

	
	public Member saveMember(Member member) {
		validateDuplicateMember(member);
		return memberRepository.save(member); // member 테이블에 insert
	}
	
	//이메일 중복체크하는 메소드
	private void validateDuplicateMember(Member member) {
		Member findMember = memberRepository.findByEmail(member.getEmail());
		if (findMember != null) {
			throw new IllegalStateException("이미 가입된 회원입니다.");
		}
	}

}
