package com.itgrids.partyanalyst.web.action;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.apache.log4j.Logger;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.json.JSONObject;
import com.itgrids.partyanalyst.dto.ProblemBeanVO;
import com.itgrids.partyanalyst.dto.ProblemManagementDataVO;
import com.itgrids.partyanalyst.dto.RegistrationVO;
import com.itgrids.partyanalyst.dto.SelectOptionVO;
import com.itgrids.partyanalyst.service.IProblemManagementService;
import com.itgrids.partyanalyst.utils.IConstants;
import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionSupport;


public class ProblemManagementAction extends ActionSupport implements ServletRequestAware{
	
	private final static Logger log = Logger.getLogger(ProblemManagementAction.class);
	
	private static final long serialVersionUID = 1L;
	
	private List<SelectOptionVO> problemSources;
	private HttpServletRequest request;
	private HttpSession session;
	private String task = null;
	JSONObject jObj = null;
	IProblemManagementService problemManagementService;
	private ProblemManagementDataVO problemManagementDataVO;

	public void setServletRequest(HttpServletRequest request) {
		this.request = request;
		this.session = request.getSession();
	}

	public List<SelectOptionVO> getProblemSources() {
		return problemSources;
	}

	public void setProblemSources(List<SelectOptionVO> problemSources) {
		this.problemSources = problemSources;
	}
	
	public String getTask() {
		return task;
	}

	public void setTask(String task) {
		this.task = task;
	}
	
	public IProblemManagementService getProblemManagementService() {
		return problemManagementService;
	}

	public void setProblemManagementService(
			IProblemManagementService problemManagementService) {
		this.problemManagementService = problemManagementService;
	}
	
	public ProblemManagementDataVO getProblemManagementDataVO() {
		return problemManagementDataVO;
	}

	public void setProblemManagementDataVO(
			ProblemManagementDataVO problemManagementDataVO) {
		this.problemManagementDataVO = problemManagementDataVO;
	}

	public String execute() throws Exception{
	problemManagementDataVO = new ProblemManagementDataVO();
	ProblemBeanVO problemBeanVO = new ProblemBeanVO();
	
	session = request.getSession();
	RegistrationVO user = (RegistrationVO) session.getAttribute("USER");
	
	if(user==null)
		return ERROR;
		
	String param = null;
	param = getTask();
	
	try {
		jObj = new JSONObject(param);
		System.out.println(jObj);
	} catch (ParseException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}	
	log.debug("Task::"+jObj.getString("task"));
	if(jObj.getString("task").equalsIgnoreCase("addNewProblem"))
	{
	problemBeanVO = new ProblemBeanVO();
	problemBeanVO.setUserID(user.getRegistrationID());
	problemBeanVO.setProblem(jObj.getString("problem"));
	problemBeanVO.setDescription(jObj.getString("description"));
	problemBeanVO.setState(jObj.getString("state"));
	problemBeanVO.setDistrict(jObj.getString("district"));
	problemBeanVO.setConstituency(jObj.getString("constituency"));
	problemBeanVO.setTehsil(jObj.getString("tehsil"));
	problemBeanVO.setVillage(jObj.getString("village"));
	problemBeanVO.setHamlet(jObj.getString("hamlet"));
	problemBeanVO.setReportedDate(jObj.getString("reportedDate"));
	problemBeanVO.setExistingFrom(jObj.getString("existingFrom"));
	problemBeanVO.setProbSource(jObj.getString("probSource"));
	problemBeanVO.setName(jObj.getString("name"));
	problemBeanVO.setMobile(jObj.getString("mobile"));
	problemBeanVO.setPhone(jObj.getString("phone"));
	problemBeanVO.setEmail(jObj.getString("email"));
	problemBeanVO.setAddress(jObj.getString("address"));
	problemBeanVO.setYear(IConstants.PRESENT_YEAR);
	
	
	 log.debug(problemBeanVO.getProblem());
	 log.debug(problemBeanVO.getDescription());
	 log.debug(problemBeanVO.getState());
	 log.debug(problemBeanVO.getDistrict());
	 log.debug(problemBeanVO.getConstituency());
	 log.debug(problemBeanVO.getTehsil());
	 log.debug(problemBeanVO.getHamlet());
	 log.debug(problemBeanVO.getReportedDate());
	 log.debug(problemBeanVO.getExistingFrom());
	 log.debug(problemBeanVO.getProbSource());
	 log.debug(problemBeanVO.getName());
	 log.debug(problemBeanVO.getMobile());
	 log.debug(problemBeanVO.getPhone());
	 log.debug(problemBeanVO.getEmail());
	 log.debug(problemBeanVO.getAddress());
	 System.out.println("RAGHU::::::::::::::::::::::::::::::::::::::"); 
	 problemManagementDataVO.setProblemBeanVO(problemManagementService.saveProblemData(problemBeanVO));
	} if(jObj.getString("task").equalsIgnoreCase("newProblemsByUserID"))
	{
		problemManagementDataVO.setNewProblems(problemManagementService.getProblemsForUser(user.getRegistrationID()));
	} 
	 	return Action.SUCCESS;
	}
	
	
}
