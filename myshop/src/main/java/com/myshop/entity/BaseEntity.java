package com.myshop.entity;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;

import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import lombok.Getter;
import lombok.Setter;

@EntityListeners(value = {AuditingEntityListener.class}) //auditing을 적용하기위한 어노테이션
@MappedSuperclass //부모클래스를 상속받는 자식클래스한테 매핑정보만 제공
@Getter
@Setter
public class BaseEntity extends BaseTimeEntity{
	
	@CreatedBy
	@Column(updatable = false)
	private String createBy; //등록자
	
	@LastModifiedBy
	private String modifiedBy; //수정

}
