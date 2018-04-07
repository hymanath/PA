package com.itgrids.partyanalyst.web.action;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.json.JSONArray;
import org.json.JSONObject;

import com.itgrids.partyanalyst.dto.AreaInchargeVO;
import com.itgrids.partyanalyst.dto.InchargeMemberVO;
import com.itgrids.partyanalyst.dto.RegistrationVO;
import com.itgrids.partyanalyst.dto.ResultStatus;
import com.itgrids.partyanalyst.dto.SelectOptionVO;
import com.itgrids.partyanalyst.service.IAreaInchargeDashBoardService;
import com.itgrids.partyanalyst.service.IUserProfileService;
import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionSupport;

public class AreaInchargeDashBoardAction extends ActionSupport  implements ServletRequestAware{
     
	private static final Logger LOG = Logger.getLogger(AreaInchargeDashBoardAction.class);
	
	private HttpServletRequest  request;
	private HttpSession session;
	
	private JSONObject jObj;
	private String task;
	private ResultStatus resultStatus;
	private AreaInchargeVO inchargeVO;
	private List<AreaInchargeVO> inchargeList;
	private IAreaInchargeDashBoardService areaInchargeDashBoardService;
	private IUserProfileService userProfileService;
	private String result;
	private Long levelId;
	private Long levelValue;
	private String locationName;
	private List<InchargeMemberVO> membersList;
	
	public String getLocationName() {
		return locationName;
	}

	public void setLocationName(String locationName) {
		this.locationName = locationName;
	}

	public String getResult() {
		return result;
	}

	public Long getLevelId() {
		return levelId;
	}


	public void setLevelId(Long levelId) {
		this.levelId = levelId;
	}


	public Long getLevelValue() {
		return levelValue;
	}


	public void setLevelValue(Long levelValue) {
		this.levelValue = levelValue;
	}


	public void setResult(String result) {
		this.result = result;
	}

	public ResultStatus getResultStatus() {
		return resultStatus;
	}

	public void setResultStatus(ResultStatus resultStatus) {
		this.resultStatus = resultStatus;
	}

	public String getTask() {
		return task;
	}

	public void setTask(String task) {
		this.task = task;
	}

	public IUserProfileService getUserProfileService() {
		return userProfileService;
	}

	public void setUserProfileService(IUserProfileService userProfileService) {
		this.userProfileService = userProfileService;
	}

	public List<InchargeMemberVO> getMembersList() {
		return membersList;
	}

	public void setMembersList(List<InchargeMemberVO> membersList) {
		this.membersList = membersList;
	}

	public String areaInchargeDashBoard(){
		try{
		session = request.getSession();
        RegistrationVO user = (RegistrationVO)session.getAttribute("USER");
        
		List<String> entitlements = user.getEntitlements();
		if(user == null || user.getEntitlements().size() == 0 || (!entitlements.contains("AREA_INCHARGE_DASHBOARD_ENTITLEMENT")))
		{
			return "loginAction";
		}
		
		if(user.getDistricts() != null && user.getDistricts().size() > 0)
		{
			levelId = 3L;
			for(SelectOptionVO optionVO : user.getDistricts())
			{
				levelValue = optionVO.getId();
				locationName = optionVO.getName();
			}
			
		}
		else if(user.getAssemblies() != null && user.getAssemblies().size() > 0)
		{
			levelId = 4L;
			for(SelectOptionVO optionVO : user.getAssemblies())
			{
				levelValue = optionVO.getId();
				locationName = optionVO.getName();
			}
			
		}
		}catch(Exception e){
			LOG.error("Entered into Execute Action",e);
		}
		return Action.SUCCESS;
	}


	public JSONObject getjObj() {
		return jObj;
	}

	public void setjObj(JSONObject jObj) {
		this.jObj = jObj;
	}
	
	public AreaInchargeVO getInchargeVO() {
		return inchargeVO;
	}

	public void setInchargeVO(AreaInchargeVO inchargeVO) {
		this.inchargeVO = inchargeVO;
	}
	
	public IAreaInchargeDashBoardService getAreaInchargeDashBoardService() {
		return areaInchargeDashBoardService;
	}

