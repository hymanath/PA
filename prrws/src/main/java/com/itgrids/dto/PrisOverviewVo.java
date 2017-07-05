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
	private Long total;
	private Long target;
	private Long achieved;
	private String achievedPercentage;
	
	private List<PrisOverviewVo> voList = new ArrayList<PrisOverviewVo>(0);
	private List<PrisOverviewVo> subList = new ArrayList<PrisOverviewVo>(0);
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
	public String getAchievedPercentage() {
		return achievedPercentage;
	}
	public void setAchievedPercentage(String achievedPercentage) {
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
	
}
