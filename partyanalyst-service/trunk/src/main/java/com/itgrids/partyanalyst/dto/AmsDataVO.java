package com.itgrids.partyanalyst.dto;

import java.io.Serializable;
import java.util.List;

//Please Don't add fields without confirmation of App
public class AmsDataVO implements Serializable {

	private Long id;
	private String name;
	
	private List<AmsKeyValueVO> newsPaperList;
	private List<Long> newsPaperIdsList;
	private List<AmsKeyValueVO> chanelList;
	private List<Long> chanelIdsList;
	private List<AmsKeyValueVO> chanelListNew;
	private List<Long> chanelIdsListNew;
	private List<AmsKeyValueVO> deptList;
	private List<Long> deptIdsList;
	private List<AmsKeyValueVO> deptListNew;
	private List<Long> deptIdsListNew;
	
	private List<AmsKeyValueVO> socailMediaTypeList;
	private List<Long> socailMediaTypeIdsList;
	private List<AmsKeyValueVO> alertCallCenterTypeIdList;
	private List<Long> alertCallCenterTypeIdsList;
	private List<AmsKeyValueVO> mondayGrievanceTypeList;
	private List<Long> mondayGrievanceTypeIdsList;
	private List<AmsKeyValueVO> janmabhoomiTypeList;
	private List<Long> janmabhoomiTypeIdsList;
	private List<AmsKeyValueVO> specialGrievanceTypeList;
	private List<Long> specialGrievanceTypeIdsList;
	private List<AmsKeyValueVO> generalGrievanceTypeList;
	private List<Long> generalGrievanceTypeIdsList;
	
	private List<AmsKeyValueVO> alertSeverityList;	
	private List<AmsKeyValueVO> alertStatusList;
	private List<AmsKeyValueVO> govtAlertSubTaksStatusList;
	
	private List<Long> alertSeverityIdsList;
	private List<Long> alertStatusIdsList;
	private List<Long> govtAlertSubTaksStatusIdsList;
	
	private String fromDate = null,toDate = null;
	
	
	public List<Long> getAlertStatusIdsList() {
		return alertStatusIdsList;
	}
	public void setAlertStatusIdsList(List<Long> alertStatusIdsList) {
		this.alertStatusIdsList = alertStatusIdsList;
	}
	public List<Long> getAlertSeverityIdsList() {
		return alertSeverityIdsList;
	}
	public void setAlertSeverityIdsList(List<Long> alertSeverityIdsList) {
		this.alertSeverityIdsList = alertSeverityIdsList;
	}
	public List<Long> getNewsPaperIdsList() {
		return newsPaperIdsList;
	}
	public void setNewsPaperIdsList(List<Long> newsPaperIdsList) {
		this.newsPaperIdsList = newsPaperIdsList;
	}
	public List<Long> getChanelIdsList() {
		return chanelIdsList;
	}
	public void setChanelIdsList(List<Long> chanelIdsList) {
		this.chanelIdsList = chanelIdsList;
	}
	public List<Long> getChanelIdsListNew() {
		return chanelIdsListNew;
	}
	public void setChanelIdsListNew(List<Long> chanelIdsListNew) {
		this.chanelIdsListNew = chanelIdsListNew;
	}
	public List<Long> getDeptIdsList() {
		return deptIdsList;
	}
	public void setDeptIdsList(List<Long> deptIdsList) {
		this.deptIdsList = deptIdsList;
	}
	public List<Long> getDeptIdsListNew() {
		return deptIdsListNew;
	}
	public void setDeptIdsListNew(List<Long> deptIdsListNew) {
		this.deptIdsListNew = deptIdsListNew;
	}
	public List<Long> getSocailMediaTypeIdsList() {
		return socailMediaTypeIdsList;
	}
	public void setSocailMediaTypeIdsList(List<Long> socailMediaTypeIdsList) {
		this.socailMediaTypeIdsList = socailMediaTypeIdsList;
	}
	public List<Long> getAlertCallCenterTypeIdsList() {
		return alertCallCenterTypeIdsList;
	}
	public void setAlertCallCenterTypeIdsList(List<Long> alertCallCenterTypeIdsList) {
		this.alertCallCenterTypeIdsList = alertCallCenterTypeIdsList;
	}
	public List<Long> getMondayGrievanceTypeIdsList() {
		return mondayGrievanceTypeIdsList;
	}
	public void setMondayGrievanceTypeIdsList(List<Long> mondayGrievanceTypeIdsList) {
		this.mondayGrievanceTypeIdsList = mondayGrievanceTypeIdsList;
	}
	public List<Long> getJanmabhoomiTypeIdsList() {
		return janmabhoomiTypeIdsList;
	}
	public void setJanmabhoomiTypeIdsList(List<Long> janmabhoomiTypeIdsList) {
		this.janmabhoomiTypeIdsList = janmabhoomiTypeIdsList;
	}
	public List<Long> getSpecialGrievanceTypeIdsList() {
		return specialGrievanceTypeIdsList;
	}
	public void setSpecialGrievanceTypeIdsList(
			List<Long> specialGrievanceTypeIdsList) {
		this.specialGrievanceTypeIdsList = specialGrievanceTypeIdsList;
	}
	public List<Long> getGeneralGrievanceTypeIdsList() {
		return generalGrievanceTypeIdsList;
	}
	public void setGeneralGrievanceTypeIdsList(
			List<Long> generalGrievanceTypeIdsList) {
		this.generalGrievanceTypeIdsList = generalGrievanceTypeIdsList;
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
	public List<AmsKeyValueVO> getAlertSeverityList() {
		return alertSeverityList;
	}
	public void setAlertSeverityList(List<AmsKeyValueVO> alertSeverityList) {
		this.alertSeverityList = alertSeverityList;
	}
	public List<AmsKeyValueVO> getAlertStatusList() {
		return alertStatusList;
	}
	public void setAlertStatusList(List<AmsKeyValueVO> alertStatusList) {
		this.alertStatusList = alertStatusList;
	}
	public List<AmsKeyValueVO> getGovtAlertSubTaksStatusList() {
		return govtAlertSubTaksStatusList;
	}
	public void setGovtAlertSubTaksStatusList(
			List<AmsKeyValueVO> govtAlertSubTaksStatusList) {
		this.govtAlertSubTaksStatusList = govtAlertSubTaksStatusList;
	}
	public List<Long> getGovtAlertSubTaksStatusIdsList() {
		return govtAlertSubTaksStatusIdsList;
	}
	public void setGovtAlertSubTaksStatusIdsList(
			List<Long> govtAlertSubTaksStatusIdsList) {
		this.govtAlertSubTaksStatusIdsList = govtAlertSubTaksStatusIdsList;
	}
		
	
	
}
