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
	  * @param  Long userId
	  * @param  Long activityMemberId
	  * @param  Long userTypeId
	  * @param  String state
	  * @param  List<Long> basicCommitteeIds
	  * @param  String startDateString
	  * @param  String endDateString
	  * @return List<List<UserTypeVO>>
	  * @author <a href="mailto:sreedhar.itgrids.hyd@gmail.com">SREEDHAR</a>
	  *  This Service Method is used to get top5 strong or top5 poor usertype committees count. 
	  *  @since 10-AUGUST-2016
	  */

   @SuppressWarnings("unused")
	public List<List<UserTypeVO>> getUserTypeWiseCommitteesCompletedCounts1(Long userId,Long activityMemberId,Long userTypeId,String state,List<Long> basicCommitteeIds,String startDateString,String endDateString){
		List<List<UserTypeVO>> userTypesList = null;
		try{ 
			
			 ActivityMemberVO activityMemberVO = new ActivityMemberVO();
		     activityMemberVO.setUserId(userId);
		     activityMemberVO.setActivityMemberId(activityMemberId);
		     activityMemberVO.setUserTypeId(userTypeId);
		     activityMemberVO = coreDashboardGenericService.getChildActivityMembersAndLocations(activityMemberVO);//calling generic method.
		     Map<Long,Map<Long,UserTypeVO>> userTypesMap = activityMemberVO.getUserTypesMap();
		     Map<Long,Set<Long>> locationLevelIdsMap = activityMemberVO.getLocationLevelIdsMap();
		     
		     
			 //Creating Business Object.
		     CommitteeInputVO committeeBO = new CommitteeInputVO();
		     committeeBO.setBasicCommitteeIds(basicCommitteeIds);
		     Long stateId = coreDashboardGenericService.getStateIdByState(state);
		     committeeBO.setStateId(stateId);
		     List<Date> datesList = coreDashboardGenericService.getDates(startDateString,endDateString,new SimpleDateFormat("dd/MM/yyyy"));
		     committeeBO.setStartDate(datesList.get(0));
		     committeeBO.setEndDate(datesList.get(1));
		     
		     //get commitees count based on location level id and location level values.
	         
		     Map<String,UserTypeVO> locationLevelCountsMap = new HashMap<String, UserTypeVO>(0);
		     
		     if(locationLevelIdsMap != null && locationLevelIdsMap.size()>0){
		    	 for(Long accessLevelId : locationLevelIdsMap.keySet()){
		    		 
		    		 getRequiredCommitteeLevelIdsByUserAccessLevelId(accessLevelId,new ArrayList<Long>(locationLevelIdsMap.get(accessLevelId)),committeeBO);
		    		 setAppropriateLocationLevelInputsToBO(accessLevelId,new ArrayList<Long>(locationLevelIdsMap.get(accessLevelId)),committeeBO);
		    		 
		    		 committeeBO.setStatus(null);
		    		 List<Object[]> totalCountList = tdpCommitteeDAO.getLocationWiseCommitteesCountByLocIds(committeeBO);
		    		 
		    		 committeeBO.setStatus("completed");
		    		 List<Object[]> completedCountList = tdpCommitteeDAO.getLocationWiseCommitteesCountByLocIds(committeeBO);
		    		 
	                 setCountsToALocation("total",totalCountList,locationLevelCountsMap,accessLevelId);
	                 setCountsToALocation("completed",completedCountList,locationLevelCountsMap,accessLevelId);
		    	 }    
		     }
		     
		     userTypesList = getMemberRelatedCountsOrPercantage(userTypesMap,"counts",locationLevelCountsMap);
		     getMemberRelatedCountsOrPercantage(userTypesMap,"percanatge",null);
		   
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
   public void setCountsToALocation(String status,List<Object[]> list,Map<String,UserTypeVO> locationLevelCountsMap,Long accessLevelId){
		
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
					 }
				 }
			 }
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
	
	public void getRequiredCommitteeLevelIdsByUserAccessLevelId(Long userAccessLevelId,List<Long> userAccessLevelValues,CommitteeInputVO inputVO){
		
		if(userAccessLevelId.longValue() == IConstants.STATE_LEVEl_ACCESS_ID.longValue() ){
			
			inputVO.setTdpCommitteeLevelIds(Arrays.asList(IConstants.STATE_ACCESS_REQUIED_COMMITTEE_LEVEl_IDS));
			
		}else if(userAccessLevelId.longValue() == IConstants.DISTRICT_LEVEl_ACCESS_ID.longValue() ){
			
			inputVO.setTdpCommitteeLevelIds(Arrays.asList(IConstants.DISTRICT_ACCESS_REQUIED_COMMITTEE_LEVEl_IDS));
			
		}else if(userAccessLevelId.longValue() == IConstants.PARLIAMENT_LEVEl_ACCESS_ID.longValue()){
			
			inputVO.setTdpCommitteeLevelIds(Arrays.asList(IConstants.CONSTITUENCY_ACCESS_REQUIED_COMMITTEE_LEVEl_IDS));
			
	    }else if(userAccessLevelId.longValue() == IConstants.ASSEMBLY_LEVEl_ACCESS_ID.longValue()){
			
			inputVO.setTdpCommitteeLevelIds(Arrays.asList(IConstants.CONSTITUENCY_ACCESS_REQUIED_COMMITTEE_LEVEl_IDS));
			
	    }else if(userAccessLevelId.longValue() == IConstants.MANDAL_LEVEl_ID.longValue()){
			
			inputVO.setTdpCommitteeLevelIds(Arrays.asList(IConstants.MANDAL_ACCESS_REQUIED_COMMITTEE_LEVEl_IDS));
		}
	}

	public void setAppropriateLocationLevelInputsToBO(Long userAccessLevelId,List<Long> userAccessLevelValues,CommitteeInputVO inputVO){
		
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
	public static Comparator<UserTypeVO> ActivityMemberCompletedCountPercDesc = new Comparator<UserTypeVO>() {
	     public int compare(UserTypeVO member2, UserTypeVO member1) {

	        Double perc2 = member2.getCompletedPerc();
	        Double perc1 = member1.getCompletedPerc();
	        //descending order of percantages.
	         return perc1.compareTo(perc2);
	    }
   }; 
	
}
