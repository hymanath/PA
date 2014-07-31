package com.itgrids.partyanalyst.service.impl;

import java.text.DateFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Random;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;

import org.apache.commons.lang.WordUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionCallback;
import org.springframework.transaction.support.TransactionCallbackWithoutResult;
import org.springframework.transaction.support.TransactionTemplate;

import com.itgrids.partyanalyst.dao.IBoothDAO;
import com.itgrids.partyanalyst.dao.IBoothPublicationVoterDAO;
import com.itgrids.partyanalyst.dao.ICasteStateDAO;
import com.itgrids.partyanalyst.dao.IConstituencyDAO;
import com.itgrids.partyanalyst.dao.IHamletDAO;
import com.itgrids.partyanalyst.dao.IPanchayatDAO;
import com.itgrids.partyanalyst.dao.ISurveyCallStatusDAO;
import com.itgrids.partyanalyst.dao.ISurveyCompletedLocationsDetailsDAO;
import com.itgrids.partyanalyst.dao.ISurveyDetailsInfoDAO;
import com.itgrids.partyanalyst.dao.ISurveySurveyorTypeDAO;
import com.itgrids.partyanalyst.dao.ISurveyUserBoothAssignDAO;
import com.itgrids.partyanalyst.dao.ISurveyUserConstituencyDAO;
import com.itgrids.partyanalyst.dao.ISurveyUserDAO;
import com.itgrids.partyanalyst.dao.ISurveyUserRelationDAO;
import com.itgrids.partyanalyst.dao.ISurveyUserTabAssignDAO;
import com.itgrids.partyanalyst.dao.ISurveyUserTrackingDAO;
import com.itgrids.partyanalyst.dao.ISurveyUserTypeDAO;
import com.itgrids.partyanalyst.dao.IUserDAO;
import com.itgrids.partyanalyst.dao.IVerifierBoothPercentageDAO;
import com.itgrids.partyanalyst.dao.IVoterDAO;
import com.itgrids.partyanalyst.dao.IVoterInfoDAO;
import com.itgrids.partyanalyst.dao.IWebMonitoringAssignedUsersDAO;
import com.itgrids.partyanalyst.dto.BasicVO;
import com.itgrids.partyanalyst.dto.ConstituencyDetailReportVO;
import com.itgrids.partyanalyst.dto.GenericVO;
import com.itgrids.partyanalyst.dto.ResultCodeMapper;
import com.itgrids.partyanalyst.dto.ResultStatus;
import com.itgrids.partyanalyst.dto.SelectOptionVO;
import com.itgrids.partyanalyst.dto.SurveyReportVO;
import com.itgrids.partyanalyst.dto.SurveyResponceVO;
import com.itgrids.partyanalyst.dto.UserBoothDetailsVO;
import com.itgrids.partyanalyst.dto.VerificationCompVO;
import com.itgrids.partyanalyst.model.SurveyCallStatus;
import com.itgrids.partyanalyst.model.SurveyDetailsInfo;
import com.itgrids.partyanalyst.model.SurveySurveyorType;
import com.itgrids.partyanalyst.model.SurveyUser;
import com.itgrids.partyanalyst.model.SurveyUserBoothAssign;
import com.itgrids.partyanalyst.model.SurveyUserConstituency;
import com.itgrids.partyanalyst.model.SurveyUserRelation;
import com.itgrids.partyanalyst.model.SurveyUserTabAssign;
import com.itgrids.partyanalyst.model.SurveyUserTracking;
import com.itgrids.partyanalyst.model.SurveyUserType;
import com.itgrids.partyanalyst.model.Voter;
import com.itgrids.partyanalyst.model.WebMonitoringAssignedUsers;
import com.itgrids.partyanalyst.service.ISurveyDataDetailsService;
import com.itgrids.partyanalyst.service.ISurveyDetailsService;
import com.itgrids.partyanalyst.utils.DateUtilService;
import com.itgrids.partyanalyst.utils.IConstants;
import com.itgrids.partyanalyst.webserviceutils.android.utilvos.UserLocationTrackingVo;

public class SurveyDataDetailsService implements ISurveyDataDetailsService
{

	private static final Logger LOG = Logger.getLogger(SurveyDataDetailsService.class);
	
	@Autowired
	private ISurveyUserTypeDAO surveyUserTypeDAO;
	
	@Autowired
	public ISurveyUserDAO surveyUserDAO;
	
	@Autowired
	private ISurveyUserTabAssignDAO surveyUserTabAssignDAO;
	
	@Autowired
	private ISurveyUserBoothAssignDAO surveyUserBoothAssignDAO;
	
	@Autowired
	private ISurveyUserRelationDAO surveyUserRelationDAO;
	
	@Autowired
	private ISurveySurveyorTypeDAO surveySurveyorTypeDAO;
	
	@Autowired
	public ISurveyDetailsInfoDAO surveyDetailsInfoDAO;
	
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
	public IHamletDAO hamletDAO;
	
	@Autowired
	public ICasteStateDAO casteStateDAO;
	
 	
	@Autowired
	private DateUtilService dateUtilService;
	
	@Autowired
	public IBoothPublicationVoterDAO boothPublicationVoterDAO;
	
	@Autowired
	private TransactionTemplate transactionTemplate;
	@Autowired
	private ISurveyUserConstituencyDAO surveyUserConstituencyDAO;
	
	@Autowired
	private IVoterInfoDAO voterInfoDAO;
	
	@Autowired
	private IUserDAO userDAO;
	
	@Autowired
	private ISurveyCallStatusDAO surveyCallStatusDAO;
	

	@Autowired
	private IWebMonitoringAssignedUsersDAO webMonitoringAssignedUsersDAO;
	
	@Autowired
	private ISurveyCompletedLocationsDetailsDAO surveyCompletedLocationsDetailsDAO;
	@Autowired
	private ISurveyDetailsService surveyDetailsService;
	
