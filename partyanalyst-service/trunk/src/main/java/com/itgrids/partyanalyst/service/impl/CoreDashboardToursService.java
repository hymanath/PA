package com.itgrids.partyanalyst.service.impl;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.apache.log4j.Logger;
import org.jfree.util.HashNMap;

import com.itgrids.partyanalyst.dao.IActivityMemberAccessLevelDAO;
import com.itgrids.partyanalyst.dao.IConstituencyDAO;
import com.itgrids.partyanalyst.dao.IDistrictDAO;
import com.itgrids.partyanalyst.dao.ISelfAppraisalCandidateDAO;
import com.itgrids.partyanalyst.dao.ISelfAppraisalCandidateDetailsDAO;
import com.itgrids.partyanalyst.dao.ISelfAppraisalCandidateLocationDAO;
import com.itgrids.partyanalyst.dto.ToursBasicVO;
import com.itgrids.partyanalyst.service.ICoreDashboardToursService;
import com.itgrids.partyanalyst.utils.CommonMethodsUtilService;
import com.itgrids.partyanalyst.utils.IConstants;
import com.sun.jersey.client.impl.CopyOnWriteHashMap;

public class CoreDashboardToursService implements ICoreDashboardToursService {

	private final static Logger LOG = Logger.getLogger(CoreDashboardToursService.class);
	
