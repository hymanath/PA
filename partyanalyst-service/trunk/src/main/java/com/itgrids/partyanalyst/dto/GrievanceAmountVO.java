package com.itgrids.partyanalyst.dto;

public class GrievanceAmountVO {
	
	private String name;
	private Long donationAmount;
	private Long totalRequests;
	private Long count;
	
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Long getDonationAmount() {
		return donationAmount;
	}
	public void setDonationAmount(Long donationAmount) {
		this.donationAmount = donationAmount;
	}
	public Long getTotalRequests() {
		return totalRequests;
	}
	public void setTotalRequests(Long totalRequests) {
		this.totalRequests = totalRequests;
	}
	public Long getCount() {
		return count;
	}
	public void setCount(Long count) {
		this.count = count;
	}
	
	

}
