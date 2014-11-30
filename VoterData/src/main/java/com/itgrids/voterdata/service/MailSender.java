package com.itgrids.voterdata.service;

import java.util.Properties;

import javax.mail.Session;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
public class MailSender {

	public static void main(String[] args)
	{
		
	}
	
	public void sendEmail(String to)
	{
		try{
			String from = "info.itgrids@gmail.com";
			final String username = "info.itgrids@gmail.com";
			final String password = "1tGridsIndia";
			
			String host = "smtp.gmail.com";

		    Properties props = new Properties();
		    props.put("mail.smtp.auth", "true");
		    props.put("mail.smtp.starttls.enable", "true");
		    props.put("mail.smtp.host", host);
		    props.put("mail.smtp.port", "587");

		      Session session = Session.getInstance(props,
		      new javax.mail.Authenticator() {
		         protected PasswordAuthentication getPasswordAuthentication() {
		            return new PasswordAuthentication(username, password);
		         }
		      });

		      try {
		}catch(Exception e)
		{
			
		}
}
