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
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;
import org.json.JSONObject;

import com.itgrids.partyanalyst.dto.BiElectionDistrictVO;
import com.itgrids.partyanalyst.dto.ChartColorsAndDataSetVO;
import com.itgrids.partyanalyst.dto.ConstituencyElectionResultsVO;
import com.itgrids.partyanalyst.dto.MandalAllElectionResultsVO;
import com.itgrids.partyanalyst.dto.PartyResultsVO;
import com.itgrids.partyanalyst.dto.SelectOptionVO;
import com.itgrids.partyanalyst.helper.ChartProducer;
import com.itgrids.partyanalyst.service.IBiElectionPageService;
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
	private List<BiElectionDistrictVO> districtsAndConsts;
	private IStaticDataService staticDataService;
	private List<ConstituencyElectionResultsVO> biElectionAssemblyConstPresentYearResults;
	private List<ConstituencyElectionResultsVO> biElectionAssemblyConstPreviousYearResults;
	private String presentYearResultsChartName;
	private String previousYearResultsChartName;
	private ServletContext context;
	HttpServletRequest request;
	HttpServletResponse response;
	HttpSession session;
	private String task;
	org.json.JSONObject jObj;
	List<MandalAllElectionResultsVO> mandalAllElectionResultsVO;
	
	
	public List<MandalAllElectionResultsVO> getMandalAllElectionResultsVO() {
		return mandalAllElectionResultsVO;
	}

	public void setMandalAllElectionResultsVO(
			List<MandalAllElectionResultsVO> mandalAllElectionResultsVO) {
		this.mandalAllElectionResultsVO = mandalAllElectionResultsVO;
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
	

	public IStaticDataService getStaticDataService() {
		return staticDataService;
	}

	public void setStaticDataService(IStaticDataService staticDataService) {
		this.staticDataService = staticDataService;
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
	
	public void setServletContext(ServletContext context) {
		this.context = context;
	}	
	
	public ServletContext getContext() {
		return context;
	}

	public String execute(){
		log.debug(" Inside Action ..");
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
		return Action.SUCCESS;
	}
	
	public String createResultsLineChart(List<ConstituencyElectionResultsVO> asseblyDetails, StringBuilder partialChartName, String year)
	{
		String chartName = null;
		try{
		
 		String lineChartName = "bielections in _" +partialChartName+"_forYear_"+year+".png";
        String chartPath = context.getRealPath("/") + "charts\\" + lineChartName;
        String title = year+"Assembly Election Results in Bi-Election Constituencies";
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
		
		mandalAllElectionResultsVO = biElectionPageService.getAllPartysElectionResultsInAConstituencyMandalWise(constiId);
		
		return Action.SUCCESS;
	}


}
