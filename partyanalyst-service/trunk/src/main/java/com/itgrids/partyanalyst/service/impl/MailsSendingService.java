package com.itgrids.partyanalyst.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.itgrids.partyanalyst.dao.IAnanymousUserDAO;
import com.itgrids.partyanalyst.dto.EmailDetailsVO;
import com.itgrids.partyanalyst.dto.ResultCodeMapper;
import com.itgrids.partyanalyst.dto.ResultStatus;
import com.itgrids.partyanalyst.service.IMailService;
import com.itgrids.partyanalyst.service.IMailsSendingService;
import com.itgrids.partyanalyst.service.IMailsTemplateService;
import com.itgrids.partyanalyst.utils.IConstants;

public class MailsSendingService implements IMailsSendingService{
	
	private static final Logger log = Logger.getLogger(MailsSendingService.class);
	private IMailService mailService;
	private IMailsTemplateService mailsTemplateService;
	
	private IAnanymousUserDAO ananymousUserDAO;
	
	public IAnanymousUserDAO getAnanymousUserDAO() {
		return ananymousUserDAO;
	}
	public void setAnanymousUserDAO(IAnanymousUserDAO ananymousUserDAO) {
		this.ananymousUserDAO = ananymousUserDAO;
	}
	
	public IMailsTemplateService getMailsTemplateService() {
		return mailsTemplateService;
	}
	public void setMailsTemplateService(IMailsTemplateService mailsTemplateService) {
		this.mailsTemplateService = mailsTemplateService;
	}
	public IMailService getMailService() {
		return mailService;
	}
	public void setMailService(IMailService mailService) {
		this.mailService = mailService;
	}
	
	public ResultStatus sendEmailFriendRequest(EmailDetailsVO emailDetailsVO)
	{
		ResultStatus resultStatus = new ResultStatus();
		try
		{
			if(emailDetailsVO == null)
			{
				resultStatus.setResultCode(ResultCodeMapper.FAILURE);
				log.error("EmailDetailsVO is Null");
				return resultStatus;
			}
				
			String subject = ""+emailDetailsVO.getSenderName()+" sent a Friend Request in PartyAnalyst.com";
			String content = " <div style='border:1px solid #CCCCCC'>"+mailsTemplateService.getHeader()+"<br/><div style='margin-left:26px;margin-top:20px;margin-bottom: 7px;'><b>Hai "+emailDetailsVO.getFromAddress()+",</b><br/></div><div style='margin-left: 45px; margin-bottom: 39px;line-height:1.5em;'><b><font style='color:blue;'>"+emailDetailsVO.getSenderName()+"</font></b> has sent you a friend request in PartyAnalyst.com. <br/>  To respond " +
					"to friend request, <a href='http://www.partyanalyst.com/loginInputAction.action'><b>Login here.</b></a></div></div>" ;
			emailDetailsVO.setSubject(subject);
			emailDetailsVO.setContent(content);
			resultStatus = mailService.sendEmail(emailDetailsVO);
			
			resultStatus.setResultCode(ResultCodeMapper.SUCCESS);
			return resultStatus;
		}catch (Exception e) {
			resultStatus.setResultCode(ResultCodeMapper.FAILURE);
			log.error("Exception Occured in sendEmailFriendRequest() Method, Exception is  - "+e );
			return resultStatus;
		}
	}
	
	public ResultStatus acceptEmailFriendRequest(EmailDetailsVO emailDetailsVO)
	{
		ResultStatus resultStatus = new ResultStatus();
		try
		{
			if(emailDetailsVO == null)
			{
				resultStatus.setResultCode(ResultCodeMapper.FAILURE);
				log.error("EmailDetailsVO is Null");
				return resultStatus;
			}
			
			String subject = ""+emailDetailsVO.getSenderName()+" accepted Friend Request in PartyAnalyst.com."; 
			String content = "<div style = 'border:1px solid #CCCCCC'>"+mailsTemplateService.getHeader()+"<br/><div style='margin-left:26px;margin-top:20px;margin-bottom: 7px;'><b>Hai "+emailDetailsVO.getFromAddress()+",</b></div>" +
					"<div style='margin-left: 45px; margin-bottom: 40px;line-height: 1.5em;'><font style='color:blue'><b>"+emailDetailsVO.getSenderName()+"</b></font> has accepted your friend request in PartyAnalyst.com.<br/>Do you want to send a message? " +
							"<a href='http://www.partyanalyst.com/loginInputAction.action'><b> Login Here</b> </a></div></div>";
			
			emailDetailsVO.setContent(content);
			emailDetailsVO.setSubject(subject);
			resultStatus = mailService.sendEmail(emailDetailsVO);
			return resultStatus;
		}catch (Exception e) {
			resultStatus.setResultCode(ResultCodeMapper.FAILURE);
			log.error("Exception Occured in acceptEmailFriendRequest() Method, Exception is  - "+e );
			return resultStatus;
		}
	}
	
