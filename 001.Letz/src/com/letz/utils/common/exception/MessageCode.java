package com.letz.utils.common.exception;

/**
 * 에러 코드에 대한 인터페이스.
 * 
 * 에러코드 처리는 Framework 에 구현되지만, 실제 에러 코드는 Application 영역이기 때문에 분리를 위해 인터페이스를 정의 하였다.
 * 
 * @author narusas
 *
 */
public interface MessageCode {

	MessageType getType();

	String getMessageCode();

	/**
	 * 에러메시지를 반환한다.
	 */
	String toMessage();

	/**
	 * 주어진 인자를 Format 한 에러 메시지를 반환한다.
	 */
	String toMessage(Object... args);

	/**
	 * UTF-8 URL Encoding 된 메시지를 반환한다.
	 */
	String toUrlEncodedMessage();

	String toUrlEncodedMessage(Object... args);

	/**
	 * MessageSource 에 해당 ErrorCode 에 대한 메시지를 찾을수 없을때 기본으로 사용할 에러 메시지.
	 * 
	 * @return
	 */
	String getDefaultMessage();

	/**
	 * 에러코드를 예외로 변환한다.
	 * 
	 */
	MallException toException();

	/**
	 * getMessage(Object... args) 에 넘길 인자를 같이 받는다.
	 * 
	 */
	MallException toException(Object... args);

	/**
	 * 다른 예외를 감싸서 예외로 발생시킨다.
	 */
	MallException toNestedException(Throwable throwable);

	/**
	 * 다른 예외를 감싸서 예외로 발생시킨다.
	 */
	MallException toNestedException(Throwable throwable, Object... args);
}
