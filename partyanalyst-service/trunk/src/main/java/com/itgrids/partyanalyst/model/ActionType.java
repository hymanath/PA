package com.itgrids.partyanalyst.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "action_type")
public class ActionType {
    /*
     * Author:santosh
     */
	private Long actionTypeId;
	private String typeName;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "action_type_id",unique = true,nullable = false)
	public Long getActionTypeId() {
		return actionTypeId;
	}
	public void setActionTypeId(Long actionTypeId) {
		this.actionTypeId = actionTypeId;
	}
	@Column(name = "type_name")
	public String getTypeName() {
		return typeName;
	}
	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}

	

}
