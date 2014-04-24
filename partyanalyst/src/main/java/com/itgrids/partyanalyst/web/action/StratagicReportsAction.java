package com.itgrids.partyanalyst.web.action;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.util.ServletContextAware;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;

import com.itgrids.partyanalyst.dto.AgeRangeVO;
import com.itgrids.partyanalyst.dto.CasteStratagicReportVO;
import com.itgrids.partyanalyst.dto.HouseHoldsVO;
import com.itgrids.partyanalyst.dto.PDFHeadingAndReturnVO;
import com.itgrids.partyanalyst.dto.PartyEffectVO;
import com.itgrids.partyanalyst.dto.PartyElectionTrendsReportVO;
import com.itgrids.partyanalyst.dto.PartyPositionResultsVO;
import com.itgrids.partyanalyst.dto.PartyPositionVO;
import com.itgrids.partyanalyst.dto.PartyResultsVO;
import com.itgrids.partyanalyst.dto.PartyResultsVerVO;
import com.itgrids.partyanalyst.dto.RegistrationVO;
import com.itgrids.partyanalyst.dto.ResultStatus;
import com.itgrids.partyanalyst.dto.SelectOptionVO;
import com.itgrids.partyanalyst.dto.StrategyVO;
import com.itgrids.partyanalyst.dto.VoterCountVO;
import com.itgrids.partyanalyst.dto.VoterDensityWithPartyVO;
import com.itgrids.partyanalyst.dto.VoterModificationGenderInfoVO;
import com.itgrids.partyanalyst.dto.VoterStratagicReportVo;
import com.itgrids.partyanalyst.excel.booth.VoterModificationVO;
import com.itgrids.partyanalyst.service.IBoothwisePollingStratagicService;
import com.itgrids.partyanalyst.service.ICrossVotingEstimationService;
import com.itgrids.partyanalyst.service.IStaticDataService;
import com.itgrids.partyanalyst.service.IStratagicReportServiceForMLASuccess;
import com.itgrids.partyanalyst.service.IStratagicReportsService;
import com.itgrids.partyanalyst.service.IStratagicReportsServicePdf;
import com.itgrids.partyanalyst.service.IStrategyModelTargetingService;
import com.itgrids.partyanalyst.service.ISuggestiveModelService;
import com.itgrids.partyanalyst.service.IVoterReportService;
import com.itgrids.partyanalyst.util.IWebConstants;
import com.itgrids.partyanalyst.utils.IConstants;
import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionSupport;

