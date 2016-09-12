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
import java.util.concurrent.CopyOnWriteArrayList;

import org.apache.log4j.Logger;

import com.itgrids.partyanalyst.dao.IActivityMemberAccessLevelDAO;
import com.itgrids.partyanalyst.dao.IPartyMeetingAttendanceDAO;
import com.itgrids.partyanalyst.dao.IPartyMeetingDAO;
import com.itgrids.partyanalyst.dao.IPartyMeetingInviteeDAO;
import com.itgrids.partyanalyst.dao.IPartyMeetingStatusDAO;
import com.itgrids.partyanalyst.dao.IPartyMeetingTypeDAO;
import com.itgrids.partyanalyst.dto.ActivityMemberVO;
import com.itgrids.partyanalyst.dto.CommitteeInputVO;
import com.itgrids.partyanalyst.dto.CoreDashboardCountsVO;
import com.itgrids.partyanalyst.dto.PartyMeetingsDataVO;
import com.itgrids.partyanalyst.dto.PartyMeetingsInputVO;
import com.itgrids.partyanalyst.dto.PartyMeetingsVO;
import com.itgrids.partyanalyst.dto.UserTypeVO;
import com.itgrids.partyanalyst.service.ICoreDashboardGenericService;
import com.itgrids.partyanalyst.service.ICoreDashboardPartyMeetingService;
import com.itgrids.partyanalyst.utils.CommonMethodsUtilService;
import com.itgrids.partyanalyst.utils.IConstants;

public class CoreDashboardPartyMeetingService implements ICoreDashboardPartyMeetingService {

	private final static Logger LOG = Logger.getLogger(CoreDashboardPartyMeetingService.class);
	
	 private IPartyMeetingDAO partyMeetingDAO;
	 private IPartyMeetingStatusDAO partyMeetingStatusDAO;
	 private CommonMethodsUtilService commonMethodsUtilService;
	 private ICoreDashboardGenericService coreDashboardGenericService;
	 private IActivityMemberAccessLevelDAO activityMemberAccessLevelDAO;
	 private IPartyMeetingTypeDAO partyMeetingTypeDAO;
	 private IPartyMeetingInviteeDAO  partyMeetingInviteeDAO;
	 private IPartyMeetingAttendanceDAO partyMeetingAttendanceDAO;
	 
