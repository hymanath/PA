package com.itgrids.partyanalyst.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.itgrids.partyanalyst.dao.IUserDAO;
import com.itgrids.partyanalyst.dto.EmailDetailsVO;
import com.itgrids.partyanalyst.dto.ProblemDetailsVO;
import com.itgrids.partyanalyst.dto.RegistrationVO;
import com.itgrids.partyanalyst.dto.ResultCodeMapper;
import com.itgrids.partyanalyst.dto.ResultStatus;
import com.itgrids.partyanalyst.service.IMailService;
import com.itgrids.partyanalyst.service.IMailsSendingService;
import com.itgrids.partyanalyst.service.IMailsTemplateService;

public class MailsSendingService implements IMailsSendingService{
	
	private static final Logger log = Logger.getLogger(MailsSendingService.class);
	private IMailService mailService;
	private IMailsTemplateService mailsTemplateService;
	private IUserDAO userDAO; 
	
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
	
	public IUserDAO getUserDAO() {
		return userDAO;
	}
	public void setUserDAO(IUserDAO userDAO) {
		this.userDAO = userDAO;
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
				
			String subject = ""+emailDetailsVO.getSenderName()+" sent a Friend Request in Party Analyst";
			String content = " <div style='background-color:#EFFFFF;width:600px;border:1px solid #CCCCCC'>"+mailsTemplateService.getHeader()+"<br/><div style='margin-left:26px;margin-top:20px;margin-bottom: 7px;'>Hi <b>"+emailDetailsVO.getFromAddress()+",</b><br/></div><div style='margin-left: 45px; margin-bottom: 20px;line-height:1.5em;'><b>"+emailDetailsVO.getSenderName()+"</b> has sent a friend request in PartyAnalyst. <br/>  To respond " +
					"to the friend request, <a href='http://www.partyanalyst.com/loginInputAction.action?source=email&target=fr&'><b>Login here.</b></a></div>" +
									"<div>"+mailsTemplateService.getFooter()+"</div></div>" ;
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
			
			String subject = ""+emailDetailsVO.getSenderName()+" accepted Friend Request in PartyAnalyst"; 
			String content = "<div style = 'background-color:#EFFFFF;width:600px;border:1px solid #CCCCCC'>"+mailsTemplateService.getHeader()+"<br/><div style='margin-left:26px;margin-top:20px;margin-bottom: 7px;'><b>Hi "+emailDetailsVO.getFromAddress()+",</b></div>" +
					"<div style='margin-left: 45px; margin-bottom: 20px;line-height: 1.5em;'><b>"+emailDetailsVO.getSenderName()+"</b> has accepted your friend request in PartyAnalyst.<br/>Do you want to send a message? " +
							"<a href='http://www.partyanalyst.com/loginInputAction.action?source=email&target=fa&'><b> Login Here</b> </a></div>" +
							"<div>"+mailsTemplateService.getFooter()+"</div></div>";
			
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
			
			String subject = ""+emailDetailsVO.getSenderName()+" send a message in Party Analyst";
			String content = "<div style='background-color:#EFFFFF;width:600px;border:1px solid #CCCCCC;'>"+mailsTemplateService.getHeader()+"<br/>" +
					"<div style='margin-left:26px;margin-top:20px;margin-bottom: 7px;'>Hi <b>"+emailDetailsVO.getFromAddress()+",</b><br/>" +
							"</div><div style='margin-left: 45px; margin-bottom: 20px;line-height: 1.5em;'><b>"+emailDetailsVO.getSenderName()+"</b> has sent a message in PartyAnalyst.<br/> To see the message and send a reply? <a href='http://www.partyanalyst.com/loginInputAction.action?source=email&target=ms&'>Login Here</a></div>" +
									"<div>"+mailsTemplateService.getFooter()+"</div></div>";
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

			List<Object[]> usersLst = userDAO.getPasswordNotUpdatdUsersList();
	
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
						       
						       content+="<br>To change your password <b><a href='http://www.partyanalyst.com/loginInputAction.action?source=email&target=pu&'>Login Here</a><b><br>";
						       
						       content+="<br>UserName:<b>&nbsp;"+userDetails[3].toString()+"</b><br>";
						       content+="Password:<b>&nbsp;"+userDetails[4].toString()+"</b><br></div>" +
						       		"<div>"+mailsTemplateService.getFooter()+"</div></div>";
					
				
					
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
			String subject = "Updates from partyanalyst";
			String content = "<div style='background-color:#EFFFFF;width:600px;border:1px solid #CCCCCC;'>"+mailsTemplateService.getHeader()+"<br/>" +
					"<div style='margin-left:26px;margin-top:20px;margin-bottom: 7px;'>Hi <b>"+emailDetailsVO.getFromAddress()+",</b><br/>" +
							"</div><div style='margin-left: 45px; margin-bottom: 40px;line-height: 1.5em;'><font> Your Political Reason for " +emailDetailsVO.getPartyStrength()+ " the candidate <b>" +emailDetailsVO.getCandidateName()+ "</b> from <b>" +emailDetailsVO.getConstituencyName()+ " " +emailDetailsVO.getElectionType()+  "</b> Constituency Added Successfully . We will send your comment to Admin for approval.<br/></div>" +
									"<div>"+mailsTemplateService.getFooter()+"</div></div>";
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
				String subject = "Updates from partyanalyst";
				String content ="<div style='background-color:#EFFFFF;width:600px;border:1px solid #CCCCCC;'>"+mailsTemplateService.getHeader()+"<br/>" +
						"<div style='margin-left:26px;margin-top:20px;margin-bottom: 7px;'>Hi <b>"+emailDetailsVO.getFromAddress()+",</b><br/>" +
						"</div><div style='margin-left: 45px; margin-bottom: 20px;line-height: 1.5em;'><font> your comment for the <b>" +emailDetailsVO.getCandidateName()+ " " +emailDetailsVO.getPartyStrength()+  "</b> from <b>"  +emailDetailsVO.getConstituencyName()+  " "  +emailDetailsVO.getElectionType()+ "</b> Constituency has approved.<br/></div>" +
								"<div>"+mailsTemplateService.getFooter()+"</div></div>";
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
			String subject = "  "+emailDetailsVO.getFromAddress()+ " posted political reason  in partyanalyst";
			
			String content ="<div style='background-color:#EFFFFF;width:600px;border:1px solid #CCCCCC;'>"+mailsTemplateService.getHeader()+"<br/>" +
					"<div style='margin-left:26px;margin-top:20px;margin-bottom: 7px;color:blue;'>Hi <b>"+emailDetailsVO.getSenderName()+ ", </b><br/>" +
					"</div><div style='margin-left: 45px; margin-bottom: 20px;line-height: 1.5em;'><font> Your friend <b>" +emailDetailsVO.getFromAddress()+ "</b> posted a political reason for <b>" +emailDetailsVO.getCandidateName()+  " "  +emailDetailsVO.getPartyStrength()+  "</b> from <b>"+emailDetailsVO.getConstituencyName()+" " +emailDetailsVO.getElectionType()+ "</b> constituency. </b><br/><br/>" +
					"If you want to know more about " +emailDetailsVO.getCandidateName()+  " , <a href='http://www.partyanalyst.com/loginInputAction.action?source=email&target=pa&'><b> Login Here</b> </a></div>" +
							"<div>"+mailsTemplateService.getFooter()+"</div></div>";
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
	
	public ResultStatus sendEmailToFreeUserAfterProblemAdded(ProblemDetailsVO problemDetailsVO)
	{
		ResultStatus resultStatus = new ResultStatus();
		EmailDetailsVO emailDetailsVO = new EmailDetailsVO();
		try{
			if(problemDetailsVO == null)
			{
				resultStatus.setResultCode(ResultCodeMapper.FAILURE);
				log.error("problemDetailsVO is null in sendSuccesMsgToUserEmail()");
				return resultStatus;
			}
			
			log.info("Entered into sendSuccesMsgToUserEmail() in MailsSendingService");
			
			String subject = "Problem Posted Successfully in PartyAnalyst";
			
			String content = "<div style='border:1px solid #CCCCCC;background:#EFFFFF;'>"+mailsTemplateService.getHeader()+"" +
					"<div style='margin-left:26px;margin-top:20px;margin-bottom: 7px;'><b>Hi "+problemDetailsVO.getEmailDetailsVO().getFromAddress()+",</b></div>" +
							"<div style='margin-left: 45px; margin-bottom: 20px;line-height: 1.5em;'>Thanks for posting your problem.Your area Problem Posted Successfully.<br><br>" +
							" Your Problem Reference Number is : <b>"+problemDetailsVO.getEmailDetailsVO().getElectionType()+"</b>" +
									"<br>This Will be usefull for Further Reference.<br>" +
							"<b>Problem : </b>"+problemDetailsVO.getDefinition()+"<br><br>" +
									"<b>Description : </b>"+problemDetailsVO.getDescription()+"<br><br>" +
											"<b>Location <span style='margin-left:80px;'>:</span>  </b>"+problemDetailsVO.getLocation()+"" +
													"<br/><b>Existing From <span style='margin-left:48px;'>:</span>  </b>"+problemDetailsVO.getIdentifiedDate()+"" +
															"<br/><b>Reported Date <span style='margin-left:45px;'>:</span>  </b>"+problemDetailsVO.getExistingFrom()+"" +
																	"<br/><b>NO.Of Files Attached <span style='margin-left:5px;'>:</span>  </b>"+problemDetailsVO.getProblemID()+"" +
																			"<br/>Your Request send to Admin for Approvel." +
							"</div>" +
									"<div>"+mailsTemplateService.getFooter()+"</div></div>";
			emailDetailsVO.setSubject(subject);
			emailDetailsVO.setContent(content);
			emailDetailsVO.setToAddress(problemDetailsVO.getEmailDetailsVO().getToAddress());
			mailService.sendEmail(emailDetailsVO);
		}catch (Exception e) {
			
			e.printStackTrace();
		}
		
		return resultStatus;
	}
	
	public ResultStatus sendEmailToFreeUserAfterProblemApproval(ProblemDetailsVO problemDetailsVO)
	{
		ResultStatus resultStatus = new ResultStatus();
		EmailDetailsVO emailDetailsVO = new EmailDetailsVO();
		if(problemDetailsVO == null)
		{
			resultStatus.setResultCode(ResultCodeMapper.FAILURE);
			log.error("problemDetailsVO is null in sendEmailToFreeUserAfterProblemApproval()");
			return resultStatus;
		}
		try
		{
			log.info("Entered into sendEmailToFreeUserAfterProblemApproval()");
			String subject = "Updates From PartyAnalyst";
			String content = "<div style='border:1px solid #CCCCCC;background:#EFFFFF;'>"+mailsTemplateService.getHeader()+"" +
					"<div style='margin-left:26px;margin-top:20px;margin-bottom: 7px;color:blue;'><b>Hi "+problemDetailsVO.getEmailDetailsVO().getFromAddress()+",</b></div>" +
							"<div style='margin-left: 45px; margin-bottom: 8px;line-height: 1.5em;'>Your Problem approved by PartyAnalyst Admin.<br><br>" +
							"<b>Problem : </b>"+problemDetailsVO.getDefinition()+"<br><br>" +
							"<b>Description : </b>"+problemDetailsVO.getDescription()+"<br><br>" +
									"Reference Number : <b> "+problemDetailsVO.getSource()+"</b><br>" +
											"To share,comment,like and More Details about the Problem - " +
											"<a href='http://www.partyanalyst.com/problemCompleteInfoAction.action?problemHistoryId="+problemDetailsVO.getProblemHistoryId()+"&source=email&'> </a> (or) Use the below Link</div>" +
													"<div style='margin-left: 15px;margin-bottom:20px;'>http://www.partyanalyst.com/problemCompleteInfoAction.action?problemHistoryId="+problemDetailsVO.getProblemHistoryId()+"&source=email&target=probApp&</div>" +
															"<div>"+mailsTemplateService.getFooter()+"</div></div>";
			
			emailDetailsVO.setToAddress(problemDetailsVO.getEmailDetailsVO().getToAddress());
			emailDetailsVO.setSubject(subject);
			emailDetailsVO.setContent(content);
			mailService.sendEmail(emailDetailsVO);
			
			return resultStatus;
		}catch (Exception e) {
			log.error("Exception Occured in sendEmailToFreeUserAfterProblemApproval() , Exception is - "+e);
			return resultStatus;
		}
		
	}
	
	public ResultStatus sendEmailToConnectedUsersAfterProblemApproval(ProblemDetailsVO problemDetailsVO)
	{
		ResultStatus resultStatus = new ResultStatus();
		EmailDetailsVO emailDetailsVO = new EmailDetailsVO();
			if(problemDetailsVO == null)
			{
				resultStatus.setResultCode(ResultCodeMapper.FAILURE);
				log.error("problemDetailsVO is null in sendEmailToConnectedUsersAfterProblemApproval()");
				return resultStatus;
			}
			try
			{
				log.info("Entered into sendEmailToConnectedUsersAfterProblemApproval()");
			String subject = ""+problemDetailsVO.getEmailDetailsVO().getSenderName()+" Posted a Problem in PartyAnalyst";
			String content = "<div style='border:1px solid #CCCCCC;background:#EFFFFF;'>"+mailsTemplateService.getHeader()+"" +
					"<div style='margin-left:26px;margin-top:20px;margin-bottom: 8px;'><b>Hi "+problemDetailsVO.getEmailDetailsVO().getFromAddress()+",</b>" +
							"</div>" +
					"<div style='margin-left: 45px; margin-bottom: 8px;line-height: 1.5em;'>Your Friend <b>"+problemDetailsVO.getEmailDetailsVO().getSenderName()+"</b> Posted a problem in PartyAnalyst.<br><br>" +
							"<b>Problem : </b>"+problemDetailsVO.getDefinition()+"<br><br>" +
							"<b>Description : </b>"+problemDetailsVO.getDescription()+"<br>" +
									"To View Problem details and comments on this Problem - " +
									"<a href='http://www.partyanalyst.com/problemCompleteInfoAction.action?problemHistoryId="+problemDetailsVO.getProblemHistoryId()+"&source=email&taget=probApprovel&'>ClickHere </a> (or) Use the below Link</div>" +
											"<div style='margin-left: 15px;margin-bottom:20px;'>http://www.partyanalyst.com/problemCompleteInfoAction.action?problemHistoryId="+problemDetailsVO.getProblemHistoryId()+"&source=email&</div>" +
													"<div>"+mailsTemplateService.getFooter()+"</div></div>";
			
			emailDetailsVO.setSubject(subject);
			emailDetailsVO.setContent(content);
			emailDetailsVO.setToAddress(problemDetailsVO.getEmailDetailsVO().getToAddress());
			mailService.sendEmail(emailDetailsVO);
			
			return resultStatus;
			
		}catch (Exception e) {
			resultStatus.setResultCode(ResultCodeMapper.FAILURE);
			resultStatus.setExceptionEncountered(e);
			log.error("Exception occured in sendEmailToConnectedUsersAfterProblemApproval() , Exception is - "+e);
			return resultStatus;
		}
	}
	
	/*public ResultStatus sendEmailToNewUserAfterPasswordChanged(RegistrationVO regVo,String requestFrom)
	{
		ResultStatus resultStatus = new ResultStatus();
		EmailDetailsVO emailDetailsVO = new EmailDetailsVO();
		if(regVo == null)
		{
			log.error("RegistrationVO is null in sendEmailToNewUserAfterPasswordChanged()");
			resultStatus.setResultCode(ResultCodeMapper.FAILURE);
			return resultStatus;
		}
		try{
			String name = null;
			if(regVo.getFirstName() != null)
				name = regVo.getFirstName();
			if(regVo.getLastName() != null)
				name +=" "+regVo.getLastName(); 
			String toAddress = regVo.getEmail();
			
			String subject = "PartyAnalyst.";
			String content = "<div style='border:1px solid #CCCCCC;background:#EFFFFF;'>"+mailsTemplateService.getHeader()+"" +
					"<div style='margin-left:26px;margin-top:20px;margin-bottom: 8px;>Hi <b>"+name+"</b><br>" +
							"Password Saved Successfully.</div>" +
					"</div>" ;
			emailDetailsVO.setSubject(subject);
			emailDetailsVO.setContent(content);
			emailDetailsVO.setToAddress(toAddress);
			mailService.sendEmail(emailDetailsVO);
		
		return resultStatus;
		}catch (Exception e) {
			log.error("Exception Occured in sendEmailToNewUserAfterPasswordChanged() Method, Exception is - "+e);
			e.printStackTrace();
			resultStatus.setResultCode(ResultCodeMapper.FAILURE);
			return resultStatus;
		}
	}*/
}