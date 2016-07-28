package com.itgrids.partyanalyst.service.impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import com.itgrids.partyanalyst.dao.IDashboardUserAccessLevelDAO;
import com.itgrids.partyanalyst.dao.IDashboardUserAccessTypeDAO;
import com.itgrids.partyanalyst.dao.ITdpBasicCommitteeDAO;
import com.itgrids.partyanalyst.dao.ITdpCommitteeDAO;
import com.itgrids.partyanalyst.dao.ITdpCommitteeLevelDAO;
import com.itgrids.partyanalyst.dto.CommitteeVO;
import com.itgrids.partyanalyst.dto.UserDataVO;
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
	
	//business methods.
	public List<UserDataVO> getUserAccessLevelAndValues(Long userId){
		
		LOG.info(" entered in to getUserAccessLevelAndValues()");
		List<UserDataVO> finalList = null;
		try{
			
			List<Object[]> userLevelsList = dashboardUserAccessLevelDAO.getUserAccessLevelAndValues(userId);
			if( userLevelsList != null && userLevelsList.size()>0){
				finalList = new ArrayList<UserDataVO>();
				for (Object[] obj : userLevelsList) {
					UserDataVO VO = new UserDataVO();
					VO.setUserId(userId);
					VO.setUserLevelId( obj[0]!= null ? (Long)obj[0] : 0l);
					VO.setLevel(obj[1]!= null ? obj[1].toString() : "");
					VO.setLevelValue(obj[2]!= null ? (Long)obj[2] : 0l);
					finalList.add(VO);
				}
			}
		}catch(Exception e){
			LOG.error("error occurred in getUserAccessLevelAndValues() of CoreDashboardService class",e);
		}
		return finalList;
	}
	
	public UserDataVO getUserTypeByUserId(Long userId){
		
		LOG.info(" entered in to getUserTypeByUserId() ");
		 UserDataVO finalVO = null;
		try{
			
			List<Object[]> userTypeList = dashboardUserAccessTypeDAO.getUserTypeByUserId(userId);
			if( userTypeList != null && userTypeList.size()>0){
			    Object[] obj = userTypeList.get(0);
				finalVO = new UserDataVO();
				finalVO.setUserId(userId);
				finalVO.setUserTypeId( obj[0]!= null ? (Long)obj[0] : 0l);
				finalVO.setUserType(obj[1]!= null ? obj[1].toString() : "");
			}
		}catch(Exception e){
			LOG.error("error occurred in getUserTypeByUserId() of CoreDashboardService class",e);
		}
		return finalVO;
	}
	
	public List<CommitteeVO> getCommitteesWiseLevelsBasedDetails(Long userAccessLevelId,List<Long> userAccessLevelValues,String state,List<Long> basicCommitteeIds,String startDateString,String endDateString){
		LOG.info(" entered in to getCommitteesWiseLevelsBasedDetails() ");
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
		     
		     if(userAccessLevelId == IConstants.DISTRICT_LEVEl_ID){
		    	 
		    	 List<Long> districtAccessRequiredLevelIds = Arrays.asList(IConstants.DISTRICT_ACCESS_REQUIED_COMMITTEE_LEVEl_IDS);
		    	 //instantiation.
		    	 Map<Long,CommitteeVO> basicCommitteeMap = new LinkedHashMap<Long,CommitteeVO>(0);
		    	 committeesInstantiationlogic(basicCommitteeMap,basicCommitteeIds,districtAccessRequiredLevelIds);
		    	 
		    	 List<Long> districtIds = userAccessLevelValues;
		    	 List<Object[]> completedList = tdpCommitteeDAO.getDistrictAccessDetails(districtIds,districtAccessRequiredLevelIds,state,basicCommitteeIds,startDate,endDate,"completed");
		    	 List<Object[]> startedList = tdpCommitteeDAO.getDistrictAccessDetails(districtIds,districtAccessRequiredLevelIds,state,basicCommitteeIds,startDate,endDate,"started");
		    	 List<Object[]> yetToStartList = tdpCommitteeDAO.getDistrictAccessDetails(districtIds,districtAccessRequiredLevelIds,state,basicCommitteeIds,startDate,endDate,"notStarted");
		    	 
		    	 setCommitteeStatusCount(completedList,basicCommitteeMap,"completed");
		    	 setCommitteeStatusCount(startedList,basicCommitteeMap,"started");
		    	 setCommitteeStatusCount(yetToStartList,basicCommitteeMap,"yetToStartList");
		    	 
		    	 finalList = getRequiredList(basicCommitteeMap);
		     }
			
		}catch(Exception e){
			LOG.error("exception occurred in getCommitteesWiseLevelsBasedDetails", e);
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
	public List<CommitteeVO>  getRequiredList(Map<Long,CommitteeVO> basicCommitteeMap){
		List<CommitteeVO> finalList = new ArrayList<CommitteeVO>();
		if(basicCommitteeMap != null && basicCommitteeMap.size() > 0){
			for (Map.Entry<Long, CommitteeVO> basicCommitteeEntry : basicCommitteeMap.entrySet()){
				if(basicCommitteeEntry.getValue()!=null && basicCommitteeEntry.getValue().getSubMap()!=null){
					basicCommitteeEntry.getValue().setSubList(new ArrayList<CommitteeVO>(basicCommitteeEntry.getValue().getSubMap().values()));
					basicCommitteeEntry.getValue().getSubMap().clear();
				}
			}
			finalList.addAll(basicCommitteeMap.values());
		}
		return finalList;
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
	
}
