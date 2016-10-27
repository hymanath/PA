package com.itgrids.partyanalyst.web.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.json.JSONObject;

import com.itgrids.partyanalyst.dto.CadreTabRecordsStatusVO;
import com.itgrids.partyanalyst.service.IDataReconsolidationService;
import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionSupport;

public class DataReconsolidationOverViewAction extends ActionSupport implements ServletRequestAware {
	private static final long serialVersionUID = 1L;
	private final static Logger LOG = Logger.getLogger(DataReconsolidationOverViewAction.class);

	  private HttpServletRequest request;
	  private JSONObject jObj;
	  private String task;
	  
	  private IDataReconsolidationService dataReconsolidationService;
	  private List<CadreTabRecordsStatusVO>  cadreTabRecordsStatusVOList;
	  private  CadreTabRecordsStatusVO finalvo;
	  
	  
	  public List<CadreTabRecordsStatusVO> getCadreTabRecordsStatusVOList() {
		return cadreTabRecordsStatusVOList;
	}

	public void setCadreTabRecordsStatusVOList(
			List<CadreTabRecordsStatusVO> cadreTabRecordsStatusVOList) {
		this.cadreTabRecordsStatusVOList = cadreTabRecordsStatusVOList;
	}

	public void setServletRequest(HttpServletRequest request) {
			this.request = request;
		}
	  
	  public HttpServletRequest getRequest() {
		return request;
	  }
	  
		public void setRequest(HttpServletRequest request) {
			this.request = request;
		}
		public JSONObject getjObj() {
			return jObj;
		}
		
		public void setjObj(JSONObject jObj) {
			this.jObj = jObj;
		}
		public String getTask() {
			return task;
		}

		public void setTask(String task) {
			this.task = task;
		}
		public String execute(){
				return Action.SUCCESS;
		}
		
		public IDataReconsolidationService getDataReconsolidationService() {
			return dataReconsolidationService;
		}
		public void setDataReconsolidationService(
				IDataReconsolidationService dataReconsolidationService) {
			this.dataReconsolidationService = dataReconsolidationService;
		}



		public CadreTabRecordsStatusVO getFinalvo() {
				return finalvo;
			}
		public void setFinalvo(CadreTabRecordsStatusVO finalvo) {
				this.finalvo = finalvo;
		}
		
	  public String getDataReConsalationOverViewAction(){
		  try {
			
			jObj = new JSONObject(getTask());
			
			Long constistuencyId = jObj.getLong("constistuencyId");
			String fromDateStr = jObj.getString("fromDate");
			String toDateStr = jObj.getString("toDate");
			
			cadreTabRecordsStatusVOList = dataReconsolidationService.dataReConsalationOverView(constistuencyId,fromDateStr,toDateStr);
			
		} catch (Exception e) {
			LOG.error("Exception raised at dataReConsalationOverView()  of DataReconsolidationOverViewAction", e);
		}
		return Action.SUCCESS;
	}
	public String dataReConsalationTotalOverView(){
		try {
			
			jObj = new JSONObject(getTask());
			
			Long constistuencyId = jObj.getLong("constistuencyId");
			String fromDateStr = jObj.getString("fromDate");
			String toDateStr = jObj.getString("toDate");
			
			finalvo = dataReconsolidationService.dataReConsalationTotalOverView(constistuencyId,fromDateStr,toDateStr);
			
		} catch (Exception e) {
			LOG.error("Exception raised at dataReConsalationTotalOverView()  of DataReconsolidationOverViewAction", e);
		}
		return Action.SUCCESS;
	}
	
	public String getCadreSurveyUserWiseRegistrations(){
		try{
			LOG.info("Entered into DataReconsolidationOverViewAction of getCadreSurveyUserWiseRegistrations ()");
			jObj = new JSONObject(getTask());
			Long cadreSrveyUserId = jObj.getLong("cadreSurveyUserId");
			Long constituencyId = jObj.getLong("constituencyId");
			String startDate = jObj.getString("strtDate");
			String toDate =jObj.getString("endDate");
			cadreTabRecordsStatusVOList = dataReconsolidationService.getCadreSurveyUserWiseRegistrations(cadreSrveyUserId,constituencyId,startDate,toDate);
			
		}catch(Exception e){
			LOG.error("Exception Occured into DataReconsolidationOverViewAction of getCadreSurveyUserWiseRegistrations ()",e);
		}
		return Action.SUCCESS;
	}
	
}
