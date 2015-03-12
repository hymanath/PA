package com.itgrids.partyanalyst.dto;



public class CadreTravelsVO {
	
	private String customerId;		
	private String membershipNo;
	private String	ticketsCount;	
	private String ticketCost;				
	private String	discountPerc;
	private String dateOfJourney;
	private String isAddress;
	private String mobileNo;
	
	
	public String getIsAddress() {
		return isAddress;
	}
	public void setIsAddress(String isAddress) {
		this.isAddress = isAddress;
	}
	public String getMembershipNo() {
		return membershipNo;
	}
	public void setMembershipNo(String membershipNo) {
		this.membershipNo = membershipNo;
	}
	
	public String getTicketCost() {
		return ticketCost;
	}
	public void setTicketCost(String ticketCost) {
		this.ticketCost = ticketCost;
	}
	
	public String getDateOfJourney() {
		return dateOfJourney;
	}
	public void setDateOfJourney(String dateOfJourney) {
		this.dateOfJourney = dateOfJourney;
	}
	public String getTicketsCount() {
		return ticketsCount;
	}
	public void setTicketsCount(String ticketsCount) {
		this.ticketsCount = ticketsCount;
	}
	public String getDiscountPerc() {
		return discountPerc;
	}
	public void setDiscountPerc(String discountPerc) {
		this.discountPerc = discountPerc;
	}
	public String getCustomerId() {
		return customerId;
	}
	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}
	public String getMobileNo() {
		return mobileNo;
	}
	public void setMobileNo(String mobileNo) {
		this.mobileNo = mobileNo;
	}
}
