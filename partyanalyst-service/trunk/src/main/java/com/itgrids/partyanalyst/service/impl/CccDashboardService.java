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

import org.apache.log4j.Logger;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionCallback;
import org.springframework.transaction.support.TransactionTemplate;

import com.itgrids.partyanalyst.dao.IAlertAssignedOfficerActionDAO;
import com.itgrids.partyanalyst.dao.IAlertAssignedOfficerDAO;
import com.itgrids.partyanalyst.dao.IAlertAssignedOfficerTrackingDAO;
import com.itgrids.partyanalyst.dao.IAlertCandidateDAO;
import com.itgrids.partyanalyst.dao.IAlertDAO;
import com.itgrids.partyanalyst.dao.IAlertDepartmentCommentDAO;
import com.itgrids.partyanalyst.dao.IAlertDepartmentDocumentDAO;
import com.itgrids.partyanalyst.dao.IAlertDepartmentStatusDAO;
import com.itgrids.partyanalyst.dao.IAlertStatusDAO;
import com.itgrids.partyanalyst.dao.IBoothDAO;
import com.itgrids.partyanalyst.dao.IConstituencyDAO;
import com.itgrids.partyanalyst.dao.IDistrictDAO;
import com.itgrids.partyanalyst.dao.IGovtAlertDepartmentLocationDAO;
import com.itgrids.partyanalyst.dao.IGovtDepartmentDAO;
import com.itgrids.partyanalyst.dao.IGovtDepartmentDesignationDAO;
import com.itgrids.partyanalyst.dao.IGovtDepartmentDesignationOfficerDAO;
import com.itgrids.partyanalyst.dao.IGovtDepartmentDesignationOfficerDetailsDAO;
import com.itgrids.partyanalyst.dao.IGovtDepartmentLevelDAO;
import com.itgrids.partyanalyst.dao.IGovtDepartmentLevelDetailsDAO;
import com.itgrids.partyanalyst.dao.ILocalElectionBodyDAO;
import com.itgrids.partyanalyst.dao.IPanchayatDAO;
import com.itgrids.partyanalyst.dao.IStateDAO;
import com.itgrids.partyanalyst.dao.ITehsilDAO;
import com.itgrids.partyanalyst.dao.ITvNewsChannelDAO;  
import com.itgrids.partyanalyst.dto.AlertAssigningVO;
import com.itgrids.partyanalyst.dto.AlertCoreDashBoardVO;
import com.itgrids.partyanalyst.dto.AlertVO;
import com.itgrids.partyanalyst.dto.GovtDepartmentVO;
import com.itgrids.partyanalyst.dto.IdAndNameVO;
import com.itgrids.partyanalyst.model.Alert;
import com.itgrids.partyanalyst.model.AlertAssignedOfficer;
import com.itgrids.partyanalyst.model.AlertAssignedOfficerAction;
import com.itgrids.partyanalyst.model.AlertAssignedOfficerTracking;
import com.itgrids.partyanalyst.model.AlertDepartmentComment;
import com.itgrids.partyanalyst.model.AlertDepartmentDocument;
import com.itgrids.partyanalyst.model.AlertStatus;
import com.itgrids.partyanalyst.service.ICccDashboardService;
import com.itgrids.partyanalyst.utils.CommonMethodsUtilService;
import com.itgrids.partyanalyst.utils.DateUtilService;
import com.itgrids.partyanalyst.utils.IConstants;

public class CccDashboardService extends AlertService implements ICccDashboardService{
	private static final Logger logger = Logger.getLogger(CccDashboardService.class);
	private IAlertDAO alertDAO;
	private ITvNewsChannelDAO tvNewsChannelDAO;
	private IAlertStatusDAO alertStatusDAO;
	private IGovtDepartmentDAO govtDepartmentDAO;
	private CommonMethodsUtilService commonMethodsUtilService;
	private IGovtDepartmentLevelDAO govtDepartmentLevelDAO;
	private IGovtAlertDepartmentLocationDAO govtAlertDepartmentLocationDAO;
	private IStateDAO stateDAO;
	private IDistrictDAO districtDAO;
	private IConstituencyDAO constituencyDAO;
	private ITehsilDAO tehsilDAO;
	private ILocalElectionBodyDAO localElectionBodyDAO;
	private IGovtDepartmentDesignationDAO govtDepartmentDesignationDAO;
	private IGovtDepartmentDesignationOfficerDetailsDAO govtDepartmentDesignationOfficerDetailsDAO;
	private TransactionTemplate transactionTemplate = null;
	private IAlertDepartmentCommentDAO alertDepartmentCommentDAO;
	private IAlertDepartmentDocumentDAO alertDepartmentDocumentDAO;
	private IGovtDepartmentDesignationOfficerDAO govtDepartmentDesignationOfficerDAO;
	private IAlertAssignedOfficerDAO alertAssignedOfficerDAO;
	private IAlertAssignedOfficerTrackingDAO alertAssignedOfficerTrackingDAO;
	private IAlertAssignedOfficerActionDAO alertAssignedOfficerActionDAO;
	private IAlertDepartmentStatusDAO alertDepartmentStatusDAO;
	private IBoothDAO boothDAO;
	private IAlertCandidateDAO alertCandidateDAO;
	private IGovtDepartmentLevelDetailsDAO govtDepartmentLevelDetailsDAO;
	private IPanchayatDAO panchayatDAO;
	
	
	public IPanchayatDAO getPanchayatDAO() {
		return panchayatDAO;
	}

	public void setPanchayatDAO(IPanchayatDAO panchayatDAO) {
		this.panchayatDAO = panchayatDAO;
	}

	public void setAlertDepartmentStatusDAO(
			IAlertDepartmentStatusDAO alertDepartmentStatusDAO) {
		this.alertDepartmentStatusDAO = alertDepartmentStatusDAO;
	}
	
	public IAlertCandidateDAO getAlertCandidateDAO() {
		return alertCandidateDAO;
	}
	public void setAlertCandidateDAO(IAlertCandidateDAO alertCandidateDAO) {
		this.alertCandidateDAO = alertCandidateDAO;
	}
	public IBoothDAO getBoothDAO() {
		return boothDAO;
	}
	public void setBoothDAO(IBoothDAO boothDAO) {
		this.boothDAO = boothDAO;
	}
	public void setGovtAlertDepartmentLocationDAO(
			IGovtAlertDepartmentLocationDAO govtAlertDepartmentLocationDAO) {
		this.govtAlertDepartmentLocationDAO = govtAlertDepartmentLocationDAO;
	}
	public void setTvNewsChannelDAO(ITvNewsChannelDAO tvNewsChannelDAO) {
		this.tvNewsChannelDAO = tvNewsChannelDAO;
	}
	public IAlertDepartmentDocumentDAO getAlertDepartmentDocumentDAO() {
		return alertDepartmentDocumentDAO;
	}
	public void setAlertDepartmentDocumentDAO(
			IAlertDepartmentDocumentDAO alertDepartmentDocumentDAO) {
		this.alertDepartmentDocumentDAO = alertDepartmentDocumentDAO;
	}
	public IAlertAssignedOfficerActionDAO getAlertAssignedOfficerActionDAO() {
		return alertAssignedOfficerActionDAO;
	}
	public void setAlertAssignedOfficerActionDAO(
			IAlertAssignedOfficerActionDAO alertAssignedOfficerActionDAO) {
		this.alertAssignedOfficerActionDAO = alertAssignedOfficerActionDAO;
	}
	public IAlertAssignedOfficerTrackingDAO getAlertAssignedOfficerTrackingDAO() {
		return alertAssignedOfficerTrackingDAO;
	}
	public void setAlertAssignedOfficerTrackingDAO(
			IAlertAssignedOfficerTrackingDAO alertAssignedOfficerTrackingDAO) {
		this.alertAssignedOfficerTrackingDAO = alertAssignedOfficerTrackingDAO;
	}
	public IAlertAssignedOfficerDAO getAlertAssignedOfficerDAO() {
		return alertAssignedOfficerDAO;
	}
	public void setAlertAssignedOfficerDAO(
			IAlertAssignedOfficerDAO alertAssignedOfficerDAO) {
		this.alertAssignedOfficerDAO = alertAssignedOfficerDAO;
	}
	public IGovtDepartmentDesignationOfficerDAO getGovtDepartmentDesignationOfficerDAO() {
		return govtDepartmentDesignationOfficerDAO;
	}
	public void setGovtDepartmentDesignationOfficerDAO(
			IGovtDepartmentDesignationOfficerDAO govtDepartmentDesignationOfficerDAO) {
		this.govtDepartmentDesignationOfficerDAO = govtDepartmentDesignationOfficerDAO;
	}
	public IAlertDepartmentCommentDAO getAlertDepartmentCommentDAO() {
		return alertDepartmentCommentDAO;
	}
	public void setAlertDepartmentCommentDAO(
			IAlertDepartmentCommentDAO alertDepartmentCommentDAO) {
		this.alertDepartmentCommentDAO = alertDepartmentCommentDAO;
	}
	public TransactionTemplate getTransactionTemplate() {
		return transactionTemplate;
	}
	public void setTransactionTemplate(TransactionTemplate transactionTemplate) {
		this.transactionTemplate = transactionTemplate;
	}
	public IGovtDepartmentDesignationOfficerDetailsDAO getGovtDepartmentDesignationOfficerDetailsDAO() {
		return govtDepartmentDesignationOfficerDetailsDAO;
	}
	public void setGovtDepartmentDesignationOfficerDetailsDAO(
			IGovtDepartmentDesignationOfficerDetailsDAO govtDepartmentDesignationOfficerDetailsDAO) {
		this.govtDepartmentDesignationOfficerDetailsDAO = govtDepartmentDesignationOfficerDetailsDAO;
	}
	public IGovtDepartmentDesignationDAO getGovtDepartmentDesignationDAO() {
		return govtDepartmentDesignationDAO;
	}
	public void setGovtDepartmentDesignationDAO(IGovtDepartmentDesignationDAO govtDepartmentDesignationDAO) {
		this.govtDepartmentDesignationDAO = govtDepartmentDesignationDAO;
	}
	public ILocalElectionBodyDAO getLocalElectionBodyDAO() {
		return localElectionBodyDAO;
	}
	public void setLocalElectionBodyDAO(ILocalElectionBodyDAO localElectionBodyDAO) {
		this.localElectionBodyDAO = localElectionBodyDAO;
	}
	public ITehsilDAO getTehsilDAO() {
		return tehsilDAO;
	}
	public void setTehsilDAO(ITehsilDAO tehsilDAO) {
		this.tehsilDAO = tehsilDAO;
	}
	public IConstituencyDAO getConstituencyDAO() {
		return constituencyDAO;
	}
	public void setConstituencyDAO(IConstituencyDAO constituencyDAO) {
		this.constituencyDAO = constituencyDAO;
	}
	public IDistrictDAO getDistrictDAO() {
		return districtDAO;
	}
	public void setDistrictDAO(IDistrictDAO districtDAO) {
		this.districtDAO = districtDAO;
	}
	public IStateDAO getStateDAO() {
		return stateDAO;
	}
	public void setStateDAO(IStateDAO stateDAO) {
		this.stateDAO = stateDAO;
	}
	public IGovtDepartmentLevelDAO getGovtDepartmentLevelDAO() {
		return govtDepartmentLevelDAO;
	}
	public void setGovtDepartmentLevelDAO(IGovtDepartmentLevelDAO govtDepartmentLevelDAO) {
		this.govtDepartmentLevelDAO = govtDepartmentLevelDAO;
	}
	public void setGovtDepartmentDAO(IGovtDepartmentDAO govtDepartmentDAO) {
		this.govtDepartmentDAO = govtDepartmentDAO;
	}
	public void setAlertDAO(IAlertDAO alertDAO) {
		this.alertDAO = alertDAO;
	}
	public void setAlertStatusDAO(IAlertStatusDAO alertStatusDAO) {
		this.alertStatusDAO = alertStatusDAO;
	}

	public void setCommonMethodsUtilService(
			CommonMethodsUtilService commonMethodsUtilService) {
		this.commonMethodsUtilService = commonMethodsUtilService;
	}
	public IGovtDepartmentLevelDetailsDAO getGovtDepartmentLevelDetailsDAO() {
		return govtDepartmentLevelDetailsDAO;
	}
	public void setGovtDepartmentLevelDetailsDAO(IGovtDepartmentLevelDetailsDAO govtDepartmentLevelDetailsDAO) {
		this.govtDepartmentLevelDetailsDAO = govtDepartmentLevelDetailsDAO;
	}

	//business methods
	/*
	 * Swadhin(non-Javadoc)
	 * @see com.itgrids.partyanalyst.service.ICccDashboardService#getTotalAlertGroupByStatus(String fromDateStr, String toDateStr, Long stateId, List<Long> printIdList, List<Long> electronicIdList, List<Long> deptIdList)
	 */
	public List<AlertVO> getTotalAlertGroupByStatus(String fromDateStr, String toDateStr, Long stateId, List<Long> printIdList, List<Long> electronicIdList, List<Long> deptIdList,Long userId){
		logger.info("Entered in getTotalAlertGroupByStatus() method of CccDashboardService{}");
		try{
			Date fromDate = null;
			Date toDate = null;
			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
			if(fromDateStr != null && fromDateStr.trim().length() > 0 && toDateStr != null && toDateStr.trim().length() > 0){
				fromDate = sdf.parse(fromDateStr);
				toDate = sdf.parse(toDateStr);
			}
			List<AlertVO> finalAlertVOs = new ArrayList<AlertVO>();
			if(printIdList != null && printIdList.size() > 0){  
				if(electronicIdList != null && electronicIdList.size() == 0){
					electronicIdList.add(0L);
				}
			}else if(electronicIdList != null && electronicIdList.size() > 0){
				if(printIdList != null && printIdList.size() == 0){
					printIdList.add(0L);
				}
			}
			//get all the alert status and build the template
			List<Object[]> statusList = alertStatusDAO.getAllStatus();
			//get alert status count and and create a map of alertStatusId and its count
			List<Object[]> totalList = new ArrayList<Object[]>();
			List<Object[]> alertCountList = alertDAO.getTotalAlertGroupByStatusForGovt(fromDate,toDate,stateId,printIdList,electronicIdList,deptIdList);//for pending status
			if(alertCountList != null && alertCountList.size() > 0){
				totalList.addAll(alertCountList);
			}
			List<Long> levelValues = new ArrayList<Long>();    
			Long levelId = 0L;
			List<Object[]> lvlValueAndLvlIdList = govtAlertDepartmentLocationDAO.getUserAccessLevels(userId);
			if(lvlValueAndLvlIdList != null && lvlValueAndLvlIdList.size() > 0){
				for(Object[] param : lvlValueAndLvlIdList){
					levelValues.add(commonMethodsUtilService.getLongValueForObject(param[1]));
					levelId = commonMethodsUtilService.getLongValueForObject(param[0]);
				}
			}
			String type = "status";
			List<Object[]> alertCountList2 = alertAssignedOfficerDAO.getAlertCountForCccAdminLongin(fromDate,toDate,stateId,printIdList,electronicIdList,deptIdList,levelId,levelValues,type);
			if(alertCountList2 != null && alertCountList2.size() > 0){
				totalList.addAll(alertCountList2);
			}
			buildStatusWiseAlertCount(statusList,totalList,finalAlertVOs);  
			return finalAlertVOs; 
		}catch(Exception e){
			e.printStackTrace();
			logger.error("Error occured getTotalAlertGroupByStatus() method of CccDashboardService{}");
		}
		return null;
	}
	/*
	 * Swadhin(non-Javadoc)
	 * @see com.itgrids.partyanalyst.service.ICccDashboardService#getTotalAlertGroupByStatusThenDepartment(String fromDateStr, String toDateStr, Long stateId, List<Long> printIdList, List<Long> electronicIdList, List<Long> deptIdList)
	 */
	public List<AlertVO> getTotalAlertGroupByStatusThenDepartment(String fromDateStr, String toDateStr, Long stateId, List<Long> printIdList, List<Long> electronicIdList, List<Long> deptIdList,Long userId,String sortingType){
		logger.info("Entered in getTotalAlertGroupByStatusThenDepartment() method of CccDashboardService{}");
		try{
			Date fromDate = null;
			Date toDate = null;
			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
			if(fromDateStr != null && fromDateStr.trim().length() > 0 && toDateStr != null && toDateStr.trim().length() > 0){
				fromDate = sdf.parse(fromDateStr);
				toDate = sdf.parse(toDateStr);
			}
			
			Map<Long,Long> statusIdAndCountMap = new HashMap<Long,Long>();
			List<AlertVO> finalListNew = new ArrayList<AlertVO>();
			//get all the alert category for  building the template
			List<Object[]> deptList = govtDepartmentDAO.getAllDepartment(); 
			if(printIdList != null && printIdList.size() > 0){  
				if(electronicIdList != null && electronicIdList.size() == 0){
					electronicIdList.add(0L);
				}
			}else if(electronicIdList != null && electronicIdList.size() > 0){
				if(printIdList != null && printIdList.size() == 0){
					printIdList.add(0L);
				}
			}
			
			List<Long> levelValues = new ArrayList<Long>();    
			Long levelId = 0L;
			List<Object[]> lvlValueAndLvlIdList = govtAlertDepartmentLocationDAO.getUserAccessLevels(userId);
			if(lvlValueAndLvlIdList != null && lvlValueAndLvlIdList.size() > 0){
				for(Object[] param : lvlValueAndLvlIdList){
					levelValues.add(commonMethodsUtilService.getLongValueForObject(param[1]));
					levelId = commonMethodsUtilService.getLongValueForObject(param[0]);
				}
			}
			
			//get alert status count and and create a map of alertStatusId and its corresponding  alert count
			String type = "status";
			List<Object[]> totalAlertCountList = new ArrayList<Object[]>();
			List<Object[]> alertCountList = alertDAO.getTotalAlertGroupByStatusForGovt(fromDate,toDate,stateId,printIdList,electronicIdList,deptIdList);
			if(alertCountList != null && alertCountList.size() > 0){
				totalAlertCountList.addAll(alertCountList);
			}
			List<Object[]> alertCountList2 = alertAssignedOfficerDAO.getAlertCountForCccAdminLongin(fromDate,toDate,stateId,printIdList,electronicIdList,deptIdList,levelId,levelValues,type);
			if(alertCountList2 != null && alertCountList2.size() > 0){
				totalAlertCountList.addAll(alertCountList2);
			}
			if(totalAlertCountList != null && totalAlertCountList.size() > 0){
				for(Object[] param : totalAlertCountList){
					statusIdAndCountMap.put(commonMethodsUtilService.getLongValueForObject(param[0]), commonMethodsUtilService.getLongValueForObject(param[2]));
				}
			}
			type = "department";
			List<Object[]> totalAlertCountGrpByDeptList = new ArrayList<Object[]>();
			List<Object[]> alertCountGrpByDeptList = alertDAO.getTotalAlertGroupByStatusThenDepartmentForGovt(fromDate,toDate,stateId,printIdList,electronicIdList,deptIdList);
			if(alertCountGrpByDeptList != null && alertCountGrpByDeptList.size() > 0){
				totalAlertCountGrpByDeptList.addAll(alertCountGrpByDeptList);
			}
			List<Object[]> alertCountGrpByDeptList2 = alertAssignedOfficerDAO.getAlertCountForCccAdminLongin(fromDate,toDate,stateId,printIdList,electronicIdList,deptIdList,levelId,levelValues,type);
			if(alertCountGrpByDeptList2 != null && alertCountGrpByDeptList2.size() > 0){
				totalAlertCountGrpByDeptList.addAll(alertCountGrpByDeptList2);
			}
			buildAlertGroupByStatusThenDepartment(totalAlertCountGrpByDeptList,statusIdAndCountMap,deptList,finalListNew,"true");
			if(sortingType != null && !sortingType.trim().isEmpty() &&  sortingType.equalsIgnoreCase("asc")){
				if(finalListNew != null && finalListNew.size() > 0){
					for(AlertVO alertVO : finalListNew){
						Collections.sort(alertVO.getSubList1(),departmentWiseAlertsSortAsc);
					}
				}
			}else{
				if(finalListNew != null && finalListNew.size() > 0){
					for(AlertVO alertVO : finalListNew){
						Collections.sort(alertVO.getSubList1(),departmentWiseAlertsSortDsc);
					}
				}
			}
			
			return finalListNew;     
		}catch(Exception e){
			e.printStackTrace();
			logger.error("Error occured getTotalAlertGroupByStatusThenDepartment() method of CccDashboardService{}");
		}
		return null;
	}
	
