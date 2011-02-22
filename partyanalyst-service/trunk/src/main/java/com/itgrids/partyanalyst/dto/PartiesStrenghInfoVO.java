package com.itgrids.partyanalyst.dto;

import java.util.ArrayList;
import java.util.List;

public class PartiesStrenghInfoVO {

	private List<Long> requiredConstituencies = new ArrayList<Long>(0);
	private List<Long> latestConstituencies = new ArrayList<Long>(0);
	private List<Long> remianingConstituencies = new ArrayList<Long>(0);
	private List<PartiesDetailsVO> partiesDetailsVO;
	private SelectOptionVO constituencyDetails;
	
	public List<PartiesDetailsVO> getPartiesDetailsVO() {
		return partiesDetailsVO;
	}
	public void setPartiesDetailsVO(List<PartiesDetailsVO> partiesDetailsVO) {
		this.partiesDetailsVO = partiesDetailsVO;
	}
	public SelectOptionVO getConstituencyDetails() {
		return constituencyDetails;
	}
	public void setConstituencyDetails(SelectOptionVO constituencyDetails) {
		this.constituencyDetails = constituencyDetails;
	}
	public List<Long> getRequiredConstituencies() {
		return requiredConstituencies;
	}
	public void setRequiredConstituencies(List<Long> requiredConstituencies) {
		this.requiredConstituencies = requiredConstituencies;
	}
	public List<Long> getLatestConstituencies() {
		return latestConstituencies;
	}
	public void setLatestConstituencies(List<Long> latestConstituencies) {
		this.latestConstituencies = latestConstituencies;
	}
	public List<Long> getRemianingConstituencies() {
		return remianingConstituencies;
	}
	public void setRemianingConstituencies(List<Long> remianingConstituencies) {
		this.remianingConstituencies = remianingConstituencies;
	}
	
}
