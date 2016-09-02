package com.itgrids.partyanalyst.dto;

import java.util.List;

public class CoreDashBoardVO {
	public Long id;
	public String name;
	public Long isDepartment;
	public Long organizationId;
	public String organization;
	public Long benefitId;
	public String benefit;
	public Long positiveCount;
	public Long negativCount;
	public Long neutralCount;
	public String ediitonType;
	public List<CoreDashBoardVO> coreDashBoardVOList;
	
	
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
	public Long getIsDepartment() {
		return isDepartment;
	}
	public void setIsDepartment(Long isDepartment) {
		this.isDepartment = isDepartment;
	}
	public Long getOrganizationId() {
		return organizationId;
	}
	public void setOrganizationId(Long organizationId) {
		this.organizationId = organizationId;
	}
	public String getOrganization() {
		return organization;
	}
	public void setOrganization(String organization) {
		this.organization = organization;
	}
	public Long getBenefitId() {
		return benefitId;
	}
	public void setBenefitId(Long benefitId) {
		this.benefitId = benefitId;
	}
	public String getBenefit() {
		return benefit;
	}
	public void setBenefit(String benefit) {
		this.benefit = benefit;
	}
	public Long getPositiveCount() {
		return positiveCount;
	}
	public void setPositiveCount(Long positiveCount) {
		this.positiveCount = positiveCount;
	}
	public Long getNegativCount() {
		return negativCount;
	}
	public void setNegativCount(Long negativCount) {
		this.negativCount = negativCount;
	}
	public Long getNeutralCount() {
		return neutralCount;
	}
	public void setNeutralCount(Long neutralCount) {
		this.neutralCount = neutralCount;
	}
	public String getEdiitonType() {
		return ediitonType;
	}
	public void setEdiitonType(String ediitonType) {
		this.ediitonType = ediitonType;
	}
	public List<CoreDashBoardVO> getCoreDashBoardVOList() {
		return coreDashBoardVOList;
	}
	public void setCoreDashBoardVOList(List<CoreDashBoardVO> coreDashBoardVOList) {
		this.coreDashBoardVOList = coreDashBoardVOList;
	}
	
	
}
