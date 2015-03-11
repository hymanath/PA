package com.itgrids.partyanalyst.service.impl;

import java.math.BigDecimal;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;

import org.apache.log4j.Logger;
import org.jfree.util.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionCallbackWithoutResult;
import org.springframework.transaction.support.TransactionTemplate;

import com.itgrids.partyanalyst.dao.IBoothDAO;
import com.itgrids.partyanalyst.dao.IBoothPublicationVoterDAO;
import com.itgrids.partyanalyst.dao.ICasteStateDAO;
import com.itgrids.partyanalyst.dao.IHamletDAO;
import com.itgrids.partyanalyst.dao.IPanchayatDAO;
import com.itgrids.partyanalyst.dao.IRegionWiseSurveysDAO;
import com.itgrids.partyanalyst.dao.ISurveyAccessUsersDAO;
import com.itgrids.partyanalyst.dao.ISurveyCallStatusDAO;
import com.itgrids.partyanalyst.dao.ISurveyCompletedLocationsDAO;
import com.itgrids.partyanalyst.dao.ISurveyCompletedLocationsDetailsDAO;
import com.itgrids.partyanalyst.dao.ISurveyConstituencyTempDAO;
import com.itgrids.partyanalyst.dao.ISurveyDAO;
import com.itgrids.partyanalyst.dao.ISurveyDetailsInfoDAO;
import com.itgrids.partyanalyst.dao.ISurveyFinalDataDAO;
import com.itgrids.partyanalyst.dao.ISurveyUserConstituencyDAO;
import com.itgrids.partyanalyst.dao.ISurveyUserDAO;
import com.itgrids.partyanalyst.dao.ISurveyUserRelationDAO;
import com.itgrids.partyanalyst.dao.ISurveyWmThirdPartyStatusDAO;
import com.itgrids.partyanalyst.dao.IUpdationDetailsDAO;
import com.itgrids.partyanalyst.dao.IUserDAO;
import com.itgrids.partyanalyst.dao.IVerifierBoothPercentageDAO;
import com.itgrids.partyanalyst.dao.IWebMonitorCompletedLocationsDetailsDAO;
import com.itgrids.partyanalyst.dao.IWebMonitoringAssignedUsersDAO;
import com.itgrids.partyanalyst.dao.hibernate.SurveyConstituencyTempDAO;
import com.itgrids.partyanalyst.dao.hibernate.SurveyWmThirdPartyStatusDAO;
import com.itgrids.partyanalyst.dto.DcDvCollectedDataVO;
import com.itgrids.partyanalyst.dto.GenericVO;
import com.itgrids.partyanalyst.dto.HamletCountVo;
import com.itgrids.partyanalyst.dto.PanchayatHamletsCountVo;
import com.itgrids.partyanalyst.dto.ResultCodeMapper;
import com.itgrids.partyanalyst.dto.ResultStatus;
import com.itgrids.partyanalyst.dto.SelectOptionVO;
import com.itgrids.partyanalyst.dto.SurveyReportVO;
import com.itgrids.partyanalyst.dto.SurveyResponceVO;
import com.itgrids.partyanalyst.dto.VerificationCompVO;
import com.itgrids.partyanalyst.model.SurveyAccessUsers;
import com.itgrids.partyanalyst.model.SurveyDetailsInfo;
import com.itgrids.partyanalyst.model.UpdationDetails;
import com.itgrids.partyanalyst.model.VerifierBoothPercentage;
import com.itgrids.partyanalyst.service.ISurveyDataDetailsService;
import com.itgrids.partyanalyst.service.ISurveyDetailsService;
import com.itgrids.partyanalyst.utils.DateUtilService;
import com.itgrids.partyanalyst.utils.IConstants;

public class SurveyDetailsService implements ISurveyDetailsService {

	private static final Logger LOG = Logger.getLogger(SurveyDetailsService.class);
	private ISurveyDAO surveyDAO;
	private TransactionTemplate transactionTemplate;
	private IUpdationDetailsDAO updationDetailsDAO;
	private IUserDAO userDAO;
	private ISurveyAccessUsersDAO surveyAccessUsersDAO;
	private DateUtilService dateUtilService = new DateUtilService();
	private IRegionWiseSurveysDAO regionWiseSurveysDAO;
	
	@Autowired
	private ISurveyFinalDataDAO surveyFinalDataDAO;
	
	@Autowired
	private ISurveyWmThirdPartyStatusDAO surveyWmThirdPartyStatusDAO;
	
	@Autowired
	private ISurveyUserRelationDAO surveyUserRelationDAO;
	
	@Autowired
	private IWebMonitoringAssignedUsersDAO webMonitoringAssignedUsersDAO;
	
	@Autowired
	private ISurveyUserConstituencyDAO surveyUserConstituencyDAO;
	
	
	@Autowired
	private ISurveyDetailsInfoDAO surveyDetailsInfoDAO;
	
	@Autowired
	private ISurveyUserDAO surveyUserDAO;
	
	@Autowired
	ICasteStateDAO casteStateDAO;
	
	@Autowired
	ISurveyCallStatusDAO surveyCallStatusDAO ;

	@Autowired
	private ISurveyCompletedLocationsDetailsDAO surveyCompletedLocationsDetailsDAO;
	
	@Autowired
	private IWebMonitorCompletedLocationsDetailsDAO webMonitorCompletedLocationsDetailsDAO;
	
	@Autowired
	private IBoothPublicationVoterDAO boothPublicationVoterDAO;
	
	@Autowired
	IBoothDAO boothDAO;
	
	@Autowired
	IPanchayatDAO panchayatDAO;
	
	@Autowired
	IVerifierBoothPercentageDAO verifierBoothPercentageDAO;
	@Autowired
	private ISurveyDataDetailsService surveyDataDetailsServic;
	
	@Autowired
	
	IHamletDAO hamletDAO;
	
	@Autowired ISurveyCompletedLocationsDAO surveyCompletedLocationsDAO;
	
	@Autowired
	private ISurveyConstituencyTempDAO surveyConstituencyTempDAO;
	
	public IRegionWiseSurveysDAO getRegionWiseSurveysDAO() {
		return regionWiseSurveysDAO;
	}

	public void setRegionWiseSurveysDAO(IRegionWiseSurveysDAO regionWiseSurveysDAO) {
		this.regionWiseSurveysDAO = regionWiseSurveysDAO;
	}

	public ISurveyAccessUsersDAO getSurveyAccessUsersDAO() {
		return surveyAccessUsersDAO;
	}

	public void setSurveyAccessUsersDAO(ISurveyAccessUsersDAO surveyAccessUsersDAO) {
		this.surveyAccessUsersDAO = surveyAccessUsersDAO;
	}

	public IUserDAO getUserDAO() {
		return userDAO;
	}

	public void setUserDAO(IUserDAO userDAO) {
		this.userDAO = userDAO;
	}

	public ISurveyDAO getSurveyDAO() {
		return surveyDAO;
	}
	
	public void setSurveyDAO(ISurveyDAO surveyDAO) {
		this.surveyDAO = surveyDAO;
	}

	public TransactionTemplate getTransactionTemplate() {
		return transactionTemplate;
	}

	public void setTransactionTemplate(TransactionTemplate transactionTemplate) {
		this.transactionTemplate = transactionTemplate;
	}

	public IUpdationDetailsDAO getUpdationDetailsDAO() {
		return updationDetailsDAO;
	}

	public void setUpdationDetailsDAO(IUpdationDetailsDAO updationDetailsDAO) {
		this.updationDetailsDAO = updationDetailsDAO;
	}

	public List<SelectOptionVO> getAllSurveys(){
		List<SelectOptionVO> listOfSurveys = null; 
		try{
			LOG.info("Entered into getAllRegisterUsersForAssigningParty()");
			List<Object[]> surveyList = surveyDAO.getAllSurveys();
			if(surveyList != null && surveyList.size() > 0)
			{
				listOfSurveys = new ArrayList<SelectOptionVO>(0);
				SelectOptionVO selectOptionVO = null;
				for(Object[] params : surveyList)
				{
					selectOptionVO = new SelectOptionVO();
					selectOptionVO.setId((Long)params[0]);
					selectOptionVO.setName(params[1].toString());
					listOfSurveys.add(selectOptionVO);
				}
			}
			
			return listOfSurveys;
		}catch (Exception e) {
			LOG.error("Exception Occured in getAllRegisterUsersForAssigningParty(), Exception - "+e);
			e.printStackTrace();
			return  null;
		}
	}
	
	public ResultStatus saveSurveyDetails(final Long userId,final Long surveyId){
		ResultStatus resultStatus = new ResultStatus();
		try{
			LOG.debug("Entered into saveSurveyDetails() method in Survey Details Service()");
			
			Long value = surveyAccessUsersDAO.checkForDuplicateRecords(userId,surveyId);
			if(value == 0){
			transactionTemplate.execute(new TransactionCallbackWithoutResult() {
				protected void doInTransactionWithoutResult(TransactionStatus status) {
					UpdationDetails updationDetails = new UpdationDetails();
					updationDetails.setCreatedBy(userDAO.get(userId));
					updationDetails.setUpdatedBy(userDAO.get(userId));
					updationDetails.setInsertedTime(dateUtilService.getCurrentDateAndTime());
					updationDetails.setLastUpdatedTime(dateUtilService.getCurrentDateAndTime());
					updationDetails = updationDetailsDAO.save(updationDetails);
					SurveyAccessUsers surveyAccessUsers = new SurveyAccessUsers();
						surveyAccessUsers.setUser(userDAO.get(userId));
						surveyAccessUsers.setSurvey(surveyDAO.get(surveyId));
						surveyAccessUsers.setUpdationDetails(updationDetails);
						surveyAccessUsersDAO.save(surveyAccessUsers);
				}
			});
				resultStatus.setResultCode(ResultCodeMapper.SUCCESS);
			}else{
				resultStatus.setResultCode(ResultCodeMapper.DATA_NOT_FOUND);
			}
			return resultStatus;
		}catch (Exception e) {
			LOG.error("Exception encountered, Check LOG for Details - ",e);
			resultStatus.setExceptionEncountered(e);
			resultStatus.setResultCode(ResultCodeMapper.FAILURE);
			return resultStatus;
		}	
	}
	
	
	public Map<String,String> getSurveyDetailsByRegion(Long regionId)
	{
		LOG.debug("Entered into the getSurveyDetailsByRegion service method");

		Map<String,String> surveyDetailsMap = new HashMap<String, String>();
		try
		{
			List<Object[]> list = regionWiseSurveysDAO.getSurveyDetailsByRegion(regionId);
			
			for(Object[] obj:list)
				surveyDetailsMap.put(obj[0].toString(), obj[1].toString());
			
		}catch(Exception e)
		{
			//e.printStackTrace();
			LOG.error("Exception raised in  getSurveyDetailsByRegion service method");
		}
		return surveyDetailsMap;
	}
	
	public List<GenericVO> getConstituencyWiseLeaders(Long constituencyId)
	{
		LOG.debug("Entered into the getConstituencyWiseLeaders service method");
		List<GenericVO> returnList = null;
		try 
		{
			List<Object[]> result = surveyUserConstituencyDAO.getSurveyLeaderByConstituency(constituencyId);
			if(result != null && result.size() > 0)
			{
				returnList = new ArrayList<GenericVO>();
				for (Object[] objects : result)
				{
					GenericVO VO = new GenericVO();
					VO.setId((Long)objects[0]);
					VO.setName(objects[1] != null ? objects[1].toString() : null);
					VO.setDesc(objects[2] != null ? objects[2].toString() : null);
					returnList.add(VO);
				}
			}
		} 
		catch (Exception e)
		{
			LOG.error("Exception raised in  getConstituencyWiseLeaders service method");
		}
		return returnList;
		
	}
	
