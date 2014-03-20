package com.itgrids.partyanalyst.dto;

import java.util.List;

public class HouseHoldsVO implements java.io.Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String familiRange ;
	private String familyCount;
	private String familyPercentage;
	private List<HouseHoldsVO> houseHoldsVOList = null;
	private String message;
	private String calcMessage;
	private String heading;
	
	public String getHeading() {
		return heading;
	}
	public void setHeading(String heading) {
		this.heading = heading;
	}
	public String getCalcMessage() {
		return calcMessage;
	}
	public void setCalcMessage(String calcMessage) {
		this.calcMessage = calcMessage;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public String getFamiliRange() {
		return familiRange;
	}
	public void setFamiliRange(String familiRange) {
		this.familiRange = familiRange;
	}
	public String getFamilyCount() {
		return familyCount;
	}
	public void setFamilyCount(String familyCount) {
		this.familyCount = familyCount;
	}
	public String getFamilyPercentage() {
		return familyPercentage;
	}
	public void setFamilyPercentage(String familyPercentage) {
		this.familyPercentage = familyPercentage;
	}
	public List<HouseHoldsVO> getHouseHoldsVOList() {
		return houseHoldsVOList;
	}
	public void setHouseHoldsVOList(List<HouseHoldsVO> houseHoldsVOList) {
		this.houseHoldsVOList = houseHoldsVOList;
	}

	
}
