package com.itgrids.partyanalyst.dto;

import java.util.List;
import java.util.Map;

import com.itgrids.partyanalyst.model.Party;

public class ContenetTransferVO {

	//Instance Variables
	private Map<Long,PartiesDetailsVO> partyOverview;
	private Map<Long,PartiesDetailsVO> data;
	private List result;
	private List<Long> allianceDetails;
	private Long won;
	private Long count;
	private Long wonRecently;
	private Long lostRecently;
	
	
	//constructor(s)
	
	public ContenetTransferVO(){		
	}
	
	public ContenetTransferVO(Long count,Long won){
		this.won = won;
		this.count = count;
	}
	
	public ContenetTransferVO(Long count,Long won,Long wonRecently,Long lostRecently){
		this.won = won;
		this.count = count;
		this.wonRecently = wonRecently;
		this.lostRecently = lostRecently;
	}
	
	//Getters and Setters
	
	
	public Map<Long, PartiesDetailsVO> getPartyOverview() {
		return partyOverview;
	}
	public List getResult() {
		return result;
	}

	public void setResult(List result) {
		this.result = result;
	}
	public List<Long> getAllianceDetails() {
		return allianceDetails;
	}

	public void setAllianceDetails(List<Long> allianceDetails) {
		this.allianceDetails = allianceDetails;
	}

	public Long getWon() {
		return won;
	}
	public void setWon(Long won) {
		this.won = won;
	}
	public Long getCount() {
		return count;
	}
	public void setCount(Long count) {
		this.count = count;
	}
	public Long getWonRecently() {
		return wonRecently;
	}
	public void setWonRecently(Long wonRecently) {
		this.wonRecently = wonRecently;
	}
	public Long getLostRecently() {
		return lostRecently;
	}
	public void setLostRecently(Long lostRecently) {
		this.lostRecently = lostRecently;
	}
	public void setPartyOverview(Map<Long, PartiesDetailsVO> partyOverview) {
		this.partyOverview = partyOverview;
	}
	public Map<Long, PartiesDetailsVO> getData() {
		return data;
	}
	public void setData(Map<Long, PartiesDetailsVO> data) {
		this.data = data;
	}
	
}
