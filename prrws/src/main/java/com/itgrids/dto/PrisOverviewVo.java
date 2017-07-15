package com.itgrids.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;


	/*
	 * Date : 07/04/2017
	 * Author :Teja
	 * @description : PrisDataVo Vo Class
	 */
public class PrisOverviewVo implements Serializable{
	
	private Long id;
	private String name;
	private Long total =0l;
	private Long target =0l;
	private Long achieved =0l;
	private Double achievedPercentage=0.00;
	
	private List<PrisOverviewVo> voList = new ArrayList<PrisOverviewVo>(0);
	private List<PrisOverviewVo> subList = new ArrayList<PrisOverviewVo>(0);
	private Long districtId;
	private String districtName;
	private Long constituencyId;
	private String constituencyName;
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
	public Long getTotal() {
		return total;
	}
	public void setTotal(Long total) {
		this.total = total;
	}
	public Long getTarget() {
		return target;
	}
	public void setTarget(Long target) {
		this.target = target;
	}
	public Long getAchieved() {
		return achieved;
	}
	public void setAchieved(Long achieved) {
		this.achieved = achieved;
	}
	public Double getAchievedPercentage() {
		return achievedPercentage;
	}
	public void setAchievedPercentage(Double achievedPercentage) {
		this.achievedPercentage = achievedPercentage;
	}
	public List<PrisOverviewVo> getVoList() {
		return voList;
	}
	public void setVoList(List<PrisOverviewVo> voList) {
		this.voList = voList;
	}
	public List<PrisOverviewVo> getSubList() {
		return subList;
	}
	public void setSubList(List<PrisOverviewVo> subList) {
		this.subList = subList;
	}
	public Long getDistrictId() {
		return districtId;
	}
	public void setDistrictId(Long districtId) {
		this.districtId = districtId;
	}
	public String getDistrictName() {
		return districtName;
	}
	public void setDistrictName(String districtName) {
		this.districtName = districtName;
	}
	public Long getConstituencyId() {
		return constituencyId;
	}
	public void setConstituencyId(Long constituencyId) {
		this.constituencyId = constituencyId;
	}
	public String getConstituencyName() {
		return constituencyName;
	}
	public void setConstituencyName(String constituencyName) {
		this.constituencyName = constituencyName;
	}
	
}
