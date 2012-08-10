package com.itgrids.partyanalyst.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;


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
	
	public CadreOnlineRegistration(){};
	public CadreOnlineRegistration(String name, String description)
	{
		this.name = name;
		this.description = description;
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
	
}
