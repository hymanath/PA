package com.itgrids.partyanalyst.dto;

import java.util.ArrayList;
import java.util.List;

public class ConstituenciesStatusVO extends ResultStatus{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private List<ConstituencyWinnerInfoVO> constituencyWinnerInfoVO = new ArrayList<ConstituencyWinnerInfoVO>();
	private List<SelectOptionVO> existConstituencies = new ArrayList<SelectOptionVO>();
	private List<SelectOptionVO> newConstituencies = new ArrayList<SelectOptionVO>();
	private List<SelectOptionVO> deletedConstituencies = new ArrayList<SelectOptionVO>();
	private Long delimitationYear;
	private int totalConstituenciesAfterDelimitation;
	private int totalDeletedConstituencies;
	private String electionType;
	private String electionYear;
	
	public int getTotalDeletedConstituencies() {
		return totalDeletedConstituencies;
	}
	public void setTotalDeletedConstituencies(int totalDeletedConstituencies) {
		this.totalDeletedConstituencies = totalDeletedConstituencies;
	}
	public Long getDelimitationYear() {
		return delimitationYear;
	}
	public void setDelimitationYear(Long delimitationYear) {
		this.delimitationYear = delimitationYear;
	}
	public List<SelectOptionVO> getExistConstituencies() {
		return existConstituencies;
	}
	public void setExistConstituencies(List<SelectOptionVO> existConstituencies) {
		this.existConstituencies = existConstituencies;
	}
	public List<SelectOptionVO> getNewConstituencies() {
		return newConstituencies;
	}
	public void setNewConstituencies(List<SelectOptionVO> newConstituencies) {
		this.newConstituencies = newConstituencies;
	}
	public List<SelectOptionVO> getDeletedConstituencies() {
		return deletedConstituencies;
	}
	public void setDeletedConstituencies(List<SelectOptionVO> deletedConstituencies) {
		this.deletedConstituencies = deletedConstituencies;
	}
	public List<ConstituencyWinnerInfoVO> getConstituencyWinnerInfoVO() {
		return constituencyWinnerInfoVO;
	}
	public void setConstituencyWinnerInfoVO(
			List<ConstituencyWinnerInfoVO> constituencyWinnerInfoVO) {
		this.constituencyWinnerInfoVO = constituencyWinnerInfoVO;
	}
	public int getTotalConstituenciesAfterDelimitation() {
		return totalConstituenciesAfterDelimitation;
	}
	public void setTotalConstituenciesAfterDelimitation(
			int totalConstituenciesAfterDelimitation) {
		this.totalConstituenciesAfterDelimitation = totalConstituenciesAfterDelimitation;
	}
		
	public String getElectionType() {
		return electionType;
	}
	public void setElectionType(String electionType) {
		this.electionType = electionType;
	}
	public String getElectionYear() {
		return electionYear;
	}
	public void setElectionYear(String electionYear) {
		this.electionYear = electionYear;
	}
}
