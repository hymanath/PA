/* 
 * Copyright (c) 2009 IT Grids.
 * All Rights Reserved.
 *
 * IT Grids Confidential Information.
 * Created on July 09, 2009
 */
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
@Table(name = "mobile_connection_type")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class MobileConnectionType implements java.io.Serializable {
	private Long mobileConnectionTypeId;
	private String connectionType;
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="mobile_connection_type_id", unique=true, nullable=false)
	public Long getMobileConnectionTypeId() {
		return mobileConnectionTypeId;
	}
	public void setMobileConnectionTypeId(Long mobileConnectionTypeId) {
		this.mobileConnectionTypeId = mobileConnectionTypeId;
	}
	@Column(name="connection_type")
	public String getConnectionType() {
		return connectionType;
	}
	public void setConnectionType(String connectionType) {
		this.connectionType = connectionType;
	}
		
}