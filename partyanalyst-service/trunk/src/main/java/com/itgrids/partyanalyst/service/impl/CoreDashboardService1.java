package com.itgrids.partyanalyst.service.impl;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
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
import com.itgrids.partyanalyst.dao.IDelimitationConstituencyAssemblyDetailsDAO;
import com.itgrids.partyanalyst.dao.ITdpBasicCommitteeDAO;
import com.itgrids.partyanalyst.dao.ITdpCommitteeDAO;
import com.itgrids.partyanalyst.dao.ITdpCommitteeLevelDAO;
import com.itgrids.partyanalyst.dao.IUserTypeRelationDAO;
import com.itgrids.partyanalyst.dto.CommitteeDataVO;
import com.itgrids.partyanalyst.dto.CommitteeInputVO;
import com.itgrids.partyanalyst.dto.UserTypeVO;
import com.itgrids.partyanalyst.service.ICoreDashboardService1;
import com.itgrids.partyanalyst.utils.IConstants;

public class CoreDashboardService1 implements ICoreDashboardService1{
	
	private final static Logger LOG = Logger.getLogger(CoreDashboardService1.class);
	
	//Attributes
	 private IDelimitationConstituencyAssemblyDetailsDAO delimitationConstituencyAssemblyDetailsDAO;
	 private ITdpCommitteeDAO tdpCommitteeDAO;
	 private ITdpCommitteeLevelDAO tdpCommitteeLevelDAO;
	 private ITdpBasicCommitteeDAO tdpBasicCommitteeDAO; 
	 private IActivityMemberAccessTypeDAO activityMemberAccessTypeDAO;
	 private IUserTypeRelationDAO userTypeRelationDAO;
	 private IActivityMemberRelationDAO activityMemberRelationDAO;
	//setters
	public void setDelimitationConstituencyAssemblyDetailsDAO(
			IDelimitationConstituencyAssemblyDetailsDAO delimitationConstituencyAssemblyDetailsDAO) {
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
	//business methods.
	
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
	public Double caclPercantage(Long subCount,Long totalCount){
		Double d = new BigDecimal(subCount * 100.0/totalCount).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
		return d;
	}
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
	public Map<Long,List<Long>> getAssemblyConstituencyListByPcList(List<Long> parliamentIds){
		
		 Map<Long,List<Long>> assemblyListByPcMap = new HashMap<Long, List<Long>>();
		 if(parliamentIds != null && parliamentIds.size() > 0){
       	 List<Object[]> list = delimitationConstituencyAssemblyDetailsDAO.getAcConstituenciesByPcList(parliamentIds);
       	 if(list!=null && list.size()>0){
       		 for(Object[] obj : list){
       			 List<Long> acList = null;
       			 acList = assemblyListByPcMap.get((Long)obj[0]);
       			 if(acList == null){
       				 acList = new ArrayList<Long>();
       				 assemblyListByPcMap.put((Long)obj[0],acList);
       			 }
       			 acList = assemblyListByPcMap.get((Long)obj[0]);
       			 acList.add(obj[1]!=null?(Long)obj[1]:0l);
       		 }
       	 }
       	 
        }
	   return assemblyListByPcMap;
	}
	public List<Date> getDates(String startDateString,String endDateString,SimpleDateFormat sdf) throws ParseException{
    	List<Date> datesList = new ArrayList<Date>();
    	Date startDate = null;
    	Date endDate = null;
    	if(startDateString != null && !startDateString.isEmpty()){
	    	 startDate = sdf.parse(startDateString);
	    }
	    if(endDateString != null && !endDateString.isEmpty()){
	    	 endDate = sdf.parse(endDateString);
	    }
	    datesList.add(0,startDate);
	    datesList.add(1,endDate);
	    return datesList;
    }
	 
	
	    /**
		  * @param  String state
		  * @param  List<Long> basicCommitteeIds
		  * @param  String startDateString
		  * @param  String endDateString
		  * @return List<CommitteeDataVO>
		  * @author <a href="mailto:aravind.itgrids.hyd@gmail.com">Aravind</a>
		  *  This Service Method is used to get District Wise Committees Count Report. 
		  *  @since 11-AUGUST-2016
		  */
		public List<CommitteeDataVO> getDistrictWiseCommitteesCountReport(String state,List<Long> basicCommitteeIds,String startDateString,String endDateString){
			
			LOG.info(" entered in to getDistrictWiseCommitteesCountReport() ");
			List<CommitteeDataVO> finalList = null;
			try{
			     List<Date> datesList = getDates(startDateString,endDateString,new SimpleDateFormat("dd/MM/yyyy"));
			     
			     //Making BO.
			     CommitteeInputVO committeeBO = new CommitteeInputVO();
			     //getRequiredCommitteeLevelIds(userAccessLevelId,committeeBO,userAccessLevelValues);
			     committeeBO.setBasicCommitteeIds(basicCommitteeIds);
			     committeeBO.setStartDate(datesList.get(0));
			     committeeBO.setEndDate(datesList.get(1));
			     committeeBO.setState(state);
			     
			     Map<Long,CommitteeDataVO> finalMap = new LinkedHashMap<Long,CommitteeDataVO>(0);
			     //setCommitteeLevelstoMap(committeeLevelMap,committeeBO.getTdpCommitteeLevelIds(),basicCommitteeIds);
			  
			     List<Object[]> totalCommitteesList      =  tdpCommitteeDAO.districtWiseCommitteesCount(committeeBO,null);
			     List<Object[]> completedCommitteesList  =  tdpCommitteeDAO.districtWiseCommitteesCount(committeeBO,"completed");
			     List<Object[]> startedCommitteesList    =  tdpCommitteeDAO.districtWiseCommitteesCount(committeeBO,"started");
			     List<Object[]> notStartedCommitteesList =  tdpCommitteeDAO.districtWiseCommitteesCount(committeeBO,"notStarted");
			     
			     setDistrictWiseCommiteesCount(totalCommitteesList,finalMap,null);
			     setDistrictWiseCommiteesCount(completedCommitteesList,finalMap,"completed");
			     setDistrictWiseCommiteesCount(startedCommitteesList,finalMap,"started");
			     setDistrictWiseCommiteesCount(notStartedCommitteesList,finalMap,"notStarted");
			     
			     if(finalMap != null && finalMap.size() > 0){
			    	 for(Long basicCommitteeId : finalMap.keySet()){
			    		 Map<Long,CommitteeDataVO> LocationMap = finalMap.get(basicCommitteeId).getSubMap();
			    		 if(LocationMap != null){
			    			 finalMap.get(basicCommitteeId).setSubList(new ArrayList<CommitteeDataVO>(LocationMap.values()));
			    			 LocationMap.clear();
			    		 }
			    	 finalList = new ArrayList<CommitteeDataVO>(finalMap.values());
			    	 //getBasicCommitteesPercantage(finalList);
			    		 
			    	 }
			    }
			     
			}catch(Exception e){
				LOG.error("exception occurred in getDistrictWiseCommitteesCountReport()", e);
				e.printStackTrace();
			}
			return finalList;
		}
		
		public void setDistrictWiseCommiteesCount(List<Object[]> list,Map<Long,CommitteeDataVO> finalMap,String status){
			
			if(list !=null && list.size() >0){
				for(Object[] obj : list){
					
					Long tdpbasicCommitteeId = obj[0] != null ? (Long)obj[0] : 0l;
					CommitteeDataVO basicCommitteeVO = null;
					basicCommitteeVO = finalMap.get(tdpbasicCommitteeId);
				    if( basicCommitteeVO == null){
				    	basicCommitteeVO = new CommitteeDataVO();
				    	basicCommitteeVO.setId(tdpbasicCommitteeId);
				    	basicCommitteeVO.setName(obj[1]!=null ? obj[1].toString() : "");
				    	basicCommitteeVO.setSubMap(new LinkedHashMap<Long, CommitteeDataVO>(0));
				    	finalMap.put(tdpbasicCommitteeId,basicCommitteeVO);
				    }
				    basicCommitteeVO = finalMap.get(tdpbasicCommitteeId);
				    CommitteeDataVO locationVO = null;
				    locationVO = basicCommitteeVO.getSubMap().get((Long)obj[2]);
					if(locationVO==null){
						locationVO = new CommitteeDataVO();
						locationVO.setId((Long)obj[2]);
						locationVO.setName(obj[3]!=null ? obj[3].toString() : "");
						basicCommitteeVO.getSubMap().put(locationVO.getId(),locationVO);
					}
					locationVO = basicCommitteeVO.getSubMap().get((Long)obj[2]);
					Long count = obj[4]!=null?(Long)obj[4]:0l;
					if(status != null && !status.isEmpty())
					{
						if(status.equalsIgnoreCase("completed")){
							locationVO.setCompletedCount(basicCommitteeVO.getCompletedCount() +count );
						}else if(status.equalsIgnoreCase("started")){
							locationVO.setStartedCount(basicCommitteeVO.getStartedCount() + count);
						}else if(status.equalsIgnoreCase("notStarted")){
							locationVO.setNotStartedCount(basicCommitteeVO.getNotStartedCount()+count);
						}
					}else{
						locationVO.setTotalCount(basicCommitteeVO.getTotalCount()+count);
					}
					
				}
			}
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
		public List<List<UserTypeVO>> getUserTypeWiseCommitteesCompletedCounts(Long userId,Long activityMemberId,Long userTypeId,String state,List<Long> basicCommitteeIds,String startDateString,String endDateString){
			List<List<UserTypeVO>> userTypesList = null;
			try{ 
			     List<Date> datesList = getDates(startDateString,endDateString,new SimpleDateFormat("dd/MM/yyyy"));
			     
			     //Making BO.
			     CommitteeInputVO committeeBO = new CommitteeInputVO();
			     committeeBO.setBasicCommitteeIds(basicCommitteeIds);
			     committeeBO.setStartDate(datesList.get(0));
			     committeeBO.setEndDate(datesList.get(1));
			     committeeBO.setState(state);
			
			     if( activityMemberId == null || activityMemberId <= 0l){
				    Object[] activityMemberIdAndAccessType= activityMemberAccessTypeDAO.getUserAccessTypeAndActivityMemberIdByUserId(userId);
				    if( activityMemberIdAndAccessType != null && activityMemberIdAndAccessType.length>0){
					   activityMemberId = activityMemberIdAndAccessType[0] != null ? (Long)activityMemberIdAndAccessType[0] : 0l;
					   userTypeId = activityMemberIdAndAccessType[1] != null ? (Long)activityMemberIdAndAccessType[1] : 0l;
				    }
			     }
		   
			     Map<Long,List<Long>> ParentChildUserTypesMap = getParentUserTypesAndItsChildUserTypes();
		         
		         Map<Long,Map<Long,UserTypeVO>> userTypesMap = new LinkedHashMap<Long, Map<Long,UserTypeVO>>(0);
		         Set<Long> parliamentConstituencyIds = new HashSet<Long>();
			     setBasicDataToMap(userTypesMap,activityMemberId,userTypeId,ParentChildUserTypesMap,parliamentConstituencyIds);
			     
			     //get assembly constituencies for patlaiament constituencies.
			     Map<Long,List<Long>> assemblyListByPcMap = null;
			     if(parliamentConstituencyIds != null && parliamentConstituencyIds.size()>0){
		        	 assemblyListByPcMap = getAssemblyConstituencyListByPcList(new ArrayList<Long>(parliamentConstituencyIds));
		         }
			     
			     
		       //get member related counts.
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
							   getRequiredCommitteeLevelIds(memberVO.getLocationLevelId(),committeeBO,new ArrayList<Long>(memberVO.getLocationValuesSet()),assemblyListByPcMap);
							   Long totalCount = tdpCommitteeDAO.getCountsForCommittees(committeeBO,null);
							   Long completedCount =  tdpCommitteeDAO.getCountsForCommittees(committeeBO,"completed");
							   memberVO.setTotalCount(totalCount != null ? totalCount :0l);
							   memberVO.setCompletedCount(completedCount!= null ? completedCount :0l);
							   if(totalCount!=null && totalCount > 0l){
								   memberVO.setCompletedPerc( caclPercantage(memberVO.getCompletedCount(),memberVO.getTotalCount()) );
							   }
						   }
					   }
					   userTypesList.add(new ArrayList<UserTypeVO>(membersMap.values()));
				   }
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
		public void setBasicDataToMap( Map<Long,Map<Long,UserTypeVO>> userTypesMap , Long activityMemberId ,Long userTypeId, Map<Long,List<Long>> ParentChildUserTypesMap,Set<Long> parliamentConstituencyIds){
			try{
				
				List<Long> childUserTypeIds = ParentChildUserTypesMap.get(userTypeId);
				
				if(childUserTypeIds != null){
					
					List<Object[]> childDetails = null;
					if( userTypeId.longValue() == IConstants.COUNTRY_USER_TYPE_ID.longValue() || userTypeId.longValue() == IConstants.STATE_USER_TYPE_ID.longValue()){
						childDetails = activityMemberAccessTypeDAO.getAllActivityMembersOfGSAndDistAndMpUserTypes(childUserTypeIds);
					}else{
						childDetails = activityMemberRelationDAO.getChildUserTypeMembers(activityMemberId, childUserTypeIds);
					}
					
					if(childDetails != null && childDetails.size() > 0)
					{
						for(Object[] obj : childDetails)
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
								 setBasicDataToMap(userTypesMap,activityMemberVO.getActivityMemberId(),activityMemberVO.getUserTypeId(),ParentChildUserTypesMap,parliamentConstituencyIds);
								 
							 }
							 activityMemberVO = membersMap.get(memberid);
							 activityMemberVO.getLocationValuesSet().add(obj[7]!= null ?(Long)obj[7]:0l);
							 //take all parliament constituency ids.
							 if(activityMemberVO.getLocationLevelId().longValue() == IConstants.PARLIAMENT_LEVEl_ACCESS_ID.longValue()){
								 parliamentConstituencyIds.add(obj[7]!= null ?(Long)obj[7]:0l);
							 }
							
						}
					}
				}
				
			}catch(Exception e){
				e.printStackTrace();
			}
		}
		public void getRequiredCommitteeLevelIds(Long userAccessLevelId,CommitteeInputVO inputVO,List<Long> userAccessLevelValues,Map<Long,List<Long>> assemblyListByPcMap){
			
			if(userAccessLevelId.longValue() == IConstants.STATE_LEVEl_ACCESS_ID.longValue() ){
				
				inputVO.setTdpCommitteeLevelIds(Arrays.asList(IConstants.STATE_ACCESS_REQUIED_COMMITTEE_LEVEl_IDS));	
				inputVO.setStateIds(userAccessLevelValues);
				
			}else if(userAccessLevelId.longValue() == IConstants.DISTRICT_LEVEl_ACCESS_ID.longValue() ){
				
				inputVO.setTdpCommitteeLevelIds(Arrays.asList(IConstants.DISTRICT_ACCESS_REQUIED_COMMITTEE_LEVEl_IDS));
				inputVO.setDistrictIds(userAccessLevelValues);
				
			}else if(userAccessLevelId.longValue() == IConstants.PARLIAMENT_LEVEl_ACCESS_ID.longValue()){
				
				inputVO.setTdpCommitteeLevelIds(Arrays.asList(IConstants.CONSTITUENCY_ACCESS_REQUIED_COMMITTEE_LEVEl_IDS));
				if(userAccessLevelValues != null && userAccessLevelValues.size() > 0){
					if(inputVO.getAssemblyConstIds() == null){
						inputVO.setAssemblyConstIds(new ArrayList<Long>(0));
					}
					for(Long parliamentId : userAccessLevelValues){
						inputVO.getAssemblyConstIds().addAll(assemblyListByPcMap.get(parliamentId));
					}
				}
			}else if(userAccessLevelId.longValue() == IConstants.ASSEMBLY_LEVEl_ACCESS_ID.longValue()){
				
				inputVO.setTdpCommitteeLevelIds(Arrays.asList(IConstants.CONSTITUENCY_ACCESS_REQUIED_COMMITTEE_LEVEl_IDS));
				inputVO.setAssemblyConstIds(userAccessLevelValues);
				
		    }else if(userAccessLevelId.longValue() == IConstants.MANDAL_LEVEl_ID.longValue()){
				
				inputVO.setTdpCommitteeLevelIds(Arrays.asList(IConstants.MANDAL_ACCESS_REQUIED_COMMITTEE_LEVEl_IDS));
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
				
		
}