public class StratagicReportsAction extends ActionSupport implements
		ServletRequestAware, ServletContextAware {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private HttpServletRequest request;
	private HttpSession session;
	private ServletContext context;
	JSONObject jObj = null;
	private String task = null;
	private ResultStatus status;
	private IStratagicReportsService stratagicReportsService;
	private IVoterReportService voterReportService;
	
	public IVoterReportService getVoterReportService() {
		return voterReportService;
	}
	public void setVoterReportService(IVoterReportService voterReportService) {
		this.voterReportService = voterReportService;
	}

	private IStratagicReportServiceForMLASuccess stratagicReportServiceForMLASuccess;
	private List<SelectOptionVO> constituenciesList;
	private List userAccessConstituencyList;
	private ICrossVotingEstimationService crossVotingEstimationService;
	private ISuggestiveModelService suggestiveModelService;
	private List<AgeRangeVO> boothWiseAddedDelList;
	private IStrategyModelTargetingService strategyModelTargetingService;
	
	List<PartyElectionTrendsReportVO> prevTrends;
	List<PartyElectionTrendsReportVO> prevTrendsParliament;
	
	private PartyResultsVerVO prevResults;
	private List<PartyResultsVO> prevMPTCZPTCResults;
	private CasteStratagicReportVO casteStratagicReportVO =null;
	private VoterStratagicReportVo voterStratagicReportVo;
	private HouseHoldsVO houseHoldsVO;
	private List<SelectOptionVO> optionsList ;
	private ResultStatus casteContainConstiList ;
	

	public ResultStatus getCasteContainConstiList() {
		return casteContainConstiList;
	}
	public void setCasteContainConstiList(ResultStatus casteContainConstiList) {
		this.casteContainConstiList = casteContainConstiList;
	}

	private PartyPositionResultsVO locationsList;
	private IStaticDataService staticDataService;
	private VoterModificationVO voterModificationVO;
	private List<VoterCountVO> VoterCountVOList;
	private VoterDensityWithPartyVO voterDensityWithPartyVO;
	
	private PDFHeadingAndReturnVO voterAgeRangeVOList;
	private PDFHeadingAndReturnVO voterModificationAgeRangeVOList;
	private VoterModificationGenderInfoVO voterModificationGenderInfoVO;
	private PDFHeadingAndReturnVO voterModificationGenderInfoVOList;
	private String url;
	List<PartyPositionVO> boothwiseResult,panchayatResult;
	private IBoothwisePollingStratagicService boothwisePollingStratagicService;
	private List<Object> criticalPanchayats;
	private Long constituencyId;
	private List<SelectOptionVO> locationIds;
	private String type;
	
	@Autowired
	private IStratagicReportsServicePdf stratagicReportsServicePdf;
	
private static final Logger log = Logger.getLogger(StratagicReportsAction.class);
	
	
	public List<PartyPositionVO> getPanchayatResult() {
	return panchayatResult;
}
public void setPanchayatResult(List<PartyPositionVO> panchayatResult) {
	this.panchayatResult = panchayatResult;
}
	public List<PartyPositionVO> getBoothwiseResult() {
		return boothwiseResult;
	}
	public void setBoothwiseResult(List<PartyPositionVO> boothwiseResult) {
		this.boothwiseResult = boothwiseResult;
	}
	public IBoothwisePollingStratagicService getBoothwisePollingStratagicService() {
		return boothwisePollingStratagicService;
	}
	public void setBoothwisePollingStratagicService(
			IBoothwisePollingStratagicService boothwisePollingStratagicService) {
		this.boothwisePollingStratagicService = boothwisePollingStratagicService;
	}
	public HttpServletRequest getRequest() {
		return request;
	}
	public ResultStatus getStatus() {
		return status;
	}
	public void setStatus(ResultStatus status) {
		this.status = status;
	}
	public IStaticDataService getStaticDataService() {
		return staticDataService;
	}
	public void setStaticDataService(IStaticDataService staticDataService) {
		this.staticDataService = staticDataService;
	}
	public List<SelectOptionVO> getOptionsList() {
		return optionsList;
	}
	public void setOptionsList(List<SelectOptionVO> optionsList) {
		this.optionsList = optionsList;
	}
	public IStratagicReportServiceForMLASuccess getStratagicReportServiceForMLASuccess() {
		return stratagicReportServiceForMLASuccess;
	}
	public void setStratagicReportServiceForMLASuccess(
			IStratagicReportServiceForMLASuccess stratagicReportServiceForMLASuccess) {
		this.stratagicReportServiceForMLASuccess = stratagicReportServiceForMLASuccess;
	}
	public HouseHoldsVO getHouseHoldsVO() {
		return houseHoldsVO;
	}
	public void setHouseHoldsVO(HouseHoldsVO houseHoldsVO) {
		this.houseHoldsVO = houseHoldsVO;
	}
	public VoterStratagicReportVo getVoterStratagicReportVo() {
		return voterStratagicReportVo;
	}
	public void setVoterStratagicReportVo(
			VoterStratagicReportVo voterStratagicReportVo) {
		this.voterStratagicReportVo = voterStratagicReportVo;
	}
	public CasteStratagicReportVO getCasteStratagicReportVO() {
		return casteStratagicReportVO;
	}
	public void setCasteStratagicReportVO(
			CasteStratagicReportVO casteStratagicReportVO) {
		this.casteStratagicReportVO = casteStratagicReportVO;
	}
	
	

	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public PDFHeadingAndReturnVO getVoterAgeRangeVOList() {
		return voterAgeRangeVOList;
	}

	public void setVoterAgeRangeVOList(PDFHeadingAndReturnVO voterAgeRangeVOList) {
		this.voterAgeRangeVOList = voterAgeRangeVOList;
	}

	public PDFHeadingAndReturnVO getVoterModificationAgeRangeVOList() {
		return voterModificationAgeRangeVOList;
	}

	public void setVoterModificationAgeRangeVOList(
			PDFHeadingAndReturnVO voterModificationAgeRangeVOList) {
		this.voterModificationAgeRangeVOList = voterModificationAgeRangeVOList;
	}

	public PDFHeadingAndReturnVO getVoterModificationGenderInfoVOList() {
		return voterModificationGenderInfoVOList;
	}

	public void setVoterModificationGenderInfoVOList(
			PDFHeadingAndReturnVO voterModificationGenderInfoVOList) {
		this.voterModificationGenderInfoVOList = voterModificationGenderInfoVOList;
	}

	public VoterModificationGenderInfoVO getVoterModificationGenderInfoVO() {
		return voterModificationGenderInfoVO;
	}

	public void setVoterModificationGenderInfoVO(
			VoterModificationGenderInfoVO voterModificationGenderInfoVO) {
		this.voterModificationGenderInfoVO = voterModificationGenderInfoVO;
	}

	

	public VoterDensityWithPartyVO getVoterDensityWithPartyVO() {
		return voterDensityWithPartyVO;
	}

	public void setVoterDensityWithPartyVO(
			VoterDensityWithPartyVO voterDensityWithPartyVO) {
		this.voterDensityWithPartyVO = voterDensityWithPartyVO;
	}

	public List<VoterCountVO> getVoterCountVOList() {
		return VoterCountVOList;
	}

	public void setVoterCountVOList(List<VoterCountVO> voterCountVOList) {
		VoterCountVOList = voterCountVOList;
	}

	public VoterModificationVO getVoterModificationVO() {
		return voterModificationVO;
	}

	public void setVoterModificationVO(VoterModificationVO voterModificationVO) {
		this.voterModificationVO = voterModificationVO;
	}

	public PartyResultsVerVO getPrevResults() {
		return prevResults;
	}

	public void setPrevResults(PartyResultsVerVO prevResults) {
		this.prevResults = prevResults;
	}

	public List<PartyResultsVO> getPrevMPTCZPTCResults() {
		return prevMPTCZPTCResults;
	}

	public void setPrevMPTCZPTCResults(List<PartyResultsVO> prevMPTCZPTCResults) {
		this.prevMPTCZPTCResults = prevMPTCZPTCResults;
	}

	public List<PartyElectionTrendsReportVO> getPrevTrends() {
		return prevTrends;
	}

	public void setPrevTrends(List<PartyElectionTrendsReportVO> prevTrends) {
		this.prevTrends = prevTrends;
	}

	public List<PartyElectionTrendsReportVO> getPrevTrendsParliament() {
		return prevTrendsParliament;
	}

	public void setPrevTrendsParliament(
			List<PartyElectionTrendsReportVO> prevTrendsParliament) {
		this.prevTrendsParliament = prevTrendsParliament;
	}

	public ServletContext getContext() {
		return context;
	}

	public void setContext(ServletContext context) {
		this.context = context;
	}
	
	public void setServletRequest(HttpServletRequest request) {
		this.request = request;
		
	}	

	public void setRequest(HttpServletRequest request) {
		this.request = request;
	}

	public void setServletContext(ServletContext context) {
		this.context = context;
	}
	
	
	public JSONObject getjObj() {
		return jObj;
	}

	public void setjObj(JSONObject jObj) {
		this.jObj = jObj;
	}

	public String getTask() {
		return task;
	}

	public void setTask(String task) {
		this.task = task;
	}
	

	public IStratagicReportsService getStratagicReportsService() {
		return stratagicReportsService;
	}

	public void setStratagicReportsService(
			IStratagicReportsService stratagicReportsService) {
		this.stratagicReportsService = stratagicReportsService;
	}
	
	public List<SelectOptionVO> getConstituenciesList() {
		return constituenciesList;
	}

	public void setConstituenciesList(List<SelectOptionVO> constituenciesList) {
		this.constituenciesList = constituenciesList;
	}
	
	public List getUserAccessConstituencyList() {
		return userAccessConstituencyList;
	}

	public void setUserAccessConstituencyList(List userAccessConstituencyList) {
		this.userAccessConstituencyList = userAccessConstituencyList;
	}
	
	public ICrossVotingEstimationService getCrossVotingEstimationService() {
		return crossVotingEstimationService;
	}

	public void setCrossVotingEstimationService(
			ICrossVotingEstimationService crossVotingEstimationService) {
		this.crossVotingEstimationService = crossVotingEstimationService;
	}

	public ISuggestiveModelService getSuggestiveModelService() {
		return suggestiveModelService;
	}

	public void setSuggestiveModelService(
			ISuggestiveModelService suggestiveModelService) {
		this.suggestiveModelService = suggestiveModelService;
	}
	

	public HttpSession getSession() {
		return session;
	}

	public void setSession(HttpSession session) {
		this.session = session;
	}
	
	

	public List<AgeRangeVO> getBoothWiseAddedDelList() {
		return boothWiseAddedDelList;
	}

	public void setBoothWiseAddedDelList(List<AgeRangeVO> boothWiseAddedDelList) {
		this.boothWiseAddedDelList = boothWiseAddedDelList;
	}
	public PartyPositionResultsVO getLocationsList() {
		return locationsList;
	}

	public void setLocationsList(PartyPositionResultsVO locationsList) {
		this.locationsList = locationsList;
	}

	public IStrategyModelTargetingService getStrategyModelTargetingService() {
		return strategyModelTargetingService;
	}
	public void setStrategyModelTargetingService(
			IStrategyModelTargetingService strategyModelTargetingService) {
		this.strategyModelTargetingService = strategyModelTargetingService;
	}
	
	public void setCriticalPanchayats(List<Object> criticalPanchayats) {
		this.criticalPanchayats = criticalPanchayats;
	}
	
	public List<SelectOptionVO> getLocationIds() {
		return locationIds;
	}
	public void setLocationIds(List<SelectOptionVO> locationIds) {
		this.locationIds = locationIds;
	}
	public List<Object> getCriticalPanchayats() {
		return criticalPanchayats;
	}
	public Long getConstituencyId() {
		return constituencyId;
	}
	public void setConstituencyId(Long constituencyId) {
		this.constituencyId = constituencyId;
	}
	
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	@Override
	public String execute() throws Exception {
		
		session = request.getSession();
		LOG.debug(" Entered Into Stratagic Reports Execute ");
		if(session.getAttribute(IConstants.USER) == null)
			return ERROR;
		
		RegistrationVO regVO = (RegistrationVO)session.getAttribute(IConstants.USER);
		
		Long electionYear = new Long(IConstants.PRESENT_ELECTION_YEAR);
		Long electionTypeId = new Long(IConstants.ASSEMBLY_ELECTION_TYPE_ID);
		userAccessConstituencyList = crossVotingEstimationService.getConstituenciesForElectionYearAndTypeWithUserAccess(regVO.getRegistrationID(),electionYear,electionTypeId);
 		constituenciesList = suggestiveModelService.getConstituenciesForUserAccessByStateId(userAccessConstituencyList,electionTypeId,electionYear);
		constituenciesList.add(0, new SelectOptionVO(0L,"Select Constituency"));
		
		return SUCCESS;
	}
	
	
	
	public String getBoothWiseAddedAndDeletedVoters(){
		try {
			jObj = new JSONObject(getTask());
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		Long constituencyId=jObj.getLong("constituencyId");
		Long publicationDateId=IConstants.STRATAGIC_REPORT_PUBLICATION_DATE_ID;
		
		boothWiseAddedDelList=stratagicReportsService.getBoothWiseAddedAndDeletedVoters(constituencyId,publicationDateId);
		
		//stratagicReportsService.generateBoothWiseAddedDeletedVoters(boothWiseAddedDelList);
		return Action.SUCCESS;
	}
	public String getPreviousTrendsReport(){
		try{
		jObj = new JSONObject(getTask());
	
	Long constituencyId=jObj.getLong("constituencyId");
	prevTrends=stratagicReportServiceForMLASuccess.getPreviousTrendsReport(constituencyId);
		} catch (Exception e) {
			e.printStackTrace();
			return Action.ERROR;
		}
		
		return Action.SUCCESS;
	}
	
	public String getPreviousTrendsReportParliament(){
		try{
		jObj = new JSONObject(getTask());
	
	Long constituencyId=jObj.getLong("constituencyId");
	prevTrendsParliament=stratagicReportServiceForMLASuccess.getPreviousTrendsReportParliament(constituencyId);
		} catch (Exception e) {
			e.printStackTrace();
			return Action.ERROR;
		}
		
		return Action.SUCCESS;
	}
	
	public String getZptcMptcResultsOfConstituency(){
		try{
			jObj = new JSONObject(getTask());
			Long constituencyId=jObj.getLong("constituencyId");
			
			String areaType=suggestiveModelService.getConstituencyType(constituencyId);
			prevResults=new PartyResultsVerVO();
				
			if(!areaType.equalsIgnoreCase("urban")){
				prevResults=stratagicReportsService.getZptcMptcResultsOfConstituency(constituencyId);
				prevResults.setZptcMptcTitle("Zilla and Mandal Parishad Elections Results");
			}

			PartyResultsVerVO pv=stratagicReportsService.getMuncipalCorpPrevResults(constituencyId);
			if(pv.getPartyResultsVOList()!=null){
				prevResults.setMuncipalCorpResults(pv.getPartyResultsVOList());
				prevResults.setPartyStrengths(pv.getPartyStrengths());
				prevResults.setParticipated(pv.getParticipated());
				prevResults.setWon(pv.getWon());
				prevResults.setOtherVotes(pv.getOtherVotes());
				prevResults.setOtherVotesPercent(pv.getOtherVotesPercent());
				prevResults.setDistrictId(pv.getDistrictId());
				prevResults.setMuncipalOrCorpOrGmc(pv.getMuncipalOrCorpOrGmc());
				prevResults.setTotalNoOfWardsTitle("Total No of Wards -"+pv.getPartyResultsVOList().size());
				prevResults.setWardTitle("Others indicates Inclusive of All Other Parties and Independents, Independents are participated for more than one seat in some of the Wards");
				prevResults.setInformation("A categorization that provides you with insight of "+pv.getElectionBodyType()+" Election results of Wards in your constituency helping in creating a common strategy:");
			}
				
			PartyResultsVerVO pv_gmc=stratagicReportsService.getMuncipalCorpPrevResultsInGHMC(constituencyId);
			if(pv_gmc.getPartyResultsVOList()!=null){
				prevResults.setGmcResults(pv_gmc.getPartyResultsVOList());
				prevResults.setPartyStrengths(pv_gmc.getPartyStrengths());
				prevResults.setParticipated(pv_gmc.getParticipated());
				prevResults.setWon(pv_gmc.getWon());
				prevResults.setOtherVotes(pv_gmc.getOtherVotes());
				prevResults.setOtherVotesPercent(pv_gmc.getOtherVotesPercent());
				prevResults.setMuncipalOrCorpOrGmc(pv.getMuncipalOrCorpOrGmc());
				prevResults.setDistrictId(pv_gmc.getDistrictId());
				prevResults.setTotalNoOfWardsTitle("Total No of Wards -"+pv_gmc.getPartyResultsVOList().size());
				prevResults.setWardTitle("Others indicates Inclusive of All Other Parties and Independents, Independents are participated for more than one seat in some of the Wards");
				prevResults.setInformation("A categorization that provides you with insight of "+pv_gmc.getElectionBodyType()+" Election results of Wards in your constituency helping in creating a common strategy:");
			}
			//stratagicReportsService.generatePdfForLocalElectionResults(prevResults);
		} catch (Exception e) {
			e.printStackTrace();
			return Action.ERROR;
		}
		return Action.SUCCESS;
	}
	
	public String getVariationOfVotersOverParty(){
			log.debug(" Entered Into getChangeByParty in StratagicReportsAction");
		try{
			jObj = new JSONObject(getTask());
			Long constituencyId=jObj.getLong("constituencyId");
			
			String[] electionIds = jObj.getString("electionId").split(",");
    		List<Long> electionIDS = new ArrayList<Long>();
    		if(electionIds != null && electionIds.length > 0 ){
    			for(int i=0 ;i<electionIds.length ;i++){
    				electionIds[i] = replaceString(electionIds[i].toString());
    				electionIDS.add(Long.valueOf(electionIds[i].toString()));
    			}    			
    		}
    		
    		String[] partyIds = jObj.getString("partyList").split(",");
    		List<Long> partyidsList = new ArrayList<Long>();
    		if(partyIds != null && partyIds.length > 0 ){
    			for(int i=0 ;i<partyIds.length ;i++){
    				partyIds[i] = replaceString(partyIds[i].toString());
    				partyidsList.add(Long.valueOf(partyIds[i].toString()));
    			}    			
    		}
    		
			/*List<Long> electionIds=new ArrayList<Long>();
			electionIds.add(e);
			electionIds.add(e);*/
			Map<Long,PartyEffectVO> prpEffect=new HashMap<Long, PartyEffectVO>();
			
			locationsList=stratagicReportsService.getPartyChanges(constituencyId,electionIDS,partyidsList);
			
		}catch(Exception e){
			log.error(" Exception Raised In getChangeByParty in StratagicReportsAction"+e);
			return Action.ERROR;
		}
		return Action.SUCCESS;
	}
	
	private String replaceString(String stringStr){
    	stringStr = stringStr.replace("\"", "");
    	stringStr = stringStr.replace("[", "");
    	stringStr = stringStr.replace("]", "");;
    	return stringStr;
    }
	
	
	 public String getPartyWiseELecitonDetails(){
	    	log.info(" entered into getPartyWiseELecitonDetails() of ElectionResultsAnalysisReportAction class.");
	    	try {
	    		session = request.getSession();
	    		RegistrationVO regVO =(RegistrationVO) session.getAttribute("USER");
				
	    		if(regVO == null )
	    			return Action.ERROR;
	    		
	    		jObj = new JSONObject(getTask());
	    	
	    		
	    		
	    		
			} catch (Exception e) {
				log.error(" Exception occured in  getPartyWiseELecitonDetails() of ElectionResultsAnalysisReportAction class.",e);
			}
	    	
	    	return Action.SUCCESS;
	    }
	 
	 	public String getSubLevelVoterModificationReportForConstituency()
		{
			
			String param ;
			param = getTask();
			try{
				jObj = new JSONObject(param);		
			}catch (Exception e) {
				e.printStackTrace();
				log.error("Exception Occured in getAllVoterInformationInALocation() Method, Exception - "+e);
			}
			
			Long constituencyId = jObj.getLong("constituencyId");
			Long fromPublicationDateId = jObj.getLong("fromPublicationDateId");
			Long toPublicationDateId = jObj.getLong("toPublicationDateId");
			String locationType = jObj.getString("locationType");
			Long locationValue = jObj.getLong("locationValue");
			String status = jObj.getString("status");
			
			
			
			/*voterModificationVO = voterModificationService.getSubLevelsVoterModificationDetails(
					locationType,locationValue,constituencyId,fromPublicationDateId,toPublicationDateId);*/
			
			voterModificationVO = stratagicReportsService.getSubLevelsVoterModificationDetailsByLocationValue(
					locationType,locationValue,constituencyId,fromPublicationDateId,toPublicationDateId);
			
			//stratagicReportsService.getPDFForSubLevelAddedDeleted(voterModificationVO);
			return Action.SUCCESS;
			
		}
	 	
	 	
	 	//Method For Getting VoterDensity
	 	/*
	 	 * @Author SASI
	 	 * @Date 19-03-2014
	 	 * 
	 	 * */
	 	public String getVoterDensityPanchayatWiseWithPartyResult(){
	 		log.debug("Entered Into getVoterDensityPanchayatWiseWithPartyResult()");
			
			try{
				log.debug("Entered into getVoterscountInPanchayats() method in Suggestive Model Action");
				jObj = new JSONObject(getTask());
				Long constituencyId = jObj.getLong("constituencyId");
				Long publicationId = jObj.getLong("publicationId");
				voterDensityWithPartyVO = stratagicReportsService.getVotersCountInPanchayatsForDensity(constituencyId,publicationId);
				
				//stratagicReportsService.generatePDFForDensity(voterDensityWithPartyVO);
			}catch(Exception e){
				log.error("Exception raised in getVoterDensityPanchayatWiseWithPartyResult() method in StratagicReportAction",e);
			}
			return Action.SUCCESS;
	 	}
	 	
	 	public String ajaxHandler()
		{
			String param;
			param = getTask();
			
			try{
				jObj = new JSONObject(param);	
			}catch (Exception e) {
				e.printStackTrace();
				LOG.error("Exception Occured in ajaxHandler() Method, Exception - "+e);
			}
			Long constituencyId = jObj.getLong("constituencyId");
			Long fromPublicationDateId = jObj.getLong("fromPublicationDateId");
			Long toPublicationDateId = jObj.getLong("toPublicationDateId");
			String locationType = jObj.getString("locationType");
			Long locationValue = jObj.getLong("locationValue");
			String taskToDo=jObj.getString("taskToDo");
			
			if(taskToDo.equalsIgnoreCase("voterInfo")){
				voterAgeRangeVOList = stratagicReportsService.getVoterInfoByPublicationDateList(locationType,locationValue,constituencyId,fromPublicationDateId,toPublicationDateId);
				//stratagicReportsService.generatePDFForVoterInfo(voterAgeRangeVOList,"voterInfo");
			}
			
			if(taskToDo.equalsIgnoreCase("addedOrDeletedVoterInfoInALocation")){
				voterModificationAgeRangeVOList = stratagicReportsService.getVotersAddedAndDeletedCountAgeWiseInBeetweenPublications(locationType,locationValue,constituencyId,fromPublicationDateId,toPublicationDateId,"intermediate");
				//stratagicReportsService.generatePDFForVoterInfo(voterModificationAgeRangeVOList,"addedDeleted");
			}
			
			if(taskToDo.equalsIgnoreCase("genderWiseVoterModifiBetweenPublications")){
				//voterModificationGenderInfoVO = stratagicReportsService.getGenderWiseVoterModificationsBetweenPublications(locationType,locationValue,constituencyId,fromPublicationDateId,toPublicationDateId,"intermediate");
			}
			
			if(taskToDo.equalsIgnoreCase("genderWiseVoterModifiForEachPublic")){
				voterModificationGenderInfoVOList = stratagicReportsService.getGenderWiseVoterModificationsForEachPublication(locationType,locationValue,constituencyId,fromPublicationDateId,toPublicationDateId,"intermediate");
				//stratagicReportsService.generatePDFForVoterInfo(voterModificationGenderInfoVOList,"genderWise");
			}
			/*if(taskToDo.equalsIgnoreCase("getCasteContainConstituency")){
				casteContainConstiList = stratagicReportsService.getRecordsCountToCasteContainConsti(constituencyId);
				
			}*/
			return Action.SUCCESS;
		}
	
	 
		/* Srishailam start*/
		public String getHouseHoldInfoByConstituency(){
			LOG.info(" Entered into getHouseHoldDetails() in StratagicReportsAction class");
			session = request.getSession();
			RegistrationVO regvo = (RegistrationVO) session.getAttribute("USER");
			try {
				if(regvo == null)
					return Action.ERROR;
				
				jObj = new JSONObject(getTask());
				Long cosntitueny = jObj.getLong("constituencyId");
				Long publicationDateId = jObj.getLong("publicationDateId");
				houseHoldsVO = stratagicReportServiceForMLASuccess.getHouseHoldInfoByConstituency(regvo.getRegistrationID(),cosntitueny,publicationDateId);
				
			} catch (Exception e) {
				LOG.error("Exception occured in getHouseHoldDetails() of StratagicReportsAction class",e);
			}
			return Action.SUCCESS;				
		}
		public String getVotersInfoByConstituency(){
			LOG.info(" Entered into getVotersInfoByConstituency() in StratagicReportsAction class");
			session = request.getSession();
			RegistrationVO regvo = (RegistrationVO) session.getAttribute("USER");
			try {
				if(regvo == null)
					return Action.ERROR;
				
				jObj = new JSONObject(getTask());
				Long cosntitueny = jObj.getLong("constituencyId");
				Long publicationDateId = jObj.getLong("publicationDateId");
				voterStratagicReportVo = stratagicReportServiceForMLASuccess.getVotersInfoByConstituency(regvo.getRegistrationID(),cosntitueny,publicationDateId);
				
			} catch (Exception e) {
				LOG.error("Exception occured in getVotersInfoByConstituency() of StratagicReportsAction class",e);
			}
			return Action.SUCCESS;				
		}
		public String getFirstTimeVotersInfoByConstituency(){
			LOG.info(" Entered into getFirstTimeVotersInfoByConstituency() in StratagicReportsAction class");
			session = request.getSession();
			RegistrationVO regvo = (RegistrationVO) session.getAttribute("USER");
			try {
				if(regvo == null)
					return Action.ERROR;
				
				jObj = new JSONObject(getTask());
				Long cosntitueny = jObj.getLong("constituencyId");
				Long publicationDateId = jObj.getLong("publicationDateId");
				voterStratagicReportVo = stratagicReportServiceForMLASuccess.getFirstTimeVotersInfoByConstituency(regvo.getRegistrationID(),cosntitueny,publicationDateId);
				
			} catch (Exception e) {
				LOG.error("Exception occured in getFirstTimeVotersInfoByConstituency() of StratagicReportsAction class",e);
			}
			return Action.SUCCESS;				
		}
		public String getAgeWiseVotersInfoByConstituency(){
			LOG.info(" Entered into getAgeWiseVotersInfoByConstituency() in StratagicReportsAction class");
			session = request.getSession();
			RegistrationVO regvo = (RegistrationVO) session.getAttribute("USER");
			try {
				if(regvo == null)
					return Action.ERROR;
				
				jObj = new JSONObject(getTask());
				Long cosntitueny = jObj.getLong("constituencyId");
				Long publicationDateId = jObj.getLong("publicationDateId");
				voterStratagicReportVo = stratagicReportServiceForMLASuccess.getAgeWiseVotersInfoByConstituency(regvo.getRegistrationID(),cosntitueny,publicationDateId);
				
			} catch (Exception e) {
				LOG.error("Exception occured in getAgeWiseVotersInfoByConstituency() of StratagicReportsAction class",e);
			}
			return Action.SUCCESS;				
		}
		public String getCasteWiseVotersInfoByConstituency(){
			LOG.info(" Entered into getCasteWiseVotersInfoByConstituency() in StratagicReportsAction class");
			session = request.getSession();
			RegistrationVO regvo = (RegistrationVO) session.getAttribute("USER");
			try {
				if(regvo == null)
					return Action.ERROR;
				
				jObj = new JSONObject(getTask());
				Long cosntitueny = jObj.getLong("constituencyId");
				Long publicationDateId = jObj.getLong("publicationDateId");
				casteStratagicReportVO = stratagicReportServiceForMLASuccess.getCasteWiseVotersInfoByConstituency(regvo.getRegistrationID(),cosntitueny,publicationDateId);
				
			} catch (Exception e) {
				LOG.error("Exception occured in getCasteWiseVotersInfoByConstituency() of StratagicReportsAction class",e);
			}
			return Action.SUCCESS;				
		}
		
		/* Srishailam end*/
		
		public String mergePanchayats(){
			LOG.info(" Entered into mergePanchayats() in StratagicReportsAction class");
			session = request.getSession();
			RegistrationVO regvo = (RegistrationVO) session.getAttribute("USER");
			try {
				if(regvo == null)
					return Action.ERROR;

			} catch (Exception e) {
				LOG.error("Exception occured in mergePanchayats() of StratagicReportsAction class",e);
			}
			return Action.SUCCESS;
		}
		
		
		
		public String getSearchTypeDetailsAction(){
			LOG.info(" Entered into getSearchTypeDetailsAction() in StratagicReportsAction class");
			session = request.getSession();
			RegistrationVO regvo = (RegistrationVO) session.getAttribute("USER");
			try {
				if(regvo == null)
					return Action.ERROR;
				
				jObj = new JSONObject(getTask());
				
				String searchtype =  jObj.getString("searchType");
				Long cosntituencyId =  jObj.getLong("cosntituencyId");
				
				optionsList = stratagicReportsService.getSearchTypeDetails(regvo.getRegistrationID(),searchtype,cosntituencyId);
				
			} catch (Exception e) {
				LOG.error("Exception occured in getSearchTypeDetailsAction() of StratagicReportsAction class",e);
			}
			return Action.SUCCESS;
		}
		
		public String mergePanchayatsToOnePanchayat(){
			
			LOG.info(" Entered into getSearchTypeDetailsAction() in StratagicReportsAction class");
			session = request.getSession();
			RegistrationVO regvo = (RegistrationVO) session.getAttribute("USER");
			try {
				if(regvo == null)
					return Action.ERROR;
				
				jObj = new JSONObject(getTask());
				
				String searchtype =  jObj.getString("type");
				Long cosntituencyId =  jObj.getLong("cosntituencyId");
				Long searchTypeValue =  jObj.getLong("searchTypeValue");
				Long panchayatId =  jObj.getLong("panchayatId");
				String PanchayatIds =  jObj.getString("PanchayatIdsForMerge");
				String[]pachayatList = PanchayatIds.split(",");
				List<Long> mergedPanchyatsIds = new ArrayList<Long>();
				
				for(int i = 0;i<pachayatList.length;i++){
					pachayatList[i] = replaceString(pachayatList[i]);
					mergedPanchyatsIds.add(Long.valueOf(pachayatList[i].toString()));
				}
				
				status = stratagicReportsService.mergePanchayatsToOnePanchayat(regvo.getRegistrationID(),searchtype,searchTypeValue,
						cosntituencyId,panchayatId,mergedPanchyatsIds);
				
			} catch (Exception e) {
				LOG.error("Exception occured in getSearchTypeDetailsAction() of StratagicReportsAction class",e);
			}
			return Action.SUCCESS;
			
		}
		
		public String electionDetailsByConstituency(){
			
			LOG.info(" Entered into electionDetailsByConstituency() in StratagicReportsAction class");
			session = request.getSession();
			RegistrationVO regvo = (RegistrationVO) session.getAttribute("USER");
			try {
				if(regvo == null)
					return Action.ERROR;
				
				jObj = new JSONObject(getTask());
				Long constituencyId= jObj.getLong("constituencyId");
				optionsList = stratagicReportsService.getElectionIdsAndYearsByCosntutuencyId(2L,constituencyId); //assembly wise election details
				
			} catch (Exception e) {
				LOG.error("Exception occured in electionDetailsByConstituency() of StratagicReportsAction class",e);
			}
			return Action.SUCCESS;
			
		}

	public String getPanchayatDetailsForElectionInCosntituency(){
		
		LOG.info(" Entered into getPanchayatDetailsForElectionInCosntituency() in StratagicReportsAction class");
		session = request.getSession();
		RegistrationVO regvo = (RegistrationVO) session.getAttribute("USER");
		try {
			if(regvo == null)
				return Action.ERROR;
			
			jObj = new JSONObject(getTask());
			Long constituencyId= jObj.getLong("constituencyId");
			Long electionId= jObj.getLong("electionId");
			optionsList = stratagicReportsService.getPanchayatDetailsForElectionInCosntituency(regvo.getRegistrationID(),constituencyId,electionId);
			
		} catch (Exception e) {
			LOG.error("Exception occured in getPanchayatDetailsForElectionInCosntituency() of StratagicReportsAction class",e);
		}
		return Action.SUCCESS;
	}
	
	public String generateReport(){
		LOG.info(" Entered into generateReport() ");
		try {
			
			StrategyVO strategyVO = new StrategyVO();
			jObj = new JSONObject(getTask());
			strategyVO.setConstituencyId(jObj.getLong("constituencyId"));
			strategyVO.setPartyId(jObj.getLong("partyId"));
			strategyVO.setPublicationId(jObj.getLong("publicationId"));
			List<Long> electionIds = new ArrayList<Long>();
			electionIds.add(jObj.getLong("electionYear1"));
			electionIds.add(jObj.getLong("electionYear2"));
			strategyVO.setElectionIds(electionIds);
			Map<Long,Float> castePercents = null;
			org.json.JSONArray castesArray = jObj.getJSONArray("expCasteArray");
			if(castesArray.length() > 0){
				castePercents = new HashMap<Long,Float>();
				for(int i = 0;i<castesArray.length();i++){
					JSONObject jSONObject= castesArray.getJSONObject(i);
					castePercents.put(jSONObject.getLong("casteId"), new Float((jSONObject.getDouble("expPerc"))));
				}
			}
			strategyVO.setCastePercents(castePercents);
			strategyVO.setPrevTrnzWt(jObj.getDouble("prevTrnzWt"));
			strategyVO.setYoungWt(jObj.getDouble("youngWt"));
			strategyVO.setPrpWt(jObj.getDouble("prpWt"));
			strategyVO.setAgedWt(jObj.getDouble("agedWt"));
			strategyVO.setTotalCastWt(jObj.getDouble("totalCastWt"));
			strategyVO.setBase(jObj.getLong("base"));
			strategyVO.setAssured(jObj.getLong("assured"));
			strategyVO.setTdpPerc(jObj.getLong("partyPerc"));
			strategyVO.setEffectPartyId(jObj.getLong("effectPartyId"));
			strategyVO.setEffectElectionId(jObj.getLong("effectElectionId"));
			String excludePanchysStr = jObj.getString("excludePanchys");
			List<Long> excludePanchys = new ArrayList<Long>();
			if(excludePanchysStr.trim().length() > 0){
				String[] pancStrArr = excludePanchysStr.split(",");
				for(String panc:pancStrArr){
				  try{
					if(panc.trim().length() > 0){
						excludePanchys.add(Long.parseLong(panc.trim()));
					}
				  }catch(Exception e){
					  LOG.error(" ",e);
				  }
				}
			}
			strategyVO.setExcludePanchys(excludePanchys);
			strategyVO.setOnlyPriority(jObj.getBoolean("onlyPriority"));
			if(jObj.getBoolean("considerRange")){
				strategyVO.setWorstMin(0d);
				strategyVO.setWorstMax(jObj.getDouble("worstMax"));
				strategyVO.setVeryPoorMin(jObj.getDouble("veryPoorMin"));
				strategyVO.setVeryPoorMax(jObj.getDouble("veryPoorMax"));
				strategyVO.setPoorMin(jObj.getDouble("poorMin"));
				strategyVO.setPoorMax(jObj.getDouble("poorMax"));
				strategyVO.setOkMin(jObj.getDouble("okMin"));
				strategyVO.setOkMax(jObj.getDouble("okMax"));
				strategyVO.setStrongMin(jObj.getDouble("strongMin"));
				strategyVO.setStrongMax(jObj.getDouble("strongMax"));
				strategyVO.setVeryStrongMin(jObj.getDouble("veryStrongMin"));
				strategyVO.setVeryStrongMax(100d);
			}
			url = stratagicReportsServicePdf.buildPdfDelegator(strategyVO).toString();
		}catch (Exception e) {
			LOG.error("Exception occured in generateReport() ",e);
		}
		return Action.SUCCESS;
	}
	
	public String getPollingPercentageForBoothsOfTDPEffect(){
		try{
		jObj = new JSONObject(getTask());
	
	Long constituencyId=jObj.getLong("constituencyId");
	Long partyId = jObj.getLong("partyId");
	Long electionId = jObj.getLong("electionId");
	Long electionId1 = jObj.getLong("electionId1");
	
	boothwiseResult = boothwisePollingStratagicService.getPollingPercentagesByParty(constituencyId,partyId,electionId,electionId1);
		} catch (Exception e) {
			e.printStackTrace();
			return Action.ERROR;
		}
		
		return Action.SUCCESS;
	}
	/*public String buildStrategicPdf()
	{
		System.out.println("inside==========================================");
		StratagicReportInputVO inputs = new StratagicReportInputVO();
		
		stratagicReportsServicePdf.buildPdfDelegator(inputs);
		
		return Action.SUCCESS;
	}*/
	
	public String getCriticalPanchayatsToAnalyse(){
		try{
			jObj = new JSONObject(getTask());
		String path = IWebConstants.STATIC_CONTENT_FOLDER_URL;
		  criticalPanchayats = strategyModelTargetingService.getCriticalPanchayats(jObj.getLong("constituencyId"),path);
		} catch (Exception e) {
			LOG.error("Exception occured in getCriticalPanchayats() ",e);
			return Action.ERROR;
		}
		
		return Action.SUCCESS;
	}
	
	public String criticalPanchayats(){
		session = request.getSession();
		String type = request.getParameter("type");
		RegistrationVO regvo = (RegistrationVO) session.getAttribute("USER");
		List<SelectOptionVO> userAccessConstiList = null;
		if(regvo == null)
			return "input";
	  if(regvo.getAccessType() != null){
		if("MLA".equalsIgnoreCase(regvo.getAccessType())){
			userAccessConstiList = new ArrayList<SelectOptionVO>();
			SelectOptionVO constituencyVo = new SelectOptionVO();
			Long constiId = Long.valueOf(regvo.getAccessValue().trim());
			constituencyVo.setName(crossVotingEstimationService.getConstituencyName(constiId));
			constituencyVo.setId(constiId);
			userAccessConstiList.add(constituencyVo);
		}else if("STATE".equalsIgnoreCase(regvo.getAccessType()) || "MP".equalsIgnoreCase(regvo.getAccessType()) || "DISTRICT".equalsIgnoreCase(regvo.getAccessType()) || "COUNTRY".equalsIgnoreCase(regvo.getAccessType())){
			Long electionYear = new Long(IConstants.PRESENT_ELECTION_YEAR);
			Long electionTypeId = new Long(IConstants.ASSEMBLY_ELECTION_TYPE_ID);
			userAccessConstiList = crossVotingEstimationService.getConstituenciesForElectionYearAndTypeWithUserAccess(regvo.getRegistrationID(),electionYear,electionTypeId);
		}else{
			return Action.ERROR;
		}
		Set<Long> constituencyIds = new HashSet<Long>();
		for(SelectOptionVO vo:userAccessConstiList){
			constituencyIds.add(vo.getId());
		}
		if(type != null && type.equalsIgnoreCase("criticalPanchayats")){
		    constituenciesList =  crossVotingEstimationService.getRuralAndRurlaUrbanConstis(constituencyIds);
		}else{
			constituenciesList = userAccessConstiList;
		}
		if(constituenciesList.size() == 1){
			constituencyId = constituenciesList.get(0).getId();
		}
	  }else{
		  return Action.ERROR;
	  }
		
		return Action.SUCCESS;
	}
	
	public String getPanchayatAssemblyResultDetails(){
		try{
			jObj = new JSONObject(getTask());
			String path = IWebConstants.STATIC_CONTENT_FOLDER_URL;
			panchayatResult = suggestiveModelService.getPartyPerfromanceStratagicReport(jObj.getLong("constituencyId"),jObj.getLong("partyId"),jObj.getLong("effectElectionId"),path);
		} catch (Exception e) {
			LOG.error("Exception occured in getPanchayatAssemblyResultDetails() ",e);
			return Action.ERROR;
		}
		
		return Action.SUCCESS;
	}
	
	public String getCasteContainConstituency(){
		try{
			jObj = new JSONObject(getTask());
			Long constituencyId= jObj.getLong("constituencyId");
		 casteContainConstiList = stratagicReportsService.getRecordsCountToCasteContainConsti(constituencyId);
		} catch (Exception e) {
			LOG.error("Exception occured in getCasteContainConstituency() ",e);
			return Action.ERROR;
		}
		
		return Action.SUCCESS;
	}
	
	
}
