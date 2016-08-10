package com.itgrids.partyanalyst.service.impl;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import com.itgrids.partyanalyst.dao.IDelimitationConstituencyAssemblyDetailsDAO;
import com.itgrids.partyanalyst.dao.ITdpCommitteeDAO;
import com.itgrids.partyanalyst.dao.ITdpCommitteeLevelDAO;
import com.itgrids.partyanalyst.dto.CommitteeDataVO;
import com.itgrids.partyanalyst.dto.CommitteeInputVO;
import com.itgrids.partyanalyst.dto.CommitteeVO;
import com.itgrids.partyanalyst.service.ICoreDashboardService1;
import com.itgrids.partyanalyst.utils.IConstants;

public class CoreDashboardService1 implements ICoreDashboardService1{
	
	private final static Logger LOG = Logger.getLogger(CoreDashboardService1.class);
	
	//Attributes
	 private IDelimitationConstituencyAssemblyDetailsDAO delimitationConstituencyAssemblyDetailsDAO;
	 private ITdpCommitteeDAO tdpCommitteeDAO;
	 private ITdpCommitteeLevelDAO tdpCommitteeLevelDAO;
	 
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
	
	public Double caclPercantage(Long subCount,Long totalCount){
		Double d = new BigDecimal(subCount * 100.0/totalCount).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
		return d;
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
	
	
}
