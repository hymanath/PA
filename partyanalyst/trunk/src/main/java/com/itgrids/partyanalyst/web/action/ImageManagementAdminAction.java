package com.itgrids.partyanalyst.web.action;

import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.web.context.ServletContextAware;

import com.itgrids.partyanalyst.dao.hibernate.ProblemFileDAO;
import com.itgrids.partyanalyst.dto.FileVO;
import com.itgrids.partyanalyst.service.IProblemManagementService;
import com.itgrids.partyanalyst.service.impl.ProblemManagementService;
import com.opensymphony.xwork2.ActionSupport;

public class ImageManagementAdminAction extends ActionSupport implements
		ServletRequestAware, ServletContextAware {

	private static final long serialVersionUID = 1L;
	private static final Logger log = Logger
			.getLogger(ProblemManagementAdminAction.class);

	private HttpServletRequest request;
	private HttpSession session;
	private String task = null;
	JSONObject jObj = null;
	private ServletContext context;
	private List<FileVO> result;
     private IProblemManagementService problemManagementService;

     
	
	public JSONObject getJObj() {
		return jObj;
	}

	public void setJObj(JSONObject obj) {
		jObj = obj;
	}

	public List<FileVO> getResult() {
		return result;
	}

	public void setResult(List<FileVO> result) {
		this.result = result;
	}

	public IProblemManagementService getProblemManagementService() {
		return problemManagementService;
	}

	public void setProblemManagementService(
			IProblemManagementService problemManagementService) {
		this.problemManagementService = problemManagementService;
	}
	
	
	public HttpServletRequest getRequest() {
		return request;
	}

	public void setRequest(HttpServletRequest request) {
		this.request = request;
	}

	public HttpSession getSession() {
		return session;
	}

	public void setSession(HttpSession session) {
		this.session = session;
	}

	public String getTask() {
		return task;
	}

	public void setTask(String task) {
		this.task = task;
	}

	

	public ServletContext getContext() {
		return context;
	}

	public void setContext(ServletContext context) {
		this.context = context;
	}

	public void setServletContext(ServletContext context) {
		this.context = context;
	}

	public void setServletRequest(HttpServletRequest request) {
		this.request = request;
	}

	public String execute() throws Exception {
		if(task != null){
			try{
				jObj = new JSONObject(getTask());
				
				if(jObj.getString("task").equals("getImage")){	
		result=problemManagementService.getImageDetails();
		
				}
				else if(jObj.getString("task").equals("performDeletionOrAcceptenceImage")){	
					JSONArray selectedProblemFileIds = jObj.getJSONArray("selectedProblemFileIds");
					Integer problemFilesIds[] = new Integer[selectedProblemFileIds.length()];					
					for(int i=0; i<selectedProblemFileIds.length(); i++){
						problemFilesIds[i] = (Integer)selectedProblemFileIds.get(i);
					}
					if(jObj.get("choice").equals("accept")){						
						problemManagementService.acceptSelectedImagesByAdmin(problemFilesIds);
					}			
					else{						
						problemManagementService.deleteSelectedImagesByAdmin(problemFilesIds);
					}
					}
				}catch(Exception e){
					e.printStackTrace();
				}
		}
		return SUCCESS;
	}

}
