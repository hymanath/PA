package com.itgrids.partyanalyst.dto;

import java.util.Date;

public class AnnouncementResultsVO {
	private	long  announcementsId;
    private	String discription;
    private	String title;
    private	String firstName;
    private	String middleName;
    private	String lastName;
    private Date fromDate;
    private Date toDate;
    private long constituencyId;
    
    
    
    
	public long getConstituencyId() {
		return constituencyId;
	}
	public void setConstituencyId(long constituencyId) {
		this.constituencyId = constituencyId;
	}
	public long getAnnouncementsId() {
		return announcementsId;
	}
	public void setAnnouncementsId(long announcementsId) {
		this.announcementsId = announcementsId;
	}
	public String getDiscription() {
		return discription;
	}
	public void setDiscription(String discription) {
		this.discription = discription;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getMiddleName() {
		return middleName;
	}
	public void setMiddleName(String middleName) {
		this.middleName = middleName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public Date getFromDate() {
		return fromDate;
	}
	public void setFromDate(Date fromDate) {
		this.fromDate = fromDate;
	}
	public Date getToDate() {
		return toDate;
	}
	public void setToDate(Date toDate) {
		this.toDate = toDate;
	}
    
	
	
}
