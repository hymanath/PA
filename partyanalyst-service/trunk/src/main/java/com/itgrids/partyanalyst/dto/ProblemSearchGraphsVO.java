package com.itgrids.partyanalyst.dto;

import java.io.Serializable;
import java.util.List;

public class ProblemSearchGraphsVO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6401335248114296078L;
    
	private List<SelectOptionVO> status;
	private List<SelectOptionVO> cadre;
	private List<ProblemBeanVO>  department;
	private List<String> requirdGraphs;
	
	public List<SelectOptionVO> getStatus() {
		return status;
	}
	public void setStatus(List<SelectOptionVO> status) {
		this.status = status;
	}
	public List<SelectOptionVO> getCadre() {
		return cadre;
	}
	public void setCadre(List<SelectOptionVO> cadre) {
		this.cadre = cadre;
	}
	public List<ProblemBeanVO> getDepartment() {
		return department;
	}
	public void setDepartment(List<ProblemBeanVO> department) {
		this.department = department;
	}
	public List<String> getRequirdGraphs() {
		return requirdGraphs;
	}
	public void setRequirdGraphs(List<String> requirdGraphs) {
		this.requirdGraphs = requirdGraphs;
	}
	
	
}
