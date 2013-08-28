package com.itgrids.partyanalyst.dto;

import java.io.Serializable;
import java.util.List;

public class VoiceSmsResponseDetailsVO implements Serializable{

	private static final long serialVersionUID = 1L;
	
	
	private Long responseId;
	private String responseCode;
	private String dateSent;
	private String numbers;
	private String description;
	private String sentStatus;
	private String userName;
	
	private List<Long> cadreMobileNumbers;
	private List<Long> influencePeopleMobileNumbers;
	private List<Long> votersMobileNumbers;
	private List<Long> allmobileNumbers;
	
	
	public List<Long> getCadreMobileNumbers() {
		return cadreMobileNumbers;
	}
	public void setCadreMobileNumbers(List<Long> cadreMobileNumbers) {
		this.cadreMobileNumbers = cadreMobileNumbers;
	}
	public List<Long> getInfluencePeopleMobileNumbers() {
		return influencePeopleMobileNumbers;
	}
	public void setInfluencePeopleMobileNumbers(
			List<Long> influencePeopleMobileNumbers) {
		this.influencePeopleMobileNumbers = influencePeopleMobileNumbers;
	}
	public List<Long> getVotersMobileNumbers() {
		return votersMobileNumbers;
	}
	public void setVotersMobileNumbers(List<Long> votersMobileNumbers) {
		this.votersMobileNumbers = votersMobileNumbers;
	}
	public List<Long> getAllmobileNumbers() {
		return allmobileNumbers;
	}
	public void setAllmobileNumbers(List<Long> allmobileNumbers) {
		this.allmobileNumbers = allmobileNumbers;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getSentStatus() {
		return sentStatus;
	}
	public void setSentStatus(String sentStatus) {
		this.sentStatus = sentStatus;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Long getResponseId() {
		return responseId;
	}
	public void setResponseId(Long responseId) {
		this.responseId = responseId;
	}
	public String getResponseCode() {
		return responseCode;
	}
	public void setResponseCode(String responseCode) {
		this.responseCode = responseCode;
	}
	public String getDateSent() {
		return dateSent;
	}
	public void setDateSent(String dateSent) {
		this.dateSent = dateSent;
	}
	public String getNumbers() {
		return numbers;
	}
	public void setNumbers(String numbers) {
		this.numbers = numbers;
	}
	

}
