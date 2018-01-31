package com.itgrids.dto;

import java.util.ArrayList;
import java.util.List;

public class NTRSujalaRduVO {
	
	private Long id;
	private String name;
	private Long waterTankCapacity = 0L;
	private String date;
	private String rduHealthStatus;
	private Long oldCustomers = 0L;
	private Long newCustomers = 0L;
	private Long totalCustomers = 0L;
	private Long waterDispence = 0L;
	private List<NTRSujalaRduVO> subList = new ArrayList<NTRSujalaRduVO>(0);
	private String waterDispenceSeparation;
	
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
	public Long getWaterTankCapacity() {
		return waterTankCapacity;
	}
	public void setWaterTankCapacity(Long waterTankCapacity) {
		this.waterTankCapacity = waterTankCapacity;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public String getRduHealthStatus() {
		return rduHealthStatus;
	}
	public void setRduHealthStatus(String rduHealthStatus) {
		this.rduHealthStatus = rduHealthStatus;
	}
	public Long getOldCustomers() {
		return oldCustomers;
	}
	public void setOldCustomers(Long oldCustomers) {
		this.oldCustomers = oldCustomers;
	}
	public Long getNewCustomers() {
		return newCustomers;
	}
	public void setNewCustomers(Long newCustomers) {
		this.newCustomers = newCustomers;
	}
	public Long getTotalCustomers() {
		return totalCustomers;
	}
	public void setTotalCustomers(Long totalCustomers) {
		this.totalCustomers = totalCustomers;
	}
	public Long getWaterDispence() {
		return waterDispence;
	}
	public void setWaterDispence(Long waterDispence) {
		this.waterDispence = waterDispence;
	}
	public List<NTRSujalaRduVO> getSubList() {
		return subList;
	}
	public void setSubList(List<NTRSujalaRduVO> subList) {
		this.subList = subList;
	}
	public String getWaterDispenceSeparation() {
		return waterDispenceSeparation;
	}
	public void setWaterDispenceSeparation(String waterDispenceSeparation) {
		this.waterDispenceSeparation = waterDispenceSeparation;
	}
}
