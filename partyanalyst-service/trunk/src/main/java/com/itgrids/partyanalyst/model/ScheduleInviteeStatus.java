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
@Table(name = "schedule_invitee_status")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class ScheduleInviteeStatus {
	private Long scheduleInviteeStatusId;
	private String status;
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="schedule_invitee_status_id", unique=true, nullable=false)
	public Long getScheduleInviteeStatusId() {
		return scheduleInviteeStatusId;
	}
	public void setScheduleInviteeStatusId(Long scheduleInviteeStatusId) {
		this.scheduleInviteeStatusId = scheduleInviteeStatusId;
	}
	@Column(name="status")
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	

}
