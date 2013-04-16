package com.itgrids.partyanalyst.model;

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

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.LazyToOne;
import org.hibernate.annotations.LazyToOneOption;
import org.hibernate.annotations.NotFoundAction;

@Entity
@Table(name = "user_access_ip_address")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class UserAcessIpAddress {

	private Long _userAcessIpAddressId;
	private User _userId;
	private String ipAddress;
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="user_access_ip_address_id", unique=true, nullable=false)

	public Long get_userAcessIpAddressId() {
		return _userAcessIpAddressId;
	}
	public void set_userAcessIpAddressId(Long _userAcessIpAddress) {
		this._userAcessIpAddressId = _userAcessIpAddress;
	}
	@ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
	@JoinColumn(name="user_id")
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public User get_userId() {
		return _userId;
	}
	public void set_userId(User _userId) {
		this._userId = _userId;
	}
	@Column(name = "ip_address", length = 20)
	public String getIpAddress() {
		return ipAddress;
	}
	public void setIpAddress(String ipAddress) {
		this.ipAddress = ipAddress;
	}
	
	

}
