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
  
  //New Tours Service Based on Screen Change
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
  
  public ToursBasicVO getToursBasicOverviewDtls(Long stateId,String fromDateStr,String toDateStr,Long activityMemberId,Long userTypeId){
	  
	  ToursBasicVO resultVO = new ToursBasicVO();
	  Map<Long,List<ToursBasicVO>> categoryWiseMap = new HashMap<Long, List<ToursBasicVO>>(0);
	  Map<Long,ToursBasicVO> designationMap = new HashMap<Long, ToursBasicVO>();
	  Map<Long,Long> targetMap = new HashMap<Long, Long>(0);
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
			 
		 List<Object[]> rtrnobjTargetLst = selfAppraisalDesignationTargetDAO.getDesignationWiseTargetCnt(fromDate, toDate,"Category");
		 setTargetCntToMap(rtrnobjTargetLst,targetMap);
		
		 List<Object[]> rtrnTourTypeObjLst = selfAppraisalDesignationTargetDAO.getDesignationWiseTargetCnt(fromDate, toDate,"Govt");
		 if(rtrnTourTypeObjLst != null && rtrnTourTypeObjLst.size() > 0){
			 for(Object[] param:rtrnobjTargetLst){
				 Long target = targetMap.get(commonMethodsUtilService.getLongValueForObject(param[0]));
				 if(target != null){
					 targetMap.put(commonMethodsUtilService.getLongValueForObject(param[0]),target+commonMethodsUtilService.getLongValueForObject(param[2])); 
				 }
			 }
		 }
		 List<Object[]> rtrnobjCtgryWseTargetLst = selfAppraisalDesignationTargetDAO.getTourCategoryWiseTargetCnt(fromDate, toDate,"Category");
		 setCategroyWiseTarget(rtrnobjCtgryWseTargetLst,categoryWiseMap);
		 
		 List<Object[]> rtrnobjGovtTargetLst = selfAppraisalDesignationTargetDAO.getTourCategoryWiseTargetCnt(fromDate, toDate,"Govt");
		  if(rtrnobjGovtTargetLst != null && rtrnobjGovtTargetLst.size() > 0){
			  for(Object[] param:rtrnobjGovtTargetLst){
				  List<ToursBasicVO> categoryTrgtLst = categoryWiseMap.get(commonMethodsUtilService.getLongValueForObject(param[0]));
				  	if(categoryTrgtLst != null ){
				  		ToursBasicVO tourTypeVO = new ToursBasicVO();
				  		tourTypeVO.setId(commonMethodsUtilService.getLongValueForObject(param[2]));
				  		tourTypeVO.setName(commonMethodsUtilService.getStringValueForObject(param[3]));
				  		tourTypeVO.setTargetDays(commonMethodsUtilService.getLongValueForObject(param[4]));
				  		categoryTrgtLst.add(tourTypeVO);
				  	}
			  }
		  }
		 
		 List<Object[]> rtrnDsgntnWseLderObjLst = selfAppraisalCandidateLocationNewDAO.getNoOfLdrsCntDesignationByBasedOnUserAccessLevel(locationAccessLevelId, locationValues, stateId, userTypeId); 
		 setDesignationWiseLeaders(rtrnDsgntnWseLderObjLst,designationMap,targetMap,categoryWiseMap);
		 List<Object[]> rtrnSubmittedLdrObjLst = selfAppraisalCandidateDayTourDAO.getToursSubmittedLeaderCntDesignationBy(fromDate, toDate);
		 setTourSubmitteedLdrCnt(rtrnSubmittedLdrObjLst,designationMap);
		 List<Object[]> rtrnComplainceObjLst = selfAppraisalCandidateDayTourDAO.getLeaderComplainceCnt(fromDate, toDate);
		 setComplainceCandiateCnt(rtrnComplainceObjLst,designationMap);
		 List<Object[]> rtrnCategoryWiseComplainceOblLst = selfAppraisalCandidateDayTourDAO.getLeaderComplainceCntCategoryWise(fromDate, toDate, "Category");
		 setCategroyWiseComplainceCandidateCnt(rtrnCategoryWiseComplainceOblLst,designationMap);
		 List<Object[]> rtrnGovtWorkWiseComplainceOblLst = selfAppraisalCandidateDayTourDAO.getLeaderComplainceCntCategoryWise(fromDate, toDate, "Govt");
		 setCategroyWiseComplainceCandidateCnt(rtrnGovtWorkWiseComplainceOblLst,designationMap);
		 //merge All Own Type Location data
		 if(designationMap != null && designationMap.size() > 0){
			 
			 for(Entry<Long,ToursBasicVO> entry:designationMap.entrySet()){
				 
				 List<ToursBasicVO> categoryList = entry.getValue().getSubList3();
				 
				  if(categoryList != null && categoryList.size() > 0){
					  
					  ToursBasicVO categoryVO = new ToursBasicVO();
					  
					  boolean isOwnTypeLocation = false;
					  
					  for(ToursBasicVO VO:categoryList){
						  
						  String[] categryArr= VO.getName().split(" ");
						  
						  if(categryArr != null && categryArr.length > 0){
							  
							  if(categryArr[0].toString().equalsIgnoreCase("Own")){
								  categoryVO.setId(VO.getId());
								  if(categoryVO.getName() == null){
									  categoryVO.setName(categryArr[0]+" "+categryArr[1]);
								  }else{
									  categoryVO.setName(categoryVO.getName()+"/"+categryArr[1]);  
								  }
								  categoryVO.setComplainceCnt(categoryVO.getComplainceCnt()+VO.getComplainceCnt());
								  if(categoryVO.getComplaincandidateIdsSet() == null){
									  categoryVO.setComplaincandidateIdsSet(new HashSet<Long>(0));
								  }
								  if(VO.getComplaincandidateIdsSet() != null ){
									  categoryVO.getComplaincandidateIdsSet().addAll(VO.getComplaincandidateIdsSet());	  
								  }
								  categoryVO.setNonComplainceCnt(categoryVO.getNonComplainceCnt()+VO.getNonComplainceCnt());
								  if(categoryVO.getNoNComplaincandidateIdsSet() == null){
									  categoryVO.setNoNComplaincandidateIdsSet(new HashSet<Long>(0));
								  }
								  if(VO.getNoNComplaincandidateIdsSet() != null){
									  categoryVO.getNoNComplaincandidateIdsSet().addAll(VO.getNoNComplaincandidateIdsSet());
								  }
								  categoryVO.setSubmitedLeaderCnt(VO.getSubmitedLeaderCnt());
								  categoryList.remove(VO);
								  isOwnTypeLocation = true;
							  }  
						  }
					  }
					  if(isOwnTypeLocation){
						  entry.getValue().getSubList3().add(0, categoryVO);  
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
						if(orgSecVO.getSubList3() != null && orgSecVO.getSubList3().size() > 0){
							orgSecAndSecVO.setSubList3(new CopyOnWriteArrayList<ToursBasicVO>(orgSecVO.getSubList3()));	
						}
					 }
				     if(secVO != null){
						orgSecAndSecVO.setNoOfLeaderCnt(orgSecAndSecVO.getNoOfLeaderCnt()+secVO.getNoOfLeaderCnt());
						orgSecAndSecVO.setNotSubmitedLeaserCnt(orgSecAndSecVO.getNotSubmitedLeaserCnt()+secVO.getNotSubmitedLeaserCnt());
						orgSecAndSecVO.setSubmitedLeaderCnt(orgSecAndSecVO.getSubmitedLeaderCnt()+secVO.getSubmitedLeaderCnt());
						orgSecAndSecVO.setComplainceCnt(orgSecAndSecVO.getComplainceCnt()+secVO.getComplainceCnt());
						orgSecAndSecVO.setNonComplainceCnt(orgSecAndSecVO.getNonComplainceCnt()+secVO.getNonComplainceCnt());
						if(orgSecAndSecVO.getSubList3() != null && orgSecAndSecVO.getSubList3().size() > 0){
							for(ToursBasicVO VO:orgSecAndSecVO.getSubList3()){
								if(secVO.getSubList3() != null && secVO.getSubList3().size() > 0){
									ToursBasicVO categroyVO = getCategoryMatchVO(secVO.getSubList3(),VO.getId());
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
  public void setTargetCntToMap(List<Object[]> objList,Map<Long,Long> targetMap){
	  try{
		  if(objList != null && objList.size() > 0){
			  for(Object[] param:objList){
				  targetMap.put(commonMethodsUtilService.getLongValueForObject(param[0]),commonMethodsUtilService.getLongValueForObject(param[2]));
			  }
		  }
	  }catch(Exception e){
		  LOG.error("Exception Occured in setTargetCntToMap() in CoreDashboardToursService  : ",e); 
	  }
  }
 public void setCategroyWiseTarget(List<Object[]> objLst,Map<Long,List<ToursBasicVO>> categoryWiseMap){
	 try{
		 if(objLst != null && objLst.size() > 0){
			 for(Object[] param:objLst){
				 List<ToursBasicVO> categoryList = categoryWiseMap.get(commonMethodsUtilService.getLongValueForObject(param[0]));
				 	if(categoryList == null){
				 		categoryList = new ArrayList<ToursBasicVO>();
				 		categoryWiseMap.put(commonMethodsUtilService.getLongValueForObject(param[0]), categoryList);
				 	}
				 	ToursBasicVO categoryVO = new ToursBasicVO();
				 	categoryVO.setId(commonMethodsUtilService.getLongValueForObject(param[2]));
				 	categoryVO.setName(commonMethodsUtilService.getStringValueForObject(param[3]));
				 	categoryVO.setTargetDays(commonMethodsUtilService.getLongValueForObject(param[4]));
				 	categoryList.add(categoryVO);
				 
			 }
		 }
	 }catch(Exception e){
		 LOG.error("Exception Occured in setCategroyWiseTarget() in CoreDashboardToursService  : ",e);
	 }
 }
 public void setDesignationWiseLeaders(List<Object[]> objLst, Map<Long,ToursBasicVO> designationMap,Map<Long,Long> targetMap,Map<Long,List<ToursBasicVO>> categoryWiseMap){
	 try{
		 if(objLst != null && objLst.size() > 0){
			 for(Object[] param:objLst){
				 ToursBasicVO designationVO = new ToursBasicVO();
				 designationVO.setId(commonMethodsUtilService.getLongValueForObject(param[0]));
				 designationVO.setName(commonMethodsUtilService.getStringValueForObject(param[1]));
				 designationVO.setNoOfLeaderCnt(commonMethodsUtilService.getLongValueForObject(param[2]));
				 designationVO.setNotSubmitedLeaserCnt(commonMethodsUtilService.getLongValueForObject(param[2]));
				 if(targetMap.get(designationVO.getId()) != null){
					 designationVO.setTargetDays(targetMap.get(designationVO.getId()));	 
				 }
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
				 		if(designationVO.getSubList3() != null && designationVO.getSubList3().size() > 0){
				 			for(ToursBasicVO vo:designationVO.getSubList3()){
				 				vo.setSubmitedLeaderCnt(commonMethodsUtilService.getLongValueForObject(param[2]));
				 			}
				 		}
				 	}
			 }
		 }
	 }catch(Exception e){
		 LOG.error("Exception Occured in setTourSubmitteedLdrCnt() in CoreDashboardToursService  : ",e);
	 }
 }
 public void setComplainceCandiateCnt(List<Object[]> objLst,Map<Long,ToursBasicVO> designationMap){
	 try{
		 if(objLst != null && objLst.size() > 0){
			 for(Object[] param:objLst){
				 Long designationId = commonMethodsUtilService.getLongValueForObject(param[0]);
				 Long candiateId = commonMethodsUtilService.getLongValueForObject(param[2]);
				 Long tourDaysCnt = commonMethodsUtilService.getLongValueForObject(param[3]);
				 ToursBasicVO designatioVO = designationMap.get(designationId);
				 if(designatioVO.getTargetDays().equals(tourDaysCnt)){
					 designatioVO.setComplainceCnt(designatioVO.getComplainceCnt()+1);
					 if(designatioVO.getComplaincandidateIdsSet() == null){
						 designatioVO.setComplaincandidateIdsSet(new HashSet<Long>()); 
					 }
					 designatioVO.getComplaincandidateIdsSet().add(candiateId);
				 }else{
					designatioVO.setNonComplainceCnt(designatioVO.getNonComplainceCnt()+1); 
					if(designatioVO.getNoNComplaincandidateIdsSet() == null){
						designatioVO.setNoNComplaincandidateIdsSet(new HashSet<Long>(0));
					}
					designatioVO.getNoNComplaincandidateIdsSet().add(candiateId);
				 }
			 }
		 }
	 }catch(Exception e){
		 LOG.error("Exception Occured in setComplainceCandiateCnt() in CoreDashboardToursService  : ",e);
	 }
 }
 public void setCategroyWiseComplainceCandidateCnt(List<Object[]> objList,Map<Long,ToursBasicVO> desigMap){
	 try{
		 if(objList != null && objList.size() > 0){
			 for(Object[] param:objList){
				 Long designationId = commonMethodsUtilService.getLongValueForObject(param[0]);
				 Long candiateId = commonMethodsUtilService.getLongValueForObject(param[2]);
				 Long id = commonMethodsUtilService.getLongValueForObject(param[3]);
				 Long tourDaysCnt = commonMethodsUtilService.getLongValueForObject(param[4]);
				 ToursBasicVO designationVO = desigMap.get(designationId);
				 if(designationVO != null){
					 ToursBasicVO categoryVO = getCategoryMatchVO(designationVO.getSubList3(),id);
					 if(categoryVO != null){
						 if(categoryVO.getTargetDays().equals(tourDaysCnt)){
							 categoryVO.setComplainceCnt(categoryVO.getComplainceCnt()+1);
							 if(categoryVO.getComplaincandidateIdsSet() == null){
								 categoryVO.setComplaincandidateIdsSet(new HashSet<Long>()); 
							 }
							 categoryVO.getComplaincandidateIdsSet().add(candiateId);
						 }else{
							 categoryVO.setNonComplainceCnt(categoryVO.getNonComplainceCnt()+1); 
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
 public ToursBasicVO getCategoryMatchVO(List<ToursBasicVO> categoryList,Long id){
	 try{
		 if(categoryList == null || categoryList.size() == 0)
			 return null;
		 for(ToursBasicVO vo:categoryList){
			 if(vo.getId().equals(id)){
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
	 Map<Long,Map<Long,Long>> candiateTargetMap = new HashMap<Long, Map<Long,Long>>(0);
	 Map<Long,Map<Long,Long>> candiateComplainceMap = new HashMap<Long, Map<Long,Long>>(0); 
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
		  List<Object[]> rtrnTargetCntObjLst = selfAppraisalDesignationTargetDAO.getCandiateWiseTargetCnt(fromDate, toDate,"Category");	
		  setCandidateTarget(rtrnTargetCntObjLst,candiateTargetMap);
		  List<Object[]> rtrnGovtObjLst = selfAppraisalDesignationTargetDAO.getCandiateWiseTargetCnt(fromDate, toDate,"Govt");	
		   if(rtrnGovtObjLst != null && rtrnGovtObjLst.size() > 0){
			   for(Object[] param:rtrnGovtObjLst){
				   Map<Long,Long> candiateMap = candiateTargetMap.get(commonMethodsUtilService.getLongValueForObject(param[0]));
				   if(candiateMap != null && candiateMap.size() > 0){
					   Long candiateTarget = candiateMap.get(commonMethodsUtilService.getLongValueForObject(param[2]));
					    if(candiateTarget != null){
					    	candiateMap.put(commonMethodsUtilService.getLongValueForObject(param[2]), candiateTarget+commonMethodsUtilService.getLongValueForObject(param[3]));
					    }
				   }
			   }
		   }
		  List<Object[]> rtrnComplainceObjLst = selfAppraisalCandidateDayTourDAO.getLeaderComplainceCnt(fromDate, toDate);
		  setCandidateTarget(rtrnComplainceObjLst,candiateComplainceMap);
		  List<Object[]> rtrnGovtComplainceObjLst = selfAppraisalCandidateDayTourDAO.getLeaderComplainceCntCategoryWise(fromDate, toDate,"Govt");
		  if(rtrnGovtComplainceObjLst != null && rtrnGovtComplainceObjLst.size() > 0){
			  for(Object[] param:rtrnGovtComplainceObjLst){
				  if(candiateComplainceMap.get(commonMethodsUtilService.getLongValueForObject(param[0])) != null && candiateComplainceMap.get(commonMethodsUtilService.getLongValueForObject(param[0])).size() > 0){
				    Map<Long,Long> candiateMap = candiateComplainceMap.get(commonMethodsUtilService.getLongValueForObject(param[0]));
				    if(candiateMap != null && candiateMap.size() > 0){
				    	Long targetCnt = candiateMap.get(commonMethodsUtilService.getLongValueForObject(param[2]));
				    	if(targetCnt != null){
				    		candiateMap.put(commonMethodsUtilService.getLongValueForObject(param[0]),targetCnt+commonMethodsUtilService.getLongValueForObject(param[4]));
				    	}
				    }
				  }
			  }
		  }
		  List<Object[]> rtrnMemberDtls = selfAppraisalCandidateLocationNewDAO.getDesignationWiseAllCandiateBasedOnUserAccessLevel(stateId, locationAccessLevelId, locationValues,userTypeId);
		  setCandidateDtls(rtrnMemberDtls,candiateDtlsMap,candiateTargetMap,candiateComplainceMap);
		  
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
 public void setCandidateTarget(List<Object[]> objList,Map<Long,Map<Long,Long>> candiateTargetMap){
	 try{
		 if(objList != null && objList.size() > 0){
			 for(Object[] param:objList){
				 Map<Long,Long> candiateMap = candiateTargetMap.get(commonMethodsUtilService.getLongValueForObject(param[0]));
				 if(candiateMap == null ){
					 candiateMap = new HashMap<Long, Long>();
					 candiateTargetMap.put(commonMethodsUtilService.getLongValueForObject(param[0]), candiateMap);
				 }
				 candiateMap.put(commonMethodsUtilService.getLongValueForObject(param[2]),commonMethodsUtilService.getLongValueForObject(param[3]));
				 
			 }
		 }
	 }catch(Exception e){
		 LOG.error("Exception Occured in setCandidateTarget() in CoreDashboardToursService  : ",e); 
	 }
 }
 public void setCandidateDtls(List<Object[]> rtrnMemberDtls,Map<Long,Map<Long,ToursBasicVO>> candiateDtlsMap,Map<Long,Map<Long,Long>> candiateTargetMap,Map<Long,Map<Long,Long>> candiateComplainceMap){
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
					 if(candiateTargetMap.get(candiateVO.getDesignationId()) != null ){
						 if(candiateTargetMap.get(candiateVO.getDesignationId()).get(candiateVO.getId()) != null){
							 candiateVO.setTargetDays(candiateTargetMap.get(candiateVO.getDesignationId()).get(candiateVO.getId()));	 
						 }
					 }
					 if(candiateComplainceMap.get(candiateVO.getDesignationId()) != null){
						 if(candiateComplainceMap.get(candiateVO.getDesignationId()).get(candiateVO.getId()) != null){
							 candiateVO.setComplainceDays(candiateComplainceMap.get(candiateVO.getDesignationId()).get(candiateVO.getId()));
						 }
					 }
					 candiateVO.setComplaincePer(calculatePercantage(candiateVO.getComplainceDays(), candiateVO.getTargetDays()));
					 candidateMap.put(candiateVO.getId(), candiateVO);
				 }
				 candiateVO.getLocationSet().add(commonMethodsUtilService.getLongValueForObject(param[5]));//location value
			 }
		 }
	 }catch(Exception e){
		 LOG.error("Exception Occured in setCandidateDtls() in CoreDashboardToursService  : ",e);
	 }
 }
 public List<ToursBasicVO> getDesignationWiseAverageTourPerformanceDtls(Long stateId,String fromDateStr,String toDateStr,Long activityMemberId,Long userTypeId){
	 
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
		  List<Object[]> rtrnTargetObjList = selfAppraisalDesignationTargetDAO.getCandiateAndCategoryWiseTargetCnt(fromDate, toDate,"Category");
		  setCandiateWiseTarget(rtrnTargetObjList,candidateTargetMap);
		  
		  List<Object[]> rtrnGovtObjList = selfAppraisalDesignationTargetDAO.getCandiateAndCategoryWiseTargetCnt(fromDate, toDate,"Govt");
		  if(rtrnGovtObjList != null && rtrnGovtObjList.size() > 0){
			  for(Object[] param:rtrnGovtObjList){
				  Map<Long,List<ToursBasicVO>> candidateMap = candidateTargetMap.get(commonMethodsUtilService.getLongValueForObject(param[0]));//designationId
				  	if(candidateMap != null && candidateMap.size() > 0){
				  		List<ToursBasicVO> categoryList = candidateMap.get(commonMethodsUtilService.getLongValueForObject(param[2]));//candiateId
				  		 if(categoryList != null ){
				  			 ToursBasicVO tourTypeVO = new ToursBasicVO();
				  			   tourTypeVO.setId(commonMethodsUtilService.getLongValueForObject(param[3]));
				  			   tourTypeVO.setName(commonMethodsUtilService.getStringValueForObject(param[4]));
				  			   tourTypeVO.setTargetDays(commonMethodsUtilService.getLongValueForObject(param[5]));
				  			   categoryList.add(tourTypeVO);
				  		 }
				  	}
			  }
		  }
		  List<Object[]> rtrnDaysToursObjLst = selfAppraisalCandidateDayTourDAO.getLeaderComplainceCntCategoryWise(fromDate, toDate, "Category");
		  setComplainceDtls(rtrnDaysToursObjLst,candidateTargetMap);
		  List<Object[]> rtrnGovtDaysToursObjLst = selfAppraisalCandidateDayTourDAO.getLeaderComplainceCntCategoryWise(fromDate, toDate, "Govt");
		  setComplainceDtls(rtrnGovtDaysToursObjLst,candidateTargetMap);
		  List<Object[]> rtrnMemberDtlsObjLst = selfAppraisalCandidateDayTourDAO.getTourSubmitteedDesignationWiseAllCandiateBasedOnUserAccessLevel(stateId, locationAccessLevelId, locationValues, userTypeId,fromDate,toDate);
		  setTourSubmitteedMemberDtls(rtrnMemberDtlsObjLst,memberDtlsMap,candidateTargetMap,designationIdAndNameMap);
		
		  //merge all Own Location Type Data
		  if(memberDtlsMap != null && memberDtlsMap.size() > 0){
			 
			  for(Entry<Long,Map<Long,ToursBasicVO>> entry:memberDtlsMap.entrySet()){
				 
				 if(entry.getValue() != null && entry.getValue().size() > 0){
					 
					  for(Entry<Long,ToursBasicVO> candiateEntry:entry.getValue().entrySet()){
						  
						  List<ToursBasicVO> categoryList = candiateEntry.getValue().getSubList3();
						  
						  if(categoryList != null && categoryList.size() > 0){
							  
							  ToursBasicVO categoryVO = new ToursBasicVO();
							  
							  boolean isOwnTypeLocation = false;
							  
							  for(ToursBasicVO VO:categoryList){
								  String[] categryArr= VO.getName().split(" ");
								  if(categryArr != null && categryArr.length > 0){
									  if(categryArr[0].toString().equalsIgnoreCase("Own")){
										  categoryVO.setId(01l);
										  if(categoryVO.getName() == null){
											  categoryVO.setName(categryArr[0]+" "+categryArr[1]);
										  }else{
											  categoryVO.setName(categoryVO.getName()+"/"+categryArr[1]);  
										  }
										  categoryVO.setTargetDays(categoryVO.getTargetDays()+VO.getTargetDays());
										  categoryVO.setComplainceDays(categoryVO.getComplainceDays()+VO.getComplainceDays());
										  categoryList.remove(VO);
										  isOwnTypeLocation = true;
									  }  
								  }
							  }
							  if(isOwnTypeLocation){
								  candiateEntry.getValue().getSubList3().add(0, categoryVO);  
							  }
						  } 
					  }
				  }
			  }
		  }
		  
		  if(memberDtlsMap != null && memberDtlsMap.size() > 0){
			  
			  for(Entry<Long,Map<Long,ToursBasicVO>> designationEntry:memberDtlsMap.entrySet()){
				  
				  if(designationEntry.getValue() != null && designationEntry.getValue().size() > 0){
					  
					  for(Entry<Long,ToursBasicVO> entry:designationEntry.getValue().entrySet()){
						  
						  ToursBasicVO candiateVO = entry.getValue();
						 		if(candiateVO.getSubList3() != null && candiateVO.getSubList3().size() > 0){
						 		   Long overAllTargetDays = 0l;
						 		   Long overAllComplaincDays = 0l;
						 	 		for(ToursBasicVO categoryVO:candiateVO.getSubList3()){
						 	 			overAllTargetDays = overAllTargetDays+categoryVO.getTargetDays();
						 	 			overAllComplaincDays = overAllComplaincDays+categoryVO.getComplainceDays();
						 	 			Long categoryId = categoryVO.getId();
						 	 			if(categoryId == 1l){//Incharge District
						 	 				candiateVO.setInchargeDistrictTrDays(categoryVO.getTargetDays());
						 	 				candiateVO.setInchargeDistrictComplainceDays(categoryVO.getComplainceDays());
						 	 			}else if(categoryId == 3l){//Incharge Constituency
						 	 				candiateVO.setInchargeConstituencyTrDays(categoryVO.getTargetDays());
						 	 				candiateVO.setInchargeConstituencyComplainceDays(categoryVO.getComplainceDays());
						 	 			}else if(categoryId == 01l){//ownDistrictConstituency
						 	 				candiateVO.setOwnConDistTrDays(categoryVO.getTargetDays());
						 	 				candiateVO.setOwnConDistComplainceDays(categoryVO.getComplainceDays());
						 	 			}else if(categoryId == 2l){//govtWork
						 	 				candiateVO.setGovtWorkTrDays(categoryVO.getTargetDays());
						 	 				candiateVO.setGovtWorkComplainceDays(categoryVO.getComplainceDays());
						 	 			}
							 		}
						 	 	
						 	 		candiateVO.setTargetDays(overAllTargetDays);
							 		candiateVO.setComplainceDays(overAllComplaincDays);
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
						  
						  candiateEntry.getValue().setComplaincePer(calculatePercantage(candiateEntry.getValue().getComplainceDays(),candiateEntry.getValue().getTargetDays()));
						  candiateEntry.getValue().setInchargeDistrictComplaincePer(calculatePercantage(candiateEntry.getValue().getInchargeDistrictComplainceDays(),candiateEntry.getValue().getInchargeDistrictTrDays()));
						  candiateEntry.getValue().setInchargeConstituencyComplaincePer(calculatePercantage(candiateEntry.getValue().getInchargeConstituencyComplainceDays(),candiateEntry.getValue().getInchargeConstituencyTrDays()));
						  candiateEntry.getValue().setOwnConDistComplaincePer(calculatePercantage(candiateEntry.getValue().getOwnConDistComplainceDays(),candiateEntry.getValue().getOwnConDistComplainceDays()));
						  candiateEntry.getValue().setGovtWorkComplaincePer(calculatePercantage(candiateEntry.getValue().getGovtWorkComplainceDays(),candiateEntry.getValue().getGovtWorkTrDays()));
						  
						  if(candiateEntry.getValue().getSubList3() != null && candiateEntry.getValue().getSubList3().size() > 0){
							  for(ToursBasicVO categoryVO:candiateEntry.getValue().getSubList3()){
								  categoryVO.setComplaincePer(calculatePercantage(categoryVO.getComplainceDays(),categoryVO.getTargetDays()));  
							  }
						  }
						  
					  }
				  }
			  }
		  }
		  //merge secretary and organization secretary
		  if(memberDtlsMap!=null && memberDtlsMap.size()>0){
		        Map<Long,ToursBasicVO> orgSecAndSecMap = new LinkedHashMap<Long,ToursBasicVO>();
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
		  //pushing in final list
		  if(memberDtlsMap != null && memberDtlsMap.size() > 0){
			  for(Entry<Long,Map<Long,ToursBasicVO>> entry:memberDtlsMap.entrySet()){
				  ToursBasicVO designationVO = new ToursBasicVO();
				  designationVO.setId(entry.getKey());
				  designationVO.setName(designationIdAndNameMap.get(entry.getKey()));
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
 public void setCandiateWiseTarget(List<Object[]> objList,Map<Long,Map<Long,List<ToursBasicVO>>> candidateTargetMap){
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
				 ToursBasicVO categoryVO = new ToursBasicVO();
				 categoryVO.setId(commonMethodsUtilService.getLongValueForObject(param[3]));
				 categoryVO.setName(commonMethodsUtilService.getStringValueForObject(param[4]));
				 categoryVO.setTargetDays(commonMethodsUtilService.getLongValueForObject(param[5]));
				 categoryList.add(categoryVO);
			 }
		 }
	 }catch(Exception e){
		 LOG.error("Exception Occured in setCandiateWiseTarget() in CoreDashboardToursService  : ",e); 
	 }
 }
 public void setComplainceDtls(List<Object[]> objList,Map<Long,Map<Long,List<ToursBasicVO>>> candidateTargetMap){
	 try{
		 if(objList != null && objList.size() > 0){
			 for(Object[] param:objList){
				 Long designationId = commonMethodsUtilService.getLongValueForObject(param[0]);
				 Long candidateId = commonMethodsUtilService.getLongValueForObject(param[2]);
				 Long id = commonMethodsUtilService.getLongValueForObject(param[3]);
				 Long noOfTours = commonMethodsUtilService.getLongValueForObject(param[4]);
				 if(candidateTargetMap.get(designationId) != null && candidateTargetMap.get(designationId).size() > 0){
					 ToursBasicVO categoryVO = getCategoryMatchVO(candidateTargetMap.get(designationId).get(candidateId), id);
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
				 		candiateMap = new HashMap<Long, ToursBasicVO>();
				 		designationIdAndNameMap.put(designationId,commonMethodsUtilService.getStringValueForObject(param[1]));
				 		memberDtlsMap.put(designationId, candiateMap);
				 	}
				 	ToursBasicVO candiateVO = new ToursBasicVO();
				 	candiateVO.setId(candiateId);
				 	candiateVO.setName(commonMethodsUtilService.getStringValueForObject(param[3]));
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
}
