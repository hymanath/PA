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
@Table(name = "user_profile_opts")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class UserProfileOpts extends BaseModel implements Serializable{

	private static final long serialVersionUID = 1L;
	private Long userProfileOptsId;
	private Registration user;
	private ProfileOpts profileOpts;
	
	public UserProfileOpts(){
		
	}
	
	public UserProfileOpts(Long userProfileOptsId){
		this.userProfileOptsId = userProfileOptsId;
	}
	
	public UserProfileOpts(Registration user,
			ProfileOpts profileOpts) {
		this.user = user;
		this.profileOpts = profileOpts;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "user_profile_opts_id", unique = true, nullable = false)
	public Long getUserProfileOptsId() {
		return userProfileOptsId;
	}

	public void setUserProfileOptsId(Long userProfileOptsId) {
		this.userProfileOptsId = userProfileOptsId;
	}

	@ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
	@JoinColumn(name="user_id")
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public Registration getUser() {
		return user;
	}

	public void setUser(Registration user) {
		this.user = user;
	}

	@ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
	@JoinColumn(name="profile_opts_id")
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public ProfileOpts getProfileOpts() {
		return profileOpts;
	}

	public void setProfileOpts(ProfileOpts profileOpts) {
		this.profileOpts = profileOpts;
	}
	
}
