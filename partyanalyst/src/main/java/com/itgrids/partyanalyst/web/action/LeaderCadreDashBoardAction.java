package com.itgrids.partyanalyst.web.action;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.json.JSONObject;

import com.itgrids.partyanalyst.dto.CadreAmountDetailsVO;
import com.itgrids.partyanalyst.dto.RegistrationVO;
import com.itgrids.partyanalyst.helper.EntitlementsHelper;
import com.itgrids.partyanalyst.service.ILeaderCadreDashBoardService;
import com.opensymphony.xwork2.Action;

public class LeaderCadreDashBoardAction implements ServletRequestAware {

	private static Logger LOG = Logger.getLogger(LeaderCadreDashBoardAction.class);
	
	private HttpServletRequest request;
	
	private EntitlementsHelper entitlementsHelper;
	private JSONObject jObj;
	private String task;
	private ILeaderCadreDashBoardService leaderCadreDashBoardService;
	private List<CadreAmountDetailsVO> amountDetails;
	private CadreAmountDetailsVO amountDetailsVO;
	
		public CadreAmountDetailsVO getAmountDetailsVO() {
		return amountDetailsVO;
	}


	public void setAmountDetailsVO(CadreAmountDetailsVO amountDetailsVO) {
		this.amountDetailsVO = amountDetailsVO;
	}


		public String getTask() {
		return task;
	}

		
	public List<CadreAmountDetailsVO> getAmountDetails() {
			return amountDetails;
		}


		public void setAmountDetails(List<CadreAmountDetailsVO> amountDetails) {
			this.amountDetails = amountDetails;
		}


	public ILeaderCadreDashBoardService getLeaderCadreDashBoardService() {
			return leaderCadreDashBoardService;
		}

		public void setLeaderCadreDashBoardService(
				ILeaderCadreDashBoardService leaderCadreDashBoardService) {
			this.leaderCadreDashBoardService = leaderCadreDashBoardService;
		}

	public void setTask(String task) {
		this.task = task;
	}

	public void setServletRequest(HttpServletRequest request) {
		this.request = request;
	}

	public EntitlementsHelper getEntitlementsHelper() {
		return entitlementsHelper;
	}

	public void setEntitlementsHelper(EntitlementsHelper entitlementsHelper) {
		this.entitlementsHelper = entitlementsHelper;
	}
 
	public String execute(){
		LOG.info("Entered into execute() in LeaderCadreDashBoardActioon class");
		leaderCadreDashBoardService.testMethod();
		return Action.SUCCESS;
	}
	
	public String getLoationWiseLeaderCadreInfo()
	{
		try{
			RegistrationVO regVO = (RegistrationVO) request.getSession().getAttribute("USER");
			
			jObj = new JSONObject(getTask());
			SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
			String fromDate = jObj.getString("fromDate");
			String toDate = jObj.getString("toDate");
			Date reqFromDate = null;
			Date reqToDate = null;
			if(fromDate.trim().length() > 0){
				reqFromDate = sdf.parse(fromDate);
			}
			if(toDate.trim().length() > 0){
				reqToDate = sdf.parse(toDate);
			}
			if(jObj.getString("task").equalsIgnoreCase("mainLevel")){
			    amountDetails = leaderCadreDashBoardService.getLoationWiseLeaderCadreDetails(jObj.getString("type"),jObj.getLong("stateId"),regVO.getAccessType(),regVO.getAccessValue(),reqFromDate,reqToDate);
			}if(jObj.getString("task").equalsIgnoreCase("subLevel")){
				amountDetails = leaderCadreDashBoardService.getSubLevelLoationWiseLeaderCadreDetails(jObj.getString("type"),jObj.getLong("id"),regVO.getAccessType(),regVO.getAccessValue(),reqFromDate,reqToDate);
			}
		}
		catch (Exception e) {
			LOG.info("Entered into getLoationLeaderCadreInfo() in LeaderCadreDashBoardActioon class");
		}
		return Action.SUCCESS;
	}
	
	public String getLocationWiseAsOfNowDetails(){
		try{
			jObj = new JSONObject(getTask());
			amountDetails = leaderCadreDashBoardService.getLocationWiseAsOfNowDetails(jObj.getString("type"),jObj.getLong("stateId"));
		}
		catch (Exception e) {
			LOG.info("Entered into getLocationWiseAsOfNowDetails() in LeaderCadreDashBoardActioon class");
		}
		return Action.SUCCESS;
	}
	
	public String getLocationWiseToDayDetails()	{
		try{
			jObj = new JSONObject(getTask());
			String task = jObj.getString("fromTask");
			if(task.equalsIgnoreCase("today")){
				amountDetails = leaderCadreDashBoardService.getLocationWiseToDayDetails(jObj.getString("type"),jObj.getLong("stateId"),"today");
			}else{
				amountDetails = leaderCadreDashBoardService.getLocationWiseToDayDetails(jObj.getString("type"),jObj.getLong("stateId"),"asOfToday");
			}
		}
		catch (Exception e) {
			LOG.info("Entered into getLocationWiseToDayDetails() in LeaderCadreDashBoardActioon class");
		}
		return Action.SUCCESS;
	}
	
	public String duplicateUserExecute()
	{
		
		return Action.SUCCESS;
	}
	public String getDuplicateUsersInLocation()
	{
		try {
			jObj = new JSONObject(getTask());
		
		SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
		String fromDate = jObj.getString("fromDate");
		String toDate = jObj.getString("toDate");
		Date reqFromDate = null;
		Date reqToDate = null;
		if(fromDate.trim().length() > 0){
			reqFromDate = sdf.parse(fromDate);
		}
		if(toDate.trim().length() > 0){
			reqToDate = sdf.parse(toDate);
		}
		if(jObj.getString("task").equalsIgnoreCase("usersData"))
		{
			Long  userId = jObj.getLong("userId");
			Long locationId = jObj.getLong("locationId");
			String type = jObj.getString("type");
			Long constituencyId = jObj.getLong("constituencyId");
			amountDetailsVO = leaderCadreDashBoardService.getUsersInLocation(reqFromDate,reqToDate,userId,locationId,type,constituencyId);
		}
		else if(jObj.getString("task").equalsIgnoreCase("getUsers"))
		amountDetailsVO = leaderCadreDashBoardService.getDuplicateUsersInLocation(reqFromDate,reqToDate);
		} catch (ParseException e) {
			
			e.printStackTrace();
		}
		return Action.SUCCESS;
	}
	
	
}