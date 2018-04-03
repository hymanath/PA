package com.itgrids.service;

import java.util.List;

import javax.mail.Session;

import com.itgrids.dto.EmailDetailsVO;

public interface IMailService {

    public EmailDetailsVO sendEmails(List<EmailDetailsVO> emailDetails);	
	public EmailDetailsVO sendEmail(EmailDetailsVO emailDetails);
	public EmailDetailsVO sendEmailStatusForJob(String content);
	public Session getSessionObject(String host);
		
	
}
