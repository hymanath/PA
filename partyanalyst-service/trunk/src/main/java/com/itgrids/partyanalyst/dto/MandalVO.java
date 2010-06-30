package com.itgrids.partyanalyst.dto;

import java.util.List;
import java.util.Set;


public class MandalVO {

	private static final long serialVersionUID = 1L;
	private String name;
	private Long id;
	private List<SelectOptionVO> partiesInMandal;
	private Set<SelectOptionVO> electionsInMandal;
	
	public MandalVO(){}
	
	public MandalVO(Long id, String name){
		this.id = id;
		this.name = name;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}

	public Set<SelectOptionVO> getElectionsInMandal() {
		return electionsInMandal;
	}

	public void setElectionsInMandal(Set<SelectOptionVO> electionsInMandal) {
		this.electionsInMandal = electionsInMandal;
	}

	public List<SelectOptionVO> getPartiesInMandal() {
		return partiesInMandal;
	}

	public void setPartiesInMandal(List<SelectOptionVO> partiesInMandal) {
		this.partiesInMandal = partiesInMandal;
	}

}
