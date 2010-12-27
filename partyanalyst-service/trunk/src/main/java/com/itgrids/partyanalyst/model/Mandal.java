package com.itgrids.partyanalyst.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import com.itgrids.partyanalyst.BaseObject;

@Entity
@Table(name="mandal")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Mandal extends BaseObject {

	private static final long serialVersionUID = 1L;
	
	@Column(name="mandal_id",nullable=false,insertable=false,updatable=false)
	private Long mandalId;
	
	@Column(name="mandal_name",nullable=false,insertable=false,updatable=false)
	private String mandalName;
	
	public Mandal() {}

	public Long getMandalId() {
		return mandalId;
	}

	public void setMandalId(Long mandalId) {
		this.mandalId = mandalId;
	}

	public String getMandalName() {
		return mandalName;
	}

	public void setMandalName(String mandalName) {
		this.mandalName = mandalName;
	}

}
