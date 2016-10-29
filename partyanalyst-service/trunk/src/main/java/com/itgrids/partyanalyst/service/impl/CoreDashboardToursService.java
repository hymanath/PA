package com.itgrids.partyanalyst.service.impl;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.apache.log4j.Logger;

import com.itgrids.partyanalyst.dao.IActivityMemberAccessLevelDAO;
import com.itgrids.partyanalyst.dao.ISelfAppraisalCandidateDetailsDAO;
import com.itgrids.partyanalyst.dao.ISelfAppraisalCandidateLocationDAO;
import com.itgrids.partyanalyst.dto.ToursBasicVO;
import com.itgrids.partyanalyst.service.ICoreDashboardToursService;
import com.itgrids.partyanalyst.utils.CommonMethodsUtilService;

public class CoreDashboardToursService implements ICoreDashboardToursService {

	private final static Logger LOG = Logger.getLogger(CoreDashboardToursService.class);
	
	private ISelfAppraisalCandidateDetailsDAO selfAppraisalCandidateDetailsDAO;
	private ISelfAppraisalCandidateLocationDAO selfAppraisalCandidateLocationDAO;
	private IActivityMemberAccessLevelDAO activityMemberAccessLevelDAO;
	private CommonMethodsUtilService commonMethodsUtilService ;
	
	public void setSelfAppraisalCandidateDetailsDAO(
			ISelfAppraisalCandidateDetailsDAO selfAppraisalCandidateDetailsDAO) {
		this.selfAppraisalCandidateDetailsDAO = selfAppraisalCandidateDetailsDAO;
	}
	public void setSelfAppraisalCandidateLocationDAO(
			ISelfAppraisalCandidateLocationDAO selfAppraisalCandidateLocationDAO) {
		this.selfAppraisalCandidateLocationDAO = selfAppraisalCandidateLocationDAO;
	}
	public void setActivityMemberAccessLevelDAO(
			IActivityMemberAccessLevelDAO activityMemberAccessLevelDAO) {
		this.activityMemberAccessLevelDAO = activityMemberAccessLevelDAO;
	}
	public void setCommonMethodsUtilService(
			CommonMethodsUtilService commonMethodsUtilService) {
		this.commonMethodsUtilService = commonMethodsUtilService;
	}
	
