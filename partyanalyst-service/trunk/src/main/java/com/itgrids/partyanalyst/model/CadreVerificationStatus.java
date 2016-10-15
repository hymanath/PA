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
@Table(name = "cadre_verification_status")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class CadreVerificationStatus extends BaseModel implements Serializable {
	
	
	private Long cadreVerificationStatusId;
	private String status;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "cadre_verification_status_id", unique = true, nullable = false)
	public Long getCadreVerificationStatusId() {
		return cadreVerificationStatusId;
	}
	public void setCadreVerificationStatusId(Long cadreVerificationStatusId) {
		this.cadreVerificationStatusId = cadreVerificationStatusId;
	}
	@Column(name="status")
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	
}
