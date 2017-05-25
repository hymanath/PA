package com.itgrids.service.impl;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.itgrids.dao.ITdpResolutionDAO;
import com.itgrids.dao.impl.TdpEmailDAO;
import com.itgrids.dto.TdpResolutionVo;
import com.itgrids.model.TdpResolution;
import com.itgrids.service.IResolutionMailService;
import com.itgrids.utils.CommonMethodsUtilService;
import com.itgrids.utils.DateUtilService;
import com.itgrids.utils.IConstants;

@Service
public class ResolutionMailService implements IResolutionMailService {
	 
	private static final Logger LOG = LoggerFactory.getLogger(ResolutionMailService.class);

	@Autowired
	private TdpEmailDAO tdpEmailDAO;
	
	@Autowired
	private ITdpResolutionDAO tdpResolutionDAO;
	
	@Transactional
	public String sentEmails(TdpResolutionVo tdpResolutionVo) {

		try{
			Date date = new Date();
			
			CommonMethodsUtilService commonMethodsUtilService = new CommonMethodsUtilService();
			
			TdpResolution tdpResolution = new TdpResolution();
			tdpResolution.setDescription(tdpResolutionVo.getDescription());
			tdpResolution.setSubject(tdpResolutionVo.getSubject());
			tdpResolution.setUpdatedTime(date);
			tdpResolution.setInsertedTime(date);
			tdpResolution.setVideourl(tdpResolutionVo.getVideourl());
			tdpResolution.setIsDeleted("N");
			tdpResolutionDAO.save(tdpResolution);
			
			List<Object[]> list = tdpEmailDAO.getAllTdpEmailmodel();
			
			Session session = getSessionObject();
			DateUtilService dateUtilService = new DateUtilService();
			
			for (Object[] params : list) 
			{
				 
				try{
					MimeMessage message = new MimeMessage(session);
					message.setSubject(tdpResolutionVo.getSubject());
				    message.setFrom(new InternetAddress(IConstants.FROMEMAILID));
				    message.setHeader("Return-Path", IConstants.FROMEMAILID);
				    message.setSentDate(dateUtilService.getCurrentDateAndTime());
				    message.setContent(tdpResolutionVo.getDescription(), "text/html");
				    message.setRecipient(Message.RecipientType.TO, new InternetAddress(params[1] != null ? params[1].toString().trim() : ""));
				    
				    Transport.send(message);
				    
				  }catch(Exception e){
					  LOG.error("Exception in sending mail : ",e);
				  }
			 }
			return "SUCCESS";
		}catch(Exception e){
			
			return "Failure";
		}
		
	}
	
	public Date convertToDateFormet(String dateStr) {
		Date date = null;
		try {
			SimpleDateFormat originalFormat = new SimpleDateFormat("yyyy-MM-dd");
			date = originalFormat.parse(dateStr);
		} catch (Exception e) {
			LOG.error("Exception raised in convertToDateFormet method in CadreRegistrationAction Action",e);
		}
		return date;
	}
	
	public Session getSessionObject()
	{
		try{
			Session session = null;
			Properties props = null;
			
	        props = new Properties();
	 
	        props.put("mail.smtp.host", IConstants.HOST);
	        props.put("mail.smtp.port", IConstants.PORT);
	        props.put("mail.smtp.user", IConstants.FROMEMAILID);
	        props.put("mail.smtp.socketFactory.port", IConstants.PORT);
	        props.put("mail.smtp.auth", "true");
	        props.put("mail.smtp.starttls.enable", "true");
	        props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
	        props.put("mail.smtp.socketFactory.fallback", "true");
	 
	        try {
            	session = Session.getInstance(props, new javax.mail.Authenticator() {
				protected PasswordAuthentication getPasswordAuthentication() {
					return new PasswordAuthentication(IConstants.FROMEMAILID,IConstants.PASSWORD);
					}
				});
		            
		        session.setDebug(true);
		        }catch (Exception e) {
		        	return null;
				}
		            
			return session;
		}catch (Exception e) {
			LOG.error("Error During Creating MimeMessage Object - Please Check Once, Exception is - "+e);
			return null;
		}
	}
}
