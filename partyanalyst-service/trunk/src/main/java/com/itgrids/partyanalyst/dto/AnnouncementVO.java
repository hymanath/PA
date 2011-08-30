package com.itgrids.partyanalyst.dto;

import java.io.Serializable;


public class AnnouncementVO implements Serializable{
		
	private static final long serialVersionUID = 1L;
	private String title;
    private String message;
    private String fromDate;
    private String toDate;
    private Long userId;
    private Long constituency;
    private String constituencyName;
    private Long state;
    private String windowTask;
    private Long announcementId;
    private String type;
    private ResultStatus resultStatus;
    private String userName;
       
    public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public ResultStatus getResultStatus() {
		return resultStatus;
	}
	public void setResultStatus(ResultStatus resultStatus) {
		this.resultStatus = resultStatus;
	}
	public Long getState() {
		return state;
	}
	public void setState(Long state) {
		this.state = state;
	}
	public Long getConstituency() {
		return constituency;
	}
	public void setConstituency(Long constituency) {
		this.constituency = constituency;
	}
    
    public Long getAnnouncementId() {
		return announcementId;
	}
	public void setAnnouncementId(Long announcementId) {
		this.announcementId = announcementId;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public String getFromDate() {
		return fromDate;
	}
	public void setFromDate(String fromDate) {
		this.fromDate = fromDate;
	}
	public String getToDate() {
		return toDate;
	}
	public void setToDate(String toDate) {
		this.toDate = toDate;
	}
	public Long getUserId() {
		return userId;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	public String getWindowTask() {
		return windowTask;
	}
	public void setWindowTask(String windowTask) {
		this.windowTask = windowTask;
	}
	public String getConstituencyName() {
		return constituencyName;
	}
	public void setConstituencyName(String constituencyName) {
		this.constituencyName = constituencyName;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
		    
}
