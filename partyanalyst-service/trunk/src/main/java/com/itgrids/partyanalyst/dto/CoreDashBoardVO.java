package com.itgrids.partyanalyst.dto;

import java.util.ArrayList;
import java.util.List;

public class CoreDashBoardVO {
	public Long id;
	public String name;
	public Long isDepartment;
	public Long organizationId;
	public String organization;
	public Long benefitId;
	public String benefit;
	public Long positiveCountMain=0l;
	public Long negativCountMain=0l;
	public Long neutralCountMain=0l;
	public Long positiveCountDist=0l;
	public Long negativCountDist=0l;
	public Long neutralCountDist=0l;
	public String ediitonType;
	public List<CoreDashBoardVO> coreDashBoardVOList=new ArrayList<CoreDashBoardVO>(0);
	public List<CoreDashBoardVO> coreDashBoardVOList1=new ArrayList<CoreDashBoardVO>(0);
	public List<CoreDashBoardVO> coreDashBoardVOList2=new ArrayList<CoreDashBoardVO>(0);
	public Long districtId;
	public String districtName;
	public Long count=0l;
	public Long tdpCount = 0l;
	public Long ysrcCount = 0l;
	public Long incCount = 0l;
	public Long bjpCount = 0l;
	
	private Double positivePerc = 0.0;
	private Double negativePerc = 0.0;
	
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
	public Long getPositiveCountMain() {
		return positiveCountMain;
	}
	public void setPositiveCountMain(Long positiveCountMain) {
		this.positiveCountMain = positiveCountMain;
	}
	public Long getNegativCountMain() {
		return negativCountMain;
	}
	public void setNegativCountMain(Long negativCountMain) {
		this.negativCountMain = negativCountMain;
	}
	public Long getNeutralCountMain() {
		return neutralCountMain;
	}
	public void setNeutralCountMain(Long neutralCountMain) {
		this.neutralCountMain = neutralCountMain;
	}
	public Long getPositiveCountDist() {
		return positiveCountDist;
	}
	public void setPositiveCountDist(Long positiveCountDist) {
		this.positiveCountDist = positiveCountDist;
	}
	public Long getNegativCountDist() {
		return negativCountDist;
	}
	public void setNegativCountDist(Long negativCountDist) {
		this.negativCountDist = negativCountDist;
	}
	public Long getNeutralCountDist() {
		return neutralCountDist;
	}
	public void setNeutralCountDist(Long neutralCountDist) {
		this.neutralCountDist = neutralCountDist;
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
	public Long getCount() {
		return count;
	}
	public void setCount(Long count) {
		this.count = count;
	}
	public Double getPositivePerc() {
		return positivePerc;
	}
	public void setPositivePerc(Double positivePerc) {
		this.positivePerc = positivePerc;
	}
	public Double getNegativePerc() {
		return negativePerc;
	}
	public void setNegativePerc(Double negativePerc) {
		this.negativePerc = negativePerc;
	}
	public List<CoreDashBoardVO> getCoreDashBoardVOList1() {
		return coreDashBoardVOList1;
	}
	public void setCoreDashBoardVOList1(List<CoreDashBoardVO> coreDashBoardVOList1) {
		this.coreDashBoardVOList1 = coreDashBoardVOList1;
	}
	public Long getTdpCount() {
		return tdpCount;
	}
	public void setTdpCount(Long tdpCount) {
		this.tdpCount = tdpCount;
	}
	public Long getYsrcCount() {
		return ysrcCount;
	}
	public void setYsrcCount(Long ysrcCount) {
		this.ysrcCount = ysrcCount;
	}
	public Long getIncCount() {
		return incCount;
	}
	public void setIncCount(Long incCount) {
		this.incCount = incCount;
	}
	public Long getBjpCount() {
		return bjpCount;
	}
	public void setBjpCount(Long bjpCount) {
		this.bjpCount = bjpCount;
	}
	public List<CoreDashBoardVO> getCoreDashBoardVOList2() {
		return coreDashBoardVOList2;
	}
	public void setCoreDashBoardVOList2(List<CoreDashBoardVO> coreDashBoardVOList2) {
		this.coreDashBoardVOList2 = coreDashBoardVOList2;
	}
	
	
}