	public ToursBasicVO getToursBasicOverviewCountDetails(Long stateId,String fromDateStr,String toDateStr,Long activityMemberId){
		
		ToursBasicVO resultVO = new ToursBasicVO();
		ToursBasicVO overAllDtlsVO = new ToursBasicVO();
		Map<Long,ToursBasicVO> LeaderMemebersMap = new HashMap<Long, ToursBasicVO>(0);
		Map<Long,Set<Long>> locationAccessLevelMap = new HashMap<Long, Set<Long>>(0);
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		Date fromDate=null;
	    Date toDate=null;
		try{
			if(fromDateStr != null && fromDateStr.trim().length()>0 && toDateStr!= null && toDateStr.trim().length()>0){
				 fromDate = sdf.parse(fromDateStr);
				 toDate = sdf.parse(toDateStr);
			 }
			 List<Object[]> rtrnUsrAccssLvlIdAndVlusObjLst=activityMemberAccessLevelDAO.getLocationLevelAndValuesByActivityMembersId(activityMemberId);
			    if(rtrnUsrAccssLvlIdAndVlusObjLst != null && !rtrnUsrAccssLvlIdAndVlusObjLst.isEmpty()){
				   for (Object[] param : rtrnUsrAccssLvlIdAndVlusObjLst) {
					Set<Long> locationValuesSet= locationAccessLevelMap.get((Long)param[0]);
					 if(locationValuesSet == null){
						 locationValuesSet = new java.util.HashSet<Long>();
						 locationAccessLevelMap.put((Long)param[0],locationValuesSet);
					 }
					 locationValuesSet.add(param[1] != null ? (Long)param[1]:0l);
				}
			   }
			   if(locationAccessLevelMap != null && !locationAccessLevelMap.isEmpty()){
				   
				   for(Entry<Long, Set<Long>> entry:locationAccessLevelMap.entrySet()){
					   
					   List<Object[]> rtrnLeaderCntObjLst = selfAppraisalCandidateLocationDAO.getNoOfLeadersCntDesignationByBasedOnUserAccessLevel(entry.getKey(), entry.getValue(),stateId);
					   	
					   if(rtrnLeaderCntObjLst != null &&  rtrnLeaderCntObjLst.size() > 0){
						   
					   		for(Object[] param:rtrnLeaderCntObjLst){
					   			
					   			ToursBasicVO leaderVO = new ToursBasicVO();
					   			leaderVO.setId(commonMethodsUtilService.getLongValueForObject(param[0]));
					   			leaderVO.setDesignation(commonMethodsUtilService.getStringValueForObject(param[1]));
					   			leaderVO.setNoOfLeaderCnt(commonMethodsUtilService.getLongValueForObject(param[2]));
					   			LeaderMemebersMap.put(leaderVO.getId(), leaderVO);
					   			//Calculating overAll 
					   			overAllDtlsVO.setNoOfLeaderCnt(overAllDtlsVO.getNoOfLeaderCnt()+leaderVO.getNoOfLeaderCnt());
					   			
					   		}
					   	}
				   }
			   }
			   if(locationAccessLevelMap != null && !locationAccessLevelMap.isEmpty()){
				   
				   for(Entry<Long, Set<Long>> entry:locationAccessLevelMap.entrySet()){
					   
					   List<Object[]> rtrnLeaderToursDtlsObjLst = selfAppraisalCandidateDetailsDAO.getToursDetailstDesignationByBasedOnUserAccessLevel(entry.getKey(),entry.getValue(), stateId, fromDate, toDate);
					   		
					   if(rtrnLeaderToursDtlsObjLst != null && !rtrnLeaderToursDtlsObjLst.isEmpty()){
						   
					   			for(Object[] param:rtrnLeaderToursDtlsObjLst){
					   				
					   				ToursBasicVO leaderVO = LeaderMemebersMap.get(commonMethodsUtilService.getLongValueForObject(param[0]));
					   				
					   					if(leaderVO != null){
					   						
					   					   leaderVO.setSubmitedLeaderCnt(commonMethodsUtilService.getLongValueForObject(param[1]));
				    					   leaderVO.setNotSubmitedLeaserCnt(leaderVO.getNoOfLeaderCnt()-leaderVO.getSubmitedLeaderCnt());
				    					   leaderVO.setSubmitedCandidateTourPer(calculatePercantage(leaderVO.getSubmitedLeaderCnt(),leaderVO.getNoOfLeaderCnt()));
				    					   leaderVO.setNotsubmitedCandidateTourPer(calculatePercantage(leaderVO.getNotSubmitedLeaserCnt(), leaderVO.getNoOfLeaderCnt()));
				    					   leaderVO.setOwnToursCnt(commonMethodsUtilService.getLongValueForObject(param[2]));
				    					   leaderVO.setInchargerToursCnt(commonMethodsUtilService.getLongValueForObject(param[3]));
				    					   leaderVO.setTotalSubmittedToursCnt(leaderVO.getOwnToursCnt()+leaderVO.getInchargerToursCnt());
				    					   Double averageTours = leaderVO.getTotalSubmittedToursCnt().doubleValue()/leaderVO.getSubmitedLeaderCnt().doubleValue();
				    					   leaderVO.setAverageTours(averageTours);
				    					   //Calculating overAll 
				    					   overAllDtlsVO.setSubmitedLeaderCnt(overAllDtlsVO.getSubmitedLeaderCnt()+leaderVO.getSubmitedLeaderCnt());
				    					   overAllDtlsVO.setNotSubmitedLeaserCnt(overAllDtlsVO.getNotSubmitedLeaserCnt()+leaderVO.getNotSubmitedLeaserCnt());
				    					   overAllDtlsVO.setOwnToursCnt(overAllDtlsVO.getOwnToursCnt()+leaderVO.getOwnToursCnt());
				    					   overAllDtlsVO.setInchargerToursCnt(overAllDtlsVO.getInchargerToursCnt()+leaderVO.getInchargerToursCnt());
				    					   overAllDtlsVO.setTotalSubmittedToursCnt(overAllDtlsVO.getTotalSubmittedToursCnt()+leaderVO.getTotalSubmittedToursCnt());
				    					   overAllDtlsVO.setAverageTours(overAllDtlsVO.getAverageTours()+leaderVO.getAverageTours());
				    					   
					   					}
					   			}
					   		}
					   }
			}
			  //calculation overAll percentage
			   overAllDtlsVO.setSubmitedCandidateTourPer(calculatePercantage(overAllDtlsVO.getSubmitedLeaderCnt(), overAllDtlsVO.getNoOfLeaderCnt()));
			   overAllDtlsVO.setNotsubmitedCandidateTourPer(calculatePercantage(overAllDtlsVO.getNotSubmitedLeaserCnt(), overAllDtlsVO.getNoOfLeaderCnt())); 
			   //setting result to final result VO
			   resultVO.setOverAllDetailsVO(overAllDtlsVO);
			   if(LeaderMemebersMap != null && LeaderMemebersMap.size() > 0){
				   resultVO.setSubList(new ArrayList<ToursBasicVO>(LeaderMemebersMap.values()));   
				   LeaderMemebersMap.clear();
			   }
		}catch(Exception e){
			LOG.error("Error occured at getToursBasicOverviewCountDetails() in CoreDashboardToursService ",e);	
		}
		return resultVO;
	}
	public Double calculatePercantage(Long subCount,Long totalCount){
	Double d=0.0d;
	if(subCount.longValue()>0l && totalCount.longValue()==0l)
	LOG.error("Sub Count Value is "+subCount+" And Total Count Value  "+totalCount);

	if(totalCount.longValue() > 0l){
		 d = new BigDecimal(subCount * 100.0/totalCount).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();	 
	}
	return d;
	} 
}
