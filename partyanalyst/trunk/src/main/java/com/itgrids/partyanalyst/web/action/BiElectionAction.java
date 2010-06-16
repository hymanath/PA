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
import javax.servlet.http.HttpSession;



import org.apache.log4j.Logger;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.general.DefaultPieDataset;
import org.apache.struts2.util.ServletContextAware;
import org.json.JSONObject;

import com.itgrids.partyanalyst.dto.BiElectionDistrictVO;
import com.itgrids.partyanalyst.dto.BiElectionResultsMainVO;
import com.itgrids.partyanalyst.dto.BiElectionResultsVO;
import com.itgrids.partyanalyst.dto.CandidateElectionResultVO;
import com.itgrids.partyanalyst.dto.ConstituencyInfoVO;
import com.itgrids.partyanalyst.dto.ConstituencyRevenueVillagesVO;
import com.itgrids.partyanalyst.dto.ConstituencyVO;
import com.itgrids.partyanalyst.dto.ElectionResultPartyVO;
import com.itgrids.partyanalyst.dto.ChartColorsAndDataSetVO;
import com.itgrids.partyanalyst.dto.ConstituencyElectionResultsVO;
import com.itgrids.partyanalyst.dto.MandalAllElectionResultsVO;
import com.itgrids.partyanalyst.dto.PartyResultsVO;
import com.itgrids.partyanalyst.dto.SelectOptionVO;
import com.itgrids.partyanalyst.dto.VotersInfoForMandalVO;
import com.itgrids.partyanalyst.dto.VotersWithDelimitationInfoVO;
import com.itgrids.partyanalyst.helper.ChartProducer;
import com.itgrids.partyanalyst.service.IBiElectionPageService;
import com.itgrids.partyanalyst.service.IConstituencyPageService;
import com.itgrids.partyanalyst.service.IStaticDataService;
import com.itgrids.partyanalyst.utils.IConstants;
import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionSupport;

