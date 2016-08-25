package com.itgrids.partyanalyst.service.impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.log4j.Logger;

import com.itgrids.partyanalyst.dao.IActivityMemberAccessTypeDAO;
import com.itgrids.partyanalyst.dao.IActivityMemberRelationDAO;
import com.itgrids.partyanalyst.dao.IDelimitationConstituencyAssemblyDetailsDAO;
import com.itgrids.partyanalyst.dao.ITdpBasicCommitteeDAO;
import com.itgrids.partyanalyst.dao.ITdpCommitteeDAO;
import com.itgrids.partyanalyst.dao.ITdpCommitteeLevelDAO;
import com.itgrids.partyanalyst.dao.IUserTypeRelationDAO;
import com.itgrids.partyanalyst.dto.ActivityMemberVO;
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
		     activityMembersList = setCommitteeCountsToActivityMembers(childActivityMembersMap,"counts",locationLevelCountsMap);
		     if(activityMembersList!=null && activityMembersList.size()>0){
		    	 setCommitteeCountsToActivityMembers(childActivityMembersMap,"percanatge",null);
		     }
	   }catch(Exception e){
		 e.printStackTrace();
	   }
	   return activityMembersList;
   }
   public List<UserTypeVO> setCommitteeCountsToActivityMembers(Map<Long,UserTypeVO> childActivityMembersMap,String type,Map<String,UserTypeVO> locationLevelCountsMap){
	   	
		List<UserTypeVO> activityMembersList = null;
		
		   if(childActivityMembersMap != null && childActivityMembersMap.size() > 0){
			   activityMembersList = new ArrayList<UserTypeVO>();
			   
			   for(Long activityMemberId:childActivityMembersMap.keySet()){ 
			     
				   UserTypeVO memberVO = childActivityMembersMap.get(activityMemberId);
				   
				   if(type.equalsIgnoreCase("counts")){
					   if(memberVO.getLocationValuesSet() != null && memberVO.getLocationValuesSet().size()>0){
						   for(Long locationValue : memberVO.getLocationValuesSet()){
							   String key = memberVO.getLocationLevelId()+"_"+locationValue;
							   UserTypeVO countVO = locationLevelCountsMap.get(key);
							   if(countVO != null)
							   {
								   memberVO.setTotalCount(memberVO.getTotalCount() + countVO.getTotalCount());
								   memberVO.setCompletedCount(memberVO.getCompletedCount()+countVO.getCompletedCount());
								   memberVO.setStartedCount(memberVO.getStartedCount()+countVO.getStartedCount());
								   memberVO.setNotStartedCount(memberVO.getNotStartedCount()+countVO.getNotStartedCount());
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
   	

}
