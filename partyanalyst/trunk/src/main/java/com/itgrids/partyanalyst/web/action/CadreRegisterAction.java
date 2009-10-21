package com.itgrids.partyanalyst.web.action;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.util.ServletContextAware;

import com.itgrids.partyanalyst.dto.CadreInfo;
import com.itgrids.partyanalyst.dto.RegistrationVO;
import com.itgrids.partyanalyst.service.impl.CadreManagementService;
import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionSupport;

public class CadreRegisterAction extends ActionSupport implements ServletRequestAware,ServletContextAware{
	
	private HttpServletRequest request;
	private HttpSession session;
	private ServletContext context;
	
	private CadreManagementService cadreManagementService;
	public void setCadreManagementService(
			CadreManagementService cadreManagementService) {
		this.cadreManagementService = cadreManagementService;
	}

	private CadreInfo cadreInfo = new CadreInfo();
	private String firstName;
	private String middleName;
	private String lastName;
	private String gender;
	private String state;
	private String district;
	private String mandal;
	private String village;
	private String cadreLevel;
	private String cadreLevelValue;
	private String mobile;
	private String email;
	
	
	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		cadreInfo.setFirstName(firstName);
	}

	public String getMiddleName() {
		return middleName;
	}

	public void setMiddleName(String middleName) {
		cadreInfo.setMiddleName(middleName);
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		cadreInfo.setLastName(lastName);
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		cadreInfo.setGender(gender);
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		cadreInfo.setState(state);
	}

	public String getDistrict() {
		return district;
	}

	public void setDistrict(String district) {
		cadreInfo.setDistrict(district);
	}

	public String getMandal() {
		return mandal;
	}

	public void setMandal(String mandal) {
		cadreInfo.setMandal(mandal);
	}

	public String getVillage() {
		return village;
	}

	public void setVillage(String village) {
		cadreInfo.setVillage(village);
	}

	public String getCadreLevel() {
		return cadreLevel;
	}

	public void setCadreLevel(String cadreLevel) {
		cadreInfo.setCadreLevel(new Long(cadreLevel));
	}

	public String getCadreLevelValue() {
		return cadreLevelValue;
	}

	public void setCadreLevelValue(String cadreLevelValue) {
		cadreInfo.setCadreLevelValue(new Long(cadreLevelValue));
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		cadreInfo.setMobile(mobile);
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		cadreInfo.setEmail(email);
	}

	public String execute() throws Exception{
		session=request.getSession();
		RegistrationVO regVO = (RegistrationVO)session.getAttribute("USER");
		cadreInfo.setuserID(regVO.getRegistrationID());
		Long id = cadreManagementService.saveCader(cadreInfo);
		String result = Action.SUCCESS;
		if(id==null)
			result = "fail";
		return Action.SUCCESS;
	}

	public void setServletRequest(HttpServletRequest req) {
		request =req;
		session = req.getSession();
		
	}

	public void setServletContext(ServletContext context) {
		this.context =context;
		
	}

}
