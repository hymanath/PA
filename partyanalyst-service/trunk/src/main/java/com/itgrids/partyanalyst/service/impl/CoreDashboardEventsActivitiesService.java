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
import com.itgrids.partyanalyst.dao.IActivityQuestionAnswerDAO;
import com.itgrids.partyanalyst.dao.IActivityQuestionnaireDAO;
import com.itgrids.partyanalyst.dao.IActivityScopeDAO;
import com.itgrids.partyanalyst.dao.IConstituencyDAO;
import com.itgrids.partyanalyst.dao.IDistrictDAO;
import com.itgrids.partyanalyst.dao.IEventAttendeeDAO;
import com.itgrids.partyanalyst.dao.IEventInviteeDAO;
import com.itgrids.partyanalyst.dao.ILocalElectionBodyDAO;
import com.itgrids.partyanalyst.dao.ILocationInfoDAO;
import com.itgrids.partyanalyst.dao.IPanchayatDAO;
import com.itgrids.partyanalyst.dao.IPartyMeetingDAO;
import com.itgrids.partyanalyst.dao.IStateDAO;
import com.itgrids.partyanalyst.dao.ITehsilDAO;
import com.itgrids.partyanalyst.dto.ActivityMemberVO;
import com.itgrids.partyanalyst.dto.EventDetailsVO;
import com.itgrids.partyanalyst.dto.EventLocationVO;
import com.itgrids.partyanalyst.dto.PartyMeetingVO;
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
	 private IPartyMeetingDAO partyMeetingDAO;
	 private IStateDAO stateDAO;
	 private IDistrictDAO districtDAO;
	 private IConstituencyDAO constituencyDAO;
	 private ITehsilDAO tehsilDAO;
	 private ILocalElectionBodyDAO localElectionBodyDAO;
	 private IPanchayatDAO panchayatDAO;
	 private IActivityQuestionAnswerDAO activityQuestionAnswerDAO;
	private IActivityQuestionnaireDAO activityQuestionnaireDAO;
	 
	 
	public IStateDAO getStateDAO() {
		return stateDAO;
	}
	public void setStateDAO(IStateDAO stateDAO) {
		this.stateDAO = stateDAO;
	}
	public IDistrictDAO getDistrictDAO() {
		return districtDAO;
	}
	public void setDistrictDAO(IDistrictDAO districtDAO) {
		this.districtDAO = districtDAO;
	}
	public IConstituencyDAO getConstituencyDAO() {
		return constituencyDAO;
	}
	public void setConstituencyDAO(IConstituencyDAO constituencyDAO) {
		this.constituencyDAO = constituencyDAO;
	}
	public ITehsilDAO getTehsilDAO() {
		return tehsilDAO;
	}
	public void setTehsilDAO(ITehsilDAO tehsilDAO) {
		this.tehsilDAO = tehsilDAO;
	}
	public ILocalElectionBodyDAO getLocalElectionBodyDAO() {
		return localElectionBodyDAO;
	}
	public void setLocalElectionBodyDAO(ILocalElectionBodyDAO localElectionBodyDAO) {
		this.localElectionBodyDAO = localElectionBodyDAO;
	}
	public IPanchayatDAO getPanchayatDAO() {
		return panchayatDAO;
	}
	public void setPanchayatDAO(IPanchayatDAO panchayatDAO) {
		this.panchayatDAO = panchayatDAO;
	}
	public IPartyMeetingDAO getPartyMeetingDAO() {
		return partyMeetingDAO;
	}
	public void setPartyMeetingDAO(IPartyMeetingDAO partyMeetingDAO) {
		this.partyMeetingDAO = partyMeetingDAO;
	}
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
	
	public IActivityQuestionAnswerDAO getActivityQuestionAnswerDAO() {
		return activityQuestionAnswerDAO;
	}
	public void setActivityQuestionAnswerDAO(
			IActivityQuestionAnswerDAO activityQuestionAnswerDAO) {
		this.activityQuestionAnswerDAO = activityQuestionAnswerDAO;
	}
	
	public IActivityQuestionnaireDAO getActivityQuestionnaireDAO() {
		return activityQuestionnaireDAO;
	}
	public void setActivityQuestionnaireDAO(
			IActivityQuestionnaireDAO activityQuestionnaireDAO) {
		this.activityQuestionnaireDAO = activityQuestionnaireDAO;
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
			  stateId = 0L;
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
		  stateId = 0L;
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
		 stateId =0L;
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
	 try {
		 if(searchType.trim().equalsIgnoreCase("events")){
			 return getSelectedChildMembersForEvnts(parentActivityMemberId,childUserTypeIds,reportType,stateId,eventIds,searchType);
		 }
		 else  if(searchType != null && searchType.equalsIgnoreCase("All") ||  searchType.equalsIgnoreCase("SingleActivity") ||  searchType.equalsIgnoreCase("activities") || 
				 searchType.equalsIgnoreCase("ScopeId") ){
			 if(!searchType.equalsIgnoreCase("ScopeId")){
				 List<Object[]> levelScopes = activityScopeDAO.getLevelAndScopeIds(eventIds,"all");
				 if(commonMethodsUtilService.isListOrSetValid(levelScopes)){
					 eventIds.clear();				 
					 for (Object[] obj : levelScopes) {
						 eventIds.add(commonMethodsUtilService.getLongValueForObject(obj[1]));
					}
				 }
			 }
			 
			 return getSelectedChildMembersForActivities(parentActivityMemberId,childUserTypeIds,reportType,stateId,eventIds,searchType);
		 }
		 
	} catch (Exception e) {
		LOG.error("Error occured at getSelectedChildMembersForEvents() in setAttendedDataToMap class",e);
	}
	 return null;
 }
 
 @SuppressWarnings("unused")
