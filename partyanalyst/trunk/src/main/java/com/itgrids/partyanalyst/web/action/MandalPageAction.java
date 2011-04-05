package com.itgrids.partyanalyst.web.action;

import java.math.BigDecimal;
import java.util.List;

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
import com.itgrids.partyanalyst.helper.ChartProducer;
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
	private String chartProducerURL="/var/www/vsites/partyanalyst.com/httpdocs/charts/";
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
		
		String param = getTask();		
		try {
			jsonObj = new JSONObject(param);
		} catch (Exception e) {			
			e.printStackTrace();
		}		
		
		String mandalId = jsonObj.getString("mandal");
		String partyId = jsonObj.getString("party");
		String alliance = "true";//jsonObj.getString("alliance");
		
		String chartName = "mandalWisePartyPerformance_"+mandalId+"_"+partyId+".png";
       // String chartPath = context.getRealPath("/")+ "charts\\" + chartName;
		 String chartPath = chartProducerURL+ chartName;
        mandalDataWithChartVO = new MandalDataWithChartVO();
		List<MandalAllElectionDetailsVO> mandalAllElectionDetailsVO = partyBoothWiseResultsService.getMandalAllElectionDetails(new Long(mandalId), new Long(partyId),new Boolean(alliance).booleanValue());
		mandalDataWithChartVO.setMandalAllElectionDetailsVO(mandalAllElectionDetailsVO);
		mandalDataWithChartVO.setChart(chartName);
	 	
        ChartProducer.createBarChart("Party Results - Year Vs Votes Percentage", "Years", "Votes Percentage", createDataset(mandalAllElectionDetailsVO), chartPath);
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
