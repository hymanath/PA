package com.itgrids.partyanalyst.web.action;

import java.math.BigDecimal;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.util.ServletContextAware;
import org.jfree.data.general.DefaultPieDataset;
import org.json.JSONObject;

import com.itgrids.partyanalyst.dto.ConstituencyInfoVO;
import com.itgrids.partyanalyst.dto.ConstituencyVO;
import com.itgrids.partyanalyst.dto.PartyResultsInVotesMarginVO;
import com.itgrids.partyanalyst.dto.PartyVotesMarginInConstituency;
import com.itgrids.partyanalyst.dto.PartyVotesMarginResultsInMandal;
import com.itgrids.partyanalyst.dto.PartyVotesMarginResultsVO;
import com.itgrids.partyanalyst.dto.VotersInfoForMandalVO;
import com.itgrids.partyanalyst.dto.VotersWithDelimitationInfoVO;
import com.itgrids.partyanalyst.dto.VotesMarginResultsMainVO;
import com.itgrids.partyanalyst.excel.booth.BoothResultVO;
import com.itgrids.partyanalyst.helper.ChartProducer;
import com.itgrids.partyanalyst.service.IConstituencyPageService;
import com.itgrids.partyanalyst.service.impl.BiElectionPageService;
import com.itgrids.partyanalyst.utils.IConstants;
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
	private IConstituencyPageService constituencyPageService;
	private ConstituencyInfoVO constituencyDetails;
	private ConstituencyVO constituencyVO;
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
	
	public IConstituencyPageService getConstituencyPageService() {
		return constituencyPageService;
	}

	public void setConstituencyPageService(
			IConstituencyPageService constituencyPageService) {
		this.constituencyPageService = constituencyPageService;
	}

	public void setBiElectionPageService(BiElectionPageService biElectionPageService) {
		this.biElectionPageService = biElectionPageService;
	}

	public BiElectionPageService getBiElectionPageService() {
		return biElectionPageService;
	}	

	public ConstituencyInfoVO getConstituencyDetails() {
		return constituencyDetails;
	}

	public void setConstituencyDetails(ConstituencyInfoVO constituencyDetails) {
		this.constituencyDetails = constituencyDetails;
	}

	public ConstituencyVO getConstituencyVO() {
		return constituencyVO;
	}

	public void setConstituencyVO(ConstituencyVO constituencyVO) {
		this.constituencyVO = constituencyVO;
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
		
		votesMarginResultsMainVO =  biElectionPageService.getVotesMarginResultsCompleteDetails(constituencyId, partyId);
		votesMarginResultsMainVO.setConstituencyVO(getVotersShareInMandalsPieChart(constituencyId));
		
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
 
 //Method To Create Votes Share In Constituency Before & After Delimitation
 public ConstituencyVO getVotersShareInMandalsPieChart(Long constituencyId){
		
		constituencyDetails = new ConstituencyInfoVO();
		constituencyDetails = constituencyPageService.getConstituencyDetails(constituencyId); 
		constituencyVO = constituencyPageService.getVotersInfoInMandalsForConstituency(constituencyId);
		String pieChart = "";
		String pieChartPath = "";
		String title = "";
		String[] chartNames = new String [constituencyVO.getAssembliesOfParliamentInfo().size()];
		String[] extraInfo = new String [constituencyVO.getAssembliesOfParliamentInfo().size()];
		int i=0;
		for(VotersWithDelimitationInfoVO votersInMandalOrAC:constituencyVO.getAssembliesOfParliamentInfo()){
			pieChart = votersInMandalOrAC.getYear()+"_Voters Info for Constituency_"+constituencyVO.getId()+"In Bi-Elections"+".png";
			pieChartPath = context.getRealPath("/")+ "charts\\" + pieChart;
			if(votersInMandalOrAC.getYear().equalsIgnoreCase(IConstants.DELIMITATION_YEAR.toString())){
				if(constituencyDetails.getConstituencyType().equalsIgnoreCase(IConstants.ASSEMBLY_ELECTION_TYPE))
					title = "Each Mandal Voters Share* After Delimitation";
				else
					title = "Each Assembly Voters Share* After Delimitation";
				extraInfo[i] = "* Based On 2009 Elections Data";
			}
			else{
				if(constituencyDetails.getConstituencyType().equalsIgnoreCase(IConstants.ASSEMBLY_ELECTION_TYPE))
					title = "Each Mandal Voters Share** Before Delimitation";
				else
					title = "Each Assembly Voters Share** Before Delimitation";
				extraInfo[i] = "** Based On 2004 Elections Data";
			}
				
			if(votersInMandalOrAC.getVotersInfoForMandalVO().size() > 0)
				ChartProducer.createProblemsPieChart(title, createPieDatasetForVoters(votersInMandalOrAC.getVotersInfoForMandalVO()), pieChartPath, null,true,260,270);
			chartNames[i++] = pieChart;
		}
		
		constituencyVO.setPieChartNames(chartNames);
		constituencyVO.setExtraInfo(extraInfo);
		
	 return constituencyVO;
	}
	
	private DefaultPieDataset createPieDatasetForVoters(List<VotersInfoForMandalVO> votersInfoForMandalVO) {
		DefaultPieDataset dataset = new DefaultPieDataset();
		Long totalVotes = 0l;
		BigDecimal percentage;
		for(VotersInfoForMandalVO votersInMandalOrAC:votersInfoForMandalVO)
			totalVotes += new Long(votersInMandalOrAC.getTotalVoters());

		for(VotersInfoForMandalVO votersInMandalOrAC:votersInfoForMandalVO){
			percentage = new BigDecimal(new Long(votersInMandalOrAC.getTotalVoters())*100.0/totalVotes).setScale(2,BigDecimal.ROUND_HALF_UP);
			dataset.setValue(votersInMandalOrAC.getMandalName()+" ["+percentage.toString()+"%]",percentage);
		}
			
		return dataset;
	}
}
