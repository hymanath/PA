package com.itgrids.partyanalyst.dto;

import java.io.Serializable;

public class MeetingBasicDetailsVO implements Serializable {
	private Long partyMeetingId;
	private String partyMeetingName;
	private String status;
	private Long invited;
	private Long attended;
	private Long late;
	private Long absent;
	private Long nonInvitee;
	private Long planned;
	private Long conducted;
	private String conductedPerc;
	private Long nonConducted;
	private String nonConductedPerc;
	private String imagesCovered;
	private Long totalImages;
	
	
	
	public Long getPlanned() {
		return planned;
	}
	public void setPlanned(Long planned) {
		this.planned = planned;
	}
	public Long getConducted() {
		return conducted;
	}
	public void setConducted(Long conducted) {
		this.conducted = conducted;
	}
	public String getConductedPerc() {
		return conductedPerc;
	}
	public void setConductedPerc(String conductedPerc) {
		this.conductedPerc = conductedPerc;
	}
	public Long getNonConducted() {
		return nonConducted;
	}
	public void setNonConducted(Long nonConducted) {
		this.nonConducted = nonConducted;
	}
	public String getNonConductedPerc() {
		return nonConductedPerc;
	}
	public void setNonConductedPerc(String nonConductedPerc) {
		this.nonConductedPerc = nonConductedPerc;
	}
	public Long getPartyMeetingId() {
		return partyMeetingId;
	}
	public void setPartyMeetingId(Long partyMeetingId) {
		this.partyMeetingId = partyMeetingId;
	}
	public String getPartyMeetingName() {
		return partyMeetingName;
	}
	public void setPartyMeetingName(String partyMeetingName) {
		this.partyMeetingName = partyMeetingName;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public Long getInvited() {
		return invited;
	}
	public void setInvited(Long invited) {
		this.invited = invited;
	}
	public Long getAttended() {
		return attended;
	}
	public void setAttended(Long attended) {
		this.attended = attended;
	}
	public Long getLate() {
		return late;
	}
	public void setLate(Long late) {
		this.late = late;
	}
	public Long getAbsent() {
		return absent;
	}
	public void setAbsent(Long absent) {
		this.absent = absent;
	}
	public Long getNonInvitee() {
		return nonInvitee;
	}
	public void setNonInvitee(Long nonInvitee) {
		this.nonInvitee = nonInvitee;
	}
	public String getImagesCovered() {
		return imagesCovered;
	}
	public void setImagesCovered(String imagesCovered) {
		this.imagesCovered = imagesCovered;
	}
	public Long getTotalImages() {
		return totalImages;
	}
	public void setTotalImages(Long totalImages) {
		this.totalImages = totalImages;
	}
}
