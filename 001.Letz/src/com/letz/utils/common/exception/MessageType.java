package com.letz.utils.common.exception;

/**
 * 에러 종류 정의
 * 
 */
public enum MessageType {
	/**
	 * 업무적으로 성공일때
	 */
	SUCCESS,

	/**
	 * 업무적 또는 사용자 요청에 문제가 있을때는 실패(Fail)
	 */
	FAIL, MSG,

	/**
	 * 파일 읽기, 네트워크 접속, DB 오류등 시스템에 문제가 있을때는 에러(Error)
	 */
	ERROR;
}
