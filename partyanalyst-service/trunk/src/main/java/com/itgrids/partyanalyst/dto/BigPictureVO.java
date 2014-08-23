package com.itgrids.partyanalyst.dto;

import java.io.Serializable;

public class BigPictureVO implements Serializable
{

	private String name;
	private Long id;
	
	private Integer dcVotersCount= 0;
	private Integer dcBoothsCount= 0;
	private Integer dcConstituencysCount= 0;

	private Integer verifierVotersCount = 0;
	private Integer verifierBoothsCount = 0;
	private Integer verifierConstituencyCount = 0;
	
	private Integer qcVotersCount= 0;
	private Integer qcBoothsCount= 0;
	private Integer qcConstituencyesCount= 0;
	
	private String dcPercentage;
	private String verifierPercentage;
	private String qcPercentage;
	
	private Integer totalVoters= 0;
	private Integer totalBooths= 0;
	private Integer totalConstituencyes= 0;
	
	private Integer correctDetails= 0;
	private Integer wrongDetails= 0;
	
	private String correctPerc;
	private String wrongPerc;
	
	private Integer redoBooths= 0;
	private Integer redoVoters= 0;
	
	private Integer matchedCount= 0;
	private Integer unMatchedCount= 0;
	
	private String matchedPerc;
	private String unMatchedPerc;
	
	public Integer getDcVotersCount() {
		return dcVotersCount;
	}
	public void setDcVotersCount(Integer dcVotersCount) {
		this.dcVotersCount = dcVotersCount;
	}
	public Integer getDcBoothsCount() {
		return dcBoothsCount;
	}
	public void setDcBoothsCount(Integer dcBoothsCount) {
		this.dcBoothsCount = dcBoothsCount;
	}
	public Integer getDcConstituencysCount() {
		return dcConstituencysCount;
	}
	public void setDcConstituencysCount(Integer dcConstituencysCount) {
		this.dcConstituencysCount = dcConstituencysCount;
	}
	public Integer getVerifierVotersCount() {
		return verifierVotersCount;
	}
	public void setVerifierVotersCount(Integer verifierVotersCount) {
		this.verifierVotersCount = verifierVotersCount;
	}
	public Integer getVerifierBoothsCount() {
		return verifierBoothsCount;
	}
	public void setVerifierBoothsCount(Integer verifierBoothsCount) {
		this.verifierBoothsCount = verifierBoothsCount;
	}
	public Integer getVerifierConstituencyCount() {
		return verifierConstituencyCount;
	}
	public void setVerifierConstituencyCount(Integer verifierConstituencyCount) {
		this.verifierConstituencyCount = verifierConstituencyCount;
	}
	public Integer getQcVotersCount() {
		return qcVotersCount;
	}
	public void setQcVotersCount(Integer qcVotersCount) {
		this.qcVotersCount = qcVotersCount;
	}
	public Integer getQcBoothsCount() {
		return qcBoothsCount;
	}
	public void setQcBoothsCount(Integer qcBoothsCount) {
		this.qcBoothsCount = qcBoothsCount;
	}
	public Integer getQcConstituencyesCount() {
		return qcConstituencyesCount;
	}
	public void setQcConstituencyesCount(Integer qcConstituencyesCount) {
		this.qcConstituencyesCount = qcConstituencyesCount;
	}
	public String getDcPercentage() {
		return dcPercentage;
	}
	public void setDcPercentage(String dcPercentage) {
		this.dcPercentage = dcPercentage;
	}
	public String getVerifierPercentage() {
		return verifierPercentage;
	}
	public void setVerifierPercentage(String verifierPercentage) {
		this.verifierPercentage = verifierPercentage;
	}
	public String getQcPercentage() {
		return qcPercentage;
	}
	public void setQcPercentage(String qcPercentage) {
		this.qcPercentage = qcPercentage;
	}
	public Integer getTotalVoters() {
		return totalVoters;
	}
	public void setTotalVoters(Integer totalVoters) {
		this.totalVoters = totalVoters;
	}
	public Integer getTotalBooths() {
		return totalBooths;
	}
	public void setTotalBooths(Integer totalBooths) {
		this.totalBooths = totalBooths;
	}
	public Integer getTotalConstituencyes() {
		return totalConstituencyes;
	}
	public void setTotalConstituencyes(Integer totalConstituencyes) {
		this.totalConstituencyes = totalConstituencyes;
	}
	public Integer getCorrectDetails() {
		return correctDetails;
	}
	public void setCorrectDetails(Integer correctDetails) {
		this.correctDetails = correctDetails;
	}
	public Integer getWrongDetails() {
		return wrongDetails;
	}
	public void setWrongDetails(Integer wrongDetails) {
		this.wrongDetails = wrongDetails;
	}
	public String getCorrectPerc() {
		return correctPerc;
	}
	public void setCorrectPerc(String correctPerc) {
		this.correctPerc = correctPerc;
	}
	public String getWrongPerc() {
		return wrongPerc;
	}
	public void setWrongPerc(String wrongPerc) {
		this.wrongPerc = wrongPerc;
	}
	
	
	public Integer getRedoBooths() {
		return redoBooths;
	}
	public void setRedoBooths(Integer redoBooths) {
		this.redoBooths = redoBooths;
	}
	public Integer getRedoVoters() {
		return redoVoters;
	}
	public void setRedoVoters(Integer redoVoters) {
		this.redoVoters = redoVoters;
	}
	public Integer getMatchedCount() {
		return matchedCount;
	}
	public void setMatchedCount(Integer matchedCount) {
		this.matchedCount = matchedCount;
	}
	public Integer getUnMatchedCount() {
		return unMatchedCount;
	}
	public void setUnMatchedCount(Integer unMatchedCount) {
		this.unMatchedCount = unMatchedCount;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getMatchedPerc() {
		return matchedPerc;
	}
	public void setMatchedPerc(String matchedPerc) {
		this.matchedPerc = matchedPerc;
	}
	public String getUnMatchedPerc() {
		return unMatchedPerc;
	}
	public void setUnMatchedPerc(String unMatchedPerc) {
		this.unMatchedPerc = unMatchedPerc;
	}
	
	
	
	
}
