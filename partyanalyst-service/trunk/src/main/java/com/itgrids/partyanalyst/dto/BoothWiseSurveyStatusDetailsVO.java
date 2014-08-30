package com.itgrids.partyanalyst.dto;

import java.util.ArrayList;
import java.util.List;

public class BoothWiseSurveyStatusDetailsVO {
	
	private Long boothId;
	private String partNo;
	private String dcCompleted = "N";
	private String dvCompleted = "N";
	private String qcCompleted = "N";
	private String wmDcCompleted = "N";
	private String wmDvCompleted = "N";
	private Long totalVoters;
	
	
	private String dcName;
	private String dcMobileNo;
	private String leaderName;
	private String leaderMobile;
	private String constituency;
	private Long count;
	private List<String> boothList = new ArrayList<String>(0);
	private String startTime;
	private String endTime;
	private String workedTime;
	private List<BoothWiseSurveyStatusDetailsVO> boothWiseSurveyDetailsVOList = new ArrayList<BoothWiseSurveyStatusDetailsVO>(0);
	
	
	public List<String> getBoothList() {
		return boothList;
	}
	public void setBoothList(List<String> boothList) {
		this.boothList = boothList;
	}
	public String getDcName() {
		return dcName;
	}
	public void setDcName(String dcName) {
		this.dcName = dcName;
	}
	public String getDcMobileNo() {
		return dcMobileNo;
	}
	public void setDcMobileNo(String dcMobileNo) {
		this.dcMobileNo = dcMobileNo;
	}
	public String getLeaderName() {
		return leaderName;
	}
	public void setLeaderName(String leaderName) {
		this.leaderName = leaderName;
	}
	public String getLeaderMobile() {
		return leaderMobile;
	}
	public void setLeaderMobile(String leaderMobile) {
		this.leaderMobile = leaderMobile;
	}
	public String getConstituency() {
		return constituency;
	}
	public void setConstituency(String constituency) {
		this.constituency = constituency;
	}
	public Long getCount() {
		return count;
	}
	public void setCount(Long count) {
		this.count = count;
	}
	public String getStartTime() {
		return startTime;
	}
	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}
	public String getEndTime() {
		return endTime;
	}
	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}
	public String getWorkedTime() {
		return workedTime;
	}
	public void setWorkedTime(String workedTime) {
		this.workedTime = workedTime;
	}
	public List<BoothWiseSurveyStatusDetailsVO> getBoothWiseSurveyDetailsVOList() {
		return boothWiseSurveyDetailsVOList;
	}
	public void setBoothWiseSurveyDetailsVOList(
			List<BoothWiseSurveyStatusDetailsVO> boothWiseSurveyDetailsVOList) {
		this.boothWiseSurveyDetailsVOList = boothWiseSurveyDetailsVOList;
	}
	public Long getTotalVoters() {
		return totalVoters;
	}
	public void setTotalVoters(Long totalVoters) {
		this.totalVoters = totalVoters;
	}
	public Long getBoothId() {
		return boothId;
	}
	public void setBoothId(Long boothId) {
		this.boothId = boothId;
	}
	public String getPartNo() {
		return partNo;
	}
	public void setPartNo(String partNo) {
		this.partNo = partNo;
	}
	public String getDcCompleted() {
		return dcCompleted;
	}
	public void setDcCompleted(String dcCompleted) {
		this.dcCompleted = dcCompleted;
	}
	public String getDvCompleted() {
		return dvCompleted;
	}
	public void setDvCompleted(String dvCompleted) {
		this.dvCompleted = dvCompleted;
	}
	public String getQcCompleted() {
		return qcCompleted;
	}
	public void setQcCompleted(String qcCompleted) {
		this.qcCompleted = qcCompleted;
	}
	public String getWmDcCompleted() {
		return wmDcCompleted;
	}
	public void setWmDcCompleted(String wmDcCompleted) {
		this.wmDcCompleted = wmDcCompleted;
	}
	public String getWmDvCompleted() {
		return wmDvCompleted;
	}
	public void setWmDvCompleted(String wmDvCompleted) {
		this.wmDvCompleted = wmDvCompleted;
	}

}
