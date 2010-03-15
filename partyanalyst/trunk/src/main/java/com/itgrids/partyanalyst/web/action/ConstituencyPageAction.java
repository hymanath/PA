/* 
 * Copyright (c) 2009 IT Grids.
 * All Rights Reserved.
 *
 * IT Grids Confidential Information.
 * Created on September 24, 2009
 */
package com.itgrids.partyanalyst.web.action;

import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;
import org.apache.struts2.util.ServletContextAware;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;

import com.itgrids.partyanalyst.dto.CandidateDetailsForConstituencyTypesVO;
import com.itgrids.partyanalyst.dto.ConstituencyElectionResultsVO;
import com.itgrids.partyanalyst.dto.ConstituencyInfoVO;
import com.itgrids.partyanalyst.dto.DelimitationConstituencyMandalResultVO;
import com.itgrids.partyanalyst.dto.ElectionBasicInfoVO;
import com.itgrids.partyanalyst.dto.ElectionTrendzReportVO;
import com.itgrids.partyanalyst.dto.PartyResultsTrendzVO;
import com.itgrids.partyanalyst.dto.ProblemBeanVO;
import com.itgrids.partyanalyst.dto.VotersWithDelimitationInfoVO;
import com.itgrids.partyanalyst.helper.ChartProducer;
import com.itgrids.partyanalyst.service.IConstituencyPageService;
import com.itgrids.partyanalyst.service.IDelimitationConstituencyMandalService;
import com.itgrids.partyanalyst.service.IElectionTrendzService;
import com.itgrids.partyanalyst.service.IProblemManagementReportService;
import com.itgrids.partyanalyst.utils.IConstants;
import com.opensymphony.xwork2.ActionSupport;

