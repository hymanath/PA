package com.itgrids.partyanalyst.web.action;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.util.ServletContextAware;
import org.json.JSONObject;

import com.itgrids.partyanalyst.dto.PartyResultsInVotesMarginVO;
import com.itgrids.partyanalyst.dto.PartyVotesMarginInConstituency;
import com.itgrids.partyanalyst.dto.PartyVotesMarginResultsInMandal;
import com.itgrids.partyanalyst.dto.PartyVotesMarginResultsVO;
import com.itgrids.partyanalyst.dto.VotesMarginResultsMainVO;
import com.itgrids.partyanalyst.excel.booth.BoothResultVO;
import com.itgrids.partyanalyst.service.impl.BiElectionPageService;
import com.opensymphony.xwork2.ActionSupport;

public class MandalwiseBoothResultsForPartyAction extends ActionSupport implements
ServletRequestAware, ServletContextAware{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private ServletContext context;
	HttpServletRequest request;
	HttpServletResponse response;
	private Long constituencyId;
	private String constituencyName;
	private Long partyId;
	private String partyName;	
	private VotesMarginResultsMainVO votesMarginResultsMainVO;	
	private BiElectionPageService biElectionPageService;
	private String task;
	org.json.JSONObject jObj;

	
	public void setServletRequest(HttpServletRequest request) {
		this.request = request;		
	}
	
	public void setServletContext(ServletContext context) {
	this.context = context;
		
	}

	public ServletContext getContext() {
		return context;
	}

	public void setContext(ServletContext context) {
		this.context = context;
	}

	public HttpServletRequest getRequest() {
		return request;
	}

	public void setRequest(HttpServletRequest request) {
		this.request = request;
	}

	public HttpServletResponse getResponse() {
		return response;
	}

	public void setResponse(HttpServletResponse response) {
		this.response = response;
	}	
	
	public Long getConstituencyId() {
		return constituencyId;
	}

	public void setConstituencyId(Long constituencyId) {
		this.constituencyId = constituencyId;
	}

	public Long getPartyId() {
		return partyId;
	}

	public void setPartyId(Long partyId) {
		this.partyId = partyId;
	}
	

	public void setVotesMarginResultsMainVO(VotesMarginResultsMainVO votesMarginResultsMainVO) {
		this.votesMarginResultsMainVO = votesMarginResultsMainVO;
	}

	public VotesMarginResultsMainVO getVotesMarginResultsMainVO() {
		return votesMarginResultsMainVO;
	}

	public void setConstituencyName(String constituencyName) {
		this.constituencyName = constituencyName;
	}

	public String getConstituencyName() {
		return constituencyName;
	}

	public void setPartyName(String partyName) {
		this.partyName = partyName;
	}

	public String getPartyName() {
		return partyName;
	}
	
	public void setBiElectionPageService(BiElectionPageService biElectionPageService) {
		this.biElectionPageService = biElectionPageService;
	}

	public BiElectionPageService getBiElectionPageService() {
		return biElectionPageService;
	}	

	public String getTask() {
		return task;
	}

	public void setTask(String task) {
		this.task = task;
	}

	public org.json.JSONObject getJObj() {
		return jObj;
	}

	public void setJObj(org.json.JSONObject obj) {
		jObj = obj;
	}

	public String execute(){
		votesMarginResultsMainVO = new VotesMarginResultsMainVO();
		/*
		//List
		List<PartyVotesMarginResultsInMandal> partyVotesMarginResultsInMandalList = new ArrayList<PartyVotesMarginResultsInMandal>();
		//Object
		PartyVotesMarginResultsInMandal	partyVotesMarginResultsInMandal1 = new PartyVotesMarginResultsInMandal();
		
		//List
		List<PartyVotesMarginResultsVO> partyVotesMarginResultsVOList = new ArrayList<PartyVotesMarginResultsVO>();
		//objects
		PartyVotesMarginResultsVO partyVotesMarginResultsVO1 = new PartyVotesMarginResultsVO();
		PartyVotesMarginResultsVO partyVotesMarginResultsVO2 = new PartyVotesMarginResultsVO();	
		PartyVotesMarginResultsVO partyVotesMarginResultsVO3 = new PartyVotesMarginResultsVO();
		PartyVotesMarginResultsVO partyVotesMarginResultsVO4 = new PartyVotesMarginResultsVO();
		
		//List
		List<PartyVotesMarginInConstituency> partyVotesMarginInConstituencyList2009A = new ArrayList<PartyVotesMarginInConstituency>();
		List<PartyVotesMarginInConstituency> partyVotesMarginInConstituencyList2009P = new ArrayList<PartyVotesMarginInConstituency>();
		List<PartyVotesMarginInConstituency> partyVotesMarginInConstituencyList2004A = new ArrayList<PartyVotesMarginInConstituency>();
		
		//objects		
		PartyVotesMarginInConstituency partyVotesMarginInConstituency1 = new PartyVotesMarginInConstituency();
		PartyVotesMarginInConstituency partyVotesMarginInConstituency2 = new PartyVotesMarginInConstituency();
		PartyVotesMarginInConstituency partyVotesMarginInConstituency3 = new PartyVotesMarginInConstituency();
		PartyVotesMarginInConstituency partyVotesMarginInConstituency4 = new PartyVotesMarginInConstituency();
				
		//List
		List<PartyResultsInVotesMarginVO> partyResultsInVotesMarginVOList = new ArrayList<PartyResultsInVotesMarginVO>();
		//objects
		PartyResultsInVotesMarginVO partyResultsInVotesMarginVO1 = new PartyResultsInVotesMarginVO();
		PartyResultsInVotesMarginVO partyResultsInVotesMarginVO2 = new PartyResultsInVotesMarginVO();
		PartyResultsInVotesMarginVO partyResultsInVotesMarginVO3 = new PartyResultsInVotesMarginVO();
		PartyResultsInVotesMarginVO partyResultsInVotesMarginVO4 = new PartyResultsInVotesMarginVO();
				
		//booth results List		
		List<BoothResultVO> boothResultsList = new ArrayList<BoothResultVO>();
		
		//Independent booth results in mandal
		BoothResultVO boothResults1 = new BoothResultVO();
		BoothResultVO boothResults2 = new BoothResultVO();
		BoothResultVO boothResults3 = new BoothResultVO();
		BoothResultVO boothResults4 = new BoothResultVO();
		BoothResultVO boothResults5 = new BoothResultVO();
		
		boothResults1.setPartNo("220");
		boothResults1.setLocation("MPPS, Bairapur");
		boothResults1.setVillagesCovered("Bairapur");
		boothResults1.setVotesEarned(294);
		boothResults1.setTotalVoters(447);
		boothResults1.setPercentage("65.77");
		boothResults1.setMandal("Nizamabad");
		boothResults1.setOppPartyId(47l);
		boothResults1.setOppParty("TDP");
		boothResults1.setOppPartyVotesEarned(445);
		boothResults1.setOppPartyPercentage("19.2");		
		boothResultsList.add(boothResults1);
		
		boothResults2.setPartNo("191");
		boothResults2.setLocation("MPPS, Nayalkal");
		boothResults2.setVillagesCovered("Nayalkal");
		boothResults2.setVotesEarned(546);
		boothResults2.setTotalVoters(897);
		boothResults2.setPercentage("60.87");
		boothResults2.setMandal("Nizamabad");
		boothResults2.setOppPartyId(47l);
		boothResults2.setOppParty("TDP");
		boothResults2.setOppPartyVotesEarned(445);
		boothResults2.setOppPartyPercentage("19.2");
		
		boothResultsList.add(boothResults2);
		
		boothResults3.setPartNo("184");
		boothResults3.setLocation("MPUPS, Madhav Nagar");
		boothResults3.setVillagesCovered("Pangra");
		boothResults3.setVotesEarned(297);
		boothResults3.setTotalVoters(589);
		boothResults3.setPercentage("50.42");
		boothResults3.setMandal("Nizamabad");
		boothResults3.setOppPartyId(47l);
		boothResults3.setOppParty("TDP");
		boothResults3.setOppPartyVotesEarned(445);
		boothResults3.setOppPartyPercentage("19.2");
		
		boothResultsList.add(boothResults3);
		
		boothResults4.setPartNo("207");
		boothResults4.setLocation("MPPS, Narsingpally");
		boothResults4.setVillagesCovered("Narsingpally Hamlet of Mudakpally");
		boothResults4.setVotesEarned(337);
		boothResults4.setTotalVoters(668);
		boothResults4.setPercentage("50.45");
		boothResults4.setMandal("Nizamabad");
		boothResults4.setOppPartyId(47l);
		boothResults4.setOppParty("TDP");
		boothResults4.setOppPartyVotesEarned(445);
		boothResults4.setOppPartyPercentage("19.2");
		
		boothResultsList.add(boothResults4);
		
		boothResults5.setPartNo("201");
		boothResults5.setLocation("MPUPS, Sirpur");
		boothResults5.setVillagesCovered("Sirpur");
		boothResults5.setVotesEarned(406);
		boothResults5.setTotalVoters(607);
		boothResults5.setPercentage("66.89");
		boothResults5.setMandal("Nizamabad");
		boothResults5.setOppPartyId(47l);
		boothResults5.setOppParty("TDP");
		boothResults5.setOppPartyVotesEarned(445);
		boothResults5.setOppPartyPercentage("19.2");
		
		boothResultsList.add(boothResults5);
		
		partyResultsInVotesMarginVO1.setMarginValue1(0);
		partyResultsInVotesMarginVO1.setMarginValue2(5);
		partyResultsInVotesMarginVO1.setResultsCount(10);
		partyResultsInVotesMarginVO1.setBoothResultsVO(boothResultsList);
		
		partyResultsInVotesMarginVOList.add(partyResultsInVotesMarginVO1);
		
		partyResultsInVotesMarginVO2.setMarginValue1(5);
		partyResultsInVotesMarginVO2.setMarginValue2(10);
		partyResultsInVotesMarginVO2.setResultsCount(5);
		partyResultsInVotesMarginVO2.setBoothResultsVO(boothResultsList);
		
		partyResultsInVotesMarginVOList.add(partyResultsInVotesMarginVO2);
		
		partyResultsInVotesMarginVO3.setMarginValue1(10);
		partyResultsInVotesMarginVO3.setMarginValue2(15);
		partyResultsInVotesMarginVO3.setResultsCount(7);
		partyResultsInVotesMarginVO3.setBoothResultsVO(boothResultsList);
		
		partyResultsInVotesMarginVOList.add(partyResultsInVotesMarginVO3);
		
		partyResultsInVotesMarginVO4.setMarginValue1(15);
		partyResultsInVotesMarginVO4.setMarginValue2(20);
		partyResultsInVotesMarginVO4.setResultsCount(2);
		partyResultsInVotesMarginVO4.setBoothResultsVO(boothResultsList);
		
		partyResultsInVotesMarginVOList.add(partyResultsInVotesMarginVO4);
	
		partyVotesMarginInConstituency1.setConstituencyId(2L);
		partyVotesMarginInConstituency1.setConstituencyName("NIZAMABAD URBAN");
		partyVotesMarginInConstituency1.setPartyResultsInVotesMarginVO(partyResultsInVotesMarginVOList);
		
		partyVotesMarginInConstituencyList2009A.add(partyVotesMarginInConstituency1);
		
		partyVotesMarginInConstituency2.setConstituencyId(2L);
		partyVotesMarginInConstituency2.setConstituencyName("NIZAMABAD RURAL");
		partyVotesMarginInConstituency2.setPartyResultsInVotesMarginVO(partyResultsInVotesMarginVOList);
		
		partyVotesMarginInConstituencyList2009A.add(partyVotesMarginInConstituency2);
		
		partyVotesMarginInConstituency3.setConstituencyId(3L);
		partyVotesMarginInConstituency3.setConstituencyName("NIZAMABAD");
		partyVotesMarginInConstituency3.setPartyResultsInVotesMarginVO(partyResultsInVotesMarginVOList);
		
		partyVotesMarginInConstituencyList2009P.add(partyVotesMarginInConstituency3);
		partyVotesMarginInConstituencyList2004A.add(partyVotesMarginInConstituency3);
		
		partyVotesMarginInConstituency3.setConstituencyId(4L);
		partyVotesMarginInConstituency3.setConstituencyName("DICHPALLI");
		partyVotesMarginInConstituency3.setPartyResultsInVotesMarginVO(partyResultsInVotesMarginVOList);
		partyVotesMarginInConstituencyList2004A.add(partyVotesMarginInConstituency4);
		
		
		partyVotesMarginResultsVO1.setElectionId(1L);
		partyVotesMarginResultsVO1.setElecionType("Assembly");
		partyVotesMarginResultsVO1.setElectionYear("2009");
		partyVotesMarginResultsVO1.setPartyVotesMarginInConstituency(partyVotesMarginInConstituencyList2009A);
		
		partyVotesMarginResultsVOList.add(partyVotesMarginResultsVO1);
		
		partyVotesMarginResultsVO2.setElectionId(2L);
		partyVotesMarginResultsVO2.setElecionType("Parliament");
		partyVotesMarginResultsVO2.setElectionYear("2009");
		partyVotesMarginResultsVO2.setPartyVotesMarginInConstituency(partyVotesMarginInConstituencyList2009P);
		
		partyVotesMarginResultsVOList.add(partyVotesMarginResultsVO2);
		
		partyVotesMarginResultsVO3.setElectionId(3L);
		partyVotesMarginResultsVO3.setElecionType("Assembly");
		partyVotesMarginResultsVO3.setElectionYear("2004");
		partyVotesMarginResultsVO3.setPartyVotesMarginInConstituency(partyVotesMarginInConstituencyList2004A);
		
		partyVotesMarginResultsVOList.add(partyVotesMarginResultsVO3);
		
		partyVotesMarginResultsVO4.setElectionId(4L);
		partyVotesMarginResultsVO4.setElecionType("Parliament");
		partyVotesMarginResultsVO4.setElectionYear("2004");
		partyVotesMarginResultsVO4.setPartyVotesMarginInConstituency(partyVotesMarginInConstituencyList2009P);
		
		partyVotesMarginResultsVOList.add(partyVotesMarginResultsVO4);
			
		
		
		partyVotesMarginResultsInMandal1.setMandalId(1L);
		partyVotesMarginResultsInMandal1.setMandalName("Nizamabad");
		partyVotesMarginResultsInMandal1.setPartyVotesMarginResultsVO(partyVotesMarginResultsVOList);
		
		partyVotesMarginResultsInMandalList.add(partyVotesMarginResultsInMandal1);
				
		
		votesMarginResultsMainVO.setConstituencyId(1L);
		votesMarginResultsMainVO.setConstituencyName("NIZAMABAD URBAN");
		votesMarginResultsMainVO.setPartyVotesMarginResultsInMandal(partyVotesMarginResultsInMandalList);
		*/
		votesMarginResultsMainVO =  biElectionPageService.getVotesMarginResultsCompleteDetails(constituencyId, partyId);
		return SUCCESS;
	}	
 public String ajaxCall() throws Exception
 {
	 String param=null;			    
		param = getTask();
		
		try {
			jObj=new JSONObject(param);
			System.out.println("jObj = "+jObj);
		} catch (ParseException e) {
			e.printStackTrace();
			
		}
		Long constId = new Long(jObj.getString("constituencyId"));
		Long partyId = new Long(jObj.getString("partyId"));	
		votesMarginResultsMainVO =  biElectionPageService.getVotesMarginResultsCompleteDetails(constId, partyId);
	 return  SUCCESS;
 }
}
