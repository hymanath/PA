package com.itgrids.partyanalyst.web.action;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.util.ServletContextAware;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;
import org.json.JSONObject;

import com.itgrids.partyanalyst.dto.ConstituencyRevenueVillagesVO;
import com.itgrids.partyanalyst.dto.ElectionResultVO;
import com.itgrids.partyanalyst.dto.ElectionWiseMandalPartyResultListVO;
import com.itgrids.partyanalyst.dto.ElectionWiseMandalPartyResultVO;
import com.itgrids.partyanalyst.dto.MandalAndRevenueVillagesInfoVO;
import com.itgrids.partyanalyst.dto.MandalInfoVO;
import com.itgrids.partyanalyst.dto.NavigationVO;
import com.itgrids.partyanalyst.dto.PartyResultVO;
import com.itgrids.partyanalyst.dto.PartyVotesEarnedVO;
import com.itgrids.partyanalyst.dto.RegistrationVO;
import com.itgrids.partyanalyst.dto.SelectOptionVO;
import com.itgrids.partyanalyst.dto.VillageDetailsVO;
import com.itgrids.partyanalyst.helper.ChartProducer;
import com.itgrids.partyanalyst.helper.ChartUtils;
import com.itgrids.partyanalyst.helper.EntitlementsHelper;
import com.itgrids.partyanalyst.service.IConstituencyPageService;
import com.itgrids.partyanalyst.service.IDelimitationConstituencyMandalService;
import com.itgrids.partyanalyst.service.IPartyBoothWiseResultsService;
import com.itgrids.partyanalyst.service.IStaticDataService;
import com.itgrids.partyanalyst.utils.ElectionResultComparator;
import com.itgrids.partyanalyst.utils.IConstants;
import com.opensymphony.xwork2.ActionSupport;

public class MandalPageElectionInfoAction extends ActionSupport implements ServletRequestAware, ServletContextAware{

	private HttpServletRequest request;
	private ServletContext context;
	private HttpSession session;
	private IDelimitationConstituencyMandalService delimitationConstituencyMandalService;
	private ElectionWiseMandalPartyResultListVO electionWiseMandalPartyResultListVO;
	private List<ElectionWiseMandalPartyResultVO> mptcZptcElectionResultsVO;
	private VillageDetailsVO villageDetailsVO;
	private MandalInfoVO mandalInfoVO;
	private List<SelectOptionVO> electionSelectVO;
	private Set<SelectOptionVO> partiesInMandalWiseElections;
	private IPartyBoothWiseResultsService partyBoothWiseResultsService;
	private IStaticDataService staticDataService;
	private IConstituencyPageService constituencyPageService;
	private String task = null;
	JSONObject jObj = null;
	private String mandalId;
	private MandalAndRevenueVillagesInfoVO mandalAndRevenueVillagesInfoVO;
	private List<PartyVotesEarnedVO> townshipResults;
	private static final Logger log = Logger.getLogger(MandalPageElectionInfoAction.class);
	private List<ConstituencyRevenueVillagesVO> townshipWiseElectionResults;
	private List<PartyResultVO> allElectionResults;
	private NavigationVO navigationVO;
	private EntitlementsHelper entitlementsHelper;
	private String chartProducerURL="/var/www/vsites/partyanalyst.com/httpdocs/charts/";
	public List<PartyResultVO> getAllElectionResults() {
		return allElectionResults;
	}

	public void setAllElectionResults(List<PartyResultVO> allElectionResults) {
		this.allElectionResults = allElectionResults;
	}

	public List<ConstituencyRevenueVillagesVO> getTownshipWiseElectionResults() {
		return townshipWiseElectionResults;
	}

	public void setTownshipWiseElectionResults(
			List<ConstituencyRevenueVillagesVO> townshipWiseElectionResults) {
		this.townshipWiseElectionResults = townshipWiseElectionResults;
	}

	public void setServletRequest(HttpServletRequest request) {
		this.request = request;
	}

	public IDelimitationConstituencyMandalService getDelimitationConstituencyMandalService() {
		return delimitationConstituencyMandalService;
	}

	public void setDelimitationConstituencyMandalService(
			IDelimitationConstituencyMandalService delimitationConstituencyMandalService) {
		this.delimitationConstituencyMandalService = delimitationConstituencyMandalService;
	}

