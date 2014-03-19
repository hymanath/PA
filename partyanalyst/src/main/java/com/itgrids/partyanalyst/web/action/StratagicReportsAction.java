package com.itgrids.partyanalyst.web.action;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.util.ServletContextAware;
import org.json.JSONObject;

import com.itgrids.partyanalyst.dto.AgeRangeVO;
import com.itgrids.partyanalyst.dto.PartyEffectVO;
import com.itgrids.partyanalyst.dto.PartyElectionTrendsReportVO;
import com.itgrids.partyanalyst.dto.PartyPositionResultsVO;
import com.itgrids.partyanalyst.dto.PartyResultsVO;
import com.itgrids.partyanalyst.dto.PartyResultsVerVO;
import com.itgrids.partyanalyst.dto.RegistrationVO;
import com.itgrids.partyanalyst.dto.SelectOptionVO;
import com.itgrids.partyanalyst.dto.VoterCountVO;
import com.itgrids.partyanalyst.dto.VoterDensityWithPartyVO;
import com.itgrids.partyanalyst.excel.booth.VoterModificationVO;
import com.itgrids.partyanalyst.service.ICrossVotingEstimationService;
import com.itgrids.partyanalyst.service.IStratagicReportsService;
import com.itgrids.partyanalyst.service.ISuggestiveModelService;
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
	
	private IStratagicReportsService stratagicReportsService;
	private List<SelectOptionVO> constituenciesList;
	private List userAccessConstituencyList;
	private ICrossVotingEstimationService crossVotingEstimationService;
	private ISuggestiveModelService suggestiveModelService;
	private List<AgeRangeVO> boothWiseAddedDelList;
	
	List<PartyElectionTrendsReportVO> prevTrends;
	List<PartyElectionTrendsReportVO> prevTrendsParliament;
	
	private PartyResultsVerVO prevResults;
	private List<PartyResultsVO> prevMPTCZPTCResults;
	private PartyPositionResultsVO locationsList;
	private VoterModificationVO voterModificationVO;
	private List<VoterCountVO> VoterCountVOList;
	private VoterDensityWithPartyVO voterDensityWithPartyVO;
	
	
	private static final Logger log = Logger.getLogger(StratagicReportsAction.class);
	
	
	
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
		
		
		return Action.SUCCESS;
	}
	public String getPreviousTrendsReport(){
		try{
		jObj = new JSONObject(getTask());
	
	Long constituencyId=jObj.getLong("constituencyId");
	prevTrends=stratagicReportsService.getPreviousTrendsReport(constituencyId);
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
	prevTrendsParliament=stratagicReportsService.getPreviousTrendsReportParliament(constituencyId);
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
			}

			PartyResultsVerVO pv=stratagicReportsService.getMuncipalCorpPrevResults(constituencyId);
			if(pv.getPartyResultsVOList()!=null){
				prevResults.setMuncipalCorpResults(pv.getPartyResultsVOList());
				prevResults.setPartyStrengths(pv.getPartyStrengths());
				prevResults.setParticipated(pv.getParticipated());
				prevResults.setWon(pv.getWon());
				prevResults.setOtherVotes(pv.getOtherVotes());
				prevResults.setOtherVotesPercent(pv.getOtherVotesPercent());
			}
				
			PartyResultsVerVO pv_gmc=stratagicReportsService.getMuncipalCorpPrevResultsInGHMC(constituencyId);
			if(pv_gmc.getPartyResultsVOList()!=null){
				prevResults.setGmcResults(pv_gmc.getPartyResultsVOList());
				prevResults.setPartyStrengths(pv_gmc.getPartyStrengths());
				prevResults.setParticipated(pv_gmc.getParticipated());
				prevResults.setWon(pv_gmc.getWon());
				prevResults.setOtherVotes(pv_gmc.getOtherVotes());
				prevResults.setOtherVotesPercent(pv_gmc.getOtherVotesPercent());
			}

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
			}catch(Exception e){
				log.error("Exception raised in getVoterDensityPanchayatWiseWithPartyResult() method in StratagicReportAction",e);
			}
			return Action.SUCCESS;
	 	}
	
}
