package com.itgrids.partyanalyst.service.impl;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Map.Entry;

import org.apache.log4j.Logger;

import com.itgrids.partyanalyst.dao.ITrainingCampDetailsInfoDAO;
import com.itgrids.partyanalyst.dto.ActivityMemberVO;
import com.itgrids.partyanalyst.dto.TrainingCampProgramVO;
import com.itgrids.partyanalyst.dto.TrainingCampVO;
import com.itgrids.partyanalyst.dto.UserTypeVO;
import com.itgrids.partyanalyst.service.ICoreDashboardCoreService;
import com.itgrids.partyanalyst.service.ICoreDashboardGenericService;
import com.itgrids.partyanalyst.utils.CommonMethodsUtilService;

public class CoreDashboardCoreService implements ICoreDashboardCoreService {
	private final static Logger LOG = Logger.getLogger(CoreDashboardMainService.class);
	private CommonMethodsUtilService commonMethodsUtilService;
	private ITrainingCampDetailsInfoDAO trainingCampDetailsInfoDAO;
	private ICoreDashboardGenericService coreDashboardGenericService;
	
	public CommonMethodsUtilService getCommonMethodsUtilService() {
		return commonMethodsUtilService;
	}

	public void setCommonMethodsUtilService(CommonMethodsUtilService commonMethodsUtilService) {
		this.commonMethodsUtilService = commonMethodsUtilService;
	}
	
	public void setTrainingCampDetailsInfoDAO(ITrainingCampDetailsInfoDAO trainingCampDetailsInfoDAO) {
		this.trainingCampDetailsInfoDAO = trainingCampDetailsInfoDAO;
	}
	
	public void setCoreDashboardGenericService(ICoreDashboardGenericService coreDashboardGenericService) {
		this.coreDashboardGenericService = coreDashboardGenericService;
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

}

	
