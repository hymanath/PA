package com.itgrids.partyanalyst.model;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
@Entity
@Table(name = "my_group")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class MyGroup implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	private  Long myGroupId;
	private String groupName;
	private String groupDescription;
	private Date createdDate;
	private Date updatedDate;
	private Set <PersonalUserGroup> personalUserGroups = new HashSet<PersonalUserGroup>(0);
	
	
	public MyGroup() {		
	}	
	
	public MyGroup(Long myGroupId, String groupName, String groupDescription,
			Date createdDate, Date updatedDate, Set <PersonalUserGroup> personalUserGroups) {
		super();
		this.myGroupId = myGroupId;
		this.groupName = groupName;
		this.groupDescription = groupDescription;
		this.createdDate = createdDate;
		this.updatedDate = updatedDate;
		this.personalUserGroups = personalUserGroups;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "my_group_id", unique = true, nullable = false)
	public Long getMyGroupId() {
		return myGroupId;
	}
	public void setMyGroupId(Long myGroupId) {
		this.myGroupId = myGroupId;
	}
	@Column(name = "group_name", length = 100)
	public String getGroupName() {
		return groupName;
	}	
	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}
	@Column(name = "group_desc", length = 250)
	public String getGroupDescription() {
		return groupDescription;
	}
	public void setGroupDescription(String groupDescription) {
		this.groupDescription = groupDescription;
	}
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "created_date")
	public Date getCreatedDate() {
		return createdDate;
	}
	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "updated_date")
	public Date getUpdatedDate() {
		return updatedDate;
	}
	public void setUpdatedDate(Date updatedDate) {
		this.updatedDate = updatedDate;
	}
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "myGroup")
	public Set<PersonalUserGroup> getPersonalUserGroups() {
		return personalUserGroups;
	}

	public void setPersonalUserGroups(Set<PersonalUserGroup> personalUserGroups) {
		this.personalUserGroups = personalUserGroups;
	}
	
	
	
	
}
