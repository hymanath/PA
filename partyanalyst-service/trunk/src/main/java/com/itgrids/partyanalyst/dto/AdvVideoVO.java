package com.itgrids.partyanalyst.dto;

import java.io.Serializable;

public class AdvVideoVO implements Serializable{
	

	private static final long serialVersionUID = -6224528555283479856L;
	private Long   advVideoId;
	private String type;
	private String description;
	private String tags;
	private Long   duration;
	private String code;
	private String thumbnailUrl;
	private String time;
	private String title;
	public Long getAdvVideoId() {
		return advVideoId;
	}
	public void setAdvVideoId(Long advVideoId) {
		this.advVideoId = advVideoId;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getTags() {
		return tags;
	}
	public void setTags(String tags) {
		this.tags = tags;
	}
	public Long getDuration() {
		return duration;
	}
	public void setDuration(Long duration) {
		this.duration = duration;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getThumbnailUrl() {
		return thumbnailUrl;
	}
	public void setThumbnailUrl(String thumbnailUrl) {
		this.thumbnailUrl = thumbnailUrl;
	}
	
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	
	

}
