package com.itgrids.partyanalyst.web.action;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;
import org.json.JSONObject;
import org.springframework.web.context.ServletContextAware;

import com.itgrids.partyanalyst.dto.CastWiseElectionVotersVO;
import com.itgrids.partyanalyst.dto.GenderAgeWiseVotersVO;
import com.itgrids.partyanalyst.dto.InfluencingPeopleVO;
import com.itgrids.partyanalyst.dto.LocationWiseBoothDetailsVO;
import com.itgrids.partyanalyst.dto.MandalAllElectionDetailsVO;
import com.itgrids.partyanalyst.dto.MandalDataWithChartVO;
import com.itgrids.partyanalyst.dto.MandalInfoVO;
import com.itgrids.partyanalyst.dto.PartyElectionVotersHeaderDataVO;
import com.itgrids.partyanalyst.dto.ResultWithExceptionVO;
import com.itgrids.partyanalyst.dto.VillageDetailsVO;
import com.itgrids.partyanalyst.service.IConstituencyPageService;
import com.itgrids.partyanalyst.service.IDelimitationConstituencyMandalService;
import com.itgrids.partyanalyst.service.IPartyBoothWiseResultsService;
import com.itgrids.partyanalyst.utils.IConstants;
import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionSupport;

public class MandalPageAction extends ActionSupport implements ServletRequestAware, ServletContextAware{
	
	private HttpServletRequest request;
	private IDelimitationConstituencyMandalService delimitationConstituencyMandalService;
	private MandalInfoVO mandalInfoVO;
	private VillageDetailsVO villageDetailsVO;
	private JSONObject jsonObj = null;
	private String task = null;

	private static final Logger log = Logger.getLogger(MandalPageAction.class);
	private IPartyBoothWiseResultsService partyBoothWiseResultsService;
	private MandalDataWithChartVO mandalDataWithChartVO;
	private HttpSession session;
	private ServletContext context;
	private PartyElectionVotersHeaderDataVO partyElectionVotersHeaderDataVO;
	private IConstituencyPageService constituencyPageService;
	private List<InfluencingPeopleVO> influencingPeopleInMandal;
	private List<LocationWiseBoothDetailsVO> townshipWiseBoothDataInMandal;
	private CastWiseElectionVotersVO castWiseElectionVoters;
	private GenderAgeWiseVotersVO genderAgeWiseVoters;

	public List<InfluencingPeopleVO> getInfluencingPeopleInMandal() {
		return influencingPeopleInMandal;
	}

	public void setInfluencingPeopleInMandal(
			List<InfluencingPeopleVO> influencingPeopleInMandal) {
		this.influencingPeopleInMandal = influencingPeopleInMandal;
	}

	public List<LocationWiseBoothDetailsVO> getTownshipWiseBoothDataInMandal() {
		return townshipWiseBoothDataInMandal;
	}

	public void setTownshipWiseBoothDataInMandal(
			List<LocationWiseBoothDetailsVO> townshipWiseBoothDataInMandal) {
		this.townshipWiseBoothDataInMandal = townshipWiseBoothDataInMandal;
	}

	public IConstituencyPageService getConstituencyPageService() {
		return constituencyPageService;
	}

	public void setConstituencyPageService(
			IConstituencyPageService constituencyPageService) {
		this.constituencyPageService = constituencyPageService;
	}

	public HttpSession getSession() {
		return session;
	}

	public void setSession(HttpSession session) {
		this.session = session;
	}

	public void setServletRequest(HttpServletRequest request) {
		this.request = request;	
	}

	public void setDelimitationConstituencyMandalService(
			IDelimitationConstituencyMandalService delimitationConstituencyMandalService) {
		this.delimitationConstituencyMandalService = delimitationConstituencyMandalService;
	}

	public void setMandalInfoVO(MandalInfoVO mandalInfoVO) {
		this.mandalInfoVO = mandalInfoVO;
	}
	
	public MandalInfoVO getMandalInfoVO(){
		return mandalInfoVO;
	}
	
	public VillageDetailsVO getVillageDetailsVO() {
		return villageDetailsVO;
	}

	public void setVillageDetailsVO(VillageDetailsVO villageDetailsVO) {
		this.villageDetailsVO = villageDetailsVO;
	}

	public String getTask() {
		return task;
	}

	public void setTask(String task) {
		this.task = task;
	}