	private ISelfAppraisalCandidateDetailsDAO selfAppraisalCandidateDetailsDAO;
	private ISelfAppraisalCandidateLocationDAO selfAppraisalCandidateLocationDAO;
	private IActivityMemberAccessLevelDAO activityMemberAccessLevelDAO;
	private ISelfAppraisalCandidateDAO selfAppraisalCandidateDAO;
	private IConstituencyDAO constituencyDAO;
	private IDistrictDAO districtDAO;
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
	public ISelfAppraisalCandidateDAO getSelfAppraisalCandidateDAO() {
		return selfAppraisalCandidateDAO;
	}
	public void setSelfAppraisalCandidateDAO(
			ISelfAppraisalCandidateDAO selfAppraisalCandidateDAO) {
		this.selfAppraisalCandidateDAO = selfAppraisalCandidateDAO;
	}
	public IConstituencyDAO getConstituencyDAO() {
		return constituencyDAO;
	}
	public void setConstituencyDAO(IConstituencyDAO constituencyDAO) {
		this.constituencyDAO = constituencyDAO;
	}
	public void setDistrictDAO(IDistrictDAO districtDAO) {
		this.districtDAO = districtDAO;
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
	
	public List<ToursBasicVO> getDistrictWiseToursSubmitedDetails(Long stateId,String fromDateStr,String toDateStr,Long activityMemberId,Long userTypeId){
		List<ToursBasicVO> resultList = new ArrayList<ToursBasicVO>();
		Set<Long> locationValues = new java.util.HashSet<Long>();
		Map<Long,Map<Long,Set<Long>>> candiateAccessLevelMap = new CopyOnWriteHashMap();
		Map<Long,Map<Long,ToursBasicVO>> memberDetaislMap = new HashMap<Long, Map<Long,ToursBasicVO>>();
		Map<Long,String> designationMap = new HashMap<Long, String>();
		Map<Long,String> districtMap = new HashMap<Long, String>();
		Long locationAccessLevelId =0l;
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		Date fromDate=null;
	    Date toDate=null;
		try{
			if(fromDateStr != null && fromDateStr.trim().length()>0 && toDateStr!= null && toDateStr.trim().length()>0){
				 fromDate = sdf.parse(fromDateStr);
				 toDate = sdf.parse(toDateStr);
			 }
			 List<Object[]> rtrnUsrAccssLvlIdAndVlusObjLst=activityMemberAccessLevelDAO.getLocationLevelAndValuesByActivityMembersId(activityMemberId);
			 if(rtrnUsrAccssLvlIdAndVlusObjLst != null && rtrnUsrAccssLvlIdAndVlusObjLst.size() > 0){
				 locationAccessLevelId=(Long) rtrnUsrAccssLvlIdAndVlusObjLst.get(0)[0];
				 for(Object[] param:rtrnUsrAccssLvlIdAndVlusObjLst){
					 locationValues.add(commonMethodsUtilService.getLongValueForObject(param[1]));
				 }
			 }
			 if(userTypeId.longValue()==IConstants.GENERAL_SECRETARY_USER_TYPE_ID || userTypeId.longValue()==IConstants.STATE_TYPE_USER_ID){
				 List<Object[]> rtrnObjLst = selfAppraisalCandidateDAO.getMemberAccessLevelIdsAndValue();
				   if(rtrnObjLst != null && rtrnObjLst.size() > 0){
					   for(Object[] param:rtrnObjLst){
						   Map<Long,Set<Long>> userAccessLevelMap = candiateAccessLevelMap.get(commonMethodsUtilService.getLongValueForObject(param[0]));
						   	if(userAccessLevelMap == null){
						   		userAccessLevelMap = new HashMap<Long, Set<Long>>();
						   		candiateAccessLevelMap.put(commonMethodsUtilService.getLongValueForObject(param[0]), userAccessLevelMap);
						   	}
						   	Set<Long> locationSet = userAccessLevelMap.get(commonMethodsUtilService.getLongValueForObject(param[1]));
						   	if(locationSet == null){
						   		locationSet = new HashSet<Long>();
						   		userAccessLevelMap.put(commonMethodsUtilService.getLongValueForObject(param[1]), locationSet);
						   	}
						   	locationSet.add(commonMethodsUtilService.getLongValueForObject(param[2]));
					   }
				   }
				  if(candiateAccessLevelMap != null && candiateAccessLevelMap.size() > 0){
					  
					  for(Entry<Long,Map<Long,Set<Long>>> entry:candiateAccessLevelMap.entrySet()){
						  
						  if(entry.getKey().longValue()==243l || entry.getKey().longValue()==242l){
							  
							  Map<Long,Set<Long>> accessLevelMap = entry.getValue();
							  
							  for(Entry<Long,Set<Long>> locationEntry:accessLevelMap.entrySet()){
								  
								  List<Object[]> objList = constituencyDAO.getDistrictBasedOnConstituenciesId(locationEntry.getValue());
								  
								  Map<Long,Set<Long>> districtsMap = new HashMap<Long, Set<Long>>();
								  setDistrictsToMap(objList,locationEntry.getKey(),districtsMap);
								  candiateAccessLevelMap.remove(entry.getKey().longValue());
								  candiateAccessLevelMap.put(entry.getKey().longValue(), districtsMap);
							  }
						  }
					  }
				  }
				   List<Object[]> rtrnObjList = districtDAO.getDistrictListBystateId(stateId);
				   if(rtrnObjLst != null && rtrnObjList.size() > 0){
					   for(Object[] param:rtrnObjLst){
						   districtMap.put(commonMethodsUtilService.getLongValueForObject(param[0]), commonMethodsUtilService.getStringValueForObject(param[1])); 
					   }
				   }
			 }
			 List<Object[]> rtrnObjList = selfAppraisalCandidateDetailsDAO.getToursVisitedDetailsDistrictWiseBasedOnUserAccessLevel(locationAccessLevelId, locationValues, stateId, fromDate, toDate,null);
			 setMemberDetails(rtrnObjList,memberDetaislMap,designationMap);
			 if(locationAccessLevelId == 2l){//state access
				 List<Object[]> rtrnMpObjList = selfAppraisalCandidateDetailsDAO.getToursVisitedDetailsDistrictWiseBasedOnUserAccessLevel(locationAccessLevelId, locationValues, stateId, fromDate, toDate,"MP");
				 setMemberDetails(rtrnMpObjList,memberDetaislMap,designationMap); 
			 }
			 if(userTypeId.longValue()==IConstants.GENERAL_SECRETARY_USER_TYPE_ID || userTypeId.longValue()==IConstants.STATE_TYPE_USER_ID){
				 
			 Map<Long,ToursBasicVO> gsMap = memberDetaislMap.get(3l);
			 
			  if(gsMap != null && gsMap.size() > 0){
				
				  for(Entry<Long,ToursBasicVO> entry:gsMap.entrySet()){
					
					  ToursBasicVO VO = entry.getValue();
					  
					  if(VO.getId().equals(243) || VO.getId().equals(242)){
						
						  Map<Long,Set<Long>> locationMap = candiateAccessLevelMap.get(VO.getId());
						  
						  for(Entry<Long,Set<Long>> locationEntry:locationMap.entrySet()){
							  
							  for(Long id:locationEntry.getValue()){
								  
								  if(!VO.getLocationSet().contains(id)){
									  
									  VO.getLocationSet().add(id);
									   VO.setName(VO.getName()+","+districtMap.get(id));
								   }
							   }
							   
						   }
					  }
				  }
			  }
			 }
			  if(memberDetaislMap != null && memberDetaislMap.size() > 0){
				  for(Entry<Long,Map<Long,ToursBasicVO>> entry:memberDetaislMap.entrySet()){
					  ToursBasicVO VO = new ToursBasicVO();
					   VO.setId(entry.getKey());
					   VO.setDesignation(designationMap.get(entry.getKey()));
					   VO.setSubList(new ArrayList<ToursBasicVO>(entry.getValue().values()));
					   resultList.add(VO);
				  }
			  }
		}catch(Exception e){
			LOG.error("Error occured at getDistrictWiseToursLeaderDetails() in CoreDashboardToursService ",e);		
		}
		return resultList;
	}
	public void setDistrictsToMap(List<Object[]> objList,Long accessLevelId, Map<Long,Set<Long>> districtsMap){
		try{
			if(objList != null && objList.size() > 0){
				for(Object[] param:objList){
					Set<Long> districtSet = districtsMap.get(accessLevelId);
					if(districtSet == null ){
						districtSet = new HashSet<Long>();
						districtsMap.put(accessLevelId, districtSet);
					}
					districtSet.add(commonMethodsUtilService.getLongValueForObject(param[0]));
				}
			}
		}catch(Exception e){
			LOG.error("Error occured at setDistrictsToMap() in CoreDashboardToursService ",e);
		}
	}
	public void setMemberDetails(List<Object[]> objList,Map<Long,Map<Long,ToursBasicVO>> memberDetaislMap,Map<Long,String> designationMap){
		try{
			if(objList != null && objList.size() > 0){
				for(Object[] param:objList){
					Map<Long,ToursBasicVO> memberMap = memberDetaislMap.get(commonMethodsUtilService.getLongValueForObject(param[0]));
					 if(memberMap == null ){
						 memberMap = new HashMap<Long, ToursBasicVO>(); 
						 designationMap.put(commonMethodsUtilService.getLongValueForObject(param[0]),commonMethodsUtilService.getStringValueForObject(param[1]));
						 memberDetaislMap.put(commonMethodsUtilService.getLongValueForObject(param[0]), memberMap);
					 }
					 ToursBasicVO candidateVO = memberMap.get(commonMethodsUtilService.getLongValueForObject(param[3]));
					 if(candidateVO == null){
						 candidateVO = new ToursBasicVO();
						 candidateVO.getLocationSet().add(commonMethodsUtilService.getLongValueForObject(param[3]));
						 candidateVO.setLocationScopeId(commonMethodsUtilService.getLongValueForObject(param[2]));
						 candidateVO.setName(commonMethodsUtilService.getStringValueForObject(param[4]));
						 candidateVO.setId(commonMethodsUtilService.getLongValueForObject(param[5]));
						 candidateVO.setSubmitedLeaderCnt(commonMethodsUtilService.getLongValueForObject(param[6]));
						 candidateVO.setOwnToursCnt(commonMethodsUtilService.getLongValueForObject(param[7]));
						 candidateVO.setInchargerToursCnt(commonMethodsUtilService.getLongValueForObject(param[8]));
						 candidateVO.setTotalSubmittedToursCnt(candidateVO.getOwnToursCnt()+candidateVO.getInchargerToursCnt());
						 Double averageTours = candidateVO.getTotalSubmittedToursCnt().doubleValue()/candidateVO.getSubmitedLeaderCnt().doubleValue();
						 candidateVO.setAverageTours(averageTours);
						 memberMap.put(commonMethodsUtilService.getLongValueForObject(param[3]), candidateVO);
					 }
				}
			}
		}catch(Exception e){
			LOG.error("Error occured at setMemberDetails() in CoreDashboardToursService ",e);	
		}
		
	}
}
