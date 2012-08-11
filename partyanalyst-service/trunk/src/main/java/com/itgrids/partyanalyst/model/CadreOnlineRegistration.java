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
@Table(name = "cadre_online_registration")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class CadreOnlineRegistration extends BaseModel implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 4467594093647833999L;
	
	private Long cadreOnlineRegistrationId;
	private String name;
	private String description;
	private User user;
	
	public CadreOnlineRegistration(){};
	public CadreOnlineRegistration(String name, String description,User user)
	{
		this.name = name;
		this.description = description;
		this.user = user;
	}
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "cadre_online_registration_id", unique = true, nullable = false)
	public Long getCadreOnlineRegistrationId() {
		return cadreOnlineRegistrationId;
	}
	public void setCadreOnlineRegistrationId(Long cadreOnlineRegistrationId) {
		this.cadreOnlineRegistrationId = cadreOnlineRegistrationId;
	}
	
	@Column(name = "name", length = 150)
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	@Column(name = "description", length = 300)
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
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
	
}
