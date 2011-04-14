package com.itgrids.partyanalyst.service.impl;

import java.util.Properties;

import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMessage.RecipientType;

import com.itgrids.partyanalyst.dto.QuickRequestVO;
import com.itgrids.partyanalyst.dto.ResultCodeMapper;
import com.itgrids.partyanalyst.dto.ResultStatus;
import com.itgrids.partyanalyst.service.IMailService;
import com.itgrids.partyanalyst.utils.IConstants;

public class MailService implements IMailService{

	
	
	    private static String STARTTLS = "true";
	    private static String AUTH = "true";
	    private static String SOCKET_FACTORY = "javax.net.ssl.SSLSocketFactory";
	    String subject = "User Requirements From PartyAnalyst";
	    String text = "";
	 
	    public synchronized ResultStatus sendQuickRequestEmailToAdmin(QuickRequestVO quickRequestVO){
	       
	    	
	    	ResultStatus rs=new ResultStatus();
	    	//Use Properties object to set environment properties
	        Properties props = new Properties();
	 
	        props.put("mail.smtp.host", IConstants.HOST);
	        props.put("mail.smtp.port", IConstants.PORT);
	        props.put("mail.smtp.user", IConstants.FROMEMAILID);
	        props.put("mail.smtp.socketFactory.port", IConstants.PORT);
	    
	        props.put("mail.smtp.auth", AUTH);
	        props.put("mail.smtp.starttls.enable", STARTTLS);
	        	 
	        
	        props.put("mail.smtp.socketFactory.class", SOCKET_FACTORY);
	        props.put("mail.smtp.socketFactory.fallback", "true");
	 
	        try {
	 
	            //Obtain the default mail session
	            Session session = Session.getInstance(props, new javax.mail.Authenticator() {
					protected PasswordAuthentication getPasswordAuthentication() {
						return new PasswordAuthentication(IConstants.FROMEMAILID,IConstants.PASSWORD);
					}
				});
	            session.setDebug(true);
	            
	            //Construct the mail message
	            MimeMessage message = new MimeMessage(session);
	            text = "";
	            text += "Name : " + quickRequestVO.getUserName();
	            text += "\nEmailId : " + quickRequestVO.getEmailId();
	            text += "\nMobileNO : " + quickRequestVO.getMobileNumber();
	            text += "\nRequirement : "+ quickRequestVO.getUserRequirement();
	            
	            message.setText(text);
	            message.setSubject(subject);
	            message.setFrom(new InternetAddress(IConstants.FROMEMAILID));
	            message.addRecipient(RecipientType.TO, new InternetAddress(IConstants.TOEMAILID));
	            message.saveChanges();
	 
	            //Use Transport to deliver the message
	            Transport transport = session.getTransport("smtp");
	            transport.connect(IConstants.HOST,IConstants.FROMEMAILID,IConstants.PASSWORD);
	            transport.sendMessage(message, message.getAllRecipients());
	            transport.close();
	            rs.setExceptionMsg("Your Request sent successfully");
	 
	        } 
	        catch (Exception e) {
	            e.printStackTrace();
	            rs.setExceptionEncountered(e);
	            rs.setExceptionMsg(e.getMessage());
	            rs.setResultCode(ResultCodeMapper.FAILURE);
	            return rs;
	         }
			return rs;
	 	    }
	}


