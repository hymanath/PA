package com.itgrids.partyanalyst.web.action;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.util.ServletContextAware;

import com.opensymphony.xwork2.ActionSupport;

public class CandidateSearchAction 
	extends ActionSupport implements ServletRequestAware,ServletContextAware{	
		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;
		private static final Logger LOG = Logger.getLogger(CandidateSearchAction.class);
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
				
			LOG.info("In candidate search result action + execute method...........");
			//LOG.info("Name = "+request.getParameter("name"));
			LOG.info("Name = "+getName());
			LOG.info("party = "+getParty());
						
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

