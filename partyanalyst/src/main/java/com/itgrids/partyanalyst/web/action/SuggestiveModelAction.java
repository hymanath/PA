package com.itgrids.partyanalyst.web.action;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.json.JSONArray;
import org.json.JSONObject;

import com.itgrids.partyanalyst.dto.BasicVO;
import com.itgrids.partyanalyst.dto.CastVO;
import com.itgrids.partyanalyst.dto.CensusVO;
import com.itgrids.partyanalyst.dto.DelimitationEffectVO;
import com.itgrids.partyanalyst.dto.ExceptCastsVO;
import com.itgrids.partyanalyst.dto.MandalVO;
import com.itgrids.partyanalyst.dto.OptionVO;
import com.itgrids.partyanalyst.dto.PanchayatVO;
import com.itgrids.partyanalyst.dto.PartyImpactVO;
import com.itgrids.partyanalyst.dto.PartyPositionVO;
import com.itgrids.partyanalyst.dto.RegistrationVO;
import com.itgrids.partyanalyst.dto.SelectOptionVO;
import com.itgrids.partyanalyst.dto.VoterCountVO;
import com.itgrids.partyanalyst.dto.VoterDataVO;
import com.itgrids.partyanalyst.dto.VoterHouseInfoVO;
import com.itgrids.partyanalyst.dto.VotersInfoForMandalVO;
import com.itgrids.partyanalyst.dto.YouthLeaderSelectionVO;
import com.itgrids.partyanalyst.excel.booth.VoterVO;
import com.itgrids.partyanalyst.helper.EntitlementsHelper;
import com.itgrids.partyanalyst.service.ICrossVotingEstimationService;
import com.itgrids.partyanalyst.service.IElectionService;
import com.itgrids.partyanalyst.service.IStaticDataService;
import com.itgrids.partyanalyst.service.ISuggestiveModelService;
import com.itgrids.partyanalyst.util.IWebConstants;
import com.itgrids.partyanalyst.utils.IConstants;
import com.opensymphony.xwork2.Action;

public class SuggestiveModelAction  implements ServletRequestAware {
	
	private HttpServletRequest request;
	private ISuggestiveModelService suggestiveModelService;
	private HttpSession session;
	private String task;
	JSONObject jObj;
	private OptionVO optionVO;
	private List<SelectOptionVO> constituencies;
	private List<SelectOptionVO> electionsYears;
	private List<SelectOptionVO> partyList;
	private IStaticDataService staticDataService;
	private List<PartyPositionVO> partyPositionVOList;
	private List<PanchayatVO> panchayatVOs;
	private List<YouthLeaderSelectionVO> leaderSelectionList;
	private ICrossVotingEstimationService crossVotingEstimationService;
	private List userAccessConstituencyList;
	private List<VoterVO> deletedVoters;
	private List<SelectOptionVO> castesList;
	private List<YouthLeaderSelectionVO> leaderSelectionLists;
	private Map<String,Map<String,PartyImpactVO>> resultMap;
	private List<BasicVO> hamletDetails;
	private EntitlementsHelper entitlementsHelper;
	private boolean hideMainMenu;
	private boolean castDetails;
	private boolean prevElecResults;
	private boolean youngVoters;
	private boolean oldVoters;
	private Long constituencyId;
	private String casteIds;
	private Long fromYear;
	private Long toYear;
	private Long partyId;
	private Long fromAge;
	private Long toAge;
	private String constituencyName;
	private List<VoterDataVO> mandalPanchayatList;
	private MandalVO paricipatedPartyList;
	private List<VoterCountVO> voterCountVOList;
	private List<SelectOptionVO> pachayatsList;
	private IElectionService electionService;
	private DelimitationEffectVO delimitationEffectVO;
	private CensusVO constituencyCensusResultVO;
	private static final Logger LOG = Logger.getLogger(SuggestiveModelAction.class);
	private VotersInfoForMandalVO votersInfoForMandalVO;
	private Long latestPublicationId;
	private List<VoterHouseInfoVO> familyInfo;
	
	public List<VoterHouseInfoVO> getFamilyInfo() {
		return familyInfo;
	}
	public void setFamilyInfo(List<VoterHouseInfoVO> familyInfo) {
		this.familyInfo = familyInfo;
	}
	public Long getLatestPublicationId() {
		return latestPublicationId;
	}
	public void setLatestPublicationId(Long latestPublicationId) {
		this.latestPublicationId = latestPublicationId;
	}
	public CensusVO getConstituencyCensusResultVO() {
		return constituencyCensusResultVO;
	}
	public void setConstituencyCensusResultVO(CensusVO constituencyCensusResultVO) {
		this.constituencyCensusResultVO = constituencyCensusResultVO;
	}
	public IElectionService getElectionService() {
		return electionService;
	}
	public void setElectionService(IElectionService electionService) {
		this.electionService = electionService;
	}
	
	public VotersInfoForMandalVO getVotersInfoForMandalVO() {
		return votersInfoForMandalVO;
	}

