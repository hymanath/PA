package com.itgrids.partyanalyst.helper;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.itgrids.partyanalyst.dto.ResultStatus;

/**
 * @author Kamalakar Dandu
 */

public class GallaryVO extends ResultStatus implements Serializable{

	private static final long serialVersionUID = 2570682950385479277L;
	
	private Long gallaryId;
	private String gallaryName;
	private String description;
	private Date createdDate;
	private Date updatedDate;
	private String visibility;
	private String contentType;
	private Long candidateId;
	private Long partyId;
	private Long userId;
	private Long orderNo;
	private Long PartyProfileDescriptionId;
	private String isPrivate;
	private List<FileVO> filesList = new ArrayList<FileVO>(0);
	private String pageName;
	private Long pageId;
	private String customPageName;
	private Long customPageType;
	
	
	
	public Long getPageId() {
		return pageId;
	}
	public void setPageId(Long pageId) {
		this.pageId = pageId;
	}
	public String getCustomPageName() {
		return customPageName;
	}
	public void setCustomPageName(String customPageName) {
		this.customPageName = customPageName;
	}
	public Long getCustomPageType() {
		return customPageType;
	}
	public void setCustomPageType(Long customPageType) {
		this.customPageType = customPageType;
	}
	public String getPageName() {
		return pageName;
	}
	public void setPageName(String pageName) {
		this.pageName = pageName;
	}
	public String getIsPrivate() {
		return isPrivate;
	}
	public void setIsPrivate(String isPrivate) {
		this.isPrivate = isPrivate;
	}
	public Long getPartyProfileDescriptionId() {
		return PartyProfileDescriptionId;
	}
	public void setPartyProfileDescriptionId(Long partyProfileDescriptionId) {
		PartyProfileDescriptionId = partyProfileDescriptionId;
	}
	public Long getOrderNo() {
		return orderNo;
	}
	public void setOrderNo(Long orderNo) {
		this.orderNo = orderNo;
	}
	public List<FileVO> getFilesList() {
		return filesList;
	}
	public void setFilesList(List<FileVO> filesList) {
		this.filesList = filesList;
	}
	public Long getUserId() {
		return userId;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	public String getContentType() {
		return contentType;
	}
	public void setContentType(String contentType) {
		this.contentType = contentType;
	}
	public Long getGallaryId() {
		return gallaryId;
	}
	public void setGallaryId(Long gallaryId) {
		this.gallaryId = gallaryId;
	}
	public String getGallaryName() {
		return gallaryName;
	}
	public void setGallaryName(String gallaryName) {
		this.gallaryName = gallaryName;
	}
	public Date getCreatedDate() {
		return createdDate;
	}
	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}
	public Date getUpdatedDate() {
		return updatedDate;
	}
	public void setUpdatedDate(Date updatedDate) {
		this.updatedDate = updatedDate;
	}
	public String getVisibility() {
		return visibility;
	}
	public void setVisibility(String visibility) {
		this.visibility = visibility;
	}
	public Long getCandidateId() {
		return candidateId;
	}
	public void setCandidateId(Long candidateId) {
		this.candidateId = candidateId;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Long getPartyId() {
		return partyId;
	}
	public void setPartyId(Long partyId) {
		this.partyId = partyId;
	}
	
}