public List<UserTypeVO> getSelectedChildMembersForActivities(Long parentActivityMemberId,List<Long> childUserTypeIds,String reportType,Long stateId,List<Long> eventIds,String searchType){
	 List<UserTypeVO> resultList = new ArrayList<UserTypeVO>();
	 Map<String,Long> eventIviteeMap = new HashMap<String, Long>(0);
	 Map<String,Long> eventInviteeAttendedMap = new HashMap<String, Long>(0);
	 Map<String,Long> eventAttendedMap = new HashMap<String, Long>(0);
	
	 try {
		 
			 Map<Long,Set<Long>> locationLevelIdsMap=null;
			  Map<String,String>     nameForLocationMap=null;
			  ActivityMemberVO activityMemberVO=null;
			  Map<Long,UserTypeVO> childActivityMembersMap=null;
			  
			  List<Object[]> hasSpecilaActivitiesList = activityConductedInfoDAO.getTotalCountsForScopeIds(eventIds,null,null,null);
			 Map<String,Long> activityConductedMap = new HashMap<String, Long>(0);
			 Map<String,Long> totalActiviteesMap = new HashMap<String, Long>(0);
			 //Map<String,Long> activityAttendedMap = new HashMap<String, Long>(0);
			 
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
					// if(searchType != null && searchType.equalsIgnoreCase("events")){
			if(locationLevelIdsMap != null && locationLevelIdsMap.size() > 0){
		    	 for(Entry<Long,Set<Long>> entry:locationLevelIdsMap.entrySet()){
		    		 
		    		 List<Object[]> rtrnActivitiesObjLst = new ArrayList<Object[]>(0);
		    		 List<Object[]> rtnActivitiesObjLst = activityLocationInfoDAO.getLocationWiseTotalActivitiesCount(entry.getKey(), new ArrayList<Long>(entry.getValue()), stateId, eventIds);
		    		 if(commonMethodsUtilService.isListOrSetValid(hasSpecilaActivitiesList)){
		    			 List<Object[]> rtrActivitiesObjLst = activityConductedInfoDAO.getLocationWiseTotalActivitiesCount(entry.getKey(), new ArrayList<Long>(entry.getValue()), stateId, eventIds);
		    			 if(commonMethodsUtilService.isListOrSetValid(rtrActivitiesObjLst))
			    			 rtrnActivitiesObjLst.addAll(rtrActivitiesObjLst);
		    		 }
		    		 
		    		 if(commonMethodsUtilService.isListOrSetValid(rtnActivitiesObjLst))
		    			 rtrnActivitiesObjLst.addAll(rtnActivitiesObjLst);
		    		 
		    		 
		    		 if(rtrnActivitiesObjLst != null && rtrnActivitiesObjLst.size() > 0){
		    			  for (Object[] param : rtrnActivitiesObjLst) {
		    				  String locationLevelAndId = entry.getKey()+"-"+commonMethodsUtilService.getStringValueForObject(param[0]);
		    				  Long count =0L;
		    				  
		    				  if(totalActiviteesMap.get(locationLevelAndId) != null)
		    					  count = totalActiviteesMap.get(locationLevelAndId);
		    				  totalActiviteesMap.put(locationLevelAndId, count+commonMethodsUtilService.getLongValueForObject(param[1]));
						}
		    		  }
		    	  }
			    }
			  
			  if(locationLevelIdsMap != null && locationLevelIdsMap.size() > 0){
		    	 for(Entry<Long,Set<Long>> entry:locationLevelIdsMap.entrySet()){
		    		 List<Object[]> rtrnActivitiesObjLst = new ArrayList<Object[]>(0);
		    		 List<Object[]> rtnActivitiesObjLst = activityLocationInfoDAO.getLocationWiseConductedActivitiesCount(entry.getKey(), new ArrayList<Long>(entry.getValue()), stateId, eventIds);
		    		 if(commonMethodsUtilService.isListOrSetValid(hasSpecilaActivitiesList)){
		    			 List<Object[]> rtrActivitiesObjLst = activityConductedInfoDAO.getLocationWiseConductedActivitiesCount(entry.getKey(), new ArrayList<Long>(entry.getValue()), stateId, eventIds);
		    			 if(commonMethodsUtilService.isListOrSetValid(rtrActivitiesObjLst))
			    			 rtrnActivitiesObjLst.addAll(rtrActivitiesObjLst);
		    		 }
		    		 if(commonMethodsUtilService.isListOrSetValid(rtnActivitiesObjLst))
		    			 rtrnActivitiesObjLst.addAll(rtnActivitiesObjLst);
		    		
		    		 
		    		 if(rtrnActivitiesObjLst != null && rtrnActivitiesObjLst.size() > 0){
		    			  for (Object[] param : rtrnActivitiesObjLst) {
		    				  String locationLevelAndId = entry.getKey()+"-"+commonMethodsUtilService.getStringValueForObject(param[0]);
		    				  Long count =0L;			    				  
		    				  if(activityConductedMap.get(locationLevelAndId) != null)
		    					  count = activityConductedMap.get(locationLevelAndId);
		    				  activityConductedMap.put(locationLevelAndId, count+commonMethodsUtilService.getLongValueForObject(param[1]));
						}
		    		  }
		    	 }
			  }
			  
		     //setting invitees count
		    if(childActivityMembersMap != null && childActivityMembersMap.size() > 0){
		    	for(UserTypeVO VO:childActivityMembersMap.values()){
		    		  for(Long locationValueId:VO.getLocationValuesSet()){
			    		  String key = VO.getLocationLevelId()+"-"+locationValueId;
			    		  if(totalActiviteesMap.get(key) != null){
			    			  if(VO.getTotalActvtiesCount() == null)
			    				  VO.setTotalActvtiesCount(totalActiviteesMap.get(key)); 
			    			  else
			    				  VO.setTotalActvtiesCount(VO.getTotalActvtiesCount()+totalActiviteesMap.get(key));
			    		  }
			    	  }
		    	}
		    }
		    
		   //setting attended count
		   if(childActivityMembersMap != null && childActivityMembersMap.size() > 0){
		    	for(UserTypeVO VO:childActivityMembersMap.values()){
		    		  for(Long locationValueId:VO.getLocationValuesSet()){
			    		  String key = VO.getLocationLevelId()+"-"+locationValueId;
			    		  if(activityConductedMap.get(key) != null){
			    			  if(VO.getCondctedActiesCount() == null)
			    				  VO.setCondctedActiesCount(activityConductedMap.get(key));//(VO.getInviteeCnt()+eventIviteeMap.get(key)); 
			    			  else
			    				  VO.setCondctedActiesCount(VO.getCondctedActiesCount()+activityConductedMap.get(key));
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
				   if(commonMethodsUtilService.isListOrSetValid(userVO.getLocationValuesSet())){
					    userVO.setNotCondctedActiesCount(userVO.getTotalActvtiesCount() - userVO.getCondctedActiesCount());
					    userVO.setNotConductedPerc(calculatePercantage(userVO.getNotCondctedActiesCount(), userVO.getTotalActvtiesCount()));
					    userVO.setConductedPerc(calculatePercantage(userVO.getCondctedActiesCount(), userVO.getTotalActvtiesCount()));
				   }
			   }
		   }
	 
	  if(childActivityMembersMap != null && childActivityMembersMap.size() > 0){
		  resultList.addAll(childActivityMembersMap.values());
	  }
	  
	  if(resultList != null && resultList.size() > 0)
	  {
		  Collections.sort(resultList, eventConductedCuntDesc);
	  }
	} catch (Exception e) {
		 LOG.error("Error occured at getSelectedChildMembersForActivities() in setAttendedDataToMap class",e);
	}
	 return resultList;
 }
 
 @SuppressWarnings("unused")
 public List<UserTypeVO> getSelectedChildMembersForMultiLocationMeetings(Long parentActivityMemberId,List<Long> childUserTypeIds,String reportType,Long stateId,
		 				Long partyMeetingMainTypeId,Long partyMeetingLevelId,Long meetingGrpId,String fromDateStr,String toDateStr){
 	 List<UserTypeVO> resultList = new ArrayList<UserTypeVO>();
 	 Map<String,Long> eventIviteeMap = new HashMap<String, Long>(0);
 	 Map<String,Long> eventInviteeAttendedMap = new HashMap<String, Long>(0);
 	 Map<String,Long> eventAttendedMap = new HashMap<String, Long>(0);
 	
 	 try {
 		 
 			 Map<Long,Set<Long>> locationLevelIdsMap=null;
 			  Map<String,String>     nameForLocationMap=null;
 			  ActivityMemberVO activityMemberVO=null;
 			  Map<Long,UserTypeVO> childActivityMembersMap=null;
 			  Date fromDate = null;
 			  Date toDate = null;
 			  SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
 			  if(fromDateStr != null && toDateStr != null){
 				  fromDate = sdf.parse(fromDateStr);
 				  toDate = sdf.parse(toDateStr);
 			  }
 			  
 			  //List<Object[]> hasSpecilaActivitiesList = activityConductedInfoDAO.getTotalCountsForScopeIds(eventIds,null,null,null);
 			 Map<String,Long> activityConductedMap = new HashMap<String, Long>(0);
 			 Map<String,Long> totalActiviteesMap = new HashMap<String, Long>(0);
 			 Map<String,List<Long>> totalAttendedMembersMap = new LinkedHashMap<String, List<Long>>(0);
 			Map<String,List<Long>> totalInvitedMembersMap = new LinkedHashMap<String, List<Long>>(0);
 			 
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
 					// if(searchType != null && searchType.equalsIgnoreCase("events")){
 			if(locationLevelIdsMap != null && locationLevelIdsMap.size() > 0){
 		    	 for(Entry<Long,Set<Long>> entry:locationLevelIdsMap.entrySet()){
 		    		 
 		    		 List<Object[]> rtrnActivitiesObjLst = new ArrayList<Object[]>(0);
 		    		 
 		    		 rtrnActivitiesObjLst = partyMeetingDAO.getTotalMeetingsCounts(entry.getKey(), new ArrayList<Long>(entry.getValue()), stateId, partyMeetingMainTypeId, partyMeetingLevelId, meetingGrpId, fromDate, toDate);
 		    		 /*List<Object[]> rtnActivitiesObjLst = activityLocationInfoDAO.getLocationWiseTotalActivitiesCount(entry.getKey(), new ArrayList<Long>(entry.getValue()), stateId, eventIds);
 		    		 if(commonMethodsUtilService.isListOrSetValid(hasSpecilaActivitiesList)){
 		    			 List<Object[]> rtrActivitiesObjLst = activityConductedInfoDAO.getLocationWiseTotalActivitiesCount(entry.getKey(), new ArrayList<Long>(entry.getValue()), stateId, eventIds);
 		    			 if(commonMethodsUtilService.isListOrSetValid(rtrActivitiesObjLst))
 			    			 rtrnActivitiesObjLst.addAll(rtrActivitiesObjLst);
 		    		 }
 		    		 
 		    		 if(commonMethodsUtilService.isListOrSetValid(rtnActivitiesObjLst))
 		    			 rtrnActivitiesObjLst.addAll(rtnActivitiesObjLst);*/
 		    		 
 		    		 
 		    		 if(rtrnActivitiesObjLst != null && rtrnActivitiesObjLst.size() > 0){
 		    			  for (Object[] param : rtrnActivitiesObjLst) {
 		    				  String locationLevelAndId = entry.getKey()+"-"+commonMethodsUtilService.getStringValueForObject(param[0]);
 		    				  Long count =0L;
 		    				  
 		    				  if(totalActiviteesMap.get(locationLevelAndId) != null)
 		    					  count = totalActiviteesMap.get(locationLevelAndId);
 		    				  totalActiviteesMap.put(locationLevelAndId, count+commonMethodsUtilService.getLongValueForObject(param[2]));
 						}
 		    		  }
 		    	  }
 			    }
 			  
 			  if(locationLevelIdsMap != null && locationLevelIdsMap.size() > 0){
 		    	 for(Entry<Long,Set<Long>> entry:locationLevelIdsMap.entrySet()){
 		    		 List<Object[]> rtrnActivitiesObjLst = new ArrayList<Object[]>(0);
 		    		 
 		    		 rtrnActivitiesObjLst = partyMeetingDAO.getTotalConductedMeetingsCounts(entry.getKey(), new ArrayList<Long>(entry.getValue()), stateId, partyMeetingMainTypeId, partyMeetingLevelId, meetingGrpId, fromDate, toDate);
 		    		/* List<Object[]> rtnActivitiesObjLst = activityLocationInfoDAO.getLocationWiseConductedActivitiesCount(entry.getKey(), new ArrayList<Long>(entry.getValue()), stateId, eventIds);
 		    		 if(commonMethodsUtilService.isListOrSetValid(hasSpecilaActivitiesList)){
 		    			 List<Object[]> rtrActivitiesObjLst = activityConductedInfoDAO.getLocationWiseConductedActivitiesCount(entry.getKey(), new ArrayList<Long>(entry.getValue()), stateId, eventIds);
 		    			 if(commonMethodsUtilService.isListOrSetValid(rtrActivitiesObjLst))
 			    			 rtrnActivitiesObjLst.addAll(rtrActivitiesObjLst);
 		    		 }
 		    		 if(commonMethodsUtilService.isListOrSetValid(rtnActivitiesObjLst))
 		    			 rtrnActivitiesObjLst.addAll(rtnActivitiesObjLst);*/
 		    		
 		    		 
 		    		 if(rtrnActivitiesObjLst != null && rtrnActivitiesObjLst.size() > 0){
 		    			  for (Object[] param : rtrnActivitiesObjLst) {
 		    				  String locationLevelAndId = entry.getKey()+"-"+commonMethodsUtilService.getStringValueForObject(param[0]);
 		    				  Long count =0L;			    				  
 		    				  if(activityConductedMap.get(locationLevelAndId) != null)
 		    					  count = activityConductedMap.get(locationLevelAndId);
 		    				  activityConductedMap.put(locationLevelAndId, count+commonMethodsUtilService.getLongValueForObject(param[1]));
 						}
 		    		  }
 		    	 }
 			  }
 			  
 			 if(locationLevelIdsMap != null && locationLevelIdsMap.size() > 0){
 		    	 for(Entry<Long,Set<Long>> entry:locationLevelIdsMap.entrySet()){
 		    		 
 		    		 List<Object[]> rtrnActivitiesObjLst = partyMeetingDAO.getTotalAttendedMembers(entry.getKey(), new ArrayList<Long>(entry.getValue()), stateId, partyMeetingMainTypeId, partyMeetingLevelId, meetingGrpId, fromDate, toDate);
 		    		
 		    		 if(rtrnActivitiesObjLst != null && rtrnActivitiesObjLst.size() > 0){
 		    			  for (Object[] param : rtrnActivitiesObjLst) {
 		    				  String locationLevelAndId = entry.getKey()+"-"+commonMethodsUtilService.getStringValueForObject(param[0]);
 		    				  List<Long> membersList = new ArrayList<Long>();			    				  
 		    				  if(totalAttendedMembersMap.get(locationLevelAndId) != null)
 		    					 membersList = totalAttendedMembersMap.get(locationLevelAndId);
 		    				 membersList.add(commonMethodsUtilService.getLongValueForObject(param[1]));
 		    				 totalAttendedMembersMap.put(locationLevelAndId, membersList);
 						}
 		    		  }
 		    	 }
 			  }
 			 
 			if(locationLevelIdsMap != null && locationLevelIdsMap.size() > 0){
		    	 for(Entry<Long,Set<Long>> entry:locationLevelIdsMap.entrySet()){
		    		 
		    		 List<Object[]> rtrnActivitiesObjLst = partyMeetingDAO.getTotalInvitedMembers(entry.getKey(), new ArrayList<Long>(entry.getValue()), stateId, partyMeetingMainTypeId, partyMeetingLevelId, meetingGrpId, fromDate, toDate);
		    		
		    		 if(rtrnActivitiesObjLst != null && rtrnActivitiesObjLst.size() > 0){
		    			  for (Object[] param : rtrnActivitiesObjLst) {
		    				  String locationLevelAndId = entry.getKey()+"-"+commonMethodsUtilService.getStringValueForObject(param[0]);
		    				  List<Long> membersList = new ArrayList<Long>();			    				  
		    				  if(totalInvitedMembersMap.get(locationLevelAndId) != null)
		    					 membersList = totalInvitedMembersMap.get(locationLevelAndId);
		    				 membersList.add(commonMethodsUtilService.getLongValueForObject(param[1]));
		    				 totalInvitedMembersMap.put(locationLevelAndId, membersList);
						}
		    		  }
		    	 }
			  }
 			  
 		     //setting Total Meetings count
 		    if(childActivityMembersMap != null && childActivityMembersMap.size() > 0){
 		    	for(UserTypeVO VO:childActivityMembersMap.values()){
 		    		  for(Long locationValueId:VO.getLocationValuesSet()){
 			    		  String key = VO.getLocationLevelId()+"-"+locationValueId;
 			    		  if(totalActiviteesMap.get(key) != null){
 			    			  if(VO.getTotalMeetingCnt() == null)
 			    				  VO.setTotalMeetingCnt(totalActiviteesMap.get(key)); 
 			    			  else
 			    				  VO.setTotalMeetingCnt(VO.getTotalMeetingCnt()+totalActiviteesMap.get(key));
 			    		  }
 			    	  }
 		    	}
 		    }
 		    
 		   //setting Conducted count
 		   if(childActivityMembersMap != null && childActivityMembersMap.size() > 0){
 		    	for(UserTypeVO VO:childActivityMembersMap.values()){
 		    		  for(Long locationValueId:VO.getLocationValuesSet()){
 			    		  String key = VO.getLocationLevelId()+"-"+locationValueId;
 			    		  if(activityConductedMap.get(key) != null){
 			    			  if(VO.getConductedMeetingCnt() == null)
 			    				  VO.setConductedMeetingCnt(activityConductedMap.get(key));//(VO.getInviteeCnt()+eventIviteeMap.get(key)); 
 			    			  else
 			    				  VO.setConductedMeetingCnt(VO.getConductedMeetingCnt()+activityConductedMap.get(key));
 			    		  }
 			    	  }
 		    	}
 		    }
 		   
 		   //Setting Not Conducted Count
 		  if(childActivityMembersMap != null && childActivityMembersMap.size() > 0){
		    	for(UserTypeVO VO:childActivityMembersMap.values()){
		    		 VO.setNotConductedMeetingCnt(VO.getTotalMeetingCnt() - VO.getConductedMeetingCnt());
		    	}
		    }

 		 //setting Attended Members List
		    if(childActivityMembersMap != null && childActivityMembersMap.size() > 0){
		    	for(UserTypeVO VO:childActivityMembersMap.values()){
		    		  for(Long locationValueId:VO.getLocationValuesSet()){
			    		  String key = VO.getLocationLevelId()+"-"+locationValueId;
			    		  if(totalAttendedMembersMap.get(key) != null){
			    			  VO.getAttndMembersList().addAll(totalAttendedMembersMap.get(key));
			    		  }
			    	  }
		    	}
		    }
		    
		    //setting Invited Members List
		    if(childActivityMembersMap != null && childActivityMembersMap.size() > 0){
		    	for(UserTypeVO VO:childActivityMembersMap.values()){
		    		  for(Long locationValueId:VO.getLocationValuesSet()){
			    		  String key = VO.getLocationLevelId()+"-"+locationValueId;
			    		  if(totalInvitedMembersMap.get(key) != null){
			    			  VO.getInvitedMembrsList().addAll(totalInvitedMembersMap.get(key)); 
			    		  }
			    	  }
		    	}
		    }
		    
		  //setting Invitee Attended Members List
		    if(childActivityMembersMap != null && childActivityMembersMap.size() > 0){
		    	for(UserTypeVO VO:childActivityMembersMap.values()){
		    		if(VO.getAttndMembersList() != null && !VO.getAttndMembersList().isEmpty() && VO.getInvitedMembrsList() != null && !VO.getInvitedMembrsList().isEmpty()){
		    			for (Long inviteeCadreId : VO.getInvitedMembrsList()) {
							if(VO.getAttndMembersList().contains(inviteeCadreId))
								VO.getInvitAttndMbrsList().add(inviteeCadreId);
						}
		    		}
		    		VO.setTotalCount((long) VO.getAttndMembersList().size());
		    		VO.setInviteeAttendedCnt((long) VO.getInvitAttndMbrsList().size());
		    	}
		    }
		    
		    // calculating invitee Attended percentage
	 		   if(childActivityMembersMap != null && childActivityMembersMap.size() > 0){
	 			   for(UserTypeVO userVO:childActivityMembersMap.values()){
	 				   if(commonMethodsUtilService.isListOrSetValid(userVO.getLocationValuesSet())){
	 					   userVO.setInviteeAttendedCntPer(calculatePercantage(userVO.getInviteeAttendedCnt(), userVO.getTotalCount()));
	 					    /*userVO.setNotCondctedActiesCount(userVO.getTotalActvtiesCount() - userVO.getCondctedActiesCount());
	 					    userVO.setNotConductedPerc(calculatePercantage(userVO.getNotCondctedActiesCount(), userVO.getTotalActvtiesCount()));
	 					    userVO.setConductedPerc(calculatePercantage(userVO.getCondctedActiesCount(), userVO.getTotalActvtiesCount()));*/
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
 		   /*if(childActivityMembersMap != null && childActivityMembersMap.size() > 0){
 			   for(UserTypeVO userVO:childActivityMembersMap.values()){
 				   if(commonMethodsUtilService.isListOrSetValid(userVO.getLocationValuesSet())){
 					    userVO.setNotCondctedActiesCount(userVO.getTotalActvtiesCount() - userVO.getCondctedActiesCount());
 					    userVO.setNotConductedPerc(calculatePercantage(userVO.getNotCondctedActiesCount(), userVO.getTotalActvtiesCount()));
 					    userVO.setConductedPerc(calculatePercantage(userVO.getCondctedActiesCount(), userVO.getTotalActvtiesCount()));
 				   }
 			   }
 		   }*/
 	 
 	  if(childActivityMembersMap != null && childActivityMembersMap.size() > 0){
 		  resultList.addAll(childActivityMembersMap.values());
 	  }
 	  
 	  if(resultList != null && resultList.size() > 0)
 	  {
 		  Collections.sort(resultList, eventInviteeAttendedPercDesc);
 	  }
 	} catch (Exception e) {
 		 LOG.error("Error occured at getSelectedChildMembersForActivities() in setAttendedDataToMap class",e);
 	}
 	 return resultList;
  }
 
 @SuppressWarnings("unused")
 public List<PartyMeetingVO> getLocationWiseSelectedChildMembersForMultiLocationMeetings(Long locationLevelId,List<Long> locationValues,Long stateId,
		 			Long partyMeetingMainTypeId,Long partyMeetingLevelId,Long meetingGrpId,String fromDateStr,String toDateStr){
	 List<PartyMeetingVO> returnList = new ArrayList<PartyMeetingVO>();
	 try {
		Map<Long,PartyMeetingVO> locationMap = new LinkedHashMap<Long, PartyMeetingVO>();
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		Date fromDate = null;
		Date toDate = null;
		if(fromDateStr != null && toDateStr != null){
			fromDate = sdf.parse(fromDateStr);
			toDate = sdf.parse(toDateStr);
		}
		
		/*List<Object[]> list = null;
		if(locationLevelId != null && locationLevelId.longValue()==IConstants.STATE_LEVEl_ACCESS_ID && locationValues != null && !locationValues.isEmpty())
			list = stateDAO.getStatesForList(locationValues);
		else if(locationLevelId != null && locationLevelId.longValue()==IConstants.DISTRICT_LEVEl_ACCESS_ID && locationValues != null && !locationValues.isEmpty())
			list = districtDAO.getDistrictDetailsByDistrictIds(locationValues);
		else if(locationLevelId != null && locationLevelId.longValue()==IConstants.PARLIAMENT_LEVEl_ACCESS_ID && locationValues != null && !locationValues.isEmpty())
			list = constituencyDAO.getConstituencyNameByConstituencyIdsList(locationValues);
		else if(locationLevelId != null && locationLevelId.longValue()==IConstants.ASSEMBLY_LEVEl_ACCESS_ID && locationValues != null && !locationValues.isEmpty())
			list = constituencyDAO.getConstituencyNameByConstituencyIdsList(locationValues);
		else if(locationLevelId != null && locationLevelId.longValue()==IConstants.MANDAL_LEVEl_ID && locationValues != null && !locationValues.isEmpty())
			list = tehsilDAO.getTehsilNameByTehsilIdsList(locationValues);
		else if(locationLevelId != null && locationLevelId.longValue()==IConstants.MUNCIPALITY_LEVEl_ID && locationValues != null && !locationValues.isEmpty()) //  town/division
			list = localElectionBodyDAO.getLocalElectionBodyNames(locationValues);
		else if(locationLevelId != null && locationLevelId.longValue()==IConstants.VILLAGE_LEVEl_ID && locationValues != null && !locationValues.isEmpty())
			list = panchayatDAO.getPanchayatsByPanchayatIdsList(locationValues);
		else if(locationLevelId != null && locationLevelId.longValue()==IConstants.WARD_LEVEl_ID && locationValues != null && !locationValues.isEmpty())
			list = constituencyDAO.getConstituencyNameByConstituencyIdsList(locationValues);
		
		if(list != null && !list.isEmpty()){
			for (Object[] obj : list) {
				PartyMeetingVO vo = new PartyMeetingVO();
				vo.setId(Long.valueOf(obj[0] != null ? obj[0].toString():"0"));
				vo.setName(obj[1] != null ? obj[1].toString():"");
				locationMap.put(vo.getId(), vo);
			}
		}*/
		
		List<Object[]> totalMeetings = partyMeetingDAO.getLocationWiseTotalMeetingsCounts(locationLevelId, locationValues, stateId, partyMeetingMainTypeId, partyMeetingLevelId, meetingGrpId, fromDate, toDate);
		if(totalMeetings != null && !totalMeetings.isEmpty()){
			for (Object[] obj : totalMeetings) {
				Long id = Long.valueOf(obj[0] != null ? obj[0].toString():"0");
				PartyMeetingVO vo = locationMap.get(id);
				if(vo != null)
					vo.setCount(Long.valueOf(obj[2] != null ? obj[2].toString():"0"));
				else{
					vo = new PartyMeetingVO();
					vo.setId(Long.valueOf(obj[0] != null ? obj[0].toString():"0"));
					vo.setName(obj[1] != null ? obj[1].toString():"");
					vo.setCount(Long.valueOf(obj[2] != null ? obj[2].toString():"0"));
					locationMap.put(vo.getId(), vo);
				}
			}
		}
		
		List<Object[]> conductedMeetings = partyMeetingDAO.getLocationWiseTotalConductedMeetingsCounts(locationLevelId, locationValues, stateId, partyMeetingMainTypeId, partyMeetingLevelId, meetingGrpId, fromDate, toDate);
		if(conductedMeetings != null && !conductedMeetings.isEmpty()){
			for (Object[] obj : conductedMeetings) {
				Long id = Long.valueOf(obj[0] != null ? obj[0].toString():"0");
				PartyMeetingVO vo = locationMap.get(id);
				if(vo != null)
					vo.setConductedCount(Long.valueOf(obj[1] != null ? obj[1].toString():"0"));
				vo.setNotConductedCount(vo.getCount() - vo.getConductedCount());
			}
		}
		
		List<Object[]> attendedMembers = partyMeetingDAO.getLocationWiseTotalAttendedMembers(locationLevelId, locationValues, stateId, partyMeetingMainTypeId, partyMeetingLevelId, meetingGrpId, fromDate, toDate);
		if(attendedMembers != null && !attendedMembers.isEmpty()){
			for (Object[] obj : attendedMembers) {
				Long id = Long.valueOf(obj[0] != null ? obj[0].toString():"0");
				PartyMeetingVO vo = locationMap.get(id);
				if(vo != null)
					vo.getAttendedList().add(Long.valueOf(obj[1] != null ? obj[1].toString():"0"));
			}
		}
		
		List<Object[]> invitedMembers = partyMeetingDAO.getLocationWiseTotalInvitedMembers(locationLevelId, locationValues, stateId, partyMeetingMainTypeId, partyMeetingLevelId, meetingGrpId, fromDate, toDate);
		if(invitedMembers != null && !invitedMembers.isEmpty()){
			for (Object[] obj : invitedMembers) {
				Long id = Long.valueOf(obj[0] != null ? obj[0].toString():"0");
				PartyMeetingVO vo = locationMap.get(id);
				if(vo != null)
					vo.getInvitedList().add(Long.valueOf(obj[1] != null ? obj[1].toString():"0"));
			}
		}
		
		if(locationMap != null && locationMap.size() > 0){
			 for(Entry<Long,PartyMeetingVO> entry:locationMap.entrySet()){
				PartyMeetingVO vo = entry.getValue();
				if(vo.getAttendedList() != null && !vo.getAttendedList().isEmpty()){
					if(vo.getInvitedList() != null && !vo.getInvitedList().isEmpty()){
						for (Long id : vo.getInvitedList()) {
							if(vo.getAttendedList().contains(id)){
								vo.getInviteeAttendedList().add(id);
							}
							else{
								vo.getAbsentList().add(id);
							}
						}
						for (Long id : vo.getAttendedList()) {
							if(!vo.getInvitedList().contains(id)){
								vo.getNonInviteeAttendedList().add(id);
							}
						}
					}
					else{
						vo.setNonInviteeAttendedList(vo.getAttendedList());
					}
				}
				
				vo.setAttendedCount((long) vo.getAttendedList().size());
				vo.setInvitedCount((long) vo.getInvitedList().size());
				vo.setInviteeAttendedCount((long) vo.getInviteeAttendedList().size());
				vo.setAbsentCount((long) vo.getAbsentList().size());
				vo.setNonInviteeCount((long) vo.getNonInviteeAttendedList().size());
			}
		}
		
		if(locationMap != null && locationMap.size() > 0){
			 for(Entry<Long,PartyMeetingVO> entry:locationMap.entrySet()){
				PartyMeetingVO vo = entry.getValue();
				vo.setInviteeAttendedPerc(calculatePercantage(vo.getInviteeAttendedCount(), vo.getInvitedCount()));
				vo.setInviteeAbscentPerc(calculatePercantage(vo.getAbsentCount(), vo.getInvitedCount()));
				vo.setNonInviteePerc(calculatePercantage(vo.getNonInviteeCount(), vo.getAttendedCount()));
			 }
		}
		
		if(locationMap != null)
			returnList = new ArrayList<PartyMeetingVO>(locationMap.values());
		
	} catch (Exception e) {
		LOG.error("Error occured at getLocationWiseSelectedChildMembersForMultiLocationMeetings() in setAttendedDataToMap class",e);
	}
	 return returnList;
 }
 
 public List<Long> getPartyMeetingLevelIdsByAccessLevel(Long accessLevelId,List<Long> levelValues,Long stateId,Long partyMeetingMainTypeId,Long meetingGrpId,String fromDateStr,String toDateStr){
	 List<Long> partyMeetingLevelIds = null;
	 try {
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		Date fromDate = null;
		Date toDate = null;
		if(fromDateStr != null && toDateStr != null){
			fromDate = sdf.parse(fromDateStr);
			toDate = sdf.parse(toDateStr);
		}
		
		partyMeetingLevelIds = partyMeetingDAO.getPartyMeetingLevelIdsByAccessLevel(accessLevelId, levelValues, stateId, partyMeetingMainTypeId, meetingGrpId, fromDate, toDate);
		
	} catch (Exception e) {
		LOG.error("Error occured at getPartyMeetingLevelIdsByAccessLevel()",e);
	}
	 return partyMeetingLevelIds;
 }
 
 public List<UserTypeVO> getSelectedChildMembersForEvnts(Long parentActivityMemberId,List<Long> childUserTypeIds,String reportType,Long stateId,List<Long> eventIds,String searchType){
	 
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
				// if(searchType != null && searchType.equalsIgnoreCase("events")){
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
	
	public EventDetailsVO getEventPoorPerformanceLocation(Long userTypeId,Long stateId,Long activityMemberId,List<Long> eventsIds,String searchType){
		try {
			if(searchType != null && searchType.equalsIgnoreCase("events")){
				return getEventsPoorPerformanceLocation(userTypeId,stateId,activityMemberId,eventsIds,searchType);
			}else  if(searchType != null && searchType.equalsIgnoreCase("All") ||  searchType.equalsIgnoreCase("SingleActivity") ||  searchType.equalsIgnoreCase("activities") || 
					 searchType.equalsIgnoreCase("ScopeId") ){
				 if(!searchType.equalsIgnoreCase("ScopeId")){
					 List<Object[]> levelScopes = activityScopeDAO.getLevelAndScopeIds(eventsIds,"all");
					 if(commonMethodsUtilService.isListOrSetValid(levelScopes)){
						 eventsIds.clear();				 
						 for (Object[] obj : levelScopes) {
							 eventsIds.add(commonMethodsUtilService.getLongValueForObject(obj[1]));
						}
					 }
				 }
				return getActivitiesPoorPerformanceLocation(userTypeId,stateId,activityMemberId,eventsIds,searchType);
			}
		} catch (Exception e) {
			 LOG.error("Error occured at getEventPoorPerformanceLocation() in setAttendedDataToMap class",e);	
		}
		return null;
	}
	
	public EventDetailsVO getActivitiesPoorPerformanceLocation(Long userTypeId,Long stateId,Long activityMemberId,List<Long> eventsId,String searchType){
		
		  EventDetailsVO resultVO = new EventDetailsVO();
		  Map<Long,EventDetailsVO> eventDtlsMap = new HashMap<Long, EventDetailsVO>(0);
		  Map<Long,Set<Long>> accessLevelMap = new HashMap<Long, Set<Long>>(0);
		try {
			 List<Object[]> hasSpecilaActivitiesList = activityConductedInfoDAO.getTotalCountsForScopeIds(eventsId,null,null,null);
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
				    		 
				    		 List<Object[]> rtrnEventInviteeObjList = new ArrayList<Object[]>(0);
				    		 List<Object[]> list1 = activityLocationInfoDAO.getLocationWiseEventInviteedCntBasedOnUserType(entry.getKey(),new ArrayList<Long>(entry.getValue()), stateId, eventsId, "District");
				    		 if(commonMethodsUtilService.isListOrSetValid(hasSpecilaActivitiesList)){
				    			 List<Object[]> list2 = activityConductedInfoDAO.getLocationWiseEventInviteedCntBasedOnUserType(entry.getKey(),new ArrayList<Long>(entry.getValue()), stateId, eventsId, "District");
				    			 if(commonMethodsUtilService.isListOrSetValid(list2))
					    			 rtrnEventInviteeObjList.addAll(list2);
				    		 }
				    		 
				    		 if(commonMethodsUtilService.isListOrSetValid(list1))
				    			 rtrnEventInviteeObjList.addAll(list1);
				    		
				    		 
				    		 setEventInviteeDetailsToMap(rtrnEventInviteeObjList,eventDtlsMap);
				    		 List<Object[]> rtrnEventInviteeAttendedObjList = new ArrayList<Object[]>(0);
				    		 
				    		 List<Object[]> list3 = activityLocationInfoDAO.getLocationWiseEventInviteeAttendedCntBasedOnUserType(entry.getKey(),new ArrayList<Long>(entry.getValue()), stateId, eventsId, "District");
				    		 if(commonMethodsUtilService.isListOrSetValid(hasSpecilaActivitiesList)){
				    			 List<Object[]> list4 = activityConductedInfoDAO.getLocationWiseEventInviteeAttendedCntBasedOnUserType(entry.getKey(),new ArrayList<Long>(entry.getValue()), stateId, eventsId, "District");
				    			 if(commonMethodsUtilService.isListOrSetValid(list4))
					    			 rtrnEventInviteeAttendedObjList.addAll(list4);
				    		 }
				    		 if(commonMethodsUtilService.isListOrSetValid(list3))
				    			 rtrnEventInviteeAttendedObjList.addAll(list3);
				    		 
				    		 
				    		 setEventInviteeAttendedCntToMap(rtrnEventInviteeAttendedObjList,eventDtlsMap);
				    		 caculatingNonInviteeAttendedCnt(eventDtlsMap,searchType);
				    		 resultVO.getDistrictList().addAll(eventDtlsMap.values());
				    		 eventDtlsMap.clear();
				    	 }
				    }
			   }
	          if(userTypeId != null && userTypeId.longValue()==IConstants.STATE_TYPE_USER_ID ||  userTypeId.longValue()==IConstants.GENERAL_SECRETARY_USER_TYPE_ID  || userTypeId.longValue()==IConstants.ORGANIZING_SECRETARY_USER_TYPE_ID || userTypeId.longValue() ==IConstants.SECRETARY_USER_TYPE_ID
				|| userTypeId.longValue() ==IConstants.DISTRICT_PRESIDENT_USER_TYPE_ID || userTypeId.longValue()==IConstants.MP_USER_TYPE_ID || userTypeId.longValue()==IConstants.COUNTRY_TYPE_USER_ID || userTypeId.longValue()==IConstants.INCHARGE_MINISTER_USER_TYPE_ID || userTypeId.longValue()==IConstants.PARLIAMENT_INCHARGE_USER_TYPE_ID){
				    if(accessLevelMap != null && accessLevelMap.size() > 0){
				    	 for(Entry<Long,Set<Long>> entry:accessLevelMap.entrySet()){
				    		 
				    		 List<Object[]> rtrnEventInviteeObjList = new ArrayList<Object[]>(0);
				    		 List<Object[]> list1 = activityLocationInfoDAO.getLocationWiseEventInviteedCntBasedOnUserType(entry.getKey(),new ArrayList<Long>(entry.getValue()), stateId, eventsId, "Constituency");
				    		 if(commonMethodsUtilService.isListOrSetValid(hasSpecilaActivitiesList)){
				    			 List<Object[]> list2 = activityConductedInfoDAO.getLocationWiseEventInviteedCntBasedOnUserType(entry.getKey(),new ArrayList<Long>(entry.getValue()), stateId, eventsId, "Constituency");
				    			 if(commonMethodsUtilService.isListOrSetValid(list2))
					    			 rtrnEventInviteeObjList.addAll(list2);
				    		 }
				    		 if(commonMethodsUtilService.isListOrSetValid(list1))
				    			 rtrnEventInviteeObjList.addAll(list1);
				    		 
				    		 
				    		 setEventInviteeDetailsToMap(rtrnEventInviteeObjList,eventDtlsMap);
				    		 List<Object[]> rtrnEventInviteeAttendedObjList = new ArrayList<Object[]>(0);
				    		 
				    		 List<Object[]> list3 = activityLocationInfoDAO.getLocationWiseEventInviteeAttendedCntBasedOnUserType(entry.getKey(),new ArrayList<Long>(entry.getValue()), stateId, eventsId, "Constituency");
				    		 if(commonMethodsUtilService.isListOrSetValid(hasSpecilaActivitiesList)){
				    			 List<Object[]> list4 = activityConductedInfoDAO.getLocationWiseEventInviteeAttendedCntBasedOnUserType(entry.getKey(),new ArrayList<Long>(entry.getValue()), stateId, eventsId, "Constituency");
				    			 if(commonMethodsUtilService.isListOrSetValid(list4))
					    			 rtrnEventInviteeAttendedObjList.addAll(list4);
				    		 }
				    		 if(commonMethodsUtilService.isListOrSetValid(list3))
				    			 rtrnEventInviteeAttendedObjList.addAll(list3);
				    		
				    		 
				    		 setEventInviteeAttendedCntToMap(rtrnEventInviteeAttendedObjList,eventDtlsMap);
				    		 caculatingNonInviteeAttendedCnt(eventDtlsMap,searchType);
				    		// resultVO.getDistrictList().addAll(eventDtlsMap.values());
				    		 resultVO.getConstituencyList().addAll(eventDtlsMap.values());
				    		 eventDtlsMap.clear();
				    	 
				    	 }
				    }
			   }
	           if(userTypeId != null && userTypeId.longValue()==IConstants.CONSTITUENCY_USER_TYPE_ID  || userTypeId.longValue()==IConstants.ORGANIZING_SECRETARY_USER_TYPE_ID || userTypeId.longValue() ==IConstants.SECRETARY_USER_TYPE_ID
			   || userTypeId.longValue() ==IConstants.DISTRICT_PRESIDENT_USER_TYPE_ID || userTypeId.longValue()==IConstants.MLA_USER_TYPE_ID || userTypeId.longValue()==IConstants.MP_USER_TYPE_ID ||
					   userTypeId.longValue()==IConstants.CONSTITUENCY_INCHARGE_USER_TYPE_ID || userTypeId.longValue()==IConstants.INCHARGE_MINISTER_USER_TYPE_ID || userTypeId.longValue()==IConstants.PARLIAMENT_INCHARGE_USER_TYPE_ID){
						    if(accessLevelMap != null && accessLevelMap.size() > 0){
						    	 for(Entry<Long,Set<Long>> entry:accessLevelMap.entrySet()){
						    		 List<Object[]> rtrnEventMndlInviteeObjList = new ArrayList<Object[]>(0);
						    		 List<Object[]> list1 = activityLocationInfoDAO.getLocationWiseEventInviteedCntBasedOnUserType(entry.getKey(),new ArrayList<Long>(entry.getValue()), stateId, eventsId, "Mandal");
						    		 if(commonMethodsUtilService.isListOrSetValid(hasSpecilaActivitiesList)){
						    			 List<Object[]> list2 = activityConductedInfoDAO.getLocationWiseEventInviteedCntBasedOnUserType(entry.getKey(),new ArrayList<Long>(entry.getValue()), stateId, eventsId, "Mandal");
						    			 if(commonMethodsUtilService.isListOrSetValid(list2))
							    			 rtrnEventMndlInviteeObjList.addAll(list2);
						    		 }
						    		 if(commonMethodsUtilService.isListOrSetValid(list1))
						    			 rtrnEventMndlInviteeObjList.addAll(list1);
						    		
						    		 
						    		 setEventInviteeDetailsToMap(rtrnEventMndlInviteeObjList,eventDtlsMap);
						    		// List<Object[]> rtrnEventMndlAttendedObjList = eventAttendeeDAO.getLocationWiseEventAttendedCntBasedOnUserType(entry.getKey(),new ArrayList<Long>(entry.getValue()), stateId, eventsId, "Mandal");
						    		// setEventAttendedCntToMap(rtrnEventMndlAttendedObjList,eventDtlsMap);
						    		 //List<Object[]> rtrnEventMndlInviteeAttendedObjList = eventAttendeeDAO.getLocationWiseEventInviteeAttendedCntBasedOnUserType(entry.getKey(),new ArrayList<Long>(entry.getValue()), stateId, eventsId, "Mandal");
						    		 List<Object[]> rtrnEventMndlInviteeAttendedObjList = new ArrayList<Object[]>(0);
						    		 list1 = activityLocationInfoDAO.getLocationWiseEventInviteeAttendedCntBasedOnUserType(entry.getKey(),new ArrayList<Long>(entry.getValue()), stateId, eventsId, "Mandal");
						    		 List<Object[]> list2 = null;
						    		 if(commonMethodsUtilService.isListOrSetValid(hasSpecilaActivitiesList)){
							    		 list2 = activityConductedInfoDAO.getLocationWiseEventInviteeAttendedCntBasedOnUserType(entry.getKey(),new ArrayList<Long>(entry.getValue()), stateId, eventsId, "Mandal");
							    		 if(commonMethodsUtilService.isListOrSetValid(list2))
							    			 rtrnEventMndlInviteeAttendedObjList.addAll(list2);
						    		 }
						    		 if(commonMethodsUtilService.isListOrSetValid(list1))
						    			 rtrnEventMndlInviteeAttendedObjList.addAll(list1);
						    		 
						    		 
						    		 setEventInviteeAttendedCntToMap(rtrnEventMndlInviteeAttendedObjList,eventDtlsMap);
						    		 caculatingNonInviteeAttendedCnt(eventDtlsMap,searchType);
						    		 resultVO.getMandalTwnDivisionList().addAll(eventDtlsMap.values());
						    		 eventDtlsMap.clear();
						    		 
						    		// List<Object[]> rtrnEventTwnDivInviteeObjList = eventInviteeDAO.getLocationWiseEventInviteedCntBasedOnUserType(entry.getKey(),new ArrayList<Long>(entry.getValue()), stateId, eventsId, "TownDivision");
						    		 List<Object[]> rtrnEventTwnDivInviteeObjList = new ArrayList<Object[]>(0);
						    		 list1 = activityLocationInfoDAO.getLocationWiseEventInviteedCntBasedOnUserType(entry.getKey(),new ArrayList<Long>(entry.getValue()), stateId, eventsId, "Mandal");
						    		 if(commonMethodsUtilService.isListOrSetValid(hasSpecilaActivitiesList)){
						    			 list2 = activityConductedInfoDAO.getLocationWiseEventInviteedCntBasedOnUserType(entry.getKey(),new ArrayList<Long>(entry.getValue()), stateId, eventsId, "Mandal");
						    			 if(commonMethodsUtilService.isListOrSetValid(list2))
							    			 rtrnEventTwnDivInviteeObjList.addAll(list2);
						    		 }
						    		 if(commonMethodsUtilService.isListOrSetValid(list1))
						    			 rtrnEventTwnDivInviteeObjList.addAll(list1);
						    		
						    		 
						    		 setEventInviteeDetailsToMap(rtrnEventTwnDivInviteeObjList,eventDtlsMap);
						    		 List<Object[]> rtrnEventTwnDivInviteeAttendedObjList = new ArrayList<Object[]>(0);
						    		 list1 = activityLocationInfoDAO.getLocationWiseEventInviteeAttendedCntBasedOnUserType(entry.getKey(),new ArrayList<Long>(entry.getValue()), stateId, eventsId, "TownDivision");
						    		 if(commonMethodsUtilService.isListOrSetValid(hasSpecilaActivitiesList)){
						    			 list2 = activityConductedInfoDAO.getLocationWiseEventInviteeAttendedCntBasedOnUserType(entry.getKey(),new ArrayList<Long>(entry.getValue()), stateId, eventsId, "TownDivision");
						    			 if(commonMethodsUtilService.isListOrSetValid(list2))
							    			 rtrnEventTwnDivInviteeAttendedObjList.addAll(list2);
						    		 }
						    		 if(commonMethodsUtilService.isListOrSetValid(list1))
						    			 rtrnEventTwnDivInviteeAttendedObjList.addAll(list1);
						    		
						    		 
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
						    		//List<Object[]> rtrnEventVllgInviteeObjList = eventInviteeDAO.getLocationWiseEventInviteedCntBasedOnUserType(entry.getKey(),new ArrayList<Long>(entry.getValue()), stateId, eventsId, "Village");
						    		 List<Object[]> rtrnEventVllgInviteeObjList = new ArrayList<Object[]>(0);
						    		 List<Object[]>  list1 = activityLocationInfoDAO.getLocationWiseEventInviteeAttendedCntBasedOnUserType(entry.getKey(),new ArrayList<Long>(entry.getValue()), stateId, eventsId, "Mandal");
							    	if(commonMethodsUtilService.isListOrSetValid(hasSpecilaActivitiesList)){
							    		 List<Object[]>  list2 = activityConductedInfoDAO.getLocationWiseEventInviteeAttendedCntBasedOnUserType(entry.getKey(),new ArrayList<Long>(entry.getValue()), stateId, eventsId, "Mandal");
							    		 if(commonMethodsUtilService.isListOrSetValid(list2))
							    			 rtrnEventVllgInviteeObjList.addAll(list2);
						    		 }
						    		 
						    		 if(commonMethodsUtilService.isListOrSetValid(list1))
						    			 rtrnEventVllgInviteeObjList.addAll(list1);
						    		 
						    		 
						    		 setEventInviteeDetailsToMap(rtrnEventVllgInviteeObjList,eventDtlsMap);
						    		// List<Object[]> rtrnEventVllgAttendedObjList = eventAttendeeDAO.getLocationWiseEventAttendedCntBasedOnUserType(entry.getKey(),new ArrayList<Long>(entry.getValue()), stateId, eventsId, "Village");
						    		// setEventAttendedCntToMap(rtrnEventVllgAttendedObjList,eventDtlsMap);
						    		// List<Object[]> rtrnEventVllgInviteeAttendedObjList = eventAttendeeDAO.getLocationWiseEventInviteeAttendedCntBasedOnUserType(entry.getKey(),new ArrayList<Long>(entry.getValue()), stateId, eventsId, "Village");
						    		 List<Object[]> rtrnEventVllgInviteeAttendedObjList = new ArrayList<Object[]>(0);
						    		 List<Object[]> list2 = null;
						    		 list1 = activityLocationInfoDAO.getLocationWiseEventInviteeAttendedCntBasedOnUserType(entry.getKey(),new ArrayList<Long>(entry.getValue()), stateId, eventsId, "Village");
						    		 if(commonMethodsUtilService.isListOrSetValid(hasSpecilaActivitiesList)){
						    			 list2 = activityConductedInfoDAO.getLocationWiseEventInviteeAttendedCntBasedOnUserType(entry.getKey(),new ArrayList<Long>(entry.getValue()), stateId, eventsId, "Village");
						    			 if(commonMethodsUtilService.isListOrSetValid(list2))
							    			 rtrnEventVllgInviteeAttendedObjList.addAll(list2);
						    		 }
						    		 if(commonMethodsUtilService.isListOrSetValid(list1))
						    			 rtrnEventVllgInviteeAttendedObjList.addAll(list1);
						    		
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
		} catch (Exception e) {
			LOG.error("Error occured at getActivitiesPoorPerformanceLocation() in setAttendedDataToMap class",e);	
		}
		
		return resultVO;
	}
  public EventDetailsVO getEventsPoorPerformanceLocation(Long userTypeId,Long stateId,Long activityMemberId,List<Long> eventsId,String searchType){
	  
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
				    		 caculatingNonInviteeAttendedCnt(eventDtlsMap,searchType);
				    		 resultVO.getDistrictList().addAll(eventDtlsMap.values());
				    		 eventDtlsMap.clear();
				    	 }
				    }
			   }
	          if(userTypeId != null && userTypeId.longValue()==IConstants.STATE_TYPE_USER_ID ||  userTypeId.longValue()==IConstants.GENERAL_SECRETARY_USER_TYPE_ID  || userTypeId.longValue()==IConstants.ORGANIZING_SECRETARY_USER_TYPE_ID || userTypeId.longValue() ==IConstants.SECRETARY_USER_TYPE_ID
				|| userTypeId.longValue() ==IConstants.DISTRICT_PRESIDENT_USER_TYPE_ID || userTypeId.longValue()==IConstants.MP_USER_TYPE_ID || userTypeId.longValue()==IConstants.COUNTRY_TYPE_USER_ID || userTypeId.longValue()==IConstants.INCHARGE_MINISTER_USER_TYPE_ID || userTypeId.longValue()==IConstants.PARLIAMENT_INCHARGE_USER_TYPE_ID){
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
					   userTypeId.longValue()==IConstants.CONSTITUENCY_INCHARGE_USER_TYPE_ID || userTypeId.longValue()==IConstants.INCHARGE_MINISTER_USER_TYPE_ID || userTypeId.longValue()==IConstants.PARLIAMENT_INCHARGE_USER_TYPE_ID){
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
    	 LOG.error("Error occured at getEventsPoorPerformanceLocation() in setAttendedDataToMap class",e);	
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
					 	eventVO.setInviteeAttendedCounPer(calculatePercantage(eventVO.getInviteeAttendedCount(), eventVO.getInviteeCount()));//calculatePercantage(eventVO.getInviteeAttendedCount(), eventVO.getInviteeCount()));
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
		    				    // List<Object[]> rtrnTotalActivityCntObjLst = locationInfoDAO.getTotalActivityLocationWise(getRequiredRegionScopeIds(activityEntry.getValue()), accessLevelValue,locationEntry.getValue());
							     List<Object[]> rtrnTotalActivityCntObjLst = activityLocationInfoDAO.getActivityTotalCntBasedOnUserAccesslevel(locationEntry.getKey(), new ArrayList<Long>(locationEntry.getValue()), stateId, activityEntry.getKey(), new ArrayList<Long>(activityEntry.getValue()),fromDate,toDate);
		    				     List<Object[]> rtrnTotalActivityCntObjectLst = activityConductedInfoDAO.getActivityTotalCntBasedOnUserAccesslevel(locationEntry.getKey(), new ArrayList<Long>(locationEntry.getValue()), stateId, activityEntry.getKey(), new ArrayList<Long>(activityEntry.getValue()),fromDate,toDate);
		    				     if(commonMethodsUtilService.isListOrSetValid(rtrnTotalActivityCntObjectLst)){
		    				    	 if(!commonMethodsUtilService.isListOrSetValid(rtrnTotalActivityCntObjLst))
		    				    		 rtrnTotalActivityCntObjLst = new ArrayList<Object[]>(0);
		    				    		 rtrnTotalActivityCntObjLst.addAll(rtrnTotalActivityCntObjectLst);
		    				     }
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
public static Comparator<UserTypeVO> eventConductedCuntDesc = new Comparator<UserTypeVO>() {
		public int compare(UserTypeVO member2, UserTypeVO member1) {

		Long cunt2 = member2.getCondctedActiesCount();
		Long cunt1 = member1.getCondctedActiesCount();
		//descending order of percantages.
		 return cunt1.compareTo(cunt2);
		}
	};

@Override
public List<EventLocationVO> activitiesLocationWiseData(String fromDate,String toDate,Long locationScopeId,Long activityId) {
	
	List<EventLocationVO> finalList = new ArrayList<EventLocationVO>();
	try{
		Map<Long,EventLocationVO> locationMap = new HashMap<Long,EventLocationVO>();
		List<Object[]> activityScopeList= activityScopeDAO.getActivityScopeIdByActivityAndLevelId(activityId);
		// 0-total,1-conduct,2-locationUId,3-locationName
		Long activityScopeId=0l;
		for (Object[] objects : activityScopeList) {
			activityScopeId = commonMethodsUtilService.getLongValueForObject(objects[0]);
		}
		List<Object[]> questionList =activityQuestionnaireDAO.getActivityQuestionsOptionsDetails(activityScopeId);
		 //0-qId,1-question,2-optionTypId,3-type,4-hasremark 5-activityOptionId,6-opt 7-qusnaryId,8-mandatry
		Map<Long, EventLocationVO> questionaryMap = new HashMap<Long, EventLocationVO>();
		for (Object[] objects : questionList) {
			EventLocationVO questioNVo =questionaryMap.get(commonMethodsUtilService.getLongValueForObject(objects[0]));
			if(questioNVo == null){
				questioNVo = new EventLocationVO();
				EventLocationVO optionVo = new EventLocationVO();
				questioNVo.setQuestionId(commonMethodsUtilService.getLongValueForObject(objects[0]));
				questioNVo.setQuestionName(commonMethodsUtilService.getStringValueForObject(objects[1]));
				questioNVo.setOptionId(commonMethodsUtilService.getLongValueForObject(objects[2]));
				questioNVo.setOptionType(commonMethodsUtilService.getStringValueForObject(objects[3]));
				optionVo.setOptionId(commonMethodsUtilService.getLongValueForObject(objects[5]));
				optionVo.setOptionName(commonMethodsUtilService.getStringValueForObject(objects[6]));
				questioNVo.getOptionList().add(optionVo);
				questionaryMap.put(commonMethodsUtilService.getLongValueForObject(objects[0]), questioNVo);
			}else{
				EventLocationVO optionVo = new EventLocationVO();
				optionVo.setOptionId(commonMethodsUtilService.getLongValueForObject(objects[5]));
				optionVo.setOptionName(commonMethodsUtilService.getStringValueForObject(objects[6]));
				questioNVo.getOptionList().add(optionVo);
			}
			
		}
		
		//totalCount
		List<Object[]> VillageList =activityLocationInfoDAO.getLocationwiseCoductedCount(activityScopeId,locationScopeId,"total");
		
		//conduct
		List<Object[]> VillageConductedList =activityLocationInfoDAO.getLocationwiseCoductedCount(activityScopeId,locationScopeId,"conduct");
		
		
		if(commonMethodsUtilService.isListOrSetValid(VillageList)){
			VillageList.addAll(VillageConductedList);
		}
		
		for (Object[] objects : VillageList) {
			EventLocationVO locationVo =locationMap.get(commonMethodsUtilService.getLongValueForObject(objects[2]));
			if(locationVo == null){
				locationVo = new EventLocationVO();
				locationVo.setLocationId(commonMethodsUtilService.getLongValueForObject(objects[2]));
				locationVo.setLocationName(commonMethodsUtilService.getStringValueForObject(objects[3]));
				locationVo.setTotalCount(commonMethodsUtilService.getLongValueForObject(objects[0]));
				locationVo.setConductedCount(commonMethodsUtilService.getLongValueForObject(objects[1]));
				if(locationScopeId == 4l){
					locationVo.setDistrictId(commonMethodsUtilService.getLongValueForObject(objects[4]));
					locationVo.setDistrictName(commonMethodsUtilService.getStringValueForObject(objects[5]));
					
				}
				locationVo.getQuestionList().addAll(getQuestionTemplate(questionaryMap));//getting template
				locationMap.put(commonMethodsUtilService.getLongValueForObject(objects[2]), locationVo);
			}else{
				locationVo.setTotalCount(commonMethodsUtilService.getLongValueForObject(objects[0])+locationVo.getTotalCount());
				locationVo.setConductedCount(commonMethodsUtilService.getLongValueForObject(objects[1])+locationVo.getConductedCount());
			}
			
		}
		
		List<Object[]> vilageanswerList = activityQuestionAnswerDAO.getCountanswereddetails(activityScopeId, locationScopeId);
		
		//0-qid,1-question,2-optionId,3-optionType,4-optionName 5-optionId,6-count, 7-memcount,8-locationId
		for (Object[] param : vilageanswerList) {
			EventLocationVO Vo = locationMap.get(commonMethodsUtilService.getLongValueForObject(param[8]));
			if(Vo != null){
					if(Vo.getQuestionList() !=null && Vo.getQuestionList().size() >0){
						EventLocationVO mathedVo = getMatchedVo(Vo.getQuestionList(),commonMethodsUtilService.getLongValueForObject(param[0]));
						if(mathedVo != null){
							if(mathedVo.getOptionList() != null && mathedVo.getOptionList().size() >0){
								EventLocationVO mathedOptionVo = getMatchedOPtonVo(mathedVo.getOptionList(),commonMethodsUtilService.getLongValueForObject(param[5]));
								if(mathedOptionVo !=null){
									if(mathedOptionVo.getOptionId().longValue() == 0l && mathedVo.getOptionId()==4l ){
										mathedOptionVo.setCount(mathedOptionVo.getCount()+commonMethodsUtilService.getLongValueForObject(param[7]));
									}else{
										mathedOptionVo.setCount(mathedOptionVo.getCount()+commonMethodsUtilService.getLongValueForObject(param[6]));
									}
								}
							}
						}
					}
				}
		}
		List<Object[]> ivrstatusData= activityLocationInfoDAO.getIvrStatusForLocation(activityScopeId,locationScopeId);
		//0-count,1-status,2-question 3-qid,4-locationId
		for (Object[] objects : ivrstatusData) {
			EventLocationVO locationVo =locationMap.get(commonMethodsUtilService.getLongValueForObject(objects[4]));
			if(locationVo != null){
				EventLocationVO mathedQuestionVo = getMatchedVo(locationVo.getQuestionList(),commonMethodsUtilService.getLongValueForObject(objects[3]));
				if(mathedQuestionVo != null){
					if(mathedQuestionVo.getOptionList() != null && mathedQuestionVo.getOptionList().size() >0){
						Long option =null;
						if(commonMethodsUtilService.getStringValueForObject(objects[1]).trim().equalsIgnoreCase("Y")){
							option=1l;
						}else if(commonMethodsUtilService.getStringValueForObject(objects[1]).trim().equalsIgnoreCase("N")){
							option =2l;
						}
						EventLocationVO mathedOptionVo = getMatchedOPtonVo(mathedQuestionVo.getOptionList(),option);
						if(mathedOptionVo !=null){
							mathedOptionVo.setCount(mathedOptionVo.getCount()+commonMethodsUtilService.getLongValueForObject(objects[0]));
						}
					}
				}else{
					mathedQuestionVo = new EventLocationVO();
					EventLocationVO optionVo1 =new EventLocationVO();
					EventLocationVO optionVo2 =new EventLocationVO();
					mathedQuestionVo.setQuestionId(commonMethodsUtilService.getLongValueForObject(objects[3]));
					mathedQuestionVo.setQuestionName(commonMethodsUtilService.getStringValueForObject(objects[2]));
					optionVo1.setOptionId(1l);
					optionVo1.setOptionName("Yes");
					optionVo2.setOptionId(2l);
					optionVo2.setOptionName("No");
					if(commonMethodsUtilService.getStringValueForObject(objects[1]).trim().equalsIgnoreCase("Y")){
						optionVo1.setCount(commonMethodsUtilService.getLongValueForObject(objects[0]));
					}else if(commonMethodsUtilService.getStringValueForObject(objects[1]).trim().equalsIgnoreCase("N")){
						optionVo2.setCount(commonMethodsUtilService.getLongValueForObject(objects[0]));
					}
					mathedQuestionVo.getOptionList().add(optionVo1);
					mathedQuestionVo.getOptionList().add(optionVo2);
					locationVo.getQuestionList().add(mathedQuestionVo);
				}
			}
		}
 		for (Long lcoationId : locationMap.keySet()) {
			EventLocationVO vo=  locationMap.get(lcoationId);
			if(vo != null && vo.getTotalCount()>0 ){
				vo.setPercentage((double) ((vo.getConductedCount()/vo.getTotalCount())*100));
				for (EventLocationVO questionVO : vo.getQuestionList()) {
					if(questionVO.getQuestionName().trim().contains("Average Public Attended?")){
						for (EventLocationVO optionVo : questionVO.getOptionList()) {
							if(vo.getConductedCount() !=null && vo.getConductedCount()>0){
								optionVo.setPercentage((double) (optionVo.getCount()/vo.getConductedCount()));
							}
						}
					}else if(questionVO.getQuestionName().trim().contains("Average Gramma Sabha Conducted Time (Duration)?")){
						for (EventLocationVO optionVo : questionVO.getOptionList()) {
							if(vo.getConductedCount() !=null && vo.getConductedCount()>0){
								optionVo.setPercentage((double) (optionVo.getCount()/(60*vo.getConductedCount())));
							}
						}
					}
				}
				finalList.add(vo);
			}
		}
		
	}catch(Exception e){
		 LOG.error("Error occured at activitiesLocationWiseData() in setAttendedDataToMap class",e); 
	}
	return finalList;
}
  public List<EventLocationVO> getQuestionTemplate(Map<Long,EventLocationVO> questionMap) {
	  List<EventLocationVO> questionList = new ArrayList<EventLocationVO>();
		try {
			for (Entry<Long, EventLocationVO> questionEntry : questionMap.entrySet()) {
				EventLocationVO questioNVo = new EventLocationVO();
				questioNVo.setQuestionId(questionEntry.getValue().getQuestionId());
				questioNVo.setQuestionName(questionEntry.getValue().getQuestionName());
				questioNVo.setOptionId(questionEntry.getValue().getOptionId());
				questioNVo.setOptionType(questionEntry.getValue().getOptionType());
				if (questionEntry.getValue().getOptionList() != null) {
					for (EventLocationVO VO : questionEntry.getValue().getOptionList()) {
						EventLocationVO optionVo = new EventLocationVO();
						optionVo.setOptionId(VO.getOptionId());
						optionVo.setOptionName(VO.getOptionName());
						questioNVo.getOptionList().add(optionVo);
					}
				}
				questionList.add(questioNVo);
			}
		} catch (Exception e) {
		   LOG.error("Error occured at getQuestionTemplate() in setAttendedDataToMap class",e); 
	   }
	   return questionList;
  }

	private EventLocationVO getMatchedOPtonVo(List<EventLocationVO> optionList, Long optionId) {
		try {
			if (optionList == null || optionList.size() == 0 || optionId == null)
				return null;
			for (EventLocationVO vo : optionList) {
				if (vo.getOptionId().longValue() == optionId.longValue())
					return vo;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;

	}
	public EventLocationVO getMatchedVo(List<EventLocationVO> returnList,Long id){
		try {
			if (returnList == null || returnList.size() == 0 || id == null)
				return null;
			for (EventLocationVO vo : returnList) {
				if (vo.getQuestionId().longValue() == id.longValue())
					return vo;
			}
		}
	  catch(Exception e)
	  {
	    e.printStackTrace();
	  }
	  return null;
	}
}



	  