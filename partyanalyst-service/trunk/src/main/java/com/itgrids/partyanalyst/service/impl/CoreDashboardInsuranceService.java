package com.itgrids.partyanalyst.service.impl;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.apache.log4j.Logger;

import com.itgrids.partyanalyst.dao.IInsuranceStatusDAO;
import com.itgrids.partyanalyst.dao.hibernate.ActivityMemberAccessLevelDAO;
import com.itgrids.partyanalyst.dto.ActivityMemberVO;
import com.itgrids.partyanalyst.dto.CadreInsuranceInputVO;
import com.itgrids.partyanalyst.dto.ComplaintMasterVO;
import com.itgrids.partyanalyst.dto.CoreDashboardInsuranceVO;
import com.itgrids.partyanalyst.dto.InsuranceLagDaysVO;
import com.itgrids.partyanalyst.dto.UserTypeVO;
import com.itgrids.partyanalyst.service.ICoreDashboardGenericService;
import com.itgrids.partyanalyst.service.ICoreDashboardInsuranceService;
import com.itgrids.partyanalyst.utils.CommonMethodsUtilService;
import com.itgrids.partyanalyst.utils.DateUtilService;
import com.itgrids.partyanalyst.utils.IConstants;

public class CoreDashboardInsuranceService implements ICoreDashboardInsuranceService{

	private final static Logger LOG = Logger.getLogger(CoreDashboardPartyMeetingService.class);

	private ActivityMemberAccessLevelDAO activityMemberAccessLevelDAO;
	private IInsuranceStatusDAO insuranceStatusDAO;
	private ICoreDashboardGenericService coreDashboardGenericService;
	private CommonMethodsUtilService commonMethodsUtilService;
	
	
	public IInsuranceStatusDAO getInsuranceStatusDAO() {
		return insuranceStatusDAO;
	}
	public void setInsuranceStatusDAO(IInsuranceStatusDAO insuranceStatusDAO) {
		this.insuranceStatusDAO = insuranceStatusDAO;
	}
	public ActivityMemberAccessLevelDAO getActivityMemberAccessLevelDAO() {
		return activityMemberAccessLevelDAO;
	}
	public void setActivityMemberAccessLevelDAO(
			ActivityMemberAccessLevelDAO activityMemberAccessLevelDAO) {
		this.activityMemberAccessLevelDAO = activityMemberAccessLevelDAO;
	}
   	public void setCoreDashboardGenericService(
			ICoreDashboardGenericService coreDashboardGenericService) {
		this.coreDashboardGenericService = coreDashboardGenericService;
	}
   public void setCommonMethodsUtilService(
			CommonMethodsUtilService commonMethodsUtilService) {
		this.commonMethodsUtilService = commonMethodsUtilService;
	}
	public List<CoreDashboardInsuranceVO> getInsuraceCompanyAndTypeOfIssueWiseComplaintsDetails(Long activityMemberId,Long cadreYearId,Long stateId,
						String fromDateStr,String toDateStr){
		List<CoreDashboardInsuranceVO> returnList = new ArrayList<CoreDashboardInsuranceVO>();
		try {
			Map<String,CoreDashboardInsuranceVO> statusMap = new LinkedHashMap<String, CoreDashboardInsuranceVO>();
			CoreDashboardInsuranceVO totalvo = new CoreDashboardInsuranceVO();
			
			SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
			Date fromDate = null;
			Date toDate = null;
			if(fromDateStr != null && toDateStr != null){
				fromDate = sdf.parse(fromDateStr);
				toDate = sdf.parse(toDateStr);
			}
			
			Long locationId = 0L;
			Set<Long> locationValuesSet = new java.util.HashSet<Long>();  
			
			if(stateId != null && (stateId == 0l || stateId == 2l)){
				locationId = 2l;
				locationValuesSet.add(2l);
				if(stateId == 0l)
					locationValuesSet.add(1l);
			}
			else if(stateId != null && stateId.longValue() == 1l){
				List<Object[]> rtrnUsrAccssLvlIdAndVlusObjLst=activityMemberAccessLevelDAO.getLocationLevelAndValuesByActivityMembersId(activityMemberId);
			    if(rtrnUsrAccssLvlIdAndVlusObjLst != null && !rtrnUsrAccssLvlIdAndVlusObjLst.isEmpty()){
				   for (Object[] obj : rtrnUsrAccssLvlIdAndVlusObjLst) {
					   locationId = Long.valueOf(obj[0] != null ? obj[0].toString():"0");
					   locationValuesSet.add(Long.valueOf(obj[1] != null ? obj[1].toString():"0"));
				   }
			    }
			}
			
			List<Object[]> companyList = insuranceStatusDAO.getAllInsuranceCompanies();
			
			String statusStr = IConstants.CORE_DASHBOARD_INSURANCE_STATUS;
			String[] statusTemplate = statusStr.split(",");
			if(statusTemplate != null && statusTemplate.length > 0){
				for (String status : statusTemplate) {
					CoreDashboardInsuranceVO vo = new CoreDashboardInsuranceVO();
					vo.setName(status);
					vo.setSubList(setInsuranceCompaniesList(companyList));
					statusMap.put(status, vo);
				}
			}
			
			List<Object[]> list = insuranceStatusDAO.getStatusAndInsuranceCompanyWiseComplaints(locationId, locationValuesSet, stateId, cadreYearId, fromDate, toDate);
			if(list != null && !list.isEmpty()){
				for (Object[] obj : list) {
					Long statusId = Long.valueOf(obj[0] != null ? obj[0].toString():"0");
					Long companyId = Long.valueOf(obj[1] != null ? obj[1].toString():"0");
					//String companyName = obj[2] != null ? obj[2].toString():"";
					String issueType = obj[3] != null ? obj[3].toString():"";
					Long count = Long.valueOf(obj[4] != null ? obj[4].toString():"0");
					
					String status = "";
					if(statusId == 1l || statusId == 2l)
						status = "INTIMATIONS";
					else if(statusId == 3l || statusId == 7l)
						status = "FORWARDED";
					else if(statusId == 6l || statusId == 8l)
						status = "SETTLED";
					else if(statusId == 4l || statusId == 5l)
						status = "REJECTED";
					
					CoreDashboardInsuranceVO statusvo = statusMap.get(status);
					if(statusvo != null){
						CoreDashboardInsuranceVO companyvo = getMatchVO(statusvo.getSubList(), companyId);
						if(issueType != null && issueType.equalsIgnoreCase("Death")){
							companyvo.setDeathCount(companyvo.getDeathCount()+count);
							statusvo.setDeathCount(statusvo.getDeathCount()+count);
							totalvo.setDeathCount(totalvo.getDeathCount()+count);
						}
						else if(issueType != null && issueType.equalsIgnoreCase("Hospitalization")){
							companyvo.setHospitalizationCount(companyvo.getHospitalizationCount()+count);
							statusvo.setHospitalizationCount(statusvo.getHospitalizationCount()+count);
							totalvo.setHospitalizationCount(totalvo.getHospitalizationCount()+count);
						}
						companyvo.setTotalCount(companyvo.getTotalCount()+count);
						statusvo.setTotalCount(statusvo.getTotalCount()+count);
						totalvo.setTotalCount(totalvo.getTotalCount()+count);
					}
				}
			}
			
			if(statusMap != null)
				returnList = new ArrayList<CoreDashboardInsuranceVO>(statusMap.values());
			
			if(returnList != null && !returnList.isEmpty()){
				for (CoreDashboardInsuranceVO vo : returnList) {
					vo.setPercentage(calculatePercantage(vo.getTotalCount(), totalvo.getTotalCount()));
				}
				returnList.get(0).setCoreDashboardInsuranceVO(totalvo);
			}
		    
		} catch (Exception e) {
			LOG.error("Exception Occured in getInsuraceCompanyAndTypeOfIssueWiseComplaintsDetails on CoreDashboardInsuranceService", e);
		}
		return returnList;
	}
	