	public static Comparator<AlertVO> departmentWiseAlertsSortDsc = new Comparator<AlertVO>(){
		public int compare(AlertVO obj1, AlertVO obj2)
		{
			return (obj2.getCategoryCount().intValue()) - (obj1.getCategoryCount().intValue());
		}
	};
	public static Comparator<AlertVO> departmentWiseAlertsSortAsc = new Comparator<AlertVO>(){
		public int compare(AlertVO obj1, AlertVO obj2)
		{
			return (obj1.getCategoryCount().intValue()) - (obj2.getCategoryCount().intValue());
		}
	};
	/*
	 * Swadhin(non-Javadoc)
	 * @see com.itgrids.partyanalyst.service.ICccDashboardService#getTotalAlertByStatus(String fromDateStr, String toDateStr, Long stateId, List<Long> printIdList, List<Long> electronicIdList, List<Long> deptIdList,Long statusId)
	 */
	public List<AlertCoreDashBoardVO> getTotalAlertByStatus(String fromDateStr, String toDateStr, Long stateId, List<Long> printIdList, List<Long> electronicIdList, List<Long> deptIdList,Long statusId){
		logger.info("Entered in getTotalAlertByStatus() method of CccDashboardService{}");
		try{
			Date fromDate = null;
			Date toDate = null;
			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
			if(fromDateStr != null && fromDateStr.trim().length() > 0 && toDateStr != null && toDateStr.trim().length() > 0){
				fromDate = sdf.parse(fromDateStr);
				toDate = sdf.parse(toDateStr);
			}
			List<AlertCoreDashBoardVO> alertCoreDashBoardVOs = new ArrayList<AlertCoreDashBoardVO>();
			List<Object[]> alertList = alertDAO.getTotalAlertByStatus(fromDate,toDate,stateId,printIdList,electronicIdList,deptIdList,statusId);
			setAlertDtls(alertCoreDashBoardVOs, alertList);    
			return alertCoreDashBoardVOs;
		}catch(Exception e){
			e.printStackTrace();
			logger.error("Error occured getTotalAlertByStatus() method of CccDashboardService{}");
		}
		return null;
	}
	public Double calculatePercantage(Long subCount,Long totalCount){
		Double d=0.0d;
		if(subCount.longValue()>0l && totalCount.longValue()==0l)
			logger.error("Sub Count Value is "+subCount+" And Total Count Value  "+totalCount);

		if(totalCount.longValue() > 0l){
			 d = new BigDecimal(subCount * 100.0/totalCount).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();	 
		}
		return d;
	}
	
	public List<GovtDepartmentVO> getDepartmentLevels(){
		List<GovtDepartmentVO> returnList = new ArrayList<GovtDepartmentVO>();
		try {
			List<Object[]> list = govtDepartmentLevelDAO.getDepartmentLevels();
			if(list != null && !list.isEmpty()){
				for (Object[] obj : list) {
					GovtDepartmentVO vo = new GovtDepartmentVO();
					vo.setId(Long.valueOf(obj[0] != null ? obj[0].toString():"0"));
					vo.setName(obj[1] != null ? obj[1].toString():"");
					returnList.add(vo);
				}
			}
		} catch (Exception e) {
			logger.error("Error occured getDepartmentLevels() method of CccDashboardService",e);
		}
		return returnList;
	}
	
	public List<GovtDepartmentVO> getLocationsBasedOnLevel(Long levelId){
		List<GovtDepartmentVO> returnList = new ArrayList<GovtDepartmentVO>();
		try {
			List<Object[]> list = new ArrayList<Object[]>();
			
			if(levelId != null && levelId > 0l){
				if(levelId == 2l)							//State
					list = stateDAO.getTeluguStates();
				else if(levelId == 3l)						//District
					list = districtDAO.getDistrictsWithNewDistricts();
				else if(levelId >= 4l)						//Constituenncy
					list = constituencyDAO.getConstituencyByStateDetails();
			/*	else if(levelId == 4l){						//Mandal/Muncipality
					List<Object[]> mandalList = tehsilDAO.getAllTehsilListByState();
					List<Object[]> lebList = localElectionBodyDAO.getAllLocalElectionBodyListByState();
					
					list.addAll(mandalList);
					list.addAll(lebList);
				}*/
				
				if(list != null && !list.isEmpty()){
					for (Object[] obj : list) {
						GovtDepartmentVO vo = new GovtDepartmentVO();
						
						vo.setId(Long.valueOf(obj[0] != null ? obj[0].toString():"0"));
						vo.setName(obj[1] != null ? obj[1].toString():"");
						returnList.add(vo);
					}
				}
			}
		} catch (Exception e) {
			logger.error("Error occured getLocationsBasedOnLevel() method of CccDashboardService",e);
		}
		return returnList;
	}
	
	public List<GovtDepartmentVO> getMandalsForConstituency(Long constituencyId){
		List<GovtDepartmentVO> returnList = new ArrayList<GovtDepartmentVO>();
		try {
			List<Object[]> list = boothDAO.getTehsilListByConstituency(constituencyId, 22l);
			if(list != null && !list.isEmpty()){
				for (Object[] obj : list) {
					GovtDepartmentVO vo = new GovtDepartmentVO();
					
					vo.setId(Long.valueOf(obj[0] != null ? obj[0].toString():"0"));
					vo.setId(Long.valueOf("1"+vo.getId().toString()));
					vo.setName(obj[1] != null ? obj[1].toString():"");
					vo.setName(vo.getName()+" Mandal");
					returnList.add(vo);
				}
			}
		} catch (Exception e) {
			logger.error("Error occured getMandalsForConstituency() method of CccDashboardService",e);
		}
		return returnList;
	}
	
	public List<GovtDepartmentVO> getLebsForConstituency(Long constituencyId){
		List<GovtDepartmentVO> returnList = new ArrayList<GovtDepartmentVO>();
		try {
			List<Object[]> list = boothDAO.getLEBListByConstituency(constituencyId, 22l);
			if(list != null && !list.isEmpty()){
				for (Object[] obj : list) {
					GovtDepartmentVO vo = new GovtDepartmentVO();
					
					vo.setId(Long.valueOf(obj[0] != null ? obj[0].toString():"0"));
					vo.setId(Long.valueOf("2"+vo.getId().toString()));
					vo.setName(obj[1] != null ? obj[1].toString():"");
					vo.setName(vo.getName()+" Muncipality");
					returnList.add(vo);
				}
			}
		} catch (Exception e) {
			logger.error("Error occured getLebsForConstituency() method of CccDashboardService",e);
		}
		return returnList;
	}
	
	public List<GovtDepartmentVO> getPanchayatsMandalId(Long mandalId,Long constituencyId){
		List<GovtDepartmentVO> returnList = new ArrayList<GovtDepartmentVO>();
		try {
			Long level = Long.valueOf(mandalId.toString().substring(0,1));
			Long manlId = Long.valueOf(mandalId.toString().substring(1));
			List<Long> mandalIds = new ArrayList<Long>();
			mandalIds.add(manlId);
			
			List<Object[]> list = null;
			if(level != null && level.longValue() == 1l)
				list = boothDAO.getPanchayatsForConstituency(mandalIds, 22l);
			else if(level != null && level.longValue() == 2l)
				list = boothDAO.getWardsByLocalElecBodyId(manlId, 22l, constituencyId);
			
			if(list != null && !list.isEmpty()){
				for (Object[] obj : list) {
					GovtDepartmentVO vo = new GovtDepartmentVO();
					
					vo.setId(Long.valueOf(obj[0] != null ? obj[0].toString():"0"));
					vo.setName(obj[1] != null ? obj[1].toString():"");
					returnList.add(vo);
				}
			}
		} catch (Exception e) {
			logger.error("Error occured getLebsForConstituency() method of CccDashboardService",e);
		}
		return returnList;
	}
	
	public List<GovtDepartmentVO> getDepartmentsByAlert(Long alertId){
		List<GovtDepartmentVO> returnList = new ArrayList<GovtDepartmentVO>();
		try {
			List<Object[]> list = alertDAO.getDepartmentsByAlertId(alertId);
			if(list != null && !list.isEmpty()){
				for (Object[] obj : list) {
					GovtDepartmentVO vo = new GovtDepartmentVO();
					
					vo.setId(Long.valueOf(obj[0] != null ? obj[0].toString():"0"));
					vo.setName(obj[1] != null ? obj[1].toString():"");
					returnList.add(vo);
				}
			}
		} catch (Exception e) {
			logger.error("Error occured getDepartmentsByAlert() method of CccDashboardService",e);
		}
		return returnList;
	}
	
	public List<GovtDepartmentVO> getDesignationsByDepartment(Long departmentId,Long levelId){
		List<GovtDepartmentVO> returnList = new ArrayList<GovtDepartmentVO>();
		try {
			List<Object[]> list = govtDepartmentDesignationDAO.getDesignationsForDepartment(departmentId,levelId);
			if(list != null && !list.isEmpty()){
				for (Object[] obj : list) {
					GovtDepartmentVO vo = new GovtDepartmentVO();
					
					vo.setId(Long.valueOf(obj[0] != null ? obj[0].toString():"0"));
					vo.setName(obj[1] != null ? obj[1].toString():"");
					returnList.add(vo);
				}
			}
		} catch (Exception e) {
			logger.error("Error occured getDesignationsByDepartment() method of CccDashboardService",e);
		}
		return returnList;
	}
	
	public List<GovtDepartmentVO> getOfficersByDesignationAndLevel(Long levelId,Long levelValue,Long designationId){
		List<GovtDepartmentVO> returnList = new ArrayList<GovtDepartmentVO>();
		try {
			List<Object[]> list = govtDepartmentDesignationOfficerDetailsDAO.getOfficersByDesignationAndLevel(levelId, levelValue, designationId);
			if(list != null && !list.isEmpty()){
				for (Object[] obj : list) {
					GovtDepartmentVO vo = new GovtDepartmentVO();
					
					vo.setId(Long.valueOf(obj[0] != null ? obj[0].toString():"0"));
					vo.setName(obj[1] != null ? obj[1].toString():"");
					returnList.add(vo);
				}
			}
		} catch (Exception e) {
			logger.error("Error occured getOfficersByDesignationAndLevel() method of CccDashboardService",e);
		}
		return returnList;
	}
	/*
	 * Swadhin(non-Javadoc)
	 */
	public List<IdAndNameVO> getNewsPapaerList(){
		try{
			List<IdAndNameVO> list = new ArrayList<IdAndNameVO>();
			IdAndNameVO idAndNameVO = null;
			List<Object[]> newsPaperList = alertDAO.getNewsPapaerList();
			if(newsPaperList != null && newsPaperList.size() > 0){  
				for(Object[] param : newsPaperList){
					idAndNameVO = new IdAndNameVO();
					idAndNameVO.setId(commonMethodsUtilService.getLongValueForObject(param[0]));
					idAndNameVO.setName(commonMethodsUtilService.getStringValueForObject(param[1]));
					list.add(idAndNameVO);
				}
			}
			return list;
		}catch(Exception e){
			e.printStackTrace();
			logger.error("Error occured getNewsPapaerList() method of CccDashboardService",e);
		}
		return null;
	}
	/*
	 * Swadhin(non-Javadoc)
	 */
	public List<IdAndNameVO> getChannelList(){
		try{
			List<IdAndNameVO> list = new ArrayList<IdAndNameVO>();
			IdAndNameVO idAndNameVO = null;
			List<Object[]> channelList = tvNewsChannelDAO.getChannelList();
			if(channelList != null && channelList.size() > 0){  
				for(Object[] param : channelList){
					idAndNameVO = new IdAndNameVO();
					idAndNameVO.setId(commonMethodsUtilService.getLongValueForObject(param[0]));
					idAndNameVO.setName(commonMethodsUtilService.getStringValueForObject(param[1]));
					list.add(idAndNameVO);
				}
			}
			return list;
		}catch(Exception e){
			e.printStackTrace();
			logger.error("Error occured getNewsPapaerList() method of CccDashboardService",e);
		}
		return null;
	}
	/*
	 * Swadhin(non-Javadoc)
	 */
	public List<IdAndNameVO> getDeptList(){
		try{
			List<IdAndNameVO> list = new ArrayList<IdAndNameVO>();
			IdAndNameVO idAndNameVO = null;
			List<Object[]> deptList = govtDepartmentDAO.getAllDepartment();
			if(deptList != null && deptList.size() > 0){  
				for(Object[] param : deptList){
					idAndNameVO = new IdAndNameVO();
					idAndNameVO.setId(commonMethodsUtilService.getLongValueForObject(param[0]));
					idAndNameVO.setName(commonMethodsUtilService.getStringValueForObject(param[1]));
					list.add(idAndNameVO);
				}
			}
			return list;
		}catch(Exception e){
			e.printStackTrace();
			logger.error("Error occured getNewsPapaerList() method of CccDashboardService",e);
		}
		return null;
	}
	
