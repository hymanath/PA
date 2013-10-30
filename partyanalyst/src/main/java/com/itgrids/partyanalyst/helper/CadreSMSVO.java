package com.itgrids.partyanalyst.helper;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.itgrids.partyanalyst.dto.SelectOptionVO;

public class CadreSMSVO implements Serializable{


	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private List<SelectOptionVO> states = new ArrayList<SelectOptionVO>();
	private List<SelectOptionVO> districts = new ArrayList<SelectOptionVO>();
	private List<SelectOptionVO> constituencies = new ArrayList<SelectOptionVO>();
	private List<SelectOptionVO> mandals = new ArrayList<SelectOptionVO>();
	private List<SelectOptionVO> villages = new ArrayList<SelectOptionVO>();
	private List<SelectOptionVO> regions = new ArrayList<SelectOptionVO>();
	private List<SelectOptionVO> partyCommitee = new ArrayList<SelectOptionVO>();
	private List<SelectOptionVO> commiteeDesignation = new ArrayList<SelectOptionVO>();
	private List<SelectOptionVO> parliamentConstituencys = new ArrayList<SelectOptionVO>();
	
	public List<SelectOptionVO> getStates() {
		return states;
	}
	public void setStates(List<SelectOptionVO> states) {
		this.states = states;
	}
	public List<SelectOptionVO> getDistricts() {
		return districts;
	}
	public void setDistricts(List<SelectOptionVO> districts) {
		this.districts = districts;
	}
	public List<SelectOptionVO> getConstituencies() {
		return constituencies;
	}
	public void setConstituencies(List<SelectOptionVO> constituencies) {
		this.constituencies = constituencies;
	}
	public List<SelectOptionVO> getMandals() {
		return mandals;
	}
	public void setMandals(List<SelectOptionVO> mandals) {
		this.mandals = mandals;
	}
	public List<SelectOptionVO> getVillages() {
		return villages;
	}
	public void setVillages(List<SelectOptionVO> villages) {
		this.villages = villages;
	}
	public List<SelectOptionVO> getRegions() {
		return regions;
	}
	public void setRegions(List<SelectOptionVO> regions) {
		this.regions = regions;
	}
	public List<SelectOptionVO> getPartyCommitee() {
		return partyCommitee;
	}
	public void setPartyCommitee(List<SelectOptionVO> partyCommitee) {
		this.partyCommitee = partyCommitee;
	}
	public List<SelectOptionVO> getCommiteeDesignation() {
		return commiteeDesignation;
	}
	public void setCommiteeDesignation(List<SelectOptionVO> commiteeDesignation) {
		this.commiteeDesignation = commiteeDesignation;
	}
	public List<SelectOptionVO> getParliamentConstituencys() {
		return parliamentConstituencys;
	}
	public void setParliamentConstituencys(
			List<SelectOptionVO> parliamentConstituencys) {
		this.parliamentConstituencys = parliamentConstituencys;
	}
	
	
	
}