	public void setVotersInfoForMandalVO(VotersInfoForMandalVO votersInfoForMandalVO) {
		this.votersInfoForMandalVO = votersInfoForMandalVO;
	}
	
	public DelimitationEffectVO getDelimitationEffectVO() {
		return delimitationEffectVO;
	}

	public void setDelimitationEffectVO(DelimitationEffectVO delimitationEffectVO) {
		this.delimitationEffectVO = delimitationEffectVO;
	}

	public List<SelectOptionVO> getPachayatsList() {
		return pachayatsList;
	}

	public void setPachayatsList(List<SelectOptionVO> pachayatsList) {
		this.pachayatsList = pachayatsList;
	}

	public List<VoterCountVO> getVoterCountVOList() {
		return voterCountVOList;
	}

	public void setVoterCountVOList(List<VoterCountVO> voterCountVOList) {
		this.voterCountVOList = voterCountVOList;
	}

	public MandalVO getParicipatedPartyList() {
		return paricipatedPartyList;
	}

	public void setParicipaytedPartyList(MandalVO paricipatedPartyList) {
		this.paricipatedPartyList = paricipatedPartyList;
	}

	public List<BasicVO> getHamletDetails() {
		return hamletDetails;
	}

	public void setHamletDetails(List<BasicVO> hamletDetails) {
		this.hamletDetails = hamletDetails;
	}
	public EntitlementsHelper getEntitlementsHelper() {
		return entitlementsHelper;
	}

	public void setEntitlementsHelper(EntitlementsHelper entitlementsHelper) {
		this.entitlementsHelper = entitlementsHelper;
	}
	
	public Map<String, Map<String, PartyImpactVO>> getResultMap() {
		return resultMap;
	}

	public void setResultMap(Map<String, Map<String, PartyImpactVO>> resultMap) {
		this.resultMap = resultMap;
	}
	
	
	
	public List<YouthLeaderSelectionVO> getLeaderSelectionLists() {
		return leaderSelectionLists;
	}

	public void setLeaderSelectionLists(
			List<YouthLeaderSelectionVO> leaderSelectionLists) {
		this.leaderSelectionLists = leaderSelectionLists;
	}

	public List<SelectOptionVO> getCastesList() {
		return castesList;
	}

	public void setCastesList(List<SelectOptionVO> castesList) {
		this.castesList = castesList;
	}

	public List<VoterVO> getDeletedVoters() {
		return deletedVoters;
	}

	public void setDeletedVoters(List<VoterVO> deletedVoters) {
		this.deletedVoters = deletedVoters;
	}

	public List<PanchayatVO> getPanchayatVOs() {
		return panchayatVOs;
	}

	public void setPanchayatVOs(List<PanchayatVO> panchayatVOs) {
		this.panchayatVOs = panchayatVOs;
	}

	public List<SelectOptionVO> getPartyList() {
		return partyList;
	}

	public void setPartyList(List<SelectOptionVO> partyList) {
		this.partyList = partyList;
	}

	public void setServletRequest(HttpServletRequest request) {
		this.request = request;
		
	}
	
	public ISuggestiveModelService getSuggestiveModelService() {
		return suggestiveModelService;
	}

	public HttpServletRequest getRequest() {
		return request;
	}

