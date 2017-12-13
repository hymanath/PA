package com.itgrids.partyanalyst.service.impl;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.apache.log4j.Logger;

import com.itgrids.partyanalyst.dao.IActivityMemberAccessLevelDAO;
import com.itgrids.partyanalyst.dao.ITdpCadreDAO;
import com.itgrids.partyanalyst.dao.ITdpCommitteeEnrollmentDAO;
import com.itgrids.partyanalyst.dao.ITrainingCampAttendanceDAO;
import com.itgrids.partyanalyst.dao.ITrainingCampBatchAttendeeDAO;
import com.itgrids.partyanalyst.dao.ITrainingCampBatchDAO;
import com.itgrids.partyanalyst.dao.ITrainingCampCadreFeedbackDetailsDAO;
import com.itgrids.partyanalyst.dao.ITrainingCampDetailsInfoDAO;
import com.itgrids.partyanalyst.dao.ITrainingCampProgramDAO;
import com.itgrids.partyanalyst.dto.ActivityMemberVO;
import com.itgrids.partyanalyst.dto.KeyValueVO;
import com.itgrids.partyanalyst.dto.TrainingCampProgramVO;
import com.itgrids.partyanalyst.dto.TrainingCampSurveyVO;
import com.itgrids.partyanalyst.dto.TrainingCampVO;
import com.itgrids.partyanalyst.dto.UserTypeVO;
import com.itgrids.partyanalyst.service.ICoreDashboardCoreService;
import com.itgrids.partyanalyst.service.ICoreDashboardGenericService;
import com.itgrids.partyanalyst.utils.CommonMethodsUtilService;
import com.itgrids.partyanalyst.utils.DateUtilService;
import com.itgrids.partyanalyst.utils.IConstants;
import com.itgrids.partyanalyst.utils.SetterAndGetterUtilService;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.GenericType;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.config.ClientConfig;
import com.sun.jersey.api.client.config.DefaultClientConfig;
import com.sun.jersey.api.json.JSONConfiguration;


public class CoreDashboardCoreService implements ICoreDashboardCoreService {
	private final static Logger LOG = Logger.getLogger(CoreDashboardCoreService.class);
	private CommonMethodsUtilService commonMethodsUtilService;
	private SetterAndGetterUtilService setterAndGetterUtilService;
	private ITrainingCampDetailsInfoDAO trainingCampDetailsInfoDAO;
	private ICoreDashboardGenericService coreDashboardGenericService;
	private ITrainingCampCadreFeedbackDetailsDAO trainingCampCadreFeedbackDetailsDAO;
	private ITrainingCampProgramDAO trainingCampProgramDAO;
	private ITdpCommitteeEnrollmentDAO tdpCommitteeEnrollmentDAO;
	private IActivityMemberAccessLevelDAO activityMemberAccessLevelDAO;
	private ITrainingCampBatchDAO trainingCampBatchDAO;
	private ITrainingCampAttendanceDAO trainingCampAttendanceDAO;
	private ITdpCadreDAO tdpCadreDAO;
	private ITrainingCampBatchAttendeeDAO trainingCampBatchAttendeeDAO;
	
	public ITrainingCampBatchAttendeeDAO getTrainingCampBatchAttendeeDAO() {
		return trainingCampBatchAttendeeDAO;
	}

	public void setTrainingCampBatchAttendeeDAO(
			ITrainingCampBatchAttendeeDAO trainingCampBatchAttendeeDAO) {
		this.trainingCampBatchAttendeeDAO = trainingCampBatchAttendeeDAO;
	}

	public CommonMethodsUtilService getCommonMethodsUtilService() {
		return commonMethodsUtilService;
	}

	public void setCommonMethodsUtilService(
			CommonMethodsUtilService commonMethodsUtilService) {
		this.commonMethodsUtilService = commonMethodsUtilService;
	}

	public SetterAndGetterUtilService getSetterAndGetterUtilService() {
		return setterAndGetterUtilService;
	}

	public void setSetterAndGetterUtilService(
			SetterAndGetterUtilService setterAndGetterUtilService) {
		this.setterAndGetterUtilService = setterAndGetterUtilService;
	}

	public ITrainingCampDetailsInfoDAO getTrainingCampDetailsInfoDAO() {
		return trainingCampDetailsInfoDAO;
	}

	public void setTrainingCampDetailsInfoDAO(
			ITrainingCampDetailsInfoDAO trainingCampDetailsInfoDAO) {
		this.trainingCampDetailsInfoDAO = trainingCampDetailsInfoDAO;
	}

	public ICoreDashboardGenericService getCoreDashboardGenericService() {
		return coreDashboardGenericService;
	}

	public void setCoreDashboardGenericService(
			ICoreDashboardGenericService coreDashboardGenericService) {
		this.coreDashboardGenericService = coreDashboardGenericService;
	}

	public ITrainingCampProgramDAO getTrainingCampProgramDAO() {
		return trainingCampProgramDAO;
	}

	public void setTrainingCampProgramDAO(
			ITrainingCampProgramDAO trainingCampProgramDAO) {
		this.trainingCampProgramDAO = trainingCampProgramDAO;
	}

	public ITdpCommitteeEnrollmentDAO getTdpCommitteeEnrollmentDAO() {
		return tdpCommitteeEnrollmentDAO;
	}

	public void setTdpCommitteeEnrollmentDAO(
			ITdpCommitteeEnrollmentDAO tdpCommitteeEnrollmentDAO) {
		this.tdpCommitteeEnrollmentDAO = tdpCommitteeEnrollmentDAO;
	}

	public IActivityMemberAccessLevelDAO getActivityMemberAccessLevelDAO() {
		return activityMemberAccessLevelDAO;
	}

	public void setActivityMemberAccessLevelDAO(
			IActivityMemberAccessLevelDAO activityMemberAccessLevelDAO) {
		this.activityMemberAccessLevelDAO = activityMemberAccessLevelDAO;
	}

	public ITrainingCampBatchDAO getTrainingCampBatchDAO() {
		return trainingCampBatchDAO;
	}

	public void setTrainingCampBatchDAO(ITrainingCampBatchDAO trainingCampBatchDAO) {
		this.trainingCampBatchDAO = trainingCampBatchDAO;
	}

	public ITrainingCampAttendanceDAO getTrainingCampAttendanceDAO() {
		return trainingCampAttendanceDAO;
	}

	public void setTrainingCampAttendanceDAO(
			ITrainingCampAttendanceDAO trainingCampAttendanceDAO) {
		this.trainingCampAttendanceDAO = trainingCampAttendanceDAO;
	}

	public ITdpCadreDAO getTdpCadreDAO() {
		return tdpCadreDAO;
	}

	public void setTdpCadreDAO(ITdpCadreDAO tdpCadreDAO) {
		this.tdpCadreDAO = tdpCadreDAO;
	}

	public ITrainingCampCadreFeedbackDetailsDAO getTrainingCampCadreFeedbackDetailsDAO() {
		return trainingCampCadreFeedbackDetailsDAO;
	}

	public void setTrainingCampCadreFeedbackDetailsDAO(
			ITrainingCampCadreFeedbackDetailsDAO trainingCampCadreFeedbackDetailsDAO) {
		this.trainingCampCadreFeedbackDetailsDAO = trainingCampCadreFeedbackDetailsDAO;
	}
	/*
	 * Swadhin 
	 * @see com.itgrids.partyanalyst.service.ICoreDashboardCoreService#getFeedbackOnLeaders(java.lang.Long, java.util.List, java.util.List, java.lang.Long, java.util.List, java.lang.Long, java.lang.Long)
	 */
	
	

