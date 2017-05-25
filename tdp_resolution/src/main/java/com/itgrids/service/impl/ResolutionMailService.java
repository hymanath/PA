package com.itgrids.service.impl;

import java.util.List;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.Session;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.itgrids.dao.impl.TdpEmailDAO;
import com.itgrids.service.IResolutionMailService;
import com.itgrids.utils.CommonMethodsUtilService;
import com.itgrids.utils.IConstants;

@Service
public class ResolutionMailService implements IResolutionMailService {
	 
	@Autowired
	private TdpEmailDAO tdpEmailDAO;
	
	
	@Transactional
	public String sentEmails(String json) {

		try{
			CommonMethodsUtilService commonMethodsUtilService = new CommonMethodsUtilService();
			
			List<Object[]> allTdpEmailmodel = tdpEmailDAO.getAllTdpEmailmodel();

			 for (Object[] model : allTdpEmailmodel) 
			 {
				Properties properties = System.getProperties();
				properties.setProperty("mail.smtp.host", IConstants.HOST);
				Session session = Session.getDefaultInstance(properties);
				MimeMessage message = new MimeMessage(session);
				message.setFrom(new InternetAddress(IConstants.FROMEMAILID));
				message.addRecipient(Message.RecipientType.TO, new InternetAddress(commonMethodsUtilService.getStringValueForObject(model[0].toString())));
				message.setSubject("Ping");
				message.setText("Hello, this is example of sending email  ");
			 }
			return "SUCCESS";
		}catch(Exception e){
			
			return "Failure";
		}
		
	}

}
