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
@Table(name = "govt_sms_type")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class GovtSmsType extends BaseModel implements Serializable  {

	private Long govtSmsTypeId;
	private String type;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "govt_sms_type_id", unique = true, nullable = false)
	public Long getGovtSmsTypeId() {
		return govtSmsTypeId;
	}
	public void setGovtSmsTypeId(Long govtSmsTypeId) {
		this.govtSmsTypeId = govtSmsTypeId;
	}
	
	@Column(name = "type")
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	
	
}
