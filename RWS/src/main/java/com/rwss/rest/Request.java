package com.rwss.rest;

public class Request<T> {
	
	public enum OperationType {
		FETCH("FETCH"),ADD("ADD"),UPDATE("UPDATE"),DELETE("DELETE"),
		FETCHBYCLAUSE("FETCHBYCLAUSE");
		
		private String value;
		OperationType(String value) {
			this.value = value;
		}
		
		public String getValue() {
			return this.value;
		}
	}
	
	private String type;
	
	private T data;

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public T getData() {
		return data;
	}

	public void setData(T data) {
		this.data = data;
	}
}
