package com.itgrids.partyanalyst.service.impl;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.log4j.Logger;

import com.itgrids.partyanalyst.dao.IActivityMemberAccessTypeDAO;
import com.itgrids.partyanalyst.dao.IActivityMemberRelationDAO;
import com.itgrids.partyanalyst.dao.IConstituencyDAO;
import com.itgrids.partyanalyst.dao.IDelimitationConstituencyAssemblyDetailsDAO;
import com.itgrids.partyanalyst.dao.IDistrictDAO;
import com.itgrids.partyanalyst.dao.IUserTypeRelationDAO;
import com.itgrids.partyanalyst.dto.ActivityMemberVO;
import com.itgrids.partyanalyst.dto.CommitteeInputVO;
import com.itgrids.partyanalyst.dto.UserDataVO;
import com.itgrids.partyanalyst.dto.UserTypeVO;
import com.itgrids.partyanalyst.service.ICoreDashboardGenericService;
import com.itgrids.partyanalyst.utils.IConstants;

public class CoreDashboardGenericService implements ICoreDashboardGenericService{
	
	private final static Logger LOG = Logger.getLogger(CoreDashboardGenericService.class);
	
	//ATTRIBUTES
	private IActivityMemberAccessTypeDAO activityMemberAccessTypeDAO;
    private IUserTypeRelationDAO userTypeRelationDAO;
    private IActivityMemberRelationDAO activityMemberRelationDAO;
    private IDelimitationConstituencyAssemblyDetailsDAO delimitationConstituencyAssemblyDetailsDAO;
    private IDistrictDAO districtDAO;
    private IConstituencyDAO constituencyDAO;
    
	//SETTERS
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
	 public void setDelimitationConstituencyAssemblyDetailsDAO(
				IDelimitationConstituencyAssemblyDetailsDAO delimitationConstituencyAssemblyDetailsDAO) {
			this.delimitationConstituencyAssemblyDetailsDAO = delimitationConstituencyAssemblyDetailsDAO;
	}

	public void setDistrictDAO(IDistrictDAO districtDAO) {
		this.districtDAO = districtDAO;
	}

	public void setConstituencyDAO(IConstituencyDAO constituencyDAO) {
		this.constituencyDAO = constituencyDAO;
	}
	
	//BUSINESS METHODS
	
	/**
	  * @param  ActivityMemberVO activityMemberVO
	  * @return ActivityMemberVO activityMemberVO
	  * @author <a href="mailto:sreedhar.itgrids.hyd@gmail.com">SREEDHAR</a>
	  *  This Service Method is used SubLevel UserTypes and their activity members and members activity members for a particular activity member. 
	  *  @since 24-AUGUST-2016
	  */
    @SuppressWarnings("unused")
	public ActivityMemberVO getChildActivityMembersAndLocations(ActivityMemberVO activityMemberVO){
    	
    	try{
			
    		//get activitymemberid and usertypeid if not available.
    		if(activityMemberVO.getActivityMemberId() == null || activityMemberVO.getActivityMemberId() <= 0l)
    		{
			    Object[] activityMemberIdAndAccessType= activityMemberAccessTypeDAO.getUserAccessTypeAndActivityMemberIdByUserId(activityMemberVO.getUserId());
			    
			    if( activityMemberIdAndAccessType != null && activityMemberIdAndAccessType.length>0)
			    { 
				   activityMemberVO.setActivityMemberId( activityMemberIdAndAccessType[0] != null ? (Long)activityMemberIdAndAccessType[0] : 0l);
				   activityMemberVO.setUserTypeId( activityMemberIdAndAccessType[1] != null ? (Long)activityMemberIdAndAccessType[1] : 0l);
			    }
		     }
    		
    		Map<Long,List<Long>> ParentChildUserTypesMap = getParentUserTypesAndItsChildUserTypes();
    		Map<Long,Map<Long,UserTypeVO>> userTypesMap  = new LinkedHashMap<Long, Map<Long,UserTypeVO>>(0);
    		Map<Long,Set<Long>> locationLevelIdsMap = new HashMap<Long, Set<Long>>();
    		
    		setLocationLevelsToActivityMembers(userTypesMap,activityMemberVO.getActivityMemberId(),activityMemberVO.getUserTypeId(),ParentChildUserTypesMap,locationLevelIdsMap);
    		
    		activityMemberVO.setUserTypesMap(userTypesMap);
    		activityMemberVO.setLocationLevelIdsMap(locationLevelIdsMap);
    		
		}catch(Exception e){
			LOG.error("Exception occurred in getChildActivityMembersAndLocations() method in CoreDashboardGenericService class",e);
		}
    	return activityMemberVO;
    }
    
