package com.itgrids.partyanalyst.web.action;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.util.ServletContextAware;

import com.opensymphony.xwork2.ActionSupport;

public class CandidateSearchAction 
	extends ActionSupport implements ServletRequestAware,ServletContextAware{	
		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;
		HttpServletRequest request;
		HttpServletResponse response;
		HttpSession session;
		
		private String name;		
		private String party;	
		public String getName() {
			return name;
		}
		public void setName(String name) {
			this.name = name;
		}		
		public String getParty() {
			return party;
		}
		public void setParty(String party) {
			this.party = party;
		}
		
		
		public String execute() {
				
			System.out.println("In candidate search result action + execute method...........");
			//System.out.println("Name = "+request.getParameter("name"));
			System.out.println("Name = "+getName());
			System.out.println("party = "+getParty());
						
			return SUCCESS;
			
		}
		
		
		public void setServletRequest(HttpServletRequest request) {
			// TODO Auto-generated method stub
			this.request=request;
			
		}
		public HttpServletRequest getRequest() {
			return request;
		}
		public void setServletContext(ServletContext arg0) {
			// TODO Auto-generated method stub
			
		}

	}

