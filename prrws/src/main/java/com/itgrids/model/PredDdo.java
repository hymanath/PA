package com.itgrids.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "pred_ddo")
public class PredDdo implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private Long predDdoId;
	private String division;
	private String tddocode;
	
	@Id
	@Column(name = "pred_ddo_id")
	@GeneratedValue(strategy = GenerationType.AUTO)
	public Long getPredDdoId() {
		return predDdoId;
	}
	public void setPredDdoId(Long predDdoId) {
		this.predDdoId = predDdoId;
	}
	@Column(name = "division")
	public String getDivision() {
		return division;
	}
	public void setDivision(String division) {
		this.division = division;
	}
	@Column(name = "tddocode")
	public String getTddocode() {
		return tddocode;
	}
	public void setTddocode(String tddocode) {
		this.tddocode = tddocode;
	}
	
}
