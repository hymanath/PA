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
@Table(name = "kaizala_properties")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class KaizalaProperties extends BaseModel implements Serializable{
	
	private Long kaizalaPropertiesId;
	private Long kaizalaActionsId;
	private String name;
	private String type;
	private String value;
	
	private KaizalaActions kaizalaActions;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "kaizala_properties_id", unique = true, nullable = false)
	public Long getKaizalaPropertiesId() {
		return kaizalaPropertiesId;
	}

	public void setKaizalaPropertiesId(Long kaizalaPropertiesId) {
		this.kaizalaPropertiesId = kaizalaPropertiesId;
	}
	@Column(name = "kaizala_actions_id")
	public Long getKaizalaActionsId() {
		return kaizalaActionsId;
	}

	public void setKaizalaActionsId(Long kaizalaActionsId) {
		this.kaizalaActionsId = kaizalaActionsId;
	}
	@Column(name = "name")
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	@Column(name = "type")
	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
	@Column(name = "value")
	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}
	@ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
	@JoinColumn(name="kaizala_actions_id", insertable=false, updatable = false)
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public KaizalaActions getKaizalaActions() {
		return kaizalaActions;
	}

	public void setKaizalaActions(KaizalaActions kaizalaActions) {
		this.kaizalaActions = kaizalaActions;
	}
	
}
