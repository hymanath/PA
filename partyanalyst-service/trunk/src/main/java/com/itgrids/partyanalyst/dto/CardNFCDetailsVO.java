package com.itgrids.partyanalyst.dto;

import java.io.Serializable;

public class CardNFCDetailsVO implements Serializable{


	private static final long serialVersionUID = 1L;
	
	
	private Long voterId;
	private String NfcNumber;
	private String firstCode;
	private String dataSourceType;
	private Long tdpCadreId;
	
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
