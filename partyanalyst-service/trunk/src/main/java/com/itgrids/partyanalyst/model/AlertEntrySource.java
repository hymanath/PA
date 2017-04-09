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
@Table(name = "alert_entry_source")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class AlertEntrySource extends BaseModel implements Serializable{

	private Long alertEntrySourceId;
	private String entrySource;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "alert_entry_source_id", unique = true, nullable = false)
	public Long getAlertEntrySourceId() {
		return alertEntrySourceId;
	}
	public void setAlertEntrySourceId(Long alertEntrySourceId) {
		this.alertEntrySourceId = alertEntrySourceId;
	}
	
	@Column(name = "entry_source")
	public String getEntrySource() {
		return entrySource;
	}
	public void setEntrySource(String entrySource) {
		this.entrySource = entrySource;
	}
}
