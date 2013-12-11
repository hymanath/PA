package com.itgrids.partyanalyst.dto;

import java.io.Serializable;

public class PanchayatAddedOrDeletedVO implements Serializable{

	private Long panchayatId;
	private Long mandalId;
	private Long addedCount;
	private Long deletedCount;
	private Long total;
	private String panchayatName;
	private String mandalName;
	/**
	 * @return the panchayatId
	 */
	public Long getPanchayatId() {
		return panchayatId;
	}
	/**
	 * @param panchayatId the panchayatId to set
	 */
	public void setPanchayatId(Long panchayatId) {
		this.panchayatId = panchayatId;
	}
	/**
	 * @return the mandalId
	 */
	public Long getMandalId() {
		return mandalId;
	}
	/**
	 * @param mandalId the mandalId to set
	 */
	public void setMandalId(Long mandalId) {
		this.mandalId = mandalId;
	}
	/**
	 * @return the addedCount
	 */
	public Long getAddedCount() {
		return addedCount;
	}
	/**
	 * @param addedCount the addedCount to set
	 */
	public void setAddedCount(Long addedCount) {
		this.addedCount = addedCount;
	}
	/**
	 * @return the deletedCount
	 */
	public Long getDeletedCount() {
		return deletedCount;
	}
	/**
	 * @param deletedCount the deletedCount to set
	 */
	public void setDeletedCount(Long deletedCount) {
		this.deletedCount = deletedCount;
	}
	/**
	 * @return the total
	 */
	public Long getTotal() {
		return total;
	}
	/**
	 * @param total the total to set
	 */
	public void setTotal(Long total) {
		this.total = total;
	}
	/**
	 * @return the panchayatName
	 */
	public String getPanchayatName() {
		return panchayatName;
	}
	/**
	 * @param panchayatName the panchayatName to set
	 */
	public void setPanchayatName(String panchayatName) {
		this.panchayatName = panchayatName;
	}
	/**
	 * @return the mandalName
	 */
	public String getMandalName() {
		return mandalName;
	}
	/**
	 * @param mandalName the mandalName to set
	 */
	public void setMandalName(String mandalName) {
		this.mandalName = mandalName;
	}
	
	
}
