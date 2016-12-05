package com.itgrids.cardprint.dto;

import java.io.Serializable;

public class ResultStatus implements Serializable{
	
	private static final long serialVersionUID = 7386906567965997588L;
	
	private Long id;
	private int resultCode;
    private Throwable exceptionEncountered;
    private boolean isResultPartial;	
    private String exceptionClass;
    private String exceptionMsg;
    
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public int getResultCode() {
		return resultCode;
	}
	public void setResultCode(int resultCode) {
		this.resultCode = resultCode;
	}
	public Throwable getExceptionEncountered() {
		return exceptionEncountered;
	}
	public void setExceptionEncountered(Throwable exceptionEncountered) {
		this.exceptionEncountered = exceptionEncountered;
	}
	public String getExceptionClass() {
		return exceptionClass;
	}
	public void setExceptionClass(String exceptionClass) {
		this.exceptionClass = exceptionClass;
	}
	public String getExceptionMsg() {
		return exceptionMsg;
	}
	public void setExceptionMsg(String exceptionMsg) {
		this.exceptionMsg = exceptionMsg;
	}
	public boolean getIsResultPartial() {
		return isResultPartial;
	}
	public void setResultPartial(boolean isResultPartial) {
		this.isResultPartial = isResultPartial;
	}
}
