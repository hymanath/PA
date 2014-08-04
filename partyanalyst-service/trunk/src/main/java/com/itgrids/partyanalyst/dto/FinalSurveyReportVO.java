package com.itgrids.partyanalyst.dto;

import java.io.Serializable;

public class FinalSurveyReportVO implements Serializable
{

	
	private static final long serialVersionUID = -8478466611141257847L;
	private Long boothId;
	private String partNo;
	private Long totalVoters;
	
	private Long dcCasteMapped;
	private String dcCasteMappedPerc;
	
	private Long dcHamletMapped;
	private String dcHamletMappedPerc;
	
	private Long dcMobileMapped;
	
	private Long wmDcTotal;
	
	private Long wmDcMobileMapped;
	private String wmDcMobileMappedPerc;
	private String wmDcMobileMappedError;
	
	private Long wmDcMobileUnMapped;
	private String wmDcMobileUnMappedPerc;
	private String wmDcMobileUnMappedError;
	
	private Long wmDcCasteMapped;
	private String wmDcCasteMappedPerc;
	private String wmDcCasteMappedError;
	
	private Long wmDcCasteUnMapped;
	private String wmDcCasteUnMappedPerc;
	private String wmDcCasteUnMappedError; 
	
	private Long dvCasteMapped;
	private String dvCasteMappedPerc;
	private Long dvCasteUnMatched;
	
	private String dvUnMatchedPerc;
	private Long dvNotIdentifed;
	private String dvNotIdentifedPerc;
	
	private Long wmDvY = 0l;
	private Long wmDvN = 0l;
	private Long wmDvEmpty = 0l;
	
	private Integer matchedCount = 0;
	private Integer unMatchedCount = 0 ;
	private Integer notIdentifedCount = 0 ;
	