	public void setRequest(HttpServletRequest request) {
		this.request = request;
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

	public String getTask() {
		return task;
	}

	public void setTask(String task) {
		this.task = task;
	}

	public JSONObject getjObj() {
		return jObj;
	}

	public void setjObj(JSONObject jObj) {
		this.jObj = jObj;
	}
	public OptionVO getOptionVO() {
		return optionVO;
	}

	public void setOptionVO(OptionVO optionVO) {
		this.optionVO = optionVO;
	}
	public IStaticDataService getStaticDataService() {
		return staticDataService;
	}
	public void setStaticDataService(IStaticDataService staticDataService) {
		this.staticDataService = staticDataService;
	}
	
	public List<SelectOptionVO> getConstituencies() {
		return constituencies;
	}
	public void setConstituencies(List<SelectOptionVO> constituencies) {
		this.constituencies = constituencies;
	}		
	public List<SelectOptionVO> getElectionsYears() {
		return electionsYears;
	}
	public void setElectionsYears(List<SelectOptionVO> electionsYears) {
		this.electionsYears = electionsYears;
	}
	
	public List<PartyPositionVO> getPartyPositionVOList() {
		return partyPositionVOList;
	}

	public void setPartyPositionVOList(List<PartyPositionVO> partyPositionVOList) {
		this.partyPositionVOList = partyPositionVOList;
	}
	
	public List<YouthLeaderSelectionVO> getLeaderSelectionList() {
		return leaderSelectionList;
	}

	public void setLeaderSelectionList(
			List<YouthLeaderSelectionVO> leaderSelectionList) {
		this.leaderSelectionList = leaderSelectionList;
	}
	public ICrossVotingEstimationService getCrossVotingEstimationService() {
		return crossVotingEstimationService;
	}

	public void setCrossVotingEstimationService(
			ICrossVotingEstimationService crossVotingEstimationService) {
		this.crossVotingEstimationService = crossVotingEstimationService;
	}

	public List getUserAccessConstituencyList() {
		return userAccessConstituencyList;
	}
	
	public void setUserAccessConstituencyList(List userAccessConstituencyList) {
		this.userAccessConstituencyList = userAccessConstituencyList;
	}


	public boolean isHideMainMenu() {
		return hideMainMenu;
	}

	public void setHideMainMenu(boolean hideMainMenu) {
		this.hideMainMenu = hideMainMenu;
	}

	public boolean isPrevElecResults() {
		return prevElecResults;
	}

	public void setPrevElecResults(boolean prevElecResults) {
		this.prevElecResults = prevElecResults;
	}

	public boolean isYoungVoters() {
		return youngVoters;
	}

	public void setYoungVoters(boolean youngVoters) {
		this.youngVoters = youngVoters;
	}

	public boolean isOldVoters() {
		return oldVoters;
	}

	public void setOldVoters(boolean oldVoters) {
		this.oldVoters = oldVoters;
	}

	public Long getConstituencyId() {
		return constituencyId;
	}

	public void setConstituencyId(Long constituencyId) {
		this.constituencyId = constituencyId;
	}

	public String getCasteIds() {
		return casteIds;
	}

	public void setCasteIds(String casteIds) {
		this.casteIds = casteIds;
	}

	public Long getFromYear() {
		return fromYear;
	}

	public void setFromYear(Long fromYear) {
		this.fromYear = fromYear;
	}

	public Long getToYear() {
		return toYear;
	}

	public void setToYear(Long toYear) {
		this.toYear = toYear;
	}

	public Long getPartyId() {
		return partyId;
	}

	public void setPartyId(Long partyId) {
		this.partyId = partyId;
	}

	public Long getFromAge() {
		return fromAge;
	}

	public void setFromAge(Long fromAge) {
		this.fromAge = fromAge;
	}

	public Long getToAge() {
		return toAge;
	}

	public void setToAge(Long toAge) {
		this.toAge = toAge;
	}

	

	public String getConstituencyName() {
		return constituencyName;
	}

	public void setConstituencyName(String constituencyName) {
		this.constituencyName = constituencyName;
	}

	public boolean isCastDetails() {
		return castDetails;
	}

	public void setCastDetails(boolean castDetails) {
		this.castDetails = castDetails;
	}
	
	public List<VoterDataVO> getMandalPanchayatList() {
		return mandalPanchayatList;
	}

	public void setMandalPanchayatList(List<VoterDataVO> mandalPanchayatList) {
		this.mandalPanchayatList = mandalPanchayatList;
	}

	public String execute(){
		session = request.getSession();
		RegistrationVO regVO = (RegistrationVO) request.getSession().getAttribute("USER");
		/*if(session.getAttribute(IConstants.USER) == null && 
				!entitlementsHelper.checkForEntitlementToViewReport(null, IConstants.VOTER_ANALYSIS))
			return Action.INPUT;
		if(!entitlementsHelper.checkForEntitlementToViewReport((RegistrationVO)session.getAttribute(IConstants.USER), IConstants.VOTER_ANALYSIS))
			return Action.ERROR;*/
		List<String> entitlements = null;
	    if(regVO.getEntitlements() != null && regVO.getEntitlements().size()>0){
	      entitlements = regVO.getEntitlements();
	      if(regVO == null &&  !entitlements.contains(IConstants.VOTER_ANALYSIS)){
	    	  return Action.INPUT;
	      }
	      if(!entitlements.contains(IConstants.VOTER_ANALYSIS)){
	    	  return Action.ERROR;
	      }
	    }
		else {
			
			return Action.SUCCESS;
		}
		return Action.SUCCESS;
			
	}
	
	public String getPartyPerformanceForSelectedLocation()
	{
		try{
		session = request.getSession();
		jObj = new JSONObject(getTask());
		
		 if(jObj.getString("task").equalsIgnoreCase("getPartyPerformanceReport"))
		 {
			 List<Long> electionIds = new ArrayList<Long>();
			 JSONArray jArray = jObj.getJSONArray("electionId");
			 for(int i = 0 ; i < jArray.length() ; i++)
			 {
				 electionIds.add(Long.valueOf(jArray.getString(i)));
			 }
			// partyPositionVOList = suggestiveModelService.getPartyPerformenceReport(jObj.getLong("constituencyId"),jObj.getLong("partyId"),jObj.getLong("locationId"),jObj.getString("locationType"),electionIds,jObj.getString("tempVar"));
			 partyPositionVOList = suggestiveModelService.getPartyPerformenceReport1(jObj.getLong("constituencyId"),jObj.getLong("partyId"),electionIds,jObj.getString("tempVar"));
		 }
		
			
		}catch (Exception e) {
		 e.printStackTrace();
		// LOG.error(" Exception Occured in getPartyPerformanceForSelectedLocation() method, Exception - "+e);
		}
		return Action.SUCCESS;
	}
	
	public String getConstituenciesList(){
		session = request.getSession();
		RegistrationVO regVO = (RegistrationVO)session.getAttribute(IConstants.USER);
		try {
			jObj = new JSONObject(getTask());
		} catch (Exception e) {
			e.printStackTrace();
		}
		Long electionYear = Long.valueOf(IConstants.PRESENT_ELECTION_YEAR);
		Long electionTypeId = Long.valueOf(IConstants.ASSEMBLY_ELECTION_TYPE_ID);
		userAccessConstituencyList = crossVotingEstimationService.getConstituenciesForElectionYearAndTypeWithUserAccess(regVO.getRegistrationID(),electionYear,electionTypeId);
		constituencies = suggestiveModelService.getConstituenciesForUserAccessByStateId(userAccessConstituencyList,electionTypeId,electionYear);
		constituencies.add(0, new SelectOptionVO(0L,"Select Constituency"));
		return Action.SUCCESS;
	}
	
	
	public String getPartyDetails(){
		try{
			jObj = new JSONObject(getTask());
		}catch(Exception e){
			e.printStackTrace();
		}
		//Long tehsilId= jObj.getLong("mandalId");
		//partyList = suggestiveModelService.getPartyDetailsByMandal(tehsilId);
		partyList = staticDataService.getStaticParties();
		return Action.SUCCESS;
	}
	public String getAgeWiseGroupReport(){
		session=request.getSession();
		RegistrationVO regVO=(RegistrationVO)session.getAttribute(IConstants.USER);
		
		Long userId=null;
		if(regVO.getParentUserId()!=null){
			userId=regVO.getMainAccountId();
		}else{
			userId=regVO.getRegistrationID();
		}
		try{
			jObj = new JSONObject(getTask());
		
		String task=jObj.getString("task");
		JSONArray agesArr = jObj.getJSONArray("agesList");
		Long constiId=jObj.getLong("constituencyId");
		Long loctnId=jObj.getLong("locationId");
		String loctnType=jObj.getString("locationType");
		//Long electionId=jObj.getLong("electionId");
		JSONArray elctionIds = jObj.getJSONArray("electionIds");
		JSONArray castesSelected=jObj.getJSONArray("castesSelcted");
		JSONArray jsonArray = jObj.getJSONArray("expCasteArray");
		JSONArray jsonArrayMncpl=jObj.getJSONArray("expCasteArrayForMuncipality");
		
		List<Long> electionIds = new ArrayList<Long>(); 
		if(elctionIds != null && elctionIds.length() >0){
			for(int i=0;i<elctionIds.length();i++){
				electionIds.add(Long.valueOf(elctionIds.getInt(i)));
			}
		}
		
		List<Long> casteIds=new ArrayList<Long>();
		if(castesSelected.length()!=0){
			for(int i=0;i<castesSelected.length();i++){
				casteIds.add(Long.valueOf(castesSelected.getInt(i)));
			}
		}
		
		
		List<SelectOptionVO> groups=new ArrayList<SelectOptionVO>();
		
		SelectOptionVO select;
		for (int i = 0; i < agesArr.length(); i++) {
			select=new SelectOptionVO();
			if(i%2==0){
				select.setId(Long.valueOf(agesArr.getInt(i)));
				select.setPopulateId(Long.valueOf(agesArr.getInt(i+1)));
				groups.add(select);
			}
			
		}
		
		List<ExceptCastsVO> exceptCasteList = new ArrayList<ExceptCastsVO>();
		for (int i = 0 ; i < jsonArray.length() ; i++) {
			JSONObject jSONObject= jsonArray.getJSONObject(i);
			ExceptCastsVO exceptCastsVO = new ExceptCastsVO();
			exceptCastsVO.setCasteId(jSONObject.getLong("casteId"));
			//exceptCastsVO.setCasteName(jSONObject.getString("casteName"));
			exceptCastsVO.setCastePerc(jSONObject.getDouble("expPerc"));
			exceptCastsVO.setPanchayatId(jSONObject.getLong("panchayatId"));
			//exceptCastsVO.setSelectLevelId(jSONObject.getLong("selLevel"));
			//exceptCastsVO.setSelectLevelvalue(jSONObject.getLong("levelValue"));
			exceptCasteList.add(exceptCastsVO);
		}
		
		List<ExceptCastsVO> exceptCasteMncplList = new ArrayList<ExceptCastsVO>();
		if(jsonArrayMncpl.length()>0){
		for (int i = 0 ; i < jsonArrayMncpl.length() ; i++) {
			JSONObject jSONObject= jsonArrayMncpl.getJSONObject(i);
			ExceptCastsVO exceptCastsVO = new ExceptCastsVO();
			exceptCastsVO.setCasteId(jSONObject.getLong("casteId"));
			//exceptCastsVO.setCasteName(jSONObject.getString("casteName"));
			exceptCastsVO.setCastePerc(jSONObject.getDouble("expPerc"));
			exceptCastsVO.setPanchayatId(jSONObject.getLong("mandalId"));
			//exceptCastsVO.setSelectLevelId(jSONObject.getLong("selLevel"));
			//exceptCastsVO.setSelectLevelvalue(jSONObject.getLong("levelValue"));
			exceptCasteMncplList.add(exceptCastsVO);
		}
		}
		
		panchayatVOs=suggestiveModelService.getVotersGroupDetails(groups,constiId,loctnId,loctnType,electionIds,userId,casteIds,exceptCasteList,exceptCasteMncplList);
		}catch(Exception e){
			e.printStackTrace();
		}
		try{
		for(PanchayatVO range:panchayatVOs){
			for(PanchayatVO booth:range.getBoothsList()){
				Collections.sort(booth.getAllSelectedCastes(),sourceSort);
			}
			for(PanchayatVO panchayat:range.getPanchayatList()){
				Collections.sort(panchayat.getAllSelectedCastes(),sourceSort);
			}
		}
		}catch(Exception e){
			
		}
		return Action.SUCCESS;
	}
	public static Comparator<CastVO> sourceSort = new Comparator<CastVO>()
			{
				  
			  public int compare(CastVO cstVO1, CastVO cstVO2)
				{
				   return (cstVO1.getCastStateId().intValue()) - (cstVO2.getCastStateId().intValue());
				}
		  };
	public String getLeadersData()
	{
		try{
			jObj = new JSONObject(getTask());
			session = request.getSession();
			RegistrationVO regVO = (RegistrationVO)session.getAttribute(IConstants.USER);
			if(regVO == null)
			{
				return Action.ERROR;
			}
			
			Long userId = null;
			if(regVO.getParentUserId()!=null)
			{
			  userId = regVO.getMainAccountId();
			}
			else
			{
			  userId = regVO.getRegistrationID();
			}
			//Long mandalId       = jObj.getLong("mandalId");
			Long constituencyId = jObj.getLong("constituencyId");
			String casteIds = jObj.getString("casteIds");
			Long publicationId = jObj.getLong("publiationId");
			String constituencyType = jObj.getString("constituencyType");
			String[] strArray = casteIds.split(",");
			JSONArray jsonArray1 = jObj.getJSONArray("expCasteArrayForMuncipality");
			List<ExceptCastsVO> exceptCasteListForMuncipal = new ArrayList<ExceptCastsVO>();
			for (int i = 0 ; i < jsonArray1.length() ; i++) {
				JSONObject jSONObject= jsonArray1.getJSONObject(i);
				ExceptCastsVO exceptCastsVO = new ExceptCastsVO();
				exceptCastsVO.setCasteId(jSONObject.getLong("casteId"));
				exceptCastsVO.setCastePerc(jSONObject.getDouble("expPerc"));
				exceptCastsVO.setPanchayatId(jSONObject.getLong("mandalId"));
				exceptCasteListForMuncipal.add(exceptCastsVO);
			}
			JSONArray jsonArray = jObj.getJSONArray("expCasteArray");
			List<Long> castesIdsList = new ArrayList<Long>();
			Boolean checkStatus = jObj.getBoolean("checkStatus");
			Map<Long,Double> casteMap = new HashMap<Long,Double>();
			List<ExceptCastsVO> exceptCasteList = new ArrayList<ExceptCastsVO>();
			for (int i = 0 ; i < jsonArray.length() ; i++) {
				JSONObject jSONObject= jsonArray.getJSONObject(i);
				ExceptCastsVO exceptCastsVO = new ExceptCastsVO();
				exceptCastsVO.setCasteId(jSONObject.getLong("casteId"));
				//exceptCastsVO.setCasteName(jSONObject.getString("casteName"));
				exceptCastsVO.setCastePerc(jSONObject.getDouble("expPerc"));
				exceptCastsVO.setPanchayatId(jSONObject.getLong("panchayatId"));
				//exceptCastsVO.setSelectLevelId(jSONObject.getLong("selLevel"));
				//exceptCastsVO.setSelectLevelvalue(jSONObject.getLong("levelValue"));
				exceptCasteList.add(exceptCastsVO);
			}
			
			for(String casteId:strArray)
				castesIdsList.add(Long.parseLong(casteId));
			
			if(constituencyType.equalsIgnoreCase(IConstants.URBAN))
			{
				leaderSelectionList = suggestiveModelService.getLeadersInUrbanConstituencyes(userId,constituencyId,castesIdsList,exceptCasteList,checkStatus,publicationId);
			}
			else
			{
				leaderSelectionList = suggestiveModelService.findingBoothInchargesForBoothLevel(userId,constituencyId,castesIdsList,casteMap,exceptCasteList,checkStatus,publicationId,exceptCasteListForMuncipal);
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return Action.SUCCESS;
	}
	public String getDeletedVotersInfoByPanchayats()
	{
		try{
			jObj = new JSONObject(getTask());
			List<Long> panchayatIds = new ArrayList<Long>(0);
			JSONArray arr = jObj.getJSONArray("panchayats");
			if(arr != null && arr.length() > 0)
				for(int i=0 ;i< arr.length() ; i++)
				{
				 panchayatIds.add(Long.valueOf(arr.get(i).toString()));
				}
			//deletedVoters = suggestiveModelService.getDeletedVoterInfo(panchayatIds);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return Action.SUCCESS;
	}

		public String getElectionYears(){
			if(task != null){
				try{
					jObj = new JSONObject(getTask());
					LOG.info("Result From JSON:"+jObj);
				}catch(Exception e){
					e.printStackTrace();
				}
				
				if(jObj.getString("task").equals("getElectionYears")){
					
					String cnstncyType=suggestiveModelService.getConstituencyType(jObj.getLong("constituencyId"));
					electionsYears = staticDataService.getElectionIdsAndYearsByElectionScopeId(jObj.getLong("electionScopeId"),jObj.getLong("partyId"),jObj.getLong("constituencyId"),cnstncyType);
					LOG.debug("getElectionScopes......"+electionsYears.size());
				}
			}
			
			return Action.SUCCESS;
		}

		public String getLeadersListInRuralUrbans()
		{
			try{
				jObj = new JSONObject(getTask());
				session = request.getSession();
				RegistrationVO regVO = (RegistrationVO)session.getAttribute(IConstants.USER);
				if(regVO == null)
				{
					return Action.ERROR;
				}
				
				Long userId = null;
				if(regVO.getParentUserId()!=null)
				{
				  userId = regVO.getMainAccountId();
				}
				else
				{
				  userId = regVO.getRegistrationID();
				}
				//Long mandalId       = jObj.getLong("mandalId");
				JSONArray jsonArray = jObj.getJSONArray("expCasteArrayForMuncipality");
				String casteIds = jObj.getString("casteIds");
				Long constituencyId = jObj.getLong("constituencyId");
				String[] strArray = casteIds.split(",");
				List<Long> castesIdsList = new ArrayList<Long>();
				Boolean checkStatus = jObj.getBoolean("checkStatus");
				List<ExceptCastsVO> exceptCasteList = new ArrayList<ExceptCastsVO>();
				for (int i = 0 ; i < jsonArray.length() ; i++) {
					JSONObject jSONObject= jsonArray.getJSONObject(i);
					ExceptCastsVO exceptCastsVO = new ExceptCastsVO();
					exceptCastsVO.setCasteId(jSONObject.getLong("casteId"));
					exceptCastsVO.setCastePerc(jSONObject.getDouble("expPerc"));
					exceptCastsVO.setPanchayatId(jSONObject.getLong("mandalId"));
					exceptCasteList.add(exceptCastsVO);
				}
				Long publicationId = jObj.getLong("publicationId");
				for(String casteId:strArray)
					castesIdsList.add(Long.parseLong(casteId));

				leaderSelectionLists = suggestiveModelService.findingBoothInchargesForBoothLevelForMincipality(userId,constituencyId,castesIdsList,exceptCasteList,checkStatus,publicationId);
			}catch(Exception e){
				e.printStackTrace();
			}
			
			return Action.SUCCESS;
		}
		
		public String getUserAssignedVotersCastes(){
			session = request.getSession();
			RegistrationVO regVO = (RegistrationVO)session.getAttribute("USER");
			if(task != null){				
				try{
					jObj = new JSONObject(getTask());
				}catch(Exception e){}
				Long userID = regVO.getRegistrationID();
				if(regVO.getMainAccountId() != null){
					userID = regVO.getMainAccountId();
				}
				Long publicationId = null;
				try{
				   publicationId = jObj.getLong("publicationId");
				}catch(Exception e){
					
				}
				if(jObj.getString("task").equals("getUserAssignedVoterCastes")){
					castesList = suggestiveModelService.getUserAssignedVotersCasteDetailsByConstId(jObj.getLong("constituencyId"),userID,publicationId);
				}
			}
			
			return Action.SUCCESS;
			
		}
		
		public String getEffectOfNewPartyOnTraditionalParties()
		{
			try{
				jObj = new JSONObject(getTask());
			}catch(Exception e){
				e.printStackTrace();
			}
			
			//newPartyImpactDetails = constituencyPageService.findPanchayatsWiseResultsInElectionsOfMandalForSuggestiveModel(jObj.getLong("constituencyId"));
			
			resultMap = suggestiveModelService.getElectionResultsForSelectedElectionsForAConsttituency(jObj.getLong("constituencyId"),jObj.getString("partyName"),jObj.getLong("publicationId"));
			
			return Action.SUCCESS;
			
		}
		
		public String getCasteDetails(){
			try{
				jObj = new JSONObject(getTask());
				session = request.getSession();
				RegistrationVO regVO = (RegistrationVO)session.getAttribute(IConstants.USER);
				if(regVO == null)
				{
					return Action.ERROR;
				}
				
				Long userId = null;
				if(regVO.getParentUserId()!=null)
				{
				  userId = regVO.getMainAccountId();
				}
				else
				{
				  userId = regVO.getRegistrationID();
				}
				List<Long> candidateCastes = new ArrayList<Long>(0);
			Long publicationId= jObj.getLong("publicationId");
			JSONArray arr = jObj.getJSONArray("candidateCastes");
			if(arr != null && arr.length() > 0)
				for(int i=0 ;i< arr.length() ; i++)
				{
					candidateCastes.add(Long.valueOf(arr.get(i).toString()));
				}
			
			//Long candidateCastes= jObj.getLong("candidateCastes");
			Long constituencyId = jObj.getLong("constituencyId");
			hamletDetails = suggestiveModelService.getHamletDetailsByPanchayatIds(constituencyId,publicationId,userId,candidateCastes);
			}catch(Exception e){
				e.printStackTrace();
			}
			return Action.SUCCESS;
		}
		
		public String getCasteAvaliableConstituencys()
		{
			session = request.getSession();
			RegistrationVO regVO = (RegistrationVO)session.getAttribute(IConstants.USER);
			if(regVO == null)
			{
				return Action.ERROR;
			}
			Long userId         = null;
			if(regVO.getParentUserId()!=null)
			{
			  userId = regVO.getMainAccountId();
			}
			else
			{
			  userId = regVO.getRegistrationID();
			}
			Long electionYear = Long.valueOf(IConstants.PRESENT_ELECTION_YEAR);
			Long electionTypeId = Long.valueOf(IConstants.ASSEMBLY_ELECTION_TYPE_ID);
			userAccessConstituencyList = crossVotingEstimationService.getConstituenciesForElectionYearAndTypeWithUserAccess(regVO.getRegistrationID(),electionYear,electionTypeId);
			constituencies = suggestiveModelService.getCasteAvaliableConstituencysService(userAccessConstituencyList,electionTypeId,electionYear,userId);
			Collections.sort(constituencies);
			return Action.SUCCESS;
		}
		public String getMandalsAndPanchayts()
		{
			try {
				jObj = new JSONObject(getTask());
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			session = request.getSession();
			RegistrationVO regVO = (RegistrationVO)session.getAttribute(IConstants.USER);
			if(regVO == null)
			{
				return Action.ERROR;
			}
			Long constituencyId = jObj.getLong("constituencyId");
			
			mandalPanchayatList = suggestiveModelService.getMandalsAndPanchayats(constituencyId);
			
			return Action.SUCCESS;
		}
		public String getPollingPercentagesByParty()
		{
			
			try{
				jObj = new JSONObject(getTask());
				Long partyId = jObj.getLong("partyId");
				Long eleId = jObj.getLong("eleId");
				Long constituenycId = jObj.getLong("constituencyId");
				String type = jObj.getString("type");
				String path = IWebConstants.STATIC_CONTENT_FOLDER_URL;
				partyPositionVOList = suggestiveModelService.getPollingPercentagesByParty(constituenycId,partyId,eleId,jObj.getLong("eleId1"),path,type);
				
			}
			catch(Exception e)
			{
			e.printStackTrace();	
			}
			return Action.SUCCESS;
		}
		
		public String getAddedVoterDetailsForBooth()
		{
			
			try{
				jObj = new JSONObject(getTask());
				Long constituencyId = jObj.getLong("constituencyId");
				Long partno = jObj.getLong("partno");
				deletedVoters = suggestiveModelService.getAddedVotersDetailsByPartNo(constituencyId,partno,jObj.getInt("startIndex"),jObj.getInt("results"));
				
			}
			catch(Exception e)
			{
			e.printStackTrace();	
			}
			return Action.SUCCESS;
		}
		
		public String getPartipatedPartyDetailsInAConstituency(){
			try{
				jObj = new JSONObject(getTask());
			}catch(Exception e){
				e.printStackTrace();
			}
			Long constituencyId= jObj.getLong("mandalId");
			//partyList = suggestiveModelService.getPartyDetailsByMandal(tehsilId);
			//paricipatedPartyList = staticDataService.getElectionYearsAndPartiesForSelectedConstituency(constituencyId);
			paricipatedPartyList = staticDataService.getElectionYearsAndPartiesForSelectedConstituencyInSuggestive(constituencyId);
			return Action.SUCCESS;
		}
		
		public String getVoterscountInPanchayats()
		{
			try{
				LOG.debug("Entered into getVoterscountInPanchayats() method in Suggestive Model Action");
				jObj = new JSONObject(getTask());
				Long constituencyId = jObj.getLong("constituencyId");
				Long publicationId = jObj.getLong("publicationId");
				voterCountVOList = suggestiveModelService.getVotersCountInPanchayats(constituencyId,publicationId);
			}catch(Exception e){
				LOG.error("Exception raised in getVoterscountInPanchayats() method in Suggestive Model Action",e);
			}
			return Action.SUCCESS;
		}
		
		public String getSelectedCountPAnchayatsDetails()
		{
			try{
				LOG.debug("Entered into getSelectedCountPAnchayatsDetails() method in Suggestive Model Action");
				jObj = new JSONObject(getTask());
				Long constituencyId = jObj.getLong("constituencyId");
				//Long publicationId  = jObj.getLong("publicatinId");
				Long minValue       = jObj.getLong("minValue");
				Long maxValue       = jObj.getLong("maxValue");
				pachayatsList = suggestiveModelService.getSelectedCountPAnchayatsDetails(constituencyId,minValue,maxValue);
			}catch(Exception e){
				LOG.error("Exception raised in getSelectedCountPAnchayatsDetails() method in Suggestive Model Action",e);
			}
			return Action.SUCCESS;
		}
		
		public String getDelimationEffect()
		{
			try{
				LOG.debug("Entered into getDelimationEffect() method in Suggestive Model Action");
				jObj = new JSONObject(getTask());
				delimitationEffectVO = suggestiveModelService.getDelimationEffectOnConstituency(jObj.getLong("constituencyId"),jObj.getLong("partyId"));
			}catch(Exception e){
				LOG.error("Exception raised in getDelimationEffect() method in Suggestive Model Action",e);
			}
			return Action.SUCCESS;
		}
		
		public String getVoterAgeGroupResults()
		{
			try{
				LOG.debug("Entered into getVoterAgeGroupResults() method in Suggestive Model Action");
				jObj = new JSONObject(getTask());
				Long constituencyId = jObj.getLong("constituencyId");
				Long publicationDateId = jObj.getLong("publicationDateId");
				
				votersInfoForMandalVO = suggestiveModelService.getVotersCount(constituencyId,publicationDateId,"constituency");
			}catch(Exception e){
				LOG.error("Exception raised in getVoterAgeGroupResults() method in Suggestive Model Action",e);
			}
			return Action.SUCCESS;
		}
		
		public String getCensusDetailsForAConstituency()
		{
			try
			{
				try{
					jObj = new JSONObject(getTask());
				}catch(Exception e){
					e.printStackTrace();
				}
				
				constituencyCensusResultVO = electionService.getCensusDetailsForAConstituency(jObj.getLong("constituencyId"));
			
			}catch(Exception e)
			{
				e.printStackTrace();
			}
			  return Action.SUCCESS;
		}
		
		public String getLatestPublicationByConstituency()
		{
			try{
				jObj = new JSONObject(getTask());
				latestPublicationId = suggestiveModelService.getLatestPublicationByConstituency(jObj.getLong("constituencyId"));
			}
			catch (Exception e) {
				e.printStackTrace();
			}
			 return Action.SUCCESS;
		}
		
		public String getFamilyDetailsForConstituency()
		{
			try{
				jObj = new JSONObject(getTask());
				String path = IWebConstants.STATIC_CONTENT_FOLDER_URL ;
				session = request.getSession();
				RegistrationVO regVo = (RegistrationVO) session.getAttribute(IConstants.USER);
				if(regVo == null)
					return Action.ERROR;
				Long userId = regVo.getRegistrationID();
				familyInfo = suggestiveModelService.getFamilyDetailsForConstituency(jObj.getLong("constituencyId"),jObj.getLong("publicationId"),jObj.getLong("minVal"),jObj.getLong("maxVal"),jObj.getInt("startIndex"),jObj.getInt("results"),userId,path,jObj.getString("type"));
				
				if(jObj.getString("type") != null && jObj.getString("type").trim().equalsIgnoreCase("district"))
					familyInfo = new ArrayList<VoterHouseInfoVO>();
				/*if(jObj.getString("output") != null && jObj.getString("output").trim().equalsIgnoreCase("Excel"))
					familyInfo = new ArrayList<VoterHouseInfoVO>();*/
			}
			catch (Exception e) {
				e.printStackTrace();
			}
			 return Action.SUCCESS;	
		}
		
		public String getReport(){
			try{
				jObj = new JSONObject(getTask());
				String ids = jObj.getString("constituencyIds");
				String[] constiIds = ids.split(",");
				List<Long> constituencyIds = new ArrayList<Long>();
				for(String id:constiIds){
					constituencyIds.add(Long.valueOf(id));
				}
				//familyInfo = suggestiveModelService.calculateOrderOfPriorityForConstituency(1l,constituencyIds,null,null,null,null,null,null,null,null);
			}
			catch (Exception e) {
				e.printStackTrace();
			}
			 return Action.SUCCESS;	
		}
		
		public String getFamilyDetailsForPanchayat()
		{
			try{
				jObj = new JSONObject(getTask());
				String path = IWebConstants.STATIC_CONTENT_FOLDER_URL ;
				session = request.getSession();
				RegistrationVO regVo = (RegistrationVO) session.getAttribute(IConstants.USER);
				if(regVo == null)
					return Action.ERROR;
				Long userId = regVo.getRegistrationID();
				VoterVO votervo = new VoterVO();
				votervo.setConstituencyId(jObj.getLong("constituencyId"));
				votervo.setType(jObj.getString("type").toString().trim());
				votervo.setYear(jObj.getLong("year"));
				votervo.setStartIndex(jObj.getInt("startIndex"));
				votervo.setMaxIndex(jObj.getInt("results"));
				familyInfo = suggestiveModelService.getFamilyDetailsForPanchayat(votervo,userId,path);
			}
			catch (Exception e) {
				e.printStackTrace();
			}
			 return Action.SUCCESS;	
		}
		public String getCriticalPanchayatConstituencies()
		{
			try{
				constituencies = suggestiveModelService.getCriticalPanchayatConstituencies();
			}
			catch (Exception e) {
				e.printStackTrace();
			}
			 return Action.SUCCESS;	
		}
}
