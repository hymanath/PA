package com.itgrids.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.itgrids.dto.EmailDetailsVO;
import com.itgrids.dto.ResultCodeMapper;
import com.itgrids.service.IMailService;
import com.itgrids.service.IMailsTemplateService;
import com.itgrids.utils.DateUtilService;
import com.itgrids.utils.IConstants;

@Service
@Transactional
public class MailService implements IMailService {

	private static final Logger LOG = Logger.getLogger(SolidWasteManagementService.class);
	@Autowired
	private DateUtilService dateUtilService;
	@Autowired
	private IMailsTemplateService mailsTemplateService;

	@Override
	public EmailDetailsVO sendEmails(List<EmailDetailsVO> emailDetails) {
		EmailDetailsVO resultStatus = new EmailDetailsVO();
		{
			String host = IConstants.DEFAULT_MAIL_SERVER;
			try {

				if (emailDetails == null || emailDetails.size() == 0) {
					LOG.warn("Empty Mailing List - Please Check Once");
					resultStatus.setResultCode(ResultCodeMapper.FAILURE);
					return resultStatus;
				}

				Session session = getSessionObject(host);

				if (session == null) {
					LOG.error("MimeMessage Object is Not Created");
					resultStatus.setResultCode(ResultCodeMapper.FAILURE);
					return resultStatus;
				}

				for (EmailDetailsVO emailDetailsVO : emailDetails) {
					try {
						MimeMessage message = new MimeMessage(session);
						message.setSubject(emailDetailsVO.getSubject());
						message.setFrom(new InternetAddress(IConstants.LOCALFROMEMAILID));
						message.setHeader("Return-Path", IConstants.LOCALFROMEMAILID);
						message.setSentDate(dateUtilService.getCurrentDateAndTime());
						message.setContent(emailDetailsVO.getContent(), "text/html");
						message.setRecipient(Message.RecipientType.TO,
								new InternetAddress(emailDetailsVO.getToAddress()));
						message.getContent();
						if (host.equalsIgnoreCase(IConstants.LOCALHOST)) {
							Transport transport = session.getTransport("smtp");
							transport.connect(IConstants.HOST, IConstants.LOCALFROMEMAILID, IConstants.PASSWORD);
							transport.sendMessage(message, message.getAllRecipients());
							transport.close();
						} else
							Transport.send(message);
						resultStatus.setMessage(IConstants.SUCCESS);
						resultStatus.setResultCode(ResultCodeMapper.SUCCESS);
					} catch (Exception e) {
						LOG.error("Exception in sending mail : ", e);
						resultStatus.setMessage(IConstants.FAILURE);
						resultStatus.setResultCode(ResultCodeMapper.FAILURE);
					}
				}
				return resultStatus;
			} catch (Exception e) {
				resultStatus.setResultCode(ResultCodeMapper.FAILURE);
				return resultStatus;
			}
		}
	}

	@Override
	public EmailDetailsVO sendEmail(EmailDetailsVO emailDetails) {
		try {
			if (emailDetails == null)
				return null;
			List<EmailDetailsVO> emailsList = new ArrayList<EmailDetailsVO>(0);
			emailsList.add(emailDetails);

			return sendEmails(emailsList);
		} catch (Exception e) {
			return null;
		}
	}

	@Override
	public EmailDetailsVO sendEmailStatusForJob(String content) {
		EmailDetailsVO rs = new EmailDetailsVO();
		try {

			EmailDetailsVO emailDetailsVo = new EmailDetailsVO();
			String subject;
			subject = "";
			String text = "";
			subject = "Job Schedular Status";
			text = "<div style='border:1px solid #CCCCCC;background:#EFFFFF;'>" + mailsTemplateService.getHeader() + "";
			text += "<div style='margin-left:26px;margin-top:20px;margin-bottom: 7px;'><b>Hi Admin Group,</b></div>";
			text += "<div style='margin-left:45px;margin-bottom:40px;line-height: 1.5em;'>";
			text += "" + content + " ";
			text += "</div>";
			text += "<div style='margin: -17px 3px 0px 19px; padding-bottom: 18px;'>" + mailsTemplateService.getFooter()
					+ "</div></div>";
			String emails = "a.dakavaram@itgrids.com, suresh.bathula@itgrids.com,sanjeev.duddela@itgrids.com,chandrashekar.gurudu@itgrids.com";
			String emailArr[] = emails.split(",");
			for (String email : emailArr) {
				emailDetailsVo.setContent(text);
				emailDetailsVo.setSubject(subject);
				emailDetailsVo.setToAddress(email.toString());
				emailDetailsVo.setFromAddress(IConstants.LOCALFROMEMAILID);
				rs = sendEmail(emailDetailsVo);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return rs;

	}

	@Override
	public Session getSessionObject(String host) {
		try {
			Session session = null;
			Properties props = null;

			if (host.equalsIgnoreCase(IConstants.SERVER)) {
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
							return new PasswordAuthentication(IConstants.LOCALFROMEMAILID, IConstants.PASSWORD);
						}
					});

					session.setDebug(true);
				} catch (Exception e) {
					return null;
				}

			} else if (host.equalsIgnoreCase(IConstants.SERVER)) {
				props = System.getProperties();
				session = Session.getDefaultInstance(props);
			} else
				LOG.warn("Please specify the host to send the Emails");

			return session;
		} catch (Exception e) {
			LOG.error("Error During Creating MimeMessage Object - Please Check Once, Exception is - " + e);
			return null;
		}
	}
}