	public List<CoreDashboardInsuranceVO> setInsuranceCompaniesList(List<Object[]> list){
		List<CoreDashboardInsuranceVO> returnList = new ArrayList<CoreDashboardInsuranceVO>();
		try {
			if(list != null && !list.isEmpty()){
				for (Object[] obj : list) {
					CoreDashboardInsuranceVO vo = new CoreDashboardInsuranceVO();
					vo.setId(Long.valueOf(obj[0] != null ? obj[0].toString():"0"));
					vo.setName(obj[1] != null ? obj[1].toString():"");
					returnList.add(vo);
				}
			}
		} catch (Exception e) {
			LOG.error("Exception Occured in setInsuranceCompaniesList on CoreDashboardInsuranceService", e);
		}
		return returnList;
	}
	
	public CoreDashboardInsuranceVO getMatchVO(List<CoreDashboardInsuranceVO> list,Long id){
		if(list == null || list.isEmpty())
			return null;
		for(CoreDashboardInsuranceVO vo:list){
			 if(vo.getId().equals(id)){
				 return vo;
			 }
		}
		return null;
	}
	
	public List<ComplaintMasterVO> getInsuraceStatusWiseComplaintsDetails(Long activityMemberId,Long cadreYearId,Long stateId,
			String fromDateStr,String toDateStr,String status,Long companyId,String issueType){
		List<ComplaintMasterVO> returnList = new ArrayList<ComplaintMasterVO>();
		try {
			SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
			Date fromDate = null;
			Date toDate = null;
			if(fromDateStr != null && toDateStr != null){
				fromDate = sdf.parse(fromDateStr);
				toDate = sdf.parse(toDateStr);
			}
			
			Long locationId = 0L;
			Set<Long> locationValuesSet = new java.util.HashSet<Long>();  
			
			if(stateId != null && (stateId == 0l || stateId == 2l)){
				locationId = 2l;
				locationValuesSet.add(2l);
				if(stateId == 0l)
					locationValuesSet.add(1l);
			}
			else if(stateId != null && stateId.longValue() == 1l){
				List<Object[]> rtrnUsrAccssLvlIdAndVlusObjLst=activityMemberAccessLevelDAO.getLocationLevelAndValuesByActivityMembersId(activityMemberId);
			    if(rtrnUsrAccssLvlIdAndVlusObjLst != null && !rtrnUsrAccssLvlIdAndVlusObjLst.isEmpty()){
				   for (Object[] obj : rtrnUsrAccssLvlIdAndVlusObjLst) {
					   locationId = Long.valueOf(obj[0] != null ? obj[0].toString():"0");
					   locationValuesSet.add(Long.valueOf(obj[1] != null ? obj[1].toString():"0"));
				   }
			    }
			}
			
			List<Long> statusIds = new ArrayList<Long>();
			if(status != null && status.trim().length() > 0 && status.trim().equalsIgnoreCase("INTIMATIONS")){
				statusIds.add(1l);
				statusIds.add(2l);
			}else if(status != null && status.trim().length() > 0 && status.trim().equalsIgnoreCase("FORWARDED")){
				statusIds.add(3l);
				statusIds.add(7l);
			}else if(status != null && status.trim().length() > 0 && status.trim().equalsIgnoreCase("SETTLED")){
				statusIds.add(6l);
				statusIds.add(8l);
			}else if(status != null && status.trim().length() > 0 && status.trim().equalsIgnoreCase("REJECTED")){
				statusIds.add(4l);
				statusIds.add(5l);
			}
			
			List<Long> complaintIds = insuranceStatusDAO.getStatusAndInsuranceCompanyWiseComplaintIds(locationId, locationValuesSet, stateId, cadreYearId, fromDate, toDate, statusIds, companyId, issueType);
			if(complaintIds != null && !complaintIds.isEmpty())
				returnList = getComplaintDetailsByComplaintIds(complaintIds);
			
		} catch (Exception e) {
			LOG.error("Exception Occured in getInsuraceStatusWiseComplaintsDetails on CoreDashboardInsuranceService", e);
		}
		return returnList;
	}
	
