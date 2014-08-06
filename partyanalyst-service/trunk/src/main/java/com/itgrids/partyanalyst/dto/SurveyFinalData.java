package com.itgrids.partyanalyst.dto;

import java.io.Serializable;
import java.util.Date;

import com.itgrids.partyanalyst.model.CasteState;
import com.itgrids.partyanalyst.model.Constituency;
import com.itgrids.partyanalyst.model.Hamlet;
import com.itgrids.partyanalyst.model.Voter;

public class SurveyFinalData implements Serializable
{

	
	private static final long serialVersionUID = -7007064771731428343L;
	private Long surveyFinalDataIid;
	private Long voterId;
	private Voter voter;
	
	private String mobileNo;
	
	private String isCadre;
	private String isInfluencingPeople;
	
	
	private Long casteStateId;
	private CasteState casteState;
	private String casteName;
	
	private Long hamletId;
	private Hamlet hamlet;
	private String hamletName;
	
	private Long wardId;
	private Constituency ward;
	
	private String localArea;
	
	private Date insertedTime;
	private Date updatedTime;
	
	private SurveyWmThirdPartyStatus surveyWmThirdPartyStatus;
	private Long surveyWmThirdPartyStatusId;
	
	
	public Long getSurveyFinalDataIid() {
		return surveyFinalDataIid;
	}
	public void setSurveyFinalDataIid(Long surveyFinalDataIid) {
		this.surveyFinalDataIid = surveyFinalDataIid;
	}
	public Long getVoterId() {
		return voterId;
	}
	public void setVoterId(Long voterId) {
		this.voterId = voterId;
	}
	public Voter getVoter() {
		return voter;
	}
	public void setVoter(Voter voter) {
		this.voter = voter;
	}
	public String getMobileNo() {
		return mobileNo;
	}
	public void setMobileNo(String mobileNo) {
		this.mobileNo = mobileNo;
	}
	public String getIsCadre() {
		return isCadre;
	}
	public void setIsCadre(String isCadre) {
		this.isCadre = isCadre;
	}
	public String getIsInfluencingPeople() {
		return isInfluencingPeople;
	}
	public void setIsInfluencingPeople(String isInfluencingPeople) {
		this.isInfluencingPeople = isInfluencingPeople;
	}
	public Long getCasteStateId() {
		return casteStateId;
	}
	public void setCasteStateId(Long casteStateId) {
		this.casteStateId = casteStateId;
	}
	public CasteState getCasteState() {
		return casteState;
	}
	public void setCasteState(CasteState casteState) {
		this.casteState = casteState;
	}
	public String getCasteName() {
		return casteName;
	}
	public void setCasteName(String casteName) {
		this.casteName = casteName;
	}
	public Long getHamletId() {
		return hamletId;
	}
	public void setHamletId(Long hamletId) {
		this.hamletId = hamletId;
	}
	public Hamlet getHamlet() {
		return hamlet;
	}
	public void setHamlet(Hamlet hamlet) {
		this.hamlet = hamlet;
	}
	public String getHamletName() {
		return hamletName;
	}
	public void setHamletName(String hamletName) {
		this.hamletName = hamletName;
	}
	public Long getWardId() {
		return wardId;
	}
	public void setWardId(Long wardId) {
		this.wardId = wardId;
	}
	public Constituency getWard() {
		return ward;
	}
	public void setWard(Constituency ward) {
		this.ward = ward;
	}
	public String getLocalArea() {
		return localArea;
	}
	public void setLocalArea(String localArea) {
		this.localArea = localArea;
	}
	public Date getInsertedTime() {
		return insertedTime;
	}
	public void setInsertedTime(Date insertedTime) {
		this.insertedTime = insertedTime;
	}
	public Date getUpdatedTime() {
		return updatedTime;
	}
	public void setUpdatedTime(Date updatedTime) {
		this.updatedTime = updatedTime;
	}
	public SurveyWmThirdPartyStatus getSurveyWmThirdPartyStatus() {
		return surveyWmThirdPartyStatus;
	}
	public void setSurveyWmThirdPartyStatus(
			SurveyWmThirdPartyStatus surveyWmThirdPartyStatus) {
		this.surveyWmThirdPartyStatus = surveyWmThirdPartyStatus;
	}
	public Long getSurveyWmThirdPartyStatusId() {
		return surveyWmThirdPartyStatusId;
	}
	public void setSurveyWmThirdPartyStatusId(Long surveyWmThirdPartyStatusId) {
		this.surveyWmThirdPartyStatusId = surveyWmThirdPartyStatusId;
	}
	
	
	
}
