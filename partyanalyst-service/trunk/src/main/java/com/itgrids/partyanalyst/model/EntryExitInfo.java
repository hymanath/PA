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
@Table(name = "entry_exit_info")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class EntryExitInfo {
   
	Long entryExitInfoId;
	Long entryId;
	Long exitId;
	Long parentEventId;
	String serviceRunReq;
	String eventType;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "entry_exit_info_id", unique = true, nullable = false)
	public Long getEntryExitInfoId() {
		return entryExitInfoId;
	}
	public void setEntryExitInfoId(Long entryExitInfoId) {
		this.entryExitInfoId = entryExitInfoId;
	}
	
	@Column(name = "entry_id")
	public Long getEntryId() {
		return entryId;
	}
	public void setEntryId(Long entryId) {
		this.entryId = entryId;
	}
	
	@Column(name = "exit_id")
	public Long getExitId() {
		return exitId;
	}
	public void setExitId(Long exitId) {
		this.exitId = exitId;
	}
	
	@Column(name = "parent_event_id")
	public Long getParentEventId() {
		return parentEventId;
	}
	public void setParentEventId(Long parentEventId) {
		this.parentEventId = parentEventId;
	}
	
	@Column(name = "servicerun_req")
	public String getServiceRunReq() {
		return serviceRunReq;
	}
	public void setServiceRunReq(String serviceRunReq) {
		this.serviceRunReq = serviceRunReq;
	}
	
	@Column(name = "event_type")
	public String getEventType() {
		return eventType;
	}
	public void setEventType(String eventType) {
		this.eventType = eventType;
	}
	
	
}
