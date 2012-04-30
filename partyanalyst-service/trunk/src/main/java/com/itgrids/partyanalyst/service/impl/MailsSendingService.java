package com.itgrids.partyanalyst.service.impl;

import net.sf.cglib.core.EmitUtils;

import com.itgrids.partyanalyst.dto.EmailDetailsVO;
import com.itgrids.partyanalyst.dto.ResultStatus;
import com.itgrids.partyanalyst.service.IMailService;
import com.itgrids.partyanalyst.service.IMailsSendingService;
import com.itgrids.partyanalyst.service.IMailsTemplateService;

public class MailsSendingService implements IMailsSendingService{
	
	private IMailService mailService;
	private IMailsTemplateService mailsTemplateService;
	
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
	public ResultStatus sendEmailFriendRequest(String userName,String email,String requestFrom,String senderName , String msg)
	{
		ResultStatus resultStatus = new ResultStatus();
		
		/*String message = msg;
		if(message != null && message.trim().length() > 0)
			message="\""+message+"\"";*/
		
		String subject = ""+senderName+" sent a Friend Request in PartyAnalyst.com";
		String content = " <div style='border:1px solid #CCCCCC'>"+mailsTemplateService.getHeader()+"<br/><div style='margin-left:26px;margin-top:20px;margin-bottom: 7px;'><b>Hai "+userName+",</b><br/></div><div style='margin-left: 45px; margin-bottom: 39px;line-height:1.5em;'><b><font style='color:blue;'>"+senderName+"</font></b> has sent you a friend request in PartyAnalyst.com. <br/>  To respond " +
				"to friend request, <a href='http://www.partyanalyst.com/loginInputAction.action'><b>Login here.</b></a></div></div>" ;
		EmailDetailsVO emailDetailsVO = new EmailDetailsVO();
		emailDetailsVO.setToAddress(email);
		emailDetailsVO.setHost(requestFrom);
		emailDetailsVO.setSubject(subject);
		emailDetailsVO.setContent(content);
		
		resultStatus = mailService.sendEmail(emailDetailsVO ,requestFrom );
		return resultStatus;
	}
	
	public ResultStatus acceptEmailFriendRequest(String userName,String email,String requestFrom,String senderName)
	{
		ResultStatus resultStatus = new ResultStatus();
		
		String subject = ""+senderName+" accepted Friend Request in PartyAnalyst.com.";
		String content = "<div style = 'border:1px solid #CCCCCC'>"+mailsTemplateService.getHeader()+"<br/><div style='margin-left:26px;margin-top:20px;margin-bottom: 7px;'><b>Hai "+senderName+",</b></div>" +
				"<div style='margin-left: 45px; margin-bottom: 40px;line-height: 1.5em;'><font style='color:blue'><b>"+userName+"</b></font> has accepted your friend request in PartyAnalyst.com.<br/>Do you want to send a message? " +
						"<a href='http://www.partyanalyst.com/loginInputAction.action'><b> Login Here</b> </a></div></div>"; 
		EmailDetailsVO emailDetailsVO = new EmailDetailsVO();
		emailDetailsVO.setToAddress(email);
		emailDetailsVO.setHost(requestFrom);
		emailDetailsVO.setSubject(subject);
		emailDetailsVO.setContent(content);
		resultStatus = mailService.sendEmail(emailDetailsVO, requestFrom);
		return resultStatus;
		
	}
	
	public ResultStatus sendMessageToConnectUser(String userName,String email,String requestFrom,String msg,String senderName)
	{
		//String message = "";
		ResultStatus resultStatus = new ResultStatus();
		EmailDetailsVO emailDetailsVO = new EmailDetailsVO();
		/*if(msg != null && msg.trim().length() > 0)
		{
			message = "\""+msg+"\"";
		}*/
		String subject = ""+senderName+" send a message in PartyAnalyst.com.";
		String content = "<div style='border:1px solid #CCCCCC;'>"+mailsTemplateService.getHeader()+"<br/>" +
				"<div style='margin-left:26px;margin-top:20px;margin-bottom: 7px;'><b>Hai "+userName+",</b><br/>" +
						"</div><div style='margin-left: 45px; margin-bottom: 40px;line-height: 1.5em;'><font style='color:blue;'><b>"+senderName+"</b></font> has sent a message in PartyAnalyst.com.<br/> Do you want to send the reply? <a href='http://www.partyanalyst.com/loginInputAction.action'>Login Here</a></div></div>";
		emailDetailsVO.setContent(content);
		emailDetailsVO.setHost(requestFrom);
		emailDetailsVO.setToAddress(email);
		emailDetailsVO.setSubject(subject);
		resultStatus = mailService.sendEmail(emailDetailsVO, requestFrom);
		return resultStatus;
	}
	

}
