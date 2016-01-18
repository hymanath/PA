package com.itgrids.partyanalyst.dto;

import java.util.ArrayList;
import java.util.List;

public class ActivityAttendanceInfoVO implements java.io.Serializable{
	
	private Long id;
	private String name;
	private List<ActivityAttendanceInfoVO> subList = new ArrayList<ActivityAttendanceInfoVO>(0);
	private List<ActivityAttendanceInfoVO> localBodyList= new ArrayList<ActivityAttendanceInfoVO>(0);
	
	private Long totalLocations=0l;
	private Long totalMembers=0l;
	
	private Long totalWebCadreAttendance=0l;
	private Long totalInfoCellCadreAttendance=0l;
	
	private Long totalWebPublicAttendance=0l;
	private Long totalInfoCellPublicAttendance=0l;
	
	private Long totalWebPhotosAttendance=0l;
	private Long totalInfoCellPhotosAttendance=0l;
	
	private String locationType;
	
	
	public String getLocationType() {
		return locationType;
	}
	public void setLocationType(String locationType) {
		this.locationType = locationType;
	}
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
	public List<ActivityAttendanceInfoVO> getSubList() {
		return subList;
	}
	public void setSubList(List<ActivityAttendanceInfoVO> subList) {
		this.subList = subList;
	}
	public List<ActivityAttendanceInfoVO> getLocalBodyList() {
		return localBodyList;
	}
	public void setLocalBodyList(List<ActivityAttendanceInfoVO> localBodyList) {
		this.localBodyList = localBodyList;
	}
	public Long getTotalLocations() {
		return totalLocations;
	}
	public void setTotalLocations(Long totalLocations) {
		this.totalLocations = totalLocations;
	}
	public Long getTotalMembers() {
		return totalMembers;
	}
	public void setTotalMembers(Long totalMembers) {
		this.totalMembers = totalMembers;
	}
	public Long getTotalWebCadreAttendance() {
		return totalWebCadreAttendance;
	}
	public void setTotalWebCadreAttendance(Long totalWebCadreAttendance) {
		this.totalWebCadreAttendance = totalWebCadreAttendance;
	}
	public Long getTotalInfoCellCadreAttendance() {
		return totalInfoCellCadreAttendance;
	}
	public void setTotalInfoCellCadreAttendance(Long totalInfoCellCadreAttendance) {
		this.totalInfoCellCadreAttendance = totalInfoCellCadreAttendance;
	}
	public Long getTotalWebPublicAttendance() {
		return totalWebPublicAttendance;
	}
	public void setTotalWebPublicAttendance(Long totalWebPublicAttendance) {
		this.totalWebPublicAttendance = totalWebPublicAttendance;
	}
	public Long getTotalInfoCellPublicAttendance() {
		return totalInfoCellPublicAttendance;
	}
	public void setTotalInfoCellPublicAttendance(Long totalInfoCellPublicAttendance) {
		this.totalInfoCellPublicAttendance = totalInfoCellPublicAttendance;
	}
	public Long getTotalWebPhotosAttendance() {
		return totalWebPhotosAttendance;
	}
	public void setTotalWebPhotosAttendance(Long totalWebPhotosAttendance) {
		this.totalWebPhotosAttendance = totalWebPhotosAttendance;
	}
	public Long getTotalInfoCellPhotosAttendance() {
		return totalInfoCellPhotosAttendance;
	}
	public void setTotalInfoCellPhotosAttendance(Long totalInfoCellPhotosAttendance) {
		this.totalInfoCellPhotosAttendance = totalInfoCellPhotosAttendance;
	}
	
	
	
	
	
 
}
