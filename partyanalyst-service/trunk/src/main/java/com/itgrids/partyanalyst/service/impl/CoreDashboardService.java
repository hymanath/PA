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
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.Map.Entry;

import org.apache.log4j.Logger;

import com.itgrids.partyanalyst.dao.IActivityConductedInfoDAO;
import com.itgrids.partyanalyst.dao.IActivityDocumentDAO;
import com.itgrids.partyanalyst.dao.IActivityInfoDocumentDAO;
import com.itgrids.partyanalyst.dao.IActivityLocationInfoDAO;
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
import com.itgrids.partyanalyst.dto.CommitteeVO;
import com.itgrids.partyanalyst.dto.EventDetailsVO;
import com.itgrids.partyanalyst.dto.IdAndNameVO;
import com.itgrids.partyanalyst.dto.SearchAttributeVO;
import com.itgrids.partyanalyst.dto.UserDataVO;
import com.itgrids.partyanalyst.dto.UserTypeVO;
import com.itgrids.partyanalyst.model.ActivityScope;
import com.itgrids.partyanalyst.service.ICoreDashboardService;
import com.itgrids.partyanalyst.utils.CommonMethodsUtilService;
import com.itgrids.partyanalyst.utils.IConstants;

/**
 * @author Srujana
 *
 */
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
						values = constituencyDAO.getConstituenctNamesByIds(locationLevelValues);
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
			
			List<Object[]> list = activityScopeDAO.getActivityDetails(fromDate, toDate,stateId);
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
					vo.setPartyId(commonMethodsUtilService.getLongValueForObject(obj[2]));
					returnList.add(vo);
				}
			}
			
			Collections.sort(returnList, new Comparator<IdAndNameVO>() {
				public int compare(IdAndNameVO o1, IdAndNameVO o2) {
					if(o2.getPartyId() != null && o1.getPartyId() != null )
						return o2.getPartyId().compareTo(o1.getPartyId());
					else
						return 0;
				}
			});
			
		} catch (Exception e) {
			LOG.error("error occurred in getActivityDetails() of CoreDashboardService class",e);
		}
		return returnList;
	}
	
	public List<IdAndNameVO> getActivityOverAllSummary(Long activityId){
		List<IdAndNameVO> returnList = new ArrayList<IdAndNameVO>();
		try {
			List<Long> scopeIds = new ArrayList<Long>();
			Long stateId = 1L; 
			List<Object[]> list = activityScopeDAO.getActivityLevelsByActivity(activityId);
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
			
			if(returnList != null && !returnList.isEmpty()){
				for (IdAndNameVO idAndNameVO : returnList) {
					Long totalCount = locationInfoDAO.getTotalCountByScope(idAndNameVO.getId(), idAndNameVO.getPartyId(), idAndNameVO.getSessionNo());
					idAndNameVO.setApTotal(totalCount);
				}
			}
			
			if(scopeIds != null && !scopeIds.isEmpty()){
				List<Object[]> plannedList = activityLocationInfoDAO.getPlannedCountsForScopeIds(scopeIds);
				List<Object[]> specialActivitiesPlannedList = activityConductedInfoDAO.getPlannedCountsForScopeIds(scopeIds);
				if(commonMethodsUtilService.isListOrSetValid(specialActivitiesPlannedList)){
					if(!commonMethodsUtilService.isListOrSetValid(plannedList))
						plannedList = new ArrayList<Object[]>(0);
						plannedList.addAll(specialActivitiesPlannedList);
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
				
				List<Object[]> ivrList = activityLocationInfoDAO.getIVRCountsForScopeIds(scopeIds);
				List<Object[]> specialActivitiesivrList = activityConductedInfoDAO.getIVRCountsForScopeIds(scopeIds);
				if(commonMethodsUtilService.isListOrSetValid(specialActivitiesivrList)){
					if(!commonMethodsUtilService.isListOrSetValid(ivrList))
						ivrList = new ArrayList<Object[]>(0);
						ivrList.addAll(specialActivitiesivrList);
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
				
				List<Object[]> infocellList = activityLocationInfoDAO.getInfocellCountsForScopeIds(scopeIds);
				List<Object[]> specialActivitiesinfocellList = activityConductedInfoDAO.getInfocellCountsForScopeIds(scopeIds);
				if(commonMethodsUtilService.isListOrSetValid(specialActivitiesinfocellList)){
					if(!commonMethodsUtilService.isListOrSetValid(infocellList))
						infocellList = new ArrayList<Object[]>(0);
						infocellList.addAll(specialActivitiesinfocellList);
				}
				
				if(infocellList != null && !infocellList.isEmpty()){
					for (Object[] obj : infocellList) {
						Long scopeId = Long.valueOf(obj[0] != null ? obj[0].toString():"0");
						Long count = Long.valueOf(obj[1] != null ? obj[1].toString():"0");
						
						IdAndNameVO vo = getMatchedVOById(scopeId, returnList);
						if(vo != null){
							vo.setInviteeAttendeeCnt(count);			//InfoCellCount
							String percentage = "0.00";
							if(count > 0)
								percentage = (new BigDecimal((vo.getInviteeAttendeeCnt() * 100.0)/vo.getApTotal().doubleValue()).setScale(2, BigDecimal.ROUND_HALF_UP)).toString();
							vo.setActualMobNumber(percentage);			//InfocellPerc
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
						List<Object[]>  list1  = activityDocumentDAO.getImagesCoveredAndTotalImagesForConstituencies(districtIdsList,scopeWiseActivitiesMap.get(levelId),"state","panchayat");
						List<Object[]>  list2  = activityDocumentDAO.getImagesCoveredAndTotalImagesForConstituencies(districtIdsList,scopeWiseActivitiesMap.get(levelId),"state","ward");
						if(commonMethodsUtilService.isListOrSetValid(list1)){
							documentCountList.addAll(list1);
						}
						if(commonMethodsUtilService.isListOrSetValid(list2)){
							documentCountList.addAll(list2);
						}
					}
					else if(levelId != null && levelId.longValue()==2L){//mandal/town/division
						List<Object[]>  list1  = activityDocumentDAO.getImagesCoveredAndTotalImagesForConstituencies(districtIdsList,scopeWiseActivitiesMap.get(levelId),"state","mandal");
						List<Object[]>  list2  = activityDocumentDAO.getImagesCoveredAndTotalImagesForConstituencies(districtIdsList,scopeWiseActivitiesMap.get(levelId),"state","town");
						if(commonMethodsUtilService.isListOrSetValid(list1)){
							documentCountList.addAll(list1);
						}
						if(commonMethodsUtilService.isListOrSetValid(list2)){
							documentCountList.addAll(list2);
						}
					}
					else if(levelId != null && levelId.longValue()==5L){//constituency
						List<Object[]>  list1  = activityDocumentDAO.getImagesCoveredAndTotalImagesForConstituencies(districtIdsList,scopeWiseActivitiesMap.get(levelId),"state","constituency");
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
			    			vo.setImagesCovered(coveredCount);
			    			vo.setTotalImages(imageCount);
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
			List<Object[]> list = activityScopeDAO.getActivityLevelsByActivity(activityId);
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
					List<Object[]> distList = locationInfoDAO.getDistrictWiseTotalCountsByLevelId(activityId);
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
	public List<IdAndNameVO> activitiesDistrictWiseCohort(List<Long> activityIdsLst,String fromDateStr,String toDateStr){
		List<IdAndNameVO> returnList = new ArrayList<IdAndNameVO>();
		try {
			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
			Date fromDate = null;
			Date toDate = null;
			if(fromDateStr != null && toDateStr != null){
				fromDate = sdf.parse(fromDateStr);
				toDate = sdf.parse(toDateStr);
			}
			Map<Long,IdAndNameVO> districtsMap = new HashMap<Long, IdAndNameVO>(0);
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
			
			List<Object[]> list1 = activityScopeDAO.getActivityLevelsByActivity(activityIdsLst.get(0));
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
					
					returnList.add(vo);
				}
			}
			
			Map<Long,Map<Long,Long>> totalScopeLocationsMap = new HashMap<Long,Map<Long, Long>>();
			if(returnList != null && !returnList.isEmpty()){
				for (IdAndNameVO idAndNameVO : returnList) {
					SearchAttributeVO searchAttributeVO = new SearchAttributeVO();
					searchAttributeVO.setScopeValue(idAndNameVO.getSessionNo());
					searchAttributeVO.setScopeId(3L);
					
					if(!commonMethodsUtilService.isListOrSetValid(searchAttributeVO.getLocationTypeIdsList()))
						searchAttributeVO.setLocationTypeIdsList(new ArrayList<Long>(0));
					
					if(idAndNameVO.getId() != null){
						if(idAndNameVO.getId().longValue() == 1L){
							searchAttributeVO.getLocationTypeIdsList().add(6L);
							searchAttributeVO.getLocationTypeIdsList().add(8L);
						}
						else if(idAndNameVO.getId().longValue() == 2L){
							searchAttributeVO.getLocationTypeIdsList().add(5L);
							searchAttributeVO.getLocationTypeIdsList().add(7L);
						}
						else if(idAndNameVO.getId().longValue() == 3L){
							searchAttributeVO.getLocationTypeIdsList().add(3L);
						}
						else if(idAndNameVO.getId().longValue() == 4L)
							searchAttributeVO.getLocationTypeIdsList().add(2L);
						else if(idAndNameVO.getId().longValue() == 5L)
							searchAttributeVO.getLocationTypeIdsList().add(4L);					
					}
					
					List<Object[]> areasList  = locationInfoDAO.areaCountDetailsListByAreaIdsOnScope(searchAttributeVO,null);
					if(commonMethodsUtilService.isListOrSetValid(areasList)){
						for (Object[] param : areasList) {
							
							Map<Long,Long> totalLocationsMap = new HashMap<Long, Long>(0);
							Long count = 0L;
							if(totalScopeLocationsMap.get(idAndNameVO.getTdpcadreId()) != null){
								totalLocationsMap = totalScopeLocationsMap.get(idAndNameVO.getTdpcadreId());
								count=totalLocationsMap.get(commonMethodsUtilService.getLongValueForObject(param[2]));
							}
							if(count == null)
								count = 0L;
							totalLocationsMap.put(commonMethodsUtilService.getLongValueForObject(param[2]), count+commonMethodsUtilService.getLongValueForObject(param[1]));
							totalScopeLocationsMap.put(idAndNameVO.getTdpcadreId(), totalLocationsMap);
						}
					}
				}
			}
			
			List<Object[]> list = activityLocationInfoDAO.activitiesDistrictWiseCohort(activityIdsLst,fromDate, toDate);
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
					vo.setName(commonMethodsUtilService.getStringValueForObject(obj[4]) +" - "+commonMethodsUtilService.getStringValueForObject(obj[5])+" Level Activity ");
					vo.setCountOfActivityLocationInfo(Long.valueOf(obj[1] != null ? obj[1].toString():""));
					vo.setLocationId(Long.valueOf(obj[2] != null ? obj[2].toString():""));
					vo.setLocationName(commonMethodsUtilService.getStringValueForObject(obj[3]));
					vo.setActualMobNumber(commonMethodsUtilService.getStringValueForObject(obj[5]));
					vo.setPerc("0.00");
					vo.setRemainingPerc("0.00");
					
					Map<Long,Long> totalLocationsMap = new HashMap<Long, Long>(0);
					if(totalScopeLocationsMap.get(vo.getId()) != null){
						totalLocationsMap = totalScopeLocationsMap.get(vo.getId());
					}
					
					Long totalCount = totalLocationsMap.get(vo.getLocationId());
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
					}
					/*
					Collections.sort(vo.getDistList(), new Comparator<IdAndNameVO>() {
						public int compare(IdAndNameVO o1, IdAndNameVO o2) {
							return o1.getLocationName().compareTo(o2.getLocationName());
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
	public void buildDataToList(List<EventDetailsVO> returnList,Long districtId,Long activityScopeId,String searchType,Long stateId){
		List<Object[]> planedList = activityLocationInfoDAO.getDistrictWiseActivityCounts(districtId,activityScopeId,searchType,stateId,"planned");
		List<Object[]> list1 = activityConductedInfoDAO.getDistrictWiseActivityCounts(districtId,activityScopeId,searchType,stateId,"planned");
		if(commonMethodsUtilService.isListOrSetValid(list1))
			if(!commonMethodsUtilService.isListOrSetValid(planedList))
				planedList = new ArrayList<Object[]>(0);
				planedList.addAll(list1);
			setDataToVO(planedList,returnList,"planned");
			
		List<Object[]> condtdcList = activityLocationInfoDAO.getDistrictWiseActivityCounts(districtId,activityScopeId,searchType,stateId,"infocell");
		list1 = activityConductedInfoDAO.getDistrictWiseActivityCounts(districtId,activityScopeId,searchType,stateId,"infocell");
		if(commonMethodsUtilService.isListOrSetValid(list1))
			if(!commonMethodsUtilService.isListOrSetValid(condtdcList))
				condtdcList = new ArrayList<Object[]>(0);
				condtdcList.addAll(list1);
			setDataToVO(condtdcList,returnList,"infocell");
		List<Object[]> ivrList = activityLocationInfoDAO.getDistrictWiseActivityCounts(districtId,activityScopeId,searchType,stateId,"ivr");
		list1 = activityConductedInfoDAO.getDistrictWiseActivityCounts(districtId,activityScopeId,searchType,stateId,"ivr");
		if(commonMethodsUtilService.isListOrSetValid(list1))
			if(!commonMethodsUtilService.isListOrSetValid(ivrList))
				ivrList = new ArrayList<Object[]>(0);
				ivrList.addAll(list1);
			setDataToVO(ivrList,returnList,"ivr");
	}
	public List<EventDetailsVO> getDistrictWiseActivityCounts(Long districtId,Long activityScopeId, String searchType ,Long stateId ){
		
		List<EventDetailsVO> returnList = new ArrayList<EventDetailsVO>();
		try{
			/*ActivityScope scope = activityScopeDAO.get(activityScopeId);
			if(scope != null){
				if(scope.getActivityLevelId().longValue() == 1L || scope.getActivityLevelId().longValue() == 2L )
					searchType ="constituency";
				else if(scope.getActivityLevelId().longValue() == 3L )
					searchType ="district";
				else if(scope.getActivityLevelId().longValue() == 4L )
					searchType ="state";
				else if(scope.getActivityLevelId().longValue() == 5L )
					searchType ="constituency";
			}*/
			
			if(searchType != null && searchType.equalsIgnoreCase("constituency")){
				buildDataToList(returnList,districtId,activityScopeId,searchType,stateId);
			}else if(searchType != null && searchType.equalsIgnoreCase("mandal")){
				
				buildDataToList(returnList,districtId,activityScopeId,"mandal",stateId);
				buildDataToList(returnList,districtId,activityScopeId,"town",stateId);
				
			}else if(searchType != null && searchType.equalsIgnoreCase("villageWard")){
				String district = districtId.toString();
				buildDataToList(returnList,Long.valueOf(district.substring(1)),activityScopeId,"village",stateId);
				buildDataToList(returnList,Long.valueOf(district.substring(1)),activityScopeId,"ward",stateId);
				
			}
			
			 List<Long> districts = new ArrayList<Long>();
			 districts.add(districtId);
			 List<Long> activityScopeIds = new ArrayList<Long>();
			 activityScopeIds.add(activityScopeId);
			Long levelId = activityScopeDAO.getActivityLevelIdByActivityScopeId(activityScopeId);
			
			if(levelId != null && levelId.longValue() == 1l){
				List<Object[]> panchayatsImgs = activityDocumentDAO.getImagesCoveredAndTotalImagesForConstituencies(districts,activityScopeIds,searchType,"panchayat");
					setImagesCoveredAndTotalImages(panchayatsImgs,returnList);
				List<Object[]> wardsImgs = activityDocumentDAO.getImagesCoveredAndTotalImagesForConstituencies(districts,activityScopeIds,searchType,"ward");
					setImagesCoveredAndTotalImages(wardsImgs,returnList);
			}else if(levelId != null && levelId.longValue() == 2l){
				List<Object[]> mandalImgs = activityDocumentDAO.getImagesCoveredAndTotalImagesForConstituencies(districts,activityScopeIds,searchType,"mandal");
					setImagesCoveredAndTotalImages(mandalImgs,returnList);
				List<Object[]> townImgs = activityDocumentDAO.getImagesCoveredAndTotalImagesForConstituencies(districts,activityScopeIds,searchType,"town");
					setImagesCoveredAndTotalImages(townImgs,returnList);
			}else if(levelId != null && levelId.longValue() == 5l){
				List<Object[]> mandalImgs = activityDocumentDAO.getImagesCoveredAndTotalImagesForConstituencies(districts,activityScopeIds,searchType,"constituency");
					setImagesCoveredAndTotalImages(mandalImgs,returnList);
			}
			else if(levelId != null && levelId.longValue() == 3l){
				List<Object[]> mandalImgs = activityDocumentDAO.getImagesCoveredAndTotalImagesForConstituencies(districts,activityScopeIds,"district","district");
				setImagesCoveredAndTotalImages(mandalImgs,returnList);
			}
			else if(levelId != null && levelId.longValue() == 4l){
				List<Object[]> mandalImgs = activityDocumentDAO.getImagesCoveredAndTotalImagesForConstituencies(districts,activityScopeIds,"state","state");
				setImagesCoveredAndTotalImages(mandalImgs,returnList);
			}
			Map<Long,Map<Long,Long>> totalScopeLocationsMap = new HashMap<Long,Map<Long,Long>>();
			SearchAttributeVO searchAttributeVO = new SearchAttributeVO();
	         
			if(searchType != null && searchType.equalsIgnoreCase("constituency"))
				searchAttributeVO.setScopeId(4L);
			else if(searchType != null && searchType.equalsIgnoreCase("mandal"))
				searchAttributeVO.setScopeId(5L);
			else if(searchType != null && searchType.equalsIgnoreCase("villageWard"))
				searchAttributeVO.setScopeId(5L);
	          
	          if(!commonMethodsUtilService.isListOrSetValid(searchAttributeVO.getLocationTypeIdsList()))
	            searchAttributeVO.setLocationTypeIdsList(new ArrayList<Long>(0));
	          
	          if(levelId != null){
	            if(levelId.longValue() == 1L){
	              searchAttributeVO.getLocationTypeIdsList().add(6L);
	              searchAttributeVO.getLocationTypeIdsList().add(8L);
	            }
	            else if(levelId.longValue() == 2L){
	              searchAttributeVO.getLocationTypeIdsList().add(5L);
	              searchAttributeVO.getLocationTypeIdsList().add(7L);
	            }
	            else if(levelId.longValue() == 3L){
	              searchAttributeVO.getLocationTypeIdsList().add(3L);
	            }
	            else if(levelId.longValue() == 4L)
	              searchAttributeVO.getLocationTypeIdsList().add(2L);
	            else if(levelId.longValue() == 5L)
	              searchAttributeVO.getLocationTypeIdsList().add(4L);          
	          }
	          
	          if(commonMethodsUtilService.isListOrSetValid(searchAttributeVO.getLocationTypeIdsList()) && searchAttributeVO.getLocationTypeIdsList().size()==1 && 
	        		  searchAttributeVO.getLocationTypeIdsList().get(0).longValue() == searchAttributeVO.getScopeId().longValue()){
	        	  setTotalLocationsToVO(returnList,null,1L);
	          }else{
	        	  List<Object[]> areasList  = locationInfoDAO.areaCountDetailsListByAreaIdsOnScope(searchAttributeVO,null);
		          if(commonMethodsUtilService.isListOrSetValid(areasList)){
		            for (Object[] param : areasList) {
		              
		              Map<Long,Long> totalLocationsMap = new HashMap<Long, Long>();
		              Long count = 0L;
		              if(totalScopeLocationsMap.get(activityScopeId) != null){
		                totalLocationsMap = totalScopeLocationsMap.get(activityScopeId);
		                count=totalLocationsMap.get(commonMethodsUtilService.getLongValueForObject(param[2]));
		              }
		              if(count == null)
		                count = 0L;
		              totalLocationsMap.put(commonMethodsUtilService.getLongValueForObject(param[2]), count+commonMethodsUtilService.getLongValueForObject(param[1]));
		              totalScopeLocationsMap.put(activityScopeId, totalLocationsMap);
		            }
		          }
	          }
	          
	          if(commonMethodsUtilService.isMapValid(totalScopeLocationsMap)){
	        	  Map<Long,Long> locatnContMap = totalScopeLocationsMap.get(activityScopeId);
	        	  if(commonMethodsUtilService.isMapValid(locatnContMap)){
	        		  setTotalLocationsToVO(returnList,locatnContMap,null);
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
	
	public void setDataToVO(List<Object[]> planedList,List<EventDetailsVO> returnList,String type){
		
		try{
			
		if(planedList != null && planedList.size() > 0){
				for(Object[] obj : planedList){
					EventDetailsVO vo = getMatchedVo((Long)obj[0],returnList);
					
					if(vo == null){
						vo = new EventDetailsVO();
						returnList.add(vo);
					}
					
					vo.setId(obj[0] != null ? (Long)obj[0] : 0l);
					vo.setName(obj[1] != null ? obj[1].toString() : "");
					
					if(type != null && type.equalsIgnoreCase("planned"))
						vo.setInviteeCount(obj[2] != null ? vo.getInviteeCount()+(Long)obj[2] : 0l);
					else if(type != null && type.equalsIgnoreCase("infocell"))
						vo.setInviteeNotAttendedCount(obj[2] != null ? vo.getInviteeNotAttendedCount()+(Long)obj[2] : 0l);
					else if(type != null && type.equalsIgnoreCase("ivr"))
						vo.setInviteeAttendedCount(obj[2] != null ? vo.getInviteeAttendedCount()+(Long)obj[2] : 0l);
					
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
					EventDetailsVO vo = getMatchedVo((Long)obj[0],returnList);
					if(vo != null){
						vo.setImagesCovered(obj[1] != null ? vo.getImagesCovered()+(Long)obj[1] : 0l);
						vo.setTotalImages(obj[2] != null ? vo.getTotalImages()+(Long)obj[2] : 0l);
					}
				}
			}
		}catch(Exception e){
			e.printStackTrace();
			LOG.error("Exception occurred at setImagesCoveredAndTotalImages() of CoreDashboardService", e);
		}
	}
}