	public void setAreaInchargeDashBoardService(
			IAreaInchargeDashBoardService areaInchargeDashBoardService) {
		this.areaInchargeDashBoardService = areaInchargeDashBoardService;
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

	public void setServletRequest(HttpServletRequest request) {
		this.request = request;
	}
	
	public List<AreaInchargeVO> getInchargeList() {
		return inchargeList;
	}

	public void setInchargeList(List<AreaInchargeVO> inchargeList) {
		this.inchargeList = inchargeList;
	}

	public String getAreaInchargeDetails(){
		try{
			
			/*RegistrationVO regVO =(RegistrationVO) request.getSession().getAttribute("USER");
			Long userId=0l;
			if(regVO!=null){
				userId = regVO.getRegistrationID();
			}*/
			jObj = new JSONObject(getTask());
			inchargeList = areaInchargeDashBoardService.getAreaInchargeDetails(jObj.getString("voterId"),jObj.getString("mobileNo"),jObj.getString("memberShipId"));
			
		}catch (Exception e) {
			LOG.error("Entered into getAreaInchargeDetails Action",e);
		}
		
		return Action.SUCCESS;
	}
	
	public String savingAssigningBooths(){
		try{
			
			jObj = new JSONObject(getTask());
			JSONArray boothIdsArr = jObj.getJSONArray("boothIds");  
			List<String> boothIdsList = new ArrayList<String>();
			if(boothIdsArr != null && boothIdsArr.length() > 0){
				for (int i = 0; i < boothIdsArr.length(); i++){
					boothIdsList.add(boothIdsArr.getString(i));          
				}  
			}
			resultStatus = areaInchargeDashBoardService.savingAssigningBooths(jObj.getLong("cadreId"),boothIdsList,jObj.getLong("levelId"),jObj.getLong("levelValue"));
			
		}catch (Exception e) {
			LOG.error("Entered into savingAssigningBooths Action",e);
		}
		
		return Action.SUCCESS;
	}
	public String getAssignedAndUnAssignedBooths(){
		try{
			
			jObj = new JSONObject(getTask());
			inchargeVO = areaInchargeDashBoardService.getAssignedAndUnAssignedBooths(jObj.getLong("levelId"),jObj.getLong("levelValue"));
			
		}catch (Exception e) {
			LOG.error("Entered into getAssignedAndUnAssignedBooths Action",e);
		}
		
		return Action.SUCCESS;
	}
	public String editAssignedInchargeDetails(){
		try{
			
			jObj = new JSONObject(getTask());
			inchargeVO = areaInchargeDashBoardService.editAssignedInchargeDetails(jObj.getLong("cadreId"),jObj.getLong("levelId"),jObj.getLong("levelValue"));
			
		}catch (Exception e) {
			LOG.error("Entered into editAssignedInchargeDetails Action",e);
		}
		
		return Action.SUCCESS;
	}
	public String getAreaInchargeAssignedBoothDetails(){
		try{
			
			jObj = new JSONObject(getTask());
			inchargeList = areaInchargeDashBoardService.getAreaInchargeAssignedBoothDetails(jObj.getLong("levelId"),jObj.getLong("levelValue"));
			
		}catch (Exception e) {
			LOG.error("Entered into getAreaInchargeAssignedBoothDetails Action",e);
		}
		
		return Action.SUCCESS;
	}
	public String getAreaInchargesStatusWiseCount(){
		try{
			
			jObj = new JSONObject(getTask());
			inchargeVO = areaInchargeDashBoardService.getAreaInchargesStatusWiseCount(jObj.getLong("levelId"),jObj.getLong("levelValue"));
			
		}catch (Exception e) {
			LOG.error("Entered into getAreaInchargesStatusWiseCount Action",e);
		}
		
		return Action.SUCCESS;
		
	}
	public String deleteAreaInchargeAssignBooths(){
		try{
			LOG.info("Entered into deleteAreaInchargeAssignBooths");
				jObj = new JSONObject(getTask());
			Long candidateId = jObj.getLong("candidateId");
			String boothId = jObj.getString("boothId");
			
			result = areaInchargeDashBoardService.deleteAreaInchargeAssignBooths(candidateId,boothId,jObj.getLong("levelId"),jObj.getLong("levelValue"));	
		}catch(Exception e){
			LOG.error("Exception in deleteAreaInchargeAssig" +
					"nBooths method,e ");
		}
		return Action.SUCCESS;
	}

	public String getBoothAssignedpercentageBaseConstituencies(){
		try{
			LOG.info("Entered into deleteAreaInchargeAssignBooths");
				jObj = new JSONObject(getTask());
			String fromDateStr = jObj.getString("fromDateStr");
			String toDateStr = jObj.getString("toDateStr");
			Long activityMemberId =jObj.getLong("activityMemberId");
			
			membersList = areaInchargeDashBoardService.getBoothAssignedpercentageBaseConstituencies(fromDateStr,toDateStr,activityMemberId);	
		}catch(Exception e){
			LOG.error("Exception in getBoothAssignedpercentageBaseConstituencies" +
					"nBooths method,e ");
		}
		return Action.SUCCESS;
	}
}
