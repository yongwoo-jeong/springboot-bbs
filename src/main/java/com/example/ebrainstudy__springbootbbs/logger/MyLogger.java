package com.example.ebrainstudy__springbootbbs.logger;

import java.util.logging.Level;
import java.util.logging.Logger;

public class MyLogger {
	/**
	 * myLogger 이름의 로거 객체 생성
	 */
	Logger logger = Logger.getLogger("mylogger");
	/**
	 * 내부적으로 생성 스태틱 로거 인스턴스
	 */
	private static MyLogger instance = new MyLogger();
	/**
	 * 로그 레벨 ALL 로 설정
	 */
	private MyLogger(){ // 싱글톤 패턴
		logger.setLevel(Level.ALL);
	}

	/**
	 * 외부에서 마이로거를 사용할 때 인스턴스를 반환받기 위한 메서드
	 * @return 마이로거 스태틱 인스턴스 반환
	 */
	public static MyLogger getLogger(){
		return instance;
	}
	/**
	 * 마이로거를 사용중인 클래스명을 반환해줌
	 * 어느 클래스에서 로그가 남았는지 확인을 위함
	 * @return 클래스명
	 */
	public static String getClassName() {
		return Thread.currentThread().getStackTrace()[2].getClassName();
	}
	public void info(String msg){
		logger.fine(msg);
	}
	public void warning(String msg){
		logger.warning(msg);
	}
	public void severe(String msg){
		logger.severe(msg);
	}

}