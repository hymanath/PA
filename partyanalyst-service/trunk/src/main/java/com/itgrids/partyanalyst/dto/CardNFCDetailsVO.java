package com.itgrids.partyanalyst.dto;

import java.io.Serializable;

public class CardNFCDetailsVO implements Serializable{


	private static final long serialVersionUID = 1L;
	
	
	private Long voterId;
	private String NfcNumber;
	private String firstCode;
	private String dataSourceType;
	private Long tdpCadreId;
	private Long userId;
	private String imageBase64String;
	private String voterName;
	
	
	
	public String getImageBase64String() {
		return imageBase64String;
	}
	public void setImageBase64String(String imageBase64String) {
		this.imageBase64String = imageBase64String;
	}
	public String getVoterName() {
		return voterName;
	}
	public void setVoterName(String voterName) {
		this.voterName = voterName;
	}
	public Long getUserId() {
		return userId;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	public Long getVoterId() {
		return voterId;
	}
	public void setVoterId(Long voterId) {
		this.voterId = voterId;
	}
	public String getNfcNumber() {
		return NfcNumber;
	}
	public void setNfcNumber(String nfcNumber) {
		NfcNumber = nfcNumber;
	}
	public String getFirstCode() {
		return firstCode;
	}
	public void setFirstCode(String firstCode) {
		this.firstCode = firstCode;
	}
	public String getDataSourceType() {
		return dataSourceType;
	}
	public void setDataSourceType(String dataSourceType) {
		this.dataSourceType = dataSourceType;
	}
	public Long getTdpCadreId() {
		return tdpCadreId;
	}
	public void setTdpCadreId(Long tdpCadreId) {
		this.tdpCadreId = tdpCadreId;
	}
	
}
