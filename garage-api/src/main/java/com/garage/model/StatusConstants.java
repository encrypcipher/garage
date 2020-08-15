package com.garage.model;

import org.springframework.stereotype.Component;

@Component
public class StatusConstants {
	private static final String SUCCESS = "200";
	private static final String BAD_REQUEST = "4XX";
	private static final String SERVER_ERROR = "5XX";
	
	public String getSuccess() {
        return SUCCESS;
    }

	public String getBadRequest() {
		return BAD_REQUEST;
	}

	public String getSeerverError() {
		return SERVER_ERROR;
	}
}
