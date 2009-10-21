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
		try{
			System.out.println("Narender CadreReportAction Action Action Action Action Action Action1111111111111111111111");
			session=request.getSession();
			RegistrationVO user = (RegistrationVO) session.getAttribute("USER");
			userCadresInfoVO.setUserID(user.getRegistrationID()); 
			userCadresInfoVO.setUserAccessType(user.getAccessType());
			userCadresInfoVO.setUserAccessValue(user.getAccessValue());
			System.out.println("Narender CadreReportAction Action Action Action Action Action Action22222222222222222222222222222");
			userCadresInfoVO = cadreManagementService.getUserCadresInfo(userCadresInfoVO);
			System.out.println("Narender CadreReportAction Action Action Action Action Action Action....................................");
			System.out.println(userCadresInfoVO.getRegionLevelCadres().size());
		}catch (Exception e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
		}finally{
			System.out.println("Narender CadreReportAction End");
			return Action.SUCCESS;			
		}
	}
	public void setServletContext(ServletContext arg0) {
		// TODO Auto-generated method stub
		
	}

	public void setServletRequest(HttpServletRequest arg0) {
		this.request = arg0;
		
	}
	

}
