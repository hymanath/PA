package com.itgrids.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
@Entity
@Table(name = "diseases")
public class Diseases implements Serializable{
	private static final long serialVersionUID = 1L;
	private Long diseasesId;
	private String diseaseName;
	@Id
	@Column(name="diseases_id")
	@GeneratedValue(strategy= GenerationType.AUTO)
	public Long getDiseasesId() {
		return diseasesId;
	}
	public void setDiseasesId(Long diseasesId) {
		this.diseasesId = diseasesId;
	}
	@Column(name="disease_name")
	public String getDiseaseName() {
		return diseaseName;
	}
	public void setDiseaseName(String diseaseName) {
		this.diseaseName = diseaseName;
	}

}
