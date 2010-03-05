package com.itgrids.partyanalyst.dto;

import java.io.Serializable;
import java.util.List;

public class ElectionWiseMandalPartyResultVO  implements Serializable {

	private Long electionYear;
	private String electionType;
	private Long totalVotersInMandal;
	private List<ConstituencyWiseDataForMandalVO> constituencyWiseDataForMandalVOs; 
	private BoothTypeDetailsVO boothTypeDetailsVO;
	
	public Long getElectionYear() {
		return electionYear;
	}
	
	public void setElectionYear(Long electionYear) {
		this.electionYear = electionYear;
	}
	
	public String getElectionType() {
		return electionType;
	}
	
	public void setElectionType(String electionType) {
		this.electionType = electionType;
	}
	
	public BoothTypeDetailsVO getBoothTypeDetailsVO() {
		return boothTypeDetailsVO;
	}
	
	public void setBoothTypeDetailsVO(BoothTypeDetailsVO boothTypeDetailsVO) {
		this.boothTypeDetailsVO = boothTypeDetailsVO;
	}
	
	public void setTotalVotersInMandal(Long totalVotersInMandal) {
		this.totalVotersInMandal = totalVotersInMandal;
	}
	
	public Long getTotalVotersInMandal() {
		return totalVotersInMandal;
	}
	
	public List<ConstituencyWiseDataForMandalVO> getConstituencyWiseDataForMandalVOs() {
		return constituencyWiseDataForMandalVOs;
	}

	public void setConstituencyWiseDataForMandalVOs(
			List<ConstituencyWiseDataForMandalVO> constituencyWiseDataForMandalVOs) {
		this.constituencyWiseDataForMandalVOs = constituencyWiseDataForMandalVOs;
	}

	@Override
	public boolean equals(Object obj){
		ElectionWiseMandalPartyResultVO voObj = (ElectionWiseMandalPartyResultVO) obj;
		return this.electionYear.equals(voObj.getElectionYear()) && this.electionType.equals(voObj.getElectionType());
	}
	
	@Override
	public int hashCode(){
		return this.electionYear.hashCode() + this.electionType.hashCode();
	}
}
