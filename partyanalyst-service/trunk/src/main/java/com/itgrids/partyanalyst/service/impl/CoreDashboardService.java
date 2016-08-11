package com.itgrids.partyanalyst.service.impl;

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

import org.apache.log4j.Logger;

import com.itgrids.partyanalyst.dao.IActivityMemberAccessLevelDAO;
import com.itgrids.partyanalyst.dao.IActivityMemberAccessTypeDAO;
import com.itgrids.partyanalyst.dao.IActivityMemberDAO;
import com.itgrids.partyanalyst.dao.IActivityMemberRelationDAO;
import com.itgrids.partyanalyst.dao.IConstituencyDAO;
import com.itgrids.partyanalyst.dao.IDashboardUserAccessLevelDAO;
import com.itgrids.partyanalyst.dao.IDashboardUserAccessTypeDAO;
import com.itgrids.partyanalyst.dao.IDelimitationConstituencyAssemblyDetailsDAO;
import com.itgrids.partyanalyst.dao.IDistrictDAO;
import com.itgrids.partyanalyst.dao.ITdpBasicCommitteeDAO;
import com.itgrids.partyanalyst.dao.ITdpCommitteeDAO;
import com.itgrids.partyanalyst.dao.ITdpCommitteeLevelDAO;
import com.itgrids.partyanalyst.dao.IUserTypeRelationDAO;
import com.itgrids.partyanalyst.dto.CommitteeVO;
import com.itgrids.partyanalyst.dto.UserDataVO;
import com.itgrids.partyanalyst.dto.UserTypeVO;
import com.itgrids.partyanalyst.service.ICoreDashboardService;
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
	
	//business methods.
	public UserDataVO getUserBasicDetails(Long userId){
		
		LOG.info(" entered in to getUserBasicDetails() ");
		UserDataVO finalVO = new UserDataVO();
		try{
			
			/*List<Object[]> userTypeList = dashboardUserAccessTypeDAO.getUserTypeByUserId(userId);
			List<Object[]> userLevelsList = dashboardUserAccessLevelDAO.getUserAccessLevelAndValues(userId);*/
			
			List<Object[]> userTypeList = activityMemberAccessTypeDAO.getActivityMemberUserAccessTypeByUserId(userId);
			List<Object[]> userLevelsList = activityMemberAccessLevelDAO.getActivityMemberUserAccessLevelAndValues(userId);
			
			finalVO.setUserId(userId);
			
			if( userTypeList != null && userTypeList.size()>0){
			    Object[] obj = userTypeList.get(0);
				finalVO.setUserTypeId( obj[0]!= null ? (Long)obj[0] : 0l);
				finalVO.setUserType(obj[1]!= null ? obj[1].toString() : "");
			}
			if( userLevelsList != null && userLevelsList.size()>0){
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
}
