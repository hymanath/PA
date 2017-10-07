package com.itgrids.partyanalyst.service.impl;

import java.io.File;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
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

import org.apache.log4j.Logger;

import com.itgrids.partyanalyst.dao.IConstituencyDAO;
import com.itgrids.partyanalyst.dao.IDelimitationConstituencyDAO;
import com.itgrids.partyanalyst.dao.IInsuranceStatusDAO;
import com.itgrids.partyanalyst.dao.hibernate.ActivityMemberAccessLevelDAO;
import com.itgrids.partyanalyst.dao.hibernate.ConstituencyDAO;
import com.itgrids.partyanalyst.dto.ActivityMemberVO;
import com.itgrids.partyanalyst.dto.CadreInsuranceInputVO;
import com.itgrids.partyanalyst.dto.ComplaintMasterVO;
import com.itgrids.partyanalyst.dto.ComplaintScanCopyVO;
import com.itgrids.partyanalyst.dto.CoreDashboardInsuranceVO;
import com.itgrids.partyanalyst.dto.InsuranceLagDaysVO;
import com.itgrids.partyanalyst.dto.InsuranceSimpleVO;
import com.itgrids.partyanalyst.dto.InsuranceStatusVO;
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
	private IConstituencyDAO constituencyDAO;
	private IDelimitationConstituencyDAO delimitationConstituencyDAO;
	
	
	public IDelimitationConstituencyDAO getDelimitationConstituencyDAO() {
		return delimitationConstituencyDAO;
	}
	public void setDelimitationConstituencyDAO(
			IDelimitationConstituencyDAO delimitationConstituencyDAO) {
		this.delimitationConstituencyDAO = delimitationConstituencyDAO;
	}
	public IConstituencyDAO getConstituencyDAO() {
		return constituencyDAO;
	}
	public void setConstituencyDAO(IConstituencyDAO constituencyDAO) {
		this.constituencyDAO = constituencyDAO;
	}
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
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
			SimpleDateFormat sdf1 = new SimpleDateFormat("MMM dd,yyyy hh:mm:ss a");
			
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
					vo.setPostedDate(obj[13] != null ? obj[13].toString() :null);
					if(vo.getPostedDate() != null){
						Date dated = sdf.parse(vo.getPostedDate());
						vo.setPostedDate(sdf1.format(dated));
					}
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
					String updatedDate = obj[2] != null ? obj[2].toString():null;
					
					ComplaintMasterVO vo = getComplaintMasterMatchedVO(returnList, complaintId);
					if(vo != null){
						vo.setComment(comment);
						vo.setUpdatedDate(updatedDate);
						if(vo.getUpdatedDate() != null){
							Date dated = sdf.parse(vo.getUpdatedDate());
							vo.setUpdatedDate(sdf1.format(dated));
						}
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
	
	//Complaint Details Starts
	
	public InsuranceSimpleVO getStatusTrackingDetailsOfInsuranceByComplaint(Long complaintId){
		
		InsuranceSimpleVO finalVO = new InsuranceSimpleVO(); 
		try{
			//Long complaintId =21337l;
			
			SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
			SimpleDateFormat sdf1 = new SimpleDateFormat("dd-MM-yyyy");
			
			Map<String,InsuranceSimpleVO> firstMap = new LinkedHashMap<String, InsuranceSimpleVO>(); 
			List<InsuranceSimpleVO> statusList=getGrievanceInsuranceStatusList(firstMap);
			
			//0.id,1.statusId,2.comment(status),3.inserted time,4.name
			List<Object[]> complaintDetails=insuranceStatusDAO.getAllStatusDetailsByComplaint(complaintId,"insurance");
			if(complaintDetails !=null && complaintDetails.size()>0){
				
				//boolean multiStatus=checkMultiStatusForInsurance(complaintDetails);
				boolean multiStatus = true;
				if(multiStatus){
					
					finalVO.setSimpleVOList2(new ArrayList<InsuranceSimpleVO>());
					for(int i=0;i<complaintDetails.size();i++){
						
						Object[] obj=complaintDetails.get(i);
						
						
						if((Long)obj[1]==1l){
							//first map
							String dateString = "";
							//SimpleVO notVerifiedVO=firstMap.get("Applied");
							InsuranceSimpleVO notVerifiedVO=firstMap.get("Waiting For Documents");
							if(obj[3] != null && !obj[3].toString().isEmpty()){
								Date datte =  sdf.parse(obj[3].toString());
								notVerifiedVO.setDateString(sdf1.format(datte));
								dateString = notVerifiedVO.getDateString();
							}
							//notVerifiedVO.setDateString(dateString);
							notVerifiedVO.setDate(obj[3] != null ? sdf.parse(obj[3].toString()):null);
							//total list
							InsuranceSimpleVO simpleVO=new InsuranceSimpleVO();
							//simpleVO.setName("Applied");
							simpleVO.setName("Waiting For Documents");
							simpleVO.setDate(obj[3] != null ? sdf.parse(obj[3].toString()):null);
							simpleVO.setDateString(dateString);
							simpleVO.setUsername(obj[4] !=null ? obj[4].toString():"");
							
							if(i==(complaintDetails.size()-1)){
								notVerifiedVO.setStatus("current");
								simpleVO.setStatus("current");
								//firstMap.get("Applied").setCurrent(obj[2].toString().toLowerCase());
								firstMap.get("Waiting For Documents").setCurrent(obj[2]!= null?obj[2].toString().toLowerCase():"WAITING FOR DOCUMENTS" );
								
							}
							finalVO.getSimpleVOList2().add(simpleVO);
						}else{
							//first map
							InsuranceSimpleVO VO=firstMap.get(obj[2].toString());
							if(VO == null)
								VO = new InsuranceSimpleVO();
							String dateString = "";
							if(obj[3] != null && !obj[3].toString().isEmpty()){
								Date datte =  sdf.parse(obj[3].toString());
								dateString = sdf1.format(datte);
							}
							VO.setDateString(dateString);
							VO.setDate(obj[3] != null ? sdf.parse(obj[3].toString()):null);
							
							//total list
							InsuranceSimpleVO simpleVO=new InsuranceSimpleVO();
							simpleVO.setName(obj[2].toString());
							simpleVO.setDate(obj[3] != null ? sdf.parse(obj[3].toString()):null);
							simpleVO.setDateString(dateString);
							simpleVO.setUsername(obj[4] !=null ? obj[4].toString():"");
							
							if(i==(complaintDetails.size()-1)){
								VO.setStatus("current");//to show current Status
								simpleVO.setStatus("current");//to show current Status
								//firstMap.get("Applied").setCurrent(obj[2].toString());
								firstMap.get("Waiting For Documents").setCurrent(obj[2]!= null?obj[2].toString().toLowerCase():"WAITING FOR DOCUMENTS" );
								
							}
							finalVO.getSimpleVOList2().add(simpleVO);
						}
					}
					finalVO.setSimpleVOList1(new ArrayList<InsuranceSimpleVO>(firstMap.values()));
					//diffreneceBetweenDates(finalVO.getSimpleVOList2());
				}
				else{
					
					for(int i=0;i<complaintDetails.size();i++){  
						Object[] obj=complaintDetails.get(i);
						
						if((Long)obj[1]==1l){
							//SimpleVO notVerifiedVO=firstMap.get("Applied");
							InsuranceSimpleVO notVerifiedVO=firstMap.get("Waiting For Documents");
							if(obj[3] != null && !obj[3].toString().isEmpty()){
								Date datte =  sdf.parse(obj[3].toString());
								notVerifiedVO.setDateString(sdf1.format(datte));
							}
							//notVerifiedVO.setDateString(sdf.format((Date)obj[3]));
							notVerifiedVO.setDate(obj[3] != null ? sdf.parse(obj[3].toString()):null);
							if(i==(complaintDetails.size()-1)){
								notVerifiedVO.setStatus("current");
								//firstMap.get("Applied").setCurrent("Applied");
								firstMap.get("Waiting For Documents").setCurrent("Waiting For Documents");
								
							}
						}else{
							InsuranceSimpleVO VO=firstMap.get(obj[2].toString());
							if(obj[3] != null && !obj[3].toString().isEmpty())
							{
								Date datte =  sdf.parse(obj[3].toString());
								VO.setDateString(sdf1.format(datte));
								VO.setDate(obj[3] != null ? sdf.parse(obj[3].toString()):null);
							}
							if(i==(complaintDetails.size()-1)){
								VO.setStatus("current");
								//firstMap.get("Applied").setCurrent(obj[2].toString());
								firstMap.get("Waiting For Documents").setCurrent(obj[2]!= null?obj[2].toString().toLowerCase():"WAITING FOR DOCUMENTS" );
							}
						}
					}
					finalVO.setSimpleVOList1(new ArrayList<InsuranceSimpleVO>(firstMap.values()));
				}
				
				
				List<InsuranceSimpleVO> finalDateVos=new ArrayList<InsuranceSimpleVO>();
				
				//Applied,Intimated Status Scenario
				if(finalVO.getSimpleVOList1().get(0).getCurrent().equalsIgnoreCase("Waiting For Documents") || finalVO.getSimpleVOList1().get(0).getCurrent().equalsIgnoreCase("Documents Submitted In Party") || finalVO.getSimpleVOList1().get(0).getCurrent().equalsIgnoreCase("Forwarded to Insurance") || finalVO.getSimpleVOList1().get(0).getCurrent().equalsIgnoreCase("Approved - Compensated")){
					for(int i=0;i<finalVO.getSimpleVOList1().size();i++){
						 if( finalVO.getSimpleVOList1().get(i).getName().equalsIgnoreCase("Waiting For Documents") ||finalVO.getSimpleVOList1().get(i).getName().equalsIgnoreCase("Documents Submitted In Party")|| finalVO.getSimpleVOList1().get(i).getName().equalsIgnoreCase("Forwarded to Insurance") || finalVO.getSimpleVOList1().get(i).getName().equalsIgnoreCase("Approved - Compensated")){
							 finalDateVos.add(finalVO.getSimpleVOList1().get(i));
						 }
					}
				}
				// Document Status Scenario
				else if(finalVO.getSimpleVOList1().get(0).getCurrent().equalsIgnoreCase("Waiting for Documents") || finalVO.getSimpleVOList1().get(0).getCurrent().equalsIgnoreCase("Documents Submitted In Party") || finalVO.getSimpleVOList1().get(0).getCurrent().equalsIgnoreCase("Forwarded to Insurance")){
					for(int i=0;i<finalVO.getSimpleVOList1().size();i++){
						
						if(finalVO.getSimpleVOList1().get(0).getCurrent().equalsIgnoreCase("Waiting for Documents")){
							 if( finalVO.getSimpleVOList1().get(i).getName().equalsIgnoreCase("Waiting for Documents") ||finalVO.getSimpleVOList1().get(i).getName().equalsIgnoreCase("Documents Submitted In Party")|| finalVO.getSimpleVOList1().get(i).getName().equalsIgnoreCase("Forwarded to Insurance") || finalVO.getSimpleVOList1().get(i).getName().equalsIgnoreCase("Approved - Compensated")){
								 finalDateVos.add(finalVO.getSimpleVOList1().get(i));
							 }
						}
						else if(finalVO.getSimpleVOList1().get(0).getCurrent().equalsIgnoreCase("Documents Submitted In Party")){
							 if( finalVO.getSimpleVOList1().get(i).getName().equalsIgnoreCase("Waiting for Documents") ||finalVO.getSimpleVOList1().get(i).getName().equalsIgnoreCase("Documents Submitted In Party")|| finalVO.getSimpleVOList1().get(i).getName().equalsIgnoreCase("Forwarded to Insurance") || finalVO.getSimpleVOList1().get(i).getName().equalsIgnoreCase("Approved - Compensated")){
								 finalDateVos.add(finalVO.getSimpleVOList1().get(i));
							 }
						}else{
							 if( finalVO.getSimpleVOList1().get(i).getName().equalsIgnoreCase("Waiting for Documents") ||finalVO.getSimpleVOList1().get(i).getName().equalsIgnoreCase("Documents Submitted In Party")|| finalVO.getSimpleVOList1().get(i).getName().equalsIgnoreCase("Forwarded to Insurance") || finalVO.getSimpleVOList1().get(i).getName().equalsIgnoreCase("Approved - Compensated")){
								 finalDateVos.add(finalVO.getSimpleVOList1().get(i));
							 }
						}
						
						/* if( finalVO.getSimpleVOList1().get(i).getName().equalsIgnoreCase("Applied") ||finalVO.getSimpleVOList1().get(i).getName().equalsIgnoreCase("Intimated")|| finalVO.getSimpleVOList1().get(i).getName().equalsIgnoreCase("Documents Submitted") || finalVO.getSimpleVOList1().get(i).getName().equalsIgnoreCase("Approved") || finalVO.getSimpleVOList1().get(i).getName().equalsIgnoreCase("Cheque Received") ){
							 finalDateVos.add(finalVO.getSimpleVOList1().get(i));
						 }*/
					}
				}
				
				//Rejected and Not Eligible
				else if(finalVO.getSimpleVOList1().get(0).getCurrent().equalsIgnoreCase("Rejected") || finalVO.getSimpleVOList1().get(0).getCurrent().equalsIgnoreCase("Not Eligible")){
					for(int i=0;i<finalVO.getSimpleVOList1().size();i++){
						
						if(finalVO.getSimpleVOList1().get(0).getCurrent().equalsIgnoreCase("Rejected")){
							if( finalVO.getSimpleVOList1().get(i).getName().equalsIgnoreCase("Waiting for Documents") ||finalVO.getSimpleVOList1().get(i).getName().equalsIgnoreCase("Documents Submitted In Party") || finalVO.getSimpleVOList1().get(i).getName().equalsIgnoreCase("Rejected")){
								 finalDateVos.add(finalVO.getSimpleVOList1().get(i));
							 }
						}else if(finalVO.getSimpleVOList1().get(0).getCurrent().equalsIgnoreCase("Not Eligible")){
							if( finalVO.getSimpleVOList1().get(i).getName().equalsIgnoreCase("Waiting for Documents") ||finalVO.getSimpleVOList1().get(i).getName().equalsIgnoreCase("Documents Submitted In Party") || finalVO.getSimpleVOList1().get(i).getName().equalsIgnoreCase("Not Eligible")){
								 finalDateVos.add(finalVO.getSimpleVOList1().get(i));
							 }
						}
						
					}
				}
				finalVO.setSimpleVOList1(finalDateVos);
				
				List<InsuranceSimpleVO> newStatusList = new ArrayList<InsuranceSimpleVO>();
				if(finalVO.getSimpleVOList1() !=null && finalVO.getSimpleVOList1().size()>0){
					
					
					int index = getIndexByCurrent(finalVO.getSimpleVOList1());
					
					for(int i=0;i<finalVO.getSimpleVOList1().size();i++){
						if(i<index){
							InsuranceSimpleVO vo = finalVO.getSimpleVOList1().get(i);
							if(vo.getDateString() !=null){
								newStatusList.add(vo);
							}
						}
						else{
							InsuranceSimpleVO vo1 = finalVO.getSimpleVOList1().get(i);
							newStatusList.add(vo1);
						}
					}
				}
				
				if(newStatusList !=null && newStatusList.size()>0){
					finalVO.setSimpleVOList1(newStatusList);
				}
				
				diffreneceBetweenDates(finalVO.getSimpleVOList1());
			
			}
			else{
				List<String> status=insuranceStatusDAO.getStatusBycomplaintIdForInsurance(complaintId);
				if(status!=null && status.size()>0){
					finalVO.setOnlystatus(status.get(0));
				}
			}
			
			
		return finalVO;
		
		}catch (Exception e) {
			LOG.error("Exception Occured in () getStatusTrackingDetailsOfInsuranceByComplaint method, Exception - ",e);
		}
		
		return finalVO;
	
	}
	
	public void diffreneceBetweenDates(List<InsuranceSimpleVO> list){
		try{
			for(int i=0;i<list.size();i++){
				
				Date d2=null;
				Date d1=null;
				if(list.get(i).getStatus()!=null &&list.get(i).getStatus().equalsIgnoreCase("current")){
					d2=new DateUtilService().getCurrentDateAndTime();  
					d1=list.get(i).getDate();
				}else{
					if(i!=(list.size()-1)){
						d2=list.get(i+1).getDate();
						d1=list.get(i).getDate();
					}
				}
				if(d2!=null && d1!=null){
				 long diff = d2.getTime() - d1.getTime();
				 long diffDays = diff / (24 * 60 * 60 * 1000);
				 list.get(i).setType(diffDays+" days");
				}
		}
		}catch(Exception e){
			LOG.error(" Exception Occured in diffreneceBetweenDates() method, Exception - ",e);
		}
	}
	
	public List<InsuranceSimpleVO> getGrievanceInsuranceStatusList(Map<String,InsuranceSimpleVO> insuranceStatusMap){
		
		List<InsuranceSimpleVO> finalList = new ArrayList<InsuranceSimpleVO>();
		try{
			
			List<Object[]> insurancestatusList = insuranceStatusDAO.getInsuranceStatus();
			if(insurancestatusList != null && insurancestatusList.size() > 0)
			{
				for(Object[] obj :insurancestatusList )
				{
					InsuranceSimpleVO vo = new InsuranceSimpleVO();
			    
			    vo.setId(obj[0] !=null ? (Long)obj[0]:0l);//statusId
				vo.setName(obj[1] !=null ?  obj[1].toString():"");//status
				
				insuranceStatusMap.put(vo.getName(),vo);
				finalList.add(vo);
				}
			}
			
			return finalList;
		}catch(Exception e){
			LOG.error("Exception Occured in () getGrievanceInsuranceStatusList method, Exception - ",e);
		}
		return finalList;
	}
	
	public int getIndexByCurrent(List<InsuranceSimpleVO> simpleVoList){
		
		int k=0;
		try{
			for(int i=0;i<simpleVoList.size();i++){
				InsuranceSimpleVO vo = simpleVoList.get(i);
				if(vo.getStatus() !=null && !vo.getStatus().isEmpty() && vo.getStatus().equalsIgnoreCase("current")){
					k=i;
				}
			}
			return k;
		}catch(Exception e){
			LOG.error("Exception Occured in () getIndexByCurrent method, Exception - ",e);
		}
		return k;
	}
	
	public ComplaintMasterVO getComplaintScanCopyDetails(Long complaintId){
		ComplaintMasterVO returnvo = new ComplaintMasterVO();
		try {
			List<Object[]> list = insuranceStatusDAO.getSubjectAndDescForComplaint(complaintId);
			if(list != null && !list.isEmpty()){
				Object[] obj = list.get(0);
				returnvo.setSubject(obj[0] != null ? obj[0].toString():"");
				returnvo.setDescription(obj[1] != null ? obj[1].toString():"");
			}
			
			returnvo.setScanCopyList(getComplaintScanCopies(complaintId));
			
		} catch (Exception e) {
			LOG.error("Exception Occured in () getComplaintScanCopyDetails method, Exception - ",e);
		}
		return returnvo;
	}
	
	public List<ComplaintScanCopyVO> getComplaintScanCopies(Long complaintId)
	{
		List<ComplaintScanCopyVO> files = new ArrayList<ComplaintScanCopyVO>();
		try{
			
			List<Object[]> list = insuranceStatusDAO.getComplaintScanCopies(complaintId);
			if(list != null && list.size() > 0)
			{
				for(Object[] params : list)
				{
					if(params[1] != null)
					{
					File f = new File(""+IConstants.STATIC_CONTENT_FOLDER_URL+"complaintScannedCopy/"+params[1].toString());
						if(f.exists())
						{
							ComplaintScanCopyVO vo = new ComplaintScanCopyVO();
							vo.setPath(params[1] != null ? params[1].toString() : "");
							vo.setScanCopyId(params[0] != null ? (Long)params[0] : 0l);
							vo.setNewCopy(true);
							if(params[2]!=null){
								String dateString=((Date)params[2]).toString();
								if(dateString.length() > 18){
									vo.setDateString(new DateUtilService().convert12HoursDateFormat(dateString.substring(0, 18)));
								 }
							}
							
							files.add(vo);
						}
					}
				}
			}
			else
			{
				List list1 = insuranceStatusDAO.getScanCopyForComplaint(complaintId);
				if(list1 != null && list1.size() > 0)
				{
					if(list1.get(0) != null)
					{
							File f = new File(""+IConstants.STATIC_CONTENT_FOLDER_URL+"complaintScannedCopy/old/"+list1.get(0).toString());
							if(f.exists())
							{
								ComplaintScanCopyVO vo = new ComplaintScanCopyVO();
								vo.setPath(list1.get(0) != null ? list1.get(0).toString() : "");
								vo.setNewCopy(false);
								files.add(vo);	
							}
					}
				}
			}
		}
		catch(Exception e)
		{
			LOG.error("Exception Occured in getComplaitnScanCopies() method, Exception - ",e);
		}
		return files;
	}
	
	public CoreDashboardInsuranceVO getRemarksByComplaint(Long complaitnId)
	 {
		CoreDashboardInsuranceVO returnVo = new CoreDashboardInsuranceVO();
		 List<CoreDashboardInsuranceVO> voList = new ArrayList<CoreDashboardInsuranceVO>();
		 DateUtilService date = new DateUtilService();
		 returnVo.setSubList(voList);
		 try{
			List<Object[]> list = insuranceStatusDAO.getRemarks(complaitnId);
			
			if(list != null && list.size() > 0)
			{
				for(Object[] params : list)
				{
					CoreDashboardInsuranceVO vo = new CoreDashboardInsuranceVO();
					String remarks =params[0] != null ? params[0].toString() : ""; 
					vo.setRemarks(remarks);
					vo.setName(params[1] != null ? params[1].toString() : "");
					if(params[2] != null)
					{
						String time = params[2].toString();
						 if(time.length() > 20)
						vo.setUpdatedTime(date.convert12HoursDateFormat(time.substring(0, 19)));
					}
					voList.add(vo);
				}
			}
		 }
		 catch (Exception e) {
			 LOG.error("Exception Occured in getRemarksByComplaint() method, Exception - ",e);
		}
		return returnVo;
	 }
	
	public CoreDashboardInsuranceVO getComplaintResponsesByComplaint(Long complaintId)
	 {
		CoreDashboardInsuranceVO returnVo = new CoreDashboardInsuranceVO();
		 try{
			 List<Object[]> conversationDetails = insuranceStatusDAO.getComplaintResponsesByComplaintId(complaintId);
			 DateUtilService date = new DateUtilService();
			 List<CoreDashboardInsuranceVO> voList = new ArrayList<CoreDashboardInsuranceVO>();
			 if(conversationDetails != null && conversationDetails.size() > 0){
				 for(Object[] obj : conversationDetails){
					 CoreDashboardInsuranceVO vo = new CoreDashboardInsuranceVO();
					 vo.setSubject(obj[0] != null?obj[0].toString():"");
					 vo.setName(obj[1] != null?obj[1].toString():"");
					 if(obj[2] != null)
						{
							 String time = obj[2].toString();
							 if(time.length() > 20)
							 vo.setRaisedDate(date.convert12HoursDateFormat(time.substring(0, 19)));
						}
					 vo.setImage(obj[3] != null?obj[3].toString():"");
					 voList.add(vo);
				 }
			 }
			 returnVo.setSubList(voList);
		 }
		 catch (Exception e) {
			 LOG.error("Exception Occured in getComplaintResponsesByComplaint() method, Exception - ",e);
		}
		return returnVo;
	 }
	
	//Complaint Details Ends
	
	public List<CoreDashboardInsuranceVO> getInsuranceCompanyWiseOverviewAndStatusDetails(Long activityMemberId,Long cadreYearId,String fromDateStr,String toDateStr){
		List<CoreDashboardInsuranceVO> returnList = new ArrayList<CoreDashboardInsuranceVO>();
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
			List<Object[]> rtrnUsrAccssLvlIdAndVlusObjLst=activityMemberAccessLevelDAO.getLocationLevelAndValuesByActivityMembersId(activityMemberId);
		    if(rtrnUsrAccssLvlIdAndVlusObjLst != null && !rtrnUsrAccssLvlIdAndVlusObjLst.isEmpty()){
			   for (Object[] obj : rtrnUsrAccssLvlIdAndVlusObjLst) {
				   locationId = Long.valueOf(obj[0] != null ? obj[0].toString():"0");
				   locationValuesSet.add(Long.valueOf(obj[1] != null ? obj[1].toString():"0"));
			   }
		    }
		    
		    List<Object[]> companyList = insuranceStatusDAO.getAllInsuranceCompanies();
		    String statusStr = IConstants.CORE_DASHBOARD_INSURANCE_STATUS;
			String[] statusTemplate = statusStr.split(",");
		    
			CoreDashboardInsuranceVO apvo = new CoreDashboardInsuranceVO();
		    setInsuranceCompaniesListsTemplate(companyList, apvo);
		    setStatusTemplateList(statusTemplate, apvo);
			
		    List<Object[]> apList = insuranceStatusDAO.getStatusAndInsuranceCompanyWiseComplaints(locationId, locationValuesSet, 1l, cadreYearId, fromDate, toDate);
		    if(apList != null && !apList.isEmpty()){
		    	for (Object[] obj : apList) {
		    		Long statusId = Long.valueOf(obj[0] != null ? obj[0].toString():"0");
					Long companyId = Long.valueOf(obj[1] != null ? obj[1].toString():"0");
					String issueType = obj[3] != null ? obj[3].toString():"";
					Long count = Long.valueOf(obj[4] != null ? obj[4].toString():"0");
					Long amount = Long.valueOf(obj[5] != null ? obj[5].toString():"0");
					
					String status = "";
					if(statusId == 1l || statusId == 2l)
						status = "INTIMATIONS";
					else if(statusId == 3l || statusId == 7l)
						status = "FORWARDED";
					else if(statusId == 6l || statusId == 8l)
						status = "SETTLED";
					else if(statusId == 4l || statusId == 5l)
						status = "REJECTED";
					
					CoreDashboardInsuranceVO overAllCmpnyvo = getMatchVO(apvo.getOverViewList(), companyId);
					if(issueType != null && issueType.equalsIgnoreCase("Death")){
						CoreDashboardInsuranceVO deathCmpnyvo = getMatchVO(apvo.getDeathList(), companyId);
						overAllCmpnyvo.setDeathCount(overAllCmpnyvo.getDeathCount()+count);
						deathCmpnyvo.setTotalCount(deathCmpnyvo.getTotalCount()+count);
						//overAllCmpnyvo.setAmount(overAllCmpnyvo.getAmount()+amount);
						deathCmpnyvo.setAmount(deathCmpnyvo.getAmount()+amount);
						
						CoreDashboardInsuranceVO statusvo = getMatchedVOByStatusStr(deathCmpnyvo.getSubList(), status);
						statusvo.setCount(statusvo.getCount()+count);
					}
					else if(issueType != null && issueType.equalsIgnoreCase("Hospitalization")){
						CoreDashboardInsuranceVO hsptlCmpnyvo = getMatchVO(apvo.getHospitalizationList(), companyId);
						overAllCmpnyvo.setHospitalizationCount(overAllCmpnyvo.getHospitalizationCount()+count);
						hsptlCmpnyvo.setTotalCount(hsptlCmpnyvo.getTotalCount()+count);
						//overAllCmpnyvo.setAmount(overAllCmpnyvo.getAmount()+amount);
						hsptlCmpnyvo.setAmount(hsptlCmpnyvo.getAmount()+amount);
						
						CoreDashboardInsuranceVO statusvo = getMatchedVOByStatusStr(hsptlCmpnyvo.getSubList(), status);
						statusvo.setCount(statusvo.getCount()+count);
					}
					overAllCmpnyvo.setTotalCount(overAllCmpnyvo.getTotalCount()+count);
					overAllCmpnyvo.setAmount(overAllCmpnyvo.getAmount()+amount);
				}
		    }
		    
		    CoreDashboardInsuranceVO tsvo = new CoreDashboardInsuranceVO();
		    setInsuranceCompaniesListsTemplate(companyList, tsvo);
		    setStatusTemplateList(statusTemplate, tsvo);
			
		    List<Object[]> tsList = insuranceStatusDAO.getStatusAndInsuranceCompanyWiseComplaints(0l, null, 2l, cadreYearId, fromDate, toDate);
		    if(tsList != null && !tsList.isEmpty()){
		    	for (Object[] obj : tsList) {
		    		Long statusId = Long.valueOf(obj[0] != null ? obj[0].toString():"0");
					Long companyId = Long.valueOf(obj[1] != null ? obj[1].toString():"0");
					String issueType = obj[3] != null ? obj[3].toString():"";
					Long count = Long.valueOf(obj[4] != null ? obj[4].toString():"0");
					Long amount = Long.valueOf(obj[5] != null ? obj[5].toString():"0");
					
					String status = "";
					if(statusId == 1l || statusId == 2l)
						status = "INTIMATIONS";
					else if(statusId == 3l || statusId == 7l)
						status = "FORWARDED";
					else if(statusId == 6l || statusId == 8l)
						status = "SETTLED";
					else if(statusId == 4l || statusId == 5l)
						status = "REJECTED";
					
					CoreDashboardInsuranceVO overAllCmpnyvo = getMatchVO(tsvo.getOverViewList(), companyId);
					if(issueType != null && issueType.equalsIgnoreCase("Death")){
						CoreDashboardInsuranceVO deathCmpnyvo = getMatchVO(tsvo.getDeathList(), companyId);
						overAllCmpnyvo.setDeathCount(overAllCmpnyvo.getDeathCount()+count);
						deathCmpnyvo.setTotalCount(deathCmpnyvo.getTotalCount()+count);
						deathCmpnyvo.setAmount(deathCmpnyvo.getAmount()+amount);
						
						CoreDashboardInsuranceVO statusvo = getMatchedVOByStatusStr(deathCmpnyvo.getSubList(), status);
						statusvo.setCount(statusvo.getCount()+count);
					}
					else if(issueType != null && issueType.equalsIgnoreCase("Hospitalization")){
						CoreDashboardInsuranceVO hsptlCmpnyvo = getMatchVO(tsvo.getHospitalizationList(), companyId);
						overAllCmpnyvo.setHospitalizationCount(overAllCmpnyvo.getHospitalizationCount()+count);
						hsptlCmpnyvo.setTotalCount(hsptlCmpnyvo.getTotalCount()+count);
						hsptlCmpnyvo.setAmount(hsptlCmpnyvo.getAmount()+amount);
						
						CoreDashboardInsuranceVO statusvo = getMatchedVOByStatusStr(hsptlCmpnyvo.getSubList(), status);
						statusvo.setCount(statusvo.getCount()+count);
					}
					overAllCmpnyvo.setTotalCount(overAllCmpnyvo.getTotalCount()+count);
					overAllCmpnyvo.setAmount(overAllCmpnyvo.getAmount()+amount);
				}
		    }
		    
		    returnList.add(apvo);
		    returnList.add(tsvo);
		    
		} catch (Exception e) {
			LOG.error("Exception Occured in getInsuranceCompanyWiseOverviewAndStatusDetails() method, Exception - ",e);
		}
		return returnList;
	}
	
	public void setInsuranceCompaniesListsTemplate(List<Object[]> list,CoreDashboardInsuranceVO statevo){
		try {
			if(list != null && !list.isEmpty()){
				for (Object[] obj : list) {
					CoreDashboardInsuranceVO vo = new CoreDashboardInsuranceVO();
					vo.setId(Long.valueOf(obj[0] != null ? obj[0].toString():"0"));
					vo.setName(obj[1] != null ? obj[1].toString():"");
					statevo.getOverViewList().add(vo);
				}
				for (Object[] obj : list) {
					CoreDashboardInsuranceVO vo = new CoreDashboardInsuranceVO();
					vo.setId(Long.valueOf(obj[0] != null ? obj[0].toString():"0"));
					vo.setName(obj[1] != null ? obj[1].toString():"");
					statevo.getDeathList().add(vo);
				}
				for (Object[] obj : list) {
					CoreDashboardInsuranceVO vo = new CoreDashboardInsuranceVO();
					vo.setId(Long.valueOf(obj[0] != null ? obj[0].toString():"0"));
					vo.setName(obj[1] != null ? obj[1].toString():"");
					statevo.getHospitalizationList().add(vo);
				}
			}
		} catch (Exception e) {
			LOG.error("Exception Occured in setInsuranceCompaniesList on CoreDashboardInsuranceService", e);
		}
	}
	
	public void setStatusTemplateList(String[] statusArr,CoreDashboardInsuranceVO apvo){
		try {
			if(apvo.getDeathList() != null && !apvo.getDeathList().isEmpty()){
				for (CoreDashboardInsuranceVO companyvo : apvo.getDeathList()) {
					if(statusArr != null && statusArr.length > 0){
						for (String status : statusArr) {
							CoreDashboardInsuranceVO vo = new CoreDashboardInsuranceVO();
							vo.setName(status);
							companyvo.getSubList().add(vo);
						}
					}
				}
			}
			if(apvo.getHospitalizationList() != null && !apvo.getHospitalizationList().isEmpty()){
				for (CoreDashboardInsuranceVO companyvo : apvo.getHospitalizationList()) {
					if(statusArr != null && statusArr.length > 0){
						for (String status : statusArr) {
							CoreDashboardInsuranceVO vo = new CoreDashboardInsuranceVO();
							vo.setName(status);
							companyvo.getSubList().add(vo);
						}
					}
				}
			}
		} catch (Exception e) {
			LOG.error("Exception Occured in setStatusTemplateList on CoreDashboardInsuranceService", e);
		}
	}
	
	public CoreDashboardInsuranceVO getMatchedVOByStatusStr(List<CoreDashboardInsuranceVO> list,String status){
		if(list == null || list.isEmpty())
			return null;
		for(CoreDashboardInsuranceVO vo:list){
			 if(vo.getName().equalsIgnoreCase(status)){
				 return vo;
			 }
		}
		return null;
	}
	
	/**
	* @param  Long activityMemberId
	* @param  Long userId
	* @param  Long userTypeId
	* @param Long stateId
	* @param cadreEnrollmentYearId
	* @param String fromDateStr
	* @param String toDateStr
	* @return  List<List<UserTypeVO>>
	* @author Santosh 
	* @Description :This Service Method is used to get top5 strong or top5 poor members cadre complaint count wise based on candidate access location. 
	*  @since 28-MARCH-2017
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
			    		 setComplaintCount(entry.getKey(),rtrnComplaintCnt,complainctCntMap,issueTypeList,"complaintCnt");
			    		
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
					    					 vo.setTotalCount(vo.getTotalCount()+statusVO.getTotalCount());//Over all complaint count candidate wise
					    					 issueTypeVO.setTotalCount(issueTypeVO.getTotalCount()+statusVO.getTotalCount());//Issue type wise total complaint count
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
				if(statusList != null && statusList.size() > 0){
					Collections.sort(statusList, new Comparator<UserTypeVO>() {
						
						public int compare(UserTypeVO status1, UserTypeVO status2) {
							return status1.getId().compareTo(status2.getId());
						}
					});
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
		 public void setComplaintCount(Long userAccessLevelId,List<Object[]> objList,Map<String,UserTypeVO> locationWisecomplaintMap,List<UserTypeVO> issueTypeList,String type){
			 try{
				 if(objList != null && objList.size() > 0){
					 for(Object[] param:objList){
						 String locationLevelAndId = userAccessLevelId+"-"+commonMethodsUtilService.getStringValueForObject(param[0]);
						 UserTypeVO locationVO = locationWisecomplaintMap.get(locationLevelAndId);
						  if(locationVO == null){
							  locationVO = new UserTypeVO();
							  locationVO.setId(commonMethodsUtilService.getLongValueForObject(param[0]));
							  locationVO.setName(commonMethodsUtilService.getStringValueForObject(param[1]));
							  locationVO.setSubList(getIssueTypeLst(issueTypeList));
							  locationWisecomplaintMap.put(locationLevelAndId,locationVO);
						  }
						 String issueType = commonMethodsUtilService.getStringValueForObject(param[2]);
						 Long statusId = commonMethodsUtilService.getLongValueForObject(param[3]);
						 Long complaintCnt = commonMethodsUtilService.getLongValueForObject(param[4]);
						 Long approvedAmt = 0l;
						 if(type.equalsIgnoreCase("complaintCnt")){
							  approvedAmt = commonMethodsUtilService.getLongValueForObject(param[5]);	 
						 }
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
								  if(type.equalsIgnoreCase("complaintCnt")){
									  statusMatchVO.setTotalCount(statusMatchVO.getTotalCount()+complaintCnt);//complaint count
									  statusMatchVO.setPositiveCount(statusMatchVO.getPositiveCount()+approvedAmt);//approved amount
								  }else if(type.equalsIgnoreCase("benefitCnt")){
									   statusMatchVO.setNegativeCount(statusMatchVO.getNegativeCount()+complaintCnt);//benefit member count
								  }
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
		/**
		* @param  Long parentActivityMemberId
		* @param  List<Long> childUserTypeIds
		* @param  String reportType
		* @param Long stateId
		* @param cadreEnrollmentYearId
		* @param String fromDateStr
		* @param String toDateStr
		* @return  List<UserTypeVO>
		* @author Santosh 
		* @Description :This Service Method is used to get selected child member and direct child member cadre complaint details based on selection. 
		*  @since 29-MARCH-2017
		*/
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
					    		 setComplaintCount(entry.getKey(),rtrnComplaintCnt,complainctCntMap,issueTypeList,"complaintCnt");
					    
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
							    										 
							    										 matchVO.setTotalCount(matchVO.getTotalCount()+statusVO.getTotalCount());//pushing complaint count
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
					  if(resultList != null && resultList.size() > 0){
						 Collections.sort(resultList, cadreSortingByComplaintCount);
					  }
			 } catch (Exception e) {
					 LOG.error("Error occured at getSelectedChildMemberCadreInsuranceComplainctCnt() in CoreDashboardInsuranceService class",e);
			}
		return resultList;
	  }
	   public static Comparator<UserTypeVO> cadreSortingByComplaintCount = new Comparator<UserTypeVO>() {
			public int compare(UserTypeVO member2, UserTypeVO member1) {
				Long count2 = member2.getTotalCount();
				Long count1 = member1.getTotalCount();
				//descending order of percantages.
				 return count1.compareTo(count2);
			}
		};
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
		/**
		* @param  Long activityMemberId
		* @param Long stateId
		* @param cadreEnrollmentYearId
		* @param String fromDateStr
		* @param String toDateStr
		* @return  List<UserTypeVO>
		* @author Santosh 
		* @Description :This Service Method is used to get candidate wise cadre complaint details. 
		*  @since 29-MARCH-2017
		*/
		public List<UserTypeVO> getCandiateWiseCadreInsurencaeDtls(Long activityMemberId,Long stateId,Long cadreEnrollmentYearId,String fromDateStr,String toDateStr){
			 List<UserTypeVO> resultList = new ArrayList<UserTypeVO>();
			 List<UserTypeVO> issueTypeList = new ArrayList<UserTypeVO>();
			 Long locationAccessLevelId = 0l;
			 Set<Long> locationValues = new HashSet<Long>(0);
			 Map<String,UserTypeVO> complainctCntMap  = new HashMap<String, UserTypeVO>(0);
			 try {
				 SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
				 Date fromDate = null;
				 Date toDate = null;
				 if(fromDateStr != null && fromDateStr.length() > 0 && toDateStr != null && fromDateStr.length() > 0){
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
				  //Setting query input parameter
			     CadreInsuranceInputVO inputVO = new CadreInsuranceInputVO();
			     inputVO.setStateId(stateId);
			     inputVO.setFromDate(fromDate);
			     inputVO.setToDate(toDate);
			     inputVO.setEnrollmentYearId(cadreEnrollmentYearId);
			     inputVO.setActivityMemerId(activityMemberId);
			     inputVO.setUserAccessLevelId(locationAccessLevelId);
			     inputVO.setUserAccessLevelValues(locationValues);
			     //PrepareTemplate
			      List<String> rtrnissutTypeObj = insuranceStatusDAO.getAllIssueType(stateId);
			      List<Object[]> rtrnStatusObjLst = insuranceStatusDAO.getAllGrievanceInsuranceStatus();
			      //Prepare Template
			      prepareTemplate(rtrnissutTypeObj,rtrnStatusObjLst,issueTypeList);
			      List<Object[]> rtrnComplaintCntObjLst = insuranceStatusDAO.getLocationWiseComplaintAndBenefitMemberCntBasedOnUserAccessLevel(inputVO, "complaintCnt");
			      List<Object[]> rtrnBenefitCntObjLst = insuranceStatusDAO.getLocationWiseComplaintAndBenefitMemberCntBasedOnUserAccessLevel(inputVO, "BenefitCnt");
			      setComplaintCount(locationAccessLevelId,rtrnComplaintCntObjLst,complainctCntMap,issueTypeList,"complaintCnt");
			      setComplaintCount(locationAccessLevelId,rtrnBenefitCntObjLst,complainctCntMap,issueTypeList,"benefitCnt");
			      //Calculate over all details candidate wise
				     if(complainctCntMap != null && complainctCntMap.size() > 0){
				    	      for(UserTypeVO vo:complainctCntMap.values()){
						    	 if(vo.getSubList() != null && vo.getSubList().size() > 0){
						    		 for(UserTypeVO issueTypeVO:vo.getSubList()){
						    			 if(issueTypeVO.getSubList() != null && issueTypeVO.getSubList().size() >0l){
						    				 for(UserTypeVO statusVO:issueTypeVO.getSubList()){
						    					 vo.setPositiveCount(vo.getPositiveCount()+statusVO.getPositiveCount());//Over all approved amount location wise 
						    					 vo.setNegativeCount(vo.getNegativeCount()+statusVO.getNegativeCount());//Over all benefit candidate count 
						    					 vo.setTotalCount(vo.getTotalCount()+statusVO.getTotalCount());//Over all complaint Count location  wise
						    					 issueTypeVO.setTotalCount(issueTypeVO.getTotalCount()+statusVO.getTotalCount());//Issue Type wise total count
						    					 issueTypeVO.setPositiveCount(issueTypeVO.getPositiveCount()+statusVO.getPositiveCount());//issue type wise total approved amount
						    					 issueTypeVO.setNegativeCount(issueTypeVO.getNegativeCount()+statusVO.getNegativeCount());//issue type wise benefit candidate count
						    				 }
						    			 }
						    		 }
						    	 }
					   }
				   }
				  if(complainctCntMap != null && complainctCntMap.size() > 0){
					  resultList = new ArrayList<UserTypeVO>(complainctCntMap.values());
				  }
				  if(resultList != null && resultList.size() > 0){
						 Collections.sort(resultList, cadreSortingByComplaintCount);
				  }
			} catch (Exception e) {
				 LOG.error("Error occured at getCandiateWiseCadreInsurencaeDtls() in CoreDashboardInsuranceService class",e);
			}
			 return resultList;
	}
		/*
		 * Swadhin K Lenka
		 * @see com.itgrids.partyanalyst.service.ICoreDashboardInsuranceService#getDistrictWiseThenCategoryWiseInsuranceMemberCount(java.lang.Long, java.lang.Long, java.lang.Long, java.lang.Long, java.lang.Long, java.lang.String, java.lang.String, java.lang.String)
		 */
		public List<CoreDashboardInsuranceVO> getDistrictWiseThenCategoryWiseInsuranceMemberCount(Long activityMemberId,Long userTypeId,Long stateId,Long cadreEnrollmentYearId,Long districtId, String status, String category, String fromDateStr,String toDateStr,String sortingCondition,String order) {
			try{
				List<CoreDashboardInsuranceVO> coreDashboardInsuranceVOs = new ArrayList<CoreDashboardInsuranceVO>();
				SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
				Date fromDate = null;
				Date toDate = null;
				if(fromDateStr != null && fromDateStr.length() > 0 && toDateStr != null && fromDateStr.length() > 0){
					fromDate = sdf.parse(fromDateStr);
					toDate = sdf.parse(toDateStr);
				}
				Set<Long> locationValuesSet = new java.util.HashSet<Long>();  
				Long levelId = 0L;
				List<Object[]> rtrnUsrAccssLvlIdAndVlusObjLst=activityMemberAccessLevelDAO.getLocationLevelAndValuesByActivityMembersId(activityMemberId);
			    if(rtrnUsrAccssLvlIdAndVlusObjLst != null && !rtrnUsrAccssLvlIdAndVlusObjLst.isEmpty()){
				   for (Object[] param : rtrnUsrAccssLvlIdAndVlusObjLst) {
					   levelId = commonMethodsUtilService.getLongValueForObject(param[0]);
					   locationValuesSet.add(param[1] != null ? (Long)param[1]:0l);
				   }
			    }
			    //create custom status list in service and send to query
			    List<Long> statusIdList = new ArrayList<Long>();
				if(status != null && status.trim().length() > 0 && status.trim().equalsIgnoreCase("INTIMATIONS")){
					statusIdList.add(1l);
					statusIdList.add(2l);
				}else if(status != null && status.trim().length() > 0 && status.trim().equalsIgnoreCase("FORWARDED")){
					statusIdList.add(3l);
					statusIdList.add(7l);
				}else if(status != null && status.trim().length() > 0 && status.trim().equalsIgnoreCase("SETTLED")){
					statusIdList.add(6l);
					statusIdList.add(8l);
				}else if(status != null && status.trim().length() > 0 && status.trim().equalsIgnoreCase("REJECTED")){
					statusIdList.add(4l);
					statusIdList.add(5l);
				}
			    
				  
				   
				List<Object[]> list1 = null;
				List<Object[]> list2 = null;
				if(levelId != null && levelId.longValue() == 4L){
					list1 = insuranceStatusDAO.getDistrictWiseThenCategoryWiseInsuranceMemberCount(levelId, locationValuesSet, userTypeId, stateId, cadreEnrollmentYearId, districtId, statusIdList, category, fromDate, toDate,"category","all");
					list2 = insuranceStatusDAO.getDistrictWiseThenCategoryWiseInsuranceMemberCount(levelId, locationValuesSet, userTypeId, stateId, cadreEnrollmentYearId, districtId, statusIdList, category, fromDate, toDate,"category","filter");
					if(list1 != null && list1.size() > 0){
						List<Long> constIdList = new ArrayList<Long>();
						for(Object[] param : list1){
							constIdList.add(param[0] != null ? (Long)param[0] : 0L);
						}
						
						List<Object[]> constIdAndNameList = constituencyDAO.getConstituencyByConstituencyIds(constIdList);
						Map<Long,String> idNameMap = new HashMap<Long,String>();
						for(Object[] param : constIdAndNameList){
							idNameMap.put(param[0] != null ? (Long)param[0] : 0L, param[1] != null ? param[1].toString() : "");
						}
						for(Object[] param: list1){
							param[1] = idNameMap.get(param[0] != null ? (Long)param[0] : 0L);
						}
						
					}
				}else{
					list1 = insuranceStatusDAO.getDistrictWiseThenCategoryWiseInsuranceMemberCount(levelId, locationValuesSet, userTypeId, stateId, cadreEnrollmentYearId, districtId, statusIdList, category, fromDate, toDate,"category","all");
					list2 = insuranceStatusDAO.getDistrictWiseThenCategoryWiseInsuranceMemberCount(levelId, locationValuesSet, userTypeId, stateId, cadreEnrollmentYearId, districtId, statusIdList, category, fromDate, toDate,"category","filter");
				}
				
				//create map of id and name
				Map<Long,String> idAndNameMap = new HashMap<Long,String>();
				//id and category and count map all
				Map<Long,Map<String,Long>> locIdAndCategoryAndCountMap = new HashMap<Long,Map<String,Long>>();
				Map<String,Long> categoryAndCountMap = null;
				
				//id and category and count map Filter
				Map<Long,Map<String,Long>> locIdAndCategoryAndCountMapFilter = new HashMap<Long,Map<String,Long>>();
				Map<String,Long> categoryAndCountMapFilter = null;
				
				//id and category and amount map
				Map<Long,Map<String,Long>> locIdAndCategoryAndAmountMap = new HashMap<Long,Map<String,Long>>();
				Map<String,Long> categoryAndAmountMap = null;
				
				
				if(list1 != null && list1.size() > 0){
					for(Object[] param : list1){
						//create map if id and name
						idAndNameMap.put(param[0] != null ? (Long)param[0] : 0L, param[1] != null ? param[1].toString().trim() : "");
						
						//id and category and count map
						categoryAndCountMap = locIdAndCategoryAndCountMap.get(param[0] != null ? (Long)param[0] : 0L);
						if(categoryAndCountMap ==  null){
							categoryAndCountMap = new HashMap<String,Long>();
							locIdAndCategoryAndCountMap.put(param[0] != null ? (Long)param[0] : 0L, categoryAndCountMap);
						}
						categoryAndCountMap.put(param[2] != null ? param[2].toString().trim() : "", param[3] != null ? (Long)param[3] : 0L);
						
					}
				}
				if(list2 != null && list2.size() > 0){
					for(Object[] param : list2){
						//id and category and count map filter
						categoryAndCountMapFilter = locIdAndCategoryAndCountMapFilter.get(param[0] != null ? (Long)param[0] : 0L);
						if(categoryAndCountMapFilter ==  null){
							categoryAndCountMapFilter = new HashMap<String,Long>();
							locIdAndCategoryAndCountMapFilter.put(param[0] != null ? (Long)param[0] : 0L, categoryAndCountMapFilter);
						}
						categoryAndCountMapFilter.put(param[2] != null ? param[2].toString().trim() : "", param[3] != null ? (Long)param[3] : 0L);
						
						//id and category and amount map
						categoryAndAmountMap = locIdAndCategoryAndAmountMap.get(param[0] != null ? (Long)param[0] : 0L);
						if(categoryAndAmountMap == null){
							categoryAndAmountMap = new HashMap<String,Long>();
							locIdAndCategoryAndAmountMap.put(param[0] != null ? (Long)param[0] : 0L, categoryAndAmountMap);
						}
						categoryAndAmountMap.put(param[2] != null ? param[2].toString().trim() : "", param[4] != null ? (Long)param[4] : 0L);
					}
				}
				
				
				//prepare vo object
				//CoreDashboardInsuranceVO coreDashboardInsuranceVO = null;
				if(idAndNameMap != null && idAndNameMap.size() > 0){
					for(Entry<Long,String> entry : idAndNameMap.entrySet()){
						CoreDashboardInsuranceVO insuranceVO = new CoreDashboardInsuranceVO();
						insuranceVO.setId(entry.getKey());
						insuranceVO.setName(entry.getValue());
						Long totalCount = 0L;
						if(locIdAndCategoryAndCountMap.get(entry.getKey()) != null && locIdAndCategoryAndCountMap.get(entry.getKey()).get("Death") != null){
							insuranceVO.setDeathCount(locIdAndCategoryAndCountMap.get(entry.getKey()).get("Death"));
							totalCount+=locIdAndCategoryAndCountMap.get(entry.getKey()).get("Death");
						}
						if(locIdAndCategoryAndCountMap.get(entry.getKey()) != null && locIdAndCategoryAndCountMap.get(entry.getKey()).get("Hospitalization") != null){
							insuranceVO.setHospitalizationCount(locIdAndCategoryAndCountMap.get(entry.getKey()).get("Hospitalization"));
							totalCount+=locIdAndCategoryAndCountMap.get(entry.getKey()).get("Hospitalization");
						}
						insuranceVO.setTotalCategroyCount(totalCount);
						
						Long totalCountFilter = 0L;
						if(locIdAndCategoryAndCountMapFilter.get(entry.getKey()) != null && locIdAndCategoryAndCountMapFilter.get(entry.getKey()).get("Death") != null){
							insuranceVO.setDeathCountFilter(locIdAndCategoryAndCountMapFilter.get(entry.getKey()).get("Death"));
							totalCountFilter+=locIdAndCategoryAndCountMapFilter.get(entry.getKey()).get("Death");
						}
						if(locIdAndCategoryAndCountMapFilter.get(entry.getKey()) != null && locIdAndCategoryAndCountMapFilter.get(entry.getKey()).get("Hospitalization") != null){
							insuranceVO.setHospitalizationCountFilter(locIdAndCategoryAndCountMapFilter.get(entry.getKey()).get("Hospitalization"));
							totalCountFilter+=locIdAndCategoryAndCountMapFilter.get(entry.getKey()).get("Hospitalization");
						}
						
						insuranceVO.setTotalCategroyCountFilter(totalCountFilter);
						
						Long totalAmount = 0L;
						
						if(locIdAndCategoryAndAmountMap.get(entry.getKey()) != null && locIdAndCategoryAndAmountMap.get(entry.getKey()).get("Death") != null){
							totalAmount+=locIdAndCategoryAndAmountMap.get(entry.getKey()).get("Death");
						}
						if(locIdAndCategoryAndAmountMap.get(entry.getKey()) != null && locIdAndCategoryAndAmountMap.get(entry.getKey()).get("Hospitalization") != null){
							totalAmount+=locIdAndCategoryAndAmountMap.get(entry.getKey()).get("Hospitalization");
						}
						insuranceVO.setTotalAmount(totalAmount);
						coreDashboardInsuranceVOs.add(insuranceVO);
					}
				}
				//push const no.
				if(levelId.longValue() == 4L || levelId.longValue() == 5L){
					List<Long> locaionIds = new ArrayList<Long>();
					if(coreDashboardInsuranceVOs != null && coreDashboardInsuranceVOs.size() > 0){
						for(CoreDashboardInsuranceVO coreDashboardInsuranceVO : coreDashboardInsuranceVOs){
							locaionIds.add(coreDashboardInsuranceVO.getId());
						}
					}
					
					Map<Long,Long> constIdAndNoMap = new HashMap<Long,Long>();
					List<Object[]> constOrderList = delimitationConstituencyDAO.getConstituencyNumbersForConstituenctIds(locaionIds);
					if(constOrderList != null && constOrderList.size() > 0){
						for(Object[] param : constOrderList){
							constIdAndNoMap.put(commonMethodsUtilService.getLongValueForObject(param[0]), commonMethodsUtilService.getLongValueForObject(param[1]));
						}
					}
					
					if(coreDashboardInsuranceVOs != null && coreDashboardInsuranceVOs.size() > 0){
						for(CoreDashboardInsuranceVO coreDashboardInsuranceVO : coreDashboardInsuranceVOs){
							coreDashboardInsuranceVO.setConstituencyNo(constIdAndNoMap.get(coreDashboardInsuranceVO.getId()));
						}
					}
				}
				
				//sort list based on members
				if(sortingCondition != null && sortingCondition.equalsIgnoreCase("insuredMember")){
					if(order != null && order.equalsIgnoreCase("desc")){
						if(coreDashboardInsuranceVOs != null && coreDashboardInsuranceVOs.size() > 0){
							Collections.sort(coreDashboardInsuranceVOs, benefitedMemberCountDesc);
						}
					}else{
						if(coreDashboardInsuranceVOs != null && coreDashboardInsuranceVOs.size() > 0){
							Collections.sort(coreDashboardInsuranceVOs, benefitedMemberCountAsc);
						}
					}
				}
				
				//sort list based on name
				if(sortingCondition != null && sortingCondition.equalsIgnoreCase("name")){
					if(order != null && order.equalsIgnoreCase("desc")){
						if(coreDashboardInsuranceVOs != null && coreDashboardInsuranceVOs.size() > 0){
							Collections.sort(coreDashboardInsuranceVOs, benefitedLocationNameDesc);
						}
					}else{
						if(coreDashboardInsuranceVOs != null && coreDashboardInsuranceVOs.size() > 0){
							Collections.sort(coreDashboardInsuranceVOs, benefitedLocationNameAsc);
						}
					}
				}
				
				//sort list based on amount
				if(sortingCondition != null && sortingCondition.equalsIgnoreCase("amount")){
					if(order != null && order.equalsIgnoreCase("desc")){
						if(coreDashboardInsuranceVOs != null && coreDashboardInsuranceVOs.size() > 0){
							Collections.sort(coreDashboardInsuranceVOs, benefitedAmountDesc);
						}
					}else{
						if(coreDashboardInsuranceVOs != null && coreDashboardInsuranceVOs.size() > 0){
							Collections.sort(coreDashboardInsuranceVOs, benefitedAmountAsc);
						}
					}
				}
				
				//sort list based on constituency no
				if(sortingCondition != null && sortingCondition.equalsIgnoreCase("constituencyNo")){
					if(order != null && order.equalsIgnoreCase("desc")){
						if(coreDashboardInsuranceVOs != null && coreDashboardInsuranceVOs.size() > 0){
							Collections.sort(coreDashboardInsuranceVOs, constituencyNoDesc);
						}
					}else{
						if(coreDashboardInsuranceVOs != null && coreDashboardInsuranceVOs.size() > 0){
							Collections.sort(coreDashboardInsuranceVOs, constituencyNoAsc);
						}
					}
				}
				
				
				System.out.println("hi");
				return coreDashboardInsuranceVOs;      
			}catch(Exception e){
				LOG.error("Error occured at getDistrictWiseThenCategoryWiseInsuranceMemberCount() in CoreDashboardInsuranceService class",e);
			}
			return null;
		}
		
		/*
		 * Swadhin K Lenka
		 * @see com.itgrids.partyanalyst.service.ICoreDashboardInsuranceService#getConstituencyWiseThenCategoryWiseInsuranceMemberCount(java.lang.Long, java.lang.Long, java.lang.Long, java.lang.Long, java.lang.Long, java.lang.String, java.lang.String, java.lang.String)
		 */
		public List<CoreDashboardInsuranceVO> getConstituencyWiseThenCategoryWiseInsuranceMemberCount(Long activityMemberId,Long userTypeId,Long stateId,Long cadreEnrollmentYearId,Long districtId, String status, String category, String fromDateStr,String toDateStr,String sortingCondition,String order) {
			try{
				List<CoreDashboardInsuranceVO> coreDashboardInsuranceVOs = new ArrayList<CoreDashboardInsuranceVO>();
				SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
				Date fromDate = null;
				Date toDate = null;
				if(fromDateStr != null && fromDateStr.length() > 0 && toDateStr != null && fromDateStr.length() > 0){
					fromDate = sdf.parse(fromDateStr);
					toDate = sdf.parse(toDateStr);
				}
				
				Set<Long> locationValuesSet = new java.util.HashSet<Long>();  
				Long levelId = 0L;
				List<Object[]> rtrnUsrAccssLvlIdAndVlusObjLst=activityMemberAccessLevelDAO.getLocationLevelAndValuesByActivityMembersId(activityMemberId);
			    if(rtrnUsrAccssLvlIdAndVlusObjLst != null && !rtrnUsrAccssLvlIdAndVlusObjLst.isEmpty()){
				   for (Object[] param : rtrnUsrAccssLvlIdAndVlusObjLst) {
					   levelId = commonMethodsUtilService.getLongValueForObject(param[0]);
					   locationValuesSet.add(param[1] != null ? (Long)param[1]:0l);
				   }
			    }
				
			    //create custom status list in service and send to query
			    List<Long> statusIdList = new ArrayList<Long>();
				if(status != null && status.trim().length() > 0 && status.trim().equalsIgnoreCase("INTIMATIONS")){
					statusIdList.add(1l);
					statusIdList.add(2l);
				}else if(status != null && status.trim().length() > 0 && status.trim().equalsIgnoreCase("FORWARDED")){
					statusIdList.add(3l);
					statusIdList.add(7l);
				}else if(status != null && status.trim().length() > 0 && status.trim().equalsIgnoreCase("SETTLED")){
					statusIdList.add(6l);
					statusIdList.add(8l);
				}else if(status != null && status.trim().length() > 0 && status.trim().equalsIgnoreCase("REJECTED")){
					statusIdList.add(4l);
					statusIdList.add(5l);
				}
				   
				List<Object[]> list1 = insuranceStatusDAO.getConstituencyWiseThenCategoryWiseInsuranceMemberCount(levelId, locationValuesSet, userTypeId, stateId, cadreEnrollmentYearId, districtId, statusIdList, category, fromDate, toDate,"category","all");
				List<Object[]> list2 = insuranceStatusDAO.getConstituencyWiseThenCategoryWiseInsuranceMemberCount(levelId, locationValuesSet, userTypeId, stateId, cadreEnrollmentYearId, districtId, statusIdList, category, fromDate, toDate,"category","filter");
				//create map of id and name
				Map<Long,String> idAndNameMap = new HashMap<Long,String>();
				//id and category and count map
				Map<Long,Map<String,Long>> locIdAndCategoryAndCountMap = new HashMap<Long,Map<String,Long>>();
				Map<String,Long> categoryAndCountMap = null;
				
				//id and category and count map Filter
				Map<Long,Map<String,Long>> locIdAndCategoryAndCountMapFilter = new HashMap<Long,Map<String,Long>>();
				Map<String,Long> categoryAndCountMapFilter = null;
				
				//id and category and amount map
				Map<Long,Map<String,Long>> locIdAndCategoryAndAmountMap = new HashMap<Long,Map<String,Long>>();
				Map<String,Long> categoryAndAmountMap = null;
				
				
				if(list1 != null && list1.size() > 0){
					for(Object[] param : list1){
						//create map if id and name
						idAndNameMap.put(param[0] != null ? (Long)param[0] : 0L, param[1] != null ? param[1].toString().trim() : "");
						
						//id and category and count map
						categoryAndCountMap = locIdAndCategoryAndCountMap.get(param[0] != null ? (Long)param[0] : 0L);
						if(categoryAndCountMap ==  null){
							categoryAndCountMap = new HashMap<String,Long>();
							locIdAndCategoryAndCountMap.put(param[0] != null ? (Long)param[0] : 0L, categoryAndCountMap);
						}
						categoryAndCountMap.put(param[2] != null ? param[2].toString().trim() : "", param[3] != null ? (Long)param[3] : 0L);
						
					}
				}
				
				if(list2 != null && list2.size() > 0){
					for(Object[] param : list2){
						
						//id and category and count map Filter
						categoryAndCountMapFilter = locIdAndCategoryAndCountMapFilter.get(param[0] != null ? (Long)param[0] : 0L);
						if(categoryAndCountMapFilter ==  null){
							categoryAndCountMapFilter = new HashMap<String,Long>();
							locIdAndCategoryAndCountMapFilter.put(param[0] != null ? (Long)param[0] : 0L, categoryAndCountMapFilter);
						}
						categoryAndCountMapFilter.put(param[2] != null ? param[2].toString().trim() : "", param[3] != null ? (Long)param[3] : 0L);
						
						//id and category and amount map
						categoryAndAmountMap = locIdAndCategoryAndAmountMap.get(param[0] != null ? (Long)param[0] : 0L);
						if(categoryAndAmountMap == null){
							categoryAndAmountMap = new HashMap<String,Long>();
							locIdAndCategoryAndAmountMap.put(param[0] != null ? (Long)param[0] : 0L, categoryAndAmountMap);
						}
						categoryAndAmountMap.put(param[2] != null ? param[2].toString().trim() : "", param[4] != null ? (Long)param[4] : 0L);
					}
				}
				
				
				//prepare vo object
				//CoreDashboardInsuranceVO coreDashboardInsuranceVO = null;
				if(idAndNameMap != null && idAndNameMap.size() > 0){
					for(Entry<Long,String> entry : idAndNameMap.entrySet()){
						CoreDashboardInsuranceVO insuranceVO = new CoreDashboardInsuranceVO();
						insuranceVO.setId(entry.getKey());
						insuranceVO.setName(entry.getValue());
						Long totalCount = 0L;
						if(locIdAndCategoryAndCountMap.get(entry.getKey()) != null && locIdAndCategoryAndCountMap.get(entry.getKey()).get("Death") != null){
							insuranceVO.setDeathCount(locIdAndCategoryAndCountMap.get(entry.getKey()).get("Death"));
							totalCount+=locIdAndCategoryAndCountMap.get(entry.getKey()).get("Death");
						}
						if(locIdAndCategoryAndCountMap.get(entry.getKey()) != null && locIdAndCategoryAndCountMap.get(entry.getKey()).get("Hospitalization") != null){
							insuranceVO.setHospitalizationCount(locIdAndCategoryAndCountMap.get(entry.getKey()).get("Hospitalization"));
							totalCount+=locIdAndCategoryAndCountMap.get(entry.getKey()).get("Hospitalization");
						}
						
						insuranceVO.setTotalCategroyCount(totalCount);
						
						
						Long totalCountFilter = 0L;
						if(locIdAndCategoryAndCountMapFilter.get(entry.getKey()) != null && locIdAndCategoryAndCountMapFilter.get(entry.getKey()).get("Death") != null){
							insuranceVO.setDeathCountFilter(locIdAndCategoryAndCountMapFilter.get(entry.getKey()).get("Death"));
							totalCountFilter+=locIdAndCategoryAndCountMapFilter.get(entry.getKey()).get("Death");
						}
						if(locIdAndCategoryAndCountMapFilter.get(entry.getKey()) != null && locIdAndCategoryAndCountMapFilter.get(entry.getKey()).get("Hospitalization") != null){
							insuranceVO.setHospitalizationCountFilter(locIdAndCategoryAndCountMapFilter.get(entry.getKey()).get("Hospitalization"));
							totalCountFilter+=locIdAndCategoryAndCountMapFilter.get(entry.getKey()).get("Hospitalization");
						}
						
						insuranceVO.setTotalCategroyCountFilter(totalCountFilter);
						
						Long totalAmount = 0L;
						
						if(locIdAndCategoryAndAmountMap.get(entry.getKey()) != null && locIdAndCategoryAndAmountMap.get(entry.getKey()).get("Death") != null){
							totalAmount+=locIdAndCategoryAndAmountMap.get(entry.getKey()).get("Death");
						}
						if(locIdAndCategoryAndAmountMap.get(entry.getKey()) != null && locIdAndCategoryAndAmountMap.get(entry.getKey()).get("Hospitalization") != null){
							totalAmount+=locIdAndCategoryAndAmountMap.get(entry.getKey()).get("Hospitalization");
						}
						insuranceVO.setTotalAmount(totalAmount);
						coreDashboardInsuranceVOs.add(insuranceVO);
					}
				}
				
				//push const no.
				if(coreDashboardInsuranceVOs != null && coreDashboardInsuranceVOs.size() > 0){
					List<Long> locaionIds = new ArrayList<Long>();
					for(CoreDashboardInsuranceVO coreDashboardInsuranceVO : coreDashboardInsuranceVOs){
						locaionIds.add(coreDashboardInsuranceVO.getId());
					}
					
					Map<Long,Long> constIdAndNoMap = new HashMap<Long,Long>();
					List<Object[]> constOrderList = delimitationConstituencyDAO.getConstituencyNumbersForConstituenctIds(locaionIds);
					
					for(Object[] param : constOrderList){
						constIdAndNoMap.put(commonMethodsUtilService.getLongValueForObject(param[0]), commonMethodsUtilService.getLongValueForObject(param[1]));
					}
					
					for(CoreDashboardInsuranceVO coreDashboardInsuranceVO : coreDashboardInsuranceVOs){
						coreDashboardInsuranceVO.setConstituencyNo(constIdAndNoMap.get(coreDashboardInsuranceVO.getId()));
					}
				
				}
				
		
				
				//sort list based on members
				if(sortingCondition != null && sortingCondition.equalsIgnoreCase("insuredMember")){
					if(order != null && order.equalsIgnoreCase("desc")){
						if(coreDashboardInsuranceVOs != null && coreDashboardInsuranceVOs.size() > 0){
							Collections.sort(coreDashboardInsuranceVOs, benefitedMemberCountDesc);
						}
					}else{
						if(coreDashboardInsuranceVOs != null && coreDashboardInsuranceVOs.size() > 0){
							Collections.sort(coreDashboardInsuranceVOs, benefitedMemberCountAsc);
						}
					}
				}
				
				//sort list based on name
				if(sortingCondition != null && sortingCondition.equalsIgnoreCase("name")){
					if(order != null && order.equalsIgnoreCase("desc")){
						if(coreDashboardInsuranceVOs != null && coreDashboardInsuranceVOs.size() > 0){
							Collections.sort(coreDashboardInsuranceVOs, benefitedLocationNameDesc);
						}
					}else{
						if(coreDashboardInsuranceVOs != null && coreDashboardInsuranceVOs.size() > 0){
							Collections.sort(coreDashboardInsuranceVOs, benefitedLocationNameAsc);
						}
					}
				}
				
				//sort list based on amount
				if(sortingCondition != null && sortingCondition.equalsIgnoreCase("amount")){
					if(order != null && order.equalsIgnoreCase("desc")){
						if(coreDashboardInsuranceVOs != null && coreDashboardInsuranceVOs.size() > 0){
							Collections.sort(coreDashboardInsuranceVOs, benefitedAmountDesc);
						}
					}else{
						if(coreDashboardInsuranceVOs != null && coreDashboardInsuranceVOs.size() > 0){
							Collections.sort(coreDashboardInsuranceVOs, benefitedAmountAsc);
						}
					}
				}
				
				//sort list based on constituency no
				if(sortingCondition != null && sortingCondition.equalsIgnoreCase("constituencyNo")){
					if(order != null && order.equalsIgnoreCase("desc")){
						if(coreDashboardInsuranceVOs != null && coreDashboardInsuranceVOs.size() > 0){
							Collections.sort(coreDashboardInsuranceVOs, constituencyNoDesc);
						}
					}else{
						if(coreDashboardInsuranceVOs != null && coreDashboardInsuranceVOs.size() > 0){
							Collections.sort(coreDashboardInsuranceVOs, constituencyNoAsc);
						}
					}
				}
				
				
				
				System.out.println("hi");
				return coreDashboardInsuranceVOs;
			}catch(Exception e){
				LOG.error("Error occured at getConstituencyWiseThenCategoryWiseInsuranceMemberCount() in CoreDashboardInsuranceService class",e);
			}
			return null;
		}
		
		/*
		 * Swadhin K Lenka
		 * @see com.itgrids.partyanalyst.service.ICoreDashboardInsuranceService#getDistrictWiseThenStatusWiseInsuranceMemberCount(java.lang.Long, java.lang.Long, java.lang.Long, java.lang.Long, java.lang.Long, java.lang.String, java.lang.String, java.lang.String)
		 */
		public List<CoreDashboardInsuranceVO> getDistrictWiseThenStatusWiseInsuranceMemberCount(Long activityMemberId,Long userTypeId,Long stateId,Long cadreEnrollmentYearId,Long districtId, String status, String category, String fromDateStr,String toDateStr,String sortingCondition,String order) {
			try{
				List<CoreDashboardInsuranceVO> coreDashboardInsuranceVOs = new ArrayList<CoreDashboardInsuranceVO>();
				SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
				Date fromDate = null;
				Date toDate = null;
				if(fromDateStr != null && fromDateStr.length() > 0 && toDateStr != null && fromDateStr.length() > 0){
					fromDate = sdf.parse(fromDateStr);
					toDate = sdf.parse(toDateStr);
				}
				Set<Long> locationValuesSet = new java.util.HashSet<Long>();  
				Long levelId = 0L;
				List<Object[]> rtrnUsrAccssLvlIdAndVlusObjLst = activityMemberAccessLevelDAO.getLocationLevelAndValuesByActivityMembersId(activityMemberId);
			    if(rtrnUsrAccssLvlIdAndVlusObjLst != null && !rtrnUsrAccssLvlIdAndVlusObjLst.isEmpty()){
				   for (Object[] param : rtrnUsrAccssLvlIdAndVlusObjLst) {
					   levelId = commonMethodsUtilService.getLongValueForObject(param[0]);
					   locationValuesSet.add(param[1] != null ? (Long)param[1]:0l);
				   }
			    }
			    //create custom status list in service and send to query
			    List<Long> statusIdList = new ArrayList<Long>();
				if(status != null && status.trim().length() > 0 && status.trim().equalsIgnoreCase("INTIMATIONS")){
					statusIdList.add(1l);
					statusIdList.add(2l);
				}else if(status != null && status.trim().length() > 0 && status.trim().equalsIgnoreCase("FORWARDED")){
					statusIdList.add(3l);
					statusIdList.add(7l);
				}else if(status != null && status.trim().length() > 0 && status.trim().equalsIgnoreCase("SETTLED")){
					statusIdList.add(6l);
					statusIdList.add(8l);
				}else if(status != null && status.trim().length() > 0 && status.trim().equalsIgnoreCase("REJECTED")){
					statusIdList.add(4l);
					statusIdList.add(5l);
				}
				
				List<Object[]> list1 = null;
				List<Object[]> list2 = null;
				if(levelId != null && levelId.longValue() == 4L){
					list1 = insuranceStatusDAO.getDistrictWiseThenCategoryWiseInsuranceMemberCount(levelId, locationValuesSet, userTypeId, stateId, cadreEnrollmentYearId, districtId, statusIdList, category, fromDate, toDate,"status","all");
					list2 = insuranceStatusDAO.getDistrictWiseThenCategoryWiseInsuranceMemberCount(levelId, locationValuesSet, userTypeId, stateId, cadreEnrollmentYearId, districtId, statusIdList, category, fromDate, toDate,"status","filter");
					if(list1 != null && list1.size() > 0){
						List<Long> constIdList = new ArrayList<Long>();
						for(Object[] param : list1){
							constIdList.add(param[0] != null ? (Long)param[0] : 0L);
						}
						
						List<Object[]> constIdAndNameList = constituencyDAO.getConstituencyByConstituencyIds(constIdList);
						Map<Long,String> idNameMap = new HashMap<Long,String>();
						for(Object[] param : constIdAndNameList){
							idNameMap.put(param[0] != null ? (Long)param[0] : 0L, param[1] != null ? param[1].toString() : "");
						}
						for(Object[] param: list1){
							param[1] = idNameMap.get(param[0] != null ? (Long)param[0] : 0L);
						}
						
					}
				}else{
					list1 = insuranceStatusDAO.getDistrictWiseThenCategoryWiseInsuranceMemberCount(levelId, locationValuesSet, userTypeId, stateId, cadreEnrollmentYearId, districtId, statusIdList, category, fromDate, toDate,"status","all");
					list2 = insuranceStatusDAO.getDistrictWiseThenCategoryWiseInsuranceMemberCount(levelId, locationValuesSet, userTypeId, stateId, cadreEnrollmentYearId, districtId, statusIdList, category, fromDate, toDate,"status","all");
				}
				
				//create map of id and name
				Map<Long,String> idAndNameMap = new HashMap<Long,String>();
				
				//id and status and count map
				Map<Long,Map<Long,Long>> locIdAndStatusAndCountMap = new HashMap<Long,Map<Long,Long>>();
				Map<Long,Long> statusAndCountMap = null;
				
				//id and status and count map Filter
				Map<Long,Map<Long,Long>> locIdAndStatusAndCountMapFilter = new HashMap<Long,Map<Long,Long>>();
				Map<Long,Long> statusAndCountMapFilter = null;
				
				
				//id and status and amount map
				Map<Long,Map<Long,Long>> locIdAndStatusAndAmountMap = new HashMap<Long,Map<Long,Long>>();
				Map<Long,Long> statusAndAmountMap = null;
				
				//location id and total member count map
				Map<Long,Long> locationIdAndMemberCountMap = new HashMap<Long,Long>();
				
				//location id and total member count map Filter
				Map<Long,Long> locationIdAndMemberCountMapFilter = new HashMap<Long,Long>();
				
				//location id and total amount count map
				Map<Long,Long> locationIdAndTotalAmountMap = new HashMap<Long,Long>();
				
				if(list1 != null && list1.size() > 0){
					for(Object[] param : list1){
						//create map if id and name
						idAndNameMap.put(param[0] != null ? (Long)param[0] : 0L, param[1] != null ? param[1].toString().trim() : "");
						
						//id and status and count map
						statusAndCountMap = locIdAndStatusAndCountMap.get(param[0] != null ? (Long)param[0] : 0L);
						if(statusAndCountMap ==  null){
							statusAndCountMap = new HashMap<Long,Long>();
							locIdAndStatusAndCountMap.put(param[0] != null ? (Long)param[0] : 0L, statusAndCountMap);
						}
						statusAndCountMap.put(param[2] != null ? (Long)param[2] : 0L, param[3] != null ? (Long)param[3] : 0L);
						
						
						//location id and total member count map
						Long memCount = locationIdAndMemberCountMap.get(param[0] != null ? (Long)param[0] : 0L);
						if(memCount == null){
							locationIdAndMemberCountMap.put(param[0] != null ? (Long)param[0] : 0L, new Long(0));
						}
						locationIdAndMemberCountMap.put(param[0] != null ? (Long)param[0] : 0L, (locationIdAndMemberCountMap.get(param[0] != null ? (Long)param[0] : 0L))+(param[3] != null ? (Long)param[3] : 0L));
						
						
					}
				}
				
				if(list2 != null && list2.size() > 0){
					for(Object[] param : list2){
						
						//id and status and count map Filter
						statusAndCountMapFilter = locIdAndStatusAndCountMapFilter.get(param[0] != null ? (Long)param[0] : 0L);
						if(statusAndCountMapFilter ==  null){
							statusAndCountMapFilter = new HashMap<Long,Long>();
							locIdAndStatusAndCountMapFilter.put(param[0] != null ? (Long)param[0] : 0L, statusAndCountMapFilter);
						}
						statusAndCountMapFilter.put(param[2] != null ? (Long)param[2] : 0L, param[3] != null ? (Long)param[3] : 0L);
						
						//id and status and amount map
						statusAndAmountMap = locIdAndStatusAndAmountMap.get(param[0] != null ? (Long)param[0] : 0L);
						if(statusAndAmountMap == null){
							statusAndAmountMap = new HashMap<Long,Long>();
							locIdAndStatusAndAmountMap.put(param[0] != null ? (Long)param[0] : 0L, statusAndAmountMap);
						}
						statusAndAmountMap.put(param[2] != null ? (Long)param[2] : 0L, param[4] != null ? (Long)param[4] : 0L);
						
						//location id and total member count map Filter
						Long memCount = locationIdAndMemberCountMapFilter.get(param[0] != null ? (Long)param[0] : 0L);
						if(memCount == null){
							locationIdAndMemberCountMapFilter.put(param[0] != null ? (Long)param[0] : 0L, new Long(0));
						}
						locationIdAndMemberCountMapFilter.put(param[0] != null ? (Long)param[0] : 0L, (locationIdAndMemberCountMapFilter.get(param[0] != null ? (Long)param[0] : 0L))+(param[3] != null ? (Long)param[3] : 0L));
						
						//location id and total amount count map
						Long totAmount = locationIdAndTotalAmountMap.get(param[0] != null ? (Long)param[0] : 0L);
						if(totAmount == null){
							locationIdAndTotalAmountMap.put(param[0] != null ? (Long)param[0] : 0L, new Long(0));
						}
						locationIdAndTotalAmountMap.put(param[0] != null ? (Long)param[0] : 0L, (locationIdAndTotalAmountMap.get(param[0] != null ? (Long)param[0] : 0L))+(param[4] != null ? (Long)param[4] : 0L));
						
						
					}
				}
				
				CoreDashboardInsuranceVO insuranceVO = null;
				if(idAndNameMap != null && idAndNameMap.size() > 0){
					for(Entry<Long,String> entry : idAndNameMap.entrySet()){
						insuranceVO = new CoreDashboardInsuranceVO();
						insuranceVO.setId(entry.getKey() != null ? entry.getKey() : 0L);
						insuranceVO.setName(entry.getValue() != null ? entry.getValue() : "");
						insuranceVO.setTotalStatusCount(locationIdAndMemberCountMap.get(entry.getKey()) != null ? locationIdAndMemberCountMap.get(entry.getKey()) : 0L);
						insuranceVO.setTotalStatusCountFilter(locationIdAndMemberCountMapFilter.get(entry.getKey()) != null ? locationIdAndMemberCountMapFilter.get(entry.getKey()) : 0L);
						insuranceVO.setTotalAmount(locationIdAndTotalAmountMap.get(entry.getKey()) != null ? locationIdAndTotalAmountMap.get(entry.getKey()) : 0L);
						
						addListForAllStatus(insuranceVO);
						
						putValueInCorrectList(locIdAndStatusAndCountMap,locIdAndStatusAndAmountMap,insuranceVO,locIdAndStatusAndCountMapFilter);
						coreDashboardInsuranceVOs.add(insuranceVO);
					}
				}
				
				
				//push const no.
				if(levelId.longValue() == 4L || levelId.longValue() == 5L){
					List<Long> locaionIds = new ArrayList<Long>();
					if(coreDashboardInsuranceVOs != null && coreDashboardInsuranceVOs.size() > 0){
						for(CoreDashboardInsuranceVO coreDashboardInsuranceVO : coreDashboardInsuranceVOs){
							locaionIds.add(coreDashboardInsuranceVO.getId());
						}
					}
					
					Map<Long,Long> constIdAndNoMap = new HashMap<Long,Long>();
					List<Object[]> constOrderList = delimitationConstituencyDAO.getConstituencyNumbersForConstituenctIds(locaionIds);
					if(constOrderList != null && constOrderList.size() > 0){
						for(Object[] param : constOrderList){
							constIdAndNoMap.put(commonMethodsUtilService.getLongValueForObject(param[0]), commonMethodsUtilService.getLongValueForObject(param[1]));
						}
					}
					
					if(coreDashboardInsuranceVOs != null && coreDashboardInsuranceVOs.size() > 0){
						for(CoreDashboardInsuranceVO coreDashboardInsuranceVO : coreDashboardInsuranceVOs){
							coreDashboardInsuranceVO.setConstituencyNo(constIdAndNoMap.get(coreDashboardInsuranceVO.getId()));
						}
					}
				}
				
				
				
				//sort list based on members
				if(sortingCondition != null && sortingCondition.equalsIgnoreCase("insuredMember")){
					if(order != null && order.equalsIgnoreCase("desc")){
						if(coreDashboardInsuranceVOs != null && coreDashboardInsuranceVOs.size() > 0){
							Collections.sort(coreDashboardInsuranceVOs, benefitedMemberCountForStatusDesc);
						}
					}else{
						if(coreDashboardInsuranceVOs != null && coreDashboardInsuranceVOs.size() > 0){
							Collections.sort(coreDashboardInsuranceVOs, benefitedMemberCountForStatusAsc);
						}
					}
				}
				
				//sort list based on name
				if(sortingCondition != null && sortingCondition.equalsIgnoreCase("name")){
					if(order != null && order.equalsIgnoreCase("desc")){
						if(coreDashboardInsuranceVOs != null && coreDashboardInsuranceVOs.size() > 0){
							Collections.sort(coreDashboardInsuranceVOs, benefitedLocationNameDesc);
						}
					}else{
						if(coreDashboardInsuranceVOs != null && coreDashboardInsuranceVOs.size() > 0){
							Collections.sort(coreDashboardInsuranceVOs, benefitedLocationNameAsc);
						}
					}
				}
				
				//sort list based on amount
				if(sortingCondition != null && sortingCondition.equalsIgnoreCase("amount")){
					if(order != null && order.equalsIgnoreCase("desc")){
						if(coreDashboardInsuranceVOs != null && coreDashboardInsuranceVOs.size() > 0){
							Collections.sort(coreDashboardInsuranceVOs, benefitedAmountDesc);
						}
					}else{
						if(coreDashboardInsuranceVOs != null && coreDashboardInsuranceVOs.size() > 0){
							Collections.sort(coreDashboardInsuranceVOs, benefitedAmountAsc);
						}
					}
				}
				
				//sort list based on constituency no
				if(sortingCondition != null && sortingCondition.equalsIgnoreCase("constituencyNo")){
					if(order != null && order.equalsIgnoreCase("desc")){
						if(coreDashboardInsuranceVOs != null && coreDashboardInsuranceVOs.size() > 0){
							Collections.sort(coreDashboardInsuranceVOs, constituencyNoDesc);
						}
					}else{
						if(coreDashboardInsuranceVOs != null && coreDashboardInsuranceVOs.size() > 0){
							Collections.sort(coreDashboardInsuranceVOs, constituencyNoAsc);
						}
					}
				}
				
				
				System.out.println("hi");
				return coreDashboardInsuranceVOs;
			}catch(Exception e){
				LOG.error("Error occured at getDistrictWiseThenStatusWiseInsuranceMemberCount() in CoreDashboardInsuranceService class",e);
			}
			return null;  
		}
		/*
		 * Swadhin K Lenka
		 * @see com.itgrids.partyanalyst.service.ICoreDashboardInsuranceService#getConstituencyWiseThenStatusWiseInsuranceMemberCount(java.lang.Long, java.lang.Long, java.lang.Long, java.lang.Long, java.lang.Long, java.lang.String, java.lang.String, java.lang.String)
		 */
		public List<CoreDashboardInsuranceVO> getConstituencyWiseThenStatusWiseInsuranceMemberCount(Long activityMemberId,Long userTypeId,Long stateId,Long cadreEnrollmentYearId,Long districtId, String status, String category, String fromDateStr,String toDateStr,String sortingCondition,String order) {
			try{
				List<CoreDashboardInsuranceVO> coreDashboardInsuranceVOs = new ArrayList<CoreDashboardInsuranceVO>();
				SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
				Date fromDate = null;
				Date toDate = null;
				if(fromDateStr != null && fromDateStr.length() > 0 && toDateStr != null && fromDateStr.length() > 0){
					fromDate = sdf.parse(fromDateStr);
					toDate = sdf.parse(toDateStr);
				}
				Set<Long> locationValuesSet = new java.util.HashSet<Long>();  
				Long levelId = 0L;
				List<Object[]> rtrnUsrAccssLvlIdAndVlusObjLst = activityMemberAccessLevelDAO.getLocationLevelAndValuesByActivityMembersId(activityMemberId);
			    if(rtrnUsrAccssLvlIdAndVlusObjLst != null && !rtrnUsrAccssLvlIdAndVlusObjLst.isEmpty()){
				   for (Object[] param : rtrnUsrAccssLvlIdAndVlusObjLst) {
					   levelId = commonMethodsUtilService.getLongValueForObject(param[0]);
					   locationValuesSet.add(param[1] != null ? (Long)param[1]:0l);
				   }
			    }
			    //create custom status list in service and send to query
			    List<Long> statusIdList = new ArrayList<Long>();
				if(status != null && status.trim().length() > 0 && status.trim().equalsIgnoreCase("INTIMATIONS")){
					statusIdList.add(1l);
					statusIdList.add(2l);
				}else if(status != null && status.trim().length() > 0 && status.trim().equalsIgnoreCase("FORWARDED")){
					statusIdList.add(3l);
					statusIdList.add(7l);
				}else if(status != null && status.trim().length() > 0 && status.trim().equalsIgnoreCase("SETTLED")){
					statusIdList.add(6l);
					statusIdList.add(8l);
				}else if(status != null && status.trim().length() > 0 && status.trim().equalsIgnoreCase("REJECTED")){
					statusIdList.add(4l);
					statusIdList.add(5l);
				}
				
				List<Object[]> list1 = insuranceStatusDAO.getConstituencyWiseThenCategoryWiseInsuranceMemberCount(levelId, locationValuesSet, userTypeId, stateId, cadreEnrollmentYearId, districtId, statusIdList, category, fromDate, toDate,"status","all");
				List<Object[]> list2 = insuranceStatusDAO.getConstituencyWiseThenCategoryWiseInsuranceMemberCount(levelId, locationValuesSet, userTypeId, stateId, cadreEnrollmentYearId, districtId, statusIdList, category, fromDate, toDate,"status","filter");
				
				//create map of id and name
				Map<Long,String> idAndNameMap = new HashMap<Long,String>();
				
				//id and status and count map
				Map<Long,Map<Long,Long>> locIdAndStatusAndCountMap = new HashMap<Long,Map<Long,Long>>();
				Map<Long,Long> statusAndCountMap = null;
				
				
				//id and status and count map filter
				Map<Long,Map<Long,Long>> locIdAndStatusAndCountMapFilter = new HashMap<Long,Map<Long,Long>>();
				Map<Long,Long> statusAndCountMapFilter = null;
				
				
				
				//id and status and amount map
				Map<Long,Map<Long,Long>> locIdAndStatusAndAmountMap = new HashMap<Long,Map<Long,Long>>();
				Map<Long,Long> statusAndAmountMap = null;
				
				//location id and total member count map
				Map<Long,Long> locationIdAndMemberCountMap = new HashMap<Long,Long>();
				
				
				//location id and total member count map Filter
				Map<Long,Long> locationIdAndMemberCountMapFilter = new HashMap<Long,Long>();
				
				//location id and total amount count map
				Map<Long,Long> locationIdAndTotalAmountMap = new HashMap<Long,Long>();
				
				if(list1 != null && list1.size() > 0){
					for(Object[] param : list1){
						//create map if id and name
						idAndNameMap.put(param[0] != null ? (Long)param[0] : 0L, param[1] != null ? param[1].toString().trim() : "");
						
						//id and status and count map
						statusAndCountMap = locIdAndStatusAndCountMap.get(param[0] != null ? (Long)param[0] : 0L);
						if(statusAndCountMap ==  null){
							statusAndCountMap = new HashMap<Long,Long>();
							locIdAndStatusAndCountMap.put(param[0] != null ? (Long)param[0] : 0L, statusAndCountMap);
						}
						statusAndCountMap.put(param[2] != null ? (Long)param[2] : 0L, param[3] != null ? (Long)param[3] : 0L);
						
						//location id and total member count map
						Long memCount = locationIdAndMemberCountMap.get(param[0] != null ? (Long)param[0] : 0L);
						if(memCount == null){
							locationIdAndMemberCountMap.put(param[0] != null ? (Long)param[0] : 0L, new Long(0));
						}
						locationIdAndMemberCountMap.put(param[0] != null ? (Long)param[0] : 0L, (locationIdAndMemberCountMap.get(param[0] != null ? (Long)param[0] : 0L))+(param[3] != null ? (Long)param[3] : 0L));
						
					}
				}
				
				
				if(list2 != null && list2.size() > 0){
					for(Object[] param : list2){
						
						//id and status and count map Filter
						statusAndCountMapFilter = locIdAndStatusAndCountMapFilter.get(param[0] != null ? (Long)param[0] : 0L);
						if(statusAndCountMapFilter ==  null){
							statusAndCountMapFilter = new HashMap<Long,Long>();
							locIdAndStatusAndCountMapFilter.put(param[0] != null ? (Long)param[0] : 0L, statusAndCountMapFilter);
						}
						statusAndCountMapFilter.put(param[2] != null ? (Long)param[2] : 0L, param[3] != null ? (Long)param[3] : 0L);
						
						//id and status and amount map
						statusAndAmountMap = locIdAndStatusAndAmountMap.get(param[0] != null ? (Long)param[0] : 0L);
						if(statusAndAmountMap == null){
							statusAndAmountMap = new HashMap<Long,Long>();
							locIdAndStatusAndAmountMap.put(param[0] != null ? (Long)param[0] : 0L, statusAndAmountMap);
						}
						statusAndAmountMap.put(param[2] != null ? (Long)param[2] : 0L, param[4] != null ? (Long)param[4] : 0L);
						
						//location id and total member count map Filter
						Long memCount = locationIdAndMemberCountMapFilter.get(param[0] != null ? (Long)param[0] : 0L);
						if(memCount == null){
							locationIdAndMemberCountMapFilter.put(param[0] != null ? (Long)param[0] : 0L, new Long(0));
						}
						locationIdAndMemberCountMapFilter.put(param[0] != null ? (Long)param[0] : 0L, (locationIdAndMemberCountMapFilter.get(param[0] != null ? (Long)param[0] : 0L))+(param[3] != null ? (Long)param[3] : 0L));
						
						//location id and total amount count map
						Long totAmount = locationIdAndTotalAmountMap.get(param[0] != null ? (Long)param[0] : 0L);
						if(totAmount == null){
							locationIdAndTotalAmountMap.put(param[0] != null ? (Long)param[0] : 0L, new Long(0));
						}
						locationIdAndTotalAmountMap.put(param[0] != null ? (Long)param[0] : 0L, (locationIdAndTotalAmountMap.get(param[0] != null ? (Long)param[0] : 0L))+(param[4] != null ? (Long)param[4] : 0L));
						
						
					}
				}
				
				CoreDashboardInsuranceVO insuranceVO = null;
				if(idAndNameMap != null && idAndNameMap.size() > 0){
					for(Entry<Long,String> entry : idAndNameMap.entrySet()){
						insuranceVO = new CoreDashboardInsuranceVO();
						insuranceVO.setId(entry.getKey() != null ? entry.getKey() : 0L);
						insuranceVO.setName(entry.getValue() != null ? entry.getValue() : "");
						insuranceVO.setTotalStatusCount(locationIdAndMemberCountMap.get(entry.getKey()) != null ? locationIdAndMemberCountMap.get(entry.getKey()) : 0L);
						insuranceVO.setTotalStatusCountFilter(locationIdAndMemberCountMapFilter.get(entry.getKey()) != null ? locationIdAndMemberCountMapFilter.get(entry.getKey()) : 0L);
						insuranceVO.setTotalAmount(locationIdAndTotalAmountMap.get(entry.getKey()) != null ? locationIdAndTotalAmountMap.get(entry.getKey()) : 0L);
						
						addListForAllStatus(insuranceVO);
						
						putValueInCorrectList(locIdAndStatusAndCountMap,locIdAndStatusAndAmountMap,insuranceVO,locIdAndStatusAndCountMapFilter);
						coreDashboardInsuranceVOs.add(insuranceVO);
					}
				}
				
				
				//push const no.
				if(coreDashboardInsuranceVOs != null && coreDashboardInsuranceVOs.size() > 0){
					List<Long> locaionIds = new ArrayList<Long>();
					for(CoreDashboardInsuranceVO coreDashboardInsuranceVO : coreDashboardInsuranceVOs){
						locaionIds.add(coreDashboardInsuranceVO.getId());
					}
					
					Map<Long,Long> constIdAndNoMap = new HashMap<Long,Long>();
					List<Object[]> constOrderList = delimitationConstituencyDAO.getConstituencyNumbersForConstituenctIds(locaionIds);
					
					for(Object[] param : constOrderList){
						constIdAndNoMap.put(commonMethodsUtilService.getLongValueForObject(param[0]), commonMethodsUtilService.getLongValueForObject(param[1]));
					}
					
					for(CoreDashboardInsuranceVO coreDashboardInsuranceVO : coreDashboardInsuranceVOs){
						coreDashboardInsuranceVO.setConstituencyNo(constIdAndNoMap.get(coreDashboardInsuranceVO.getId()));
					}
				}
				
				//sort list based on members
				if(sortingCondition != null && sortingCondition.equalsIgnoreCase("insuredMember")){
					if(order != null && order.equalsIgnoreCase("desc")){
						if(coreDashboardInsuranceVOs != null && coreDashboardInsuranceVOs.size() > 0){
							Collections.sort(coreDashboardInsuranceVOs, benefitedMemberCountForStatusDesc);
						}
					}else{
						if(coreDashboardInsuranceVOs != null && coreDashboardInsuranceVOs.size() > 0){
							Collections.sort(coreDashboardInsuranceVOs, benefitedMemberCountForStatusAsc);
						}
					}
				}
				
				//sort list based on name
				if(sortingCondition != null && sortingCondition.equalsIgnoreCase("name")){
					if(order != null && order.equalsIgnoreCase("desc")){
						if(coreDashboardInsuranceVOs != null && coreDashboardInsuranceVOs.size() > 0){
							Collections.sort(coreDashboardInsuranceVOs, benefitedLocationNameDesc);
						}
					}else{
						if(coreDashboardInsuranceVOs != null && coreDashboardInsuranceVOs.size() > 0){
							Collections.sort(coreDashboardInsuranceVOs, benefitedLocationNameAsc);
						}
					}
				}
				
				//sort list based on amount
				if(sortingCondition != null && sortingCondition.equalsIgnoreCase("amount")){
					if(order != null && order.equalsIgnoreCase("desc")){
						if(coreDashboardInsuranceVOs != null && coreDashboardInsuranceVOs.size() > 0){
							Collections.sort(coreDashboardInsuranceVOs, benefitedAmountDesc);
						}
					}else{
						if(coreDashboardInsuranceVOs != null && coreDashboardInsuranceVOs.size() > 0){
							Collections.sort(coreDashboardInsuranceVOs, benefitedAmountAsc);
						}
					}
				}
				
				//sort list based on constituency no
				if(sortingCondition != null && sortingCondition.equalsIgnoreCase("constituencyNo")){
					if(order != null && order.equalsIgnoreCase("desc")){
						if(coreDashboardInsuranceVOs != null && coreDashboardInsuranceVOs.size() > 0){
							Collections.sort(coreDashboardInsuranceVOs, constituencyNoDesc);
						}
					}else{
						if(coreDashboardInsuranceVOs != null && coreDashboardInsuranceVOs.size() > 0){
							Collections.sort(coreDashboardInsuranceVOs, constituencyNoAsc);
						}
					}
				}
				
				
				
				System.out.println("hi");
				return coreDashboardInsuranceVOs;
			}catch(Exception e){
				LOG.error("Error occured at getConstituencyWiseThenStatusWiseInsuranceMemberCount() in CoreDashboardInsuranceService class",e);
			}
			return null;
		}
		
		public void addListForAllStatus(CoreDashboardInsuranceVO insuranceVO){
			try{
				InsuranceStatusVO insuranceStatusVO = null;
				for(int i = 0 ; i <= 3 ; i++){
					insuranceStatusVO = new InsuranceStatusVO();
					insuranceStatusVO.setStatusId(Long.parseLong((new Integer(i+1)).toString()));
					insuranceStatusVO.setStatus(IConstants.CORE_DASHBOARD_INSURANCE_STATUS_ARR[i]);
					insuranceVO.getInsuranceStatusVOs().add(insuranceStatusVO);
				}
			}catch(Exception e){
				e.printStackTrace();
			}
		}
		public void putValueInCorrectList(Map<Long,Map<Long,Long>> locIdAndStatusAndCountMap,Map<Long,Map<Long,Long>> locIdAndStatusAndAmountMap,CoreDashboardInsuranceVO insuranceVO,Map<Long,Map<Long,Long>> locIdAndStatusAndCountMapFilter){
			if(insuranceVO.getInsuranceStatusVOs() != null && insuranceVO.getInsuranceStatusVOs().size() > 0){
				Map<Long,Long> statusIdAndCountMap = (locIdAndStatusAndCountMap.get(insuranceVO.getId()) != null ? locIdAndStatusAndCountMap.get(insuranceVO.getId()) : new HashMap<Long,Long>());
				Map<Long,Long> statusIdAndCountMapFilter = (locIdAndStatusAndCountMapFilter.get(insuranceVO.getId()) != null ? locIdAndStatusAndCountMapFilter.get(insuranceVO.getId()) : new HashMap<Long,Long>());
				Map<Long,Long> statusIdAndAmountMap = (locIdAndStatusAndAmountMap.get(insuranceVO.getId()) != null ? locIdAndStatusAndAmountMap.get(insuranceVO.getId()) : new HashMap<Long,Long>() );
				for(InsuranceStatusVO param : insuranceVO.getInsuranceStatusVOs()){
					Long statusId = param.getStatusId();
					if(statusId.longValue() == 1L){
						//for member count
						param.setInsuredMemberCount(param.getInsuredMemberCount()+(statusIdAndCountMap.get(1L) != null ? statusIdAndCountMap.get(1L) : 0L));
						param.setInsuredMemberCount(param.getInsuredMemberCount()+(statusIdAndCountMap.get(2L) != null ? statusIdAndCountMap.get(2L) : 0L));
						
						param.setInsuredMemberCountFilter(param.getInsuredMemberCountFilter()+(statusIdAndCountMapFilter.get(1L) != null ? statusIdAndCountMapFilter.get(1L) : 0L));
						param.setInsuredMemberCountFilter(param.getInsuredMemberCountFilter()+(statusIdAndCountMapFilter.get(2L) != null ? statusIdAndCountMapFilter.get(2L) : 0L));
						
						//for amount
						param.setInsuredAmount(param.getInsuredAmount()+(statusIdAndAmountMap.get(1L) != null ? statusIdAndAmountMap.get(1L) : 0L));
						param.setInsuredAmount(param.getInsuredAmount()+(statusIdAndAmountMap.get(2L) != null ? statusIdAndAmountMap.get(2L) : 0L));
					}else if(statusId.longValue() == 2L){
						//for member count
						param.setInsuredMemberCount(param.getInsuredMemberCount()+(statusIdAndCountMap.get(3L) != null ? statusIdAndCountMap.get(3L) : 0L));
						param.setInsuredMemberCount(param.getInsuredMemberCount()+(statusIdAndCountMap.get(7L) != null ? statusIdAndCountMap.get(7L) : 0L));
						
						param.setInsuredMemberCountFilter(param.getInsuredMemberCountFilter()+(statusIdAndCountMapFilter.get(3L) != null ? statusIdAndCountMapFilter.get(3L) : 0L));
						param.setInsuredMemberCountFilter(param.getInsuredMemberCountFilter()+(statusIdAndCountMapFilter.get(7L) != null ? statusIdAndCountMapFilter.get(7L) : 0L));
						//for amount
						param.setInsuredAmount(param.getInsuredAmount()+(statusIdAndAmountMap.get(3L) != null ? statusIdAndAmountMap.get(3L) : 0L));
						param.setInsuredAmount(param.getInsuredAmount()+(statusIdAndAmountMap.get(7L) != null ? statusIdAndAmountMap.get(7L) : 0L));
					}else if(statusId.longValue() == 3L){
						//for member count
						param.setInsuredMemberCount(param.getInsuredMemberCount()+(statusIdAndCountMap.get(6L) != null ? statusIdAndCountMap.get(6L) : 0L));
						param.setInsuredMemberCount(param.getInsuredMemberCount()+(statusIdAndCountMap.get(8L) != null ? statusIdAndCountMap.get(8L) : 0L));
						
						param.setInsuredMemberCountFilter(param.getInsuredMemberCountFilter()+(statusIdAndCountMapFilter.get(6L) != null ? statusIdAndCountMapFilter.get(6L) : 0L));
						param.setInsuredMemberCountFilter(param.getInsuredMemberCountFilter()+(statusIdAndCountMapFilter.get(8L) != null ? statusIdAndCountMapFilter.get(8L) : 0L));
						//for amount
						param.setInsuredAmount(param.getInsuredAmount()+(statusIdAndAmountMap.get(6L) != null ? statusIdAndAmountMap.get(6L) : 0L));
						param.setInsuredAmount(param.getInsuredAmount()+(statusIdAndAmountMap.get(8L) != null ? statusIdAndAmountMap.get(8L) : 0L));
					}else if(statusId.longValue() == 4L){
						//for member count
						param.setInsuredMemberCount(param.getInsuredMemberCount()+(statusIdAndCountMap.get(4L) != null ? statusIdAndCountMap.get(4L) : 0L));
						param.setInsuredMemberCount(param.getInsuredMemberCount()+(statusIdAndCountMap.get(5L) != null ? statusIdAndCountMap.get(5L) : 0L));
						
						param.setInsuredMemberCountFilter(param.getInsuredMemberCountFilter()+(statusIdAndCountMapFilter.get(4L) != null ? statusIdAndCountMapFilter.get(4L) : 0L));
						param.setInsuredMemberCountFilter(param.getInsuredMemberCountFilter()+(statusIdAndCountMapFilter.get(5L) != null ? statusIdAndCountMapFilter.get(5L) : 0L));
						//for amount
						param.setInsuredAmount(param.getInsuredAmount()+(statusIdAndAmountMap.get(4L) != null ? statusIdAndAmountMap.get(4L) : 0L));
						param.setInsuredAmount(param.getInsuredAmount()+(statusIdAndAmountMap.get(5L) != null ? statusIdAndAmountMap.get(5L) : 0L));
					}
				}
			}
		}
		/*
		 * Swadhin K Lenka
		 * @see com.itgrids.partyanalyst.service.ICoreDashboardInsuranceService#getLocationWiseThenCategoryWiseInsuranceMemberCountForTS(java.lang.Long, java.lang.Long, java.lang.Long, java.lang.Long, java.lang.Long, java.lang.String, java.lang.String, java.lang.String)
		 */
		public List<CoreDashboardInsuranceVO> getLocationWiseThenCategoryWiseInsuranceMemberCountForTS(Long stateId,Long cadreEnrollmentYearId,Long locationId, String status, String category, String fromDateStr,String toDateStr,String type, String locationType,String sortingCondition,String order) {
			try{
				List<CoreDashboardInsuranceVO> coreDashboardInsuranceVOs = new ArrayList<CoreDashboardInsuranceVO>();
				SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
				Date fromDate = null;
				Date toDate = null;
				if(fromDateStr != null && fromDateStr.length() > 0 && toDateStr != null && fromDateStr.length() > 0){
					fromDate = sdf.parse(fromDateStr);
					toDate = sdf.parse(toDateStr);
				}
				
			    //create custom status list in service and send to query
			    List<Long> statusIdList = new ArrayList<Long>();
				if(status != null && status.trim().length() > 0 && status.trim().equalsIgnoreCase("INTIMATIONS")){
					statusIdList.add(1l);
					statusIdList.add(2l);
				}else if(status != null && status.trim().length() > 0 && status.trim().equalsIgnoreCase("FORWARDED")){
					statusIdList.add(3l);
					statusIdList.add(7l);
				}else if(status != null && status.trim().length() > 0 && status.trim().equalsIgnoreCase("SETTLED")){
					statusIdList.add(6l);
					statusIdList.add(8l);
				}else if(status != null && status.trim().length() > 0 && status.trim().equalsIgnoreCase("REJECTED")){
					statusIdList.add(4l);
					statusIdList.add(5l);
				}
				   
				List<Object[]> list1 = insuranceStatusDAO.getLocationWiseThenCategoryWiseInsuranceMemberCountForTS(stateId, cadreEnrollmentYearId, locationId, statusIdList, category, fromDate, toDate,type,locationType,"all");
				List<Object[]> list2 = insuranceStatusDAO.getLocationWiseThenCategoryWiseInsuranceMemberCountForTS(stateId, cadreEnrollmentYearId, locationId, statusIdList, category, fromDate, toDate,type,locationType,"filter");
				
				//create map of id and name
				Map<Long,String> idAndNameMap = new HashMap<Long,String>();
				//id and category and count map
				Map<Long,Map<String,Long>> locIdAndCategoryAndCountMap = new HashMap<Long,Map<String,Long>>();
				Map<String,Long> categoryAndCountMap = null;
				
				//id and category and count map Filter
				Map<Long,Map<String,Long>> locIdAndCategoryAndCountMapFilter = new HashMap<Long,Map<String,Long>>();
				Map<String,Long> categoryAndCountMapFilter = null;
				
				//id and category and amount map
				Map<Long,Map<String,Long>> locIdAndCategoryAndAmountMap = new HashMap<Long,Map<String,Long>>();
				Map<String,Long> categoryAndAmountMap = null;
				
				
				if(list1 != null && list1.size() > 0){
					for(Object[] param : list1){
						//create map if id and name
						idAndNameMap.put(param[0] != null ? (Long)param[0] : 0L, param[1] != null ? param[1].toString().trim() : "");
						
						//id and category and count map
						categoryAndCountMap = locIdAndCategoryAndCountMap.get(param[0] != null ? (Long)param[0] : 0L);
						if(categoryAndCountMap ==  null){
							categoryAndCountMap = new HashMap<String,Long>();
							locIdAndCategoryAndCountMap.put(param[0] != null ? (Long)param[0] : 0L, categoryAndCountMap);
						}
						categoryAndCountMap.put(param[2] != null ? param[2].toString().trim() : "", param[3] != null ? (Long)param[3] : 0L);
					
					}
				}
				
				
				if(list2 != null && list2.size() > 0){
					for(Object[] param : list2){
						
						//id and category and count map Filter
						categoryAndCountMapFilter = locIdAndCategoryAndCountMapFilter.get(param[0] != null ? (Long)param[0] : 0L);
						if(categoryAndCountMapFilter ==  null){
							categoryAndCountMapFilter = new HashMap<String,Long>();
							locIdAndCategoryAndCountMapFilter.put(param[0] != null ? (Long)param[0] : 0L, categoryAndCountMapFilter);
						}
						categoryAndCountMapFilter.put(param[2] != null ? param[2].toString().trim() : "", param[3] != null ? (Long)param[3] : 0L);
						
						//id and category and amount map
						categoryAndAmountMap = locIdAndCategoryAndAmountMap.get(param[0] != null ? (Long)param[0] : 0L);
						if(categoryAndAmountMap == null){
							categoryAndAmountMap = new HashMap<String,Long>();
							locIdAndCategoryAndAmountMap.put(param[0] != null ? (Long)param[0] : 0L, categoryAndAmountMap);
						}
						categoryAndAmountMap.put(param[2] != null ? param[2].toString().trim() : "", param[4] != null ? (Long)param[4] : 0L);
					}
				}
				
				
				//prepare vo object
				//CoreDashboardInsuranceVO coreDashboardInsuranceVO = null;
				if(idAndNameMap != null && idAndNameMap.size() > 0){
					for(Entry<Long,String> entry : idAndNameMap.entrySet()){
						CoreDashboardInsuranceVO insuranceVO = new CoreDashboardInsuranceVO();
						insuranceVO.setId(entry.getKey());
						insuranceVO.setName(entry.getValue());
						Long totalCount = 0L;
						if(locIdAndCategoryAndCountMap.get(entry.getKey()) != null && locIdAndCategoryAndCountMap.get(entry.getKey()).get("Death") != null){
							insuranceVO.setDeathCount(locIdAndCategoryAndCountMap.get(entry.getKey()).get("Death"));
							totalCount+=locIdAndCategoryAndCountMap.get(entry.getKey()).get("Death");
						}
						if(locIdAndCategoryAndCountMap.get(entry.getKey()) != null && locIdAndCategoryAndCountMap.get(entry.getKey()).get("Hospitalization") != null){
							insuranceVO.setHospitalizationCount(locIdAndCategoryAndCountMap.get(entry.getKey()).get("Hospitalization"));
							totalCount+=locIdAndCategoryAndCountMap.get(entry.getKey()).get("Hospitalization");
						}
						
						insuranceVO.setTotalCategroyCount(totalCount);
						
						Long totalCountFilter = 0L;
						if(locIdAndCategoryAndCountMapFilter.get(entry.getKey()) != null && locIdAndCategoryAndCountMapFilter.get(entry.getKey()).get("Death") != null){
							insuranceVO.setDeathCountFilter(locIdAndCategoryAndCountMapFilter.get(entry.getKey()).get("Death"));
							totalCountFilter+=locIdAndCategoryAndCountMapFilter.get(entry.getKey()).get("Death");
						}
						if(locIdAndCategoryAndCountMapFilter.get(entry.getKey()) != null && locIdAndCategoryAndCountMapFilter.get(entry.getKey()).get("Hospitalization") != null){
							insuranceVO.setHospitalizationCountFilter(locIdAndCategoryAndCountMapFilter.get(entry.getKey()).get("Hospitalization"));
							totalCountFilter+=locIdAndCategoryAndCountMapFilter.get(entry.getKey()).get("Hospitalization");
						}
						
						insuranceVO.setTotalCategroyCountFilter(totalCountFilter);
						
						
						Long totalAmount = 0L;  
						
						if(locIdAndCategoryAndAmountMap.get(entry.getKey()) != null && locIdAndCategoryAndAmountMap.get(entry.getKey()).get("Death") != null){
							totalAmount+=locIdAndCategoryAndAmountMap.get(entry.getKey()).get("Death");
						}
						if(locIdAndCategoryAndAmountMap.get(entry.getKey()) != null && locIdAndCategoryAndAmountMap.get(entry.getKey()).get("Hospitalization") != null){
							totalAmount+=locIdAndCategoryAndAmountMap.get(entry.getKey()).get("Hospitalization");
						}
						insuranceVO.setTotalAmount(totalAmount);
						coreDashboardInsuranceVOs.add(insuranceVO);
					}
				}
				
				
				
				//push const no.
				if(coreDashboardInsuranceVOs != null && coreDashboardInsuranceVOs.size() > 0){
					List<Long> locaionIds = new ArrayList<Long>();
					for(CoreDashboardInsuranceVO coreDashboardInsuranceVO : coreDashboardInsuranceVOs){
						locaionIds.add(coreDashboardInsuranceVO.getId());
					}
					
					Map<Long,Long> constIdAndNoMap = new HashMap<Long,Long>();
					List<Object[]> constOrderList = delimitationConstituencyDAO.getConstituencyNumbersForConstituenctIds(locaionIds);
					
					for(Object[] param : constOrderList){
						constIdAndNoMap.put(commonMethodsUtilService.getLongValueForObject(param[0]), commonMethodsUtilService.getLongValueForObject(param[1]));
					}
					
					for(CoreDashboardInsuranceVO coreDashboardInsuranceVO : coreDashboardInsuranceVOs){
						coreDashboardInsuranceVO.setConstituencyNo(constIdAndNoMap.get(coreDashboardInsuranceVO.getId()));
					}
				}
				
				//sort list based on members
				if(sortingCondition != null && sortingCondition.equalsIgnoreCase("insuredMember")){
					if(order != null && order.equalsIgnoreCase("desc")){
						if(coreDashboardInsuranceVOs != null && coreDashboardInsuranceVOs.size() > 0){
							Collections.sort(coreDashboardInsuranceVOs, benefitedMemberCountDesc);
						}
					}else{
						if(coreDashboardInsuranceVOs != null && coreDashboardInsuranceVOs.size() > 0){
							Collections.sort(coreDashboardInsuranceVOs, benefitedMemberCountAsc);
						}
					}
				}
				
				//sort list based on name
				if(sortingCondition != null && sortingCondition.equalsIgnoreCase("name")){
					if(order != null && order.equalsIgnoreCase("desc")){
						if(coreDashboardInsuranceVOs != null && coreDashboardInsuranceVOs.size() > 0){
							Collections.sort(coreDashboardInsuranceVOs, benefitedLocationNameDesc);
						}
					}else{
						if(coreDashboardInsuranceVOs != null && coreDashboardInsuranceVOs.size() > 0){
							Collections.sort(coreDashboardInsuranceVOs, benefitedLocationNameAsc);
						}
					}
				}
				
				//sort list based on amount
				if(sortingCondition != null && sortingCondition.equalsIgnoreCase("amount")){
					if(order != null && order.equalsIgnoreCase("desc")){
						if(coreDashboardInsuranceVOs != null && coreDashboardInsuranceVOs.size() > 0){
							Collections.sort(coreDashboardInsuranceVOs, benefitedAmountDesc);
						}
					}else{
						if(coreDashboardInsuranceVOs != null && coreDashboardInsuranceVOs.size() > 0){
							Collections.sort(coreDashboardInsuranceVOs, benefitedAmountAsc);
						}
					}
				}
				
				//sort list based on constituency no
				if(sortingCondition != null && sortingCondition.equalsIgnoreCase("constituencyNo")){
					if(order != null && order.equalsIgnoreCase("desc")){
						if(coreDashboardInsuranceVOs != null && coreDashboardInsuranceVOs.size() > 0){
							Collections.sort(coreDashboardInsuranceVOs, constituencyNoDesc);
						}
					}else{
						if(coreDashboardInsuranceVOs != null && coreDashboardInsuranceVOs.size() > 0){
							Collections.sort(coreDashboardInsuranceVOs, constituencyNoAsc);
						}
					}
				}
				return coreDashboardInsuranceVOs;
			}catch(Exception e){
				LOG.error("Error occured at getDistrictWiseThenCategoryWiseInsuranceMemberCountForTS() in CoreDashboardInsuranceService class",e);
			}
			return null;
		}
		public List<CoreDashboardInsuranceVO> getLocationWiseThenStatusWiseInsuranceMemberCountForTS(Long stateId,Long cadreEnrollmentYearId,Long locationId, String status, String category, String fromDateStr,String toDateStr,String type, String locationType,String sortingCondition,String order) {
			try{
				List<CoreDashboardInsuranceVO> coreDashboardInsuranceVOs = new ArrayList<CoreDashboardInsuranceVO>();
				SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
				Date fromDate = null;
				Date toDate = null;
				if(fromDateStr != null && fromDateStr.length() > 0 && toDateStr != null && fromDateStr.length() > 0){
					fromDate = sdf.parse(fromDateStr);
					toDate = sdf.parse(toDateStr);
				}
				//create custom status list in service and send to query
			    List<Long> statusIdList = new ArrayList<Long>();
				if(status != null && status.trim().length() > 0 && status.trim().equalsIgnoreCase("INTIMATIONS")){
					statusIdList.add(1l);
					statusIdList.add(2l);
				}else if(status != null && status.trim().length() > 0 && status.trim().equalsIgnoreCase("FORWARDED")){
					statusIdList.add(3l);
					statusIdList.add(7l);
				}else if(status != null && status.trim().length() > 0 && status.trim().equalsIgnoreCase("SETTLED")){
					statusIdList.add(6l);
					statusIdList.add(8l);
				}else if(status != null && status.trim().length() > 0 && status.trim().equalsIgnoreCase("REJECTED")){
					statusIdList.add(4l);
					statusIdList.add(5l);
				}
				
				List<Object[]> list1 = insuranceStatusDAO.getLocationWiseThenCategoryWiseInsuranceMemberCountForTS(stateId, cadreEnrollmentYearId, locationId, statusIdList, category, fromDate, toDate,type,locationType,"all");
				List<Object[]> list2 = insuranceStatusDAO.getLocationWiseThenCategoryWiseInsuranceMemberCountForTS(stateId, cadreEnrollmentYearId, locationId, statusIdList, category, fromDate, toDate,type,locationType,"filter");
				//create map of id and name
				Map<Long,String> idAndNameMap = new HashMap<Long,String>();
				
				//id and status and count map
				Map<Long,Map<Long,Long>> locIdAndStatusAndCountMap = new HashMap<Long,Map<Long,Long>>();
				Map<Long,Long> statusAndCountMap = null;
				
				//id and status and count map Filter
				Map<Long,Map<Long,Long>> locIdAndStatusAndCountMapFilter = new HashMap<Long,Map<Long,Long>>();
				Map<Long,Long> statusAndCountMapFilter = null;
				
				
				//id and status and amount map
				Map<Long,Map<Long,Long>> locIdAndStatusAndAmountMap = new HashMap<Long,Map<Long,Long>>();
				Map<Long,Long> statusAndAmountMap = null;
				
				//location id and total member count map
				Map<Long,Long> locationIdAndMemberCountMap = new HashMap<Long,Long>();
				
				//location id and total member count map Filter
				Map<Long,Long> locationIdAndMemberCountMapFilter = new HashMap<Long,Long>();
				
				//location id and total amount count map
				Map<Long,Long> locationIdAndTotalAmountMap = new HashMap<Long,Long>();
				
				if(list1 != null && list1.size() > 0){
					for(Object[] param : list1){
						//create map if id and name
						idAndNameMap.put(param[0] != null ? (Long)param[0] : 0L, param[1] != null ? param[1].toString().trim() : "");
						
						//id and status and count map
						statusAndCountMap = locIdAndStatusAndCountMap.get(param[0] != null ? (Long)param[0] : 0L);
						if(statusAndCountMap ==  null){
							statusAndCountMap = new HashMap<Long,Long>();
							locIdAndStatusAndCountMap.put(param[0] != null ? (Long)param[0] : 0L, statusAndCountMap);
						}
						statusAndCountMap.put(param[2] != null ? (Long)param[2] : 0L, param[3] != null ? (Long)param[3] : 0L);
						
						
						//location id and total member count map
						Long memCount = locationIdAndMemberCountMap.get(param[0] != null ? (Long)param[0] : 0L);
						if(memCount == null){
							locationIdAndMemberCountMap.put(param[0] != null ? (Long)param[0] : 0L, new Long(0));
						}
						locationIdAndMemberCountMap.put(param[0] != null ? (Long)param[0] : 0L, (locationIdAndMemberCountMap.get(param[0] != null ? (Long)param[0] : 0L))+(param[3] != null ? (Long)param[3] : 0L));
						
					}
				}
				
				
				if(list2 != null && list2.size() > 0){
					for(Object[] param : list2){
						
						
						//id and status and count map Filter
						statusAndCountMapFilter = locIdAndStatusAndCountMapFilter.get(param[0] != null ? (Long)param[0] : 0L);
						if(statusAndCountMapFilter ==  null){
							statusAndCountMapFilter = new HashMap<Long,Long>();
							locIdAndStatusAndCountMapFilter.put(param[0] != null ? (Long)param[0] : 0L, statusAndCountMapFilter);
						}
						statusAndCountMapFilter.put(param[2] != null ? (Long)param[2] : 0L, param[3] != null ? (Long)param[3] : 0L);
						
						//id and status and amount map
						statusAndAmountMap = locIdAndStatusAndAmountMap.get(param[0] != null ? (Long)param[0] : 0L);
						if(statusAndAmountMap == null){
							statusAndAmountMap = new HashMap<Long,Long>();
							locIdAndStatusAndAmountMap.put(param[0] != null ? (Long)param[0] : 0L, statusAndAmountMap);
						}
						statusAndAmountMap.put(param[2] != null ? (Long)param[2] : 0L, param[4] != null ? (Long)param[4] : 0L);
						
						//location id and total member count map Filter
						Long memCount = locationIdAndMemberCountMapFilter.get(param[0] != null ? (Long)param[0] : 0L);
						if(memCount == null){
							locationIdAndMemberCountMapFilter.put(param[0] != null ? (Long)param[0] : 0L, new Long(0));
						}
						locationIdAndMemberCountMapFilter.put(param[0] != null ? (Long)param[0] : 0L, (locationIdAndMemberCountMapFilter.get(param[0] != null ? (Long)param[0] : 0L))+(param[3] != null ? (Long)param[3] : 0L));
						
						//location id and total amount count map
						Long totAmount = locationIdAndTotalAmountMap.get(param[0] != null ? (Long)param[0] : 0L);
						if(totAmount == null){
							locationIdAndTotalAmountMap.put(param[0] != null ? (Long)param[0] : 0L, new Long(0));
						}
						locationIdAndTotalAmountMap.put(param[0] != null ? (Long)param[0] : 0L, (locationIdAndTotalAmountMap.get(param[0] != null ? (Long)param[0] : 0L))+(param[4] != null ? (Long)param[4] : 0L));
						
						
					}
				}
				
				CoreDashboardInsuranceVO insuranceVO = null;
				if(idAndNameMap != null && idAndNameMap.size() > 0){
					for(Entry<Long,String> entry : idAndNameMap.entrySet()){
						insuranceVO = new CoreDashboardInsuranceVO();
						insuranceVO.setId(entry.getKey() != null ? entry.getKey() : 0L);
						insuranceVO.setName(entry.getValue() != null ? entry.getValue() : "");
						insuranceVO.setTotalStatusCount(locationIdAndMemberCountMap.get(entry.getKey()) != null ? locationIdAndMemberCountMap.get(entry.getKey()) : 0L);
						insuranceVO.setTotalStatusCountFilter(locationIdAndMemberCountMapFilter.get(entry.getKey()) != null ? locationIdAndMemberCountMapFilter.get(entry.getKey()) : 0L);
						insuranceVO.setTotalAmount(locationIdAndTotalAmountMap.get(entry.getKey()) != null ? locationIdAndTotalAmountMap.get(entry.getKey()) : 0L);
						
						addListForAllStatus(insuranceVO);
						
						putValueInCorrectList(locIdAndStatusAndCountMap,locIdAndStatusAndAmountMap,insuranceVO,locIdAndStatusAndCountMapFilter);
						coreDashboardInsuranceVOs.add(insuranceVO);
					}
				}
				
				
				//push const no.
				if(coreDashboardInsuranceVOs != null && coreDashboardInsuranceVOs.size() > 0){
					List<Long> locaionIds = new ArrayList<Long>();
					for(CoreDashboardInsuranceVO coreDashboardInsuranceVO : coreDashboardInsuranceVOs){
						locaionIds.add(coreDashboardInsuranceVO.getId());
					}
					
					Map<Long,Long> constIdAndNoMap = new HashMap<Long,Long>();
					List<Object[]> constOrderList = delimitationConstituencyDAO.getConstituencyNumbersForConstituenctIds(locaionIds);
					
					for(Object[] param : constOrderList){
						constIdAndNoMap.put(commonMethodsUtilService.getLongValueForObject(param[0]), commonMethodsUtilService.getLongValueForObject(param[1]));
					}
					
					for(CoreDashboardInsuranceVO coreDashboardInsuranceVO : coreDashboardInsuranceVOs){
						coreDashboardInsuranceVO.setConstituencyNo(constIdAndNoMap.get(coreDashboardInsuranceVO.getId()));
					}
				}
				
				//sort list based on members
				if(sortingCondition != null && sortingCondition.equalsIgnoreCase("insuredMember")){
					if(order != null && order.equalsIgnoreCase("desc")){
						if(coreDashboardInsuranceVOs != null && coreDashboardInsuranceVOs.size() > 0){
							Collections.sort(coreDashboardInsuranceVOs, benefitedMemberCountForStatusDesc);
						}
					}else{
						if(coreDashboardInsuranceVOs != null && coreDashboardInsuranceVOs.size() > 0){
							Collections.sort(coreDashboardInsuranceVOs, benefitedMemberCountForStatusAsc);
						}
					}
				}
				
				//sort list based on name
				if(sortingCondition != null && sortingCondition.equalsIgnoreCase("name")){
					if(order != null && order.equalsIgnoreCase("desc")){
						if(coreDashboardInsuranceVOs != null && coreDashboardInsuranceVOs.size() > 0){
							Collections.sort(coreDashboardInsuranceVOs, benefitedLocationNameDesc);
						}
					}else{
						if(coreDashboardInsuranceVOs != null && coreDashboardInsuranceVOs.size() > 0){
							Collections.sort(coreDashboardInsuranceVOs, benefitedLocationNameAsc);
						}
					}
				}
				
				//sort list based on amount
				if(sortingCondition != null && sortingCondition.equalsIgnoreCase("amount")){
					if(order != null && order.equalsIgnoreCase("desc")){
						if(coreDashboardInsuranceVOs != null && coreDashboardInsuranceVOs.size() > 0){
							Collections.sort(coreDashboardInsuranceVOs, benefitedAmountDesc);
						}
					}else{
						if(coreDashboardInsuranceVOs != null && coreDashboardInsuranceVOs.size() > 0){
							Collections.sort(coreDashboardInsuranceVOs, benefitedAmountAsc);
						}
					}
				}
				
				//sort list based on constituency no
				if(sortingCondition != null && sortingCondition.equalsIgnoreCase("constituencyNo")){
					if(order != null && order.equalsIgnoreCase("desc")){
						if(coreDashboardInsuranceVOs != null && coreDashboardInsuranceVOs.size() > 0){
							Collections.sort(coreDashboardInsuranceVOs, constituencyNoDesc);
						}
					}else{
						if(coreDashboardInsuranceVOs != null && coreDashboardInsuranceVOs.size() > 0){
							Collections.sort(coreDashboardInsuranceVOs, constituencyNoAsc);
						}
					}
				}
				
				
				
				System.out.println("hi");  
				return coreDashboardInsuranceVOs;
			}catch(Exception e){
				LOG.error("Error occured at getLocationWiseThenStatusWiseInsuranceMemberCountForTS() in CoreDashboardInsuranceService class",e);
			}
			return null;
		}
		public static Comparator<CoreDashboardInsuranceVO> benefitedMemberCountDesc = new Comparator<CoreDashboardInsuranceVO>() {
			public int compare(CoreDashboardInsuranceVO member2, CoreDashboardInsuranceVO member1) {

				Long perc2 = member2.getTotalCategroyCount();
				Long perc1 = member1.getTotalCategroyCount();
				//descending order of count.
				return perc1.compareTo(perc2);  
			}
		};
		public static Comparator<CoreDashboardInsuranceVO> benefitedMemberCountAsc = new Comparator<CoreDashboardInsuranceVO>() {
			public int compare(CoreDashboardInsuranceVO member2, CoreDashboardInsuranceVO member1) {

				Long perc2 = member2.getTotalCategroyCount();
				Long perc1 = member1.getTotalCategroyCount();
				//descending order of count.
				return perc2.compareTo(perc1);
			}
		};
		public static Comparator<UserTypeVO> complaintCountDesc = new Comparator<UserTypeVO>() {
			public int compare(UserTypeVO member2, UserTypeVO member1) {
			Long count2 = member2.getTotalCount();
			Long count1 = member1.getTotalCount();
			//descending order of percantages.
			 return count1.compareTo(count2);
			}
		}; 
		public static Comparator<UserTypeVO> complaintCountAsc = new Comparator<UserTypeVO>() {
			public int compare(UserTypeVO member2, UserTypeVO member1) {
			Long count2 = member2.getTotalCount();
			Long count1 = member1.getTotalCount();
			//descending order of percantages.
			 return count2.compareTo(count1);
			}
		}; 
		
		public static Comparator<CoreDashboardInsuranceVO> benefitedLocationNameDesc = new Comparator<CoreDashboardInsuranceVO>() {
			public int compare(CoreDashboardInsuranceVO member2, CoreDashboardInsuranceVO member1) {

				String name2 = member2.getName();
				String name1 = member1.getName();
				//descending order of count.
				return name1.compareTo(name2);
			}
		};
		public static Comparator<CoreDashboardInsuranceVO> benefitedLocationNameAsc = new Comparator<CoreDashboardInsuranceVO>() {
			public int compare(CoreDashboardInsuranceVO member2, CoreDashboardInsuranceVO member1) {

				String name2 = member2.getName();
				String name1 = member1.getName();
				//descending order of count.
				return name2.compareTo(name1);
			}
		};
		public static Comparator<CoreDashboardInsuranceVO> benefitedAmountDesc = new Comparator<CoreDashboardInsuranceVO>() {
			public int compare(CoreDashboardInsuranceVO member2, CoreDashboardInsuranceVO member1) {

				Long amount2 = member2.getTotalAmount();
				Long amount1 = member1.getTotalAmount();
				//descending order of count.
				return amount1.compareTo(amount2);
			}
		};
		public static Comparator<CoreDashboardInsuranceVO> benefitedAmountAsc = new Comparator<CoreDashboardInsuranceVO>() {
			public int compare(CoreDashboardInsuranceVO member2, CoreDashboardInsuranceVO member1) {

				Long amount2 = member2.getTotalAmount();
				Long amount1 = member1.getTotalAmount();
				//descending order of count.
				return amount2.compareTo(amount1);
			}
		};
		public static Comparator<CoreDashboardInsuranceVO> constituencyNoDesc = new Comparator<CoreDashboardInsuranceVO>() {
			public int compare(CoreDashboardInsuranceVO member2, CoreDashboardInsuranceVO member1) {

				Long amount2 = member2.getConstituencyNo();
				Long amount1 = member1.getConstituencyNo();
				//descending order of count.
				return amount1.compareTo(amount2);
			}
		};
		public static Comparator<CoreDashboardInsuranceVO> constituencyNoAsc = new Comparator<CoreDashboardInsuranceVO>() {
			public int compare(CoreDashboardInsuranceVO member2, CoreDashboardInsuranceVO member1) {

				Long amount2 = member2.getConstituencyNo();
				Long amount1 = member1.getConstituencyNo();
				//descending order of count.
				return amount2.compareTo(amount1);
			}
		};
		
		public static Comparator<CoreDashboardInsuranceVO> benefitedMemberCountForStatusDesc = new Comparator<CoreDashboardInsuranceVO>() {
			public int compare(CoreDashboardInsuranceVO member2, CoreDashboardInsuranceVO member1) {

				Long perc2 = member2.getTotalStatusCount();
				Long perc1 = member1.getTotalStatusCount();
				//descending order of count.
				return perc1.compareTo(perc2);
			}
		};
		
		public static Comparator<CoreDashboardInsuranceVO> benefitedMemberCountForStatusAsc = new Comparator<CoreDashboardInsuranceVO>() {
			public int compare(CoreDashboardInsuranceVO member2, CoreDashboardInsuranceVO member1) {

				Long perc2 = member2.getTotalStatusCount();
				Long perc1 = member1.getTotalStatusCount();
				//descending order of count.
				return perc2.compareTo(perc1);
			}
		};
		
	public List<ComplaintMasterVO> getComplaintRaisedDetails(Long levelId,List<Long> locationValues,String startDateStr,String endDateStr,String type,String grievanceType,String year){
		List<ComplaintMasterVO> complaintList = new ArrayList<ComplaintMasterVO>(0);
		try {
			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
			Date fromDate = null;
			Date toDate = null;
			if(startDateStr != null && endDateStr != null){
				fromDate = sdf.parse(startDateStr);
				toDate = sdf.parse(endDateStr);
			}
			//Get complaintIds For levelwise
			List<Long> complaintIds = insuranceStatusDAO.getComplaintIdsByGrievanceType(levelId, locationValues, fromDate, toDate, type, grievanceType,Long.valueOf(year));
			if(complaintIds != null && !complaintIds.isEmpty())
				complaintList = getComplaintDetailsByComplaintIds(complaintIds,type);
		} catch (Exception e) {
			LOG.error("Error occured at getComplaintRaisedDetails() in CoreDashboardInsuranceService class",e);
		}
		return complaintList;
	}
	
	
	public List<ComplaintMasterVO> getComplaintDetailsByComplaintIds(List<Long> complaintIds,String type){
		List<ComplaintMasterVO> returnList = new ArrayList<ComplaintMasterVO>();
		try {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
			SimpleDateFormat sdf1 = new SimpleDateFormat("MMM dd,yyyy hh:mm:ss a");
			
			List<Object[]> list = insuranceStatusDAO.getComplaintIdsByGrievanceType(complaintIds,type);
			if(list != null && !list.isEmpty()){
				for (Object[] obj : list) {
					ComplaintMasterVO vo = new ComplaintMasterVO();
					
					vo.setComplaintId(Long.valueOf(obj[0] != null ? obj[0].toString():"0"));
					vo.setName(obj[1] != null ? obj[1].toString():"");
					vo.setMobileNo(obj[2] != null ? obj[2].toString():"");
					vo.setDistrictName(obj[3] != null ? obj[3].toString():"");
					vo.setConstituencyName(obj[4] != null ? obj[4].toString():"");
					vo.setMandalName(obj[5] != null ? obj[5].toString()+" Mandal":"");
					vo.setVillageName(obj[6] != null ? obj[6].toString():"");
					vo.setDescription(obj[7] != null ? obj[7].toString():"");
					vo.setSubject(obj[9] != null ? obj[9].toString():"");
					vo.setStatus(obj[10] != null ? obj[10].toString():"");
					vo.setPostedDate(obj[8] != null ? obj[8].toString() :null);
					if(vo.getPostedDate() != null){
						Date dated = sdf.parse(vo.getPostedDate());
						vo.setPostedDate(sdf1.format(dated));
					}
					returnList.add(vo);
				}
			}
			
			List<Object[]> commentList = insuranceStatusDAO.getLatestComplaintResponsesForComplaintIds(complaintIds);
			if(commentList != null && !commentList.isEmpty()){
				for (Object[] obj : commentList) {
					Long complaintId = Long.valueOf(obj[0] != null ? obj[0].toString():"0");
					String comment = obj[1] != null ? obj[1].toString():"";
					String updatedDate = obj[2] != null ? obj[2].toString():null;
					
					ComplaintMasterVO vo = getComplaintMasterMatchedVO(returnList, complaintId);
					if(vo != null){
						vo.setComment(comment);
						vo.setUpdatedDate(updatedDate);
						if(vo.getUpdatedDate() != null){
							Date dated = sdf.parse(vo.getUpdatedDate());
							vo.setUpdatedDate(sdf1.format(dated));
						}
					}
				}
			}
			
		} catch (Exception e) {
			LOG.error("Exception Occured in getComplaintDetailsByComplaintIds on CoreDashboardInsuranceService", e);
		}
		return returnList;
	}
}
