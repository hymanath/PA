package com.itgrids.partyanalyst.service.impl;

import java.util.ArrayList;
import java.util.List;


import net.sf.cglib.core.EmitUtils;

import org.apache.log4j.Logger;

import com.itgrids.partyanalyst.dao.IMobileAppUserAccessKeyDAO;
import com.itgrids.partyanalyst.dao.IMobileAppUserDAO;
import com.itgrids.partyanalyst.dao.IMobileAppUserProfileDAO;
import com.itgrids.partyanalyst.dao.IVoiceRecordingDetailsDAO;
import com.itgrids.partyanalyst.dao.IWebServiceBaseUrlDAO;
import com.itgrids.partyanalyst.dao.hibernate.VoiceRecordingDetailsDAO;
import com.itgrids.partyanalyst.dto.BasicVO;
import com.itgrids.partyanalyst.dto.EmailDetailsVO;
import com.itgrids.partyanalyst.dto.ResultCodeMapper;
import com.itgrids.partyanalyst.dto.ResultStatus;
import com.itgrids.partyanalyst.dto.SelectOptionVO;
import com.itgrids.partyanalyst.dto.VoiceSmsResponseDetailsVO;
import com.itgrids.partyanalyst.dto.WSResultVO;
import com.itgrids.partyanalyst.model.MobileAppUser;
import com.itgrids.partyanalyst.model.MobileAppUserAccessKey;
import com.itgrids.partyanalyst.service.ILoginService;
import com.itgrids.partyanalyst.service.IMailService;
import com.itgrids.partyanalyst.service.IMobileService;
import com.itgrids.partyanalyst.service.ISmsService;
import com.itgrids.partyanalyst.service.IVoiceSmsService;
import com.itgrids.partyanalyst.service.IWebServiceHandlerService;

import com.itgrids.partyanalyst.utils.IConstants;

public class WebServiceHandlerService implements IWebServiceHandlerService {
	
	private static final Logger log = Logger.getLogger(WebServiceHandlerService.class);
	
	private ILoginService loginService;
	
	private IMobileService mobileService;

	private ISmsService smsCountrySmsService;
	private IMailService mailService;
	
	private IMobileAppUserProfileDAO mobileAppUserProfileDAO;
	
	private IMobileAppUserDAO mobileAppUserDAO;
	private IMobileAppUserAccessKeyDAO mobileAppUserAccessKeyDAO ;
	private IWebServiceBaseUrlDAO webServiceBaseUrlDAO;
	private IVoiceSmsService voiceSmsService;
	private IVoiceRecordingDetailsDAO voiceRecordingDetailsDAO;
	
	public IVoiceRecordingDetailsDAO getVoiceRecordingDetailsDAO() {
		return voiceRecordingDetailsDAO;
	}

	public IWebServiceBaseUrlDAO getWebServiceBaseUrlDAO() {
		return webServiceBaseUrlDAO;
	}

	public void setWebServiceBaseUrlDAO(IWebServiceBaseUrlDAO webServiceBaseUrlDAO) {
		this.webServiceBaseUrlDAO = webServiceBaseUrlDAO;
	}

	public void setVoiceRecordingDetailsDAO(
			IVoiceRecordingDetailsDAO voiceRecordingDetailsDAO) {
		this.voiceRecordingDetailsDAO = voiceRecordingDetailsDAO;
	}

	public IVoiceSmsService getVoiceSmsService() {
		return voiceSmsService;
	}

	public void setVoiceSmsService(IVoiceSmsService voiceSmsService) {
		this.voiceSmsService = voiceSmsService;
	}

	public IMobileAppUserAccessKeyDAO getMobileAppUserAccessKeyDAO() {
		return mobileAppUserAccessKeyDAO;
	}

	public void setMobileAppUserAccessKeyDAO(
			IMobileAppUserAccessKeyDAO mobileAppUserAccessKeyDAO) {
		this.mobileAppUserAccessKeyDAO = mobileAppUserAccessKeyDAO;
	}

	public IMobileAppUserDAO getMobileAppUserDAO() {
		return mobileAppUserDAO;
	}

	public void setMobileAppUserDAO(IMobileAppUserDAO mobileAppUserDAO) {
		this.mobileAppUserDAO = mobileAppUserDAO;
	}

