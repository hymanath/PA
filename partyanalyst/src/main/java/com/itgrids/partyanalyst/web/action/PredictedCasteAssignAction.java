package com.itgrids.partyanalyst.web.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.json.JSONObject;

import com.itgrids.partyanalyst.dto.RegistrationVO;
import com.itgrids.partyanalyst.dto.ResultStatus;
import com.itgrids.partyanalyst.dto.SelectOptionVO;
import com.itgrids.partyanalyst.excel.booth.VoterVO;
import com.itgrids.partyanalyst.service.ICastePredictionService;
import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionSupport;

public class PredictedCasteAssignAction extends ActionSupport implements ServletRequestAware{
	
	private static final long serialVersionUID = 7126714305674261144L;
	private static final Logger log = Logger.getLogger(PredictedCasteAssignAction.class);
	
	private HttpServletRequest request;
	private ICastePredictionService castePredictionService;
	private ResultStatus resultStatus;
	private List<VoterVO> casteCountList;
	private HttpSession session;
	private List<SelectOptionVO> predictionCasteList;
	private String task;
	JSONObject jObj;
	
	public List<SelectOptionVO> getPredictionCasteList() {
		return predictionCasteList;
	}
	public void setPredictionCasteList(List<SelectOptionVO> predictionCasteList) {
		this.predictionCasteList = predictionCasteList;
	}
	public ResultStatus getResultStatus() {
		return resultStatus;
	}
	public void setResultStatus(ResultStatus resultStatus) {
		this.resultStatus = resultStatus;
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
	public JSONObject getjObj() {
		return jObj;
	}
	public void setjObj(JSONObject jObj) {
		this.jObj = jObj;
	}
	public void setServletRequest(HttpServletRequest arg0) {
		
		this.request=arg0;
	}
	
	public ICastePredictionService getCastePredictionService() {
		return castePredictionService;
	}
	public void setCastePredictionService(
			ICastePredictionService castePredictionService) {
		this.castePredictionService = castePredictionService;
	}
	
	public List<VoterVO> getCasteCountList() {
		return casteCountList;
	}
	public void setCasteCountList(List<VoterVO> casteCountList) {
		this.casteCountList = casteCountList;
	}
	public String execute()
	{
		try{
			predictionCasteList = castePredictionService.getPredictedCasteList();
			return Action.SUCCESS;
			
		}catch(Exception e)
		{
			log.error("Exception is - ",e);
			return Action.SUCCESS;
		}
	}
	
	public String getCasteCount()
	{
		try{
			jObj = new JSONObject(getTask());
			session = request.getSession();
			RegistrationVO regVo = (RegistrationVO) session.getAttribute("USER");
			casteCountList = castePredictionService.getCountForCaste(regVo.getRegistrationID());
		}
		catch(Exception e)
		{
			log.error("Exception Occured in getCasteCount() method in PredictedCasteAssignAction ", e);
		}

		return Action.SUCCESS;
	}
	
	
	public ResultStatus insertPredictedCaste()
	{
		resultStatus = new ResultStatus();
		try{
			
			jObj = new JSONObject(getTask());
			session = request.getSession();
			RegistrationVO regVo = (RegistrationVO) session.getAttribute("USER");
			if(regVo == null)
				return null;
			
			Long casteStateId = jObj.getLong("casteStateId");
			Integer firstRecord = jObj.getInt("firstRecord");
			Integer maxRecords = jObj.getInt("maxRecords");
			
			resultStatus = castePredictionService.insertPredictedCasteIntoMainCasteTables(regVo.getRegistrationID(),casteStateId,firstRecord,maxRecords);
			return resultStatus;
		}
		catch(Exception e)
		{
			log.error("Exception Occured in getCasteCount() method in PredictedCasteAssignAction ", e);
			return resultStatus;
		}

	}
	
	

}
