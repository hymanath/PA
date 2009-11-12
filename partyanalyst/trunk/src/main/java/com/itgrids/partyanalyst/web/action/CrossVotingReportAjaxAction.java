package com.itgrids.partyanalyst.web.action;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.util.ServletContextAware;
import com.itgrids.partyanalyst.dto.SelectOptionVO;
import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionSupport;

import com.itgrids.partyanalyst.dto.CrossVotingConsolidateVO;
import com.itgrids.partyanalyst.dto.CrossVotedCandidateVO;
import com.itgrids.partyanalyst.dto.CrossVotedMandalVO;
import com.itgrids.partyanalyst.dto.CrossVotedBoothVO;
import com.itgrids.partyanalyst.dto.ResultObjectVO;


public class CrossVotingReportAjaxAction extends ActionSupport implements ServletRequestAware,ServletContextAware{

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String electionYear;
	private String party;
	private String parliamentValue;
	private String assemblyValue;
	private HttpServletRequest request;
	private List<SelectOptionVO> parliamentList;
	private CrossVotingConsolidateVO crossVotingConsolidateVO;
	private CrossVotedCandidateVO pcCrossVotedCandidateVO;
	private CrossVotedCandidateVO acCrossVotedCandidateVO;
	private List<CrossVotedMandalVO> crossVotedMandalVO;
	private List<CrossVotedBoothVO> crossVotedBoothVO;
	private ResultObjectVO resultObjectVO; 
	

	public void setServletRequest(HttpServletRequest request) {
		this.request = request;		
	}

	public void setServletContext(ServletContext arg0) {
		// TODO Auto-generated method stub
		
	}
	
	public String getElectionYear() {
		return electionYear;
	}

	public void setElectionYear(String electionYear) {
		this.electionYear = electionYear;
	}

	public String getParty() {
		return party;
	}

	public void setParty(String party) {
		this.party = party;
	}
	
	public List<SelectOptionVO> getParliamentList() {
		return parliamentList;
	}

	public void setParliamentList(List<SelectOptionVO> parliamentList) {
		this.parliamentList = parliamentList;
	}

	public String getParliamentValue() {
		return parliamentValue;
	}

	public void setParliamentValue(String parliamentValue) {
		this.parliamentValue = parliamentValue;
	}

	public String getAssemblyValue() {
		return assemblyValue;
	}

	public void setAssemblyValue(String assemblyValue) {
		this.assemblyValue = assemblyValue;
	}

	public CrossVotingConsolidateVO getCrossVotingConsolidateVO() {
		return crossVotingConsolidateVO;
	}

	public void setCrossVotingConsolidateVO(
			CrossVotingConsolidateVO crossVotingConsolidateVO) {
		this.crossVotingConsolidateVO = crossVotingConsolidateVO;
	}
	
	public ResultObjectVO getResultObjectVO() {
		return resultObjectVO;
	}

	public void setResultObjectVO(ResultObjectVO resultObjectVO) {
		this.resultObjectVO = resultObjectVO;
	}
	
