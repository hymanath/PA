package com.itgrids.partyanalyst.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import com.itgrids.partyanalyst.dao.IBoothDAO;
import com.itgrids.partyanalyst.dao.ICasteStateDAO;
import com.itgrids.partyanalyst.dao.IConstituencyDAO;
import com.itgrids.partyanalyst.dao.IHamletDAO;
import com.itgrids.partyanalyst.dao.IPanchayatDAO;
import com.itgrids.partyanalyst.dao.ISurveyDetailsInfoDAO;
import com.itgrids.partyanalyst.dao.ISurveySurveyorTypeDAO;
import com.itgrids.partyanalyst.dao.ISurveyUserBoothAssignDAO;
import com.itgrids.partyanalyst.dao.ISurveyUserDAO;
import com.itgrids.partyanalyst.dao.ISurveyUserRelationDAO;
import com.itgrids.partyanalyst.dao.ISurveyUserTabAssignDAO;
import com.itgrids.partyanalyst.dao.ISurveyUserTrackingDAO;
import com.itgrids.partyanalyst.dao.ISurveyUserTypeDAO;
import com.itgrids.partyanalyst.dao.IVoterDAO;
import com.itgrids.partyanalyst.dto.GenericVO;
import com.itgrids.partyanalyst.dto.ResultStatus;
import com.itgrids.partyanalyst.dto.SurveyResponceVO;
import com.itgrids.partyanalyst.model.SurveyDetailsInfo;
import com.itgrids.partyanalyst.model.SurveySurveyorType;
import com.itgrids.partyanalyst.model.SurveyUser;
import com.itgrids.partyanalyst.model.SurveyUserBoothAssign;
import com.itgrids.partyanalyst.model.SurveyUserRelation;
import com.itgrids.partyanalyst.model.SurveyUserTabAssign;
import com.itgrids.partyanalyst.model.SurveyUserTracking;
import com.itgrids.partyanalyst.model.SurveyUserType;
import com.itgrids.partyanalyst.model.Voter;
import com.itgrids.partyanalyst.service.ISurveyDataDetailsService;
import com.itgrids.partyanalyst.utils.DateUtilService;

public class SurveyDataDetailsService implements ISurveyDataDetailsService
{

	private static final Logger LOG = Logger.getLogger(SurveyDataDetailsService.class);
	
	@Autowired
	private ISurveyUserTypeDAO surveyUserTypeDAO;
	
	@Autowired
	private ISurveyUserDAO surveyUserDAO;
	
	@Autowired
	private ISurveyUserTabAssignDAO surveyUserTabAssignDAO;
	
	@Autowired
	private ISurveyUserBoothAssignDAO surveyUserBoothAssignDAO;
	
	@Autowired
	private ISurveyUserRelationDAO surveyUserRelationDAO;
	
	@Autowired
	private ISurveySurveyorTypeDAO surveySurveyorTypeDAO;
	
	@Autowired
	private ISurveyDetailsInfoDAO surveyDetailsInfoDAO;
	
	@Autowired
	private ISurveyUserTrackingDAO surveyUserTrackingDAO;
	
	@Autowired
	private IConstituencyDAO constituencyDAO ;
	
	@Autowired
	private IPanchayatDAO  panchayatDAO;
	
	@Autowired
	private IBoothDAO boothDAO;
	
	@Autowired
	private IVoterDAO voterDAO;
	
	@Autowired
	private IHamletDAO hamletDAO;
	
	@Autowired
	private ICasteStateDAO casteStateDAO;
 	
