package com.itgrids.partyanalyst.service;

import com.itgrids.partyanalyst.dto.QuickRequestVO;
import com.itgrids.partyanalyst.dto.RegistrationVO;
import com.itgrids.partyanalyst.dto.ResultStatus;

public interface IMailService {

	 public ResultStatus sendQuickRequestEmailToAdmin(QuickRequestVO quickRequestVO,String requestFrom);
	 
	 public ResultStatus sendMailToUserToRecoverPassword(RegistrationVO registrationVO,String requestFrom);
	 
	  public  ResultStatus sendMailFromLocalHost(QuickRequestVO quickRequestVO);
	  
	  public ResultStatus sendRegistrationNotification(RegistrationVO registrationVO,String requestFrom);
	  
	  public  ResultStatus sendMailFromServer(QuickRequestVO quickRequestVO);
	
		 
	 

}
