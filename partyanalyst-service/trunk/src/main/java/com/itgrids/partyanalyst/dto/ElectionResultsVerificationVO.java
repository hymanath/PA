package com.itgrids.partyanalyst.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class ElectionResultsVerificationVO implements Serializable{

	
	private static final long serialVersionUID = -307847387680779504L;

	private Long electionId;
	
	private List<ElectionResultsVerificationInfoVO> consValidVotesGreaterTotVotesVO = new ArrayList<ElectionResultsVerificationInfoVO>(0);
	private List<ElectionResultsVerificationInfoVO> consValidVotesVO = new ArrayList<ElectionResultsVerificationInfoVO>(0);
	private List<ElectionResultsVerificationInfoVO> conTotalVotesVO = new ArrayList<ElectionResultsVerificationInfoVO>(0);
	
	private List<ElectionResultsVerificationInfoVO> boothValidVotesGreaterTotVotesVO = new ArrayList<ElectionResultsVerificationInfoVO>(0);
	private List<ElectionResultsVerificationInfoVO> boothValidVotesVO = new ArrayList<ElectionResultsVerificationInfoVO>(0);
	private List<ElectionResultsVerificationInfoVO> boothTotalVotesVO = new ArrayList<ElectionResultsVerificationInfoVO>(0);
	private List<ElectionResultsVerificationInfoVO> boothTotWithMaleAndFemaleVO = new ArrayList<ElectionResultsVerificationInfoVO>(0);
	
	
	public Long getElectionId() {
		return electionId;
	}
	public void setElectionId(Long electionId) {
		this.electionId = electionId;
	}
	public List<ElectionResultsVerificationInfoVO> getConsValidVotesGreaterTotVotesVO() {
		return consValidVotesGreaterTotVotesVO;
	}
	public void setConsValidVotesGreaterTotVotesVO(
			List<ElectionResultsVerificationInfoVO> consValidVotesGreaterTotVotesVO) {
		this.consValidVotesGreaterTotVotesVO = consValidVotesGreaterTotVotesVO;
	}
	public List<ElectionResultsVerificationInfoVO> getConsValidVotesVO() {
		return consValidVotesVO;
	}
	public void setConsValidVotesVO(
			List<ElectionResultsVerificationInfoVO> consValidVotesVO) {
		this.consValidVotesVO = consValidVotesVO;
	}
	public List<ElectionResultsVerificationInfoVO> getConTotalVotesVO() {
		return conTotalVotesVO;
	}
	public void setConTotalVotesVO(
			List<ElectionResultsVerificationInfoVO> conTotalVotesVO) {
		this.conTotalVotesVO = conTotalVotesVO;
	}
	public List<ElectionResultsVerificationInfoVO> getBoothValidVotesGreaterTotVotesVO() {
		return boothValidVotesGreaterTotVotesVO;
	}
	public void setBoothValidVotesGreaterTotVotesVO(
			List<ElectionResultsVerificationInfoVO> boothValidVotesGreaterTotVotesVO) {
		this.boothValidVotesGreaterTotVotesVO = boothValidVotesGreaterTotVotesVO;
	}
	public List<ElectionResultsVerificationInfoVO> getBoothValidVotesVO() {
		return boothValidVotesVO;
	}
	public void setBoothValidVotesVO(
			List<ElectionResultsVerificationInfoVO> boothValidVotesVO) {
		this.boothValidVotesVO = boothValidVotesVO;
	}
	public List<ElectionResultsVerificationInfoVO> getBoothTotalVotesVO() {
		return boothTotalVotesVO;
	}
	public void setBoothTotalVotesVO(
			List<ElectionResultsVerificationInfoVO> boothTotalVotesVO) {
		this.boothTotalVotesVO = boothTotalVotesVO;
	}
	public List<ElectionResultsVerificationInfoVO> getBoothTotWithMaleAndFemaleVO() {
		return boothTotWithMaleAndFemaleVO;
	}
	public void setBoothTotWithMaleAndFemaleVO(
			List<ElectionResultsVerificationInfoVO> boothTotWithMaleAndFemaleVO) {
		this.boothTotWithMaleAndFemaleVO = boothTotWithMaleAndFemaleVO;
	}
	
	
}
