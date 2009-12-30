package com.itgrids.partyanalyst.model;

public class ContactGroup extends BaseModel {

	private static final long serialVersionUID = 1L;
	
	private Long groupId;
	private Contact[] contacts;
	private ContactGroup[] subGroups;
	
	public ContactGroup() {
		super();
	}

	public ContactGroup(Long groupId) {
		super();
		this.groupId = groupId;
	}

	public Long getGroupId() {
		return groupId;
	}

	public void setGroupId(Long groupId) {
		this.groupId = groupId;
	}

	public Contact[] getContacts() {
		return contacts;
	}

	public void setContacts(Contact[] contacts) {
		this.contacts = contacts;
	}

	public ContactGroup[] getSubGroups() {
		return subGroups;
	}

	public void setSubGroups(ContactGroup[] subGroups) {
		this.subGroups = subGroups;
	}
	
	
	
}
