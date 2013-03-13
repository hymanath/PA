package com.itgrids.partyanalyst.web.action;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.json.JSONArray;
import org.json.JSONObject;

import com.itgrids.partyanalyst.dto.RegistrationVO;
import com.itgrids.partyanalyst.dto.ResultStatus;
import com.itgrids.partyanalyst.dto.SelectOptionVO;
import com.itgrids.partyanalyst.excel.booth.VoterVO;
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
	private List<String> votercardIds;
	private List<Long> serialNos;
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

	
	public List<String> getVotercardIds() {
		return votercardIds;
	}

	public void setVotercardIds(List<String> votercardIds) {
		this.votercardIds = votercardIds;
	}

	
	public List<Long> getSerialNos() {
		return serialNos;
	}

	public void setSerialNos(List<Long> serialNos) {
		this.serialNos = serialNos;
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
		else if(jObj.getString("task").equalsIgnoreCase("saveVoterDetails"))
		{
			JSONArray jsonArray = jObj.getJSONArray("voterInfo");
			Long boothId = jObj.getLong("boothId");
			Long sno = null;
			Map<String , VoterVO> votersMap = new HashMap<String, VoterVO>();
			//VoterVO  voterVo = new ArrayList<VoterVO>();
			for (int i = 0 ; i< jsonArray.length(); i++)
			{
				VoterVO voterDetails = new VoterVO();
				JSONObject jSONObject= jsonArray.getJSONObject(i);
				String name = jSONObject.getString("name");	
				String gender = jSONObject.getString("gender");
				Long age = jSONObject.getLong("age");
				String voterId = jSONObject.getString("voterId");
				String houseNo = jSONObject.getString("houseNo");
				String gurdianName = jSONObject.getString("gurdianName");
				String relationType = jSONObject.getString("relationType");
				String serialNo = jSONObject.getString("serialNo");
				if(serialNo != null && serialNo.trim().length() > 0)
				{
					sno = Long.valueOf(serialNo);
				}
				voterDetails.setFirstName(name);
				voterDetails.setGender(gender);
				voterDetails.setAge(age);
				voterDetails.setVoterIDCardNo(voterId);
				voterDetails.setHouseNo(houseNo);
				voterDetails.setRelationshipType(relationType);
				voterDetails.setSerialNo(sno);
				voterDetails.setRelativeFirstName(gurdianName);
				//voterVo.add(voterDetails);
				votersMap.put(voterId, voterDetails);
			}
			votercardIds = votersAnalysisService.storeVoterDetails(votersMap,boothId);
			return "storeVoter";
		}
		else if(jObj.getString("task").equalsIgnoreCase("checkForVoterId"))
		{
			storeVoter = votersAnalysisService.checkForVoterId(jObj.getString("voterId"));
			return "storeVoter";
		}
		else if(jObj.getString("task").equalsIgnoreCase("checkForSerialNos")){
			JSONArray jsonArray = jObj.getJSONArray("serialNoList");
			Long boothId = jObj.getLong("boothId"); 
			List<Long> snos = new ArrayList<Long>();
			for (int i = 0 ; i< jsonArray.length(); i++)
			{
				JSONObject jSONObject= jsonArray.getJSONObject(i);
				Long serialNo = jSONObject.getLong("serialNo");
				snos.add(serialNo);
			}
			serialNos = votersAnalysisService.checkForSerialNos(snos,boothId);
			return "serialNo";
		}
		return Action.SUCCESS;
		
	}

}
