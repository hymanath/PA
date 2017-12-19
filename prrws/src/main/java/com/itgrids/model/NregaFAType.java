package com.itgrids.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "nrega_fa_type")
public class NregaFAType {

	private Long nregaFaTypeId;
	private String type;
	
	@Id
	@Column(name="nrega_fa_type_id")
	@GeneratedValue(strategy= GenerationType.AUTO)
	public Long getNregaFaTypeId() {
		return nregaFaTypeId;
	}
	public void setNregaFaTypeId(Long nregaFaTypeId) {
		this.nregaFaTypeId = nregaFaTypeId;
	}
	
	@Column(name="type")
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
}