	@SuppressWarnings("static-access")
	public List<TrainingCampSurveyVO> getFeedbackOnLeaders(Long userAccessLevelId, List<Long> userAccessLevelValues, List<Long> trainingProgramIds,Long traingCampEnrollmentYearId,List<Long> trainingCampLevelIds,Long groupType){
		List<TrainingCampSurveyVO> finalVOList1 = new ArrayList<TrainingCampSurveyVO>();
		List<TrainingCampSurveyVO> finalVOList2 = new ArrayList<TrainingCampSurveyVO>();
		List<TrainingCampSurveyVO> finalVOList = new ArrayList<TrainingCampSurveyVO>();
		TrainingCampSurveyVO campSurveyVO = null;
	    try{
	    	List<Object[]> list1 = trainingCampCadreFeedbackDetailsDAO.getCommunicationFeedbackOnLeaders(userAccessLevelId,userAccessLevelValues,trainingProgramIds,traingCampEnrollmentYearId,trainingCampLevelIds,groupType,1L);
	    	List<Object[]> list2 = trainingCampCadreFeedbackDetailsDAO.getCommunicationFeedbackOnLeaders(userAccessLevelId,userAccessLevelValues,trainingProgramIds,traingCampEnrollmentYearId,trainingCampLevelIds,groupType,2L);
	    	if(list1 != null && list1.size() > 0){
	    		buildTemplateForLocation(list1,finalVOList1);
	    		for(Object[] param : list1){
	    			campSurveyVO = (TrainingCampSurveyVO) setterAndGetterUtilService.getMatchedVOfromList(finalVOList1, "id", commonMethodsUtilService.getLongValueForObject(param[0]).toString());
	    			if(commonMethodsUtilService.getLongValueForObject(param[2]).longValue() == 1L){
	    				campSurveyVO.setPoor(commonMethodsUtilService.getLongValueForObject(param[3]));
	    			}else if(commonMethodsUtilService.getLongValueForObject(param[2]).longValue() == 2L){
	    				campSurveyVO.setAverage(commonMethodsUtilService.getLongValueForObject(param[3]));
	    			}else if(commonMethodsUtilService.getLongValueForObject(param[2]).longValue() == 3L){
	    				campSurveyVO.setGood(commonMethodsUtilService.getLongValueForObject(param[3]));
	    			}else if(commonMethodsUtilService.getLongValueForObject(param[2]).longValue() == 4L){
	    				campSurveyVO.setVeryGood(commonMethodsUtilService.getLongValueForObject(param[3]));
	    			}else if(commonMethodsUtilService.getLongValueForObject(param[2]).longValue() == 5L){
	    				campSurveyVO.setExcellent(commonMethodsUtilService.getLongValueForObject(param[3]));
	    			}
	    		}
	    	}
	    	TrainingCampSurveyVO vo1 = new TrainingCampSurveyVO();
	    	vo1.setId(1L);
	    	vo1.setName("How is leader's communication ckills?");
	    	vo1.getProgramsList().addAll(finalVOList1);
	    	if(list2 != null && list2.size() > 0){
	    		buildTemplateForLocation(list2,finalVOList2);
	    		for(Object[] param : list2){
	    			campSurveyVO = (TrainingCampSurveyVO) setterAndGetterUtilService.getMatchedVOfromList(finalVOList2, "id", commonMethodsUtilService.getLongValueForObject(param[0]).toString());
	    			if(commonMethodsUtilService.getLongValueForObject(param[2]).longValue() == 1L){
	    				campSurveyVO.setPoor(commonMethodsUtilService.getLongValueForObject(param[3]));
	    			}else if(commonMethodsUtilService.getLongValueForObject(param[2]).longValue() == 2L){
	    				campSurveyVO.setAverage(commonMethodsUtilService.getLongValueForObject(param[3]));
	    			}else if(commonMethodsUtilService.getLongValueForObject(param[2]).longValue() == 3L){
	    				campSurveyVO.setGood(commonMethodsUtilService.getLongValueForObject(param[3]));
	    			}else if(commonMethodsUtilService.getLongValueForObject(param[2]).longValue() == 4L){
	    				campSurveyVO.setVeryGood(commonMethodsUtilService.getLongValueForObject(param[3]));
	    			}else if(commonMethodsUtilService.getLongValueForObject(param[2]).longValue() == 5L){
	    				campSurveyVO.setExcellent(commonMethodsUtilService.getLongValueForObject(param[3]));
	    			}
	    		}
	    	}
	    	TrainingCampSurveyVO vo2 = new TrainingCampSurveyVO();
	    	vo2.setId(2L);
	    	vo2.setName("How is leader's leadership skills?");
	    	vo2.getProgramsList().addAll(finalVOList2);
	    	finalVOList.add(vo1);
	    	finalVOList.add(vo2);
	    }catch(Exception e){
	    	LOG.error(" Error Occured in getFeedbackOnLeaders method in CoreDashboardCoreService class" ,e);
	    }
	    return finalVOList;
	}
	