	public IMobileAppUserProfileDAO getMobileAppUserProfileDAO() {
		return mobileAppUserProfileDAO;
	}

	public void setMobileAppUserProfileDAO(
			IMobileAppUserProfileDAO mobileAppUserProfileDAO) {
		this.mobileAppUserProfileDAO = mobileAppUserProfileDAO;
	}

	public IMailService getMailService() {
		return mailService;
	}

	public void setMailService(IMailService mailService) {
		this.mailService = mailService;
	}

	public ISmsService getSmsCountrySmsService() {
		return smsCountrySmsService;
	}

	public void setSmsCountrySmsService(ISmsService smsCountrySmsService) {
		this.smsCountrySmsService = smsCountrySmsService;
	}

	public IMobileService getMobileService() {
		return mobileService;
	}

	public void setMobileService(IMobileService mobileService) {
		this.mobileService = mobileService;
	}

	public ILoginService getLoginService() {
		return loginService;
	}

	public void setLoginService(ILoginService loginService) {
		this.loginService = loginService;
	}

	public String checkForUserAuthentication(String userName , String passWord)
	{
		log.debug("Entered into the checkForUserAuthentication  method in WebServiceHandlerService");
		try
		{
			
		}catch(Exception e)
		{
			log.error("Exception raised in checkForUserAuthentication  method in WebServiceHandlerService");
			e.printStackTrace();
		}
		return "error";
	}
	public ResultStatus checkUserAuthenticationAndUpdateAuthorisedTime(String userId,String macAdressId)
	{
		try{
		return mobileService.checkAuthenticateUserAndUpdateLastAuthorisedTime(userId, macAdressId);
		}
		catch(Exception e)
		{
			log.error("Exception raised in checkUserAuthenticationAndUpdateAuthorisedTime  method in WebServiceHandlerService");
			return null;
		}
		
	}
	
	public ResultStatus sendSmsToUser(String uniquecode)
	{
		ResultStatus resultStatus = new ResultStatus();
		try{
			List<Object> mobileAppUserId = mobileAppUserDAO.checkUniqueCode(uniquecode);
			
			if(mobileAppUserId == null || mobileAppUserId .size() == 0)
				resultStatus.setResultCode(ResultCodeMapper.DATA_NOT_FOUND);
			
			if(mobileAppUserId != null)
			{
				List<Object[]> list =mobileAppUserProfileDAO.getMobileNoByUniquecode(uniquecode);
				
				if(list == null || list.size() == 0)
					resultStatus.setResultCode(ResultCodeMapper.FAILURE);
				
				if(list != null && list.size() > 0)
				{
					for(Object[] params : list)
					{
						if(params[0] == null)
							resultStatus.setResultCode(ResultCodeMapper.FAILURE);
						else
						{
							String[] mobile = {params[0].toString()};
							String[] admingroupMobileNos = {IConstants.ADMINGROUPMOBILE};
							String name = params[1].toString()+" " +params[2].toString();
							String smsText = "Dear "+name+" your Request For forgot password is received. We will send Access key to this Mobile shortly.";
							String smsText1 = "Hi Admin Group,"+name+" requested for forgot access key, verify him and send a Access key as soon as possible.";
							
							resultStatus = smsCountrySmsService.sendSmsFromAdmin(smsText,true,mobile);
							smsCountrySmsService.sendSmsFromAdmin(smsText1,true,admingroupMobileNos);
							
							if(name != null && name !="")
								mailService.sendEmailToAdminGroupForAccessKey(name);
						}
					}
				}
			}
		
		}
		catch (Exception e) {
			e.printStackTrace();
			return resultStatus;
		}
		return resultStatus;
	
	}
	

