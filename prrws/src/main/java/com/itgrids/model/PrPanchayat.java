package com.itgrids.model;

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
@Table(name = "pr_panchayat")
public class PrPanchayat {

	private Long prPanchayatId;
	private String panchayatName;
	private String uniquecode;
	private Long prTehsilId;
	private Long panchayatId;
	
	private Panchayat panchayat;
	private PrTehsil prTehsil;

	@Id
	@Column(name="pr_panchayat_id")
	@GeneratedValue(strategy= GenerationType.AUTO)
	public Long getPrPanchayatId() {
		return prPanchayatId;
	}

	public void setPrPanchayatId(Long prPanchayatId) {
		this.prPanchayatId = prPanchayatId;
	}
	@Column(name="panchayat_name")
	public String getPanchayatName() {
		return panchayatName;
	}

	public void setPanchayatName(String panchayatName) {
		this.panchayatName = panchayatName;
	}
	@Column(name="unique_code")
	public String getUniquecode() {
		return uniquecode;
	}

	public void setUniquecode(String uniquecode) {
		this.uniquecode = uniquecode;
	}
	@Column(name="pr_tehsil_id")
	public Long getPrTehsilId() {
		return prTehsilId;
	}

	public void setPrTehsilId(Long prTehsilId) {
		this.prTehsilId = prTehsilId;
	}
	@Column(name="panchayat_id")
	public Long getPanchayatId() {
		return panchayatId;
	}

	public void setPanchayatId(Long panchayatId) {
		this.panchayatId = panchayatId;
	}
	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinColumn(name = "panchayat_id", insertable = false, updatable = false)
	public Panchayat getPanchayat() {
		return panchayat;
	}

	public void setPanchayat(Panchayat panchayat) {
		this.panchayat = panchayat;
	}
	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinColumn(name = "pr_tehsil_id", insertable = false, updatable = false)
	public PrTehsil getPrTehsil() {
		return prTehsil;
	}

	public void setPrTehsil(PrTehsil prTehsil) {
		this.prTehsil = prTehsil;
	}
	
	
	
}
