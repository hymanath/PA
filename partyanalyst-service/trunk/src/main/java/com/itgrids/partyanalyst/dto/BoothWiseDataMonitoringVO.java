/**
 * @author Sravanth
 * Oct 17, 2016
 * FieldMonitoringVO.java
 * PA - SERV - com.itgrids.partyanalyst.dto
 */
package com.itgrids.partyanalyst.dto;

/**
 * @author Sravanth
 * @date Oct 17, 2016
 *
 */
public class BoothWiseDataMonitoringVO {

	private Long boothId;
	private String partNo;
	private Long mandalId;
	private String mandal;
	private Long lebId;
	private String leb;
	private Long panchayatId;
	private String panchayat;
	private Long totalCount = 0l;
	private Long approvedCount = 0l;
	private Long rejectedCount = 0l;
	private Long pendingCount = 0l;
	
	
	public Long getBoothId() {
		return boothId;
	}
	public void setBoothId(Long boothId) {
		this.boothId = boothId;
	}
	public String getPartNo() {
		return partNo;
	}
	public void setPartNo(String partNo) {
		this.partNo = partNo;
	}
	public Long getMandalId() {
		return mandalId;
	}
	public void setMandalId(Long mandalId) {
		this.mandalId = mandalId;
	}
	public String getMandal() {
		return mandal;
	}
	public void setMandal(String mandal) {
		this.mandal = mandal;
	}
	public Long getLebId() {
		return lebId;
	}
	public void setLebId(Long lebId) {
		this.lebId = lebId;
	}
	public String getLeb() {
		return leb;
	}
	public void setLeb(String leb) {
		this.leb = leb;
	}
	public Long getPanchayatId() {
		return panchayatId;
	}
	public void setPanchayatId(Long panchayatId) {
		this.panchayatId = panchayatId;
	}
	public String getPanchayat() {
		return panchayat;
	}
	public void setPanchayat(String panchayat) {
		this.panchayat = panchayat;
	}
	public Long getTotalCount() {
		return totalCount;
	}
	public void setTotalCount(Long totalCount) {
		this.totalCount = totalCount;
	}
	public Long getApprovedCount() {
		return approvedCount;
	}
	public void setApprovedCount(Long approvedCount) {
		this.approvedCount = approvedCount;
	}
	public Long getRejectedCount() {
		return rejectedCount;
	}
	public void setRejectedCount(Long rejectedCount) {
		this.rejectedCount = rejectedCount;
	}
	public Long getPendingCount() {
		return pendingCount;
	}
	public void setPendingCount(Long pendingCount) {
		this.pendingCount = pendingCount;
	}
}
