package com.itgrids.partyanalyst.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;



/**
 * The persistent class for the union_tab_user database table.
 * 
 */
@Entity
@Table(name="union_tab_user")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class UnionTabUser implements Serializable {
	private static final long serialVersionUID = 1L;
	private Long unionTabUserId;
	private String isDeleted;
	private String isEnabled;
	private String mobileNo;
	private String name;
	private String passWord;
	private String userName;

    public UnionTabUser() {
    }


	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="union_tab_user_id", unique=true, nullable=false)
	public Long getUnionTabUserId() {
		return unionTabUserId;
	}
	public void setUnionTabUserId(Long unionTabUserId) {
		this.unionTabUserId = unionTabUserId;
	}


	@Column(name="is_deleted")
	public String getIsDeleted() {
		return this.isDeleted;
	}



	public void setIsDeleted(String isDeleted) {
		this.isDeleted = isDeleted;
	}


	@Column(name="is_enabled")
	public String getIsEnabled() {
		return this.isEnabled;
	}

	public void setIsEnabled(String isEnabled) {
		this.isEnabled = isEnabled;
	}


	@Column(name="mobile_no")
	public String getMobileNo() {
		return this.mobileNo;
	}

	public void setMobileNo(String mobileNo) {
		this.mobileNo = mobileNo;
	}


	@Column(name="name")
	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}


	@Column(name="password")
	public String getPassWord() {
		return passWord;
	}
	public void setPassWord(String passWord) {
		this.passWord = passWord;
	}
	@Column(name="username")
	public String getUserName() {
		return userName;
	}


	public void setUserName(String userName) {
		this.userName = userName;
	}



}