	public void setPartyMeetingDAO(IPartyMeetingDAO partyMeetingDAO) {
		this.partyMeetingDAO = partyMeetingDAO;
	}
	public void setPartyMeetingStatusDAO(
			IPartyMeetingStatusDAO partyMeetingStatusDAO) {
		this.partyMeetingStatusDAO = partyMeetingStatusDAO;
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
   public void setPartyMeetingTypeDAO(IPartyMeetingTypeDAO partyMeetingTypeDAO) {
		this.partyMeetingTypeDAO = partyMeetingTypeDAO;
	}
   
	public void setPartyMeetingInviteeDAO(
			IPartyMeetingInviteeDAO partyMeetingInviteeDAO) {
		this.partyMeetingInviteeDAO = partyMeetingInviteeDAO;
	}
	public void setPartyMeetingAttendanceDAO(
			IPartyMeetingAttendanceDAO partyMeetingAttendanceDAO) {
		this.partyMeetingAttendanceDAO = partyMeetingAttendanceDAO;
	}
/**
 * @param  Long activityMemberId
 * @param String fromDateStr
 * @param String toDateStr
 * @param Long stateId
 * @return PartyMeetingsVO
 * @author Santosh 
 * @Description :This Service Method is used to get Meeting basic count details.. 
 *  @since 9-AUGUST-2016
 */
 public PartyMeetingsVO getPartyMeetingBasicCountDetails(Long activityMemberId,Long stateId,String fromDateStr,String toDateStr,List<Long> partyMeetingTypeValues){
	  
	 PartyMeetingsVO resultVO = new PartyMeetingsVO(); 
	 
	 Map<Long,Set<Long>> locationAccessLevelMap = new HashMap<Long, Set<Long>>(0);
	 Map<String,Long> overAllTotalCountMap = new HashMap<String, Long>(0);
	 Map<String,PartyMeetingsVO> overAllTotalDtlsMap = new HashMap<String, PartyMeetingsVO>(0);
	 Map<String,Long> overAllTotalLevelWiseCountMap = new HashMap<String, Long>(0);
	 Map<Long,PartyMeetingsVO> overAllLevelWiseDtlsMap = new HashMap<Long, PartyMeetingsVO>(0);
	 Map<String,PartyMeetingsVO> mandalTwnDivMap = new HashMap<String, PartyMeetingsVO>(0);
	 Map<String,PartyMeetingsVO> villageWardMap = new HashMap<String, PartyMeetingsVO>(0);
	 Long userAccessLevelId=0l;
     SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
	 Date fromDate=null;
	 Date toDate=null;
	 try{
		 
		if(fromDateStr != null && fromDateStr.trim().length()>0 && toDateStr!= null && toDateStr.trim().length()>0){
			 fromDate = sdf.parse(fromDateStr);
			 toDate = sdf.parse(toDateStr);
		 }
		
	    List<Object[]> rtrnUsrAccssLvlIdAndVlusObjLst=activityMemberAccessLevelDAO.getLocationLevelAndValuesByActivityMembersId(activityMemberId);
	    if(rtrnUsrAccssLvlIdAndVlusObjLst != null && !rtrnUsrAccssLvlIdAndVlusObjLst.isEmpty()){
		   userAccessLevelId = commonMethodsUtilService.getLongValueForObject(rtrnUsrAccssLvlIdAndVlusObjLst.get(0)[0]);
		   for (Object[] param : rtrnUsrAccssLvlIdAndVlusObjLst) {
			Set<Long> locationValuesSet= locationAccessLevelMap.get((Long)param[0]);
			 if(locationValuesSet == null){
				 locationValuesSet = new java.util.HashSet<Long>();
				 locationAccessLevelMap.put((Long)param[0],locationValuesSet);
			 }
			 locationValuesSet.add(param[1] != null ? (Long)param[1]:0l);
		}
	   }
	   // OverAll
	  if(locationAccessLevelMap != null && locationAccessLevelMap.size() > 0){
		  for (Entry<Long,Set<Long>> entry : locationAccessLevelMap.entrySet()) {
			List<Object[]> rtrnObjList = partyMeetingDAO.getPartyMeetingOverAllCountByUserAccessLevel(entry.getKey(),new ArrayList<Long>(entry.getValue()), stateId, fromDate, toDate,partyMeetingTypeValues);// userAccessLevelId,locationValues,
			 if(rtrnObjList != null && rtrnObjList.size() > 0){
				 overAllTotalCountMap.put("overAllTotalCount",0l);
				 for (Object[] param : rtrnObjList) {
					Long count= param[2] != null ? (Long)param[2] :0l;
					overAllTotalCountMap.put("overAllTotalCount", overAllTotalCountMap.get("overAllTotalCount")+count);
				}
			 }
		}
	  }
	 if(locationAccessLevelMap != null && locationAccessLevelMap.size() > 0){
		 for (Entry<Long, Set<Long>> entry : locationAccessLevelMap.entrySet()) {
			 List<Object[]> rtrnList = partyMeetingStatusDAO.getPartyMeetingCountLevelWise(entry.getKey(),new ArrayList<Long>(entry.getValue()), stateId, fromDate, toDate,partyMeetingTypeValues);
			  if(rtrnList != null && rtrnList.size() > 0){
				  for (Object[] param : rtrnList) {
					 PartyMeetingsVO VO = overAllTotalDtlsMap.get("overAllTotalDtls");
					  if(VO == null){
						  VO = new PartyMeetingsVO();
						  overAllTotalDtlsMap.put("overAllTotalDtls",VO);
					  }
					  String status = param[2] != null ? param[2].toString() :"";
					  Long count = param[3] != null ? (Long)param[3]:0l;
					  if(status.equalsIgnoreCase("Y")){
						  VO.setConductedCount(VO.getConductedCount()+count);
					  }else if(status.equalsIgnoreCase("N")){
						  VO.setNotConductedCount(VO.getNotConductedCount()+count);
					  }else if(status.equalsIgnoreCase("M")){
						  VO.setMayBeCount(VO.getMayBeCount()+count); 
					  }
				}
			  }
		}
	 }
	 if(locationAccessLevelMap != null && locationAccessLevelMap.size() > 0){
		 for(Entry<Long,Set<Long>> entry:locationAccessLevelMap.entrySet()){
			 List<Object[]> rtrnObjList =  partyMeetingDAO.getPartyMeetingOverAllCountByUserAccessLevel(entry.getKey(),new ArrayList<Long>(entry.getValue()), stateId, fromDate, toDate,partyMeetingTypeValues);// userAccessLevelId,locationValues,
			
			 overAllTotalLevelWiseCountMap.put("totalStateCount", 0l);
			 setOverAllCountToMap(rtrnObjList,overAllTotalLevelWiseCountMap,"totalStateCount","STATE");
			 overAllTotalLevelWiseCountMap.put("totalDistrictCount", 0l);
			 setOverAllCountToMap(rtrnObjList,overAllTotalLevelWiseCountMap,"totalDistrictCount","DISTRICT");
			 overAllTotalLevelWiseCountMap.put("totalConstituencyCount", 0l);
			 setOverAllCountToMap(rtrnObjList,overAllTotalLevelWiseCountMap,"totalConstituencyCount","CONSTITUENCY");
			 overAllTotalLevelWiseCountMap.put("totalMandalTownDivisionCount", 0l);
			 setOverAllCountToMap(rtrnObjList,overAllTotalLevelWiseCountMap,"totalMandalTownDivisionCount","MandalTownDivision");
			 overAllTotalLevelWiseCountMap.put("totalVillageWardCount", 0l);
			 setOverAllCountToMap(rtrnObjList,overAllTotalLevelWiseCountMap,"totalVillageWardCount","VillageWard");
		 
		 }
	 }
     if(locationAccessLevelMap != null && locationAccessLevelMap.size() > 0){
    	 for(Entry<Long,Set<Long>> entry:locationAccessLevelMap.entrySet()){
    		 List<Object[]> rtrnObjList = partyMeetingStatusDAO.getPartyMeetingCountLevelWise(entry.getKey(), new ArrayList<Long>(entry.getValue()), stateId, fromDate, toDate,partyMeetingTypeValues);
    		 setPartyMeetingLevelWiseDtlsCntToMap(rtrnObjList,overAllLevelWiseDtlsMap);
    	 }
     }
       //Calculate OverAll Details percentage
       PartyMeetingsVO overAllDtlsVO = overAllTotalDtlsMap.get("overAllTotalDtls");
       if(overAllDtlsVO != null){
    	   overAllDtlsVO.setName("OverAllDtls");
           if(overAllTotalCountMap.get("overAllTotalCount") != null){
        	   overAllDtlsVO.setTotalCount(overAllTotalCountMap.get("overAllTotalCount"));
           }
           overAllDtlsVO.setTotalCountPer(calculatePercantage(overAllDtlsVO.getTotalCount(),overAllDtlsVO.getTotalCount()));
           overAllDtlsVO.setConductedCountPer(calculatePercantage(overAllDtlsVO.getConductedCount(),overAllDtlsVO.getTotalCount()));
           overAllDtlsVO.setNotConductedCountPer(calculatePercantage(overAllDtlsVO.getNotConductedCount(), overAllDtlsVO.getTotalCount()));
           overAllDtlsVO.setMayBeCountPer(calculatePercantage(overAllDtlsVO.getMayBeCount(),overAllDtlsVO.getTotalCount()));
       }
     
       //Calculate Party Meeting Level Wise Data 
        setDataToPartyMeetingLevelWise( overAllLevelWiseDtlsMap.get(1l),overAllTotalLevelWiseCountMap,"State","totalStateCount");
        setDataToPartyMeetingLevelWise( overAllLevelWiseDtlsMap.get(2l),overAllTotalLevelWiseCountMap,"District","totalDistrictCount");
        setDataToPartyMeetingLevelWise( overAllLevelWiseDtlsMap.get(3l),overAllTotalLevelWiseCountMap,"Constituency","totalConstituencyCount");
        
      //Merge Mandal Town And Division 
        
	   PartyMeetingsVO mandalVO = overAllLevelWiseDtlsMap.get(4l);
	   PartyMeetingsVO townVO = overAllLevelWiseDtlsMap.get(5l);
	   PartyMeetingsVO divisionVO = overAllLevelWiseDtlsMap.get(6l);
	     
       PartyMeetingsVO manTwnDivVO = new PartyMeetingsVO();
       
       manTwnDivVO.setName("Mandal/Town/Division");
       if(overAllTotalLevelWiseCountMap.get("totalMandalTownDivisionCount") != null){
    	 manTwnDivVO.setTotalCount(overAllTotalLevelWiseCountMap.get("totalMandalTownDivisionCount"));   
       }
      if(mandalVO != null){
    	  mergeRequiredData(manTwnDivVO,mandalVO);  
      }
      if(townVO != null){
    	  mergeRequiredData(manTwnDivVO,townVO);  
      }
      if(divisionVO != null){
    	  mergeRequiredData(manTwnDivVO,divisionVO);  
      }
      //Calculating Percentage 
      calculatePercentages(manTwnDivVO,mandalTwnDivMap,"mandalTwnDiv");
      
      //Merge Village Ward 
       PartyMeetingsVO villageVO = overAllLevelWiseDtlsMap.get(7l);
       PartyMeetingsVO wardVO = overAllLevelWiseDtlsMap.get(8l);
      
       PartyMeetingsVO villageWardVO = new PartyMeetingsVO();
       
       villageWardVO.setName("Village/Ward");
       if(overAllTotalLevelWiseCountMap.get("totalVillageWardCount") != null){
    	   villageWardVO.setTotalCount(overAllTotalLevelWiseCountMap.get("totalVillageWardCount"));
       }
       if(villageVO != null){
     	  mergeRequiredData(villageWardVO,villageVO);  
       }
       if(wardVO != null){
     	  mergeRequiredData(villageWardVO,wardVO);  
       }
       //Calculating Percentage 
       calculatePercentages(villageWardVO,villageWardMap,"villageWard");
       
       
       //Setting Result to final VO By User Access Level
       
       //common for all user
       resultVO.setOverAllVO(overAllTotalDtlsMap.get("overAllTotalDtls"));
	   resultVO.getPartyMettingsVOList().add(villageWardMap.get("villageWard"));
	   resultVO.getPartyMettingsVOList().add(mandalTwnDivMap.get("mandalTwnDiv"));
	 
       if(userAccessLevelId.longValue()==IConstants.COUNTRY_LEVEl_ACCESS_ID || userAccessLevelId.longValue()==IConstants.STATE_LEVEl_ACCESS_ID){
    	   if(overAllLevelWiseDtlsMap.get(3l) != null){
    		   resultVO.getPartyMettingsVOList().add(overAllLevelWiseDtlsMap.get(3l));   
    	   }
    	   if(overAllLevelWiseDtlsMap.get(2l) != null){
    		   resultVO.getPartyMettingsVOList().add(overAllLevelWiseDtlsMap.get(2l));   
    	   }
    	   if(overAllLevelWiseDtlsMap.get(1l) != null){
    	 	   resultVO.getPartyMettingsVOList().add(overAllLevelWiseDtlsMap.get(1l));
    	   }
       }
       if(userAccessLevelId.longValue()==IConstants.DISTRICT_LEVEl_ACCESS_ID){
    	   if(overAllLevelWiseDtlsMap.get(3l) != null){
    		   resultVO.getPartyMettingsVOList().add(overAllLevelWiseDtlsMap.get(3l));   
    	   }
    	   if(overAllLevelWiseDtlsMap.get(2l) != null){
    		   resultVO.getPartyMettingsVOList().add(overAllLevelWiseDtlsMap.get(2l));   
    	   }
       }
       if(userAccessLevelId.longValue()==IConstants.PARLIAMENT_LEVEl_ACCESS_ID || userAccessLevelId.longValue()==IConstants.ASSEMBLY_LEVEl_ACCESS_ID){
    	   if(overAllLevelWiseDtlsMap.get(3l) != null){
    		   resultVO.getPartyMettingsVOList().add(overAllLevelWiseDtlsMap.get(3l));   
    	   }
        }
    }catch(Exception e) {
		 LOG.error("Exception raised at getPartyMeetingBasicCount() method of CoreDashboardPartyMeetingService", e);	
	 }
	 return resultVO; 
  }
  public void setOverAllCountToMap(List<Object[]> returnList,Map<String,Long> totalMap ,String key,String levelType){
	  try{
		  if(returnList != null && returnList.size() > 0){
			  for(Object[] param : returnList) {
				  Long partyMeetingLevelId = commonMethodsUtilService.getLongValueForObject(param[0]);
				  Long  totalMeetingCnt = commonMethodsUtilService.getLongValueForObject(param[2]);
				  if(levelType.equalsIgnoreCase("STATE")){
					  if(partyMeetingLevelId.longValue()== 1l){
						  totalMap.put(key, totalMap.get(key)+totalMeetingCnt); 
					  }
				  }else if(levelType.equalsIgnoreCase("DISTRICT")){
					  if(partyMeetingLevelId.longValue()== 2l){
						  totalMap.put(key, totalMap.get(key)+totalMeetingCnt);	  
					  }
				  }else if(levelType.equalsIgnoreCase("CONSTITUENCY")){
					  if(partyMeetingLevelId.longValue()== 3l){
						  totalMap.put(key, totalMap.get(key)+totalMeetingCnt);
					  }
				  }else if(levelType.equalsIgnoreCase("MandalTownDivision")){
					  if(partyMeetingLevelId.longValue()== 4l || partyMeetingLevelId.longValue()== 5l || partyMeetingLevelId.longValue()== 6l){
						  totalMap.put(key, totalMap.get(key)+totalMeetingCnt);
					  }  
				  }else if(levelType.equalsIgnoreCase("VillageWard")){
					  if(partyMeetingLevelId.longValue()== 7l || partyMeetingLevelId.longValue()== 8l ){
						  totalMap.put(key, totalMap.get(key)+totalMeetingCnt);
					  }  
				  }
				}
		  }  
	  }catch(Exception e){
		  LOG.error("Exception raised at setOverAllCountToMap() method of CoreDashboardPartyMeetingService", e);  
	  }
  }
  public void setPartyMeetingLevelWiseDtlsCntToMap(List<Object[]> returnList,Map<Long,PartyMeetingsVO> paryMeetingLevelMap){
	  try{
		 if(returnList != null && returnList.size() > 0){
			 for (Object[] param : returnList) {
				Long partyMeetingLevelId = commonMethodsUtilService.getLongValueForObject(param[0]);
				String status = commonMethodsUtilService.getStringValueForObject(param[2]);
				Long totalMeetingCount = commonMethodsUtilService.getLongValueForObject(param[3]);
			    PartyMeetingsVO VO = paryMeetingLevelMap.get(partyMeetingLevelId);
			  if(VO == null){
				  VO = new PartyMeetingsVO();
				  paryMeetingLevelMap.put(partyMeetingLevelId, VO);
			  }
			  if(status.equalsIgnoreCase("Y")){
				  VO.setConductedCount(totalMeetingCount) ; 
			  }else if(status.equalsIgnoreCase("N")){
				  VO.setNotConductedCount(totalMeetingCount);
			  }else if(status.equalsIgnoreCase("M")){
				  VO.setMayBeCount(totalMeetingCount);
			  }
			}
		 }
	  }catch(Exception e){
		  LOG.error("Exception raised at setOverAllCountToMap() method of CoreDashboardPartyMeetingService", e);
	  }
  }
 public void  setDataToPartyMeetingLevelWise(PartyMeetingsVO VO, Map<String,Long> overAllTotalLevelWiseCountMap,String levelName,String key){
	 try{
		 if(VO != null){
			 VO.setTotalCount(overAllTotalLevelWiseCountMap.get(key));
			 VO.setName(levelName);
			 VO.setConductedCountPer(calculatePercantage(VO.getConductedCount(),VO.getTotalCount()));
			 VO.setNotConductedCountPer(calculatePercantage(VO.getNotConductedCount(),VO.getTotalCount()));
			 VO.setMayBeCountPer(calculatePercantage(VO.getMayBeCount(), VO.getTotalCount())); 
		 }
	 }catch(Exception e){
		  LOG.error("Exception raised at setDataToPartyMeetingLevelWise() method of CoreDashboardPartyMeetingService", e); 
	 }
 }
 public void mergeRequiredData(PartyMeetingsVO resultVO,PartyMeetingsVO VO){
	 try{
		 resultVO.setConductedCount(resultVO.getConductedCount()+VO.getConductedCount());
		 resultVO.setNotConductedCount(resultVO.getNotConductedCount()+VO.getNotConductedCount());
		 resultVO.setMayBeCount(resultVO.getMayBeCount()+VO.getMayBeCount());
	 }catch(Exception e){
		 LOG.error("Exception raised at mergeRequiredData() method of CoreDashboardPartyMeetingService", e); 
	 }
 }
 public void calculatePercentages(PartyMeetingsVO resultVO, Map<String,PartyMeetingsVO> map,String key){
	 try{
		 if(resultVO != null){
			 resultVO.setTotalCountPer(calculatePercantage(resultVO.getTotalCount(),resultVO.getTotalCount()));
			 resultVO.setConductedCountPer(calculatePercantage(resultVO.getConductedCount(),resultVO.getTotalCount()));
			 resultVO.setNotConductedCountPer(calculatePercantage(resultVO.getNotConductedCount(),resultVO.getTotalCount()));
			 resultVO.setMayBeCountPer(calculatePercantage(resultVO.getMayBeCount(),resultVO.getTotalCount()));
			 map.put(key, resultVO);	 
		 }
		 }catch(Exception e){
		 LOG.error("Exception raised at calculatePercentage() method of CoreDashboardPartyMeetingService", e);	 
	 }
 }
	/**
	* @param  Long userId
	* @param  Long userTypeId
	* @param  Long activityMemberId
	* @param String fromDateStr
	* @param String toDateStr
	* @param Long stateId
	* @return  List<List<UserTypeVO>>
	* @author Santosh 
	* @Description :This Service Method is used to get top5 strong or top5 poor members Conducted And Maybe Meeting  count.. 
	*  @since 9-AUGUST-2016
	*/
 public List<List<UserTypeVO>> getUserTypeWiseMeetingCounductedNotCounductedMayBeDetailsCnt(Long userId,Long userTypeId,Long activityMemberId,Long stateId,String fromDateStr,String toDateStr,List<Long> partyMeetingTypeValues){
	
	    List<List<UserTypeVO>> resultList = new ArrayList<List<UserTypeVO>>(0);
	    
	    Map<Long,Long> totalCntMap = new HashMap<Long, Long>(0);
	    Map<String,PartyMeetingsVO> meetingCntDtlsMap = new HashMap<String, PartyMeetingsVO>(0);
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
		     
		     if(locationLevelMap != null && locationLevelMap.size() > 0){
		    	 
		    	 for (Entry<Long, Set<Long>> entry : locationLevelMap.entrySet()) {
		    		 
                   List<Object[]> returnOverAllObjList = partyMeetingDAO.getPartyMeetingOverAllCountLocationWiseByUserAccessLevel(entry.getKey(),new ArrayList<Long>(entry.getValue()), stateId, fromDate, toDate,partyMeetingTypeValues);
                  
                   if(returnOverAllObjList!=null && returnOverAllObjList.size()>0){
                	   for(Object[] obj : returnOverAllObjList){
                		   Long userAccessLevelId = entry.getKey();
                		   Long locationLevelValue = (Long)obj[0];
                		   String key = userAccessLevelId+"-"+locationLevelValue;
                		   
                		   PartyMeetingsVO locationVO = null;
                		   locationVO = meetingCntDtlsMap.get(key);
                		   if(locationVO==null){
                			   locationVO = new PartyMeetingsVO();
                			   locationVO.setTotalMeetingCnt(obj[1]!=null?(Long)obj[1]:0l);
                			   meetingCntDtlsMap.put(key, locationVO);
                		   }
                	   }
                   }
				}
		     }
		     
		    if(locationLevelMap != null && locationLevelMap.size() > 0){
		    	for(Entry<Long,Set<Long>> entry:locationLevelMap.entrySet()){
		    		List<Object[]> returnList = partyMeetingStatusDAO.getPartyMeetingCountLocationWiseByUserAccess(entry.getKey(),new ArrayList<Long>(entry.getValue()), stateId, fromDate, toDate,partyMeetingTypeValues);
		    		calculateMeetingStatusWiseDetailsCnt(entry.getKey(),returnList,meetingCntDtlsMap);
		    	}
		    }
		    
		    
		    if(userTypeMapDtls != null && userTypeMapDtls.size() > 0){
		    	
				  for (Entry<Long, Map<Long, UserTypeVO>> entry : userTypeMapDtls.entrySet()) {
					  
				      Map<Long,UserTypeVO> userTypeMap = entry.getValue();
				      
				      for(UserTypeVO vo:userTypeMap.values()){
				    	  
				    	  for(Long locationValueId:vo.getLocationValuesSet()){
				    		  
				    		  String key = vo.getLocationLevelId()+"-"+locationValueId;
				    		  
				    		  PartyMeetingsVO partyMeetingVO = meetingCntDtlsMap.get(key);
				    		  
				    		  if(partyMeetingVO != null){
				    			  vo.setTotalMeetingCnt(vo.getTotalMeetingCnt() + partyMeetingVO.getTotalMeetingCnt());
					    		  vo.setConductedMeetingCnt(vo.getConductedMeetingCnt()+partyMeetingVO.getConductedCount());
					    		  vo.setNotConductedMeetingCnt(vo.getNotConductedMeetingCnt()+partyMeetingVO.getNotConductedCount());
					    		  vo.setMayBeMeetingCnt(vo.getMayBeMeetingCnt()+partyMeetingVO.getMayBeCount());
				    		  }
				    		  
				    		}
				      }
			}  
			} 
		    
		    //Calculate Percentage
		    if(userTypeMapDtls != null && userTypeMapDtls.size() > 0){
				  for (Entry<Long, Map<Long, UserTypeVO>> entry : userTypeMapDtls.entrySet()) {
				      Map<Long,UserTypeVO> userTypeMap = entry.getValue();
				      for(UserTypeVO vo:userTypeMap.values()){
				    		  Long totalMeetingCnt = vo.getTotalMeetingCnt();
				    		  if(totalMeetingCnt != null){
				    			  vo.setConductedAndMayBeMeetingPer(calculatePercantage(vo.getConductedMeetingCnt(),totalMeetingCnt));  
				    		  }
				     		}
				      }
			} 
		    
		    //merging secreteries and general secrerteries.
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
					Collections.sort(memberList, meetingCountPercDesc);
				}
			}
	 }catch(Exception e) {
	  LOG.error("Exception raised at getUserTypeWiseMeetingCounductedNotCounductedMayBeDetailsCnt() method of CoreDashboardPartyMeetingService", e);
	}
	return resultList; 
 }
