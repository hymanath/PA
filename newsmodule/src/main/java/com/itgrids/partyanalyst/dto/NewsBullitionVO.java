package com.itgrids.partyanalyst.dto;

import java.io.Serializable;
import java.util.Date;

public class NewsBullitionVO implements Serializable{

	private Long channelId;
	private Date newsBullitionTime;
	private Long minutes;
	private Long seconds;
	private String newsBullitionDescription;
	private String isHeadLine;
	private Long newsBullitionTypeId;
	private Date inserted_time;
	private Date update_time;
	private String isDeleted;
	
	private Long newsBullitionNewsTypeId;
	
	private Long districtId;
	private Long mandalId;
	private Long villageId;
	public Long getChannelId() {
		return channelId;
	}
	public void setChannelId(Long channelId) {
		this.channelId = channelId;
	}
	public Date getNewsBullitionTime() {
		return newsBullitionTime;
	}
	public void setNewsBullitionTime(Date newsBullitionTime) {
		this.newsBullitionTime = newsBullitionTime;
	}
	public Long getMinutes() {
		return minutes;
	}
	public void setMinutes(Long minutes) {
		this.minutes = minutes;
	}
	public Long getSeconds() {
		return seconds;
	}
	public void setSeconds(Long seconds) {
		this.seconds = seconds;
	}
	public String getNewsBullitionDescription() {
		return newsBullitionDescription;
	}
	public void setNewsBullitionDescription(String newsBullitionDescription) {
		this.newsBullitionDescription = newsBullitionDescription;
	}
	
	public Long getNewsBullitionTypeId() {
		return newsBullitionTypeId;
	}
	public void setNewsBullitionTypeId(Long newsBullitionTypeId) {
		this.newsBullitionTypeId = newsBullitionTypeId;
	}
	public Date getInserted_time() {
		return inserted_time;
	}
	public void setInserted_time(Date inserted_time) {
		this.inserted_time = inserted_time;
	}
	public Date getUpdate_time() {
		return update_time;
	}
	public void setUpdate_time(Date update_time) {
		this.update_time = update_time;
	}
	
	public Long getNewsBullitionNewsTypeId() {
		return newsBullitionNewsTypeId;
	}
	public void setNewsBullitionNewsTypeId(Long newsBullitionNewsTypeId) {
		this.newsBullitionNewsTypeId = newsBullitionNewsTypeId;
	}
	public Long getDistrictId() {
		return districtId;
	}
	public void setDistrictId(Long districtId) {
		this.districtId = districtId;
	}
	public Long getMandalId() {
		return mandalId;
	}
	public void setMandalId(Long mandalId) {
		this.mandalId = mandalId;
	}
	public Long getVillageId() {
		return villageId;
	}
	public void setVillageId(Long villageId) {
		this.villageId = villageId;
	}
	public String getIsHeadLine() {
		return isHeadLine;
	}
	public void setIsHeadLine(String isHeadLine) {
		this.isHeadLine = isHeadLine;
	}
	public String getIsDeleted() {
		return isDeleted;
	}
	public void setIsDeleted(String isDeleted) {
		this.isDeleted = isDeleted;
	}
	
	
	
}
