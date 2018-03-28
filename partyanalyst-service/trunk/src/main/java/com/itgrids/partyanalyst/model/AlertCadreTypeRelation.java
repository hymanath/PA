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
@Table(name = "alert_cadre_type_relation")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class AlertCadreTypeRelation extends BaseModel implements Serializable {

	private Long alertCadreTypeRelationId;
	private String tdpCadreId;
	private Long alertCadreTypeId;
	private String isAlertCreate;
	
	private TdpCadre tdpCadre;
	private AlertCadreType alertCadreType;
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="alert_cadre_type_relation_id",unique=true,nullable=false)
	public Long getAlertCadreTypeRelationId() {
		return alertCadreTypeRelationId;
	}
	public void setAlertCadreTypeRelationId(Long alertCadreTypeRelationId) {
		this.alertCadreTypeRelationId = alertCadreTypeRelationId;
	}
	@Column(name="tdp_cadre_id")
	public String getTdpCadreId() {
		return tdpCadreId;
	}
	public void setTdpCadreId(String tdpCadreId) {
		this.tdpCadreId = tdpCadreId;
	}
	@Column(name="alert_cadre_type_id")
	public Long getAlertCadreTypeId() {
		return alertCadreTypeId;
	}
	public void setAlertCadreTypeId(Long alertCadreTypeId) {
		this.alertCadreTypeId = alertCadreTypeId;
	}
	
	@ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
	@JoinColumn(name="tdp_cadre_id", insertable=false, updatable = false)
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public TdpCadre getTdpCadre() {
		return tdpCadre;
	}
	public void setTdpCadre(TdpCadre tdpCadre) {
		this.tdpCadre = tdpCadre;
	}
	@ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
	@JoinColumn(name="alert_cadre_type_id", insertable=false, updatable = false)
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public AlertCadreType getAlertCadreType() {
		return alertCadreType;
	}
	public void setAlertCadreType(AlertCadreType alertCadreType) {
		this.alertCadreType = alertCadreType;
	}
	@Column(name="is_alert_create")
	public String getIsAlertCreate() {
		return isAlertCreate;
	}
	public void setIsAlertCreate(String isAlertCreate) {
		this.isAlertCreate = isAlertCreate;
	}
	
	
	
}