	public List<ComplaintMasterVO> getComplaintDetailsByComplaintIds(List<Long> complaintIds){
		List<ComplaintMasterVO> returnList = new ArrayList<ComplaintMasterVO>();
		try {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm a");
			
			List<Object[]> list = insuranceStatusDAO.getStatusAndInsuranceCompanyWiseComplaintDetails(complaintIds);
			if(list != null && !list.isEmpty()){
				for (Object[] obj : list) {
					ComplaintMasterVO vo = new ComplaintMasterVO();
					
					vo.setComplaintId(Long.valueOf(obj[0] != null ? obj[0].toString():"0"));
					vo.setName(obj[1] != null ? obj[1].toString():"");
					vo.setMobileNo(obj[2] != null ? obj[2].toString():"");
					vo.setMemberShipNo(obj[3] != null ? obj[3].toString():"");
					vo.setDistrictId(Long.valueOf(obj[4] != null ? obj[4].toString():""));
					vo.setDistrictName(obj[5] != null ? obj[5].toString():"");
					vo.setConstituencyId(Long.valueOf(obj[6] != null ? obj[6].toString():""));
					vo.setConstituencyName(obj[7] != null ? obj[7].toString():"");
					vo.setSubject(obj[8] != null ? obj[8].toString():"");
					vo.setDescription(obj[9] != null ? obj[9].toString():"");
					vo.setTypeOfIssue(obj[10] != null ? obj[10].toString():"");
					vo.setStatusId(Long.valueOf(obj[11] != null ? obj[11].toString():""));
					vo.setStatus(obj[12] != null ? obj[12].toString():"");
					vo.setPostedDate(obj[13] != null ? sdf.format((Date)obj[13]) :null);
					vo.setMadalId(Long.valueOf(obj[14] != null ? obj[14].toString():"0"));
					vo.setMandalName(obj[15] != null ? obj[15].toString()+" Mandal":"");
					vo.setVillageId(Long.valueOf(obj[18] != null ? obj[18].toString():"0"));
					vo.setVillageName(obj[19] != null ? obj[19].toString():"");
					if(vo.getMadalId() == 0l && vo.getMandalName().trim().length() == 0l && vo.getVillageId() == 0l && vo.getVillageName().trim().length() == 0l){
						vo.setMadalId(Long.valueOf(obj[16] != null ? obj[16].toString():"0"));
						vo.setMandalName(obj[17] != null ? obj[17].toString()+" Muncipality":"");
						vo.setVillageId(Long.valueOf(obj[20] != null ? obj[20].toString():"0"));
						vo.setVillageName(obj[21] != null ? obj[21].toString():"");
					}
					
					returnList.add(vo);
				}
			}
			
			List<Object[]> commentList = insuranceStatusDAO.getLatestComplaintResponsesForComplaintIds(complaintIds);
			if(commentList != null && !commentList.isEmpty()){
				for (Object[] obj : commentList) {
					Long complaintId = Long.valueOf(obj[0] != null ? obj[0].toString():"0");
					String comment = obj[1] != null ? obj[1].toString():"";
					String updatedDate = obj[2] != null ? sdf.format((Date)obj[2]):null;
					
					ComplaintMasterVO vo = getComplaintMasterMatchedVO(returnList, complaintId);
					if(vo != null){
						vo.setComment(comment);
						vo.setUpdatedDate(updatedDate);
					}
				}
			}
			
		} catch (Exception e) {
			LOG.error("Exception Occured in getComplaintDetailsByComplaintIds on CoreDashboardInsuranceService", e);
		}
		return returnList;
	}
	public InsuranceLagDaysVO getLagDaysInsuranceComplaintsCounts(Long activityMemberId,Long cadreYearId,Long stateId,String status,Long companyId,String issueType){
		InsuranceLagDaysVO returnvo = new InsuranceLagDaysVO();
		try {
			Long locationId = 0L;
			Set<Long> locationValuesSet = new java.util.HashSet<Long>();  
			
			if(stateId != null && (stateId == 0l || stateId == 2l)){
				locationId = 2l;
				locationValuesSet.add(2l);
				if(stateId == 0l)
					locationValuesSet.add(1l);
			}
			else if(stateId != null && stateId.longValue() == 1l){
				List<Object[]> rtrnUsrAccssLvlIdAndVlusObjLst=activityMemberAccessLevelDAO.getLocationLevelAndValuesByActivityMembersId(activityMemberId);
			    if(rtrnUsrAccssLvlIdAndVlusObjLst != null && !rtrnUsrAccssLvlIdAndVlusObjLst.isEmpty()){
				   for (Object[] obj : rtrnUsrAccssLvlIdAndVlusObjLst) {
					   locationId = Long.valueOf(obj[0] != null ? obj[0].toString():"0");
					   locationValuesSet.add(Long.valueOf(obj[1] != null ? obj[1].toString():"0"));
				   }
			    }
			}
			
			List<Long> statusIds = new ArrayList<Long>();
			if(status != null && status.trim().length() > 0 && status.trim().equalsIgnoreCase("INTIMATIONS")){
				statusIds.add(1l);
				statusIds.add(2l);
			}else if(status != null && status.trim().length() > 0 && status.trim().equalsIgnoreCase("FORWARDED")){
				statusIds.add(3l);
				statusIds.add(7l);
			}else if(status != null && status.trim().length() > 0 && status.trim().equalsIgnoreCase("SETTLED")){
				statusIds.add(6l);
				statusIds.add(8l);
			}else if(status != null && status.trim().length() > 0 && status.trim().equalsIgnoreCase("REJECTED")){
				statusIds.add(4l);
				statusIds.add(5l);
			}
			
			Date todayDate = new DateUtilService().getCurrentDateAndTime();
			List<Long> todayList = insuranceStatusDAO.getStatusAndInsuranceCompanyWiseComplaintIds(locationId, locationValuesSet, stateId, cadreYearId, todayDate, todayDate, statusIds, companyId, issueType);
			if(todayList != null && todayList.isEmpty())
				returnvo.setTodayCount((long)todayList.size());
			
			Calendar cal = Calendar.getInstance();
			cal.setTime(todayDate);
			cal.add(Calendar.DATE, -7);
			Date weekDate =  cal.getTime();
			List<Long> weekList = insuranceStatusDAO.getStatusAndInsuranceCompanyWiseComplaintIds(locationId, locationValuesSet, stateId, cadreYearId, weekDate, todayDate, statusIds, companyId, issueType);
			if(weekList != null && !weekList.isEmpty())
				returnvo.setWeekCount((long)weekList.size());
			
			cal.clear();
			cal.setTime(todayDate);
			cal.add(Calendar.DATE, -30);
			Date monthDate =  cal.getTime();
			List<Long> monthList = insuranceStatusDAO.getStatusAndInsuranceCompanyWiseComplaintIds(locationId, locationValuesSet, stateId, cadreYearId, monthDate, todayDate, statusIds, companyId, issueType);
			if(monthList != null && !monthList.isEmpty())
				returnvo.setMonthCount((long)monthList.size());
			
			cal.clear();
			cal.setTime(todayDate);
			cal.add(Calendar.DATE, -90);
			Date threeMnthsDate =  cal.getTime();
			List<Long> threeMnthsList = insuranceStatusDAO.getStatusAndInsuranceCompanyWiseComplaintIds(locationId, locationValuesSet, stateId, cadreYearId, threeMnthsDate, todayDate, statusIds, companyId, issueType);
			if(threeMnthsList != null && !threeMnthsList.isEmpty())
				returnvo.setThreeMnthsCount((long)threeMnthsList.size());
			
			cal.clear();
			cal.setTime(todayDate);
			cal.add(Calendar.DATE, -180);
			Date sixMnthsDate =  cal.getTime();
			List<Long> sixMnthsList = insuranceStatusDAO.getStatusAndInsuranceCompanyWiseComplaintIds(locationId, locationValuesSet, stateId, cadreYearId, sixMnthsDate, todayDate, statusIds, companyId, issueType);
			if(sixMnthsList != null && !sixMnthsList.isEmpty())
				returnvo.setSixMnthsCount((long)sixMnthsList.size());
			
			cal.clear();
			cal.setTime(todayDate);
			cal.add(Calendar.DATE, -270);
			Date nineMnthsDate =  cal.getTime();
			List<Long> nineMnthsList = insuranceStatusDAO.getStatusAndInsuranceCompanyWiseComplaintIds(locationId, locationValuesSet, stateId, cadreYearId, nineMnthsDate, todayDate, statusIds, companyId, issueType);
			if(nineMnthsList != null && !nineMnthsList.isEmpty())
				returnvo.setNineMonthsCount((long)nineMnthsList.size());
			
			cal.clear();
			List<Long> overAllList = insuranceStatusDAO.getStatusAndInsuranceCompanyWiseComplaintIds(locationId, locationValuesSet, stateId, cadreYearId, null, null, statusIds, companyId, issueType);
			if(overAllList != null && !overAllList.isEmpty())
				returnvo.setOverAllCount((long)overAllList.size());
			
		} catch (Exception e) {
			LOG.error("Exception Occured in getLagDaysInsuranceComplaintsCounts on CoreDashboardInsuranceService", e);
		}
		return returnvo;
	}
	