	public ResultStatus sendMessageToConnectUser(EmailDetailsVO emailDetailsVO)
	{
		ResultStatus resultStatus = new ResultStatus();
		try
		{
			if(emailDetailsVO == null)
			{
				resultStatus.setResultCode(ResultCodeMapper.FAILURE);
				log.error("EmailDetailsVO is Null");
				return resultStatus;
			}
			
			String subject = ""+emailDetailsVO.getSenderName()+" send a message in PartyAnalyst.com.";
			String content = "<div style='border:1px solid #CCCCCC;'>"+mailsTemplateService.getHeader()+"<br/>" +
					"<div style='margin-left:26px;margin-top:20px;margin-bottom: 7px;'><b>Hai "+emailDetailsVO.getFromAddress()+",</b><br/>" +
							"</div><div style='margin-left: 45px; margin-bottom: 40px;line-height: 1.5em;'><font style='color:blue;'><b>"+emailDetailsVO.getSenderName()+"</b></font> has sent a message in PartyAnalyst.com.<br/> Do you want to send the reply? <a href='http://www.partyanalyst.com/loginInputAction.action'>Login Here</a></div></div>";
			emailDetailsVO.setContent(content);
			emailDetailsVO.setSubject(subject);
			resultStatus = mailService.sendEmail(emailDetailsVO);
			return resultStatus;
			
		}catch (Exception e) {
			resultStatus.setResultCode(ResultCodeMapper.FAILURE);
			log.error("Exception Occured in sendMessageToConnectUser() Method, Exception is  - "+e );
			return resultStatus;
		}
	}
	public ResultStatus sendMailsToPasswordnotUpdatedusers()
	{
		List<EmailDetailsVO> mainEmailDetailsVoList = new ArrayList<EmailDetailsVO>(0);
		ResultStatus resultStatus = new ResultStatus();
		try{			
			log.info("Enetered into the sendMailsToPasswordnotUpdatedusers method");
			
			String subject="Change Your Password - PartyAnalyst";

			List<Object[]> usersLst = ananymousUserDAO.getPasswordNotUpdatdUsersList();
	
			if(usersLst != null && usersLst.size()>0)
			{
				for (Object[] userDetails : usersLst)
				{
					try{
						
						String content = "<div style = 'background-color:#EFFFFF;width:600px;border:1px solid #CCCCCC'>"+mailsTemplateService.getHeader()+"<br/><div style='margin-left:26px;margin-top:20px;margin-bottom: 7px;'></div>" +
								"<div style='margin-left: 45px; margin-bottom: 40px;line-height: 1.5em;'> ";				
						       content+="Dear <font style='color:#1155CC;'><b>"+userDetails[0].toString()+"</b>,</font><br>";
						       
						       content+="You registered with <b>PartyAnalyst</b> on "+userDetails[2].toString()+".Still you didn't change your password.Presently your password is systems generated password.<br><br>";
						       content+="     Please change your password and stay connected with <b>PartyAnalyst</b> to get more updates from your <b>Friends, Constituency, District, Political Parties and Politicians</b>.<br>";
						       
						       
						       content+="<br>To change your password <b><a href='http://www.partyanalyst.com/loginInputAction.action?'>Login Here</a><b><br>";
						       
						       content+="<br>UserName:<b>&nbsp;"+userDetails[3].toString()+"</b><br>";
						       content+="Password:<b>&nbsp;"+userDetails[4].toString()+"</b><br></div></div>";
					
				
					
					EmailDetailsVO emailDetailsVO = new EmailDetailsVO();
					
					emailDetailsVO.setSubject(subject);
					emailDetailsVO.setToAddress(userDetails[5].toString());
					emailDetailsVO.setContent(content);				
				
					mainEmailDetailsVoList.add(emailDetailsVO);
					}catch (Exception ex) {}
				}						
				mailService.sendEmails(mainEmailDetailsVoList);
				resultStatus.setResultCode(ResultCodeMapper.SUCCESS);
			}
			return resultStatus;
	}
	catch(Exception e){		
		log.info("Exception raised in the sendMailsToPasswordnotUpdatedusers method - "+e);
		resultStatus.setResultCode(ResultCodeMapper.FAILURE);
		return resultStatus;
	}
		
	}
	