	public String assigningAlertToOfficer(final AlertAssigningVO inputvo){
		String status = null;
		try {
			status = (String)transactionTemplate.execute(new TransactionCallback() {
				public Object doInTransaction(TransactionStatus arg0) {
					
					List<Long> documentIds = new ArrayList<Long>(0);
					
					//Comments Saving
					AlertDepartmentComment alertDepartmentComment = new AlertDepartmentComment();
					alertDepartmentComment.setComment(inputvo.getComment());
					alertDepartmentComment.setInsertedBy(inputvo.getUserId());
					alertDepartmentComment.setInsertedTime(new DateUtilService().getCurrentDateAndTime());
					alertDepartmentComment = alertDepartmentCommentDAO.save(alertDepartmentComment);
					
					//Documents Saving
					if(inputvo.getDocumentsList() != null && !inputvo.getDocumentsList().isEmpty()){
						for (String path : inputvo.getDocumentsList()) {
							AlertDepartmentDocument alertDepartmentDocument = new AlertDepartmentDocument();
							alertDepartmentDocument.setDocument(path);
							alertDepartmentDocument.setInsertedBy(inputvo.getUserId());
							alertDepartmentDocument.setInsertedTime(new DateUtilService().getCurrentDateAndTime());
							alertDepartmentDocument = alertDepartmentDocumentDAO.save(alertDepartmentDocument);
							documentIds.add(alertDepartmentDocument.getAlertDepartmentDocumentId());
						}
					}
					
					
					//Alert Status Updation
					Alert alert = alertDAO.get(inputvo.getAlertId());
					alert.setAlertStatusId(2l);
					alert.setUpdatedTime(new DateUtilService().getCurrentDateAndTime());
					alert.setUpdatedBy(inputvo.getUserId());
					alert = alertDAO.save(alert);
					
					if(inputvo.getLevelId() == 5l || inputvo.getLevelId() == 7l){
						Long levelVal = Long.valueOf(inputvo.getLevelValue().toString().substring(1));
						inputvo.setLevelValue(levelVal);
					}
					//Get Department Designation Officer Ids
					Long desigOfficerId = null;
					List<Long> designationOfficerIds = govtDepartmentDesignationOfficerDetailsDAO.getDesignationOfficerIds(inputvo.getLevelId(), inputvo.getLevelValue(), inputvo.getDesignationId(), inputvo.getGovtOfficerId());
					if(designationOfficerIds != null && !designationOfficerIds.isEmpty())
						desigOfficerId = designationOfficerIds.get(0);
					
					//Officer Assigning
					AlertAssignedOfficer alertAssignedOfficer = new AlertAssignedOfficer();
					alertAssignedOfficer.setAlertId(inputvo.getAlertId());
					alertAssignedOfficer.setGovtDepartmentDesignationOfficerId(desigOfficerId);
					alertAssignedOfficer.setGovtOfficerId(inputvo.getGovtOfficerId());
					alertAssignedOfficer.setInsertedBy(inputvo.getUserId());
					alertAssignedOfficer.setUpdatedBy(inputvo.getUserId());
					alertAssignedOfficer.setInsertedTime(new DateUtilService().getCurrentDateAndTime());
					alertAssignedOfficer.setUpdatedTime(new DateUtilService().getCurrentDateAndTime());
					alertAssignedOfficer.setAlertStatusId(2l);
					alertAssignedOfficer = alertAssignedOfficerDAO.save(alertAssignedOfficer);
					
					//Officer Assigning Tracking
					AlertAssignedOfficerTracking alertAssignedOfficerTracking = new AlertAssignedOfficerTracking();
					alertAssignedOfficerTracking.setAlertAssignedOfficerId(alertAssignedOfficer.getAlertAssignedOfficerId());
					alertAssignedOfficerTracking.setAlertId(inputvo.getAlertId());
					alertAssignedOfficerTracking.setGovtDepartmentDesignationOfficerId(desigOfficerId);
					alertAssignedOfficerTracking.setGovtOfficerId(inputvo.getGovtOfficerId());
					alertAssignedOfficerTracking.setInsertedBy(inputvo.getUserId());
					alertAssignedOfficerTracking.setUpdatedBy(inputvo.getUserId());
					alertAssignedOfficerTracking.setInsertedTime(new DateUtilService().getCurrentDateAndTime());
					alertAssignedOfficerTracking.setUpdatedTime(new DateUtilService().getCurrentDateAndTime());
					alertAssignedOfficerTracking.setAlertStatusId(2l);
					alertAssignedOfficerTracking = alertAssignedOfficerTrackingDAO.save(alertAssignedOfficerTracking);
					
					//Alert Assigned Officer Action Saving
					if(documentIds != null && !documentIds.isEmpty()){
						for (int i = 0; i < documentIds.size(); i++) {
							AlertAssignedOfficerAction alertAssignedOfficerAction = new AlertAssignedOfficerAction();
							alertAssignedOfficerAction.setAlertId(inputvo.getAlertId());
							alertAssignedOfficerAction.setAlertAssignedOfficerId(alertAssignedOfficer.getAlertAssignedOfficerId());
							alertAssignedOfficerAction.setGovtOfficerId(inputvo.getGovtOfficerId());
							alertAssignedOfficerAction.setAlertStatusId(2l);
							if(i == 0)
								alertAssignedOfficerAction.setAlertDepartmentCommentId(alertDepartmentComment.getAlertDepartmentCommentId());
								
							alertAssignedOfficerAction.setAlertDepartmentDocumentId(documentIds.get(i));
							alertAssignedOfficerAction.setInsertedBy(inputvo.getUserId());
							alertAssignedOfficerAction.setUpdatedBy(inputvo.getUserId());
							alertAssignedOfficerAction.setInsertedTime(new DateUtilService().getCurrentDateAndTime());
							alertAssignedOfficerAction.setUpdatedTime(new DateUtilService().getCurrentDateAndTime());
							alertAssignedOfficerAction.setIsDeleted("N");
							alertAssignedOfficerAction = alertAssignedOfficerActionDAO.save(alertAssignedOfficerAction);
						}
					}else{
						AlertAssignedOfficerAction alertAssignedOfficerAction = new AlertAssignedOfficerAction();
						alertAssignedOfficerAction.setAlertId(inputvo.getAlertId());
						alertAssignedOfficerAction.setAlertAssignedOfficerId(alertAssignedOfficer.getAlertAssignedOfficerId());
						alertAssignedOfficerAction.setGovtOfficerId(inputvo.getGovtOfficerId());
						alertAssignedOfficerAction.setAlertStatusId(2l);
						alertAssignedOfficerAction.setAlertDepartmentCommentId(alertDepartmentComment.getAlertDepartmentCommentId());
							
						//alertAssignedOfficerAction.setAlertDepartmentDocumentId(documentIds.get(i));
						alertAssignedOfficerAction.setInsertedBy(inputvo.getUserId());
						alertAssignedOfficerAction.setUpdatedBy(inputvo.getUserId());
						alertAssignedOfficerAction.setInsertedTime(new DateUtilService().getCurrentDateAndTime());
						alertAssignedOfficerAction.setUpdatedTime(new DateUtilService().getCurrentDateAndTime());
						alertAssignedOfficerAction.setIsDeleted("N");
						alertAssignedOfficerAction = alertAssignedOfficerActionDAO.save(alertAssignedOfficerAction);
					}
					/*AlertAssignedOfficerAction alertAssignedOfficerAction = new AlertAssignedOfficerAction();
					alertAssignedOfficerAction.setAlertId(inputvo.getAlertId());
					alertAssignedOfficerAction.setAlertAssignedOfficerId(alertAssignedOfficer.getAlertAssignedOfficerId());
					alertAssignedOfficerAction.setGovtOfficerId(inputvo.getGovtOfficerId());
					alertAssignedOfficerAction.setAlertStatusId(2l);
					alertAssignedOfficerAction.setAlertDepartmentCommentId(alertDepartmentComment.getAlertDepartmentCommentId());
					alertAssignedOfficerAction.setAlertDepartmentDocumentId(alertDepartmentDocument.getAlertDepartmentDocumentId());
					alertAssignedOfficerAction.setInsertedBy(inputvo.getUserId());
					alertAssignedOfficerAction.setUpdatedBy(inputvo.getUserId());
					alertAssignedOfficerAction.setInsertedTime(new DateUtilService().getCurrentDateAndTime());
					alertAssignedOfficerAction.setUpdatedTime(new DateUtilService().getCurrentDateAndTime());
					alertAssignedOfficerAction.setIsDeleted("N");
					alertAssignedOfficerAction = alertAssignedOfficerActionDAO.save(alertAssignedOfficerAction);*/
					
					return "success";
				}
			});
		} catch (Exception e) {
			logger.error("Error occured assigningAlertToOfficer() method of CccDashboardService",e);
		}
		return status;
	}
	
	public List<GovtDepartmentVO> getAssignedOfficersDetailsForAlert(Long alertId){
		List<GovtDepartmentVO> returnList = new ArrayList<GovtDepartmentVO>();
		try {
			List<Object[]> list = alertAssignedOfficerDAO.getAssignedOfficersForAlert(alertId);
			if(list != null && !list.isEmpty()){
				for (Object[] obj : list) {
					GovtDepartmentVO vo = new GovtDepartmentVO();
					
					vo.setId(Long.valueOf(obj[0] != null ? obj[0].toString():"0"));
					vo.setName(obj[1] != null ? obj[1].toString():"");
					vo.setDepartment(obj[2] != null ? obj[2].toString():"");
					vo.setMobileNo(obj[3] != null ? obj[3].toString():"");
					vo.setDesignation(obj[4] != null ? obj[4].toString():"");
					
					returnList.add(vo);
				}
			}
		} catch (Exception e) {
			logger.error("Error occured getAssignedOfficersDetailsForAlert() method of CccDashboardService",e);
		}
		return returnList;
	}
	
