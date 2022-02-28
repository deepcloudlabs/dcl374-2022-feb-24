package com.example.crm.exception;

@SuppressWarnings("serial")
public class CustomerNotFoundException extends RuntimeException {
	private final int i18nId;
	private final String debugId;

	public CustomerNotFoundException(String message, int i18nId, String debugId) {
		super(message);
		this.i18nId = i18nId;
		this.debugId = debugId;
	}

	public int getI18nId() {
		return i18nId;
	}

	public String getDebugId() {
		return debugId;
	}

	
}
