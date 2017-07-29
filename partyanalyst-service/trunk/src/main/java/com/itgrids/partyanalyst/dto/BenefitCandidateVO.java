package com.itgrids.partyanalyst.dto;

public class BenefitCandidateVO {
	private Long id;
	private String name="";
	private String relativeName="";
	private Long cadreId;
	private String memberShipNum="";
	private String aadharNum="";
	private String mobilenum="";
	private Long manMunId;
	private String manMumName="";
	private String year="";
	private Long benefitId;
	private String benefitName="";
	private Long amount;
	private Long totalCount=0l;
	private Long totalPopulation;
	
	
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
	
	public String getRelativeName() {
		return relativeName;
	}
	public void setRelativeName(String relativeName) {
		this.relativeName = relativeName;
	}
	public String getMemberShipNum() {
		return memberShipNum;
	}
	public void setMemberShipNum(String memberShipNum) {
		this.memberShipNum = memberShipNum;
	}
	public String getAadharNum() {
		return aadharNum;
	}
	public void setAadharNum(String aadharNum) {
		this.aadharNum = aadharNum;
	}
	public String getMobilenum() {
		return mobilenum;
	}
	public void setMobilenum(String mobilenum) {
		this.mobilenum = mobilenum;
	}
	public Long getManMunId() {
		return manMunId;
	}
	public void setManMunId(Long manMunId) {
		this.manMunId = manMunId;
	}
	public String getManMumName() {
		return manMumName;
	}
	public void setManMumName(String manMumName) {
		this.manMumName = manMumName;
	}
	public String getYear() {
		return year;
	}
	public void setYear(String year) {
		this.year = year;
	}
	public Long getBenefitId() {
		return benefitId;
	}
	public void setBenefitId(Long benefitId) {
		this.benefitId = benefitId;
	}
	public String getBenefitName() {
		return benefitName;
	}
	public void setBenefitName(String benefitName) {
		this.benefitName = benefitName;
	}
	public Long getAmount() {
		return amount;
	}
	public void setAmount(Long amount) {
		this.amount = amount;
	}
	public Long getCadreId() {
		return cadreId;
	}
	public void setCadreId(Long cadreId) {
		this.cadreId = cadreId;
	}
	public Long getTotalCount() {
		return totalCount;
	}
	public void setTotalCount(Long totalCount) {
		this.totalCount = totalCount;
	}
	public Long getTotalPopulation() {
		return totalPopulation;
	}
	public void setTotalPopulation(Long totalPopulation) {
		this.totalPopulation = totalPopulation;
	}
	
}
