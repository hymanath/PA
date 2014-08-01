package com.itgrids.partyanalyst.web.action;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.json.JSONArray;
import org.json.JSONObject;

import com.itgrids.partyanalyst.dto.CandidateVO;
import com.itgrids.partyanalyst.dto.GenericVO;
import com.itgrids.partyanalyst.dto.RegistrationVO;
import com.itgrids.partyanalyst.dto.ResultStatus;
import com.itgrids.partyanalyst.service.ICandidateUpdationDetailsService;
import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionSupport;

public class CandidateUpdationDetailsAction extends ActionSupport implements ServletRequestAware
{
/**
*
*/
private static final Logger LOG = Logger.getLogger(CandidateUpdationDetailsAction.class);
private static final long serialVersionUID = 1L;
private HttpServletRequest request;
private HttpServletResponse response;
private HttpSession session;
JSONObject jObj = null;
private String task = null;
private CandidateVO candidateDetails;
private List<GenericVO> electionYearsList,districtList;
private List<CandidateVO> candidateVOList;
private ResultStatus resultStatus;

public List<GenericVO> getElectionYearsList() {
return electionYearsList;
}

public void setElectionYearsList(List<GenericVO> electionYearsList) {
this.electionYearsList = electionYearsList;
}

public List<GenericVO> getDistrictList() {
return districtList;
}

public void setDistrictList(List<GenericVO> districtList) {
this.districtList = districtList;
}
private ICandidateUpdationDetailsService candidateUpdationDetailsService;


public void setServletRequest(HttpServletRequest request) {
this.request = request;
}

public void setServletResponse(HttpServletResponse response) {
this.response = response;
}

public HttpServletRequest getRequest() {
return request;
}

public void setRequest(HttpServletRequest request) {
this.request = request;
}

public HttpServletResponse getResponse() {
return response;
}

public void setResponse(HttpServletResponse response) {
this.response = response;
}

public HttpSession getSession() {
return session;
}

public void setSession(HttpSession session) {
this.session = session;
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



public CandidateVO getCandidateDetails() {
return candidateDetails;
}

public void setCandidateDetails(CandidateVO candidateDetails) {
this.candidateDetails = candidateDetails;
}

public ICandidateUpdationDetailsService getCandidateUpdationDetailsService() {
return candidateUpdationDetailsService;
}

public void setCandidateUpdationDetailsService(
ICandidateUpdationDetailsService candidateUpdationDetailsService) {
this.candidateUpdationDetailsService = candidateUpdationDetailsService;
}


public List<CandidateVO> getCandidateVOList() {
	return candidateVOList;
}

public void setCandidateVOList(List<CandidateVO> candidateVOList) {
	this.candidateVOList = candidateVOList;
}


public ResultStatus getResultStatus() {
	return resultStatus;
}

public void setResultStatus(ResultStatus resultStatus) {
	this.resultStatus = resultStatus;
}

public String execute()
{
	HttpSession session = request.getSession();
	RegistrationVO user = (RegistrationVO) session.getAttribute("USER");
	
	if(user == null)
	{
		return Action.INPUT;
	}
    return Action.SUCCESS;
}

public String getCandidateDetailsForElection()
{
try{

jObj = new JSONObject(getTask());
candidateDetails = candidateUpdationDetailsService.getCandidateDetailsForElection(jObj.getLong("electionId"),jObj.getLong("districtId"));
}
catch (Exception e) {
LOG.error("Exception Occured in getCandidateDetails() method", e);

}
return Action.SUCCESS;
}

@SuppressWarnings("unchecked")
public String gettingElectionYears()
{
try {
Long electionTypeId = Long.parseLong(request
.getParameter("electionTypeId"));
electionYearsList = (List<GenericVO>) candidateUpdationDetailsService.gettingElectionYears(electionTypeId);
} catch (Exception e) {
LOG.error("Exception Occured in gettingElectionYears() method", e);
}

return Action.SUCCESS;
}

@SuppressWarnings("unchecked")
public String getAllDistrictsForAState()
{
try {
Long stateId = Long.parseLong(request.getParameter("stateId"));
districtList = (List<GenericVO>)candidateUpdationDetailsService.getAllDistrictsForAState(stateId);

} catch (Exception e) {
LOG.error("Exception Occured in getAllDistrictsForAState() method", e);
}
return Action.SUCCESS;
}

public String gettingCandidateDetails()
{
 try{
	 List<Long> districtIds=new ArrayList<Long>();
	 String task=request.getParameter("task");
	 org.json.JSONObject jobj=new org.json.JSONObject(task);
	 Long electionScopeId=jobj.getLong("electionScopeId");
	 Long electionId=jobj.getLong("electionId");
	 JSONArray jArray = jobj.getJSONArray("districtIds");
	 for (int i = 0; i < jArray.length(); i++) 
		{
			districtIds.add((Long.parseLong(jArray.getString(i))));
		}
   candidateVOList =candidateUpdationDetailsService.gettingCandidateDetails(electionScopeId,electionId,districtIds);
     
   }
    catch(Exception e)
   {
	 LOG.error("Exception Occured in getAllDistrictsForAState() method", e);
     e.printStackTrace();
	 
   }
	
	return Action.SUCCESS;
}


public String updateDetailsofACandidate()
{
 List<CandidateVO> candidateVOList=new ArrayList<CandidateVO>();
 try
 {
	 org.json.JSONObject jobj=new org.json.JSONObject(getTask());
	 JSONArray jArray = jobj.getJSONArray("candidateDetailsArray");
	 for (int i = 0; i < jArray.length(); i++) 
	 {
		JSONObject obj = jArray.getJSONObject(i);
		CandidateVO vo=new CandidateVO();
		vo.setCandidateId(obj.getLong("candidateDetailsId"));//candidateDetailsId
		//vo.setCaste(obj.getString("caste"));
		//vo.setEducation(obj.getString("education"));
		vo.setCasteId(obj.getLong("casteId"));
		vo.setEducationId(obj.getLong("educationId"));
		vo.setHowLongWorkingInParty(obj.getDouble("workingHours"));
		vo.setMobileNo(obj.getString("mobileNo"));
		candidateVOList.add(vo);
	 }
	 
	 resultStatus= candidateUpdationDetailsService.updateDetailsofACandidate(candidateVOList);
	 
 }
 catch (Exception e)
 {
	 LOG.error("Exception Occured in updateDetailsofACandidate() method", e);
     e.printStackTrace();
 }	
	
	

	return Action.SUCCESS;	
}





}