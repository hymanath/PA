package com.itgrids.partyanalyst.service.impl;

import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import java.io.File;
import java.io.FileWriter;
import java.security.Key;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Random;

import javax.crypto.spec.SecretKeySpec;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.apache.log4j.Logger;
import org.codehaus.jettison.json.JSONObject;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import com.itgrids.partyanalyst.dao.IOtpDetailsDAO;
import com.itgrids.partyanalyst.dao.ITdpCadreDAO;
import com.itgrids.partyanalyst.dto.ResultStatus;
import com.itgrids.partyanalyst.model.OtpDetails;
import com.itgrids.partyanalyst.saml.SamlSignatureUtil;
import com.itgrids.partyanalyst.service.ISmsService;
import com.itgrids.partyanalyst.service.IZohoAlertService;
import com.itgrids.partyanalyst.utils.CommonMethodsUtilService;
import com.itgrids.partyanalyst.utils.DateUtilService;
import com.itgrids.partyanalyst.utils.IConstants;
import com.itgrids.partyanalyst.utils.ImageAndStringConverter;
import com.itgrids.partyanalyst.utils.RandomNumberGeneraion;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;

public class ZohoAlertService implements IZohoAlertService {
	private static final Logger LOG = Logger.getLogger(ZohoAlertService.class);

	private ITdpCadreDAO tdpCadreDAO;
	private ISmsService smsCountrySmsService;
	private DateUtilService dateUtilService;
	private IOtpDetailsDAO otpDetailsDAO; 
	private CommonMethodsUtilService commonMethodsUtilService ;

	public void setTdpCadreDAO(ITdpCadreDAO tdpCadreDAO) {
		this.tdpCadreDAO = tdpCadreDAO;
	}

	

	public void setSmsCountrySmsService(ISmsService smsCountrySmsService) {
		this.smsCountrySmsService = smsCountrySmsService;
	}



	public DateUtilService getDateUtilService() {
		return dateUtilService;
	}

	public void setDateUtilService(DateUtilService dateUtilService) {
		this.dateUtilService = dateUtilService;
	}

	public IOtpDetailsDAO getOtpDetailsDAO() {
		return otpDetailsDAO;
	}

	public void setOtpDetailsDAO(IOtpDetailsDAO otpDetailsDAO) {
		this.otpDetailsDAO = otpDetailsDAO;
	}

	public CommonMethodsUtilService getCommonMethodsUtilService() {
		return commonMethodsUtilService;
	}

	public void setCommonMethodsUtilService(CommonMethodsUtilService commonMethodsUtilService) {
		this.commonMethodsUtilService = commonMethodsUtilService;
	}

