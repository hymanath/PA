package com.itgrids.partyanalyst.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionCallbackWithoutResult;
import org.springframework.transaction.support.TransactionTemplate;

import com.itgrids.partyanalyst.dao.IRegionWiseSurveysDAO;
import com.itgrids.partyanalyst.dao.ISurveyAccessUsersDAO;
import com.itgrids.partyanalyst.dao.ISurveyCompletedLocationsDetailsDAO;
import com.itgrids.partyanalyst.dao.ISurveyDAO;
import com.itgrids.partyanalyst.dao.ISurveyDetailsInfoDAO;
import com.itgrids.partyanalyst.dao.ISurveyUserConstituencyDAO;
import com.itgrids.partyanalyst.dao.ISurveyUserDAO;
import com.itgrids.partyanalyst.dao.ISurveyUserRelationDAO;
import com.itgrids.partyanalyst.dao.IUpdationDetailsDAO;
import com.itgrids.partyanalyst.dao.IUserDAO;
import com.itgrids.partyanalyst.dao.IWebMonitorCompletedLocationsDetailsDAO;
import com.itgrids.partyanalyst.dao.IWebMonitoringAssignedUsersDAO;
import com.itgrids.partyanalyst.dto.GenericVO;
import com.itgrids.partyanalyst.dto.HamletCountInputVO;
import com.itgrids.partyanalyst.dto.HamletCountVo;
import com.itgrids.partyanalyst.dto.PanchayatHamletsCountVo;
import com.itgrids.partyanalyst.dto.ResultCodeMapper;
import com.itgrids.partyanalyst.dto.ResultStatus;
import com.itgrids.partyanalyst.dto.SelectOptionVO;
import com.itgrids.partyanalyst.dto.SurveyReportVO;
import com.itgrids.partyanalyst.model.SurveyAccessUsers;
import com.itgrids.partyanalyst.model.UpdationDetails;
import com.itgrids.partyanalyst.service.ISurveyDetailsService;
import com.itgrids.partyanalyst.utils.DateUtilService;

public class SurveyDetailsService implements ISurveyDetailsService {

	private static final Logger log = Logger.getLogger(SurveyDetailsService.class);
	private ISurveyDAO surveyDAO;
	private TransactionTemplate transactionTemplate;
	private IUpdationDetailsDAO updationDetailsDAO;
	private IUserDAO userDAO;
	private ISurveyAccessUsersDAO surveyAccessUsersDAO;
	private DateUtilService dateUtilService = new DateUtilService();
	private IRegionWiseSurveysDAO regionWiseSurveysDAO;
	
	
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
	private ISurveyCompletedLocationsDetailsDAO surveyCompletedLocationsDetailsDAO;
	
