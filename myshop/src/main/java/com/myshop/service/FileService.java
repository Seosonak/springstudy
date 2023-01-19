package com.myshop.service;

import java.io.File;
import java.io.FileOutputStream;
import java.util.UUID;

import org.springframework.stereotype.Service;

import lombok.extern.java.Log;




@Log  //lombok에 있는 어노테이션 (말그대로 로그를 남기는 어노테이션임 )
@Service
public class FileService {
	// 파일업로드
	public String uploadFile(String uploadPath, String originalFileName, byte[] fileData) throws Exception {
		UUID uuid = UUID.randomUUID(); //중복되지않는 랜덤한 파일이름 생성
		
		String extension = originalFileName.substring(originalFileName.lastIndexOf(".")); //확장자명 분리
		String savedFileName = uuid.toString() + extension; //파일이름을 생성해줌
		String fileUploadFullUrl = uploadPath + "/" + savedFileName; //파일업로드 경로(절대경로)를 만들어줌
		
		// 생성자에 파일이 저장될 위치와 파일이름을 넘겨서 출력스트림을 만든다  
		FileOutputStream fos = new FileOutputStream(fileUploadFullUrl);
		fos.write(fileData); //파일은 byte단위로 옴. 출력스트림에 파일데이터입력
		fos.close(); //입출력소스는 항상 닫아줘야함!
		
		return savedFileName;
	}

	// 파일 삭제
	public void deleteFile(String filePath) throws Exception {
		File deleteFile = new File(filePath); //파일이 저장된 경로를 이용, 파일객체를 생성한다(java.io임포트)
		
		
		if(deleteFile.exists()) { 	// 해당경로에 해당파일이 있으면
			deleteFile.delete();   // 해당 파일을 삭제
			log.info("파일을 삭제하였습니다."); //삭제하고 로그로 남김
		} else {
			log.info("파일이 존재하지 않습니다.");
		}
		

	}
}
