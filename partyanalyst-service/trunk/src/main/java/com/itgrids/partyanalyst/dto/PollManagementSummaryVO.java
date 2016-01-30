package com.itgrids.partyanalyst.dto;

import java.io.Serializable;
import java.util.List;

public class PollManagementSummaryVO implements Serializable{
     
	private Long   id;
	private String name;
	
	private Long   totalVoters;
	private Long   totalVotersPolled;
	private Long   totalVotersYetToBePolled; 
	private String pollPercent;
	private String yetToPollPercent;
	
	private Long   cadreCount;
	private Long   cadreCountPolled;
	private Long   cadreCountYetToBePolled;
	private String cadrepollPercent;
	private String cadreYetToPollPercent;
	
	private Long   capCadreCount;
	private Long   capCadreCountPolled;
	private Long   capCadreCountYetToBePolled;
	private String capCadrePollPercent;
	private String capCadreYetTopollPercent;
	
	private Long   nonCapVoters;
	private Long   nonCapVotersPolled;
	private Long   nonCapVotersYetToBePolled;
	private String nonCapVotersPollPercent;
	private String nonCapVotersYetToPollPercent;
	
	List<PollManagementSummaryVO> subList;
    
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Long getTotalVoters() {
		return totalVoters;
	}

	public void setTotalVoters(Long totalVoters) {
		this.totalVoters = totalVoters;
	}

	public String getPollPercent() {
		return pollPercent;
	}

	public void setPollPercent(String pollPercent) {
		this.pollPercent = pollPercent;
	}

	public String getYetToPollPercent() {
		return yetToPollPercent;
	}

	public void setYetToPollPercent(String yetToPollPercent) {
		this.yetToPollPercent = yetToPollPercent;
	}

	public Long getCadreCount() {
		return cadreCount;
	}

	public void setCadreCount(Long cadreCount) {
		this.cadreCount = cadreCount;
	}

	public String getCadrepollPercent() {
		return cadrepollPercent;
	}

	public void setCadrepollPercent(String cadrepollPercent) {
		this.cadrepollPercent = cadrepollPercent;
	}

	public String getCadreYetToPollPercent() {
		return cadreYetToPollPercent;
	}

	public void setCadreYetToPollPercent(String cadreYetToPollPercent) {
		this.cadreYetToPollPercent = cadreYetToPollPercent;
	}

	public Long getCapCadreCount() {
		return capCadreCount;
	}

	public void setCapCadreCount(Long capCadreCount) {
		this.capCadreCount = capCadreCount;
	}

	public String getCapCadrePollPercent() {
		return capCadrePollPercent;
	}

	public void setCapCadrePollPercent(String capCadrePollPercent) {
		this.capCadrePollPercent = capCadrePollPercent;
	}

	public String getCapCadreYetTopollPercent() {
		return capCadreYetTopollPercent;
	}

	public void setCapCadreYetTopollPercent(String capCadreYetTopollPercent) {
		this.capCadreYetTopollPercent = capCadreYetTopollPercent;
	}

	public Long getNonCapVoters() {
		return nonCapVoters;
	}

	public void setNonCapVoters(Long nonCapVoters) {
		this.nonCapVoters = nonCapVoters;
	}

	public String getNonCapVotersPollPercent() {
		return nonCapVotersPollPercent;
	}

	public void setNonCapVotersPollPercent(String nonCapVotersPollPercent) {
		this.nonCapVotersPollPercent = nonCapVotersPollPercent;
	}

	public String getNonCapVotersYetToPollPercent() {
		return nonCapVotersYetToPollPercent;
	}

	public void setNonCapVotersYetToPollPercent(String nonCapVotersYetToPollPercent) {
		this.nonCapVotersYetToPollPercent = nonCapVotersYetToPollPercent;
	}

	public List<PollManagementSummaryVO> getSubList() {
		return subList;
	}

	public void setSubList(List<PollManagementSummaryVO> subList) {
		this.subList = subList;
	}

	public Long getTotalVotersPolled() {
		return totalVotersPolled;
	}

	public void setTotalVotersPolled(Long totalVotersPolled) {
		this.totalVotersPolled = totalVotersPolled;
	}

	public Long getCadreCountPolled() {
		return cadreCountPolled;
	}

	public void setCadreCountPolled(Long cadreCountPolled) {
		this.cadreCountPolled = cadreCountPolled;
	}

	public Long getCadreCountYetToBePolled() {
		return cadreCountYetToBePolled;
	}

	public void setCadreCountYetToBePolled(Long cadreCountYetToBePolled) {
		this.cadreCountYetToBePolled = cadreCountYetToBePolled;
	}

	public Long getCapCadreCountPolled() {
		return capCadreCountPolled;
	}

	public void setCapCadreCountPolled(Long capCadreCountPolled) {
		this.capCadreCountPolled = capCadreCountPolled;
	}

	public Long getCapCadreCountYetToBePolled() {
		return capCadreCountYetToBePolled;
	}

	public void setCapCadreCountYetToBePolled(Long capCadreCountYetToBePolled) {
		this.capCadreCountYetToBePolled = capCadreCountYetToBePolled;
	}

	public Long getNonCapVotersPolled() {
		return nonCapVotersPolled;
	}

	public void setNonCapVotersPolled(Long nonCapVotersPolled) {
		this.nonCapVotersPolled = nonCapVotersPolled;
	}

	public Long getNonCapVotersYetToBePolled() {
		return nonCapVotersYetToBePolled;
	}

	public void setNonCapVotersYetToBePolled(Long nonCapVotersYetToBePolled) {
		this.nonCapVotersYetToBePolled = nonCapVotersYetToBePolled;
	}

	public Long getTotalVotersYetToBePolled() {
		return totalVotersYetToBePolled;
	}

	public void setTotalVotersYetToBePolled(Long totalVotersYetToBePolled) {
		this.totalVotersYetToBePolled = totalVotersYetToBePolled;
	}
    	
	
}
