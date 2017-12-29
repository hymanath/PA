package com.itgrids.partyanalyst.dto;

import java.io.Serializable;
/**
 * used in the DTO classes to know the result status of a task
 * @author Narender
 *
 */
/**
 * @author Teja
 *
 */
public class ResultStatus implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -4612224221019720019L;
	
	private int resultCode;
    private Throwable exceptionEncountered;
    private boolean isResultPartial;	
    private String exceptionClass;
    private String exceptionMsg;
    private String message;
    private String host;
    private String tempHost;
    private Long resultState;
    private Long tabPrimaryKey;
    private String uniqueKey;
    private Long serverPrimaryKey;
    private String filePath;
    
    
    public Long getServerPrimaryKey() {
		return serverPrimaryKey;
	}
	public void setServerPrimaryKey(Long serverPrimaryKey) {
		this.serverPrimaryKey = serverPrimaryKey;
	}
	public Long getTabPrimaryKey() {
		return tabPrimaryKey;
	}
	public void setTabPrimaryKey(Long tabPrimaryKey) {
		this.tabPrimaryKey = tabPrimaryKey;
	}
	public String getUniqueKey() {
		return uniqueKey;
	}
	public void setUniqueKey(String uniqueKey) {
		this.uniqueKey = uniqueKey;
	}
	public String getTempHost() {
		return tempHost;
	}
	public void setTempHost(String tempHost) {
		this.tempHost = tempHost;
	}
	public String getHost() {
		return host;
	}
	public void setHost(String host) {
		this.host = host;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
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
	public Long getResultState() {
		return resultState;
	}
	public void setResultState(Long resultState) {
		this.resultState = resultState;
	}
	public String getFilePath() {
		return filePath;
	}
	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}
	
    
    
}
