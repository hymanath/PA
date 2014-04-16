package com.itgrids.eliteclub.dto;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class CategoriesVo {
	
	
	private Integer categoryId;
	private String categoryName;
	//private Date created;
	//private Date updated;
	//private Character isDeleted;
	private List<AudioFileVo> files = new ArrayList<AudioFileVo>(0);
	
	
	public List<AudioFileVo> getFiles() {
		return files;
	}
	public void setFiles(List<AudioFileVo> files) {
		this.files = files;
	}
	public Integer getCategoryId()  {
		return categoryId;
	}
	public void setCategoryId(Integer categoryId) {
		this.categoryId = categoryId;
	}
	public String getCategoryName() {
		return categoryName;
	}
	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
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
	}*/
	
	
	

}