	public List<GovtDepartmentVO> getStatusWiseCommentsTracking(Long alertId){
		List<GovtDepartmentVO> returnList = null;
		try {
			Map<Long,GovtDepartmentVO> statusMap = new LinkedHashMap<Long, GovtDepartmentVO>();
			
			/*List<Object[]> statusList = alertDepartmentStatusDAO.getAllStatus();
			if(statusList != null && !statusList.isEmpty()){
				for (Object[] obj : statusList) {
					GovtDepartmentVO vo = new GovtDepartmentVO();
					
					Long statusId = Long.valueOf(obj[0] != null ? obj[0].toString():"0");
					vo.setStatusId(statusId);
					vo.setStatus(obj[1] != null ? obj[1].toString():"");
					
					statusMap.put(statusId, vo);
				}
			}*/
			
			AlertStatus pendingSts = alertStatusDAO.get(1l);
			GovtDepartmentVO pendvo = new GovtDepartmentVO();
			pendvo.setId(pendingSts.getAlertStatusId());
			pendvo.setStatus(pendingSts.getAlertStatus());
			statusMap.put(pendingSts.getAlertStatusId(), pendvo);
			
			List<Object[]> list = alertAssignedOfficerTrackingDAO.getStatusWiseTrackingComments(alertId);
			if(list != null && !list.isEmpty()){
				for (Object[] obj : list) {
					Long statusId = Long.valueOf(obj[0] != null ? obj[0].toString():"0");
					GovtDepartmentVO vo = statusMap.get(statusId);
					if(vo == null){
						vo = new GovtDepartmentVO();
						vo.setStatusId(statusId);
						vo.setStatus(obj[1] != null ? obj[1].toString():"");
						
						GovtDepartmentVO datevo = new GovtDepartmentVO();
						datevo.setCommentId(Long.valueOf(obj[2] != null ? obj[2].toString():"0"));
						datevo.setComment(obj[3] != null ? obj[3].toString():"");
						datevo.setId(Long.valueOf(obj[4] != null ? obj[4].toString():"0"));
						datevo.setName(obj[5] != null ? obj[5].toString():"");
						datevo.setDateStr(obj[6] != null ? obj[6].toString():"");
						datevo.setSource(obj[7] != null ? obj[7].toString():"");
						vo.getGovtDeptList().add(datevo);
						
						statusMap.put(statusId, vo);
					}
					else{
						GovtDepartmentVO datevo = new GovtDepartmentVO();
						datevo.setCommentId(Long.valueOf(obj[2] != null ? obj[2].toString():"0"));
						datevo.setComment(obj[3] != null ? obj[3].toString():"");
						datevo.setId(Long.valueOf(obj[4] != null ? obj[4].toString():"0"));
						datevo.setName(obj[5] != null ? obj[5].toString():"");
						datevo.setDateStr(obj[6] != null ? obj[6].toString():"");
						datevo.setSource(obj[7] != null ? obj[7].toString():"");
						vo.getGovtDeptList().add(datevo);
					}
				}
			}
			
			if(statusMap != null)
				returnList = new ArrayList<GovtDepartmentVO>(statusMap.values());
			
		} catch (Exception e) {
			logger.error("Error occured getStatusWiseCommentsTracking() method of CccDashboardService",e);
		}
		return returnList;
	}
	/*
	 * Swadhin(non-Javadoc) both IAS and collector
	 */
	public List<AlertVO> getTotalAlertGroupByStatusForOneDept(String fromDateStr, String toDateStr, Long stateId, List<Long> printIdList, List<Long> electronicIdList, Long userId){
		logger.info("Entered in getTotalAlertGroupByStatusForOneDept() method of CccDashboardService{}");
		try{
			Date fromDate = null;
			Date toDate = null;
			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
			if(fromDateStr != null && fromDateStr.trim().length() > 0 && toDateStr != null && toDateStr.trim().length() > 0){
				fromDate = sdf.parse(fromDateStr);
				toDate = sdf.parse(toDateStr);
			}
			if(printIdList != null && printIdList.size() > 0){
				if(electronicIdList != null && electronicIdList.size() == 0){
					electronicIdList.add(0L);
				}
			}else if(electronicIdList != null && electronicIdList.size() > 0){
				if(printIdList != null && printIdList.size() == 0){
					printIdList.add(0L);
				}
			}
			List<AlertVO> finalAlertVOs = new ArrayList<AlertVO>();
			//get all the alert status and build the template
			List<Object[]> statusList = new ArrayList<Object[]>();
			Object[] obj = null;
			List<Long> deptList = new ArrayList<Long>();
			List<Object[]> dptIdList = govtAlertDepartmentLocationDAO.getDeptListForUser(1L);
			if(dptIdList != null && dptIdList.size() > 0){
				for(Object[] param : dptIdList){
					deptList.add(commonMethodsUtilService.getLongValueForObject(param[0]));
				}
			}
			//get alert status count and and create a map of alertStatusId and its count
			List<Object[]> alertCountList = alertAssignedOfficerDAO.getTotalAlertGroupByStatusForGovtOneDept(fromDate,toDate,stateId,printIdList,electronicIdList,deptList);
			if(alertCountList != null && alertCountList.size() > 0){
				for(Object[] param : alertCountList){
					obj = new Object[2];
					obj[0] = param[0];
					obj[1] = param[1];
					statusList.add(obj);
				}
			}
			buildStatusWiseAlertCount(statusList,alertCountList,finalAlertVOs);
			if(finalAlertVOs != null && finalAlertVOs.size() > 0){
				for(AlertVO param : finalAlertVOs){
					param.setDeptIdList(deptList);
					if(dptIdList.get(0) != null){
						param.setDistrictId(commonMethodsUtilService.getLongValueForObject(dptIdList.get(0)[3]));
						param.setLocationName(commonMethodsUtilService.getStringValueForObject(dptIdList.get(0)[4]));   
					}
				}
			}
			return finalAlertVOs; 
		}catch(Exception e){
			e.printStackTrace();
			logger.error("Error occured getTotalAlertGroupByStatusForOneDept() method of CccDashboardService{}");
		}
		return null;
	}
	/*
	 * Swadhin(non-Javadoc) Both IAS and collector
	 */
	//swa
	public List<AlertVO> getTotalAlertGroutByDeptThenStatus(String fromDateStr, String toDateStr, Long stateId, List<Long> printIdList, List<Long> electronicIdList, Long userId){
		logger.info("Entered in getTotalAlertGroutByDeptThenStatus() method of CccDashboardService{}");
		try{
			Date fromDate = null;
			Date toDate = null;
			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
			if(fromDateStr != null && fromDateStr.trim().length() > 0 && toDateStr != null && toDateStr.trim().length() > 0){
				fromDate = sdf.parse(fromDateStr);
				toDate = sdf.parse(toDateStr);
			}
			
			Map<Long,Long> deptIdAndCountMap = new HashMap<Long,Long>();
			List<AlertVO> finalListNew = new ArrayList<AlertVO>();
			//get all the alert category for  building the template
			//List<Object[]> deptList = govtDepartmentDAO.getAllDepartment();//old
			
			//get all the status
			List<Object[]> statusList = new ArrayList<Object[]>();
			Object[] obj = null;
			List<Long> deptList = new ArrayList<Long>();
			List<Object[]> dptIdList = govtAlertDepartmentLocationDAO.getDeptListForUser(userId);
			if(dptIdList != null && dptIdList.size() > 0){
				for(Object[] param : dptIdList){
					deptList.add(commonMethodsUtilService.getLongValueForObject(param[0]));
				}
			}//getStatusForDepartments
			List<Object[]> alertCountList = alertDepartmentStatusDAO.getStatusForDepartments(deptList);
			if(alertCountList != null && alertCountList.size() > 0){
				for(Object[] param : alertCountList){
					obj = new Object[2];
					obj[0] = param[0];
					obj[1] = param[1];
					statusList.add(obj);        
				}
			}  
			//get alert status count and and create a map of alertStatusId and its corresponding  alert count
			//List<Object[]> alertCountList = alertDAO.getTotalAlertGroupByStatusForGovt(fromDate,toDate,stateId,printIdList,electronicIdList,deptIdList);//old
			List<Long> lvlIdList = new ArrayList<Long>();  
			Long lvlValue = 0L;
			List<Object[]> lvlValueAndLvlIdList = govtAlertDepartmentLocationDAO.getUserAccessLevels(userId);
			if(lvlValueAndLvlIdList != null && lvlValueAndLvlIdList.size() > 0){
				for(Object[] param : lvlValueAndLvlIdList){
					lvlIdList.add(commonMethodsUtilService.getLongValueForObject(param[1]));
					lvlValue = commonMethodsUtilService.getLongValueForObject(param[0]);
				}
			}
			List<Object[]> alertCntList = alertAssignedOfficerDAO.getDepartmentWiseAlertCount(fromDate,toDate,stateId,printIdList,electronicIdList,deptList,lvlValue,lvlIdList,"dept");
			if(alertCntList != null && alertCntList.size() > 0){  
				for(Object[] param : alertCntList){  
					deptIdAndCountMap.put(commonMethodsUtilService.getLongValueForObject(param[0]), commonMethodsUtilService.getLongValueForObject(param[2]));
				}  
			}  
			//getTotalAlertGroupByDepartmentThenStatusForGovt
			//List<Object[]> alertCountGrpByDeptList = alertDAO.getTotalAlertGroupByStatusThenDepartmentForGovt(fromDate,toDate,stateId,printIdList,electronicIdList,deptIdList);//old
			List<Object[]> alertCountGrpByDeptList = alertAssignedOfficerDAO.getDepartmentWiseAlertCount(fromDate,toDate,stateId,printIdList,electronicIdList,deptList,lvlValue,lvlIdList,"status");
					
			
			buildAlertGroupByStatusThenDepartment(alertCountGrpByDeptList,deptIdAndCountMap,statusList,finalListNew,"false");  
			
			return finalListNew;     
		}catch(Exception e){
			e.printStackTrace();
			logger.error("Error occured getTotalAlertGroutByDeptThenStatus() method of CccDashboardService{}");
		}
		return null;
	}
	/*
	 * Swadhin(non-Javadoc)
	 */
	public List<AlertVO> getAlertCountLocationWiseThenStatusWise(String fromDateStr, String toDateStr, Long stateId, List<Long> printIdList, List<Long> electronicIdList, List<Long> govtDepartmentIds,Long lvlValue){
		try{
			Date fromDate = null;  
			Date toDate = null;
			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
			if(fromDateStr != null && fromDateStr.trim().length() > 0 && toDateStr != null && toDateStr.trim().length() > 0){
				fromDate = sdf.parse(fromDateStr);
				toDate = sdf.parse(toDateStr);
			}
			if(printIdList != null && printIdList.size() > 0){
				if(electronicIdList != null && electronicIdList.size() == 0){
					electronicIdList.add(0L);
				}
			}else if(electronicIdList != null && electronicIdList.size() > 0){
				if(printIdList != null && printIdList.size() == 0){
					printIdList.add(0L);
				}
			}
			List<AlertVO> finalListNew = new ArrayList<AlertVO>();
			List<Object[]> statusList = alertDepartmentStatusDAO.getStatusForDepartments(govtDepartmentIds);
			List<Object[]> alertCountList = alertAssignedOfficerDAO.getLocationWiseThenStatusWiseAlertCount(fromDate, toDate, stateId, printIdList, electronicIdList, govtDepartmentIds, lvlValue);
			Map<Long,Long> locIdAndAltCountMap = new HashMap<Long,Long>();
			Long count = 0L;
			if(alertCountList != null && alertCountList.size() > 0){
				for(Object[] param : alertCountList){
					count = locIdAndAltCountMap.get(commonMethodsUtilService.getLongValueForObject(param[0]));
					if(count == null){
						locIdAndAltCountMap.put(commonMethodsUtilService.getLongValueForObject(param[0]), 0L);
					}
					locIdAndAltCountMap.put(commonMethodsUtilService.getLongValueForObject(param[0]), locIdAndAltCountMap.get(commonMethodsUtilService.getLongValueForObject(param[0]))+commonMethodsUtilService.getLongValueForObject(param[4]));
					
				}
			}
			buildAlertGroupByStatusThenDepartment(alertCountList,locIdAndAltCountMap,statusList,finalListNew,"false");
			return finalListNew;       
		}catch(Exception e){
			e.printStackTrace();
			logger.error("Error occured getAlertCountLocationWiseThenStatusWise() method of CccDashboardService{}");
		}
		return null;
	}
	public void buildStatusWiseAlertCount(List<Object[]> statusList,List<Object[]> alertCountList,List<AlertVO> finalAlertVOs){
		AlertVO alertVO = null;
		List<AlertVO> alertVOs = new ArrayList<AlertVO>();
		Map<Long,Long> statusIdAndCountMap = new HashMap<Long,Long>();
		Map<Long,String> stsIdAndColorMap = new HashMap<Long,String>();
		
		List<Object[]> stsList = alertStatusDAO.getAllStatus();
		for(Object[] param : stsList){
			stsIdAndColorMap.put(commonMethodsUtilService.getLongValueForObject(param[0]), commonMethodsUtilService.getStringValueForObject(param[2]));
		}
		if(statusList != null && statusList.size() > 0){         
			for(Object[] param : statusList){
				alertVO = new AlertVO();
				alertVO.setColor(commonMethodsUtilService.getStringValueForObject(stsIdAndColorMap.get(commonMethodsUtilService.getLongValueForObject(param[0]))));
				alertVO.setStatusId(commonMethodsUtilService.getLongValueForObject(param[0]));
				alertVO.setStatus(commonMethodsUtilService.getStringValueForObject(param[1]));
				alertVOs.add(alertVO);
			}
		}
		
		Long totalCount = 0L;  
		if(alertCountList != null && alertCountList.size() > 0){
			for(Object[] param : alertCountList){
				statusIdAndCountMap.put(commonMethodsUtilService.getLongValueForObject(param[0]), commonMethodsUtilService.getLongValueForObject(param[2]));
				totalCount+=commonMethodsUtilService.getLongValueForObject(param[2]);
			}
		}
		//push the status count into list if count is 0 push 0 also
		if(alertVOs != null && alertVOs.size() > 0){
			for(AlertVO vo : alertVOs){
				if(statusIdAndCountMap.get(vo.getStatusId()) != null){
					vo.setCount(statusIdAndCountMap.get(vo.getStatusId()));
					vo.setStatusPercent(calculatePercantage(statusIdAndCountMap.get(vo.getStatusId()),totalCount));
				}else{
					vo.setCount(0l);  
				}
			}
		}
		if(alertVOs != null && alertVOs.size() > 0){
			for(AlertVO param : alertVOs){
				if(param.getCount().longValue() > 0L){
					finalAlertVOs.add(param);
				}
			}
		}
	}
	public void buildAlertGroupByStatusThenDepartment(List<Object[]> alertCountGrpByDeptList, Map<Long,Long> statusIdAndCountMap, List<Object[]>deptList, List<AlertVO> finalListNew,String filter){
		AlertVO alertVO = null;
		List<AlertVO> alertVOs = null;
		//get all the alert count group by status then category.
		Map<Long,String> statusIdAndNameMap = new HashMap<Long,String>();
		Map<Long,Long> deptIdAndCountMap = null;//new HashMap<Long, Long>();
		Map<Long,Map<Long,Long>> statusIdAndCategoryIdAndCountMap = new HashMap<Long,Map<Long,Long>>();
		Map<Long,String> stsIdAndColorMap = new HashMap<Long,String>();
		List<Object[]> stsList = alertStatusDAO.getAllStatus();
		for(Object[] param : stsList){
			stsIdAndColorMap.put(commonMethodsUtilService.getLongValueForObject(param[0]), commonMethodsUtilService.getStringValueForObject(param[2]));
		}
		if(alertCountGrpByDeptList != null && alertCountGrpByDeptList.size() > 0){
			for(Object[] param : alertCountGrpByDeptList){
				deptIdAndCountMap = statusIdAndCategoryIdAndCountMap.get(commonMethodsUtilService.getLongValueForObject(param[0]));
				if(deptIdAndCountMap != null){
					deptIdAndCountMap.put(commonMethodsUtilService.getLongValueForObject(param[2]), commonMethodsUtilService.getLongValueForObject(param[4]));
				}else{
					deptIdAndCountMap = new HashMap<Long, Long>();
					deptIdAndCountMap.put(commonMethodsUtilService.getLongValueForObject(param[2]), commonMethodsUtilService.getLongValueForObject(param[4]));
					statusIdAndCategoryIdAndCountMap.put(commonMethodsUtilService.getLongValueForObject(param[0]),deptIdAndCountMap);
				}
				statusIdAndNameMap.put(commonMethodsUtilService.getLongValueForObject(param[0]), commonMethodsUtilService.getStringValueForObject(param[1]));
			}
		}
		//build final vo to sent to ui
		List<AlertVO> finalList = new ArrayList<AlertVO>();
		
		AlertVO innerListAlertVO = null;
		if(statusIdAndCategoryIdAndCountMap.size() > 0){
			for(Entry<Long,Map<Long,Long>> entry : statusIdAndCategoryIdAndCountMap.entrySet()){
				deptIdAndCountMap = entry.getValue();
				Long totalCount = statusIdAndCountMap.get(entry.getKey());
				if(deptIdAndCountMap.size() > 0){
					if(deptList != null && deptList.size() > 0){
						alertVOs = new ArrayList<AlertVO>();
						innerListAlertVO = new AlertVO();
						for(Object[] param : deptList){
							alertVO = new AlertVO();
							alertVO.setColor(commonMethodsUtilService.getStringValueForObject(stsIdAndColorMap.get(commonMethodsUtilService.getLongValueForObject(param[0]))));
							alertVO.setCategoryId(commonMethodsUtilService.getLongValueForObject(param[0]));
							alertVO.setCategory(commonMethodsUtilService.getStringValueForObject(param[1]));
							alertVOs.add(alertVO);  
						}
					}
					for(AlertVO param : alertVOs){
						if(deptIdAndCountMap.get(param.getCategoryId()) != null){
							param.setCategoryCount(deptIdAndCountMap.get(param.getCategoryId()));
							if(totalCount != null){
								param.setStatusPercent(calculatePercantage(deptIdAndCountMap.get(param.getCategoryId()),totalCount));
							}
						}else{
							param.setCategoryCount(0l);      
						}
					}
					innerListAlertVO.setSubList1(alertVOs);  
					if(statusIdAndNameMap.get(entry.getKey()) != null){
						innerListAlertVO.setStatusId(entry.getKey());
						innerListAlertVO.setColor(commonMethodsUtilService.getStringValueForObject(stsIdAndColorMap.get(commonMethodsUtilService.getLongValueForObject(entry.getKey()))));
						innerListAlertVO.setStatus(statusIdAndNameMap.get(entry.getKey()));
						
					}
					if(statusIdAndCountMap.get(entry.getKey()) != null){
						innerListAlertVO.setCount(statusIdAndCountMap.get(entry.getKey()));
					}
					finalList.add(innerListAlertVO);     
				}
			}
		}//finalListNew
		List<AlertVO> innerList = null;
		if(finalList != null && finalList.size() > 0){
			if(filter.equalsIgnoreCase("true")){
				for(AlertVO param : finalList){
					innerList = new ArrayList<AlertVO>();
					if(param.getCount().longValue() > 0L){
						finalListNew.add(param);
						for(AlertVO vo : param.getSubList1()){
							if(vo.getCategoryCount().longValue() > 0L){
								innerList.add(vo);
							}
						}
						param.setSubList1(innerList);
					}
				}
			}else{
				for(AlertVO param : finalList){
					if(param.getCount().longValue() > 0L){
						finalListNew.add(param);
					}
				}
				
			}
			
		}
	}
	/*
	 * Author: Teja
	 *  getDistrictWiseTotalAlerts Strip */
	public List<GovtDepartmentVO> getDistrictWiseTotalAlertsForAlert(String startDateStr,String endDateStr,Long stateId,List<Long> deptIdList,List<Long> paperIdList,List<Long> chanelIdList,Long userId){
		List<GovtDepartmentVO> finalVOList = new ArrayList<GovtDepartmentVO>();
		try {
			Date fromDate = null;
			Date toDate = null;
			List<Object[]> distWiseAlertLst = null;
			List<Object[]> statusWiseCntsLst = null;
			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
			if(startDateStr != null && startDateStr.trim().length() > 0 && endDateStr != null && endDateStr.trim().length() > 0){
				fromDate = sdf.parse(startDateStr);
				toDate = sdf.parse(endDateStr);
			}
			/*if(paperIdList != null && paperIdList.size() > 0){
		        if(chanelIdList != null && chanelIdList.size() == 0){
		        	chanelIdList.add(0L);
		        }
		      }else if(chanelIdList != null && chanelIdList.size() > 0){
		        if(paperIdList != null && paperIdList.size() == 0){
		        	paperIdList.add(0L);
		        }
		      }*/
			
			//Srav
			//List<Long> dptIdList = new ArrayList<Long>();
			Long levelId = 0l;
			List<Long> levelValues = new ArrayList<Long>();
			List<Object[]> accessList = govtAlertDepartmentLocationDAO.getUserAccessLevelsForUser(userId);
			if(accessList != null && !accessList.isEmpty()){
				for (Object[] obj : accessList) {
					//dptIdList.add(Long.valueOf(obj[0] != null ? obj[0].toString():"0"));
					levelId = Long.valueOf(obj[1] != null ? obj[1].toString():"0");
					levelValues.add(Long.valueOf(obj[2] != null ? obj[2].toString():"0"));
				}
			}
			
			//departmentId-0,departmentName-1,districtId-2,districtName-3,Count-4
			//distWiseAlertLst = alertDAO.getDistrictWiseTotalAlertsForAlert(fromDate, toDate, stateId, deptIdList, paperIdList, chanelIdList);
			distWiseAlertLst = alertAssignedOfficerDAO.getDepartmentAndDistrictWiseAlertsCounts(deptIdList, levelId, levelValues, stateId, fromDate, toDate, paperIdList, chanelIdList);
			List<Long> deptIds = new ArrayList<Long>(0);
			if(distWiseAlertLst != null && distWiseAlertLst.size() > 0){
				for (Object[] objects : distWiseAlertLst) {
					GovtDepartmentVO matchedVO = getmatchedDeptVo(finalVOList,(Long)objects[0]);
					if(matchedVO == null){
						matchedVO = new GovtDepartmentVO();
						matchedVO.setDepartmentId(Long.valueOf(objects[0] != null ? objects[0].toString():"0"));
						matchedVO.setDepartment(objects[1] != null ? objects[1].toString():"");
						deptIds.add((Long)objects[0]);
					GovtDepartmentVO districtVO = new GovtDepartmentVO();
						districtVO.setDepartmentId(Long.valueOf(objects[2] != null ? objects[2].toString():"0"));
						districtVO.setDepartment(objects[3] != null ? objects[3].toString():"");
							districtVO.setCount(Long.valueOf(objects[4] != null ? objects[4].toString():"0"));
							
						matchedVO.getGovtDepartmentVOList().add(districtVO);	
						finalVOList.add(matchedVO);
					}else{
						GovtDepartmentVO matchedDistVO = getmatchedDeptVo(matchedVO.getGovtDepartmentVOList(), (Long)objects[2]);
						if(matchedDistVO == null){
								matchedDistVO = new GovtDepartmentVO();
								matchedDistVO.setDepartmentId(Long.valueOf(objects[2] != null ? objects[2].toString():"0"));
								matchedDistVO.setDepartment(objects[3] != null ? objects[3].toString():"");
								matchedDistVO.setCount(matchedDistVO.getCount()+Long.valueOf(objects[4] != null ? objects[4].toString():"0"));
								
							matchedVO.getGovtDepartmentVOList().add(matchedDistVO);
						}else{
							matchedDistVO.setCount(matchedDistVO.getCount()+Long.valueOf(objects[4] != null ? objects[4].toString():"0"));
						}
					}
				}
			}
			//statusWiseMediaTypeTotalCounts
			//statusId-0,status-1,Count-2,alertCategoryId-3,deptId-4
			//statusWiseCntsLst = alertDAO.getStatusWiseTotalCountsForAlert(fromDate, toDate, stateId,deptIds, paperIdList, chanelIdList);
			statusWiseCntsLst = alertAssignedOfficerDAO.getStatusWiseTotalCountsForAlert(deptIdList, levelId, levelValues, stateId, fromDate, toDate, paperIdList, chanelIdList);
			 if(statusWiseCntsLst != null && statusWiseCntsLst.size() > 0){
				 setDataToVoList(statusWiseCntsLst,finalVOList);
			 }
 			 if(finalVOList != null && finalVOList.size() >0){
 				 for (GovtDepartmentVO mainVo : finalVOList) {
 					 if(mainVo.getGovtDeptList() != null && mainVo.getGovtDeptList().size() > 0){
 						 for (GovtDepartmentVO statusTotVo : mainVo.getGovtDeptList()){
		  					 	statusTotVo.setCount(statusTotVo.getPrintCnt()+statusTotVo.getElecCnt());//statusCount
		 				 }
 					 }
				}
 			 }
			
			
		} catch (Exception e) {
			logger.error("Error occured getDistrictWiseTotalAlertsForAlert() method of CccDashboardService",e);
		}
		return finalVOList;
	}

