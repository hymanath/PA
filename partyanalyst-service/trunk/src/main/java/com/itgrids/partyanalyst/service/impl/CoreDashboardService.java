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
import java.util.Map.Entry;
import java.util.Set;

import org.apache.log4j.Logger;

import com.itgrids.partyanalyst.dao.IActivityConductedInfoDAO;
import com.itgrids.partyanalyst.dao.IActivityDocumentDAO;
import com.itgrids.partyanalyst.dao.IActivityInfoDocumentDAO;
import com.itgrids.partyanalyst.dao.IActivityLocationInfoDAO;
import com.itgrids.partyanalyst.dao.IActivityLocationParticipantInfoDAO;
import com.itgrids.partyanalyst.dao.IActivityMemberAccessLevelDAO;
import com.itgrids.partyanalyst.dao.IActivityMemberAccessTypeDAO;
import com.itgrids.partyanalyst.dao.IActivityMemberDAO;
import com.itgrids.partyanalyst.dao.IActivityMemberRelationDAO;
import com.itgrids.partyanalyst.dao.IActivityScopeDAO;
import com.itgrids.partyanalyst.dao.IConstituencyDAO;
import com.itgrids.partyanalyst.dao.IDashboardUserAccessLevelDAO;
import com.itgrids.partyanalyst.dao.IDashboardUserAccessTypeDAO;
import com.itgrids.partyanalyst.dao.IDelimitationConstituencyAssemblyDetailsDAO;
import com.itgrids.partyanalyst.dao.IDistrictDAO;
import com.itgrids.partyanalyst.dao.ILocationInfoDAO;
import com.itgrids.partyanalyst.dao.ITdpBasicCommitteeDAO;
import com.itgrids.partyanalyst.dao.ITdpCommitteeDAO;
import com.itgrids.partyanalyst.dao.ITdpCommitteeLevelDAO;
import com.itgrids.partyanalyst.dao.IUserTypeRelationDAO;
import com.itgrids.partyanalyst.dto.AffiliatedVo;
import com.itgrids.partyanalyst.dto.CommitteeVO;
import com.itgrids.partyanalyst.dto.EventDetailsVO;
import com.itgrids.partyanalyst.dto.IdAndNameVO;
import com.itgrids.partyanalyst.dto.UserDataVO;
import com.itgrids.partyanalyst.dto.UserTypeVO;
import com.itgrids.partyanalyst.model.ActivityScope;
import com.itgrids.partyanalyst.service.ICoreDashboardService;
import com.itgrids.partyanalyst.utils.CommonMethodsUtilService;
import com.itgrids.partyanalyst.utils.IConstants;

public class CoreDashboardService implements ICoreDashboardService{
	
	private final static Logger LOG = Logger.getLogger(CoreDashboardService.class);
	
	//Attributes
	private IDashboardUserAccessLevelDAO dashboardUserAccessLevelDAO;
	private IDashboardUserAccessTypeDAO  dashboardUserAccessTypeDAO;
	private ITdpCommitteeDAO tdpCommitteeDAO;
	private ITdpCommitteeLevelDAO tdpCommitteeLevelDAO;
	private ITdpBasicCommitteeDAO tdpBasicCommitteeDAO;
	private IDelimitationConstituencyAssemblyDetailsDAO delimitationConstituencyAssemblyDetailsDAO;
	private IActivityMemberAccessTypeDAO activityMemberAccessTypeDAO;
	private IActivityMemberAccessLevelDAO activityMemberAccessLevelDAO;
	private IActivityMemberDAO activityMemberDAO;
	private IUserTypeRelationDAO userTypeRelationDAO;
	private IActivityMemberRelationDAO activityMemberRelationDAO;
	private IDistrictDAO districtDAO;
	private IConstituencyDAO constituencyDAO;
	private IActivityScopeDAO activityScopeDAO;
	private ILocationInfoDAO locationInfoDAO;
	private IActivityLocationInfoDAO activityLocationInfoDAO;
	private IActivityInfoDocumentDAO activityInfoDocumentDAO;
	private CommonMethodsUtilService commonMethodsUtilService = new CommonMethodsUtilService();
	private IActivityDocumentDAO activityDocumentDAO;
	private IActivityConductedInfoDAO activityConductedInfoDAO;

