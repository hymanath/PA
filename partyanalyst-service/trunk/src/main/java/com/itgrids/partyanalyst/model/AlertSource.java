package com.itgrids.partyanalyst.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

@Entity
@Table(name = "alert_source")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class AlertSource extends BaseModel implements java.io.Serializable {
private static final long serialVersionUID = 1L;
	
	private Long alertSourceId;
	private String source;
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "alert_source_id", unique = true, nullable = false)
	public Long getAlertSourceId() {
		return alertSourceId;
	}
	public void setAlertSourceId(Long alertSourceId) {
		this.alertSourceId = alertSourceId;
	}
	@Column(name = "source", length = 30)
	public String getSource() {
		return source;
	}
	public void setSource(String source) {
		this.source = source;
	}
	

}
