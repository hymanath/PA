package com.itgrids.partyanalyst.web.action;

import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.util.ServletContextAware;

import com.itgrids.partyanalyst.dto.CadreInfo;
import com.itgrids.partyanalyst.dto.RegistrationVO;
import com.itgrids.partyanalyst.dto.SelectOptionVO;
import com.itgrids.partyanalyst.service.impl.CadreManagementService;
import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.validator.annotations.RegexFieldValidator;
import com.opensymphony.xwork2.validator.annotations.RequiredStringValidator;
import com.opensymphony.xwork2.validator.annotations.StringLengthFieldValidator;
import com.opensymphony.xwork2.validator.annotations.ValidatorType;

public class CadreRegisterAction extends ActionSupport implements ServletRequestAware,ServletContextAware{
	
	private HttpServletRequest request;
	private HttpSession session;
	private ServletContext context;
	
	private CadreManagementService cadreManagementService;
	private final static Logger log = Logger.getLogger(CadreRegisterAction.class);
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
	private String constituency;
	private String mandal;
	private String village;
	private String cadreLevel;
	private String cadreLevelValue;
	private String mobile;
	private String email;
	
	
	public String getFirstName() {
		System.out.println("***********IN getter firstname of cadre register******"+firstName);	
		return cadreInfo.getFirstName();
	}
	@RequiredStringValidator(type = ValidatorType.FIELD, message = "Firstname is required",shortCircuit=true)
		public void setFirstName(String firstName) {
		System.out.println("***********IN setter firstname of cadre register******"+firstName);
		this.cadreInfo.setFirstName(firstName);
	}

	public String getMiddleName() {
		//System.out.println();
		return cadreInfo.getMiddleName();
	}

	public void setMiddleName(String middleName) {
		this.cadreInfo.setMiddleName(middleName);
	}

	public String getLastName() {
		return cadreInfo.getLastName();
	}
	@RequiredStringValidator(type = ValidatorType.FIELD, message = "Lastname is required",shortCircuit=true)
	public void setLastName(String lastName) {
		this.cadreInfo.setLastName(lastName);
	}

	public String getGender() {
		return cadreInfo.getGender();
	}
	@RequiredStringValidator(type = ValidatorType.FIELD, message = "please select Gender",shortCircuit=true)
	public void setGender(String gender) {
		this.cadreInfo.setGender(gender);
	}

	public String getState() {
		return cadreInfo.getState();
	}
	 @RequiredStringValidator(type = ValidatorType.FIELD, message = "please select State",shortCircuit=true)
	public void setState(String state) {
		this.cadreInfo.setState(state);
	}

	public String getDistrict() {
		return cadreInfo.getDistrict();
	}
	 @RequiredStringValidator(type = ValidatorType.FIELD, message = "please select District",shortCircuit=true)
	public void setDistrict(String district) {
		this.cadreInfo.setDistrict(district);
	}

	public String getConstituency() {
		return cadreInfo.getConstituencyID().toString();
	}
	 @RequiredStringValidator(type = ValidatorType.FIELD, message = "please select  Constituency",shortCircuit=true)
	public void setConstituency(String constituency) {
		 System.out.println("*** in seter of constituency method");
		this.cadreInfo.setConstituencyID(new Long(constituency));
	}
	public String getMandal() {
		return cadreInfo.getMandal();
	}
	 @RequiredStringValidator(type = ValidatorType.FIELD, message = "please select Mandal/Tehsil",shortCircuit=true)
	public void setMandal(String mandal) {
		this.cadreInfo.setMandal(mandal);
	}

	public String getVillage() {
		return cadreInfo.getVillage();
	}
	 @RequiredStringValidator(type = ValidatorType.FIELD, message = "please select Village",shortCircuit=true)
	public void setVillage(String village) {
		this.cadreInfo.setVillage(village);
	}

	public String getCadreLevel() {
		return cadreInfo.getCadreLevel().toString();
	}
	 @RequiredStringValidator(type = ValidatorType.FIELD, message = "please select CadreLevel",shortCircuit=true)
	public void setCadreLevel(String cadreLevel) {
		this.cadreInfo.setCadreLevel(new Long(cadreLevel));
	}

	public String getCadreLevelValue() {
		return cadreInfo.getCadreLevelValue().toString();
	}
	 @RequiredStringValidator(type = ValidatorType.FIELD, message = "please select CadreLevelValue",shortCircuit=true)
	public void setCadreLevelValue(String cadreLevelValue) {
		this.cadreInfo.setCadreLevelValue(new Long(cadreLevelValue));
	}

	public String getMobile() {
		return cadreInfo.getMobile();
	}
	@RequiredStringValidator(type = ValidatorType.FIELD, message = "Mobile number is required",shortCircuit=true)
	@StringLengthFieldValidator(type = ValidatorType.FIELD,message = "mobile number should be 10 digits", shortCircuit = true,  minLength = "10",  maxLength = "10")
	@RegexFieldValidator( type = ValidatorType.FIELD, expression = "^([9]{1})([02346789]{1})([0-9]{8})$", message="mobile number should contain digits",shortCircuit = true)
	public void setMobile(String mobile) {
		this.cadreInfo.setMobile(mobile);
	}

	public String getEmail() {
		return cadreInfo.getEmail();
	}

	public void setEmail(String email) {
		this.cadreInfo.setEmail(email);
	}

	public String execute() throws Exception{
		log.debug("In The Excecute For Cader");
		session=request.getSession();
		System.out.println(" in execute of cadre registration page");
		String name1=request.getParameter("firstName");
		System.out.println("the firstname is ....." +name1);
		String name2=request.getParameter("lastName");
		String name3=request.getParameter("constituency");
		System.out.println("the lastname is ....." +name2);
		System.out.println("the constituency is***"+name3);
		RegistrationVO regVO = (RegistrationVO)session.getAttribute("USER");
		cadreInfo.setuserID(regVO.getRegistrationID());
		if("MP".equals(regVO.getAccessType())){
			Long constituencyID = cadreInfo.getConstituencyID();
			List<SelectOptionVO> list = cadreManagementService.getStateDistricConstituencytByConstituencyID(constituencyID);
			SelectOptionVO obj = new SelectOptionVO();
			if(list.size()==3)
				obj = list.get(1);
			cadreInfo.setDistrict(obj.getId().toString());
		}
		
		if(cadreInfo != null){
			System.out.println(" cadre level :" + cadreInfo.getCadreLevel());
			System.out.println(" cadre level Val :" + cadreInfo.getCadreLevelValue());
		}
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
