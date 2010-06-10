package com.itgrids.partyanalyst.dto;


public class PoliticalChangesVO implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String description;
	private String date;
	private String impact;
	private String title;
	private String name;
	private String mobile;
	private String telephoneNo;
	private String address;
	private String email;
	private String sourceOfInformation;
	private String reportedDate;
	private String identifiedDate;
	private Long partyId;
	private String partyName;
	private Long userId;
	private Long selectedPersonId;
	private Long politicalChangeId;
	private String saveType;
	private String range;
	private Long rangeId;
	private String locationName;
	
	public String getLocationName() {
		return locationName;
	}
	public void setLocationName(String locationName) {
		this.locationName = locationName;
	}
	public String getRange() {
		return range;
	}
	public void setRange(String range) {
		this.range = range;
	}
	public Long getRangeId() {
		return rangeId;
	}
	public void setRangeId(Long rangeId) {
		this.rangeId = rangeId;
	}
	public String getSaveType() {
		return saveType;
	}
	public void setSaveType(String saveType) {
		this.saveType = saveType;
	}
	public String getPartyName() {
		return partyName;
	}
	public void setPartyName(String partyName) {
		this.partyName = partyName;
	}
	public String getIdentifiedDate() {
		return identifiedDate;
	}
	public void setIdentifiedDate(String identifiedDate) {
		this.identifiedDate = identifiedDate;
	}
	public Long getPoliticalChangeId() {
		return politicalChangeId;
	}
	public void setPoliticalChangeId(Long politicalChangeId) {
		this.politicalChangeId = politicalChangeId;
	}
	public Long getSelectedPersonId() {
		return selectedPersonId;
	}
	public void setSelectedPersonId(Long selectedPersonId) {
		this.selectedPersonId = selectedPersonId;
	}
	public Long getUserId() {
		return userId;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	public Long getPartyId() {
		return partyId;
	}
	public void setPartyId(Long partyId) {
		this.partyId = partyId;
	}
		public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public String getTelephoneNo() {
		return telephoneNo;
	}
	public void setTelephoneNo(String telephoneNo) {
		this.telephoneNo = telephoneNo;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getSourceOfInformation() {
		return sourceOfInformation;
	}
	public void setSourceOfInformation(String sourceOfInformation) {
		this.sourceOfInformation = sourceOfInformation;
	}
	public String getReportedDate() {
		return reportedDate;
	}
	public void setReportedDate(String reportedDate) {
		this.reportedDate = reportedDate;
	}
		public String getDescription() {
			return description;
		}
		public void setDescription(String description) {
			this.description = description;
		}
		public String getDate() {
			return date;
		}
		public void setDate(String date) {
			this.date = date;
		}
		public String getImpact() {
			return impact;
		}
		public void setImpact(String impact) {
			this.impact = impact;
		}
 
 	}
