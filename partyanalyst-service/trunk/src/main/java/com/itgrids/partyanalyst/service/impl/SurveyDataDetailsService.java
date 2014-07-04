package com.itgrids.partyanalyst.service.impl;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import com.itgrids.partyanalyst.dao.IBoothDAO;
import com.itgrids.partyanalyst.dao.IBoothPublicationVoterDAO;
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
import com.itgrids.partyanalyst.dto.SelectOptionVO;
import com.itgrids.partyanalyst.dto.SurveyReportVO;
import com.itgrids.partyanalyst.dto.SurveyResponceVO;
import com.itgrids.partyanalyst.dto.UserBoothDetailsVO;
import com.itgrids.partyanalyst.model.CasteState;
import com.itgrids.partyanalyst.model.Hamlet;
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
import com.itgrids.partyanalyst.utils.IConstants;

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
	
	@Autowired
	private IBoothPublicationVoterDAO boothPublicationVoterDAO;

	
	/**
	 * This Service is used for saving the user type details
	 * @param userTypeDescription
	 * @return resultStatus
	 */
	public ResultStatus saveSurveyUserType(String userTypeDescription,String userType)
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
				surveyUserType.setUserType(userType);
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
			DateUtilService date1 = new DateUtilService();
			SurveyUserTabAssign surveyUserTabAssign = new SurveyUserTabAssign();
			surveyUserTabAssign.setTabNo(tabNo);
			surveyUserTabAssign.setRemarks(remarks);
			surveyUserTabAssign.setDate(date);
			surveyUserTabAssign.setSurveyUser(surveyUserDAO.get(surveyUserId));
			surveyUserTabAssign.setInsertedTime(date1.getCurrentDateAndTime());
			surveyUserTabAssign.setUpdatedTime(date1.getCurrentDateAndTime());
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
	public ResultStatus saveSurveyUserBoothAssign(Long surveyUserId,Long constituencyId,List<Long> boothIds)
	{
		LOG.info("Entered into saveSurvetUserBoothAssign service in SurveyDataDetailsService");
		ResultStatus resultStatus = new ResultStatus();
		try
		{
			
			List<Object[]> existingBoothsDtls = surveyUserBoothAssignDAO.getAllTheAssignedBoothsByConstituencyIdAndUserId(constituencyId,surveyUserId);
			
			List<Long> existingBoothIds = new ArrayList<Long>();
			
			for(Object[] existingBoothDtls:existingBoothsDtls)
			{
				existingBoothIds.add((Long)existingBoothDtls[1]);
				
				if(!boothIds.contains((Long)existingBoothDtls[1]))//if the existing boothId is not exist 
					                                              //in present booth list,then we are setting delete indicator Y to those records
				{
					SurveyUserBoothAssign surveyUserBoothAssign = surveyUserBoothAssignDAO.get((Long)existingBoothDtls[0]);
					
					surveyUserBoothAssign.setIsDelete("Y");
					surveyUserBoothAssign.setUpdatedTime(dateUtilService.getCurrentDateAndTime());
					surveyUserBoothAssignDAO.save(surveyUserBoothAssign);
				}
			}
			
			for(Long boothId:boothIds)
			{
				if(!existingBoothIds.contains(boothId))//if the present booth is not exist 
								                    //in existing booth list,then we are inserting that record into the database
				{
					SurveyUserBoothAssign surveyUserBoothAssign = new SurveyUserBoothAssign();
					
					surveyUserBoothAssign.setBoothId(boothId);
					surveyUserBoothAssign.setSurveyUserId(surveyUserId);
					surveyUserBoothAssign.setConstituencyId(constituencyId);
                    surveyUserBoothAssign.setInsertedTime(dateUtilService.getCurrentDateAndTime());
                    surveyUserBoothAssign.setUpdatedTime(dateUtilService.getCurrentDateAndTime());
                    surveyUserBoothAssign.setIsDelete("N");
					surveyUserBoothAssignDAO.save(surveyUserBoothAssign);
					
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
	public ResultStatus saveServeyUserRelationDetails(Long userTypeId,List<Long> surveyUserIds,Long leaderId,Long constituencyId)
	{
		LOG.info("Entered into saveServeyUserRelationDetails service in SurveyDataDetailsService");
		ResultStatus resultStatus = new ResultStatus();
		try 
		{
			if(surveyUserIds != null && surveyUserIds.size() > 0)
			{
				for (Long surveyUserId : surveyUserIds)
				{
					SurveyUserRelation surveyUserRelation = new SurveyUserRelation();
					surveyUserRelation.setSurveyUserType(surveyUserTypeDAO.get(userTypeId));
					surveyUserRelation.setSurveyUser(surveyUserDAO.get(surveyUserId));
					surveyUserRelation.setSurveyLeader(surveyUserDAO.get(leaderId));
					surveyUserRelation.setConstituency(constituencyDAO.get(constituencyId));
					surveyUserRelation.setActiveStatus("Y");
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
			resultStatus.setMessage("Exception");
		}
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
			 LOG.error("Exception raised in getUserTypes service in SurveyDataDetailsService", e);
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
			LOG.error("Exception raised in getSurveyUsersByUserType service in SurveyDataDetailsService", e);
		}
		 return returnList;
	}
	
	public List<GenericVO> getSurveyUsersForAssignToLeader(Long userTypeId)
	{
		List<GenericVO> returnList = null;
		try
		{
			List<Object[]> result = surveyUserDAO.getSurveyUsersByUserTypeForLeaderAssign(userTypeId);
			if(result != null && result.size() > 0)
			{
				returnList = new ArrayList<GenericVO>();
				fillGenericVO(result,returnList);
			}
		} 
		catch (Exception e)
		{
			LOG.error("Exception raised in getSurveyUsersForAssignToLeader service in SurveyDataDetailsService", e);
		}
		 return returnList;
	}
	
	/**
	 * This Service is used for getting all leaders by constituency wise
	 * @return returnList
	 */
	public List<GenericVO> getConstituencyWiseLeaders()
	{
		List<GenericVO> returnList = null;
		try
		{
			List<Object[]> result = surveyUserRelationDAO.getLeadersByConstituency();
			if(result != null && result.size() > 0)
			{
				returnList = new ArrayList<GenericVO>();
				for (Object[] parms : result)
				{
					GenericVO genericVO = new GenericVO();
					genericVO.setId(parms[0] != null ? (Long)parms[0] : 0l);
					genericVO.setName(parms[1] != null ? parms[1].toString() : "");
					genericVO.setRank(parms[2] != null ? (Long)parms[2] : 0l);
					genericVO.setDesc(parms[3] != null ? parms[3].toString() : "");
					returnList.add(genericVO);
				}
			}
		}
		catch (Exception e)
		{
			LOG.error("Exception raised in getConstituencyWiseLeaders service in SurveyDataDetailsService", e);
		}
		 return returnList;
	}
	
	/**
	 * This Service is used for getting all users by leade and constituency
	 * @param leaderId
	 * @param constituencyId
	 * @return returnList
	 */
	public List<GenericVO> getSurveyUsersByLeades(Long leaderId,Long constituencyId)
	{
		List<GenericVO> returnList = null;
		try
		{
			List<Object[]> result = surveyUserRelationDAO.getUsersByConstituencyAndLeader(leaderId, constituencyId);
			if(result != null && result.size() > 0)
			{
				returnList = new ArrayList<GenericVO>();
				Map<Long,List<GenericVO>> resultMap = new java.util.HashMap<Long, List<GenericVO>>();
				for (Object[] parms : result)
				{
					GenericVO VO = new GenericVO();
					List<GenericVO> list = resultMap.get((Long)parms[0]);
					if(list == null)
					{
						list = new ArrayList<GenericVO>();
						resultMap.put((Long)parms[0], list);
					}
					VO.setId(parms[0] != null ? (Long)parms[0] : 0l);
					VO.setName(parms[1] != null ? parms[1].toString() : "");
					VO.setRank(parms[2] != null ? (Long.valueOf(parms[2].toString())) : 0l);
					list.add(VO);
				}
				List<Long> surveyUserIds = new ArrayList<Long>(resultMap.keySet());
				if(surveyUserIds != null && surveyUserIds.size() > 0)
				{
					for (Long surveyUserId : surveyUserIds)
					{
						GenericVO genericVO = new GenericVO();
						genericVO.setId(surveyUserId);
						List<GenericVO> genericVOList = resultMap.get(surveyUserId);
						if(genericVOList != null && genericVOList.size() > 0)
						{
							genericVO.setName(genericVOList.get(0).getName());
							genericVO.setGenericVOList(genericVOList);
						}
						returnList.add(genericVO);
						
					}
				}
			}
		}
		catch (Exception e)
		{
			LOG.error("Exception raised in getSurveyUsersByLeades service in SurveyDataDetailsService", e);
		}
		 return returnList;
	}
	
	/**
	 * This Service is used for getting longititude and latitude values for user tracking
	 * @param surveyUserId
	 * @param date
	 * @return
	 */
	public List<GenericVO> getLatLongForUserTrackung(Long surveyUserId,Date date)
	{
		List<GenericVO> returnList = null;
		try 
		{
			List<Object[]> result = surveyUserTrackingDAO.getLatLongForUserTracking(surveyUserId, date);
			if(result != null && result.size() > 0)
			{
				returnList = new ArrayList<GenericVO>();
				for (Object[] parms : result)
				{
					GenericVO VO = new GenericVO();
					VO.setName(parms[0] != null ? parms[0].toString() : "");//longtitude
					VO.setDesc(parms[1] != null ? parms[1].toString() : "");//latitude
					returnList.add(VO);
				}
			}
		}
		catch (Exception e) 
		{
			LOG.error("Exception raised in getLatLongForUserTrackung service in SurveyDataDetailsService", e);
		}
		return returnList;
	}
	
	/**
	 * This Service is used for getting all detils taken by data collector
	 * @param surveyUserId
	 * @param date
	 * @return returnList
	 */
	public List<SurveyResponceVO> getLatLongForSurveyDetails(Long surveyUserId,Date date)
	{
		List<SurveyResponceVO> returnList = null;
		try
		{
			List<SurveyDetailsInfo> result = surveyDetailsInfoDAO.getLatLongForSurveyDetails(surveyUserId, date);
			if(result != null && result.size() > 0)
			{
				returnList = new ArrayList<SurveyResponceVO>();
				fullSurveyResponceVO(result,returnList);
			}
		} 
		catch (Exception e)
		{
			LOG.error("Exception raised in getLatLongForUserTrackung service in SurveyDataDetailsService", e);
		}
		return returnList;
	}
	
	/**
	 * This Service is used for filling all details for survey data collection
	 * @param result
	 * @param returnList
	 */
	
	public void fullSurveyResponceVO(List<SurveyDetailsInfo> result,List<SurveyResponceVO> returnList )
	{
		for (SurveyDetailsInfo surveyDetailsInfo : result)
		{
			SurveyResponceVO VO = new SurveyResponceVO();
			if(surveyDetailsInfo.getVoter() != null)
			{
				VO.setVoterId(surveyDetailsInfo.getVoter().getVoterId());
				VO.setVoterCardNo(surveyDetailsInfo.getVoter().getVoterIDCardNo());
				VO.setVoterName(surveyDetailsInfo.getVoter().getName());
				VO.setGender(surveyDetailsInfo.getVoter().getGender());
				VO.setAge(surveyDetailsInfo.getVoter().getAge());
			}
			VO.setMobileNo(surveyDetailsInfo.getMobileNumber());
			VO.setIsCadre(surveyDetailsInfo.getIsCadre());
			VO.setIsInfluencingPeople(surveyDetailsInfo.getIsInfluencingPeople());
			if(surveyDetailsInfo.getCaste() != null)
			{
				String casteName = casteStateDAO.get(surveyDetailsInfo.getCaste().getCasteStateId()).getCaste().getCasteName();
				VO.setCasteName(casteName);
			}
			else
			{
				VO.setCasteName(surveyDetailsInfo.getCasteName());
			}
			if(surveyDetailsInfo.getHamlet() != null)
			{
				String hamletName = hamletDAO.get(surveyDetailsInfo.getHamlet().getHamletId()).getHamletName();
				VO.setHamletName(hamletName);
			}
			else
			{
				VO.setHamletName(surveyDetailsInfo.getHamletName());
			}
			VO.setLatitude(surveyDetailsInfo.getLatitude());
			VO.setLocalArea(surveyDetailsInfo.getLocalArea());
			VO.setLongitude(surveyDetailsInfo.getLongitude());
			returnList.add(VO);
		}
	}
	
	/**
	 * This Service is used for assigng voter details for Verifier fordata collection
	 * @param surveyUserId
	 * @param boothId
	 * @return returnList
	 */
	public List<SurveyResponceVO> getDetailsForVerifier(Long surveyUserId,Long boothId)
	{
		 List<SurveyResponceVO> returnList = null;
		 try
		 {
			
			List<SurveyDetailsInfo> result = surveyDetailsInfoDAO.getSurveyDetilsForAssibnedBooths(boothId);
			if(result != null && result.size() > 0)
			{
				List<SurveyResponceVO> SurveyResponceVOList = new ArrayList<SurveyResponceVO>();
				fullSurveyResponceVO(result,SurveyResponceVOList);
				Map<Long,SurveyResponceVO> collectedDataMap = new HashMap<Long, SurveyResponceVO>();
				Map<Long,SurveyResponceVO> totalVoterMap = new HashMap<Long, SurveyResponceVO>();
				
				Integer totalCollectedData = SurveyResponceVOList.size();
				
				List<Object[]> voterHouseDetails = boothPublicationVoterDAO.getTotalVotersByBoothsForVerfier(boothId, 10l);
				if(voterHouseDetails != null && voterHouseDetails.size() > 0)
				{
					for (Object[] parms : voterHouseDetails)
					{
						SurveyResponceVO VO = new SurveyResponceVO();
						VO.setVoterId((Long)parms[0]);
						VO.setVoterCardNo(parms[4] != null ? parms[4].toString() :"");
						VO.setAge(parms[3] != null ? (Long)parms[3] :null);
						VO.setGender(parms[2] != null ? parms[2].toString() :"");
						VO.setHouseNo(parms[1] != null ? parms[1].toString() :"");
						totalVoterMap.put((Long)parms[0], VO);
					}
				}
				
				Integer totalBoothAvaliableData = totalVoterMap.size();
				if(totalBoothAvaliableData > totalCollectedData)
				{
					Integer eareseDataCount = totalCollectedData/10;
					if(SurveyResponceVOList != null && SurveyResponceVOList.size() > 0)
					{
						Random randomNum = new Random();
						
						for(Integer i = 0 ; i < eareseDataCount ; i++)
						{
							SurveyResponceVOList.remove(randomNum.nextInt(SurveyResponceVOList.size())) ;
						}
					}
					for (SurveyResponceVO surveyResponceVO : SurveyResponceVOList)
					{
						collectedDataMap.put(surveyResponceVO.getVoterId(), surveyResponceVO);
					}
					
				}
				
				if(totalVoterMap != null && totalVoterMap.size() > 0)
				{
					returnList = new ArrayList<SurveyResponceVO>();
					List<Long> voterIds = new ArrayList<Long>(totalVoterMap.keySet());
					for (Long voterId : voterIds)
					{
						SurveyResponceVO VO = collectedDataMap.get(voterId);
						if(VO == null)
						{
							returnList.add(totalVoterMap.get(voterId));
						}
						else
						{
							returnList.add(VO);
						}
					}
				}
				
				
			}
		 }
		 catch (Exception e) 
		 {
			 LOG.error("Exception raised in getDetailsForVerifier service in SurveyDataDetailsService", e);
		 }
		 return returnList;
	}
	
	public List<UserBoothDetailsVO> getAssignedBoothsDetailsByConstituencyIdAndUserId(Long constituencyId,Long userId)
	{
		
		LOG.info("Entered into saveSurvetUserBoothAssign service in SurveyDataDetailsService");
		List<UserBoothDetailsVO> resultList = new ArrayList<UserBoothDetailsVO>();
		
		try
		{
			List<Object[]> boothDtls = boothDAO.getAllTheBoothsDetailsByConstituencyId(constituencyId,10L);
			
			List<Long> existingBoothIds = new ArrayList<Long>();
			
			List<Object[]> existingBoothsDtls = surveyUserBoothAssignDAO
					.getAllTheAssignedBoothsByConstituencyIdAndUserId(
							constituencyId, userId);
			
			for(Object[] existingBoothDtls:existingBoothsDtls)
				existingBoothIds.add((Long)existingBoothDtls[1]);
			
			
			for(Object[] obj:boothDtls)
			{
				UserBoothDetailsVO boothDetails = new UserBoothDetailsVO();
				
				boothDetails.setBoothId((Long)obj[0]);
				boothDetails.setPartNo(obj[1].toString());
				/*if(obj[2] != null)
				{
					Panchayat panchayat = (Panchayat)obj[3];
					boothDetails.setPanchayatName(panchayat.getPanchayatName());
				}
				
				if(obj[3] != null)
				{
					LocalElectionBody localBody = (LocalElectionBody)obj[3];
					boothDetails.setPanchayatName(localBody.getName());
				}*/
				
				if(existingBoothIds.contains((Long)obj[0]))
					boothDetails.setUserHas(true);
				
				resultList.add(boothDetails);
			}
			
		}catch(Exception e)
		{
			LOG.error("Exception raised in saveSurvetUserBoothAssign service in SurveyDataDetailsService");
			e.printStackTrace();
		}
		
	  return resultList;
		
	}
	
	
	
	public List<SurveyReportVO> getDayWisereportDetailsByConstituencyId(Long constituencyId,String startDate,String endDate,Long userTypeId)
	{
		LOG.info("Entered into getDayWisereportDetailsByConstituencyId service in SurveyDataDetailsService");

		List<SurveyReportVO> reportList = new ArrayList<SurveyReportVO>();
		try
		{
		   // DateFormat df = new SimpleDateFormat("MM/dd/yyyy"); 
			 DateFormat df = new SimpleDateFormat("yyyy-mm-dd"); 
		    Date startDt =  df.parse(startDate);
		    Date endDt =  df.parse(endDate);
		    
			List<Object[]> dayWiseReportDtls = surveyDetailsInfoDAO.getDayWisereportDetailsByConstituencyId(constituencyId,startDt,endDt,userTypeId);
			
			List<Long> userIds = new ArrayList<Long>();
			SortedSet<String> surveyDates = new TreeSet<String>();
			
			for(Object[] obj:dayWiseReportDtls)
			{
				if(!userIds.contains((Long)obj[1]))
				userIds.add((Long)obj[1]);
				surveyDates.add(obj[3].toString());
			}
			
			for(Long userId:userIds)
			{
				SurveyReportVO userVO = new SurveyReportVO();
				userVO.setUserid(userId);
				
				for(String surveyDate:surveyDates)
				{
					SurveyReportVO dateVO = new SurveyReportVO();
					dateVO.setSurveyDate(surveyDate);
					userVO.getSubList().add(dateVO);
				}
				
				reportList.add(userVO);
			}
			
             for(Object[] obj:dayWiseReportDtls)
             {
            	 SurveyReportVO userVO = getMatchedUserVO(reportList,(Long)obj[1]);
            	 userVO.setUserName(obj[2].toString());
            	 SurveyReportVO dateVO = getMatchedSurveyDateVO(userVO.getSubList(),obj[3].toString());
            	 dateVO.setCount((Long)obj[0]);
            	 
             }
			
		}catch(Exception e)
		{
			e.printStackTrace();
			LOG.error("Exception raised in getDayWisereportDetailsByConstituencyId service in SurveyDataDetailsService", e);
		}
		return reportList;
	}
	
	public SurveyReportVO getMatchedUserVO(List<SurveyReportVO> usersList,Long userId)
	{
		for(SurveyReportVO userVO:usersList)
			if(userVO.getUserid().equals(userId))
				return userVO;
		return null;
	}
	
	public SurveyReportVO getMatchedSurveyDateVO(List<SurveyReportVO> datesList,String surveyDate)
	{
		for(SurveyReportVO userVO:datesList)
			if(userVO.getSurveyDate().equals(surveyDate))
				return userVO;
		return null;
	}
	
	public List<SelectOptionVO> getAllAssemblyConstituenciesByStateId()
	{
		List<SelectOptionVO> resultList = new ArrayList<SelectOptionVO>();
		try {
			List<Object[]> constituenciesList = constituencyDAO.getAllAssemblyConstituenciesByStateId(1L);
			
			for(Object[] obj:constituenciesList)
			{
				resultList.add(new SelectOptionVO((Long)obj[0],obj[1].toString()));
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return resultList;
	}
	
	public List<SurveyReportVO> getBoothWiseUserSamplesDetailsByDates(Long userId,String startDate,String endDate)
	{
		LOG.info("Entered into getBoothWiseUserSamplesDetailsByDates service in SurveyDataDetailsService");

		List<SurveyReportVO> resultList = new ArrayList<SurveyReportVO>();
		
		 try {
			DateFormat df = new SimpleDateFormat("MM/dd/yyyy"); 
			    
			    Date startDt =  df.parse(startDate);
			    Date endDt =  df.parse(endDate);
			
			List<Object[]> boothWiseCountList =  surveyDetailsInfoDAO.getBoothWiseUserSamplesDetailsByDates(userId,startDt,endDt);
			
			List<Long> boothIds = new ArrayList<Long>();
			
			for(Object[] obj:boothWiseCountList)
			{
				if(!boothIds.contains((Long)obj[1]))
						boothIds.add((Long)obj[1]);
				
			}
			
			List<Object[]> totalVotersDtls = boothDAO.getTotalaVotesDetailsByBoothIds(boothIds);
			
			Map<Long,Long> boothWiseCountMap = new HashMap<Long, Long>();
			
			for(Object[] obj:totalVotersDtls)
				boothWiseCountMap.put((Long)obj[1], (Long)obj[0]);
			
			for(Object[] obj:boothWiseCountList)
			{
				SurveyReportVO boothCount = new SurveyReportVO();
				
				boothCount.setBoothId((Long)obj[1]);
				boothCount.setPartNo(obj[2].toString());
                boothCount.setCount((Long)obj[0]);
                boothCount.setTotalVoters(boothWiseCountMap.get((Long)obj[1]));
                
                resultList.add(boothCount);
			};
			 
		} catch (Exception e) {
			e.printStackTrace();
			LOG.error("Exception raised in getBoothWiseUserSamplesDetailsByDates service in SurveyDataDetailsService", e);
		}
		 return resultList;
	}
	
	public List<SurveyReportVO> getReportForVerification(Long boothId)
	{
		LOG.info("Entered into getReportForVerification service in SurveyDataDetailsService");

		List<SurveyReportVO> resultList = new ArrayList<SurveyReportVO>();
		
		try {
			List<SurveyDetailsInfo> votersList = boothPublicationVoterDAO.getVotersDetailsByBoothId(boothId);
			
			 java.util.Set<Long>  voterIds = new java.util.HashSet<Long>();
			 
			 for(SurveyDetailsInfo voterDtls:votersList)
			 {
				 voterIds.add(voterDtls.getVoter().getVoterId());
			 }
			 
			 for(Long voterId:voterIds)
			 {
				 SurveyReportVO voter = new SurveyReportVO();
				 
				 voter.setVoterId(voterId);
				 
				 resultList.add(voter);
			 }
			
			// 0 - voterId 
			// 1 - voter name
			// 2 - idCadre
			// 3 - isInfluencingPeople
			// 4 - caste
			// 5 - localArea
			// 6 - hamlet
			// 7 - userId
			// 8 - user name
			// 9 - userType
			 
			 SurveyReportVO subTypeVO = null;
			 
			
			for(SurveyDetailsInfo surveyDtlsInfo:votersList)
			{
				subTypeVO = new SurveyReportVO();
				
				SurveyReportVO voterVO = getmatchedVoterVO(resultList,(surveyDtlsInfo.getVoter().getVoterId()));
				
				subTypeVO.setCadre(surveyDtlsInfo.getIsCadre());
				
				subTypeVO.setInfluencePeople(surveyDtlsInfo.getIsInfluencingPeople());
				
                subTypeVO.setVoterId(surveyDtlsInfo.getVoter().getVoterId());
                subTypeVO.setVoterName(surveyDtlsInfo.getVoter().getName());
                subTypeVO.setUserid(surveyDtlsInfo.getSurveyUser().getSurveyUserId());
                subTypeVO.setLocalArea(surveyDtlsInfo.getLocalArea());
                
                subTypeVO.setCasteId(surveyDtlsInfo.getCaste().getCasteStateId());
                
                if(surveyDtlsInfo.getHamlet() != null)
                {
	        	 subTypeVO.setHamletName(surveyDtlsInfo.getHamlet().getHamletName());
	             subTypeVO.setHamletId(surveyDtlsInfo.getHamlet().getHamletId());
                }

				
				if(surveyDtlsInfo.getSurveyUser().getSurveyUserType().getSurveyUsertypeId().equals(IConstants.DATA_COLLECTOR_TYPE_ID))// collector
				{
					voterVO.setDataCollector(subTypeVO);
				}
				else if(surveyDtlsInfo.getSurveyUser().getSurveyUserType().getSurveyUsertypeId().equals(IConstants.VERIFIER_TYPE_ID))// verifier
				{
					voterVO.setVerifier(subTypeVO);
				}
				else
				{
					voterVO.setThirdParty(subTypeVO); //third party
				}
					
			}
			
			checkForMatchedDetailsForDataCollectorAndVerifierAndThirdParty(resultList);
			checkForMatchedDetailsForDataCollectorAndVerifier(resultList);
			
		} catch (Exception e) {
			//e.printStackTrace();
			LOG.error("Exception raised in getReportForVerification service in SurveyDataDetailsService", e);
		}
		
		return resultList;
	}
	
	public SurveyReportVO getmatchedVoterVO(List<SurveyReportVO> votersList,Long voterId)
	{
			for(SurveyReportVO userVO:votersList)
			if(userVO.getVoterId().equals(voterId))
				return userVO;
		return null;
	}
	
	private void checkForMatchedDetailsForDataCollectorAndVerifier(List<SurveyReportVO> votersList)
	{

		try {
			for(SurveyReportVO voterVO:votersList)
			{
				
				if(voterVO.getDataCollector() != null && voterVO.getVerifier() != null && voterVO.getThirdParty() != null)
				{
					if (voterVO.getDataCollector().isCadre() == voterVO
							.getVerifier().isCadre())					
					{
						voterVO.setCadreMatched(true);
					}
					else
					{
						voterVO.setCadreMatched(false);
					}
					
					
					
					if (voterVO.getDataCollector().isInfluencePeople() == voterVO
							.getVerifier().isInfluencePeople())					
					{
						voterVO.setInfluencePeopleMatched(true);
					}
					else
					{
						voterVO.setInfluencePeopleMatched(false);
					}
					
					
					if (voterVO.getDataCollector().getLocalArea().equalsIgnoreCase(voterVO.getVerifier().getLocalArea()))				
					{
						voterVO.setLocalAreaMatched(true);
					}
					else
					{
						voterVO.setLocalAreaMatched(false);
					}
					
					
					if (voterVO.getDataCollector().getHamletId().equals(voterVO.getVerifier().getHamletId()))				
					{
						voterVO.setHamletMatched(true);
					}
					else
					{
						voterVO.setHamletMatched(false);
					}
					
					
					if (voterVO.getDataCollector().getCasteId().equals(voterVO.getVerifier().getCasteId()))				
					{
						voterVO.setCasteMatched(true);
					}
					else
					{
						voterVO.setCasteMatched(false);
					}
				}
				
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	private void checkForMatchedDetailsForDataCollectorAndVerifierAndThirdParty(List<SurveyReportVO> votersList)
	{

		try {
			for(SurveyReportVO voterVO:votersList)
			{
				
				if(voterVO.getDataCollector() != null && voterVO.getVerifier() != null && voterVO.getThirdParty() != null)
				{
					if (voterVO.getDataCollector().isCadre() == voterVO
							.getVerifier().isCadre()
							&& voterVO.getVerifier().isCadre() == voterVO
									.getThirdParty().isCadre())					
					{
						voterVO.setCadreMatched(true);
					}
					else
					{
						voterVO.setCadreMatched(false);
					}
					
					
					
					if (voterVO.getDataCollector().isInfluencePeople() == voterVO
							.getVerifier().isInfluencePeople()
							&& voterVO.getVerifier().isInfluencePeople() == voterVO
									.getThirdParty().isInfluencePeople())					
					{
						voterVO.setInfluencePeopleMatched(true);
					}
					else
					{
						voterVO.setInfluencePeopleMatched(false);
					}
					
					
					if (voterVO.getDataCollector().getLocalArea().equalsIgnoreCase(voterVO.getVerifier().getLocalArea()) 
							&& voterVO.getVerifier().getLocalArea() .equalsIgnoreCase(voterVO.getThirdParty().getLocalArea()))				
					{
						voterVO.setLocalAreaMatched(true);
					}
					else
					{
						voterVO.setLocalAreaMatched(false);
					}
					
					
					if (voterVO.getDataCollector().getHamletId().equals(voterVO.getVerifier().getHamletId()) 
							&& voterVO.getVerifier().getHamletId() .equals(voterVO.getThirdParty().getHamletId()))				
					{
						voterVO.setHamletMatched(true);
					}
					else
					{
						voterVO.setHamletMatched(false);
					}
					
					
					if (voterVO.getDataCollector().getCasteId().equals(voterVO.getVerifier().getCasteId()) 
							&& voterVO.getVerifier().getCasteId() .equals(voterVO.getThirdParty().getCasteId()))				
					{
						voterVO.setCasteMatched(true);
					}
					else
					{
						voterVO.setCasteMatched(false);
					}
				}
				
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	
	public ResultStatus saveSurveyUserTrackingDetails(Long surveyUserId,Date date,String longitude,String latitude)
	{
		ResultStatus resultStatus = new ResultStatus();
		try
		{
			SurveyUserTracking surveyUserTracking = new SurveyUserTracking();
			surveyUserTracking.setSurveyUser(surveyUserDAO.get(surveyUserId));
			surveyUserTracking.setDate(date);
			surveyUserTracking.setLongitude(longitude);
			surveyUserTracking.setLatitude(latitude);
			surveyUserTracking.setInsertedTime(dateUtilService.getCurrentDateAndTime());
			surveyUserTracking.setUpdatedTime(dateUtilService.getCurrentDateAndTime());
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
	
	public List<GenericVO> releaseLeadersWithUser(Long leaderId)
	{
		List<GenericVO> returnList = null;
		try
		{
			List<Object[]> result = surveyUserRelationDAO.getUserForAssignedUser(leaderId);
			if(result != null && result.size() > 0)
			{
				returnList = new ArrayList<GenericVO>();
				for (Object[] parms : result) 
				{
					GenericVO genericVO = new GenericVO();
					genericVO.setId(parms[0] != null ? (Long)parms[0] : 0l);
					genericVO.setName(parms[1] != null ? parms[1].toString() : "");
					genericVO.setRank(parms[2] != null ? (Long)parms[2] : 0l);
					genericVO.setDesc(parms[3] != null ? parms[3].toString() : "");
					returnList.add(genericVO);
				}
				
			}
		} 
		catch (Exception e) 
		{
			LOG.error("Exception raised in releaseLeadersWithUser service in SurveyDataDetailsService", e);
		}
		return returnList;
	}
	
	
	/**
	 * This Service is used for Update the user relations.
	 * @param userTypeId
	 * @param surveyUserId
	 * @param leaderId
	 * @param constituencyId
	 * @return resultStatus
	 */
	public ResultStatus updateServeyUserRelationDetails(Long userTypeId,List<Long> surveyUserIds,Long leaderId,Long constituencyId)
	{
		LOG.info("Entered into saveServeyUserRelationDetails service in SurveyDataDetailsService");
		ResultStatus resultStatus = new ResultStatus();
		try 
		{
			if(surveyUserIds != null && surveyUserIds.size() > 0)
			{
				int count = surveyUserRelationDAO.updateUserLeaderRelations(userTypeId,surveyUserIds, leaderId, constituencyId);
				if(count > 0)
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
		catch (Exception e)
		{
			LOG.error("Exception raised in saveServeyUserRelationDetails service in SurveyDataDetailsService", e);
			resultStatus.setResultCode(3);
			resultStatus.setMessage("Exception");
		}
		return resultStatus;
	}
	
	/*public List<SurveyResponceVO> getSurveyUserBoothsAndVoterDetails(Long surveyUserId)
	{
		List<SurveyResponceVO> resultList = new ArrayList<SurveyResponceVO>();
		
		try{
			
			List<Long> boothIds = surveyUserBoothAssignDAO.getBoothIdsBySurveyUser(surveyUserId);
			List<Long> booths = new ArrayList<Long>();
			if(boothIds != null && boothIds.size() > 0)
			{
				
				List<Object[]> list = surveyDetailsInfoDAO.getVoterDetailsForbooths(boothIds);	
				if(list != null && list.size() > 0)
				{
					for(Object[] params : list)
					{
						SurveyResponceVO boothVo = new SurveyResponceVO();
						if(!booths.contains((Long)params[0]))
						boothVo.setBoothId((Long)params[0]);
						booths.add((Long)params[0]);
					}
					
					for(Object[] params : list)
					{
						SurveyResponceVO booth = checkBoothExist(resultList,(Long)params[0]);
						if(booth != null)
						{
							SurveyDetailsInfo surveyDetailsInfo = (SurveyDetailsInfo) params[1];
							if(surveyDetailsInfo.getVoter() != null)
							{
								Voter voter = surveyDetailsInfo.getVoter();
								SurveyResponceVO voterVo = new SurveyResponceVO();
								voterVo.setVoterId(voter.getVoterId());
								voterVo.setVoterCardNo(voter.getVoterIDCardNo());
								voterVo.setVoterName(voter.getName() != null ?voter.getName().toString() : "");
								voterVo.setAge((Long)voter.getAge());
								voterVo.setGender(voter.getGender().toString());
							
								voterVo.setCasteName(surveyDetailsInfo.getCasteName() != null ?surveyDetailsInfo.getCasteName().toString() : "");
								voterVo.setCasteId(surveyDetailsInfo.getCaste() != null ? surveyDetailsInfo.getCaste().getCaste().getCasteId() : 0l);
								voterVo.setHamletId(surveyDetailsInfo.getHamlet() != null ? surveyDetailsInfo.getHamlet().getHamletId() : 0l);
								voterVo.setHamletName(surveyDetailsInfo.getHamlet() != null ?surveyDetailsInfo.getHamlet().getHamletName() : "");
								voterVo.setIsCadre(surveyDetailsInfo.getIsCadre().toString());
								voterVo.setIsInfluencingPeople(surveyDetailsInfo.getIsInfluencingPeople().toString());
								booth.getVotersList().add(voterVo);
							}
							
						}
								
						
					}
					
				}
				
			
				
				
			}
		}
		catch (Exception e) {
			LOG.error("Exception raised in getSurveyUserBoothsAndVoterDetails service in SurveyDataDetailsService", e);
		}
		return resultList;
	}
	
	
	public SurveyResponceVO checkBoothExist(List<SurveyResponceVO> resultList,Long boothId)
	{
		try{
			
			if(resultList == null)
				return null;
			for(SurveyResponceVO vo : resultList)
			{
				if(vo.getBoothId().longValue() == boothId)
					return vo;
			}
		}
		catch (Exception e) {
			LOG.error("Exception raised in checkBoothExist service in SurveyDataDetailsService", e);
		}
		return null;
	}*/
	
}
