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
import java.util.TreeMap;

import org.apache.log4j.Logger;

import com.itgrids.partyanalyst.dao.IActivityMemberAccessTypeDAO;
import com.itgrids.partyanalyst.dao.IActivityMemberRelationDAO;
import com.itgrids.partyanalyst.dao.IConstituencyDAO;
import com.itgrids.partyanalyst.dao.IDelimitationConstituencyAssemblyDetailsDAO;
import com.itgrids.partyanalyst.dao.IDistrictDAO;
import com.itgrids.partyanalyst.dao.IPartyMeetingDAO;
import com.itgrids.partyanalyst.dao.IPartyMeetingStatusDAO;
import com.itgrids.partyanalyst.dao.IUserTypeRelationDAO;
import com.itgrids.partyanalyst.dto.ActivityMemberVO;
import com.itgrids.partyanalyst.dto.CommitteeInputVO;
import com.itgrids.partyanalyst.dto.CoreDashboardCountsVO;
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
    private IPartyMeetingDAO partyMeetingDAO;
    private IPartyMeetingStatusDAO partyMeetingStatusDAO;;
    
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
	
	public void setPartyMeetingDAO(IPartyMeetingDAO partyMeetingDAO) {
		this.partyMeetingDAO = partyMeetingDAO;
	}

	public void setPartyMeetingStatusDAO(
			IPartyMeetingStatusDAO partyMeetingStatusDAO) {
		this.partyMeetingStatusDAO = partyMeetingStatusDAO;
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
	  * @param  ActivityMemberVO activityMemberVO
	  * @return ActivityMemberVO activityMemberVO
	  * @author <a href="mailto:sreedhar.itgrids.hyd@gmail.com">SREEDHAR</a>
	  *  This Service Method is used SubLevel UserTypes and their activity members and members activity members for a particular activity member. 
	  *  @since 30-AUGUST-2016
	  */
    
    public ActivityMemberVO getChildActivityMembersAndLocationsNew(ActivityMemberVO activityMemberVO){
       	
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
       		Map<Long,Map<Long,UserTypeVO>> userTypesMap  = new TreeMap<Long, Map<Long,UserTypeVO>>();
       		Map<Long,Set<Long>> locationLevelIdsMap = new HashMap<Long, Set<Long>>();
       		
       		Map<Long,Map<Long,UserTypeVO>> allParentsMap = getAllChildUserTypeMembersAndParentUserTypeMembers();
       		
       		setLocationLevelsToActivityMembersNew(allParentsMap,userTypesMap,activityMemberVO.getActivityMemberId(),ParentChildUserTypesMap,locationLevelIdsMap);
       		
       		activityMemberVO.setUserTypesMap(userTypesMap);
       		activityMemberVO.setLocationLevelIdsMap(locationLevelIdsMap);
       		
    		}catch(Exception e){
    			LOG.error("Exception occurred in getChildActivityMembersAndLocations() method in CoreDashboardGenericService class",e);
    		}
       	return activityMemberVO;
       }
       
       public void setLocationLevelsToActivityMembersNew(Map<Long,Map<Long,UserTypeVO>> totalMap, Map<Long,Map<Long,UserTypeVO>> userTypesMap , Long activityMemberId , Map<Long,List<Long>> ParentChildUserTypesRelationMap,Map<Long,Set<Long>> locationLevelIdsMap){
    		try{
    			
    			Map<Long,UserTypeVO> childMap = totalMap.get(activityMemberId);
    			
    			if(childMap != null && childMap.size()>0){
    				
    				for(Long childActivityMemberId : childMap.keySet() ){
    					
    					UserTypeVO childVO = childMap.get(childActivityMemberId);
    					
    					Long childUserTypeId = childVO.getUserTypeId();
    						
    						//userType
    						 if(!userTypesMap.containsKey(childUserTypeId)){ 
    							 userTypesMap.put(childUserTypeId, new LinkedHashMap<Long,UserTypeVO>());
    						 }
    						 Map<Long,UserTypeVO> membersMap = userTypesMap.get(childUserTypeId);
    						 if(!membersMap.containsKey(childActivityMemberId)){
    							 membersMap.put(childActivityMemberId, childVO);
    							 //its child data
    							 setLocationLevelsToActivityMembersNew(totalMap,userTypesMap,childActivityMemberId,ParentChildUserTypesRelationMap,locationLevelIdsMap);
    						 }
    						 UserTypeVO activityMemberVO = membersMap.get(childActivityMemberId);
    						 
    						 //get locationlevelId and its corresponding locationValues.
    						 Set<Long> locationLevelValues = null;
    						 locationLevelValues = locationLevelIdsMap.get(activityMemberVO.getLocationLevelId());
    						 if(locationLevelValues == null){
    							 locationLevelValues = new HashSet<Long>();
    							 locationLevelIdsMap.put(activityMemberVO.getLocationLevelId(), locationLevelValues);
    						 }
    						 locationLevelValues = locationLevelIdsMap.get(activityMemberVO.getLocationLevelId());
    						 if(activityMemberVO.getLocationValuesSet() !=null && activityMemberVO.getLocationValuesSet().size()>0){
    							 locationLevelValues.addAll(activityMemberVO.getLocationValuesSet());
    						 }
    				}
    			}
    			
    		}catch(Exception e){
    			LOG.error("Exception occurred in setLocationLevelsToActivityMembersNew() method in CoreDashboardGenericService class",e);
    		}
    	}
       
       
       public Map<Long,Map<Long,UserTypeVO>> getAllChildUserTypeMembersAndParentUserTypeMembers(){
    	   
    	   Map<Long,Map<Long,UserTypeVO>> parentMap = new LinkedHashMap<Long, Map<Long,UserTypeVO>>(0);
    	   try{
    		
    		   List<Object[]> list = activityMemberRelationDAO.getAllChildUserTypeMembersAndParentUserTypeMembers();
    		   if( list != null && list.size()>0){
    			   for(Object[] obj : list){
    				   
    				   Long parentActivityMemberId = obj[0]!=null?(Long)obj[0]:0l;
    				   
    				   Map<Long,UserTypeVO> childMap = null;
    				   childMap = parentMap.get(parentActivityMemberId);
    				   if(childMap == null){
    					   childMap = new LinkedHashMap<Long, UserTypeVO>();
    					   parentMap.put(parentActivityMemberId, childMap);
    				   }
    				   childMap = parentMap.get(parentActivityMemberId);
    				   Long childActivityMemberId = obj[1]!=null?(Long)obj[1]:0l;
    				   UserTypeVO childVO =childMap.get(childActivityMemberId);
    				   
    				   if(childVO == null){
    					   
    					   childVO = new UserTypeVO();
    					   
    					   childVO.setParentActivityMemberId(parentActivityMemberId);
    					   childVO.setActivityMemberId( obj[1]!=null?(Long)obj[1]:0l);
    					   childVO.setTdpCadreId(obj[2]!=null?(Long)obj[2]:0l);
    					   childVO.setName(obj[3]!=null?obj[3].toString():"");
    					   childVO.setImage(obj[9]!=null?obj[9].toString():"");
    					   childVO.setUserTypeId(obj[4]!=null?(Long)obj[4]:0l);
    					   childVO.setUserType(obj[5]!=null?obj[5].toString():"");
    					   childVO.setLocationLevelId(obj[6]!=null?(Long)obj[6]:0l);
    					   childVO.setLocationLevelName(obj[7]!=null?obj[7].toString():"");
    					   childVO.setLocationValuesSet( new HashSet<Long>(0));
    					   
    					   childMap.put(childActivityMemberId, childVO);
    				   }
    				      childVO =childMap.get(childActivityMemberId); 
    				      childVO.getLocationValuesSet().add(obj[8]!= null ?(Long)obj[8]:0l);
    			   }
    		   }
    	   }catch(Exception e){
    		 e.printStackTrace();
    	   }
    	   return parentMap;
       }
    
       /**
  	  * @author <a href="mailto:sreedhar.itgrids.hyd@gmail.com">SREEDHAR</a>
  	  *  This Service Method is used required child usertype members and 5heir details. 
  	  *  @since 15-SEPTEMBER-2016
  	  */
  	public ActivityMemberVO getRequiredSubLevelActivityMembersDetails(Long parentActivityMemberId,List<Long> childUSerTypeIds){
  		
  		ActivityMemberVO activityMemberVO = null;
  		try{
  			 activityMemberVO =  new ActivityMemberVO();
  			 
  			 activityMemberVO.setActivityMemberId(parentActivityMemberId);
  			 
  			 //Get All sub level userTypes And thier members.
  			 activityMemberVO = getChildActivityMembersAndLocationsNew(activityMemberVO);
  			 Map<Long,Map<Long,UserTypeVO>> userTypesMap = activityMemberVO.getUserTypesMap();
  		     Map<Long,Set<Long>> locationLevelIdsMap = activityMemberVO.getLocationLevelIdsMap();
  		     
  		     activityMemberVO.setUserTypesMap(null);
  		     activityMemberVO.setLocationLevelIdsMap(null);
  		     
  		     //get only required usertypes and their members.
  		     
  		     Map<Long,UserTypeVO> requiredUserTypeMap =  new LinkedHashMap<Long, UserTypeVO>(0);
  		     
  		     if(childUSerTypeIds != null && childUSerTypeIds.size() > 0){
  		    	
  		    	 for(Long childUSerTypeId : childUSerTypeIds ){
  		    		
  		    		Map<Long,UserTypeVO> userTypeCorrespondingActivityMembers = userTypesMap.get(childUSerTypeId);
  		    		
  		    		requiredUserTypeMap.putAll(userTypeCorrespondingActivityMembers);
  		    		
  		    	 }
  		     }
  		     
  		     
  		     Set<Long> requiredLocationLevelIds = new HashSet<Long>();
  		     if(requiredUserTypeMap != null && requiredUserTypeMap.size() > 0){
  		    	 
  		    	 for(Long activityMemberId : requiredUserTypeMap.keySet()){
  		    		 
  		    		 UserTypeVO memberVO = requiredUserTypeMap.get(activityMemberId);
  		    		 requiredLocationLevelIds.add(memberVO.getLocationLevelId());
  		    	 }
  		     }
  		     
  		     Map<Long,Set<Long>> requiredLocationLevelIdsMap = new HashMap<Long, Set<Long>>();
  		     if(requiredLocationLevelIds!=null && requiredLocationLevelIds.size() > 0){
  		    	 
  		    	 for(Long locationLevelId : requiredLocationLevelIds){
  		    		 
  		    		 Set<Long> locationLevelValues = locationLevelIdsMap.get(locationLevelId);
  		    		 requiredLocationLevelIdsMap.put(locationLevelId, locationLevelValues);
  		    	 }
  		     }
  		     activityMemberVO.setActivityMembersMap(requiredUserTypeMap);
  		     activityMemberVO.setLocationLevelIdsMap(requiredLocationLevelIdsMap);
  			 
  		}catch(Exception e) {
  			LOG.error("Exception occurred in getRequiredSubLevelActivityMembersDetails() method in CoreDashboardGenericService class",e);
  			e.printStackTrace();
  		}
  		return activityMemberVO;
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
						values = constituencyDAO.getConstituenciesNamesByIds(new ArrayList<Long>(locationLevelValues));
						suffix = " Parliament";
					}else if(locationLevelId == IConstants.ASSEMBLY_LEVEl_ACCESS_ID){
						values = constituencyDAO.getConstituenciesNamesByIds(new ArrayList<Long>(locationLevelValues));
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
      
      /**   START
 	  *  This  Method is used to get all aub level child userTypes to parent userType.. 
 	  */
  	public List<UserTypeVO> getAllItsSubUserTypeIdsByParentUserTypeId(List<Long> parentUserTypeIdsList){
  		
  		List<UserTypeVO> finalList = null;
  		try{
  			
  			Map<Long,List<UserTypeVO>> allParentChildUserTypesMap = getAllParentChildUserTypesMap();
  			
  			Map<Long,UserTypeVO> finalMap = new TreeMap<Long,UserTypeVO>();
  			
  			getAllSubUserTypes(finalMap,parentUserTypeIdsList,allParentChildUserTypesMap);
  			
  			if(finalMap!=null && finalMap.size() > 0){
  				
  			  
  				if(finalMap!=null && finalMap.size()>0){
					
	  				  //mixing organizing secretery and secretery.
	  					UserTypeVO secOrgSecVO = null;
	  					if(finalMap.containsKey(11l)){//secretery. 
	  						secOrgSecVO = new UserTypeVO();
	  						secOrgSecVO.setShortName("4,11");
	  						secOrgSecVO.setUserType(" ORGANIZING SECRETARY / SECRETARY");
	  						finalMap.remove(11l); 
	  					}
	  					if(finalMap.containsKey(4l)){//organizing secretery.
	  						if(secOrgSecVO==null){
	  							secOrgSecVO = new UserTypeVO();
	  	  						secOrgSecVO.setShortName("4,11");
	  	  						secOrgSecVO.setUserType(" ORGANIZING SECRETARY / SECRETARY");
	  						}
	  					}
	  					if(secOrgSecVO!=null){
	  						finalMap.put(4l, secOrgSecVO); 
	 			    	 }
	  			    	
	  				   /*//mixing mla and constituency incharge.
	  					UserTypeVO mlaConstInchargeVO = null;
	  					if(finalMap.containsKey(9l)){//const incharge. 
	  						mlaConstInchargeVO = new UserTypeVO();
	  						mlaConstInchargeVO.setShortName("7,9");
	  						mlaConstInchargeVO.setUserType(" MLA / CONSTITUENCY INCHARGE ");
	  						finalMap.remove(9l); 
	  					}
	  					if(finalMap.containsKey(7l)){//mla
	  						if(mlaConstInchargeVO==null){
	  							mlaConstInchargeVO = new UserTypeVO();
	  							mlaConstInchargeVO.setShortName("7,9");
	  							mlaConstInchargeVO.setUserType(" MLA / CONSTITUENCY INCHARGE ");
	  						}
	  					}
	  					if(mlaConstInchargeVO!=null){
	  						finalMap.put(7l, mlaConstInchargeVO); 
	 			    	 } */
  			     }
  				
  				finalList = new ArrayList<UserTypeVO>(finalMap.values());
  				UserTypeVO MLACIVO = new UserTypeVO();
  				MLACIVO.setUserType("MLA/CI");
  				MLACIVO.setShortName("9,7");
  				finalList.add(finalList.size(), MLACIVO);
  			}
  			
  			
  		}catch(Exception e){
  			LOG.error("Exception occurred in getAllItsSubUserTypeIdsByParentUserTypeId() ",e);
  		}
  		return finalList;
  	}
  	
  	public void getAllSubUserTypes(Map<Long,UserTypeVO> finalMap,List<Long> parentUserTypeIdsList,Map<Long,List<UserTypeVO>> ParentChildUserTypesMap){
  		
  		try{
  			
  			if(parentUserTypeIdsList != null && parentUserTypeIdsList.size()>0){
  				for (Long parentUserTypeId : parentUserTypeIdsList) {
  					List<UserTypeVO> childUserTypeIds = ParentChildUserTypesMap.get(parentUserTypeId);
  		  			 
  		  			 if(childUserTypeIds!=null && childUserTypeIds.size() > 0)
  		  			 {
  		  				 for(UserTypeVO childUserTypeVO : childUserTypeIds)
  		  				 {
  		  					 Long childUserTypeId = childUserTypeVO.getId();
  		  					 
  		  					 UserTypeVO childVO = finalMap.get(childUserTypeId);
  		  					 
  		  					 if(childVO == null)
  		  					 {
  		  						 UserTypeVO userTypeVO = new UserTypeVO();
  		  						 userTypeVO.setUserTypeId(childUserTypeId);
  		  						 userTypeVO.setUserType(childUserTypeVO.getName());
  		  						 userTypeVO.setShortName(childUserTypeId.toString());
  		  						 finalMap.put(childUserTypeId, userTypeVO);
  		  						List<Long> childUserTypeIdsList = new ArrayList<Long>(0);
  		  						childUserTypeIdsList.add(childUserTypeId);
  		  						 //its child data.
  		  						 getAllSubUserTypes(finalMap,childUserTypeIdsList,ParentChildUserTypesMap);
  		  						 
  		  					 }
  		  				 }
  		  			 }
				}
  			}
  		}catch(Exception e){
  			LOG.error("Exception occurred in getAllSubUserTypes() ",e);
  		}
  	}
  	
  	public Map<Long,List<UserTypeVO>> getAllParentChildUserTypesMap(){
  		
  		Map<Long,List<UserTypeVO>> userTypesMap = new HashMap<Long, List<UserTypeVO>>();
  		
  		List<Object[]> list  = userTypeRelationDAO.getParentUserTypesAndItsChildUserTypes();
  		if( list != null && list.size() > 0){
  			for( Object[] obj : list){
  				
  				if( obj[0] != null){
  					List<UserTypeVO> childUserTypeIds = null;
  					childUserTypeIds = userTypesMap.get((Long)obj[0]);
  					if( childUserTypeIds == null){
  						childUserTypeIds = new ArrayList<UserTypeVO>();
  						userTypesMap.put((Long)obj[0],childUserTypeIds);
  					}
  					childUserTypeIds = userTypesMap.get((Long)obj[0]);
  					UserTypeVO childVO = new UserTypeVO();
  					childVO.setId(obj[2]!=null?(Long)obj[2]:0l);
  					childVO.setName(obj[3]!=null?obj[3].toString():"");
  					childUserTypeIds.add(childVO);
  				}
  			}
  		}
  		return userTypesMap;
  	}
    /**   END */
  	
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
		Double d =0.0d;
		try {
			if(subCount != null && subCount.longValue()>0L && totalCount != null && totalCount.longValue()>0L )
				d = new BigDecimal(subCount * 100.0/totalCount).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
		} catch (Exception e) {
			LOG.error("Exception occurred in caclPercantage() method in CoreDashboardGenericService class",e);
		}
		
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
	
	
	   public void getRequiredCommitteeLevelIdsByUserAccessLevelId(Long userAccessLevelId,CommitteeInputVO inputVO){
			
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
			   
			   if(!committeeBO.getCommitteType().equalsIgnoreCase("boothCommittee")){
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
			   }else{
				   if(committeeBO.getStateIds()!=null && committeeBO.getStateIds().size()>0){
						sb.append(" and model.address.state.stateId in (:tdpCommitteeLevelValues) ");
					}
					else if(committeeBO.getDistrictIds() != null && committeeBO.getDistrictIds().size()>0){
						
						sb.append(" and model.address.district.districtId in (:tdpCommitteeLevelValues) ");
						
					}else if(committeeBO.getParliamentConstIds() != null && committeeBO.getParliamentConstIds().size()>0){
						
						sb.append(" and model.address.parliamentConstituency.constituencyId in (:tdpCommitteeLevelValues) ");
						
					}else if(committeeBO.getAssemblyConstIds() != null && committeeBO.getAssemblyConstIds().size()>0){
						
						sb.append(" and model.address.constituency.constituencyId in (:tdpCommitteeLevelValues) ");
						
					}else if(committeeBO.getTehsilIds()!= null && committeeBO.getTehsilIds().size()>0){
						
						sb.append(" and model.address.tehsil.tehsilId in (:tdpCommitteeLevelValues) ");
					}
			   }
			  
			   return sb;
		   }
		
		 /**
		  * @author <a href="mailto:sreedhar.itgrids.hyd@gmail.com">SREEDHAR</a>
		  *  Generic method : This  Method is used to get the meetings count based on given locations. 
		  *  @since 07-SEPTEMBER-2016
		  */
	 public Map<String,CoreDashboardCountsVO> getMeetingsCountByLocationLevelIdAndLevelValues(Map<Long,Set<Long>> locationLevelIdsMap,CommitteeInputVO committeeBO){
		   
		   Map<String,CoreDashboardCountsVO> locationLevelCountsMap = new HashMap<String, CoreDashboardCountsVO>(0);
		   
		   if(locationLevelIdsMap != null && locationLevelIdsMap.size()>0){
		    	 for(Long userAccessLevelId : locationLevelIdsMap.keySet()){
		    		 
		    		 clearMeetingsLocationLevelIds(committeeBO);
		    		 
		    		 setAppropriateLocationLevelInputsToBO(userAccessLevelId,new ArrayList<Long>(locationLevelIdsMap.get(userAccessLevelId)),committeeBO);
		    		 
		    		 List<Object[]> totalCountList  =  partyMeetingDAO.getLocationWiseMeetingsCountByLocIds(committeeBO); 
		    		 
		    		 List<Object[]> statusCountList =  partyMeetingStatusDAO.getLocationWiseMeetingsStatusCountByLocIds(committeeBO);
		    		 
		    		 setMeetingsCountToItsCorrespondingLocation("total",totalCountList,locationLevelCountsMap,userAccessLevelId);
		    		 setMeetingsCountToItsCorrespondingLocation("meetingStatus",statusCountList,locationLevelCountsMap,userAccessLevelId);
		    	 }    
		     }
		    return locationLevelCountsMap;
	 }
	 public void clearMeetingsLocationLevelIds(CommitteeInputVO inputVO){
			inputVO.setStateIds(null);
			inputVO.setDistrictIds(null);
			inputVO.setParliamentConstIds(null);
			inputVO.setAssemblyConstIds(null);
			inputVO.setTehsilIds(null);
	}
	 public void setMeetingsCountToItsCorrespondingLocation(String status,List<Object[]> list,Map<String,CoreDashboardCountsVO> locationLevelCountsMap,Long accessLevelId){
			
		 try{
			  
		   	 if(list!=null && list.size()>0){
				 for(Object[] obj : list){
					 
					 Long locationId = obj[1]!=null?(Long)obj[1]:0l;
					 if(locationId > 0l){
						 
						 String key = accessLevelId+"_"+(Long)obj[1]; 
						 CoreDashboardCountsVO countsVO = locationLevelCountsMap.get(key);
						 if(countsVO == null){
							 countsVO = new CoreDashboardCountsVO();
							 locationLevelCountsMap.put(key, countsVO);
						 }
						 countsVO = locationLevelCountsMap.get(key);
						 Long count = obj[0]!=null?(Long)obj[0]:0l;
						 
						 if(status.equalsIgnoreCase("total")){
							 countsVO.setTotalCount(count); 
						 }else{
							 String meetingStatus = obj[2]!=null?obj[2].toString():"";
							 if(meetingStatus.equalsIgnoreCase("Y")){
								 countsVO.setConductedCount(count);
							 }else if(meetingStatus.equalsIgnoreCase("N")){
								 countsVO.setNotConductedCount(count);
							 }else if(meetingStatus.equalsIgnoreCase("M")){
								 countsVO.setMayBeCount(count);
							 }
						 }
					 }
				 }
			}
		 }catch(Exception e){
			 LOG.error("Exception occurred in setMeetingsCountToItsCorrespondingLocation() method in CoreDashboardGenericService class",e);
		}

	  }
	  
	 
}