	@Autowired
	private DateUtilService dateUtilService;

	
	/**
	 * This Service is used for saving the user type details
	 * @param userTypeDescription
	 * @return resultStatus
	 */
	public ResultStatus saveSurveyUserType(String userTypeDescription)
	{
		LOG.info("Entered into saveSurveyUserType service in SurveyDataDetailsService");
		ResultStatus resultStatus = new ResultStatus();
		try
		{
			Long userTypeId = surveyUserTypeDAO.checkForUsertype(userTypeDescription.trim());
			if(userTypeId == null)
			{
				SurveyUserType surveyUserType = new SurveyUserType();
				surveyUserType.setDescription(userTypeDescription);
				surveyUserType.setInsertedTime(dateUtilService.getCurrentDateAndTime());
				surveyUserType.setUpdatedTime(dateUtilService.getCurrentDateAndTime());
				SurveyUserType result = surveyUserTypeDAO.save(surveyUserType);
				if(result != null)
				{
					resultStatus.setResultCode(0);
					resultStatus.setMessage("Success");
				}
				else
				{
					resultStatus.setResultCode(1);
					resultStatus.setMessage("Failure");
				}
			}
			else
			{
				resultStatus.setResultCode(4);
				resultStatus.setMessage("Exists");
			}
			
		} 
		catch (Exception e)
		{
			LOG.error("Exception raised in saveSurveyUserType service in SurveyDataDetailsService", e);
			resultStatus.setResultCode(3);
			resultStatus.setMessage("Exception");
		}
		return resultStatus;
	}
	
	/**
	 * This Service is used for saving all survey user details.
	 * @param firstName
	 * @param lastName
	 * @param userName
	 * @param password
	 * @param address
	 * @param mobileNo
	 * @param activeStatus
	 * @param userTypeId
	 * @return resultStatus
	 */
	public ResultStatus saveSurveyUser(String firstName,String lastName,String userName,String password,String address,String mobileNo,Long userTypeId)
	{
		LOG.info("Entered into saveSurveyUser service in SurveyDataDetailsService");
		ResultStatus resultStatus = new ResultStatus();
		try
		{
			Long userId = getUserDetailsForCheck(userName.trim(),password.trim());
			if(userId == null)
			{
				SurveyUser surveyUser = new SurveyUser();
				surveyUser.setFirstName(firstName);
				surveyUser.setLastName(lastName);
				surveyUser.setUserName(userName);
				surveyUser.setPassword(password);
				surveyUser.setAddress(address);
				surveyUser.setMobileNo(mobileNo);
				surveyUser.setActiveStatus("Y");
				surveyUser.setInsertedTime(dateUtilService.getCurrentDateAndTime());
				surveyUser.setUpdatedTime(dateUtilService.getCurrentDateAndTime());
				SurveyUserType surveyUserType = surveyUserTypeDAO.get(userTypeId);
				if(surveyUserType != null)
				{
					surveyUser.setSurveyUserType(surveyUserType);
				}
				SurveyUser result = surveyUserDAO.save(surveyUser);
				if(result != null)
				{
					resultStatus.setResultCode(0);
					resultStatus.setMessage("Success");
				}
				else
				{
					resultStatus.setResultCode(1);
					resultStatus.setMessage("Failure");
				}
			}
			else
			{
				resultStatus.setResultCode(4);
				resultStatus.setMessage("Exists");
			}
			
			
		}
		catch (Exception e)
		{
			LOG.error("Exception raised in saveSurveyUser service in SurveyDataDetailsService", e);
			resultStatus.setResultCode(3);
			resultStatus.setMessage("Exception");
		}
		return resultStatus;
	}
	
	/**
	 * This Service is used for saving the tab details for assigned survey user.
	 * @param surveyUserId
	 * @param tabNo
	 * @param remarks
	 * @param date
	 * @return resultStatus
	 */
	public ResultStatus saveSurveyUserTabAssign(Long surveyUserId,String tabNo,String remarks,Date date)
	{
		LOG.info("Entered into saveSurveyUserTabAssign service in SurveyDataDetailsService");
		ResultStatus resultStatus = new ResultStatus();
		try
		{
			SurveyUserTabAssign surveyUserTabAssign = new SurveyUserTabAssign();
			surveyUserTabAssign.setTabNo(tabNo);
			surveyUserTabAssign.setRemarks(remarks);
			surveyUserTabAssign.setDate(date);
			surveyUserTabAssign.setSurveyUser(surveyUserDAO.get(surveyUserId));
			//surveyUserTabAssign.setActiveStatus("Y");
			SurveyUserTabAssign result = surveyUserTabAssignDAO.save(surveyUserTabAssign);
			if(result != null)
			{
				resultStatus.setResultCode(0);
				resultStatus.setMessage("Success");
			}
			else
			{
				resultStatus.setResultCode(1);
				resultStatus.setMessage("Failure");
			}
		}
		catch (Exception e)
		{
			LOG.error("Exception raised in saveSurveyUserTabAssign service in SurveyDataDetailsService", e);
			resultStatus.setResultCode(3);
			resultStatus.setMessage("Exception");
		}
		return resultStatus;
	}
	
