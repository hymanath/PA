package com.itgrids.partyanalyst.web.action;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.struts2.interceptor.ServletRequestAware;
import org.jfree.util.Log;
import org.json.JSONObject;

import com.itgrids.partyanalyst.dto.RegistrationVO;
import com.itgrids.partyanalyst.dto.ResultStatus;
import com.itgrids.partyanalyst.dto.SelectOptionVO;
import com.itgrids.partyanalyst.dto.VoterDataVO;
import com.itgrids.partyanalyst.dto.VoterHouseInfoVO;
import com.itgrids.partyanalyst.excel.booth.VoterVO;
import com.itgrids.partyanalyst.service.ICrossVotingEstimationService;
import com.itgrids.partyanalyst.service.IStaticDataService;
import com.itgrids.partyanalyst.service.IVotersAnalysisService;
import com.itgrids.partyanalyst.service.impl.RegionServiceDataImp;
import com.itgrids.partyanalyst.utils.IConstants;
import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.opensymphony.xwork2.Preparable;

public class VotersEditAction  extends ActionSupport implements ServletRequestAware,Preparable,ModelDriven<VoterHouseInfoVO>{
	/**
	 * 
	 */
	private static final long serialVersionUID = -8900996662457488196L;
	private IVotersAnalysisService votersAnalysisService;
	JSONObject jObj = null;
	private String task = null;
	private HttpServletRequest request;
	private HttpSession session;
	private VoterHouseInfoVO voterHouseInfoVO;
	private VoterHouseInfoVO voterHouseInfoVO1;
	private Long voterId;
	private ResultStatus result;
	private IStaticDataService staticDataService;
	private List<SelectOptionVO> partyGroupList, castCategoryGroupList,userAccessStates;
	
	private List<VoterHouseInfoVO> userCategorysList;
	private String resultStr;
	private List<SelectOptionVO> voterCategoryValues;
	private SelectOptionVO selectOptionVO,casteVO;
	private String save;
	private Long userId;
	private VoterVO voterVO;
	//private String windowTask = null;
	private ResultStatus resultStatus;
	private List<VoterHouseInfoVO> votersFamilyInfo;
	
	private boolean status;
	
	private ICrossVotingEstimationService crossVotingEstimationService;
	
	private List<SelectOptionVO> constituencyList, userAccessConstituencyList;
	
	private RegionServiceDataImp regionServiceDataImp;
	
	private List<SelectOptionVO> hamlets;
	
	private List<VoterVO> votersData , voterDetails;
	
	public List<SelectOptionVO> getHamlets() {
		return hamlets;
	}

	public void setHamlets(List<SelectOptionVO> hamlets) {
		this.hamlets = hamlets;
	}

	public RegionServiceDataImp getRegionServiceDataImp() {
		return regionServiceDataImp;
	}

	public void setRegionServiceDataImp(RegionServiceDataImp regionServiceDataImp) {
		this.regionServiceDataImp = regionServiceDataImp;
	}

	public ICrossVotingEstimationService getCrossVotingEstimationService() {
		return crossVotingEstimationService;
	}

	public void setCrossVotingEstimationService(
			ICrossVotingEstimationService crossVotingEstimationService) {
		this.crossVotingEstimationService = crossVotingEstimationService;
	}

	public List<SelectOptionVO> getUserAccessConstituencyList() {
		return userAccessConstituencyList;
	}

	public void setUserAccessConstituencyList(
			List<SelectOptionVO> userAccessConstituencyList) {
		this.userAccessConstituencyList = userAccessConstituencyList;
	}

	public List<SelectOptionVO> getPartyGroupList() {
		return partyGroupList;
	}

	public List<SelectOptionVO> getVoterCategoryValues() {
		return voterCategoryValues;
	}

	public void setVoterCategoryValues(List<SelectOptionVO> voterCategoryValues) {
		this.voterCategoryValues = voterCategoryValues;
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

	public List<VoterHouseInfoVO> getUserCategorysList() {
		return userCategorysList;
	}

	public void setUserCategorysList(List<VoterHouseInfoVO> userCategorysList) {
		this.userCategorysList = userCategorysList;
	}

	public VoterHouseInfoVO getVoterHouseInfoVO1() {
		return voterHouseInfoVO1;
	}

	public void setVoterHouseInfoVO1(VoterHouseInfoVO voterHouseInfoVO1) {
		this.voterHouseInfoVO1 = voterHouseInfoVO1;
	}

	public String getResultStr() {
		return resultStr;
	}

	public void setResultStr(String resultStr) {
		this.resultStr = resultStr;
	}

	public void setPartyGroupList(List<SelectOptionVO> partyGroupList) {
		this.partyGroupList = partyGroupList;
	}

	public IStaticDataService getStaticDataService() {
		return staticDataService;
	}

	public void setStaticDataService(IStaticDataService staticDataService) {
		this.staticDataService = staticDataService;
	}

	public ResultStatus getResult() {
		return result;
	}

	public void setResult(ResultStatus result) {
		this.result = result;
	}

	public Long getVoterId() {
		return voterId;
	}

	public void setVoterId(Long voterId) {
		this.voterId = voterId;
	}

	public HttpServletRequest getRequest() {
		return request;
	}

	public void setRequest(HttpServletRequest request) {
		this.request = request;
	}

	public HttpSession getSession() {
		return session;
	}

	public void setSession(HttpSession session) {
		this.session = session;
	}

	
	
	public VoterHouseInfoVO getVoterHouseInfoVO() {
		return voterHouseInfoVO;
	}

	public void setVoterHouseInfoVO(VoterHouseInfoVO voterHouseInfoVO) {
		this.voterHouseInfoVO = voterHouseInfoVO;
	}

	public IVotersAnalysisService getVotersAnalysisService() {
		return votersAnalysisService;
	}

	public void setVotersAnalysisService(
			IVotersAnalysisService votersAnalysisService) {
		this.votersAnalysisService = votersAnalysisService;
	}

	public void setServletRequest(HttpServletRequest arg0) {
		
		this.request=arg0;
	}
	
	
	public SelectOptionVO getSelectOptionVO() {
		return selectOptionVO;
	}

	public void setSelectOptionVO(SelectOptionVO selectOptionVO) {
		this.selectOptionVO = selectOptionVO;
	}

	public String getSave() {
		return save;
	}

	public void setSave(String save) {
		this.save = save;
	}
	
	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}
	
