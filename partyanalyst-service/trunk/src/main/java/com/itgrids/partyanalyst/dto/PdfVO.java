package com.itgrids.partyanalyst.dto;

import java.io.Serializable;
import java.util.List;

import com.itgrids.partyanalyst.excel.booth.VoterModificationAgeRangeVO;
import com.itgrids.partyanalyst.excel.booth.VoterVO;
import com.itgrids.partyanalyst.model.VoterModificationAgeInfo;

public class PdfVO implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -6112785313725138141L;
	
	private List<VoterAdderdOrDeletedRengesInfoVO> addedvoterList;
	private List<VoterAdderdOrDeletedRengesInfoVO> deletedvoterList;
	private List<VoterAdderdOrDeletedRengesInfoVO> ruralUrbanDeletedvoterList;
	private List<VoterAdderdOrDeletedRengesInfoVO> ruralUrbanAddedvoterList;
	
	private List<VoterVO> addedVoterDetails;
	private List<VoterVO> deletedVoterDetils;
	
	private List<VotersDetailsVO> addedVoterDetailsVoList;
	private List<VotersDetailsVO> deletedVoterDetailsVoList;
	private List<VotersDetailsVO> completeVoterDetailsVoList;
	
	private List<VotersDetailsVO> addedDetaildByPerc;
	private List<VotersDetailsVO> deletedDetaildByPerc;
	
	private List<VoterModificationAgeRangeVO> ageRangeVOList;
	/**
	 * @return the addedvoterList
	 */
	public List<VoterAdderdOrDeletedRengesInfoVO> getAddedvoterList() {
		return addedvoterList;
	}
	/**
	 * @param addedvoterList the addedvoterList to set
	 */
	public void setAddedvoterList(
			List<VoterAdderdOrDeletedRengesInfoVO> addedvoterList) {
		this.addedvoterList = addedvoterList;
	}
	/**
	 * @return the deletedvoterList
	 */
	public List<VoterAdderdOrDeletedRengesInfoVO> getDeletedvoterList() {
		return deletedvoterList;
	}
	/**
	 * @param deletedvoterList the deletedvoterList to set
	 */
	public void setDeletedvoterList(
			List<VoterAdderdOrDeletedRengesInfoVO> deletedvoterList) {
		this.deletedvoterList = deletedvoterList;
	}
	/**
	 * @return the ruralUrbanDeletedvoterList
	 */
	public List<VoterAdderdOrDeletedRengesInfoVO> getRuralUrbanDeletedvoterList() {
		return ruralUrbanDeletedvoterList;
	}
	/**
	 * @param ruralUrbanDeletedvoterList the ruralUrbanDeletedvoterList to set
	 */
	public void setRuralUrbanDeletedvoterList(
			List<VoterAdderdOrDeletedRengesInfoVO> ruralUrbanDeletedvoterList) {
		this.ruralUrbanDeletedvoterList = ruralUrbanDeletedvoterList;
	}
	/**
	 * @return the ruralUrbanAddedvoterList
	 */
	public List<VoterAdderdOrDeletedRengesInfoVO> getRuralUrbanAddedvoterList() {
		return ruralUrbanAddedvoterList;
	}
	/**
	 * @param ruralUrbanAddedvoterList the ruralUrbanAddedvoterList to set
	 */
	public void setRuralUrbanAddedvoterList(
			List<VoterAdderdOrDeletedRengesInfoVO> ruralUrbanAddedvoterList) {
		this.ruralUrbanAddedvoterList = ruralUrbanAddedvoterList;
	}
	/**
	 * @return the addedVoterDetails
	 */
	public List<VoterVO> getAddedVoterDetails() {
		return addedVoterDetails;
	}
	/**
	 * @param addedVoterDetails the addedVoterDetails to set
	 */
	public void setAddedVoterDetails(List<VoterVO> addedVoterDetails) {
		this.addedVoterDetails = addedVoterDetails;
	}
	/**
	 * @return the deletedVoterDetils
	 */
	public List<VoterVO> getDeletedVoterDetils() {
		return deletedVoterDetils;
	}
	/**
	 * @param deletedVoterDetils the deletedVoterDetils to set
	 */
	public void setDeletedVoterDetils(List<VoterVO> deletedVoterDetils) {
		this.deletedVoterDetils = deletedVoterDetils;
	}
	/**
	 * @return the addedVoterDetailsVoList
	 */
	public List<VotersDetailsVO> getAddedVoterDetailsVoList() {
		return addedVoterDetailsVoList;
	}
	/**
	 * @param addedVoterDetailsVoList the addedVoterDetailsVoList to set
	 */
	public void setAddedVoterDetailsVoList(
			List<VotersDetailsVO> addedVoterDetailsVoList) {
		this.addedVoterDetailsVoList = addedVoterDetailsVoList;
	}
	/**
	 * @return the deletedVoterDetailsVoList
	 */
	public List<VotersDetailsVO> getDeletedVoterDetailsVoList() {
		return deletedVoterDetailsVoList;
	}
	/**
	 * @param deletedVoterDetailsVoList the deletedVoterDetailsVoList to set
	 */
	public void setDeletedVoterDetailsVoList(
			List<VotersDetailsVO> deletedVoterDetailsVoList) {
		this.deletedVoterDetailsVoList = deletedVoterDetailsVoList;
	}
	/**
	 * @return the completeVoterDetailsVoList
	 */
	public List<VotersDetailsVO> getCompleteVoterDetailsVoList() {
		return completeVoterDetailsVoList;
	}
	/**
	 * @param completeVoterDetailsVoList the completeVoterDetailsVoList to set
	 */
	public void setCompleteVoterDetailsVoList(
			List<VotersDetailsVO> completeVoterDetailsVoList) {
		this.completeVoterDetailsVoList = completeVoterDetailsVoList;
	}
	/**
	 * @return the addedDetaildByPerc
	 */
	public List<VotersDetailsVO> getAddedDetaildByPerc() {
		return addedDetaildByPerc;
	}
	/**
	 * @param addedDetaildByPerc the addedDetaildByPerc to set
	 */
	public void setAddedDetaildByPerc(List<VotersDetailsVO> addedDetaildByPerc) {
		this.addedDetaildByPerc = addedDetaildByPerc;
	}
	/**
	 * @return the deletedDetaildByPerc
	 */
	public List<VotersDetailsVO> getDeletedDetaildByPerc() {
		return deletedDetaildByPerc;
	}
	/**
	 * @param deletedDetaildByPerc the deletedDetaildByPerc to set
	 */
	public void setDeletedDetaildByPerc(List<VotersDetailsVO> deletedDetaildByPerc) {
		this.deletedDetaildByPerc = deletedDetaildByPerc;
	}
	/**
	 * @return the ageRangeVOList
	 */
	public List<VoterModificationAgeRangeVO> getAgeRangeVOList() {
		return ageRangeVOList;
	}
	/**
	 * @param ageRangeVOList the ageRangeVOList to set
	 */
	public void setAgeRangeVOList(List<VoterModificationAgeRangeVO> ageRangeVOList) {
		this.ageRangeVOList = ageRangeVOList;
	}
	
	
	
	
}
