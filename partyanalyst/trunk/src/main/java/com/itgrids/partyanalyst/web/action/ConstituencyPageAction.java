/* 
 * Copyright (c) 2009 IT Grids.
 * All Rights Reserved.
 *
 * IT Grids Confidential Information.
 * Created on September 24, 2009
 */
package com.itgrids.partyanalyst.web.action;

import java.math.BigDecimal;
import java.text.ParseException;
import java.util.ArrayList;
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
import org.jfree.data.general.DefaultPieDataset;
import org.jfree.data.general.PieDataset;
import org.json.JSONObject;

import com.itgrids.partyanalyst.dto.CandidateDetailsForConstituencyTypesVO;
import com.itgrids.partyanalyst.dto.CandidatePartyInfoVO;
import com.itgrids.partyanalyst.dto.CandidateVotingTrendzCharts;
import com.itgrids.partyanalyst.dto.ConstituencyElectionResultsVO;
import com.itgrids.partyanalyst.dto.ConstituencyInfoVO;
import com.itgrids.partyanalyst.dto.ConstituencyOrMandalWiseElectionVO;
import com.itgrids.partyanalyst.dto.ConstituencyRevenueVillagesVO;
import com.itgrids.partyanalyst.dto.DelimitationConstituencyMandalResultVO;
import com.itgrids.partyanalyst.dto.ElectionBasicInfoVO;
import com.itgrids.partyanalyst.dto.ElectionTrendzOverviewVO;
import com.itgrids.partyanalyst.dto.ElectionTrendzReportVO;
import com.itgrids.partyanalyst.dto.PartyElectionResultVO;
import com.itgrids.partyanalyst.dto.PartyResultsTrendzVO;
import com.itgrids.partyanalyst.dto.ProblemBeanVO;
import com.itgrids.partyanalyst.dto.SelectOptionVO;
import com.itgrids.partyanalyst.dto.VotersWithDelimitationInfoVO;
import com.itgrids.partyanalyst.helper.ChartProducer;
import com.itgrids.partyanalyst.service.IConstituencyPageService;
import com.itgrids.partyanalyst.service.IDelimitationConstituencyMandalService;
import com.itgrids.partyanalyst.service.IElectionTrendzService;
import com.itgrids.partyanalyst.service.IProblemManagementReportService;
import com.itgrids.partyanalyst.service.IStaticDataService;
import com.itgrids.partyanalyst.utils.IConstants;
import com.opensymphony.xwork2.Action;
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
	 private CandidateVotingTrendzCharts candidateVotingTrendzCharts;
	 private IStaticDataService staticDataService;
	 private IDelimitationConstituencyMandalService delimitationConstituencyMandalService;
	 private DelimitationConstituencyMandalResultVO delimitationConstituencyMandalResultVO;	
	 private ConstituencyRevenueVillagesVO constituencyRevenueVillagesVO;
	 private ElectionBasicInfoVO electionBasicInfoVO;
	 private IElectionTrendzService electionTrendzService;
	 private List<SelectOptionVO> electionIdsAndYears;
	 private static final Logger log = Logger.getLogger(ConstituencyPageAction.class);
	 
	 JSONObject jObj = null;
	 private String task;
	
	public String getTask() {
		return task;
	}

	public void setTask(String task) {
		this.task = task;
	}

	 private enum ChartType {pollingTrendz, pollingPercent};
	 
	
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

	public List<SelectOptionVO> getElectionIdsAndYears() {
		return electionIdsAndYears;
	}

	public void setElectionIdsAndYears(List<SelectOptionVO> electionIdsAndYears) {
		this.electionIdsAndYears = electionIdsAndYears;
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

	public CandidateVotingTrendzCharts getCandidateVotingTrendzCharts() {
		return candidateVotingTrendzCharts;
	}

	public void setCandidateVotingTrendzCharts(
			CandidateVotingTrendzCharts candidateVotingTrendzCharts) {
		this.candidateVotingTrendzCharts = candidateVotingTrendzCharts;
	}

	public ConstituencyRevenueVillagesVO getConstituencyRevenueVillagesVO() {
		return constituencyRevenueVillagesVO;
	}

	public void setConstituencyRevenueVillagesVO(
			ConstituencyRevenueVillagesVO constituencyRevenueVillagesVO) {
		this.constituencyRevenueVillagesVO = constituencyRevenueVillagesVO;
	}

	public IStaticDataService getStaticDataService() {
		return staticDataService;
	}

	public void setStaticDataService(IStaticDataService staticDataService) {
		this.staticDataService = staticDataService;
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
		
	
		System.out.println("electionTrendzReportVO ============ "+electionTrendzReportVO);
		
		if(problemBean != null)
			System.out.println("problemBean === "+problemBean.size());
		
		electionBasicInfoVO = electionTrendzService.getBasicElectionInfoFromConstituencyId(constituencyId);
		
           if(electionBasicInfoVO.getElectionId() != null){
			
			System.out.println("Inside trendz service call ....");
			electionTrendzReportVO = electionTrendzService.getVotingTrendzForAConstituency(electionBasicInfoVO.getElectionId(),electionBasicInfoVO.getElectionTypeId(),electionBasicInfoVO.getElectionYear(),constituencyId,IConstants.MALETRENDZ,IConstants.FEMALETRENDZ);
			if(electionTrendzReportVO != null)
			getMapsForVotingTrendz(electionTrendzReportVO);
			electionTrendzReportVO.setPrevElectionYearsInfo(electionTrendzService.getPreviousElectionsInfoForAConstituency(electionBasicInfoVO.getElectionYear(), constituencyId));
           }
		
		
		if(constituencyElectionResultsVO != null || constituencyDetails != null){
			return Action.SUCCESS;
		}
		else
			return Action.ERROR;
		
	}
	
	public void getMapsForVotingTrendz(ElectionTrendzReportVO electionTrendzReportVO){
        
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
		 				
		 			String pollingChartId = constituencyName.concat(stateName).concat("PieChart");
		 			String pollingChartName = "pollingPercentChart_" + pollingChartId + session.getId()+".png";
		 			String pollingChartPath = context.getRealPath("/") + "charts\\" + pollingChartName;
		 			
		 			List<CategoryDataset> dataset = new ArrayList<CategoryDataset>();
		 	        
		 	        dataset.add(createDatasetForPollingInfo1(electionTrendzReportVO.getElectionTrendzOverviewVO(),ChartType.pollingTrendz));
		 	        dataset.add(createDatasetForPollingInfo2(electionTrendzReportVO.getElectionTrendzOverviewVO(), ChartType.pollingPercent));
		 			
		 			    ChartProducer.createLineChartForPolling("Polling Trendz ..",dataset ,"","", pollingChartPath);
		 			    request.setAttribute("pollingChartName", pollingChartName);
		 				session.setAttribute("pollingChartName", pollingChartName);
		 				
		 			String wonCandChartId = constituencyName.concat(stateName).concat("PieChart");
			 		String wonCandChartName = "wonCandOverallVotesPercentChart_" + wonCandChartId + session.getId()+".png";
			 		String wonCandChartPath = context.getRealPath("/") + "charts\\" + wonCandChartName;
		 				
		 		    String wonCandTotPercent = electionTrendzReportVO.getElectionTrendzOverviewVO().getWonCandidateResultTrendz().getTotalVotesPercent();
		 		    String wonCandMalePercent = electionTrendzReportVO.getElectionTrendzOverviewVO().getWonCandidateResultTrendz().getOverallMaleVotesPercent();
		 		    String wonCandFemalePercent = electionTrendzReportVO.getElectionTrendzOverviewVO().getWonCandidateResultTrendz().getOverallFemaleVotesPercent();
		 		    String wonCandMaleOrFemalePercent = electionTrendzReportVO.getElectionTrendzOverviewVO().getWonCandidateResultTrendz().getOverallMaleOrFemaleVotesPercent();
		 		    		 		    
		 		    String maleLabelForVoting = "Male";
		 		    String femaleLabelForVotingPercent = "Female";
		 		    String maleOrFemaleLabelForVotingPercent = "Male/Female";	
		 		   
		 		        ChartProducer.createPieChart("", createPieDataSet(maleLabelForVoting,femaleLabelForVotingPercent,maleOrFemaleLabelForVotingPercent,wonCandTotPercent,wonCandMalePercent,wonCandFemalePercent,wonCandMaleOrFemalePercent), wonCandChartPath);
		 		        //ChartProducer.createBarChartForCandidateVotingTrendz("","Trendz","Votes %", createDatasetForCandTrendz(electionTrendzReportVO.getElectionTrendzOverviewVO().getWonCandidateResultTrendz().getPartyName(),null,wonCandMalePercent,wonCandFemalePercent,wonCandMaleOrFemalePercent), wonCandChartPath);
		 		        request.setAttribute("wonCandChartName", wonCandChartName);
	 				    session.setAttribute("wonCandChartName", wonCandChartName);
		 		    
	 			    String wonCandVotesChartId = constituencyName.concat(stateName).concat("PieChart");
				 	String wonCandVotesChartName = "wonCandVotesPercentChart_" + wonCandVotesChartId + session.getId()+".png";
				 	String wonCandVotesChartPath = context.getRealPath("/") + "charts\\" + wonCandVotesChartName;
				 	
				 	String wonCandTotVotesPercent = electionTrendzReportVO.getElectionTrendzOverviewVO().getWonCandidateResultTrendz().getTotalVotesPercent();
		 		    String wonCandMaleVotesPercent = electionTrendzReportVO.getElectionTrendzOverviewVO().getWonCandidateResultTrendz().getMaleVotesPercentInConstiVotes();
		 		    String wonCandFemaleVotesPercent = electionTrendzReportVO.getElectionTrendzOverviewVO().getWonCandidateResultTrendz().getFemaleVotesPercentInConstiVotes();
		 		    String wonCandMaleOrFemaleVotesPercent = electionTrendzReportVO.getElectionTrendzOverviewVO().getWonCandidateResultTrendz().getMaleOrFemaleVotesPercentInConstiVotes();

		 		       ChartProducer.createPieChart("", createPieDataSetForCandidateVotes(maleLabelForVoting,femaleLabelForVotingPercent,maleOrFemaleLabelForVotingPercent,wonCandTotVotesPercent,wonCandMaleVotesPercent,wonCandFemaleVotesPercent,wonCandMaleOrFemaleVotesPercent), wonCandVotesChartPath);
			           request.setAttribute("wonCandVotesChartName", wonCandVotesChartName);
				       session.setAttribute("wonCandVotesChartName", wonCandVotesChartName);
				       
				       candidateVotingTrendzCharts = new CandidateVotingTrendzCharts();
				       
				       candidateVotingTrendzCharts.setPollingDetailsChart(pollingChartName);
				       candidateVotingTrendzCharts.setVotingTrendzMainChart(barChartName);
				       candidateVotingTrendzCharts.setCandOverallVotesPercent(wonCandVotesChartName);
				       candidateVotingTrendzCharts.setCandVotingTrendz(wonCandChartName);
				       
				       electionTrendzReportVO.getElectionTrendzOverviewVO().setElectionTrendzCharts(candidateVotingTrendzCharts);
	 		  		 	         
		    	 }
		    	 catch(Exception exc){
		    		 exc.printStackTrace();
		    	 }
		    }
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
	
  private PieDataset createPieDataSet(String maleLabel,String femaleLabel,String maleOrFemaleLabel,String pollingPercent,String malePollPercent,String femalePollPercent,String maleOrFemalePollPercent){
	  DefaultPieDataset dataset = new DefaultPieDataset();
	  dataset.setValue(maleLabel, new Double(malePollPercent));
	  dataset.setValue(femaleLabel, new Double(femalePollPercent));
	  dataset.setValue(maleOrFemaleLabel, new Double(maleOrFemalePollPercent));
	  
	  return dataset;
  }
  
  private PieDataset createPieDataSetForCandidateVotes(String maleLabel,String femaleLabel,String maleOrFemaleLabel,String pollingPercent,String maleVotesPercent,String femaleVotesPercent,String maleOrFemaleVotesPercent){
	  
	  Double totalPercnt = new Double(100) - (new Double(maleVotesPercent) + new Double(femaleVotesPercent) + new Double(maleOrFemaleVotesPercent));
	  DefaultPieDataset dataset = new DefaultPieDataset();
	  dataset.setValue("Other Candidates Votes % Share",totalPercnt);
	  dataset.setValue(maleLabel, new Double(maleVotesPercent));
	  dataset.setValue(femaleLabel, new Double(femaleVotesPercent));
	  dataset.setValue(maleOrFemaleLabel, new Double(maleOrFemaleVotesPercent));
	  
	  return dataset;
  }
  private CategoryDataset createDatasetForPollingInfo1(ElectionTrendzOverviewVO pollingTrendz,ChartType chartType) {
      
      // row keys...
      final String series1 = "Total Voters";
      final String series2 = "Polled Votes";
      
      final String category1 = "Male";
      final String category2 = "Female";
      final String category3 = "Male/Female";
      
      // create the dataset...
      final DefaultCategoryDataset dataset = new DefaultCategoryDataset();
         
      dataset.addValue(pollingTrendz.getMaleVoters(), series1, category1);
      dataset.addValue(pollingTrendz.getFemaleVoters(), series1,category2);
      dataset.addValue(pollingTrendz.getMaleAndFemaleVoters(), series1, category3);
      
      dataset.addValue(pollingTrendz.getMalePolledVotes(), series2, category1);
      dataset.addValue(pollingTrendz.getFemalePolledVotes(), series2, category2);
      dataset.addValue(pollingTrendz.getMaleAndFemalePolledVotes(), series2, category3);
      
      	
      return dataset;
   }
  
  private CategoryDataset createDatasetForPollingInfo2(ElectionTrendzOverviewVO pollingTrendz,ChartType chartType) {
	  final String series3 =  "Polling %";
	  final String category1 = "Male";
      final String category2 = "Female";
      final String category3 = "Male/Female";

	  // create the dataset...
      final DefaultCategoryDataset dataset = new DefaultCategoryDataset();
      
      dataset.addValue(new Double(pollingTrendz.getMalePollingPercent()), series3, category1);
      dataset.addValue(new Double(pollingTrendz.getFemalePollingPercent()), series3, category2);
      dataset.addValue(new Double(pollingTrendz.getMaleAndFemalePollingPercent()), series3, category3);
      
      return dataset;
  }
  
  private CategoryDataset createDatasetForCandTrendz(String partyName,String completeVotesPercent,String maleVotesPercent,String femaleVotesPercent,String maleOrFemaleVotesPercent) {
		
	     final String category1 = "Male %";
	     final String category2 = "Female %";
	     final String category3 = "MaleOrFemale %";
    final DefaultCategoryDataset dataset = new DefaultCategoryDataset();
   
    final String series = partyName;
    dataset.addValue(new Double(maleVotesPercent), category1, series);
    dataset.addValue(new Double(femaleVotesPercent), category2, series);
 	dataset.addValue(new Double(maleOrFemaleVotesPercent), category3, series);
    
    return dataset;
  }

  public String getCandidateVotingTrendz(){
	  
	    String param=null;		
		param=request.getParameter("task");
		log.debug("param:"+param);
		
		try {
			jObj=new JSONObject(param);
			System.out.println("jObj = "+jObj);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return Action.SUCCESS;
  }
  
  public String getNextPrevCandidateVotingTrendz()
  {
		String param = null;
		String candName = "";
		String partyName = "";
		String candTotPercent = "";
		String candMalePercent = "";
		String candFemalePercent = "";
		String candMaleOrFemalePercent = "";
		String maleVotesPercentInConstiVotes = "";
		String femaleVotesPercentInConstiVotes = "";
		String maleOrFemaleVotesPercentInConstiVotes = "";
		
		
		param = getTask();
		session = request.getSession();
		
		try {
			jObj = new JSONObject(param);
			log.debug("Params :" + jObj);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		candName                = jObj.getString("candidateName");
		partyName               = jObj.getString("partyName");
		candTotPercent          = jObj.getString("totalVotesPercent");
		candMalePercent         = jObj.getString("overallMaleVotesPercent");
		candFemalePercent       = jObj.getString("overallFemaleVotesPercent"); 
		candMaleOrFemalePercent = jObj.getString("overallMaleOrFemaleVotesPercent"); 
		maleVotesPercentInConstiVotes = jObj.getString("maleVotesPercentInConstiVotes"); 
		femaleVotesPercentInConstiVotes = jObj.getString("femaleVotesPercentInConstiVotes");
		maleOrFemaleVotesPercentInConstiVotes = jObj.getString("maleOrFemaleVotesPercentInConstiVotes");
		
		log.debug("Creating Charts For " + candName);
		
		String wonCandChartIdNew = candName.concat("VotingTrendz").concat("PieChart");
 		String wonCandChartNameNew = "candOverallVotesPercentChart_" + wonCandChartIdNew + session.getId() +".png";
 		String wonCandChartPathNew = context.getRealPath("/") + "charts\\" + wonCandChartNameNew;
				
		    String maleLabelForVoting = "Male";
		    String femaleLabelForVotingPercent = "Female";
		    String maleOrFemaleLabelForVotingPercent = "Male/Female";	
		   
		        //ChartProducer.createBarChartForCandidateVotingTrendz("","Trendz","Votes %", createDatasetForCandTrendz(partyName,null,candMalePercent,candFemalePercent,candMaleOrFemalePercent), wonCandChartPathNew);
		        ChartProducer.createPieChart("", createPieDataSet(maleLabelForVoting,femaleLabelForVotingPercent,maleOrFemaleLabelForVotingPercent,"0",candMalePercent,candFemalePercent,candMaleOrFemalePercent), wonCandChartPathNew);
		        request.setAttribute("wonCandChartName", wonCandChartNameNew);
			    session.setAttribute("wonCandChartName", wonCandChartNameNew);
			    
		String wonCandVotesChartId = candName.concat("VotingTrendz").concat("PieChart");
	 	String wonCandVotesChartName = "candVotesPercentInConstiChart_" + wonCandVotesChartId + session.getId()+".png";
	 	String wonCandVotesChartPath = context.getRealPath("/") + "charts\\" + wonCandVotesChartName;
	 	
	 	   ChartProducer.createPieChart("", createPieDataSetForCandidateVotes(maleLabelForVoting,femaleLabelForVotingPercent,maleOrFemaleLabelForVotingPercent,candTotPercent,maleVotesPercentInConstiVotes,femaleVotesPercentInConstiVotes,maleOrFemaleVotesPercentInConstiVotes), wonCandVotesChartPath);
           request.setAttribute("wonCandVotesChartName", wonCandVotesChartName);
	       session.setAttribute("wonCandVotesChartName", wonCandVotesChartName);
	       
	       candidateVotingTrendzCharts = new CandidateVotingTrendzCharts();
	       
	       candidateVotingTrendzCharts.setCandOverallVotesPercent(wonCandVotesChartName);
	       candidateVotingTrendzCharts.setCandVotingTrendz(wonCandChartNameNew);
		
		return Action.SUCCESS;
  }
  
  public String getVotingTrendzForElectionYears() throws Exception{
	  
	    String param=null;		
	    String districtId = "";
		param = getTask();
		
		try {
			jObj=new JSONObject(param);
			System.out.println("jObj = "+jObj);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		electionBasicInfoVO = electionTrendzService.getBasicElectionInfoFromConstituencyId(new Long(232));
		if(electionBasicInfoVO.getElectionId() != null){
			
			System.out.println("Inside trendz service call ....");
			electionTrendzReportVO = electionTrendzService.getVotingTrendzForAConstituency(electionBasicInfoVO.getElectionId(),electionBasicInfoVO.getElectionTypeId(),electionBasicInfoVO.getElectionYear(),new Long(232),IConstants.MALETRENDZ,IConstants.FEMALETRENDZ);
			
			if(electionTrendzReportVO != null)
				getMapsForVotingTrendz(electionTrendzReportVO);
		}
		return Action.SUCCESS;
  }

  public String getConstituencyElectionYears(){
	  
	  	String param=null;		
	    param = getTask();
		
		try {
			jObj=new JSONObject(param);
			System.out.println("jObj = "+jObj);
		} catch (ParseException e) {
			e.printStackTrace();
		}
	  
	  electionIdsAndYears = staticDataService.getElectionIdsAndYearsForConstituency(jObj.getLong("constituencyId"));
	  return SUCCESS;
  }
  
  public String getParliamentConstituencyAssemblyWiseResults(){
	  
	  String param = getTask();
	  
	  try {
			jObj=new JSONObject(param);
			System.out.println("jObj = "+jObj);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		String chartTitle = null;
		String chartName = null;
		String domainAxisName = null;
		
		constituencyRevenueVillagesVO = constituencyPageService.getConstituencyElecResults(jObj.getLong("constituencyId")
				, jObj.getString("electionYear"));
		if(constituencyRevenueVillagesVO.getElectionType().equalsIgnoreCase(IConstants.ASSEMBLY_ELECTION_TYPE)){
			chartName = "partyPerformanceInAllSubLocations_"+constituencyRevenueVillagesVO.getConstituencyId()+"_"+jObj.getString("electionYear")+".png";
			chartTitle = "Mandal Wise Electon Results For "+constituencyRevenueVillagesVO.getConstituencyName()+" "+constituencyRevenueVillagesVO.getElectionType()+" Constituency"+" In "+jObj.getString("electionYear");
			domainAxisName = "Mandals";
		}else{
			chartName = "partyPerformanceInAllSubLocations_"+constituencyRevenueVillagesVO.getConstituencyId()+"_"+jObj.getString("electionYear")+".png";
			chartTitle = "Assembly Constituencies Wise Electon Results For "+constituencyRevenueVillagesVO.getConstituencyName()+" "+constituencyRevenueVillagesVO.getElectionType()+" Constituency"+" In "+jObj.getString("electionYear");
			domainAxisName = "Assembly Constituencies";
		}
		
        String chartPath = context.getRealPath("/")+ "charts\\" + chartName;
        constituencyRevenueVillagesVO.setChartPath(chartName);
        ChartProducer.createLineChart(chartTitle, domainAxisName, "Percentages", createDataset(constituencyRevenueVillagesVO), chartPath);
	  
	  return SUCCESS;
  }
  
  private CategoryDataset createDataset(ConstituencyRevenueVillagesVO constituencyObj) {
      final DefaultCategoryDataset dataset = new DefaultCategoryDataset();
      List<PartyElectionResultVO> pariesInfo = null;
      List<CandidatePartyInfoVO> candidatesInfo = constituencyObj.getCandidateNamePartyAndStatus();
  	  for(ConstituencyOrMandalWiseElectionVO constiInfoVO:constituencyObj.getConstituencyOrMandalWiseElectionVO()){
  		pariesInfo = constiInfoVO.getPartyElectionResultVOs();
  		for(int i=0; i<pariesInfo.size(); i++){
  			dataset.addValue(new BigDecimal(pariesInfo.get(i).getVotesPercentage()), candidatesInfo.get(i).getParty()+"["+candidatesInfo.get(i).getRank()+"]", constiInfoVO.getLocationName());	
  		}        		
      }
      return dataset;
  }
	
}
