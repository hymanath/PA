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
	private List<IdNameVO> subList;
	private List<String> commentsList;
	private boolean current;
	private Long fromStatusId;
	private String fromStatus;
	
	private Long actionId;
	private String actionStatus;
	private Long count;
	
	
	public Long getCount() {
		return count;
	}
	public void setCount(Long count) {
		this.count = count;
	}
	public Long getActionId() {
		return actionId;
	}
	public void setActionId(Long actionId) {
		this.actionId = actionId;
	}
	public String getActionStatus() {
		return actionStatus;
	}
	public void setActionStatus(String actionStatus) {
		this.actionStatus = actionStatus;
	}
	
	public Long getFromStatusId() {
		return fromStatusId;
	}
	public void setFromStatusId(Long fromStatusId) {
		this.fromStatusId = fromStatusId;
	}
	public String getFromStatus() {
		return fromStatus;
	}
	public void setFromStatus(String fromStatus) {
		this.fromStatus = fromStatus;
	}
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
	
	public List<IdNameVO> getSubList() {
		return subList;
	}
	public void setSubList(List<IdNameVO> subList) {
		this.subList = subList;
	}
	public List<String> getCommentsList() {
		return commentsList;
	}
	public void setCommentsList(List<String> commentsList) {
		this.commentsList = commentsList;
	}
	
	
	

}
