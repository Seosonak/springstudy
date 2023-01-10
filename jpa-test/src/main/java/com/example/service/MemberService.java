package com.example.service;

import javax.persistence.*;
import com.example.entity.Member;
import com.example.entity.emf.UniqueEntityManagerFactory;

public class MemberService {

	public void save(Member member) {
		// 엔티티매니저팩토리 ! 애플리케이션당 하나
		EntityManagerFactory emf = UniqueEntityManagerFactory.emf;
		// 엔티티 매니저 : 엔티티 매니저팩토리를 생성한다
		EntityManager em = emf.createEntityManager();
		// 트래잭션(쪼갤수없는 업무의 단위) : 트래잭션단위로 업무하기때문에 중요함!
		EntityTransaction tx = em.getTransaction();

		try {
			tx.begin(); // 트래잭션 시작과 동시에 커넥션을 획득
			em.persist(member); // 영속 : 엔티티매니저를 통해 엔티티를 영속성컨테스트에 저장함
			tx.commit(); // 커밋 ! 이때 실제 DB에 저장이 됨
		} catch (Exception e) {
			e.printStackTrace(); // 에러출력
			tx.rollback(); // 에러가 있으면 커밋전으로 롤백시켜줌
		} finally {
			em.close(); // *준영속 : 영속성 컨텍스트에서 관리하던 영속상태의 엔티티를 영속성컨텍스트가 관리하지않음.

			// em.detach(member); or clear 둘다써도되고 둘중하나만써도됨
			// em.clear();

			// em.remove(member); // 삭제상태 : 영속성 컨텍스트와 db에서 삭제된상태
		}
	}
}
