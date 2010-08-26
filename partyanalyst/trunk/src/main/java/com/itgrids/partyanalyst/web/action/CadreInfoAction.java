package com.itgrids.partyanalyst.web.action;

import java.text.ParseException;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.interceptor.ServletRequestAware;
import org.json.JSONObject;

import com.itgrids.partyanalyst.dto.CadreInfo;
import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionSupport;

public class CadreInfoAction extends ActionSupport implements ServletRequestAware{

	private HttpServletRequest request;
	private String cadreId;
	private String task;
	JSONObject jObj;
	private CadreInfo cadreInfo; 
	
	public CadreInfo getCadreInfo() {
		return cadreInfo;
	}


	public void setCadreInfo(CadreInfo cadreInfo) {
		this.cadreInfo = cadreInfo;
	}


	public String getTask() {
		return task;
	}


	public void setTask(String task) {
		this.task = task;
	}


	public String getCadreId() {
		return cadreId;
	}


	public void setCadreId(String cadreId) {
		this.cadreId = cadreId;
	}


	public void setServletRequest(HttpServletRequest request) {
		this.request = request;
		
	}
	
	
	public String execute() throws Exception
	{
		
		return SUCCESS;
	}
	
	public String getCadreInfoByCadreId()
	{
		String param = null;
		param = getTask();
		
		try {
			jObj = new JSONObject(param);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		cadreInfo = getDummyCadreInfo();
		return Action.SUCCESS;
	}
	
	private CadreInfo getDummyCadreInfo()
	{
		CadreInfo cInfo = new CadreInfo();
		cInfo.setCadreID(new Long(1));
		cInfo.setFirstName("Sai Krishna");
		cInfo.setMiddleName("");
		cInfo.setLastName("Basetti");
		cInfo.setMobile("9989876597");
		cInfo.setLandLineNo("2556688");
		cInfo.setEmail("sai.basetti@gmail.com");
		cInfo.setGender("Male");
		cInfo.setDateOfBirth("14-02-1985");
		cInfo.setStrCadreLevel("district");
		cInfo.setStrCadreLevelValue("Nellore");
		
		cInfo.setHouseNo("25");
		cInfo.setStreet("Bank Colony");
		cInfo.setPinCode("500025");
		cInfo.setState("Andhra Pradesh");
		
		return cInfo;
	}
	
}
