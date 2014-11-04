package com.itgrids.partyanalyst.dao.hibernate;

import org.appfuse.dao.BaseDaoTestCase;

import com.itgrids.partyanalyst.dao.ILoginDetailsByTabDAO;
import com.itgrids.partyanalyst.dto.LoginResponceVO;
import com.itgrids.partyanalyst.webservice.android.abstractservice.IWebServiceHandlerService1;
import com.itgrids.partyanalyst.webservice.android.concreteservice.WebServiceHandlerService1;
import com.itgrids.partyanalyst.webserviceutils.android.utilvos.UserLoginUtils;

public class LoginDetailsByTabDAOHibernateTest extends BaseDaoTestCase{
	private ILoginDetailsByTabDAO loginDetailsByTabDAO;
	
	public ILoginDetailsByTabDAO getLoginDetailsByTabDAO() {
		return loginDetailsByTabDAO;
	}

	public void setLoginDetailsByTabDAO(ILoginDetailsByTabDAO loginDetailsByTabDAO) {
		this.loginDetailsByTabDAO = loginDetailsByTabDAO;
	}

	public void test(){
		WebServiceHandlerService1 webServiceHandlerService1=new WebServiceHandlerService1();
		webServiceHandlerService1.setLoginDetailsByTabDAO(loginDetailsByTabDAO);
		
		UserLoginUtils userLoginUtils=new UserLoginUtils();
		userLoginUtils.setUserName("sreedhar");
		userLoginUtils.setPassWord("seedi");
		
		LoginResponceVO loginResponceVO=new LoginResponceVO();
		loginResponceVO.setStatusMsg("success");
		
		webServiceHandlerService1.savingUserDetailsWhoLoggedIn(userLoginUtils,loginResponceVO);
		
	}
  

}
