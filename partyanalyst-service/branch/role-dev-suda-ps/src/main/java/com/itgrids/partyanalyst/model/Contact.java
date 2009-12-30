package com.itgrids.partyanalyst.model;

public class Contact extends BaseModel {

	private static final long serialVersionUID = 1L;

	private Long contactId;
	private String firstName;
	private String lastName;
	private Language language;
	private String phoneNumber;
	private boolean isSmsCapable;
	
	public Contact() {
		super();
	}

	public Contact(String firstName, String lastName, Language language,
			String phoneNumber) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.language = language;
		this.phoneNumber = phoneNumber;
	}

	public Long getContactId() {
		return contactId;
	}

	public void setContactId(Long contactId) {
		this.contactId = contactId;
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

	public Language getLanguage() {
		return language;
	}

	public void setLanguage(Language language) {
		this.language = language;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public boolean isSmsCapable() {
		return isSmsCapable;
	}

	public void setSmsCapable(boolean isSmsCapable) {
		this.isSmsCapable = isSmsCapable;
	}

	
}
