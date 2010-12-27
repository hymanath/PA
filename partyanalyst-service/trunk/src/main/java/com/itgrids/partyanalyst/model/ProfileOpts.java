package com.itgrids.partyanalyst.model;

import java.io.Serializable;
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

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.NotFoundAction;

@Entity
@Table(name = "profile_opts")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class ProfileOpts extends BaseModel implements Serializable{

	private static final long serialVersionUID = 1L;
	private Long profileOptsId;
	private String name;
	private String description;
	private String isDisplay;
	private Set<UserProfileOpts> userProfileOptses = new HashSet<UserProfileOpts>(0);
	
	public ProfileOpts(){
		
	}
	
	public ProfileOpts(Long profileOptsId){
		this.profileOptsId = profileOptsId;
	}

	public ProfileOpts(String name, String description,
			String isDisplay) {
		this.name = name;
		this.description = description;
		this.isDisplay = isDisplay;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "profile_opts_id", unique = true, nullable = false)
	public Long getProfileOptsId() {
		return profileOptsId;
	}

	public void setProfileOptsId(Long profileOptsId) {
		this.profileOptsId = profileOptsId;
	}

	@Column(name = "name", length = 50)
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(name = "description", length = 50)
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Column(name = "is_display", length = 50)
	public String getIsDisplay() {
		return isDisplay;
	}

	public void setIsDisplay(String isDisplay) {
		this.isDisplay = isDisplay;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "profileOpts")
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public Set<UserProfileOpts> getUserProfileOptses() {
		return userProfileOptses;
	}

	public void setUserProfileOptses(Set<UserProfileOpts> userProfileOptses) {
		this.userProfileOptses = userProfileOptses;
	}
	
}
