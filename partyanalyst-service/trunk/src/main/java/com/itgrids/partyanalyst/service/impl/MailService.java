package com.itgrids.partyanalyst.service.impl;

import java.util.Properties;

import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMessage.RecipientType;



import com.itgrids.partyanalyst.dao.IConstituencyDAO;
import com.itgrids.partyanalyst.dao.IDistrictDAO;

import com.itgrids.partyanalyst.dto.QuickRequestVO;
import com.itgrids.partyanalyst.dto.RegistrationVO;
import com.itgrids.partyanalyst.dto.ResultCodeMapper;
import com.itgrids.partyanalyst.dto.ResultStatus;
import com.itgrids.partyanalyst.service.IMailService;
import com.itgrids.partyanalyst.utils.IConstants;

public class MailService implements IMailService{

	private IDistrictDAO districtDAO;
	private IConstituencyDAO constituencyDAO;
	
	    
	 
	    public IConstituencyDAO getConstituencyDAO() {
		return constituencyDAO;
	    }
	    
	    public void setConstituencyDAO(IConstituencyDAO constituencyDAO) {
		this.constituencyDAO = constituencyDAO;
		}
	    
	    public void setDistrictDAO(IDistrictDAO districtDAO) {
			this.districtDAO = districtDAO;
		}
	    
		public IDistrictDAO getDistrictDAO() {
			return districtDAO;
		}
		public synchronized ResultStatus sendMailFromLocalHost(QuickRequestVO quickRequestVO){
	    	String STARTTLS = "true";
		    String AUTH = "true";
		    String SOCKET_FACTORY = "javax.net.ssl.SSLSocketFactory";
		    
		    ResultStatus rs=new ResultStatus();
	    	//Use Properties object to set environment properties
	        Properties props = new Properties();
	 
	        props.put("mail.smtp.host", IConstants.HOST);
	        props.put("mail.smtp.port", IConstants.PORT);
	        props.put("mail.smtp.user", quickRequestVO.getFromEmailId());
	        props.put("mail.smtp.socketFactory.port", IConstants.PORT);
	    
	        props.put("mail.smtp.auth", AUTH);
	        props.put("mail.smtp.starttls.enable", STARTTLS);
	        	 
	        
	        props.put("mail.smtp.socketFactory.class", SOCKET_FACTORY);
	        props.put("mail.smtp.socketFactory.fallback", "true");
	 
	        try {
	            final String fromEmailId = quickRequestVO.getFromEmailId();
	            //Obtain the default mail session
	            Session session = Session.getInstance(props, new javax.mail.Authenticator() {
					protected PasswordAuthentication getPasswordAuthentication() {
						return new PasswordAuthentication(fromEmailId,IConstants.PASSWORD);
					}
				});
	            session.setDebug(true);
	            MimeMessage message = new MimeMessage(session);
	            
	            //Construct the mail message
	            
	            message.setContent(quickRequestVO.getText(),"text/html");
	            message.setSubject(quickRequestVO.getSubject());
	            message.setFrom(new InternetAddress(quickRequestVO.getFromEmailId()));
	            message.addRecipient(RecipientType.TO, new InternetAddress(quickRequestVO.getToEmailId()));
	            message.saveChanges();
	 
	            //Use Transport to deliver the message
	            Transport transport = session.getTransport("smtp");
	            transport.connect(IConstants.HOST,fromEmailId,IConstants.PASSWORD);
	            transport.sendMessage(message, message.getAllRecipients());
	            transport.close();
	            
	            
	            rs.setExceptionMsg("Your Request sent successfully");
	            rs.setResultCode(ResultCodeMapper.SUCCESS);
	            
	        } 
	        catch (Exception e) {
	            e.printStackTrace();
	            rs.setExceptionEncountered(e);
	            rs.setExceptionMsg(e.getMessage());
	            rs.setResultCode(ResultCodeMapper.FAILURE);
	        }
	    	return rs;
	    }
	    public  ResultStatus sendQuickRequestEmailToAdmin(QuickRequestVO quickRequestVO,String requestFrom){
	       
	    	 ResultStatus rs = new  ResultStatus();
	    	
	    		String subject;
	    		String text;
	    		subject = "";
	    		subject= "User Requirements From PartyAnalyst"; 
	           
	           
	            text = "";
	            text += "Name : " + quickRequestVO.getUserName();
	            text += "\nEmailId : " + quickRequestVO.getEmailId();
	            text += "\nMobileNO : " + quickRequestVO.getMobileNumber();
	            text += "\nRequirement : "+ quickRequestVO.getUserRequirement();
	            
	            quickRequestVO.setText(text);
	            quickRequestVO.setToEmailId(IConstants.TOEMAILID);
	            quickRequestVO.setSubject(subject);
	            quickRequestVO.setFromEmailId(IConstants.FROMEMAILID);
	            if(requestFrom.equalsIgnoreCase(IConstants.LOCALHOST)){
	            rs=sendMailFromLocalHost(quickRequestVO);
	            }
	            else if(requestFrom.equalsIgnoreCase(IConstants.SERVER))
	            {
	            	rs = sendMailFromServer(quickRequestVO);
	            }
	            
			return rs;
	 	    }
	    
