package com.itgrids.partyanalyst.model;

import java.io.Serializable;
import java.util.Date;

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
@Table(name = "information_manager")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class InformationManager extends BaseModel implements Serializable{

	private static final long serialVersionUID = 1L;

	private Long informationManagerId;
	
	private String firstName;
	private String lastName;
	private String mobile;
	private String designation;
	private String email;
	private User user;
	private InformationManager parentUser;
	
	private RegionScopes regionScopes;
	private Long locationValue;
	private Date createdTime;
	
	public InformationManager()
	{
		
	}
	public InformationManager(Long informationManagerId,String firstName,String lastName,
			String email,User user,InformationManager parentUser, RegionScopes regionScopes,Long locationValue,Date createdTime)
	{
	this.informationManagerId = informationManagerId;
	this.firstName =firstName;
	this.lastName =lastName;
	this.email =email;
	this.user =user;
	this.parentUser = parentUser;
	this.regionScopes =regionScopes;
	this.locationValue =locationValue;
	this.createdTime =createdTime;
	}
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="information_manager_id", unique=true, nullable=false)
	public Long getInformationManagerId() {
		return informationManagerId;
	}
	public void setInformationManagerId(Long informationManagerId) {
		this.informationManagerId = informationManagerId;
	}
	@Column(name = "firstname", length = 70)
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	@Column(name = "lastname", length = 70)
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	@Column(name = "mobile", length = 25)
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	@Column(name = "designation", length = 45)
	public String getDesignation() {
		return designation;
	}
	public void setDesignation(String designation) {
		this.designation = designation;
	}
	@Column(name = "email", length = 50)
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
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
	@JoinColumn(name="parent_user_id")
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public InformationManager getParentUser() {
		return parentUser;
	}
	public void setParentUser(InformationManager parentUser) {
		this.parentUser = parentUser;
	}
	
	@ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
	@JoinColumn(name="region_scopes_id")
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public RegionScopes getRegionScopes() {
		return regionScopes;
	}
	public void setRegionScopes(RegionScopes regionScopes) {
		this.regionScopes = regionScopes;
	}
	@Column(name = "location_value", length = 15)
	public Long getLocationValue() {
		return locationValue;
	}
	public void setLocationValue(Long locationValue) {
		this.locationValue = locationValue;
	}
	@Column(name = "created_time", length = 10)
	public Date getCreatedTime() {
		return createdTime;
	}
	public void setCreatedTime(Date createdTime) {
		this.createdTime = createdTime;
	}
	
	
}
