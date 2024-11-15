package com.hexaware.exceptions;

import java.time.LocalDateTime;

public class ErrorDetails {
	LocalDateTime time;
	String msg;
	String path;
	String errorCode;
	
	public ErrorDetails(LocalDateTime time, String msg, String path, String errorCode) {
		super();
		this.time = time;
		this.msg = msg;
		this.path = path;
		this.errorCode = errorCode;
	}
 
	public LocalDateTime getTime() {
		return time;
	}
	public void setTime(LocalDateTime time) {
		this.time = time;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	public String getPath() {
		return path;
	}
	public void setPath(String path) {
		this.path = path;
	}
	public String getErrorCode() {
		return errorCode;
	}
	public void setErrorCode(String errorCode) {
		this.errorCode = errorCode;
	}
	@Override
	public String toString() {
		return "errorDetails [time=" + time + ", msg=" + msg + ", path=" + path + ", errorCode=" + errorCode + "]";
	}

}
