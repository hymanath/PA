package com.itgrids.partyanalyst.service.impl;

import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import java.io.File;
import java.io.StringWriter;
import java.security.Key;
import java.security.PrivateKey;
import java.security.cert.X509Certificate;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

import javax.crypto.spec.SecretKeySpec;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.apache.commons.codec.binary.Base64;
import org.apache.log4j.Logger;
import org.codehaus.jettison.json.JSONObject;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import com.itgrids.partyanalyst.dao.IOtpDetailsDAO;
import com.itgrids.partyanalyst.dao.ITdpCadreDAO;
import com.itgrids.partyanalyst.dao.IZohoTdpCadreContactDAO;
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
	
	private IZohoTdpCadreContactDAO zohoTdpCadreContactDAO;

	
	public void setZohoTdpCadreContactDAO(
			IZohoTdpCadreContactDAO zohoTdpCadreContactDAO) {
		this.zohoTdpCadreContactDAO = zohoTdpCadreContactDAO;
	}

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
						
						status = setOTPConfirmationDetails(jobj.getString("memberShipId"),status);
						
						/*try {
							ResultStatus samlResult = sendSamlResponseToZoho(jobj.getString("memberShipId"));
							
							status = setCadreTypeDetailsOfSamlLogin(jobj.getString("memberShipId"),status);
							
							if(status !=null && status.has("status") && status.getString("status").trim().equalsIgnoreCase("success")){
								status.put("samlResponse", samlResult.getMessage() !=null && !samlResult.getMessage().trim().isEmpty()?
										samlResult.getMessage().trim():"");
							}
							
						} catch (Exception e) {
							LOG.error("Exception raised in sendSamlResponseToZoho in checkOTPDetails method of ZohoAlertService Class ", e);
						}*/
						
						return 	status;
					}
					else{
						status.put("memberShipId", "");
						status.put("status", "failed");
					}
		    	 }
				 else{
					 status.put("memberShipId", "");
					 status.put("status", "failed");
				 }
			}
			else{
				status.put("memberShipId", "");
				status.put("status", "failed");
			}
			
		} catch (Exception e) {
			LOG.error("Exception Occured in checkOTPDetails() in ZohoAlertService class.",e);
		}
		return status;
	}
	
	public JSONObject setOTPConfirmationDetails(String membershipId,JSONObject status){
		try {
			ResultStatus samlResult = sendSamlResponseToZoho(membershipId);
			
			status = setCadreTypeDetailsOfSamlLogin(membershipId,status);
			
			if(status !=null && status.has("status") && status.getString("status").trim().equalsIgnoreCase("success")){
				status.put("samlResponse", samlResult.getMessage() !=null && !samlResult.getMessage().trim().isEmpty()?
						samlResult.getMessage().trim():"");
			}
		} catch (Exception e) {
			LOG.error("Exception Occured in setOTPConfirmationDetails() in ZohoAlertService class.",e);
		}
		return status;
	}
	
	public JSONObject setCadreTypeDetailsOfSamlLogin(String membershipId,JSONObject statusJson){
		try {
			if(membershipId !=null && !membershipId.trim().isEmpty())			
			{
				//tdpCadreId,memberShipNo,alertCadreTypeId,typeName,isalertCreate
				List<Object[]> cadreTypeInfoObj = zohoTdpCadreContactDAO.getAlertCadreTypeInfo(membershipId);
				if(cadreTypeInfoObj !=null && cadreTypeInfoObj.size()>0){
					for (Object[] objects : cadreTypeInfoObj) {
						if(objects[2] !=null){
							
							statusJson.put("memberShipId", membershipId);
							statusJson.put("status", "success");
							
							if((Long)objects[2] == 3l){ // stake holders
								statusJson.put("isAssignee", "true");
								statusJson.put("isAgent", "true");
								return statusJson;
							}else if((Long)objects[2] ==4l){ // assignees
								statusJson.put("isAssignee", "true");
								statusJson.put("isAgent", "false");
							}else{ // ProgramCommittee && InfoCenter
								statusJson.put("isAssignee", "false");
								statusJson.put("isAgent", "true");
							}
							statusJson.put("isAllowedToAddAlert", objects[4] !=null && objects[4].toString().trim().equalsIgnoreCase("Y")?"true":"false");
						}
					}
					
				}else{
					statusJson.put("memberShipId", "");
					statusJson.put("status", "failed");
				}
			}
		} catch (Exception e) {
			LOG.error("Exception Occured in setCadreTypeDetailsOfSamlLogin() in ZohoAlertService class.",e);
		}
		return statusJson;
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


	// Here We are generating input XML file and returning document File
	public File getSamlXmlFile(String memberShipId,String successMsg,String firstName,String lastName) {
		File file = null;
		try{
	        DocumentBuilderFactory dbFactory =
	        DocumentBuilderFactory.newInstance();
	        dbFactory.setNamespaceAware(true);  // written for testing by akhila
	        DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
	        Document doc = dBuilder.newDocument();
	        Date currentDate = dateUtilService.getCurrentDateAndTime();
	        SimpleDateFormat dateFormatter = new SimpleDateFormat(
	                "yyyy-MM-dd'T'HH:mm:ss'Z'");
	        dateFormatter.setTimeZone(TimeZone.getTimeZone("UTC"));
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
	        issuer.appendChild(doc.createTextNode("https://mytdp.com"));
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
	        Element authnContextClassRef = doc.createElement("saml:AuthnContextClassRef"); 
	        authnContextClassRef.appendChild(doc.createTextNode("urn:oasis:names:tc:SAML:2.0:ac:classes:PasswordProtectedTransport"));
	        authnContext.appendChild(authnContextClassRef);
	        authnStatement.appendChild(authnContext);
	        
	        
	       /* Element attributeStatement = doc.createElement("saml:AttributeStatement");
	        Element attribute = doc.createElement("saml:Attribute");
	        attribute.setAttribute("NameFormat","urn:oasis:names:tc:SAML:2.0:attrname-format:basic");
	        attribute.setAttribute("Name","PersonImmutableID");
	        
	        Element attributeValue = doc.createElement("saml:AttributeValue");
	        attributeValue.setAttribute("xmlns:xs","http://www.w3.org/2001/XMLSchema-instance");
	        attributeValue.setAttribute("xsi:type","xs:string");
	        attribute.appendChild(attributeValue);
	        attributeStatement.appendChild(attribute);
	       
	        
	        Element attribute1 = doc.createElement("saml:Attribute");
	        attribute1.setAttribute("NameFormat","urn:oasis:names:tc:SAML:2.0:attrname-format:basic");
	        attribute1.setAttribute("Name","User.email");
	        
	        Element attributeValue1 = doc.createElement("saml:AttributeValue");
	        attributeValue1.setAttribute("xmlns:xs","http://www.w3.org/2001/XMLSchema-instance");
	        attributeValue1.setAttribute("xsi:type","xs:string");
	        attributeValue1.appendChild(doc.createTextNode(memberShipId+"@mytdp.com"));
	        attribute1.appendChild(attributeValue1);
	        attributeStatement.appendChild(attribute1);
	        
	        
	        Element attribute2 = doc.createElement("saml:Attribute");
	        attribute2.setAttribute("NameFormat","urn:oasis:names:tc:SAML:2.0:attrname-format:basic");
	        attribute2.setAttribute("Name","User.FirstName");
	        
	        Element attributeValue2 = doc.createElement("saml:AttributeValue");
	        attributeValue2.setAttribute("xmlns:xs","http://www.w3.org/2001/XMLSchema-instance");
	        attributeValue2.setAttribute("xsi:type","xs:string");
	        attributeValue2.appendChild(doc.createTextNode(firstName));
	        attribute2.appendChild(attributeValue2);
	        attributeStatement.appendChild(attribute2);
	        
	        
	        Element attribute3 = doc.createElement("saml:Attribute");
	        attribute3.setAttribute("NameFormat","urn:oasis:names:tc:SAML:2.0:attrname-format:basic");
	        attribute3.setAttribute("Name","memberOf");
	        
	        Element attributeValue3 = doc.createElement("saml:AttributeValue");
	        attributeValue3.setAttribute("xmlns:xs","http://www.w3.org/2001/XMLSchema-instance");
	        attributeValue3.setAttribute("xsi:type","xs:string");
	        attribute3.appendChild(attributeValue3);
	        attributeStatement.appendChild(attribute3);

	        Element attribute4 = doc.createElement("saml:Attribute");
	        attribute4.setAttribute("NameFormat","urn:oasis:names:tc:SAML:2.0:attrname-format:basic");
	        attribute4.setAttribute("Name","User.LastName");
	        
	        Element attributeValue4 = doc.createElement("saml:AttributeValue");
	        attributeValue4.setAttribute("xmlns:xs","http://www.w3.org/2001/XMLSchema-instance");
	        attributeValue4.setAttribute("xsi:type","xs:string");
	        attributeValue4.appendChild(doc.createTextNode(lastName));
	        attribute4.appendChild(attributeValue4);
	        attributeStatement.appendChild(attribute4); 
	        assertion.appendChild(attributeStatement); */

	        // write the content into xml file
	        TransformerFactory transformerFactory = TransformerFactory.newInstance();
	        Transformer transformer = transformerFactory.newTransformer();
	        DOMSource source = new DOMSource(doc);
	        
	        StreamResult result = new StreamResult(new File(IConstants.STATIC_CONTENT_FOLDER_URL+"security/SAML/ZOHO/input/saml.xml"));
	        
	        //StreamResult result = new StreamResult(new File("E://ZOHO/input/saml.xml"));
	        
	        transformer.transform(source, result);
	        
	        // Output to console for testing
	      /*  StreamResult consoleResult = new StreamResult(System.out);
	        transformer.transform(source, consoleResult);*/
	        
	        file = new File(IConstants.STATIC_CONTENT_FOLDER_URL+"security/SAML/ZOHO/input/saml.xml");
	        //file = new File("E://ZOHO/input/saml.xml");
	      
		}catch (Exception e) {
			LOG.error("Exception Occured in getSamlXmlFile() in ZohoAlertService class.",e);
		}
		return file;
	}
	
	// This is the Service for Saml Calling by passing membershipId
	public ResultStatus sendSamlResponseToZoho(String membershipId)
	{ 	
		
		ResultStatus resultStatus = new ResultStatus();
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
				
					String  b = getSamlXmlString(obj[0].toString(),"Success",firstName,lastName);
					
					String pub= "-----BEGIN CERTIFICATE-----\n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\n-----END CERTIFICATE-----\n";
					String privateKeyStr = "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";  //No I18N
					PrivateKey privateKey = samlSignatureUtil.getPrivateKey(privateKeyStr);
					X509Certificate cert = SamlSignatureUtil.getCertificate(pub);
					Document doc2 = samlSignatureUtil.signNew(b,IConstants.ZOHO_SAML_RESPONSE_UNIQUE_ID,privateKey,cert,"saml:Assertion"); 
					
					Element element = doc2.getDocumentElement();
					TransformerFactory tf1 = TransformerFactory.newInstance();
					Transformer trans1 = tf1.newTransformer();

					StringWriter writer1 = new StringWriter();

						trans1.setOutputProperty(OutputKeys.OMIT_XML_DECLARATION, "yes"); // No I18N
					trans1.transform(new DOMSource(element), new StreamResult(writer1));
					String samlResponse=  writer1.toString();
					samlResponse =  Base64.encodeBase64String(samlResponse.getBytes());
					
					if(samlResponse !=null){
				    	resultStatus.setMessage(samlResponse);
				    	resultStatus.setExceptionMsg("success");
				    }else{
				    	resultStatus.setExceptionMsg("failure");
				    }
				    	
				}
			}
			
		} catch (Exception e) {
			resultStatus.setExceptionMsg("failure");
			LOG.error("Exception Occured in sendSamlResponseToZoho(Long) in ZohoAlertService class.",e);
		}
		return resultStatus;
		
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
	
	
	public String getSamlXmlString(String memberShipId,String successMsg,String firstName,String lastName) {
		File file = null;
		try{
	        DocumentBuilderFactory dbFactory =
	        DocumentBuilderFactory.newInstance();
	        dbFactory.setNamespaceAware(true);  // written for testing by akhila
	        DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
	        Document doc = dBuilder.newDocument();
	        
	        // UTC Time Format
	        
	        Date currentDate = dateUtilService.getCurrentDateAndTime();
	        SimpleDateFormat dateFormatter = new SimpleDateFormat(
	                "yyyy-MM-dd'T'HH:mm:ss'Z'");
	        dateFormatter.setTimeZone(TimeZone.getTimeZone("UTC"));
	        Date aDate = new Date(System.currentTimeMillis()+5*60*1000);
	        Date bDate = new Date(System.currentTimeMillis()-5*60*1000);
	        String strUTCDate = dateFormatter.format(currentDate);
	        String after = dateFormatter.format(aDate);
	        String before = dateFormatter.format(bDate);
	        
	      
	        // root element
	        Element rootElement = doc.createElement("samlp:Response");
	        rootElement.setAttribute("InResponseTo","_47e32a7f3431474d8c8ce81e6c751a5d1518774");
	        rootElement.setAttribute("Destination","https://accounts.zoho.com/samlresponse/mytdp.com");
	        rootElement.setAttribute("IssueInstant",strUTCDate);
	        rootElement.setAttribute("Version","2.0");
	        rootElement.setAttribute("ID","R992fdbeb2a4873b04c105a8a5f74c1a267ed0f22");
	        rootElement.setAttribute("xmlns:samlp","urn:oasis:names:tc:SAML:2.0:protocol");
	        doc.appendChild(rootElement);
	        
	        Element issuer = doc.createElement("saml:Issuer");
	        issuer.appendChild(doc.createTextNode("https://mytdp.com"));
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
	        Element authnContextClassRef = doc.createElement("saml:AuthnContextClassRef"); 
	        authnContextClassRef.appendChild(doc.createTextNode("urn:oasis:names:tc:SAML:2.0:ac:classes:PasswordProtectedTransport"));
	        authnContext.appendChild(authnContextClassRef);
	        authnStatement.appendChild(authnContext);
	        
	        
	       /* Element attributeStatement = doc.createElement("saml:AttributeStatement");
	        Element attribute = doc.createElement("saml:Attribute");
	        attribute.setAttribute("NameFormat","urn:oasis:names:tc:SAML:2.0:attrname-format:basic");
	        attribute.setAttribute("Name","PersonImmutableID");
	        
	        Element attributeValue = doc.createElement("saml:AttributeValue");
	        attributeValue.setAttribute("xmlns:xs","http://www.w3.org/2001/XMLSchema-instance");
	        attributeValue.setAttribute("xsi:type","xs:string");
	        attribute.appendChild(attributeValue);
	        attributeStatement.appendChild(attribute);
	       
	        
	        Element attribute1 = doc.createElement("saml:Attribute");
	        attribute1.setAttribute("NameFormat","urn:oasis:names:tc:SAML:2.0:attrname-format:basic");
	        attribute1.setAttribute("Name","User.email");
	        
	        Element attributeValue1 = doc.createElement("saml:AttributeValue");
	        attributeValue1.setAttribute("xmlns:xs","http://www.w3.org/2001/XMLSchema-instance");
	        attributeValue1.setAttribute("xsi:type","xs:string");
	        attributeValue1.appendChild(doc.createTextNode(memberShipId+"@mytdp.com"));
	        attribute1.appendChild(attributeValue1);
	        attributeStatement.appendChild(attribute1);
	        
	        
	        Element attribute2 = doc.createElement("saml:Attribute");
	        attribute2.setAttribute("NameFormat","urn:oasis:names:tc:SAML:2.0:attrname-format:basic");
	        attribute2.setAttribute("Name","User.FirstName");
	        
	        Element attributeValue2 = doc.createElement("saml:AttributeValue");
	        attributeValue2.setAttribute("xmlns:xs","http://www.w3.org/2001/XMLSchema-instance");
	        attributeValue2.setAttribute("xsi:type","xs:string");
	        attributeValue2.appendChild(doc.createTextNode(firstName));
	        attribute2.appendChild(attributeValue2);
	        attributeStatement.appendChild(attribute2);
	        
	        
	        Element attribute3 = doc.createElement("saml:Attribute");
	        attribute3.setAttribute("NameFormat","urn:oasis:names:tc:SAML:2.0:attrname-format:basic");
	        attribute3.setAttribute("Name","memberOf");
	        
	        Element attributeValue3 = doc.createElement("saml:AttributeValue");
	        attributeValue3.setAttribute("xmlns:xs","http://www.w3.org/2001/XMLSchema-instance");
	        attributeValue3.setAttribute("xsi:type","xs:string");
	        attribute3.appendChild(attributeValue3);
	        attributeStatement.appendChild(attribute3);

	        Element attribute4 = doc.createElement("saml:Attribute");
	        attribute4.setAttribute("NameFormat","urn:oasis:names:tc:SAML:2.0:attrname-format:basic");
	        attribute4.setAttribute("Name","User.LastName");
	        
	        Element attributeValue4 = doc.createElement("saml:AttributeValue");
	        attributeValue4.setAttribute("xmlns:xs","http://www.w3.org/2001/XMLSchema-instance");
	        attributeValue4.setAttribute("xsi:type","xs:string");
	        attributeValue4.appendChild(doc.createTextNode(lastName));
	        attribute4.appendChild(attributeValue4);
	        attributeStatement.appendChild(attribute4); 
	        assertion.appendChild(attributeStatement); */

	        // write the content into xml file
	       /* TransformerFactory transformerFactory = TransformerFactory.newInstance();
	        Transformer transformer = transformerFactory.newTransformer();
	        DOMSource source = new DOMSource(doc);
	        
	        StreamResult result = new StreamResult(new File(IConstants.STATIC_CONTENT_FOLDER_URL+"security/SAML/ZOHO/input/saml.xml"));
	        
	        //StreamResult result = new StreamResult(new File("E://ZOHO/input/saml.xml"));
	        
	        transformer.transform(source, result);
	        
	        // Output to console for testing
	        StreamResult consoleResult = new StreamResult(System.out);
	        transformer.transform(source, consoleResult);
	        
	        file = new File(IConstants.STATIC_CONTENT_FOLDER_URL+"security/SAML/ZOHO/input/saml.xml");
	        //file = new File("E://ZOHO/input/saml.xml");
*/	      
	        TransformerFactory tf1 = TransformerFactory.newInstance();
			Transformer trans1 = tf1.newTransformer();
			
			StringWriter writer1 = new StringWriter();
			trans1.setOutputProperty(OutputKeys.OMIT_XML_DECLARATION, "yes"); // No I18N
			
			trans1.transform(new DOMSource(doc.getDocumentElement()), new StreamResult(writer1));
			return writer1.toString();
		}catch (Exception e) {
			LOG.error("Exception Occured in getSamlXmlFile() in ZohoAlertService class.",e);
		}
		return null;
	}
}
