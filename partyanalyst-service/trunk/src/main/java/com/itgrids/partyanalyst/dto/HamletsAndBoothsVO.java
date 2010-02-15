package com.itgrids.partyanalyst.dto;

import java.util.List;

public class HamletsAndBoothsVO {

	private List<SelectOptionVO> hamlets;
	private List<ConstituencyBoothInfoVO> constituenciesAndBooths;
	
	public List<SelectOptionVO> getHamlets() {
		return hamlets;
	}
	
	public void setHamlets(List<SelectOptionVO> hamlets) {
		this.hamlets = hamlets;
	}
	
	public List<ConstituencyBoothInfoVO> getConstituenciesAndBooths() {
		return constituenciesAndBooths;
	}
	
	public void setConstituenciesAndBooths(
			List<ConstituencyBoothInfoVO> constituenciesAndBooths) {
		this.constituenciesAndBooths = constituenciesAndBooths;
	}
	
	
	
}
