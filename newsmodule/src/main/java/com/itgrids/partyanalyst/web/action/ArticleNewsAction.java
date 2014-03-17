package com.itgrids.partyanalyst.web.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.json.JSONObject;

import com.itgrids.partyanalyst.dto.ArticleVO;
import com.itgrids.partyanalyst.dto.RegistrationVO;
import com.itgrids.partyanalyst.service.IArticleNewsService;
import com.opensymphony.xwork2.Action;

public class ArticleNewsAction implements ServletRequestAware{
	
	private static final Logger log = Logger.getLogger(ArticleNewsAction.class);
    private IArticleNewsService articleNewsService;
	private HttpServletRequest request;
	private HttpSession session;
	private JSONObject jObj;
	private ArticleVO savedDetails1,articleVO;
	
	
	
	
	public ArticleVO getSavedDetails1() {
		return savedDetails1;
	}
	public void setSavedDetails1(ArticleVO savedDetails1) {
		this.savedDetails1 = savedDetails1;
	}
	public IArticleNewsService getArticleNewsService() {
		return articleNewsService;
	}
	public void setArticleNewsService(IArticleNewsService articleNewsService) {
		this.articleNewsService = articleNewsService;
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
	public JSONObject getjObj() {
		return jObj;
	}
	public void setjObj(JSONObject jObj) {
		this.jObj = jObj;
	}
	
	
	@Override
	public void setServletRequest(HttpServletRequest request) {
		
		this.request = request;
		
	}
    public String execute(){
		
		return Action.SUCCESS;
	}
    public String getTotalArticleNews()
    {
 	 try{
 		 session = request.getSession();
 		 RegistrationVO regVO = (RegistrationVO)session.getAttribute("USER");
 		 if(regVO == null)
 		  return null;
 		 
 		 
 		 savedDetails1 = articleNewsService.getArticleNews();
 		 
 	 }catch (Exception e) {
 	  
 	  log.error(" Exception Occured in getArticleNews() method, Exception - ",e);
 	}
 	 return Action.SUCCESS;
    }
	
    
}