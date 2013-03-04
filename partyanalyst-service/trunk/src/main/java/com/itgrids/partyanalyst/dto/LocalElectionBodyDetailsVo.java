package com.itgrids.partyanalyst.dto;

import java.util.ArrayList;
import java.util.List;

public class LocalElectionBodyDetailsVo {
	
	private List<TeshilPartyInfoVO> mptcDetails = new ArrayList<TeshilPartyInfoVO>();
	private List<TeshilPartyInfoVO> zptcDetails = new ArrayList<TeshilPartyInfoVO>();
	private List<TeshilPartyInfoVO> muncipalityDetails = new ArrayList<TeshilPartyInfoVO>();
	private List<TeshilPartyInfoVO> corprationDetails = new ArrayList<TeshilPartyInfoVO>();
	private List<TeshilPartyInfoVO> ghmcDetails = new ArrayList<TeshilPartyInfoVO>();
	
	public List<TeshilPartyInfoVO> getMptcDetails() {
		return mptcDetails;
	}
	public void setMptcDetails(List<TeshilPartyInfoVO> mptcDetails) {
		this.mptcDetails = mptcDetails;
	}
	public List<TeshilPartyInfoVO> getZptcDetails() {
		return zptcDetails;
	}
	public void setZptcDetails(List<TeshilPartyInfoVO> zptcDetails) {
		this.zptcDetails = zptcDetails;
	}
	public List<TeshilPartyInfoVO> getMuncipalityDetails() {
		return muncipalityDetails;
	}
	public void setMuncipalityDetails(List<TeshilPartyInfoVO> muncipalityDetails) {
		this.muncipalityDetails = muncipalityDetails;
	}
	public List<TeshilPartyInfoVO> getCorprationDetails() {
		return corprationDetails;
	}
	public void setCorprationDetails(List<TeshilPartyInfoVO> corprationDetails) {
		this.corprationDetails = corprationDetails;
	}
	public List<TeshilPartyInfoVO> getGhmcDetails() {
		return ghmcDetails;
	}
	public void setGhmcDetails(List<TeshilPartyInfoVO> ghmcDetails) {
		this.ghmcDetails = ghmcDetails;
	}
	
	

}
