package com.itgrids.partyanalyst.web.action;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.struts2.interceptor.ServletRequestAware;
import org.jfree.util.Log;
import org.json.JSONObject;

import com.itgrids.partyanalyst.dto.HamletsAndBoothsVO;
import com.itgrids.partyanalyst.dto.MandalVO;
import com.itgrids.partyanalyst.dto.ProblemBeanVO;
import com.itgrids.partyanalyst.dto.RegistrationVO;
import com.itgrids.partyanalyst.dto.SelectOptionVO;
import com.itgrids.partyanalyst.service.IProblemManagementReportService;
import com.itgrids.partyanalyst.service.IRegionServiceData;
import com.itgrids.partyanalyst.service.IStaticDataService;
import com.itgrids.partyanalyst.service.impl.RegionServiceDataImp;
import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionSupport;


public class ProblemManagementReportAction extends ActionSupport implements
		ServletRequestAware {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -500281545950721654L;
	private IProblemManagementReportService problemManagementReportService;
	private HttpServletRequest request;
	private HttpSession session;
	private List<ProblemBeanVO> problemBean;
	private String task = null;
	JSONObject jObj = null;
	private IStaticDataService staticDataService;
	private List<SelectOptionVO> result;
	private List<MandalVO> mandals;
	private HamletsAndBoothsVO hamletsAndBoothsVO; 
	private IRegionServiceData regionServiceDataImp;
	private Long locationId = null;
	private String status=null;
	private String taskType=null;
	public String getTaskType() {
		return taskType;
	}
	public void setTaskType(String taskType) {
		this.taskType = taskType;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	
	public Long getLocationId() {
		return locationId;
	}
	public void setLocationId(Long locationId) {
		this.locationId = locationId;
	}
	public IRegionServiceData getRegionServiceDataImp() {
		return regionServiceDataImp;
	}
	public void setRegionServiceDataImp(IRegionServiceData regionServiceDataImp) {
		this.regionServiceDataImp = regionServiceDataImp;
	}
	
	public List<ProblemBeanVO> getProblemBean() {
		return problemBean;
	}
	public void setProblemBean(List<ProblemBeanVO> problemBean) {
		this.problemBean = problemBean;
	}
	public void setServletRequest(HttpServletRequest request) {
		this.request = request;
		this.session = request.getSession();
	}
	public IProblemManagementReportService getProblemManagementReportService() {
		return problemManagementReportService;
	}

	public void setProblemManagementReportService(
			IProblemManagementReportService problemManagementReportService) {
		this.problemManagementReportService = problemManagementReportService;
	}
	public String getTask() {
		return task;
	}

	public void setTask(String task) {
		this.task = task;
	}
	public IStaticDataService getStaticDataService() {
		return staticDataService;
	}

	public void setStaticDataService(IStaticDataService staticDataService) {
		this.staticDataService = staticDataService;
	}
	public void setResult(List<SelectOptionVO> result) {
		this.result = result;
	}
	public List<SelectOptionVO> getResult() {
		return result;
	}
	public List<MandalVO> getMandals() {
		return mandals;
	}

	public void setMandals(List<MandalVO> mandals) {
		this.mandals = mandals;
	}
	public HamletsAndBoothsVO getHamletsAndBoothsVO() {
		return hamletsAndBoothsVO;
	}

	public void setHamletsAndBoothsVO(HamletsAndBoothsVO hamletsAndBoothsVO) {
		this.hamletsAndBoothsVO = hamletsAndBoothsVO;
	}

	public String execute() throws Exception
	{	
		session=request.getSession();
		RegistrationVO user = (RegistrationVO) session.getAttribute("USER");
		if(user==null)
			return ERROR;
		if(task != null){
			try{
				jObj = new JSONObject(getTask());
				System.out.println("Result From JSON:"+jObj);
			}catch(Exception e){
				e.printStackTrace();
			}
			if(jObj.getString("task").equals("getStates")){
				result = new ArrayList<SelectOptionVO>(2);
				result.add(0,new SelectOptionVO(0l,"Select State"));
				result.add(1,new SelectOptionVO(1l,"Andra Pradesh"));
			}
			if(jObj.getString("task").equals("getDistricts")){
				result = staticDataService.getDistricts(new Long(jObj.getString("locationId")));
				result.add(0,new SelectOptionVO(0l,"Select District"));
			}
			else if(jObj.getString("task").equals("getConstituencies")){
					result = regionServiceDataImp.getConstituenciesByDistrictID(new Long(jObj.getString("locationId")));
				result.add(0,new SelectOptionVO(0l,"Select Constituencies"));
			}
			else if(jObj.getString("task").equals("getMandals")){
				result = regionServiceDataImp.getMandalsByConstituencyID(new Long(jObj.getLong("locationId")));
				result.add(0,new SelectOptionVO(0l,"Select Mandal"));
			}	
			else if(jObj.getString("task").equals("getTowhships")){
				result = staticDataService.findTownshipsByTehsilID(new Long(jObj.getLong("locationId")));
				result.add(0,new SelectOptionVO(0l,"Select Mandal"));
			}
			else if(jObj.getString("task").equals("getVillages")){
				result = staticDataService.getHamletsForTownship(new Long(jObj.getLong("locationId")));
				result.add(0,new SelectOptionVO(0l,"Select Hamlet"));
			}
			else if(jObj.getString("task").equals("findVoters")){
				taskType =  jObj.getString("taskType");
				locationId = new Long(jObj.getLong("locationId"));
				status = findTaskType(taskType);
				problemBean = problemManagementReportService.getHamletProblemsInfo(new Long(locationId), user.getRegistrationID(),status);
			}
			else if(jObj.getString("task").equals("findTownshipVoters")){
				taskType =  jObj.getString("taskType");
				locationId = new Long(jObj.getLong("locationId"));
				status = findTaskType(taskType);
				problemBean = problemManagementReportService.getTehsilProblemsInfo(new Long(locationId), user.getRegistrationID(),status);
			}
			else if(jObj.getString("task").equals("findConstituencyVoters")){
				taskType =  jObj.getString("taskType");
				locationId = new Long(jObj.getLong("locationId"));
				status = findTaskType(taskType);
				problemBean = problemManagementReportService.getConstituencyProblemsInfo(new Long(locationId), user.getRegistrationID(),status);
			}
		}
		return SUCCESS;    
	}	
	
	public String findTaskType(String taskType){
		if(taskType.equalsIgnoreCase("new")){
			return "new";
		}
		else if(taskType.equalsIgnoreCase("classify")){
			return "classify";
		}
		else if(taskType.equalsIgnoreCase("assigned")){
			return "assigned";
		}
		else if(taskType.equalsIgnoreCase("progress")){
			return "progress";
		}
		else if(taskType.equalsIgnoreCase("pending")){
			return "pending";
		}
		else if(taskType.equalsIgnoreCase("fixed")){
			return "fixed";
		}
		else if(taskType.equalsIgnoreCase("deo")){
			return "deo";
		}
		else if(taskType.equalsIgnoreCase("collectorate")){
			return "collectorate";
		}
		else if(taskType.equalsIgnoreCase("mro")){
			return "mro";
		}
		else if(taskType.equalsIgnoreCase("VILLAGE SECRETARY")){
			return "VILLAGE SECRETARY";
		}
		else{
		return "";
		}
	}
}	
		

