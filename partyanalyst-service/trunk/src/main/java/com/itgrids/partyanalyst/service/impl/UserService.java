package com.itgrids.partyanalyst.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMessage.RecipientType;

import org.apache.log4j.Logger;

import com.itgrids.partyanalyst.dao.IUserDAO;
import com.itgrids.partyanalyst.dto.QuickRequestVO;
import com.itgrids.partyanalyst.dto.ResultCodeMapper;
import com.itgrids.partyanalyst.dto.ResultStatus;
import com.itgrids.partyanalyst.service.IUserService;
import com.itgrids.partyanalyst.utils.IConstants;

public class UserService implements IUserService{
	public static Logger log = Logger.getLogger(UserService.class); 
	
	private IUserDAO userDAO;
	private ResultStatus resultStatus;
	
	public ResultStatus getResultStatus() {
		return resultStatus;
	}
	public void setResultStatus(ResultStatus resultStatus) {
		this.resultStatus = resultStatus;
	}
	public IUserDAO getUserDAO() {
		return userDAO;
	}
	public void setUserDAO(IUserDAO userDAO) {
		this.userDAO = userDAO;
	}
	
	/**
	 * @author srishailam
	 * @param Long
	 * @return Long
	 * 
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public Long getUserDistrictByUserId(Long userId){
		log.debug("entered into getUserDistrictByUserId() method ");
		List userIds = new ArrayList();
		long userDistrictId = 0L;
	//	List locationIds = null;
		Object[] values = null;
		try{
		userIds.add(userId);
		List<Object[]> userLocations = userDAO.getAnanymousUserLocationDetailsByIds(userIds);
		if(userLocations !=null && userLocations.size()>0){
			values = (Object[])userLocations.get(0);
			/*locationIds = new ArrayList();
			locationIds.add((Long)values[0]);
			locationIds.add((Long)values[1]);
			locationIds.add((Long)values[2]);
			locationIds.add((Long)values[3]);
			locationIds.add((Long)values[4]);
			locationIds.add((Long)values[5]);*/
			userDistrictId = (Long)values[2];
			}
		}catch(Exception e){
			log.error("error occured in getUserDistrictByUserId() method");
			e.printStackTrace();
			resultStatus.setResultCode(ResultCodeMapper.FAILURE);
		}
		return userDistrictId;
	}
	//Sending Mails Using Java Mail API.
		public synchronized ResultStatus sendMail(QuickRequestVO quickRequestVO)
		{
			  
			log.debug("Entered into sendMail() Method"); 	
			String STARTTLS = "true";
		    String AUTH = "true";
		    String SOCKET_FACTORY = "javax.net.ssl.SSLSocketFactory";
		    
		    ResultStatus rs=new ResultStatus();
	  	
	      Properties props = new Properties();
	       props.put("mail.smtp.host", IConstants.HOST);
	       props.put("mail.smtp.port", IConstants.PORT);
	       props.put("mail.smtp.user", IConstants.LOCALFROMEMAILID);
	       props.put("mail.smtp.auth", AUTH);
	       props.put("mail.smtp.socketFactory.class", SOCKET_FACTORY);
	       props.put("mail.smtp.socketFactory.port", IConstants.PORT);
	       props.put("mail.smtp.socketFactory.fallback", "true");
	       props.put("mail.smtp.starttls.enable", STARTTLS);
		    
	      try {
	          final String fromEmailId = quickRequestVO.getFromEmailId();
	        
	          Session session = Session.getInstance(props, new javax.mail.Authenticator() {
					protected PasswordAuthentication getPasswordAuthentication() {
						return new PasswordAuthentication(fromEmailId,IConstants.PASSWORD);
					}
				});
	          session.setDebug(true);
	         
	          MimeMessage message = new MimeMessage(session);
	          message.setFrom(new InternetAddress(quickRequestVO.getFromEmailId()));
	          message.addRecipient(RecipientType.TO, new InternetAddress(quickRequestVO.getToEmailId()));
	          message.setHeader("Return-Path", IConstants.LOCALFROMEMAILID);
	          message.setSentDate(new java.util.GregorianCalendar().getTime());
			    message.setSubject(quickRequestVO.getSubject());
	          message.setContent(quickRequestVO.getText(),"text/html");
	          Transport.send(message);
	          
	          rs.setExceptionMsg("Your Request sent successfully");
	          rs.setResultCode(ResultCodeMapper.SUCCESS);
	          
	      } 
	      catch (Exception e) {
	    	  log.error("Exception Occured in sendMailFromLocalHost() Method, Exception is - "+e);
	          rs.setExceptionEncountered(e);
	          rs.setExceptionMsg(e.getMessage());
	          rs.setResultCode(ResultCodeMapper.FAILURE);
	      }
	  	return rs;
	  
	  }
}
