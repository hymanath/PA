package com.itgrids.partyanalyst.web.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.json.JSONObject;

import com.itgrids.partyanalyst.dto.CardPrintVO;
import com.itgrids.partyanalyst.service.ICardPrintService;
import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionSupport;

public class CardPrintAction extends ActionSupport implements ServletRequestAware{

	private final static Logger LOG = Logger.getLogger(FieldMonitoringAction.class);
	
	//instance variables
	private HttpServletRequest request;
	private HttpSession	session;
	private JSONObject jObj;
	private String task;
	
	
	//Attributes
	private ICardPrintService cardPrintService;
	private List<CardPrintVO> vendorList;
	
	
	//implementation methods
	public void setServletRequest(HttpServletRequest request) {
		this.request = request;
	}
	public HttpSession getSession() {
		return session;
	}
	public void setSession(HttpSession session) {
		this.session = session;
	}
	
	//setters and getters.
	public ICardPrintService getCardPrintService() {
		return cardPrintService;
	}
	public void setCardPrintService(ICardPrintService cardPrintService) {
		this.cardPrintService = cardPrintService;
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
	
	public List<CardPrintVO> getVendorList() {
		return vendorList;
	}
	public void setVendorList(List<CardPrintVO> vendorList) {
		this.vendorList = vendorList;
	}
	public String execute(){
		try{
			//jObj = new JSONObject(getTask());
			vendorList = cardPrintService.getVendorNames();
			vendorList.add(0, new CardPrintVO(0l, "ALL"));
			
		}catch(Exception e){
			LOG.error("Exception raised in cardPrintDashboard() in CardPrintAction",e);
		}
		return Action.SUCCESS;
	}
	public String cardPrintDashboard() {
			try{
			//jObj = new JSONObject(getTask());
			vendorList = cardPrintService.getVendorNames();
			vendorList.add(0, new CardPrintVO(0l, "ALL"));
			
		}catch(Exception e){
			LOG.error("Exception raised in cardPrintDashboard() in CardPrintAction",e);
		}
			return Action.SUCCESS;	
	}
	public String getConstencyList(){
		try{
			jObj = new JSONObject(getTask());
			vendorList = cardPrintService.getConstListByVendor(jObj.getLong("vendorId"),jObj.getLong("districtId"));
		}catch(Exception e){
			LOG.error("Exception raised in getConstencyList() in CardPrintAction ",e);
		}
		return Action.SUCCESS;
	}
	public String getDistrictList(){
		try{
			jObj = new JSONObject(getTask());
			vendorList = cardPrintService.getDstrListByVendor(jObj.getLong("vendorId"));
		}catch(Exception e){
			LOG.error("Exception raised in getDistrictList() in CardPrintAction ",e);
		}
		return Action.SUCCESS;
	}
}
