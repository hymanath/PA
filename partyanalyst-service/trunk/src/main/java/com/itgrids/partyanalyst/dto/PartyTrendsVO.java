package com.itgrids.partyanalyst.dto;

import com.itgrids.partyanalyst.model.PartyTrends;





/**
 * @author Administrator Feb 24, 2014
 *
 */
public class PartyTrendsVO {
	
	 private Long partyTrendsId;	

		
	  
	 private Long constituencyId;
	 private String  constituencyName;
	 private String name;
	 
	  
	private Float pervTrenzWt;
	private Float prpWt;
	private Float  youngVotersWt;
	private Float  totalWt;
	
	public PartyTrendsVO()
	{
		super();
	}
	
	public PartyTrendsVO(Long partyTrendsId, Long constituencyId,
			String constituencyName, String name, Float pervTrenzWt,
			Float prpWt, Float youngVotersWt, Float totalWt) {
		super();
		this.partyTrendsId = partyTrendsId;
		this.constituencyId = constituencyId;
		this.constituencyName = constituencyName;
		this.name = name;
		this.pervTrenzWt = pervTrenzWt;
		this.prpWt = prpWt;
		this.youngVotersWt = youngVotersWt;
		this.totalWt = totalWt;
	}
	
	@Override
	public String toString() {
		return "PartyTrendsVO [partyTrendsId=" + partyTrendsId
				+ ", constituencyId=" + constituencyId + ", name=" + name
				+ ", pervTrenzWt=" + pervTrenzWt + ", prpWt=" + prpWt
				+ ", youngVotersWt=" + youngVotersWt + ", totalWt=" + totalWt
				+ "]";
	}

	public Long getPartyTrendsId() {
		return partyTrendsId;
	}

	public void setPartyTrendsId(Long partyTrendsId) {
		this.partyTrendsId = partyTrendsId;
	}

	public Long getConstituencyId() {
		return constituencyId;
	}

	public void setConstituencyId(Long constituencyId) {
		this.constituencyId = constituencyId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Float getPervTrenzWt() {
		return pervTrenzWt;
	}

	public void setPervTrenzWt(Float pervTrenzWt) {
		this.pervTrenzWt = pervTrenzWt;
	}

	public Float getPrpWt() {
		return prpWt;
	}

	public void setPrpWt(Float prpWt) {
		this.prpWt = prpWt;
	}

	public Float getYoungVotersWt() {
		return youngVotersWt;
	}

	public void setYoungVotersWt(Float youngVotersWt) {
		this.youngVotersWt = youngVotersWt;
	}

	public Float getTotalWt() {
		return totalWt;
	}

	public void setTotalWt(Float totalWt) {
		this.totalWt = totalWt;
	}

	public String getConstituencyName() {
		return constituencyName;
	}

	public void setConstituencyName(String constituencyName) {
		this.constituencyName = constituencyName;
	}

	
	
	
}
