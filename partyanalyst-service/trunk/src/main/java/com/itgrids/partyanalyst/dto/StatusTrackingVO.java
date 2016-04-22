package com.itgrids.partyanalyst.dto;

import java.util.ArrayList;
import java.util.List;

public class StatusTrackingVO {
	
	private Long id;
	private String status;
	private String comments;
	private Long userId;
	private String uname;
	private String date;
	private String time;
	
	
	private boolean current;
	
	
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	public boolean isCurrent() {
		return current;
	}
	public void setCurrent(boolean current) {
		this.current = current;
	}
	private List<StatusTrackingVO> flowList = new ArrayList<StatusTrackingVO>();
	
	
	
	public List<StatusTrackingVO> getFlowList() {
		return flowList;
	}
	public void setFlowList(List<StatusTrackingVO> flowList) {
		this.flowList = flowList;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	
	public String getComments() {
		return comments;
	}
	public void setComments(String comments) {
		this.comments = comments;
	}
	
	public String getUname() {
		return uname;
	}
	public void setUname(String uname) {
		this.uname = uname;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public Long getUserId() {
		return userId;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	
	

}