	public void buildTemplateForLocation(List<Object[]> list,List<TrainingCampSurveyVO> finalVOList){
		try{
			Map<Long,String> locIdAndNameMap = new HashMap<Long,String>();
			TrainingCampSurveyVO campSurveyVO = null;
			for(Object[] param : list){
				locIdAndNameMap.put(commonMethodsUtilService.getLongValueForObject(param[0]), commonMethodsUtilService.getStringValueForObject(param[1]));
			}
			if(locIdAndNameMap != null && locIdAndNameMap.size() > 0){
				for(Entry<Long,String> param : locIdAndNameMap.entrySet()){
					campSurveyVO = new TrainingCampSurveyVO();
					campSurveyVO.setId(param.getKey());
					campSurveyVO.setName(param.getValue());
					campSurveyVO.setPoor(0L);
					campSurveyVO.setAverage(0L);
					campSurveyVO.setGood(0L);
					campSurveyVO.setVeryGood(0L);
					campSurveyVO.setExcellent(0L);
					finalVOList.add(campSurveyVO);
				}
			}
		}catch(Exception e){
			LOG.error(" Error Occured in buildTemplateForCenter method in CoreDashboardCoreService class" ,e);
		}
	}	
	/**
	* @author Swadhin 
	* @Description :This Service Method is used to get top5 strong or top5 poor members attended and eligible count..structure 
	* @since 10-DEC-2017
	*/
	public List<List<UserTypeVO>> getUserTypeWiseTotalEligibleAndAttendedCnt(Long userId,Long userTypeId,Long activityMembersId,Long userAccessLevelId,List<Long> userAccessLevelValues,Long stateId,String toDateStr,List<Long> enrollmentYearIds,List<Long> programIdList){
		List<List<UserTypeVO>> resultList = new ArrayList<List<UserTypeVO>>(0);
		Map<String,TrainingCampProgramVO> eligibleAndAttendedCntMap = new HashMap<String, TrainingCampProgramVO>(0);
		Map<Long,Set<Long>> locationLevelMap = null;
		Map<Long,Map<Long,UserTypeVO>> userTypeMapDtls = null;
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		Date toDate=null;
		try{
			if(toDateStr != null && !toDateStr.isEmpty() && toDateStr.length() > 0){
				 toDate = sdf.parse(toDateStr);
			 }
		     ActivityMemberVO activityMemberVO = new ActivityMemberVO();
		     activityMemberVO.setUserId(userId);
		     activityMemberVO.setActivityMemberId(activityMembersId);
		     activityMemberVO.setUserTypeId(userTypeId);
		     activityMemberVO = coreDashboardGenericService.getChildActivityMembersAndLocationsNew(activityMemberVO);//calling generic method.
		     userTypeMapDtls = activityMemberVO.getUserTypesMap();
		     locationLevelMap = activityMemberVO.getLocationLevelIdsMap();
		     
		     if(locationLevelMap != null && locationLevelMap.size() > 0){
		    	 for(Entry<Long,Set<Long>> entry:locationLevelMap.entrySet()){
		    		 Long accessLevelValue = 0l;
		    		 //As per intermediate table state->2,district->3,parliament->10,constituency->4
		    		 //As per user_level table state->2,district->3,parliament->4,constituency->5
		    		 //here we are using intermediate table.
		    		 //So, modify 4 -> 10 and 5 -> 4
		    		 if(entry.getKey().longValue() == 4l){
		    			 accessLevelValue = 10l;
		    		 }else if(entry.getKey().longValue() == 5l){
		    			 accessLevelValue = 4l;
		    		 }else{
		    			 accessLevelValue = entry.getKey();	 
		    		 }
		    		 List<Object[]> returnObjList = trainingCampDetailsInfoDAO.getTrainingCampProgramEligibleAndAttendedMemberLocationWise(accessLevelValue,new ArrayList<Long>(entry.getValue()), toDate,enrollmentYearIds,programIdList);
		    		 if(returnObjList != null && returnObjList.size() > 0){
		    			 for (Object[] param : returnObjList) {
		    				 String locationLevelAndId = entry.getKey()+"-"+param[0].toString();
		    				 TrainingCampProgramVO eligibleAndAttendedVO = new TrainingCampProgramVO();
		    				 eligibleAndAttendedVO.setTotalEligibleCount(commonMethodsUtilService.getLongValueForObject(param[1]));
		    				 eligibleAndAttendedVO.setTotalAttenedCount(commonMethodsUtilService.getLongValueForObject(param[2]));
		    				 eligibleAndAttendedVO.setTotalNotAttenedCount(commonMethodsUtilService.getLongValueForObject(param[3]));
		    				 eligibleAndAttendedCntMap.put(locationLevelAndId,eligibleAndAttendedVO);
		    			 }
		    		 }
		    	 }  
		     }
		     //Pushing requird data
		     if(userTypeMapDtls != null && userTypeMapDtls.size() > 0){
		    	 for(Entry<Long, Map<Long, UserTypeVO>> entry : userTypeMapDtls.entrySet()){
		    		 Map<Long,UserTypeVO> userTypeMap = entry.getValue();
		    		 for(UserTypeVO vo:userTypeMap.values()){
		    			 for(Long locationValueId:vo.getLocationValuesSet()){
	    			 		String key = vo.getLocationLevelId()+"-"+locationValueId;   
	    			 		if(eligibleAndAttendedCntMap.get(key) != null){
	    			 			vo.setTotalEligibleCount(vo.getTotalEligibleCount()+eligibleAndAttendedCntMap.get(key).getTotalEligibleCount()); 
	    			 			vo.setTotalAttenedCount(vo.getTotalAttenedCount()+eligibleAndAttendedCntMap.get(key).getTotalAttenedCount()); 
	    			 			vo.setTotalNotAttenedCount(vo.getTotalNotAttenedCount()+eligibleAndAttendedCntMap.get(key).getTotalNotAttenedCount());
	    			 		}
		    			 }
		    		 }
		    	 }	  
		     }
		     //Calculating percentage
		     if(userTypeMapDtls != null && userTypeMapDtls.size() > 0){
		    	 for(Entry<Long, Map<Long, UserTypeVO>> entry : userTypeMapDtls.entrySet()){
		    		 Map<Long,UserTypeVO> userTypeMap = entry.getValue();
		    		 for(UserTypeVO vo:userTypeMap.values()){
		    			 vo.setTotalAttenedCountPer(calculatePercantage(vo.getTotalAttenedCount(),vo.getTotalEligibleCount()));  
		    			 vo.setTotalNotAttenedCountPer(calculatePercantage(vo.getTotalNotAttenedCount(),vo.getTotalEligibleCount()));	
			     		}
				  	}
		     }
	
		     if(userTypeMapDtls!=null && userTypeMapDtls.size()>0){
		    	 Map<Long,UserTypeVO> orgSecAndSecMap = new LinkedHashMap<Long,UserTypeVO>();
		    	 Map<Long,UserTypeVO>  secreteriesMap = null;
		    	 if(userTypeMapDtls.containsKey(11l)){
		    		 secreteriesMap = userTypeMapDtls.get(11l);
		    		 orgSecAndSecMap.putAll(secreteriesMap);
		    		 //remove secreteries from Map
		    		 userTypeMapDtls.remove(11l); 
		    	 }
        
		    	 Map<Long,UserTypeVO>  organizingSecreteriesMap = null;
		    	 if(userTypeMapDtls.containsKey(4l)){
		    		 organizingSecreteriesMap = userTypeMapDtls.get(4l);
		    		 orgSecAndSecMap.putAll(organizingSecreteriesMap);
		    	 }
       
		    	 if(organizingSecreteriesMap!=null && organizingSecreteriesMap.size()>0){
		    		 userTypeMapDtls.put(4l, orgSecAndSecMap); 
		    	 }
		     }
	
		     if(userTypeMapDtls != null && userTypeMapDtls.size() > 0){
		    	 for(Entry<Long, Map<Long, UserTypeVO>> entry:userTypeMapDtls.entrySet()){
		    		 Map<Long,UserTypeVO> userTypeMap = entry.getValue();
		    		 resultList.add(new ArrayList<UserTypeVO>(userTypeMap.values()));
		    	 }
		     }
		     if(resultList != null && resultList.size() > 0){
		    	 for(List<UserTypeVO> memberList:resultList){
		    		 Collections.sort(memberList, trainingMemberEligibleAttendedPercDesc);
		    	 }
		     }
		}catch(Exception e){
			LOG.error("Error occured at getUserTypeWiseTotalEligibleAndAttendedCnt() in CoreDashboardMainService {}",e); 
		}
		return resultList; 
	}
	public static Comparator<UserTypeVO> trainingMemberEligibleAttendedPercDesc = new Comparator<UserTypeVO>() {
		public int compare(UserTypeVO member2, UserTypeVO member1) {
			Double perc2 = member2.getTotalAttenedCountPer();
			Double perc1 = member1.getTotalAttenedCountPer();
			//descending order of percantages.
			return perc1.compareTo(perc2);
		}
	}; 
	public Double calculatePercantage(Long subCount,Long totalCount){
		Double d=0.0d;
		if(subCount.longValue()>0l && totalCount.longValue()==0l)
		LOG.error("Sub Count Value is "+subCount+" And Total Count Value  "+totalCount);
	
		if(totalCount.longValue() > 0l){
			 d = new BigDecimal(subCount * 100.0/totalCount).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();	 
		}
		return d;
	}
	public TrainingCampVO getTrainingCampFeedBackDetails(Long userAccessLevelId, List<Long> userAccessLevelValueList,Long stateId, String fromDateStr, String toDateStr, List<Long> enrollmentYearIdList, List<Long> programIdList){
		TrainingCampVO FinaltrainingCampVO = new TrainingCampVO();
		return null;
	}
	/**
     * @author  Sai Kumar <href:saikumar.mandal@itgrids.com >
     * @Date 8th December,2017
     * @return List of program ids
   */
	public List<KeyValueVO> getProgramIdsList() {
		List<KeyValueVO> returnList = new ArrayList<KeyValueVO>();
		List<Long> programIdsList = trainingCampProgramDAO.getProgramIdsList();
		if(programIdsList !=null && programIdsList.size() >0){
			for(Long programId : programIdsList) {
				KeyValueVO vo = new KeyValueVO();
				vo.setId(programId);
				returnList.add(vo);
			}
		}
		return returnList;
	}
	/**
     * @author  Sai Kumar <href:saikumar.mandal@itgrids.com >
     * @Date 8th December,2017
     * @return List of enrollment ids
   */
	public List<KeyValueVO> getCadreCommiteeEnrollmentIds() {
		List<KeyValueVO> returnList = new ArrayList<KeyValueVO>();
		List<Object[]> tdpCadreEnrollmentYearList = tdpCommitteeEnrollmentDAO.getTdpCadreEnrollmentYear();
		if(tdpCadreEnrollmentYearList !=null && tdpCadreEnrollmentYearList.size() >0){
			for(Object[] param : tdpCadreEnrollmentYearList){
				KeyValueVO vo = new KeyValueVO();
				vo.setId(commonMethodsUtilService.getLongValueForObject(param[0]));
				vo.setName(commonMethodsUtilService.getStringValueForObject(param[1]));
				returnList.add(vo);
			}
		}
		return returnList;
	}
	/**
     * @author  Sai Kumar <href:saikumar.mandal@itgrids.com >
     * @Date 11th December,2017
   */
	public TrainingCampProgramVO getTrainingCampBasicDetailsCntOverviewDayWise(Long globalActivityMemberId,Long stateId,String fromDateStr, String toDateStr, List<Long> enrollmentYearIds,List<Long> programIdList,List<Long> committeeLevelIds) {
		TrainingCampProgramVO finalResultVO = new TrainingCampProgramVO();
		Map<Long, TrainingCampProgramVO> trainingCampProgramDtlsMap = new HashMap<Long, TrainingCampProgramVO>();
		Long userAccessLevelId = null;
		List<Long> userAccessLevelValues = new ArrayList<Long>();
		try {
			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

			Date startDate = null;
			Date endDate = null;
			if(fromDateStr != null && !fromDateStr.isEmpty() && fromDateStr.trim().length() > 0 && toDateStr != null && !toDateStr.isEmpty() && toDateStr.trim().length() > 0){
				startDate = sdf.parse(fromDateStr);
				endDate = sdf.parse(toDateStr);
			}
			
			List<Object[]> accessLvlIdAndValuesList = activityMemberAccessLevelDAO.getLocationLevelAndValuesByActivityMembersId(globalActivityMemberId);
			if (accessLvlIdAndValuesList != null && accessLvlIdAndValuesList.size() > 0) {
				userAccessLevelId = accessLvlIdAndValuesList.get(0)[0] != null ? (Long) accessLvlIdAndValuesList.get(0)[0] : 0l;
				for (Object[] param : accessLvlIdAndValuesList) {
					userAccessLevelValues.add(param[1] != null ? (Long) param[1] : 0l);
				}
			}
			
			List<Long> tdpCommitteeLvlIds = new ArrayList<Long>();
			tdpCommitteeLvlIds.add(6l);
			tdpCommitteeLvlIds.add(8l);
			tdpCommitteeLvlIds.add(5l);
			tdpCommitteeLvlIds.add(7l);
			tdpCommitteeLvlIds.add(9l);

			String committeeLvlVals = "5,6,7,8,9";
			
			
			//List<Long> programIds = trainingCampScheduleDAO.getTrainingCampProgramIds(enrollmentYearIds.get(0));
			//List<Object[]> attendedList = trainingCampBatchAttendeeDAO.getDayWiseTrainingCampDetailsCount(programIds,fromDate,toDay,enrollmentYrIds,1L,committeeLvlVals,userAccessLevelId,levelVals);
			List<Object[]>  trainingCampObj=trainingCampBatchDAO.getTraingCampBatchDetaisByDatesAndProgramIdsAndEnroleMentIds(null,null,enrollmentYearIds,programIdList);
			List<Long> batchIdsList=new ArrayList<Long>();// adding all batchIds to list
			if(trainingCampObj != null && trainingCampObj.size() >0){
			 	for(Object[] param:trainingCampObj){
			 		Long batchId=commonMethodsUtilService.getLongValueForObject(param[0]);
		  	        batchIdsList.add(batchId);
				}
			}
			List<Object[]> attendedList = getCampDetailsListByFiltering(enrollmentYearIds,programIdList,batchIdsList,committeeLevelIds);
			
			List<Object[]> rtrnCommiteeLevelEligibleAndAttendedObjLst = trainingCampDetailsInfoDAO
					.getTrainingCampProgramEligibleAndAttendedMemberCommitteeLevelWise(
							userAccessLevelId, userAccessLevelValues, startDate,
							enrollmentYearIds, programIdList,committeeLevelIds);

			Map<Long, Map<Long, Long>> batchMemdaysMap = new HashMap<Long, Map<Long, Long>>();
			if (attendedList != null && attendedList.size() > 0) {
				for (Object[] param : attendedList) {
					Map<Long, Long> memDaysMap = batchMemdaysMap.get(commonMethodsUtilService.getLongValueForObject(param[2]));
					if (memDaysMap == null) {
						memDaysMap = new HashMap<Long, Long>(0);
						memDaysMap.put(commonMethodsUtilService.getLongValueForObject(param[0]), 1l);
						batchMemdaysMap.put(commonMethodsUtilService.getLongValueForObject(param[2]), memDaysMap);
					}
					
					Long attendedDaysforBatch = memDaysMap.get(commonMethodsUtilService.getLongValueForObject(param[0]));
					if (attendedDaysforBatch == null) {
						memDaysMap.put(commonMethodsUtilService.getLongValueForObject(param[0]), 1l);
					} else {
						memDaysMap.put(commonMethodsUtilService.getLongValueForObject(param[0]),attendedDaysforBatch + 1l);
					}
				}
			}
			Map<Long, Long> memMaxDayCnt = getCountOfMaxDaysAttendedInBatch(batchMemdaysMap, attendedList);// Tdp CAdre max days Vo getting
			TrainingCampProgramVO villageWardVO = new TrainingCampProgramVO();
			villageWardVO.setName("Village/Ward");
			setElibibleAndAttendedMemberCntToMap(rtrnCommiteeLevelEligibleAndAttendedObjLst, villageWardVO,	"villageWard");
			villageWardVO.getLocationList().addAll(getDaysList());
			setTotalAttendedAndNonInviteeAttended1(attendedList, villageWardVO,	"villageWard", memMaxDayCnt);
			villageWardVO.setTotalAttenedCountPer(calculatePercantage(villageWardVO.getInviteeAttended(),	villageWardVO.getTotalEligibleCount()));
			villageWardVO.setTotalNotAttenedCountPer(calculatePercantage(villageWardVO.getTotalNotAttenedCount(),villageWardVO.getTotalEligibleCount()));
			finalResultVO.setVillageWardVO(villageWardVO);
			TrainingCampProgramVO manTwnDivVO = new TrainingCampProgramVO();
			manTwnDivVO.setName("Mandal/Town/Division");
			manTwnDivVO.getLocationList().addAll(getDaysList());
			setElibibleAndAttendedMemberCntToMap(rtrnCommiteeLevelEligibleAndAttendedObjLst, manTwnDivVO,"mandalTwnDiv");
			setTotalAttendedAndNonInviteeAttended1(attendedList, manTwnDivVO,"mandalTwnDiv", memMaxDayCnt);
			manTwnDivVO.setTotalAttenedCountPer(calculatePercantage(manTwnDivVO.getInviteeAttended(),	manTwnDivVO.getTotalEligibleCount()));
			manTwnDivVO.setTotalNotAttenedCountPer(calculatePercantage(	manTwnDivVO.getTotalNotAttenedCount(),manTwnDivVO.getTotalEligibleCount()));
			finalResultVO.setMandalTownDivisionVO(manTwnDivVO);

			List<Object[]> rtrnObjLst = trainingCampDetailsInfoDAO.getTrainingCampProgramEligibleAndAttendedDetails(userAccessLevelId, userAccessLevelValues, startDate,enrollmentYearIds, programIdList,committeeLevelIds);
			if (rtrnObjLst != null && rtrnObjLst.size() > 0) {
				for (Object[] param : rtrnObjLst) {
					TrainingCampProgramVO programVO = new TrainingCampProgramVO();
					programVO.getLocationList().addAll(getDaysList());
					programVO.setId(commonMethodsUtilService.getLongValueForObject(param[0]));
					programVO.setName(commonMethodsUtilService.getStringValueForObject(param[1]));
					programVO.setTotalEligibleCount(commonMethodsUtilService.getLongValueForObject(param[2]));
					setDayWiseCountToProgramWiseVO(programVO,manTwnDivVO.getLocationList());
					setDayWiseCountToProgramWiseVO(programVO,villageWardVO.getLocationList());
					setInviteeAndNonInviteeCount(programVO);
					programVO.setTotalNotAttenedCount(villageWardVO.getTotalNotAttenedCount()+ manTwnDivVO.getTotalNotAttenedCount());
					programVO.setTotalAttenedCountPer(calculatePercantage(programVO.getInviteeAttended(),programVO.getTotalEligibleCount()));
					programVO.setTotalNotAttenedCountPer(calculatePercantage(programVO.getTotalNotAttenedCount(),	programVO.getTotalEligibleCount()));
					trainingCampProgramDtlsMap.put(programVO.getId(), programVO);
				}
			}  
			if (trainingCampProgramDtlsMap != null	&& trainingCampProgramDtlsMap.size() > 0) {
				finalResultVO.setTrainingProgramList(new ArrayList<TrainingCampProgramVO>(trainingCampProgramDtlsMap.values()));
			}
		}catch (Exception e) {
			LOG.error("Exception raised at getTrainingCampBasicDetailsCntOverviewDayWise() method of CoreDashboardCoreService", e);
		}
		//add leadership skills for other leader
		addLeadershipSkillsForOtherLeader(userAccessLevelId, userAccessLevelValues, stateId,enrollmentYearIds,finalResultVO);
		
		return finalResultVO;
	}
	