	@Override
	public String getMobileNoByMemberShip(String membershipId) {
		LOG.fatal(" Calling Zoho App Webservice For OTP with Membership ID -"+membershipId);
		String mobileNo = null;
		Long cadreId=0l;
		try{
			if(membershipId!=null && !membershipId.trim().isEmpty() && membershipId.trim().equalsIgnoreCase(IConstants.ZOHO_IOS_STATIC_MEMBERSHIP_ID)){
				return IConstants.ZOHO_IOS_STATIC_MOBILE_NO;
			}else{
				List<Object[]> mobileNoList = tdpCadreDAO.getMobileNoOfMembership(membershipId);
				if(mobileNoList!=null && mobileNoList.size()>0){
					cadreId=mobileNoList.get(0)[0]!=null?(Long)mobileNoList.get(0)[0]:null;
					mobileNo=mobileNoList.get(0)[1]!=null?mobileNoList.get(0)[1].toString():null;
					if(mobileNo!=null && mobileNo.length()>0){
						generatingAndSavingOTPDetails(cadreId,mobileNo,membershipId);
					}
				}else{
					return null;
				}
			}
		}catch (Exception e) {
			LOG.error("Exception raised at getMobileNoByMemberShip in ZohoAlertService Class ", e);
		}
		return mobileNo;
	}
	
	
	
public String generatingAndSavingOTPDetails(Long tdpCadreId,String mobileNoStr,String membershipId){
		
		String status = null;
		try {
			String mobileNo=mobileNoStr.trim();
			if(mobileNoStr.length()>10)
				mobileNo = mobileNoStr.substring(mobileNoStr.length() - 10,mobileNoStr.length());

			List<Object[]> existingOTPDtls = otpDetailsDAO.isExistOTPDetails(mobileNo,new DateUtilService().getCurrentDateAndTime());
			if(existingOTPDtls != null && existingOTPDtls.size()>0L){
				Object[] obj = existingOTPDtls.get(0);
				String otp = commonMethodsUtilService.getStringValueForObject(obj[0]);
				String dateStr = commonMethodsUtilService.getStringValueForObject(obj[1]);
				
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				Date date = sdf.parse(dateStr);
				Calendar calendar = Calendar.getInstance();
				calendar.setTime(date);
				// next 15 min date time
				
				 long duration = (Calendar.getInstance().getTimeInMillis() - calendar.getTimeInMillis());
		    	 if(duration < 15 * 60 * 1000)// if duration less than 15 min 
		    	 {
		    		    calendar.add(Calendar.MINUTE, 15);
						String finalDateStr = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss").format(calendar.getTime());
						String message = "your OTP is "+otp+" . This OTP Validate upto Next "+finalDateStr+" .";
						String[] phoneNumbers  = {mobileNoStr.toString()};
						smsCountrySmsService.sendOTPSmsFromAdminForZohoUser(message, true,phoneNumbers);
		    	 }
			}else{
				status=sendOtp(mobileNoStr,tdpCadreId,membershipId);
			}
			
			
		} catch (Exception e) {
			e.printStackTrace();
			LOG.error("Exception raised in generatingAndSavingOTPDetails in ZohoAlertService service", e);
			return "failure";
		}
		return status;
	}
	


	@Override
	public String sendOtp(String phoneNumber,Long cadreId,String membershipId) {
		OtpDetails otpDetails = null;
		try {
			if(phoneNumber!=null && phoneNumber.length()==10){
				
				RandomNumberGeneraion rnd = new RandomNumberGeneraion();
				int otpRand = 0;
			
				while(otpRand <= 0 ){
					 otpRand = rnd.randomGenerator(6);
				}
					String otpNum = String.valueOf(otpRand);
					String msg = "Your OTP is "+otpNum+" . This OTP is valid for next 15 mins.";
					String[] phoneNumbers = {phoneNumber.toString()};
						ResultStatus otpStatus = smsCountrySmsService.sendOTPSmsFromAdminForZohoUser(msg, true, phoneNumbers);
						otpDetails = new OtpDetails();
						otpDetails.setMobileNo(phoneNumber);
						otpDetails.setOtp(Integer.toString(otpRand));
						otpDetails.setTdpCadreId(cadreId);
						otpDetails.setGeneratedTime(dateUtilService.getCurrentDateAndTime());
						otpDetails.setUpdatedTime(dateUtilService.getCurrentDateAndTime());
						otpDetails.setIsValid('Y');
						otpDetails.setMembershipId(membershipId);
						otpDetailsDAO.save(otpDetails);
						
			}
		} catch (Exception e) {
			LOG.error("Exception raised in sendOtp in ZohoAlertService service", e);
		}
		return "Success";
	}
	
	
	
