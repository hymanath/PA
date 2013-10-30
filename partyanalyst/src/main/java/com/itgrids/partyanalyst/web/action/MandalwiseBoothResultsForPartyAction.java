package com.itgrids.partyanalyst.web.action;


import java.awt.Color;
import java.math.BigDecimal;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.util.ServletContextAware;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.general.DefaultPieDataset;
import org.json.JSONObject;
import com.itgrids.partyanalyst.dto.CandidateElectionResultVO;
import com.itgrids.partyanalyst.dto.ChartColorsAndDataSetVO;
import com.itgrids.partyanalyst.dto.ConstituencyElectionResultsVO;
import com.itgrids.partyanalyst.dto.ElectionResultPartyVO;
import com.itgrids.partyanalyst.dto.ConstituencyInfoVO;
import com.itgrids.partyanalyst.dto.ConstituencyVO;
import com.itgrids.partyanalyst.dto.PartyResultsInVotesMarginVO;
import com.itgrids.partyanalyst.dto.PartyResultsVO;
import com.itgrids.partyanalyst.dto.PartyVotesMarginInConstituency;
import com.itgrids.partyanalyst.dto.PartyVotesMarginResultsInMandal;
import com.itgrids.partyanalyst.dto.PartyVotesMarginResultsVO;
import com.itgrids.partyanalyst.dto.VotersInfoForMandalVO;
import com.itgrids.partyanalyst.dto.VotersWithDelimitationInfoVO;
import com.itgrids.partyanalyst.dto.VotesMarginResultsMainVO;
import com.itgrids.partyanalyst.excel.booth.BoothResultVO;
import com.itgrids.partyanalyst.helper.ChartProducer;
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
	private static final Logger log = Logger.getLogger(MandalwiseBoothResultsForPartyAction.class);
	private int errorFlag=0;
	private String chartProducerURL="/var/www/vsites/partyanalyst.com/httpdocs/charts/";
	public int getErrorFlag() {
		return errorFlag;
	}

	public void setErrorFlag(int errorFlag) {
		this.errorFlag = errorFlag;
	}

	public static Logger getLog() {
		return log;
	}

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
		getMandalWiseGraphsForTheConstituency(votesMarginResultsMainVO);
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

 	public VotesMarginResultsMainVO  getMandalWiseGraphsForTheConstituency(VotesMarginResultsMainVO votesMarginResultsMainVO){
 		String cPath = request.getContextPath();
 		String chartPath="";
		try{
			for(int i=0;i<votesMarginResultsMainVO.getPartyVotesMarginResultsInMandal().size();i++){
				String chartName = votesMarginResultsMainVO.getPartyVotesMarginResultsInMandal().get(i).getChartName();
					
				if(cPath.contains("PartyAnalyst"))
					 chartPath = context.getRealPath("/")+ "charts\\" + chartName;
					else
					 chartPath = chartProducerURL + chartName;
				
		 	    String title = "Mandal Wise Election Result For "+votesMarginResultsMainVO.getPartyVotesMarginResultsInMandal().get(i).getMandalName()+" Mandal";		 	    
		        ChartColorsAndDataSetVO chartColorsAndDataSetVO = createDataSetForGraph(votesMarginResultsMainVO,i);
		        
				ChartProducer.createLineChart(title,"Election Type","Range", (DefaultCategoryDataset)chartColorsAndDataSetVO.getDataSet(),chartPath,320,980, new ArrayList<Color>(chartColorsAndDataSetVO.getColorsSet()),false);
			} 
		}catch(Exception ex){
			ex.printStackTrace();
		}
		return votesMarginResultsMainVO;
 	}
 	
 	private ChartColorsAndDataSetVO createDataSetForGraph(VotesMarginResultsMainVO votesMarginResultsMainVO,int mandalCount){		
		List<PartyVotesMarginInConstituency> partyResults = new ArrayList<PartyVotesMarginInConstituency>();
		List<PartyVotesMarginResultsInMandal> mandalResult = new  ArrayList<PartyVotesMarginResultsInMandal>();
		
		for(int i=mandalCount;i<=mandalCount;i++){
				String mandalName = votesMarginResultsMainVO.getPartyVotesMarginResultsInMandal().get(i).getMandalName();
				if(votesMarginResultsMainVO.getPartyVotesMarginResultsInMandal().get(i).getPartyVotesMarginResultsVO()!=null){
					for(int j=0;j<votesMarginResultsMainVO.getPartyVotesMarginResultsInMandal().get(i).getPartyVotesMarginResultsVO().size();j++){					
						String electionType = votesMarginResultsMainVO.getPartyVotesMarginResultsInMandal().get(i).getPartyVotesMarginResultsVO().get(j).getElecionType();
						String electionYear = votesMarginResultsMainVO.getPartyVotesMarginResultsInMandal().get(i).getPartyVotesMarginResultsVO().get(j).getElectionYear();
						if(votesMarginResultsMainVO.getPartyVotesMarginResultsInMandal().get(i).getPartyVotesMarginResultsVO().get(j).getPartyVotesMarginInConstituency().size()>1){
								partyResults.addAll(votesMarginResultsMainVO.getPartyVotesMarginResultsInMandal().get(i).getPartyVotesMarginResultsVO().get(j).getPartyVotesMarginInConstituency());
								mandalResult.addAll(setElectionVoForGraphs(getBoothCount(partyResults),electionType,electionYear,mandalName));
								partyResults.clear();							
							}else{	
								mandalResult.addAll(setElectionVoForGraphs(votesMarginResultsMainVO.getPartyVotesMarginResultsInMandal().get(i).getPartyVotesMarginResultsVO().get(j).getPartyVotesMarginInConstituency().get(0).getPartyResultsInVotesMarginVO(),electionType,electionYear,mandalName));
							}
					}		
				}else{
					errorFlag = 1;
				}
						
			}
			
		/*for(int m=0;m<mandalResult.size();m++){	
 			System.out.println("====================================");
 			System.out.println(mandalResult.get(m).getMandalName());
 			System.out.println("====================================");
	 		for(int i=0;i<mandalResult.get(m).getPartyVotesMarginResultsVO().size();i++){
	 			System.out.println(mandalResult.get(m).getPartyVotesMarginResultsVO().get(i).getElecionType());
	 			System.out.println(mandalResult.get(m).getPartyVotesMarginResultsVO().get(i).getElectionYear());
	 			for(int j=0;j<mandalResult.get(m).getPartyVotesMarginResultsVO().get(i).getPartyVotesMarginInConstituency().size();j++){
	 				for(int k=0;k<mandalResult.get(m).getPartyVotesMarginResultsVO().get(i).getPartyVotesMarginInConstituency().get(j).getPartyResultsInVotesMarginVO().size();k++){
	 					int margin1 = mandalResult.get(m).getPartyVotesMarginResultsVO().get(i).getPartyVotesMarginInConstituency().get(j).getPartyResultsInVotesMarginVO().get(k).getMarginValue1();
	 					int margin2 = mandalResult.get(m).getPartyVotesMarginResultsVO().get(i).getPartyVotesMarginInConstituency().get(j).getPartyResultsInVotesMarginVO().get(k).getMarginValue2();
	 					int resultCount = mandalResult.get(m).getPartyVotesMarginResultsVO().get(i).getPartyVotesMarginInConstituency().get(j).getPartyResultsInVotesMarginVO().get(k).getResultsCount();
	 					System.out.println(margin1+"\t\t"+margin2+"\t\t"+resultCount);
	 				}
	 			}
	 			System.out.println("====================================");
	 			System.out.println("====================================");
	 		}
 		}*/
		
		final DefaultCategoryDataset dataset = new DefaultCategoryDataset();
		ChartColorsAndDataSetVO chartColorsAndDataSetVO = new ChartColorsAndDataSetVO();
		Set<Color> colorsSet = new LinkedHashSet<Color>();
		
		
		for(PartyVotesMarginResultsInMandal consti:mandalResult){
			for(PartyVotesMarginResultsVO category:consti.getPartyVotesMarginResultsVO()){
				if( 
						(IConstants.PARLIAMENT_ELECTION_TYPE.equalsIgnoreCase(category.getElecionType()))
												&&
						 (IConstants.PRESENT_ELECTION_YEAR.equalsIgnoreCase(category.getElectionYear())))
							{	
								colorsSet.add(Color.BLUE);
							}
				
	        	else
	        		if(
	        				(IConstants.PARLIAMENT_ELECTION_TYPE.equalsIgnoreCase(category.getElecionType()))
	        									&&
							(IConstants.PREVIOUS_ELECTION_YEAR.equalsIgnoreCase(category.getElectionYear())))
							{	
								colorsSet.add(Color.GREEN);
							}
	            else
	            	if(
	            			(IConstants.ASSEMBLY_ELECTION_TYPE.equalsIgnoreCase(category.getElecionType()))
	            								&&
							(IConstants.PRESENT_ELECTION_YEAR.equalsIgnoreCase(category.getElectionYear())))
							{	
								colorsSet.add(Color.MAGENTA);
							}
	            else
	            	if(
	            			(IConstants.ASSEMBLY_ELECTION_TYPE.equalsIgnoreCase(category.getElecionType()))
	            								&&
							(IConstants.PREVIOUS_ELECTION_YEAR.equalsIgnoreCase(category.getElectionYear())))
							{	
								colorsSet.add(Color.red);
							}
				
				for(int h=0;h<category.getPartyVotesMarginInConstituency().get(0).getPartyResultsInVotesMarginVO().size();h++){
					int margin1 = category.getPartyVotesMarginInConstituency().get(0).getPartyResultsInVotesMarginVO().get(h).getMarginValue1();
					int margin2 = category.getPartyVotesMarginInConstituency().get(0).getPartyResultsInVotesMarginVO().get(h).getMarginValue2();
					int marginCount = category.getPartyVotesMarginInConstituency().get(0).getPartyResultsInVotesMarginVO().get(h).getResultsCount();
					
					dataset.addValue(marginCount,(category.getElecionType()+"-"+category.getElectionYear()),(margin1+"-"+margin2));
				}
				
			}
		}
		chartColorsAndDataSetVO.setDataSet(dataset);
        chartColorsAndDataSetVO.setColorsSet(colorsSet);	
		return chartColorsAndDataSetVO;
	}
 	
 	public List<PartyVotesMarginResultsInMandal> setElectionVoForGraphs(List<PartyResultsInVotesMarginVO> mandalElection,String electionType,String electionYear,String mandalName){
 		 		
 		
 		List<PartyVotesMarginInConstituency> partyVotesMargin = new ArrayList<PartyVotesMarginInConstituency>(0);
 		PartyVotesMarginInConstituency votesMargin = new PartyVotesMarginInConstituency();
 		votesMargin.setPartyResultsInVotesMarginVO(mandalElection);
 		partyVotesMargin.add(votesMargin);
 		
 		
 		List<PartyVotesMarginResultsVO> mandalElectionVo = new ArrayList<PartyVotesMarginResultsVO>();
 		PartyVotesMarginResultsVO partyVotes =  new PartyVotesMarginResultsVO();
 		partyVotes.setElecionType(electionType);
 		partyVotes.setElectionYear(electionYear);
 		partyVotes.setPartyVotesMarginInConstituency(partyVotesMargin);
 		mandalElectionVo.add(partyVotes);
 		
 		List<PartyVotesMarginResultsInMandal> mandalResult = new ArrayList<PartyVotesMarginResultsInMandal>(0);
 		PartyVotesMarginResultsInMandal resultsInMandal = new PartyVotesMarginResultsInMandal();
 		resultsInMandal.setMandalName(mandalName);
 		resultsInMandal.setPartyVotesMarginResultsVO(mandalElectionVo);
 		mandalResult.add(resultsInMandal);
 		
 		
 		return mandalResult;
 	}
 	
 	private List<PartyResultsInVotesMarginVO>  getBoothCount(List<PartyVotesMarginInConstituency> partyResults) {
 		List<PartyResultsInVotesMarginVO> partyResultsMarginVotes = new ArrayList<PartyResultsInVotesMarginVO>(0);
 		for(int i=0;i<partyResults.size();i++){
 			for(int j=0;j<partyResults.get(i).getPartyResultsInVotesMarginVO().size();j++){
 				int margin1 = partyResults.get(i).getPartyResultsInVotesMarginVO().get(j).getMarginValue1();
 	 			int margin2 = partyResults.get(i).getPartyResultsInVotesMarginVO().get(j).getMarginValue2();
 				int marginCount = partyResults.get(i).getPartyResultsInVotesMarginVO().get(j).getResultsCount(); 				
 				if(i==0){
 					PartyResultsInVotesMarginVO result = new PartyResultsInVotesMarginVO();
 					result.setMarginValue1(margin1);
 					result.setMarginValue2(margin2);
 					result.setResultsCount(marginCount);
 					partyResultsMarginVotes.add(result);
 				}			
			}
 		}
 		for(int i=1;i<partyResults.size();i++){ 		
 			for(int j=0;j<partyResults.get(i).getPartyResultsInVotesMarginVO().size();j++){ 		
 				for(int k=0;k<partyResultsMarginVotes.size();k++){ 					
 					if(partyResults.get(i).getPartyResultsInVotesMarginVO().get(j).getMarginValue1()==partyResultsMarginVotes.get(k).getMarginValue1()){	
 						partyResultsMarginVotes.get(k).setResultsCount(partyResultsMarginVotes.get(k).getResultsCount()+partyResults.get(i).getPartyResultsInVotesMarginVO().get(j).getResultsCount());	
 				}				
			}	
		}									
	}
 	
 	return partyResultsMarginVotes;
 }
 	

 
 //Method To Create Votes Share In Constituency Before & After Delimitation
 public ConstituencyVO getVotersShareInMandalsPieChart(Long constituencyId){
		
		constituencyDetails = new ConstituencyInfoVO();
		constituencyDetails = constituencyPageService.getConstituencyDetails(constituencyId); 
		constituencyVO = constituencyPageService.getVotersInfoInMandalsForConstituency(constituencyId);
		String cPath = request.getContextPath();

		String pieChart = "";
		String pieChartPath = "";
		String title = "";
		String[] chartNames = new String [constituencyVO.getAssembliesOfParliamentInfo().size()];
		String[] extraInfo = new String [constituencyVO.getAssembliesOfParliamentInfo().size()];
		int i=0;
		for(VotersWithDelimitationInfoVO votersInMandalOrAC:constituencyVO.getAssembliesOfParliamentInfo()){
			pieChart = votersInMandalOrAC.getYear()+"_Voters Info for Constituency_"+constituencyVO.getId()+"In Bi-Elections"+".png";
			
			if(cPath.contains("PartyAnalyst"))
				pieChartPath = context.getRealPath("/")+ "charts\\" + pieChart;
			else
				pieChartPath = chartProducerURL+ pieChart;
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
