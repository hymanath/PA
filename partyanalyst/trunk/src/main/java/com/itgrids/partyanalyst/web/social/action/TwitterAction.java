package com.itgrids.partyanalyst.web.social.action;

import java.text.ParseException;



import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;
import org.apache.struts2.util.ServletContextAware;
import org.json.JSONObject;

import com.itgrids.partyanalyst.dto.SocialNetworkVO;
import com.itgrids.partyanalyst.social.service.ISocialService;
import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionSupport;

public class TwitterAction extends ActionSupport implements
ServletRequestAware, ServletResponseAware, ServletContextAware  {
	
	private static final long serialVersionUID = -4119321096803350183L;
	private String task = null;
	//private List<SelectOptionVO> partyNames;
	private List<SocialNetworkVO> partyNamesList;
	
	//private List<SelectOptionVO> candidateNames;
	private HttpServletRequest request;
	private HttpSession session;
	private ISocialService socialService;
	JSONObject jObj = null;
	
	

	
	public String getTask() {
		return task;
	}
	public void setTask(String task) {
		this.task = task;
	}
	
	public ISocialService getSocialService() {
		return socialService;
	}
	public void setSocialService(ISocialService socialService) {
		this.socialService = socialService;
	}
	
	public void setServletRequest(HttpServletRequest request) {
		this.request = request;
		this.session = request.getSession();
	}
	
	public List<SocialNetworkVO> getPartyNamesList() {
		return partyNamesList;
	}
	public void setPartyNamesList(List<SocialNetworkVO> partyNamesList) {
		this.partyNamesList = partyNamesList;
	}
	public void setServletResponse(HttpServletResponse arg0) {

	}
	public void setServletContext(ServletContext arg0) {

	}
	public String execute() {
	
		/*List list=socialService.getNames();
				
		*/
		return Action.SUCCESS;
	}
	
	public String ajaxCallHandler()
	{
		String param = null;
		param = getTask();
		
		try {
			jObj = new JSONObject(param);
			System.out.println(jObj);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		if(jObj.getString("task").equalsIgnoreCase("getPartyNames"))
		{
			System.out.println("with in the action of twitter");
			partyNamesList = socialService.getPartyNames();
			System.out.println("partyNamesList value is:"+partyNamesList);
		}else if(jObj.getString("task").equalsIgnoreCase("getCandidateNames")){
			
				partyNamesList = socialService.getCandidateNames();
		}else if(jObj.getString("task").equalsIgnoreCase("getNames")){
			partyNamesList = socialService.getNames();
			}
	
		return Action.SUCCESS;
		
	}
	
	
	
	
}
	

