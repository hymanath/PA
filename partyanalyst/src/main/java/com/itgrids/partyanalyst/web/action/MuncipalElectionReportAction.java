package com.itgrids.partyanalyst.web.action;

import java.text.ParseException;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.util.ServletContextAware;
import org.json.JSONObject;

import com.itgrids.partyanalyst.dto.MandalAllElectionDetailsVO;
import com.itgrids.partyanalyst.dto.ResultCodeMapper;
import com.itgrids.partyanalyst.service.IStaticDataService;
import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionSupport;

public class MuncipalElectionReportAction extends ActionSupport implements
		ServletRequestAware, ServletContextAware {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static final Logger log = Logger.getLogger(MuncipalElectionReportAction.class);
	private ServletContext context;
	private HttpSession session;
	private HttpServletRequest request;
	private String task = null;
	JSONObject jObj = null;
	private Long muncipalityId,muncipalityElectionId;
	private String muncipalityElectionType,electionYear,name;
	private IStaticDataService staticDataService;
	private MandalAllElectionDetailsVO mandalAllElectionDetails;
	private String dataAvailabilityFlag;
	
	public MandalAllElectionDetailsVO getMandalAllElectionDetails() {
		return mandalAllElectionDetails;
	}

	public void setMandalAllElectionDetails(
			MandalAllElectionDetailsVO mandalAllElectionDetails) {
		this.mandalAllElectionDetails = mandalAllElectionDetails;
	}

	public IStaticDataService getStaticDataService() {
		return staticDataService;
	}

	public void setStaticDataService(IStaticDataService staticDataService) {
		this.staticDataService = staticDataService;
	}

	public void setServletRequest(HttpServletRequest request) {
		this.request = request;			
	}

	public String getElectionYear() {
		return electionYear;
	}

	public void setElectionYear(String electionYear) {
		this.electionYear = electionYear;
	}

	public HttpServletRequest getRequest() {
		return request;
	}

	public void setRequest(HttpServletRequest request) {
		this.request = request;
	}
	public String getTask() {
		return task;
	}

	public void setTask(String task) {
		this.task = task;
	}

	public String getDataAvailabilityFlag() {
		return dataAvailabilityFlag;
	}

	public void setDataAvailabilityFlag(String dataAvailabilityFlag) {
		this.dataAvailabilityFlag = dataAvailabilityFlag;
	}

	public JSONObject getJObj() {
		return jObj;
	}

	public Long getMuncipalityId() {
		return muncipalityId;
	}

	public void setMuncipalityId(Long muncipalityId) {
		this.muncipalityId = muncipalityId;
	}

	public Long getMuncipalityElectionId() {
		return muncipalityElectionId;
	}

	public void setMuncipalityElectionId(Long muncipalityElectionId) {
		this.muncipalityElectionId = muncipalityElectionId;
	}

	public String getMuncipalityElectionType() {
		return muncipalityElectionType;
	}

	public void setMuncipalityElectionType(String muncipalityElectionType) {
		this.muncipalityElectionType = muncipalityElectionType;
	}

	public void setJObj(JSONObject obj) {
		jObj = obj;
	}	
	
	public ServletContext getContext() {
		return context;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public HttpSession getSession() {
		return session;
	}

	public void setSession(HttpSession session) {
		this.session = session;
	}
	
	public void setServletContext(ServletContext context) {
		this.context = context;
		
	}

	public String execute () throws Exception 
	{
		return Action.SUCCESS;		
	}
	
	public String getCandidateWiseMuncipalitiesElectionTrends(){
		if(task != null){
			try {
				jObj = new JSONObject(getTask());
				System.out.println(jObj);
			} catch (ParseException e) {
				e.printStackTrace();
			}	
			log.debug("Task::"+jObj.getString("task"));
		
			if(jObj.getString("task").equalsIgnoreCase("getAllCandidates")||jObj.getString("task").equalsIgnoreCase("getWinners")||jObj.getString("task").equalsIgnoreCase("allCandidates")||jObj.getString("task").equalsIgnoreCase("getParties"))
			{
				try{		
					mandalAllElectionDetails = staticDataService.getAllMuncipalElectionDetails(new Long(jObj.getString("muncipalityId")),jObj.getString("candidateDetailsType"),new Long(jObj.getString("partyId")),jObj.getString("electionType"));
					if(mandalAllElectionDetails.getResultStatus().getResultCode()!=ResultCodeMapper.FAILURE){
						dataAvailabilityFlag = "Data Not Available.";
					}
				}catch(Exception e){
					log.debug("No data is available...");
				}
			}
		}
		return Action.SUCCESS;	
  }
}