	public ResultStatus acceptEmailForAnalyzeConstituency(EmailDetailsVO emailDetailsVO)
	{
		ResultStatus resultStatus = new ResultStatus();
		try
		{
			if(emailDetailsVO == null)
			{
				log.error("Error Occured in acceptEmailForAnalyzeConstituency() of mailSending Service");
				resultStatus.setResultCode(ResultCodeMapper.FAILURE);
				return resultStatus;
			}
			String subject = "Partyanalyst.com";
			String content = "<div style='border:1px solid #CCCCCC;'>"+mailsTemplateService.getHeader()+"<br/>" +
					"<div style='margin-left:26px;margin-top:20px;margin-bottom: 7px;color:blue;'><b>Hai "+emailDetailsVO.getFromAddress()+",</b><br/>" +
							"</div><div style='margin-left: 45px; margin-bottom: 40px;line-height: 1.5em;'><font><b> Your Political Reason for <b>" +emailDetailsVO.getPartyStrength()+ " the candidate " +emailDetailsVO.getCandidateName()+ " from " +emailDetailsVO.getConstituencyName()+ " " +emailDetailsVO.getElectionType()+  " Constituency Added Successfully . We will send your comment to Admin for approval.</b></font> <br/></div></div>";
			emailDetailsVO.setContent(content);
			emailDetailsVO.setSubject(subject);
			//emailDetailsVO.setContent(content);
			resultStatus = mailService.sendEmail(emailDetailsVO);
			resultStatus.setResultCode(ResultCodeMapper.SUCCESS);
		
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		return resultStatus;

}
	
	public void acceptEmailForUserComments(EmailDetailsVO emailDetailsVO)
	{
		
		try
		{
			if(emailDetailsVO != null)
			{
				String subject = "Partyanalyst.com";
				String content ="<div style='border:1px solid #CCCCCC;'>"+mailsTemplateService.getHeader()+"<br/>" +
						"<div style='margin-left:26px;margin-top:20px;margin-bottom: 7px;color:blue;'><b>Hai "+emailDetailsVO.getFromAddress()+",</b><br/>" +
						"</div><div style='margin-left: 45px; margin-bottom: 40px;line-height: 1.5em;'><font><b> your comment for the " +emailDetailsVO.getCandidateName()+ " " +emailDetailsVO.getPartyStrength()+  " from "  +emailDetailsVO.getConstituencyName()+  " "  +emailDetailsVO.getElectionType()+ " Constituency has approved.<br/></div></div>";
		emailDetailsVO.setContent(content);
		emailDetailsVO.setSubject(subject);
		 mailService.sendEmail(emailDetailsVO);
			}
		}
		catch(Exception e)
		{
		e.printStackTrace();	
		}
		
	}
	
	public ResultStatus sendEmailForConnectedUsers(EmailDetailsVO emailDetailsVO)
	{
		ResultStatus resultStatus = new ResultStatus();
		try
		{
			if(emailDetailsVO != null)
			{
			String subject = "Partyanalyst.com";
			
			String content ="<div style='border:1px solid #CCCCCC;'>"+mailsTemplateService.getHeader()+"<br/>" +
					"<div style='margin-left:26px;margin-top:20px;margin-bottom: 7px;color:blue;'><b>Hai "+emailDetailsVO.getSenderName()+ ", </b><br/>" +
					"</div><div style='margin-left: 45px; margin-bottom: 40px;line-height: 1.5em;'><font><b> your friend " +emailDetailsVO.getFromAddress()+ " assesment for the " +emailDetailsVO.getCandidateName()+  " "  +emailDetailsVO.getPartyStrength()+  " from "+emailDetailsVO.getConstituencyName()+" " +emailDetailsVO.getElectionType()+ " constituency </b><br/>" +
					"If you Want to know more about " +emailDetailsVO.getCandidateName()+ " <a href='http://www.partyanalyst.com/loginInputAction.action'><b> Login Here</b> </a></div></div>";
			emailDetailsVO.setSubject(subject);
			emailDetailsVO.setContent(content);
			resultStatus = mailService.sendEmail(emailDetailsVO);
			
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
			return resultStatus;
		}
		return resultStatus;
	}
	
}