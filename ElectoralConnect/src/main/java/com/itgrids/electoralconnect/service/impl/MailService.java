package com.itgrids.electoralconnect.service.impl;

import java.util.Properties;

import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMessage.RecipientType;

import org.apache.log4j.Logger;

import com.itgrids.electoralconnect.dto.QuickRequestVO;
import com.itgrids.electoralconnect.dto.RegistrationVO;
import com.itgrids.electoralconnect.dto.ResultCodeMapper;
import com.itgrids.electoralconnect.dto.ResultStatus;
import com.itgrids.electoralconnect.dto.UserVO;
import com.itgrids.electoralconnect.service.IMailService;
import com.itgrids.electoralconnect.service.IMailsTemplateService;
import com.itgrids.electoralconnect.util.IConstants;


public class MailService implements IMailService{

	private static final Logger log = Logger.getLogger(MailService.class);
	//private DateUtilService dateUtilService = new DateUtilService();
	private IMailsTemplateService mailsTemplateService;
	//private IStaticDataService staticDataService;
	
	 
    public IMailsTemplateService getMailsTemplateService() {
		return mailsTemplateService;
	}

	public void setMailsTemplateService(IMailsTemplateService mailsTemplateService) {
		this.mailsTemplateService = mailsTemplateService;
	}

	/*
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

	public IStaticDataService getStaticDataService() {
		return staticDataService;
	}

	public void setStaticDataService(IStaticDataService staticDataService) {
		this.staticDataService = staticDataService;
	}*/

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
    		subject= "User Requirements From Party Analyst"; 
           
           
            text = "";
            text += "Name : " + quickRequestVO.getUserName();
            text += "\nEmailId : " + quickRequestVO.getEmailId();
            text += "\nMobileNO : " + quickRequestVO.getMobileNumber();
            text += "\nRequirement : "+ quickRequestVO.getUserRequirement();
            
            quickRequestVO.setText(text);
            quickRequestVO.setToEmailId(IConstants.TOEMAILID);
            quickRequestVO.setSubject(subject);
            
            if(requestFrom.equalsIgnoreCase(IConstants.LOCALHOST)){
            	quickRequestVO.setFromEmailId(IConstants.LOCALFROMEMAILID);
            rs=sendMailFromLocalHost(quickRequestVO);
            }
            else if(requestFrom.equalsIgnoreCase(IConstants.SERVER))
            {
            	quickRequestVO.setFromEmailId(IConstants.FROMEMAILID);
            	rs = sendMailFromServer(quickRequestVO);
            }
            
