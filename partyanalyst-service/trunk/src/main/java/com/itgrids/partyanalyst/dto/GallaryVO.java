package com.itgrids.partyanalyst.dto;

import java.io.Serializable;
import java.util.Date;

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
	private Long candidateId;

	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
}
