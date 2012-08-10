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
@Table(name = "user_cadre_online_registration")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class UserCadreOnlineRegistration extends BaseModel implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 44577389599457915L;
	private Long userCadreOnlineRegistrationId;
	private String name;
	private String description;
	private User user;
	private CadreOnlineRegistration cadreOnlineRegistration;
	
	public UserCadreOnlineRegistration(){};
	public UserCadreOnlineRegistration(String name,String description,User user,CadreOnlineRegistration cadreOnlineRegistration)
	{
		this.name = name;
		this.description = description;
		this.user = user;
		this.cadreOnlineRegistration = cadreOnlineRegistration;
	}
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name = "user_cadre_online_registration_id", unique = true, nullable = false)
	public Long getUserCadreOnlineRegistrationId() {
		return userCadreOnlineRegistrationId;
	}
	public void setUserCadreOnlineRegistrationId(Long userCadreOnlineRegistrationId) {
		this.userCadreOnlineRegistrationId = userCadreOnlineRegistrationId;
	}
	
	@Column(name = "name", length=150)
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	@Column(name = "description",length = 300)
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	
	@ManyToOne(cascade = CascadeType.ALL,fetch= FetchType.LAZY)
	@JoinColumn(name = "user_id")
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	
	@ManyToOne(cascade = CascadeType.ALL, fetch= FetchType.LAZY)
	@JoinColumn(name = "cadre_online_registration_id")
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public CadreOnlineRegistration getCadreOnlineRegistration() {
		return cadreOnlineRegistration;
	}
	public void setCadreOnlineRegistration(
			CadreOnlineRegistration cadreOnlineRegistration) {
		this.cadreOnlineRegistration = cadreOnlineRegistration;
	}
	

}
