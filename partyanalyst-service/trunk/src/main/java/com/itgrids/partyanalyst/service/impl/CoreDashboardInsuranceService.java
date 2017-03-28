package com.itgrids.partyanalyst.service.impl;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.log4j.Logger;

import com.itgrids.partyanalyst.dao.IInsuranceStatusDAO;
import com.itgrids.partyanalyst.dao.hibernate.ActivityMemberAccessLevelDAO;
import com.itgrids.partyanalyst.dto.ComplaintMasterVO;
import com.itgrids.partyanalyst.dto.CoreDashboardInsuranceVO;
import com.itgrids.partyanalyst.dto.InsuranceLagDaysVO;
import com.itgrids.partyanalyst.service.ICoreDashboardInsuranceService;
import com.itgrids.partyanalyst.utils.DateUtilService;
import com.itgrids.partyanalyst.utils.IConstants;

public class CoreDashboardInsuranceService implements ICoreDashboardInsuranceService{

	private final static Logger LOG = Logger.getLogger(CoreDashboardPartyMeetingService.class);

	private ActivityMemberAccessLevelDAO activityMemberAccessLevelDAO;
	private IInsuranceStatusDAO insuranceStatusDAO;
	
	
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
}
