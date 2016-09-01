package com.itgrids.partyanalyst.service.impl;

import java.math.BigDecimal;
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
import java.util.Map.Entry;
import java.util.Set;

import org.apache.log4j.Logger;

import com.itgrids.partyanalyst.dao.IActivityMemberAccessLevelDAO;
import com.itgrids.partyanalyst.dao.IActivityMemberAccessTypeDAO;
import com.itgrids.partyanalyst.dao.IActivityMemberRelationDAO;
import com.itgrids.partyanalyst.dao.ICharacteristicsDAO;
import com.itgrids.partyanalyst.dao.IDebateParticipantCharcsDAO;
import com.itgrids.partyanalyst.dao.IDebateParticipantDAO;
import com.itgrids.partyanalyst.dao.IDelimitationConstituencyAssemblyDetailsDAO;
import com.itgrids.partyanalyst.dao.ITdpBasicCommitteeDAO;
import com.itgrids.partyanalyst.dao.ITdpCommitteeDAO;
import com.itgrids.partyanalyst.dao.ITdpCommitteeLevelDAO;
import com.itgrids.partyanalyst.dao.ITdpCommitteeMemberDAO;
import com.itgrids.partyanalyst.dao.ITrainingCampAttendanceDAO;
import com.itgrids.partyanalyst.dao.IUserTypeRelationDAO;
import com.itgrids.partyanalyst.dto.ActivityMemberVO;
import com.itgrids.partyanalyst.dto.CommitteeDataVO;
import com.itgrids.partyanalyst.dto.CommitteeInputVO;
import com.itgrids.partyanalyst.dto.CoreDebateVO;
import com.itgrids.partyanalyst.dto.TrainingCampProgramVO;
import com.itgrids.partyanalyst.dto.UserDataVO;
import com.itgrids.partyanalyst.dto.UserTypeVO;
import com.itgrids.partyanalyst.model.Characteristics;
import com.itgrids.partyanalyst.service.ICoreDashboardGenericService;
import com.itgrids.partyanalyst.service.ICoreDashboardMainService;
import com.itgrids.partyanalyst.utils.CommonMethodsUtilService;
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
	 
	 private ITdpCommitteeMemberDAO tdpCommitteeMemberDAO;
	 private ITrainingCampAttendanceDAO trainingCampAttendanceDAO;
	 
	 private IDebateParticipantDAO debateParticipantDAO;
	 private IDebateParticipantCharcsDAO debateParticipantCharcsDAO;
	 private CommonMethodsUtilService commonMethodsUtilService;
	 private ICharacteristicsDAO characteristicsDAO;
	 
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
	public void setTdpCommitteeMemberDAO(
			ITdpCommitteeMemberDAO tdpCommitteeMemberDAO) {
		this.tdpCommitteeMemberDAO = tdpCommitteeMemberDAO;
	}
	public void setTrainingCampAttendanceDAO(
			ITrainingCampAttendanceDAO trainingCampAttendanceDAO) {
		this.trainingCampAttendanceDAO = trainingCampAttendanceDAO;
	}
	
	public void setDebateParticipantDAO(IDebateParticipantDAO debateParticipantDAO) {
		this.debateParticipantDAO = debateParticipantDAO;
	}
	public void setDebateParticipantCharcsDAO(
			IDebateParticipantCharcsDAO debateParticipantCharcsDAO) {
		this.debateParticipantCharcsDAO = debateParticipantCharcsDAO;
	}	
	
	
	public void setCommonMethodsUtilService(
			CommonMethodsUtilService commonMethodsUtilService) {
		this.commonMethodsUtilService = commonMethodsUtilService;
	}
	
	public void setCharacteristicsDAO(ICharacteristicsDAO characteristicsDAO) {
		this.characteristicsDAO = characteristicsDAO;
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
	  *  This Service Method is used to get the main and affliated committees total and completed and started counts level wise based on the user access levels. 
	  *  @since 29-JULY-2016
	  */
	public CommitteeDataVO getCommitteesBasicCountReport(Long userAccessLevelId,List<Long> userAccessLevelValues,String state,List<Long> basicCommitteeIds,String dateString){
		
		LOG.info(" entered in to getCommitteesBAsicCountReport() ");
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		CommitteeDataVO finalVO = new CommitteeDataVO();
		Date startDate = null;
		Date endDate = null;
		try{
		     
		     //Create Business object.
		     CommitteeInputVO committeeBO = new CommitteeInputVO();
		     coreDashboardGenericService.getRequiredCommitteeLevelIdsByUserAccessLevelId(userAccessLevelId,committeeBO);
		     coreDashboardGenericService.setAppropriateLocationLevelInputsToBO(userAccessLevelId,userAccessLevelValues,committeeBO);
		     committeeBO.setBasicCommitteeIds(basicCommitteeIds);
		     if(dateString != null && !dateString.isEmpty()){
			    committeeBO.setDate(sdf.parse(dateString));
			 }
		     Long stateId = coreDashboardGenericService.getStateIdByState(state);
		     committeeBO.setStateId(stateId);
		     
		     Map<Long,CommitteeDataVO> committeeLevelMap = new LinkedHashMap<Long,CommitteeDataVO>(0);
		     setCommitteeLevelstoMap(committeeLevelMap,committeeBO.getTdpCommitteeLevelIds());
		  
		     List<Object[]> totalCommitteesList     =  tdpCommitteeDAO.getCommitteesTotalOrStartedOrCompletedCount(committeeBO);
		     committeeBO.setStatus("completed");
		     List<Object[]> completedCommitteesList =  tdpCommitteeDAO.getCommitteesTotalOrStartedOrCompletedCount(committeeBO);
		     committeeBO.setStatus("started");
		     List<Object[]> startedCommitteesList   =  tdpCommitteeDAO.getCommitteesTotalOrStartedOrCompletedCount(committeeBO);
		     
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
			inputVO.setParliamentConstIds(userAccessLevelValues);
			
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
		     activityMemberVO = coreDashboardGenericService.getChildActivityMembersAndLocationsNew(activityMemberVO);
		     Map<Long,Map<Long,UserTypeVO>> userTypesMap = activityMemberVO.getUserTypesMap();
		     Map<Long,Set<Long>> locationLevelIdsMap = activityMemberVO.getLocationLevelIdsMap();
	         
		   //get commitees count based on location level id and location level values.
		     List<String> statusList = new ArrayList<String>();
		     statusList.add("completed");
		     committeeBO.setStatusList(statusList);
		     Map<String,UserTypeVO> locationLevelCountsMap = getCommitteesCountByLocationLevelIdAndLevelValues(locationLevelIdsMap,committeeBO);
		     
		     getMemberRelatedCountsOrPercantage(userTypesMap,"counts",locationLevelCountsMap);
		     getMemberRelatedCountsOrPercantage(userTypesMap,"percanatge",null);
		     
		    //mixing secreteries and general secreteries.
		     if(userTypesMap!=null && userTypesMap.size()>0){
		    	 
		    	 Map<Long,UserTypeVO> orgSecAndSecMap = new LinkedHashMap<Long,UserTypeVO>();
		    	 
		    	 Map<Long,UserTypeVO>  secreteriesMap = null;
		    	 if(userTypesMap.containsKey(11l)){
		    		 secreteriesMap = userTypesMap.get(11l);
		    		 orgSecAndSecMap.putAll(secreteriesMap);
		    		 //remove secreteries from Map
		    		 userTypesMap.remove(11l); 
		    	 }
		    	 
		    	 Map<Long,UserTypeVO>  organizingSecreteriesMap = null;
		    	 if(userTypesMap.containsKey(4l)){
		    		 organizingSecreteriesMap = userTypesMap.get(4l);
		    		 orgSecAndSecMap.putAll(organizingSecreteriesMap);
		    	 }
		    	
		    	 if(organizingSecreteriesMap!=null && organizingSecreteriesMap.size()>0){
		    		 userTypesMap.put(4l, orgSecAndSecMap); 
		    	 }
		    	 
		     }
		     
		     //converting Map to list.
		     if(userTypesMap != null && userTypesMap.size() > 0){
		    	 userTypesList = new ArrayList<List<UserTypeVO>>();
		    	 for(Long userType:userTypesMap.keySet()){
		    		 Map<Long,UserTypeVO> membersMap = userTypesMap.get(userType);
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
			LOG.error("exception occurred in getUserTypeWiseCommitteesCompletedCounts1()", e);
		}
	    return userTypesList;
	}
   
	
	public void getMemberRelatedCountsOrPercantage(Map<Long,Map<Long,UserTypeVO>> userTypesMap,String type,Map<String,UserTypeVO> locationLevelCountsMap){
		
   	//get member related counts or percanatges
		
		   if( userTypesMap != null && userTypesMap.size() > 0)
		   {   
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
			   }
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
		public List<CommitteeDataVO> getLevelWiseBasicCommitteesCountReport(Long userAccessLevelId,List<Long> userAccessLevelValues,String state,List<Long> basicCommitteeIds,String dateString){
		
		LOG.info(" entered in to getLevelWiseBasicCommitteesCountReport() ");
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		List<CommitteeDataVO> finalList = null;
		Date startDate = null;
		Date endDate = null;
		try{
			 
		     
		     //Making BO.
		     CommitteeInputVO committeeBO = new CommitteeInputVO();
		     coreDashboardGenericService.getRequiredCommitteeLevelIdsByUserAccessLevelId(userAccessLevelId,committeeBO);
		     coreDashboardGenericService.setAppropriateLocationLevelInputsToBO(userAccessLevelId,userAccessLevelValues,committeeBO);
		     committeeBO.setBasicCommitteeIds(basicCommitteeIds);
		     if(dateString != null && !dateString.isEmpty()){
				committeeBO.setDate(sdf.parse(dateString));
			 }
		     Long stateId = coreDashboardGenericService.getStateIdByState(state);
		     committeeBO.setStateId(stateId);
		     
		     Map<Long,CommitteeDataVO> committeeLevelMap = new LinkedHashMap<Long,CommitteeDataVO>(0);
		     setCommitteeLevelstoMap(committeeLevelMap,committeeBO.getTdpCommitteeLevelIds(),basicCommitteeIds);
		  
		     List<Object[]> totalCommitteesList      =  tdpCommitteeDAO.levelWiseBasicCommitteesCount(committeeBO);
		     
		     committeeBO.setStatus("completed");
		     List<Object[]> completedCommitteesList  =  tdpCommitteeDAO.levelWiseBasicCommitteesCount(committeeBO);
		     
		     committeeBO.setStatus("started");
		     List<Object[]> startedCommitteesList    =  tdpCommitteeDAO.levelWiseBasicCommitteesCount(committeeBO);
		     
		     committeeBO.setStatus("notStarted");
		     List<Object[]> notStartedCommitteesList =  tdpCommitteeDAO.levelWiseBasicCommitteesCount(committeeBO);
		     
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
		public List<CommitteeDataVO> committeesPerformanceCohort(List<Long> tdpCommitteeLevelIdsClicked,List<Long> basicCommitteeIds,String committeeStatus,Long userLocationLevelId,List<Long> userLocationLevelValues,String dateString,String state){
		    List<CommitteeDataVO> finalList = null;
		    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		    try{
		      
		      //CREATING BUSINESS OBJECT.
		      CommitteeInputVO committeeBO = new CommitteeInputVO();
		      committeeBO.setTdpCommitteeLevelIds(tdpCommitteeLevelIdsClicked);
		      committeeBO.setBasicCommitteeIds(basicCommitteeIds);
		      if(dateString != null && !dateString.isEmpty()){
		         committeeBO.setDate(sdf.parse(dateString));
		       }
		      Long stateId = coreDashboardGenericService.getStateIdByState(state);
		      committeeBO.setStateId(stateId);
		      setAppropriateLocationLevelInputsToBO(userLocationLevelId,committeeBO,userLocationLevelValues);
		      
		      Map<Long,CommitteeDataVO> finalMap = new LinkedHashMap<Long,CommitteeDataVO>();
		      
		      //finding grouping location.
		      List<String> groupingLocationsList = new ArrayList<String>();
		      if(userLocationLevelValues != null && userLocationLevelValues.size()>0){
		    	  
		    	  if(userLocationLevelValues.size()>1){
		    		  
		    		  String groupingLocationConsider = "self";
		    		  if(userLocationLevelId == 2 ){//user_level table
		    			  groupingLocationsList.add("State");
					   }else if(userLocationLevelId == 3){
						   groupingLocationsList.add("District");
					   }else if(userLocationLevelId == 4){
						   groupingLocationsList.add("Parliament");
					   }else if(userLocationLevelId == 5){
						   groupingLocationsList.add("Constituency");
					   }else if(userLocationLevelId == 6){
						   groupingLocationsList.add("Mandal");
						   groupingLocationsList.add("LocalElectionbody");
					   }
		    		  
		    	  }else if(userLocationLevelValues.size()==1){
		    		  
		    		  String groupingLocationConsider = "subLevel";
	    			  if(userLocationLevelId == 2 ){//user_level table
	    				  groupingLocationsList.add("District");
					   }else if(userLocationLevelId == 3){
						   groupingLocationsList.add("Constituency");
					   }else if(userLocationLevelId == 4){
						   groupingLocationsList.add("Constituency");
					   }else if(userLocationLevelId == 5){
						   groupingLocationsList.add("Mandal");
						   groupingLocationsList.add("LocalElectionbody");
					   }else if(userLocationLevelId == 6){
						   groupingLocationsList.add("Village");
						   groupingLocationsList.add("Ward");
					   }
		    	  }
		      }
		      
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
		    	LOG.error("exception occurred in committeesPerformanceCohort()", e);
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
		    	 //sorting in descending order of completed percantages.
		    	 Collections.sort(activityMembersList,ActivityMemberCompletedCountPercDesc);
		     }
	   }catch(Exception e){
		   LOG.error("exception occurred in getSelectedChildUserTypeMembers()", e);
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
	  *  Generic method : This  Method is used to get the committees count based on given locations. 
	  */
   public Map<String,UserTypeVO> getCommitteesCountByLocationLevelIdAndLevelValues(Map<Long,Set<Long>> locationLevelIdsMap,CommitteeInputVO committeeBO){
	   
	   Map<String,UserTypeVO> locationLevelCountsMap = new HashMap<String, UserTypeVO>(0);
	   
	   if(locationLevelIdsMap != null && locationLevelIdsMap.size()>0){
	    	 for(Long userAccessLevelId : locationLevelIdsMap.keySet()){
	    		 clearLocationLevelIds(committeeBO);
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
   public void clearLocationLevelIds(CommitteeInputVO inputVO){
			inputVO.setStateIds(null);
			inputVO.setDistrictIds(null);
			inputVO.setParliamentConstIds(null);
			inputVO.setAssemblyConstIds(null);
			inputVO.setTehsilIds(null);
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
		    	  
		    	//sorting in descending order of completed percantages.
			      Collections.sort(activityMembersList,ActivityMemberCompletedCountPercDesc);
		     }
	   }catch(Exception e){
		   LOG.error("exception occurred in getDirectChildActivityMemberCommitteeDetails()", e);
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
						   Collections.sort(committeeLevelVO.getSubList(),BasicCommitteesCompletedCountPercASC);
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
			   Collections.sort(cumulativesList,BasicCommitteesCompletedCountPercASC);
			   //add to finalVO
			   finalVO.setSubList1(cumulativesList);
		    }
			 
			 
	} catch (Exception e) {
		LOG.error("exception occurred in getTopPoorPerformancecommittees()", e);
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
		   LOG.error("exception occurred in setLevelWiseBasicCommitteesCounts()", e);
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
     
	 public static Comparator<CommitteeDataVO> BasicCommitteesCompletedCountPercASC = new Comparator<CommitteeDataVO>() {
	     public int compare(CommitteeDataVO member2, CommitteeDataVO member1) {
	
	        Double perc2 = member2.getCompletedPerc();
	        Double perc1 = member1.getCompletedPerc();
	        //ascending order of percantages.
	         return perc2.compareTo(perc1);
	    }
	}; 
 
	/**
	  * @author <a href="mailto:sreedhar.itgrids.hyd@gmail.com">SREEDHAR</a>
	  *  This Service Method is used to get the getTopPoorPerformancecommittees.
	  *  @since 29-AUGUST-2016
	  */
	public List<CommitteeDataVO> getTopPoorCommitteeLocations(Long activityMemberId,List<Long> basicCommitteeIds,String state,String dateString){
		   List<CommitteeDataVO> finalList = null;
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
			     CommitteeInputVO committeeBO = new CommitteeInputVO();
			     committeeBO.setBasicCommitteeIds(basicCommitteeIds);
			     Long stateId = coreDashboardGenericService.getStateIdByState(state);
			     committeeBO.setStateId(stateId);
			     if(dateString != null && !dateString.isEmpty()){
			    	 committeeBO.setDate(sdf.parse(dateString));
			     }
			     
			     setAppropriateLocationLevelInputsToBO(userLocationLevelId,committeeBO,userLocationLevelValues);
			     
			     committeeBO.setQueryString(coreDashboardGenericService.getCommittesRelatedLocationQuerypart(committeeBO));
			     
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
			    
			     Map<Long,CommitteeDataVO> finalMap = new LinkedHashMap<Long,CommitteeDataVO>();
			     
			     if(groupingLocationsList != null && groupingLocationsList.size() > 0){
				        for(String groupingLocation : groupingLocationsList){
				          
				          committeeBO.setGroupingLocation(groupingLocation);
				          
				          List<Object[]>  totalList   = tdpCommitteeDAO.getTopPoorCommitteeLocations(committeeBO);
				          committeeBO.setStatus("completed"); 
				          List<Object[]> completedList = tdpCommitteeDAO.getTopPoorCommitteeLocations(committeeBO);
				          
				          locationCommitteeCountSetting(totalList,finalMap,null,groupingLocation);
				          locationCommitteeCountSetting(completedList,finalMap,"completed",groupingLocation);
				          
				        }
				   }
			     
			     if(finalMap != null && finalMap.size()>0){
			    	 finalList = new ArrayList<CommitteeDataVO>(finalMap.values());
			    	 //calculating percantages
			    	 for(CommitteeDataVO locationVO : finalList){
			    		 if(locationVO.getTotalCount()!=null && locationVO.getTotalCount() > 0l){
			    			 locationVO.setCompletedPerc(coreDashboardGenericService.caclPercantage(locationVO.getCompletedCount(),locationVO.getTotalCount()) );
						  }
			    	 }
			    	 //sorting 
			    	 Collections.sort(finalList,BasicCommitteesCompletedCountPercASC);
			    	 finalList.get(0).setRequiredName(returnLevelName);
			     }
			     
	 	  }catch(Exception e){
	 		 LOG.error("exception occurred in getTopPoorCommitteeLocations()", e);
		  }
		   return finalList;
	   }
	  
	   public void locationCommitteeCountSetting(List<Object[]> list,Map<Long,CommitteeDataVO> finalMap,String status,String groupingLocation){
		    
		    if(list !=null && list.size() >0){
		      for(Object[] obj : list){
		         
			    	  Long locationId = obj[1]!=null ? (Long)obj[1]:0l;
			    	  
			          CommitteeDataVO locationVO = null;
			          locationVO = finalMap.get(locationId);
			          if(locationVO==null){
				          locationVO = new CommitteeDataVO();
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
			        if(status != null && !status.isEmpty())
			        {
			          if(status.equalsIgnoreCase("completed")){
			            locationVO.setCompletedCount(locationVO.getCompletedCount() +count );
			          }
			        }else{
			          locationVO.setTotalCount(locationVO.getTotalCount()+count);
			        }
		      }
		   }
		    
	  }
	   /**
		  * @param  Long userAccessLevelId
		  * @param List<Long> userAccessLevelValues
		  * @return TrainingCampProgramVO
		  * @author Santosh 
		  * @Description :This Service Method is used to get training camp basic count details.. 
		  *  @since 23-AUGUST-2016
		  */
	public TrainingCampProgramVO getTrainingCampBasicDetailsCntOverview(Long userAccessLevelId,List<Long> userAccessLevelValues){
		 
	 TrainingCampProgramVO finalResultVO = new TrainingCampProgramVO();
		 
		 Map<Long,Long> trainingCampProgramAttendedCntMap = new HashMap<Long, Long>();
		 Map<Long,TrainingCampProgramVO> trainingCampProgramEligibleCntMap = new HashMap<Long, TrainingCampProgramVO>();
		 Map<String,Long> totalEligibleMemberCntMap = new HashMap<String, Long>();
		 Map<String,Long> totalAttenedMemberCntMap = new HashMap<String, Long>();
		 
		 try{
			 List<Object[]> rtrnElgbleMemberForProgramObjList = tdpCommitteeMemberDAO.getTotalEligibleMembersForTrainingCampProgram(userAccessLevelId, userAccessLevelValues);//Eligible Member For Training Program
			 List<Object[]> rtrnAttendedMemberForProgramObjList = trainingCampAttendanceDAO.getTotalAttenedCadresByTrainingCampProgram(userAccessLevelId, userAccessLevelValues);//Attended Member In Training Program
			 if(rtrnAttendedMemberForProgramObjList != null && rtrnAttendedMemberForProgramObjList.size() > 0){
				 for (Object[] param : rtrnAttendedMemberForProgramObjList) {
					 trainingCampProgramAttendedCntMap.put((Long)param[0],param[2] != null ? (Long)param[2]:0l);
				}
			 }
			 if(rtrnElgbleMemberForProgramObjList != null && rtrnElgbleMemberForProgramObjList.size() > 0){
				 for (Object[] param : rtrnElgbleMemberForProgramObjList) {
					TrainingCampProgramVO programVO = new TrainingCampProgramVO();
					Long programId= param[0] != null ? (Long)param[0] :0l;
					programVO.setId(programId);
					programVO.setName(param[1] != null ? param[1].toString():"");
					programVO.setTotalEligibleCount(param[2] != null ? (Long)param[2]:0l);
					programVO.setTotalEligibleCountPer(calculatePercantage(programVO.getTotalEligibleCount(), programVO.getTotalEligibleCount()));
					if(trainingCampProgramAttendedCntMap.get(programId)!=null){
						programVO.setTotalAttenedCount(trainingCampProgramAttendedCntMap.get(programId));
					}
					programVO.setTotalAttenedCountPer(calculatePercantage(programVO.getTotalAttenedCount(),programVO.getTotalEligibleCount()));
					if(programVO.getTotalEligibleCount()>0l){
						programVO.setTotalNotAttenedCount(programVO.getTotalEligibleCount()-programVO.getTotalAttenedCount());	
					}
					programVO.setTotalNotAttenedCountPer(calculatePercantage(programVO.getTotalNotAttenedCount(),programVO.getTotalEligibleCount()));
					trainingCampProgramEligibleCntMap.put(programId, programVO);
				}
			 }
			List<Object[]> rtrnTtlElgbleMmbrObjList = tdpCommitteeMemberDAO.getLevelWiseTotalEligibleMembersForTrainingCampProgram(userAccessLevelId, userAccessLevelValues);//Level Wise Eligible Member
			List<Object[]> rtrnTtlAttendedMmbrObjList = trainingCampAttendanceDAO.getTotalAttenedCadresByCommitteeLevel(userAccessLevelId, userAccessLevelValues);//Level Wise Attended Member
		
			totalEligibleMemberCntMap.put("totalElibible", 0l);//to avoid NullPointerException
			totalAttenedMemberCntMap.put("totalAttended", 0l);//to avoid NullPointerException
			setEligibleMemberCntToMap(rtrnTtlElgbleMmbrObjList,totalEligibleMemberCntMap,"villageWard");
			setAttendedMemberCntToMap(rtrnTtlAttendedMmbrObjList,totalAttenedMemberCntMap,"villageWard");
			
			  TrainingCampProgramVO villageWardVO = new TrainingCampProgramVO();
			   villageWardVO.setName("Village/Ward");
			   if(totalEligibleMemberCntMap.get("totalElibible") != null){
				   villageWardVO.setTotalEligibleCount(totalEligibleMemberCntMap.get("totalElibible"));   
			   }
			   if(totalAttenedMemberCntMap.get("totalAttended") != null){
				   villageWardVO.setTotalAttenedCount(totalAttenedMemberCntMap.get("totalAttended"));   
			   }
			   villageWardVO.setTotalAttenedCountPer(calculatePercantage(villageWardVO.getTotalAttenedCount(),villageWardVO.getTotalEligibleCount()));
			   if(villageWardVO.getTotalEligibleCount() > 0l){
				   villageWardVO.setTotalNotAttenedCount(villageWardVO.getTotalEligibleCount()-villageWardVO.getTotalAttenedCount());   
			   }
			   villageWardVO.setTotalNotAttenedCountPer(calculatePercantage(villageWardVO.getTotalNotAttenedCount(), villageWardVO.getTotalEligibleCount()));
			   totalEligibleMemberCntMap.clear();
			   totalAttenedMemberCntMap.clear(); 
		       finalResultVO.setVillageWardVO(villageWardVO);
			   
			  totalEligibleMemberCntMap.put("totalElibible", 0l);//to avoid NullPointerException
			  totalAttenedMemberCntMap.put("totalAttended", 0l);//to avoid NullPointerException
			  setEligibleMemberCntToMap(rtrnTtlElgbleMmbrObjList,totalEligibleMemberCntMap,"mandalTwnDiv");
			  setAttendedMemberCntToMap(rtrnTtlAttendedMmbrObjList,totalAttenedMemberCntMap,"mandalTwnDiv");
		 
		     TrainingCampProgramVO manTwnDivVO = new TrainingCampProgramVO();
		 	  manTwnDivVO.setName("Mandal/Town/Division");
			  if(totalEligibleMemberCntMap.get("totalElibible") != null){
				 manTwnDivVO.setTotalEligibleCount(totalEligibleMemberCntMap.get("totalElibible"));  
			  }
			  if(totalAttenedMemberCntMap.get("totalAttended") != null){
				  manTwnDivVO.setTotalAttenedCount(totalAttenedMemberCntMap.get("totalAttended"));	  
			  }
			  manTwnDivVO.setTotalAttenedCountPer(calculatePercantage(manTwnDivVO.getTotalAttenedCount(),manTwnDivVO.getTotalEligibleCount()));
			  if(manTwnDivVO.getTotalEligibleCount() > 0){
				  manTwnDivVO.setTotalNotAttenedCount(manTwnDivVO.getTotalEligibleCount()-manTwnDivVO.getTotalAttenedCount());	  
			  }
			  manTwnDivVO.setTotalNotAttenedCountPer(calculatePercantage(manTwnDivVO.getTotalNotAttenedCount(), manTwnDivVO.getTotalEligibleCount()));
			  finalResultVO.setMandalTownDivisionVO(manTwnDivVO); 
			  totalEligibleMemberCntMap.clear();
			  totalAttenedMemberCntMap.clear();
	      
		      totalEligibleMemberCntMap.put("totalElibible", 0l);//to avoid NullPointerException
			  totalAttenedMemberCntMap.put("totalAttended", 0l);//to avoid NullPointerException
			  setEligibleMemberCntToMap(rtrnTtlElgbleMmbrObjList,totalEligibleMemberCntMap,"district");
			  setAttendedMemberCntToMap(rtrnTtlAttendedMmbrObjList,totalAttenedMemberCntMap,"district");
			  
		     TrainingCampProgramVO districtVO = new TrainingCampProgramVO();
		      districtVO.setName("District");
		      if(totalEligibleMemberCntMap.get("totalElibible") != null){
		    	districtVO.setTotalEligibleCount(totalEligibleMemberCntMap.get("totalElibible"));  
			  }
			  if(totalAttenedMemberCntMap.get("totalAttended") != null){
				  districtVO.setTotalAttenedCount(totalAttenedMemberCntMap.get("totalAttended"));	  
			  }
			    districtVO.setTotalAttenedCountPer(calculatePercantage(districtVO.getTotalAttenedCount(),districtVO.getTotalEligibleCount()));
			  if(districtVO.getTotalEligibleCount() > 0){
				  districtVO.setTotalNotAttenedCount(districtVO.getTotalEligibleCount()-districtVO.getTotalAttenedCount());	  
			  }
			  districtVO.setTotalNotAttenedCountPer(calculatePercantage(districtVO.getTotalNotAttenedCount(), districtVO.getTotalEligibleCount()));
	          finalResultVO.setDistrictVO(districtVO);
	          totalEligibleMemberCntMap.clear();
	          totalAttenedMemberCntMap.clear();
		          
	          totalEligibleMemberCntMap.put("totalElibible", 0l);//to avoid NullPointerException
	    	  totalAttenedMemberCntMap.put("totalAttended", 0l);//to avoid NullPointerException
	    	  setEligibleMemberCntToMap(rtrnTtlElgbleMmbrObjList,totalEligibleMemberCntMap,"state");
	    	  setAttendedMemberCntToMap(rtrnTtlAttendedMmbrObjList,totalAttenedMemberCntMap,"state");
	    	  
	          TrainingCampProgramVO stateVO = new TrainingCampProgramVO();
	           stateVO.setName("State");
		      if(totalEligibleMemberCntMap.get("totalElibible") != null){
		    	  stateVO.setTotalEligibleCount(totalEligibleMemberCntMap.get("totalElibible"));  
			  }
			  if(totalAttenedMemberCntMap.get("totalAttended") != null){
				  stateVO.setTotalAttenedCount(totalAttenedMemberCntMap.get("totalAttended"));	  
			  }
			  stateVO.setTotalAttenedCountPer(calculatePercantage(stateVO.getTotalAttenedCount(),stateVO.getTotalEligibleCount()));
			  if(stateVO.getTotalEligibleCount() > 0){
				  stateVO.setTotalNotAttenedCount(stateVO.getTotalEligibleCount()-stateVO.getTotalAttenedCount());	  
			  }
			  stateVO.setTotalNotAttenedCountPer(calculatePercantage(stateVO.getTotalNotAttenedCount(), stateVO.getTotalEligibleCount()));
	           finalResultVO.setStateVO(stateVO);
	           totalEligibleMemberCntMap.clear();
	           totalAttenedMemberCntMap.clear();  
	          
		  if(trainingCampProgramEligibleCntMap != null && trainingCampProgramEligibleCntMap.size() > 0){
			  finalResultVO.setTrainingProgramList(new ArrayList<TrainingCampProgramVO>(trainingCampProgramEligibleCntMap.values()));
		  }
		 }catch(Exception e){
			 LOG.error("Error occured at getTotalEligibleAttendedAndNotAttenedOverviewCount() in CoreDashboardMainService {}",e);
		 }
		 return finalResultVO;
	}
	public void setEligibleMemberCntToMap(List<Object[]> rtrnObjList,Map<String,Long> totalEligibleMemberCntMap,String levelType){
	try{
		 if(rtrnObjList != null && rtrnObjList.size() > 0){
				for (Object[] param : rtrnObjList) {
					Long levelId = param[0] != null ? (Long)param[0]:0l;
					Long eligibleMembersCnt = 0l;
					if(levelType.equalsIgnoreCase("villageWard")){
						if(levelId.longValue()==6l || levelId.longValue()==8l){
						 eligibleMembersCnt = param[1] != null ? (Long)param[1]:0l;	
						}
					}else if(levelType.equalsIgnoreCase("mandalTwnDiv")){
						if(levelId.longValue()==5l || levelId.longValue()==7l || levelId.longValue()==9l){
						 eligibleMembersCnt = param[1] != null ? (Long)param[1]:0l;	
						}
					}else if(levelType.equalsIgnoreCase("district")){
					   if(levelId.longValue()==11l){
						  eligibleMembersCnt = param[1] != null ? (Long)param[1]:0l; 
					   }
					}else if(levelType.equalsIgnoreCase("state")){
						  if(levelId.longValue()==10l){
							  eligibleMembersCnt = param[1] != null ? (Long)param[1]:0l; 
						   }
					}
					totalEligibleMemberCntMap.put("totalElibible", totalEligibleMemberCntMap.get("totalElibible")+eligibleMembersCnt);
				}
			}
	}catch(Exception e){
		 LOG.error("Error occured at setEligibleMemberCntToMap() in CoreDashboardService1 {}",e); 
	}
	}
	public void setAttendedMemberCntToMap(List<Object[]> rtrnTtlAttendedMmbrObjList,Map<String,Long> totalAttenedMemberCntMap,String levelType){
	try{
		 if(rtrnTtlAttendedMmbrObjList != null && rtrnTtlAttendedMmbrObjList.size() > 0){
				for (Object[] param : rtrnTtlAttendedMmbrObjList) {
					Long levelId = param[0] != null ? (Long)param[0]:0l;
					Long attendedMemCnt = 0l;
					if(levelType.equalsIgnoreCase("villageWard")){
						if(levelId.longValue()==6l || levelId.longValue()==8l){
							attendedMemCnt = param[1] != null ? (Long)param[1]:0l;	
						}
					}else if(levelType.equalsIgnoreCase("mandalTwnDiv")){
						if(levelId.longValue()==5l || levelId.longValue()==7l || levelId.longValue()==9l){
							attendedMemCnt = param[1] != null ? (Long)param[1]:0l;	
						}
					}else if(levelType.equalsIgnoreCase("district")){
						if(levelId.longValue()==11l){
						 attendedMemCnt = param[1] != null ? (Long)param[1]:0l; 
						}
					}else if(levelType.equalsIgnoreCase("state")){
						if(levelId.longValue()==10){
						 attendedMemCnt = param[1] != null ? (Long)param[1]:0l; 
						 }
					}
					totalAttenedMemberCntMap.put("totalAttended", totalAttenedMemberCntMap.get("totalAttended")+attendedMemCnt);
				}
			}
	}catch(Exception e){
		 LOG.error("Error occured at setAttendedMemberCntToMap() in CoreDashboardMainService {}",e); 
	}
	}
	/**
	* @param  Long userAccessLevelId
	* @param List<Long> userAccessLevelValues
	* @return List<TrainingCampProgramVO>
	* @author Santosh 
	* @Description :This Service Method is used to get training camp program details count by user type.. 
	*  @since 23-AUGUST-2016
	*/
	public List<TrainingCampProgramVO> getTrainingCampProgramsDetailsCntByDistrict(Long userAccessLevelId,List<Long> userAccessLevelValues){

	List<TrainingCampProgramVO> resultList = new ArrayList<TrainingCampProgramVO>(0);
	Map<Long,List<TrainingCampProgramVO>> programDtlsMap = new HashMap<Long, List<TrainingCampProgramVO>>(0);
	Map<Long,String> programIdNameMap = new HashMap<Long, String>();
	try{
		List<Object[]> rtrnElgbleMmbrsObjLst = tdpCommitteeMemberDAO.getTotalEligibleMembersForTrainingCampProgramByDistrict(userAccessLevelId, userAccessLevelValues); 
		 if(rtrnElgbleMmbrsObjLst != null && !rtrnElgbleMmbrsObjLst.isEmpty()){
			 for (Object[] param : rtrnElgbleMmbrsObjLst) {
				List<TrainingCampProgramVO> districtsList = programDtlsMap.get((Long)param[0]);
				 if(districtsList == null){
					 districtsList = new ArrayList<TrainingCampProgramVO>();
					 programIdNameMap.put((Long)param[0], param[1] != null ? param[1].toString(): "");
					 programDtlsMap.put((Long)param[0], districtsList);
				 }
				 TrainingCampProgramVO districtVO = new TrainingCampProgramVO();
				   districtVO.setId(param[2] != null ? (Long)param[2] : 0l);
				   districtVO.setName(param[3] != null ? param[3].toString() : "");
				   districtVO.setTotalEligibleCount(param[4] != null ? (Long)param[4] : 0l);
				   districtsList.add(districtVO);
			}
		 }
		List<Object[]> rtrnAttnddMemObjList = trainingCampAttendanceDAO.getTotalAttenedCadresOfTrainingCampProgramByDistrict(userAccessLevelId, userAccessLevelValues);
		 if(rtrnAttnddMemObjList != null && !rtrnAttnddMemObjList.isEmpty()){
				 for (Object[] param : rtrnAttnddMemObjList) {
					Long programId= (Long)param[0];
					Long districtId = param[2] != null ? (Long)param[2]:0l;
					Long attendedCnt = param[4] != null ? (Long)param[4]:0l;
					List<TrainingCampProgramVO> districtList = programDtlsMap.get(programId);
					if(districtList != null && !districtList.isEmpty()){
						 for (TrainingCampProgramVO districtVO : districtList) {
								if(districtVO.getId().equals(districtId)){
									districtVO.setTotalAttenedCount(attendedCnt);
									districtVO.setTotalAttenedCountPer(calculatePercantage(districtVO.getTotalAttenedCount(), districtVO.getTotalEligibleCount()));
									if(districtVO.getTotalEligibleCount() > 0l){
										districtVO.setTotalNotAttenedCount(districtVO.getTotalEligibleCount()-districtVO.getTotalAttenedCount());	
									}
									districtVO.setTotalNotAttenedCountPer(calculatePercantage(districtVO.getTotalNotAttenedCount(), districtVO.getTotalEligibleCount()));
								}
						 }
					}
				}
		 }
		 if(programDtlsMap != null && programDtlsMap.size() > 0){
			 for(Entry<Long, List<TrainingCampProgramVO>> entry:programDtlsMap.entrySet()){
				 TrainingCampProgramVO programVO = new TrainingCampProgramVO();
				        programVO.setId(entry.getKey());
				        programVO.setName(programIdNameMap.get(entry.getKey()));
				        programVO.setDistrictList(entry.getValue());
				        resultList.add(programVO);
			 }
		 }
		 
	}catch(Exception e) {
		  LOG.error("Error occured at getTrainingCampProgramsDetailsCntDistrictWise() in CoreDashboardMainService {}",e);
	}
	return resultList;	 
	}
	/**
	* @param  Long userId
	* @param  Long userTypeId
	* @param  Long userAccessLevelId
	* @param List<Long> userAccessLevelValues
	* @return  List<List<UserTypeVO>>
	* @author Santosh 
	* @Description :This Service Method is used to get top5 strong or top5 poor members attended and eligible count.. 
	*  @since 24-AUGUST-2016
	*/
	public List<List<UserTypeVO>> getUserTypeWiseTotalEligibleAndAttendedCnt(Long userId,Long userTypeId,Long activityMembersId,Long userAccessLevelId,List<Long> userAccessLevelValues){

	List<List<UserTypeVO>> resultList = new ArrayList<List<UserTypeVO>>(0);
	Map<String,Long> elibibleMemberCntMap = new HashMap<String, Long>(0);
	Map<String,Long> attendedMemberCntMap = new HashMap<String, Long>(0);
	Map<Long,Set<Long>> locationLevelMap = null;
	Map<Long,Map<Long,UserTypeVO>> userTypeMapDtls = null;
	try{
		 
		   ActivityMemberVO activityMemberVO = new ActivityMemberVO();
		     activityMemberVO.setUserId(userId);
		     activityMemberVO.setActivityMemberId(activityMembersId);
		     activityMemberVO.setUserTypeId(userTypeId);
		     activityMemberVO = coreDashboardGenericService.getChildActivityMembersAndLocationsNew(activityMemberVO);//calling generic method.
		     userTypeMapDtls = activityMemberVO.getUserTypesMap();
		     locationLevelMap = activityMemberVO.getLocationLevelIdsMap();
		     
	if(locationLevelMap != null && locationLevelMap.size() > 0){
		  for(Entry<Long,Set<Long>> entry:locationLevelMap.entrySet()){
			List<Object[]> returnObjList = tdpCommitteeMemberDAO.getUserWiseTotalEligibleMembersForTrainingCampProgram(entry.getKey(),new ArrayList<Long>(entry.getValue()));
			   if(returnObjList != null && returnObjList.size() > 0){
				   for (Object[] param : returnObjList) {
					String locationLevelAndId = entry.getKey()+"-"+param[0].toString();
					elibibleMemberCntMap.put(locationLevelAndId, param[1] != null ? (Long)param[1]:0l);
				}
			   }
		   }  
	}
	if(locationLevelMap != null && locationLevelMap.size() > 0){
		  for(Entry<Long,Set<Long>> entry:locationLevelMap.entrySet()){
			List<Object[]> returnObjList = trainingCampAttendanceDAO.getUserWiseTotalAttenedCadresCntForTrainingProgram(entry.getKey(),new ArrayList<Long>(entry.getValue()));
			   if(returnObjList != null && returnObjList.size() > 0){
				   for (Object[] param : returnObjList) {
					 String locationLevelAndId = entry.getKey()+"-"+param[0].toString();
					 attendedMemberCntMap.put(locationLevelAndId, param[1] != null ? (Long)param[1]:0l);
				}
			   }
		   }  
	}
	//Pushing Eligible Count
	if(userTypeMapDtls != null && userTypeMapDtls.size() > 0){
		  for (Entry<Long, Map<Long, UserTypeVO>> entry : userTypeMapDtls.entrySet()) {
		      Map<Long,UserTypeVO> userTypeMap = entry.getValue();
		      for(UserTypeVO vo:userTypeMap.values()){
		    	  for(Long locationValueId:vo.getLocationValuesSet()){
		    		  String key = vo.getLocationLevelId()+"-"+locationValueId;
		    		  if(elibibleMemberCntMap.get(key) != null){
				    		 vo.setTotalEligibleCount(vo.getTotalEligibleCount()+elibibleMemberCntMap.get(key)); 
				    	 }
		    	  }
		      }
	}  
	}
	//Pushing Attended Count
	if(userTypeMapDtls != null && userTypeMapDtls.size() > 0){
		  for(Entry<Long, Map<Long, UserTypeVO>> entry : userTypeMapDtls.entrySet()){
		      Map<Long,UserTypeVO> userTypeMap = entry.getValue();
		      for(UserTypeVO vo:userTypeMap.values()){
		    	   for(Long locationValueId:vo.getLocationValuesSet()){
		    			 String key = vo.getLocationLevelId()+"-"+locationValueId;   
		    			 if(attendedMemberCntMap.get(key) != null){
		    	    		 vo.setTotalAttenedCount(vo.getTotalAttenedCount()+attendedMemberCntMap.get(key)); 
		    	    	 }
		    	   }
		      }
	}	  
	}

	//Calculating percentage
	if(userTypeMapDtls != null && userTypeMapDtls.size() > 0){
		  for(Entry<Long, Map<Long, UserTypeVO>> entry : userTypeMapDtls.entrySet()){
		      Map<Long,UserTypeVO> userTypeMap = entry.getValue();
		      for(UserTypeVO vo:userTypeMap.values()){
		    	 	vo.setTotalAttenedCountPer(calculatePercantage(vo.getTotalAttenedCount(),vo.getTotalEligibleCount()));  
		    	 	if(vo.getTotalEligibleCount() > 0l){
		    	 		vo.setTotalNotAttenedCount(vo.getTotalEligibleCount()-vo.getTotalAttenedCount());	
		    	 	}
			     	 vo.setTotalNotAttenedCountPer(calculatePercantage(vo.getTotalNotAttenedCount(),vo.getTotalEligibleCount()));	
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
			Collections.sort(memberList, trainingMemberEligibleAttendedPercDesc);
		}
	}
	}catch(Exception e){
	LOG.error("Error occured at getUserTypeWiseTotalEligibleAndAttendedCnt() in CoreDashboardMainService {}",e); 
	}
	return resultList; 
	}
	public static Comparator<UserTypeVO> trainingMemberEligibleAttendedPercDesc = new Comparator<UserTypeVO>() {
	public int compare(UserTypeVO member2, UserTypeVO member1) {

	Double perc2 = member2.getTotalAttenedCountPer();
	Double perc1 = member1.getTotalAttenedCountPer();
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
	/**
	* @param  Long parentActivityMemberId
	* @param  Long childUserTypeId
	* @param  Long userAccessLevelId
	* @param List<Long> userAccessLevelValues
	* @param String reportType
	* @return List<UserTypeVO>
	* @author Santosh 
	* @Description :This Service Method is used to get selected child member and for userType.. 
	*  @since 26-AUGUST-2016
	*/
	public List<UserTypeVO> getSelectedChildTypeMembersForTrainingProgram(Long parentActivityMemberId,Long childUserTypeId,Long locationLevelId,List<Long> locationLevelValues,String reportType){

	List<UserTypeVO> resultList = new ArrayList<UserTypeVO>(0);
	Map<String,Long> elibibleMemberCntMap = new HashMap<String, Long>(0);
	Map<String,Long> attendedMemberCntMap = new HashMap<String, Long>(0);
	try{
		   //calling generic method to get childActivityMembers and there location level and values
		ActivityMemberVO activityMemberVO=null;
		  Map<Long,UserTypeVO> childActivityMembersMap=null;
		  
		  
		  Map<Long,Set<Long>> locationLevelIdsMap=null;
		  Map<String,String>     nameForLocationMap=null;
		  if(reportType != null && reportType.equalsIgnoreCase("selectedUserType")){
			  activityMemberVO= coreDashboardGenericService.getSelectedChildUserTypeMembers(parentActivityMemberId,childUserTypeId);
			  childActivityMembersMap= activityMemberVO.getActivityMembersMap();
			  locationLevelIdsMap= activityMemberVO.getLocationLevelIdsMap();
		  }else if(reportType != null && reportType.equalsIgnoreCase("directChild")){
			   activityMemberVO = coreDashboardGenericService.getDirectChildActivityMemberCommitteeDetails(parentActivityMemberId,childUserTypeId);//activityMemerId,userTypeId
			   childActivityMembersMap = activityMemberVO.getActivityMembersMap();
			   locationLevelIdsMap = activityMemberVO.getLocationLevelIdsMap();
		  }
		  
	/*	if(childUserTypeId != null && childUserTypeId.longValue()==IConstants.DISTRICT_PRESIDENT_USER_ID || childUserTypeId.longValue()==IConstants.MP_USER_ID
		   ||childUserTypeId.longValue()==IConstants.MLA_USER_ID || childUserTypeId.longValue()==IConstants.CONSTITUENCY_USER_ID || 
		   childUserTypeId.longValue()==IConstants.CONSTITUENCY_INCHARGE_USER_ID){*/
		   if(locationLevelIdsMap != null && locationLevelIdsMap.size() > 0){
			  nameForLocationMap = coreDashboardGenericService.getLocationNamesByLocationIds(locationLevelIdsMap);
		  }
	 	    if(locationLevelIdsMap != null && locationLevelIdsMap.size() > 0){
				  for(Entry<Long,Set<Long>> entry:locationLevelIdsMap.entrySet()){
					List<Object[]> returnObjList = tdpCommitteeMemberDAO.getUserWiseTotalEligibleMembersForTrainingCampProgram(entry.getKey(),new ArrayList<Long>(entry.getValue()));
					   if(returnObjList != null && returnObjList.size() > 0){
						   for (Object[] param : returnObjList) {
							String locationLevelAndId = entry.getKey()+"-"+param[0].toString();
							elibibleMemberCntMap.put(locationLevelAndId, param[1] != null ? (Long)param[1]:0l);
						}
					   }
				   }  
			}  
			  if(locationLevelIdsMap != null && locationLevelIdsMap.size() > 0){
				  for(Entry<Long,Set<Long>> entry:locationLevelIdsMap.entrySet()){
					List<Object[]> returnObjList = trainingCampAttendanceDAO.getUserWiseTotalAttenedCadresCntForTrainingProgram(entry.getKey(),new ArrayList<Long>(entry.getValue()));
					   if(returnObjList != null && returnObjList.size() > 0){
						   for (Object[] param : returnObjList) {
							 String locationLevelAndId = entry.getKey()+"-"+param[0].toString();
							 attendedMemberCntMap.put(locationLevelAndId, param[1] != null ? (Long)param[1]:0l);
						}
					   }
				   }  
			  } 
		  //Pushing Eligible Count
			  if(childActivityMembersMap != null && childActivityMembersMap.size() > 0){
			      for(UserTypeVO vo:childActivityMembersMap.values()){
			    	  for(Long locationValueId:vo.getLocationValuesSet()){
			    		  String key = vo.getLocationLevelId()+"-"+locationValueId;
			    		  if(elibibleMemberCntMap.get(key) != null){
					    		 vo.setTotalEligibleCount(vo.getTotalEligibleCount()+elibibleMemberCntMap.get(key)); 
					    	 }
			    	  }
			      }
		    }    
		  //Pushing Attended Count
		 	  if(childActivityMembersMap != null && childActivityMembersMap.size() > 0){
			      for(UserTypeVO vo:childActivityMembersMap.values()){
			    	   for(Long locationValueId:vo.getLocationValuesSet()){
			    			 String key = vo.getLocationLevelId()+"-"+locationValueId;   
			    			 if(attendedMemberCntMap.get(key) != null){
			    	    		 vo.setTotalAttenedCount(vo.getTotalAttenedCount()+attendedMemberCntMap.get(key)); 
			    	    	 }
			    	   }
			      }
		   }  
		  //Setting Location name
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
			//Calculating percentage
		  if(childActivityMembersMap != null && childActivityMembersMap.size() > 0){
			      for(UserTypeVO vo:childActivityMembersMap.values()){
			    	 	vo.setTotalAttenedCountPer(calculatePercantage(vo.getTotalAttenedCount(),vo.getTotalEligibleCount()));  
			    	 	if(vo.getTotalEligibleCount() > 0l){
			    	 		 vo.setTotalNotAttenedCount(vo.getTotalEligibleCount()-vo.getTotalAttenedCount());	
			    	 	}
				     	 vo.setTotalNotAttenedCountPer(calculatePercantage(vo.getTotalNotAttenedCount(),vo.getTotalEligibleCount()));	
		  		     	}
		  }
		  if(childActivityMembersMap != null && childActivityMembersMap.size() > 0){
			  resultList.addAll(childActivityMembersMap.values());
		  }
		  if(resultList != null && resultList.size() > 0)
		  {
			  Collections.sort(resultList, trainingMemberEligibleAttendedPercDesc);
		  }
	}catch(Exception e){
		LOG.error("Error occured at getSelectedChildTypeMembersForTrainingProgram() in CoreDashboardMainService ",e);
	}
	return resultList;	
	}
	/**
	* @param  Long userAccessLevelId
	* @param List<Long> userAccessLevelValues
	* @return  TrainingCampProgramVO
	* @author Santosh 
	* @Description :This Service Method is used to get top5 poor district and constituency locations attended counts. 
	*  @since 29-AUGUST-2016
	*/
  public TrainingCampProgramVO getTrainingProgramPoorCompletedLocationDtls(Long userAccessLevelId,List<Long> userAccessLevelValues){
	  
	  TrainingCampProgramVO resultVO = new TrainingCampProgramVO();
	  Map<Long,TrainingCampProgramVO> eligibleMembersMap = new HashMap<Long, TrainingCampProgramVO>();
	  Map<Long,Long> attendedMembersMap = new HashMap<Long, Long>();
	  try{
	   List<Object[]> rtrnDistAttendedObj = trainingCampAttendanceDAO.getTotalAttenedCadresOfTrainingCampProgramByLocationType(userAccessLevelId, userAccessLevelValues,"District");   
	   List<Object[]> rtrnDistEligibleObj =  tdpCommitteeMemberDAO.getTotalEligibleMembersForTrainingCampProgramByLocationType(userAccessLevelId, userAccessLevelValues, "District"); 	 
	   setAttendedMembersCntToMap(rtrnDistAttendedObj,attendedMembersMap);
	   setEligibleMemberCntToMap(rtrnDistEligibleObj,attendedMembersMap,eligibleMembersMap);
	  
	   if(eligibleMembersMap != null && eligibleMembersMap.size()>0){
		 resultVO.setDistrictList(new ArrayList<TrainingCampProgramVO>(eligibleMembersMap.values()));
	   }
	    eligibleMembersMap.clear();
	    attendedMembersMap.clear(); 

	   List<Object[]> rtrnConsAttendedObj = trainingCampAttendanceDAO.getTotalAttenedCadresOfTrainingCampProgramByLocationType(userAccessLevelId, userAccessLevelValues,"Constituency");   
	   List<Object[]> rtrnConsEligibleObj =  tdpCommitteeMemberDAO.getTotalEligibleMembersForTrainingCampProgramByLocationType(userAccessLevelId, userAccessLevelValues, "Constituency"); 	 
	   setAttendedMembersCntToMap(rtrnConsAttendedObj,attendedMembersMap);
	   setEligibleMemberCntToMap(rtrnConsEligibleObj,attendedMembersMap,eligibleMembersMap);
	   if(eligibleMembersMap != null && eligibleMembersMap.size()>0){
			 resultVO.setConstituencyList(new ArrayList<TrainingCampProgramVO>(eligibleMembersMap.values()));
			 eligibleMembersMap.clear();
			 attendedMembersMap.clear(); 
	   } 
	   if(resultVO != null){
		   if(resultVO.getDistrictList() != null && resultVO.getDistrictList().size()> 0){
            Collections.sort(resultVO.getDistrictList(),trainingMemberEligibleAttendedPercasc);
		   }
		   if(resultVO.getConstituencyList() != null && resultVO.getConstituencyList().size() > 0){
			Collections.sort(resultVO.getConstituencyList(),trainingMemberEligibleAttendedPercasc);   
		   }
	   }
	  }catch(Exception e) {
		LOG.error("Error occured at getTrainingProgramPoorCompletedLocationDtls in CoreDashboardMainService ",e);
	 }
	 return resultVO;
  }
  public void  setAttendedMembersCntToMap(List<Object[]> rtrnDistAttendedObj,Map<Long,Long> attendedMembersMap){
	 try{
	   if(rtrnDistAttendedObj != null && rtrnDistAttendedObj.size() > 0){
		   for (Object[] param : rtrnDistAttendedObj) {
			attendedMembersMap.put((Long)param[0],param[2] != null ? (Long)param[2]:0l);
		}
	   }
	 }catch(Exception e) {
			LOG.error("Error occured at setAttendedMembersCntToMap in CoreDashboardMainService ",e);
	 }
  }
  public void setEligibleMemberCntToMap(List<Object[]> rtrnDistEligibleObj,Map<Long,Long> attendedMembersMap,Map<Long,TrainingCampProgramVO> eligibleMembersMap){
	 try{
		 if(rtrnDistEligibleObj != null && rtrnDistEligibleObj.size() > 0){
			 for(Object[] obj : rtrnDistEligibleObj) {
				TrainingCampProgramVO vo = new TrainingCampProgramVO();
				  vo.setId((Long)obj[0]);
				  vo.setName(obj[1] != null ? obj[1].toString():"");
				  vo.setTotalEligibleCount(obj[2] != null ? (Long)obj[2]:0l);
				  vo.setTotalAttenedCount(attendedMembersMap.get(vo.getId()));
				  vo.setTotalAttenedCountPer(calculatePercantage(vo.getTotalAttenedCount(),vo.getTotalEligibleCount()));
				  if(vo.getTotalEligibleCount() > 0){
					  vo.setTotalNotAttenedCount(vo.getTotalEligibleCount()-vo.getTotalAttenedCount());  
				  }
				  vo.setTotalNotAttenedCountPer(calculatePercantage(vo.getTotalNotAttenedCount(),vo.getTotalEligibleCount()));
				  eligibleMembersMap.put(vo.getId(),vo);
			}
		 }
	 }catch(Exception e) {
			LOG.error("Error occured at setEligibleMemberCntToMap in CoreDashboardMainService ",e);
	}
  }
	public static Comparator<TrainingCampProgramVO> trainingMemberEligibleAttendedPercasc = new Comparator<TrainingCampProgramVO>() {
	public int compare(TrainingCampProgramVO member2, TrainingCampProgramVO member1) {
	Double perc2 = member2.getTotalAttenedCountPer();
	Double perc1 = member1.getTotalAttenedCountPer();
	//ascending order of percantages.
	 return perc2.compareTo(perc1);
	}
	}; 
	
	
	/*  Debate Service*/
	
	
	
	public List<CoreDebateVO> getPartyWiseTotalDebateDetails(String startDateStr,String endDateStr){		
		List<CoreDebateVO> returnList = new ArrayList<CoreDebateVO>();		
		try{
			Date startDate = null;
			Date endDate   =null;
			
			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
			if(startDateStr !=null && !startDateStr.trim().isEmpty() && endDateStr !=null && !endDateStr.trim().isEmpty()){
				startDate = sdf.parse(startDateStr);
				endDate = sdf.parse(endDateStr);
			}
			
			Map<Long,CoreDebateVO> countMap = new HashMap<Long, CoreDebateVO>();
			
			//0.partyId,1.shortName,2.debateCount,3.candidateCount
			List<Object[]> debateCountObjList = debateParticipantDAO.getPartyWiseDebateDetails(startDate,endDate);	
			
			//0.partyId,1.shortName,2.characteristicsId,3.name,4.scale
			List<Object[]> scaleCountObjList = debateParticipantCharcsDAO.getPartyWiseScalesOfEachCharecter(startDate,endDate);
			
			List<Characteristics> charecters = characteristicsDAO.getCharacteristicsDetails();
			
			if(commonMethodsUtilService.isListOrSetValid(debateCountObjList)){			
				countMap = setDebateValuesToMap(debateCountObjList,countMap);
			}
			
			if(commonMethodsUtilService.isListOrSetValid(scaleCountObjList)){
				countMap = setScaleVauesToParty(scaleCountObjList,countMap);
			}
			
			if(countMap !=null && countMap.size()>0){				
				returnList = new ArrayList<CoreDebateVO>(countMap.values());				
			}
			
			if(commonMethodsUtilService.isListOrSetValid(returnList)){
				for (CoreDebateVO objects : returnList) {
					objects.setScalePerc(Double.parseDouble(new BigDecimal((objects.getScale())/objects.getDebateCount()).setScale(2, BigDecimal.ROUND_HALF_UP).toString()));
					if(objects.getScalePerc() !=null && objects.getScalePerc() >0.0 ){
						objects.setScalePerc(Double.parseDouble(new BigDecimal((objects.getScalePerc())/charecters.size()).setScale(2, BigDecimal.ROUND_HALF_UP).toString()));
					}					
				}
			}
			
			//System.out.println(returnList);
						
		}catch (Exception e) {
			e.printStackTrace();
		}
		
		return returnList;
		
	}
	
	public Map<Long,CoreDebateVO> setDebateValuesToMap(List<Object[]> ObjList,Map<Long,CoreDebateVO> countMap){
		try{			
			if(commonMethodsUtilService.isListOrSetValid(ObjList)){			
				for (Object[] obj : ObjList) {					
					CoreDebateVO coreDebateVO = new CoreDebateVO();
						coreDebateVO.setId((Long)obj[0]);
						coreDebateVO.setName(commonMethodsUtilService.getStringValueForObject(obj[1]));
						coreDebateVO.setDebateCount(commonMethodsUtilService.getLongValueForObject(obj[2]));
						coreDebateVO.setCandidateCount(commonMethodsUtilService.getLongValueForObject(obj[3]));						
						countMap.put((Long)obj[0], coreDebateVO);										
				}
			}
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		return countMap;
	} 
	
	public Map<Long,CoreDebateVO> setScaleVauesToParty(List<Object[]> ObjList,Map<Long,CoreDebateVO> countMap){
		try{
			if(commonMethodsUtilService.isListOrSetValid(ObjList)){			
				for (Object[] obj : ObjList) {
					CoreDebateVO coreDebateVO = countMap.get((Long)obj[0]);
					if(coreDebateVO == null){
						coreDebateVO = new CoreDebateVO();
						coreDebateVO.setId((Long)obj[0]);
						coreDebateVO.setName(commonMethodsUtilService.getStringValueForObject(obj[1]));
						countMap.put((Long)obj[0], coreDebateVO);
					}		
				/*	if(coreDebateVO.getScale() !=null && coreDebateVO.getScale()>0 && obj[4] !=null && (Long)obj[4]>0){
						coreDebateVO.setScale(Double.parseDouble(new BigDecimal((coreDebateVO.getScale())/(Long)obj[4]).setScale(2, BigDecimal.ROUND_HALF_UP).toString()));
					}	*/				
					coreDebateVO.setScale(coreDebateVO.getScale() + (Double.parseDouble(commonMethodsUtilService.getStringValueForObject(obj[4]))) );
				}
			}
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		return countMap;
	}	
	public List<CoreDebateVO> getSpokesPersonWiseDebate(String startDateStr,String endDateStr,String searchType){
	
		List<CoreDebateVO> returnList = new ArrayList<CoreDebateVO>();
		
		try{			
			Date startDate = null;
			Date endDate   =null;
			
			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
			if(startDateStr !=null && !startDateStr.trim().isEmpty() && endDateStr !=null && !endDateStr.trim().isEmpty()){
				startDate = sdf.parse(startDateStr);
				endDate = sdf.parse(endDateStr);
			}
			
			Map<Long,Map<Long,CoreDebateVO>> partyMap = new HashMap<Long, Map<Long,CoreDebateVO>>();
			//Map<Long,CoreDebateVO> candidateMap= new HashMap<Long, CoreDebateVO>();
			
			//0.partyId,1.name,2.candidateId,3.name,4.scale
			List<Object[]> candidateObjList =  debateParticipantCharcsDAO.getPartywiseCandidateScaling(startDate,endDate,searchType);
			
			//0.partyId,1.name,2.candidateId,3.name,4.debateCount
			 List<Object[]> debateCountsList = debateParticipantDAO.getTotalDabtesCountsForEachCandidateNew(startDate, endDate);
			 
			 List<Characteristics> charecters = characteristicsDAO.getCharacteristicsDetails();
						 
			if(commonMethodsUtilService.isListOrSetValid(candidateObjList)){
				for (Object[] parms : candidateObjList) {					
					Map<Long,CoreDebateVO> candidateMap = partyMap.get((Long)parms[0]);
					if(candidateMap == null){						
						candidateMap = new HashMap<Long, CoreDebateVO>();						
						partyMap.put((Long)parms[0], candidateMap);						
					}					
					CoreDebateVO VO = candidateMap.get((Long)parms[2]);			
					
					if(VO == null){
						VO = new CoreDebateVO();
						 VO.setId((Long)parms[0]);
						 VO.setName(commonMethodsUtilService.getStringValueForObject(parms[1]));
						 VO.setCandidateId(commonMethodsUtilService.getLongValueForObject(parms[2]));
						 VO.setCandidateName(commonMethodsUtilService.getStringValueForObject(parms[3]));
						 VO.setScale(Double.parseDouble(commonMethodsUtilService.getStringValueForObject(parms[4])));//candidateId
						 candidateMap.put((Long)parms[2], VO);
					}
				
				}
			}
			
			if(commonMethodsUtilService.isListOrSetValid(debateCountsList)){
				for (Object[] obj : debateCountsList) {					
					Map<Long,CoreDebateVO> candidateMap = partyMap.get(commonMethodsUtilService.getLongValueForObject(obj[0]));					
					if(candidateMap !=null){
						CoreDebateVO vo =candidateMap.get(commonMethodsUtilService.getLongValueForObject(obj[2]));						
						if(vo !=null){	
							
							if(vo.getScale() !=null && vo.getScale()>0.0 && obj[4] !=null && (Long)obj[4]>0){
								vo.setScalePerc(Double.parseDouble(new BigDecimal((vo.getScale())/(Long)obj[4]).setScale(2, BigDecimal.ROUND_HALF_UP).toString()));
								if(vo.getScalePerc() !=null && vo.getScalePerc() >0.0 ){
									vo.setScalePerc(Double.parseDouble(new BigDecimal((vo.getScalePerc())/charecters.size()).setScale(2, BigDecimal.ROUND_HALF_UP).toString()));
								}
							}
							vo.setDebateCount(commonMethodsUtilService.getLongValueForObject(obj[4]));
							
							//vo.setScalePerc(vo.getScale()/(Double.parseDouble(commonMethodsUtilService.getStringValueForObject(obj[4]))));							
						}						
					}					
				}
			}
			
			if(partyMap !=null && partyMap.size()>0){
				for (Entry<Long, Map<Long, CoreDebateVO>> party : partyMap.entrySet()) {					
					List<CoreDebateVO> candidateList =new ArrayList<CoreDebateVO>();					
					CoreDebateVO VO = new CoreDebateVO();					
					Long partyId = party.getKey();					
					VO.setId(partyId);//PartyId					
					Map<Long, CoreDebateVO> candidateMap =  party.getValue();					
					if(candidateMap !=null){
						candidateList = new ArrayList<CoreDebateVO>(candidateMap.values());
					}					
					VO.setCoreDebateVOList(candidateList);					
					returnList.add(VO);
				}
			}
			
			//System.out.println(returnList);
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		
		return returnList;
	}
	
	public List<CoreDebateVO> getScaleBasedPerformanceCohort(String startDateStr,String endDateStr){
		List<CoreDebateVO> returnList = new ArrayList<CoreDebateVO>();
		try{
			
			Date startDate = null;
			Date endDate   =null;
			
			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
			if(startDateStr !=null && !startDateStr.trim().isEmpty() && endDateStr !=null && !endDateStr.trim().isEmpty()){
				startDate = sdf.parse(startDateStr);
				endDate = sdf.parse(endDateStr);
			}
			
			Map<Long,CoreDebateVO> countMap = new HashMap<Long, CoreDebateVO>();			
			List<Object[]> debateCountObjList = debateParticipantDAO.getPartyWiseDebateDetails(startDate,endDate);			
			//0.partyId,1.shortName,2.characteristicsId,3.name,4.scale
			List<Object[]> scaleCountObjList = debateParticipantCharcsDAO.getPartyWiseScalesOfEachCharecter(startDate,endDate);
			
			if(commonMethodsUtilService.isListOrSetValid(debateCountObjList)){			
				countMap = setDebateValuesToMap(debateCountObjList,countMap);
			}
			
			if(commonMethodsUtilService.isListOrSetValid(scaleCountObjList)){			
				for (Object[] obj : scaleCountObjList) {					
					CoreDebateVO partyVO = countMap.get(obj[0]);					
					if(partyVO != null){							
						List<CoreDebateVO> list = partyVO.getCoreDebateVOList();
						if(list == null){
							list = new ArrayList<CoreDebateVO>();
							partyVO.setCoreDebateVOList(list);
						}						
						CoreDebateVO VO = new CoreDebateVO();
						VO.setId(commonMethodsUtilService.getLongValueForObject(obj[2]));
						VO.setName(commonMethodsUtilService.getStringValueForObject(obj[3]));						
						if(obj[4] !=null && (Double)obj[4]>0.0 && partyVO.getDebateCount() >0){
							VO.setScalePerc(Double.parseDouble(new BigDecimal(((Double)obj[4])/partyVO.getDebateCount()).setScale(2, BigDecimal.ROUND_HALF_UP).toString()));
						}						
						list.add(VO);						
					}	
				}
			}			
			if(countMap !=null && countMap.size()>0){
				returnList = new ArrayList<CoreDebateVO>(countMap.values());
			}			
			
			//System.out.println(returnList);
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		return returnList;
	}
	
	public List<CoreDebateVO> getCandidateOverAllPerformanceCohort(String startDateStr,String endDateStr){
		
		List<CoreDebateVO> returnList = new ArrayList<CoreDebateVO>();
		
		try{			
			Date startDate = null;
			Date endDate   =null;
			
			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
			if(startDateStr !=null && !startDateStr.trim().isEmpty() && endDateStr !=null && !endDateStr.trim().isEmpty()){
				startDate = sdf.parse(startDateStr);
				endDate = sdf.parse(endDateStr);
			}
			
			Map<Long,Map<Long,List<CoreDebateVO>>> countMap = new HashMap<Long, Map<Long,List<CoreDebateVO>>>();
			
			Map<Long,CoreDebateVO> debateMap = new HashMap<Long, CoreDebateVO>();
			
			//0.partyId,1.name,2.candidateId,3.name,4.charecterId,5.name,6.scale
			List<Object[]> charecterObjList =  debateParticipantCharcsDAO.getPartywiseCandidateCharectersScaling(startDate,endDate);
			
			//0.partyId,1.name,2.candidateId,3.name,4.debateCount
			 List<Object[]> debateCountsList = debateParticipantDAO.getTotalDabtesCountsForEachCandidateNew(startDate, endDate);
			
			 if(commonMethodsUtilService.isListOrSetValid(charecterObjList)){
				 for (Object[] obj : charecterObjList) {				
					 Map<Long,List<CoreDebateVO>> candidateMap = countMap.get((Long)obj[0]);					 
					 if(candidateMap ==null){
						 candidateMap = new HashMap<Long, List<CoreDebateVO>>();
						 countMap.put((Long)obj[0], candidateMap);
					 }					 
					 List<CoreDebateVO> charecterList  = candidateMap.get((Long)obj[2]);
					 if(charecterList == null){
						 charecterList = new ArrayList<CoreDebateVO>();
						 candidateMap.put((Long)obj[2], charecterList);
					 }					 
					 CoreDebateVO VO = new CoreDebateVO();
					 VO.setId((Long)obj[0]);
					 VO.setName(commonMethodsUtilService.getStringValueForObject(obj[1]));
					 VO.setCandidateId(commonMethodsUtilService.getLongValueForObject(obj[2]));
					 VO.setCandidateName(commonMethodsUtilService.getStringValueForObject(obj[3]));
					 VO.setCharecterId(commonMethodsUtilService.getLongValueForObject(obj[4]));
					 VO.setCharecterName(commonMethodsUtilService.getStringValueForObject(obj[5]));
					 VO.setScale(Double.parseDouble(commonMethodsUtilService.getStringValueForObject(obj[6])));					
					 charecterList.add(VO);
				}
			 }
			 
			 if(commonMethodsUtilService.isListOrSetValid(debateCountsList)){
					for (Object[] obj : debateCountsList) {					
						Map<Long,List<CoreDebateVO>> candidateMap = countMap.get(commonMethodsUtilService.getLongValueForObject(obj[0]));					
						if(candidateMap !=null){
								List<CoreDebateVO> voList = candidateMap.get(commonMethodsUtilService.getLongValueForObject(obj[2]));						
								if(commonMethodsUtilService.isListOrSetValid(voList)){	
									for (CoreDebateVO vo : voList) {
										if(vo.getScale() !=null && vo.getScale()>0.0 && obj[4] !=null && (Long)obj[4]>0){
											vo.setScalePerc(Double.parseDouble(new BigDecimal((vo.getScale())/(Long)obj[4]).setScale(2, BigDecimal.ROUND_HALF_UP).toString()));
										}
										vo.setDebateCount(commonMethodsUtilService.getLongValueForObject(obj[4]));
									}
								}
							}						
						}					
			}
			 
			 if(countMap !=null && countMap.size()>0){				
					for (Entry<Long, Map<Long, List<CoreDebateVO>>> party : countMap.entrySet()) {							
						List<CoreDebateVO> partiesList =new ArrayList<CoreDebateVO>();								
						CoreDebateVO VO = new CoreDebateVO();													
						VO.setId(party.getKey());//partyId					
						Map<Long, List<CoreDebateVO>> candidates =  party.getValue();
						
						if(candidates !=null){							
							List<CoreDebateVO> candidatesList =new ArrayList<CoreDebateVO>();							
							for (Entry<Long, List<CoreDebateVO>> candMap : candidates.entrySet()) {								
								CoreDebateVO smallVo = new CoreDebateVO();
								smallVo.setCandidateId(candMap.getKey());								
								smallVo.setCoreDebateVOList(candMap.getValue());
								candidatesList.add(smallVo);
							}							
							VO.setCoreDebateVOList(candidatesList);
						}							
						returnList.add(VO);
					}				
			}
			 
			//System.out.println(returnList);
		}catch (Exception e) {
			e.printStackTrace();
		}
		
		return returnList;
	}
	
	public List<CoreDebateVO> getChannelAndPartyWiseDetails(String startDateStr,String endDateStr){
		
		List<CoreDebateVO> returnList = new ArrayList<CoreDebateVO>();
		
		try{			
			Date startDate = null;
			Date endDate   =null;
			
			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
			if(startDateStr !=null && !startDateStr.trim().isEmpty() && endDateStr !=null && !endDateStr.trim().isEmpty()){
				startDate = sdf.parse(startDateStr);
				endDate = sdf.parse(endDateStr);
			}
			
			Map<Long,Map<Long,CoreDebateVO>> channelMap = new HashMap<Long, Map<Long,CoreDebateVO>>();			
			//0.channelId,1.channel,2.partyId,3.partyName,4.scaleCount
			List<Object[]> channelScaleObjList = debateParticipantCharcsDAO.getChannelAndPartyWiseCharecter(startDate,endDate);
			
			//0.channelId,1.channel,2.partyId,3.partyName,4.debateCount
			List<Object[]> channelObjList = debateParticipantDAO.getChannelWiseDebateDetails(startDate,endDate);
			
			List<Characteristics> charecters = characteristicsDAO.getCharacteristicsDetails();			
			
			if(commonMethodsUtilService.isListOrSetValid(channelScaleObjList)){
				
				channelMap = setDebateDetailsToMap(channelScaleObjList,channelMap);
				
				
				for (Object[] obj : channelScaleObjList) {					
					Map<Long,CoreDebateVO> partyMap =  channelMap.get((Long)obj[0]);							
					if(partyMap ==null){
						partyMap = new HashMap<Long, CoreDebateVO>();						
						channelMap.put((Long)obj[0], partyMap);						
					}					
					CoreDebateVO VO = partyMap.get((Long)obj[2]);					
					if(VO == null){
						VO = new CoreDebateVO();
						partyMap.put((Long)obj[2], VO);						
					}					
					VO.setId((Long)obj[0]);//channelId
					VO.setName(commonMethodsUtilService.getStringValueForObject(obj[1]));//channel
					VO.setCandidateId(commonMethodsUtilService.getLongValueForObject(obj[2]));//PartyId
					VO.setCandidateName(commonMethodsUtilService.getStringValueForObject(obj[3]));//PartyName
					VO.setScale(Double.parseDouble(commonMethodsUtilService.getStringValueForObject(obj[4])));					
				}
			}			
			if(commonMethodsUtilService.isListOrSetValid(channelObjList)){	
				for (Object[] param : channelObjList) {					
					Map<Long,CoreDebateVO> partyMap = channelMap.get((Long)param[0]);
					if(partyMap !=null){																	
						CoreDebateVO vo = partyMap.get((Long)param[2]);
						if(vo !=null){	
							if(vo.getScale() !=null && param[4] !=null){
								vo.setScalePerc(Double.parseDouble(new BigDecimal((vo.getScale())/(Long)param[4]).setScale(2, BigDecimal.ROUND_HALF_UP).toString()));
								if(vo.getScalePerc() !=null && vo.getScalePerc() >0.0 ){
									vo.setScalePerc(Double.parseDouble(new BigDecimal((vo.getScalePerc())/charecters.size()).setScale(2, BigDecimal.ROUND_HALF_UP).toString()));
								}
							}
							vo.setDebateCount(commonMethodsUtilService.getLongValueForObject(param[4]));
						}
					}
				}						
			}					
			if(channelMap !=null && channelMap.size()>0){				
					for (Entry<Long, Map<Long, CoreDebateVO>> channel : channelMap.entrySet()) {					
						List<CoreDebateVO> partiesList =new ArrayList<CoreDebateVO>();					
						CoreDebateVO VO = new CoreDebateVO();					
						Long channelId = channel.getKey();					
						VO.setId(channelId);//channelId					
						Map<Long, CoreDebateVO> partyMap =  channel.getValue();					
						if(partyMap !=null){
							partiesList = new ArrayList<CoreDebateVO>(partyMap.values());
						}					
						VO.setCoreDebateVOList(partiesList);					
						returnList.add(VO);
					}				
			}
			
			//System.out.println(returnList);
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		return returnList;
		
	}
	
	public Map<Long,Map<Long,CoreDebateVO>> setDebateDetailsToMap(List<Object[]> objList,Map<Long,Map<Long,CoreDebateVO>> mainMap){			
		
		try{
			
			if(commonMethodsUtilService.isListOrSetValid(objList)){
				
				for (Object[] obj : objList) {					
					Map<Long,CoreDebateVO> innerMap =  mainMap.get((Long)obj[0]);							
					if(innerMap ==null){
						innerMap = new HashMap<Long, CoreDebateVO>();						
						mainMap.put((Long)obj[0], innerMap);						
					}					
					CoreDebateVO VO = innerMap.get((Long)obj[2]);					
					if(VO == null){
						VO = new CoreDebateVO();
						innerMap.put((Long)obj[2], VO);						
					}					
					VO.setId((Long)obj[0]);
					VO.setName(commonMethodsUtilService.getStringValueForObject(obj[1]));
					VO.setCandidateId(commonMethodsUtilService.getLongValueForObject(obj[2]));
					VO.setCandidateName(commonMethodsUtilService.getStringValueForObject(obj[3]));
					VO.setScale(Double.parseDouble(commonMethodsUtilService.getStringValueForObject(obj[4])));					
				}
			}
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		return mainMap;
		
	}
	
	public List<CoreDebateVO> getRoleBasedPerformanceCohort(String startDateStr,String endDateStr){
		
		List<CoreDebateVO> returnList = new ArrayList<CoreDebateVO>(0);
		
		try{			
			Date startDate = null;
			Date endDate   =null;
			
			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
			if(startDateStr !=null && !startDateStr.trim().isEmpty() && endDateStr !=null && !endDateStr.trim().isEmpty()){
				startDate = sdf.parse(startDateStr);
				endDate = sdf.parse(endDateStr);
			}
			
			Map<Long,Map<Long,CoreDebateVO>> rolesMap = new HashMap<Long, Map<Long,CoreDebateVO>>();			
			//0.partyId,1.name,2.rolesId,3.role,4.scale
			List<Object[]> roleObjList = debateParticipantCharcsDAO.getRoleBasedPerformanceCohort(startDate, endDate);
			
			//0.partyId,1.shortName,2.debateCount,3.candidatesCount
			List<Object[]> debateCountObjList = debateParticipantDAO.getPartyWiseDebateDetails(startDate,endDate);
			
			List<Characteristics> charecters = characteristicsDAO.getCharacteristicsDetails();
			
			if(commonMethodsUtilService.isListOrSetValid(roleObjList)){			
				rolesMap = setDebateDetailsToMap(roleObjList,rolesMap);				
			}			
			if(commonMethodsUtilService.isListOrSetValid(debateCountObjList)){
				for (Object[] obj : debateCountObjList) {					
					Map<Long,CoreDebateVO> roleMap =rolesMap.get((Long)obj[0]);					
					if(roleMap !=null){
						for (Entry<Long, CoreDebateVO> role : roleMap.entrySet()) {							
							CoreDebateVO VO = role.getValue();	
							if(VO !=null){
								if( VO.getScale() !=null && obj[2] !=null && (Long)obj[2]>0l){
									VO.setScalePerc(Double.parseDouble(new BigDecimal((VO.getScale())/(Long)obj[2]).setScale(2, BigDecimal.ROUND_HALF_UP).toString()));									
									if(VO.getScalePerc() !=null && VO.getScalePerc() >0.0 ){
										VO.setScalePerc(Double.parseDouble(new BigDecimal((VO.getScalePerc())/charecters.size()).setScale(2, BigDecimal.ROUND_HALF_UP).toString()));
									}									
								}
								VO.setDebateCount(commonMethodsUtilService.getLongValueForObject(obj[2]));			
							}				
						}
					}					
				}
			}			
			if(rolesMap !=null && rolesMap.size()>0){				
				for (Entry<Long, Map<Long, CoreDebateVO>> party : rolesMap.entrySet()) {					
					List<CoreDebateVO> rolesList =new ArrayList<CoreDebateVO>();					
					CoreDebateVO VO = new CoreDebateVO();													
					VO.setId(party.getKey());//partyId					
					Map<Long, CoreDebateVO> roles =  party.getValue();					
					if(roles !=null){
						rolesList = new ArrayList<CoreDebateVO>(roles.values());
					}					
					VO.setCoreDebateVOList(rolesList);					
					returnList.add(VO);
				}				
			}
			
			//System.out.println(returnList);
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		
		return returnList;
	}
	
	/**
	  * @author <a href="mailto:aravind.itgrids.hyd@gmail.com">ARAVIND</a>
	  *  This Service Method is used to get the basic Committee Details
	  *  @since 01-SEPTEMBER-2016
	  */
	
	public List<UserDataVO> getbasicCommitteeDetails(){
		//appCndDesigList->appointmentCandidateDesignationList
		List<UserDataVO> basicCommitteeList = new ArrayList<UserDataVO>(0);
		try{
			LOG.info("Entered into getbasicCommitteeDetails() method of CoreDashboardMainService");
			List<Object[]>  objList = tdpBasicCommitteeDAO.getBasicCommittees();
			if(objList != null && objList.size() > 0){
				for (Object[] objects : objList) {
					UserDataVO vo = new UserDataVO();
					vo.setId((Long)objects[0]);
					vo.setName(objects[1].toString());
					basicCommitteeList.add(vo);
				}
			}
			
		}catch(Exception e){
			LOG.error("Exception raised at getbasicCommitteeDetails() method of CoreDashboardMainService", e);
		}
		return basicCommitteeList;
	}
}
