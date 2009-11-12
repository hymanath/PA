package com.itgrids.partyanalyst.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class VillageDetailsVO extends ResultStatus implements Serializable{
	private String mandalName;
	private List<VillageCensusInfoVO> villageCensusList = new ArrayList<VillageCensusInfoVO>();
	public String getMandalName() {
		return mandalName;
	}
	public void setMandalName(String mandalName) {
		this.mandalName = mandalName;
	}
	public List<VillageCensusInfoVO> getVillageCensusList() {
		return villageCensusList;
	}
	public void setVillageCensusList(List<VillageCensusInfoVO> villageCensusList) {
		this.villageCensusList = villageCensusList;
	}
	

}
