package com.itgrids.partyanalyst.service.impl;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

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
import com.itgrids.partyanalyst.service.ICccDashboardService;
import com.itgrids.partyanalyst.utils.CommonMethodsUtilService;
import com.itgrids.partyanalyst.utils.DateUtilService;

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
	public List<AlertVO> getTotalAlertGroupByStatus(String fromDateStr, String toDateStr, Long stateId, List<Long> printIdList, List<Long> electronicIdList, List<Long> deptIdList){
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
			
			//get all the alert status and build the template
			List<Object[]> statusList = alertStatusDAO.getAllStatus();
			//get alert status count and and create a map of alertStatusId and its count
			List<Object[]> alertCountList = alertDAO.getTotalAlertGroupByStatusForGovt(fromDate,toDate,stateId,printIdList,electronicIdList,deptIdList);
			buildStatusWiseAlertCount(statusList,alertCountList,finalAlertVOs);
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
	public List<AlertVO> getTotalAlertGroupByStatusThenDepartment(String fromDateStr, String toDateStr, Long stateId, List<Long> printIdList, List<Long> electronicIdList, List<Long> deptIdList){
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
			
			//get alert status count and and create a map of alertStatusId and its corresponding  alert count
			List<Object[]> alertCountList = alertDAO.getTotalAlertGroupByStatusForGovt(fromDate,toDate,stateId,printIdList,electronicIdList,deptIdList);
			if(alertCountList != null && alertCountList.size() > 0){
				for(Object[] param : alertCountList){
					statusIdAndCountMap.put(commonMethodsUtilService.getLongValueForObject(param[0]), commonMethodsUtilService.getLongValueForObject(param[2]));
				}
			}
			
			List<Object[]> alertCountGrpByDeptList = alertDAO.getTotalAlertGroupByStatusThenDepartmentForGovt(fromDate,toDate,stateId,printIdList,electronicIdList,deptIdList);
			buildAlertGroupByStatusThenDepartment(alertCountGrpByDeptList,statusIdAndCountMap,deptList,finalListNew,"true");
			return finalListNew;     
		}catch(Exception e){
			e.printStackTrace();
			logger.error("Error occured getTotalAlertGroupByStatusThenDepartment() method of CccDashboardService{}");
		}
		return null;
	}
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
						AlertDepartmentDocument alertDepartmentDocument = new AlertDepartmentDocument();
						alertDepartmentDocument.setDocument(inputvo.getDocument());
						alertDepartmentDocument.setInsertedBy(inputvo.getUserId());
						alertDepartmentDocument.setInsertedTime(new DateUtilService().getCurrentDateAndTime());
						alertDepartmentDocument = alertDepartmentDocumentDAO.save(alertDepartmentDocument);
						documentIds.add(alertDepartmentDocument.getAlertDepartmentDocumentId());
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
					List<Long> designationOfficerIds = govtDepartmentDesignationOfficerDAO.getDesignationOfficerIds(inputvo.getLevelId(), inputvo.getLevelValue(), inputvo.getDesignationId());
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
			List<AlertVO> finalListNew = new ArrayList<AlertVO>();
			//get all the alert category for  building the template
			//List<Object[]> deptList = govtDepartmentDAO.getAllDepartment();//old
			
			//get all the status
			List<Object[]> statusList = new ArrayList<Object[]>();
			Object[] obj = null;
			List<Long> deptList = new ArrayList<Long>();
			List<Object[]> dptIdList = govtAlertDepartmentLocationDAO.getDeptListForUser(1L);
			if(dptIdList != null && dptIdList.size() > 0){
				for(Object[] param : dptIdList){
					deptList.add(commonMethodsUtilService.getLongValueForObject(param[0]));
				}
			}
			List<Object[]> alertCountList = alertAssignedOfficerDAO.getTotalAlertGroupByStatusForGovtOneDept(fromDate,toDate,stateId,printIdList,electronicIdList,deptList);
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
			
			List<Object[]> alertCntList = alertAssignedOfficerDAO.getDepartmentWiseAlertCount(fromDate,toDate,stateId,printIdList,electronicIdList,deptList);
			if(alertCntList != null && alertCntList.size() > 0){
				for(Object[] param : alertCntList){  
					deptIdAndCountMap.put(commonMethodsUtilService.getLongValueForObject(param[0]), commonMethodsUtilService.getLongValueForObject(param[2]));
				}
			}
			
			//List<Object[]> alertCountGrpByDeptList = alertDAO.getTotalAlertGroupByStatusThenDepartmentForGovt(fromDate,toDate,stateId,printIdList,electronicIdList,deptIdList);//old
			List<Object[]> alertCountGrpByDeptList = alertAssignedOfficerDAO.getTotalAlertGroupByDepartmentThenStatusForGovt(fromDate,toDate,stateId,printIdList,electronicIdList,deptList);
					
			
			buildAlertGroupByStatusThenDepartment(alertCountGrpByDeptList,deptIdAndCountMap,statusList,finalListNew,"true");
			
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
	public List<AlertVO> getAlertCountLocationWiseThenStatusWise(String fromDateStr, String toDateStr, Long stateId, List<Long> printIdList, List<Long> electronicIdList, Long govtDepartmentId,Long lvlValue){
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
			List<Object[]> statusList = alertDepartmentStatusDAO.getAllStatusForDepartment(govtDepartmentId);
			List<Object[]> alertCountList = alertAssignedOfficerDAO.getLocationWiseThenStatusWiseAlertCount(fromDate, toDate, stateId, printIdList, electronicIdList, govtDepartmentId, lvlValue);
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
			logger.error("Error occured getAllStatusForDepartment() method of CccDashboardService{}");
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
	public List<GovtDepartmentVO> getDistrictWiseTotalAlertsForAlert(String startDateStr,String endDateStr,Long stateId,
			 List<Long> deptIdList,List<Long> paperIdList,List<Long> chanelIdList ){
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
			//departmentId-0,departmentName-1,districtId-2,districtName-3,Count-4
			distWiseAlertLst = alertDAO.getDistrictWiseTotalAlertsForAlert(fromDate, toDate, stateId, deptIdList, paperIdList, chanelIdList);
			
			if(distWiseAlertLst != null && distWiseAlertLst.size() > 0){
				for (Object[] objects : distWiseAlertLst) {
					GovtDepartmentVO matchedVO = getmatchedDeptVo(finalVOList,(Long)objects[0]);
					if(matchedVO == null){
						matchedVO = new GovtDepartmentVO();
						matchedVO.setDepartmentId((Long)objects[0]);
						matchedVO.setDepartment(objects[1].toString());
						
					GovtDepartmentVO districtVO = new GovtDepartmentVO();
						districtVO.setDepartmentId((Long)objects[2]);
						districtVO.setDepartment(objects[3].toString());
							districtVO.setCount((Long)objects[4]);
							
						matchedVO.getGovtDepartmentVOList().add(districtVO);	
						finalVOList.add(matchedVO);
					}else{
						GovtDepartmentVO matchedDistVO = getmatchedDeptVo(matchedVO.getGovtDepartmentVOList(), (Long)objects[2]);
						if(matchedDistVO == null){
								matchedDistVO = new GovtDepartmentVO();
								matchedDistVO.setDepartmentId((Long)objects[2]);
								matchedDistVO.setDepartment(objects[3].toString());
								matchedDistVO.setCount(matchedDistVO.getCount()+(Long)objects[4]);
								
							matchedVO.getGovtDepartmentVOList().add(matchedDistVO);
						}else{
							matchedDistVO.setCount(matchedDistVO.getCount()+(Long)objects[4]);
						}
					}
				}
			}
			//statusWiseMediaTypeTotalCounts
			//statusId-0,status-1,Count-2
			statusWiseCntsLst = alertDAO.getStatusWiseTotalCountsForAlert(fromDate, toDate, stateId, deptIdList, paperIdList, chanelIdList);
 			 if(statusWiseCntsLst != null && statusWiseCntsLst.size() > 0){
				 setDataToVoList(statusWiseCntsLst,finalVOList);
			 }
 			 if(finalVOList.get(0).getGovtDeptList() != null && finalVOList.get(0).getGovtDeptList().size() >0){
 				 for (GovtDepartmentVO mainVo : finalVOList) {
 					 for (GovtDepartmentVO statusTotVo : finalVOList.get(0).getGovtDeptList()){
  					 	statusTotVo.setCount(statusTotVo.getPrintCnt()+statusTotVo.getElecCnt());//statusCount
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
	//statusId-0,status-1,Count-2,alertCategoryId-3
	public void setDataToVoList(List<Object[]> objList,List<GovtDepartmentVO> finalVOList){
		if(objList != null && objList.size() > 0){
			for (Object[] objects : objList) {
				GovtDepartmentVO matchedStatusVO = getmatchedDeptVo(finalVOList.get(0).getGovtDeptList(),commonMethodsUtilService.getLongValueForObject(objects[0]));
				if(matchedStatusVO == null){
					matchedStatusVO = new GovtDepartmentVO();
					matchedStatusVO.setDepartmentId(commonMethodsUtilService.getLongValueForObject(objects[0]));
					matchedStatusVO.setDepartment(commonMethodsUtilService.getStringValueForObject(objects[1]));
					
					if(commonMethodsUtilService.getLongValueForObject(objects[3]) == 2l){
						matchedStatusVO.setPrintCnt(commonMethodsUtilService.getLongValueForObject(objects[2]));//For Print
					}else if(commonMethodsUtilService.getLongValueForObject(objects[3]) == 3l){
						matchedStatusVO.setElecCnt(commonMethodsUtilService.getLongValueForObject(objects[2]));//For Electronic
					}
					
					finalVOList.get(0).getGovtDeptList().add(matchedStatusVO);
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
	public List<GovtDepartmentVO> getStatusWiseDistrictTotalForAlert(String startDateStr,String endDateStr,Long stateId,
			 List<Long> deptIdList,List<Long> paperIdList,List<Long> chanelIdList ){
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
			//districtId-0,districtName-1,statusId-2,statusName-3,count-4
			statusWiseDistLst = alertDAO.getDistWiseTotalAlertsStatusForAlert(fromDate, toDate, stateId, deptIdList, paperIdList, chanelIdList);
			
			if(statusWiseDistLst != null && statusWiseDistLst.size() > 0){
				for (Object[] objects : statusWiseDistLst) {
					GovtDepartmentVO matchedDistVO = getmatchedDeptVo(finalVOList,(Long)objects[0]);
					if(matchedDistVO == null){
						matchedDistVO = new GovtDepartmentVO();
						matchedDistVO.setDepartmentId((Long)objects[0]);
						matchedDistVO.setDepartment(objects[1].toString());
						
					GovtDepartmentVO statusVO = new GovtDepartmentVO();
						statusVO.setDepartmentId((Long)objects[2]);
						statusVO.setDepartment(objects[3].toString());
						statusVO.setCount((Long)objects[4]);
							
							matchedDistVO.getGovtDepartmentVOList().add(statusVO);	
						finalVOList.add(matchedDistVO);
					}else{
						GovtDepartmentVO matchedStatusVO = getmatchedDeptVo(matchedDistVO.getGovtDepartmentVOList(), (Long)objects[2]);
						if(matchedStatusVO == null){
							matchedStatusVO = new GovtDepartmentVO();
							matchedStatusVO.setDepartmentId((Long)objects[2]);
							matchedStatusVO.setDepartment(objects[3].toString());
							matchedStatusVO.setCount(matchedDistVO.getCount()+(Long)objects[4]);
								
							matchedDistVO.getGovtDepartmentVOList().add(matchedStatusVO);
						}else{
							matchedStatusVO.setCount(matchedDistVO.getCount()+(Long)objects[4]);
						}
					}
				}
			}
			//statusWiseMediaTypeTotalCounts
			//statusId-0,status-1,Count-2
			statusWiseCntsLst = alertDAO.getStatusWiseTotalCountsForAlert(fromDate, toDate, stateId, deptIdList, paperIdList, chanelIdList);
			 if(statusWiseCntsLst != null && statusWiseCntsLst.size() > 0){
				 setDataToVoList(statusWiseCntsLst,finalVOList);
			 }
			 if(finalVOList != null && finalVOList.size() >0){
				 for (GovtDepartmentVO govtDeptVo : finalVOList) {
					 if(govtDeptVo.getGovtDepartmentVOList() != null && govtDeptVo.getGovtDepartmentVOList().size() > 0){
						 for (GovtDepartmentVO vo2 : govtDeptVo.getGovtDepartmentVOList()) {
							 vo2.setTotalCount(vo2.getCount());
							 if(vo2.getTotalCount()>0l){
								 vo2.setPercentage(caclPercantage(vo2.getCount(),vo2.getTotalCount())); 
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
					
					returnList.add(vo);
				}
			}
		} catch (Exception e) {
			logger.error("Error occured getInvolvedMembersInAlert() method of CccDashboardService",e);
		}
		return returnList;
	}
	
	public List<AlertVO> getStatusWiseAlertDetails(String fromDateStr, String toDateStr, Long stateId, List<Long> printIdList, List<Long> electronicIdList, Long userId){
		logger.info("Entered in getTotalAlertGroupByStatusForOneDept() method of CccDashboardService{}");
		List<AlertVO> returnList = new ArrayList<AlertVO>();
		try {
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
			
			List<Long> dptIdList = new ArrayList<Long>();
			List<Object[]> dptsList = govtAlertDepartmentLocationDAO.getDeptListForUser(userId);
			if(dptsList != null && !dptsList.isEmpty()){
				for (Object[] obj : dptsList) {
					dptIdList.add(Long.valueOf(obj[0] != null ? obj[0].toString():"0"));
				}
			}
			List<Object[]> list = alertAssignedOfficerDAO.getStatusWiseAlertDetails(fromDate, toDate, stateId, printIdList, electronicIdList, dptIdList);
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
					vo.setNoOfDays(new DateUtilService().noOfDayBetweenDates(vo.getDate1(), vo.getDate2()));
					
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
			userId = 1L;
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
}
