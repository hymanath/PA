package com.itgrids.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "govt_scheme")
public class GovtScheme{
	
	
	private static final long serialVersionUID = -2853930539938433902L;

	@Id
	@Column(name="govt_scheme_id")
	@GeneratedValue(strategy= GenerationType.AUTO)
	private Long govtSchemeId;
	
	@Column(name="scheme_name")
	private String schemeName;

	public Long getGovtSchemeId() {
		return govtSchemeId;
	}

	public void setGovtSchemeId(Long govtSchemeId) {
		this.govtSchemeId = govtSchemeId;
	}

	public String getSchemeName() {
		return schemeName;
	}

	public void setSchemeName(String schemeName) {
		this.schemeName = schemeName;
	}
	
	
	

}
