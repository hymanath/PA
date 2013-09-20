package com.itgrids.partyanalyst.dto;

import java.math.BigDecimal;
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
   private String name;
   private List<CasteWiseResultVO> partiesList;
   
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
