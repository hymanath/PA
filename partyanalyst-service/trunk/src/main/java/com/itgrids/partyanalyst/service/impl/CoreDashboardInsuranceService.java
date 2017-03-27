package com.itgrids.partyanalyst.service.impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
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
import com.itgrids.partyanalyst.service.ICoreDashboardInsuranceService;
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
						}
						else if(issueType != null && issueType.equalsIgnoreCase("Hospitalization")){
							companyvo.setHospitalizationCount(companyvo.getHospitalizationCount()+count);
							statusvo.setHospitalizationCount(statusvo.getHospitalizationCount()+count);
						}
						companyvo.setTotalCount(companyvo.getTotalCount()+count);
						statusvo.setTotalCount(statusvo.getTotalCount()+count);
					}
				}
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
					vo.setPostedDate(obj[13] != null ? obj[13].toString():"");
					
					returnList.add(vo);
				}
			}
			
		} catch (Exception e) {
			LOG.error("Exception Occured in getComplaintDetailsByComplaintIds on CoreDashboardInsuranceService", e);
		}
		return returnList;
	}
}