	public IConstituencyPageService getConstituencyPageService() {
		return constituencyPageService;
	}

	public void setConstituencyPageService(
			IConstituencyPageService constituencyPageService) {
		this.constituencyPageService = constituencyPageService;
	}

	public ElectionWiseMandalPartyResultListVO getElectionWiseMandalPartyResultListVO() {
		return electionWiseMandalPartyResultListVO;
	}

	public void setElectionWiseMandalPartyResultListVO(
			ElectionWiseMandalPartyResultListVO electionWiseMandalPartyResultListVO) {
		this.electionWiseMandalPartyResultListVO = electionWiseMandalPartyResultListVO;
	}
	
	public IPartyBoothWiseResultsService getPartyBoothWiseResultsService() {
		return partyBoothWiseResultsService;
	}

	public void setPartyBoothWiseResultsService(
			IPartyBoothWiseResultsService partyBoothWiseResultsService) {
		this.partyBoothWiseResultsService = partyBoothWiseResultsService;
	}

	public MandalInfoVO getMandalInfoVO() {
		return mandalInfoVO;
	}

	public void setMandalInfoVO(MandalInfoVO mandalInfoVO) {
		this.mandalInfoVO = mandalInfoVO;
	}

	public VillageDetailsVO getVillageDetailsVO() {
		return villageDetailsVO;
	}

	public void setVillageDetailsVO(VillageDetailsVO villageDetailsVO) {
		this.villageDetailsVO = villageDetailsVO;
	}

	public List<SelectOptionVO> getElectionSelectVO() {
		return electionSelectVO;
	}

	public void setElectionSelectVO(List<SelectOptionVO> electionSelectVO) {
		this.electionSelectVO = electionSelectVO;
	}

	public String getTask() {
		return task;
	}

	public void setTask(String task) {
		this.task = task;
	}

	public IStaticDataService getStaticDataService() {
		return staticDataService;
	}

	public void setStaticDataService(IStaticDataService staticDataService) {
		this.staticDataService = staticDataService;
	}

	public HttpSession getSession() {
		return session;
	}

	public void setSession(HttpSession session) {
		this.session = session;
	}

	public List<PartyVotesEarnedVO> getTownshipResults() {
		return townshipResults;
	}

	public MandalAndRevenueVillagesInfoVO getMandalAndRevenueVillagesInfoVO() {
		return mandalAndRevenueVillagesInfoVO;
	}

	public void setMandalAndRevenueVillagesInfoVO(
			MandalAndRevenueVillagesInfoVO mandalAndRevenueVillagesInfoVO) {
		this.mandalAndRevenueVillagesInfoVO = mandalAndRevenueVillagesInfoVO;
	}

	public void setTownshipResults(List<PartyVotesEarnedVO> townshipResults) {
		this.townshipResults = townshipResults;
	}

	public List<ElectionWiseMandalPartyResultVO> getMptcZptcElectionResultsVO() {
		return mptcZptcElectionResultsVO;
	}

	public void setMptcZptcElectionResultsVO(
			List<ElectionWiseMandalPartyResultVO> mptcZptcElectionResultsVO) {
		this.mptcZptcElectionResultsVO = mptcZptcElectionResultsVO;
	}

	public String getMandalId() {
		return mandalId;
	}

	public void setMandalId(String mandalId) {
		this.mandalId = mandalId;
	}

	public Set<SelectOptionVO> getPartiesInMandalWiseElections() {
		return partiesInMandalWiseElections;
	}

	public void setPartiesInMandalWiseElections(
			Set<SelectOptionVO> partiesInMandalWiseElections) {
		this.partiesInMandalWiseElections = partiesInMandalWiseElections;
	}

	public NavigationVO getNavigationVO() {
		return navigationVO;
	}

	public void setNavigationVO(NavigationVO navigationVO) {
		this.navigationVO = navigationVO;
	}

	public EntitlementsHelper getEntitlementsHelper() {
		return entitlementsHelper;
	}

	public void setEntitlementsHelper(EntitlementsHelper entitlementsHelper) {
		this.entitlementsHelper = entitlementsHelper;
	}

