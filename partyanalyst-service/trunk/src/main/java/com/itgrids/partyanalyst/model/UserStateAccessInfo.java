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
@Table(name = "user_state_access_info")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class UserStateAccessInfo extends BaseModel implements Serializable{

	private static final long serialVersionUID = 1L;
	private Long userStateAccessInfoId;
	private Registration user;
	private State state ;
	
	public UserStateAccessInfo(){
		
	}
	
	public UserStateAccessInfo(Long userStateAccessInfoId,
			Registration user, State state) {
		super();
		this.userStateAccessInfoId = userStateAccessInfoId;
		this.user = user;
		this.state = state;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "user_state_access_info_id", unique = true, nullable = false)
	public Long getUserStateAccessInfoId() {
		return userStateAccessInfoId;
	}

	public void setUserStateAccessInfoId(Long userStateAccessInfoId) {
		this.userStateAccessInfoId = userStateAccessInfoId;
	}

	@ManyToOne(cascade=CascadeType.ALL,fetch = FetchType.LAZY)
	@JoinColumn(name = "user_id")
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public Registration getUser() {
		return user;
	}

	public void setUser(Registration user) {
		this.user = user;
	}
	
	@ManyToOne(cascade=CascadeType.ALL,fetch = FetchType.LAZY)
	@JoinColumn(name = "state_id")
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public State getState() {
		return state;
	}

	public void setState(State state) {
		this.state = state;
	}
	

}