	public GovtDepartmentVO getmatchedDeptVo(List<GovtDepartmentVO> finalVOList,Long govtId){
		if(finalVOList != null && finalVOList.size() > 0){
			for (GovtDepartmentVO govtDeptVO : finalVOList) {
				if(govtDeptVO.getDepartmentId() != null && govtDeptVO.getDepartmentId().equals(govtId)){
					return govtDeptVO;
				}
			}
		}
		return null;
	}
	//statusId-0,status-1,Count-2,alertCategoryId-3,deptId-4
	public void setDataToVoList(List<Object[]> objList,List<GovtDepartmentVO> finalList){
		if(objList != null && objList.size() > 0){
			for (Object[] objects : objList) {
				GovtDepartmentVO matchedDeptVO = getmatchedDeptVo(finalList,commonMethodsUtilService.getLongValueForObject(objects[4]));
				if(matchedDeptVO == null){
					matchedDeptVO = new GovtDepartmentVO();
					matchedDeptVO.setDepartmentId(commonMethodsUtilService.getLongValueForObject(objects[4]));
					
					GovtDepartmentVO statusVO = new GovtDepartmentVO();
					statusVO.setDepartmentId(commonMethodsUtilService.getLongValueForObject(objects[0]));
					statusVO.setDepartment(commonMethodsUtilService.getStringValueForObject(objects[1]));
					
					if(commonMethodsUtilService.getLongValueForObject(objects[3]) == 2l){
						statusVO.setPrintCnt(commonMethodsUtilService.getLongValueForObject(objects[2]));//For Print
					}else if(commonMethodsUtilService.getLongValueForObject(objects[3]) == 3l){
						statusVO.setElecCnt(commonMethodsUtilService.getLongValueForObject(objects[2]));//For Electronic
					}
					matchedDeptVO.getGovtDeptList().add(statusVO);
					finalList.add(matchedDeptVO);
				}else{
					GovtDepartmentVO matchedStatusVO = getmatchedDeptVo(matchedDeptVO.getGovtDeptList(),commonMethodsUtilService.getLongValueForObject(objects[0]));
					if(matchedStatusVO == null){
						matchedStatusVO = new GovtDepartmentVO();
						matchedStatusVO.setDepartmentId(commonMethodsUtilService.getLongValueForObject(objects[0]));
						matchedStatusVO.setDepartment(commonMethodsUtilService.getStringValueForObject(objects[1]));
						
						if(commonMethodsUtilService.getLongValueForObject(objects[3]) == 2l){
							matchedStatusVO.setPrintCnt(commonMethodsUtilService.getLongValueForObject(objects[2]));//For Print
						}else if(commonMethodsUtilService.getLongValueForObject(objects[3]) == 3l){
							matchedStatusVO.setElecCnt(commonMethodsUtilService.getLongValueForObject(objects[2]));//For Electronic
						}
						matchedDeptVO.getGovtDeptList().add(matchedStatusVO);
					}else{
						if(commonMethodsUtilService.getLongValueForObject(objects[3]) == 2l){
							matchedStatusVO.setPrintCnt(matchedStatusVO.getPrintCnt()+commonMethodsUtilService.getLongValueForObject(objects[2]));//printCount
						}else if(commonMethodsUtilService.getLongValueForObject(objects[3]) == 3l){
							matchedStatusVO.setElecCnt(matchedStatusVO.getElecCnt()+commonMethodsUtilService.getLongValueForObject(objects[2]));//ElectCount
						}
					}
				}
			}
		}
	}
	public List<GovtDepartmentVO> getStatusWiseDistrictTotalForAlert(String startDateStr,String endDateStr,Long stateId,
			 List<Long> deptIdList,List<Long> paperIdList,List<Long> chanelIdList,Long userId){
		List<GovtDepartmentVO> finalVOList = new ArrayList<GovtDepartmentVO>();
		try {
			Date fromDate = null;
			Date toDate = null;
			List<Object[]> statusWiseDistLst = null;
			List<Object[]> statusWiseCntsLst = null;
			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
			if(startDateStr != null && startDateStr.trim().length() > 0 && endDateStr != null && endDateStr.trim().length() > 0){
				fromDate = sdf.parse(startDateStr);
				toDate = sdf.parse(endDateStr);
			}
			if(paperIdList != null && paperIdList.size() > 0){
		        if(chanelIdList != null && chanelIdList.size() == 0){
		        	chanelIdList.add(0L);
		        }
		      }else if(chanelIdList != null && chanelIdList.size() > 0){
		        if(paperIdList != null && paperIdList.size() == 0){
		        	paperIdList.add(0L);
		        }
		      }
			
			//List<Long> dptIdList = new ArrayList<Long>();
			Long levelId = 0l;
			List<Long> levelValues = new ArrayList<Long>();
			List<Object[]> accessList = govtAlertDepartmentLocationDAO.getUserAccessLevelsForUser(userId);
			if(accessList != null && !accessList.isEmpty()){
				for (Object[] obj : accessList) {
					//dptIdList.add(Long.valueOf(obj[0] != null ? obj[0].toString():"0"));
					levelId = Long.valueOf(obj[1] != null ? obj[1].toString():"0");
					levelValues.add(Long.valueOf(obj[2] != null ? obj[2].toString():"0"));
				}
			}
			
			//districtId-0,districtName-1,statusId-2,statusName-3,count-4
			//statusWiseDistLst = alertDAO.getDistWiseTotalAlertsStatusForAlert(fromDate, toDate, stateId, deptIdList, paperIdList, chanelIdList);
			statusWiseDistLst = alertAssignedOfficerDAO.getDistWiseTotalAlertsStatusForAlert(deptIdList, levelId, levelValues, stateId, fromDate, toDate, paperIdList, chanelIdList);
			if(statusWiseDistLst != null && statusWiseDistLst.size() > 0){
				for (Object[] objects : statusWiseDistLst) {
					GovtDepartmentVO matchedDistVO = getmatchedDeptVo(finalVOList,(Long)objects[0]);
					if(matchedDistVO == null){
						matchedDistVO = new GovtDepartmentVO();
						matchedDistVO.setDepartmentId(Long.valueOf(objects[0] != null ? objects[0].toString():"0"));
						matchedDistVO.setDepartment(objects[1] != null ? objects[1].toString():"");
					
						GovtDepartmentVO statusVO = new GovtDepartmentVO();
						statusVO.setDepartmentId(Long.valueOf(objects[2] != null ? objects[2].toString():"0"));
						statusVO.setDepartment(objects[3] != null ? objects[3].toString():"");
						statusVO.setCount(Long.valueOf(objects[4] != null ? objects[4].toString():"0"));
							
							matchedDistVO.getGovtDepartmentVOList().add(statusVO);	
						finalVOList.add(matchedDistVO);
					}else{
						GovtDepartmentVO matchedStatusVO = getmatchedDeptVo(matchedDistVO.getGovtDepartmentVOList(), (Long)objects[2]);
						if(matchedStatusVO == null){
							matchedStatusVO = new GovtDepartmentVO();
							matchedStatusVO.setDepartmentId(Long.valueOf(objects[2] != null ? objects[2].toString():"0"));
							matchedStatusVO.setDepartment(objects[3] != null ? objects[3].toString():"");
							matchedStatusVO.setCount(matchedDistVO.getCount()+Long.valueOf(objects[4] != null ? objects[4].toString():"0"));
								
							matchedDistVO.getGovtDepartmentVOList().add(matchedStatusVO);
						}else{
							matchedStatusVO.setCount(matchedDistVO.getCount()+Long.valueOf(objects[4] != null ? objects[4].toString():"0"));
						}
					}
				}
			}
			 if(finalVOList != null && finalVOList.size() >0){
				 for (GovtDepartmentVO govtDeptVo : finalVOList) {
					 if(govtDeptVo.getGovtDepartmentVOList() != null && govtDeptVo.getGovtDepartmentVOList().size() > 0){
						 for (GovtDepartmentVO vo2 : govtDeptVo.getGovtDepartmentVOList()) {
							 govtDeptVo.setTotalCount(govtDeptVo.getTotalCount()+vo2.getCount());
							 if(govtDeptVo.getTotalCount()>0l){
								 vo2.setPercentage(caclPercantage(vo2.getCount(),govtDeptVo.getTotalCount())); 
							 }
						}
					 }
				}
			 }
		} catch (Exception e) {
			logger.error("Error occured getStatusWiseDistrictTotalForAlert() method of CccDashboardService",e);
		}
		return finalVOList;
	}
	public Double caclPercantage(Long subCount,Long totalCount){
		Double d = new BigDecimal(subCount * 100.0/totalCount).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
		return d;
	}
	
	public List<GovtDepartmentVO> getInvolvedMembersInAlert(Long alertId){
		List<GovtDepartmentVO> returnList = new ArrayList<GovtDepartmentVO>();
		try {
			List<Object[]> list = alertCandidateDAO.getInvolvedMembersInAlert(alertId);
			if(list != null && !list.isEmpty()){
				for (Object[] obj : list) {
					GovtDepartmentVO vo = new GovtDepartmentVO();
					
					vo.setStatusId(Long.valueOf(obj[0] != null ? obj[0].toString():"0"));	//newsOrganizationId
					vo.setStatus(obj[1] != null ? obj[1].toString():"");					//Organization
					vo.setId(Long.valueOf(obj[2] != null ? obj[2].toString():"0"));			//tdpCadreId
					vo.setName(obj[3] != null ? obj[3].toString():"");						//firstName
					vo.setDateStr(obj[4] != null ? obj[4].toString():"");					//membershipNo
					vo.setSource(obj[5] != null ? obj[5].toString():"");					//mobileNo
					vo.setImage(obj[6] != null ? obj[6].toString():"");						//image
					if(vo.getImage() != null && vo.getImage().trim().length() > 0)
						vo.setImage("https://mytdp.com/images/"+IConstants.CADRE_IMAGES+"/"+vo.getImage());
					vo.setImpactLevelId(Long.valueOf(obj[7] != null ? obj[7].toString():"0"));
					vo.setImpactLevel(obj[8] != null ? obj[8].toString():"");
					
					returnList.add(vo);
				}
			}
		} catch (Exception e) {
			logger.error("Error occured getInvolvedMembersInAlert() method of CccDashboardService",e);
		}
		return returnList;
	}
	
	public List<AlertVO> getStatusWiseAlertDetails(String fromDateStr, String toDateStr, Long stateId, List<Long> printIdList, List<Long> electronicIdList, Long userId, Long statusId){
		logger.info("Entered in getTotalAlertGroupByStatusForOneDept() method of CccDashboardService{}");
		List<AlertVO> returnList = new ArrayList<AlertVO>();
		try {
			Date fromDate = null;
			Date toDate = null;
			SimpleDateFormat myFormat = new SimpleDateFormat("yyyy-MM-dd");
			Date todayDate = new DateUtilService().getCurrentDateAndTime();
			String todayDateStr = myFormat.format(todayDate);
			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
			
			if(fromDateStr != null && fromDateStr.trim().length() > 0 && toDateStr != null && toDateStr.trim().length() > 0){
				fromDate = sdf.parse(fromDateStr);
				toDate = sdf.parse(toDateStr);
			}
			/*if(printIdList != null && printIdList.size() > 0){
				if(electronicIdList != null && electronicIdList.size() == 0){
					electronicIdList.add(0L);
				}
			}else if(electronicIdList != null && electronicIdList.size() > 0){
				if(printIdList != null && printIdList.size() == 0){
					printIdList.add(0L);
				}
			}*/
			
			List<Long> dptIdList = new ArrayList<Long>();
			Long levelId = 0l;
			List<Long> levelValues = new ArrayList<Long>();
			
			List<Object[]> dptsList = govtAlertDepartmentLocationDAO.getUserAccessLevelsForUser(userId);
			if(dptsList != null && !dptsList.isEmpty()){
				for (Object[] obj : dptsList) {
					dptIdList.add(Long.valueOf(obj[0] != null ? obj[0].toString():"0"));
					levelId = Long.valueOf(obj[1] != null ? obj[1].toString():"0");
					levelValues.add(Long.valueOf(obj[2] != null ? obj[2].toString():"0"));
				}
			}
			
			Long designationOffId = 0l;
			Long govtOffId = 0l;
			List<Object[]> myList = govtDepartmentDesignationOfficerDetailsDAO.getDeptDesigOfficerIdAndGovtOfficerIdForUserId(userId, dptIdList, levelId, levelValues);
			if(myList != null && !myList.isEmpty()){
				for (Object[] obj : myList) {
					designationOffId = Long.valueOf(obj[0] != null ? obj[0].toString():"0");
					govtOffId = Long.valueOf(obj[1] != null ? obj[1].toString():"0");
				}
			}
			
			List<Object[]> list = alertAssignedOfficerDAO.getStatusWiseAlertDetails(fromDate, toDate, stateId, printIdList, electronicIdList, dptIdList,levelId,levelValues,statusId,designationOffId,govtOffId);
			if(list != null && !list.isEmpty()){
				for (Object[] obj : list) {
					AlertVO vo = new AlertVO();
					
					vo.setAlertId(Long.valueOf(obj[0] != null ? obj[0].toString():"0"));
					vo.setSeverity(Long.valueOf(obj[1] != null ? obj[1].toString():"0"));
					vo.setSeverityStr(obj[2] != null ? obj[2].toString():"");
					vo.setTitle(obj[3] != null ? obj[3].toString():"");
					vo.setDate1(obj[4] != null ? obj[4].toString():"");
					vo.setAssignedDate(obj[5] != null ? obj[5].toString():"");
					vo.setDate2(obj[6] != null ? obj[6].toString():"");
					vo.setStatusId(Long.valueOf(obj[7] != null ? obj[7].toString():"0"));
					vo.setStatus(obj[8] != null ? obj[8].toString():"");
					vo.setColor(obj[9] != null ? obj[9].toString():"");
					if(vo.getStatusId() != null && (vo.getStatusId() == 4l || vo.getStatusId() == 5l || vo.getStatusId() == 6l || vo.getStatusId() == 7l))
						vo.setNoOfDays(new DateUtilService().noOfDayBetweenDates(vo.getDate1(), vo.getDate2()));
					else
						vo.setNoOfDays(new DateUtilService().noOfDayBetweenDates(vo.getDate1(), todayDateStr));
					
					returnList.add(vo);
				}
			}
			
		} catch (Exception e) {
			logger.error("Error occured getInvolvedMembersInAlert() method of CccDashboardService",e);
		}
		return returnList;
	}
	
