package com.itgrids.partyanalyst.dto;

import java.util.List;

public class VillageBoothElectionVO extends ResultStatus{

	List<String> villageErrors;
	List<String> hamletErrors;
	List<String> boothErrors;
	List<String> dataDuplicateErrors;
	
	public List<String> getVillageErrors() {
		return villageErrors;
	}
	
	public void setVillageErrors(List<String> villageErrors) {
		this.villageErrors = villageErrors;
	}
	
	public List<String> getHamletErrors() {
		return hamletErrors;
	}
	
	public void setHamletErrors(List<String> hamletErrors) {
		this.hamletErrors = hamletErrors;
	}
	
	public List<String> getBoothErrors() {
		return boothErrors;
	}
	
	public void setBoothErrors(List<String> boothErrors) {
		this.boothErrors = boothErrors;
	}
	
	public List<String> getDataDuplicateErrors() {
		return dataDuplicateErrors;
	}
	
	public void setDataDuplicateErrors(List<String> dataDuplicateErrors) {
		this.dataDuplicateErrors = dataDuplicateErrors;
	}
	
}
