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
import java.util.Map.Entry;
import java.util.Set;

import org.apache.log4j.Logger;

import com.itgrids.partyanalyst.dao.IActivityConductedInfoDAO;
import com.itgrids.partyanalyst.dao.IActivityDocumentDAO;
import com.itgrids.partyanalyst.dao.IActivityLocationInfoDAO;
import com.itgrids.partyanalyst.dao.IActivityMemberAccessLevelDAO;
import com.itgrids.partyanalyst.dao.IActivityScopeDAO;
import com.itgrids.partyanalyst.dao.IEventAttendeeDAO;
import com.itgrids.partyanalyst.dao.IEventInviteeDAO;
import com.itgrids.partyanalyst.dao.ILocationInfoDAO;
import com.itgrids.partyanalyst.dto.ActivityMemberVO;
import com.itgrids.partyanalyst.dto.EventDetailsVO;
import com.itgrids.partyanalyst.dto.SearchAttributeVO;
import com.itgrids.partyanalyst.dto.UserTypeVO;
import com.itgrids.partyanalyst.service.ICoreDashboardEventsActivitiesService;
import com.itgrids.partyanalyst.service.ICoreDashboardGenericService;
import com.itgrids.partyanalyst.utils.CommonMethodsUtilService;
import com.itgrids.partyanalyst.utils.IConstants;

public class CoreDashboardEventsActivitiesService implements ICoreDashboardEventsActivitiesService {
	
	private final static Logger LOG = Logger.getLogger(CoreDashboardEventsActivitiesService.class);
	
	 private CommonMethodsUtilService commonMethodsUtilService;
	 private ICoreDashboardGenericService coreDashboardGenericService;
	 private IActivityMemberAccessLevelDAO activityMemberAccessLevelDAO;
	 private IEventInviteeDAO eventInviteeDAO;
	 private IEventAttendeeDAO eventAttendeeDAO;
	 //Activity DAO
	 private ILocationInfoDAO locationInfoDAO;
	 private IActivityLocationInfoDAO activityLocationInfoDAO;
	 private IActivityScopeDAO activityScopeDAO;
	 private IActivityConductedInfoDAO activityConductedInfoDAO;
	 private IActivityDocumentDAO activityDocumentDAO;
	 
	 
	public IActivityDocumentDAO getActivityDocumentDAO() {
		return activityDocumentDAO;
	}
	public void setActivityDocumentDAO(IActivityDocumentDAO activityDocumentDAO) {
		this.activityDocumentDAO = activityDocumentDAO;
	}
	public IActivityConductedInfoDAO getActivityConductedInfoDAO() {
		return activityConductedInfoDAO;
	}
	public void setActivityConductedInfoDAO(
			IActivityConductedInfoDAO activityConductedInfoDAO) {
		this.activityConductedInfoDAO = activityConductedInfoDAO;
	}
	public void setCommonMethodsUtilService(
			CommonMethodsUtilService commonMethodsUtilService) {
		this.commonMethodsUtilService = commonMethodsUtilService;
	}
	public void setCoreDashboardGenericService(
			ICoreDashboardGenericService coreDashboardGenericService) {
		this.coreDashboardGenericService = coreDashboardGenericService;
	}
	public void setActivityMemberAccessLevelDAO(
			IActivityMemberAccessLevelDAO activityMemberAccessLevelDAO) {
		this.activityMemberAccessLevelDAO = activityMemberAccessLevelDAO;
	}
	public void setEventInviteeDAO(IEventInviteeDAO eventInviteeDAO) {
		this.eventInviteeDAO = eventInviteeDAO;
	}
	public void setEventAttendeeDAO(IEventAttendeeDAO eventAttendeeDAO) {
		this.eventAttendeeDAO = eventAttendeeDAO;
	}
	public void setLocationInfoDAO(ILocationInfoDAO locationInfoDAO) {
		this.locationInfoDAO = locationInfoDAO;
	}
	public void setActivityLocationInfoDAO(
			IActivityLocationInfoDAO activityLocationInfoDAO) {
		this.activityLocationInfoDAO = activityLocationInfoDAO;
	}
	public void setActivityScopeDAO(IActivityScopeDAO activityScopeDAO) {
		this.activityScopeDAO = activityScopeDAO;
	}
	/**
	* @param  Long activityMemberId
	* @param  Long stateId
	* @param  List<Long> eventIds
	* @return List<EventDetailsVO>
	* @author Santosh 
	* @Description :This Service Method is used to basic event invitee attended and attended count.. 
	*  @since 15-SEPTEMBEr-2016
	*/
	public List<EventDetailsVO> getEventBasicCountDetails(List<Long> eventIds,Long activityMemberId,Long stateId){
		  
		List<EventDetailsVO> resultList = new ArrayList<EventDetailsVO>(0);
		Map<Long,EventDetailsVO> eventDtlsMap = new HashMap<Long, EventDetailsVO>(0);
		Map<Long,Set<Long>> locationAccessLevelMap = new HashMap<Long, Set<Long>>(0);
		  try{
			
			    List<Object[]> rtrnUsrAccssLvlIdAndVlusObjLst=activityMemberAccessLevelDAO.getLocationLevelAndValuesByActivityMembersId(activityMemberId);
			    if(rtrnUsrAccssLvlIdAndVlusObjLst != null && !rtrnUsrAccssLvlIdAndVlusObjLst.isEmpty()){
			      for (Object[] param : rtrnUsrAccssLvlIdAndVlusObjLst) {
					Set<Long> locationValuesSet= locationAccessLevelMap.get((Long)param[0]);
					 if(locationValuesSet == null){
						 locationValuesSet = new java.util.HashSet<Long>();
						 locationAccessLevelMap.put((Long)param[0],locationValuesSet);
					 }
					 locationValuesSet.add(param[1] != null ? (Long)param[1]:0l);
				}
			   }		
			 if(locationAccessLevelMap != null && locationAccessLevelMap.size() > 0){
				 for(Entry<Long, Set<Long>> entry:locationAccessLevelMap.entrySet()){
					List<Object[]> rtrnEventInviteeObLst = eventInviteeDAO.getEventInviteedCountByEvent(entry.getKey(), new ArrayList<Long>(entry.getValue()), stateId, eventIds);
					 setDataToMap(rtrnEventInviteeObLst,eventDtlsMap);
				 }
			 }
			 if(locationAccessLevelMap != null && locationAccessLevelMap.size() > 0){
				 
				 for(Entry<Long,Set<Long>> entry:locationAccessLevelMap.entrySet()){
					 
					 List<Object[]> rtrnEventAttendedObjLst = eventAttendeeDAO.getEventAttendedCountByEvent(entry.getKey(), new ArrayList<Long>(entry.getValue()), stateId, eventIds);
					  
					 if(rtrnEventAttendedObjLst != null && rtrnEventAttendedObjLst.size()>0){
						  
						    for(Object[] param:rtrnEventAttendedObjLst){
						    	
						    	EventDetailsVO eventVO = eventDtlsMap.get(commonMethodsUtilService.getLongValueForObject(param[0])); //eventId
						    	
						    	if(eventVO != null){
						    		
						    		 eventVO.setAttendedCount(commonMethodsUtilService.getLongValueForObject(param[1])); //attended count
						    	 }
						    }
					  }
				 }
			 }
			if(locationAccessLevelMap != null && locationAccessLevelMap.size() > 0){
				
				for(Entry<Long,Set<Long>> entry:locationAccessLevelMap.entrySet()){
					
					List<Object[]> rtrnEventInviteeAttendedObjList = eventAttendeeDAO.getEventInviteeAttendedCountByEvent(entry.getKey(), new ArrayList<Long>(entry.getValue()), stateId, eventIds);
					
					if(rtrnEventInviteeAttendedObjList != null && rtrnEventInviteeAttendedObjList.size() > 0){
						
						 for(Object[] param : rtrnEventInviteeAttendedObjList){
							 
							 EventDetailsVO eventVO = eventDtlsMap.get(commonMethodsUtilService.getLongValueForObject(param[0]));
							  
							 if(eventVO != null ){
								  
								  eventVO.setInviteeAttendedCount(commonMethodsUtilService.getLongValueForObject(param[1]));
								  eventVO.setInviteeNotAttendedCount(eventVO.getInviteeCount()-eventVO.getInviteeAttendedCount());
							  }
						 }
					 }
 				}
			}
			//Calculating percentage
			if(eventDtlsMap != null && eventDtlsMap.size() > 0){
				for(Entry<Long,EventDetailsVO> entry:eventDtlsMap.entrySet()){
					  EventDetailsVO eventVO = entry.getValue();
					  eventVO.setNonInviteeAttendedCount(eventVO.getAttendedCount()-eventVO.getInviteeAttendedCount());
					  eventVO.setInviteeAttendedCounPer(calculatePercantage(eventVO.getInviteeAttendedCount(), eventVO.getInviteeCount()));
					  eventVO.setNonInviteeAttendedCountPer(calculatePercantage(eventVO.getNonInviteeAttendedCount(), eventVO.getAttendedCount()));	
					  eventVO.setInviteeNotAttendedCountPer(calculatePercantage(eventVO.getInviteeNotAttendedCount(), eventVO.getInviteeCount()));
				}
			}
		   if(eventDtlsMap != null && eventDtlsMap.size() > 0){
			   resultList.addAll(new ArrayList<EventDetailsVO>(eventDtlsMap.values()));
		   }
			
		}catch(Exception e){
			LOG.error("Error occured at getEventBasicCountDetails() in CoreDashboardEventsActivitiesService class",e); 
		}
		return resultList;
	}
	public void setDataToMap(List<Object[]> rtrnEventInviteeObLst,Map<Long,EventDetailsVO> eventDtlsMap){
		try{
			if(rtrnEventInviteeObLst != null && rtrnEventInviteeObLst.size() > 0){
				for (Object[] param : rtrnEventInviteeObLst) {
					EventDetailsVO eventVO = eventDtlsMap.get(commonMethodsUtilService.getLongValueForObject(param[0]));
					 if(eventVO == null ){
						  eventVO = new EventDetailsVO();
						  eventVO.setId(commonMethodsUtilService.getLongValueForObject(param[0]));
						  eventVO.setName(commonMethodsUtilService.getStringValueForObject(param[1]));
						  eventVO.setInviteeCount(commonMethodsUtilService.getLongValueForObject(param[2]));
						  eventDtlsMap.put(eventVO.getId(), eventVO);
					  }
				}
			}
		}catch(Exception e){
		 LOG.error("Error occured at setDataToMap() in CoreDashboardEventsActivitiesService class",e);	
		}
	}
	
