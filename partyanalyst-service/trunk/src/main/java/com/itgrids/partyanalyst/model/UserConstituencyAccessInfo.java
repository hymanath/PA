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
@Table(name = "user_constituency_access_info")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class UserConstituencyAccessInfo extends BaseModel{

	private static final long serialVersionUID = 1L;
	private Long userConstituencyAccessInfoId;
	private Constituency constituency ;
	private User user;
	private Long userId;
	private Long constituencyId;
	
	public UserConstituencyAccessInfo(){}
	
	public UserConstituencyAccessInfo(Long userId, Long constituencyId) {
		this.userId = userId;
		this.constituencyId = constituencyId;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "user_constituency_access_info_id", unique = true, nullable = false)
	public Long getUserConstituencyAccessInfoId() {
		return userConstituencyAccessInfoId;
	}

	public void setUserConstituencyAccessInfoId(Long userConstituencyAccessInfoId) {
		this.userConstituencyAccessInfoId = userConstituencyAccessInfoId;
	}

	@ManyToOne(cascade=CascadeType.ALL,fetch = FetchType.LAZY)
	@JoinColumn(name = "constituency_id", insertable = false, updatable = false)
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public Constituency getConstituency() {
		return constituency;
	}
	
	public void setConstituency(Constituency constituency) {
		this.constituency = constituency;
	}

	@ManyToOne(cascade=CascadeType.ALL,fetch=FetchType.LAZY)
	@JoinColumn(name = "user_id", insertable = false, updatable = false)
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	@Column(name = "user_id", length = 10)
	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	@Column(name = "constituency_id", length = 10)
	public Long getConstituencyId() {
		return constituencyId;
	}

	public void setConstituencyId(Long constituencyId) {
		this.constituencyId = constituencyId;
	}
	
	

}
