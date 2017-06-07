package com.itgrids.partyanalyst.dto;

import java.io.Serializable;
import java.util.List;

//Please Don't add fields without confirmation of App
public class AmsDataVO implements Serializable {

	private Long id;
	private String name;
	
	private List<AmsKeyValueVO> newsPaperList;
	private List<AmsKeyValueVO> chanelList;
	private List<AmsKeyValueVO> chanelListNew;
	private List<AmsKeyValueVO> deptList;
	private List<AmsKeyValueVO> deptListNew;
	
	private List<AmsKeyValueVO> socailMediaTypeList;
	private List<AmsKeyValueVO> alertCallCenterTypeIdList;
	private List<AmsKeyValueVO> mondayGrievanceTypeList;
	private List<AmsKeyValueVO> janmabhoomiTypeList;
	private List<AmsKeyValueVO> specialGrievanceTypeList;
	private List<AmsKeyValueVO> generalGrievanceTypeList;
	
	
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
	public List<AmsKeyValueVO> getNewsPaperList() {
		return newsPaperList;
	}
	public void setNewsPaperList(List<AmsKeyValueVO> newsPaperList) {
		this.newsPaperList = newsPaperList;
	}
	public List<AmsKeyValueVO> getChanelList() {
		return chanelList;
	}
	public void setChanelList(List<AmsKeyValueVO> chanelList) {
		this.chanelList = chanelList;
	}
	public List<AmsKeyValueVO> getChanelListNew() {
		return chanelListNew;
	}
	public void setChanelListNew(List<AmsKeyValueVO> chanelListNew) {
		this.chanelListNew = chanelListNew;
	}
	public List<AmsKeyValueVO> getDeptList() {
		return deptList;
	}
	public void setDeptList(List<AmsKeyValueVO> deptList) {
		this.deptList = deptList;
	}
	public List<AmsKeyValueVO> getDeptListNew() {
		return deptListNew;
	}
	public void setDeptListNew(List<AmsKeyValueVO> deptListNew) {
		this.deptListNew = deptListNew;
	}
	public List<AmsKeyValueVO> getSocailMediaTypeList() {
		return socailMediaTypeList;
	}
	public void setSocailMediaTypeList(List<AmsKeyValueVO> socailMediaTypeList) {
		this.socailMediaTypeList = socailMediaTypeList;
	}
	public List<AmsKeyValueVO> getAlertCallCenterTypeIdList() {
		return alertCallCenterTypeIdList;
	}
	public void setAlertCallCenterTypeIdList(
			List<AmsKeyValueVO> alertCallCenterTypeIdList) {
		this.alertCallCenterTypeIdList = alertCallCenterTypeIdList;
	}
	public List<AmsKeyValueVO> getMondayGrievanceTypeList() {
		return mondayGrievanceTypeList;
	}
	public void setMondayGrievanceTypeList(
			List<AmsKeyValueVO> mondayGrievanceTypeList) {
		this.mondayGrievanceTypeList = mondayGrievanceTypeList;
	}
	public List<AmsKeyValueVO> getJanmabhoomiTypeList() {
		return janmabhoomiTypeList;
	}
	public void setJanmabhoomiTypeList(List<AmsKeyValueVO> janmabhoomiTypeList) {
		this.janmabhoomiTypeList = janmabhoomiTypeList;
	}
	public List<AmsKeyValueVO> getSpecialGrievanceTypeList() {
		return specialGrievanceTypeList;
	}
	public void setSpecialGrievanceTypeList(
			List<AmsKeyValueVO> specialGrievanceTypeList) {
		this.specialGrievanceTypeList = specialGrievanceTypeList;
	}
	public List<AmsKeyValueVO> getGeneralGrievanceTypeList() {
		return generalGrievanceTypeList;
	}
	public void setGeneralGrievanceTypeList(
			List<AmsKeyValueVO> generalGrievanceTypeList) {
		this.generalGrievanceTypeList = generalGrievanceTypeList;
	}
		
	
	
}
