package com.itgrids.partyanalyst.web.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.json.JSONObject;

import com.itgrids.partyanalyst.dto.CadreAmountDetailsVO;
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
			jObj = new JSONObject(getTask());
			amountDetails = leaderCadreDashBoardService.getLoationWiseLeaderCadreDetails(jObj.getString("type"),jObj.getLong("stateId"));
		}
		catch (Exception e) {
			LOG.info("Entered into getLoationLeaderCadreInfo() in LeaderCadreDashBoardActioon class");
		}
		return Action.SUCCESS;
	}
}