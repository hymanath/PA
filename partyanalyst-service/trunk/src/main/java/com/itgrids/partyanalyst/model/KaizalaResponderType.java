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
@Table(name="kaizala_responder_type")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class KaizalaResponderType extends BaseModel implements Serializable{
	
	private Long kaizalaResponderTypeId;
	private String type;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "kaizala_responder_type_id", unique = true, nullable = false)
	public Long getKaizalaResponderTypeId() {
		return kaizalaResponderTypeId;
	}
	public void setKaizalaResponderTypeId(Long kaizalaResponderTypeId) {
		this.kaizalaResponderTypeId = kaizalaResponderTypeId;
	}
	@Column(name = "type")
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
}
