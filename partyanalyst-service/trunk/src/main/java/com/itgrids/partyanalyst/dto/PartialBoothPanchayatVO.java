package com.itgrids.partyanalyst.dto;

import java.io.Serializable;

public class PartialBoothPanchayatVO implements Serializable{

	private static final long serialVersionUID = 1L;
	private Long     partialBoothPanchayatId;
	private Long     panchayatId;
	private Long     boothId;
	private Long     partialpanchayatId;
	private Long     hamletId;
	
	private String   panchayatName;
	private String   boothName;
	private String   description;
	private String   partialPanchayatName;
	private String   partialDescription;
	private String   hamletName;
	
	
	public Long getPartialBoothPanchayatId() {
		return partialBoothPanchayatId;
	}
	public void setPartialBoothPanchayatId(Long partialBoothPanchayatId) {
		this.partialBoothPanchayatId = partialBoothPanchayatId;
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
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Long getPartialpanchayatId() {
		return partialpanchayatId;
	}
	public void setPartialpanchayatId(Long partialpanchayatId) {
		this.partialpanchayatId = partialpanchayatId;
	}
	public String getPartialPanchayatName() {
		return partialPanchayatName;
	}
	public void setPartialPanchayatName(String partialPanchayatName) {
		this.partialPanchayatName = partialPanchayatName;
	}
	public String getPartialDescription() {
		return partialDescription;
	}
	public void setPartialDescription(String partialDescription) {
		this.partialDescription = partialDescription;
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
	
	
}
