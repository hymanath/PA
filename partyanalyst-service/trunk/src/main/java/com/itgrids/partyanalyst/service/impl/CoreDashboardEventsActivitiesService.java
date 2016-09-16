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

import com.itgrids.partyanalyst.dao.IActivityMemberAccessLevelDAO;
import com.itgrids.partyanalyst.dao.IEventAttendeeDAO;
import com.itgrids.partyanalyst.dao.IEventInviteeDAO;
import com.itgrids.partyanalyst.dto.ActivityMemberVO;
import com.itgrids.partyanalyst.dto.EventDetailsVO;
import com.itgrids.partyanalyst.dto.UserTypeVO;
import com.itgrids.partyanalyst.service.ICoreDashboardEventsActivitiesService;
import com.itgrids.partyanalyst.service.ICoreDashboardGenericService;
import com.itgrids.partyanalyst.utils.CommonMethodsUtilService;

public class CoreDashboardEventsActivitiesService implements ICoreDashboardEventsActivitiesService {
	
	private final static Logger LOG = Logger.getLogger(CoreDashboardEventsActivitiesService.class);
	
	 private CommonMethodsUtilService commonMethodsUtilService;
	 private ICoreDashboardGenericService coreDashboardGenericService;
	 private IActivityMemberAccessLevelDAO activityMemberAccessLevelDAO;
	 private IEventInviteeDAO eventInviteeDAO;
	 private IEventAttendeeDAO eventAttendeeDAO;
	 
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
								  eventVO.setNonInviteeAttendedCount(eventVO.getAttendedCount()-eventVO.getInviteeAttendedCount());
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
		     if(locationLevelMap != null && locationLevelMap.size() > 0){
		    	 
		    	 for(Entry<Long,Set<Long>> entry:locationLevelMap.entrySet()){
		    		 
		    		 List<Object[]> rtrnEventAttendedCnt = eventAttendeeDAO.getEventAttendedCountLocationWise(entry.getKey(),new ArrayList<Long>(entry.getValue()), stateId, eventIds);
		    		  
		    		 if(rtrnEventAttendedCnt != null && rtrnEventAttendedCnt.size() > 0){
		    			 
		    			  for (Object[] param : rtrnEventAttendedCnt) {
		    				  
		    				  String locationLevelAndId = entry.getKey()+"-"+commonMethodsUtilService.getStringValueForObject(param[0]);
		    				  eventAttendedMap.put(locationLevelAndId, commonMethodsUtilService.getLongValueForObject(param[1]));
						}
		    		  }
		    	 }
		     }
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
		     //pushing attended cnt
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
			}
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
				    	  
				    	  vo.setNonInviteeAttendedCnt(vo.getAttendedCnt()-vo.getInviteeAttendedCnt());
				    	  vo.setNonInviteeAttendedCntPer(calculatePercantage(vo.getNonInviteeAttendedCnt(),vo.getAttendedCnt()));
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
	
}
