package com.itgrids.electoralconnect.service;

import com.itgrids.electoralconnect.dto.QuickRequestVO;
import com.itgrids.electoralconnect.dto.RegistrationVO;
import com.itgrids.electoralconnect.dto.ResultStatus;
import com.itgrids.electoralconnect.dto.UserVO;



public interface IMailService {

	public ResultStatus sendQuickRequestEmailToAdmin(QuickRequestVO quickRequestVO,String requestFrom);
	 
	public ResultStatus sendMailToUserToRecoverPassword(RegistrationVO regVO , String requestFrom);
	 
	public  ResultStatus sendMailFromLocalHost(QuickRequestVO quickRequestVO);
	  
	public ResultStatus sendRegistrationNotification(RegistrationVO userVO,String requestFrom);
	  
	public  ResultStatus sendMailFromServer(QuickRequestVO quickRequestVO);
		
}
