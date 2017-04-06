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
@Table(name="govt_alert_action_type")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class GovtAlertActionType {

	private Long govtAlertActionTypeId;
	private String actionType;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "govt_alert_action_type_id", unique = true, nullable = false)
	public Long getGovtAlertActionTypeId() {
		return govtAlertActionTypeId;
	}
	public void setGovtAlertActionTypeId(Long govtAlertActionTypeId) {
		this.govtAlertActionTypeId = govtAlertActionTypeId;
	}
	@Column(name = "action_type")
	public String getActionType() {
		return actionType;
	}
	public void setActionType(String actionType) {
		this.actionType = actionType;
	}
	
	
}
