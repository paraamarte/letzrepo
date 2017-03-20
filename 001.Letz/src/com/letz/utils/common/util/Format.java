package com.sivillage.common.util;

public class Format {
	String template;

	public Format(String template) {
		super();
		this.template = template;
	}

	public String format(Object... args) {
		return String.format(template, args);
	}

	public String format() {
		return template;
	}

	@Override
	public String toString() {
		return template;
	}

}