package com.itgrids.partyanalyst.dto;

import java.io.Serializable;

public class RegistrationCountVO implements Serializable {
	/**    
	 * 
	 */
	private static final long serialVersionUID = 1712029614318973289L;
	private Long mandalId;
	private String mandalName;
	private Long panchayatId;
	private String panchayatName;
	private Long boothId;
	private String boothName;
	private String localElectionBody;
	private Long localElectionBodyId = 0l;
	private Long totalVoter;
	private Long cadreCount2014 = 0l;
	private Long cadreCount2016OverAll = 0l;
	private Long cadreCount2016Today = 0l;
	private Long renewalCount = 0l;
	private Long newCount = 0l;
	public Long getMandalId() {
		return mandalId;
	}
	public void setMandalId(Long mandalId) {
		this.mandalId = mandalId;
	}
	public String getMandalName() {
		return mandalName;
	}
	public void setMandalName(String mandalName) {
		this.mandalName = mandalName;
	}
	public Long getPanchayatId() {
		return panchayatId;
	}
	public void setPanchayatId(Long panchayatId) {
		this.panchayatId = panchayatId;
	}
	public String getPanchayatName() {
		return panchayatName;
	}
	public void setPanchayatName(String panchayatName) {
		this.panchayatName = panchayatName;
	}
	public Long getBoothId() {
		return boothId;
	}
	public void setBoothId(Long boothId) {
		this.boothId = boothId;
	}
	public String getBoothName() {
		return boothName;
	}
	public void setBoothName(String boothName) {
		this.boothName = boothName;
	}
	public Long getTotalVoter() {
		return totalVoter;
	}
	public void setTotalVoter(Long totalVoter) {
		this.totalVoter = totalVoter;
	}
	public Long getCadreCount2014() {
		return cadreCount2014;
	}
	public void setCadreCount2014(Long cadreCount2014) {
		this.cadreCount2014 = cadreCount2014;
	}
	public Long getCadreCount2016OverAll() {
		return cadreCount2016OverAll;
	}
	public void setCadreCount2016OverAll(Long cadreCount2016OverAll) {
		this.cadreCount2016OverAll = cadreCount2016OverAll;
	}
	public Long getCadreCount2016Today() {
		return cadreCount2016Today;
	}
	public void setCadreCount2016Today(Long cadreCount2016Today) {
		this.cadreCount2016Today = cadreCount2016Today;
	}
	public Long getRenewalCount() {
		return renewalCount;
	}
	public void setRenewalCount(Long renewalCount) {
		this.renewalCount = renewalCount;
	}
	public Long getNewCount() {
		return newCount;
	}
	public void setNewCount(Long newCount) {
		this.newCount = newCount;
	}
	public String getLocalElectionBody() {
		return localElectionBody;
	}
	public void setLocalElectionBody(String localElectionBody) {
		this.localElectionBody = localElectionBody;
	}
	public Long getLocalElectionBodyId() {
		return localElectionBodyId;
	}
	public void setLocalElectionBodyId(Long localElectionBodyId) {
		this.localElectionBodyId = localElectionBodyId;
	}
	
}
