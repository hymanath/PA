package com.itgrids.partyanalyst.helper;

import java.io.Serializable;
import java.util.List;

public class ContentDetailsVO implements Serializable{

	private static final long serialVersionUID = 2886030089182642020L;
	
	private List<GallaryVO> relatedGalleries;
	private List<GallaryVO> otherGalleries;
	private String contentType;
	
	public String getContentType() {
		return contentType;
	}
	public void setContentType(String contentType) {
		this.contentType = contentType;
	}
	public List<GallaryVO> getRelatedGalleries() {
		return relatedGalleries;
	}
	public void setRelatedGalleries(List<GallaryVO> relatedGalleries) {
		this.relatedGalleries = relatedGalleries;
	}
	public List<GallaryVO> getOtherGalleries() {
		return otherGalleries;
	}
	public void setOtherGalleries(List<GallaryVO> otherGalleries) {
		this.otherGalleries = otherGalleries;
	}
}
