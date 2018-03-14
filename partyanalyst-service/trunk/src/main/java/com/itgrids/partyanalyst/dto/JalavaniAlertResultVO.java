package com.itgrids.partyanalyst.dto;

import java.util.ArrayList;
import java.util.List;


public class JalavaniAlertResultVO {
	public Long districtId,constituenctId,mandalId,statusId,count=0l;
	public String districtName,constiruenctName,mandalName,status,color;
	public List<JalavaniAlertResultVO> voList = new ArrayList<JalavaniAlertResultVO>(0);
	public Double percentage;
	
	
	public Long getDistrictId() {
		return districtId;
	}
	public void setDistrictId(Long districtId) {
		this.districtId = districtId;
	}
	public Long getConstituenctId() {
		return constituenctId;
	}
	public void setConstituenctId(Long constituenctId) {
		this.constituenctId = constituenctId;
	}
	public Long getMandalId() {
		return mandalId;
	}
	public void setMandalId(Long mandalId) {
		this.mandalId = mandalId;
	}
	public Long getStatusId() {
		return statusId;
	}
	public void setStatusId(Long statusId) {
		this.statusId = statusId;
	}
	public Long getCount() {
		return count;
	}
	public void setCount(Long count) {
		this.count = count;
	}
	public String getDistrictName() {
		return districtName;
	}
	public void setDistrictName(String districtName) {
		this.districtName = districtName;
	}
	public String getConstiruenctName() {
		return constiruenctName;
	}
	public void setConstiruenctName(String constiruenctName) {
		this.constiruenctName = constiruenctName;
	}
	public String getMandalName() {
		return mandalName;
	}
	public void setMandalName(String mandalName) {
		this.mandalName = mandalName;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public List<JalavaniAlertResultVO> getVoList() {
		return voList;
	}
	public void setVoList(List<JalavaniAlertResultVO> voList) {
		this.voList = voList;
	}
	public String getColor() {
		return color;
	}
	public void setColor(String color) {
		this.color = color;
	}
	public Double getPercentage() {
		return percentage;
	}
	public void setPercentage(Double percentage) {
		this.percentage = percentage;
	}
	
}
