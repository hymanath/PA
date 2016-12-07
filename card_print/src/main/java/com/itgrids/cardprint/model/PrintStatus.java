package com.itgrids.cardprint.model;

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
@Table(name = "print_status")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class PrintStatus extends BaseModel implements java.io.Serializable {
	
	private Long printStatusId;
	private String printStatus;
	private String description;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "print_status_id", unique = true, nullable = false)
	public Long getPrintStatusId() {
		return printStatusId;
	}
	public void setPrintStatusId(Long printStatusId) {
		this.printStatusId = printStatusId;
	}
	@Column(name = "print_status")
	public String getPrintStatus() {
		return printStatus;
	}
	public void setPrintStatus(String printStatus) {
		this.printStatus = printStatus;
	}
	@Column(name = "description")
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	
}
