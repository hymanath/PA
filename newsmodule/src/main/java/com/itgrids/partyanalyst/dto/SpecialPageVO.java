package com.itgrids.partyanalyst.dto;

public class SpecialPageVO {

	private Long specialPageId;
	private String title;
	private String heading;
	private String eventImagePath;
	private Long orderNo;
	private String description;
	private Long specialPageDescriptionId;
	
	public Long getSpecialPageDescriptionId() {
		return specialPageDescriptionId;
	}
	public void setSpecialPageDescriptionId(Long specialPageDescriptionId) {
		this.specialPageDescriptionId = specialPageDescriptionId;
	}
	public Long getOrderNo() {
		return orderNo;
	}
	public void setOrderNo(Long orderNo) {
		this.orderNo = orderNo;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
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
