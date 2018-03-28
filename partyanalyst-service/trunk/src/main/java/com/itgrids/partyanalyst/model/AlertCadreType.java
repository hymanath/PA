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
@Table(name = "alert_cadre_type")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class AlertCadreType extends BaseModel implements Serializable{

	private Long alertCadreTypeId;
	private String typeName;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="alert_cadre_type_id" , unique =true, nullable=false)
	public Long getAlertCadreTypeId() {
		return alertCadreTypeId;
	}
	public void setAlertCadreTypeId(Long alertCadreTypeId) {
		this.alertCadreTypeId = alertCadreTypeId;
	}
	@Column(name="type_name")
	public String getTypeName() {
		return typeName;
	}
	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}
	
	
	
}
