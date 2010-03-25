package com.itgrids.partyanalyst.web.action;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
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
import com.itgrids.partyanalyst.dto.PartyResultVO;
import com.itgrids.partyanalyst.dto.PartyVotesEarnedVO;
import com.itgrids.partyanalyst.dto.RegistrationVO;
import com.itgrids.partyanalyst.dto.SelectOptionVO;
import com.itgrids.partyanalyst.dto.VillageDetailsVO;
import com.itgrids.partyanalyst.helper.ChartProducer;
import com.itgrids.partyanalyst.service.IConstituencyPageService;
import com.itgrids.partyanalyst.service.IDelimitationConstituencyMandalService;
import com.itgrids.partyanalyst.service.IPartyBoothWiseResultsService;
import com.itgrids.partyanalyst.service.IStaticDataService;
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

	public String execute()throws Exception{
		
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
		Throwable ex = villageDetailsVO.getExceptionEncountered();
		if(ex!=null){
			log.error("exception raised while retrieving mandal details ", ex);
		}
		
		partiesInMandalWiseElections = staticDataService.getAllPartiesParticipatedInMandal(new Long(mandalID));
		log.debug("No. Of Parties Participated In mandal:"+partiesInMandalWiseElections.size());
		ElectionWiseMandalPartyResultListVO mptcZptcResultListVO = partyBoothWiseResultsService.getAllMPTCAndZPTCElectionsInfoInTehsil(new Long(mandalID));
		mptcZptcElectionResultsVO = mptcZptcResultListVO.getPartyWiseElectionResultsVOList();
		electionWiseMandalPartyResultListVO = partyBoothWiseResultsService.getPartyGenderWiseBoothVotesForMandal(new Long(mandalID), "Mandal");
		List<PartyResultVO> acPcElectionResultsForParties = electionWiseMandalPartyResultListVO.getAllPartiesAllElectionResults();
		List<PartyResultVO> mptcZptcElectionResultsForParties = mptcZptcResultListVO.getAllPartiesAllElectionResults();
		
		Map<PartyResultVO, List<ElectionResultVO>> resultMap = new HashMap<PartyResultVO, List<ElectionResultVO>>();
		
		for(PartyResultVO partyResultVO:acPcElectionResultsForParties){
			resultMap.put(partyResultVO, partyResultVO.getElectionWiseResults());
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
		
		List<PartyResultVO> allElectionResults = new ArrayList<PartyResultVO>();
		
		for(Map.Entry<PartyResultVO, List<ElectionResultVO>> entry:resultMap.entrySet()){
			allElectionResults.add(entry.getKey());
		}
		
		for(PartyResultVO partyResultVO:allElectionResults){
			String chartName = "partyPerformanceInAllMandalElections_"+mandalId+"_"+partyResultVO.getPartyId()+".png";
	        String chartPath = context.getRealPath("/")+ "charts\\" + chartName;
	        //String title, String domainAxisL, String rangeAxisL, CategoryDataset dataset, String fileName
			ChartProducer.createBarChart(partyResultVO.getPartyName()+" Performance In Diff Elections Of "+mandalName+" Mandal", "Election Years", "Percentages", createDataset(partyResultVO), chartPath);
		}
				
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
			
			if(jObj.getString("task").equals("getElectionYears")){
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
		}
		return SUCCESS;
	}
	
	private CategoryDataset createDataset(PartyResultVO partyResultVO) {
        final String series1 = IConstants.ASSEMBLY_ELECTION_TYPE;
        final String series2 = IConstants.PARLIAMENT_ELECTION_TYPE;
        final String series3 = IConstants.MPTC_ELECTION_TYPE;
        final String series4 = IConstants.ZPTC_ELECTION_TYPE;
        final DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        for(ElectionResultVO result: partyResultVO.getElectionWiseResults()){
        	if(result.getElectionType().equals(series1))
        		dataset.addValue(new BigDecimal(result.getPercentage()), series1, result.getElectionYear());
        	else if(result.getElectionType().equals(series2))
        		dataset.addValue(new BigDecimal(result.getPercentage()), series2, result.getElectionYear());
        	else if(result.getElectionType().equals(series3))
        		dataset.addValue(new BigDecimal(result.getPercentage()), series3, result.getElectionYear());
        	else if(result.getElectionType().equals(series4))
        		dataset.addValue(new BigDecimal(result.getPercentage()), series4, result.getElectionYear());
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
