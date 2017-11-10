package com.itgrids.partyanalyst.service.impl;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.apache.commons.lang.StringEscapeUtils;
import org.apache.log4j.Logger;
import org.jfree.util.Log;

import com.itgrids.partyanalyst.dao.IActivityMemberAccessLevelDAO;
import com.itgrids.partyanalyst.dao.IActivityMemberAccessTypeDAO;
import com.itgrids.partyanalyst.dao.IActivityMemberRelationDAO;
import com.itgrids.partyanalyst.dao.IBoothDAO;
import com.itgrids.partyanalyst.dao.IBoothInchargeDAO;
import com.itgrids.partyanalyst.dao.ICharacteristicsDAO;
import com.itgrids.partyanalyst.dao.IConstituencyDAO;
import com.itgrids.partyanalyst.dao.IDebateDAO;
import com.itgrids.partyanalyst.dao.IDebateParticipantCharcsDAO;
import com.itgrids.partyanalyst.dao.IDebateParticipantDAO;
import com.itgrids.partyanalyst.dao.IDebateRolesDAO;
import com.itgrids.partyanalyst.dao.IDebateSubjectDAO;
import com.itgrids.partyanalyst.dao.IDelimitationConstituencyAssemblyDetailsDAO;
import com.itgrids.partyanalyst.dao.IPartyDAO;
import com.itgrids.partyanalyst.dao.IPublicRepresentativeTypeDAO;
import com.itgrids.partyanalyst.dao.ITdpBasicCommitteeDAO;
import com.itgrids.partyanalyst.dao.ITdpCommitteeDAO;
import com.itgrids.partyanalyst.dao.ITdpCommitteeLevelDAO;
import com.itgrids.partyanalyst.dao.ITdpCommitteeMemberDAO;
import com.itgrids.partyanalyst.dao.ITrainingCampAttendanceDAO;
import com.itgrids.partyanalyst.dao.ITrainingCampBatchAttendeeDAO;
import com.itgrids.partyanalyst.dao.ITrainingCampBatchDAO;
import com.itgrids.partyanalyst.dao.ITrainingCampDAO;
import com.itgrids.partyanalyst.dao.ITrainingCampDetailsInfoDAO;
import com.itgrids.partyanalyst.dao.ITrainingCampProgramDAO;
import com.itgrids.partyanalyst.dao.ITrainingCampScheduleDAO;
import com.itgrids.partyanalyst.dao.IUserTypeRelationDAO;
import com.itgrids.partyanalyst.dto.ActivityMemberVO;
import com.itgrids.partyanalyst.dto.BoothInchargesVO;
import com.itgrids.partyanalyst.dto.CommitteeDataVO;
import com.itgrids.partyanalyst.dto.CommitteeInputVO;
import com.itgrids.partyanalyst.dto.CoreDebateVO;
import com.itgrids.partyanalyst.dto.IdNameVO;
import com.itgrids.partyanalyst.dto.TrainingCampProgramVO;
import com.itgrids.partyanalyst.dto.UserDataVO;
import com.itgrids.partyanalyst.dto.UserTypeVO;
import com.itgrids.partyanalyst.model.Characteristics;
import com.itgrids.partyanalyst.service.ICoreDashboardGenericService;
import com.itgrids.partyanalyst.service.ICoreDashboardMainService;
import com.itgrids.partyanalyst.utils.CommonMethodsUtilService;
import com.itgrids.partyanalyst.utils.DateUtilService;
import com.itgrids.partyanalyst.utils.IConstants;
import com.itgrids.partyanalyst.utils.SetterAndGetterUtilService;

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
	 private IDebateRolesDAO debateRolesDAO;
	 private IPartyDAO partyDAO;
	 private ITrainingCampBatchAttendeeDAO trainingCampBatchAttendeeDAO;
	 private IPublicRepresentativeTypeDAO publicRepresentativeTypeDAO;
	 private SetterAndGetterUtilService setterAndGetterUtilService;
	 private IDebateDAO debateDAO;
	 private IDebateSubjectDAO debateSubjectDAO;
	 private ITrainingCampBatchDAO trainingCampBatchDAO;
	 private ITrainingCampDetailsInfoDAO trainingCampDetailsInfoDAO;
	 private IBoothInchargeDAO boothInchargeDAO;
	 private IConstituencyDAO constituencyDAO;
	 private IBoothDAO boothDAO;
	 private ITrainingCampDAO trainingCampDAO;
	 private ITrainingCampScheduleDAO trainingCampScheduleDAO;
	 private ITrainingCampProgramDAO trainingCampProgramDAO;
	//SETTERS
	
	public void setCoreDashboardGenericService(ICoreDashboardGenericService coreDashboardGenericService) {
		this.coreDashboardGenericService = coreDashboardGenericService;
	 }
	public ITrainingCampProgramDAO getTrainingCampProgramDAO() {
		return trainingCampProgramDAO;
	}
	public void setTrainingCampProgramDAO(
			ITrainingCampProgramDAO trainingCampProgramDAO) {
		this.trainingCampProgramDAO = trainingCampProgramDAO;
	}
	public ITrainingCampDAO getTrainingCampDAO() {
		return trainingCampDAO;
	}
	public void setTrainingCampDAO(ITrainingCampDAO trainingCampDAO) {
		this.trainingCampDAO = trainingCampDAO;
	}
	public IUserTypeRelationDAO getUserTypeRelationDAO() {
		return userTypeRelationDAO;
	}
	public ITrainingCampScheduleDAO getTrainingCampScheduleDAO() {
		return trainingCampScheduleDAO;
	}
	public void setTrainingCampScheduleDAO(
			ITrainingCampScheduleDAO trainingCampScheduleDAO) {
		this.trainingCampScheduleDAO = trainingCampScheduleDAO;
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
	
	
	public void setDebateRolesDAO(IDebateRolesDAO debateRolesDAO) {
		this.debateRolesDAO = debateRolesDAO;
	}
	
	public void setPartyDAO(IPartyDAO partyDAO) {
		this.partyDAO = partyDAO;
	}
	
	public void setTrainingCampBatchAttendeeDAO(
			ITrainingCampBatchAttendeeDAO trainingCampBatchAttendeeDAO) {
		this.trainingCampBatchAttendeeDAO = trainingCampBatchAttendeeDAO;
	}
	
	public void setPublicRepresentativeTypeDAO(
			IPublicRepresentativeTypeDAO publicRepresentativeTypeDAO) {
		this.publicRepresentativeTypeDAO = publicRepresentativeTypeDAO;
	}
	
	
	public void setSetterAndGetterUtilService(
			SetterAndGetterUtilService setterAndGetterUtilService) {
		this.setterAndGetterUtilService = setterAndGetterUtilService;
	}
	
	public void setDebateDAO(IDebateDAO debateDAO) {
		this.debateDAO = debateDAO;
	}
	public void setDebateSubjectDAO(IDebateSubjectDAO debateSubjectDAO) {
		this.debateSubjectDAO = debateSubjectDAO;
	}
	public void setTrainingCampBatchDAO(ITrainingCampBatchDAO trainingCampBatchDAO) {
		this.trainingCampBatchDAO = trainingCampBatchDAO;
	}
	public void setTrainingCampDetailsInfoDAO(ITrainingCampDetailsInfoDAO trainingCampDetailsInfoDAO) {
		this.trainingCampDetailsInfoDAO = trainingCampDetailsInfoDAO;
	}
	
	public IBoothDAO getBoothDAO() {
		return boothDAO;
	}
	public void setBoothDAO(IBoothDAO boothDAO) {
		this.boothDAO = boothDAO;
	}
	
	public IBoothInchargeDAO getBoothInchargeDAO() {
		return boothInchargeDAO;
	}
	public void setBoothInchargeDAO(IBoothInchargeDAO boothInchargeDAO) {
		this.boothInchargeDAO = boothInchargeDAO;
	}
	
	public IConstituencyDAO getConstituencyDAO() {
		return constituencyDAO;
	}
	public void setConstituencyDAO(IConstituencyDAO constituencyDAO) {
		this.constituencyDAO = constituencyDAO;
	}
	//santosh
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
	public CommitteeDataVO getCommitteesBasicCountReport(Long userAccessLevelId,List<Long> userAccessLevelValues,String state,Map<Long,List<Long>> committeeLevelBasedCommitteeIdsMap,String dateString,List<Long> committeeEnrollmentYearsIdsLst){
		
		LOG.info(" entered in to getCommitteesBAsicCountReport() ");
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		CommitteeDataVO finalVO = new CommitteeDataVO();
		Date startDate = null;
		Date endDate = null;
		try{
		//	dateString.split("-");
		     //Create Business object.
		     CommitteeInputVO committeeBO = new CommitteeInputVO();
		     coreDashboardGenericService.getRequiredCommitteeLevelIdsByUserAccessLevelId(userAccessLevelId,committeeBO);
		     coreDashboardGenericService.setAppropriateLocationLevelInputsToBO(userAccessLevelId,userAccessLevelValues,committeeBO);
		     //setting committee enrollment year
		     committeeBO.setEnrollmentYearList(committeeEnrollmentYearsIdsLst);
		     
		     //committeeBO.setBasicCommitteeIds(basicCommitteeIds);
		     committeeBO.setCommitteesQueryString(prepareQueryForCommitteeLevelBasedCommitteeIds(committeeLevelBasedCommitteeIdsMap,"TdpCommittee"));
		     
		     
		     if(dateString != null && !dateString.isEmpty()){
			   // committeeBO.setDate(sdf.parse(dateString));
		    	 String DatesArr[] = dateString.split("-");
		    	 if(DatesArr != null && DatesArr.length>0){
		    		 startDate = sdf.parse(commonMethodsUtilService.getStringValueForObject(DatesArr[0]));
		    		 endDate = sdf.parse(commonMethodsUtilService.getStringValueForObject(DatesArr[1]));
		    		 
		    		 committeeBO.setStartDate(startDate);
		    		 committeeBO.setEndDate(endDate);
		    	 }
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
	 public StringBuilder prepareQueryForCommitteeLevelBasedCommitteeIds(Map<Long,List<Long>> committeeLevelBasedCommitteeIdsMap,String committeType){
		   
		   StringBuilder sb = new StringBuilder();
		   boolean anyQueryExist = false;
		   
		   if(!committeType.equalsIgnoreCase("boothCommittee")){  
			   if(committeeLevelBasedCommitteeIdsMap != null && committeeLevelBasedCommitteeIdsMap.size()>0){
				   sb.append(" and ( ");
				   
				   for(Long tdpCommitteeLevelId : committeeLevelBasedCommitteeIdsMap.keySet()){
					   
					   List<Long> basicCommitteeIds = committeeLevelBasedCommitteeIdsMap.get(tdpCommitteeLevelId);
					   if(basicCommitteeIds != null && basicCommitteeIds.size()>0){
						   
						   if(anyQueryExist){
							   sb.append(" or ");
						   }else{
							   anyQueryExist = true;
						   }
						   
						   String basicCommitteesString = listToCommaValues(basicCommitteeIds);
						   
						   if(tdpCommitteeLevelId.longValue() == 6){ //village/ward
							   
							   sb.append(" model.tdpCommitteeLevelId in (6,8) and model.tdpBasicCommitteeId in ("+basicCommitteesString+")");
							   
						   }else if(tdpCommitteeLevelId.longValue() == 5){//mandal/town/division
							   
							   sb.append(" model.tdpCommitteeLevelId in (5,7,9) and model.tdpBasicCommitteeId in ("+basicCommitteesString+")");
							   
						   }else{
							 
							   sb.append(" model.tdpCommitteeLevelId = "+tdpCommitteeLevelId+" and model.tdpBasicCommitteeId in ("+basicCommitteesString+")");
						   }
					   }
				   }
				   sb.append(" ) ");
			   }
		 }
		   
		   return sb;
	   }
	   public String listToCommaValues(List<Long> list){
		    
		    StringBuilder commaSepValueBuilder = new StringBuilder();
		    
		    for ( int i = 0; i< list.size(); i++){
		      commaSepValueBuilder.append(list.get(i));
		      
		      if ( i != list.size()-1){
		        commaSepValueBuilder.append(", ");
		      }
		    }
		    return commaSepValueBuilder.toString();
	   }
	/**
	  * @author <a href="mailto:sreedhar.itgrids.hyd@gmail.com">SREEDHAR</a>
	  *  This Service Method is used to get top5 strong or top5 poor usertype committees count. 
	  *  @since 10-AUGUST-2016
	  */
   
	public List<List<UserTypeVO>> getUserTypeWiseCommitteesCompletedCounts1(Long userId,Long activityMemberId,Long userTypeId,String state,Map<Long,List<Long>> committeeLevelBasedCommitteeIdsMap,String dateString,List<Long> committeeEnrollmentYearsIdsLst,String committeType){
		List<List<UserTypeVO>> userTypesList = null;
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		try{ 
			Date startDate = null;
			Date endDate = null;
			//Creating Business Object.
		     CommitteeInputVO committeeBO = new CommitteeInputVO();
		     //committeeBO.setBasicCommitteeIds(basicCommitteeIds);
		     committeeBO.setCommitteesQueryString(prepareQueryForCommitteeLevelBasedCommitteeIds(committeeLevelBasedCommitteeIdsMap,committeType));
		     Long stateId = coreDashboardGenericService.getStateIdByState(state);
		     committeeBO.setStateId(stateId);
		     committeeBO.setCommitteType(committeType);// boothCommittee / tdpCommittee
		     if(dateString != null && !dateString.isEmpty()){
		    	// committeeBO.setDate(sdf.parse(dateString));
		    	 String DatesArr[] = dateString.split("-");
		    	 if(DatesArr != null && DatesArr.length>0){
		    		 startDate = sdf.parse(commonMethodsUtilService.getStringValueForObject(DatesArr[0]));
		    		 endDate = sdf.parse(commonMethodsUtilService.getStringValueForObject(DatesArr[1]));
		    		 
		    		 committeeBO.setStartDate(startDate);
		    		 committeeBO.setEndDate(endDate);
		    	 }
		     }
		     
		     //setting committee enrollment year
		      committeeBO.setEnrollmentYearList(committeeEnrollmentYearsIdsLst);
		      
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
		     statusList.add("started");
		     statusList.add("notStarted");
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
										   memberVO.setStartedCount(memberVO.getStartedCount()+countVO.getStartedCount());
										  // memberVO.setNotStartedCount(memberVO.getNotStartedCount()+countVO.getNotStartedCount());
										   memberVO.setNotStartedCount(memberVO.getTotalCount() - (memberVO.getStartedCount()+memberVO.getCompletedCount()));
									   }
								   }
							   } 
						   }else if(type.equalsIgnoreCase("percanatge"))
						   {
							   if(memberVO.getTotalCount()!=null && memberVO.getTotalCount() > 0l)
							   {
								   memberVO.setCompletedPerc( coreDashboardGenericService.caclPercantage(memberVO.getCompletedCount(),memberVO.getTotalCount()) );
								   memberVO.setStartedPerc(coreDashboardGenericService.caclPercantage(memberVO.getStartedCount(), memberVO.getTotalCount()));
								   memberVO.setNotStartedPerc(coreDashboardGenericService.caclPercantage(memberVO.getNotStartedCount(), memberVO.getTotalCount()));
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
		public List<CommitteeDataVO> getLevelWiseBasicCommitteesCountReport(Long userAccessLevelId,List<Long> userAccessLevelValues,String state,Map<Long,List<Long>> committeeLevelBasedCommitteeIdsMap,String dateString,List<Long> committeeEnrollmentYearsIdsLst,
				String committeType){
		
		LOG.info(" entered in to getLevelWiseBasicCommitteesCountReport() ");
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		List<CommitteeDataVO> finalList = null;
		Date startDate = null;
		Date endDate = null;
		try{
			 
		     
		     //Making BO.
		     CommitteeInputVO committeeBO = new CommitteeInputVO();
		     committeeBO.setCommitteType(committeType);
		     coreDashboardGenericService.getRequiredCommitteeLevelIdsByUserAccessLevelId(userAccessLevelId,committeeBO);
		     coreDashboardGenericService.setAppropriateLocationLevelInputsToBO(userAccessLevelId,userAccessLevelValues,committeeBO);
		     //committeeBO.setBasicCommitteeIds(basicCommitteeIds);
		     committeeBO.setCommitteesQueryString(prepareQueryForCommitteeLevelBasedCommitteeIds(committeeLevelBasedCommitteeIdsMap,committeType));
		     if(dateString != null && !dateString.isEmpty()){
				//committeeBO.setDate(sdf.parse(dateString));
		    	 if(dateString != null && !dateString.isEmpty()){
		    		   // committeeBO.setDate(sdf.parse(dateString));
		    			 String DatesArr[] = dateString.split("-");
		    			 if(DatesArr != null && DatesArr.length>0){
		    				 startDate = sdf.parse(commonMethodsUtilService.getStringValueForObject(DatesArr[0]));
		    				 endDate = sdf.parse(commonMethodsUtilService.getStringValueForObject(DatesArr[1]));
		    				 
		    				 committeeBO.setStartDate(startDate);
		    				 committeeBO.setEndDate(endDate);
		    			 }
		    		 }
			 }
		     Long stateId = coreDashboardGenericService.getStateIdByState(state);
		     committeeBO.setStateId(stateId);
		     
		     //Setting enrollment years ids
		     committeeBO.setEnrollmentYearList(committeeEnrollmentYearsIdsLst);
		     
		     Map<Long,CommitteeDataVO> committeeLevelMap = new LinkedHashMap<Long,CommitteeDataVO>(0);
		  
		     List<Object[]> totalCommitteesList=null, completedCommitteesList=null, startedCommitteesList=null , notStartedCommitteesList = null;
		     
		     if(committeeBO.getCommitteType().trim().equalsIgnoreCase("tdpCommittee")){
		    	 setCommitteeLevelstoMap(committeeLevelMap,committeeBO.getTdpCommitteeLevelIds(),committeeLevelBasedCommitteeIdsMap);
		    	 
			     totalCommitteesList      =  tdpCommitteeDAO.levelWiseBasicCommitteesCount(committeeBO);
			     
			     committeeBO.setStatus("completed");
			     completedCommitteesList  =  tdpCommitteeDAO.levelWiseBasicCommitteesCount(committeeBO);
			     
			     committeeBO.setStatus("started");
			     startedCommitteesList  =  tdpCommitteeDAO.levelWiseBasicCommitteesCount(committeeBO);
			     
			     committeeBO.setStatus("notStarted");
			     notStartedCommitteesList =  tdpCommitteeDAO.levelWiseBasicCommitteesCount(committeeBO);
			     
			     setCountByLevelAndBasicCommittees(totalCommitteesList,committeeLevelMap,null,committeeBO.getCommitteType().trim());
			     setCountByLevelAndBasicCommittees(completedCommitteesList,committeeLevelMap,"completed",committeeBO.getCommitteType().trim());
			     setCountByLevelAndBasicCommittees(startedCommitteesList,committeeLevelMap,"started",committeeBO.getCommitteType().trim());
			     setCountByLevelAndBasicCommittees(notStartedCommitteesList,committeeLevelMap,"notStarted",committeeBO.getCommitteType().trim());
			    
			} else if (committeeBO.getCommitteType().trim().equalsIgnoreCase("boothCommittee")) {
				
		    	 setboothCommitteeLevelstoMap(committeeLevelMap,committeeBO.getTdpCommitteeLevelIds(),committeeLevelBasedCommitteeIdsMap);

				totalCommitteesList = boothDAO.levelWiseBasicCommitteesCount(committeeBO);

				committeeBO.setStatus("started");
				startedCommitteesList = boothInchargeDAO.levelWiseBasicCommitteesCount(committeeBO);

				setCountByLevelAndBasicBoothCommittees(totalCommitteesList,committeeLevelMap, null, committeeBO.getCommitteType().trim());
				setCountByLevelAndBasicBoothCommittees(startedCommitteesList,committeeLevelMap, "started", committeeBO.getCommitteType().trim());
				setCountByLevelAndBasicBoothCommittees(totalCommitteesList,committeeLevelMap, "notStarted", committeeBO.getCommitteType().trim());

			}
		    
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
		public void setCommitteeLevelstoMap(Map<Long,CommitteeDataVO> committeeLevelMap,List<Long> requiredCommitteeLevelIds,Map<Long,List<Long>> committeeLevelBasedCommitteeIdsMap){
			 Map<Long,String> committeeLevelNameMap = getAllCommitteeLevels();
			 Map<Long,String> committeeNameMap   = getCommitteesNames();
			 
		  	 if(requiredCommitteeLevelIds != null && requiredCommitteeLevelIds.size() > 0){
				 for(Long committeeLevelId : requiredCommitteeLevelIds){
					 if(committeeLevelId != 7 && committeeLevelId != 9 && committeeLevelId != 8 ){
						 CommitteeDataVO committeeLevelVO = new CommitteeDataVO();
						 committeeLevelVO.setId(committeeLevelId);
						 committeeLevelVO.setName(committeeLevelNameMap.get(committeeLevelId));
						 committeeLevelVO.setSubMap(getBasicCommitteesMap(committeeLevelBasedCommitteeIdsMap,committeeNameMap,committeeLevelId));
						 committeeLevelMap.put(committeeLevelVO.getId(), committeeLevelVO);
					 }
				 }
			  }
		}
		public void setboothCommitteeLevelstoMap(Map<Long,CommitteeDataVO> committeeLevelMap,List<Long> requiredCommitteeLevelIds,Map<Long,List<Long>> committeeLevelBasedCommitteeIdsMap){
			 Map<Long,String> committeeLevelNameMap = getAllCommitteeLevels();
			 Map<Long,String> committeeNameMap   = getCommitteesNames();
			 
		  	 if(requiredCommitteeLevelIds != null && requiredCommitteeLevelIds.size() > 0){
				 for(Long committeeLevelId : requiredCommitteeLevelIds){
					 if(committeeLevelId != 7 && committeeLevelId != 9 && committeeLevelId != 8 ){
						 CommitteeDataVO committeeLevelVO = new CommitteeDataVO();
						 committeeLevelVO.setId(committeeLevelId);
						 committeeLevelVO.setName(committeeLevelNameMap.get(committeeLevelId));
						 committeeLevelVO.setSubMap(getBasicCommitteesMap(committeeLevelBasedCommitteeIdsMap,committeeNameMap,committeeLevelId));
						 committeeLevelMap.put(committeeLevelVO.getId(), committeeLevelVO);
					 }
				 }
			  }
		}

		public Map<Long,CommitteeDataVO> getBasicCommitteesMap(Map<Long,List<Long>> committeeLevelBasedCommitteeIdsMap,Map<Long,String> committeeNameMap,Long committeeLevelId){
			
			Map<Long,CommitteeDataVO> basicCommitteeMap = new LinkedHashMap<Long, CommitteeDataVO>(); 
			
			List<Long> requiredBasicCommitteeIds =  committeeLevelBasedCommitteeIdsMap.get(committeeLevelId);
			
			if(requiredBasicCommitteeIds != null && requiredBasicCommitteeIds.size()>0){
				for(Long basicCommitteeId : requiredBasicCommitteeIds){
					CommitteeDataVO basicCommitteeVO = new CommitteeDataVO();
					basicCommitteeVO.setId(basicCommitteeId);
					basicCommitteeVO.setName(committeeNameMap.get(basicCommitteeId));
					basicCommitteeMap.put(basicCommitteeVO.getId(), basicCommitteeVO);
				}
			}
			return basicCommitteeMap;
		}

	public void setCountByLevelAndBasicCommittees(List<Object[]> list,Map<Long, CommitteeDataVO> committeeLevelMap, String status,
			String committeeType) {

		try {

			if (list != null && list.size() > 0) {
				for (Object[] obj : list) {
					Long tdpCommitteeLevelId = obj[0] != null ? (Long) obj[0] : 0l;
					CommitteeDataVO committeeLevelVO = null;
					if (tdpCommitteeLevelId.longValue() == 7 || tdpCommitteeLevelId.longValue() == 9) { // Mandal/town/division
						committeeLevelVO = committeeLevelMap.get(5l);
					} else if (tdpCommitteeLevelId.longValue() == 8) { // village/ward
						committeeLevelVO = committeeLevelMap.get(6l);
					} else {
						committeeLevelVO = committeeLevelMap.get(tdpCommitteeLevelId);
					}

					if (committeeLevelVO != null&& committeeLevelVO.getSubMap() != null) {
						Long basicCommitteeId = obj[2] != null ? (Long) obj[2]: 0l;
						CommitteeDataVO basicCommitteeVO = committeeLevelVO.getSubMap().get(basicCommitteeId);
						if (basicCommitteeVO != null) {
							Long count = obj[6] != null ? (Long) obj[6] : 0l;
							if (status != null && !status.isEmpty()) {
								if (status.equalsIgnoreCase("completed")) {
									basicCommitteeVO.setCompletedCount(basicCommitteeVO.getCompletedCount()+ count);
								} else if (status.equalsIgnoreCase("started")) {
									basicCommitteeVO.setStartedCount(basicCommitteeVO.getStartedCount() + count);
								} else if (status.equalsIgnoreCase("notStarted")) {
									basicCommitteeVO.setNotStartedCount(basicCommitteeVO.getNotStartedCount()+ count);
								}
							} else {
								basicCommitteeVO.setTotalCount(basicCommitteeVO.getTotalCount() + count);
							}
						}
					}
				}
			}
		} catch (Exception e) {
			LOG.error("exception occurred in setCountByLevelAndBasicCommittees()",e);
		}

	}

	public void setCountByLevelAndBasicBoothCommittees(List<Object[]> list,Map<Long, CommitteeDataVO> committeeLevelMap, String status,
			String committeeType) {

		try {

			if (list != null && list.size() > 0) {
				for (Object[] obj : list) {
					Integer i = obj[0] != null ? (Integer) obj[0] : 0;
					Long tdpCommitteeLevelId = i.longValue();
					CommitteeDataVO committeeLevelVO = null;
					if (tdpCommitteeLevelId.longValue() == 7 || tdpCommitteeLevelId.longValue() == 9) { // Mandal/town/division
						committeeLevelVO = committeeLevelMap.get(5l);
					} else if (tdpCommitteeLevelId.longValue() == 8) { // village/ward
						committeeLevelVO = committeeLevelMap.get(6l);
					} else {
						committeeLevelVO = committeeLevelMap.get(tdpCommitteeLevelId);
					}

					Integer basicCommitteeIdInt = obj[2] != null ? (Integer) obj[2] : 0;
					Long basicCommitteeId = basicCommitteeIdInt.longValue();
					CommitteeDataVO basicCommitteeVO = committeeLevelVO.getSubMap().get(basicCommitteeId);
					if (basicCommitteeVO != null) {
						Long count = obj[6] != null ? (Long) obj[6] : 0;
						if (status != null && !status.isEmpty()) {
							if (status.equalsIgnoreCase("started")) {
								basicCommitteeVO.setStartedCount(basicCommitteeVO.getStartedCount() + count);
							} else if (status.equalsIgnoreCase("notStarted")) {
								basicCommitteeVO.setNotStartedCount((count - basicCommitteeVO.getStartedCount()) + count);
							}
						} else {
							basicCommitteeVO.setTotalCount(basicCommitteeVO.getTotalCount() + count);
						}
					}
				}
			}
		} catch (Exception e) {
			LOG.error("exception occurred in setCountByLevelAndBasicCommittees()",e);
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
		public List<CommitteeDataVO> committeesPerformanceCohort(List<Long> tdpCommitteeLevelIdsClicked,Map<Long,List<Long>> committeeLevelBasedCommitteeIdsMap,String committeeStatus,Long userLocationLevelId,List<Long> userLocationLevelValues,String dateString,String state,List<Long> committeeEnrollmentYearsIdsLst, String committeType){
		    List<CommitteeDataVO> finalList = null;
		    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		    try{
		    	Date startDate = null;
		    	Date endDate = null;
		      //CREATING BUSINESS OBJECT.
		      CommitteeInputVO committeeBO = new CommitteeInputVO();
		      committeeBO.setCommitteType(committeType);
		      committeeBO.setTdpCommitteeLevelIds(tdpCommitteeLevelIdsClicked);
		      committeeBO.setCommitteesQueryString(prepareQueryForCommitteeLevelBasedCommitteeIds(committeeLevelBasedCommitteeIdsMap,committeType));
		      if(dateString != null && !dateString.isEmpty()){
		        // committeeBO.setDate(sdf.parse(dateString));
		    	  
		    	  if(dateString != null && !dateString.isEmpty()){
		    		   // committeeBO.setDate(sdf.parse(dateString));
		    			 String DatesArr[] = dateString.split("-");
		    			 if(DatesArr != null && DatesArr.length>0){
		    				 startDate = sdf.parse(commonMethodsUtilService.getStringValueForObject(DatesArr[0]));
		    				 endDate = sdf.parse(commonMethodsUtilService.getStringValueForObject(DatesArr[1]));
		    				 
		    				 committeeBO.setStartDate(startDate);
		    				 committeeBO.setEndDate(endDate);
		    			 }
		    		 }
		    	  
		       }
		      // Setting committee enrollment years
		      committeeBO.setEnrollmentYearList(committeeEnrollmentYearsIdsLst);
		      
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
		      
		      if(groupingLocationsList != null && groupingLocationsList.size() > 0 && committeType.trim().equalsIgnoreCase("tdpCommittee")){
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
		      }else if(groupingLocationsList != null && groupingLocationsList.size() > 0 && committeType.trim().equalsIgnoreCase("boothCommittee")){

			    	
			        for(String groupingLocation : groupingLocationsList){
			          
			          committeeBO.setGroupingLocation(groupingLocation);
			          
			         /* List<Object[]>  totalList   = boothDAO.committeesPerformanceCohort(committeeBO);
			          List<Object[]> startedList = null;
			          
			          if(committeeStatus.equalsIgnoreCase("started") || committeeStatus.equalsIgnoreCase("all")){
			            
			            committeeBO.setStatus("started");
			            startedList = boothInchargeDAO.committeesPerformanceCohort(committeeBO);
			          }*/
			          
			          committeeBO.setStatus("total");
			          List<Object[]>  totalList   = boothInchargeDAO.committeesPerformanceCohort(committeeBO);
			          List<Object[]> startedList = null;
			          List<Object[]> completedList = null;
			          List<Object[]> notStartedList = null;
			          
			          if(committeeStatus.equalsIgnoreCase("started") || committeeStatus.equalsIgnoreCase("all")){
			            
			            committeeBO.setStatus("started");
			            startedList =boothInchargeDAO.committeesPerformanceCohort(committeeBO);
			          }
			          if(committeeStatus.equalsIgnoreCase("completed") || committeeStatus.equalsIgnoreCase("all") ){
			            
			            committeeBO.setStatus("completed"); 
			            completedList = boothInchargeDAO.committeesPerformanceCohort(committeeBO);
			            
			          }
			          if(committeeStatus.equalsIgnoreCase("notStarted") || committeeStatus.equalsIgnoreCase("all") ){
			            
			            committeeBO.setStatus("notStarted");
			            notStartedList = boothInchargeDAO.committeesPerformanceCohort(committeeBO);
			          }
			          
			          setLocationWiseBoothCommiteesCount(totalList,finalMap,null,groupingLocation);
			          setLocationWiseBoothCommiteesCount(startedList,finalMap,"started",groupingLocation);
			          setLocationWiseBoothCommiteesCount(notStartedList,finalMap,"notStarted",groupingLocation);
			          setLocationWiseBoothCommiteesCount(completedList,finalMap,"completed",groupingLocation);
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

	public void setLocationWiseCommiteesCount(List<Object[]> list,Map<Long, CommitteeDataVO> finalMap, String status,String groupingLocation) {

		if (list != null && list.size() > 0) {
			for (Object[] obj : list) {
				
				Long tdpbasicCommitteeId = obj[1] != null ? (Long)obj[1] : 0l;
				CommitteeDataVO basicCommitteeVO = null;
				basicCommitteeVO = finalMap.get(tdpbasicCommitteeId);
				if (basicCommitteeVO == null) {
					basicCommitteeVO = new CommitteeDataVO();
					basicCommitteeVO.setId(tdpbasicCommitteeId);
					basicCommitteeVO.setName(obj[2] != null ? obj[2].toString() : "");
					basicCommitteeVO.setSubMap(new LinkedHashMap<Long, CommitteeDataVO>(0));

					finalMap.put(tdpbasicCommitteeId, basicCommitteeVO);
				}
				basicCommitteeVO = finalMap.get(tdpbasicCommitteeId);
				CommitteeDataVO locationVO = null;
				locationVO = basicCommitteeVO.getSubMap().get((Long) obj[3]);
				if (locationVO == null) {
					locationVO = new CommitteeDataVO();
					locationVO.setId((Long) obj[3]);
					locationVO.setName(obj[4] != null ? obj[4].toString() : "");

					if (groupingLocation.equalsIgnoreCase("LocalElectionBody")) {
						locationVO.setLocationLevelName(obj[6] != null ? obj[6].toString() : "");
					} else {
						locationVO.setLocationLevelName(groupingLocation);
					}

					basicCommitteeVO.getSubMap().put(locationVO.getId(),locationVO);
				}
				locationVO = basicCommitteeVO.getSubMap().get((Long) obj[3]);
				Long count = obj[0] != null ? (Long) obj[0] : 0l;
				if (status != null && !status.isEmpty()) {
					if (status.equalsIgnoreCase("completed")) {
						locationVO.setCompletedCount(locationVO.getCompletedCount() + count);
					} else if (status.equalsIgnoreCase("started")) {
						locationVO.setStartedCount(locationVO.getStartedCount()+ count);
					} else if (status.equalsIgnoreCase("notStarted")) {
						locationVO.setNotStartedCount(locationVO.getNotStartedCount() + count);
					}
				} else {
					locationVO.setTotalCount(locationVO.getTotalCount() + count);
				}
			}
		}
	}

public void setLocationWiseBoothCommiteesCount(List<Object[]> list,Map<Long,CommitteeDataVO> finalMap,String status,String groupingLocation){
	  
	  if(list !=null && list.size() >0){
	    for(Object[] obj : list){
	      
	      Integer tdpboothcommiteeId = obj[1] != null ? (Integer) obj[1] : 0;
		  Long tdpbasicCommitteeId = tdpboothcommiteeId.longValue();
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
		         // Long completedCount = (count-(locationVO.getStartedCount()+locationVO.getNotStartedCount()))+count;
		          //locationVO.setCompletedCount(completedCount);
	        	
	        }else if(status.equalsIgnoreCase("started")){
	          locationVO.setStartedCount(locationVO.getStartedCount() + count);
	        }else if(status.equalsIgnoreCase("notStarted")){
	        	locationVO.setNotStartedCount((count - locationVO.getStartedCount()) + count);
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
   public List<UserTypeVO> getSelectedChildUserTypeMembers(Long parentActivityMemberId,List<Long> childUserTypeIds,String state,Map<Long,List<Long>> committeeLevelBasedCommitteeIdsMap,String dateString,List<Long> committeeEnrollmentYearsIdsLst,String committeType){
	    List<UserTypeVO> activityMembersList = null;
	    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
	   try{
		   Date startDate = null;
		   Date endDate = null;
		     //Creating Business Object.
		     CommitteeInputVO committeeBO = new CommitteeInputVO();
		     committeeBO.setCommitteesQueryString(prepareQueryForCommitteeLevelBasedCommitteeIds(committeeLevelBasedCommitteeIdsMap,committeType));
		     Long stateId = coreDashboardGenericService.getStateIdByState(state);
		     committeeBO.setStateId(stateId);
		     committeeBO.setCommitteType(committeType);
		     if(dateString != null && !dateString.isEmpty()){
		    	 //committeeBO.setDate(sdf.parse(dateString));
		    	 
		    	 if(dateString != null && !dateString.isEmpty()){
		    		   // committeeBO.setDate(sdf.parse(dateString));
		    			 String DatesArr[] = dateString.split("-");
		    			 if(DatesArr != null && DatesArr.length>0){
		    				 startDate = sdf.parse(commonMethodsUtilService.getStringValueForObject(DatesArr[0]));
		    				 endDate = sdf.parse(commonMethodsUtilService.getStringValueForObject(DatesArr[1]));
		    				 
		    				 committeeBO.setStartDate(startDate);
		    				 committeeBO.setEndDate(endDate);
		    			 }
		    		 }
		    	 
		     }
		   
		      // Setting committee enrollment years
		      committeeBO.setEnrollmentYearList(committeeEnrollmentYearsIdsLst);
		      
		     //calling generic method.
		   /* ActivityMemberVO activityMemberVO = coreDashboardGenericService.getSelectedChildUserTypeMembers(parentActivityMemberId,childUserTypeId);*/
		    ActivityMemberVO activityMemberVO = coreDashboardGenericService.getRequiredSubLevelActivityMembersDetails(parentActivityMemberId,childUserTypeIds);
		    Map<Long,UserTypeVO> childActivityMembersMap = activityMemberVO.getActivityMembersMap();
		    Map<Long,Set<Long>> locationLevelIdsMap = activityMemberVO.getLocationLevelIdsMap();
		     
		     //get commitees count based on location level id and location level values.
		     List<String> statusList = new ArrayList<String>();
		     statusList.add("completed");
		     statusList.add("started");
		     statusList.add("notStarted");
		     committeeBO.setStatusList(statusList);
		     Map<String,UserTypeVO> locationLevelCountsMap = getCommitteesCountByLocationLevelIdAndLevelValues(locationLevelIdsMap,committeeBO);
		     Map<String,String>     nameForLocationMap  = coreDashboardGenericService.getLocationNamesByLocationIds(locationLevelIdsMap);
		     
		     //add counts to activity members.
		     activityMembersList = setCommitteeCountsToActivityMembers(childActivityMembersMap,"counts",locationLevelCountsMap,nameForLocationMap);
		     if(activityMembersList!=null && activityMembersList.size()>0){
		    	 setCommitteeCountsToActivityMembers(childActivityMembersMap,"percanatge",null,null);
		    	
		    	 //Remove those candidate.Which location has not committee.
		    	   List<UserTypeVO> filterCandidateList = new ArrayList<UserTypeVO>();
				    for(UserTypeVO candiateVO:activityMembersList){
				    	 if(candiateVO.getTotalCount()>0l){
				    		 filterCandidateList.add(candiateVO);
				    	 }
				    }
				    activityMembersList.clear();
				    activityMembersList.addAll(filterCandidateList);
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
	    		
	    		 if(committeeBO.getCommitteType() != null && !committeeBO.getCommitteType().isEmpty()){
	    			 if(committeeBO.getCommitteType().trim().equalsIgnoreCase("tdpCommittee")){
	    				 List<Object[]> totalCountList = tdpCommitteeDAO.getLocationWiseCommitteesCountByLocIds(committeeBO);
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
	    	    		 setCommitteesCountToItsCorrespondingLocation("total",totalCountList,locationLevelCountsMap,userAccessLevelId);
	    			 }
	    			 else if(committeeBO.getCommitteType().trim().equalsIgnoreCase("boothCommittee")){
	    				 committeeBO.setStatus("total");
	    				 List<Object[]> totalCountList = boothInchargeDAO.getLocationWiseCommitteesCountByLocIds(committeeBO);
	    				 if(committeeBO.getStatusList().contains("completed")){
	    	    			 committeeBO.setStatus("completed");
	    	    			 List<Object[]> completedCountList = boothInchargeDAO.getLocationWiseCommitteesCountByLocIds(committeeBO);
	    	    			 setBoothCommitteesCountToItsCorrespondingLocation("completed",completedCountList,locationLevelCountsMap,userAccessLevelId);
	    	    		 }
	    	    		 if(committeeBO.getStatusList().contains("started")){
	    	    			 committeeBO.setStatus("started");
	    	    			 List<Object[]> startedCountList = boothInchargeDAO.getLocationWiseCommitteesCountByLocIds(committeeBO);
	    	    			 setBoothCommitteesCountToItsCorrespondingLocation("started",startedCountList,locationLevelCountsMap,userAccessLevelId);
	    	    		 }
	    	    		 /*if(committeeBO.getStatusList().contains("notStarted")){
	    	    			 committeeBO.setStatus("notStarted");
	    	    			 List<Object[]> notStartedList = boothInchargeDAO.getLocationWiseCommitteesCountByLocIds(committeeBO);
	    	    			 setBoothCommitteesCountToItsCorrespondingLocation("notStarted",notStartedList,locationLevelCountsMap,userAccessLevelId);
	    	    		 }*/
	    	    		 setBoothCommitteesCountToItsCorrespondingLocation("total",totalCountList,locationLevelCountsMap,userAccessLevelId);
	    	    		 getNotStartedBoothCount(locationLevelCountsMap);
	    			 }
	    		 }
	    	 }    
	     }
	    return locationLevelCountsMap;
   }
   public void getNotStartedBoothCount( Map<String,UserTypeVO> locationLevelCountsMap){
	   try {
		   if(locationLevelCountsMap != null && locationLevelCountsMap.size() > 0){
			   for (Entry<String, UserTypeVO> entry : locationLevelCountsMap.entrySet()) {
				entry.getValue().setNotStartedCount(entry.getValue().getTotalCount()-(entry.getValue().getStartedCount()+entry.getValue().getCompletedCount()));
			}
		   }
	   } catch(Exception e){
		   LOG.error("Exception occured at getNotStartedBoothCount() in CoreDashboardMainService"+e);
	   }
	   
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
   
   public void setBoothCommitteesCountToItsCorrespondingLocation(String status,List<Object[]> list,Map<String,UserTypeVO> locationLevelCountsMap,Long accessLevelId){
		
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
					 }else if(status.equalsIgnoreCase("started")){
						 countsVO.setStartedCount(obj[0]!=null?(Long)obj[0]:0l); 
					 }else if(status.equalsIgnoreCase("completed")){
						 countsVO.setCompletedCount(obj[0]!=null?(Long)obj[0]:0l); 
					 }/*else if(status.equalsIgnoreCase("notStarted")){
						 Long toalcount =obj[0]!=null?(Long)obj[0]:0l;
						 Long count= toalcount-(countsVO.getStartedCount()+countsVO.getCompletedCount());
						 countsVO.setNotStartedCount(count); 
					 }*/
				 }
			 }
	 }
  
 public List<UserTypeVO> getDirectChildActivityMemberCommitteeDetails(Long activityMemberId,Long userTypeId,String state,Map<Long,List<Long>> committeeLevelBasedCommitteeIdsMap,String dateString,List<Long> committeeEnrollmentYearsIdsLst,
String committeType){
	    List<UserTypeVO> activityMembersList = null;
	    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
	   try{
		   Date startDate = null;
		   Date endDate = null;
		     //Creating Business Object.
		     CommitteeInputVO committeeBO = new CommitteeInputVO();
		     committeeBO.setCommitteesQueryString(prepareQueryForCommitteeLevelBasedCommitteeIds(committeeLevelBasedCommitteeIdsMap,committeType));
		     Long stateId = coreDashboardGenericService.getStateIdByState(state);
		     committeeBO.setStateId(stateId);
		     committeeBO.setCommitteType(committeType);
		     if(dateString != null && !dateString.isEmpty()){
		    	// committeeBO.setDate(sdf.parse(dateString));
		    	 String DatesArr[] = dateString.split("-");
		    	 if(DatesArr != null && DatesArr.length>0){
		    		 startDate = sdf.parse(commonMethodsUtilService.getStringValueForObject(DatesArr[0]));
		    		 endDate = sdf.parse(commonMethodsUtilService.getStringValueForObject(DatesArr[1]));
		    		 
		    		 committeeBO.setStartDate(startDate);
		    		 committeeBO.setEndDate(endDate);
		    	 }
		     }
		   
		     // Setting committee enrollment years
		      committeeBO.setEnrollmentYearList(committeeEnrollmentYearsIdsLst);
		      
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
		    	
		    	  //Remove those candidate.Which location has not committee.
		    	   List<UserTypeVO> filterCandidateList = new ArrayList<UserTypeVO>();
				    for(UserTypeVO candiateVO:activityMembersList){
				    	 if(candiateVO.getTotalCount()>0l){
				    		 filterCandidateList.add(candiateVO);
				    	 }
				    }
				    activityMembersList.clear();
				    activityMembersList.addAll(filterCandidateList);
				    
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
 public CommitteeDataVO getTopPoorPerformancecommittees(Long activityMemberId,Map<Long,List<Long>> committeeLevelBasedCommitteeIdsMap,String state,String dateString,List<Long> committeeEnrollmentYearsIdsLst,
			String committeType){
	   SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
	   CommitteeDataVO finalVO = new CommitteeDataVO();
	   try {
		   Date startDate = null;
		   Date endDate = null;
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
		     committeeBO.setCommitteType(committeType);
		     committeeBO.setCommitteesQueryString(prepareQueryForCommitteeLevelBasedCommitteeIds(committeeLevelBasedCommitteeIdsMap,committeType));
		     Long stateId = coreDashboardGenericService.getStateIdByState(state);
		     committeeBO.setStateId(stateId);
		     if(dateString != null && !dateString.isEmpty()){
		    	// committeeBO.setDate(sdf.parse(dateString));
		    	 String DatesArr[] = dateString.split("-");
		    	 if(DatesArr != null && DatesArr.length>0){
		    		 startDate = sdf.parse(commonMethodsUtilService.getStringValueForObject(DatesArr[0]));
		    		 endDate = sdf.parse(commonMethodsUtilService.getStringValueForObject(DatesArr[1]));
		    		 
		    		 committeeBO.setStartDate(startDate);
		    		 committeeBO.setEndDate(endDate);
		    	 }
		     }
		      // Setting committee enrollment years
		      committeeBO.setEnrollmentYearList(committeeEnrollmentYearsIdsLst);
		      
		     List<Long> requiredCommitteeLevelIds = coreDashboardGenericService.getRequiredTdpCommitteeLevelIdsByUserAccessLevelId(userLocationLevelId,userLocationLevelValues);
		     committeeBO.setTdpCommitteeLevelIds(requiredCommitteeLevelIds);
		     coreDashboardGenericService.setAppropriateLocationLevelInputsToBO(userLocationLevelId,userLocationLevelValues,committeeBO);
		     committeeBO.setQueryString(coreDashboardGenericService.getCommittesRelatedLocationQuerypart(committeeBO));
		     
		     //pre data setting.
		     Map<Long,String> committeeLevelNameMap = getAllCommitteeLevels();
		     Map<Long,String> committeeNamesMap = getCommitteesNames(); 
		     
		     Map<Long,CommitteeDataVO> committeeLevelMap = new LinkedHashMap<Long,CommitteeDataVO>(0);
		     if(!committeeBO.getCommitteType().equalsIgnoreCase("boothCommittee")){
		   	 if(requiredCommitteeLevelIds != null && requiredCommitteeLevelIds.size() > 0){
				 for(Long committeeLevelId : requiredCommitteeLevelIds){
					 if(committeeLevelId != 7 && committeeLevelId != 9 && committeeLevelId != 8 ){
						 CommitteeDataVO committeeDataVO = new CommitteeDataVO();
						 committeeDataVO.setId(committeeLevelId);
						 committeeDataVO.setName(committeeLevelNameMap.get(committeeLevelId));
						 
						 //basic committees.
						 List<Long> requiredBasicCommitteeIds = committeeLevelBasedCommitteeIdsMap.get(committeeLevelId);
						 if(requiredBasicCommitteeIds != null && requiredBasicCommitteeIds.size()>0){
							 Map<Long,CommitteeDataVO> basicCommitteesMap = new LinkedHashMap<Long, CommitteeDataVO>();
							 for(Long basicCommitteeId : requiredBasicCommitteeIds){
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
	   		}else{
	   			
	   		 CommitteeDataVO committeeDataVO = new CommitteeDataVO();
			 committeeDataVO.setId(1L);
			 committeeDataVO.setName("Booth Committee");
			 
			 //basic committees.
			 List<Long> requiredBasicCommitteeIds = committeeLevelBasedCommitteeIdsMap.get(1L);
			 if(requiredBasicCommitteeIds != null && requiredBasicCommitteeIds.size()>0){
				 Map<Long,CommitteeDataVO> basicCommitteesMap = new LinkedHashMap<Long, CommitteeDataVO>();
				 for(Long basicCommitteeId : requiredBasicCommitteeIds){
					 CommitteeDataVO basicCommitteeVO = new CommitteeDataVO();
					 basicCommitteeVO.setId(1L);
					 basicCommitteeVO.setName(committeeNamesMap.get(basicCommitteeId));
					 basicCommitteesMap.put(basicCommitteeId, basicCommitteeVO);
				 }
				 committeeDataVO.setSubMap(basicCommitteesMap);
			 }
			 
			 committeeLevelMap.put(committeeDataVO.getId(), committeeDataVO);
			 
	   			List<Object[]> totalCommittees = boothInchargeDAO.getBoothCommitteesCommitteeLevelWiseCountsByLocIds(committeeBO);
			     setLevelWiseBasicCommitteesCounts(committeeLevelMap,totalCommittees,"total");
			     
			     committeeBO.setStatus("completed");
			     List<Object[]> completedCommittees = boothInchargeDAO.getBoothCommitteesCommitteeLevelWiseCountsByLocIds(committeeBO);
			     setLevelWiseBasicCommitteesCounts(committeeLevelMap,completedCommittees,"completed");
	   		}
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
			   Set<Long> basicCommitteeIdsSet = new HashSet<Long>(); 
			   for(Long committeeLevelId : committeeLevelBasedCommitteeIdsMap.keySet()){
				   basicCommitteeIdsSet.addAll(committeeLevelBasedCommitteeIdsMap.get(committeeLevelId));
			   }
			   List<Long> basicCommitteeIds = new ArrayList<Long>(basicCommitteeIdsSet);
			   if(basicCommitteeIds != null && basicCommitteeIds.size()>0){
					 for(Long basicCommitteeId : basicCommitteeIds){
						 CommitteeDataVO basicCommitteeVO = new CommitteeDataVO();
						 basicCommitteeVO.setId(basicCommitteeId);
						 basicCommitteeVO.setName(committeeNamesMap.get(basicCommitteeId));
						 basicCommitteesMap.put(basicCommitteeId, basicCommitteeVO);
					 }
				}
			   if(!committeeBO.getCommitteType().equalsIgnoreCase("boothCommittee")){
				 committeeBO.setStatus(null);
				 List<Object[]> totalList = tdpCommitteeDAO.getCumulativeCommitteesCountsByLocIds(committeeBO);
				 committeeBO.setStatus("completed");
				 List<Object[]> completedList = tdpCommitteeDAO.getCumulativeCommitteesCountsByLocIds(committeeBO);
				 setCountsToBasicCommittees(basicCommitteesMap,totalList,"total");
				 setCountsToBasicCommittees(basicCommitteesMap,completedList,"completed");
			   }else{
			   	 committeeBO.setStatus(null);
				 List<Object[]> totalList = boothInchargeDAO.getBoothCommitteesCumulativeCommitteesCountsByLocIds(committeeBO);
				 committeeBO.setStatus("completed");
				 List<Object[]> completedList = boothInchargeDAO.getBoothCommitteesCumulativeCommitteesCountsByLocIds(committeeBO);
				 setCountsToBasicCommittees(basicCommitteesMap,totalList,"total");
				 setCountsToBasicCommittees(basicCommitteesMap,completedList,"completed");
			   }
			
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
		    	    
		    		 Long tdpCommitteelevelId = commonMethodsUtilService.getLongValueForObject(obj[0]);
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
	    		CommitteeDataVO basicCommitteeVO = basicCommitteesMap.get(commonMethodsUtilService.getLongValueForObject(obj[0]));
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
	public List<CommitteeDataVO> getTopPoorCommitteeLocations(Long activityMemberId,Map<Long,List<Long>> committeeLevelBasedCommitteeIdsMap,String state,String dateString,List<Long> committeeEnrollmentYearsIdsLst,
			String committeType){
		   List<CommitteeDataVO> finalList = null;
		   SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		   try{
			   Date startDate = null;
			   Date endDate = null;
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
			     //committeeBO.setBasicCommitteeIds(basicCommitteeIds);
			     committeeBO.setCommitteesQueryString(prepareQueryForCommitteeLevelBasedCommitteeIds(committeeLevelBasedCommitteeIdsMap,committeType));
			     Long stateId = coreDashboardGenericService.getStateIdByState(state);
			     committeeBO.setStateId(stateId);
			     committeeBO.setCommitteType("committeType");
			     if(dateString != null && !dateString.isEmpty()){
			    	 //committeeBO.setDate(sdf.parse(dateString));
			    	 String DatesArr[] = dateString.split("-");
			    	 if(DatesArr != null && DatesArr.length>0){
			    		 startDate = sdf.parse(commonMethodsUtilService.getStringValueForObject(DatesArr[0]));
			    		 endDate = sdf.parse(commonMethodsUtilService.getStringValueForObject(DatesArr[1]));
			    		 
			    		 committeeBO.setStartDate(startDate);
			    		 committeeBO.setEndDate(endDate);
			    	 }
			     }
			     
			     // Setting committee enrollment years
			      committeeBO.setEnrollmentYearList(committeeEnrollmentYearsIdsLst);
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
			     
			     if(groupingLocationsList != null && groupingLocationsList.size() > 0 && committeType.trim().equalsIgnoreCase("tdpCommittee")){
				        for(String groupingLocation : groupingLocationsList){
				          
				          committeeBO.setGroupingLocation(groupingLocation);
				          
				          List<Object[]>  totalList   = tdpCommitteeDAO.getTopPoorCommitteeLocations(committeeBO);
				          committeeBO.setStatus("completed"); 
				          List<Object[]> completedList = tdpCommitteeDAO.getTopPoorCommitteeLocations(committeeBO);
				          
				          locationCommitteeCountSetting(totalList,finalMap,null,groupingLocation);
				          locationCommitteeCountSetting(completedList,finalMap,"completed",groupingLocation);
				          
				        }
				   }
			     if(groupingLocationsList != null && groupingLocationsList.size() > 0 && committeType.trim().equalsIgnoreCase("boothCommittee")){
				        for(String groupingLocation : groupingLocationsList){
				          
				          committeeBO.setGroupingLocation(groupingLocation);
				          				          
				          List<Object[]>  totalList   = boothDAO.getLocationWiseCommitteesCountByLocIds(committeeBO);
		    	    			 committeeBO.setStatus("completed");
		    	    			 List<Object[]> completedList = boothInchargeDAO.getTopPoorCommitteeLocations(committeeBO);
		    	    			 locationCommitteeCountSetting(completedList,finalMap,"completed",groupingLocation);
				          
				          locationCommitteeCountSetting(totalList,finalMap,"null",groupingLocation);
				          
				        }
				   }
			     
			     if(finalMap != null && finalMap.size()>0){
			    	 finalList = new ArrayList<CommitteeDataVO>(finalMap.values());
			    	 //calculating percantages
			    	 for(CommitteeDataVO locationVO : finalList){
			    		 if(locationVO.getTotalCount()!=null && locationVO.getTotalCount() > 0l && committeType.trim().equalsIgnoreCase("tdpCommittee") )  {
			    			 locationVO.setCompletedPerc(coreDashboardGenericService.caclPercantage(locationVO.getCompletedCount(),locationVO.getTotalCount()) );
						  
			    		 }else if(locationVO.getTotalCount()!=null && locationVO.getTotalCount() > 0l && committeType.trim().equalsIgnoreCase("boothCommittee") )  {
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
	   /* Training Camp Service */
	   /**
		  * @param  Long userAccessLevelId
		  * @param List<Long> userAccessLevelValues
		  * @param Long stateId
		  * @return TrainingCampProgramVO
		  * @author Santosh 
		  * @Description :This Service Method is used to get training camp basic count details.. 
		  *  @since 23-AUGUST-2016
		  */
	/*public TrainingCampProgramVO getTrainingCampBasicDetailsCntOverview(Long userAccessLevelId,List<Long> userAccessLevelValues,Long stateId,String toDateStr){
		 
	 TrainingCampProgramVO finalResultVO = new TrainingCampProgramVO();
		 Map<String,Long> totalEligibleMemberCntMap = new HashMap<String, Long>();
		 Map<String,Long> totalAttenedMemberCntMap = new HashMap<String, Long>();
		 SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		 Date toDate=null;
		 try{
			 if(toDateStr != null && !toDateStr.isEmpty() && toDateStr.length() > 0){
				 toDate = sdf.parse(toDateStr);
			 }
			List<Object[]> rtrnTtlElgbleMmbrObjList = tdpCommitteeMemberDAO.getLevelWiseTotalEligibleMembersForTrainingCampProgram(userAccessLevelId, userAccessLevelValues,stateId);//Level Wise Eligible Member
			List<Object[]> rtrnTtlAttendedMmbrObjList = trainingCampAttendanceDAO.getTotalAttenedCadresByCommitteeLevel(userAccessLevelId, userAccessLevelValues,stateId,toDate);//Level Wise Attended Member
		
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
	}*/
	   /** village
		  * @param  Long userAccessLevelId
		  * @param List<Long> userAccessLevelValues
		  * @param Long stateId
		  * @return TrainingCampProgramVO
		  * @author Santosh 
		  * @Description :This Service Method is used to get training camp basic count details.. 
		  *  @since 11-NOV-2016
		  */
	   public TrainingCampProgramVO getTrainingCampBasicDetailsCntOverview(Long userAccessLevelId,List<Long> userAccessLevelValues,Long stateId,String toDateStr,List<Long> enrollmentYearIds,List<Long> programIdList){
			 
		     /*TrainingCampProgramVO finalResultVO = new TrainingCampProgramVO();
			 Map<Long,TrainingCampProgramVO> trainingCampProgramDtlsMap = new HashMap<Long, TrainingCampProgramVO>();
			 SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
			 Date toDate=null;
			 try{
				 if(toDateStr != null && !toDateStr.isEmpty() && toDateStr.length() > 0){
					 toDate = sdf.parse(toDateStr);
				 }
				 Long accessLevelValue =0l;	
			     if(userAccessLevelId.longValue() == 4l){// user level 4 means parliament constituency in the case of core dashboard
			    	 accessLevelValue = 10l; //region scope 10  means parliament constituency in intermediate table so that we are replacing value
			     }else if(userAccessLevelId.longValue()==5l){// user level 5 means constituency in the case of core dashboard
			    	 accessLevelValue = 4l;  //region scope 4  means constituency in intermediate table so that we are replacing value
			     }else{
			    	 accessLevelValue = userAccessLevelId;	 
			     }
				
				 
				 List<Long> tdpCommitteeLvlIds = new ArrayList<Long>();
				 tdpCommitteeLvlIds.add(6l);//village: 
				 tdpCommitteeLvlIds.add(8l);//ward
				 tdpCommitteeLvlIds.add(5l);//mandal
				 tdpCommitteeLvlIds.add(7l);//town
				 tdpCommitteeLvlIds.add(9l);//division
				 List<Object[]> attendedList = trainingCampBatchAttendeeDAO.getTotalAttendeeCount(programIdList,1l,toDate,enrollmentYearIds,accessLevelValue,userAccessLevelValues,tdpCommitteeLvlIds);
			     List<Object[]> rtrnCommiteeLevelEligibleAndAttendedObjLst = trainingCampDetailsInfoDAO.getTrainingCampProgramEligibleAndAttendedMemberCommitteeLevelWise(accessLevelValue, userAccessLevelValues, toDate,enrollmentYearIds,programIdList);
				    TrainingCampProgramVO villageWardVO = new TrainingCampProgramVO();
					villageWardVO.setName("Village/Ward");
					setElibibleAndAttendedMemberCntToMap(rtrnCommiteeLevelEligibleAndAttendedObjLst,villageWardVO,"villageWard");
					setTotalAttendedAndNonInviteeAttended(attendedList,villageWardVO,"villageWard");
					villageWardVO.setTotalAttenedCountPer(calculatePercantage(villageWardVO.getTotalAttenedCount(),villageWardVO.getTotalEligibleCount()));
					villageWardVO.setTotalNotAttenedCountPer(calculatePercantage(villageWardVO.getTotalNotAttenedCount(), villageWardVO.getTotalEligibleCount()));
					finalResultVO.setVillageWardVO(villageWardVO);
			 
			       TrainingCampProgramVO manTwnDivVO = new TrainingCampProgramVO();
			 	   manTwnDivVO.setName("Mandal/Town/Division");
			 	   setElibibleAndAttendedMemberCntToMap(rtrnCommiteeLevelEligibleAndAttendedObjLst,manTwnDivVO,"mandalTwnDiv");
				   setTotalAttendedAndNonInviteeAttended(attendedList,manTwnDivVO,"mandalTwnDiv");
				   manTwnDivVO.setTotalAttenedCountPer(calculatePercantage(manTwnDivVO.getTotalAttenedCount(),manTwnDivVO.getTotalEligibleCount()));
				   manTwnDivVO.setTotalNotAttenedCountPer(calculatePercantage(manTwnDivVO.getTotalNotAttenedCount(), manTwnDivVO.getTotalEligibleCount()));
				  finalResultVO.setMandalTownDivisionVO(manTwnDivVO); 
				  
				  List<Object[]> rtrnObjLst = trainingCampDetailsInfoDAO.getTrainingCampProgramEligibleAndAttendedDetails(accessLevelValue, userAccessLevelValues, toDate,enrollmentYearIds,programIdList); 
					 if(rtrnObjLst != null && rtrnObjLst.size() > 0){
						 for (Object[] param : rtrnObjLst) {
							TrainingCampProgramVO programVO = new TrainingCampProgramVO();
							programVO.setId(commonMethodsUtilService.getLongValueForObject(param[0]));
							programVO.setName(commonMethodsUtilService.getStringValueForObject(param[1]));
							programVO.setTotalEligibleCount(commonMethodsUtilService.getLongValueForObject(param[2]));
							//programVO.setInviteeAttended(commonMethodsUtilService.getLongValueForObject(param[3]));
							programVO.setInviteeAttended(villageWardVO.getInviteeAttended()+manTwnDivVO.getInviteeAttended());
							programVO.setTotalAttenedCount(villageWardVO.getTotalAttenedCount()+manTwnDivVO.getTotalAttenedCount());
							//programVO.setTotalNotAttenedCount(commonMethodsUtilService.getLongValueForObject(param[4]));	
							programVO.setTotalNotAttenedCount(villageWardVO.getTotalNotAttenedCount()+manTwnDivVO.getTotalNotAttenedCount());
							programVO.setTotalAttenedCountPer(calculatePercantage(programVO.getTotalAttenedCount(),programVO.getTotalEligibleCount()));
							programVO.setTotalNotAttenedCountPer(calculatePercantage(programVO.getTotalNotAttenedCount(),programVO.getTotalEligibleCount()));
							//programVO.setNonInviteeAttended(programVO.getTotalAttenedCount()-programVO.getInviteeAttended());
							programVO.setNonInviteeAttended(villageWardVO.getNonInviteeAttended()+manTwnDivVO.getNonInviteeAttended());
							trainingCampProgramDtlsMap.put(programVO.getId(), programVO);
						}
					 }
		      if(trainingCampProgramDtlsMap != null && trainingCampProgramDtlsMap.size() > 0){
				  finalResultVO.setTrainingProgramList(new ArrayList<TrainingCampProgramVO>(trainingCampProgramDtlsMap.values()));
			  }
			 }catch(Exception e){
				 LOG.error("Error occured at getTotalEligibleAttendedAndNotAttenedOverviewCount() in CoreDashboardMainService {}",e);
			 }
			 return finalResultVO;*/
		   
		   TrainingCampProgramVO obj = getTrainingCampBasicDetailsCntOverviewDayWise(userAccessLevelId,userAccessLevelValues, stateId, toDateStr, enrollmentYearIds,programIdList);

			return obj;
		}
	public void setElibibleAndAttendedMemberCntToMap(List<Object[]> rtrnTtlAttendedMmbrObjList,TrainingCampProgramVO resultVO,String levelType){
	try{
		 if(rtrnTtlAttendedMmbrObjList != null && rtrnTtlAttendedMmbrObjList.size() > 0){
				for (Object[] param : rtrnTtlAttendedMmbrObjList) {
					Long levelId = commonMethodsUtilService.getLongValueForObject(param[0]);
					if(levelType.equalsIgnoreCase("villageWard")){
						if(levelId.longValue() == 6l || levelId.longValue() == 8l){
							resultVO.setTotalEligibleCount(resultVO.getTotalEligibleCount()+commonMethodsUtilService.getLongValueForObject(param[1]));
							//resultVO.setTotalAttenedCount(resultVO.getTotalAttenedCount()+commonMethodsUtilService.getLongValueForObject(param[2]));
							//resultVO.setTotalNotAttenedCount(resultVO.getTotalNotAttenedCount()+commonMethodsUtilService.getLongValueForObject(param[3]));
						}
					}else if(levelType.equalsIgnoreCase("mandalTwnDiv")){
						if(levelId.longValue() == 5l || levelId.longValue() == 7l || levelId.longValue() == 9l){
							resultVO.setTotalEligibleCount(resultVO.getTotalEligibleCount()+commonMethodsUtilService.getLongValueForObject(param[1]));
							//resultVO.setTotalAttenedCount(resultVO.getTotalAttenedCount()+commonMethodsUtilService.getLongValueForObject(param[2]));
							//resultVO.setTotalNotAttenedCount(resultVO.getTotalNotAttenedCount()+commonMethodsUtilService.getLongValueForObject(param[3]));
						}
					}
				}
			}
	}catch(Exception e){
		 LOG.error("Error occured at setElibibleAndAttendedMemberCntToMap() in CoreDashboardMainService {}",e); 
	}
	}
	/**
	* @param  Long userAccessLevelId
	* @param List<Long> userAccessLevelValues
	* @return List<TrainingCampProgramVO>
	* @param Long stateId
	* @author Santosh 
	* @Description :This Service Method is used to get training camp program details count by district wise. 
	*  @since 23-AUGUST-2016
	*/
	public List<TrainingCampProgramVO> getTrainingCampProgramsDetailsCntByDistrict(Long userAccessLevelId,List<Long> userAccessLevelValues,Long stateId,String toDateStr){
	String status = "leadership";
	List<TrainingCampProgramVO> resultList = new ArrayList<TrainingCampProgramVO>(0);
	Map<Long,List<TrainingCampProgramVO>> programDtlsMap = new HashMap<Long, List<TrainingCampProgramVO>>(0);
	Map<Long,String> programIdNameMap = new HashMap<Long, String>();
	 SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
	 Date toDate=null;
	 try{
		if(toDateStr != null && !toDateStr.isEmpty() && toDateStr.length() > 0){
			 toDate = sdf.parse(toDateStr);
		 }
		List<Object[]> rtrnElgbleMmbrsObjLst = tdpCommitteeMemberDAO.getTotalEligibleMembersForTrainingCampProgramByDistrict(userAccessLevelId, userAccessLevelValues,stateId,status, null); 
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
		List<Object[]> rtrnAttnddMemObjList = trainingCampAttendanceDAO.getTotalAttenedCadresOfTrainingCampProgramByDistrict(userAccessLevelId, userAccessLevelValues,stateId,toDate,status, null);
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
	* @param  Long userAccessLevelId
	* @param List<Long> userAccessLevelValues
	* @return List<TrainingCampProgramVO>
	* @param Long stateId
	* @author Santosh 
	* @Description :This Service Method is used to get training camp program details count by user type.. 
	*  @since 23-AUGUST-2016
	*/
	public List<TrainingCampProgramVO> getTrainingCampProgramsDetailsCntByUserType(Long userAccessLevelId,List<Long> userAccessLevelValues,Long stateId,String toDateStr,Long userTypeId,Long activityMemberId,List<Long> enrollmentYearIds,List<Long> trainingCampProgramIds){
		String status = "leadership";
		List<TrainingCampProgramVO> resultList = new ArrayList<TrainingCampProgramVO>(0);
		Map<Long,List<TrainingCampProgramVO>> programDtlsMap = new HashMap<Long, List<TrainingCampProgramVO>>(0);
		Map<Long,List<TrainingCampProgramVO>> twnDivDtlsMap = new HashMap<Long, List<TrainingCampProgramVO>>(0);
		Map<Long,String> programIdNameMap = new HashMap<Long, String>();
		Map<Long,String> twnDivisionProgramIdNameMap = new HashMap<Long, String>();
	    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		 Date toDate=null;
		 try{
			if(toDateStr != null && !toDateStr.isEmpty() && toDateStr.length() > 0){
				 toDate = sdf.parse(toDateStr);
			 }
			if(userTypeId != null && userTypeId.longValue()==IConstants.MLA_USER_TYPE_ID || userTypeId.longValue()==IConstants.CONSTITUENCY_USER_TYPE_ID || userTypeId.longValue()==IConstants.CONSTITUENCY_INCHARGE_USER_TYPE_ID){
				List<Object[]> rtrnMandalElgbleMmbrsObjLst = tdpCommitteeMemberDAO.getTotalEligibleMembersForTrainingCampProgramByUserType(userAccessLevelId, userAccessLevelValues, stateId, status, null, null, userTypeId, "tehsil",trainingCampProgramIds); 
				setEligibleDataToMap(rtrnMandalElgbleMmbrsObjLst,programDtlsMap,programIdNameMap,userTypeId,"Tehsil");
				List<Object[]> rtrnMandalAttnddMemObjList = trainingCampAttendanceDAO.getTotalAttenedCadresOfTrainingCampProgramByUserType(userAccessLevelId, userAccessLevelValues, stateId, toDate, status, null, null, userTypeId, "tehsil",enrollmentYearIds,trainingCampProgramIds);
				setAttendedDataToMap(rtrnMandalAttnddMemObjList,programDtlsMap,userTypeId,"Tehsil");
				List<Object[]> rtrnTwnDivElgbleMmbrsObjLst = tdpCommitteeMemberDAO.getTotalEligibleMembersForTrainingCampProgramByUserType(userAccessLevelId, userAccessLevelValues, stateId, status, null, null, userTypeId, "townDivision",trainingCampProgramIds); 
				setEligibleDataToMap(rtrnTwnDivElgbleMmbrsObjLst,twnDivDtlsMap,twnDivisionProgramIdNameMap,userTypeId,"townDivision");
				List<Object[]> rtrnTwnDivAttnddMemObjList = trainingCampAttendanceDAO.getTotalAttenedCadresOfTrainingCampProgramByUserType(userAccessLevelId, userAccessLevelValues, stateId, toDate, status, null, null, userTypeId, "townDivision",enrollmentYearIds,trainingCampProgramIds);
				setAttendedDataToMap(rtrnTwnDivAttnddMemObjList,twnDivDtlsMap,userTypeId,"townDivision");
			}else{
				List<Object[]> rtrnElgbleMmbrsObjLst = tdpCommitteeMemberDAO.getTotalEligibleMembersForTrainingCampProgramByUserType(userAccessLevelId, userAccessLevelValues, stateId, status, null, null, userTypeId,null,trainingCampProgramIds); 
				setEligibleDataToMap(rtrnElgbleMmbrsObjLst,programDtlsMap,programIdNameMap,userTypeId,null);
				List<Object[]> rtrnAttnddMemObjList = trainingCampAttendanceDAO.getTotalAttenedCadresOfTrainingCampProgramByUserType(userAccessLevelId, userAccessLevelValues, stateId, toDate, status, null, null, userTypeId, null,enrollmentYearIds,trainingCampProgramIds);
				setAttendedDataToMap(rtrnAttnddMemObjList,programDtlsMap,userTypeId,null);
			}
			
			 if(programDtlsMap != null && programDtlsMap.size() > 0){
				 for(Entry<Long, List<TrainingCampProgramVO>> entry:programDtlsMap.entrySet()){
					 TrainingCampProgramVO programVO = new TrainingCampProgramVO();
					        programVO.setId(entry.getKey());
					        programVO.setName(programIdNameMap.get(entry.getKey()));
					        programVO.getLocationList().addAll(entry.getValue());
					        if(twnDivDtlsMap != null && twnDivDtlsMap.size() >0){
					         programVO.getLocationList().addAll(twnDivDtlsMap.get(entry.getKey()));	 //town division details adding 
							 }
					        resultList.add(programVO);
				 }
			 }else{
				 if(twnDivDtlsMap != null && twnDivDtlsMap.size() > 0){ //
		               /* for town division scenario .there may be change in mandal level record is not there but
						in town division record is there*/
					 for(Entry<Long, List<TrainingCampProgramVO>> entry:twnDivDtlsMap.entrySet()){
						 TrainingCampProgramVO programVO = new TrainingCampProgramVO();
						        programVO.setId(entry.getKey());
						        programVO.setName(twnDivisionProgramIdNameMap.get(entry.getKey()));
						        programVO.getLocationList().addAll(entry.getValue());
						        resultList.add(programVO);
					 }
					}
			 }
		}catch(Exception e) {
			  LOG.error("Error occured at getTrainingCampProgramsDetailsCntDistrictWise() in CoreDashboardMainService {}",e);
		}
		return resultList;	 
		}
	
    public void setEligibleDataToMap(List<Object[]> rtrnElgbleMmbrsObjLst,Map<Long,List<TrainingCampProgramVO>> programDtlsMap,Map<Long,String> programIdNameMap,Long userTypeId,String status){
    	try{
    		 if(rtrnElgbleMmbrsObjLst != null && !rtrnElgbleMmbrsObjLst.isEmpty()){
				 for (Object[] param : rtrnElgbleMmbrsObjLst) {
					List<TrainingCampProgramVO> locatioList = programDtlsMap.get((Long)param[0]);
					 if(locatioList == null){
						 locatioList = new ArrayList<TrainingCampProgramVO>();
						 programIdNameMap.put((Long)param[0], param[1] != null ? param[1].toString(): "");
						 programDtlsMap.put((Long)param[0], locatioList);
					 }
					  TrainingCampProgramVO locatioVO = new TrainingCampProgramVO();
					   String strLocationId = param[2] != null ? param[2].toString():"0";
					   if(userTypeId.longValue()==IConstants.STATE_TYPE_USER_ID || userTypeId.longValue()==IConstants.COUNTRY_TYPE_USER_ID || userTypeId.longValue()==IConstants.GENERAL_SECRETARY_USER_TYPE_ID){
						   strLocationId="1"+strLocationId;
					   }else if(userTypeId != null && userTypeId.longValue()==IConstants.SECRETARY_USER_TYPE_ID || userTypeId.longValue()==IConstants.ORGANIZING_SECRETARY_USER_TYPE_ID || userTypeId.longValue()==IConstants.DISTRICT_PRESIDENT_USER_TYPE_ID
						|| userTypeId.longValue()==IConstants.MP_USER_TYPE_ID || userTypeId.longValue()==IConstants.INCHARGE_MINISTER_USER_TYPE_ID || userTypeId.longValue()==IConstants.PARLIAMENT_INCHARGE_USER_TYPE_ID){
						   strLocationId="2"+strLocationId;
					   }else if(userTypeId != null && userTypeId.longValue()==IConstants.MLA_USER_TYPE_ID || userTypeId.longValue()==IConstants.CONSTITUENCY_USER_TYPE_ID || userTypeId.longValue()==IConstants.CONSTITUENCY_INCHARGE_USER_TYPE_ID){
						   if(status.equalsIgnoreCase("Tehsil")){
							   strLocationId="3"+strLocationId;
						   }else if(status.equalsIgnoreCase("townDivision")){
							   strLocationId="4"+strLocationId; 
						   }
					   }
					   locatioVO.setId(Long.valueOf(strLocationId));
					   locatioVO.setName(param[3] != null ? param[3].toString() : "");
					   locatioVO.setTotalEligibleCount(param[4] != null ? (Long)param[4] : 0l);   
					   locatioList.add(locatioVO);
				}
			 }
    	}catch(Exception e){
    		  LOG.error("Error occured at setEligibleDataToMap() in CoreDashboardMainService {}",e);	
    	}
    }
    public void setAttendedDataToMap(List<Object[]> rtrnAttnddMemObjList,Map<Long,List<TrainingCampProgramVO>> programDtlsMap,Long userTypeId,String status){
    	
    	try{
    		 if(rtrnAttnddMemObjList != null && !rtrnAttnddMemObjList.isEmpty()){
				 for (Object[] param : rtrnAttnddMemObjList) {
					Long programId= (Long)param[0];
					 String strLocationId = param[2] != null ? param[2].toString():"0";
					   if(userTypeId.longValue()==IConstants.STATE_TYPE_USER_ID || userTypeId.longValue()==IConstants.COUNTRY_TYPE_USER_ID || userTypeId.longValue()==IConstants.GENERAL_SECRETARY_USER_TYPE_ID){
						   strLocationId="1"+strLocationId;
					   }else if(userTypeId != null && userTypeId.longValue()==IConstants.SECRETARY_USER_TYPE_ID || userTypeId.longValue()==IConstants.ORGANIZING_SECRETARY_USER_TYPE_ID || userTypeId.longValue()==IConstants.DISTRICT_PRESIDENT_USER_TYPE_ID
						   || userTypeId.longValue()==IConstants.MP_USER_TYPE_ID || userTypeId.longValue()==IConstants.INCHARGE_MINISTER_USER_TYPE_ID || userTypeId.longValue()==IConstants.PARLIAMENT_INCHARGE_USER_TYPE_ID){
						   strLocationId="2"+strLocationId;
					   }else if(userTypeId != null && userTypeId.longValue()==IConstants.MLA_USER_TYPE_ID || userTypeId.longValue()==IConstants.CONSTITUENCY_USER_TYPE_ID || userTypeId.longValue()==IConstants.CONSTITUENCY_INCHARGE_USER_TYPE_ID){
						   if(status.equalsIgnoreCase("Tehsil")){
							   strLocationId="3"+strLocationId;
						   }else if(status.equalsIgnoreCase("townDivision")){
							   strLocationId="4"+strLocationId; 
						   }
					   }
					   
					  Long locationId = Long.valueOf(strLocationId);
						
					Long attendedCnt = param[4] != null ? (Long)param[4]:0l;
					List<TrainingCampProgramVO> locationList = programDtlsMap.get(programId);
					if(locationList != null && !locationList.isEmpty()){
						 for (TrainingCampProgramVO districtVO : locationList) {
								if(districtVO.getId().equals(locationId)){
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
    	}catch(Exception e){
    		 LOG.error("Error occured at setAttendedDataToMap() in CoreDashboardMainService {}",e);	
    	}
    }
	
	/**
	* @param  Long userAccessLevelId
	* @param List<Long> userAccessLevelValues
	* @return List<TrainingCampProgramVO>
	* @param Long stateId
	* @author Santosh 
	* @Description :This Service Method is used to get training camp program details count by user type.. 
	*  @since 10-NOV-2016
	*/
/*	public List<TrainingCampProgramVO> getTrainingCampProgramsDetailsCntByUserType(Long userAccessLevelId,List<Long> userAccessLevelValues,Long stateId,String toDateStr,Long userTypeId,Long activityMemberId){
		List<TrainingCampProgramVO> resultList = new ArrayList<TrainingCampProgramVO>(0);
		Map<Long,List<TrainingCampProgramVO>> programDtlsMap = new HashMap<Long, List<TrainingCampProgramVO>>(0);
		Map<Long,String> programIdNameMap = new HashMap<Long, String>();
	    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		Date toDate=null;
		 try{
			if(toDateStr != null && !toDateStr.isEmpty() && toDateStr.length() > 0){
				 toDate = sdf.parse(toDateStr);
			 }
			 Long accessLevelValue =0l;	
		     if(userAccessLevelId.longValue() == 4l){// user level 4 means parliament constituency in the case of core dashboard
		    	 accessLevelValue = 10l; //region scope 10  means parliament constituency in intermediate table so that we are replacing value
		     }else if(userAccessLevelId.longValue()==5l){// user level 5 means constituency in the case of core dashboard
		    	 accessLevelValue = 4l;  //region scope 4  means constituency in intermediate table so that we are replacing value
		     }else{
		    	 accessLevelValue = userAccessLevelId;	 
		     }
			List<Object[]> rtrnObjList = trainingCampDetailsInfoDAO.getLocationWiseReportBasedOnUserType(accessLevelValue, userAccessLevelValues, stateId, userTypeId, activityMemberId);
			 setTrainingCampProgramDtlsToMap(rtrnObjList,programDtlsMap,programIdNameMap,userTypeId);
			 if(programDtlsMap != null && programDtlsMap.size() > 0){
				 for(Entry<Long, List<TrainingCampProgramVO>> entry:programDtlsMap.entrySet()){
					 TrainingCampProgramVO programVO = new TrainingCampProgramVO();
					        programVO.setId(entry.getKey());
					        programVO.setName(programIdNameMap.get(entry.getKey()));
					        programVO.getLocationList().addAll(entry.getValue());
					        resultList.add(programVO);
				 }
			 }
		}catch(Exception e) {
			  LOG.error("Error occured at getTrainingCampProgramsDetailsCntDistrictWise() in CoreDashboardMainService {}",e);
		}
		return resultList;	 
		}
	
  public void setTrainingCampProgramDtlsToMap(List<Object[]> objList,Map<Long,List<TrainingCampProgramVO>> programDtlsMap,Map<Long,String> programIdNameMap,Long userTypeId){
	  try{
		  if(objList != null && objList.size() > 0){
			  for(Object[] param:objList){
				  List<TrainingCampProgramVO> locationList = programDtlsMap.get(commonMethodsUtilService.getLongValueForObject(param[0]));
				   if(locationList == null ){
					   locationList = new ArrayList<TrainingCampProgramVO>();
					   programIdNameMap.put(commonMethodsUtilService.getLongValueForObject(param[0]),commonMethodsUtilService.getStringValueForObject(param[1]));
					   programDtlsMap.put(commonMethodsUtilService.getLongValueForObject(param[0]), locationList);
				   }
				   TrainingCampProgramVO locationVO = new TrainingCampProgramVO();
				   String strLocationId = param[2] != null ? param[2].toString():"0";
				   if(userTypeId.longValue()==IConstants.STATE_TYPE_USER_ID || userTypeId.longValue()==IConstants.COUNTRY_TYPE_USER_ID || userTypeId.longValue()==IConstants.GENERAL_SECRETARY_USER_TYPE_ID){
					  strLocationId="1"+strLocationId;
				   }else if(userTypeId != null && userTypeId.longValue()==IConstants.SECRETARY_USER_TYPE_ID || userTypeId.longValue()==IConstants.ORGANIZING_SECRETARY_USER_TYPE_ID || userTypeId.longValue()==IConstants.DISTRICT_PRESIDENT_USER_TYPE_ID
					  || userTypeId.longValue()==IConstants.MP_USER_TYPE_ID || userTypeId.longValue()==IConstants.MLA_USER_TYPE_ID || userTypeId.longValue()==IConstants.CONSTITUENCY_USER_TYPE_ID 
					  || userTypeId.longValue()==IConstants.CONSTITUENCY_INCHARGE_USER_TYPE_ID){
					   strLocationId="2"+strLocationId;
				   }
				   locationVO.setId(Long.valueOf(strLocationId));
				   locationVO.setName(commonMethodsUtilService.getStringValueForObject(param[3]));
				   locationVO.setTotalEligibleCount(commonMethodsUtilService.getLongValueForObject(param[4]));   
				   locationVO.setTotalAttenedCount(commonMethodsUtilService.getLongValueForObject(param[5]));
				   locationVO.setTotalNotAttenedCount(commonMethodsUtilService.getLongValueForObject(param[6]));	
				   locationVO.setTotalAttenedCountPer(calculatePercantage(locationVO.getTotalAttenedCount(), locationVO.getTotalEligibleCount()));
				   locationVO.setTotalNotAttenedCountPer(calculatePercantage(locationVO.getTotalNotAttenedCount(), locationVO.getTotalEligibleCount()));
				   locationList.add(locationVO);
			  }
		  }
	  }catch(Exception e){
		  LOG.error("Error occured at setTrainingCampProgramDtlsToMap() in CoreDashboardMainService {}",e);  
	  }
  } */
  
  /**
	* @param  Long userId
	* @param  Long userTypeId
	* @param  Long userAccessLevelId
	* @param List<Long> userAccessLevelValues
	* @param Long stateId
	* @return  List<List<UserTypeVO>>
	* @author Santosh 
	* @Description :This Service Method is used to get top5 strong or top5 poor members attended and eligible count.. 
	*  @since 24-AUGUST-2016
	*/
/*	public List<List<UserTypeVO>> getUserTypeWiseTotalEligibleAndAttendedCnt(Long userId,Long userTypeId,Long activityMembersId,Long userAccessLevelId,List<Long> userAccessLevelValues,Long stateId,String toDateStr){

	List<List<UserTypeVO>> resultList = new ArrayList<List<UserTypeVO>>(0);
	Map<String,Long> elibibleMemberCntMap = new HashMap<String, Long>(0);
	Map<String,Long> attendedMemberCntMap = new HashMap<String, Long>(0);
	Map<Long,Set<Long>> locationLevelMap = null;
	Map<Long,Map<Long,UserTypeVO>> userTypeMapDtls = null;
	SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
	Date toDate=null;
	try{
			if(toDateStr != null && !toDateStr.isEmpty() && toDateStr.length() > 0){
				 toDate = sdf.parse(toDateStr);
			 }
		     ActivityMemberVO activityMemberVO = new ActivityMemberVO();
		     activityMemberVO.setUserId(userId);
		     activityMemberVO.setActivityMemberId(activityMembersId);
		     activityMemberVO.setUserTypeId(userTypeId);
		     activityMemberVO = coreDashboardGenericService.getChildActivityMembersAndLocationsNew(activityMemberVO);//calling generic method.
		     userTypeMapDtls = activityMemberVO.getUserTypesMap();
		     locationLevelMap = activityMemberVO.getLocationLevelIdsMap();
		     
	if(locationLevelMap != null && locationLevelMap.size() > 0){
		  for(Entry<Long,Set<Long>> entry:locationLevelMap.entrySet()){
			List<Object[]> returnObjList = tdpCommitteeMemberDAO.getUserWiseTotalEligibleMembersForTrainingCampProgram(entry.getKey(),new ArrayList<Long>(entry.getValue()),stateId);
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
			List<Object[]> returnObjList = trainingCampAttendanceDAO.getUserWiseTotalAttenedCadresCntForTrainingProgram(entry.getKey(),new ArrayList<Long>(entry.getValue()),stateId,toDate);
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
	}; */
	
  
	/**
	* @param  Long userId
	* @param  Long userTypeId
	* @param  Long userAccessLevelId
	* @param List<Long> userAccessLevelValues
	* @param Long stateId
	* @return  List<List<UserTypeVO>>
	* @author Santosh 
	* @Description :This Service Method is used to get top5 strong or top5 poor members attended and eligible count..structure 
	*  @since 11-NOV-2016
	*/
	public List<List<UserTypeVO>> getUserTypeWiseTotalEligibleAndAttendedCnt(Long userId,Long userTypeId,Long activityMembersId,Long userAccessLevelId,List<Long> userAccessLevelValues,Long stateId,String toDateStr,List<Long> enrollmentYearIds,List<Long> programIdList){

	List<List<UserTypeVO>> resultList = new ArrayList<List<UserTypeVO>>(0);
	Map<String,TrainingCampProgramVO> eligibleAndAttendedCntMap = new HashMap<String, TrainingCampProgramVO>(0);
	Map<Long,Set<Long>> locationLevelMap = null;
	Map<Long,Map<Long,UserTypeVO>> userTypeMapDtls = null;
	SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
	Date toDate=null;
	try{
			if(toDateStr != null && !toDateStr.isEmpty() && toDateStr.length() > 0){
				 toDate = sdf.parse(toDateStr);
			 }
		     ActivityMemberVO activityMemberVO = new ActivityMemberVO();
		     activityMemberVO.setUserId(userId);
		     activityMemberVO.setActivityMemberId(activityMembersId);
		     activityMemberVO.setUserTypeId(userTypeId);
		     activityMemberVO = coreDashboardGenericService.getChildActivityMembersAndLocationsNew(activityMemberVO);//calling generic method.
		     userTypeMapDtls = activityMemberVO.getUserTypesMap();
		     locationLevelMap = activityMemberVO.getLocationLevelIdsMap();
		     
	if(locationLevelMap != null && locationLevelMap.size() > 0){
		  for(Entry<Long,Set<Long>> entry:locationLevelMap.entrySet()){
			    Long accessLevelValue =0l;	
			     if(entry.getKey().longValue() == 4l){// user level 4 means parliament constituency in the case of core dashboard
			    	 accessLevelValue = 10l; //region scope 10  means parliament constituency in intermediate table so that we are replacing value
			     }else if(entry.getKey().longValue()==5l){// user level 5 means constituency in the case of core dashboard
			    	 accessLevelValue = 4l;  //region scope 4  means constituency in intermediate table so that we are replacing value
			     }else{
			    	 accessLevelValue = entry.getKey();	 
			     }
			   List<Object[]> returnObjList = trainingCampDetailsInfoDAO.getTrainingCampProgramEligibleAndAttendedMemberLocationWise(accessLevelValue,new ArrayList<Long>(entry.getValue()), toDate,enrollmentYearIds,programIdList);
			   if(returnObjList != null && returnObjList.size() > 0){
				   for (Object[] param : returnObjList) {
					   String locationLevelAndId = entry.getKey()+"-"+param[0].toString();
					   TrainingCampProgramVO eligibleAndAttendedVO = new TrainingCampProgramVO();
					   eligibleAndAttendedVO.setTotalEligibleCount(commonMethodsUtilService.getLongValueForObject(param[1]));
					   eligibleAndAttendedVO.setTotalAttenedCount(commonMethodsUtilService.getLongValueForObject(param[2]));
					   eligibleAndAttendedVO.setTotalNotAttenedCount(commonMethodsUtilService.getLongValueForObject(param[3]));
					   eligibleAndAttendedCntMap.put(locationLevelAndId,eligibleAndAttendedVO);
				   }
			   }
		   }  
	}
	//Pushing requird data
	if(userTypeMapDtls != null && userTypeMapDtls.size() > 0){
		  for(Entry<Long, Map<Long, UserTypeVO>> entry : userTypeMapDtls.entrySet()){
		      Map<Long,UserTypeVO> userTypeMap = entry.getValue();
		      for(UserTypeVO vo:userTypeMap.values()){
		    	   for(Long locationValueId:vo.getLocationValuesSet()){
		    			 String key = vo.getLocationLevelId()+"-"+locationValueId;   
		    			 if(eligibleAndAttendedCntMap.get(key) != null){
		    				 vo.setTotalEligibleCount(vo.getTotalEligibleCount()+eligibleAndAttendedCntMap.get(key).getTotalEligibleCount()); 
		    				 vo.setTotalAttenedCount(vo.getTotalAttenedCount()+eligibleAndAttendedCntMap.get(key).getTotalAttenedCount()); 
		    	    		 vo.setTotalNotAttenedCount(vo.getTotalNotAttenedCount()+eligibleAndAttendedCntMap.get(key).getTotalNotAttenedCount());
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
	*  @param Long stateId
	* @return List<UserTypeVO>
	* @author Santosh 
	* @Description :This Service Method is used to get selected child member and for userType.. 
	*  @since 26-AUGUST-2016
	*/
/*	public List<UserTypeVO> getSelectedChildTypeMembersForTrainingProgram(Long parentActivityMemberId,List<Long> childUserTypeIds,Long locationLevelId,List<Long> locationLevelValues,String reportType,Long stateId,String toDateStr){

	List<UserTypeVO> resultList = new ArrayList<UserTypeVO>(0);
	Map<String,Long> elibibleMemberCntMap = new HashMap<String, Long>(0);
	Map<String,Long> attendedMemberCntMap = new HashMap<String, Long>(0);
	SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
	Date toDate=null;
	try{
		if(toDateStr != null && !toDateStr.isEmpty() && toDateStr.length() > 0){
			 toDate = sdf.parse(toDateStr);
		 }
		   //calling generic method to get childActivityMembers and there location level and values
		  ActivityMemberVO activityMemberVO=null;
		  Map<Long,UserTypeVO> childActivityMembersMap=null;
		  
		  
		  Map<Long,Set<Long>> locationLevelIdsMap=null;
		  Map<String,String>     nameForLocationMap=null;
		  if(reportType != null && reportType.equalsIgnoreCase("selectedUserType")){
			 // activityMemberVO= coreDashboardGenericService.getSelectedChildUserTypeMembers(parentActivityMemberId,childUserTypeId);
			  activityMemberVO = coreDashboardGenericService.getRequiredSubLevelActivityMembersDetails(parentActivityMemberId,childUserTypeIds);
			  childActivityMembersMap= activityMemberVO.getActivityMembersMap();
			  locationLevelIdsMap= activityMemberVO.getLocationLevelIdsMap();
		  }else if(reportType != null && reportType.equalsIgnoreCase("directChild")){
			  if(childUserTypeIds != null && childUserTypeIds.size()>0){
				   activityMemberVO = coreDashboardGenericService.getDirectChildActivityMemberCommitteeDetails(parentActivityMemberId,childUserTypeIds.get(0));//activityMemerId,userTypeId
			  }
			   childActivityMembersMap = activityMemberVO.getActivityMembersMap();
			   locationLevelIdsMap = activityMemberVO.getLocationLevelIdsMap();
		  }
		   
		  if(locationLevelIdsMap != null && locationLevelIdsMap.size() > 0){
			  nameForLocationMap = coreDashboardGenericService.getLocationNamesByLocationIds(locationLevelIdsMap);
		  }
	 	    if(locationLevelIdsMap != null && locationLevelIdsMap.size() > 0){
				  for(Entry<Long,Set<Long>> entry:locationLevelIdsMap.entrySet()){
					List<Object[]> returnObjList = tdpCommitteeMemberDAO.getUserWiseTotalEligibleMembersForTrainingCampProgram(entry.getKey(),new ArrayList<Long>(entry.getValue()),stateId);
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
					List<Object[]> returnObjList = trainingCampAttendanceDAO.getUserWiseTotalAttenedCadresCntForTrainingProgram(entry.getKey(),new ArrayList<Long>(entry.getValue()),stateId,toDate);
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
	}*/
	/**
	* @param  Long parentActivityMemberId
	* @param  Long childUserTypeId
	* @param  Long userAccessLevelId
	* @param List<Long> userAccessLevelValues
	* @param String reportType
	*  @param Long stateId 
	* @return List<UserTypeVO>
	* @author Santosh 
	* @Description :This Service Method is used to get selected child member and for userType.. 
	*  @since 10-NOV-2016
	*/
	public List<UserTypeVO> getSelectedChildTypeMembersForTrainingProgram(Long parentActivityMemberId,List<Long> childUserTypeIds,Long locationLevelId,List<Long> locationLevelValues,String reportType,Long stateId,String toDateStr,List<Long> enrollmentYearIds,List<Long> programIdsList){

	List<UserTypeVO> resultList = new ArrayList<UserTypeVO>(0);
	Map<String,TrainingCampProgramVO> eligibleAndAttendedCntMap = new HashMap<String, TrainingCampProgramVO>(0);  
	SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
	Date toDate=null;
	try{
		/*if(toDateStr != null && !toDateStr.isEmpty() && toDateStr.length() > 0){
			 toDate = sdf.parse(toDateStr);
		 }
		   //calling generic method to get childActivityMembers and there location level and values
		  ActivityMemberVO activityMemberVO=null;
		  Map<Long,UserTypeVO> childActivityMembersMap=null;
		  
		  
		  Map<Long,Set<Long>> locationLevelIdsMap=null;
		  Map<String,String>     nameForLocationMap=null;
		  if(reportType != null && reportType.equalsIgnoreCase("selectedUserType")){
			 // activityMemberVO= coreDashboardGenericService.getSelectedChildUserTypeMembers(parentActivityMemberId,childUserTypeId);
			  activityMemberVO = coreDashboardGenericService.getRequiredSubLevelActivityMembersDetails(parentActivityMemberId,childUserTypeIds);
			  childActivityMembersMap= activityMemberVO.getActivityMembersMap();
			  locationLevelIdsMap= activityMemberVO.getLocationLevelIdsMap();
		  }else if(reportType != null && reportType.equalsIgnoreCase("directChild")){
			  if(childUserTypeIds != null && childUserTypeIds.size()>0){
				   activityMemberVO = coreDashboardGenericService.getDirectChildActivityMemberCommitteeDetails(parentActivityMemberId,childUserTypeIds.get(0));//activityMemerId,userTypeId
			  }
			   childActivityMembersMap = activityMemberVO.getActivityMembersMap();
			   locationLevelIdsMap = activityMemberVO.getLocationLevelIdsMap();
		  }
		   
		  if(locationLevelIdsMap != null && locationLevelIdsMap.size() > 0){
			  nameForLocationMap = coreDashboardGenericService.getLocationNamesByLocationIds(locationLevelIdsMap);
		  }
		  	List<Long> inviteeRoles = new ArrayList<Long>();
			inviteeRoles.add(1l);
			inviteeRoles.add(2l);
			inviteeRoles.add(3l);
			
	 	    if(locationLevelIdsMap != null && locationLevelIdsMap.size() > 0){
				  for(Entry<Long,Set<Long>> entry:locationLevelIdsMap.entrySet()){
					    Long accessLevelValue =0l;	
					     if(entry.getKey().longValue() == 4l){// user level 4 means parliament constituency in the case of core dashboard
					    	 accessLevelValue = 10l; //region scope 10  means parliament constituency in intermediate table so that we are replacing value
					     }else if(entry.getKey().longValue()==5l){// user level 5 means constituency in the case of core dashboard
					    	 accessLevelValue = 4l;  //region scope 4  means constituency in intermediate table so that we are replacing value
					     }else{
					    	 accessLevelValue = entry.getKey();	 
					     }
					     List<Object[]> returnObjList = trainingCampDetailsInfoDAO.getTrainingCampProgramEligibleAndAttendedMemberLocationWise(accessLevelValue,new ArrayList<Long>(entry.getValue()), toDate,enrollmentYearIds,programIdsList);
						   if(returnObjList != null && returnObjList.size() > 0){
							   for (Object[] param : returnObjList) {
								   String locationLevelAndId = entry.getKey()+"-"+param[0].toString();
								   TrainingCampProgramVO eligibleAndAttendedVO = new TrainingCampProgramVO();
								   eligibleAndAttendedVO.setTotalEligibleCount(commonMethodsUtilService.getLongValueForObject(param[1]));
								  // eligibleAndAttendedVO.setTotalAttenedCount(commonMethodsUtilService.getLongValueForObject(param[2]));
								  // eligibleAndAttendedVO.setTotalNotAttenedCount(commonMethodsUtilService.getLongValueForObject(param[3]));
								   eligibleAndAttendedCntMap.put(locationLevelAndId,eligibleAndAttendedVO);
							}
						   }
						   List<Object[]> totalAttnd = trainingCampBatchAttendeeDAO.getTotalLocationWiseAttendeeCount(programIdsList,1l, toDate,enrollmentYearIds,accessLevelValue,new ArrayList<Long>(entry.getValue()));
						   if(totalAttnd != null && totalAttnd.size() > 0){
							   for (Object[] param : totalAttnd) {
								   String locationLevelAndId = entry.getKey()+"-"+param[0].toString();
								   TrainingCampProgramVO eligibleAndAttendedVO = eligibleAndAttendedCntMap.get(locationLevelAndId);
								   if(eligibleAndAttendedVO != null){
									  // eligibleAndAttendedVO.setTotalAttenedCount(commonMethodsUtilService.getLongValueForObject(param[1]));
									   
									   if(!inviteeRoles.contains(commonMethodsUtilService.getLongValueForObject(param[2]))){
											if(eligibleAndAttendedVO.getNonInviteeAttended() != null )
												eligibleAndAttendedVO.setNonInviteeAttended(eligibleAndAttendedVO.getNonInviteeAttended()+commonMethodsUtilService.getLongValueForObject(param[1]));
											else
												eligibleAndAttendedVO.setNonInviteeAttended(commonMethodsUtilService.getLongValueForObject(param[2]));
										}else{
											if(eligibleAndAttendedVO.getInviteeAttended() != null )
												eligibleAndAttendedVO.setInviteeAttended(eligibleAndAttendedVO.getInviteeAttended()+commonMethodsUtilService.getLongValueForObject(param[1]));
											else
												eligibleAndAttendedVO.setInviteeAttended(commonMethodsUtilService.getLongValueForObject(param[2]));
										}
									   eligibleAndAttendedVO.setTotalAttenedCount(eligibleAndAttendedVO.getInviteeAttended()+eligibleAndAttendedVO.getNonInviteeAttended());
								   }
							   }
						   }
				   }  
			}  
		  //Pushing Required Count
		 	  if(childActivityMembersMap != null && childActivityMembersMap.size() > 0){
			      for(UserTypeVO vo:childActivityMembersMap.values()){
			    	   for(Long locationValueId:vo.getLocationValuesSet()){
			    		   String key = vo.getLocationLevelId()+"-"+locationValueId;   
			    			 if(eligibleAndAttendedCntMap.get(key) != null){
			    				 TrainingCampProgramVO eligibleAndAttendedVO = eligibleAndAttendedCntMap.get(key);
			    				 eligibleAndAttendedVO.setTotalNotAttenedCount(eligibleAndAttendedVO.getTotalEligibleCount()-eligibleAndAttendedVO.getInviteeAttended());
			    				 
			    				 vo.setTotalEligibleCount(vo.getTotalEligibleCount()+eligibleAndAttendedVO.getTotalEligibleCount()); 
			    				 vo.setInviteeAttendedCnt(vo.getInviteeAttendedCnt()+eligibleAndAttendedVO.getInviteeAttended()); 
			    				 vo.setNonInviteeAttendedCnt(vo.getNonInviteeAttendedCnt()+eligibleAndAttendedVO.getNonInviteeAttended());
			    				 vo.setTotalAttenedCount(vo.getInviteeAttendedCnt()+vo.getNonInviteeAttendedCnt());
			    	    		 vo.setTotalNotAttenedCount(vo.getTotalNotAttenedCount()+eligibleAndAttendedVO.getTotalNotAttenedCount());
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
			    	  //vo.getInviteeAttendedCnt() means who are presidents, vice-presidents and General Secretery only eligible people for trainings
			    	 	 vo.setTotalAttenedCountPer(calculatePercantage(vo.getInviteeAttendedCnt(),vo.getTotalEligibleCount()));  
			    	 	 vo.setTotalNotAttenedCountPer(calculatePercantage(vo.getTotalNotAttenedCount(),vo.getTotalEligibleCount()));	
			      }
			}
		  if(childActivityMembersMap != null && childActivityMembersMap.size() > 0){
			  resultList.addAll(childActivityMembersMap.values());
		  }
		  if(resultList != null && resultList.size() > 0)
		  {
			  Collections.sort(resultList, trainingMemberEligibleAttendedPercDesc);
		  }*/
		 resultList =getSelectedChildTypeMembersForTrainingProgramForDayWise( parentActivityMemberId, childUserTypeIds, locationLevelId, locationLevelValues, reportType, stateId, toDateStr, enrollmentYearIds, programIdsList);
	}catch(Exception e){
		LOG.error("Error occured at getSelectedChildTypeMembersForTrainingProgram() in CoreDashboardMainService ",e);
	}
	return resultList;	
	}
	/**
	* @param  Long userAccessLevelId
	* @param List<Long> userAccessLevelValues
	* @param Long stateId
	* @return  TrainingCampProgramVO
	* @author Santosh 
	* @Description :This Service Method is used to get top5 poor Training Camp Program locations attended counts. 
	*  @since 29-AUGUST-2016
	*/
  public TrainingCampProgramVO getTrainingProgramPoorCompletedLocationDtls(Long userTypeId,Long activityMemberId,Long stateId,String toDateStr,List<Long> enrollmentYearIds,List<Long> programIdList){
	  
	  TrainingCampProgramVO resultVO = new TrainingCampProgramVO();
	  Map<Long,TrainingCampProgramVO> eligibleMembersMap = new HashMap<Long, TrainingCampProgramVO>();
	  Map<Long,Long> attendedMembersMap = new HashMap<Long, Long>();
	  Map<Long,Set<Long>> locationMap = new HashMap<Long, Set<Long>>();
	  SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
	  Date toDate=null;
	  try{
		  if(toDateStr != null && !toDateStr.isEmpty() && toDateStr.length() > 0){
			  toDate = sdf.parse(toDateStr);
			 }
		  
		  SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd");
			String fromDate = sdf1.format(toDate);
			DateUtilService dateUtilService = new DateUtilService();
	        Date currentDate = dateUtilService.getCurrentDateAndTime();
	        String toDay = sdf1.format(currentDate);
	        
	        String programIds = getListToString(programIdList);
	        String enrollmentYrs = getListToString(enrollmentYearIds);
		  
	        List<Object[]> rtrnUserAccessLevelIdAndValuesObjList=activityMemberAccessLevelDAO.getLocationLevelAndValuesByActivityMembersId(activityMemberId);
		   if(rtrnUserAccessLevelIdAndValuesObjList != null && rtrnUserAccessLevelIdAndValuesObjList.size() > 0){
			   for (Object[] obj : rtrnUserAccessLevelIdAndValuesObjList) {
				  Set<Long> locationValueSet= locationMap.get((Long)obj[0]);
				    if(locationValueSet == null){
				    	locationValueSet = new java.util.HashSet<Long>();
				    	locationMap.put((Long)obj[0],locationValueSet);
				    }
				     locationValueSet.add(obj[1] != null ? (Long)obj[1]:0l);
			}
		   }
		  
		   List<Object[]>  trainingCampObj=trainingCampBatchDAO.getTraingCampBatchDetaisByDatesAndProgramIdsAndEnroleMentIds(null,null,enrollmentYearIds,programIdList);
			List<Long> batchIdsList=new ArrayList<Long>();// adding all batchIds to list
			if(trainingCampObj != null && trainingCampObj.size() >0){
			 	for(Object[] param:trainingCampObj){
			 		Long batchId=commonMethodsUtilService.getLongValueForObject(param[0]);
		  	        batchIdsList.add(batchId);
				}
			}
	   if(userTypeId != null && userTypeId.longValue()==IConstants.GENERAL_SECRETARY_USER_TYPE_ID){
		   if(locationMap != null && locationMap.size() > 0){
			   for(Entry<Long,Set<Long>> entry:locationMap.entrySet()){
				   String levelVals = getListToString(new ArrayList<Long>(entry.getValue()));
				  // List<Object[]> rtrnDistAttendedObj = trainingCampAttendanceDAO.getTotalAttenedCadresOfTrainingCampProgramByLocationType(entry.getKey(),new ArrayList<Long>(entry.getValue()),"District",stateId,toDate,enrollmentYearIds,programIdList); // userAccessLevelId & values   
				  // List<Object[]> rtrnDistAttendedObj = trainingCampBatchAttendeeDAO.getDayWiseTrainingCampDetailsCount(programIds,fromDate,toDay,enrollmentYrs,1L,"5,6,7,8,9",entry.getKey(),levelVals);
				   List<Object[]> rtrnDistAttendedObj = getCampDetailsListByFiltering(enrollmentYearIds,programIdList,batchIdsList);
				   List<Object[]> rtrnDistEligibleObj =  tdpCommitteeMemberDAO.getTotalEligibleMembersForTrainingCampProgramByLocationType(entry.getKey(),new ArrayList<Long>(entry.getValue()), "District",stateId);// userAccessLevelId & values 	 
				  // setAttendedMembersCntToMap(rtrnDistAttendedObj,attendedMembersMap);
				   Map<Long, Map<Long, Long>> batchMemdaysMap = getBatchWiseAttendedCountMap( rtrnDistAttendedObj);
				   Map<Long, Long> memMaxDayCnt = getCountOfMaxDaysAttendedInBatch(batchMemdaysMap, rtrnDistAttendedObj);// Tdp CAdre max days Vo getting
				   setEligibleMemberCntToMapWithDayWise(rtrnDistEligibleObj,attendedMembersMap,eligibleMembersMap,rtrnDistAttendedObj,"District",memMaxDayCnt);
				   if(eligibleMembersMap != null && eligibleMembersMap.size()>0){
					 resultVO.setDistrictList(new ArrayList<TrainingCampProgramVO>(eligibleMembersMap.values()));
				   }
				    eligibleMembersMap.clear();
				    attendedMembersMap.clear(); 
			   }
		   }
	   }
	   if(userTypeId != null && userTypeId.longValue()==IConstants.GENERAL_SECRETARY_USER_TYPE_ID  || userTypeId.longValue()==IConstants.ORGANIZING_SECRETARY_USER_TYPE_ID || userTypeId.longValue() ==IConstants.SECRETARY_USER_TYPE_ID
	   || userTypeId.longValue() ==IConstants.DISTRICT_PRESIDENT_USER_TYPE_ID || userTypeId.longValue()==IConstants.MP_USER_TYPE_ID || userTypeId.longValue()==IConstants.INCHARGE_MINISTER_USER_TYPE_ID || userTypeId.longValue()==IConstants.PARLIAMENT_INCHARGE_USER_TYPE_ID){
		if(locationMap != null && locationMap.size() > 0){
			for(Entry<Long,Set<Long>> entry:locationMap.entrySet()){
				String levelVals = getListToString(new ArrayList<Long>(entry.getValue()));
				 // List<Object[]> rtrnConsAttendedObj = trainingCampAttendanceDAO.getTotalAttenedCadresOfTrainingCampProgramByLocationType(entry.getKey(), new ArrayList<Long>(entry.getValue()),"Constituency",stateId,toDate,enrollmentYearIds,programIdList);   
				//List<Object[]> rtrnConsAttendedObj = trainingCampBatchAttendeeDAO.getDayWiseTrainingCampDetailsCount(programIds,fromDate,toDay,enrollmentYrs,1L,"5,6,7,8,9",entry.getKey(),levelVals);
				List<Object[]> rtrnConsAttendedObj = getCampDetailsListByFiltering(enrollmentYearIds,programIdList,batchIdsList);
				List<Object[]> rtrnConsEligibleObj =  tdpCommitteeMemberDAO.getTotalEligibleMembersForTrainingCampProgramByLocationType(entry.getKey(), new ArrayList<Long>(entry.getValue()), "Constituency",stateId); 	 
				 Map<Long, Map<Long, Long>> batchMemdaysMap = getBatchWiseAttendedCountMap( rtrnConsAttendedObj);
				 Map<Long, Long> memMaxDayCnt = getCountOfMaxDaysAttendedInBatch(batchMemdaysMap, rtrnConsAttendedObj);// Tdp CAdre max days Vo getting 
				//setAttendedMembersCntToMap(rtrnConsAttendedObj,attendedMembersMap);
				setEligibleMemberCntToMapWithDayWise(rtrnConsEligibleObj,attendedMembersMap,eligibleMembersMap,rtrnConsAttendedObj,"Constituency",memMaxDayCnt);
				   if(eligibleMembersMap != null && eligibleMembersMap.size()>0){
						 resultVO.setConstituencyList(new ArrayList<TrainingCampProgramVO>(eligibleMembersMap.values()));
						 eligibleMembersMap.clear();
						 attendedMembersMap.clear(); 
				   } 
			}
		}
	   }
	   if(userTypeId != null && userTypeId.longValue()==IConstants.CONSTITUENCY_USER_TYPE_ID  || userTypeId.longValue()==IConstants.ORGANIZING_SECRETARY_USER_TYPE_ID || userTypeId.longValue() ==IConstants.SECRETARY_USER_TYPE_ID
			   || userTypeId.longValue() ==IConstants.DISTRICT_PRESIDENT_USER_TYPE_ID || userTypeId.longValue()==IConstants.MLA_USER_TYPE_ID || userTypeId.longValue()==IConstants.MP_USER_TYPE_ID ||
			   userTypeId.longValue()==IConstants.CONSTITUENCY_INCHARGE_USER_TYPE_ID || userTypeId.longValue()==IConstants.INCHARGE_MINISTER_USER_TYPE_ID || userTypeId.longValue()==IConstants.PARLIAMENT_INCHARGE_USER_TYPE_ID){
		   if(locationMap != null && locationMap.size() > 0){
			   for(Entry<Long,Set<Long>> entry:locationMap.entrySet()){
				  // List<Object[]> rtrnConsAttendedObj = trainingCampAttendanceDAO.getTotalAttenedCadresOfTrainingCampProgramByLocationType(entry.getKey(),new ArrayList<Long>(entry.getValue()),"Mandal",stateId,toDate,enrollmentYearIds,programIdList);   
				   String levelVals = getListToString(new ArrayList<Long>(entry.getValue()));
				  // List<Object[]> rtrnConsAttendedObj = trainingCampBatchAttendeeDAO.getDayWiseTrainingCampDetailsCount(programIds,fromDate,toDay,enrollmentYrs,1L,"5,6,7,8,9",entry.getKey(),levelVals);
				   List<Object[]> rtrnConsAttendedObj = getCampDetailsListByFiltering(enrollmentYearIds,programIdList,batchIdsList);
				   List<Object[]> rtrnConsEligibleObj =  tdpCommitteeMemberDAO.getTotalEligibleMembersForTrainingCampProgramByLocationType(entry.getKey(),new ArrayList<Long>(entry.getValue()), "Mandal",stateId); 	 
				  // setAttendedMembersCntToMap(rtrnConsAttendedObj,attendedMembersMap);
				   Map<Long, Map<Long, Long>> batchMemdaysMap = getBatchWiseAttendedCountMap( rtrnConsAttendedObj);
				   Map<Long, Long> memMaxDayCnt = getCountOfMaxDaysAttendedInBatch(batchMemdaysMap, rtrnConsAttendedObj);// Tdp CAdre max days Vo getting 
				   setEligibleMemberCntToMapWithDayWise(rtrnConsEligibleObj,attendedMembersMap,eligibleMembersMap,rtrnConsAttendedObj,"Mandal",memMaxDayCnt);
				   if(eligibleMembersMap != null && eligibleMembersMap.size()>0){
						 resultVO.getMandalList().addAll(new ArrayList<TrainingCampProgramVO>(eligibleMembersMap.values()));
						 eligibleMembersMap.clear();
						 attendedMembersMap.clear(); 
				   } 
				   //List<Object[]> rtrnTwnDivsnAttendedObjList = trainingCampAttendanceDAO.getTotalAttenedCadresOfTrainingCampProgramByLocationType(entry.getKey(),new ArrayList<Long>(entry.getValue()),"TownDivision",stateId,toDate,enrollmentYearIds,programIdList);   
				   
				   List<Object[]> rtrnTwnDivsnEligibleObjList =  tdpCommitteeMemberDAO.getTotalEligibleMembersForTrainingCampProgramByLocationType(entry.getKey(),new ArrayList<Long>(entry.getValue()), "TownDivision",stateId); 	 
				  // setAttendedMembersCntToMap(rtrnTwnDivsnAttendedObjList,attendedMembersMap);
				   setEligibleMemberCntToMapWithDayWise(rtrnTwnDivsnEligibleObjList,attendedMembersMap,eligibleMembersMap,rtrnConsAttendedObj,"TownDivision",memMaxDayCnt);
				   if(eligibleMembersMap != null && eligibleMembersMap.size()>0){
					   List<TrainingCampProgramVO> townDivList = new ArrayList<TrainingCampProgramVO>(eligibleMembersMap.values());
						 resultVO.getMandalList().addAll(townDivList); // merging town division data
						 eligibleMembersMap.clear();
						 attendedMembersMap.clear(); 
				   } 
			   }
		   }
		}
	   if(userTypeId != null && userTypeId.longValue()==IConstants.CONSTITUENCY_USER_TYPE_ID  || userTypeId.longValue()==IConstants.CONSTITUENCY_INCHARGE_USER_TYPE_ID || userTypeId.longValue()==IConstants.MLA_USER_TYPE_ID){
		   if(locationMap != null && locationMap.size() > 0){
			   for(Entry<Long,Set<Long>> entry:locationMap.entrySet()){
				  // List<Object[]> rtrnConsAttendedObj = trainingCampAttendanceDAO.getTotalAttenedCadresOfTrainingCampProgramByLocationType(entry.getKey(),new ArrayList<Long>(entry.getValue()),"Village",stateId,toDate,enrollmentYearIds,programIdList);   
				   String levelVals = getListToString(new ArrayList<Long>(entry.getValue()));
				  // List<Object[]> rtrnConsAttendedObj = trainingCampBatchAttendeeDAO.getDayWiseTrainingCampDetailsCount(programIds,fromDate,toDay,enrollmentYrs,1L,"5,6,7,8,9",entry.getKey(),levelVals);
				   List<Object[]> rtrnConsAttendedObj = getCampDetailsListByFiltering(enrollmentYearIds,programIdList,batchIdsList);
				   List<Object[]> rtrnConsEligibleObj =  tdpCommitteeMemberDAO.getTotalEligibleMembersForTrainingCampProgramByLocationType(entry.getKey(),new ArrayList<Long>(entry.getValue()), "Village",stateId); 	 
				  // setAttendedMembersCntToMap(rtrnConsAttendedObj,attendedMembersMap);
				   Map<Long, Map<Long, Long>> batchMemdaysMap = getBatchWiseAttendedCountMap( rtrnConsAttendedObj);
				   Map<Long, Long> memMaxDayCnt = getCountOfMaxDaysAttendedInBatch(batchMemdaysMap, rtrnConsAttendedObj);// Tdp CAdre max days Vo getting
				   setEligibleMemberCntToMapWithDayWise(rtrnConsEligibleObj,attendedMembersMap,eligibleMembersMap,rtrnConsAttendedObj,"Village",memMaxDayCnt);
				   if(eligibleMembersMap != null && eligibleMembersMap.size()>0){
						 resultVO.getVillageList().addAll(new ArrayList<TrainingCampProgramVO>(eligibleMembersMap.values()));
						 eligibleMembersMap.clear();
						 attendedMembersMap.clear(); 
				  }   
				   //List<Object[]> rtrnWardAttendedObj = trainingCampAttendanceDAO.getTotalAttenedCadresOfTrainingCampProgramByLocationType(entry.getKey(),new ArrayList<Long>(entry.getValue()),"Ward",stateId,toDate,enrollmentYearIds,programIdList);   
				   List<Object[]> rtrnWardEligibleObj =  tdpCommitteeMemberDAO.getTotalEligibleMembersForTrainingCampProgramByLocationType(entry.getKey(),new ArrayList<Long>(entry.getValue()), "Ward",stateId); 	 
				  // setAttendedMembersCntToMap(rtrnWardAttendedObj,attendedMembersMap);
				   setEligibleMemberCntToMapWithDayWise(rtrnWardEligibleObj,attendedMembersMap,eligibleMembersMap,rtrnConsAttendedObj,"Ward",memMaxDayCnt);
				   if(eligibleMembersMap != null && eligibleMembersMap.size()>0){
					   List<TrainingCampProgramVO> wardList = new ArrayList<TrainingCampProgramVO>(eligibleMembersMap.values());
						 resultVO.getVillageList().addAll(wardList); //merging ward data
						 eligibleMembersMap.clear();
						 attendedMembersMap.clear(); 
				  }   
			   }
		   }
		}
	   if(resultVO != null){
		   if(resultVO.getDistrictList() != null && resultVO.getDistrictList().size()> 0){
            Collections.sort(resultVO.getDistrictList(),trainingMemberEligibleAttendedPercasc);
		   }
		   if(resultVO.getConstituencyList() != null && resultVO.getConstituencyList().size() > 0){
			Collections.sort(resultVO.getConstituencyList(),trainingMemberEligibleAttendedPercasc);   
		   }
		   if(resultVO.getMandalList()!= null && resultVO.getMandalList().size()> 0){
	            Collections.sort(resultVO.getMandalList(),trainingMemberEligibleAttendedPercasc);
		   }
		   if(resultVO.getVillageList()!= null && resultVO.getVillageList().size() > 0){
			 Collections.sort(resultVO.getVillageList(),trainingMemberEligibleAttendedPercasc);   
		   }
	   }
	  }catch(Exception e) {
		LOG.error("Error occured at getTrainingProgramPoorCompletedLocationDtls in CoreDashboardMainService ",e);
	 }
	 return resultVO;
  }
  public Map<Long, Map<Long, Long>> getBatchWiseAttendedCountMap(List<Object[]> attendedList){
	  Map<Long, Map<Long, Long>> batchMemdaysMap = new HashMap<Long, Map<Long, Long>>();
	  try{
		  if (attendedList != null && attendedList.size() > 0) {
				for (Object[] param : attendedList) {
					Map<Long, Long> memDaysMap = batchMemdaysMap.get(commonMethodsUtilService.getLongValueForObject(param[2]));
					if (memDaysMap == null) {
						memDaysMap = new HashMap<Long, Long>(0);
						memDaysMap.put(commonMethodsUtilService.getLongValueForObject(param[0]), 1l);
						batchMemdaysMap.put(commonMethodsUtilService.getLongValueForObject(param[2]), memDaysMap);
					}
					Long attendedDaysforBatch = memDaysMap.get(commonMethodsUtilService.getLongValueForObject(param[0]));
					if (attendedDaysforBatch == null) {
						memDaysMap.put(commonMethodsUtilService.getLongValueForObject(param[0]), 1l);
					} else {
						memDaysMap.put(commonMethodsUtilService.getLongValueForObject(param[0]),attendedDaysforBatch + 1l);
					}

				}
			}
			
	  }catch (Exception e) {
		  LOG.error("Error occured at setAttendedMembersCntToMap in CoreDashboardMainService ",e);
	}
	  return batchMemdaysMap;
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
  public void setEligibleMemberCntToMap(List<Object[]> rtrnDistEligibleObj,Map<Long,Long> attendedMembersMap,Map<Long,TrainingCampProgramVO> eligibleMembersMap,List<Object[]> rtrnDistAttendedObj){
	 try{
		 
		 List<Long> inviteeRoles = new ArrayList<Long>();
			inviteeRoles.add(1l);
			inviteeRoles.add(2l);
			inviteeRoles.add(3l);
			
		 if(commonMethodsUtilService.isListOrSetValid(rtrnDistAttendedObj)){
			 for (Object[] obj : rtrnDistAttendedObj) {
				  TrainingCampProgramVO vo = new TrainingCampProgramVO();
				  vo.setId((Long)obj[0]);
				  vo.setName(obj[1] != null ? obj[1].toString():"");
				  if(inviteeRoles.contains(commonMethodsUtilService.getLongValueForObject(obj[3]))){
					  if(vo.getInviteeAttended() != null)
						  vo.setInviteeAttended(vo.getInviteeAttended()+commonMethodsUtilService.getLongValueForObject(obj[2]));
					  else
						  vo.setInviteeAttended(commonMethodsUtilService.getLongValueForObject(obj[2]));
				  }else{
					  if(vo.getNonInviteeAttended() != null)
						  vo.setNonInviteeAttended(vo.getNonInviteeAttended()+commonMethodsUtilService.getLongValueForObject(obj[2]));
					  else
						  vo.setNonInviteeAttended(commonMethodsUtilService.getLongValueForObject(obj[2]));
				  }
				  eligibleMembersMap.put(vo.getId(),vo);
			}
		 }
		 
		 if(rtrnDistEligibleObj != null && rtrnDistEligibleObj.size() > 0){
			 for(Object[] obj : rtrnDistEligibleObj) {
				TrainingCampProgramVO vo =   eligibleMembersMap.get(commonMethodsUtilService.getLongValueForObject(obj[0]));
				if(vo == null)
					vo = new TrainingCampProgramVO();
				
				  vo.setId((Long)obj[0]);
				  vo.setName(obj[1] != null ? obj[1].toString():"");
				  vo.setTotalEligibleCount(obj[2] != null ? (Long)obj[2]:0l);
				  vo.setTotalAttenedCountPer(calculatePercantage(vo.getInviteeAttended(),vo.getTotalEligibleCount()));
				  if(vo.getTotalEligibleCount() > 0){
					  vo.setTotalNotAttenedCount(vo.getTotalEligibleCount()-vo.getInviteeAttended());  
				  }
				  vo.setTotalNotAttenedCountPer(calculatePercantage(vo.getTotalNotAttenedCount(),vo.getTotalEligibleCount()));
				  eligibleMembersMap.put(vo.getId(),vo);
			}
		 }
	 }catch(Exception e) {
			LOG.error("Error occured at setEligibleMemberCntToMap in CoreDashboardMainService ",e);
	}
  }
  
  public void setEligibleMemberCntToMapWithDayWise(List<Object[]> rtrnDistEligibleObj,Map<Long,Long> attendedMembersMap,Map<Long,TrainingCampProgramVO> eligibleMembersMap,
		  List<Object[]> rtrnDistAttendedObj,String locationType,Map<Long,Long> memMaxDayCnt){
		 try{
			 
			 List<Long> mandalTownDivisionInviteeRoles = new ArrayList<Long>();
			  	mandalTownDivisionInviteeRoles.add(1l);
				mandalTownDivisionInviteeRoles.add(2l);
				mandalTownDivisionInviteeRoles.add(3l);

				List<Long> villageWardInviteeRoles = new ArrayList<Long>();
				villageWardInviteeRoles.add(1l);
				villageWardInviteeRoles.add(3l);
				Long inviteeAttendeForLoc = 0l;
				Long nonInvitAttForLoc = 0l;
				Map<Long,Set<Long>> locCadreIdsMap = new HashMap<Long,Set<Long>>();
			 if(commonMethodsUtilService.isListOrSetValid(rtrnDistAttendedObj)){
				 for (Object[] obj : rtrnDistAttendedObj) {
					 Long locId = 0l;
					 String locName = "";
					 if(locationType.equalsIgnoreCase("District")){
						 locId = commonMethodsUtilService.getLongValueForObject(obj[11]);
						 locName = commonMethodsUtilService.getStringValueForObject(obj[12]);
					 }else if(locationType.equalsIgnoreCase("Constituency")){
						 locId = commonMethodsUtilService.getLongValueForObject(obj[15]);
						 locName = commonMethodsUtilService.getStringValueForObject(obj[16]);
					 }else if(locationType.equalsIgnoreCase("Mandal")){
						 locId = commonMethodsUtilService.getLongValueForObject(obj[17]);
						 locName = commonMethodsUtilService.getStringValueForObject(obj[18]);
					 }else if(locationType.equalsIgnoreCase("TownDivision")){
						 locId = commonMethodsUtilService.getLongValueForObject(obj[19]);
						 locName = commonMethodsUtilService.getStringValueForObject(obj[20]);
					 }else if(locationType.equalsIgnoreCase("Village")){
						 locId = commonMethodsUtilService.getLongValueForObject(obj[21]);
						 locName = commonMethodsUtilService.getStringValueForObject(obj[22]);
					 }else if(locationType.equalsIgnoreCase("Ward")){
						 locId = commonMethodsUtilService.getLongValueForObject(obj[23]);
						 locName = commonMethodsUtilService.getStringValueForObject(obj[24]);
					 }
					 TrainingCampProgramVO vo = eligibleMembersMap.get(locId);
					 if(vo == null){
							vo = new TrainingCampProgramVO();
						    vo.getLocationList().addAll(getDaysList());
						    vo.setId(locId);
							vo.setName(locName);
							eligibleMembersMap.put(vo.getId(),vo);
					 }
					 
					 Set<Long> cadreIds = locCadreIdsMap.get(locId);
					 if(cadreIds == null ){
						 cadreIds = new HashSet<Long>();
						 locCadreIdsMap.put(locId, cadreIds);
					 }
					 cadreIds.add(commonMethodsUtilService.getLongValueForObject(obj[0]));
					 Long levelId = commonMethodsUtilService.getLongValueForObject(obj[4]);
					 if(cadreIds.contains(commonMethodsUtilService.getLongValueForObject(obj[0]))){
						 Long maxDayForAtt = memMaxDayCnt.get(commonMethodsUtilService.getLongValueForObject(obj[0]));
						 
						 TrainingCampProgramVO dayVO = getMatchVOList1(vo.getLocationList(), maxDayForAtt);
						 if(dayVO != null){
							 if(levelId.longValue()==0L && (levelId != 5L && levelId != 7L && levelId != 9L && levelId != 6L && levelId != 8L)){
									dayVO.getOthersIds().add(commonMethodsUtilService.getLongValueForObject(obj[0]));
								}else if((levelId.longValue() == 5L || levelId.longValue() == 7L || levelId.longValue() == 9L) && mandalTownDivisionInviteeRoles.contains(commonMethodsUtilService.getLongValueForObject(obj[3]))){
									dayVO.getInviteesIds().add(commonMethodsUtilService.getLongValueForObject(obj[0]));
								}else if((levelId.longValue() == 6L || levelId.longValue() == 8L) && villageWardInviteeRoles.contains(commonMethodsUtilService.getLongValueForObject(obj[3]))){
									dayVO.getInviteesIds().add(commonMethodsUtilService.getLongValueForObject(obj[0]));
								}else{
									dayVO.getNonInviteesIds().add(commonMethodsUtilService.getLongValueForObject(obj[0]));
								}
						 }
					 }
					  
					  /*if(inviteeRoles.contains(commonMethodsUtilService.getLongValueForObject(obj[3]))){
						  if(vo.getInviteeAttended() != null)
							  vo.setInviteeAttended(vo.getInviteeAttended()+commonMethodsUtilService.getLongValueForObject(obj[2]));
						  else
							  vo.setInviteeAttended(commonMethodsUtilService.getLongValueForObject(obj[2]));
					  }else{
						  if(vo.getNonInviteeAttended() != null)
							  vo.setNonInviteeAttended(vo.getNonInviteeAttended()+commonMethodsUtilService.getLongValueForObject(obj[2]));
						  else
							  vo.setNonInviteeAttended(commonMethodsUtilService.getLongValueForObject(obj[2]));
					  }*/
				}
			 }
			 
			 if(commonMethodsUtilService.isMapValid(eligibleMembersMap)){
		 		  for(TrainingCampProgramVO eligibleAndAttendedVO:eligibleMembersMap.values()){
						for(TrainingCampProgramVO dayVO :eligibleAndAttendedVO.getLocationList()){
							dayVO.setInviteeAttended(Long.valueOf(String.valueOf(dayVO.getInviteesIds().size())));
							dayVO.setNonInviteeAttended(Long.valueOf(String.valueOf(dayVO.getNonInviteesIds().size())));
							dayVO.setOthersCount(Long.valueOf(String.valueOf(dayVO.getOthersIds().size())));
							dayVO.setTotalAttenedCount(dayVO.getInviteeAttended()+dayVO.getNonInviteeAttended());
							eligibleAndAttendedVO.setInviteeAttended(eligibleAndAttendedVO.getInviteeAttended()+dayVO.getInviteeAttended());
							eligibleAndAttendedVO.setNonInviteeAttended(eligibleAndAttendedVO.getNonInviteeAttended()+dayVO.getNonInviteeAttended());
							dayVO.getInviteesIds().clear();dayVO.getNonInviteesIds().clear();dayVO.getOthersIds().clear();
						}
					}
				}
			 if(rtrnDistEligibleObj != null && rtrnDistEligibleObj.size() > 0){
				 for(Object[] obj : rtrnDistEligibleObj) {
					TrainingCampProgramVO vo =   eligibleMembersMap.get(commonMethodsUtilService.getLongValueForObject(obj[0]));
					if(vo == null){
						vo = new TrainingCampProgramVO();
						 vo.getLocationList().addAll(getDaysList());
						 vo.setId((Long)obj[0]);
						 vo.setName(obj[1] != null ? obj[1].toString():"");
						 eligibleMembersMap.put(vo.getId(),vo);
					}
					
					vo.setTotalEligibleCount(obj[2] != null ? (Long)obj[2]:0l);
					  vo.setTotalAttenedCountPer(calculatePercantage(vo.getInviteeAttended(),vo.getTotalEligibleCount()));
					  if(vo.getTotalEligibleCount() > 0){
						  vo.setTotalNotAttenedCount(vo.getTotalEligibleCount()-vo.getInviteeAttended());  
					  }
					  vo.setTotalNotAttenedCountPer(calculatePercantage(vo.getTotalNotAttenedCount(),vo.getTotalEligibleCount()));
					  for(TrainingCampProgramVO dayVO :vo.getLocationList()){
						  dayVO.setTotalAttenedCountPer(calculatePercantage(dayVO.getInviteeAttended(),vo.getTotalEligibleCount()));
						  if(vo.getTotalEligibleCount() > 0){
							  dayVO.setTotalNotAttenedCount(vo.getTotalEligibleCount()-dayVO.getInviteeAttended());  
						  }
						  dayVO.setTotalNotAttenedCountPer(calculatePercantage(dayVO.getTotalNotAttenedCount(),vo.getTotalEligibleCount()));
					 } 
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
	/**
	* @param  Long userAccessLevelId
	* @param List<Long> userAccessLevelValues
	* @param Long stateId
	* @return  TrainingCampProgramVO
	* @author Santosh 
	* @Description :This Service Method is used to get top5 poor Training Camp Program locations attended counts. 
	*  @since 10-NOV-2016
	*/
/*  public TrainingCampProgramVO getTrainingProgramPoorCompletedLocationDtls(Long userTypeId,Long activityMemberId,Long stateId,String toDateStr){
	  
	  TrainingCampProgramVO resultVO = new TrainingCampProgramVO();
	  Map<Long,TrainingCampProgramVO> trainingCampDtlsMap = new HashMap<Long, TrainingCampProgramVO>();
	  Map<Long,Set<Long>> locationMap = new HashMap<Long, Set<Long>>();
	  SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
	  Date toDate=null;
	  try{
		  if(toDateStr != null && !toDateStr.isEmpty() && toDateStr.length() > 0){
				 toDate = sdf.parse(toDateStr);
			 }
		  List<Object[]> rtrnUserAccessLevelIdAndValuesObjList=activityMemberAccessLevelDAO.getLocationLevelAndValuesByActivityMembersId(activityMemberId);
		   if(rtrnUserAccessLevelIdAndValuesObjList != null && rtrnUserAccessLevelIdAndValuesObjList.size() > 0){
			   for (Object[] obj : rtrnUserAccessLevelIdAndValuesObjList) {
				  Set<Long> locationValueSet= locationMap.get((Long)obj[0]);
				    if(locationValueSet == null){
				    	locationValueSet = new java.util.HashSet<Long>();
				    	locationMap.put((Long)obj[0],locationValueSet);
				    }
				     locationValueSet.add(obj[1] != null ? (Long)obj[1]:0l);
			}
		   }
		  
	   if(userTypeId != null && userTypeId.longValue()==IConstants.GENERAL_SECRETARY_USER_TYPE_ID){
		   if(locationMap != null && locationMap.size() > 0){
			   for(Entry<Long,Set<Long>> entry:locationMap.entrySet()){
				   Long accessLevelValue =0l;	
				     if(entry.getKey().longValue() == 4l){// user level 4 means parliament constituency in the case of core dashboard
				    	 accessLevelValue = 10l; //region scope 10  means parliament constituency in intermediate table so that we are replacing value
				     }else if(entry.getKey().longValue()==5l){// user level 5 means constituency in the case of core dashboard
				    	 accessLevelValue = 4l;  //region scope 4  means constituency in intermediate table so that we are replacing value
				     }else{
				    	 accessLevelValue = entry.getKey();	 
				     }
				   List<Object[]> retrunObjList = trainingCampDetailsInfoDAO.getTrainingCampEligibleAndAttendedCntByLocationType(accessLevelValue, new ArrayList<Long>(entry.getValue()), stateId, userTypeId, activityMemberId, "District");   
				   setTrainingCampDtlsToMap(retrunObjList,trainingCampDtlsMap);
				   resultVO.setDistrictList(new ArrayList<TrainingCampProgramVO>(trainingCampDtlsMap.values()));
				   trainingCampDtlsMap.clear();
			   }
		   }
	   }
	   if(userTypeId != null && userTypeId.longValue()==IConstants.GENERAL_SECRETARY_USER_TYPE_ID  || userTypeId.longValue()==IConstants.ORGANIZING_SECRETARY_USER_TYPE_ID || userTypeId.longValue() ==IConstants.SECRETARY_USER_TYPE_ID
	   || userTypeId.longValue() ==IConstants.DISTRICT_PRESIDENT_USER_TYPE_ID || userTypeId.longValue()==IConstants.MP_USER_TYPE_ID || userTypeId.longValue()==IConstants.CONSTITUENCY_USER_TYPE_ID 
	   || userTypeId.longValue()==IConstants.CONSTITUENCY_INCHARGE_USER_TYPE_ID || userTypeId.longValue()==IConstants.MLA_USER_TYPE_ID){
		if(locationMap != null && locationMap.size() > 0){
			for(Entry<Long,Set<Long>> entry:locationMap.entrySet()){
						 Long accessLevelValue =0l;	
					     if(entry.getKey().longValue() == 4l){// user level 4 means parliament constituency in the case of core dashboard
					    	 accessLevelValue = 10l; //region scope 10  means parliament constituency in intermediate table so that we are replacing value
					     }else if(entry.getKey().longValue()==5l){// user level 5 means constituency in the case of core dashboard
					    	 accessLevelValue = 4l;  //region scope 4  means constituency in intermediate table so that we are replacing value
					     }else{
					    	 accessLevelValue = entry.getKey();	 
					     }
					     List<Object[]> retrunObjList = trainingCampDetailsInfoDAO.getTrainingCampEligibleAndAttendedCntByLocationType(accessLevelValue, new ArrayList<Long>(entry.getValue()), stateId, userTypeId, activityMemberId, "Constituency");   
					     setTrainingCampDtlsToMap(retrunObjList,trainingCampDtlsMap);
					     resultVO.setConstituencyList(new ArrayList<TrainingCampProgramVO>(trainingCampDtlsMap.values()));
					     trainingCampDtlsMap.clear();
			}
		}
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
  public void setTrainingCampDtlsToMap(List<Object[]> objList,Map<Long,TrainingCampProgramVO> trainingCampDtlsMap){
	  try{
		  if(objList != null && objList.size() > 0){
			  for(Object[] param:objList){
				  TrainingCampProgramVO locationVO = new TrainingCampProgramVO();
				  locationVO.setId(commonMethodsUtilService.getLongValueForObject(param[0]));
				  locationVO.setName(commonMethodsUtilService.getStringValueForObject(param[1]));
				  locationVO.setTotalEligibleCount(commonMethodsUtilService.getLongValueForObject(param[2]));
				  locationVO.setTotalAttenedCount(commonMethodsUtilService.getLongValueForObject(param[3]));  
				  locationVO.setTotalNotAttenedCount(commonMethodsUtilService.getLongValueForObject(param[4]));  
				  locationVO.setTotalNotAttenedCountPer(calculatePercantage(locationVO.getTotalNotAttenedCount(),locationVO.getTotalEligibleCount()));
				  locationVO.setTotalAttenedCountPer(calculatePercantage(locationVO.getTotalAttenedCount(),locationVO.getTotalEligibleCount()));
				  trainingCampDtlsMap.put(locationVO.getId(), locationVO);
		  }
		  }
	  }catch(Exception e){
		  LOG.error("Error occured at setTrainingCampDtlsToMap in CoreDashboardMainService ",e); 
	  }
  }
	public static Comparator<TrainingCampProgramVO> trainingMemberEligibleAttendedPercasc = new Comparator<TrainingCampProgramVO>() {
	public int compare(TrainingCampProgramVO member2, TrainingCampProgramVO member1) {
	Double perc2 = member2.getTotalAttenedCountPer();
	Double perc1 = member1.getTotalAttenedCountPer();
	//ascending order of percantages.
	 return perc2.compareTo(perc1);
	}
	}; */
	/**
	* @param  Long userAccessLevelId
	* @param List<Long> userAccessLevelValues
	* @param Long stateId
	* @return  List<TrainingCampProgramVO>
	* @author Santosh 
	* @Description :This Service Method get basic training program attended and eligible count. 
	*  @since 29-AUGUST-2016
	*/
/* public List<TrainingCampProgramVO> getTrainingCampProgramsBasicCountDetails(Long userAccessLevelId,List<Long> userAccessLevelValues,Long stateId,String toDateStr){
		 
		 List<TrainingCampProgramVO> resultList = new ArrayList<TrainingCampProgramVO>(0);
		 Map<Long,Long> trainingCampProgramAttendedCntMap = new HashMap<Long, Long>();
		 Map<Long,TrainingCampProgramVO> trainingCampProgramEligibleCntMap = new HashMap<Long, TrainingCampProgramVO>();
		 SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		 Date toDate=null;
		 try{
		   if(toDateStr != null && !toDateStr.isEmpty() && toDateStr.length() > 0){
				 toDate = sdf.parse(toDateStr);
			 }
			 List<Object[]> rtrnElgbleMemberForProgramObjList = tdpCommitteeMemberDAO.getTotalEligibleMembersForTrainingCampProgram(userAccessLevelId, userAccessLevelValues,stateId);
			 List<Object[]> rtrnAttendedMemberForProgramObjList = trainingCampAttendanceDAO.getTotalAttenedCadresByTrainingCampProgram(userAccessLevelId, userAccessLevelValues,stateId,toDate);
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
					if(programVO.getTotalEligibleCount() > 0){
						programVO.setTotalNotAttenedCount(programVO.getTotalEligibleCount()-programVO.getTotalAttenedCount());	
					}
					programVO.setTotalNotAttenedCountPer(calculatePercantage(programVO.getTotalNotAttenedCount(),programVO.getTotalEligibleCount()));
					trainingCampProgramEligibleCntMap.put(programId, programVO);
				}
			 } 
			  if(trainingCampProgramEligibleCntMap != null && trainingCampProgramEligibleCntMap.size() > 0){
				  resultList.addAll(new ArrayList<TrainingCampProgramVO>(trainingCampProgramEligibleCntMap.values()));
			  }
		 }catch(Exception e){
			 LOG.error("Error occured at getTrainingCampProgramsDetails() in CoreDashboardService1 {}",e); 
		 }
		 return resultList;
	 } */
	/**
	* @param  Long userAccessLevelId
	* @param List<Long> userAccessLevelValues
	* @param Long stateId
	* @return  List<TrainingCampProgramVO>
	* @author Santosh 
	* @Description :This Service Method get basic training program attended and eligible count. 
	*  @since 10-NOV-2016
	*/
public List<TrainingCampProgramVO> getTrainingCampProgramsBasicCountDetails(Long userAccessLevelId,List<Long> userAccessLevelValues,Long stateId,String toDateStr,List<Long> enrollmentYearIds){
		 
		 List<TrainingCampProgramVO> resultList = new ArrayList<TrainingCampProgramVO>(0);
		 Map<Long,TrainingCampProgramVO> trainingCampProgramDtlsMap = new HashMap<Long, TrainingCampProgramVO>();
		 SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		 Date toDate=null;
		 try{
		   if(toDateStr != null && !toDateStr.isEmpty() && toDateStr.length() > 0){
				 toDate = sdf.parse(toDateStr);
			 }
		     Long accessLevelValue =0l;	
		     if(userAccessLevelId.longValue() == 4l){// user level 4 means parliament constituency in the case of core dashboard
		    	 accessLevelValue = 10l; //region scope 10  means parliament constituency in intermediate table so that we are replacing value
		     }else if(userAccessLevelId.longValue()==5l){// user level 5 means constituency in the case of core dashboard
		    	 accessLevelValue = 4l;  //region scope 4  means constituency in intermediate table so that we are replacing value
		     }else{
		    	 accessLevelValue = userAccessLevelId;	 
		     }
		     List<Object[]> rtrnObjLst = trainingCampDetailsInfoDAO.getTrainingCampProgramEligibleAndAttendedDetails(accessLevelValue, userAccessLevelValues, toDate,enrollmentYearIds,null); 
			 if(rtrnObjLst != null && rtrnObjLst.size() > 0){
				 for (Object[] param : rtrnObjLst) {
					TrainingCampProgramVO programVO = new TrainingCampProgramVO();
					programVO.setId(commonMethodsUtilService.getLongValueForObject(param[0]));
					programVO.setName(commonMethodsUtilService.getStringValueForObject(param[1]));
					programVO.setTotalEligibleCount(commonMethodsUtilService.getLongValueForObject(param[2]));
					programVO.setTotalAttenedCount(commonMethodsUtilService.getLongValueForObject(param[3]));
					programVO.setTotalNotAttenedCount(commonMethodsUtilService.getLongValueForObject(param[4]));	
					programVO.setTotalAttenedCountPer(calculatePercantage(programVO.getTotalAttenedCount(),programVO.getTotalEligibleCount()));
					programVO.setTotalNotAttenedCountPer(calculatePercantage(programVO.getTotalNotAttenedCount(),programVO.getTotalEligibleCount()));
					trainingCampProgramDtlsMap.put(programVO.getId(), programVO);
				}
			 }
			  if(trainingCampProgramDtlsMap != null && trainingCampProgramDtlsMap.size() > 0){
				  resultList.addAll(new ArrayList<TrainingCampProgramVO>(trainingCampProgramDtlsMap.values()));
			  }
		 }catch(Exception e){
			 LOG.error("Error occured at getTrainingCampProgramsDetails() in CoreDashboardService1 {}",e); 
		 }
		 return resultList;
	 }


  public String getTrainingCampRecentTime(){
			  String lastUpdatedTimeStr="";
			  SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm a ");
			  try{
				  Date lastUpdatedTime = trainingCampAttendanceDAO.getLastUpdatedTime();
				  if(lastUpdatedTime != null){
					  lastUpdatedTimeStr= sdf.format(lastUpdatedTime);
				  }
			  }catch(Exception e){
				  LOG.error("Error occured at getTrainingCampRecentTime() in CoreDashboardMainService {}",e);  
			  }
			  return lastUpdatedTimeStr;
   }
/*  Debate Service*/



public List<CoreDebateVO> getPartyWiseTotalDebateDetails(String startDateStr,String endDateStr,String state,List<Long> debateLocationIdList,List<Long> debateParticipantLocationIdList){		
	List<CoreDebateVO> returnList = new ArrayList<CoreDebateVO>();		
	try{
		Date startDate = null;
		Date endDate   =null;
		
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		if(startDateStr !=null && !startDateStr.trim().isEmpty() && endDateStr !=null && !endDateStr.trim().isEmpty()){
			startDate = sdf.parse(startDateStr);
			endDate = sdf.parse(endDateStr);
		}
		
		Map<Long,CoreDebateVO> countMap = new LinkedHashMap<Long, CoreDebateVO>();
		
		List<Object[]> debateCountObjList = null;
		List<Object[]> scaleCountObjList = null;
		List<Object[]> debateParticipantList =null;
		if(debateLocationIdList.contains(0L)){
		  debateLocationIdList.remove(0L);
		}
	    if(debateLocationIdList != null && debateLocationIdList.size() == 1 && debateLocationIdList.contains(2l)){
	    	 debateCountObjList = debateParticipantDAO.getPartyWiseDebateOtherDetails(startDate,endDate,state,debateLocationIdList);
	    	 scaleCountObjList = debateParticipantCharcsDAO.getPartyWiseScalesOfOthersEachCharecter(startDate,endDate,state,debateLocationIdList);
	    	 debateParticipantList= debateParticipantDAO.getPartyWiseDebateParticipantOtherDetails(startDate,endDate,state,debateLocationIdList,debateParticipantLocationIdList);
	    }else if(debateLocationIdList !=null && debateLocationIdList.size() > 1L && !debateLocationIdList.contains(2L)){
	    	debateCountObjList = debateParticipantDAO.getPartyWiseDebateDetails(startDate,endDate,state,debateLocationIdList);
	    	scaleCountObjList = debateParticipantCharcsDAO.getPartyWiseScalesOfEachCharecter(startDate,endDate,state,debateLocationIdList);
	    	debateParticipantList = debateParticipantDAO.getPartyWiseDebateParticipantDetails(startDate,endDate,state,debateLocationIdList,debateParticipantLocationIdList);
	    }else if(debateLocationIdList !=null && debateLocationIdList.size() == 2L && debateLocationIdList.contains(2L)){
	    	List<CoreDebateVO> finalList =	getPartyWiseCombineTwoStatesDebateDetails(startDateStr,endDateStr,state,debateLocationIdList,debateParticipantLocationIdList,returnList);
	    	return finalList;
	    }else if(debateLocationIdList !=null && debateLocationIdList.size() ==3l){ // 1,2
	    	
	    	if((debateLocationIdList.contains(2L)) || (debateParticipantLocationIdList.contains(2L))){
		    	  debateCountObjList = debateParticipantDAO.getPartyWiseDebateOtherDetails(startDate,endDate,state,debateLocationIdList);
		    	  scaleCountObjList = debateParticipantCharcsDAO.getPartyWiseScalesOfOthersEachCharecter(startDate,endDate,state,debateLocationIdList);
		    	  //debateParticipantList= debateParticipantDAO.getPartyWiseDebateParticipantOtherDetails(startDate,endDate,state,debateLocationIdList,debateParticipantLocationIdList);
		      }
		      
		       debateLocationIdList.remove(2L);
		      List<Object[]> subDebateCountObjList = debateParticipantDAO.getPartyWiseDebateDetails(startDate,endDate,state,debateLocationIdList);
		      List<Object[]> subScaleCountObjList = debateParticipantCharcsDAO.getPartyWiseScalesOfEachCharecter(startDate,endDate,state,debateLocationIdList);
		       debateLocationIdList.clear();
		       debateParticipantLocationIdList.clear();
		      List<Object[]> subDebateParticipantList = debateParticipantDAO.getPartyWiseDebateParticipantDetails(startDate,endDate,state,debateLocationIdList,debateParticipantLocationIdList);
		      
		      if((debateCountObjList !=null && subDebateCountObjList !=null) || (scaleCountObjList !=null && subScaleCountObjList !=null) || (subDebateParticipantList != null && debateParticipantList != null) ){
		    	  debateCountObjList.addAll(subDebateCountObjList);
		    	  scaleCountObjList.addAll(subScaleCountObjList);
		    	  debateParticipantList = subDebateParticipantList;
		    	  //debateParticipantList.addAll(subDebateParticipantList);
		      }else{
		    	  debateCountObjList = subDebateCountObjList;
		    	  scaleCountObjList = subScaleCountObjList;
		    	  debateParticipantList = subDebateParticipantList;
		      }
		    
	    	
	    	/*//debateLocationIdList.clear();
	    	//debateParticipantLocationIdList.clear();
	    	debateCountObjList = debateParticipantDAO.getPartyWiseDebateDetails(startDate,endDate,state,debateLocationIdList);
	    	scaleCountObjList = debateParticipantCharcsDAO.getPartyWiseScalesOfEachCharecter(startDate,endDate,state,debateLocationIdList);
	    	debateParticipantList = debateParticipantDAO.getPartyWiseDebateParticipantDetails(startDate,endDate,state,debateLocationIdList,debateParticipantLocationIdList);*/
		    }
		//0.partyId,1.shortName,2.debateCount,3.candidateCount
		//List<Object[]> debateCountObjList = debateParticipantDAO.getPartyWiseDebateDetails(startDate,endDate,state,debateLocationIdList);	
	    
		//0.partyId,1.shortName,2.characteristicsId,3.name,4.scale
		//List<Object[]> scaleCountObjList = debateParticipantCharcsDAO.getPartyWiseScalesOfEachCharecter(startDate,endDate,state,debateLocationIdList);
	 List<Characteristics> charecters = characteristicsDAO.getCharacteristicsDetails();
		if(debateCountObjList !=null && debateCountObjList.size()>0 ){
		if(commonMethodsUtilService.isListOrSetValid(debateCountObjList)){			
			//countMap = setDebateValuesToMap(debateCountObjList,countMap,"debates");
			countMap= setDebateAndDebateParticipantValuesToMap(debateCountObjList,countMap,"debates");
		}
		}
		if(debateParticipantList !=null && debateParticipantList.size()>0 ){
			if(commonMethodsUtilService.isListOrSetValid(debateParticipantList)){			
				//countMap = setDebateValuesToMap(debateParticipantList,partyMap,"debatesParticipants");
				countMap = setDebateAndDebateParticipantValuesToMap(debateParticipantList,countMap,"debatesParticipants");
			}
			}
		if(scaleCountObjList !=null && scaleCountObjList.size()>0 ){
		if(commonMethodsUtilService.isListOrSetValid(scaleCountObjList)){
			countMap = setScaleVauesToParty(scaleCountObjList,countMap);
		}
		}
		
		if(countMap !=null && countMap.size()>0){				
			returnList = new ArrayList<CoreDebateVO>(countMap.values());				
		}
		
		if(commonMethodsUtilService.isListOrSetValid(returnList)){
			for (CoreDebateVO objects : returnList) {
				
				if(objects.getCandidateCount() !=null && objects.getDebateCount() !=null && objects.getCandidateCount() > objects.getDebateCount()){
					objects.setScalePerc(Double.parseDouble(new BigDecimal((objects.getScale())/objects.getCandidateCount()).setScale(2, BigDecimal.ROUND_HALF_UP).toString()));
				}									
				else if(objects.getCandidateCount() !=null && objects.getDebateCount() !=null && objects.getDebateCount() > objects.getCandidateCount() ){
					objects.setScalePerc(Double.parseDouble(new BigDecimal((objects.getScale())/objects.getDebateCount()).setScale(2, BigDecimal.ROUND_HALF_UP).toString()));
				}else if(objects.getDebateCount() !=null && objects.getDebateCount()>0l){
					objects.setScalePerc(Double.parseDouble(new BigDecimal((objects.getScale())/objects.getDebateCount()).setScale(2, BigDecimal.ROUND_HALF_UP).toString()));
				}
				
				if(objects.getScalePerc() !=null && objects.getScalePerc() >0.0 ){
					objects.setScalePerc(Double.parseDouble(new BigDecimal(objects.getScalePerc()/charecters.size()).setScale(1, BigDecimal.ROUND_HALF_UP).toString()));
				}					
			}
		}
		
		if(returnList !=null && returnList.size()>0){
			CoreDebateVO VO= returnList.get(0);
			VO.setRecentTime(getLatestDebate());
		}
					
	}catch (Exception e) {
		LOG.error("Exception raised at getPartyWiseTotalDebateDetails() method of CoreDashboardMainService", e);
	}
	
	return returnList;
	
}

public Map<Long,CoreDebateVO> setDebateValuesToMap(List<Object[]> ObjList,Map<Long,CoreDebateVO> countMap){
	try{			
		if(commonMethodsUtilService.isListOrSetValid(ObjList)){			
			for (Object[] obj : ObjList) {
				
				CoreDebateVO coreDebateVO  = countMap.get((Long)obj[0]);
				
				if(coreDebateVO == null){
					 coreDebateVO = new CoreDebateVO();
					 coreDebateVO.setId((Long)obj[0]);
					 coreDebateVO.setName(commonMethodsUtilService.getStringValueForObject(obj[1]));
					 
					 countMap.put((Long)obj[0], coreDebateVO);	
				}			
					
				coreDebateVO.setDebateCount(coreDebateVO.getDebateCount() + commonMethodsUtilService.getLongValueForObject(obj[2]));
				coreDebateVO.setCandidateCount(coreDebateVO.getCandidateCount() + commonMethodsUtilService.getLongValueForObject(obj[3]));						
													
			}
		}
		
	}catch (Exception e) {
		LOG.error("Exception raised at setDebateValuesToMap() method of CoreDashboardMainService", e);
	}
	return countMap;
} 

public Map<Long,CoreDebateVO> setDebateAndDebateParticipantValuesToMap(List<Object[]> ObjList,Map<Long,CoreDebateVO> countMap,String type){
	try{
		CoreDebateVO coreDebateVO =null;
		if(commonMethodsUtilService.isListOrSetValid(ObjList)){			
			for (Object[] obj : ObjList) {
				  coreDebateVO  = countMap.get((Long)obj[0]);
				if(coreDebateVO == null){
					 coreDebateVO = new CoreDebateVO();
					 coreDebateVO.setId((Long)obj[0]);
					 coreDebateVO.setName(commonMethodsUtilService.getStringValueForObject(obj[1]));
					 
					 countMap.put((Long)obj[0], coreDebateVO);	
				}			
				if(type != null && type.equalsIgnoreCase("debates"))	
				  coreDebateVO.setDebateCount(coreDebateVO.getDebateCount() + commonMethodsUtilService.getLongValueForObject(obj[2]));
				if(type != null && type.equalsIgnoreCase("debatesParticipants"))
				  coreDebateVO.setCandidateCount(coreDebateVO.getCandidateCount() + commonMethodsUtilService.getLongValueForObject(obj[3]));						
													
			}
		}
		
	}catch (Exception e) {
		LOG.error("Exception raised at setDebateValuesToMap() method of CoreDashboardMainService", e);
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
		LOG.error("Exception raised at setScaleVauesToParty() method of CoreDashboardMainService", e);
	}
	return countMap;
}	
public List<CoreDebateVO> getSpokesPersonWiseDebate(String startDateStr,String endDateStr,String searchType,String state,List<Long> debateParticipantLocationIdList,List<Long> debateLocationIdList){

	List<CoreDebateVO> returnList = new ArrayList<CoreDebateVO>();
	
	try{			
		Date startDate = null;
		Date endDate   =null;
		
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		if(startDateStr !=null && !startDateStr.trim().isEmpty() && endDateStr !=null && !endDateStr.trim().isEmpty()){
			startDate = sdf.parse(startDateStr);
			endDate = sdf.parse(endDateStr);
		}
		
		Map<Long,Map<Long,CoreDebateVO>> partyMap = new LinkedHashMap<Long, Map<Long,CoreDebateVO>>();
		List<Object[]> candidateObjList =null;
		List<Object[]> debateCountsList =null;
		if(debateLocationIdList != null && debateLocationIdList.size() == 1 && debateLocationIdList.contains(2l)){
			candidateObjList = debateParticipantCharcsDAO.getPartywiseCandidateOthersScaling(startDate,endDate,searchType,state,debateParticipantLocationIdList,debateLocationIdList);
			debateCountsList = debateParticipantDAO.getTotalDabtesCountsForEachCandidateOthersNew(startDate, endDate,state,debateParticipantLocationIdList,debateLocationIdList);
	    }else if(debateLocationIdList !=null && debateLocationIdList.size() == 1L && !debateLocationIdList.contains(2L)){
	    	candidateObjList = debateParticipantCharcsDAO.getPartywiseCandidateScaling(startDate,endDate,searchType,state,debateParticipantLocationIdList,debateLocationIdList);
			debateCountsList = debateParticipantDAO.getTotalDabtesCountsForEachCandidateNew(startDate, endDate,state,debateParticipantLocationIdList,debateLocationIdList);
	    }else if(debateLocationIdList !=null && debateLocationIdList.size()>1){ // 1,2
	      if(debateLocationIdList.contains(2L)){
	    	  candidateObjList = debateParticipantCharcsDAO.getPartywiseCandidateOthersScaling(startDate,endDate,searchType,state,debateParticipantLocationIdList,debateLocationIdList);
				debateCountsList = debateParticipantDAO.getTotalDabtesCountsForEachCandidateOthersNew(startDate, endDate,state,debateParticipantLocationIdList,debateLocationIdList);
	      }
	      debateLocationIdList.remove(2L);  
	      
	      List<Object[]> subCandidateObjList = debateParticipantCharcsDAO.getPartywiseCandidateScaling(startDate,endDate,searchType,state,debateParticipantLocationIdList,debateLocationIdList);
	      List<Object[]> subDebateCountsList =debateParticipantDAO.getTotalDabtesCountsForEachCandidateNew(startDate, endDate,state,debateParticipantLocationIdList,debateLocationIdList);
	      
	      if((candidateObjList !=null && subCandidateObjList !=null) || (debateCountsList !=null && subDebateCountsList !=null) ){
	    	  candidateObjList.addAll(subCandidateObjList);
	    	  debateCountsList.addAll(subDebateCountsList);
	      }else{
	    	  candidateObjList = subCandidateObjList;
	    	  debateCountsList = subDebateCountsList;
	      }
	    }
		
		//Map<Long,CoreDebateVO> candidateMap= new HashMap<Long, CoreDebateVO>();
		//0.partyId,1.name,2.candidateId,3.name,4.scale
		//List<Object[]> candidateObjList =  debateParticipantCharcsDAO.getPartywiseCandidateScaling(startDate,endDate,searchType,state,debateParticipantLocationIdList,debateLocationIdList);
		
		//0.partyId,1.name,2.candidateId,3.name,4.debateCount
		//List<Object[]> debateCountsList = debateParticipantDAO.getTotalDabtesCountsForEachCandidateNew(startDate, endDate,state,debateParticipantLocationIdList,debateLocationIdList);
		 
		 List<Characteristics> charecters = characteristicsDAO.getCharacteristicsDetails();
					 
		if(commonMethodsUtilService.isListOrSetValid(candidateObjList)){
			for (Object[] parms : candidateObjList) {					
				Map<Long,CoreDebateVO> candidateMap = partyMap.get((Long)parms[0]);
				if(candidateMap == null){						
					candidateMap = new LinkedHashMap<Long, CoreDebateVO>();						
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
								vo.setScalePerc(Double.parseDouble(new BigDecimal(vo.getScalePerc()/charecters.size()).setScale(1, BigDecimal.ROUND_HALF_UP).toString()));
							}
						}
						vo.setDebateCount(vo.getDebateCount()+commonMethodsUtilService.getLongValueForObject(obj[4]));
						
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
		
		// Sorting Based On SearchType Of Scale Perc
		
					if(commonMethodsUtilService.isListOrSetValid(returnList)){
						for (CoreDebateVO party : returnList) {					
							Collections.sort(party.getCoreDebateVOList(),debateComparedCountSort);
							
							List<CoreDebateVO> newList = new ArrayList<CoreDebateVO>();
							
							if(searchType !=null && searchType.trim().equalsIgnoreCase("top")){
								if(party.getCoreDebateVOList() !=null && party.getCoreDebateVOList().size()>5){
									
									for(int i=Integer.parseInt(party.getCoreDebateVOList().size()+"");i>Integer.parseInt((party.getCoreDebateVOList().size()-5)+"");i--){	
										CoreDebateVO VO = party.getCoreDebateVOList().get(i-1);
										if(VO !=null){
											newList.add(VO);
										}								
									}
									
								}else{
									Collections.reverse(party.getCoreDebateVOList());
									newList.addAll(party.getCoreDebateVOList());
								}						
							}else if(searchType !=null && searchType.trim().equalsIgnoreCase("poor")){						
								if(party.getCoreDebateVOList() !=null && party.getCoreDebateVOList().size()>5){
									for(int i=0;i<5;i++){
										CoreDebateVO VO = party.getCoreDebateVOList().get(i);
										if(VO !=null){
											newList.add(VO);
										}
									}
								}else{
									newList.addAll(party.getCoreDebateVOList());
								}						
							}					
							party.getCoreDebateVOList().clear();					
							party.setCoreDebateVOList(newList);
						}				
					}
		
		//System.out.println(returnList);
		
	}catch (Exception e) {
		LOG.error("Exception raised at getSpokesPersonWiseDebate() method of CoreDashboardMainService", e);
	}
	
	return returnList;
}

	public static Comparator<CoreDebateVO> debateComparedCountSort = new Comparator<CoreDebateVO>()
	{
		public int compare(CoreDebateVO cstVO1, CoreDebateVO cstVO2)
		{			
			 Double perc1 = cstVO1.getScalePerc();
		      Double perc2 = cstVO2.getScalePerc();
			return perc1.compareTo(perc2);
		}
	};

public List<CoreDebateVO> getScaleBasedPerformanceCohort(String startDateStr,String endDateStr,String state,List<Long> debateLocationIdList){
	List<CoreDebateVO> returnList = new ArrayList<CoreDebateVO>();
	try{
		
		Date startDate = null;
		Date endDate   =null;
		
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		if(startDateStr !=null && !startDateStr.trim().isEmpty() && endDateStr !=null && !endDateStr.trim().isEmpty()){
			startDate = sdf.parse(startDateStr);
			endDate = sdf.parse(endDateStr);
		}
		List<Object[]> debateCountObjList = null;
		List<Object[]> scaleCountObjList = null;
	    if(debateLocationIdList != null && debateLocationIdList.size() == 1 && debateLocationIdList.contains(2l)){
	    	debateCountObjList = debateParticipantDAO.getPartyWiseDebateOtherDetails(startDate,endDate,state,debateLocationIdList);
	    	scaleCountObjList = debateParticipantCharcsDAO.getPartyWiseScalesOfOthersEachCharecter(startDate,endDate,state,debateLocationIdList);
	    }else if(debateLocationIdList !=null && debateLocationIdList.size() == 1L && !debateLocationIdList.contains(2L)){
	    	debateCountObjList = debateParticipantDAO.getPartyWiseDebateDetails(startDate,endDate,state,debateLocationIdList);
	    	scaleCountObjList = debateParticipantCharcsDAO.getPartyWiseScalesOfEachCharecter(startDate,endDate,state,debateLocationIdList);
	    }else if(debateLocationIdList !=null && debateLocationIdList.size()>1){ // 1,2
	      if(debateLocationIdList.contains(2L)){
	    	  debateCountObjList = debateParticipantDAO.getPartyWiseDebateOtherDetails(startDate,endDate,state,debateLocationIdList);
	    	  scaleCountObjList = debateParticipantCharcsDAO.getPartyWiseScalesOfOthersEachCharecter(startDate,endDate,state,debateLocationIdList);
	      }
	      debateLocationIdList.remove(2L);  
	      
	      List<Object[]> subDebateCountObjList = debateParticipantDAO.getPartyWiseDebateDetails(startDate,endDate,state,debateLocationIdList);
	      List<Object[]> subScaleCountObjList = debateParticipantCharcsDAO.getPartyWiseScalesOfEachCharecter(startDate,endDate,state,debateLocationIdList);
	      
	      if((debateCountObjList !=null && subDebateCountObjList !=null) || (scaleCountObjList !=null && subScaleCountObjList !=null) ){
	    	  debateCountObjList.addAll(subDebateCountObjList);
	    	  scaleCountObjList.addAll(subScaleCountObjList);
	      }else{
	    	  debateCountObjList = subDebateCountObjList;
	    	  scaleCountObjList = subScaleCountObjList;
	      }
	    }else{
	    	debateCountObjList = debateParticipantDAO.getPartyWiseDebateDetails(startDate,endDate,state,debateLocationIdList);
	    	scaleCountObjList = debateParticipantCharcsDAO.getPartyWiseScalesOfEachCharecter(startDate,endDate,state,debateLocationIdList);
	    }
		
		
		Map<Long,CoreDebateVO> countMap = new LinkedHashMap<Long, CoreDebateVO>();			
		//List<Object[]> debateCountObjList = debateParticipantDAO.getPartyWiseDebateDetails(startDate,endDate,state,debateLocationIdList);		
		
		//0.partyId,1.shortName,2.characteristicsId,3.name,4.scale
		//List<Object[]> scaleCountObjList = debateParticipantCharcsDAO.getPartyWiseScalesOfEachCharecter(startDate,endDate,state,debateLocationIdList);
		if(debateCountObjList != null && debateCountObjList.size()>0){
		if(commonMethodsUtilService.isListOrSetValid(debateCountObjList)){			
			countMap = setDebateValuesToMap(debateCountObjList,countMap);
		}
		}
	 if(scaleCountObjList != null && scaleCountObjList.size()>0){
		if(commonMethodsUtilService.isListOrSetValid(scaleCountObjList)){
			CoreDebateVO VO =null;
			for (Object[] obj : scaleCountObjList) {					
				CoreDebateVO partyVO = countMap.get(obj[0]);					
				if(partyVO != null){							
					List<CoreDebateVO> list = partyVO.getCoreDebateVOList();
					if(list == null){
						list = new LinkedList<CoreDebateVO>();
						partyVO.setCoreDebateVOList(list);
						partyVO.setOverAllPerc(0.00);
					}
					VO = getMatchedScaleId(list,commonMethodsUtilService.getLongValueForObject(obj[2]));
					if(VO == null){
					 VO = new CoreDebateVO();
					 VO.setId(commonMethodsUtilService.getLongValueForObject(obj[2]));
					 VO.setName(commonMethodsUtilService.getStringValueForObject(obj[3]));
					 list.add(VO);	
					}
					if(obj[4] !=null && (Double)obj[4]>0.0 && partyVO.getDebateCount() >0){
						
						if(partyVO.getCandidateCount() !=null && partyVO.getDebateCount() !=null && partyVO.getCandidateCount() > partyVO.getDebateCount()){
							VO.setScalePerc(Double.parseDouble(new BigDecimal(VO.getScalePerc()+Double.parseDouble(new BigDecimal((Double)obj[4]/partyVO.getCandidateCount()).setScale(1, BigDecimal.ROUND_HALF_UP).toString())).setScale(1, BigDecimal.ROUND_HALF_UP).toString()));
						}									
						else if(partyVO.getCandidateCount() !=null && partyVO.getDebateCount() !=null && partyVO.getDebateCount() > partyVO.getCandidateCount() ){
							VO.setScalePerc(Double.parseDouble(new BigDecimal(VO.getScalePerc()+Double.parseDouble(new BigDecimal((Double)obj[4]/partyVO.getDebateCount()).setScale(1, BigDecimal.ROUND_HALF_UP).toString())).setScale(1, BigDecimal.ROUND_HALF_UP).toString()));
						}else if(partyVO.getDebateCount() !=null && partyVO.getDebateCount()>0l){
							VO.setScalePerc(Double.parseDouble(new BigDecimal(VO.getScalePerc()+Double.parseDouble(new BigDecimal((Double)obj[4]/partyVO.getDebateCount()).setScale(1, BigDecimal.ROUND_HALF_UP).toString())).setScale(1, BigDecimal.ROUND_HALF_UP).toString()));
						}
						
						//VO.setScalePerc(Double.parseDouble(new BigDecimal((Double)obj[4]/partyVO.getDebateCount()).setScale(1, BigDecimal.ROUND_HALF_UP).toString()));
						//partyVO.setOverAllPerc(Double.parseDouble(new BigDecimal(partyVO.getOverAllPerc()+/*VO.getScalePerc()*/ 
								//Double.parseDouble(new BigDecimal((Double)obj[4]/partyVO.getDebateCount()).setScale(2, BigDecimal.ROUND_HALF_UP).toString())   ).setScale(1, BigDecimal.ROUND_HALF_UP).toString()));
						
						/*if(partyVO.getCandidateCount() !=null && partyVO.getDebateCount() !=null && partyVO.getCandidateCount() > partyVO.getDebateCount()){
							partyVO.setOverAllPerc(Double.parseDouble(new BigDecimal(partyVO.getOverAllPerc()+/*VO.getScalePerc()*/ 
									//Double.parseDouble(new BigDecimal((Double)obj[4]/partyVO.getCandidateCount()).setScale(2, BigDecimal.ROUND_HALF_UP).toString()) ).setScale(1, BigDecimal.ROUND_HALF_UP).toString()));
						//}									
						//else if(partyVO.getCandidateCount() !=null && partyVO.getDebateCount() !=null && partyVO.getDebateCount() > partyVO.getCandidateCount() ){
							//partyVO.setOverAllPerc(Double.parseDouble(new BigDecimal(partyVO.getOverAllPerc()+/*VO.getScalePerc()*/ 
									//Double.parseDouble(new BigDecimal((Double)obj[4]/partyVO.getDebateCount()).setScale(2, BigDecimal.ROUND_HALF_UP).toString())   ).setScale(1, BigDecimal.ROUND_HALF_UP).toString()));
						//}else if(partyVO.getDebateCount() !=null && partyVO.getDebateCount()>0l){
							//partyVO.setOverAllPerc(Double.parseDouble(new BigDecimal(partyVO.getOverAllPerc()+/*VO.getScalePerc()*/ 
									//Double.parseDouble(new BigDecimal((Double)obj[4]/partyVO.getDebateCount()).setScale(2, BigDecimal.ROUND_HALF_UP).toString())   ).setScale(1, BigDecimal.ROUND_HALF_UP).toString()));
						//}*/
						
					}						
					//list.add(VO);	
					if(list != null && list.size()>0){
						if(partyVO.getCandidateCount() !=null && partyVO.getDebateCount() !=null && partyVO.getCandidateCount() > partyVO.getDebateCount()){
						partyVO.setOverAllPerc(Double.parseDouble(new BigDecimal(partyVO.getOverAllPerc()+/*VO.getScalePerc()*/ 
								Double.parseDouble(new BigDecimal((Double)obj[4]/partyVO.getCandidateCount()).setScale(2, BigDecimal.ROUND_HALF_UP).toString()) ).setScale(1, BigDecimal.ROUND_HALF_UP).toString()));
					 }									
					else if(partyVO.getCandidateCount() !=null && partyVO.getDebateCount() !=null && partyVO.getDebateCount() > partyVO.getCandidateCount() ){
						partyVO.setOverAllPerc(Double.parseDouble(new BigDecimal(partyVO.getOverAllPerc()+/*VO.getScalePerc()*/ 
								Double.parseDouble(new BigDecimal((Double)obj[4]/partyVO.getDebateCount()).setScale(2, BigDecimal.ROUND_HALF_UP).toString())   ).setScale(1, BigDecimal.ROUND_HALF_UP).toString()));
					}else if(partyVO.getDebateCount() !=null && partyVO.getDebateCount()>0l){
						partyVO.setOverAllPerc(Double.parseDouble(new BigDecimal(partyVO.getOverAllPerc()+/*VO.getScalePerc()*/ 
								Double.parseDouble(new BigDecimal((Double)obj[4]/partyVO.getDebateCount()).setScale(2, BigDecimal.ROUND_HALF_UP).toString())   ).setScale(1, BigDecimal.ROUND_HALF_UP).toString()));
					 }
					}
				}
				
			}
		}
		}
		if(countMap !=null && countMap.size()>0){
			returnList = new ArrayList<CoreDebateVO>(countMap.values());
		}			
		
		//System.out.println(returnList);
		
	}catch (Exception e) {
		LOG.error("Exception raised at getScaleBasedPerformanceCohort() method of CoreDashboardMainService", e);
	}
	return returnList;
}

public List<CoreDebateVO> getCandidateOverAllPerformanceCohort(String startDateStr,String endDateStr,String state,List<Long> debateParticipantLocationIdList,List<Long> debateLocationIdList){
	
	List<CoreDebateVO> returnList = new ArrayList<CoreDebateVO>();
	List<CoreDebateVO> charactersList = new ArrayList<CoreDebateVO>();
	
	try{			
		Date startDate = null;
		Date endDate   =null;
		
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		if(startDateStr !=null && !startDateStr.trim().isEmpty() && endDateStr !=null && !endDateStr.trim().isEmpty()){
			startDate = sdf.parse(startDateStr);
			endDate = sdf.parse(endDateStr);
		}
		if(debateLocationIdList.contains(0L)){
			debateLocationIdList.remove(0L);
		}
		Map<Long,Map<Long,List<CoreDebateVO>>> countMap = new LinkedHashMap<Long, Map<Long,List<CoreDebateVO>>>();
		List<Object[]> charecterObjList =null;
		List<Object[]> debateCountsList  =null;
		if((debateLocationIdList != null && debateLocationIdList.size() == 1L && debateLocationIdList.contains(2l)) || (debateParticipantLocationIdList != null && debateParticipantLocationIdList.size() == 1 && debateParticipantLocationIdList.contains(2l))){
			charecterObjList = debateParticipantCharcsDAO.getPartywiseOthersCandidateCharectersScaling(startDate,endDate,state,debateParticipantLocationIdList,debateLocationIdList);
			debateCountsList = debateParticipantDAO.getTotalDabtesCountsForEachOtherCandidateNew(startDate, endDate,state,debateParticipantLocationIdList,debateLocationIdList);
	    }else if(debateLocationIdList !=null && debateLocationIdList.size() > 1L && !debateLocationIdList.contains(2L)){
	    	charecterObjList = debateParticipantCharcsDAO.getPartywiseCandidateCharectersScaling(startDate,endDate,state,debateParticipantLocationIdList,debateLocationIdList);
	    	debateCountsList = debateParticipantDAO.getTotalDabtesCountsForEachCandidateNew(startDate, endDate,state,debateParticipantLocationIdList,debateLocationIdList);
	    }else if(debateLocationIdList !=null && debateLocationIdList.size() == 2L && debateLocationIdList.contains(2L)){
		      if((debateLocationIdList.contains(2L)) || (debateParticipantLocationIdList.contains(2L))){
		    	  charecterObjList = debateParticipantCharcsDAO.getPartywiseOthersCandidateCharectersScaling(startDate,endDate,state,debateParticipantLocationIdList,debateLocationIdList);
		    	  debateCountsList = debateParticipantDAO.getTotalDabtesCountsForEachOtherCandidateNew(startDate, endDate,state,debateParticipantLocationIdList,debateLocationIdList);
		      }
		       debateLocationIdList.remove(2L);
		      
		       List<Object[]> subCharecterObjList=  debateParticipantCharcsDAO.getPartywiseCandidateCharectersScaling(startDate,endDate,state,debateParticipantLocationIdList,debateLocationIdList);
		       List<Object[]>  subDebateCountsList = debateParticipantDAO.getTotalDabtesCountsForEachCandidateNew(startDate, endDate,state,debateParticipantLocationIdList,debateLocationIdList);
		      
		      if((charecterObjList !=null && subCharecterObjList !=null) || (debateCountsList != null && subDebateCountsList != null) ){
		    	  charecterObjList.addAll(subCharecterObjList);
		    	  debateCountsList.addAll(subDebateCountsList);
		      }else{
		    	  charecterObjList= subCharecterObjList;
		    	  debateCountsList= subDebateCountsList;
		    	  
		      }
		    }else if(debateLocationIdList !=null && debateLocationIdList.size() ==3l){
		    	debateLocationIdList.clear();
		    	debateParticipantLocationIdList.clear();
		    	charecterObjList = debateParticipantCharcsDAO.getPartywiseCandidateCharectersScaling(startDate,endDate,state,debateParticipantLocationIdList,debateLocationIdList);
		    	debateCountsList = debateParticipantDAO.getTotalDabtesCountsForEachCandidateNew(startDate, endDate,state,debateParticipantLocationIdList,debateLocationIdList);
		    }
		
		
		
		
		
		//0.partyId,1.name,2.candidateId,3.name,4.charecterId,5.name,6.scale
		//List<Object[]> charecterObjList =  debateParticipantCharcsDAO.getPartywiseCandidateCharectersScaling(startDate,endDate,state,debateParticipantLocationIdList,debateLocationIdList);
		
		//0.partyId,1.name,2.candidateId,3.name,4.debateCount
		// List<Object[]> debateCountsList = debateParticipantDAO.getTotalDabtesCountsForEachCandidateNew(startDate, endDate,state,debateParticipantLocationIdList,debateLocationIdList);
		 
		 List<Characteristics> charecters = characteristicsDAO.getCharacteristicsDetails();
		 //List<Object[]> charecters =characteristicsDAO.getCharacteristicsDetailsNew();
		
		 if(commonMethodsUtilService.isListOrSetValid(charecterObjList)){
			 for (Object[] obj : charecterObjList) {				
				 Map<Long,List<CoreDebateVO>> candidateMap = countMap.get((Long)obj[0]);			//PartyMap		 
				 if(candidateMap ==null){
					 candidateMap = new LinkedHashMap<Long, List<CoreDebateVO>>();
					 countMap.put((Long)obj[0], candidateMap);
				 }					 
				 List<CoreDebateVO> charecterList  = candidateMap.get((Long)obj[2]);				//candidateMap
				 if(charecterList == null){
					 charecterList = new LinkedList<CoreDebateVO>();
					 candidateMap.put((Long)obj[2], charecterList);
				 }	
				 
				 
				 CoreDebateVO matchedVo  = getMatchedCandidateId(charecterList,commonMethodsUtilService.getLongValueForObject(obj[4]));
				 if(matchedVo == null){
					 matchedVo =new CoreDebateVO();
					 matchedVo.setCharecterId(commonMethodsUtilService.getLongValueForObject(obj[4]));
					 matchedVo.setCharecterName(commonMethodsUtilService.getStringValueForObject(obj[5]));
					 charecterList.add(matchedVo);
					 
				 }
				 matchedVo.setId((Long)obj[0]);
				 matchedVo.setName(commonMethodsUtilService.getStringValueForObject(obj[1]));
				 matchedVo.setCandidateId(commonMethodsUtilService.getLongValueForObject(obj[2]));
				 matchedVo.setCandidateName(commonMethodsUtilService.getStringValueForObject(obj[3]));
				 matchedVo.setScale(matchedVo.getScale()+Double.parseDouble(commonMethodsUtilService.getStringValueForObject(obj[6])));
				 /*CoreDebateVO VO = new CoreDebateVO();
				 VO.setId((Long)obj[0]);
				 VO.setName(commonMethodsUtilService.getStringValueForObject(obj[1]));
				 VO.setCandidateId(commonMethodsUtilService.getLongValueForObject(obj[2]));
				 VO.setCandidateName(commonMethodsUtilService.getStringValueForObject(obj[3]));
				 VO.setCharecterId(commonMethodsUtilService.getLongValueForObject(obj[4]));
				 VO.setCharecterName(commonMethodsUtilService.getStringValueForObject(obj[5]));
				 VO.setScale(Double.parseDouble(commonMethodsUtilService.getStringValueForObject(obj[6])));*/					
				 //charecterList.add(VO);
				
			 }
		 }
		 
		 if(commonMethodsUtilService.isListOrSetValid(debateCountsList)){
				for (Object[] obj : debateCountsList) {					
					Map<Long,List<CoreDebateVO>> candidateMap = countMap.get(commonMethodsUtilService.getLongValueForObject(obj[0]));					
					if(candidateMap !=null){
							List<CoreDebateVO> voList = candidateMap.get(commonMethodsUtilService.getLongValueForObject(obj[2]));						
							if(commonMethodsUtilService.isListOrSetValid(voList)){	
								Double candidateScale=0.00;
								for (CoreDebateVO vo : voList) {
									vo.setDebateCount(vo.getDebateCount()+commonMethodsUtilService.getLongValueForObject(obj[4]));	
									if(vo.getScale() !=null && vo.getScale()>0.0 && vo.getDebateCount() !=null && vo.getDebateCount()>0){
										vo.setScalePerc(Double.parseDouble(new BigDecimal(vo.getScale()/vo.getDebateCount()).setScale(1, BigDecimal.ROUND_HALF_UP).toString()));
										/*if(vo.getScalePerc() !=null && vo.getScalePerc()>0.00){
											vo.setOverAllPerc(Double.parseDouble(new BigDecimal(vo.getOverAllPerc()+vo.getScalePerc()
													Double.parseDouble(new BigDecimal(vo.getScale()/(Long)obj[4]).setScale(2, BigDecimal.ROUND_HALF_UP).toString())).setScale(1, BigDecimal.ROUND_HALF_UP).toString()));
										}*/
										
										candidateScale = candidateScale + vo.getScale();
									}
									
									//vo.setDebateCount(vo.getDebateCount()+commonMethodsUtilService.getLongValueForObject(obj[4]));	
									
									CoreDebateVO firstVo  = voList.get(0);
									if(candidateScale>0.00 && (vo.getDebateCount() !=null && vo.getDebateCount() >0l))
										firstVo.setOverAllPerc(Double.parseDouble(new BigDecimal((candidateScale/vo.getDebateCount())/charecters.size()).setScale(1, BigDecimal.ROUND_HALF_UP).toString()));
									
								}
							}
						}						
					}					
		}
		 
		 if(countMap !=null && countMap.size()>0){				
				for (Entry<Long, Map<Long, List<CoreDebateVO>>> party : countMap.entrySet()) {							
					CoreDebateVO VO = new CoreDebateVO();													
					VO.setId(party.getKey());//partyId					
					Map<Long, List<CoreDebateVO>> candidates =  party.getValue();
					
					if(candidates !=null){							
						List<CoreDebateVO> candidatesList =new LinkedList<CoreDebateVO>();							
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
		LOG.error("Exception raised at getCandidateOverAllPerformanceCohort() method of CoreDashboardMainService", e);
	}
	
	return returnList;
}

public List<CoreDebateVO> getChannelAndPartyWiseDetails(String startDateStr,String endDateStr,String state,List<Long> debateLocationIdList){
	
	List<CoreDebateVO> returnList = new ArrayList<CoreDebateVO>();
	
	try{			
		Date startDate = null;
		Date endDate   =null;
		
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		if(startDateStr !=null && !startDateStr.trim().isEmpty() && endDateStr !=null && !endDateStr.trim().isEmpty()){
			startDate = sdf.parse(startDateStr);
			endDate = sdf.parse(endDateStr);
		}
		
		List<Object[]> channelScaleObjList = null;
		List<Object[]> channelObjList = null;
	    if(debateLocationIdList != null && debateLocationIdList.size() == 1 && debateLocationIdList.contains(2l)){
	    	channelObjList = debateParticipantDAO.getChannelWiseOthersDebateDetails(startDate,endDate,state,debateLocationIdList);
	    	 channelScaleObjList = debateParticipantCharcsDAO.getChannelAndPartyWiseOthersCharecter(startDate,endDate,state,debateLocationIdList);
	    }else if(debateLocationIdList !=null && debateLocationIdList.size() == 1L && !debateLocationIdList.contains(2L)){
	    	channelObjList = debateParticipantDAO.getChannelWiseDebateDetails(startDate,endDate,state,debateLocationIdList);
	    	channelScaleObjList = debateParticipantCharcsDAO.getChannelAndPartyWiseCharecter(startDate,endDate,state,debateLocationIdList);
	    }else if(debateLocationIdList !=null && debateLocationIdList.size()>1){ // 1,2
	      if(debateLocationIdList.contains(2L)){
	    	  channelObjList = debateParticipantDAO.getChannelWiseOthersDebateDetails(startDate,endDate,state,debateLocationIdList);
	    	  channelScaleObjList = debateParticipantCharcsDAO.getChannelAndPartyWiseOthersCharecter(startDate,endDate,state,debateLocationIdList);
	      }
	      debateLocationIdList.remove(2L);  
	      
	      List<Object[]> subChannelObjList = debateParticipantDAO.getChannelWiseDebateDetails(startDate,endDate,state,debateLocationIdList);
	      List<Object[]> subChannelScaleObjList = debateParticipantCharcsDAO.getChannelAndPartyWiseCharecter(startDate,endDate,state,debateLocationIdList);
	      
	      if((channelScaleObjList !=null && subChannelScaleObjList !=null) || (channelObjList !=null && subChannelObjList !=null) ){
	    	  channelScaleObjList.addAll(subChannelScaleObjList);
	    	  channelObjList.addAll(subChannelObjList);
	      }else{
	    	  channelScaleObjList = subChannelScaleObjList;
	    	  channelObjList = subChannelObjList;
	      }
	    }else{
	    	channelObjList = debateParticipantDAO.getChannelWiseDebateDetails(startDate,endDate,state,debateLocationIdList);
	    	channelScaleObjList = debateParticipantCharcsDAO.getChannelAndPartyWiseCharecter(startDate,endDate,state,debateLocationIdList);
	    }
		
		Map<Long,Map<Long,CoreDebateVO>> channelMap = new LinkedHashMap<Long, Map<Long,CoreDebateVO>>();			
		//0.channelId,1.channel,2.partyId,3.partyName,4.scaleCount
		//List<Object[]> channelScaleObjList = debateParticipantCharcsDAO.getChannelAndPartyWiseCharecter(startDate,endDate,state,debateLocationIdList);
		//getChannelAndPartyWiseOthersCharecter
		
		//0.channelId,1.channel,2.partyId,3.partyName,4.debateCount
		//List<Object[]> channelObjList = debateParticipantDAO.getChannelWiseDebateDetails(startDate,endDate,state,debateLocationIdList);
		//getChannelWiseOthersDebateDetails
		List<Characteristics> charecters = characteristicsDAO.getCharacteristicsDetails();		
				
		List<Object[]> partieList = partyDAO.getPartiesListForCoreDashBoard(state);
	if(channelScaleObjList != null && channelScaleObjList.size()>0){
		if(commonMethodsUtilService.isListOrSetValid(channelScaleObjList)){
			
			channelMap = setDebateDetailsToMap(channelScaleObjList,channelMap,partieList);
			
			
			/*for (Object[] obj : channelScaleObjList) {					
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
			}*/
		}
	}
	if(channelObjList != null && channelObjList.size()>0){
		if(commonMethodsUtilService.isListOrSetValid(channelObjList)){	
			for (Object[] param : channelObjList) {					
				Map<Long,CoreDebateVO> partyMap = channelMap.get((Long)param[0]);
				if(partyMap !=null){																	
					CoreDebateVO vo = partyMap.get((Long)param[2]);
					if(vo !=null){	
						if(vo.getScale() !=null && param[4] !=null){
							vo.setScalePerc(Double.parseDouble(new BigDecimal((vo.getScale())/(Long)param[4]).setScale(2, BigDecimal.ROUND_HALF_UP).toString()));
							if(vo.getScalePerc() !=null && vo.getScalePerc() >0.0 ){
								vo.setScalePerc(Double.parseDouble(new BigDecimal(vo.getScalePerc()/charecters.size()).setScale(1, BigDecimal.ROUND_HALF_UP).toString()));
							}
						}
						vo.setDebateCount(commonMethodsUtilService.getLongValueForObject(param[4]));
					}
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
						if(partiesList != null && partiesList.size() > 0){
							for (CoreDebateVO coreDebateVO : partiesList) {
								if(coreDebateVO.getName() != null && !coreDebateVO.getName().trim().isEmpty()){
									VO.setName(coreDebateVO.getName());
								}
							}
						}
					}					
					VO.setCoreDebateVOList(partiesList);					
					returnList.add(VO);
				}				
		}
		
		//System.out.println(returnList);
		
	}catch (Exception e) {
		LOG.error("Exception raised at getChannelAndPartyWiseDetails() method of CoreDashboardMainService", e);
	}
	return returnList;
	
}

public Map<Long,Map<Long,CoreDebateVO>> setDebateDetailsToMap(List<Object[]> objList,Map<Long,Map<Long,CoreDebateVO>> mainMap,List<Object[]> partieList){			
	
	try{
		
		if(commonMethodsUtilService.isListOrSetValid(objList)){
			
			for (Object[] obj : objList) {					
				Map<Long,CoreDebateVO> innerMap =  mainMap.get((Long)obj[0]);							
				if(innerMap ==null){
					innerMap = new LinkedHashMap<Long, CoreDebateVO>();
					
					if(commonMethodsUtilService.isListOrSetValid(partieList)){
						for (Object[] param : partieList) {
							CoreDebateVO roleVO = new CoreDebateVO();
							roleVO.setCandidateId(commonMethodsUtilService.getLongValueForObject(param[0]));
							roleVO.setCandidateName(commonMethodsUtilService.getStringValueForObject(param[1]));
							innerMap.put(roleVO.getCandidateId(), roleVO);
						}
					}
					
					mainMap.put((Long)obj[0], innerMap);						
				}					
				CoreDebateVO VO = innerMap.get((Long)obj[2]);					
				if(VO == null){//Max it'l not happen
					VO = new CoreDebateVO();
					innerMap.put((Long)obj[2], VO);						
				}					
				VO.setId((Long)obj[0]);
				VO.setName(commonMethodsUtilService.getStringValueForObject(obj[1]));
				//VO.setCandidateId(commonMethodsUtilService.getLongValueForObject(obj[2]));
				//VO.setCandidateName(StringEscapeUtils.unescapeJava(commonMethodsUtilService.getStringValueForObject(obj[3])));
				VO.setScale(Double.parseDouble(commonMethodsUtilService.getStringValueForObject(obj[4])));					
			}
		}
		
	}catch (Exception e) {
		LOG.error("Exception raised at setDebateDetailsToMap() method of CoreDashboardMainService", e);
	}
	return mainMap;
	
}

public Map<Long,Map<Long,CoreDebateVO>> setDebateDetailsToMapNew(List<Object[]> objList,Map<Long,Map<Long,CoreDebateVO>> mainMap,List<Object[]> debateRoles){			
	
	try{
		
		List<Characteristics> charecters = characteristicsDAO.getCharacteristicsDetails();
		
		
		if(commonMethodsUtilService.isListOrSetValid(objList)){
			
			for (Object[] obj : objList) {					
				Map<Long,CoreDebateVO> innerMap =  mainMap.get((Long)obj[0]);							
				if(innerMap ==null){
					innerMap = new HashMap<Long, CoreDebateVO>();	
					
					if(commonMethodsUtilService.isListOrSetValid(debateRoles)){
						for (Object[] param : debateRoles) {
							CoreDebateVO roleVO = new CoreDebateVO();
							roleVO.setId((Long)obj[0]);
							roleVO.setName(commonMethodsUtilService.getStringValueForObject(obj[1]));
							roleVO.setCandidateId(commonMethodsUtilService.getLongValueForObject(param[0]));
							roleVO.setCandidateName(commonMethodsUtilService.getStringValueForObject(param[1]));
							innerMap.put(roleVO.getCandidateId(), roleVO);
						}
					}
					
					mainMap.put((Long)obj[0], innerMap);						
				}					
				CoreDebateVO VO = innerMap.get((Long)obj[2]);					
				if(VO == null){
					VO = new CoreDebateVO();
					innerMap.put((Long)obj[2], VO);						
				}				
				
				//VO.setCandidateId(commonMethodsUtilService.getLongValueForObject(obj[2]));
				//VO.setCandidateName(StringEscapeUtils.unescapeJava(commonMethodsUtilService.getStringValueForObject(obj[3])));
				VO.setScale(VO.getScale()+Double.parseDouble(commonMethodsUtilService.getStringValueForObject(obj[4])));		
				VO.setDebateCount(VO.getDebateCount()+commonMethodsUtilService.getLongValueForObject(obj[5]));
				
				if( VO.getScale() !=null && VO.getScale()>0.00 && VO.getDebateCount() !=null && VO.getDebateCount()>0l){
					VO.setScalePerc(Double.parseDouble(new BigDecimal((VO.getScale())/VO.getDebateCount()).setScale(2, BigDecimal.ROUND_HALF_UP).toString()));									
					if(VO.getScalePerc() !=null && VO.getScalePerc() >0.0 ){
						VO.setScalePerc(Double.parseDouble(new BigDecimal(VO.getScalePerc()/charecters.size()).setScale(1, BigDecimal.ROUND_HALF_UP).toString()));
					}									
				}
				
			}
		}
		
	}catch (Exception e) {
		LOG.error("Exception raised at setDebateDetailsToMapNew() method of CoreDashboardMainService", e);
	}
	return mainMap;
	
}

public List<CoreDebateVO> getRoleBasedPerformanceCohort(String startDateStr,String endDateStr,String state,List<Long> debateLocationIdList){
	
	List<CoreDebateVO> returnList = new ArrayList<CoreDebateVO>(0);
	
	try{			
		Date startDate = null;
		Date endDate   =null;
		
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		if(startDateStr !=null && !startDateStr.trim().isEmpty() && endDateStr !=null && !endDateStr.trim().isEmpty()){
			startDate = sdf.parse(startDateStr);
			endDate = sdf.parse(endDateStr);
		}
		
		List<Object[]> debateCountObjList = null;
		List<Object[]> roleObjList = null;
	    if(debateLocationIdList != null && debateLocationIdList.size() == 1 && debateLocationIdList.contains(2l)){
	    	 debateCountObjList = debateParticipantDAO.getPartyWiseDebateOtherDetails(startDate,endDate,state,debateLocationIdList);
	    	 roleObjList = debateParticipantCharcsDAO.getRoleBasedOthersPerformanceCohort(startDate,endDate,state,debateLocationIdList);
	    }else if(debateLocationIdList !=null && debateLocationIdList.size() == 1L && !debateLocationIdList.contains(2L)){
	    	debateCountObjList = debateParticipantDAO.getPartyWiseDebateDetails(startDate,endDate,state,debateLocationIdList);
	    	roleObjList = debateParticipantCharcsDAO.getRoleBasedPerformanceCohort(startDate,endDate,state,debateLocationIdList);
	    }else if(debateLocationIdList !=null && debateLocationIdList.size()>1){ // 1,2
	      if(debateLocationIdList.contains(2L)){
	    	  debateCountObjList = debateParticipantDAO.getPartyWiseDebateOtherDetails(startDate,endDate,state,debateLocationIdList);
	    	  roleObjList = debateParticipantCharcsDAO.getRoleBasedOthersPerformanceCohort(startDate,endDate,state,debateLocationIdList);
	      }
	      debateLocationIdList.remove(2L);  
	      
	      List<Object[]> subDebateCountObjList = debateParticipantDAO.getPartyWiseDebateDetails(startDate,endDate,state,debateLocationIdList);
	      List<Object[]> subRoleObjList = debateParticipantCharcsDAO.getRoleBasedPerformanceCohort(startDate,endDate,state,debateLocationIdList);
	      
	      if((debateCountObjList !=null && subDebateCountObjList !=null) || (roleObjList !=null && subRoleObjList !=null) ){
	    	  debateCountObjList.addAll(subDebateCountObjList);
	    	  roleObjList.addAll(subRoleObjList);
	      }else{
	    	  debateCountObjList = subDebateCountObjList;
	    	  roleObjList = subRoleObjList;
	      }
	    }else{
	    	debateCountObjList = debateParticipantDAO.getPartyWiseDebateDetails(startDate,endDate,state,debateLocationIdList);
	    	roleObjList = debateParticipantCharcsDAO.getRoleBasedPerformanceCohort(startDate,endDate,state,debateLocationIdList);
	    }	
		Map<Long,Map<Long,CoreDebateVO>> rolesMap = new LinkedHashMap<Long, Map<Long,CoreDebateVO>>();			
		//0.partyId,1.name,2.rolesId,3.role,4.scale,5.debatesCount
		//List<Object[]> roleObjList = debateParticipantCharcsDAO.getRoleBasedPerformanceCohort(startDate, endDate,state,debateLocationIdList);
		
		//0.partyId,1.shortName,2.debateCount,3.candidatesCount
		//List<Object[]> debateCountObjList = debateParticipantDAO.getPartyWiseDebateDetails(startDate,endDate,state,debateLocationIdList);
		
		List<Characteristics> charecters = characteristicsDAO.getCharacteristicsDetails();
		
		List<Object[]> debateRoles =  debateRolesDAO.getDebateRolesNew();
		if(roleObjList != null && roleObjList.size()>0){
		if(commonMethodsUtilService.isListOrSetValid(roleObjList)){			
			rolesMap = setDebateDetailsToMapNew(roleObjList,rolesMap,debateRoles);				
		}
		}
		if(debateCountObjList != null && debateCountObjList.size()>0){
		if(commonMethodsUtilService.isListOrSetValid(debateCountObjList)){
			for (Object[] obj : debateCountObjList) {					
				Map<Long,CoreDebateVO> roleMap =rolesMap.get((Long)obj[0]);					
				if(roleMap !=null){
					for (Entry<Long, CoreDebateVO> role : roleMap.entrySet()) {							
						CoreDebateVO VO = role.getValue();	
						if(VO !=null){
							/*if( VO.getScale() !=null && VO.getScale()>0.00 && obj[2] !=null && (Long)obj[2]>0l){
								VO.setScalePerc(Double.parseDouble(new BigDecimal((VO.getScale())/(Long)obj[2]).setScale(2, BigDecimal.ROUND_HALF_UP).toString()));									
								if(VO.getScalePerc() !=null && VO.getScalePerc() >0.0 ){
									VO.setScalePerc(Double.parseDouble(new BigDecimal(VO.getScalePerc()/charecters.size()).setScale(1, BigDecimal.ROUND_HALF_UP).toString()));
								}									
							}*/
							VO.setOverAllDebateCount(commonMethodsUtilService.getLongValueForObject(obj[2]) + VO.getOverAllDebateCount());			
						}				
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
		LOG.error("Exception raised at getRoleBasedPerformanceCohort() method of CoreDashboardMainService", e);
	}
	
	return returnList;
}

public List<CoreDebateVO> getRolesPerformanceOfCandidate(String startDateStr,String endDateStr,List<Long> roles,String state,List<Long> participantLocationIdList,List<Long> debateLocationIdList){
	
	List<CoreDebateVO> candidateScaleList = new ArrayList<CoreDebateVO>();
	
	try{
		
		Date startDate = null;
		Date endDate   =null;
		
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		if(startDateStr !=null && !startDateStr.trim().isEmpty() && endDateStr !=null && !endDateStr.trim().isEmpty()){
			startDate = sdf.parse(startDateStr);
			endDate = sdf.parse(endDateStr);
		}
		
		
		//0.candidateId,1.name,2.partyId,3.name,4.scale,5.debateCount	
		List<Object[]> candidateScaleObj = null;
		if(debateLocationIdList.contains(0L)){
			debateLocationIdList.remove(0L);
		}
		
		if(roles !=null && roles.size()>0){
			/*if((debateLocationIdList != null && debateLocationIdList.size() == 1 && debateLocationIdList.contains(2l)) || (participantLocationIdList != null && participantLocationIdList.size() == 1 && participantLocationIdList.contains(2l))){
                candidateScaleObj = debateParticipantCharcsDAO.getScaleOfCandidateOthersNew(startDate,endDate,roles,state,participantLocationIdList,debateLocationIdList);
            }else if((debateLocationIdList !=null && debateLocationIdList.size() == 1L && !debateLocationIdList.contains(2L)) || (participantLocationIdList !=null && participantLocationIdList.size() == 1L && !participantLocationIdList.contains(2L))){
                candidateScaleObj = debateParticipantCharcsDAO.getScaleOfCandidateNew(startDate,endDate,roles,state,participantLocationIdList,debateLocationIdList);
            }else if((debateLocationIdList !=null && debateLocationIdList.size()>1) || (participantLocationIdList !=null && participantLocationIdList.size()>1) ){ // 1,2
                  if((debateLocationIdList.contains(2L)) || (participantLocationIdList.contains(2L))){
                      candidateScaleObj = debateParticipantCharcsDAO.getScaleOfCandidateOthersNew(startDate,endDate,roles,state,participantLocationIdList,debateLocationIdList);
                  }
                   debateLocationIdList.remove(2L);
                 
                   List<Object[]> subCandidateScaleObj=  debateParticipantCharcsDAO.getScaleOfCandidateNew(startDate,endDate,roles,state,participantLocationIdList,debateLocationIdList);
                 
                  if(candidateScaleObj !=null && subCandidateScaleObj !=null ){
                      candidateScaleObj.addAll(subCandidateScaleObj);
                  }else{
                      candidateScaleObj= subCandidateScaleObj;
                     
                  }
                }else{
                    candidateScaleObj = debateParticipantCharcsDAO.getScaleOfCandidateNew(startDate,endDate,roles,state,participantLocationIdList,debateLocationIdList);
                }*/
			candidateScaleObj = debateParticipantCharcsDAO.getScaleOfCandidateNew(startDate,endDate,roles,state,participantLocationIdList,debateLocationIdList);
		}else{
			/*if((debateLocationIdList != null && debateLocationIdList.size() == 1 && debateLocationIdList.contains(2l)) || (participantLocationIdList != null && participantLocationIdList.size() == 1 && participantLocationIdList.contains(2l))){
                candidateScaleObj = debateParticipantCharcsDAO.getScaleOfOthersCandidate(startDate, endDate, roles, state,participantLocationIdList,debateLocationIdList);
            }else if((debateLocationIdList !=null && debateLocationIdList.size() == 1L && !debateLocationIdList.contains(2L)) || (participantLocationIdList !=null && participantLocationIdList.size() == 1L && !participantLocationIdList.contains(2L))){
                candidateScaleObj = debateParticipantCharcsDAO.getScaleOfCandidate(startDate, endDate, roles, state,participantLocationIdList,debateLocationIdList);
            }else if((debateLocationIdList !=null && debateLocationIdList.size()>1) || (participantLocationIdList !=null && participantLocationIdList.size()>1) ){ // 1,2
                  if((debateLocationIdList.contains(2L)) || (participantLocationIdList.contains(2L))){
                      candidateScaleObj = debateParticipantCharcsDAO.getScaleOfOthersCandidate(startDate, endDate, roles, state,participantLocationIdList,debateLocationIdList);
                  }
                   debateLocationIdList.remove(2L);
                 
                   List<Object[]> subCandidateScaleObj= debateParticipantCharcsDAO.getScaleOfCandidate(startDate, endDate, roles, state,participantLocationIdList,debateLocationIdList);
                 
                  if(candidateScaleObj !=null && subCandidateScaleObj !=null ){
                      candidateScaleObj.addAll(subCandidateScaleObj);
                  }else{
                      candidateScaleObj= subCandidateScaleObj;
                     
                  }
                }else{
                    candidateScaleObj = debateParticipantCharcsDAO.getScaleOfCandidate(startDate, endDate, roles, state,participantLocationIdList,debateLocationIdList);
                }*/
			candidateScaleObj = debateParticipantCharcsDAO.getScaleOfCandidate(startDate, endDate, roles, state,participantLocationIdList,debateLocationIdList);
		}
		
		
		
		//0.candidateId,1.name,2.partyId,3.name,4.debatesCount			
		//List<Object[]>  candidateDebateObj = debateParticipantDAO.getDebatesCountOfCandidate(startDate,endDate,roles,state);			
		
		
		List<Characteristics> charecters = characteristicsDAO.getCharacteristicsDetails();
		
		if(commonMethodsUtilService.isListOrSetValid(candidateScaleObj)){
			candidateScaleList = setCandidateObjIntoList(candidateScaleObj,candidateScaleList);
		}
		
		if(commonMethodsUtilService.isListOrSetValid(candidateScaleList)){			
			for (CoreDebateVO VO : candidateScaleList) {		
				if(VO.getScale() !=null && VO.getScale()>0.00 &&  VO.getDebateCount() !=null &&  VO.getDebateCount()>0){
					VO.setScalePerc(Double.parseDouble(new BigDecimal((VO.getScale()/VO.getDebateCount()) / charecters.size()).setScale(1, BigDecimal.ROUND_HALF_UP).toString()));
				}					
			}				
		}
		
		//Sorting Based on Scale Percenatage For Candidate
		
		if(commonMethodsUtilService.isListOrSetValid(candidateScaleList)){				
			Collections.sort(candidateScaleList,scaleComparedCountSort);
			
			List<CoreDebateVO> newList = new ArrayList<CoreDebateVO>();
			
			if(candidateScaleList !=null && candidateScaleList.size()>5){
				for(int i=0;i<5;i++){
					CoreDebateVO VO = candidateScaleList.get(i);
					if(VO !=null){
						newList.add(VO);
					}
				}
			}else{
				newList.addAll(candidateScaleList);
			}
			
			return newList;
			
		}
	}catch (Exception e) {
		LOG.error("Exception raised at getRolesPerformanceOfCandidate() method of CoreDashboardMainService", e);
	}
	
	return candidateScaleList;
	
}

public static Comparator<CoreDebateVO> scaleComparedCountSort = new Comparator<CoreDebateVO>()
{
			public int compare(CoreDebateVO cstVO1, CoreDebateVO cstVO2)
			{			
				 Double perc1 = cstVO1.getScalePerc();
			      Double perc2 = cstVO2.getScalePerc();
				return perc2.compareTo(perc1);
			}
};

public List<CoreDebateVO> setCandidateObjIntoList(List<Object[]> candidateScaleObj , List<CoreDebateVO> candidateScaleList){
	try{			
		if(commonMethodsUtilService.isListOrSetValid(candidateScaleObj)){				
			String[] propertiesList = {"id","name","candidateId","candidateName","scale","debateCount"};				
			candidateScaleList = setterAndGetterUtilService.setValuesToVO(candidateScaleObj, propertiesList, "com.itgrids.partyanalyst.dto.CoreDebateVO");				
		}
		
	}catch (Exception e) {
		LOG.error("Exception raised at setCandidateObjIntoList() method of CoreDashboardMainService", e);
	}
	
	return candidateScaleList;
}

public List<IdNameVO> getDebateRolesNew(){
	List<IdNameVO> returnList = new ArrayList<IdNameVO>(0);
	try{
		
		List<Object[]> debateRoles =  debateRolesDAO.getDebateRolesNew();
		
		if(commonMethodsUtilService.isListOrSetValid(debateRoles)){			
			String[] propertiesList = {"id","name"};				
			returnList = setterAndGetterUtilService.setValuesToVO(debateRoles, propertiesList, "com.itgrids.partyanalyst.dto.IdNameVO");			
		}
		
	}catch (Exception e) {
		LOG.error("Exception raised at getDebateRolesNew() method of CoreDashboardMainService", e);
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
}//state lvl.
public List<IdNameVO> getStateLevelCampAttendedDetails(List<Long> programIdList,Long stateId,String dateStr,String option,List<Long> enrollYrIds){    
	LOG.info(" entered in to getStateLevelCampAttendedDetails() of CoreDashBoardMainService ");
	/*try{
		DateUtilService dateUtilService = new DateUtilService();
		Date toDate = null;
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd");
		if(dateStr != null && !dateStr.isEmpty() && dateStr.length() > 0){
			toDate = sdf.parse(dateStr);
		}
		IdNameVO idNameVO = null;  
		Map<Long,IdNameVO> idAndIdNameVoMap = new HashMap<Long,IdNameVO>();
		List<IdNameVO> idNameVOs  = new ArrayList<IdNameVO>();
		
		List<Object[]> inviteCountList = trainingCampBatchAttendeeDAO.getTotalInvitedForTrainingCampStateLevel(programIdList, stateId, toDate,enrollYrIds);
		List<Object[]> attendedCountList = null;
		if(option.equalsIgnoreCase("dayWise") && programIdList.size() ==1 && programIdList.get(0) != 6){    
			List<Object[]> fromAndToDate = trainingCampBatchDAO.getFromAndToDate(programIdList.get(0));
			String frmDateStr = "";
			String toDateStr = "";
			Date fromDate = null; 
			Date toDt = null;
			if(fromAndToDate != null && fromAndToDate.size() > 0){
				frmDateStr = fromAndToDate.get(0)[0].toString().substring(0, 10);
				toDateStr = fromAndToDate.get(0)[1].toString().substring(0, 10);
				fromDate = sdf1.parse(frmDateStr);
				toDt = sdf1.parse(toDateStr); 
			}
			
			List<String> dateStrList = dateUtilService.getDaysBetweenDatesStringFormat(fromDate, toDt);
			List<Date> dateList = new ArrayList<Date>();
			if(dateStrList != null && dateStrList.size() > 0){  
				for(String param : dateStrList){
					dateList.add(sdf1.parse(param));
				}
			}
			attendedCountList = trainingCampAttendanceDAO.getTotalAttendedForTrainingCampStateLevel(programIdList, stateId, toDate,dateList,option,enrollYrIds); 
			if(attendedCountList != null && attendedCountList.size() > 0){
				for(Object[] param : attendedCountList){
					idNameVO = new IdNameVO();
					idNameVO.setId(param[0] != null ? (Long)param[0] : 0l);
					idNameVO.setName(param[1] != null ? param[1].toString() : "");
					idNameVO.setDateStr(param[2] != null ? param[2].toString() : "");
					idNameVO.setActualCount(param[3] != null ? (Long)param[3] : 0l);
					idNameVO.setCount(inviteCountList.get(0)[2] != null ? (Long)inviteCountList.get(0)[2] : 0l);  
					idNameVOs.add(idNameVO);      
				}
				return idNameVOs;    
			}
		}else{
			attendedCountList = trainingCampAttendanceDAO.getTotalAttendedForTrainingCampStateLevel(programIdList, stateId, toDate,null,option,enrollYrIds); 
		}
		
		if(inviteCountList != null && inviteCountList.size() > 0){
			for(Object[] obj : inviteCountList){
				idNameVO = new IdNameVO();
				idNameVO.setId(obj[0] != null ? (Long)obj[0] : 0l);
				idNameVO.setName(obj[1] != null ? obj[1].toString() : "");    
				idNameVO.setCount(obj[2] != null ? (Long)obj[2] : 0l);
				idAndIdNameVoMap.put(obj[0] != null ? (Long)obj[0] : 0l, idNameVO);
				
			}  
			
		}  
		if(attendedCountList != null && attendedCountList.size() > 0){
			for(Object[] obj : attendedCountList){
				idNameVO = idAndIdNameVoMap.get(obj[0] != null ? (Long)obj[0] : 0l);
				if(idNameVO != null){
					idNameVO.setActualCount(obj[2] != null ? (Long)obj[2] : 0l);
				}
			}
		}
		List<Long> inviteeRoles = new ArrayList<Long>();
		inviteeRoles.add(1l);
		inviteeRoles.add(2l);
		inviteeRoles.add(3l);
		List<Object[]> invAttndNonInvAttnd = trainingCampBatchAttendeeDAO.getInviteeAttndAndNonInviteeAttnded(programIdList, stateId, toDate,enrollYrIds);
		if(invAttndNonInvAttnd != null && invAttndNonInvAttnd.size() > 0){
			for(Object[] obj : invAttndNonInvAttnd){
				idNameVO = idAndIdNameVoMap.get(obj[0] != null ? (Long)obj[0] : 0l);
				if(idNameVO != null){
					if(inviteeRoles.contains(commonMethodsUtilService.getLongValueForObject(obj[3]))){
						if(idNameVO.getInviteeAttnd() != null)
							idNameVO.setInviteeAttnd(idNameVO.getInviteeAttnd()+commonMethodsUtilService.getLongValueForObject(obj[2]));
						else
							idNameVO.setInviteeAttnd(commonMethodsUtilService.getLongValueForObject(obj[2]));
					}else{
						if(idNameVO.getNonInviteeAttnd() != null)
							idNameVO.setNonInviteeAttnd(idNameVO.getNonInviteeAttnd()+commonMethodsUtilService.getLongValueForObject(obj[2]));
						else
							idNameVO.setNonInviteeAttnd(commonMethodsUtilService.getLongValueForObject(obj[2]));
					}
					//idNameVO.setActualCount(obj[2] != null ? (Long)obj[2] : 0l);
				}
			}
		}
		idNameVOs = new ArrayList<IdNameVO>(idAndIdNameVoMap.values());
		Collections.sort(idNameVOs, new Comparator<IdNameVO>(){
			public int compare(IdNameVO obj1, IdNameVO obj2){
				if(obj1.getId() == obj2.getId()){
					return 0;
				}else{
					return obj1.getId() > obj2.getId() ? -1 : 1;
				}
			}
		});
		
		return idNameVOs;
		
	}catch(Exception e){
		e.printStackTrace();
		LOG.error("Error occured at getStateLevelCampAttendedDetails() in CoreDashBoardMainService ",e); 
	}
	return null;  */
	
	List<IdNameVO> vo = getStateLevelCampAttendedDetailsDyaWise(programIdList, stateId, dateStr, option, enrollYrIds);

	return vo;
}

public List<List<IdNameVO>> getStateLevelCampDetailsRepresentative(List<Long> programIdList, Long stateId, String dateStr,List<Long> enrollYrIds){
	LOG.info(" entered in to getStateLevelCampDetailsRepresentatative() of CoreDashBoardMainService ");
	try{
		Date toDate = null;
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		if(dateStr != null && !dateStr.isEmpty() && dateStr.length() > 0){
			 toDate = sdf.parse(dateStr);  
		}
		List<List<IdNameVO>> pubRepDtls = new ArrayList<List<IdNameVO>>();
		List<Long> locationIdList = null;
		List<Long> idList = new ArrayList<Long>();
		Long count = 0l;
		List<IdNameVO> idNameVOs = new ArrayList<IdNameVO>();
		IdNameVO idNameVO = null;
		Map<Long,String> idAndLocationMapInvite = new HashMap<Long,String>();
		Map<Long,Long> idAndValueMapInvite = new HashMap<Long,Long>();
		Map<Long,String> idAndLocationMapAttendee = new HashMap<Long,String>();
		Map<Long,Long> idAndValueMapAttendee = new HashMap<Long,Long>();
		
		Long stateLevelCampId = 6l;
		Long stateLevelProgramId = 6l;
		List<IdNameVO> campDetailsRepresentative = new ArrayList<IdNameVO>(0);     
		List<Object[]> stateDistrictTrainingProgramInvitedDetails = trainingCampBatchAttendeeDAO.getStateDistrictTrainingProgramInvitedDetails(stateLevelCampId, programIdList, stateId, toDate,enrollYrIds);
		List<Object[]> stateDistrictTrainingProgramAttendedDetails = trainingCampAttendanceDAO.getStateDistrictTrainingProgramAttendedDetails(stateLevelCampId, programIdList, stateId, toDate,enrollYrIds);  
		
		List<Object[]> mlaMpInchargeTrainingProgramInvitedDetails = trainingCampBatchAttendeeDAO.getMlaMpInchargeTrainingProgramInvitedDetails(stateLevelCampId, programIdList, stateId, toDate,enrollYrIds);
		List<Object[]> mlaMpInchargeTrainingProgramAttendedDetails = trainingCampAttendanceDAO.getMlaMpInchargeTrainingProgramAttendedDetails(stateLevelCampId, programIdList, stateId, toDate,enrollYrIds);
		if(stateDistrictTrainingProgramInvitedDetails != null && stateDistrictTrainingProgramInvitedDetails.size() > 0){ 
			for(Object[] obj : stateDistrictTrainingProgramInvitedDetails){      
				idList.add(obj[0] != null ? (Long)obj[0] : 0l);
				idAndLocationMapInvite.put(obj[0] != null ? (Long)obj[0] : 0l, obj[1] != null ? obj[1].toString() : "");
				idAndValueMapInvite.put(obj[0] != null ? (Long)obj[0] : 0l, obj[2] != null ? (Long)obj[2] : 0l);
			}
			//10:state 11:dist
			locationIdList = new ArrayList<Long>(){{  
				add(10l);
				add(11l);
			}};
			idList.removeAll(locationIdList);
		}
		if(stateDistrictTrainingProgramAttendedDetails != null && stateDistrictTrainingProgramAttendedDetails.size() > 0){
			for(Object[] obj : stateDistrictTrainingProgramAttendedDetails){
				idAndLocationMapAttendee.put(obj[0] != null ? (Long)obj[0] : 0l, obj[1] != null ? obj[1].toString() : "");
				idAndValueMapAttendee.put(obj[0] != null ? (Long)obj[0] : 0l, obj[2] != null ? (Long)obj[2] : 0l);
			}
		}
		//for district and state
		if(locationIdList != null && locationIdList.size() > 0 ){
			for(Long id : locationIdList){
				idNameVO = new IdNameVO();
				count = idAndValueMapInvite.get(id);
				if(count != null){
					idNameVO.setId(id);
					idNameVO.setCount(count);
					idNameVO.setStatus(idAndLocationMapInvite.get(id) != null ? idAndLocationMapInvite.get(id) : "");
					idNameVO.setActualCount(idAndValueMapAttendee.get(id) != null ? idAndValueMapAttendee.get(id) : 0l);
					idNameVO.setApplicationStatus("tdpCommitteeLevel");
					idNameVO.setApplicationStatusId(id);
					idNameVOs.add(idNameVO);
				}
			}
		}
		//except dist and state remaining are in others
		Long othersCount = 0l;
		Long othersActualCount = 0l;
		if(idList != null && idList.size() > 0 ){
			for(Long id : idList){
				count = idAndValueMapInvite.get(id);
				if(count != null){
					othersCount+=count;
					othersActualCount+=idAndValueMapAttendee.get(id) != null ? idAndValueMapAttendee.get(id) : 0l;
				}
			}
		}
		//for MLA/MLC/MP/CONSTITUTION/INCHARGE
		
		idAndLocationMapInvite.clear();
		idAndValueMapInvite.clear();
		idAndLocationMapAttendee.clear();
		idAndValueMapAttendee.clear();
		if(mlaMpInchargeTrainingProgramInvitedDetails != null && mlaMpInchargeTrainingProgramInvitedDetails.size() > 0){
			for(Object[] obj : mlaMpInchargeTrainingProgramInvitedDetails){
				idAndLocationMapInvite.put(obj[0] != null ? (Long)obj[0] : 0l, obj[1] != null ? obj[1].toString() : "");
				idAndValueMapInvite.put(obj[0] != null ? (Long)obj[0] : 0l, obj[2] != null ? (Long)obj[2] : 0l);
			}
		}
		if(mlaMpInchargeTrainingProgramAttendedDetails != null && mlaMpInchargeTrainingProgramAttendedDetails.size() > 0){
			for(Object[] obj : mlaMpInchargeTrainingProgramAttendedDetails){
				idAndLocationMapAttendee.put(obj[0] != null ? (Long)obj[0] : 0l, obj[1] != null ? obj[1].toString() : "");
				idAndValueMapAttendee.put(obj[0] != null ? (Long)obj[0] : 0l, obj[2] != null ? (Long)obj[2] : 0l);
			}
		}
		//for MLA-2
		count = idAndValueMapInvite.get(2l);
		if(count != null){
			idNameVO = new IdNameVO();
			idNameVO.setId(2l);
			idNameVO.setCount(count);
			idNameVO.setStatus(idAndLocationMapInvite.get(2l) != null ? idAndLocationMapInvite.get(2l) : "");
			idNameVO.setActualCount(idAndValueMapAttendee.get(2l) != null ? idAndValueMapAttendee.get(2l) : 0l);
			idNameVO.setApplicationStatus("publicRepresentative");
			idNameVO.setApplicationStatusId(2l);
			campDetailsRepresentative.add(idNameVO);
		}
		//for MLC-12
		count = idAndValueMapInvite.get(12l);
		if(count != null){
			idNameVO = new IdNameVO();
			idNameVO.setId(12l);
			idNameVO.setCount(count);
			idNameVO.setStatus(idAndLocationMapInvite.get(12l) != null ? idAndLocationMapInvite.get(12l) : "");
			idNameVO.setActualCount(idAndValueMapAttendee.get(12l) != null ? idAndValueMapAttendee.get(12l) : 0l);
			idNameVO.setApplicationStatus("publicRepresentative");
			idNameVO.setApplicationStatusId(12l);
			campDetailsRepresentative.add(idNameVO);
		}
		//for MP-1
		count = idAndValueMapInvite.get(1l);
		if(count != null){
			idNameVO = new IdNameVO();
			idNameVO.setId(1l);
			idNameVO.setCount(count);
			idNameVO.setStatus(idAndLocationMapInvite.get(1l) != null ? idAndLocationMapInvite.get(1l) : "");
			idNameVO.setActualCount(idAndValueMapAttendee.get(1l) != null ? idAndValueMapAttendee.get(1l) : 0l);
			idNameVO.setApplicationStatus("publicRepresentative");
			idNameVO.setApplicationStatusId(1l);
			campDetailsRepresentative.add(idNameVO);
		}
		//for Constituency Incharge-21
		count = idAndValueMapInvite.get(21l);
		if(count != null){
			idNameVO = new IdNameVO();     
			idNameVO.setId(21l);
			idNameVO.setCount(count);
			idNameVO.setStatus(idAndLocationMapInvite.get(21l) != null ? idAndLocationMapInvite.get(21l) : "");
			idNameVO.setActualCount(idAndValueMapAttendee.get(21l) != null ? idAndValueMapAttendee.get(21l) : 0l);
			idNameVO.setApplicationStatus("publicRepresentative");
			idNameVO.setApplicationStatusId(21l);
			campDetailsRepresentative.add(idNameVO);
		}
		//for Other
		List<Long> representativeIds = new ArrayList<Long>(){{
			add(1l);
			add(2l);
			add(12l);
			add(21l);
		}};
		List<Long> otherPubRepIdList = publicRepresentativeTypeDAO.getIds(representativeIds);
		idNameVO = new IdNameVO();
		Long actualCount = 0l;
		for(Long id : otherPubRepIdList){
			count = idAndValueMapInvite.get(id);
			actualCount = idAndValueMapAttendee.get(id) != null ? idAndValueMapAttendee.get(id) : 0l;
			if(count != null){
				idNameVO.setId(0l);
				idNameVO.setCount(idNameVO.getCount()+count);
				idNameVO.setStatus("Other");
				idNameVO.setActualCount(idNameVO.getActualCount() + actualCount);
				idNameVO.setApplicationStatus("Other");
				idNameVO.setApplicationStatusId(0l);        
			}
		}
		idNameVO.setCount(idNameVO.getCount()+othersCount);
		idNameVO.setActualCount(idNameVO.getActualCount() + othersActualCount); 
		campDetailsRepresentative.add(idNameVO);
		if(idNameVOs.size() > 0 ){    
			pubRepDtls.add(idNameVOs);
		}
		if(campDetailsRepresentative.size() > 0){
			pubRepDtls.add(campDetailsRepresentative);
		}
		return pubRepDtls;
	}catch(Exception e){  
		e.printStackTrace();
		LOG.error("Error occured at getStateLevelCampDetailsRepresentatative() in CoreDashBoardMainService ",e); 
	}
	return null;
}

public List<List<IdNameVO>> getDistrictWiseCampAttendedMembers(List<Long> programIdList,Long stateId,String dateStr,List<Long> enrollmentYrIds){
	LOG.info(" entered in to getDistrictWiseCampAttendedMembers() of CoreDashBoardMainService ");
	try{
		
		if(programIdList != null && programIdList.size() == 1 && programIdList.contains(7L))
			return getSpecialDistrictWiseCampAttendedMembers(programIdList,stateId,dateStr);  
		
		Date toDate = null;
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		if(dateStr != null && !dateStr.isEmpty() && dateStr.length() > 0){
			 toDate = sdf.parse(dateStr);  
		}
		Map<Long,String> programIdAndNameMap = new HashMap<Long,String>(0);
		IdNameVO idNameVO = null;
		//programId and invitedprogramCountList map
		Map<Long,List<IdNameVO>> programIdAndInvitedProgramCountMap = new HashMap<Long,List<IdNameVO>>(0);
		//programId and attendedprogramCountList map
		Map<Long,List<IdNameVO>> programIdAndAttendedProgramCountMap = new HashMap<Long,List<IdNameVO>>(0);
		//invitedCountList
		List<IdNameVO> invitedCountList = null;
		//attendedCountList
		List<IdNameVO> attendedCountList = null; 
		List<List<IdNameVO>> listOfIdNameVoList = new ArrayList<List<IdNameVO>>(0);
		List<IdNameVO> idNameVOs = null;
		//distId and distName map for invite->idAndLocationInviteMap
		//distId and count map for invite ->idAndValueInviteMap
		Map<Long,String> idAndLocationInviteMap = new HashMap<Long,String>();
		Map<Long,Long> idAndValueInviteMap = new HashMap<Long,Long>();   
		//distId and distName map for Attended->idAndLocationAttendedMap
		//distId and count map for Attended ->idAndValueAttendedMap
		Map<Long,String> idAndLocationAttendedMap = new HashMap<Long,String>(); 
		Map<Long,Long> idAndValueAttendedMap = new HashMap<Long,Long>();
		
		List<Object[]> distWiseInvitedMembers = trainingCampBatchAttendeeDAO.getDistWiseInvitedMembers(programIdList,stateId,toDate,enrollmentYrIds);
		if(distWiseInvitedMembers != null && distWiseInvitedMembers.size() > 0){
			for(Object [] obj : distWiseInvitedMembers){
				programIdAndNameMap.put(obj[0] != null ? (Long)obj[0] : 0l, obj[1] != null ? obj[1].toString() : "");
				idNameVOs = programIdAndInvitedProgramCountMap.get(obj[0] != null ? (Long)obj[0] : 0l); 
				if(idNameVOs != null){
					idNameVO = new IdNameVO();
					idNameVO.setId(obj[0] != null ? (Long)obj[0] : 0l);
					idNameVO.setName(obj[1] != null ? obj[1].toString() : "");
					idNameVO.setDistrictid(obj[2] != null ? (Long)obj[2] : 0l);
					idNameVO.setDistrictName(obj[3] != null ? obj[3].toString() : "");
					idNameVO.setCount(obj[4] != null ? (Long)obj[4] : 0l);
					idNameVOs.add(idNameVO);
					programIdAndInvitedProgramCountMap.put(obj[0] != null ? (Long)obj[0] : 0l, idNameVOs);
				}else{
					idNameVOs = new ArrayList<IdNameVO>(0);
					idNameVO = new IdNameVO();
					idNameVO.setId(obj[0] != null ? (Long)obj[0] : 0l);
					idNameVO.setName(obj[1] != null ? obj[1].toString() : "");
					idNameVO.setDistrictid(obj[2] != null ? (Long)obj[2] : 0l);
					idNameVO.setDistrictName(obj[3] != null ? obj[3].toString() : "");
					idNameVO.setCount(obj[4] != null ? (Long)obj[4] : 0l);
					idNameVOs.add(idNameVO);
					programIdAndInvitedProgramCountMap.put(obj[0] != null ? (Long)obj[0] : 0l, idNameVOs);
				}
			}
		} 
		List<Object[]> destWiseAttendedMembers = trainingCampAttendanceDAO.getDestWiseAttendedMembers(programIdList,stateId,toDate,enrollmentYrIds); 
		if(destWiseAttendedMembers != null && destWiseAttendedMembers.size() > 0){
			for(Object [] obj : destWiseAttendedMembers){
				idNameVOs = programIdAndAttendedProgramCountMap.get(obj[0] != null ? (Long)obj[0] : 0l);
				if(idNameVOs != null){  
					idNameVO = new IdNameVO();
					idNameVO.setId(obj[0] != null ? (Long)obj[0] : 0l);
					idNameVO.setName(obj[1] != null ? obj[1].toString() : "");
					idNameVO.setDistrictid(obj[2] != null ? (Long)obj[2] : 0l);
					idNameVO.setDistrictName(obj[3] != null ? obj[3].toString() : "");
					idNameVO.setCount(obj[4] != null ? (Long)obj[4] : 0l);
					idNameVOs.add(idNameVO);
					programIdAndAttendedProgramCountMap.put(obj[0] != null ? (Long)obj[0] : 0l, idNameVOs);
				}else{
					idNameVOs = new ArrayList<IdNameVO>(0);
					idNameVO = new IdNameVO();
					idNameVO.setId(obj[0] != null ? (Long)obj[0] : 0l);
					idNameVO.setName(obj[1] != null ? obj[1].toString() : "");
					idNameVO.setDistrictid(obj[2] != null ? (Long)obj[2] : 0l);
					idNameVO.setDistrictName(obj[3] != null ? obj[3].toString() : "");
					idNameVO.setCount(obj[4] != null ? (Long)obj[4] : 0l);
					idNameVOs.add(idNameVO);
					programIdAndAttendedProgramCountMap.put(obj[0] != null ? (Long)obj[0] : 0l, idNameVOs);
				}  
			}
		}
		if(programIdAndInvitedProgramCountMap.size() > 0){
			//Set<Long> programIdSet = programIdAndInvitedProgramCountMap.keySet();
			for(Long programId : programIdAndInvitedProgramCountMap.keySet()){
				idAndLocationInviteMap.clear();
				idAndValueInviteMap.clear();
				idAndLocationAttendedMap.clear();
				idAndValueAttendedMap.clear();
				invitedCountList = programIdAndInvitedProgramCountMap.get(programId);
				
				if(invitedCountList != null && invitedCountList.size() > 0){  
					for(IdNameVO obj : invitedCountList){
						idAndLocationInviteMap.put(obj.getDistrictid(), obj.getDistrictName());
						idAndValueInviteMap.put(obj.getDistrictid(), obj.getCount());
					}
				}
				attendedCountList = programIdAndAttendedProgramCountMap.get(programId);
				if(attendedCountList != null && attendedCountList.size() > 0){
					for(IdNameVO obj : attendedCountList){
						idAndLocationAttendedMap.put(obj.getDistrictid(), obj.getDistrictName());
						idAndValueAttendedMap.put(obj.getDistrictid(), obj.getCount()); 
					}
				}
				Set<Long> distIdList = idAndLocationInviteMap.keySet();
				if(distIdList != null && distIdList.size() > 0){
					idNameVOs = new ArrayList<IdNameVO>(0);
					Map<Long,IdNameVO> districtsMap = new HashMap<Long, IdNameVO>(0);
					for(Long distId : distIdList){
						idNameVO = new IdNameVO();
						
						idNameVO.setDistrictid(distId);
						idNameVO.setName(idAndLocationInviteMap.get(distId));
						idNameVO.setCount(idAndValueInviteMap.get(distId));
						idNameVO.setActualCount(idAndValueAttendedMap.get(distId) != null ? idAndValueAttendedMap.get(distId) : 0l);
						idNameVO.setApplicationStatus(programIdAndNameMap.get(programId));
						idNameVO.setApplicationStatusId(programId);  
						//idNameVOs.add(idNameVO);
						districtsMap.put(distId,idNameVO);
					}
					
					for(int k=11;k<=23;k++){//for Andra Pradesh Districts
						IdNameVO idNameVO1 = districtsMap.get(Long.valueOf(String.valueOf(k)));
						if(idNameVO1 != null)
							idNameVOs.add(idNameVO1);									
					}
					
					for(int k=1;k<=10;k++){//for Telangana Districts
						IdNameVO idNameVO1 = districtsMap.get(Long.valueOf(String.valueOf(k)));
						if(idNameVO1 != null)
							idNameVOs.add(idNameVO1);									
					}
				}
				listOfIdNameVoList.add(idNameVOs);   
				
			}   
		}
		if(listOfIdNameVoList != null && listOfIdNameVoList.size() > 0){
			Collections.sort(listOfIdNameVoList, new Comparator<List<IdNameVO>>(){
				public int compare(List<IdNameVO> obj1, List<IdNameVO> obj2){
					if(obj1.get(0).getApplicationStatusId() == obj2.get(0).getApplicationStatusId()){
						return 0;
					}else{
						return obj1.get(0).getApplicationStatusId() > obj2.get(0).getApplicationStatusId() ? -1 : 1;
					}
				}
			});    
		}
		return listOfIdNameVoList;
	}catch(Exception e){
		e.printStackTrace();
		LOG.error("Error occured at getDistrictWiseCampAttendedMembers() in CoreDashBoardMainService ",e); 
	}
	return null;
}

public List<List<IdNameVO>> getSpecialDistrictWiseCampAttendedMembers(List<Long> programIdList,Long stateId,String dateStr){
	LOG.info(" entered in to getDistrictWiseCampAttendedMembers() of CoreDashBoardMainService ");
	try{
		Date toDate = null;
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		if(dateStr != null && !dateStr.isEmpty() && dateStr.length() > 0){
			 toDate = sdf.parse(dateStr);  
		}
		Map<Long,String> programIdAndNameMap = new HashMap<Long,String>(0);
		IdNameVO idNameVO = null;
		//programId and invitedprogramCountList map
				Map<Long,List<IdNameVO>> programIdAndInvitedProgramCountMap = new HashMap<Long,List<IdNameVO>>(0);
				//day wise program performance
				Map<String,Map<Long,List<IdNameVO>>> daywiseProgramIdAndAttendedProgramCountMap = new HashMap<String,Map<Long,List<IdNameVO>>>(0);
				//programId and attendedprogramCountList map
				//Map<Long,List<IdNameVO>> programIdAndAttendedProgramCountMap = new HashMap<Long,List<IdNameVO>>(0);
				//invitedCountList
				List<IdNameVO> invitedCountList = null;
				//attendedCountList
				List<IdNameVO> attendedCountList = null; 
				List<List<IdNameVO>> listOfIdNameVoList = new ArrayList<List<IdNameVO>>(0);
				List<IdNameVO> idNameVOs = null;
				//distId and distName map for invite->idAndLocationInviteMap
				//distId and count map for invite ->idAndValueInviteMap
				Map<Long,String> idAndLocationInviteMap = new HashMap<Long,String>();
				Map<Long,Long> idAndValueInviteMap = new HashMap<Long,Long>();   
				//distId and distName map for Attended->idAndLocationAttendedMap
				//distId and count map for Attended ->idAndValueAttendedMap
				Map<Long,String> idAndLocationAttendedMap = new HashMap<Long,String>(); 
				Map<Long,Long> idAndValueAttendedMap = new HashMap<Long,Long>();
				
				List<Object[]> distWiseInvitedMembers = trainingCampBatchAttendeeDAO.getDistWiseInvitedMembers(programIdList,stateId,toDate,null);
				if(distWiseInvitedMembers != null && distWiseInvitedMembers.size() > 0){
					for(Object [] obj : distWiseInvitedMembers){
						programIdAndNameMap.put(obj[0] != null ? (Long)obj[0] : 0l, obj[1] != null ? obj[1].toString() : "");
						idNameVOs = programIdAndInvitedProgramCountMap.get(obj[0] != null ? (Long)obj[0] : 0l); 
						if(idNameVOs != null){
							idNameVO = new IdNameVO();
							idNameVO.setId(obj[0] != null ? (Long)obj[0] : 0l);
							idNameVO.setName(obj[1] != null ? obj[1].toString() : "");
							idNameVO.setDistrictid(obj[2] != null ? (Long)obj[2] : 0l);
							idNameVO.setDistrictName(obj[3] != null ? obj[3].toString() : "");
							idNameVO.setCount(obj[4] != null ? (Long)obj[4] : 0l);
							idNameVOs.add(idNameVO);
							programIdAndInvitedProgramCountMap.put(obj[0] != null ? (Long)obj[0] : 0l, idNameVOs);
						}else{
							idNameVOs = new ArrayList<IdNameVO>(0);
							idNameVO = new IdNameVO();
							idNameVO.setId(obj[0] != null ? (Long)obj[0] : 0l);
							idNameVO.setName(obj[1] != null ? obj[1].toString() : "");
							idNameVO.setDistrictid(obj[2] != null ? (Long)obj[2] : 0l);
							idNameVO.setDistrictName(obj[3] != null ? obj[3].toString() : "");
							idNameVO.setCount(obj[4] != null ? (Long)obj[4] : 0l);
							idNameVOs.add(idNameVO);
							programIdAndInvitedProgramCountMap.put(obj[0] != null ? (Long)obj[0] : 0l, idNameVOs);
						}
					}
				} 
				List<Object[]> destWiseAttendedMembers = trainingCampAttendanceDAO.getDestictWiseAttendedMembers(programIdList,stateId,toDate); 
				if(destWiseAttendedMembers != null && destWiseAttendedMembers.size() > 0){
					
					for(Object [] obj : destWiseAttendedMembers){
						String date = commonMethodsUtilService.getStringValueForObject(obj[5]);
						Map<Long,List<IdNameVO>> programIdAndAttendedProgramCountMap = new HashMap<Long,List<IdNameVO>>(0);
						
						if(daywiseProgramIdAndAttendedProgramCountMap.get(date) != null){
							programIdAndAttendedProgramCountMap = daywiseProgramIdAndAttendedProgramCountMap.get(date);
						}
						
						idNameVOs = programIdAndAttendedProgramCountMap.get(obj[0] != null ? (Long)obj[0] : 0l);
						if(idNameVOs != null){  
							idNameVO = new IdNameVO();
							idNameVO.setId(obj[0] != null ? (Long)obj[0] : 0l);
							idNameVO.setName(obj[1] != null ? obj[1].toString() : "");
							idNameVO.setDistrictid(obj[2] != null ? (Long)obj[2] : 0l);
							idNameVO.setDistrictName(obj[3] != null ? obj[3].toString() : "");
							idNameVO.setCount(obj[4] != null ? (Long)obj[4] : 0l);
							idNameVOs.add(idNameVO);
							programIdAndAttendedProgramCountMap.put(obj[0] != null ? (Long)obj[0] : 0l, idNameVOs);
						}else{
							idNameVOs = new ArrayList<IdNameVO>(0);
							idNameVO = new IdNameVO();
							idNameVO.setId(obj[0] != null ? (Long)obj[0] : 0l);
							idNameVO.setName(obj[1] != null ? obj[1].toString() : "");
							idNameVO.setDistrictid(obj[2] != null ? (Long)obj[2] : 0l);
							idNameVO.setDistrictName(obj[3] != null ? obj[3].toString() : "");
							idNameVO.setCount(obj[4] != null ? (Long)obj[4] : 0l);
							idNameVOs.add(idNameVO);
							programIdAndAttendedProgramCountMap.put(obj[0] != null ? (Long)obj[0] : 0l, idNameVOs);
						} 
						
						daywiseProgramIdAndAttendedProgramCountMap.put(date, programIdAndAttendedProgramCountMap);
					}
				}
				if(programIdAndInvitedProgramCountMap.size() > 0){
					//Set<Long> programIdSet = programIdAndInvitedProgramCountMap.keySet();
					for(Long programId : programIdAndInvitedProgramCountMap.keySet()){
						idAndLocationInviteMap.clear();
						idAndValueInviteMap.clear();
						idAndLocationAttendedMap.clear();
						idAndValueAttendedMap.clear();
						invitedCountList = programIdAndInvitedProgramCountMap.get(programId);
						
						if(invitedCountList != null && invitedCountList.size() > 0){  
							for(IdNameVO obj : invitedCountList){
								idAndLocationInviteMap.put(obj.getDistrictid(), obj.getDistrictName());
								idAndValueInviteMap.put(obj.getDistrictid(), obj.getCount());
							}
						}
						
						if(commonMethodsUtilService.isMapValid(daywiseProgramIdAndAttendedProgramCountMap)){
							int i=1;
							idNameVOs = new ArrayList<IdNameVO>(0);
							for (String Datestr : daywiseProgramIdAndAttendedProgramCountMap.keySet()) {
								IdNameVO idNamVO = new IdNameVO();
								idNamVO.setName("DAY-"+i);
								idNamVO.setDateStr(Datestr);
								
								Map<Long,List<IdNameVO>> programIdAndAttendedProgramCountMap = daywiseProgramIdAndAttendedProgramCountMap.get(Datestr);
								
								attendedCountList = programIdAndAttendedProgramCountMap.get(programId);
								if(attendedCountList != null && attendedCountList.size() > 0){
									for(IdNameVO obj : attendedCountList){
										idAndLocationAttendedMap.put(obj.getDistrictid(), obj.getDistrictName());
										idAndValueAttendedMap.put(obj.getDistrictid(), obj.getCount()); 
									}
								}
								Set<Long> distIdList = idAndLocationInviteMap.keySet();
								List<IdNameVO> idNameVOsList = new ArrayList<IdNameVO>(0);
								Map<Long,IdNameVO> districtsMap = new HashMap<Long,IdNameVO>(0);
								if(distIdList != null && distIdList.size() > 0){
									
									for(Long distId : distIdList){
										idNameVO = new IdNameVO();
										
										idNameVO.setDistrictid(distId);
										idNameVO.setName(idAndLocationInviteMap.get(distId));
										idNameVO.setCount(idAndValueInviteMap.get(distId));
										idNameVO.setActualCount(idAndValueAttendedMap.get(distId) != null ? idAndValueAttendedMap.get(distId) : 0l);
										idNameVO.setApplicationStatus(programIdAndNameMap.get(programId));
										idNameVO.setApplicationStatusId(programId);  
										//idNameVOsList.add(idNameVO);
										districtsMap.put(distId, idNameVO);
									}
									
									for(int k=11;k<=23;k++){//for Andra Pradesh Districts
										IdNameVO idNameVO1 = districtsMap.get(Long.valueOf(String.valueOf(k)));
										if(idNameVO1 != null)
											idNameVOsList.add(idNameVO1);										
									}
									
									for(int k=1;k<=10;k++){//for Telangana Districts
										IdNameVO idNameVO1 = districtsMap.get(Long.valueOf(String.valueOf(k)));
										if(idNameVO1 != null)
											idNameVOsList.add(idNameVO1);									
									}
									
									idNamVO.setSubList1(idNameVOsList);
								}
								idNameVOs.add(idNamVO);
								
								i=i+1;
							}
						}
						
						listOfIdNameVoList.add(idNameVOs);   
					}   
				}
				/*if(listOfIdNameVoList != null && listOfIdNameVoList.size() > 0){
					Collections.sort(listOfIdNameVoList, new Comparator<List<IdNameVO>>(){
						public int compare(List<IdNameVO> obj1, List<IdNameVO> obj2){
							if(obj1.get(0).getApplicationStatusId() == obj2.get(0).getApplicationStatusId()){
								return 0;
							}else{
								return obj1.get(0).getApplicationStatusId() > obj2.get(0).getApplicationStatusId() ? -1 : 1;
							}
						}
					});    
				}*/
				return listOfIdNameVoList;
			}catch(Exception e){
				e.printStackTrace();
				LOG.error("Error occured at getDistrictWiseCampAttendedMembers() in CoreDashBoardMainService ",e); 
			}
			return null;
		}
		public List<IdNameVO> getCandidateDtlsPerDist(Long distId, Long programId, Long stateId, String strDate){//String dateStr
			LOG.info(" entered in to getDistrictWiseCampAttendedMembers() of CoreDashBoardMainService ");
			try{
				List<Long> invitedCadreIds = new ArrayList<Long>();
				List<Long> attendedCadreIds = new ArrayList<Long>();
				List<Long> absentCadreIds = new ArrayList<Long>();
				IdNameVO idNameVO = null;
				Long cadreId = null;
				Map<Long,IdNameVO> idAndMemberDtlsMap = new HashMap<Long,IdNameVO>();
				Map<Long,IdNameVO> absentIdAndMemberDtlsMap = new HashMap<Long,IdNameVO>();
				List<IdNameVO> idNameVOs = new ArrayList<IdNameVO>();
				List<Date> datesList  = new ArrayList<Date>(0);
				if(strDate != null && !strDate.equalsIgnoreCase("0"))
					datesList.add(new SimpleDateFormat("yyyy-MM-dd").parse(strDate));
				
				List<Object[]> getInvitedMemberCadreId = trainingCampBatchAttendeeDAO.getInvitedMemberCadreId(distId,programId);
				List<Object[]> getAttendedMemberCadreId = trainingCampAttendanceDAO.getAttendedMemberCadreId(distId,programId,datesList);
				if(getInvitedMemberCadreId != null && getInvitedMemberCadreId.size() > 0){
					for(Object[] obj : getInvitedMemberCadreId){
						invitedCadreIds.add(obj[2] != null ? (Long)obj[2] : 0l);
					}
				}
				if(getAttendedMemberCadreId != null && getAttendedMemberCadreId.size() > 0){
					for(Object[] obj : getAttendedMemberCadreId){
						attendedCadreIds.add(obj[2] != null ? (Long)obj[2] : 0l);
					}
				}
				if(invitedCadreIds.size() > 0){
					for(Long id : invitedCadreIds){
						if(!(attendedCadreIds.contains(id))){
							absentCadreIds.add(id);
						}
					}
				}
				if(attendedCadreIds.size() > 0){
					List<Object[]> destWiseAttendedMembersDesignation = trainingCampAttendanceDAO.getMembersDetails(attendedCadreIds); 
					if(destWiseAttendedMembersDesignation != null && destWiseAttendedMembersDesignation.size() > 0){
				for(Object[] obj : destWiseAttendedMembersDesignation){
					cadreId = obj[0] != null ? (Long)obj[0] : 0l;
					idNameVO = idAndMemberDtlsMap.get(cadreId);
					if(idNameVO != null){
						String status = idNameVO.getStatus();
						if(obj[2] != null){
							status = status+","+obj[2].toString();
							idNameVO.setStatus(status);
							idAndMemberDtlsMap.put(cadreId, idNameVO);
						}else{
							if(obj[3] != null){
								status = status+","+(obj[4] != null ? obj[4].toString() : "")+" "+(obj[3] != null ? obj[3].toString() : "");
								idNameVO.setStatus(status);
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
		/*if(idAndMemberDtlsMap.size() > 0){
			idNameVOs = new ArrayList<IdNameVO>(idAndMemberDtlsMap.values());
				//idAndMemberDtlsMap.clear();
		}*/
		if(absentCadreIds.size() > 0){
			List<Object[]> destWiseAbsaentMembersDesignation = trainingCampAttendanceDAO.getMembersDetails(absentCadreIds);    
			if(destWiseAbsaentMembersDesignation != null && destWiseAbsaentMembersDesignation.size() > 0){
				for(Object[] obj : destWiseAbsaentMembersDesignation){
					cadreId = obj[0] != null ? (Long)obj[0] : 0l;
					idNameVO = absentIdAndMemberDtlsMap.get(cadreId);
					if(idNameVO != null){
						String status = idNameVO.getStatus();
						if(obj[2] != null){
							status = status+","+obj[2].toString();
							idNameVO.setStatus(status);
							absentIdAndMemberDtlsMap.put(cadreId, idNameVO);
						}else{
							if(obj[3] != null){
								status = status+","+(obj[4] != null ? obj[4].toString() : "")+" "+(obj[3] != null ? obj[3].toString() : "");
								idNameVO.setStatus(status);
								absentIdAndMemberDtlsMap.put(cadreId, idNameVO);
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
						absentIdAndMemberDtlsMap.put(cadreId, idNameVO); 
					}
				}
			}
		}  
		if(idAndMemberDtlsMap.size() > 0 || absentIdAndMemberDtlsMap.size()>0){
			idNameVOs.addAll(new ArrayList<IdNameVO>(idAndMemberDtlsMap.values()));
			idNameVOs.addAll(new ArrayList<IdNameVO>(absentIdAndMemberDtlsMap.values()));
			
			List<Object[]> attendedMemberCadreOverviewDtls = trainingCampAttendanceDAO.getAttendedMemberCadreOverview(distId,programId);
			
			if(commonMethodsUtilService.isListOrSetValid(attendedMemberCadreOverviewDtls)){
				Long totalDayscount = Long.valueOf(String.valueOf(attendedMemberCadreOverviewDtls.get(0)[3]));
				Map<String,Long> daywiseAttendedCountMap = new HashMap<String, Long>(0);
				
				for (int i = 1; i <= totalDayscount; i++) {
					daywiseAttendedCountMap.put(i+"", 0L);
				}
				
				for (Object[] param : attendedMemberCadreOverviewDtls) {
					Long count = commonMethodsUtilService.getLongValueForObject(param[3]);
					Long attendencecount = 0L;
					if(daywiseAttendedCountMap.get(count.toString().trim()) != null){
						attendencecount = daywiseAttendedCountMap.get(count.toString().trim());
					}
					/*if(count>1){
						for (int i = 1; i < count; i++) {
							Long tempCount =0L;
							Long previousDayNo=Long.valueOf(String.valueOf(i));
							if(daywiseAttendedCountMap.get(previousDayNo.toString().trim()) != null){
								tempCount = daywiseAttendedCountMap.get(previousDayNo.toString().trim());
							}
							tempCount=tempCount+1;
							daywiseAttendedCountMap.put(previousDayNo.toString().trim(),tempCount);
						}
					}*/
					
					attendencecount =attendencecount+1;
					daywiseAttendedCountMap.put(count.toString().trim(), attendencecount);
				}
				
				List<IdNameVO> daysCountList = new ArrayList<IdNameVO>(0);
				if(commonMethodsUtilService.isMapValid(daywiseAttendedCountMap)){
					for (String dayStr : daywiseAttendedCountMap.keySet()) {
						IdNameVO vo = new IdNameVO();
						vo.setName(dayStr+"-DAY(s)");
						vo.setCount(daywiseAttendedCountMap.get(dayStr));
						daysCountList.add(vo);
					}
				}
				
				Collections.sort(daysCountList, new Comparator<IdNameVO>() {
					public int compare(IdNameVO o1, IdNameVO o2) {
						return o1.getName().compareTo(o2.getName());
					}
				});
				
				idNameVOs.get(0).getIdnameList().addAll(daysCountList);
			}
		}
		
		return idNameVOs;
	}catch(Exception e){
		e.printStackTrace();
		LOG.error("Error occured at getDistrictWiseCampAttendedMembers() in CoreDashBoardMainService ",e); 
	}
	return null;
}

public List<IdNameVO> getLeaderShipCandidateDtlsPerDist(Long userAccessLevelId, List<Long> userAccessLevelValues, Long stateId, Long locationId, String dateStr,List<Long> enrollmentYearIds,List<Long> trainingCampProgramIds){
	LOG.info(" entered in to getDistrictWiseCampAttendedMembers() of CoreDashBoardMainService ");
	
	try{
		IdNameVO idNameVO = null;
		Long cadreId = null;
		Map<Long,IdNameVO> idAndMemberDtlsMap = new HashMap<Long,IdNameVO>();  
		List<IdNameVO> idNameVOs = new ArrayList<IdNameVO>();
		List<Long> invitedCadreIds = new ArrayList<Long>();
		List<Long> attendedCadreIds = new ArrayList<Long>();
		List<Long> absentCadreIds = new ArrayList<Long>();
		String stats = "camp";
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		Date toDate=null;
		Long lctnId=0l;
		String locationType="";
		
		if(dateStr != null && !dateStr.isEmpty() && dateStr.length() > 0){ 
			toDate = sdf.parse(dateStr);  
		}
		
		String strLocationId = null;
		if(locationId != null && locationId>0){
			strLocationId = locationId.toString();
		}
		 Long firstDigit = Long.valueOf(strLocationId.substring(0, 1));
		 if(firstDigit.longValue()==1l){
			 lctnId=Long.valueOf(strLocationId.substring(1, strLocationId.length()));	
			 locationType="District";
		 }else if(firstDigit.longValue()==2l){
			 lctnId=Long.valueOf(strLocationId.substring(1, strLocationId.length()));	
			 locationType="Constituency";
		 }else if(firstDigit.longValue()==3l){
			 lctnId=Long.valueOf(strLocationId.substring(1, strLocationId.length()));	
			 locationType="mandal"; 
		 }else if(firstDigit.longValue()==4l){
			 lctnId=Long.valueOf(strLocationId.substring(1, strLocationId.length()));	
			 locationType="townDivision";  
		 }
		List<Object[]> rtrnElgbleMmbrsObjLst = tdpCommitteeMemberDAO.getTotalEligibleMembersForTrainingCampProgramByUserType(userAccessLevelId, userAccessLevelValues, stateId, stats, lctnId, locationType, null, null,trainingCampProgramIds); 
		List<Object[]> rtrnAttnddMemObjList = trainingCampAttendanceDAO.getTotalAttenedCadresOfTrainingCampProgramByUserType(userAccessLevelId, userAccessLevelValues, stateId, toDate, stats, lctnId,locationType, null,null, enrollmentYearIds,trainingCampProgramIds);
		if(rtrnElgbleMmbrsObjLst != null && rtrnElgbleMmbrsObjLst.size() > 0){  
			for(Object[] obj : rtrnElgbleMmbrsObjLst){
				invitedCadreIds.add(obj[2] != null ? (Long)obj[2] : 0l);
			}
		}
		if(rtrnAttnddMemObjList != null && rtrnAttnddMemObjList.size() > 0){
			for(Object[] obj : rtrnAttnddMemObjList){
				attendedCadreIds.add(obj[2] != null ? (Long)obj[2] : 0l);
			}
		}
		if(invitedCadreIds.size() > 0){
			for(Long id : invitedCadreIds){
				if(!(attendedCadreIds.contains(id))){
					absentCadreIds.add(id);
				}
			}
		}
		if(attendedCadreIds.size() > 0){
			List<Object[]> destWiseAttendedMembersDesignation = trainingCampAttendanceDAO.getMembersDetails(attendedCadreIds); 
			if(destWiseAttendedMembersDesignation != null && destWiseAttendedMembersDesignation.size() > 0){
				for(Object[] obj : destWiseAttendedMembersDesignation){
					cadreId = obj[0] != null ? (Long)obj[0] : 0l;
					idNameVO = idAndMemberDtlsMap.get(cadreId);
					if(idNameVO != null){
						String status = idNameVO.getStatus();
						if(obj[2] != null){
							status = status+","+obj[2].toString();
							idNameVO.setStatus(status);
							idAndMemberDtlsMap.put(cadreId, idNameVO);
						}else{
							if(obj[3] != null){
								status = status+","+(obj[4] != null ? obj[4].toString() : "")+" "+(obj[3] != null ? obj[3].toString() : "");
								idNameVO.setStatus(status);
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
			idNameVOs = new ArrayList<IdNameVO>(idAndMemberDtlsMap.values());
			idAndMemberDtlsMap.clear();
		}
		if(absentCadreIds.size() > 0){
			List<Object[]> destWiseAbsaentMembersDesignation = trainingCampAttendanceDAO.getMembersDetails(absentCadreIds);  
			if(destWiseAbsaentMembersDesignation != null && destWiseAbsaentMembersDesignation.size() > 0){
				for(Object[] obj : destWiseAbsaentMembersDesignation){
					cadreId = obj[0] != null ? (Long)obj[0] : 0l;
					idNameVO = idAndMemberDtlsMap.get(cadreId);
					if(idNameVO != null){
						String status = idNameVO.getStatus();
						if(obj[2] != null){
							status = status+","+obj[2].toString();
							idNameVO.setStatus(status);
							idAndMemberDtlsMap.put(cadreId, idNameVO);
						}else{
							if(obj[3] != null){
								status = status+","+(obj[4] != null ? obj[4].toString() : "")+" "+(obj[3] != null ? obj[3].toString() : "");
								idNameVO.setStatus(status);
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
		LOG.error("Error occured at getLeaderShipCandidateDtlsPerDist() in CoreDashBoardMainService ",e); 
	}
	return null;
	
}

public String getLatestDebate(){
	String time="";
	try {
		Object[] isRescntTime = debateDAO.getLatestDebate();
		if(isRescntTime != null){
		
			time = isRescntTime[0].toString()+"/"+new SimpleDateFormat("yyyy-MM-dd hh:mm a").format((Date)isRescntTime[1]);
		}
	} catch (Exception e) {
		Log.error("Exception raised at getRescentArticleTime", e);
	}
	return time;		
}

public List<CoreDebateVO> getCoreDebateBasicDetailsOfParty(Long partyId,String startDateStr,String endDateStr,String searchType,Long candidateId,List<Long> debateLocationIdList,List<Long> debateParticipantLocationIdList){
	
	List<CoreDebateVO> finalList = new ArrayList<CoreDebateVO>();		
	try{
		
	/*	String startDateStr="26/09/2016";
		String endDateStr="26/09/2016";*/
		Date startDate = null;
		Date endDate   =null;
		
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		
		SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd hh:mm a");
		
		
		if(startDateStr !=null && !startDateStr.trim().isEmpty() && endDateStr !=null && !endDateStr.trim().isEmpty()){
			startDate = sdf.parse(startDateStr);
			endDate = sdf.parse(endDateStr);
		}
		
		List<Long> partyIds = new ArrayList<Long>(0);
		
		partyIds.add(partyId);
		
		Set<Long> debateIds = new LinkedHashSet<Long>(0);
		
		List<Object[]> listObj = null;
	    if((debateLocationIdList != null && debateLocationIdList.size() == 1 && debateLocationIdList.contains(2l)) || (debateParticipantLocationIdList != null && debateParticipantLocationIdList.size() == 1 && debateParticipantLocationIdList.contains(2l))){
	    	listObj = debateParticipantDAO.getPartyWiseOthersDebates(partyIds,startDate,endDate,null,searchType,candidateId,debateLocationIdList,debateParticipantLocationIdList);
	    }else if((debateLocationIdList !=null && debateLocationIdList.size() == 1L && !debateLocationIdList.contains(2L)) || (debateParticipantLocationIdList !=null && debateParticipantLocationIdList.size() == 1L && !debateParticipantLocationIdList.contains(2L))){
	    	listObj = debateParticipantDAO.getPartyWiseDebates(partyIds,startDate,endDate,null,searchType,candidateId,debateLocationIdList,debateParticipantLocationIdList);
	    }else if((debateLocationIdList !=null && debateLocationIdList.size()>1) || (debateParticipantLocationIdList !=null && debateParticipantLocationIdList.size()>1) ){ // 1,2
	      if((debateLocationIdList.contains(2L)) || (debateParticipantLocationIdList.contains(2L))){
	    	 listObj = debateParticipantDAO.getPartyWiseOthersDebates(partyIds,startDate,endDate,null,searchType,candidateId,debateLocationIdList,debateParticipantLocationIdList);
	      }
	      debateLocationIdList.remove(2L);  
	      debateParticipantLocationIdList.remove(2L);
	      List<Object[]> subListObj = debateParticipantDAO.getPartyWiseDebates(partyIds,startDate,endDate,null,searchType,candidateId,debateLocationIdList,debateParticipantLocationIdList);
	      
	      
	      if(listObj !=null && subListObj !=null){
	    	  listObj.addAll(subListObj);
	    	 
	      }else{
	    	  listObj = subListObj;
	    	  
	      }
	    }else{
	    	listObj = debateParticipantDAO.getPartyWiseDebates(partyIds,startDate,endDate,null,searchType,candidateId,debateLocationIdList,debateParticipantLocationIdList);
	    }
	    
		Map<Long,CoreDebateVO> mainMap = new LinkedHashMap<Long, CoreDebateVO>(0);
		//0.candidateId,1.candidateName,2.debateId,3.startTime,4.endTime,5.debateObserverid,6.observer,7.channelId,8.channelName
		//List<Object[]> listObj = debateParticipantDAO.getPartyWiseDebates(partyIds,startDate,endDate,null,searchType,candidateId,debateLocationIdList);
		
		if(listObj !=null && listObj.size()>0){			
			for(Object[] obj:listObj){
				CoreDebateVO VO = new CoreDebateVO();
				if(searchType !=null && !searchType.trim().isEmpty() && (searchType.trim().equalsIgnoreCase("candidate") || searchType.trim().equalsIgnoreCase("debate"))){
					VO.setCandidateId(obj[0] !=null ? (Long)obj[0]:0l);
					VO.setCandidateName(obj[1] !=null ? obj[1].toString():"");
				}
				//VO.setName(obj[1] !=null ? obj[1].toString():"");				
				VO.setId(obj[2] !=null ? (Long)obj[2]:0l);
				VO.setStartTime(obj[3] !=null ? sdf1.format((Date)obj[3]):null);
				VO.setEndTime(obj[4] !=null ? sdf1.format((Date)obj[4]):null);
				VO.setObserverId(obj[5] !=null ? (Long)obj[5]:0l);
				VO.setObserverName(obj[6] !=null ? obj[6].toString():"");
				VO.setCharecterId(obj[7] !=null ? (Long)obj[7]:0l);
				VO.setCharecterName(obj[8] !=null ? obj[8].toString():"");
				VO.setDebateLocationId(obj[9] !=null ? (Long)obj[9]:0l);
				VO.setDebateLocation(obj[10] !=null ? obj[10].toString():"");
				debateIds.add(VO.getId());
				finalList.add(VO);
				//mainMap.put(VO.getId(), VO);
				
				
			}	
		}
		
		// here we are getting the main subjet of the debeate
		Map<Long,List<String>> subjectsMap = new HashMap<Long, List<String>>();
		
		if(debateIds !=null && debateIds.size()>0){
			List<Object[]> subjectsList = debateSubjectDAO.getDebateSubjectDetailsOfList(debateIds);			
			//List<String> debateSubject = new ArrayList<String>(0);
			if(subjectsList != null && subjectsList.size() > 0)
			{
				for(Object[] obj:subjectsList){										
					List<String> subject = subjectsMap.get((Long)obj[0]);					
					if(subject == null){
						subject = new ArrayList<String>();
						subjectsMap.put((Long)obj[0], subject);
					}
					subject.add(obj[2] !=null ? StringEscapeUtils.unescapeJava(obj[2].toString()):"");				
				}
			}
			
			for(CoreDebateVO VO:finalList){				
				List<String> subjects = subjectsMap.get(VO.getId());
				if(subjects !=null){
					VO.setDebateSubject(subjects);
				}				
			}
			
		}
		
		
		
		
		
	}catch(Exception e){
		Log.error("Exception raised at getCoreDebateBasicDetailsOfParty", e);
	}
	return finalList;

}
public CoreDebateVO getMatchVOList2(List<CoreDebateVO> returnList,Long dayId){
	if(returnList == null )
		return null;
	for(CoreDebateVO dayVO:returnList){
		 if(dayVO.getCandidateId().equals(dayId)){
			 return dayVO;
		 }
	}
	return null;
}
public List<IdNameVO> getTrainingProgramMemberDtlsStatusWise(List<Long> programIdList,Long stateId,String dateStr,String status,String designation,Long designationId){
	try{
		DateUtilService dateUtilService = new DateUtilService();
		Date toDate = null;
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		if(dateStr != null && !dateStr.isEmpty() && dateStr.length() > 0){
			toDate = sdf.parse(dateStr);
		}
		Long stateLevelCampId = 0l;
		List<Long> designationIdList = new ArrayList<Long>();
		designationIdList.add(designationId);
		//it contains totol invited cadre ids
		List<Long> totalInvitedCadreId = new ArrayList<Long>(0);
		//it contains total attended cadre ids
		List<Long> totalAttendedCadreId = new ArrayList<Long>();
		//it contains total absent cadre ids
		List<Long> totalAbsentCadreId = new ArrayList<Long>();
		//this block is for Tdp Committee Level
		List<Object[]> membersDesignationDtlsList = null;  
		if(designation.equalsIgnoreCase("tdpCommitteeLevel")){
			List<Object[]> stDistTrainingPrgInvitedDtlsCmtLvL = trainingCampBatchAttendeeDAO.getStDistTrainingPrgInvitedDtlsCmtLvL(stateLevelCampId, programIdList, stateId, toDate,designationIdList);
			List<Object[]> stDistTrainingPrgAttendedDtlsCmtLvL = trainingCampAttendanceDAO.getStDistTrainingPrgAttendedDtlsCmtLvL(stateLevelCampId, programIdList, stateId, toDate,designationIdList);
			//totol invited cadre ids
			if(stDistTrainingPrgInvitedDtlsCmtLvL != null && stDistTrainingPrgInvitedDtlsCmtLvL.size() > 0){
				for(Object[] param : stDistTrainingPrgInvitedDtlsCmtLvL){
					totalInvitedCadreId.add(param[2] != null ? (Long)param[2] : 0l);
				}
			}
			//total attended cadre ids
			if(stDistTrainingPrgAttendedDtlsCmtLvL != null & stDistTrainingPrgAttendedDtlsCmtLvL.size() > 0){
				for(Object[] param : stDistTrainingPrgAttendedDtlsCmtLvL){
					totalAttendedCadreId.add(param[2] != null ? (Long)param[2] : 0l);
				}
			}
			//total absent cadre ids
			totalAbsentCadreId.addAll(totalInvitedCadreId);
			totalAbsentCadreId.removeAll(totalAttendedCadreId);
			
		}
		else if(designation.equalsIgnoreCase("publicRepresentative")){
			List<Object[]> mlaMpInchargeTrainingProgramInvitedDetails = trainingCampBatchAttendeeDAO.getMlaMpInchargeTrngPrgInvitedDtlsPubRep(stateLevelCampId, programIdList, stateId, toDate,designationIdList);
			List<Object[]> mlaMpInchargeTrainingProgramAttendedDetails = trainingCampAttendanceDAO.getMlaMpInchargeTrngPrgAttendedDtlsPubRep(stateLevelCampId, programIdList, stateId, toDate,designationIdList);
			
			//totol invited cadre ids
			if(mlaMpInchargeTrainingProgramInvitedDetails != null && mlaMpInchargeTrainingProgramInvitedDetails.size() > 0){
				for(Object[] param : mlaMpInchargeTrainingProgramInvitedDetails){
					totalInvitedCadreId.add(param[2] != null ? (Long)param[2] : 0l);
				}
			}
			//total attended cadre ids
			if(mlaMpInchargeTrainingProgramAttendedDetails != null & mlaMpInchargeTrainingProgramAttendedDetails.size() > 0){
				for(Object[] param : mlaMpInchargeTrainingProgramAttendedDetails){
					totalAttendedCadreId.add(param[2] != null ? (Long)param[2] : 0l);
				}
			}
			//total absent cadre ids
			totalAbsentCadreId.addAll(totalInvitedCadreId);
			totalAbsentCadreId.removeAll(totalAttendedCadreId);
			
			
		}
		else{
			List<Long> designationIdList1 = new ArrayList<Long>(){{ 
				//other Tdp Committee Level ids
				//5,6,7,8,9,12
				add(5l);add(6l);add(7l);add(8l);add(9l);add(12l);
			}};
			List<Object[]> stDistTrainingPrgInvitedDtlsCmtLvL = trainingCampBatchAttendeeDAO.getStDistTrainingPrgInvitedDtlsCmtLvL(stateLevelCampId, programIdList, stateId, toDate,designationIdList1);
			List<Object[]> stDistTrainingPrgAttendedDtlsCmtLvL = trainingCampAttendanceDAO.getStDistTrainingPrgAttendedDtlsCmtLvL(stateLevelCampId, programIdList, stateId, toDate,designationIdList1);
			List<Long> designationIdList2 = new ArrayList<Long>(){{ 
				add(3l);add(4l);add(5l);add(6l);add(7l);add(8l);add(9l);add(10l);add(11l);
				add(13l);add(14l);add(15l);add(16l);add(17l);add(18l);	add(19l);add(20l);
				add(22l);add(23l);add(24l);add(25l);add(26l);add(27l);add(28l);add(29l);
				
			}};
			//other public representative ids
			//3,4,5,6,7,8,9,10,11,13,14,15,16,17,18,19,20,22,23,24,25,26,27,28,29
			List<Object[]> mlaMpInchargeTrainingProgramInvitedDetails = trainingCampBatchAttendeeDAO.getMlaMpInchargeTrngPrgInvitedDtlsPubRep(stateLevelCampId, programIdList, stateId, toDate,designationIdList2);
			List<Object[]> mlaMpInchargeTrainingProgramAttendedDetails = trainingCampAttendanceDAO.getMlaMpInchargeTrngPrgAttendedDtlsPubRep(stateLevelCampId, programIdList, stateId, toDate,designationIdList2);
			//totol invited cadre ids
			if(stDistTrainingPrgInvitedDtlsCmtLvL != null && stDistTrainingPrgInvitedDtlsCmtLvL.size() > 0){
				for(Object[] param : stDistTrainingPrgInvitedDtlsCmtLvL){
					totalInvitedCadreId.add(param[2] != null ? (Long)param[2] : 0l);
				}
			}  
			if(mlaMpInchargeTrainingProgramInvitedDetails != null && mlaMpInchargeTrainingProgramInvitedDetails.size() > 0){
				for(Object[] param : mlaMpInchargeTrainingProgramInvitedDetails){
					totalInvitedCadreId.add(param[2] != null ? (Long)param[2] : 0l);
				}
			}
			
			//total attended cadre ids
			if(stDistTrainingPrgAttendedDtlsCmtLvL != null & stDistTrainingPrgAttendedDtlsCmtLvL.size() > 0){
				for(Object[] param : stDistTrainingPrgAttendedDtlsCmtLvL){
					totalAttendedCadreId.add(param[2] != null ? (Long)param[2] : 0l);
				}
			}
			if(mlaMpInchargeTrainingProgramAttendedDetails != null & mlaMpInchargeTrainingProgramAttendedDetails.size() > 0){
				for(Object[] param : mlaMpInchargeTrainingProgramAttendedDetails){
					totalAttendedCadreId.add(param[2] != null ? (Long)param[2] : 0l);
				}
			}
			//total absent cadre ids
			totalAbsentCadreId.addAll(totalInvitedCadreId);
			totalAbsentCadreId.removeAll(totalAttendedCadreId);
			
		}  
		if(status.equalsIgnoreCase("ELIGIBLE")){
			membersDesignationDtlsList = trainingCampAttendanceDAO.getMembersDetails(totalInvitedCadreId); 
		}else if(status.equalsIgnoreCase("INVITED")){
			membersDesignationDtlsList = trainingCampAttendanceDAO.getMembersDetails(totalInvitedCadreId); 
		}else if(status.equalsIgnoreCase("ATTENDED")){
			membersDesignationDtlsList = trainingCampAttendanceDAO.getMembersDetails(totalAttendedCadreId); 
		}else{
			membersDesignationDtlsList = trainingCampAttendanceDAO.getMembersDetails(totalAbsentCadreId); 
		}
		Long cadreId = 0l;
		IdNameVO idNameVO = null;
		List<IdNameVO> idNameVOs = new ArrayList<IdNameVO>();
		Map<Long,IdNameVO> idAndMemberDtlsMap = new HashMap<Long,IdNameVO>();
		
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
		
		idNameVOs.addAll(idAndMemberDtlsMap.values());
		if(status.equalsIgnoreCase("ATTENDED")){
			//Update the list on which day he is presented.
			SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd");
			List<Object[]> fromAndToDate = trainingCampBatchDAO.getFromAndToDate(programIdList.get(0));
			String frmDateStr = "";
			String toDateStr = "";
			Date fromDate = null; 
			Date toDt = null;
			if(fromAndToDate != null && fromAndToDate.size() > 0){
				frmDateStr = fromAndToDate.get(0)[0].toString().substring(0, 10);
				toDateStr = fromAndToDate.get(0)[1].toString().substring(0, 10);
				fromDate = sdf1.parse(frmDateStr);
				toDt = sdf1.parse(toDateStr); 
			}
			//total no of days for the training camp.
			List<String> dateStrList = dateUtilService.getDaysBetweenDatesStringFormat(fromDate, toDt);
			List<Date> dateList = new ArrayList<Date>();
			Map<String,Long> dateAndPositionMap = new HashMap<String,Long>();
			Long i = 1l;
			if(dateStrList != null && dateStrList.size() > 0){  
				for(String param : dateStrList){
					dateList.add(sdf1.parse(param));
					dateAndPositionMap.put(param, i);
					i++;
				}
			}
			List<Object[]> dayWisePresentList = trainingCampAttendanceDAO.getDayWisePresent(programIdList, stateId, dateStrList);
			//cadre and how many days he is presect map
			Map<Long,List<String>> cadreIdAndNoOfDayPresentList = new HashMap<Long,List<String>>();
			List<String> datePresentList = null;
			if(dayWisePresentList != null && dayWisePresentList.size() > 0){
				for(Object[] param : dayWisePresentList){
					datePresentList = cadreIdAndNoOfDayPresentList.get(param[1] != null ? (Long)param[1] : 0l);
					if(datePresentList != null){
						datePresentList.add(param[0] != null ? param[0].toString() : "");
						cadreIdAndNoOfDayPresentList.put(param[1] != null ? (Long)param[1] : 0l, datePresentList);
					}else{
						datePresentList = new ArrayList<String>();
						datePresentList.add(param[0] != null ? param[0].toString() : "");
						cadreIdAndNoOfDayPresentList.put(param[1] != null ? (Long)param[1] : 0l, datePresentList);
					}
				}
			}
			//calculate how many days camp continued.
			Long noOfDayCompleted = null;
			String toDayDate = sdf1.format(new Date());
			if(dateAndPositionMap != null && dateAndPositionMap.size() > 0){
				noOfDayCompleted = dateAndPositionMap.get(toDayDate);
				if(noOfDayCompleted == null){
					noOfDayCompleted = Long.valueOf(String.valueOf(dateAndPositionMap.size()));
				}
			}
			Long tdpCadreId = null;
			List<String> actualPresentStatus = null;
			List<String> presentStatusArr = null;
			if(idNameVOs != null && idNameVOs.size() > 0){
				for(IdNameVO nameVO : idNameVOs){
					presentStatusArr = new ArrayList<String>();
					tdpCadreId = nameVO.getId();
					actualPresentStatus = cadreIdAndNoOfDayPresentList.get(tdpCadreId);
					if(actualPresentStatus != null){
						for(String dt : dateStrList){
							if(actualPresentStatus.contains(dt)){
								presentStatusArr.add("Yes");
							}else{
								presentStatusArr.add("No");
							}
						}
					}
					nameVO.setSubList(presentStatusArr);
					nameVO.setCount(noOfDayCompleted);
				}
			}
		}
		
		return idNameVOs;
	}catch(Exception e){
		e.printStackTrace();
		Log.error("Exception raised at getTrainingProgramMemberDtlsStatusWise of coreDashboardMainService", e);
	}
	return null;  
}
public List<List<IdNameVO>> getStateLevelCampDetailsDayWise(List<Long> programIdList, Long stateId, String dateStr){
	try{
		Date date = null;
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		List<List<IdNameVO>> idNameVOsList = new ArrayList<List<IdNameVO>>();
		if(dateStr != null && dateStr.length() > 0 && !dateStr.isEmpty()){
			date = sdf.parse(dateStr);
		}
		List<String> dateStrList = new ArrayList<String>();
		dateStrList.add(dateStr);
		List<Object[]> totalInvitedForTrainingCamp = trainingCampBatchAttendeeDAO.getTotalInvitedCadreIdForTrainingCampStateLevel(programIdList, stateId, date);
		List<Object[]> totalAttendedMembers = trainingCampAttendanceDAO.getDayWisePresent(programIdList, stateId, dateStrList);
		//collect invited cadreIds
		List<Long> invitedCadreIds = new ArrayList<Long>();
		if(totalInvitedForTrainingCamp != null && totalInvitedForTrainingCamp.size() > 0){
			for(Object[] param : totalInvitedForTrainingCamp){
				invitedCadreIds.add(param[2] != null ? (Long)param[2] : 0l);
			}
		}
		//collect attended cadreIds
		List<Long> attendedCadreIds = new ArrayList<Long>();
		if(totalAttendedMembers != null && totalAttendedMembers.size() > 0){
			for(Object[] param : totalAttendedMembers){
				attendedCadreIds.add(param[1] != null ? (Long)param[1] : 0l);
			}
		}
		//collect absent cadreIds
		List<Long> absentCadreIds = new ArrayList<Long>();
		absentCadreIds.addAll(invitedCadreIds);
		absentCadreIds.removeAll(attendedCadreIds);
		
		//prepare a list if idnamevo for attended members.
		Long cadreId = 0l;
		IdNameVO idNameVO = null;
		List<IdNameVO> idNameVOs = new ArrayList<IdNameVO>();
		Map<Long,IdNameVO> idAndMemberDtlsMap = new HashMap<Long,IdNameVO>();
		List<Object[]> membersDesignationDtlsList = null;
		if(attendedCadreIds != null && attendedCadreIds.size() > 0){
			membersDesignationDtlsList = trainingCampAttendanceDAO.getMembersDetails(attendedCadreIds);
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
						idNameVO.setWish("attended");
						idAndMemberDtlsMap.put(cadreId, idNameVO); 
					}
				}
			}
			idNameVOs = new ArrayList<IdNameVO>(idAndMemberDtlsMap.values());
			idNameVOsList.add(idNameVOs);
		}
		idAndMemberDtlsMap.clear();
		if(absentCadreIds != null && absentCadreIds.size() > 0){
			membersDesignationDtlsList = trainingCampAttendanceDAO.getMembersDetails(absentCadreIds);
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
						idNameVO.setWish("absent");
						idAndMemberDtlsMap.put(cadreId, idNameVO); 
					}
				}
			}
			idNameVOs = new ArrayList<IdNameVO>(idAndMemberDtlsMap.values());
			idNameVOsList.add(idNameVOs);
		}
		return idNameVOsList;  
	}catch(Exception e){ 
		e.printStackTrace();
		Log.error("Exception raised at getTrainingProgramMemberDtlsStatusWise of coreDashboardMainService", e);
	}
	return null;
}

public List<CoreDebateVO> getCandidateWiseDebateDetailsOfCore(Long partyId,String startDateStr,String endDateStr,Long candidateId,List<Long> debateLocationIdList,List<Long> debateParticipantLocationIdList){
	
	List<CoreDebateVO> finalList = new ArrayList<CoreDebateVO>();		
	try{

		Date startDate = null;
		Date endDate   =null;
		String searchType = "debate";
		
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		
		SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd hh:mm a");
		
		
		if(startDateStr !=null && !startDateStr.trim().isEmpty() && endDateStr !=null && !endDateStr.trim().isEmpty()){
			startDate = sdf.parse(startDateStr);
			endDate = sdf.parse(endDateStr);
		}
		
		List<Long> partyIds = new ArrayList<Long>(0);
		List<Long> candidateIds = new ArrayList<Long>();
		
		partyIds.add(partyId);
		candidateIds.add(candidateId);
		
		Set<Long> debateIds = new LinkedHashSet<Long>(0);
		Map<Long,CoreDebateVO> mainMap = new LinkedHashMap<Long, CoreDebateVO>(0);
		
		List<Object[]> listObj = null;
		if((debateLocationIdList != null && debateLocationIdList.size() == 1 && debateLocationIdList.contains(2l)) || (debateParticipantLocationIdList != null && debateParticipantLocationIdList.size() == 1 && debateParticipantLocationIdList.contains(2l))){
			 listObj = debateParticipantDAO.getPartyAndCandidateWiseOthersDebates(partyIds,startDate,endDate,null,searchType,candidateIds,debateLocationIdList,debateParticipantLocationIdList);
	    }else if((debateLocationIdList !=null && debateLocationIdList.size() == 1L && !debateLocationIdList.contains(2L)) || (debateParticipantLocationIdList !=null && debateParticipantLocationIdList.size() == 1L && !debateParticipantLocationIdList.contains(2L))){
	    	listObj = debateParticipantDAO.getPartyAndCandidateWiseDebates(partyIds,startDate,endDate,null,searchType,candidateIds,debateLocationIdList,debateParticipantLocationIdList);
	    }else if((debateLocationIdList !=null && debateLocationIdList.size()>1) || (debateParticipantLocationIdList !=null && debateParticipantLocationIdList.size()>1) ){ // 1,2
		      if((debateLocationIdList.contains(2L)) || (debateParticipantLocationIdList.contains(2L))){
		    	  listObj = debateParticipantDAO.getPartyAndCandidateWiseOthersDebates(partyIds,startDate,endDate,null,searchType,candidateIds,debateLocationIdList,debateParticipantLocationIdList);
		      }
		       debateLocationIdList.remove(2L);
		      
		       List<Object[]> subListObj = debateParticipantDAO.getPartyAndCandidateWiseDebates(partyIds,startDate,endDate,null,searchType,candidateIds,debateLocationIdList,debateParticipantLocationIdList);
		      
		      if(listObj !=null && subListObj !=null ){
		    	  listObj.addAll(subListObj);
		      }else{
		    	  listObj= subListObj;
		    	  
		      }
		    }else{
		    	listObj = debateParticipantDAO.getPartyAndCandidateWiseDebates(partyIds,startDate,endDate,null,searchType,candidateIds,debateLocationIdList,debateParticipantLocationIdList);
		    }
		//0.candidateId,1.candidateName,2.debateId,3.startTime,4.endTime,5.debateObserverid,6.observer,7.channelId,8.channelName
		//List<Object[]> listObj = debateParticipantDAO.getPartyAndCandidateWiseDebates(partyIds,startDate,endDate,null,searchType,candidateIds,debateLocationIdList,debateParticipantLocationIdList);
		
		if(listObj !=null && listObj.size()>0){			
			for(Object[] obj:listObj){
				CoreDebateVO VO = new CoreDebateVO();
				
				if(searchType !=null && !searchType.trim().isEmpty() && searchType.trim().equalsIgnoreCase("candidate")){
					VO.setCandidateId(obj[0] !=null ? (Long)obj[0]:0l);
					VO.setCandidateName(obj[1] !=null ? obj[1].toString():"");
				}
				VO.setId(obj[2] !=null ? (Long)obj[2]:0l);//debateId
				VO.setStartTime(obj[3] !=null ? sdf1.format((Date)obj[3]):null);
				VO.setEndTime(obj[4] !=null ? sdf1.format((Date)obj[4]):null);
				VO.setObserverId(obj[5] !=null ? (Long)obj[5]:0l);
				VO.setObserverName(obj[6] !=null ? obj[6].toString():"");
				VO.setCharecterId(obj[7] !=null ? (Long)obj[7]:0l);
				VO.setCharecterName(obj[8] !=null ? obj[8].toString():"");
				VO.setDebateLocationId(obj[9] !=null ? (Long)obj[9]:0l);
				debateIds.add(VO.getId());
				finalList.add(VO);
			}	
		}
		
		// here we are getting the main subjet of the debeate
		Map<Long,List<String>> subjectsMap = new HashMap<Long, List<String>>();
		
		if(debateIds !=null && debateIds.size()>0){	
			//get Subjects Of Debate
			getDebateSubjects(debateIds,subjectsMap,debateLocationIdList);			
			
			for(CoreDebateVO VO:finalList){				
				List<String> subjects = subjectsMap.get(VO.getId());
				if(subjects !=null){
					VO.setDebateSubject(subjects);
				}				
			}
			
		}
		
	}catch(Exception e){
		Log.error("Exception raised at getCoreDebateBasicDetailsOfParty", e);
	}
	return finalList;

}

public void getDebateSubjects(Set<Long> debateIds,Map<Long,List<String>> subjectsMap,List<Long> debateLocationIdList){
	try {
		
		List<Object[]> subjectsList = debateSubjectDAO.getDebateSubjectDetailsOfList(debateIds);			
		if(subjectsList != null && subjectsList.size() > 0)
		{
			for(Object[] obj:subjectsList){										
				List<String> subject = subjectsMap.get((Long)obj[0]);					
				if(subject == null){
					subject = new ArrayList<String>();
					subjectsMap.put((Long)obj[0], subject);
				}
				subject.add(obj[2] !=null ? StringEscapeUtils.unescapeJava(obj[2].toString()):"");				
			}
		}
		
	} catch (Exception e) {
		Log.error("Exception raised at getDebateSubjects", e);
	}
}
public BoothInchargesVO  getUserTypeWiseBoothCommitteesInchargeDetails(Long activityMemberId,String state,String dateString,List<Long> committeeEnrollmentYearsIdsLst){
	  SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
	   BoothInchargesVO inchargeVo = new BoothInchargesVO();
	try{
		   Date startDate = null;
		   Date endDate = null;
		    Long userLocationLevelId = null;
		    Set<Long> userLocationLevelValues = null;
		    
		    List<Object[]> locations = activityMemberAccessLevelDAO.getLocationsByActivityMemberId(activityMemberId);
		    if(locations!=null && locations.size()>0){
		    	userLocationLevelValues = new HashSet<Long>();
		    	for(Object[] obj : locations){
		    		userLocationLevelId = (Long)obj[0];
		    		userLocationLevelValues.add(obj[2]!=null?(Long)obj[2]:0l);
		    	}
		    }
		    Long stateId = coreDashboardGenericService.getStateIdByState(state);
		    if(dateString != null && !dateString.isEmpty()){
		    	// committeeBO.setDate(sdf.parse(dateString));
		    	 String DatesArr[] = dateString.split("-");
		    	 if(DatesArr != null && DatesArr.length>0){
		    		 startDate = sdf.parse(commonMethodsUtilService.getStringValueForObject(DatesArr[0]));
		    		 endDate = sdf.parse(commonMethodsUtilService.getStringValueForObject(DatesArr[1]));
		    	 }
		     }
		    if(userLocationLevelId != null && userLocationLevelId.longValue()==IConstants.PARLIAMENT_LEVEl_ACCESS_ID){
		    	List<Long>	userLocationLevel =constituencyDAO.getConstistuencyWiseParliamentIds(userLocationLevelValues);
		    	userLocationLevelValues.clear();
		    	userLocationLevelValues.addAll(userLocationLevel);
		    	userLocationLevelId = 5l;
		    }
		    Long totalCount= boothInchargeDAO.getBoothAssignInchargeCount("total",userLocationLevelId,userLocationLevelValues,startDate,endDate,committeeEnrollmentYearsIdsLst,null);
		    Long startedCount =boothInchargeDAO.getBoothAssignInchargeCount("started",userLocationLevelId,userLocationLevelValues,startDate,endDate,committeeEnrollmentYearsIdsLst,null);
		    Long completedCount =boothInchargeDAO.getBoothAssignInchargeCount("completed",userLocationLevelId,userLocationLevelValues,startDate,endDate,committeeEnrollmentYearsIdsLst,null);

		    Long notStartedCount = totalCount-(startedCount+completedCount);
		    inchargeVo.setTotalCount(totalCount);
		    inchargeVo.setStartedCount(startedCount);
		    inchargeVo.setCompletedCount(completedCount);
		    inchargeVo.setNotStartedCount(notStartedCount);
		    
		    
	}catch(Exception e){
		Log.error("Exception raised at getUserTypeWiseBoothCommitteesInchargeDetails", e);
	}
	return inchargeVo;
}


public List<BoothInchargesVO> getBoothCommitteeInchargesCount(Long activityMemberId,List<Long> committeeEnrlmntYrIds,String dateString,Long stateId){
	List<BoothInchargesVO> returnList = new ArrayList<BoothInchargesVO>(0);
	try{
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		Date startDate = null;
		Date endDate = null;
		if(dateString != null && !dateString.isEmpty()){
	          // committeeBO.setDate(sdf.parse(dateString));
	           String DatesArr[] = dateString.split("-");
	           if(DatesArr != null && DatesArr.length>0){
	             startDate = sdf.parse(commonMethodsUtilService.getStringValueForObject(DatesArr[0]));
	             endDate = sdf.parse(commonMethodsUtilService.getStringValueForObject(DatesArr[1]));
	            }
	         }
		
		Long locationId = 0L;
		Set<Long> locationValuesSet = new HashSet<Long>(0);
		List<Object[]> rtrnUsrAccssLvlIdAndVlusObjLst=activityMemberAccessLevelDAO.getLocationLevelAndValuesByActivityMembersId(activityMemberId);
	    if(rtrnUsrAccssLvlIdAndVlusObjLst != null && !rtrnUsrAccssLvlIdAndVlusObjLst.isEmpty()){
		   for (Object[] param : rtrnUsrAccssLvlIdAndVlusObjLst) {
			   locationId = commonMethodsUtilService.getLongValueForObject(param[0]);
			   locationValuesSet.add(param[1] != null ? (Long)param[1]:0l);
		   }
	    } 
	   
	    if(locationId != null && locationId.longValue()==IConstants.PARLIAMENT_LEVEl_ACCESS_ID){
	    	 List<Long> locationValues1 =constituencyDAO.getConstistuencyWiseParliamentIds(locationValuesSet);
	    	locationValuesSet.clear();
	    	locationValuesSet.addAll(locationValues1);
	    	locationId = 5l;
	        }
	    
	    List<Object[]> boothInchargs = boothInchargeDAO.getBoothInchargeCountDetails(locationId, locationValuesSet, committeeEnrlmntYrIds, startDate, endDate);
	    setCountForBoothCommitteeIncharges(returnList,"defaultData",0l,null);
	    Map<Long,Long> boothMap = new HashMap<Long,Long>();
	    if(boothInchargs != null && boothInchargs.size() >0){
	    	for(Object[] obj :boothInchargs){
	    		Long cnt =boothMap.get(obj[0]);
	    		
	    		if(cnt != null && cnt.longValue() >0l){
	    			boothMap.put((Long)obj[0], cnt+1l);
	    		}else{
	    			boothMap.put((Long)obj[0], 1l);
	    		}
	    		
	    	}
	    }
	    
	    if(boothMap != null && boothMap.size() >0){
	    	for(Object[] obj :boothInchargs){
	    		Long cnt =boothMap.get(obj[0]);
	    		if(cnt != null && cnt.longValue() >0l){
	    			//boothMap.put((Long)obj[0], cnt+1l);
	    			setCountForBoothCommitteeIncharges(returnList,"matchedVO",cnt,obj);
	    		}else{
	    			//boothMap.put((Long)obj[0], 1l);
	    			setCountForBoothCommitteeIncharges(returnList,"matchedVO",1l,obj);
	    		}
	    	}
	    }
	    
	}catch(Exception e){
		e.printStackTrace();
		Log.error("Exception raised at getBoothCommitteeInchargesCount", e);
	}
	
	return returnList;
}

public void setCountForBoothCommitteeIncharges(List<BoothInchargesVO> returnList,String type,Long cnt,Object[] obj){
	 try{
		 
		 if(type != null && type.equalsIgnoreCase("defaultData")){
			 List<String> boothIncharges = new ArrayList<String>(0);
			 boothIncharges.add("1");
			 boothIncharges.add("2");
			 boothIncharges.add("3");
			 boothIncharges.add("4");
			 boothIncharges.add("5-10");
			 boothIncharges.add("10-Above");
			 for(String str :boothIncharges){
				 BoothInchargesVO vo = new BoothInchargesVO();
				 vo.setBoothInchargesAssnd(str);
				 returnList.add(vo);
			 }
		 }else{
			 BoothInchargesVO matchedVO = null;
			 if(cnt != null && cnt == 1l){
				 matchedVO  = getMatchVO(returnList,"1");
			}else if(cnt != null && cnt == 2l){
				 matchedVO  = getMatchVO(returnList,"2");
			}else if(cnt != null && cnt == 3l){
				 matchedVO  = getMatchVO(returnList,"3");
			}else if(cnt != null && cnt == 4l){
				 matchedVO  = getMatchVO(returnList,"4");
			}else if(cnt != null && cnt >= 5l && cnt <= 10l){
				 matchedVO  = getMatchVO(returnList,"5-10");
			}else if(cnt != null && cnt >= 10l){
				 matchedVO  = getMatchVO(returnList,"10-Above");
			}
			 
			 if(matchedVO != null){
				 Long boothId = (Long)obj[0];
				 matchedVO.getBoothIds().add(boothId);//booths
				 if(matchedVO.getTdpCadreId() != null && matchedVO.getTdpCadreId().longValue() >0)
					 matchedVO.setTdpCadreId(matchedVO.getTdpCadreId()+1l);//no of booth incharges
				 else
					 matchedVO.setTdpCadreId(1l);//no of booth incharges
				 if(obj[2] != null){
					 String gender = (String)obj[2];
					 if(gender.equalsIgnoreCase("M")){
						 if(matchedVO.getMaleCnt() != null && matchedVO.getMaleCnt().longValue() >0)
							 matchedVO.setMaleCnt(matchedVO.getMaleCnt()+1l);
						 else
							 matchedVO.setMaleCnt(1l);
					 }else if(gender.equalsIgnoreCase("F")){
						 if(matchedVO.getFemaleCnt() != null && matchedVO.getFemaleCnt().longValue() >0)
							 matchedVO.setFemaleCnt(matchedVO.getFemaleCnt()+1l);
						 else
							 matchedVO.setFemaleCnt(1l);
					 }
				 }
			 }
		 }
	 }catch(Exception e){
		 e.printStackTrace();
	 }
}

public BoothInchargesVO getMatchVO(List<BoothInchargesVO> returnList,String boothInchrgAss){
	if(returnList == null || returnList.size()==1)
		return null;
	for(BoothInchargesVO boothIncharg:returnList){
		 if(boothIncharg.getBoothInchargesAssnd().equalsIgnoreCase(boothInchrgAss)){
			 return boothIncharg;
		 }
	}
	return null;
}

public void setTotalAttendedAndNonInviteeAttended(List<Object[]> rtrnTtlAttendedMmbrObjList,TrainingCampProgramVO resultVO,String levelType){
	try{
		
		List<Long> inviteeRoles = new ArrayList<Long>();
		inviteeRoles.add(1l);
		inviteeRoles.add(2l);
		inviteeRoles.add(3l);
		
		 if(rtrnTtlAttendedMmbrObjList != null && rtrnTtlAttendedMmbrObjList.size() > 0){
				for (Object[] param : rtrnTtlAttendedMmbrObjList) {
					Long levelId = commonMethodsUtilService.getLongValueForObject(param[3]);
					if(levelType.equalsIgnoreCase("villageWard")){
						if(levelId.longValue() == 6l || levelId.longValue() == 8l){
							/*resultVO.setTotalAttenedCount(resultVO.getTotalAttenedCount()+commonMethodsUtilService.getLongValueForObject(param[2]));
							Long nonInviteeAttnd = resultVO.getTotalAttenedCount()-resultVO.getInviteeAttended();
							resultVO.setNonInviteeAttended(nonInviteeAttnd);*/
							if(!inviteeRoles.contains(commonMethodsUtilService.getLongValueForObject(param[4]))){
								if(resultVO.getNonInviteeAttended() != null )
									resultVO.setNonInviteeAttended(resultVO.getNonInviteeAttended()+commonMethodsUtilService.getLongValueForObject(param[2]));
								else
									resultVO.setNonInviteeAttended(commonMethodsUtilService.getLongValueForObject(param[2]));
							}else{
								if(resultVO.getInviteeAttended() != null )
									resultVO.setInviteeAttended(resultVO.getInviteeAttended()+commonMethodsUtilService.getLongValueForObject(param[2]));
								else
									resultVO.setInviteeAttended(commonMethodsUtilService.getLongValueForObject(param[2]));
							}
							
						}
					}else if(levelType.equalsIgnoreCase("mandalTwnDiv")){
						if(levelId.longValue() == 5l || levelId.longValue() == 7l || levelId.longValue() == 9l){
							/*resultVO.setTotalAttenedCount(resultVO.getTotalAttenedCount()+commonMethodsUtilService.getLongValueForObject(param[2]));
							Long nonInviteeAttnd = resultVO.getTotalAttenedCount()-resultVO.getInviteeAttended();
							resultVO.setNonInviteeAttended(nonInviteeAttnd);*/
							
							if(!inviteeRoles.contains(commonMethodsUtilService.getLongValueForObject(param[4]))){
								if(resultVO.getNonInviteeAttended() != null )
									resultVO.setNonInviteeAttended(resultVO.getNonInviteeAttended()+commonMethodsUtilService.getLongValueForObject(param[2]));
								else
									resultVO.setNonInviteeAttended(commonMethodsUtilService.getLongValueForObject(param[2]));
							}else{
								if(resultVO.getInviteeAttended() != null )
									resultVO.setInviteeAttended(resultVO.getInviteeAttended()+commonMethodsUtilService.getLongValueForObject(param[2]));
								else
									resultVO.setInviteeAttended(commonMethodsUtilService.getLongValueForObject(param[2]));
							}
							
						}
					}
				}
				resultVO.setTotalAttenedCount(resultVO.getInviteeAttended()+resultVO.getNonInviteeAttended());
				resultVO.setTotalNotAttenedCount(resultVO.getTotalEligibleCount()-resultVO.getInviteeAttended());
			}
	}catch(Exception e){
		 LOG.error("Error occured at setElibibleAndAttendedMemberCntToMap() in CoreDashboardMainService {}",e); 
	}
	}

/**
* @param  Long parentActivityMemberId
* @param  Long childUserTypeId
* @param  Long userAccessLevelId
* @param List<Long> userAccessLevelValues
* @param String reportType
*  @param Long stateId 
* @return List<UserTypeVO>
* @author Hymavathi 
* @Description :This Service Method is used to get selected child member and for userType..and For Day wise 
*  @since 10-NOV-2016
*/
public List<UserTypeVO> getSelectedChildTypeMembersForTrainingProgramForDayWise(Long parentActivityMemberId,List<Long> childUserTypeIds,Long locationLevelId,List<Long> locationLevelValues,String reportType,Long stateId,String toDateStr,List<Long> enrollmentYearIds,List<Long> programIdsList){

List<UserTypeVO> resultList = new ArrayList<UserTypeVO>(0);
Map<String,TrainingCampProgramVO> eligibleAndAttendedCntMap = new HashMap<String, TrainingCampProgramVO>(0);  
SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
Date toDate=null;
try{
	if(toDateStr != null && !toDateStr.isEmpty() && toDateStr.length() > 0){
		 toDate = sdf.parse(toDateStr);
	 }
	   //calling generic method to get childActivityMembers and there location level and values
	  ActivityMemberVO activityMemberVO=null;
	  Map<Long,UserTypeVO> childActivityMembersMap=null;
	  
	  
	  Map<Long,Set<Long>> locationLevelIdsMap=null;
	  Map<String,String>     nameForLocationMap=null;
	  if(reportType != null && reportType.equalsIgnoreCase("selectedUserType")){
		 // activityMemberVO= coreDashboardGenericService.getSelectedChildUserTypeMembers(parentActivityMemberId,childUserTypeId);
		  activityMemberVO = coreDashboardGenericService.getRequiredSubLevelActivityMembersDetails(parentActivityMemberId,childUserTypeIds);
		  childActivityMembersMap= activityMemberVO.getActivityMembersMap();
		  locationLevelIdsMap= activityMemberVO.getLocationLevelIdsMap();
	  }else if(reportType != null && reportType.equalsIgnoreCase("directChild")){
		  if(childUserTypeIds != null && childUserTypeIds.size()>0){
			   activityMemberVO = coreDashboardGenericService.getDirectChildActivityMemberCommitteeDetails(parentActivityMemberId,childUserTypeIds.get(0));//activityMemerId,userTypeId
		  }
		   childActivityMembersMap = activityMemberVO.getActivityMembersMap();
		   locationLevelIdsMap = activityMemberVO.getLocationLevelIdsMap();
	  }
	   
	  if(locationLevelIdsMap != null && locationLevelIdsMap.size() > 0){
		  nameForLocationMap = coreDashboardGenericService.getLocationNamesByLocationIds(locationLevelIdsMap);
	  }
	  List<Long> mandalTownDivisionInviteeRoles = new ArrayList<Long>();
	  	mandalTownDivisionInviteeRoles.add(1l);
		mandalTownDivisionInviteeRoles.add(2l);
		mandalTownDivisionInviteeRoles.add(3l);

		List<Long> villageWardInviteeRoles = new ArrayList<Long>();
		villageWardInviteeRoles.add(1l);
		villageWardInviteeRoles.add(3l);
		String programIds = getListToString(programIdsList);
        String enrollmentYrs = getListToString(enrollmentYearIds);
 	    if(locationLevelIdsMap != null && locationLevelIdsMap.size() > 0){
			  for(Entry<Long,Set<Long>> entry:locationLevelIdsMap.entrySet()){
				    Long accessLevelValue =0l;	
				     if(entry.getKey().longValue() == 4l){// user level 4 means parliament constituency in the case of core dashboard
				    	 accessLevelValue = 10l; //region scope 10  means parliament constituency in intermediate table so that we are replacing value
				     }else if(entry.getKey().longValue()==5l){// user level 5 means constituency in the case of core dashboard
				    	 accessLevelValue = 4l;  //region scope 4  means constituency in intermediate table so that we are replacing value
				     }else{
				    	 accessLevelValue = entry.getKey();	 
				     }
				     List<Object[]> returnObjList = trainingCampDetailsInfoDAO.getTrainingCampProgramEligibleAndAttendedMemberLocationWise(accessLevelValue,new ArrayList<Long>(entry.getValue()), toDate,enrollmentYearIds,programIdsList);
					   if(returnObjList != null && returnObjList.size() > 0){
						   for (Object[] param : returnObjList) {
							   String locationLevelAndId = entry.getKey()+"-"+param[0].toString();
							   TrainingCampProgramVO eligibleAndAttendedVO = new TrainingCampProgramVO();
							   eligibleAndAttendedVO.setTotalEligibleCount(commonMethodsUtilService.getLongValueForObject(param[1]));
							   eligibleAndAttendedVO.getLocationList().addAll(getDaysList());
							  // eligibleAndAttendedVO.setInviteeAttended(commonMethodsUtilService.getLongValueForObject(param[2]));
							  // eligibleAndAttendedVO.setTotalNotAttenedCount(commonMethodsUtilService.getLongValueForObject(param[3]));
							   eligibleAndAttendedCntMap.put(locationLevelAndId,eligibleAndAttendedVO);
						}
					   }
					   Map<Long,Map<Long,Long>> batchMemdaysMap = new HashMap<Long, Map<Long,Long>>();
					   
					   List<Long> committeeLvlIds = new ArrayList<Long>();
					   committeeLvlIds.add(5l);
					   committeeLvlIds.add(6l);
					   committeeLvlIds.add(7l);
					   committeeLvlIds.add(8l);
					   committeeLvlIds.add(9l);
					   String fromDate = null;
					   SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd");

					   fromDate = sdf1.format(toDate);
					   DateUtilService dateUtilService = new DateUtilService();
					   Date currentDate = dateUtilService.getCurrentDateAndTime();
					   String toDay = sdf1.format(currentDate);
					   
					   String levelVals = getListToString(new ArrayList<Long>(entry.getValue()));
					   String committeeLvlVals = getListToString(committeeLvlIds);
					   if(entry.getKey().longValue() == 4l)
						   accessLevelValue = 5l;
					 //List<Object[]> totalAttnd = trainingCampBatchAttendeeDAO.getTotalLocationWiseAttendeeCount(programIdsList,1l, toDate,enrollmentYearIds,accessLevelValue,new ArrayList<Long>(entry.getValue()));
					  // List<Object[]> totalAttnd = trainingCampBatchAttendeeDAO.getDayWiseTrainingCampDetailsCount( programIds,fromDate,toDay,enrollmentYrs,1l,committeeLvlVals,accessLevelValue,levelVals);
					   
					   List<Object[]>  trainingCampObj=trainingCampBatchDAO.getTraingCampBatchDetaisByDatesAndProgramIdsAndEnroleMentIds(null,null,enrollmentYearIds,programIdsList);
						List<Long> batchIdsList=new ArrayList<Long>();// adding all batchIds to list
						if(trainingCampObj != null && trainingCampObj.size() >0){
						 	for(Object[] param:trainingCampObj){
						 		Long batchId=commonMethodsUtilService.getLongValueForObject(param[0]);
					  	        batchIdsList.add(batchId);
							}
						}
					   List<Object[]> totalAttnd = getCampDetailsListByFiltering(enrollmentYearIds,programIdsList,batchIdsList);
					   
					   //0 locationId,1 batchId,2 tdpCadreId,3 roleId
					   if(totalAttnd != null && totalAttnd.size() > 0){
						   for (Object[] param : totalAttnd) {
							    Map<Long,Long> memDaysMap = batchMemdaysMap.get(commonMethodsUtilService.getLongValueForObject(param[2]));
							    if(memDaysMap == null){
							    	memDaysMap = new HashMap<Long,Long>();
							    	memDaysMap.put(commonMethodsUtilService.getLongValueForObject(param[0]), 1l);
							    	batchMemdaysMap.put(commonMethodsUtilService.getLongValueForObject(param[2]), memDaysMap);
							    }
							   Long attendedDaysforBatch = memDaysMap.get(commonMethodsUtilService.getLongValueForObject(param[0]));
							    if(attendedDaysforBatch == null){
							    	memDaysMap.put(commonMethodsUtilService.getLongValueForObject(param[0]), 1l);
							    }else{
							    	memDaysMap.put(commonMethodsUtilService.getLongValueForObject(param[0]), attendedDaysforBatch+1l);
							    }
							}
					   }
					   
					   List<TrainingCampProgramVO> daysList = new ArrayList<TrainingCampProgramVO>();
					   
					  // Map<Long,Long> memCnt = new HashMap<Long,Long>();
					   Map<Long,Long> memMaxDayCnt = getMaxDaysAttendedInBatch( batchMemdaysMap , totalAttnd);
					   if(totalAttnd != null && totalAttnd.size() > 0){
						   for (Object[] param : totalAttnd) {
							     accessLevelValue =0l;	
							     if(entry.getKey().longValue() == 4l){// user level 4 means parliament constituency in the case of core dashboard
							    	 accessLevelValue = commonMethodsUtilService.getLongValueForObject(param[13]); //region scope 10  means parliament constituency in intermediate table so that we are replacing value
							     }else if(entry.getKey().longValue()==5l){// user level 5 means constituency in the case of core dashboard
							    	 accessLevelValue = commonMethodsUtilService.getLongValueForObject(param[15]);  //region scope 4  means constituency in intermediate table so that we are replacing value
							     }else if(entry.getKey().longValue()==2l){// user level 5 means constituency in the case of core dashboard
							    	 accessLevelValue = commonMethodsUtilService.getLongValueForObject(param[9]);  //region scope 4  means constituency in intermediate table so that we are replacing value
							     }else if(entry.getKey().longValue()==3l){// user level 5 means constituency in the case of core dashboard
							    	 accessLevelValue = commonMethodsUtilService.getLongValueForObject(param[11]);  //region scope 4  means constituency in intermediate table so that we are replacing value
							     }else if(entry.getKey().longValue()==6l){// user level 5 means constituency in the case of core dashboard
							    	 accessLevelValue = commonMethodsUtilService.getLongValueForObject(param[17]);  //region scope 4  means constituency in intermediate table so that we are replacing value
							     }else if(entry.getKey().longValue()==7l){// user level 5 means constituency in the case of core dashboard
							    	 accessLevelValue = commonMethodsUtilService.getLongValueForObject(param[19]);  //region scope 4  means constituency in intermediate table so that we are replacing value
							     }else if(entry.getKey().longValue()==8l){// user level 5 means constituency in the case of core dashboard
							    	 accessLevelValue = commonMethodsUtilService.getLongValueForObject(param[21]);  //region scope 4  means constituency in intermediate table so that we are replacing value
							     }else if(entry.getKey().longValue()==9l){// user level 5 means constituency in the case of core dashboard
							    	 accessLevelValue = commonMethodsUtilService.getLongValueForObject(param[23]);  //region scope 4  means constituency in intermediate table so that we are replacing value
							     }
							     
							   String locationLevelAndId = entry.getKey()+"-"+accessLevelValue;
							   TrainingCampProgramVO eligibleAndAttendedVO = eligibleAndAttendedCntMap.get(locationLevelAndId);
							   if(eligibleAndAttendedVO != null){
								  // eligibleAndAttendedVO.setTotalAttenedCount(commonMethodsUtilService.getLongValueForObject(param[1]));
								   Long attendedDaysforBatch = memMaxDayCnt.get(commonMethodsUtilService.getLongValueForObject(param[0]));
								   Long levelId = commonMethodsUtilService.getLongValueForObject(param[4]);
								    	TrainingCampProgramVO dayVO = getMatchVOList1(eligibleAndAttendedVO.getLocationList(),attendedDaysforBatch);
								    		if(dayVO != null){
								    			
								    			if(levelId.longValue()==0L && (levelId != 5L && levelId != 7L && levelId != 9L && levelId != 6L && levelId != 8L)){
													dayVO.getOthersIds().add(commonMethodsUtilService.getLongValueForObject(param[0]));
												}else if((levelId.longValue() == 5L || levelId.longValue() == 7L || levelId.longValue() == 9L) && mandalTownDivisionInviteeRoles.contains(commonMethodsUtilService.getLongValueForObject(param[3]))){
													dayVO.getInviteesIds().add(commonMethodsUtilService.getLongValueForObject(param[0]));
												}else if((levelId.longValue() == 6L || levelId.longValue() == 8L) && villageWardInviteeRoles.contains(commonMethodsUtilService.getLongValueForObject(param[3]))){
													dayVO.getInviteesIds().add(commonMethodsUtilService.getLongValueForObject(param[0]));
												}else{
													dayVO.getNonInviteesIds().add(commonMethodsUtilService.getLongValueForObject(param[0]));
												}
								    	}
								   }
							   }
						   }
					   
					   }
			   }  
 	   if(commonMethodsUtilService.isMapValid(eligibleAndAttendedCntMap)){
 		  for(TrainingCampProgramVO eligibleAndAttendedVO:eligibleAndAttendedCntMap.values()){
				for(TrainingCampProgramVO dayVO :eligibleAndAttendedVO.getLocationList()){
					dayVO.setInviteeAttended(Long.valueOf(String.valueOf(dayVO.getInviteesIds().size())));
					dayVO.setNonInviteeAttended(Long.valueOf(String.valueOf(dayVO.getNonInviteesIds().size())));
					dayVO.setOthersCount(Long.valueOf(String.valueOf(dayVO.getOthersIds().size())));
					dayVO.setTotalAttenedCount(dayVO.getInviteeAttended()+dayVO.getNonInviteeAttended());
					
					
					dayVO.getInviteesIds().clear();dayVO.getNonInviteesIds().clear();dayVO.getOthersIds().clear();
				}
			}
		}
	  //Pushing Required Count
	 	  if(childActivityMembersMap != null && childActivityMembersMap.size() > 0){
		      for(UserTypeVO vo:childActivityMembersMap.values()){
		    	  vo.setDaysList(getDaysList());
		    	   for(Long locationValueId:vo.getLocationValuesSet()){
		    		   String key = vo.getLocationLevelId()+"-"+locationValueId;   
		    			 if(eligibleAndAttendedCntMap.get(key) != null){
		    				 TrainingCampProgramVO eligibleAndAttendedVO = eligibleAndAttendedCntMap.get(key);
		    				// vo.setDaysList(eligibleAndAttendedVO.getLocationList());
		    				 Long inviteeAttended = 0l;
		    				 Long nonInviteeAttnd = 0l;
		    				 Long totalAttnd = 0l;
		    				 for(TrainingCampProgramVO locDayVO :eligibleAndAttendedVO.getLocationList()){
		    					 TrainingCampProgramVO memDayVo = getMatchVOList1(vo.getDaysList(), locDayVO.getId());
		    					 if(memDayVo != null){
		    						 inviteeAttended = inviteeAttended+locDayVO.getInviteeAttended();
		    						 nonInviteeAttnd = nonInviteeAttnd+locDayVO.getNonInviteeAttended();
		    						 totalAttnd =totalAttnd+locDayVO.getTotalAttenedCount();
		    						 memDayVo.setNonInviteeAttended(memDayVo.getNonInviteeAttended()+locDayVO.getNonInviteeAttended());
		    						 memDayVo.setInviteeAttended(memDayVo.getInviteeAttended()+locDayVO.getInviteeAttended());
		    						 memDayVo.setTotalAttenedCount(memDayVo.getTotalAttenedCount()+locDayVO.getTotalAttenedCount());
		    						 
		    						 //eligibleAndAttendedVO.setNonInviteeAttended(eligibleAndAttendedVO.getNonInviteeAttended()+locDayVO.getNonInviteeAttended());
									//eligibleAndAttendedVO.setInviteeAttended(eligibleAndAttendedVO.getInviteeAttended()+locDayVO.getInviteeAttended());
									//eligibleAndAttendedVO.setTotalAttenedCount(eligibleAndAttendedVO.getTotalAttenedCount()+locDayVO.getTotalAttenedCount());
		    					 }
		    				 }
		    				 
		    				 
		    				 vo.setTotalEligibleCount(vo.getTotalEligibleCount()+eligibleAndAttendedVO.getTotalEligibleCount()); 
		    				 eligibleAndAttendedVO.setTotalNotAttenedCount(eligibleAndAttendedVO.getTotalEligibleCount()-inviteeAttended);
		    				 vo.setInviteeAttendedCnt(vo.getInviteeAttendedCnt()+inviteeAttended); 
		    				 vo.setNonInviteeAttendedCnt(vo.getNonInviteeAttendedCnt()+nonInviteeAttnd);
		    				 vo.setTotalAttenedCount(vo.getTotalAttenedCount()+totalAttnd);
		    	    		 vo.setTotalNotAttenedCount(Math.abs(vo.getTotalNotAttenedCount()+eligibleAndAttendedVO.getTotalNotAttenedCount()));
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
		    	  //vo.getInviteeAttendedCnt() means who are presidents, vice-presidents and General Secretery only eligible people for trainings
		    	 	 vo.setTotalAttenedCountPer(calculatePercantage(vo.getInviteeAttendedCnt(),vo.getTotalEligibleCount()));  
		    	 	 vo.setTotalNotAttenedCountPer(calculatePercantage(vo.getTotalNotAttenedCount(),vo.getTotalEligibleCount()));
		    	 	for(TrainingCampProgramVO dayVo:vo.getDaysList()){
		    	 		dayVo.setTotalAttenedCountPer(calculatePercantage(dayVo.getInviteeAttended(),vo.getInviteeAttendedCnt()));  //day wise InviteeAttended  Percentage
		    	 	}
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
	LOG.error("Error occured at getSelectedChildTypeMembersForTrainingProgramForDayWise() in CoreDashboardMainService ",e);
}
return resultList;	
}

public List<TrainingCampProgramVO> getDaysList(){
	List<TrainingCampProgramVO> daysList = new ArrayList<TrainingCampProgramVO>();
	TrainingCampProgramVO day1 = new TrainingCampProgramVO();
	day1.setId(1l);
	day1.setName("1 Day");
	TrainingCampProgramVO day2 = new TrainingCampProgramVO();
	day2.setId(2l);
	day2.setName("2 Days");
	TrainingCampProgramVO day3 = new TrainingCampProgramVO();
	day3.setId(3l);
	day3.setName("3 Days");
	daysList.add(day1);
	daysList.add(day2);
	daysList.add(day3);
	return daysList;
}
public TrainingCampProgramVO getMatchVOList1(List<TrainingCampProgramVO> returnList,Long dayId){
		if(returnList == null )
			return null;
		for(TrainingCampProgramVO dayVO:returnList){
			 if(dayVO.getId().equals(dayId)){
				 return dayVO;
			 }
		}
		return null;
}

public Map<Long,Long> getMaxDaysAttendedInBatch(Map<Long,Map<Long,Long>> batchMemdaysMap ,List<Object[]> list){
	Map<Long,Long> memMaxDayCnt = new HashMap<Long,Long>();
	try{
		if(list != null && list.size() > 0){
			   for (Object[] param : list) {
				   Map<Long,Long> memDaysMap = batchMemdaysMap.get(commonMethodsUtilService.getLongValueForObject(param[2]));
				    if(memDaysMap != null){
				    	Long attendedDaysforBatch = memDaysMap.get(commonMethodsUtilService.getLongValueForObject(param[0]));
				    	if(memMaxDayCnt.get(commonMethodsUtilService.getLongValueForObject(param[0])) == null){
				    		memMaxDayCnt.put(commonMethodsUtilService.getLongValueForObject(param[0]), attendedDaysforBatch);
				    	}else if(memMaxDayCnt.get(commonMethodsUtilService.getLongValueForObject(param[0]))< attendedDaysforBatch){
				    		memMaxDayCnt.put(commonMethodsUtilService.getLongValueForObject(param[0]), attendedDaysforBatch);	
					    }
				    }
			   }
			}
		
	}catch(Exception e){
		e.printStackTrace();
		LOG.error("Error occured at getMaxDaysAttendedForBatch() in CoreDashboardMainService ",e);
	}
	
	return memMaxDayCnt;
}
public List<IdNameVO> getStateLevelCampAttendedDetailsDyaWise(List<Long> programIdList, Long stateId, String dateStr,String option, List<Long> enrollYrIds) {
	LOG.info(" entered in to getStateLevelCampAttendedDetails() of CoreDashBoardMainService ");
	try {
		DateUtilService dateUtilService = new DateUtilService();
		Date toDate = null;
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd");
		SimpleDateFormat sdf3 = new SimpleDateFormat("yyyy-MM-dd");
		Date currentDate = dateUtilService.getCurrentDateAndTime();
		String programIds = getListToString(programIdList);
        String enrollmentYrs = getListToString(enrollYrIds);
		if (dateStr != null && !dateStr.isEmpty() && dateStr.length() > 0) {
			toDate = sdf.parse(dateStr);
		}
		IdNameVO idNameVO = null;
		Map<Long, IdNameVO> idAndIdNameVoMap = new HashMap<Long, IdNameVO>();
		List<IdNameVO> idNameVOs = new ArrayList<IdNameVO>();

		List<Object[]> inviteCountList = trainingCampBatchAttendeeDAO
				.getTotalInvitedForTrainingCampStateLevel(programIdList,
						stateId, currentDate, enrollYrIds);
		List<Object[]> attendedCountList = null;
		if (option.equalsIgnoreCase("dayWise") && programIdList.size() == 1
				&& programIdList.get(0) != 6) {
			List<Object[]> fromAndToDate = trainingCampBatchDAO
					.getFromAndToDate(programIdList.get(0));
			String frmDateStr = "";
			String toDateStr = "";
			Date fromDate = null;
			Date toDt = null;
			if (fromAndToDate != null && fromAndToDate.size() > 0) {
				frmDateStr = fromAndToDate.get(0)[0].toString().substring(
						0, 10);
				toDateStr = fromAndToDate.get(0)[1].toString().substring(0,
						10);
				fromDate = sdf1.parse(frmDateStr);
				toDt = sdf1.parse(toDateStr);
			}

			List<String> dateStrList = dateUtilService
					.getDaysBetweenDatesStringFormat(fromDate, toDt);
			List<Date> dateList = new ArrayList<Date>();
			if (dateStrList != null && dateStrList.size() > 0) {
				for (String param : dateStrList) {
					dateList.add(sdf1.parse(param));
				}
			}
			attendedCountList = trainingCampAttendanceDAO.getTotalAttendedForTrainingCampStateLevel(programIdList, stateId, toDate, dateList,option, enrollYrIds);
			if (attendedCountList != null && attendedCountList.size() > 0) {
				for (Object[] param : attendedCountList) {
					idNameVO = new IdNameVO();
					idNameVO.setId(param[0] != null ? (Long) param[0] : 0l);
					idNameVO.setName(param[1] != null ? param[1].toString(): "");
					idNameVO.setDateStr(param[2] != null ? param[2].toString() : "");
					idNameVO.setActualCount(param[3] != null ? (Long) param[3]: 0l);
					if(inviteCountList != null && inviteCountList.size()>0){
					idNameVO.setCount(inviteCountList.get(0)[2] != null ? (Long) inviteCountList.get(0)[2] : 0l);
					}
					idNameVOs.add(idNameVO);
				}
				//return idNameVOs;
			}
		} else {
			attendedCountList = trainingCampAttendanceDAO
					.getTotalAttendedForTrainingCampStateLevel(
							programIdList, stateId, toDate, null, option,
							enrollYrIds);
		}

		if (inviteCountList != null && inviteCountList.size() > 0) {
			for (Object[] obj : inviteCountList) {
				idNameVO = new IdNameVO();
				idNameVO.setId(obj[0] != null ? (Long) obj[0] : 0l);
				idNameVO.setName(obj[1] != null ? obj[1].toString() : "");
				idNameVO.setCount(obj[2] != null ? (Long) obj[2] : 0l);
				idNameVO.setIdnameList(getDaysListTemplate());
				idAndIdNameVoMap.put(obj[0] != null ? (Long) obj[0] : 0l,
						idNameVO);
			}
		}
		if (attendedCountList != null && attendedCountList.size() > 0) {
			for (Object[] obj : attendedCountList) {
				idNameVO = idAndIdNameVoMap
						.get(obj[0] != null ? (Long) obj[0] : 0l);
				if (idNameVO != null) {
					idNameVO.setActualCount(obj[2] != null ? (Long) obj[2]
							: 0l);
				}
			}
		}
		List<Long> mandalTownDivisionInviteeRoles = new ArrayList<Long>();
		mandalTownDivisionInviteeRoles.add(1l);
		mandalTownDivisionInviteeRoles.add(2l);
		mandalTownDivisionInviteeRoles.add(3l);

		List<Long> villageWardInviteeRoles = new ArrayList<Long>();
		villageWardInviteeRoles.add(1l);
		villageWardInviteeRoles.add(3l);
		
		List<Long> loctionVals = new ArrayList<Long>();
		loctionVals.add(stateId);

		String levelVals = "1";
		String committeeLvlVals = "5,6,7,8,9";
		SimpleDateFormat sd = new SimpleDateFormat("yyyy-MM-dd");
		String fromDate = sd.format(toDate);
		
		String today = sd.format(currentDate);
		Long locationScopeId =2L;
        // List<Object[]> invAttndNonInvAttnd =
		// trainingCampBatchAttendeeDAO.getInviteeAttndAndNonInviteeAttnded(programIdList,
		// stateId, toDate,enrollYrIds);
		//List<Object[]> invAttndNonInvAttnd = trainingCampBatchAttendeeDAO.getDayWiseTrainingCampDetailsCount(programIds,fromDate, today, enrollmentYrs, 1L,committeeLvlVals, locationScopeId, levelVals);
		
		List<Object[]>  trainingCampObj=trainingCampBatchDAO.getTraingCampBatchDetaisByDatesAndProgramIdsAndEnroleMentIds(null,null,enrollYrIds,programIdList);
		List<Long> batchIdsList=new ArrayList<Long>();// adding all batchIds to list
		if(trainingCampObj != null && trainingCampObj.size() >0){
		 	for(Object[] param:trainingCampObj){
		 		Long batchId=commonMethodsUtilService.getLongValueForObject(param[0]);
	  	        batchIdsList.add(batchId);
			}
		}
	   List<Object[]> invAttndNonInvAttnd = getCampDetailsListByFiltering(enrollYrIds,programIdList,batchIdsList);
	   
		Map<Long, Map<Long, Long>> batchMemdaysMap = new HashMap<Long, Map<Long, Long>>();
		if (invAttndNonInvAttnd != null && invAttndNonInvAttnd.size() > 0) {
			for (Object[] param : invAttndNonInvAttnd) {
				Map<Long, Long> memDaysMap = batchMemdaysMap.get(commonMethodsUtilService.getLongValueForObject(param[2]));
				if (memDaysMap == null) {
					memDaysMap = new HashMap<Long, Long>(0);
				}
				Long attendedDaysforBatch = memDaysMap.get(commonMethodsUtilService.getLongValueForObject(param[0]));
				if (attendedDaysforBatch == null) {
					memDaysMap.put(commonMethodsUtilService	.getLongValueForObject(param[0]), 1l);
				} else {
					memDaysMap.put(commonMethodsUtilService.getLongValueForObject(param[0]),attendedDaysforBatch + 1l);
				}
				batchMemdaysMap.put(commonMethodsUtilService.getLongValueForObject(param[2]), memDaysMap);
			}
		}
		//Map<Long, Long> totalCountMap = new HashMap<Long, Long>();// tdpCadre adding Count to days
		
		Map<Long, Long> memMaxDayCnt = getCountOfMaxDaysAttendedInBatch(
				batchMemdaysMap, invAttndNonInvAttnd);// Tdp CAdre max days Vo getting
		//Long inviteeAtt = 0l;
		//Long nonInviteeAtt = 0l;
		if (invAttndNonInvAttnd != null && invAttndNonInvAttnd.size() > 0) {
			for (Object[] param : invAttndNonInvAttnd) {
				idNameVO = idAndIdNameVoMap.get(commonMethodsUtilService.getLongValueForObject(param[8]));
				if (idNameVO != null) {
					Long memattendedforBatch = memMaxDayCnt.get(commonMethodsUtilService.getLongValueForObject(param[0]));
					Long levelId = commonMethodsUtilService.getLongValueForObject(param[4]);
					IdNameVO dayVO = getTrainingsMatchVOList(idNameVO.getIdnameList(), memattendedforBatch);
					if(levelId.longValue()==0L && (levelId != 5L && levelId != 7L && levelId != 9L && levelId != 6L && levelId != 8L)){
						dayVO.getOtherIds().add(commonMethodsUtilService.getLongValueForObject(param[0]));
					}else if((levelId.longValue() == 5L || levelId.longValue() == 7L || levelId.longValue() == 9L) && mandalTownDivisionInviteeRoles.contains(commonMethodsUtilService.getLongValueForObject(param[3]))){
						dayVO.getInviteeIds().add(commonMethodsUtilService.getLongValueForObject(param[0]));
					}else if((levelId.longValue() == 6L || levelId.longValue() == 8L) && villageWardInviteeRoles.contains(commonMethodsUtilService.getLongValueForObject(param[3]))){
						dayVO.getInviteeIds().add(commonMethodsUtilService.getLongValueForObject(param[0]));
					}else{
						dayVO.getNonInviteeIds().add(commonMethodsUtilService.getLongValueForObject(param[0]));
					}
				}
			}
		}
		
		if(commonMethodsUtilService.isMapValid(idAndIdNameVoMap)){
			for (Long programId : idAndIdNameVoMap.keySet()) {
				IdNameVO programVO = idAndIdNameVoMap.get(programId);
				if(commonMethodsUtilService.isListOrSetValid(programVO.getIdnameList())){
					for (IdNameVO dayVO : programVO.getIdnameList()) {
						
						dayVO.setInviteeAttnd(Long.valueOf(String.valueOf(dayVO.getInviteeIds().size())));
						dayVO.setNonInviteeAttnd(Long.valueOf(String.valueOf(dayVO.getNonInviteeIds().size())));
						dayVO.setOthersAttnd(Long.valueOf(String.valueOf(dayVO.getOtherIds().size())));
						dayVO.setNonInviteeAttnd(dayVO.getNonInviteeAttnd()+dayVO.getOthersAttnd());
						dayVO.setTotalCount(dayVO.getInviteeAttnd()+dayVO.getNonInviteeAttnd());
						
						programVO.setInviteeAttnd(programVO.getInviteeAttnd()+dayVO.getInviteeAttnd());
						programVO.setNonInviteeAttnd(programVO.getNonInviteeAttnd()+dayVO.getNonInviteeAttnd());
						programVO.setTotalCount(programVO.getInviteeAttnd()+programVO.getNonInviteeAttnd());
						
						dayVO.getInviteeIds().clear();dayVO.getNonInviteeIds().clear();dayVO.getOtherIds().clear();
					}
					
				}
			}
		}
		if(commonMethodsUtilService.isMapValid(idAndIdNameVoMap)){
			for (Long programId : idAndIdNameVoMap.keySet()) {
				IdNameVO programVO = idAndIdNameVoMap.get(programId);
				if(commonMethodsUtilService.isListOrSetValid(programVO.getIdnameList())){
					for (IdNameVO dayVO : programVO.getIdnameList()) {
						dayVO.setPercentage(String.valueOf(calculatePercantage(dayVO.getInviteeAttnd(), programVO.getInviteeAttnd())));
					}
				}
			}
		}
		
		
		idNameVOs = new ArrayList<IdNameVO>(idAndIdNameVoMap.values());
		Collections.sort(idNameVOs, new Comparator<IdNameVO>() {
			public int compare(IdNameVO obj1, IdNameVO obj2) {
				if (obj1.getId() == obj2.getId()) {
					return 0;
				} else {
					return obj1.getId() > obj2.getId() ? -1 : 1;
				}
			}
		});

		return idNameVOs;

	}

	catch (Exception e) {
		e.printStackTrace();
		LOG.error(
				"Error occured at getStateLevelCampAttendedDetailsDyaWise() in CoreDashBoardMainService ",
				e);
	}
	return null;
}

public List<IdNameVO> getDaysListTemplate() {
	List<IdNameVO> daysList = new ArrayList<IdNameVO>();
	IdNameVO day1 = new IdNameVO();
	day1.setId(1l);
	day1.setName("1 Day");
	IdNameVO day2 = new IdNameVO();
	day2.setId(2l);
	day2.setName("2 Days");
	IdNameVO day3 = new IdNameVO();
	day3.setId(3l);
	day3.setName("3 Days");
	daysList.add(day1);
	daysList.add(day2);
	daysList.add(day3);
	return daysList;
}

public String getListToString(List<Long> list) {
	String listString = "";
	Long count = 0l;
	for (Long val : list) {
		count++;
		if(list.size() >count)
			listString += val + ",";
		else
			listString += val;
		
	}

	return listString;
}

public TrainingCampProgramVO getMatchVO(
		List<TrainingCampProgramVO> returnList, Long dayId) {
	if (returnList == null)
		return null;
	for (TrainingCampProgramVO dayVO : returnList) {
		if (dayVO.getId().equals(dayId)) {
			return dayVO;
		}
	}
	return null;
}

public IdNameVO getTrainingsMatchVOList(List<IdNameVO> returnList, Long dayId) {
	if (returnList == null)
		return null;
	for (IdNameVO dayVO : returnList) {
		if (dayVO.getId().equals(dayId)) {
			return dayVO;
		}
	}
	return null;
}

public Map<Long, Long> getCountOfMaxDaysAttendedInBatch(
		Map<Long, Map<Long, Long>> batchMemdaysMap, List<Object[]> list) {
	Map<Long, Long> memMaxDayCnt = new HashMap<Long, Long>();
	try {
		if (list != null && list.size() > 0) {
			for (Object[] param : list) {
				Map<Long, Long> memDaysMap = batchMemdaysMap.get(commonMethodsUtilService.getLongValueForObject(param[2]));
				if (memDaysMap != null) {
					Long attendedDaysforBatch = memDaysMap.get(commonMethodsUtilService.getLongValueForObject(param[0]));
					if (memMaxDayCnt.get(commonMethodsUtilService.getLongValueForObject(param[0])) == null) {
						memMaxDayCnt.put(commonMethodsUtilService.getLongValueForObject(param[0]),attendedDaysforBatch);
					} else if (memMaxDayCnt.get(commonMethodsUtilService.getLongValueForObject(param[0])) < attendedDaysforBatch) {
						memMaxDayCnt.put(commonMethodsUtilService.getLongValueForObject(param[0]),attendedDaysforBatch);
					}
					if (memMaxDayCnt.get(commonMethodsUtilService.getLongValueForObject(param[0])) != null && 
							memMaxDayCnt.get(commonMethodsUtilService.getLongValueForObject(param[0]))>3L)
							memMaxDayCnt.put(commonMethodsUtilService.getLongValueForObject(param[0]),3L);
				}
			}
		}

	} catch (Exception e) {
		e.printStackTrace();
		LOG.error(
				"Error occured at getMaxDaysAttendedForBatch() in CoreDashboardMainService ",
				e);
	}

	return memMaxDayCnt;
}

public void setTotalAttendedAndNonInviteeAttended1(List<Object[]> rtrnTtlAttendedMmbrObjList,TrainingCampProgramVO resultVO, String levelType,Map<Long, Long> memMaxDayCnt) {
	try {

		List<Long> inviteeRoles = new ArrayList<Long>();
		inviteeRoles.add(1l);
		inviteeRoles.add(3l);
		if (levelType.equalsIgnoreCase("mandalTwnDiv"))
			inviteeRoles.add(2l);
		Long inviteeAtt = 0l;
		Long nonInviteeAtt = 0l;

		if (rtrnTtlAttendedMmbrObjList != null && rtrnTtlAttendedMmbrObjList.size() > 0) {
			for (Object[] param : rtrnTtlAttendedMmbrObjList) {
					Long levelId = commonMethodsUtilService.getLongValueForObject(param[4]);
					Long memattendedforBatch = memMaxDayCnt.get(commonMethodsUtilService.getLongValueForObject(param[0]));
					TrainingCampProgramVO dayVO = getMatchVO(resultVO.getLocationList(),memattendedforBatch);
					
					if(levelType.equalsIgnoreCase("villageWard") && (levelId == 0l || (levelId.longValue() != 6l &&  levelId.longValue() != 8l && levelId.longValue() != 5l  && levelId.longValue() != 7l  && levelId.longValue() != 9l))){
						dayVO.getOthersIds().add(commonMethodsUtilService.getLongValueForObject(param[0]));
					}
					else if (levelType.equalsIgnoreCase("villageWard") && (levelId.longValue() == 6l || levelId.longValue() == 8l)) {
						if (inviteeRoles.contains(commonMethodsUtilService.getLongValueForObject(param[3])))
							dayVO.getInviteesIds().add(commonMethodsUtilService.getLongValueForObject(param[0]));
						else
							dayVO.getNonInviteesIds().add(commonMethodsUtilService.getLongValueForObject(param[0]));
						
					}else if (levelType.equalsIgnoreCase("mandalTwnDiv") && (levelId.longValue() == 5l || levelId.longValue() == 7l || levelId.longValue() == 9l)) {
						if (inviteeRoles.contains(commonMethodsUtilService.getLongValueForObject(param[3])))
							dayVO.getInviteesIds().add(commonMethodsUtilService.getLongValueForObject(param[0]));
						else
							dayVO.getNonInviteesIds().add(commonMethodsUtilService.getLongValueForObject(param[0]));
					}
				}
			}
		
		if(commonMethodsUtilService.isListOrSetValid(resultVO.getLocationList())){
			for (TrainingCampProgramVO dayVO : resultVO.getLocationList()) {
				
				dayVO.setInviteeAttended(Long.valueOf(String.valueOf(dayVO.getInviteesIds().size())));
				dayVO.setNonInviteeAttended(Long.valueOf(String.valueOf(dayVO.getNonInviteesIds().size())));
				dayVO.setOthersCount(Long.valueOf(String.valueOf(dayVO.getOthersIds().size())));
				dayVO.setTotalAttenedCount(dayVO.getInviteeAttended()+dayVO.getNonInviteeAttended());
				
				resultVO.setInviteeAttended(resultVO.getInviteeAttended()+dayVO.getInviteeAttended());
				resultVO.setNonInviteeAttended(resultVO.getNonInviteeAttended()+dayVO.getNonInviteeAttended());
				resultVO.setTotalAttenedCount(resultVO.getInviteeAttended()+resultVO.getNonInviteeAttended());
				
				dayVO.getInviteesIds().clear();dayVO.getNonInviteesIds().clear();dayVO.getOthersIds().clear();
			}
		}
		
		if(commonMethodsUtilService.isListOrSetValid(resultVO.getLocationList())){
			for (TrainingCampProgramVO dayVO : resultVO.getLocationList()) {
				dayVO.setTotalAttenedCountPer(calculatePercantage(dayVO.getInviteeAttended(),	resultVO.getInviteeAttended()));
			}
		}
		resultVO.setTotalNotAttenedCount(resultVO.getTotalEligibleCount()- resultVO.getInviteeAttended());
	} catch (Exception e) {
		LOG.error("Error occured at setElibibleAndAttendedMemberCntToMap() in CoreDashboardMainService {}",e);
	}
}

public List<Object[]> getCampDetailsListByFiltering(List<Long> enrollmentYearIds,List<Long> programYearIds,List<Long> batchIdsList){
    List<Object[]> campDetailsList = new ArrayList<Object[]>(0);
    if(batchIdsList != null && batchIdsList.size()>0){
               int filterCount = 50;
               int i = 0; 
               int j = filterCount;
               int maxcount = batchIdsList.size();
               while (maxcount >0){  
                   if(maxcount<filterCount)
                       j = i+maxcount;
                       List<Object[]>  tempList  = trainingCampAttendanceDAO.getDayWiseTrainingCampDetailsCount(enrollmentYearIds,programYearIds,batchIdsList.subList(i, j));//Procedure Call
                      if(commonMethodsUtilService.isListOrSetValid(tempList)){
                        campDetailsList.addAll(tempList);
                      }
                       i=j;
                       maxcount = maxcount-filterCount;
                       j=j+filterCount;
               }
          }
    return campDetailsList;
}//demo
public TrainingCampProgramVO getTrainingCampBasicDetailsCntOverviewDayWise(Long userAccessLevelId, List<Long> userAccessLevelValues,Long stateId, String toDateStr, List<Long> enrollmentYearIds,List<Long> programIdList) {
	TrainingCampProgramVO finalResultVO = new TrainingCampProgramVO();
	Map<Long, TrainingCampProgramVO> trainingCampProgramDtlsMap = new HashMap<Long, TrainingCampProgramVO>();
	SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
	Date toDate = null;
	try {
		if (toDateStr != null && !toDateStr.isEmpty()
				&& toDateStr.length() > 0) {
			toDate = sdf.parse(toDateStr);
		}
		Long accessLevelValue = 0l;
		if (userAccessLevelId.longValue() == 4l) {// user level 4 means parliament constituency in the case of core dashboard
			accessLevelValue = 10l; // region scope 10 means parliament constituency in intermediate table so that we are replacing value
		} else if (userAccessLevelId.longValue() == 5l) {// user level 5
															// means
															// constituency
															// in the case
															// of core
															// dashboard
			accessLevelValue = 4l; // region scope 4 means constituency in
									// intermediate table so that we are
									// replacing value
		} else {
			accessLevelValue = userAccessLevelId;
		}
		List<Long> tdpCommitteeLvlIds = new ArrayList<Long>();
		tdpCommitteeLvlIds.add(6l);
		tdpCommitteeLvlIds.add(8l);
		tdpCommitteeLvlIds.add(5l);
		tdpCommitteeLvlIds.add(7l);
		tdpCommitteeLvlIds.add(9l);

		SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd");
		String fromDate = sdf1.format(toDate);
		DateUtilService dateUtilService = new DateUtilService();
        Date currentDate = dateUtilService.getCurrentDateAndTime();
        String toDay = sdf1.format(currentDate);			
		String levelVals = getListToString(userAccessLevelValues);
		String committeeLvlVals = "5,6,7,8,9";
		String enrollmentYrIds = getListToString(enrollmentYearIds);
		
		
		//List<Long> programIds = trainingCampScheduleDAO.getTrainingCampProgramIds(enrollmentYearIds.get(0));
		//List<Object[]> attendedList = trainingCampBatchAttendeeDAO.getDayWiseTrainingCampDetailsCount(programIds,fromDate,toDay,enrollmentYrIds,1L,committeeLvlVals,userAccessLevelId,levelVals);
		List<Object[]>  trainingCampObj=trainingCampBatchDAO.getTraingCampBatchDetaisByDatesAndProgramIdsAndEnroleMentIds(null,null,enrollmentYearIds,programIdList);
		List<Long> batchIdsList=new ArrayList<Long>();// adding all batchIds to list
		if(trainingCampObj != null && trainingCampObj.size() >0){
		 	for(Object[] param:trainingCampObj){
		 		Long batchId=commonMethodsUtilService.getLongValueForObject(param[0]);
	  	        batchIdsList.add(batchId);
			}
		}
		List<Object[]> attendedList = getCampDetailsListByFiltering(enrollmentYearIds,programIdList,batchIdsList);
		
		List<Object[]> rtrnCommiteeLevelEligibleAndAttendedObjLst = trainingCampDetailsInfoDAO
				.getTrainingCampProgramEligibleAndAttendedMemberCommitteeLevelWise(
						accessLevelValue, userAccessLevelValues, toDate,
						enrollmentYearIds, programIdList);

		Map<Long, Map<Long, Long>> batchMemdaysMap = new HashMap<Long, Map<Long, Long>>();
		if (attendedList != null && attendedList.size() > 0) {
			for (Object[] param : attendedList) {
				Map<Long, Long> memDaysMap = batchMemdaysMap.get(commonMethodsUtilService.getLongValueForObject(param[2]));
				if (memDaysMap == null) {
					memDaysMap = new HashMap<Long, Long>(0);
					memDaysMap.put(commonMethodsUtilService.getLongValueForObject(param[0]), 1l);
					batchMemdaysMap.put(commonMethodsUtilService.getLongValueForObject(param[2]), memDaysMap);
				}
				
				Long attendedDaysforBatch = memDaysMap.get(commonMethodsUtilService.getLongValueForObject(param[0]));
				if (attendedDaysforBatch == null) {
					memDaysMap.put(commonMethodsUtilService.getLongValueForObject(param[0]), 1l);
				} else {
					memDaysMap.put(commonMethodsUtilService.getLongValueForObject(param[0]),attendedDaysforBatch + 1l);
				}
			}
		}
		Map<Long, Long> memMaxDayCnt = getCountOfMaxDaysAttendedInBatch(batchMemdaysMap, attendedList);// Tdp CAdre max days Vo getting
		TrainingCampProgramVO villageWardVO = new TrainingCampProgramVO();
		villageWardVO.setName("Village/Ward");
		setElibibleAndAttendedMemberCntToMap(rtrnCommiteeLevelEligibleAndAttendedObjLst, villageWardVO,	"villageWard");
		villageWardVO.getLocationList().addAll(getDaysList());
		setTotalAttendedAndNonInviteeAttended1(attendedList, villageWardVO,	"villageWard", memMaxDayCnt);
		villageWardVO.setTotalAttenedCountPer(calculatePercantage(villageWardVO.getInviteeAttended(),	villageWardVO.getTotalEligibleCount()));
		villageWardVO.setTotalNotAttenedCountPer(calculatePercantage(villageWardVO.getTotalNotAttenedCount(),villageWardVO.getTotalEligibleCount()));
		finalResultVO.setVillageWardVO(villageWardVO);
		TrainingCampProgramVO manTwnDivVO = new TrainingCampProgramVO();
		manTwnDivVO.setName("Mandal/Town/Division");
		manTwnDivVO.getLocationList().addAll(getDaysList());
		setElibibleAndAttendedMemberCntToMap(rtrnCommiteeLevelEligibleAndAttendedObjLst, manTwnDivVO,"mandalTwnDiv");
		setTotalAttendedAndNonInviteeAttended1(attendedList, manTwnDivVO,"mandalTwnDiv", memMaxDayCnt);
		manTwnDivVO.setTotalAttenedCountPer(calculatePercantage(manTwnDivVO.getInviteeAttended(),	manTwnDivVO.getTotalEligibleCount()));
		manTwnDivVO.setTotalNotAttenedCountPer(calculatePercantage(	manTwnDivVO.getTotalNotAttenedCount(),manTwnDivVO.getTotalEligibleCount()));
		finalResultVO.setMandalTownDivisionVO(manTwnDivVO);

		List<Object[]> rtrnObjLst = trainingCampDetailsInfoDAO.getTrainingCampProgramEligibleAndAttendedDetails(accessLevelValue, userAccessLevelValues, toDate,enrollmentYearIds, programIdList);
		if (rtrnObjLst != null && rtrnObjLst.size() > 0) {
			for (Object[] param : rtrnObjLst) {
				TrainingCampProgramVO programVO = new TrainingCampProgramVO();
				programVO.getLocationList().addAll(getDaysList());
				programVO.setId(commonMethodsUtilService.getLongValueForObject(param[0]));
				programVO.setName(commonMethodsUtilService.getStringValueForObject(param[1]));
				programVO.setTotalEligibleCount(commonMethodsUtilService.getLongValueForObject(param[2]));
				setDayWiseCountToProgramWiseVO(programVO,manTwnDivVO.getLocationList());
				setDayWiseCountToProgramWiseVO(programVO,villageWardVO.getLocationList());
				setInviteeAndNonInviteeCount(programVO);
				programVO.setTotalNotAttenedCount(villageWardVO.getTotalNotAttenedCount()+ manTwnDivVO.getTotalNotAttenedCount());
				programVO.setTotalAttenedCountPer(calculatePercantage(programVO.getInviteeAttended(),programVO.getTotalEligibleCount()));
				programVO.setTotalNotAttenedCountPer(calculatePercantage(programVO.getTotalNotAttenedCount(),	programVO.getTotalEligibleCount()));
				trainingCampProgramDtlsMap.put(programVO.getId(), programVO);
			}
		}  
		if (trainingCampProgramDtlsMap != null	&& trainingCampProgramDtlsMap.size() > 0) {
			finalResultVO.setTrainingProgramList(new ArrayList<TrainingCampProgramVO>(trainingCampProgramDtlsMap.values()));
		}
	} catch (Exception e) {
		LOG.error(
				"Error occured at getTotalEligibleAttendedAndNotAttenedOverviewCount() in CoreDashboardMainService {}",
				e);
	}
	//add leadership skills for other leader
	addLeadershipSkillsForOtherLeader(userAccessLevelId, userAccessLevelValues, stateId,enrollmentYearIds,finalResultVO);
	
	return finalResultVO;
}
public List<Object[]> getCampDetailsListByFilteringAccessLevelWise(Long userAccessLevelId,List<Long> levelValueList,List<Long> enrollmentYearIds,List<Long> programYearIds,List<Long> batchIdsList){
    List<Object[]> campDetailsList = new ArrayList<Object[]>(0);
    if(batchIdsList != null && batchIdsList.size()>0){
               int filterCount = 50;
               int i = 0; 
               int j = filterCount;
               int maxcount = batchIdsList.size();
               while (maxcount >0){  
                   if(maxcount<filterCount)
                       j = i+maxcount;
                       List<Object[]>  tempList  = trainingCampAttendanceDAO.getDayWiseTrainingCampDetailsCountLocationLevelWise(userAccessLevelId,levelValueList,enrollmentYearIds,programYearIds,batchIdsList.subList(i, j));//Procedure Call
                      if(commonMethodsUtilService.isListOrSetValid(tempList)){
                    	  for(Object[] param : tempList){
                    		  if(commonMethodsUtilService.getLongValueForObject(param[26]).longValue() == 2L){
                    			  param[8]=9L;
                    			  campDetailsList.add(param);
                    		  }
                    	  }
                      }
                       i=j;
                       maxcount = maxcount-filterCount;
                       j=j+filterCount;
               }
          }
    return campDetailsList;
}
public void addLeadershipSkillsForOtherLeader(Long userAccessLevelId, List<Long> userAccessLevelValues, Long stateId, List<Long> enrollmentYearIds, TrainingCampProgramVO finalResultVO){
	try{
		List<Object[]> batchIdAndProgramIdList = trainingCampBatchDAO.getProgramIdAndBatchIdListByPassingEnrollmentYearId(enrollmentYearIds.get(0));
		Set<Long> programIdList = new HashSet<Long>();
		Set<Long> catchIdList = new HashSet<Long>();
		if(batchIdAndProgramIdList != null && batchIdAndProgramIdList.size() > 0){
			for(Object[] param : batchIdAndProgramIdList){
				catchIdList.add(commonMethodsUtilService.getLongValueForObject(param[0]));
				programIdList.add(commonMethodsUtilService.getLongValueForObject(param[1]));
			}
		}
		
		Long accessLevelValue = userAccessLevelId;
		
		List<Object[]>  tempList = null;
		List<Object[]>  tempList2 = null;
		if(accessLevelValue.longValue() == IConstants.STATE_LEVEl_ACCESS_ID){
			List<Long> distList1 = new ArrayList<Long>(){{add(11L);add(12L);add(13L);add(14L);add(15L);add(16L);add(17L);}};
			tempList  = getCampDetailsListByFilteringAccessLevelWise(3L,distList1,enrollmentYearIds,new ArrayList<Long>(programIdList),new ArrayList<Long>(catchIdList));
			List<Long> distList2 = new ArrayList<Long>(){{add(18L);add(19L);add(20L);add(21L);add(22L);add(23L);add(517L);}};
			tempList2  = getCampDetailsListByFilteringAccessLevelWise(3L,distList2,enrollmentYearIds,new ArrayList<Long>(programIdList),new ArrayList<Long>(catchIdList));
			tempList.addAll(tempList2);
		}else{
			tempList  = getCampDetailsListByFilteringAccessLevelWise(accessLevelValue,userAccessLevelValues,enrollmentYearIds,new ArrayList<Long>(programIdList),new ArrayList<Long>(catchIdList));
		}
		
		//take total attended.
		List<Object[]> attendedList = new ArrayList<Object[]>();
		buildTotalAttendedProgramWise(attendedList,tempList);
		
		
		
		//create a map for training camp id and cadreId and list of attended date.
		Map<Long,Map<Long,Set<String>>> programIdAndCadreIdAndListOfAttendedDate = new HashMap<Long,Map<Long,Set<String>>>();
		
		Map<Long,Set<Long>> programIdAndTotalBatchList = new HashMap<Long,Set<Long>>();
		
		initializeTrainingCampMap(programIdAndCadreIdAndListOfAttendedDate,programIdAndTotalBatchList,attendedList,"all","camp");
		
		// teke all invite attended cadre list
		Map<Long,Map<Long,Set<String>>> programIdAndInviteeCadreIdAndListOfAttendedDate = new HashMap<Long,Map<Long,Set<String>>>();
		
		
		
		initializeTrainingCampMap(programIdAndInviteeCadreIdAndListOfAttendedDate,null,tempList,"inviteeAttended","special");
		
		//get camp name
		List<Object[]> programNameList = null;
		if(programIdAndTotalBatchList != null && programIdAndTotalBatchList.keySet() != null && programIdAndTotalBatchList.keySet().size() > 0){
			programNameList = trainingCampProgramDAO.getTrainingProgramDetailsByProgramIds(new ArrayList<Long>(programIdAndTotalBatchList.keySet()));
		}
		Map<Long,String> programIdAndNameMap = new HashMap<Long,String>();
		if(programNameList != null && programNameList.size() > 0){
			for(Object[] param : programNameList){
				programIdAndNameMap.put(commonMethodsUtilService.getLongValueForObject(param[0]), commonMethodsUtilService.getStringValueForObject(param[1]));
			}
		}
		
		//build vo for ui
		
		List<TrainingCampProgramVO> finalVO = new ArrayList<TrainingCampProgramVO>();
		if(programIdAndCadreIdAndListOfAttendedDate != null && programIdAndCadreIdAndListOfAttendedDate.size() > 0){
			for(Entry<Long,Map<Long,Set<String>>> param : programIdAndCadreIdAndListOfAttendedDate.entrySet()){
				String programName = programIdAndNameMap.get(param.getKey());
				initializeTotalAttendedVO(finalVO,param.getKey(),programName,param.getValue(),programIdAndTotalBatchList,null);
			}
		}
		
		if(programIdAndInviteeCadreIdAndListOfAttendedDate != null && programIdAndInviteeCadreIdAndListOfAttendedDate.size() > 0){
			for(Entry<Long,Map<Long,Set<String>>> param : programIdAndInviteeCadreIdAndListOfAttendedDate.entrySet()){
				initializeTotalInviteeAttendedVO(finalVO,param.getKey(),param.getValue());
			}
		}
		//push completed and running batches per program
		Map<Long,Map<Long,String>> programIdAndBatchIdAndbetweenDate = new HashMap<Long,Map<Long,String>>();
		if(tempList != null && tempList.size() > 0){
			buildProgramWiseBatchMap(tempList,programIdAndBatchIdAndbetweenDate);
			pushProgramWiseBatch(finalVO,programIdAndBatchIdAndbetweenDate);
		}
		
		if(finalVO != null && finalVO.size() > 0){
			for(TrainingCampProgramVO outerParam : finalVO){
				outerParam.setNonInviteeAttended(outerParam.getTotalAttenedCount() - outerParam.getInviteeAttended());
				List<TrainingCampProgramVO> tempVOList = outerParam.getTrainingProgramList();
				if(tempVOList != null && tempVOList.size() > 0){
					for(TrainingCampProgramVO innerParam : tempVOList){
						innerParam.setOnly1dayCountNonInvited(innerParam.getOnly1dayCount() - innerParam.getOnly1dayCountInvited());
						innerParam.setOnly1dayCountNonInvited(innerParam.getOnly1dayCount() - innerParam.getOnly1dayCountInvited());
						innerParam.setOnly1dayCountNonInvited(innerParam.getOnly1dayCount() - innerParam.getOnly1dayCountInvited());
					}
				}
			}
		}
		
		
		if(finalVO != null && finalVO.size() > 0){
			finalResultVO.setLeaderTrainingList(finalVO);
		}
		
	}catch(Exception e){
		LOG.error("Error occured at addLeadershipSkillsForOtherLeader() in CoreDashboardMainService {}",e);
	}
}
public void pushProgramWiseBatch(List<TrainingCampProgramVO> finalVO,Map<Long,Map<Long,String>> programIdAndBatchIdAndbetweenDate){
	try{
		for(TrainingCampProgramVO campProgramVO : finalVO){
			Long programId = campProgramVO.getId();
			Map<Long,String> batchIdAndBetweenDate = programIdAndBatchIdAndbetweenDate.get(programId);
			if(batchIdAndBetweenDate != null && batchIdAndBetweenDate.size() > 0){
				setBatchStatus(campProgramVO,batchIdAndBetweenDate);
			}
		}
	}catch(Exception e){
		LOG.error("Error occured at pushProgramWiseBatch() in CoreDashboardMainService {}",e);
	}
}
public void setBatchStatus(TrainingCampProgramVO campProgramVO,Map<Long,String> batchIdAndBetweenDate){
	try{
		DateUtilService dateUtilService = new DateUtilService();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Long totalBatch = 0L;
		Long runningBatch = 0L;
		Long completedBatch = 0L;
		Long upcommingBatch = 0L;
		for(Entry<Long,String> param : batchIdAndBetweenDate.entrySet()){
			totalBatch = totalBatch + 1L;
			String[] dateArr = param.getValue().split(",");
			Date fromDate = sdf.parse(dateArr[0]);
			Long fromDateMili = fromDate.getTime();
			Date toDate = sdf.parse(dateArr[1]);
			Long toDateMili = toDate.getTime();
			Date today = dateUtilService.getCurrentDateAndTime();
			Long todayMili = today.getTime();
			
			if(fromDateMili <= todayMili && toDateMili >= todayMili){
				runningBatch = runningBatch + 1L;
			}else if(todayMili > toDateMili){
				completedBatch = completedBatch + 1L;
			}else if(fromDateMili > todayMili){
				upcommingBatch = upcommingBatch + 1L;
			}
		}
		campProgramVO.setTotalBatch(totalBatch);
		campProgramVO.setCompletedBatch(completedBatch);
		campProgramVO.setRunningBatch(runningBatch);
		campProgramVO.setUpcommintbatch(upcommingBatch);
	}catch(Exception e){
		LOG.error("Error occured at setBatchStatus() in CoreDashboardMainService {}",e);
	}
}
public void buildProgramWiseBatchMap(List<Object[]> tempList,Map<Long,Map<Long,String>> programIdAndBatchIdAndbetweenDate){
	try{
		Map<Long,String> batchIdAndBetweenDateMap = null;
		String btnDate = "";
		for(Object[] param : tempList){
			batchIdAndBetweenDateMap = programIdAndBatchIdAndbetweenDate.get(commonMethodsUtilService.getLongValueForObject(param[8]));
			if(batchIdAndBetweenDateMap == null){
				batchIdAndBetweenDateMap = new HashMap<Long,String>();
				programIdAndBatchIdAndbetweenDate.put(commonMethodsUtilService.getLongValueForObject(param[8]), batchIdAndBetweenDateMap);
			}
				String betweenDate = commonMethodsUtilService.getStringValueForObject(param[27]).trim().substring(0, 10)+","+commonMethodsUtilService.getStringValueForObject(param[28]).trim().substring(0, 10);
				batchIdAndBetweenDateMap.put(commonMethodsUtilService.getLongValueForObject(param[2]), betweenDate);
			
		}
	}catch(Exception e){
		LOG.error("Error occured at buildProgramWiseBatchMap() in CoreDashboardMainService {}",e);
	}
}
public void buildTotalAttendedProgramWise(List<Object[]> attendedList, List<Object[]> tempList){
	try{
		Object[] objArr = null;
		if(tempList != null && tempList.size() > 0){
			for(Object[] param : tempList){
				objArr = new Object[4];
				objArr[0] = param[8];
				objArr[1] = param[2];
				objArr[2] = param[0];
				objArr[3] = param[1];
				attendedList.add(objArr);
			}
		}
	}catch(Exception e){
		LOG.error("Error occured at buildTotalAttended() in CoreDashboardMainService {}",e);
	}
}
public void setDayWiseCountToProgramWiseVO(TrainingCampProgramVO programVO,List<TrainingCampProgramVO> list){
	try{
		
		if(list != null && list.size() >0){
			for(TrainingCampProgramVO dayVo :list){
					TrainingCampProgramVO matchedVO = getMatchVO(programVO.getLocationList(),dayVo.getId());
						if(matchedVO != null){
							matchedVO.setNonInviteeAttended(matchedVO.getNonInviteeAttended() + dayVo.getNonInviteeAttended()+dayVo.getOthersCount());
							matchedVO.setOthersCount(matchedVO.getOthersCount()+dayVo.getOthersCount());
							matchedVO.setInviteeAttended(matchedVO.getInviteeAttended() + dayVo.getInviteeAttended());
						} 
						
						matchedVO.setTotalAttenedCount( matchedVO.getNonInviteeAttended()+matchedVO.getInviteeAttended());
						
				}
			}
		
	}catch(Exception e){
		e.printStackTrace();
		LOG.error("Error occured at setDayWiseCountToProgramWiseVO() in CoreDashboardMainService {}",e);
	}
}
public void setInviteeAndNonInviteeCount(TrainingCampProgramVO programVO){
	try{
		
		for(TrainingCampProgramVO dayVo :programVO.getLocationList()){
				programVO.setInviteeAttended(programVO.getInviteeAttended()+dayVo.getInviteeAttended());
				programVO.setNonInviteeAttended(programVO.getNonInviteeAttended()+dayVo.getNonInviteeAttended());
				programVO.setTotalAttenedCount(programVO.getInviteeAttended()+programVO.getNonInviteeAttended());
		}
		for(TrainingCampProgramVO dayVo :programVO.getLocationList()){
			dayVo.setTotalAttenedCountPer(calculatePercantage(dayVo.getInviteeAttended(),	programVO.getInviteeAttended()));
	}
			
	}catch(Exception e){
		e.printStackTrace();
		LOG.error("Error occured at setInviteeAndNonInviteeCount() in CoreDashboardMainService {}",e);
	}
}
public CoreDebateVO getMatchedScaleId(List<CoreDebateVO> returnList,Long scopeId){
	if(returnList == null )
		return null;
	for(CoreDebateVO dayVO:returnList){
		 if(dayVO.getId().equals(scopeId)){
			 return dayVO;
		 }
	}
	return null;
}
public CoreDebateVO getMatchedCandidateId(List<CoreDebateVO> returnList,Long charecterId){
	if(returnList == null )
		return null;
	for(CoreDebateVO dayVO:returnList){
		 if(dayVO.getCharecterId().equals(charecterId)){
			 return dayVO;
		 }
	}
	return null;
}
/*
 * Swadhin K Lenka
 * @see com.itgrids.partyanalyst.service.ICoreDashboardMainService#getTrainingCampBasicDetailsCntOverviewTrainingCampCenterWise(java.lang.Long, java.util.List, java.lang.Long, java.lang.String, java.util.List, java.util.List)
 */
public TrainingCampProgramVO getTrainingCampBasicDetailsCntOverviewTrainingCampCenterWise(Long userAccessLevelId, List<Long> userAccessLevelValues,Long stateId, String fromDateStr, String toDateStr, List<Long> enrollmentYearIds,List<Long> programIdList) {
	try{
		TrainingCampProgramVO finalResultVO = new TrainingCampProgramVO();
		Map<Long, TrainingCampProgramVO> trainingCampProgramDtlsMap = new HashMap<Long, TrainingCampProgramVO>();
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		Date fromDate = null;
		Date toDate = null;
		
		if (fromDateStr != null && !fromDateStr.isEmpty() && fromDateStr.length() > 0 && toDateStr != null && !toDateStr.isEmpty() && toDateStr.length() > 0) {
			fromDate = sdf.parse(fromDateStr);
			toDate = sdf.parse(toDateStr);
		}
		  Long accessLevelValue = 0l;
		  if(userAccessLevelId.longValue() == 4L){
		   accessLevelValue = 5L;
		  }else if(userAccessLevelId.longValue() == 5L){
		   accessLevelValue = 4L;
		  }else{
			   accessLevelValue=userAccessLevelId;
		  }
		  
		// take all attended cadre camp wise.
		
		//List<Object[]> attendedList = trainingCampAttendanceDAO.getAttendedCountForTrainingCamp(accessLevelValue, userAccessLevelValues,fromDate,toDate,enrollmentYearIds.get(0),programIdList);
		
		List<Object[]>  tempList = null;
		List<Object[]>  tempList2 = null;
		if(accessLevelValue.longValue() == IConstants.STATE_LEVEl_ACCESS_ID){
			List<Long> distList1 = new ArrayList<Long>(){{add(11L);add(12L);add(13L);add(14L);add(15L);add(16L);add(17L);}};
			tempList  = trainingCampAttendanceDAO.getInviteAttendedCountForTrainingCamp(3L,distList1,enrollmentYearIds,programIdList);//Procedure Call
			List<Long> distList2 = new ArrayList<Long>(){{add(18L);add(19L);add(20L);add(21L);add(22L);add(23L);add(517L);}};
			tempList2  = trainingCampAttendanceDAO.getInviteAttendedCountForTrainingCamp(3L,distList2,enrollmentYearIds,programIdList);//Procedure Call
			tempList.addAll(tempList2);
		}else{
			tempList  = trainingCampAttendanceDAO.getInviteAttendedCountForTrainingCamp(accessLevelValue,userAccessLevelValues,enrollmentYearIds,programIdList);//Procedure Call
		}
		
		//take total attended.
		List<Object[]> attendedList = new ArrayList<Object[]>();
		buildTotalAttended(attendedList,tempList);
		
		
		
		//create a map for training camp id and cadreId and list of attended date.
		Map<Long,Map<Long,Set<String>>> campIdAndCadreIdAndListOfAttendedDate = new HashMap<Long,Map<Long,Set<String>>>();
		
		Map<Long,Set<Long>> campIdAndTotalBatchList = new HashMap<Long,Set<Long>>();
		
		initializeTrainingCampMap(campIdAndCadreIdAndListOfAttendedDate,campIdAndTotalBatchList,attendedList,"all","camp");
		
		// teke all invite attended cadre list
		Map<Long,Map<Long,Set<String>>> campIdAndInviteeCadreIdAndListOfAttendedDate = new HashMap<Long,Map<Long,Set<String>>>();
		
		
		
		initializeTrainingCampMap(campIdAndInviteeCadreIdAndListOfAttendedDate,null,tempList,"inviteeAttended","camp");
		
		//get camp name
		List<Object[]> campNameList = null;
		if(campIdAndTotalBatchList != null && campIdAndTotalBatchList.keySet() != null && campIdAndTotalBatchList.keySet().size() > 0){
			campNameList = trainingCampDAO.getTrainingCamps(campIdAndTotalBatchList.keySet());
		}
		Map<Long,String> campIdAndNameMap = new HashMap<Long,String>();
		Map<String,String> campNameAndDescription = new HashMap<String,String>();
		if(campNameList != null && campNameList.size() > 0){
			for(Object[] param : campNameList){
				campIdAndNameMap.put(commonMethodsUtilService.getLongValueForObject(param[0]), commonMethodsUtilService.getStringValueForObject(param[1]));
				campNameAndDescription.put(commonMethodsUtilService.getStringValueForObject(param[1]), commonMethodsUtilService.getStringValueForObject(param[2]));
			}
		}
		
		//build vo for ui
		
		List<TrainingCampProgramVO> finalVO = new ArrayList<TrainingCampProgramVO>();
		if(campIdAndCadreIdAndListOfAttendedDate != null && campIdAndCadreIdAndListOfAttendedDate.size() > 0){
			for(Entry<Long,Map<Long,Set<String>>> param : campIdAndCadreIdAndListOfAttendedDate.entrySet()){
				String campName = campIdAndNameMap.get(param.getKey());
				String description =campNameAndDescription.get(campName);
				initializeTotalAttendedVO(finalVO,param.getKey(),campName,param.getValue(),campIdAndTotalBatchList,description);
			}
		}
		
		if(campIdAndInviteeCadreIdAndListOfAttendedDate != null && campIdAndInviteeCadreIdAndListOfAttendedDate.size() > 0){
			for(Entry<Long,Map<Long,Set<String>>> param : campIdAndInviteeCadreIdAndListOfAttendedDate.entrySet()){
				initializeTotalInviteeAttendedVO(finalVO,param.getKey(),param.getValue());
			}
		}
		
		//calculate non invite attended.
		
		if(finalVO != null && finalVO.size() > 0){
			for(TrainingCampProgramVO outerParam : finalVO){
				outerParam.setNonInviteeAttended(outerParam.getTotalAttenedCount() - outerParam.getInviteeAttended());
				List<TrainingCampProgramVO> tempVOList = outerParam.getTrainingProgramList();
				if(tempVOList != null && tempVOList.size() > 0){
					for(TrainingCampProgramVO innerParam : tempVOList){
						innerParam.setOnly1dayCountNonInvited(innerParam.getOnly1dayCount() - innerParam.getOnly1dayCountInvited());
						innerParam.setOnly1dayCountNonInvited(innerParam.getOnly1dayCount() - innerParam.getOnly1dayCountInvited());
						innerParam.setOnly1dayCountNonInvited(innerParam.getOnly1dayCount() - innerParam.getOnly1dayCountInvited());
					}
				}
			}
		}
		finalResultVO.setTrainingProgramList(finalVO);
		return finalResultVO;
	}catch(Exception e){
		LOG.error("Error occured at getTrainingCampBasicDetailsCntOverviewTrainingCampCenterWise() in CoreDashboardMainService {}",e);
		return null;
	}
	
}
public void buildTotalAttended(List<Object[]> attendedList, List<Object[]> tempList){
	try{
		Object[] objArr = null;
		if(tempList != null && tempList.size() > 0){
			for(Object[] param : tempList){
				objArr = new Object[4];
				objArr[0] = param[25];
				objArr[1] = param[2];
				objArr[2] = param[0];
				objArr[3] = param[1];
				attendedList.add(objArr);
			}
		}
	}catch(Exception e){
		LOG.error("Error occured at buildTotalAttended() in CoreDashboardMainService {}",e);
	}
}
public void initializeTotalInviteeAttendedVO(List<TrainingCampProgramVO> finalVO,Long campId,Map<Long,Set<String>> cadreIdAndInviteeAttendedDatesList){
	try{
		Long oneDayAttended = 0L;
		Long twoDayAttended = 0L;
		Long threeDayAttended = 0L;
		Long totalAttended = 0L;
		if(cadreIdAndInviteeAttendedDatesList != null && cadreIdAndInviteeAttendedDatesList.size() > 0){
			for(Entry<Long,Set<String>> param : cadreIdAndInviteeAttendedDatesList.entrySet() ){
				totalAttended = totalAttended + 1L;
				if(param.getValue().size() >= 3){
					threeDayAttended = threeDayAttended + 1L;
				}else if(param.getValue().size() >= 2){
					twoDayAttended = twoDayAttended + 1L;
				}else if(param.getValue().size() >= 1){
					oneDayAttended = oneDayAttended + 1L;
				}
			}
		}
		
		if(finalVO != null && finalVO.size() > 0){
			TrainingCampProgramVO outerVO = (TrainingCampProgramVO) setterAndGetterUtilService.getMatchedVOfromList(finalVO, "id", campId.toString());
			if(outerVO != null && outerVO.getTrainingProgramList().size() > 0){
				for(TrainingCampProgramVO innerVO : outerVO.getTrainingProgramList()){
					Long dayId = innerVO.getId();
					if(dayId.longValue() == 1L){
						innerVO.setOnly1dayCountInvited(oneDayAttended);
					}else if(dayId.longValue() == 2L){
						innerVO.setOnly1dayCountInvited(twoDayAttended);
					}else if(dayId.longValue() == 3L){
						innerVO.setOnly1dayCountInvited(threeDayAttended);
					}
				}
			}
			outerVO.setInviteeAttended(totalAttended);
		}
		
	}catch(Exception e){
		LOG.error("Error occured at initializeTotalInviteeAttendedVO() in CoreDashboardMainService {}",e);
	}
}
public void initializeTotalAttendedVO(List<TrainingCampProgramVO> finalVO,Long campId,String campName,  Map<Long,Set<String>> cadreIdAndAttendedDatesList,Map<Long,Set<Long>> campIdAndTotalBatchList,String description){
	try{
		TrainingCampProgramVO trainingCampProgramVO = new TrainingCampProgramVO();
		
		List<TrainingCampProgramVO> campList = new ArrayList<TrainingCampProgramVO>();
		
		
		Long oneDayAttended = 0L;
		Long twoDayAttended = 0L;
		Long threeDayAttended = 0L;
		if(cadreIdAndAttendedDatesList != null && cadreIdAndAttendedDatesList.size() > 0){
			for(Entry<Long,Set<String>> param : cadreIdAndAttendedDatesList.entrySet() ){
				if(param.getValue().size() >= 3){
					threeDayAttended = threeDayAttended + 1L;
				}else if(param.getValue().size() >= 2){
					twoDayAttended = twoDayAttended + 1L;
				}else if(param.getValue().size() >= 1){
					oneDayAttended = oneDayAttended + 1L;
				}
			}
		}
		
		trainingCampProgramVO.setId(campId);
		trainingCampProgramVO.setName(campName);//{1=[514], 2=[583], 3=[540], 4=[513], 7=[594]}
		trainingCampProgramVO.setDescription(description);
		trainingCampProgramVO.setTotalBath(Long.parseLong(String.valueOf(campIdAndTotalBatchList.get(campId).size())));
		// for one day
		TrainingCampProgramVO tempVO1 = new TrainingCampProgramVO();
		tempVO1.setId(1L);
		tempVO1.setName("1 Day");
		tempVO1.setOnly1dayCount(oneDayAttended);
		campList.add(tempVO1);
		
		// for two day
		TrainingCampProgramVO tempVO2 = new TrainingCampProgramVO();
		tempVO2 = new TrainingCampProgramVO();
		tempVO2.setId(2L);
		tempVO2.setName("2 Day");
		tempVO2.setOnly1dayCount(twoDayAttended);
		campList.add(tempVO2);
		// for three day
		TrainingCampProgramVO tempVO3 = new TrainingCampProgramVO();
		tempVO3 = new TrainingCampProgramVO();
		tempVO3.setId(3L);
		tempVO3.setName("3 Day");
		tempVO3.setOnly1dayCount(threeDayAttended);
		campList.add(tempVO3);
		trainingCampProgramVO.setTotalAttenedCount(tempVO1.getOnly1dayCount() + tempVO2.getOnly1dayCount() + tempVO3.getOnly1dayCount());
		trainingCampProgramVO.getTrainingProgramList().addAll(campList);
		
		finalVO.add(trainingCampProgramVO);
	}catch(Exception e){
		LOG.error("Error occured at initializeVO() in CoreDashboardMainService {}",e);
	}
}
public void initializeTrainingCampMap(Map<Long,Map<Long,Set<String>>> campIdAndCadreIdAndListOfAttendedDate, Map<Long,Set<Long>> campIdAndTotalBatchList,List<Object[]> attendedList,String mapType,String blockType){
	try{
		Map<Long,Set<String>> cadreIdAndListOfAttendedDate = null;
		Set<String> attendedDateList = null;
		
		Set<Long> batchIdList = null;
		int campIdPosition = 0;
		int cadreIdPosition = 0;
		int datePosition = 0;
		if(mapType.trim().equalsIgnoreCase("all") && blockType.trim().equalsIgnoreCase("camp")){
			campIdPosition = 0;
			cadreIdPosition = 2;
			datePosition = 3;
		}else if(mapType.trim().equalsIgnoreCase("inviteeAttended") && blockType.trim().equalsIgnoreCase("camp")){
			campIdPosition = 25;
			cadreIdPosition = 0;
			datePosition = 1;
		}else{
			campIdPosition = 8;
			cadreIdPosition = 0;
			datePosition = 1;
		}
		if(attendedList != null && attendedList.size() > 0){
			for(Object[] param : attendedList){
				if(mapType.trim().equalsIgnoreCase("inviteeAttended") && commonMethodsUtilService.getStringValueForObject(param[7]).trim().equalsIgnoreCase("NON INVITEE")){
					continue;
				}
				cadreIdAndListOfAttendedDate = campIdAndCadreIdAndListOfAttendedDate.get(commonMethodsUtilService.getLongValueForObject(param[campIdPosition]));
				if(cadreIdAndListOfAttendedDate == null){
					cadreIdAndListOfAttendedDate = new HashMap<Long,Set<String>>();
					attendedDateList = new HashSet<String>();
					attendedDateList.add(commonMethodsUtilService.getStringValueForObject(param[datePosition]));
					cadreIdAndListOfAttendedDate.put(commonMethodsUtilService.getLongValueForObject(param[cadreIdPosition]), attendedDateList);
					campIdAndCadreIdAndListOfAttendedDate.put(commonMethodsUtilService.getLongValueForObject(param[campIdPosition]), cadreIdAndListOfAttendedDate);
				}else{
					attendedDateList = cadreIdAndListOfAttendedDate.get(commonMethodsUtilService.getLongValueForObject(param[cadreIdPosition]));
					if(attendedDateList == null){
						attendedDateList = new HashSet<String>();
						attendedDateList.add(commonMethodsUtilService.getStringValueForObject(param[datePosition]));
						cadreIdAndListOfAttendedDate.put(commonMethodsUtilService.getLongValueForObject(param[cadreIdPosition]), attendedDateList);
					}else{
						attendedDateList.add(commonMethodsUtilService.getStringValueForObject(param[datePosition]));
					}
				}
			}
		}
		if(attendedList != null && attendedList.size() > 0 && campIdAndTotalBatchList != null){
			for(Object[] param : attendedList){
				batchIdList = campIdAndTotalBatchList.get(commonMethodsUtilService.getLongValueForObject(param[0]));
				if(batchIdList == null){
					batchIdList = new HashSet<Long>();
					campIdAndTotalBatchList.put(commonMethodsUtilService.getLongValueForObject(param[0]), batchIdList);
				}
				batchIdList.add(commonMethodsUtilService.getLongValueForObject(param[1]));
			}
		}
		
	}catch(Exception e){
		LOG.error("Error occured at initializeTrainingCampMap() in CoreDashboardMainService {}",e);
	}
}

	public List<Long> getTrainingCampPrograms(Long enrollmentIds){
		List<Long> programIds = new ArrayList<Long>();
		try{
			
			 programIds = trainingCampScheduleDAO.getTrainingCampProgramIds(enrollmentIds);
				
		}catch(Exception e){
			LOG.error("Error occured at getTrainingCampProgramIds() in CoreDashboardMainService {}",e);
		}
		return programIds;
	 }

	public List<CommitteeDataVO> getCommitteeDetailedReport(List<Long> enrollmentYearIdsList,Long committeeLevelId,String fromDate, String toDate, List<Long> basicCommitteeTypeIdsList, List<Long> committeeTypeIdsList,Long locationScopeId, List<Long> locationValuesList ){
		List<CommitteeDataVO> returnList = new ArrayList<CommitteeDataVO>(0);
		try {
			Map<Long,CommitteeDataVO> locationMap = new HashMap<Long, CommitteeDataVO>(0);
			List<Object[]> totalCommitteeDetailsList = tdpCommitteeDAO.getAvailableCommitteeDetails("",enrollmentYearIdsList,committeeLevelId,basicCommitteeTypeIdsList,committeeTypeIdsList,locationScopeId,locationValuesList);
			if(commonMethodsUtilService.isListOrSetValid(totalCommitteeDetailsList)){
				for (Object[] param : totalCommitteeDetailsList) {
					Long locationId = commonMethodsUtilService.getLongValueForObject(param[0]);
					CommitteeDataVO locationVO = locationMap.get(locationId);
					if(locationVO == null){
						locationVO = new CommitteeDataVO();
						locationMap.put(locationId, locationVO);
					}
					locationVO.setId(locationId);
					locationVO.setName( commonMethodsUtilService.getStringValueForObject(param[1]));
					
					CommitteeDataVO commVO = new CommitteeDataVO();
					commVO.setId(commonMethodsUtilService.getLongValueForObject(param[3]));
					commVO.setName(commonMethodsUtilService.getStringValueForObject(param[2]));
					commVO.setTotalCount(commonMethodsUtilService.getLongValueForObject(param[4]));
					if(!commonMethodsUtilService.isListOrSetValid(locationVO.getSubList()))
						locationVO.setSubList(new ArrayList<CommitteeDataVO>());
					locationVO.getSubList().add(commVO);
				}
			}
			
			List<Object[]> addedCommitteeDetailsList = tdpCommitteeDAO.getCommitteeMembersAddedStatusDetails(enrollmentYearIdsList,committeeLevelId,basicCommitteeTypeIdsList,committeeTypeIdsList,locationScopeId,locationValuesList);
			if(commonMethodsUtilService.isListOrSetValid(addedCommitteeDetailsList)){
				for (Object[] param : addedCommitteeDetailsList) {
					Long locationId = commonMethodsUtilService.getLongValueForObject(param[0]);
					Long committeeId = commonMethodsUtilService.getLongValueForObject(param[3]);
					CommitteeDataVO locationVO = locationMap.get(locationId);
					if(locationVO != null && commonMethodsUtilService.isListOrSetValid(locationVO.getSubList())){
						for (CommitteeDataVO vo : locationVO.getSubList()) {
							if(vo != null && vo.getId().longValue() == committeeId.longValue())
								vo.setCompletedCount(commonMethodsUtilService.getLongValueForObject(param[4]));
						}
					}
				}
			}
			
			if(commonMethodsUtilService.isMapValid(locationMap)){
				returnList.addAll(locationMap.values());
			}
			
		} catch (Exception e) {
			LOG.error("Error occured at getCommitteeDetailedReport() in CoreDashboardMainService {}",e);
		}
		return returnList;
	}
public List<CoreDebateVO> getPartyWiseCombineTwoStatesDebateDetails(String startDateStr,String endDateStr,String state,List<Long> debateLocationIdList,List<Long> debateParticipantLocationIdList,List<CoreDebateVO> returnList){				
	try{
		Date startDate = null;
		Date endDate   =null;
		
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		if(startDateStr !=null && !startDateStr.trim().isEmpty() && endDateStr !=null && !endDateStr.trim().isEmpty()){
			startDate = sdf.parse(startDateStr);
			endDate = sdf.parse(endDateStr);
		}
		
		Map<Long,CoreDebateVO> countMap = new LinkedHashMap<Long, CoreDebateVO>();
		Map<Long,List<Long>> partyWiseCandidateList = new LinkedHashMap<Long,List<Long>>();
		List<Object[]> debateParticipantList=null;
		List<Object[]> scaleCountObjList = null;
		List<Object[]> debateCountObjList =null;
		List<Object[]> othersdebateParticipantList =null;
		if(debateLocationIdList.contains(2L)){
			scaleCountObjList = debateParticipantCharcsDAO.getPartyWiseScalesOfOthersEachCharecter(startDate,endDate,state,debateLocationIdList);
			debateParticipantList= debateParticipantDAO.getPartyWiseDebateParticipantStateOtherDetails(startDate,endDate,state,debateLocationIdList,debateParticipantLocationIdList);
			debateCountObjList = debateParticipantDAO.getPartyWiseDebateOtherDetails(startDate,endDate,state,debateLocationIdList);
			othersdebateParticipantList= debateParticipantDAO.getPartyWiseDebateParticipantStateOtherDetails(startDate,endDate,state,debateLocationIdList,debateParticipantLocationIdList);
    	}
    	 debateLocationIdList.remove(2L);
    	 List<Object[]> subDebateCountObjList = debateParticipantDAO.getPartyWiseDebateDetails(startDate,endDate,state,debateLocationIdList);
    	 List<Object[]> subScaleCountObjList = debateParticipantCharcsDAO.getPartyWiseScalesOfEachCharecter(startDate,endDate,state,debateLocationIdList);
    	 List<Object[]> subDebateParticipantList = debateParticipantDAO.getPartyWiseStateDebateParticipantDetails(startDate,endDate,state,debateLocationIdList,debateParticipantLocationIdList);
	       if((scaleCountObjList !=null && subScaleCountObjList !=null) || (debateCountObjList != null && subDebateCountObjList!= null) || (debateParticipantList !=null && subDebateParticipantList !=null) ){
		    	  debateCountObjList.addAll(subDebateCountObjList);
		    	  scaleCountObjList.addAll(subScaleCountObjList);
		    	  debateParticipantList.addAll(subDebateParticipantList);
		      }else{
		    	  debateCountObjList = subDebateCountObjList;
		    	  scaleCountObjList = subScaleCountObjList;
		    	  debateParticipantList = subDebateParticipantList;
		      }
		List<Characteristics> charecters = characteristicsDAO.getCharacteristicsDetails();
		if(debateCountObjList !=null && debateCountObjList.size()>0 ){
		if(commonMethodsUtilService.isListOrSetValid(debateCountObjList)){			
			countMap= setDebateAndDebateParticipantValuesToMap(debateCountObjList,countMap,"debates");
			//countMap = setDebateParticipantValuesToMap(debateCountObjList,countMap,"debates",partyWiseCandidateList,subDebateParticipantList); 
		}
		}
		
		if((commonMethodsUtilService.isListOrSetValid(debateParticipantList)) || (commonMethodsUtilService.isListOrSetValid(othersdebateParticipantList))){			
			countMap = setDebateParticipantValuesToMap(othersdebateParticipantList,countMap,"debatesPartcpants",partyWiseCandidateList,subDebateParticipantList,debateParticipantList); 
		}
		
		if(scaleCountObjList !=null && scaleCountObjList.size()>0 ){
		if(commonMethodsUtilService.isListOrSetValid(scaleCountObjList)){
			countMap = setScaleVauesToParty(scaleCountObjList,countMap);
		}
		}
		
		if(countMap !=null && countMap.size()>0){				
			returnList = new ArrayList<CoreDebateVO>(countMap.values());				
		}
		
		if(commonMethodsUtilService.isListOrSetValid(returnList)){
			for (CoreDebateVO objects : returnList) {
				
				if(objects.getCandidateCount() !=null && objects.getDebateCount() !=null && objects.getCandidateCount() > objects.getDebateCount()){
					objects.setScalePerc(Double.parseDouble(new BigDecimal((objects.getScale())/objects.getCandidateCount()).setScale(2, BigDecimal.ROUND_HALF_UP).toString()));
				}									
				else if(objects.getCandidateCount() !=null && objects.getDebateCount() !=null && objects.getDebateCount() > objects.getCandidateCount() ){
					objects.setScalePerc(Double.parseDouble(new BigDecimal((objects.getScale())/objects.getDebateCount()).setScale(2, BigDecimal.ROUND_HALF_UP).toString()));
				}else if(objects.getDebateCount() !=null && objects.getDebateCount()>0l){
					objects.setScalePerc(Double.parseDouble(new BigDecimal((objects.getScale())/objects.getDebateCount()).setScale(2, BigDecimal.ROUND_HALF_UP).toString()));
				}
				
				if(objects.getScalePerc() !=null && objects.getScalePerc() >0.0 ){
					objects.setScalePerc(Double.parseDouble(new BigDecimal(objects.getScalePerc()/charecters.size()).setScale(1, BigDecimal.ROUND_HALF_UP).toString()));
				}					
			}
		}
		
		if(returnList !=null && returnList.size()>0){
			CoreDebateVO VO= returnList.get(0);
			VO.setRecentTime(getLatestDebate());
		}
		
		//System.out.println(returnList);
					
	}catch (Exception e) {
		LOG.error("Exception raised at getPartyWiseTotalDebateDetails() method of CoreDashboardMainService", e);
	}
	
	return returnList;
	
}
public Map<Long,CoreDebateVO> setDebateParticipantValuesToMap(List<Object[]> othersObjList,Map<Long,CoreDebateVO> countMap,String type,Map<Long,List<Long>> partyWiseCandidateList,List<Object[]> ObjList,List<Object[]> ObjAllList){
	try{
		List<Long> candidateIds =null;
		CoreDebateVO coreDebateVO =null;
		if(commonMethodsUtilService.isListOrSetValid(ObjList)){
			for(Object[] param : ObjList){
				candidateIds = partyWiseCandidateList.get((Long)param[0]);
					if(candidateIds == null){
						candidateIds =new ArrayList<Long>();
						candidateIds.add(commonMethodsUtilService.getLongValueForObject(param[3]));
						 partyWiseCandidateList.put((Long)param[0], candidateIds);
					}else{
						candidateIds.add(commonMethodsUtilService.getLongValueForObject(param[3]));
					}
			}
		}
		if(commonMethodsUtilService.isListOrSetValid(othersObjList)){
			for(Object[] obj : othersObjList){
				candidateIds = partyWiseCandidateList.get((Long)obj[0]);
				if(candidateIds == null){
					candidateIds =new ArrayList<Long>();
					candidateIds.add(commonMethodsUtilService.getLongValueForObject(obj[3]));
					partyWiseCandidateList.put((Long)obj[0], candidateIds);
				}else{
					if(!candidateIds.contains(commonMethodsUtilService.getLongValueForObject(obj[3]))){
						candidateIds.add(commonMethodsUtilService.getLongValueForObject(obj[3]));
					}
				}
				
				
			}
		}
		if(commonMethodsUtilService.isListOrSetValid(ObjAllList)){
			for(Object[] obj : ObjAllList){
				Long count=0l;
				List<Long> candidates = partyWiseCandidateList.get((Long)obj[0]);
				for(int i=0;i<candidates.size();i++){
					count=count+1;
				}
				coreDebateVO  = countMap.get((Long)obj[0]);
				if(coreDebateVO == null){
						 coreDebateVO = new CoreDebateVO();
						 coreDebateVO.setId((Long)obj[0]);
						 coreDebateVO.setName(commonMethodsUtilService.getStringValueForObject(obj[1]));
						 countMap.put((Long)obj[0], coreDebateVO);	
						
					}
				if(type != null && type.equalsIgnoreCase("debates"))	
					  coreDebateVO.setDebateCount(coreDebateVO.getDebateCount() + commonMethodsUtilService.getLongValueForObject(obj[2]));
				if(type != null && type.equalsIgnoreCase("debatesPartcpants"))
					 coreDebateVO.setCandidateCount(count);
			}
		}
		
	}catch (Exception e) {
		LOG.error("Exception raised at setDebateParticipantValuesToMap() method of CoreDashboardMainService", e);
	}
	return countMap;
} 
}  

