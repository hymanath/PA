package com.itgrids.partyanalyst.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class TotalMPTCMandalLeaderVO implements Serializable {

	private List<MPTCMandalLeaderVO> totalLeaders = new ArrayList<MPTCMandalLeaderVO>();
	private List<MPTCMandalLeaderVO> winners = new ArrayList<MPTCMandalLeaderVO>();
	
	public List<MPTCMandalLeaderVO> getTotalLeaders() {
		return totalLeaders;
	}
	public void setTotalLeaders(List<MPTCMandalLeaderVO> totalLeaders) {
		this.totalLeaders = totalLeaders;
	}
	public List<MPTCMandalLeaderVO> getWinners() {
		return winners;
	}
	public void setWinners(List<MPTCMandalLeaderVO> winners) {
		this.winners = winners;
	}
}
