package com.itgrids.partyanalyst.web.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.json.JSONObject;

import com.itgrids.partyanalyst.dto.BloodBankVO;
import com.itgrids.partyanalyst.service.IBloodBankService;
import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionSupport;

public class BloodBankAction extends ActionSupport implements ServletRequestAware{

	private static final Logger         		LOG = Logger.getLogger(BloodBankAction.class);

	private HttpServletRequest         			request;
	private HttpSession 						session;
	private IBloodBankService                   bloodBankService;
	private JSONObject							jObj;
	private String 								task;
	
	private List<BloodBankVO> bloodBankVOList;
	private BloodBankVO bloodBankVO;
	
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
	public String getTask() {
		return task;
	}
	public void setTask(String task) {
		this.task = task;
	}
	public void setServletRequest(HttpServletRequest request) {
		this.request = request;
	}
	
	public IBloodBankService getBloodBankService() {
		return bloodBankService;
	}
	public void setBloodBankService(IBloodBankService bloodBankService) {
		this.bloodBankService = bloodBankService;
	}
	
	public String bloodBankRegistration(){
		return Action.SUCCESS;
	}
	
	public String bloodBankDashBoard(){
		return Action.SUCCESS;
	}
	
	public String bloodBankBleading(){
		return Action.SUCCESS;
	}
	
	public List<BloodBankVO> getBloodBankVOList() {
		return bloodBankVOList;
	}
	public void setBloodBankVOList(List<BloodBankVO> bloodBankVOList) {
		this.bloodBankVOList = bloodBankVOList;
	}
	
	public BloodBankVO getBloodBankVO() {
		return bloodBankVO;
	}
	public void setBloodBankVO(BloodBankVO bloodBankVO) {
		this.bloodBankVO = bloodBankVO;
	}
	public String getOccuations(){
		try{
			bloodBankVOList=bloodBankService.getOccupationList();
		}catch(Exception e){
		 LOG.info("Error raised at getOccuations() in BloodBankAction",e);	
		}
		return Action.SUCCESS;
	}
	public String getEducationQualifications(){
		try {
         	bloodBankVOList=bloodBankService.getEducationalQualificationsList();
		} catch (Exception e) {
			 LOG.info("Error raised at getEducationsQualifications() in BloodBankAction",e);
		}
		return Action.SUCCESS;
	}
	public String getCadreDetails(){
		
		try {
			jObj= new JSONObject(getTask());	
			bloodBankVO=bloodBankService.getCadreDetails(jObj.getString("memberShipNo"));
		} catch (Exception e) {
			 LOG.info("Error raised at getCadreDetails() in BloodBankAction",e);
		}
		return Action.SUCCESS;
	}
}