	public List<GovtDepartmentVO> getAlertStatusForUser(Long userId){
		List<GovtDepartmentVO> returnList = new ArrayList<GovtDepartmentVO>();
		try {
			List<Long> dptIdList = new ArrayList<Long>();
			List<Object[]> dptsList = govtAlertDepartmentLocationDAO.getDeptListForUser(userId);
			if(dptsList != null && !dptsList.isEmpty()){
				for (Object[] obj : dptsList) {
					dptIdList.add(Long.valueOf(obj[0] != null ? obj[0].toString():"0"));
				}
			}
			List<Object[]> list = alertDepartmentStatusDAO.getStatusForDepartments(dptIdList);
			if(list != null && !list.isEmpty()){
				for (Object[] obj : list) {
					GovtDepartmentVO vo = new GovtDepartmentVO();
					
					vo.setId(Long.valueOf(obj[0] != null ? obj[0].toString():"0"));
					vo.setName(obj[1] != null ? obj[1].toString():"");
					returnList.add(vo);
				}
			}
		} catch (Exception e) {
			logger.error("Error occured getAlertStatusForUser() method of CccDashboardService",e);
		}
		return returnList;
	}
	
	public List<GovtDepartmentVO> getAssignedDepartmentsForUser(Long userId){
		List<GovtDepartmentVO> returnList = new ArrayList<GovtDepartmentVO>();
		try {
			List<Object[]> list = govtAlertDepartmentLocationDAO.getDepartmentsForUser(userId);
			if(list != null && !list.isEmpty()){
				for (Object[] obj : list) {
					GovtDepartmentVO vo = new GovtDepartmentVO();
					vo.setId(Long.valueOf(obj[0] != null ? obj[0].toString():"0"));
					vo.setName(obj[1] != null ? obj[1].toString():"");
					returnList.add(vo);
				}
			}
		} catch (Exception e) {
			logger.error("Error occured getAssignedDepartmentsForUser() method of CccDashboardService",e);
		}
		return returnList;
	}
	public List<IdAndNameVO> getGovtDeptLevelForDeptAndUser(Long departmentId,Long userId){
		try{
			List<IdAndNameVO> returnList = new ArrayList<IdAndNameVO>();
			IdAndNameVO idAndNameVO= null;
			List<Object[]> deptList = govtAlertDepartmentLocationDAO.getGovtDeptLevelForDeptAndUser(departmentId, userId);
			if(deptList != null && deptList.size() > 0){
				for(Object[] param : deptList){
					idAndNameVO = new IdAndNameVO();
					idAndNameVO.setId(commonMethodsUtilService.getLongValueForObject(param[0]));
					idAndNameVO.setName(commonMethodsUtilService.getStringValueForObject(param[1]));
					returnList.add(idAndNameVO);
				}
			}
			return returnList;    
		}catch(Exception e){
			logger.error("Error occured getGovtDeptLevelForDeptAndUser() method of CccDashboardService",e);
		}
		return null;
	}
	public List<IdAndNameVO> getDeptIdAndNameListForUser(Long userId){
		try{
			List<IdAndNameVO> returnList = new ArrayList<IdAndNameVO>();
			IdAndNameVO idAndNameVO= null;
			List<Object[]> deptList = govtAlertDepartmentLocationDAO.getDeptIdAndNameListForUser(1L);
			if(deptList != null && deptList.size() > 0){
				for(Object[] param : deptList){
					idAndNameVO = new IdAndNameVO();
					idAndNameVO.setId(commonMethodsUtilService.getLongValueForObject(param[0]));
					idAndNameVO.setName(commonMethodsUtilService.getStringValueForObject(param[1]));
					returnList.add(idAndNameVO);
				}
			}
			return returnList;   
		}catch(Exception e){
			logger.error("Error occured getDeptListForUser() method of CccDashboardService",e);
		}
		return null;
	}
public List<GovtDepartmentVO> getLevelsByDeptId(Long departmentId){
		List<GovtDepartmentVO> returnList = new ArrayList<GovtDepartmentVO>();
		try {
			List<Object[]> list = govtDepartmentLevelDetailsDAO.getLocationNamesByDepmntId(departmentId);
			if(list != null && !list.isEmpty()){
				for (Object[] obj : list) {
					GovtDepartmentVO vo = new GovtDepartmentVO();
					vo.setId(Long.valueOf(obj[0] != null ? obj[0].toString():"0"));
					vo.setName(obj[1] != null ? obj[1].toString():"");
					returnList.add(vo);
				}
			}
		} catch (Exception e) {
			logger.error("Error occured getLevelsByDeptId() method of CccDashboardService",e);
		}
		return returnList;
	}	

	public List<GovtDepartmentVO> getAssignedDesignationsForUser(Long userId){
		List<GovtDepartmentVO> returnList = new ArrayList<GovtDepartmentVO>();
		try {
			List<Object[]> list = govtDepartmentDesignationOfficerDAO.getDeptDesignationsForUser(userId);
			if(list != null && !list.isEmpty()){
				for (Object[] obj : list) {
					GovtDepartmentVO vo = new GovtDepartmentVO();
					vo.setId(Long.valueOf(obj[0] != null ? obj[0].toString():"0"));
					vo.setDesignation(obj[1] != null ? obj[1].toString():"");
					vo.setDepartmentId(Long.valueOf(obj[2] != null ? obj[2].toString():"0"));
					vo.setDepartment(obj[3] != null ? obj[3].toString():"");
					
					returnList.add(vo);
				}
			}
		} catch (Exception e) {
			logger.error("Error occured getAssignedDesignationsForUser() method of CccDashboardService",e);
		}
		return returnList;
	}
	
	public GovtDepartmentVO getAssignedLevelsForUser(Long userId,Long designationId){
		GovtDepartmentVO returnvo = new GovtDepartmentVO();
		try {
			Long levelId = 0l;
			List<Long> levelValues = new ArrayList<Long>();
			
			List<Object[]> list = govtDepartmentDesignationOfficerDAO.getLevelsForUser(userId, designationId);
			if(list != null&& !list.isEmpty()){
				for (Object[] obj : list) {
					Long id = Long.valueOf(obj[0] != null ? obj[0].toString():"0");
					if(levelId.longValue() != id.longValue()){
						GovtDepartmentVO vo = new GovtDepartmentVO();
						vo.setId(id);
						vo.setName(obj[1] != null ? obj[1].toString():"");
						returnvo.getGovtDeptList().add(vo);
						
						levelId = id;
					}
					levelValues.add(Long.valueOf(obj[2] != null ? obj[2].toString():"0"));
				}
			}
			
			if(levelId > 0l && levelValues != null && !levelValues.isEmpty()){
				List<Object[]> locationList = new ArrayList<Object[]>();
				if(levelId == 3l)							//District
					 locationList = districtDAO.getDistrictDetailsByDistrictIds(levelValues);
				else if(levelId == 4l || levelId == 8l)		//Constituency || Ward
					locationList = constituencyDAO.getConstituenciesNamesByIds(levelValues);
				else if(levelId == 5l)						//Mandal
					locationList = tehsilDAO.getTehsilNameByTehsilIdsList(levelValues);
				else if(levelId == 6l)						//Village
					locationList = panchayatDAO.getPanchayatNamesByIds(levelValues);
				else if(levelId == 7l)						//LocalElectionBody
					locationList = localElectionBodyDAO.getLocalElectionBodyNames(levelValues);
				
				if(locationList != null && !locationList.isEmpty()){
					for (Object[] obj : locationList) {
						GovtDepartmentVO vo = new GovtDepartmentVO();
						vo.setId(Long.valueOf(obj[0] != null ? obj[0].toString():"0"));
						vo.setName(obj[1] != null ? obj[1].toString():"");
						returnvo.getGovtDepartmentVOList().add(vo);
					}
				}
			}
			
		} catch (Exception e) {
			logger.error("Error occured getAssignedLevelsForUser() method of CccDashboardService",e);
		}
		return returnvo;
	}
	
	public List<GovtDepartmentVO> getSubLevelsForUser(Long userId,Long designationId){
		List<GovtDepartmentVO> returnList = new ArrayList<GovtDepartmentVO>();
		try {
			Long levelId = 0l;
			List<Long> list = govtDepartmentDesignationOfficerDAO.getLevelIdForDesignation(userId, designationId);
			if(list != null && !list.isEmpty())
				levelId = list.get(0);
			
			List<Object[]> levelList = govtDepartmentLevelDAO.getLowerLevelsByLevel(levelId);
			if(levelList != null && !levelList.isEmpty()){
				for (Object[] obj : levelList) {
					GovtDepartmentVO vo = new GovtDepartmentVO();
					vo.setId(Long.valueOf(obj[0] != null ? obj[0].toString():"0"));
					vo.setName(obj[1] != null ? obj[1].toString():"");
					returnList.add(vo);
				}
			}
			
		} catch (Exception e) {
			logger.error("Error occured getSubLevelsForUser() method of CccDashboardService",e);
		}
		return returnList;
	}
	
	public List<GovtDepartmentVO> getSubOrdinatesAlertsOverView(Long designationId,Long levelId,String startDate,String endDate){
		List<GovtDepartmentVO> returnList = new ArrayList<GovtDepartmentVO>();
		try {
			Map<Long,GovtDepartmentVO> locationMap = new LinkedHashMap<Long, GovtDepartmentVO>();
			Date fromDate = null;
			Date toDate = null;
			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
			if(startDate != null && endDate != null){
				fromDate = sdf.parse(startDate);
				toDate = sdf.parse(endDate);
			}
			
			List<Object[]> statusList = alertDepartmentStatusDAO.getStatusWithoutPending();
			List<Object[]> list = alertAssignedOfficerDAO.getSubOrdinatesAlertDetails(designationId, levelId, fromDate, toDate);
			if(list != null && !list.isEmpty()){
				for (Object[] obj : list) {
					Long id = Long.valueOf(obj[0] != null ? obj[0].toString():"0");
					Long statusId = Long.valueOf(obj[2] != null ? obj[2].toString():"0");
					
					GovtDepartmentVO vo = locationMap.get(id);
					if(vo == null){
						vo = new GovtDepartmentVO();
						
						vo.setId(id);
						vo.setName(obj[1] != null ? obj[1].toString():"");
						vo.setGovtDeptList(setStatusList(statusList));
						GovtDepartmentVO stsvo = getMatchedVo(vo.getGovtDeptList(), statusId);
						if(stsvo != null){
							stsvo.setCount(Long.valueOf(obj[4] != null ? obj[4].toString():"0"));
							vo.setCount(vo.getCount()+stsvo.getCount());
						}
						locationMap.put(id, vo);	
					}
					else{
						GovtDepartmentVO stsvo = getMatchedVo(vo.getGovtDeptList(), statusId);
						if(stsvo != null){
							stsvo.setCount(Long.valueOf(obj[4] != null ? obj[4].toString():"0"));
							vo.setCount(vo.getCount()+stsvo.getCount());
						}
					}
				}
			}
			
			if(locationMap != null)
				returnList = new ArrayList<GovtDepartmentVO>(locationMap.values());
			
		} catch (Exception e) {
			logger.error("Error occured getSubOrdinatesAlertsOverView() method of CccDashboardService",e);
		}
		return returnList;
	}
	
	public List<GovtDepartmentVO> setStatusList(List<Object[]> list){
		List<GovtDepartmentVO> returnList = new ArrayList<GovtDepartmentVO>();
		try {
			if(list != null && !list.isEmpty()){
				for (Object[] obj : list) {
					GovtDepartmentVO vo = new GovtDepartmentVO();
					vo.setId(Long.valueOf(obj[0] != null ? obj[0].toString():"0"));
					vo.setName(obj[1] != null ? obj[1].toString():"");
					vo.setColor(obj[2] != null ? obj[2].toString():"");
					returnList.add(vo);
				}
			}
		} catch (Exception e) {
			logger.error("Error occured setStatusList() method of CccDashboardService",e);
		}
		return returnList;
	}
	
	public GovtDepartmentVO getMatchedVo(List<GovtDepartmentVO> list,Long id){
		GovtDepartmentVO vo = null;
		try {
			if(list != null && !list.isEmpty()){
				for (GovtDepartmentVO stsvo : list) {
					if(stsvo.getId() != null && id != null && stsvo.getId().longValue() == id.longValue())
						return stsvo;
				}
			}
		} catch (Exception e) {
			logger.error("Error occured getMatchedVo() method of CccDashboardService",e);
		}
		return vo;
	}
	
