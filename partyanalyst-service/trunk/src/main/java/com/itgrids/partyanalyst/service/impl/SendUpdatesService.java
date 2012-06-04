package com.itgrids.partyanalyst.service.impl;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import org.apache.log4j.Logger;

import com.itgrids.partyanalyst.dao.IAnanymousUserDAO;
import com.itgrids.partyanalyst.dao.hibernate.AnanymousUserDAO;
import com.itgrids.partyanalyst.dto.DataTransferVO;
import com.itgrids.partyanalyst.dto.RegistrationVO;
import com.itgrids.partyanalyst.service.ISendUpdatesService;
import com.itgrids.partyanalyst.service.ISmsService;

public class SendUpdatesService implements ISendUpdatesService{

	public static Logger log = Logger.getLogger(SendUpdatesService.class);
	
	private IAnanymousUserDAO ananymousUserDAO;
	private ISmsService smsCountrySmsService;
	
	private RegistrationVO registrationVO;
	
	public ISmsService getSmsCountrySmsService() {
		return smsCountrySmsService;
	}
	public void setSmsCountrySmsService(ISmsService smsCountrySmsService) {
		this.smsCountrySmsService = smsCountrySmsService;
	}
	public RegistrationVO getRegistrationVO() {
		return registrationVO;
	}
	public void setRegistrationVO(RegistrationVO registrationVO) {
		this.registrationVO = registrationVO;
	}
	public IAnanymousUserDAO getAnanymousUserDAO() {
		return ananymousUserDAO;
	}
	public void setAnanymousUserDAO(IAnanymousUserDAO ananymousUserDAO) {
		this.ananymousUserDAO = ananymousUserDAO;
	}
	
		public List<RegistrationVO> getAllUsersForSendSms()

		{
			log.debug("Entered into getAllUsersForSendSms() method of SendUpdatesService");
			List<RegistrationVO> regVO = new ArrayList<RegistrationVO>();
			RegistrationVO registrationVO = null;
			try
			{
				List<Object[]> userDetails = ananymousUserDAO.getAllUsersMobile();
				
				for(Object[] params : userDetails)
				{
					registrationVO = new RegistrationVO();
					registrationVO.setName(params[0].toString() + params[1].toString());
					registrationVO.setRegistrationID((Long)params[4]);
					registrationVO.setMobile(params[2].toString());
					registrationVO.setConstituency(params[3].toString());
					regVO.add(registrationVO);
				}
				
				return regVO;
			}
			catch(Exception e){
				log.error("Exception occured in getAllUsersForSendSms() of SendUpdatesService");
				e.printStackTrace();
			}
			return regVO;
		}
		public List<RegistrationVO> sendSmsForAllUsersFromAdmin(String message)
		{
			log.debug("Entered into this sendSmsForAllUsersFromAdmin() method of SendUpdatesService");
			List<RegistrationVO> smsDetails = new ArrayList<RegistrationVO>();
			
			RegistrationVO  registrationVO = null;
			try
			{
			List<Object> userDetails = ananymousUserDAO.getAllMobilenosAsUnique();
			
			String[] mobilenumbers = new String[userDetails.size()];
			
			for (int i = 0; i < userDetails.size(); i++)
			{
				
				
				mobilenumbers[i] = userDetails.get(i).toString();
				smsCountrySmsService.sendSmsFromAdmin(message, true,mobilenumbers[i] );
			}
			
			return smsDetails;
			
		}
			catch(Exception e)
			{
				e.printStackTrace();
			}
			return smsDetails;
	
		}
		

}