	/**
	 * This Service is used for assign booths to survey user
	 * @param surveyUserId
	 * @param constituencyId
	 * @param panchayatId
	 * @param boothIds
	 * @return resultStatus
	 */
	public ResultStatus saveSurvetUserBoothAssign(Long surveyUserId,Long constituencyId,Long panchayatId,List<Long> boothIds)
	{
		LOG.info("Entered into saveSurvetUserBoothAssign service in SurveyDataDetailsService");
		ResultStatus resultStatus = new ResultStatus();
		try
		{
			
			if(boothIds != null && boothIds.size() > 0)
			{
				for (Long boothId : boothIds)
				{
					SurveyUserBoothAssign surveyUserBoothAssign = new SurveyUserBoothAssign();
					surveyUserBoothAssign.setSurveyUser(surveyUserDAO.get(surveyUserId));
					surveyUserBoothAssign.setConstituency(constituencyDAO.get(constituencyId));
					surveyUserBoothAssign.setPanchayat(panchayatDAO.get(panchayatId));
					surveyUserBoothAssign.setBooth(boothDAO.get(boothId));
					SurveyUserBoothAssign result = surveyUserBoothAssignDAO.save(surveyUserBoothAssign);
					if(result != null)
					{
						resultStatus.setResultCode(0);
						resultStatus.setMessage("Success");
					}
					else
					{
						resultStatus.setResultCode(1);
						resultStatus.setMessage("Failure");
					}
				}
			}
			
		}
		catch (Exception e)
		{
			LOG.error("Exception raised in saveSurvetUserBoothAssign service in SurveyDataDetailsService", e);
			resultStatus.setResultCode(3);
			resultStatus.setMessage("Exception");
		}
		return resultStatus;
	}
	
	/**
	 * This Service is used for saving the user relations.
	 * @param userTypeId
	 * @param surveyUserId
	 * @param leaderId
	 * @param constituencyId
	 * @return resultStatus
	 */
	public ResultStatus saveServeyUserRelationDetails(Long userTypeId,Long surveyUserId,Long leaderId,Long constituencyId)
	{
		LOG.info("Entered into saveServeyUserRelationDetails service in SurveyDataDetailsService");
		ResultStatus resultStatus = new ResultStatus();
		try 
		{
			SurveyUserRelation surveyUserRelation = new SurveyUserRelation();
			surveyUserRelation.setSurveyUserType(surveyUserTypeDAO.get(userTypeId));
			surveyUserRelation.setSurveyUser(surveyUserDAO.get(surveyUserId));
			surveyUserRelation.setSurveyLeader(surveyUserDAO.get(leaderId));
			surveyUserRelation.setConstituency(constituencyDAO.get(constituencyId));
			surveyUserRelation.setActiveStatus("N");
			SurveyUserRelation result = surveyUserRelationDAO.save(surveyUserRelation);
			if(result != null)
			{
				resultStatus.setResultCode(0);
				resultStatus.setMessage("Success");
			}
			else
			{
				resultStatus.setResultCode(1);
				resultStatus.setMessage("Failure");
			}
		}
		catch (Exception e)
		{
			LOG.error("Exception raised in saveServeyUserRelationDetails service in SurveyDataDetailsService", e);
			resultStatus.setResultCode(3);
			resultStatus.setMessage("Exception");
		}
		return resultStatus;
	}
	