public class BiElectionAction extends ActionSupport implements
		ServletRequestAware, ServletContextAware {

	
	/**
	 * 
	 */
	
	private static final long serialVersionUID = 1L;

	private static final Logger log = Logger.getLogger(BiElectionAction.class);
	private IBiElectionPageService biElectionPageService;
	private IConstituencyPageService constituencyPageService;
	private List<BiElectionDistrictVO> districtsAndConsts;

	private ServletContext context;
	private IStaticDataService staticDataService;
	private List<ConstituencyElectionResultsVO> biElectionAssemblyConstPresentYearResults;
	private List<ConstituencyElectionResultsVO> biElectionAssemblyConstPreviousYearResults;
	private String presentYearResultsChartName;
	private String previousYearResultsChartName;
	private String chartPath;
	HttpServletRequest request;
	HttpServletResponse response;
	HttpSession session;
	private String task;
	org.json.JSONObject jObj;
	List<MandalAllElectionResultsVO> mandalAllElectionResultsVO;
	List<BiElectionResultsVO> biElectionResultsVO;
	List<CandidateElectionResultVO> winningCandidatesList;
	private BiElectionResultsMainVO biElectionResultsMainVO;
	private String electionYear;
	private String electionType;
	private ConstituencyInfoVO constituencyDetails;
	private ConstituencyVO constituencyVO; 
	
	private String mandalWiseResultsChart;
	private List<SelectOptionVO> zptcElectionYears;
	private List<SelectOptionVO> mptcElectionYears;  
	private List<SelectOptionVO> electionTypes;
	private Long zptcElectionId; 
	private Long mptcElectionId;
	private String mptcElectionType,zptcElectionType;

	public List<BiElectionResultsVO> getBiElectionResultsVO() {
		return biElectionResultsVO;
	}

	public void setBiElectionResultsVO(List<BiElectionResultsVO> biElectionResultsVO) {
		this.biElectionResultsVO = biElectionResultsVO;
	}

	public IConstituencyPageService getConstituencyPageService() {
		return constituencyPageService;
	}

	public void setConstituencyPageService(
			IConstituencyPageService constituencyPageService) {
		this.constituencyPageService = constituencyPageService;
	}

	public ConstituencyInfoVO getConstituencyDetails() {
		return constituencyDetails;
	}

	public void setConstituencyDetails(ConstituencyInfoVO constituencyDetails) {
		this.constituencyDetails = constituencyDetails;
	}

	public void setServletContext(ServletContext context) {
		this.context = context;		
	}
	
	public BiElectionResultsMainVO getBiElectionResultsMainVO() {
		return biElectionResultsMainVO;
	}

	public void setBiElectionResultsMainVO(
			BiElectionResultsMainVO biElectionResultsMainVO) {
		this.biElectionResultsMainVO = biElectionResultsMainVO;
	}

	public String getChartPath() {
		return chartPath;
	}

	public void setChartPath(String chartPath) {
		this.chartPath = chartPath;
	}

	public IStaticDataService getStaticDataService() {
		return staticDataService;
	}

	public void setStaticDataService(IStaticDataService staticDataService) {
		this.staticDataService = staticDataService;
	}		

	
	public List<MandalAllElectionResultsVO> getMandalAllElectionResultsVO() {
		return mandalAllElectionResultsVO;
	}

	public void setMandalAllElectionResultsVO(
			List<MandalAllElectionResultsVO> mandalAllElectionResultsVO) {
		this.mandalAllElectionResultsVO = mandalAllElectionResultsVO;
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

	public List<BiElectionDistrictVO> getDistrictsAndConsts() {
		return districtsAndConsts;
	}

	public void setDistrictsAndConsts(List<BiElectionDistrictVO> districtsAndConsts) {
		this.districtsAndConsts = districtsAndConsts;
	}

	public void setServletRequest(HttpServletRequest request) {
		this.request = request;
	}

	public void setServletResponse(HttpServletResponse response) {
        this.response = response;
	}
	
	public IBiElectionPageService getBiElectionPageService() {
		return biElectionPageService;
	}

	public void setBiElectionPageService(
			IBiElectionPageService biElectionPageService) {
		this.biElectionPageService = biElectionPageService;
	}	
	
	public List<ConstituencyElectionResultsVO> getBiElectionAssemblyConstPresentYearResults() {
		return biElectionAssemblyConstPresentYearResults;
	}

	public void setBiElectionAssemblyConstPresentYearResults(
			List<ConstituencyElectionResultsVO> biElectionAssemblyConstPresentYearResults) {
		this.biElectionAssemblyConstPresentYearResults = biElectionAssemblyConstPresentYearResults;
	}

	public List<ConstituencyElectionResultsVO> getBiElectionAssemblyConstPreviousYearResults() {
		return biElectionAssemblyConstPreviousYearResults;
	}

	public void setBiElectionAssemblyConstPreviousYearResults(
			List<ConstituencyElectionResultsVO> biElectionAssemblyConstPreviousYearResults) {
		this.biElectionAssemblyConstPreviousYearResults = biElectionAssemblyConstPreviousYearResults;
	}	

	public String getPresentYearResultsChartName() {
		return presentYearResultsChartName;
	}

	public void setPresentYearResultsChartName(String presentYearResultsChartName) {
		this.presentYearResultsChartName = presentYearResultsChartName;
	}

	public String getPreviousYearResultsChartName() {
		return previousYearResultsChartName;
	}

	public void setPreviousYearResultsChartName(String previousYearResultsChartName) {
		this.previousYearResultsChartName = previousYearResultsChartName;
	}
	
	public ServletContext getContext() {
		return context;
	}
	
	public List<CandidateElectionResultVO> getWinningCandidatesList() {
		return winningCandidatesList;
	}

	public void setWinningCandidatesList(
			List<CandidateElectionResultVO> winningCandidatesList) {
		this.winningCandidatesList = winningCandidatesList;
	}	

	public String getElectionYear() {
		return electionYear;
	}

	public void setElectionYear(String electionYear) {
		this.electionYear = electionYear;
	}

	public String getElectionType() {
		return electionType;
	}

	public void setElectionType(String electionType) {
		this.electionType = electionType;
	}	

	public List<SelectOptionVO> getZptcElectionYears() {
		return zptcElectionYears;
	}

	public void setZptcElectionYears(List<SelectOptionVO> zptcElectionYears) {
		this.zptcElectionYears = zptcElectionYears;
	}

	public List<SelectOptionVO> getMptcElectionYears() {
		return mptcElectionYears;
	}

	public void setMptcElectionYears(List<SelectOptionVO> mptcElectionYears) {
		this.mptcElectionYears = mptcElectionYears;
	}

	public List<SelectOptionVO> getElectionTypes() {
		return electionTypes;
	}

	public void setElectionTypes(List<SelectOptionVO> electionTypes) {
		this.electionTypes = electionTypes;
	}

	public Long getZptcElectionId() {
		return zptcElectionId;
	}

	public void setZptcElectionId(Long zptcElectionId) {
		this.zptcElectionId = zptcElectionId;
	}

	public Long getMptcElectionId() {
		return mptcElectionId;
	}

	public void setMptcElectionId(Long mptcElectionId) {
		this.mptcElectionId = mptcElectionId;
	}
	
	

	public String getMptcElectionType() {
		return mptcElectionType;
	}

	public void setMptcElectionType(String mptcElectionType) {
		this.mptcElectionType = mptcElectionType;
	}

	public String getZptcElectionType() {
		return zptcElectionType;
	}

	public void setZptcElectionType(String zptcElectionType) {
		this.zptcElectionType = zptcElectionType;
	}

	public String execute(){
		log.debug(" Inside Action ..");
		mptcElectionType = IConstants.MPTC_ELECTION_TYPE;
		zptcElectionType = IConstants.ZPTC_ELECTION_TYPE;
		electionTypes = staticDataService.getAllElectionTypes();
		for(SelectOptionVO eleTypes : electionTypes){
			if(eleTypes.getName().equalsIgnoreCase(mptcElectionType)){
				mptcElectionId = eleTypes.getId();
			}else if(eleTypes.getName().equalsIgnoreCase(zptcElectionType)){
				zptcElectionId = eleTypes.getId();
			}
		}
		zptcElectionYears = staticDataService.getAllElectionYearsForATeshil(zptcElectionId);
		
		mptcElectionYears = staticDataService.getAllElectionYearsForATeshil(mptcElectionId);
		List<Long> constituencyIdsList = new ArrayList<Long>();
		StringBuilder sb = new StringBuilder();
		districtsAndConsts = biElectionPageService.getBiElectionConstituenciesDistrictWise();
		for(BiElectionDistrictVO biElectionDistrictVO: districtsAndConsts)
		{	
			sb.append(biElectionDistrictVO.getDistrictId());
			for(SelectOptionVO obj:biElectionDistrictVO.getConstituenciesList())
				constituencyIdsList.add(obj.getId());						
		}
		biElectionAssemblyConstPresentYearResults = staticDataService.findAssemblyConstituenciesResultsByConstituencyIds(IConstants.PRESENT_ELECTION_YEAR, constituencyIdsList);
		biElectionAssemblyConstPreviousYearResults = staticDataService.findAssemblyConstituenciesResultsByConstituencyIds(IConstants.PREVIOUS_ELECTION_YEAR, constituencyIdsList);
		if(biElectionAssemblyConstPresentYearResults != null && biElectionAssemblyConstPresentYearResults.size()>0)
			presentYearResultsChartName = createResultsLineChart(biElectionAssemblyConstPresentYearResults,sb, IConstants.PRESENT_ELECTION_YEAR);
		if(biElectionAssemblyConstPreviousYearResults !=null && biElectionAssemblyConstPreviousYearResults.size()>0)
			previousYearResultsChartName = createResultsLineChart(biElectionAssemblyConstPreviousYearResults,sb, IConstants.PREVIOUS_ELECTION_YEAR);
		biElectionAssemblyConstPreviousYearResults = staticDataService.findAssemblyConstituenciesResultsByConstituencyIds(IConstants.PRESENT_ELECTION_YEAR, constituencyIdsList);
		winningCandidatesList = staticDataService.getWinningCandidatesInConstituencies(IConstants.PRESENT_ELECTION_YEAR, constituencyIdsList);
		electionYear = IConstants.PRESENT_ELECTION_YEAR;
		electionType = IConstants.ASSEMBLY_ELECTION_TYPE;
		return Action.SUCCESS;
	}
	
	public String createResultsLineChart(List<ConstituencyElectionResultsVO> asseblyDetails, StringBuilder partialChartName, String year)
	{
		String chartName = null;
		try{
		
 		String lineChartName = "bielections in _" +partialChartName+"_forYear_"+year+".png";
        String chartPath = context.getRealPath("/") + "charts\\" + lineChartName;
        String title = year+" Assembly Election Results in Bi-Election Constituencies";
        ChartColorsAndDataSetVO chartColorsAndDataSetVO = createDataSetForGraph(asseblyDetails);
		ChartProducer.createLineChart(title,"Constituencies","Votes Percentage", (DefaultCategoryDataset)chartColorsAndDataSetVO.getDataSet(),chartPath,320,920, new ArrayList<Color>(chartColorsAndDataSetVO.getColorsSet()));
		chartName = lineChartName;
		}catch(Exception ex){
			ex.printStackTrace();
			log.debug("Exception Raised :" + ex);
		}
		return chartName;
		
	}
	
	private ChartColorsAndDataSetVO createDataSetForGraph(List<ConstituencyElectionResultsVO> asseblyDetails){
		ChartColorsAndDataSetVO chartColorsAndDataSetVO = new ChartColorsAndDataSetVO();
		Set<Color> colorsSet = new LinkedHashSet<Color>();
		final DefaultCategoryDataset dataset = new DefaultCategoryDataset();
		for(ConstituencyElectionResultsVO consti:asseblyDetails)
			for(PartyResultsVO category:consti.getPartyResultsVO())
			{
				if(IConstants.TDP.equalsIgnoreCase(category.getPartyName()))
				{	colorsSet.add(IConstants.TDP_COLOR);
					log.debug("TDP ADDED");
				}
				
	        	else
	        		if(IConstants.INC.equalsIgnoreCase(category.getPartyName()))
	        		{	colorsSet.add(IConstants.INC_COLOR);
	        		log.debug("INC ADDEd");
	        		}
	            	else
	            		if(IConstants.BJP.equalsIgnoreCase(category.getPartyName()))
	            		{	colorsSet.add(IConstants.BJP_COLOR);
	            			log.debug("BJP ADDEd");
	            		}
	                	else
	                		if(IConstants.PRP.equalsIgnoreCase(category.getPartyName()))
	                    		{colorsSet.add(IConstants.PRP_COLOR);
	                    		log.debug("PRP ADDEd");
	                    		}
	                    	else
	                    		if(IConstants.TRS.equalsIgnoreCase(category.getPartyName()))
	                        		{
	                    			colorsSet.add(IConstants.TRS_COLOR);
	                    			log.debug("TRS ADDEd");
	                    			}
	                    		else
		                    		if(IConstants.AIMIM.equalsIgnoreCase(category.getPartyName()))
		                        		{
		                    			colorsSet.add(IConstants.AIMIM_COLOR);
		                    			log.debug("AIMIM ADDEd");
		                    			}
		                    		else
			                    		if(IConstants.CPI.equalsIgnoreCase(category.getPartyName()))
			                        		{
			                    			colorsSet.add(IConstants.CPI_COLOR);
			                    			log.debug("CPI ADDEd");
			                    			}
	                    		else
			                    	{colorsSet.add(null);
			                    	log.debug("Default ADDEd");
			                    	}
				dataset.addValue(new BigDecimal(category.getPercentage()),category.getPartyName(),consti.getConstituencyName());
			}
		chartColorsAndDataSetVO.setDataSet(dataset);
        chartColorsAndDataSetVO.setColorsSet(colorsSet);	
		return chartColorsAndDataSetVO;
	}

	
	public String getMandalsVotingTrendz() throws Exception
	{
		String param=null;			    
		param = getTask();
		
		try {
			jObj=new JSONObject(param);
			System.out.println("jObj = "+jObj);
		} catch (ParseException e) {
			e.printStackTrace();
		}	
		
		Long districtId = new Long(jObj.getString("districtId"));
		Long constiId =  new Long(jObj.getString("constituencyId"));
		String constiName = jObj.getString("constiName");
		
		
		biElectionResultsVO = biElectionPageService.getMandalWiseResultsForAConstituency(constiId);
		
		biElectionResultsMainVO = new BiElectionResultsMainVO();
		biElectionResultsMainVO.setBiElectionResultsMainVO(biElectionResultsVO);
		biElectionResultsMainVO.setMandalWiseResultsChart(getMandalResults(constiId,constiName));
		biElectionResultsMainVO.setAssemblyResultsChartForPresentYear(presentYearResultsChartName);
		biElectionResultsMainVO.setAssemblyResultsChartForPreviousYear(previousYearResultsChartName);
		
		if(constiId != null && constiId != new Long(0))
			biElectionResultsMainVO.setConstituencyVO(getVotersShareInMandalsPieChart(constiId));
		
		return Action.SUCCESS;
	}
	
	  public String getMandalResults(Long constituencyId,String constituencyName){
		 
		  List<ElectionResultPartyVO> list = staticDataService.getAllMandalElectionInformationForAConstituency(constituencyId);

		  String chartTitle = "AllPartiesAllElectionYearsForAllElectiontypesConstituencyLatestMandalDetails";		  
		  String chartName = "ElectionDetails for"+constituencyName+"_"+list.get(0).getElectionYear()+"_"+constituencyId;
		  String domainAxisName = "Mandals";
		  		 
			  chartTitle = "All Election Results for "+constituencyName;
			  chartName = "mandalWiseParliamentElectionsResults"+"_"+constituencyName+"_"+"constituencyId"+".png";
			  chartPath = context.getRealPath("/")+ "charts\\" + chartName;
			  ChartColorsAndDataSetVO chartColorsAndDataSetVO = createDataset(list);
			  ChartProducer.createLineChart(chartTitle, domainAxisName, "Percentages", (DefaultCategoryDataset)chartColorsAndDataSetVO.getDataSet(), chartPath,320,920,new ArrayList<Color>(chartColorsAndDataSetVO.getColorsSet()));
			  
			  mandalWiseResultsChart = chartName;
			  
			 return chartName;
	  }
	  
	  

	private ChartColorsAndDataSetVO createDataset(List<ElectionResultPartyVO> list) {
	       final DefaultCategoryDataset dataset = new DefaultCategoryDataset();
	       ChartColorsAndDataSetVO chartColorsAndDataSetVO = new ChartColorsAndDataSetVO();
		   Set<Color> colorsSet = new LinkedHashSet<Color>();
	       try {
			       for(ElectionResultPartyVO electionResultPartyVO:list){
			    		   for(CandidateElectionResultVO value:electionResultPartyVO.getCandidateElectionResultsVO()){
			    			  
			    					if(IConstants.TDP.equalsIgnoreCase(value.getPartyName()))
			    						{	colorsSet.add(IConstants.TDP_COLOR);
			    							log.debug("TDP ADDED");
			    						}
			    						
			    			        	else
			    			        		if(IConstants.INC.equalsIgnoreCase(value.getPartyName()))
			    			        		{	colorsSet.add(IConstants.INC_COLOR);
			    			        		log.debug("INC ADDEd");
			    			        		}
			    			            	else
			    			            		if(IConstants.BJP.equalsIgnoreCase(value.getPartyName()))
			    			            		{	colorsSet.add(IConstants.BJP_COLOR);
			    			            			log.debug("BJP ADDEd");
			    			            		}
			    			                	else
			    			                		if(IConstants.PRP.equalsIgnoreCase(value.getPartyName()))
			    			                    		{colorsSet.add(IConstants.PRP_COLOR);
			    			                    		log.debug("PRP ADDEd");
			    			                    		}
			    			                    	else
			    			                    		if(IConstants.TRS.equalsIgnoreCase(value.getPartyName()))
			    			                        		{
			    			                    			colorsSet.add(IConstants.TRS_COLOR);
			    			                    			log.debug("TRS ADDEd");
			    			                    			}
			    			                    		else
			    				                    		if(IConstants.AIMIM.equalsIgnoreCase(value.getPartyName()))
			    				                        		{
			    				                    			colorsSet.add(IConstants.AIMIM_COLOR);
			    				                    			log.debug("AIMIM ADDEd");
			    				                    			}
			    				                    		else
			    					                    		if(IConstants.CPI.equalsIgnoreCase(value.getPartyName()))
			    					                        		{
			    					                    			colorsSet.add(IConstants.CPI_COLOR);
			    					                    			log.debug("CPI ADDEd");
			    					                    			}
			    			                    		else
			    					                    	{colorsSet.add(null);
			    					                    	log.debug("Default ADDEd");
			    					                    	}   
			    		   
			    		    dataset.addValue(new BigDecimal(value.getVotesPercentage()), value.getPartyName(), electionResultPartyVO.getElectionType()+" "+electionResultPartyVO.getElectionYear());
			    		   }			    	   
			       }		
			       chartColorsAndDataSetVO.setDataSet(dataset);
		           chartColorsAndDataSetVO.setColorsSet(colorsSet);	
		       } catch (Exception e) {
					e.printStackTrace();
				}
	       return chartColorsAndDataSetVO;
		}

	public String getMandalWiseResultsChart() {
		return mandalWiseResultsChart;
	}

	public void setMandalWiseResultsChart(String mandalWiseResultsChart) {
		this.mandalWiseResultsChart = mandalWiseResultsChart;
	}
	
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
