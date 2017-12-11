package com.itgrids.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "nrega_component_status")
public class NregaComponentStatus{
	
	
	private static final long serialVersionUID = -2853930539938433902L;

	@Id
	@Column(name="nrega_component_status_id")
	@GeneratedValue(strategy= GenerationType.AUTO)
	private Long nregaComponentStatusId;
	
	@Column(name="status")
	private String status;

	public Long getNregaComponentStatusId() {
		return nregaComponentStatusId;
	}

	public void setNregaComponentStatusId(Long nregaComponentStatusId) {
		this.nregaComponentStatusId = nregaComponentStatusId;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	
	
}
