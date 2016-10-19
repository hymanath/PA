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
@Table(name = "data_reject_reason")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class DataRejectReason extends BaseModel implements Serializable{
	
	private static final long serialVersionUID = 7254215584202599072L;
	
	private Long dataRejectReasonId;
	private String rejectReason;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "data_reject_reason_id", unique = true, nullable = false)
	public Long getDataRejectReasonId() {
		return dataRejectReasonId;
	}
	public void setDataRejectReasonId(Long dataRejectReasonId) {
		this.dataRejectReasonId = dataRejectReasonId;
	}
	
	@Column(name="reject_reason")
	public String getRejectReason() {
		return rejectReason;
	}
	public void setRejectReason(String rejectReason) {
		this.rejectReason = rejectReason;
	}
	
	
}
