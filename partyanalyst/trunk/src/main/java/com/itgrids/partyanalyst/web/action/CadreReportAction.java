package com.itgrids.partyanalyst.web.action;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.util.ServletContextAware;

import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionSupport;
import com.itgrids.partyanalyst.dto.UserCadresInfoVO;
import com.itgrids.partyanalyst.dto.RegistrationVO;
import com.itgrids.partyanalyst.service.impl.CadreManagementService;

public class CadreReportAction extends ActionSupport implements ServletContextAware,ServletRequestAware{


	private CadreManagementService cadreManagementService;
	private UserCadresInfoVO userCadresInfoVO = new UserCadresInfoVO();
	private HttpServletRequest request;
	private HttpSession session;
	public void setCadreManagementService(
			CadreManagementService cadreManagementService) {
		this.cadreManagementService = cadreManagementService;
	}

	public UserCadresInfoVO getUserCadresInfoVO() {
		return userCadresInfoVO;
	}


	public void setUserCadresInfoVO(UserCadresInfoVO userCadresInfoVO) {
		this.userCadresInfoVO = userCadresInfoVO;
	}

	
	public String execute() throws Exception{
		session=request.getSession();
		RegistrationVO user = (RegistrationVO) session.getAttribute("USER");
		if(user==null){
			userCadresInfoVO = new UserCadresInfoVO();
		}else{
			userCadresInfoVO.setUserID(user.getRegistrationID()); 
			userCadresInfoVO.setUserAccessType(user.getAccessType());
			userCadresInfoVO.setUserAccessValue(user.getAccessValue());
			if("MLA".equalsIgnoreCase(user.getAccessType())
					|| "MP".equalsIgnoreCase(user.getAccessType())){
				String constituencyName = cadreManagementService.getConstituencyName(new Long(user.getAccessValue()));
				userCadresInfoVO.setUserAccessDisplayValue(constituencyName);
			}else if("COUNTRY".equalsIgnoreCase(user.getAccessType())){
				String countryName = cadreManagementService.getCountryName(new Long(user.getAccessValue()));
				userCadresInfoVO.setUserAccessDisplayValue(countryName);
			}else if("STATE".equalsIgnoreCase(user.getAccessType())){
				String stateName = cadreManagementService.getStateName(new Long(user.getAccessValue()));
				userCadresInfoVO.setUserAccessDisplayValue(stateName);
			}else if("DISTRICT".equalsIgnoreCase(user.getAccessType())){
				String districtName = cadreManagementService.getDistrictName(new Long(user.getAccessValue()));
				userCadresInfoVO.setUserAccessDisplayValue(districtName);
			}else if("MANDAL".equalsIgnoreCase(user.getAccessType())){
				String mandalName = cadreManagementService.getMandalName(new Long(user.getAccessValue()));
				userCadresInfoVO.setUserAccessDisplayValue(mandalName);
			}
			userCadresInfoVO = cadreManagementService.getUserCadresInfo(userCadresInfoVO);
		}
		
		session.setAttribute("USERCADRESINFOVO", userCadresInfoVO);
		return Action.SUCCESS;
	}
	public void setServletContext(ServletContext arg0) {
		// TODO Auto-generated method stub
		
	}

	public void setServletRequest(HttpServletRequest arg0) {
		this.request = arg0;
		
	}
}
