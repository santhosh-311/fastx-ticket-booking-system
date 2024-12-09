package com.hexaware.exceptions;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

public class ErrorDetails {
	LocalDateTime time;
	String msg;
	String path;
	Integer errorCode;
	Map<String,String> hashMap;
	
	public ErrorDetails(LocalDateTime time, String msg, String path, Integer errorCode) {
		super();
		this.time = time;
		this.msg = msg;
		this.path = path;
		this.errorCode = errorCode;
	}
	public ErrorDetails(LocalDateTime time, Map<String,String> hashMap, String path, Integer errorCode) {
		super();
		this.time = time;
		this.hashMap=hashMap;
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
	public Integer getErrorCode() {
		return errorCode;
	}
	public void setErrorCode(Integer errorCode) {
		this.errorCode = errorCode;
	}
	
	
	public Map<String, String> getHashMap() {
		return hashMap;
	}
	public void setHashMap(Map<String, String> hashMap) {
		this.hashMap = hashMap;
	}
	@Override
	public String toString() {
		return "errorDetails [time=" + time + ", msg=" + msg + ", path=" + path + ", errorCode=" + errorCode + "]";
	}

}
