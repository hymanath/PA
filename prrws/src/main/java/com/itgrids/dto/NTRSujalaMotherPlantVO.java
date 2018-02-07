package com.itgrids.dto;

import java.util.ArrayList;
import java.util.List;

public class NTRSujalaMotherPlantVO {
	
	private Long id;
	private String name;
	private Long motherPlantCount = 0L;
	private String plantHealthStatus;
	private String waterQuanStatus;
	private Long tdsCount;
	private Long phCount;
	private String highDispanceRDUName;
	private Long highDispanceRDUCount;
	private String lowDispanceRDUName;
	private Long lowDispanceRDUCount;
	private Long waterGenerated;
	private Long customers;
	private Long rduCount;
	private String date;
	private String mpWaterDispenced;
	private List<NTRSujalaMotherPlantVO> subList = new ArrayList<NTRSujalaMotherPlantVO>(0);
	
	
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
	public Long getMotherPlantCount() {
		return motherPlantCount;
	}
	public void setMotherPlantCount(Long motherPlantCount) {
		this.motherPlantCount = motherPlantCount;
	}
	public String getPlantHealthStatus() {
		return plantHealthStatus;
	}
	public void setPlantHealthStatus(String plantHealthStatus) {
		this.plantHealthStatus = plantHealthStatus;
	}
	public String getWaterQuanStatus() {
		return waterQuanStatus;
	}
	public void setWaterQuanStatus(String waterQuanStatus) {
		this.waterQuanStatus = waterQuanStatus;
	}
	public Long getTdsCount() {
		return tdsCount;
	}
	public void setTdsCount(Long tdsCount) {
		this.tdsCount = tdsCount;
	}
	public Long getPhCount() {
		return phCount;
	}
	public void setPhCount(Long phCount) {
		this.phCount = phCount;
	}
	public String getHighDispanceRDUName() {
		return highDispanceRDUName;
	}
	public void setHighDispanceRDUName(String highDispanceRDUName) {
		this.highDispanceRDUName = highDispanceRDUName;
	}
	public Long getHighDispanceRDUCount() {
		return highDispanceRDUCount;
	}
	public void setHighDispanceRDUCount(Long highDispanceRDUCount) {
		this.highDispanceRDUCount = highDispanceRDUCount;
	}
	public String getLowDispanceRDUName() {
		return lowDispanceRDUName;
	}
	public void setLowDispanceRDUName(String lowDispanceRDUName) {
		this.lowDispanceRDUName = lowDispanceRDUName;
	}
	public Long getLowDispanceRDUCount() {
		return lowDispanceRDUCount;
	}
	public void setLowDispanceRDUCount(Long lowDispanceRDUCount) {
		this.lowDispanceRDUCount = lowDispanceRDUCount;
	}
	public Long getWaterGenerated() {
		return waterGenerated;
	}
	public void setWaterGenerated(Long waterGenerated) {
		this.waterGenerated = waterGenerated;
	}
	public Long getCustomers() {
		return customers;
	}
	public void setCustomers(Long customers) {
		this.customers = customers;
	}
	public Long getRduCount() {
		return rduCount;
	}
	public void setRduCount(Long rduCount) {
		this.rduCount = rduCount;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public List<NTRSujalaMotherPlantVO> getSubList() {
		return subList;
	}
	public void setSubList(List<NTRSujalaMotherPlantVO> subList) {
		this.subList = subList;
	}
	public String getMpWaterDispenced() {
		return mpWaterDispenced;
	}
	public void setMpWaterDispenced(String mpWaterDispenced) {
		this.mpWaterDispenced = mpWaterDispenced;
	}
}
