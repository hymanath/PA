package com.itgrids.partyanalyst.service.impl;

import java.security.Key;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.crypto.spec.SecretKeySpec;

import org.apache.log4j.Logger;
import org.codehaus.jettison.json.JSONObject;

import com.itgrids.partyanalyst.dao.IOtpDetailsDAO;
import com.itgrids.partyanalyst.dao.ITdpCadreDAO;
import com.itgrids.partyanalyst.dto.ResultStatus;
import com.itgrids.partyanalyst.model.OtpDetails;
import com.itgrids.partyanalyst.service.ISmsService;
import com.itgrids.partyanalyst.service.IZohoAlertService;
import com.itgrids.partyanalyst.utils.CommonMethodsUtilService;
import com.itgrids.partyanalyst.utils.DateUtilService;
import com.itgrids.partyanalyst.utils.IConstants;
import com.itgrids.partyanalyst.utils.RandomNumberGeneraion;

import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

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
	
	
	

}
