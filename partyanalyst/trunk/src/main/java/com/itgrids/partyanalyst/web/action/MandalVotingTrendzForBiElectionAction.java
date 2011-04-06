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
import org.apache.struts2.util.ServletContextAware;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.general.DefaultPieDataset;
import org.json.JSONObject;

import com.itgrids.partyanalyst.dto.BiElectionDistrictVO;
import com.itgrids.partyanalyst.dto.BiElectionResultsMainVO;
import com.itgrids.partyanalyst.dto.BiElectionResultsVO;
import com.itgrids.partyanalyst.dto.CandidateElectionResultVO;
import com.itgrids.partyanalyst.dto.ChartColorsAndDataSetVO;
import com.itgrids.partyanalyst.dto.ConstituencyInfoVO;
import com.itgrids.partyanalyst.dto.ConstituencyVO;
import com.itgrids.partyanalyst.dto.ElectionResultPartyVO;
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

public class MandalVotingTrendzForBiElectionAction extends ActionSupport
		implements ServletRequestAware, ServletResponseAware, ServletContextAware{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static final Logger log = Logger.getLogger(BiElectionAction.class);
	HttpServletRequest request;
	HttpServletResponse response;
	HttpSession session;
	
	private IBiElectionPageService biElectionPageService;
	private IConstituencyPageService constituencyPageService;
	private IStaticDataService staticDataService;
	
	private ServletContext context;
	private String districtId;
	private String constiId;
	private String constiName;
	private String task;
	JSONObject jObj;
	List<BiElectionResultsVO> biElectionResultsVO;	
	private ConstituencyVO constituencyVO;	
	BiElectionResultsMainVO biElectionResultsMainVO;
	private String presentYearResultsChartName;
	private String previousYearResultsChartName;
	
	private String chartPath;
	private String mandalWiseResultsChart;
	private ConstituencyInfoVO constituencyDetails;
	private List<BiElectionDistrictVO> districtsAndConsts;
	
	/*-- new --*/
	private List<SelectOptionVO> zptcElectionYears;
	private List<SelectOptionVO> mptcElectionYears;  
	private List<SelectOptionVO> electionTypes;
	private Long zptcElectionId; 
	private Long mptcElectionId;
	private String mptcElectionType,zptcElectionType;
	private String chartProducerURL="/var/www/vsites/partyanalyst.com/httpdocs/charts/";
	
	
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
	
	/*-- new(End) --*/
	
	
	
	public List<BiElectionDistrictVO> getDistrictsAndConsts() {
		return districtsAndConsts;
	}


	public void setDistrictsAndConsts(List<BiElectionDistrictVO> districtsAndConsts) {
		this.districtsAndConsts = districtsAndConsts;
	}


	public void setServletContext(ServletContext context) {
		this.context = context;
		
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


	public String getMandalWiseResultsChart() {
		return mandalWiseResultsChart;
	}


	public void setMandalWiseResultsChart(String mandalWiseResultsChart) {
		this.mandalWiseResultsChart = mandalWiseResultsChart;
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


	public List<BiElectionResultsVO> getBiElectionResultsVO() {
		return biElectionResultsVO;
	}


	public void setBiElectionResultsVO(List<BiElectionResultsVO> biElectionResultsVO) {
		this.biElectionResultsVO = biElectionResultsVO;
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


	public BiElectionResultsMainVO getBiElectionResultsMainVO() {
		return biElectionResultsMainVO;
	}


	public void setBiElectionResultsMainVO(
			BiElectionResultsMainVO biElectionResultsMainVO) {
		this.biElectionResultsMainVO = biElectionResultsMainVO;
	}


	public ConstituencyVO getConstituencyVO() {
		return constituencyVO;
	}


	public void setConstituencyVO(ConstituencyVO constituencyVO) {
		this.constituencyVO = constituencyVO;
	}


	public IBiElectionPageService getBiElectionPageService() {
		return biElectionPageService;
	}


	public void setBiElectionPageService(
			IBiElectionPageService biElectionPageService) {
		this.biElectionPageService = biElectionPageService;
	}


	public String getTask() {
		return task;
	}


	public void setTask(String task) {
		this.task = task;
	}


	public String getDistrictId() {
		return districtId;
	}


	public void setDistrictId(String districtId) {
		this.districtId = districtId;
	}


	public String getConstiId() {
		return constiId;
	}


	public void setConstiId(String constiId) {
		this.constiId = constiId;
	}


	public String getConstiName() {
		return constiName;
	}


	public void setConstiName(String constiName) {
		this.constiName = constiName;
	}


	public void setServletRequest(HttpServletRequest request) {
		this.request = request;
	}


	public void setServletResponse(HttpServletResponse response) {
		this.response = response;
	}
	
	public String execute() throws Exception
	{
		List<Long> constituencyIdsList = new ArrayList<Long>();
		StringBuilder sb = new StringBuilder();
		districtsAndConsts = biElectionPageService.getBiElectionConstituenciesDistrictWise();
		for(BiElectionDistrictVO biElectionDistrictVO: districtsAndConsts)
		{	
			sb.append(biElectionDistrictVO.getDistrictId());
			for(SelectOptionVO obj:biElectionDistrictVO.getConstituenciesList())
				constituencyIdsList.add(obj.getId());						
		}
		
		/**/
		
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

		 	
		return Action.SUCCESS;
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
		biElectionResultsMainVO.setElectionResultsChart(getElectionResultsPieChart(constiId,constiName));
		if(constiId != null && constiId != new Long(0))
			biElectionResultsMainVO.setConstituencyVO(getVotersShareInMandalsPieChart(constiId));
		
		return Action.SUCCESS;
	}
	
	public List<String> getElectionResultsPieChart(Long constiId,String constiName)
	{
		List<String> electionResultsChart = new ArrayList<String>();
		List<String> allPartiesElectionResultsChart = new ArrayList<String>();
		List<ElectionResultPartyVO> chartList = staticDataService.getAllMandalElectionInformationForAConstituency(constiId,0);
		
		if(chartList.size() == 0)
			return null;
		
		for(int i=0; i<chartList.size(); i++)
		{			
			String electionResultPieChartName = createPieChartForElectionTypeNElectionYear(constiId,chartList.get(i),"allparties");
			if(electionResultPieChartName.length() != 0)
				allPartiesElectionResultsChart.add(electionResultPieChartName);
		}
		biElectionResultsMainVO.setAllPartiesElectionResultsChart(allPartiesElectionResultsChart);
		
		for(int i=0; i<chartList.size(); i++)
		{			
			String electionResultPieChartName = createPieChartForElectionTypeNElectionYear(constiId,chartList.get(i),"selectedParties");
			if(electionResultPieChartName.length() != 0)
				electionResultsChart.add(electionResultPieChartName);
		}	
		
		return electionResultsChart;	
		
		
	}
	
	public String createPieChartForElectionTypeNElectionYear(Long constiId,ElectionResultPartyVO result,String chartType)
	{	
		String cPath = request.getContextPath();
		String chartName = "Election_Result_"+result.getElectionType()+"_"+result.getElectionYear()+"_"+constiId+"_piechart"+".png";
		String allPartychartName = "All_Parties_Election_Result_"+result.getElectionType()+"_"+result.getElectionYear()+"_piechart"+".png";
		String localChart = null;
		String chartPath="";
		String allPartychartPath ="";
		if(cPath.contains("PartyAnalyst"))
			 chartPath = context.getRealPath("/") + "charts\\" + chartName;
		else
			chartPath = chartProducerURL + chartName;
		
		if(cPath.contains("PartyAnalyst"))
		    allPartychartPath = context.getRealPath("/") + "charts\\" + allPartychartName;
		else
		   allPartychartPath = chartProducerURL+ allPartychartName;
		Double otherPartyVotesPercent = 0D;
		
		String chartTitle = ""+result.getElectionType()+" - "+result.getElectionYear();
		final DefaultPieDataset dataset = new DefaultPieDataset();
		Color[] colors = null;
		if(chartType.equalsIgnoreCase("allparties"))
			colors = new Color[result.getCandidateElectionResultsVO().size()];
		if(chartType.equalsIgnoreCase("selectedParties"))
			colors = new Color[7];
		log.debug(" results size ==== "+result.getCandidateElectionResultsVO().size());		
		int j=0;
		for(int i=0; i<result.getCandidateElectionResultsVO().size(); i++ )
		{		
			String partyName = result.getCandidateElectionResultsVO().get(i).getPartyName(); 
			Double votesPercent = Double.valueOf(result.getCandidateElectionResultsVO().get(i).getVotesPercentage());
			log.debug(" party Name ==== "+partyName+", votes Percent = "+votesPercent);	
						
			if(chartType.equalsIgnoreCase("allparties"))
			{	
				if(partyName.equals(IConstants.INC))
				{
					colors[i]=IConstants.INC_COLOR;
					log.debug(" party Name ==== "+partyName+", votes Percent = "+i);
				}				
				else
				if(partyName.equals(IConstants.IND))
				{
					colors[i]=IConstants.IND_COLOR;
					log.debug(" party Name ==== "+partyName+", votes Percent = "+i);
				}				
				else
				if(partyName.equals(IConstants.PRP))
				{
					colors[i]=IConstants.PRP_COLOR;
					log.debug(" party Name ==== "+partyName+", votes Percent = "+i);
				}			
				else
				if(partyName.equals(IConstants.TDP))
				{
					colors[i]=IConstants.TDP_COLOR;
					log.debug(" party Name ==== "+partyName+", votes Percent = "+i);
				}	
				else
				if(partyName.equals(IConstants.TRS))
				{
					colors[i]=IConstants.TRS_COLOR;
					log.debug(" party Name ==== "+partyName+", votes Percent = "+i);
				}
				else
				if(partyName.equals(IConstants.CPI))
				{
					colors[i]=IConstants.CPI_COLOR;
					log.debug(" party Name ==== "+partyName+", votes Percent = "+i);
				}						
				else
				if(partyName.equals(IConstants.CPM))
				{
					colors[i]=IConstants.CPM_COLOR;
					log.debug(" party Name ==== "+partyName+", votes Percent = "+i);
				}					
				else
				if(partyName.equals(IConstants.BJP))
				{
					colors[i]=IConstants.BJP_COLOR;
					log.debug(" party Name ==== "+partyName+", votes Percent = "+i);
				}					
				else
				{
					colors[i] = null;
					log.debug(" party Name ==== "+partyName+", votes Percent = "+i);
				}
				
				dataset.setValue(partyName+" ["+votesPercent.toString()+"%]",votesPercent);	
			}else if(chartType.equalsIgnoreCase("selectedParties"))
			{
				if(partyName.equalsIgnoreCase(IConstants.INC) || partyName.equalsIgnoreCase(IConstants.PRP) || partyName.equalsIgnoreCase(IConstants.TDP) || partyName.equalsIgnoreCase(IConstants.TRS) || partyName.equalsIgnoreCase(IConstants.CPI) || partyName.equalsIgnoreCase(IConstants.CPM) || partyName.equalsIgnoreCase(IConstants.BJP))
				{				
					if(partyName.equals(IConstants.INC))
					{
						colors[j++]=IConstants.INC_COLOR;
						log.debug(" party Name ==== "+partyName+", votes Percent = "+i);
					}				
					else
					if(partyName.equals(IConstants.PRP))
					{
						colors[j++]=IConstants.PRP_COLOR;
						log.debug(" party Name ==== "+partyName+", votes Percent = "+i);
					}			
					else
					if(partyName.equals(IConstants.TDP))
					{
						colors[j++]=IConstants.TDP_COLOR;
						log.debug(" party Name ==== "+partyName+", votes Percent = "+i);
					}	
					else
					if(partyName.equals(IConstants.TRS))
					{
						colors[j++]=IConstants.TRS_COLOR;
						log.debug(" party Name ==== "+partyName+", votes Percent = "+i);
					}
					else
					if(partyName.equals(IConstants.CPI))
					{
						colors[j++]=IConstants.CPI_COLOR;
						log.debug(" party Name ==== "+partyName+", votes Percent = "+i);
					}						
					else
					if(partyName.equals(IConstants.CPM))
					{
						colors[j++]=IConstants.CPM_COLOR;
						log.debug(" party Name ==== "+partyName+", votes Percent = "+i);
					}					
					else
					if(partyName.equals(IConstants.BJP))
					{
						colors[j++]=IConstants.BJP_COLOR;
						log.debug(" party Name ==== "+partyName+", votes Percent = "+i);
					}					
					
					dataset.setValue(partyName+" ["+votesPercent.toString()+"%]",votesPercent);	
				}	
				else
				{
					otherPartyVotesPercent+=votesPercent;
				}
				
			}
		}
		
		if(chartType.equalsIgnoreCase("allparties")){
			ChartProducer.createProblemsPieChart(chartTitle, dataset, allPartychartPath , colors,true,300,280);
			localChart = allPartychartName;
		}
			
		else if(chartType.equalsIgnoreCase("selectedParties")){			
			BigDecimal	otherPartyVotes = new BigDecimal(otherPartyVotesPercent).setScale(2, BigDecimal.ROUND_HALF_UP);			
			dataset.setValue("Others"+" ["+otherPartyVotes.toString()+"%]",otherPartyVotes);
			colors[j] = IConstants.DEFAULT_COLOR;
			ChartProducer.createProblemsPieChart(chartTitle, dataset, chartPath , colors,true,300,280);
			localChart = chartName;
		}
		
		return localChart;
			
	}
	
	
	 public String getMandalResults(Long constituencyId,String constituencyName){
		 
		  List<ElectionResultPartyVO> list = staticDataService.getAllMandalElectionInformationForAConstituency(constituencyId,0);
		  String cPath = request.getContextPath();
		  String chartTitle = "AllPartiesAllElectionYearsForAllElectiontypesConstituencyLatestMandalDetails";		  
		  String chartName = "ElectionDetails for"+constituencyName+"_"+list.get(0).getElectionYear()+"_"+constituencyId;
		  String domainAxisName = "Mandals";
		  		 
			  chartTitle = "All Election Results for "+constituencyName;
			  chartName = "mandalWiseParliamentElectionsResults"+"_"+constituencyName+"_"+"constituencyId"+".png";
			 
			  if(cPath.contains("PartyAnalyst"))
			      chartPath = context.getRealPath("/")+ "charts\\" + chartName;
			  else
			       chartPath = chartProducerURL + chartName;
			  ChartColorsAndDataSetVO chartColorsAndDataSetVO = createDataset(list);
			  ChartProducer.createLineChartWithThickness(chartTitle, domainAxisName, "Percentages", (DefaultCategoryDataset)chartColorsAndDataSetVO.getDataSet(), chartPath,320,920,new ArrayList<Color>(chartColorsAndDataSetVO.getColorsSet()),false);
			  
			  mandalWiseResultsChart = chartName;
			  
			 return chartName;
	  }
	 
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
				       pieChartPath = chartProducerURL + pieChart;
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
