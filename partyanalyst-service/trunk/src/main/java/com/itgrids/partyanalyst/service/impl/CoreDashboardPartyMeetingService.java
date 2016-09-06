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

import com.itgrids.partyanalyst.dao.IActivityMemberAccessLevelDAO;
import com.itgrids.partyanalyst.dao.IPartyMeetingDAO;
import com.itgrids.partyanalyst.dao.IPartyMeetingStatusDAO;
import com.itgrids.partyanalyst.dto.ActivityMemberVO;
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
 public PartyMeetingsVO getPartyMeetingBasicCountDetails(Long activityMemberId,Long stateId,String fromDateStr,String toDateStr){
	  
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
			List<Object[]> rtrnObjList = partyMeetingDAO.getPartyMeetingOverAllCountByUserAccessLevel(entry.getKey(),new ArrayList<Long>(entry.getValue()), stateId, fromDate, toDate);// userAccessLevelId,locationValues,
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
			 List<Object[]> rtrnList = partyMeetingStatusDAO.getPartyMeetingCountLevelWise(entry.getKey(),new ArrayList<Long>(entry.getValue()), stateId, fromDate, toDate);
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
			 List<Object[]> rtrnObjList =  partyMeetingDAO.getPartyMeetingOverAllCountByUserAccessLevel(entry.getKey(),new ArrayList<Long>(entry.getValue()), stateId, fromDate, toDate);// userAccessLevelId,locationValues,
			
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
    		 List<Object[]> rtrnObjList = partyMeetingStatusDAO.getPartyMeetingCountLevelWise(entry.getKey(), new ArrayList<Long>(entry.getValue()), stateId, fromDate, toDate);
    		 setPartyMeetingLevelWiseDtlsCntToMap(rtrnObjList,overAllLevelWiseDtlsMap);
    	 }
     }
       //Calculate OverAll Details percentage
       PartyMeetingsVO overAllDtlsVO = overAllTotalDtlsMap.get("overAllTotalDtls");
       overAllDtlsVO.setName("OverAllDtls");
       overAllDtlsVO.setTotalCount(overAllTotalCountMap.get("overAllTotalCount"));
       overAllDtlsVO.setTotalCountPer(calculatePercantage(overAllDtlsVO.getTotalCount(),overAllDtlsVO.getTotalCount()));
       overAllDtlsVO.setConductedCountPer(calculatePercantage(overAllDtlsVO.getConductedCount(),overAllDtlsVO.getTotalCount()));
       overAllDtlsVO.setNotConductedCountPer(calculatePercantage(overAllDtlsVO.getNotConductedCount(), overAllDtlsVO.getTotalCount()));
       overAllDtlsVO.setMayBeCountPer(calculatePercantage(overAllDtlsVO.getMayBeCount(),overAllDtlsVO.getTotalCount()));
     
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
       manTwnDivVO.setTotalCount(overAllTotalLevelWiseCountMap.get("totalMandalTownDivisionCount"));
       manTwnDivVO.setConductedCount(mandalVO.getConductedCount()+townVO.getConductedCount()+divisionVO.getConductedCount());
       manTwnDivVO.setNotConductedCount(mandalVO.getNotConductedCount()+townVO.getNotConductedCount()+divisionVO.getNotConductedCount());
       manTwnDivVO.setMayBeCount(mandalVO.getMayBeCount()+townVO.getMayBeCount()+divisionVO.getMayBeCount());
       manTwnDivVO.setTotalCountPer(calculatePercantage(manTwnDivVO.getTotalCount(),manTwnDivVO.getTotalCount()));
       manTwnDivVO.setConductedCountPer(calculatePercantage(manTwnDivVO.getConductedCount(),manTwnDivVO.getTotalCount()));
       manTwnDivVO.setNotConductedCountPer(calculatePercantage(manTwnDivVO.getNotConductedCount(),manTwnDivVO.getTotalCount()));
       manTwnDivVO.setMayBeCountPer(calculatePercantage(manTwnDivVO.getMayBeCount(),manTwnDivVO.getTotalCount()));
       mandalTwnDivMap.put("mandalTwnDiv", manTwnDivVO);
       
       //Merge Village Ward 
       PartyMeetingsVO villageVO = overAllLevelWiseDtlsMap.get(7l);
       PartyMeetingsVO wardVO = overAllLevelWiseDtlsMap.get(8l);
      
       PartyMeetingsVO villageWardVO = new PartyMeetingsVO();
       
       villageWardVO.setName("Village/Ward");
       villageWardVO.setTotalCount(overAllTotalLevelWiseCountMap.get("totalVillageWardCount"));
       villageWardVO.setConductedCount(villageVO.getConductedCount()+wardVO.getConductedCount());
       villageWardVO.setNotConductedCount(villageVO.getNotConductedCount()+wardVO.getNotConductedCount());
       villageWardVO.setMayBeCount(villageVO.getMayBeCount()+wardVO.getMayBeCount());
       villageWardVO.setTotalCountPer(calculatePercantage(villageWardVO.getTotalCount(),villageWardVO.getTotalCount()));
       villageWardVO.setConductedCountPer(calculatePercantage(villageWardVO.getConductedCount(), villageWardVO.getTotalCount()));
       villageWardVO.setNotConductedCountPer(calculatePercantage(villageWardVO.getNotConductedCount(), villageWardVO.getTotalCount()));
       villageWardVO.setMayBeCountPer(calculatePercantage(villageWardVO.getMayBeCount(),villageWardVO.getTotalCount()));
       villageWardMap.put("villageWard", villageWardVO);
       
       //Setting Result to final VO By User Access Level
       
       //common for all user
       resultVO.setOverAllVO(overAllTotalDtlsMap.get("overAllTotalDtls"));
	   resultVO.getPartyMettingsVOList().add(villageWardMap.get("villageWard"));
	   resultVO.getPartyMettingsVOList().add(mandalTwnDivMap.get("mandalTwnDiv"));
	 
       if(userAccessLevelId.longValue()==IConstants.COUNTRY_LEVEl_ACCESS_ID || userAccessLevelId.longValue()==IConstants.STATE_LEVEl_ACCESS_ID){
    	   resultVO.getPartyMettingsVOList().add(overAllLevelWiseDtlsMap.get(3l));
    	   resultVO.getPartyMettingsVOList().add(overAllLevelWiseDtlsMap.get(2l));
    	   resultVO.getPartyMettingsVOList().add(overAllLevelWiseDtlsMap.get(1l));
       }
       if(userAccessLevelId.longValue()==IConstants.DISTRICT_LEVEl_ACCESS_ID){
    	   resultVO.getPartyMettingsVOList().add(overAllLevelWiseDtlsMap.get(3l));
    	   resultVO.getPartyMettingsVOList().add(overAllLevelWiseDtlsMap.get(2l));
       }
       if(userAccessLevelId.longValue()==IConstants.PARLIAMENT_LEVEl_ACCESS_ID || userAccessLevelId.longValue()==IConstants.ASSEMBLY_LEVEl_ACCESS_ID){
    	   resultVO.getPartyMettingsVOList().add(overAllLevelWiseDtlsMap.get(3l));
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
		 VO.setTotalCount(overAllTotalLevelWiseCountMap.get(key));
		 VO.setName(levelName);
		 VO.setConductedCountPer(calculatePercantage(VO.getConductedCount(),VO.getTotalCount()));
		 VO.setNotConductedCountPer(calculatePercantage(VO.getNotConductedCount(),VO.getTotalCount()));
		 VO.setMayBeCountPer(calculatePercantage(VO.getMayBeCount(), VO.getTotalCount())); 
	 }catch(Exception e){
		  LOG.error("Exception raised at setDataToPartyMeetingLevelWise() method of CoreDashboardPartyMeetingService", e); 
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
 public List<List<UserTypeVO>> getUserTypeWiseMeetingCounductedNotCounductedMayBeDetailsCnt(Long userId,Long userTypeId,Long activityMemberId,Long stateId,String fromDateStr,String toDateStr){
	
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
                   List<Object[]> returnOverAllObjList = partyMeetingDAO.getPartyMeetingOverAllCountLocationWiseByUserAccessLevel(entry.getKey(),new ArrayList<Long>(entry.getValue()), stateId, fromDate, toDate);
                   totalCntMap.put(entry.getKey(), 0l);
                   calculateTotalCntByAccessLevelWise(entry.getKey(),returnOverAllObjList,totalCntMap);
				}
		     }
		    if(locationLevelMap != null && locationLevelMap.size() > 0){
		    	for(Entry<Long,Set<Long>> entry:locationLevelMap.entrySet()){
		    		List<Object[]> returnList = partyMeetingStatusDAO.getPartyMeetingCountLocationWiseByUserAccess(entry.getKey(),new ArrayList<Long>(entry.getValue()), stateId, fromDate, toDate);
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
				    		  Long totalMeetingCnt = totalCntMap.get(vo.getLocationLevelId());
				    		  if(totalMeetingCnt != null){
				    			  vo.setConductedAndMayBeMeetingPer(calculatePercantage(vo.getConductedMeetingCnt()+vo.getMayBeMeetingCnt(),totalMeetingCnt));  
				    		  }
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
					Collections.sort(memberList, meetingCountPercDesc);
				}
			}
	 }catch(Exception e) {
	  LOG.error("Exception raised at getUserTypeWiseMeetingCounductedNotCounductedMayBeDetailsCnt() method of CoreDashboardPartyMeetingService", e);
	}
	return resultList; 
 }
 public void calculateTotalCntByAccessLevelWise(Long accessLevelId,List<Object[]> returnList,Map<Long,Long> totalCntMap){
	 try{
		if(returnList != null && returnList.size() > 0){
			for(Object[] obj: returnList) {
				totalCntMap.put(accessLevelId,totalCntMap.get(accessLevelId)+commonMethodsUtilService.getLongValueForObject(obj[1]));
			}
		}
	 }catch(Exception e){
		LOG.error("Exception raised at calculateTotalCntByAccessLevelWise() method of CoreDashboardPartyMeetingService", e);
	 }
 }
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
	 
}
