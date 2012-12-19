package com.itgrids.partyanalyst.service.impl;

import java.util.HashMap;
import java.util.Map;

import javax.mail.internet.MimeMessage;

import org.apache.velocity.app.VelocityEngine;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.ui.velocity.VelocityEngineUtils;

import com.itgrids.partyanalyst.dao.IUserSocialNetworkSiteDAO;
import com.itgrids.partyanalyst.dto.ResultCodeMapper;
import com.itgrids.partyanalyst.dto.ResultStatus;
import com.itgrids.partyanalyst.dto.SocialNetworkVO;
import com.itgrids.partyanalyst.model.UserSocialNetworkSite;
import com.itgrids.partyanalyst.service.IMailService;
import com.itgrids.partyanalyst.service.IUserSocialNetworkSiteService;
import com.itgrids.partyanalyst.utils.IConstants;

public class UserSocialNetworkSiteService implements IUserSocialNetworkSiteService{
	
	private IUserSocialNetworkSiteDAO userSocialNetworkSiteDAO;
	private VelocityEngine  velocityEngine;
	//private JavaMailSenderImpl mailSender;
	private IMailService mailService;
	private String templateVM;
	
	
	
	
	

	public IUserSocialNetworkSiteDAO getUserSocialNetworkSiteDAO() {
		return userSocialNetworkSiteDAO;
	}

	public void setUserSocialNetworkSiteDAO(
			IUserSocialNetworkSiteDAO userSocialNetworkSiteDAO) {
		this.userSocialNetworkSiteDAO = userSocialNetworkSiteDAO;
	}

	public String saveUserSocialNetworkSiteInfo(String partyName,String candidateName,String twitterId,String yourName,String emailId){
		  
		try{
			
			
			UserSocialNetworkSite userSocialNetworkSite=new UserSocialNetworkSite();
			if(partyName!=null && partyName.trim().length()>0)
			userSocialNetworkSite.setPartyName(partyName);
			if(candidateName!=null && candidateName.trim().length()>0)
			userSocialNetworkSite.setCandidateName(candidateName);
			if(twitterId!=null && twitterId.trim().length()>0)
			userSocialNetworkSite.setTwitterProfileId(twitterId);
			if(yourName!=null && yourName.trim().length()>0)
			userSocialNetworkSite.setUserName(yourName);
			if(emailId!=null && emailId.trim().length()>0)
			userSocialNetworkSite.setUserEmailId(emailId);
			if(userSocialNetworkSite!=null)
			userSocialNetworkSiteDAO.save(userSocialNetworkSite);
			sendTestMail(partyName,candidateName,twitterId,yourName,emailId);
			
		}
		
		catch (Exception e) {
			
			e.printStackTrace();
		}
		return "success";
	}
	

public void sendTestMail(String partyName,String candidateName,String twitterId,String yourName,String emailId){
		
	final SocialNetworkVO socialNetworkVO = new SocialNetworkVO();
	if(partyName!=null && partyName.trim().length()>0){
	socialNetworkVO.setPartyOrCandidateName(partyName);
	}
	else if(candidateName!=null && candidateName.trim().length()>0){
		socialNetworkVO.setPartyOrCandidateName(candidateName);
	}
	if(twitterId!=null && twitterId.trim().length()>0)
	socialNetworkVO.setTwitterId(twitterId);
	if(yourName!=null && yourName.trim().length()>0)
	socialNetworkVO.setYourName(yourName);
	if(emailId!=null && emailId.trim().length()>0)
	socialNetworkVO.setEmailId(emailId);	
	
		
		JavaMailSenderImpl javamailsender = new JavaMailSenderImpl();
		javamailsender.setSession(mailService.getSessionObject(IConstants.LOCALHOST));
		//final String username=javamailsender.getUsername();


		MimeMessagePreparator preparator = new MimeMessagePreparator() {
	         public void prepare(MimeMessage mimeMessage) throws Exception {
	            MimeMessageHelper message = new MimeMessageHelper(mimeMessage);
	            message.setTo("info@partyanalyst.com");
	            Map model = new HashMap();
	   		    model.put("user", socialNetworkVO);
	            String text = VelocityEngineUtils.mergeTemplateIntoString(
	 	               velocityEngine, templateVM, model);
	            message.setText(text, true);
	            message.setSubject("This is subject");
	            message.setFrom("partyanalyst04@gmail.com");
	         }
	      };
	      javamailsender.send(preparator);	
	}

	public VelocityEngine getVelocityEngine() {
		return velocityEngine;
	}
	
	public void setVelocityEngine(VelocityEngine velocityEngine) {
		this.velocityEngine = velocityEngine;
	}
	
	public String getTemplateVM() {
		return templateVM;
	}
	
	public void setTemplateVM(String templateVM) {
		this.templateVM = templateVM;
	}

	public IMailService getMailService() {
		return mailService;
	}

	public void setMailService(IMailService mailService) {
		this.mailService = mailService;
	}

	
	


}
