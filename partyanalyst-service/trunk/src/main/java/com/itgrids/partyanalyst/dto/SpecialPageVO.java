package com.itgrids.partyanalyst.dto;

public class SpecialPageVO {

	private Long specialPageId;
	private String title;
	private String heading;
	private String eventImagePath;
	
	public Long getSpecialPageId() {
		return specialPageId;
	}
	public String getTitle() {
		return title;
	}
	public String getHeading() {
		return heading;
	}
	public String getEventImagePath() {
		return eventImagePath;
	}
	public void setSpecialPageId(Long specialPageId) {
		this.specialPageId = specialPageId;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public void setHeading(String heading) {
		this.heading = heading;
	}
	public void setEventImagePath(String eventImagePath) {
		this.eventImagePath = eventImagePath;
	}
}