	public String updatingAlertInformation(final AlertAssigningVO inputvo){
		String status = null;
		try {
			status = (String)transactionTemplate.execute(new TransactionCallback() {
				public Object doInTransaction(TransactionStatus arg0) {
					
					List<Long> documentIds = new ArrayList<Long>(0);
					
					//Comments Saving
					AlertDepartmentComment alertDepartmentComment = new AlertDepartmentComment();
					alertDepartmentComment.setComment(inputvo.getComment());
					alertDepartmentComment.setInsertedBy(inputvo.getUserId());
					alertDepartmentComment.setInsertedTime(new DateUtilService().getCurrentDateAndTime());
					alertDepartmentComment = alertDepartmentCommentDAO.save(alertDepartmentComment);
					
					//Documents Saving
					if(inputvo.getDocumentsList() != null && !inputvo.getDocumentsList().isEmpty()){
						for (String path : inputvo.getDocumentsList()) {
							AlertDepartmentDocument alertDepartmentDocument = new AlertDepartmentDocument();
							alertDepartmentDocument.setDocument(path);
							alertDepartmentDocument.setInsertedBy(inputvo.getUserId());
							alertDepartmentDocument.setInsertedTime(new DateUtilService().getCurrentDateAndTime());
							alertDepartmentDocument = alertDepartmentDocumentDAO.save(alertDepartmentDocument);
							documentIds.add(alertDepartmentDocument.getAlertDepartmentDocumentId());
						}
					}
					
					
					//Alert Status Updation
					Alert alert = alertDAO.get(inputvo.getAlertId());
					alert.setAlertStatusId(inputvo.getStatusId());
					alert.setUpdatedTime(new DateUtilService().getCurrentDateAndTime());
					alert.setUpdatedBy(inputvo.getUserId());
					alert = alertDAO.save(alert);
					
					Long assignedOfficerId = 0l;
					List<Long> alertAssignIds = alertAssignedOfficerDAO.getAlertAssignedOfficerIdsForAlertByUser(inputvo.getAlertId(), inputvo.getUserId());
					if(alertAssignIds != null && !alertAssignIds.isEmpty())
						assignedOfficerId = alertAssignIds.get(0);
					
					AlertAssignedOfficer alertAssignedOfficer = alertAssignedOfficerDAO.get(assignedOfficerId);
					
					//Officer Assigning Tracking
					AlertAssignedOfficerTracking alertAssignedOfficerTracking = new AlertAssignedOfficerTracking();
					alertAssignedOfficerTracking.setAlertAssignedOfficerId(alertAssignedOfficer.getAlertAssignedOfficerId());
					alertAssignedOfficerTracking.setAlertId(alertAssignedOfficer.getAlertId());
					alertAssignedOfficerTracking.setGovtDepartmentDesignationOfficerId(alertAssignedOfficer.getGovtDepartmentDesignationOfficerId());
					alertAssignedOfficerTracking.setGovtOfficerId(alertAssignedOfficer.getGovtOfficerId());
					alertAssignedOfficerTracking.setInsertedBy(alertAssignedOfficer.getInsertedBy());
					alertAssignedOfficerTracking.setUpdatedBy(alertAssignedOfficer.getUpdatedBy());
					alertAssignedOfficerTracking.setInsertedTime(alertAssignedOfficer.getInsertedTime());
					alertAssignedOfficerTracking.setUpdatedTime(alertAssignedOfficer.getUpdatedTime());
					alertAssignedOfficerTracking.setAlertStatusId(alertAssignedOfficer.getAlertStatusId());
					alertAssignedOfficerTracking = alertAssignedOfficerTrackingDAO.save(alertAssignedOfficerTracking);
					
					//Officer Assigning Updation
					alertAssignedOfficer.setUpdatedBy(inputvo.getUserId());
					alertAssignedOfficer.setUpdatedTime(new DateUtilService().getCurrentDateAndTime());
					alertAssignedOfficer.setAlertStatusId(inputvo.getStatusId());
					if(inputvo.getStatusId() != null && inputvo.getStatusId() == 8l)
						alertAssignedOfficer.setIsApproved("N");
					alertAssignedOfficer = alertAssignedOfficerDAO.save(alertAssignedOfficer);
					
					//Alert Assigned Officer Action Saving
					if(documentIds != null && !documentIds.isEmpty()){
						for (int i = 0; i < documentIds.size(); i++) {
							AlertAssignedOfficerAction alertAssignedOfficerAction = new AlertAssignedOfficerAction();
							alertAssignedOfficerAction.setAlertId(inputvo.getAlertId());
							alertAssignedOfficerAction.setAlertAssignedOfficerId(alertAssignedOfficer.getAlertAssignedOfficerId());
							alertAssignedOfficerAction.setGovtOfficerId(inputvo.getGovtOfficerId());
							alertAssignedOfficerAction.setAlertStatusId(inputvo.getStatusId());
							if(i == 0)
								alertAssignedOfficerAction.setAlertDepartmentCommentId(alertDepartmentComment.getAlertDepartmentCommentId());
								
							alertAssignedOfficerAction.setAlertDepartmentDocumentId(documentIds.get(i));
							alertAssignedOfficerAction.setInsertedBy(inputvo.getUserId());
							alertAssignedOfficerAction.setUpdatedBy(inputvo.getUserId());
							alertAssignedOfficerAction.setInsertedTime(new DateUtilService().getCurrentDateAndTime());
							alertAssignedOfficerAction.setUpdatedTime(new DateUtilService().getCurrentDateAndTime());
							alertAssignedOfficerAction.setIsDeleted("N");
							alertAssignedOfficerAction = alertAssignedOfficerActionDAO.save(alertAssignedOfficerAction);
						}
					}else{
						AlertAssignedOfficerAction alertAssignedOfficerAction = new AlertAssignedOfficerAction();
						alertAssignedOfficerAction.setAlertId(inputvo.getAlertId());
						alertAssignedOfficerAction.setAlertAssignedOfficerId(alertAssignedOfficer.getAlertAssignedOfficerId());
						alertAssignedOfficerAction.setGovtOfficerId(inputvo.getGovtOfficerId());
						alertAssignedOfficerAction.setAlertStatusId(inputvo.getStatusId());
						alertAssignedOfficerAction.setAlertDepartmentCommentId(alertDepartmentComment.getAlertDepartmentCommentId());
							
						//alertAssignedOfficerAction.setAlertDepartmentDocumentId(documentIds.get(i));
						alertAssignedOfficerAction.setInsertedBy(inputvo.getUserId());
						alertAssignedOfficerAction.setUpdatedBy(inputvo.getUserId());
						alertAssignedOfficerAction.setInsertedTime(new DateUtilService().getCurrentDateAndTime());
						alertAssignedOfficerAction.setUpdatedTime(new DateUtilService().getCurrentDateAndTime());
						alertAssignedOfficerAction.setIsDeleted("N");
						alertAssignedOfficerAction = alertAssignedOfficerActionDAO.save(alertAssignedOfficerAction);
					}
					
					return "success";
				}
			});
		} catch (Exception e) {
			logger.error("Error occured updatingAlertInformation() method of CccDashboardService",e);
		}
		return status;
	}
	/*
	 * Swadhin(non-Javadoc)
	 * @see com.itgrids.partyanalyst.service.ICccDashboardService#getTotalAlertByStatusForOfficer(String fromDateStr, String toDateStr,Long userId)
	 * IAS Officer
	 */
	public List<AlertVO> getTotalAlertByStatusForOfficer(String fromDateStr, String toDateStr,Long userId){
		logger.info("Entered in getTotalAlertByStatus() method of CccDashboardService{}");
		try{
			Date fromDate = null;
			Date toDate = null;
			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
			if(fromDateStr != null && fromDateStr.trim().length() > 0 && toDateStr != null && toDateStr.trim().length() > 0){
				fromDate = sdf.parse(fromDateStr);
				toDate = sdf.parse(toDateStr);
			}
			List<Long> dptIdList = new ArrayList<Long>();
			List<Object[]> dptsList = govtAlertDepartmentLocationDAO.getDeptListForUser(userId);
			if(dptsList != null && !dptsList.isEmpty()){
				for (Object[] obj : dptsList) {
					dptIdList.add(Long.valueOf(obj[0] != null ? obj[0].toString():"0"));
				}
			}
			List<Long> lvlIdList = new ArrayList<Long>();
			Long lvlValue = 0L;
			List<Object[]> lvlValueAndLvlIdList = govtAlertDepartmentLocationDAO.getUserAccessLevels(userId);
			if(lvlValueAndLvlIdList != null && lvlValueAndLvlIdList.size() > 0){
				for(Object[] param : lvlValueAndLvlIdList){
					lvlIdList.add(commonMethodsUtilService.getLongValueForObject(param[1]));
					lvlValue = commonMethodsUtilService.getLongValueForObject(param[0]);
				}
			}
			List<AlertVO> finalAlertVOs = new ArrayList<AlertVO>();
			List<Object[]> userInfoList = govtDepartmentDesignationOfficerDetailsDAO.getDeptDesigOfficerIdAndGovtOfficerIdForUserId(userId,dptIdList,lvlValue,lvlIdList);
			List<Object[]> alertList = alertAssignedOfficerDAO.getAlertIdAndDeptDesigOfficerIdAndGovtOfficerIdList(fromDate, toDate,dptIdList,lvlValue,lvlIdList,null);
			
			Set<Long> alertIdSet = new HashSet<Long>();
			Long deptDesigOfficerId = null;
			Long officerId = null;
			Long deptDesigOfficerId2 = null;
			Long officerId2 = null;
			if(userInfoList != null && userInfoList.size() > 0 && alertList != null && alertList.size() > 0){
				for(Object[] param : userInfoList){
					deptDesigOfficerId = commonMethodsUtilService.getLongValueForObject(param[0]);
					officerId = commonMethodsUtilService.getLongValueForObject(param[1]);
					if(deptDesigOfficerId.longValue() > 0L && officerId.longValue() > 0L){
						for(Object[] param2 : alertList){
							deptDesigOfficerId2 = commonMethodsUtilService.getLongValueForObject(param2[0]);
							officerId2 = commonMethodsUtilService.getLongValueForObject(param2[1]);
							if(deptDesigOfficerId2.longValue() > 0L && officerId2.longValue() > 0L){
								if(deptDesigOfficerId.equals(deptDesigOfficerId2) && officerId.equals(officerId2)){
									alertIdSet.add(commonMethodsUtilService.getLongValueForObject(param2[2]));
								}
							}
						}
					}
					
				}
			}
			Object[] obj = null;
			List<Object[]> statusList = new ArrayList<Object[]>();
			if(alertIdSet != null && alertIdSet.size() > 0){
				//get alert status count and and create a map of alertStatusId and its count
				List<Object[]> alertCountList = alertAssignedOfficerDAO.getTotalAlertGroupByStatusForAlertList(alertIdSet);
				
				if(alertCountList != null && alertCountList.size() > 0){
					for(Object[] param : alertCountList){
						obj = new Object[2];
						obj[0] = param[0];
						obj[1] = param[1];
						statusList.add(obj);
					}
				}
				buildStatusWiseAlertCount(statusList,alertCountList,finalAlertVOs);
			}
			if(finalAlertVOs != null && finalAlertVOs.size() > 0){
				for(AlertVO param : finalAlertVOs){
					if(dptsList.get(0)[3] != null && dptsList.get(0)[4] != null){
						param.setLocationId(commonMethodsUtilService.getLongValueForObject(dptsList.get(0)[3]));
						param.setLocationName(commonMethodsUtilService.getStringValueForObject(dptsList.get(0)[4]));
					}
				}
			}
			return finalAlertVOs;
		}catch(Exception e){
			e.printStackTrace();
			logger.error("Error occured getTotalAlertByStatus() method of CccDashboardService{}");
		}
		return null;
	}
	/*
	 * Swadhin(non-Javadoc)
	 * @see com.itgrids.partyanalyst.service.ICccDashboardService#getTotalAlertByDeptForOfficer(String fromDateStr, String toDateStr,Long userId)
	 * IAS Officer
	 */
	public List<AlertVO> getTotalAlertByDeptForOfficer(String fromDateStr, String toDateStr,Long userId){
		logger.info("Entered in getTotalAlertByStatus() method of CccDashboardService{}");
		try{
			DateUtilService dateUtilService = new DateUtilService();
			Date fromDate = dateUtilService.getCurrentDateAndTime();
			Date toDate = dateUtilService.getCurrentDateAndTime();
			
			List<AlertVO> finalAlertVOs = new ArrayList<AlertVO>();
			AlertVO alertVO = null;
			List<Long> dptIdList = new ArrayList<Long>();
			List<Object[]> dptsList = govtAlertDepartmentLocationDAO.getDeptListForUser(userId);
			if(dptsList != null && !dptsList.isEmpty()){
				for (Object[] obj : dptsList) {
					dptIdList.add(Long.valueOf(obj[0] != null ? obj[0].toString():"0"));
				}
			}
			List<Long> lvlIdList = new ArrayList<Long>();
			Long lvlValue = 0L;
			List<Object[]> lvlValueAndLvlIdList = govtAlertDepartmentLocationDAO.getUserAccessLevels(userId);
			if(lvlValueAndLvlIdList != null && lvlValueAndLvlIdList.size() > 0){  
				for(Object[] param : lvlValueAndLvlIdList){
					lvlIdList.add(commonMethodsUtilService.getLongValueForObject(param[1]));
					lvlValue = commonMethodsUtilService.getLongValueForObject(param[0]);
				}
			}
			List<Object[]> userInfoList = govtDepartmentDesignationOfficerDetailsDAO.getDeptDesigOfficerIdAndGovtOfficerIdForUserId(userId,dptIdList,lvlValue,lvlIdList);
			List<Object[]> alertList = alertAssignedOfficerDAO.getAlertIdAndDeptDesigOfficerIdAndGovtOfficerIdList(fromDate, toDate,dptIdList,lvlValue,lvlIdList,null);
			
			Set<Long> alertIdSet = new HashSet<Long>();    
			Long deptDesigOfficerId = null;
			Long officerId = null;
			Long deptDesigOfficerId2 = null;
			Long officerId2 = null;
			if(userInfoList != null && userInfoList.size() > 0 && alertList != null && alertList.size() > 0){
				for(Object[] param : userInfoList){
					deptDesigOfficerId = commonMethodsUtilService.getLongValueForObject(param[0]);
					officerId = commonMethodsUtilService.getLongValueForObject(param[1]);
					if(deptDesigOfficerId.longValue() > 0L && officerId.longValue() > 0L){
						for(Object[] param2 : alertList){
							deptDesigOfficerId2 = commonMethodsUtilService.getLongValueForObject(param2[0]);
							officerId2 = commonMethodsUtilService.getLongValueForObject(param2[1]);
							if(deptDesigOfficerId2.longValue() > 0L && officerId2.longValue() > 0L){
								if(deptDesigOfficerId.equals(deptDesigOfficerId2) && officerId.equals(officerId2)){
									alertIdSet.add(commonMethodsUtilService.getLongValueForObject(param2[2]));
								}  
							}
						}
					}
				}
			}
			if(alertIdSet != null && alertIdSet.size() > 0){
				List<Object[]> alertCountList = alertAssignedOfficerDAO.getTotalAlertGroupByDeptForAlertList(alertIdSet);
				if(alertCountList != null && alertCountList.size() > 0){
					for(Object[] param : alertCountList){
						alertVO = new AlertVO();
						alertVO.setId(commonMethodsUtilService.getLongValueForObject(param[0]));
						alertVO.setStatus(commonMethodsUtilService.getStringValueForObject(param[1]));
						alertVO.setCount(commonMethodsUtilService.getLongValueForObject(param[2]));
						finalAlertVOs.add(alertVO);
					}
				}
			}
			
			return finalAlertVOs;
		}catch(Exception e){
			e.printStackTrace();
			logger.error("Error occured getTotalAlertByStatus() method of CccDashboardService{}");
		}
		return null;
	}
	
	public List<AlertCoreDashBoardVO> getSubOrdinateLocationWiseAlertDetails(Long designationId,Long levelId,Long levelValue,String fromDateStr,String toDateStr){
		List<AlertCoreDashBoardVO> returnList = new ArrayList<AlertCoreDashBoardVO>();
		try {
			Date fromDate = null;
			Date toDate = null;
			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
			if(fromDateStr != null && toDateStr != null){
				fromDate = sdf.parse(fromDateStr);
				toDate = sdf.parse(toDateStr);
			}
			
			List<Object[]> alertList = alertAssignedOfficerDAO.getSubOrdinateLevelWiseAlertsDetails(designationId, levelId, levelValue, fromDate, toDate);
			setAlertDtls(returnList, alertList);
			
		} catch (Exception e) {
			logger.error("Error occured getSubOrdinateLocationWiseAlertDetails() method of CccDashboardService",e);
		}
		return returnList;
	}
	public List<AlertCoreDashBoardVO> getTotalAlertByStatusForDeptWiseClick(String fromDateStr, String toDateStr, Long stateId, List<Long> printIdList,
			List<Long> electronicIdList, List<Long> deptIdList,Long statusId,String type,Long userId){
		logger.info("Entered in getTotalAlertByStatus() method of CccDashboardService{}");
		try{
			Date fromDate = null;
			Date toDate = null;
			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
			if(fromDateStr != null && fromDateStr.trim().length() > 0 && toDateStr != null && toDateStr.trim().length() > 0){
				fromDate = sdf.parse(fromDateStr);
				toDate = sdf.parse(toDateStr);
			}
			
			Long levelId = 0l;
			List<Long> levelValues = new ArrayList<Long>();
			List<Object[]> accessList = govtAlertDepartmentLocationDAO.getUserAccessLevelsForUser(userId);
			if(accessList != null && !accessList.isEmpty()){
				for (Object[] obj : accessList) {
					//dptIdList.add(Long.valueOf(obj[0] != null ? obj[0].toString():"0"));
					levelId = Long.valueOf(obj[1] != null ? obj[1].toString():"0");
					levelValues.add(Long.valueOf(obj[2] != null ? obj[2].toString():"0"));
				}
			}
			
			List<AlertCoreDashBoardVO> alertCoreDashBoardVOs = new ArrayList<AlertCoreDashBoardVO>();
			//List<Object[]> alertList = alertDAO.getTotalAlertByStatusForDeptWiseClick(fromDate,toDate,stateId,printIdList,electronicIdList,deptIdList,statusId,type);
			List<Object[]> alertList = alertAssignedOfficerDAO.getTotalAlertByStatusForDeptWiseClick(deptIdList, levelId, levelValues, stateId, fromDate, toDate, printIdList, electronicIdList, type, statusId);
			setAlertDtls(alertCoreDashBoardVOs, alertList);    
			return alertCoreDashBoardVOs;
		}catch(Exception e){
			e.printStackTrace();
			logger.error("Error occured getTotalAlertByStatus() method of CccDashboardService{}");
		}
		return null;
	}
	
