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
import com.itgrids.partyanalyst.dto.CasteStratagicReportVO;
import com.itgrids.partyanalyst.dto.HouseHoldsVO;
import com.itgrids.partyanalyst.dto.PDFHeadingAndReturnVO;
import com.itgrids.partyanalyst.dto.PartyEffectVO;
import com.itgrids.partyanalyst.dto.PartyElectionTrendsReportVO;
import com.itgrids.partyanalyst.dto.PartyPositionResultsVO;
import com.itgrids.partyanalyst.dto.PartyResultsVO;
import com.itgrids.partyanalyst.dto.PartyResultsVerVO;
import com.itgrids.partyanalyst.dto.RegistrationVO;
import com.itgrids.partyanalyst.dto.ResultStatus;
import com.itgrids.partyanalyst.dto.SelectOptionVO;
import com.itgrids.partyanalyst.dto.VoterCountVO;
import com.itgrids.partyanalyst.dto.VoterDensityWithPartyVO;
import com.itgrids.partyanalyst.dto.VoterModificationGenderInfoVO;
import com.itgrids.partyanalyst.dto.VoterStratagicReportVo;
import com.itgrids.partyanalyst.excel.booth.VoterModificationVO;
import com.itgrids.partyanalyst.service.ICrossVotingEstimationService;
import com.itgrids.partyanalyst.service.IStaticDataService;
import com.itgrids.partyanalyst.service.IStratagicReportServiceForMLASuccess;
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
	private ResultStatus status;
	private IStratagicReportsService stratagicReportsService;
	private IStratagicReportServiceForMLASuccess stratagicReportServiceForMLASuccess;
	private List<SelectOptionVO> constituenciesList;
	private List userAccessConstituencyList;
	private ICrossVotingEstimationService crossVotingEstimationService;
	private ISuggestiveModelService suggestiveModelService;
	private List<AgeRangeVO> boothWiseAddedDelList;
	
	List<PartyElectionTrendsReportVO> prevTrends;
	List<PartyElectionTrendsReportVO> prevTrendsParliament;
	
	private PartyResultsVerVO prevResults;
	private List<PartyResultsVO> prevMPTCZPTCResults;
	private CasteStratagicReportVO casteStratagicReportVO =null;
	private VoterStratagicReportVo voterStratagicReportVo;
	private HouseHoldsVO houseHoldsVO;
	private List<SelectOptionVO> optionsList ;
	private PartyPositionResultsVO locationsList;
	private IStaticDataService staticDataService;
	private VoterModificationVO voterModificationVO;
	private List<VoterCountVO> VoterCountVOList;
	private VoterDensityWithPartyVO voterDensityWithPartyVO;
	
	private PDFHeadingAndReturnVO voterAgeRangeVOList;
	private PDFHeadingAndReturnVO voterModificationAgeRangeVOList;
	private VoterModificationGenderInfoVO voterModificationGenderInfoVO;
	private PDFHeadingAndReturnVO voterModificationGenderInfoVOList;
	
	private static final Logger log = Logger.getLogger(StratagicReportsAction.class);
	
	
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
				prevResults.setDistrictId(pv.getDistrictId());
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
			}
			
			if(taskToDo.equalsIgnoreCase("addedOrDeletedVoterInfoInALocation")){
				voterModificationAgeRangeVOList = stratagicReportsService.getVotersAddedAndDeletedCountAgeWiseInBeetweenPublications(locationType,locationValue,constituencyId,fromPublicationDateId,toPublicationDateId,"intermediate");
			}
			
			if(taskToDo.equalsIgnoreCase("genderWiseVoterModifiBetweenPublications")){
				voterModificationGenderInfoVO = stratagicReportsService.getGenderWiseVoterModificationsBetweenPublications(locationType,locationValue,constituencyId,fromPublicationDateId,toPublicationDateId,"intermediate");
			}
			
			if(taskToDo.equalsIgnoreCase("genderWiseVoterModifiForEachPublic")){
				voterModificationGenderInfoVOList = stratagicReportsService.getGenderWiseVoterModificationsForEachPublication(locationType,locationValue,constituencyId,fromPublicationDateId,toPublicationDateId,"intermediate");
			}
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
}