	@Autowired
	IVerifierBoothPercentageDAO verifierBoothPercentageDAO;
	
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
			Long userTypeId = surveyUserTypeDAO.checkForUsertype(userType.trim());
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
	public ResultStatus saveSurveyUserTabAssign(Long surveyUserId,List<BasicVO> tabsInfoList)
	{
		LOG.info("Entered into saveSurveyUserTabAssign service in SurveyDataDetailsService");
		ResultStatus resultStatus = new ResultStatus();
		try
		{
			
			SimpleDateFormat originalFormat = new SimpleDateFormat("dd-MM-yyyy");
			SimpleDateFormat targetFormat = new SimpleDateFormat("yyyy-MM-dd" );
			
			SurveyUserTabAssign result = null;
			
			if(tabsInfoList != null && tabsInfoList.size()>0){
				
				for (BasicVO basicVO : tabsInfoList) {
					
					DateUtilService date1 = new DateUtilService();
					SurveyUserTabAssign surveyUserTabAssign = new SurveyUserTabAssign();
					surveyUserTabAssign.setTabNo(basicVO.getCasteName().toString());
				//	surveyUserTabAssign.setRemarks(remarks);
					
					Date originalDate = originalFormat.parse(basicVO.getName());
					Date finalDate= targetFormat.parse(targetFormat.format(originalDate));
										
					surveyUserTabAssign.setDate(finalDate);
					surveyUserTabAssign.setSurveyUser(surveyUserDAO.get(surveyUserId));
					surveyUserTabAssign.setInsertedTime(date1.getCurrentDateAndTime());
					surveyUserTabAssign.setUpdatedTime(date1.getCurrentDateAndTime());
					surveyUserTabAssign.setActiveStatus("Y");
					
					result = surveyUserTabAssignDAO.save(surveyUserTabAssign);
				}
				
			}
			

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
	public ResultStatus saveSurveyUserBoothAssign(Long surveyUserId,Long constituencyId,List<Long> boothIds,String saveSurveyUserBoothAssign)	{
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
					resultStatus.setResultCode(ResultCodeMapper.SUCCESS);
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
                    
                    if(saveSurveyUserBoothAssign.equalsIgnoreCase("true"))
                    	surveyUserBoothAssign.setRemainingDataBooth("Y");
                    else
                    	surveyUserBoothAssign.setRemainingDataBooth("N");
                    	
					surveyUserBoothAssignDAO.save(surveyUserBoothAssign);
					resultStatus.setResultCode(ResultCodeMapper.SUCCESS);
					
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
	public ResultStatus saveServeyUserRelationDetails(Long userTypeId,List<Long> surveyUserIds,Long leaderId)
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
					//surveyUserRelation.setConstituency(constituencyDAO.get(constituencyId));
					surveyUserRelation.setInsertedTime(dateUtilService.getCurrentDateAndTime());
					surveyUserRelation.setUpdatedTime(dateUtilService.getCurrentDateAndTime());
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
	public ResultStatus deactivateUser(Long userId,String remarks,Long userTypeId)
	{
		LOG.info("Entered into deactivateUser service in SurveyDataDetailsService");
		ResultStatus resultStatus = new ResultStatus();
		try
		{
				
		
		 if(userTypeId.longValue() == 3L || userTypeId.longValue() == 5L)
		 
			{
			 resultStatus = deactivateUserByID(userId,remarks);
			}
		 else if(userTypeId.longValue() == 1L || userTypeId.longValue() == 4L){
			 releaseTabsAndBoothsBySurveyUserId(userId,null,remarks);
			 resultStatus = deactivateUserByID(userId,remarks);
		 }
			
			/*
			 if(userTypeId == 3)
			{
				List<Object[]> users = surveyUserRelationDAO.getUsersForAssignedUser(userId,userTypeId);
				if(users != null && users.size() > 0)
				{
					resultStatus.setResultCode(4);	
					resultStatus.setMessage("users exist");// for leader users are available
					return resultStatus;	
				}
				
			}
			resultStatus = deactivateUserByID(userId,remarks);
			*/
			
			
		}
		catch (Exception e) 
		{
			LOG.error("Exception raised in deactivateUser service in SurveyDataDetailsService", e);
			resultStatus.setResultCode(3);
			resultStatus.setMessage("Exception");
		}
		return resultStatus;
}
	
	
	
	
	/* deactivate user */
	public ResultStatus deactivateUserByID(final Long userId,final String remarks)
	{

	ResultStatus resultStatus = (ResultStatus) transactionTemplate
			.execute(new TransactionCallback() {
				public Object doInTransaction(TransactionStatus status) {

					ResultStatus rs = new ResultStatus();

					try {
							SurveyUser surveyUser = surveyUserDAO.get(userId);
							if(surveyUser == null)
							{
								rs.setResultCode(3);
								rs.setMessage("No Exist");
								return rs;
							}
							/*surveyUser.setActiveStatus("N");
							surveyUser.setActiveStatus("Y"); // if we delete it first it will not works further . don't change as N
							surveyUser.setRemarks(remarks);
							SurveyUser result = surveyUserDAO.save(surveyUser); */
							if(surveyUser != null)
							{
								rs.setResultCode(0);
								rs.setMessage("Success");
							}
							else
							{
								rs.setResultCode(1);
								rs.setMessage("Failure");
							}
					} catch (Exception ex) {

						status.setRollbackOnly();
						ex.printStackTrace();
						
						rs.setExceptionEncountered(ex);
						rs.setExceptionMsg(ex.getMessage());
						rs.setResultCode(ResultCodeMapper.FAILURE);

						return rs;
					}
					return rs;
				}
			});

	return resultStatus;
	}
	
	
	/* deactivate Survey leader */
	public ResultStatus deactiveSurveyLeader(final Long userId,final String remarks,final Long userTypeId,final Long dummyLeadId)
	{
		
		ResultStatus status = releaseTabsAndBoothsBySurveyUserId(userId,dummyLeadId,remarks); 
		/*
		final DateUtilService date = new DateUtilService();
		
		final List<Long> surveyUserIds = new ArrayList<Long>();
		
			List<Object[]> users = surveyUserRelationDAO.getUsersForAssignedUser(userId,userTypeId);
			
			deactivateUserByID(userId,remarks);
			
			if(users != null && users.size() > 0)
			{
			
				for(Object[] params : users)
				{
					if(!surveyUserIds.contains((Long)params[0]))
					surveyUserIds.add((Long)params[0]);
					
				}
			}
			
			///* dummy leader save */
		/*	ResultStatus resultStatus = (ResultStatus) transactionTemplate
					.execute(new TransactionCallback() {
						public Object doInTransaction(TransactionStatus status) {

							ResultStatus rs = new ResultStatus();

							try {
								
			/* save dummy lead */
			/*SurveyUser surveyUser = new SurveyUser();
			surveyUser.setFirstName(dummyLeadName);
			surveyUser.setSurveyUserType(surveyUserTypeDAO.get(userTypeId));
			surveyUser.setActiveStatus("Y");
			surveyUser.setInsertedTime(date.getCurrentDateAndTime());
			surveyUser.setUpdatedTime(date.getCurrentDateAndTime());*/
								
		/*	SurveyUser surveyUser = surveyUserDAO.get(dummyLeadId);
			
			
			if(surveyUserIds != null && surveyUserIds.size() > 0)
			{*/
				/* tabNo assign to dummy leader*/ 
		/*		List<Object[]> tabNos = surveyUserTabAssignDAO.getTabNos(surveyUserIds);
				List<Long> surveyUserTabIDs = new ArrayList<Long>();
				if(tabNos != null && tabNos.size() > 0)
				{
					for(Object[] tab : tabNos)
					{
					
					if(!surveyUserTabIDs.contains((Long)tab[1]))
					surveyUserTabIDs.add((Long)tab[1]);	
					
					SurveyUserTabAssign surveyUserTabAssign = new SurveyUserTabAssign();
					surveyUserTabAssign.setTabNo(tab[0].toString());
					surveyUserTabAssign.setSurveyUser(surveyUser);
					surveyUserTabAssign.setActiveStatus("Y");
					surveyUserTabAssignDAO.save(surveyUserTabAssign);
				
					
					}
					
					surveyUserTabIDs.add(userId);
					surveyUserTabAssignDAO.updateActiveStatus(surveyUserTabIDs);
				
				}
			
				
				List<Long> surveyUserRelationIds = new ArrayList<Long>();
				Map<Long,Long> constituencyMap = new HashMap<Long, Long>();//<surveyuserId,constiId>
				List<Object[]> list = surveyUserRelationDAO.getConstituencyForSurveyUser(surveyUserIds);
					if(list != null && list.size() > 0)
					{
						for(Object[] params1 : list)
						{
							if(!surveyUserRelationIds.contains((Long)params1[2]))
						    surveyUserRelationIds.add((Long)params1[2]);
							
							SurveyUserRelation survRelation = surveyUserRelationDAO.get((Long)params1[2]);
							SurveyUserRelation surveyUserRelation = new SurveyUserRelation();
							if(survRelation.getConstituency() != null)
							surveyUserRelation.setConstituency(constituencyDAO.get(constituencyMap.get(survRelation.getConstituency().getConstituencyId())));
							surveyUserRelation.setSurveyUser(surveyUserDAO.get((Long)params1[1]));
							surveyUserRelation.setSurveyLeader(surveyUser);
							surveyUserRelation.setSurveyUserType(surveyUserTypeDAO.get(userTypeId));
							surveyUserRelation.setActiveStatus("Y");
							surveyUserRelationDAO.save(surveyUserRelation);
						
						}
					
						
						surveyUserRelationIds.add(userId);
						surveyUserRelationDAO.updateActiveStatusByIDs(surveyUserRelationIds);
						
					}
					
				*/
					/* survey user constituency */
					
		/*			List<Object[]> list3 = surveyUserConstituencyDAO.getSurveyUserConstituency(userId);
					if(list3 != null && list3.size() > 0)
					{
						List<Long> surveyUserConstituencyIds = new ArrayList<Long>();
						  for(Object[] params : list3)
							{
							
						    surveyUserConstituencyIds.add((Long)params[0]);
						    
						    
							SurveyUserConstituency survConstituency = new SurveyUserConstituency();
							survConstituency.setConstituency(constituencyDAO.get((Long)params[1]));
							survConstituency.setSurveyUser(surveyUser);
							survConstituency.setActiveStatus("Y");
							surveyUserConstituencyDAO.save(survConstituency);
							}
						  
						  surveyUserConstituencyIds.add(userId);
						  surveyUserConstituencyDAO.updateActiveStatusByList(surveyUserConstituencyIds);
					}
				
				
				
			}
							} catch (Exception ex) {

								status.setRollbackOnly();
								ex.printStackTrace();
								
								rs.setExceptionEncountered(ex);
								rs.setExceptionMsg(ex.getMessage());
								rs.setResultCode(ResultCodeMapper.FAILURE);

								return rs;
							}
							return rs;
						}
					});
				*/
			return status;
			
	
		
	}
	
	
	
	
	
	/**
	 * This Service is Used For Saving Survey Data
	 * @param List<surveyResponceVO>
	 * @return resultStatus
	 */
	public ResultStatus saveSurveyDataDetailsInfo( final SurveyResponceVO inputResponse )
	{
		
		final List<SurveyResponceVO> surveyResponceList=inputResponse.getVerifiersData();
		LOG.info("Entered into saveSurveyDataDetailsInfo service in SurveyDataDetailsService");
		final ResultStatus resultStatus = new ResultStatus();
		try
		{
			if(surveyResponceList != null && surveyResponceList.size() > 0)
			{
				final SurveyUser surveyUser = surveyUserDAO.get(Long.valueOf(inputResponse.getUserId()));
                final Long userTypeId=Long.valueOf(inputResponse.getUserTypeId());
                final Long constituencyId=Long.valueOf(inputResponse.getConstituencyId());
                final Long userId=Long.valueOf(inputResponse.getUserId());
				transactionTemplate.execute(new TransactionCallbackWithoutResult() {

					@Override
					protected void doInTransactionWithoutResult(
							TransactionStatus arg0) {
							try {
									
								for (SurveyResponceVO surveyResponceVO : surveyResponceList)
								{
									
									
									Long voterID=surveyResponceVO.getVoterId();
									
									//check whether record avilable for user and uniqueID
									SurveyDetailsInfo surveyDetailsInfo=surveyDetailsInfoDAO.checkUserForVoter(userId, surveyResponceVO.getUuid(),voterID);
									
									
									if(surveyDetailsInfo==null){
										
										surveyDetailsInfo = new SurveyDetailsInfo();
										surveyDetailsInfo.setInsertedTime(new DateUtilService().getCurrentDateAndTime());
										SimpleDateFormat sdf = new SimpleDateFormat(IConstants.DATE_AND_TIME_FORMAT);
									//	sdf.setTimeZone(TimeZone.getTimeZone(IConstants.TIME_ZONE_INDIA));
										//sdf.parse(surveyResponceVO.getInsertTime());
										surveyDetailsInfo.setDate(sdf.parse(surveyResponceVO.getInsertTime()));
									
									}else{
										surveyDetailsInfo.setUpdatedTime(new DateUtilService().getCurrentDateAndTime());
									}
									
									if(surveyUser != null)
									{
										surveyDetailsInfo.setSurveyUser(surveyUser);
										SurveySurveyorType surveySurveyorType = surveySurveyorTypeDAO.get(Long.valueOf(surveyResponceVO.getDataTypeId()));
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
										//surveyDetailsInfo.setBooth(boothDAO.get(surveyResponceVO.getBoothId()));
										surveyDetailsInfo.setBoothId(surveyResponceVO.getBoothId());
										surveyDetailsInfo.setLatitude(surveyResponceVO.getLatitude());
										surveyDetailsInfo.setLongitude(surveyResponceVO.getLongitude());
										surveyDetailsInfo.setIsCadre(surveyResponceVO.getIsCadre());
										surveyDetailsInfo.setIsInfluencingPeople(surveyResponceVO.getIsInfluencingPeople());
										if(surveyResponceVO.getMobileNo()!=null&&!surveyResponceVO.getMobileNo().equalsIgnoreCase("null"))
										surveyDetailsInfo.setMobileNumber(surveyResponceVO.getMobileNo());
										
										surveyDetailsInfo.setLocalArea(surveyResponceVO.getLocalArea());
										surveyDetailsInfo.setHamletName(surveyResponceVO.getHamletName());
										surveyDetailsInfo.setCasteName(surveyResponceVO.getCasteName());
										surveyDetailsInfo.setUuid(surveyResponceVO.getUuid());
										surveyDetailsInfo.setStatusId(Integer.valueOf(surveyResponceVO.getStatusId()));
										if(surveyResponceVO.getWardId() != null && surveyResponceVO.getWardId() > 0)
										surveyDetailsInfo.setWardId(surveyResponceVO.getWardId());
										
										surveyDetailsInfo.setHouseNoPoint(surveyResponceVO.getHouseNo());
										
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
									//throw new RuntimeException("anil throw");
		
								}
							}catch (Exception e) {
							LOG.debug("exception raised at inside transaction ", e);
							throw new RuntimeException(e.getMessage());
						}
					}});
		
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
	
	
	public List<GenericVO> getSurveyUsersByUserType(Long userTypeId,Long constituencyId)
	{
		List<GenericVO> returnList = null;
		try
		{
			List<Object[]> result = null ; 
			
			List<Object[]> leadersList = surveyUserConstituencyDAO.getSurveyConstituencyLeadersList(constituencyId);
			List<Long> leaderIds = new ArrayList<Long>(0);
			if(leadersList != null && leadersList.size()>0){
				for (Object[] leader : leadersList) {
					leaderIds.add(leader[0] != null ? (Long)leader[0]:0L);
				}
			}
			
			// 1 for data collectors & 3 for data leads , 4 for data verifiers & 5 for verifier leads
			if(userTypeId.longValue() == 1L)
			{ 
				userTypeId = 3L;
			}
			else if (userTypeId.longValue() == 4L) {
				userTypeId = 5L;
			}			
			
			if( userTypeId.longValue() == 3L || userTypeId.longValue() == 5L){
				
				if(leaderIds != null && leaderIds.size()>0){
					result = surveyUserRelationDAO.getLeadersBysurveyUserIds(leaderIds,userTypeId);
				}
			}
			
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
	
	/**
	 * @author Srishailam Pittala
	 * @param leaderId
	 * @return List<GenericVO>
	 */
	public List<GenericVO> getSurveyUsersByLeader(Long leaderId)
	{
		List<GenericVO> returnList = null;
		try
		{
			List<Object[]> result = surveyUserRelationDAO.getUserForAssignedUsers(leaderId);
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
			//List<Object[]> result = surveyUserRelationDAO.getLeadersByConstituency();
			List<Object[]> result = surveyUserConstituencyDAO.getLeadersByConstituency();
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
					VO.setDesc(parms[3] != null ? parms[3].toString():"");
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
	 * @return returnList
	 */
	public List<GenericVO> getLatLongForUserTrackung(Long surveyUserId,Date date)
	{
		List<GenericVO> returnList = null;
		try 
		{
			List<Object[]> result = surveyUserTrackingDAO.getLatLongForUserTracking(surveyUserId, date);
			if(result != null && result.size() > 0)
			{
				DateFormat dateFormat = new SimpleDateFormat("hh:mm a"); 
				returnList = new ArrayList<GenericVO>();
				for (Object[] parms : result)
				{
					GenericVO VO = new GenericVO();
					VO.setName(parms[0] != null ? parms[0].toString() : "");//longtitude
					VO.setDesc(parms[1] != null ? parms[1].toString() : "");//latitude
					String time = dateFormat.format(parms[2]);
					VO.setPercent(time);
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
				VO.setHouseNo(surveyDetailsInfo.getVoter().getHouseNo());
				VO.setUuid(surveyDetailsInfo.getUuid());
			}
			VO.setMobileNo(surveyDetailsInfo.getMobileNumber());
			VO.setIsCadre(surveyDetailsInfo.getIsCadre());
			VO.setIsInfluencingPeople(surveyDetailsInfo.getIsInfluencingPeople());
			if(surveyDetailsInfo.getCaste() != null)
			{
				String casteName = casteStateDAO.get(surveyDetailsInfo.getCaste().getCasteStateId()).getCaste().getCasteName();
				VO.setCasteName(casteName);
				VO.setCasteId(surveyDetailsInfo.getCaste().getCasteStateId());
			}
			else
			{
				VO.setCasteName(surveyDetailsInfo.getCasteName());
			}
			if(surveyDetailsInfo.getHamlet() != null)
			{
				String hamletName = hamletDAO.get(surveyDetailsInfo.getHamlet().getHamletId()).getHamletName();
				VO.setHamletName(hamletName);
				VO.setHamletId(surveyDetailsInfo.getHamlet().getHamletId());
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
	 * This Service is used for assigng voter details for Verifier for data collection
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
				
				List<Object[]> voterHouseDetails = boothPublicationVoterDAO.getTotalVotersByBoothsForVerfier(boothId, IConstants.VOTER_DATA_PUBLICATION_ID);
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
				
			//Integer totalBoothAvaliableData = totalVoterMap.size();
				//if(totalBoothAvaliableData >= totalCollectedData)
				//{
					Integer percentage = 10;
					Integer eareseDataCount = null;
					List<String> percfList = verifierBoothPercentageDAO.getBoothWisePercentage(boothId);
					if(percfList != null && percfList.size() > 0)
					percentage = Integer.valueOf(percfList.get(0));
					if(percentage.intValue() != 100)
					{
						if(percentage > 0)
						{
							 eareseDataCount =(percentage*totalCollectedData)/100;
						}
						else
						{
							 eareseDataCount =(10/totalCollectedData)*100;
						}
						
						if(SurveyResponceVOList != null && SurveyResponceVOList.size() > 0)
						{
							Random randomNum = new Random();
							Integer size = SurveyResponceVOList.size()-1;
							for(Integer i = 0 ; i < eareseDataCount ; i++)
							{
								SurveyResponceVOList.remove(randomNum.nextInt(size)) ;
								size--;
							}
							for (SurveyResponceVO surveyResponceVO : SurveyResponceVOList)
							{
								collectedDataMap.put(surveyResponceVO.getVoterId(), surveyResponceVO);
							}
						}
						
					}
					
					
				//}
				
				if(totalVoterMap != null && totalVoterMap.size() > 0)
				{
					returnList = new ArrayList<SurveyResponceVO>();
					List<Long> voterIds = new ArrayList<Long>(totalVoterMap.keySet());
					for (Long voterId : voterIds)
					{
						SurveyResponceVO VO = collectedDataMap.get(voterId);
						if(VO == null)
						{   
							VO=totalVoterMap.get(voterId);
							VO.setDataTypeId("1");
						}
						else
						{
							VO.setDataTypeId("2");
						}
						VO.setBoothId(boothId);
						returnList.add(VO);
					}
				}
				
				
			}
		 }
		 catch (Exception e) 
		 {
			 LOG.error("Exception raised in getDetailsForVerifier service in SurveyDataDetailsService", e);
			 e.printStackTrace();
		 }
		 return returnList;
	}
	
	public List<UserBoothDetailsVO> getAssignedBoothsDetailsByConstituencyIdAndUserId(Long constituencyId,Long userId)
	{
		
		LOG.info("Entered into saveSurvetUserBoothAssign service in SurveyDataDetailsService");
		List<UserBoothDetailsVO> resultList = new ArrayList<UserBoothDetailsVO>();
		
		try
		{
			List<Object[]> boothDtls = boothDAO.getAllTheBoothsDetailsByConstituencyId(constituencyId,IConstants.VOTER_DATA_PUBLICATION_ID);
			
			List<Long> existingBoothIds = new ArrayList<Long>();
			
			List<Object[]> existingBoothsDtls = surveyUserBoothAssignDAO
					.getAllTheAssignedBoothsByConstituencyIdAndUserId(
							constituencyId, userId);
			
			for(Object[] existingBoothDtls:existingBoothsDtls)
				existingBoothIds.add((Long)existingBoothDtls[1]);
			
			
			/*SurveyUser surveyUser = surveyUserDAO.get(userId);
			
			List<Long> completedBoothsList = new ArrayList<Long>();
			 List<Long> completedBoothIds = new ArrayList<Long>();
			if(surveyUser.getSurveyUserType().getSurveyUsertypeId().equals(IConstants.VERIFIER_ROLE_ID))
				completedBoothsList = surveyCompletedLocationsDetailsDAO.getCompletedBoothDetailsByBoothIds(existingBoothIds);
			 completed panchayat booths 	
			SurveyReportVO vo = surveyDetailsService.getPanchayatsStatusCountByConstituency(constituencyId);
			List<Long> panchayatIds = vo.getCompleteIds();
			List<Long> boothIds = new ArrayList<Long>();
			if(panchayatIds != null && panchayatIds.size() > 0)
			{
			 List<Object[]> list = boothDAO.getBoothsByPanhcayats(panchayatIds,IConstants.VOTER_DATA_PUBLICATION_ID); 
			 if(list != null && list.size() > 0)
			 {
				 for(Object[] params : list)
				 {
					 if(!boothIds.contains((Long)params[2]))
					 boothIds.add((Long)params[2]);
				 }
			 }
			
			
			 for(Long id : completedBoothsList)
			 {
				
				 if(boothIds.contains(id))
					 completedBoothIds.add(id);
			 }
			}*/
			 
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
				
			/*	if(surveyUser.getSurveyUserType().getSurveyUsertypeId().equals(IConstants.VERIFIER_ROLE_ID))
				{
					//if(completedBoothsList.contains((Long)obj[0]))
					if(completedBoothIds.contains((Long)obj[0]))
						resultList.add(boothDetails);
				}else
				{*/
					resultList.add(boothDetails);
				//}
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
			/* DateFormat df = new SimpleDateFormat("yyyy-mm-dd"); 
		    Date startDt =  df.parse(startDate);
		    Date endDt =  df.parse(endDate);*/
			
			SimpleDateFormat originalFormat = new SimpleDateFormat("dd-MM-yyyy");
			SimpleDateFormat targetFormat = new SimpleDateFormat("yyyy-MM-dd" );
			Date strdate;
			Date enddate;
			strdate = originalFormat.parse(startDate);
			Date convertedstrdate = targetFormat.parse(targetFormat.format(strdate));			
			
			enddate = originalFormat.parse(endDate);
			Date convertedenddate = targetFormat.parse(targetFormat.format(enddate));
		    
			List<Object[]> dayWiseReportDtls = surveyDetailsInfoDAO.getDayWisereportDetailsByConstituencyId(constituencyId,convertedstrdate,convertedenddate,userTypeId);
		    
			
			
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
					SimpleDateFormat inFormat = new SimpleDateFormat("yyyy-MM-dd");
					SimpleDateFormat outFormat = new SimpleDateFormat("dd-MMM-yyyy");
					Calendar c = Calendar.getInstance();
					c.setTime(inFormat.parse(surveyDate));
					dateVO.setSurveyDate1(outFormat.format(c.getTime()));
					dateVO.setSurveyDate(surveyDate);
					userVO.getSubList().add(dateVO);
				}
				
				reportList.add(userVO);
			}
			
             for(Object[] obj:dayWiseReportDtls)
             {
            	
            	 SurveyReportVO userVO = getMatchedUserVO(reportList,(Long)obj[1]);
	            	
	            	 userVO.setUserName(obj[2] != null ? obj[2].toString() : "");
		            	
		             SurveyReportVO dateVO = getMatchedSurveyDateVO(userVO.getSubList(),obj[3].toString());
		             dateVO.setCount(obj[0] != null ? (Long)obj[0] : 0l);
		            	
            	
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
	
	public List<SurveyReportVO> getBoothWiseUserSamplesDetailsByDates(Long userId,String startDate)
	{
		LOG.info("Entered into getBoothWiseUserSamplesDetailsByDates service in SurveyDataDetailsService");

		List<SurveyReportVO> resultList = new ArrayList<SurveyReportVO>();
		
		 try {
			/*DateFormat df = new SimpleDateFormat("MM/dd/yyyy"); 
			    
			    Date startDt =  df.parse(startDate);
			    Date endDt =  df.parse(endDate);
			
			List<Object[]> boothWiseCountList =  surveyDetailsInfoDAO.getBoothWiseUserSamplesDetailsByDates(userId,startDt,endDt);
			*/
			 
			 SimpleDateFormat originalFormat = new SimpleDateFormat("dd-MM-yyyy");
				SimpleDateFormat targetFormat = new SimpleDateFormat("MM-dd-yyyy" );
				Date strdate;
				//Date enddate;
				strdate = originalFormat.parse(startDate);
				Date convertedstrdate = targetFormat.parse(targetFormat.format(strdate));			
				
			//	enddate = originalFormat.parse(endDate);
				//Date convertedenddate = targetFormat.parse(targetFormat.format(enddate));
			
			List<Object[]> boothWiseCountList =  surveyDetailsInfoDAO.getBoothWiseUserSamplesDetailsByDates(userId,convertedstrdate);
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
	
	
	public List<SurveyReportVO> getReportForVerification(Long boothId,String type)
	{
		LOG.info("Entered into getReportForVerification service in SurveyDataDetailsService");

		List<SurveyReportVO> resultList = new ArrayList<SurveyReportVO>();
		
		try {
			List<SurveyDetailsInfo> votersList = boothPublicationVoterDAO.getVotersDetailsByBoothId(boothId);
			
			Set<Long>  voterIds = new HashSet<Long>();
			 
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
                subTypeVO.setSurveyDetailsInfoId(surveyDtlsInfo.getSurveyDetailsInfoId());
                subTypeVO.setVoterIDCardNo(surveyDtlsInfo.getVoter().getVoterIDCardNo());
                subTypeVO.setCasteId(surveyDtlsInfo.getCaste().getCasteStateId());
                subTypeVO.setVerified(surveyDtlsInfo.getVerified());    
                subTypeVO.setCaste(surveyDtlsInfo.getCaste().getCaste().getCasteName());
                
                if(surveyDtlsInfo.getHamlet() != null)
                {
	        	 subTypeVO.setHamletName(surveyDtlsInfo.getHamlet().getHamletName());
	             subTypeVO.setHamletId(surveyDtlsInfo.getHamlet().getHamletId());
                }

				if(surveyDtlsInfo.getSurveyUser().getSurveyUserType().getSurveyUsertypeId().equals(IConstants.DATA_COLLECTOR_TYPE_ID))// collector
				{
					voterVO.setDataCollector(subTypeVO);
					voterVO.setVoterIDCardNo(surveyDtlsInfo.getVoter().getVoterIDCardNo());
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
			
			for(SurveyReportVO voterVO:resultList)
			{
				if(voterVO.getVerifier() == null && voterVO.getThirdParty() == null)
				{
					voterVO.setCadreMatched(true);
					voterVO.setInfluencePeopleMatched(true);
					voterVO.setLocalAreaMatched(true);
					voterVO.setCasteMatched(true);
					voterVO.setHamletMatched(true);
					
				}
				
			}
			
			Long matchedCount = 0L;
			Long unmatchedCount = 0L;
			
			for(SurveyReportVO voterVO:resultList)
			{
				if(voterVO.isCadreMatched() && voterVO.isInfluencePeopleMatched() && voterVO.isLocalAreaMatched() && voterVO.isCasteMatched() && voterVO.isHamletMatched())
					matchedCount ++;
				else
					unmatchedCount++;
			}
			
			if(resultList != null && resultList.size() >0)
			{
				resultList.get(0).setMatchedCount(matchedCount);
				resultList.get(0).setUnmatchedCount(unmatchedCount);
				
			}
			
			Iterator<SurveyReportVO> itr = resultList.iterator();

			
			if(type.equalsIgnoreCase("unmatched"))
			{
				
				while(itr.hasNext())
				{
					SurveyReportVO voterVO =itr.next();
						if(voterVO.isCadreMatched() && voterVO.isInfluencePeopleMatched() && voterVO.isCasteMatched() && voterVO.isHamletMatched() && voterVO.isLocalAreaMatched())
						    itr.remove();
						
							
							
				}
			}else if(type.equalsIgnoreCase("matched"))
			{
			
				while(itr.hasNext())
				{
					SurveyReportVO voterVO =itr.next();
						if(!(voterVO.isCadreMatched() && voterVO.isInfluencePeopleMatched() && voterVO.isCasteMatched() && voterVO.isHamletMatched() && voterVO.isLocalAreaMatched()))
						{
							 itr.remove();
						}
						
				}
			}
			
			
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
	
	/**
	 * This Service is used for getting survey user id.
	 * @param userName
	 * @param password
	 * @return userId,userTypeId
	 *  
	 */
	public Object[] auhenticateUserandGetUserType(String userName,String password)
	{
		LOG.info("Entered into getUserDetailsForCheck service in SurveyDataDetailsService");
		Object[] userId = null;
		try
		{
			 userId =(Object[]) surveyUserDAO.getUserDetailsAndUserType(userName,password);
		} 
		catch (Exception e)
		{
			LOG.error("Exception raised in getUserDetailsForCheck service in SurveyDataDetailsService", e);
		}	
		return userId;
	}
	private void checkForMatchedDetailsForDataCollectorAndVerifier(List<SurveyReportVO> votersList)
	{

		try {
			for(SurveyReportVO voterVO:votersList)
			{
				
				if(voterVO.getDataCollector() != null && voterVO.getVerifier() != null && voterVO.getThirdParty() == null)
				{
					if (voterVO.getDataCollector().getCadre().equalsIgnoreCase(voterVO
							.getVerifier().getCadre()))				
					{
						voterVO.setCadreMatched(true);
					}
					else
					{
						voterVO.setCadreMatched(false);
					}
					
					
					if (voterVO.getDataCollector().getInfluencePeople().equalsIgnoreCase(voterVO
							.getVerifier().getInfluencePeople()))					
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
					if (voterVO.getDataCollector().getCadre().equalsIgnoreCase(voterVO
							.getVerifier().getCadre())
							&& voterVO.getVerifier().getCadre().equalsIgnoreCase(voterVO
									.getThirdParty().getCadre()))					
					{
						voterVO.setCadreMatched(true);
					}
					else
					{
						voterVO.setCadreMatched(false);
					}
					
					
					
					if (voterVO.getDataCollector().getInfluencePeople().equalsIgnoreCase(voterVO
							.getVerifier().getInfluencePeople())
							&& voterVO.getVerifier().getInfluencePeople().equalsIgnoreCase(voterVO
									.getThirdParty().getInfluencePeople()))					
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
	
/*	
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
	}*/
	
	public List<GenericVO> releaseLeadersWithUser(Long leaderId,Long userType)
	{
		List<GenericVO> returnList = null;
		try
		{
			List<Object[]> result = surveyUserRelationDAO.getUserForAssignedUser(leaderId,userType);
			if(result != null && result.size() > 0)
			{
				returnList = new ArrayList<GenericVO>();
				for (Object[] parms : result) 
				{
					GenericVO genericVO = new GenericVO();
					genericVO.setId(parms[0] != null ? (Long)parms[0] : 0l);
					genericVO.setName(parms[1] != null ? parms[1].toString() : "");
					/*genericVO.setRank(parms[2] != null ? (Long)parms[2] : 0l);
					genericVO.setDesc(parms[3] != null ? parms[3].toString() : "");*/
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
	public ResultStatus updateServeyUserRelationDetails(final Long userTypeId,final List<Long> surveyUserIds,final Long leaderId, final Long dummyLeaderId)
	{
		LOG.info("Entered into saveServeyUserRelationDetails service in SurveyDataDetailsService");
		ResultStatus resultStatus = new ResultStatus();
		try 
		{
			//int count = surveyUserRelationDAO.updateUserLeaderRelations(userTypeId,surveyUserIds, leaderId);
			
			transactionTemplate.execute(new TransactionCallbackWithoutResult() {

				protected void doInTransactionWithoutResult(TransactionStatus arg0) {
					if(surveyUserIds != null && surveyUserIds.size() > 0)
					{
	
						List<Object[]> surveyUserRelationDtls = surveyUserRelationDAO.getConstituencyForSurveyUser(surveyUserIds);

						if(surveyUserRelationDtls != null && surveyUserRelationDtls.size()>0){
							for (Object[] surveyUserRelation : surveyUserRelationDtls) {

								SurveyUserRelation surveyUserRelationModel = surveyUserRelationDAO.get(surveyUserRelation[2] != null ? (Long) surveyUserRelation[2]:0L);
								surveyUserRelationModel.setActiveStatus("N");
								surveyUserRelationModel.setUpdatedTime(dateUtilService.getCurrentDateAndTime());
								surveyUserRelationModel = surveyUserRelationDAO.save(surveyUserRelationModel);
								
								if(dummyLeaderId != null){ // this loop works for if leader deactivating
									
									SurveyUserRelation userRelation = new SurveyUserRelation();
									userRelation.setSurveyUserType(surveyUserRelationModel.getSurveyUserType());
									userRelation.setSurveyLeader(surveyUserDAO.get(dummyLeaderId));
									userRelation.setSurveyUser(surveyUserRelationModel.getSurveyUser());
									userRelation.setConstituency(surveyUserRelationModel.getConstituency());
									userRelation.setActiveStatus("Y");
									userRelation.setInsertedTime(surveyUserRelationModel.getInsertedTime());
									userRelation.setUpdatedTime(dateUtilService.getCurrentDateAndTime());
									
									surveyUserRelationDAO.save(userRelation);
								}
								
							}
						}
						
						List<Long> assignedUsers= surveyUserBoothAssignDAO.getAssignedDetailsForUser(surveyUserIds);
						if(assignedUsers != null && assignedUsers.size()>0){
							for (Long user: assignedUsers) {
								
								SurveyUserBoothAssign surveyUserBoothAssign = surveyUserBoothAssignDAO.get(user);
								surveyUserBoothAssign.setIsDelete("Y");
								
								surveyUserBoothAssign = surveyUserBoothAssignDAO.save(surveyUserBoothAssign); 
							}
						}
									
						List<Object[]> assignTabsIdsList = surveyUserTabAssignDAO.getSurveyTabsBySurveyUserIdsList(surveyUserIds);
						
						if(assignTabsIdsList != null && assignTabsIdsList.size()>0){
							for (Object[] assgnTab : assignTabsIdsList) {
								
								SurveyUserTabAssign surveyUserTabAssign = surveyUserTabAssignDAO.get(assgnTab[2] != null ?(Long) assgnTab[2]:0L );
								surveyUserTabAssign.setActiveStatus("N");
								surveyUserTabAssign.setUpdatedTime(new DateUtilService().getCurrentDateAndTime());
								
								surveyUserTabAssign = surveyUserTabAssignDAO.save(surveyUserTabAssign); 
								
								
								if(dummyLeaderId != null){ // this loop works for if leader deactivating
									
									for (Long surveyUserId : surveyUserIds) {
										
										SurveyUserTabAssign userTabAssign = new SurveyUserTabAssign();
										userTabAssign.setSurveyUser(surveyUserDAO.get(surveyUserId));
										userTabAssign.setTabNo(surveyUserTabAssign.getTabNo());
										userTabAssign.setActiveStatus("Y");
										userTabAssign.setDate(surveyUserTabAssign.getDate());
										userTabAssign.setInsertedTime(surveyUserTabAssign.getInsertedTime());
										userTabAssign.setUpdatedTime(dateUtilService.getCurrentDateAndTime());
										
										surveyUserTabAssignDAO.save(userTabAssign); 
										
									}
									
								}
							}
						}
					
					}
				}
			});

			resultStatus.setResultCode(0);
			resultStatus.setMessage("Success");
		}
		catch (Exception e)
		{
			LOG.error("Exception raised in saveServeyUserRelationDetails service in SurveyDataDetailsService", e);
			resultStatus.setResultCode(3);
			resultStatus.setMessage("Exception");
		}
		return resultStatus;
	}
	
	public String saveVerifiedRecordsDetails(final List<Long> verifierIds)
	{
		LOG.info("Entered into saveVerifiedRecordsDetails service in SurveyDataDetailsService");

		try
		{
			transactionTemplate.execute(new TransactionCallbackWithoutResult() {
				public void doInTransactionWithoutResult(TransactionStatus status) {

				for(Long verifierId:verifierIds)
				{
					SurveyDetailsInfo surveyDetailsInfo = surveyDetailsInfoDAO.get(verifierId);
					
						List<SurveyDetailsInfo> surveyDetailsInfos = surveyDetailsInfoDAO
								.getVerifiedVotersDetailsBySurveyDetailsInfoId(surveyDetailsInfo
										.getVoter().getVoterId());
						
						for(SurveyDetailsInfo info:surveyDetailsInfos)
						{
							info.setVerified("N");
							surveyDetailsInfoDAO.save(info);

						}
					
					surveyDetailsInfo.setVerified("Y");
					
					surveyDetailsInfoDAO.save(surveyDetailsInfo);
				}
			}});
		}catch(Exception e)
		{
			e.printStackTrace();
			LOG.error("Exception raised in saveVerifiedRecordsDetails service in SurveyDataDetailsService", e);
			return null;
		}
		return "success";
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
	
	public List<UserBoothDetailsVO> getBoothDetailsByConstituencyId(Long constituencyId)
	{
		List<UserBoothDetailsVO> resultList = new ArrayList<UserBoothDetailsVO>();
		try {
			long publicationId= boothDAO.getLatestPublicationDateIdForAConstituency(constituencyId);
			List<Object[]> boothsList = boothDAO.getAllTheBoothsDetailsByConstituencyId(constituencyId,IConstants.VOTER_DATA_PUBLICATION_ID);
			
			for(Object[] parms:boothsList)
			{
				UserBoothDetailsVO vo = new UserBoothDetailsVO();
				vo.setBoothId(parms[0] != null ? (Long)parms[0] : 0l);
				vo.setPartNo(parms[1] != null ? parms[1].toString() : "");			
				resultList.add(vo);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return resultList;
	}
	
	
	public ResultStatus saveSurveyUserTrackingDetails(UserLocationTrackingVo userLocationTrackingVo )
	{
		ResultStatus resultStatus = new ResultStatus();
		try
		{
			
			
			SurveyUserTracking surveyUserTracking = new SurveyUserTracking();
			Long userId=userLocationTrackingVo.getSurveyUserId();
			  
			
			Long id=surveyUserTrackingDAO.checkWhetherRecordExistingOrNot(userLocationTrackingVo.getUuid(), userLocationTrackingVo.getImeiNo(), new DateUtilService().getDateAndTime(userLocationTrackingVo.getInsertTime()));
			
			
			if(id!=null) {
				resultStatus.setResultCode(0);
				resultStatus.setMessage("Success");
				return resultStatus;
				
				
			}
				
			if(userId!=null && userId!=0 )
			surveyUserTracking.setSurveyUser(surveyUserDAO.get(userLocationTrackingVo.getSurveyUserId()));			
			
			surveyUserTracking.setDate(new DateUtilService().getDateAndTime(userLocationTrackingVo.getInsertTime()));
			surveyUserTracking.setLongitude(userLocationTrackingVo.getLongitude());
			surveyUserTracking.setLatitude(userLocationTrackingVo.getLatitude());
			surveyUserTracking.setUniqueUUID(userLocationTrackingVo.getUuid());
			surveyUserTracking.setInsertedTime(dateUtilService.getCurrentDateAndTime());
			surveyUserTracking.setUpdatedTime(dateUtilService.getCurrentDateAndTime());
			surveyUserTracking.setImeiNo(userLocationTrackingVo.getImeiNo());
			SurveyUserTracking result = surveyUserTrackingDAO.save(surveyUserTracking);
			surveyUserTrackingDAO.sessionFlush();
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
			throw new RuntimeException("Exception Because Of",e);
		}
		return resultStatus;
}
	
	
	public List<SurveyReportVO> getDayWiseReportByConstituencyIdAndUserType(
			Long constituencyId, String startDate, String endDate,
			Long userTypeId,List<Long> bothIds,List<Long> userIds)	{
		List<SurveyReportVO> resultList = new ArrayList<SurveyReportVO>();

		try
		{
			SimpleDateFormat originalFormat = new SimpleDateFormat("dd-MM-yyyy");
			SimpleDateFormat targetFormat = new SimpleDateFormat("yyyy-MM-dd" );
			Date strdate;
			Date enddate;
			strdate = originalFormat.parse(startDate);
			Date convertedstrdate = targetFormat.parse(targetFormat.format(strdate));			
			
			enddate = originalFormat.parse(endDate);
			Date convertedenddate = targetFormat.parse(targetFormat.format(enddate));
			List<Object[]> dayWiseReportDtls = null;
			
			if(userIds != null && userIds.size() > 0)
			{
				 dayWiseReportDtls = surveyDetailsInfoDAO
							.getDayWisereportDetailsByConstituencyIdAndUserIds(
									constituencyId, convertedstrdate, convertedenddate,
									userTypeId,bothIds,userIds);
			}
			else
			{
				 dayWiseReportDtls = surveyDetailsInfoDAO
							.getDayWisereportDetailsByConstituencyIdAndUserTypeId(
									constituencyId, convertedstrdate, convertedenddate,
									userTypeId,bothIds);
			}
			
			
			
			
			List<String> surveyDates = new ArrayList<String>();
			
			Set<Long> boothIds = new HashSet<Long>();
			
			for(Object[] boothDtls:dayWiseReportDtls)
				boothIds.add((Long)boothDtls[3]);
			Map<String,Long> totalCount = new HashMap<String, Long>();
			List<Long> booths = new ArrayList<Long>(boothIds);
			Map<Long,Long> totalVotersMap = new HashMap<Long, Long>();
			List<Object[]> totalVoters = boothPublicationVoterDAO.getTotalVoters(booths);
			if(totalVoters != null && totalVoters.size() > 0)
				for(Object[] params : totalVoters)
				{
					Long total = totalVotersMap.get((Long)params[0]);
					if(total == null)
					totalVotersMap.put((Long)params[0], (Long)params[2]);
					else
						totalVotersMap.put((Long)params[0], (Long)params[2] + total);	
				}
			
			for(Object[] boothDtls:dayWiseReportDtls)
			{
				Long count = totalCount.get(boothDtls[6].toString());
				if(count == null)
				{
				totalCount.put(boothDtls[6].toString(), (Long)boothDtls[0]);
				}
				else
				{
				 totalCount.put(boothDtls[6].toString(), (Long)boothDtls[0] + count);	
				}
				
				SurveyReportVO userBoothVO = getMatchedUserBoothVO(resultList,(Long)boothDtls[1],(Long)boothDtls[3]);

				if(userBoothVO == null)
				{
					SurveyReportVO reportVO = new SurveyReportVO();
					
					reportVO.setPartNo(boothDtls[4].toString());
					reportVO.setBoothId((Long)boothDtls[3]);
	                reportVO.setUserid((Long)boothDtls[1]);
	                reportVO.setUserName(boothDtls[2].toString()); 
	                reportVO.setTotalVoters(totalVotersMap.get((Long)boothDtls[3]) != null ? totalVotersMap.get((Long)boothDtls[3]) : 0l);
					
	                resultList.add(reportVO);
				}
                
                if(!surveyDates.contains(boothDtls[6].toString()))
                	surveyDates.add(boothDtls[6].toString());
				
			}
			
			
			for(SurveyReportVO report:resultList)
			{
			   for(String surveyDate:surveyDates)
			   {
				   SurveyReportVO surveyDateVO = new SurveyReportVO();
				   
                    surveyDateVO.setSurveyDate(surveyDate);
                    report.getSubList().add(surveyDateVO);
			   }
			}
			
			
			for(Object[] boothDtls:dayWiseReportDtls)
			{
				
				SurveyReportVO userBoothVO = getMatchedUserBoothVO(resultList,(Long)boothDtls[1],(Long)boothDtls[3]);
				SurveyReportVO surveyDateVO = getMatchedSurveyDateVO(userBoothVO.getSubList(), boothDtls[6].toString());
				
				surveyDateVO.setCount((Long)boothDtls[0]);
				
				surveyDateVO.setPercent(surveyDateVO.getCount() != null &&surveyDateVO.getCount() !=0 ? roundTo2DigitsFloatValue((float) surveyDateVO
						.getCount() * 100f / userBoothVO.getTotalVoters())
						: "0.00");
			}
			
			if(resultList != null && resultList.size() > 0)
			for( SurveyReportVO vo : resultList.get(0).getSubList())
		   {
			  vo.setTotal(totalCount.get(vo.getSurveyDate().toString()));
		   }
			
			
				for(SurveyReportVO userVO:resultList)
				{
			  		Long totalCnt = 0L;
				   for(SurveyReportVO dateVO:userVO.getSubList())
				   {
					   totalCnt = totalCnt + dateVO.getCount(); 
				   }
				   
					userVO.setTotalCollectedCount(totalCnt);
					userVO.setTotalCollectedPercent(totalCnt != null &&totalCnt !=0 ? roundTo2DigitsFloatValue((float) totalCnt * 100f / userVO.getTotalVoters())
								: "0.00");
				}
			
			
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		
		return resultList;
		
	}
	
	private SurveyReportVO getMatchedUserBoothVO(List<SurveyReportVO> resultList , Long userId,Long boothId)
	{
		for(SurveyReportVO userBoothVO:resultList)
			if(userBoothVO.getUserid().equals(userId) && userBoothVO.getBoothId().equals(boothId))
				return userBoothVO;
		return null;
	}





			
	public List<GenericVO> getSurveyConstituencyUsersList(Long constituencyId){
			
			 List<GenericVO> returnList = null;
			 try 
			 {
				List<Object[]> result = surveyUserConstituencyDAO.getSurveyConstituencyLeadersList(constituencyId);
				if(result != null && result.size() > 0)
				{
					List<Long> leaderIds = new ArrayList<Long>();
					returnList = new ArrayList<GenericVO>();
					
					for (Object[] param : result) {
						
						leaderIds.add((Long) param[0]);
						
					}
					
					List<Object[]> usersList = surveyUserRelationDAO.getAllUserForAssignedUsers(leaderIds);
					if(usersList != null && usersList.size() > 0)
					{
						for (Object[] param : usersList)
						{
							GenericVO vo = new GenericVO();
							vo.setId((Long) param[0]);
							vo.setName(param[1].toString());						
							returnList.add(vo);
						}
					}
				}
			 } 
			 catch (Exception e)
			 {
				 LOG.error("Exception raised in getSurveyConstituencyLeadersList() service in SurveyDataDetailsService", e);
			 }
			 return returnList;
		}

	

	public List<GenericVO> getSurveyConstituencyLeadersList(Long constituencyId){
			
			 List<GenericVO> returnList = null;
			 try 
			 {
				List<Object[]> result = surveyUserConstituencyDAO.getSurveyConstituencyLeadersList(constituencyId);
				if(result != null && result.size() > 0)
				{
					returnList = new ArrayList<GenericVO>();
					
					for (Object[] param : result)
					{
						GenericVO vo = new GenericVO();
						vo.setId((Long) param[0]);
						vo.setName(param[3].toString());						
						returnList.add(vo);
					}
				}
			 } 
			 catch (Exception e)
			 {
				 LOG.error("Exception raised in getSurveyConstituencyLeadersList() service in SurveyDataDetailsService", e);
			 }
			 return returnList;
		}

	
	
	public List<GenericVO> getSurveyConstituencyList(){
		
		 List<GenericVO> returnList = null;
		 try 
		 {
			List<Object[]> result = surveyUserConstituencyDAO.getSurveyConstituencyList();
			if(result != null && result.size() > 0)
			{
				returnList = new ArrayList<GenericVO>();
				fillGenericVO(result,returnList);
			}
		 } 
		 catch (Exception e)
		 {
			 LOG.error("Exception raised in getSurveyConstituencyList() service in SurveyDataDetailsService", e);
		 }
		 return returnList;
	}

	public GenericVO releaseLeadersWithUserandTabsList(Long leaderId) {

		List<GenericVO> returnList = null;
		GenericVO returnVO = new GenericVO();
		try
		{
			
			List<GenericVO> tabsList = new ArrayList<GenericVO>();
			
			List<Object[]> tabsInfo = surveyUserTabAssignDAO.getSurveyTabsBySurveyUserId(leaderId);
			
			if(tabsInfo != null && tabsInfo.size()>0){
				for (Object[] param : tabsInfo) {
					GenericVO genericVO = new GenericVO();
					genericVO.setId(Long.valueOf(param[0].toString()));
					genericVO.setName(param[1].toString());					
					tabsList.add(genericVO);
				}
			}

			List<Object[]> result = surveyUserRelationDAO.getUserForAssignedUsers(leaderId);
			List<Long> surveyUserIdsList = new ArrayList<Long>();
			if(result != null && result.size() > 0)
			{
				returnList = new ArrayList<GenericVO>();
				for (Object[] parms : result) 
				{
					surveyUserIdsList.add((Long) parms[0]);
				}
			}
			
			List<Object[]> assignedUsersInfo =null ;
			List<Long> assignedUseIds = new ArrayList<Long>();
			
			if(surveyUserIdsList != null && surveyUserIdsList.size()>0){
				assignedUsersInfo = surveyUserTabAssignDAO.getSurveyTabsBySurveyUserIdsList(surveyUserIdsList);
				if(assignedUsersInfo != null && assignedUsersInfo.size()>0){

					for (Object[] params : assignedUsersInfo) {
						GenericVO genericV1O = getGenericVOByName(tabsList,params[1].toString());
						if(genericV1O != null){						
							tabsList.remove(genericV1O);
						}
						assignedUseIds.add((Long)params[0]);
					}
				}
			}
			
			
			
			if(result != null && result.size() > 0)
			{
				returnList = new ArrayList<GenericVO>();
				for (Object[] parms : result) 
				{	
					if(!assignedUseIds.contains((Long)parms[0])){
						GenericVO genericVO = new GenericVO();
						genericVO.setId(parms[0] != null ? (Long)parms[0] : 0l);
						genericVO.setName(parms[1] != null ? parms[1].toString() : "");
						genericVO.setGenericVOList(tabsList);
						returnList.add(genericVO);	
					}									
				}				
			}
			
			returnVO.setGenericVOList(returnList);
		} 
		catch (Exception e) 
		{
			LOG.error("Exception raised in releaseLeadersWithUser service in SurveyDataDetailsService", e);
		}
		return returnVO;
	}
	
	public GenericVO getGenericVOByName(List<GenericVO> tabsList,String tabNo){
		GenericVO genericVO = null;
		try {
			
			if(tabsList != null && tabsList.size()>0){
				for (GenericVO genericVO1 : tabsList) {
					if(genericVO1.getName().toString().equalsIgnoreCase(tabNo)){
						return genericVO1;
					}
				}
			}
		} catch (Exception e) {
			LOG.error("Exception raised in getMactchedVO() service in SurveyDataDetailsService", e);
		}
		return genericVO;
	}
	/**
	 * This Service is used for saving the tab details for assigned survey user.
	 * @param tabsInfoList
	 * @return resultStatus
	 */
	public ResultStatus saveSurveyUserTabAssign(List<BasicVO> tabsInfoList)
	{
		LOG.info("Entered into saveSurveyUserTabAssign service in SurveyDataDetailsService");
		ResultStatus resultStatus = new ResultStatus();
		try
		{

			SurveyUserTabAssign result = null;
			
			if(tabsInfoList != null && tabsInfoList.size()>0){
				
				for (BasicVO basicVO : tabsInfoList) {
					
					DateUtilService date1 = new DateUtilService();
					SurveyUserTabAssign surveyUserTabAssign = new SurveyUserTabAssign();
				
					surveyUserTabAssign.setSurveyUser(surveyUserDAO.get(basicVO.getId()));
					surveyUserTabAssign.setTabNo(basicVO.getName());
					surveyUserTabAssign.setActiveStatus("Y");
					surveyUserTabAssign.setDate(date1.getCurrentDateAndTime());					
					surveyUserTabAssign.setInsertedTime(date1.getCurrentDateAndTime());
					surveyUserTabAssign.setUpdatedTime(date1.getCurrentDateAndTime());
										
					result = surveyUserTabAssignDAO.save(surveyUserTabAssign);
				}
				
			}
			

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
	
	public List<GenericVO> getExistedSurveyUsersByUserType(Long userTypeId)
	{
		List<GenericVO> returnList = null;
		try
		{
			List<Object[]> result = surveyUserRelationDAO.getExistedSurveyUsersByUserType(userTypeId);
			if(result != null && result.size() > 0)
			{
				returnList = new ArrayList<GenericVO>();
				fillGenericVO(result,returnList);
			}
		}
		catch (Exception e)
		{
			LOG.error("Exception raised in getExistedSurveyUsersByUserType service in SurveyDataDetailsService", e);
		}
		 return returnList;
	}
	
	public List<GenericVO> getExistedConstituenciesDetailsByUserId(Long userId)
	{
		List<GenericVO> returnList = null;
		try
		{
			List<Object[]> result = surveyUserConstituencyDAO.getExistedConstituenciesDetailsByUserId(userId);
			if(result != null && result.size() > 0)
			{
				returnList = new ArrayList<GenericVO>();
				fillGenericVO(result,returnList);
			}
		}
		catch (Exception e)
		{
			LOG.error("Exception raised in getExistedSurveyUsersByUserType service in SurveyDataDetailsService", e);
		}
		 return returnList;
	}
	
	
	public List<SurveyReportVO> getAllAssignedConstituenciesUsers(Long userTypeId)
	{
		List<SurveyReportVO> resultList = new ArrayList<SurveyReportVO>();
		try {
			
			List<Object[]> totalUsersList = surveyUserDAO.getSurveyUsersByUserType(userTypeId);
			
			List<Long> usersList = surveyUserConstituencyDAO.getAlreadyAssignedUsers();
			
			for(Object[] parms:totalUsersList)
			{
				
				SurveyReportVO vo = new SurveyReportVO();
				if(!usersList.contains((Long)parms[0])){
					vo.setUserid((Long)parms[0]);
					vo.setUserName(parms[1].toString());
					resultList.add(vo);
				}
					
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return resultList;
	}
	
	public List<SelectOptionVO> getAllAssignedConstituency(Long userTypeId)
	{
		List<SelectOptionVO> resultList = new ArrayList<SelectOptionVO>();
		try {
			List<Object[]> totalConstituenciesList = constituencyDAO.getAllAssemblyConstituenciesByStateId(1L);
			//List<Long> constituenciesList = surveyUserConstituencyDAO.getAlreadyAssignedConstituencies(userTypeId);
			
			for(Object[] parms:totalConstituenciesList)
			{
				SelectOptionVO vo = new SelectOptionVO();
				//if(!constituenciesList.contains((Long)parms[0])){
				vo.setId((Long)parms[0]);
				vo.setName(parms[1].toString());
				resultList.add(vo);
				//}
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return resultList;
	}
	
	public ResultStatus assignConstituencyForAUser(Long userId,Long constituencyId)
	{
		ResultStatus resultStatus = new ResultStatus();
		try
		{
			SurveyUserConstituency surveyUserConstituency = new SurveyUserConstituency();
			
			surveyUserConstituency.setSurveyUser(surveyUserDAO.get(userId));
			surveyUserConstituency.setConstituency(constituencyDAO.get(constituencyId));
			surveyUserConstituency.setActiveStatus("Y");
		
			SurveyUserConstituency result = surveyUserConstituencyDAO.save(surveyUserConstituency);
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
			LOG.error("Exception raised in assignConstituencyForAUser service in SurveyDataDetailsService", e);
			resultStatus.setResultCode(3);
			resultStatus.setMessage("Exception");
		}
		return resultStatus;
	}
	
	
	public ConstituencyDetailReportVO getCosntituencyWiseReportByContiId(Long constituencyId){
		ConstituencyDetailReportVO reportVO = new ConstituencyDetailReportVO();
		try {
			

			// List<Object[]> constituencyCollectdDetls = surveyDetailsInfoDAO.getsurveyDetailsInfoByConstituencyId(constituencyId,1L);
			 
			// List<Object[]> constituencyVerifiedDetls = surveyDetailsInfoDAO.getsurveyDetailsInfoByConstituencyId(constituencyId,4L);
			 
			 List<Object[]> constituencyBoothInfo = surveyDetailsInfoDAO.getBoothDetailsByConstituencyId(constituencyId);
			 
			// Long totalVoters = voterInfoDAO.getTotalVotersForSelectdLevel(1L, constituencyId, 10L, constituencyId); //(report level id,reportlevelValue,publicationId,constId);
			
			 /* if(constituencyCollectdDetls != null && constituencyCollectdDetls.size()>0){
				
				 reportVO.setConstituencyTotalVoters(totalVoters);
				 
				 for (Object[] param : constituencyCollectdDetls) {
					
					reportVO.setTotalColelctedVoters(param[0] != null ? (Long)param[0]:0L);

					Long casteCollectedCount = (param[1] != null ? (Long)param[1]:0L) + (param[2] != null ? (Long)param[2]:0L);
					reportVO.setCasteCollectedCount(casteCollectedCount);
					
					Long hamletCollectedCount =  (param[3] != null ? (Long)param[3]:0L) + (param[4] != null ? (Long)param[4]:0L);
					reportVO.setHamletCollectedCount(hamletCollectedCount);
					
					reportVO.setCadreCollectedCount(param[5] != null ? (Long)param[5]:0L);
					reportVO.setInfluencePeopleCollectedCount(param[6] != null ? (Long)param[6]:0L);
					reportVO.setMobileNoCollectedCount(param[7] != null ? (Long)param[7]:0L);
					
					reportVO.setNotCollectedVoters(totalVoters - (Long) param[0]);
					}
				 
				 
				 }
				 
				 if(constituencyCollectdDetls != null && constituencyCollectdDetls.size()>0){
					 
					 for (Object[] params : constituencyVerifiedDetls) {
						 
							//reportVO.setTotalVerifiedVoters(params[0] != null ? (Long)params[0]:0L);

							Long casteVerifiedCount = (params[1] != null ? (Long)params[1]:0L) + (params[2] != null ? (Long)params[2]:0L);
							reportVO.setCasteVerifiedCount(casteVerifiedCount);
							
							Long hamletVerifiedCount = (params[3] != null ? (Long)params[3]:0L) + (params[4] != null ? (Long)params[4]:0L);
							reportVO.setHamletVerifiedCount(hamletVerifiedCount);
							
							reportVO.setCadreVerifiedCount(params[5] != null ? (Long)params[5]:0L);
							reportVO.setInfluencePeopleVerifiedCount(params[6] != null ? (Long)params[6]:0L);
							reportVO.setMobileNoVerifiedCount(params[7] != null ? (Long)params[7]:0L);
							
							reportVO.setNotVerifiedVoters(totalVoters - (Long) params[0]);
							
					 }
				 }
				 */
			 
			 Long totalVoters =  boothPublicationVoterDAO.getTotalVotersForConstituency(constituencyId);
			 reportVO.setConstituencyTotalVoters(totalVoters);
			 
			 Long survyeVoterCount = surveyDetailsInfoDAO.getTotalSurveyVotersByconstituency(constituencyId,1L,"constituencyWise");
			 
			 reportVO.setTotalColelctedVoters(survyeVoterCount);
			 reportVO.setCasteCollectedCount(surveyDetailsInfoDAO.getCasteCountByBoothByConstituency(constituencyId,1L,"constituencyWise"));
			 reportVO.setHamletCollectedCount(surveyDetailsInfoDAO.getHamletCountByBoothByConstituency(constituencyId,1L,"constituencyWise"));				
			 reportVO.setCadreCollectedCount(surveyDetailsInfoDAO.getCadreCountByBoothByConstituency(constituencyId,1L,"constituencyWise"));
			 reportVO.setInfluencePeopleCollectedCount(surveyDetailsInfoDAO.getInfluencingPeopleCountByBoothByConstituency(constituencyId,1L,"constituencyWise"));
			 reportVO.setMobileNoCollectedCount(surveyDetailsInfoDAO.getMobileCountByBoothByConstituency(constituencyId,1L,"constituencyWise"));
			
			 reportVO.setNotCollectedVoters(totalVoters - survyeVoterCount);
				
			 
			 
			 
			 Long verifiedCount = surveyDetailsInfoDAO.getTotalSurveyVotersByconstituency(constituencyId,4L,"constituencyWise");
			 reportVO.setTotalVerifiedVoters(verifiedCount);			 
			 reportVO.setCasteVerifiedCount(surveyDetailsInfoDAO.getCasteCountByBoothByConstituency(constituencyId,4L,"constituencyWise"));				
			 reportVO.setHamletVerifiedCount(surveyDetailsInfoDAO.getHamletCountByBoothByConstituency(constituencyId,4L,"constituencyWise"));			
			 reportVO.setCadreVerifiedCount(surveyDetailsInfoDAO.getCadreCountByBoothByConstituency(constituencyId,4L,"constituencyWise"));
			 reportVO.setInfluencePeopleVerifiedCount(surveyDetailsInfoDAO.getInfluencingPeopleCountByBoothByConstituency(constituencyId,4L,"constituencyWise"));
			 reportVO.setMobileNoVerifiedCount(surveyDetailsInfoDAO.getMobileCountByBoothByConstituency(constituencyId,4L,"constituencyWise"));
			
			 reportVO.setNotVerifiedVoters(totalVoters - verifiedCount);
			
				 List<GenericVO> boothsList = new ArrayList<GenericVO>();
				 if(constituencyBoothInfo != null && constituencyBoothInfo.size()>0){
					 
					 for (Object[] booth : constituencyBoothInfo) {
						 GenericVO vo = new GenericVO();
						 vo.setId((Long) booth[0]);
						 vo.setName(booth[1].toString());
						 boothsList.add(vo);					 
					 }
				 }
			 
				if(boothsList != null && boothsList.size()>0){ 
						reportVO.setBoothsList(boothsList);
				}
			 
		} catch (Exception e) {
			LOG.error("Exception raised in getCosntituencyWiseReportByContiId() service in SurveyDataDetailsService", e);
			e.printStackTrace();
			reportVO = null;
		}
		return reportVO;
	}
	
	public ConstituencyDetailReportVO getBoothWiseDetails(Long boothId,Long constituencyId){
		ConstituencyDetailReportVO reportVO = new ConstituencyDetailReportVO();
		try {
			
			
			 Long totalVoters =  boothPublicationVoterDAO.getTotalVotersForBoothId(boothId);
			 reportVO.setConstituencyTotalVoters(totalVoters);
			 
			 Long survyeVoterCount = surveyDetailsInfoDAO.getTotalSurveyVotersByconstituency(boothId,1L,"boothWise");
			 
			 reportVO.setTotalColelctedVoters(survyeVoterCount);
			 reportVO.setCasteCollectedCount(surveyDetailsInfoDAO.getCasteCountByBoothByConstituency(boothId,1L,"boothWise"));
			 reportVO.setHamletCollectedCount(surveyDetailsInfoDAO.getHamletCountByBoothByConstituency(boothId,1L,"boothWise"));				
			 reportVO.setCadreCollectedCount(surveyDetailsInfoDAO.getCadreCountByBoothByConstituency(boothId,1L,"boothWise"));
			 reportVO.setInfluencePeopleCollectedCount(surveyDetailsInfoDAO.getInfluencingPeopleCountByBoothByConstituency(boothId,1L,"boothWise"));
			 reportVO.setMobileNoCollectedCount(surveyDetailsInfoDAO.getMobileCountByBoothByConstituency(boothId,1L,"boothWise"));
			
			 reportVO.setNotCollectedVoters(totalVoters - survyeVoterCount);
				
			 
			 
			 
			 Long verifiedCount = surveyDetailsInfoDAO.getTotalSurveyVotersByconstituency(boothId,4L,"boothWise");
			 reportVO.setTotalVerifiedVoters(verifiedCount);			 
			 reportVO.setCasteVerifiedCount(surveyDetailsInfoDAO.getCasteCountByBoothByConstituency(boothId,4L,"boothWise"));				
			 reportVO.setHamletVerifiedCount(surveyDetailsInfoDAO.getHamletCountByBoothByConstituency(boothId,4L,"boothWise"));			
			 reportVO.setCadreVerifiedCount(surveyDetailsInfoDAO.getCadreCountByBoothByConstituency(boothId,4L,"boothWise"));
			 reportVO.setInfluencePeopleVerifiedCount(surveyDetailsInfoDAO.getInfluencingPeopleCountByBoothByConstituency(boothId,4L,"boothWise"));
			 reportVO.setMobileNoVerifiedCount(surveyDetailsInfoDAO.getMobileCountByBoothByConstituency(boothId,4L,"boothWise"));
			
			 reportVO.setNotVerifiedVoters(totalVoters - verifiedCount);
			
			 
			/*
			 List<Object[]> constituencyCollectdDetls = surveyDetailsInfoDAO.getsurveyDetailsInfoByboothId(boothId,1L);
			 List<Object[]> constituencyVerifiedDetls = surveyDetailsInfoDAO.getsurveyDetailsInfoByboothId(boothId,4L);
			 
			// Long totalVoters = voterInfoDAO.getTotalVotersForSelectdLevel(4L, boothId, 10L, constituencyId); //(report level id,reportlevelValue,publicationId,constId);
			 List<Long> boothIds = new ArrayList<Long>();
			 boothIds.add(boothId);
			 List<Object[]> result = boothPublicationVoterDAO.getBoothWiseVoterDetails(boothIds);
			 Long totalVoters = (Long) result.get(0)[1];
			 if(constituencyCollectdDetls != null && constituencyCollectdDetls.size()>0){
					
				 reportVO.setConstituencyTotalVoters(totalVoters);
				 
				 for (Object[] param : constituencyCollectdDetls) {
					
					//reportVO.setTotalColelctedVoters(param[0] != null ? (Long)param[0]:0L);

					Long casteCollectedCount = (param[1] != null ? (Long)param[1]:0L) + (param[2] != null ? (Long)param[2]:0L);
					reportVO.setCasteCollectedCount(casteCollectedCount);
					
					Long hamletCollectedCount =  (param[3] != null ? (Long)param[3]:0L) + (param[4] != null ? (Long)param[4]:0L);
					reportVO.setHamletCollectedCount(hamletCollectedCount);
					
					reportVO.setCadreCollectedCount(param[5] != null ? (Long)param[5]:0L);
					reportVO.setInfluencePeopleCollectedCount(param[6] != null ? (Long)param[6]:0L);
					reportVO.setMobileNoCollectedCount(param[7] != null ? (Long)param[7]:0L);
					
					reportVO.setNotCollectedVoters(totalVoters - (param[0]!= null?(Long) param[0]:0L));
					reportVO.setTotalColelctedVoters((param[8]!= null?(Long) param[8]:0L)); // local area count
					}
				 
				 
				 }
			 
				 if(constituencyCollectdDetls != null && constituencyCollectdDetls.size()>0){
					 
					 for (Object[] params : constituencyVerifiedDetls) {
						 
						//	reportVO.setTotalVerifiedVoters(params[0] != null ? (Long)params[0]:0L);

							Long casteVerifiedCount = (params[1] != null ? (Long)params[1]:0L) + (params[2] != null ? (Long)params[2]:0L);
							reportVO.setCasteVerifiedCount(casteVerifiedCount);
							
							Long hamletVerifiedCount = (params[3] != null ? (Long)params[3]:0L) + (params[4] != null ? (Long)params[4]:0L);
							reportVO.setHamletVerifiedCount(hamletVerifiedCount);
							
							reportVO.setCadreVerifiedCount(params[5] != null ? (Long)params[5]:0L);
							reportVO.setInfluencePeopleVerifiedCount(params[6] != null ? (Long)params[6]:0L);
							reportVO.setMobileNoVerifiedCount(params[7] != null ? (Long)params[7]:0L);
							
							reportVO.setNotVerifiedVoters(totalVoters - (params[0]!= null?(Long) params[0]:0L));
							reportVO.setTotalVerifiedVoters((params[8]!= null?(Long) params[8]:0L)); // local area count
					 }
				 }
				 
				*/
		} catch (Exception e) {
			LOG.error("Exception raised in getBoothWiseDetails() service in SurveyDataDetailsService", e);
			e.printStackTrace();
			reportVO = null;
		}
		return reportVO;
	}
	
	
	public List<SelectOptionVO> getLatLongForSurveyUsersByConstituency(Long constituencyId,Date date,List<Long> userIds)
	{
		List<SelectOptionVO> returnList = null;
		try
		{
			List<Object[]> result = null ;
			if(userIds != null && userIds.size() > 0)
			{
				 result = surveyDetailsInfoDAO.getLatLongForSurveyUsersByConstituencyByUser(constituencyId, date,userIds);
			}
			else
			{
				 result = surveyDetailsInfoDAO.getLatLongForSurveyUsersByConstituency(constituencyId, date);
			}
			
			if(result != null && result.size() > 0)
			{
				returnList = new ArrayList<SelectOptionVO>();
				Map<Long,SelectOptionVO> resultMap = new HashMap<Long, SelectOptionVO>();
				for (Object[] parms : result)
				{
					if(resultMap.get((Long)parms[7]) == null)
					{
						SelectOptionVO VO = new SelectOptionVO();
						VO.setName(parms[0] != null ? parms[0].toString() : null);//user Name
						VO.setPartno(parms[1] != null ? parms[1].toString() : null);// part no
						VO.setValue(parms[2] != null ? parms[2].toString() : null);// Mandal
						VO.setUrl(parms[3] != null ? parms[3].toString() : null);// Panchayat
						VO.setLocation(parms[5] != null ? parms[5].toString() : null);// Location
						VO.setVillageCovered(parms[4] != null ? parms[4].toString() : null);// Location Covered
						VO.setType(parms[6] != null ? parms[6].toString() : null);// Location Covered
						VO.setId((Long)parms[7]);//userId
						VO.setMandalName(parms[11] != null ? parms[11].toString() : null);
						VO.setOrderId((Long)parms[10]);//boothId
						if( parms[8] != null &&  parms[9] != null)
						{
							VO.setLatitude(parms[8] != null ? parms[8].toString() : null);// Location Covered
							VO.setLongititude(parms[9] != null ? parms[9].toString() : null);// Location Covered
						}
						
						returnList.add(VO);
						resultMap.put((Long)parms[7], VO);
					}
					
				}
			}
		} 
		catch (Exception e)
		{
			LOG.error("Exception raised in getLatLongForSurveyUsersByConstituency service in SurveyDataDetailsService", e);
			e.printStackTrace();
		}
		return returnList;
	}
	
	public List<Long> getDataCollectedCount(Long userId,Long boothId)
	{
		List<Long> returnList = new ArrayList<Long>();
		try
		{
			List<Long> boothIds = new ArrayList<Long>();
			boothIds.add(boothId);
			Long voterCount = null;
			List<Object[]> result =  boothPublicationVoterDAO.getBoothWiseVoterDetails(boothIds);
			if(result != null && result.size() > 0)
			{
				 voterCount     =  (Long) result.get(0)[1];
				
			}
			Long casteCount     =  surveyDetailsInfoDAO.getCasteCountByBooth(userId, boothId);
			Long hamletCount    =  surveyDetailsInfoDAO.getHamletCountByBooth(userId, boothId);
			Long localAreaCount =  surveyDetailsInfoDAO.getLocalAreaCountByBooth(userId, boothId);
			Long cadreCount     =  surveyDetailsInfoDAO.getCadreCountByBooth(userId, boothId);
			Long influCount     =  surveyDetailsInfoDAO.getInfluencingPeopleCountByBooth(userId, boothId);
			returnList.add(voterCount);
			returnList.add(casteCount);
			returnList.add(hamletCount);
			returnList.add(localAreaCount);
			returnList.add(cadreCount);
			returnList.add(influCount);
		} 
		catch (Exception e)
		{
			LOG.error("Exception raised in getLatLongForSurveyUsersByConstituency service in SurveyDataDetailsService", e);
			e.printStackTrace();
		}
		return returnList;
	}
	
	
	
	@SuppressWarnings("unchecked")
	public List<SelectOptionVO> getConstituencyListByDistrictId(Long districtId){
		
		List<SelectOptionVO> result = new ArrayList<SelectOptionVO>();
		try {
			List<Object[]> constituencies = surveyDetailsInfoDAO.findConstituenciesByDistrictId(districtId);			
			
			for(Object[] constituency : constituencies){
				SelectOptionVO vo = new SelectOptionVO();
				vo.setId((Long)constituency[0]);
				vo.setName(constituency[1].toString());
				result.add(vo);
			}
			Collections.sort(result);
			
		} catch (Exception e) {
			result = null;
			LOG.error("Exception raised in getConstituencyListByDistrictId() service in SurveyDataDetailsService", e);
			e.printStackTrace();
		}
		
		
		return result;
	}

	
	@SuppressWarnings("unchecked")
	public List<SelectOptionVO> getAssignedBoothDetailsByuserId(Long constituencyId,Long surveyUserId){
		List<SelectOptionVO> result = new ArrayList<SelectOptionVO>();
		try {
			List<Object[]> booths = surveyUserBoothAssignDAO.getAllTheAssignedBoothsByConstituencyIdAndUserId(constituencyId,surveyUserId);		

			for(Object[] booth : booths){
				result.add(new SelectOptionVO(booth[1] != null ? (Long) booth[1]:0L,WordUtils.capitalize(booth[2] != null ? booth[2].toString().toLowerCase():"")));
			}
		} catch (Exception e) {
			result = null;
			LOG.error("Exception raised in getAssignedBoothDetailsByuserId() service in SurveyDataDetailsService", e);
			e.printStackTrace();
		}		
		return result;
	}
	
	

	public List<SelectOptionVO> getSurveyStartedConstituencyList(){
		List<SelectOptionVO> result = new ArrayList<SelectOptionVO>();
		try {
			List<Object[]> booths = surveyDetailsInfoDAO.getSurveyStartedConstituencyInfo();	

			for(Object[] booth : booths){
				result.add(new SelectOptionVO(booth[0] != null ? (Long) booth[0]:0L,WordUtils.capitalize(booth[1] != null ? booth[1].toString().toLowerCase():"")));
			}
		} catch (Exception e) {
			result = null;
			LOG.error("Exception raised in getAssignedBoothDetailsByuserId() service in SurveyDataDetailsService", e);
			e.printStackTrace();
		}		
		return result;
	}
	/*
	public List<SurveyReportVO> getSurveyVotersList(Long constituencyId, Long boothId,Long leaderId,String searchDate){
		List<SurveyReportVO> retultList = new ArrayList<SurveyReportVO>();
		try {
			
			SurveyReportVO finalVO = new SurveyReportVO();
			SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy");
			//SimpleDateFormat finalFormat = new SimpleDateFormat("yyyy-MM-dd");
			Date date = format.parse(searchDate);
			//List<Object[]> usersList = surveyUserRelationDAO.getUsersByConstituencyAndLeader(leaderId, constituencyId);
			
			//Set<Long> assignedUserIds = new HashSet<Long>();
			
			//if(usersList != null && usersList.size()>0){
				//for (Object[] user : usersList) {
				//	assignedUserIds.add((Long) user[0]);
				//}
				
				List<Long> ids = new ArrayList<Long>();
				ids.add(leaderId);
				List<Object[]> votersLsit = surveyDetailsInfoDAO.getVoterDetailsByBoothId(boothId,ids,date);

				List<Object[]> verifiedList = surveyCallStatusDAO.getSurveyCallDtalsByboothId(boothId);
				Map<Long,String> mobileMatched = new HashMap<Long,String>();
				Map<Long,String> casteMatched = new HashMap<Long,String>();
				Map<Long,String> newCasteMatched = new HashMap<Long,String>();
				
				if(verifiedList != null && verifiedList.size()>0){
					for (Object[] param : verifiedList) {
						if(param[1] != null)
						{
							if(!param[1].toString().equalsIgnoreCase("N")){
								mobileMatched.put((Long)param[0], "Y");
							}
							else if(param[1].toString().equalsIgnoreCase("N")){
								mobileMatched.put((Long)param[0], "N");
							}
							else{								
								mobileMatched.put((Long)param[0], "Not Mapped");							
							}
						}
						
						if(param[2] != null)
						{
							if(!param[2].toString().equalsIgnoreCase("N")){
								casteMatched.put((Long)param[0], "Y");
							}
							else if(param[2].toString().equalsIgnoreCase("N")){
								casteMatched.put((Long)param[0], "N");
							}
							else{
								casteMatched.put((Long)param[0], "Not Mapped");
							}
						}
						
						if(param[3] != null)
						{
								newCasteMatched.put((Long)param[0], param[3].toString());
						}
						
					}
				}
				if(votersLsit != null && votersLsit.size()>0){
					List<SurveyReportVO> resultList = new ArrayList<SurveyReportVO>();
					for (Object[] voter : votersLsit) {
						
						SurveyReportVO reportVO = new SurveyReportVO();
						
						//reportVO.setUserName(voter[0] != null ? voter[0].toString():"");
						reportVO.setVoterIDCardNo(voter[1] != null ? voter[1].toString():"");
						if(voter[2] != null && !voter[2].toString().equalsIgnoreCase("null"))
						{
							reportVO.setMobileNo(voter[2] != null ? voter[2].toString():"");
						}
						
						reportVO.setCaste(voter[3] != null ? voter[3].toString(): voter[4] != null ? voter[4].toString():"");
						reportVO.setHamletName(voter[5] != null ? voter[5].toString():voter[6] != null ? voter[6].toString():"");
						reportVO.setLocalArea(voter[7] != null ? voter[7].toString():"");
						reportVO.setUserid(voter[8] != null ?(Long) voter[8]:0L);
						reportVO.setVoterId(voter[9] != null ?(Long) voter[9]:0L);
						reportVO.setCadre(voter[10] != null ? voter[10].toString():"");
						reportVO.setInfluencePeople(voter[11] != null ? voter[11].toString():"");
						reportVO.setUserName(voter[12] != null ? voter[12].toString():"");  // votername 
						reportVO.setPartNo(voter[13] != null ? voter[13].toString():"");    // H.No
						reportVO.setVoterName(voter[14] != null ? voter[14].toString():""); // relativeName
						reportVO.setSerailNo(voter[15] != null ?(Long) voter[15]:0L);
						if(voter[9] != null)
								{
									String casteMatchd = casteMatched.get((Long) voter[9]);
									String mobilMatchd = mobileMatched.get((Long) voter[9]);
		
								if(casteMatchd != null && casteMatchd.equalsIgnoreCase("Y")){
									reportVO.setCasteMatchedCount(1L);
								}else if(casteMatchd != null && casteMatchd.equalsIgnoreCase("N")){
									reportVO.setCasteMatchedCount(2L);
								}else{							
									reportVO.setCasteMatchedCount(0L);
								}
								
								
								if(mobilMatchd != null && mobilMatchd.equalsIgnoreCase("Y")){
									reportVO.setMobileMatchedCount(1L);
								}else if(mobilMatchd != null && mobilMatchd.equalsIgnoreCase("N")){
									reportVO.setMobileMatchedCount(2L);
								}else{							
									reportVO.setMobileMatchedCount(0L);
								}
								
								
								if(newCasteMatched.get(voter[9] != null ?(Long) voter[9]:0L) != null){
									reportVO.setCasteId(Long.valueOf(newCasteMatched.get((Long) voter[9])));
								}
							}
						
						resultList.add(reportVO);
						
					}
					
					if(resultList != null && resultList.size()>0){
						finalVO.setSubList(resultList);
						
					}
				}	
				
			//}
					
			List<GenericVO> casteListOfSamples = new ArrayList<GenericVO>();
			List<Object[]> casteInfo = surveyDetailsInfoDAO.getCasteWiseCountInBooth(boothId);	
			if(casteInfo != null && casteInfo.size()>0){
				for (Object[] caste : casteInfo) {
					GenericVO vo = new  GenericVO();
					vo.setId(caste[0] != null ? (Long) caste[0]:0L);
					vo.setName(caste[1] != null ? caste[1].toString():"");
					vo.setCount(caste[2] != null ? (Long) caste[2]:0L);
					
					casteListOfSamples.add(vo);
					
				}
			}
			
			finalVO.setCount(surveyDetailsInfoDAO.getTotalVotersinBooth(boothId));
			
		
			if(casteListOfSamples != null && casteListOfSamples.size()>0){
				
				Collections.sort(casteListOfSamples, new Comparator<GenericVO>() {

					public int compare(GenericVO o1, GenericVO o2) {
						
						return (int) (o2.getCount() - o1.getCount());
					}
				});
				
				finalVO.setGenericVOList(casteListOfSamples);
			}
			
			retultList.add(finalVO);
			
			
			SurveyReportVO allCastesVO = new SurveyReportVO();
			
			List<Object[]> castesList = casteStateDAO.getAllCasteDetailsForVoters(1L); // for AP state
			List<GenericVO> stateCasteList = new ArrayList<GenericVO>();
			
			if(castesList != null && castesList.size()>0){
				for (Object[] cast : castesList) {
					GenericVO vo = new  GenericVO();
					vo.setId(cast[0] != null ? (Long) cast[0]:0L);
					vo.setName(cast[1] != null ? cast[1].toString():"");
					
					stateCasteList.add(vo);
				}
				
				allCastesVO.setGenericVOList(stateCasteList);
			}
			
			retultList.add(allCastesVO);
			
		} catch (Exception e) {
			retultList = null;
			LOG.error("Exception raised in getSurveyVotersList() service in SurveyDataDetailsService", e);
			e.printStackTrace();
		}		
		return retultList;
	}
	
	*/
	
	public void fillDcWmMap(Map<Long,GenericVO> dcWmMap , List<VerificationCompVO> resultList)
	{
		for (VerificationCompVO verificationCompVO : resultList) 
		{
			GenericVO VO = new GenericVO();
			VO.setDesc(verificationCompVO.getDcCaste());//DC CASTE
			VO.setName(verificationCompVO.getWmCaste());//DV CASTE
			dcWmMap.put(verificationCompVO.getVoterId(), VO);
		}
	}
	 public List<SurveyReportVO> getSurveyVotersList(Long constituencyId, Long boothId,Long surveyUserId,String searchDate,Long userType){
		List<SurveyReportVO> retultList = new ArrayList<SurveyReportVO>();
		try {
			
			SurveyReportVO finalVO = new SurveyReportVO();
			SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy");
			//SimpleDateFormat finalFormat = new SimpleDateFormat("yyyy-MM-dd");
			Date date = format.parse(searchDate);
			//List<Object[]> usersList = surveyUserRelationDAO.getUsersByConstituencyAndLeader(leaderId, constituencyId);
			
			//Set<Long> assignedUserIds = new HashSet<Long>();
			
			//if(usersList != null && usersList.size()>0){
				//for (Object[] user : usersList) {
				//	assignedUserIds.add((Long) user[0]);
				//}
				
				List<Long> surveyUserids = new ArrayList<Long>();
				surveyUserids.add(surveyUserId);
				//List<Object[]> votersLsit = surveyDetailsInfoDAO.getVoterDetailsByBoothId(boothId,ids,date);
				
				List<Object[]> votersLsit = surveyDetailsInfoDAO.getVotersDetailsByBoothId(boothId,surveyUserids,date);
				List<Object[]> verifiedList = null;
				Map<Long,GenericVO> dcWmMap = null;
				if(userType.longValue() == 1l)
				{
					 verifiedList = surveyCallStatusDAO.getSurveyCallDtalsByboothId(boothId,surveyUserId);
				}
				else
				{
					List<VerificationCompVO> dcWmDetails = surveyDetailsService.checkForWebMonitorData(boothId);
					if(dcWmDetails != null && dcWmDetails.size() > 0)
					{
						dcWmMap = new HashMap<Long, GenericVO>();
						for (VerificationCompVO verificationCompVO : dcWmDetails)
						{
							List<VerificationCompVO> matchedList = verificationCompVO.getMatchedList();
							if(matchedList != null && matchedList.size() > 0)
							{
								fillDcWmMap(dcWmMap,matchedList);
							}
							List<VerificationCompVO> unMatchedLiat = verificationCompVO.getUnMatchedList();
							if(unMatchedLiat != null && unMatchedLiat.size() > 0)
							{
								fillDcWmMap(dcWmMap,unMatchedLiat);
							}
							List<VerificationCompVO> notVerifiedList = verificationCompVO.getNotVerifiedList();
							if(notVerifiedList != null && notVerifiedList.size() > 0)
							{
								fillDcWmMap(dcWmMap,notVerifiedList);
							}
						}
					}
					verifiedList = surveyCallStatusDAO.getDvSurveyCallDtalsByboothId(boothId,surveyUserId);
				}
				
				
				Map<Long,String> mobileMatched = new HashMap<Long,String>();
				Map<Long,String> casteMatched = new HashMap<Long,String>();
				Map<Long,String> newCasteMatched = new HashMap<Long,String>();
				
				if(verifiedList != null && verifiedList.size()>0){
					for (Object[] param : verifiedList) {
						if(param[1] != null)
						{
							if(!param[1].toString().equalsIgnoreCase("N")){
								mobileMatched.put((Long)param[0], "Y");
							}
							else if(param[1].toString().equalsIgnoreCase("N")){
								mobileMatched.put((Long)param[0], "N");
							}
							else{								
								mobileMatched.put((Long)param[0], "Not Mapped");							
							}
						}
						
						if(param[2] != null)
						{
							if(!param[2].toString().equalsIgnoreCase("N")){
								casteMatched.put((Long)param[0], "Y");
							}
							else if(param[2].toString().equalsIgnoreCase("N")){
								casteMatched.put((Long)param[0], "N");
							}
							else{
								casteMatched.put((Long)param[0], "Not Mapped");
							}
						}
						
						if(param[3] != null)
						{
								newCasteMatched.put((Long)param[0], param[3].toString());
						}
						
					}
				}
				
				if(votersLsit != null && votersLsit.size()>0){
					List<SurveyReportVO> resultList = new ArrayList<SurveyReportVO>();
					for (Object[] voterInfo : votersLsit) {
						
						SurveyReportVO reportVO = new SurveyReportVO();
						SurveyDetailsInfo surveyDetailsInfo = (SurveyDetailsInfo) voterInfo[0]; 
						
						reportVO.setVoterIDCardNo(surveyDetailsInfo.getVoter().getVoterIDCardNo());
						reportVO.setMobileNo(surveyDetailsInfo.getMobileNumber() != null ? surveyDetailsInfo.getMobileNumber():"");
						
						if(surveyDetailsInfo.getCaste() != null){
							reportVO.setCaste(surveyDetailsInfo.getCaste().getCaste().getCasteName());
						}
						else{
							reportVO.setCaste(surveyDetailsInfo.getCasteName() != null ? surveyDetailsInfo.getCasteName() :"");
						}
						
						if(surveyDetailsInfo.getHamlet() != null){
							reportVO.setHamletName(surveyDetailsInfo.getHamlet().getHamletName());
						}
						else{
							reportVO.setHamletName(surveyDetailsInfo.getHamletName() != null ? surveyDetailsInfo.getHamletName() :"");
						}
						
						reportVO.setLocalArea(surveyDetailsInfo.getLocalArea() != null ? surveyDetailsInfo.getLocalArea():"");
						reportVO.setUserid(surveyDetailsInfo.getSurveyUser().getSurveyUserId() != null ? surveyDetailsInfo.getSurveyUser().getSurveyUserId():0L);
						reportVO.setVoterId(surveyDetailsInfo.getVoter() != null ? surveyDetailsInfo.getVoter().getVoterId():0L);
						reportVO.setCadre(surveyDetailsInfo.getIsCadre() != null ? surveyDetailsInfo.getIsCadre():"");
						reportVO.setInfluencePeople(surveyDetailsInfo.getIsInfluencingPeople() != null ? surveyDetailsInfo.getIsInfluencingPeople() :"");
						reportVO.setUserName(surveyDetailsInfo.getVoter() != null ? surveyDetailsInfo.getVoter().getName():"");
						reportVO.setPartNo(surveyDetailsInfo.getVoter() != null ? surveyDetailsInfo.getVoter().getHouseNo():"");
						reportVO.setVoterName(surveyDetailsInfo.getVoter() != null ? surveyDetailsInfo.getVoter().getRelativeName():"");
						reportVO.setSerailNo(voterInfo[1] != null ?(Long) voterInfo[1]:0L);
						
						if(surveyDetailsInfo.getVoter() != null)
								{
									String casteMatchd = casteMatched.get(surveyDetailsInfo.getVoter().getVoterId());
									String mobilMatchd = mobileMatched.get(surveyDetailsInfo.getVoter().getVoterId());
		
								if(casteMatchd != null && casteMatchd.equalsIgnoreCase("Y")){
									reportVO.setCasteMatchedCount(1L);
								}else if(casteMatchd != null && casteMatchd.equalsIgnoreCase("N")){
									reportVO.setCasteMatchedCount(2L);
								}else{							
									reportVO.setCasteMatchedCount(0L);
								}
								
								
								if(mobilMatchd != null && mobilMatchd.equalsIgnoreCase("Y")){
									reportVO.setMobileMatchedCount(1L);
								}else if(mobilMatchd != null && mobilMatchd.equalsIgnoreCase("N")){
									reportVO.setMobileMatchedCount(2L);
								}else{							
									reportVO.setMobileMatchedCount(0L);
								}
																
								if(newCasteMatched.get( surveyDetailsInfo.getVoter().getVoterId()) != null ){
									reportVO.setCasteId(Long.valueOf(newCasteMatched.get(surveyDetailsInfo.getVoter().getVoterId())));
								}
							}
						if(dcWmMap != null && dcWmMap.size() > 0)
						{
							GenericVO genVO = dcWmMap.get(reportVO.getVoterId());
							if(genVO != null)
							{
								reportVO.setDcCaste(genVO.getDesc());
								reportVO.setWmCaste(genVO.getName());
							}
						}
						resultList.add(reportVO);
						
					}
					
					if(resultList != null && resultList.size()>0){
						finalVO.setSubList(resultList);
						
					}
				}	
				
			//}
					
			List<GenericVO> casteListOfSamples = new ArrayList<GenericVO>();
			List<Object[]> casteInfo = surveyDetailsInfoDAO.getCasteWiseCountInBooth(boothId,surveyUserids);	
			if(casteInfo != null && casteInfo.size()>0){
				for (Object[] caste : casteInfo) {
					GenericVO vo = new  GenericVO();
					vo.setId(caste[0] != null ? (Long) caste[0]:0L);
					vo.setName(caste[1] != null ? caste[1].toString():"");
					vo.setCount(caste[2] != null ? (Long) caste[2]:0L);
					
					casteListOfSamples.add(vo);
					
				}
			}
			
			finalVO.setCount(surveyDetailsInfoDAO.getTotalVotersinBooth(boothId));
			
			if(casteListOfSamples != null && casteListOfSamples.size()>0){
				
				Collections.sort(casteListOfSamples, new Comparator<GenericVO>() {

					public int compare(GenericVO o1, GenericVO o2) {
						
						return (int) (o2.getCount() - o1.getCount());
					}
				});
				
				finalVO.setGenericVOList(casteListOfSamples);
			}
						
			retultList.add(finalVO);
			
			
			SurveyReportVO allCastesVO = new SurveyReportVO();
			
			List<Object[]> castesList = casteStateDAO.getAllCasteDetailsForVoters(1L); // for AP state
			List<GenericVO> stateCasteList = new ArrayList<GenericVO>();
			
			if(castesList != null && castesList.size()>0){
				for (Object[] cast : castesList) {
					GenericVO vo = new  GenericVO();
					vo.setId(cast[0] != null ? (Long) cast[0]:0L);
					vo.setName(cast[1] != null ? cast[1].toString():"");
					
					stateCasteList.add(vo);
				}
				
				allCastesVO.setGenericVOList(stateCasteList);
			}
			
			retultList.add(allCastesVO);
			
		} catch (Exception e) {
			retultList = null;
			LOG.error("Exception raised in getSurveyVotersList() service in SurveyDataDetailsService", e);
			e.printStackTrace();
		}		
		return retultList;
	}
	 
	public void saveCallCenterForDataCollector(SurveyReportVO surveyReportVO,Long userId)
	{
		SurveyCallStatus surveyCallStatus = null;
		
		Long surveyCallStatusId = surveyCallStatusDAO.getSurveyCallDtalsByVoterId(surveyReportVO.getVoterId());
		
		if(surveyCallStatusId != null && surveyCallStatusId != 0 )
		{				
			surveyCallStatus = surveyCallStatusDAO.get(surveyCallStatusId);
		}			
		else if(surveyCallStatus == null )
		{
			surveyCallStatus = new SurveyCallStatus();
			//surveyCallStatus.setSurveyUser(surveyUserDAO.get(surveyReportVO.getUserid()));
			
			surveyCallStatus.setVoterId(surveyReportVO.getVoterId());
			//surveyCallStatus.setVoter(voterDAO.get(surveyReportVO.getVoterId()));
			surveyCallStatus.setInsertedDate(dateUtilService.getCurrentDateAndTime());
		}
		surveyCallStatus.setSurveyUserId(surveyReportVO.getUserid());
		
		surveyCallStatus.setUpdatedDate(dateUtilService.getCurrentDateAndTime());
		//surveyCallStatus.setBooth(boothDAO.get(surveyReportVO.getBoothId()));
		surveyCallStatus.setBoothId(surveyReportVO.getBoothId());
		surveyCallStatus.setUserId(userId);
		//surveyCallStatus.setUser(userDAO.get(userId));
		
		if(surveyReportVO.getMobileNo().equalsIgnoreCase("2")){
			surveyCallStatus.setMobileNoStatus("Y");
		}
		else if(surveyReportVO.getMobileNo().equalsIgnoreCase("6")){
			surveyCallStatus.setMobileNoStatus(null);
		}
		else{
			surveyCallStatus.setMobileNoStatus("N");
		}
		
		if(surveyReportVO.getMatchedCount().toString().equalsIgnoreCase("1") ){
			surveyCallStatus.setMatchedStatus("Y");
			surveyCallStatus.setCasteStateId(null);
		}
		else if(surveyReportVO.getMatchedCount().toString().equalsIgnoreCase("5") ){
			surveyCallStatus.setMatchedStatus(null);
			surveyCallStatus.setCasteStateId(null);
		}
		else{	
			surveyCallStatus.setMatchedStatus("N");
			
			if(surveyReportVO.getCasteId() != 0){
				//surveyCallStatus.setCasteState(casteStateDAO.get(surveyReportVO.getCasteId()));
				surveyCallStatus.setCasteStateId(surveyReportVO.getCasteId());
			}
		}
									
		surveyCallStatusDAO.save(surveyCallStatus);
	}
	
	
	public void saveCallCenterForDataVerifier(SurveyReportVO surveyReportVO,Long userId)
	{
		SurveyCallStatus surveyCallStatus = null;
		
		Long surveyCallStatusId = surveyCallStatusDAO.getSurveyCallDtalsByVoterId(surveyReportVO.getVoterId());
		
		if(surveyCallStatusId != null && surveyCallStatusId != 0 )
		{				
			surveyCallStatus = surveyCallStatusDAO.get(surveyCallStatusId);
		}			
		else if(surveyCallStatus == null )
		{
			surveyCallStatus = new SurveyCallStatus();
			
			surveyCallStatus.setVoterId(surveyReportVO.getVoterId());
			surveyCallStatus.setInsertedDate(dateUtilService.getCurrentDateAndTime());
		}
		surveyCallStatus.setDvSurveyUserId(surveyReportVO.getUserid());
		
		surveyCallStatus.setUpdatedDate(dateUtilService.getCurrentDateAndTime());
		surveyCallStatus.setBoothId(surveyReportVO.getBoothId());
		surveyCallStatus.setDvWebMonterId(userId);
		
		if(surveyReportVO.getMobileNo().equalsIgnoreCase("2"))
		{
			surveyCallStatus.setDvMobileNoStatus("Y");
		}
		else if(surveyReportVO.getMobileNo().equalsIgnoreCase("6"))
		{
			surveyCallStatus.setDvMobileNoStatus(null);
		}
		else
		{
			surveyCallStatus.setDvMobileNoStatus("N");
		}
		
		if(surveyReportVO.getMatchedCount().toString().equalsIgnoreCase("1") )
		{
			surveyCallStatus.setDvMatchedStatus("Y");
			surveyCallStatus.setDvCasteStateId(null);
		}
		else if(surveyReportVO.getMatchedCount().toString().equalsIgnoreCase("5") ){
			surveyCallStatus.setDvMatchedStatus(null);
			surveyCallStatus.setDvCasteStateId(null);
		}
		else{	
			surveyCallStatus.setDvMatchedStatus("N");
			
			if(surveyReportVO.getCasteId() != 0){
				//surveyCallStatus.setCasteState(casteStateDAO.get(surveyReportVO.getCasteId()));
				surveyCallStatus.setDvCasteStateId(surveyReportVO.getCasteId());
			}
		}
									
		surveyCallStatusDAO.save(surveyCallStatus);
	}
	public ResultStatus saveSurveyCallStatusDetils(final Long userId,final List<SurveyReportVO> verifiedList){
		ResultStatus status = new ResultStatus();
		
		try {
			
			transactionTemplate.execute(new TransactionCallbackWithoutResult() {
				protected void doInTransactionWithoutResult(TransactionStatus arg0) {
					
					DateUtilService dateUtilService = new DateUtilService();
					
					
					if(verifiedList != null && verifiedList.size()>0){

						
						for (SurveyReportVO surveyReportVO : verifiedList) {
							
							if(surveyReportVO.getUserTypeId().longValue() == 1l)
							{
								saveCallCenterForDataCollector(surveyReportVO,userId);
							}
							else
							{
								saveCallCenterForDataVerifier(surveyReportVO,userId);
							}
							
							
						}
					}
				}
			});
			
			status.setResultCode(ResultCodeMapper.SUCCESS);
			status.setMessage(" Survey Details Verified Successfully...");
			
		} catch (Exception e) {
			status.setResultCode(ResultCodeMapper.FAILURE);
			status.setMessage(" Exception occured while saving Survey Details. ");
			LOG.error("Exception raised in saveSurveyCallStatusDetils() service in SurveyDataDetailsService", e);
			e.printStackTrace();			
		}
		
		return status;
	}
	
	public List<SelectOptionVO> getsurveyuserConstituencies()
	{
		List<SelectOptionVO> resultList = new ArrayList<SelectOptionVO>();
		try{
			List<Object[]> list = surveyUserConstituencyDAO.getSurveyConstituencies();
			if(list != null && list.size() > 0)
			{
				for(Object[] params : list)
				{
				resultList.add(new SelectOptionVO((Long)params[0],params[1].toString()));
				}
			}
			
		}
		catch (Exception e) {
			LOG.error("Exception raised in getsurveyuserConstituencies() service in SurveyDataDetailsService", e);	
		}
		return resultList;
	}
	
	public List<SurveyReportVO> getSurveyDetailsForConstituency(Long constituencyId,Long userTypeId,String fromDate,List<Long> userIds,String toDate)
	{
		
		
		List<SurveyReportVO> resultList = new ArrayList<SurveyReportVO>();
		List<Long> userIDs = new ArrayList<Long>();
		List<Long> boothIDs = new ArrayList<Long>();
		try{
			SimpleDateFormat originalFormat = new SimpleDateFormat("dd-MM-yyyy");
			Date date1 = originalFormat.parse(fromDate);
			Date date2 = originalFormat.parse(toDate);
			List<Object[]> dataList = null;
			if(userIds != null && userIds.size() > 0)
			{
				dataList = surveyDetailsInfoDAO.getSurveyDetailsByConstituencyByUsers(constituencyId,userTypeId,date1,userIds,date2);
				userIDs = surveyDetailsInfoDAO.getUserIdsForConstituencyByUser(constituencyId,userTypeId,date1,userIds,date2);
			}
			else
			{
				dataList = surveyDetailsInfoDAO.getSurveyDetailsByConstituency(constituencyId,userTypeId,date1,date2);
				userIDs = surveyDetailsInfoDAO.getUserIdsForConstituency(constituencyId,userTypeId,date1,date2);
			}
			
			Map<Long,SurveyReportVO> leadersMap = null;
			if(userIDs != null && userIDs.size() > 0)
			{
				leadersMap = new HashMap<Long, SurveyReportVO>();

				List<Object[]> leadersList = surveyUserRelationDAO.getUserLeaderIds(userIDs);
				if(leadersList != null && leadersList.size() > 0)
				{
					for(Object[] leader : leadersList)
					{
						if(leadersMap.get((Long)leader[0]) == null){
							SurveyReportVO surveyReportVO = new SurveyReportVO();
							surveyReportVO.setId((Long)leader[1]);
							surveyReportVO.setName(leader[2].toString());
							surveyReportVO.setVerified(leader[3].toString());
							leadersMap.put((Long)leader[0], surveyReportVO);
						}
					}
				}
			}
			if(dataList != null && dataList.size() > 0)
			{
			// List<Object[]> list1 = surveyDetailsInfoDAO.getBoothCount(constituencyId,userTypeId);
				for(Object[] user : dataList)
				{
					SurveyReportVO surveyReportVO = new SurveyReportVO();
					surveyReportVO.setUserid((Long)user[0]);
					surveyReportVO.setUserName(user[1].toString());
					surveyReportVO.setMobileNo(user[5].toString());
					if(leadersMap.get((Long)user[0]) != null){
						surveyReportVO.setVerifier(leadersMap.get((Long)user[0]));
					}
					resultList.add(surveyReportVO);
					//userIDs.add((Long)user[0]);
					if(!boothIDs.contains((Long)user[2]))
					boothIDs.add((Long)user[2]);
			}

				
				  Map<Long,Long> totalVotersMap = new HashMap<Long, Long>();
					 List<Object[]> boothTotalVoters = boothPublicationVoterDAO.getBoothWiseVoterDetails(boothIDs);
					 if(boothTotalVoters != null && boothTotalVoters.size() > 0)
					 {
						for(Object[] params : boothTotalVoters)
						{
							if(totalVotersMap.get((Long)params[0]) == null)
							totalVotersMap.put((Long)params[0], (Long)params[1]);
							else
								totalVotersMap.put((Long)params[0], (Long)params[1] + totalVotersMap.get((Long)params[0]));	
						}
					 }
				if(dataList != null && dataList.size() > 0)
				{
					 for(Object[] params : dataList)
					 {
						 SurveyReportVO boothVo = getMatchedVo(resultList,(Long)params[0]);
						 if(boothVo != null)
						 {
							 SurveyReportVO vo = new SurveyReportVO();
							 vo.setBoothId((Long)params[2]);
							 vo.setPartNo(params[3].toString());
						     vo.setTotalVoters(totalVotersMap.get((Long)params[2]));
							 
							 boothVo.getSubList().add(vo);
							 
						 }
						 
					 }
				}
				
				
				List<Object[]> list2 = surveyDetailsInfoDAO.getHamletCountByBooths(userIDs,boothIDs,userTypeId,date1,date2);
				if(list2 != null && list2.size() > 0)
				{
					 for(Object[] params : list2)
					 {
						 SurveyReportVO user = getMatchedVo(resultList,(Long)params[0]);
						 if(user != null)
						 {
							 SurveyReportVO boothVo = getMatchedBoothVo(user.getSubList(),(Long)params[1]);
							 if(boothVo != null)
							 boothVo.setHamletCount((Long)params[2]);
						 }
						 
					 }
				 
				}
				
				List<Object[]> list3 = surveyDetailsInfoDAO.getCasteCountByBooths(userIDs,boothIDs,userTypeId,date1,date2);
				if(list3 != null && list3.size() > 0)
				{
					for(Object[] params : list3)
					 {
						 SurveyReportVO user = getMatchedVo(resultList,(Long)params[0]);
						 if(user != null)
						 {
							 SurveyReportVO boothVo = getMatchedBoothVo(user.getSubList(),(Long)params[1]);
							 if(boothVo != null)
							 boothVo.setCasteCount((Long)params[2]);
						 }
						 
					 }
				} 
				
				List<Object[]> list4 = surveyDetailsInfoDAO.getMbileNoCountByBooths(userIDs,boothIDs,userTypeId,date1,date2);
				if(list4 != null && list4.size() > 0)
				{
					for(Object[] params : list4)
					 {
						 SurveyReportVO user = getMatchedVo(resultList,(Long)params[0]);
						 if(user != null)
						 {
							 SurveyReportVO boothVo = getMatchedBoothVo(user.getSubList(),(Long)params[1]);
							 if(boothVo != null)
							 boothVo.setMobileNoCount((Long)params[2]);
						 }
						 
					 }
				} 
				
				List<Object[]> statusList =surveyCallStatusDAO.getStatusListForUser(userIDs,boothIDs,userTypeId);
				if(statusList !=null && statusList.size() > 0)
				{
					
					
					for(Object[] params : statusList)
					 {
						
						 SurveyReportVO user = getMatchedVo(resultList,(Long)params[0]);
						 if(user != null)
						 {
							
							 SurveyReportVO boothVo = getMatchedBoothVo(user.getSubList(),(Long)params[1]);
							 if(boothVo != null)
							 {
								 boothVo.setCount(boothVo.getCount() + 1);
								 if(params[2]!= null && params[2].toString().equalsIgnoreCase("N"))
									boothVo.setMobileNotMatchedCount(boothVo.getMobileNotMatchedCount() + 1);
								if(params[2]!= null && params[2].toString().equalsIgnoreCase("Y"))
									boothVo.setMobileMatchedCount(boothVo.getMobileMatchedCount() + 1);
								if(params[3]!= null && params[3].toString().equalsIgnoreCase("N"))
									boothVo.setCasteNotMatchedCount(boothVo.getCasteNotMatchedCount() + 1);
								if(params[3]!= null && params[3].toString().equalsIgnoreCase("Y"))
									boothVo.setCasteMatchedCount(boothVo.getCasteMatchedCount() + 1);
							 }
						 }
						 
					 }
					
					
				}
			}
			
			
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return resultList;
	}
	
	
	public SurveyReportVO getMatchedVo(List<SurveyReportVO> resultList,Long userId)
	{
	
		try{
			if(resultList == null)
				return null;
			for(SurveyReportVO vo : resultList)
			{
				if(userId.longValue() == vo.getUserid().longValue())
					return vo;
			}
		}
		catch (Exception e) {
			e.printStackTrace();
			
		}
		return null;
	}
	public SurveyReportVO getMatchedBoothVo(List<SurveyReportVO> boothList,Long boothId)
	{
	
		try{
			if(boothList == null)
				return null;
			for(SurveyReportVO vo : boothList)
			{
				if(boothId.longValue() == vo.getBoothId().longValue())
					return vo;
			}
		}
		catch (Exception e) {
			e.printStackTrace();
			
		}
		return null;
	}
	
	/**
	 * This Service is used for getting all user names and password for users under leader
	 * @param leaderId
	 * @return returnList
	 */
	public List<GenericVO> getSurveyUserNameAndPasswordByLeader(Long leaderId)
	{
		List<GenericVO> returnList = null;
		try
		{
			List<Object[]> result = surveyUserRelationDAO.getSurveyUsersByLeaderWise(leaderId);
			if(result != null && result.size() > 0)
			{
				returnList = new ArrayList<GenericVO>();
				for (Object[] parms : result)
				{
					GenericVO VO = new GenericVO();
					VO.setId((Long)parms[0]);
					VO.setName(parms[1].toString());
					VO.setDesc(parms[2].toString());
					returnList.add(VO);
				}
			}
		}
		catch (Exception e) 
		{
			LOG.error("Exception raised in getSurveyUserNameAndPasswordByLeader() service in SurveyDataDetailsService", e);
		}
		return returnList;
	}
	
	public List<SurveyReportVO> getSurveyUserDetailsByConstituencies(Long constituencyId,Date date,List<Long> userIds)
	{
		List<SurveyReportVO> returnList = new ArrayList<SurveyReportVO>();
		try
		{
			List<Object[]> details = null;
			if(userIds != null && userIds.size() > 0)
			{
				details = surveyDetailsInfoDAO.getAllUserDetailsByConstituencyByUsers(constituencyId, date,userIds);
			}
			else
			{
				details = surveyDetailsInfoDAO.getAllUserDetailsByConstituency(constituencyId, date);
			}
			
			if(details != null && details.size() > 0)
			{
				
				Map<Long,Map<Long,SurveyReportVO>> resultMap = new HashMap<Long, Map<Long,SurveyReportVO>>();
				Map<Long,SurveyReportVO> userDataMap = new HashMap<Long, SurveyReportVO>();
				DateFormat dateFormat = new SimpleDateFormat("hh:mm a"); 
								 
				for (Object[] parms : details)
				{
						
						Map<Long,SurveyReportVO> boothMap = resultMap.get((Long)parms[0]);
						if(boothMap == null)
						{
							boothMap = new HashMap<Long, SurveyReportVO>();
							
							resultMap.put((Long)parms[0], boothMap);		
							
						}					
			            SurveyReportVO vo = userDataMap.get((Long)parms[0]);
			            if(vo == null)
			            {
			            	  vo = new SurveyReportVO();
			            	  vo.setUserid((Long)parms[0]);
							  vo.setUserName(parms[1] != null ? parms[1].toString() : null);
							  vo.setMobileNo(parms[2] != null ? parms[2].toString() : null);
							  vo.setUserType(parms[8] != null ? parms[8].toString() : null);
							  
							  if(parms[10] != null)
							  {
									String time = dateFormat.format(parms[10]);							 
									vo.setSurveyDate(time);
							  }
							  userDataMap.put((Long)parms[0], vo);
			              }		
					 
						  SurveyReportVO  vo1 = boothMap.get((Long)parms[9]);
						  if(vo1 == null)
						  {
							  vo1 = new SurveyReportVO();
							  vo1.setPartNo(parms[3] != null ? parms[3].toString() : null);
							  vo1.setMandalName(parms[4] != null ? parms[4].toString() : null);
							  vo1.setPanchayatName(parms[5] != null ? parms[5].toString() : null);
							  vo1.setLocalArea(parms[6] != null ? parms[6].toString() : null);						
							  vo1.setVillageCovered(parms[7] != null ? parms[7].toString() : null);	
							  vo1.setBoothId((Long)parms[9]);
							  boothMap.put((Long)parms[9],vo1);		
						  }
				}
								
				for(Long userId : resultMap.keySet())
				{
					SurveyReportVO vo = userDataMap.get(userId);
					Map<Long,SurveyReportVO> boothMap = resultMap.get(userId);
					List<SurveyReportVO> booths = new ArrayList<SurveyReportVO>();
					for(Long booth : boothMap.keySet())
					{
						SurveyReportVO vo1 = boothMap.get(booth);
						booths.add(vo1);	
					}
					vo.setSubList(booths);
					returnList.add(vo);
				}
			}
		} 
		catch (Exception e)
		{
			LOG.error("Exception raised in getSurveyUserDetails method", e);
			e.printStackTrace();
		}
		return returnList;
	}
	
	public GenericVO getAlreadyAssignTabsListForLeader(Long leaderId){
		
		GenericVO returnVO = new GenericVO();
		
		List<GenericVO> returnList = new ArrayList<GenericVO>();
		try
		{
			List<Object[]> tabsList = surveyUserTabAssignDAO.getSurveyTabsBySurveyUserId(leaderId);
			
			if(tabsList != null && tabsList.size()>0){
				for (Object[] param : tabsList) {
					
					GenericVO vo = new GenericVO();
					vo.setId((Long)param[0]);
					vo.setName(param[1].toString().trim());					
					returnList.add(vo);
					
				}
				
				returnVO.setGenericVOList(returnList);
			}
		} 
		catch (Exception e)
		{
			LOG.error("Exception raised in getAlreadyAssignTabsListForLeader() in SurveyDataService service class.", e);
			e.printStackTrace();
			returnList = null;
		}
		return returnVO;
		
	}
	
	public List<GenericVO> getAssignedUsersOfAConstituency(Long constituencyId)
	{
		
		 List<GenericVO> returnList  = new ArrayList<GenericVO>();
		try
		{
			List<Object[]> usersDtls = surveyUserRelationDAO.getAssignedUsersOfAConstituency(constituencyId);
			List<Long> constUsersIds = new ArrayList<Long>(0);
			
			if(usersDtls != null && usersDtls.size()>0){
				for(Object[] obj:usersDtls)
				{
					GenericVO userVO = new GenericVO();
					
					userVO.setId((Long)obj[0]);
					userVO.setName(obj[1].toString());
					returnList.add(userVO);
					
					constUsersIds.add((Long)obj[0]);
				}
			}
			
			
			if(constUsersIds != null && constUsersIds.size()>0){
				List<Long> alredyAssnIds = webMonitoringAssignedUsersDAO.getConstiteuncyUsersInConsti(constUsersIds);
				if(alredyAssnIds != null && alredyAssnIds.size()>0){
					
					for (Long surveyUserId : alredyAssnIds) {
						
						GenericVO genericO = getGenericVOById(returnList,surveyUserId);
						
						if(genericO != null){						
							returnList.remove(genericO);
						}
						
					}
					
				}
			}
			
			
		}catch(Exception e)
		{
			e.printStackTrace();
			LOG.error(" exception occured in getAssignedUsersOfAConstituency () of SurveyDataDetailsService class ",e);
		}
		return returnList;
	}
	
	public GenericVO getGenericVOById(List<GenericVO> usersList,Long surveyUserId){
		GenericVO returnVO = null;
		try {
			if(usersList != null && usersList.size()>0){
				for (GenericVO vo : usersList) {
					if(vo.getId().toString().equalsIgnoreCase(surveyUserId.toString())){
						return vo;
					}
				}
			}
			
		} catch (Exception e) {
			returnVO = null;
		}
		return returnVO;
	}
	public List<GenericVO> getAllWebMonitoringUsersDetails()
	{
		 List<GenericVO> usersList  = new ArrayList<GenericVO>();
		try
		{
			List<Object[]> usersDetails = userDAO.getAllWebMonitoringUsersDetails(IConstants.CASTE_SURVEY_CALL_CENTER );
			
			for(Object[] obj:usersDetails)
			{
				GenericVO userVO = new GenericVO();
				
				userVO.setId((Long)obj[0]);
                userVO.setName(obj[1]+" "+obj[2]);
                
                usersList.add(userVO);
			}
			
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		return usersList;
	}
	
	public String saveWebMonioringAssignDetails(final Long webMonitorId,final List<Long> userIds)
	{
		try
		{
			List<WebMonitoringAssignedUsers> users = webMonitoringAssignedUsersDAO.getAssignedUsersDetailsByWebMonitorId(webMonitorId);
			final Map<Long,Long> monitringAssignUsersMap = new HashMap<Long,Long>();
			
			if(users != null && users.size() >0){
				for(WebMonitoringAssignedUsers assignedUser:users){
						assignedUser.setIsDelete("Y");
						monitringAssignUsersMap.put(assignedUser.getSurveyUserId(), assignedUser.getWebMonitoringAssignedUsersId());
				}
			}
			
			transactionTemplate.execute(new TransactionCallbackWithoutResult() {
				protected void doInTransactionWithoutResult(TransactionStatus status) {
			
				for(Long userId:userIds)
				{
					WebMonitoringAssignedUsers webMonitoringAssignedUsers = null;
					
					if(monitringAssignUsersMap != null && monitringAssignUsersMap.size()>0){
						
						if(monitringAssignUsersMap.get(userId) != null){
							webMonitoringAssignedUsers = webMonitoringAssignedUsersDAO.get(monitringAssignUsersMap.get(userId));
						}
					}
					
					if(webMonitoringAssignedUsers == null){
						webMonitoringAssignedUsers = new WebMonitoringAssignedUsers();
						webMonitoringAssignedUsers.setSurveyUserId(userId);
						webMonitoringAssignedUsers.setWebMoniterUserId(webMonitorId);
					}
					
					
					webMonitoringAssignedUsers.setIsDelete("N");
					webMonitoringAssignedUsersDAO.save(webMonitoringAssignedUsers);
					constituencyDAO.flushAndclearSession();
				}
			
			}});
		}catch(Exception e)
		{
			e.printStackTrace();
			return null;
		}
		return "success";		
	}
	
	public SurveyReportVO getTotalCasteCollectedCount()
	{
		
		SurveyReportVO surveyReportVO = new SurveyReportVO();
		try{
			SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
			Date date = new Date();
			String modifiedDate= new SimpleDateFormat("yyyy-MM-dd").format(date);
			Date convertedDate = null;
			convertedDate = formatter.parse(modifiedDate);
			Long total = surveyDetailsInfoDAO.getTotalCastecollectedCount();
			Long today =  surveyDetailsInfoDAO.getTotalCastecollectedCountForToday(convertedDate);
			surveyReportVO.setCount(total);
			surveyReportVO.setCasteCount(today);
			
		}
		catch (Exception e) {
			LOG.error("Exception Occured in getTotalCasteCollectedCount()",e);
		}
		return surveyReportVO;
	}
	public List<SurveyReportVO> getCasteCollectedCountsForDates(String date)
	{
		List<SurveyReportVO> resultList = new ArrayList<SurveyReportVO>();
		List<Object[]> list = null;
	try{
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		Date date1 = new Date();
		String modifiedDate= new SimpleDateFormat("yyyy-MM-dd").format(date1);
		Date convertedDate = null;
		convertedDate = formatter.parse(modifiedDate);
		if(date.equals(""))
		list =  surveyDetailsInfoDAO.getTotalCastecollectedCountForDates();
		else
		list = surveyDetailsInfoDAO.getTotalCastecollectedCountForTodayForDates(convertedDate);
		
		
		
		for(Object[] params : list)
		{
			SurveyReportVO vo = new SurveyReportVO();
			vo.setSurveyDate(params[0].toString().substring(8, 10)+"-"+params[0].toString().substring(5, 7)+"-"+params[0].toString().substring(0, 4));
			vo.setCount((Long)params[1]);
			resultList.add(vo);
		}
	}
	catch (Exception e) {
		LOG.error("Exception Occured in getCasteCollectedCountsForDates()",e);
	}
	return resultList;
	}
	
	  public String roundTo2DigitsFloatValue(Float number){
		  
		  LOG.debug("Entered into the roundTo2DigitsFloatValue service method");
		  
		  String result = "";
		  try
		  {
			
			NumberFormat f = NumberFormat.getInstance(Locale.ENGLISH);  
			f.setMaximumFractionDigits(2);  
			f.setMinimumFractionDigits(2);
			
			result =  f.format(number);
		  }catch(Exception e)
		  {
			  LOG.error("Exception raised in roundTo2DigitsFloatValue service method");
			  e.printStackTrace();
		  }
		  return result;
	  }
	  /**
	   * @author Srishailam Pittala
	   * @Date : 23rd July, 2014
	   * 
	   * @param constitunecyId
	   * @param surveyUserId (may be Data Collector Id / Data Lead Id ) 
	   * @return result status 
	   */
	  public ResultStatus releaseTabsAndBoothsBySurveyUserId(final  Long surveyUserId,final  Long dummyLeaderId,final String remark){
		 ResultStatus resultstatus = new ResultStatus();
		  try {
			  
			 	  SurveyUser  surveyUser= surveyUserDAO.get(surveyUserId);
				  
				  final Long userTypeId = surveyUser.getSurveyUserType().getSurveyUsertypeId();
				 
				  Object status = transactionTemplate.execute(new TransactionCallback() 
				  { 
					  
					public Object doInTransaction(TransactionStatus arg0) 
					{
						List<Long> surveyUserBoothAssignIds = new ArrayList<Long>(0);
						List<Long> assignedUsersList = new ArrayList<Long>(0);
						ResultStatus status = new ResultStatus();
						
						 if(userTypeId.longValue() == 1L || userTypeId.longValue() == 4L )
						  {
								  	assignedUsersList.add(surveyUserId);
								  	status =  updateServeyUserRelationDetails(userTypeId,assignedUsersList, surveyUserId,dummyLeaderId);  // releasing users and tabs for single assigned user 
							  
							  		surveyUserBoothAssignIds = surveyUserBoothAssignDAO.getAssignedDetailsForUser(assignedUsersList);
							  
						  }
						  else if(userTypeId.longValue() == 3L || userTypeId.longValue() == 5L )
						  {
								 assignedUsersList =  surveyUserRelationDAO.getAssignedUsersForLeader(surveyUserId);
								 
								 if(assignedUsersList != null && assignedUsersList.size()>0){
									
									 status =  updateServeyUserRelationDetails(userTypeId,assignedUsersList, surveyUserId,dummyLeaderId);  // releasing users and tabs for assigned users from leader
									 
									  surveyUserBoothAssignIds = surveyUserBoothAssignDAO.getAssignedDetailsForUser(assignedUsersList);
								}
						  }						  
						 
						  if(surveyUserBoothAssignIds != null && surveyUserBoothAssignIds.size()>0)
						  {
							  updateBoothAssignForDummyUser(dummyLeaderId,surveyUserBoothAssignIds,assignedUsersList);
						  }
						  
						 	 SurveyUser surveyUser = surveyUserDAO.get(surveyUserId);	
						 	 
							  if(surveyUser != null)
							  {
								  surveyUser.setActiveStatus("N");
								  surveyUser.setRemarks(remark);
								  surveyUserDAO.save(surveyUser);
							  }
							  
						return status;
					}
				});
				 
				  resultstatus =(ResultStatus) status;
			
		} catch (Exception e) {
			  LOG.error("Exception raised in releaseTabsAndBoothsBySurveyUserId() in  SurveyDataDetailsService class",e);
			  resultstatus.setResultCode(1);
			  resultstatus.setMessage("Error occured while updating the Deactivating the details...");
			  e.printStackTrace();
		}
		  
		  return resultstatus;
	  }
	  
	  private void updateBoothAssignForDummyUser(final Long dummyLeaderId,final List<Long> surveyUserBoothAssignIds,final List<Long> assignedUsersList)
	  {		  
			 LOG.error("Entered into updateBoothAssignForDummyUser() in  SurveyDataDetailsService class");
			 
		  try {
			  
			  transactionTemplate.execute(new TransactionCallbackWithoutResult() {
				protected void doInTransactionWithoutResult(TransactionStatus arg0) {
				
					 if(surveyUserBoothAssignIds != null && surveyUserBoothAssignIds.size()>0){
						  
						  for (Long surveyUserBoothAssignId : surveyUserBoothAssignIds) {
							  
							  SurveyUserBoothAssign surveyUserBoothAssign = surveyUserBoothAssignDAO.get(surveyUserBoothAssignId);
							  
							  surveyUserBoothAssign.setIsDelete("Y");
							  surveyUserBoothAssign.setUpdatedTime(dateUtilService.getCurrentDateAndTime());
							  surveyUserBoothAssign = surveyUserBoothAssignDAO.save(surveyUserBoothAssign);
							  
							  if(dummyLeaderId != null){ // this loop works for if leader deactivating
									
								  for (Long surveyUserId : assignedUsersList) 
								  {
									
									  SurveyUserBoothAssign userBoothAssign = new SurveyUserBoothAssign();
									  
									  userBoothAssign.setSurveyUserId(surveyUserId);
									  userBoothAssign.setConstituencyId(surveyUserBoothAssign.getConstituencyId());
									  userBoothAssign.setPanchayat(surveyUserBoothAssign.getPanchayat());
									  userBoothAssign.setBoothId(surveyUserBoothAssign.getBoothId());
									  userBoothAssign.setInsertedTime(surveyUserBoothAssign.getInsertedTime());
									  userBoothAssign.setIsDelete("N");
									  userBoothAssign.setRemainingDataBooth(surveyUserBoothAssign.getRemainingDataBooth());							 
									  
									  surveyUserBoothAssignDAO.save(userBoothAssign);
									  
								  }
								  
								  
								}
						  }
					  }
				
				}
			});
			 

		} catch (Exception e) {
			 LOG.error("Exception raised in updateBoothAssignForDummyUser() in  SurveyDataDetailsService class",e);
		}
		  
	
	  }
	  
	  public List<Long> getUsersForLeader(Long leaderId)
		{
			List<Long> usersList = new ArrayList<Long>();
			try
			{
				usersList= surveyUserRelationDAO.getUsersForLeader(leaderId);
				
			} 
			catch (Exception e)
			{
				LOG.error("Exception raised in getUsersForLeader", e);
				e.printStackTrace();
			}
			return usersList;
		}
		
	  
}
