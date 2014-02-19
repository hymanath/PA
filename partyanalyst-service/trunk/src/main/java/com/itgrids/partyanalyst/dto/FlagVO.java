package com.itgrids.partyanalyst.dto;

import java.util.List;

public class FlagVO {
	
	public Long flagId;
	public String flagName;
	public List<String> voterIDS;
	public String colour;
	public Long mobileUserId;
	
	public Long getMobileUserId() {
		return mobileUserId;
	}
	public void setMobileUserId(Long mobileUserId) {
		this.mobileUserId = mobileUserId;
	}
	public Long getFlagId() {
		return flagId;
	}
	public void setFlagId(Long flagId) {
		this.flagId = flagId;
	}
	public String getFlagName() {
		return flagName;
	}
	public void setFlagName(String flagName) {
		this.flagName = flagName;
	}
	public List<String> getVoterIDS() {
		return voterIDS;
	}
	public void setVoterIDS(List<String> voterIDS) {
		this.voterIDS = voterIDS;
	}
	public String getColour() {
		return colour;
	}
	public void setColour(String colour) {
		this.colour = colour;
	}

}