	public MandalDataWithChartVO getMandalDataWithChartVO() {
		return mandalDataWithChartVO;
	}

	public void setMandalDataWithChartVO(MandalDataWithChartVO mandalDataWithChartVO) {
		this.mandalDataWithChartVO = mandalDataWithChartVO;
	}

	public IPartyBoothWiseResultsService getPartyBoothWiseResultsService() {
		return partyBoothWiseResultsService;
	}

	public void setPartyBoothWiseResultsService(
			IPartyBoothWiseResultsService partyBoothWiseResultsService) {
		this.partyBoothWiseResultsService = partyBoothWiseResultsService;
	}

	public void setServletContext(ServletContext context) {
		this.context = context;
	}
	
	
	public PartyElectionVotersHeaderDataVO getPartyElectionVotersHeaderDataVO() {
		return partyElectionVotersHeaderDataVO;
	}

	public void setPartyElectionVotersHeaderDataVO(
			PartyElectionVotersHeaderDataVO partyElectionVotersHeaderDataVO) {
		this.partyElectionVotersHeaderDataVO = partyElectionVotersHeaderDataVO;
	}

	public void setCastWiseElectionVoters(
			CastWiseElectionVotersVO castWiseElectionVoters) {
		this.castWiseElectionVoters = castWiseElectionVoters;
	}
	public CastWiseElectionVotersVO getCastWiseElectionVoters() {
		return castWiseElectionVoters;
	}

	public GenderAgeWiseVotersVO getGenderAgeWiseVoters() {
		return genderAgeWiseVoters;
	}

	public void setGenderAgeWiseVoters(GenderAgeWiseVotersVO genderAgeWiseVoters) {
		this.genderAgeWiseVoters = genderAgeWiseVoters;
	}

	public String execute() throws Exception {
		
		String mandalID = request.getParameter("MANDAL_ID");
		String mandalName = request.getParameter("MANDAL_NAME");
		List<MandalInfoVO> mandalInfo = delimitationConstituencyMandalService.getCensusInfoForMandals(mandalID);
		for(MandalInfoVO mandalInfoVO : mandalInfo){
			mandalInfoVO.setMandalName(mandalName);
			Throwable ex = mandalInfoVO.getExceptionEncountered();
			if(ex!=null){
				log.error("exception raised while retrieving mandal details ", ex);
				return ERROR;
			}
			setMandalInfoVO(mandalInfoVO);
			break;
		}
		villageDetailsVO = delimitationConstituencyMandalService.getVillagesFormMandal(new Long(mandalID));
		villageDetailsVO.setMandalName(mandalName);
		Throwable ex = villageDetailsVO.getExceptionEncountered();
		if(ex!=null){
			log.error("exception raised while retrieving mandal details ", ex);
		}
		partyElectionVotersHeaderDataVO = delimitationConstituencyMandalService.getPartyElectionVotersForMandal(new Long(mandalID), IConstants.MANDAL);
		ex = partyElectionVotersHeaderDataVO.getExceptionEncountered();
		if(ex!=null){
			log.error("exception raised while retrieving mandal voters party wise ", ex);
		}

		castWiseElectionVoters = delimitationConstituencyMandalService.findCastWiseVotersForMandal(new Long(mandalID));
		ex = castWiseElectionVoters.getExceptionEncountered();
		if(ex!=null){
			log.error("exception raised while retrieving mandal voters cast wise ", ex);
		}
		genderAgeWiseVoters = delimitationConstituencyMandalService.findGenderAgeWiseVotersForMandal(new Long(mandalID));
		
		if(log.isDebugEnabled()){
			log.debug("size============================================"+mandalInfo.size());
			log.debug("size============================================"+(villageDetailsVO.getVillageCensusList()).size());
			log.debug("size============================================"+castWiseElectionVoters.getCasteVoters().size());
			log.debug("size============================================"+genderAgeWiseVoters.getVoterAgeRangeVOList().size());
			log.debug("end of MandalPageAction.execute()");
		}
		
		ResultWithExceptionVO influencingPeopleInMandalVO = constituencyPageService.getAllMandalLevelLeaders(new Long(mandalID));
		if(influencingPeopleInMandalVO.getExceptionEncountered() == null){
			influencingPeopleInMandal = (List<InfluencingPeopleVO>)influencingPeopleInMandalVO.getFinalResult();
		}
		/*//ResultWithExceptionVO boothDataOfRevenueVillagesInMandal = constituencyPageService.getTownshipWiseBoothDetailsForTehsil(new Long(mandalID), 2l);
		if(boothDataOfRevenueVillagesInMandal.getResultStatus().getExceptionEncountered() == null){
			townshipWiseBoothDataInMandal = (List<LocationWiseBoothDetailsVO>)boothDataOfRevenueVillagesInMandal.getFinalResult();
		}*/
		return SUCCESS;
	}
	