/* public void calculateTotalCntByAccessLevelWise(Long accessLevelId,List<Object[]> returnList,Map<Long,Long> totalCntMap){
	 try{
		if(returnList != null && returnList.size() > 0){
			for(Object[] obj: returnList) {
				totalCntMap.put(accessLevelId,totalCntMap.get(accessLevelId)+commonMethodsUtilService.getLongValueForObject(obj[1]));
			}
		}
	 }catch(Exception e){
		LOG.error("Exception raised at calculateTotalCntByAccessLevelWise() method of CoreDashboardPartyMeetingService", e);
	 }
 }*/
 public void calculateMeetingStatusWiseDetailsCnt(Long userAccessLevelId,List<Object[]> returnList,Map<String,PartyMeetingsVO> meetingCntDtlsMap){
	 try{
		if(returnList != null && returnList.size() > 0){
			for (Object[] obj : returnList) {
				Long locationLevelValue = commonMethodsUtilService.getLongValueForObject(obj[0]);
				String meetingStatus = commonMethodsUtilService.getStringValueForObject(obj[1]);
				Long meetingCnt = commonMethodsUtilService.getLongValueForObject(obj[2]);
				String key = userAccessLevelId+"-"+locationLevelValue;
				PartyMeetingsVO meetingsVO = meetingCntDtlsMap.get(key);
				  if(meetingsVO == null){
					  meetingsVO = new PartyMeetingsVO();
					  meetingCntDtlsMap.put(key, meetingsVO);
				  }
				  meetingsVO = meetingCntDtlsMap.get(key);
				  if(meetingStatus.equalsIgnoreCase("Y")){
					  meetingsVO.setConductedCount(meetingCnt);
				  }else if(meetingStatus.equalsIgnoreCase("N")){
					  meetingsVO.setNotConductedCount(meetingCnt);
				  }else if(meetingStatus.equalsIgnoreCase("M")){
					 meetingsVO.setMayBeCount(meetingCnt);
				  }
			}
		}
	 }catch(Exception e) {
			LOG.error("Exception raised at calculateMeetingStatusWiseDetailsCnt() method of CoreDashboardPartyMeetingService", e);
	}
 }
	public static Comparator<UserTypeVO> meetingCountPercDesc = new Comparator<UserTypeVO>() {
	public int compare(UserTypeVO member2, UserTypeVO member1) {

	Double perc2 = member2.getConductedAndMayBeMeetingPer();
	Double perc1 = member1.getConductedAndMayBeMeetingPer();
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
	
   public List<PartyMeetingsVO> getPartyMeetingTypeByPartyMeetingMainType(Long partyMeetingMainTypeId){
	  
	   List<PartyMeetingsVO> resultList = new ArrayList<PartyMeetingsVO>(0);
	   
	   try{
		List<Object[]>   rtrnObjList = partyMeetingTypeDAO.getPartyMeetingTypeByPartyMeetingMainType(partyMeetingMainTypeId);
		  if(rtrnObjList != null && rtrnObjList.size() > 0){
			  for(Object[] param:rtrnObjList){
				  PartyMeetingsVO vo = new PartyMeetingsVO();
				  vo.setId(commonMethodsUtilService.getLongValueForObject(param[0]));
				  vo.setName(commonMethodsUtilService.getStringValueForObject(param[1]));
				  resultList.add(vo);
			  }
			  
		  }
	   }catch(Exception e){
		   LOG.error("Exception raised at getPartyMeetingTypeByPartyMeetingMainType() method of CoreDashboardPartyMeetingService", e);   
	   }
	   return resultList;
   } 
	
	/**
	  * @author <a href="mailto:sreedhar.itgrids.hyd@gmail.com">SREEDHAR</a>
	  *  This Service Method is used to get the selected child usertype activity memberids and its meeting counts for a parent usertype activity member id. 
	  *  @since 07-SEPTEMBER-2016
	  */
  public List<UserTypeVO> getSelectedChildUserTypeMembersWithMeetingsCount(Long parentActivityMemberId,Long childUserTypeId,String state,String startDateString,String endDateString,List<Long> partyMeetingTypeIds){
	    List<UserTypeVO> activityMembersList = null;
	   try{
		   
		    //calling generic method.
		    ActivityMemberVO activityMemberVO = coreDashboardGenericService.getSelectedChildUserTypeMembers(parentActivityMemberId,childUserTypeId);
		    Map<Long,UserTypeVO> childActivityMembersMap = activityMemberVO.getActivityMembersMap();
		    Map<Long,Set<Long>> locationLevelIdsMap = activityMemberVO.getLocationLevelIdsMap();
		   
		     //Creating Business Object.
		     CommitteeInputVO meetingBO = new CommitteeInputVO();
		     Long stateId = coreDashboardGenericService.getStateIdByState(state);
		     meetingBO.setStateId(stateId);
		     List<Date>  datesList = coreDashboardGenericService.getDates(startDateString, endDateString, new SimpleDateFormat("dd/MM/yyyy"));
		     meetingBO.setStartDate(datesList.get(0));
		     meetingBO.setEndDate(datesList.get(1));
		     meetingBO.setPartyMeetingTypeIds(partyMeetingTypeIds);
		     
		     //getting counts.
		     Map<String,CoreDashboardCountsVO> locationLevelCountsMap = coreDashboardGenericService.getMeetingsCountByLocationLevelIdAndLevelValues(locationLevelIdsMap,meetingBO);
		     Map<String,String>     nameForLocationMap  = coreDashboardGenericService.getLocationNamesByLocationIds(locationLevelIdsMap);
		     
		     activityMembersList = setMeetingsCountsToActivityMembers(childActivityMembersMap,"counts",locationLevelCountsMap,nameForLocationMap);
		     if(activityMembersList!=null && activityMembersList.size()>0){
		    	 setMeetingsCountsToActivityMembers(childActivityMembersMap,"percanatge",null,null);
		    	 //sorting in descending order of completed percantages.
		    	 Collections.sort(activityMembersList,ActivityMemberConductedCountPercDesc);
		     }
		     
	   }catch(Exception e){
		   LOG.error("exception occurred in getSelectedChildUserTypeMembersWithMeetingsCount()", e);
	   }
	   return activityMembersList;
  }

  public List<UserTypeVO> setMeetingsCountsToActivityMembers(Map<Long,UserTypeVO> childActivityMembersMap,String type,Map<String,CoreDashboardCountsVO> countForLocationMap,Map<String,String> nameForLocationMap){
	   List<UserTypeVO> activityMembersList = null;
	   try{
			
		   if(childActivityMembersMap != null && childActivityMembersMap.size() > 0){
			   activityMembersList = new ArrayList<UserTypeVO>();
			   
			   for(Long activityMemberId:childActivityMembersMap.keySet()){ 
			     
				   UserTypeVO memberVO = childActivityMembersMap.get(activityMemberId);
				   
				   if(type.equalsIgnoreCase("counts")){
					   if(memberVO.getLocationValuesSet() != null && memberVO.getLocationValuesSet().size()>0){
						   for(Long locationValue : memberVO.getLocationValuesSet()){
							   String key = memberVO.getLocationLevelId()+"_"+locationValue;
							   
							   //setting count to a location.
							   CoreDashboardCountsVO countVO = countForLocationMap.get(key);
							   if(countVO != null)
							   {
								   memberVO.setTotalMeetingCnt(memberVO.getTotalMeetingCnt() + countVO.getTotalCount());
								   memberVO.setConductedMeetingCnt(memberVO.getConductedMeetingCnt()+countVO.getConductedCount());
								   memberVO.setNotConductedMeetingCnt(memberVO.getNotConductedMeetingCnt()+countVO.getNotConductedCount());
								   memberVO.setMayBeMeetingCnt(memberVO.getMayBeMeetingCnt()+countVO.getMayBeCount());
							   }
							   //setting name to a location.
							   if(nameForLocationMap!=null && nameForLocationMap.size()>0){
								   if(memberVO.getLocationName() == null || memberVO.getLocationName().isEmpty()){
									   memberVO.setLocationName(nameForLocationMap.get(key));
								   }else{
									   memberVO.setLocationName( memberVO.getLocationName()+","+ nameForLocationMap.get(key) );  
								   }
							   }
						   }
					   } 
				   }else if(type.equalsIgnoreCase("percanatge")){
					   if(memberVO.getTotalMeetingCnt()!=null && memberVO.getTotalMeetingCnt() > 0l){
						   memberVO.setConductedMeetingPerc( coreDashboardGenericService.caclPercantage(memberVO.getConductedMeetingCnt(),memberVO.getTotalMeetingCnt()) );
						   memberVO.setNotConductedMeetingPerc( coreDashboardGenericService.caclPercantage(memberVO.getNotConductedMeetingCnt(),memberVO.getTotalMeetingCnt()) );
						   memberVO.setMayBeMeetingPerc( coreDashboardGenericService.caclPercantage(memberVO.getMayBeMeetingCnt(),memberVO.getTotalMeetingCnt()) );
					   }
	                }
		     }
			   activityMembersList.addAll(childActivityMembersMap.values());
      }
		   
	   }catch(Exception e){
		   LOG.error("exception occurred in setMeetingsCountsToActivityMembers()", e);
	   }
	   return activityMembersList;
  }
  
  public static Comparator<UserTypeVO> ActivityMemberConductedCountPercDesc = new Comparator<UserTypeVO>() {
	     public int compare(UserTypeVO member2, UserTypeVO member1) {

	        Double perc2 = member2.getConductedMeetingPerc();
	        Double perc1 = member1.getConductedMeetingPerc();
	        //descending order of percantages.
	         return perc1.compareTo(perc2);
	    }
  }; 

     /**
	  * @author <a href="mailto:sreedhar.itgrids.hyd@gmail.com">SREEDHAR</a>
	  *  This Service Method is used to get the direct child usertype activity memberids and its meetings counts for a parent usertype activity member id. 
	  *  @since 07-SEPTEMBER-2016
	  */
  public List<UserTypeVO> getDirectChildActivityMemberMeetingsDetails(Long activityMemberId,Long userTypeId,String state,String startDateString,String endDateString,List<Long> partyMeetingTypeIds){
	    List<UserTypeVO> activityMembersList = null;
	   try{
		   
		   //calling generic method.
		    ActivityMemberVO activityMemberVO = coreDashboardGenericService.getDirectChildActivityMemberCommitteeDetails(activityMemberId,userTypeId);
		    Map<Long,UserTypeVO> childActivityMembersMap = activityMemberVO.getActivityMembersMap();
		    Map<Long,Set<Long>> locationLevelIdsMap = activityMemberVO.getLocationLevelIdsMap();
		   
		     //Creating Business Object.
		     CommitteeInputVO meetingBO = new CommitteeInputVO();
		     Long stateId = coreDashboardGenericService.getStateIdByState(state);
		     meetingBO.setStateId(stateId);
		     List<Date>  datesList = coreDashboardGenericService.getDates(startDateString, endDateString, new SimpleDateFormat("dd/MM/yyyy"));
		     meetingBO.setStartDate(datesList.get(0));
		     meetingBO.setEndDate(datesList.get(1));
		     meetingBO.setPartyMeetingTypeIds(partyMeetingTypeIds);
		     
		     //getting counts
		     Map<String,CoreDashboardCountsVO> locationLevelCountsMap = coreDashboardGenericService.getMeetingsCountByLocationLevelIdAndLevelValues(locationLevelIdsMap,meetingBO);
		     Map<String,String>     nameForLocationMap  = coreDashboardGenericService.getLocationNamesByLocationIds(locationLevelIdsMap);
		     
		     activityMembersList = setMeetingsCountsToActivityMembers(childActivityMembersMap,"counts",locationLevelCountsMap,nameForLocationMap);
		     if(activityMembersList!=null && activityMembersList.size()>0){
		    	 setMeetingsCountsToActivityMembers(childActivityMembersMap,"percanatge",null,null);
		    	 //sorting in descending order of completed percantages.
		    	 Collections.sort(activityMembersList,ActivityMemberConductedCountPercDesc);
		     }
		     
	   }catch(Exception e){
		   LOG.error("exception occurred in getDirectChildActivityMemberMeetingsDetails()", e);
	   }
	   return activityMembersList;
    }
  
  /**
	  * @author <a href="mailto:sreedhar.itgrids.hyd@gmail.com">SREEDHAR</a>
	  *  This Service Method is used to get the getTopPoorPerformancecommittees.
	  *  @since 29-AUGUST-2016
	  */
	public List<PartyMeetingsDataVO> getTopPoorMeetingLocations(Long activityMemberId,List<Long> partyMeetingTypeIds,String state,String startDateString,String endDateString){
		   List<PartyMeetingsDataVO> finalList = null;
		   SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		   try{
			
			    Long userLocationLevelId = null;
			    List<Long> userLocationLevelValues = null;
			    List<Object[]> locations = activityMemberAccessLevelDAO.getLocationsByActivityMemberId(activityMemberId);
			    if(locations!=null && locations.size()>0){
			    	userLocationLevelValues = new ArrayList<Long>();
			    	for(Object[] obj : locations){
			    		userLocationLevelId = (Long)obj[0];
			    		userLocationLevelValues.add(obj[2]!=null?(Long)obj[2]:0l);
			    	}
			    }
			    
			     //Creating Business Object.
			     CommitteeInputVO meetingBO = new CommitteeInputVO();
			     meetingBO.setPartyMeetingTypeIds(partyMeetingTypeIds);
			     List<Date>  datesList = coreDashboardGenericService.getDates(startDateString, endDateString, new SimpleDateFormat("dd/MM/yyyy"));
			     meetingBO.setStartDate(datesList.get(0));
			     meetingBO.setEndDate(datesList.get(1));
			     Long stateId = coreDashboardGenericService.getStateIdByState(state);
			     meetingBO.setStateId(stateId);
			     
			     coreDashboardGenericService.setAppropriateLocationLevelInputsToBO(userLocationLevelId,userLocationLevelValues,meetingBO);
			     
			     //get grouping locations.grouping location is direct single sublevel of the user location level.
			     String returnLevelName="";
			     List<String> groupingLocationsList = new ArrayList<String>();
			     if(userLocationLevelId.longValue() == IConstants.COUNTRY_LEVEl_ACCESS_ID.longValue() || userLocationLevelId.longValue() == IConstants.STATE_LEVEl_ACCESS_ID.longValue() ){
			    	 groupingLocationsList.add("District");
			    	 returnLevelName = "Districts";
				}else if(userLocationLevelId.longValue() == IConstants.DISTRICT_LEVEl_ACCESS_ID.longValue() ){
					groupingLocationsList.add("Constituency");
					returnLevelName = "Constituencies";
				}else if(userLocationLevelId.longValue() == IConstants.PARLIAMENT_LEVEl_ACCESS_ID.longValue()){
					groupingLocationsList.add("Constituency");
					returnLevelName = "Constituencies";
				}else if(userLocationLevelId.longValue() == IConstants.ASSEMBLY_LEVEl_ACCESS_ID.longValue()){
					groupingLocationsList.add("Mandal");
					groupingLocationsList.add("LocalElectionbody");
					returnLevelName = "Mandals/Muncipalitys/Divisions";
			    }else if(userLocationLevelId.longValue() == IConstants.MANDAL_LEVEl_ID.longValue()){
			    	groupingLocationsList.add("Village");
			    	groupingLocationsList.add("Ward");
			    	returnLevelName = "Villages/Wards";
				}
			    
			     Map<Long,PartyMeetingsDataVO> finalMap = new LinkedHashMap<Long,PartyMeetingsDataVO>();
			     
			     if(groupingLocationsList != null && groupingLocationsList.size() > 0){
				        for(String groupingLocation : groupingLocationsList){
				          
				        	meetingBO.setGroupingLocation(groupingLocation);
				          
				            List<Object[]>  totalList   = partyMeetingDAO.getTopPoorMeetingLocations(meetingBO);
				            List<Object[]> completedList = partyMeetingStatusDAO.getTopPoorMeetingLocations(meetingBO);
				          
				            locationMeetingCountSetting(totalList,finalMap,"total",groupingLocation);
				            locationMeetingCountSetting(completedList,finalMap,"conducted",groupingLocation);
				          
				        }
				   }
			     
			     if(finalMap != null && finalMap.size()>0){
			    	 finalList = new ArrayList<PartyMeetingsDataVO>(finalMap.values());
			    	 //calculating percantages
			    	 for(PartyMeetingsDataVO locationVO : finalList){
			    		 if(locationVO.getTotalCount()!=null && locationVO.getTotalCount() > 0l){
			    			 locationVO.setConductedPerc(coreDashboardGenericService.caclPercantage(locationVO.getConductedCount(),locationVO.getTotalCount()) );
						  }
			    	 }
			    	 //sorting 
			    	 Collections.sort(finalList,meetingsConductedCountPercASC);
			    	 finalList.get(0).setRequiredName(returnLevelName);
			     }
			     
	 	  }catch(Exception e){
	 		 LOG.error("exception occurred in getTopPoorMeetingLocations()", e);
		  }
		   return finalList;
	   }
		
	   public void locationMeetingCountSetting(List<Object[]> list,Map<Long,PartyMeetingsDataVO> finalMap,String status,String groupingLocation){
		    try{
				
		        if(list !=null && list.size() >0){
				      for(Object[] obj : list){
				         
					    	  Long locationId = obj[1]!=null ? (Long)obj[1]:0l;
					    	  
					    	  PartyMeetingsDataVO locationVO = null;
					          locationVO = finalMap.get(locationId);
					          if(locationVO==null){
						          locationVO = new PartyMeetingsDataVO();
						          locationVO.setId(locationId);
						          locationVO.setName(obj[2]!=null ? obj[2].toString() : "");
						          
						          if(groupingLocation.equalsIgnoreCase("LocalElectionBody")){
						            locationVO.setLocationLevelName(obj[4]!=null ? obj[4].toString() : "");
						          }else{
						            locationVO.setLocationLevelName(groupingLocation);
						          }
						          
						          finalMap.put(locationId,locationVO);
					         }
					        locationVO = finalMap.get(locationId);
					        Long count = obj[0]!=null?(Long)obj[0]:0l;
					        
					        if(status != null && !status.isEmpty()){
					        	
					          if(status.equalsIgnoreCase("total")){
					            locationVO.setTotalCount(locationVO.getTotalCount() +count );
					          }else{
						        	String meetingStatus = null;
				        			if(groupingLocation.equalsIgnoreCase("LocalElectionBody")){
				        				meetingStatus = obj[5]!=null?obj[5].toString():"";
							        }else{
							        	meetingStatus = obj[3]!=null?obj[3].toString():"";
							        }
				        			if(meetingStatus!=null && meetingStatus.equalsIgnoreCase("Y")){
				        				locationVO.setConductedCount(locationVO.getConductedCount()+count);
				        			}
						        }
					       }
				      }
				   }
			}catch(Exception e){
				LOG.error("exception occurred in getTopPoorMeetingLocations()", e);
			}
		    
	  }

	   public static Comparator<PartyMeetingsDataVO> meetingsConductedCountPercASC = new Comparator<PartyMeetingsDataVO>() {
		     public int compare(PartyMeetingsDataVO member2, PartyMeetingsDataVO member1) {
		
		        Double perc2 = member2.getConductedPerc();
		        Double perc1 = member1.getConductedPerc();
		        //ascending order of percantages.
		         return perc2.compareTo(perc1);
		    }
		}; 
  public List<PartyMeetingsVO> getPartyMeetingCntDetailstLevelWiseByUserAccessLevel(Long activityMemberId,Long stateId,String fromDateStr,String toDateStr,List<Long> partyMeetingTypeValues){
	  
	  List<PartyMeetingsVO> resultList = new ArrayList<PartyMeetingsVO>(0);
	    Map<Long,Set<Long>> locationAccessLevelMap = new HashMap<Long, Set<Long>>(0);
	    Map<Long,Map<Long,PartyMeetingsVO>> meetingsDtlsMap = new HashMap<Long, Map<Long,PartyMeetingsVO>>(0);
	    Long userAccessLevelId=0l;
	    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		Date fromDate=null;
		Date toDate=null;
	    List<Long> locationValues=null;
	
	   try{
		   if(fromDateStr != null && fromDateStr.trim().length()>0 && toDateStr!= null && toDateStr.trim().length()>0){
				 fromDate = sdf.parse(fromDateStr);
				 toDate = sdf.parse(toDateStr);
			 }
			
		  List<Object[]> rtrnUsrAccssLvlIdAndVlusObjLst=activityMemberAccessLevelDAO.getLocationLevelAndValuesByActivityMembersId(activityMemberId);
		    if(rtrnUsrAccssLvlIdAndVlusObjLst != null && !rtrnUsrAccssLvlIdAndVlusObjLst.isEmpty()){
			   userAccessLevelId = commonMethodsUtilService.getLongValueForObject(rtrnUsrAccssLvlIdAndVlusObjLst.get(0)[0]);
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
				  for (Entry<Long,Set<Long>> entry : locationAccessLevelMap.entrySet()) {
					   locationValues = new ArrayList<Long>(entry.getValue());
					   List<Object[]> rtrnObjList = null;
					   if(entry.getKey().longValue()==5l && locationValues != null && locationValues.size() == 1){
						   rtrnObjList = partyMeetingStatusDAO.getPartyMeetingCntDetailstLevelWiseByUserAccessLevel(entry.getKey(),locationValues, stateId, fromDate, toDate, partyMeetingTypeValues, "tehsil");
						   setDataToMap(rtrnObjList,meetingsDtlsMap);
						   rtrnObjList = partyMeetingStatusDAO.getPartyMeetingCntDetailstLevelWiseByUserAccessLevel(entry.getKey(),locationValues, stateId, fromDate, toDate, partyMeetingTypeValues, "townDivision");
						   setDataToMap(rtrnObjList,meetingsDtlsMap);
					   }else{
						   rtrnObjList = partyMeetingStatusDAO.getPartyMeetingCntDetailstLevelWiseByUserAccessLevel(entry.getKey(),locationValues, stateId, fromDate, toDate, partyMeetingTypeValues,null);// userAccessLevelId,locationValues,
						   setDataToMap(rtrnObjList,meetingsDtlsMap);
					   }
				}
			  }
		    // merging mandal town division data
		    Map<Long,PartyMeetingsVO> mandalMap = meetingsDtlsMap.get(4l);
		    Map<Long,PartyMeetingsVO> townMap = meetingsDtlsMap.get(5l);
		    Map<Long,PartyMeetingsVO> divisionMap = meetingsDtlsMap.get(6l);
		    
		    Map<Long,PartyMeetingsVO> mandalTwnDivisionMap = new HashMap<Long, PartyMeetingsVO>(0);
		    
		     if(mandalMap != null){
		    	 mandalTwnDivisionMap.putAll(mandalMap);
		    	 for(Entry<Long,PartyMeetingsVO> entry:mandalTwnDivisionMap.entrySet()){
		    		 Long locationId = entry.getKey();
		    		 PartyMeetingsVO mandalVO = entry.getValue();
		    		 if(townMap != null){
		    			 if(townMap.get(locationId) != null){
		    				 mergeRequiredData(mandalVO,townMap.get(locationId)); 
		    			 }
		    		 }
		    		 if(divisionMap != null){
		    			 if(divisionMap.get(locationId) != null){
		    				 mergeRequiredData(mandalVO,divisionMap.get(locationId)); 
		    			 }
		    		 }
		    	 }
		     }else if(townMap != null) {
		    		 mandalTwnDivisionMap.putAll(townMap); 
		    		 for(Entry<Long,PartyMeetingsVO> entry:mandalTwnDivisionMap.entrySet()){
			    		 Long locationId = entry.getKey();
			    		 PartyMeetingsVO townVO = entry.getValue();
			    		 if(divisionMap != null){
			    			 if(divisionMap.get(locationId) != null){
			    				 mergeRequiredData(townVO,divisionMap.get(locationId)); 
			    			 }
			    		 }
			    	 }
		     }else if(divisionMap != null){
		    	 mandalTwnDivisionMap.putAll(divisionMap);  
		     }
		     
		    // Merging village ward data
		     Map<Long,PartyMeetingsVO> villageMap = meetingsDtlsMap.get(7l);
			 Map<Long,PartyMeetingsVO> wardMap = meetingsDtlsMap.get(8l);
			 
			 Map<Long,PartyMeetingsVO> villageWardMap = new HashMap<Long, PartyMeetingsVO>();
			 
			  if(villageMap != null ){
				  villageWardMap.putAll(villageMap);
				  for(Entry<Long,PartyMeetingsVO> entry:villageWardMap.entrySet()){
					  Long locationId = entry.getKey();
			    		 PartyMeetingsVO villageVO = entry.getValue();
			    		 if(wardMap != null){
			    			 if(wardMap.get(locationId) != null){
			    				 mergeRequiredData(villageVO,wardMap.get(locationId)); 
			    			 }
			    		 } 
				  }
			  }else{
				  villageWardMap.putAll(wardMap);  
			  }
		      //calculate percentage
			  if(meetingsDtlsMap != null && meetingsDtlsMap.size() > 0){
				  for(Entry<Long, Map<Long, PartyMeetingsVO>> entry:meetingsDtlsMap.entrySet()){
					   Long levelId = entry.getKey();
					   if(levelId.longValue() == 1l || levelId.longValue()==2l || levelId.longValue()==3l){
						   if(entry.getValue() != null && entry.getValue().size() > 0){
							   for(Entry<Long,PartyMeetingsVO> locationEntry:entry.getValue().entrySet()){
								   calculateTotalCntAndPercentage(locationEntry.getValue());  
							   }   
						   }
						  
					   }
				  }
			  }
			 if(mandalTwnDivisionMap != null && mandalTwnDivisionMap.size() > 0){
				 for(Entry<Long,PartyMeetingsVO> entry:mandalTwnDivisionMap.entrySet()){
					 calculateTotalCntAndPercentage(entry.getValue());  
				 }
			 }
			 if(villageWardMap != null && villageWardMap.size() > 0){
				 for(Entry<Long,PartyMeetingsVO> entry:villageWardMap.entrySet()){
					 calculateTotalCntAndPercentage(entry.getValue());  
				 }
			 }
			   //Preparing final result based on user access level
			  
			  // common for all 
		       PartyMeetingsVO villageWardVO = new PartyMeetingsVO();
			   villageWardVO.setName("Village/Ward");
			   if(villageWardMap != null && villageWardMap.size()  > 0){
				   villageWardVO.getPartyMettingsVOList().addAll(villageWardMap.values());   
			   }
			   resultList.add(villageWardVO);
			   PartyMeetingsVO manDalTwnDivVO = new PartyMeetingsVO();
			   manDalTwnDivVO.setName("Mandal/Town/Division");
			   if(mandalTwnDivisionMap != null && mandalTwnDivisionMap.size()  > 0){
				   manDalTwnDivVO.getPartyMettingsVOList().addAll(mandalTwnDivisionMap.values());   
			   }
			   resultList.add(manDalTwnDivVO);
			  
			  
			    PartyMeetingsVO stateVO = new PartyMeetingsVO();
			    stateVO.setName("State");
			    if(meetingsDtlsMap.get(1l) != null && meetingsDtlsMap.get(1l).size() > 0){
			    	 stateVO.getPartyMettingsVOList().addAll(meetingsDtlsMap.get(1l).values()); 	
			    }
			    PartyMeetingsVO districtVO = new PartyMeetingsVO();
			    districtVO.setName("District");
			    if(meetingsDtlsMap.get(2l) != null && meetingsDtlsMap.get(2l).size() > 0){
			    	districtVO.getPartyMettingsVOList().addAll(meetingsDtlsMap.get(2l).values()); 	
			    }
			    PartyMeetingsVO constituencyVO = new PartyMeetingsVO();
			    constituencyVO.setName("Constituency");
			    if(meetingsDtlsMap.get(3l) != null && meetingsDtlsMap.get(3l).size() > 0){
			    	constituencyVO.getPartyMettingsVOList().addAll(meetingsDtlsMap.get(3l).values()); 	
			    }
			   
			   
		       if(userAccessLevelId.longValue()==IConstants.COUNTRY_LEVEl_ACCESS_ID || userAccessLevelId.longValue()==IConstants.STATE_LEVEl_ACCESS_ID){
		    	   if(locationValues != null && locationValues.size()==1){
		    		   resultList.add(constituencyVO);
			    	   resultList.add(districtVO);
			       }else{
			    	   resultList.add(stateVO);
			     	   resultList.add(constituencyVO);
			    	   resultList.add(districtVO);   
		    	   }
		       }else if(userAccessLevelId.longValue()==IConstants.DISTRICT_LEVEl_ACCESS_ID){
		    	   if(locationValues != null && locationValues.size()==1){
		    		   resultList.add(constituencyVO);
			       }else{
			    	   resultList.add(constituencyVO);
			    	   resultList.add(districtVO);  
			       }  
		    	   }
		       else if(userAccessLevelId.longValue()==IConstants.ASSEMBLY_LEVEl_ACCESS_ID){
		    	   if(locationValues != null && locationValues.size()==1 ){
			        }else{
			        	  resultList.add(constituencyVO);	
			        }  
		       }
		         if(userAccessLevelId.longValue()==IConstants.PARLIAMENT_LEVEl_ACCESS_ID ){
		    	     resultList.add(constituencyVO);	
		         }
		     
			  
	  }catch(Exception e){
		  LOG.error("exception occurred in getPartyMeetingCntDetailstLevelWiseByUserAccessLevel()", e);  
	  }
	  return resultList;
  }
  public void calculateTotalCntAndPercentage(PartyMeetingsVO vo){
	  try{
		  if(vo != null ){
				  vo.setTotalMeetingCnt(vo.getConductedCount()+vo.getNotConductedCount()+vo.getMayBeCount());
				  vo.setTotalCountPer(calculatePercantage(vo.getTotalMeetingCnt(), vo.getTotalMeetingCnt()));
				  vo.setConductedCountPer(calculatePercantage(vo.getConductedCount(),vo.getTotalMeetingCnt()));
				  vo.setNotConductedCountPer(calculatePercantage(vo.getNotConductedCount(), vo.getTotalMeetingCnt()));
				  vo.setMayBeCountPer(calculatePercantage(vo.getMayBeCount(),vo.getTotalMeetingCnt()));
		  }
	  }catch(Exception e){
		  LOG.error("exception occurred in calculateTotalCntAndPercentage()", e);	  
	  }
  }
 public void setDataToMap(List<Object[]> rtrnObjList,Map<Long,Map<Long,PartyMeetingsVO>> meetingsDtlsMap){
	 try{
		 if(rtrnObjList != null && rtrnObjList.size() > 0){
			 for(Object[] param:rtrnObjList){
				 Long meetingLevelId = commonMethodsUtilService.getLongValueForObject(param[0]);
				 Map<Long,PartyMeetingsVO> locationMap = meetingsDtlsMap.get(meetingLevelId);
				   if(locationMap == null){
					   locationMap = new HashMap<Long, PartyMeetingsVO>();
					   meetingsDtlsMap.put(meetingLevelId, locationMap);
				   }
				   Long locationId = commonMethodsUtilService.getLongValueForObject(param[1]);
				   String locationName = commonMethodsUtilService.getStringValueForObject(param[2]);
				   String meetingStatus = commonMethodsUtilService.getStringValueForObject(param[3]);
				   Long meetingCnt = commonMethodsUtilService.getLongValueForObject(param[4]);
				   PartyMeetingsVO locationVO = locationMap.get(locationId);
				      if(locationVO == null){
				    	  locationVO = new PartyMeetingsVO();
				    	  locationVO.setId(locationId);
				    	  locationVO.setName(locationName);
				    	  locationMap.put(locationId, locationVO);
				      }
				      if(meetingStatus.equalsIgnoreCase("Y")){
				    	  locationVO.setConductedCount(meetingCnt) ; 
					  }else if(meetingStatus.equalsIgnoreCase("N")){
						  locationVO.setNotConductedCount(meetingCnt);
					  }else if(meetingStatus.equalsIgnoreCase("M")){
						  locationVO.setMayBeCount(meetingCnt);
					  }
			 }
		 }
	 }catch(Exception e){
		 LOG.error("exception occurred in getPartyMeetingCntDetailstLevelWiseByUserAccessLevel()", e);	 
	 }
 }
		
 /**
  *  @author <a href="mailto:sreedhar.itgrids.hyd@gmail.com">SREEDHAR</a>
  *  This Service Method is used to get the getPartyMeetingsMainTypeOverView data.
  *  @since 09-SEPTEMBER-2016
  */
public List<PartyMeetingsDataVO> getPartyMeetingsMainTypeOverViewData(Long partyMeetingMainTypeId,List<Long> partyMeetingTypeIds,String state,String startDateString, String endDateString){
	
	List<PartyMeetingsDataVO> finalList = new ArrayList<PartyMeetingsDataVO>();
	
	try{
		
		//creating inputVO
		PartyMeetingsInputVO inputVO = new PartyMeetingsInputVO();
		
		 inputVO.setPartyMeetingMainTypeId(partyMeetingMainTypeId);
		 inputVO.setPartyMeetingTypeIds(partyMeetingTypeIds);
		 List<Date> datesList = coreDashboardGenericService.getDates(startDateString, endDateString, new SimpleDateFormat("dd/MM/yyyy"));
		 inputVO.setStartDate(datesList.get(0));
		 inputVO.setEndDate(datesList.get(1));
		 Long stateId = coreDashboardGenericService.getStateIdByState(state);
		 inputVO.setStateId(stateId);
	     
		 List<Object[]> noOfMeetingsList = partyMeetingDAO.getNoOfMeetingsByPartyMeetingTypeIds(inputVO);
		 List<Object[]> inviteesList = partyMeetingInviteeDAO.getInvitedCountForPartyMeetingTypeIds(inputVO);
		 List<Object[]> invitteeAttendedList = partyMeetingInviteeDAO.getInvitteeAttendedCountForPartyMeetingTypeIds(inputVO);
	     List<Object[]> attendedList = partyMeetingAttendanceDAO.getAttendedCountForPartyMeetingTypeIds(inputVO);
	     
		 
	     Map<Long,PartyMeetingsDataVO> meetingTypeVOMap = new LinkedHashMap<Long,PartyMeetingsDataVO>(0); 
	     
		 if(noOfMeetingsList != null && noOfMeetingsList.size() > 0){
			 for(Object[] obj : noOfMeetingsList){
				 Long partyMeetingTypeId = obj[0]!=null ? (Long)obj[0]:0l;
				 if(partyMeetingTypeId > 0){
					 
					 PartyMeetingsDataVO meetingTypeVO = new PartyMeetingsDataVO(); 
					 meetingTypeVO.setId(partyMeetingTypeId);
					 meetingTypeVO.setName(obj[1]!=null?obj[1].toString():"");
					 meetingTypeVO.setNoOfMeetings(obj[2]!=null?(Long)obj[2]:0l);
					 
					 meetingTypeVOMap.put(meetingTypeVO.getId(), meetingTypeVO);
				 }
			 }
		 }
		 
		 if(inviteesList!=null && inviteesList.size()>0){
			 for(Object[] obj : inviteesList){
				 Long partyMeetingTypeId = obj[0]!=null ? (Long)obj[0]:0l;
				 if(partyMeetingTypeId > 0){
					 PartyMeetingsDataVO meetingTypeVO  = meetingTypeVOMap.get(partyMeetingTypeId);
					 if(meetingTypeVO!=null){
						 meetingTypeVO.setInvitedCount(obj[2]!=null?(Long)obj[2]:0l);
						 meetingTypeVO.setNotAttendedCount(obj[2]!=null?(Long)obj[2]:0l);
					 }
				 }
			 }
		 }
		 
		 if(attendedList!=null && attendedList.size()>0){
			 for(Object[] obj : attendedList){
				 Long partyMeetingTypeId = obj[0]!=null ? (Long)obj[0]:0l;
				 if(partyMeetingTypeId > 0){
					 PartyMeetingsDataVO meetingTypeVO  = meetingTypeVOMap.get(partyMeetingTypeId);
					 if(meetingTypeVO!=null){
						 meetingTypeVO.setAttendedCount(obj[2]!=null?(Long)obj[2]:0l);
					 }
				 }
			 }
		 }
		 
		 if(invitteeAttendedList!=null && invitteeAttendedList.size()>0){
			 for(Object[] obj : invitteeAttendedList){
				 Long partyMeetingTypeId = obj[0]!=null ? (Long)obj[0]:0l;
				 if(partyMeetingTypeId > 0){
					 PartyMeetingsDataVO meetingTypeVO  = meetingTypeVOMap.get(partyMeetingTypeId);
					 if(meetingTypeVO!=null){
						 meetingTypeVO.setInvitteeAttendedCount(obj[2]!=null?(Long)obj[2]:0l);
						 meetingTypeVO.setNotAttendedCount( meetingTypeVO.getInvitedCount() - meetingTypeVO.getInvitteeAttendedCount() );
					 }
				 }
			 }
		 }
		 
		 if(meetingTypeVOMap != null && meetingTypeVOMap.size() > 0 ){
			 
			 for(Long partyMeetingTypeId : meetingTypeVOMap.keySet()){
				 PartyMeetingsDataVO meetingTypeVO = meetingTypeVOMap.get(partyMeetingTypeId);
				 if(meetingTypeVO.getInvitedCount()!=null && meetingTypeVO.getInvitedCount() > 0l) {
					 meetingTypeVO.setAttendedPerc( coreDashboardGenericService.caclPercantage(meetingTypeVO.getAttendedCount(),meetingTypeVO.getInvitedCount()) );
					 meetingTypeVO.setInviteeAttendedPerc( coreDashboardGenericService.caclPercantage(meetingTypeVO.getInvitteeAttendedCount(),meetingTypeVO.getInvitedCount()) );
					 meetingTypeVO.setNotAttendedPerc( coreDashboardGenericService.caclPercantage(meetingTypeVO.getNotAttendedCount(),meetingTypeVO.getInvitedCount()) );
				   }
			 }
			 finalList.addAll(meetingTypeVOMap.values());
		 }
		 
		 
	}catch(Exception e){
		LOG.error("exception occurred in getPartyMeetingsMainTypeOverViewData()", e);
	}
	return finalList;
  }	
/**
 *  @author <a href="mailto:sreedhar.itgrids.hyd@gmail.com">SREEDHAR</a>
 *  This Service Method is used to get the getCommitteesAndPublicRepresentativeMembersInvitedAndAttendedToMeetings
 *  @since 10-SEPTEMBER-2016
 */
public PartyMeetingsDataVO getCommitteesAndPublicRepresentativeMembersInvitedAndAttendedToMeetings(Long partyMeetingMainTypeId,List<Long> partyMeetingTypeIds,String state,String startDateString, String endDateString){

PartyMeetingsDataVO finalVO = new PartyMeetingsDataVO();

try{
	  //creating inputVO
	  PartyMeetingsInputVO inputVO = new PartyMeetingsInputVO();
	
	  inputVO.setPartyMeetingMainTypeId(partyMeetingMainTypeId);
	  inputVO.setPartyMeetingTypeIds(partyMeetingTypeIds);
	  List<Date> datesList = coreDashboardGenericService.getDates(startDateString, endDateString, new SimpleDateFormat("dd/MM/yyyy"));
	  inputVO.setStartDate(datesList.get(0));
	  inputVO.setEndDate(datesList.get(1));
	  Long stateId = coreDashboardGenericService.getStateIdByState(state);
	  inputVO.setStateId(stateId);
		 
	  
	  //prepare data
	  List<Object[]> finalCommitteeLevelIds = new ArrayList<Object[]>();
	  finalCommitteeLevelIds.add(new Object[] { 10l,"State Committee"  });
	  finalCommitteeLevelIds.add(new Object[] { 11l,"District Committee"  });
	  
	  List<Object[]> finalPublicRepresentativeIds = new ArrayList<Object[]>();
	  finalPublicRepresentativeIds.add(new Object[] { 2l,"MLA/MLC"  });//2-->mla,12-->mlc
	  finalPublicRepresentativeIds.add(new Object[] { 1l,"MP"  });
	  finalPublicRepresentativeIds.add(new Object[] { 21l,"CONSTITUENCY INCHARGES"  });
	  
	  Map<Long,PartyMeetingsDataVO> committeeLevelsMap = new LinkedHashMap<Long,PartyMeetingsDataVO>(0);
	  if(finalCommitteeLevelIds != null && finalCommitteeLevelIds.size() > 0){
		  for(Object[] obj : finalCommitteeLevelIds){
			  PartyMeetingsDataVO dataVO = new PartyMeetingsDataVO();
			  dataVO.setId((Long)obj[0]);
			  dataVO.setName(obj[1].toString());
			  committeeLevelsMap.put(dataVO.getId(),dataVO);
		  }
	  }
	  
	  Map<Long,PartyMeetingsDataVO> publicRepresentativesMap = new LinkedHashMap<Long,PartyMeetingsDataVO>(0);
	  if(finalPublicRepresentativeIds != null && finalPublicRepresentativeIds.size() > 0){
		  for(Object[] obj : finalPublicRepresentativeIds){
			  PartyMeetingsDataVO dataVO = new PartyMeetingsDataVO();
			  dataVO.setId((Long)obj[0]);
			  dataVO.setName(obj[1].toString());
			  publicRepresentativesMap.put(dataVO.getId(),dataVO);
		  }
	  }
	  
	  Long othersInvitedCount = 0l;
	  Long othersInvitteeAttendedCount=0l;
	  Long othersNotAttendedCount = 0l;
	  Long othersAttendedCount = 0l;
	  
	  //committees Queries
	  List<Object[]> committeesInvitedList = partyMeetingInviteeDAO.getInvitedCadreCountForMeetingsByCommitteeWise(inputVO);
	  List<Object[]> committeesInvitteeAttendedList = partyMeetingInviteeDAO.getInvitteeAttendedCadreCountForMeetingsByCommitteeWise(inputVO);
	  List<Object[]> committeesAttendedList = partyMeetingAttendanceDAO.getAttendedCadreCountForMeetingsByCommitteeWise(inputVO);
	  
	  if(committeesInvitedList != null && committeesInvitedList.size() >0){
		  for(Object[] obj : committeesInvitedList){
			  Long committeeLevelId = obj[0]!=null?(Long)obj[0]:0l;
			  PartyMeetingsDataVO committeeLevelVO = committeeLevelsMap.get(committeeLevelId);
			  Long invitedCount = obj[2]!=null?(Long)obj[2]:0l;
			  if(committeeLevelVO!=null){ //state or district committee
				  committeeLevelVO.setInvitedCount(invitedCount); 
			  }else{//others
				  othersInvitedCount =  othersInvitedCount + invitedCount ;
			  }
		  }
	  }
	  
	  if(committeesInvitteeAttendedList != null && committeesInvitteeAttendedList.size() >0){
		  for(Object[] obj : committeesInvitteeAttendedList){
			  Long committeeLevelId = obj[0]!=null?(Long)obj[0]:0l;
			  PartyMeetingsDataVO committeeLevelVO = committeeLevelsMap.get(committeeLevelId);
			  Long inviteeAttendedCount = obj[2]!=null?(Long)obj[2]:0l;
			  if(committeeLevelVO!=null){ //state or district committee
				  committeeLevelVO.setInvitteeAttendedCount(inviteeAttendedCount); 
			  }else{//others
				  othersInvitteeAttendedCount =  othersInvitteeAttendedCount + inviteeAttendedCount ;
			  }
		  }
	  }
	  
	  if(committeesAttendedList != null && committeesAttendedList.size() >0){
		  for(Object[] obj : committeesAttendedList){
			  Long committeeLevelId = obj[0]!=null?(Long)obj[0]:0l;
			  PartyMeetingsDataVO committeeLevelVO = committeeLevelsMap.get(committeeLevelId);
			  Long attendedCount = obj[2]!=null?(Long)obj[2]:0l;
			  if(committeeLevelVO!=null){ //state or district committee
				  committeeLevelVO.setAttendedCount(attendedCount); 
			  }else{//others
				  othersAttendedCount =  othersAttendedCount + attendedCount ;
			  }
		  }
	  }
	  
	  if(committeeLevelsMap!=null && committeeLevelsMap.size()>0){
		  for(Long committeeLevelId : committeeLevelsMap.keySet() ){
			  PartyMeetingsDataVO committeeLevelVO = committeeLevelsMap.get(committeeLevelId);
			  committeeLevelVO.setNotAttendedCount(committeeLevelVO.getInvitedCount() - committeeLevelVO.getInvitteeAttendedCount());
		  }
	  }
	  
	  //public representative queries.
	  List<Object[]> publicRepresentativesInvitedList = partyMeetingInviteeDAO.getInvitedCadreCountForMeetingsByPublicRepresentativeWise(inputVO);
	  List<Object[]> publicRepresentativesInvitedAttendedList = partyMeetingInviteeDAO.getInvitteeAttendedCadreCountForMeetingsByPublicRepresentativeWise(inputVO);
	  List<Object[]> publicRepresentativesAttendedList = partyMeetingAttendanceDAO.getAttendedCadreCountForMeetingsByByPublicRepresentativeWise(inputVO);
	  
	  
	  if(publicRepresentativesInvitedList != null && publicRepresentativesInvitedList.size() > 0){
		  for(Object[] obj : publicRepresentativesInvitedList){
			  Long  publicRepresentativeTypeId = obj[0]!=null?(Long)obj[0]:0l;
			  Long invitedCount = obj[2]!=null?(Long)obj[2]:0l;
			  PartyMeetingsDataVO publicRepresentativeTypeVO = null;
			  if(publicRepresentativeTypeId.longValue() == 12l){//mlc
				  publicRepresentativeTypeVO = publicRepresentativesMap.get(2l); //2-->mla
			  }else{
				  publicRepresentativeTypeVO = publicRepresentativesMap.get(publicRepresentativeTypeId);
			  }
			  if(publicRepresentativeTypeVO!=null){
				  publicRepresentativeTypeVO.setInvitedCount( publicRepresentativeTypeVO.getInvitedCount() + invitedCount);
			  }else{
				  othersInvitedCount = othersInvitedCount + invitedCount;
			  }
		  }
	  }
	  
	  if(publicRepresentativesInvitedAttendedList != null && publicRepresentativesInvitedAttendedList.size() > 0){
		  for(Object[] obj : publicRepresentativesInvitedAttendedList){
			  Long  publicRepresentativeTypeId = obj[0]!=null?(Long)obj[0]:0l;
			  Long inviteeAttendedCount = obj[2]!=null?(Long)obj[2]:0l;
			  PartyMeetingsDataVO publicRepresentativeTypeVO = null;
			  if(publicRepresentativeTypeId.longValue() == 12l){//mlc
				  publicRepresentativeTypeVO = publicRepresentativesMap.get(2l); //2-->mla
			  }else{
				  publicRepresentativeTypeVO = publicRepresentativesMap.get(publicRepresentativeTypeId);
			  }
			  if(publicRepresentativeTypeVO!=null){
				  publicRepresentativeTypeVO.setInvitteeAttendedCount( publicRepresentativeTypeVO.getInvitteeAttendedCount() + inviteeAttendedCount);
			  }else{
				  othersInvitteeAttendedCount = othersInvitteeAttendedCount + inviteeAttendedCount;
			  }
		  }
	  }
	  
	  if(publicRepresentativesAttendedList != null && publicRepresentativesAttendedList.size() > 0){
		  for(Object[] obj : publicRepresentativesAttendedList){
			  Long  publicRepresentativeTypeId = obj[0]!=null?(Long)obj[0]:0l;
			  Long  attendedCount = obj[2]!=null?(Long)obj[2]:0l;
			  PartyMeetingsDataVO publicRepresentativeTypeVO = null;
			  if(publicRepresentativeTypeId.longValue() == 12l){//mlc
				  publicRepresentativeTypeVO = publicRepresentativesMap.get(2l); //2-->mla
			  }else{
				  publicRepresentativeTypeVO = publicRepresentativesMap.get(publicRepresentativeTypeId);
			  }
			  if(publicRepresentativeTypeVO!=null){
				  publicRepresentativeTypeVO.setAttendedCount( publicRepresentativeTypeVO.getAttendedCount() + attendedCount);
			  }else{
				  othersAttendedCount = othersAttendedCount + attendedCount;
			  }
		  }
	  }
	  
	  if(publicRepresentativesMap!=null && publicRepresentativesMap.size()>0){
		  for(Long publicRepresentativeTypeId : publicRepresentativesMap.keySet() ){
			  PartyMeetingsDataVO publicRepresentativeTypeVO = publicRepresentativesMap.get(publicRepresentativeTypeId);
			  publicRepresentativeTypeVO.setNotAttendedCount(publicRepresentativeTypeVO.getInvitedCount() - publicRepresentativeTypeVO.getInvitteeAttendedCount());
		  }
	  }
	  
	  
	  //calculating percantages.
	  calculatingPercantages(committeeLevelsMap);
	  calculatingPercantages(publicRepresentativesMap);
	  
	  
	  //Others Related
	  othersNotAttendedCount = othersInvitedCount - othersInvitteeAttendedCount;
	  PartyMeetingsDataVO othersVO = new PartyMeetingsDataVO();
	  othersVO.setName("Others");
	  othersVO.setInvitedCount(othersInvitedCount);
	  othersVO.setInvitteeAttendedCount(othersInvitteeAttendedCount);
	  othersVO.setAttendedCount(othersAttendedCount);
	  othersVO.setNotAttendedCount(othersNotAttendedCount);
	  
	  if(othersVO.getInvitedCount() != null && othersVO.getInvitedCount() > 0l){
		  othersVO.setAttendedPerc( coreDashboardGenericService.caclPercantage(othersVO.getAttendedCount(),othersVO.getInvitedCount()) );
		  othersVO.setInviteeAttendedPerc( coreDashboardGenericService.caclPercantage(othersVO.getInvitteeAttendedCount(),othersVO.getInvitedCount()) );
		  othersVO.setNotAttendedPerc( coreDashboardGenericService.caclPercantage(othersVO.getNotAttendedCount(),othersVO.getInvitedCount()) );
	  }else{
		  othersVO.setAttendedPerc(100.0);
	  }
	  
	  if(committeeLevelsMap!=null && committeeLevelsMap.size()>0){
		  finalVO.setSubList1(new ArrayList<PartyMeetingsDataVO>( committeeLevelsMap.values() ));
	  }
	  if(publicRepresentativesMap!=null && publicRepresentativesMap.size()>0){
		  finalVO.setSubList2(new ArrayList<PartyMeetingsDataVO>( publicRepresentativesMap.values() ));
	  }
	  finalVO.setSubVO(othersVO);
	  
}catch(Exception e){
	e.printStackTrace();
}
return finalVO;
}

public void calculatingPercantages(Map<Long,PartyMeetingsDataVO> dataMap){

if(dataMap != null && dataMap.size() > 0 ){
	 
	 for(Long id : dataMap.keySet()){
		 PartyMeetingsDataVO dataVO = dataMap.get(id);
		 if(dataVO.getInvitedCount()!=null && dataVO.getInvitedCount() > 0l) {
			 dataVO.setAttendedPerc( coreDashboardGenericService.caclPercantage(dataVO.getAttendedCount(),dataVO.getInvitedCount()) );
			 dataVO.setInviteeAttendedPerc( coreDashboardGenericService.caclPercantage(dataVO.getInvitteeAttendedCount(),dataVO.getInvitedCount()) );
			 dataVO.setNotAttendedPerc( coreDashboardGenericService.caclPercantage(dataVO.getNotAttendedCount(),dataVO.getInvitedCount()) );
		   }else{
			   dataVO.setAttendedPerc(100.0);
		   }
	 }
	 
 }

}
public List<PartyMeetingsDataVO> getParyMeetingTypeDetailsDistrictWise(Long partyMeetingMainTypeId,List<Long> partyMeetingTypeIds,String state,String startDateString, String endDateString){
	
	List<PartyMeetingsDataVO> resultList = new ArrayList<PartyMeetingsDataVO>();
	Map<Long,String> partyMeetingTypeMap = new HashMap<Long, String>(0);
	Map<Long,List<PartyMeetingsDataVO>> inviteeCadresMap = new HashMap<Long, List<PartyMeetingsDataVO>>(0);
	
	try{
		
		//creating inputVO
		PartyMeetingsInputVO inputVO = new PartyMeetingsInputVO();
		
		 inputVO.setPartyMeetingMainTypeId(partyMeetingMainTypeId);
		 inputVO.setPartyMeetingTypeIds(partyMeetingTypeIds);
		 List<Date> datesList = coreDashboardGenericService.getDates(startDateString, endDateString, new SimpleDateFormat("dd/MM/yyyy"));
		 inputVO.setStartDate(datesList.get(0));
		 inputVO.setEndDate(datesList.get(1));
		 Long stateId = coreDashboardGenericService.getStateIdByState(state);
		 inputVO.setStateId(stateId);
		 List<Object[]> inviteesList = partyMeetingInviteeDAO.getDistrictWiseInvitedCountForPartyMeetingTypeIds(inputVO);
		 List<Object[]> invitteeAttendedList = partyMeetingInviteeDAO.getDistrictWiseInvitteeAttendedCountForPartyMeetingTypeIds(inputVO);
	     List<Object[]> attendedList = partyMeetingAttendanceDAO.getDistrictWiseAttendedCountForPartyMeetingTypeIds(inputVO);
	     setInviteeDetails(inviteesList,inviteeCadresMap,partyMeetingTypeMap);
	     
	     if(invitteeAttendedList != null && invitteeAttendedList.size() > 0){
	    	 for (Object[] param : invitteeAttendedList) {
				Long partyMeetinTypeId = commonMethodsUtilService.getLongValueForObject(param[0]);
				Long districtId = commonMethodsUtilService.getLongValueForObject(param[2]);
				Long inviteeAttendedCnt = commonMethodsUtilService.getLongValueForObject(param[4]);
				List<PartyMeetingsDataVO> inviteeList = inviteeCadresMap.get(partyMeetinTypeId);
				  if(inviteeList != null && inviteeList.size() > 0){
					  for (PartyMeetingsDataVO inviteeVO : inviteeList) {
						  if(inviteeVO.getId().equals(districtId)){
							  inviteeVO.setInvitteeAttendedCount(inviteeAttendedCnt);
							  inviteeVO.setNotAttendedCount(inviteeVO.getInvitedCount()-inviteeVO.getInvitteeAttendedCount());
						  }
					}
				  }
			}
	     }
	    /* if(attendedList != null && attendedList.size() > 0){
	    	  for (Object[] param : attendedList) {
	    			Long partyMeetinTypeId = commonMethodsUtilService.getLongValueForObject(param[0]);
	    			String partyMeetingType = commonMethodsUtilService.getStringValueForObject(param[1]);
					Long districtId = commonMethodsUtilService.getLongValueForObject(param[2]);
					String districtName = commonMethodsUtilService.getStringValueForObject(param[3]);
					Long attendedCnt = commonMethodsUtilService.getLongValueForObject(param[4]);
					List<PartyMeetingsDataVO> inviteeList = inviteeCadresMap.get(partyMeetinTypeId);
					
					PartyMeetingsDataVO inviteeVO = getMatchVO(inviteeList,districtId);
					if(inviteeVO == null){
						PartyMeetingsDataVO attendedVO = new PartyMeetingsDataVO();
						attendedVO.setId(districtId);
						attendedVO.setName(districtName);
						attendedVO.setAttendedCount(attendedCnt);
						if(inviteeList == null){
							inviteeList = new ArrayList<PartyMeetingsDataVO>(0);
							inviteeCadresMap.put(partyMeetinTypeId, inviteeList);
							partyMeetingTypeMap.put(partyMeetinTypeId, partyMeetingType);
						}
						inviteeList.add(attendedVO);	
					}else{
						 inviteeVO.setAttendedCount(attendedCnt);	
					}
					}
	     }*/
	     //Calculate percentage
	     if(inviteeCadresMap != null && inviteeCadresMap.size() > 0){
	    	 for(Entry<Long,List<PartyMeetingsDataVO>> entry:inviteeCadresMap.entrySet()){
	    		  List<PartyMeetingsDataVO> inviteeAttendedNtAttnddCadreLst = entry.getValue();
	    		   if(inviteeAttendedNtAttnddCadreLst != null && inviteeAttendedNtAttnddCadreLst.size() > 0){
	    			   for (PartyMeetingsDataVO invtAttnddNtAttnddCdrVO : inviteeAttendedNtAttnddCadreLst) {
	    				   invtAttnddNtAttnddCdrVO.setInviteeAttendedPerc(calculatePercantage(invtAttnddNtAttnddCdrVO.getInvitteeAttendedCount(),invtAttnddNtAttnddCdrVO.getInvitedCount()));
	    				   invtAttnddNtAttnddCdrVO.setNotAttendedPerc(calculatePercantage(invtAttnddNtAttnddCdrVO.getNotAttendedCount(),invtAttnddNtAttnddCdrVO.getInvitedCount()));
	    				   //invtAttnddNtAttnddCdrVO.setAttendedPerc(calculatePercantage(invtAttnddNtAttnddCdrVO.getAttendedCount(),invtAttnddNtAttnddCdrVO.getInvitedCount()));
					}
	    		   }
	    	 }
	     }
	     //setting result to resultList
	     if(inviteeCadresMap != null && inviteeCadresMap.size() > 0){
	    	 for(Entry<Long,List<PartyMeetingsDataVO>> entry:inviteeCadresMap.entrySet()){
	    		 Long partyMeetingTypeId = entry.getKey();
	    		 PartyMeetingsDataVO partyMeetingTypeVO = new PartyMeetingsDataVO();
	    		 partyMeetingTypeVO.setId(partyMeetingTypeId);
	    		 partyMeetingTypeVO.setName(partyMeetingTypeMap.get(partyMeetingTypeId));
	    		 partyMeetingTypeVO.setDistrictList(new ArrayList<PartyMeetingsDataVO>(entry.getValue()));
	    		 resultList.add(partyMeetingTypeVO);
	    	 }
	     }
	     }
	catch(Exception e){
		LOG.error("exception occurred in getParyMeetingTypeDistrictWiseDetails()", e);
	}
	return resultList;
}
@SuppressWarnings("null")
public PartyMeetingsDataVO getMatchVO(List<PartyMeetingsDataVO> inviteeList,Long districtId){
	if(inviteeList == null || inviteeList.size()==1)
		return null;
	for(PartyMeetingsDataVO districtVO:inviteeList){
		 if(districtVO.getId().equals(districtId)){
			 return districtVO;
		 }
	}
	return null;
}
public void setInviteeDetails(List<Object[]> inviteeReturnList,Map<Long,List<PartyMeetingsDataVO>> inviteeMap,Map<Long,String> partyMeetingTypeMap){
	try{
		if(inviteeReturnList != null && inviteeReturnList.size() > 0){
			for(Object[] param:inviteeReturnList){
				Long partyMeetingTypeId = commonMethodsUtilService.getLongValueForObject(param[0]);
				String partyMeetingType = commonMethodsUtilService.getStringValueForObject(param[1]);
				 List<PartyMeetingsDataVO> inviteeList=inviteeMap.get(partyMeetingTypeId);
				  if(inviteeList == null ){
					  inviteeList = new ArrayList<PartyMeetingsDataVO>();
					  partyMeetingTypeMap.put(partyMeetingTypeId, partyMeetingType);
					  inviteeMap.put(partyMeetingTypeId, inviteeList);
				  }
				  PartyMeetingsDataVO inviteeCadreVO = new PartyMeetingsDataVO();
				  inviteeCadreVO.setId(commonMethodsUtilService.getLongValueForObject(param[2]));
				  inviteeCadreVO.setName(commonMethodsUtilService.getStringValueForObject(param[3]));
				  inviteeCadreVO.setInvitedCount(commonMethodsUtilService.getLongValueForObject(param[4]));
				  inviteeList.add(inviteeCadreVO);
			}
		}
	}catch(Exception e){
		LOG.error("exception occurred in setInviteeDetails()", e);	
	}
}
}
