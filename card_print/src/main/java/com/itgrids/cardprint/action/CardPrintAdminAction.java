package com.itgrids.cardprint.action;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.json.JSONObject;

import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionSupport;

public class CardPrintAdminAction extends ActionSupport implements ServletRequestAware {
	
	private static final Logger LOG = Logger.getLogger(CardPrintAdminAction.class);
	private static final long serialVersionUID = 1L;
	
			private HttpServletRequest request;
			private HttpServletResponse response;
			private ServletContext context;
			private HttpSession session;
			private String task;
			private JSONObject jobj;

			public void setServletRequest(HttpServletRequest request) {
				this.request = request;
		
			}
			public void setServletResponse(HttpServletResponse response) {
				this.response = response;
				
			}
			public void setServletContext(ServletContext context) {
				this.context = context;
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
		
			public JSONObject getJobj() {
				return jobj;
			}
		
			public void setJobj(JSONObject jobj) {
				this.jobj = jobj;
			}
			
			public HttpSession getSession() {
				return session;
			}

			public void setSession(HttpSession session) {
				this.session = session;
			}
			
			public String execute()
			{
				session = request.getSession();
				return Action.SUCCESS;
			}

			
}
