package com.itgrids.partyanalyst.web.action;

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
import com.itgrids.partyanalyst.service.IVotersAnalysisService;
import com.itgrids.partyanalyst.utils.IConstants;
import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionSupport;

@SuppressWarnings("serial")
/**
 * This Class is used for adding missing voters
 * @author Prasad Thiragabathina
 *
 */
public class MissingVotersAction extends ActionSupport implements ServletRequestAware {
	
	private HttpServletRequest request;
	private List<SelectOptionVO> constituencyList,userAccessConstituencyList;
	private ICrossVotingEstimationService crossVotingEstimationService;
	private IVotersAnalysisService votersAnalysisService;
	private String task;
	JSONObject jObj;
	private static final Logger log = Logger.getLogger(VotersAnalysisAction.class);
	private List<SelectOptionVO> boothsList;
	private ResultStatus storeVoter;
	public void setServletRequest(HttpServletRequest request) {
		this.request = request;
	}
	
	public List<SelectOptionVO> getConstituencyList() {
		return constituencyList;
	}

	public void setConstituencyList(List<SelectOptionVO> constituencyList) {
		this.constituencyList = constituencyList;
	}
	
	public List<SelectOptionVO> getUserAccessConstituencyList() {
		return userAccessConstituencyList;
	}

	public void setUserAccessConstituencyList(
			List<SelectOptionVO> userAccessConstituencyList) {
		this.userAccessConstituencyList = userAccessConstituencyList;
	}
	
	public ICrossVotingEstimationService getCrossVotingEstimationService() {
		return crossVotingEstimationService;
	}

	public void setCrossVotingEstimationService(
			ICrossVotingEstimationService crossVotingEstimationService) {
		this.crossVotingEstimationService = crossVotingEstimationService;
	}
	
	public IVotersAnalysisService getVotersAnalysisService() {
		return votersAnalysisService;
	}

	public void setVotersAnalysisService(
			IVotersAnalysisService votersAnalysisService) {
		this.votersAnalysisService = votersAnalysisService;
	}
	
	public String getTask() {
		return task;
	}

	public void setTask(String task) {
		this.task = task;
	}
	
	public List<SelectOptionVO> getBoothsList() {
		return boothsList;
	}

	public void setBoothsList(List<SelectOptionVO> boothsList) {
		this.boothsList = boothsList;
	}
	
	public ResultStatus getStoreVoter() {
		return storeVoter;
	}

	public void setStoreVoter(ResultStatus storeVoter) {
		this.storeVoter = storeVoter;
	}

	public String execute() {
		HttpSession session = request.getSession();
		RegistrationVO user=(RegistrationVO) session.getAttribute("USER");
		if(user == null)
		return ERROR;
		Long userID = user.getRegistrationID();
		Long electionYear = new Long(IConstants.PRESENT_ELECTION_YEAR);
		Long electionTypeId = new Long(IConstants.ASSEMBLY_ELECTION_TYPE_ID);
		userAccessConstituencyList = crossVotingEstimationService.getConstituenciesForElectionYearAndTypeWithUserAccess(userID,electionYear,electionTypeId);
		constituencyList = votersAnalysisService.getConstituencyList(userAccessConstituencyList);
		constituencyList.add(0, new SelectOptionVO(0L,"Select Constituency"));
		return SUCCESS;
		
	}
	/**
	 * This method is used for handling all Ajax calls 
	 * @return String
	 * @author Prasad Thiragabathina
	 */
	public String ajaxHandler() {
		String param;
		param = getTask();

		try{
			jObj = new JSONObject(param);	
			
		}catch (Exception e) {
			e.printStackTrace();
			log.error("Exception Occured in getRequestMessagesForUser() Method,Exception is- "+e);
		}
		if(jObj.getString("task").equalsIgnoreCase("getBoothsForConstituency"))
		{
			Long constituencyId = jObj.getLong("constituencyId");
			Long publicationId = jObj.getLong("publicationId");
			boothsList = votersAnalysisService.getBoothsForConstituencyAndPublication(constituencyId,publicationId);
			return "boothsList";
		}
		if(jObj.getString("task").equalsIgnoreCase("saveVoterDetails"))
		{
			String name = jObj.getString("name");
			String voterCardNo = jObj.getString("voterCardNo");
			String houseNo = jObj.getString("houseNo");
			String gaurdian = jObj.getString("gaurdian");
			String relationShip = jObj.getString("relationShip");
			String gender = jObj.getString("gender");
			String mobileNo = jObj.getString("mobileNo");
			Long age = jObj.getLong("age");
			Long boothId = jObj.getLong("boothId");
			storeVoter = votersAnalysisService.saveVoters(name,voterCardNo,houseNo,gaurdian,relationShip,gender,mobileNo,age,boothId);
			return "storeVoter";
		}
		return Action.SUCCESS;
		
	}

}
