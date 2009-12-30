/* 
 * Copyright (c) 2009 IT Grids.
 * All Rights Reserved.
 *
 * IT Grids Confidential Information.
 * Created on October 28, 2009
 */
package com.itgrids.partyanalyst.dto;

public class PartyResultsPercentageVO {

	private String year;
	private String percentage;
	private Long seatsWOn;
	
	//getters and setters
	public String getYear() {
		return year;
	}
	public void setYear(String year) {
		this.year = year;
	}
	public String getPercentage() {
		return percentage;
	}
	public void setPercentage(String percentage) {
		this.percentage = percentage;
	}
	public Long getSeatsWOn() {
		return seatsWOn;
	}
	public void setSeatsWOn(Long seatsWOn) {
		this.seatsWOn = seatsWOn;
	}
	
}