	/**
	 * This Service is used for saving user tacking details.
	 * @param surveyUserId
	 * @param longitude
	 * @param latitude
	 * @param date
	 * @return resultStatus
	 */
	public ResultStatus saveSurveyUserTrackingDetails(Long surveyUserId,String longitude , String latitude,Date date)
	{
		LOG.info("Entered into saveSurveyUserTrackingDetails service in SurveyDataDetailsService");
		ResultStatus resultStatus = new ResultStatus();
		try
		{
			SurveyUserTracking surveyUserTracking = new SurveyUserTracking();
			surveyUserTracking.setSurveyUser(surveyUserDAO.get(surveyUserId));
			surveyUserTracking.setLatitude(latitude);
			surveyUserTracking.setLongitude(longitude);
			surveyUserTracking.setDate(date);
			SurveyUserTracking result = surveyUserTrackingDAO.save(surveyUserTracking);
			if(result != null)
			{
				resultStatus.setResultCode(0);
				resultStatus.setMessage("Success");
			}
			else
			{
				resultStatus.setResultCode(1);
				resultStatus.setMessage("Failure");
			}
		} 
		catch (Exception e)
		{
			LOG.error("Exception raised in saveSurveyUserTrackingDetails service in SurveyDataDetailsService", e);
			resultStatus.setResultCode(3);
			resultStatus.setMessage("Exception");
		}
		return resultStatus;
	}
	
	/**
	 * This Service is used for Deactive User .
	 * @param userId
	 * @param remarks
	 * @return resultStatus
	 */
	public ResultStatus deactivateUser(Long userId,String remarks)
	{
		LOG.info("Entered into deactivateUser service in SurveyDataDetailsService");
		ResultStatus resultStatus = new ResultStatus();
		try
		{
			SurveyUser surveyUser = surveyUserDAO.get(userId);
			if(surveyUser == null)
			{
				resultStatus.setResultCode(3);
				resultStatus.setMessage("No Exist");
				return resultStatus;
			}
			surveyUser.setActiveStatus("N");
			surveyUser.setRemarks(remarks);
			SurveyUser result = surveyUserDAO.save(surveyUser);
			if(result != null)
			{
				resultStatus.setResultCode(0);
				resultStatus.setMessage("Success");
			}
			else
			{
				resultStatus.setResultCode(1);
				resultStatus.setMessage("Failure");
			}
		}
		catch (Exception e) 
		{
			LOG.error("Exception raised in deactivateUser service in SurveyDataDetailsService", e);
			resultStatus.setResultCode(3);
			resultStatus.setMessage("Exception");
		}
		return resultStatus;
	}
	