	public String execute()throws Exception{
		
		System.out.println("In Execute of ajax");
		
		electionYear = request.getParameter("election");
		party = request.getParameter("party");
		parliamentValue = request.getParameter("parliamentValue");
		assemblyValue = request.getParameter("assemblyValue");
		
		resultObjectVO = new ResultObjectVO();
		System.out.println("year == "+electionYear+",party == "+party+",parliament = "+parliamentValue+",assembly = "+assemblyValue);
		
		if(electionYear != null && party != null && parliamentValue == null && assemblyValue == null)
		{
			System.out.println("IN election year");
			
			parliamentList = new ArrayList<SelectOptionVO>();
			
			
			SelectOptionVO pList1 = new SelectOptionVO();
			pList1.setId(new Long(1));
			pList1.setName("Nellore");
			
			SelectOptionVO pList2 = new SelectOptionVO();
			pList2.setId(new Long(2));
			pList2.setName("Rangareddy");
			
			parliamentList.add(pList1);
			parliamentList.add(pList2);
				
			resultObjectVO.setDataList(parliamentList);	
			
			
		}
		else if(electionYear == null && party == null && parliamentValue != null && assemblyValue == null)
		{
			System.out.println("IN parliament value");
			
			System.out.println("IN election year");
			
			parliamentList = new ArrayList<SelectOptionVO>();
			
			SelectOptionVO pList1 = new SelectOptionVO();
			pList1.setId(new Long(1));
			pList1.setName("Nellore");
			
			SelectOptionVO pList2 = new SelectOptionVO();
			pList2.setId(new Long(2));
			pList2.setName("Rangareddy");
			
			parliamentList.add(pList1);
			parliamentList.add(pList2);
			
			resultObjectVO.setDataList(parliamentList);	
			
		}
		else if(electionYear == null && party == null && parliamentValue == null && assemblyValue != null)
		{
			System.out.println("IN assembly value");
			crossVotedBoothVO = new ArrayList<CrossVotedBoothVO>();
			crossVotedMandalVO = new ArrayList<CrossVotedMandalVO>();
			
			//
			
			CrossVotedBoothVO booth1 =  new CrossVotedBoothVO();
			booth1.setAcPercentage("50");
			booth1.setAcValidVotes(new Long(15000));
			booth1.setPartNO("2");
			booth1.setPcPercentage("20");
			booth1.setPcValidVotes(new Long(14000));
			booth1.setPercentageDifference("20");
			booth1.setVillagesCovered("Nellore,Krishna");
			
			CrossVotedBoothVO booth2 =  new CrossVotedBoothVO();
			booth2.setAcPercentage("40");
			booth2.setAcValidVotes(new Long(20000));
			booth2.setPartNO("3");
			booth2.setPcPercentage("30");
			booth2.setPcValidVotes(new Long(10000));
			booth2.setPercentageDifference("25");
			booth2.setVillagesCovered("Rangareddy,Adilabad");
			
			crossVotedBoothVO.add(booth1);
			crossVotedBoothVO.add(booth2);			
			
			//
			
			CrossVotedMandalVO mandal1 = new CrossVotedMandalVO();
			mandal1.setAcEarnedVotesInMandal(new Long(20000));
			mandal1.setAcPercentageInMandal("40");
			mandal1.setAcValidVotesInMandal(new Long(18000));
			mandal1.setMandalName("Allur");
			mandal1.setPcEarnedVotesInMandal(new Long(25000));
			mandal1.setPcPercentageInMandal("25");
			mandal1.setPcValidVotesInMandal(new Long(22000));
			mandal1.setPercentageDifferenceInMandal("20");
			mandal1.setCrossVotedBooths(crossVotedBoothVO);
			
			CrossVotedMandalVO mandal2 = new CrossVotedMandalVO();
			mandal2.setAcEarnedVotesInMandal(new Long(35000));
			mandal2.setAcPercentageInMandal("60");
			mandal2.setAcValidVotesInMandal(new Long(32000));
			mandal2.setMandalName("Sullurupeta");
			mandal2.setPcEarnedVotesInMandal(new Long(60000));
			mandal2.setPcPercentageInMandal("52");
			mandal2.setPcValidVotesInMandal(new Long(55000));
			mandal2.setPercentageDifferenceInMandal("40");
			mandal2.setCrossVotedBooths(crossVotedBoothVO);

			crossVotedMandalVO.add(mandal1);
			crossVotedMandalVO.add(mandal2);
			
			
			//---
			
			pcCrossVotedCandidateVO = new CrossVotedCandidateVO();
			pcCrossVotedCandidateVO.setCandidateId(new Long(1));
			pcCrossVotedCandidateVO.setCandidateName("Siva Kumar");
			pcCrossVotedCandidateVO.setImage("image");
			pcCrossVotedCandidateVO.setRank("1");
			pcCrossVotedCandidateVO.setVotesPercentage("20");		
			
			acCrossVotedCandidateVO = new CrossVotedCandidateVO();
			acCrossVotedCandidateVO.setCandidateId(new Long(2));
			acCrossVotedCandidateVO.setCandidateName("Sai Krishna");
			acCrossVotedCandidateVO.setImage("image");
			acCrossVotedCandidateVO.setRank("1");
			acCrossVotedCandidateVO.setVotesPercentage("25");	 
			
			//--
			
			crossVotingConsolidateVO = new CrossVotingConsolidateVO();
			crossVotingConsolidateVO.setAcCandidateData(pcCrossVotedCandidateVO);
			crossVotingConsolidateVO.setPcCandidateData(acCrossVotedCandidateVO);
			crossVotingConsolidateVO.setMandals(crossVotedMandalVO);
			
			setCrossVotingConsolidateVO(crossVotingConsolidateVO);	
				
			resultObjectVO.setCrossVotingConsolidateVO(crossVotingConsolidateVO);	
		}
		
				
				
		return Action.SUCCESS;
	}

	
}
