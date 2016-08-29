package com.itgrids.partyanalyst.service.impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.log4j.Logger;

import com.itgrids.partyanalyst.dao.IActivityMemberAccessLevelDAO;
import com.itgrids.partyanalyst.dao.IActivityMemberAccessTypeDAO;
import com.itgrids.partyanalyst.dao.IActivityMemberRelationDAO;
import com.itgrids.partyanalyst.dao.IDelimitationConstituencyAssemblyDetailsDAO;
import com.itgrids.partyanalyst.dao.ITdpBasicCommitteeDAO;
import com.itgrids.partyanalyst.dao.ITdpCommitteeDAO;
import com.itgrids.partyanalyst.dao.ITdpCommitteeLevelDAO;
import com.itgrids.partyanalyst.dao.IUserTypeRelationDAO;
import com.itgrids.partyanalyst.dto.ActivityMemberVO;
import com.itgrids.partyanalyst.dto.CommitteeDataVO;
import com.itgrids.partyanalyst.dto.CommitteeInputVO;
import com.itgrids.partyanalyst.dto.UserTypeVO;
import com.itgrids.partyanalyst.service.ICoreDashboardGenericService;
import com.itgrids.partyanalyst.service.ICoreDashboardMainService;
import com.itgrids.partyanalyst.utils.IConstants;

public class CoreDashboardMainService implements ICoreDashboardMainService {

	private final static Logger LOG = Logger.getLogger(CoreDashboardMainService.class);
	
	//ATTRIBUTES.
	 private ICoreDashboardGenericService coreDashboardGenericService;
	 private IDelimitationConstituencyAssemblyDetailsDAO delimitationConstituencyAssemblyDetailsDAO;
	 private ITdpCommitteeDAO tdpCommitteeDAO;
	 private ITdpCommitteeLevelDAO tdpCommitteeLevelDAO;
	 private ITdpBasicCommitteeDAO tdpBasicCommitteeDAO; 
	 private IActivityMemberAccessTypeDAO activityMemberAccessTypeDAO;
	 private IUserTypeRelationDAO userTypeRelationDAO;
	 private IActivityMemberRelationDAO activityMemberRelationDAO;
	 private IActivityMemberAccessLevelDAO activityMemberAccessLevelDAO;
	//SETTERS
	 public void setCoreDashboardGenericService(ICoreDashboardGenericService coreDashboardGenericService) {
		this.coreDashboardGenericService = coreDashboardGenericService;
	 }
	public void setDelimitationConstituencyAssemblyDetailsDAO(IDelimitationConstituencyAssemblyDetailsDAO delimitationConstituencyAssemblyDetailsDAO){
		this.delimitationConstituencyAssemblyDetailsDAO = delimitationConstituencyAssemblyDetailsDAO;
	}
	public void setTdpCommitteeDAO(ITdpCommitteeDAO tdpCommitteeDAO) {
		this.tdpCommitteeDAO = tdpCommitteeDAO;
	}
	
	public void setTdpCommitteeLevelDAO(ITdpCommitteeLevelDAO tdpCommitteeLevelDAO) {
		this.tdpCommitteeLevelDAO = tdpCommitteeLevelDAO;
	}
	
	public void setTdpBasicCommitteeDAO(ITdpBasicCommitteeDAO tdpBasicCommitteeDAO) {
		this.tdpBasicCommitteeDAO = tdpBasicCommitteeDAO;
	}
	
	public void setActivityMemberAccessTypeDAO(
			IActivityMemberAccessTypeDAO activityMemberAccessTypeDAO) {
		this.activityMemberAccessTypeDAO = activityMemberAccessTypeDAO;
	}
	
	public void setUserTypeRelationDAO(IUserTypeRelationDAO userTypeRelationDAO) {
		this.userTypeRelationDAO = userTypeRelationDAO;
	}
	
	public void setActivityMemberRelationDAO(
			IActivityMemberRelationDAO activityMemberRelationDAO) {
		this.activityMemberRelationDAO = activityMemberRelationDAO;
	}
	
	
	
	public void setActivityMemberAccessLevelDAO(
			IActivityMemberAccessLevelDAO activityMemberAccessLevelDAO) {
		this.activityMemberAccessLevelDAO = activityMemberAccessLevelDAO;
	}
	/**
	  * @author <a href="mailto:sreedhar.itgrids.hyd@gmail.com">SREEDHAR</a>
	  *  This Service Method is used to get top5 strong or top5 poor usertype committees count. 
	  *  @since 10-AUGUST-2016
	  */
   
	public List<List<UserTypeVO>> getUserTypeWiseCommitteesCompletedCounts1(Long userId,Long activityMemberId,Long userTypeId,String state,List<Long> basicCommitteeIds,String dateString){
		List<List<UserTypeVO>> userTypesList = null;
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		try{ 
			 
			//Creating Business Object.
		     CommitteeInputVO committeeBO = new CommitteeInputVO();
		     committeeBO.setBasicCommitteeIds(basicCommitteeIds);
		     Long stateId = coreDashboardGenericService.getStateIdByState(state);
		     committeeBO.setStateId(stateId);
		     if(dateString != null && !dateString.isEmpty()){
		    	 committeeBO.setDate(sdf.parse(dateString));
		     }
		     
			 //calling generic method.
			 ActivityMemberVO activityMemberVO = new ActivityMemberVO();
		     activityMemberVO.setUserId(userId);
		     activityMemberVO.setActivityMemberId(activityMemberId);
		     activityMemberVO.setUserTypeId(userTypeId);
		     activityMemberVO = coreDashboardGenericService.getChildActivityMembersAndLocations(activityMemberVO);
		     Map<Long,Map<Long,UserTypeVO>> userTypesMap = activityMemberVO.getUserTypesMap();
		     Map<Long,Set<Long>> locationLevelIdsMap = activityMemberVO.getLocationLevelIdsMap();
	         
		   //get commitees count based on location level id and location level values.
		     List<String> statusList = new ArrayList<String>();
		     statusList.add("completed");
		     committeeBO.setStatusList(statusList);
		     Map<String,UserTypeVO> locationLevelCountsMap = getCommitteesCountByLocationLevelIdAndLevelValues(locationLevelIdsMap,committeeBO);
		     
		     userTypesList = getMemberRelatedCountsOrPercantage(userTypesMap,"counts",locationLevelCountsMap);
		     if(userTypesList!=null && userTypesList.size()>0){
		    	 getMemberRelatedCountsOrPercantage(userTypesMap,"percanatge",null);
		     }
		   
		  //sorting
		   if(userTypesList != null && userTypesList.size()>0)
		   {
			   for(List<UserTypeVO> membersList : userTypesList)
			   {
				   if(membersList != null)
				   {  
					  Collections.sort(membersList,ActivityMemberCompletedCountPercDesc);
					 
				   }
			   }
		   }
		   
		}catch(Exception e){
			e.printStackTrace();
		}
	    return userTypesList;
	}
   
	
	public List<List<UserTypeVO>> getMemberRelatedCountsOrPercantage(Map<Long,Map<Long,UserTypeVO>> userTypesMap,String type,Map<String,UserTypeVO> locationLevelCountsMap){
		
   	//get member related counts or percanatges
		List<List<UserTypeVO>> userTypesList = null;
		
		   if( userTypesMap != null && userTypesMap.size() > 0)
		   {
			   userTypesList = new ArrayList<List<UserTypeVO>>();
			   
			   for(Long userType:userTypesMap.keySet())
			   {   
				   Map<Long,UserTypeVO> membersMap = userTypesMap.get(userType);
				   
				   if( membersMap != null && membersMap.size()>0)
				   {  
					   for(Long memberid : membersMap.keySet())
					   {  
						   UserTypeVO memberVO = membersMap.get(memberid);
						   
						   if(type.equalsIgnoreCase("counts")){
							   
							   if(memberVO.getLocationValuesSet() != null && memberVO.getLocationValuesSet().size()>0)
							   {
								   for(Long locationValue : memberVO.getLocationValuesSet())
								   {  
									   String key = memberVO.getLocationLevelId()+"_"+locationValue;
									   UserTypeVO countVO = locationLevelCountsMap.get(key);
									   if(countVO != null)
									   {
										   memberVO.setTotalCount(memberVO.getTotalCount() + countVO.getTotalCount());
										   memberVO.setCompletedCount(memberVO.getCompletedCount()+countVO.getCompletedCount());
									   }
								   }
							   } 
						   }else if(type.equalsIgnoreCase("percanatge"))
						   {
							   if(memberVO.getTotalCount()!=null && memberVO.getTotalCount() > 0l)
							   {
								   memberVO.setCompletedPerc( coreDashboardGenericService.caclPercantage(memberVO.getCompletedCount(),memberVO.getTotalCount()) );
							   }
						   }
					   }
				   }
				   userTypesList.add(new ArrayList<UserTypeVO>(membersMap.values()));
			   }
		   }
		   return userTypesList;
    }
	
