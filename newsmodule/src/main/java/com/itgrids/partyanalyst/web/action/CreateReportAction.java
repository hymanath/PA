package com.itgrids.partyanalyst.web.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.json.JSONObject;

import com.itgrids.partyanalyst.dto.FileVO;
import com.itgrids.partyanalyst.dto.NewsActivityVO;
import com.itgrids.partyanalyst.dto.RegistrationVO;
import com.itgrids.partyanalyst.dto.ResultStatus;
import com.itgrids.partyanalyst.service.IReportService;
import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionSupport;

public class CreateReportAction extends ActionSupport implements ServletRequestAware {
   
	/**
	 * 
	 */
	private static final long serialVersionUID = -8200198030562751322L;
	private static final org.apache.log4j.Logger LOG = Logger.getLogger(CreateReportAction.class);
	private HttpServletRequest request;
	private Long reportId;
	private String key;
	private FileVO news;
	private NewsActivityVO report;
	private boolean forPdf;
	private Long delaySeconds;
	private HttpSession session;
	private ResultStatus resultStatus;
	private JSONObject jObj;
	private String task;
	
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
	public ResultStatus getResultStatus() {
		return resultStatus;
	}

	public void setResultStatus(ResultStatus resultStatus) {
		this.resultStatus = resultStatus;
	}

	public String getTask() {
		return task;
	}

	public void setTask(String task) {
		this.task = task;
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

	public boolean isForPdf() {
		return forPdf;
	}

	public void setForPdf(boolean forPdf) {
		this.forPdf = forPdf;
	}

	public Long getDelaySeconds() {
		return delaySeconds;
	}

	public void setDelaySeconds(Long delaySeconds) {
		this.delaySeconds = delaySeconds;
	}

	public String execute(){
		HttpSession session = request.getSession();
		RegistrationVO user = (RegistrationVO)session.getAttribute("USER");
		if(user == null && key != null){
			forPdf = true;
		}
		if(user == null && key == null){
			return "notLogged";
		}
		if(forPdf){
		  news = reportService.getReportData(reportId,null, key);
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
	
	public String addDelay(){
	   try{
		Thread.sleep(delaySeconds*1000);
	   }catch(Exception e){
		   LOG.error("Exception rised in addDelay",e);
	   }
		return Action.SUCCESS;
	}
	public String deleteReportFromFile()
	   {
		   try{
			     jObj =new JSONObject(getTask());
				 session = request.getSession();
				  RegistrationVO regVo = (RegistrationVO) session.getAttribute("USER");
				  if(regVo == null)
					  return Action.ERROR;
				   Long userId = regVo.getRegistrationID();  
				 
				   resultStatus = reportService.deleteReportNews(jObj.getLong("reportFilesId"),userId);  
		   }
		   catch (Exception e) {
			e.printStackTrace();
		}
		return Action.SUCCESS;
	   }
}
