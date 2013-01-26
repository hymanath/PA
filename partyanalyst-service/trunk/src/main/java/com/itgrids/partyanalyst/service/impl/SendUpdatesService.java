package com.itgrids.partyanalyst.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.mail.internet.MimeMessage;

import org.apache.log4j.Logger;
import org.apache.velocity.app.VelocityEngine;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.ui.velocity.VelocityEngineUtils;

import com.itgrids.partyanalyst.dao.IUserDAO;
import com.itgrids.partyanalyst.dao.IUserRolesDAO;
import com.itgrids.partyanalyst.dto.RegistrationVO;
import com.itgrids.partyanalyst.dto.SelectOptionVO;
import com.itgrids.partyanalyst.service.IMailService;
import com.itgrids.partyanalyst.service.ISendUpdatesService;
import com.itgrids.partyanalyst.service.ISmsService;
import com.itgrids.partyanalyst.utils.IConstants;

public class SendUpdatesService implements ISendUpdatesService{

	public static Logger log = Logger.getLogger(SendUpdatesService.class);
	
	private ISmsService smsCountrySmsService;
	private IUserDAO userDAO;
	private IUserRolesDAO userRolesDAO;
	private List<Object> userRoles;
	private VelocityEngine  velocityEngine;
	private IMailService mailService;
	private String templateVM;
	
	public VelocityEngine getVelocityEngine() {
		return velocityEngine;
	}
	public void setVelocityEngine(VelocityEngine velocityEngine) {
		this.velocityEngine = velocityEngine;
	}
	public IMailService getMailService() {
		return mailService;
	}
	public void setMailService(IMailService mailService) {
		this.mailService = mailService;
	}
	public String getTemplateVM() {
		return templateVM;
	}
	public void setTemplateVM(String templateVM) {
		this.templateVM = templateVM;
	}
	public List<Object> getUserRoles() {
		return userRoles;
	}
	public void setUserRoles(List<Object> userRoles) {
		this.userRoles = userRoles;
	}
	public IUserRolesDAO getUserRolesDAO() {
		return userRolesDAO;
	}
	public void setUserRolesDAO(IUserRolesDAO userRolesDAO) {
		this.userRolesDAO = userRolesDAO;
	}
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

	public IUserDAO getUserDAO() {
		return userDAO;
	}
	public void setUserDAO(IUserDAO userDAO) {
		this.userDAO = userDAO;
	}
	
	
	
	public List<RegistrationVO> getAllUsersForSendSms()

	{
		log.debug("Entered into getAllUsersForSendSms() method of SendUpdatesService");
		List<RegistrationVO> regVO = new ArrayList<RegistrationVO>();
		RegistrationVO registrationVO = null;
		try
		{
			List<Object[]> userDetails = userRolesDAO.getAllFreeusertoSendSms();
			
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
			List<Object> userDetails = userRolesDAO.getAllMobilenosAsUnique();
			
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
		
				
	public String sendEmailsFromAdminToUsers(final List<String> mailIds,final String mailMessage,final String subject){
		try{
			
			for(final String mailId:mailIds){
				final SelectOptionVO selectOptionVO = new SelectOptionVO();
				selectOptionVO.setValue(mailMessage);
				final String subjectone =subject;
				JavaMailSenderImpl javamailsender = new JavaMailSenderImpl();
				javamailsender.setSession(mailService.getSessionObject(IConstants.DEFAULT_MAIL_SERVER));
				MimeMessagePreparator preparator = new MimeMessagePreparator() {
	         public void prepare(MimeMessage mimeMessage) throws Exception {
	        	 String  emailIdOfUser;
	            MimeMessageHelper message = new MimeMessageHelper(mimeMessage);
	            	message.setTo(mailId);
	            Map model = new HashMap();
	   		    model.put("content",selectOptionVO);
	            String text = VelocityEngineUtils.mergeTemplateIntoString(
	 	               velocityEngine, templateVM, model);
	            message.setText(text, true);
	            message.setSubject(subjectone);
	            message.setFrom(IConstants.FROMEMAILID);
	         }
	      };
	      javamailsender.send(preparator);	
	}
	      return "success";
	}catch(Exception e){
		log.error("Exception Rised in sendMail : ", e);
		return "failure";
	}
		}

	
	public List<RegistrationVO>  getUsersForSendingEmails(Long selectedState,Long selectedDistrict,Long selectedConstituency,Long userType,Long locationScope){
		log.debug("Entered into getAllUsersForSendSms() method of SendUpdatesService");
		List<RegistrationVO> regVOForCus= new ArrayList<RegistrationVO>();
		RegistrationVO registrationVO = null;
		try
		{
			List<Object[]> userDetails =null;
			userDetails=userRolesDAO.getUsersForSendingEmails(selectedState,selectedDistrict,selectedConstituency,userType,locationScope);
			if(userDetails!=null)
			for(Object[] params : userDetails)
			{
				registrationVO = new RegistrationVO();
				
				 if(params[0]!=null)
					 registrationVO.setRegistrationID((Long)params[0]);
				 if(params[1]!=null && params[2]!=null)
					 registrationVO.setName(params[1].toString() +" "+ params[2].toString()); 
				 if(params[3]!=null)
					 registrationVO.setEmail(params[3].toString());
				 if(params[4]!=null)
					 registrationVO.setState(params[4].toString());
				 if(params[5]!=null)
					 registrationVO.setDistrict(params[5].toString());
				 if(params[6]!=null)
					 registrationVO.setConstituency(params[6].toString());
				 
				 if(registrationVO!=null)
					 regVOForCus.add(registrationVO);
				
			}
			
			return regVOForCus;
		}
		catch(Exception e){
			log.error("Exception occured in getUsersForSendingEmails() in sendUpdateService ",e);
			//e.printStackTrace();
		}
		
		
		return regVOForCus;
		
		
	}
		
}
