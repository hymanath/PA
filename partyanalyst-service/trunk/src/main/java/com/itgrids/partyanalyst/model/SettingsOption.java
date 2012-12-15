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
@Table(name = "settings_option")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class SettingsOption implements Serializable{
	
	private Long settingsOptionId;
	private String optionName;
	private String description;
	private Long orderNo;
	
	
	private Set<UserPrivacySettings> userPrivacySetting = new HashSet<UserPrivacySettings>(0);
	// Default Constructors
	public SettingsOption()
	{
	}
	
	//Full Constructor
	public SettingsOption(Long settingsOptionId,String optionName,String description,Long orderNo)
	{
	this.settingsOptionId = settingsOptionId;
	this.optionName = optionName;
	this.description = description;
	this.orderNo = orderNo;
	}
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "settings_option_id", unique = true, nullable = false)
	public Long getSettingsOptionId() {
		return settingsOptionId;
	}
	public void setSettingsOptionId(Long settingsOptionId) {
		this.settingsOptionId = settingsOptionId;
	}
	@Column(name="option_name",length=20)
	public String getOptionName() {
		return optionName;
	}
	public void setOptionName(String optionName) {
		this.optionName = optionName;
	}
	@Column(name="description",length=200)
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	@Column(name="order_no")
	public Long getOrderNo() {
		return orderNo;
	}
	public void setOrderNo(Long orderNo) {
		this.orderNo = orderNo;
	}  
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "settingsOption")
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public Set<UserPrivacySettings> getUserPrivacySetting() {
		return userPrivacySetting;
	}
	public void setUserPrivacySetting(Set<UserPrivacySettings> userPrivacySetting) {
		this.userPrivacySetting = userPrivacySetting;
	}
	
}
