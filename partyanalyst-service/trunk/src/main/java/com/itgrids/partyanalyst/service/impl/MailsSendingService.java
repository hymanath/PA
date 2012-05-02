package com.itgrids.partyanalyst.service.impl;

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
	
	public ResultStatus sendEmailFriendRequest(EmailDetailsVO emailDetVO)
	{
		ResultStatus resultStatus = new ResultStatus();
		String subject = ""+emailDetVO.getSenderName()+" sent a Friend Request in PartyAnalyst.com";
		
		String content = " <div style='border:1px solid #CCCCCC'>"+mailsTemplateService.getHeader()+"<br/><div style='margin-left:26px;margin-top:20px;margin-bottom: 7px;'><b>Hai "+emailDetVO.getFromAddress()+",</b><br/></div><div style='margin-left: 45px; margin-bottom: 39px;line-height:1.5em;'><b><font style='color:blue;'>"+emailDetVO.getSenderName()+"</font></b> has sent you a friend request in PartyAnalyst.com. <br/>  To respond " +
				"to friend request, <a href='http://www.partyanalyst.com/loginInputAction.action'><b>Login here.</b></a></div></div>" ;
		
		EmailDetailsVO emailDetailsVO = new EmailDetailsVO();
		emailDetailsVO.setToAddress(emailDetVO.getToAddress());
		emailDetailsVO.setHost(emailDetVO.getHost());
		emailDetailsVO.setSubject(subject);
		emailDetailsVO.setContent(content);
		resultStatus = mailService.sendEmail(emailDetailsVO ,emailDetVO.getHost() );
		return resultStatus;
	}
	
	public ResultStatus acceptEmailFriendRequest(EmailDetailsVO emaDetailsVO)
	{
		ResultStatus resultStatus = new ResultStatus();
		EmailDetailsVO emailDetailsVO = new EmailDetailsVO();
		String subject = ""+emaDetailsVO.getSenderName()+" accepted Friend Request in PartyAnalyst.com."; 
		String content = "<div style = 'border:1px solid #CCCCCC'>"+mailsTemplateService.getHeader()+"<br/><div style='margin-left:26px;margin-top:20px;margin-bottom: 7px;'><b>Hai "+emaDetailsVO.getSenderName()+",</b></div>" +
				"<div style='margin-left: 45px; margin-bottom: 40px;line-height: 1.5em;'><font style='color:blue'><b>"+emaDetailsVO.getFromAddress()+"</b></font> has accepted your friend request in PartyAnalyst.com.<br/>Do you want to send a message? " +
						"<a href='http://www.partyanalyst.com/loginInputAction.action'><b> Login Here</b> </a></div></div>";
		
		emailDetailsVO.setHost(emaDetailsVO.getHost());
		emailDetailsVO.setSubject(subject);
		emailDetailsVO.setContent(content);
		emailDetailsVO.setToAddress(emaDetailsVO.getToAddress());
		resultStatus = mailService.sendEmail(emailDetailsVO, emaDetailsVO.getHost());
		return resultStatus;
	}
	
	public ResultStatus sendMessageToConnectUser(EmailDetailsVO emailDetVO)
	{
		ResultStatus resultStatus = new ResultStatus();
		EmailDetailsVO emailDetailsVO = new EmailDetailsVO();
		String subject = ""+emailDetVO.getSenderName()+" send a message in PartyAnalyst.com.";
		
		String content = "<div style='border:1px solid #CCCCCC;'>"+mailsTemplateService.getHeader()+"<br/>" +
				"<div style='margin-left:26px;margin-top:20px;margin-bottom: 7px;'><b>Hai "+emailDetVO.getFromAddress()+",</b><br/>" +
						"</div><div style='margin-left: 45px; margin-bottom: 40px;line-height: 1.5em;'><font style='color:blue;'><b>"+emailDetVO.getSenderName()+"</b></font> has sent a message in PartyAnalyst.com.<br/> Do you want to send the reply? <a href='http://www.partyanalyst.com/loginInputAction.action'>Login Here</a></div></div>";
		emailDetailsVO.setContent(content);
		emailDetailsVO.setSubject(subject);
		emailDetailsVO.setHost(emailDetVO.getHost());
		emailDetailsVO.setToAddress(emailDetVO.getToAddress());
		resultStatus = mailService.sendEmail(emailDetailsVO, emailDetVO.getHost());
		return resultStatus;
	}

}
