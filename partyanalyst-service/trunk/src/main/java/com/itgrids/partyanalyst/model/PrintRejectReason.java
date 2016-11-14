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
@Table(name = "print_reject_reason")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class PrintRejectReason extends BaseModel implements Serializable{
	
	private Long printRejectReasonId;
	private String reason;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "print_reject_reason_id", unique = true, nullable = false)
	public Long getPrintRejectReasonId() {
		return printRejectReasonId;
	}
	public void setPrintRejectReasonId(Long printRejectReasonId) {
		this.printRejectReasonId = printRejectReasonId;
	}
	
	@Column(name="reason")
	public String getReason() {
		return reason;
	}
	public void setReason(String reason) {
		this.reason = reason;
	}
	
	
}
