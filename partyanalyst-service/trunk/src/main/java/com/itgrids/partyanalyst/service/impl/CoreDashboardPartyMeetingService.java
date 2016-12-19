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
import org.jfree.util.Log;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionCallbackWithoutResult;
import org.springframework.transaction.support.TransactionTemplate;

import com.itgrids.partyanalyst.dao.IActivityMemberAccessLevelDAO;
import com.itgrids.partyanalyst.dao.IPartyMeetingAttendanceDAO;
import com.itgrids.partyanalyst.dao.IPartyMeetingDAO;
import com.itgrids.partyanalyst.dao.IPartyMeetingInviteeDAO;
import com.itgrids.partyanalyst.dao.IPartyMeetingSessionDAO;
import com.itgrids.partyanalyst.dao.IPartyMeetingStatusDAO;
import com.itgrids.partyanalyst.dao.IPartyMeetingTypeDAO;
import com.itgrids.partyanalyst.dao.ITrainingCampAttendanceDAO;
import com.itgrids.partyanalyst.dto.ActivityMemberVO;
import com.itgrids.partyanalyst.dto.CommitteeInputVO;
import com.itgrids.partyanalyst.dto.CoreDashboardCountsVO;
import com.itgrids.partyanalyst.dto.IdNameVO;
import com.itgrids.partyanalyst.dto.PartyMeetingsDataVO;
import com.itgrids.partyanalyst.dto.PartyMeetingsInputVO;
import com.itgrids.partyanalyst.dto.PartyMeetingsVO;
import com.itgrids.partyanalyst.dto.ResultStatus;
import com.itgrids.partyanalyst.dto.UserTypeVO;
import com.itgrids.partyanalyst.service.ICoreDashboardGenericService;
import com.itgrids.partyanalyst.service.ICoreDashboardPartyMeetingService;
import com.itgrids.partyanalyst.utils.CommonMethodsUtilService;
import com.itgrids.partyanalyst.utils.DateUtilService;
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
	 private TransactionTemplate transactionTemplate;
	 private ITrainingCampAttendanceDAO trainingCampAttendanceDAO;
	 private IPartyMeetingSessionDAO partyMeetingSessionDAO;
	 
	public IPartyMeetingSessionDAO getPartyMeetingSessionDAO() {
		return partyMeetingSessionDAO;
	}
	public void setPartyMeetingSessionDAO(
			IPartyMeetingSessionDAO partyMeetingSessionDAO) {
		this.partyMeetingSessionDAO = partyMeetingSessionDAO;
	}
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
	
	public void setTransactionTemplate(TransactionTemplate transactionTemplate) {
		this.transactionTemplate = transactionTemplate;
	}

