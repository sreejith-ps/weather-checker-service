package com.etslt.oss.dto;

import java.util.Date;

public class ErrorResponse {
	private Integer errorCode;
	private String errorMessage;
	private Date timestamp;

	public ErrorResponse(Integer errorCode, String errorMessage, Date timestamp) {
		super();
		this.errorCode = errorCode;
		this.errorMessage = errorMessage;
		this.timestamp = timestamp;
	}

	public Integer getErrorCode() {
		return errorCode;
	}

	public String getErrorMessage() {
		return errorMessage;
	}

	public Date getTimestamp() {
		return timestamp;
	}
	
	
}
