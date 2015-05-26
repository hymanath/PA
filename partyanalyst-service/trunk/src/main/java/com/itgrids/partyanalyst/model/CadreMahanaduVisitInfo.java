package com.itgrids.partyanalyst.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

@Entity
@Table(name = "cadre_mahanadu_visitinfo")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class CadreMahanaduVisitInfo {

	private Long cadreMahanaduVisitInfoId;
	private Date insertedTime;
	private Long parentEventId;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "cadre_mahanadu_visitinfo_id", unique = true, nullable = false)
	public Long getCadreMahanaduVisitInfoId() {
		return cadreMahanaduVisitInfoId;
	}
	
	public void setCadreMahanaduVisitInfoId(Long cadreMahanaduVisitInfoId) {
		this.cadreMahanaduVisitInfoId = cadreMahanaduVisitInfoId;
	}
	
	@Column(name="inserted_time")
	public Date getInsertedTime() {
		return insertedTime;
	}
	
	public void setInsertedTime(Date insertedTime) {
		this.insertedTime = insertedTime;
	}

	@Column(name="parent_event_id")
	public Long getParentEventId() {
		return parentEventId;
	}

	public void setParentEventId(Long parentEventId) {
		this.parentEventId = parentEventId;
	}
	
}
