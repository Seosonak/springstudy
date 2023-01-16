package com.myshop.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.myshop.entity.Member;

public interface MemberRepository extends JpaRepository<Member, Long> {
	 Member findByEmail(String email); //회원가입시 중복 회원이 있는지 검사하기 위해 (이메일셀렉트해와서 선검사)
	 
}
