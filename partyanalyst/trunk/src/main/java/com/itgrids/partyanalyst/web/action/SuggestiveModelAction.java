package com.itgrids.partyanalyst.web.action;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.jfree.util.Log;
import org.json.JSONArray;
import org.json.JSONObject;

import com.itgrids.partyanalyst.dto.OptionVO;
import com.itgrids.partyanalyst.dto.PanchayatVO;
import com.itgrids.partyanalyst.dto.PartyPositionVO;
import com.itgrids.partyanalyst.dto.RegistrationVO;
import com.itgrids.partyanalyst.dto.SelectOptionVO;
import com.itgrids.partyanalyst.dto.YouthLeaderSelectionVO;
import com.itgrids.partyanalyst.excel.booth.VoterVO;
import com.itgrids.partyanalyst.service.ICrossVotingEstimationService;
import com.itgrids.partyanalyst.service.IStaticDataService;
import com.itgrids.partyanalyst.service.ISuggestiveModelService;
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
	private List<YouthLeaderSelectionVO> LeaderSelectionList;
	private ICrossVotingEstimationService crossVotingEstimationService;
	private List userAccessConstituencyList;
	private List<VoterVO> deletedVoters;
	private List<SelectOptionVO> castesList;
	private List<YouthLeaderSelectionVO> LeaderSelectionLists;
	private static final Logger log = Logger.getLogger(SuggestiveModelAction.class);
	
	public List<YouthLeaderSelectionVO> getLeaderSelectionLists() {
		return LeaderSelectionLists;
	}

	public void setLeaderSelectionLists(
			List<YouthLeaderSelectionVO> leaderSelectionLists) {
		LeaderSelectionLists = leaderSelectionLists;
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
		return LeaderSelectionList;
	}

	public void setLeaderSelectionList(
			List<YouthLeaderSelectionVO> leaderSelectionList) {
		LeaderSelectionList = leaderSelectionList;
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


	public String execute(){
		
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
			 partyPositionVOList = suggestiveModelService.getPartyPerformenceReport(jObj.getLong("constituencyId"),jObj.getLong("partyId"),jObj.getLong("locationId"),jObj.getString("locationType"),electionIds,jObj.getString("tempVar")); 
		 }
		
			
		}catch (Exception e) {
		 e.printStackTrace();
		 Log.error(" Exception Occured in getPartyPerformanceForSelectedLocation() method, Exception - "+e);
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
		Long electionYear = new Long(IConstants.PRESENT_ELECTION_YEAR);
		Long electionTypeId = new Long(IConstants.ASSEMBLY_ELECTION_TYPE_ID);
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
		try{
			jObj = new JSONObject(getTask());
		}catch(Exception e){
			e.printStackTrace();
		}
		String task=jObj.getString("task");
		JSONArray agesArr = jObj.getJSONArray("agesList");
		Long constiId=jObj.getLong("constituencyId");
		Long loctnId=jObj.getLong("locationId");
		String loctnType=jObj.getString("locationType");
		Long electionId=jObj.getLong("electionId");
		
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
		
		panchayatVOs=suggestiveModelService.getVotersGroupDetails(groups,constiId,loctnId,loctnType,electionId,regVO.getRegistrationID());
		
		return Action.SUCCESS;
	}
	
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
			Long userId         = regVO.getRegistrationID();
			//Long mandalId       = jObj.getLong("mandalId");
			Long constituencyId = jObj.getLong("constituencyId");
			LeaderSelectionList = suggestiveModelService.findingBoothInchargesForBoothLevel(userId,constituencyId);
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
				 panchayatIds.add(new Long(arr.get(i).toString()));
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
					System.out.println("Result From JSON:"+jObj);
				}catch(Exception e){
					e.printStackTrace();
				}
				
				if(jObj.getString("task").equals("getElectionYears")){
					electionsYears = staticDataService.getElectionIdsAndYearsByElectionScopeId(jObj.getLong("electionScopeId"),jObj.getLong("partyId"),jObj.getLong("constituencyId"));
					log.debug("getElectionScopes......"+electionsYears.size());
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
				Long userId         = regVO.getRegistrationID();
				//Long mandalId       = jObj.getLong("mandalId");
				Long constituencyId = jObj.getLong("constituencyId");
				LeaderSelectionLists = suggestiveModelService.findingBoothInchargesForBoothLevelForMincipality(userId,constituencyId);
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
				
				if(jObj.getString("task").equals("getUserAssignedVoterCastes")){
					castesList = suggestiveModelService.getUserAssignedVotersCasteDetailsByConstId(jObj.getLong("constituencyId"),regVO.getRegistrationID());
				}
			}
			
			return Action.SUCCESS;
			
		}
}
