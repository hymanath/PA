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
}
