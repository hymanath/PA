package com.itgrids.partyanalyst.dto;

import java.io.Serializable;
import java.util.List;

public class YouthLeaderSelectionVO implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 4729160048778925425L;
	private Long mandalId;
	private Long panchayatId;
	private Long boothId;
	private Long panchayatTotalVoters;
	private Long boothTotalVoters;
	private Long casteVoters;
	
	private Double casteVotersPerc;
	
	
	private String casteName;
	
	private String mandalName;
	private String panchayatName;
	private String boothName;
	private List<YouthLeaderSelectionVO> boothLevelLeadersList;
	private List<YouthLeaderSelectionVO> panchayatLevelLeadersList;
	private YouthLeaderSelectionVO youthLeaderSelectionVO;
	public Long getMandalId() {
		return mandalId;
	}
	public void setMandalId(Long mandalId) {
		this.mandalId = mandalId;
	}
	public Long getPanchayatId() {
		return panchayatId;
	}
	public void setPanchayatId(Long panchayatId) {
		this.panchayatId = panchayatId;
	}
	public Long getBoothId() {
		return boothId;
	}
	public void setBoothId(Long boothId) {
		this.boothId = boothId;
	}
	public Long getPanchayatTotalVoters() {
		return panchayatTotalVoters;
	}
	public void setPanchayatTotalVoters(Long panchayatTotalVoters) {
		this.panchayatTotalVoters = panchayatTotalVoters;
	}
	public Long getBoothTotalVoters() {
		return boothTotalVoters;
	}
	public void setBoothTotalVoters(Long boothTotalVoters) {
		this.boothTotalVoters = boothTotalVoters;
	}
	public Long getCasteVoters() {
		return casteVoters;
	}
	public void setCasteVoters(Long casteVoters) {
		this.casteVoters = casteVoters;
	}
	public Double getCasteVotersPerc() {
		return casteVotersPerc;
	}
	public void setCasteVotersPerc(Double casteVotersPerc) {
		this.casteVotersPerc = casteVotersPerc;
	}
	public String getCasteName() {
		return casteName;
	}
	public void setCasteName(String casteName) {
		this.casteName = casteName;
	}
	public String getMandalName() {
		return mandalName;
	}
	public void setMandalName(String mandalName) {
		this.mandalName = mandalName;
	}
	public String getPanchayatName() {
		return panchayatName;
	}
	public void setPanchayatName(String panchayatName) {
		this.panchayatName = panchayatName;
	}
	public String getBoothName() {
		return boothName;
	}
	public void setBoothName(String boothName) {
		this.boothName = boothName;
	}
	public List<YouthLeaderSelectionVO> getBoothLevelLeadersList() {
		return boothLevelLeadersList;
	}
	public void setBoothLevelLeadersList(
			List<YouthLeaderSelectionVO> boothLevelLeadersList) {
		this.boothLevelLeadersList = boothLevelLeadersList;
	}
	public List<YouthLeaderSelectionVO> getPanchayatLevelLeadersList() {
		return panchayatLevelLeadersList;
	}
	public void setPanchayatLevelLeadersList(
			List<YouthLeaderSelectionVO> panchayatLevelLeadersList) {
		this.panchayatLevelLeadersList = panchayatLevelLeadersList;
	}
	public YouthLeaderSelectionVO getYouthLeaderSelectionVO() {
		return youthLeaderSelectionVO;
	}
	public void setYouthLeaderSelectionVO(
			YouthLeaderSelectionVO youthLeaderSelectionVO) {
		this.youthLeaderSelectionVO = youthLeaderSelectionVO;
	}
	
	
	
	
}