	private Integer collectedCount = 0 ;
	private Integer updatedCount = 0;
	private Integer verifiedCount = 0;
	
	
	public String getPartNo() {
		return partNo;
	}
	public void setPartNo(String partNo) {
		this.partNo = partNo;
	}
	public Long getTotalVoters() {
		return totalVoters;
	}
	public void setTotalVoters(Long totalVoters) {
		this.totalVoters = totalVoters;
	}
	public Long getDcCasteMapped() {
		return dcCasteMapped;
	}
	public void setDcCasteMapped(Long dcCasteMapped) {
		this.dcCasteMapped = dcCasteMapped;
	}
	public String getDcCasteMappedPerc() {
		return dcCasteMappedPerc;
	}
	public void setDcCasteMappedPerc(String dcCasteMappedPerc) {
		this.dcCasteMappedPerc = dcCasteMappedPerc;
	}
	public Long getDcHamletMapped() {
		return dcHamletMapped;
	}
	public void setDcHamletMapped(Long dcHamletMapped) {
		this.dcHamletMapped = dcHamletMapped;
	}
	public String getDcHamletMappedPerc() {
		return dcHamletMappedPerc;
	}
	public void setDcHamletMappedPerc(String dcHamletMappedPerc) {
		this.dcHamletMappedPerc = dcHamletMappedPerc;
	}
	public Long getDcMobileMapped() {
		return dcMobileMapped;
	}
	public void setDcMobileMapped(Long dcMobileMapped) {
		this.dcMobileMapped = dcMobileMapped;
	}
	public Long getWmDcTotal() {
		return wmDcTotal;
	}
	public void setWmDcTotal(Long wmDcTotal) {
		this.wmDcTotal = wmDcTotal;
	}
	public Long getWmDcMobileMapped() {
		return wmDcMobileMapped;
	}
	public void setWmDcMobileMapped(Long wmDcMobileMapped) {
		this.wmDcMobileMapped = wmDcMobileMapped;
	}
	public String getWmDcMobileMappedPerc() {
		return wmDcMobileMappedPerc;
	}
	public void setWmDcMobileMappedPerc(String wmDcMobileMappedPerc) {
		this.wmDcMobileMappedPerc = wmDcMobileMappedPerc;
	}
	public String getWmDcMobileMappedError() {
		return wmDcMobileMappedError;
	}
	public void setWmDcMobileMappedError(String wmDcMobileMappedError) {
		this.wmDcMobileMappedError = wmDcMobileMappedError;
	}
	public Long getWmDcMobileUnMapped() {
		return wmDcMobileUnMapped;
	}
	public void setWmDcMobileUnMapped(Long wmDcMobileUnMapped) {
		this.wmDcMobileUnMapped = wmDcMobileUnMapped;
	}
	public String getWmDcMobileUnMappedPerc() {
		return wmDcMobileUnMappedPerc;
	}
	public void setWmDcMobileUnMappedPerc(String wmDcMobileUnMappedPerc) {
		this.wmDcMobileUnMappedPerc = wmDcMobileUnMappedPerc;
	}
	public String getWmDcMobileUnMappedError() {
		return wmDcMobileUnMappedError;
	}
	public void setWmDcMobileUnMappedError(String wmDcMobileUnMappedError) {
		this.wmDcMobileUnMappedError = wmDcMobileUnMappedError;
	}
	public Long getWmDcCasteMapped() {
		return wmDcCasteMapped;
	}
	public void setWmDcCasteMapped(Long wmDcCasteMapped) {
		this.wmDcCasteMapped = wmDcCasteMapped;
	}
	public String getWmDcCasteMappedPerc() {
		return wmDcCasteMappedPerc;
	}
	public void setWmDcCasteMappedPerc(String wmDcCasteMappedPerc) {
		this.wmDcCasteMappedPerc = wmDcCasteMappedPerc;
	}
	public String getWmDcCasteMappedError() {
		return wmDcCasteMappedError;
	}
	public void setWmDcCasteMappedError(String wmDcCasteMappedError) {
		this.wmDcCasteMappedError = wmDcCasteMappedError;
	}
	public Long getWmDcCasteUnMapped() {
		return wmDcCasteUnMapped;
	}
	public void setWmDcCasteUnMapped(Long wmDcCasteUnMapped) {
		this.wmDcCasteUnMapped = wmDcCasteUnMapped;
	}
	public String getWmDcCasteUnMappedPerc() {
		return wmDcCasteUnMappedPerc;
	}
	public void setWmDcCasteUnMappedPerc(String wmDcCasteUnMappedPerc) {
		this.wmDcCasteUnMappedPerc = wmDcCasteUnMappedPerc;
	}
	public String getWmDcCasteUnMappedError() {
		return wmDcCasteUnMappedError;
	}
	public void setWmDcCasteUnMappedError(String wmDcCasteUnMappedError) {
		this.wmDcCasteUnMappedError = wmDcCasteUnMappedError;
	}
	public Long getDvCasteMapped() {
		return dvCasteMapped;
	}
	public void setDvCasteMapped(Long dvCasteMapped) {
		this.dvCasteMapped = dvCasteMapped;
	}
	public String getDvCasteMappedPerc() {
		return dvCasteMappedPerc;
	}
	public void setDvCasteMappedPerc(String dvCasteMappedPerc) {
		this.dvCasteMappedPerc = dvCasteMappedPerc;
	}
	public Long getDvCasteUnMatched() {
		return dvCasteUnMatched;
	}
	public void setDvCasteUnMatched(Long dvCasteUnMatched) {
		this.dvCasteUnMatched = dvCasteUnMatched;
	}
	public String getDvUnMatchedPerc() {
		return dvUnMatchedPerc;
	}
	public void setDvUnMatchedPerc(String dvUnMatchedPerc) {
		this.dvUnMatchedPerc = dvUnMatchedPerc;
	}
	public Long getDvNotIdentifed() {
		return dvNotIdentifed;
	}
	public void setDvNotIdentifed(Long dvNotIdentifed) {
		this.dvNotIdentifed = dvNotIdentifed;
	}
	public String getDvNotIdentifedPerc() {
		return dvNotIdentifedPerc;
	}
	public void setDvNotIdentifedPerc(String dvNotIdentifedPerc) {
		this.dvNotIdentifedPerc = dvNotIdentifedPerc;
	}
	public Long getWmDvY() {
		return wmDvY;
	}
	public void setWmDvY(Long wmDvY) {
		this.wmDvY = wmDvY;
	}
	public Long getWmDvN() {
		return wmDvN;
	}
	public void setWmDvN(Long wmDvN) {
		this.wmDvN = wmDvN;
	}
	public Long getWmDvEmpty() {
		return wmDvEmpty;
	}
	public void setWmDvEmpty(Long wmDvEmpty) {
		this.wmDvEmpty = wmDvEmpty;
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
	public Integer getNotIdentifedCount() {
		return notIdentifedCount;
	}
	public void setNotIdentifedCount(Integer notIdentifedCount) {
		this.notIdentifedCount = notIdentifedCount;
	}
	public Integer getCollectedCount() {
		return collectedCount;
	}
	public void setCollectedCount(Integer collectedCount) {
		this.collectedCount = collectedCount;
	}
	public Integer getUpdatedCount() {
		return updatedCount;
	}
	public void setUpdatedCount(Integer updatedCount) {
		this.updatedCount = updatedCount;
	}
	public Integer getVerifiedCount() {
		return verifiedCount;
	}
	public void setVerifiedCount(Integer verifiedCount) {
		this.verifiedCount = verifiedCount;
	}
	public Long getBoothId() {
		return boothId;
	}
	public void setBoothId(Long boothId) {
		this.boothId = boothId;
	}
	
	
	
}