	public Double calculatePercantage(Long subCount,Long totalCount){
		Double d=0.0d;
		if(subCount.longValue()>0l && totalCount.longValue()==0l)
		LOG.error("Sub Count Value is "+subCount+" And Total Count Value  "+totalCount);

		if(totalCount.longValue() > 0l){
			 d = new BigDecimal(subCount * 100.0/totalCount).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();	 
		}
		return d;
		} 
	/**
	* @param  Long activityMemberId
	* @param  Long stateId
	* @param  Long userId
	* @param Long stateId
	* @List<Long> eventIds
	* @return List<List<UserTypeVO>>
	* @author Santosh 
	* @Description :This Service Method is used to get top 5 strong and poor members by event invitee attended percentage. 
	* @since 15-SEPTEMBER-2016
	*/
  public List<List<UserTypeVO>> getUserTypeWiseTotalInviteeAndInviteeAttendedCnt(List<Long> eventIds,Long activityMemberId,Long userId,Long userTypeId,Long stateId) {
	 
	  List<List<UserTypeVO>> resultList = new ArrayList<List<UserTypeVO>>(0);
	 
	  Map<String,Long> eventIviteeMap = new HashMap<String, Long>(0);
	  Map<String,Long> eventInviteeAttendedMap = new HashMap<String, Long>(0);
	  Map<String,Long> eventAttendedMap = new HashMap<String, Long>(0);
	  Map<Long,Set<Long>> locationLevelMap = null;
	  Map<Long,Map<Long,UserTypeVO>> userTypeMapDtls = null;
	  try{
		  
		     ActivityMemberVO activityMemberVO = new ActivityMemberVO();
		     activityMemberVO.setUserId(userId);
		     activityMemberVO.setActivityMemberId(activityMemberId);
		     activityMemberVO.setUserTypeId(userTypeId);
		     activityMemberVO = coreDashboardGenericService.getChildActivityMembersAndLocationsNew(activityMemberVO);//calling generic method.
		     userTypeMapDtls = activityMemberVO.getUserTypesMap();
		     locationLevelMap = activityMemberVO.getLocationLevelIdsMap();
		     
		     if(locationLevelMap != null && locationLevelMap.size() > 0){
		    	 
		    	 for(Entry<Long,Set<Long>> entry:locationLevelMap.entrySet()){
		    		 
		    		 List<Object[]> rtrnEventInviteeObjLst = eventInviteeDAO.getLocationWiseEventInviteedCount(entry.getKey(), new ArrayList<Long>(entry.getValue()), stateId, eventIds);
		    		 
		    		 if(rtrnEventInviteeObjLst != null && rtrnEventInviteeObjLst.size() > 0){
		    			 
		    			  for (Object[] param : rtrnEventInviteeObjLst) {
		    				  
		    				  String locationLevelAndId = entry.getKey()+"-"+commonMethodsUtilService.getStringValueForObject(param[0]);
		    				  eventIviteeMap.put(locationLevelAndId, commonMethodsUtilService.getLongValueForObject(param[1]));
						}
		    		  }
		    	 }
		     }
		  /*   if(locationLevelMap != null && locationLevelMap.size() > 0){
		    	 
		    	 for(Entry<Long,Set<Long>> entry:locationLevelMap.entrySet()){
		    		 
		    		 List<Object[]> rtrnEventAttendedCnt = eventAttendeeDAO.getEventAttendedCountLocationWise(entry.getKey(),new ArrayList<Long>(entry.getValue()), stateId, eventIds);
		    		  
		    		 if(rtrnEventAttendedCnt != null && rtrnEventAttendedCnt.size() > 0){
		    			 
		    			  for (Object[] param : rtrnEventAttendedCnt) {
		    				  
		    				  String locationLevelAndId = entry.getKey()+"-"+commonMethodsUtilService.getStringValueForObject(param[0]);
		    				  eventAttendedMap.put(locationLevelAndId, commonMethodsUtilService.getLongValueForObject(param[1]));
						}
		    		  }
		    	 }
		     }*/
		     if(locationLevelMap != null && locationLevelMap.size() > 0){
		    	 
		    	 for(Entry<Long,Set<Long>> entry:locationLevelMap.entrySet()){
		    		 
		    		 List<Object[]> rtrnEventInviteeAttendedCnt = eventAttendeeDAO.getEventInviteeAttendedCountLocationWise(entry.getKey(),new ArrayList<Long>(entry.getValue()), stateId, eventIds);
		    		  
		    		 if(rtrnEventInviteeAttendedCnt != null && rtrnEventInviteeAttendedCnt.size() > 0){
		    			  
		    			  for (Object[] param : rtrnEventInviteeAttendedCnt) {
		    				  
		    				  String locationLevelAndId = entry.getKey()+"-"+commonMethodsUtilService.getStringValueForObject(param[0]);
		    				  eventInviteeAttendedMap.put(locationLevelAndId, commonMethodsUtilService.getLongValueForObject(param[1]));
						}
		    		  }
		    	 }
		     }
		     //pushing invitee cnt
		     if(userTypeMapDtls != null && userTypeMapDtls.size() > 0){
		    	 
				  for (Entry<Long, Map<Long, UserTypeVO>> entry : userTypeMapDtls.entrySet()) {
					  
				      Map<Long,UserTypeVO> userTypeMap = entry.getValue();
				      
				      for(UserTypeVO vo:userTypeMap.values()){
				    	  
				    	  for(Long locationValueId:vo.getLocationValuesSet()){
				    		  
				    		  String key = vo.getLocationLevelId()+"-"+locationValueId;
				    		  
				    		  if(eventIviteeMap.get(key) != null){
				    			  
						    		vo.setInviteeCnt(vo.getInviteeCnt()+eventIviteeMap.get(key));
						    	 }
				    	  }
				      }
			}  
			}
		  /*   //pushing attended cnt
		     if(userTypeMapDtls != null && userTypeMapDtls.size() > 0){
		    	 
				  for (Entry<Long, Map<Long, UserTypeVO>> entry : userTypeMapDtls.entrySet()) {
					  
				      Map<Long,UserTypeVO> userTypeMap = entry.getValue();
				      
				      for(UserTypeVO vo:userTypeMap.values()){
				    	  
				    	  for(Long locationValueId:vo.getLocationValuesSet()){
				    		  
				    		  String key = vo.getLocationLevelId()+"-"+locationValueId;
				    		  
				    		  if(eventAttendedMap.get(key) != null){
				    			  
						    		vo.setAttendedCnt(vo.getAttendedCnt()+eventAttendedMap.get(key));
						    	 }
				    	  }
				      }
			}  
			}*/
		    //pushing invitee attended cnt 
		    if(userTypeMapDtls != null && userTypeMapDtls.size() > 0){
		    	
				  for (Entry<Long, Map<Long, UserTypeVO>> entry : userTypeMapDtls.entrySet()) {
					  
				      Map<Long,UserTypeVO> userTypeMap = entry.getValue();
				      
				      for(UserTypeVO vo:userTypeMap.values()){
				    	  
				    	  for(Long locationValueId:vo.getLocationValuesSet()){
				    		  
				    		  String key = vo.getLocationLevelId()+"-"+locationValueId;
				    		  
				    		  if(eventInviteeAttendedMap.get(key) != null){
				    			  
						    		vo.setInviteeAttendedCnt(vo.getInviteeAttendedCnt()+eventInviteeAttendedMap.get(key));
						    	 }
				    	  }
				      }
			}  
			}
		    // Calculate percentage
		    if(userTypeMapDtls != null && userTypeMapDtls.size() > 0){
		    	
				  for (Entry<Long, Map<Long, UserTypeVO>> entry : userTypeMapDtls.entrySet()) {
					  
				      Map<Long,UserTypeVO> userTypeMap = entry.getValue();
				      
				      for(UserTypeVO vo:userTypeMap.values()){
				    	  
				    	//  vo.setNonInviteeAttendedCnt(vo.getAttendedCnt()-vo.getInviteeAttendedCnt());
				    	//  vo.setNonInviteeAttendedCntPer(calculatePercantage(vo.getNonInviteeAttendedCnt(),vo.getAttendedCnt()));
				    	  vo.setInviteeAttendedCntPer(calculatePercantage(vo.getInviteeAttendedCnt(),vo.getInviteeCnt()));
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
					Collections.sort(memberList, eventMembersInviteeAttedededPercDesc);
				}
			}
		
	 }catch (Exception e) {
		 LOG.error("Error occured at setDataToMap() in getUserTypeWiseTotalInviteeAndInviteeAttendedCnt class",e);
	 }
	  return resultList;
  }
   public static Comparator<UserTypeVO> eventMembersInviteeAttedededPercDesc = new Comparator<UserTypeVO>() {
		public int compare(UserTypeVO member2, UserTypeVO member1) {

		Double perc2 = member2.getInviteeAttendedCntPer();
		Double perc1 = member1.getInviteeAttendedCntPer();
		//descending order of percantages.
		 return perc1.compareTo(perc2);
		}
	}; 
		/**
		* @param  Long activityMemberId
		* @param String reportType
		* @param Long stateId
		* List<Long> eventIds
		* @return List<EventDetailsVO>
		* @author Santosh 
		* @Description :This Service Method is used to get location wise event invitee and attended details count based on userType.. 
		*  @since 17-SEPTEMBER-2016
		*/
 public List<EventDetailsVO> getLocationWiseByInviteeAttendedAndInviteeAttendedCntBasedOnUserType(Long userTypeId,Long stateId,Long activityMemberId,List<Long> eventIds){
	 
	 List<EventDetailsVO> resultList = new ArrayList<EventDetailsVO>(0);
	 Map<Long,Set<Long>> locationAccessLevelMap = new HashMap<Long, Set<Long>>(0);
	 Map<Long,Map<Long,EventDetailsVO>> eventInviteeMapDtls = new HashMap<Long, Map<Long,EventDetailsVO>>(0);
	 Map<Long,Map<Long,EventDetailsVO>> eventInviteeTwnDivMapDtls = new HashMap<Long, Map<Long,EventDetailsVO>>(0);
	 Map<Long,String> eventIdNameMap = new HashMap<Long, String>();
	 Map<Long,String> twnDivsionidNameMap = new HashMap<Long, String>(0);
	 
	 try{
		  List<Object[]> rtrnUsrAccssLvlIdAndVlusObjLst=activityMemberAccessLevelDAO.getLocationLevelAndValuesByActivityMembersId(activityMemberId);
		    if(rtrnUsrAccssLvlIdAndVlusObjLst != null && !rtrnUsrAccssLvlIdAndVlusObjLst.isEmpty()){
		      for (Object[] param : rtrnUsrAccssLvlIdAndVlusObjLst) {
				Set<Long> locationValuesSet= locationAccessLevelMap.get((Long)param[0]);
				 if(locationValuesSet == null){
					 locationValuesSet = new java.util.HashSet<Long>();
					 locationAccessLevelMap.put((Long)param[0],locationValuesSet);
				 }
				 locationValuesSet.add(param[1] != null ? (Long)param[1]:0l);
			}
		   }
		 if(locationAccessLevelMap != null && locationAccessLevelMap.size() > 0){
			 for(Entry<Long,Set<Long>> entry:locationAccessLevelMap.entrySet()){
				 if(userTypeId != null && userTypeId.longValue()==IConstants.MLA_USER_TYPE_ID || userTypeId.longValue()==IConstants.CONSTITUENCY_USER_TYPE_ID || userTypeId.longValue()==IConstants.CONSTITUENCY_INCHARGE_USER_TYPE_ID){
						List<Object[]> rtrnMandalInviteeMmbrsObjLst = eventInviteeDAO.getEventInviteeCntByEventAndLocationBasedOnUserType(userTypeId, stateId, eventIds,entry.getKey(),new ArrayList<Long>(entry.getValue()),"tehsil"); 
						setInviteeCntDtlsToMap(rtrnMandalInviteeMmbrsObjLst,eventInviteeMapDtls,eventIdNameMap);
						List<Object[]> rtrnMandalEventAttnddMemObjList = eventAttendeeDAO.getEventAttendedCntByEventAndLocationBasedOnUserType(userTypeId, stateId, eventIds,entry.getKey(),new ArrayList<Long>(entry.getValue()),"tehsil");
						setAttendedDataToMap(rtrnMandalEventAttnddMemObjList,eventInviteeMapDtls);
						List<Object[]> rtrnMandalEventInviteeAttendedMmrsObjList = eventAttendeeDAO.getEventInviteeAttendedCntByEventAndLocationBasedOnUserType(userTypeId, stateId, eventIds, entry.getKey(),new ArrayList<Long>(entry.getValue()),"tehsil");
						setInviteeAttendedCntToMap(rtrnMandalEventInviteeAttendedMmrsObjList,eventInviteeMapDtls);
						List<Object[]> rtrnTwnDivInviteeMmbrsObjLst = eventInviteeDAO.getEventInviteeCntByEventAndLocationBasedOnUserType(userTypeId, stateId, eventIds,entry.getKey(),new ArrayList<Long>(entry.getValue()),"townDivision"); 
						setInviteeCntDtlsToMap(rtrnTwnDivInviteeMmbrsObjLst,eventInviteeTwnDivMapDtls,twnDivsionidNameMap);
						List<Object[]> rtrnTwnDivEventAttnddMemObjList = eventAttendeeDAO.getEventAttendedCntByEventAndLocationBasedOnUserType(userTypeId, stateId, eventIds,entry.getKey(),new ArrayList<Long>(entry.getValue()),"townDivision");
						setAttendedDataToMap(rtrnTwnDivEventAttnddMemObjList,eventInviteeTwnDivMapDtls);
						List<Object[]> rtrnTwnDivEventInviteeAttendedMmrsObjList = eventAttendeeDAO.getEventInviteeAttendedCntByEventAndLocationBasedOnUserType(userTypeId, stateId, eventIds, entry.getKey(),new ArrayList<Long>(entry.getValue()),"townDivision");
						setInviteeAttendedCntToMap(rtrnTwnDivEventInviteeAttendedMmrsObjList,eventInviteeTwnDivMapDtls);
					}else{
						List<Object[]> rtrnEventInviteeMmbrsObjLst = eventInviteeDAO.getEventInviteeCntByEventAndLocationBasedOnUserType(userTypeId, stateId, eventIds,entry.getKey(),new ArrayList<Long>(entry.getValue()),null); 
						setInviteeCntDtlsToMap(rtrnEventInviteeMmbrsObjLst,eventInviteeMapDtls,eventIdNameMap);
						List<Object[]> rtrnEventAttendedMmbrsObjLst = eventAttendeeDAO.getEventAttendedCntByEventAndLocationBasedOnUserType(userTypeId, stateId, eventIds,entry.getKey(),new ArrayList<Long>(entry.getValue()),null);
						setAttendedDataToMap(rtrnEventAttendedMmbrsObjLst,eventInviteeMapDtls);
						List<Object[]> rtrnEventInviteeAttendedMmbrsObjLst = eventAttendeeDAO.getEventInviteeAttendedCntByEventAndLocationBasedOnUserType(userTypeId, stateId, eventIds, entry.getKey(),new ArrayList<Long>(entry.getValue()),null);
						setInviteeAttendedCntToMap(rtrnEventInviteeAttendedMmbrsObjLst,eventInviteeMapDtls);
					}
			 }
		 }
		 //calculating percentage
		 if(eventInviteeMapDtls != null && eventInviteeMapDtls.size() > 0){
			 for(Entry<Long,Map<Long,EventDetailsVO>> entry:eventInviteeMapDtls.entrySet()){
				 if(entry.getKey() != null && entry.getValue().size() >0){
					 for(Entry<Long,EventDetailsVO> locationEntry:entry.getValue().entrySet()){
						   EventDetailsVO locationVO = locationEntry.getValue();
						     locationVO.setNonInviteeAttendedCount(locationVO.getAttendedCount()-locationVO.getInviteeAttendedCount());
						     locationVO.setInviteeAttendedCounPer(calculatePercantage(locationVO.getInviteeAttendedCount(), locationVO.getInviteeCount()));
						     locationVO.setNonInviteeAttendedCountPer(calculatePercantage(locationVO.getNonInviteeAttendedCount(), locationVO.getAttendedCount()));
					 }
				 }
			 }
		 }
		 //calculating percentage in the case of town division 
		 if(eventInviteeTwnDivMapDtls != null && eventInviteeTwnDivMapDtls.size() > 0){
			 for(Entry<Long,Map<Long,EventDetailsVO>> entry:eventInviteeTwnDivMapDtls.entrySet()){
				 if(entry.getKey() != null && entry.getValue().size() >0){
					 for(Entry<Long,EventDetailsVO> locationEntry:entry.getValue().entrySet()){
						   EventDetailsVO locationVO = locationEntry.getValue();
						     locationVO.setNonInviteeAttendedCount(locationVO.getAttendedCount()-locationVO.getInviteeAttendedCount());
						     locationVO.setInviteeAttendedCounPer(calculatePercantage(locationVO.getInviteeAttendedCount(), locationVO.getInviteeCount()));
						     locationVO.setNonInviteeAttendedCountPer(calculatePercantage(locationVO.getNonInviteeAttendedCount(), locationVO.getAttendedCount()));
					 }
				 }
			 }
		 }
		 if(eventInviteeMapDtls != null && eventInviteeMapDtls.size() > 0){
			 for(Entry<Long,Map<Long,EventDetailsVO>> entry:eventInviteeMapDtls.entrySet()){
				   EventDetailsVO eventVO = new EventDetailsVO();
				     eventVO.setId(entry.getKey());
				     eventVO.setName(eventIdNameMap.get(entry.getKey()));
				     eventVO.getLocationList().addAll(entry.getValue().values());
				     if(eventInviteeTwnDivMapDtls != null && eventInviteeTwnDivMapDtls.size() > 0){ // merging town division data if there.
				     eventVO.getLocationList().addAll(eventInviteeTwnDivMapDtls.get(entry.getKey()).values());	 
				     }
				    resultList.add(eventVO); 
			 }
		 }else{
			if(eventInviteeTwnDivMapDtls != null && eventInviteeTwnDivMapDtls.size() > 0){ //
               /* for town division scenario .there may be change in mandal level record is not there but
				in town division record is there*/
				for(Entry<Long,Map<Long,EventDetailsVO>> entry:eventInviteeTwnDivMapDtls.entrySet()){
					 EventDetailsVO eventVO = new EventDetailsVO();
					 eventVO.setId(entry.getKey());
					 eventVO.setName(twnDivsionidNameMap.get(entry.getKey()));
					 eventVO.getLocationList().addAll(entry.getValue().values());
				     resultList.add(eventVO); 
				}
			}
		 }
	 }catch(Exception e){
		 LOG.error("Error occured at setDataToMap() in getLocationWiseByInviteeAttendedAndInviteeAttendedCntBasedOnUserType class",e);	
	 }
	 return resultList;
 }
 public void setInviteeCntDtlsToMap(List<Object[]> inviteeList, Map<Long,Map<Long,EventDetailsVO>> eventInviteeMapDtls,Map<Long,String> eventIdNameMap){
	try{
		if(inviteeList != null && inviteeList.size() > 0){
			for(Object[] param:inviteeList){
				Map<Long,EventDetailsVO> locationMap = eventInviteeMapDtls.get(commonMethodsUtilService.getLongValueForObject(param[0]));
				  if(locationMap == null){
					  locationMap = new HashMap<Long, EventDetailsVO>();
					  eventIdNameMap.put(commonMethodsUtilService.getLongValueForObject(param[0]), commonMethodsUtilService.getStringValueForObject(param[1]));
					  eventInviteeMapDtls.put(commonMethodsUtilService.getLongValueForObject(param[0]),locationMap);
				  }
				  EventDetailsVO locationVO = new EventDetailsVO();
				  locationVO.setId(commonMethodsUtilService.getLongValueForObject(param[2]));
				  locationVO.setName(commonMethodsUtilService.getStringValueForObject(param[3]));
				  locationVO.setInviteeCount(commonMethodsUtilService.getLongValueForObject(param[4]));
				  locationMap.put(locationVO.getId(), locationVO);
			}
		}
	}catch (Exception e) {
		LOG.error("Error occured at setDataToMap() in getLocationWiseByInviteeAttendedAndInviteeAttendedCntBasedOnUserType class",e);
	}
 }
 public void setAttendedDataToMap(List<Object[]> attendedList, Map<Long,Map<Long,EventDetailsVO>> eventInviteeMapDtls){
	 try{
		 if(attendedList != null && attendedList.size() > 0){
			 for(Object[] param:attendedList){
				   Map<Long,EventDetailsVO> locatioMap = eventInviteeMapDtls.get(commonMethodsUtilService.getLongValueForObject(param[0]));
				     EventDetailsVO locationVO = locatioMap.get(commonMethodsUtilService.getLongValueForObject(param[1]));
				        if(locationVO == null ){
				        	EventDetailsVO lcationVO = new EventDetailsVO();
				        	lcationVO.setId(commonMethodsUtilService.getLongValueForObject(param[1]));
				        	lcationVO.setName(commonMethodsUtilService.getStringValueForObject(param[2]));
				        	lcationVO.setAttendedCount(commonMethodsUtilService.getLongValueForObject(param[3]));
				        	locatioMap.put(lcationVO.getId(), lcationVO);
				        }else{
				        	locationVO.setAttendedCount(commonMethodsUtilService.getLongValueForObject(param[3]));
				        }
			 }
		 }
	 }catch(Exception e) {
		 LOG.error("Error occured at setDataToMap() in setAttendedDataToMap class",e);	
	}
 }
 public void setInviteeAttendedCntToMap(List<Object[]> inviteeAttendedList, Map<Long,Map<Long,EventDetailsVO>> eventInviteeMapDtls){
	 try{
		if(inviteeAttendedList != null && inviteeAttendedList.size() > 0){
			for(Object[] param:inviteeAttendedList){
				Map<Long,EventDetailsVO> locationMap = eventInviteeMapDtls.get(commonMethodsUtilService.getLongValueForObject(param[0]));
				 if(locationMap.get(commonMethodsUtilService.getLongValueForObject(param[1])) !=null ){
					locationMap.get(commonMethodsUtilService.getLongValueForObject(param[1])).setInviteeAttendedCount(commonMethodsUtilService.getLongValueForObject(param[3])); 
				 }
			}
		}
	 }catch(Exception e) {
		LOG.error("Error occured at setInviteeAttendedCntToMap() in setAttendedDataToMap class",e);
	}
 }
 /**
	* @param  Long parentActivityMemberId
	* @param  List<Long> childUserTypeIds
	* @param String reportType
	* @List<Long> eventIds
	* @param Long stateId
	* @return List<UserTypeVO>
	* @author Santosh 
	* @Description :This Service Method is used to get selected child member and for userType.. 
	*  @since 19-SEPTEMBER-2016
	*/
 public List<UserTypeVO> getSelectedChildMembersForEvents(Long parentActivityMemberId,List<Long> childUserTypeIds,String reportType,Long stateId,List<Long> eventIds,String searchType){
	 
	 List<UserTypeVO> resultList = new ArrayList<UserTypeVO>();
	 Map<String,Long> eventIviteeMap = new HashMap<String, Long>(0);
	 Map<String,Long> eventInviteeAttendedMap = new HashMap<String, Long>(0);
	 Map<String,Long> eventAttendedMap = new HashMap<String, Long>(0);
	
	 //if(searchType.trim().equalsIgnoreCase("events")){
	 try{
		  Map<Long,Set<Long>> locationLevelIdsMap=null;
		  Map<String,String>     nameForLocationMap=null;
		  ActivityMemberVO activityMemberVO=null;
		  Map<Long,UserTypeVO> childActivityMembersMap=null;
		  
		  //calling generic method to get childActivityMembers and there location level and values
		  
		  if(reportType != null && reportType.equalsIgnoreCase("selectedUserType")){
			  activityMemberVO = coreDashboardGenericService.getRequiredSubLevelActivityMembersDetails(parentActivityMemberId,childUserTypeIds);
			  childActivityMembersMap= activityMemberVO.getActivityMembersMap();
			  locationLevelIdsMap= activityMemberVO.getLocationLevelIdsMap();
		  }else if(reportType != null && reportType.equalsIgnoreCase("directChild")){
			  if(childUserTypeIds != null && childUserTypeIds.size()>0){
				   activityMemberVO = coreDashboardGenericService.getDirectChildActivityMemberCommitteeDetails(parentActivityMemberId,childUserTypeIds.get(0));//activityMemerId,userTypeId
			  }
			   childActivityMembersMap = activityMemberVO.getActivityMembersMap();
			   locationLevelIdsMap = activityMemberVO.getLocationLevelIdsMap();
		  }
		   
		  if(locationLevelIdsMap != null && locationLevelIdsMap.size() > 0){
			  nameForLocationMap = coreDashboardGenericService.getLocationNamesByLocationIds(locationLevelIdsMap);
		  }
		  
		 if(searchType != null && searchType.equalsIgnoreCase("events")){
		  if(locationLevelIdsMap != null && locationLevelIdsMap.size() > 0){
		    	 
		    	 for(Entry<Long,Set<Long>> entry:locationLevelIdsMap.entrySet()){
		    		 
		    		 List<Object[]> rtrnEventInviteeObjLst = eventInviteeDAO.getLocationWiseEventInviteedCount(entry.getKey(), new ArrayList<Long>(entry.getValue()), stateId, eventIds);
		    		 
		    		 if(rtrnEventInviteeObjLst != null && rtrnEventInviteeObjLst.size() > 0){
		    			 
		    			  for (Object[] param : rtrnEventInviteeObjLst) {
		    				  
		    				  String locationLevelAndId = entry.getKey()+"-"+commonMethodsUtilService.getStringValueForObject(param[0]);
		    				  eventIviteeMap.put(locationLevelAndId, commonMethodsUtilService.getLongValueForObject(param[1]));
						}
		    		  }
		    	 }
		  }
		  if(locationLevelIdsMap != null && locationLevelIdsMap.size() > 0){
		    	 
		    	 for(Entry<Long,Set<Long>> entry:locationLevelIdsMap.entrySet()){
		    		 
		    		 List<Object[]> rtrnEventAttendedCnt = eventAttendeeDAO.getEventAttendedCountLocationWise(entry.getKey(),new ArrayList<Long>(entry.getValue()), stateId, eventIds);
		    		  
		    		 if(rtrnEventAttendedCnt != null && rtrnEventAttendedCnt.size() > 0){
		    			 
		    			  for (Object[] param : rtrnEventAttendedCnt) {
		    				  
		    				  String locationLevelAndId = entry.getKey()+"-"+commonMethodsUtilService.getStringValueForObject(param[0]);
		    				  eventAttendedMap.put(locationLevelAndId, commonMethodsUtilService.getLongValueForObject(param[1]));
						}
		    		  }
		    	 }
		     }
		     if(locationLevelIdsMap != null && locationLevelIdsMap.size() > 0){
		    	 
		    	 for(Entry<Long,Set<Long>> entry:locationLevelIdsMap.entrySet()){
		    		 
		    		 List<Object[]> rtrnEventInviteeAttendedCnt = eventAttendeeDAO.getEventInviteeAttendedCountLocationWise(entry.getKey(),new ArrayList<Long>(entry.getValue()), stateId, eventIds);
		    		  
		    		 if(rtrnEventInviteeAttendedCnt != null && rtrnEventInviteeAttendedCnt.size() > 0){
		    			  
		    			  for (Object[] param : rtrnEventInviteeAttendedCnt) {
		    				  
		    				  String locationLevelAndId = entry.getKey()+"-"+commonMethodsUtilService.getStringValueForObject(param[0]);
		    				  eventInviteeAttendedMap.put(locationLevelAndId, commonMethodsUtilService.getLongValueForObject(param[1]));
						}
		    		  }
		    	 }
		     }
		     //setting invitees count
		    if(childActivityMembersMap != null && childActivityMembersMap.size() > 0){
		    	
		    	for(UserTypeVO VO:childActivityMembersMap.values()){
		    		
		    		  for(Long locationValueId:VO.getLocationValuesSet()){
		    			  
			    		  String key = VO.getLocationLevelId()+"-"+locationValueId;
			    		  
			    		  if(eventIviteeMap.get(key) != null){
					    	
			    			  VO.setInviteeCnt(VO.getInviteeCnt()+eventIviteeMap.get(key)); 
					    	 
			    		  }
			    	  }
		    	}
		    }
		    //setting attended count
	   if(childActivityMembersMap != null && childActivityMembersMap.size() > 0){
			    	
			    	for(UserTypeVO VO:childActivityMembersMap.values()){
			    		
			    		  for(Long locationValueId:VO.getLocationValuesSet()){
			    			  
				    		  String key = VO.getLocationLevelId()+"-"+locationValueId;
				    		  
				    		  if(eventAttendedMap.get(key) != null){
						    	
				    			  VO.setAttendedCnt(VO.getAttendedCnt()+eventAttendedMap.get(key)); 
						    	 
				    		  }
				    	  }
			    	}
			    }
	   //setting invitee attended count
	   if(childActivityMembersMap != null && childActivityMembersMap.size() > 0){
	   	
	   	for(UserTypeVO VO:childActivityMembersMap.values()){
	   		
	   		  for(Long locationValueId:VO.getLocationValuesSet()){
	   			  
		    		  String key = VO.getLocationLevelId()+"-"+locationValueId;
		    		  
		    		  if(eventInviteeAttendedMap.get(key) != null){
				    	
		    			  VO.setInviteeAttendedCnt(VO.getInviteeAttendedCnt()+eventInviteeAttendedMap.get(key)); 
				    	 
		    		  }
		    	  }
	   	}
	   }
	   // setting location name for direct child 
	   if(childActivityMembersMap != null && childActivityMembersMap.size() > 0){
		      for(UserTypeVO vo:childActivityMembersMap.values()){
		    	  for(Long locationValueId:vo.getLocationValuesSet()){
		    		  String key = vo.getLocationLevelId()+"_"+locationValueId;
		    		  if(vo.getLocationName() == null || vo.getLocationName().isEmpty()){
		    			  vo.setLocationName(nameForLocationMap.get(key));
					   }else{
						   vo.setLocationName(vo.getLocationName()+","+ nameForLocationMap.get(key) );  
					   }
		    	  }
		      }
	    } 
	   // calculating non invitees count and percentage
	   if(childActivityMembersMap != null && childActivityMembersMap.size() > 0){
		   for(UserTypeVO userVO:childActivityMembersMap.values()){
			    userVO.setNonInviteeAttendedCnt(userVO.getAttendedCnt()-userVO.getInviteeAttendedCnt());
			    userVO.setNonInviteeAttendedCntPer(calculatePercantage(userVO.getNonInviteeAttendedCnt(), userVO.getAttendedCnt()));
			    userVO.setInviteeAttendedCntPer(calculatePercantage(userVO.getInviteeAttendedCnt(),userVO.getInviteeCnt()));
		   }
	   }
	  
	   }else{
		 setActivitiesDetails(locationLevelIdsMap, stateId,
				 resultList,childActivityMembersMap,eventIds,searchType);
		 if(childActivityMembersMap != null && childActivityMembersMap.size() > 0){
		    	for(UserTypeVO VO:childActivityMembersMap.values()){
		    		//for(Long locationValueId:VO.getLocationValuesSet()){
		    		if(VO.getTotalActvtiesCount() != null && VO.getTotalActvtiesCount().longValue() > 0l && VO.getCondctedActiesCount() != null){
		    			VO.setNotCondctedActiesCount(VO.getTotalActvtiesCount()-VO.getCondctedActiesCount());
		    			VO.setConductedPerc(calculatePercantage(VO.getCondctedActiesCount(), VO.getTotalActvtiesCount()));
		    		}
		    		if(VO.getTotalActvtiesCount() != null && VO.getNotCondctedActiesCount() != null){
		    			VO.setNotConductedPerc(calculatePercantage(VO.getNotCondctedActiesCount(), VO.getTotalActvtiesCount()));
		    		}
		    		//}
		    	}
		}
	 }
		 
		 
		  if(childActivityMembersMap != null && childActivityMembersMap.size() > 0){
			  resultList.addAll(childActivityMembersMap.values());
		  }
		  
		  if(resultList != null && resultList.size() > 0)
		  {
			  Collections.sort(resultList, eventInviteeAttendedPercDesc);
		  }
	 }catch(Exception e) {
		 LOG.error("Error occured at getSelectedChildMembersForEvents() in setAttendedDataToMap class",e);	
	 	}
	// }
	 //Activities
	 /*else{
		 try{
			  Map<Long,Set<Long>> locationLevelIdsMap=null;
			  Map<String,String>     nameForLocationMap=null;
			  ActivityMemberVO activityMemberVO=null;
			  Map<Long,UserTypeVO> childActivityMembersMap=null;
			  
			  //calling generic method to get childActivityMembers and there location level and values
			  
			  if(reportType != null && reportType.equalsIgnoreCase("selectedUserType")){
				  activityMemberVO = coreDashboardGenericService.getRequiredSubLevelActivityMembersDetails(parentActivityMemberId,childUserTypeIds);
				  childActivityMembersMap= activityMemberVO.getActivityMembersMap();
				  locationLevelIdsMap= activityMemberVO.getLocationLevelIdsMap();
			  }else if(reportType != null && reportType.equalsIgnoreCase("directChild")){
				  if(childUserTypeIds != null && childUserTypeIds.size()>0){
					   activityMemberVO = coreDashboardGenericService.getDirectChildActivityMemberCommitteeDetails(parentActivityMemberId,childUserTypeIds.get(0));//activityMemerId,userTypeId
				  }
				   childActivityMembersMap = activityMemberVO.getActivityMembersMap();
				   locationLevelIdsMap = activityMemberVO.getLocationLevelIdsMap();
			  }
			   
			  if(locationLevelIdsMap != null && locationLevelIdsMap.size() > 0){
				  nameForLocationMap = coreDashboardGenericService.getLocationNamesByLocationIds(locationLevelIdsMap);
			  }
			  if(locationLevelIdsMap != null && locationLevelIdsMap.size() > 0){
			    	 
			    	 for(Entry<Long,Set<Long>> entry:locationLevelIdsMap.entrySet()){
			    		 
			    		 List<Object[]> rtrnEventInviteeObjLst = eventInviteeDAO.getLocationWiseEventInviteedCount(entry.getKey(), new ArrayList<Long>(entry.getValue()), stateId, eventIds);
			    		 
			    		 if(rtrnEventInviteeObjLst != null && rtrnEventInviteeObjLst.size() > 0){
			    			 
			    			  for (Object[] param : rtrnEventInviteeObjLst) {
			    				  
			    				  String locationLevelAndId = entry.getKey()+"-"+commonMethodsUtilService.getStringValueForObject(param[0]);
			    				  eventIviteeMap.put(locationLevelAndId, commonMethodsUtilService.getLongValueForObject(param[1]));
							}
			    		  }
			    	 }
			  }
			  if(locationLevelIdsMap != null && locationLevelIdsMap.size() > 0){
			    	 
			    	 for(Entry<Long,Set<Long>> entry:locationLevelIdsMap.entrySet()){
			    		 
			    		 List<Object[]> rtrnEventAttendedCnt = eventAttendeeDAO.getEventAttendedCountLocationWise(entry.getKey(),new ArrayList<Long>(entry.getValue()), stateId, eventIds);
			    		  
			    		 if(rtrnEventAttendedCnt != null && rtrnEventAttendedCnt.size() > 0){
			    			 
			    			  for (Object[] param : rtrnEventAttendedCnt) {
			    				  
			    				  String locationLevelAndId = entry.getKey()+"-"+commonMethodsUtilService.getStringValueForObject(param[0]);
			    				  eventAttendedMap.put(locationLevelAndId, commonMethodsUtilService.getLongValueForObject(param[1]));
							}
			    		  }
			    	 }
			     }
			     if(locationLevelIdsMap != null && locationLevelIdsMap.size() > 0){
			    	 
			    	 for(Entry<Long,Set<Long>> entry:locationLevelIdsMap.entrySet()){
			    		 
			    		 List<Object[]> rtrnEventInviteeAttendedCnt = eventAttendeeDAO.getEventInviteeAttendedCountLocationWise(entry.getKey(),new ArrayList<Long>(entry.getValue()), stateId, eventIds);
			    		  
			    		 if(rtrnEventInviteeAttendedCnt != null && rtrnEventInviteeAttendedCnt.size() > 0){
			    			  
			    			  for (Object[] param : rtrnEventInviteeAttendedCnt) {
			    				  
			    				  String locationLevelAndId = entry.getKey()+"-"+commonMethodsUtilService.getStringValueForObject(param[0]);
			    				  eventInviteeAttendedMap.put(locationLevelAndId, commonMethodsUtilService.getLongValueForObject(param[1]));
							}
			    		  }
			    	 }
			     }
			     //setting invitees count
			    if(childActivityMembersMap != null && childActivityMembersMap.size() > 0){
			    	
			    	for(UserTypeVO VO:childActivityMembersMap.values()){
			    		
			    		  for(Long locationValueId:VO.getLocationValuesSet()){
			    			  
				    		  String key = VO.getLocationLevelId()+"-"+locationValueId;
				    		  
				    		  if(eventIviteeMap.get(key) != null){
						    	
				    			  VO.setTotalActvtiesCount(0l);//(VO.getInviteeCnt()+eventIviteeMap.get(key)); 
						    	 
				    		  }
				    	  }
			    	}
			    }
			    //setting attended count
		   if(childActivityMembersMap != null && childActivityMembersMap.size() > 0){
				    	
				    	for(UserTypeVO VO:childActivityMembersMap.values()){
				    		
				    		  for(Long locationValueId:VO.getLocationValuesSet()){
				    			  
					    		  String key = VO.getLocationLevelId()+"-"+locationValueId;
					    		  
					    		  if(eventAttendedMap.get(key) != null){
							    	
					    			  VO.setCondctedActiesCount(0l);//(VO.getAttendedCnt()+eventAttendedMap.get(key)); 
							    	 
					    		  }
					    	  }
				    	}
				    }
		   //setting invitee attended count
		   if(childActivityMembersMap != null && childActivityMembersMap.size() > 0){
		   	
		   	for(UserTypeVO VO:childActivityMembersMap.values()){
		   		
		   		  for(Long locationValueId:VO.getLocationValuesSet()){
		   			  
			    		  String key = VO.getLocationLevelId()+"-"+locationValueId;
			    		  
			    		  if(eventInviteeAttendedMap.get(key) != null){
					    	
			    			  VO.setInviteeAttendedCnt(VO.getInviteeAttendedCnt()+eventInviteeAttendedMap.get(key)); 
					    	 
			    		  }
			    	  }
		   	}
		   }
		   // setting location name for direct child 
		   if(childActivityMembersMap != null && childActivityMembersMap.size() > 0){
			      for(UserTypeVO vo:childActivityMembersMap.values()){
			    	  for(Long locationValueId:vo.getLocationValuesSet()){
			    		  String key = vo.getLocationLevelId()+"_"+locationValueId;
			    		  if(vo.getLocationName() == null || vo.getLocationName().isEmpty()){
			    			  vo.setLocationName(nameForLocationMap.get(key));
						   }else{
							   vo.setLocationName(vo.getLocationName()+","+ nameForLocationMap.get(key) );  
						   }
			    	  }
			      }
		    } 
		   // calculating non invitees count and percentage
		   if(childActivityMembersMap != null && childActivityMembersMap.size() > 0){
			   for(UserTypeVO userVO:childActivityMembersMap.values()){
				   userVO.setNotCondctedActiesCount(0l);//(userVO.getAttendedCnt()-userVO.getInviteeAttendedCnt());
				    userVO.setNotConductedPerc(0.00);//(calculatePercantage(userVO.getNonInviteeAttendedCnt(), userVO.getAttendedCnt()));
				    userVO.setConductedPerc(0.00);//(calculatePercantage(userVO.getInviteeAttendedCnt(),userVO.getInviteeCnt()));
			   }
		   }
		   if(childActivityMembersMap != null && childActivityMembersMap.size() > 0){
				  resultList.addAll(childActivityMembersMap.values());
			  }
			  if(resultList != null && resultList.size() > 0)
			  {
				  Collections.sort(resultList, eventInviteeAttendedPercDesc);
			  }
		 }catch(Exception e) {
			 LOG.error("Error occured at getSelectedChildMembersForEvents() in setAttendedDataToMap class",e);	
		 	}
	 }*/
	
	 return resultList;
 }
 public void setActivitiesDetails(Map<Long,Set<Long>> locationLevelIdsMap,Long stateId,List<UserTypeVO> resultList,
		 Map<Long,UserTypeVO> childActivityMembersMap,List<Long> eventIds,String searchType){
	 
	 try{
		 Map<Long,List<Long>> levlScopesMap = new HashMap<Long,List<Long>>();
		 List<Object[]> levelScopes = activityScopeDAO.getLevelAndScopeIds(eventIds,searchType);
		 if(commonMethodsUtilService.isListOrSetValid(levelScopes)){
			 for (Object[] obj : levelScopes) {
				List<Long> scopeIds = levlScopesMap.get(commonMethodsUtilService.getLongValueForObject(obj[2]));
				if(commonMethodsUtilService.isListOrSetValid(scopeIds)){
					scopeIds.add(commonMethodsUtilService.getLongValueForObject(obj[1]));
				}else{
					scopeIds = new ArrayList<Long>();
					scopeIds.add(commonMethodsUtilService.getLongValueForObject(obj[1]));
					levlScopesMap.put(commonMethodsUtilService.getLongValueForObject(obj[2]), scopeIds);
				}
			}
		 }
		 //Long levelId = activityScopeDAO.getActivityLevelIdByActivityScopeId(activityScopeId); 
		 
		 setTotalActivitiesCount(levlScopesMap,resultList,locationLevelIdsMap,stateId,childActivityMembersMap,"totalcount");
		 setTotalActivitiesCount(levlScopesMap,resultList,locationLevelIdsMap,stateId,childActivityMembersMap,"conductedcount");
		 
	 }catch(Exception e) {
		 LOG.error("Error occured at setActivitiesDetails() in setAttendedDataToMap class",e);	
	 }
	 
 }
	public static Comparator<UserTypeVO> eventInviteeAttendedPercDesc = new Comparator<UserTypeVO>() {
	public int compare(UserTypeVO member2, UserTypeVO member1) {

	Double perc2 = member2.getInviteeAttendedCntPer();
	Double perc1 = member1.getInviteeAttendedCntPer();
	//descending order of percantages.
	 return perc1.compareTo(perc2);
	}
	}; 
  public EventDetailsVO getEventPoorPerformanceLocation(Long userTypeId,Long stateId,Long activityMemberId,List<Long> eventsId,String searchType){
	  
	  EventDetailsVO resultVO = new EventDetailsVO();
	  Map<Long,EventDetailsVO> eventDtlsMap = new HashMap<Long, EventDetailsVO>(0);
	  Map<Long,Set<Long>> accessLevelMap = new HashMap<Long, Set<Long>>(0);
	  if(searchType.trim().equalsIgnoreCase("events")){
	  try{
		  List<Object[]> rtrnUserAccessLevelIdAndValuesObjList=activityMemberAccessLevelDAO.getLocationLevelAndValuesByActivityMembersId(activityMemberId);
		   if(rtrnUserAccessLevelIdAndValuesObjList != null && rtrnUserAccessLevelIdAndValuesObjList.size() > 0){
			   for (Object[] obj : rtrnUserAccessLevelIdAndValuesObjList) {
				  Set<Long> locationValueSet= accessLevelMap.get((Long)obj[0]);
				    if(locationValueSet == null){
				    	locationValueSet = new java.util.HashSet<Long>();
				    	accessLevelMap.put((Long)obj[0],locationValueSet);
				    }
				     locationValueSet.add(obj[1] != null ? (Long)obj[1]:0l);
			}
		   }
		   if(userTypeId != null && userTypeId.longValue()==IConstants.GENERAL_SECRETARY_USER_TYPE_ID || userTypeId.longValue()==IConstants.STATE_TYPE_USER_ID || userTypeId.longValue()==IConstants.COUNTRY_TYPE_USER_ID){
			    if(accessLevelMap != null && accessLevelMap.size() > 0){
			    	 for(Entry<Long,Set<Long>> entry:accessLevelMap.entrySet()){
			    		 List<Object[]> rtrnEventInviteeObjList = eventInviteeDAO.getLocationWiseEventInviteedCntBasedOnUserType(entry.getKey(),new ArrayList<Long>(entry.getValue()), stateId, eventsId, "District");
			    		 setEventInviteeDetailsToMap(rtrnEventInviteeObjList,eventDtlsMap);
			    		// List<Object[]> rtrnEventAttendedObjList = eventAttendeeDAO.getLocationWiseEventAttendedCntBasedOnUserType(entry.getKey(),new ArrayList<Long>(entry.getValue()), stateId, eventsId, "District");
			    		// setEventAttendedCntToMap(rtrnEventAttendedObjList,eventDtlsMap);
			    		 List<Object[]> rtrnEventInviteeAttendedObjList = eventAttendeeDAO.getLocationWiseEventInviteeAttendedCntBasedOnUserType(entry.getKey(),new ArrayList<Long>(entry.getValue()), stateId, eventsId, "District");
			    		 setEventInviteeAttendedCntToMap(rtrnEventInviteeAttendedObjList,eventDtlsMap);
			    		 caculatingNonInviteeAttendedCnt(eventDtlsMap,searchType);
			    		 resultVO.getDistrictList().addAll(eventDtlsMap.values());
			    		 eventDtlsMap.clear();
			    	 }
			    }
		   }
          if(userTypeId != null && userTypeId.longValue()==IConstants.STATE_TYPE_USER_ID ||  userTypeId.longValue()==IConstants.GENERAL_SECRETARY_USER_TYPE_ID  || userTypeId.longValue()==IConstants.ORGANIZING_SECRETARY_USER_TYPE_ID || userTypeId.longValue() ==IConstants.SECRETARY_USER_TYPE_ID
			|| userTypeId.longValue() ==IConstants.DISTRICT_PRESIDENT_USER_TYPE_ID || userTypeId.longValue()==IConstants.MP_USER_TYPE_ID || userTypeId.longValue()==IConstants.COUNTRY_TYPE_USER_ID){
			    if(accessLevelMap != null && accessLevelMap.size() > 0){
			    	 for(Entry<Long,Set<Long>> entry:accessLevelMap.entrySet()){
			    		 List<Object[]> rtrnEventInviteeObjList = eventInviteeDAO.getLocationWiseEventInviteedCntBasedOnUserType(entry.getKey(),new ArrayList<Long>(entry.getValue()), stateId, eventsId, "Constituency");
			    		 setEventInviteeDetailsToMap(rtrnEventInviteeObjList,eventDtlsMap);
			    		// List<Object[]> rtrnEventAttendedObjList = eventAttendeeDAO.getLocationWiseEventAttendedCntBasedOnUserType(entry.getKey(),new ArrayList<Long>(entry.getValue()), stateId, eventsId, "Constituency");
			    		// setEventAttendedCntToMap(rtrnEventAttendedObjList,eventDtlsMap);
			    		 List<Object[]> rtrnEventInviteeAttendedObjList = eventAttendeeDAO.getLocationWiseEventInviteeAttendedCntBasedOnUserType(entry.getKey(),new ArrayList<Long>(entry.getValue()), stateId, eventsId, "Constituency");
			    		 setEventInviteeAttendedCntToMap(rtrnEventInviteeAttendedObjList,eventDtlsMap);
			    		 caculatingNonInviteeAttendedCnt(eventDtlsMap,searchType);
			    		 resultVO.getConstituencyList().addAll(eventDtlsMap.values());
			    		 eventDtlsMap.clear();
			    	 }
			    }
		   }
           if(userTypeId != null && userTypeId.longValue()==IConstants.CONSTITUENCY_USER_TYPE_ID  || userTypeId.longValue()==IConstants.ORGANIZING_SECRETARY_USER_TYPE_ID || userTypeId.longValue() ==IConstants.SECRETARY_USER_TYPE_ID
		   || userTypeId.longValue() ==IConstants.DISTRICT_PRESIDENT_USER_TYPE_ID || userTypeId.longValue()==IConstants.MLA_USER_TYPE_ID || userTypeId.longValue()==IConstants.MP_USER_TYPE_ID ||
				   userTypeId.longValue()==IConstants.CONSTITUENCY_INCHARGE_USER_TYPE_ID){
					    if(accessLevelMap != null && accessLevelMap.size() > 0){
					    	 for(Entry<Long,Set<Long>> entry:accessLevelMap.entrySet()){
					    		 List<Object[]> rtrnEventMndlInviteeObjList = eventInviteeDAO.getLocationWiseEventInviteedCntBasedOnUserType(entry.getKey(),new ArrayList<Long>(entry.getValue()), stateId, eventsId, "Mandal");
					    		 setEventInviteeDetailsToMap(rtrnEventMndlInviteeObjList,eventDtlsMap);
					    		// List<Object[]> rtrnEventMndlAttendedObjList = eventAttendeeDAO.getLocationWiseEventAttendedCntBasedOnUserType(entry.getKey(),new ArrayList<Long>(entry.getValue()), stateId, eventsId, "Mandal");
					    		// setEventAttendedCntToMap(rtrnEventMndlAttendedObjList,eventDtlsMap);
					    		 List<Object[]> rtrnEventMndlInviteeAttendedObjList = eventAttendeeDAO.getLocationWiseEventInviteeAttendedCntBasedOnUserType(entry.getKey(),new ArrayList<Long>(entry.getValue()), stateId, eventsId, "Mandal");
					    		 setEventInviteeAttendedCntToMap(rtrnEventMndlInviteeAttendedObjList,eventDtlsMap);
					    		 caculatingNonInviteeAttendedCnt(eventDtlsMap,searchType);
					    		 resultVO.getMandalTwnDivisionList().addAll(eventDtlsMap.values());
					    		 eventDtlsMap.clear();
					    		 
					    		 List<Object[]> rtrnEventTwnDivInviteeObjList = eventInviteeDAO.getLocationWiseEventInviteedCntBasedOnUserType(entry.getKey(),new ArrayList<Long>(entry.getValue()), stateId, eventsId, "TownDivision");
					    		 setEventInviteeDetailsToMap(rtrnEventTwnDivInviteeObjList,eventDtlsMap);
					    		// List<Object[]> rtrnEventTwnDivAttendedObjList = eventAttendeeDAO.getLocationWiseEventAttendedCntBasedOnUserType(entry.getKey(),new ArrayList<Long>(entry.getValue()), stateId, eventsId, "TownDivision");
					    		// setEventAttendedCntToMap(rtrnEventTwnDivAttendedObjList,eventDtlsMap);
					    		 List<Object[]> rtrnEventTwnDivInviteeAttendedObjList = eventAttendeeDAO.getLocationWiseEventInviteeAttendedCntBasedOnUserType(entry.getKey(),new ArrayList<Long>(entry.getValue()), stateId, eventsId, "TownDivision");
					    		 setEventInviteeAttendedCntToMap(rtrnEventTwnDivInviteeAttendedObjList,eventDtlsMap);
					    		 caculatingNonInviteeAttendedCnt(eventDtlsMap,searchType);
					    		 resultVO.getMandalTwnDivisionList().addAll(eventDtlsMap.values()); // town divsion data merging 
					    		 eventDtlsMap.clear();
					    	
					    	 }
					    }
				   }  
                if(userTypeId != null && userTypeId.longValue()==IConstants.CONSTITUENCY_USER_TYPE_ID  || userTypeId.longValue()==IConstants.CONSTITUENCY_INCHARGE_USER_TYPE_ID || userTypeId.longValue()==IConstants.MLA_USER_TYPE_ID){
							    if(accessLevelMap != null && accessLevelMap.size() > 0){
							    	 for(Entry<Long,Set<Long>> entry:accessLevelMap.entrySet()){
							    		 List<Object[]> rtrnEventVllgInviteeObjList = eventInviteeDAO.getLocationWiseEventInviteedCntBasedOnUserType(entry.getKey(),new ArrayList<Long>(entry.getValue()), stateId, eventsId, "Village");
							    		 setEventInviteeDetailsToMap(rtrnEventVllgInviteeObjList,eventDtlsMap);
							    		// List<Object[]> rtrnEventVllgAttendedObjList = eventAttendeeDAO.getLocationWiseEventAttendedCntBasedOnUserType(entry.getKey(),new ArrayList<Long>(entry.getValue()), stateId, eventsId, "Village");
							    		// setEventAttendedCntToMap(rtrnEventVllgAttendedObjList,eventDtlsMap);
							    		 List<Object[]> rtrnEventVllgInviteeAttendedObjList = eventAttendeeDAO.getLocationWiseEventInviteeAttendedCntBasedOnUserType(entry.getKey(),new ArrayList<Long>(entry.getValue()), stateId, eventsId, "Village");
							    		 setEventInviteeAttendedCntToMap(rtrnEventVllgInviteeAttendedObjList,eventDtlsMap);
							    		 caculatingNonInviteeAttendedCnt(eventDtlsMap,searchType);
							    		 resultVO.getVillageWardList().addAll(eventDtlsMap.values());
							    		 eventDtlsMap.clear();
							    		
							    		 List<Object[]> rtrnEventWrdInviteeObjList = eventInviteeDAO.getLocationWiseEventInviteedCntBasedOnUserType(entry.getKey(),new ArrayList<Long>(entry.getValue()), stateId, eventsId, "Ward");
							    		 setEventInviteeDetailsToMap(rtrnEventWrdInviteeObjList,eventDtlsMap);
							    		// List<Object[]> rtrnEventWrdAttendedObjList = eventAttendeeDAO.getLocationWiseEventAttendedCntBasedOnUserType(entry.getKey(),new ArrayList<Long>(entry.getValue()), stateId, eventsId, "Ward");
							    		// setEventAttendedCntToMap(rtrnEventWrdAttendedObjList,eventDtlsMap);
							    		 List<Object[]> rtrnEventWrdInviteeAttendedObjList = eventAttendeeDAO.getLocationWiseEventInviteeAttendedCntBasedOnUserType(entry.getKey(),new ArrayList<Long>(entry.getValue()), stateId, eventsId, "Ward");
							    		 setEventInviteeAttendedCntToMap(rtrnEventWrdInviteeAttendedObjList,eventDtlsMap);
							    		 caculatingNonInviteeAttendedCnt(eventDtlsMap,searchType);
							    		 resultVO.getVillageWardList().addAll(eventDtlsMap.values()); // ward data merging
							    		 eventDtlsMap.clear();
							    	
							    	 }
							    }
					 }
		   if(resultVO != null ){
			   if(resultVO.getDistrictList() != null && resultVO.getDistrictList().size() > 0){
				   Collections.sort(resultVO.getDistrictList(), eventInviteeeAttendedMemberPercasc);   
			   }
			   if(resultVO.getConstituencyList() != null && resultVO.getConstituencyList().size() > 0){
				   Collections.sort(resultVO.getConstituencyList(), eventInviteeeAttendedMemberPercasc);   
			   }
			   if(resultVO.getMandalTwnDivisionList() != null && resultVO.getMandalTwnDivisionList().size() > 0){
				   Collections.sort(resultVO.getMandalTwnDivisionList(), eventInviteeeAttendedMemberPercasc);   
			   }
			   if(resultVO.getVillageWardList() != null && resultVO.getVillageWardList().size() > 0){
				   Collections.sort(resultVO.getVillageWardList(), eventInviteeeAttendedMemberPercasc);   
			   }
		   }
     }
	  catch(Exception e) {
    	 LOG.error("Error occured at getEventPoorPerformanceLocation() in setAttendedDataToMap class",e);	
	  }
	  }else{
		  try{
			  List<Object[]> rtrnUserAccessLevelIdAndValuesObjList=activityMemberAccessLevelDAO.getLocationLevelAndValuesByActivityMembersId(activityMemberId);
			   if(rtrnUserAccessLevelIdAndValuesObjList != null && rtrnUserAccessLevelIdAndValuesObjList.size() > 0){
				   for (Object[] obj : rtrnUserAccessLevelIdAndValuesObjList) {
					  Set<Long> locationValueSet= accessLevelMap.get((Long)obj[0]);
					    if(locationValueSet == null){
					    	locationValueSet = new java.util.HashSet<Long>();
					    	accessLevelMap.put((Long)obj[0],locationValueSet);
					    }
					     locationValueSet.add(obj[1] != null ? (Long)obj[1]:0l);
				}
			   }
			   if(userTypeId != null && userTypeId.longValue()==IConstants.GENERAL_SECRETARY_USER_TYPE_ID || userTypeId.longValue()==IConstants.STATE_TYPE_USER_ID || userTypeId.longValue()==IConstants.COUNTRY_TYPE_USER_ID){
				    if(accessLevelMap != null && accessLevelMap.size() > 0){
				    	 for(Entry<Long,Set<Long>> entry:accessLevelMap.entrySet()){
				    		 List<Object[]> rtrnEventInviteeObjList = eventInviteeDAO.getLocationWiseEventInviteedCntBasedOnUserType(entry.getKey(),new ArrayList<Long>(entry.getValue()), stateId, eventsId, "District");
				    		 setEventInviteeDetailsToMap(rtrnEventInviteeObjList,eventDtlsMap);
				    		// List<Object[]> rtrnEventAttendedObjList = eventAttendeeDAO.getLocationWiseEventAttendedCntBasedOnUserType(entry.getKey(),new ArrayList<Long>(entry.getValue()), stateId, eventsId, "District");
				    		// setEventAttendedCntToMap(rtrnEventAttendedObjList,eventDtlsMap);
				    		 List<Object[]> rtrnEventInviteeAttendedObjList = eventAttendeeDAO.getLocationWiseEventInviteeAttendedCntBasedOnUserType(entry.getKey(),new ArrayList<Long>(entry.getValue()), stateId, eventsId, "District");
				    		 setEventInviteeAttendedCntToMap(rtrnEventInviteeAttendedObjList,eventDtlsMap);
				    		 caculatingNonInviteeAttendedCnt(eventDtlsMap,searchType);
				    		 resultVO.getDistrictList().addAll(eventDtlsMap.values());
				    		 eventDtlsMap.clear();
				    	 }
				    }
			   }
	          if(userTypeId != null && userTypeId.longValue()==IConstants.STATE_TYPE_USER_ID ||  userTypeId.longValue()==IConstants.GENERAL_SECRETARY_USER_TYPE_ID  || userTypeId.longValue()==IConstants.ORGANIZING_SECRETARY_USER_TYPE_ID || userTypeId.longValue() ==IConstants.SECRETARY_USER_TYPE_ID
				|| userTypeId.longValue() ==IConstants.DISTRICT_PRESIDENT_USER_TYPE_ID || userTypeId.longValue()==IConstants.MP_USER_TYPE_ID || userTypeId.longValue()==IConstants.COUNTRY_TYPE_USER_ID){
				    if(accessLevelMap != null && accessLevelMap.size() > 0){
				    	 for(Entry<Long,Set<Long>> entry:accessLevelMap.entrySet()){
				    		 List<Object[]> rtrnEventInviteeObjList = eventInviteeDAO.getLocationWiseEventInviteedCntBasedOnUserType(entry.getKey(),new ArrayList<Long>(entry.getValue()), stateId, eventsId, "Constituency");
				    		 setEventInviteeDetailsToMap(rtrnEventInviteeObjList,eventDtlsMap);
				    		// List<Object[]> rtrnEventAttendedObjList = eventAttendeeDAO.getLocationWiseEventAttendedCntBasedOnUserType(entry.getKey(),new ArrayList<Long>(entry.getValue()), stateId, eventsId, "Constituency");
				    		// setEventAttendedCntToMap(rtrnEventAttendedObjList,eventDtlsMap);
				    		 List<Object[]> rtrnEventInviteeAttendedObjList = eventAttendeeDAO.getLocationWiseEventInviteeAttendedCntBasedOnUserType(entry.getKey(),new ArrayList<Long>(entry.getValue()), stateId, eventsId, "Constituency");
				    		 setEventInviteeAttendedCntToMap(rtrnEventInviteeAttendedObjList,eventDtlsMap);
				    		 caculatingNonInviteeAttendedCnt(eventDtlsMap,searchType);
				    		 resultVO.getConstituencyList().addAll(eventDtlsMap.values());
				    		 eventDtlsMap.clear();
				    	 }
				    }
			   }
	           if(userTypeId != null && userTypeId.longValue()==IConstants.CONSTITUENCY_USER_TYPE_ID  || userTypeId.longValue()==IConstants.ORGANIZING_SECRETARY_USER_TYPE_ID || userTypeId.longValue() ==IConstants.SECRETARY_USER_TYPE_ID
			   || userTypeId.longValue() ==IConstants.DISTRICT_PRESIDENT_USER_TYPE_ID || userTypeId.longValue()==IConstants.MLA_USER_TYPE_ID || userTypeId.longValue()==IConstants.MP_USER_TYPE_ID ||
					   userTypeId.longValue()==IConstants.CONSTITUENCY_INCHARGE_USER_TYPE_ID){
						    if(accessLevelMap != null && accessLevelMap.size() > 0){
						    	 for(Entry<Long,Set<Long>> entry:accessLevelMap.entrySet()){
						    		 List<Object[]> rtrnEventMndlInviteeObjList = eventInviteeDAO.getLocationWiseEventInviteedCntBasedOnUserType(entry.getKey(),new ArrayList<Long>(entry.getValue()), stateId, eventsId, "Mandal");
						    		 setEventInviteeDetailsToMap(rtrnEventMndlInviteeObjList,eventDtlsMap);
						    		// List<Object[]> rtrnEventMndlAttendedObjList = eventAttendeeDAO.getLocationWiseEventAttendedCntBasedOnUserType(entry.getKey(),new ArrayList<Long>(entry.getValue()), stateId, eventsId, "Mandal");
						    		// setEventAttendedCntToMap(rtrnEventMndlAttendedObjList,eventDtlsMap);
						    		 List<Object[]> rtrnEventMndlInviteeAttendedObjList = eventAttendeeDAO.getLocationWiseEventInviteeAttendedCntBasedOnUserType(entry.getKey(),new ArrayList<Long>(entry.getValue()), stateId, eventsId, "Mandal");
						    		 setEventInviteeAttendedCntToMap(rtrnEventMndlInviteeAttendedObjList,eventDtlsMap);
						    		 caculatingNonInviteeAttendedCnt(eventDtlsMap,searchType);
						    		 resultVO.getMandalTwnDivisionList().addAll(eventDtlsMap.values());
						    		 eventDtlsMap.clear();
						    		 
						    		 List<Object[]> rtrnEventTwnDivInviteeObjList = eventInviteeDAO.getLocationWiseEventInviteedCntBasedOnUserType(entry.getKey(),new ArrayList<Long>(entry.getValue()), stateId, eventsId, "TownDivision");
						    		 setEventInviteeDetailsToMap(rtrnEventTwnDivInviteeObjList,eventDtlsMap);
						    		// List<Object[]> rtrnEventTwnDivAttendedObjList = eventAttendeeDAO.getLocationWiseEventAttendedCntBasedOnUserType(entry.getKey(),new ArrayList<Long>(entry.getValue()), stateId, eventsId, "TownDivision");
						    		// setEventAttendedCntToMap(rtrnEventTwnDivAttendedObjList,eventDtlsMap);
						    		 List<Object[]> rtrnEventTwnDivInviteeAttendedObjList = eventAttendeeDAO.getLocationWiseEventInviteeAttendedCntBasedOnUserType(entry.getKey(),new ArrayList<Long>(entry.getValue()), stateId, eventsId, "TownDivision");
						    		 setEventInviteeAttendedCntToMap(rtrnEventTwnDivInviteeAttendedObjList,eventDtlsMap);
						    		 caculatingNonInviteeAttendedCnt(eventDtlsMap,searchType);
						    		 resultVO.getMandalTwnDivisionList().addAll(eventDtlsMap.values()); // town divsion data merging 
						    		 eventDtlsMap.clear();
						    	
						    	 }
						    }
					   }  
	                if(userTypeId != null && userTypeId.longValue()==IConstants.CONSTITUENCY_USER_TYPE_ID  || userTypeId.longValue()==IConstants.CONSTITUENCY_INCHARGE_USER_TYPE_ID || userTypeId.longValue()==IConstants.MLA_USER_TYPE_ID){
								    if(accessLevelMap != null && accessLevelMap.size() > 0){
								    	 for(Entry<Long,Set<Long>> entry:accessLevelMap.entrySet()){
								    		 List<Object[]> rtrnEventVllgInviteeObjList = eventInviteeDAO.getLocationWiseEventInviteedCntBasedOnUserType(entry.getKey(),new ArrayList<Long>(entry.getValue()), stateId, eventsId, "Village");
								    		 setEventInviteeDetailsToMap(rtrnEventVllgInviteeObjList,eventDtlsMap);
								    		// List<Object[]> rtrnEventVllgAttendedObjList = eventAttendeeDAO.getLocationWiseEventAttendedCntBasedOnUserType(entry.getKey(),new ArrayList<Long>(entry.getValue()), stateId, eventsId, "Village");
								    		// setEventAttendedCntToMap(rtrnEventVllgAttendedObjList,eventDtlsMap);
								    		 List<Object[]> rtrnEventVllgInviteeAttendedObjList = eventAttendeeDAO.getLocationWiseEventInviteeAttendedCntBasedOnUserType(entry.getKey(),new ArrayList<Long>(entry.getValue()), stateId, eventsId, "Village");
								    		 setEventInviteeAttendedCntToMap(rtrnEventVllgInviteeAttendedObjList,eventDtlsMap);
								    		 caculatingNonInviteeAttendedCnt(eventDtlsMap,searchType);
								    		 resultVO.getVillageWardList().addAll(eventDtlsMap.values());
								    		 eventDtlsMap.clear();
								    		
								    		 List<Object[]> rtrnEventWrdInviteeObjList = eventInviteeDAO.getLocationWiseEventInviteedCntBasedOnUserType(entry.getKey(),new ArrayList<Long>(entry.getValue()), stateId, eventsId, "Ward");
								    		 setEventInviteeDetailsToMap(rtrnEventWrdInviteeObjList,eventDtlsMap);
								    		// List<Object[]> rtrnEventWrdAttendedObjList = eventAttendeeDAO.getLocationWiseEventAttendedCntBasedOnUserType(entry.getKey(),new ArrayList<Long>(entry.getValue()), stateId, eventsId, "Ward");
								    		// setEventAttendedCntToMap(rtrnEventWrdAttendedObjList,eventDtlsMap);
								    		 List<Object[]> rtrnEventWrdInviteeAttendedObjList = eventAttendeeDAO.getLocationWiseEventInviteeAttendedCntBasedOnUserType(entry.getKey(),new ArrayList<Long>(entry.getValue()), stateId, eventsId, "Ward");
								    		 setEventInviteeAttendedCntToMap(rtrnEventWrdInviteeAttendedObjList,eventDtlsMap);
								    		 caculatingNonInviteeAttendedCnt(eventDtlsMap,searchType);
								    		 resultVO.getVillageWardList().addAll(eventDtlsMap.values()); // ward data merging
								    		 eventDtlsMap.clear();
								    	
								    	 }
								    }
						 }
			   if(resultVO != null ){
				   if(resultVO.getDistrictList() != null && resultVO.getDistrictList().size() > 0){
					   Collections.sort(resultVO.getDistrictList(), eventInviteeeAttendedMemberPercasc);   
				   }
				   if(resultVO.getConstituencyList() != null && resultVO.getConstituencyList().size() > 0){
					   Collections.sort(resultVO.getConstituencyList(), eventInviteeeAttendedMemberPercasc);   
				   }
				   if(resultVO.getMandalTwnDivisionList() != null && resultVO.getMandalTwnDivisionList().size() > 0){
					   Collections.sort(resultVO.getMandalTwnDivisionList(), eventInviteeeAttendedMemberPercasc);   
				   }
				   if(resultVO.getVillageWardList() != null && resultVO.getVillageWardList().size() > 0){
					   Collections.sort(resultVO.getVillageWardList(), eventInviteeeAttendedMemberPercasc);   
				   }
			   }
	     }
		  catch(Exception e) {
	    	 LOG.error("Error occured at getEventPoorPerformanceLocation() in setAttendedDataToMap class",e);	
		  }
	  }
	return resultVO;  
  }
  public static Comparator<EventDetailsVO> eventInviteeeAttendedMemberPercasc = new Comparator<EventDetailsVO>() {
		public int compare(EventDetailsVO member2, EventDetailsVO member1) {
		Double perc2 = member2.getInviteeAttendedCounPer();
		Double perc1 = member1.getInviteeAttendedCounPer();
		//ascending order of percantages.
		 return perc2.compareTo(perc1);
		}
		}; 
  public void setEventInviteeDetailsToMap(List<Object[]> rtrnObjList,Map<Long,EventDetailsVO> eventDtlsMap){
	 try{
		 if(rtrnObjList != null && rtrnObjList.size() > 0){
			 for(Object[] param:rtrnObjList){
				   EventDetailsVO eventVO = eventDtlsMap.get(commonMethodsUtilService.getLongValueForObject(param[0]));
				     if(eventVO == null ){
				    	 eventVO = new EventDetailsVO();
				    	 eventVO.setId(commonMethodsUtilService.getLongValueForObject(param[0]));
				    	 eventVO.setName(commonMethodsUtilService.getStringValueForObject(param[1]));
				    	 eventVO.setInviteeCount(commonMethodsUtilService.getLongValueForObject(param[2]));
				    	 eventDtlsMap.put(eventVO.getId(), eventVO);
				     }
			 }
		 }
	 }catch(Exception e){
		 LOG.error("Error occured at getSelectedChildMembersForEvents() in setAttendedDataToMap class",e);
	 }
  }
 public void setEventAttendedCntToMap(List<Object[]> rtrnEventAttendedObjList,Map<Long,EventDetailsVO> eventDtlsMap){
	 try{
		if(rtrnEventAttendedObjList != null && rtrnEventAttendedObjList.size() > 0){
			 for(Object[] param:rtrnEventAttendedObjList){
				 EventDetailsVO eventVO = eventDtlsMap.get(commonMethodsUtilService.getLongValueForObject(param[0]));
				     if(eventVO == null){
				    	 eventVO = new EventDetailsVO();
				    	 eventVO.setId(commonMethodsUtilService.getLongValueForObject(param[0]));
				    	 eventVO.setName(commonMethodsUtilService.getStringValueForObject(param[1]));
				    	 eventVO.setAttendedCount(commonMethodsUtilService.getLongValueForObject(param[2]));
				    	 eventDtlsMap.put(eventVO.getId(), eventVO);
				     }else{
				    	 eventVO.setAttendedCount(commonMethodsUtilService.getLongValueForObject(param[2])); 
				     }
			 }
		}
	 }catch (Exception e) {
		 LOG.error("Error occured at setEventAttendedCntToMap() in setAttendedDataToMap class",e);
	}
 }
 public void setEventInviteeAttendedCntToMap(List<Object[]> rtrnEventInviteeAttendedObjList,Map<Long,EventDetailsVO> eventDtlsMap){
	 try{
		if(rtrnEventInviteeAttendedObjList != null && rtrnEventInviteeAttendedObjList.size() > 0){
			for(Object[] param:rtrnEventInviteeAttendedObjList){
				EventDetailsVO eventVO = eventDtlsMap.get(commonMethodsUtilService.getLongValueForObject(param[0]));
				  if(eventVO != null ){
					  eventVO.setInviteeAttendedCount(commonMethodsUtilService.getLongValueForObject(param[2]));
				  }
			}
		}
	 }catch(Exception e) {
	   LOG.error("Error occured at setEventInviteeAttendedCntToMap() in setAttendedDataToMap class",e);
	}
 }
 public void caculatingNonInviteeAttendedCnt(Map<Long,EventDetailsVO> eventDtlsMap,String searchType){
	 try{
		 if(searchType.trim().equalsIgnoreCase("events")){
			 if(eventDtlsMap != null && eventDtlsMap.size() > 0){
				 for(Entry<Long,EventDetailsVO> entry:eventDtlsMap.entrySet()){
					 EventDetailsVO eventVO = entry.getValue();
					 	//eventVO.setNonInviteeAttendedCount(eventVO.getAttendedCount()-eventVO.getInviteeAttendedCount());
					 	//eventVO.setNonInviteeAttendedCountPer(calculatePercantage(eventVO.getNonInviteeAttendedCount(),eventVO.getAttendedCount()));
					 	eventVO.setInviteeAttendedCounPer(calculatePercantage(eventVO.getInviteeAttendedCount(), eventVO.getInviteeCount()));
				 }
			 }
		 }else{
			 if(eventDtlsMap != null && eventDtlsMap.size() > 0){
				 for(Entry<Long,EventDetailsVO> entry:eventDtlsMap.entrySet()){
					 EventDetailsVO eventVO = entry.getValue();
					 	//eventVO.setNonInviteeAttendedCount(eventVO.getAttendedCount()-eventVO.getInviteeAttendedCount());
					 	//eventVO.setNonInviteeAttendedCountPer(calculatePercantage(eventVO.getNonInviteeAttendedCount(),eventVO.getAttendedCount()));
					 	eventVO.setInviteeAttendedCounPer(0.00d);//calculatePercantage(eventVO.getInviteeAttendedCount(), eventVO.getInviteeCount()));
				 }
			 }
	  }
	 }catch(Exception e){
		LOG.error("Error occured at caculatingNonInviteeAttendedCnt() in setAttendedDataToMap class",e);
	}
 }
 
 /**
    * @param  List<Long> activityIds
    * @param  List<Long> activityLevelIds
	* @param  Long activityMemberId
	* @param  Long stateId
	* @param  Long userId
	* @param  Long userTypeId
	* @param  fromDateStr
	* @param  toDateStr
	* @return  List<List<UserTypeVO>>
	* @author Santosh 
	* @Description :This Service Method is used to get Activity Conducted Count And Percentage. 
	* @since 24-JANAURY-2017
	*/
 @SuppressWarnings("null")
public List<List<UserTypeVO>> getUserTypeActivityConductedCnt(List<Long> activityIds,List<Long> activityLevelIds, Long activityMemberId,Long userId,Long userTypeId,Long stateId,String fromDateStr,String toDateStr) {
	 
	  List<List<UserTypeVO>> resultList = new ArrayList<List<UserTypeVO>>(0);
	 
	  Map<String,Long> totalActivityCntMap = new HashMap<String, Long>(0);
	  Map<String,Long> activityConductedCntMap = new HashMap<String, Long>(0);
	  Map<Long,List<Long>> activityLevelMap = new HashMap<Long, List<Long>>(); 
	  Map<Long,Set<Long>> locationLevelMap = null;
	  Map<Long,Map<Long,UserTypeVO>> userTypeMapDtls = null;
	  SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
	  Date toDate=null;
	  Date fromDate=null;
	  try{
		  
		    if(fromDateStr != null && fromDateStr.trim().length()>0 && toDateStr!= null && toDateStr.trim().length()>0){
				 toDate = sdf.parse(toDateStr);
				 fromDate = sdf.parse(fromDateStr);
			 }
		  
		     ActivityMemberVO activityMemberVO = new ActivityMemberVO();
		     activityMemberVO.setUserId(userId);
		     activityMemberVO.setActivityMemberId(activityMemberId);
		     activityMemberVO.setUserTypeId(userTypeId);
		     activityMemberVO = coreDashboardGenericService.getChildActivityMembersAndLocationsNew(activityMemberVO);//calling generic method.
		     userTypeMapDtls = activityMemberVO.getUserTypesMap();
		     locationLevelMap = activityMemberVO.getLocationLevelIdsMap();
		   
		     if(activityLevelIds !=  null && activityLevelIds.size() > 0){
		    	 setSelectedActivityLevel(activityLevelMap,activityIds,activityLevelIds);
		      }else{
	    	     List<Object[]> rtrnActivityLevelObjLst = activityScopeDAO.getActivityLevelIdBasedOnActivityId(activityIds);
			     setActiviyLevel(rtrnActivityLevelObjLst,activityLevelMap);
	        }
		     
		     //getting total activity count
		     if(activityLevelMap != null && activityLevelMap.size() > 0){
		    	 for(Entry<Long,List<Long>> activityEntry:activityLevelMap.entrySet()){
		    		 if(locationLevelMap != null && locationLevelMap.size() > 0){
		    			for(Entry<Long,Set<Long>> locationEntry:locationLevelMap.entrySet()){
		    				     Long accessLevelValue =0l;	
							     if(locationEntry.getKey().longValue() == 4l){// user level 4 means parliament constituency in the case of core dashboard
							    	 accessLevelValue = 10l; //region scope 10  means parliament constituency in intermediate table so that we are replacing value
							     }else if(locationEntry.getKey().longValue()==5l){// user level 5 means constituency in the case of core dashboard
							    	 accessLevelValue = 4l;  //region scope 4  means constituency in intermediate table so that we are replacing value
							     }else{
							    	 accessLevelValue = locationEntry.getKey();	 
							     }
		    				     List<Object[]> rtrnTotalActivityCntObjLst = locationInfoDAO.getTotalActivityLocationWise(getRequiredRegionScopeIds(activityEntry.getValue()), accessLevelValue,locationEntry.getValue());
		    				     setRequiredActivityData(rtrnTotalActivityCntObjLst,totalActivityCntMap,locationEntry.getKey());
		    			}
		    		 }
		    		 
		    	 }
		     }
		     //getting total activity conducted count
		     if(activityLevelMap != null && activityLevelMap.size() > 0){
		    	 for(Entry<Long,List<Long>> activityEntry:activityLevelMap.entrySet()){
		    		 if(locationLevelMap != null && locationLevelMap.size() > 0){
		    			for(Entry<Long,Set<Long>> locationEntry:locationLevelMap.entrySet()){
		    				     List<Object[]> rtrnTotalActivityCntObjLst = activityLocationInfoDAO.getActivityConductedCntBasedOnUserAccesslevel(locationEntry.getKey(), new ArrayList<Long>(locationEntry.getValue()), stateId, activityEntry.getKey(), new ArrayList<Long>(activityEntry.getValue()),fromDate,toDate);
		    				     List<Object[]> rtrnTotalActivityCntObjectLst = activityConductedInfoDAO.getActivityConductedCntBasedOnUserAccesslevel(locationEntry.getKey(), new ArrayList<Long>(locationEntry.getValue()), stateId, activityEntry.getKey(), new ArrayList<Long>(activityEntry.getValue()),fromDate,toDate);
		    				     if(commonMethodsUtilService.isListOrSetValid(rtrnTotalActivityCntObjectLst)){
		    				    	 if(!commonMethodsUtilService.isListOrSetValid(rtrnTotalActivityCntObjLst))
		    				    		 rtrnTotalActivityCntObjLst = new ArrayList<Object[]>(0);
		    				    		 rtrnTotalActivityCntObjLst.addAll(rtrnTotalActivityCntObjectLst);
		    				     }
		    				     setRequiredActivityData(rtrnTotalActivityCntObjLst,activityConductedCntMap,locationEntry.getKey());
		    			}
		    		 }
		    	 }
		     }
		     //pushing total activity count 
			    if(userTypeMapDtls != null && userTypeMapDtls.size() > 0){
			    	
					  for (Entry<Long, Map<Long, UserTypeVO>> entry : userTypeMapDtls.entrySet()) {
						  
					      Map<Long,UserTypeVO> userTypeMap = entry.getValue();
					      
					      for(UserTypeVO vo:userTypeMap.values()){
					    	  
					    	  for(Long locationValueId:vo.getLocationValuesSet()){
					    		  
					    		  String key = vo.getLocationLevelId()+"-"+locationValueId;
					    		  
					    		  if(totalActivityCntMap.get(key) != null){
					    			  
							    		vo.setTotalCount(vo.getTotalCount()+totalActivityCntMap.get(key));
							    	 }
					    	  }
					      }
				     }  
				}
			  //pushing total conducted activity count 
			    if(userTypeMapDtls != null && userTypeMapDtls.size() > 0){
			    	
					  for (Entry<Long, Map<Long, UserTypeVO>> entry : userTypeMapDtls.entrySet()) {
						  
					      Map<Long,UserTypeVO> userTypeMap = entry.getValue();
					      
					      for(UserTypeVO vo:userTypeMap.values()){
					    	  
					    	  for(Long locationValueId:vo.getLocationValuesSet()){
					    		  
					    		  String key = vo.getLocationLevelId()+"-"+locationValueId;
					    		  
					    		  if(activityConductedCntMap.get(key) != null){
					    			  
							    		vo.setCompletedCount(vo.getCompletedCount()+activityConductedCntMap.get(key));
							    	 }
					    	  }
					      }
				     }  
				}
			    // Calculate percentage
			    if(userTypeMapDtls != null && userTypeMapDtls.size() > 0){
			    	
					  for (Entry<Long, Map<Long, UserTypeVO>> entry : userTypeMapDtls.entrySet()) {
						  
					      Map<Long,UserTypeVO> userTypeMap = entry.getValue();
					      
					      for(UserTypeVO vo:userTypeMap.values()){
					    	  
					    	  vo.setCompletedPerc(calculatePercantage(vo.getCompletedCount(),vo.getTotalCount()));
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
						Collections.sort(memberList, activityConductedPercDesc);
					}
				}
		}catch(Exception e){
			LOG.error("Error occured at getUserTypeActivityConductedCnt() in setAttendedDataToMap class",e);	
		}
	  return resultList;
  }
 public void setActiviyLevel(List<Object[]>  objList,Map<Long,List<Long>> activityLevelMap){
	 try{
		 if(objList != null && objList.size() > 0){
			 for(Object[] param:objList){
				 List<Long> activityLevelList = activityLevelMap.get(commonMethodsUtilService.getLongValueForObject(param[0]));
				  if(activityLevelList == null){
					  activityLevelList = new ArrayList<Long>();  
					  activityLevelMap.put(commonMethodsUtilService.getLongValueForObject(param[0]), activityLevelList);
				  }
				  activityLevelList.add(commonMethodsUtilService.getLongValueForObject(param[2]));
			 }
		 }
	 }catch(Exception e){
		 LOG.error("Error occured at setActiviyLevel() in setAttendedDataToMap class",e); 
	 }
 }
 public void setSelectedActivityLevel(Map<Long,List<Long>> activityMap,List<Long> activityIds,List<Long> activityLevel){
	 try{
		 if(activityIds != null && activityIds.size() > 0){
			 Long activityId = activityIds.get(0);
			 activityMap.put(activityId, activityLevel);
		 }
	 }catch(Exception e){
		 LOG.error("Error occured at setSelectedActivityLevel() in setAttendedDataToMap class",e);	 
	 }
 }
 public List<Long> getRequiredRegionScopeIds(List<Long> levelIds){
	 List<Long> regionScopeList = new ArrayList<Long>(0);
	 try{
		  if(levelIds != null && levelIds.size() > 0){
			  for(Long levelId:levelIds){
				  if(levelId != null){
		              if(levelId.longValue() == 1L){
		            	  regionScopeList.add(6L);
		            	  regionScopeList.add(8L);
		              }else if(levelId.longValue() == 2L){
		            	  regionScopeList.add(5L);
		            	  regionScopeList.add(7L);
		              }else if(levelId.longValue() == 3L){
		            	  regionScopeList.add(3L);
		              }else if(levelId.longValue() == 4L){
		            	  regionScopeList.add(2L);	  
		              }else if(levelId.longValue() == 5L){
		            	  regionScopeList.add(4L);	  
		              }
		            }  	  
			  }
		  }
	 }catch(Exception e){
		 LOG.error("Error occured at getRequiredRegionScopeIds() in setAttendedDataToMap class",e); 	 
	 }
	 return regionScopeList;
 }
 public void setRequiredActivityData(List<Object[]> objList,Map<String,Long> totalActivityCntMap,Long locationScopeId){
	 try{
		 if(objList != null && objList.size() > 0){
			 for(Object[] param:objList){
				   String locationLevelAndId = locationScopeId+"-"+commonMethodsUtilService.getStringValueForObject(param[0]);
				   Long activityCnt = commonMethodsUtilService.getLongValueForObject(param[1]);
				   Long totalActivityCnt = totalActivityCntMap.get(locationLevelAndId);
				   if(totalActivityCnt == null){
					   totalActivityCnt = 0l;
				   }
				   totalActivityCntMap.put(locationLevelAndId, totalActivityCnt+activityCnt);
			 }
		 }
	 }catch(Exception e){
		 LOG.error("Error occured at setTotalActivityCntLocationWise() in setAttendedDataToMap class",e); 
	 }
 }
 public static Comparator<UserTypeVO> activityConductedPercDesc = new Comparator<UserTypeVO>() {
		public int compare(UserTypeVO member2, UserTypeVO member1) {

		Double perc2 = member2.getCompletedPerc();
		Double perc1 = member1.getCompletedPerc();
		//descending order of percantages.
		 return perc1.compareTo(perc2);
		}
	}; 
	
	public void setTotalActivitiesCount( Map<Long,List<Long>> levlScopesMap,List<UserTypeVO> resultList,Map<Long,Set<Long>>  locationLevelIdsMap,
			Long stateId,Map<Long,UserTypeVO>  childActivityMembersMap,String type){
		 Map<Long,Map<Long,Long>> totalScopeLocationsMap = new HashMap<Long, Map<Long,Long>>(0);
		  
    	 for(Entry<Long,Set<Long>> entry:locationLevelIdsMap.entrySet()){
    		 
    			//Map<Long ,List<Long>>  activityLevelScopesMap = new HashMap<Long, List<Long>>(0);
    			
    			if(commonMethodsUtilService.isMapValid(levlScopesMap)){
    				for (Long levelId : levlScopesMap.keySet()) {
    					 SearchAttributeVO searchAttributeVO = new SearchAttributeVO();
    					if(!commonMethodsUtilService.isListOrSetValid(searchAttributeVO.getLocationTypeIdsList()))
		    	            searchAttributeVO.setLocationTypeIdsList(new ArrayList<Long>(0));
		    	          
		    	          if(levelId != null){
		    	            if(levelId.longValue() == 1L){
		    	            	searchAttributeVO.getLocationTypeIdsList().add(6L);
		    	            	searchAttributeVO.getLocationTypeIdsList().add(8L);
		    	            }
		    	            else if(levelId.longValue() == 2L){
		    	            	searchAttributeVO.getLocationTypeIdsList().add(5L);
		    	            	searchAttributeVO.getLocationTypeIdsList().add(7L);
		    	            }
		    	            else if(levelId.longValue() == 3L){
		    	            	searchAttributeVO.getLocationTypeIdsList().add(3L);
		    	            }
		    	            else if(levelId.longValue() == 4L){
		    	            	searchAttributeVO.getLocationTypeIdsList().add(2L);
		    	            }else if(levelId.longValue() == 5L){
		    	            	searchAttributeVO.getLocationTypeIdsList().add(4L);   
		    	            }
		    	          }
		    	          
		    	          if(entry.getKey().longValue() == 3L){
		    	        	  searchAttributeVO.setScopeId(3L);
		    	          }else if(entry.getKey().longValue() == 4L){
		    	        	  searchAttributeVO.setScopeId(10L);
		    	          }else if(entry.getKey().longValue() == 5L){
		    	        	  searchAttributeVO.setScopeId(4L);
		    	          }else if(entry.getKey().longValue() == 6L){
		    	        	  searchAttributeVO.setScopeId(5L);
		    	          }else if(entry.getKey().longValue() == 7L){
		    	        	  searchAttributeVO.setScopeId(7L);
		    	          }else if(entry.getKey().longValue() == 8L){
		    	        	  searchAttributeVO.setScopeId(6L);
		    	          }else if(entry.getKey().longValue() == 9L){
		    	        	  searchAttributeVO.setScopeId(8L);
		    	          }
		    	          List<Object[]> areasList = null;
		    	          List<Long> scopesList = null;
		    	          List<Object[]> list1 = null;
		    	          if(type != null && type.equalsIgnoreCase("totalcount")){
		    	        	  areasList  = locationInfoDAO.areaCountDetailsListByAreaIdsOnScope(searchAttributeVO,null,null);
		    	        	  scopesList = levlScopesMap.get(levelId);
		    	          }else if(type != null && type.equalsIgnoreCase("conductedcount")){
		    	        	  scopesList = levlScopesMap.get(levelId);
		    	        	  areasList = activityLocationInfoDAO.getActivityConductedCount(entry.getKey(),new ArrayList<Long>(entry.getValue()), stateId);
		    	      		  list1 = activityConductedInfoDAO.getActivityConductedCount(entry.getKey(),new ArrayList<Long>(entry.getValue()), stateId);
		    	      		if(commonMethodsUtilService.isListOrSetValid(list1))
		    	      			if(!commonMethodsUtilService.isListOrSetValid(areasList))
		    	      				areasList = new ArrayList<Object[]>(0);
		    	      				areasList.addAll(list1);
		    	          }
		    	          
	    	    	  for (Long scopeId : scopesList) {
    	        		  if(commonMethodsUtilService.isListOrSetValid(areasList)){
    	      	            for (Object[] param : areasList) {      	              
    	      	              Map<Long,Long> totalLocationsMap = new HashMap<Long, Long>();
    	      	              Long count = 0L;
    	      	              if(totalScopeLocationsMap.get(scopeId) != null){
    	      	                totalLocationsMap = totalScopeLocationsMap.get(scopeId);
    	      	                count=totalLocationsMap.get(commonMethodsUtilService.getLongValueForObject(param[2]));
    	      	              }
    	      	              if(count == null)
    	      	                count = 0L;
    	      	              totalLocationsMap.put(commonMethodsUtilService.getLongValueForObject(param[2]), count+commonMethodsUtilService.getLongValueForObject(param[1]));
    	      	              totalScopeLocationsMap.put(scopeId, totalLocationsMap);
    	      	            }
    	      	          }
    	                } 
					}
    			}
        
          
    	if(commonMethodsUtilService.isMapValid(levlScopesMap)){
    		for (Long levelId : levlScopesMap.keySet()) {
    			List<Long> scopesList = levlScopesMap.get(levelId);
    			for (Long scopeId : scopesList) {
    				 Map<Long,Long> locatnContMap = totalScopeLocationsMap.get(scopeId);
    	        	  if(commonMethodsUtilService.isMapValid(locatnContMap)){
    	        		  if(childActivityMembersMap != null && childActivityMembersMap.size() > 0){
					    	//for(UserTypeVO VO:childActivityMembersMap.values()){
    	        			  
    	        			  UserTypeVO VO = childActivityMembersMap.get(scopeId);
					    		for(Long locationValueId:VO.getLocationValuesSet()){
					    			if(type != null && type.equalsIgnoreCase("totalcount")){
					    				Long count =VO.getTotalActvtiesCount() != null ?VO.getTotalActvtiesCount().longValue() : 0l;
					    				Long count1 =locatnContMap.get(locationValueId) != null ?locatnContMap.get(locationValueId).longValue() : 0l;
					    				VO.setTotalActvtiesCount(count+count1);//Total Activities Count For location Value
					    			}else if(type != null && type.equalsIgnoreCase("conductedcount")){
					    				Long count =VO.getCondctedActiesCount() != null ?VO.getCondctedActiesCount().longValue() : 0l;
					    				Long count1 =locatnContMap.get(locationValueId) != null ?locatnContMap.get(locationValueId).longValue() : 0l;
					    				VO.setCondctedActiesCount(count+count1);//Conducted Activities Count For location Value
					    			}
					    		}
					    	//}
    	        		}
				   }
    			}
    		}
    	}
     }
    	 
    }

}



	  