package com.itgrids.partyanalyst.dto;

import java.io.Serializable;

public class PartyEffectVO  implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -5240878292965907316L;
	private Double tdpPrevPerc;
	private Double tdpCurrentPerc;
	private Double prpCurrentPerc;
	private Long id;
	private Double difference;
	
	public Double getTdpPrevPerc() {
		return tdpPrevPerc;
	}
	
	public void setTdpPrevPerc(Double tdpPrevPerc) {
		this.tdpPrevPerc = tdpPrevPerc;
	}
	
	public Double getTdpCurrentPerc() {
		return tdpCurrentPerc;
	}
	
	public void setTdpCurrentPerc(Double tdpCurrentPerc) {
		this.tdpCurrentPerc = tdpCurrentPerc;
	}
	
	public Double getPrpCurrentPerc() {
		return prpCurrentPerc;
	}
	
	public void setPrpCurrentPerc(Double prpCurrentPerc) {
		this.prpCurrentPerc = prpCurrentPerc;
	}
	
	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	
	public Double getDifference() {
		return difference;
	}
	
	public void setDifference(Double difference) {
		this.difference = difference;
	}
	
	
}