    public void setLocationLevelsToActivityMembers( Map<Long,Map<Long,UserTypeVO>> userTypesMap , Long activityMemberId ,Long userTypeId, Map<Long,List<Long>> ParentChildUserTypesRelationMap,Map<Long,Set<Long>> locationLevelIdsMap){
		try{
			
			List<Long> childUserTypeIds = ParentChildUserTypesRelationMap.get(userTypeId);
			
			if(childUserTypeIds != null){
				
				List<Object[]> childMembers = null;
				if( userTypeId.longValue() == IConstants.COUNTRY_USER_TYPE_ID.longValue() || userTypeId.longValue() == IConstants.STATE_USER_TYPE_ID.longValue()){
					childMembers = activityMemberAccessTypeDAO.getAllActivityMembersOfGSAndDistAndMpUserTypes(childUserTypeIds);
				}else{
					childMembers = activityMemberRelationDAO.getChildUserTypeMembers(activityMemberId, childUserTypeIds);
				}
				
				if(childMembers != null && childMembers.size() > 0)
				{
					for(Object[] obj : childMembers)
					{
						 UserTypeVO userTypeVO = null;
						 Long userTypeid = (Long)obj[3];
						 if(!userTypesMap.containsKey(userTypeid)){ 
							 userTypesMap.put(userTypeid, new LinkedHashMap<Long,UserTypeVO>());
						 }
						 Map<Long,UserTypeVO> membersMap = userTypesMap.get(userTypeid);
						 UserTypeVO activityMemberVO = null;
						 Long memberid = (Long)obj[0];
						 if(!membersMap.containsKey(memberid)){
							 
							 activityMemberVO = new UserTypeVO();
							 activityMemberVO.setActivityMemberId((Long)obj[0]);
							 activityMemberVO.setTdpCadreId(obj[1]!=null?(Long)obj[1]:0l);
							 activityMemberVO.setName(obj[2]!=null?obj[2].toString():"");
							 activityMemberVO.setImage(obj[8]!=null?obj[8].toString():"");
							 activityMemberVO.setUserTypeId(obj[3]!=null?(Long)obj[3]:0l);
							 activityMemberVO.setUserType(obj[4]!=null?obj[4].toString():"");
							 activityMemberVO.setLocationLevelId(obj[5]!=null?(Long)obj[5]:0l);
							 activityMemberVO.setLocationLevelName(obj[6]!=null?obj[6].toString():"");
							 activityMemberVO.setLocationValuesSet( new HashSet<Long>());
							 
							 membersMap.put(memberid,activityMemberVO);
							 
							 //its child data.
							 setLocationLevelsToActivityMembers(userTypesMap,activityMemberVO.getActivityMemberId(),activityMemberVO.getUserTypeId(),ParentChildUserTypesRelationMap,locationLevelIdsMap);
							 
						 }
						 activityMemberVO = membersMap.get(memberid);
						 activityMemberVO.getLocationValuesSet().add(obj[7]!= null ?(Long)obj[7]:0l);
						 
						 //get locationlevelId and its corresponding locationValues.
						 Set<Long> locationLevelValues = null;
						 locationLevelValues = locationLevelIdsMap.get(activityMemberVO.getLocationLevelId());
						 if(locationLevelValues == null){
							 locationLevelValues = new HashSet<Long>();
							 locationLevelIdsMap.put(activityMemberVO.getLocationLevelId(), locationLevelValues);
						 }
						 locationLevelValues = locationLevelIdsMap.get(activityMemberVO.getLocationLevelId());
						 if(obj[7]!=null){
							 locationLevelValues.add((Long)obj[7]); 
						 }
						 
					}
				}
			}
			
		}catch(Exception e){
			LOG.error("Exception occurred in setLocationLevelsToActivityMembers() method in CoreDashboardGenericService class",e);
		}
	}
	
