package com.itgrids.partyanalyst.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class CrossVotingVO implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -2408424603038925745L;

	List<SelectOptionVO> yearsList = new ArrayList<SelectOptionVO>();	
	List<SelectOptionVO> parliamentLists = new ArrayList<SelectOptionVO>();	
	List<SelectOptionVO> assemblyList = new ArrayList<SelectOptionVO>();	
	List<SelectOptionVO> partiesList = new ArrayList<SelectOptionVO>();	
	
	
	public List<SelectOptionVO> getYearsList() {
		return yearsList;
	}
	
	public void setYearsList(List<SelectOptionVO> yearsList) {
		this.yearsList = yearsList;
	}
	
	public List<SelectOptionVO> getParliamentLists() {
		return parliamentLists;
	}
	
	public void setParliamentLists(List<SelectOptionVO> parliamentLists) {
		this.parliamentLists = parliamentLists;
	}
	
	public List<SelectOptionVO> getAssemblyList() {
		return assemblyList;
	}
	
	public void setAssemblyList(List<SelectOptionVO> assemblyList) {
		this.assemblyList = assemblyList;
	}
	
	public List<SelectOptionVO> getPartiesList() {
		return partiesList;
	}
	
	public void setPartiesList(List<SelectOptionVO> partiesList) {
		this.partiesList = partiesList;
	}
	
}
