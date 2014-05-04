package com.itgrids.eliteclub.dto;

import java.util.Date;

import com.itgrids.eliteclub.model.Category;
import com.itgrids.eliteclub.model.ContentType;

public class AudioFileVo {
	
	
	private Integer fileId;
	//private ContentType contentType;
	//private Category category;
	//private byte[] content;
	private String url;
	private String description;
	//private Date created;
	//private Date updated;
	//private Character isDeleted;
	private int contentTypeId;
	
	
	
	
	public int getContentTypeId() {
		return contentTypeId;
	}
	public void setContentTypeId(int contentTypeId) {
		this.contentTypeId = contentTypeId;
	}
	public Integer getFileId() {
		return fileId;
	}
	public void setFileId(Integer fileId) {
		this.fileId = fileId;
	}
	/*public ContentType getContentType() {
		return contentType;
	}
	public void setContentType(ContentType contentType) {
		this.contentType = contentType;
	}
	public Category getCategory() {
		return category;
	}
	public void setCategory(Category category) {
		this.category = category;
	}
	public byte[] getContent() {
		return content;
	}
	public void setContent(byte[] content) {
		this.content = content;
	}*/
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	/*public Date getCreated() {
		return created;
	}
	public void setCreated(Date created) {
		this.created = created;
	}
	public Date getUpdated() {
		return updated;
	}
	public void setUpdated(Date updated) {
		this.updated = updated;
	}
	public Character getIsDeleted() {
		return isDeleted;
	}
	public void setIsDeleted(Character isDeleted) {
		this.isDeleted = isDeleted;
	}
	*/
	
	
	

}