	public ResultStatus updatePassword(String uniqueCode,String pwd,String accessKey)
	{
		ResultStatus resultStatus = new ResultStatus();
		try{
			
			List<Object[]> list = mobileAppUserAccessKeyDAO.checkUniqueCodeByAccesskey(uniqueCode,accessKey);
			if(list == null || list.size() == 0)
				resultStatus.setResultCode(ResultCodeMapper.DATA_NOT_FOUND);
			if(list != null && list.size() > 0)
			{
				for(Object[] params : list)
				{
				MobileAppUser mobileAppUser = mobileAppUserDAO.get((Long)params[0]);
				mobileAppUser.setPassword(pwd);
				mobileAppUser = mobileAppUserDAO.save(mobileAppUser);
				MobileAppUserAccessKey mobileAppUserAccessKey = mobileAppUserAccessKeyDAO.get((Long)params[1]);
				mobileAppUserAccessKey.setIsUsed("true");
				mobileAppUserAccessKeyDAO.save(mobileAppUserAccessKey);
				resultStatus.setResultCode(ResultCodeMapper.SUCCESS);
				}
			}
		
		}
		catch (Exception e) {
			e.printStackTrace();
			resultStatus.setResultCode(ResultCodeMapper.FAILURE);
		}
		return resultStatus;
		
	}
	public List<WSResultVO> getUserVoiceRecordedFiles(String uniqueCode)
	{
		 List<WSResultVO> result = new ArrayList<WSResultVO>();
		try{
			List<Object> userId = mobileAppUserDAO.checkUniqueCode(uniqueCode);
				if(userId == null || userId .size() == 0)
					return result;
				else
				{
					List<Object[]> list = voiceRecordingDetailsDAO.getAllTheRecordingDetailsOfUser((Long)userId.get(0));
						
					if(list == null || list.size() == 0)
							return null;
					else
					{
						 for(Object[] params : list)
						 {
							 WSResultVO wsResultVO = new WSResultVO();
							 wsResultVO.setId((Long)params[2]);
							 wsResultVO.setName(params[0].toString());
							 wsResultVO.setDescription(params[1].toString());
							 wsResultVO.setLocation(IConstants.LIVE_VOICE_RECORDINGS_URL+params[2].toString()+"/"+params[0]);
							 result.add(wsResultVO);		
						 }
					}
				}
				
		  }
		catch(Exception e)
		{
			e.printStackTrace();
			return result;
		}
		return result;
	}
	public String sendVoiceSMS(String uniqueCode,String mobileNos,String FilePath)
	{
		ResultStatus resultStatus = new ResultStatus();
		String result = "";
		String smsText = "Hi";
		StringBuffer audioFilePath = new StringBuffer();
		
		try{
			List<Object> userId = mobileAppUserDAO.checkUniqueCode(uniqueCode);
			if(userId == null || userId .size() == 0)
				result ="data not found";
			else
			{
			audioFilePath.append(IConstants.LIVE_VOICE_RECORDINGS_URL+"/"+(Long)userId.get(0)+"/"+FilePath);
				
			//audioFilePath.append("http://122.169.253.134:8080/TDP/voice_recording/test6.wav");
			result = voiceSmsService.sendVoiceSMS(audioFilePath.toString(),(Long)userId.get(0),mobileNos,null,smsText,null);
			}
			
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	
	public ResultStatus sendSMS(String uniqueCode,String mobileNos,String message)
	{
		ResultStatus resultStatus = new ResultStatus();
		try{
			
			List<Object> mobileAppUserId = mobileAppUserDAO.checkUniqueCode(uniqueCode);
			
			if(mobileAppUserId == null || mobileAppUserId .size() == 0)
				resultStatus.setResultCode(ResultCodeMapper.DATA_NOT_FOUND);
			else
			{
				if(!(message.toString().equals("\"\"") && mobileNos.toString().equals("\"\"")))
				{
					String[] mobilenoarr = mobileNos.split(",");
					resultStatus = smsCountrySmsService.sendSmsFromAdmin(message, true, mobilenoarr);
				}
			
			}
		}
		catch (Exception e) {
			log.error("Exception Occured in sendSMS()",e);
			e.printStackTrace();
		}
		return resultStatus;
	}
	
	public String getBaseUrlForApp(String appName)
	{
		try{
			String url = webServiceBaseUrlDAO.getBaseURLForAnApp(appName);
			
			if(url == null || url.isEmpty())
				return "FAIL:URL not found"; 
			else
				return url.trim();
		}catch(Exception e)
		{
			log.error("Exception Occured, Exception is - "+e);
			return "FAIL:URL not found"; 
		}
	}

}

