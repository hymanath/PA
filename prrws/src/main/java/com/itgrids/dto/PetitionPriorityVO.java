package com.itgrids.dto;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class PetitionPriorityVO implements java.io.Serializable{

	private Long workId;
	private Long petitionId;
	private Long priority;
	private Long userId;
	private Date dateTime;
	private List<PetitionPriorityVO> worksList = new ArrayList<PetitionPriorityVO>(0);
	
	public Date getDateTime() {
		return dateTime;
	}
	public void setDateTime(Date dateTime) {
		this.dateTime = dateTime;
	}
	public Long getUserId() {
		return userId;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	public List<PetitionPriorityVO> getWorksList() {
		return worksList;
	}
	public void setWorksList(List<PetitionPriorityVO> worksList) {
		this.worksList = worksList;
	}
	public Long getWorkId() {
		return workId;
	}
	public void setWorkId(Long workId) {
		this.workId = workId;
	}
	public Long getPetitionId() {
		return petitionId;
	}
	public void setPetitionId(Long petitionId) {
		this.petitionId = petitionId;
	}
	public Long getPriority() {
		return priority;
	}
	public void setPriority(Long priority) {
		this.priority = priority;
	}
}