	@Autowired
	private IWebMonitorCompletedLocationsDetailsDAO webMonitorCompletedLocationsDetailsDAO;
	
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
			log.info("Entered into getAllRegisterUsersForAssigningParty()");
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
			log.error("Exception Occured in getAllRegisterUsersForAssigningParty(), Exception - "+e);
			e.printStackTrace();
			return  null;
		}
	}
	
	public ResultStatus saveSurveyDetails(final Long userId,final Long surveyId){
		ResultStatus resultStatus = new ResultStatus();
		try{
			log.debug("Entered into saveSurveyDetails() method in Survey Details Service()");
			
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
			log.error("Exception encountered, Check log for Details - ",e);
			resultStatus.setExceptionEncountered(e);
			resultStatus.setResultCode(ResultCodeMapper.FAILURE);
			return resultStatus;
		}	
	}
	
	
	public Map<String,String> getSurveyDetailsByRegion(Long regionId)
	{
		log.debug("Entered into the getSurveyDetailsByRegion service method");

		Map<String,String> surveyDetailsMap = new HashMap<String, String>();
		try
		{
			List<Object[]> list = regionWiseSurveysDAO.getSurveyDetailsByRegion(regionId);
			
			for(Object[] obj:list)
				surveyDetailsMap.put(obj[0].toString(), obj[1].toString());
			
		}catch(Exception e)
		{
			//e.printStackTrace();
			log.error("Exception raised in  getSurveyDetailsByRegion service method");
		}
		return surveyDetailsMap;
	}
	
	public List<GenericVO> getConstituencyWiseLeaders(Long constituencyId)
	{
		log.debug("Entered into the getConstituencyWiseLeaders service method");
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
			log.error("Exception raised in  getConstituencyWiseLeaders service method");
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
		log.info("Entered into unTagConstituencyForAUser method");
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
			log.error("Exception raised in unTagConstituencyForAUser", e);
			resultStatus.setResultCode(3);
			resultStatus.setMessage("Exception");
		}
		return resultStatus;
	}


	public List<GenericVO> getAssignedSurveyUsersForWebMontringTeam(Long userId)
	{
		log.info("Entered into getAssignedSurveyUsersForWebMontringTeam method");
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
	    	log.error("Exception raised in getAssignedSurveyUsersForWebMontringTeam", e);
		}
	    return returnList;
	}
	
	public List<GenericVO> getNotStartedUsersDetails(Long webMonitorUserId,Long leaderId)
	{
		log.info("Entered into getNotStartedUsersDetails method");
		
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
			e.printStackTrace();
	    	log.error("Exception raised in getNotStartedUsersDetails", e);
			
		}
		
		return resultList;
		
	}
	
public GenericVO getSurveyStatusBoothList(Long constituencyId){
		
		log.info("Entered into getSurveyStatusBoothList method in SurveyDetailsService class.");
		
		 GenericVO genericVO = new GenericVO();

		try
		{
			 List<GenericVO> resultList = new ArrayList<GenericVO>();
			 
			List<Object[]> processingBooths  =  surveyDetailsInfoDAO.getProcecingBoothCountByConstId(constituencyId);
			List<Long> procesingBoothIds = new ArrayList<Long>();
			if(processingBooths != null && processingBooths.size()>0){				
				
				for (Object[] booth : processingBooths) {
					procesingBoothIds.add((Long) booth[0]);
				}
			}
		
			if(procesingBoothIds != null && procesingBoothIds.size()>0){			
				List<Long> completedBooths  = surveyCompletedLocationsDetailsDAO.getSurveyCompletedCountByConstId(9L,procesingBoothIds);
								
				if(completedBooths != null && completedBooths.size()>0){	
					
					Long procesing = Long.valueOf(String.valueOf(processingBooths.size())) - Long.valueOf(String.valueOf(completedBooths.size()));
					
					GenericVO procesingVO = new GenericVO();
					procesingVO.setName("procesing");
					procesingVO.setId(procesing);
					resultList.add(procesingVO);
					
					
					
					GenericVO completedVO = new GenericVO();
					completedVO.setName("completed");
					completedVO.setId(Long.valueOf(String.valueOf(completedBooths.size())));
					resultList.add(completedVO);

					
					Long count = webMonitorCompletedLocationsDetailsDAO.getSurveyWMCompletedCountByConstId(9L,completedBooths);
					
					GenericVO WMCompletedVO = new GenericVO();
					WMCompletedVO.setName("WMCompleted");
					WMCompletedVO.setId(count);
					resultList.add(WMCompletedVO);
					
				}
			}
			
			genericVO.setGenericVOList(resultList);
		}catch(Exception e)
		{
			e.printStackTrace();
	    	log.error("Exception raised in getSurveyStatusBoothList  method in SurveyDetailsService class.", e);
			
		}
		
		return genericVO;
		
	}

	@SuppressWarnings("unchecked")
	public  PanchayatHamletsCountVo   getSurveyDataCountForHamletsByPanchayats(HamletCountInputVO inputVo)
	{
		PanchayatHamletsCountVo responseVo= new PanchayatHamletsCountVo();
		
		List<?> details=surveyDetailsInfoDAO.getHamletCountBasedOnPanchayIds(inputVo.getPanchayatIds());
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
	
	
	
}
 