	public SelectOptionVO getCasteVO() {
		return casteVO;
	}

	public void setCasteVO(SelectOptionVO casteVO) {
		this.casteVO = casteVO;
	}

	public List<SelectOptionVO> getCastCategoryGroupList() {
		return castCategoryGroupList;
	}

	public void setCastCategoryGroupList(List<SelectOptionVO> castCategoryGroupList) {
		this.castCategoryGroupList = castCategoryGroupList;
	}

	public List<SelectOptionVO> getUserAccessStates() {
		return userAccessStates;
	}

	public void setUserAccessStates(List<SelectOptionVO> userAccessStates) {
		this.userAccessStates = userAccessStates;
	}

	public ResultStatus getResultStatus() {
		return resultStatus;
	}

	public void setResultStatus(ResultStatus resultStatus) {
		this.resultStatus = resultStatus;
	}

	public List<VoterHouseInfoVO> getVotersFamilyInfo() {
		return votersFamilyInfo;
	}

	public void setVotersFamilyInfo(List<VoterHouseInfoVO> votersFamilyInfo) {
		this.votersFamilyInfo = votersFamilyInfo;
	}

	public boolean isStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}
	public List<SelectOptionVO> getConstituencyList() {
		return constituencyList;
	}

	public void setConstituencyList(List<SelectOptionVO> constituencyList) {
		this.constituencyList = constituencyList;
	}
	
	
	

	public List<VoterVO> getVotersData() {
		return votersData;
	}

	public void setVotersData(List<VoterVO> votersData) {
		this.votersData = votersData;
	}

	
	public List<VoterVO> getVoterDetails() {
		return voterDetails;
	}

	public void setVoterDetails(List<VoterVO> voterDetails) {
		this.voterDetails = voterDetails;
	}

	
	public VoterVO getVoterVO() {
		return voterVO;
	}

	public void setVoterVO(VoterVO voterVO) {
		this.voterVO = voterVO;
	}

	public String execute() throws Exception{
		
		
		session = request.getSession();
		RegistrationVO user = (RegistrationVO)session.getAttribute("USER");
		if(user == null)
			return "error";
		
		userId = user.getRegistrationID();
		
				
		castCategoryGroupList = votersAnalysisService.getcastCategoryGroups();
		userAccessStates = staticDataService.getUserAccessStates(userId);
		
		
		Long electionYear = new Long(IConstants.PRESENT_ELECTION_YEAR);
		Long electionTypeId = new Long(IConstants.ASSEMBLY_ELECTION_TYPE_ID);
		userAccessConstituencyList = crossVotingEstimationService.getConstituenciesForElectionYearAndTypeWithUserAccess(userId,electionYear,electionTypeId);
		constituencyList = votersAnalysisService.getConstituencyList(userAccessConstituencyList);
		constituencyList.add(0, new SelectOptionVO(0L,"Select Constituency"));
		//session.setAttribute(ISessionConstants.WINDOW_TASK,windowTask);
/*		HttpSession session = request.getSession();
		session = request.getSession();
		RegistrationVO user = (RegistrationVO) session.getAttribute("USER");
		if(user == null)
			return ERROR;
		try{
			if(session.getAttribute(IWebConstants.PARTY_ANALYST_USER_ROLE) !=null && session.getAttribute(IWebConstants.PARTY_ANALYST_USER_ROLE).equals(true))
			{
				String accessType =user.getAccessType();
				Long accessValue= new Long(user.getAccessValue());
				
			}*/
		
		return SUCCESS;
	}
	

