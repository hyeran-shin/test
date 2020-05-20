package com.bit.util;

import java.io.File;
import java.util.UUID;

import com.oreilly.servlet.multipart.FileRenamePolicy;

public class MyFileRenamePolicy implements FileRenamePolicy {
	@Override
	public File rename(File f) {
		// 확장자 데이터 추출
		String name = f.getName();
		String ext = ""; // 확장자, ext는 확장자 약자
		int dot = name.lastIndexOf(".");

		if (dot != -1) { // 확장자가 존재한다면,
			ext = name.substring(dot);
		} else {
			ext = "";
		}

		
		/*
		 * UID (User Identifier)
		 * 	- 사용자 식별자(OS에 따라 32비트, 64비트 등...)
		 * UUID (Universally Unique Identifier) 
		 *  - 범용 고유 식별자 (32개의 16진수 표현, 128비트, ...)
		*/	
		
		String fileName="bit-" + UUID.randomUUID(); // 유니크한 랜덤 식별자 생성
		File renameFile = new File(f.getParentFile(), fileName+ext);
		// 새로운 파일 이름과 기존 확장자를 붙인 새 파일 생성!
		System.out.println("파일이름 : " + fileName);
		System.out.println("변경이름 : " + renameFile);
		
		return renameFile; // 이름을 바꾼 파일 반환!
	}
}






