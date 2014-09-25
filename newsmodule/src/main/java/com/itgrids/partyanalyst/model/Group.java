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
@Table (name="groups")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Group extends BaseModel implements Serializable {


	/**
	 * 
	 */
	private static final long serialVersionUID = -6426930945673244433L;
	private Long groupId;
	private String name;



	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "group_id", unique = true, nullable = false)
	public Long getGroupId() {
		return groupId;
	}

	public void setGroupId(Long groupId) {
		this.groupId = groupId;
	}

	@Column(name = "name")
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	
}
