package com.itgrids.service.impl;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.itgrids.dao.ITdpResolutionDAO;
import com.itgrids.dao.ITdpResolutionTypeDAO;
import com.itgrids.dao.impl.TdpEmailDAO;
import com.itgrids.dto.MemberShipVO;
import com.itgrids.dto.TdpResolutionVo;
import com.itgrids.model.TdpResolution;
import com.itgrids.model.TdpResolutionType;
import com.itgrids.service.IResolutionMailService;
import com.itgrids.utils.DateUtilService;
import com.itgrids.utils.IConstants;

@Service
public class ResolutionMailService implements IResolutionMailService {
	 
	private static final Logger LOG = LoggerFactory.getLogger(ResolutionMailService.class);

	@Autowired
	private TdpEmailDAO tdpEmailDAO;
	
	@Autowired
	private ITdpResolutionDAO tdpResolutionDAO;
	
	@Autowired
	private ITdpResolutionTypeDAO tdpResolutionTypeDAO;
	
	@Transactional
	public String sentEmails(TdpResolutionVo tdpResolutionVo) {

		String subjectBody = null;
		try{
			List<String> listsubjects= tdpResolutionVo.getListSubjects();
			for(int i =0; i<listsubjects.size();i++){
				if(subjectBody ==null){
					subjectBody=listsubjects.get(i).toString();
				}else{
					subjectBody=subjectBody.concat(listsubjects.get(i).toString());
				}
			}
			MemberShipVO memberShipVO =getMemberShipDetails(tdpResolutionVo.getMembershipId());
			Date date = new Date();
			TdpResolution tdpResolution = new TdpResolution();
			tdpResolution.setDescription(tdpResolutionVo.getDescription());
			tdpResolution.setSubject(subjectBody);
			tdpResolution.setUpdatedTime(date);
			tdpResolution.setInsertedTime(date);
			tdpResolution.setMembershipId(tdpResolutionVo.getMembershipId());
			tdpResolution.setVideourl(tdpResolutionVo.getVideourl());
			tdpResolution.setIsDeleted("N");
			tdpResolutionDAO.save(tdpResolution);
			List<Object[]> list = tdpEmailDAO.getAllTdpEmailmodel();
			
			String body ="Hi All \n\n"+
					"\n My Topic is "+subjectBody+"\n "+tdpResolutionVo.getDescription()+
					"\n\n To Watch Video On Above Reoultion:\t"+tdpResolutionVo.getVideourl()
					+"\n\n Thanks & Regards, \n "+memberShipVO.getName();

			Session session = getSessionObject();
			DateUtilService dateUtilService = new DateUtilService();
			try{
					MimeMessage message = new MimeMessage(session);
					message.setSubject(subjectBody, "UTF-8");
				    message.setFrom(new InternetAddress(IConstants.FROMEMAILID));
				    message.setHeader("Content-Type", "text/plain; charset=UTF-8");
				    message.setSentDate(dateUtilService.getCurrentDateAndTime());
				    message.setContent(body, "text/plain; charset=UTF-8");
				    
				    for (Object[] params : list) 
					{
						 message.addRecipient(Message.RecipientType.TO, new InternetAddress(params[1] != null ? params[1].toString().trim() : ""));
					}
				    
				    Transport.send(message);
				    
				  }catch(Exception e){
					  LOG.error("Exception in sending mail : ",e);
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
	        props.put("mail.mime.charset", "charset=UTF-8");
	 
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

	public MemberShipVO getMemberShipDetails(Long memberShipId) {
	
		MemberShipVO memberShipVO = new MemberShipVO();
		try {

			String cadreurl = "http://mytdp.com/WebService/getCadreDetailsForPrinting/"+ memberShipId;
			URL obj = new URL(cadreurl);
			HttpURLConnection con = (HttpURLConnection) obj.openConnection();
			con.setRequestMethod("GET");
			con.setRequestProperty("Content-Type", "application/json;  charset=utf-8");
			int responseCode = con.getResponseCode();
			if (responseCode == HttpURLConnection.HTTP_OK) { // success
				BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream(),"utf8"));
				String inputLine;
				StringBuffer response = new StringBuffer();

				while ((inputLine = in.readLine()) != null) {
					response.append(inputLine);
				}
				in.close();
				JSONObject jobj = new JSONObject(response.toString());
				memberShipVO.setName(jobj.getString("voterName"));
				memberShipVO.setConstituency(jobj.getString("constituencyName"));
				
			} else {
				LOG.info("GET request not worked");
				return null;
			}
			return memberShipVO;

		} catch (IOException e) {
			e.printStackTrace();
			return null;
		 }
		
	}

	@Transactional
	public List<TdpResolutionType> getResolutions(String days) {
		
		JSONObject json = new JSONObject(days);
		String dayString = json.getString("daysStr");
		List<String> daysList = new ArrayList<String>();
		if(days != null && days.length() > 0){
			String[] daysArr = dayString.split(",");
			 if(daysArr != null && daysArr.length > 0){
				 for(int i = 0;i<daysArr.length;i++){
					 if(daysArr[i]==daysArr[0]){
						System.out.println(daysArr[0]); 
					 }
					 daysList.add(daysArr[i]);
				 }
			 }
		}
		List<Object[]> list = tdpResolutionTypeDAO.getResolutions(daysList);  
		List<TdpResolutionType> tdpResolutionTypeList = new ArrayList<TdpResolutionType>();
		for (Object[] param : list){
			TdpResolutionType tdpResolutionType = new TdpResolutionType();
			tdpResolutionType.setResolutionName(param[1] != null ? param[1].toString().trim() : "");
			tdpResolutionType.setDay(param[2] != null ? param[2].toString().trim() : "");
			tdpResolutionType.setTdpResolutionTypeId(Long.valueOf(param[0] != null ? param[0].toString().trim() : "0"));
			
			tdpResolutionTypeList.add(tdpResolutionType);
		}
		
		return tdpResolutionTypeList;
	}
}
