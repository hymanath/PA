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
	
	private List<PrisOverviewVo> distList = new ArrayList<PrisOverviewVo>(0);
	private List<PrisOverviewVo> consList = new ArrayList<PrisOverviewVo>(0);
	private List<PrisOverviewVo> distOverviewList = new ArrayList<PrisOverviewVo>(0);
	private List<PrisOverviewVo> consOverviewList = new ArrayList<PrisOverviewVo>(0);
	private List<PrisOverviewVo> mandalOverviewList = new ArrayList<PrisOverviewVo>(0);
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
	public List<PrisOverviewVo> getDistList() {
		return distList;
	}
	public void setDistList(List<PrisOverviewVo> distList) {
		this.distList = distList;
	}
	public List<PrisOverviewVo> getConsList() {
		return consList;
	}
	public void setConsList(List<PrisOverviewVo> consList) {
		this.consList = consList;
	}
	public List<PrisOverviewVo> getDistOverviewList() {
		return distOverviewList;
	}
	public void setDistOverviewList(List<PrisOverviewVo> distOverviewList) {
		this.distOverviewList = distOverviewList;
	}
	public List<PrisOverviewVo> getConsOverviewList() {
		return consOverviewList;
	}
	public void setConsOverviewList(List<PrisOverviewVo> consOverviewList) {
		this.consOverviewList = consOverviewList;
	}
	public List<PrisOverviewVo> getMandalOverviewList() {
		return mandalOverviewList;
	}
	public void setMandalOverviewList(List<PrisOverviewVo> mandalOverviewList) {
		this.mandalOverviewList = mandalOverviewList;
	}
}