	public JSONObject checkOTPDetails(JSONObject jobj){
		JSONObject status =  new JSONObject();
		
		try {
			
			if (jobj.getString("memberShipId").trim().equalsIgnoreCase(IConstants.ZOHO_IOS_STATIC_MEMBERSHIP_ID)) {
				if (jobj.getString("otp").trim().equalsIgnoreCase(IConstants.ZOHO_IOS_STATIC_MEMBERSHIP_ID_OTP)) {
					status.put("memberShipId", jobj.getString("memberShipId"));
					status.put("status", "success");
				} else {
					status.put("membershipId", "");
					status.put("status", "failed");
				}
				return status;
			}
			
			Date currentTime = dateUtilService.getCurrentDateAndTime();

			List<Object[]> otpDetails = otpDetailsDAO.checkOTPDetails(jobj.getString("otp"), jobj.getString("memberShipId"), currentTime);
			if(otpDetails != null && otpDetails.size()>0)
			{
				Object[] obj = otpDetails.get(otpDetails.size()-1);
				
				Long otpDetailsId = obj[0] != null ? Long.valueOf(obj[0].toString()):0L;
				String  generatedTime = obj[1] != null ? obj[1].toString():"";
				
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				Date date = sdf.parse(generatedTime);
				Calendar calendar = Calendar.getInstance();
				calendar.setTime(date);
				 long duration = (Calendar.getInstance().getTimeInMillis() - calendar.getTimeInMillis());
				 
				 if(duration < 15 * 60 * 1000)// if duration less than 15 min 
		    	 {				 
					if(otpDetailsId != null && otpDetailsId.longValue() > 0l){
						OtpDetails otpVerification = otpDetailsDAO.get(otpDetailsId);
						otpVerification.setIsValid('N');
						otpVerification.setUpdatedTime(dateUtilService.getCurrentDateAndTime());
						otpVerification = otpDetailsDAO.save(otpVerification);
						status.put("memberShipId",jobj.getString("memberShipId"));
						status.put("status", "success");
						return 	status;
					}
					else{
						status.put("membershipId", "");
						status.put("status", "failed");
					}
		    	 }
				 else{
					 status.put("membershipId", "");
					 status.put("status", "failed");
				 }
			}
			else{
				status.put("membershipId", "");
				status.put("status", "failed");
			}
			
		} catch (Exception e) {
			LOG.error("Exception Occured in checkOTPDetails() in ZohoAlertService class.",e);
		}
		return status;
	}

	public String generateJwtForZoho(String userToken) {

		try {
			if(userToken!=null && !userToken.trim().isEmpty()){
				List<Object[]> mobileNoList = tdpCadreDAO.getMobileNoOfMembership(userToken.split("@")[0]);
				if (mobileNoList != null && mobileNoList.size() > 0) {
					return jwtCodeGeneration(userToken);
				}
			}
			
		} catch (Exception e) {
			LOG.error("Exception Occured in generateJwtForZoho() in ZohoAlertService class.", e);
		}

		return "inValidMemberShipID";
	}

	public String jwtCodeGeneration(String userToken) {
		String jwt=null;
		try {
			String secretKey = IConstants.ZOHO_JWT_SECRETKEY;
		      long notBeforeMillis = System.currentTimeMillis();
		      long notAfterMillis = notBeforeMillis + 300000;

		      SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;

		      byte[] apiKeySecretBytes = secretKey.getBytes();
		      Key signingKey = new SecretKeySpec(apiKeySecretBytes, signatureAlgorithm.getJcaName());

		      JwtBuilder builder = Jwts.builder().signWith(signatureAlgorithm, signingKey);

		      jwt = builder.claim("email_verified", true)
		                                  .claim("not_after", notAfterMillis)
		                                  .claim("not_before", notBeforeMillis)
		                                  .claim("email", userToken).compact();
		      return jwt;
		} catch (Exception e) {
			LOG.error("Exception Occured in generateJwtForZoho() in ZohoAlertService class.", e);
		}
		return null;
	}

	
    /*
     * 
     * @see com.itgrids.partyanalyst.service.IZohoAlertService#sendSms(java.lang.String, java.lang.String)
     */

	@Override
	public JSONObject sendSms(String phoneNumber,String message) {
		JSONObject status = new JSONObject();
		try {
			if(phoneNumber!=null && phoneNumber.length()>0){
				String[] phnNoAry = {phoneNumber.toString()};
 				smsCountrySmsService.sendOTPSmsFromAdminForZohoUser(message, true, phnNoAry);
 				status.put("Status", "Success");
			}
		} catch (Exception e) {
			LOG.error("Exception Occured in sendSms() in ZohoAlertService class.",e);
		}
		return status;
	}



