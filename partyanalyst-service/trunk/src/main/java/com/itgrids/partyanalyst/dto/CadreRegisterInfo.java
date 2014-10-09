package com.itgrids.partyanalyst.dto;

import java.io.Serializable;
import java.util.List;

public class CadreRegisterInfo implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -2883187909007470353L;

	private Long totalCount;
	private Long apCount;
	private Long tgCount;
	private String name;
	private String area;
	private String location;
	private Long percentage;
	private String date;
	private List<CadreRegisterInfo> infoList;
	
	public Long getTotalCount() {
		return totalCount;
	}
	
	public void setTotalCount(Long totalCount) {
		this.totalCount = totalCount;
	}
	
	public Long getApCount() {
		return apCount;
	}
	
	public void setApCount(Long apCount) {
		this.apCount = apCount;
	}
	
	public Long getTgCount() {
		return tgCount;
	}
	
	public void setTgCount(Long tgCount) {
		this.tgCount = tgCount;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getArea() {
		return area;
	}

	public void setArea(String area) {
		this.area = area;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public Long getPercentage() {
		return percentage;
	}

	public void setPercentage(Long percentage) {
		this.percentage = percentage;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public List<CadreRegisterInfo> getInfoList() {
		return infoList;
	}

	public void setInfoList(List<CadreRegisterInfo> infoList) {
		this.infoList = infoList;
	}	
	
}
