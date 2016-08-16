package com.itgrids.partyanalyst.dto;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Administrator
 *
 */
public class GovtOrderVO{
	private Long govtOrderId;
	private String positionIdsString="";
	private String goName;
	private String goCode;
	private String fromDate;
	private String toDate;
	private String remarks="";
	private Long locationLevelId;
	private String locationLevelValuesStr="";
	private Long departmentId;
	private Long boardId;
	
	
	public Long getDepartmentId() {
		return departmentId;
	}
	public void setDepartmentId(Long departmentId) {
		this.departmentId = departmentId;
	}
	public Long getBoardId() {
		return boardId;
	}
	public void setBoardId(Long boardId) {
		this.boardId = boardId;
	}
	public String getLocationLevelValuesStr() {
		return locationLevelValuesStr;
	}
	public void setLocationLevelValuesStr(String locationLevelValuesStr) {
		this.locationLevelValuesStr = locationLevelValuesStr;
	}
	public Long getLocationLevelId() {
		return locationLevelId;
	}
	public void setLocationLevelId(Long locationLevelId) {
		this.locationLevelId = locationLevelId;
	}
	public Long getGovtOrderId() {
		return govtOrderId;
	}
	public void setGovtOrderId(Long govtOrderId) {
		this.govtOrderId = govtOrderId;
	}
	public String getPositionIdsString() {
		return positionIdsString;
	}
	public void setPositionIdsString(String positionIdsString) {
		this.positionIdsString = positionIdsString;
	}
	public String getGoName() {
		return goName;
	}
	public void setGoName(String goName) {
		this.goName = goName;
	}
	public String getGoCode() {
		return goCode;
	}
	public void setGoCode(String goCode) {
		this.goCode = goCode;
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
	public String getRemarks() {
		return remarks;
	}
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
}
