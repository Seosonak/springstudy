package com.myshop.entity;

import javax.persistence.*;

import lombok.*;

@Entity
@Table(name="item_img")
@Getter
@Setter
public class ItemImg extends BaseEntity{
	
	@Id
	@Column(name="item_img_id")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	private String imgName; //이미지파일명
	
	private String oriImgName; //원본이미지 파일명
	
	private String imgUrl; //이미지조회경로
	
	private String repimgYn; //대표이미지여부
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "item_id")
	private Item item;
	
	//원본이미지 파일명, 업데이트할 이미지파일명, 이미지경로를파라메터로 받아서 이미지정보를 업데이트하는 메소드
	public void updateItemImg(String oriImgName, String imgName, String imgUrl) {
		this.oriImgName =oriImgName;
		this.imgName = imgName;
		this.imgUrl = imgUrl;
	}
	

}
