package com.letz.utils.common.exception;

/**
 * �봽濡쒖젥�듃 �삁�쇅 理쒖긽�쐞 �겢�옒�뒪
 * 
 */
public class MallException extends RuntimeException {
	private static final long serialVersionUID = -3464619227592080122L;

	protected MessageCode errorCode;

	public MallException() {
	}

	public MallException(String message) {
		this(message, null, null);
	}

	public MallException(String message, Throwable throwable) {
		this(message, null, throwable);
	}

	public MallException(MessageCode errorCode) {
		this(errorCode.toMessage(), errorCode, null);
	}

	public MallException(String message, MessageCode errorCode, Throwable throwable) {
		super(message, throwable);
		this.errorCode = errorCode;
	}

	public MallException(Exception e) {
		super(e);
	}

}
