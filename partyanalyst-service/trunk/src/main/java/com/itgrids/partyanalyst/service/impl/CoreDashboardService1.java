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
	public CommitteeDataVO getCommitteesBasicCountReport(Long userAccessLevelId,List<Long> userAccessLevelValues,String state,List<Long> basicCommitteeIds,String startDateString,String endDateString){
		
		LOG.info(" entered in to getCommitteesBAsicCountReport() ");
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		CommitteeDataVO finalVO = new CommitteeDataVO();
		Date startDate = null;
		Date endDate = null;
		try{
		     if(startDateString != null && !startDateString.isEmpty()){
		    	 startDate = sdf.parse(startDateString);
		     }
		     if(endDateString != null && !endDateString.isEmpty()){
		    	 endDate = sdf.parse(endDateString);
		     }
		     
		     //Making BO.
		     CommitteeInputVO committeeBO = new CommitteeInputVO();
		     getRequiredCommitteeLevelIds(userAccessLevelId,committeeBO,userAccessLevelValues);
		     committeeBO.setBasicCommitteeIds(basicCommitteeIds);
		     committeeBO.setStartDate(startDate);
		     committeeBO.setEndDate(endDate);
		     committeeBO.setState(state);
		     
		     Map<Long,CommitteeDataVO> committeeLevelMap = new LinkedHashMap<Long,CommitteeDataVO>(0);
		     setCommitteeLevelstoMap(committeeLevelMap,committeeBO.getTdpCommitteeLevelIds());
		  
		     List<Object[]> totalCommitteesList     =  tdpCommitteeDAO.getCommitteesTotalOrStartedOrCompletedCount(committeeBO,null);
		     List<Object[]> completedCommitteesList =  tdpCommitteeDAO.getCommitteesTotalOrStartedOrCompletedCount(committeeBO,"completed");
		     List<Object[]> startedCommitteesList   =  tdpCommitteeDAO.getCommitteesTotalOrStartedOrCompletedCount(committeeBO,"started");
		     
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
	    					 levelVO.getMainVO().setCompletedPerc( caclPercantage(levelVO.getMainVO().getCompletedCount(),levelVO.getMainVO().getTotalCount()) );
	    					 levelVO.getMainVO().setStartedPerc( caclPercantage(levelVO.getMainVO().getStartedCount(),levelVO.getMainVO().getTotalCount()) );
	    				 }
	    				 if(levelVO.getAffliatedVO() != null && levelVO.getAffliatedVO().getTotalCount() > 0l ){
	    					 levelVO.getAffliatedVO().setCompletedPerc( caclPercantage(levelVO.getAffliatedVO().getCompletedCount(),levelVO.getAffliatedVO().getTotalCount()) );
	    					 levelVO.getAffliatedVO().setStartedPerc( caclPercantage(levelVO.getAffliatedVO().getStartedCount(),levelVO.getAffliatedVO().getTotalCount()) );
	    				 }
	    			 }
	    		 }
	    		 finalVO.setSubList(finalList);
	    	 }
	    	 
	    	 if(finalVO.getMainVO() != null && finalVO.getMainVO().getTotalCount() > 0l){
	    		 finalVO.getMainVO().setCompletedPerc(caclPercantage(finalVO.getMainVO().getCompletedCount(),finalVO.getMainVO().getTotalCount()));
	    		 finalVO.getMainVO().setStartedPerc(caclPercantage(finalVO.getMainVO().getStartedCount(),finalVO.getMainVO().getTotalCount()));
	    	 }
			 if(finalVO.getAffliatedVO() != null && finalVO.getAffliatedVO().getTotalCount() > 0l ){
				 finalVO.getAffliatedVO().setCompletedPerc(caclPercantage(finalVO.getAffliatedVO().getCompletedCount(),finalVO.getAffliatedVO().getTotalCount()));
				 finalVO.getAffliatedVO().setStartedPerc(caclPercantage(finalVO.getAffliatedVO().getStartedCount(),finalVO.getAffliatedVO().getTotalCount()) );
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
			inputVO.setAssemblyConstIds(delimitationConstituencyAssemblyDetailsDAO.getAssemblyConstituenciesByParliamentList(userAccessLevelValues));
			
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
		public List<CommitteeDataVO> getLevelWiseBasicCommitteesCountReport(Long userAccessLevelId,List<Long> userAccessLevelValues,String state,List<Long> basicCommitteeIds,String startDateString,String endDateString){
		
		LOG.info(" entered in to getLevelWiseBasicCommitteesCountReport() ");
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		List<CommitteeDataVO> finalList = null;
		Date startDate = null;
		Date endDate = null;
		try{
		     if(startDateString != null && !startDateString.isEmpty()){
		    	 startDate = sdf.parse(startDateString);
		     }
		     if(endDateString != null && !endDateString.isEmpty()){
		    	 endDate = sdf.parse(endDateString);
		     }
		     
		     //Making BO.
		     CommitteeInputVO committeeBO = new CommitteeInputVO();
		     getRequiredCommitteeLevelIds(userAccessLevelId,committeeBO,userAccessLevelValues);
		     committeeBO.setBasicCommitteeIds(basicCommitteeIds);
		     committeeBO.setStartDate(startDate);
		     committeeBO.setEndDate(endDate);
		     committeeBO.setState(state);
		     
		     Map<Long,CommitteeDataVO> committeeLevelMap = new LinkedHashMap<Long,CommitteeDataVO>(0);
		     setCommitteeLevelstoMap(committeeLevelMap,committeeBO.getTdpCommitteeLevelIds(),basicCommitteeIds);
		  
		     List<Object[]> totalCommitteesList      =  tdpCommitteeDAO.levelWiseBasicCommitteesCount(committeeBO,null);
		     List<Object[]> completedCommitteesList  =  tdpCommitteeDAO.levelWiseBasicCommitteesCount(committeeBO,"completed");
		     List<Object[]> startedCommitteesList    =  tdpCommitteeDAO.levelWiseBasicCommitteesCount(committeeBO,"started");
		     List<Object[]> notStartedCommitteesList =  tdpCommitteeDAO.levelWiseBasicCommitteesCount(committeeBO,"notStarted");
		     
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
			e.printStackTrace();
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
								basicCommitteeVO.setCompletedPerc( caclPercantage(basicCommitteeVO.getCompletedCount(),basicCommitteeVO.getTotalCount()) );
								basicCommitteeVO.setStartedPerc( caclPercantage(basicCommitteeVO.getStartedCount(),basicCommitteeVO.getTotalCount()) );
								basicCommitteeVO.setNotStartedPerc( caclPercantage(basicCommitteeVO.getNotStartedCount(),basicCommitteeVO.getTotalCount()) );
							}
						}
					}
				}
			}
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
			    	 getBasicCommitteesPercantage(finalList);
			    		 
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
			     List<Date> datesList = getDates(startDateString,endDateString,new SimpleDateFormat("dd/MM/yyyy"));
			     
			     //Making BO.
			     CommitteeInputVO committeeBO = new CommitteeInputVO();
			     committeeBO.setBasicCommitteeIds(basicCommitteeIds);
			     committeeBO.setStartDate(datesList.get(0));
			     committeeBO.setEndDate(datesList.get(1));
			     if(state.equalsIgnoreCase("ap")){
			    	 committeeBO.setStateId(1l);	 
			     }else if(state.equalsIgnoreCase("ts")){
			    	 committeeBO.setStateId(36l);	 
			     }
			     
			     
			     //get activitymemberid and usertypeid if not there.
			     if( activityMemberId == null || activityMemberId <= 0l){
				    Object[] activityMemberIdAndAccessType= activityMemberAccessTypeDAO.getUserAccessTypeAndActivityMemberIdByUserId(userId);
				    if( activityMemberIdAndAccessType != null && activityMemberIdAndAccessType.length>0){
					   activityMemberId = activityMemberIdAndAccessType[0] != null ? (Long)activityMemberIdAndAccessType[0] : 0l;
					   userTypeId = activityMemberIdAndAccessType[1] != null ? (Long)activityMemberIdAndAccessType[1] : 0l;
				    }
			     }
		   
			     Map<Long,List<Long>> ParentChildUserTypesMap = getParentUserTypesAndItsChildUserTypes();
		         
		         Map<Long,Map<Long,UserTypeVO>> userTypesMap = new LinkedHashMap<Long, Map<Long,UserTypeVO>>(0);
		         
		         Map<Long,Set<Long>> locationLevelIdsMap = new HashMap<Long, Set<Long>>(); 
		         
		         
			     setBasicDataToMap(userTypesMap,activityMemberId,userTypeId,ParentChildUserTypesMap,locationLevelIdsMap);
			     
			   //get assembly constituencies for parliament constituencies.
			     Set<Long> parliamentConstituencyIds = locationLevelIdsMap.get(IConstants.PARLIAMENT_LEVEl_ACCESS_ID);
			     Map<Long,List<Long>> assemblyListByPcMap =  new HashMap<Long, List<Long>>();
			     Set<Long> assemblyConstituencyIdsByParliamentIds = new HashSet<Long>();
			     if(parliamentConstituencyIds != null && parliamentConstituencyIds.size()>0){
		               	 List<Object[]> list = delimitationConstituencyAssemblyDetailsDAO.getAcConstituenciesByPcList(new ArrayList<Long>(parliamentConstituencyIds));
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
		               			 assemblyConstituencyIdsByParliamentIds.add(obj[1]!=null?(Long)obj[1]:0l);
		               		 }
		               	 }
		         }
			     
			     //add parliamnet level access level values to assembly access level values.
			     if(parliamentConstituencyIds != null && parliamentConstituencyIds.size()>0){
			    	 Set<Long> assemblyConstituencyIds = locationLevelIdsMap.get(IConstants.ASSEMBLY_LEVEl_ACCESS_ID);
			    	 if(assemblyConstituencyIds == null){
			    		 assemblyConstituencyIds = new HashSet<Long>();
			    		 assemblyConstituencyIds.addAll(assemblyConstituencyIdsByParliamentIds);
			    		 locationLevelIdsMap.put(IConstants.ASSEMBLY_LEVEl_ACCESS_ID, assemblyConstituencyIds);
			    	 }else{
			    		 assemblyConstituencyIds.addAll(assemblyConstituencyIdsByParliamentIds);
			    	 }
			    	 
			     }
			     
			     
			     //get commitees count based on location level id and location level values.
			     Map<String,UserTypeVO> locationLevelCountsMap = new HashMap<String, UserTypeVO>(0);
			     if(locationLevelIdsMap != null && locationLevelIdsMap.size()>0){
			    	 for(Long accessLevelId : locationLevelIdsMap.keySet()){
			    		 
			    		 getCommitteeLevelIdsByUserAccessLevelId(accessLevelId,new ArrayList<Long>(locationLevelIdsMap.get(accessLevelId)),committeeBO);
			    		 committeeBO.setStatus(null);
			    		 List<Object[]> totalCountList = tdpCommitteeDAO.getLocationWiseCommitteesCountByLocIds(committeeBO);
			    		 committeeBO.setStatus("completed");
			    		 List<Object[]> completedCountList = tdpCommitteeDAO.getLocationWiseCommitteesCountByLocIds(committeeBO);
		                 setCountsToALocation("total",totalCountList,locationLevelCountsMap,accessLevelId);
		                 setCountsToALocation("completed",completedCountList,locationLevelCountsMap,accessLevelId);
			    	 }    
			     }
			     
			     userTypesList = getMemberRelatedCountsOrPercantage(userTypesMap,"counts",locationLevelCountsMap,assemblyListByPcMap);
			     getMemberRelatedCountsOrPercantage(userTypesMap,"percanatge",null,null);
			   
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

		public void setBasicDataToMap( Map<Long,Map<Long,UserTypeVO>> userTypesMap , Long activityMemberId ,Long userTypeId, Map<Long,List<Long>> ParentChildUserTypesRelationMap,Map<Long,Set<Long>> locationLevelIdsMap){
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
								 setBasicDataToMap(userTypesMap,activityMemberVO.getActivityMemberId(),activityMemberVO.getUserTypeId(),ParentChildUserTypesRelationMap,locationLevelIdsMap);
								 
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
				e.printStackTrace();
			}
		}
		public List<List<UserTypeVO>> getMemberRelatedCountsOrPercantage(Map<Long,Map<Long,UserTypeVO>> userTypesMap,String type,Map<String,UserTypeVO> locationLevelCountsMap,Map<Long,List<Long>> assemblyListByPcMap){
			
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
								   
								   if(memberVO.getLocationValuesSet() != null && memberVO.getLocationValuesSet().size()>0){
									   for(Long locationValue : memberVO.getLocationValuesSet()){
										   if(memberVO.getLocationLevelId().longValue() == IConstants.PARLIAMENT_LEVEl_ACCESS_ID){
											   List<Long> assemblyIds = assemblyListByPcMap.get(locationValue);
											   if(assemblyIds!=null && assemblyIds.size()>0){
												   for(Long assemblyId : assemblyIds){
													   String key = IConstants.ASSEMBLY_LEVEl_ACCESS_ID+"_"+assemblyId;
													   UserTypeVO countVO = locationLevelCountsMap.get(key);
													   if(countVO != null){
														   memberVO.setTotalCount(memberVO.getTotalCount() + countVO.getTotalCount());
														   memberVO.setCompletedCount(memberVO.getCompletedCount()+countVO.getCompletedCount());
													   }
												   }
											   }
										   }else{
											   String key = memberVO.getLocationLevelId()+"_"+locationValue;
											   UserTypeVO countVO = locationLevelCountsMap.get(key);
											   if(countVO != null){
												   memberVO.setTotalCount(memberVO.getTotalCount() + countVO.getTotalCount());
												   memberVO.setCompletedCount(memberVO.getCompletedCount()+countVO.getCompletedCount());
											   }
										   }
									   }
								   } 
							   }else if(type.equalsIgnoreCase("percanatge")){
								   if(memberVO.getTotalCount()!=null && memberVO.getTotalCount() > 0l){
									   memberVO.setCompletedPerc( caclPercantage(memberVO.getCompletedCount(),memberVO.getTotalCount()) );
								   }
							   }
						   }
					   }
					   userTypesList.add(new ArrayList<UserTypeVO>(membersMap.values()));
				   }
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
		public void getCommitteeLevelIdsByUserAccessLevelId(Long userAccessLevelId,List<Long> userAccessLevelValues,CommitteeInputVO inputVO){
			
			if(userAccessLevelId.longValue() == IConstants.STATE_LEVEl_ACCESS_ID.longValue() ){
				
				inputVO.setTdpCommitteeLevelIds(Arrays.asList(IConstants.STATE_ACCESS_REQUIED_COMMITTEE_LEVEl_IDS));	
				inputVO.setStateIds(userAccessLevelValues);
				
			}else if(userAccessLevelId.longValue() == IConstants.DISTRICT_LEVEl_ACCESS_ID.longValue() ){
				
				inputVO.setTdpCommitteeLevelIds(Arrays.asList(IConstants.DISTRICT_ACCESS_REQUIED_COMMITTEE_LEVEl_IDS));
				inputVO.setDistrictIds(userAccessLevelValues);
				
			}else if(userAccessLevelId.longValue() == IConstants.ASSEMBLY_LEVEl_ACCESS_ID.longValue()){
				
				inputVO.setTdpCommitteeLevelIds(Arrays.asList(IConstants.CONSTITUENCY_ACCESS_REQUIED_COMMITTEE_LEVEl_IDS));
				inputVO.setAssemblyConstIds(userAccessLevelValues);
				
		    }else if(userAccessLevelId.longValue() == IConstants.MANDAL_LEVEl_ID.longValue()){
				
				inputVO.setTdpCommitteeLevelIds(Arrays.asList(IConstants.MANDAL_ACCESS_REQUIED_COMMITTEE_LEVEl_IDS));
				inputVO.setTehsilIds(userAccessLevelValues);
			}
			
		}
		
		
}