	private IActivityLocationParticipantInfoDAO activityLocationParticipantInfoDAO;
	
	
	public IActivityConductedInfoDAO getActivityConductedInfoDAO() {
		return activityConductedInfoDAO;
	}
	public void setActivityConductedInfoDAO(
			IActivityConductedInfoDAO activityConductedInfoDAO) {
		this.activityConductedInfoDAO = activityConductedInfoDAO;
	}
	public IActivityDocumentDAO getActivityDocumentDAO() {
		return activityDocumentDAO;
	}
	public void setActivityDocumentDAO(IActivityDocumentDAO activityDocumentDAO) {
		this.activityDocumentDAO = activityDocumentDAO;
	}
	public CommonMethodsUtilService getCommonMethodsUtilService() {
		return commonMethodsUtilService;
	}
	public void setCommonMethodsUtilService(
			CommonMethodsUtilService commonMethodsUtilService) {
		this.commonMethodsUtilService = commonMethodsUtilService;
	}
	//setters
	public void setDashboardUserAccessLevelDAO(
			IDashboardUserAccessLevelDAO dashboardUserAccessLevelDAO) {
		this.dashboardUserAccessLevelDAO = dashboardUserAccessLevelDAO;
	}
	public void setDashboardUserAccessTypeDAO(
			IDashboardUserAccessTypeDAO dashboardUserAccessTypeDAO) {
		this.dashboardUserAccessTypeDAO = dashboardUserAccessTypeDAO;
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
	public void setDelimitationConstituencyAssemblyDetailsDAO(
			IDelimitationConstituencyAssemblyDetailsDAO delimitationConstituencyAssemblyDetailsDAO) {
		this.delimitationConstituencyAssemblyDetailsDAO = delimitationConstituencyAssemblyDetailsDAO;
	}
	public void setActivityMemberAccessTypeDAO(
			IActivityMemberAccessTypeDAO activityMemberAccessTypeDAO) {
		this.activityMemberAccessTypeDAO = activityMemberAccessTypeDAO;
	}
	public void setActivityMemberAccessLevelDAO(
			IActivityMemberAccessLevelDAO activityMemberAccessLevelDAO) {
		this.activityMemberAccessLevelDAO = activityMemberAccessLevelDAO;
	}
	public void setActivityMemberDAO(IActivityMemberDAO activityMemberDAO) {
		this.activityMemberDAO = activityMemberDAO;
	}
	public void setUserTypeRelationDAO(IUserTypeRelationDAO userTypeRelationDAO) {
		this.userTypeRelationDAO = userTypeRelationDAO;
	}
	public void setActivityMemberRelationDAO(
			IActivityMemberRelationDAO activityMemberRelationDAO) {
		this.activityMemberRelationDAO = activityMemberRelationDAO;
	}
	public void setDistrictDAO(IDistrictDAO districtDAO) {
		this.districtDAO = districtDAO;
	}
	public void setConstituencyDAO(IConstituencyDAO constituencyDAO) {
		this.constituencyDAO = constituencyDAO;
	}
	public IActivityScopeDAO getActivityScopeDAO() {
		return activityScopeDAO;
	}
	public void setActivityScopeDAO(IActivityScopeDAO activityScopeDAO) {
		this.activityScopeDAO = activityScopeDAO;
	}
	public ILocationInfoDAO getLocationInfoDAO() {
		return locationInfoDAO;
	}
	public void setLocationInfoDAO(ILocationInfoDAO locationInfoDAO) {
		this.locationInfoDAO = locationInfoDAO;
	}
	public IActivityLocationInfoDAO getActivityLocationInfoDAO() {
		return activityLocationInfoDAO;
	}
	public void setActivityLocationInfoDAO(
			IActivityLocationInfoDAO activityLocationInfoDAO) {
		this.activityLocationInfoDAO = activityLocationInfoDAO;
	}
	
	
	public IActivityInfoDocumentDAO getActivityInfoDocumentDAO() {
		return activityInfoDocumentDAO;
	}
	public void setActivityInfoDocumentDAO(
			IActivityInfoDocumentDAO activityInfoDocumentDAO) {
		this.activityInfoDocumentDAO = activityInfoDocumentDAO;
	}
	
	public IActivityLocationParticipantInfoDAO getActivityLocationParticipantInfoDAO() {
		return activityLocationParticipantInfoDAO;
	}
	public void setActivityLocationParticipantInfoDAO(
			IActivityLocationParticipantInfoDAO activityLocationParticipantInfoDAO) {
		this.activityLocationParticipantInfoDAO = activityLocationParticipantInfoDAO;
	}
	//business methods.
	public UserDataVO getUserBasicDetails(Long userId){
		
		LOG.info(" entered in to getUserBasicDetails() ");
		UserDataVO finalVO = new UserDataVO();
		try{
			
			List<Object[]> userTypeList = activityMemberAccessTypeDAO.getActivityMemberUserAccessTypeByUserId(userId);
			List<Object[]> userLevelsList = activityMemberAccessLevelDAO.getActivityMemberUserAccessLevelAndValues(userId);
			
			finalVO.setUserId(userId);
			
			if( userTypeList != null && userTypeList.size()>0){
			    Object[] obj = userTypeList.get(0);
			    finalVO.setActivityMemberId(obj[2]!=null?(Long)obj[2]:0l);
				finalVO.setUserTypeId( obj[0]!= null ? (Long)obj[0] : 0l);
				finalVO.setUserType(obj[1]!= null ? obj[1].toString() : "");
			}
			if(userLevelsList != null && userLevelsList.size()>0){
				for (Object[] obj : userLevelsList) {
					finalVO.setUserAccessLevelId( obj[0]!= null ? (Long)obj[0] : 0l);
					finalVO.setUserAccessLevel(obj[1]!= null ? obj[1].toString() : "");
					if( finalVO.getUserAccessLevelValuesList() == null){
						finalVO.setUserAccessLevelValuesList(new ArrayList<Long>(0));
					}
					finalVO.getUserAccessLevelValuesList().add(obj[2]!= null ? (Long)obj[2] : 0l);
				}
			}
			
		}catch(Exception e){
			LOG.error("error occurred in getUserBasicDetails() of CoreDashboardService class",e);
		}
		return finalVO;
	}
	
    public Map<Long,List<UserDataVO>> getAllChildUserTypesAndItsParents(){
		
		Map<Long,List<UserDataVO>> userTypesMap = new HashMap<Long, List<UserDataVO>>();
		
		List<Object[]> list  = userTypeRelationDAO.getParentUserTypesAndItsChildUserTypes();
		if( list != null && list.size() > 0){
			for( Object[] obj : list){
				
				if( obj[0] != null){
					List<UserDataVO> childUserTypeIds = null;
					childUserTypeIds = userTypesMap.get((Long)obj[0]);
					if( childUserTypeIds == null){
						childUserTypeIds = new ArrayList<UserDataVO>();
						userTypesMap.put((Long)obj[0],childUserTypeIds);
					}
					childUserTypeIds = userTypesMap.get((Long)obj[0]);
					UserDataVO childVO = new UserDataVO();
					childVO.setUserId((Long)obj[0]);
					childVO.setUserTypeId(obj[2]!=null?(Long)obj[2]:0l);
					childVO.setUserType(obj[3]!=null?obj[3].toString():"");
				}
			}
		}
		return userTypesMap;
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
	  * @param  Long userAccessLevelId
	  * @param  List<Long> userAccessLevelValues
	  * @param  Long committeeId
	  * @param  String state
	  * @param  String startDateString
	  * @param  String endDateString
	  * @return List<CommitteeVO>
	  * @author <a href="mailto:aravind.itgrids.hyd@gmail.com">Aravind</a>
	  *  This Service Method is used to get the main committee completed,started and yet to start counts for the user access level . 
	  *  @since 27-JULY-2016
	  */
	public CommitteeVO getCommitteesCumulativeBasicReportChart(Long userAccessLevelId,List<Long> userAccessLevelValues,String state,Long committeeId,String startDateString,String endDateString){
		
		LOG.info(" entered in to getCommitteesCumulativeBasicReportChart() ");
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		
		CommitteeVO finalVO = new CommitteeVO();
		Date startDate = null;
		Date endDate = null;
		try{
			
			 if(startDateString != null && !startDateString.isEmpty()){
		    	 startDate = sdf.parse(startDateString);
		     }
		     if(endDateString != null && !endDateString.isEmpty()){
		    	 endDate = sdf.parse(endDateString);
		     }
		     
		     if(userAccessLevelId.longValue() == IConstants.PARLIAMENT_LEVEl_ACCESS_ID.longValue()){
			    userAccessLevelValues = delimitationConstituencyAssemblyDetailsDAO.getAssemblyConstituenciesByParliamentList(userAccessLevelValues);
			 }
		     if( userAccessLevelValues != null && userAccessLevelValues.size() > 0){
		    	 
		    	 Long completedCount = tdpCommitteeDAO.getCommitteesCumulativeBasicReportChartQuery(userAccessLevelId,userAccessLevelValues,state,committeeId,startDate,endDate,"completed");
				 Long StartedCount = tdpCommitteeDAO.getCommitteesCumulativeBasicReportChartQuery(userAccessLevelId,userAccessLevelValues,state,committeeId,startDate,endDate,"started");
				 Long yetToStartCount = tdpCommitteeDAO.getCommitteesCumulativeBasicReportChartQuery(userAccessLevelId,userAccessLevelValues,state,committeeId,startDate,endDate,"notStarted");
				 
				 finalVO.setName("Main");
				 
				 finalVO.setStartedCount(StartedCount!= null ? StartedCount : 0l);
				 finalVO.setCompletedCount(completedCount != null ? completedCount : 0l);
				 finalVO.setYetToStartCount(yetToStartCount!= null ? yetToStartCount : 0l);
		     }
		}catch (Exception e) {
			LOG.error("error occurred in getCommitteesCumulativeBasicReportChart() of CoreDashboardService class",e);
		}
		 return finalVO;
	}
	
	 /**
	  * @param  Long userAccessLevelId
	  * @param  List<Long> userAccessLevelValues
	  * @param  String state
	  * @param  List<Long> basicCommitteeIds
	  * @param  String startDateString
	  * @param  String endDateString
	  * @return List<CommitteeVO>
	  * @author <a href="mailto:sreedhar.itgrids.hyd@gmail.com">SREEDHAR</a>
	  *  This Service Method is used to get the committee level wise counts for the given basiccommittee ids cumulatively. 
	  *  @since 29-JULY-2016
	  */
	public List<CommitteeVO> getCommitteesCumulativeOverallReportCharts(Long userAccessLevelId,List<Long> userAccessLevelValues,String state,List<Long> basicCommitteeIds,String startDateString,String endDateString){
		LOG.info(" entered in to getCommitteesCumulaticeOverallReportCharts() ");
		List<CommitteeVO> finalList = null;
		Date startDate = null;
		Date endDate = null;
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		
		try{
		     if(startDateString != null && !startDateString.isEmpty()){
		    	 startDate = sdf.parse(startDateString);
		     }
		     if(endDateString != null && !endDateString.isEmpty()){
		    	 endDate = sdf.parse(endDateString);
		     }
		     
		     Map<Long,CommitteeVO> basicCommitteeMap = new LinkedHashMap<Long,CommitteeVO>(0);
		     
		     List<Object[]> completedList = null;
		     List<Object[]> startedList = null;
		     List<Object[]> yetToStartList = null;
		     
		     if(userAccessLevelId.longValue() == IConstants.DISTRICT_LEVEl_ACCESS_ID.longValue() || userAccessLevelId.longValue() == IConstants.STATE_LEVEl_ACCESS_ID.longValue()){
		    	 
		    	 List<Long> districtAccessRequiredLevelIds = Arrays.asList(IConstants.DISTRICT_ACCESS_REQUIED_COMMITTEE_LEVEl_IDS);
		    	 //instantiation.
		    	 committeesInstantiationlogic(basicCommitteeMap,basicCommitteeIds,districtAccessRequiredLevelIds);
		    	
		    	 completedList = tdpCommitteeDAO.getCommitteesCumulativeOverallReportChartsQuery(userAccessLevelId,userAccessLevelValues,districtAccessRequiredLevelIds,state,basicCommitteeIds,startDate,endDate,"completed");
		    	 startedList = tdpCommitteeDAO.getCommitteesCumulativeOverallReportChartsQuery(userAccessLevelId,userAccessLevelValues,districtAccessRequiredLevelIds,state,basicCommitteeIds,startDate,endDate,"started");
		    	 yetToStartList = tdpCommitteeDAO.getCommitteesCumulativeOverallReportChartsQuery(userAccessLevelId,userAccessLevelValues,districtAccessRequiredLevelIds,state,basicCommitteeIds,startDate,endDate,"notStarted");
		     }
		     else if(userAccessLevelId.longValue() == IConstants.PARLIAMENT_LEVEl_ACCESS_ID.longValue() || userAccessLevelId.longValue() == IConstants.ASSEMBLY_LEVEl_ACCESS_ID.longValue()){
		    	 
		    	 List<Long> assemblyConstIds = null;
		    	 List<Long> ParOrConstAccessRequiredLevelIds = Arrays.asList(IConstants.CONSTITUENCY_ACCESS_REQUIED_COMMITTEE_LEVEl_IDS);
		    	 
		    	 if(userAccessLevelId.longValue() == IConstants.PARLIAMENT_LEVEl_ACCESS_ID.longValue()){
		    		 assemblyConstIds = delimitationConstituencyAssemblyDetailsDAO.getAssemblyConstituenciesByParliamentList(userAccessLevelValues);
		    	 }else{
		    		 assemblyConstIds =  userAccessLevelValues;
		    	 }
		    	 //instantiation.
		    	 committeesInstantiationlogic(basicCommitteeMap,basicCommitteeIds,ParOrConstAccessRequiredLevelIds);
		    	 
		    	 completedList = tdpCommitteeDAO.getCommitteesCumulativeOverallReportChartsQuery(userAccessLevelId,assemblyConstIds,ParOrConstAccessRequiredLevelIds,state,basicCommitteeIds,startDate,endDate,"completed");
		    	 startedList = tdpCommitteeDAO.getCommitteesCumulativeOverallReportChartsQuery(userAccessLevelId,assemblyConstIds,ParOrConstAccessRequiredLevelIds,state,basicCommitteeIds,startDate,endDate,"started");
		    	 yetToStartList = tdpCommitteeDAO.getCommitteesCumulativeOverallReportChartsQuery(userAccessLevelId,assemblyConstIds,ParOrConstAccessRequiredLevelIds,state,basicCommitteeIds,startDate,endDate,"notStarted");
		    
		     }
		     else if(userAccessLevelId.longValue() == IConstants.MANDAL_LEVEl_ID.longValue()){
		    	 
		    	 List<Long> mandalAccessRequiredLevelIds = Arrays.asList(IConstants.MANDAL_ACCESS_REQUIED_COMMITTEE_LEVEl_IDS);
		    	
		    	 //instantiation.
		    	 committeesInstantiationlogic(basicCommitteeMap,basicCommitteeIds,mandalAccessRequiredLevelIds);
		    	 
		    	 completedList = tdpCommitteeDAO.getCommitteesCumulativeOverallReportChartsQuery(userAccessLevelId,userAccessLevelValues,mandalAccessRequiredLevelIds,state,basicCommitteeIds,startDate,endDate,"completed");
		    	 startedList = tdpCommitteeDAO.getCommitteesCumulativeOverallReportChartsQuery(userAccessLevelId,userAccessLevelValues,mandalAccessRequiredLevelIds,state,basicCommitteeIds,startDate,endDate,"started");
		    	 yetToStartList = tdpCommitteeDAO.getCommitteesCumulativeOverallReportChartsQuery(userAccessLevelId,userAccessLevelValues,mandalAccessRequiredLevelIds,state,basicCommitteeIds,startDate,endDate,"notStarted");
		    
		     }
		     
		     setCommitteeStatusCount(completedList,basicCommitteeMap,"completed");
	    	 setCommitteeStatusCount(startedList,basicCommitteeMap,"started");
	    	 setCommitteeStatusCount(yetToStartList,basicCommitteeMap,"yetToStartList");
	    	 
	    	 
		     finalList = getRequiredList(basicCommitteeMap);
		     
		}catch(Exception e){
			LOG.error("exception occurred in getCommitteesCumulativeOverallReportCharts()", e);
		}
		return finalList;
	}
	
	
	public void committeesInstantiationlogic(Map<Long,CommitteeVO> basicCommitteeMap,List<Long> basicCommitteeIds,List<Long> requiredCommitteeLevelIds){
		
		 Map<Long,String> committeeLevelNameMap = getAllCommitteeLevels();
	     Map<Long,String> committeeNameMap   = getCommitteesNames();
	     
	   	 if(basicCommitteeIds != null && basicCommitteeIds.size() > 0){
				 for(Long basicCommitteeId : basicCommitteeIds){
					 
					 CommitteeVO basicCommitteeVO = new CommitteeVO();
					 basicCommitteeVO.setId(basicCommitteeId);
					 basicCommitteeVO.setName(committeeNameMap.get(basicCommitteeId));
					 
					 if( requiredCommitteeLevelIds != null && requiredCommitteeLevelIds.size() >0){
						 Map<Long,CommitteeVO> committeeLevelsMap = new LinkedHashMap<Long, CommitteeVO>();
			    		 for( Long reqLevelId :requiredCommitteeLevelIds ){
			    			 if(reqLevelId != 7 && reqLevelId != 9 && reqLevelId != 8 ){
			    				 CommitteeVO committeeLevelVO = new CommitteeVO();
			    				 committeeLevelVO.setId(reqLevelId);
			    				 committeeLevelVO.setName(committeeLevelNameMap.get(reqLevelId));
			    				 committeeLevelsMap.put(reqLevelId, committeeLevelVO);
			    			 }
			    		 }
			    		 basicCommitteeVO.setSubMap(committeeLevelsMap);
			    	 }
					 
					 basicCommitteeMap.put(basicCommitteeVO.getId(), basicCommitteeVO);
				 }
			 }
		}
	
	public void setCommitteeStatusCount(List<Object[]> completedList,Map<Long,CommitteeVO> basicCommitteeMap,String status){
		
		try{
			
			if( completedList != null && completedList .size() > 0){
	    		 for( Object[] obj : completedList){
	    			 if( obj[2] != null){
	    				 CommitteeVO basicCommitteeVO  =  basicCommitteeMap.get((Long)obj[2]);
	    				 if(basicCommitteeVO != null && obj[4] != null && basicCommitteeVO.getSubMap()!=null){
	    					 CommitteeVO committeeLevelVO = null;
	    					 if( ((Long)obj[4]).longValue() == 7 || ((Long)obj[4]).longValue() == 9){ // Mandal/town/division
	    						 committeeLevelVO = basicCommitteeVO.getSubMap().get(5l);
	    					 }else if( ((Long)obj[4]).longValue() == 8 ){ // village/ward
	    						 committeeLevelVO = basicCommitteeVO.getSubMap().get(6l);
	    					 }else{
	    						 committeeLevelVO = basicCommitteeVO.getSubMap().get(obj[4]);
	    					 }
	    					 if(committeeLevelVO != null){
	    						 if(status.equalsIgnoreCase("started")){
	    							 committeeLevelVO.setStartedCount(committeeLevelVO.getStartedCount() + (Long)obj[6] );
	    							 //total started counts for particular basic committee for pie chart
	    							 basicCommitteeVO.setStartedCount(basicCommitteeVO.getStartedCount() + committeeLevelVO.getStartedCount());
	    						 }else if(status.equalsIgnoreCase("completed")){
	    							 committeeLevelVO.setCompletedCount(committeeLevelVO.getCompletedCount() + (Long)obj[6] ); 
	    							//total started counts for particular basic committee for pie chart
	    							 basicCommitteeVO.setCompletedCount(basicCommitteeVO.getCompletedCount()+committeeLevelVO.getCompletedCount());
	    						 }else if(status.equalsIgnoreCase("yetToStart")){
	    							 committeeLevelVO.setYetToStartCount(committeeLevelVO.getYetToStartCount() + (Long)obj[6] );
	    							//total started counts for particular basic committee for pie chart
	    							 basicCommitteeVO.setYetToStartCount(basicCommitteeVO.getYetToStartCount() + committeeLevelVO.getYetToStartCount());
	    						 }
	    					 }
	    				 }
	    			 }
	    		 }
	    	 }
		}catch(Exception e){
			LOG.error("exception occurred in setCommitteeStatusCount()", e);
		}
	}
	public List<CommitteeVO>  getRequiredList(Map<?,CommitteeVO> basicCommitteeMap){
		List<CommitteeVO> finalList = new ArrayList<CommitteeVO>();
		if(basicCommitteeMap != null && basicCommitteeMap.size() > 0){
			for (Map.Entry<?, CommitteeVO> basicCommitteeEntry : basicCommitteeMap.entrySet()){
				if(basicCommitteeEntry.getValue()!=null && basicCommitteeEntry.getValue().getSubMap()!=null){
					basicCommitteeEntry.getValue().setSubList(new ArrayList<CommitteeVO>(basicCommitteeEntry.getValue().getSubMap().values()));
					basicCommitteeEntry.getValue().getSubMap().clear();
				}
			}
			finalList.addAll(basicCommitteeMap.values());
		}
		return finalList;
	}
	/**
	  * @param  Long userAccessLevelId
	  * @param  List<Long> userAccessLevelValues
	  * @param  String state
	  * @param  List<Long> basicCommitteeIds
	  * @param  String firstMonthString
	  * @param  String secondMonthString
	  * @return List<CommitteeVO>
	  * @author <a href="mailto:sreedhar.itgrids.hyd@gmail.com">SREEDHAR</a>
	  *   This Service Method is used to get the basic committee  wise comparative counts  for given months. 
	  *  @since 30-JULY-2016
	  */
	public List<CommitteeVO> getCommitteesComparativeBascicReportChart(Long userAccessLevelId,List<Long> userAccessLevelValues,String state,List<Long> basicCommitteeIds,String firstMonthString,String secondMonthString){
		
		LOG.info(" entered in to getCommitteesComparativeBascicReportChart() ");
		List<CommitteeVO> finalList = null;
		SimpleDateFormat sdf = new SimpleDateFormat("MM/yyyy");
		SimpleDateFormat sdf1 = new SimpleDateFormat("MMM-yyyy");
		SimpleDateFormat dateFormat = new SimpleDateFormat("MM/yyyy/dd");
		try{
			 String firstSetMonthName = null;
			 int firstSetMonth = 0;
			 int firstSetYear = 0;
			 Date setFirstDate = null;
		     if(firstMonthString != null && !firstMonthString.isEmpty()){
		    	 firstSetMonthName = sdf1.format(sdf.parse(firstMonthString));
		    	 String[] array = firstMonthString.split("/");
		    	 firstSetMonth = Integer.valueOf(array[0]);
		    	 firstSetYear  = Integer.valueOf(array[1]);
		    	 setFirstDate = dateFormat.parse(firstMonthString+"/31");
		     }
		     
		     String secondSetMonthName = null;
		     int secondSetMonth = 0;
			 int secondSetYear = 0;
			 Date setSecondDate = null;
		     if(secondMonthString != null && !secondMonthString.isEmpty()){
		    	 secondSetMonthName = sdf1.format(sdf.parse(secondMonthString));
		    	 String[] array = secondMonthString.split("/");
		    	 secondSetMonth = Integer.valueOf(array[0]);
		    	 secondSetYear  = Integer.valueOf(array[1]);
		    	 setSecondDate =  dateFormat.parse(secondMonthString+"/31");
		     }
		     
		     Map<Long,String> committeeNameMap   = getCommitteesNames();
		     
		     //pre setting.
		     Map<String,CommitteeVO> finalMap = new LinkedHashMap<String,CommitteeVO>(0);
		     for(int i=1;i<=2;i++){
		    	 CommitteeVO mainVO = new CommitteeVO();
		    	 mainVO.setType("set"+i);
		    	 if(i==1){
		    		 mainVO.setName(firstSetMonthName);
		    	 }else{
		    		 mainVO.setName(secondSetMonthName); 
		    	 }
		    	 if(basicCommitteeIds != null && basicCommitteeIds.size() > 0){
		    		 Map<Long,CommitteeVO> subMap = new LinkedHashMap<Long, CommitteeVO>();
		    		 for(Long basicCommitteeId : basicCommitteeIds){
		    			 CommitteeVO subVO = new CommitteeVO();
		    			 subVO.setId(basicCommitteeId);
		    			 subVO.setName(committeeNameMap.get(basicCommitteeId));
		    			 subMap.put(basicCommitteeId, subVO);
		    		 }
		    		 mainVO.setSubMap(subMap); 
		    	 }
		    	 finalMap.put(mainVO.getType(), mainVO);
		     }
		     
		    if(userAccessLevelId.longValue() == IConstants.PARLIAMENT_LEVEl_ACCESS_ID.longValue()){
		    	userAccessLevelValues = delimitationConstituencyAssemblyDetailsDAO.getAssemblyConstituenciesByParliamentList(userAccessLevelValues);
		    }
		    List<Object[]> firstSetCompletedList = tdpCommitteeDAO.getCommitteesComparativeBascicReportChartQuery(userAccessLevelId,userAccessLevelValues,state,basicCommitteeIds,setFirstDate);
		    List<Object[]> secondSetCompletedList = tdpCommitteeDAO.getCommitteesComparativeBascicReportChartQuery(userAccessLevelId,userAccessLevelValues,state,basicCommitteeIds,setSecondDate);	 
		    setComparativeDetails(firstSetCompletedList,finalMap,"set1");
		    setComparativeDetails(secondSetCompletedList,finalMap,"set2");
		    
		    finalList = getRequiredList(finalMap);
		    
		}catch(Exception e){
			LOG.error("exception occurred in getCommitteesComparativeBascicReportChart() ", e);
		}
		return finalList;
	}
	
	public void setComparativeDetails( List<Object[]> list,Map<String,CommitteeVO> finalMap,String setType){
		if(list != null && list.size() > 0){
			for(Object[] obj : list){
				CommitteeVO mainVO = finalMap.get(setType);
				if( mainVO != null && mainVO.getSubMap() != null && obj[2] != null){
					CommitteeVO subVO = mainVO.getSubMap().get((Long)obj[2]);
					if( subVO != null){
						subVO.setCompletedCount( obj[4]!=null ? (Long)obj[4] : 0l);
					}
				}
			}
		}
	}
	
	/**
	  * @param  Long userAccessLevelId
	  * @param  List<Long> userAccessLevelValues
	  * @param  String state
	  * @param  List<Long> basicCommitteeIds
	  * @param  String firstMonthString
	  * @param  String secondMonthString
	  * @return List<CommitteeVO>
	  * @author <a href="mailto:sreedhar.itgrids.hyd@gmail.com">SREEDHAR</a>
	  *  This Service Method is used to get the committee level wise comparative counts for basic commitees for given months. 
	  *  @since 30-JULY-2016
	  */
	public List<CommitteeVO> getCommitteesComparativeOverallReportChart(Long userAccessLevelId,List<Long> userAccessLevelValues,String state,List<Long> basicCommitteeIds,String firstMonthString,String secondMonthString){
		
		LOG.info(" entered in to getCommitteesComparativeOverallReportChart() ");
		List<CommitteeVO> finalList = null;
		SimpleDateFormat sdf = new SimpleDateFormat("MM/yyyy");
		SimpleDateFormat sdf1 = new SimpleDateFormat("MMM-yyyy");
		SimpleDateFormat dateFormat = new SimpleDateFormat("MM/yyyy/dd");
		try{
			
			 String firstSetMonthName = null;
			 int firstSetMonth = 0;
			 int firstSetYear = 0;
			 Date setFirstDate = null;
		     if(firstMonthString != null && !firstMonthString.isEmpty()){
		    	 firstSetMonthName = sdf1.format(sdf.parse(firstMonthString));
		    	 String[] array = firstMonthString.split("/");
		    	 firstSetMonth = Integer.valueOf(array[0]);
		    	 firstSetYear  = Integer.valueOf(array[1]);
		    	 setFirstDate = dateFormat.parse(firstMonthString+"/31");
		     }
		     
		     String secondSetMonthName = null;
		     int secondSetMonth = 0;
			 int secondSetYear = 0;
			 Date setSecondDate = null;
		     if(secondMonthString != null && !secondMonthString.isEmpty()){
		    	 secondSetMonthName = sdf1.format(sdf.parse(secondMonthString));
		    	 String[] array = secondMonthString.split("/");
		    	 secondSetMonth = Integer.valueOf(array[0]);
		    	 secondSetYear  = Integer.valueOf(array[1]);
		    	 setSecondDate =  dateFormat.parse(secondMonthString+"/31");
		     }
		     
		     Map<Long,CommitteeVO> basicCommitteeMap = new LinkedHashMap<Long,CommitteeVO>(0);
		     
		     List<Object[]> firstSetCompletedList = null;
		     List<Object[]> secondSetCompletedList = null;
		     
		     if(userAccessLevelId.longValue() == IConstants.DISTRICT_LEVEl_ACCESS_ID.longValue() || userAccessLevelId.longValue() == IConstants.STATE_LEVEl_ACCESS_ID.longValue()){
		    	 List<Long> districtAccessRequiredLevelIds = Arrays.asList(IConstants.DISTRICT_ACCESS_REQUIED_COMMITTEE_LEVEl_IDS);
		    	 setCommitteesComparativeInitialisationlogic(basicCommitteeMap,basicCommitteeIds,districtAccessRequiredLevelIds,firstSetMonthName,secondSetMonthName);
		    	 firstSetCompletedList =  tdpCommitteeDAO.getCommitteesComparativeOverallReportChartQuery(userAccessLevelId,userAccessLevelValues,districtAccessRequiredLevelIds,state,basicCommitteeIds,setFirstDate);
		    	 secondSetCompletedList = tdpCommitteeDAO.getCommitteesComparativeOverallReportChartQuery(userAccessLevelId,userAccessLevelValues,districtAccessRequiredLevelIds,state,basicCommitteeIds,setSecondDate);
		     }
		     else if(userAccessLevelId.longValue() == IConstants.PARLIAMENT_LEVEl_ACCESS_ID.longValue() || userAccessLevelId.longValue() == IConstants.ASSEMBLY_LEVEl_ACCESS_ID.longValue()){
		    	 List<Long> assemblyConstIds = null;
		    	 List<Long> ParOrConstAccessRequiredLevelIds = Arrays.asList(IConstants.CONSTITUENCY_ACCESS_REQUIED_COMMITTEE_LEVEl_IDS);
		    	 if(userAccessLevelId.longValue() == IConstants.PARLIAMENT_LEVEl_ACCESS_ID.longValue()){
		    		 assemblyConstIds = delimitationConstituencyAssemblyDetailsDAO.getAssemblyConstituenciesByParliamentList(userAccessLevelValues);
		    	 }else{
		    		 assemblyConstIds =  userAccessLevelValues;
		    	 }
		    	 setCommitteesComparativeInitialisationlogic(basicCommitteeMap,basicCommitteeIds,ParOrConstAccessRequiredLevelIds,firstSetMonthName,secondSetMonthName);
		    	 firstSetCompletedList  = tdpCommitteeDAO.getCommitteesComparativeOverallReportChartQuery(userAccessLevelId,assemblyConstIds,ParOrConstAccessRequiredLevelIds,state,basicCommitteeIds,setFirstDate);
		    	 secondSetCompletedList = tdpCommitteeDAO.getCommitteesComparativeOverallReportChartQuery(userAccessLevelId,assemblyConstIds,ParOrConstAccessRequiredLevelIds,state,basicCommitteeIds,setSecondDate);
		    
		     }
		     else if(userAccessLevelId.longValue() == IConstants.MANDAL_LEVEl_ID.longValue()){
		    	 List<Long> mandalAccessRequiredLevelIds = Arrays.asList(IConstants.MANDAL_ACCESS_REQUIED_COMMITTEE_LEVEl_IDS); 
		    	 setCommitteesComparativeInitialisationlogic(basicCommitteeMap,basicCommitteeIds,mandalAccessRequiredLevelIds,firstSetMonthName,secondSetMonthName);
		    	 firstSetCompletedList  = tdpCommitteeDAO.getCommitteesComparativeOverallReportChartQuery(userAccessLevelId,userAccessLevelValues,mandalAccessRequiredLevelIds,state,basicCommitteeIds,setFirstDate);
		    	 secondSetCompletedList = tdpCommitteeDAO.getCommitteesComparativeOverallReportChartQuery(userAccessLevelId,userAccessLevelValues,mandalAccessRequiredLevelIds,state,basicCommitteeIds,setSecondDate);
		     }
		     
		     setCommitteesComparativeCounts(firstSetCompletedList,basicCommitteeMap,"set1");
		     setCommitteesComparativeCounts(secondSetCompletedList,basicCommitteeMap,"set2");
		     
		     finalList = getFinalCommitteesList(basicCommitteeMap);
		}catch(Exception e){
			LOG.error("exception occurred in getCommitteesComparativeOverallReportChart", e);
		}
		return finalList;
	}
	
	public void setCommitteesComparativeInitialisationlogic(Map<Long,CommitteeVO> basicCommitteeMap,List<Long> basicCommitteeIds,List<Long> requiredCommitteeLevelIds,String firstSetMonthName,String secondSetMonthName){
		
		try{
			
			Map<Long,String> committeeLevelNameMap = getAllCommitteeLevels();
			Map<Long,String> committeeNameMap      = getCommitteesNames();
			
			if( basicCommitteeIds != null && basicCommitteeIds.size() > 0){
				for( Long basicCommitteeId : basicCommitteeIds){
					
					CommitteeVO basicCommitteeVO = new CommitteeVO();
					basicCommitteeVO.setId(basicCommitteeId);
					basicCommitteeVO.setName(committeeNameMap.get(basicCommitteeId));
			        
					//months
					Map<String,CommitteeVO> monthsMap = new LinkedHashMap<String, CommitteeVO>();
					for( int i=1; i<=2 ; i++){
						
	   					 CommitteeVO monthVO = new CommitteeVO();
	   			    	 monthVO.setType("set"+i);
	   			    	 if(i==1){
	   			    		 monthVO.setName(firstSetMonthName);
	   			    	 }else{
	   			    		 monthVO.setName(secondSetMonthName); 
	   			    	 }
	   			    	 
	   			    	 //committee levels
	   			    	if(requiredCommitteeLevelIds != null && requiredCommitteeLevelIds.size() >0){
	   			    		Map<Long,CommitteeVO> committeeLevelsMap = new LinkedHashMap<Long, CommitteeVO>();
	   			    		for( Long reqLevelId :requiredCommitteeLevelIds ){
	   			    			if(reqLevelId != 7 && reqLevelId != 9 && reqLevelId != 8 ){
	   			    				
	   			    				CommitteeVO committeeLevelVO = new CommitteeVO();
	   			    				committeeLevelVO.setId(reqLevelId);
	   			    				committeeLevelVO.setName(committeeLevelNameMap.get(reqLevelId));
	   			    				
	   			    				committeeLevelsMap.put(reqLevelId,committeeLevelVO);
	   			    			}
	   			    		}
	   			    		monthVO.setSubMap(committeeLevelsMap);
	   			    	}
	   			    	monthsMap.put(monthVO.getType(),monthVO);
   				     }
					basicCommitteeVO.setSubMap(monthsMap);
					basicCommitteeMap.put(basicCommitteeVO.getId(), basicCommitteeVO);
				}
			}
			
		}catch(Exception e){
			LOG.error("exception occurred in setCommitteesComparativeInitialisationlogic ", e);
		}
	}
	
	
	public void setCommitteesComparativeCounts(List<Object[]> list,Map<Long,CommitteeVO> basicCommitteeMap,String setType){
		
		try{
			
			if(list != null && list .size() > 0){
			
	    		 for( Object[] obj : list){
	    			 
	    				 CommitteeVO basicCommitteeVO  =  basicCommitteeMap.get((Long)obj[2]);
	    				 if(basicCommitteeVO != null && basicCommitteeVO.getSubMap()!=null)
	    				 { 
	    					 CommitteeVO monthVO = null;
	    					 if(basicCommitteeVO.getSubMap().containsKey(setType)){
	    						 monthVO = basicCommitteeVO.getSubMap().get(setType);
	    						 
	    						 if(monthVO.getSubMap() != null &&  monthVO.getSubMap().size() > 0){
	    							 
	    							 CommitteeVO committeeLevelVO = null;
	    							 
	    							 if( ((Long)obj[4]).longValue() == 7 || ((Long)obj[4]).longValue() == 9){ // Mandal/town/division
	    	    						 committeeLevelVO = monthVO.getSubMap().get(5l);
	    	    					 }else if( ((Long)obj[4]).longValue() == 8 ){ // village/ward
	    	    						 committeeLevelVO = monthVO.getSubMap().get(6l);
	    	    					 }else{
	    	    						 committeeLevelVO = monthVO.getSubMap().get(obj[4]);
	    	    					 }
	    							 if( committeeLevelVO != null){
	    								 committeeLevelVO.setCompletedCount(committeeLevelVO.getCompletedCount() + (Long)obj[6] ); 
	    							 }
	    						 }
	    					 }
	    					 
	    				 }
	    				 
	    		}
	    	}
	    	
		}catch(Exception e){
			LOG.error("exception occurred in setCommitteeStatusCount()", e);
		}
	}
	public List<CommitteeVO>  getFinalCommitteesList(Map<Long,CommitteeVO> basicCommitteeMap){
		
		List<CommitteeVO> finalList = new ArrayList<CommitteeVO>();
		
		if(basicCommitteeMap != null && basicCommitteeMap.size() > 0){
			for (Map.Entry<Long, CommitteeVO> basicCommitteeEntry : basicCommitteeMap.entrySet()){
				
				if(basicCommitteeEntry.getValue()!=null && basicCommitteeEntry.getValue().getSubMap()!=null){
					for(Entry<?, CommitteeVO> committeeLevelEntry :    basicCommitteeEntry.getValue().getSubMap().entrySet() ){
						committeeLevelEntry.getValue().setSubList(new ArrayList<CommitteeVO>(committeeLevelEntry.getValue().getSubMap().values()));
						committeeLevelEntry.getValue().getSubMap().clear();
					}
					basicCommitteeEntry.getValue().setSubList(new ArrayList<CommitteeVO>(basicCommitteeEntry.getValue().getSubMap().values()));
					basicCommitteeEntry.getValue().getSubMap().clear();
				}
			}
			finalList.addAll(basicCommitteeMap.values());
		}
		return finalList;
	}
	
	/**
	  * @param  Long userId
	  * @return UserTypeVO
	  * @author <a href="mailto:sreedhar.itgrids.hyd@gmail.com">SREEDHAR</a>
	  *  This Service Method is used to get the login user related sub level details. 
	  *  @since 04-AUGUST-2016
	  */
	public UserTypeVO getLoggedInUserStructure(Long userId){
		
		LOG.info(" entered in to getLoggedInUserStructure() ");
		UserTypeVO finalVO = null;
		try{
			Long activityMemberId = activityMemberDAO.findActivityMemberIdByUserId(userId);
			Map<Long,List<Long>> ParentChildUserTypesMap = getParentUserTypesAndItsChildUserTypes();
			finalVO = getActivityMemberDetails(activityMemberId,ParentChildUserTypesMap);
			
			Map<Long,List<Long>>  locationMap = new HashMap<Long,List<Long>>();
			Map<String,String> locationValuesMap = new HashMap<String,String>();
			
			//setting Location Names.
			getAllLocationIds(finalVO,locationMap);
			getLocationValues(locationMap,locationValuesMap);
			assignLocationNames(finalVO,locationValuesMap);
			
		}catch(Exception e){
			LOG.error("error occurred in getLoggedInUserStructure() of CoreDashboardService class",e);
		}
		return finalVO;
	}
    
	public UserTypeVO getActivityMemberDetails(Long activityMemberId,Map<Long,List<Long>> ParentChildUserTypesMap){
		
		UserTypeVO finalVO = new UserTypeVO();
		try{
			
			  List<Object[]> details = activityMemberDAO.getActivityMemberDetails(activityMemberId);
			  if( details!=null && details.size() > 0){
				  for(Object[] obj : details ){
					 finalVO.setActivityMemberId(obj[0]!= null ? (Long)obj[0] : 0l);
					 finalVO.setTdpCadreId(obj[6]!= null ? (Long)obj[6]:0l);
					 finalVO.setName(obj[7]!=null? obj[7].toString() : "");
					 finalVO.setImage(obj[8]!=null? obj[8].toString() : "");
					 finalVO.setUserTypeId(obj[1]!= null ? (Long)obj[1] : 0l);
					 finalVO.setUserType(obj[2]!= null ? obj[2].toString() : "");
					 finalVO.setShortName(obj[9]!= null ? obj[9].toString() : "");
				 	 finalVO.setLocationLevelId(obj[3]!= null ? (Long)obj[3] : 0l);
					 finalVO.setLocationLevelName(obj[4]!= null ? obj[4].toString() : "");
					 if( finalVO.getLocationValuesSet() == null){
						finalVO.setLocationValuesSet(new HashSet<Long>());
					 }
					 finalVO.getLocationValuesSet().add(obj[5]!= null ? (Long)obj[5] : 0l);
				  }
			  }
			  
			  List<Long> childUserTypeIds = ParentChildUserTypesMap.get(finalVO.getUserTypeId());
			  
				if(childUserTypeIds != null && childUserTypeIds.size() > 0){
					
					List<Object[]> childDetails = null;
					if( finalVO.getUserTypeId().longValue() == IConstants.COUNTRY_USER_TYPE_ID.longValue() || finalVO.getUserTypeId().longValue() == IConstants.STATE_USER_TYPE_ID.longValue()){
						childDetails = activityMemberAccessTypeDAO.getAllActivityMembersOfGSAndDistAndMpUserTypes(childUserTypeIds);
					}else{
						childDetails = activityMemberRelationDAO.getChildUserTypeMembers(finalVO.getActivityMemberId(), childUserTypeIds);
					}
					
					if( childDetails != null && childDetails.size() > 0){
						for( Object[] obj : childDetails){
							try{
							if( finalVO.getSubMap() == null){
								finalVO.setSubMap(new LinkedHashMap<Long,UserTypeVO>());
							}
							
							 UserTypeVO userTypeVO = null;
							if(!finalVO.getSubMap().containsKey((Long)obj[3])){  // usertype
								userTypeVO = new UserTypeVO();
								userTypeVO.setUserTypeId((Long)obj[3]);
								userTypeVO.setUserType(obj[4]!=null?obj[4].toString():"");
								userTypeVO.setShortName(obj[9]!=null?obj[9].toString():"");
								userTypeVO.setSubMap(new LinkedHashMap<Long,UserTypeVO>(0));
								finalVO.getSubMap().put((Long)obj[3],userTypeVO);
							}
							userTypeVO = finalVO.getSubMap().get((Long)obj[3]);
							
							UserTypeVO activityMemberVO = null;
							if(!userTypeVO.getSubMap().containsKey((Long)obj[0])){
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
								
								
								userTypeVO.getSubMap().put((Long)obj[0], getActivityMemberDetails(activityMemberVO.getActivityMemberId(),ParentChildUserTypesMap));
							}
							activityMemberVO = userTypeVO.getSubMap().get((Long)obj[0]);
							activityMemberVO.getLocationValuesSet().add(obj[7]!= null ?(Long)obj[7]:0l);
						}catch(Exception e){
							LOG.error(e);
						}
						}	
					}
					if(finalVO.getSubMap() != null && finalVO.getSubMap().size() > 0){
						for(Long usertypeId : finalVO.getSubMap().keySet()){
							UserTypeVO userTypeVO = finalVO.getSubMap().get(usertypeId);
							if( userTypeVO != null && userTypeVO.getSubMap() != null && userTypeVO.getSubMap().size()>0){
								userTypeVO.setSubList(new ArrayList<UserTypeVO>(userTypeVO.getSubMap().values()));
								userTypeVO.getSubMap().clear();
							}
						}
						finalVO.setSubList(new ArrayList<UserTypeVO>(finalVO.getSubMap().values()));
						finalVO.getSubMap().clear();
					}
				}else{
					return finalVO;
				}
			
		}catch(Exception e){
			e.printStackTrace();
		}
		return finalVO;
	}
	public void getAllLocationIds(UserTypeVO vo,Map<Long,List<Long>> locationMap){
     	
	   	   Long locationLevelId  = vo.getLocationLevelId();
	   	   
	   	   if(locationLevelId != null && locationLevelId > 0l){
	   		   
	   		   if(!locationMap.containsKey(locationLevelId)){
	   			 List<Long> al = new ArrayList<Long>(); 
	   			 locationMap.put(locationLevelId,al);
	   		   }
	   		   List<Long> al = locationMap.get(locationLevelId);
	   		   if(vo.getLocationValuesSet()!= null && vo.getLocationValuesSet().size() > 0){
	   			  al.addAll(vo.getLocationValuesSet());
	   		   }
	   	   }
	   		
	   	  if(vo.getSubList() != null && vo.getSubList().size() > 0){
	   		 for( UserTypeVO usertypeVO : vo.getSubList() ){
	   			 if( usertypeVO.getSubList() != null && usertypeVO.getSubList().size() > 0){
	   				 for(UserTypeVO  memberVO : usertypeVO.getSubList()){
	   					getAllLocationIds(memberVO,locationMap);
	   				 }
	   			 }
	   		 }
	   	  }
	}
	
	public  void  getLocationValues( Map<Long,List<Long>> locationMap,Map<String,String> locationValuesMap){
		
		if(locationMap != null && locationMap.size() > 0){
			
			for(Long locationLevelId : locationMap.keySet()){
				
				List<Long> locationLevelValues = locationMap.get(locationLevelId);
				
				if( locationLevelValues != null && locationLevelValues.size() > 0){
					
					List<Object[]> values = null;
					if(locationLevelId == IConstants.DISTRICT_LEVEl_ACCESS_ID){
						values = districtDAO.getDistrictNamesByIds(locationLevelValues);
					}else if(locationLevelId == IConstants.PARLIAMENT_LEVEl_ACCESS_ID){
						values = constituencyDAO.getConstituenciesNamesByIds(locationLevelValues);
					}else if(locationLevelId == IConstants.ASSEMBLY_LEVEl_ACCESS_ID){
						values = constituencyDAO.getConstituenctNamesByIds(locationLevelValues);
					}
					
					if( values != null && values.size() > 0){
						for( Object[] obj : values){
							locationValuesMap.put(locationLevelId+"_"+obj[0],obj[1]!=null ? obj[1].toString() : "");
						}
					}
				}
			}
		}
	}
	
	public void assignLocationNames(UserTypeVO vo,Map<String,String> locationValuesMap){
    	
    	Long locationLevelId = vo.getLocationLevelId();
    	if(locationLevelId != null && locationLevelId > 0l){
    		if(locationLevelId == IConstants.DISTRICT_LEVEl_ACCESS_ID || locationLevelId == IConstants.PARLIAMENT_LEVEl_ACCESS_ID || locationLevelId == IConstants.ASSEMBLY_LEVEl_ACCESS_ID ){
    			if( vo.getLocationValuesSet().size() == 1){
    				vo.setLocationName( locationValuesMap.get(locationLevelId+"_"+vo.getLocationValuesSet().iterator().next().toString()) );
    			}
			}
	   	 }
    	
    	if(vo.getSubList() != null && vo.getSubList().size() > 0){
    		for(UserTypeVO usertypeVO : vo.getSubList()){
    			if( usertypeVO.getSubList() != null && usertypeVO.getSubList().size() > 0){
	   				 for(UserTypeVO  memberVO : usertypeVO.getSubList()){
	   					assignLocationNames(memberVO,locationValuesMap);
	   				 }
	   			 }
    		}
    	}
    	
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
	
	public List<IdAndNameVO> getActivityDetails(String fromDateStr,String toDateStr){
		List<IdAndNameVO> returnList = new ArrayList<IdAndNameVO>();
		try {
			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
			Date fromDate = null;
			Date toDate = null;
			Long stateId = 1L;
			if(fromDateStr != null && toDateStr != null){
				fromDate = sdf.parse(fromDateStr);
				toDate = sdf.parse(toDateStr);
			}
			
			List<Object[]> list = activityScopeDAO.getActivityDetails1(fromDate, toDate,stateId);
			List<Object[]> list1 = activityConductedInfoDAO.getSpecialActivitiesDetails(fromDate, toDate,stateId);
			if(commonMethodsUtilService.isListOrSetValid(list1))
				if(!commonMethodsUtilService.isListOrSetValid(list1))
					list = new ArrayList<Object[]>(0);
					list.addAll(list1);
					
			if(list != null && !list.isEmpty()){
				for (Object[] obj : list) {
					IdAndNameVO vo = new IdAndNameVO();
					vo.setId(Long.valueOf(obj[0] != null ? obj[0].toString():"0"));
					vo.setName(obj[1] != null ? obj[1].toString():"");
					//vo.setPartyId(commonMethodsUtilService.getLongValueForObject(obj[2]));
					returnList.add(vo);
				}
			}
			
			/*Collections.sort(returnList, new Comparator<IdAndNameVO>() {
				public int compare(IdAndNameVO o1, IdAndNameVO o2) {
					if(o2.getPartyId() != null && o1.getPartyId() != null )
						return o2.getPartyId().compareTo(o1.getPartyId());
					else
						return 0;
				}
			});*/
			
		} catch (Exception e) {
			LOG.error("error occurred in getActivityDetails() of CoreDashboardService class",e);
		}
		return returnList;
	}
	
	public List<IdAndNameVO> getActivityOverAllSummary(Long activityId,Long activityMemberId,Long stateId,Long userTypeId){
		List<IdAndNameVO> returnList = new ArrayList<IdAndNameVO>();
		try {
			List<Long> scopeIds = new ArrayList<Long>();
			List<Long> activityIds = new ArrayList<Long>();
			activityIds.add(activityId);
			Long locationAccessLevelId = 0l;
			Set<Long> locationValues = new HashSet<Long>(0);
			//Long stateId = 1L; 
			 List<Object[]> userAccLvlANdVals=activityMemberAccessLevelDAO.getLocationLevelAndValuesByActivityMembersId(activityMemberId);
				if(userAccLvlANdVals != null && userAccLvlANdVals.size() > 0){
					 locationAccessLevelId=(Long) userAccLvlANdVals.get(0)[0];
					 for(Object[] param:userAccLvlANdVals){
						 locationValues.add(commonMethodsUtilService.getLongValueForObject(param[1]));
					 }
			   }
			List<Object[]> list = activityScopeDAO.getActivityLevelsByActivity(activityIds,null,null,stateId);
			
			Map<Long,List<Long>> scopeWiseActivitiesMap = new HashMap<Long, List<Long>>(0);
			if(list != null && !list.isEmpty()){
				for (Object[] obj : list) {
					IdAndNameVO vo = new IdAndNameVO();
					
					vo.setTdpcadreId(Long.valueOf(obj[0] != null ? obj[0].toString():"0"));	//ActivityScopeId
					vo.setId(Long.valueOf(obj[1] != null ? obj[1].toString():"0"));			//ActivityLevelId
					vo.setName(obj[2] != null ? obj[2].toString():"");						//ActivityLevel
					vo.setPartyId(Long.valueOf(obj[3] != null ? obj[3].toString():"0"));	//ScopeId
					vo.setSessionNo(Long.valueOf(obj[4] != null ? obj[4].toString():"0"));	//ScopeValue
					vo.setLocationName(obj[5] != null ? obj[5].toString():"");//activityName
					
					vo.setMobileNumber("0.00");	
					vo.setImagePathStr("0.00");
					vo.setActualMobNumber("0.00");
					
					List<Long> activityScopeIdsList = new ArrayList<Long>(0);
					if(scopeWiseActivitiesMap.get(vo.getId()) != null){
						activityScopeIdsList =scopeWiseActivitiesMap.get(vo.getId());
					}
					activityScopeIdsList.add(vo.getTdpcadreId());
					scopeWiseActivitiesMap.put(vo.getId(), activityScopeIdsList);
					
					returnList.add(vo);
					scopeIds.add(vo.getTdpcadreId());
				}
			}
			
			List<Object[]> hasSpecilaActivitiesList = activityConductedInfoDAO.getTotalCountsForScopeIds(scopeIds,null,null,null);
			if(returnList != null && !returnList.isEmpty()){
				for (IdAndNameVO idAndNameVO : returnList) {
					/*Long publicationDateId = 0l;
					ActivityScope activityScope = activityScopeDAO.get(idAndNameVO.getTdpcadreId());
					if(activityScope != null)
					   publicationDateId = activityScope.getPublicationDateId();
					Long totalCount = locationInfoDAO.getTotalCountByScope(idAndNameVO.getId(), idAndNameVO.getPartyId(), idAndNameVO.getSessionNo(),publicationDateId);
					idAndNameVO.setApTotal(totalCount);*/
					
					List<Object[]> totalList1 = activityLocationInfoDAO.getTotalCountsForScopeIds(scopeIds,null,locationAccessLevelId,locationValues);
					if(commonMethodsUtilService.isListOrSetValid(hasSpecilaActivitiesList)){
						List<Object[]> totalList2 = activityConductedInfoDAO.getTotalCountsForScopeIds(scopeIds,null,locationAccessLevelId,locationValues);
						if(commonMethodsUtilService.isListOrSetValid(totalList2)){
							if(!commonMethodsUtilService.isListOrSetValid(totalList1))
								totalList1 = new ArrayList<Object[]>(0);
								totalList1.addAll(totalList2);
						}
					}
					if(totalList1 != null && !totalList1.isEmpty()){
						for (Object[] param : totalList1) {
							Long scopeId = Long.valueOf(param[0] != null ? param[0].toString():"0");
							Long count = Long.valueOf(param[1] != null ? param[1].toString():"0");
							
							IdAndNameVO vo = getMatchedVOById(scopeId, returnList);
							if(vo != null){
								vo.setApTotal(count);			//PlannedCount
							}
							//idAndNameVO.setApTotal(commonMethodsUtilService.getLongValueForObject(param[1]));
						}
					}
				}
			}
			
			if(scopeIds != null && !scopeIds.isEmpty()){

				List<Object[]> plannedList = activityLocationInfoDAO.getPlannedCountsForScopeIds(scopeIds,null,locationAccessLevelId,locationValues);
				if(commonMethodsUtilService.isListOrSetValid(hasSpecilaActivitiesList)){
					List<Object[]> specialActivitiesPlannedList = activityConductedInfoDAO.getPlannedCountsForScopeIds(scopeIds,null,locationAccessLevelId,locationValues);
					if(commonMethodsUtilService.isListOrSetValid(specialActivitiesPlannedList)){
						if(!commonMethodsUtilService.isListOrSetValid(plannedList))
							plannedList = new ArrayList<Object[]>(0);
							plannedList.addAll(specialActivitiesPlannedList);
					}
				}
				if(plannedList != null && !plannedList.isEmpty()){
					for (Object[] obj : plannedList) {
						Long scopeId = Long.valueOf(obj[0] != null ? obj[0].toString():"0");
						Long count = Long.valueOf(obj[1] != null ? obj[1].toString():"0");
						
						IdAndNameVO vo = getMatchedVOById(scopeId, returnList);
						if(vo != null){
							vo.setInviteeCount(count);			//PlannedCount
							String percentage = "0.00";
							if(count > 0)
								percentage = (new BigDecimal((vo.getInviteeCount() * 100.0)/vo.getApTotal().doubleValue()).setScale(2, BigDecimal.ROUND_HALF_UP)).toString();
							vo.setMobileNumber(percentage);		//PlannedPerc
						}
					}
				}
				
				List<Object[]> ivrList = activityLocationInfoDAO.getIVRCountsForScopeIds(scopeIds,1L,locationAccessLevelId,locationValues);
				if(commonMethodsUtilService.isListOrSetValid(hasSpecilaActivitiesList)){
					List<Object[]> specialActivitiesivrList = activityConductedInfoDAO.getIVRCountsForScopeIds(scopeIds,1L,locationAccessLevelId,locationValues);
					if(commonMethodsUtilService.isListOrSetValid(specialActivitiesivrList)){
						if(!commonMethodsUtilService.isListOrSetValid(ivrList))
							ivrList = new ArrayList<Object[]>(0);
							ivrList.addAll(specialActivitiesivrList);
					}
				}
				
				if(ivrList != null && !ivrList.isEmpty()){
					for (Object[] obj : ivrList) {
						Long scopeId = Long.valueOf(obj[0] != null ? obj[0].toString():"0");
						Long count = Long.valueOf(obj[1] != null ? obj[1].toString():"0");
						
						IdAndNameVO vo = getMatchedVOById(scopeId, returnList);
						if(vo != null){
							vo.setAttenteeCount(count);			//IVRCount
							String percentage = "0.00";
							if(count.longValue() > 0L)
								percentage = (new BigDecimal((vo.getAttenteeCount() * 100.0)/vo.getApTotal().doubleValue()).setScale(2, BigDecimal.ROUND_HALF_UP)).toString();
							vo.setImagePathStr(percentage);		//IVRPerc
						}
					}
				}
				Long stateId1 =1l;
				List<Object[]> infocellList = activityLocationInfoDAO.getInfocellCountsForScopeIds(scopeIds,stateId1,locationAccessLevelId,locationValues);
				if(commonMethodsUtilService.isListOrSetValid(hasSpecilaActivitiesList)){
					List<Object[]> specialActivitiesinfocellList = activityConductedInfoDAO.getInfocellCountsForScopeIds(scopeIds,stateId1,locationAccessLevelId,locationValues);
					if(commonMethodsUtilService.isListOrSetValid(specialActivitiesinfocellList)){
						if(!commonMethodsUtilService.isListOrSetValid(infocellList))
							infocellList = new ArrayList<Object[]>(0);
							infocellList.addAll(specialActivitiesinfocellList);
					}
				}
				
				if(infocellList != null && !infocellList.isEmpty()){
					for (Object[] obj : infocellList) {
						Long scopeId = Long.valueOf(obj[0] != null ? obj[0].toString():"0");
						Long count = Long.valueOf(obj[1] != null ? obj[1].toString():"0");
						Long attendenCount = Long.valueOf(obj[2] != null ? obj[2].toString():"0");
						IdAndNameVO vo = getMatchedVOById(scopeId, returnList);
						if(vo != null){
							vo.setIsCsd(attendenCount.toString());
							vo.setInviteeAttendeeCnt(count);			//InfoCellCount
							String percentage = "0.00";
							if(count > 0)
								percentage = (new BigDecimal((vo.getInviteeAttendeeCnt() * 100.0)/vo.getApTotal().doubleValue()).setScale(2, BigDecimal.ROUND_HALF_UP)).toString();
							vo.setActualMobNumber(percentage);			//InfocellPerc
						}
					}
				}
				
				
				List<Object[]> yesCountList = activityLocationInfoDAO.getPlannedCountsForScopeIds(scopeIds,"yes",locationAccessLevelId,locationValues);
				if(commonMethodsUtilService.isListOrSetValid(hasSpecilaActivitiesList)){
					List<Object[]> specialActivitiesYesCountList = activityConductedInfoDAO.getPlannedCountsForScopeIds(scopeIds,"yes",locationAccessLevelId,locationValues);
					if(commonMethodsUtilService.isListOrSetValid(specialActivitiesYesCountList)){
						if(!commonMethodsUtilService.isListOrSetValid(yesCountList))
							yesCountList = new ArrayList<Object[]>(0);
							yesCountList.addAll(specialActivitiesYesCountList);
					}
				}
				
				if(yesCountList != null && !yesCountList.isEmpty()){
					for (Object[] obj : yesCountList) {
						Long scopeId = Long.valueOf(obj[0] != null ? obj[0].toString():"0");
						Long count = Long.valueOf(obj[1] != null ? obj[1].toString():"0");
						
						IdAndNameVO vo = getMatchedVOById(scopeId, returnList);
						if(vo != null){
							vo.setYesCount(count);
						}
					}
				}
				
				List<Object[]> noCountList = activityLocationInfoDAO.getPlannedCountsForScopeIds(scopeIds,"no",locationAccessLevelId,locationValues);
				if(commonMethodsUtilService.isListOrSetValid(hasSpecilaActivitiesList)){
					List<Object[]> specialActivitiesNoCountList = activityConductedInfoDAO.getPlannedCountsForScopeIds(scopeIds,"no",locationAccessLevelId,locationValues);
					if(commonMethodsUtilService.isListOrSetValid(specialActivitiesNoCountList)){
						if(!commonMethodsUtilService.isListOrSetValid(noCountList))
							noCountList = new ArrayList<Object[]>(0);
							noCountList.addAll(specialActivitiesNoCountList);
					}
				}
				
				if(noCountList != null && !noCountList.isEmpty()){
					for (Object[] obj : noCountList) {
						Long scopeId = Long.valueOf(obj[0] != null ? obj[0].toString():"0");
						Long count = Long.valueOf(obj[1] != null ? obj[1].toString():"0");
						
						IdAndNameVO vo = getMatchedVOById(scopeId, returnList);
						if(vo != null){
							vo.setNoCount(count);
						}
					}
				}
				
				List<Object[]> maybeCountList = activityLocationInfoDAO.getPlannedCountsForScopeIds(scopeIds,"maybe",locationAccessLevelId,locationValues);
				if(commonMethodsUtilService.isListOrSetValid(hasSpecilaActivitiesList)){
					List<Object[]> specialActivitiesMaybeCountList = activityConductedInfoDAO.getPlannedCountsForScopeIds(scopeIds,"maybe",locationAccessLevelId,locationValues);
					if(commonMethodsUtilService.isListOrSetValid(specialActivitiesMaybeCountList)){
						if(!commonMethodsUtilService.isListOrSetValid(maybeCountList))
							maybeCountList = new ArrayList<Object[]>(0);
							maybeCountList.addAll(specialActivitiesMaybeCountList);
					}
				}
				if(maybeCountList != null && !maybeCountList.isEmpty()){
					for (Object[] obj : maybeCountList) {
						Long scopeId = Long.valueOf(obj[0] != null ? obj[0].toString():"0");
						Long count = Long.valueOf(obj[1] != null ? obj[1].toString():"0");
						
						IdAndNameVO vo = getMatchedVOById(scopeId, returnList);
						if(vo != null){
							vo.setMayBecount(count);
						}
					}
				}
				
			}
			
			if(commonMethodsUtilService.isMapValid(scopeWiseActivitiesMap)){
				List<Long> districtIdsList = new ArrayList<Long>(0);
				if(stateId!= null){
					if(stateId.longValue() ==1L)
						districtIdsList.addAll(Arrays.asList(IConstants.AP_NEW_DISTRICTS_IDS));
					else if(stateId.longValue() ==2L || stateId.longValue() ==36L)
						districtIdsList.addAll(Arrays.asList(IConstants.TS_NEW_DISTRICTS_IDS));
					else{
						districtIdsList.addAll(Arrays.asList(IConstants.AP_NEW_DISTRICTS_IDS));
						districtIdsList.addAll(Arrays.asList(IConstants.AP_NEW_DISTRICTS_IDS));
					}
				}
				
				for (Long levelId : scopeWiseActivitiesMap.keySet()) {
					List<Object[]> documentCountList  =  new ArrayList<Object[]>(0);
					if(levelId != null && levelId.longValue()==1L){//village /ward
						List<Object[]>  list1  = activityDocumentDAO.getImagesCoveredAndTotalImagesCountForConstituencies(districtIdsList,scopeWiseActivitiesMap.get(levelId),"state","panchayat",locationAccessLevelId,locationValues);
						if(commonMethodsUtilService.isListOrSetValid(list1)){
							documentCountList.addAll(list1);
						}
						if(commonMethodsUtilService.isListOrSetValid(hasSpecilaActivitiesList)){
							List<Object[]>  list2  = activityDocumentDAO.getImagesCoveredAndTotalImagesCountForConstituencies(districtIdsList,scopeWiseActivitiesMap.get(levelId),"state","ward",locationAccessLevelId,locationValues);
							if(commonMethodsUtilService.isListOrSetValid(list2)){
								documentCountList.addAll(list2);
							}
						}
					}
					else if(levelId != null && levelId.longValue()==2L){//mandal/town/division
						List<Object[]>  list1  = activityDocumentDAO.getImagesCoveredAndTotalImagesCountForConstituencies(districtIdsList,scopeWiseActivitiesMap.get(levelId),"state","mandal",locationAccessLevelId,locationValues);
						if(commonMethodsUtilService.isListOrSetValid(list1)){
							documentCountList.addAll(list1);
						}
						if(commonMethodsUtilService.isListOrSetValid(hasSpecilaActivitiesList)){
							List<Object[]>  list2  = activityDocumentDAO.getImagesCoveredAndTotalImagesCountForConstituencies(districtIdsList,scopeWiseActivitiesMap.get(levelId),"state","town",locationAccessLevelId,locationValues);
							if(commonMethodsUtilService.isListOrSetValid(list2)){
								documentCountList.addAll(list2);
							}
						}
					}
					else if(levelId != null && levelId.longValue()==5L){//constituency
						List<Object[]>  list1  = activityDocumentDAO.getImagesCoveredAndTotalImagesCountForConstituencies(districtIdsList,scopeWiseActivitiesMap.get(levelId),"state","constituency",locationAccessLevelId,locationValues);
						if(commonMethodsUtilService.isListOrSetValid(list1)){
							documentCountList.addAll(list1);
						}
					}
			      if(commonMethodsUtilService.isListOrSetValid(documentCountList)){
			    	  for(Object[] param : documentCountList){
			    		Long imageCount = commonMethodsUtilService.getLongValueForObject(param[2]);
			    		Long coveredCount = commonMethodsUtilService.getLongValueForObject(param[1]);
			    		Long   activityScopeId  = commonMethodsUtilService.getLongValueForObject(param[3]);
			    		IdAndNameVO vo = getMatchedVOById(activityScopeId, returnList);
			    		if(vo != null){
			    			vo.setImagesCovered(vo.getImagesCovered()+coveredCount);
			    			vo.setTotalImages(vo.getTotalImages()+imageCount);
			    		}
			    	  }
			      }
				}
			}
		} catch (Exception e) {
			LOG.error("error occurred in getActivityOverAllSummary() of CoreDashboardService class",e);
		}
		return returnList;
	}
	
	public IdAndNameVO getMatchedVOById(Long scopeId,List<IdAndNameVO> list){
		IdAndNameVO returnvo = null;
	   	try {
				if(list != null && !list.isEmpty()){
					for (IdAndNameVO vo : list) {
						if(vo.getTdpcadreId().longValue() == scopeId.longValue()){				//ScopeId
							return vo;
						}
					}
				}
				return null;
			} catch (Exception e) {
				LOG.error("Exception occurred at getMatchedVOById() of CoreDashboardService", e);
			}
	   	return returnvo;
	   }
	
	public List<IdAndNameVO> getDistrictWiseActivitiesSummary(Long activityId){
		List<IdAndNameVO> returnList = new ArrayList<IdAndNameVO>();
		try {
			List<Long> activityIds = new ArrayList<Long>();
			activityIds.add(activityId);
			Long stateId =1l;
			List<Object[]> list = activityScopeDAO.getActivityLevelsByActivity(activityIds,null,null,stateId);
			if(list != null && !list.isEmpty()){
				for (Object[] obj : list) {
					IdAndNameVO vo = new IdAndNameVO();
					
					vo.setTdpcadreId(Long.valueOf(obj[0] != null ? obj[0].toString():"0"));	//ActivityScopeId
					vo.setId(Long.valueOf(obj[1] != null ? obj[1].toString():"0"));			//ActivityLevelId
					vo.setName(obj[2] != null ? obj[2].toString():"");						//ActivityLevel
					vo.setPartyId(Long.valueOf(obj[3] != null ? obj[3].toString():"0"));	//ScopeId
					vo.setSessionNo(Long.valueOf(obj[4] != null ? obj[4].toString():"0"));	//ScopeValue
					
					returnList.add(vo);
				}
			}
			
			if(returnList != null && !returnList.isEmpty()){
				for (IdAndNameVO idAndNameVO : returnList) {
					ActivityScope activityScope = activityScopeDAO.get(idAndNameVO.getTdpcadreId());
					Long publicationDateId = 0l;
					if(activityScope != null)
					   publicationDateId = activityScope.getPublicationDateId();
					List<Object[]> distList = locationInfoDAO.getDistrictWiseTotalCountsByLevelId(activityId,publicationDateId);
					if(distList != null && !distList.isEmpty()){
						for (Object[] obj : distList) {
							IdAndNameVO vo = new IdAndNameVO();
							
							vo.setId(Long.valueOf(obj[0] != null ? obj[0].toString():"0"));
							vo.setApTotal(Long.valueOf(obj[1] != null ? obj[1].toString():"0"));
						}
					}
				}
			}
			
		} catch (Exception e) {
			LOG.error("Exception occurred at getDistrictWiseActivitiesSummary() of CoreDashboardService", e);
		}
		return returnList;
	}
	public List<IdAndNameVO> activitiesDistrictWiseCohort(List<Long> activityIdsLst,String fromDateStr,String toDateStr,Long scopeId,Long activityMemberId,Long stateId,Long userTypeId){
		List<IdAndNameVO> returnList = new ArrayList<IdAndNameVO>();
		try {
			Long locationAccessLevelId = 0l;
			Set<Long> locationValues = new HashSet<Long>(0);
			List<Object[]> rtrnUsrAccssLvlIdAndVlusObjLst=activityMemberAccessLevelDAO.getLocationLevelAndValuesByActivityMembersId(activityMemberId);
			if(rtrnUsrAccssLvlIdAndVlusObjLst != null && rtrnUsrAccssLvlIdAndVlusObjLst.size() > 0){
				 locationAccessLevelId=(Long) rtrnUsrAccssLvlIdAndVlusObjLst.get(0)[0];
				 for(Object[] param:rtrnUsrAccssLvlIdAndVlusObjLst){
					 locationValues.add(commonMethodsUtilService.getLongValueForObject(param[1]));
				 }
		   }
			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
			Date fromDate = null;
			Date toDate = null;
			if(fromDateStr != null && toDateStr != null){
				fromDate = sdf.parse(fromDateStr);
				toDate = sdf.parse(toDateStr);
			}
			Map<Long,IdAndNameVO> districtsMap = new HashMap<Long, IdAndNameVO>(0);
			if(scopeId != null && scopeId.longValue()==3L){
				List<Object[]> districtsList = districtDAO.getDistrictListBystateId(1L);
				if(commonMethodsUtilService.isListOrSetValid(districtsList)){
					for (Object[] param : districtsList) {
						IdAndNameVO vo = new IdAndNameVO();
						vo.setLocationId(commonMethodsUtilService.getLongValueForObject(param[0]));
						vo.setLocationName(commonMethodsUtilService.getStringValueForObject(param[1]));
						vo.setPerc("0.00");
						vo.setRemainingPerc("100.00");
						vo.setCountOfActivityLocationInfo(0L);
						districtsMap.put(commonMethodsUtilService.getLongValueForObject(param[0]), vo);
					}
				}
			}
			
			List<Object[]> list1 = activityScopeDAO.getActivityLevelsByActivity(activityIdsLst,fromDate,toDate,stateId);
			List<Long> scopeIds = new ArrayList<Long>(0);
			if(list1 != null && !list1.isEmpty()){
				for (Object[] obj : list1) {
					IdAndNameVO vo = new IdAndNameVO();
					
					vo.setTdpcadreId(Long.valueOf(obj[0] != null ? obj[0].toString():"0"));	//ActivityScopeId
					vo.setId(Long.valueOf(obj[1] != null ? obj[1].toString():"0"));			//ActivityLevelId
					vo.setName(obj[2] != null ? obj[2].toString():"");						//ActivityLevel
					vo.setPartyId(Long.valueOf(obj[3] != null ? obj[3].toString():"0"));	//ScopeId
					vo.setSessionNo(Long.valueOf(obj[4] != null ? obj[4].toString():"0"));	//ScopeValue
					
					vo.setMobileNumber("0.00");	
					vo.setImagePathStr("0.00");
					vo.setActualMobNumber("0.00");
					vo.setYesPerc("0.00");
					vo.setNoPerc("0.00");
					vo.setMayPerc("0.00");
					returnList.add(vo);
					scopeIds.add(vo.getTdpcadreId());
				}
			}
			
		
			List<Object[]> resltList = new ArrayList<Object[]>(0);
			Map<Long,Long> yesCoutnMap = new HashMap<Long, Long>(0);
			Map<Long,Long> noCoutnMap = new HashMap<Long, Long>(0);
			Map<Long,Long> mayBeCoutnMap = new HashMap<Long, Long>(0);
			List<Object[]> hasSpecilaActivitiesList = activityConductedInfoDAO.getTotalCountsForScopeIds(scopeIds,null,null,null);
			List<Object[]> maybelist = activityLocationInfoDAO.activitiesDistrictWiseCohort(activityIdsLst,fromDate, toDate,scopeId,1l,"maybe",locationAccessLevelId,locationValues);
			if(commonMethodsUtilService.isListOrSetValid(hasSpecilaActivitiesList)){
				List<Object[]> maybesList = activityConductedInfoDAO.activitiesDistrictWiseCohort(activityIdsLst,fromDate, toDate,scopeId,"maybe",locationAccessLevelId,locationValues);
				if(commonMethodsUtilService.isListOrSetValid(maybesList)){
					resltList.addAll(maybesList);
				}
			}
			if(commonMethodsUtilService.isListOrSetValid(maybelist)){
				resltList.addAll(maybelist);
			}
			
			
			if(commonMethodsUtilService.isListOrSetValid(resltList)){
				for (Object[] param : resltList) {
					mayBeCoutnMap.put(commonMethodsUtilService.getLongValueForObject(param[2]), commonMethodsUtilService.getLongValueForObject(param[1]));
				}
				resltList.clear();
			}
			List<Object[]> nolist = activityLocationInfoDAO.activitiesDistrictWiseCohort(activityIdsLst,fromDate, toDate,scopeId,1l,"no",locationAccessLevelId,locationValues);
			if(commonMethodsUtilService.isListOrSetValid(hasSpecilaActivitiesList)){
				List<Object[]> nosList = activityConductedInfoDAO.activitiesDistrictWiseCohort(activityIdsLst,fromDate, toDate,scopeId,"no",locationAccessLevelId,locationValues);
				if(commonMethodsUtilService.isListOrSetValid(nosList)){
					resltList.addAll(nosList);
				}
			}
			if(commonMethodsUtilService.isListOrSetValid(nolist)){
				resltList.addAll(nolist);
			}
			
			
			if(commonMethodsUtilService.isListOrSetValid(resltList)){
				for (Object[] param : resltList) {
					noCoutnMap.put(commonMethodsUtilService.getLongValueForObject(param[2]), commonMethodsUtilService.getLongValueForObject(param[1]));
				}
				resltList.clear();
			}
			
			List<Object[]> yeslist = activityLocationInfoDAO.activitiesDistrictWiseCohort(activityIdsLst,fromDate, toDate,scopeId,1l,"yes",locationAccessLevelId,locationValues);
			if(commonMethodsUtilService.isListOrSetValid(hasSpecilaActivitiesList)){
				List<Object[]> yessList = activityConductedInfoDAO.activitiesDistrictWiseCohort(activityIdsLst,fromDate, toDate,scopeId,"yes",locationAccessLevelId,locationValues);
				if(commonMethodsUtilService.isListOrSetValid(yessList)){
					resltList.addAll(yessList);
				}
			}
			if(commonMethodsUtilService.isListOrSetValid(yeslist)){
				resltList.addAll(yeslist);
			}
			
			
			if(commonMethodsUtilService.isListOrSetValid(resltList)){
				for (Object[] param : resltList) {
					yesCoutnMap.put(commonMethodsUtilService.getLongValueForObject(param[2]), commonMethodsUtilService.getLongValueForObject(param[1]));
				}
				resltList.clear();
			}
			
			
			List<Object[]> totallist = activityLocationInfoDAO.activitiesDistrictWiseCount(activityIdsLst,fromDate, toDate,scopeId,1l,"maybe",locationAccessLevelId,locationValues);
			if(commonMethodsUtilService.isListOrSetValid(hasSpecilaActivitiesList)){
				List<Object[]> totallist1 = activityConductedInfoDAO.activitiesDistrictWiseCount(activityIdsLst,fromDate, toDate,scopeId,"maybe",locationAccessLevelId,locationValues);
				if(commonMethodsUtilService.isListOrSetValid(totallist1)){
					resltList.addAll(totallist1);
				}
			}
			if(commonMethodsUtilService.isListOrSetValid(totallist)){
				resltList.addAll(totallist);
			}
			
			
			Map<Long,Map<Long,Long>> scopeWiseTotalLoationsMap = new HashMap<Long,Map<Long, Long>>(0);
			if(commonMethodsUtilService.isListOrSetValid(resltList)){
				for (Object[] param : resltList) {
					Map<Long,Long> totalLocationsMap = new HashMap<Long, Long>(0);
					if(scopeWiseTotalLoationsMap.get(commonMethodsUtilService.getLongValueForObject(param[0]))!=null){	
						totalLocationsMap = scopeWiseTotalLoationsMap.get(commonMethodsUtilService.getLongValueForObject(param[0]));
					}
						totalLocationsMap.put(commonMethodsUtilService.getLongValueForObject(param[2]), commonMethodsUtilService.getLongValueForObject(param[1]));
						scopeWiseTotalLoationsMap.put(commonMethodsUtilService.getLongValueForObject(param[0]), totalLocationsMap);
					
				}
				resltList.clear();
			}
			
			
			List<Object[]> list = activityLocationInfoDAO.activitiesDistrictWiseCohort(activityIdsLst,fromDate, toDate,scopeId,1l,null,locationAccessLevelId,locationValues);
			if(commonMethodsUtilService.isListOrSetValid(hasSpecilaActivitiesList)){
				List<Object[]> specialActivitiesList = activityConductedInfoDAO.activitiesDistrictWiseCohort(activityIdsLst,fromDate, toDate,scopeId,null,locationAccessLevelId,locationValues);
				if(commonMethodsUtilService.isListOrSetValid(specialActivitiesList)){
					if(!commonMethodsUtilService.isListOrSetValid(list))
						list = new ArrayList<Object[]>(0);
						list.addAll(specialActivitiesList);
				}
				
			}
						
			Map<Long,List<IdAndNameVO>> scopesMap = new HashMap<Long, List<IdAndNameVO>>(0);
			Map<Long,List<Long>> scopeDataAvailableLocationMap = new HashMap<Long,List<Long>>(0);
			if(list != null && !list.isEmpty()){
				for (Object[] obj : list) {
					IdAndNameVO vo = new IdAndNameVO();
					vo.setId(Long.valueOf(obj[0] != null ? obj[0].toString():"0"));
					
					List<IdAndNameVO> resultList = new ArrayList<IdAndNameVO>(0);
					if(scopesMap.get(vo.getId()) != null){
						resultList = scopesMap.get(vo.getId());
					}
					vo.setPartyName(commonMethodsUtilService.getStringValueForObject(obj[4]));
					vo.setName(vo.getPartyName()+" "+commonMethodsUtilService.getStringValueForObject(obj[5])+" Level Activity ");
					vo.setCountOfActivityLocationInfo(Long.valueOf(obj[1] != null ? obj[1].toString():""));
					vo.setLocationId(Long.valueOf(obj[2] != null ? obj[2].toString():"0"));
					vo.setLocationName(commonMethodsUtilService.getStringValueForObject(obj[3]));
					vo.setActualMobNumber(commonMethodsUtilService.getStringValueForObject(obj[5]));
					vo.setPerc("0.00");
					vo.setRemainingPerc("0.00");
					vo.setYesPerc("0.00");
					vo.setNoPerc("0.00");
					vo.setMayPerc("0.00");
					vo.setApTotal(commonMethodsUtilService.getLongValueForObject(obj[6]));
					
					Map<Long,Long> totalLocationsMap = scopeWiseTotalLoationsMap.get(commonMethodsUtilService.getLongValueForObject(obj[0]));
					Long totalCount = totalLocationsMap.get(vo.getLocationId());
					if(totalCount != null && totalCount.longValue()>0L)
					{
							if(totalCount != null && totalCount.longValue()>0L && 
									 vo.getCountOfActivityLocationInfo() != null && vo.getCountOfActivityLocationInfo().longValue()>0L){
								Double perc = (Double) (vo.getCountOfActivityLocationInfo() * 100.0/totalCount);
								Float perc1 = Float.valueOf(perc.toString());
								vo.setPerc(commonMethodsUtilService.roundTo2DigitsFloatValueAsString(perc1).toString());
								if(perc1 < 100){
									Double RemaingPerc = (Double)(100.0-perc1);
									Float RemaingPerc1 =Float.valueOf(RemaingPerc.toString());
									vo.setRemainingPerc(commonMethodsUtilService.roundTo2DigitsFloatValueAsString(RemaingPerc1).toString());
								}
							}
							
							if(commonMethodsUtilService.isMapValid(yesCoutnMap)){
								Long yesCount = yesCoutnMap.get(vo.getLocationId());
								if(yesCount != null && yesCount.longValue()>0L){
									Double perc = (Double) (yesCount * 100.0/totalCount);
									Float perc1 = Float.valueOf(perc.toString());
									vo.setYesPerc(commonMethodsUtilService.roundTo2DigitsFloatValueAsString(perc1).toString());
								}
							}
							
							if(commonMethodsUtilService.isMapValid(noCoutnMap)){
								Long noCount = noCoutnMap.get(vo.getLocationId());
								if(noCount != null && noCount.longValue()>0L){
									Double perc = (Double) (noCount * 100.0/totalCount);
									Float perc1 = Float.valueOf(perc.toString());
									vo.setNoPerc(commonMethodsUtilService.roundTo2DigitsFloatValueAsString(perc1).toString());
								}
							}
							
							if(commonMethodsUtilService.isMapValid(mayBeCoutnMap)){
								Long maybeCount = mayBeCoutnMap.get(vo.getLocationId());
								if(maybeCount != null && maybeCount.longValue()>0L){
									Double perc = (Double) (maybeCount * 100.0/totalCount);
									Float perc1 = Float.valueOf(perc.toString());
									vo.setMayPerc(commonMethodsUtilService.roundTo2DigitsFloatValueAsString(perc1).toString());
								}
							}
					}
					
					resultList.add(vo);
					List<Long> locationIdsList= new ArrayList<Long>(0);
					if(scopeDataAvailableLocationMap.get(vo.getId()) != null){
						locationIdsList = scopeDataAvailableLocationMap.get(vo.getId());
					}
					locationIdsList.add(vo.getLocationId());
					scopeDataAvailableLocationMap.put(vo.getId(), locationIdsList);
					
					scopesMap.put(vo.getId(), resultList);
				}
			}
			
			returnList.clear();
			if(commonMethodsUtilService.isMapValid(scopesMap)){
				for (Long id : scopesMap.keySet()) {
					IdAndNameVO vo = new IdAndNameVO();
					vo.setDistList(scopesMap.get(id));
					
					if(scopesMap.get(id) == null || scopesMap.get(id).size()==0){ // all districts if data not available
						vo.getDistList().addAll(districtsMap.values());
					}
					else{
						List<Long> locationIdsList = scopeDataAvailableLocationMap.get(id);
						for (Long locationId : districtsMap.keySet()) {
							if(!locationIdsList.contains(locationId)){
								vo.getDistList().add(districtsMap.get(locationId));
							}
						}
					}
					
					if(commonMethodsUtilService.isListOrSetValid(vo.getDistList())){
						IdAndNameVO vo1 = vo.getDistList().get(0);
						vo.setId(vo1.getId());
						vo.setName(vo1.getName());
						vo.setPartyName(vo1.getPartyName());
						vo.setActualMobNumber(vo1.getActualMobNumber());
						vo.setApTotal(vo1.getApTotal());
					}
					/*
					Collections.sort(vo.getDistList(), new Comparator<IdAndNameVO>() {
						public int compare(IdAndNameVO o1, IdAndNameVO o2) {
							if(o2.getLocationName() != null && o1.getLocationName() != null )
								return o1.getLocationName().compareTo(o2.getLocationName());
							else
								return 0;
						}
					});
					*/
					returnList.add(vo);
				}
			}
			
		} catch (Exception e) {
			LOG.error("error occurred in activitiesDistrictWiseCohort() of CoreDashboardService class",e);
		}
		return returnList;
	}
	public void buildDataToList(List<EventDetailsVO> returnList,Long districtId,Long activityScopeId,String searchType,Long stateId,Long locationAccessLevelId ,
	Set<Long> locationValues,String showType){
		List<Long> scopeIds = new ArrayList<Long>(0);
		scopeIds.add(activityScopeId);
		List<Object[]> hasSpecilaActivitiesList = activityConductedInfoDAO.getTotalCountsForScopeIds(scopeIds,null,null,null);
		
		
		List<Object[]> totalList = activityLocationInfoDAO.getDistrictWiseActivityCounts(districtId,activityScopeId,searchType,stateId,showType,null,locationAccessLevelId
				,locationValues);
		
		if(commonMethodsUtilService.isListOrSetValid(hasSpecilaActivitiesList)){
			List<Object[]> totalList1 = activityConductedInfoDAO.getDistrictWiseActivityCounts(districtId,activityScopeId,searchType,stateId,showType,null
					,locationAccessLevelId,locationValues);
			if(commonMethodsUtilService.isListOrSetValid(totalList1))
				if(!commonMethodsUtilService.isListOrSetValid(totalList))
					totalList = new ArrayList<Object[]>(0);
					totalList.addAll(totalList1);
		}
		setDataToVO(totalList,returnList,showType,searchType);
		
		 totalList = activityLocationInfoDAO.getDistrictWiseActivityCounts(districtId,activityScopeId,searchType,stateId,null,null,locationAccessLevelId
				,locationValues);
		
		if(commonMethodsUtilService.isListOrSetValid(hasSpecilaActivitiesList)){
			List<Object[]> totalList1 = activityConductedInfoDAO.getDistrictWiseActivityCounts(districtId,activityScopeId,searchType,stateId,null,null,locationAccessLevelId,locationValues);
			if(commonMethodsUtilService.isListOrSetValid(totalList1))
				if(!commonMethodsUtilService.isListOrSetValid(totalList))
					totalList = new ArrayList<Object[]>(0);
					totalList.addAll(totalList1);
		}
		setDataToVO(totalList,returnList,"total",searchType);
		
	if(showType == null || !showType.equalsIgnoreCase("NotConducted")){	
		/*List<Object[]> planedList = activityLocationInfoDAO.getDistrictWiseActivityCounts(districtId,activityScopeId,searchType,stateId,"planned",null,locationAccessLevelId
		,locationValues);
activityLocationInfoDAO.flushAndclearSession();
List<Object[]> list1 = activityConductedInfoDAO.getDistrictWiseActivityCounts(districtId,activityScopeId,searchType,stateId,"planned",null
		,locationAccessLevelId
		,locationValues);
if(commonMethodsUtilService.isListOrSetValid(list1))
	if(!commonMethodsUtilService.isListOrSetValid(planedList))
		planedList = new ArrayList<Object[]>(0);
		planedList.addAll(list1);
	setDataToVO(planedList,returnList,"planned",searchType);
	*/
List<Object[]> condtdcList = activityLocationInfoDAO.getDistrictWiseActivityCounts(districtId,activityScopeId,searchType,stateId,"infocell",null,locationAccessLevelId
		,locationValues);
List<Object[]> list1 = null;
if(commonMethodsUtilService.isListOrSetValid(hasSpecilaActivitiesList)){
	list1 = activityConductedInfoDAO.getDistrictWiseActivityCounts(districtId,activityScopeId,searchType,stateId,"infocell",null,locationAccessLevelId
		,locationValues);
	if(commonMethodsUtilService.isListOrSetValid(list1))
		if(!commonMethodsUtilService.isListOrSetValid(condtdcList))
			condtdcList = new ArrayList<Object[]>(0);
			condtdcList.addAll(list1);
}

	setDataToVO(condtdcList,returnList,"infocell",searchType);
List<Object[]> ivrList = activityLocationInfoDAO.getDistrictWiseActivityCounts(districtId,activityScopeId,searchType,stateId,"ivr",null,locationAccessLevelId
		,locationValues);
if(commonMethodsUtilService.isListOrSetValid(hasSpecilaActivitiesList)){
	list1 = activityConductedInfoDAO.getDistrictWiseActivityCounts(districtId,activityScopeId,searchType,stateId,"ivr",null,locationAccessLevelId
		,locationValues);
	if(commonMethodsUtilService.isListOrSetValid(list1))
		if(!commonMethodsUtilService.isListOrSetValid(ivrList))
			ivrList = new ArrayList<Object[]>(0);
			ivrList.addAll(list1);
}

	setDataToVO(ivrList,returnList,"ivr",searchType);

	List<Object[]> yesCountList = activityLocationInfoDAO.getDistrictWiseActivityCounts(districtId,activityScopeId,searchType,stateId,"planned","yes",locationAccessLevelId
			,locationValues);
	if(commonMethodsUtilService.isListOrSetValid(hasSpecilaActivitiesList)){
		List<Object[]> specialActivitiesYesCountList = activityConductedInfoDAO.getDistrictWiseActivityCounts(districtId,activityScopeId,searchType,stateId,"infocell","yes",locationAccessLevelId
			,locationValues);
		if(commonMethodsUtilService.isListOrSetValid(specialActivitiesYesCountList)){
			if(!commonMethodsUtilService.isListOrSetValid(yesCountList))
				yesCountList = new ArrayList<Object[]>(0);
				yesCountList.addAll(specialActivitiesYesCountList);
		}
	}
	
	
	if(yesCountList != null && !yesCountList.isEmpty()){
		for (Object[] obj : yesCountList) {
			Long id = Long.valueOf(obj[0] != null ? obj[0].toString():"0");
			Long count = Long.valueOf(obj[2] != null ? obj[2].toString():"0");
			
			EventDetailsVO vo = getMatchedVOByIdForCounts(id, returnList);
			if(vo != null){
				vo.setYesCount(count);
			}
		}
	}
	
	List<Object[]> noCountList = activityLocationInfoDAO.getDistrictWiseActivityCounts(districtId,activityScopeId,searchType,stateId,"planned","no",locationAccessLevelId
			,locationValues);
	if(commonMethodsUtilService.isListOrSetValid(hasSpecilaActivitiesList)){
		List<Object[]> specialActivitiesNoCountList = activityConductedInfoDAO.getDistrictWiseActivityCounts(districtId,activityScopeId,searchType,stateId,"infocell","no",locationAccessLevelId
			,locationValues);
		if(commonMethodsUtilService.isListOrSetValid(specialActivitiesNoCountList)){
			if(!commonMethodsUtilService.isListOrSetValid(noCountList))
				noCountList = new ArrayList<Object[]>(0);
				noCountList.addAll(specialActivitiesNoCountList);
		}
	}
	
	
	if(noCountList != null && !noCountList.isEmpty()){
		for (Object[] obj : noCountList) {
			Long id = Long.valueOf(obj[0] != null ? obj[0].toString():"0");
			Long count = Long.valueOf(obj[2] != null ? obj[2].toString():"0");
			
			EventDetailsVO vo = getMatchedVOByIdForCounts(id, returnList);
			if(vo != null){
				vo.setNoCount(count);
			}
		}
	}
	
	List<Object[]> maybeCountList = activityLocationInfoDAO.getDistrictWiseActivityCounts(districtId,activityScopeId,searchType,stateId,"planned","maybe",locationAccessLevelId
			,locationValues);
	if(commonMethodsUtilService.isListOrSetValid(hasSpecilaActivitiesList)){
		List<Object[]> specialActivitiesMaybeCountList = activityConductedInfoDAO.getDistrictWiseActivityCounts(districtId,activityScopeId,searchType,stateId,"infocell","maybe",locationAccessLevelId
			,locationValues);
		if(commonMethodsUtilService.isListOrSetValid(specialActivitiesMaybeCountList)){
			if(!commonMethodsUtilService.isListOrSetValid(maybeCountList))
				maybeCountList = new ArrayList<Object[]>(0);
				maybeCountList.addAll(specialActivitiesMaybeCountList);
		}
	}
	
	
	if(maybeCountList != null && !maybeCountList.isEmpty()){
		for (Object[] obj : maybeCountList) {
			Long id = Long.valueOf(obj[0] != null ? obj[0].toString():"0");
			Long count = Long.valueOf(obj[2] != null ? obj[2].toString():"0");
			
			EventDetailsVO vo = getMatchedVOByIdForCounts(id, returnList);
			if(vo != null){
				vo.setMayBeCount(count);
			}
		}
	}
	
	ActivityScope activityScope = activityScopeDAO.get(activityScopeId);
	Long levelId = 0L;
	if(activityScope != null)
		levelId = activityScope.getActivityLevelId();
	
	if(levelId != null && levelId.longValue()>0L){
		List<Object[]> conductedCuntList = new ArrayList<Object[]>(0);
		if(levelId.longValue() == 1L){
			List<Object[]> list = activityLocationInfoDAO.getConductedCounts(districtId,activityScopeId,searchType,stateId,"village",locationAccessLevelId,locationValues); 
			List<Object[]> list2 = activityLocationInfoDAO.getConductedCounts(districtId,activityScopeId,searchType,stateId,"wards",locationAccessLevelId,locationValues);
			
			if(commonMethodsUtilService.isListOrSetValid(list))
				conductedCuntList.addAll(list);
			if(commonMethodsUtilService.isListOrSetValid(list2))
				conductedCuntList.addAll(list2);
			if(commonMethodsUtilService.isListOrSetValid(hasSpecilaActivitiesList)){
				List<Object[]> list5 = activityConductedInfoDAO.getConductedCounts(districtId,activityScopeId,searchType,stateId,"village",locationAccessLevelId,locationValues); 
				List<Object[]> list6 = activityConductedInfoDAO.getConductedCounts(districtId,activityScopeId,searchType,stateId,"wards",locationAccessLevelId,locationValues);
				
				if(commonMethodsUtilService.isListOrSetValid(list))
					conductedCuntList.addAll(list5);
				if(commonMethodsUtilService.isListOrSetValid(list2))
					conductedCuntList.addAll(list6);
			}
			
		}
		else if(levelId.longValue() == 2L){
			List<Object[]> list = activityLocationInfoDAO.getConductedCounts(districtId,activityScopeId,searchType,stateId,"mandal",locationAccessLevelId,locationValues); 
			List<Object[]> list2 = activityLocationInfoDAO.getConductedCounts(districtId,activityScopeId,searchType,stateId,"town",locationAccessLevelId,locationValues);
			
			if(commonMethodsUtilService.isListOrSetValid(list))
				conductedCuntList.addAll(list);
			if(commonMethodsUtilService.isListOrSetValid(list2))
				conductedCuntList.addAll(list2);
			
			if(commonMethodsUtilService.isListOrSetValid(hasSpecilaActivitiesList)){
				List<Object[]> list5 = activityConductedInfoDAO.getConductedCounts(districtId,activityScopeId,searchType,stateId,"mandal",locationAccessLevelId	,locationValues); 
				List<Object[]> list6 = activityConductedInfoDAO.getConductedCounts(districtId,activityScopeId,searchType,stateId,"town",locationAccessLevelId,locationValues);
				
				if(commonMethodsUtilService.isListOrSetValid(list))
					conductedCuntList.addAll(list5);
				if(commonMethodsUtilService.isListOrSetValid(list2))
					conductedCuntList.addAll(list6);
			}
			
		}
		else if(levelId.longValue() == 3L){
			List<Object[]> list = activityLocationInfoDAO.getConductedCounts(districtId,activityScopeId,searchType,stateId,"district",locationAccessLevelId,locationValues); 
			if(commonMethodsUtilService.isListOrSetValid(list))
				conductedCuntList.addAll(list);
			
			if(commonMethodsUtilService.isListOrSetValid(hasSpecilaActivitiesList)){
				List<Object[]> list5 = activityConductedInfoDAO.getConductedCounts(districtId,activityScopeId,searchType,stateId,"district",locationAccessLevelId,locationValues); 
				
				if(commonMethodsUtilService.isListOrSetValid(list))
					conductedCuntList.addAll(list5);
			}
			
		}
		else if(levelId.longValue() == 4L){
			List<Object[]> list = activityLocationInfoDAO.getConductedCounts(districtId,activityScopeId,searchType,stateId,"constituency",locationAccessLevelId,locationValues); 
			if(commonMethodsUtilService.isListOrSetValid(list))
				conductedCuntList.addAll(list);
			
			if(commonMethodsUtilService.isListOrSetValid(hasSpecilaActivitiesList)){
				List<Object[]> list5 = activityConductedInfoDAO.getConductedCounts(districtId,activityScopeId,searchType,stateId,"constituency",locationAccessLevelId,locationValues); 
				
				if(commonMethodsUtilService.isListOrSetValid(list))
					conductedCuntList.addAll(list5);
			}
			
		}
		
		if(conductedCuntList != null && !conductedCuntList.isEmpty()){
			for (Object[] obj : conductedCuntList) {
				Long id = Long.valueOf(obj[0] != null ? obj[0].toString():"0");
				Long count = Long.valueOf(obj[2] != null ? obj[2].toString():"0");
				
				EventDetailsVO vo = getMatchedVOByIdForCounts(id, returnList);
				if(vo != null){
					if(vo.getConductedCount() == null || vo.getConductedCount().longValue()==0L)
						vo.setConductedCount(count);
					else
						vo.setConductedCount(count+vo.getConductedCount());
				}
			}
		}
	}
}
			
	}
	public List<EventDetailsVO> getDistrictWiseActivityCounts(Long districtId,Long activityScopeId, String searchType ,Long stateId,Long activityMemberId,
			Long userTypeId,String showType){
		
		List<EventDetailsVO> returnList = new ArrayList<EventDetailsVO>();
		try{
			
			Long locationAccessLevelId = 0l;
			Set<Long> locationValues = new HashSet<Long>(0);
			//Long stateId = 1L; 
			 List<Object[]> userAccLvlANdVals=activityMemberAccessLevelDAO.getLocationLevelAndValuesByActivityMembersId(activityMemberId);
				if(userAccLvlANdVals != null && userAccLvlANdVals.size() > 0){
					 locationAccessLevelId=(Long) userAccLvlANdVals.get(0)[0];
					 for(Object[] param:userAccLvlANdVals){
						 locationValues.add(commonMethodsUtilService.getLongValueForObject(param[1]));
					 }
			   }
			if(searchType != null && searchType.equalsIgnoreCase("constituency")){
				buildDataToList(returnList,districtId,activityScopeId,searchType,stateId,locationAccessLevelId,locationValues,showType);
			}else if(searchType != null && searchType.equalsIgnoreCase("mandal")){
				
				buildDataToList(returnList,districtId,activityScopeId,"mandal",stateId,locationAccessLevelId,locationValues,showType);
				buildDataToList(returnList,districtId,activityScopeId,"town",stateId,locationAccessLevelId,locationValues,showType);
				
			}else if(searchType != null && searchType.equalsIgnoreCase("villageWard")){
				String district = districtId.toString();
				buildDataToList(returnList,Long.valueOf(district.substring(1)),activityScopeId,"village",stateId,locationAccessLevelId,locationValues,showType);
				buildDataToList(returnList,Long.valueOf(district.substring(1)),activityScopeId,"ward",stateId,locationAccessLevelId,locationValues,showType);
				
			}else if(searchType != null && searchType.equalsIgnoreCase("onlyvillage")){
				String district = districtId.toString();
				buildDataToList(returnList,Long.valueOf(district.substring(1)),activityScopeId,searchType,stateId,locationAccessLevelId,locationValues,showType);
			}
			
			 List<Long> districts = new ArrayList<Long>();
			 if(districtId != null && districtId.longValue() > 0l)
				 districts.add(districtId);
			 List<Long> activityScopeIds = new ArrayList<Long>();
			 activityScopeIds.add(activityScopeId);
			 
			Long levelId = activityScopeDAO.getActivityLevelIdByActivityScopeId(activityScopeId,stateId);
			
			if(searchType != null && searchType.equalsIgnoreCase("villageWard") || searchType.equalsIgnoreCase("onlyvillage")){
				String district = districts.get(0).toString();
				districts.clear();
				districts.add(Long.valueOf(district.substring(1)));
			}
			if(showType == null || !showType.equalsIgnoreCase("NotConducted")){
			if(searchType != null && searchType.equalsIgnoreCase("constituency")){
				List<Object[]> mandalImgs = activityDocumentDAO.getImagesCoveredAndTotalImagesForConstituencies(districts,activityScopeIds,searchType,"constituency",stateId,levelId,locationAccessLevelId,locationValues);
				setImagesCoveredAndTotalImages(mandalImgs,returnList);
				List<Object[]> localEltnImgsImgs = activityDocumentDAO.getImagesCoveredAndTotalImagesForConstituencies(districts,activityScopeIds,searchType,"localElectn",stateId,levelId,locationAccessLevelId,locationValues);
				setImagesCoveredAndTotalImages(localEltnImgsImgs,returnList);
			}else if(searchType != null && searchType.equalsIgnoreCase("mandal")){
				List<Object[]> mandalImgs = activityDocumentDAO.getImagesCoveredAndTotalImagesForConstituencies(districts,activityScopeIds,searchType,"mandal",stateId,levelId,locationAccessLevelId,locationValues);
				setImagesCoveredAndTotalImages(mandalImgs,returnList);
				List<Object[]> townImgs = activityDocumentDAO.getImagesCoveredAndTotalImagesForConstituencies(districts,activityScopeIds,searchType,"town",stateId,levelId,locationAccessLevelId,locationValues);
				setImagesCoveredAndTotalImages(townImgs,returnList);
			}else if(searchType != null && searchType.equalsIgnoreCase("villageWard")){
				List<Object[]> panchayatsImgs = activityDocumentDAO.getImagesCoveredAndTotalImagesForConstituencies(districts,activityScopeIds,searchType,"panchayat",stateId,levelId,locationAccessLevelId,locationValues);
				setImagesCoveredAndTotalImages(panchayatsImgs,returnList);
				List<Object[]> wardsImgs = activityDocumentDAO.getImagesCoveredAndTotalImagesForConstituencies(districts,activityScopeIds,searchType,"ward",stateId,levelId,locationAccessLevelId,locationValues);
				setImagesCoveredAndTotalImages(wardsImgs,returnList);
			}else if(searchType != null && searchType.equalsIgnoreCase("onlyvillage")){
				List<Object[]> mandalImgs = activityDocumentDAO.getImagesCoveredAndTotalImagesForConstituencies(districts,activityScopeIds,searchType,"onlyvillage",stateId,levelId,locationAccessLevelId,locationValues);
				setImagesCoveredAndTotalImages(mandalImgs,returnList);
			}
			}
	          if(returnList != null && returnList.size() > 0){
	        	  for (EventDetailsVO vo : returnList) {
	        		 if(vo.getType() != null && vo.getType().equalsIgnoreCase("mandal")){
	      				Long id = vo.getId();
	      				vo.setId(Long.valueOf("2"+id));
	      			}else  if(vo.getType() != null && vo.getType().equalsIgnoreCase("town")){
	      				Long id = vo.getId();
	      				vo.setId(Long.valueOf("1"+id));
	      			}else if(vo.getType() != null && vo.getType().equalsIgnoreCase("village")){
	      				Long id = vo.getId();
	      				vo.setId(Long.valueOf("1"+id));
	      			}else if(vo.getType() != null && vo.getType().equalsIgnoreCase("ward")){
	      				Long id = vo.getId();
	      				vo.setId(Long.valueOf("2"+id));
	      			}else if(vo.getType() != null && vo.getType().equalsIgnoreCase("onlyvillage")){
	      				Long id = vo.getId();
	      				vo.setId(Long.valueOf("1"+id));
	      			}
				}
	          }
		}catch(Exception e){
			e.printStackTrace();
			LOG.error("Exception occurred at getDistrictWiseActivityCounts() of CoreDashboardService", e);
		}
		
		return returnList;
		
	}
	
	public void setTotalLocationsToVO(List<EventDetailsVO> returnList,Map<Long,Long> totalLocationsMap,Long defaultValue){
		
		try{
			
			if(commonMethodsUtilService.isListOrSetValid(returnList)){
				for(EventDetailsVO vo : returnList){
					if(commonMethodsUtilService.isMapValid(totalLocationsMap)){
						Long count = totalLocationsMap.get(vo.getId());
						if(count != null && count.longValue() > 0l)
							vo.setAttendedCount(count);//total Locations
					}else{
						vo.setAttendedCount(defaultValue);//total Locations
					}
				}
			}
				
		}catch(Exception e){
			LOG.error("Exception occurred at setTotalLocationsToVO() of CoreDashboardService", e);
		}
	}
	
	public void setDataToVO(List<Object[]> planedList,List<EventDetailsVO> returnList,String type,String searchType){
		
		try{
			boolean isFilled =true;
		if(!commonMethodsUtilService.isListOrSetValid(returnList))
			isFilled=false;
		
		if(planedList != null && planedList.size() > 0){
				for(Object[] obj : planedList){
					EventDetailsVO vo = getMatchedVo(obj[0] != null ? (Long)obj[0] :0l,returnList);
					if(!isFilled){
						if(vo == null){
							vo = new EventDetailsVO();
							returnList.add(vo);
						}
					}
					if(vo != null){
						vo.setType(searchType);	
						vo.setId(obj[0] != null ? (Long)obj[0] : 0l);
						String electionType= obj[4] != null ? obj[4].toString():"";
						vo.setName(obj[1] != null ? obj[1].toString()+ "  "+electionType : "");
						
						if(type != null && type.equalsIgnoreCase("planned"))
							vo.setInviteeCount(obj[2] != null ? vo.getInviteeCount()+commonMethodsUtilService.getLongValueForObject(obj[2]) : 0l);
						else if(type != null && type.equalsIgnoreCase("infocell")){
							vo.setInviteeNotAttendedCount(obj[2] != null ? vo.getInviteeNotAttendedCount()+commonMethodsUtilService.getLongValueForObject(obj[2]) : 0l);
							vo.setInfoCellAttendedCount(obj[3] != null ? vo.getInviteeNotAttendedCount()+commonMethodsUtilService.getLongValueForObject(obj[3]) : 0l);
						}
						else if(type != null && type.equalsIgnoreCase("ivr"))
							vo.setInviteeAttendedCount(obj[2] != null ? vo.getInviteeAttendedCount()+commonMethodsUtilService.getLongValueForObject(obj[2]) : 0l);
						else if(type != null && type.equalsIgnoreCase("All"))
							vo.setAttendedCount(obj[2] != null ? vo.getAttendedCount()+commonMethodsUtilService.getLongValueForObject(obj[2]) : 1l);
						else if(type != null && type.equalsIgnoreCase("Conducted")){
							vo.setAttendedCount(obj[2] != null ? vo.getAttendedCount()+commonMethodsUtilService.getLongValueForObject(obj[2]) : 1l);
							try {vo.setActivityAttendedCount(obj[3] != null ? vo.getActivityAttendedCount()+commonMethodsUtilService.getLongValueForObject(obj[3]) : 0l);} catch (Exception e) {}
							
							if(vo.getTotalCount() != null && vo.getTotalCount().longValue()>0L && vo.getAttendedCount() != null && vo.getAttendedCount().longValue()>0L)
								vo.setNotUpdatedCount(vo.getTotalCount()-vo.getAttendedCount());
						}
						else if(type != null && type.equalsIgnoreCase("NotConducted"))
							vo.setAttendedCount(obj[2] != null ? vo.getAttendedCount()+commonMethodsUtilService.getLongValueForObject(obj[2]) : 1l);
						else if(type != null && type.equalsIgnoreCase("total")){
							vo.setTotalCount(obj[2] != null ? vo.getTotalCount()+commonMethodsUtilService.getLongValueForObject(obj[2]) : 1l);
							
						}
					}
				}
			}
			
		}catch(Exception e){
			LOG.error("Exception occurred at setDataToVO() of CoreDashboardService", e);
		}
	}
	
	public EventDetailsVO getMatchedVo(Long id,List<EventDetailsVO> list){
		EventDetailsVO returnvo = null;
	   	try {
				if(list != null && !list.isEmpty()){
					for (EventDetailsVO vo : list) {
						if(vo.getId().longValue() == id.longValue()){				
							return vo;
						}
					}
				}
				return null;
			} catch (Exception e) {
				e.printStackTrace();
				LOG.error("Exception occurred at getMatchedVo() of CoreDashboardService", e);
			}
	   	return returnvo;
	   }
	
public void setImagesCoveredAndTotalImages(List<Object[]> planedList,List<EventDetailsVO> returnList){
		
		try{
			
		if(planedList != null && planedList.size() > 0){
				for(Object[] obj : planedList){
					EventDetailsVO vo = getMatchedVo(obj[0] != null ? (Long)obj[0] :0l,returnList);
					if(vo != null){
						vo.setImagesCovered(obj[1] != null ? vo.getImagesCovered()+(Long)obj[1] : 0l);
						vo.setTotalImages(vo.getTotalImages()+commonMethodsUtilService.getLongValueForObject(obj[2]));
					}
				}
			}
		}catch(Exception e){
			e.printStackTrace();
			LOG.error("Exception occurred at setImagesCoveredAndTotalImages() of CoreDashboardService", e);
		}
	}
public EventDetailsVO getMatchedVOByIdForCounts(Long id,List<EventDetailsVO> list){
	EventDetailsVO returnvo = null;
   	try {
			if(list != null && !list.isEmpty()){
				for (EventDetailsVO vo : list) {
					if(vo.getId().longValue() == id.longValue()){				//ScopeId
						return vo;
					}
				}
			}
			return null;
		} catch (Exception e) {
			LOG.error("Exception occurred at getMatchedVOById() of CoreDashboardService", e);
		}
   	return returnvo;
   }
@Override
public List<AffiliatedVo> getAffilliatedMemberCount(String fromDateStr,String toDateStr, Long activityId, Long activityMemberId,String type) {
	List<AffiliatedVo> finalList = new ArrayList<AffiliatedVo>();
	try{
		activityLocationParticipantInfoDAO.callProcedureofactivitySp();
		Long locationAccessLevelId = 0l;
		Set<Long> locationValues = new HashSet<Long>(0);
		if(type !=null && type.length() > 0 && type.equalsIgnoreCase("main")){
		 List<Object[]> userAccLvlANdVals=activityMemberAccessLevelDAO.getLocationLevelAndValuesByActivityMembersId(activityMemberId);
			if(userAccLvlANdVals != null && userAccLvlANdVals.size() > 0){
				 locationAccessLevelId=(Long) userAccLvlANdVals.get(0)[0];
				 for(Object[] param:userAccLvlANdVals){
					 locationValues.add(commonMethodsUtilService.getLongValueForObject(param[1]));
				 }
		   }
			if(locationAccessLevelId == 4l){
				locationAccessLevelId=10l;
			}else if(locationAccessLevelId == 5l){
				locationAccessLevelId=4l;
			}
		}else{
			locationAccessLevelId=activityMemberId;
		}
		
			List<Object[]> activityParticipatentCount =activityLocationParticipantInfoDAO.getCoveredPeopleOfActivity(activityId,locationAccessLevelId,locationValues);
			
			// 0-totalMem1-totalcovered2-todaycovered,3-totreg,4-todayreg,5-totLoan,6todayLoanApp,7-locationname,8-locationID
			if(type !=null && type.equalsIgnoreCase("table")){
				for (Object[] objects : activityParticipatentCount) {
					AffiliatedVo affiliatedVo= new AffiliatedVo();
					affiliatedVo.setLocationId(commonMethodsUtilService.getLongValueForObject(objects[7]));
					affiliatedVo.setTotalMembers(commonMethodsUtilService.getLongValueForObject(objects[0]));
					affiliatedVo.setTotalCovered(commonMethodsUtilService.getLongValueForObject(objects[1]));
					affiliatedVo.setTodayCovered(commonMethodsUtilService.getLongValueForObject(objects[2]));
					affiliatedVo.setTotalRegistration(commonMethodsUtilService.getLongValueForObject(objects[3]));
					affiliatedVo.setTodayRegistration(commonMethodsUtilService.getLongValueForObject(objects[4]));
					affiliatedVo.setTotalLoanApplied(commonMethodsUtilService.getLongValueForObject(objects[5]));
					affiliatedVo.setTodayLoanApplied(commonMethodsUtilService.getLongValueForObject(objects[6]));
					finalList.add(affiliatedVo);
				}
			}else{
				AffiliatedVo affiliatedVo= new AffiliatedVo();
				affiliatedVo.setLocationId(locationAccessLevelId);
				for (Object[] objects : activityParticipatentCount) {
					affiliatedVo.setTotalMembers(affiliatedVo.getTotalMembers()+commonMethodsUtilService.getLongValueForObject(objects[0]));
					affiliatedVo.setTotalCovered(affiliatedVo.getTotalCovered()+commonMethodsUtilService.getLongValueForObject(objects[1]));
					affiliatedVo.setTodayCovered(affiliatedVo.getTodayCovered()+commonMethodsUtilService.getLongValueForObject(objects[2]));
					affiliatedVo.setTotalRegistration(affiliatedVo.getTotalRegistration()+commonMethodsUtilService.getLongValueForObject(objects[3]));
					affiliatedVo.setTodayRegistration(affiliatedVo.getTodayRegistration()+commonMethodsUtilService.getLongValueForObject(objects[4]));
					affiliatedVo.setTotalLoanApplied(affiliatedVo.getTotalLoanApplied()+commonMethodsUtilService.getLongValueForObject(objects[5]));
					affiliatedVo.setTodayLoanApplied(affiliatedVo.getTodayLoanApplied()+commonMethodsUtilService.getLongValueForObject(objects[6]));
				}
				finalList.add(affiliatedVo);
			}
	}catch(Exception e){
		LOG.error("Exception occurred at getAffilliatedMemberCount() of CoreDashboardService", e);
	}
	return finalList;
}

}