	public String execute()throws Exception{
		
		String cPath = request.getContextPath();
		mandalId = request.getParameter("MANDAL_ID");
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
		
		//Entitlement Check
		HttpSession session = request.getSession();
		RegistrationVO regVO = session.getAttribute(IConstants.USER) != null?(RegistrationVO)session.getAttribute(IConstants.USER):null;
		
		if(((regVO != null && !entitlementsHelper.checkForEntitlementToViewReport(regVO, IConstants.TEHSIL_ANALYSIS)) ||
				(regVO == null && !entitlementsHelper.checkForEntitlementToViewReport(regVO,  IConstants.TEHSIL_ANALYSIS)))||
				!entitlementsHelper.checkForRegionToViewReport(regVO, IConstants.TEHSIL_LEVEL, Long.parseLong(mandalID)))
			villageDetailsVO.setShowRevenueVillageInfo(false);
		
		
		Throwable ex = villageDetailsVO.getExceptionEncountered();
		if(ex!=null){
			log.error("exception raised while retrieving mandal details ", ex);
		}
		
		partiesInMandalWiseElections = staticDataService.getAllPartiesParticipatedInMandal(new Long(mandalID));
		log.debug("No. Of Parties Participated In mandal:"+partiesInMandalWiseElections.size());
		ElectionWiseMandalPartyResultListVO mptcZptcResultListVO = partyBoothWiseResultsService.getAllMPTCAndZPTCElectionsInfoInTehsil(new Long(mandalID));
		mptcZptcElectionResultsVO = mptcZptcResultListVO.getPartyWiseElectionResultsVOList();
		List<PartyResultVO> acPcElectionResultsForParties = null;
		
		if(((regVO != null && entitlementsHelper.checkForEntitlementToViewReport(regVO, IConstants.TEHSIL_ANALYSIS)) ||
				(regVO == null && entitlementsHelper.checkForEntitlementToViewReport(regVO,  IConstants.TEHSIL_ANALYSIS)))||
				entitlementsHelper.checkForRegionToViewReport(regVO, IConstants.TEHSIL_LEVEL, Long.parseLong(mandalID))){
			electionWiseMandalPartyResultListVO = partyBoothWiseResultsService.getPartyGenderWiseBoothVotesForMandal(new Long(mandalID), "Mandal");
			acPcElectionResultsForParties = electionWiseMandalPartyResultListVO.getAllPartiesAllElectionResults();
		}

		List<PartyResultVO> mptcZptcElectionResultsForParties = mptcZptcResultListVO.getAllPartiesAllElectionResults();
		Map<PartyResultVO, List<ElectionResultVO>> resultMap = new HashMap<PartyResultVO, List<ElectionResultVO>>();
		
		if(acPcElectionResultsForParties !=null){
		  for(PartyResultVO partyResultVO:acPcElectionResultsForParties){
			resultMap.put(partyResultVO, partyResultVO.getElectionWiseResults());
		   }
		}
		List<ElectionResultVO> elections = null;
		for(PartyResultVO partyResultVO:mptcZptcElectionResultsForParties){
			elections = resultMap.get(partyResultVO);
			if(elections == null)
				resultMap.put(partyResultVO, elections);
			else
				elections.addAll(partyResultVO.getElectionWiseResults());
		}
		System.out.println(resultMap.size());
		
		allElectionResults = new ArrayList<PartyResultVO>();
		
		for(Map.Entry<PartyResultVO, List<ElectionResultVO>> entry:resultMap.entrySet()){
			allElectionResults.add(entry.getKey());
		}
		String chartPath="";
		String chartName = "allPartiesMandalWisePerformanceInAllElections_"+mandalId+".png";
		if(cPath.contains("PartyAnalyst"))
             chartPath = context.getRealPath("/")+ "charts\\" + chartName;
		else
		   chartPath = chartProducerURL + chartName;
        //String title, String domainAxisL, String rangeAxisL, CategoryDataset dataset, String fileName
        Set<String> paritesInChart = new LinkedHashSet<String>();
		ChartProducer.createLineChart("All Parties Performance In Diff Elections Of "+mandalName+"", "Elections", "Percentages", createDataset(allElectionResults, paritesInChart), chartPath,400,700, ChartUtils.getLineChartColors(paritesInChart) ,true);
				
		navigationVO = staticDataService.findHirarchiForNavigation(new Long(mandalId), IConstants.TEHSIL_LEVEL);
		
		return SUCCESS;
		
	}
	
