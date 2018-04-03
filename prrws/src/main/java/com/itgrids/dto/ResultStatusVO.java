package com.itgrids.dto;

public class ResultStatusVO {
	
	private int resultCode;
    private Throwable exceptionEncountered;
    private boolean isResultPartial;	
    private String exceptionClass;
    private String exceptionMsg;
    private String message;
    private String host;
    private String tempHost;
    private Long resultState;
    private String uniqueKey;
    private Long serverPrimaryKey;
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
	public boolean isResultPartial() {
		return isResultPartial;
	}
	public void setResultPartial(boolean isResultPartial) {
		this.isResultPartial = isResultPartial;
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
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public String getHost() {
		return host;
	}
	public void setHost(String host) {
		this.host = host;
	}
	public String getTempHost() {
		return tempHost;
	}
	public void setTempHost(String tempHost) {
		this.tempHost = tempHost;
	}
	public Long getResultState() {
		return resultState;
	}
	public void setResultState(Long resultState) {
		this.resultState = resultState;
	}
	public String getUniqueKey() {
		return uniqueKey;
	}
	public void setUniqueKey(String uniqueKey) {
		this.uniqueKey = uniqueKey;
	}
	public Long getServerPrimaryKey() {
		return serverPrimaryKey;
	}
	public void setServerPrimaryKey(Long serverPrimaryKey) {
		this.serverPrimaryKey = serverPrimaryKey;
	}
    }
