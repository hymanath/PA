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
	private Double achievedPercentage=0.00;
	
	private List<PrisOverviewVo> subList = new ArrayList<PrisOverviewVo>(0);
	private Long districtId;
	private String districtName;
	private Long constituencyId;
	private String constituencyName;
	private String districtStarted;
	private String consStarted;
	private String consNotStarted;
	private String consCompleted;
	private String mandalStarted;
	private String mandalNotStarted;
	private String mandalCompleted;
	private String panchayatStarted;
	private String panchayatNotStarted;
	private String panchayatCompleted;
	private String districtNotStarted;
	private String districtCompleted;
	private String parliamentStarted;
	private String parliamentNotStarted;
	private String parliamentCompleted;
	
	
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
	public Double getAchievedPercentage() {
		return achievedPercentage;
	}
	public void setAchievedPercentage(Double achievedPercentage) {
		this.achievedPercentage = achievedPercentage;
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
	public String getDistrictStarted() {
		return districtStarted;
	}
	public void setDistrictStarted(String districtStarted) {
		this.districtStarted = districtStarted;
	}
	public String getConsStarted() {
		return consStarted;
	}
	public void setConsStarted(String consStarted) {
		this.consStarted = consStarted;
	}
	public String getConsNotStarted() {
		return consNotStarted;
	}
	public void setConsNotStarted(String consNotStarted) {
		this.consNotStarted = consNotStarted;
	}
	public String getConsCompleted() {
		return consCompleted;
	}
	public void setConsCompleted(String consCompleted) {
		this.consCompleted = consCompleted;
	}
	public String getMandalStarted() {
		return mandalStarted;
	}
	public void setMandalStarted(String mandalStarted) {
		this.mandalStarted = mandalStarted;
	}
	public String getMandalNotStarted() {
		return mandalNotStarted;
	}
	public void setMandalNotStarted(String mandalNotStarted) {
		this.mandalNotStarted = mandalNotStarted;
	}
	public String getMandalCompleted() {
		return mandalCompleted;
	}
	public void setMandalCompleted(String mandalCompleted) {
		this.mandalCompleted = mandalCompleted;
	}
	public String getPanchayatStarted() {
		return panchayatStarted;
	}
	public void setPanchayatStarted(String panchayatStarted) {
		this.panchayatStarted = panchayatStarted;
	}
	public String getPanchayatNotStarted() {
		return panchayatNotStarted;
	}
	public void setPanchayatNotStarted(String panchayatNotStarted) {
		this.panchayatNotStarted = panchayatNotStarted;
	}
	public String getPanchayatCompleted() {
		return panchayatCompleted;
	}
	public void setPanchayatCompleted(String panchayatCompleted) {
		this.panchayatCompleted = panchayatCompleted;
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
	public String getDistrictNotStarted() {
		return districtNotStarted;
	}
	public void setDistrictNotStarted(String districtNotStarted) {
		this.districtNotStarted = districtNotStarted;
	}
	public String getDistrictCompleted() {
		return districtCompleted;
	}
	public void setDistrictCompleted(String districtCompleted) {
		this.districtCompleted = districtCompleted;
	}
	public String getParliamentStarted() {
		return parliamentStarted;
	}
	public void setParliamentStarted(String parliamentStarted) {
		this.parliamentStarted = parliamentStarted;
	}
	public String getParliamentNotStarted() {
		return parliamentNotStarted;
	}
	public void setParliamentNotStarted(String parliamentNotStarted) {
		this.parliamentNotStarted = parliamentNotStarted;
	}
	public String getParliamentCompleted() {
		return parliamentCompleted;
	}
	public void setParliamentCompleted(String parliamentCompleted) {
		this.parliamentCompleted = parliamentCompleted;
	}
	
}