public String saveVoterDetails(){
	
	HttpSession session = request.getSession();
	RegistrationVO user=(RegistrationVO) session.getAttribute("USER");
	Long userId = null;
	if(user != null && user.getRegistrationID() != null)
		if(user.getParentUserId()!=null)
			userId=user.getMainAccountId();
		else
			userId = user.getRegistrationID();
	else 
	  return "error";
	voterHouseInfoVO.setUserId(userId);
	String voterId = request.getParameter("voterId");
	String boothId = request.getParameter("boothId");
	if(voterId != null && voterId.trim().length() > 0)
	 voterHouseInfoVO.setVoterId(new Long(voterId));
	if(boothId != null && boothId.trim().length() > 0)
		 voterHouseInfoVO.setBoothId(new Long(boothId));
	 votersAnalysisService.updateVoterDetails(voterHouseInfoVO,"all");
	request.setAttribute("voterId", voterHouseInfoVO.getVoterId());
	resultStr = SUCCESS;
	request.setAttribute("resultStr", resultStr);
	return Action.SUCCESS;

}
	
	public void prepare() throws Exception {
   if(request.getParameter("save") == null){
	String voterId = request.getParameter("voterId");
	String boothId = request.getParameter("boothId");
	HttpSession session = request.getSession();
	RegistrationVO user=(RegistrationVO) session.getAttribute("USER");
	Long userId = user.getRegistrationID();
	//VoterHouseInfoVO voterHouseInfoVO=new VoterHouseInfoVO();
	
	//List<SelectOptionVO> partyGroupList=null;
	if(voterId !=null)
	{
		voterHouseInfoVO1=votersAnalysisService.getBoothDetailsForVoter(new Long(boothId));
		voterHouseInfoVO= votersAnalysisService.getVoterPersonalDetailsByVoterId(new Long(voterId),userId);
		
		voterHouseInfoVO.setBoothName(voterHouseInfoVO1.getBoothName());
		voterHouseInfoVO.setVilliageCovered(voterHouseInfoVO1.getVilliageCovered());
		voterHouseInfoVO.setPanchayatName(voterHouseInfoVO1.getPanchayatName());
		
	}
   }
  }
	
	public VoterHouseInfoVO getModel() {
		// TODO Auto-generated method stub
			return getVoterHouseInfoVO();
	}
	
	public String callAjaxHandler(){
		String param = null;
		param = getTask();
		try {
			jObj = new JSONObject(param);
			System.out.println(jObj);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		if(jObj.getString("task").equalsIgnoreCase("getVoterCategories")){
			System.out.println("with in the action");
			Long voterCategoryId=new Long(jObj.getString("voterCategory"));
			String letters=jObj.getString("letters");
			voterCategoryValues = votersAnalysisService.getVoterCategoryValues(voterCategoryId,letters);
			System.out.println("voterCategoryValues value is:"+voterCategoryValues);
		}
		else if(jObj.getString("task").equalsIgnoreCase("storeValues"))
		{
				String groupName = jObj.getString("name");
				HttpSession session = request.getSession();
				RegistrationVO user = (RegistrationVO) session.getAttribute("USER");
				if(user == null)
					return ERROR;
				
				
				selectOptionVO = votersAnalysisService.storeGroupName(user.getRegistrationID(),groupName);
				
				return  "category";
		}
		else if(jObj.getString("task").equalsIgnoreCase("getCategoryValues"))
		{
				HttpSession session = request.getSession();
				RegistrationVO user = (RegistrationVO) session.getAttribute("USER");
				if(user == null)
				return ERROR;
				voterCategoryValues = votersAnalysisService.findVoterCategoryValues(user.getRegistrationID());
				voterCategoryValues.add(0, new SelectOptionVO(0l,"Select Group"));
		}
		else if(jObj.getString("task").equalsIgnoreCase("storeCategoeryValues"))
		{
				String name =  jObj.getString("categoryValue");
				Long id = jObj.getLong("categoryId");
				HttpSession session = request.getSession();
				RegistrationVO user = (RegistrationVO) session.getAttribute("USER");
				if(user == null)
				return ERROR;
				
				selectOptionVO = votersAnalysisService.storeCategoryVakues(user.getRegistrationID(),name,id);
				return  "category";
		}
		
			
		return Action.SUCCESS;
	}
	
	public String saveCasteName()
	{
		session = request.getSession();
		RegistrationVO user = (RegistrationVO)session.getAttribute("USER");
		if(user == null)
			return "error";
		userId = user.getRegistrationID();
		try{
			jObj = new JSONObject(getTask());
			String casteName = jObj.getString("casteName");
			Long stateId = jObj.getLong("stateId");
			Long casteCateGroupId = jObj.getLong("casteCateGroupId");
			resultStatus = votersAnalysisService.saveCasteName(userId,stateId,casteCateGroupId,casteName);
		}catch (Exception e) {
			Log.error("Exception Occured in saveCasteName() Method, Exception - ",e);
		}
		
		return Action.SUCCESS;
				
	}
	
public String saveLocality()
	{
		session = request.getSession();
		RegistrationVO user = (RegistrationVO)session.getAttribute("USER");
		if(user == null)
			return "error";
		userId = user.getRegistrationID();
		try{
			jObj = new JSONObject(getTask());
			String name = jObj.getString("name");
			Long hamlet = jObj.getLong("hamlet");
			Long localbodyId = jObj.getLong("localbodyId");
			Long wardId = jObj.getLong("ward");
			
			resultStatus = votersAnalysisService.saveLocality(userId,hamlet,name,localbodyId,wardId);
		}catch (Exception e) {
			Log.error("Exception Occured in saveCasteName() Method, Exception - ",e);
		}
		
		return Action.SUCCESS;
				
	}
	
	public String getMultipleFamilesInfo(){
		try{
			    jObj = new JSONObject(getTask());
			    HttpSession session = request.getSession();
				RegistrationVO user=(RegistrationVO) session.getAttribute("USER");
				Long userId = null;
				if(user != null && user.getRegistrationID() != null)
				    userId = user.getRegistrationID();
				else 
				  return "error";
				org.json.JSONArray jSONArray = jObj.getJSONArray("selectedFamilies");
				List<VoterHouseInfoVO> familiesList = new ArrayList<VoterHouseInfoVO>();
				VoterHouseInfoVO voterHouseInfoVO = null;
				for(int i = 0;i<jSONArray.length();i++){
					JSONObject jSONObject= jSONArray.getJSONObject(i);
					voterHouseInfoVO = new VoterHouseInfoVO();
					voterHouseInfoVO.setHouseNo(jSONObject.getString("houseNo"));
					voterHouseInfoVO.setPublicationId(jSONObject.getLong("publicationId"));
					voterHouseInfoVO.setBoothId(jSONObject.getLong("boothId"));
					familiesList.add(voterHouseInfoVO);
				}
				//votersFamilyInfo = votersAnalysisService.getMultipleFamiliesInfo(familiesList);
				votersFamilyInfo = votersAnalysisService.getMultipleFamiliesInformation(familiesList,userId);
			
		}catch (Exception e) {
			Log.error("Exception Occured in getMultipleFamilesInfo() Method, Exception - ",e);
		}
		return Action.SUCCESS;
	}
	
	public String getMultipleFamilesInfoForHamlet(){
		try{
			    jObj = new JSONObject(getTask());
			    HttpSession session = request.getSession();
				RegistrationVO user=(RegistrationVO) session.getAttribute("USER");
				Long userId = null;
				if(user != null && user.getRegistrationID() != null)
				    userId = user.getRegistrationID();
				else 
				  return "error";
				org.json.JSONArray jSONArray = jObj.getJSONArray("selectedFamilies");
				String selectType = jObj.getString("selectType");
				
				List<VoterHouseInfoVO> familiesList = new ArrayList<VoterHouseInfoVO>();
				VoterHouseInfoVO voterHouseInfoVO = null;
				for(int i = 0;i<jSONArray.length();i++){
					JSONObject jSONObject= jSONArray.getJSONObject(i);
					voterHouseInfoVO = new VoterHouseInfoVO();
					voterHouseInfoVO.setHouseNo(jSONObject.getString("houseNo"));
					voterHouseInfoVO.setPublicationId(jSONObject.getLong("publicationId"));
					voterHouseInfoVO.setBoothId(jSONObject.getLong("boothId"));
					voterHouseInfoVO.setHamletId(jSONObject.getLong("hamletId"));
					
					
					familiesList.add(voterHouseInfoVO);
				}
				//votersFamilyInfo = votersAnalysisService.getMultipleFamiliesInfo(familiesList);
				
			votersFamilyInfo = votersAnalysisService
					.getMultipleFamiliesInformationForHamlet(familiesList,
							userId,selectType);
			
		}catch (Exception e) {
			Log.error("Exception Occured in getMultipleFamilesInfoForHamlet() Method, Exception - ",e);
		}
		return Action.SUCCESS;
	}
	
	public String getMultipleFamilesInfoForEdit(){
		try{
			    jObj = new JSONObject(getTask());
			    HttpSession session = request.getSession();
				RegistrationVO user=(RegistrationVO) session.getAttribute("USER");
				Long userId = null;
				if(user != null && user.getRegistrationID() != null)
				    userId = user.getRegistrationID();
				else 
				  return "error";
				org.json.JSONArray jSONArray = jObj.getJSONArray("selectedVoters");
			   if(jObj.getString("task").equalsIgnoreCase("allFamiliesEditInfo")){
					List<VoterHouseInfoVO> votersList = new ArrayList<VoterHouseInfoVO>();
					VoterHouseInfoVO voterHouseInfoVO = null;
					for(int i = 0;i<jSONArray.length();i++){
						JSONObject jSONObject= jSONArray.getJSONObject(i);
						voterHouseInfoVO = new VoterHouseInfoVO();
						voterHouseInfoVO.setVoterId(jSONObject.getLong("voterId"));
						voterHouseInfoVO.setBoothId(jSONObject.getLong("boothId"));
						votersList.add(voterHouseInfoVO);
					}
					voterHouseInfoVO1 = votersAnalysisService.getVoterPersonalDetailsList(votersList,userId);
					return "data";
			  }else{
				  String type = "";
				    List<VoterHouseInfoVO> votersList = new ArrayList<VoterHouseInfoVO>();
					VoterHouseInfoVO voterHouseInfoVO = null;
					List<VoterHouseInfoVO> categoriesList = null;
					int totalCategCount =  jObj.getInt("total");
					for(int i = 0;i<jSONArray.length();i++){
						JSONObject jSONObject= jSONArray.getJSONObject(i);
						voterHouseInfoVO = new VoterHouseInfoVO();
						voterHouseInfoVO.setVoterId(jSONObject.getLong("voterId"));
						voterHouseInfoVO.setCasteStateId(jSONObject.getLong("castId"));
						voterHouseInfoVO.setPartyId(jSONObject.getLong("partyId"));
						voterHouseInfoVO.setMobileNo(jSONObject.getString("mobileNo"));
						voterHouseInfoVO.setUserId(userId);
						
						categoriesList = new ArrayList<VoterHouseInfoVO>();
						VoterHouseInfoVO category = null;
						if(totalCategCount > 0){
							for(int j=0;j<totalCategCount;j++){
								category = new VoterHouseInfoVO();
								String[] categVal = jSONObject.getString("categ"+j).split(",");
								category.setUserCategoryValueId(new Long(categVal[0]));
								category.setCategoryValuesId(new Long(categVal[1]));
								categoriesList.add(category);
							}
							
						}
						voterHouseInfoVO.setCategoriesList(categoriesList);
						votersList.add(voterHouseInfoVO);
					}
					
					
				  status = votersAnalysisService.updateMultipleVoterDetails(votersList,"partyCast");
				  return "update";
			  }
		}catch (Exception e) {
			Log.error("Exception Occured in getMultipleFamilesInfoForEdit() Method, Exception - ",e);
			return "exception";
		}
	}
	
	 public String getVotersInfoBySearchCriteria(){
		 try{
		    HttpSession session = request.getSession();
			RegistrationVO user=(RegistrationVO) session.getAttribute("USER");
			Long userId = null;
			if(user != null && user.getRegistrationID() != null)
				if(user.getParentUserId()!=null)
					userId=user.getMainAccountId();
				else
					userId = user.getRegistrationID();
			else 
			  return "error";
		  if(request.getParameter("task").equalsIgnoreCase("votersData")){
			VoterHouseInfoVO searchInfo = new VoterHouseInfoVO();
			searchInfo.setUserId(userId);
			if(request.getParameter("voterCardId").trim().length() >0)
				searchInfo.setVoterIdCardNo(request.getParameter("voterCardId").trim());
			if(request.getParameter("voterName").trim().length() >0)
				searchInfo.setName(request.getParameter("voterName").trim());
			if(request.getParameter("voterNameType").trim().length() >0)
				searchInfo.setSetValue(request.getParameter("voterNameType").trim());
			if(request.getParameter("guardianName").trim().length() >0)
				searchInfo.setGaurdian(request.getParameter("guardianName").trim());
			if(request.getParameter("gender").trim().length() >0)
				searchInfo.setGender(request.getParameter("gender").trim());
			if(Long.parseLong(request.getParameter("startAge")) > 0)
				searchInfo.setAge(Long.parseLong(request.getParameter("startAge")));
			if(Long.parseLong(request.getParameter("endAge")) > 0)
				searchInfo.setToAge(Long.parseLong(request.getParameter("endAge")));
			if(Long.parseLong(request.getParameter("fromSno")) > 0)
				searchInfo.setFromSno(Long.parseLong(request.getParameter("fromSno")));
			if(Long.parseLong(request.getParameter("toSno")) > 0)
				searchInfo.setToSno(Long.parseLong(request.getParameter("toSno")));
			if("or".equalsIgnoreCase(request.getParameter("queryType"))){
				searchInfo.setQueryType("or");
			}
			if(request.getParameter("houseNo") != null && request.getParameter("houseNo").trim().length() >0)
				searchInfo.setHouseNo(request.getParameter("houseNo").trim());
			
			searchInfo.setPublicationId(Long.parseLong(request.getParameter("publicationDateId")));
			searchInfo.setStartIndex(Integer.parseInt(request.getParameter("startIndex")));
			searchInfo.setMaxIndex(Integer.parseInt(request.getParameter("results")));
			searchInfo.setSortBy(request.getParameter("dir").trim());
			searchInfo.setSortByColum(request.getParameter("sort").trim());
			List<Long> categories = new ArrayList<Long>();
			if(request.getParameter("selIds").trim().length() >0){
				String[] idsArray = request.getParameter("selIds").trim().split(",");
			    for(String id : idsArray){
                   if(id.equalsIgnoreCase("party")){
                	   searchInfo.setPartyPresent(true);
			    	}else if(id.equalsIgnoreCase("cast")){
			    		searchInfo.setCastPresent(true);
			    	}else if(id.equalsIgnoreCase("locality")){
			    		searchInfo.setLocalityPresent(true);
			    	}
			    	else{
			    		categories.add(new Long(id));
			    	}
			    }
			} 
			voterHouseInfoVO1 = votersAnalysisService.getVotersInfoBySearchCriteria(searchInfo,request.getParameter("locationLvl"),Long.parseLong(request.getParameter("id")),categories);
		  }else{
			  voterHouseInfoVO1 = votersAnalysisService.getUserVoterCategories(userId);
		  }
		  }catch(Exception e){
			 Log.error("Exception Occured in getVotersInfoBySearchCriteria() Method, Exception - ",e);
		 }
		 return Action.SUCCESS;
	 }
	 
	 public String updateAllSelectedVotersInformation()
	 {
		 try
		 {
			 jObj = new JSONObject(getTask());
			 HttpSession session = request.getSession();
             RegistrationVO user=(RegistrationVO) session.getAttribute("USER");			 
			 Long userId = null;
				if(user != null && user.getRegistrationID() != null)
				    userId = user.getRegistrationID();
				else 
				  return "error";
				
			 org.json.JSONArray votersJSONArray = jObj.getJSONArray("selectedVoters");
			 Long totalCategoriesCount = jObj.getLong("total");
			 List<VoterHouseInfoVO> votersList = new ArrayList<VoterHouseInfoVO>();
			 
			 for(int i = 0;i<votersJSONArray.length();i++){
					JSONObject jSONObject= votersJSONArray.getJSONObject(i);
					voterHouseInfoVO = new VoterHouseInfoVO();
					voterHouseInfoVO.setPartyId(jSONObject.getLong("partyId"));
					voterHouseInfoVO.setCasteStateId(jSONObject.getLong("casteId"));
					voterHouseInfoVO.setHamletId(jSONObject.getLong("hamletId"));
					voterHouseInfoVO.setLocalitityId(jSONObject.getLong("localityHamletId"));

					
					voterHouseInfoVO.setUserId(userId);
					voterHouseInfoVO.setVoterId(jSONObject.getLong("voterId"));
					
					VoterHouseInfoVO category = null;
					List<VoterHouseInfoVO> categoriesList = new ArrayList<VoterHouseInfoVO>();					
					
					for(int j=0;j<totalCategoriesCount;j++){
						category = new VoterHouseInfoVO();
						String[] categVal = jSONObject.getString("categ"+j).split("-");
						category.setUserCategoryValueId(new Long(categVal[0]));
						category.setCategoryValuesId(new Long(categVal[1]));
						categoriesList.add(category);					
					}
					
					voterHouseInfoVO.setCategoriesList(categoriesList);
					
					votersList.add(voterHouseInfoVO);
				}
			 
			 
			status =  votersAnalysisService.updateAllSelectedVotersDetails(votersList);
			return "update";
			 
		 }catch(Exception e)
		 {
			 e.printStackTrace();
			 
		 }
		 return Action.SUCCESS;
		 
	 }
	 
	 public String getVotersFamiliesForEditFormAction()
	 {
		 
		 try{
			 jObj = new JSONObject(getTask());
			 HttpSession session = request.getSession();
			 RegistrationVO user=(RegistrationVO) session.getAttribute("USER");
			 
			 Long userId = null;
				if(user != null && user.getRegistrationID() != null)
				    userId = user.getRegistrationID();
				else 
				  return "error";
			
			    VoterHouseInfoVO parameters = new VoterHouseInfoVO();
			
			    parameters.setSelectedType(jObj.getString("selectedType"));
		       	parameters.setSelectedTypeId(jObj.getLong("selectedTypeId"));
		       	parameters.setPublicationId(jObj.getLong("publicationId"));
		       	parameters.setUserId(userId);

	    	org.json.JSONArray votersJSONArray = jObj.getJSONArray("votersIds");
		    List<VoterHouseInfoVO> votersList = new ArrayList<VoterHouseInfoVO>();
			VoterHouseInfoVO voterHouseInfoVO = null;
			for(int i = 0;i<votersJSONArray.length();i++){
				JSONObject jSONObject= votersJSONArray.getJSONObject(i);
				voterHouseInfoVO = new VoterHouseInfoVO();
				voterHouseInfoVO.setVoterId(jSONObject.getLong("voterId"));
				voterHouseInfoVO.setBoothId(jSONObject.getLong("boothId"));
				votersList.add(voterHouseInfoVO);
			}
			//voterHouseInfoVO1 = votersAnalysisService.getSelectedCategoryOptionsForIndividual(votersList,parameters);
			voterHouseInfoVO1 = votersAnalysisService.getSelectedVotersDetails(votersList , parameters);
	    
			 
		 }catch(Exception e)
		 {
			 e.printStackTrace();
			 
		 }
		 return Action.SUCCESS;
		 
	 }
	 
	 public String updateAllSelectedVoters(){
		 return Action.SUCCESS;
		 
	 }
	 
	 public String getMultipleFamilesInfoForEditWithSelection(){
			try{
				    jObj = new JSONObject(getTask());
				    VoterHouseInfoVO parameters = new VoterHouseInfoVO();
				    HttpSession session = request.getSession();
					RegistrationVO user=(RegistrationVO) session.getAttribute("USER");
					Long userId = null;
					if(user != null && user.getRegistrationID() != null){
						if(user.getParentUserId()!=null)
							userId=user.getMainAccountId();
						else	
							userId = user.getRegistrationID();
					}
					else 
					  return "error";
					org.json.JSONArray jSONArray = jObj.getJSONArray("selectedVoters");
				   if(jObj.getString("task").equalsIgnoreCase("getAllVoters")){
					    List<Long> categories = new ArrayList<Long>();
					    parameters.setIds(categories);
					    parameters.setUserId(userId);
					    String[] idsArray = jObj.getString("ids").split(",");
					    for(String id : idsArray){
					    	if(id.equalsIgnoreCase("all")){
					    		parameters.setAll(true);
					    		parameters.setPartyPresent(true);
					    		parameters.setCastPresent(true);
					    		parameters.setLocalityPresent(true);
					    	}else if(id.equalsIgnoreCase("party")){
					    		parameters.setPartyPresent(true);
					    	}else if(id.equalsIgnoreCase("cast")){
					    		parameters.setCastPresent(true);
					    	}else if(id.equalsIgnoreCase("locality")){
					    		parameters.setLocalityPresent(true);
					    	} 	
					    	else{
					    		categories.add(new Long(id));
					    	}
					    }
					    parameters.setSelectedType(jObj.getString("selectedType"));
				       	parameters.setSelectedTypeId(jObj.getLong("selectedTypeId"));
				       	parameters.setPublicationId(jObj.getLong("publicationId"));
					    if(jObj.getString("type").equalsIgnoreCase("getAllVoters")){
					    	org.json.JSONArray votersJSONArray = jObj.getJSONArray("votersIds");
						    List<VoterHouseInfoVO> votersList = new ArrayList<VoterHouseInfoVO>();
							VoterHouseInfoVO voterHouseInfoVO = null;
							for(int i = 0;i<votersJSONArray.length();i++){
								JSONObject jSONObject= votersJSONArray.getJSONObject(i);
								voterHouseInfoVO = new VoterHouseInfoVO();
								voterHouseInfoVO.setVoterId(jSONObject.getLong("voterId"));
								voterHouseInfoVO.setBoothId(jSONObject.getLong("boothId"));
								votersList.add(voterHouseInfoVO);
							}
							voterHouseInfoVO1 = votersAnalysisService.getSelectedCategoryOptionsForIndividual(votersList,parameters);
					    }else{   
					    	parameters.setVoterId(jObj.getLong("voterId"));
					    	
						  voterHouseInfoVO1 = votersAnalysisService.getSelectedCategoryOptions(parameters);
						  //
					    }
						return "data";
				  }else if(jObj.getString("task").equalsIgnoreCase("updateAllVoters")){
						VoterHouseInfoVO voterHouseInfoVO  = new VoterHouseInfoVO();
						List<VoterHouseInfoVO> categoriesList = null;
						int totalCategCount =  jObj.getInt("total");
						boolean partyPresent = jObj.getBoolean("partyPresent");
						boolean castPresent = jObj.getBoolean("castPresent");
						boolean localityPresent = jObj.getBoolean("localityPresent");
						for(int i = 0;i<jSONArray.length();i++){
							JSONObject jSONObject= jSONArray.getJSONObject(i);
							//voterHouseInfoVO.setVoterId(jSONObject.getLong("voterId"));
							if(castPresent)
							  voterHouseInfoVO.setCasteStateId(jSONObject.getLong("castId"));
							if(partyPresent)
							  voterHouseInfoVO.setPartyId(jSONObject.getLong("partyId"));
							if(localityPresent){
								Long hamletId=jSONObject.getLong("hamletId");
								Long locId=jSONObject.getLong("localityHamletId");
								
								if(hamletId !=null &&  hamletId !=0)
								{   if(locId !=0 && locId!=null)
									voterHouseInfoVO.setLocalitityId(locId);
							
									voterHouseInfoVO.setHamletId(hamletId);
								}else if(locId !=0l && locId!=null)
									voterHouseInfoVO.setLocalitityId(locId);
														
							}
								//  voterHouseInfoVO.setl(jSONObject.getLong("partyId"));
							voterHouseInfoVO.setUserId(userId);
							categoriesList = new ArrayList<VoterHouseInfoVO>();
							VoterHouseInfoVO category = null;
							if(totalCategCount > 0){
								for(int j=0;j<totalCategCount;j++){
									category = new VoterHouseInfoVO();
									String[] categVal = jSONObject.getString("categ"+j).split(",");
									category.setUserCategoryValueId(new Long(categVal[0]));
									category.setCategoryValuesId(new Long(categVal[1]));
									categoriesList.add(category);
								}
								
							}
							voterHouseInfoVO.setCategoriesList(categoriesList);
						}
						String type = null;
						if((castPresent && partyPresent && localityPresent))
							{
							type ="all";
						}
						else if(partyPresent && localityPresent  )
						
						type="partyLocality";
						else if (castPresent && partyPresent)
						type="partyCast";
						else if (castPresent && localityPresent)
						type="castLocality";
						else if(castPresent){
							type ="cast";
						}else if(partyPresent){
							type ="party";
						}
							else if(localityPresent){
								type ="localityPresent";
						}
						String[] voters = jObj.getString("voterIds").split(",");
						 votersAnalysisService.updateSelectedFieldsForAllVoters(voterHouseInfoVO,voters,type);
						return "update";
				  }else if(jObj.getString("task").equalsIgnoreCase("updateIndividuls")){
					    List<VoterHouseInfoVO> votersList = new ArrayList<VoterHouseInfoVO>();
						VoterHouseInfoVO voterHouseInfoVO = null;
						List<VoterHouseInfoVO> categoriesList = null;
						int totalCategCount =  jObj.getInt("total");
						boolean partyPresent = jObj.getBoolean("partyPresent");
						boolean castPresent = jObj.getBoolean("castPresent");
						boolean localityPresent = jObj.getBoolean("localityPresent");
						for(int i = 0;i<jSONArray.length();i++){
							JSONObject jSONObject= jSONArray.getJSONObject(i);
							voterHouseInfoVO = new VoterHouseInfoVO();
							voterHouseInfoVO.setVoterId(jSONObject.getLong("voterId"));
							if(castPresent)
							  voterHouseInfoVO.setCasteStateId(jSONObject.getLong("castId"));
							if(partyPresent)
							  voterHouseInfoVO.setPartyId(jSONObject.getLong("partyId"));
							if(localityPresent){
								Long hamletId=jSONObject.getLong("hamletId");
								
								Long locId= 0l;
								try{
									locId	=jSONObject.getLong("localityHamletId");
								}catch(Exception e){}
								if(hamletId !=null &&  hamletId !=0)
								{   if(locId !=0l && locId!=null)
									voterHouseInfoVO.setLocalitityId(locId);
							
									voterHouseInfoVO.setHamletId(hamletId);
								}else if(locId !=0l && locId!=null)
									voterHouseInfoVO.setLocalitityId(locId);		
							}
							voterHouseInfoVO.setUserId(userId);
							categoriesList = new ArrayList<VoterHouseInfoVO>();
							VoterHouseInfoVO category = null;
							if(totalCategCount > 0){
								for(int j=0;j<totalCategCount;j++){
									category = new VoterHouseInfoVO();
									String[] categVal = jSONObject.getString("categ"+j).split(",");
									category.setUserCategoryValueId(new Long(categVal[0]));
									category.setCategoryValuesId(new Long(categVal[1]));
									categoriesList.add(category);
								}
								
							}
							voterHouseInfoVO.setCategoriesList(categoriesList);
							votersList.add(voterHouseInfoVO);
						}
						String type = null;
						if(castPresent && partyPresent && localityPresent){
							type ="all";
						}	else if(partyPresent && localityPresent  )
							
							type="partyLocality";
							else if (castPresent && partyPresent)
							type="partyCast";
							else if (castPresent && localityPresent)
							type="castLocality";
						else if(castPresent){
							type ="cast";
						}else if(partyPresent)
							type ="party";
							else if(localityPresent){
								type ="localityPresent";
						}
					  status = votersAnalysisService.updateMultipleVoterDetails(votersList,type);
					  return "update";
				  }
			}catch (Exception e) {
				Log.error("Exception Occured in getMultipleFamilesInfoForEditWithSelection() Method, Exception - ",e);
				return "exception";
			}
			return Action.SUCCESS;
		}
	   
	    public String getElectionsInAConsti(){
	    	String param = null;
			param = getTask();
			try {
				jObj = new JSONObject(param);
				userAccessStates = votersAnalysisService.getAllElectionsInAConsti(2l,jObj.getLong("constituencyId"));
				System.out.println(jObj);
			} catch (ParseException e) {
				Log.error("Exception Occured in getElectionsInAConsti() Method, Exception - ",e);
				e.printStackTrace();
			}
			return Action.SUCCESS;
	    }
	    
	   public String getHamletsInATehsil()
	   {
		   String param = null;
		   
			param = getTask();
			try {
				jObj = new JSONObject(param);
				if(jObj.getString("task").equalsIgnoreCase("getHamletList"))
				
				hamlets = regionServiceDataImp.getHamletsInATehsil(jObj.getLong("tehsilId"));
				else if(jObj.getString("task").equalsIgnoreCase("getWardList"))
				{
					
				hamlets = regionServiceDataImp.getWardsInALocalElectionBodyByID(jObj.getLong("tehsilId"));
				}
				System.out.println(jObj);
			} catch (ParseException e) {
				Log.error("Exception Occured in getElectionsInAConsti() Method, Exception - ",e);
				e.printStackTrace();
			}
			return Action.SUCCESS;  
	   }
	   
	   public String getVoterData()
	   {
		  // String param = null;
			//param = getTask();
			try {
				LOG.debug("Entered into the getvoterData() method in VotersEditAction");
				//jObj = new JSONObject(param);
				session = request.getSession();
				RegistrationVO user=(RegistrationVO) session.getAttribute("USER");
				Long userId = null;
				if(user != null && user.getRegistrationID() != null)
				userId = user.getRegistrationID();
				String constituencyId = request.getParameter("constituencyId");
				String ids = request.getParameter("id");
				String publicationId = request.getParameter("publicationId");
				String startIndex = request.getParameter("startIndex");
				String maxIndex = request.getParameter("results");
				String buildType = request.getParameter("buildType");
				String sort = request.getParameter("sort");
				String dir = request.getParameter("dir");
				//String task = jObj.getString("getVoterData");
				VoterDataVO voterDataVO = new VoterDataVO();
				voterDataVO.setConstituencyId(Long.valueOf(constituencyId));
				voterDataVO.setId(Long.valueOf(ids));
				voterDataVO.setPublicationId(Long.valueOf(publicationId));
				voterDataVO.setStartIndex(Long.valueOf(startIndex));
				voterDataVO.setMaxIndex(Long.valueOf(maxIndex));
				voterDataVO.setDir(dir);
				voterDataVO.setBuildType(buildType);
				voterDataVO.setSort(sort);
				List<Long> categories = new ArrayList<Long>();
				if(request.getParameter("reqfields").trim().length() >0){
					String[] idsArray = request.getParameter("reqfields").trim().split(",");
				    for(String id : idsArray){
	                   if(id.equalsIgnoreCase("party")){
	                	   voterDataVO.setPartyPresent(true);
				    	}else if(id.equalsIgnoreCase("cast")){
				    		voterDataVO.setCastePresent(true);
				    	}
				    	else{
				    		categories.add(new Long(id));
				    	}
				    }
				} 
				votersData = votersAnalysisService.getVoterData(voterDataVO , userId , categories);
				voterVO = new VoterVO();
				if(votersData != null & votersData.size() > 0)
				{
					voterVO.setVotersList(votersData);
					voterVO.setTotalVoters(votersData.get(0).getTotalVoters());
				}
				
			} catch (Exception e) {
				LOG.error("Error occured in the getvoterData() method in VotersEditAction", e);
			}
		   return Action.SUCCESS;
	   }
   }
