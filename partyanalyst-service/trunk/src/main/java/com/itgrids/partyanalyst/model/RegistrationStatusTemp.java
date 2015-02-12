package com.itgrids.partyanalyst.model;

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
@Table(name = "registration_status_temp")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class RegistrationStatusTemp {
	
	private Long registrationStatusTempId;
	private String message;
	private String type;
	private Date insertedTime;
	private Long boothId;
	private Booth booth;
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "registration_status_temp_id", unique = true, nullable = false)
	public Long getRegistrationStatusTempId() {
		return registrationStatusTempId;
	}
	public void setRegistrationStatusTempId(Long registrationStatusTempId) {
		this.registrationStatusTempId = registrationStatusTempId;
	}
	
	@Column(name = "message")
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	
	@Column(name = "type")
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	
	
	@Column(name = "inserted_time")
	public Date getInsertedTime() {
		return insertedTime;
	}
	public void setInsertedTime(Date insertedTime) {
		this.insertedTime = insertedTime;
	}
	@Column(name="booth_id")
	public Long getBoothId() {
		return boothId;
	}

	public void setBoothId(Long boothId) {
		this.boothId = boothId;
	}

	@ManyToOne(cascade=CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinColumn(name = "booth_id" , insertable = false, updatable = false)
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public Booth getBooth() {
		return booth;
	}

	public void setBooth(Booth booth) {
		this.booth = booth;
	}

	
	
	
	
	
}
