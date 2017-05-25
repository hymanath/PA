package com.itgrids.service;

import java.util.List;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.Session;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.appfuse.service.impl.GenericManagerImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.itgrids.dao.TdpEmailDao;
import com.itgrids.model.TdpEmailmodel;
import com.itgrids.utils.CommonMethodsUtilService;
import com.itgrids.utils.IConstants;

@Service
public class ResolutionmailserviceImpl extends GenericManagerImpl<TdpEmailmodel, Integer> implements IResolutionmailservice {
	 
	
	private final TdpEmailDao tdpEmailDao;
	@Autowired
	public ResolutionmailserviceImpl(final TdpEmailDao tdpEmailDao){
		
		this.tdpEmailDao=tdpEmailDao;
	}
	
	@Transactional
	public String sentmail(String json) {

		try{
			CommonMethodsUtilService commonMethodsUtilService = new CommonMethodsUtilService();
			List<Object[]> allTdpEmailmodel = tdpEmailDao.getAllTdpEmailmodel();
			//List<TdpEmailVO> tdpEmailVOs = new ArrayList<TdpEmailVO>();

			 for (Object[] model : allTdpEmailmodel) {
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