	/**
	  * @author <a href="mailto:sreedhar.itgrids.hyd@gmail.com">SREEDHAR</a>
	  *  This Service Method is used to get the selected child usertype activity memberids and its committee counts for a parent usertype activity member id. 
	  *  @since 25-AUGUST-2016
	  */
	public ActivityMemberVO getSelectedChildUserTypeMembers(Long parentActivityMemberId,Long childUSerTypeId){
    	ActivityMemberVO finalVO = new ActivityMemberVO();
    	
    	try{
    		List<Long> childUserTypeIds = new ArrayList<Long>();
    		childUserTypeIds.add(childUSerTypeId);
    		
    		List<Object[]> childMembers = activityMemberRelationDAO.getChildUserTypeMembers(parentActivityMemberId, childUserTypeIds);
    		setActivityMemberDetailsAsVOS(finalVO,childMembers);
    		
		}catch(Exception e){
			LOG.error("Exception occurred in getChildActivityMembersAndLocations() method in CoreDashboardGenericService class",e);
		}
    	return finalVO;
    }
	
	
	/**
	  * @author <a href="mailto:sreedhar.itgrids.hyd@gmail.com">SREEDHAR</a>
	  *  This Service Method is used to get the selected child usertype activity memberids and its committee counts for a parent usertype activity member id. 
	  *  @since 25-AUGUST-2016
	  */
	public ActivityMemberVO getDirectChildActivityMemberCommitteeDetails(Long activityMemberId,Long userTypeId){
   	    ActivityMemberVO finalVO = new ActivityMemberVO();
   	    try{
   		
   	    	Map<Long,List<Long>> ParentChildUserTypesRelationMap = getParentUserTypesAndItsChildUserTypes();
   	    	List<Long> childUserTypeIds = ParentChildUserTypesRelationMap.get(userTypeId);
   		
   	    	List<Object[]> childMembers = activityMemberRelationDAO.getChildUserTypeMembers(activityMemberId, childUserTypeIds);
   		
   	    	setActivityMemberDetailsAsVOS(finalVO,childMembers);
		}catch(Exception e){
			LOG.error("Exception occurred in getChildActivityMembersAndLocations() method in CoreDashboardGenericService class",e);
		}
   	   return finalVO;
   }
	
