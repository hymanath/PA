package com.itgrids.partyanalyst.dto;

import java.io.Serializable;
import java.util.Date;

public class LeaderOccasionVO implements Serializable{

	private Long leaderOccasionId;
	private Long tdpCadreId;
	private Date occasionDate;
	public Long getLeaderOccasionId() {
		return leaderOccasionId;
	}
	public void setLeaderOccasionId(Long leaderOccasionId) {
		this.leaderOccasionId = leaderOccasionId;
	}
	public Long getTdpCadreId() {
		return tdpCadreId;
	}
	public void setTdpCadreId(Long tdpCadreId) {
		this.tdpCadreId = tdpCadreId;
	}
	public Date getOccasionDate() {
		return occasionDate;
	}
	public void setOccasionDate(Date occasionDate) {
		this.occasionDate = occasionDate;
	}
	
	
	
	
}