	public List<GovtDepartmentVO> getDesigAndStatusWiseAlertsCounts(Long departmentId,Long stateId,String fromDateStr,String toDateStr,List<Long> printIdsList,List<Long> electronicIdsList,Long userId){
		List<GovtDepartmentVO> returnList = new ArrayList<GovtDepartmentVO>();
		try {
			Map<Long,GovtDepartmentVO> designationMap = new LinkedHashMap<Long, GovtDepartmentVO>();
			Date fromDate = null;
			Date toDate = null;
			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
			if(fromDateStr != null && toDateStr != null){
				fromDate = sdf.parse(fromDateStr);
				toDate = sdf.parse(toDateStr);
			}
			
			/*if(printIdsList != null && printIdsList.size() > 0){  
				if(electronicIdsList != null && electronicIdsList.size() == 0){
					electronicIdsList.add(0L);
				}
			}else if(electronicIdsList != null && electronicIdsList.size() > 0){
				if(printIdsList != null && printIdsList.size() == 0){
					printIdsList.add(0L);
				}
			}*/
			
			List<Long> dptIdList = new ArrayList<Long>();
			Long levelId = 0l;
			List<Long> levelValues = new ArrayList<Long>();
			List<Object[]> accessList = govtAlertDepartmentLocationDAO.getAccessDeptsAndLvlsForUserAndDept(userId, departmentId);
			if(accessList != null && !accessList.isEmpty()){
				for (Object[] obj : accessList) {
					dptIdList.add(Long.valueOf(obj[0] != null ? obj[0].toString():"0"));
					levelId = Long.valueOf(obj[1] != null ? obj[1].toString():"0");
					levelValues.add(Long.valueOf(obj[2] != null ? obj[2].toString():"0"));
				}
			}
			
			List<Object[]> statusList = alertDepartmentStatusDAO.getStatusWithoutPending();
			
			List<Object[]> list = alertAssignedOfficerDAO.getDesigAndStatusWiseAlertsCounts(dptIdList, levelId, levelValues, stateId, fromDate, toDate, printIdsList, electronicIdsList);
			if(list != null && !list.isEmpty()){
				for (Object[] obj : list) {
					Long designationId = Long.valueOf(obj[0] != null ? obj[0].toString():"0");
					Long statusId = Long.valueOf(obj[2] != null ? obj[2].toString():"0");
					
					GovtDepartmentVO vo = designationMap.get(designationId);
					if(vo == null){
						vo = new GovtDepartmentVO();
						vo.setId(designationId);
						vo.setName(obj[1] != null ? obj[1].toString():"0");
						vo.setGovtDeptList(setStatusList(statusList));
						GovtDepartmentVO stsvo = getMatchedVo(vo.getGovtDeptList(), statusId);
						if(stsvo != null){
							stsvo.setCount(Long.valueOf(obj[4] != null ? obj[4].toString():"0"));
							vo.setCount(vo.getCount()+stsvo.getCount());
						}
						designationMap.put(designationId, vo);	
					}
					else{
						GovtDepartmentVO stsvo = getMatchedVo(vo.getGovtDeptList(), statusId);
						if(stsvo != null){
							stsvo.setCount(Long.valueOf(obj[4] != null ? obj[4].toString():"0"));
							vo.setCount(vo.getCount()+stsvo.getCount());
						}
					}
				}
			}
			
			if(designationMap != null)
				returnList = new ArrayList<GovtDepartmentVO>(designationMap.values());
			
			/*if(returnList != null && !returnList.isEmpty()){
				for (GovtDepartmentVO designvo : returnList) {
					if(designvo.getGovtDeptList() != null && !designvo.getGovtDeptList().isEmpty()){
						for (GovtDepartmentVO stsvo : designvo.getGovtDeptList()) {
							if(stsvo != null && stsvo.getId() == 1l)
								designvo.setPendingTotal(designvo.getPendingTotal()+stsvo.getCount());
							else if(stsvo != null && stsvo.getId() == 2l)
								designvo.setNotifiedTotal(designvo.getNotifiedTotal()+stsvo.getCount());
							else if(stsvo != null && stsvo.getId() == 3l)
								designvo.setActionInprgsTtl(designvo.getActionInprgsTtl()+stsvo.getCount());
							else if(stsvo != null && stsvo.getId() == 4l)
								designvo.setCompletedTotal(designvo.getCompletedTotal()+stsvo.getCount());
							else if(stsvo != null && stsvo.getId() == 5l)
								designvo.setUnablToRslveTtl(designvo.getUnablToRslveTtl()+stsvo.getCount());
							else if(stsvo != null && stsvo.getId() == 6l)
								designvo.setAtnNotRqdTtl(designvo.getAtnNotRqdTtl()+stsvo.getCount());
							else if(stsvo != null && stsvo.getId() == 7l)
								designvo.setDuplicateTotal(designvo.getDuplicateTotal()+stsvo.getCount());
						}
					}
				}
			}*/
			
		} catch (Exception e) {
			logger.error("Error occured getDesigAndStatusWiseAlertsCounts() method of CccDashboardService",e);
		}
		return returnList;
	}
	
	public List<AlertCoreDashBoardVO> getDesigAndStatusWiseAlertDetails(Long departmentId,Long stateId,String fromDateStr,String toDateStr,List<Long> printIdsList,
			List<Long> electronicIdsList,Long userId,Long designationId,Long statusId){
		List<AlertCoreDashBoardVO> returnList = new ArrayList<AlertCoreDashBoardVO>();
		try {
			Date fromDate = null;
			Date toDate = null;
			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
			if(fromDateStr != null && toDateStr != null){
				fromDate = sdf.parse(fromDateStr);
				toDate = sdf.parse(toDateStr);
			}
			
			/*if(printIdsList != null && printIdsList.size() > 0){  
				if(electronicIdsList != null && electronicIdsList.size() == 0){
					electronicIdsList.add(0L);
				}
			}else if(electronicIdsList != null && electronicIdsList.size() > 0){
				if(printIdsList != null && printIdsList.size() == 0){
					printIdsList.add(0L);
				}
			}*/
			
			List<Long> dptIdList = new ArrayList<Long>();
			Long levelId = 0l;
			List<Long> levelValues = new ArrayList<Long>();
			List<Object[]> accessList = govtAlertDepartmentLocationDAO.getAccessDeptsAndLvlsForUserAndDept(userId, departmentId);
			if(accessList != null && !accessList.isEmpty()){
				for (Object[] obj : accessList) {
					dptIdList.add(Long.valueOf(obj[0] != null ? obj[0].toString():"0"));
					levelId = Long.valueOf(obj[1] != null ? obj[1].toString():"0");
					levelValues.add(Long.valueOf(obj[2] != null ? obj[2].toString():"0"));
				}
			}
			
			List<Object[]> alertList = alertAssignedOfficerDAO.getDesigAndStatusWiseAlertDetails(dptIdList, levelId, levelValues, stateId, fromDate, toDate, printIdsList, electronicIdsList, designationId, statusId);
			setAlertDtls(returnList, alertList);
			
		} catch (Exception e) {
			logger.error("Error occured getDesigAndStatusWiseAlertDetails() method of CccDashboardService",e);
		}
		return returnList;
	}
	/*
	 * Swadhin(non-Javadoc)
	 * @see com.itgrids.partyanalyst.service.ICccDashboardService#getTotalAlertDtls(String fromDateStr, String toDateStr,Long userId,Long statusId,Long deptId,String type)
	 * IAS Officer
	 */
	public List<AlertCoreDashBoardVO> getTotalAlertDtls(String fromDateStr, String toDateStr,Long userId,Long statusId,Long deptId,String type){
		logger.info("Entered in getTotalAlertDtls() method of CccDashboardService{}");
		try{
			
			DateUtilService dateUtilService = new DateUtilService();
			
			Date fromDate = null;
			Date toDate = null;
			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
			
			
			
			List<AlertCoreDashBoardVO> alertCoreDashBoardVOs = new ArrayList<AlertCoreDashBoardVO>();
			List<Long> dptIdList = new ArrayList<Long>();
			List<Object[]> dptsList = govtAlertDepartmentLocationDAO.getDeptListForUser(userId);
			if(dptsList != null && !dptsList.isEmpty()){
				for (Object[] obj : dptsList) {
					dptIdList.add(Long.valueOf(obj[0] != null ? obj[0].toString():"0"));
				}
			}
			List<Long> levelValuesList = new ArrayList<Long>();    
			Long levelId = 0L;
			List<Object[]> lvlValueAndLvlIdList = govtAlertDepartmentLocationDAO.getUserAccessLevels(userId);
			if(lvlValueAndLvlIdList != null && lvlValueAndLvlIdList.size() > 0){
				for(Object[] param : lvlValueAndLvlIdList){
					levelValuesList.add(commonMethodsUtilService.getLongValueForObject(param[1]));
					levelId = commonMethodsUtilService.getLongValueForObject(param[0]);
				}
			}
			
			if(type !=null && !type.trim().isEmpty() && type.trim().equalsIgnoreCase("toDay")){
				fromDate = dateUtilService.getCurrentDateAndTime();
				toDate = dateUtilService.getCurrentDateAndTime();
				if(deptId != null && deptId.longValue() > 0L){
					dptIdList.clear();
					dptIdList.add(deptId);
				}
			}else{
				if(fromDateStr != null && fromDateStr.trim().length() > 0 && toDateStr != null && toDateStr.trim().length() > 0){
					fromDate = sdf.parse(fromDateStr);
					toDate = sdf.parse(toDateStr);
				}
			}
			List<AlertVO> finalAlertVOs = new ArrayList<AlertVO>();
			List<Object[]> userInfoList = govtDepartmentDesignationOfficerDetailsDAO.getDeptDesigOfficerIdAndGovtOfficerIdForUserId(userId,dptIdList,levelId,levelValuesList);
			List<Object[]> alertList = alertAssignedOfficerDAO.getAlertIdAndDeptDesigOfficerIdAndGovtOfficerIdList(fromDate, toDate,dptIdList,levelId,levelValuesList,statusId);
			
			Set<Long> alertIdSet = new HashSet<Long>();
			Long deptDesigOfficerId = null;
			Long officerId = null;
			Long deptDesigOfficerId2 = null;
			Long officerId2 = null;
			if(userInfoList != null && userInfoList.size() > 0 && alertList != null && alertList.size() > 0){
				for(Object[] param : userInfoList){
					deptDesigOfficerId = commonMethodsUtilService.getLongValueForObject(param[0]);
					officerId = commonMethodsUtilService.getLongValueForObject(param[1]);
					if(deptDesigOfficerId.longValue() > 0L && officerId.longValue() > 0L){
						for(Object[] param2 : alertList){
							deptDesigOfficerId2 = commonMethodsUtilService.getLongValueForObject(param2[0]);
							officerId2 = commonMethodsUtilService.getLongValueForObject(param2[1]);
							if(deptDesigOfficerId2.longValue() > 0L && officerId2.longValue() > 0L){
								if(deptDesigOfficerId.equals(deptDesigOfficerId2) && officerId.equals(officerId2)){
									alertIdSet.add(commonMethodsUtilService.getLongValueForObject(param2[2]));
								}
							}
						}
					}
				}
			}
			if(alertIdSet != null && alertIdSet.size() > 0){
				List<Object[]> list = alertDAO.getAlertDtls(alertIdSet);
				setAlertDtls(alertCoreDashBoardVOs, list);
			}
			
			return alertCoreDashBoardVOs;   
		}catch(Exception e){
			e.printStackTrace();
			logger.error("Error occured getTotalAlertDtls() method of CccDashboardService{}");
		}
		return null;
	}
	/*
	 * Swadhin(non-Javadoc)
	 * @see com.itgrids.partyanalyst.service.ICccDashboardService#getTotalAlertByOtherStatus(String fromDateStr, String toDateStr, Long stateId, List<Long> printIdList, List<Long> electronicIdList, List<Long> deptIdList,Long statusId)
	 */
	public List<AlertCoreDashBoardVO> getTotalAlertByOtherStatus(String fromDateStr, String toDateStr, Long stateId, List<Long> printIdList, List<Long> electronicIdList, List<Long> deptIdList,Long statusId,Long userId){
		logger.info("Entered in getTotalAlertByStatus() method of CccDashboardService{}");
		try{
			Date fromDate = null;
			Date toDate = null;
			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
			if(fromDateStr != null && fromDateStr.trim().length() > 0 && toDateStr != null && toDateStr.trim().length() > 0){
				fromDate = sdf.parse(fromDateStr);
				toDate = sdf.parse(toDateStr);
			}
			List<Long> levelValuesList = new ArrayList<Long>();    
			Long levelId = 0L;
			List<Object[]> lvlValueAndLvlIdList = govtAlertDepartmentLocationDAO.getUserAccessLevels(userId);
			if(lvlValueAndLvlIdList != null && lvlValueAndLvlIdList.size() > 0){
				for(Object[] param : lvlValueAndLvlIdList){
					levelValuesList.add(commonMethodsUtilService.getLongValueForObject(param[1]));
					levelId = commonMethodsUtilService.getLongValueForObject(param[0]);
				}
			}
			
			List<AlertCoreDashBoardVO> alertCoreDashBoardVOs = new ArrayList<AlertCoreDashBoardVO>();
			List<Long> alertIdSet = alertAssignedOfficerDAO.getTotalAlertByOtherStatus(fromDate,toDate,stateId,printIdList,electronicIdList,deptIdList,statusId,levelId,levelValuesList);
			if(alertIdSet != null && alertIdSet.size() > 0){
				List<Object[]> list = alertDAO.getAlertDtls(new HashSet<Long>(alertIdSet));
				setAlertDtls(alertCoreDashBoardVOs, list);
			}
			
			return alertCoreDashBoardVOs;
		}catch(Exception e){
			e.printStackTrace();
			logger.error("Error occured getTotalAlertByStatus() method of CccDashboardService{}");
		}
		return null;  
	}
	/*
	 * Swadhin(non-Javadoc) Both IAS and collector
	 */
	//getTotalAlertGroutByDeptThenStatus
	public List<AlertCoreDashBoardVO> getTotalAlertDetailsGroupByDeptThenStatus(String fromDateStr, String toDateStr, Long stateId, List<Long> printIdList, List<Long> electronicIdList, Long userId,Long deptId, Long statusId){
		logger.info("Entered in getTotalAlertDetailsGroupByDeptThenStatus() method of CccDashboardService{}");
		try{
			Date fromDate = null; 
			Date toDate = null;
			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
			if(fromDateStr != null && fromDateStr.trim().length() > 0 && toDateStr != null && toDateStr.trim().length() > 0){
				fromDate = sdf.parse(fromDateStr);
				toDate = sdf.parse(toDateStr);
			}
			if(printIdList != null && printIdList.size() > 0){
				if(electronicIdList != null && electronicIdList.size() == 0){
					electronicIdList.add(0L);
				}
			}else if(electronicIdList != null && electronicIdList.size() > 0){
				if(printIdList != null && printIdList.size() == 0){
					printIdList.add(0L);
				}
			}
			
			Map<Long,Long> deptIdAndCountMap = new HashMap<Long,Long>();
			List<AlertCoreDashBoardVO> alertCoreDashBoardVOs = new ArrayList<AlertCoreDashBoardVO>();
			//get all the alert category for  building the template
			//List<Object[]> deptList = govtDepartmentDAO.getAllDepartment();//old
			
			//get all the status
			
			Object[] obj = null;
			List<Long> deptList = new ArrayList<Long>();
			if(deptId != null && deptId.longValue() > 0L){
				deptList.add(deptId);
			}
			//get alert status count and and create a map of alertStatusId and its corresponding  alert count
			//List<Object[]> alertCountList = alertDAO.getTotalAlertGroupByStatusForGovt(fromDate,toDate,stateId,printIdList,electronicIdList,deptIdList);//old
			List<Long> lvlIdList = new ArrayList<Long>();
			Long lvlValue = 0L;
			List<Object[]> lvlValueAndLvlIdList = govtAlertDepartmentLocationDAO.getUserAccessLevels(userId);
			if(lvlValueAndLvlIdList != null && lvlValueAndLvlIdList.size() > 0){
				for(Object[] param : lvlValueAndLvlIdList){
					lvlIdList.add(commonMethodsUtilService.getLongValueForObject(param[1]));
					lvlValue = commonMethodsUtilService.getLongValueForObject(param[0]);
				}
			}
			
			//List<Object[]> alertCountGrpByDeptList = alertDAO.getTotalAlertGroupByStatusThenDepartmentForGovt(fromDate,toDate,stateId,printIdList,electronicIdList,deptIdList);//old
			List<Long> alertList = alertAssignedOfficerDAO.getTotalAlertIdGroupByDepartmentThenStatusForGovt(fromDate,toDate,stateId,printIdList,electronicIdList,deptList,lvlValue,lvlIdList,statusId);
			
			if(alertList != null && alertList.size() > 0){
				List<Object[]> list = alertDAO.getAlertDtls(new HashSet<Long>(alertList));
				setAlertDtls(alertCoreDashBoardVOs, list);
			}
			
			return alertCoreDashBoardVOs;
		}catch(Exception e){
			e.printStackTrace();
			logger.error("Error occured getTotalAlertDetailsGroupByDeptThenStatus() method of CccDashboardService{}");
		}
		return null;
	}
	/*
	 * Swadhin(non-Javadoc)
	 */
	//getLocationWiseThenStatusWiseAlertCountDetails
	public List<AlertCoreDashBoardVO> getAlertCountDetailsLocationWiseThenStatusWise(String fromDateStr, String toDateStr, Long stateId, List<Long> printIdList, List<Long> electronicIdList, List<Long> govtDepartmentIds,Long lvlValue,Long locId,Long statusId){
		try{
			Date fromDate = null;  
			Date toDate = null;
			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
			List<AlertCoreDashBoardVO> alertCoreDashBoardVOs = new ArrayList<AlertCoreDashBoardVO>();
			if(fromDateStr != null && fromDateStr.trim().length() > 0 && toDateStr != null && toDateStr.trim().length() > 0){
				fromDate = sdf.parse(fromDateStr);
				toDate = sdf.parse(toDateStr);
			}
			if(printIdList != null && printIdList.size() > 0){
				if(electronicIdList != null && electronicIdList.size() == 0){
					electronicIdList.add(0L);
				}
			}else if(electronicIdList != null && electronicIdList.size() > 0){
				if(printIdList != null && printIdList.size() == 0){
					printIdList.add(0L);      
				}
			}  
			
			List<Long> alertList = alertAssignedOfficerDAO.getLocationWiseThenStatusWiseAlertCountDetails(fromDate, toDate, stateId, printIdList, electronicIdList, govtDepartmentIds, lvlValue,locId,statusId);
			if(alertList != null && alertList.size() > 0){
				List<Object[]> list = alertDAO.getAlertDtls(new HashSet<Long>(alertList));
				setAlertDtls(alertCoreDashBoardVOs, list);
			}  
			
			return alertCoreDashBoardVOs;
		}catch(Exception e){
			e.printStackTrace();
			logger.error("Error occured getAlertCountLocationWiseThenStatusWise() method of CccDashboardService{}");
		}
		return null;
	}
}