	public void setActivityMemberDetailsAsVOS(ActivityMemberVO finalVO,List<Object[]> memberDetails){
		
		try{
			//return variables
			Map<Long,UserTypeVO> activityMembersMap  = null;
			Map<Long,Set<Long>> locationLevelIdsMap  = null;
					
			if(memberDetails != null && memberDetails.size() > 0){
				
		   		activityMembersMap  = new LinkedHashMap<Long,UserTypeVO>(0);
		   		locationLevelIdsMap = new HashMap<Long, Set<Long>>(0);
   			    
				for(Object[] obj : memberDetails)
				{	 
					 UserTypeVO activityMemberVO = null;
					 Long memberid = (Long)obj[0];
					 if(!activityMembersMap.containsKey(memberid)){
						 
						 activityMemberVO = new UserTypeVO();
						 activityMemberVO.setActivityMemberId((Long)obj[0]);
						 activityMemberVO.setTdpCadreId(obj[1]!=null?(Long)obj[1]:0l);
						 activityMemberVO.setName(obj[2]!=null?obj[2].toString():"");
						 activityMemberVO.setImage(obj[8]!=null?obj[8].toString():"");
						 activityMemberVO.setUserTypeId(obj[3]!=null?(Long)obj[3]:0l);
						 activityMemberVO.setUserType(obj[4]!=null?obj[4].toString():"");
						 activityMemberVO.setLocationLevelId(obj[5]!=null?(Long)obj[5]:0l);
						 activityMemberVO.setLocationLevelName(obj[6]!=null?obj[6].toString():"");
						 activityMemberVO.setLocationValuesSet( new HashSet<Long>());
						 
						 activityMembersMap.put(memberid,activityMemberVO);
						 
					 }
					 activityMemberVO = activityMembersMap.get(memberid);
					 activityMemberVO.getLocationValuesSet().add(obj[7]!= null ?(Long)obj[7]:0l);
					 
					 //get locationlevelId and its corresponding locationValues.
					 Set<Long> locationLevelValues = null;
					 locationLevelValues = locationLevelIdsMap.get(activityMemberVO.getLocationLevelId());
					 if(locationLevelValues == null){
						 locationLevelValues = new HashSet<Long>();
						 locationLevelIdsMap.put(activityMemberVO.getLocationLevelId(), locationLevelValues);
					 }
					 locationLevelValues = locationLevelIdsMap.get(activityMemberVO.getLocationLevelId());
					 if(obj[7]!=null){
						 locationLevelValues.add((Long)obj[7]); 
					 }
				}
				
				finalVO.setActivityMembersMap(activityMembersMap);
		   		finalVO.setLocationLevelIdsMap(locationLevelIdsMap);
			}
		}catch(Exception e){
			LOG.error("Exception occurred in setActivityMemberDetailsAsVOS() method in CoreDashboardGenericService class",e);
		}
	}
	
	
	/*
	 * Get location Names By location Ids .
	 */
	
	public  Map<String,String>  getLocationNamesByLocationIds( Map<Long,Set<Long>> locationLevelIdsMap){
		
		Map<String,String> locationNamesMap = new HashMap<String,String>(0);
		
		if(locationLevelIdsMap != null && locationLevelIdsMap.size() > 0){
			
			for(Long locationLevelId : locationLevelIdsMap.keySet()){
				
				Set<Long> locationLevelValues = locationLevelIdsMap.get(locationLevelId);
				
				if( locationLevelValues != null && locationLevelValues.size() > 0){
					
					List<Object[]> values = null;
					String suffix = null;
					if(locationLevelId == IConstants.DISTRICT_LEVEl_ACCESS_ID){
						values = districtDAO.getDistrictNamesByIds(new ArrayList<Long>(locationLevelValues));
						suffix = " District";
					}else if(locationLevelId == IConstants.PARLIAMENT_LEVEl_ACCESS_ID){
						values = constituencyDAO.getConstituenctNamesByIds(new ArrayList<Long>(locationLevelValues));
						suffix = " Parliament";
					}else if(locationLevelId == IConstants.ASSEMBLY_LEVEl_ACCESS_ID){
						values = constituencyDAO.getConstituenctNamesByIds(new ArrayList<Long>(locationLevelValues));
						suffix = " Constituency";
					}
					
					if( values != null && values.size() > 0){
						for( Object[] obj : values){
							locationNamesMap.put(locationLevelId+"_"+obj[0],obj[1]!=null ? obj[1].toString() +" "+suffix : "");
						}
					}
				}
			}
		}
		return locationNamesMap;
	}
	
	
    /**
	  * @return List<>
	  * @author <a href="mailto:sreedhar.itgrids.hyd@gmail.com">SREEDHAR</a>
	  *  This  Method is used to get all 'child userTypes to parent userTypes'. 
	  *  @since 24-AUGUST-2016
	  */
      public List<UserDataVO> getChildUserTypesByItsParentUserType(Long parentUserTypeId){
    	  List<UserDataVO> finalList = null;
    	  try{
    			List<Object[]> list  = userTypeRelationDAO.getChildUserTypesByItsParentUserType(parentUserTypeId);
    			
    			if(list != null && list.size() > 0)
    			{
    				finalList = new ArrayList<UserDataVO>();
    				
    				for(Object[] obj : list)
    				{	
    					if(obj[0] != null)
    					{	
    						UserDataVO childUserTypeVO = new UserDataVO();
    						childUserTypeVO.setUserTypeId((Long)obj[0]);
    						childUserTypeVO.setUserType(obj[1]!=null?obj[1].toString():"");
    						childUserTypeVO.setParentUserTypeId(obj[2]!=null?(Long)obj[2]:0l);
    						childUserTypeVO.setParentUserType(obj[3]!=null?obj[3].toString():"");
    						
    						finalList.add(childUserTypeVO);
    					}
    				}
    			}
    		  
		  }catch(Exception e){
			  LOG.error("Exception occurred in getChildUserTypesByItsParent() method in CoreDashboardGenericService class",e);
		  }
    	  return finalList;
      }
    
