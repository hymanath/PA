package com.itgrids.partyanalyst.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMessage.RecipientType;

import org.apache.log4j.Logger;

import com.itgrids.partyanalyst.dao.IConstituencyDAO;
import com.itgrids.partyanalyst.dao.IDistrictDAO;
import com.itgrids.partyanalyst.dto.EmailDetailsVO;
import com.itgrids.partyanalyst.dto.QuickRequestVO;
import com.itgrids.partyanalyst.dto.RegistrationVO;
import com.itgrids.partyanalyst.dto.ResultCodeMapper;
import com.itgrids.partyanalyst.dto.ResultStatus;
import com.itgrids.partyanalyst.service.IMailService;
import com.itgrids.partyanalyst.utils.DateUtilService;
import com.itgrids.partyanalyst.utils.IConstants;

public class MailService implements IMailService{

	private IDistrictDAO districtDAO;
	private IConstituencyDAO constituencyDAO;
	private static final Logger log = Logger.getLogger(MailService.class);
	private DateUtilService dateUtilService = new DateUtilService();
	    
	 
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
    	if(registrationVO.getPassword()!= null)
    	text += "<b><font color=\"green\">Your Password is:</font></b>" +"  "+ registrationVO.getPassword();
    	
    	quickRequestVO.setToEmailId(registrationVO.getEmail());
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
    
    public ResultStatus sendRegistrationNotification(RegistrationVO registrationVO,String requestFrom){
    	
    	QuickRequestVO quickRequestVO = new QuickRequestVO();
    	ResultStatus rs = new ResultStatus();
    	String constituency=registrationVO.getContextPath()+"/constituencyPageAction.action?constituencyId="+registrationVO.getConstituency();
    	String district=registrationVO.getContextPath()+"/districtPageAction.action?districtId="+registrationVO.getDistrictId()+"&districtName="+registrationVO.getDistrict();
    	String login=registrationVO.getContextPath()+"/loginInputAction.action";
    	String sendMail="mailto:info@partyanalyst.com";
    	String subject;
		String text;
    	subject = "";
    	subject = "Welcome to PartyAnalyst.com"+" "+registrationVO.getFirstName();
    	
    	
    	text = "";
    	text +="<b>Hey"+"     "+ registrationVO.getFirstName();

    	text +="</b></font><br><br>Just a quick note to confirm that you are now a registered member of our Party Analyst Community.";

    	text +="<br><br>Thank you for joining - we're so glad to have you as the newest member of our growing Community.";

    	text +="<br><br>Here are your login details. Please keep them safe.";

    	text +="<br><br>Username:"+ " "+registrationVO.getEmail();
    	text +="<br>Password:"+" "+registrationVO.getPassword();
    	text +="<br><br>You can now connect with people, post problems, post comments and do lot more things. Why wait? Login now and start connecting with people. <br>Follow this link to log in <a href="+login+">Login</a>&nbsp;&nbsp;If you can't get login page click below url :";
    	text +="<br><br><a href="+login+" target='_blank'>"+login+"</a>";
    	text +="<br><br>Explore more about your Constituency : <a href="+constituency+">"+constituencyDAO.get((new Long(registrationVO.getConstituency()))).getName()+"</a>&nbsp;&nbsp;If you can't get constituency page click below url :";
    	text +="<br><br><a href="+constituency+" target='_blank'>"+constituency+"</a>";
    	text +="<br><br>Explore more about your  District : <a href="+district+">"+registrationVO.getDistrict()+"</a>&nbsp;&nbsp;If you can't get district page click below url :";
    	text +="<br><br><a href="+district+" target='_blank'>"+district+"</a>";
    	text +="<br><br><br>Please add this email which you like from Party Analyst won&#39;t go for junk email."; 
    	text +="<br>We hope to see you around and take part in our community!!!";
    	text +="<br><br>For suggestions and support contact us at <b><a href="+sendMail+">info@partyanalyst.com</a></b>";
    	text +="<br><br><b>Best Wishes,";
    	text +="<br><font color=\"green\">Party Analyst Team.</font></b>";

    	/*text +="<br><br>PS: Now that you are part of our community, why not invite your friends to join our community? You can not only connect and share your thoughts with them but also make them part of the change.";

    	text +="<br>INVITE FRIENDS NOW";*/



    	//text = "<b><font color=\"blue\">Thank You  For Registering with PartyAnalyst</font></b>";
    	
    	quickRequestVO.setToEmailId(registrationVO.getEmail());
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
	
	 public  ResultStatus sendArticleToAdmin(QuickRequestVO quickRequestVO,String requestFrom){
	        
		    	 ResultStatus rs = new  ResultStatus();
		    	
		    		String subject;
		    		String text;
		    		subject = "";
		    		subject= "User Interesting to Post Articles to PartyAnalyst"; 
		           
		           
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
		    		subject= " Articles On PartyAnalyst"; 

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
	
	public ResultStatus sendEmail(EmailDetailsVO emailDetails,String host)
	{
		try{
			if(emailDetails == null || host == null)
				return null;
			List<EmailDetailsVO> emailsList = new ArrayList<EmailDetailsVO>(0);
			emailsList.add(emailDetails);
			
			return sendEmails(emailsList,host);
		}catch (Exception e) {
			return null;
		}
	}
	public ResultStatus sendEmails(List<EmailDetailsVO> emailDetails,String host)
	{
		ResultStatus resultStatus = new ResultStatus();
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
			 
	
	private Session getSessionObject(String host)
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
	public ResultStatus freeUserSendingMailsToFriends(List<EmailDetailsVO> emaildtlslist,String host){
		String subject="Invitation From PartyAnalyst";
		String text="PartyAnalyst inviting you to connect to your people,";
		text+="post your area problems and access to many features<br/><br/><br/>";
		text+="Please <a href=http://www.partyanalyst.com/freeUserRegistration.action> Click Here</a> To Register";
		List<EmailDetailsVO> mainEmailDetailsVoList=new ArrayList();
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
					mainEmailDetailsVO.setHost(host);
					mainEmailDetailsVoList.add(mainEmailDetailsVO);
				}
			}
			
			ResultStatus rs=sendEmails(mainEmailDetailsVoList,host);
			return rs;	
		}catch(Exception e){
			e.printStackTrace();
		return null;	
		}
		
	}

}

