package com.itgrids.partyanalyst.dto;

import java.io.Serializable;
import java.util.Date;

public class AnnouncementInfo implements Serializable {
	private long announcementId;
	private String title;
	private String message;
	private String fromdate;
	private String todate;
	private long constituency;
	private long state;
	
	
	public long getConstituency() {
		return constituency;
	}
	public void setConstituency(long constituency) {
		this.constituency = constituency;
	}
	public long getState() {
		return state;
	}
	public void setState(long state) {
		this.state = state;
	}
	public long getAnnouncementId() {
		return announcementId;
	}
	public void setAnnouncementId(long announcementId) {
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
	public String getFromdate() {
		return fromdate;
	}
	public void setFromdate(String fromdate) {
		this.fromdate = fromdate;
	}
	public String getTodate() {
		return todate;
	}
	public void setTodate(String todate) {
		this.todate = todate;
	}
	

}