    public List<Long> getAssemblyConstituencyIdsByParliamentConstituencyIds(List<Long> parliamentIds){
		List<Long> assemblyConstituencyIds = delimitationConstituencyAssemblyDetailsDAO.getAssemblyConstituenciesByParliamentList(parliamentIds);
		return assemblyConstituencyIds;
	}
	
	public Long getStateIdByState(String state){
		Long stateId = 0l;
		if(state!=null && !state.isEmpty()){
			if(state.equalsIgnoreCase("ap")){
				stateId=1l;	 
		     }else if(state.equalsIgnoreCase("ts")){
		    	stateId= 36l;	 
		     }
		}
		return stateId;
	}
    
	public List<Date> getDates(String startDateString,String endDateString,SimpleDateFormat sdf){
    	List<Date> datesList = new ArrayList<Date>();
    	Date startDate = null;
    	Date endDate = null;
    	try{
    		if(startDateString != null && !startDateString.isEmpty()){
   	    	 startDate = sdf.parse(startDateString);
    		}
	   	    if(endDateString != null && !endDateString.isEmpty()){
	   	    	 endDate = sdf.parse(endDateString);
	   	    }
		}catch(Exception e){}
	    datesList.add(0,startDate);
	    datesList.add(1,endDate);
	    return datesList;
    }
	
