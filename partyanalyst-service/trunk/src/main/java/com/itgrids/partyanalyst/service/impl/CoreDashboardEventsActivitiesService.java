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
import com.itgrids.partyanalyst.dto.InviteesVO;
import com.itgrids.partyanalyst.dto.TrainingCampProgramVO;
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
	/**
	* @param  Long activityMemberId
	* @param  Long stateId
	* @param  List<Long> eventIds
	* @return List<EventDetailsVO>
	* @author Santosh 
	* @Description :This Service Method is used to basic event invitee attended and invitee attended count.. 
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
					 if(eventInviteeMapDtls != null && eventInviteeMapDtls.size() > 0){ // merging mandal data if there.
					 eventVO.getLocationList().addAll(eventInviteeMapDtls.get(entry.getKey()).values());
					 }
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
 public List<UserTypeVO> getSelectedChildMembersForEvents(Long parentActivityMemberId,List<Long> childUserTypeIds,String reportType,Long stateId,List<Long> eventIds){
	 
	 List<UserTypeVO> resultList = new ArrayList<UserTypeVO>();
	 Map<String,Long> eventIviteeMap = new HashMap<String, Long>(0);
	 Map<String,Long> eventInviteeAttendedMap = new HashMap<String, Long>(0);
	 Map<String,Long> eventAttendedMap = new HashMap<String, Long>(0);
	
	 
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
	 return resultList;
 }
	public static Comparator<UserTypeVO> eventInviteeAttendedPercDesc = new Comparator<UserTypeVO>() {
	public int compare(UserTypeVO member2, UserTypeVO member1) {

	Double perc2 = member2.getInviteeAttendedCntPer();
	Double perc1 = member1.getInviteeAttendedCntPer();
	//descending order of percantages.
	 return perc1.compareTo(perc2);
	}
	}; 
  public EventDetailsVO getEventPoorPerformanceLocation(Long userTypeId,Long stateId,Long activityMemberId,List<Long> eventsId){
	  
	  EventDetailsVO resultVO = new EventDetailsVO();
	  Map<Long,EventDetailsVO> eventDtlsMap = new HashMap<Long, EventDetailsVO>(0);
	  Map<Long,Set<Long>> accessLevelMap = new HashMap<Long, Set<Long>>(0);
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
			    		 caculatingNonInviteeAttendedCnt(eventDtlsMap);
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
			    		 caculatingNonInviteeAttendedCnt(eventDtlsMap);
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
					    		 caculatingNonInviteeAttendedCnt(eventDtlsMap);
					    		 resultVO.getMandalTwnDivisionList().addAll(eventDtlsMap.values());
					    		 eventDtlsMap.clear();
					    		 
					    		 List<Object[]> rtrnEventTwnDivInviteeObjList = eventInviteeDAO.getLocationWiseEventInviteedCntBasedOnUserType(entry.getKey(),new ArrayList<Long>(entry.getValue()), stateId, eventsId, "TownDivision");
					    		 setEventInviteeDetailsToMap(rtrnEventTwnDivInviteeObjList,eventDtlsMap);
					    		// List<Object[]> rtrnEventTwnDivAttendedObjList = eventAttendeeDAO.getLocationWiseEventAttendedCntBasedOnUserType(entry.getKey(),new ArrayList<Long>(entry.getValue()), stateId, eventsId, "TownDivision");
					    		// setEventAttendedCntToMap(rtrnEventTwnDivAttendedObjList,eventDtlsMap);
					    		 List<Object[]> rtrnEventTwnDivInviteeAttendedObjList = eventAttendeeDAO.getLocationWiseEventInviteeAttendedCntBasedOnUserType(entry.getKey(),new ArrayList<Long>(entry.getValue()), stateId, eventsId, "TownDivision");
					    		 setEventInviteeAttendedCntToMap(rtrnEventTwnDivInviteeAttendedObjList,eventDtlsMap);
					    		 caculatingNonInviteeAttendedCnt(eventDtlsMap);
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
							    		 caculatingNonInviteeAttendedCnt(eventDtlsMap);
							    		 resultVO.getVillageWardList().addAll(eventDtlsMap.values());
							    		 eventDtlsMap.clear();
							    		
							    		 List<Object[]> rtrnEventWrdInviteeObjList = eventInviteeDAO.getLocationWiseEventInviteedCntBasedOnUserType(entry.getKey(),new ArrayList<Long>(entry.getValue()), stateId, eventsId, "Ward");
							    		 setEventInviteeDetailsToMap(rtrnEventWrdInviteeObjList,eventDtlsMap);
							    		// List<Object[]> rtrnEventWrdAttendedObjList = eventAttendeeDAO.getLocationWiseEventAttendedCntBasedOnUserType(entry.getKey(),new ArrayList<Long>(entry.getValue()), stateId, eventsId, "Ward");
							    		// setEventAttendedCntToMap(rtrnEventWrdAttendedObjList,eventDtlsMap);
							    		 List<Object[]> rtrnEventWrdInviteeAttendedObjList = eventAttendeeDAO.getLocationWiseEventInviteeAttendedCntBasedOnUserType(entry.getKey(),new ArrayList<Long>(entry.getValue()), stateId, eventsId, "Ward");
							    		 setEventInviteeAttendedCntToMap(rtrnEventWrdInviteeAttendedObjList,eventDtlsMap);
							    		 caculatingNonInviteeAttendedCnt(eventDtlsMap);
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
     }catch(Exception e) {
    	 LOG.error("Error occured at getSelectedChildMembersForEvents() in setAttendedDataToMap class",e);	
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
 public void caculatingNonInviteeAttendedCnt(Map<Long,EventDetailsVO> eventDtlsMap){
	 try{
		if(eventDtlsMap != null && eventDtlsMap.size() > 0){
			for(Entry<Long,EventDetailsVO> entry:eventDtlsMap.entrySet()){
				EventDetailsVO eventVO = entry.getValue();
				//eventVO.setNonInviteeAttendedCount(eventVO.getAttendedCount()-eventVO.getInviteeAttendedCount());
				//eventVO.setNonInviteeAttendedCountPer(calculatePercantage(eventVO.getNonInviteeAttendedCount(),eventVO.getAttendedCount()));
				eventVO.setInviteeAttendedCounPer(calculatePercantage(eventVO.getInviteeAttendedCount(), eventVO.getInviteeCount()));
			}
		}
	 }catch(Exception e){
		LOG.error("Error occured at caculatingNonInviteeAttendedCnt() in setAttendedDataToMap class",e);
	}
 }
}
