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

/**
 * gallary entity. 
 * @author <a href="mailto:kamalakardandu@gmail.com">Kamalakar Dandu</a>
 */

@Entity
@Table(name = "user_gallary")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class UserGallary implements Serializable{

	private static final long serialVersionUID = 4116973597357848823L;
	private Long userGallaryId;
	private Registration registration;
	private Gallary gallary;
	
	public UserGallary()
	{}
	
	public UserGallary(Registration registration,Gallary gallary)
	{
		this.registration = registration;
		this.gallary = gallary;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "user_gallary_id",unique = true,nullable = false)
	public Long getUserGallaryId() {
		return userGallaryId;
	}

	public void setUserGallaryId(Long userGallaryId) {
		this.userGallaryId = userGallaryId;
	}

	@ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
	@JoinColumn(name = "user_id")
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action = NotFoundAction.IGNORE)
	public Registration getRegistration() {
		return registration;
	}

	public void setRegistration(Registration registration) {
		this.registration = registration;
	}

	@ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
	@JoinColumn(name = "gallary_id")
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action = NotFoundAction.IGNORE)
	public Gallary getGallary() {
		return gallary;
	}

	public void setGallary(Gallary gallary) {
		this.gallary = gallary;
	}
	
	

}
