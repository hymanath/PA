package com.itgrids.partyanalyst.web.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.json.JSONObject;

import com.itgrids.partyanalyst.dto.CandidateVO;
import com.itgrids.partyanalyst.dto.GenericVO;
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

public String execute()
{

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


}