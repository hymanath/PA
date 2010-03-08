package com.itgrids.partyanalyst.dto;

import java.io.Serializable;

public class ProblemHistoryVO implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Long problemHistoryId;
	private String comments;
	private String movedDate;
	private String isDelete;
	private String status;
	
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getComments() {
		return comments;
	}
	public void setComments(String comments) {
		this.comments = comments;
	}
	public String getMovedDate() {
		return movedDate;
	}
	public void setMovedDate(String movedDate) {
		this.movedDate = movedDate;
	}
	public String getIsDelete() {
		return isDelete;
	}
	public void setIsDelete(String isDelete) {
		this.isDelete = isDelete;
	}
	public Long getProblemHistoryId() {
		return problemHistoryId;
	}
	public void setProblemHistoryId(Long problemHistoryId) {
		this.problemHistoryId = problemHistoryId;
	}
	
}
