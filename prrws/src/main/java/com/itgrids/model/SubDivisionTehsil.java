package com.itgrids.model;

import java.util.Date;

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
@Table(name = "sub_division_tehsil")
public class SubDivisionTehsil {

	private Long subDivisionTehsilId;
	private String subDivisionId;
	private Long tehsilId;
	
	
	private SubDivision subDivision;
	private Tehsil tehsil;
	
	
	private User insertedUser;
	private MobileAppUser mobileAppUser;
	
	@Id
	@Column(name="sub_division_tehsil_id")
	@GeneratedValue(strategy= GenerationType.AUTO)
	public Long getSubDivisionTehsilId() {
		return subDivisionTehsilId;
	}
	public void setSubDivisionTehsilId(Long subDivisionTehsilId) {
		this.subDivisionTehsilId = subDivisionTehsilId;
	}
	
	@Column(name="sub_division_id")
	public String getSubDivisionId() {
		return subDivisionId;
	}
	public void setSubDivisionId(String subDivisionId) {
		this.subDivisionId = subDivisionId;
	}
	
	@Column(name="tehsil_id")
	public Long getTehsilId() {
		return tehsilId;
	}
	public void setTehsilId(Long tehsilId) {
		this.tehsilId = tehsilId;
	}
	
	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinColumn(name = "sub_division_id", insertable = false, updatable = false)
	public SubDivision getSubDivision() {
		return subDivision;
	}
	public void setSubDivision(SubDivision subDivision) {
		this.subDivision = subDivision;
	}
	
	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinColumn(name = "tehsil_id", insertable = false, updatable = false)
	public Tehsil getTehsil() {
		return tehsil;
	}
	public void setTehsil(Tehsil tehsil) {
		this.tehsil = tehsil;
	}
}