	@SuppressWarnings("unchecked")
	public String getMandalPartyResult() throws Exception{		
		
		String cPath = request.getContextPath();
		String param = getTask();
		String chartPath="";
		try {
			jsonObj = new JSONObject(param);
		} catch (Exception e) {			
			e.printStackTrace();
		}		
		
		String mandalId = jsonObj.getString("mandal");
		String partyId = jsonObj.getString("party");
		//String alliance = "true";//jsonObj.getString("alliance");
		String alliance = jsonObj.getString("allianceVal");
		
		/*String chartName = "mandalWisePartyPerformance_"+mandalId+"_"+partyId+".png";
         if(cPath.contains("PartyAnalyst"))
        	 chartPath = context.getRealPath("/")+ "charts\\" + chartName;
         else
		  chartPath = IWebConstants.CHART_URL_IN_SERVER + chartName;
        */
     	List<String> xaxisList =new ArrayList<String>(0);
     	List<String> electionYears =new ArrayList<String>(0);
     	Map<String,Map<String,Double>> resultMap = new HashMap<String, Map<String,Double>>();
        mandalDataWithChartVO = new MandalDataWithChartVO();
		List<MandalAllElectionDetailsVO> mandalAllElectionDetailsVO = partyBoothWiseResultsService.getMandalAllElectionDetails(new Long(mandalId), new Long(partyId),new Boolean(alliance).booleanValue());
		if(mandalAllElectionDetailsVO != null && mandalAllElectionDetailsVO.size() > 0)
		{
		for(MandalAllElectionDetailsVO list : mandalAllElectionDetailsVO)
			{
			if(!electionYears.contains(list.getElectionYear().toString()))
			electionYears.add(list.getElectionYear().toString());
			if(!xaxisList.contains(list.getElectionType().toString()))
			xaxisList.add(list.getElectionType().toString());
			
			Map<String,Double> yearVotesMap = resultMap.get(list.getElectionType().toString());
			if(yearVotesMap == null)
			{
				yearVotesMap = new HashMap<String, Double>();
				resultMap.put(list.getElectionType().toString(), yearVotesMap);
			}
			
			Double percentage = yearVotesMap.get(list.getElectionYear());
			if(percentage == null)
			{
				Double val = Double.parseDouble(list.getPartyVotesPercentage().toString());
				
				if(!list.getElectionType().equalsIgnoreCase(IConstants.MPTC)){
				  yearVotesMap.put(list.getElectionYear(), val);
				}else
				{
					yearVotesMap.put(list.getElectionYear(), Double.parseDouble(list.getVotesGainedPercent().toString()));
					
				}
				
			}
			
	}
		}
		List<MandalAllElectionDetailsVO> result = new ArrayList<MandalAllElectionDetailsVO>();
		MandalAllElectionDetailsVO MandalAllElectionDetailsVO1 = null;
	if(xaxisList != null && xaxisList.size() > 0)
	{
	for(String electionType : xaxisList)
	{
		for(String electionType1 : resultMap.keySet())
		{
			if(electionType.toString().equalsIgnoreCase(electionType1.toString()))
			{
			MandalAllElectionDetailsVO1 = new MandalAllElectionDetailsVO();
			MandalAllElectionDetailsVO1.setElectionType(electionType);	
			Map<String,Double> yearVotesMap = resultMap.get(electionType);
			List<MandalAllElectionDetailsVO> subList = new ArrayList<MandalAllElectionDetailsVO>();
			MandalAllElectionDetailsVO MandalAllElectionDetailsVO2 = null;
			boolean flag=false;
			for(String electionYear : electionYears)
			{
			MandalAllElectionDetailsVO2 = new MandalAllElectionDetailsVO();
			for(String eleYear : yearVotesMap.keySet())
			{
				if(electionYear.toString().equalsIgnoreCase(eleYear.toString()))
				{
					Double percentage1 = yearVotesMap.get(eleYear);
					yearVotesMap.put(eleYear, percentage1);
					MandalAllElectionDetailsVO2.setPartyVotesPercentage(String.valueOf(percentage1));
					MandalAllElectionDetailsVO2.setElectionYear(eleYear);
					flag=true;
				}
				
			}
			if(flag==false)
			{
				Double percentage = 0.0;
				yearVotesMap.put(electionYear,percentage);
				MandalAllElectionDetailsVO2.setPartyVotesPercentage(String.valueOf(percentage));
				MandalAllElectionDetailsVO2.setElectionYear(electionYear);
			}
				else
				flag=false;
			
			subList.add(MandalAllElectionDetailsVO2);
		}
			MandalAllElectionDetailsVO1.setMptcMandalAllElectionDetailsVO(subList);
			result.add(MandalAllElectionDetailsVO1);
	}
	 }
		
 }
}
	Map<String,List<String>> mapData=new HashMap<String, List<String>>();
	if(result != null && result.size() > 0)
	{
		for(MandalAllElectionDetailsVO vo : result)
		{
			for(MandalAllElectionDetailsVO subvo : vo.getMptcMandalAllElectionDetailsVO())
			{
				List<String> valuesList = mapData.get(subvo.getElectionYear().toString());
				if(valuesList == null)
				{
				valuesList = new ArrayList<String>();
				
				valuesList.add(subvo.getPartyVotesPercentage().toString());
				mapData.put(subvo.getElectionYear().toString(),valuesList);
				}
				else
					valuesList.add(subvo.getPartyVotesPercentage().toString());	
			}
		}
	}
		List<MandalAllElectionDetailsVO> result1 = new ArrayList<MandalAllElectionDetailsVO>();
		for(String key :mapData.keySet())
		{
			MandalAllElectionDetailsVO graphData = new MandalAllElectionDetailsVO();
			graphData.setElectionYear(key);
			List<String> valuesList = mapData.get(key.toString());
			
			graphData.setElectionTypes(valuesList);
			result1.add(graphData);
		}
		if(electionYears != null && electionYears.size() > 0)
		result.get(0).setElectionYears(electionYears);
		if(xaxisList != null && xaxisList.size() > 0)
		result.get(0).setElectionTypes(xaxisList);
		if(result1 != null && result1.size() > 0)
		result.get(0).setZptcMandalAllElectionDetailsVO(result1);
		mandalDataWithChartVO.setMandalAllElectionDetailsVO1(result);
		mandalDataWithChartVO.setMandalAllElectionDetailsVO(mandalAllElectionDetailsVO);
		//mandalDataWithChartVO.setChart(chartName);
	// jfree charts are not useing
        //ChartProducer.createBarChart("Party Results - Year Vs Votes Percentage", "Years", "Votes Percentage", createDataset(mandalAllElectionDetailsVO), chartPath);
	   if(mandalAllElectionDetailsVO!=null)				
			return Action.SUCCESS;
		else
			return Action.ERROR;
		
	}

	private CategoryDataset createDataset(List<MandalAllElectionDetailsVO> mandalAllElectionDetailsVO) {
        final String series1 =  IConstants.ASSEMBLY_ELECTION_TYPE;
        final String series2 = IConstants.PARLIAMENT_ELECTION_TYPE;
        final String series3 = IConstants.MPTC_ELECTION_TYPE;
        final String series4 = IConstants.ZPTC_ELECTION_TYPE;
        final DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        for(MandalAllElectionDetailsVO result: mandalAllElectionDetailsVO){
        	if(result.getElectionType().equals(series1))
        		dataset.addValue(new BigDecimal(result.getPartyVotesPercentage()), result.getElectionYear(), series1);
        	else if(result.getElectionType().equals(series2))
        		dataset.addValue(new BigDecimal(result.getPartyVotesPercentage()), result.getElectionYear(), series2);
        	else if(result.getElectionType().equals(series3))
        		dataset.addValue(new BigDecimal(result.getPartyVotesPercentage()), result.getElectionYear(), series3);
        	else if(result.getElectionType().equals(series4))
        		dataset.addValue(new BigDecimal(result.getPartyVotesPercentage()), result.getElectionYear(), series4);
        }
        return dataset;
    }
}
