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
@Table(name = "camp_call_status")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class CampCallStatus {
private Long campCallStatusId;
private String status;

@Id
@GeneratedValue(strategy = GenerationType.AUTO)
@Column(name = "camp_call_status_id", unique = true, nullable = false)
public Long getCampCallStatusId() {
	return campCallStatusId;
}
public void setCampCallStatusId(Long campCallStatusId) {
	this.campCallStatusId = campCallStatusId;
}
@Column(name = "status")
public String getStatus() {
	return status;
}
public void setStatus(String status) {
	this.status = status;
}


}