		return rs;
 	    }
    
    public ResultStatus sendMailToUserToRecoverPassword(RegistrationVO regVO , String requestFrom){
    	
    	QuickRequestVO quickRequestVO = new QuickRequestVO();
    	ResultStatus rs = new ResultStatus();
    	String subject;
		String text;
    	subject = "";
    	subject = "Mail From www.electoralconnect.com";
    	
    	text = "";
    	text = "<div style='border:1px solid #CCCCCC;background:#EFFFFF;'>"+mailsTemplateService.getHeader()+"" 
    			+"<div style='margin-left:26px;margin-top:20px;margin-bottom: 7px;'>Hi <b>"+regVO.getFirstName()+" "+regVO.getLastName()+",</b></div>" +
    			"<div style='margin-left:45px;margin-bottom:40px;line-height: 1.5em;'>Here are the login details for your Party Analyst Account.<br>";
    	text += "User Name is : " + " " +regVO.getEmail()+"<br>";
    	if(regVO.getPassword() != null)
    	text += "Your Password is :" +"  <b>"+ regVO.getPassword()+"</b>";
    	text +="</div><div style='margin: -17px 3px 0px 19px; padding-bottom: 18px;'>"+mailsTemplateService.getFooter()+"</div></div>";
    	quickRequestVO.setToEmailId(regVO.getEmail());
    	quickRequestVO.setSubject(subject);
    	quickRequestVO.setText(text);
    	
    	 if(requestFrom.equalsIgnoreCase(IConstants.LOCALHOST)){
            	quickRequestVO.setFromEmailId(IConstants.LOCALFROMEMAILID);
            rs=sendMailFromLocalHost(quickRequestVO);
            }
            else if(requestFrom.equalsIgnoreCase(IConstants.SERVER))
            {
            	quickRequestVO.setFromEmailId(IConstants.FROMEMAILID);
            	rs = sendMailFromServer(quickRequestVO);
            }

    	return rs;
    }
    
    public ResultStatus sendRegistrationNotification(RegistrationVO  regVO,String requestFrom)
    {
    	ResultStatus rs = new ResultStatus();
    	try{
    	QuickRequestVO quickRequestVO = new QuickRequestVO();
    	
    	//String login = registrationVO.getContextPath()+"/loginInputAction.action";
    	String sendMail="mailto:info@partyanalyst.com";
    	String subject;
		String text;
    	subject = "";
    	subject = "Welcome to Electoral Connect";
    	
    	
    	text = "";
    	text +="<div style='border:1px solid #CCCCCC;background:#EFFFFF;'>"+mailsTemplateService.getHeader()+"<br/>";
      	
    	text +="<div style='margin-left:20px; margin-top:15px;'>Hi  <b>"+regVO.getFirstName()+" "+regVO.getLastName()+",</div>";
    	
    	text +="<div style='margin: 12px 15px 30px 30px; line-height: 1.8em;'></b></font>Just a quick note to confirm that you are now a registered member of Party Analyst family.";
    	
    	text +="<br>Thank you for joining - we're so glad to have you as the newest member of our growing community.";

    	
    	text +="<br>Here are your login details. Please keep them safe for future reference.";
    	
    	text +="<br><b>Username : </b>"+ " <span style='text-decoration: none;'>"+regVO.getEmail()+"</span>";
    	text +="<br><b>Password : </b>"+" "+regVO.getPassword();
    	
    	text +="<br>We hope to see you around and take part in our community!!!";
    	//text +="<br><br>For suggestions and support contact us at <b><a href="+sendMail+">info@partyanalyst.com</a></b>";
    	text +="<br>Have a good day!";
    	//text +="<div style='margin-left: -28px;'>"+mailsTemplateService.getFooter()+"</div>";
    	//text +="<br><br>-&nbsp;&nbsp;&nbsp;PartyAnalyst Team.";
    	//text +="<br><br><b>PS:</b> Please add this email to your address book so that the emails from us dont end up in your junk folder.";
    	text +="<div style='margin-top: 10px;'>" +
				"Thanks," +
				"<br>Electoral Connect Team<br>" +
				"<a href='http://www.partyanalyst.com/homePage.action'>www.partyanalyst.com</a><br>" +
				"<div><p><b>PS:&nbsp;</b>Please add this email to your address book so that the emails from us don't end up in your junk folder.For suggestions and support contact us at <b><a href="+sendMail+">info@partyanalyst.com</a></p></div></div>";
    	text +="</div></div>";
    	
    	/*text +="<br><br>PS: Now that you are part of our community, why not invite your friends to join our community? You can not only connect and share your thoughts with them but also make them part of the change.";

    	text +="<br>INVITE FRIENDS NOW";*/



    	//text = "<b><font color=\"blue\">Thank You  For Registering with PartyAnalyst</font></b>";
    	
    	quickRequestVO.setToEmailId(regVO.getEmail());
    	quickRequestVO.setSubject(subject);
    	quickRequestVO.setText(text);
    	
   
    	 if(requestFrom.equalsIgnoreCase(IConstants.LOCALHOST)){
    		 quickRequestVO.setFromEmailId(IConstants.LOCALFROMEMAILID);
	            rs=sendMailFromLocalHost(quickRequestVO);
	           	            
	            }
	            else if(requestFrom.equalsIgnoreCase(IConstants.SERVER))
	            {
	            	quickRequestVO.setFromEmailId(IConstants.FROMEMAILID);
	            	rs = sendMailFromServer(quickRequestVO);
	            }

    	return rs;
    	}catch (Exception e) {
    		log.error("Exception Occured - "+e);
    		rs.setResultCode(ResultCodeMapper.FAILURE);
    		return rs;
    	}
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

	

	
	/* public  ResultStatus sendArticleToAdmin(QuickRequestVO quickRequestVO,String requestFrom){
	        
		    	 ResultStatus rs = new  ResultStatus();
		    	
		    		String subject;
		    		String text;
		    		subject = "";
		    		subject= "User Interesting to Post Articles to Party Analyst"; 
		           
		           
		            text = "";
		            text += "<table><tr><td>Name :</td><td>" + quickRequestVO.getUserName()+"</td></tr>";
		            text += "<tr><td>EmailId :</td><td> " + quickRequestVO.getEmailId()+"</td></tr>";
		            text += "<tr><td>MobileNO :</td><td> " + quickRequestVO.getMobileNumber()+"</td></tr>";
		            text += "<tr><td>Comment : </td><td>"+ quickRequestVO.getUserRequirement()+"</td></tr>";
		            text+="</table>";
		            quickRequestVO.setText(text);
		            quickRequestVO.setToEmailId(IConstants.TOEMAILID);
		            quickRequestVO.setSubject(subject);
		            if(requestFrom.equalsIgnoreCase(IConstants.LOCALHOST)){
		            	quickRequestVO.setFromEmailId(IConstants.LOCALFROMEMAILID);
		            rs=sendMailFromLocalHost(quickRequestVO);
		            }
		            else if(requestFrom.equalsIgnoreCase(IConstants.SERVER))
		            {
		            	quickRequestVO.setFromEmailId(IConstants.FROMEMAILID);
		            	rs = sendMailFromServer(quickRequestVO);
		            }
		            text="ThankYou for your interest we will get back to you as soon as possible";
		    		subject= " Articles On Party Analyst"; 

		            quickRequestVO.setText(text);
		            quickRequestVO.setToEmailId(quickRequestVO.getEmailId());

		            quickRequestVO.setSubject(subject);

		            if(requestFrom.equalsIgnoreCase(IConstants.LOCALHOST)){
		            	quickRequestVO.setFromEmailId(IConstants.LOCALFROMEMAILID);
		            rs=sendMailFromLocalHost(quickRequestVO);
		            }
		            else if(requestFrom.equalsIgnoreCase(IConstants.SERVER))
		            {
		            	quickRequestVO.setFromEmailId(IConstants.FROMEMAILID);
		            	rs = sendMailFromServer(quickRequestVO);
		            }
		            
		            
				return rs;
		 	    }
	
	public ResultStatus sendEmail(EmailDetailsVO emailDetails)
	{
		try{
			if(emailDetails == null)
				return null;
			List<EmailDetailsVO> emailsList = new ArrayList<EmailDetailsVO>(0);
			emailsList.add(emailDetails);
			
			return sendEmails(emailsList);
		}catch (Exception e) {
			return null;
		}
	}
	public ResultStatus sendEmails(List<EmailDetailsVO> emailDetails)
	{
		ResultStatus resultStatus = new ResultStatus();
		String host = IConstants.DEFAULT_MAIL_SERVER;
		try{
			
			if(emailDetails == null || emailDetails.size() == 0)
			{
				log.warn("Empty Mailing List - Please Check Once");
				resultStatus.setResultCode(ResultCodeMapper.FAILURE);
				return resultStatus;
			}
			
			Session session = getSessionObject(host);
			
			if(session == null)
			{
				log.error("MimeMessage Object is Not Created");
				resultStatus.setResultCode(ResultCodeMapper.FAILURE);
				return resultStatus;
			}
			
			for(EmailDetailsVO emailDetailsVO : emailDetails)
			{
			  try{
				MimeMessage message = new MimeMessage(session);
				message.setSubject(emailDetailsVO.getSubject());
			    message.setFrom(new InternetAddress(IConstants.FROMEMAILID));
			    message.setHeader("Return-Path", IConstants.FROMEMAILID);
			    message.setSentDate(dateUtilService.getCurrentDateAndTime());
			    message.setContent(emailDetailsVO.getContent(), "text/html");
			    message.setRecipient(Message.RecipientType.TO, new InternetAddress(emailDetailsVO.getToAddress()));
			    
			    if(host.equalsIgnoreCase(IConstants.LOCALHOST))
				{
			    	Transport transport = session.getTransport("smtp");
		            transport.connect(IConstants.HOST,IConstants.LOCALFROMEMAILID,IConstants.PASSWORD);
		            transport.sendMessage(message, message.getAllRecipients());
		            transport.close();
				}
			    else
			    	 Transport.send(message);
			  }catch(Exception e){
				  log.error("Exception in sending mail : ",e);
			  }
			}
			
			resultStatus.setResultCode(ResultCodeMapper.SUCCESS);
			return resultStatus;
		}catch (Exception e) {
			resultStatus.setExceptionEncountered(e);
			resultStatus.setExceptionMsg(e.getMessage());
			resultStatus.setResultCode(ResultCodeMapper.FAILURE);
			return resultStatus;
		}
	}
			 
	
	public Session getSessionObject(String host)
	{
		try{
			Session session = null;
			Properties props = null;
			
			if(host.equalsIgnoreCase(IConstants.LOCALHOST))
			{
		        props = new Properties();
		 
		        props.put("mail.smtp.host", IConstants.HOST);
		        props.put("mail.smtp.port", IConstants.PORT);
		        props.put("mail.smtp.user", IConstants.LOCALFROMEMAILID);
		        props.put("mail.smtp.socketFactory.port", IConstants.PORT);
		        props.put("mail.smtp.auth", "true");
		        props.put("mail.smtp.starttls.enable", "true");
		        props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
		        props.put("mail.smtp.socketFactory.fallback", "true");
		 
		        try {
		            	session = Session.getInstance(props, new javax.mail.Authenticator() {
						protected PasswordAuthentication getPasswordAuthentication() {
							return new PasswordAuthentication(IConstants.LOCALFROMEMAILID,IConstants.PASSWORD);
						}
					});
		            
		            session.setDebug(true);
		        }catch (Exception e) {
		        	return null;
				}
		            
			}
			else if(host.equalsIgnoreCase(IConstants.SERVER))
			{
				props = System.getProperties();
				session = Session.getDefaultInstance(props);
			}
			else
				log.warn("Please specify the host to send the Emails");
			
			return session;
		}catch (Exception e) {
			log.error("Error During Creating MimeMessage Object - Please Check Once, Exception is - "+e);
			return null;
		}
	}
	
	public ResultStatus freeUserSendingMailsToFriends(List<EmailDetailsVO> emaildtlslist,String host)
	{
		String subject="Invitation From Party Analyst";
		String text="Party Analyst inviting you to connect to your people,";
		text+="post your area problems and access to many features<br/><br/><br/>";
		text+="Please <a href=http://www.partyanalyst.com/freeUserRegistration.action> Click Here</a> To Register";
		List<EmailDetailsVO> mainEmailDetailsVoList = new ArrayList<EmailDetailsVO>(0);
		
		try{
			if(emaildtlslist!=null && emaildtlslist.size()>0){
				for(int i=0;i<emaildtlslist.size();i++){
					EmailDetailsVO mainEmailDetailsVO=new EmailDetailsVO(); 
					EmailDetailsVO emailDetailsVO=emaildtlslist.get(i);
					mainEmailDetailsVO.setSubject(subject);
					mainEmailDetailsVO.setToAddress(emailDetailsVO.getToAddress());
					String welcomename=emailDetailsVO.getWelcomeName();
					String content="Hi "+welcomename+",<br/>"+text;
					mainEmailDetailsVO.setContent(content);
					mainEmailDetailsVoList.add(mainEmailDetailsVO);
				}
			}
			
			ResultStatus rs = sendEmails(mainEmailDetailsVoList);
			return rs;	
		}catch(Exception e){
			log.error(e);
			return null;	
		}
		
	}
	*/
	/**
	 * This Service is used to seng email to the user about the abused comment status
	 * @param UserVO userVO
	 * @param String requestFrom
	 * @return ResultStatus
	 * @date 06-07-2013
	 */
	public ResultStatus sendMailToUserAboutAbuseComment(UserVO userVO,String requestFrom)
	{
		QuickRequestVO quickRequestVO = new QuickRequestVO();
    	ResultStatus rs = new ResultStatus();
    	String subject;
		String text;
    	subject = "";
    	subject = "Mail From www.electoralconnect.com";
    	
    	text = "";
    	text = "<div style='border:1px solid #CCCCCC;background:#EFFFFF;'>"+mailsTemplateService.getHeader()+"" 
    			+"<div style='margin-left:26px;margin-top:20px;margin-bottom: 7px;'>Hi <b>"+userVO.getFirstname()+" "+userVO.getLastname()+",</b></div>" +
    			"<div style='margin-left:45px;margin-bottom:40px;line-height: 1.5em;'>Here are the Comment details .<br>";
    	text += "Your Comment : " + " " +userVO.getDescription()+"<br>";
    	text += "User Name is : " + " " +userVO.getEmailId()+"<br>";
    	if(userVO.getPwd() != null)
    		text += "Your comment is abused beacuse your comment is not good it may hurt some one thats why we deleted your comment</br>";
    		
    	//text += "Your Password is :" +"  <b>"+ regVO.getPassword()+"</b>";
    	text +="</div><div style='margin: -17px 3px 0px 19px; padding-bottom: 18px;'>"+mailsTemplateService.getFooter()+"</div></div>";
    	quickRequestVO.setToEmailId(userVO.getEmailId());
    	quickRequestVO.setSubject(subject);
    	quickRequestVO.setText(text);
    	
    	 if(requestFrom.equalsIgnoreCase(IConstants.LOCALHOST)){
            	quickRequestVO.setFromEmailId(IConstants.LOCALFROMEMAILID);
            rs=sendMailFromLocalHost(quickRequestVO);
            }
            else if(requestFrom.equalsIgnoreCase(IConstants.SERVER))
            {
            	quickRequestVO.setFromEmailId(IConstants.FROMEMAILID);
            	rs = sendMailFromServer(quickRequestVO);
            }

    	return rs;
	}
	
}



