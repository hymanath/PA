package com.itgrids.partyanalyst.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "delimitation_constituency_mandal_details")
public class DelimitationConstituencyMandal extends BaseModel {
	private Long dcm_id;
	private DelimitationConstituency delimitationConstituency;
	private Tehsil tehsil;
	private String isPartial;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "dcm_id", unique = true, nullable = false)
	public Long getDcm_id() {
		return dcm_id;
	}
	public void setDcm_id(Long dcm_id) {
		this.dcm_id = dcm_id;
	}

	@ManyToOne(cascade=CascadeType.ALL,fetch = FetchType.LAZY)
	@JoinColumn(name = "delimitation_constituency_id")
	public DelimitationConstituency getDelimitationConstituency() {
		return delimitationConstituency;
	}
	public void setDelimitationConstituency(
			DelimitationConstituency delimitationConstituency) {
		this.delimitationConstituency = delimitationConstituency;
	}

	@ManyToOne(cascade=CascadeType.ALL,fetch = FetchType.LAZY)
	@JoinColumn(name = "tehsil_id")
	public Tehsil getTehsil() {
		return tehsil;
	}
	public void setTehsil(Tehsil tehsil) {
		this.tehsil = tehsil;
	}
	@Column(name = "is_partial")
	public String getIsPartial() {
		return isPartial;
	}
	public void setIsPartial(String isPartial) {
		this.isPartial = isPartial;
	}

}
