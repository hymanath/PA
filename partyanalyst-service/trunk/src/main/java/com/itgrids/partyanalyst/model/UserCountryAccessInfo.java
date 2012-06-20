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
@Table(name = "user_country_access_info")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class UserCountryAccessInfo extends BaseModel{

	private static final long serialVersionUID = 1L;
	private Long userCountryAccessInfoId;
	private Country country ;
	private User user;
	
	public UserCountryAccessInfo(){
		
	}
	
	public UserCountryAccessInfo(Long userCountryAccessInfoId,
			User user, Country country) {
		super();
		this.userCountryAccessInfoId = userCountryAccessInfoId;
		this.user = user;
		this.country = country;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "user_country_access_info_id", unique = true, nullable = false)
	public Long getUserCountryAccessInfoId() {
		return userCountryAccessInfoId;
	}

	public void setUserCountryAccessInfoId(Long userCountryAccessInfoId) {
		this.userCountryAccessInfoId = userCountryAccessInfoId;
	}

	@ManyToOne(cascade=CascadeType.ALL,fetch = FetchType.LAZY)
	@JoinColumn(name = "country_id")
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public Country getCountry() {
		return country;
		
	}

	public void setCountry(Country country) {
		this.country = country;
	}

	@ManyToOne(cascade=CascadeType.ALL,fetch=FetchType.LAZY)
	@JoinColumn(name="user_id")
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
	
	
	
}
