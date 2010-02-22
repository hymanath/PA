/* 
 * Copyright (c) 2009 IT Grids.
 * All Rights Reserved.
 *
 * IT Grids Confidential Information.
 * Created on February 15,2010.
 */
package com.itgrids.partyanalyst.dto;

import java.math.BigDecimal;

public class CompleteResultsVO {

	private Long seatsWon;
	private BigDecimal votesPercentage;
	
	
	public Long getSeatsWon() {
		return seatsWon;
	}
	public void setSeatsWon(Long seatsWon) {
		this.seatsWon = seatsWon;
	}
	public BigDecimal getVotesPercentage() {
		return votesPercentage;
	}
	public void setVotesPercentage(BigDecimal votesPercentage) {
		this.votesPercentage = votesPercentage;
	}
	
}
