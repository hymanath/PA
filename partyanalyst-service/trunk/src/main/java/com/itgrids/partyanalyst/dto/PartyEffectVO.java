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
	private Long tdpCurrentVotes;
	private Long tdpPrevVotes;
	private Long id;
	private Double difference;
	private String name;
	private Double points;
	private String castes;
	private Long reportLvl;
	private Double tdpLostPerc;
	
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

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Double getPoints() {
		return points;
	}

	public void setPoints(Double points) {
		this.points = points;
	}

	public String getCastes() {
		return castes;
	}

	public void setCastes(String castes) {
		this.castes = castes;
	}

	public Long getReportLvl() {
		return reportLvl;
	}

	public void setReportLvl(Long reportLvl) {
		this.reportLvl = reportLvl;
	}

	public Long getTdpCurrentVotes() {
		return tdpCurrentVotes;
	}

	public void setTdpCurrentVotes(Long tdpCurrentVotes) {
		this.tdpCurrentVotes = tdpCurrentVotes;
	}

	public Long getTdpPrevVotes() {
		return tdpPrevVotes;
	}

	public void setTdpPrevVotes(Long tdpPrevVotes) {
		this.tdpPrevVotes = tdpPrevVotes;
	}

	public Double getTdpLostPerc() {
		return tdpLostPerc;
	}

	public void setTdpLostPerc(Double tdpLostPerc) {
		this.tdpLostPerc = tdpLostPerc;
	}
	
	
}
