package com.itgrids.partyanalyst.service.impl;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

import org.apache.log4j.Logger;

import com.itgrids.partyanalyst.dao.IActivityMemberAccessLevelDAO;
import com.itgrids.partyanalyst.dao.IConstituencyDAO;
import com.itgrids.partyanalyst.dao.IDistrictDAO;
import com.itgrids.partyanalyst.dao.ISelfAppraisalCandidateDAO;
import com.itgrids.partyanalyst.dao.ISelfAppraisalCandidateDetailsDAO;
import com.itgrids.partyanalyst.dao.ISelfAppraisalCandidateLocationDAO;
import com.itgrids.partyanalyst.dao.ISelfAppraisalCandidateTourLocationDAO;
import com.itgrids.partyanalyst.dao.ISelfAppraisalDesignationDAO;
import com.itgrids.partyanalyst.dto.ToursBasicVO;
import com.itgrids.partyanalyst.service.ICoreDashboardToursService;
import com.itgrids.partyanalyst.utils.CommonMethodsUtilService;
import com.itgrids.partyanalyst.utils.IConstants;

public class CoreDashboardToursService implements ICoreDashboardToursService {

	private final static Logger LOG = Logger.getLogger(CoreDashboardToursService.class);
	
	private ISelfAppraisalCandidateDetailsDAO selfAppraisalCandidateDetailsDAO;
	private ISelfAppraisalCandidateLocationDAO selfAppraisalCandidateLocationDAO;
	private IActivityMemberAccessLevelDAO activityMemberAccessLevelDAO;
	private ISelfAppraisalCandidateDAO selfAppraisalCandidateDAO;
	private IConstituencyDAO constituencyDAO;
	private IDistrictDAO districtDAO;
	private ISelfAppraisalCandidateTourLocationDAO selfAppraisalCandidateTourLocationDAO;
	private ISelfAppraisalDesignationDAO selfAppraisalDesignationDAO;
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
	public void setSelfAppraisalCandidateTourLocationDAO(ISelfAppraisalCandidateTourLocationDAO selfAppraisalCandidateTourLocationDAO) {
		this.selfAppraisalCandidateTourLocationDAO = selfAppraisalCandidateTourLocationDAO;
	}
	public void setSelfAppraisalDesignationDAO(
			ISelfAppraisalDesignationDAO selfAppraisalDesignationDAO) {
		this.selfAppraisalDesignationDAO = selfAppraisalDesignationDAO;
	}
	public ToursBasicVO getToursBasicOverviewCountDetails(Long stateId,String fromDateStr,String toDateStr,Long activityMemberId){
		
		ToursBasicVO resultVO = new ToursBasicVO();
		Map<Long,ToursBasicVO> LeaderMemebersMap = new HashMap<Long, ToursBasicVO>(0);
		Long locationAccessLevelId=0l;
		Set<Long> locationValues = new HashSet<Long>(0);
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
		 		   List<Object[]> rtrnLeaderCntObjLst = selfAppraisalCandidateLocationDAO.getNoOfLeadersCntDesignationByBasedOnUserAccessLevel(locationAccessLevelId,locationValues,stateId);
				   	
				   if(rtrnLeaderCntObjLst != null &&  rtrnLeaderCntObjLst.size() > 0){
					   
				   		for(Object[] param:rtrnLeaderCntObjLst){
				   			
				   			
				   			ToursBasicVO leaderVO = new ToursBasicVO();
				   			leaderVO.setId(commonMethodsUtilService.getLongValueForObject(param[0]));
				   			leaderVO.setDesignation(commonMethodsUtilService.getStringValueForObject(param[1]));
				   			leaderVO.setNoOfLeaderCnt(commonMethodsUtilService.getLongValueForObject(param[2]));
				   			leaderVO.setNotSubmitedLeaserCnt(leaderVO.getNoOfLeaderCnt());//default all leader has not submitted tours.if it is submitted then this value will be replace;
				   		    leaderVO.setNotsubmitedCandidateTourPer(calculatePercantage(leaderVO.getNotSubmitedLeaserCnt(), leaderVO.getNoOfLeaderCnt()));
				   			LeaderMemebersMap.put(leaderVO.getId(), leaderVO);
				   		}
				   	}
					 
				   List<Object[]> rtrnLeaderToursDtlsObjLst = selfAppraisalCandidateDetailsDAO.getToursDetailstDesignationByBasedOnUserAccessLevel(locationAccessLevelId,locationValues, stateId, fromDate, toDate);
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
				    					   leaderVO.setNoOfDistinctTours(commonMethodsUtilService.getLongValueForObject(param[4]));
				    					   Double averageTours = leaderVO.getTotalSubmittedToursCnt().doubleValue()/leaderVO.getNoOfDistinctTours().doubleValue();
				    					   if(!(averageTours.isNaN())){
				    						   leaderVO.setAverageTours(averageTours);   
				    					   }
				    					}
					   			}
					   		}
			
			    //calculation overAll percentage
					if(LeaderMemebersMap != null && LeaderMemebersMap.size() > 0){
					     ToursBasicVO overAllDtlsVO = new ToursBasicVO();
						for(Entry<Long,ToursBasicVO> entry:LeaderMemebersMap.entrySet()){
							    if(entry.getValue() != null){
							    	   overAllDtlsVO.setNoOfLeaderCnt(overAllDtlsVO.getNoOfLeaderCnt()+entry.getValue().getNoOfLeaderCnt());
							           overAllDtlsVO.setSubmitedLeaderCnt(overAllDtlsVO.getSubmitedLeaderCnt()+entry.getValue().getSubmitedLeaderCnt());
			    					   overAllDtlsVO.setNotSubmitedLeaserCnt(overAllDtlsVO.getNotSubmitedLeaserCnt()+entry.getValue().getNotSubmitedLeaserCnt());
			    					   overAllDtlsVO.setOwnToursCnt(overAllDtlsVO.getOwnToursCnt()+entry.getValue().getOwnToursCnt());
			    					   overAllDtlsVO.setInchargerToursCnt(overAllDtlsVO.getInchargerToursCnt()+entry.getValue().getInchargerToursCnt());
			    					   overAllDtlsVO.setTotalSubmittedToursCnt(overAllDtlsVO.getTotalSubmittedToursCnt()+entry.getValue().getTotalSubmittedToursCnt());
			    					   overAllDtlsVO.setNoOfDistinctTours(overAllDtlsVO.getNoOfDistinctTours()+entry.getValue().getNoOfDistinctTours()); 	
							    }
						}
						   overAllDtlsVO.setSubmitedCandidateTourPer(calculatePercantage(overAllDtlsVO.getSubmitedLeaderCnt(), overAllDtlsVO.getNoOfLeaderCnt()));
						   overAllDtlsVO.setNotsubmitedCandidateTourPer(calculatePercantage(overAllDtlsVO.getNotSubmitedLeaserCnt(), overAllDtlsVO.getNoOfLeaderCnt())); 
						   Double averageTours = overAllDtlsVO.getTotalSubmittedToursCnt().doubleValue()/overAllDtlsVO.getNoOfDistinctTours().doubleValue();
						   if(!(averageTours.isNaN())){
							   overAllDtlsVO.setAverageTours(averageTours);
						   }
						   //setting result to final result VO
						   resultVO.setOverAllDetailsVO(overAllDtlsVO);
					}
			   if(LeaderMemebersMap != null && LeaderMemebersMap.size() > 0){
				   resultVO.getSubList().addAll(new ArrayList<ToursBasicVO>(LeaderMemebersMap.values()));   
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
	public List<List<ToursBasicVO>> getDesigWiseMemberDtls(Long stateId,String fromDateStr,String toDateStr,Long activityMemberId){
		try{
			Map<Long,Map<Long,ToursBasicVO>> desigIdAndMapOfCandIdAndCandDtlsMap = new HashMap<Long,Map<Long,ToursBasicVO>>();
			Long locationScopeId = 0l;
			Set<Long> locationValueSet = new HashSet<Long>(0);
			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
			Date fromDate=null;
		    Date toDate=null;
		    if(fromDateStr != null && fromDateStr.trim().length()>0 && toDateStr!= null && toDateStr.trim().length()>0){
				 fromDate = sdf.parse(fromDateStr);
				 toDate = sdf.parse(toDateStr);  
		    }
		    List<Object[]> rtrnUsrAccssLvlIdAndVlusObjLst=activityMemberAccessLevelDAO.getLocationLevelAndValuesByActivityMembersId(activityMemberId);
		    if(rtrnUsrAccssLvlIdAndVlusObjLst != null && !rtrnUsrAccssLvlIdAndVlusObjLst.isEmpty()){
		    	locationScopeId = (Long)rtrnUsrAccssLvlIdAndVlusObjLst.get(0)[0];
		    	for (Object[] param : rtrnUsrAccssLvlIdAndVlusObjLst) {
		    		locationValueSet.add(param[1] != null ? (Long)param[1] : 0l); 
		    	}
		    }
		    //take all the tour counts first..
		    /*
 			S_A_C_I		L_V		own_tour_count   
			1			504		30				
			1			505		20				
			1			506		40				
			3			464		10				
			16			14		2				
			17			14		3				
			18			16		1				
			19			16		11				
			
			//second query
			S_A_C_I		L_V		incharge_tour_count
			1			504		30
			1			505		40
			1			506		40				
			3			464		10				
			16			14		2				
			17			14		3				
			18			16		1				
			19			16		11				
			
			
			*/
		    List<Object[]> cndWiseAndLocValWiseCountListForOwn = selfAppraisalCandidateDetailsDAO.getCndWiseAndLocValWiseCountListForOwn(fromDate,toDate);
		    Map<Long,Map<Long,Long>> candIdAndLocValAndCountMap = new HashMap<Long,Map<Long,Long>>();
		    Map<Long,Long> locValAndTourCountMap = new HashMap<Long,Long>();
			if(cndWiseAndLocValWiseCountListForOwn != null && cndWiseAndLocValWiseCountListForOwn.size() > 0){
				for(Object[] param : cndWiseAndLocValWiseCountListForOwn){  
					locValAndTourCountMap = candIdAndLocValAndCountMap.get(commonMethodsUtilService.getLongValueForObject(param[0]));
					if(locValAndTourCountMap != null){
						locValAndTourCountMap.put(commonMethodsUtilService.getLongValueForObject(param[1]), commonMethodsUtilService.getLongValueForObject(param[2]));
					}else{
						locValAndTourCountMap = new HashMap<Long,Long>();
						locValAndTourCountMap.put(commonMethodsUtilService.getLongValueForObject(param[1]), commonMethodsUtilService.getLongValueForObject(param[2]));
						candIdAndLocValAndCountMap.put(commonMethodsUtilService.getLongValueForObject(param[0]), locValAndTourCountMap);
					}  
				}    
			}
		    List<Object[]> cndWiseAndLocValWiseCountListForIncharge = selfAppraisalCandidateDetailsDAO.getCndWiseAndLocValWiseCountListForIncharge(fromDate,toDate);

			if(cndWiseAndLocValWiseCountListForIncharge != null && cndWiseAndLocValWiseCountListForIncharge.size() > 0){
				for(Object[] param : cndWiseAndLocValWiseCountListForIncharge){
					locValAndTourCountMap = candIdAndLocValAndCountMap.get(commonMethodsUtilService.getLongValueForObject(param[0]));
					if(locValAndTourCountMap != null){
						locValAndTourCountMap.put(commonMethodsUtilService.getLongValueForObject(param[1]), commonMethodsUtilService.getLongValueForObject(param[2]));
					}else{
						locValAndTourCountMap = new HashMap<Long,Long>();
						locValAndTourCountMap.put(commonMethodsUtilService.getLongValueForObject(param[1]), commonMethodsUtilService.getLongValueForObject(param[2]));
						candIdAndLocValAndCountMap.put(commonMethodsUtilService.getLongValueForObject(param[0]), locValAndTourCountMap);
					}  
				}    
			}
    	/*  
    	 * S_A_D_I[0]		designation[1]				S_A_C_I[2]		S_A_L_S_I[3]	name[4]							A_M_I[5]
    	    ========================================================================================================================
    	    3			GENERAL SECRETARIES				239					1			RAMMANAIDU NIMMALA				60
			3			GENERAL SECRETARIES				240					1			BUCHAYYA CHOWDARI GORANTLA		61
			3			GENERAL SECRETARIES				241					1			SUBRAHMANYAM REDDY				62
			3			GENERAL SECRETARIES				242					1			B JAYANAGESWARA REDDY			63
			3			GENERAL SECRETARIES				243					1			RAMAIAH VARLA					64
			4			ORGANIZING SECRETARIES			32					1			Krishan Ganni					65
			4			ORGANIZING SECRETARIES			33					1			VENKATESWARA RAO VANAMADI		66
		*/
		    List<Object[]> disignationIdList = selfAppraisalDesignationDAO.getDesiganationList();
		    List<Long> desigList = new ArrayList<Long>();
		    if(disignationIdList != null && disignationIdList.size() > 0){
		    	for(Object[] param : disignationIdList){
		    		desigList.add(commonMethodsUtilService.getLongValueForObject(param[0])); 
		    	}
		    }
		    List<Object[]> desigWiseAllCandidate = selfAppraisalCandidateLocationDAO.getDesigWiseAllCandidate(stateId,locationScopeId,locationValueSet,desigList);
		    
		    prepairDesignationWiseCandidateDtls(desigWiseAllCandidate, desigIdAndMapOfCandIdAndCandDtlsMap);
		
		    Map<Long,ToursBasicVO> candIdAndCandDtlsMap = null;
		    List<Long> locValLstOfCandidate = null;
		    ToursBasicVO resultVO = null;
		    Long candidateId = null;
		    Long tourCount = 0l;
		    if(desigIdAndMapOfCandIdAndCandDtlsMap != null && desigIdAndMapOfCandIdAndCandDtlsMap.size() > 0){
		    	for(Entry<Long,Map<Long,ToursBasicVO>> enter : desigIdAndMapOfCandIdAndCandDtlsMap.entrySet()){
		    		candIdAndCandDtlsMap = enter.getValue();
		    		if(candIdAndCandDtlsMap != null && candIdAndCandDtlsMap.size() > 0){
		    			for(Entry<Long,ToursBasicVO> entry1 : candIdAndCandDtlsMap.entrySet()){
		    				tourCount = 0l;
		    				resultVO = entry1.getValue();
		    				candidateId = resultVO.getId();  
		    				locValLstOfCandidate = resultVO.getLocationValueList();  
		    				if(candidateId != null && locValLstOfCandidate != null && locValLstOfCandidate.size() > 0){
		    					locValAndTourCountMap = candIdAndLocValAndCountMap.get(candidateId);
		    					if(locValAndTourCountMap != null){
		    						for(Long lcVal : locValLstOfCandidate){
		    							if(locValAndTourCountMap.get(lcVal) != null){
		    								tourCount = tourCount + locValAndTourCountMap.get(lcVal);
		    							}
		    						}
		    					}
		    					resultVO.setTotalTour(tourCount);
		    				}        
		    			}
		    		}
		    	}
		    }
		    List<List<ToursBasicVO>> finalList = new ArrayList<List<ToursBasicVO>>();
		    List<ToursBasicVO> subList = null;
		    if(desigIdAndMapOfCandIdAndCandDtlsMap != null && desigIdAndMapOfCandIdAndCandDtlsMap.size() > 0){
		    	for(Entry<Long,Map<Long,ToursBasicVO>> entry : desigIdAndMapOfCandIdAndCandDtlsMap.entrySet()){
		    		candIdAndCandDtlsMap = desigIdAndMapOfCandIdAndCandDtlsMap.get(entry.getKey());
		    		subList = new ArrayList<ToursBasicVO>();
		    		if(candIdAndCandDtlsMap != null){
		    			subList.addAll(candIdAndCandDtlsMap.values());
		    			Collections.sort(subList, sortCandidateDesc);
		    			finalList.add(subList);
		    		} 
		    	}
		    }
			return finalList;
		}catch(Exception e){ 
			e.printStackTrace();
			LOG.error("Error occured at getDesigWiseMemberDtls() in CoreDashboardToursService ",e);
		}
		return null;  
	}
	public List<ToursBasicVO> getMemberDtlsForADesignation(List<Long> disigList,Long stateId,String fromDateStr,String toDateStr,Long activityMemberId){
		try{
			Map<Long,Map<Long,ToursBasicVO>> desigIdAndMapOfCandIdAndCandDtlsMap = new HashMap<Long,Map<Long,ToursBasicVO>>();
			Long locationScopeId = 0l;
			Set<Long> locationValueSet = new HashSet<Long>(0);
			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
			Date fromDate=null;
		    Date toDate=null;
		    if(fromDateStr != null && fromDateStr.trim().length()>0 && toDateStr!= null && toDateStr.trim().length()>0){
				 fromDate = sdf.parse(fromDateStr);
				 toDate = sdf.parse(toDateStr);  
		    }
		    List<Object[]> rtrnUsrAccssLvlIdAndVlusObjLst=activityMemberAccessLevelDAO.getLocationLevelAndValuesByActivityMembersId(activityMemberId);
		    if(rtrnUsrAccssLvlIdAndVlusObjLst != null && !rtrnUsrAccssLvlIdAndVlusObjLst.isEmpty()){
		    	locationScopeId = (Long)rtrnUsrAccssLvlIdAndVlusObjLst.get(0)[0];
		    	for (Object[] param : rtrnUsrAccssLvlIdAndVlusObjLst) {
		    		locationValueSet.add(param[1] != null ? (Long)param[1] : 0l); 
		    	}
		    }
		    //take all the tour counts first..  
		    /*
 			S_A_C_I		L_V		own_tour_count  own_tour_count 
			1			504		30				10
			1			505		20				20			
			1			506		40				30
			3			464		10				40
			16			14		2				20
			17			14		3				20
			18			16		1				10
			19			16		11				1
			*/
		    //List<Object[]> cndWiseAndLocValWiseCountList = selfAppraisalCandidateDetailsDAO.getCndWiseAndLocValWiseCountList(fromDate,toDate);
		    List<Object[]> cndWiseAndLocValWiseCountListForOwn = selfAppraisalCandidateDetailsDAO.getCndWiseAndLocValWiseCountListForOwn(fromDate,toDate);
		    Map<Long,Map<Long,Long>> candIdAndLocValAndCountMap = new HashMap<Long,Map<Long,Long>>();
		    Map<Long,Long> locValAndTourCountMap = new HashMap<Long,Long>();
			if(cndWiseAndLocValWiseCountListForOwn != null && cndWiseAndLocValWiseCountListForOwn.size() > 0){
				for(Object[] param : cndWiseAndLocValWiseCountListForOwn){
					locValAndTourCountMap = candIdAndLocValAndCountMap.get(commonMethodsUtilService.getLongValueForObject(param[0]));
					if(locValAndTourCountMap != null){
						locValAndTourCountMap.put(commonMethodsUtilService.getLongValueForObject(param[1]), commonMethodsUtilService.getLongValueForObject(param[2]));
					}else{
						locValAndTourCountMap = new HashMap<Long,Long>();
						locValAndTourCountMap.put(commonMethodsUtilService.getLongValueForObject(param[1]), commonMethodsUtilService.getLongValueForObject(param[2]));
						candIdAndLocValAndCountMap.put(commonMethodsUtilService.getLongValueForObject(param[0]), locValAndTourCountMap);
					}
				}
			}
			
			List<Object[]> cndWiseAndLocValWiseCountListForIncharge = selfAppraisalCandidateDetailsDAO.getCndWiseAndLocValWiseCountListForIncharge(fromDate,toDate);

			if(cndWiseAndLocValWiseCountListForIncharge != null && cndWiseAndLocValWiseCountListForIncharge.size() > 0){
				for(Object[] param : cndWiseAndLocValWiseCountListForIncharge){    
					locValAndTourCountMap = candIdAndLocValAndCountMap.get(commonMethodsUtilService.getLongValueForObject(param[0]));
					if(locValAndTourCountMap != null){
						locValAndTourCountMap.put(commonMethodsUtilService.getLongValueForObject(param[1]), commonMethodsUtilService.getLongValueForObject(param[2]));
					}else{
						locValAndTourCountMap = new HashMap<Long,Long>();
						locValAndTourCountMap.put(commonMethodsUtilService.getLongValueForObject(param[1]), commonMethodsUtilService.getLongValueForObject(param[2]));
						candIdAndLocValAndCountMap.put(commonMethodsUtilService.getLongValueForObject(param[0]), locValAndTourCountMap);
					}  
				}    
			}  
    	/*  
    	 * S_A_D_I[0]		designation[1]				S_A_C_I[2]		S_A_L_S_I[3]	name[4]							A_M_I[5]
    	    ========================================================================================================================
    	    3			GENERAL SECRETARIES				239					1			RAMMANAIDU NIMMALA				60
    	    3			GENERAL SECRETARIES				239					3			RAMMANAIDU NIMMALA				60
			3			GENERAL SECRETARIES				240					1			BUCHAYYA CHOWDARI GORANTLA		61
			3			GENERAL SECRETARIES				241					1			SUBRAHMANYAM REDDY				62
			3			GENERAL SECRETARIES				242					1			B JAYANAGESWARA REDDY			63
			3			GENERAL SECRETARIES				243					1			RAMAIAH VARLA					64
			4			ORGANIZING SECRETARIES			32					1			Krishan Ganni					65
			4			ORGANIZING SECRETARIES			33					1			VENKATESWARA RAO VANAMADI		66
		*/
		    
		    List<Object[]> desigWiseAllCandidate = selfAppraisalCandidateLocationDAO.getDesigWiseAllCandidate(stateId,locationScopeId,locationValueSet,disigList);
		    
		    prepairDesignationWiseCandidateDtls(desigWiseAllCandidate, desigIdAndMapOfCandIdAndCandDtlsMap);
		    Map<Long,ToursBasicVO> candIdAndCandDtlsMap = null;
		    
		    //get all the comment and file.
		    List<Long> cndIdListForCmtAndFile = new ArrayList<Long>();
		    if(disigList != null && disigList.size() > 0){
		    	for(Long id  : disigList){
		    		candIdAndCandDtlsMap = desigIdAndMapOfCandIdAndCandDtlsMap.get(id);
		    		if(candIdAndCandDtlsMap != null){
		    			cndIdListForCmtAndFile.addAll(candIdAndCandDtlsMap.keySet());
		    		}
		    	}
		    }
		    
		    Map<Long,List<String>> candIdAndRemarksListMap = new HashMap<Long,List<String>>();
		    List<String> remarksList = null;
		    Map<Long,List<String>> candIdAndFilePathListMap = new HashMap<Long,List<String>>();
		    List<String> filePathList = null;  
		    if(cndIdListForCmtAndFile != null && cndIdListForCmtAndFile.size() > 0){
		    	List<Object[]> memberDtls = selfAppraisalCandidateDetailsDAO.getCommendAndFilePathDtls(cndIdListForCmtAndFile,fromDate,toDate);
		    	if(memberDtls != null && memberDtls.size() > 0){  
		    		for(Object[] param : memberDtls){
		    			//add remarks to map
		    			remarksList = candIdAndRemarksListMap.get(commonMethodsUtilService.getLongValueForObject(param[0]));
		    			if(remarksList != null){
		    				remarksList.add(commonMethodsUtilService.getStringValueForObject(param[3]));
		    			}else{
		    				remarksList = new ArrayList<String>();
		    				remarksList.add(commonMethodsUtilService.getStringValueForObject(param[3]));
		    				candIdAndRemarksListMap.put(commonMethodsUtilService.getLongValueForObject(param[0]),remarksList);
		    			}
		    			//add file path to map
		    			filePathList = candIdAndFilePathListMap.get(commonMethodsUtilService.getLongValueForObject(param[0]));
		    			if(filePathList != null){
		    				filePathList.add(commonMethodsUtilService.getStringValueForObject(param[4]));
		    			}else{
		    				filePathList = new ArrayList<String>();
		    				filePathList.add(commonMethodsUtilService.getStringValueForObject(param[4]));
		    				candIdAndFilePathListMap.put(commonMethodsUtilService.getLongValueForObject(param[0]),filePathList);
		    			}
		    		}
		    	}
		    }
		    
		    List<Long> locValLstOfCandidate = null;
		    ToursBasicVO resultVO = null;
		    Long candidateId = null;
		    Long tourCount = 0l;
		    if(desigIdAndMapOfCandIdAndCandDtlsMap != null && desigIdAndMapOfCandIdAndCandDtlsMap.size() > 0){
		    	for(Entry<Long,Map<Long,ToursBasicVO>> enter : desigIdAndMapOfCandIdAndCandDtlsMap.entrySet()){
		    		candIdAndCandDtlsMap = enter.getValue();
		    		if(candIdAndCandDtlsMap != null && candIdAndCandDtlsMap.size() > 0){
		    			for(Entry<Long,ToursBasicVO> entry1 : candIdAndCandDtlsMap.entrySet()){
		    				tourCount = 0l;
		    				resultVO = entry1.getValue();
		    				candidateId = resultVO.getId();
		    				//push remark list to Vo
		    				if(candIdAndRemarksListMap.get(candidateId) != null){
		    					resultVO.getRemarkList().addAll(candIdAndRemarksListMap.get(candidateId));
		    				}
		    				//push file path to vo
		    				if(candIdAndFilePathListMap.get(candidateId) != null){
		    					resultVO.getFilePathList().addAll(candIdAndFilePathListMap.get(candidateId));
		    				}
		    				locValLstOfCandidate = resultVO.getLocationValueList();
		    				if(candidateId != null && locValLstOfCandidate != null && locValLstOfCandidate.size() > 0){
		    					locValAndTourCountMap = candIdAndLocValAndCountMap.get(candidateId);
		    					if(locValAndTourCountMap != null){
		    						for(Long lcVal : locValLstOfCandidate){
		    							if(locValAndTourCountMap.get(lcVal) != null){
		    								tourCount = tourCount + locValAndTourCountMap.get(lcVal);
		    							}
		    						}
		    					}
		    					resultVO.setTotalTour(tourCount);
		    				}        
		    			}  
		    		}
		    	}
		    }
		   
		    //List<ToursBasicVO> finalList = new ArrayList<ToursBasicVO>();  
		    List<ToursBasicVO> subList = new ArrayList<ToursBasicVO>();
		    if(desigIdAndMapOfCandIdAndCandDtlsMap != null && desigIdAndMapOfCandIdAndCandDtlsMap.size() > 0){
		    	for(Entry<Long,Map<Long,ToursBasicVO>> entry : desigIdAndMapOfCandIdAndCandDtlsMap.entrySet()){
		    		candIdAndCandDtlsMap = desigIdAndMapOfCandIdAndCandDtlsMap.get(entry.getKey());
		    		if(candIdAndCandDtlsMap != null){
		    			subList.addAll(candIdAndCandDtlsMap.values());
		    		}     
		    	}
		    }
		    if(subList != null){  
		    	Collections.sort(subList, sortCandidateDesc);
		    }
			return subList;    
		}catch(Exception e){
			e.printStackTrace();
			LOG.error("Error occured at getMemberDtlsForADesignation() in CoreDashboardToursService ",e);
		}
		return null;
	}
	public void prepairDesignationWiseCandidateDtls(List<Object[]> desigWiseAllCandidate,
													Map<Long,Map<Long,ToursBasicVO>> desigIdAndMapOfCandIdAndCandDtlsMap){
		ToursBasicVO resultVO = null;
		Long candidateId = null;
	    Long designationId = null;
	    Set<Long> candidateIdSet = null;
	    Set<Long> candidateIdList = new HashSet<Long>();
	    Set<Long> locationScopeIdList = new HashSet<Long>();
	    Map<Long,String> desigIdAndDesigMap = new HashMap<Long,String>();
	    Map<Long,Long> candIdAndActivityMemIdMap = new HashMap<Long,Long>();
	    Map<Long, Long> candidateIdAndLocationScopeIdMap = new HashMap<Long,Long>();
	    Map<Long, String> candidateIdAndNameMap = new HashMap<Long,String>();
		Map<Long,Set<Long>> desigIdAndCandidateIdSetMap = new HashMap<Long,Set<Long>>();
		if(desigWiseAllCandidate != null && desigWiseAllCandidate.size() > 0){
	    	for(Object[] param : desigWiseAllCandidate){
	    		designationId = commonMethodsUtilService.getLongValueForObject(param[0]);
	    		candidateId = commonMethodsUtilService.getLongValueForObject(param[2]);
	    		candidateIdSet = desigIdAndCandidateIdSetMap.get(commonMethodsUtilService.getLongValueForObject(param[0]));
	    		if(candidateIdSet != null){
	    			candidateIdSet.add(candidateId);
	    			desigIdAndCandidateIdSetMap.put(designationId, candidateIdSet);
	    		}else{
	    			candidateIdSet = new HashSet<Long>();  
	    			candidateIdSet.add(candidateId);
	    			desigIdAndCandidateIdSetMap.put(designationId, candidateIdSet);
	    		}
	    		candidateIdAndNameMap.put(commonMethodsUtilService.getLongValueForObject(param[2]), commonMethodsUtilService.getStringValueForObject(param[4]));
	    		candidateIdAndLocationScopeIdMap.put(commonMethodsUtilService.getLongValueForObject(param[2]), commonMethodsUtilService.getLongValueForObject(param[3]));
	    		desigIdAndDesigMap.put(commonMethodsUtilService.getLongValueForObject(param[0]), commonMethodsUtilService.getStringValueForObject(param[1]));
	    		candidateIdList.add(commonMethodsUtilService.getLongValueForObject(param[2]));
	    		candIdAndActivityMemIdMap.put(candidateId, commonMethodsUtilService.getLongValueForObject(param[5]));
	    		locationScopeIdList.add(commonMethodsUtilService.getLongValueForObject(param[3]));  
	    	}
	    }
	    /*  
        S_A_C_I			S_A_L_S_I		L_V	
		1, 				2, 				504
		1, 				2, 				509
		3, 				2, 				464
		4, 				2, 				479
		5, 				2, 				463
		6, 				2, 				500
		7, 				2,				473
	    */
	    Map<Long,Set<Long>> candIdAndSetOfLocValMap = new HashMap<Long,Set<Long>>();
	    Set<Long> locationValSet = null;
	    Long locationValue = null;
	    List<Object[]> locationValueListForAllCnd = selfAppraisalCandidateLocationDAO.getLocationListByCndIdAndScopeId(candidateIdList,locationScopeIdList);
	    if(locationValueListForAllCnd != null && locationValueListForAllCnd.size() > 0){
	    	for(Object[] param : locationValueListForAllCnd){
	    		candidateId = commonMethodsUtilService.getLongValueForObject(param[0]);
	    		locationValue = commonMethodsUtilService.getLongValueForObject(param[2]);
	    		locationValSet = candIdAndSetOfLocValMap.get(candidateId);
	    		if(locationValSet != null){
	    			locationValSet.add(locationValue);
	    			candIdAndSetOfLocValMap.put(candidateId, locationValSet);
	    		}else{
	    			locationValSet = new HashSet<Long>();
	    			locationValSet.add(locationValue);
	    			candIdAndSetOfLocValMap.put(candidateId, locationValSet);
	    		}
	    	}  
	    }
	    Long selfAppLocationScpId = null;
	    
	    Map<Long,ToursBasicVO> candIdAndCandDtlsMap = null;
	    if(desigIdAndCandidateIdSetMap != null & desigIdAndCandidateIdSetMap.size() > 0){
	    	for(Entry<Long,Set<Long>> entry : desigIdAndCandidateIdSetMap.entrySet()){
	    		candidateIdSet = entry.getValue();
	    		if(candidateIdSet != null && candidateIdSet.size() >0){
	    			candIdAndCandDtlsMap = new HashMap<Long,ToursBasicVO>();
	    			for(Long cndId : candidateIdSet){
	    				if(candIdAndSetOfLocValMap.get(cndId) != null){
	    					resultVO = new ToursBasicVO();
		    				selfAppLocationScpId = candidateIdAndLocationScopeIdMap.get(cndId);
		    				resultVO.setId(cndId);
		    				resultVO.setActivityMemberId(candIdAndActivityMemIdMap.get(cndId));
		    				resultVO.setName(candidateIdAndNameMap.get(cndId));
		    				resultVO.setDesignation(desigIdAndDesigMap.get(entry.getKey()));
		    				resultVO.setDesignationId(entry.getKey());
		    				resultVO.setLocationScopeId(selfAppLocationScpId);
		    				//add all the location value based on candidate id.
		    				resultVO.getLocationValueList().addAll(new ArrayList<Long>(candIdAndSetOfLocValMap.get(cndId)));
		    				candIdAndCandDtlsMap.put(cndId, resultVO);
	    				}    
	    			}
	    		}
	    		desigIdAndMapOfCandIdAndCandDtlsMap.put(entry.getKey(), candIdAndCandDtlsMap);
	    	}
	    }
		
	}
	public ToursBasicVO getDesignationDtls(Long activityMemberId, List<Long> desigIdList, String startDateStr, String endDateStr){
	    	LOG.info("Entered into getDesignationDtls() of ToursService{}");
	    	try{  
	    		ToursBasicVO toursBasicVO = new ToursBasicVO();
	    		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
	    		Date startDate = null;
	    		Date endDate = null;
	    		if(startDateStr != null && startDateStr.length() > 0 && endDateStr != null && endDateStr.length() > 0){
	    			startDate = sdf.parse(startDateStr);
	    			endDate = sdf.parse(endDateStr);  
	    		}
	    		Long locationScopeId = 0l;
				Set<Long> locationValueSet = new HashSet<Long>(0);
				
			    List<Object[]> rtrnUsrAccssLvlIdAndVlusObjLst=activityMemberAccessLevelDAO.getLocationLevelAndValuesByActivityMembersId(activityMemberId);
			    if(rtrnUsrAccssLvlIdAndVlusObjLst != null && !rtrnUsrAccssLvlIdAndVlusObjLst.isEmpty()){
			    	locationScopeId = (Long)rtrnUsrAccssLvlIdAndVlusObjLst.get(0)[0];
			    	for (Object[] param : rtrnUsrAccssLvlIdAndVlusObjLst) {
			    		locationValueSet.add(param[1] != null ? (Long)param[1] : 0l); 
			    	}
			    }
	    		
	    		List<Object[]> desigDtls = selfAppraisalCandidateLocationDAO.getTotalLeadersDesignationBy(desigIdList,locationScopeId,locationValueSet,startDate,endDate);
	    		Long cndCount = 0l;    
	    		if(desigDtls != null && desigDtls.size() > 0){ 
	    			for(Object[] param : desigDtls){
	    				cndCount = cndCount + (param[1] != null ? (Long)param[1] : 0l);
	    			}
	    			toursBasicVO.setCandidateCount(cndCount);      
	    		}
	    		//List<Object[]> memDtlsList= selfAppraisalCandidateDetailsDAO.getSubmittedToursLeadersDetails(startDate,endDate,desigIdList);
	    		List<Object[]> memDtlsList= selfAppraisalCandidateDetailsDAO.getSubmittedToursDetails(startDate,endDate,desigIdList,locationScopeId,locationValueSet);
	    		Long selectedCandCount = 0l;    
	    		Long totalTours = 0l;
	    		if(memDtlsList != null && memDtlsList.size() > 0){  
	    			for(Object[] param : memDtlsList){
	    				selectedCandCount = selectedCandCount + (param[1] != null ? (Long)param[1] : 0l);
	    				totalTours = totalTours + ((param[2] != null ? (Long)param[2] : 0l) + (param[3] != null ? (Long)param[3] : 0l));
	    			}      
	    			toursBasicVO.setSelectedCandCount(selectedCandCount);
	    			toursBasicVO.setTotalTour(totalTours);
	    		}    
	    		return toursBasicVO;  
	    		
	    	}catch(Exception e){  
	    		e.printStackTrace();
	    		LOG.error("Error Occured at getDesignationDtls() in ToursService class",e);
	    	}
	    	return null;  
	    }
	public List<ToursBasicVO> getDesignationLabelList(Long activityMemberId){
		try{
			Long locationScopeId = 0l;
			Set<Long> locationValueSet = new HashSet<Long>(0);
			
		    List<Object[]> rtrnUsrAccssLvlIdAndVlusObjLst=activityMemberAccessLevelDAO.getLocationLevelAndValuesByActivityMembersId(activityMemberId);
		    if(rtrnUsrAccssLvlIdAndVlusObjLst != null && !rtrnUsrAccssLvlIdAndVlusObjLst.isEmpty()){
		    	locationScopeId = (Long)rtrnUsrAccssLvlIdAndVlusObjLst.get(0)[0];
		    	for (Object[] param : rtrnUsrAccssLvlIdAndVlusObjLst) {
		    		locationValueSet.add(param[1] != null ? (Long)param[1] : 0l); 
		    	}
		    }
		    List<ToursBasicVO> basicVOs = new ArrayList<ToursBasicVO>();
		    ToursBasicVO toursBasicVO = null; 
		    List<Object[]> designationList = selfAppraisalCandidateLocationDAO.getDesignationListDtls(locationScopeId,locationValueSet);	
		    
		    ToursBasicVO orgAndSecVO = null;
			if(designationList != null && designationList.size() > 0){
				for(Object[] param : designationList){
					toursBasicVO = new ToursBasicVO();
					if(commonMethodsUtilService.getLongValueForObject(param[0])==4l || commonMethodsUtilService.getLongValueForObject(param[0])==5l){
					   if(orgAndSecVO.getComment() == null){
						   orgAndSecVO = new ToursBasicVO();
						   orgAndSecVO.setComment(commonMethodsUtilService.getStringValueForObject(param[0]));
						   orgAndSecVO.setName(commonMethodsUtilService.getStringValueForObject(param[1]));
					   }else{
						   orgAndSecVO.setComment(orgAndSecVO.getComment()+","+commonMethodsUtilService.getLongValueForObject(param[0]));
						   orgAndSecVO.setName(orgAndSecVO.getName()+"/"+commonMethodsUtilService.getStringValueForObject(param[1]));
					   }
					}else{
						toursBasicVO.setComment(commonMethodsUtilService.getLongValueForObject(param[0]).toString());//id
						toursBasicVO.setName(commonMethodsUtilService.getStringValueForObject(param[1]));
						toursBasicVO.setCandidateCount(commonMethodsUtilService.getLongValueForObject(param[2]));//order
						basicVOs.add(toursBasicVO);
					}
				}
			}
			if(basicVOs != null){
				if(basicVOs.get(0).getComment().trim().equalsIgnoreCase("3")){
					if(orgAndSecVO != null){
						basicVOs.add(1, orgAndSecVO);  
					}
				}else{
					if(orgAndSecVO != null){
						basicVOs.add(0, orgAndSecVO);  
					}
				}
			}
			return basicVOs;  
		}catch(Exception e){
			e.printStackTrace();
			LOG.error("Error Occured at getDesignationLabelList() in ToursService class",e);
		}
		return null;
	}
	public static Comparator<ToursBasicVO> sortCandidateDesc = new Comparator<ToursBasicVO>(){
			public int compare(ToursBasicVO vo1, ToursBasicVO vo2){  
				Long count1 = vo1.getTotalTour();
				Long count2 = vo2.getTotalTour();
				return count2.compareTo(count1);
			}
	};
	
	public List<ToursBasicVO> getDistrictWiseToursSubmitedDetails(Long stateId,String fromDateStr,String toDateStr,Long activityMemberId,Long userTypeId){
		List<ToursBasicVO> resultList = new ArrayList<ToursBasicVO>();
		Set<Long> locationValues = new java.util.HashSet<Long>();
		Map<Long,Map<Long,Set<Long>>> candiateAccessLevelMap = new ConcurrentHashMap();
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
						  
						  if(entry.getKey().longValue()==36l || entry.getKey().longValue()==37l){
							  
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
				   List<Object[]> rtrnDistrictList = districtDAO.getDistrictListBystateId(stateId);
				   if(rtrnDistrictList != null && rtrnDistrictList.size() > 0){
					   for(Object[] param:rtrnDistrictList){
						   districtMap.put(commonMethodsUtilService.getLongValueForObject(param[0]), commonMethodsUtilService.getStringValueForObject(param[1])); 
					   }
				   }
				   
			 List<Object[]> rtrnObjList = selfAppraisalCandidateDetailsDAO.getToursVisitedDetailsDistrictWiseBasedOnUserAccessLevel(locationAccessLevelId, locationValues, stateId, fromDate, toDate,null);
			 setMemberDetails(rtrnObjList,memberDetaislMap,designationMap);
			 if(locationAccessLevelId == 2l){//state access
				 List<Object[]> rtrnMpObjList = selfAppraisalCandidateDetailsDAO.getToursVisitedDetailsDistrictWiseBasedOnUserAccessLevel(locationAccessLevelId, locationValues, stateId, fromDate, toDate,"MP");
				 setMemberDetails(rtrnMpObjList,memberDetaislMap,designationMap); 
			 }
			 if(memberDetaislMap != null && memberDetaislMap.size() > 0){
				 Map<Long,ToursBasicVO> gsMap = memberDetaislMap.get(3l);
				 
				  if(gsMap != null && gsMap.size() > 0){
					
					  for(Entry<Long,ToursBasicVO> entry:gsMap.entrySet()){
						
						  ToursBasicVO VO = entry.getValue();
						 	  Map<Long,Set<Long>> locationMap = candiateAccessLevelMap.get(VO.getId());
							  if(locationMap != null && locationMap.size() > 0){
								  for(Entry<Long,Set<Long>> locationEntry:locationMap.entrySet()){
									  
									  for(Long id:locationEntry.getValue()){
										  
										  if(!VO.getLocationSet().contains(id)){
											  
											  VO.getLocationSet().add(id);
											  VO.setName(VO.getName()+","+districtMap.get(id)); // setting all district access of GS
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
					   VO.getSubList().addAll(new ArrayList<ToursBasicVO>(entry.getValue().values()));
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
						 candidateVO.setNoOfDistinctTours(commonMethodsUtilService.getLongValueForObject(param[9]));
						 candidateVO.setTotalSubmittedToursCnt(candidateVO.getOwnToursCnt()+candidateVO.getInchargerToursCnt());
						 Double averageTours = candidateVO.getTotalSubmittedToursCnt().doubleValue()/candidateVO.getNoOfDistinctTours().doubleValue();
						 candidateVO.setAverageTours(averageTours);
						 memberMap.put(commonMethodsUtilService.getLongValueForObject(param[3]), candidateVO);
					 }
				}
			}
		}catch(Exception e){
			LOG.error("Error occured at setMemberDetails() in CoreDashboardToursService ",e);	
		}
		
	}
	public ToursBasicVO getTopPoorToursLocationDetails(Long candidateId,Long userTypeId,Long stateId,String fromDateStr,String toDateStr){
		ToursBasicVO resultVO = new ToursBasicVO();
		Set<Long> locationValues = new HashSet<Long>();
		Map<Long,ToursBasicVO> toursDtslMap = new HashMap<Long, ToursBasicVO>();
		Long locationAccessLevelId =0l;
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		Date fromDate=null;
	    Date toDate=null;
	  try{
			if(fromDateStr != null && fromDateStr.trim().length()>0 && toDateStr!= null && toDateStr.trim().length()>0){
				 fromDate = sdf.parse(fromDateStr);
				 toDate = sdf.parse(toDateStr);
			 }
			// List<Object[]> rtrnUsrAccssLvlIdAndVlusObjLst=activityMemberAccessLevelDAO.getLocationLevelAndValuesByActivityMembersId(activityMemberId);
			List<Object[]> rtrnUsrAccssLvlIdAndVlusObjLst = selfAppraisalCandidateLocationDAO.getCandiateLocationScopeIdAndValues(candidateId);
			 if(rtrnUsrAccssLvlIdAndVlusObjLst != null && rtrnUsrAccssLvlIdAndVlusObjLst.size() > 0){
				 locationAccessLevelId=(Long) rtrnUsrAccssLvlIdAndVlusObjLst.get(0)[0];
				 for(Object[] param:rtrnUsrAccssLvlIdAndVlusObjLst){
					 locationValues.add(commonMethodsUtilService.getLongValueForObject(param[1]));
				 }
			 }
		    if(userTypeId != null && userTypeId.longValue()==IConstants.GENERAL_SECRETARY_USER_TYPE_ID || userTypeId.longValue()==IConstants.ORGANIZING_SECRETARY_USER_TYPE_ID 
			|| userTypeId.longValue() ==5l || userTypeId.longValue() ==2l || userTypeId.longValue()==1l ){ // 2 -DISTRICT PRESIDENTS , 5-SECRETARIES, 1-MINISTERS
		     List<Object[]> rtrnDistObJLst = selfAppraisalCandidateDetailsDAO.getToursVisitedDetailsLocationWiseBasedOnUserAccessLevel(locationAccessLevelId, locationValues, stateId, fromDate, toDate,"District",candidateId);
			  setToursDtlsToList(rtrnDistObJLst,toursDtslMap);
			  resultVO.getSubList().addAll(toursDtslMap.values());
			  toursDtslMap.clear();
		    }
		    if(userTypeId.longValue()==IConstants.MP_USER_TYPE_ID){
		    	  List<Object[]> rtrnParliamentObJLst = selfAppraisalCandidateDetailsDAO.getToursVisitedDetailsLocationWiseBasedOnUserAccessLevel(locationAccessLevelId, locationValues, stateId, fromDate, toDate,"ParliamentConstituency",candidateId);
				  setToursDtlsToList(rtrnParliamentObJLst,toursDtslMap);
				  resultVO.getSubList().addAll(toursDtslMap.values());
				  toursDtslMap.clear();
		    }
		   if(userTypeId.longValue()==IConstants.MLA_USER_TYPE_ID){
				  List<Object[]> rtrnCnsttncyObJLst = selfAppraisalCandidateDetailsDAO.getToursVisitedDetailsLocationWiseBasedOnUserAccessLevel(locationAccessLevelId, locationValues, stateId, fromDate, toDate,"Constituency",candidateId);
				  setToursDtlsToList(rtrnCnsttncyObJLst,toursDtslMap);
				  resultVO.getSubList().addAll(toursDtslMap.values());
				  toursDtslMap.clear();
			}
		    if(resultVO.getSubList() != null && resultVO.getSubList().size() > 0){
		    	Collections.sort(resultVO.getSubList(), toursPoorPerformanceAscendingPer);
		    }
		  /*  if(resultVO.getSubList2() != null && resultVO.getSubList2().size() > 0){
		    	Collections.sort(resultVO.getSubList2(), toursPoorPerformanceAscendingPer);
		    }*/
		}catch(Exception e){
			LOG.error("Error occured at getPoorToursLocationDetails() in CoreDashboardToursService ",e);	
		}
		return resultVO;
	}
	public void setToursDtlsToList(List<Object[]> objList,Map<Long,ToursBasicVO> toursDtslMap){
		try{
			if(objList != null && !objList.isEmpty()){
				for(Object[] param:objList){
					ToursBasicVO locationVO = new ToursBasicVO();
					locationVO.setId(commonMethodsUtilService.getLongValueForObject(param[0]));
					locationVO.setName(commonMethodsUtilService.getStringValueForObject(param[1]));
					locationVO.setSubmitedLeaderCnt(commonMethodsUtilService.getLongValueForObject(param[2]));
					locationVO.setOwnToursCnt(commonMethodsUtilService.getLongValueForObject(param[3]));
					locationVO.setInchargerToursCnt(commonMethodsUtilService.getLongValueForObject(param[4]));
					locationVO.setTotalSubmittedToursCnt(locationVO.getOwnToursCnt()+locationVO.getInchargerToursCnt());
					Double averageTours = locationVO.getTotalSubmittedToursCnt().doubleValue()/locationVO.getSubmitedLeaderCnt().doubleValue();
					locationVO.setAverageTours(averageTours);
					toursDtslMap.put(locationVO.getId(),locationVO);
				}
			}
		}catch(Exception e){
			LOG.error("Error occured at setToursDtlsToList() in CoreDashboardToursService ",e);	
		}
	}
	public static Comparator<ToursBasicVO> toursPoorPerformanceAscendingPer = new Comparator<ToursBasicVO>() {
	public int compare(ToursBasicVO member2, ToursBasicVO member1) {
	Double perc2 = member2.getAverageTours();
	Double perc1 = member1.getAverageTours();
	//ascending order of percantages.
	 return perc2.compareTo(perc1);
	}
	};

   public ToursBasicVO getLeaderAverageToursBasedOnAccessLevel(Long candidateId,Long stateId,String fromDateStr,String toDateStr){
	   ToursBasicVO resultVO = new ToursBasicVO();
	   Set<Long> locationValues = new HashSet<Long>();
	   Long locationAccessLevelId =0l;
	   SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
	   Date fromDate=null;
	   Date toDate=null;
	   try{
		   if(fromDateStr != null && fromDateStr.trim().length()>0 && toDateStr!= null && toDateStr.trim().length()>0){
				 fromDate = sdf.parse(fromDateStr);
				 toDate = sdf.parse(toDateStr);
			 }
			List<Object[]> rtrnUsrAccssLvlIdAndVlusObjLst = selfAppraisalCandidateTourLocationDAO.getCandiateLocationScopeIdAndValues(candidateId);
			 if(rtrnUsrAccssLvlIdAndVlusObjLst != null && rtrnUsrAccssLvlIdAndVlusObjLst.size() > 0){
				 locationAccessLevelId=(Long) rtrnUsrAccssLvlIdAndVlusObjLst.get(0)[0];
				 for(Object[] param:rtrnUsrAccssLvlIdAndVlusObjLst){
					 locationValues.add(commonMethodsUtilService.getLongValueForObject(param[1]));
				 }
			 }
			List<Object[]> rtrnObjList = selfAppraisalCandidateDetailsDAO.getToursVisitedDetailsByUserAccessLevel(locationAccessLevelId, locationValues, stateId, fromDate, toDate);
			List<ToursBasicVO> locationDtlsLst = new ArrayList<ToursBasicVO>();
			setToursDtlsToVO(rtrnObjList,locationDtlsLst);
			if(locationAccessLevelId.longValue()==1l){////district 
				resultVO.setId(locationAccessLevelId);
				resultVO.setName("DISTRICT WISE AVERAGE TOURS REPORT ");
			}else if(locationAccessLevelId.longValue()==2l){//parliamentConstituency
				resultVO.setId(locationAccessLevelId);
				resultVO.setName("PARLIAMENT CONSTITUENCY WISE AVERAGE TOURS REPORT");
			}else if(locationAccessLevelId.longValue()==3l){//constituency
				resultVO.setId(locationAccessLevelId);
				resultVO.setName("CONSTITUENCY WISE AVERAGE TOURS REPORT");
			}
			   resultVO.getSubList().addAll(locationDtlsLst);
		    if(resultVO.getSubList() != null && resultVO.getSubList().size() > 0){
		    	Collections.sort(resultVO.getSubList(), toursPoorPerformanceAscendingPer);
		    }
	   }catch(Exception e){
		    LOG.error("Error occured at setToursDtlsToList() in CoreDashboardToursService ",e);	 
	   }
	   return resultVO;
   }
   public void setToursDtlsToVO(List<Object[]> objList,List<ToursBasicVO> locationVOList){
		try{
			if(objList != null && !objList.isEmpty()){
				for(Object[] param:objList){
					ToursBasicVO locationVO = new ToursBasicVO();
					locationVO.setId(commonMethodsUtilService.getLongValueForObject(param[0]));
					locationVO.setName(commonMethodsUtilService.getStringValueForObject(param[1]));
					locationVO.setSubmitedLeaderCnt(commonMethodsUtilService.getLongValueForObject(param[2]));
					locationVO.setOwnToursCnt(commonMethodsUtilService.getLongValueForObject(param[3]));
					locationVO.setInchargerToursCnt(commonMethodsUtilService.getLongValueForObject(param[4]));
					locationVO.setNoOfDistinctTours(commonMethodsUtilService.getLongValueForObject(param[5]));
					locationVO.setTotalSubmittedToursCnt(locationVO.getOwnToursCnt()+locationVO.getInchargerToursCnt());
					Double averageTours = locationVO.getTotalSubmittedToursCnt().doubleValue()/locationVO.getNoOfDistinctTours().doubleValue();
					locationVO.setAverageTours(averageTours);
					locationVOList.add(locationVO);
				}
			}
		}catch(Exception e){
			LOG.error("Error occured at setToursDtlsToList() in CoreDashboardToursService ",e);	
		}
	}
}
