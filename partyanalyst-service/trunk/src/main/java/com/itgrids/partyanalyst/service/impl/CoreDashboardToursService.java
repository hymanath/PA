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
import java.util.Map.Entry;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;

import org.apache.log4j.Logger;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionCallback;
import org.springframework.transaction.support.TransactionTemplate;

import com.itgrids.partyanalyst.dao.IActivityMemberAccessLevelDAO;
import com.itgrids.partyanalyst.dao.IActivityMemberRelationDAO;
import com.itgrids.partyanalyst.dao.ICadreSurveyUserDAO;
import com.itgrids.partyanalyst.dao.IConstituencyDAO;
import com.itgrids.partyanalyst.dao.IDistrictDAO;
import com.itgrids.partyanalyst.dao.ISelfAppraisalCandidateDAO;
import com.itgrids.partyanalyst.dao.ISelfAppraisalCandidateDayTourDAO;
import com.itgrids.partyanalyst.dao.ISelfAppraisalCandidateDetailsDAO;
import com.itgrids.partyanalyst.dao.ISelfAppraisalCandidateLocationDAO;
import com.itgrids.partyanalyst.dao.ISelfAppraisalCandidateLocationNewDAO;
import com.itgrids.partyanalyst.dao.ISelfAppraisalCandidateTourLocationDAO;
import com.itgrids.partyanalyst.dao.ISelfAppraisalDesignationDAO;
import com.itgrids.partyanalyst.dao.ISelfAppraisalDesignationTargetDAO;
import com.itgrids.partyanalyst.dao.ITabLogInAuthDAO;
import com.itgrids.partyanalyst.dto.TabLoginAuthVO;
import com.itgrids.partyanalyst.dto.ToursBasicVO;
import com.itgrids.partyanalyst.dto.UserTypeVO;
import com.itgrids.partyanalyst.model.CadreSurveyUser;
import com.itgrids.partyanalyst.model.TabLogInAuth;
import com.itgrids.partyanalyst.service.ICoreDashboardToursService;
import com.itgrids.partyanalyst.utils.CommonMethodsUtilService;
import com.itgrids.partyanalyst.utils.DateUtilService;
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
	private TransactionTemplate transactionTemplate = null;
	private ICadreSurveyUserDAO cadreSurveyUserDAO;
	private ITabLogInAuthDAO tabLogInAuthDAO;
	private IActivityMemberRelationDAO activityMemberRelationDAO;
	//New Dao
	private ISelfAppraisalCandidateLocationNewDAO selfAppraisalCandidateLocationNewDAO;
	private ISelfAppraisalDesignationTargetDAO selfAppraisalDesignationTargetDAO;
	private ISelfAppraisalCandidateDayTourDAO selfAppraisalCandidateDayTourDAO;
	 

	public ITabLogInAuthDAO getTabLogInAuthDAO() {
		return tabLogInAuthDAO;
	}
	public void setTabLogInAuthDAO(ITabLogInAuthDAO tabLogInAuthDAO) {
		this.tabLogInAuthDAO = tabLogInAuthDAO;
	}
	public ICadreSurveyUserDAO getCadreSurveyUserDAO() {
		return cadreSurveyUserDAO;
	}
	public void setCadreSurveyUserDAO(ICadreSurveyUserDAO cadreSurveyUserDAO) {
		this.cadreSurveyUserDAO = cadreSurveyUserDAO;
	}
	public TransactionTemplate getTransactionTemplate() {
		return transactionTemplate;
	}
	public void setTransactionTemplate(TransactionTemplate transactionTemplate) {
		this.transactionTemplate = transactionTemplate;
	}
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
	public void setActivityMemberRelationDAO(
			IActivityMemberRelationDAO activityMemberRelationDAO) {
		this.activityMemberRelationDAO = activityMemberRelationDAO;
	}
	public void setSelfAppraisalCandidateLocationNewDAO(
			ISelfAppraisalCandidateLocationNewDAO selfAppraisalCandidateLocationNewDAO) {
		this.selfAppraisalCandidateLocationNewDAO = selfAppraisalCandidateLocationNewDAO;
	}
	public void setSelfAppraisalDesignationTargetDAO(
			ISelfAppraisalDesignationTargetDAO selfAppraisalDesignationTargetDAO) {
		this.selfAppraisalDesignationTargetDAO = selfAppraisalDesignationTargetDAO;
	}
	public void setSelfAppraisalCandidateDayTourDAO(
			ISelfAppraisalCandidateDayTourDAO selfAppraisalCandidateDayTourDAO) {
		this.selfAppraisalCandidateDayTourDAO = selfAppraisalCandidateDayTourDAO;
	}
	public ToursBasicVO getToursBasicOverviewCountDetails(Long stateId,String fromDateStr,String toDateStr,Long activityMemberId,Long userTypeId){
		
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
					 if(activityMemberId != null && activityMemberId == 4l || activityMemberId == 5l){
					 List<Long> districtIds = constituencyDAO.getDistrictsByConstituenciesIds(locationValues);
					 locationAccessLevelId = 3l; // district access level ids
					 locationValues.clear();
					 locationValues.addAll(districtIds);
					 }
				 }
		 		   List<Object[]> rtrnLeaderCntObjLst = selfAppraisalCandidateLocationDAO.getNoOfLeadersCntDesignationByBasedOnUserAccessLevel(locationAccessLevelId,locationValues,stateId,userTypeId);
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
				   
				   List<Object[]> rtrnSubmittedToursObjLst = selfAppraisalCandidateDetailsDAO.getToursSubmittedAndNoOfToursCntDesignationByBasedOnUserAccessLevel(locationAccessLevelId, locationValues, stateId, fromDate, toDate,userTypeId);
				   if(rtrnSubmittedToursObjLst != null && !rtrnSubmittedToursObjLst.isEmpty()){
					  	for(Object[] param:rtrnSubmittedToursObjLst){
				   				ToursBasicVO leaderVO = LeaderMemebersMap.get(commonMethodsUtilService.getLongValueForObject(param[0]));
				   					if(leaderVO != null){
				   					   leaderVO.setSubmitedLeaderCnt(commonMethodsUtilService.getLongValueForObject(param[1]));
			    					   leaderVO.setNotSubmitedLeaserCnt(leaderVO.getNoOfLeaderCnt()-leaderVO.getSubmitedLeaderCnt());
			    					   leaderVO.setNoOfDistinctTours(commonMethodsUtilService.getLongValueForObject(param[2]));
			    					}
				   			}
				   		}
			       List<Object[]> rtrnLdrOwnTursDtlsObjLst = selfAppraisalCandidateDetailsDAO.getOwnToursCntDetailstDesignationByBasedOnUserAccessLevel(locationAccessLevelId,locationValues, stateId, fromDate, toDate,userTypeId);
				   if(rtrnLdrOwnTursDtlsObjLst != null && !rtrnLdrOwnTursDtlsObjLst.isEmpty()){
					  	for(Object[] param:rtrnLdrOwnTursDtlsObjLst){
				   				ToursBasicVO leaderVO = LeaderMemebersMap.get(commonMethodsUtilService.getLongValueForObject(param[0]));
				   					if(leaderVO != null){
				   					   leaderVO.setOwnToursCnt(commonMethodsUtilService.getLongValueForObject(param[1]));
			    					}
				   			}
				   		}
				   List<Object[]> rtrnLdrInchargeTursDtlsObjLst = selfAppraisalCandidateDetailsDAO.getInchargeToursCntDetailstDesignationByBasedOnUserAccessLevel(locationAccessLevelId,locationValues, stateId, fromDate, toDate,userTypeId);
				   if(rtrnLdrInchargeTursDtlsObjLst != null && !rtrnLdrInchargeTursDtlsObjLst.isEmpty()){
					  	for(Object[] param:rtrnLdrInchargeTursDtlsObjLst){
				   				ToursBasicVO leaderVO = LeaderMemebersMap.get(commonMethodsUtilService.getLongValueForObject(param[0]));
				   					if(leaderVO != null){
				   					   leaderVO.setInchargerToursCnt(commonMethodsUtilService.getLongValueForObject(param[1]));
			    					}
				   			}
				   		}
				   if(LeaderMemebersMap != null && LeaderMemebersMap.size() > 0){
					   for(Entry<Long,ToursBasicVO> entry:LeaderMemebersMap.entrySet()){
						    ToursBasicVO leaderVO = entry.getValue();
						     if(leaderVO != null){
						    	   leaderVO.setTotalSubmittedToursCnt(leaderVO.getOwnToursCnt()+leaderVO.getInchargerToursCnt());
								   leaderVO.setSubmitedCandidateTourPer(calculatePercantage(leaderVO.getSubmitedLeaderCnt(),leaderVO.getNoOfLeaderCnt()));
		    					   leaderVO.setNotsubmitedCandidateTourPer(calculatePercantage(leaderVO.getNotSubmitedLeaserCnt(), leaderVO.getNoOfLeaderCnt()));
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
					/* Merge Sec And Org Sec Data */
				 if(LeaderMemebersMap != null && LeaderMemebersMap.size() > 0){
					     ToursBasicVO orgSecAndSecVO = new ToursBasicVO();
					     orgSecAndSecVO.setDesignation("ORGANIZING SECRETARY/SECRETARY"); 
					     orgSecAndSecVO.setId(4l);
					     
						 ToursBasicVO orgSecVO = LeaderMemebersMap.get(4l);
						 ToursBasicVO secVO = LeaderMemebersMap.get(5l);
						 
						 if(orgSecVO != null){
							orgSecAndSecVO.setNoOfLeaderCnt(orgSecVO.getNoOfLeaderCnt());
							orgSecAndSecVO.setNotSubmitedLeaserCnt(orgSecVO.getNotSubmitedLeaserCnt());
							orgSecAndSecVO.setSubmitedLeaderCnt(orgSecVO.getSubmitedLeaderCnt());
							orgSecAndSecVO.setNoOfDistinctTours(orgSecVO.getNoOfDistinctTours());
							orgSecAndSecVO.setOwnToursCnt(orgSecVO.getOwnToursCnt());
							orgSecAndSecVO.setInchargerToursCnt(orgSecVO.getInchargerToursCnt());
							orgSecAndSecVO.setTotalSubmittedToursCnt(orgSecAndSecVO.getOwnToursCnt()+orgSecAndSecVO.getInchargerToursCnt());
						 }
					     if(secVO != null){
							orgSecAndSecVO.setNoOfLeaderCnt(orgSecAndSecVO.getNoOfLeaderCnt()+secVO.getNoOfLeaderCnt());
							orgSecAndSecVO.setNotSubmitedLeaserCnt(orgSecAndSecVO.getNotSubmitedLeaserCnt()+secVO.getNotSubmitedLeaserCnt());
							orgSecAndSecVO.setSubmitedLeaderCnt(orgSecAndSecVO.getSubmitedLeaderCnt()+secVO.getSubmitedLeaderCnt());
							orgSecAndSecVO.setNoOfDistinctTours(orgSecAndSecVO.getNoOfDistinctTours()+secVO.getNoOfDistinctTours());
							orgSecAndSecVO.setOwnToursCnt(orgSecAndSecVO.getOwnToursCnt()+secVO.getOwnToursCnt());
							orgSecAndSecVO.setInchargerToursCnt(orgSecAndSecVO.getInchargerToursCnt()+secVO.getInchargerToursCnt());
							orgSecAndSecVO.setTotalSubmittedToursCnt(orgSecAndSecVO.getOwnToursCnt()+orgSecAndSecVO.getInchargerToursCnt());
						 }
					     orgSecAndSecVO.setSubmitedCandidateTourPer(calculatePercantage(orgSecAndSecVO.getSubmitedLeaderCnt(),orgSecAndSecVO.getNoOfLeaderCnt()));
					     orgSecAndSecVO.setNotsubmitedCandidateTourPer(calculatePercantage(orgSecAndSecVO.getNotSubmitedLeaserCnt(), orgSecAndSecVO.getNoOfLeaderCnt()));
					     Double averageTours = orgSecAndSecVO.getTotalSubmittedToursCnt().doubleValue()/orgSecAndSecVO.getNoOfDistinctTours().doubleValue();
	  					 if(!(averageTours.isNaN())){
	  						 orgSecAndSecVO.setAverageTours(averageTours);   
	  					 }
	  					 if(orgSecVO == null && secVO == null){
	  					 }else{
	  						 LeaderMemebersMap.remove(5l);//removing Sec
						     LeaderMemebersMap.put(4l, orgSecAndSecVO); 
	  					 }
	  			 }
			   	
			   if(LeaderMemebersMap != null && LeaderMemebersMap.size() > 0){
				   resultVO.getSubList().addAll(new ArrayList<ToursBasicVO>(LeaderMemebersMap.values()));   
				   LeaderMemebersMap.clear();  
			   }
			   if(resultVO.getSubList() != null && resultVO.getSubList().size() > 0){
				   Collections.sort(resultVO.getSubList(), toursLeaderSorting);
			   }
		}catch(Exception e){
			LOG.error("Error occured at getToursBasicOverviewCountDetails() in CoreDashboardToursService ",e);	
		}
		return resultVO;
	}
	public static Comparator<ToursBasicVO> toursLeaderSorting = new Comparator<ToursBasicVO>() {
		public int compare(ToursBasicVO member2, ToursBasicVO member1) {
		Long id = member2.getId();
		Long  id1 = member1.getId();
		//ascending order of percantages.
		 return id.compareTo(id1);
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
	public Double calculatePercantageBasedOnDouble(Double subCount,Double totalCount){
		Double d=0.0d;
		if(subCount.longValue()>0l && totalCount.longValue()==0l)
		LOG.error("Sub Count Value is "+subCount+" And Total Count Value  "+totalCount);

		if(totalCount.longValue() > 0l){
			 d = new BigDecimal(subCount * 100.0/totalCount).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();	 
		}
		return d;
		}
	public List<List<ToursBasicVO>> getDesigWiseMemberDtls(Long stateId,String fromDateStr,String toDateStr,Long activityMemberId,Long userTypeId, String level){
		try{
			Map<Long,Map<Long,ToursBasicVO>> desigIdAndMapOfCandIdAndCandDtlsMap = new LinkedHashMap<Long,Map<Long,ToursBasicVO>>();
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
			List<Long> desigList = new ArrayList<Long>();
			if(level.equalsIgnoreCase("bellow")){  
				if(userTypeId.longValue() == 2l){  
			    	desigList = Arrays.asList(IConstants.DISTRICT_PRESIDENT_SUB_LEVEL_DESIG_IDS);
			    }else if(userTypeId.longValue() == IConstants.GENERAL_SECRETARY_USER_TYPE_ID){
			    	desigList = Arrays.asList(new Long[]{4L,5L});      
			    }else if(userTypeId.longValue() == IConstants.ORGANIZING_SECRETARY_USER_TYPE_ID){
			    	desigList = Arrays.asList(IConstants.ORGANIZING_SECRETARY_SUB_LEVEL_DESIG_IDS);
			    }else if(userTypeId.longValue() == IConstants.MP_USER_TYPE_ID){
			    	desigList = Arrays.asList(IConstants.MP_SUB_LEVEL_DESIG_IDS);
			    }else if(userTypeId.longValue() == 5l){
			    	desigList = Arrays.asList(IConstants.SECRETARY_SUB_LEVEL_DESIG_IDS);    
			    } 
			}else{
				if(userTypeId.longValue() == IConstants.STATE_TYPE_USER_ID){  
			    	desigList = Arrays.asList(IConstants.STATE_SUB_LEVEL_DESIG_IDS);
			    }else if(userTypeId.longValue() == IConstants.GENERAL_SECRETARY_USER_TYPE_ID){
			    	desigList = Arrays.asList(IConstants.GENERAL_SECRETARY_SUB_LEVEL_DESIG_IDS);
			    }else if(userTypeId.longValue() == IConstants.ORGANIZING_SECRETARY_USER_TYPE_ID){
			    	desigList = Arrays.asList(IConstants.ORGANIZING_SECRETARY_SUB_LEVEL_DESIG_IDS);
			    }else if(userTypeId.longValue() == IConstants.DISTRICT_PRESIDENT_USER_TYPE_ID){
			    	desigList = Arrays.asList(IConstants.DISTRICT_PRESIDENT_SUB_LEVEL_DESIG_IDS);
			    }else if(userTypeId.longValue() == IConstants.MP_USER_TYPE_ID){
			    	desigList = Arrays.asList(IConstants.MP_SUB_LEVEL_DESIG_IDS);
			    }else if(userTypeId.longValue() == IConstants.SECRETARY_USER_TYPE_ID){
			    	desigList = Arrays.asList(IConstants.SECRETARY_SUB_LEVEL_DESIG_IDS);    
			    }  
			}
			if(activityMemberId != null && activityMemberId == 4l || activityMemberId == 5l){
				 List<Long> districtIds = constituencyDAO.getDistrictsByConstituenciesIds(locationValueSet);
				 locationScopeId = 3l; // district access level ids
				 locationValueSet.clear();
				 locationValueSet.addAll(districtIds);       
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
		    List<ToursBasicVO> subList1 = new ArrayList<ToursBasicVO>();
		    if(desigIdAndMapOfCandIdAndCandDtlsMap != null && desigIdAndMapOfCandIdAndCandDtlsMap.size() > 0){
		    	for(Entry<Long,Map<Long,ToursBasicVO>> entry : desigIdAndMapOfCandIdAndCandDtlsMap.entrySet()){
		    		candIdAndCandDtlsMap = desigIdAndMapOfCandIdAndCandDtlsMap.get(entry.getKey());
		    		if(level.equalsIgnoreCase("bellow")){
			    		subList = new ArrayList<ToursBasicVO>();
			    		if(candIdAndCandDtlsMap != null){
			    			subList.addAll(candIdAndCandDtlsMap.values());
			    			Collections.sort(subList, sortCandidateDesc);
			    			finalList.add(subList);  
			    		} 
		    		}else{       
		    			//collect the value of Sec/OS
			    		if(candIdAndCandDtlsMap != null && (entry.getKey().longValue()==4 || entry.getKey().longValue()==5)){
			    			subList1.addAll(candIdAndCandDtlsMap.values());
			    			continue;
			    		}
			    		//add Sec/os in the final list;
			    		if(entry.getKey().longValue() >= 6){
			    			if(subList1 != null && subList1.size() > 0){
			    		    	Collections.sort(subList1, sortCandidateDesc);
			    		    	finalList.add(subList1);
			    		    	subList1 = new ArrayList<ToursBasicVO>();
			    		    }
			    		}
			    		subList = new ArrayList<ToursBasicVO>();
			    		if(candIdAndCandDtlsMap != null){
			    			subList.addAll(candIdAndCandDtlsMap.values());
			    			Collections.sort(subList, sortCandidateDesc);
			    			finalList.add(subList);  
			    		} 
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
	public List<ToursBasicVO> getMemberDtlsForADesignation(List<Long> disigList,Long stateId,String fromDateStr,String toDateStr,Long activityMemberId,String outPutType){
		try{
			List<ToursBasicVO> docListVO = new ArrayList<ToursBasicVO>();
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
			//particularly for two members(convert constituency id into distring id)    
			if(activityMemberId != null && activityMemberId == 4l || activityMemberId == 5l){
				 List<Long> districtIds = constituencyDAO.getDistrictsByConstituenciesIds(locationValueSet);
				 locationScopeId = 3l; // district access level ids
				 locationValueSet.clear();
				 locationValueSet.addAll(districtIds);         
			}
		    List<Object[]> desigWiseAllCandidate = selfAppraisalCandidateLocationDAO.getDesigWiseAllCandidate(stateId,locationScopeId,locationValueSet,disigList);
		    
		    prepairDesignationWiseCandidateDtls(desigWiseAllCandidate, desigIdAndMapOfCandIdAndCandDtlsMap);
		    Map<Long,ToursBasicVO> candIdAndCandDtlsMap = null;
		    Map<Long,ToursBasicVO> candIdAndCandDtlsMapForAllDesig = new HashMap<Long,ToursBasicVO>();
		    
		    //get all the comment and file.
		    List<Long> cndIdListForCmtAndFile = new ArrayList<Long>();
		    if(disigList != null && disigList.size() > 0){
		    	for(Long id  : disigList){
		    		candIdAndCandDtlsMap = desigIdAndMapOfCandIdAndCandDtlsMap.get(id);
		    		candIdAndCandDtlsMapForAllDesig.putAll(candIdAndCandDtlsMap);
		    		if(candIdAndCandDtlsMap != null){
		    			cndIdListForCmtAndFile.addAll(candIdAndCandDtlsMap.keySet());
		    		}
		    	}
		    }
		    ToursBasicVO resultVO = null;
		    Map<Long,List<String>> candIdAndRemarksListMap = new HashMap<Long,List<String>>();
		    List<String> remarksList = null;
		    Map<Long,List<String>> candIdAndFilePathListMap = new HashMap<Long,List<String>>();    
		    List<String> filePathList = null;  
		    if(cndIdListForCmtAndFile != null && cndIdListForCmtAndFile.size() > 0){
		    	List<Object[]> memberDtls = selfAppraisalCandidateDetailsDAO.getCommendAndFilePathDtls(cndIdListForCmtAndFile,fromDate,toDate);
		    	if(memberDtls != null && memberDtls.size() > 0){  
		    		if(outPutType.equalsIgnoreCase("document")){
		    			for(Object[] param : memberDtls){
		    				resultVO = new ToursBasicVO();
		    				resultVO.setId(commonMethodsUtilService.getLongValueForObject(param[0]));         
		    				resultVO.setMonth(commonMethodsUtilService.getStringValueForObject(param[1]));
		    				resultVO.setYear(commonMethodsUtilService.getLongValueForObject(param[2]));
		    				resultVO.setComment(commonMethodsUtilService.getStringValueForObject(param[3]));
		    				resultVO.setFilePath(commonMethodsUtilService.getStringValueForObject(param[4]));
		    				resultVO.setOwnTours(commonMethodsUtilService.getLongValueForObject(param[5]));
		    				resultVO.setInchargerTours(commonMethodsUtilService.getLongValueForObject(param[6]));  
		    				if(commonMethodsUtilService.getLongValueForObject(param[0]) != null){
		    					if(candIdAndCandDtlsMapForAllDesig.get(commonMethodsUtilService.getLongValueForObject(param[0])) != null){
		    						resultVO.setName(candIdAndCandDtlsMapForAllDesig.get(commonMethodsUtilService.getLongValueForObject(param[0])).getName());
		    						resultVO.setDesignation(candIdAndCandDtlsMapForAllDesig.get(commonMethodsUtilService.getLongValueForObject(param[0])).getDesignation());
		    					}    
		    				}
		    				docListVO.add(resultVO);
		    			}
		    			return docListVO;  
		    		}else{
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
		    }
		    if(candIdAndFilePathListMap.keySet() != null ){
		    	Set<Long> cndIds = candIdAndFilePathListMap.keySet();
		    	
		    }
		    
		    List<Long> locValLstOfCandidate = null;
		    
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
	    		
	    		List<Long> CandidateIds = selfAppraisalCandidateLocationDAO.getCandiateIdList(locationScopeId,locationValueSet,desigIdList);
	    		if(CandidateIds != null && CandidateIds.size() > 0){
	    			toursBasicVO.setCandidateCount(Long.parseLong(Integer.toString(CandidateIds.size())));
	    		}
	    		List<Object[]> memDtlsList= selfAppraisalCandidateDetailsDAO.getSubmittedToursDetails(startDate,endDate,CandidateIds);
	    		Long selectedCandCount = 0l;    
	    		Long totalTours = 0l;
	    		if(memDtlsList != null && memDtlsList.size() > 0){   
	    			for(Object[] param : memDtlsList){
	    				selectedCandCount = selectedCandCount + 1;
	    				totalTours = totalTours + ((param[1] != null ? (Long)param[1] : 0l) + (param[2] != null ? (Long)param[2] : 0l));
	    			}       
	    			toursBasicVO.setSelectedCandCount(selectedCandCount);
	    			toursBasicVO.setTotalTour(totalTours);
	    		}
	    		
	    		Long totalUniqueTour = selfAppraisalCandidateDetailsDAO.geTtotalUniqueTour(CandidateIds,startDate,endDate);
	    		toursBasicVO.setInchargerToursCnt(totalUniqueTour);//total unique tours
	    		return toursBasicVO;      
	    		
	    	}catch(Exception e){  
	    		e.printStackTrace();
	    		LOG.error("Error Occured at getDesignationDtls() in ToursService class",e);
	    	}
	    	return null;  
	    }
	public List<ToursBasicVO> getDesignationLabelList(Long activityMemberId, Long userTypeId ){
		try{
			List<Long>  desigList = new ArrayList<Long>();
			List<Object[]> designationDtlsList = null;
			
			if(userTypeId.longValue() == IConstants.STATE_TYPE_USER_ID){
		    	desigList = Arrays.asList(IConstants.STATE_SUB_LEVEL_DESIG_IDS);
		    	designationDtlsList = selfAppraisalDesignationDAO.getDesignationDtls(desigList);
		    }else if(userTypeId.longValue() == IConstants.GENERAL_SECRETARY_USER_TYPE_ID){
		    	desigList = Arrays.asList(IConstants.GENERAL_SECRETARY_SUB_LEVEL_DESIG_IDS);
		    	designationDtlsList = selfAppraisalDesignationDAO.getDesignationDtls(desigList);
		    }else if(userTypeId.longValue() == IConstants.ORGANIZING_SECRETARY_USER_TYPE_ID){
		    	desigList = Arrays.asList(IConstants.ORGANIZING_SECRETARY_SUB_LEVEL_DESIG_IDS);
		    	designationDtlsList = selfAppraisalDesignationDAO.getDesignationDtls(desigList);
		    }else if(userTypeId.longValue() == IConstants.DISTRICT_PRESIDENT_USER_TYPE_ID){
		    	desigList = Arrays.asList(IConstants.DISTRICT_PRESIDENT_SUB_LEVEL_DESIG_IDS);  
		    	designationDtlsList = selfAppraisalDesignationDAO.getDesignationDtls(desigList);
		    }else if(userTypeId.longValue() == IConstants.MP_USER_TYPE_ID){
		    	desigList = Arrays.asList(IConstants.MP_SUB_LEVEL_DESIG_IDS);
		    	designationDtlsList = selfAppraisalDesignationDAO.getDesignationDtls(desigList);
		    }else if(userTypeId.longValue() == IConstants.SECRETARY_USER_TYPE_ID){
		    	desigList = Arrays.asList(IConstants.SECRETARY_SUB_LEVEL_DESIG_IDS);
		    	designationDtlsList = selfAppraisalDesignationDAO.getDesignationDtls(desigList);
		    }
			List<ToursBasicVO> basicVOs = new ArrayList<ToursBasicVO>();
			ToursBasicVO toursBasicVO = null; 
			ToursBasicVO orgAndSecVO = new ToursBasicVO();
			if(designationDtlsList != null && designationDtlsList.size() > 0){
				for(Object[] param : designationDtlsList){
					toursBasicVO = new ToursBasicVO();
					if(commonMethodsUtilService.getLongValueForObject(param[0])==4l || commonMethodsUtilService.getLongValueForObject(param[0])==5l){
					   if(orgAndSecVO.getComment() == null){
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
					if(orgAndSecVO != null && orgAndSecVO.getComment() != null){
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
	/*public List<ToursBasicVO> getDesignationLabelList(Long activityMemberId){
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
		    
		    ToursBasicVO orgAndSecVO = new ToursBasicVO();
			if(designationList != null && designationList.size() > 0){
				for(Object[] param : designationList){
					toursBasicVO = new ToursBasicVO();
					if(commonMethodsUtilService.getLongValueForObject(param[0])==4l || commonMethodsUtilService.getLongValueForObject(param[0])==5l){
					   if(orgAndSecVO.getComment() == null){
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
					if(orgAndSecVO != null && orgAndSecVO.getComment() != null){
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
	}*/
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
					 if(activityMemberId != null && activityMemberId == 4l || activityMemberId == 5l){
						 List<Long> districtIds = constituencyDAO.getDistrictsByConstituenciesIds(locationValues);
						 locationAccessLevelId = 3l; // district access level ids
						 locationValues.clear();
						 locationValues.addAll(districtIds);
					}
				 }
			 
			    List<Object[]> rtrnObjLst = selfAppraisalCandidateTourLocationDAO.getCandiateLocationScopeIdAndValuesByDesignation(3l);//get gs access level and values
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
			   	
			   List<Object[]> rtrnDistrictList = districtDAO.getDistrictListBystateId(stateId);
			   if(rtrnDistrictList != null && rtrnDistrictList.size() > 0){
				   for(Object[] param:rtrnDistrictList){
					   districtMap.put(commonMethodsUtilService.getLongValueForObject(param[0]), commonMethodsUtilService.getStringValueForObject(param[1])); 
				   }
			   }
				   
			 List<Object[]> rtrnSbmtedTurCanddateCntObjList = selfAppraisalCandidateDetailsDAO.getToursSubmittedCandidateCntAndNoOfToursDistrictWiseBsdOnUserAccssLvl(locationAccessLevelId, locationValues, stateId, fromDate, toDate,null,userTypeId);
			 setMemberDetails(rtrnSbmtedTurCanddateCntObjList,memberDetaislMap,designationMap);
			
			 List<Object[]> rtrnOwnToursCntObjLst = selfAppraisalCandidateDetailsDAO.getOwnToursCntDistrictWiseBsdOnUserAccssLvl(locationAccessLevelId, locationValues, stateId, fromDate, toDate,null,userTypeId);
			 if(rtrnOwnToursCntObjLst != null && rtrnOwnToursCntObjLst.size() > 0){
				 
				 for(Object[] param:rtrnOwnToursCntObjLst){
					 
					 Map<Long,ToursBasicVO> locationMap = memberDetaislMap.get(commonMethodsUtilService.getLongValueForObject(param[0]));//designationId
					 
					   if(locationMap != null ){
						   
						   ToursBasicVO locationVO =locationMap.get(commonMethodsUtilService.getLongValueForObject(param[1]));//locationId 
						   
						    if(locationVO != null){
						    	
								  locationVO.setOwnToursCnt(commonMethodsUtilService.getLongValueForObject(param[2]));
							}
					   }
				 }
			 }
			 List<Object[]> rtrnInchargeToursCntObjLst = selfAppraisalCandidateDetailsDAO.getInchargeToursCntDistrictWiseBsdOnUsrAccssLvl(locationAccessLevelId, locationValues, stateId, fromDate, toDate,null,userTypeId);
			 if(rtrnInchargeToursCntObjLst != null && rtrnInchargeToursCntObjLst.size() > 0){
				 
					 for(Object[] param:rtrnInchargeToursCntObjLst){
						 
						 Map<Long,ToursBasicVO> locationMap = memberDetaislMap.get(commonMethodsUtilService.getLongValueForObject(param[0]));//designationId
						 
						   if(locationMap != null ){
							   
							   ToursBasicVO locationVO =locationMap.get(commonMethodsUtilService.getLongValueForObject(param[1]));//locationId 
							   
							    if(locationVO != null){
							    	
							    	locationVO.setInchargerToursCnt(commonMethodsUtilService.getLongValueForObject(param[2]));
								}
						   }
					 }
			 }
			//Getting Mp Details
			   if(locationAccessLevelId == 2l){ // Mp will come only for state access user
				 List<Object[]> rtrnMpObjList = selfAppraisalCandidateDetailsDAO.getToursSubmittedCandidateCntAndNoOfToursDistrictWiseBsdOnUserAccssLvl(locationAccessLevelId, locationValues, stateId, fromDate, toDate,"MP",userTypeId);
				 setMemberDetails(rtrnMpObjList,memberDetaislMap,designationMap); 
				 
				 List<Object[]> rtrnMpOwnToursCntObjLst = selfAppraisalCandidateDetailsDAO.getOwnToursCntDistrictWiseBsdOnUserAccssLvl(locationAccessLevelId, locationValues, stateId, fromDate, toDate,"MP",userTypeId);
				 if(rtrnMpOwnToursCntObjLst != null && rtrnMpOwnToursCntObjLst.size() > 0){
					 
					 for(Object[] param:rtrnMpOwnToursCntObjLst){
						 
						 Map<Long,ToursBasicVO> locationMap = memberDetaislMap.get(commonMethodsUtilService.getLongValueForObject(param[0]));//designationId
						 
						   if(locationMap != null ){
							   
							   ToursBasicVO locationVO =locationMap.get(commonMethodsUtilService.getLongValueForObject(param[1]));//locationId 
							   
							    if(locationVO != null){
							    	
									  locationVO.setOwnToursCnt(commonMethodsUtilService.getLongValueForObject(param[2]));
								}
						   }
					 }
				 }
				 List<Object[]> rtrnMpInchargeToursCntObjLst = selfAppraisalCandidateDetailsDAO.getInchargeToursCntDistrictWiseBsdOnUsrAccssLvl(locationAccessLevelId, locationValues, stateId, fromDate, toDate,"MP",userTypeId);
				 if(rtrnMpInchargeToursCntObjLst != null && rtrnMpInchargeToursCntObjLst.size() > 0){
					 
						 for(Object[] param:rtrnMpInchargeToursCntObjLst){
							 
							 Map<Long,ToursBasicVO> locationMap = memberDetaislMap.get(commonMethodsUtilService.getLongValueForObject(param[0]));//designationId
							 
							   if(locationMap != null ){
								   
								   ToursBasicVO locationVO =locationMap.get(commonMethodsUtilService.getLongValueForObject(param[1]));//locationId 
								   
								    if(locationVO != null){
								    	
								    	locationVO.setInchargerToursCnt(commonMethodsUtilService.getLongValueForObject(param[2]));
									}
							   }
						 }
				 }
			   }
			 //calculating average
			 if(memberDetaislMap != null && memberDetaislMap.size() > 0){
				 
				 for(Entry<Long,Map<Long,ToursBasicVO>> entry:memberDetaislMap.entrySet()){
					 
					 Map<Long,ToursBasicVO> locationMap = entry.getValue();
					 
					  if(locationMap != null && locationMap.size() > 0){
						  
						  for(Entry<Long,ToursBasicVO> locationEntry:locationMap.entrySet()){
							  
							  if(locationEntry.getValue() != null){
								  
								  locationEntry.getValue().setTotalSubmittedToursCnt(locationEntry.getValue().getOwnToursCnt()+locationEntry.getValue().getInchargerToursCnt());
								  Double averageTours = locationEntry.getValue().getTotalSubmittedToursCnt().doubleValue()/locationEntry.getValue().getNoOfDistinctTours().doubleValue();
								  locationEntry.getValue().setAverageTours(averageTours);
							  }
						  }
					  }
				 }
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
			 /* Merge Sec And OrgSec data */
			 if(memberDetaislMap != null && memberDetaislMap.size() > 0){
				 Map<Long,ToursBasicVO> secAndOrgSecMap = new HashMap<Long, ToursBasicVO>();
				 Map<Long,ToursBasicVO> orgSecMap = memberDetaislMap.get(4l);
				 Map<Long,ToursBasicVO> secMap = memberDetaislMap.get(5l);
				 if(orgSecMap != null){
					 
					 secAndOrgSecMap.putAll(orgSecMap);
					 
					 if(secMap != null && secMap.size() > 0){
						 
						 for(Entry<Long,ToursBasicVO> entry:secMap.entrySet()){
							 
							  ToursBasicVO orgSecVO = secAndOrgSecMap.get(entry.getKey());
							  
							    if(orgSecVO == null){
							    	
							    	secAndOrgSecMap.put(entry.getKey(), entry.getValue());
							    	
							    }else{
							    	orgSecVO.setSubmitedLeaderCnt(orgSecVO.getSubmitedLeaderCnt()+entry.getValue().getSubmitedLeaderCnt());
							    	orgSecVO.setOwnToursCnt(orgSecVO.getOwnToursCnt()+entry.getValue().getOwnToursCnt());
							    	orgSecVO.setInchargerToursCnt(orgSecVO.getInchargerToursCnt()+entry.getValue().getInchargerToursCnt());
							    	orgSecVO.setNoOfDistinctTours(orgSecVO.getNoOfDistinctTours()+entry.getValue().getNoOfDistinctTours());
							    	orgSecVO.setTotalSubmittedToursCnt(orgSecVO.getTotalSubmittedToursCnt()+entry.getValue().getTotalSubmittedToursCnt());
									Double averageTours = orgSecVO.getTotalSubmittedToursCnt().doubleValue()/orgSecVO.getNoOfDistinctTours().doubleValue();
									orgSecVO.setAverageTours(averageTours);
							    }
						 }
					 }
				 }else{
					 if(secMap != null && secMap.size() > 0){
						 secAndOrgSecMap.putAll(secMap);	 
					 }
				 }
				 memberDetaislMap.remove(5l);//remove sec
				 memberDetaislMap.remove(4l);//remove org sec
				 if(secAndOrgSecMap != null && secAndOrgSecMap.size() > 0){
					 ToursBasicVO secOrgSecVO = new ToursBasicVO();
					 secOrgSecVO.setId(4l);
					 secOrgSecVO.setDesignation("ORGANIZING SECRETARY/SECRETARY");
					 secOrgSecVO.getSubList().addAll(new ArrayList<ToursBasicVO>(secAndOrgSecMap.values()));
					 resultList.add(secOrgSecVO); // adding sec and org sec merge data into result list.
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
			   if(resultList != null && resultList.size() > 0){
				   Collections.sort(resultList, toursLeaderSorting);
			   }
		}catch(Exception e){
			LOG.error("Error occured at getDistrictWiseToursLeaderDetails() in CoreDashboardToursService ",e);		
		}
		return resultList;
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
						 candidateVO.setNoOfDistinctTours(commonMethodsUtilService.getLongValueForObject(param[7]));
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

   public ToursBasicVO getLeaderAverageToursBasedOnAccessLevel(Long candidateId,Long stateId,String fromDateStr,String toDateStr,Long userTypeId){
	   ToursBasicVO resultVO = new ToursBasicVO();
	   Set<Long> locationValues = new HashSet<Long>();
	   Map<Long,ToursBasicVO> toursDtlsMap = new LinkedHashMap<Long, ToursBasicVO>();
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
			 if(locationAccessLevelId > 0l && locationValues != null && locationValues.size() > 0){
				 List<Object[]> rtrnSbmtdTurCnddateCntObjList = selfAppraisalCandidateDetailsDAO.getToursSubmittedCnddteAndNoOfToursCntByUserAccessLevel(locationAccessLevelId, locationValues, stateId, fromDate, toDate);	 
				 setToursDtlsToVO(rtrnSbmtdTurCnddateCntObjList,toursDtlsMap);
			 }
			 if(locationAccessLevelId > 0l && locationValues != null && locationValues.size() > 0){
				 List<Object[]> rtrnOwnTurObjLst = selfAppraisalCandidateDetailsDAO.getOwnToursCntByUserAccessLevel(locationAccessLevelId, locationValues, stateId, fromDate, toDate);	 
				 if(rtrnOwnTurObjLst != null && rtrnOwnTurObjLst.size() > 0){
					 for(Object[] param:rtrnOwnTurObjLst){
						 if(toursDtlsMap.get(commonMethodsUtilService.getLongValueForObject(param[0])) != null){
							 toursDtlsMap.get(commonMethodsUtilService.getLongValueForObject(param[0])).setOwnToursCnt(commonMethodsUtilService.getLongValueForObject(param[1])); 
						 }
					 }
				 }
			 }
			 if(locationAccessLevelId > 0l && locationValues != null && locationValues.size() > 0){
				 List<Object[]> rtrnInchargeObjLst = selfAppraisalCandidateDetailsDAO.getInchargeToursCntByUserAccessLevel(locationAccessLevelId, locationValues, stateId, fromDate, toDate);	 
				 if(rtrnInchargeObjLst != null && rtrnInchargeObjLst.size() > 0){
					 for(Object[] param:rtrnInchargeObjLst){
						 if(toursDtlsMap.get(commonMethodsUtilService.getLongValueForObject(param[0])) != null){
							 toursDtlsMap.get(commonMethodsUtilService.getLongValueForObject(param[0])).setInchargerToursCnt(commonMethodsUtilService.getLongValueForObject(param[1])); 
						 }
					 }
				 }
			 }
			 if(toursDtlsMap != null && toursDtlsMap.size() > 0){
				 for(Entry<Long,ToursBasicVO> entry:toursDtlsMap.entrySet()){
					      if(entry.getValue() != null){
					    	    entry.getValue().setTotalSubmittedToursCnt(entry.getValue().getOwnToursCnt()+entry.getValue().getInchargerToursCnt());
								Double averageTours = entry.getValue().getTotalSubmittedToursCnt().doubleValue()/entry.getValue().getNoOfDistinctTours().doubleValue();
								entry.getValue().setAverageTours(averageTours);
					      }
				 }
			 }
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
			  if(toursDtlsMap != null && toursDtlsMap.size() > 0){
				  resultVO.getSubList().addAll(toursDtlsMap.values());  
			  }
			if(resultVO.getSubList() != null && resultVO.getSubList().size() > 0){
		    	Collections.sort(resultVO.getSubList(), toursPoorPerformanceAscendingPer);
		    }
	   }catch(Exception e){
		    LOG.error("Error occured at setToursDtlsToList() in CoreDashboardToursService ",e);	 
	   }
	   return resultVO;
   }
   public void setToursDtlsToVO(List<Object[]> objList,Map<Long,ToursBasicVO> toursDtlsMap){
		try{
			if(objList != null && !objList.isEmpty()){
				for(Object[] param:objList){
					ToursBasicVO locationVO = new ToursBasicVO();
					locationVO.setId(commonMethodsUtilService.getLongValueForObject(param[0]));
					locationVO.setName(commonMethodsUtilService.getStringValueForObject(param[1]));
					locationVO.setSubmitedLeaderCnt(commonMethodsUtilService.getLongValueForObject(param[2]));
					locationVO.setNoOfDistinctTours(commonMethodsUtilService.getLongValueForObject(param[3]));
					toursDtlsMap.put(locationVO.getId(), locationVO);
				}
			}
		}catch(Exception e){
			LOG.error("Error occured at setToursDtlsToList() in CoreDashboardToursService ",e);	
		}
	}
   public List<ToursBasicVO> getTourSubmittedLeadersDetails(List<Long> designationIds,String isSubmitted,String fromDateStr,String toDateStr,Long activityMemberId,Long stateId,Long userTypeId){
	   List<ToursBasicVO> resultList = new ArrayList<ToursBasicVO>(0);
	   Map<Long,ToursBasicVO> leaderDtlsMap = new LinkedHashMap<Long, ToursBasicVO>(0);
	   Set<Long> locationValues = new HashSet<Long>();
	   SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
	   Long locationAccessLevelId =0l;
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
			
			List<Object[]> ownToursSubmittedCntObjLst = selfAppraisalCandidateDetailsDAO.getOwnTourSubmittedCandiatesDesignationWise(locationAccessLevelId, locationValues, stateId, fromDate, toDate, designationIds);
			List<Object[]> inchargeToursSubmittedCntObjLst = selfAppraisalCandidateDetailsDAO.getInchargeTourSubmittedCandiatesDesignationWise(locationAccessLevelId, locationValues, stateId, fromDate, toDate, designationIds);
	
			if(isSubmitted != null && isSubmitted.equalsIgnoreCase("Yes")){
			   setOWnToursSubmittedLeadersDtls(ownToursSubmittedCntObjLst,leaderDtlsMap,isSubmitted);
			   setInchargeToursSubmittedLeadersDtls(inchargeToursSubmittedCntObjLst,leaderDtlsMap,isSubmitted);
			   
		   }else if(isSubmitted.equalsIgnoreCase("No")){
			   setOWnToursSubmittedLeadersDtls(ownToursSubmittedCntObjLst,leaderDtlsMap,isSubmitted);
			   setInchargeToursSubmittedLeadersDtls(inchargeToursSubmittedCntObjLst,leaderDtlsMap,isSubmitted);
			 
			  List<Object[]> rtrnAllLeadersObjLst = selfAppraisalCandidateLocationDAO.getTotalLeaderDesignationWise(designationIds,locationAccessLevelId,locationValues,stateId);
			  Set<Long> submittedTourIdsSet = new HashSet<Long>(leaderDtlsMap.keySet());
			  leaderDtlsMap.clear();
			  if(rtrnAllLeadersObjLst != null && rtrnAllLeadersObjLst.size() > 0){
				  for(Object[] param:rtrnAllLeadersObjLst){
					  if(!submittedTourIdsSet.contains(commonMethodsUtilService.getLongValueForObject(param[0]))){
						  setNotSubmittedToursDetails(param,leaderDtlsMap,isSubmitted);   
					  }
				  }
			  }
		   }else if(isSubmitted.equalsIgnoreCase("All")){
			   
			   //Submitted candidate details
			   List<Object[]> rtrnAllLeadersObjLst = selfAppraisalCandidateLocationDAO.getTotalLeaderDesignationWise(designationIds,locationAccessLevelId,locationValues,stateId);
			   setOWnToursSubmittedLeadersDtls(ownToursSubmittedCntObjLst,leaderDtlsMap,"Yes");
			   setInchargeToursSubmittedLeadersDtls(inchargeToursSubmittedCntObjLst,leaderDtlsMap,"Yes");
			  
			   // not Submitted candidate details 
			   Set<Long> submittedTourIdsSet = new HashSet<Long>(leaderDtlsMap.keySet());
				  if(rtrnAllLeadersObjLst != null && rtrnAllLeadersObjLst.size() > 0){
					  for(Object[] param:rtrnAllLeadersObjLst){
						  if(!submittedTourIdsSet.contains(commonMethodsUtilService.getLongValueForObject(param[0]))){
							  setNotSubmittedToursDetails(param,leaderDtlsMap,"No");   
						  }
					  }
				  }
		   }
		   if(leaderDtlsMap != null && leaderDtlsMap.size() > 0){
			   for(Entry<Long,ToursBasicVO> entry:leaderDtlsMap.entrySet()){
				   if(entry.getValue() != null){
					   entry.getValue().setTotalSubmittedToursCnt(entry.getValue().getOwnToursCnt()+entry.getValue().getInchargerToursCnt());   
				   }
			   }
		   }
		   //Getting Candidate Location 
		   if(leaderDtlsMap != null && leaderDtlsMap.size() > 0){
			   Map<Long,Set<Long>> candiateScopeMap = new HashMap<Long, Set<Long>>();
			   Map<Long,Set<Long>> ministerScopeMap = new HashMap<Long, Set<Long>>();
			   Map<Long,Set<Long>> districtPersidentScopeMap = new HashMap<Long, Set<Long>>();
			   Map<Long,ToursBasicVO> locationMap = new HashMap<Long, ToursBasicVO>();
	           List<Object[]> candiateScopeIdsObjLst = selfAppraisalCandidateLocationDAO.getCandiateIdsScope(new ArrayList<Long>(leaderDtlsMap.keySet()),"MP/MLA/CI");
	           List<Object[]> dstrctPrsdntScopeObjLst = selfAppraisalCandidateLocationDAO.getCandiateIdsScope(new ArrayList<Long>(leaderDtlsMap.keySet()),"DistrictPresident");
	           List<Object[]>  ministerScopeObjLst = selfAppraisalCandidateLocationDAO.getCandiateIdsScope(new ArrayList<Long>(leaderDtlsMap.keySet()),"Minister");
	           if(candiateScopeIdsObjLst != null && candiateScopeIdsObjLst.size() > 0){
	        	   setCandidateIdsScopeWise(candiateScopeIdsObjLst,candiateScopeMap);
	 	       }
	           if(dstrctPrsdntScopeObjLst != null && dstrctPrsdntScopeObjLst.size() > 0){
	        	   setCandidateIdsScopeWise(dstrctPrsdntScopeObjLst,districtPersidentScopeMap);
	 	       }
	           if(ministerScopeObjLst != null && ministerScopeObjLst.size() > 0){
	        	   setCandidateIdsScopeWise(ministerScopeObjLst,ministerScopeMap);
	 	       }
	           if(candiateScopeMap != null && candiateScopeMap.size() > 0){
	        	   for(Entry<Long,Set<Long>> entry:candiateScopeMap.entrySet()){
	        		   Long locationScopeId = entry.getKey();
	        		    List<Object[]> locationObjLst = selfAppraisalCandidateLocationDAO.getCandiateLocation(locationScopeId,new ArrayList<Long>(entry.getValue()),null);
	        		    setCandiateLocation(locationScopeId,locationObjLst,locationMap);
	        	   }
	           }
	           if(ministerScopeMap != null && ministerScopeMap.size() > 0){
	        	   for(Entry<Long,Set<Long>> entry:ministerScopeMap.entrySet()){
	        		   Long locationScopeId = entry.getKey();
	        		    List<Object[]> locationObjLst = selfAppraisalCandidateLocationDAO.getCandiateLocation(locationScopeId,new ArrayList<Long>(entry.getValue()),"Minister");
	        		    setCandiateLocation(locationScopeId,locationObjLst,locationMap);
	        	   }
	           }
	           if(districtPersidentScopeMap != null && districtPersidentScopeMap.size() > 0){
	        	   for(Entry<Long,Set<Long>> entry:districtPersidentScopeMap.entrySet()){
	        		   Long locationScopeId = entry.getKey();
	        		    List<Object[]> locationObjLst = selfAppraisalCandidateLocationDAO.getCandiateLocation(locationScopeId,new ArrayList<Long>(entry.getValue()),"DistrictPresident");
	        		    setCandiateLocation(locationScopeId,locationObjLst,locationMap);
	        	   }
	            }
	           if(locationMap != null && locationMap.size() > 0){
	        	   for(Entry<Long,ToursBasicVO> entry:locationMap.entrySet()){
	        		   ToursBasicVO candiateVO = leaderDtlsMap.get(entry.getKey());
	        		    if(candiateVO != null){
	        		    	candiateVO.setLocationId(entry.getValue().getLocationId());
	        		    	candiateVO.setLocationName(entry.getValue().getLocationName());
	        		    	candiateVO.setDesignation(candiateVO.getDesignation()+"(<strong>"+entry.getValue().getLocationName()+"</strong>)");
	        		    }
	        	   }
	           }
		   }
		   if(leaderDtlsMap != null && leaderDtlsMap.size() > 0){
			   resultList.addAll(leaderDtlsMap.values());
			   leaderDtlsMap.clear();
		   }
		   return resultList;
	   }catch(Exception e){
		   LOG.error("Error occured at getTourSubmittedLeadersDetails() in CoreDashboardToursService ",e);	
	   }
	   return null;
   }
   public void setNotSubmittedToursDetails(Object[] param, Map<Long,ToursBasicVO> leaderDtlsMap,String isSubmitted){
	   try{
		   ToursBasicVO leaderVO = leaderDtlsMap.get(commonMethodsUtilService.getLongValueForObject(param[0]));
		    if(leaderVO == null){
		    	leaderVO = new ToursBasicVO();
		    	leaderVO.setCandDtlsId(commonMethodsUtilService.getLongValueForObject(param[0]));
		    	leaderVO.setName(commonMethodsUtilService.getStringValueForObject(param[1]));
		    	leaderVO.setMobileNo(commonMethodsUtilService.getStringValueForObject(param[2]));
		    	leaderVO.setDesignationId(commonMethodsUtilService.getLongValueForObject(param[3]));
		    	leaderVO.setDesignation(commonMethodsUtilService.getStringValueForObject(param[4]));
		    	leaderVO.setTdpCadreId(commonMethodsUtilService.getLongValueForObject(param[5]));
		    	leaderVO.setIsTourSubmitted(isSubmitted);	
		    	leaderDtlsMap.put(leaderVO.getCandDtlsId(), leaderVO); 
		    }  
	   }catch(Exception e){
		   LOG.error("Error occured at setNotSubmittedToursDetails() in CoreDashboardToursService ",e);   
	   }
   }
   public void setOWnToursSubmittedLeadersDtls(List<Object[]> objList,Map<Long,ToursBasicVO> leaderDtlsMap,String isSubmitted){
	   try{
		   if(objList!= null && objList.size() > 0){
			   for(Object[] param:objList){
				       ToursBasicVO leaderVO = new ToursBasicVO();
				    	leaderVO.setCandDtlsId(commonMethodsUtilService.getLongValueForObject(param[0]));
				    	leaderVO.setName(commonMethodsUtilService.getStringValueForObject(param[1]));
				    	leaderVO.setMobileNo(commonMethodsUtilService.getStringValueForObject(param[2]));
				    	leaderVO.setDesignationId(commonMethodsUtilService.getLongValueForObject(param[3]));
				    	leaderVO.setDesignation(commonMethodsUtilService.getStringValueForObject(param[4]));
				    	leaderVO.setTdpCadreId(commonMethodsUtilService.getLongValueForObject(param[5]));
				    	leaderVO.setOwnToursCnt(commonMethodsUtilService.getLongValueForObject(param[6]));
				    	leaderVO.setIsTourSubmitted(isSubmitted);	
				    	leaderDtlsMap.put(leaderVO.getCandDtlsId(), leaderVO);
				    }
		   }
	   }catch(Exception e){
		   LOG.error("Error occured at setOWnToursSubmittedLeadersDtls() in CoreDashboardToursService ",e);	  
	   }
   }
   public void setInchargeToursSubmittedLeadersDtls(List<Object[]> objList,Map<Long,ToursBasicVO> leaderDtlsMap,String isSubmitted){
	   try{
		   if(objList!= null && objList.size() > 0){
			   for(Object[] param:objList){
				       ToursBasicVO leaderVO = leaderDtlsMap.get(commonMethodsUtilService.getLongValueForObject(param[0]));
				       if(leaderVO == null){
				    	    leaderVO = new ToursBasicVO();
					    	leaderVO.setCandDtlsId(commonMethodsUtilService.getLongValueForObject(param[0]));
					    	leaderVO.setName(commonMethodsUtilService.getStringValueForObject(param[1]));
					    	leaderVO.setMobileNo(commonMethodsUtilService.getStringValueForObject(param[2]));
					    	leaderVO.setDesignationId(commonMethodsUtilService.getLongValueForObject(param[3]));
					    	leaderVO.setDesignation(commonMethodsUtilService.getStringValueForObject(param[4]));
					    	leaderVO.setTdpCadreId(commonMethodsUtilService.getLongValueForObject(param[5]));
					    	leaderVO.setInchargerToursCnt(commonMethodsUtilService.getLongValueForObject(param[6]));
					    	leaderVO.setIsTourSubmitted(isSubmitted);	
					    	leaderDtlsMap.put(leaderVO.getCandDtlsId(), leaderVO);  
				       }else{
				    	   leaderVO.setInchargerToursCnt(commonMethodsUtilService.getLongValueForObject(param[6]));  
				       }
				    }
		   }
	   }catch(Exception e){
		   LOG.error("Error occured at setInchargeToursSubmittedLeadersDtls() in CoreDashboardToursService ",e);	  
	   }
   }
   public void setCandidateIdsScopeWise(List<Object[]> objList, Map<Long,Set<Long>> candiateScopeMap){
	   try{
		  if(objList != null && objList.size() > 0){
			  for(Object[] param:objList){
				  Set<Long> candiateIdsSet = candiateScopeMap.get(commonMethodsUtilService.getLongValueForObject(param[0]));
				   if(candiateIdsSet == null){
					   candiateIdsSet = new HashSet<Long>();
					   candiateScopeMap.put(commonMethodsUtilService.getLongValueForObject(param[0]),candiateIdsSet);
				   }
				   candiateIdsSet.add(commonMethodsUtilService.getLongValueForObject(param[1]));
			  }
		  }
	   }catch(Exception e){
		   LOG.error("Error occured at setCandidateIdsScopeWise() in CoreDashboardToursService ",e);   
	   }
   }
  public void setCandiateLocation(Long locationScopeId,List<Object[]> locationObjLst,Map<Long,ToursBasicVO> locationMap){
	   try{
		   if(locationObjLst != null && locationObjLst.size() > 0){
			   for(Object[] param:locationObjLst){
				   ToursBasicVO locationVO = new ToursBasicVO();
				    locationVO.setCandDtlsId(commonMethodsUtilService.getLongValueForObject(param[0]));
				    locationVO.setLocationId(commonMethodsUtilService.getLongValueForObject(param[1]));
				    String suffix="";
				    if(locationScopeId.longValue() == 1l){
				    	suffix = "District";
				    }else if(locationScopeId.longValue() == 2l){
				    	suffix = "Parliament";
				    }else if(locationScopeId.longValue() == 3l){
				    	suffix = "Constituency";
				    }
				    locationVO.setLocationName(commonMethodsUtilService.getStringValueForObject(param[2])+" "+suffix);
				    locationMap.put(locationVO.getCandDtlsId(), locationVO);
			   }
		   }
	   }catch(Exception e){
		   LOG.error("Error occured at setCandiateLocation() in CoreDashboardToursService ",e);   
	   }
   }
   public void getSubLevelDtls(Long activityMemberId){
	   try{
		   
	   }catch(Exception e){
		   e.printStackTrace();
		   LOG.error("Error occured at getSubLevelDtls() in CoreDashboardToursService ",e);	
	   }
   }
   
   public String savingTabUserDetails(final Long loginUserId,final String userName,final String imeiNo){
		 String status = null;
		   try {
			   status = (String)transactionTemplate.execute(new TransactionCallback() {
				public Object doInTransaction(TransactionStatus arg0) {
					List<Long> tabLogInAuthIdsList = tabLogInAuthDAO.checkAlreadyExisyToUserIMEINo(userName,imeiNo);
					TabLogInAuth tabLogInAuth= null;
					if(tabLogInAuthIdsList == null || tabLogInAuthIdsList.size() == 0){
						List<CadreSurveyUser> list = cadreSurveyUserDAO.getCadreSurveyUserByUsername(userName);
						if(commonMethodsUtilService.isListOrSetValid(list)){
							CadreSurveyUser cadreSurveyUser = list.get(0);
							tabLogInAuth = new TabLogInAuth();
							tabLogInAuth.setCadreSurveyUserId(cadreSurveyUser.getCadreSurveyUserId());
							tabLogInAuth.setImeiNo(imeiNo);
							tabLogInAuth.setIsDeleted("N");
							tabLogInAuth.setInsertedTime(new DateUtilService().getCurrentDateAndTime());
							tabLogInAuth.setVersion("1");
							tabLogInAuth.setStatus("success");
							tabLogInAuth.setUpdatedById(loginUserId);
							tabLogInAuth = tabLogInAuthDAO.save(tabLogInAuth);
						}
					}
					
					if(tabLogInAuth != null)
						return "success";
					else
						return "failure";
				}
			}); 
		} catch (Exception e) {
			status = "failure";
			LOG.error("Error occured at savingTabUserDetails() in CoreDashboardToursService ",e);
		}
		   return status;
	   }
 public List<TabLoginAuthVO> getDetailsByUserName(String userName)
   {
  	List<TabLoginAuthVO> returnList = new ArrayList<TabLoginAuthVO>();
  	try{
  		List<Object[]> tabLoginDetails = tabLogInAuthDAO.getTabLoginDetails(userName);
  		if(tabLoginDetails != null && tabLoginDetails.size() > 0){
  			
  			for (Object[] param : tabLoginDetails) {
  				TabLoginAuthVO vo = new TabLoginAuthVO();
  				vo.setImiNo(commonMethodsUtilService.getStringValueForObject(param[0]));
  				vo.setInsertedTime(commonMethodsUtilService.getStringValueForObject(param[1]));
  				vo.setStatus(commonMethodsUtilService.getStringValueForObject(param[2]));
  				vo.setIsDeleted(commonMethodsUtilService.getStringValueForObject(param[3]));
  				vo.setTabLoginAuthId(commonMethodsUtilService.getLongValueForObject(param[4]));
  				//vo.setConstistuencyName(commonMethodsUtilService.getStringValueForObject(param[5]));
  				
  				returnList.add(vo);
  			}
  		}
  	}
  	catch(Exception e)
  	{
  		e.printStackTrace();
  		LOG.error("Exception Occured in getTabLoginDetails() Method - Exception is : ",e);
  	}
  	return returnList;
  	
  }
   public List<TabLoginAuthVO> getUpdatedIMEINumberDetails(String imeiNo)
   {
  	List<TabLoginAuthVO> returnList = new ArrayList<TabLoginAuthVO>();
  	try{
  		List<Object[]> tabLoginDetails = tabLogInAuthDAO.getTabUserDetails(imeiNo);
  		if(tabLoginDetails != null && tabLoginDetails.size() > 0){
  			
  			for (Object[] param : tabLoginDetails) {
  				TabLoginAuthVO vo = new TabLoginAuthVO();
  				vo.setCadreSurveyUserId(commonMethodsUtilService.getLongValueForObject(param[0]));
  				vo.setName(commonMethodsUtilService.getStringValueForObject(param[1]));
  				vo.setInsertedTime(commonMethodsUtilService.getStringValueForObject(param[2]));
  				vo.setStatus(commonMethodsUtilService.getStringValueForObject(param[3]));
  				vo.setIsDeleted(commonMethodsUtilService.getStringValueForObject(param[4]));
  				vo.setTabLoginAuthId(commonMethodsUtilService.getLongValueForObject(param[5]));
  				//vo.setConstistuencyName(commonMethodsUtilService.getStringValueForObject(param[6]));
  				returnList.add(vo);
  			}
  		}
  	}
  	catch(Exception e)
  	{
  		e.printStackTrace();
  		LOG.error("Exception Occured in getTabUserDetails() Method - Exception is : ",e);
  	}
  	return returnList;
  	
  }
   
	public String updateUserORIMEIDetails(Long loginAuthId) {
		String status = null;

		try {
			int tabLoginDetails = tabLogInAuthDAO
					.updateUserORIMEIDetails(loginAuthId);
			if (tabLoginDetails > 0)
				status = "success";

		} catch (Exception e) {
			e.printStackTrace();
			LOG.error("Exception Occured in updateUserORIMEIDetails Method - Exception is : ",e);
			status = "failure";
		}
		return status;

	}
	//
	public ToursBasicVO getToursBasicOverviewCountDetailsNew(Long stateId,String fromDateStr,String toDateStr,Long activityMemberId,Long userTypeId){
	  ToursBasicVO resultVO = new ToursBasicVO();
	  Map<Long,Set<Long>> parentChildMap = new HashMap<Long, Set<Long>>();
	  Map<Long,String> designationIdAndNameMap = new HashMap<Long, String>();
	  Map<Long,ToursBasicVO> overAllDesigMap = new HashMap<Long, ToursBasicVO>();
	  Set<Long> candiateIdsSet = new HashSet<Long>(0);
	  Map<Long,Map<Long,ToursBasicVO>> desigMap = new HashMap<Long, Map<Long,ToursBasicVO>>();
	  SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
	  Date fromDate=null;
	  Date toDate=null;
	   try{
		   if(fromDateStr != null && fromDateStr.trim().length()>0 && toDateStr!= null && toDateStr.trim().length()>0){
				 fromDate = sdf.parse(fromDateStr);
				 toDate = sdf.parse(toDateStr);
			 }
		   List<Long> activityMemberLst = new ArrayList<Long>();
		   activityMemberLst.add(activityMemberId);
		   List<Object[]> rtrnParentChildObjLst = activityMemberRelationDAO.getChildMemberIdsBasedOnParentTypeIds(activityMemberLst);
		   	if(rtrnParentChildObjLst != null && rtrnParentChildObjLst.size() > 0){
		   		for(Object[] param:rtrnParentChildObjLst){
		   			Set<Long> childActivityMemberSet = parentChildMap.get(commonMethodsUtilService.getLongValueForObject(param[0]));
		   				if(childActivityMemberSet == null ){
		   					childActivityMemberSet = new HashSet<Long>();
		   					parentChildMap.put(commonMethodsUtilService.getLongValueForObject(param[0]),childActivityMemberSet);
		   				}
		   				childActivityMemberSet.add(commonMethodsUtilService.getLongValueForObject(param[1]));
		   		}
		   	}
		   	if(parentChildMap != null && parentChildMap.size() > 0){
		   		for(Entry<Long,Set<Long>> entry:parentChildMap.entrySet()){
		   			List<Object[]> rtrnCandidateObjLst = selfAppraisalCandidateDAO.getCandidateIdsAndDesignationByActivityMemberIds(new ArrayList<Long>(entry.getValue()));
		   			buildDesignationWiseCandiateStructure(rtrnCandidateObjLst,desigMap,designationIdAndNameMap,candiateIdsSet);
		   		}
		   	}
		   	if(candiateIdsSet != null && candiateIdsSet.size() > 0){
		   			List<Object[]> rtrnToursSubmittedDtlsObjLst = selfAppraisalCandidateDetailsDAO.getTourSubmittedCandidateDetailsByCandiateIds(new ArrayList<Long>(candiateIdsSet), fromDate, toDate);
		   			setToursSubmittedDetails(rtrnToursSubmittedDtlsObjLst,desigMap);
		   	}
		   
		   	if(desigMap != null && desigMap.size() > 0){
		   		
		   		for(Entry<Long,Map<Long,ToursBasicVO>> desigEntry:desigMap.entrySet()){
		   			
		   			if(desigEntry.getValue() != null && desigEntry.getValue().size() > 0){
		   				
		   				for(Entry<Long,ToursBasicVO> candiateEntry:desigEntry.getValue().entrySet()){
		   					
		   					ToursBasicVO candiateVO = overAllDesigMap.get(desigEntry.getKey());
		   					
		   					if(candiateVO == null){
		   						candiateVO = new ToursBasicVO();
		   						overAllDesigMap.put(desigEntry.getKey(), candiateVO);
		   					}
		   					candiateVO.setNoOfDistinctTours(candiateVO.getNoOfDistinctTours()+candiateEntry.getValue().getNoOfDistinctTours());
		   					candiateVO.setOwnToursCnt(candiateVO.getOwnToursCnt()+candiateEntry.getValue().getOwnToursCnt());
		   					candiateVO.setInchargerToursCnt(candiateVO.getInchargerToursCnt()+candiateEntry.getValue().getInchargerToursCnt());
		   					candiateVO.setTotalSubmittedToursCnt(candiateVO.getTotalSubmittedToursCnt()+candiateEntry.getValue().getTotalSubmittedToursCnt());
		   					if(candiateEntry.getValue().getTotalSubmittedToursCnt() > 0l){
		   						candiateVO.setSubmitedLeaderCnt(candiateVO.getSubmitedLeaderCnt()+1);	
		   					}else{
		   						candiateVO.setNotSubmitedLeaserCnt(candiateVO.getNotSubmitedLeaserCnt()+1);	
		   					}
		   					candiateVO.setNoOfLeaderCnt(candiateVO.getSubmitedLeaderCnt()+candiateVO.getNotSubmitedLeaserCnt());
		   				}
		   			}
		   		}
		   	}
		   	ToursBasicVO overAllDtlsVO = new ToursBasicVO();
		   	if(overAllDesigMap != null && overAllDesigMap.size() > 0){
		   		
		   		for(Entry<Long,ToursBasicVO> entry:overAllDesigMap.entrySet()){
		   			
		   			overAllDtlsVO.setNoOfDistinctTours(overAllDtlsVO.getNoOfDistinctTours()+entry.getValue().getNoOfDistinctTours());
		   			overAllDtlsVO.setOwnToursCnt(overAllDtlsVO.getOwnToursCnt()+entry.getValue().getOwnToursCnt());
		   			overAllDtlsVO.setInchargerToursCnt(overAllDtlsVO.getInchargerToursCnt()+entry.getValue().getInchargerToursCnt());
		   			overAllDtlsVO.setTotalSubmittedToursCnt(overAllDtlsVO.getTotalSubmittedToursCnt()+entry.getValue().getTotalSubmittedToursCnt());
		   			overAllDtlsVO.setSubmitedLeaderCnt(overAllDtlsVO.getSubmitedLeaderCnt()+entry.getValue().getSubmitedLeaderCnt());
		   			overAllDtlsVO.setNotSubmitedLeaserCnt(overAllDtlsVO.getNotSubmitedLeaserCnt()+entry.getValue().getNotSubmitedLeaserCnt());
		   			overAllDtlsVO.setNoOfLeaderCnt(overAllDtlsVO.getSubmitedLeaderCnt()+overAllDtlsVO.getNotSubmitedLeaserCnt());
		   			entry.getValue().setSubmitedCandidateTourPer(calculatePercantage(entry.getValue().getSubmitedLeaderCnt(),entry.getValue().getNoOfLeaderCnt()));
		   			entry.getValue().setNotsubmitedCandidateTourPer(calculatePercantage(entry.getValue().getNotSubmitedLeaserCnt(), entry.getValue().getNoOfLeaderCnt()));
			        Double averageTours = entry.getValue().getTotalSubmittedToursCnt().doubleValue()/entry.getValue().getNoOfDistinctTours().doubleValue();
			        if(!(averageTours.isNaN())){
			    	   entry.getValue().setAverageTours(averageTours);   
			      } 
		   		}
		   	}
		   	overAllDtlsVO.setSubmitedCandidateTourPer(calculatePercantage(overAllDtlsVO.getSubmitedLeaderCnt(),overAllDtlsVO.getNoOfLeaderCnt()));
		   	overAllDtlsVO.setNotsubmitedCandidateTourPer(calculatePercantage(overAllDtlsVO.getNotSubmitedLeaserCnt(), overAllDtlsVO.getNoOfLeaderCnt()));
	        Double averageTours = overAllDtlsVO.getTotalSubmittedToursCnt().doubleValue()/overAllDtlsVO.getNoOfDistinctTours().doubleValue();
	        if(!(averageTours.isNaN())){
	        	overAllDtlsVO.setAverageTours(averageTours);   
	      } 
	       if(overAllDesigMap != null && overAllDesigMap.size() > 0){
	    	   for(Entry<Long,ToursBasicVO> entry:overAllDesigMap.entrySet()){
	    		   entry.getValue().setDesignationId(entry.getKey());
	    		   entry.getValue().setDesignation(designationIdAndNameMap.get(entry.getKey()));
	    		   resultVO.getSubList().add(entry.getValue());  
	    	   }
	       }
	       resultVO.setOverAllDetailsVO(overAllDtlsVO);  
	   }catch(Exception e){
		 LOG.error("Exception Occured in getToursBasicOverViewDetails Method - Exception is : ",e);   
	   }
	   return resultVO;
   }
  public void buildDesignationWiseCandiateStructure(List<Object[]> objList, Map<Long,Map<Long,ToursBasicVO>> desigMap, Map<Long,String> designationIdAndNameMap,Set<Long> candiateIdsSet){
	  try{
		  if(objList != null && !objList.isEmpty()){
			  for(Object[] param:objList){
				  Map<Long,ToursBasicVO> candiateMap = desigMap.get(commonMethodsUtilService.getLongValueForObject(param[0]));
				  	if(candiateMap == null ){
				  		candiateMap = new HashMap<Long, ToursBasicVO>();
				  		designationIdAndNameMap.put(commonMethodsUtilService.getLongValueForObject(param[0]),commonMethodsUtilService.getStringValueForObject(param[1]));
				  		desigMap.put(commonMethodsUtilService.getLongValueForObject(param[0]), candiateMap);
				  	}
				  	ToursBasicVO candiateVO = candiateMap.get(commonMethodsUtilService.getLongValueForObject(param[2]));
				  	if(candiateVO == null){
				  		candiateVO = new ToursBasicVO();
				  	 	candiateVO.setId(commonMethodsUtilService.getLongValueForObject(param[2]));
					  	candiateMap.put(candiateVO.getId(), candiateVO);
				  	}
				  	candiateIdsSet.add(commonMethodsUtilService.getLongValueForObject(param[2]));
			  }
		  }
	  }catch(Exception e){
		  LOG.error("Exception Occured in buildDesignationWiseCandiateStructure Method - Exception is : ",e); 
	  }
  }
  public void setToursSubmittedDetails(List<Object[]> objList,Map<Long,Map<Long,ToursBasicVO>> desigMap) {
		if(objList != null && objList.size() > 0){
			for(Object[] param:objList){
				Long designationId = commonMethodsUtilService.getLongValueForObject(param[0]);
				Long candidateId = commonMethodsUtilService.getLongValueForObject(param[1]);
				Map<Long,ToursBasicVO> candiateMap = desigMap.get(designationId);
				 if(candiateMap != null){
					 if(candiateMap.get(candidateId) != null ){
						 candiateMap.get(candidateId).setNoOfDistinctTours(commonMethodsUtilService.getLongValueForObject(param[2]));
						 candiateMap.get(candidateId).setOwnToursCnt(commonMethodsUtilService.getLongValueForObject(param[3]));
						 candiateMap.get(candidateId).setInchargerToursCnt(commonMethodsUtilService.getLongValueForObject(param[4]));
						 candiateMap.get(candidateId).setTotalSubmittedToursCnt(candiateMap.get(candidateId).getOwnToursCnt()+candiateMap.get(candidateId).getInchargerToursCnt());
					 }
				 }
			}
		}
	}
  
 
  //New Tours Service Based on New Screen Change 

  /*
   * Author : Santosh
   */
  
  public List<Date> getDates(String startDateString,String endDateString,SimpleDateFormat sdf){
  	List<Date> datesList = new ArrayList<Date>();
  	Date startDate = null;
  	Date endDate = null;
  	try{
  		if(startDateString != null && !startDateString.isEmpty()){
 	    	 startDate = sdf.parse(startDateString);
  		}
	   	    if(endDateString != null && !endDateString.isEmpty()){
	   	    	 endDate = sdf.parse(endDateString);
	   	    }
		}catch(Exception e){
			e.printStackTrace();
		}
	    datesList.add(0,startDate);
	    datesList.add(1,endDate);
	    return datesList;
  }
  public static final int getMonthsDifference(Date date1, Date date2) {
	    if(date1 == null || date2 == null)
	    	return 0;
	    @SuppressWarnings("deprecation")
		int m1 = date1.getYear() * 12 + date1.getMonth();
	    @SuppressWarnings("deprecation")
		int m2 = date2.getYear() * 12 + date2.getMonth();
	    return m2 - m1 + 1;
 }
  
  public ToursBasicVO getToursBasicOverviewDtls(Long stateId,String fromDateStr,String toDateStr,Long activityMemberId,Long userTypeId){
	  
	  ToursBasicVO resultVO = new ToursBasicVO();
	  Map<Long,List<ToursBasicVO>> categoryWiseMap = new HashMap<Long, List<ToursBasicVO>>(0);
	  Map<Long,List<ToursBasicVO>> designationMonthTarget = new HashMap<Long, List<ToursBasicVO>>();
	  Map<Long,Map<Long,ToursBasicVO>> candiateDtlsMap = new HashMap<Long, Map<Long,ToursBasicVO>>();
	  Set<Long> candidateIdSet = new HashSet<Long>();
	  Map<Long,ToursBasicVO> designationMap = new HashMap<Long, ToursBasicVO>();
	  Set<Long> locationValues = new HashSet<Long>(0);
	  Long locationAccessLevelId = 0l;
	  Date fromDate=null;
	  Date toDate = null;
	  try{
		  List<Date>  datesList = getDates(fromDateStr, toDateStr, new SimpleDateFormat("dd/MM/yyyy"));
		  fromDate=datesList.get(0);
		  toDate=datesList.get(1);
		  
		  List<Object[]> rtrnUsrAccssLvlIdAndVlusObjLst=activityMemberAccessLevelDAO.getLocationLevelAndValuesByActivityMembersId(activityMemberId);
			 if(rtrnUsrAccssLvlIdAndVlusObjLst != null && rtrnUsrAccssLvlIdAndVlusObjLst.size() > 0){
				 locationAccessLevelId=(Long) rtrnUsrAccssLvlIdAndVlusObjLst.get(0)[0];
				 for(Object[] param:rtrnUsrAccssLvlIdAndVlusObjLst){
					 locationValues.add(commonMethodsUtilService.getLongValueForObject(param[1]));
				 }
			 }
			 
		 int noOfMonth = getMonthsDifference(fromDate,toDate);	 
		 
		 List<Object[]> rtrnobjCtgryWseTargetLst = selfAppraisalDesignationTargetDAO.getTourCategoryWiseTargetCnt(fromDate, toDate,"Category");
		 setCategroyWiseTarget(rtrnobjCtgryWseTargetLst,categoryWiseMap,"Category",noOfMonth);
		 List<Object[]> rtrnobjGovtTargetLst = selfAppraisalDesignationTargetDAO.getTourCategoryWiseTargetCnt(fromDate, toDate,"Govt");
		 setCategroyWiseTarget(rtrnobjGovtTargetLst,categoryWiseMap,"Govt",noOfMonth);
	
		//Taking Static Designation Wise Target
		 setCategroyWiseTarget(rtrnobjCtgryWseTargetLst,designationMonthTarget,"Category",1);
		 setCategroyWiseTarget(rtrnobjGovtTargetLst,designationMonthTarget,"Govt",1);  
		 
		  List<Object[]> rtrnCategoryWiseSubmittedLdrs = selfAppraisalCandidateDayTourDAO.getCategoryWiseTourSubmittedLeader(fromDate, toDate, "Category");
		  setCategoryWiseTourSubmittedLeader(rtrnCategoryWiseSubmittedLdrs,categoryWiseMap,"Category");
		  List<Object[]> rtrnGovtSubmittedLdrs = selfAppraisalCandidateDayTourDAO.getCategoryWiseTourSubmittedLeader(fromDate, toDate, "Govt");
		  setCategoryWiseTourSubmittedLeader(rtrnGovtSubmittedLdrs,categoryWiseMap,"Govt");
		  
		  
		 List<Object[]> rtrnDsgntnWseLderObjLst = selfAppraisalCandidateLocationNewDAO.getNoOfLdrsCntDesignationByBasedOnUserAccessLevel(locationAccessLevelId, locationValues, stateId, userTypeId,"overAll"); 
		 setDesignationWiseLeaders(rtrnDsgntnWseLderObjLst,designationMap,categoryWiseMap);
		 
		 List<Object[]> rtrnDsgntnWseCandiateObjLst = selfAppraisalCandidateLocationNewDAO.getNoOfLdrsCntDesignationByBasedOnUserAccessLevel(locationAccessLevelId, locationValues, stateId, userTypeId,"Candiate"); 
		 setCandidateIds(rtrnDsgntnWseCandiateObjLst,candidateIdSet);
		 
		 List<Object[]> rtrnSubmittedLdrObjLst = selfAppraisalCandidateDayTourDAO.getToursSubmittedLeaderCntDesignationBy(fromDate, toDate,candidateIdSet);
		 setTourSubmitteedLdrCnt(rtrnSubmittedLdrObjLst,designationMap);
		
		 List<Object[]> rtrnCategoryWiseComplainceOblLst = selfAppraisalCandidateDayTourDAO.getLeaderComplainceCntCategoryWise(fromDate, toDate, "Category",null,candidateIdSet);
		 prepareCandiateWiseDtlsToCalculateComplainceCandiate(rtrnCategoryWiseComplainceOblLst,candiateDtlsMap,categoryWiseMap,"category");
		 setCategroyWiseComplainceCandidateCnt(rtrnCategoryWiseComplainceOblLst,designationMap,"Category");
		 List<Object[]> rtrnGovtWorkWiseComplainceOblLst = selfAppraisalCandidateDayTourDAO.getLeaderComplainceCntCategoryWise(fromDate, toDate, "Govt",null,candidateIdSet);
		 prepareCandiateWiseDtlsToCalculateComplainceCandiate(rtrnGovtWorkWiseComplainceOblLst,candiateDtlsMap,categoryWiseMap,"Govt");
		 setCategroyWiseComplainceCandidateCnt(rtrnGovtWorkWiseComplainceOblLst,designationMap,"Govt");
		
		 if(designationMap != null && designationMap.size() > 0){
			 for(Entry<Long, ToursBasicVO> entry:designationMap.entrySet()){
				 if(designationMonthTarget.get(entry.getKey()) != null && designationMonthTarget.get(entry.getKey()).size() > 0){
					 entry.getValue().getSubList().addAll(designationMonthTarget.get(entry.getKey()));	 
				 }
			 }
		 }
		 
		 //calculating Complaince Member Designation Wise
		 if(candiateDtlsMap != null && candiateDtlsMap.size() > 0){
			 
			 for(Entry<Long,Map<Long,ToursBasicVO>> designationEntry:candiateDtlsMap.entrySet()){
				 
				 if(designationEntry.getValue() != null && designationEntry.getValue().size() > 0l){
					 
					 for(Entry<Long,ToursBasicVO> entry:designationEntry.getValue().entrySet()){
						 
						 List<ToursBasicVO> categoryList = entry.getValue().getSubList3();
						 
						   if(categoryList != null && categoryList.size() > 0){
								   Double totalPer= 0.0d;
								   for(ToursBasicVO VO:categoryList){
									   totalPer = totalPer+VO.getComplaincePer();
								   }
								   
								   Integer totalCount =0;
								   if(categoryList != null && categoryList.size() > 0){
									    totalCount = categoryList.size() * 100;   
								   }
								   
								   ToursBasicVO candiateVO = designationMap.get(designationEntry.getKey());
								   if(candiateVO != null){
									   Double percentage = calculatePercantageBasedOnDouble(totalPer,totalCount.doubleValue());
									     if(percentage >=100d){
										    candiateVO.setComplainceCnt(candiateVO.getComplainceCnt()+1);
											if(candiateVO.getComplaincandidateIdsSet() == null){
												 candiateVO.setComplaincandidateIdsSet(new HashSet<Long>()); 
											}
											candiateVO.getComplaincandidateIdsSet().add(entry.getKey());
										 }else{
											 candiateVO.setNonComplainceCnt(candiateVO.getNonComplainceCnt()+1); 
											if(candiateVO.getNoNComplaincandidateIdsSet() == null){
												candiateVO.setNoNComplaincandidateIdsSet(new HashSet<Long>(0));
											}
											candiateVO.getNoNComplaincandidateIdsSet().add(entry.getKey());
										 }     
								   }
								
							  }
						   }
					 }
				 }
			 }
	
		 //calculation overAll percentage
			if(designationMap != null && designationMap.size() > 0){
			     ToursBasicVO overAllDtlsVO = new ToursBasicVO();
				for(Entry<Long,ToursBasicVO> entry:designationMap.entrySet()){
					    if(entry.getValue() != null){
					       entry.getValue().setSubmitedCandidateTourPer(calculatePercantage(entry.getValue().getSubmitedLeaderCnt(),entry.getValue().getNoOfLeaderCnt()));
					       entry.getValue().setNotsubmitedCandidateTourPer(calculatePercantage(entry.getValue().getNotSubmitedLeaserCnt(),entry.getValue().getNoOfLeaderCnt()));
					       overAllDtlsVO.setNoOfLeaderCnt(overAllDtlsVO.getNoOfLeaderCnt()+entry.getValue().getNoOfLeaderCnt());
				           overAllDtlsVO.setSubmitedLeaderCnt(overAllDtlsVO.getSubmitedLeaderCnt()+entry.getValue().getSubmitedLeaderCnt());
    					   overAllDtlsVO.setNotSubmitedLeaserCnt(overAllDtlsVO.getNotSubmitedLeaserCnt()+entry.getValue().getNotSubmitedLeaserCnt());
    					   overAllDtlsVO.setComplainceCnt(overAllDtlsVO.getComplainceCnt()+entry.getValue().getComplainceCnt());
    					   overAllDtlsVO.setNonComplainceCnt(overAllDtlsVO.getNonComplainceCnt()+entry.getValue().getNonComplainceCnt());
    					 }
				}
				   overAllDtlsVO.setSubmitedCandidateTourPer(calculatePercantage(overAllDtlsVO.getSubmitedLeaderCnt(), overAllDtlsVO.getNoOfLeaderCnt()));
				   overAllDtlsVO.setNotsubmitedCandidateTourPer(calculatePercantage(overAllDtlsVO.getNotSubmitedLeaserCnt(), overAllDtlsVO.getNoOfLeaderCnt())); 
				   //setting result to final result VO
				   resultVO.setOverAllDetailsVO(overAllDtlsVO);
			}
			/* Merge Sec And Org Sec Data */
			 if(designationMap != null && designationMap.size() > 0){
				     ToursBasicVO orgSecAndSecVO = new ToursBasicVO();
				     orgSecAndSecVO.setName("ORGANIZING SECRETARY/SECRETARY"); 
				     orgSecAndSecVO.setId(4l);
				     
					 ToursBasicVO orgSecVO = designationMap.get(4l);
					 ToursBasicVO secVO = designationMap.get(5l);
					 
					 if(orgSecVO != null){
						orgSecAndSecVO.setNoOfLeaderCnt(orgSecVO.getNoOfLeaderCnt());
						orgSecAndSecVO.setNotSubmitedLeaserCnt(orgSecVO.getNotSubmitedLeaserCnt());
						orgSecAndSecVO.setSubmitedLeaderCnt(orgSecVO.getSubmitedLeaderCnt());
						orgSecAndSecVO.setComplainceCnt(orgSecVO.getComplainceCnt());
						orgSecAndSecVO.setNonComplainceCnt(orgSecVO.getNonComplainceCnt());
						orgSecAndSecVO.getSubList().addAll(orgSecVO.getSubList());
						if(orgSecVO.getSubList3() != null && orgSecVO.getSubList3().size() > 0){
							orgSecAndSecVO.setSubList3(new CopyOnWriteArrayList<ToursBasicVO>(orgSecVO.getSubList3()));	
						}
					 }
				     if(secVO != null){
				    	 if(orgSecAndSecVO.getSubList() != null && orgSecAndSecVO.getSubList().size() > 0){
				    		 orgSecAndSecVO.getSubList().clear(); 
				    		 orgSecAndSecVO.getSubList().addAll(new ArrayList<ToursBasicVO>(secVO.getSubList()));
				    	 }else{
				    		 orgSecAndSecVO.getSubList().addAll(new ArrayList<ToursBasicVO>(secVO.getSubList()));	 
				    	 }
				        orgSecAndSecVO.setNoOfLeaderCnt(orgSecAndSecVO.getNoOfLeaderCnt()+secVO.getNoOfLeaderCnt());
						orgSecAndSecVO.setNotSubmitedLeaserCnt(orgSecAndSecVO.getNotSubmitedLeaserCnt()+secVO.getNotSubmitedLeaserCnt());
						orgSecAndSecVO.setSubmitedLeaderCnt(orgSecAndSecVO.getSubmitedLeaderCnt()+secVO.getSubmitedLeaderCnt());
						orgSecAndSecVO.setComplainceCnt(orgSecAndSecVO.getComplainceCnt()+secVO.getComplainceCnt());
						orgSecAndSecVO.setNonComplainceCnt(orgSecAndSecVO.getNonComplainceCnt()+secVO.getNonComplainceCnt());
						if(orgSecAndSecVO.getSubList3() != null && orgSecAndSecVO.getSubList3().size() > 0){
							for(ToursBasicVO VO:orgSecAndSecVO.getSubList3()){
								if(secVO.getSubList3() != null && secVO.getSubList3().size() > 0){
									ToursBasicVO categroyVO = getCategoryMatchVO(secVO.getSubList3(),VO.getIdStr());
									 if(categroyVO != null ){
										 VO.setComplainceCnt(VO.getComplainceCnt()+categroyVO.getComplainceCnt());
										 VO.setNonComplainceCnt(VO.getNonComplainceCnt()+categroyVO.getNonComplainceCnt());
										 VO.setSubmitedLeaderCnt(VO.getSubmitedLeaderCnt()+categroyVO.getSubmitedLeaderCnt());
									 }
								}
							}
						}
					 }
				     orgSecAndSecVO.setSubmitedCandidateTourPer(calculatePercantage(orgSecAndSecVO.getSubmitedLeaderCnt(),orgSecAndSecVO.getNoOfLeaderCnt()));
				     orgSecAndSecVO.setNotsubmitedCandidateTourPer(calculatePercantage(orgSecAndSecVO.getNotSubmitedLeaserCnt(), orgSecAndSecVO.getNoOfLeaderCnt()));
					 if(orgSecVO == null && secVO == null){
 					 }else{
 						designationMap.remove(5l);//removing Sec
 						designationMap.put(4l, orgSecAndSecVO); 
 					 }
 			 }
			  if(designationMap != null && designationMap.size() > 0){
				   resultVO.getSubList().addAll(new ArrayList<ToursBasicVO>(designationMap.values()));   
				   designationMap.clear();  
			   }
			   if(resultVO.getSubList() != null && resultVO.getSubList().size() > 0){
				   Collections.sort(resultVO.getSubList(), toursLeaderSorting);
			   }
	  }catch(Exception e){
		  LOG.error("Exception Occured in getToursBasicOverviewDtls() in CoreDashboardToursService  : ",e); 
	  }
	  return resultVO;
  }
  public void setCandidateIds(List<Object[]> objList,Set<Long> candiateIdSet){
	  try{
		  if(objList != null && objList.size() > 0){
			  for(Object[] param:objList){
				  candiateIdSet.add(commonMethodsUtilService.getLongValueForObject(param[2]));  
			  }
		  }
	  }catch(Exception e){
		  LOG.error("Exception Occured in setCandidateIds() in CoreDashboardToursService  : ",e);  
	  }
  }
  public void prepareCandiateWiseDtlsToCalculateComplainceCandiate(List<Object[]> rtrnObjList,Map<Long,Map<Long,ToursBasicVO>> candiateDtlsMap,Map<Long,List<ToursBasicVO>> categoryWiseMap,String type){
	  try{
		  if(rtrnObjList !=null && rtrnObjList.size() > 0){
			  for(Object[] param:rtrnObjList){
				  Map<Long,ToursBasicVO> candiateMap = candiateDtlsMap.get(commonMethodsUtilService.getLongValueForObject(param[0]));
				   if(candiateMap == null){
					   candiateMap = new HashMap<Long, ToursBasicVO>();  
					   candiateDtlsMap.put(commonMethodsUtilService.getLongValueForObject(param[0]), candiateMap);
				   }
				      ToursBasicVO candiateVO = candiateMap.get(commonMethodsUtilService.getLongValueForObject(param[2]));
				      if(candiateVO == null){
				    	  candiateVO = new ToursBasicVO(); 
				    	  candiateVO.setId(commonMethodsUtilService.getLongValueForObject(param[2]));
				    	  if(categoryWiseMap.get(commonMethodsUtilService.getLongValueForObject(param[0])) != null){
				    		  candiateVO.setSubList3(new CopyOnWriteArrayList<ToursBasicVO>(categoryWiseMap.get(commonMethodsUtilService.getLongValueForObject(param[0]))));  
				    	  }
				    	  candiateMap.put(candiateVO.getId(), candiateVO);
				      }
				        String idStr = commonMethodsUtilService.getStringValueForObject(param[3]);
						 if(type.equalsIgnoreCase("Govt")){
							 idStr = "0"+idStr;
						 }
						 Long tourDaysCnt = commonMethodsUtilService.getLongValueForObject(param[4]);
				         ToursBasicVO categoryVO = getCategoryMatchVO(candiateVO.getSubList3(),idStr);
						 if(categoryVO != null){
							  if(tourDaysCnt>=categoryVO.getTargetDays()){
								 categoryVO.setComplainceDays(tourDaysCnt);
								 Double complaincePer = calculatePercantage(categoryVO.getComplainceDays(),categoryVO.getTargetDays());
								 if(complaincePer > 100d){
									 categoryVO.setComplaincePer(100d);
								 }else{
									 categoryVO.setComplaincePer(complaincePer);	 
								 }
							 }else{
								 categoryVO.setComplainceDays(tourDaysCnt);
								 Double complaincePer = calculatePercantage(categoryVO.getComplainceDays(),categoryVO.getTargetDays());
								 categoryVO.setComplaincePer(complaincePer);	 
							}
			      }
			    }
			}
	  }catch(Exception e){
		  LOG.error("Exception Occured in prepareCandiateWiseDtlsToTakeComplainceCandiate() in CoreDashboardToursService  : ",e);  
	  }
  }
 public void setCategroyWiseTarget(List<Object[]> objLst,Map<Long,List<ToursBasicVO>> categoryWiseMap,String type,int noOfMonth){
	 try{
		 if(objLst != null && objLst.size() > 0){
			 for(Object[] param:objLst){
				 List<ToursBasicVO> categoryList = categoryWiseMap.get(commonMethodsUtilService.getLongValueForObject(param[0]));
				 	if(categoryList == null){
				 		categoryList = new ArrayList<ToursBasicVO>();
				 		categoryWiseMap.put(commonMethodsUtilService.getLongValueForObject(param[0]), categoryList);
				 	}
				 	ToursBasicVO categoryVO = new ToursBasicVO();
				 	 String idStr = commonMethodsUtilService.getStringValueForObject(param[2]);
					 if(type.equalsIgnoreCase("Govt")){
						 idStr = "0"+idStr;
					 }
				 	categoryVO.setIdStr(idStr);
				 	categoryVO.setName(commonMethodsUtilService.getStringValueForObject(param[3]));
				 	categoryVO.setTargetDays(commonMethodsUtilService.getLongValueForObject(param[4])*noOfMonth);
				 	categoryList.add(categoryVO);
			 }
		 }
	 }catch(Exception e){
		 LOG.error("Exception Occured in setCategroyWiseTarget() in CoreDashboardToursService  : ",e);
	 }
 }
 public void setDesignationWiseLeaders(List<Object[]> objLst, Map<Long,ToursBasicVO> designationMap,Map<Long,List<ToursBasicVO>> categoryWiseMap){
	 try{
		 if(objLst != null && objLst.size() > 0){
			 for(Object[] param:objLst){
				 ToursBasicVO designationVO = new ToursBasicVO();
				 designationVO.setId(commonMethodsUtilService.getLongValueForObject(param[0]));
				 designationVO.setName(commonMethodsUtilService.getStringValueForObject(param[1]));
				 designationVO.setNoOfLeaderCnt(commonMethodsUtilService.getLongValueForObject(param[2]));
				 designationVO.setNotSubmitedLeaserCnt(commonMethodsUtilService.getLongValueForObject(param[2]));
				 if(categoryWiseMap.get(designationVO.getId()) != null){
					 designationVO.setSubList3(new CopyOnWriteArrayList<ToursBasicVO>(categoryWiseMap.get(designationVO.getId())));	 
				 }
				 designationMap.put(designationVO.getId(), designationVO);
			 }
		 }
	 }catch(Exception e){
		 LOG.error("Exception Occured in setDesignationWiseLeaders() in CoreDashboardToursService  : ",e);	 
	 }
 }
 public void setTourSubmitteedLdrCnt(List<Object[]> objList, Map<Long,ToursBasicVO> designationMap){
	 try{
		 if(objList != null && objList.size() > 0){
			 for(Object[] param:objList){
				 ToursBasicVO designationVO = designationMap.get(commonMethodsUtilService.getLongValueForObject(param[0]));
				 	if(designationVO != null){
				 		designationVO.setSubmitedLeaderCnt(commonMethodsUtilService.getLongValueForObject(param[2]));
				 		designationVO.setNotSubmitedLeaserCnt(designationVO.getNoOfLeaderCnt()-designationVO.getSubmitedLeaderCnt());
				 	}
			 }
		 }
	 }catch(Exception e){
		 LOG.error("Exception Occured in setTourSubmitteedLdrCnt() in CoreDashboardToursService  : ",e);
	 }
 }
 public void setCategoryWiseTourSubmittedLeader(List<Object[]> objList,Map<Long,List<ToursBasicVO>> categoryWiseMap,String type){
	 try{
		 if(objList != null && objList.size() > 0){
			 for(Object[] param:objList){
				 Long designationId = commonMethodsUtilService.getLongValueForObject(param[0]);
				 String idStr = commonMethodsUtilService.getStringValueForObject(param[2]);
				 if(type.equalsIgnoreCase("Govt")){
					 idStr = "0"+idStr;
				 }
				 ToursBasicVO categoryVO = getCategoryMatchVO(categoryWiseMap.get(designationId),idStr);
				  if(categoryVO != null){
					  categoryVO.setSubmitedLeaderCnt(commonMethodsUtilService.getLongValueForObject(param[3]));
				  }
			 }
		 }
	 }catch(Exception e){
		 LOG.error("Exception Occured in setCategoryWiseTourSubmittedLeader() in CoreDashboardToursService  : ",e);
	 }
 }

 public void setCategroyWiseComplainceCandidateCnt(List<Object[]> objList,Map<Long,ToursBasicVO> desigMap,String type){
	 try{
		 if(objList != null && objList.size() > 0){
			 for(Object[] param:objList){
				 Long designationId = commonMethodsUtilService.getLongValueForObject(param[0]);
				 Long candiateId = commonMethodsUtilService.getLongValueForObject(param[2]);
				 String idStr = commonMethodsUtilService.getStringValueForObject(param[3]);
				 if(type.equalsIgnoreCase("Govt")){
					 idStr = "0"+idStr;
				 }
				 Long tourDaysCnt = commonMethodsUtilService.getLongValueForObject(param[4]);
				 ToursBasicVO designationVO = desigMap.get(designationId);
				 if(designationVO != null){
					 ToursBasicVO categoryVO = getCategoryMatchVO(designationVO.getSubList3(),idStr);
					 if(categoryVO != null){
						 if(tourDaysCnt>=categoryVO.getTargetDays()){
							 categoryVO.setComplainceCnt(categoryVO.getComplainceCnt()+1);
							 categoryVO.setComplainceDays(tourDaysCnt);
							 Double complaincePer = calculatePercantage(categoryVO.getComplainceDays(),categoryVO.getTargetDays());
							 if(complaincePer > 100d){
								 categoryVO.setComplaincePer(100d);
							 }else{
								 categoryVO.setComplaincePer(complaincePer);	 
							 }
							 if(categoryVO.getComplaincandidateIdsSet() == null){
								 categoryVO.setComplaincandidateIdsSet(new HashSet<Long>()); 
							 }
							 categoryVO.getComplaincandidateIdsSet().add(candiateId);
						 }else{
							 categoryVO.setNonComplainceCnt(categoryVO.getNonComplainceCnt()+1); 
							 categoryVO.setComplainceDays(tourDaysCnt);
							 Double complaincePer = calculatePercantage(categoryVO.getComplainceDays(),categoryVO.getTargetDays());
							 categoryVO.setComplaincePer(complaincePer);	 
							if(categoryVO.getNoNComplaincandidateIdsSet() == null){
								categoryVO.setNoNComplaincandidateIdsSet(new HashSet<Long>(0));
							}
							categoryVO.getNoNComplaincandidateIdsSet().add(candiateId);
						 }
					 }
				 }
			 }
		 }
	 }catch(Exception e){
		 LOG.error("Exception Occured in setCategroyWiseComplainceCandidateCnt() in CoreDashboardToursService  : ",e);	 
	 }
 }
 public ToursBasicVO getCategoryMatchVO(List<ToursBasicVO> categoryList,String id){
	 try{
		 if(categoryList == null || categoryList.size() == 0)
			 return null;
		 for(ToursBasicVO vo:categoryList){
			 if(vo.getIdStr().equalsIgnoreCase(id.trim())){
				 return vo;
			 }
		 }
	 }catch(Exception e){
		 LOG.error("Exception Occured in getCategoryMatchVO() in CoreDashboardToursService  : ",e);	 
	 }
	 return null;
 }
 public List<List<ToursBasicVO>> getDesignationWiseMembersDtls(Long stateId,String fromDateStr,String toDateStr,Long activityMemberId,Long userTypeId){
	 
	 List<List<ToursBasicVO>> resultList = new ArrayList<List<ToursBasicVO>>(0);
	 Set<Long> locationValues = new HashSet<Long>(0);
	 Map<Long,Map<Long,List<ToursBasicVO>>> candiateTargetMap = new HashMap<Long, Map<Long,List<ToursBasicVO>>>();
	 Map<Long,Map<Long,ToursBasicVO>> candiateDtlsMap = new HashMap<Long, Map<Long,ToursBasicVO>>(0);
	 Long locationAccessLevelId = 0l;
	 Date fromDate=null;
	 Date toDate = null;
	 try{
		  List<Date>  datesList =getDates(fromDateStr, toDateStr, new SimpleDateFormat("dd/MM/yyyy"));
		  fromDate=datesList.get(0);
		  toDate=datesList.get(1);
		   List<Object[]> rtrnUsrAccssLvlIdAndVlusObjLst=activityMemberAccessLevelDAO.getLocationLevelAndValuesByActivityMembersId(activityMemberId);
			if(rtrnUsrAccssLvlIdAndVlusObjLst != null && rtrnUsrAccssLvlIdAndVlusObjLst.size() > 0){
				 locationAccessLevelId=(Long) rtrnUsrAccssLvlIdAndVlusObjLst.get(0)[0];
				 for(Object[] param:rtrnUsrAccssLvlIdAndVlusObjLst){
					 locationValues.add(commonMethodsUtilService.getLongValueForObject(param[1]));
				 }
		   }
		  int noOfMonth = getMonthsDifference(fromDate,toDate);	
		  List<Object[]> rtrnTargetCntObjLst = selfAppraisalDesignationTargetDAO.getCandiateWiseTargetCnt(fromDate, toDate,"Category");	
		  setCandidateTarget(rtrnTargetCntObjLst,candiateTargetMap,"Category",noOfMonth);
		  List<Object[]> rtrnGovtObjLst = selfAppraisalDesignationTargetDAO.getCandiateWiseTargetCnt(fromDate, toDate,"Govt");	
		  setCandidateTarget(rtrnGovtObjLst,candiateTargetMap,"Govt",noOfMonth);
		  
		  List<Object[]> rtrnComplainceObjLst = selfAppraisalCandidateDayTourDAO.getLeaderComplainceCntCategoryWise(fromDate, toDate,"Category",null,null);
		  setCandiateCategoryWiseComplaince(rtrnComplainceObjLst,candiateTargetMap,"Category");
		  List<Object[]> rtrnGovtComplainceObjLst = selfAppraisalCandidateDayTourDAO.getLeaderComplainceCntCategoryWise(fromDate, toDate,"Govt",null,null);
		  setCandiateCategoryWiseComplaince(rtrnGovtComplainceObjLst,candiateTargetMap,"govt");
		
		  List<Object[]> rtrnMemberDtls = selfAppraisalCandidateLocationNewDAO.getDesignationWiseAllCandiateBasedOnUserAccessLevel(stateId, locationAccessLevelId, locationValues,userTypeId,null);
		  setCandidateDtls(rtrnMemberDtls,candiateDtlsMap,candiateTargetMap);
		  
		  //Calculating OverAll percentage
		  calculatteOverAllPercentage(candiateDtlsMap);
		
		 if(candiateDtlsMap!=null && candiateDtlsMap.size()>0){
		        Map<Long,ToursBasicVO> orgSecAndSecMap = new LinkedHashMap<Long,ToursBasicVO>();
		        Map<Long,ToursBasicVO>  secreteriesMap = null;
		        if(candiateDtlsMap.containsKey(5l)){
		          secreteriesMap = candiateDtlsMap.get(5l);
		          orgSecAndSecMap.putAll(secreteriesMap);
		          //remove secreteries from Map
		          candiateDtlsMap.remove(5l); 
		        }
		        if(candiateDtlsMap.containsKey(4l)){
		          orgSecAndSecMap.putAll(candiateDtlsMap.get(4l));
		        }
		        if(orgSecAndSecMap!=null && orgSecAndSecMap.size()>0){
		        	candiateDtlsMap.put(4l, orgSecAndSecMap); 
		        }
		  }
		 if(candiateDtlsMap != null && candiateDtlsMap.size() > 0){
			  for(Entry<Long, Map<Long, ToursBasicVO>> entry:candiateDtlsMap.entrySet()){
			   Map<Long,ToursBasicVO> designationTypeMap = entry.getValue();
			   resultList.add(new ArrayList<ToursBasicVO>(designationTypeMap.values()));
			  }
		 }
		 if(resultList != null && resultList.size() > 0){
				for(List<ToursBasicVO> memberList:resultList){
					Collections.sort(memberList, tourMemberComplaincePercDesc);
				}
			}
	 }catch(Exception e){
		 LOG.error("Exception Occured in getDesignationWiseMembersDtls() in CoreDashboardToursService  : ",e); 
	 }
	 return resultList;
 }
 public static Comparator<ToursBasicVO> tourMemberComplaincePercDesc = new Comparator<ToursBasicVO>() {
		public int compare(ToursBasicVO member2, ToursBasicVO member1) {
		Double perc2 = member2.getComplaincePer();
		Double perc1 = member1.getComplaincePer();
		//descending order of percantages.
		 return perc1.compareTo(perc2);
		}
}; 
 public void setCandidateTarget(List<Object[]> objList,Map<Long,Map<Long,List<ToursBasicVO>>> candiateTargetMap,String type,int noOfMonth){
	 try{
		 if(objList != null && objList.size() > 0){
			 for(Object[] param:objList){
				 Map<Long,List<ToursBasicVO>> candiateMap = candiateTargetMap.get(commonMethodsUtilService.getLongValueForObject(param[0]));
				 if(candiateMap == null ){
					 candiateMap = new HashMap<Long, List<ToursBasicVO>>();
					 candiateTargetMap.put(commonMethodsUtilService.getLongValueForObject(param[0]), candiateMap);
				 }
				  List<ToursBasicVO> categoryList = candiateMap.get(commonMethodsUtilService.getLongValueForObject(param[2]));
				  if(categoryList == null){
					  categoryList = new ArrayList<ToursBasicVO>();
					  candiateMap.put(commonMethodsUtilService.getLongValueForObject(param[2]), categoryList);
				  }
				     String idStr = commonMethodsUtilService.getStringValueForObject(param[3]);
					 if(type.equalsIgnoreCase("Govt")){
						 idStr = "0"+idStr;
					 }
				    ToursBasicVO categoryVO = new ToursBasicVO();
				    categoryVO.setIdStr(idStr);
				    categoryVO.setName(commonMethodsUtilService.getStringValueForObject(param[4]));
				    categoryVO.setTargetDays(commonMethodsUtilService.getLongValueForObject(param[5])*noOfMonth);
				    categoryList.add(categoryVO);
			 }
		 }
	 }catch(Exception e){
		 LOG.error("Exception Occured in setCandidateTarget() in CoreDashboardToursService  : ",e); 
	 }
 }
 public void setCandiateCategoryWiseComplaince(List<Object[]> objList, Map<Long,Map<Long,List<ToursBasicVO>>> candiateTargetMap,String type){
	 try{
		 if(objList != null && objList.size() > 0){
			 for(Object[] param:objList){
				 Long designationId = commonMethodsUtilService.getLongValueForObject(param[0]);
				 Long candidateId = commonMethodsUtilService.getLongValueForObject(param[2]);
				  String categoryIdStr = commonMethodsUtilService.getStringValueForObject(param[3]);
					 if(type.equalsIgnoreCase("Govt")){
						 categoryIdStr = "0"+categoryIdStr;
					 }
				 Long tourDaysCnt = commonMethodsUtilService.getLongValueForObject(param[4]);
				 Map<Long,List<ToursBasicVO>> candiateMap = candiateTargetMap.get(designationId);
				 if(candiateMap != null && candiateMap.size() > 0){
					 List<ToursBasicVO> categoryList = candiateMap.get(candidateId);
					 ToursBasicVO categoryVO = getCategoryMatchVO(categoryList, categoryIdStr);
					  if(categoryVO != null){
						  categoryVO.setComplainceDays(tourDaysCnt);
						  Double percentate = calculatePercantage(categoryVO.getComplainceDays(),categoryVO.getTargetDays());
						   if(percentate > 100d){
							   categoryVO.setComplaincePer(100d);  
						   }else{
							   categoryVO.setComplaincePer(calculatePercantage(categoryVO.getComplainceDays(),categoryVO.getTargetDays()));   
						   }
					  }
				 }
				 
			 }
		 }
	 }catch(Exception e){
		 LOG.error("Exception Occured in setCandiateCategoryWiseComplaince() in CoreDashboardToursService  : ",e);	 
	 }
 }
 public void setCandidateDtls(List<Object[]> rtrnMemberDtls,Map<Long,Map<Long,ToursBasicVO>> candiateDtlsMap,Map<Long,Map<Long,List<ToursBasicVO>>> candiateTargetMap){
	 try{
		 if(rtrnMemberDtls != null && rtrnMemberDtls.size() > 0){
			 for(Object[] param:rtrnMemberDtls){
				 Long designationId = commonMethodsUtilService.getLongValueForObject(param[0]);
				 Map<Long,ToursBasicVO> candidateMap = candiateDtlsMap.get(designationId);
				 if(candidateMap == null ){
					 candidateMap = new HashMap<Long,ToursBasicVO>();
					 candiateDtlsMap.put(designationId, candidateMap);
				 }
				 ToursBasicVO candiateVO = candidateMap.get(commonMethodsUtilService.getLongValueForObject(param[2]));//candiateId
				 if(candiateVO == null ){
					 candiateVO = new ToursBasicVO();
					 candiateVO.setDesignationId(commonMethodsUtilService.getLongValueForObject(param[0]));
					 candiateVO.setDesignation(commonMethodsUtilService.getStringValueForObject(param[1]));
					 candiateVO.setId(commonMethodsUtilService.getLongValueForObject(param[2]));
					 candiateVO.setName(commonMethodsUtilService.getStringValueForObject(param[3]));
					 candiateVO.setLocationScopeId(commonMethodsUtilService.getLongValueForObject(param[4]));
					 if(candiateTargetMap.get(candiateVO.getDesignationId()) != null && candiateTargetMap.get(candiateVO.getDesignationId()).size() > 0){
						 candiateVO.setSubList3(new CopyOnWriteArrayList<ToursBasicVO>(candiateTargetMap.get(candiateVO.getDesignationId()).get(candiateVO.getId())));
					 }
					 candidateMap.put(candiateVO.getId(), candiateVO);
				 }
				 candiateVO.getLocationSet().add(commonMethodsUtilService.getLongValueForObject(param[5]));//location value
			 }
		 }
	 }catch(Exception e){
		 LOG.error("Exception Occured in setCandidateDtls() in CoreDashboardToursService  : ",e);
	 }
 }
 public List<ToursBasicVO> getDesignationWiseAverageTourPerformanceDtls(Long stateId,String fromDateStr,String toDateStr,Long activityMemberId,Long userTypeId,List<Long> designationIds,String isFilterApply,String filterType,Double ownDistValue,Double ownCnsttuncyValue,Double ichargeDistrictValue,Double incharegeConstituencyValue,Double govtWorkValue,Double complainceValue){
	 
	 List<ToursBasicVO> resultList = new ArrayList<ToursBasicVO>();
	 Map<Long,Map<Long,List<ToursBasicVO>>> candidateTargetMap = new HashMap<Long, Map<Long,List<ToursBasicVO>>>(0);
	 Map<Long,Map<Long,ToursBasicVO>> memberDtlsMap = new HashMap<Long, Map<Long,ToursBasicVO>>(0);
	 Map<Long,String> designationIdAndNameMap = new HashMap<Long, String>(0);
	 Set<Long> locationValues = new HashSet<Long>(0);
	 Long locationAccessLevelId = 0l;
	 Date fromDate=null;
	 Date toDate = null;
	 try{
		 List<Date>  datesList =getDates(fromDateStr, toDateStr, new SimpleDateFormat("dd/MM/yyyy"));
		  fromDate=datesList.get(0);
		  toDate=datesList.get(1);
		   List<Object[]> rtrnUsrAccssLvlIdAndVlusObjLst=activityMemberAccessLevelDAO.getLocationLevelAndValuesByActivityMembersId(activityMemberId);
			if(rtrnUsrAccssLvlIdAndVlusObjLst != null && rtrnUsrAccssLvlIdAndVlusObjLst.size() > 0){
				 locationAccessLevelId=(Long) rtrnUsrAccssLvlIdAndVlusObjLst.get(0)[0];
				 for(Object[] param:rtrnUsrAccssLvlIdAndVlusObjLst){
					 locationValues.add(commonMethodsUtilService.getLongValueForObject(param[1]));
				 }
		   }
		  int noOfMonth = getMonthsDifference(fromDate,toDate);	
		  List<Object[]> rtrnTargetObjList = selfAppraisalDesignationTargetDAO.getCandiateAndCategoryWiseTargetCnt(fromDate, toDate,"Category",null);
		  setCandiateWiseTarget(rtrnTargetObjList,candidateTargetMap,"Category",noOfMonth);
		  
		  List<Object[]> rtrnGovtObjList = selfAppraisalDesignationTargetDAO.getCandiateAndCategoryWiseTargetCnt(fromDate, toDate,"Govt",null);
		  setCandiateWiseTarget(rtrnGovtObjList,candidateTargetMap,"Govt",noOfMonth);
		
		  List<Object[]> rtrnDaysToursObjLst = selfAppraisalCandidateDayTourDAO.getLeaderComplainceCntCategoryWise(fromDate, toDate, "Category",null,null);
		  setComplainceDtls(rtrnDaysToursObjLst,candidateTargetMap,"Category");
		  List<Object[]> rtrnGovtDaysToursObjLst = selfAppraisalCandidateDayTourDAO.getLeaderComplainceCntCategoryWise(fromDate, toDate, "Govt",null,null);
		  setComplainceDtls(rtrnGovtDaysToursObjLst,candidateTargetMap,"Govt");
		  
		  List<Object[]> rtrnMemberDtlsObjLst = selfAppraisalCandidateDayTourDAO.getTourSubmitteedDesignationWiseAllCandiateBasedOnUserAccessLevel(stateId, locationAccessLevelId, locationValues, userTypeId,fromDate,toDate,designationIds);
		  setTourSubmitteedMemberDtls(rtrnMemberDtlsObjLst,memberDtlsMap,candidateTargetMap,designationIdAndNameMap);
		
		  //This Block is for searching
		  if(memberDtlsMap != null && memberDtlsMap.size() > 0){
			  
			  for(Entry<Long,Map<Long,ToursBasicVO>> designationEntry:memberDtlsMap.entrySet()){
				  
				  if(designationEntry.getValue() != null && designationEntry.getValue().size() > 0){
					  
					  for(Entry<Long,ToursBasicVO> entry:designationEntry.getValue().entrySet()){
						  
						  ToursBasicVO candiateVO = entry.getValue();
						 		if(candiateVO.getSubList3() != null && candiateVO.getSubList3().size() > 0){
						 	 		for(ToursBasicVO categoryVO:candiateVO.getSubList3()){
						 	 			String categoryId = categoryVO.getIdStr();
						 	 			if(categoryId.equalsIgnoreCase("1")){//Incharge District
						 	 				candiateVO.setInchargeDistrictTrDays(categoryVO.getTargetDays());
						 	 				candiateVO.setInchargeDistrictComplainceDays(categoryVO.getComplainceDays());
						 	 			}else if(categoryId.equalsIgnoreCase("3")){//Incharge Constituency
						 	 				candiateVO.setInchargeConstituencyTrDays(categoryVO.getTargetDays());
						 	 				candiateVO.setInchargeConstituencyComplainceDays(categoryVO.getComplainceDays());
						 	 			}else if(categoryId.equalsIgnoreCase("2")){//ownDistrict
						 	 				candiateVO.setOwnDistrictTrDays(categoryVO.getTargetDays());
						 	 				candiateVO.setOwnDistrictComplainceDays(categoryVO.getComplainceDays());
						 	 			}else if(categoryId.equalsIgnoreCase("4")){//own Constituency
						 	 			   candiateVO.setOwnContituencyTrDays(categoryVO.getTargetDays());
						 	 			   candiateVO.setOwnConstituencyComplainceDays(categoryVO.getComplainceDays());
						 	 			}else if(categoryId.equalsIgnoreCase("02")){//govtWork
						 	 				candiateVO.setGovtWorkTrDays(categoryVO.getTargetDays());
						 	 				candiateVO.setGovtWorkComplainceDays(categoryVO.getComplainceDays());
						 	 			}
							 		}
						  		}
						  }
					  }
				  }
			  }
		  
		  //calculating percentage
		  if(memberDtlsMap != null && memberDtlsMap.size() > 0){
			  
			  for(Entry<Long,Map<Long,ToursBasicVO>> entry:memberDtlsMap.entrySet()){
				  
				  if(entry.getValue() != null && entry.getValue().size() > 0){
					  
					  for(Entry<Long,ToursBasicVO> candiateEntry:entry.getValue().entrySet()){
						  
						  Double incharegDesper = calculatePercantage(candiateEntry.getValue().getInchargeDistrictComplainceDays(),candiateEntry.getValue().getInchargeDistrictTrDays());
						  if(incharegDesper > 100d){
							  candiateEntry.getValue().setInchargeDistrictComplaincePer(100d);  
						  }else{
							  candiateEntry.getValue().setInchargeDistrictComplaincePer(incharegDesper);  
						  }
						  Double inchareConsper = calculatePercantage(candiateEntry.getValue().getInchargeConstituencyComplainceDays(),candiateEntry.getValue().getInchargeConstituencyTrDays());
						   if(inchareConsper > 100d){
							   candiateEntry.getValue().setInchargeConstituencyComplaincePer(100d); 
						   }else{
							   candiateEntry.getValue().setInchargeConstituencyComplaincePer(inchareConsper);   
						   }
						  Double ownDistrper = calculatePercantage(candiateEntry.getValue().getOwnDistrictComplainceDays(),candiateEntry.getValue().getOwnDistrictTrDays());
						   if(ownDistrper > 100d){
							   candiateEntry.getValue().setOwnDistrictComplaincePer(ownDistrper);  
						   }else{
							   candiateEntry.getValue().setOwnDistrictComplaincePer(ownDistrper);   
						   }
						   Double ownConsPer = calculatePercantage(candiateEntry.getValue().getOwnConstituencyComplainceDays(), candiateEntry.getValue().getOwnContituencyTrDays());
						    if(ownConsPer > 100d){
						    	candiateEntry.getValue().setOwnContituencyComplaincePer(100d);  	
						    }else{
						    	candiateEntry.getValue().setOwnContituencyComplaincePer(ownConsPer);
						    }
						   Double govtWorkper = calculatePercantage(candiateEntry.getValue().getGovtWorkComplainceDays(),candiateEntry.getValue().getGovtWorkTrDays());
						   if(govtWorkper > 100d){
							   candiateEntry.getValue().setGovtWorkComplaincePer(100d);
						   }else{
							   candiateEntry.getValue().setGovtWorkComplaincePer(govtWorkper);   
						   }
						  
						  if(candiateEntry.getValue().getSubList3() != null && candiateEntry.getValue().getSubList3().size() > 0){
							  for(ToursBasicVO categoryVO:candiateEntry.getValue().getSubList3()){
								  Double per = calculatePercantage(categoryVO.getComplainceDays(),categoryVO.getTargetDays());
								  if(per>100d){
									  categoryVO.setComplaincePer(100d);  
								  }else{
									  categoryVO.setComplaincePer(calculatePercantage(categoryVO.getComplainceDays(),categoryVO.getTargetDays()));  
								  }
							  }
						  }
						  
					  }
				  }
			  }
		  }
		  
		  //Calculating OverAll percentage
		  calculatteOverAllPercentage(memberDtlsMap);
		  
		  //merge secretary and organization secretary
		  if(memberDtlsMap!=null && memberDtlsMap.size()>0){
		        Map<Long,ToursBasicVO> orgSecAndSecMap = new ConcurrentHashMap<Long,ToursBasicVO>();
		        Map<Long,ToursBasicVO>  secreteriesMap = null;
		        if(memberDtlsMap.containsKey(5l)){
		          secreteriesMap = memberDtlsMap.get(5l);
		          orgSecAndSecMap.putAll(secreteriesMap);
		          //remove secreteries from Map
		          memberDtlsMap.remove(5l); 
		        }
		        if(memberDtlsMap.containsKey(4l)){
		          orgSecAndSecMap.putAll(memberDtlsMap.get(4l));
		        }
		        if(orgSecAndSecMap!=null && orgSecAndSecMap.size()>0){
		        	memberDtlsMap.put(4l, orgSecAndSecMap); 
		        }
		  }
		  //filter data based on filter type criteria.if filter has applied.
		  if(isFilterApply != null && isFilterApply.equalsIgnoreCase("Yes")){
			  if(filterType != null && !filterType.equalsIgnoreCase("All")){
			    filterRequiredData(memberDtlsMap,filterType,ownDistValue,ownCnsttuncyValue,ichargeDistrictValue,incharegeConstituencyValue,govtWorkValue,complainceValue);  
			  }
		  }
		  //pushing in final list
		  if(memberDtlsMap != null && memberDtlsMap.size() > 0){
			  for(Entry<Long,Map<Long,ToursBasicVO>> entry:memberDtlsMap.entrySet()){
				  ToursBasicVO designationVO = new ToursBasicVO();
				  designationVO.setId(entry.getKey());
				  if(entry.getKey() == 4l){
					  designationVO.setName("ORGANIZING SECRETARY/SECRETARY");	  
				  }else{
					  designationVO.setName(designationIdAndNameMap.get(entry.getKey()));  
				  }
				  List<ToursBasicVO> memberList = new ArrayList<ToursBasicVO>(entry.getValue().values());
				  Collections.sort(memberList, tourMemberComplaincePercDesc);
				  designationVO.setSubList3(memberList);
				  resultList.add(designationVO);
			  }
		  }
		  memberDtlsMap.clear();
	 }catch(Exception e){
		 LOG.error("Exception Occured in getDesignationWiseAverageTourPerformanceDtls() in CoreDashboardToursService  : ",e);
	 }
	 return resultList;
 }
 public void calculatteOverAllPercentage(Map<Long,Map<Long,ToursBasicVO>> memberDtlsMap){
	 try{
		 if(memberDtlsMap != null && memberDtlsMap.size() > 0){
			  for(Entry<Long,Map<Long,ToursBasicVO>> designationEntry:memberDtlsMap.entrySet()){
				  if(designationEntry != null && designationEntry.getValue().size() >0){
					  for(Entry<Long,ToursBasicVO> candiateEntry:designationEntry.getValue().entrySet()){
						  List<ToursBasicVO> categoryList = candiateEntry.getValue().getSubList3();
						   if(categoryList != null && categoryList.size() > 0){
							   
							   Double totalPer =0.0d;
							   for(ToursBasicVO VO:categoryList){
								   totalPer = totalPer+VO.getComplaincePer(); 
							   }
							   Integer totalCount =0;
							   if(categoryList != null && categoryList.size() > 0){
								    totalCount = categoryList.size() * 100;   
							   }
							   
							   Double percentage = calculatePercantageBasedOnDouble(totalPer,totalCount.doubleValue());
							   if(percentage > 100d){
								   candiateEntry.getValue().setComplaincePer(100d);
							   }else{
								   candiateEntry.getValue().setComplaincePer(percentage);  
							   }
						   }
					  }
				  }
			  }
		  }	 
	 }catch(Exception e){
		 LOG.error("Exception Occured in calculatteOverAllPercentage() in CoreDashboardToursService  : ",e);	 
	 }
 }
 public void setCandiateWiseTarget(List<Object[]> objList,Map<Long,Map<Long,List<ToursBasicVO>>> candidateTargetMap,String type,int noOfMonth){
	 try{
		 if(objList != null && objList.size() > 0){
			 for(Object[] param:objList){
				 Map<Long,List<ToursBasicVO>> candidateMap = candidateTargetMap.get(commonMethodsUtilService.getLongValueForObject(param[0]));
				 if(candidateMap == null){
					 candidateMap = new HashMap<Long, List<ToursBasicVO>>(0);
					 candidateTargetMap.put(commonMethodsUtilService.getLongValueForObject(param[0]), candidateMap);
				 }
				 List<ToursBasicVO> categoryList = candidateMap.get(commonMethodsUtilService.getLongValueForObject(param[2]));
				 if(categoryList == null ){
					 categoryList = new CopyOnWriteArrayList<ToursBasicVO>();
					 candidateMap.put(commonMethodsUtilService.getLongValueForObject(param[2]), categoryList);
				 }
				 String ids = commonMethodsUtilService.getStringValueForObject(param[3]);
				 if(type.equalsIgnoreCase("Govt")){
					 ids = "0"+ids;
				 }
				 ToursBasicVO categoryVO = new ToursBasicVO();
				 categoryVO.setIdStr(ids);
				 categoryVO.setName(commonMethodsUtilService.getStringValueForObject(param[4]));
				 categoryVO.setTargetDays(commonMethodsUtilService.getLongValueForObject(param[5])*noOfMonth);
				 categoryList.add(categoryVO);
			 }
		 }
	 }catch(Exception e){
		 LOG.error("Exception Occured in setCandiateWiseTarget() in CoreDashboardToursService  : ",e); 
	 }
 }
 public void setComplainceDtls(List<Object[]> objList,Map<Long,Map<Long,List<ToursBasicVO>>> candidateTargetMap,String type){
	 try{
		 if(objList != null && objList.size() > 0){
			 for(Object[] param:objList){
				 Long designationId = commonMethodsUtilService.getLongValueForObject(param[0]);
				 Long candidateId = commonMethodsUtilService.getLongValueForObject(param[2]);
				 String ids = commonMethodsUtilService.getStringValueForObject(param[3]);
				 if(type.equalsIgnoreCase("Govt")){
					 ids = "0"+ids;
				 }
				 Long noOfTours = commonMethodsUtilService.getLongValueForObject(param[4]);
				 if(candidateTargetMap.get(designationId) != null && candidateTargetMap.get(designationId).size() > 0){
					 ToursBasicVO categoryVO = getCategoryMatchVO(candidateTargetMap.get(designationId).get(candidateId), ids);
					 if(categoryVO != null){
						 categoryVO.setComplainceDays(noOfTours);
					 }
				 }
			 }
		 }
	 }catch(Exception e){
		 LOG.error("Exception Occured in setComplainceDtls() in CoreDashboardToursService  : ",e);
	 }
 }
 public void setTourSubmitteedMemberDtls(List<Object[]> memberObjList,Map<Long,Map<Long,ToursBasicVO>> memberDtlsMap,Map<Long,Map<Long,List<ToursBasicVO>>> candidateTargetMap,Map<Long,String> designationIdAndNameMap){
	 try{
		 if(memberObjList != null && memberObjList.size() > 0){
			 for(Object[] param:memberObjList){
				 Long designationId = commonMethodsUtilService.getLongValueForObject(param[0]);
				 Long candiateId = commonMethodsUtilService.getLongValueForObject(param[2]);
				 Map<Long,ToursBasicVO> candiateMap = memberDtlsMap.get(designationId);
				 	if(candiateMap == null ){
				 		candiateMap = new ConcurrentHashMap<Long, ToursBasicVO>();
				 		designationIdAndNameMap.put(designationId,commonMethodsUtilService.getStringValueForObject(param[1]));
				 		memberDtlsMap.put(designationId, candiateMap);
				 	}
				 	ToursBasicVO candiateVO = new ToursBasicVO();
				 	candiateVO.setId(candiateId);
				 	candiateVO.setName(commonMethodsUtilService.getStringValueForObject(param[3]));
				 	candiateVO.setDesignationId(commonMethodsUtilService.getLongValueForObject(param[0]));
				 	candiateVO.setDesignation(commonMethodsUtilService.getStringValueForObject(param[1]));
				 	if(candidateTargetMap.get(designationId) != null && candidateTargetMap.get(designationId).size() > 0){
				 		if(candidateTargetMap.get(designationId).get(candiateId) != null && candidateTargetMap.get(designationId).get(candiateId).size() > 0){
				 			candiateVO.setSubList3(new CopyOnWriteArrayList<ToursBasicVO>(candidateTargetMap.get(designationId).get(candiateId)));	
				 		}
				 	}
				 	candiateMap.put(candiateVO.getId(), candiateVO);
			 }
		 }
	 }catch(Exception e){
		 LOG.error("Exception Occured in setComplainceDtls() in CoreDashboardToursService  : ",e); 
	 }
 }
 public void filterRequiredData(Map<Long,Map<Long,ToursBasicVO>> memberDtlsMap,String filterType,Double ownDistValue,Double ownCnsttuncyValue,Double ichargeDistrictValue,Double incharegeConstituencyValue,Double govtWorkValue,Double complainceValue){
	 try{
		 if(memberDtlsMap != null && memberDtlsMap.size() > 0){
			 for(Entry<Long,Map<Long,ToursBasicVO>> entry:memberDtlsMap.entrySet()){
				 if(entry.getValue() != null && entry.getValue().size() > 0){
					 for(Entry<Long,ToursBasicVO> candiateEntry:entry.getValue().entrySet()){
						  if(filterType.equalsIgnoreCase("complaince")){
							  if(candiateEntry.getValue().getComplaincePer()<100d){
								  entry.getValue().remove(candiateEntry.getKey());  
							  }
						  }else if(filterType.equalsIgnoreCase("nonComplaince")){
							  if(candiateEntry.getValue().getComplaincePer()>=100d){
								  entry.getValue().remove(candiateEntry.getKey());  
							  } 
						  }else if(complainceValue.doubleValue() > 0.0d){
							  if(candiateEntry.getValue().getComplaincePer() < complainceValue){
								  entry.getValue().remove(candiateEntry.getKey());   
							  }
						  }else if(ownDistValue > 0.0d && ichargeDistrictValue> 0.0d && govtWorkValue> 0.0d){
								  if((candiateEntry.getValue().getOwnDistrictComplaincePer() < ownDistValue || candiateEntry.getValue().getInchargeDistrictComplaincePer() < ichargeDistrictValue || candiateEntry.getValue().getGovtWorkComplaincePer() < govtWorkValue )){
									  entry.getValue().remove(candiateEntry.getKey());  
								  }
						 }else if(ownDistValue> 0.0d && ichargeDistrictValue> 0.0d ){
							  if((candiateEntry.getValue().getOwnDistrictComplaincePer()< ownDistValue || candiateEntry.getValue().getInchargeDistrictComplaincePer() < ichargeDistrictValue)){
								  entry.getValue().remove(candiateEntry.getKey()); 
							  }
						  }else if(ownDistValue > 0.0d  && govtWorkValue > 0.0d){
							  if((candiateEntry.getValue().getOwnDistrictComplaincePer() < ownDistValue || candiateEntry.getValue().getGovtWorkComplaincePer()< govtWorkValue)){
								  entry.getValue().remove(candiateEntry.getKey());	 
								 }
						  }else if(ichargeDistrictValue > 0.0d && govtWorkValue > 0.0d){
							  if((candiateEntry.getValue().getInchargeDistrictComplaincePer() < ichargeDistrictValue || candiateEntry.getValue().getGovtWorkComplaincePer()< govtWorkValue)){
								 entry.getValue().remove(candiateEntry.getKey());  
							  }
						  }else if(govtWorkValue > 0.0d ){
							  if(candiateEntry.getValue().getGovtWorkComplaincePer() < govtWorkValue){
								  entry.getValue().remove(candiateEntry.getKey()); 
							  }
						  }else if(ownDistValue >0.0d ){
							  if(candiateEntry.getValue().getOwnDistrictComplaincePer() < ownDistValue){
								  entry.getValue().remove(candiateEntry.getKey()); 
							  }
						  }else if(incharegeConstituencyValue > 0.0d){
							  if(candiateEntry.getValue().getInchargeConstituencyComplaincePer() < incharegeConstituencyValue){
								  entry.getValue().remove(candiateEntry.getKey());  
							  }
						  }else if(ownCnsttuncyValue > 0.0d){
							  if(candiateEntry.getValue().getOwnContituencyComplaincePer() < ownCnsttuncyValue){
								  entry.getValue().remove(candiateEntry.getKey());  
							  }
						  }else if(ichargeDistrictValue > 0.0d){
							  if(candiateEntry.getValue().getInchargeDistrictComplaincePer() < ichargeDistrictValue){
								  entry.getValue().remove(candiateEntry.getKey());  
							  }
						  }
					 }
				 }
			 }
		 }
	 }catch(Exception e){
		 LOG.error("Exception Occured in filterRequiredData() in CoreDashboardToursService  : ",e); 
	 }
 }
 public ToursBasicVO getIndividualPersonTourDetails(String fromDateStr,String toDateStr,Long selfAppraisalCandidateId){
	 ToursBasicVO resultVO = new ToursBasicVO();
	 Map<Long,List<ToursBasicVO>> candidateCategoryMap = new HashMap<Long, List<ToursBasicVO>>(0);
	 List<ToursBasicVO> dateWiseTourDtlsList = new ArrayList<ToursBasicVO>(0);
	 Date fromDate=null;
	 Date toDate = null;
	 try{
		   List<Date>  datesList =getDates(fromDateStr, toDateStr, new SimpleDateFormat("dd/MM/yyyy"));
		   fromDate=datesList.get(0);
		   toDate=datesList.get(1); 
		   int noOfMonth = getMonthsDifference(fromDate,toDate);	
		   List<Object[]> rtrnCategoryObjLst = selfAppraisalDesignationTargetDAO.getCandiateAndCategoryWiseTargetCnt(fromDate, toDate, "Category", selfAppraisalCandidateId);
		   setCandiateTourCategoryDtls(rtrnCategoryObjLst,candidateCategoryMap,"Category",noOfMonth);
		   List<Object[]> rtrnGovtgoryObjLst = selfAppraisalDesignationTargetDAO.getCandiateAndCategoryWiseTargetCnt(fromDate, toDate, "Govt", selfAppraisalCandidateId);
		   setCandiateTourCategoryDtls(rtrnGovtgoryObjLst,candidateCategoryMap,"Govt",noOfMonth);
		   List<Object[]> rtrnObjLst = selfAppraisalCandidateDayTourDAO.getLeaderComplainceCntCategoryWise(fromDate, toDate, "Category", selfAppraisalCandidateId,null);
		   setCandiateComplainceDetails(rtrnObjLst,candidateCategoryMap,"Category");
		   List<Object[]> rtrnGovtObjLst = selfAppraisalCandidateDayTourDAO.getLeaderComplainceCntCategoryWise(fromDate, toDate, "Govt", selfAppraisalCandidateId,null);
		   setCandiateComplainceDetails(rtrnGovtObjLst,candidateCategoryMap,"Govt");
		   //Getting Tour Candidate Details Day Wise
		   List<Object[]> rtrnDateWiseTourDtlsObjLst = selfAppraisalCandidateDayTourDAO.getDateWiseTourSubmittedDetails(fromDate, toDate, selfAppraisalCandidateId);
		   setDateWiseTourDtls(rtrnDateWiseTourDtlsObjLst,dateWiseTourDtlsList);
		   
		    Double totalPer =0.0d;
		    if(candidateCategoryMap != null && candidateCategoryMap.size() > 0){
		    	for(Entry<Long,List<ToursBasicVO>> entry:candidateCategoryMap.entrySet()){
		    		 if(entry.getValue() != null && entry.getValue().size() > 0){
		    			 for(ToursBasicVO VO:entry.getValue()){
		   				  VO.setYetToTourCnt(VO.getTargetDays()-VO.getComplainceDays());
							  Double percentage = calculatePercantage(VO.getComplainceDays(), VO.getTargetDays());
							  if(percentage > 100d){
								  VO.setComplaincePer(100d);   
							  }else{
								  VO.setComplaincePer(percentage);  
							  }
							  totalPer = totalPer+VO.getComplaincePer();
		    			 }
		    			Integer totalCount =0;
		  			   if(entry.getValue() != null && entry.getValue().size() > 0){
		  				    totalCount = entry.getValue().size() * 100;   
		  			   }
		  			   
		  			   Double percentage = calculatePercantageBasedOnDouble(totalPer,totalCount.doubleValue());
		  			   if(percentage > 100d){
		  				 resultVO.setComplaincePer(100d);
		  			   }else{
		  				 resultVO.setComplaincePer(percentage);  
		  			   }
		    		 }
		    	}
		    }
		    if(candidateCategoryMap != null && candidateCategoryMap.size() > 0){
		    	for(Entry<Long,List<ToursBasicVO>> entry:candidateCategoryMap.entrySet()){
		    		resultVO.getSubList().addAll(entry.getValue());	
		    	}
		    }
		    resultVO.getSubList2().addAll(dateWiseTourDtlsList);
		    
	 }catch(Exception e){
		 LOG.error("Exception Occured in getIndividualPersonTourDetails() in CoreDashboardToursService  : ",e);	 
	 }
	 return resultVO;
 }
  public void setCandiateTourCategoryDtls(List<Object[]> objList,Map<Long,List<ToursBasicVO>> candidateCategoryMap,String type,int noOfMonth){
	  try{
		  if(objList != null && objList.size() > 0){
			  for(Object[] param:objList){
				  List<ToursBasicVO> categoryList = candidateCategoryMap.get(commonMethodsUtilService.getLongValueForObject(param[2]));
				  if(categoryList == null){
					  categoryList = new CopyOnWriteArrayList<ToursBasicVO>(); 
					  candidateCategoryMap.put(commonMethodsUtilService.getLongValueForObject(param[2]), categoryList);
				  }
				  ToursBasicVO VO = new ToursBasicVO();
				    String ids = commonMethodsUtilService.getStringValueForObject(param[3]);
					 if(type.equalsIgnoreCase("Govt")){
						 ids = "0"+ids;
					 }
				  VO.setIdStr(ids);  
				  VO.setName(commonMethodsUtilService.getStringValueForObject(param[4]));
				  VO.setTargetDays(commonMethodsUtilService.getLongValueForObject(param[5])*noOfMonth);
				  VO.setYetToTourCnt(VO.getTargetDays());
				  categoryList.add(VO);
			  }
		  }
	  }catch(Exception e){
		  LOG.error("Exception Occured in setCandiateTourCategoryDtls() in CoreDashboardToursService  : ",e);  
	  }
  }
  public void setCandiateComplainceDetails(List<Object[]> objList,Map<Long,List<ToursBasicVO>> candidateCategoryMap,String type){
	  try{
		  if(objList != null && objList.size() > 0){
			  for(Object[] param:objList){
				   String categoryIds = commonMethodsUtilService.getStringValueForObject(param[3]);
				   if(type.equalsIgnoreCase("Govt")){
					   categoryIds = "0"+categoryIds;  
				   }
				  ToursBasicVO categoryVO = getCategoryMatchVO(candidateCategoryMap.get(commonMethodsUtilService.getLongValueForObject(param[2])),categoryIds);
				   if(categoryVO != null){
					   categoryVO.setComplainceDays(commonMethodsUtilService.getLongValueForObject(param[4]));
				   }
			  }
		  }
	  }catch(Exception e){
		  LOG.error("Exception Occured in setCandiateComplainceDetails() in CoreDashboardToursService  : ",e);  
	  }
  }
 public void setDateWiseTourDtls(List<Object[]> objList,List<ToursBasicVO> dateWiseTourDtlsList){
	 SimpleDateFormat sdf = new SimpleDateFormat("dd-MMM-yyyy");
	  try{
		  if(objList != null && objList.size() > 0){
			  for(Object[] param:objList){
				  ToursBasicVO VO = new ToursBasicVO();
				  if(param[0] != null){
					  VO.setTourDate(sdf.format(param[0]));  
				  }
				  VO.setTourCategoryId(commonMethodsUtilService.getLongValueForObject(param[1]));
				  VO.setTourCategory(commonMethodsUtilService.getStringValueForObject(param[2]));
				  VO.setTourTypeId(commonMethodsUtilService.getLongValueForObject(param[3]));
				  VO.setTourType(commonMethodsUtilService.getStringValueForObject(param[4]));
				  VO.setLocationId(commonMethodsUtilService.getLongValueForObject(param[5]));
				  VO.setLocationName(commonMethodsUtilService.getStringValueForObject(param[6]));
				  if(commonMethodsUtilService.getLongValueForObject(param[13])==6l){//For MP We are Setting parliamentConstituency name
				   VO.setConstituencyId(commonMethodsUtilService.getLongValueForObject(param[10]));
				   VO.setConstituencyName(commonMethodsUtilService.getStringValueForObject(param[11]));
				  }else{
				   VO.setConstituencyId(commonMethodsUtilService.getLongValueForObject(param[7]));
				   VO.setConstituencyName(commonMethodsUtilService.getStringValueForObject(param[8]));
				  }
				  VO.setId(commonMethodsUtilService.getLongValueForObject(param[9]));
				  VO.setComment(commonMethodsUtilService.getStringValueForObject(param[12]));
				  dateWiseTourDtlsList.add(VO);
			  }
		  }
	  }catch(Exception e){
		  LOG.error("Exception Occured in setDateWiseTourDtls() in CoreDashboardToursService  : ",e);
	  }
  }
 public List<ToursBasicVO> getTourLeaderDtlsBasedOnSelectionType(Long stateId,String fromDateStr,String toDateStr,Long activityMemberId,Long userTypeId,List<Long> designationIds,String filterType){
	 List<ToursBasicVO> resultList = new ArrayList<ToursBasicVO>();
	 Map<Long,Map<Long,List<ToursBasicVO>>> candidateTargetMap = new HashMap<Long, Map<Long,List<ToursBasicVO>>>(0);
	 Map<Long,Map<Long,ToursBasicVO>> memberDtlsMap = new HashMap<Long, Map<Long,ToursBasicVO>>(0);
	 Map<Long,String> designationIdAndNameMap = new HashMap<Long, String>(0);
	 
	 Set<Long> locationValues = new HashSet<Long>(0);
	 Long locationAccessLevelId = 0l;
	 Date fromDate=null;
	 Date toDate = null;
	 try{
		   List<Date>  datesList =getDates(fromDateStr, toDateStr, new SimpleDateFormat("dd/MM/yyyy"));
		  fromDate=datesList.get(0);
		  toDate=datesList.get(1);
		   List<Object[]> rtrnUsrAccssLvlIdAndVlusObjLst=activityMemberAccessLevelDAO.getLocationLevelAndValuesByActivityMembersId(activityMemberId);
			if(rtrnUsrAccssLvlIdAndVlusObjLst != null && rtrnUsrAccssLvlIdAndVlusObjLst.size() > 0){
				 locationAccessLevelId=(Long) rtrnUsrAccssLvlIdAndVlusObjLst.get(0)[0];
				 for(Object[] param:rtrnUsrAccssLvlIdAndVlusObjLst){
					 locationValues.add(commonMethodsUtilService.getLongValueForObject(param[1]));
				 }
		    }
			 int noOfMonth = getMonthsDifference(fromDate,toDate);	
			 List<Object[]> rtrnTargetObjList = selfAppraisalDesignationTargetDAO.getCandiateAndCategoryWiseTargetCnt(fromDate, toDate,"Category",null);
			  setCandiateWiseTarget(rtrnTargetObjList,candidateTargetMap,"Category",noOfMonth);
			  
			  List<Object[]> rtrnGovtObjList = selfAppraisalDesignationTargetDAO.getCandiateAndCategoryWiseTargetCnt(fromDate, toDate,"Govt",null);
			  setCandiateWiseTarget(rtrnGovtObjList,candidateTargetMap,"Govt",noOfMonth);
			 
			  if(filterType != null && !filterType.equalsIgnoreCase("notSubmitteed")){
				  List<Object[]> rtrnDaysToursObjLst = selfAppraisalCandidateDayTourDAO.getLeaderComplainceCntCategoryWise(fromDate, toDate, "Category",null,null);
				  setComplainceDtls(rtrnDaysToursObjLst,candidateTargetMap,"Category");
				  List<Object[]> rtrnGovtDaysToursObjLst = selfAppraisalCandidateDayTourDAO.getLeaderComplainceCntCategoryWise(fromDate, toDate, "Govt",null,null);
				  setComplainceDtls(rtrnGovtDaysToursObjLst,candidateTargetMap,"Govt");
			
			  }
			  
			  List<Object[]> rtrnMemberDtlsObjLst = null;
			  if(filterType != null && filterType.equalsIgnoreCase("all")){
				 rtrnMemberDtlsObjLst = selfAppraisalCandidateLocationNewDAO.getDesignationWiseAllCandiateBasedOnUserAccessLevel(stateId, locationAccessLevelId, locationValues,0l, designationIds);
			  }else if(filterType != null && filterType.equalsIgnoreCase("notSubmitteed")){
			   List<Object[]>  rtrnTotalLdrs = selfAppraisalCandidateLocationNewDAO.getDesignationWiseAllCandiateBasedOnUserAccessLevel(stateId, locationAccessLevelId, locationValues, 0l, designationIds);
			   rtrnMemberDtlsObjLst = selfAppraisalCandidateDayTourDAO.getTourSubmitteedDesignationWiseAllCandiateBasedOnUserAccessLevel(stateId, locationAccessLevelId, locationValues, 0l,fromDate,toDate,designationIds);
			   rtrnMemberDtlsObjLst = getNotSubmittedLeadrs(rtrnTotalLdrs,rtrnMemberDtlsObjLst); 
			  }else{
			   rtrnMemberDtlsObjLst = selfAppraisalCandidateDayTourDAO.getTourSubmitteedDesignationWiseAllCandiateBasedOnUserAccessLevel(stateId, locationAccessLevelId, locationValues, 0l,fromDate,toDate,designationIds);	  
			  }
			  setTourSubmitteedMemberDtls(rtrnMemberDtlsObjLst,memberDtlsMap,candidateTargetMap,designationIdAndNameMap);
			
			  //Calculating Category wise percentage
			  if(memberDtlsMap != null && memberDtlsMap.size() > 0){
				  
				  for(Entry<Long,Map<Long,ToursBasicVO>> entry:memberDtlsMap.entrySet()){
					  
					  if(entry.getValue() != null && entry.getValue().size() > 0){
						  
						  for(Entry<Long,ToursBasicVO> candiateEntry:entry.getValue().entrySet()){
							  
							  if(candiateEntry.getValue().getSubList3() != null && candiateEntry.getValue().getSubList3().size() > 0){
								  for(ToursBasicVO categoryVO:candiateEntry.getValue().getSubList3()){
									  Double per = calculatePercantage(categoryVO.getComplainceDays(),categoryVO.getTargetDays());
									   if(per> 100d){
										   categoryVO.setComplaincePer(100d); 
									   }else{
										   categoryVO.setComplaincePer(per);   
									   }
								  }
							  }
							  
						  }
					  }
				  }
			  }
			  
			  //calculating overall percentage
			  calculatteOverAllPercentage(memberDtlsMap);
			  //merge secretary and organization secretary
			  if(memberDtlsMap!=null && memberDtlsMap.size()>0){
			        Map<Long,ToursBasicVO> orgSecAndSecMap = new ConcurrentHashMap<Long,ToursBasicVO>();
			        Map<Long,ToursBasicVO>  secreteriesMap = null;
			        if(memberDtlsMap.containsKey(5l)){
			          secreteriesMap = memberDtlsMap.get(5l);
			          orgSecAndSecMap.putAll(secreteriesMap);
			          //remove secreteries from Map
			          memberDtlsMap.remove(5l); 
			        }
			        if(memberDtlsMap.containsKey(4l)){
			          orgSecAndSecMap.putAll(memberDtlsMap.get(4l));
			        }
			        if(orgSecAndSecMap!=null && orgSecAndSecMap.size()>0){
			        	memberDtlsMap.put(4l, orgSecAndSecMap); 
			        }
			  }
			  
			  //Filter Compliance Leader
			  if(filterType != null && filterType.equalsIgnoreCase("Complaince")){
				  if(memberDtlsMap != null && memberDtlsMap.size() > 0){
					  for(Entry<Long,Map<Long,ToursBasicVO>> entry:memberDtlsMap.entrySet()){
						  
							 if(entry.getValue() != null && entry.getValue().size() > 0){
								 
								 for(Entry<Long,ToursBasicVO> candiateEntry:entry.getValue().entrySet()){
									 
									  if(candiateEntry.getValue().getComplaincePer()<100d){
										  
										  entry.getValue().remove(candiateEntry.getKey());  
									  }
									 
								 }
				          }
					}  
				  }
			 }
			  //Preparing final list 
			  if(memberDtlsMap != null && memberDtlsMap.size() > 0){
				  for(Entry<Long,Map<Long,ToursBasicVO>> entry:memberDtlsMap.entrySet()){
					  ToursBasicVO designationVO = new ToursBasicVO();
					  designationVO.setId(entry.getKey());
					  if(entry.getKey() == 4l){
						  designationVO.setName("ORGANIZING SECRETARY/SECRETARY");	  
					  }else{
						  designationVO.setName(designationIdAndNameMap.get(entry.getKey()));  
					  }
					  List<ToursBasicVO> memberList = new ArrayList<ToursBasicVO>(entry.getValue().values());
					  Collections.sort(memberList, tourMemberComplaincePercDesc);
					  designationVO.setSubList3(memberList);
					  resultList.add(designationVO);
				  }
			  }
			  memberDtlsMap.clear();
		 }catch(Exception e){
			 LOG.error("Exception Occured in getTourLeaderDtlsBasedOnSelectionType() in CoreDashboardToursService  : ",e);
		 }
	 return resultList;
 }
 public List<Object[]> getNotSubmittedLeadrs(List<Object[]> totalLeadrObjList,List<Object[]> subMittedLdrsObjLst){
	 List<Object[]> notSubMittedMembersObjLst = new ArrayList<Object[]>();
	 Set<Long> subMitteddCandiateList = new HashSet<Long>(0);
	 try{
		 if(subMittedLdrsObjLst != null && subMittedLdrsObjLst.size() > 0){
			 for(Object[] param:subMittedLdrsObjLst){
				 subMitteddCandiateList.add(commonMethodsUtilService.getLongValueForObject(param[2]));
			 }
		 }
		 if(totalLeadrObjList != null && totalLeadrObjList.size() > 0){
			 for(Object[] param:totalLeadrObjList){
				 Long cadreId = commonMethodsUtilService.getLongValueForObject(param[2]);
				 if(!subMitteddCandiateList.contains(cadreId)){
					 notSubMittedMembersObjLst.add(param); 
				 }
			 }
		 }
	 }catch(Exception e){
		 LOG.error("Exception Occured in getNotSubmittedLeadrs() in CoreDashboardToursService  : ",e);	 
	 }
	 return notSubMittedMembersObjLst;
 }
}
