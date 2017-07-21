package com.itgrids.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class NregaConsolidatedDataVO implements Serializable{

	private Long id;
	private String name;
	private String district;
	private String constituency;
	private String mandal;
	private String panchayat;
	
	private String component;
	private String percentage;
	private List<NregaConsolidatedDataVO> subList = new ArrayList<NregaConsolidatedDataVO>(0);
	
	
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
	public String getDistrict() {
		return district;
	}
	public void setDistrict(String district) {
		this.district = district;
	}
	public String getConstituency() {
		return constituency;
	}
	public void setConstituency(String constituency) {
		this.constituency = constituency;
	}
	public String getMandal() {
		return mandal;
	}
	public void setMandal(String mandal) {
		this.mandal = mandal;
	}
	public String getPanchayat() {
		return panchayat;
	}
	public void setPanchayat(String panchayat) {
		this.panchayat = panchayat;
	}
	public String getComponent() {
		return component;
	}
	public void setComponent(String component) {
		this.component = component;
	}
	public String getPercentage() {
		return percentage;
	}
	public void setPercentage(String percentage) {
		this.percentage = percentage;
	}
	public List<NregaConsolidatedDataVO> getSubList() {
		return subList;
	}
	public void setSubList(List<NregaConsolidatedDataVO> subList) {
		this.subList = subList;
	}
}
