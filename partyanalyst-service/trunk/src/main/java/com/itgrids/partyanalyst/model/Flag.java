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
@Table(name = "flag")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)

public class Flag extends BaseModel implements Serializable {

	private Long flagId;
	private String name;
	private String description;
	private String color;
	private User user;
	//private String uniqueId;

	
	
	public Flag()
	{
		
	}
	public Flag(Long flagId,String name,String description,User user)
	{
		this.flagId = flagId;
		this.name = name;
		this.description = description;
		this.user = user;
	}
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "flag_id", unique = true, nullable = false)
	public Long getFlagId() {
		return flagId;
	}
	public void setFlagId(Long flagId) {
		this.flagId = flagId;
	}
	@Column(name = "name", length = 25)
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	@Column(name = "description", length = 45)
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	@Column(name = "color", length = 25)
	public String getColor() {
		return color;
	}
	public void setColor(String color) {
		this.color = color;
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
	
/*	@Column(name="unique_id")
	public String getUniqueId() {
		return uniqueId;
	}
	public void setUniqueId(String uniqueId) {
		this.uniqueId = uniqueId;
	}
	*/
}