	public static Comparator<UserTypeVO> ActivityMemberCompletedCountPercDesc = new Comparator<UserTypeVO>() {
	     public int compare(UserTypeVO member2, UserTypeVO member1) {

	        Double perc2 = member2.getCompletedPerc();
	        Double perc1 = member1.getCompletedPerc();
	        //descending order of percantages.
	         return perc1.compareTo(perc2);
	    }
   }; 
   
   /**
	  * @author <a href="mailto:sreedhar.itgrids.hyd@gmail.com">SREEDHAR</a>
	  *  This Service Method is used to get the selected child usertype activity memberids and its committee counts for a parent usertype activity member id. 
	  *  @since 25-AUGUST-2016
	  */
   public List<UserTypeVO> getSelectedChildUserTypeMembers(Long parentActivityMemberId,Long childUserTypeId,String state,List<Long> basicCommitteeIds,String dateString){
	    List<UserTypeVO> activityMembersList = null;
	    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
	   try{
		   
		     //Creating Business Object.
		     CommitteeInputVO committeeBO = new CommitteeInputVO();
		     committeeBO.setBasicCommitteeIds(basicCommitteeIds);
		     Long stateId = coreDashboardGenericService.getStateIdByState(state);
		     committeeBO.setStateId(stateId);
		     if(dateString != null && !dateString.isEmpty()){
		    	 committeeBO.setDate(sdf.parse(dateString));
		     }
		   
		     //calling generic method.
		    ActivityMemberVO activityMemberVO = coreDashboardGenericService.getSelectedChildUserTypeMembers(parentActivityMemberId,childUserTypeId);
		    Map<Long,UserTypeVO> childActivityMembersMap = activityMemberVO.getActivityMembersMap();
		    Map<Long,Set<Long>> locationLevelIdsMap = activityMemberVO.getLocationLevelIdsMap();
		     
		     //get commitees count based on location level id and location level values.
		     List<String> statusList = new ArrayList<String>();
		     statusList.add("completed");
		     statusList.add("started");
		     committeeBO.setStatusList(statusList);
		     Map<String,UserTypeVO> locationLevelCountsMap = getCommitteesCountByLocationLevelIdAndLevelValues(locationLevelIdsMap,committeeBO);
		     
		     
		     //add counts to activity members.
		     activityMembersList = setCommitteeCountsToActivityMembers(childActivityMembersMap,"counts",locationLevelCountsMap,null);
		     if(activityMembersList!=null && activityMembersList.size()>0){
		    	 setCommitteeCountsToActivityMembers(childActivityMembersMap,"percanatge",null,null);
		     }
	   }catch(Exception e){
		 e.printStackTrace();
	   }
	   return activityMembersList;
   }
   public List<UserTypeVO> setCommitteeCountsToActivityMembers(Map<Long,UserTypeVO> childActivityMembersMap,String type,Map<String,UserTypeVO> countForLocationMap,Map<String,String> nameForLocationMap){
	   	
		List<UserTypeVO> activityMembersList = null;
		
		   if(childActivityMembersMap != null && childActivityMembersMap.size() > 0){
			   activityMembersList = new ArrayList<UserTypeVO>();
			   
			   for(Long activityMemberId:childActivityMembersMap.keySet()){ 
			     
				   UserTypeVO memberVO = childActivityMembersMap.get(activityMemberId);
				   
				   if(type.equalsIgnoreCase("counts")){
					   if(memberVO.getLocationValuesSet() != null && memberVO.getLocationValuesSet().size()>0){
						   for(Long locationValue : memberVO.getLocationValuesSet()){
							   String key = memberVO.getLocationLevelId()+"_"+locationValue;
							   
							   //setting count to a location.
							   UserTypeVO countVO = countForLocationMap.get(key);
							   if(countVO != null)
							   {
								   memberVO.setTotalCount(memberVO.getTotalCount() + countVO.getTotalCount());
								   memberVO.setCompletedCount(memberVO.getCompletedCount()+countVO.getCompletedCount());
								   memberVO.setStartedCount(memberVO.getStartedCount()+countVO.getStartedCount());
								   memberVO.setNotStartedCount(memberVO.getNotStartedCount()+countVO.getNotStartedCount());
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
					   if(memberVO.getTotalCount()!=null && memberVO.getTotalCount() > 0l){
						   memberVO.setCompletedPerc( coreDashboardGenericService.caclPercantage(memberVO.getCompletedCount(),memberVO.getTotalCount()) );
						   memberVO.setStartedPerc( coreDashboardGenericService.caclPercantage(memberVO.getStartedCount(),memberVO.getTotalCount()) );
						   memberVO.setNotStartedPerc( coreDashboardGenericService.caclPercantage(memberVO.getNotStartedCount(),memberVO.getTotalCount()) );
					   }
	                }
		     }
			   activityMembersList.addAll(childActivityMembersMap.values());
        }
		   return activityMembersList;
    }
   
   
    /**
	  *  This  Method is used to get the committees count based on given locations. 
	  */
   public Map<String,UserTypeVO> getCommitteesCountByLocationLevelIdAndLevelValues(Map<Long,Set<Long>> locationLevelIdsMap,CommitteeInputVO committeeBO){
	   
	   Map<String,UserTypeVO> locationLevelCountsMap = new HashMap<String, UserTypeVO>(0);
	   
	   if(locationLevelIdsMap != null && locationLevelIdsMap.size()>0){
	    	 for(Long userAccessLevelId : locationLevelIdsMap.keySet()){
	    		 
	    		 //getRequiredCommitteeLevelIdsByUserAccessLevelId(userAccessLevelId,new ArrayList<Long>(locationLevelIdsMap.get(userAccessLevelId)),committeeBO);
	    		 coreDashboardGenericService.setAppropriateLocationLevelInputsToBO(userAccessLevelId,new ArrayList<Long>(locationLevelIdsMap.get(userAccessLevelId)),committeeBO);
	    		 
	    		 committeeBO.setStatus(null);
	    		 List<Object[]> totalCountList = tdpCommitteeDAO.getLocationWiseCommitteesCountByLocIds(committeeBO);
	    		 setCommitteesCountToItsCorrespondingLocation("total",totalCountList,locationLevelCountsMap,userAccessLevelId);
	    		 
	    		 if(committeeBO.getStatusList().contains("completed")){
	    			 committeeBO.setStatus("completed");
	    			 List<Object[]> completedCountList = tdpCommitteeDAO.getLocationWiseCommitteesCountByLocIds(committeeBO);
	    			 setCommitteesCountToItsCorrespondingLocation("completed",completedCountList,locationLevelCountsMap,userAccessLevelId);
	    		 }
	    		 if(committeeBO.getStatusList().contains("started")){
	    			 committeeBO.setStatus("started");
	    			 List<Object[]> startedCountList = tdpCommitteeDAO.getLocationWiseCommitteesCountByLocIds(committeeBO);
	    			 setCommitteesCountToItsCorrespondingLocation("started",startedCountList,locationLevelCountsMap,userAccessLevelId);
	    		 }
	    		 if(committeeBO.getStatusList().contains("notStarted")){
	    			 committeeBO.setStatus("notStarted");
	    			 List<Object[]> notStartedList = tdpCommitteeDAO.getLocationWiseCommitteesCountByLocIds(committeeBO);
	    			 setCommitteesCountToItsCorrespondingLocation("notStarted",notStartedList,locationLevelCountsMap,userAccessLevelId);
	    		 }
	    	 }    
	     }
	    return locationLevelCountsMap;
   }
   public void setCommitteesCountToItsCorrespondingLocation(String status,List<Object[]> list,Map<String,UserTypeVO> locationLevelCountsMap,Long accessLevelId){
		
	   	 if(list!=null && list.size()>0){
				 for(Object[] obj : list){
					 String key = accessLevelId+"_"+(Long)obj[1]; 
					 UserTypeVO countsVO = locationLevelCountsMap.get(key);
					 if(countsVO == null){
						 countsVO = new UserTypeVO();
						 locationLevelCountsMap.put(key, countsVO);
					 }
					 countsVO = locationLevelCountsMap.get(key);
					 if(status.equalsIgnoreCase("total")){
						 countsVO.setTotalCount(obj[0]!=null?(Long)obj[0]:0l); 
					 }else if(status.equalsIgnoreCase("completed")){
						 countsVO.setCompletedCount(obj[0]!=null?(Long)obj[0]:0l); 
					 }else if(status.equalsIgnoreCase("started")){
						 countsVO.setStartedCount(obj[0]!=null?(Long)obj[0]:0l); 
					 }else if(status.equalsIgnoreCase("notStarted")){
						 countsVO.setNotStartedCount(obj[0]!=null?(Long)obj[0]:0l); 
					 }
				 }
			 }
	 }
   
   /**
	  * @author <a href="mailto:sreedhar.itgrids.hyd@gmail.com">SREEDHAR</a>
	  *  This Service Method is used to get the direct child usertype activity memberids and its committee counts for a parent usertype activity member id. 
	  *  @since 25-AUGUST-2016
	  */
 public List<UserTypeVO> getDirectChildActivityMemberCommitteeDetails(Long activityMemberId,Long userTypeId,String state,List<Long> basicCommitteeIds,String dateString){
	    List<UserTypeVO> activityMembersList = null;
	    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
	   try{
		   
		     //Creating Business Object.
		     CommitteeInputVO committeeBO = new CommitteeInputVO();
		     committeeBO.setBasicCommitteeIds(basicCommitteeIds);
		     Long stateId = coreDashboardGenericService.getStateIdByState(state);
		     committeeBO.setStateId(stateId);
		     if(dateString != null && !dateString.isEmpty()){
		    	 committeeBO.setDate(sdf.parse(dateString));
		     }
		   
		     //calling generic method.
		    ActivityMemberVO activityMemberVO = coreDashboardGenericService.getDirectChildActivityMemberCommitteeDetails(activityMemberId,userTypeId);
		    Map<Long,UserTypeVO> childActivityMembersMap = activityMemberVO.getActivityMembersMap();
		    Map<Long,Set<Long>> locationLevelIdsMap = activityMemberVO.getLocationLevelIdsMap();
		     
		     //get commitees count based on location level id and location level values.
		     List<String> statusList = new ArrayList<String>();
		     statusList.add("completed");
		     statusList.add("started");
		     statusList.add("notStarted");
		     committeeBO.setStatusList(statusList);
		     Map<String,UserTypeVO> countForLocationMap = getCommitteesCountByLocationLevelIdAndLevelValues(locationLevelIdsMap,committeeBO);
		     Map<String,String>     nameForLocationMap  = coreDashboardGenericService.getLocationNamesByLocationIds(locationLevelIdsMap);
		     
		     //add counts to activity members.
		     activityMembersList = setCommitteeCountsToActivityMembers(childActivityMembersMap,"counts",countForLocationMap,nameForLocationMap);
		     if(activityMembersList!=null && activityMembersList.size()>0){
		    	 //calculating percantage.
		    	  setCommitteeCountsToActivityMembers(childActivityMembersMap,"percanatge",null,null);
		     }
	   }catch(Exception e){
		 e.printStackTrace();
	   }
	   return activityMembersList;
  }
	 public Map<Long,String> getAllCommitteeLevels(){
			
		 Map<Long,String> committeeLevelsMap = new HashMap<Long,String>(0);
	     List<Object[]> committeeLevels = tdpCommitteeLevelDAO.getAllLevels();
	     if( committeeLevels != null && committeeLevels.size() > 0){
	    	 for(Object[] obj : committeeLevels){
	    		 committeeLevelsMap.put((Long)obj[0],obj[1].toString());
	    	 }
	     }
	     return committeeLevelsMap;
	}
	public Map<Long,String> getCommitteesNames(){
		Map<Long,String> committeeNamesMap = new HashMap<Long,String>(0);
	     List<Object[]> committeeNames = tdpBasicCommitteeDAO.getBasicCommittees();
	     if( committeeNames != null && committeeNames.size() > 0){
	    	 for(Object[] obj : committeeNames){
	    		 committeeNamesMap.put((Long)obj[0],obj[1].toString());
	    	 }
	     }
	     return committeeNamesMap;
	}

	/**
	  * @author <a href="mailto:sreedhar.itgrids.hyd@gmail.com">SREEDHAR</a>
	  *  This Service Method is used to get the getTopPoorPerformancecommittees.
	  *  @since 27-AUGUST-2016
	  */
 public CommitteeDataVO getTopPoorPerformancecommittees(Long activityMemberId,List<Long> basicCommitteeIds,String state,String dateString){
	   SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
	   CommitteeDataVO finalVO = new CommitteeDataVO();
	   try {
		   	
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
		     CommitteeInputVO committeeBO = new CommitteeInputVO();
		     committeeBO.setBasicCommitteeIds(basicCommitteeIds);
		     Long stateId = coreDashboardGenericService.getStateIdByState(state);
		     committeeBO.setStateId(stateId);
		     if(dateString != null && !dateString.isEmpty()){
		    	 committeeBO.setDate(sdf.parse(dateString));
		     }
		     List<Long> requiredCommitteeLevelIds = coreDashboardGenericService.getRequiredTdpCommitteeLevelIdsByUserAccessLevelId(userLocationLevelId,userLocationLevelValues);
		     committeeBO.setTdpCommitteeLevelIds(requiredCommitteeLevelIds);
		     coreDashboardGenericService.setAppropriateLocationLevelInputsToBO(userLocationLevelId,userLocationLevelValues,committeeBO);
		     committeeBO.setQueryString(coreDashboardGenericService.getCommittesRelatedLocationQuerypart(committeeBO));
		     
		     //pre data setting.
		     Map<Long,String> committeeLevelNameMap = getAllCommitteeLevels();
		     Map<Long,String> committeeNamesMap = getCommitteesNames(); 
		     
		     Map<Long,CommitteeDataVO> committeeLevelMap = new LinkedHashMap<Long,CommitteeDataVO>(0);
		   	 if(requiredCommitteeLevelIds != null && requiredCommitteeLevelIds.size() > 0){
				 for(Long committeeLevelId : requiredCommitteeLevelIds){
					 if(committeeLevelId != 7 && committeeLevelId != 9 && committeeLevelId != 8 ){
						 CommitteeDataVO committeeDataVO = new CommitteeDataVO();
						 committeeDataVO.setId(committeeLevelId);
						 committeeDataVO.setName(committeeLevelNameMap.get(committeeLevelId));
						 
						 //basic committees.
						 if(basicCommitteeIds != null && basicCommitteeIds.size()>0){
							 Map<Long,CommitteeDataVO> basicCommitteesMap = new LinkedHashMap<Long, CommitteeDataVO>();
							 for(Long basicCommitteeId : basicCommitteeIds){
								 CommitteeDataVO basicCommitteeVO = new CommitteeDataVO();
								 basicCommitteeVO.setId(basicCommitteeId);
								 basicCommitteeVO.setName(committeeNamesMap.get(basicCommitteeId));
								 basicCommitteesMap.put(basicCommitteeId, basicCommitteeVO);
							 }
							 committeeDataVO.setSubMap(basicCommitteesMap);
						 }
						 
						 committeeLevelMap.put(committeeDataVO.getId(), committeeDataVO);
					 }
				 }
			  }
		     
		     List<Object[]> totalCommittees = tdpCommitteeDAO.getCommitteeLevelWiseCountsByLocIds(committeeBO);
		     setLevelWiseBasicCommitteesCounts(committeeLevelMap,totalCommittees,"total");
		     
		     committeeBO.setStatus("completed");
		     List<Object[]> completedCommittees = tdpCommitteeDAO.getCommitteeLevelWiseCountsByLocIds(committeeBO);
		     setLevelWiseBasicCommitteesCounts(committeeLevelMap,completedCommittees,"completed");
		     
		     List<CommitteeDataVO> finalList = null;
		     if(committeeLevelMap != null && committeeLevelMap.size() > 0){
		    	 for(Long committeeLevelId : committeeLevelMap.keySet()){
		    		 Map<Long,CommitteeDataVO> basicCommitteeMap = committeeLevelMap.get(committeeLevelId).getSubMap();
		    		 if(basicCommitteeMap != null){
		    			 committeeLevelMap.get(committeeLevelId).setSubList(new ArrayList<CommitteeDataVO>(basicCommitteeMap.values()));
		    			 basicCommitteeMap.clear();
		    		 }
		    	 }
		    	 finalList = new ArrayList<CommitteeDataVO>(committeeLevelMap.values());
		    	     //calc percantages.
		    	 if(finalList!=null && finalList.size()>0){
					for(CommitteeDataVO committeeLevelVO : finalList){
						if(committeeLevelVO != null && committeeLevelVO.getSubList()!=null && committeeLevelVO.getSubList().size()>0){
							for(CommitteeDataVO basicCommitteeVO : committeeLevelVO.getSubList()){
								if(basicCommitteeVO.getTotalCount()!=null && basicCommitteeVO.getTotalCount() > 0l){
									basicCommitteeVO.setCompletedPerc(coreDashboardGenericService.caclPercantage(basicCommitteeVO.getCompletedCount(),basicCommitteeVO.getTotalCount()) );
								}
							}
						}
					}
				}
		     }
		     //sorting
			   if(finalList != null && finalList.size()>0){
				   
				   for(CommitteeDataVO committeeLevelVO : finalList){
					   if(committeeLevelVO!=null && committeeLevelVO.getSubList()!=null && committeeLevelVO.getSubList().size()>0){
						   Collections.sort(committeeLevelVO.getSubList(),BasicCommitteesCompletedCountPercDesc);
					   }
				   }
				   //add to finalVO
				   finalVO.setSubList(finalList);
			   }
		       
		     
		     //getCumulativeCommitteesCountsByLocIds.
		     Map<Long,CommitteeDataVO> basicCommitteesMap = new LinkedHashMap<Long, CommitteeDataVO>();
			 if(basicCommitteeIds != null && basicCommitteeIds.size()>0){
				 for(Long basicCommitteeId : basicCommitteeIds){
					 CommitteeDataVO basicCommitteeVO = new CommitteeDataVO();
					 basicCommitteeVO.setId(basicCommitteeId);
					 basicCommitteeVO.setName(committeeNamesMap.get(basicCommitteeId));
					 basicCommitteesMap.put(basicCommitteeId, basicCommitteeVO);
				 }
			 }
			 committeeBO.setStatus(null);
			 List<Object[]> totalList = tdpCommitteeDAO.getCumulativeCommitteesCountsByLocIds(committeeBO);
			 committeeBO.setStatus("completed");
			 List<Object[]> completedList = tdpCommitteeDAO.getCumulativeCommitteesCountsByLocIds(committeeBO); 
			 
			 setCountsToBasicCommittees(basicCommitteesMap,totalList,"total");
			 setCountsToBasicCommittees(basicCommitteesMap,completedList,"completed");
			 List<CommitteeDataVO> cumulativesList = null;
			 if(basicCommitteesMap!=null && basicCommitteesMap.size()>0){
				 cumulativesList =  new ArrayList<CommitteeDataVO>(basicCommitteesMap.values());
			 }
			 //calc percantage
			 if(cumulativesList != null && cumulativesList.size()>0 ){
				for(CommitteeDataVO basicCommitteeVO : cumulativesList){
					if(basicCommitteeVO.getTotalCount()!=null && basicCommitteeVO.getTotalCount() > 0l){
						basicCommitteeVO.setCompletedPerc(coreDashboardGenericService.caclPercantage(basicCommitteeVO.getCompletedCount(),basicCommitteeVO.getTotalCount()) );
					}
				}
			}
		    
			//sorting ascending order of percantages.
		    if(cumulativesList != null && cumulativesList.size()>0){
			   Collections.sort(cumulativesList,BasicCommitteesCompletedCountPercDesc);
			   //add to finalVO
			   finalVO.setSubList1(cumulativesList);
		    }
			 
			 
	} catch (Exception e) {
		e.printStackTrace();
	}
	   return finalVO;
  }
  public void setLevelWiseBasicCommitteesCounts(Map<Long,CommitteeDataVO> committeeLevelMap,List<Object[]> dataList,String type){
	   
	   try{
		   if(dataList != null && dataList.size()>0){
		    	 for(Object[] obj : dataList){
		    	    
		    		 Long tdpCommitteelevelId = obj[0]!=null?(Long)obj[0]:0l;
		    		 if(tdpCommitteelevelId > 0l){
		    			 
		    			 CommitteeDataVO committeeLevelVO = null ;
						 if( tdpCommitteelevelId == 7l || tdpCommitteelevelId == 9l){ // Mandal/town/division
							 committeeLevelVO = committeeLevelMap.get(5l);
						 }else if( tdpCommitteelevelId == 8l ){ // village/ward
							 committeeLevelVO = committeeLevelMap.get(6l);
						 }else{
							 committeeLevelVO = committeeLevelMap.get(obj[0]);
						 }
		    			 
		    			 if(committeeLevelVO!=null ){
		    				 Map<Long,CommitteeDataVO> basicCommitteesMap = committeeLevelVO.getSubMap();
		    				 if(basicCommitteesMap!=null && basicCommitteesMap.size()>0){
		    					 Long basicCommitteeId = obj[2]!=null?(Long)obj[2]:0l;
		    					 if(basicCommitteeId>0l){
		    						 CommitteeDataVO basicCommitteeVO = basicCommitteesMap.get(basicCommitteeId);
		    						 if(basicCommitteeVO!=null){
		    							 
		    							 if(type.equalsIgnoreCase("total")){
		    								 basicCommitteeVO.setTotalCount(basicCommitteeVO.getTotalCount()+ (obj[4]!=null?(Long)obj[4]:0l) );
		    							 }else if(type.equalsIgnoreCase("completed")){
		    								 basicCommitteeVO.setCompletedCount(basicCommitteeVO.getCompletedCount()+ (obj[4]!=null?(Long)obj[4]:0l) );
		    							 }
		    							 
		    						 }
		    					 }
		    				 }
		    			 }
		    		 }
		    	 }
		     }
		   
	   }catch(Exception e){
		 e.printStackTrace();
	   }
 	}
	
	public void setCountsToBasicCommittees(Map<Long,CommitteeDataVO> basicCommitteesMap,List<Object[]> list,String type){
	   if(list!=null && list.size()>0){
	    	for(Object[] obj :list){
	    		CommitteeDataVO basicCommitteeVO = basicCommitteesMap.get((Long)obj[0]);
	    		if(basicCommitteeVO!=null){
	    			if(type.equalsIgnoreCase("total")){
	    				basicCommitteeVO.setTotalCount(obj[2]!=null?(Long)obj[2]:0l);
	    			}else if(type.equalsIgnoreCase("completed")){
	    				basicCommitteeVO.setCompletedCount(obj[2]!=null?(Long)obj[2]:0l);
	    			}
	    		}
	    	}
	     }
	 }
     
	 public static Comparator<CommitteeDataVO> BasicCommitteesCompletedCountPercDesc = new Comparator<CommitteeDataVO>() {
	     public int compare(CommitteeDataVO member2, CommitteeDataVO member1) {
	
	        Double perc2 = member2.getCompletedPerc();
	        Double perc1 = member1.getCompletedPerc();
	        //ascending order of percantages.
	         return perc2.compareTo(perc1);
	    }
	}; 
 
	/**
	  * @param  Long userAccessLevelId
	  * @param  List<Long> userAccessLevelValues
	  * @param  String state
	  * @param  List<Long> basicCommitteeIds
	  * @param  String startDateString
	  * @param  String endDateString
	  * @return CommitteeDataVO
	  * @author <a href="mailto:sreedhar.itgrids.hyd@gmail.com">SREEDHAR</a>
	  *  This Service Method is used to get the main and affliated committees total and completed and started counts level wise based on the user access levels. 
	  *  @since 29-JULY-2016
	  */
	public CommitteeDataVO getCommitteesBasicCountReport(Long userAccessLevelId,List<Long> userAccessLevelValues,String state,List<Long> basicCommitteeIds,String startDateString,String endDateString){
		
		LOG.info(" entered in to getCommitteesBAsicCountReport() ");
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		CommitteeDataVO finalVO = new CommitteeDataVO();
		Date startDate = null;
		Date endDate = null;
		try{
		     if(startDateString != null && !startDateString.isEmpty()){
		    	 startDate = sdf.parse(startDateString);
		     }
		     if(endDateString != null && !endDateString.isEmpty()){
		    	 endDate = sdf.parse(endDateString);
		     }
		     
		     //Making BO.
		     CommitteeInputVO committeeBO = new CommitteeInputVO();
		     getRequiredCommitteeLevelIds(userAccessLevelId,committeeBO,userAccessLevelValues);
		     committeeBO.setBasicCommitteeIds(basicCommitteeIds);
		     committeeBO.setStartDate(startDate);
		     committeeBO.setEndDate(endDate);
		     committeeBO.setState(state);
		     
		     Map<Long,CommitteeDataVO> committeeLevelMap = new LinkedHashMap<Long,CommitteeDataVO>(0);
		     setCommitteeLevelstoMap(committeeLevelMap,committeeBO.getTdpCommitteeLevelIds());
		  
		     List<Object[]> totalCommitteesList     =  tdpCommitteeDAO.getCommitteesTotalOrStartedOrCompletedCount(committeeBO,null);
		     List<Object[]> completedCommitteesList =  tdpCommitteeDAO.getCommitteesTotalOrStartedOrCompletedCount(committeeBO,"completed");
		     List<Object[]> startedCommitteesList   =  tdpCommitteeDAO.getCommitteesTotalOrStartedOrCompletedCount(committeeBO,"started");
		     
		     setCountByLevel(totalCommitteesList,committeeLevelMap,null,finalVO);
		     setCountByLevel(completedCommitteesList,committeeLevelMap,"completed",finalVO);
		     setCountByLevel(startedCommitteesList,committeeLevelMap,"started",finalVO);
	    	 
		     //calc percantages.
	    	 List<CommitteeDataVO> finalList = null;
	    	 if( committeeLevelMap != null && committeeLevelMap.size() > 0){
	    		 finalList = new ArrayList<CommitteeDataVO>(committeeLevelMap.values());
	    		 if( finalList != null && finalList.size() > 0){
	    			 for( CommitteeDataVO levelVO : finalList){
	    				 if(levelVO.getMainVO() != null && levelVO.getMainVO().getTotalCount() > 0l ){
	    					 levelVO.getMainVO().setCompletedPerc( coreDashboardGenericService.caclPercantage(levelVO.getMainVO().getCompletedCount(),levelVO.getMainVO().getTotalCount()) );
	    					 levelVO.getMainVO().setStartedPerc( coreDashboardGenericService.caclPercantage(levelVO.getMainVO().getStartedCount(),levelVO.getMainVO().getTotalCount()) );
	    				 }
	    				 if(levelVO.getAffliatedVO() != null && levelVO.getAffliatedVO().getTotalCount() > 0l ){
	    					 levelVO.getAffliatedVO().setCompletedPerc( coreDashboardGenericService.caclPercantage(levelVO.getAffliatedVO().getCompletedCount(),levelVO.getAffliatedVO().getTotalCount()) );
	    					 levelVO.getAffliatedVO().setStartedPerc( coreDashboardGenericService.caclPercantage(levelVO.getAffliatedVO().getStartedCount(),levelVO.getAffliatedVO().getTotalCount()) );
	    				 }
	    			 }
	    		 }
	    		 finalVO.setSubList(finalList);
	    	 }
	    	 
	    	 if(finalVO.getMainVO() != null && finalVO.getMainVO().getTotalCount() > 0l){
	    		 finalVO.getMainVO().setCompletedPerc(coreDashboardGenericService.caclPercantage(finalVO.getMainVO().getCompletedCount(),finalVO.getMainVO().getTotalCount()));
	    		 finalVO.getMainVO().setStartedPerc(coreDashboardGenericService.caclPercantage(finalVO.getMainVO().getStartedCount(),finalVO.getMainVO().getTotalCount()));
	    	 }
			 if(finalVO.getAffliatedVO() != null && finalVO.getAffliatedVO().getTotalCount() > 0l ){
				 finalVO.getAffliatedVO().setCompletedPerc(coreDashboardGenericService.caclPercantage(finalVO.getAffliatedVO().getCompletedCount(),finalVO.getAffliatedVO().getTotalCount()));
				 finalVO.getAffliatedVO().setStartedPerc(coreDashboardGenericService.caclPercantage(finalVO.getAffliatedVO().getStartedCount(),finalVO.getAffliatedVO().getTotalCount()) );
			 }
		}catch(Exception e){
			LOG.error("exception occurred in getCommitteesCumulativeOverallReportCharts()", e);
		}
		return finalVO;
	}
	
	public void getRequiredCommitteeLevelIds(Long userAccessLevelId,CommitteeInputVO inputVO,List<Long> userAccessLevelValues){
		
		if(userAccessLevelId.longValue() == IConstants.STATE_LEVEl_ACCESS_ID.longValue() ){
			
			inputVO.setTdpCommitteeLevelIds(Arrays.asList(IConstants.STATE_ACCESS_REQUIED_COMMITTEE_LEVEl_IDS));	
			inputVO.setStateIds(userAccessLevelValues);
			
		}else if(userAccessLevelId.longValue() == IConstants.DISTRICT_LEVEl_ACCESS_ID.longValue() ){
			
			inputVO.setTdpCommitteeLevelIds(Arrays.asList(IConstants.DISTRICT_ACCESS_REQUIED_COMMITTEE_LEVEl_IDS));
			inputVO.setDistrictIds(userAccessLevelValues);
			
		}else if(userAccessLevelId.longValue() == IConstants.PARLIAMENT_LEVEl_ACCESS_ID.longValue()){
			
			inputVO.setTdpCommitteeLevelIds(Arrays.asList(IConstants.CONSTITUENCY_ACCESS_REQUIED_COMMITTEE_LEVEl_IDS));
			inputVO.setAssemblyConstIds(delimitationConstituencyAssemblyDetailsDAO.getAssemblyConstituenciesByParliamentList(userAccessLevelValues));
			
		}else if(userAccessLevelId.longValue() == IConstants.ASSEMBLY_LEVEl_ACCESS_ID.longValue()){
			
			inputVO.setTdpCommitteeLevelIds(Arrays.asList(IConstants.CONSTITUENCY_ACCESS_REQUIED_COMMITTEE_LEVEl_IDS));
			inputVO.setAssemblyConstIds(userAccessLevelValues);
			
	    }else if(userAccessLevelId.longValue() == IConstants.MANDAL_LEVEl_ID.longValue()){
			
			inputVO.setTdpCommitteeLevelIds(Arrays.asList(IConstants.MANDAL_ACCESS_REQUIED_COMMITTEE_LEVEl_IDS));
			inputVO.setTehsilIds(userAccessLevelValues);
		}
		
	}
	
	public void setCommitteeLevelstoMap(Map<Long,CommitteeDataVO> committeeLevelMap,List<Long> requiredCommitteeLevelIds){
		 Map<Long,String> committeeLevelNameMap = getAllCommitteeLevels();
	   	 if(requiredCommitteeLevelIds != null && requiredCommitteeLevelIds.size() > 0){
				 for(Long committeeLevelId : requiredCommitteeLevelIds){
					 if(committeeLevelId != 7 && committeeLevelId != 9 && committeeLevelId != 8 ){
						 CommitteeDataVO committeeDataVO = new CommitteeDataVO();
						 committeeDataVO.setId(committeeLevelId);
						 committeeDataVO.setName(committeeLevelNameMap.get(committeeLevelId));
						 CommitteeDataVO mainCommitteeVO = new CommitteeDataVO();
						 CommitteeDataVO affliatedCommitteeVO = new CommitteeDataVO();
						 committeeDataVO.setMainVO(mainCommitteeVO);
						 committeeDataVO.setAffliatedVO(affliatedCommitteeVO);
						 
						 committeeLevelMap.put(committeeDataVO.getId(), committeeDataVO);
					 }
				 }
		  }
	}

	public void setCountByLevel(List<Object[]> list,Map<Long,CommitteeDataVO> committeeLevelMap,String status,CommitteeDataVO finalVO){
		try{
			if( list != null && list .size() > 0){
	    		 for( Object[] obj : list){
					 CommitteeDataVO committeeLevelVO = null ;
					 if( ((Long)obj[0]).longValue() == 7 || ((Long)obj[0]).longValue() == 9){ // Mandal/town/division
						 committeeLevelVO = committeeLevelMap.get(5l);
					 }else if( ((Long)obj[0]).longValue() == 8 ){ // village/ward
						 committeeLevelVO = committeeLevelMap.get(6l);
					 }else{
						 committeeLevelVO = committeeLevelMap.get(obj[0]);
					 }
					 if(committeeLevelVO != null){
						 Long committeeTypeId = obj[2] != null ? (Long)obj[2] :0l;
						 Long count = obj[4] != null ? (Long)obj[4] :0l;
						 
						 CommitteeDataVO committeeTypeVO = null;
						 CommitteeDataVO totalCommitteeTypeVO = null;
						 
						 if(committeeTypeId.longValue() == 1l){ // main committee
							 committeeTypeVO = committeeLevelVO.getMainVO();
							 //for overall total
							 if(finalVO.getMainVO() == null){
								 finalVO.setMainVO(new CommitteeDataVO());
							 }
							 totalCommitteeTypeVO = finalVO.getMainVO();
						 }else if(committeeTypeId.longValue() == 2l){ //affliated committee
							 committeeTypeVO = committeeLevelVO.getAffliatedVO();
							 //for overall total
							 if(finalVO.getAffliatedVO() == null){
								 finalVO.setAffliatedVO(new CommitteeDataVO());
							 }
							 totalCommitteeTypeVO = finalVO.getAffliatedVO();
						 }
						 
						 if( status == null ){
							 committeeTypeVO.setTotalCount(committeeTypeVO.getTotalCount() + count);
							 //for overall total
							 totalCommitteeTypeVO.setTotalCount(totalCommitteeTypeVO.getTotalCount() + count);
						 }else{
							 
							 if(status.equalsIgnoreCase("started")){
								 committeeTypeVO.setStartedCount(committeeTypeVO.getStartedCount() + count );
								 //for overall total
								 totalCommitteeTypeVO.setStartedCount(totalCommitteeTypeVO.getStartedCount()+count);
							 }else if(status.equalsIgnoreCase("completed")){
								 committeeTypeVO.setCompletedCount(committeeTypeVO.getCompletedCount() + count ); 
								 //for overall total
								 totalCommitteeTypeVO.setCompletedCount(totalCommitteeTypeVO.getCompletedCount()+count);
							 } 
						 }
					 }
	    		}
	      }
		}catch(Exception e){
			LOG.error("exception occurred in setCommitteeStatusCount()", e);
		}
	}
 
	/**
	  * @param  Long userAccessLevelId
	  * @param  List<Long> userAccessLevelValues
	  * @param  String state
	  * @param  List<Long> basicCommitteeIds
	  * @param  String startDateString
	  * @param  String endDateString
	  * @return CommitteeDataVO
	  * @author <a href="mailto:sreedhar.itgrids.hyd@gmail.com">SREEDHAR</a>
	  *  This Service Method is used to get committees count of given basic committess level wise based on user access levels. 
	  *  @since 10-AUGUST-2016
	  */
		public List<CommitteeDataVO> getLevelWiseBasicCommitteesCountReport(Long userAccessLevelId,List<Long> userAccessLevelValues,String state,List<Long> basicCommitteeIds,String startDateString,String endDateString){
		
		LOG.info(" entered in to getLevelWiseBasicCommitteesCountReport() ");
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		List<CommitteeDataVO> finalList = null;
		Date startDate = null;
		Date endDate = null;
		try{
		     if(startDateString != null && !startDateString.isEmpty()){
		    	 startDate = sdf.parse(startDateString);
		     }
		     if(endDateString != null && !endDateString.isEmpty()){
		    	 endDate = sdf.parse(endDateString);
		     }
		     
		     //Making BO.
		     CommitteeInputVO committeeBO = new CommitteeInputVO();
		     getRequiredCommitteeLevelIds(userAccessLevelId,committeeBO,userAccessLevelValues);
		     committeeBO.setBasicCommitteeIds(basicCommitteeIds);
		     committeeBO.setStartDate(startDate);
		     committeeBO.setEndDate(endDate);
		     committeeBO.setState(state);
		     
		     Map<Long,CommitteeDataVO> committeeLevelMap = new LinkedHashMap<Long,CommitteeDataVO>(0);
		     setCommitteeLevelstoMap(committeeLevelMap,committeeBO.getTdpCommitteeLevelIds(),basicCommitteeIds);
		  
		     List<Object[]> totalCommitteesList      =  tdpCommitteeDAO.levelWiseBasicCommitteesCount(committeeBO,null);
		     List<Object[]> completedCommitteesList  =  tdpCommitteeDAO.levelWiseBasicCommitteesCount(committeeBO,"completed");
		     List<Object[]> startedCommitteesList    =  tdpCommitteeDAO.levelWiseBasicCommitteesCount(committeeBO,"started");
		     List<Object[]> notStartedCommitteesList =  tdpCommitteeDAO.levelWiseBasicCommitteesCount(committeeBO,"notStarted");
		     
		     setCountByLevelAndBasicCommittees(totalCommitteesList,committeeLevelMap,null);
		     setCountByLevelAndBasicCommittees(completedCommitteesList,committeeLevelMap,"completed");
		     setCountByLevelAndBasicCommittees(startedCommitteesList,committeeLevelMap,"started");
		     setCountByLevelAndBasicCommittees(notStartedCommitteesList,committeeLevelMap,"notStarted");
		     
		     //calc percantages.
		     
		     if(committeeLevelMap != null && committeeLevelMap.size() > 0){
		    	 for(Long committeeLevelId : committeeLevelMap.keySet()){
		    		 Map<Long,CommitteeDataVO> basicCommitteeMap = committeeLevelMap.get(committeeLevelId).getSubMap();
		    		 if(basicCommitteeMap != null){
		    			 committeeLevelMap.get(committeeLevelId).setSubList(new ArrayList<CommitteeDataVO>(basicCommitteeMap.values()));
		    			 basicCommitteeMap.clear();
		    		 }
		    	 }
		    	 finalList = new ArrayList<CommitteeDataVO>(committeeLevelMap.values());
		    	 getBasicCommitteesPercantage(finalList);
		     }
		  
		}catch(Exception e){
			LOG.error("exception occurred in getCommitteesCumulativeOverallReportCharts()", e);
			e.printStackTrace();
		}
		return finalList;
	}
		public void setCommitteeLevelstoMap(Map<Long,CommitteeDataVO> committeeLevelMap,List<Long> requiredCommitteeLevelIds,List<Long> basicCommitteeIds){
			 Map<Long,String> committeeLevelNameMap = getAllCommitteeLevels();
			 Map<Long,String> committeeNameMap   = getCommitteesNames();
			 
		  	 if(requiredCommitteeLevelIds != null && requiredCommitteeLevelIds.size() > 0){
				 for(Long committeeLevelId : requiredCommitteeLevelIds){
					 if(committeeLevelId != 7 && committeeLevelId != 9 && committeeLevelId != 8 ){
						 CommitteeDataVO committeeLevelVO = new CommitteeDataVO();
						 committeeLevelVO.setId(committeeLevelId);
						 committeeLevelVO.setName(committeeLevelNameMap.get(committeeLevelId));
						 committeeLevelVO.setSubMap(getBasicCommitteesMap(basicCommitteeIds,committeeNameMap));
						 committeeLevelMap.put(committeeLevelVO.getId(), committeeLevelVO);
					 }
				 }
			  }
		}

		public Map<Long,CommitteeDataVO> getBasicCommitteesMap(List<Long> basicCommitteeIds,Map<Long,String> committeeNameMap){
			Map<Long,CommitteeDataVO> basicCommitteeMap = new LinkedHashMap<Long, CommitteeDataVO>(); 
			if(basicCommitteeIds != null && basicCommitteeIds.size()>0){
				for(Long basicCommitteeId : basicCommitteeIds){
					CommitteeDataVO basicCommitteeVO = new CommitteeDataVO();
					basicCommitteeVO.setId(basicCommitteeId);
					basicCommitteeVO.setName(committeeNameMap.get(basicCommitteeId));
					basicCommitteeMap.put(basicCommitteeVO.getId(), basicCommitteeVO);
				}
			}
			return basicCommitteeMap;
		}

		public void setCountByLevelAndBasicCommittees(List<Object[]> list,Map<Long,CommitteeDataVO> committeeLevelMap,String status){
			
			if(list != null && list.size() > 0)
			{
				for(Object[] obj : list)
				{	
					Long tdpCommitteeLevelId = obj[0] != null ? (Long)obj[0] : 0l;
					CommitteeDataVO committeeLevelVO = null;
					if(tdpCommitteeLevelId.longValue() == 7 || tdpCommitteeLevelId.longValue() == 9){ // Mandal/town/division
						 committeeLevelVO = committeeLevelMap.get(5l);
					}else if( tdpCommitteeLevelId.longValue() == 8 ){ // village/ward
						 committeeLevelVO = committeeLevelMap.get(6l);
					}else{
						 committeeLevelVO = committeeLevelMap.get(tdpCommitteeLevelId);
					}
					
					if(committeeLevelVO != null && committeeLevelVO.getSubMap()!=null)
					{	
						Long basicCommitteeId = obj[2] != null ? (Long)obj[2] : 0l;
						CommitteeDataVO basicCommitteeVO = committeeLevelVO.getSubMap().get(basicCommitteeId);
						if(basicCommitteeVO!=null)
						{
							Long count = obj[6]!=null?(Long)obj[6]:0l;
							if(status != null && !status.isEmpty())
							{
								if(status.equalsIgnoreCase("completed")){
									basicCommitteeVO.setCompletedCount(basicCommitteeVO.getCompletedCount() +count );
								}else if(status.equalsIgnoreCase("started")){
									basicCommitteeVO.setStartedCount(basicCommitteeVO.getStartedCount() + count);
								}else if(status.equalsIgnoreCase("notStarted")){
									basicCommitteeVO.setNotStartedCount(basicCommitteeVO.getNotStartedCount()+count);
								}
							}else{
								basicCommitteeVO.setTotalCount(basicCommitteeVO.getTotalCount()+count);
							}
						}
					}
					
				}
			}
		}

		public void getBasicCommitteesPercantage(List<CommitteeDataVO> list){
			
			if(list!=null && list.size()>0)
			{
				for(CommitteeDataVO committeeLevelVO : list)
				{	
					if(committeeLevelVO != null && committeeLevelVO.getSubList()!=null && committeeLevelVO.getSubList().size()>0){
						
						for(CommitteeDataVO basicCommitteeVO : committeeLevelVO.getSubList())
						{	
							if(basicCommitteeVO.getTotalCount()!=null && basicCommitteeVO.getTotalCount() > 0l)
							{
								basicCommitteeVO.setCompletedPerc( coreDashboardGenericService.caclPercantage(basicCommitteeVO.getCompletedCount(),basicCommitteeVO.getTotalCount()) );
								basicCommitteeVO.setStartedPerc( coreDashboardGenericService.caclPercantage(basicCommitteeVO.getStartedCount(),basicCommitteeVO.getTotalCount()) );
								basicCommitteeVO.setNotStartedPerc( coreDashboardGenericService.caclPercantage(basicCommitteeVO.getNotStartedCount(),basicCommitteeVO.getTotalCount()) );
							}
						}
					}
				}
			}
		}
 
		/**
		  * @author <a href="mailto:sreedhar.itgrids.hyd@gmail.com">SREEDHAR</a>
		  *  This Service Method is used to get CommitteesPerformance cohort details. 
		  *  @since 10-AUGUST-2016
		  */
		public List<CommitteeDataVO> committeesPerformanceCohort(List<Long> tdpCommitteeLevelIdsClicked,List<Long> basicCommitteeIds,String committeeStatus,Long userLocationLevelId,List<Long> userLocationLevelValues,List<String> groupingLocationsList,String startDateString,String endDateString,String state){
		    List<CommitteeDataVO> finalList = null;
		    try{
		      
		      List<Date> datesList = coreDashboardGenericService.getDates(startDateString,endDateString,new SimpleDateFormat("dd/MM/yyyy"));
		      
		      //CREATING BUSINESS OBJECT.
		      CommitteeInputVO committeeBO = new CommitteeInputVO();
		      committeeBO.setTdpCommitteeLevelIds(tdpCommitteeLevelIdsClicked);
		      committeeBO.setBasicCommitteeIds(basicCommitteeIds);
		      Long stateId = coreDashboardGenericService.getStateIdByState(state);
		      committeeBO.setStateId(stateId);
		      setAppropriateLocationLevelInputsToBO(userLocationLevelId,committeeBO,userLocationLevelValues);
		      
		      Map<Long,CommitteeDataVO> finalMap = new LinkedHashMap<Long,CommitteeDataVO>();
		      
		      if(groupingLocationsList != null && groupingLocationsList.size() > 0){
		        for(String groupingLocation : groupingLocationsList){
		          
		          committeeBO.setGroupingLocation(groupingLocation);
		          
		          List<Object[]>  totalList   = tdpCommitteeDAO.committeesPerformanceCohort(committeeBO);
		          List<Object[]> startedList = null;
		          List<Object[]> completedList = null;
		          List<Object[]> notStartedList = null;
		          
		          if(committeeStatus.equalsIgnoreCase("started") || committeeStatus.equalsIgnoreCase("all")){
		            
		            committeeBO.setStatus("started");
		            startedList = tdpCommitteeDAO.committeesPerformanceCohort(committeeBO);
		          }
		          if(committeeStatus.equalsIgnoreCase("completed") || committeeStatus.equalsIgnoreCase("all") ){
		            
		            committeeBO.setStatus("completed"); 
		            completedList = tdpCommitteeDAO.committeesPerformanceCohort(committeeBO);
		            
		          }
		          if(committeeStatus.equalsIgnoreCase("notStarted") || committeeStatus.equalsIgnoreCase("all") ){
		            
		            committeeBO.setStatus("notStarted");
		            notStartedList = tdpCommitteeDAO.committeesPerformanceCohort(committeeBO);
		          }
		          
		          setLocationWiseCommiteesCount(totalList,finalMap,null,groupingLocation);
		          setLocationWiseCommiteesCount(completedList,finalMap,"completed",groupingLocation);
		          setLocationWiseCommiteesCount(startedList,finalMap,"started",groupingLocation);
		          setLocationWiseCommiteesCount(notStartedList,finalMap,"notStarted",groupingLocation);
		          
		        }
		      }
		      
		      if(finalMap != null && finalMap.size() > 0){
		           for(Long basicCommitteeId : finalMap.keySet()){
		             Map<Long,CommitteeDataVO> LocationMap = finalMap.get(basicCommitteeId).getSubMap();
		             if(LocationMap != null){
		               finalMap.get(basicCommitteeId).setSubList(new ArrayList<CommitteeDataVO>(LocationMap.values()));
		               LocationMap.clear();
		             }
		           finalList = new ArrayList<CommitteeDataVO>(finalMap.values());
		           getBasicCommitteesPercantage(finalList);
		           }
		        }
		      
		    }catch(Exception e){
		      e.printStackTrace();
		    }
		    return finalList;
  }

  public void setLocationWiseCommiteesCount(List<Object[]> list,Map<Long,CommitteeDataVO> finalMap,String status,String groupingLocation){
    
    if(list !=null && list.size() >0){
      for(Object[] obj : list){
        
        Long tdpbasicCommitteeId = obj[1] != null ? (Long)obj[1] : 0l;
        CommitteeDataVO basicCommitteeVO = null;
        basicCommitteeVO = finalMap.get(tdpbasicCommitteeId);
          if( basicCommitteeVO == null){
            basicCommitteeVO = new CommitteeDataVO();
            basicCommitteeVO.setId(tdpbasicCommitteeId);
            basicCommitteeVO.setName(obj[2]!=null ? obj[2].toString() : "");
            basicCommitteeVO.setSubMap(new LinkedHashMap<Long, CommitteeDataVO>(0));


            finalMap.put(tdpbasicCommitteeId,basicCommitteeVO);
          }
          basicCommitteeVO = finalMap.get(tdpbasicCommitteeId);
          CommitteeDataVO locationVO = null;
          locationVO = basicCommitteeVO.getSubMap().get((Long)obj[3]);
        if(locationVO==null){
          locationVO = new CommitteeDataVO();
          locationVO.setId((Long)obj[3]);
          locationVO.setName(obj[4]!=null ? obj[4].toString() : "");
          
          if(groupingLocation.equalsIgnoreCase("LocalElectionBody")){
            locationVO.setLocationLevelName(obj[6]!=null ? obj[6].toString() : "");
          }else{
            locationVO.setLocationLevelName(groupingLocation);
          }
          
          basicCommitteeVO.getSubMap().put(locationVO.getId(),locationVO);
        }
        locationVO = basicCommitteeVO.getSubMap().get((Long)obj[3]);
        Long count = obj[0]!=null?(Long)obj[0]:0l;
        if(status != null && !status.isEmpty())
        {
          if(status.equalsIgnoreCase("completed")){
            locationVO.setCompletedCount(locationVO.getCompletedCount() +count );
          }else if(status.equalsIgnoreCase("started")){
            locationVO.setStartedCount(locationVO.getStartedCount() + count);
          }else if(status.equalsIgnoreCase("notStarted")){
            locationVO.setNotStartedCount(locationVO.getNotStartedCount()+count);
          }
        }else{
          locationVO.setTotalCount(locationVO.getTotalCount()+count);
        }
      }
    }
  }

  public void setAppropriateLocationLevelInputsToBO(Long userAccessLevelId,CommitteeInputVO inputVO,List<Long> userAccessLevelValues){
    
    if(userAccessLevelId.longValue() == IConstants.STATE_LEVEl_ACCESS_ID.longValue() ){
      
      inputVO.setStateIds(userAccessLevelValues);
      
    }else if(userAccessLevelId.longValue() == IConstants.DISTRICT_LEVEl_ACCESS_ID.longValue() ){
      
      inputVO.setDistrictIds(userAccessLevelValues);
      
    }else if(userAccessLevelId.longValue() == IConstants.PARLIAMENT_LEVEl_ACCESS_ID.longValue()){
      
      inputVO.setParliamentConstIds(userAccessLevelValues);
      
    }else if(userAccessLevelId.longValue() == IConstants.ASSEMBLY_LEVEl_ACCESS_ID.longValue()){
      
      inputVO.setAssemblyConstIds(userAccessLevelValues);
      
      }else if(userAccessLevelId.longValue() == IConstants.MANDAL_LEVEl_ID.longValue()){
      
      inputVO.setTehsilIds(userAccessLevelValues);
    }
    
  }
  
  public List<Long> getAssemblyConstituencyIdsByParliamentConstituencyIds(List<Long> parliamentIds){
    List<Long> assemblyConstituencyIds = delimitationConstituencyAssemblyDetailsDAO.getAssemblyConstituenciesByParliamentList(parliamentIds);
    return assemblyConstituencyIds;
  }
 
 
 
 
 
 
 
 
 
}
