package com.itgrids.partyanalyst.dto;

import java.io.Serializable;
import java.util.List;

public class VoterAdderdOrDeletedRengesInfoVO implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 395495529655601343L;
	private Long id;
	private String name;
	private Long bt1To50AddedCount;
	private Long bt1To50DeletedCount;
	private Long bt51To100AddedCount;
	private Long bt51To100DeletedCount;
	private Long bt101To150AddedCount;
	private Long bt101To150DeletedCount;
	private Long bt151To200Addedcount;
	private Long bt151To200DeletedCount;
	private Long bt201To300AddedCount;
	private Long bt201To300DeletedCount;
	private Long above300AddedCount;
	private Long above300DeletedCount;
	private Long noChangesAddedCount;
	private Long noChangesDeletedCount;
	
	private List<VoterAdderdOrDeletedRengesInfoVO> addedVoeterDetails;
	private List<VoterAdderdOrDeletedRengesInfoVO> deletedVoterDetails;
	private List<VoterAdderdOrDeletedRengesInfoVO> ruralUrbanDeletedVoterDetails;
	private List<VoterAdderdOrDeletedRengesInfoVO> ruralUrbanAddedVoterDetails;
	private List<PanchayatAddedOrDeletedVO> panchayWiseAddedDeletedList;
	/**
	 * @return the id
	 */
	public Long getId() {
		return id;
	}
	/**
	 * @param id the id to set
	 */
	public void setId(Long id) {
		this.id = id;
	}
	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}
	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * @return the bt1To50AddedCount
	 */
	public Long getBt1To50AddedCount() {
		return bt1To50AddedCount;
	}
	/**
	 * @param bt1To50AddedCount the bt1To50AddedCount to set
	 */
	public void setBt1To50AddedCount(Long bt1To50AddedCount) {
		this.bt1To50AddedCount = bt1To50AddedCount;
	}
	/**
	 * @return the bt1To50DeletedCount
	 */
	public Long getBt1To50DeletedCount() {
		return bt1To50DeletedCount;
	}
	/**
	 * @param bt1To50DeletedCount the bt1To50DeletedCount to set
	 */
	public void setBt1To50DeletedCount(Long bt1To50DeletedCount) {
		this.bt1To50DeletedCount = bt1To50DeletedCount;
	}
	/**
	 * @return the bt51To100AddedCount
	 */
	public Long getBt51To100AddedCount() {
		return bt51To100AddedCount;
	}
	/**
	 * @param bt51To100AddedCount the bt51To100AddedCount to set
	 */
	public void setBt51To100AddedCount(Long bt51To100AddedCount) {
		this.bt51To100AddedCount = bt51To100AddedCount;
	}
	/**
	 * @return the bt51To100DeletedCount
	 */
	public Long getBt51To100DeletedCount() {
		return bt51To100DeletedCount;
	}
	/**
	 * @param bt51To100DeletedCount the bt51To100DeletedCount to set
	 */
	public void setBt51To100DeletedCount(Long bt51To100DeletedCount) {
		this.bt51To100DeletedCount = bt51To100DeletedCount;
	}
	/**
	 * @return the bt101To150AddedCount
	 */
	public Long getBt101To150AddedCount() {
		return bt101To150AddedCount;
	}
	/**
	 * @param bt101To150AddedCount the bt101To150AddedCount to set
	 */
	public void setBt101To150AddedCount(Long bt101To150AddedCount) {
		this.bt101To150AddedCount = bt101To150AddedCount;
	}
	/**
	 * @return the bt101To150DeletedCount
	 */
	public Long getBt101To150DeletedCount() {
		return bt101To150DeletedCount;
	}
	/**
	 * @param bt101To150DeletedCount the bt101To150DeletedCount to set
	 */
	public void setBt101To150DeletedCount(Long bt101To150DeletedCount) {
		this.bt101To150DeletedCount = bt101To150DeletedCount;
	}
	/**
	 * @return the bt151To200Addedcount
	 */
	public Long getBt151To200Addedcount() {
		return bt151To200Addedcount;
	}
	/**
	 * @param bt151To200Addedcount the bt151To200Addedcount to set
	 */
	public void setBt151To200Addedcount(Long bt151To200Addedcount) {
		this.bt151To200Addedcount = bt151To200Addedcount;
	}
	/**
	 * @return the bt151To200DeletedCount
	 */
	public Long getBt151To200DeletedCount() {
		return bt151To200DeletedCount;
	}
	/**
	 * @param bt151To200DeletedCount the bt151To200DeletedCount to set
	 */
	public void setBt151To200DeletedCount(Long bt151To200DeletedCount) {
		this.bt151To200DeletedCount = bt151To200DeletedCount;
	}
	/**
	 * @return the bt201To300AddedCount
	 */
	public Long getBt201To300AddedCount() {
		return bt201To300AddedCount;
	}
	/**
	 * @param bt201To300AddedCount the bt201To300AddedCount to set
	 */
	public void setBt201To300AddedCount(Long bt201To300AddedCount) {
		this.bt201To300AddedCount = bt201To300AddedCount;
	}
	/**
	 * @return the bt201To300DeletedCount
	 */
	public Long getBt201To300DeletedCount() {
		return bt201To300DeletedCount;
	}
	/**
	 * @param bt201To300DeletedCount the bt201To300DeletedCount to set
	 */
	public void setBt201To300DeletedCount(Long bt201To300DeletedCount) {
		this.bt201To300DeletedCount = bt201To300DeletedCount;
	}
	/**
	 * @return the above300AddedCount
	 */
	public Long getAbove300AddedCount() {
		return above300AddedCount;
	}
	/**
	 * @param above300AddedCount the above300AddedCount to set
	 */
	public void setAbove300AddedCount(Long above300AddedCount) {
		this.above300AddedCount = above300AddedCount;
	}
	/**
	 * @return the above300DeletedCount
	 */
	public Long getAbove300DeletedCount() {
		return above300DeletedCount;
	}
	/**
	 * @param above300DeletedCount the above300DeletedCount to set
	 */
	public void setAbove300DeletedCount(Long above300DeletedCount) {
		this.above300DeletedCount = above300DeletedCount;
	}
	/**
	 * @return the noChangesAddedCount
	 */
	public Long getNoChangesAddedCount() {
		return noChangesAddedCount;
	}
	/**
	 * @param noChangesAddedCount the noChangesAddedCount to set
	 */
	public void setNoChangesAddedCount(Long noChangesAddedCount) {
		this.noChangesAddedCount = noChangesAddedCount;
	}
	/**
	 * @return the noChangesDeletedCount
	 */
	public Long getNoChangesDeletedCount() {
		return noChangesDeletedCount;
	}
	/**
	 * @param noChangesDeletedCount the noChangesDeletedCount to set
	 */
	public void setNoChangesDeletedCount(Long noChangesDeletedCount) {
		this.noChangesDeletedCount = noChangesDeletedCount;
	}
	/**
	 * @return the serialversionuid
	 */
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	/**
	 * @return the addedVoeterDetails
	 */
	public List<VoterAdderdOrDeletedRengesInfoVO> getAddedVoeterDetails() {
		return addedVoeterDetails;
	}
	/**
	 * @param addedVoeterDetails the addedVoeterDetails to set
	 */
	public void setAddedVoeterDetails(
			List<VoterAdderdOrDeletedRengesInfoVO> addedVoeterDetails) {
		this.addedVoeterDetails = addedVoeterDetails;
	}
	/**
	 * @return the deletedVoterDetails
	 */
	public List<VoterAdderdOrDeletedRengesInfoVO> getDeletedVoterDetails() {
		return deletedVoterDetails;
	}
	/**
	 * @param deletedVoterDetails the deletedVoterDetails to set
	 */
	public void setDeletedVoterDetails(
			List<VoterAdderdOrDeletedRengesInfoVO> deletedVoterDetails) {
		this.deletedVoterDetails = deletedVoterDetails;
	}
	/**
	 * @return the ruralUrbanDeletedVoterDetails
	 */
	public List<VoterAdderdOrDeletedRengesInfoVO> getRuralUrbanDeletedVoterDetails() {
		return ruralUrbanDeletedVoterDetails;
	}
	/**
	 * @param ruralUrbanDeletedVoterDetails the ruralUrbanDeletedVoterDetails to set
	 */
	public void setRuralUrbanDeletedVoterDetails(
			List<VoterAdderdOrDeletedRengesInfoVO> ruralUrbanDeletedVoterDetails) {
		this.ruralUrbanDeletedVoterDetails = ruralUrbanDeletedVoterDetails;
	}
	/**
	 * @return the ruralUrbanAddedVoterDetails
	 */
	public List<VoterAdderdOrDeletedRengesInfoVO> getRuralUrbanAddedVoterDetails() {
		return ruralUrbanAddedVoterDetails;
	}
	/**
	 * @param ruralUrbanAddedVoterDetails the ruralUrbanAddedVoterDetails to set
	 */
	public void setRuralUrbanAddedVoterDetails(
			List<VoterAdderdOrDeletedRengesInfoVO> ruralUrbanAddedVoterDetails) {
		this.ruralUrbanAddedVoterDetails = ruralUrbanAddedVoterDetails;
	}
	/**
	 * @return the panchayWiseAddedDeletedList
	 */
	public List<PanchayatAddedOrDeletedVO> getPanchayWiseAddedDeletedList() {
		return panchayWiseAddedDeletedList;
	}
	/**
	 * @param panchayWiseAddedDeletedList the panchayWiseAddedDeletedList to set
	 */
	public void setPanchayWiseAddedDeletedList(
			List<PanchayatAddedOrDeletedVO> panchayWiseAddedDeletedList) {
		this.panchayWiseAddedDeletedList = panchayWiseAddedDeletedList;
	}
	
	
	

}
