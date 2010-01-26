package com.itgrids.partyanalyst.dto;

import java.util.List;

public class NewsPaperURLsVO {

	private List<EPaperVO> ePapersURLsVO;
	private List<SelectOptionVO> districtsList;

	public List<EPaperVO> getEPapersURLsVO() {
		return ePapersURLsVO;
	}

	public void setEPapersURLsVO(List<EPaperVO> papersURLsVO) {
		ePapersURLsVO = papersURLsVO;
	}

	public List<SelectOptionVO> getDistrictsList() {
		return districtsList;
	}

	public void setDistrictsList(List<SelectOptionVO> districtsList) {
		this.districtsList = districtsList;
	}
	
	
	
	
	
}
