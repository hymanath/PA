package com.itgrids.electoralconnect.web.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.struts2.interceptor.ServletRequestAware;

import com.itgrids.electoralconnect.dto.RegistrationVO;
import com.itgrids.electoralconnect.service.IUserService;
import com.itgrids.partyanalyst.dto.ResultStatus;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.validator.annotations.RequiredStringValidator;
import com.opensymphony.xwork2.validator.annotations.ValidatorType;

@SuppressWarnings("serial")
public class ChangePasswordAction extends ActionSupport implements ServletRequestAware{

	private HttpServletRequest request;
	private String orginalpassword,newPassword,conformPassword;
	private IUserService userService;
	private String status;
	@Override
	public void setServletRequest(HttpServletRequest request) {
		this.request = request;
	}

	
	public String getOrginalpassword() {
		return orginalpassword;
	}

	@RequiredStringValidator(type = ValidatorType.FIELD, message = "Password is Mandatory")
	public void setOrginalpassword(String orginalpassword) {
		this.orginalpassword = orginalpassword;
	}


	public String getNewPassword() {
		return newPassword;
	}

	@RequiredStringValidator(type = ValidatorType.FIELD, message = "New Password is Mandatory")
	public void setNewPassword(String newPassword) {
		this.newPassword = newPassword;
	}

	public String getConformPassword() {
		return conformPassword;
	}
	
	@RequiredStringValidator(type = ValidatorType.FIELD, message = "Conform Password is Mandatory")
	public void setConformPassword(String conformPassword) {
		this.conformPassword = conformPassword;
	}
	
	
	public IUserService getUserService() {
		return userService;
	}

	public void setUserService(IUserService userService) {
		this.userService = userService;
	}

	
	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String execute()
	{
		HttpSession session = request.getSession();
		RegistrationVO regVO = (RegistrationVO) session.getAttribute("USER");
		Long userId = regVO.getRegistrationID();
		if(orginalpassword.equals(regVO.getPassword()) && conformPassword.equals(newPassword))
		{
			ResultStatus resultStatus = userService.updateUserPassword(newPassword, userId);
			if(resultStatus.getResultCode() == 0)
			{
				status = "success";
			}
			else
			{
				status = "failure";
				return ERROR;
			}
		}
		else
		{
			status = "failure";
			return ERROR;
		}
		
		return SUCCESS;
	}

	public void validate()
	{
		/*HttpSession session = request.getSession();
		RegistrationVO regVO = (RegistrationVO) session.getAttribute("USER");
		if(orginalpassword != null  && orginalpassword.trim().length() > 0  )
		{
			addFieldError("orginalpassword", "Password Is Mandatory");
		}
		else if(newPassword != null  && newPassword.trim().length() > 0)
		{
			addFieldError("newPassword","New Password Is Mandatory");
		}
		else if(conformPassword != null && conformPassword.trim().length() > 0)
		{
			addFieldError("conformPassword","Conform Password Is Mandatory");
		}
		else if(!newPassword.trim().equals(conformPassword.trim()))
		{
			addFieldError("conformPassword","New Password and Conform Password Should be same");
		}
		else if(!orginalpassword.trim().equals(regVO.getPassword().trim()))
		{
			addFieldError("orginalpassword","Wrong Password");
		}*/
	}
	
}