	public File getSamlXmlFile(String memberShipId,String successMsg,String firstName,String lastName) {
		File file = null;
		try{
	        DocumentBuilderFactory dbFactory =
	        DocumentBuilderFactory.newInstance();
	        DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
	        Document doc = dBuilder.newDocument();
	        Date currentDate = dateUtilService.getCurrentDateAndTime();
	        SimpleDateFormat dateFormatter = new SimpleDateFormat(
	                "yyyy-MM-dd'T'HH:mm:ss'Z'");
	        Date aDate = new Date(System.currentTimeMillis()+5*60*1000);
	        Date bDate = new Date(System.currentTimeMillis()-5*60*1000);
	        String strUTCDate = dateFormatter.format(currentDate);
	        String after = dateFormatter.format(aDate);
	        String before = dateFormatter.format(bDate);
	      
	        // root element
	        Element rootElement = doc.createElement("samlp:Response");
	        //Element rootElement = doc.createElementNS("samlp:Response","xmlns:xsi");
	        rootElement.setAttribute("InResponseTo","_47e32a7f3431474d8c8ce81e6c751a5d1518774");
	        rootElement.setAttribute("Destination","https://accounts.zoho.com/samlresponse/mytdp.com");
	        rootElement.setAttribute("IssueInstant",strUTCDate);
	        rootElement.setAttribute("Version","2.0");
	        rootElement.setAttribute("ID","R992fdbeb2a4873b04c105a8a5f74c1a267ed0f22");
	        rootElement.setAttribute("xmlns:samlp","urn:oasis:names:tc:SAML:2.0:protocol");
	        doc.appendChild(rootElement);
	        
	        Element issuer = doc.createElement("saml:Issuer");
	        issuer.appendChild(doc.createTextNode(memberShipId));
	        rootElement.appendChild(issuer);
	        
	        Element status = doc.createElement("samlp:Status");
	        Element statusCode = doc.createElement("samlp:StatusCode");
	        statusCode.setAttribute("Value","urn:oasis:names:tc:SAML:2.0:status:"+successMsg);
	        status.appendChild(statusCode);
	        rootElement.appendChild(status);
	        Element assertion = doc.createElement("saml:Assertion");
	        assertion.setAttribute("IssueInstant",strUTCDate);
	        assertion.setAttribute("Version","2.0");
	        assertion.setAttribute("ID","pfxd7c4f2cc-0365-42d5-b72a-a09070be3b36");
	        assertion.setAttribute("xmlns:saml","urn:oasis:names:tc:SAML:2.0:assertion");
	        assertion.setAttribute("xmlns:xsi","http://www.w3.org/2001/XMLSchema-instance");
	        assertion.setAttribute("xmlns:xs","http://www.w3.org/2001/XMLSchema");
	        rootElement.appendChild(assertion);
	        assertion.appendChild(issuer);
	        
	        Element subject = doc.createElement("saml:Subject");
	        Element nameId  =doc.createElement("saml:NameID");
	        nameId.setAttribute("Format","urn:oasis:names:tc:SAML:1.1:nameid-format:emailAddress");
	        nameId.appendChild(doc.createTextNode(memberShipId+"@mytdp.com"));
	        subject.appendChild(nameId);
	        
	        Element subjectConfirmation = doc.createElement("saml:SubjectConfirmation");
	        subjectConfirmation.setAttribute("Method","urn:oasis:names:tc:SAML:2.0:cm:bearer");
	        
	        Element subjectConfirmationData = doc.createElement("saml:SubjectConfirmationData");
	        subjectConfirmationData.setAttribute("InResponseTo","_47e32a7f3431474d8c8ce81e6c751a5d1518774");
	        subjectConfirmationData.setAttribute("Recipient","https://accounts.zoho.com/samlresponse/mytdp.com");
	        subjectConfirmationData.setAttribute("NotOnOrAfter",after);
	        
	        subjectConfirmation.appendChild(subjectConfirmationData);
	        subject.appendChild(subjectConfirmation);
	        assertion.appendChild(subject);
	        
	       
	        Element conditions = doc.createElement("saml:Conditions");
	        conditions.setAttribute("NotOnOrAfter",after);
	        conditions.setAttribute("NotBefore",before);
	        assertion.appendChild(conditions);
	        
	        Element audienceRestriction = doc.createElement("saml:AudienceRestriction");
	        Element audience = doc.createElement("saml:Audience");
	        audience.appendChild(doc.createTextNode("https://accounts.zoho.com/samlresponse/mytdp.com"));
	        audienceRestriction.appendChild(audience);
	        conditions.appendChild(audienceRestriction);
	        
	        Element authnStatement = doc.createElement("saml:AuthnStatement");
	        authnStatement.setAttribute("SessionIndex","_"+RandomNumberGeneraion.getUUIDString());
	        authnStatement.setAttribute("SessionNotOnOrAfter",after);
	        authnStatement.setAttribute("AuthnInstant",strUTCDate);
	         
	        assertion.appendChild(authnStatement);
	        
	        
	        Element authnContext = doc.createElement("saml:AuthnContext");
	        Element authnContextClassRef = doc.createElement("AuthnContextClassRef"); 
	        authnContextClassRef.appendChild(doc.createTextNode("urn:oasis:names:tc:SAML:2.0:ac:classes:PasswordProtectedTransport"));
	        authnContext.appendChild(authnContextClassRef);
	        authnStatement.appendChild(authnContext);
	        
	        
	        Element attributeStatement = doc.createElement("AttributeStatement");
	        Element attribute = doc.createElement("saml:Attribute");
	        attribute.setAttribute("NameFormat","urn:oasis:names:tc:SAML:2.0:attrname-format:basic");
	        attribute.setAttribute("Name","PersonImmutableID");
	        
	        Element attributeValue = doc.createElement("saml:AttributeValue");
	        attributeValue.setAttribute("xmlns:xsi","http://www.w3.org/2001/XMLSchema-instance");
	        attributeValue.setAttribute("xsi:type","xs:string");
	        attribute.appendChild(attributeValue);
	        attributeStatement.appendChild(attribute);
	       
	        
	        Element attribute1 = doc.createElement("saml:Attribute");
	        attribute1.setAttribute("NameFormat","urn:oasis:names:tc:SAML:2.0:attrname-format:basic");
	        attribute1.setAttribute("Name","User.email");
	        
	        Element attributeValue1 = doc.createElement("saml:AttributeValue");
	        attributeValue1.setAttribute("xmlns:xsi","http://www.w3.org/2001/XMLSchema-instance");
	        attributeValue1.setAttribute("xsi:type","xs:string");
	        attributeValue1.appendChild(doc.createTextNode(memberShipId+"@mytdp.com"));
	        attribute1.appendChild(attributeValue1);
	        attributeStatement.appendChild(attribute1);
	        
	        
	        Element attribute2 = doc.createElement("saml:Attribute");
	        attribute2.setAttribute("NameFormat","urn:oasis:names:tc:SAML:2.0:attrname-format:basic");
	        attribute2.setAttribute("Name","User.FirstName");
	        
	        Element attributeValue2 = doc.createElement("saml:AttributeValue");
	        attributeValue2.setAttribute("xmlns:xsi","http://www.w3.org/2001/XMLSchema-instance");
	        attributeValue2.setAttribute("xsi:type","xs:string");
	        attributeValue2.appendChild(doc.createTextNode(firstName));
	        attribute2.appendChild(attributeValue2);
	        attributeStatement.appendChild(attribute2);
	        
	        
	        Element attribute3 = doc.createElement("saml:Attribute");
	        attribute3.setAttribute("NameFormat","urn:oasis:names:tc:SAML:2.0:attrname-format:basic");
	        attribute3.setAttribute("Name","memberOf");
	        
	        Element attributeValue3 = doc.createElement("saml:AttributeValue");
	        attributeValue3.setAttribute("xmlns:xsi","http://www.w3.org/2001/XMLSchema-instance");
	        attributeValue3.setAttribute("xsi:type","xs:string");
	        attribute3.appendChild(attributeValue3);
	        attributeStatement.appendChild(attribute3);

	        Element attribute4 = doc.createElement("saml:Attribute");
	        attribute4.setAttribute("NameFormat","urn:oasis:names:tc:SAML:2.0:attrname-format:basic");
	        attribute4.setAttribute("Name","User.LastName");
	        
	        Element attributeValue4 = doc.createElement("saml:AttributeValue");
	        attributeValue4.setAttribute("xmlns:xsi","http://www.w3.org/2001/XMLSchema-instance");
	        attributeValue4.setAttribute("xsi:type","xs:string");
	        attributeValue4.appendChild(doc.createTextNode(lastName));
	        attribute4.appendChild(attributeValue4);
	        attributeStatement.appendChild(attribute4); 
	        assertion.appendChild(attributeStatement);

	        // write the content into xml file
	        TransformerFactory transformerFactory = TransformerFactory.newInstance();
	        Transformer transformer = transformerFactory.newTransformer();
	        DOMSource source = new DOMSource(doc);
	        
	       // StreamResult result = new StreamResult(new File(IConstants.STATIC_CONTENT_FOLDER_URL+"input/saml.xml"));
	        
	        StreamResult result = new StreamResult(new File("E://ZOHO/input/saml.xml"));
	        
	        transformer.transform(source, result);
	        
	        // Output to console for testing
	        StreamResult consoleResult = new StreamResult(System.out);
	        transformer.transform(source, consoleResult);
	        
	      //file = new File(IConstants.STATIC_CONTENT_FOLDER_URL+"input/saml.xml");
	        file = new File("E://ZOHO/input/saml.xml");
	      
		}catch (Exception e) {
			LOG.error("Exception Occured in getSamlXmlFile() in ZohoAlertService class.",e);
		}
		return file;
	}
	
