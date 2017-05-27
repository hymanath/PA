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
@Table(name = "alert_attatch_type")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class AlertAttatchType extends BaseModel implements Serializable {
	private Long alertAttatchTypeId;
	private String type;
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "alert_attatch_type_id", unique = true, nullable = false)
	public Long getAlertAttatchTypeId() {
		return alertAttatchTypeId;
	}
	public void setAlertAttatchTypeId(Long alertAttatchTypeId) {
		this.alertAttatchTypeId = alertAttatchTypeId;
	}
	@Column(name = "type")
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
}
