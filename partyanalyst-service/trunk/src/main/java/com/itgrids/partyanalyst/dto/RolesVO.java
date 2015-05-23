package com.itgrids.partyanalyst.dto;

public class RolesVO {

	private Long id;
	private String name;
	private Long filledCount;
	private Long vacancyCount;
	private Long total = 0l;

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Long getFilledCount() {
		return filledCount;
	}
	public void setFilledCount(Long filledCount) {
		this.filledCount = filledCount;
	}
	public Long getVacancyCount() {
		return vacancyCount;
	}
	public void setVacancyCount(Long vacancyCount) {
		this.vacancyCount = vacancyCount;
	}
	public Long getTotal() {
		return total;
	}
	public void setTotal(Long total) {
		this.total = total;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	
	
	
	
}
