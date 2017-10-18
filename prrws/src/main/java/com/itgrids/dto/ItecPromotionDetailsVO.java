package com.itgrids.dto;

public class ItecPromotionDetailsVO {

	private Long id;
	private String name;
	
	private String sector;
    private String district;
    private String noProjects;
    private String investment;
    private String realizedInvestment;
    private String employment;
    private String realizedEmployment;
    
    private String sourceOfLead;
    private String category;
    private String lineOfActivity;
    private String subSector;
    private String itSector;
    private String nameOfCompany;
    private String districtName;
    private String deptName;
    private Long categoryCount = 0L;
    
    
	public String getSourceOfLead() {
		return sourceOfLead;
	}
	public void setSourceOfLead(String sourceOfLead) {
		this.sourceOfLead = sourceOfLead;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public String getLineOfActivity() {
		return lineOfActivity;
	}
	public void setLineOfActivity(String lineOfActivity) {
		this.lineOfActivity = lineOfActivity;
	}
	public String getSubSector() {
		return subSector;
	}
	public void setSubSector(String subSector) {
		this.subSector = subSector;
	}
	public String getItSector() {
		return itSector;
	}
	public void setItSector(String itSector) {
		this.itSector = itSector;
	}
	public String getNameOfCompany() {
		return nameOfCompany;
	}
	public void setNameOfCompany(String nameOfCompany) {
		this.nameOfCompany = nameOfCompany;
	}
	public String getDistrictName() {
		return districtName;
	}
	public void setDistrictName(String districtName) {
		this.districtName = districtName;
	}
	public String getDeptName() {
		return deptName;
	}
	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}
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
	public String getSector() {
		return sector;
	}
	public void setSector(String sector) {
		this.sector = sector;
	}
	public String getDistrict() {
		return district;
	}
	public void setDistrict(String district) {
		this.district = district;
	}
	public String getNoProjects() {
		return noProjects;
	}
	public void setNoProjects(String noProjects) {
		this.noProjects = noProjects;
	}
	public String getInvestment() {
		return investment;
	}
	public void setInvestment(String investment) {
		this.investment = investment;
	}
	public String getRealizedInvestment() {
		return realizedInvestment;
	}
	public void setRealizedInvestment(String realizedInvestment) {
		this.realizedInvestment = realizedInvestment;
	}
	public String getEmployment() {
		return employment;
	}
	public void setEmployment(String employment) {
		this.employment = employment;
	}
	public String getRealizedEmployment() {
		return realizedEmployment;
	}
	public void setRealizedEmployment(String realizedEmployment) {
		this.realizedEmployment = realizedEmployment;
	}
	public Long getCategoryCount() {
		return categoryCount;
	}
	public void setCategoryCount(Long categoryCount) {
		this.categoryCount = categoryCount;
	}
}