public class ConstituencyPageAction extends ActionSupport implements
		ServletRequestAware, ServletResponseAware,ServletContextAware {

	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	 
	 private Long constituencyId;
	 private String constituencyName;	 
	 private List<ConstituencyElectionResultsVO> constituencyElectionResultsVO;
	 private ConstituencyInfoVO constituencyDetails;
	 private List<VotersWithDelimitationInfoVO> votersInfo;	 
	 private CandidateDetailsForConstituencyTypesVO candidateDetailsForConstituency;
	 private IProblemManagementReportService problemManagementReportService;
	 private List<ProblemBeanVO> problemBean;
	 private ElectionTrendzReportVO electionTrendzReportVO;
	 
	 private IDelimitationConstituencyMandalService delimitationConstituencyMandalService;
	 private DelimitationConstituencyMandalResultVO delimitationConstituencyMandalResultVO;	
	 
	 private ElectionBasicInfoVO electionBasicInfoVO;
	 private IElectionTrendzService electionTrendzService;
	 private static final Logger log = Logger.getLogger(ConstituencyPageAction.class);
	 
	
	 public IProblemManagementReportService getProblemManagementReportService() {
		return problemManagementReportService;
	}

	public void setProblemManagementReportService(
			IProblemManagementReportService problemManagementReportService) {
		this.problemManagementReportService = problemManagementReportService;
	}
		
	public List<ProblemBeanVO> getProblemBean() {
		return problemBean;
	}

	public void setProblemBean(List<ProblemBeanVO> problemBean) {
		this.problemBean = problemBean;
	}
	
	public CandidateDetailsForConstituencyTypesVO getCandidateDetailsForConstituency() {
			return candidateDetailsForConstituency;
	}

	public void setCandidateDetailsForConstituency(
			CandidateDetailsForConstituencyTypesVO candidateDetailsForConstituency) {
		this.candidateDetailsForConstituency = candidateDetailsForConstituency;
	}
	
	 public String getConstituencyName() {
		return constituencyName;
	}

	public void setConstituencyName(String constituencyName) {
		this.constituencyName = constituencyName;
	}

	public Long getConstituencyId() {
		return constituencyId;
	}

	public void setConstituencyId(Long constituencyId) {
		this.constituencyId = constituencyId;
	}

	public List<ConstituencyElectionResultsVO> getConstituencyElectionResultsVO() {
		return constituencyElectionResultsVO;
	}

	public void setConstituencyElectionResultsVO(
			List<ConstituencyElectionResultsVO> constituencyElectionResultsVO) {
		this.constituencyElectionResultsVO = constituencyElectionResultsVO;
	}

	public ConstituencyInfoVO getConstituencyDetails() {
		return constituencyDetails;
	}

	public void setConstituencyDetails(ConstituencyInfoVO constituencyDetails) {
		this.constituencyDetails = constituencyDetails;
	}

	public List<VotersWithDelimitationInfoVO> getVotersInfo() {
		return votersInfo;
	}

	public void setVotersInfo(List<VotersWithDelimitationInfoVO> votersInfo) {
		this.votersInfo = votersInfo;
	}

	HttpServletRequest request;
	HttpServletResponse response;
	HttpSession session;
	private ServletContext context;
	IConstituencyPageService constituencyPageService;
	
    public void setServletContext(ServletContext context) {
	   this.context = context;
	}
	
	public void setServletRequest(HttpServletRequest request) {
		this.request = request;
	}

	public void setServletResponse(HttpServletResponse response) {
		this.response = response;

	}

	public void setConstituencyPageService(
			IConstituencyPageService constituencyPageService) {
		this.constituencyPageService = constituencyPageService;
	}

	public void setDelimitationConstituencyMandalService(
			IDelimitationConstituencyMandalService delimitationConstituencyMandalService) {
		this.delimitationConstituencyMandalService = delimitationConstituencyMandalService;
	}

	
	public DelimitationConstituencyMandalResultVO getDelimitationConstituencyMandalResultVO() {
		return delimitationConstituencyMandalResultVO;
	}

	public void setDelimitationConstituencyMandalResultVO(
			DelimitationConstituencyMandalResultVO delimitationConstituencyMandalResultVO) {
		this.delimitationConstituencyMandalResultVO = delimitationConstituencyMandalResultVO;
	}

	public IElectionTrendzService getElectionTrendzService() {
		return electionTrendzService;
	}

	public void setElectionTrendzService(
			IElectionTrendzService electionTrendzService) {
		this.electionTrendzService = electionTrendzService;
	}

	public ElectionTrendzReportVO getElectionTrendzReportVO() {
		return electionTrendzReportVO;
	}

	public void setElectionTrendzReportVO(
			ElectionTrendzReportVO electionTrendzReportVO) {
		this.electionTrendzReportVO = electionTrendzReportVO;
	}

	public ElectionBasicInfoVO getElectionBasicInfoVO() {
		return electionBasicInfoVO;
	}

	public void setElectionBasicInfoVO(ElectionBasicInfoVO electionBasicInfoVO) {
		this.electionBasicInfoVO = electionBasicInfoVO;
	}

	public String execute() throws Exception{
		
				
		constituencyDetails = constituencyPageService.getConstituencyDetails(constituencyId);
				
		constituencyElectionResultsVO = constituencyPageService.getConstituencyElectionResults(constituencyId);
		
		DelimitationConstituencyMandalResultVO delimitationConstituencyMandalResultVO = delimitationConstituencyMandalService.getMandalsForDelConstituency(constituencyId);
		
		Throwable ex = delimitationConstituencyMandalResultVO.getExceptionEncountered();
		if(ex!=null){
			log.error("exception raised while retrieving mandal details ", ex);
		}
		
		log.info("delimitationConstituencyMandalResultVO.getMandals().size()::::"+delimitationConstituencyMandalResultVO.getPresentMandals().size());
		log.info("delimitationConstituencyMandalResultVO..getConstituencyType()::::"+delimitationConstituencyMandalResultVO.getConstituencyType());
		setDelimitationConstituencyMandalResultVO(delimitationConstituencyMandalResultVO);
		
		votersInfo = constituencyPageService.getVotersInfoInMandalsForConstituency(constituencyId);
		
		candidateDetailsForConstituency = constituencyPageService.getCandidateAndPartyInfoForConstituency(constituencyId);
		
		problemBean = problemManagementReportService.getConstituencyProblemsInfo(constituencyId, 0L,"");
		
	
		//electionTrendzReportVO = electionTrendzService.getVotingTrendzForAnElection(new Long(0), new Long(2), "0", new Long(1), new Long(1), new Long(232), new Long(0), new Long(0));
		System.out.println("electionTrendzReportVO ============ "+electionTrendzReportVO);
		
		if(problemBean != null)
			System.out.println("problemBean === "+problemBean.size());
		
		electionBasicInfoVO = electionTrendzService.getBasicElectionInfoFromConstituencyId(constituencyId);
		if(electionBasicInfoVO.getElectionId() != null){
			
			System.out.println("Inside trendz service call ....");
			electionTrendzReportVO = electionTrendzService.getVotingTrendzForAConstituency(electionBasicInfoVO.getElectionId(),electionBasicInfoVO.getElectionTypeId(),electionBasicInfoVO.getElectionYear(),constituencyId,IConstants.MALETRENDZ,IConstants.FEMALETRENDZ);
		    if(electionTrendzReportVO.getElectionTrendzOverviewVO() != null){
		    	 try{
		    		 
		    		String constituencyName = electionTrendzReportVO.getConstituencyName();
		    		String stateName = electionTrendzReportVO.getState();
		 			session = request.getSession();
		 			String chartId = constituencyName.concat(stateName).concat("BarChart");
		 			String barChartName = "constituencyTrendzChart_" + chartId + session.getId()+".png";
		 	        String chartPath = context.getRealPath("/") + "charts\\" + barChartName;
		 	       
		 			  	ChartProducer.createALineChartforVotingTrendzNew(constituencyName + " Voting Trendz" ,createDataset2(electionTrendzReportVO.getElectionTrendzOverviewVO().getPartyElectionTrendzVO()),createDataset1(electionTrendzReportVO.getElectionTrendzOverviewVO().getPartyElectionTrendzVO()), chartPath);
		 	        	request.setAttribute("barChartName", barChartName);
		 				session.setAttribute("barChartName", barChartName);
		 	         
		    	 }
		    	 catch(Exception exc){
		    		 exc.printStackTrace();
		    	 }
		    }
		
		
		}
		
		if(constituencyElectionResultsVO != null || constituencyDetails != null){
			//electionType = constituencyElectionResultsVO.get(0).getElectionType();
			return SUCCESS;
		}
		else
			return ERROR;
		
	}
	
	private CategoryDataset createDataset1(List<PartyResultsTrendzVO> partyResultsTrendzVO) {
		
	     final String category2 = "Male %";
	     final String category3 = "Female %";
	     final String category4 = "MaleOrFemale %";
       final DefaultCategoryDataset dataset = new DefaultCategoryDataset();
       for(PartyResultsTrendzVO result: partyResultsTrendzVO){
       	final String series =  result.getPartyName();
       	dataset.addValue(new Double(result.getMaleVotesPercent()), category2, series);
       	dataset.addValue(new Double(result.getFemaleVotesPercent()), category3, series);
    	dataset.addValue(new Double(result.getMaleAndFemaleVotesPercent()), category4, series);
       }
       return dataset;
       
   }
	
	private CategoryDataset createDataset2(List<PartyResultsTrendzVO> partyResultsTrendzVO) {
		 final String category1 =  "Total %";
	     
      final DefaultCategoryDataset dataset = new DefaultCategoryDataset();
      for(PartyResultsTrendzVO result: partyResultsTrendzVO){
      	final String series =  result.getPartyName();
      	dataset.addValue(new Double(result.getTotalVotesPercent()), category1,series );
       }
      return dataset;
      
  }

	
}
