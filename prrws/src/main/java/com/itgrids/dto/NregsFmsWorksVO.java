package com.itgrids.dto;

import java.io.Serializable;

public class NregsFmsWorksVO implements Serializable{

	private Long id;
	private String name;
	
	private String uniqueId;
	private String district;
	private String constituency;
	private String mandal;
	private String panchayat;
	
	private String category;
	private String works;
	private String wage;
	private String material;
	private String total;
	
	private String program;
	private String habitationName;
	private String workCode;
	private String workName;
	private String description;
	private String amountUnSkilled;
	private String amountMaterial;
	private String totalCost;
	private String totalManDays;
	private String days;
	
	private Long grounded;
	private Long inProgress;
	private Long completed;
	private Long programId;
	private Long noOfWorks;
	private Long locationsCount;
	private Long finalWorks;
	private String finalAmount;
	
	
	public Long getFinalWorks() {
		return finalWorks;
	}
	public void setFinalWorks(Long finalWorks) {
		this.finalWorks = finalWorks;
	}
	public String getFinalAmount() {
		return finalAmount;
	}
	public void setFinalAmount(String finalAmount) {
		this.finalAmount = finalAmount;
	}
	public Long getLocationsCount() {
		return locationsCount;
	}
	public void setLocationsCount(Long locationsCount) {
		this.locationsCount = locationsCount;
	}
	public Long getNoOfWorks() {
		return noOfWorks;
	}
	public void setNoOfWorks(Long noOfWorks) {
		this.noOfWorks = noOfWorks;
	}
	public Long getProgramId() {
		return programId;
	}
	public void setProgramId(Long programId) {
		this.programId = programId;
	}
	public Long getGrounded() {
		return grounded;
	}
	public void setGrounded(Long grounded) {
		this.grounded = grounded;
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
	public String getProgram() {
		return program;
	}
	public void setProgram(String program) {
		this.program = program;
	}
	public String getHabitationName() {
		return habitationName;
	}
	public void setHabitationName(String habitationName) {
		this.habitationName = habitationName;
	}
	public String getWorkCode() {
		return workCode;
	}
	public void setWorkCode(String workCode) {
		this.workCode = workCode;
	}
	public String getWorkName() {
		return workName;
	}
	public void setWorkName(String workName) {
		this.workName = workName;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getAmountUnSkilled() {
		return amountUnSkilled;
	}
	public void setAmountUnSkilled(String amountUnSkilled) {
		this.amountUnSkilled = amountUnSkilled;
	}
	public String getAmountMaterial() {
		return amountMaterial;
	}
	public void setAmountMaterial(String amountMaterial) {
		this.amountMaterial = amountMaterial;
	}
	public String getTotalCost() {
		return totalCost;
	}
	public void setTotalCost(String totalCost) {
		this.totalCost = totalCost;
	}
	public String getTotalManDays() {
		return totalManDays;
	}
	public void setTotalManDays(String totalManDays) {
		this.totalManDays = totalManDays;
	}
	public String getDays() {
		return days;
	}
	public void setDays(String days) {
		this.days = days;
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
	public String getUniqueId() {
		return uniqueId;
	}
	public void setUniqueId(String uniqueId) {
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
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public String getWorks() {
		return works;
	}
	public void setWorks(String works) {
		this.works = works;
	}
	public String getWage() {
		return wage;
	}
	public void setWage(String wage) {
		this.wage = wage;
	}
	public String getMaterial() {
		return material;
	}
	public void setMaterial(String material) {
		this.material = material;
	}
	public String getTotal() {
		return total;
	}
	public void setTotal(String total) {
		this.total = total;
	}
}
