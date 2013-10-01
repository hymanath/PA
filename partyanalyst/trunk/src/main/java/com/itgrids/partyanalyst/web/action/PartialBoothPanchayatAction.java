package com.itgrids.partyanalyst.web.action;

import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.util.ServletContextAware;
import org.jfree.util.Log;
import org.json.JSONObject;

import com.itgrids.partyanalyst.dto.PartialBoothPanchayatVO;
import com.itgrids.partyanalyst.dto.RegistrationVO;
import com.itgrids.partyanalyst.dto.ResultStatus;
import com.itgrids.partyanalyst.dto.SelectOptionVO;
import com.itgrids.partyanalyst.service.ICrossVotingEstimationService;
import com.itgrids.partyanalyst.service.IPartialBoothPanchayatService;
import com.itgrids.partyanalyst.utils.IConstants;
import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionSupport;

public class PartialBoothPanchayatAction extends ActionSupport implements ServletRequestAware,ServletContextAware{

	
	private static final long serialVersionUID = 1L;
	private HttpServletRequest request;
	private ServletContext context;
	private HttpSession session;
	private static final Logger LOG = Logger.getLogger(PartialBoothPanchayatAction.class);
	private ICrossVotingEstimationService crossVotingEstimationService;
	private List<SelectOptionVO> userAccessConstituencyList;
	private ResultStatus resultStatus;
	private IPartialBoothPanchayatService partialBoothPanchayatService;
	private String task;
	JSONObject jObj = null;
	private List<PartialBoothPanchayatVO> partialBoothPanchayatList;
	public void setServletContext(ServletContext context) {
		this.context = context;
	}

	public void setServletRequest(HttpServletRequest request) {
		this.request = request;	
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

	
	public ResultStatus getResultStatus() {
		return resultStatus;
	}

	public void setResultStatus(ResultStatus resultStatus) {
		this.resultStatus = resultStatus;
	}
	
	public IPartialBoothPanchayatService getPartialBoothPanchayatService() {
		return partialBoothPanchayatService;
	}

	public void setPartialBoothPanchayatService(
			IPartialBoothPanchayatService partialBoothPanchayatService) {
		this.partialBoothPanchayatService = partialBoothPanchayatService;
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

	
	public List<PartialBoothPanchayatVO> getPartialBoothPanchayatList() {
		return partialBoothPanchayatList;
	}

	public void setPartialBoothPanchayatList(
			List<PartialBoothPanchayatVO> partialBoothPanchayatList) {
		this.partialBoothPanchayatList = partialBoothPanchayatList;
	}

	public String execute()
	{
		session = request.getSession();
		RegistrationVO regVO = (RegistrationVO) session.getAttribute("USER");
		if(regVO == null)
		{
			return Action.ERROR;
		}
		Long electionYear = new Long(IConstants.PRESENT_ELECTION_YEAR);
		Long electionTypeId = new Long(IConstants.ASSEMBLY_ELECTION_TYPE_ID);
		userAccessConstituencyList = crossVotingEstimationService.getConstituenciesForElectionYearAndTypeWithUserAccess(regVO.getRegistrationID(),electionYear,electionTypeId);
		//constituencies = suggestiveModelService.getConstituenciesForUserAccessByStateId(userAccessConstituencyList,electionTypeId,electionYear);
		userAccessConstituencyList.add(0, new SelectOptionVO(0L,"Select Constituency"));
		return Action.SUCCESS;
	}
	
	public String savePartialBoothDetails()
	{
		try {
			LOG.debug("Enterd into savePartialBoothDetails() in PartialBoothPanchayatAction Class");
			String param = null;
			param = getTask();
			jObj = new JSONObject(param);
			resultStatus = partialBoothPanchayatService.savePartialBoothPanchayaDetails(jObj.getLong("panchayatId"),jObj.getLong("boothId"),jObj.getLong("ppanchayatId"),jObj.getString("description"),jObj.getString("pdescription"));
			
		} catch (Exception e) {
			Log.error("exception raised in savePartialBoothDetails() in PartialBoothPanchayatAction Class" , e);
		}
		return Action.SUCCESS;
	}
	
	public String getPartialBoothDetails()
	{
		try {
			LOG.debug("Enterd into getPartialBoothDetails() in PartialBoothPanchayatAction Class");
			String param = null;
			param = getTask();
			jObj = new JSONObject(param);
			partialBoothPanchayatList = partialBoothPanchayatService.getAllPartialBoothsInASelectedMandal(jObj.getLong("mandalId"),jObj.getLong("publicationId"));
		} catch (Exception e) {
			Log.error("exception raised in getPartialBoothDetails() in PartialBoothPanchayatAction Class" , e);
		}
		return Action.SUCCESS;
	}
	
	public String editOrDeleteSelectedPartialBooth()
	{
		String returnString = "";
		try {
			LOG.debug("Enterd into editOrDeleteSelectedPartialBooth() in PartialBoothPanchayatAction Class");
			String param = null;
			
			param = getTask();
			jObj = new JSONObject(param);
			String task = jObj.getString("task");
			if(task.equalsIgnoreCase("editSelectedPartialBooth"))
			{
				partialBoothPanchayatList = partialBoothPanchayatService.getSelectedPartialPanchayatDetails(jObj.getLong("id"));
				returnString = "edit";
			}
			else if(task.equalsIgnoreCase("deleteSelectedPartialBooth"))
			{
				resultStatus = partialBoothPanchayatService.deleteSelectedPartialPanchayat(jObj.getLong("id"),jObj.getLong("boothId"));
				returnString = "delete";
			}
		} catch (Exception e) {
			Log.error("exception raised in editOrDeleteSelectedPartialBooth() in PartialBoothPanchayatAction Class" , e);
		}
		return returnString;
	}
	
	public String updatePartialBoothDetails()
	{
		try {
			LOG.debug("Enterd into updatePartialBoothDetails() in PartialBoothPanchayatAction Class");
			String param = null;
			param = getTask();
			jObj = new JSONObject(param);
			resultStatus = partialBoothPanchayatService.updatePartialBoothPanchayaDetails(jObj.getLong("id"),jObj.getLong("panchayatId"),jObj.getLong("boothId"),jObj.getLong("ppanchayatId"),jObj.getString("description"),jObj.getString("pdescription"));
			
		} catch (Exception e) {
			Log.error("exception raised in updatePartialBoothDetails() in PartialBoothPanchayatAction Class" , e);
		}
		return Action.SUCCESS;
	}

}
