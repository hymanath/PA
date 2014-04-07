package com.itgrids.partyanalyst.web.action;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.json.JSONObject;

import com.itgrids.partyanalyst.dto.RegistrationVO;
import com.itgrids.partyanalyst.dto.ResultStatus;
import com.itgrids.partyanalyst.dto.SelectOptionVO;
import com.itgrids.partyanalyst.service.ICrossVotingEstimationService;
import com.itgrids.partyanalyst.service.IStaticDataService;
import com.itgrids.partyanalyst.service.IVotersAnalysisService;
import com.itgrids.partyanalyst.utils.IConstants;
import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionSupport;

public class VoterDataManageAction extends ActionSupport implements ServletRequestAware{

	private static final long serialVersionUID = 64316195191123277L;
	private static final Logger log = Logger.getLogger(VoterDataManageAction.class);
	private HttpServletRequest request;
	private Long constituencyId;
	private Long publicationDateId;
	private Integer startIndex;
	private Integer maxResults;
	private IVotersAnalysisService votersAnalysisService;
	private List<SelectOptionVO> constituenciesList,publicationDateList;
	private List<SelectOptionVO> districts;
	public List<SelectOptionVO> getDistricts() {
		return districts;
	}

	public void setDistricts(List<SelectOptionVO> districts) {
		this.districts = districts;
	}

	private String task;
	JSONObject jObj;
	private ResultStatus resultStatus;
	private HttpSession session;
	private List<SelectOptionVO> constituenciesListForVoterChanges;
	private List<SelectOptionVO> allConstituenciesList = new ArrayList<SelectOptionVO>(0);
	private List<SelectOptionVO> constituencyList;
	private ICrossVotingEstimationService crossVotingEstimationService;
	private Long districtId;

	public Long getDistrictId() {
		return districtId;
	}

	public void setDistrictId(Long districtId) {
		this.districtId = districtId;
	}

	private IStaticDataService staticDataService;
	
	public IStaticDataService getStaticDataService() {
		return staticDataService;
	}

	public void setStaticDataService(IStaticDataService staticDataService) {
		this.staticDataService = staticDataService;
	}

	public List<SelectOptionVO> getConstituenciesListForVoterChanges() {
		return constituenciesListForVoterChanges;
	}

	public void setConstituenciesListForVoterChanges(
			List<SelectOptionVO> constituenciesListForVoterChanges) {
		this.constituenciesListForVoterChanges = constituenciesListForVoterChanges;
	}

	public IVotersAnalysisService getVotersAnalysisService() {
		return votersAnalysisService;
	}

	public void setVotersAnalysisService(
			IVotersAnalysisService votersAnalysisService) {
		this.votersAnalysisService = votersAnalysisService;
	}

	public HttpServletRequest getRequest() {
		return request;
	}

	public void setRequest(HttpServletRequest request) {
		this.request = request;
	}

	public void setServletRequest(HttpServletRequest request) {
		this.request = request;
	}
	
	public Long getConstituencyId() {
		return constituencyId;
	}

	public void setConstituencyId(Long constituencyId) {
		this.constituencyId = constituencyId;
	}

	public Long getPublicationDateId() {
		return publicationDateId;
	}

	public void setPublicationDateId(Long publicationDateId) {
		this.publicationDateId = publicationDateId;
	}

	public Integer getStartIndex() {
		return startIndex;
	}

	public void setStartIndex(Integer startIndex) {
		this.startIndex = startIndex;
	}

	public Integer getMaxResults() {
		return maxResults;
	}

	public void setMaxResults(Integer maxResults) {
		this.maxResults = maxResults;
	}

	public List<SelectOptionVO> getConstituenciesList() {
		return constituenciesList;
	}

	public void setConstituenciesList(List<SelectOptionVO> constituenciesList) {
		this.constituenciesList = constituenciesList;
	}

	public List<SelectOptionVO> getPublicationDateList() {
		return publicationDateList;
	}

