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
	private Long count;
	private Long overAllCnt ;
	private Long designationId;
	private String desigName;
	private Long departmentId;
	private String deptName;
	private Double perc;
	private List<DistrictOfficeViewAlertVO> list1 = new ArrayList<DistrictOfficeViewAlertVO>(0);
	private List<DistrictOfficeViewAlertVO>  list2= new ArrayList<DistrictOfficeViewAlertVO>(0);
	private List<DistrictOfficeViewAlertVO> list3 = new ArrayList<DistrictOfficeViewAlertVO>(0);
	
	
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
	
	
}
