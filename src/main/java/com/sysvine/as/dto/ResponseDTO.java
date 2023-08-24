package com.sysvine.as.dto;

import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ResponseDTO {

	private status status;

	private message message;

	private String error;
	
	private Date timestamp;
	
	private List<?> data;
	
	public ResponseDTO(status status, message message,List<?> data) {
		this.timestamp = new Date();
		this.status = status;
		this.message = message;
		this.data=data;
	}
	
	public ResponseDTO(status status, message message, String error) {
		this.timestamp = new Date();
		this.status = status;
		this.message = message;
		
		this.error=error;
	}
	
	public ResponseDTO() {
	}

	public enum status {
		TRUE, FALSE;
	}
	public enum message {
		SUCCESS, FAILED;
	}
	
}