public void setTrainingCampAttendanceDAO(
			ITrainingCampAttendanceDAO trainingCampAttendanceDAO) {
		this.trainingCampAttendanceDAO = trainingCampAttendanceDAO;
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
			 List<Object[]> rtrnList = partyMeetingStatusDAO.getPartyMeetingCountLevelWise(entry.getKey(),new ArrayList<Long>(entry.getValue()), stateId, fromDate, toDate,partyMeetingTypeValues,"partyMeetingCount");
			 setOverAllDataToMap(rtrnList,overAllTotalDtlsMap,"partyMeetingCount");
			 List<Object[]> rtrnCommentCntList = partyMeetingStatusDAO.getPartyMeetingCountLevelWise(entry.getKey(),new ArrayList<Long>(entry.getValue()), stateId, fromDate, toDate,partyMeetingTypeValues,"commentCount");
			 setOverAllDataToMap(rtrnCommentCntList,overAllTotalDtlsMap,"commentCount");
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
    		 List<Object[]> rtrnObjList = partyMeetingStatusDAO.getPartyMeetingCountLevelWise(entry.getKey(), new ArrayList<Long>(entry.getValue()), stateId, fromDate, toDate,partyMeetingTypeValues,"partyMeetingCount");
    		 setPartyMeetingLevelWiseDtlsCntToMap(rtrnObjList,overAllLevelWiseDtlsMap,"partyMeetingCount");
    		 List<Object[]> rtrnCommentCntObjList = partyMeetingStatusDAO.getPartyMeetingCountLevelWise(entry.getKey(), new ArrayList<Long>(entry.getValue()), stateId, fromDate, toDate,partyMeetingTypeValues,"commentCount");
    		 setPartyMeetingLevelWiseDtlsCntToMap(rtrnCommentCntObjList,overAllLevelWiseDtlsMap,"commentCount");
    		 
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
           overAllDtlsVO.setTotalNotUpdatedCntPer(calculatePercantage(overAllDtlsVO.getTotalNotUpdatedCnt(), overAllDtlsVO.getTotalCount()));
           
           // comment percentage 
           overAllDtlsVO.setTotalCommentCnt(overAllDtlsVO.getConductedCommentCnt()+overAllDtlsVO.getNotConductedCommentCnt()+overAllDtlsVO.getMayBeCommentCnt()+overAllDtlsVO.getNotUpdatedCommentCnt());
           overAllDtlsVO.setTotalCommentCntPer(calculatePercantage(overAllDtlsVO.getTotalCommentCnt(), overAllDtlsVO.getTotalCount()));
           overAllDtlsVO.setConductedCommentCntPer(calculatePercantage(overAllDtlsVO.getConductedCommentCnt(),overAllDtlsVO.getConductedCount()));
           overAllDtlsVO.setNotConductedCommentCntPer(calculatePercantage(overAllDtlsVO.getNotConductedCommentCnt(), overAllDtlsVO.getNotConductedCount()));
           overAllDtlsVO.setMayBeCommentCntPer(calculatePercantage(overAllDtlsVO.getMayBeCommentCnt(),overAllDtlsVO.getMayBeCount()));
           overAllDtlsVO.setNotUpdatedCommentCntPer(calculatePercantage(overAllDtlsVO.getNotUpdatedCommentCnt(), overAllDtlsVO.getTotalNotUpdatedCnt()));
           
       }
     
       //Calculate Party Meeting Level Wise Data and percentage
        setDataToPartyMeetingLevelWise( overAllLevelWiseDtlsMap.get(1l),overAllTotalLevelWiseCountMap,"State","totalStateCount"); // 1l state party meeting levelId
        setDataToPartyMeetingLevelWise( overAllLevelWiseDtlsMap.get(2l),overAllTotalLevelWiseCountMap,"District","totalDistrictCount");// 2l District party meeting levelId
        setDataToPartyMeetingLevelWise( overAllLevelWiseDtlsMap.get(3l),overAllTotalLevelWiseCountMap,"Constituency","totalConstituencyCount");// 3l Constituency party meeting levelId
        
      //Merge Mandal Town And Division 
        
	   PartyMeetingsVO mandalVO = overAllLevelWiseDtlsMap.get(4l);// 4l mandal party meeting levelId
	   PartyMeetingsVO townVO = overAllLevelWiseDtlsMap.get(5l);// 5l Town party meeting levelId
	   PartyMeetingsVO divisionVO = overAllLevelWiseDtlsMap.get(6l);// 6l division party meeting levelId
	     
       PartyMeetingsVO manTwnDivVO = new PartyMeetingsVO();
       
       manTwnDivVO.setName("Mandal/Town/Division");
       if(overAllTotalLevelWiseCountMap.get("totalMandalTownDivisionCount") != null){
    	 manTwnDivVO.setTotalCount(overAllTotalLevelWiseCountMap.get("totalMandalTownDivisionCount"));   
       }
      if(mandalVO != null){
    	  mergeRequiredData(manTwnDivVO,mandalVO,"commentcount");  
      }
      if(townVO != null){
    	  mergeRequiredData(manTwnDivVO,townVO,"commentcount");  
      }
      if(divisionVO != null){
    	  mergeRequiredData(manTwnDivVO,divisionVO,"commentcount");  
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
     	  mergeRequiredData(villageWardVO,villageVO,"commentcount");  
       }
       if(wardVO != null){
     	  mergeRequiredData(villageWardVO,wardVO,"commentcount");  
       }
       //Calculating Percentage 
       calculatePercentages(villageWardVO,villageWardMap,"villageWard");
       
       
       //Setting Result to final VO By User Access Level
       
       //common for all user
       resultVO.setOverAllVO(overAllTotalDtlsMap.get("overAllTotalDtls"));
       if(villageWardMap.get("villageWard") != null){
    	   resultVO.getPartyMettingsVOList().add(villageWardMap.get("villageWard"));   
       }
       if(mandalTwnDivMap.get("mandalTwnDiv") != null){
    	   resultVO.getPartyMettingsVOList().add(mandalTwnDivMap.get("mandalTwnDiv"));
       }
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
 public void setOverAllDataToMap(List<Object[]> rtrnList,Map<String,PartyMeetingsVO> overAllTotalDtlsMap,String statusType){
	 try{
		  if(rtrnList != null && rtrnList.size() > 0){
			  for (Object[] param : rtrnList) {
				 PartyMeetingsVO VO = overAllTotalDtlsMap.get("overAllTotalDtls");
				  if(VO == null){
					  VO = new PartyMeetingsVO();
					  overAllTotalDtlsMap.put("overAllTotalDtls",VO);
				  }
				  String status = param[2] != null ? param[2].toString() :"";
				  Long count = param[3] != null ? (Long)param[3]:0l;
				   if(statusType.equalsIgnoreCase("partyMeetingCount")){
					  if(status.equalsIgnoreCase("Y")){
						  VO.setConductedCount(VO.getConductedCount()+count);
					  }else if(status.equalsIgnoreCase("N")){
						  VO.setNotConductedCount(VO.getNotConductedCount()+count);
					  }else if(status.equalsIgnoreCase("M")){
						  VO.setMayBeCount(VO.getMayBeCount()+count); 
					  }else if(status.equalsIgnoreCase("NU")){
						 VO.setTotalNotUpdatedCnt(VO.getTotalNotUpdatedCnt()+count);  
					  }  
				   }else if(statusType.equalsIgnoreCase("commentCount")){
					  if(status.equalsIgnoreCase("Y")){
						  VO.setConductedCommentCnt(VO.getConductedCommentCnt()+count);
					  }else if(status.equalsIgnoreCase("N")){
						  VO.setNotConductedCommentCnt(VO.getNotConductedCommentCnt()+count);
					  }else if(status.equalsIgnoreCase("M")){
						  VO.setMayBeCommentCnt(VO.getMayBeCommentCnt()+count); 
					  }else if(status.equalsIgnoreCase("NU")){
						  VO.setNotUpdatedCommentCnt(VO.getNotUpdatedCommentCnt()+count);
					  }   
				  }
			}
		  }
	 }catch(Exception e){
		 LOG.error("Exception raised at setOverAllDataToMap() method of CoreDashboardPartyMeetingService", e); 
	 }
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
  public void setPartyMeetingLevelWiseDtlsCntToMap(List<Object[]> returnList,Map<Long,PartyMeetingsVO> paryMeetingLevelMap,String statusType){
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
			  if(statusType.equalsIgnoreCase("partyMeetingCount")){
				  if(status.equalsIgnoreCase("Y")){
					  VO.setConductedCount(totalMeetingCount) ; 
				  }else if(status.equalsIgnoreCase("N")){
					  VO.setNotConductedCount(totalMeetingCount);
				  }else if(status.equalsIgnoreCase("M")){
					  VO.setMayBeCount(totalMeetingCount);
				  }else if(status.equalsIgnoreCase("NU")){
					  VO.setTotalNotUpdatedCnt(totalMeetingCount);
				  }  
			  }else if(statusType.equalsIgnoreCase("commentCount")){
				  if(status.equalsIgnoreCase("Y")){
					  VO.setConductedCommentCnt(VO.getConductedCommentCnt()+totalMeetingCount);
				  }else if(status.equalsIgnoreCase("N")){
					  VO.setNotConductedCommentCnt(VO.getNotConductedCommentCnt()+totalMeetingCount);
				  }else if(status.equalsIgnoreCase("M")){
					  VO.setMayBeCommentCnt(VO.getMayBeCommentCnt()+totalMeetingCount); 
				  }else if(status.equalsIgnoreCase("NU")){
					  VO.setNotUpdatedCommentCnt(VO.getNotUpdatedCommentCnt()+totalMeetingCount);
				  }   
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
			 VO.setTotalNotUpdatedCntPer(calculatePercantage(VO.getTotalNotUpdatedCnt(), VO.getTotalCount()));
			// comment percentage
		    VO.setTotalCommentCnt(VO.getConductedCommentCnt()+VO.getNotConductedCommentCnt()+VO.getMayBeCommentCnt()+VO.getNotUpdatedCommentCnt());
		    VO.setTotalCommentCntPer(calculatePercantage(VO.getTotalCommentCnt(), VO.getTotalCount()));
		    VO.setConductedCommentCntPer(calculatePercantage(VO.getConductedCommentCnt(),VO.getConductedCount()));
		    VO.setNotConductedCommentCntPer(calculatePercantage(VO.getNotConductedCommentCnt(), VO.getNotConductedCount()));
		    VO.setMayBeCommentCntPer(calculatePercantage(VO.getMayBeCommentCnt(),VO.getMayBeCount()));
		    VO.setNotUpdatedCommentCntPer(calculatePercantage(VO.getNotUpdatedCommentCnt(), VO.getTotalNotUpdatedCnt()));
		 }
	 }catch(Exception e){
		  LOG.error("Exception raised at setDataToPartyMeetingLevelWise() method of CoreDashboardPartyMeetingService", e); 
	 }
 }
 public void mergeRequiredData(PartyMeetingsVO resultVO,PartyMeetingsVO VO,String status){
	 try{
		 resultVO.setConductedCount(resultVO.getConductedCount()+VO.getConductedCount());
		 resultVO.setNotConductedCount(resultVO.getNotConductedCount()+VO.getNotConductedCount());
		 resultVO.setMayBeCount(resultVO.getMayBeCount()+VO.getMayBeCount());
		 resultVO.setTotalNotUpdatedCnt(resultVO.getTotalNotUpdatedCnt()+VO.getTotalNotUpdatedCnt());
		 if(status.equalsIgnoreCase("commentcount")){
		 resultVO.setConductedCommentCnt(resultVO.getConductedCommentCnt()+VO.getConductedCommentCnt());
		 resultVO.setNotConductedCommentCnt(resultVO.getNotConductedCommentCnt()+VO.getNotConductedCommentCnt());
		 resultVO.setMayBeCommentCnt(resultVO.getMayBeCommentCnt()+VO.getMayBeCommentCnt()); 
		 resultVO.setNotUpdatedCommentCnt(resultVO.getNotUpdatedCommentCnt()+VO.getNotUpdatedCommentCnt());
		 }
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
			 resultVO.setTotalNotUpdatedCntPer(calculatePercantage(resultVO.getTotalNotUpdatedCnt(),resultVO.getTotalCount()));
			 // comment percentage
			 resultVO.setTotalCommentCnt(resultVO.getConductedCommentCnt()+resultVO.getNotConductedCommentCnt()+resultVO.getMayBeCommentCnt()+resultVO.getNotUpdatedCommentCnt());
			 resultVO.setTotalCommentCntPer(calculatePercantage(resultVO.getTotalCommentCnt(), resultVO.getTotalCount()));
			 resultVO.setConductedCommentCntPer(calculatePercantage(resultVO.getConductedCommentCnt(),resultVO.getConductedCount()));
			 resultVO.setNotConductedCommentCntPer(calculatePercantage(resultVO.getNotConductedCommentCnt(), resultVO.getNotConductedCount()));
			 resultVO.setMayBeCommentCntPer(calculatePercantage(resultVO.getMayBeCommentCnt(),resultVO.getMayBeCount()));
			 resultVO.setNotUpdatedCommentCntPer(calculatePercantage(resultVO.getNotUpdatedCommentCnt(), resultVO.getTotalNotUpdatedCnt()));
			    
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
  public List<UserTypeVO> getSelectedChildUserTypeMembersWithMeetingsCount(Long parentActivityMemberId,List<Long> childUserTypeIds,String state,String startDateString,String endDateString,List<Long> partyMeetingTypeIds){
	    List<UserTypeVO> activityMembersList = null;
	   try{
		   
		    //calling generic method.
		    //ActivityMemberVO activityMemberVO = coreDashboardGenericService.getSelectedChildUserTypeMembers(parentActivityMemberId,childUserTypeId);
		    ActivityMemberVO activityMemberVO = coreDashboardGenericService.getRequiredSubLevelActivityMembersDetails(parentActivityMemberId,childUserTypeIds);
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
	/**
	* @param Long activityMemberId
	* @param Long stateId
	* @param String fromDateStr
	* @param String toDateStr
	* @param  List<Long> partyMeetingTypeValues
	* @return List<PartyMeetingsVO>
	* @author Santosh 
	* @Description :This Service Method is used to get Meeting conducted,not conducted and may be count details by user access level. 
	*  @since 9-AUGUST-2016
	*/
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
		    				 mergeRequiredData(mandalVO,townMap.get(locationId)," "); 
		    			 }
		    		 }
		    		 if(divisionMap != null){
		    			 if(divisionMap.get(locationId) != null){
		    				 mergeRequiredData(mandalVO,divisionMap.get(locationId)," "); 
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
			    				 mergeRequiredData(townVO,divisionMap.get(locationId)," "); 
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
			    				 mergeRequiredData(villageVO,wardMap.get(locationId)," "); 
			    			 }
			    		 } 
				  }
			  }else{
				  if(wardMap != null && wardMap.size() > 0){
					  villageWardMap.putAll(wardMap);  
				  }
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
		      if(villageWardMap != null && villageWardMap.size()  > 0){
		    	    PartyMeetingsVO villageWardVO = new PartyMeetingsVO();
				    villageWardVO.setName("Village/Ward");
				    villageWardVO.getPartyMettingsVOList().addAll(villageWardMap.values());  
				    resultList.add(villageWardVO);
			   }
			   if(mandalTwnDivisionMap != null && mandalTwnDivisionMap.size()  > 0){
				   PartyMeetingsVO manDalTwnDivVO = new PartyMeetingsVO();
				   manDalTwnDivVO.setName("Mandal/Town/Division");
				   manDalTwnDivVO.getPartyMettingsVOList().addAll(mandalTwnDivisionMap.values());   
				   resultList.add(manDalTwnDivVO);
			   }
			   
			     PartyMeetingsVO stateVO = new PartyMeetingsVO();
			   if(meetingsDtlsMap.get(1l) != null && meetingsDtlsMap.get(1l).size() > 0){
			        stateVO.setName("State");
			    	stateVO.getPartyMettingsVOList().addAll(meetingsDtlsMap.get(1l).values()); 	
			    }
			   
		  	    PartyMeetingsVO districtVO = new PartyMeetingsVO();
			    if(meetingsDtlsMap.get(2l) != null && meetingsDtlsMap.get(2l).size() > 0){
			  	      districtVO.setName("District");
					  districtVO.getPartyMettingsVOList().addAll(meetingsDtlsMap.get(2l).values()); 	
			    }
			    
			    PartyMeetingsVO constituencyVO = new PartyMeetingsVO();
			    if(meetingsDtlsMap.get(3l) != null && meetingsDtlsMap.get(3l).size() > 0){
			    	constituencyVO.setName("Constituency");
			    	constituencyVO.getPartyMettingsVOList().addAll(meetingsDtlsMap.get(3l).values()); 	
			    }
			   
			   
		       if(userAccessLevelId.longValue()==IConstants.COUNTRY_LEVEl_ACCESS_ID || userAccessLevelId.longValue()==IConstants.STATE_LEVEl_ACCESS_ID){
		    	   if(locationValues != null && locationValues.size()==1){
		    		   if(constituencyVO.getPartyMettingsVOList() != null && constituencyVO.getPartyMettingsVOList().size() > 0){
		    			   resultList.add(constituencyVO);   
		    		   }
		    		   if(districtVO.getPartyMettingsVOList() != null && districtVO.getPartyMettingsVOList().size() > 0){
		    			   resultList.add(districtVO);
				       }
		    	   }else{
		    		   if(stateVO.getPartyMettingsVOList() != null && stateVO.getPartyMettingsVOList().size() > 0){
		    			   resultList.add(stateVO);
					   }
		    		   if(constituencyVO.getPartyMettingsVOList() != null && constituencyVO.getPartyMettingsVOList().size() > 0){
		    			   resultList.add(constituencyVO);
					   }
		    		   if(districtVO.getPartyMettingsVOList() != null && districtVO.getPartyMettingsVOList().size() > 0){
		    		 	   resultList.add(districtVO);   
		   		     }
			      }
		       }else if(userAccessLevelId.longValue()==IConstants.DISTRICT_LEVEl_ACCESS_ID){
		    	   if(locationValues != null && locationValues.size()==1){
		    		   if(constituencyVO.getPartyMettingsVOList() != null && constituencyVO.getPartyMettingsVOList().size() > 0){
		    			   resultList.add(constituencyVO);
		    		   }
		           }else{
		        	   if(constituencyVO.getPartyMettingsVOList() != null && constituencyVO.getPartyMettingsVOList().size() > 0){
		    			   resultList.add(constituencyVO);   
		    		   }
		    		   if(districtVO.getPartyMettingsVOList() != null && districtVO.getPartyMettingsVOList().size() > 0){
		    			   resultList.add(districtVO);
				       }
		           }  
		    	   }
		       else if(userAccessLevelId.longValue()==IConstants.ASSEMBLY_LEVEl_ACCESS_ID){
		    	   if(locationValues != null && locationValues.size()==1 ){
			        }else{
			        	  if(constituencyVO.getPartyMettingsVOList() != null && constituencyVO.getPartyMettingsVOList().size() > 0){
			    			   resultList.add(constituencyVO);
			    		   }
			        }  
		       }
		         if(userAccessLevelId.longValue()==IConstants.PARLIAMENT_LEVEl_ACCESS_ID ){
		         	  if(constituencyVO.getPartyMettingsVOList() != null && constituencyVO.getPartyMettingsVOList().size() > 0){
		    			   resultList.add(constituencyVO);
		    		   }
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
		 
		 List<Object[]> partyMeetingsSessionWiseAttendanceDetsils = partyMeetingAttendanceDAO.getSpecialMeetingsSessionWiseAttendence();
		 Map<Long,Map<Long,Map<String,Set<Long>>>> meetingsMap = new HashMap<Long,Map<Long,Map<String,Set<Long>>>>(0);
		 Map<Long, Map<String,Set<Long>>> meetingWiseLateAbsentDetailsMap = new HashMap<Long,Map<String, Set<Long>>>(0);
		 List<Long> partyMeetingIdsLsit = new ArrayList<Long>(0);
		 if(commonMethodsUtilService.isListOrSetValid(partyMeetingsSessionWiseAttendanceDetsils)){
			 for (Object[] param : partyMeetingsSessionWiseAttendanceDetsils) {
				Long partyMeetingId =commonMethodsUtilService.getLongValueForObject(param[1]);
				partyMeetingIdsLsit.add(partyMeetingId);
			 }
		 }
		 
		 List<Object[]> inviteesForPartyMeetingsDetls = partyMeetingInviteeDAO.getInviteesForPartyMeetings(partyMeetingIdsLsit);
		 Map<Long,Set<Long>> partyMeetingInviteesMap = new HashMap<Long, Set<Long>>(0);
		 if(commonMethodsUtilService.isListOrSetValid(inviteesForPartyMeetingsDetls)){
			 for (Object[] param : inviteesForPartyMeetingsDetls) {
				 Long partyMeetingId = commonMethodsUtilService.getLongValueForObject(param[0]);
				 Long tdpCadreId = commonMethodsUtilService.getLongValueForObject(param[1]);
				 Set<Long> tdpCadreList = new HashSet<Long>(0);
				 if(partyMeetingInviteesMap.get(partyMeetingId) != null){
					 tdpCadreList = partyMeetingInviteesMap.get(partyMeetingId);
				 }
				 tdpCadreList.add(tdpCadreId);
				 partyMeetingInviteesMap.put(partyMeetingId, tdpCadreList);
			}
		 }
		 
		 if(commonMethodsUtilService.isListOrSetValid(partyMeetingsSessionWiseAttendanceDetsils)){
			 for (Object[] param : partyMeetingsSessionWiseAttendanceDetsils) {
				//Long typeId =commonMethodsUtilService.getLongValueForObject(param[0]);
				Long partyMeetingId =commonMethodsUtilService.getLongValueForObject(param[1]);
				Long sessionId =commonMethodsUtilService.getLongValueForObject(param[2]);
				Long tdpCadreId =commonMethodsUtilService.getLongValueForObject(param[4]);
				String datetime  =commonMethodsUtilService.getStringValueForObject(param[5]);
				String attendedTime  =commonMethodsUtilService.getStringValueForObject(param[6]);
				String lateTime  =commonMethodsUtilService.getStringValueForObject(param[7]);
				String defaultLateTime  =commonMethodsUtilService.getStringValueForObject(param[8]);
				String finalLateTime = lateTime;
				if(lateTime.trim().length() ==0)
					finalLateTime = defaultLateTime;
				
				Map<Long,Map<String,Set<Long>>> sessionWiseMap = new HashMap<Long,Map<String,Set<Long>>>(0);
				Map<String,Set<Long>> statuswiseTdpCadresMap = new HashMap<String, Set<Long>>(0);
				Map<String,Set<Long>> statuswisePartyMeetingTdpCadresMap = new HashMap<String, Set<Long>>(0);
				statuswiseTdpCadresMap.put("TOTAL_ATTENDED", new HashSet<Long>(0));
				statuswiseTdpCadresMap.put("LATE", new HashSet<Long>(0));
				statuswiseTdpCadresMap.put("ABSENT",new HashSet<Long>(0));
				 
				statuswisePartyMeetingTdpCadresMap.put("TOTAL_ATTENDED", new HashSet<Long>(0));
				statuswisePartyMeetingTdpCadresMap.put("LATE", new HashSet<Long>(0));
				statuswisePartyMeetingTdpCadresMap.put("ABSENT",new HashSet<Long>(0));
				
				if(meetingWiseLateAbsentDetailsMap.get(partyMeetingId) != null){
					statuswisePartyMeetingTdpCadresMap = meetingWiseLateAbsentDetailsMap.get(partyMeetingId);
				}
				else{
					Set<Long> inviteeIdsList = new HashSet<Long>(0);
					for (Long tdpCadrId : partyMeetingInviteesMap.get(partyMeetingId)) {
						inviteeIdsList.add(tdpCadrId);
					}
					statuswisePartyMeetingTdpCadresMap.put("ABSENT",inviteeIdsList);
				}
				if(meetingsMap.get(partyMeetingId) != null){
					sessionWiseMap = meetingsMap.get(partyMeetingId);
					if(commonMethodsUtilService.isMapValid(sessionWiseMap) && sessionWiseMap.get(sessionId) != null){
						statuswiseTdpCadresMap = sessionWiseMap.get(sessionId);
					}
				}
				
				
				if(sessionWiseMap.get(sessionId) == null || sessionWiseMap.get(sessionId).size()==0){
					Set<Long> inviteeIdsList = new HashSet<Long>(0);
					for (Long tdpCadrId : partyMeetingInviteesMap.get(partyMeetingId)) {
						inviteeIdsList.add(tdpCadrId);
					}
					statuswiseTdpCadresMap.put("ABSENT",inviteeIdsList);
				}
				
				if(commonMethodsUtilService.isMapValid(statuswiseTdpCadresMap)){
					Set<Long> cadreidsList1  = statuswiseTdpCadresMap.get("TOTAL_ATTENDED");
					if(!commonMethodsUtilService.isListOrSetValid(cadreidsList1)){
						cadreidsList1 = new HashSet<Long>(0);
					}
					
					cadreidsList1.add(tdpCadreId);
					statuswiseTdpCadresMap.put("TOTAL_ATTENDED", cadreidsList1);
					statuswisePartyMeetingTdpCadresMap.put("TOTAL_ATTENDED", cadreidsList1);
					//statuswisePartyMeetingTdpCadresMap.put("ABSENT",inviteeIdsList);
					 if(attendedTime.compareTo(finalLateTime)>=0){
						 Set<Long> cadreidsList = statuswiseTdpCadresMap.get("LATE");
							if(!commonMethodsUtilService.isListOrSetValid(cadreidsList)){
								cadreidsList = new HashSet<Long>(0);
							}
							if(partyMeetingInviteesMap.get(partyMeetingId).contains(tdpCadreId)){
								cadreidsList.add(tdpCadreId);
								statuswiseTdpCadresMap.put("LATE", cadreidsList);
							}
							
							cadreidsList1 = statuswisePartyMeetingTdpCadresMap.get("LATE");
							if(!commonMethodsUtilService.isListOrSetValid(cadreidsList1)){
								cadreidsList1 = new HashSet<Long>(0);
							}
							if(partyMeetingInviteesMap.get(partyMeetingId).contains(tdpCadreId)){
								cadreidsList1.add(tdpCadreId);
								statuswisePartyMeetingTdpCadresMap.put("LATE", cadreidsList1);
							}
		              } 
					
					Set<Long> cadreidsList = statuswiseTdpCadresMap.get("ABSENT");
					if(!commonMethodsUtilService.isListOrSetValid(cadreidsList)){
						cadreidsList = new HashSet<Long>(0);
					}
					if(cadreidsList.contains(tdpCadreId)){
						cadreidsList.remove(tdpCadreId);
						statuswiseTdpCadresMap.put("ABSENT", cadreidsList);
					}
					
					cadreidsList1 = statuswisePartyMeetingTdpCadresMap.get("ABSENT");
					if(cadreidsList1.contains(tdpCadreId)){
						cadreidsList1.remove(tdpCadreId);
						statuswisePartyMeetingTdpCadresMap.put("ABSENT", cadreidsList1);
					}
				}
				
				meetingWiseLateAbsentDetailsMap.put(partyMeetingId, statuswisePartyMeetingTdpCadresMap);
				sessionWiseMap.put(sessionId, statuswiseTdpCadresMap);
				meetingsMap.put(partyMeetingId, sessionWiseMap);
			}
		 }
		 
		 List<Object[]> partySessionsInfo = partyMeetingSessionDAO.getSessionDetailsForPartiMeetings(new HashSet<Long>(meetingTypeVOMap.keySet()));
		 if(commonMethodsUtilService.isListOrSetValid(partySessionsInfo) && commonMethodsUtilService.isMapValid(meetingTypeVOMap)){
			 
			 for (Object[] param : partySessionsInfo) {
				 Long partyMeetingTypeId = commonMethodsUtilService.getLongValueForObject(param[10]);
				 Long partyMeetingId = commonMethodsUtilService.getLongValueForObject(param[0]);
				 
				 PartyMeetingsDataVO partyMetingMainVO = null;
				if(meetingTypeVOMap.get(partyMeetingTypeId) != null){
					PartyMeetingsDataVO meetingTipeVO = meetingTypeVOMap.get(partyMeetingTypeId) ;
					List<PartyMeetingsDataVO> sessionList = null;
					if(meetingTipeVO != null){
						List<PartyMeetingsDataVO> partyMeetingVOList = meetingTipeVO.getSubList1();
						if(partyMeetingVOList == null || partyMeetingVOList.size()==0){
							partyMeetingVOList = new ArrayList<PartyMeetingsDataVO>(0);
							meetingTipeVO.setSubList1(partyMeetingVOList);
						}
						else{
							for (PartyMeetingsDataVO vo : partyMeetingVOList) {
								if(vo.getId() != null && vo.getId().longValue()>0L && vo.getId().longValue()==partyMeetingId.longValue()){
									sessionList = vo.getSubList1();
									partyMetingMainVO = vo;
								}
							}
						}
						
						if(sessionList == null || sessionList.size() ==0){
							PartyMeetingsDataVO meetingVO = new PartyMeetingsDataVO();
							meetingVO.setId(partyMeetingId);
							meetingVO.setName(commonMethodsUtilService.getStringValueForObject(param[11]));
							partyMeetingVOList.add(meetingVO);
							if(sessionList == null){
								sessionList = new ArrayList<PartyMeetingsDataVO>(0);
								meetingVO.setSubList1(sessionList);
							}
							partyMetingMainVO= meetingVO;
						}

						PartyMeetingsDataVO sessionVO = new PartyMeetingsDataVO();
						sessionVO.setId(commonMethodsUtilService.getLongValueForObject(param[1]));
						sessionVO.setName(commonMethodsUtilService.getStringValueForObject(param[2]));
						sessionVO.setStartTime(commonMethodsUtilService.getStringValueForObject(param[4]));
						sessionVO.setEndTime(commonMethodsUtilService.getStringValueForObject(param[5]));
						sessionVO.setLateTime(commonMethodsUtilService.getStringValueForObject(param[6]));
						
						if(sessionVO.getStartTime() == null || sessionVO.getStartTime().trim().length()==0){
							sessionVO.setStartTime(commonMethodsUtilService.getStringValueForObject(param[7]));
						}
						if(sessionVO.getEndTime() == null || sessionVO.getEndTime().trim().length()==0){
							sessionVO.setEndTime(commonMethodsUtilService.getStringValueForObject(param[8]));
						}
						if(sessionVO.getLateTime() == null || sessionVO.getLateTime().trim().length()==0){
							sessionVO.setLateTime(commonMethodsUtilService.getStringValueForObject(param[9]));
						}
						
						Long totalInviteesCount =0L;
						Set<Long> inviteesIdsList = partyMeetingInviteesMap.get(partyMetingMainVO.getId());
						if(commonMethodsUtilService.isListOrSetValid(inviteesList)){
							totalInviteesCount = Long.valueOf(String.valueOf(inviteesIdsList.size()));
						}
						partyMetingMainVO.setInvitedCount(totalInviteesCount);
						Map<Long,Map<String,Set<Long>>> sessionWiseMap  = meetingsMap.get(partyMeetingId);
						if(commonMethodsUtilService.isMapValid(sessionWiseMap)){
							Map<String,Set<Long>> statuswiseTdpCadresMap = sessionWiseMap.get(sessionVO.getId());
							if(commonMethodsUtilService.isMapValid(statuswiseTdpCadresMap)){
								for (String type : statuswiseTdpCadresMap.keySet()) {
									Set<Long> cadreidsList = statuswiseTdpCadresMap.get(type);
									if(type.trim().equalsIgnoreCase("TOTAL_ATTENDED") && commonMethodsUtilService.isListOrSetValid(cadreidsList)){
										sessionVO.setAttendedCount(Long.valueOf(String.valueOf(cadreidsList.size())));
										float aPerc =  (float) (sessionVO.getAttendedCount() *100.0/totalInviteesCount);
										String perc = commonMethodsUtilService.roundTo2DigitsFloatValueAsString(aPerc);
										sessionVO.setAttendedPerc(Double.valueOf(perc));
									}else if(type.trim().equalsIgnoreCase("LATE")){
										sessionVO.setLateAttendedCount(Long.valueOf(String.valueOf(cadreidsList.size())));
										float laPerc =  (float) (sessionVO.getLateAttendedCount() *100.0/totalInviteesCount);
										String perc1 = commonMethodsUtilService.roundTo2DigitsFloatValueAsString(laPerc);
										sessionVO.setLateattendedPerc(Double.valueOf(perc1));
									}else if(type.trim().equalsIgnoreCase("ABSENT")){
										sessionVO.setNotAttendedCount(Long.valueOf(String.valueOf(cadreidsList.size())));
										//sessionVO.setNotAttendedCount(totalInviteesCount - (sessionVO.getAttendedCount()+sessionVO.getLateAttendedCount()));
										float naPerc =  (float) (sessionVO.getNotAttendedCount() *100.0/totalInviteesCount);
										String perc2 = commonMethodsUtilService.roundTo2DigitsFloatValueAsString(naPerc);
										sessionVO.setNotAttendedPerc(Double.valueOf(perc2));
									}
								}
							}
						}
						
						Map<String,Set<Long>> meetingsWiseMap  = meetingWiseLateAbsentDetailsMap.get(partyMeetingId);
						if(commonMethodsUtilService.isMapValid(meetingsWiseMap)){
							for (String type : meetingsWiseMap.keySet()) {
								Set<Long> cadreidsList = meetingsWiseMap.get(type);
								
								if(type.trim().equalsIgnoreCase("TOTAL_ATTENDED") && commonMethodsUtilService.isListOrSetValid(cadreidsList)){
									partyMetingMainVO.setAttendedCount(Long.valueOf(String.valueOf(cadreidsList.size())));
									float aPerc =  (float) (partyMetingMainVO.getAttendedCount() *100.0/totalInviteesCount);
									String perc = commonMethodsUtilService.roundTo2DigitsFloatValueAsString(aPerc);
									partyMetingMainVO.setAttendedPerc(Double.valueOf(perc));
								}else if(type.trim().equalsIgnoreCase("LATE")){
									partyMetingMainVO.setLateAttendedCount(Long.valueOf(String.valueOf(cadreidsList.size())));
									float laPerc =  (float) (partyMetingMainVO.getLateAttendedCount() *100.0/totalInviteesCount);
									String perc1 = commonMethodsUtilService.roundTo2DigitsFloatValueAsString(laPerc);
									partyMetingMainVO.setLateattendedPerc(Double.valueOf(perc1));
								}else if(type.trim().equalsIgnoreCase("ABSENT")){
									partyMetingMainVO.setNotAttendedCount(Long.valueOf(String.valueOf(cadreidsList.size())));
									//sessionVO.setNotAttendedCount(totalInviteesCount - (sessionVO.getAttendedCount()+sessionVO.getLateAttendedCount()));
									float naPerc =  (float) (partyMetingMainVO.getNotAttendedCount() *100.0/totalInviteesCount);
									String perc2 = commonMethodsUtilService.roundTo2DigitsFloatValueAsString(naPerc);
									partyMetingMainVO.setNotAttendedPerc(Double.valueOf(perc2));
								}
							}
						}
						
						if(totalInviteesCount.longValue()>0L){
							partyMetingMainVO.setNotAttendedCount(totalInviteesCount-partyMetingMainVO.getAttendedCount());
							float naPerc =  (float) (partyMetingMainVO.getNotAttendedCount() *100.0/totalInviteesCount);
							String perc2 = commonMethodsUtilService.roundTo2DigitsFloatValueAsString(naPerc);
							partyMetingMainVO.setNotAttendedPerc(Double.valueOf(perc2));
						}
						/*partyMetingMainVO.setAttendedCount(partyMetingMainVO.getAttendedCount()+sessionVO.getAttendedCount());
						partyMetingMainVO.setLateAttendedCount(partyMetingMainVO.getLateAttendedCount()+sessionVO.getLateAttendedCount());
						partyMetingMainVO.setNotAttendedCount(partyMetingMainVO.getNotAttendedCount()+sessionVO.getNotAttendedCount());
						//partyMetingMainVO.setNotAttendedCount(totalInviteesCount - (sessionVO.getAttendedCount()+sessionVO.getLateAttendedCount()));
						
						if(totalInviteesCount.longValue()>0L){
							float aPerc =  (float) (partyMetingMainVO.getAttendedCount() *100.0/totalInviteesCount);
							String perc = commonMethodsUtilService.roundTo2DigitsFloatValueAsString(aPerc);
							partyMetingMainVO.setAttendedPerc(Double.valueOf(perc));
							
							float laPerc =  (float) (partyMetingMainVO.getLateAttendedCount() *100.0/totalInviteesCount);
							String perc1 = commonMethodsUtilService.roundTo2DigitsFloatValueAsString(laPerc);
							partyMetingMainVO.setLateattendedPerc(Double.valueOf(perc1));
							
							float naPerc =  (float) (partyMetingMainVO.getNotAttendedCount() *100.0/totalInviteesCount);
							String perc2 = commonMethodsUtilService.roundTo2DigitsFloatValueAsString(naPerc);
							partyMetingMainVO.setNotAttendedPerc(Double.valueOf(perc2));
						}
						*/
						
						
						
						sessionList.add(sessionVO);
					}
				}
			}
		 }
		 
		 if(meetingTypeVOMap != null && meetingTypeVOMap.size() > 0 ){
			 
			 for(Long partyMeetingTypeId : meetingTypeVOMap.keySet()){
				 PartyMeetingsDataVO meetingTypeVO = meetingTypeVOMap.get(partyMeetingTypeId);
				 if(meetingTypeVO.getInvitedCount()!=null && meetingTypeVO.getInvitedCount() > 0l) {
					 
					 List<PartyMeetingsDataVO> partyMetingsList = meetingTypeVO.getSubList1();
					 Long attendedCount = 0L;
					 Long lateCount = 0L;
					 Long absentCount = 0L;
					 Long invitedCount = 0L;
					 if(commonMethodsUtilService.isListOrSetValid(partyMetingsList)){
						 for (PartyMeetingsDataVO vo : partyMetingsList) {
							 attendedCount = attendedCount+vo.getAttendedCount();
							 lateCount = lateCount+vo.getLateAttendedCount();
							 absentCount = absentCount+vo.getNotAttendedCount();
							 invitedCount = invitedCount+vo.getInvitedCount();
						}
						 
						 if(lateCount != null && lateCount.longValue()>0L){
							 float aPerc =  (float) (lateCount *100.0/meetingTypeVO.getInvitedCount());
								String perc = commonMethodsUtilService.roundTo2DigitsFloatValueAsString(aPerc);
								meetingTypeVO.setLateattendedPerc(Double.valueOf(perc));
						 }
					 }
					 
					 meetingTypeVO.setInvitedCount(invitedCount);
					 meetingTypeVO.setAttendedCount(attendedCount);
					 meetingTypeVO.setNotAttendedCount(absentCount);
					 meetingTypeVO.setLateAttendedCount(lateCount);
					 
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

/*
public void  calculateLateAttendedCadreData(List<Object[]> objList,Map<Long,Map<Long,Set<Long>>> lateAttendedCadreMap,Map<Long,Map<Long,String>> meetingSessionTimeMap,Long meetingId){
    SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
    if(objList != null && objList.size() > 0){
      for(Object[] param:objList){
        Map<Long,Set<Long>> attendedMap = lateAttendedCadreMap.get(commonMethodsUtilService.getLongValueForObject(param[0]));
         if(attendedMap == null){
           attendedMap = new HashMap<Long, Set<Long>>();
           lateAttendedCadreMap.put(commonMethodsUtilService.getLongValueForObject(param[0]), attendedMap);
         }
         Set<Long> tdpCadreIds = attendedMap.get(commonMethodsUtilService.getLongValueForObject(param[2]));
          if(tdpCadreIds == null ){
            tdpCadreIds = new HashSet<Long>(); 
            attendedMap.put(commonMethodsUtilService.getLongValueForObject(param[2]), tdpCadreIds);
          }
          try{
                Date attendedTime = sdf.parse(commonMethodsUtilService.getStringValueForObject(param[4]));
              Date sessiontTime = sdf.parse(meetingSessionTimeMap.get(meetingId).get(commonMethodsUtilService.getLongValueForObject(param[2])));
              if(attendedTime.compareTo(sessiontTime)>=0){
                 tdpCadreIds.add(commonMethodsUtilService.getLongValueForObject(param[3]));  
              }  
          }catch(Exception e){
             e.printStackTrace();
          }
      }
    }
  }
*/

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
		  if(othersVO.getAttendedCount() != null && othersVO.getAttendedCount() > 0l){
			  othersVO.setAttendedPerc(100.0);
		  }
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
			   if(dataVO.getAttendedCount() != null && dataVO.getAttendedCount() > 0l){
				   dataVO.setAttendedPerc(100.0);
			   }
		   }
	 }
	 
 }

}
/**
* @param  Long partyMeetingMainTypeId
* @param  List<Long> partyMeetingTypeIds
* @param String fromDateStr
* @param String toDateStr
* @param Long stateId
* @return  List<PartyMeetingsDataVO>
* @author Santosh 
* @Description :This Service Method is used to get state level meeting invited and absent count.. 
*  @since 11-AUGUST-2016
*/
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
		 String status = "count"; 
		 List<Object[]> inviteesList = partyMeetingInviteeDAO.getDistrictWiseInvitedCountForPartyMeetingTypeIds(inputVO,status);
		 List<Object[]> invitteeAttendedList = partyMeetingInviteeDAO.getDistrictWiseInvitteeAttendedCountForPartyMeetingTypeIds(inputVO,status);
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
						  }
					}
				  }
			}
	     }
	     if(inviteeCadresMap != null && inviteeCadresMap.size() > 0){
	    	 for(Entry<Long,List<PartyMeetingsDataVO>> entry:inviteeCadresMap.entrySet()){
	    		 if(entry.getValue() != null && entry.getValue().size() > 0){
	    			 for(PartyMeetingsDataVO inviteeVO : entry.getValue()){
	    				 inviteeVO.setNotAttendedCount(inviteeVO.getInvitedCount()-inviteeVO.getInvitteeAttendedCount()); 
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

/**
 *  @author <a href="mailto:sreedhar.itgrids.hyd@gmail.com">SREEDHAR</a>
 *  This Service Method is used to push details to intermediate table 'party_meeting_status'. 
 *  @since 13-SEPTEMBER-2016
 */
 public ResultStatus insertDataInToPartyMeetingStatusTable(){
	 
	 ResultStatus resultStatus = new ResultStatus();
	 try{
		 
		 transactionTemplate.execute(new TransactionCallbackWithoutResult() {
		      public void doInTransactionWithoutResult(TransactionStatus status) {
		    	  
		    	 int deletedRecords = partyMeetingStatusDAO.deleteAllRecords();
		 		 
		    	 //int count = partyMeetingStatusDAO.setPrimaryKeyAutoIncrementToOne();
		    	 
		 		 int insertedRecordsCount = partyMeetingStatusDAO.insertPartyofficeAndIvrStatus();
		 		 
		 		 int updatedCount1= partyMeetingStatusDAO.updatePartyMeetingStatus1();
		 		 int updatedCount2= partyMeetingStatusDAO.updatePartyMeetingStatus2();
		 		 int updatedCount3= partyMeetingStatusDAO.updatePartyMeetingStatus3();
		 		 int updatedCount4= partyMeetingStatusDAO.updatePartyMeetingStatus4();
		 		 int updatedCount5= partyMeetingStatusDAO.updatePartyMeetingStatus5();
		 		 int updatedCount6= partyMeetingStatusDAO.updatePartyMeetingStatus6();
		 		 int updatedCount7= partyMeetingStatusDAO.updatePartyMeetingStatus7();
		 		 int updatedCount8= partyMeetingStatusDAO.updatePartyMeetingStatus8(); 
		 		 int updatedCount9= partyMeetingStatusDAO.updatePartyMeetingStatus9();
		 		 
		 		 Date currentDateTime = new DateUtilService().getCurrentDateAndTime();
		 		 int insertedTime = partyMeetingStatusDAO.setInsertedDate(currentDateTime);
		 		 
		 	     Log.debug(""+ deletedRecords +" - " +insertedRecordsCount +" - " +updatedCount1 + " - " +updatedCount2 +" - "
		 				   + updatedCount3 + " - " + updatedCount4 + " - " +updatedCount5 + " - " +updatedCount6 + " - " +updatedCount7
		 				   + "-" +updatedCount8 + " - "+updatedCount9 + " - "+insertedTime );
			  }
		 });
		 resultStatus.setResultCode(0);
		 resultStatus.setMessage("success");
		 
	}catch(Exception e){
		resultStatus.setResultCode(1);
		resultStatus.setMessage("failure");
		LOG.error("Exception raised in saveNewPublicRepresentativeDetails  method in CadreDetailsService.",e);
	}
	 return resultStatus;
 }
 //getParyMeetingTypeDetailsDistrictWise
 /*
  * Author : Swadhin
  * Description: This method will return details of the member who are attended or not attended in the meeting
  */
 public List<IdNameVO> getParyMeetingTypeDetailsPerDistrict(Long partyMeetingMainTypeId,List<Long> partyMeetingTypeIds,String state,String startDateString, String endDateString, Long distId){
	 LOG.info("Control entered into getParyMeetingTypeDetailsPerDistrict  method in CoreDashboardPartyMeetingService");
	 try{
		 IdNameVO idNameVO = null;     
		 Long cadreId = null;
		 Map<Long,IdNameVO> idAndMemberDtlsMap = new HashMap<Long,IdNameVO>();
		 List<IdNameVO> idNameVOs = new ArrayList<IdNameVO>();
		//creating inputVO
		PartyMeetingsInputVO inputVO = new PartyMeetingsInputVO();
			
		inputVO.setPartyMeetingMainTypeId(partyMeetingMainTypeId);
		inputVO.setPartyMeetingTypeIds(partyMeetingTypeIds);
		List<Date> datesList = coreDashboardGenericService.getDates(startDateString, endDateString, new SimpleDateFormat("dd/MM/yyyy"));
		inputVO.setStartDate(datesList.get(0));
		inputVO.setEndDate(datesList.get(1));
		Long stateId = coreDashboardGenericService.getStateIdByState(state);
		inputVO.setStateId(stateId);
		inputVO.setDistId(distId);
		String status = "details";  
		
		Set<Long> absentCadreIds = new HashSet<Long>(0);
		Set<Long> attendedCadreIds = new HashSet<Long>(0);
		Map<Long,String> memberAndMeetingNameMap = new HashMap<Long,String>(0);
		
		List<Object[]> inviteesList = partyMeetingInviteeDAO.getDistrictWiseInvitedCountForPartyMeetingTypeIds(inputVO,status);
		if(inviteesList != null && inviteesList.size() > 0){
			for(Object[] obj : inviteesList){
				String cadreIdStr = (obj[4].toString()).substring(7).trim();
				absentCadreIds.add(Long.valueOf(cadreIdStr));        
			}
		}  
		List<Object[]> invitteeAttendedList = partyMeetingInviteeDAO.getDistrictWiseInvitteeAttendedCountForPartyMeetingTypeIds(inputVO,status);
		if(invitteeAttendedList != null && invitteeAttendedList.size() > 0){
			for(Object[] obj : invitteeAttendedList){
				String caderIdStr = (obj[4].toString()).substring(7).trim();
				attendedCadreIds.add(Long.valueOf(caderIdStr)); 
				String meetings = memberAndMeetingNameMap.get(Long.valueOf(caderIdStr));
				if(meetings != null){
					meetings = meetings+","+obj[5].toString();
					memberAndMeetingNameMap.put(Long.valueOf(caderIdStr), meetings);
				}else{
					memberAndMeetingNameMap.put(Long.valueOf(caderIdStr), obj[5].toString());
				}
			}
		}
		absentCadreIds.removeAll(attendedCadreIds);
		if(attendedCadreIds.size() > 0){
			List<Object[]> destWiseAttendedMembersDesignation = trainingCampAttendanceDAO.getMembersDetails(new ArrayList<Long>(attendedCadreIds)); 
			if(destWiseAttendedMembersDesignation != null && destWiseAttendedMembersDesignation.size() > 0){  
				for(Object[] obj : destWiseAttendedMembersDesignation){
					cadreId = obj[0] != null ? (Long)obj[0] : 0l;
					idNameVO = idAndMemberDtlsMap.get(cadreId);
					if(idNameVO != null){
						String status1 = idNameVO.getStatus();
						if(obj[2] != null){
							status1 = status1+","+obj[2].toString();
							idNameVO.setStatus(status1);
							idAndMemberDtlsMap.put(cadreId, idNameVO);
						}else{
							if(obj[3] != null){
								status1 = status1+","+(obj[4] != null ? obj[4].toString() : "")+" "+(obj[3] != null ? obj[3].toString() : "");
								idNameVO.setStatus(status1);
								idAndMemberDtlsMap.put(cadreId, idNameVO);
							}
						}
						
					}else{
						idNameVO = new IdNameVO();
						idNameVO.setName(obj[1] != null ? obj[1].toString() : "");
						if(obj[2] != null){
							idNameVO.setStatus(obj[2].toString());
						}else if(obj[3] != null){
							idNameVO.setStatus((obj[4] != null ? obj[4].toString() : "")+" "+(obj[3] != null ? obj[3].toString() : ""));
						}else{
							idNameVO.setStatus("");
						}
						idNameVO.setMobileNo(obj[5] != null ? obj[5].toString() : "");
						idNameVO.setWish("attended");
						idAndMemberDtlsMap.put(cadreId, idNameVO); 
					}
				}
			}
		}
		if(idAndMemberDtlsMap.size() > 0){
			for(Entry<Long,IdNameVO> entry : idAndMemberDtlsMap.entrySet()){
				Long id = entry.getKey();
				String meeting = memberAndMeetingNameMap.get(id);
				if(meeting !=null){
					IdNameVO idNameVO2 = entry.getValue();  
					idNameVO2.setApplicationStatus(meeting);
				}
			}
		}  
		if(idAndMemberDtlsMap.size() > 0){
			idNameVOs = new ArrayList<IdNameVO>(idAndMemberDtlsMap.values());
			idAndMemberDtlsMap.clear();
		}
		if(absentCadreIds.size() > 0){
			List<Object[]> destWiseAbsaentMembersDesignation = trainingCampAttendanceDAO.getMembersDetails(new ArrayList<Long>(absentCadreIds));  
			if(destWiseAbsaentMembersDesignation != null && destWiseAbsaentMembersDesignation.size() > 0){
				for(Object[] obj : destWiseAbsaentMembersDesignation){
					cadreId = obj[0] != null ? (Long)obj[0] : 0l;
					idNameVO = idAndMemberDtlsMap.get(cadreId);
					if(idNameVO != null){
						String status1 = idNameVO.getStatus();
						if(obj[2] != null){
							status1 = status1+","+obj[2].toString();  
							idNameVO.setStatus(status1);
							idAndMemberDtlsMap.put(cadreId, idNameVO);
						}else{
							if(obj[3] != null){
								status1 = status1+","+(obj[4] != null ? obj[4].toString() : "")+" "+(obj[3] != null ? obj[3].toString() : "");
								idNameVO.setStatus(status1);
								idAndMemberDtlsMap.put(cadreId, idNameVO);
							}
						}
						
					}else{
						idNameVO = new IdNameVO();
						idNameVO.setName(obj[1] != null ? obj[1].toString() : "");
						if(obj[2] != null){
							idNameVO.setStatus(obj[2].toString());
						}
						else if(obj[3] != null){
							idNameVO.setStatus((obj[4] != null ? obj[4].toString() : "")+" "+(obj[3] != null ? obj[3].toString() : ""));
						}else{
							idNameVO.setStatus("");
						}
						idNameVO.setMobileNo(obj[5] != null ? obj[5].toString() : "");
						idNameVO.setWish("absent");
						idAndMemberDtlsMap.put(cadreId, idNameVO); 
					}
				}
			}
		}  
		if(idAndMemberDtlsMap.size() > 0){
			idNameVOs.addAll(new ArrayList<IdNameVO>(idAndMemberDtlsMap.values()));
		}
		return idNameVOs;  
	 }catch(Exception e){
		 e.printStackTrace();
		LOG.error("Exception raised in getParyMeetingTypeDetailsPerDistrict  method in CadreDetailsService.",e);

	 }
	 return null;
 }

 
 public String getMeetingRecentTime(){
	  String lastUpdatedTimeStr="";
	  SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm a ");
	  try{
		  Date lastUpdatedTime = partyMeetingStatusDAO.getMeetingLastUpdatedTime();
		  if(lastUpdatedTime != null){
			  lastUpdatedTimeStr= sdf.format(lastUpdatedTime);
		  }
	  }catch(Exception e){
		  LOG.error("Error occured at getMeetingRecentTime() in CoreDashboardPartyMeetingService {}",e);  
	  }
	  return lastUpdatedTimeStr;
}
	/**
	* @param  Long activityMemberId
	* @param  Long stateId
	* @param String fromDateStr
	* @param String toDateStr
	* @param  List<Long> partyMeetingTypeValues
	* @Param String meetingStatus
	* @param String partyMeetingLevel
	* @param String isComment
	* @param Long locationId
	* @param String locationType
	* @return List<PartyMeetingsVO>
	* @author Santosh 
	* @Description :This method is used to get Meeting comment details. 
	* @since 28-SEP-2016
	*/
 public List<PartyMeetingsVO> getPartyMeetingCommentsDetails(Long activityMemberId,Long stateId,String fromDateStr,String toDateStr,List<Long> partyMeetingTypeValues,String meetingStatus,String partyMeetingLevel,String isComment,Long locationId,String locationType){
	 
	     List<PartyMeetingsVO> resultList = new ArrayList<PartyMeetingsVO>();
	     Map<Long,Set<Long>> locationAccessLevelMap = new HashMap<Long, Set<Long>>();
	     List<Long> partyMeetingLevelIdsList = new ArrayList<Long>(0);
	     SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
	     Date fromDate=null;
		 Date toDate=null;
	 try{
		 if(fromDateStr != null && fromDateStr.trim().length()>0 && toDateStr!= null && toDateStr.trim().length()>0){
			 fromDate = sdf.parse(fromDateStr);
			 toDate = sdf.parse(toDateStr);
		 }
		 if(partyMeetingLevel != null && partyMeetingLevel.equalsIgnoreCase("State")){
			 partyMeetingLevelIdsList.add(1l); 
		 }else if(partyMeetingLevel.equalsIgnoreCase("District")){
			 partyMeetingLevelIdsList.add(2l); 
		 }else if(partyMeetingLevel.equalsIgnoreCase("Constituency")){
			 partyMeetingLevelIdsList.add(3l);
		 }else if(partyMeetingLevel.equalsIgnoreCase("Mandal/Town/Division")){
			 partyMeetingLevelIdsList.add(4l);
			 partyMeetingLevelIdsList.add(5l);
			 partyMeetingLevelIdsList.add(6l);
		 }else if(partyMeetingLevel.equalsIgnoreCase("Village/Ward")){
			 partyMeetingLevelIdsList.add(7l);
			 partyMeetingLevelIdsList.add(8l);
		 }
		
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
	    if(locationType.equalsIgnoreCase("Mandal")){
	    	String strLocationId = null;
			if(locationId != null && locationId>0){
				strLocationId = locationId.toString();
			}
			 Long firstDigit = Long.valueOf(strLocationId.substring(0, 1));
			 if(firstDigit.longValue()==1l){
				 locationId=Long.valueOf(strLocationId.substring(1, strLocationId.length()));	
				 locationType="Mandal";
			 }else if(firstDigit.longValue()==2l){
				 locationId=Long.valueOf(strLocationId.substring(1, strLocationId.length()));	
				 locationType="TownDivision";
	     }
	    }
	    if(locationAccessLevelMap != null && locationAccessLevelMap.size() > 0){
		   for(Entry<Long, Set<Long>> entry:locationAccessLevelMap.entrySet()){
			   List<Object[]> rtrnObjList = partyMeetingDAO.getPartyMeetingCommentsDtls(entry.getKey(),new ArrayList<Long>(entry.getValue()), stateId, fromDate, toDate, partyMeetingTypeValues, meetingStatus, partyMeetingLevelIdsList,isComment,locationId,locationType,partyMeetingLevel);
			   setMeetingCommentsDtla(rtrnObjList,resultList,partyMeetingLevel);
		   }
	   }
	  }catch(Exception e){
		 LOG.error("Error occured at getPartyMeetingCommentsDetails() in CoreDashboardPartyMeetingService {}",e); 
	 }
	 return resultList;
 }
 public void setMeetingCommentsDtla(List<Object[]> rtrnObjList,List<PartyMeetingsVO> resultList,String partyMeetingLevel){
	 try{
		 SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
		 if(rtrnObjList != null && rtrnObjList.size() > 0){
			 for(Object[] param:rtrnObjList){
				 PartyMeetingsVO meetingVO = new PartyMeetingsVO();
				 meetingVO.setId(commonMethodsUtilService.getLongValueForObject(param[0]));
				 meetingVO.setMeetingName(commonMethodsUtilService.getStringValueForObject(param[1]));
				 meetingVO.setMeetingTypeId(commonMethodsUtilService.getLongValueForObject(param[2]));
				 meetingVO.setMeetingType(commonMethodsUtilService.getStringValueForObject(param[3]));
				 meetingVO.setDistrictId(commonMethodsUtilService.getLongValueForObject(param[4]));
				 meetingVO.setDistrictName(commonMethodsUtilService.getStringValueForObject(param[5]));
				 if(param[6] != null && param[6].toString().trim().length() > 0 ){
				  meetingVO.setConductedDate(sdf.format(param[6]));
				 }
				  meetingVO.setRemarks(commonMethodsUtilService.getStringValueForObject(param[7]));
				  if(partyMeetingLevel != null && partyMeetingLevel.equalsIgnoreCase("Constituency")){
					meetingVO.setConstituencyId(commonMethodsUtilService.getLongValueForObject(param[8]));
					meetingVO.setConstituencyName(commonMethodsUtilService.getStringValueForObject(param[9]));
				  }
				 if(partyMeetingLevel != null && partyMeetingLevel.equalsIgnoreCase("Village/Ward") || partyMeetingLevel.equalsIgnoreCase("Mandal/Town/Division")){
					meetingVO.setConstituencyId(commonMethodsUtilService.getLongValueForObject(param[8]));
					meetingVO.setConstituencyName(commonMethodsUtilService.getStringValueForObject(param[9]));
				  if(param[10] != null){
					  Long mandalId=Long.valueOf("1"+param[10].toString());
					meetingVO.setMandalTwnDivisionId(mandalId);
					meetingVO.setMandalTwnDivision(commonMethodsUtilService.getStringValueForObject(param[11]));
				  }
				  if(param[12] != null ){
					Long twnDivisionId = Long.valueOf("2"+param[12].toString());
				   meetingVO.setMandalTwnDivisionId(twnDivisionId);
				   meetingVO.setMandalTwnDivision(commonMethodsUtilService.getStringValueForObject(param[13]));
				  }
				 }
				 resultList.add(meetingVO);
			 }
		 } 
	 }catch(Exception e){
		 LOG.error("Error occured at setMeetingCommentsDtla() in CoreDashboardPartyMeetingService {}",e);	 
	 }
 }
 /**
	* @param  Long activityMemberId
	* @param  Long stateId
	* @param String fromDateStr
	* @param String toDateStr
	* @param  List<Long> partyMeetingTypeValues
	* @Param String meetingStatus
	* @param String partyMeetingLevel
	* @param String isComment
	* @param String reportType
	* @param Long locationId
	* @param String locationType
	* @return List<PartyMeetingsVO>
	* @author Santosh 
	* @Description :This method is used to get Meeting Comulative count details. 
	* @since 29-SEP-2016
	*/
 public List<PartyMeetingsVO> getPartyMeetingComulativeCommentDetails(Long activityMemberId,Long stateId,String fromDateStr,String toDateStr,List<Long> partyMeetingTypeValues,String meetingStatus,String partyMeetingLevel,String isComment,String reportType,Long locationId,String locationType){
	     
	    List<PartyMeetingsVO> resultList = new ArrayList<PartyMeetingsVO>();
	     Map<Long,Set<Long>> locationAccessLevelMap = new HashMap<Long, Set<Long>>();
	     List<Long> partyMeetingLevelIdsList = new ArrayList<Long>(0);
	     Map<Long,PartyMeetingsVO> locationMap = new HashMap<Long, PartyMeetingsVO>(0);
	     SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
	     Date fromDate=null;
		 Date toDate=null;
	 try{
		 if(fromDateStr != null && fromDateStr.trim().length()>0 && toDateStr!= null && toDateStr.trim().length()>0){
			 fromDate = sdf.parse(fromDateStr);
			 toDate = sdf.parse(toDateStr);
		 }
		 if(partyMeetingLevel != null && partyMeetingLevel.equalsIgnoreCase("State")){
			 partyMeetingLevelIdsList.add(1l); 
		 }else if(partyMeetingLevel.equalsIgnoreCase("District")){
			 partyMeetingLevelIdsList.add(2l); 
		 }else if(partyMeetingLevel.equalsIgnoreCase("Constituency")){
			 partyMeetingLevelIdsList.add(3l);
		 }else if(partyMeetingLevel.equalsIgnoreCase("Mandal/Town/Division")){
			 partyMeetingLevelIdsList.add(4l);
			 partyMeetingLevelIdsList.add(5l);
			 partyMeetingLevelIdsList.add(6l);
		 }else if(partyMeetingLevel.equalsIgnoreCase("Village/Ward")){
			 partyMeetingLevelIdsList.add(7l);
			 partyMeetingLevelIdsList.add(8l);
		 }
		
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
	   
	    if(locationType.equalsIgnoreCase("Mandal")){
	    	String strLocationId = null;
			if(locationId != null && locationId>0){
				strLocationId = locationId.toString();
			}
			 Long firstDigit = Long.valueOf(strLocationId.substring(0, 1));
			 if(firstDigit.longValue()==1l){
				 locationId=Long.valueOf(strLocationId.substring(1, strLocationId.length()));	
				 locationType="Mandal";
			 }else if(firstDigit.longValue()==2l){
				 locationId=Long.valueOf(strLocationId.substring(1, strLocationId.length()));	
				 locationType="TownDivision";
	     }
	    }
		
			 
	   if(locationAccessLevelMap != null && locationAccessLevelMap.size() > 0){
		   for(Entry<Long, Set<Long>> entry:locationAccessLevelMap.entrySet()){
			   List<Object[]> returnObjList = partyMeetingStatusDAO.getPartyMeetingComulativeCommentDetails(entry.getKey(),new ArrayList<Long>(entry.getValue()), stateId, fromDate, toDate, partyMeetingTypeValues, meetingStatus, partyMeetingLevelIdsList,isComment,locationId,locationType,reportType, "MeetingCount");
			   setMeetingCommentsComulativeDetailsToMap(returnObjList,locationMap,"MeetingCount");
			   List<Object[]> returnCmmntCntObjList = partyMeetingStatusDAO.getPartyMeetingComulativeCommentDetails(entry.getKey(),new ArrayList<Long>(entry.getValue()), stateId, fromDate, toDate, partyMeetingTypeValues, meetingStatus, partyMeetingLevelIdsList,isComment,locationId,locationType,reportType, "commentCount");
			   setMeetingCommentsComulativeDetailsToMap(returnCmmntCntObjList,locationMap,"commentCount");
		   }
	   } 
	  if(locationMap != null && locationMap.size() > 0){
		  resultList.addAll(locationMap.values());
		  locationMap.clear();
	  }
 }catch(Exception e){
	 LOG.error("Error occured at getPartyMeetingComulativeCount() in CoreDashboardPartyMeetingService {}",e); 
 }
 return resultList;
 }
 public void setMeetingCommentsComulativeDetailsToMap(List<Object[]> rtrnObjList,Map<Long,PartyMeetingsVO> locationMap,String countType){
	 try{
		if(rtrnObjList != null && rtrnObjList.size() > 0){
			for(Object[] obj:rtrnObjList){
				 Long locationId = commonMethodsUtilService.getLongValueForObject(obj[0]);
				 PartyMeetingsVO locationVO = locationMap.get(locationId);
				   if(locationVO == null ){
					   locationVO = new PartyMeetingsVO();
					   locationVO.setId(commonMethodsUtilService.getLongValueForObject(obj[0]));
					   locationVO.setName(commonMethodsUtilService.getStringValueForObject(obj[1]));
					   locationVO.setMeetingCount(commonMethodsUtilService.getLongValueForObject(obj[2]));
					   locationMap.put(locationVO.getId(), locationVO);
				   }
				   if(countType.equalsIgnoreCase("commentCount")){
					 locationVO.setCommentCount(commonMethodsUtilService.getLongValueForObject(obj[2]));  
				   }
			}
		}
	 }catch (Exception e) {
		 LOG.error("Error occured at setMeetingCommentsComulativeDetailsToMap() in CoreDashboardPartyMeetingService {}",e);	
	}
 }
 /**
	* @param  Long activityMemberId
	* @param  Long stateId
	* @param String fromDateStr
	* @param String toDateStr
	* @param  List<Long> partyMeetingTypeValues
	* @Param String meetingStatus
	* @param String partyMeetingLevel
	* @param String isComment
	* @return List<PartyMeetingsVO>
	* @author Santosh 
	* @Description :This method is used to get only those district which has comment.. 
	* @since 29-SEP-2016
	*/
 public List<PartyMeetingsVO> getDistrictByState(Long activityMemberId,Long stateId,String fromDateStr,String toDateStr,List<Long> partyMeetingTypeValues,String meetingStatus,String partyMeetingLevel,String isComment){
	 List<PartyMeetingsVO> resultList = new ArrayList<PartyMeetingsVO>();
	 try{
		     Map<Long,Set<Long>> locationAccessLevelMap = new HashMap<Long, Set<Long>>();
		     List<Long> partyMeetingLevelIdsList = new ArrayList<Long>(0);
		     SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		     Date fromDate=null;
			 Date toDate=null;
		
			 if(fromDateStr != null && fromDateStr.trim().length()>0 && toDateStr!= null && toDateStr.trim().length()>0){
				 fromDate = sdf.parse(fromDateStr);
				 toDate = sdf.parse(toDateStr);
			 }
			 if(partyMeetingLevel != null && partyMeetingLevel.equalsIgnoreCase("State")){
				 partyMeetingLevelIdsList.add(1l); 
			 }else if(partyMeetingLevel.equalsIgnoreCase("District")){
				 partyMeetingLevelIdsList.add(2l); 
			 }else if(partyMeetingLevel.equalsIgnoreCase("Constituency")){
				 partyMeetingLevelIdsList.add(3l);
			 }else if(partyMeetingLevel.equalsIgnoreCase("Mandal/Town/Division")){
				 partyMeetingLevelIdsList.add(4l);
				 partyMeetingLevelIdsList.add(5l);
				 partyMeetingLevelIdsList.add(6l);
			 }else if(partyMeetingLevel.equalsIgnoreCase("Village/Ward")){
				 partyMeetingLevelIdsList.add(7l);
				 partyMeetingLevelIdsList.add(8l);
			 }
			
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
				   List<Object[]> returnObjList = partyMeetingDAO.getDistrictByMeetingId(entry.getKey(),new ArrayList<Long>(entry.getValue()), stateId, fromDate, toDate, partyMeetingTypeValues, meetingStatus, partyMeetingLevelIdsList,isComment);
				   setDataToResultList(returnObjList,resultList);
			   }
		   }
	 }catch (Exception e) {
		LOG.error("Error occured at getDistrictListByState() in CoreDashboardPartyMeetingService {}",e);
	 }
	 return resultList;
 }
 /** @param  Long districtId
	* @param Long activityMemberId
	* @param Long stateId
	* @param String fromDateStr
	* @param String toDateStr
	* @param  List<Long> partyMeetingTypeValues
	* @Param String meetingStatus
	* @param String partyMeetingLevel
	* @param String isComment
	* @return List<PartyMeetingsVO>
	* @author Santosh 
	* @Description :This method is used to get only those Constituency which has comment.. 
	* @since 29-SEP-2016
	*/
 public List<PartyMeetingsVO> getConstituencyByDistrictId(Long districtId,Long activityMemberId,Long stateId,String fromDateStr,String toDateStr,List<Long> partyMeetingTypeValues,String meetingStatus,String partyMeetingLevel,String isComment){
	    
	     List<PartyMeetingsVO> resultList = new ArrayList<PartyMeetingsVO>();
	     Map<Long,Set<Long>> locationAccessLevelMap = new HashMap<Long, Set<Long>>();
	     List<Long> partyMeetingLevelIdsList = new ArrayList<Long>(0);
	     SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
	     Date fromDate=null;
		 Date toDate=null;
		
	 try{
		 
		    if(fromDateStr != null && fromDateStr.trim().length()>0 && toDateStr!= null && toDateStr.trim().length()>0){
				 fromDate = sdf.parse(fromDateStr);
				 toDate = sdf.parse(toDateStr);
			 }
			 if(partyMeetingLevel != null && partyMeetingLevel.equalsIgnoreCase("State")){
				 partyMeetingLevelIdsList.add(1l); 
			 }else if(partyMeetingLevel.equalsIgnoreCase("District")){
				 partyMeetingLevelIdsList.add(2l); 
			 }else if(partyMeetingLevel.equalsIgnoreCase("Constituency")){
				 partyMeetingLevelIdsList.add(3l);
			 }else if(partyMeetingLevel.equalsIgnoreCase("Mandal/Town/Division")){
				 partyMeetingLevelIdsList.add(4l);
				 partyMeetingLevelIdsList.add(5l);
				 partyMeetingLevelIdsList.add(6l);
			 }else if(partyMeetingLevel.equalsIgnoreCase("Village/Ward")){
				 partyMeetingLevelIdsList.add(7l);
				 partyMeetingLevelIdsList.add(8l);
			 }
			
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
				   List<Object[]> returnObjList = partyMeetingDAO.getConstituencyByMeetingId(districtId,entry.getKey(),new ArrayList<Long>(entry.getValue()), stateId, fromDate, toDate, partyMeetingTypeValues, meetingStatus, partyMeetingLevelIdsList,isComment);
				   setDataToResultList(returnObjList,resultList);
			   }
		   }
	 }catch (Exception e) {
		LOG.error("Error occured at getConstituencyByDistrictId() in CoreDashboardPartyMeetingService {}",e);
	 }
	 return resultList;
 }
 /** @param Long constituenyId
	* @param  Long activityMemberId
	* @param  Long stateId
	* @param String fromDateStr
	* @param String toDateStr
	* @param  List<Long> partyMeetingTypeValues
	* @Param String meetingStatus
	* @param String partyMeetingLevel
	* @param String isComment
	* @return List<PartyMeetingsVO>
	* @author Santosh 
	* @Description :This method is used to get only those Mandal Town/Division which has comment.. 
	* @since 29-SEP-2016
	*/
 public List<PartyMeetingsVO> getMandalByConstituyId(Long constituenyId,Long activityMemberId,Long stateId,String fromDateStr,String toDateStr,List<Long> partyMeetingTypeValues,String meetingStatus,String partyMeetingLevel,String isComment){
	 List<PartyMeetingsVO> resultList = new ArrayList<PartyMeetingsVO>();
	     Map<Long,Set<Long>> locationAccessLevelMap = new HashMap<Long, Set<Long>>();
	     List<Long> partyMeetingLevelIdsList = new ArrayList<Long>(0);
	     SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
	     Date fromDate=null;
		 Date toDate=null;
	 try{
		 if(fromDateStr != null && fromDateStr.trim().length()>0 && toDateStr!= null && toDateStr.trim().length()>0){
			 fromDate = sdf.parse(fromDateStr);
			 toDate = sdf.parse(toDateStr);
		 }
		 if(partyMeetingLevel != null && partyMeetingLevel.equalsIgnoreCase("State")){
			 partyMeetingLevelIdsList.add(1l); 
		 }else if(partyMeetingLevel.equalsIgnoreCase("District")){
			 partyMeetingLevelIdsList.add(2l); 
		 }else if(partyMeetingLevel.equalsIgnoreCase("Constituency")){
			 partyMeetingLevelIdsList.add(3l);
		 }else if(partyMeetingLevel.equalsIgnoreCase("Mandal/Town/Division")){
			 partyMeetingLevelIdsList.add(4l);
			 partyMeetingLevelIdsList.add(5l);
			 partyMeetingLevelIdsList.add(6l);
		 }else if(partyMeetingLevel.equalsIgnoreCase("Village/Ward")){
			 partyMeetingLevelIdsList.add(7l);
			 partyMeetingLevelIdsList.add(8l);
		 }
		
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
			   List<Object[]> returnObjList = partyMeetingDAO.getMandalByMeetingId(constituenyId,entry.getKey(),new ArrayList<Long>(entry.getValue()), stateId, fromDate, toDate, partyMeetingTypeValues, meetingStatus, partyMeetingLevelIdsList,isComment);
			   if(returnObjList != null && returnObjList.size() > 0){
					 for(Object[] obj:returnObjList){
						 PartyMeetingsVO mandalVO = new PartyMeetingsVO();
						  Long mandalId=Long.valueOf("1"+obj[0].toString());
						  mandalVO.setId(mandalId);
						  mandalVO.setName(commonMethodsUtilService.getStringValueForObject(obj[1]));
						  resultList.add(mandalVO);
					 }
				 }
		   }
	   }
	   if(locationAccessLevelMap != null && locationAccessLevelMap.size() > 0){
		   for(Entry<Long, Set<Long>> entry:locationAccessLevelMap.entrySet()){
			   List<Object[]> returnObjList = partyMeetingDAO.getTownDivisionByMeetingId(constituenyId,entry.getKey(),new ArrayList<Long>(entry.getValue()), stateId, fromDate, toDate, partyMeetingTypeValues, meetingStatus, partyMeetingLevelIdsList,isComment);
			   if(returnObjList != null && returnObjList.size() > 0){
					 for(Object[] obj:returnObjList){
						 PartyMeetingsVO twnDivisionVO = new PartyMeetingsVO();
						  Long townDivisionId=Long.valueOf("2"+obj[0].toString());
						  twnDivisionVO.setId(townDivisionId);
						  twnDivisionVO.setName(commonMethodsUtilService.getStringValueForObject(obj[1]));
						  resultList.add(twnDivisionVO);
					 }
				 }
		
		   }
	   }
	 }catch (Exception e) {
		LOG.error("Error occured at getMandalByConstituyId() in CoreDashboardPartyMeetingService {}",e);
	 }
	 return resultList;
 }