	public List<SurveyReportVO> getSurveyUserConstituencyDetails(Long surveyUserId)
	{
		List<SurveyReportVO> resultList = new ArrayList<SurveyReportVO>();
		try {
			List<Object[]> constituency = surveyUserConstituencyDAO.getSurveyUserConstituencyDetails(surveyUserId);
			
			for(Object[] parms:constituency)
			{
				SurveyReportVO vo = new SurveyReportVO();	
				vo.setId((Long)parms[0]);
				vo.setName(parms[1].toString());
				resultList.add(vo);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return resultList;
	}
	
	public ResultStatus unTagConstituencyForAUser(Long userId,Long constituencyId)
	{
		LOG.info("Entered into unTagConstituencyForAUser method");
		ResultStatus resultStatus = new ResultStatus();
		try 
		{			
				int value = surveyUserConstituencyDAO.updateUserConstituencies(userId, constituencyId);
				if(value > 0)
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
			LOG.error("Exception raised in unTagConstituencyForAUser", e);
			resultStatus.setResultCode(3);
			resultStatus.setMessage("Exception");
		}
		return resultStatus;
	}


	public List<GenericVO> getAssignedSurveyUsersForWebMontringTeam(Long userId)
	{
		LOG.info("Entered into getAssignedSurveyUsersForWebMontringTeam method");
		List<GenericVO> returnList = null;
	    try
	    {
			List<Object[]> result = webMonitoringAssignedUsersDAO.getAssignedusersForWebMontringTeam(userId);
			if(result != null && result.size() > 0)
			{
				returnList = new ArrayList<GenericVO>();
				for (Object[] objects : result)
				{
					GenericVO VO = new GenericVO();
					VO.setId(objects[0] != null ? (Long)objects[0]:0l);
					VO.setName(objects[1] != null ? objects[1].toString() : "");
					returnList.add(VO);
				}
			}
		}
	    catch (Exception e)
	    {
	    	LOG.error("Exception raised in getAssignedSurveyUsersForWebMontringTeam", e);
		}
	    return returnList;
	}
	
	public List<GenericVO> getNotStartedUsersDetails(Long webMonitorUserId,Long leaderId)
	{
		LOG.info("Entered into getNotStartedUsersDetails method");
		
		 List<GenericVO> resultList = new ArrayList<GenericVO>();

		try
		{
			List<Long> finalUserIds = new ArrayList<Long>();
			
			List<Long> userIds = webMonitoringAssignedUsersDAO.getAssignedUsersIdsByWebMonitorId(webMonitorUserId);
			
			//SurveyUserRelationDAO
			
			List<Long> assignedUserIds = surveyUserRelationDAO.getAssignedUsersForLeader(leaderId);
			
			
			if(userIds != null && userIds.size() >0)
			{
				for(Long userId:assignedUserIds)
					if(userIds.contains(userId))
						finalUserIds.add(userId);
				
				
			}else
			{
				finalUserIds = userIds;
				
			}
			
			if(finalUserIds != null && finalUserIds.size() >0)
			{
				List<Long> activeUserIds = surveyDetailsInfoDAO.getPresentDayUserWiseSamplesCountByUserIds(finalUserIds,dateUtilService.getCurrentDateAndTime());
				
				List<Long> inActiveUserIds = new ArrayList<Long>();
				
				
				for(Long userId:userIds)
					if(!activeUserIds.contains(userId))
						inActiveUserIds.add(userId);
				
			
				if(inActiveUserIds != null && inActiveUserIds.size() >0)
				{
				
					List<Object[]> inActiveUsersDetails = 	surveyUserDAO.getUsersDetailsBySurveyUserIds(inActiveUserIds);
					
					
					for(Object[] obj:inActiveUsersDetails)
					{
						GenericVO vo = new GenericVO();
						
						vo.setName(obj[1].toString());
						vo.setId((Long)obj[0]);
						vo.setMobileNo(obj[2].toString());
						
						resultList.add(vo);
					}
				}
			}
			
			
		}catch(Exception e)
		{
			//e.printStackTrace();
	    	LOG.error("Exception raised in getNotStartedUsersDetails", e);
			
		}
		
		return resultList;
		
	}
	  /* get panchayats ready count and not ready count */
	  
	  public SurveyReportVO getPanchayatsStatusCountByConstituency(Long constituencyId)
	  {
		  SurveyReportVO vo = new SurveyReportVO();
		  Map<Long,List<Long>> panchayatsBoothsMap = new HashMap<Long, List<Long>>();
		  Map<Long,List<Long>> surveyPanchyatBoothsMap = new HashMap<Long, List<Long>>();
		try{
			
	 	    List<Object[]> panchayatsBooths = boothDAO.getPanchayatBoothDetails(constituencyId,IConstants.VOTER_DATA_PUBLICATION_ID);
	 	    List<Long> surveyBooths = surveyCompletedLocationsDAO.getBoothsOfConstituecyByStatusByPanchayat(constituencyId,9l);  // 9 is Booth scopeId  
	 	    if(surveyBooths != null && surveyBooths.size() > 0)
	 	    {
	 	    	List<Object[]> list = boothDAO.getPanchayatBooths(surveyBooths);
	 	    	if(list != null && list.size() > 0)
	 	    	{
	 	    		for(Object[] params : list)	
					{
						 List<Long> boothIds = surveyPanchyatBoothsMap.get((Long)params[0]);	
						if(boothIds == null)
						{
							boothIds = new ArrayList<Long>();
							surveyPanchyatBoothsMap.put((Long)params[0], boothIds);
						    
						}
						 boothIds.add((Long)params[2]);
					}	
	 	    	}
	 	    }
			if(panchayatsBooths != null && panchayatsBooths.size() > 0)
			{
				for(Object[] params : panchayatsBooths)	
				{
					 List<Long> boothIds1 = panchayatsBoothsMap.get((Long)params[0]);	
					if(boothIds1 == null)
					{
						boothIds1 = new ArrayList<Long>();
					    panchayatsBoothsMap.put((Long)params[0], boothIds1);
					    
					}
					 boothIds1.add((Long)params[2]);
				}
				Long completed = 0l;
				Long notCompleted = 0l;
				List<Long> completedPanchayatIds = new ArrayList<Long>();
				List<Long> notCompletedpanchayatIds = new ArrayList<Long>();
				for(Long panchayatId : panchayatsBoothsMap.keySet())
				{
					List<Long> booths = surveyPanchyatBoothsMap.get(panchayatId);
					List<Long> booths1 = panchayatsBoothsMap.get(panchayatId);
					if(booths1 != null && booths1.size() > 0)
					{
						
							if(booths != null && booths.size() == booths1.size())
							{
							 completed = completed + 1;
							 completedPanchayatIds.add(panchayatId);
							}
							else
							{
							  notCompleted	= notCompleted + 1;
							  notCompletedpanchayatIds.add(panchayatId);
							}
							
					}
						
					
				}
				vo.setCompleteIds(completedPanchayatIds);
				vo.setNotCompleteIds(notCompletedpanchayatIds);
				vo.setPanchayatCount(completed);
				vo.setPanchayatNotCompleteCount(notCompleted);
			}
			
			
			
		}
		catch (Exception e) {
			LOG.error("Exception raised in getPanchayatsReadyCountByConstituency", e);	
		}
		return vo;
	  }
	  
	  
	  
/* get  panchayats ready Data and not ready Data */
	  
	  public List<SurveyReportVO> getPanchayatsStatusWiseDataByConstituency(Long constituencyId,String status)
	  {
		 
		  List<SurveyReportVO> resultList = new ArrayList<SurveyReportVO>();
		 List<Long> panchayatsIds = new ArrayList<Long>();
		Map<Long,List<Long>> panchayatsBoothsMap = new HashMap<Long, List<Long>>();
		try{
			SurveyReportVO vo = getPanchayatsStatusCountByConstituency(constituencyId);
			if(status.equalsIgnoreCase("completed"))
				panchayatsIds = vo.getCompleteIds();
			else
				panchayatsIds = vo.getNotCompleteIds();
			
				 List<Object[]> list = boothDAO.getBoothsByPanhcayats(panchayatsIds,IConstants.VOTER_DATA_PUBLICATION_ID); 
				 if(list !=null && list.size() > 0)
				 {
					 for(Object[] params : list)	
						{
							 List<Long> boothIds1 = panchayatsBoothsMap.get((Long)params[0]);	
							if(boothIds1 == null)
							{
								boothIds1 = new ArrayList<Long>();
							    panchayatsBoothsMap.put((Long)params[0], boothIds1);
							    
							}
							 boothIds1.add((Long)params[2]);
						}
				 }
				 
				 
				 	for(Long panchayatId : panchayatsBoothsMap.keySet())
				 	{
				 		SurveyReportVO vo1 = new SurveyReportVO();
				 		List<Long> boothIds = panchayatsBoothsMap.get(panchayatId);
				 		vo1.setId(panchayatId);
				 		vo1.setName(panchayatDAO.getPanchayatNameById(panchayatId));
				 		Long totalVoters = boothPublicationVoterDAO.getTotalVotersForPanchayat(boothIds);
				 		vo1.setTotalVoters(totalVoters);
				 		Long hamletCount = surveyDetailsInfoDAO.getHamletCountByBooths(boothIds);
				 		Long casteCount =surveyDetailsInfoDAO.getCasteCountByBooths(boothIds);
				 		Long mobileNoCount =surveyDetailsInfoDAO.getMbileNoCountByBooths(boothIds);
				 		vo1.setHamletCount(hamletCount);
				 		vo1.setCasteCount(casteCount);
				 		vo1.setMobileNoCount(mobileNoCount);
						resultList.add(vo1);
				 	}
			

				
		}
		catch (Exception e) {
			LOG.error("Exception raised in getPanchayatsReadyCountByConstituency", e);	
		}
		return resultList;
	  }
	  	
public GenericVO getSurveyStatusBoothList(Long constituencyId){
		
		LOG.info("Entered into getSurveyStatusBoothList method in SurveyDetailsService class.");
		
		 GenericVO genericVO = new GenericVO();

		try
		{			
			Map<String , GenericVO> boothsMap = new LinkedHashMap<String , GenericVO>(); 
			List<Object[]> processingBooths  =  surveyDetailsInfoDAO.getProcecingBoothCountByConstId(constituencyId);
			List<Long> procesingBoothIds = new ArrayList<Long>();
			
			if(processingBooths != null && processingBooths.size()>0){		

				String procesingBoths = "";
					for (Object[] booth : processingBooths) {						
						procesingBoths = procesingBoths+","+booth[0].toString();						
						procesingBoothIds.add(booth[0] != null ? (Long) booth[0]:0L);
						
					}
				
				boothsMap.put("procesing", getBoothCountsIteration(procesingBoths,Long.valueOf(String.valueOf(processingBooths.size())),1L,"Booth Processing"));				
			}
			
			if(procesingBoothIds != null && procesingBoothIds.size()>0){
				
				List<Long> completedBooths  = surveyCompletedLocationsDetailsDAO.getSurveyCompletedCountByConstId(9L,procesingBoothIds);
								
				if(completedBooths != null && completedBooths.size()>0){	
					
					String compltdBoothIds1 = "";
					String procesingBoths = "";
					for (Long boothId : completedBooths) {	
						
						if(procesingBoothIds.contains(boothId)){
							procesingBoothIds.remove(boothId);
						}
						
						compltdBoothIds1 = compltdBoothIds1+","+boothId;	
						
					}
					
					for (Long boothId : procesingBoothIds) {
						procesingBoths = procesingBoths+","+boothId;
					}
					
					
						boothsMap.put("procesing", getBoothCountsIteration(procesingBoths,Long.valueOf(String.valueOf(procesingBoothIds.size())),1L,"Booth Processing"));
						boothsMap.put("complete", getBoothCountsIteration(compltdBoothIds1,Long.valueOf(String.valueOf(completedBooths.size())),2L,"Booth Complete"));
					
					//List<Long> WBCmpletBooths = webMonitorCompletedLocationsDetailsDAO.getSurveyWMCompletedCountByConstId(9L,completedBooths); // 9L means searching based on booths
				
						List<Long> WBCmpletBooths = webMonitorCompletedLocationsDetailsDAO.getWebMontringCount(constituencyId);
								
						if(WBCmpletBooths != null && WBCmpletBooths.size()>0){	
							String WBBooths = "";
							for (Long boothId : WBCmpletBooths) {	
								
								if(procesingBoothIds.contains(boothId)){
									procesingBoothIds.remove(boothId);
								}
								
								WBBooths = WBBooths+","+boothId;
							}
						boothsMap.put("procesing", getBoothCountsIteration(procesingBoths,Long.valueOf(String.valueOf(procesingBoothIds.size())),1L,"Booth Processing"));	
						boothsMap.put("WMCompleted", getBoothCountsIteration(WBBooths,Long.valueOf(String.valueOf(WBCmpletBooths.size())),3L,"Web Monitoring Completed"));
						
					}
					else{ // if WB completed booths not available
					
						WBCmpletBooths = webMonitorCompletedLocationsDetailsDAO.getWebMontringCount(constituencyId);
						
						if(WBCmpletBooths != null && WBCmpletBooths.size()>0){	
							String WBBooths = "";
							for (Long boothId : WBCmpletBooths) {
								if(procesingBoothIds.contains(boothId)){
									procesingBoothIds.remove(boothId);
								}
								WBBooths = WBBooths+","+boothId;
							}
							boothsMap.put("procesing", getBoothCountsIteration(procesingBoths,Long.valueOf(String.valueOf(procesingBoothIds.size())),1L,"Booth Processing"));
							boothsMap.put("WMCompleted", getBoothCountsIteration(WBBooths,Long.valueOf(String.valueOf(WBCmpletBooths.size())),3L,"Web Monitoring Completed"));
						}	
						else{
							boothsMap.put("WMCompleted", getBoothCountsIteration(null,0L,3L,"Web Monitoring Completed"));
						}					
					}
				}
				else{  // if completed booths not available
					
						boothsMap.put("complete", getBoothCountsIteration(null,0L,2L,"Booth Complete"));
						boothsMap.put("WMCompleted", getBoothCountsIteration(null,0L,3L,"Web Monitoring Completed"));
					
				}
			}
			else{

				// if processing booths not available
						boothsMap.put("procesing", getBoothCountsIteration(null,0L,1L,"Booth Processing"));
						boothsMap.put("complete", getBoothCountsIteration(null,0L,2L,"Booth Complete"));
						boothsMap.put("WMCompleted", getBoothCountsIteration(null,0L,3L,"Web Monitoring Completed"));
				
			}
			
			List<GenericVO> resultList = new ArrayList<GenericVO>();
			if(boothsMap != null && boothsMap.size()>0){
				
				for (String key : boothsMap.keySet()) {
					GenericVO vo = boothsMap.get(key);
					resultList.add(vo);
					
				}
				
				List<Long> verificationStartedList = surveyDetailsInfoDAO.getVerificationStartedBoothsDetailsByConstituencyId(constituencyId);
				List<Long> verificationCompletedList = surveyCompletedLocationsDetailsDAO.getVerificationCompletedBoothsDetailsByConstituencyId(constituencyId);
				
				genericVO.setVerificationCompletionList(verificationCompletedList);
				genericVO.setVerificationStartedList(verificationStartedList);
				
				List<Long> verificationProcessList = new ArrayList<Long>();
				
				for(Long startedId:verificationStartedList)
					if(!verificationCompletedList.contains(startedId))
						verificationProcessList.add(startedId);
						
				
				genericVO.setVerificationStartedList(verificationStartedList);
				genericVO.setVerificationProcessList(verificationProcessList);
				genericVO.setVerificationCompletionList(verificationCompletedList);
					
				
				
				//verificationCompletionCount
				genericVO.setGenericVOList(resultList);
			}
			
		}catch(Exception e)
		{
			e.printStackTrace();
			LOG.error("Exception raised in getSurveyStatusBoothList  method in SurveyDetailsService class.", e);
			
		}
		
		return genericVO;
		
	}


	private GenericVO getBoothCountsIteration(String boothIds,Long count,Long statusId,String boothType){
		GenericVO vo = new GenericVO();
		try {
			vo.setDesc(boothIds);
			vo.setCount(count);								
			vo.setId(statusId);
			vo.setName(boothType);
			
		} catch (Exception e) {
			e.printStackTrace();
			LOG.error("Exception raised in getBoothCountsIteration  method in SurveyDetailsService class.", e);
		}
		
		return vo;
	}
	
	@SuppressWarnings("unchecked")
	public  PanchayatHamletsCountVo  getSurveyDataCountForHamletsByPanchayats(Long panchayatId)
	{
		PanchayatHamletsCountVo responseVo= new PanchayatHamletsCountVo();
			List<Long> panchayatIds = new ArrayList<Long>();
			panchayatIds.add(panchayatId);
		List<?> details=surveyDetailsInfoDAO.getHamletCountBasedOnPanchayIds(panchayatIds);
		convertHamletDetailsIntoVo((List<Object[]>)details,responseVo);

		
		return responseVo;
	}
	
    /**
     * @author AnilKumar Ravula
     * 
     * @param details
     * @param responseVo
     * 
     */
	public   static void convertHamletDetailsIntoVo(List<Object[]> details, PanchayatHamletsCountVo responseVo) {
	
	
		
	
		
		List<PanchayatHamletsCountVo> responseSub= new ArrayList<PanchayatHamletsCountVo>();
		
		for (Object[] object : details) {
			
			long panchayatId=(Long)object[1];	
			
			
		
			PanchayatHamletsCountVo response=new PanchayatHamletsCountVo();			
			response.setPanchayatId(panchayatId);
			
		    int voIndex=responseSub.indexOf(response);
		     
		    
			if(responseSub.indexOf(voIndex)!=-1){
			
				response=responseSub.get(voIndex);
			}
			else{
				
               String panchayatName=object[0].toString();			
               response.setPanchayatName(panchayatName);
               response.setHamletsCountList(new ArrayList<HamletCountVo>());
               responseSub.add(response);
			}
					
			
			
			
			
			
			HamletCountVo countVO= new HamletCountVo();
			countVO.setHamletName(object[2].toString());
			countVO.setHamletId((Long)object[3]);
			countVO.setSurveyCount((Long)object[4]);
			response.getHamletsCountList().add(countVO);  //adding hamlet to panchayatVo
			
			
		}
		responseVo.setPanchayatsList(responseSub);
		
	}
	
	 public List<GenericVO> getUserForAssignedLeader(Long leaderId, Long userTypeId)
		{
			 List<GenericVO> usersList  = new ArrayList<GenericVO>();
			try
			{
				List<Object[]> usersDetails = surveyUserRelationDAO.getUserForAssignedUser(leaderId,userTypeId);
				
				for(Object[] obj:usersDetails)
				{
					GenericVO userVO = new GenericVO();
					
					userVO.setId((Long)obj[0]);
	                userVO.setName(obj[1].toString());
	                
	                usersList.add(userVO);
				}
				
			}catch(Exception e)
			{
				LOG.error("Exception raised in getUserForAssignedLeader method");
				e.printStackTrace();
			}
			return usersList;
		}
		 
	 public List<SurveyReportVO> getSurveyDetailsByBoothIds(List<Long> boothIds)
		{
				
			List<SurveyReportVO> resultList = new ArrayList<SurveyReportVO>();
			
			try{
				
				List<Object[]> totalVoters = boothPublicationVoterDAO.getTotalVoters(boothIds);

				if(totalVoters != null && totalVoters.size() > 0)
				{
					for(Object[] params : totalVoters)
					{
						 SurveyReportVO surveyReportVO = new SurveyReportVO();
						 surveyReportVO.setBoothId((Long)params[0]);
						 surveyReportVO.setPartNo(params[1].toString());
						 surveyReportVO.setTotalVoters((Long)params[2]);
						 resultList.add(surveyReportVO);						
					   	
					}
				}
				
				List<Object[]> mobileNos = surveyDetailsInfoDAO.getMobileNoCountByListOfBooths(boothIds);
				if(mobileNos != null && mobileNos.size() > 0)
				{
					 for(Object[] params : mobileNos)
					 {
						 SurveyReportVO result = getMatchedVO(resultList,(Long)params[0]);
						 if(result != null)
						 {							
							 result.setMobileNoCount((Long)params[1]);
							 
						 }						 
					 }				 
				}					
	           
				List<Object[]> casteCount = surveyDetailsInfoDAO.getCasteCountByListOfBooths(boothIds);
				if(casteCount != null && casteCount.size() > 0)
				{
					 for(Object[] params : casteCount)
					 {
						 SurveyReportVO result = getMatchedVO(resultList,(Long)params[0]);
						 if(result != null)
						 {							
							 result.setCasteCount((Long)params[1]);							
						 }						 
					 }				 
				}	
				List<Object[]> hamletCount = surveyDetailsInfoDAO.getHamletCountByListOfBooths(boothIds);
				if(hamletCount != null && hamletCount.size() > 0)
				{
					 for(Object[] params : hamletCount)
					 {
						 SurveyReportVO result = getMatchedVO(resultList,(Long)params[0]);
						 if(result != null)
						 {							
							 result.setHamletCount((Long)params[1]);							
						 }						 
					 }				 
				}	
				
				
			}
			catch(Exception e)
			{
				LOG.error("Exception raised in getSurveyDetailsByBoothIds method");
				e.printStackTrace();
			}
			return resultList;
		}
	 
	 public List<SurveyReportVO> getSurveyDetailsByConstituencyAndStatus(Long constituencyId,Long statusId,Long scopeId){
				
			List<SurveyReportVO> resultList = new ArrayList<SurveyReportVO>();
			List<Long> boothIds = new ArrayList<Long>();
			
			
			
			try{
				
				List<Long> thirdPartyStatusIdsList = new ArrayList<Long>();
				
				thirdPartyStatusIdsList.add(IConstants.TP_PROCESS_STATUS_ID);
				thirdPartyStatusIdsList.add(IConstants.TP_COMPLETED_STATUS_ID);
				thirdPartyStatusIdsList.add(IConstants.TP_WM_PROCESS_STATUS_ID);
				thirdPartyStatusIdsList.add(IConstants.TP_WM_COMPLETED_STATUS_ID);
				thirdPartyStatusIdsList.add(IConstants.READY_FOR_REVIEW);
				
				
				if(thirdPartyStatusIdsList.contains(statusId))
				   return getThirdPartyRelatedBoothsDetails(scopeId,constituencyId,statusId,thirdPartyStatusIdsList);
				
				if(statusId.equals(IConstants.DC_PROCESS_STATUS_ID))
				{
					
					List<Long> processingIds =  surveyDetailsInfoDAO.getBoothsInProcessByConstituencyId(constituencyId);
					List<Long> completedIds  = surveyCompletedLocationsDAO.getCompletedBoothsIdsByConstituencyId(constituencyId);
					
					 processingIds.removeAll(completedIds);
					 boothIds = processingIds;

					
				}else if(statusId.equals(IConstants.TP_READY_STATUS_ID))
				{
					
					List<Long> dvCompletdBooths = surveyCompletedLocationsDAO.getBoothsOfConstituecyByStatus(constituencyId,IConstants.DV_COMPLETED_STATUS_ID,scopeId);
					
					List<Long> tpProcessBooths = surveyCompletedLocationsDAO.getBoothsOfConstituecyByStatus(constituencyId,IConstants.TP_PROCESS_STATUS_ID,scopeId);
					
					List<Long> tpCompletedBooths = surveyCompletedLocationsDAO.getBoothsOfConstituecyByStatus(constituencyId,IConstants.TP_PROCESS_STATUS_ID,scopeId);

                   
					if(tpProcessBooths != null && tpProcessBooths.size() >0)
						dvCompletdBooths.removeAll(tpProcessBooths);
					
					if(tpCompletedBooths != null && tpCompletedBooths.size() >0)
					dvCompletdBooths.removeAll(tpCompletedBooths);
					
					boothIds = dvCompletdBooths;

					
				}
				else if(statusId == 0L)
				{
					
					List<Long> dvProcessBooths = surveyCompletedLocationsDAO.getVerificationProcessBoothsByConstituencyId(constituencyId);
					
					List<Long> dvCompletedBooths = surveyCompletedLocationsDAO.getCompletedBoothsDetailsByStatusIdAndConstituencyId(constituencyId,IConstants.DV_COMPLETED_STATUS_ID);
					
					dvProcessBooths.removeAll(dvCompletedBooths);
					boothIds = dvProcessBooths;

					
				}else
				{
					List<Long> boothObjs =  surveyCompletedLocationsDAO.getBoothsOfConstituecyByStatus(constituencyId, statusId, scopeId);
					if(boothObjs!=null && boothObjs.size()>0){
						for(Long obj:boothObjs){
							boothIds.add(obj);
						}
						
					}
				}
				
				List<Object[]> totalVoters = boothPublicationVoterDAO.getTotalVoters(boothIds);

				if(totalVoters != null && totalVoters.size() > 0)
				{
					for(Object[] params : totalVoters)
					{
						 SurveyReportVO surveyReportVO = new SurveyReportVO();
						 surveyReportVO.setBoothId((Long)params[0]);
						 surveyReportVO.setPartNo(params[1].toString());
						 surveyReportVO.setTotalVoters((Long)params[2]);
						 resultList.add(surveyReportVO);						
					   	
					}
				}
				
				List<Object[]> mobileNos = surveyDetailsInfoDAO.getMobileNoCountByListOfBooths(boothIds);
				if(mobileNos != null && mobileNos.size() > 0)
				{
					 for(Object[] params : mobileNos)
					 {
						 SurveyReportVO result = getMatchedVO(resultList,(Long)params[0]);
						 if(result != null)
						 {							
							 result.setMobileNoCount((Long)params[1]);
							 
						 }						 
					 }				 
				}					
	           
				List<Object[]> casteCount = surveyDetailsInfoDAO.getCasteCountByListOfBooths(boothIds);
				if(casteCount != null && casteCount.size() > 0)
				{
					 for(Object[] params : casteCount)
					 {
						 SurveyReportVO result = getMatchedVO(resultList,(Long)params[0]);
						 if(result != null)
						 {							
							 result.setCasteCount((Long)params[1]);							
						 }						 
					 }				 
				}	
				List<Object[]> hamletCount = surveyDetailsInfoDAO.getHamletCountByListOfBooths(boothIds);
				if(hamletCount != null && hamletCount.size() > 0)
				{
					 for(Object[] params : hamletCount)
					 {
						 SurveyReportVO result = getMatchedVO(resultList,(Long)params[0]);
						 if(result != null)
						 {							
							 result.setHamletCount((Long)params[1]);							
						 }						 
					 }				 
				}	
				
				
				if(statusId == 3l){
					List<Object[]> casteStatusCount = surveyCallStatusDAO.getCasteStatusForBooth(boothIds);
					if(casteStatusCount!=null && casteStatusCount.size()>0){
						for(Object[] obj:casteStatusCount){
							SurveyReportVO result = getMatchedVO(resultList, Long.valueOf(obj[0].toString()));
							if(result != null){
								if(obj[1].toString().equalsIgnoreCase("y")){
									result.setCasteMatchedCount(Long.valueOf(obj[2].toString()));
								}else{
									result.setCasteNotMatchedCount(Long.valueOf(obj[2].toString()));
								}
							}
						}
					}
					
					
					if(resultList!=null && resultList.size()>0){
						for(SurveyReportVO temp:resultList){
							Long matchedCount = 0l;
							Long unMatchedCount = 0l;
							
								if(temp.getCasteMatchedCount()!=null){matchedCount = temp.getCasteMatchedCount();}
								if(temp.getCasteNotMatchedCount()!=null){unMatchedCount = temp.getCasteNotMatchedCount();}
								Long total = matchedCount + unMatchedCount;
								if(total != null && total > 0){
									temp.setCasteErrorPercent(new BigDecimal(unMatchedCount*(100.0)/total).setScale(2, BigDecimal.ROUND_HALF_UP).toString());
								}
							
						}
					}
					
				}
				
				
				
				
				
			}
			catch(Exception e)
			{
				LOG.error("Exception raised in getSurveyDetailsByBoothIds method");
				e.printStackTrace();
			}
			return resultList;
		}
	 
	 public List<SurveyReportVO> getThirdPartyRelatedBoothsDetails(Long scopeId,Long constituencyId,Long statusId,List<Long> thirdPartyStatusIdsList)
	 {
		 LOG.info("Entered into the getThirdPartyRelatedBoothsDetails service method");
		 List<SurveyReportVO> resultList = new ArrayList<SurveyReportVO>();
		 try
		 {
			List<Object[]> casteList =  surveyDetailsInfoDAO.getBoothWiseCollectedDetailsForConstituency(constituencyId,IConstants.THIRD_PARTY_ROLE_ID,"caste",statusId);
			
			List<Object[]> hamletList =  surveyDetailsInfoDAO.getBoothWiseCollectedDetailsForConstituency(constituencyId,IConstants.THIRD_PARTY_ROLE_ID,"hamlet",statusId);
			
			List<Object[]> wardList =  surveyDetailsInfoDAO.getBoothWiseCollectedDetailsForConstituency(constituencyId,IConstants.THIRD_PARTY_ROLE_ID,"ward",statusId);

			
			List<Object[]> mobileNumberList =  surveyDetailsInfoDAO.getBoothWiseCollectedDetailsForConstituency(constituencyId,IConstants.THIRD_PARTY_ROLE_ID,"mobileNumber",statusId);
			
			
			Set<Long> totalBoothsList = new HashSet<Long>();
			
			Map<Long,Long> casteMap = new HashMap<Long, Long>();
			Map<Long,Long> hamletMap = new HashMap<Long, Long>();
			Map<Long,Long> wardMap = new HashMap<Long, Long>();
			Map<Long,Long> mobileNumbersMap = new HashMap<Long, Long>();
			Map<Long,List<SurveyReportVO>> statusMap = new HashMap<Long, List<SurveyReportVO>>();
			
			for(Object[] obj:casteList)
			{
				casteMap.put((Long)obj[1], (Long)obj[0]);
				totalBoothsList.add((Long)obj[1]);
			}
			
			for(Object[] obj:hamletList)
			{
				hamletMap.put((Long)obj[1], (Long)obj[0]);
				totalBoothsList.add((Long)obj[1]);
			}
			
			for(Object[] obj:wardList)
			{
				wardMap.put((Long)obj[1], (Long)obj[0]);
				totalBoothsList.add((Long)obj[1]);
			}
			
			for(Object[] obj:mobileNumberList)
			{
				mobileNumbersMap.put((Long)obj[1], (Long)obj[0]);
				totalBoothsList.add((Long)obj[1]);
			}
			
			if(statusId == IConstants.TP_WM_PROCESS_STATUS_ID || statusId == IConstants.TP_WM_COMPLETED_STATUS_ID){
				
			List<Long> boothIds =  surveyCompletedLocationsDAO.getBoothsOfConstituecyByStatus(constituencyId, statusId, scopeId);
			List<Object[]> statusCountList = surveyFinalDataDAO.getThirdPartyStatusWithBooths(boothIds);

			Set<Long> statusIds = new HashSet<Long>();
			List<SurveyReportVO> statusCount = new ArrayList<SurveyReportVO>();
			List<Object[]> ids = surveyWmThirdPartyStatusDAO.getStatusTypes();
			
			for(Object[] obj:ids)
			{
				if(statusIds.add((Long)obj[0]))
				{
				SurveyReportVO vo = new SurveyReportVO();
				vo.setStatusId((Long)obj[0]);
				vo.setStatus(obj[1].toString());
				vo.setCount(0l);
				statusCount.add(vo);
				}				
			}
			
			for(Object[] obj:statusCountList)
			{				
				statusMap.put((Long)obj[0], statusCount);
			}			
			
			for(Object[] obj:statusCountList)
			{			
				
				List<SurveyReportVO> statusLst = statusMap.get((Long)obj[0]);
				SurveyReportVO vo= getMatchedStatusVO(statusLst,(Long)obj[1]);
				if(vo != null){
					vo.setCount((Long)obj[2]);
				}
				totalBoothsList.add((Long)obj[0]);
			}			
			
			}
			if(statusId.equals(IConstants.TP_PROCESS_STATUS_ID))
			{
				List<Long> thirdPartyBooths = surveyCompletedLocationsDAO.getAllThirdPartyRelatedBoothByConstituencyId(constituencyId,thirdPartyStatusIdsList);
				totalBoothsList.removeAll(thirdPartyBooths);
			}
			 
			
			List<Object[] > thirdPartyErrorCountList = surveyFinalDataDAO.getBoothWiseErrorCountForAConstituency(constituencyId);
			
			//List<Object[]> thirdPartyErrorCountList = surveyCallStatusDAO.getBoothWiseErrorCountForConstituencyByUsertypeId(constituencyId,IConstants.THIRD_PARTY_ROLE_ID);
			
			Map<Long,Long> errorCountMap = new HashMap<Long, Long>();
			
			for(Object[] obj:thirdPartyErrorCountList)
				errorCountMap.put((Long)obj[1], (Long)obj[0]);
			
			List<Object[]> totalVoterList = boothPublicationVoterDAO.getTotalVotersForConstituencyByBoothWise(constituencyId);
			
			Map<Long,Long> totalVotersMap = new HashMap<Long, Long>();
			if(totalVoterList != null && totalVoterList.size() > 0)
			{
			for(Object[] obj:totalVoterList)
				totalVotersMap.put((Long)obj[0], (Long)obj[2]);
			}
			List<Object[]> boothDetails = boothDAO.getBoothDetailsByBoothIds(totalBoothsList);
			
			Map<Long,String> boothDetailsMap = new HashMap<Long, String>();
				
			for(Object[] obj:boothDetails)
				boothDetailsMap.put((Long)obj[0], obj[2].toString());
				
			
			
			for(Long boothId:totalBoothsList)
			{
				SurveyReportVO boothVO = new SurveyReportVO();
				List<SurveyReportVO> statusList  = new ArrayList<SurveyReportVO>();
				boothVO.setTotalVoters(totalVotersMap.get(boothId) != null ? totalVotersMap.get(boothId) : 0L);
				boothVO.setMobileNoCount(mobileNumbersMap.get(boothId) != null ? mobileNumbersMap.get(boothId) : 0L);
				boothVO.setCasteCount(casteMap.get(boothId) != null ? casteMap.get(boothId) : 0L);
				
				if(statusMap != null && statusMap.size() > 0)
				{
					statusList = statusMap.get(boothId != null ? boothId :0L);
					boothVO.setSubList(statusList);
				}
				if(hamletMap.get(boothId) != null)
					 boothVO.setHamletCount(hamletMap.get(boothId) != null ? hamletMap.get(boothId) : 0L);
				else if(wardMap.get(boothId) != null)
					 boothVO.setHamletCount(wardMap.get(boothId) != null ? wardMap.get(boothId) : 0L);
				
				boothVO.setBoothId(boothId);
				boothVO.setPartNo(boothDetailsMap.get(boothId));
				
				resultList.add(boothVO);
			}
			
			 
		 }catch(Exception e)
		 {
			 e.printStackTrace();
				LOG.error("Exception raised in getThirdPartyRelatedBoothsDetails service method");
		 }
		 
		 if(resultList != null && resultList.size() >0)
			 resultList.get(0).setForThirdParty(true);
		 
		 return resultList;
	 }
	 
	 public SurveyReportVO getMatchedStatusVO(List<SurveyReportVO> list,Long statusId){
			if(list!=null && list.size()>0 && statusId!=null){
				for(SurveyReportVO vo:list){
					if(vo.getStatusId().longValue() == statusId.longValue()){
						return vo;
					}
				}
			}
			return null;
		}
	 
	 
	  public String roundTo2DigitsFloatValue(Float number){
		  
		  Log.debug("Entered into the roundTo2DigitsFloatValue service method");
		  
		  String result = "";
		  try
		  {
			  
			
			NumberFormat f = NumberFormat.getInstance(Locale.ENGLISH);  
			f.setMaximumFractionDigits(2);  
			f.setMinimumFractionDigits(2);
			
			result =  f.format(number);
		  }catch(Exception e)
		  {
			  Log.error("Exception raised in roundTo2DigitsFloatValue service method");
			  e.printStackTrace();
		  }
		  return result;
	  }

	 
	 public SurveyReportVO getMatchedVO(List<SurveyReportVO> resultList,Long boothId)
		{
		
			try{
				if(resultList == null)
					return null;
				for(SurveyReportVO vo : resultList)
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
	public DcDvCollectedDataVO getMatchedConstituencyVo(List<DcDvCollectedDataVO> resultList,Long constituencyId)
	{
		try{
			if(resultList == null)
				return null;
			for(DcDvCollectedDataVO vo : resultList)
				if(vo.getId().longValue() == constituencyId)
					return vo;
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
		
	
	public List<VerificationCompVO> checkForVerifierData(List<Long> boothIds)
	{
		List<VerificationCompVO> returnList = null;
		try 
		{
			List<Object[]> castesList = casteStateDAO.getAllCasteDetailsForVoters(1l);
			if(castesList != null && castesList.size() > 0)
			{
				Map<Long,Map<Long,String>> dcBoothMap = null;// booth wise Data Collector Caste Collected 
				Map<Long,Map<Long,String>> dvBoothMap = null;// booth wise Data Verifier Caste Collected
				Map<Long,Map<Long,String>> wmBoothMap = null;// booth Wise Web Moniter Caste Collected
				Map<Long,Map<Long,String>> dvWmBoothMap = null;// booth Wise Web Moniter Caste Collected
				Map<Long,Map<Long,String>> dcBoothDatesMap = null;// Day wise booth Wise Data Collector 
				Map<Long,Map<Long,String>> dvBoothDatesMap = null;// Day wise booth Wise Data Verified 
				Map<Long,VerificationCompVO> boothWiseMap = null;// booth wise total recoreds
				Map<Long,Map<Long,Long>> dvStatusBoothMap = null;// booth wise Data Verifier Caste Collected
				Map<Long,String> dcMap = null;// Map<voterId,Caste>
				Map<Long,String> dvMap = null;//Map<voterId,Caste>
				Map<Long,String> wmMap = null;//Map<voterId,Caste>
				Map<Long,String> dvWmMap = null;//Map<voterId,Caste>
				Map<Long,String> casteMap = new HashMap<Long, String>();//Map<casteId,CasteName>
				Map<Long,Map<Long,String>> dcWmCollectedMap = null;// Day wise booth Wise Data Collector 
				Map<Long,String> dcDatesMap = null;
				Map<Long,String> dvDatesMap = null;
				Map<Long,String> dcWmMap = null;
				Map<Long,String> usersMap =null;
				Map<Long,String> verifierMap = null;
				Map<Long,String> usersDateMap =null;
				Map<Long,String> verifierDateMap = null;
				Map<Long,Long> dvStatusMap = null;
				Map<Long,String> mobileNoMap = null;
				Map<Long,Map<Long,String>> wmResultBoothMap = null;// booth Wise Web Moniter Caste Collected
				for (Object[] objects : castesList)
				{
					casteMap.put((Long)objects[0], objects[1].toString());
				}
				List<Object[]> result = surveyDetailsInfoDAO.getBoothWiseDcAndDvDetails(boothIds);
				if(result != null && result.size() > 0)
				{
					dcBoothMap = new HashMap<Long, Map<Long,String>>();
					dvBoothMap = new HashMap<Long, Map<Long,String>>();
					dvStatusBoothMap = new HashMap<Long, Map<Long,Long>>();
					dcBoothDatesMap = new HashMap<Long, Map<Long,String>>();
					dvBoothDatesMap = new HashMap<Long, Map<Long,String>>();
					List<GenericVO> userWiseList = new ArrayList<GenericVO>();
					mobileNoMap = new HashMap<Long, String>();
					for (Object[] parms	: result)
					{
						dcMap = dcBoothMap.get((Long)parms[4]);
						if(dcMap == null)
						{
							dcMap = new HashMap<Long, String>();
							dvMap = new HashMap<Long, String>();
							dvStatusMap = new HashMap<Long, Long>();
							dcDatesMap = new HashMap<Long, String>();
							dvDatesMap = new HashMap<Long, String>();
							dcBoothMap.put((Long)parms[4], dcMap);
							dvBoothMap.put((Long)parms[4], dvMap);
							dvStatusBoothMap.put((Long)parms[4], dvStatusMap);
							dcBoothDatesMap.put((Long)parms[4], dcDatesMap);
							dvBoothDatesMap.put((Long)parms[4], dvDatesMap);
						}
						GenericVO VO = new GenericVO();
						if((Long)parms[3] != null)
						{
							if((Long)parms[3] == 1)
							{
								dcMap.put((Long)parms[1], casteMap.get((Long)parms[2]));
								dcDatesMap.put((Long)parms[1], parms[7].toString());
								if(parms[9] != null)
								mobileNoMap.put((Long)parms[1], parms[9].toString());
							}
							else
							{
								dvDatesMap.put((Long)parms[1], parms[7].toString());
								dvMap.put((Long)parms[1], casteMap.get((Long)parms[2]));
								
								if(parms[8] != null)
								{
									dvStatusMap.put((Long)parms[1],Long.valueOf(parms[8].toString()));
								}
								
							}
						}
						userWiseList.add(VO);
					}
				}
				
				List<Object[]> wmDetails = surveyCallStatusDAO.getBoothWiseWmCasteUpdationDetails(boothIds);
				if(wmDetails != null && wmDetails.size() > 0)
				{
					wmBoothMap = new HashMap<Long, Map<Long,String>>();
					for (Object[] parms : wmDetails)
					{
						wmMap = wmBoothMap.get((Long)parms[3]);
						if(wmMap == null)
						{
							wmMap = new HashMap<Long, String>();
							wmBoothMap.put((Long)parms[3], wmMap);
							
						}
						if(parms[2] != null)
						{
							dcMap = dcBoothMap.get((Long)parms[3]);
							if(parms[2].toString().trim().equalsIgnoreCase("N"))
							{
								wmMap.put((Long)parms[0], casteMap.get((Long)parms[1]));
							}
							else
							{
								wmMap.put((Long)parms[0], dcMap.get((Long)parms[0]));
							}
						}
						
					}
				}
				
				
				List<Object[]> dvWmDetails = surveyCallStatusDAO.getBoothWiseDvWmCasteUpdationDetails(boothIds);
				if(dvWmDetails != null && dvWmDetails.size() > 0)
				{
					dvWmBoothMap = new HashMap<Long, Map<Long,String>>();
					for (Object[] parms : dvWmDetails)
					{
						dvWmMap = dvWmBoothMap.get((Long)parms[3]);
						if(dvWmMap == null)
						{
							dvWmMap = new HashMap<Long, String>();
							dvWmBoothMap.put((Long)parms[3], dvWmMap);
							
						}
						if(parms[2] != null)
						{
							 
							Map<Long,String> dvMapForBooth = dvBoothMap.get((Long)parms[3]);
							if(parms[2].toString().trim().equalsIgnoreCase("N"))
							{
								dvWmMap.put((Long)parms[0], casteMap.get((Long)parms[1]));
							}
							else
							{
								dvWmMap.put((Long)parms[0], dvMapForBooth.get((Long)parms[0]));
							}
						}
						
					}
				}
				if(boothIds != null && boothIds.size() > 0)
				{
					wmResultBoothMap = new HashMap<Long, Map<Long,String>>();
					for (Long boothId : boothIds)
					{
						if(wmBoothMap !=null && wmBoothMap.size() > 0)
						{
							Map<Long,String>  resultMap = checkForDcWmWithDvWm(wmBoothMap.get(boothId),dvWmBoothMap.get(boothId));
							wmResultBoothMap.put(boothId, resultMap);
						}
						
						
					}
					
				}
				
				if(boothIds != null && boothIds.size() > 0)
				{
					dcWmCollectedMap = new HashMap<Long, Map<Long,String>>();
					for(Long boothId : boothIds)
					{
						if(dcBoothMap.get(boothId) != null && dcBoothMap.get(boothId).size() > 0)
						{
							Map<Long,String>  resultMap = new HashMap<Long, String>();
							
							if(wmResultBoothMap != null && wmResultBoothMap.size() > 0)
							{
								if(wmResultBoothMap.get(boothId) != null && wmResultBoothMap.size() > 0)
								{
									checkForDcWithWm(dcBoothMap.get(boothId),wmResultBoothMap.get(boothId),resultMap);
									dcWmCollectedMap.put(boothId, resultMap);
								}
								else
								{
									dcWmCollectedMap.put(boothId, dcBoothMap.get(boothId));
								}
							}
							else
							{
								dcWmCollectedMap.put(boothId, dcBoothMap.get(boothId));
							}
							
						}
						
						
						
					}
				}
				
				
				List<Object[]> userList = surveyDetailsInfoDAO.getBoothWiseUser(boothIds,1l);
				List<Object[]> verifiersList = surveyDetailsInfoDAO.getBoothWiseUser(boothIds,4l);
				if(userList != null && userList.size() > 0)
				{
					usersMap = new HashMap<Long, String>();
					usersDateMap = new HashMap<Long, String>();
					for (Object[] objects : userList) 
					{
						String name = usersMap.get((Long)objects[0]);
						if(name == null)
						{
							usersMap.put((Long)objects[0], objects[2].toString());
							usersDateMap.put((Long)objects[0], objects[4].toString());
						}
					}
				}
				if(userList != null && userList.size() > 0)
				{
					verifierMap = new HashMap<Long, String>();
					verifierDateMap = new HashMap<Long, String>();
					for (Object[] objects : verifiersList)
					{
						String name = verifierMap.get((Long)objects[0]);
						if(name == null)
						{
							verifierMap.put((Long)objects[0], objects[2].toString());
							verifierDateMap.put((Long)objects[0], objects[4].toString());
						}
					}
				}
				List<Object[]> voterDetails = boothPublicationVoterDAO.getVoterDetailsByBoothID(boothIds);
				if(voterDetails != null && voterDetails.size() > 0)
				{
					boothWiseMap = new HashMap<Long, VerificationCompVO>();
					List<VerificationCompVO> matchedList  = null;
					List<VerificationCompVO> unMatchedList = null;
					List<VerificationCompVO> notVerifiedList = null;
					String surveyUser = null;
					String verifierUser = null;
					String dcDate = null;
					String dvDate = null;
					Integer collectdCount = 0;
					Integer updatedCount = 0;
					Integer verifedCount = 0;
					Integer notIdentifedCount = 0;
					for (Object[] parms : voterDetails)
					{
						 dcMap = dcBoothMap.get((Long)parms[4]);
						 if(dcMap != null && dcMap.size() > 0)
						 {
							 VerificationCompVO subVO = boothWiseMap.get((Long)parms[4]);
								if(subVO == null)
								{
									 subVO = new VerificationCompVO();
									 boothWiseMap.put((Long)parms[4], subVO);
									 matchedList = new ArrayList<VerificationCompVO>();
									 unMatchedList = new ArrayList<VerificationCompVO>();
									 notVerifiedList = new ArrayList<VerificationCompVO>(); 
									 if(dvBoothMap != null && dvBoothMap.size() > 0)
									 dvMap = dvBoothMap.get((Long)parms[4]);
									 if(wmResultBoothMap != null && wmResultBoothMap.size() > 0)
									 wmMap = wmResultBoothMap.get((Long)parms[4]);
									 dcDatesMap = dcBoothDatesMap.get((Long)parms[4]);
									 dvDatesMap = dcBoothDatesMap.get((Long)parms[4]);
									 dcWmMap =  dcWmCollectedMap.get((Long)parms[4]);
									 surveyUser = usersMap.get((Long)parms[4]);
									 verifierUser = verifierMap.get((Long)parms[4]);
									 dcDate = usersDateMap.get((Long)parms[4]);
									 dvDate = verifierDateMap.get((Long)parms[4]);
									 dvStatusMap = dvStatusBoothMap.get((Long)parms[4]);
									 collectdCount = 0;
									 updatedCount = 0;
									 verifedCount = 0;
									 notIdentifedCount = 0;

								}
									VerificationCompVO VO = new VerificationCompVO();
									VO.setVoterCardNO(parms[2] != null ? parms[2].toString() : "");
									VO.setVoterName(parms[1] != null ? parms[1].toString() : "");
									VO.setVoterId(parms[0] != null ? (Long)parms[0]: null  );
									VO.setHouseNo(parms[3] != null ? parms[3].toString() : "");
									VO.setPartNo(parms[6] != null ? parms[6].toString() : "");
									VO.setPanchayatName(parms[7] != null ? parms[7].toString() : "");
									if(mobileNoMap.get((Long)parms[0]) != null)
									{
										VO.setMobileNO(mobileNoMap.get((Long)parms[0]));
									}
									
									if(dvStatusMap != null && dvStatusMap.size() > 0)
									{
										Long statusId = dvStatusMap.get((Long)parms[0]);
										if(statusId != null)
										{
											if(statusId.longValue() == 1l) // collected
											{
												collectdCount = collectdCount + 1;
												VO.setVerifierStatus("COLLECTED");
											}
											else if(statusId.longValue() == 2l) // updated
											{
												updatedCount = updatedCount + 1;
												VO.setVerifierStatus("UPDATED");
											}
											else// verified
											{
												verifedCount = verifedCount + 1;
												VO.setVerifierStatus("VERIFIED");
											}
										}
										else
										{
											notIdentifedCount = notIdentifedCount + 1;
											VO.setVerifierStatus("NOT IDENTIFED");
										}
										
									}
									if(dcMap != null && dcMap.size() > 0)
									{
										VO.setDcCaste(dcMap.get((Long)parms[0]) != null ? dcMap.get((Long)parms[0]) : "-");
									}
									else
									{
										VO.setDcCaste("-");
									}
									if(dvMap != null && dvMap.size() > 0)
									{
										VO.setDvCaste(dvMap.get((Long)parms[0]) != null ? dvMap.get((Long)parms[0]) : "-");
									}
									else
									{
										VO.setDvCaste( "-");
									}
									if(wmMap != null && wmMap.size() > 0)
									{
										VO.setWmCaste(wmMap.get((Long)parms[0]) != null ? wmMap.get((Long)parms[0]) : "-");
									}
									else
									{
										VO.setWmCaste("-");
									}
									
									VO.setRelativeName(parms[5] != null ? parms[5].toString() : "");
									//VO.setDate(dcDate);
									//VO.setVerifierDate(dvDate);
									//VO.setSurveyUser(surveyUser);
									//VO.setVerifierUser(verifierUser);
									if(dcWmMap != null && dcWmMap.size() > 0)
									{
										if(dvMap.get((Long)parms[0] ) != null)
										{
											if(dcWmMap.get((Long)parms[0]) != null && dvMap.get((Long)parms[0]) != null)
											{
												if(dcWmMap.get((Long)parms[0]) .equalsIgnoreCase(dvMap.get((Long)parms[0])))
												{
													matchedList.add(VO);
												}
												/*else if(dcMap.get((Long)parms[0]).equalsIgnoreCase(wmMap.get((Long)parms[0])))
												{
													matchedList.add(VO);
												}	
												else if(dvMap.get((Long)parms[0]) == null )
												{
													notVerifiedList.add(VO);
												}*/
												else
												{
													unMatchedList.add(VO);
												}

											}
											
										}
										else
										{
											/*if(wmMap != null)
											{
												if(dcMap.get((Long)parms[0]).equalsIgnoreCase(wmMap.get((Long)parms[0])))
												{
													matchedList.add(VO);
												}
												else if(dcMap.get((Long)parms[0]) != null && wmMap.get((Long)parms[0]) != null)
												{
													unMatchedList.add(VO);
												}
												else
												{
													notVerifiedList.add(VO);
												}
											}
											else
											{*/
												notVerifiedList.add(VO);
											//}
											
											
										}
									}
									else
									{
										notVerifiedList.add(VO);
									}
								subVO.setBoothId((Long)parms[4]);
								subVO.setDate(dcDate);
								subVO.setVerifierDate(dvDate);
								subVO.setSurveyUser(surveyUser);
								subVO.setVerifierUser(verifierUser);
								subVO.setMatchedList(matchedList);
								subVO.setUnMatchedList(unMatchedList);
								subVO.setNotVerifiedList(notVerifiedList);
								subVO.setTotalCount(unMatchedList.size() + matchedList.size() + notVerifiedList.size());
								subVO.setUnMatchedCount(unMatchedList.size());
								subVO.setMatchedCount(matchedList.size());
								subVO.setCollectedCount(collectdCount);
								subVO.setUpdatedCount(updatedCount);
								subVO.setVerifieCount(verifedCount);
								subVO.setNotIdentifedCount(notIdentifedCount);
								subVO.setNotVerifiedCount(notVerifiedList.size());
						 }
						
					}
					if(boothWiseMap != null && boothWiseMap.size() > 0)
					{
						List<Long> booths = new ArrayList<Long>(boothWiseMap.keySet());
						returnList = new ArrayList<VerificationCompVO>();
						for (Long boothId : booths)
						{
							returnList.add(boothWiseMap.get(boothId));
						}
					}
				}
			}
			
		} 
		catch (Exception e)
		{
			LOG.error("Exception raised in checkForVerifierData", e);
		}
		return returnList;
	}
	
	
	public Map<Long,String> checkForDcWmWithDvWm(Map<Long,String> wmMap,Map<Long,String> dvWmMap)
	{
		Map<Long,String> resultMap = new HashMap<Long, String>();
		if(dvWmMap != null && dvWmMap.size() > 0)
		{
			if(wmMap != null && wmMap.size() > 0)
			{
				for(Long voterId : dvWmMap.keySet())
				{
					wmMap.put(voterId, dvWmMap.get(voterId));
				}
				resultMap = wmMap;
			}
			else
			{
				resultMap = dvWmMap;
			}
		}
		else
		{
			resultMap = wmMap;
		}
		return resultMap;
	}
	
	public void checkForDcWithWm(Map<Long,String> dcMap,Map<Long,String> wmMap,Map<Long,String> resultMap)
	{
		try
		{
			List<Long> dcCollectedVoters = new ArrayList<Long>(dcMap.keySet());
			if(dcCollectedVoters != null && dcCollectedVoters.size() > 0)
			{
				for (Long dcVoterId : dcCollectedVoters)
				{
					if(wmMap != null)
					{
						if(wmMap.get(dcVoterId) != null)
						{
							resultMap.put(dcVoterId, wmMap.get(dcVoterId));
						}
						else
						{
							resultMap.put(dcVoterId, dcMap.get(dcVoterId));
						}
					}
					else
					{
						resultMap.put(dcVoterId, dcMap.get(dcVoterId));
					}
					
				}
			}
		} 
		catch (Exception e)
		{
			LOG.error("Exception raised in checkForDcWithWm", e);
		}
	}
	
	public List<GenericVO> getBoothsInCallStatus(Long constituencyId)
	{
		List<GenericVO> resultList = new ArrayList<GenericVO>();
		try{
			List<Object[]> list = surveyCallStatusDAO.getBoothsByConstituency(constituencyId);
			if(list != null && list.size() > 0)
			{
				for(Object[] params : list)
				resultList.add(new GenericVO((Long)params[0],params[1].toString()));
			}
		}
		catch (Exception e) {
			LOG.error("Exception raised in getBoothsInCallStatus", e);
		}
		return resultList;
	}
	public List<GenericVO> getBoothsInSurveyDetailsInfo(Long constituencyId)
	{
		List<GenericVO> resultList = new ArrayList<GenericVO>();
		try{
			List<Object[]> list = surveyDetailsInfoDAO.getBooths(constituencyId,4l);
			if(list != null && list.size() > 0)
			{
				for(Object[] params : list)
				resultList.add(new GenericVO((Long)params[0],params[1].toString()));
			}
		}
		catch (Exception e) {
			LOG.error("Exception raised in getBoothsInCallStatus", e);
		}
		return resultList;
	}	
	public List<VerificationCompVO> checkForWebMonitorData(Long boothId)
	{
		List<VerificationCompVO> returnList = null;
		try
		{
			List<Object[]> castesList = casteStateDAO.getAllCasteDetailsForVoters(1l);
			if(castesList != null && castesList.size() > 0)
			{
				Map<Long,String> casteMap = new HashMap<Long, String>();
				for (Object[] objects : castesList)
				{
					casteMap.put((Long)objects[0], objects[1].toString());
				}
				Map<Long,String> dcMap = null;
				Map<Long,String> dcDatesMap = null;
				Map<Long,String> wmMap = null;
				Map<Long,String> usersMap = null;
				List<Object[]> dcDetails = surveyDetailsInfoDAO.getBoothWiseDcDetails(boothId);
				if(dcDetails != null && dcDetails.size() > 0)
				{
					dcMap = new HashMap<Long, String>();
					dcDatesMap = new HashMap<Long, String>();
					for (Object[] parms : dcDetails)
					{
						dcMap.put((Long)parms[1], casteMap.get((Long)parms[2]));
						dcDatesMap.put((Long)parms[1], parms[7].toString());
					}
				}
				List<Long> boothIds = new ArrayList<Long>();
				boothIds.add(boothId);
				List<Object[]> wmDetails = surveyCallStatusDAO.getBoothWiseWmCasteUpdationDetails(boothIds);
				if(wmDetails != null && wmDetails.size() > 0)
				{
					wmMap = new HashMap<Long, String>();
					for (Object[] parms : wmDetails)
					{
						if(parms[2] != null)
						{
							if(parms[2].toString().trim().equalsIgnoreCase("N"))
							{
								wmMap.put((Long)parms[0], casteMap.get((Long)parms[1]));
							}
							else
							{
								wmMap.put((Long)parms[0], dcMap.get((Long)parms[0]));
							}
						}
						
					}
				}
				List<Object[]> userList = surveyDetailsInfoDAO.getBoothWiseUser(boothIds,1l);
				if(userList != null && userList.size() > 0)
				{
					usersMap = new HashMap<Long, String>();
					for (Object[] objects : userList) 
					{
						String name = usersMap.get((Long)objects[0]);
						if(name == null)
						{
							usersMap.put((Long)objects[0], objects[2].toString());
						}
					}
				}
				
				List<Object[]> voterDetails = boothPublicationVoterDAO.getVoterDetailsByBoothID(boothIds);
				if(voterDetails != null && voterDetails.size() > 0)
				{
					returnList = new ArrayList<VerificationCompVO>();
					List<VerificationCompVO> matchedList  = new ArrayList<VerificationCompVO>();
					List<VerificationCompVO> unMatchedList = new ArrayList<VerificationCompVO>();
					List<VerificationCompVO> notVerifiedList = new ArrayList<VerificationCompVO>();
					String surveyUser = usersMap.get(boothId);
					VerificationCompVO mainVO = new VerificationCompVO();
					String date = null;
					for (Object[] parms : voterDetails)
					{
						VerificationCompVO VO = new VerificationCompVO();
						VO.setVoterCardNO(parms[2] != null ? parms[2].toString() : "");
						VO.setVoterName(parms[1] != null ? parms[1].toString() : "");
						VO.setVoterId(parms[0] != null ? (Long)parms[0]: null  );
						VO.setHouseNo(parms[3] != null ? parms[3].toString() : "");
						VO.setPartNo(parms[6] != null ? parms[6].toString() : "");
						VO.setPanchayatName(parms[7] != null ? parms[7].toString() : "");
						if(date==null)
						date = dcDatesMap.get((Long)parms[0]);
						if(dcMap != null && dcMap.size() > 0)
						{
							VO.setDcCaste(dcMap.get((Long)parms[0]) != null ? dcMap.get((Long)parms[0]) : "-");
						}
						else
						{
							VO.setDcCaste("-");
						}
						if(wmMap != null && wmMap.size() > 0)
						{
							VO.setWmCaste(wmMap.get((Long)parms[0]) != null ? wmMap.get((Long)parms[0]) : "-");
						}
						else
						{
							VO.setWmCaste("-");
						}
						
						VO.setRelativeName(parms[5] != null ? parms[5].toString() : "");
						//VO.setDate(dcDatesMap.get((Long)parms[0]));
						//VO.setSurveyUser(surveyUser);
						if(wmMap != null)
						{
							if(wmMap.get((Long)parms[0]) != null)
							{
								if(dcMap.get((Long)parms[0]) != null)
								{
									if(dcMap.get((Long)parms[0]).equalsIgnoreCase(wmMap.get((Long)parms[0])))
									{
										matchedList.add(VO);
									}
									else
									{
										unMatchedList.add(VO);
									}
								}
								else
								{
									notVerifiedList.add(VO);
								}
							}
							else
							{
								notVerifiedList.add(VO);
							}
						}
						
					}
					mainVO.setMatchedList(matchedList);
					mainVO.setMatchedCount(matchedList.size());
					mainVO.setUnMatchedList(unMatchedList);
					mainVO.setUnMatchedCount(unMatchedList.size());
					mainVO.setNotVerifiedList(notVerifiedList);
					mainVO.setNotVerifiedCount(notVerifiedList.size());
					mainVO.setDate(date);
					mainVO.setTotalCount(mainVO.getMatchedCount() + mainVO.getUnMatchedCount() + mainVO.getNotVerifiedCount());
					mainVO.setSurveyUser(surveyUser);
					returnList.add(mainVO);
				}
			}
			
		}
		catch (Exception e) 
		{
			LOG.error("Exception raised in checkForWebMonitorData", e);
		}
		return returnList;
	}
	
	public List<DcDvCollectedDataVO> getDcAndDvByConstituencyByUser(List<Long> constiIds,List<Long> surveyUserIds,Long userTypeId,Date fromDate,Date toDate)
	{
		List<DcDvCollectedDataVO> returnList = null;
		try
		{
			
			List<Long> boothidsList = boothDAO.getBoothIdsByConstituencyIdsAndPublicationId(constiIds,IConstants.VOTER_DATA_PUBLICATION_ID);
			
			List<Object[]> result = surveyDetailsInfoDAO.getDCPerformanceBoothWise(boothidsList,surveyUserIds,userTypeId,fromDate,toDate);
			
			if(result != null && result.size() > 0)
			{
				returnList = new ArrayList<DcDvCollectedDataVO>();
				for (Object[] parms : result) 
				{
					DcDvCollectedDataVO VO = new DcDvCollectedDataVO();
					VO.setPartNo(parms[0] != null ? parms[0].toString() : "");
					VO.setDate(parms[1] != null ? parms[1].toString() : "");
					VO.setTotalCount(parms[2] != null ? Long.valueOf(parms[2].toString()) : 0l);
					VO.setCasteCount(parms[3] != null ? Long.valueOf(parms[3].toString()) : 0l);
					VO.setHamletCount(parms[5] != null ? Long.valueOf(parms[5].toString()) : 0l);
					VO.setCadreCount(parms[7] != null ? Long.valueOf(parms[7].toString()) : 0l);
					VO.setInfluencePeopleCount(parms[8] != null ? Long.valueOf(parms[8].toString()) : 0l);
					VO.setMobileCount(parms[9] != null ? Long.valueOf(parms[9].toString()) : 0l);
					VO.setLocalAreaCount(parms[10] != null ? Long.valueOf(parms[10].toString()) : 0l);
					VO.setConstituency(parms[11] != null ? parms[11].toString() : "");
					VO.setId(parms[13] != null ? Long.valueOf(parms[13].toString()) : 0l);
					VO.setName(parms[14] != null ? parms[14].toString() : "");
					
					List<VerificationCompVO> wmList = checkForWebMonitorData((Long)parms[12]);
					if(wmList != null && wmList.size() > 0)
					{
						Long matchecCount = wmList.get(0).getMatchedCount().longValue();
						Long unMatchedCount = wmList.get(0).getUnMatchedCount().longValue();
						Long total = matchecCount + unMatchedCount;
						if(total != null && total > 0)
						{
							VO.setWmErrorRate(new BigDecimal(unMatchedCount*(100.0)/total).setScale(2, BigDecimal.ROUND_HALF_UP).toString());
						}
						
					}
					List<Long> boothIds = new ArrayList<Long>();
					boothIds.add((Long)parms[12]);
					List<VerificationCompVO> verifierList = checkForVerifierData(boothIds);
					if(verifierList != null && verifierList.size() > 0)
					{
						Long matchecCount = verifierList.get(0).getMatchedCount().longValue();
						Long unMatchedCount = verifierList.get(0).getUnMatchedCount().longValue();
						Long total = matchecCount + unMatchedCount;
						if(total != null && total > 0)
						{
							VO.setVerifierErrorRate(new BigDecimal(unMatchedCount*(100.0)/total).setScale(2, BigDecimal.ROUND_HALF_UP).toString());
						}
						
					}
					returnList.add(VO);
				}
			}
		}
		catch (Exception e)
		{
			LOG.error("Exception raised in getDcAndDvByConstituencyByUser", e);
		}
		return returnList;
	}
	
	public List<GenericVO> getDcorDvUsersByConstituency(Long userTypeId)
	{
		List<GenericVO> returnList = null;
		try 
		{
			List<Object[]> result = surveyDetailsInfoDAO.getDcorDvUsersByConstituency(userTypeId);
			if(result != null && result.size() > 0)
			{
				returnList = new ArrayList<GenericVO>();
				for (Object[] parms : result)
				{
					GenericVO VO = new GenericVO();
					VO.setId(parms[0] != null ? (Long)parms[0]: 0l);
					VO.setName(parms[1] != null ? parms[1].toString() : "");
					returnList.add(VO);
				}
			}
		}
		catch (Exception e) 
		{
			LOG.error("Exception raised in getDcorDvUsersByConstituency", e);
		}
		return returnList;
	}
	
	public String savePercentageOfBoothForCasteSurvey(Long boothId,String percentage){
		String status = "Saved Successfully";
		
		try{
		Long verifierBoothPercentageId = verifierBoothPercentageDAO.checkForBoothPercentages(boothId);
		VerifierBoothPercentage vb = null;

		if(verifierBoothPercentageId ==  null)
		{
			vb = new VerifierBoothPercentage();
			vb.setBoothId(boothId);
		}
		else
		{
			vb = verifierBoothPercentageDAO.get(verifierBoothPercentageId);
		}
		vb.setPercentage(percentage);			
	    VerifierBoothPercentage vbs = verifierBoothPercentageDAO.save(vb);
		if(vbs==null){
			status = "Not Saved, Please Try Again";
		}						
		}catch (Exception e) {
			status = "Not Saved, Please Try Again";
			LOG.error("Exception raised in savePercentageOfBoothForCasteSurvey", e);
		}
		
		return status;
	}
	
	public String savePercentageOfBoothForCasteSurvey(List<Long> boothIds,String percentage){
		String status = "Saved Successfully";
		
		try{
			for (Long boothId : boothIds)
			{
				Long verifierBoothPercentageId = verifierBoothPercentageDAO.checkForBoothPercentages(boothId);
				VerifierBoothPercentage vb = null;

				if(verifierBoothPercentageId ==  null)
				{
					vb = new VerifierBoothPercentage();
					vb.setBoothId(boothId);
				}
				else
				{
					vb = verifierBoothPercentageDAO.get(verifierBoothPercentageId);
				}
				vb.setPercentage(percentage);			
			    VerifierBoothPercentage vbs = verifierBoothPercentageDAO.save(vb);
				if(vbs==null){
					status = "Not Saved, Please Try Again";
				}
			}
								
		}catch (Exception e) {
			status = "Not Saved, Please Try Again";
			LOG.error("Exception raised in savePercentageOfBoothForCasteSurvey", e);
		}
		
		return status;
	}
	
	/**
	 * This Service is used for getting all verifiers Collected , verified and updated count details
	 * @param surveyUserId
	 * @param boothId
	 * @return returnList
	 */
	public List<DcDvCollectedDataVO> getVerifierCollectedDetails(Long surveyUserId,Long boothId)
	{
		List<DcDvCollectedDataVO> returnList = null;
		try 
		{
			List<Object[]> result = surveyDetailsInfoDAO.getVerifierCollectedDetails(surveyUserId, boothId);
			if(result != null && result.size() > 0)
			{
				returnList = new ArrayList<DcDvCollectedDataVO>();
				DcDvCollectedDataVO mainVO = new DcDvCollectedDataVO();
				for (Object[] parms : result)
				{
					if(parms[0] != null)
					{
						if(Long.valueOf(parms[0].toString()) == 1l)
						{
							mainVO.setMobileCount(mainVO.getMobileCount() + (Long)parms[1]);// collected count
						}
						else if(Long.valueOf(parms[0].toString()) == 2l)
						{
							mainVO.setCasteCount((Long)parms[1] + mainVO.getCasteCount()); // updated count
						}
						else
						{
							mainVO.setCadreCount((Long)parms[1] + mainVO.getCadreCount()); // verified count
						}
					}
				}
				List<Long> boothIds= new ArrayList<Long>();
				boothIds.add(boothId);
				List<VerificationCompVO> matchUnMatchList = checkForVerifierDataForWM(boothIds);
				if(matchUnMatchList != null && matchUnMatchList.size() > 0)
				{
					mainVO.setTotalCount(matchUnMatchList.get(0).getMatchedCount().longValue());
					mainVO.setInfluencePeopleCount(matchUnMatchList.get(0).getUnMatchedCount().longValue());
					mainVO.setLocalAreaCount(matchUnMatchList.get(0).getNotVerifiedCount().longValue());
				}
				returnList.add(mainVO);
			}
		} 
		catch (Exception e)
		{
			LOG.error("Exception raised in getVerifierCollectedDetails", e);
		}
		return returnList;
	}
	
	public List<DcDvCollectedDataVO> getConstituencySummaryReport()
	{
		List<DcDvCollectedDataVO> resultList = new ArrayList<DcDvCollectedDataVO>();
		//List<Long> constituencyIds = new ArrayList<Long>();
		try{
			/*List<Object[]> constituencys = surveyDetailsInfoDAO.getSurveyStartedConstituencyInfo();				
			
			if(constituencys != null && constituencys.size() > 0)
				for(Object[] params : constituencys)
					constituencyIds.add((Long)params[0]);*/
			
					
			//List<Object[]> list = boothPublicationVoterDAO.getTotalVotersForAllConstituencies(constituencyIds);
			Map<Long,GenericVO> errorMap = new HashMap<Long, GenericVO>();
			Map<Long,GenericVO> constituencyMap = null;
			getConstituencyWiseCasteAndMobileErrorRate(errorMap);
			if(errorMap != null && errorMap.size() > 0)
			{
				List<Object[]> list = surveyConstituencyTempDAO.getTotalVotersAndBooths(new ArrayList<Long>(errorMap.keySet()));
				
				if(list !=null && list.size() > 0)
				{
					constituencyMap = new HashMap<Long, GenericVO>();
					for(Object[] params : list)
					{
						GenericVO vo = new GenericVO();
						vo.setId(params[0] != null ? (Long)params[0]:0L);// constituency Id
						vo.setName(params[3] != null ? params[3].toString():"");// constituency name
						vo.setCount(params[1] != null ?(Long)params[1]:0L);// voters count
						vo.setRank(params[2] != null ? Long.valueOf(params[2].toString()) : 0L ); // booths count
						
						constituencyMap.put( (Long)params[0], vo);
					}
					
				}
				
				if(constituencyMap != null && constituencyMap.size() > 0)
				{
					for (Long constituencyId :  constituencyMap.keySet())
					{
						DcDvCollectedDataVO mainVO = new DcDvCollectedDataVO();
						GenericVO constiVO = constituencyMap.get(constituencyId);
						if(constiVO != null)
						{
							mainVO.setBoothCount(constiVO.getRank());
							mainVO.setConstituency(constiVO.getName());
							mainVO.setTotalCount(constiVO.getCount());
							
							GenericVO errorVO = errorMap.get(constituencyId);
							if(errorVO != null)
							{
								mainVO.setCasteTagedBooths(errorVO.getRank());
								mainVO.setCasteCount(errorVO.getCount());
								mainVO.setCasteVerifiedBooths(Long.valueOf(errorVO.getVerificationProcessCount()));
								
								mainVO.setCasteErrorRate(errorVO.getDesc());
								mainVO.setMobileErrorRate(errorVO.getPercent());
							}
						}
						
						resultList.add(mainVO);
					}
				}
			}
				/*List<Object[]> list1 = surveyDetailsInfoDAO.getCasteTaggedVotersForAllConstituencies(1l);
				if(list1 != null && list1.size() > 0)
				for(Object[] params : list1)
				{
					DcDvCollectedDataVO vo = getMatchedConstituencyVo(resultList,(Long)params[0]);
					if(vo != null)
					{
						vo.setCasteCount(params[1] != null ? (Long)params[1]:0L);
						vo.setCasteTagedBooths(params[2] != null ? (Long)params[2]:0L);
					}
				}
				List<Object[]> list2 = surveyDetailsInfoDAO.getMobileTaggedVotersForAllConstituencies(1l);	
				if(list2 != null && list2.size() > 0)
					for(Object[] params : list1)
					{
						DcDvCollectedDataVO vo = getMatchedConstituencyVo(resultList,(Long)params[0]);
						if(vo != null)
						{
							vo.setMobileCount(params[1] != null ? (Long)params[1]:0L);
							vo.setMobileTagedBooths(params[2] != null ? (Long)params[2]:0L);
						}
							
					}	
				
				List<Object[]> casteVoters = surveyCallStatusDAO.getCasteVotersForAllConstituencies();
				
				if(casteVoters != null && casteVoters.size() > 0)
				{
					for(Object[] params : casteVoters)
					{

						DcDvCollectedDataVO vo = getMatchedConstituencyVo(resultList,(Long)params[0]);
						if(vo != null)
						{
						if(params[2] !=null && params[2].toString().equalsIgnoreCase("Y"))
						  vo.setCasteActiveVoters(vo.getCasteActiveVoters() + (Long)params[1]);
						else if(params[2] !=null && params[2].toString().equalsIgnoreCase("N"))
							 vo.setCasteInActiveVoters(vo.getCasteInActiveVoters() + (Long)params[1]);
						Long total = vo.getCasteActiveVoters() + vo.getCasteInActiveVoters();
						vo.setCasteErrorRate(vo.getCasteInActiveVoters() != null &&vo.getCasteInActiveVoters() !=0 ? surveyDataDetailsServic.roundTo2DigitsFloatValue((float) vo.getCasteInActiveVoters() * 100f /total)
								: "0.00");
					
						List<Long> constiIds = new ArrayList<Long>();
						constiIds.add((Long)params[0]);
						
						if(vo.getCasteVerifiedBooths() == null)
							vo.setCasteVerifiedBooths((Long) surveyCallStatusDAO.getTotalVerifiedBoothsinAllConstituencyIds(constiIds).get(0)[1]);
						
						}
					}
				}
				
				
				List<Object[]> mobileVoters = surveyCallStatusDAO.getMobileVotersForAllConstituencies();
				
				if(mobileVoters != null && mobileVoters.size() > 0)
				{
					for(Object[] params : mobileVoters)
					{

						DcDvCollectedDataVO vo = getMatchedConstituencyVo(resultList,(Long)params[0]);
						if(vo != null)
						{
						if(params[2] !=null && params[2].toString().equalsIgnoreCase("Y"))
						
						  vo.setMobileActiveVoters(vo.getMobileActiveVoters() + (Long)params[1]);
						else if(params[2] !=null && params[2].toString().equalsIgnoreCase("N"))
							 vo.setMobileInActiveVoters(vo.getMobileInActiveVoters() + (Long)params[1]);
						Long total = vo.getMobileActiveVoters() + vo.getMobileInActiveVoters();
						
						vo.setMobileErrorRate(vo.getMobileInActiveVoters() != null &&vo.getMobileInActiveVoters() !=0 ? surveyDataDetailsServic.roundTo2DigitsFloatValue((float) vo.getMobileInActiveVoters() * 100f /total)
								: "0.00");

						}
					}
				}
			}*/
			
		}
		catch (Exception e) {
			LOG.error("Exception raised in getConstituencySummaryReport", e);
		}
		return resultList;
	}
	
	public void getConstituencyWiseCasteAndMobileErrorRate(Map<Long,GenericVO> returnMap)
	{
		
		List<Object[]> casteCollecetdDetails = surveyDetailsInfoDAO.getConstituencyWiseCasteCollected();
		if(casteCollecetdDetails != null && casteCollecetdDetails.size() > 0)
		{
			Map<Long,GenericVO> casteCollecetdMap = new HashMap<Long, GenericVO>();
			Map<Long,GenericVO> casteVerifiedMap  = null ;
			Map<Long,Long> verifiedBoothsMap = null;
			Map<Long,Long> mobilesCollectedMap =null;
			Map<Long,Long> mobilesVerifiedMap = null;
			Map<Long,String> constituencyMap = new HashMap<Long, String>();
			Map<Long,Long> constiBoothMap = new HashMap<Long, Long>();
			Map<Long,Long> constiCollectedMap = new HashMap<Long, Long>();
			for (Object[] objects : casteCollecetdDetails) 
			{
				String constituencyName = constituencyMap.get((Long)objects[0]);
				if(constituencyName == null)
				{
					constituencyMap.put((Long)objects[0], objects[1].toString());
				}
				Long boothCount = constiBoothMap.get((Long)objects[0]);
				if(boothCount == null)
				{
					constiBoothMap.put((Long)objects[0], 1l);
				}
				else
				{
					constiBoothMap.put((Long)objects[0], 1 + boothCount);
				}
				Long votersCount = constiCollectedMap.get((Long)objects[0]);
				if(votersCount == null)
				{
					constiCollectedMap.put((Long)objects[0], (Long)objects[2]);
				}
				else
				{
					constiCollectedMap.put((Long)objects[0], (Long)objects[2] + votersCount);
				}
				/*GenericVO casteCollecetdVO = new GenericVO();
				
				casteCollecetdVO.setId((Long)objects[0]);//constituencyId
				casteCollecetdVO.setName(objects[1].toString());//constituency Name
				casteCollecetdVO.setCount((Long)objects[2]);// total Voters
				casteCollecetdVO.setRank((Long)objects[3]);// booths count
				
				casteCollecetdMap.put((Long)objects[0], casteCollecetdVO);*/
			}
			
			for(Long constiturncyId : constituencyMap.keySet())
			{
				GenericVO casteCollecetdVO = new GenericVO();
				
				casteCollecetdVO.setId(constiturncyId);//constituencyId
				casteCollecetdVO.setName(constituencyMap.get(constiturncyId));//constituency Name
				casteCollecetdVO.setCount(constiCollectedMap.get(constiturncyId));// total Voters
				casteCollecetdVO.setRank(constiBoothMap.get(constiturncyId));// booths count
				
				casteCollecetdMap.put(constiturncyId, casteCollecetdVO);
			}
			List<Object[]> verifiedCasteDetails = surveyCallStatusDAO.getConstituencyWiseCasteUpdate();
			if(verifiedCasteDetails != null && verifiedCasteDetails.size() > 0)
			{
				casteVerifiedMap = new HashMap<Long, GenericVO>();
				for (Object[] objects : verifiedCasteDetails) 
				{
					GenericVO casteVerifiedVO = new GenericVO();
					
					casteVerifiedVO.setId((Long)objects[0]);//constituencyId
					casteVerifiedVO.setName(objects[1].toString());//constituency Name
					casteVerifiedVO.setCount((Long)objects[2]);// total Voters
					casteVerifiedMap.put((Long)objects[0], casteVerifiedVO);
				}
			}
			
			List<Object[]> casteVerifedBooths = surveyCallStatusDAO.getConstituencyWiseBoothsCount();
			if(casteVerifedBooths != null && casteVerifedBooths.size() > 0)
			{
				verifiedBoothsMap = new HashMap<Long, Long>();
				for (Object[] objects : casteVerifedBooths)
				{
					verifiedBoothsMap.put((Long)objects[0], (Long)objects[1]);
				}
			}
			
			List<Object[]> mobileCollectedDetails = surveyDetailsInfoDAO.getConstituenyWiseMobilesCollected();
			if(mobileCollectedDetails != null && mobileCollectedDetails.size() > 0)
			{
				mobilesCollectedMap = new HashMap<Long, Long>();
				for (Object[] objects : mobileCollectedDetails)
				{
					mobilesCollectedMap.put((Long)objects[0], (Long)objects[1]);
				}
			}
			
			List<Object[]> mobileVerifiedDetails = surveyCallStatusDAO.getConstituencyWiseMobilesUnMatched();
			if(mobileVerifiedDetails != null && mobileVerifiedDetails.size() > 0)
			{
				mobilesVerifiedMap = new HashMap<Long, Long>();
				for (Object[] objects : mobileVerifiedDetails)
				{
					mobilesVerifiedMap.put((Long)objects[0], (Long)objects[1]);
				}
			}
			
			if(casteCollecetdMap != null && casteCollecetdMap.size() > 0)
			{
				for (Long constituencyId : casteCollecetdMap.keySet())
				{
					GenericVO genericVO = new GenericVO();
					genericVO.setId(constituencyId);//constituencyId
					GenericVO casteCollecetdVO = casteCollecetdMap.get(constituencyId);
					genericVO.setName(casteCollecetdVO.getName());// constituency name
					genericVO.setCount(casteCollecetdVO.getCount());//total voters
					genericVO.setRank(casteCollecetdVO.getRank());// total booths count
					Long verifiedBooths = verifiedBoothsMap.get(constituencyId);
					if(verifiedBooths != null && verifiedBooths > 0)
					{
						genericVO.setVerificationProcessCount(Integer.valueOf(verifiedBooths.toString()));// verified Booths count
					}
					else
					{
						genericVO.setVerificationProcessCount(0);// verified Booths count
					}
					
					Long mobilesCollected = mobilesCollectedMap.get(constituencyId);
					Long mobilesVerified = mobilesVerifiedMap.get(constituencyId);
					if(mobilesCollected != null && mobilesCollected > 0 && mobilesVerified != null && mobilesVerified > 0)
					{
						genericVO.setPercent(new BigDecimal(mobilesVerified*(100.0)/mobilesCollected).setScale(2, BigDecimal.ROUND_HALF_UP).toString());
					}
					else
					{
						genericVO.setPercent("0.00");// mobile Error Rate
					}
					
					GenericVO verifiedVO = casteVerifiedMap.get(constituencyId);
					if(verifiedVO != null)
					{
						if(verifiedVO.getCount() != null && verifiedVO.getCount() > 0)
						{
							genericVO.setDesc(new BigDecimal(verifiedVO.getCount()*(100.0)/genericVO.getCount()).setScale(2, BigDecimal.ROUND_HALF_UP).toString());
						}
						else
						{
							genericVO.setDesc("0.00");
						}
					}
					else
					{
						genericVO.setDesc("0.00");
					}
					returnMap.put(constituencyId, genericVO);
				}
			}
			
		}
		
		
	}
	public void fullSurveyResponceVO(List<SurveyDetailsInfo> result,List<SurveyResponceVO> returnList,Long boothId )
	{
		for (SurveyDetailsInfo surveyDetailsInfo : result)
		{
			SurveyResponceVO VO = new SurveyResponceVO();
			VO.setDataTypeId("2");
			VO.setBoothId(boothId);
			VO.setSurveyUserId(surveyDetailsInfo.getSurveyUser().getSurveyUserId());
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
			if(VO.getWardId() != null)
			{
				VO.setWardId(VO.getWardId());
			}
			VO.setLatitude(surveyDetailsInfo.getLatitude());
			VO.setLocalArea(surveyDetailsInfo.getLocalArea());
			VO.setLongitude(surveyDetailsInfo.getLongitude());
			returnList.add(VO);
		}
	}

	
	public List<SurveyReportVO> getConstituencyWiseLeadersAndUsersDetails(Long constituencyId,Date date)
	{
		LOG.debug("Entered into the getConstituencyWiseLeadersAndUsersDetails method");
		List<SurveyReportVO> returnList = new ArrayList<SurveyReportVO>();
		List<Long> leaderIds = new ArrayList<Long>();
		try 
		{
			List<Object[]> leaders = surveyUserConstituencyDAO.getSurveyLeaderByConstituency(constituencyId);
			if(leaders != null && leaders.size() > 0)
			{
				returnList = new ArrayList<SurveyReportVO>();
				for (Object[] objects : leaders)
				{
					SurveyReportVO VO = new SurveyReportVO();
					VO.setId((Long)objects[0]);
					leaderIds.add((Long)objects[0]);
					VO.setLeaderName(objects[1] != null ? objects[1].toString() : null);
					VO.setMobileNo(objects[2] != null ? objects[2].toString() : null);
					returnList.add(VO);
				}
			}
			
			List<Object[]> users = surveyUserRelationDAO.getAllUserForLeader(leaderIds);
			if(users != null && users.size() > 0)
			{
				 for(Object[] params : users)
				 {
					 SurveyReportVO result = getMatchedVOForLeaders(returnList,(Long)params[3]);
					 if(result != null)
					 {		
						 SurveyReportVO vo = new SurveyReportVO();
						 vo.setUserid((Long)params[0]);
						 vo.setUserName(params[1].toString());
						 vo.setUserType(params[2].toString());
						 result.getSubList().add(vo);
					 }						 
				 }				 
			}				
			List<Long> userIds = surveyDetailsInfoDAO.getUsersByDate(constituencyId,date);
			for(SurveyReportVO vo : returnList)
			{
				for(SurveyReportVO vo1 : vo.getSubList())
				{
					if(userIds.contains(vo1.getUserid()))
					{					
						vo1.setName("Active");
					}
					else
					{				
						vo1.setName("Inactive");
					}
				}
				
			}
		
		} 
		catch (Exception e)
		{
			LOG.error("Exception raised in  getConstituencyWiseLeadersAndUsersDetails method");
		}
		return returnList;
		
	}
	
	 public SurveyReportVO getMatchedVOForLeaders(List<SurveyReportVO> resultList,Long leaderId)
		{
		
			try{
				if(resultList == null)
					return null;
				for(SurveyReportVO vo : resultList)
				{
					if(leaderId.longValue() == vo.getId().longValue())
						return vo;
				}
			}
			catch (Exception e) {
				e.printStackTrace();
				
			}
			return null;
		}
	 
	/**
	 * This Service is used for filling third party verification details
	 * @param boothId
	 * @return returnList
	 */
	public List<SurveyResponceVO> getThirdPartyVerificationDetails(Long boothId,Long userId)
	{
		List<SurveyResponceVO> returnList = null;
		try 
		{
			List<SurveyDetailsInfo> dcdetails = surveyDetailsInfoDAO.getSurveyDetilsForAssibnedBooths(boothId);
			if(dcdetails != null && dcdetails.size() > 0)
			{
				List<SurveyResponceVO> dcList = new ArrayList<SurveyResponceVO>();
				Map<Long,SurveyResponceVO> dcMap = null;
				Map<Long,SurveyResponceVO> dvMap = null;
				Map<Long,SurveyResponceVO> dcWmMap = null;
				Map<Long,SurveyResponceVO> dvWmMap = null;
				Map<Long,SurveyResponceVO> totalVoterMap = null;
				fullSurveyResponceVO(dcdetails,dcList,boothId);
				if(dcList != null && dcList.size() > 0)
				{
					dcMap = new HashMap<Long, SurveyResponceVO>();
					for (SurveyResponceVO surveyResponceVO : dcList)
					{
						dcMap.put(surveyResponceVO.getVoterId(), surveyResponceVO);
					}
				}
				List<SurveyDetailsInfo> dvDetails = surveyDetailsInfoDAO.getVerifierSurveyDetails(boothId);
				if(dvDetails != null && dvDetails.size() > 0)
				{
					List<SurveyResponceVO> dvList = new ArrayList<SurveyResponceVO>();
					
					fullSurveyResponceVO(dvDetails,dvList,boothId);
					if(dvList != null && dvList.size() >0)
					{
						dvMap = new HashMap<Long, SurveyResponceVO>();
						for (SurveyResponceVO surveyResponceVO : dvList)
						{
							dvMap.put(surveyResponceVO.getVoterId(), surveyResponceVO);
						}
					}
				}
				List<Long> boothIds = new ArrayList<Long>();
				boothIds.add(boothId);
				
				List<Object[]> wmDetails = surveyCallStatusDAO.getBoothWiseWmCasteUpdationDetails(boothIds);
				if(wmDetails != null && wmDetails.size() > 0)
				{
					dcWmMap = new HashMap<Long, SurveyResponceVO>();
					for (Object[] parms : wmDetails)
					{
						if(parms[0] != null)
						{
							if(parms[2] != null)
							{
								if(parms[2].toString().equalsIgnoreCase("Y"))
								{
									if(dcMap.get((Long)parms[0]) != null)
										dcWmMap.put((Long)parms[0],dcMap.get((Long)parms[0]));
								}
								else
								{
									if(parms[1] != null)
									{
										if((Long)parms[1]  > 0)
										{
											if(dcMap.get((Long)parms[0]) != null)
											{
												SurveyResponceVO wmVO = dcMap.get((Long)parms[0]);
												wmVO.setCasteId(((Long)parms[1]));
												String casteName = casteStateDAO.get(((Long)parms[1])).getCaste().getCasteName();
												wmVO.setCasteName(casteName);
												dcWmMap.put((Long)parms[0],wmVO);
											}
											
										}
									}
								}
							}
						}
					}
				}
				
				
				List<Object[]> dvWmDetails = surveyCallStatusDAO.getBoothWiseDvWmCasteUpdationDetails(boothIds);
				if(dvWmDetails != null && dvWmDetails.size() > 0)
				{
					dvWmMap = new HashMap<Long, SurveyResponceVO>();
					for (Object[] parms : dvWmDetails)
					{
						if(parms[0] != null)
						{
							if(parms[2] != null)
							{
								if(parms[2].toString().equalsIgnoreCase("Y"))
								{
									if(dvMap != null && dvMap.size() > 0)
									{
										if(dvMap.get((Long)parms[0]) != null)
											dvWmMap.put((Long)parms[0],dvMap.get((Long)parms[0]));
									}
									
								}
								else
								{
									if(parms[1] != null)
									{
										if((Long)parms[1]  > 0)
										{
											if(dcMap.get((Long)parms[0]) != null)
											{
												SurveyResponceVO wmVO = dcMap.get((Long)parms[0]);
												wmVO.setCasteId(((Long)parms[1]));
												String casteName = casteStateDAO.get(((Long)parms[1])).getCaste().getCasteName();
												wmVO.setCasteName(casteName);
												dvWmMap.put((Long)parms[0],wmVO);
											}
											
										}
									}
								}
							}
						}
					}
				}
				Map<Long,SurveyResponceVO> wmMap = new HashMap<Long, SurveyResponceVO>();
				checkForDcWithWmForVO(dcMap,dcWmMap,wmMap);
				Map<Long,SurveyResponceVO> resultMap = checkForDcWmWithDvWmForThirdParty(wmMap,dvWmMap);
				List<Object[]> voterHouseDetails = boothPublicationVoterDAO.getTotalVotersByBoothsForVerfierForCTP(boothId);
				if(voterHouseDetails != null && voterHouseDetails.size() > 0)
				{
					totalVoterMap = new HashMap<Long, SurveyResponceVO>();
					for (Object[] parms : voterHouseDetails)
					{
						SurveyResponceVO VO = new SurveyResponceVO();
						VO.setVoterId((Long)parms[0]);
						VO.setVoterCardNo(parms[4] != null ? parms[4].toString() :"");
						VO.setAge(parms[3] != null ? (Long)parms[3] :null);
						VO.setGender(parms[2] != null ? parms[2].toString() :"");
						VO.setHouseNo(parms[1] != null ? parms[1].toString() :"");
						VO.setDataTypeId("2");
						VO.setBoothId(boothId);
						totalVoterMap.put((Long)parms[0], VO);
					}
				}
				
				if(totalVoterMap != null && totalVoterMap.size() > 0)
				{
					List<Long> voterIds = new ArrayList<Long>(totalVoterMap.keySet());
					if(voterIds != null && voterIds.size() > 0)
					{
						returnList = new ArrayList<SurveyResponceVO>();
						for (Long voterId : voterIds) 
						{
							if(resultMap!=null&&resultMap.get(voterId) != null)
							{
								returnList.add(resultMap.get(voterId));
							}
							else if(dvMap != null && dvMap.get(voterId) != null)
							{
								returnList.add(dvMap.get(voterId));
							}
							else if(dcMap != null && dcMap.get(voterId) != null)
							{
								returnList.add(dcMap.get(voterId));
							}
							else
							{
								returnList.add(totalVoterMap.get(voterId));
							}
						}
					}
				}
			}
		} 
		catch (Exception e) 
		{
			LOG.error("Exception raised in getThirdPartyVerificationDetails", e);
		}
		return returnList;
	}
	public void checkForDcWithWmForVO(Map<Long,SurveyResponceVO> dcMap,Map<Long,SurveyResponceVO> wmMap,Map<Long,SurveyResponceVO> resultMap)
	{
		try
		{
			List<Long> dcCollectedVoters = new ArrayList<Long>(dcMap.keySet());
			if(dcCollectedVoters != null && dcCollectedVoters.size() > 0)
			{
				for (Long dcVoterId : dcCollectedVoters)
				{
					if(wmMap != null)
					{
						if(wmMap.get(dcVoterId) != null)
						{
							resultMap.put(dcVoterId, wmMap.get(dcVoterId));
						}
						else
						{
							resultMap.put(dcVoterId, dcMap.get(dcVoterId));
						}
					}
					else
					{
						resultMap.put(dcVoterId, dcMap.get(dcVoterId));
					}
					
				}
			}
		} 
		catch (Exception e)
		{
			LOG.error("Exception raised in checkForDcWithWm", e);
		}
	}
	public Map<Long,SurveyResponceVO> checkForDcWmWithDvWmForThirdParty(Map<Long,SurveyResponceVO> wmMap,Map<Long,SurveyResponceVO> dvWmMap)
	{
		Map<Long,SurveyResponceVO> resultMap = new HashMap<Long, SurveyResponceVO>();
		if(dvWmMap != null && dvWmMap.size() > 0)
		{
			if(wmMap != null && wmMap.size() > 0)
			{
				for(Long voterId : dvWmMap.keySet())
				{
					wmMap.put(voterId, dvWmMap.get(voterId));
				}
				resultMap = wmMap;
			}
			else
			{
				resultMap = dvWmMap;
			}
		}
		else
		{
			resultMap = wmMap;
		}
		return resultMap;
	}
	
	public List<SurveyReportVO> getConstituencyWiseFieldSummary()
	{
		List<SurveyReportVO> resultList = new ArrayList<SurveyReportVO>();
		List<Long> leaderIds = new ArrayList<Long>();
		List<Long> constituencyIds = new ArrayList<Long>();
		try{
			
			List<Object[]> list = surveyUserConstituencyDAO.getSurveyLeadersForAllConstituency();
			if(list != null && list.size() > 0)
			{
				for(Object[] params : list)
				{
					if(!constituencyIds.contains((Long)params[0]))
					{
					SurveyReportVO vo = new SurveyReportVO();
					vo.setId((Long)params[0]);
					vo.setName(params[1].toString());
					constituencyIds.add((Long)params[0]);
					resultList.add(vo);
				}
					if(!leaderIds.contains((Long)params[2]))
						leaderIds.add((Long)params[2]);
					
				}
				List<Object[]> list1 = surveyUserRelationDAO.getAllUsersCountForLeaders(leaderIds);
				List<Long> allLeadersUserIds = new ArrayList<Long>();
				Map<Long,List<Long>> leaderUsers = new HashMap<Long, List<Long>>();
				for(Object[] params : list1)
				{
					List<Long> userIds = leaderUsers.get((Long)params[0]);
					if(userIds == null)
					{
						userIds = new ArrayList<Long>();
						leaderUsers.put((Long)params[0], userIds);
					}
					if(!userIds.contains((Long)params[1]))
					userIds.add((Long)params[1]);
					if(!allLeadersUserIds.contains((Long)params[1]))
					allLeadersUserIds.add((Long)params[1]);
				}
				
				
				for(Object[] params : list)
				{
					SurveyReportVO vo = getMatchedSurveyReportVo(resultList, (Long)params[0]);
					if(vo != null)
					{
						
						SurveyReportVO leaderVo = new SurveyReportVO();
						leaderVo.setId((Long)params[2]);
						leaderVo.setName(params[3].toString());
						leaderVo.setTotal(leaderUsers.get((Long)params[2]) != null ? new Long(leaderUsers.get((Long)params[2]).size()) : 0l);
						leaderVo.setCompleteIds(leaderUsers.get((Long)params[2]));// userIds for leader
						vo.getSubList().add(leaderVo);
						
					}
				}
				Map<Long,List<Long>> constituencyUsersMap = new HashMap<Long, List<Long>>();
			 /*  SimpleDateFormat targetFormat = new SimpleDateFormat("yyyy-MM-dd" );
				Date date = targetFormat.parse("2014-07-14");*/
				List<Long> list2 = surveyDetailsInfoDAO.getUsersByDate(allLeadersUserIds,new Date());
				
				for(SurveyReportVO vo : resultList)
				{
					
					
					for(SurveyReportVO vo1 : vo.getSubList())
					{
						if(vo1.getCompleteIds() != null && vo1.getCompleteIds().size() > 0)
						{
							Long active = 0l;
							Long inactive = 0l;
							for(Long id : vo1.getCompleteIds())
							{
								if(list2.contains(id))
								{	
									active = active + 1;
								}
								else
								{				
									
									inactive = inactive + 1;
								}
							}
							vo1.setActiveUsersCount(active);
							vo1.setInActiveUsersCount(inactive);
						}
						
					}
					
				}
			}
			
		}
		catch (Exception e) {
			LOG.error("Exception raised in getConstituencyWiseFieldSummary", e);
		}
		return resultList;
	}
	
	public SurveyReportVO getMatchedSurveyReportVo(List<SurveyReportVO> resultList,Long id)
	{
	
		try{
			if(resultList == null)
				return null;
			for(SurveyReportVO vo : resultList)
			{
				if(id.longValue() == vo.getId().longValue())
					return vo;
			}
		}
		catch (Exception e) {
			e.printStackTrace();
			
		}
		return null;
	}
	public List<GenericVO> getUsersList(Long userTypeId,List<Long> constituencyIds){
		List<GenericVO> resultList = new ArrayList<GenericVO>();
		try{
			List<Object[]> list = surveyUserRelationDAO.getSurveyConstituencyLeadersList(userTypeId,constituencyIds);
			if(list != null && list.size() > 0){
				for(Object[] params : list){
					resultList.add(new GenericVO((Long)params[0],params[1].toString()));
				}
			}
		}
		catch (Exception e) {
			Log.error("Exception Occured in getUsersList()");
		}
		return resultList;
	}
	
	/**
	 * 	@author <a href="mailto:sasi.itgrids.hyd@gmail.com">SASI</a>
	 * 	@since	AUGUST 01 2014
	 *  @param List of BoothIds
	 *  @return List of Verifiers
	 */
	public List<VerificationCompVO> getVerifiersOfBooths(List<Long> boothIds){
		Log.debug("In getVerifiersOfBooths in SurveyDetailsService");
		List<VerificationCompVO> finalList = new ArrayList<VerificationCompVO>();
		
		try{
			List<Object[]> verifiersList = surveyDetailsInfoDAO.getBoothWiseVerifier(boothIds);
			if(verifiersList!=null && verifiersList.size()>0){
				for(Object[] temp:verifiersList){
					VerificationCompVO vo = new VerificationCompVO();
					vo.setVerifier(temp[2].toString());
					vo.setVerifierId(Long.valueOf(temp[1].toString()));

					finalList.add(vo);
				}
			}
			
		}catch (Exception e) {
			Log.error("Exception Raised in getVerifiersOfBooths");
		}
		return finalList;
	}
	
	public List<VerificationCompVO> checkForVerifierDataWithBoothAndVerifierId(List<Long> boothIds,Long verifierId){
		List<VerificationCompVO> returnList = null;
		try 
		{
			List<Object[]> castesList = casteStateDAO.getAllCasteDetailsForVoters(1l);
			if(castesList != null && castesList.size() > 0)
			{
				Map<Long,Map<Long,String>> dcBoothMap = null;// booth wise Data Collector Caste Collected 
				Map<Long,Map<Long,String>> dvBoothMap = null;// booth wise Data Verifier Caste Collected
				Map<Long,Map<Long,String>> wmBoothMap = null;// booth Wise Web Moniter Caste Collected
				Map<Long,Map<Long,String>> dvWmBoothMap = null;// booth Wise Web Moniter Caste Collected
				Map<Long,Map<Long,String>> dcBoothDatesMap = null;// Day wise booth Wise Data Collector 
				Map<Long,Map<Long,String>> dvBoothDatesMap = null;// Day wise booth Wise Data Verified 
				Map<Long,VerificationCompVO> boothWiseMap = null;// booth wise total recoreds
				Map<Long,Map<Long,Long>> dvStatusBoothMap = null;// booth wise Data Verifier Caste Collected
				Map<Long,String> dcMap = null;// Map<voterId,Caste>
				Map<Long,String> dvMap = null;//Map<voterId,Caste>
				Map<Long,String> wmMap = null;//Map<voterId,Caste>
				Map<Long,String> dvWmMap = null;//Map<voterId,Caste>
				Map<Long,String> casteMap = new HashMap<Long, String>();//Map<casteId,CasteName>
				Map<Long,Map<Long,String>> dcWmCollectedMap = null;// Day wise booth Wise Data Collector 
				Map<Long,String> dcDatesMap = null;
				Map<Long,String> dvDatesMap = null;
				Map<Long,String> dcWmMap = null;
				Map<Long,String> usersMap =null;
				Map<Long,String> verifierMap = null;
				Map<Long,String> usersDateMap =null;
				Map<Long,String> verifierDateMap = null;
				Map<Long,Long> dvStatusMap = null;
				Map<Long,String> mobileNoMap = null;
				Map<Long,Map<Long,String>> wmResultBoothMap = null;// booth Wise Web Moniter Caste Collected
				for (Object[] objects : castesList)
				{
					casteMap.put((Long)objects[0], objects[1].toString());
				}
				List<Object[]> result = surveyDetailsInfoDAO.getBoothWiseDcAndDvDetails(boothIds);
				if(result != null && result.size() > 0)
				{
					dcBoothMap = new HashMap<Long, Map<Long,String>>();
					dvBoothMap = new HashMap<Long, Map<Long,String>>();
					dvStatusBoothMap = new HashMap<Long, Map<Long,Long>>();
					dcBoothDatesMap = new HashMap<Long, Map<Long,String>>();
					dvBoothDatesMap = new HashMap<Long, Map<Long,String>>();
					List<GenericVO> userWiseList = new ArrayList<GenericVO>();
					mobileNoMap = new HashMap<Long, String>();
					for (Object[] parms	: result)
					{
						dcMap = dcBoothMap.get((Long)parms[4]);
						if(dcMap == null)
						{
							dcMap = new HashMap<Long, String>();
							dvMap = new HashMap<Long, String>();
							dvStatusMap = new HashMap<Long, Long>();
							dcDatesMap = new HashMap<Long, String>();
							dvDatesMap = new HashMap<Long, String>();
							dcBoothMap.put((Long)parms[4], dcMap);
							dvBoothMap.put((Long)parms[4], dvMap);
							dvStatusBoothMap.put((Long)parms[4], dvStatusMap);
							dcBoothDatesMap.put((Long)parms[4], dcDatesMap);
							dvBoothDatesMap.put((Long)parms[4], dvDatesMap);
						}
						GenericVO VO = new GenericVO();
						if((Long)parms[3] != null)
						{
							if((Long)parms[3] == 1)
							{
								dcMap.put((Long)parms[1], casteMap.get((Long)parms[2]));
								dcDatesMap.put((Long)parms[1], parms[7].toString());
								if(parms[9] != null)
								mobileNoMap.put((Long)parms[1], parms[9].toString());
							}
							else{
								if(verifierId.equals(Long.valueOf(parms[0].toString()))){
									dvDatesMap.put((Long)parms[1], parms[7].toString());
									dvMap.put((Long)parms[1], casteMap.get((Long)parms[2]));
									
									if(parms[8] != null){
										dvStatusMap.put((Long)parms[1],Long.valueOf(parms[8].toString()));
									}
								}
								
							}
						}
						userWiseList.add(VO);
					}
				}
				
				List<Object[]> wmDetails = surveyCallStatusDAO.getBoothWiseWmCasteUpdationDetails(boothIds);
				if(wmDetails != null && wmDetails.size() > 0)
				{
					wmBoothMap = new HashMap<Long, Map<Long,String>>();
					for (Object[] parms : wmDetails)
					{
						wmMap = wmBoothMap.get((Long)parms[3]);
						if(wmMap == null)
						{
							wmMap = new HashMap<Long, String>();
							wmBoothMap.put((Long)parms[3], wmMap);
							
						}
						if(parms[2] != null)
						{
							dcMap = dcBoothMap.get((Long)parms[3]);
							if(parms[2].toString().trim().equalsIgnoreCase("N"))
							{
								wmMap.put((Long)parms[0], casteMap.get((Long)parms[1]));
							}
							else
							{
								wmMap.put((Long)parms[0], dcMap.get((Long)parms[0]));
							}
						}
						
					}
				}
				
				
				List<Object[]> dvWmDetails = surveyCallStatusDAO.getBoothWiseDvWmCasteUpdationDetails(boothIds);
				if(dvWmDetails != null && dvWmDetails.size() > 0)
				{
					dvWmBoothMap = new HashMap<Long, Map<Long,String>>();
					for (Object[] parms : dvWmDetails)
					{
						dvWmMap = dvWmBoothMap.get((Long)parms[3]);
						if(dvWmMap == null)
						{
							dvWmMap = new HashMap<Long, String>();
							dvWmBoothMap.put((Long)parms[3], dvWmMap);
							
						}
						if(parms[2] != null)
						{
							 
							Map<Long,String> dvMapForBooth = dvBoothMap.get((Long)parms[3]);
							if(parms[2].toString().trim().equalsIgnoreCase("N"))
							{
								dvWmMap.put((Long)parms[0], casteMap.get((Long)parms[1]));
							}
							else
							{
								dvWmMap.put((Long)parms[0], dvMapForBooth.get((Long)parms[0]));
							}
						}
						
					}
				}
				if(boothIds != null && boothIds.size() > 0)
				{
					wmResultBoothMap = new HashMap<Long, Map<Long,String>>();
					for (Long boothId : boothIds)
					{
						if(wmBoothMap !=null && wmBoothMap.size() > 0)
						{
							Map<Long,String>  resultMap = checkForDcWmWithDvWm(wmBoothMap.get(boothId),dvWmBoothMap.get(boothId));
							wmResultBoothMap.put(boothId, resultMap);
						}
						
						
					}
					
				}
				
				if(boothIds != null && boothIds.size() > 0)
				{
					dcWmCollectedMap = new HashMap<Long, Map<Long,String>>();
					for(Long boothId : boothIds)
					{
						if(dcBoothMap.get(boothId) != null && dcBoothMap.get(boothId).size() > 0)
						{
							Map<Long,String>  resultMap = new HashMap<Long, String>();
							
							if(wmResultBoothMap != null && wmResultBoothMap.size() > 0)
							{
								if(wmResultBoothMap.get(boothId) != null && wmResultBoothMap.size() > 0)
								{
									checkForDcWithWm(dcBoothMap.get(boothId),wmResultBoothMap.get(boothId),resultMap);
									dcWmCollectedMap.put(boothId, resultMap);
								}
								else
								{
									dcWmCollectedMap.put(boothId, dcBoothMap.get(boothId));
								}
							}
							else
							{
								dcWmCollectedMap.put(boothId, dcBoothMap.get(boothId));
							}
							
						}
						
						
						
					}
				}
				
				
				List<Object[]> userList = surveyDetailsInfoDAO.getBoothWiseUser(boothIds,1l);
				List<Object[]> verifiersList = surveyDetailsInfoDAO.getBoothWiseUser(boothIds,4l);
				if(userList != null && userList.size() > 0)
				{
					usersMap = new HashMap<Long, String>();
					usersDateMap = new HashMap<Long, String>();
					for (Object[] objects : userList) 
					{
						String name = usersMap.get((Long)objects[0]);
						if(name == null)
						{
							usersMap.put((Long)objects[0], objects[2].toString());
							usersDateMap.put((Long)objects[0], objects[4].toString());
						}
					}
				}
				if(verifiersList != null && verifiersList.size() > 0)
				{
					verifierMap = new HashMap<Long, String>();
					verifierDateMap = new HashMap<Long, String>();
					for (Object[] objects : verifiersList)
					{
						String name = verifierMap.get((Long)objects[0]);
						if(name == null){
							if(verifierId.equals(Long.valueOf(objects[1].toString()))){
								verifierMap.put((Long)objects[0], objects[2].toString());
								verifierDateMap.put((Long)objects[0], objects[4].toString());
							}
						}
					}
				}
				List<Object[]> voterDetails = boothPublicationVoterDAO.getVoterDetailsByBoothID(boothIds);
				if(voterDetails != null && voterDetails.size() > 0)
				{
					boothWiseMap = new HashMap<Long, VerificationCompVO>();
					List<VerificationCompVO> matchedList  = null;
					List<VerificationCompVO> unMatchedList = null;
					List<VerificationCompVO> notVerifiedList = null;
					String surveyUser = null;
					String verifierUser = null;
					String dcDate = null;
					String dvDate = null;
					Integer collectdCount = 0;
					Integer updatedCount = 0;
					Integer verifedCount = 0;
					Integer notIdentifedCount = 0;
					for (Object[] parms : voterDetails)
					{
						 dcMap = dcBoothMap.get((Long)parms[4]);
						 if(dcMap != null && dcMap.size() > 0)
						 {
							 VerificationCompVO subVO = boothWiseMap.get((Long)parms[4]);
								if(subVO == null)
								{
									 subVO = new VerificationCompVO();
									 boothWiseMap.put((Long)parms[4], subVO);
									 matchedList = new ArrayList<VerificationCompVO>();
									 unMatchedList = new ArrayList<VerificationCompVO>();
									 notVerifiedList = new ArrayList<VerificationCompVO>(); 
									 if(dvBoothMap != null && dvBoothMap.size() > 0)
									 dvMap = dvBoothMap.get((Long)parms[4]);
									 if(wmResultBoothMap != null && wmResultBoothMap.size() > 0)
									 wmMap = wmResultBoothMap.get((Long)parms[4]);
									 dcDatesMap = dcBoothDatesMap.get((Long)parms[4]);
									 dvDatesMap = dcBoothDatesMap.get((Long)parms[4]);
									 dcWmMap =  dcWmCollectedMap.get((Long)parms[4]);
									 surveyUser = usersMap.get((Long)parms[4]);
									 verifierUser = verifierMap.get((Long)parms[4]);
									 dcDate = usersDateMap.get((Long)parms[4]);
									 dvDate = verifierDateMap.get((Long)parms[4]);
									 dvStatusMap = dvStatusBoothMap.get((Long)parms[4]);
									 collectdCount = 0;
									 updatedCount = 0;
									 verifedCount = 0;
									 notIdentifedCount = 0;

								}
									VerificationCompVO VO = new VerificationCompVO();
									VO.setVoterCardNO(parms[2] != null ? parms[2].toString() : "");
									VO.setVoterName(parms[1] != null ? parms[1].toString() : "");
									VO.setVoterId(parms[0] != null ? (Long)parms[0]: null  );
									VO.setHouseNo(parms[3] != null ? parms[3].toString() : "");
									VO.setPartNo(parms[6] != null ? parms[6].toString() : "");
									VO.setPanchayatName(parms[7] != null ? parms[7].toString() : "");
									if(mobileNoMap.get((Long)parms[0]) != null)
									{
										VO.setMobileNO(mobileNoMap.get((Long)parms[0]));
									}
									
									if(dvStatusMap != null && dvStatusMap.size() > 0)
									{
										Long statusId = dvStatusMap.get((Long)parms[0]);
										if(statusId != null)
										{
											if(statusId.longValue() == 1l) // collected
											{
												collectdCount = collectdCount + 1;
												VO.setVerifierStatus("COLLECTED");
											}
											else if(statusId.longValue() == 2l) // updated
											{
												updatedCount = updatedCount + 1;
												VO.setVerifierStatus("UPDATED");
											}
											else// verified
											{
												verifedCount = verifedCount + 1;
												VO.setVerifierStatus("VERIFIED");
											}
										}
										else
										{
											notIdentifedCount = notIdentifedCount + 1;
											VO.setVerifierStatus("NOT IDENTIFED");
										}
										
									}
									if(dcMap != null && dcMap.size() > 0)
									{
										VO.setDcCaste(dcMap.get((Long)parms[0]) != null ? dcMap.get((Long)parms[0]) : "-");
									}
									else
									{
										VO.setDcCaste("-");
									}
									if(dvMap != null && dvMap.size() > 0)
									{
										VO.setDvCaste(dvMap.get((Long)parms[0]) != null ? dvMap.get((Long)parms[0]) : "-");
									}
									else
									{
										VO.setDvCaste( "-");
									}
									if(wmMap != null && wmMap.size() > 0)
									{
										VO.setWmCaste(wmMap.get((Long)parms[0]) != null ? wmMap.get((Long)parms[0]) : "-");
									}
									else
									{
										VO.setWmCaste("-");
									}
									
									VO.setRelativeName(parms[5] != null ? parms[5].toString() : "");
									//VO.setDate(dcDate);
									//VO.setVerifierDate(dvDate);
									//VO.setSurveyUser(surveyUser);
									//VO.setVerifierUser(verifierUser);
									if(dcWmMap != null && dcWmMap.size() > 0)
									{
										if(dvMap.get((Long)parms[0] ) != null)
										{
											if(dcWmMap.get((Long)parms[0]) != null && dvMap.get((Long)parms[0]) != null)
											{
												if(dcWmMap.get((Long)parms[0]) .equalsIgnoreCase(dvMap.get((Long)parms[0])))
												{
													matchedList.add(VO);
												}
												/*else if(dcMap.get((Long)parms[0]).equalsIgnoreCase(wmMap.get((Long)parms[0])))
												{
													matchedList.add(VO);
												}	
												else if(dvMap.get((Long)parms[0]) == null )
												{
													notVerifiedList.add(VO);
												}*/
												else
												{
													unMatchedList.add(VO);
												}

											}
											
										}
										else
										{
											/*if(wmMap != null)
											{
												if(dcMap.get((Long)parms[0]).equalsIgnoreCase(wmMap.get((Long)parms[0])))
												{
													matchedList.add(VO);
												}
												else if(dcMap.get((Long)parms[0]) != null && wmMap.get((Long)parms[0]) != null)
												{
													unMatchedList.add(VO);
												}
												else
												{
													notVerifiedList.add(VO);
												}
											}
											else
											{*/
												notVerifiedList.add(VO);
											//}
											
											
										}
									}
									else
									{
										notVerifiedList.add(VO);
									}
								subVO.setBoothId((Long)parms[4]);
								subVO.setDate(dcDate);
								subVO.setVerifierDate(dvDate);
								subVO.setSurveyUser(surveyUser);
								subVO.setVerifierUser(verifierUser);
								subVO.setMatchedList(matchedList);
								subVO.setUnMatchedList(unMatchedList);
								subVO.setNotVerifiedList(notVerifiedList);
								subVO.setTotalCount(unMatchedList.size() + matchedList.size() + notVerifiedList.size());
								subVO.setUnMatchedCount(unMatchedList.size());
								subVO.setMatchedCount(matchedList.size());
								subVO.setCollectedCount(collectdCount);
								subVO.setUpdatedCount(updatedCount);
								subVO.setVerifieCount(verifedCount);
								subVO.setNotIdentifedCount(notIdentifedCount);
								subVO.setNotVerifiedCount(notVerifiedList.size());
						 }
						
					}
					if(boothWiseMap != null && boothWiseMap.size() > 0)
					{
						List<Long> booths = new ArrayList<Long>(boothWiseMap.keySet());
						returnList = new ArrayList<VerificationCompVO>();
						for (Long boothId : booths)
						{
							returnList.add(boothWiseMap.get(boothId));
						}
					}
				}
			}
			
		} 
		catch (Exception e)
		{
			LOG.error("Exception raised in checkForVerifierData", e);
		}
		return returnList;
	}
	
	public List<VerificationCompVO> checkForVerifierDataForWM(List<Long> boothIds)
	{
		List<VerificationCompVO> returnList = null;
		try 
		{
			List<Object[]> castesList = casteStateDAO.getAllCasteDetailsForVoters(1l);
			if(castesList != null && castesList.size() > 0)
			{
				Map<Long,Map<Long,String>> dcBoothMap = null;// booth wise Data Collector Caste Collected 
				Map<Long,Map<Long,String>> dvBoothMap = null;// booth wise Data Verifier Caste Collected
				Map<Long,Map<Long,String>> wmBoothMap = null;// booth Wise Web Moniter Caste Collected
				Map<Long,Map<Long,String>> dvWmBoothMap = null;// booth Wise Web Moniter Caste Collected
				Map<Long,Map<Long,String>> dcBoothDatesMap = null;// Day wise booth Wise Data Collector 
				Map<Long,Map<Long,String>> dvBoothDatesMap = null;// Day wise booth Wise Data Verified 
				Map<Long,VerificationCompVO> boothWiseMap = null;// booth wise total recoreds
				Map<Long,Map<Long,Long>> dvStatusBoothMap = null;// booth wise Data Verifier Caste Collected
				Map<Long,String> dcMap = null;// Map<voterId,Caste>
				Map<Long,String> dvMap = null;//Map<voterId,Caste>
				Map<Long,String> wmMap = null;//Map<voterId,Caste>
				Map<Long,String> dvWmMap = null;//Map<voterId,Caste>
				Map<Long,String> casteMap = new HashMap<Long, String>();//Map<casteId,CasteName>
				Map<Long,Map<Long,String>> dcWmCollectedMap = null;// Day wise booth Wise Data Collector 
				Map<Long,String> dcDatesMap = null;
				Map<Long,String> dvDatesMap = null;
				Map<Long,String> dcWmMap = null;
				Map<Long,String> usersMap =null;
				Map<Long,String> verifierMap = null;
				Map<Long,String> usersDateMap =null;
				Map<Long,String> verifierDateMap = null;
				Map<Long,Long> dvStatusMap = null;
				Map<Long,String> mobileNoMap = null;
				//Map<Long,Map<Long,String>> wmResultBoothMap = null;// booth Wise Web Moniter Caste Collected
				for (Object[] objects : castesList)
				{
					casteMap.put((Long)objects[0], objects[1].toString());
				}
				List<Object[]> result = surveyDetailsInfoDAO.getBoothWiseDcAndDvDetails(boothIds);
				if(result != null && result.size() > 0)
				{
					dcBoothMap = new HashMap<Long, Map<Long,String>>();
					dvBoothMap = new HashMap<Long, Map<Long,String>>();
					dvStatusBoothMap = new HashMap<Long, Map<Long,Long>>();
					dcBoothDatesMap = new HashMap<Long, Map<Long,String>>();
					dvBoothDatesMap = new HashMap<Long, Map<Long,String>>();
					List<GenericVO> userWiseList = new ArrayList<GenericVO>();
					mobileNoMap = new HashMap<Long, String>();
					for (Object[] parms	: result)
					{
						dcMap = dcBoothMap.get((Long)parms[4]);
						if(dcMap == null)
						{
							dcMap = new HashMap<Long, String>();
							dvMap = new HashMap<Long, String>();
							dvStatusMap = new HashMap<Long, Long>();
							dcDatesMap = new HashMap<Long, String>();
							dvDatesMap = new HashMap<Long, String>();
							dcBoothMap.put((Long)parms[4], dcMap);
							dvBoothMap.put((Long)parms[4], dvMap);
							dvStatusBoothMap.put((Long)parms[4], dvStatusMap);
							dcBoothDatesMap.put((Long)parms[4], dcDatesMap);
							dvBoothDatesMap.put((Long)parms[4], dvDatesMap);
						}
						GenericVO VO = new GenericVO();
						if((Long)parms[3] != null)
						{
							if((Long)parms[3] == 1)
							{
								dcMap.put((Long)parms[1], casteMap.get((Long)parms[2]));
								dcDatesMap.put((Long)parms[1], parms[7].toString());
								if(parms[9] != null)
								mobileNoMap.put((Long)parms[1], parms[9].toString());
							}
							else
							{
								dvDatesMap.put((Long)parms[1], parms[7].toString());
								dvMap.put((Long)parms[1], casteMap.get((Long)parms[2]));
								
								if(parms[8] != null)
								{
									dvStatusMap.put((Long)parms[1],Long.valueOf(parms[8].toString()));
								}
								
							}
						}
						userWiseList.add(VO);
					}
				}
				
				List<Object[]> wmDetails = surveyCallStatusDAO.getBoothWiseWmCasteUpdationDetails(boothIds);
				if(wmDetails != null && wmDetails.size() > 0)
				{
					wmBoothMap = new HashMap<Long, Map<Long,String>>();
					for (Object[] parms : wmDetails)
					{
						wmMap = wmBoothMap.get((Long)parms[3]);
						if(wmMap == null)
						{
							wmMap = new HashMap<Long, String>();
							wmBoothMap.put((Long)parms[3], wmMap);
							
						}
						if(parms[2] != null)
						{
							dcMap = dcBoothMap.get((Long)parms[3]);
							if(parms[2].toString().trim().equalsIgnoreCase("N"))
							{
								wmMap.put((Long)parms[0], casteMap.get((Long)parms[1]));
							}
							else
							{
								wmMap.put((Long)parms[0], dcMap.get((Long)parms[0]));
							}
						}
						
					}
				}
				
				
				
				if(boothIds != null && boothIds.size() > 0)
				{
					dcWmCollectedMap = new HashMap<Long, Map<Long,String>>();
					for(Long boothId : boothIds)
					{
						if(dcBoothMap.get(boothId) != null && dcBoothMap.get(boothId).size() > 0)
						{
							Map<Long,String>  resultMap = new HashMap<Long, String>();
							
							if(wmBoothMap != null && wmBoothMap.size() > 0)
							{
								if(wmBoothMap.get(boothId) != null && wmBoothMap.size() > 0)
								{
									checkForDcWithWm(dcBoothMap.get(boothId),wmBoothMap.get(boothId),resultMap);
									dcWmCollectedMap.put(boothId, resultMap);
								}
								else
								{
									dcWmCollectedMap.put(boothId, dcBoothMap.get(boothId));
								}
							}
							else
							{
								dcWmCollectedMap.put(boothId, dcBoothMap.get(boothId));
							}
							
						}
						
						
						
					}
				}
				
				
				List<Object[]> userList = surveyDetailsInfoDAO.getBoothWiseUser(boothIds,1l);
				List<Object[]> verifiersList = surveyDetailsInfoDAO.getBoothWiseUser(boothIds,4l);
				if(userList != null && userList.size() > 0)
				{
					usersMap = new HashMap<Long, String>();
					usersDateMap = new HashMap<Long, String>();
					for (Object[] objects : userList) 
					{
						String name = usersMap.get((Long)objects[0]);
						if(name == null)
						{
							usersMap.put((Long)objects[0], objects[2].toString());
							usersDateMap.put((Long)objects[0], objects[4].toString());
						}
					}
				}
				if(userList != null && userList.size() > 0)
				{
					verifierMap = new HashMap<Long, String>();
					verifierDateMap = new HashMap<Long, String>();
					for (Object[] objects : verifiersList)
					{
						String name = verifierMap.get((Long)objects[0]);
						if(name == null)
						{
							verifierMap.put((Long)objects[0], objects[2].toString());
							verifierDateMap.put((Long)objects[0], objects[4].toString());
						}
					}
				}
				List<Object[]> voterDetails = boothPublicationVoterDAO.getVoterDetailsByBoothID(boothIds);
				if(voterDetails != null && voterDetails.size() > 0)
				{
					boothWiseMap = new HashMap<Long, VerificationCompVO>();
					List<VerificationCompVO> matchedList  = null;
					List<VerificationCompVO> unMatchedList = null;
					List<VerificationCompVO> notVerifiedList = null;
					String surveyUser = null;
					String verifierUser = null;
					String dcDate = null;
					String dvDate = null;
					Integer collectdCount = 0;
					Integer updatedCount = 0;
					Integer verifedCount = 0;
					Integer notIdentifedCount = 0;
					for (Object[] parms : voterDetails)
					{
						 dcMap = dcBoothMap.get((Long)parms[4]);
						 if(dcMap != null && dcMap.size() > 0)
						 {
							 VerificationCompVO subVO = boothWiseMap.get((Long)parms[4]);
								if(subVO == null)
								{
									 subVO = new VerificationCompVO();
									 boothWiseMap.put((Long)parms[4], subVO);
									 matchedList = new ArrayList<VerificationCompVO>();
									 unMatchedList = new ArrayList<VerificationCompVO>();
									 notVerifiedList = new ArrayList<VerificationCompVO>(); 
									 if(dvBoothMap != null && dvBoothMap.size() > 0)
									 dvMap = dvBoothMap.get((Long)parms[4]);
									 if(wmBoothMap != null && wmBoothMap.size() > 0)
									 wmMap = wmBoothMap.get((Long)parms[4]);
									 dcDatesMap = dcBoothDatesMap.get((Long)parms[4]);
									 dvDatesMap = dcBoothDatesMap.get((Long)parms[4]);
									 dcWmMap =  dcWmCollectedMap.get((Long)parms[4]);
									 surveyUser = usersMap.get((Long)parms[4]);
									 verifierUser = verifierMap.get((Long)parms[4]);
									 dcDate = usersDateMap.get((Long)parms[4]);
									 dvDate = verifierDateMap.get((Long)parms[4]);
									 dvStatusMap = dvStatusBoothMap.get((Long)parms[4]);
									 collectdCount = 0;
									 updatedCount = 0;
									 verifedCount = 0;
									 notIdentifedCount = 0;

								}
									VerificationCompVO VO = new VerificationCompVO();
									VO.setVoterCardNO(parms[2] != null ? parms[2].toString() : "");
									VO.setVoterName(parms[1] != null ? parms[1].toString() : "");
									VO.setVoterId(parms[0] != null ? (Long)parms[0]: null  );
									VO.setHouseNo(parms[3] != null ? parms[3].toString() : "");
									VO.setPartNo(parms[6] != null ? parms[6].toString() : "");
									//VO.setPanchayatName(parms[7] != null ? parms[7].toString() : "");
									if(mobileNoMap.get((Long)parms[0]) != null)
									{
										VO.setMobileNO(mobileNoMap.get((Long)parms[0]));
									}
									
									if(dvStatusMap != null && dvStatusMap.size() > 0)
									{
										Long statusId = dvStatusMap.get((Long)parms[0]);
										if(statusId != null)
										{
											if(statusId.longValue() == 1l) // collected
											{
												collectdCount = collectdCount + 1;
												VO.setVerifierStatus("COLLECTED");
											}
											else if(statusId.longValue() == 2l) // updated
											{
												updatedCount = updatedCount + 1;
												VO.setVerifierStatus("UPDATED");
											}
											else// verified
											{
												verifedCount = verifedCount + 1;
												VO.setVerifierStatus("VERIFIED");
											}
										}
										else
										{
											notIdentifedCount = notIdentifedCount + 1;
											VO.setVerifierStatus("NOT IDENTIFED");
										}
										
									}
									if(dcMap != null && dcMap.size() > 0)
									{
										VO.setDcCaste(dcMap.get((Long)parms[0]) != null ? dcMap.get((Long)parms[0]) : "-");
									}
									else
									{
										VO.setDcCaste("-");
									}
									if(dvMap != null && dvMap.size() > 0)
									{
										VO.setDvCaste(dvMap.get((Long)parms[0]) != null ? dvMap.get((Long)parms[0]) : "-");
									}
									else
									{
										VO.setDvCaste( "-");
									}
									if(wmMap != null && wmMap.size() > 0)
									{
										VO.setWmCaste(wmMap.get((Long)parms[0]) != null ? wmMap.get((Long)parms[0]) : "-");
									}
									else
									{
										VO.setWmCaste("-");
									}
									
									VO.setRelativeName(parms[5] != null ? parms[5].toString() : "");
									//VO.setDate(dcDate);
									//VO.setVerifierDate(dvDate);
									//VO.setSurveyUser(surveyUser);
									//VO.setVerifierUser(verifierUser);
									if(dcWmMap != null && dcWmMap.size() > 0)
									{
										if(dvMap.get((Long)parms[0] ) != null)
										{
											if(dcWmMap.get((Long)parms[0]) != null && dvMap.get((Long)parms[0]) != null)
											{
												if(dcWmMap.get((Long)parms[0]) .equalsIgnoreCase(dvMap.get((Long)parms[0])))
												{
													matchedList.add(VO);
												}
												/*else if(dcMap.get((Long)parms[0]).equalsIgnoreCase(wmMap.get((Long)parms[0])))
												{
													matchedList.add(VO);
												}	
												else if(dvMap.get((Long)parms[0]) == null )
												{
													notVerifiedList.add(VO);
												}*/
												else
												{
													unMatchedList.add(VO);
												}

											}
											
										}
										else
										{
											/*if(wmMap != null)
											{
												if(dcMap.get((Long)parms[0]).equalsIgnoreCase(wmMap.get((Long)parms[0])))
												{
													matchedList.add(VO);
												}
												else if(dcMap.get((Long)parms[0]) != null && wmMap.get((Long)parms[0]) != null)
												{
													unMatchedList.add(VO);
												}
												else
												{
													notVerifiedList.add(VO);
												}
											}
											else
											{*/
												notVerifiedList.add(VO);
											//}
											
											
										}
									}
									else
									{
										notVerifiedList.add(VO);
									}
								subVO.setBoothId((Long)parms[4]);
								subVO.setDate(dcDate);
								subVO.setVerifierDate(dvDate);
								subVO.setSurveyUser(surveyUser);
								subVO.setVerifierUser(verifierUser);
								subVO.setMatchedList(matchedList);
								subVO.setUnMatchedList(unMatchedList);
								subVO.setNotVerifiedList(notVerifiedList);
								subVO.setTotalCount(unMatchedList.size() + matchedList.size() + notVerifiedList.size());
								subVO.setUnMatchedCount(unMatchedList.size());
								subVO.setMatchedCount(matchedList.size());
								subVO.setCollectedCount(collectdCount);
								subVO.setUpdatedCount(updatedCount);
								subVO.setVerifieCount(verifedCount);
								subVO.setNotIdentifedCount(notIdentifedCount);
								subVO.setNotVerifiedCount(notVerifiedList.size());
						 }
						
					}
					if(boothWiseMap != null && boothWiseMap.size() > 0)
					{
						List<Long> booths = new ArrayList<Long>(boothWiseMap.keySet());
						returnList = new ArrayList<VerificationCompVO>();
						for (Long boothId : booths)
						{
							returnList.add(boothWiseMap.get(boothId));
						}
					}
				}
			}
			
		} 
		catch (Exception e)
		{
			LOG.error("Exception raised in checkForVerifierData", e);
		}
		return returnList;
	}
	
}
 