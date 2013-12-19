package com.itgrids.partyanalyst.web.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.struts2.interceptor.ServletRequestAware;

import com.itgrids.partyanalyst.dto.FileVO;
import com.itgrids.partyanalyst.dto.NewsActivityVO;
import com.itgrids.partyanalyst.dto.RegistrationVO;
import com.itgrids.partyanalyst.service.IReportService;
import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionSupport;

public class CreateReportAction extends ActionSupport implements ServletRequestAware {
   
	/**
	 * 
	 */
	private static final long serialVersionUID = -8200198030562751322L;
	private HttpServletRequest request;
	private Long reportId;
	private String key;
	private FileVO news;
	private NewsActivityVO report;
	
	private IReportService reportService;
	
	public void setServletRequest(HttpServletRequest request) {
		this.request = request;
	}
	
	public Long getReportId() {
		return reportId;
	}

	public void setReportId(Long reportId) {
		this.reportId = reportId;
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public IReportService getReportService() {
		return reportService;
	}

	public void setReportService(IReportService reportService) {
		this.reportService = reportService;
	}

	public FileVO getNews() {
		return news;
	}

	public void setNews(FileVO news) {
		this.news = news;
	}

	public NewsActivityVO getReport() {
		return report;
	}

	public void setReport(NewsActivityVO report) {
		this.report = report;
	}

	public String execute(){
		HttpSession session = request.getSession();
		RegistrationVO user = (RegistrationVO)session.getAttribute("USER"); 
		if(user == null && key == null){
			return "notLogged";
		}
		return Action.SUCCESS;
	}
	
	public String prepareReport(){
		HttpSession session = request.getSession();
		RegistrationVO user = (RegistrationVO)session.getAttribute("USER"); 
		Long userId = null;
		if(user != null){
			userId = user.getRegistrationID();
		}
		if(user == null && key == null){
			news = new FileVO();
			news.setName("Invalid User");
			return Action.SUCCESS;
		}
		 news = reportService.getReportData(reportId, userId, key);
		return Action.SUCCESS;
	}
	
	public String activitiesReports(){
		return Action.SUCCESS;
	}
	
	public String prepareActivitiesReport(){
		report = reportService.getActivitiesReportData(key);
		return Action.SUCCESS;
	}
}