	/**
	 * This Service is Used For Saving Survey Data
	 * @param List<surveyResponceVO>
	 * @return resultStatus
	 */
	public ResultStatus saveSurveyDataDetailsInfo(List<SurveyResponceVO> surveyResponceList)
	{
		LOG.info("Entered into saveSurveyDataDetailsInfo service in SurveyDataDetailsService");
		ResultStatus resultStatus = new ResultStatus();
		try
		{
			if(surveyResponceList != null && surveyResponceList.size() > 0)
			{
				for (SurveyResponceVO surveyResponceVO : surveyResponceList)
				{
					SurveyDetailsInfo surveyDetailsInfo = new SurveyDetailsInfo();
					SurveyUser surveyUser = surveyUserDAO.get(surveyResponceVO.getSurveyUserId());
					if(surveyUser != null)
					{
						surveyDetailsInfo.setSurveyUser(surveyUser);
						SurveySurveyorType surveySurveyorType = surveySurveyorTypeDAO.get(surveyResponceVO.getSurveyorId());
						surveyDetailsInfo.setSurveySurveyorType(surveySurveyorType);
						Voter voter = voterDAO.get(surveyResponceVO.getVoterId());
						if(voter != null)
						{
							surveyDetailsInfo.setVoter(voter);
						}
						else
						{
							if(surveyResponceVO.getVoterCardNo() != null)
							{
								Long voterId = voterDAO.getVoterIdByIdCardNo(surveyResponceVO.getVoterCardNo());
								surveyDetailsInfo.setVoter( voterDAO.get(voterId));
							}
							
						}
						
						surveyDetailsInfo.setLatitude(surveyResponceVO.getLatitude());
						surveyDetailsInfo.setLongitude(surveyResponceVO.getLongitude());
						surveyDetailsInfo.setIsCadre(surveyResponceVO.getIsCadre());
						surveyDetailsInfo.setIsInfluencingPeople(surveyResponceVO.getIsInfluencingPeople());
						surveyDetailsInfo.setMobileNumber(surveyResponceVO.getMobileNo());
						
						surveyDetailsInfo.setLocalArea(surveyResponceVO.getLocalArea());
						surveyDetailsInfo.setHamletName(surveyResponceVO.getHamletName());
						surveyDetailsInfo.setCasteName(surveyResponceVO.getCasteName());
						
						surveyDetailsInfo.setDate(surveyResponceVO.getDate());
						
						if(surveyResponceVO.getHamletId() != null && surveyResponceVO.getHamletId() > 0)
						{
							surveyDetailsInfo.setHamlet( hamletDAO.get(surveyResponceVO.getHamletId()));
						}
						if(surveyResponceVO.getCasteId() != null && surveyResponceVO.getCasteId() > 0)
						{
							surveyDetailsInfo.setCaste(casteStateDAO.get(surveyResponceVO.getCasteId() ));
						}
						
						
						
						SurveyDetailsInfo result = surveyDetailsInfoDAO.save(surveyDetailsInfo);
						if(result != null)
						{
							resultStatus.setResultCode(0);
							resultStatus.setMessage("Success");
						}
						else
						{
							resultStatus.setResultCode(1);
							resultStatus.setMessage("Failure");
						}
					}
				}
			}
			
			
			
		}
		catch (Exception e)
		{
			LOG.error("Exception raised in saveSurveyDataDetailsInfo service in SurveyDataDetailsService", e);
			resultStatus.setResultCode(3);
			resultStatus.setMessage("Exception");		}
		return resultStatus;
	}
	
	/**
	 * This Service is used for getting survey user id.
	 * @param userName
	 * @param password
	 * @return userId
	 */
	public Long getUserDetailsForCheck(String userName,String password)
	{
		LOG.info("Entered into getUserDetailsForCheck service in SurveyDataDetailsService");
		Long userId = null;
		try
		{
			userId = surveyUserDAO.getUserDetails(userName,password);
		} 
		catch (Exception e)
		{
			LOG.error("Exception raised in getUserDetailsForCheck service in SurveyDataDetailsService", e);
		}	
		return userId;
	}
	
	/**
	 * This Service is used for getting all userType
	 * @return returnList
	 */
	public List<GenericVO> getUserTypes()
	{
		 List<GenericVO> returnList = null;
		 try 
		 {
			List<Object[]> result = surveyUserTypeDAO.getAllUserTypes();
			if(result != null && result.size() > 0)
			{
				returnList = new ArrayList<GenericVO>();
				fillGenericVO(result,returnList);
			}
		 } 
		 catch (Exception e)
		 {
			
		 }
		 return returnList;
	}
	
	/**
	 * This Service is used for filling Generic VO
	 * @param result
	 * @param returnList
	 */
	public void fillGenericVO(List<Object[]> result,List<GenericVO> returnList)
	{
		for (Object[] parms : result)
		{
			GenericVO genericVO = new GenericVO();
			genericVO.setId(parms[0] != null ? (Long)parms[0] : 0l);
			genericVO.setName(parms[1] != null ? parms[1].toString() : "");
			returnList.add(genericVO);
		}
	}
	
	/**
	 * This Service is used for getting all user by user type
	 * @param userTypeId
	 * @return returnList
	 */
	public List<GenericVO> getSurveyUsersByUserType(Long userTypeId)
	{
		List<GenericVO> returnList = null;
		try
		{
			List<Object[]> result = surveyUserDAO.getSurveyUsersByUserType(userTypeId);
			if(result != null && result.size() > 0)
			{
				returnList = new ArrayList<GenericVO>();
				fillGenericVO(result,returnList);
			}
		}
		catch (Exception e)
		{
			
		}
		 return returnList;
	}
}
