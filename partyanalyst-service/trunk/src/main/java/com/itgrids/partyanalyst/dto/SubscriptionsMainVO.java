package com.itgrids.partyanalyst.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class SubscriptionsMainVO implements Serializable{

	List<SubscriptionsVO> profilePartySubscriptions;
	List<SubscriptionsVO> profileSpecialPageSubscriptions;
	List<SubscriptionsVO> profileCandidateSubscriptions;
	List<SubscriptionsVO> profileConstituencySubscriptions;
	List<SubscriptionsVO> userPartySubscriptions;
	List<SubscriptionsVO> userSpecialPageSubscriptions;
	List<SubscriptionsVO> userCandidateSubscriptions;
	List<SubscriptionsVO> userConstituencySubscriptions;
	private List<SelectOptionVO> partyList = new ArrayList<SelectOptionVO>(0);
	private List<SelectOptionVO> constituencyList = new ArrayList<SelectOptionVO>(0);
	
	
	public List<SubscriptionsVO> getProfilePartySubscriptions() {
		return profilePartySubscriptions;
	}
	public void setProfilePartySubscriptions(
			List<SubscriptionsVO> profilePartySubscriptions) {
		this.profilePartySubscriptions = profilePartySubscriptions;
	}
	public List<SubscriptionsVO> getProfileSpecialPageSubscriptions() {
		return profileSpecialPageSubscriptions;
	}
	public void setProfileSpecialPageSubscriptions(
			List<SubscriptionsVO> profileSpecialPageSubscriptions) {
		this.profileSpecialPageSubscriptions = profileSpecialPageSubscriptions;
	}
	public List<SubscriptionsVO> getProfileCandidateSubscriptions() {
		return profileCandidateSubscriptions;
	}
	public void setProfileCandidateSubscriptions(
			List<SubscriptionsVO> profileCandidateSubscriptions) {
		this.profileCandidateSubscriptions = profileCandidateSubscriptions;
	}
	public List<SubscriptionsVO> getProfileConstituencySubscriptions() {
		return profileConstituencySubscriptions;
	}
	public void setProfileConstituencySubscriptions(
			List<SubscriptionsVO> profileConstituencySubscriptions) {
		this.profileConstituencySubscriptions = profileConstituencySubscriptions;
	}
	public List<SubscriptionsVO> getUserPartySubscriptions() {
		return userPartySubscriptions;
	}
	public void setUserPartySubscriptions(
			List<SubscriptionsVO> userPartySubscriptions) {
		this.userPartySubscriptions = userPartySubscriptions;
	}
	public List<SubscriptionsVO> getUserSpecialPageSubscriptions() {
		return userSpecialPageSubscriptions;
	}
	public void setUserSpecialPageSubscriptions(
			List<SubscriptionsVO> userSpecialPageSubscriptions) {
		this.userSpecialPageSubscriptions = userSpecialPageSubscriptions;
	}
	public List<SubscriptionsVO> getUserCandidateSubscriptions() {
		return userCandidateSubscriptions;
	}
	public void setUserCandidateSubscriptions(
			List<SubscriptionsVO> userCandidateSubscriptions) {
		this.userCandidateSubscriptions = userCandidateSubscriptions;
	}
	public List<SubscriptionsVO> getUserConstituencySubscriptions() {
		return userConstituencySubscriptions;
	}
	public void setUserConstituencySubscriptions(
			List<SubscriptionsVO> userConstituencySubscriptions) {
		this.userConstituencySubscriptions = userConstituencySubscriptions;
	}
	public List<SelectOptionVO> getPartyList() {
		return partyList;
	}
	public void setPartyList(List<SelectOptionVO> partyList) {
		this.partyList = partyList;
	}
	public List<SelectOptionVO> getConstituencyList() {
		return constituencyList;
	}
	public void setConstituencyList(List<SelectOptionVO> constituencyList) {
		this.constituencyList = constituencyList;
	}
	
	
}
