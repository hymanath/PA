package com.itgrids.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class NregsDataVO implements Serializable{

	private Long uniqueId;
	private String district;
	private String constituency;
	private String mandal;
	private String panchayat;
	private Long targetPersonDays;
	private Long generatedPersonDays;
	private String perAppLB;
	private String avgWageRate;
	private String totalExpenditure;
	
	private Long target;
	private String grounded;
	private String notGrounded;
	private Long inProgress;
	private Long completed;
	private String percentage;
	private Long total = 0l;
	private Long constiInRed = 0l;
	private Long constiInOrange = 0l;
	private Long constiInGreen = 0l;
	private Long mandalsInRed = 0l;
	private Long mandalsInOrange = 0l;
	private Long mandalsInGreen = 0l;
	
	private List<NregsDataVO> distConsCuntList = new ArrayList<NregsDataVO>(0);
	private List<NregsDataVO> distMandalCuntList = new ArrayList<NregsDataVO>(0);
	private List<NregsDataVO> distList = new ArrayList<NregsDataVO>(0);
	
	
	public Long getUniqueId() {
		return uniqueId;
	}
	public void setUniqueId(Long uniqueId) {
		this.uniqueId = uniqueId;
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
	public Long getTargetPersonDays() {
		return targetPersonDays;
	}
	public void setTargetPersonDays(Long targetPersonDays) {
		this.targetPersonDays = targetPersonDays;
	}
	public Long getGeneratedPersonDays() {
		return generatedPersonDays;
	}
	public void setGeneratedPersonDays(Long generatedPersonDays) {
		this.generatedPersonDays = generatedPersonDays;
	}
	public String getPerAppLB() {
		return perAppLB;
	}
	public void setPerAppLB(String perAppLB) {
		this.perAppLB = perAppLB;
	}
	public String getAvgWageRate() {
		return avgWageRate;
	}
	public void setAvgWageRate(String avgWageRate) {
		this.avgWageRate = avgWageRate;
	}
	public String getTotalExpenditure() {
		return totalExpenditure;
	}
	public void setTotalExpenditure(String totalExpenditure) {
		this.totalExpenditure = totalExpenditure;
	}
	public Long getTarget() {
		return target;
	}
	public void setTarget(Long target) {
		this.target = target;
	}
	public String getGrounded() {
		return grounded;
	}
	public void setGrounded(String grounded) {
		this.grounded = grounded;
	}
	public String getNotGrounded() {
		return notGrounded;
	}
	public void setNotGrounded(String notGrounded) {
		this.notGrounded = notGrounded;
	}
	public Long getInProgress() {
		return inProgress;
	}
	public void setInProgress(Long inProgress) {
		this.inProgress = inProgress;
	}
	public Long getCompleted() {
		return completed;
	}
	public void setCompleted(Long completed) {
		this.completed = completed;
	}
	public String getPercentage() {
		return percentage;
	}
	public void setPercentage(String percentage) {
		this.percentage = percentage;
	}
	public Long getConstiInRed() {
		return constiInRed;
	}
	public void setConstiInRed(Long constiInRed) {
		this.constiInRed = constiInRed;
	}
	public Long getConstiInOrange() {
		return constiInOrange;
	}
	public void setConstiInOrange(Long constiInOrgange) {
		this.constiInOrange = constiInOrgange;
	}
	public Long getConstiInGreen() {
		return constiInGreen;
	}
	public void setConstiInGreen(Long constiInGreen) {
		this.constiInGreen = constiInGreen;
	}
	public Long getTotal() {
		return total;
	}
	public void setTotal(Long total) {
		this.total = total;
	}
	public Long getMandalsInRed() {
		return mandalsInRed;
	}
	public void setMandalsInRed(Long mandalsInRed) {
		this.mandalsInRed = mandalsInRed;
	}
	public Long getMandalsInOrange() {
		return mandalsInOrange;
	}
	public void setMandalsInOrange(Long mandalsInOrange) {
		this.mandalsInOrange = mandalsInOrange;
	}
	public Long getMandalsInGreen() {
		return mandalsInGreen;
	}
	public void setMandalsInGreen(Long mandalsInGreen) {
		this.mandalsInGreen = mandalsInGreen;
	}
	public List<NregsDataVO> getDistConsCuntList() {
		return distConsCuntList;
	}
	public void setDistConsCuntList(List<NregsDataVO> distConsCuntList) {
		this.distConsCuntList = distConsCuntList;
	}
	public List<NregsDataVO> getDistMandalCuntList() {
		return distMandalCuntList;
	}
	public void setDistMandalCuntList(List<NregsDataVO> distMandalCuntList) {
		this.distMandalCuntList = distMandalCuntList;
	}
	public List<NregsDataVO> getDistList() {
		return distList;
	}
	public void setDistList(List<NregsDataVO> distList) {
		this.distList = distList;
	}
	
}