	public ComplaintMasterVO getComplaintMasterMatchedVO(List<ComplaintMasterVO> list,Long id){
		if(list == null || list.isEmpty())
			return null;
		for(ComplaintMasterVO vo:list){
			 if(vo.getComplaintId().equals(id)){
				 return vo;
			 }
		}
		return null;
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
	/*
	 * Santosh (non-Javadoc)
	 * @see com.itgrids.partyanalyst.service.ICoreDashboardInsuranceService#getUserTypeWiseTotalCadreInsuranceComplainctCnt(java.lang.Long, java.lang.Long, java.lang.Long, java.lang.Long, java.lang.Long, java.lang.String, java.lang.String)
	 */
	public List<List<UserTypeVO>> getUserTypeWiseTotalCadreInsuranceComplainctCnt(Long activityMemberId,Long userId,Long userTypeId,Long stateId,Long cadreEnrollmentYearId,String fromDateStr,String toDateStr) {
		 
		  List<List<UserTypeVO>> resultList = new ArrayList<List<UserTypeVO>>(0);
		  Map<String,UserTypeVO> complainctCntMap  = new HashMap<String, UserTypeVO>(0);
		  List<UserTypeVO> issueTypeList = new ArrayList<UserTypeVO>();
		  Map<Long,Set<Long>> locationLevelMap = null;
		  Map<Long,Map<Long,UserTypeVO>> userTypeMapDtls = null;
		  try{
			     SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
				 Date fromDate = null;
				 Date toDate = null;
				 if(fromDateStr != null && fromDateStr.length() > 0 && toDateStr != null && fromDateStr.length() > 0){
					fromDate = sdf.parse(fromDateStr);
					toDate = sdf.parse(toDateStr);
				 }
			     ActivityMemberVO activityMemberVO = new ActivityMemberVO();
			     activityMemberVO.setUserId(userId);
			     activityMemberVO.setActivityMemberId(activityMemberId);
			     activityMemberVO.setUserTypeId(userTypeId);
			     activityMemberVO = coreDashboardGenericService.getChildActivityMembersAndLocationsNew(activityMemberVO);//calling generic method.
			     userTypeMapDtls = activityMemberVO.getUserTypesMap();
			     locationLevelMap = activityMemberVO.getLocationLevelIdsMap();
			     //Setting query input parameter
			     CadreInsuranceInputVO inputVO = new CadreInsuranceInputVO();
			     inputVO.setStateId(stateId);
			     inputVO.setFromDate(fromDate);
			     inputVO.setToDate(toDate);
			     inputVO.setEnrollmentYearId(cadreEnrollmentYearId);
			     //PrepareTemplate
			     List<String> rtrnissutTypeObj = insuranceStatusDAO.getAllIssueType(stateId);
			     List<Object[]> rtrnStatusObjLst = insuranceStatusDAO.getAllGrievanceInsuranceStatus();
			     //Prepare Template
			      prepareTemplate(rtrnissutTypeObj,rtrnStatusObjLst,issueTypeList);
			      //Setting Candidate Wise Template
			      settingCandiateWiseIssueTypeAndStatusDls(userTypeMapDtls,issueTypeList);
			      
			      if(locationLevelMap != null && locationLevelMap.size() > 0){
			    	 
			    	 for(Entry<Long,Set<Long>> entry:locationLevelMap.entrySet()){
			    		 inputVO.setUserAccessLevelId(entry.getKey());
			    		 inputVO.setUserAccessLevelValues(entry.getValue());
			    		 List<Object[]> rtrnComplaintCnt = insuranceStatusDAO.getLocationWiseComplaintCntBasedOnUserAccessLevel(inputVO);
			    		 setComplaintCount(entry.getKey(),rtrnComplaintCnt,complainctCntMap,issueTypeList);
			    		
			    	 }
			     }
			  
			     //pushing complaint count
			     if(userTypeMapDtls != null && userTypeMapDtls.size() > 0){
			    	 
					  for (Entry<Long, Map<Long, UserTypeVO>> entry : userTypeMapDtls.entrySet()) {
						  
					      Map<Long,UserTypeVO> userTypeMap = entry.getValue();
					      
					      for(UserTypeVO vo:userTypeMap.values()){
					    	  
					    	  for(Long locationValueId:vo.getLocationValuesSet()){
					    		  
					    		  String key = vo.getLocationLevelId()+"-"+locationValueId;
					    		  
					    		  if(complainctCntMap.get(key) != null){
					    			  
					    			  UserTypeVO issueTypeVO = complainctCntMap.get(key);
					    			  
					    				  if(issueTypeVO.getSubList() != null && issueTypeVO.getSubList().size() > 0){
					    					  
					    					  for(UserTypeVO issTypeVO:issueTypeVO.getSubList()){
					    						  
					    						  List<UserTypeVO> statuList = getIssueTypeMatchVO(vo.getSubList(),issTypeVO.getName());
					    						  
					    						  if(statuList != null && statuList.size() > 0){
					    							  
					    							  if(issTypeVO.getSubList() != null && issTypeVO.getSubList().size() > 0){
					    								  
					    								for(UserTypeVO statusVO:issTypeVO.getSubList()) {
					    									
					    									UserTypeVO matchVO = getStatusMatchVO(statuList, statusVO.getId());
					    									
					    									 if(matchVO != null){
					    										 
					    										 matchVO.setTotalCount(matchVO.getTotalCount()+statusVO.getTotalCount());
					    									 }
					    								}
					    							  }
					    						  }
					    					  }
					    				  }
					    			  }
					    	  }
					      }
				  }  
				}
			
			    // Calculate Total Complaint Count Candidate Wise
			     if(userTypeMapDtls != null && userTypeMapDtls.size() > 0){
			    	  for (Entry<Long, Map<Long, UserTypeVO>> entry : userTypeMapDtls.entrySet()) {
					      Map<Long,UserTypeVO> userTypeMap = entry.getValue();
					      for(UserTypeVO vo:userTypeMap.values()){
					    	 if(vo.getSubList() != null && vo.getSubList().size() > 0){
					    		 for(UserTypeVO issueTypeVO:vo.getSubList()){
					    			 if(issueTypeVO.getSubList() != null && issueTypeVO.getSubList().size() >0l){
					    				 for(UserTypeVO statusVO:issueTypeVO.getSubList()){
					    					 vo.setTotalCount(vo.getTotalCount()+statusVO.getTotalCount());//Over All Count candidate wise
					    					 issueTypeVO.setTotalCount(issueTypeVO.getTotalCount()+statusVO.getTotalCount());//Issue Type wise total count
					    				 }
					    			 }
					    		 }
					    	 }
					    	
				     }  
				 }
			   }
			   if(userTypeMapDtls!=null && userTypeMapDtls.size()>0){
			        Map<Long,UserTypeVO> orgSecAndSecMap = new LinkedHashMap<Long,UserTypeVO>();
			        Map<Long,UserTypeVO>  secreteriesMap = null;
			        if(userTypeMapDtls.containsKey(11l)){
			          secreteriesMap = userTypeMapDtls.get(11l);
			          orgSecAndSecMap.putAll(secreteriesMap);
			          //remove secreteries from Map
			          userTypeMapDtls.remove(11l); 
			        }
			        
			        Map<Long,UserTypeVO>  organizingSecreteriesMap = null;
			        if(userTypeMapDtls.containsKey(4l)){
			          organizingSecreteriesMap = userTypeMapDtls.get(4l);
			          orgSecAndSecMap.putAll(organizingSecreteriesMap);
			        }
			       
			        if(organizingSecreteriesMap!=null && organizingSecreteriesMap.size()>0){
			        	userTypeMapDtls.put(4l, orgSecAndSecMap); 
			        }
			      }
				
				if(userTypeMapDtls != null && userTypeMapDtls.size() > 0){
					  for(Entry<Long, Map<Long, UserTypeVO>> entry:userTypeMapDtls.entrySet()){
					   Map<Long,UserTypeVO> userTypeMap = entry.getValue();
					   resultList.add(new ArrayList<UserTypeVO>(userTypeMap.values()));
				}
				}
				if(resultList != null && resultList.size() > 0){
					for(List<UserTypeVO> memberList:resultList){
						Collections.sort(memberList, complaintCountDesc);
					}
				}
			
		 }catch (Exception e) {
			 LOG.error("Error occured at getUserTypeWiseTotalComplainctCnt() in CoreDashboardInsuranceService class",e);
		 }
		  return resultList;
	  }
	   public static Comparator<UserTypeVO> complaintCountDesc = new Comparator<UserTypeVO>() {
			public int compare(UserTypeVO member2, UserTypeVO member1) {
			Long count2 = member2.getTotalCount();
			Long count1 = member1.getTotalCount();
			//descending order of percantages.
			 return count1.compareTo(count2);
			}
		}; 
		public void prepareTemplate(List<String> issueTypeLst,List<Object[]> statusLst,List<UserTypeVO> issueTypeList){
			try{
				if(issueTypeLst != null && issueTypeLst.size() > 0){
					for(String issueType:issueTypeLst){
						UserTypeVO issueTypeVO = new UserTypeVO();
						issueTypeVO.setName(issueType);
						issueTypeVO.setSubList(getStatusList(statusLst));
						issueTypeList.add(issueTypeVO);
					}
				}
			}catch(Exception e){
				LOG.error("Error occured at prepareTemplate() in CoreDashboardInsuranceService class",e);
			}
		}
		public List<UserTypeVO> getStatusList(List<Object[]> statusObjLst){
			List<UserTypeVO> statusList = new ArrayList<UserTypeVO>();
			try{
				if(statusObjLst != null && statusObjLst.size() > 0){
					for(Object[] param:statusObjLst){
						UserTypeVO statuVO = new UserTypeVO();
						Long statusId = commonMethodsUtilService.getLongValueForObject(param[0]);
						if(statusId == 1l || statusId == 2l){
							statuVO.setId(1l);
							statuVO.setName("INTIMATIONS");
						}else if(statusId == 3l || statusId == 7l){
							statuVO.setId(2l);
							statuVO.setName("FORWARDED");
						}else if(statusId == 6l || statusId == 8l){
							statuVO.setId(3l);
							statuVO.setName("SETTLED");
						}else if(statusId == 4l || statusId == 5l){
							statuVO.setId(4l);
							statuVO.setName("REJECTED");
						}else{
							statuVO.setId(statusId);
							statuVO.setName(commonMethodsUtilService.getStringValueForObject(param[1]));
						}
						UserTypeVO statusVO = getStatusMatchVO(statusList,statuVO.getId());
						if(statusVO == null){
							statusList.add(statuVO);
						}
					}
				}
			}catch(Exception e){
				LOG.error("Error occured at getStatusList() in CoreDashboardInsuranceService class",e);	
			}
			return statusList;
		}
		public void settingCandiateWiseIssueTypeAndStatusDls(Map<Long,Map<Long,UserTypeVO>> userTypeMapDtls,List<UserTypeVO> issueTypeList){
			try{
				if(userTypeMapDtls != null && userTypeMapDtls.size() > 0){
					for(Entry<Long,Map<Long,UserTypeVO>> designationEntry:userTypeMapDtls.entrySet()){
						if(designationEntry.getValue() != null && designationEntry.getValue().size() > 0){
							for(Entry<Long,UserTypeVO> candidateEntry:designationEntry.getValue().entrySet()){
								candidateEntry.getValue().setSubList(getIssueTypeLst(issueTypeList));
							}
						}
					}
				}
			}catch(Exception e){
				LOG.error("Error occured at settingCandiateWiseIssueTypeAndStatusDlst() in CoreDashboardInsuranceService class",e);
			}
		}
		public List<UserTypeVO> getIssueTypeLst(List<UserTypeVO> issueTypeList){
	    	 List<UserTypeVO> resultList = new ArrayList<UserTypeVO>();
	    	 try{
	    		 if(issueTypeList != null && issueTypeList.size() > 0){
	    			 for(UserTypeVO issueTypeVO:issueTypeList){
	    				 UserTypeVO issueTypVO = new UserTypeVO();
	    				 issueTypVO.setName(issueTypeVO.getName());
	    				 if(issueTypeVO.getSubList() != null && issueTypeVO.getSubList().size() > 0){
	    					 for(UserTypeVO statusVO:issueTypeVO.getSubList()){
	    						 UserTypeVO statuVO = new UserTypeVO();
	    						 statuVO.setId(statusVO.getId());
	    						 statuVO.setName(statusVO.getName());
	    						 if(issueTypVO.getSubList() == null){
	    							 issueTypVO.setSubList(new ArrayList<UserTypeVO>());
	    						 }
	    						 issueTypVO.getSubList().add(statuVO);
	    					 }
	    				 }
	    				 resultList.add(issueTypVO);
	    			 }
	    		 }
	    	 }catch(Exception e){
	    		 LOG.error("Error occured at getStatusList() in CoreDashboardInsuranceService class",e);
	    	 }
	    	 return resultList;
		 }
		 public void setComplaintCount(Long userAccessLevelId,List<Object[]> objList,Map<String,UserTypeVO> locationWisecomplaintMap,List<UserTypeVO> issueTypeList){
			 try{
				 if(objList != null && objList.size() > 0){
					 for(Object[] param:objList){
						 String locationLevelAndId = userAccessLevelId+"-"+commonMethodsUtilService.getStringValueForObject(param[0]);
						 UserTypeVO locationVO = locationWisecomplaintMap.get(locationLevelAndId);
						  if(locationVO == null){
							  locationVO = new UserTypeVO();
							  locationVO.setId(commonMethodsUtilService.getLongValueForObject(param[0]));
							  locationVO.setSubList(getIssueTypeLst(issueTypeList));
							  locationWisecomplaintMap.put(locationLevelAndId,locationVO);
						  }
						 String issueType = commonMethodsUtilService.getStringValueForObject(param[1]);
						 Long statusId = commonMethodsUtilService.getLongValueForObject(param[2]);
						 Long complaintCnt = commonMethodsUtilService.getLongValueForObject(param[3]);
						 Long approvedAmt = commonMethodsUtilService.getLongValueForObject(param[4]);
						 List<UserTypeVO>  statusList = getIssueTypeMatchVO(locationVO.getSubList(),issueType);
						 
					     if(statusId == 1l || statusId == 2l){
						     statusId = 1l;
						 }else if(statusId == 3l || statusId == 7l){
							 statusId = 2l;
						 }else if(statusId == 6l || statusId == 8l){
							 statusId = 3l;
						 }else if(statusId == 4l || statusId == 5l){
							 statusId = 4l;
						 }
						 if(statusList != null && statusList.size() > 0){
							 UserTypeVO statusMatchVO = getStatusMatchVO(statusList,statusId);
							  if(statusMatchVO != null ){
								  statusMatchVO.setTotalCount(statusMatchVO.getTotalCount()+complaintCnt);
								  statusMatchVO.setPositiveCount(statusMatchVO.getPositiveCount()+approvedAmt);
							  }
						 }
				   }
				}
			 }catch(Exception e){
				 LOG.error("Error occured at setComplaintCount() in CoreDashboardInsuranceService class",e); 
			 }
		 }
		public UserTypeVO getStatusMatchVO(List<UserTypeVO> statuVOList,Long statusId){
				try{
					if(statuVOList == null || statuVOList.size() == 0)
						return null;
					for(UserTypeVO statusVO:statuVOList){
						if(statusVO.getId().equals(statusId)){
							return statusVO;
						}
					}
				}catch(Exception e){
					LOG.error("Error occured at getStatusList() in CoreDashboardInsuranceService class",e);
				}
				return null;
		  }
		public List<UserTypeVO> getIssueTypeMatchVO(List<UserTypeVO> statuVOList,String issueType){
			try{
				if(statuVOList == null || statuVOList.size() == 0)
					return null;
				for(UserTypeVO statusVO:statuVOList){
					if(statusVO.getName().equalsIgnoreCase(issueType)){
						return statusVO.getSubList();
					}
				}
			}catch(Exception e){
				LOG.error("Error occured at getStatusList() in CoreDashboardInsuranceService class",e);
			}
			return null;
	  }
		/*
		 * Santosh (non-Javadoc)
		 * @see com.itgrids.partyanalyst.service.ICoreDashboardInsuranceService#getSelectedChildMemberCadreInsuranceComplainctCnt(java.lang.Long, java.util.List, java.lang.String, java.lang.Long, java.lang.Long, java.lang.String, java.lang.String)
		 */
		@SuppressWarnings("unused")
		public List<UserTypeVO> getSelectedChildMemberCadreInsuranceComplainctCnt(Long parentActivityMemberId,List<Long> childUserTypeIds,String reportType,Long stateId,Long cadreEnrollmentYearId,String fromDateStr,String toDateStr){
			 List<UserTypeVO> resultList = new ArrayList<UserTypeVO>();
			 List<UserTypeVO> issueTypeList = new ArrayList<UserTypeVO>();
			 Map<String,UserTypeVO> complainctCntMap  = new HashMap<String, UserTypeVO>(0);
			 try {
				 SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
				 Date fromDate = null;
				 Date toDate = null;
				 if(fromDateStr != null && fromDateStr.length() > 0 && toDateStr != null && fromDateStr.length() > 0){
					fromDate = sdf.parse(fromDateStr);
					toDate = sdf.parse(toDateStr);
				 }
				  //Setting query input parameter
			     CadreInsuranceInputVO inputVO = new CadreInsuranceInputVO();
			     inputVO.setStateId(stateId);
			     inputVO.setFromDate(fromDate);
			     inputVO.setToDate(toDate);
			     inputVO.setEnrollmentYearId(cadreEnrollmentYearId);
			    
				 Map<Long,Set<Long>> locationLevelIdsMap=null;
				 Map<String,String>     nameForLocationMap=null;
				 ActivityMemberVO activityMemberVO=null;
				 Map<Long,UserTypeVO> childActivityMembersMap=null;
					  
					//calling generic method to get childActivityMembers and there location level and values
					  
						  if(reportType != null && reportType.equalsIgnoreCase("selectedUserType")){
							  activityMemberVO = coreDashboardGenericService.getRequiredSubLevelActivityMembersDetails(parentActivityMemberId,childUserTypeIds);
							  childActivityMembersMap= activityMemberVO.getActivityMembersMap();
							  locationLevelIdsMap= activityMemberVO.getLocationLevelIdsMap();
						  }else if(reportType != null && reportType.equalsIgnoreCase("directChild")){
							  if(childUserTypeIds != null && childUserTypeIds.size()>0){
								   activityMemberVO = coreDashboardGenericService.getDirectChildActivityMemberCommitteeDetails(parentActivityMemberId,childUserTypeIds.get(0));//activityMemerId,userTypeId
							  }
							   childActivityMembersMap = activityMemberVO.getActivityMembersMap();
							   locationLevelIdsMap = activityMemberVO.getLocationLevelIdsMap();
						  }
					  
						  if(locationLevelIdsMap != null && locationLevelIdsMap.size() > 0){
							  nameForLocationMap = coreDashboardGenericService.getLocationNamesByLocationIds(locationLevelIdsMap);
						  }
					  
					     //PrepareTemplate
					      List<String> rtrnissutTypeObj = insuranceStatusDAO.getAllIssueType(stateId);
					      List<Object[]> rtrnStatusObjLst = insuranceStatusDAO.getAllGrievanceInsuranceStatus();
					      //Prepare Template
					      prepareTemplate(rtrnissutTypeObj,rtrnStatusObjLst,issueTypeList);
					      //Setting Candidate Wise Template
					      settingCandiateWiseIssueTypeAndStatusDetails(childActivityMembersMap,issueTypeList);
					      
					if(locationLevelIdsMap != null && locationLevelIdsMap.size() > 0){
				    	 for(Entry<Long,Set<Long>> entry:locationLevelIdsMap.entrySet()){
				    		 inputVO.setUserAccessLevelId(entry.getKey());
				    		 inputVO.setUserAccessLevelValues(entry.getValue());
				    		 List<Object[]> rtrnComplaintCnt = insuranceStatusDAO.getLocationWiseComplaintCntBasedOnUserAccessLevel(inputVO);
				    		 setComplaintCount(entry.getKey(),rtrnComplaintCnt,complainctCntMap,issueTypeList);
				    
				    	  }
					    }
					 //pushing complaint and approved amount count
				      if(childActivityMembersMap != null && childActivityMembersMap.size() > 0){
				    	 
				    	      for(UserTypeVO vo:childActivityMembersMap.values()){
						    	  
						    	  for(Long locationValueId:vo.getLocationValuesSet()){
						    		  
						    		  String key = vo.getLocationLevelId()+"-"+locationValueId;
						    		  
						    		  if(complainctCntMap.get(key) != null){
						    			  
						    			  UserTypeVO issueTypeVO = complainctCntMap.get(key);
						    			  
						    				  if(issueTypeVO.getSubList() != null && issueTypeVO.getSubList().size() > 0){
						    					  
						    					  for(UserTypeVO issTypeVO:issueTypeVO.getSubList()){
						    						  
						    						  List<UserTypeVO> statuList = getIssueTypeMatchVO(vo.getSubList(),issTypeVO.getName());
						    						  
						    						  if(statuList != null && statuList.size() > 0){
						    							  
						    							  if(issTypeVO.getSubList() != null && issTypeVO.getSubList().size() > 0){
						    								  
						    								for(UserTypeVO statusVO:issTypeVO.getSubList()) {
						    									
						    									UserTypeVO matchVO = getStatusMatchVO(statuList, statusVO.getId());
						    									
						    									 if(matchVO != null){
						    										 
						    										 matchVO.setTotalCount(matchVO.getTotalCount()+statusVO.getTotalCount());
						    										 matchVO.setPositiveCount(matchVO.getPositiveCount()+statusVO.getPositiveCount());//we are pushing approved amount
						    									 }
						    								}
						    							  }
						    						  }
						    					  }
						    				  }
						    			  }
						    	  }
						      }
					  }

				   // setting location name for direct child 
				   if(childActivityMembersMap != null && childActivityMembersMap.size() > 0){
					      for(UserTypeVO vo:childActivityMembersMap.values()){
					    	  for(Long locationValueId:vo.getLocationValuesSet()){
					    		  String key = vo.getLocationLevelId()+"_"+locationValueId;
					    		  if(vo.getLocationName() == null || vo.getLocationName().isEmpty()){
					    			  vo.setLocationName(nameForLocationMap.get(key));
								   }else{
									   vo.setLocationName(vo.getLocationName()+","+ nameForLocationMap.get(key) );  
								   }
					    	  }
					      }
				    }
				   
				   // Calculate Total Complaint Count Candidate Wise
				     if(childActivityMembersMap != null && childActivityMembersMap.size() > 0){
				    	      for(UserTypeVO vo:childActivityMembersMap.values()){
						    	 if(vo.getSubList() != null && vo.getSubList().size() > 0){
						    		 for(UserTypeVO issueTypeVO:vo.getSubList()){
						    			 if(issueTypeVO.getSubList() != null && issueTypeVO.getSubList().size() >0l){
						    				 for(UserTypeVO statusVO:issueTypeVO.getSubList()){
						    					 vo.setPositiveCount(vo.getPositiveCount()+statusVO.getPositiveCount());
						    					 vo.setTotalCount(vo.getTotalCount()+statusVO.getTotalCount());//Over All Count candidate wise
						    					 issueTypeVO.setTotalCount(issueTypeVO.getTotalCount()+statusVO.getTotalCount());//Issue Type wise total count
						    					 issueTypeVO.setPositiveCount(issueTypeVO.getPositiveCount()+statusVO.getPositiveCount());
						    					
						    				 }
						    			 }
						    		 }
						    	 }
					   }
				   }
			 
			  if(childActivityMembersMap != null && childActivityMembersMap.size() > 0){
				  resultList.addAll(childActivityMembersMap.values());
			  }
			  
		    /*if(resultList != null && resultList.size() > 0)
			  {
				  Collections.sort(resultList, eventInviteeAttendedPercDesc);
			  }*/
			} catch (Exception e) {
				 LOG.error("Error occured at getSelectedChildMemberCadreInsuranceComplainctCnt() in CoreDashboardInsuranceService class",e);
			}
			 return resultList;
		 }
		public void settingCandiateWiseIssueTypeAndStatusDetails(Map<Long,UserTypeVO> userTypeMapDtls,List<UserTypeVO> issueTypeList){
			try{
				if(userTypeMapDtls != null && userTypeMapDtls.size() > 0){
					for(Entry<Long,UserTypeVO> candidateEntry:userTypeMapDtls.entrySet()){
						candidateEntry.getValue().setSubList(getIssueTypeLst(issueTypeList));
			         }
				}
			}catch(Exception e){
				LOG.error("Error occured at settingCandiateWiseIssueTypeAndStatusDetails() in CoreDashboardInsuranceService class",e);
			}
		}
}
