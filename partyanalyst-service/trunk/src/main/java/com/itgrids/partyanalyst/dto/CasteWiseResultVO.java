package com.itgrids.partyanalyst.dto;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class CasteWiseResultVO {
    
   private Long year;
   private String type;
   private List<Long> totalVotesList;
   private List<Long> polledVotesList;
   private List<BigDecimal> polledVotesPercList;
   private Map<String,List<Long>> locationResults;
   private Map<String,List<BigDecimal>> locationPercnts;
 
   private List<CasteWiseResultVO> partiesList;
   private List<CasteWiseResultVO> candidateList  = new ArrayList<CasteWiseResultVO>();
   private Long id;
   private String name;
   private String caste;
   private Long votes = 0l;
   private String party;
   private String status;
   private String percentage;
   private List<SelectOptionVO> casteList = new ArrayList<SelectOptionVO>();
   private Long partyId;
   
   
   
	public Long getPartyId() {
	return partyId;
}

public void setPartyId(Long partyId) {
	this.partyId = partyId;
}

	public List<CasteWiseResultVO> getCandidateList() {
	return candidateList;
}

public void setCandidateList(List<CasteWiseResultVO> candidateList) {
	this.candidateList = candidateList;
}

public Long getId() {
	return id;
}

public void setId(Long id) {
	this.id = id;
}

public String getCaste() {
	return caste;
}

public void setCaste(String caste) {
	this.caste = caste;
}

public Long getVotes() {
	return votes;
}

public void setVotes(Long votes) {
	this.votes = votes;
}

public String getParty() {
	return party;
}

public void setParty(String party) {
	this.party = party;
}



public String getStatus() {
	return status;
}

public void setStatus(String status) {
	this.status = status;
}

public String getPercentage() {
	return percentage;
}

public void setPercentage(String percentage) {
	this.percentage = percentage;
}

public List<SelectOptionVO> getCasteList() {
	return casteList;
}

public void setCasteList(List<SelectOptionVO> casteList) {
	this.casteList = casteList;
}

	public Long getYear() {
		return year;
	}
	
	public void setYear(Long year) {
		this.year = year;
	}
	
	public String getType() {
		return type;
	}
	
	public void setType(String type) {
		this.type = type;
	}
	
	public List<Long> getTotalVotesList() {
		return totalVotesList;
	}
	
	public void setTotalVotesList(List<Long> totalVotesList) {
		this.totalVotesList = totalVotesList;
	}
	
	public List<Long> getPolledVotesList() {
		return polledVotesList;
	}
	
	public void setPolledVotesList(List<Long> polledVotesList) {
		this.polledVotesList = polledVotesList;
	}
	
	public List<BigDecimal> getPolledVotesPercList() {
		return polledVotesPercList;
	}
	
	public void setPolledVotesPercList(List<BigDecimal> polledVotesPercList) {
		this.polledVotesPercList = polledVotesPercList;
	}

	public Map<String, List<Long>> getLocationResults() {
		return locationResults;
	}

	public void setLocationResults(Map<String, List<Long>> locationResults) {
		this.locationResults = locationResults;
	}

	public Map<String, List<BigDecimal>> getLocationPercnts() {
		return locationPercnts;
	}

	public void setLocationPercnts(Map<String, List<BigDecimal>> locationPercnts) {
		this.locationPercnts = locationPercnts;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<CasteWiseResultVO> getPartiesList() {
		return partiesList;
	}

	public void setPartiesList(List<CasteWiseResultVO> partiesList) {
		this.partiesList = partiesList;
	}
	   
   
}