	public String getListToString(List<Long> list) {
		String listString = "";
		Long count = 0l;
		for (Long val : list) {
			count++;
			if(list.size() >count)
				listString += val + ",";
			else
				listString += val;
			
		}

		return listString;
	}
	public List<Object[]> getCampDetailsListByFiltering(List<Long> enrollmentYearIds,List<Long> programYearIds,List<Long> batchIdsList,List<Long> committeeLevelIds){
	    List<Object[]> campDetailsList = new ArrayList<Object[]>(0);
	    if(batchIdsList != null && batchIdsList.size()>0){
	               int filterCount = 50;
	               int i = 0; 
	               int j = filterCount;
	               int maxcount = batchIdsList.size();
	               while (maxcount >0){  
	                   if(maxcount<filterCount)
	                       j = i+maxcount;
	                       List<Object[]>  tempList  = trainingCampAttendanceDAO.getDayWiseTrainingCampDetailsCount(enrollmentYearIds,programYearIds,batchIdsList.subList(i, j),committeeLevelIds);//Procedure Call
	                      if(commonMethodsUtilService.isListOrSetValid(tempList)){
	                        campDetailsList.addAll(tempList);
	                      }
	                       i=j;
	                       maxcount = maxcount-filterCount;
	                       j=j+filterCount;
	               }
	          }
	    return campDetailsList;
	}
	
