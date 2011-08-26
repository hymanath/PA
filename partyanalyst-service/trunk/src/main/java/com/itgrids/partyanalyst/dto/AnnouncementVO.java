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
    private Long state;
    private String windowTask;
    private Long announcementId;
       
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
		    
}
