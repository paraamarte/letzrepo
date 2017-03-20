package com.letz.utils.common.exception;

/**
 * ���� �ڵ忡 ���� �������̽�.
 * 
 * �����ڵ� ó���� Framework �� ����������, ���� ���� �ڵ�� Application �����̱� ������ �и��� ���� �������̽��� ���� �Ͽ���.
 * 
 * @author narusas
 *
 */
public interface MessageCode {

	MessageType getType();

	String getMessageCode();

	/**
	 * �����޽����� ��ȯ�Ѵ�.
	 */
	String toMessage();

	/**
	 * �־��� ���ڸ� Format �� ���� �޽����� ��ȯ�Ѵ�.
	 */
	String toMessage(Object... args);

	/**
	 * UTF-8 URL Encoding �� �޽����� ��ȯ�Ѵ�.
	 */
	String toUrlEncodedMessage();

	String toUrlEncodedMessage(Object... args);

	/**
	 * MessageSource �� �ش� ErrorCode �� ���� �޽����� ã���� ������ �⺻���� ����� ���� �޽���.
	 * 
	 * @return
	 */
	String getDefaultMessage();

	/**
	 * �����ڵ带 ���ܷ� ��ȯ�Ѵ�.
	 * 
	 */
	MallException toException();

	/**
	 * getMessage(Object... args) �� �ѱ� ���ڸ� ���� �޴´�.
	 * 
	 */
	MallException toException(Object... args);

	/**
	 * �ٸ� ���ܸ� ���μ� ���ܷ� �߻���Ų��.
	 */
	MallException toNestedException(Throwable throwable);

	/**
	 * �ٸ� ���ܸ� ���μ� ���ܷ� �߻���Ų��.
	 */
	MallException toNestedException(Throwable throwable, Object... args);
}
