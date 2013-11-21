package com.itgrids.partyanalyst.web.action;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.interceptor.ServletRequestAware;

import com.itgrids.partyanalyst.service.INewsAnalysisService;
import com.opensymphony.xwork2.ActionSupport;

public class NewsAnalysisAction extends ActionSupport implements ServletRequestAware {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5662347083673292646L;

	private HttpServletRequest request;
	
	private INewsAnalysisService newsAnalysisService;

	public void setServletRequest(HttpServletRequest request) {
		this.request = request;
	}

	public INewsAnalysisService getNewsAnalysisService() {
		return newsAnalysisService;
	}

	public void setNewsAnalysisService(INewsAnalysisService newsAnalysisService) {
		this.newsAnalysisService = newsAnalysisService;
	}
	
	
	
}
