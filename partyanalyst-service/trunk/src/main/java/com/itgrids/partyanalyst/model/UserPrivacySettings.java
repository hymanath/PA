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
@Table(name = "user_privacy_settings")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class UserPrivacySettings implements Serializable{
	private Long userPrivacySettingsId;
	
	
	private Content content;
	
	private SettingsOption settingsOption;
	
	private User user;
	//Default Constructor
	public UserPrivacySettings()
	{
		
	}
	//Full Constructor
	public UserPrivacySettings(Long userPrivacySettingsId,User user,SettingsOption settingsOption)
	{
	this.userPrivacySettingsId = userPrivacySettingsId;
	this.user=user;
	this.settingsOption = settingsOption;
	}
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "user_privacy_settings_id", unique = true, nullable = false)
	public Long getUserPrivacySettingsId() {
		return userPrivacySettingsId;
	}

	public void setUserPrivacySettingsId(Long userPrivacySettingsId) {
		this.userPrivacySettingsId = userPrivacySettingsId;
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
	@ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
	@JoinColumn(name="content_id")
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public Content getContent() {
		return content;
	}

	public void setContent(Content content) {
		this.content = content;
	}
	@ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
	@JoinColumn(name="settings_option_id")
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public SettingsOption getSettingsOption() {
		return settingsOption;
	}

	public void setSettingsOption(SettingsOption settingsOption) {
		this.settingsOption = settingsOption;
	}
	
	
	

}
