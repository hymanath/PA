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
@Table(name = "tdp_cadre_travels")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class TdpCadreTravels {
	
	private Long tdpCadreTravelsId;		
	private String name;
	
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "tdp_cadre_travels_id", unique = true, nullable = false)
	public Long getTdpCadreTravelsId() {
		return tdpCadreTravelsId;
	}
	public void setTdpCadreTravelsId(Long tdpCadreTravelsId) {
		this.tdpCadreTravelsId = tdpCadreTravelsId;
	}
	
	@Column(name="name")
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}	
	
	
	

}