	    public ResultStatus sendMailToUserToRecoverPassword(RegistrationVO registrationVO , String requestFrom){
	    	
	    	QuickRequestVO quickRequestVO = new QuickRequestVO();
	    	ResultStatus rs = new ResultStatus();
	    	String subject;
    		String text;
	    	subject = "";
	    	subject = "Mail From www.partyanalyst.com";
	    	
	    	text = "";
	    	text = "Here are the login details for your PartyAnalyst Account.<br>";
	    	text +="User Name is:" +registrationVO.getUserName()+"<br>";
	    	text += "<b><font color=\"green\">Your Password is:</font></b>" +"  "+ registrationVO.getPassword();
	    	
	    	quickRequestVO.setToEmailId(registrationVO.getEmail());
	    	quickRequestVO.setSubject(subject);
	    	quickRequestVO.setText(text);
	    	quickRequestVO.setFromEmailId(IConstants.FROMEMAILID);
	    	 if(requestFrom.equalsIgnoreCase(IConstants.LOCALHOST)){
		            rs=sendMailFromLocalHost(quickRequestVO);
		            }
		            else if(requestFrom.equalsIgnoreCase(IConstants.SERVER))
		            {
		            	rs = sendMailFromServer(quickRequestVO);
		            }

	    	return rs;
	    }
	    
	    public ResultStatus sendRegistrationNotification(RegistrationVO registrationVO,String requestFrom){
	    	
	    	QuickRequestVO quickRequestVO = new QuickRequestVO();
	    	ResultStatus rs = new ResultStatus();
	    	
	    	String subject;
    		String text;
	    	subject = "";
	    	subject = "Welcome to PartyAnalyst.com"+" "+registrationVO.getUserName();
	    	
	    	
	    	text = "";
	    	text +="<b>Hey<font color=\"blue\">"+"     "+ registrationVO.getUserName();

	    	text +="</b></font><br><br>Just a quick note to confirm that you are now a registered member of our PartyAnalyst community.";

	    	text +="<br><br>Thank you for joining - we're so glad to have you as the newest member of our growing community.";

	    	text +="<br><br>Here are your login details. Please keep them safe.";

	    	text +="<br><br>Username:"+ " "+registrationVO.getUserName();
	    	text +="<br>Password:"+" "+registrationVO.getPassword();
	    	text +="<br><br>You can now connect with people, post problems, post comments and do lot more things. Why wait? Login now and start connecting with people. <br>Follow this link to log in <a href='www.partyanalyst.com/loginInputAction.action'>Login</a>";
	    	text +="<br><br>Explore more about your constituency : <a href='www.partyanalyst.com/constituencyPageAction.action?constituencyId="+registrationVO.getConstituency()+"'>"+constituencyDAO.get((new Long(registrationVO.getConstituency()))).getName()+"</a>";
	    	text +="<br><br>Explore more about your district:<a href=\'www.partyanalyst.com/districtPageAction.action?districtId="+registrationVO.getDistrict()+"&districtName="+districtDAO.get(new Long(registrationVO.getDistrict())).getDistrictName()+"\'>"+districtDAO.get(new Long(registrationVO.getDistrict())).getDistrictName().toUpperCase()+"</a>" ;
	    	text +="<br><br><br>Please add this email which you like from Party Analyst won&#39;t go for junk email."; 
	    	text +="<br>We hope to see you around and take part in our community!!!";
	    	text +="<br><br>For suggestions and support contact us at <b>info@itgrids.com</b>";
	    	text +="<br><br><b>Best Wishes,";
	    	text +="<br><font color=\"green\">PartyAnalyst Team.</font></b>";

	    	/*text +="<br><br>PS: Now that you are part of our community, why not invite your friends to join our community? You can not only connect and share your thoughts with them but also make them part of the change.";

	    	text +="<br>INVITE FRIENDS NOW";*/



	    	//text = "<b><font color=\"blue\">Thank You  For Registering with PartyAnalyst</font></b>";
	    	
	    	quickRequestVO.setToEmailId(registrationVO.getEmail());
	    	quickRequestVO.setSubject(subject);
	    	quickRequestVO.setText(text);
	    	quickRequestVO.setFromEmailId(IConstants.FROMEMAILID);
	   
	    	 if(requestFrom.equalsIgnoreCase(IConstants.LOCALHOST)){
		            rs=sendMailFromLocalHost(quickRequestVO);
		            }
		            else if(requestFrom.equalsIgnoreCase(IConstants.SERVER))
		            {
		            	rs = sendMailFromServer(quickRequestVO);
		            }

	    	return rs;
	    }

		public ResultStatus sendMailFromServer(QuickRequestVO quickRequestVO) {
			
			ResultStatus rs=new ResultStatus();
			
			Properties prop = System.getProperties();
			Session session = Session.getDefaultInstance(prop);
			MimeMessage message = new MimeMessage(session);
			
			try{
			    message.setSubject(quickRequestVO.getSubject());
			     
			    //Per RFP these next four should be set
			    message.setFrom(new InternetAddress(quickRequestVO.getFromEmailId()));
			    message.setHeader("Return-Path", IConstants.FROMEMAILID);
			    message.setSentDate(new java.util.GregorianCalendar().getTime());
			    message.setContent(quickRequestVO.getText(), "text/html");
			    message.setRecipient(Message.RecipientType.TO, new InternetAddress(quickRequestVO.getToEmailId()));
			    Transport.send(message);
			    rs.setExceptionMsg("Your Request sent successfully");
	            rs.setResultCode(ResultCodeMapper.SUCCESS);
	            
	        } 
	        catch (Exception e) {
	            e.printStackTrace();
	            rs.setExceptionEncountered(e);
	            rs.setExceptionMsg(e.getMessage());
	            rs.setResultCode(ResultCodeMapper.FAILURE);
	        }
	    	return rs;
		}
		
	    
	}

