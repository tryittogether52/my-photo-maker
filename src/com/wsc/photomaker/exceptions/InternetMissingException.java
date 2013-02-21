package com.wsc.photomaker.exceptions;

@SuppressWarnings("serial")
public class InternetMissingException extends Exception {

	public InternetMissingException() {
		super();
	}

	public InternetMissingException(String detailMessage) {
		super(detailMessage);
	}
}
