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
@Table(name = "govt_rejoinder_action")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class GovtRejoinderAction extends BaseModel implements Serializable {
	
	private Long govtRejoinderActionId;
	private String action;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "govt_rejoinder_action_id", unique = true, nullable = false)
	public Long getGovtRejoinderActionId() {
		return govtRejoinderActionId;
	}
	public void setGovtRejoinderActionId(Long govtRejoinderActionId) {
		this.govtRejoinderActionId = govtRejoinderActionId;
	}
	
	@Column(name = "action")
	public String getAction() {
		return action;
	}
	public void setAction(String action) {
		this.action = action;
	}
}
