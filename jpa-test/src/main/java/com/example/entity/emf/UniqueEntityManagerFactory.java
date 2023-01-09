package com.example.entity.emf;

import javax.persistence.EntityManagerFactory;

public class UniqueEntityManagerFactory {
//엔티티매니저팩토리. 하나! 그래서 여기저기에서 쓰기위해 static! 
	public static EntityManagerFactory emf;
}
