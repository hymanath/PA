/* 
 * Copyright (c) 2009 IT Grids.
 * All Rights Reserved.
 *
 * IT Grids Confidential Information.
 * Created on Feb, 2010
 * Author Saikrishna.g
 */

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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.LazyToOne;
import org.hibernate.annotations.LazyToOneOption;
import org.hibernate.annotations.NotFoundAction;
@Entity
@Table(name = "static_users")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class StaticUsers implements  java.io.Serializable {
/**
	 * 
	 */
	private static final long serialVersionUID = 6647665620722906944L;
private Long staticUserId;
private String name;
private String mobileNumber;
private String emailId;
private String address;
private Date updatedDate;
private Date createdDate;
private String location;
private String designation;
private String phoneNumber;
private StaticUserDesignation staticUserDesignation;
private Set<StaticUserGroup> staticUserGroups = new HashSet<StaticUserGroup>(0);
//constructors

/** default constructors*/
public StaticUsers()
{
}
/** one parameter constructors*/

public StaticUsers(Long staticUserId) {
	
	this.staticUserId = staticUserId;
}
public StaticUsers(Long staticUserId, String name, String mobileNumber,
		String emailId, String address, Date updatedDate,String location,
		String designation,Date createdDate,String phoneNumber, Set<StaticUserGroup> staticUserGroups,
		StaticUserDesignation staticUserDesignation) {
	
	this.staticUserId = staticUserId;
	this.name = name;
	this.mobileNumber = mobileNumber;
	this.emailId = emailId;
	this.address = address;
	this.updatedDate=updatedDate;
	this.location=location;
	this.designation=designation;
	this.createdDate=createdDate;
	this.phoneNumber=phoneNumber;
	this.staticUserGroups = staticUserGroups;
	this.staticUserDesignation = staticUserDesignation;
}
@Id
@GeneratedValue(strategy = GenerationType.AUTO)
@Column(name = "static_user_id", unique = true, nullable = false)
public Long getStaticUserId() {
	return staticUserId;
}
public void setStaticUserId(Long staticUserId) {
	this.staticUserId = staticUserId;
}

@Column(name = "name", length = 100)
public String getName() {
	return name;
}
public void setName(String name) {
	this.name = name;
}
@Column(name = "mobile", length=25)
public String getMobileNumber() {
	return mobileNumber;
}
public void setMobileNumber(String mobileNumber) {
	this.mobileNumber = mobileNumber;
}

@Column(name = "email", length = 100)
public String getEmailId() {
	return emailId;
}
public void setEmailId(String emailId) {
	this.emailId = emailId;
}
@Column(name = "address", length = 250)
public String getAddress() {
	return address;
}
public void setAddress(String address) {
	this.address = address;
}


@Temporal(TemporalType.TIMESTAMP)
@Column(name = "updated_date")
public Date getUpdatedDate() {
	return updatedDate;
}
public void setUpdatedDate(Date updatedDate) {
	this.updatedDate = updatedDate;
}
@Column(name = "location", length = 100)
public String getLocation() {
	return location;
}
public void setLocation(String location) {
	this.location = location;
}

@Column(name = "designation", length = 100)
public String getDesignation() {
	return designation;
}
public void setDesignation(String designation) {
	this.designation = designation;
}

@Temporal(TemporalType.TIMESTAMP)
@Column(name = "created_date")
public Date getCreatedDate() {
	return createdDate;
}
public void setCreatedDate(Date createdDate) {
	this.createdDate = createdDate;
}

@Column(name = "phone", length = 25)
public String getPhoneNumber() {
	return phoneNumber;
}
public void setPhoneNumber(String phoneNumber) {
	this.phoneNumber = phoneNumber;
}
@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "staticUser")
public Set<StaticUserGroup> getStaticUserGroups() {
	return staticUserGroups;
}
public void setStaticUserGroups(Set<StaticUserGroup> staticUserGroups) {
	this.staticUserGroups = staticUserGroups;
}

@ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
@JoinColumn(name="static_user_designation_id")
@LazyToOne(LazyToOneOption.NO_PROXY)
@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
public StaticUserDesignation getStaticUserDesignation() {
	return staticUserDesignation;
}
public void setStaticUserDesignation(StaticUserDesignation staticUserDesignation) {
	this.staticUserDesignation = staticUserDesignation;
}

}
