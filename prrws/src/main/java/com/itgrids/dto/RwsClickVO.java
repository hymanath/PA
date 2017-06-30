package com.itgrids.dto;

import java.io.Serializable;

public class RwsClickVO implements Serializable{

	private Long id;
	private String name;
	
	private String mandalName;
	private String constituencyName;
	private Long districtCode;
	private String districtName;
	private Long sacntionedAmount;
	private String workName;
	private Long workId;
	private Long sourceCount;
	private Long constituencyCode;
	private Long mandalCode;
	private String habitationName;
	private String completionDate;
	private String habitationCode;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getMandalName() {
		return mandalName;
	}
	public void setMandalName(String mandalName) {
		this.mandalName = mandalName;
	}
	
	public String getConstituencyName() {
		return constituencyName;
	}
	public void setConstituencyName(String constituencyName) {
		this.constituencyName = constituencyName;
	}
	public Long getDistrictCode() {
		return districtCode;
	}
	public void setDistrictCode(Long districtCode) {
		this.districtCode = districtCode;
	}
	public String getDistrictName() {
		return districtName;
	}
	public void setDistrictName(String districtName) {
		this.districtName = districtName;
	}
	public Long getSacntionedAmount() {
		return sacntionedAmount;
	}
	public void setSacntionedAmount(Long sacntionedAmount) {
		this.sacntionedAmount = sacntionedAmount;
	}
	public String getWorkName() {
		return workName;
	}
	public void setWorkName(String workName) {
		this.workName = workName;
	}
	public Long getWorkId() {
		return workId;
	}
	public void setWorkId(Long workId) {
		this.workId = workId;
	}
	public Long getSourceCount() {
		return sourceCount;
	}
	public void setSourceCount(Long sourceCount) {
		this.sourceCount = sourceCount;
	}
	public Long getConstituencyCode() {
		return constituencyCode;
	}
	public void setConstituencyCode(Long constituencyCode) {
		this.constituencyCode = constituencyCode;
	}
	public Long getMandalCode() {
		return mandalCode;
	}
	public void setMandalCode(Long mandalCode) {
		this.mandalCode = mandalCode;
	}
	public String getHabitationName() {
		return habitationName;
	}
	public void setHabitationName(String habitationName) {
		this.habitationName = habitationName;
	}
	public String getCompletionDate() {
		return completionDate;
	}
	public void setCompletionDate(String completionDate) {
		this.completionDate = completionDate;
	}
	public String getHabitationCode() {
		return habitationCode;
	}
	public void setHabitationCode(String habitationCode) {
		this.habitationCode = habitationCode;
	}
	
	
	
	
}
