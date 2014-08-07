package com.itgrids.partyanalyst.dto;

import java.io.Serializable;
import java.util.Date;

public class ThirdPartyCompressionVO implements Serializable
{
	
	private static final long serialVersionUID = 1745654716686337359L;
	private Long surveyUserId;
	private Long surveyorId;
	private Long voterId;
	private Long casteId;
	private Long hamletId;
	private Long wardId;
	private String casteName = null;
	private String hamletName = null;
	private String localArea = null;
	private Date date;
	private Long boothId;
	
	private String voterCardNo;
	
	
	private String isCadre ;
	private String isInfluencingPeople ;
	private String mobileNo ;
	
	private String voterName;
	private String relativeName;
	private String gender;
	private Long age;
	private String houseNo;
	
	private Long tpCasteStateId;
	private Long wmCasteStateId;
	
	private String wmCaste;
	private String tpCaste;
	
	private Long tphamletId;
	private Long wmHamletId;
	
	
	private String tpHamlet;
	private String wmHamlet;
	
	private Long tpWard;
	private Long wmWard;
	
	
	private String status;
	
	public Long getSurveyUserId() {
		return surveyUserId;
	}
	public void setSurveyUserId(Long surveyUserId) {
		this.surveyUserId = surveyUserId;
	}
	public Long getSurveyorId() {
		return surveyorId;
	}
	public void setSurveyorId(Long surveyorId) {
		this.surveyorId = surveyorId;
	}
	public Long getVoterId() {
		return voterId;
	}
	public void setVoterId(Long voterId) {
		this.voterId = voterId;
	}
	public Long getCasteId() {
		return casteId;
	}
	public void setCasteId(Long casteId) {
		this.casteId = casteId;
	}
	public Long getHamletId() {
		return hamletId;
	}
	public void setHamletId(Long hamletId) {
		this.hamletId = hamletId;
	}
	public Long getWardId() {
		return wardId;
	}
	public void setWardId(Long wardId) {
		this.wardId = wardId;
	}
	public String getCasteName() {
		return casteName;
	}
	public void setCasteName(String casteName) {
		this.casteName = casteName;
	}
	public String getHamletName() {
		return hamletName;
	}
	public void setHamletName(String hamletName) {
		this.hamletName = hamletName;
	}
	public String getLocalArea() {
		return localArea;
	}
	public void setLocalArea(String localArea) {
		this.localArea = localArea;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public Long getBoothId() {
		return boothId;
	}
	public void setBoothId(Long boothId) {
		this.boothId = boothId;
	}
	public String getVoterCardNo() {
		return voterCardNo;
	}
	public void setVoterCardNo(String voterCardNo) {
		this.voterCardNo = voterCardNo;
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
	public String getMobileNo() {
		return mobileNo;
	}
	public void setMobileNo(String mobileNo) {
		this.mobileNo = mobileNo;
	}
	public String getVoterName() {
		return voterName;
	}
	public void setVoterName(String voterName) {
		this.voterName = voterName;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public Long getAge() {
		return age;
	}
	public void setAge(Long age) {
		this.age = age;
	}
	public String getHouseNo() {
		return houseNo;
	}
	public void setHouseNo(String houseNo) {
		this.houseNo = houseNo;
	}
	public String getWmCaste() {
		return wmCaste;
	}
	public void setWmCaste(String wmCaste) {
		this.wmCaste = wmCaste;
	}
	public String getTpCaste() {
		return tpCaste;
	}
	public void setTpCaste(String tpCaste) {
		this.tpCaste = tpCaste;
	}
	public String getRelativeName() {
		return relativeName;
	}
	public void setRelativeName(String relativeName) {
		this.relativeName = relativeName;
	}
	public String getTpHamlet() {
		return tpHamlet;
	}
	public void setTpHamlet(String tpHamlet) {
		this.tpHamlet = tpHamlet;
	}
	public String getWmHamlet() {
		return wmHamlet;
	}
	public void setWmHamlet(String wmHamlet) {
		this.wmHamlet = wmHamlet;
	}
	public Long getTpWard() {
		return tpWard;
	}
	public void setTpWard(Long tpWard) {
		this.tpWard = tpWard;
	}
	public Long getWmWard() {
		return wmWard;
	}
	public void setWmWard(Long wmWard) {
		this.wmWard = wmWard;
	}
	public Long getTpCasteStateId() {
		return tpCasteStateId;
	}
	public void setTpCasteStateId(Long tpCasteStateId) {
		this.tpCasteStateId = tpCasteStateId;
	}
	public Long getWmCasteStateId() {
		return wmCasteStateId;
	}
	public void setWmCasteStateId(Long wmCasteStateId) {
		this.wmCasteStateId = wmCasteStateId;
	}
	public Long getTphamletId() {
		return tphamletId;
	}
	public void setTphamletId(Long tphamletId) {
		this.tphamletId = tphamletId;
	}
	public Long getWmHamletId() {
		return wmHamletId;
	}
	public void setWmHamletId(Long wmHamletId) {
		this.wmHamletId = wmHamletId;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	
	
	


}
