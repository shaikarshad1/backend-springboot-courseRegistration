package com.cts.studentmicroservice.exceptionHandling;


public class ErrorResponsePojo {
	private String message;
	private String title;
	public ErrorResponsePojo(String message, String title) {
		super();
		this.message = message;
		this.title = title;
	}
	public ErrorResponsePojo() {
		super();
		// TODO Auto-generated constructor stub
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}

	
	
}