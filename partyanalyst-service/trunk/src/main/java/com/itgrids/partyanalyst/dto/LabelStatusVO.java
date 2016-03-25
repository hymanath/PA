package com.itgrids.partyanalyst.dto;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.itgrids.partyanalyst.model.AppointmentStatus;

public class LabelStatusVO {
	
	private Long labelId;
	private String labelName;
	private String Status;
	private Long StatusId;
	private Date dateTime;
	
	private Long totalCount=0l;
	private List<LabelStatusVO> statusList = new ArrayList<LabelStatusVO>(0);
	private List<LabelStatusVO> overAllStatusList = new ArrayList<LabelStatusVO>(0);
	private List<AppointmentStatus> staticStatusList = new ArrayList<AppointmentStatus>(0);
	
	
	
	public Long getLabelId() {
		return labelId;
	}
	public void setLabelId(Long labelId) {
		this.labelId = labelId;
	}
	public String getLabelName() {
		return labelName;
	}
	public void setLabelName(String labelName) {
		this.labelName = labelName;
	}
	public String getStatus() {
		return Status;
	}
	public void setStatus(String status) {
		Status = status;
	}
	public Long getStatusId() {
		return StatusId;
	}
	public void setStatusId(Long statusId) {
		StatusId = statusId;
	}
	public Long getTotalCount() {
		return totalCount;
	}
	public void setTotalCount(Long totalCount) {
		this.totalCount = totalCount;
	}
	public List<LabelStatusVO> getStatusList() {
		return statusList;
	}
	public void setStatusList(List<LabelStatusVO> statusList) {
		this.statusList = statusList;
	}
	public List<AppointmentStatus> getStaticStatusList() {
		return staticStatusList;
	}
	public void setStaticStatusList(List<AppointmentStatus> staticStatusList) {
		this.staticStatusList = staticStatusList;
	}
	public List<LabelStatusVO> getOverAllStatusList() {
		return overAllStatusList;
	}
	public void setOverAllStatusList(List<LabelStatusVO> overAllStatusList) {
		this.overAllStatusList = overAllStatusList;
	}
	public Date getDateTime() {
		return dateTime;
	}
	public void setDateTime(Date dateTime) {
		this.dateTime = dateTime;
	}
	
	
}
