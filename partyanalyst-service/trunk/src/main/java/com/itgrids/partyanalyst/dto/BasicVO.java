package com.itgrids.partyanalyst.dto;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("serial")
public class BasicVO implements Serializable{

	private Long id;
	private Long count;
	private String name;
	private String mandalName;
	private String casteName;
	private Double perc;
	private List<BasicVO> selectedCasteDetails = new ArrayList<BasicVO>();
	private List<BasicVO> hamletVoterInfo = new ArrayList<BasicVO>();
	private List<BasicVO> hamletCasteInfo = new ArrayList<BasicVO>();
	private List<BasicVO> panchayatVoterInfo = new ArrayList<BasicVO>();
	private List<VoterCastInfoVO> casteList = new ArrayList<VoterCastInfoVO>();
	private Long hamletId;
	private String hamletName;
	private Long voterCount;
	private List casteNames;
	private String persent;
	private Long expCount;
	private Long levelValue;
	private Long levelId;
	public List<BasicVO> getSelectedCasteDetails() {
		return selectedCasteDetails;
	}
	public void setSelectedCasteDetails(List<BasicVO> selectedCasteDetails) {
		this.selectedCasteDetails = selectedCasteDetails;
	}
	public String getMandalName() {
		return mandalName;
	}
	public void setMandalName(String mandalName) {
		this.mandalName = mandalName;
	}
	public String getPersent() {
		return persent;
	}
	public void setPersent(String persent) {
		this.persent = persent;
	}
	public String getCasteName() {
		return casteName;
	}
	public void setCasteName(String casteName) {
		this.casteName = casteName;
	}
	public List<BasicVO> getPanchayatVoterInfo() {
		return panchayatVoterInfo;
	}
	public void setPanchayatVoterInfo(List<BasicVO> panchayatVoterInfo) {
		this.panchayatVoterInfo = panchayatVoterInfo;
	}
	public List getCasteNames() {
		return casteNames;
	}
	public void setCasteNames(List casteNames) {
		this.casteNames = casteNames;
	}
	public List<VoterCastInfoVO> getCasteList() {
		return casteList;
	}
	public void setCasteList(List<VoterCastInfoVO> casteList) {
		this.casteList = casteList;
	}
	public List<BasicVO> getHamletVoterInfo() {
		return hamletVoterInfo;
	}
	public void setHamletVoterInfo(List<BasicVO> hamletVoterInfo) {
		this.hamletVoterInfo = hamletVoterInfo;
	}
	public List<BasicVO> getHamletCasteInfo() {
		return hamletCasteInfo;
	}
	public void setHamletCasteInfo(List<BasicVO> hamletCasteInfo) {
		this.hamletCasteInfo = hamletCasteInfo;
	}
	public Long getVoterCount() {
		return voterCount;
	}
	public void setVoterCount(Long voterCount) {
		this.voterCount = voterCount;
	}
	public Long getHamletId() {
		return hamletId;
	}
	public void setHamletId(Long hamletId) {
		this.hamletId = hamletId;
	}
	public String getHamletName() {
		return hamletName;
	}
	public void setHamletName(String hamletName) {
		this.hamletName = hamletName;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Long getCount() {
		return count;
	}
	public void setCount(Long count) {
		this.count = count;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Double getPerc() {
		return perc;
	}
	public void setPerc(Double perc) {
		this.perc = perc;
	}
	public Long getExpCount() {
		return expCount;
	}
	public void setExpCount(Long expCount) {
		this.expCount = expCount;
	}
	public Long getLevelValue() {
		return levelValue;
	}
	public void setLevelValue(Long levelValue) {
		this.levelValue = levelValue;
	}
	public Long getLevelId() {
		return levelId;
	}
	public void setLevelId(Long levelId) {
		this.levelId = levelId;
	}
	
	
}
