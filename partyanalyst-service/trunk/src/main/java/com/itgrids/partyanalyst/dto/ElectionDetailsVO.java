package com.itgrids.partyanalyst.dto;

import java.io.Serializable;
import java.util.List;

public class ElectionDetailsVO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 753815547614023258L;
	
	private List<ElectionBasicInfoVO> assemblyElections;
	private List<ElectionBasicInfoVO> parliamentElections;
	
	//getters and setters
	public List<ElectionBasicInfoVO> getAssemblyElections() {
		return assemblyElections;
	}
	public void setAssemblyElections(List<ElectionBasicInfoVO> assemblyElections) {
		this.assemblyElections = assemblyElections;
	}
	public List<ElectionBasicInfoVO> getParliamentElections() {
		return parliamentElections;
	}
	public void setParliamentElections(List<ElectionBasicInfoVO> parliamentElections) {
		this.parliamentElections = parliamentElections;
	}

}
