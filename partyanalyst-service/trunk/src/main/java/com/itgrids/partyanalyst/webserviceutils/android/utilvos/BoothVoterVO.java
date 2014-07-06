package com.itgrids.partyanalyst.webserviceutils.android.utilvos;

import java.util.ArrayList;
import java.util.List;

public class BoothVoterVO {
	
	private List<Long> voterIds = new ArrayList<Long>();
	private String partNo;

	
	
	public List<Long> getVoterIds() {
		return voterIds;
	}
	public void setVoterIds(List<Long> voterIds) {
		this.voterIds = voterIds;
	}
	public String getPartNo() {
		return partNo;
	}
	public void setPartNo(String partNo) {
		this.partNo = partNo;
	}

}
