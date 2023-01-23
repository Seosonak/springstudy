package com.myshop.service;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;

import org.apache.groovy.parser.antlr4.util.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.myshop.entity.ItemImg;
import com.myshop.repository.ItemImgRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional
public class ItemImgService {
	
	@Value("${itemImgLocation}")
	private String itemImgLocation;
	
	private final ItemImgRepository itemImgRepository;
	
	private final FileService fileService;
	
	public void saveItemImg(ItemImg itemImg, MultipartFile itemImgFile) throws Exception {
		String oriImgName = itemImgFile.getOriginalFilename(); //파일원본이름만 뽑아옴 
		String imgName = ""; //빈문자열
		String imgUrl = "";
		
		// 파일업로드
		if (!StringUtils.isEmpty(oriImgName)) { //파일이름이 빈문자나 null이 아니면 업로드해줌
			imgName = fileService.uploadFile(itemImgLocation, oriImgName, itemImgFile.getBytes());
			imgUrl = "/images/item/" + imgName;
		}
		
		//상품 이미지 정보 저장하기
		itemImg.updateItemImg(oriImgName, imgName, imgUrl);
		itemImgRepository.save(itemImg);  //영속성컨텐츠에  save하면 DB에 저장됨 ! 
		
	}
	
	//이미지 업데이트 메소드
	public void updateItemImg(Long itemImgId, MultipartFile itemImgFile) throws Exception{
		if(!itemImgFile.isEmpty()) { //뭔가파일이 있으면 
			ItemImg savedItemImg = itemImgRepository.findById(itemImgId)
													.orElseThrow(EntityNotFoundException::new);
			
			//기존 이미지파일 삭제
			if(!StringUtils.isEmpty(savedItemImg.getImgName())) {
				fileService.deleteFile(itemImgLocation + "/" + savedItemImg.getImgName());
			}
			//수정된 이미지파일 재업로드
			String oriImgName = itemImgFile.getOriginalFilename(); //파일원본이름만 뽑아옴 
			String imgName = fileService.uploadFile(itemImgLocation, oriImgName, itemImgFile.getBytes());
			String imgUrl = "/images/item/" + imgName;
			
			// ***** 중요 ******* savedItemImg는 현재 영속상태이므로 데이터를 변경하는 것만으로 변경감지 기능이 동작하여 트랜잭션이 끝날때 업데이트쿼리가 실행됨
			// -> 엔티티가 반드시 영속상태여야 함 !  주의주의주의주의주의 !@!@!@!@@!@!#!@#!@!!!! 
			savedItemImg.updateItemImg(oriImgName, imgName, imgUrl);
				
		}
	}
}