	public void setPublicationDateList(List<SelectOptionVO> publicationDateList) {
		this.publicationDateList = publicationDateList;
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

	public ResultStatus getResultStatus() {
		return resultStatus;
	}

	public void setResultStatus(ResultStatus resultStatus) {
		this.resultStatus = resultStatus;
	}

	public HttpSession getSession() {
		return session;
	}

	public void setSession(HttpSession session) {
		this.session = session;
	}

	public List<SelectOptionVO> getAllConstituenciesList() {
		return allConstituenciesList;
	}

	public void setAllConstituenciesList(List<SelectOptionVO> allConstituenciesList) {
		this.allConstituenciesList = allConstituenciesList;
	}

	public List<SelectOptionVO> getConstituencyList() {
		return constituencyList;
	}

	public void setConstituencyList(List<SelectOptionVO> constituencyList) {
		this.constituencyList = constituencyList;
	}

	public ICrossVotingEstimationService getCrossVotingEstimationService() {
		return crossVotingEstimationService;
	}

	public void setCrossVotingEstimationService(
			ICrossVotingEstimationService crossVotingEstimationService) {
		this.crossVotingEstimationService = crossVotingEstimationService;
	}

	public String execute()
	{
		session = request.getSession();
	
		RegistrationVO user = (RegistrationVO) session.getAttribute("USER");
		
		if(user == null || ((RegistrationVO)user).getRegistrationID() == null){
			log.error(" No User Log In .....");			
			return "error";
		}
		
		constituenciesList = votersAnalysisService.getConstituenciesList();
		
		publicationDateList = votersAnalysisService.getAllPublicationDates();
		
		districts = staticDataService.getDistricts(1l);
		
		constituenciesListForVoterChanges = votersAnalysisService.getConstituenciesToBeMappedForVoterChanges();
		
		
		allConstituenciesList = user.getUserAccessVoterConstituencies();
		if(constituencyList == null || constituencyList.isEmpty()){
			Long userID = user.getRegistrationID();
			Long electionYear = new Long(IConstants.PRESENT_ELECTION_YEAR);
			Long electionTypeId = new Long(IConstants.ASSEMBLY_ELECTION_TYPE_ID);
			allConstituenciesList = crossVotingEstimationService.getConstituenciesForElectionYearAndTypeWithUserAccess(userID,electionYear,electionTypeId);
		}
		constituencyList = staticDataService.getConstituencies(1l);
		
		constituencyList.add(0,new SelectOptionVO(0L,"Select Constituency"));
			
		
		return ActionSupport.SUCCESS;
	}
	
	public String AjaxHandler()
	{
		try {
			jObj = new JSONObject(getTask());
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		if(jObj.getString("task").equalsIgnoreCase("insertVoterData"))
		{
			/*if(jObj.getBoolean("isUpdateSno"))
				resultStatus = votersAnalysisService.updateSerialNo(jObj.getLong("constituencyId"),null,null,null);
			else*/
				resultStatus = votersAnalysisService.insertVoterData(jObj.getLong("constituencyId"),jObj.getLong("publicationDateId"),jObj.getInt("startIndex"),jObj.getInt("maxResults"));
		}
		else if(jObj.getString("task").equalsIgnoreCase("insertVoterModificationData"))
		{
			resultStatus = votersAnalysisService.moveVotersModificationDataFromTempToMainTable(jObj.getLong("constituencyId"), jObj.getLong("publicationDateId"));
		}
		else if(jObj.getString("task").equalsIgnoreCase("InsertmapVoterData"))
		{
			resultStatus = votersAnalysisService.mapVoterDataFromOnePublicationToAnotherPublication(jObj.getLong("constituencyId"),jObj.getLong("frompublicationDateId"),jObj.getLong("topublicationDateId"),jObj.getBoolean("boothCreateflag"));
		}
		
		else if(jObj.getString("task").equalsIgnoreCase("getVoterDataForDis"))
		{
			  
			 try{
				 publicationDateList = new ArrayList<SelectOptionVO>();
				 
				
			
		   List<SelectOptionVO> constituencies = staticDataService.getConstituenciesFordistricts(jObj.getLong("DistrictId"));
			 
				
			  if(constituencies != null && constituencies.size() > 0)
			  {
			  for(SelectOptionVO vo :constituencies )
			  {
					 
					Long toList =votersAnalysisService.getVoterCountForToPublication(vo.getId(),jObj.getLong("topublicationDateIdForDis"));
					
					Long fromList =votersAnalysisService.getVoterCountForToPublication(vo.getId(),jObj.getLong("frompublicationDateIdForDis"));
					
					
			    resultStatus = votersAnalysisService.mapVoterDataFromOnePublicationToAnotherPublication(vo.getId(),jObj.getLong("frompublicationDateIdForDis"),jObj.getLong("topublicationDateIdForDis"),jObj.getBoolean("boothCreateflag"));
			    SelectOptionVO vo1 = new SelectOptionVO();
				  vo1.setId(Long.valueOf(resultStatus.getResultCode()));
				  vo1.setValidCount(fromList);
				  vo1.setTotalCount(toList);
				  vo1.setName(vo.getName().toString());
				  publicationDateList.add(vo1);
				
			  }
			  }
			 
			 
			 }catch(Exception e)
			 {
			  e.printStackTrace(); 
			 }
		}
		
		
		return Action.SUCCESS;
	}
	public String insertTeluguVoterdata()
	{
		try{
			jObj = new JSONObject(getTask());
			resultStatus = votersAnalysisService.insertVoterDataIntoVoterNamesTemp(jObj.getLong("constituencyId"));
		}
		catch (Exception e) {
			 e.printStackTrace(); 
		}
		return Action.SUCCESS;
	}
	
	public String getCriticalPanchayatConstituencies()
	{
		try{
			constituenciesList = votersAnalysisService.getVoterNamestempConstituencies();
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		 return Action.SUCCESS;	
	}
}
