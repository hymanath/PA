package com.itgrids.partyanalyst.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.itgrids.partyanalyst.model.BaseModel;

public class DistrictOfficeViewAlertVO extends BaseModel implements Serializable{

	private Long id;
	private String name;
	private Long count = 0l;
	private Long completedCnt = 0l;
	private Long taskCnt = 0l;
	private Long taskCompletedCnt = 0l;
	private Long overAllCnt = 0l;
	private Long designationId;
	private String desigName;
	private Long departmentId;
	private String deptName;
	private Long todayCount;
	private Double perc;
	private List<DistrictOfficeViewAlertVO> list1 = new ArrayList<DistrictOfficeViewAlertVO>(0);
	private List<DistrictOfficeViewAlertVO>  list2= new ArrayList<DistrictOfficeViewAlertVO>(0);
	private List<DistrictOfficeViewAlertVO> list3 = new ArrayList<DistrictOfficeViewAlertVO>(0);
	private Long govtDeptDesigOffcrId;
	private Long govtOfficerId;
	private Double alertsPerc;
	private String color;
	private List<Long> todayAlertIds = new ArrayList<Long>();
	private List<Long> overAllAlertIds = new ArrayList<Long>();
	private List<Long> levelValues =new ArrayList<Long>();
	private Long levelId;
	private List<DistrictOfficeViewAlertVO> subList1 = new ArrayList<DistrictOfficeViewAlertVO>(0);
	private List<DistrictOfficeViewAlertVO>  subList2= new ArrayList<DistrictOfficeViewAlertVO>(0);
	private List<DistrictOfficeViewAlertVO> subList3 = new ArrayList<DistrictOfficeViewAlertVO>(0);
	
	
	
	public List<DistrictOfficeViewAlertVO> getSubList1() {
		return subList1;
	}
	public void setSubList1(List<DistrictOfficeViewAlertVO> subList1) {
		this.subList1 = subList1;
	}
	public List<DistrictOfficeViewAlertVO> getSubList2() {
		return subList2;
	}
	public void setSubList2(List<DistrictOfficeViewAlertVO> subList2) {
		this.subList2 = subList2;
	}
	public List<DistrictOfficeViewAlertVO> getSubList3() {
		return subList3;
	}
	public void setSubList3(List<DistrictOfficeViewAlertVO> subList3) {
		this.subList3 = subList3;
	}
	public List<Long> getTodayAlertIds() {
		return todayAlertIds;
	}
	public void setTodayAlertIds(List<Long> todayAlertIds) {
		this.todayAlertIds = todayAlertIds;
	}
	public List<Long> getOverAllAlertIds() {
		return overAllAlertIds;
	}
	public void setOverAllAlertIds(List<Long> overAllAlertIds) {
		this.overAllAlertIds = overAllAlertIds;
	}
	public String getColor() {
		return color;
	}
	public void setColor(String color) {
		this.color = color;
	}
	public Double getAlertsPerc() {
		return alertsPerc;
	}
	public void setAlertsPerc(Double alertsPerc) {
		this.alertsPerc = alertsPerc;
	}
	public Long getCompletedCnt() {
		return completedCnt;
	}
	public void setCompletedCnt(Long completedCnt) {
		this.completedCnt = completedCnt;
	}
	public Long getTaskCnt() {
		return taskCnt;
	}
	public void setTaskCnt(Long taskCnt) {
		this.taskCnt = taskCnt;
	}
	public Long getTaskCompletedCnt() {
		return taskCompletedCnt;
	}
	public void setTaskCompletedCnt(Long taskCompletedCnt) {
		this.taskCompletedCnt = taskCompletedCnt;
	}
	public Long getGovtDeptDesigOffcrId() {
		return govtDeptDesigOffcrId;
	}
	public void setGovtDeptDesigOffcrId(Long govtDeptDesigOffcrId) {
		this.govtDeptDesigOffcrId = govtDeptDesigOffcrId;
	}
	public Long getGovtOfficerId() {
		return govtOfficerId;
	}
	public void setGovtOfficerId(Long govtOfficerId) {
		this.govtOfficerId = govtOfficerId;
	}
	public Long getTodayCount() {
		return todayCount;
	}
	public void setTodayCount(Long todayCount) {
		this.todayCount = todayCount;
	}
	public Double getPerc() {
		return perc;
	}
	public void setPerc(Double perc) {
		this.perc = perc;
	}
	public Long getOverAllCnt() {
		return overAllCnt;
	}
	public void setOverAllCnt(Long overAllCnt) {
		this.overAllCnt = overAllCnt;
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
	public Long getCount() {
		return count;
	}
	public void setCount(Long count) {
		this.count = count;
	}
	public List<DistrictOfficeViewAlertVO> getList1() {
		return list1;
	}
	public void setList1(List<DistrictOfficeViewAlertVO> list1) {
		this.list1 = list1;
	}
	public List<DistrictOfficeViewAlertVO> getList2() {
		return list2;
	}
	public void setList2(List<DistrictOfficeViewAlertVO> list2) {
		this.list2 = list2;
	}
	public List<DistrictOfficeViewAlertVO> getList3() {
		return list3;
	}
	public void setList3(List<DistrictOfficeViewAlertVO> list3) {
		this.list3 = list3;
	}
	public Long getDesignationId() {
		return designationId;
	}
	public void setDesignationId(Long designationId) {
		this.designationId = designationId;
	}
	public String getDesigName() {
		return desigName;
	}
	public void setDesigName(String desigName) {
		this.desigName = desigName;
	}
	public Long getDepartmentId() {
		return departmentId;
	}
	public void setDepartmentId(Long departmentId) {
		this.departmentId = departmentId;
	}
	public String getDeptName() {
		return deptName;
	}
	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}
	public List<Long> getLevelValues() {
		return levelValues;
	}
	public void setLevelValues(List<Long> levelValues) {
		this.levelValues = levelValues;
	}
	public Long getLevelId() {
		return levelId;
	}
	public void setLevelId(Long levelId) {
		this.levelId = levelId;
	}
	
	
}