public void setDataToResultList(List<Object[]> returnObjList,List<PartyMeetingsVO> resultList){
	try{
		if(returnObjList != null && returnObjList.size() > 0){
			for(Object[] param:returnObjList){
				PartyMeetingsVO VO = new PartyMeetingsVO();
				 VO.setId(commonMethodsUtilService.getLongValueForObject(param[0]));
				 VO.setName(commonMethodsUtilService.getStringValueForObject(param[1]));
				 resultList.add(VO);
			}
		}
	}catch (Exception e) {
		LOG.error("Error occured at setDataToResultList() in CoreDashboardPartyMeetingService {}",e);
	}
}
	public List<IdNameVO> getParyMeetingMemberDtls(Long partyMeetingMainTypeId,List<Long> partyMeetingTypeIds,Long meetingId,String state,String startDateString,String endDateString,String status){
		LOG.info("Entered into ");
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
			inputVO.setDistId(meetingId);
			List<Long> absentList = new ArrayList<Long>();
			List<Long> cadreIdList = new ArrayList<Long>();
			List<Long> cadreIdAttendList = new ArrayList<Long>();
			if(status.equalsIgnoreCase("invited")){
				List<Long> inviteesList = partyMeetingInviteeDAO.getInvitedMemberCadreId(inputVO);
				cadreIdAttendList = partyMeetingInviteeDAO.getAttendedMemberCadreId(inputVO);
				cadreIdList.addAll(inviteesList);
			}else if(status.equalsIgnoreCase("attended")){
				List<Long> attendedList = partyMeetingAttendanceDAO.getAttendedMemberCadreId(inputVO);
				cadreIdList.addAll(attendedList);  
			}else{
				List<Long> inviteesList = partyMeetingInviteeDAO.getInvitedMemberCadreId(inputVO);
				List<Long> attendedList = partyMeetingInviteeDAO.getAttendedMemberCadreId(inputVO);
				absentList.addAll(inviteesList);
				absentList.removeAll(attendedList);
				cadreIdList.addAll(absentList);    
			}
			Long cadreId = 0l;
			IdNameVO idNameVO = null;
			List<IdNameVO> idNameVOs = new ArrayList<IdNameVO>();
			Map<Long,IdNameVO> idAndMemberDtlsMap = new HashMap<Long,IdNameVO>();  
			if(cadreIdList != null && cadreIdList.size() > 0){
				List<Object[]> membersDesignationDtlsList = trainingCampAttendanceDAO.getMembersDetails(cadreIdList);
				if(membersDesignationDtlsList != null && membersDesignationDtlsList.size() > 0){
					for(Object[] obj : membersDesignationDtlsList){
						cadreId = obj[0] != null ? (Long)obj[0] : 0l;
						idNameVO = idAndMemberDtlsMap.get(cadreId);
						if(idNameVO != null){
							String sts = idNameVO.getStatus();
							if(obj[2] != null){
								sts = sts+","+obj[2].toString();
								idNameVO.setStatus(sts);
								idAndMemberDtlsMap.put(cadreId, idNameVO);
							}else{
								if(obj[3] != null){
									sts = sts+","+(obj[4] != null ? obj[4].toString() : "")+" "+(obj[3] != null ? obj[3].toString() : "");
									idNameVO.setStatus(sts);
									idAndMemberDtlsMap.put(cadreId, idNameVO);
								}
							}  
							
						}else{
							idNameVO = new IdNameVO();
							idNameVO.setId(cadreId); //cadreId
							idNameVO.setName(obj[1] != null ? obj[1].toString() : "");
							if(obj[2] != null){
								idNameVO.setStatus(obj[2].toString());
							}else if(obj[3] != null){
								idNameVO.setStatus((obj[4] != null ? obj[4].toString() : "")+" "+(obj[3] != null ? obj[3].toString() : ""));
							}else{
								idNameVO.setStatus("");
							}
							idNameVO.setMobileNo(obj[5] != null ? obj[5].toString() : "");
							//idNameVO.setWish("attended");
							idAndMemberDtlsMap.put(cadreId, idNameVO); 
						}
					}
				}
			}
			idNameVOs = new ArrayList<IdNameVO>(idAndMemberDtlsMap.values());
			if(status.equalsIgnoreCase("invited")){
				for(IdNameVO nameVO : idNameVOs){
					if(cadreIdAttendList.contains(nameVO.getId())){
						nameVO.setWish("attended");
					}else{
						nameVO.setWish("absent");
					}
				}
			}else if(status.equalsIgnoreCase("attended")){
				for(IdNameVO nameVO : idNameVOs){
					nameVO.setWish("attended");
				}
			}else{     
				for(IdNameVO nameVO : idNameVOs){
					nameVO.setWish("absent");    
				}
			}
		return idNameVOs;     
			
		}catch(Exception e){
			e.printStackTrace();
			LOG.error("Error occured at getParyMeetingMemberDtls() in CoreDashboardPartyMeetingService {}",e);
		}
		return null;
	}

}
