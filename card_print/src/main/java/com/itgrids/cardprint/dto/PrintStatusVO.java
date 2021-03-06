package com.itgrids.cardprint.dto;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

public class PrintStatusVO implements Serializable{
	
	private Long id;
	private String name;
	private Long count = 0l;
	private String status;
	private String districtName;
	private String vendorName;
	private Long maxCount = 0l;
	private Long zebraCount = 0l;
	
	private Map<String,PrintStatusVO> subMap;
	private List<PrintStatusVO> subList;
	
	//constructors
	public PrintStatusVO(){}
	
	public PrintStatusVO(Long id , String name){
		this.id = id;
		this.name = name;
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

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getDistrictName() {
		return districtName;
	}

	public void setDistrictName(String districtName) {
		this.districtName = districtName;
	}

	public Map<String, PrintStatusVO> getSubMap() {
		return subMap;
	}

	public void setSubMap(Map<String, PrintStatusVO> subMap) {
		this.subMap = subMap;
	}

	public List<PrintStatusVO> getSubList() {
		return subList;
	}

	public void setSubList(List<PrintStatusVO> subList) {
		this.subList = subList;
	}

	public Long getMaxCount() {
		return maxCount;
	}

	public void setMaxCount(Long maxCount) {
		this.maxCount = maxCount;
	}

	public Long getZebraCount() {
		return zebraCount;
	}

	public void setZebraCount(Long zebraCount) {
		this.zebraCount = zebraCount;
	}

	public String getVendorName() {
		return vendorName;
	}

	public void setVendorName(String vendorName) {
		this.vendorName = vendorName;
	}
	
}
