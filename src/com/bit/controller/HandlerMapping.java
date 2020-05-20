package com.bit.controller;

import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

public class HandlerMapping {
	private Map<String, Controller> mappings = null;

	// configName : 설정 파일 경로
	public HandlerMapping(String configName) {
		mappings = new HashMap<>();

		Properties prop = new Properties();

		try {
			// Resources 상대 경로 입력
			InputStream inputStream = this.getClass().getResourceAsStream(configName);

			prop.load(inputStream);
			Set<Object> keys = prop.keySet();
			/*
			 * Set의 특징 : 저장 순서를 지키지 않는다.
			 * 	-> *bean.properties 에 작성된 순서와 다르다.
			 * key : /loginForm.do
			 * ....
			 * write.do 단계에서 예외 발생(ClassCastException)
			 * logout.do 수행하지 않았다.
			 *  프로그램 동작 시 LogoutController만 문제가 발생한 이유 
			 *  
			 *  WriteController에서 오류 발생했을 때 
			 *  logout.do가 실행순서 상 write.do보다 밑에 있을 때
			 *  로그아웃이 오류남 
			 *  
			 * */

			for (Object key : keys) {
				String className = prop.getProperty(key.toString());

				Class<?> clz = Class.forName(className);
				//추적이 어려우면, 콘솔 로그로 확인
				System.out.println("className :" + className);
				System.out.println("key :" + key);
				
				mappings.put(key.toString(), (Controller) clz.newInstance());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// 요청 들어온 uri에 대한 Controller 객체를 반환
	public Controller getController(String uri) {
		return mappings.get(uri);
	}
}
