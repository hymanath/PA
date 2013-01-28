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
import com.itgrids.partyanalyst.dto.VoterHouseInfoVO;
import com.itgrids.partyanalyst.service.IStaticDataService;
import com.itgrids.partyanalyst.service.IVotersAnalysisService;
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
	//private String windowTask = null;
	private ResultStatus resultStatus;
	private List<VoterHouseInfoVO> votersFamilyInfo;
	private boolean status;
	
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

	public String execute() throws Exception{
		
		
		session = request.getSession();
		RegistrationVO user = (RegistrationVO)session.getAttribute("USER");
		if(user == null)
			return "error";
		
		userId = user.getRegistrationID();
		
				
		castCategoryGroupList = votersAnalysisService.getcastCategoryGroups();
		userAccessStates = staticDataService.getUserAccessStates(userId);
		
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
				votersFamilyInfo = votersAnalysisService.getMultipleFamiliesInfo(familiesList);
			
		}catch (Exception e) {
			Log.error("Exception Occured in getMultipleFamilesInfo() Method, Exception - ",e);
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
				  status = votersAnalysisService.updateMultipleVoterDetails(votersList,"all");
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
			    	}else{
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
	 
	 public String getMultipleFamilesInfoForEditWithSelection(){
			try{
				    jObj = new JSONObject(getTask());
				    VoterHouseInfoVO parameters = new VoterHouseInfoVO();
				    HttpSession session = request.getSession();
					RegistrationVO user=(RegistrationVO) session.getAttribute("USER");
					Long userId = null;
					if(user != null && user.getRegistrationID() != null)
					    userId = user.getRegistrationID();
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
					    	}else if(id.equalsIgnoreCase("party")){
					    		parameters.setPartyPresent(true);
					    	}else if(id.equalsIgnoreCase("cast")){
					    		parameters.setCastPresent(true);
					    	}else{
					    		categories.add(new Long(id));
					    	}
					    }
					    
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
					    }
						return "data";
				  }else if(jObj.getString("task").equalsIgnoreCase("updateAllVoters")){
						VoterHouseInfoVO voterHouseInfoVO  = new VoterHouseInfoVO();
						List<VoterHouseInfoVO> categoriesList = null;
						int totalCategCount =  jObj.getInt("total");
						boolean partyPresent = jObj.getBoolean("partyPresent");
						boolean castPresent = jObj.getBoolean("castPresent");
						for(int i = 0;i<jSONArray.length();i++){
							JSONObject jSONObject= jSONArray.getJSONObject(i);
							//voterHouseInfoVO.setVoterId(jSONObject.getLong("voterId"));
							if(castPresent)
							  voterHouseInfoVO.setCasteStateId(jSONObject.getLong("castId"));
							if(partyPresent)
							  voterHouseInfoVO.setPartyId(jSONObject.getLong("partyId"));
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
						if(castPresent && partyPresent){
							type ="all";
						}else if(castPresent){
							type ="cast";
						}else if(partyPresent){
							type ="party";
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
						for(int i = 0;i<jSONArray.length();i++){
							JSONObject jSONObject= jSONArray.getJSONObject(i);
							voterHouseInfoVO = new VoterHouseInfoVO();
							voterHouseInfoVO.setVoterId(jSONObject.getLong("voterId"));
							if(castPresent)
							  voterHouseInfoVO.setCasteStateId(jSONObject.getLong("castId"));
							if(partyPresent)
							  voterHouseInfoVO.setPartyId(jSONObject.getLong("partyId"));
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
						if(castPresent && partyPresent){
							type ="all";
						}else if(castPresent){
							type ="cast";
						}else if(partyPresent){
							type ="party";
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
   }
