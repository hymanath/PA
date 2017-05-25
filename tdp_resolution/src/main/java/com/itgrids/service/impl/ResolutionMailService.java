package com.itgrids.service.impl;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.Session;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.itgrids.dao.ITdpResolutionMailDAO;
import com.itgrids.dao.impl.TdpEmailDAO;
import com.itgrids.dto.TdpResolutionVo;
import com.itgrids.model.TdpResolutionMail;
import com.itgrids.service.IResolutionMailService;
import com.itgrids.utils.CommonMethodsUtilService;
import com.itgrids.utils.IConstants;

@Service
public class ResolutionMailService implements IResolutionMailService {
	 
	private static final Logger LOG = LoggerFactory.getLogger(ResolutionMailService.class);

	@Autowired
	private TdpEmailDAO tdpEmailDAO;
	
	@Autowired
	private ITdpResolutionMailDAO iTdpResolutionMailDAO;
	
	@Transactional
	public String sentEmails(TdpResolutionVo tdpResolutionVo) {

		try{
			CommonMethodsUtilService commonMethodsUtilService = new CommonMethodsUtilService();
			
			TdpResolutionMail tdpResolutionMail = new TdpResolutionMail();
			tdpResolutionMail.setDescription(tdpResolutionVo.getDescription());
			tdpResolutionMail.setSubject(tdpResolutionVo.getSubject());
			tdpResolutionMail.setUpdatedTime(convertToDateFormet(tdpResolutionVo.getUpdatedTime()));
			tdpResolutionMail.setInsertedTime(convertToDateFormet(tdpResolutionVo.getInsertedTime()));
			tdpResolutionMail.setVideourl(tdpResolutionVo.getVideourl());
			iTdpResolutionMailDAO.save(tdpResolutionMail);
			List<Object[]> allTdpEmailmodel = tdpEmailDAO.getAllTdpEmailmodel();

			 for (Object[] model : allTdpEmailmodel) 
			 {
				Properties properties = System.getProperties();
				properties.setProperty("mail.smtp.host", IConstants.HOST);
				Session session = Session.getDefaultInstance(properties);
				MimeMessage message = new MimeMessage(session);
				message.setFrom(new InternetAddress(IConstants.FROMEMAILID));
				message.addRecipient(Message.RecipientType.TO, new InternetAddress(commonMethodsUtilService.getStringValueForObject(model[0].toString())));
				message.setSubject(tdpResolutionVo.getSubject());
				message.setText(tdpResolutionVo.getDescription());
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

}
