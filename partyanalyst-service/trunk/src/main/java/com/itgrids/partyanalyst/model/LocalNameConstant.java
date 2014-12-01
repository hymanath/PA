package com.itgrids.partyanalyst.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * 
 * @author Srishailam Pittala
 *
 */

@Entity
@Table(name = "local_name_constants")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class LocalNameConstant implements java.io.Serializable{

	private Long localNameConstantId;
	private String name;
	private String localName;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "local_name_constants_id", unique = true, nullable = false)
	public Long getLocalNameConstantId() {
		return localNameConstantId;
	}
	public void setLocalNameConstantId(Long localNameConstantId) {
		this.localNameConstantId = localNameConstantId;
	}
	
	@Column(name="name")
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	@Column(name="local_name")
	public String getLocalName() {
		return localName;
	}
	public void setLocalName(String localName) {
		this.localName = localName;
	}
	
	
}
