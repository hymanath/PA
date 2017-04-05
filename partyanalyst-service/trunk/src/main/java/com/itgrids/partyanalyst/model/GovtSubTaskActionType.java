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
@Table(name="govt_sub_task_action_type")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class GovtSubTaskActionType extends BaseModel implements Serializable{

	private static final long serialVersionUID = -3806379738904956901L;
	
	private Long govtSubTaskActionTypeId;
	private String action;
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="govt_sub_task_action_type_id", unique=true, nullable=false)
	public Long getGovtSubTaskActionTypeId() {
		return govtSubTaskActionTypeId;
	}
	public void setGovtSubTaskActionTypeId(Long govtSubTaskActionTypeId) {
		this.govtSubTaskActionTypeId = govtSubTaskActionTypeId;
	}
	
	@Column(name="action")
	public String getAction() {
		return action;
	}
	public void setAction(String action) {
		this.action = action;
	}
	
}
