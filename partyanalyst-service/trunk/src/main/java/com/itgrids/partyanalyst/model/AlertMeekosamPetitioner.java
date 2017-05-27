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
@Table(name = "alert_meekosam_petitioner")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class AlertMeekosamPetitioner extends BaseModel implements Serializable {
	private Long  alertMeekosamPetitionerId;
	private Long alertId;
	private Long meekosamPetitionerId;
	
	private Alert alert;
	private MeekosamPetitioner meekosamPetitioner;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "alert_meekosam_petitioner_id", unique = true, nullable = false)
	public Long getAlertMeekosamPetitionerId() {
		return alertMeekosamPetitionerId;
	}
	public void setAlertMeekosamPetitionerId(Long alertMeekosamPetitionerId) {
		this.alertMeekosamPetitionerId = alertMeekosamPetitionerId;
	}
	@Column(name = "alert_id")
	public Long getAlertId() {
		return alertId;
	}
	public void setAlertId(Long alertId) {
		this.alertId = alertId;
	}
	@Column(name = "meekosam_petitioner_id")
	public Long getMeekosamPetitionerId() {
		return meekosamPetitionerId;
	}
	public void setMeekosamPetitionerId(Long meekosamPetitionerId) {
		this.meekosamPetitionerId = meekosamPetitionerId;
	}
	@ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
	@JoinColumn(name="alert_id", insertable=false, updatable = false)
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public Alert getAlert() {
		return alert;
	}
	public void setAlert(Alert alert) {
		this.alert = alert;
	}
	@ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
	@JoinColumn(name="meekosam_petitioner_id", insertable=false, updatable = false)
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public MeekosamPetitioner getMeekosamPetitioner() {
		return meekosamPetitioner;
	}
	public void setMeekosamPetitioner(MeekosamPetitioner meekosamPetitioner) {
		this.meekosamPetitioner = meekosamPetitioner;
	}
}
