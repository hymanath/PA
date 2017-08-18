package com.itgrids.partyanalyst.model;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.LazyToOne;
import org.hibernate.annotations.LazyToOneOption;
import org.hibernate.annotations.NotFoundAction;

@Entity
@Table(name = "kaizala_action_type")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class KaizalaActionType extends BaseModel implements Serializable {
	private Long kaizalaActionTypeId;
	private String actionType;
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "kaizala_action_type_id", unique = true, nullable = false)
	public Long getKaizalaActionTypeId() {
		return kaizalaActionTypeId;
	}

	public void setKaizalaActionTypeId(Long kaizalaActionTypeId) {
		this.kaizalaActionTypeId = kaizalaActionTypeId;
	}

	@Column(name = "action_type")
	public String getActionType() {
		return actionType;
	}

	public void setActionType(String actionType) {
		this.actionType = actionType;
	}
}