	public String getElectionIdsAndYears(){
		if(task != null){
			try{
				jObj = new JSONObject(getTask());
				System.out.println("Result From JSON:"+jObj);
			}catch(Exception e){
				e.printStackTrace();
			}
			
			if(jObj.getString("task").equals("getElectionYears") || jObj.getString("task").equals("getElectionYearsForPanchayat")){
				System.out.println("For Districts Of State");
				electionSelectVO = staticDataService.getElectionIdsAndYears(jObj.getLong("electionTypeId"));
				electionSelectVO.add(0,new SelectOptionVO(0l,"Select Year"));
			}
		}
		return SUCCESS;
	}
	
	public String getTownshipDetails(){
		if(task != null){
			try{
				jObj = new JSONObject(getTask());
				System.out.println("Result From JSON:"+jObj);
			}catch(Exception e){
				e.printStackTrace();
			}
			Long partyId;
			session = request.getSession();
			RegistrationVO user = (RegistrationVO) session.getAttribute("USER");
			if(user == null)
				partyId = null;
			else
				partyId = user.getParty();
			
			if(jObj.getString("task").equals("getRevenueVillagesInfo")){
				mandalAndRevenueVillagesInfoVO = constituencyPageService.getTownshipWiseBoothDetailsForTehsil(jObj.getLong("mandalId"), jObj.getLong("electionId"));
			}
			else if(jObj.getString("task").equals("getPanchayatsInfo")){
				mandalAndRevenueVillagesInfoVO = constituencyPageService.getPanchayatWiseBoothDetailsForTehsil(jObj.getLong("mandalId"), jObj.getLong("electionId"));
			}
		}
		return SUCCESS;
	}

	public String getTownshipElectionResults(){
		if(task != null){
			try{
				jObj = new JSONObject(getTask());
				System.out.println("Result From JSON:"+jObj);
			}catch(Exception e){
				e.printStackTrace();
			}
	
			if(jObj.getString("task").equals("getRevenueVillagesElectionInfo")){
				townshipResults = constituencyPageService.getTownshipWiseElectionsForTehsil(jObj.getLong("townshipId"), jObj.getLong("electionId"));
			}
			else if(jObj.getString("task").equals("getPanchayatElectionInfo"))
			{
				townshipResults = constituencyPageService.getPanchayatWiseElectionsForTehsil(jObj.getString("boothIdStr"),jObj.getLong("electionId"));
			}
		}
		return SUCCESS;
	}
	
	private CategoryDataset createDataset(List<PartyResultVO> allElectionResults, Set<String> partiesInChart) {
        final DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        List<ElectionResultVO> partiesElectionResults = new ArrayList<ElectionResultVO>();
        ElectionResultVO partiesElecResultForGraph = null;
        
        for(PartyResultVO partyResultVO:allElectionResults)
        	for(ElectionResultVO result: partyResultVO.getElectionWiseResults()){
        		partiesElecResultForGraph = new ElectionResultVO();
    			partiesElecResultForGraph.setPercentage(result.getPercentage());
        		partiesElecResultForGraph.setElectionYear(result.getElectionYear()+" "+result.getElectionType());
        		partiesElecResultForGraph.setPartyName(partyResultVO.getPartyName());
        		partiesElectionResults.add(partiesElecResultForGraph);
          	}
        
        Collections.sort(partiesElectionResults, new ElectionResultComparator());
        
        for(ElectionResultVO graphInfo:partiesElectionResults){
        	partiesInChart.add(graphInfo.getPartyName());
        	dataset.addValue(new BigDecimal(graphInfo.getPercentage()), graphInfo.getPartyName(),
           			graphInfo.getElectionYear());	
        }
           	
        return dataset;
    }

	public void setServletContext(ServletContext context) {
		this.context = context;
	}
	
	public String getConsolidatedTownshipWiseElectionInfo(){
		if(task != null){
			try{
				jObj = new JSONObject(getTask());
				System.out.println("Result From JSON:"+jObj);
			}catch(Exception e){
				e.printStackTrace();
			}
	
			if(jObj.getString("task").equals("getAllRevenueVillagesElectionResults")){
				townshipWiseElectionResults = constituencyPageService.getPartiesResultsInVillagesGroupByMandal(jObj.getLong("mandalId"), jObj.getLong("electionId"));
				log.debug("Revenue Villages Parties::"+townshipWiseElectionResults.size());
			}
		}
		return SUCCESS;
	}
	
}
