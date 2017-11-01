package com.itgrids.dto;

public class WaterTanksClorinationVO {
	
	private String sessionToken;
	private String sessionId;
	private Long id;
	private String name;
	private String firstName;
	private String lastName;
	private String email;
	private String isSysAdmin;
	private String lastLoginDate;
	private String host;
	private Long checked = 0L;
	private Long clorinated = 0L;
	private Long notClorinated = 0L;
	
	
	public String getSessionToken() {
		return sessionToken;
	}
	public void setSessionToken(String sessionToken) {
		this.sessionToken = sessionToken;
	}
	public String getSessionId() {
		return sessionId;
	}
	public void setSessionId(String sessionId) {
		this.sessionId = sessionId;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getIsSysAdmin() {
		return isSysAdmin;
	}
	public void setIsSysAdmin(String isSysAdmin) {
		this.isSysAdmin = isSysAdmin;
	}
	public String getLastLoginDate() {
		return lastLoginDate;
	}
	public void setLastLoginDate(String lastLoginDate) {
		this.lastLoginDate = lastLoginDate;
	}
	public String getHost() {
		return host;
	}
	public void setHost(String host) {
		this.host = host;
	}
	public Long getChecked() {
		return checked;
	}
	public void setChecked(Long checked) {
		this.checked = checked;
	}
	public Long getClorinated() {
		return clorinated;
	}
	public void setClorinated(Long clorinated) {
		this.clorinated = clorinated;
	}
	public Long getNotClorinated() {
		return notClorinated;
	}
	public void setNotClorinated(Long notClorinated) {
		this.notClorinated = notClorinated;
	}
}