	public Double caclPercantage(Long subCount,Long totalCount){
		Double d = new BigDecimal(subCount * 100.0/totalCount).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
		return d;
	}
	 /**
	  * 
	  * @return Map<Long,List<Long>>
	  * @author <a href="mailto:sreedhar.itgrids.hyd@gmail.com">SREEDHAR</a>
	  *  This  Method is used to get all 'child userTypes to parent userTypes'. 
	  *  @since 24-AUGUST-2016
	  */
   public Map<Long,List<Long>> getParentUserTypesAndItsChildUserTypes(){
		
		Map<Long,List<Long>> userTypesMap = new HashMap<Long, List<Long>>();
		
		List<Object[]> list  = userTypeRelationDAO.getParentUserTypesAndItsChildUserTypes();
		if( list != null && list.size() > 0){
			for( Object[] obj : list){
				
				if( obj[0] != null){
					List<Long> childUserTypeIds = null;
					childUserTypeIds = userTypesMap.get((Long)obj[0]);
					if( childUserTypeIds == null){
						childUserTypeIds = new ArrayList<Long>();
						userTypesMap.put((Long)obj[0],childUserTypeIds);
					}
					childUserTypeIds = userTypesMap.get((Long)obj[0]);
					childUserTypeIds.add((Long)obj[2]);
				}
			}
		}
		return userTypesMap;
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
		
		public List<Long> getRequiredTdpCommitteeLevelIdsByUserAccessLevelId(Long userLocationLevelId,List<Long> userLocationLevelValues){
			   
		     //getting sameleveltosublevel and only sublevel maps.
		     Map<Long,List<Long>> selfWithSubLevelCommitteeLevelIdsMap = committeeLevelIdsForUserAccessLevelIdBySameLevel();
		     Map<Long,List<Long>> onlySubLevelCommitteeLevelIdsMap = committeeLevelIdsForUserAccessLevelIdBySubLevel();
		     
		     List<Long> requiredTdpCommitteeLevelIds = null;
		     if(userLocationLevelValues!=null && userLocationLevelValues.size()>0){
		    	 if(userLocationLevelValues.size()>1){
		    		 requiredTdpCommitteeLevelIds = selfWithSubLevelCommitteeLevelIdsMap.get(userLocationLevelId);
		    	 }else{
		    		 requiredTdpCommitteeLevelIds = onlySubLevelCommitteeLevelIdsMap.get(userLocationLevelId);
		    	 }
		     }
		     return requiredTdpCommitteeLevelIds;
		}
		
		public Map<Long,List<Long>> committeeLevelIdsForUserAccessLevelIdBySameLevel(){
		   
		   Map<Long,List<Long>> tdpCommitteeLevelIdsMap = new LinkedHashMap<Long,List<Long>>();
		   
		   Long[] COUNTRY_ACCESS_REQUIED_COMMITTEE_LEVEl_IDS = {10l,11l ,5l,7l,9l, 6l,8l};
		   tdpCommitteeLevelIdsMap.put(IConstants.COUNTRY_LEVEl_ACCESS_ID, Arrays.asList(COUNTRY_ACCESS_REQUIED_COMMITTEE_LEVEl_IDS));
		   
		   Long[] STATE_ACCESS_REQUIED_COMMITTEE_LEVEl_IDS = {10l,11l ,5l,7l,9l, 6l,8l};
		   tdpCommitteeLevelIdsMap.put(IConstants.STATE_LEVEl_ACCESS_ID, Arrays.asList(STATE_ACCESS_REQUIED_COMMITTEE_LEVEl_IDS));
		   
		   Long[] DISTRICT_ACCESS_REQUIED_COMMITTEE_LEVEl_IDS = {11l ,5l,7l,9l, 6l,8l};
		   tdpCommitteeLevelIdsMap.put(IConstants.DISTRICT_LEVEl_ACCESS_ID, Arrays.asList(DISTRICT_ACCESS_REQUIED_COMMITTEE_LEVEl_IDS));
		   
		   Long[] PARLIAMAENT_ACCESS_REQUIED_COMMITTEE_LEVEl_IDS = {5l,7l,9l, 6l,8l};
		   tdpCommitteeLevelIdsMap.put(IConstants.PARLIAMENT_LEVEl_ACCESS_ID, Arrays.asList(PARLIAMAENT_ACCESS_REQUIED_COMMITTEE_LEVEl_IDS));
		   
		   Long[] CONSTITUENCY_ACCESS_REQUIED_COMMITTEE_LEVEl_IDS = {5l,7l,9l, 6l,8l};
		   tdpCommitteeLevelIdsMap.put(IConstants.ASSEMBLY_LEVEl_ACCESS_ID, Arrays.asList(CONSTITUENCY_ACCESS_REQUIED_COMMITTEE_LEVEl_IDS));
		   
		   Long[] MANDAL_ACCESS_REQUIED_COMMITTEE_LEVEl_IDS = {5l,7l,9l, 6l,8l};
		   tdpCommitteeLevelIdsMap.put(IConstants.MANDAL_LEVEl_ID, Arrays.asList(MANDAL_ACCESS_REQUIED_COMMITTEE_LEVEl_IDS));
		   
		   return tdpCommitteeLevelIdsMap;
		}
		
		public Map<Long,List<Long>> committeeLevelIdsForUserAccessLevelIdBySubLevel(){
		   
		   Map<Long,List<Long>> tdpCommitteeLevelIdsMap = new LinkedHashMap<Long,List<Long>>();
		   
		   Long[] COUNTRY_ACCESS_REQUIED_COMMITTEE_LEVEl_IDS = {10l,11l ,5l,7l,9l, 6l,8l};
		   tdpCommitteeLevelIdsMap.put(IConstants.COUNTRY_LEVEl_ACCESS_ID, Arrays.asList(COUNTRY_ACCESS_REQUIED_COMMITTEE_LEVEl_IDS));
		   
		   Long[] STATE_ACCESS_REQUIED_COMMITTEE_LEVEl_IDS = {11l ,5l,7l,9l, 6l,8l};
		   tdpCommitteeLevelIdsMap.put(IConstants.STATE_LEVEl_ACCESS_ID, Arrays.asList(STATE_ACCESS_REQUIED_COMMITTEE_LEVEl_IDS));
		   
		   Long[] DISTRICT_ACCESS_REQUIED_COMMITTEE_LEVEl_IDS = {5l,7l,9l, 6l,8l};
		   tdpCommitteeLevelIdsMap.put(IConstants.DISTRICT_LEVEl_ACCESS_ID, Arrays.asList(DISTRICT_ACCESS_REQUIED_COMMITTEE_LEVEl_IDS));
		   
		   Long[] PARLIAMAENT_ACCESS_REQUIED_COMMITTEE_LEVEl_IDS = {5l,7l,9l, 6l,8l};
		   tdpCommitteeLevelIdsMap.put(IConstants.PARLIAMENT_LEVEl_ACCESS_ID, Arrays.asList(PARLIAMAENT_ACCESS_REQUIED_COMMITTEE_LEVEl_IDS));
		   
		   Long[] CONSTITUENCY_ACCESS_REQUIED_COMMITTEE_LEVEl_IDS = {5l,7l,9l, 6l,8l};
		   tdpCommitteeLevelIdsMap.put(IConstants.ASSEMBLY_LEVEl_ACCESS_ID, Arrays.asList(CONSTITUENCY_ACCESS_REQUIED_COMMITTEE_LEVEl_IDS));
		   
		   Long[] MANDAL_ACCESS_REQUIED_COMMITTEE_LEVEl_IDS = {6l,8l};
		   tdpCommitteeLevelIdsMap.put(IConstants.MANDAL_LEVEl_ID, Arrays.asList(MANDAL_ACCESS_REQUIED_COMMITTEE_LEVEl_IDS));
		   
		   return tdpCommitteeLevelIdsMap;
		}
		public StringBuilder getCommittesRelatedLocationQuerypart(CommitteeInputVO committeeBO){
			   
			   StringBuilder sb = new StringBuilder();
			   
			   if(committeeBO.getStateIds()!=null && committeeBO.getStateIds().size()>0){
					sb.append(" and model.userAddress.state.stateId in (:tdpCommitteeLevelValues) ");
				}
				else if(committeeBO.getDistrictIds() != null && committeeBO.getDistrictIds().size()>0){
					
					sb.append(" and model.userAddress.district.districtId in (:tdpCommitteeLevelValues) ");
					
				}else if(committeeBO.getParliamentConstIds() != null && committeeBO.getParliamentConstIds().size()>0){
					
					sb.append(" and model.userAddress.parliamentConstituency.constituencyId in (:tdpCommitteeLevelValues) ");
					
				}else if(committeeBO.getAssemblyConstIds() != null && committeeBO.getAssemblyConstIds().size()>0){
					
					sb.append(" and model.userAddress.constituency.constituencyId in (:tdpCommitteeLevelValues) ");
					
				}else if(committeeBO.getTehsilIds()!= null && committeeBO.getTehsilIds().size()>0){
					
					sb.append(" and model.userAddress.tehsil.tehsilId in (:tdpCommitteeLevelValues) ");
				}
			   return sb;
		   }
		
}
