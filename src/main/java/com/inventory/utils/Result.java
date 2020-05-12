package com.inventory.utils;


public class Result<T> {
	private T content;
	private String message;

	public Result() {
		content = null;
		message = "";
	}

	public Result (T obj) {
		content = obj;
		message = "";
	}
	
	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public T getContent() {
		return content;
	}

	public void setContent(T obj) {
		this.content = obj;
	}
}