	public String sendSamlResponseToZoho(String membershipId)
	{ 	
		String status = null;
		SamlSignatureUtil samlSignatureUtil = new SamlSignatureUtil();
		ImageAndStringConverter converter = new ImageAndStringConverter();
		
		try {
			List<Object[]> cadreDetailsObj = null;
			if(membershipId !=null){
				List<String> membershipIds = new ArrayList<String>(0);
				membershipIds.add(membershipId);
				//membershipId,firstName,lastName
				cadreDetailsObj =  tdpCadreDAO.getMemberInfoByTdpCadreIds(membershipIds);
			}
			
			if(cadreDetailsObj !=null && cadreDetailsObj.size()>0){
				
				Object[] obj = cadreDetailsObj.get(0);
				
				String firstName=obj[1] !=null ? obj[1].toString():"";
				String lastName =obj[2] !=null ? obj[2].toString():"";
				
				if(obj !=null && obj.length>0){
					
					File fXmlFile = getSamlXmlFile(obj[0].toString(),"Success",firstName,lastName);
					
					DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
					DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
					Document doc = dBuilder.parse(fXmlFile);
					
					Document doc2 = samlSignatureUtil.signFul(doc,IConstants.ZOHO_SAML_RESPONSE_UNIQUE_ID);
					
					DOMSource source = new DOMSource(doc2);
				    //FileWriter writer = new FileWriter(new File(IConstants.STATIC_CONTENT_FOLDER_URL+"/output/saml.xml"));
					FileWriter writer = new FileWriter(new File("E://ZOHO/output/saml.xml"));
				    StreamResult result = new StreamResult(writer);

				    TransformerFactory transformerFactory = TransformerFactory.newInstance();
				    Transformer transformer = transformerFactory.newTransformer();
				    transformer.transform(source, result);
				    
				    //String samlResponse = converter.convertImageFileToBase64String(new File(IConstants.STATIC_CONTENT_FOLDER_URL+"/output/saml.xml"));
					
				    String samlResponse = converter.convertImageFileToBase64String(new File("E://ZOHO/output/saml.xml"));
				    
				    
				    
				    System.out.println(samlResponse);
				    
				  /*  if(samlResponse !=null)
				    	sendSamlResponse(samlResponse);*/
				    
				    status ="success";
				}
			}
			
		} catch (Exception e) {
			LOG.error("Exception Occured in sendSamlResponseToZoho(Long) in ZohoAlertService class.",e);
			status ="failure";
		}
		return status;
		
	}
	public String sendSamlResponse(String samlResponse){
		try {
			
			JSONObject json = new JSONObject();
			
			WebResource webResource = commonMethodsUtilService.getWebResourceObject("https://accounts.zoho.com/samlresponse/mytdp.com");

			WebResource.Builder builder = webResource.getRequestBuilder();
		
			json.put("SAMLResponse", samlResponse);
			json.put("RelayState", IConstants.ZOHO_SAML_DESK_RELAY_STATE_ID);
			
			ClientResponse response = builder.post(ClientResponse.class,json);
			
			if (response.getStatus() != 200) {
				throw new RuntimeException("Failed : HTTP error code : " + response.getStatus());
			} else {
				return "success";
			}
			
		} catch (Exception e) {
			LOG.error("Exception Occured in sendSamlResponse(String) in ZohoAlertService class.",e);
		}
		return null;
	}
}
