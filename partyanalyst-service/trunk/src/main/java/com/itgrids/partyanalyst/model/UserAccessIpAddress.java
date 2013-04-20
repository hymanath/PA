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

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.LazyToOne;
import org.hibernate.annotations.LazyToOneOption;
import org.hibernate.annotations.NotFoundAction;

@Entity
@Table(name = "user_access_ip_address")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class UserAccessIpAddress  extends BaseModel implements Serializable{

	private static final long serialVersionUID = 487218426412629176L;
	
	private Long userAccessIpAddressId;
	private User user;
	private String ipAddress;
	
	public UserAccessIpAddress()
	{}
	
	public UserAccessIpAddress(User user, String ipAddress)
	{
		this.user = user;
		this.ipAddress = ipAddress;
	}


	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="user_access_ip_address_id", unique=true, nullable=false)
	public Long getUserAccessIpAddressId() {
		return userAccessIpAddressId;
	}

	public void setUserAccessIpAddressId(Long userAccessIpAddressId) {
		this.userAccessIpAddressId = userAccessIpAddressId;
	}

	@ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
	@JoinColumn(name="user_id")
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	@Column(name="ip_address",length=20)
	public String getIpAddress() {
		return ipAddress;
	}

	public void setIpAddress(String ipAddress) {
		this.ipAddress = ipAddress;
	}
	

}
