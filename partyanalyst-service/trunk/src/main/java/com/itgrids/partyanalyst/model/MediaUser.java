/* 
 * Copyright (c) 2010 IT Grids.
 * All Rights Reserved.
 *
 * IT Grids Confidential Information.
 * Created on August 10,2010
 */
package com.itgrids.partyanalyst.model;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.LazyToOne;
import org.hibernate.annotations.LazyToOneOption;
import org.hibernate.annotations.NotFoundAction;

@Entity
@Table(name = "media_user")
public class MediaUser extends BaseModel implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Long mediaUserId;
	private User user;
	private String firstName;
	private String middleName;
	private String lastName;
	private String userName;
	private String password;
	private String mobileNo;
	private String email;
	private String address;
	
	/** Default Constructor */
	public MediaUser(){
	 super();	
	}
	
	/** Parameterized Constructor */
	public MediaUser(Long mediaUserId, User user, String firstName,
			String middleName, String lastName, String userName,
			String password, String mobileNo, String email, String address) {
		super();
		this.mediaUserId = mediaUserId;
		this.user = user;
		this.firstName = firstName;
		this.middleName = middleName;
		this.lastName = lastName;
		this.userName = userName;
		this.password = password;
		this.mobileNo = mobileNo;
		this.email = email;
		this.address = address;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "media_user_id", unique = true, nullable = false)
	public Long getMediaUserId() {
		return mediaUserId;
	}

	public void setMediaUserId(Long mediaUserId) {
		this.mediaUserId = mediaUserId;
	}

	@ManyToOne(cascade=CascadeType.ALL,fetch = FetchType.LAZY)
	@JoinColumn(name = "user_id")
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	@Column(name = "first_name", length = 25)
	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	@Column(name = "middle_name", length = 25)
	public String getMiddleName() {
		return middleName;
	}

	public void setMiddleName(String middleName) {
		this.middleName = middleName;
	}

	@Column(name = "last_name", length = 25)
	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	@Column(name = "user_name", length = 25)
	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	@Column(name = "password", length = 25)
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Column(name = "mobile_number", length = 25)
	public String getMobileNo() {
		return mobileNo;
	}

	public void setMobileNo(String mobileNo) {
		this.mobileNo = mobileNo;
	}

	@Column(name = "email", length = 25)
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Column(name = "address", length = 250)
	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

}
