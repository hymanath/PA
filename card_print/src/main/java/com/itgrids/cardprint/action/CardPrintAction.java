package com.itgrids.cardprint.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.json.JSONObject;

import com.itgrids.cardprint.dto.BasicVO;
import com.itgrids.cardprint.service.ICardPrintService;
import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionSupport;

public class CardPrintAction extends ActionSupport implements ServletRequestAware{
	
	private static final Logger LOG = Logger.getLogger(CardPrintAction.class);
	
	//Member Variables
	private HttpServletRequest request;
	private String task;
	private JSONObject jobj;
	private List<BasicVO> basicVOList;
	
	//Attributes
	private ICardPrintService cardPrintService;
	
	//setters and getters
	public HttpServletRequest getRequest() {
		return request;
	}
	public void setRequest(HttpServletRequest request) {
		this.request = request;
	}
	
	public String getTask() {
		return task;
	}
	public void setTask(String task) {
		this.task = task;
	}
	public JSONObject getJobj() {
		return jobj;
	}
	public void setJobj(JSONObject jobj) {
		this.jobj = jobj;
	}
	
	public void setCardPrintService(ICardPrintService cardPrintService) {
		this.cardPrintService = cardPrintService;
	}
	
	public List<BasicVO> getBasicVOList() {
		return basicVOList;
	}
	public void setBasicVOList(List<BasicVO> basicVOList) {
		this.basicVOList = basicVOList;
	}
	//Implementation Methods
	public void setServletRequest(HttpServletRequest request) {
		this.request = request;
	}
	
	
	//Request Handling Methods
	public String  getAllVendors(){
		try{
			basicVOList = cardPrintService.getAllVendors();
			
		}catch(Exception e){
			LOG.error("Exception Occurred At getAllVendors() in CardPrintAction class",e) ;
		}
		return Action.SUCCESS;
	}
	
	public String  getAllPrintStatus(){
		try{
			basicVOList = cardPrintService.getAllPrintStatus();
			
		}catch(Exception e){
			LOG.error("Exception Occurred At getAllPrintStatus() in CardPrintAction class",e) ;
		}
		return Action.SUCCESS;
	}
}
