package com.itgrids.partyanalyst.dto;

import java.io.Serializable;

public class KaizalaExceptionalReportVO implements Serializable {
	private Long parliamentId;
	private String parliamentName;
	private Long constituencyId;
	private String constituencyName;
	private Long target;
	private Long committeeInstalled;
	private Long committeeNotInstalled;
	private Long committeeHavingNoSmartPhone;
	private Double committeeNotInstalledPer;
	private Long publicInstalled;
	private Long cadreInstalled;
	private Long totalInstalled;
	private Long totalNotInstalled;
	private Long totalNotHavingSmartphone;
	public Long getParliamentId() {
		return parliamentId;
	}
	public void setParliamentId(Long parliamentId) {
		this.parliamentId = parliamentId;
	}
	public String getParliamentName() {
		return parliamentName;
	}
	public void setParliamentName(String parliamentName) {
		this.parliamentName = parliamentName;
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
	public Long getTarget() {
		return target;
	}
	public void setTarget(Long target) {
		this.target = target;
	}
	public Long getCommitteeInstalled() {
		return committeeInstalled;
	}
	public void setCommitteeInstalled(Long committeeInstalled) {
		this.committeeInstalled = committeeInstalled;
	}
	public Long getCommitteeNotInstalled() {
		return committeeNotInstalled;
	}
	public void setCommitteeNotInstalled(Long committeeNotInstalled) {
		this.committeeNotInstalled = committeeNotInstalled;
	}
	public Long getCommitteeHavingNoSmartPhone() {
		return committeeHavingNoSmartPhone;
	}
	public void setCommitteeHavingNoSmartPhone(Long committeeHavingNoSmartPhone) {
		this.committeeHavingNoSmartPhone = committeeHavingNoSmartPhone;
	}
	public Double getCommitteeNotInstalledPer() {
		return committeeNotInstalledPer;
	}
	public void setCommitteeNotInstalledPer(Double committeeNotInstalledPer) {
		this.committeeNotInstalledPer = committeeNotInstalledPer;
	}
	public Long getPublicInstalled() {
		return publicInstalled;
	}
	public void setPublicInstalled(Long publicInstalled) {
		this.publicInstalled = publicInstalled;
	}
	public Long getCadreInstalled() {
		return cadreInstalled;
	}
	public void setCadreInstalled(Long cadreInstalled) {
		this.cadreInstalled = cadreInstalled;
	}
	public Long getTotalInstalled() {
		return totalInstalled;
	}
	public void setTotalInstalled(Long totalInstalled) {
		this.totalInstalled = totalInstalled;
	}
	public Long getTotalNotInstalled() {
		return totalNotInstalled;
	}
	public void setTotalNotInstalled(Long totalNotInstalled) {
		this.totalNotInstalled = totalNotInstalled;
	}
	public Long getTotalNotHavingSmartphone() {
		return totalNotHavingSmartphone;
	}
	public void setTotalNotHavingSmartphone(Long totalNotHavingSmartphone) {
		this.totalNotHavingSmartphone = totalNotHavingSmartphone;
	}
	
	
	
}