	public Map<Long, Long> getCountOfMaxDaysAttendedInBatch(
			Map<Long, Map<Long, Long>> batchMemdaysMap, List<Object[]> list) {
		Map<Long, Long> memMaxDayCnt = new HashMap<Long, Long>();
		try {
			if (list != null && list.size() > 0) {
				for (Object[] param : list) {
					Map<Long, Long> memDaysMap = batchMemdaysMap.get(commonMethodsUtilService.getLongValueForObject(param[2]));
					if (memDaysMap != null) {
						Long attendedDaysforBatch = memDaysMap.get(commonMethodsUtilService.getLongValueForObject(param[0]));
						if (memMaxDayCnt.get(commonMethodsUtilService.getLongValueForObject(param[0])) == null) {
							memMaxDayCnt.put(commonMethodsUtilService.getLongValueForObject(param[0]),attendedDaysforBatch);
						} else if (memMaxDayCnt.get(commonMethodsUtilService.getLongValueForObject(param[0])) < attendedDaysforBatch) {
							memMaxDayCnt.put(commonMethodsUtilService.getLongValueForObject(param[0]),attendedDaysforBatch);
						}
						if (memMaxDayCnt.get(commonMethodsUtilService.getLongValueForObject(param[0])) != null && 
								memMaxDayCnt.get(commonMethodsUtilService.getLongValueForObject(param[0]))>3L)
								memMaxDayCnt.put(commonMethodsUtilService.getLongValueForObject(param[0]),3L);
					}
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
			LOG.error(
					"Error occured at getMaxDaysAttendedForBatch() in CoreDashboardCoreService ",
					e);
		}

		return memMaxDayCnt;
	}

	public void setElibibleAndAttendedMemberCntToMap(List<Object[]> rtrnTtlAttendedMmbrObjList,TrainingCampProgramVO resultVO,String levelType){
		try{
			 if(rtrnTtlAttendedMmbrObjList != null && rtrnTtlAttendedMmbrObjList.size() > 0){
					for (Object[] param : rtrnTtlAttendedMmbrObjList) {
						Long levelId = commonMethodsUtilService.getLongValueForObject(param[0]);
						if(levelType.equalsIgnoreCase("villageWard")){
							if(levelId.longValue() == 6l || levelId.longValue() == 8l){
								resultVO.setTotalEligibleCount(resultVO.getTotalEligibleCount()+commonMethodsUtilService.getLongValueForObject(param[1]));
								//resultVO.setTotalAttenedCount(resultVO.getTotalAttenedCount()+commonMethodsUtilService.getLongValueForObject(param[2]));
								//resultVO.setTotalNotAttenedCount(resultVO.getTotalNotAttenedCount()+commonMethodsUtilService.getLongValueForObject(param[3]));
							}
						}else if(levelType.equalsIgnoreCase("mandalTwnDiv")){
							if(levelId.longValue() == 5l || levelId.longValue() == 7l || levelId.longValue() == 9l){
								resultVO.setTotalEligibleCount(resultVO.getTotalEligibleCount()+commonMethodsUtilService.getLongValueForObject(param[1]));
								//resultVO.setTotalAttenedCount(resultVO.getTotalAttenedCount()+commonMethodsUtilService.getLongValueForObject(param[2]));
								//resultVO.setTotalNotAttenedCount(resultVO.getTotalNotAttenedCount()+commonMethodsUtilService.getLongValueForObject(param[3]));
							}
						}
					}
				}
		}catch(Exception e){
			 LOG.error("Error occured at setElibibleAndAttendedMemberCntToMap() in CoreDashboardCoreService {}",e); 
		}
		}
	
	public List<TrainingCampProgramVO> getDaysList(){
		List<TrainingCampProgramVO> daysList = new ArrayList<TrainingCampProgramVO>();
		TrainingCampProgramVO day1 = new TrainingCampProgramVO();
		day1.setId(1l);
		day1.setName("1 Day");
		TrainingCampProgramVO day2 = new TrainingCampProgramVO();
		day2.setId(2l);
		day2.setName("2 Days");
		TrainingCampProgramVO day3 = new TrainingCampProgramVO();
		day3.setId(3l);
		day3.setName("3 Days");
		daysList.add(day1);
		daysList.add(day2);
		daysList.add(day3);
		return daysList;
	}
	
	public void setTotalAttendedAndNonInviteeAttended1(List<Object[]> rtrnTtlAttendedMmbrObjList,TrainingCampProgramVO resultVO, String levelType,Map<Long, Long> memMaxDayCnt) {
		try {

			List<Long> inviteeRoles = new ArrayList<Long>();
			inviteeRoles.add(1l);
			inviteeRoles.add(3l);
			if (levelType.equalsIgnoreCase("mandalTwnDiv"))
				inviteeRoles.add(2l);
			Long inviteeAtt = 0l;
			Long nonInviteeAtt = 0l;

			if (rtrnTtlAttendedMmbrObjList != null && rtrnTtlAttendedMmbrObjList.size() > 0) {
				for (Object[] param : rtrnTtlAttendedMmbrObjList) {
						Long levelId = commonMethodsUtilService.getLongValueForObject(param[4]);
						Long memattendedforBatch = memMaxDayCnt.get(commonMethodsUtilService.getLongValueForObject(param[0]));
						TrainingCampProgramVO dayVO = getMatchVO(resultVO.getLocationList(),memattendedforBatch);
						
						if(levelType.equalsIgnoreCase("villageWard") && (levelId == 0l || (levelId.longValue() != 6l &&  levelId.longValue() != 8l && levelId.longValue() != 5l  && levelId.longValue() != 7l  && levelId.longValue() != 9l))){
							dayVO.getOthersIds().add(commonMethodsUtilService.getLongValueForObject(param[0]));
						}
						else if (levelType.equalsIgnoreCase("villageWard") && (levelId.longValue() == 6l || levelId.longValue() == 8l)) {
							if (inviteeRoles.contains(commonMethodsUtilService.getLongValueForObject(param[3])))
								dayVO.getInviteesIds().add(commonMethodsUtilService.getLongValueForObject(param[0]));
							else
								dayVO.getNonInviteesIds().add(commonMethodsUtilService.getLongValueForObject(param[0]));
							
						}else if (levelType.equalsIgnoreCase("mandalTwnDiv") && (levelId.longValue() == 5l || levelId.longValue() == 7l || levelId.longValue() == 9l)) {
							if (inviteeRoles.contains(commonMethodsUtilService.getLongValueForObject(param[3])))
								dayVO.getInviteesIds().add(commonMethodsUtilService.getLongValueForObject(param[0]));
							else
								dayVO.getNonInviteesIds().add(commonMethodsUtilService.getLongValueForObject(param[0]));
						}
					}
				}
			
			if(commonMethodsUtilService.isListOrSetValid(resultVO.getLocationList())){
				for (TrainingCampProgramVO dayVO : resultVO.getLocationList()) {
					
					dayVO.setInviteeAttended(Long.valueOf(String.valueOf(dayVO.getInviteesIds().size())));
					dayVO.setNonInviteeAttended(Long.valueOf(String.valueOf(dayVO.getNonInviteesIds().size())));
					dayVO.setOthersCount(Long.valueOf(String.valueOf(dayVO.getOthersIds().size())));
					dayVO.setTotalAttenedCount(dayVO.getInviteeAttended()+dayVO.getNonInviteeAttended());
					
					resultVO.setInviteeAttended(resultVO.getInviteeAttended()+dayVO.getInviteeAttended());
					resultVO.setNonInviteeAttended(resultVO.getNonInviteeAttended()+dayVO.getNonInviteeAttended());
					resultVO.setTotalAttenedCount(resultVO.getInviteeAttended()+resultVO.getNonInviteeAttended());
					
					dayVO.getInviteesIds().clear();dayVO.getNonInviteesIds().clear();dayVO.getOthersIds().clear();
				}
			}
			
			if(commonMethodsUtilService.isListOrSetValid(resultVO.getLocationList())){
				for (TrainingCampProgramVO dayVO : resultVO.getLocationList()) {
					dayVO.setTotalAttenedCountPer(calculatePercantage(dayVO.getInviteeAttended(),	resultVO.getInviteeAttended()));
				}
			}
			resultVO.setTotalNotAttenedCount(resultVO.getTotalEligibleCount()- resultVO.getInviteeAttended());
		} catch (Exception e) {
			LOG.error("Error occured at setElibibleAndAttendedMemberCntToMap() in CoreDashboardCoreService {}",e);
		}
	}
	public void setDayWiseCountToProgramWiseVO(TrainingCampProgramVO programVO,List<TrainingCampProgramVO> list){
		try{
			
			if(list != null && list.size() >0){
				for(TrainingCampProgramVO dayVo :list){
						TrainingCampProgramVO matchedVO = getMatchVO(programVO.getLocationList(),dayVo.getId());
							if(matchedVO != null){
								matchedVO.setNonInviteeAttended(matchedVO.getNonInviteeAttended() + dayVo.getNonInviteeAttended()+dayVo.getOthersCount());
								matchedVO.setOthersCount(matchedVO.getOthersCount()+dayVo.getOthersCount());
								matchedVO.setInviteeAttended(matchedVO.getInviteeAttended() + dayVo.getInviteeAttended());
							} 
							
							matchedVO.setTotalAttenedCount( matchedVO.getNonInviteeAttended()+matchedVO.getInviteeAttended());
							
					}
				}
			
		}catch(Exception e){
			e.printStackTrace();
			LOG.error("Error occured at setDayWiseCountToProgramWiseVO() in CoreDashboardCoreService {}",e);
		}
	}
	
	public void setInviteeAndNonInviteeCount(TrainingCampProgramVO programVO){
		try{
			
			for(TrainingCampProgramVO dayVo :programVO.getLocationList()){
					programVO.setInviteeAttended(programVO.getInviteeAttended()+dayVo.getInviteeAttended());
					programVO.setNonInviteeAttended(programVO.getNonInviteeAttended()+dayVo.getNonInviteeAttended());
					programVO.setTotalAttenedCount(programVO.getInviteeAttended()+programVO.getNonInviteeAttended());
			}
			for(TrainingCampProgramVO dayVo :programVO.getLocationList()){
				dayVo.setTotalAttenedCountPer(calculatePercantage(dayVo.getInviteeAttended(),	programVO.getInviteeAttended()));
		}
				
		}catch(Exception e){
			e.printStackTrace();
			LOG.error("Error occured at setInviteeAndNonInviteeCount() in CoreDashboardCoreService {}",e);
		}
	}
	
	public TrainingCampProgramVO getMatchVO(
			List<TrainingCampProgramVO> returnList, Long dayId) {
		if (returnList == null)
			return null;
		for (TrainingCampProgramVO dayVO : returnList) {
			if (dayVO.getId().equals(dayId)) {
				return dayVO;
			}
		}
		return null;
	}
	public void addLeadershipSkillsForOtherLeader(Long userAccessLevelId, List<Long> userAccessLevelValues, Long stateId, List<Long> enrollmentYearIds, TrainingCampProgramVO finalResultVO){
		try{
			List<Object[]> batchIdAndProgramIdList = trainingCampBatchDAO.getProgramIdAndBatchIdListByPassingEnrollmentYearId(enrollmentYearIds.get(0));
			Set<Long> programIdList = new HashSet<Long>();
			Set<Long> catchIdList = new HashSet<Long>();
			if(batchIdAndProgramIdList != null && batchIdAndProgramIdList.size() > 0){
				for(Object[] param : batchIdAndProgramIdList){
					catchIdList.add(commonMethodsUtilService.getLongValueForObject(param[0]));
					programIdList.add(commonMethodsUtilService.getLongValueForObject(param[1]));
				}
			}
			
			Long accessLevelValue = userAccessLevelId;
			
			List<Object[]>  tempList = null;
			List<Object[]>  tempList2 = null;
			if(accessLevelValue.longValue() == IConstants.STATE_LEVEl_ACCESS_ID){
				List<Long> distList1 = new ArrayList<Long>(){{add(11L);add(12L);add(13L);add(14L);add(15L);add(16L);add(17L);}};
				tempList  = getCampDetailsListByFilteringAccessLevelWise(3L,distList1,enrollmentYearIds,new ArrayList<Long>(programIdList),new ArrayList<Long>(catchIdList));
				List<Long> distList2 = new ArrayList<Long>(){{add(18L);add(19L);add(20L);add(21L);add(22L);add(23L);add(517L);}};
				tempList2  = getCampDetailsListByFilteringAccessLevelWise(3L,distList2,enrollmentYearIds,new ArrayList<Long>(programIdList),new ArrayList<Long>(catchIdList));
				tempList.addAll(tempList2);
			}else{
				tempList  = getCampDetailsListByFilteringAccessLevelWise(accessLevelValue,userAccessLevelValues,enrollmentYearIds,new ArrayList<Long>(programIdList),new ArrayList<Long>(catchIdList));
			}
			
			//take total attended.
			List<Object[]> attendedList = new ArrayList<Object[]>();
			buildTotalAttendedProgramWise(attendedList,tempList);
			
			
			
			//create a map for training camp id and cadreId and list of attended date.
			Map<Long,Map<Long,Set<String>>> programIdAndCadreIdAndListOfAttendedDate = new HashMap<Long,Map<Long,Set<String>>>();
			
			Map<Long,Set<Long>> programIdAndTotalBatchList = new HashMap<Long,Set<Long>>();
			
			initializeTrainingCampMap(programIdAndCadreIdAndListOfAttendedDate,programIdAndTotalBatchList,attendedList,"all","camp");
			
			// teke all invite attended cadre list
			Map<Long,Map<Long,Set<String>>> programIdAndInviteeCadreIdAndListOfAttendedDate = new HashMap<Long,Map<Long,Set<String>>>();
			
			
			
			initializeTrainingCampMap(programIdAndInviteeCadreIdAndListOfAttendedDate,null,tempList,"inviteeAttended","special");
			
			//get camp name
			List<Object[]> programNameList = null;
			if(programIdAndTotalBatchList != null && programIdAndTotalBatchList.keySet() != null && programIdAndTotalBatchList.keySet().size() > 0){
				programNameList = trainingCampProgramDAO.getTrainingProgramDetailsByProgramIds(new ArrayList<Long>(programIdAndTotalBatchList.keySet()));
			}
			Map<Long,String> programIdAndNameMap = new HashMap<Long,String>();
			if(programNameList != null && programNameList.size() > 0){
				for(Object[] param : programNameList){
					programIdAndNameMap.put(commonMethodsUtilService.getLongValueForObject(param[0]), commonMethodsUtilService.getStringValueForObject(param[1]));
				}
			}
			
			//build vo for ui
			
			List<TrainingCampProgramVO> finalVO = new ArrayList<TrainingCampProgramVO>();
			if(programIdAndCadreIdAndListOfAttendedDate != null && programIdAndCadreIdAndListOfAttendedDate.size() > 0){
				for(Entry<Long,Map<Long,Set<String>>> param : programIdAndCadreIdAndListOfAttendedDate.entrySet()){
					String programName = programIdAndNameMap.get(param.getKey());
					initializeTotalAttendedVO(finalVO,param.getKey(),programName,param.getValue(),programIdAndTotalBatchList,null);
				}
			}
			
			if(programIdAndInviteeCadreIdAndListOfAttendedDate != null && programIdAndInviteeCadreIdAndListOfAttendedDate.size() > 0){
				for(Entry<Long,Map<Long,Set<String>>> param : programIdAndInviteeCadreIdAndListOfAttendedDate.entrySet()){
					initializeTotalInviteeAttendedVO(finalVO,param.getKey(),param.getValue());
				}
			}
			//push completed and running batches per program
			Map<Long,Map<Long,String>> programIdAndBatchIdAndbetweenDate = new HashMap<Long,Map<Long,String>>();
			if(tempList != null && tempList.size() > 0){
				buildProgramWiseBatchMap(tempList,programIdAndBatchIdAndbetweenDate);
				pushProgramWiseBatch(finalVO,programIdAndBatchIdAndbetweenDate);
			}
			
			if(finalVO != null && finalVO.size() > 0){
				for(TrainingCampProgramVO outerParam : finalVO){
					outerParam.setNonInviteeAttended(outerParam.getTotalAttenedCount() - outerParam.getInviteeAttended());
					List<TrainingCampProgramVO> tempVOList = outerParam.getTrainingProgramList();
					if(tempVOList != null && tempVOList.size() > 0){
						for(TrainingCampProgramVO innerParam : tempVOList){
							innerParam.setOnly1dayCountNonInvited(innerParam.getOnly1dayCount() - innerParam.getOnly1dayCountInvited());
							innerParam.setOnly1dayCountNonInvited(innerParam.getOnly1dayCount() - innerParam.getOnly1dayCountInvited());
							innerParam.setOnly1dayCountNonInvited(innerParam.getOnly1dayCount() - innerParam.getOnly1dayCountInvited());
						}
					}
				}
			}
			
			
			if(finalVO != null && finalVO.size() > 0){
				finalResultVO.setLeaderTrainingList(finalVO);
			}
			
		}catch(Exception e){
			LOG.error("Error occured at addLeadershipSkillsForOtherLeader() in CoreDashboardCoreService {}",e);
		}
	}
	
	public List<Object[]> getCampDetailsListByFilteringAccessLevelWise(Long userAccessLevelId,List<Long> levelValueList,List<Long> enrollmentYearIds,List<Long> programYearIds,List<Long> batchIdsList){
	    List<Object[]> campDetailsList = new ArrayList<Object[]>(0);
	    if(batchIdsList != null && batchIdsList.size()>0){
	               int filterCount = 50;
	               int i = 0; 
	               int j = filterCount;
	               int maxcount = batchIdsList.size();
	               while (maxcount >0){  
	                   if(maxcount<filterCount)
	                       j = i+maxcount;
	                       List<Object[]>  tempList  = trainingCampAttendanceDAO.getDayWiseTrainingCampDetailsCountLocationLevelWise(userAccessLevelId,levelValueList,enrollmentYearIds,programYearIds,batchIdsList.subList(i, j));//Procedure Call
	                      if(commonMethodsUtilService.isListOrSetValid(tempList)){
	                    	  for(Object[] param : tempList){
	                    		  if(commonMethodsUtilService.getLongValueForObject(param[26]).longValue() == 2L){
	                    			  param[8]=9L;
	                    			  campDetailsList.add(param);
	                    		  }
	                    	  }
	                      }
	                       i=j;
	                       maxcount = maxcount-filterCount;
	                       j=j+filterCount;
	               }
	          }
	    return campDetailsList;
	}
	
	public void buildTotalAttendedProgramWise(List<Object[]> attendedList, List<Object[]> tempList){
		try{
			Object[] objArr = null;
			if(tempList != null && tempList.size() > 0){
				for(Object[] param : tempList){
					objArr = new Object[4];
					objArr[0] = param[8];
					objArr[1] = param[2];
					objArr[2] = param[0];
					objArr[3] = param[1];
					attendedList.add(objArr);
				}
			}
		}catch(Exception e){
			LOG.error("Error occured at buildTotalAttended() in CoreDashboardCoreService {}",e);
		}
	}
	
	public void initializeTrainingCampMap(Map<Long,Map<Long,Set<String>>> campIdAndCadreIdAndListOfAttendedDate, Map<Long,Set<Long>> campIdAndTotalBatchList,List<Object[]> attendedList,String mapType,String blockType){
		try{
			Map<Long,Set<String>> cadreIdAndListOfAttendedDate = null;
			Set<String> attendedDateList = null;
			
			Set<Long> batchIdList = null;
			int campIdPosition = 0;
			int cadreIdPosition = 0;
			int datePosition = 0;
			if(mapType.trim().equalsIgnoreCase("all") && blockType.trim().equalsIgnoreCase("camp")){
				campIdPosition = 0;
				cadreIdPosition = 2;
				datePosition = 3;
			}else if(mapType.trim().equalsIgnoreCase("inviteeAttended") && blockType.trim().equalsIgnoreCase("camp")){
				campIdPosition = 25;
				cadreIdPosition = 0;
				datePosition = 1;
			}else{
				campIdPosition = 8;
				cadreIdPosition = 0;
				datePosition = 1;
			}
			if(attendedList != null && attendedList.size() > 0){
				for(Object[] param : attendedList){
					if(mapType.trim().equalsIgnoreCase("inviteeAttended") && commonMethodsUtilService.getStringValueForObject(param[7]).trim().equalsIgnoreCase("NON INVITEE")){
						continue;
					}
					cadreIdAndListOfAttendedDate = campIdAndCadreIdAndListOfAttendedDate.get(commonMethodsUtilService.getLongValueForObject(param[campIdPosition]));
					if(cadreIdAndListOfAttendedDate == null){
						cadreIdAndListOfAttendedDate = new HashMap<Long,Set<String>>();
						attendedDateList = new HashSet<String>();
						attendedDateList.add(commonMethodsUtilService.getStringValueForObject(param[datePosition]));
						cadreIdAndListOfAttendedDate.put(commonMethodsUtilService.getLongValueForObject(param[cadreIdPosition]), attendedDateList);
						campIdAndCadreIdAndListOfAttendedDate.put(commonMethodsUtilService.getLongValueForObject(param[campIdPosition]), cadreIdAndListOfAttendedDate);
					}else{
						attendedDateList = cadreIdAndListOfAttendedDate.get(commonMethodsUtilService.getLongValueForObject(param[cadreIdPosition]));
						if(attendedDateList == null){
							attendedDateList = new HashSet<String>();
							attendedDateList.add(commonMethodsUtilService.getStringValueForObject(param[datePosition]));
							cadreIdAndListOfAttendedDate.put(commonMethodsUtilService.getLongValueForObject(param[cadreIdPosition]), attendedDateList);
						}else{
							attendedDateList.add(commonMethodsUtilService.getStringValueForObject(param[datePosition]));
						}
					}
				}
			}
			if(attendedList != null && attendedList.size() > 0 && campIdAndTotalBatchList != null){
				for(Object[] param : attendedList){
					batchIdList = campIdAndTotalBatchList.get(commonMethodsUtilService.getLongValueForObject(param[0]));
					if(batchIdList == null){
						batchIdList = new HashSet<Long>();
						campIdAndTotalBatchList.put(commonMethodsUtilService.getLongValueForObject(param[0]), batchIdList);
					}
					batchIdList.add(commonMethodsUtilService.getLongValueForObject(param[1]));
				}
			}
			
		}catch(Exception e){
			LOG.error("Error occured at initializeTrainingCampMap() in CoreDashboardCoreService {}",e);
		}
	}
	
	public void initializeTotalAttendedVO(List<TrainingCampProgramVO> finalVO,Long campId,String campName,  Map<Long,Set<String>> cadreIdAndAttendedDatesList,Map<Long,Set<Long>> campIdAndTotalBatchList,String description){
		try{
			TrainingCampProgramVO trainingCampProgramVO = new TrainingCampProgramVO();
			
			List<TrainingCampProgramVO> campList = new ArrayList<TrainingCampProgramVO>();
			
			
			Long oneDayAttended = 0L;
			Long twoDayAttended = 0L;
			Long threeDayAttended = 0L;
			if(cadreIdAndAttendedDatesList != null && cadreIdAndAttendedDatesList.size() > 0){
				for(Entry<Long,Set<String>> param : cadreIdAndAttendedDatesList.entrySet() ){
					if(param.getValue().size() >= 3){
						threeDayAttended = threeDayAttended + 1L;
					}else if(param.getValue().size() >= 2){
						twoDayAttended = twoDayAttended + 1L;
					}else if(param.getValue().size() >= 1){
						oneDayAttended = oneDayAttended + 1L;
					}
				}
			}
			
			trainingCampProgramVO.setId(campId);
			trainingCampProgramVO.setName(campName);//{1=[514], 2=[583], 3=[540], 4=[513], 7=[594]}
			trainingCampProgramVO.setDescription(description);
			trainingCampProgramVO.setTotalBath(Long.parseLong(String.valueOf(campIdAndTotalBatchList.get(campId).size())));
			// for one day
			TrainingCampProgramVO tempVO1 = new TrainingCampProgramVO();
			tempVO1.setId(1L);
			tempVO1.setName("1 Day");
			tempVO1.setOnly1dayCount(oneDayAttended);
			campList.add(tempVO1);
			
			// for two day
			TrainingCampProgramVO tempVO2 = new TrainingCampProgramVO();
			tempVO2 = new TrainingCampProgramVO();
			tempVO2.setId(2L);
			tempVO2.setName("2 Day");
			tempVO2.setOnly1dayCount(twoDayAttended);
			campList.add(tempVO2);
			// for three day
			TrainingCampProgramVO tempVO3 = new TrainingCampProgramVO();
			tempVO3 = new TrainingCampProgramVO();
			tempVO3.setId(3L);
			tempVO3.setName("3 Day");
			tempVO3.setOnly1dayCount(threeDayAttended);
			campList.add(tempVO3);
			trainingCampProgramVO.setTotalAttenedCount(tempVO1.getOnly1dayCount() + tempVO2.getOnly1dayCount() + tempVO3.getOnly1dayCount());
			trainingCampProgramVO.getTrainingProgramList().addAll(campList);
			
			finalVO.add(trainingCampProgramVO);
		}catch(Exception e){
			LOG.error("Error occured at initializeVO() in CoreDashboardCoreService {}",e);
		}
	}
	
	public void initializeTotalInviteeAttendedVO(List<TrainingCampProgramVO> finalVO,Long campId,Map<Long,Set<String>> cadreIdAndInviteeAttendedDatesList){
		try{
			Long oneDayAttended = 0L;
			Long twoDayAttended = 0L;
			Long threeDayAttended = 0L;
			Long totalAttended = 0L;
			if(cadreIdAndInviteeAttendedDatesList != null && cadreIdAndInviteeAttendedDatesList.size() > 0){
				for(Entry<Long,Set<String>> param : cadreIdAndInviteeAttendedDatesList.entrySet() ){
					totalAttended = totalAttended + 1L;
					if(param.getValue().size() >= 3){
						threeDayAttended = threeDayAttended + 1L;
					}else if(param.getValue().size() >= 2){
						twoDayAttended = twoDayAttended + 1L;
					}else if(param.getValue().size() >= 1){
						oneDayAttended = oneDayAttended + 1L;
					}
				}
			}
			
			if(finalVO != null && finalVO.size() > 0){
				TrainingCampProgramVO outerVO = (TrainingCampProgramVO) setterAndGetterUtilService.getMatchedVOfromList(finalVO, "id", campId.toString());
				if(outerVO != null && outerVO.getTrainingProgramList().size() > 0){
					for(TrainingCampProgramVO innerVO : outerVO.getTrainingProgramList()){
						Long dayId = innerVO.getId();
						if(dayId.longValue() == 1L){
							innerVO.setOnly1dayCountInvited(oneDayAttended);
						}else if(dayId.longValue() == 2L){
							innerVO.setOnly1dayCountInvited(twoDayAttended);
						}else if(dayId.longValue() == 3L){
							innerVO.setOnly1dayCountInvited(threeDayAttended);
						}
					}
				}
				outerVO.setInviteeAttended(totalAttended);
			}
			
		}catch(Exception e){
			LOG.error("Error occured at initializeTotalInviteeAttendedVO() in CoreDashboardCoreService {}",e);
		}
	}
	
	public void buildProgramWiseBatchMap(List<Object[]> tempList,Map<Long,Map<Long,String>> programIdAndBatchIdAndbetweenDate){
		try{
			Map<Long,String> batchIdAndBetweenDateMap = null;
			String btnDate = "";
			for(Object[] param : tempList){
				batchIdAndBetweenDateMap = programIdAndBatchIdAndbetweenDate.get(commonMethodsUtilService.getLongValueForObject(param[8]));
				if(batchIdAndBetweenDateMap == null){
					batchIdAndBetweenDateMap = new HashMap<Long,String>();
					programIdAndBatchIdAndbetweenDate.put(commonMethodsUtilService.getLongValueForObject(param[8]), batchIdAndBetweenDateMap);
				}
					String betweenDate = commonMethodsUtilService.getStringValueForObject(param[27]).trim().substring(0, 10)+","+commonMethodsUtilService.getStringValueForObject(param[28]).trim().substring(0, 10);
					batchIdAndBetweenDateMap.put(commonMethodsUtilService.getLongValueForObject(param[2]), betweenDate);
				
			}
		}catch(Exception e){
			LOG.error("Error occured at buildProgramWiseBatchMap() in CoreDashboardCoreService {}",e);
		}
	}
	
	public void pushProgramWiseBatch(List<TrainingCampProgramVO> finalVO,Map<Long,Map<Long,String>> programIdAndBatchIdAndbetweenDate){
		try{
			for(TrainingCampProgramVO campProgramVO : finalVO){
				Long programId = campProgramVO.getId();
				Map<Long,String> batchIdAndBetweenDate = programIdAndBatchIdAndbetweenDate.get(programId);
				if(batchIdAndBetweenDate != null && batchIdAndBetweenDate.size() > 0){
					setBatchStatus(campProgramVO,batchIdAndBetweenDate);
				}
			}
		}catch(Exception e){
			LOG.error("Error occured at pushProgramWiseBatch() in CoreDashboardCoreService {}",e);
		}
	}
	
	public void setBatchStatus(TrainingCampProgramVO campProgramVO,Map<Long,String> batchIdAndBetweenDate){
		try{
			DateUtilService dateUtilService = new DateUtilService();
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			Long totalBatch = 0L;
			Long runningBatch = 0L;
			Long completedBatch = 0L;
			Long upcommingBatch = 0L;
			for(Entry<Long,String> param : batchIdAndBetweenDate.entrySet()){
				totalBatch = totalBatch + 1L;
				String[] dateArr = param.getValue().split(",");
				Date fromDate = sdf.parse(dateArr[0]);
				Long fromDateMili = fromDate.getTime();
				Date toDate = sdf.parse(dateArr[1]);
				Long toDateMili = toDate.getTime();
				Date today = dateUtilService.getCurrentDateAndTime();
				Long todayMili = today.getTime();
				
				if(fromDateMili <= todayMili && toDateMili >= todayMili){
					runningBatch = runningBatch + 1L;
				}else if(todayMili > toDateMili){
					completedBatch = completedBatch + 1L;
				}else if(fromDateMili > todayMili){
					upcommingBatch = upcommingBatch + 1L;
				}
			}
			campProgramVO.setTotalBatch(totalBatch);
			campProgramVO.setCompletedBatch(completedBatch);
			campProgramVO.setRunningBatch(runningBatch);
			campProgramVO.setUpcommintbatch(upcommingBatch);
		}catch(Exception e){
			LOG.error("Error occured at setBatchStatus() in CoreDashboardCoreService {}",e);
		}
	}
	public TrainingCampVO getTrainingCampFeedBackDetails(Long activityMemberId,String commiteeLevelId ,Long stateId, String dateStr, List<Long> enrollmentYearIdList, List<Long> programIdList){
		try{
			TrainingCampVO FinaltrainingCampVO = new TrainingCampVO();
			Long feedBackProgramCount=0L;
			Long leaderFeedBackCount=0L;
			Long feedBackQuizCount=0L;
			Long userAccessLevelId = null;
			List<Long> commiteeList = new ArrayList<Long>();
				for (String s : commiteeLevelId.split(",")){
					commiteeList.add(Long.parseLong(s));
				}
			String listString = "";
				for (Long programId : programIdList)
				{
				    listString += programId ;
				}
		   List<Long> userAccessLevelValues = new ArrayList<Long>();
		   List<Object[]> accessLvlIdAndValuesList = activityMemberAccessLevelDAO.getLocationLevelAndValuesByActivityMembersId(activityMemberId);  
			   if(accessLvlIdAndValuesList != null && accessLvlIdAndValuesList.size() > 0){
			    userAccessLevelId = accessLvlIdAndValuesList.get(0)[0] != null ? (Long)accessLvlIdAndValuesList.get(0)[0] : 0l;
			    for(Object[] param : accessLvlIdAndValuesList){
			     userAccessLevelValues.add(param[1] != null ? (Long)param[1] : 0l);
			    }
			   }
	   	 ClientConfig clientConfig = new DefaultClientConfig();
	     clientConfig.getFeatures().put(JSONConfiguration.FEATURE_POJO_MAPPING, Boolean.TRUE);
         Client client = Client.create(clientConfig);
	         WebResource webResource = client.resource("http://mytdp.com/Survey/WebService/getTrainingCampFeedBackDetails/"+listString+"/");
	        // WebResource webResource = client.resource("https://mytdp.com/Survey/WebService/getTrainingCampFeedBackDetails/"+listString+"/");
	         ClientResponse response = webResource.accept("application/json").type("application/json").get(ClientResponse.class);
	          if(response.getStatus() != 200){
	 	    	  throw new RuntimeException("Failed : HTTP error code : "+ response.getStatus());
	 	      }else{
	 	    	 List<Long> programList = response.getEntity(new GenericType<List<Long>>() {});
	 
	 	    	
		 	    	if(programList != null && !programList.isEmpty()){
		 	    		
		 	    		feedBackProgramCount=tdpCadreDAO.filteredTdpCardreIdsCount(programList,userAccessLevelId,userAccessLevelValues);
		 	    	}
		   
	 	      }
	          FinaltrainingCampVO.setFeedBackProgramCount(feedBackProgramCount);
	         // WebResource webResource2 = client.resource("http://192.168.11.154:8080/Survey/WebService/getTrainingQuizFeedBackDetails/"+listString+"/");
	         WebResource webResource2 = client.resource("http://mytdp.com/Survey/WebService/getTrainingQuizFeedBackDetails/"+listString+"/");
		         ClientResponse response2 = webResource2.accept("application/json").type("application/json").get(ClientResponse.class);
		          if(response2.getStatus() != 200){
		 	    	  throw new RuntimeException("Failed : HTTP error code : "+ response2.getStatus());
		 	      }else{
		 	    	 List<Long> quizList = response2.getEntity(new GenericType<List<Long>>() {});
		 
		 	    	
			 	    	if(quizList != null && !quizList.isEmpty()){
			 	    		
			 	    		feedBackQuizCount=tdpCadreDAO.filteredTdpCardreIdsCount(quizList,userAccessLevelId,userAccessLevelValues);
			 	    	}
			   
		 	      }
		          FinaltrainingCampVO.setFeedBackQuizCount(feedBackQuizCount);
          leaderFeedBackCount=trainingCampCadreFeedbackDetailsDAO.getLeaderFeedBackDetails(programIdList,userAccessLevelId,userAccessLevelValues,commiteeList);
          FinaltrainingCampVO.setLeaderFeedBackCount(leaderFeedBackCount);
          
          return FinaltrainingCampVO;
		}catch(Exception e){
			LOG.error("Error occured at getTrainingCampFeedBackDetails() in CoreDashboardMainService {}",e); 
		}
		return null;
		
	}
	public TrainingCampVO getTrainingCampFeedBackDetailsProgramWise(Long activityMemberId,String commiteeLevelId ,Long stateId, String dateStr, List<Long> enrollmentYearIdList, List<Long> programIdList,Long StringType ){
		List<Long> userAccessLevelValues = new ArrayList<Long>();
		Long userAccessLevelId = null;
		 TrainingCampVO finalVo = new TrainingCampVO();
		try{
			List<Object[]> accessLvlIdAndValuesList = activityMemberAccessLevelDAO.getLocationLevelAndValuesByActivityMembersId(activityMemberId);  
				   if(accessLvlIdAndValuesList != null && accessLvlIdAndValuesList.size() > 0){
				    userAccessLevelId = accessLvlIdAndValuesList.get(0)[0] != null ? (Long)accessLvlIdAndValuesList.get(0)[0] : 0l;
					    for(Object[] param : accessLvlIdAndValuesList){
					     userAccessLevelValues.add(param[1] != null ? (Long)param[1] : 0l);
					    }
				   }
				   List<Long> commiteeList = new ArrayList<Long>();
					for (String s : commiteeLevelId.split(",")){
						commiteeList.add(Long.parseLong(s));
					}
				   String listString = "";
					for (Long programId : programIdList)
					{
					    listString += programId ;
					}
			 ClientConfig clientConfig = new DefaultClientConfig();
		     clientConfig.getFeatures().put(JSONConfiguration.FEATURE_POJO_MAPPING, Boolean.TRUE);
	         Client client = Client.create(clientConfig);
		     WebResource webResource = client.resource("http://localhost:8080/Survey/WebService/getTrainingFeedBackDetailsOnProgram/"+listString+"/");
		  // WebResource webResource2 = client.resource("http://mytdp.com/Survey/WebService/getTrainingQuizFeedBackDetails/"+listString+"/");
			 ClientResponse response = webResource.accept("application/json").type("application/json").get(ClientResponse.class);
			  if(response.getStatus() != 200){
				  throw new RuntimeException("Failed : HTTP error code : "+ response.getStatus());
			  }else{
				  List<Long> cadreIdList = response.getEntity(new GenericType<List<Long>>() {});
			  
			  List<Object[]>  tempList = null;
			  List<Object[]>  tempList2 = null;
				 //Query query = getSession().createSQLQuery("CALL get_training_camp_attendance_details(:programId,'2010-07-23','2050-08-02',:enrollemntYrId,:basicCommitteeId,'5,7,9,6,8','2','1')")
			  if(userAccessLevelId.longValue() == IConstants.STATE_LEVEl_ACCESS_ID){
					List<Long> distList1 = new ArrayList<Long>(){{add(11L);add(12L);add(13L);add(14L);add(15L);add(16L);add(17L);}};
					tempList  = trainingCampAttendanceDAO.getInviteAttendedCountForTrainingCamp(3L,distList1,enrollmentYearIdList,programIdList,commiteeList);//Procedure Call
					List<Long> distList2 = new ArrayList<Long>(){{add(18L);add(19L);add(20L);add(21L);add(22L);add(23L);add(517L);}};
					tempList2  = trainingCampAttendanceDAO.getInviteAttendedCountForTrainingCamp(3L,distList2,enrollmentYearIdList,programIdList,commiteeList);//Procedure Call
					tempList.addAll(tempList2);
				}else{
					tempList  = trainingCampAttendanceDAO.getInviteAttendedCountForTrainingCamp(userAccessLevelId,userAccessLevelValues,enrollmentYearIdList,programIdList,commiteeList);//Procedure Call
				}
			 Map<Long,Set<Long>> mainCadreIdsmap = new HashMap<Long, Set<Long>>();
			 Set<Long> cadreIds = null;
			 for(Object[] param :tempList){
				    if(commonMethodsUtilService.getStringValueForObject(param[7]).trim().equalsIgnoreCase("INVITEE")){
					 if(StringType == 1L && commonMethodsUtilService.getLongValueForObject(param[25])!= null){
						cadreIds = mainCadreIdsmap.get(commonMethodsUtilService.getLongValueForObject(param[25]));
					}else if(StringType == 2L){
						cadreIds = mainCadreIdsmap.get(commonMethodsUtilService.getLongValueForObject(param[11]));
					}else if(StringType == 3L){
						cadreIds = mainCadreIdsmap.get(commonMethodsUtilService.getLongValueForObject(param[13]));
					}else if(StringType == 4L){
						cadreIds = mainCadreIdsmap.get(commonMethodsUtilService.getLongValueForObject(param[15]));
					}
					if(cadreIds == null){
						cadreIds = new HashSet<Long>();
						if(StringType ==1L && commonMethodsUtilService.getLongValueForObject(param[25])!= null){
							 mainCadreIdsmap.put(commonMethodsUtilService.getLongValueForObject(param[25]),cadreIds);
						}else if(StringType ==2L){
							 mainCadreIdsmap.put(commonMethodsUtilService.getLongValueForObject(param[11]),cadreIds);
						}else if(StringType ==3L){
							 mainCadreIdsmap.put(commonMethodsUtilService.getLongValueForObject(param[13]),cadreIds);
						}else if(StringType ==4L){
							 mainCadreIdsmap.put(commonMethodsUtilService.getLongValueForObject(param[15]),cadreIds);
						}
					   }
					cadreIds.add(commonMethodsUtilService.getLongValueForObject(param[0]));
				   }
			    }
				 Map<Long,Long> mainMapCount = new HashMap<Long, Long>();
				 for(Entry<Long,Set<Long>> maincadreId :mainCadreIdsmap.entrySet()){
					 mainMapCount.put(maincadreId.getKey(), Long.parseLong(String.valueOf(maincadreId.getValue().size())));
				 }
				 
				List<Object[]> finalList= trainingCampBatchAttendeeDAO.getFilteredListCount(cadreIdList,programIdList,commiteeList,userAccessLevelId,userAccessLevelValues,StringType);
				Long countOfcadreIds = 0L;
				 for(Object[] param:finalList){
					 TrainingCampVO feedbackCountVo = new TrainingCampVO();
					 feedbackCountVo.setId(commonMethodsUtilService.getLongValueForObject(param[0]));
					 feedbackCountVo.setName(commonMethodsUtilService.getStringValueForObject(param[1]));
					 countOfcadreIds= mainMapCount.get(commonMethodsUtilService.getLongValueForObject(param[0]));
					 if(countOfcadreIds == null){
						 feedbackCountVo.setFeedBackProgramCount(0L);
					 }else{
						 feedbackCountVo.setFeedBackProgramCount(countOfcadreIds);
					 }
					 if(countOfcadreIds == null){
						 feedbackCountVo.setProgramId(0L);
					 }else{
						 feedbackCountVo.setProgramId((commonMethodsUtilService.getLongValueForObject(param[2])/countOfcadreIds)*100);
					 }
					 finalVo.getBatchDetails().add(feedbackCountVo);
					 
					 }
				
			}
		 return finalVo;
			  
		}catch(Exception e){
			LOG.error("Error occured at getTrainingCampFeedBackDetailsProgramWise() in CoreDashboardMainService {}",e); 
		}
		   
	return null;
	}

}

	
