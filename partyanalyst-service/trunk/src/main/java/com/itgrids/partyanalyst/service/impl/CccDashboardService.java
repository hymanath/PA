package com.itgrids.partyanalyst.service.impl;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
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
import com.itgrids.partyanalyst.dao.IAlertDAO;
import com.itgrids.partyanalyst.dao.IAlertDepartmentCommentDAO;
import com.itgrids.partyanalyst.dao.IAlertDepartmentDocumentDAO;
import com.itgrids.partyanalyst.dao.IAlertStatusDAO;
import com.itgrids.partyanalyst.dao.IConstituencyDAO;
import com.itgrids.partyanalyst.dao.IDistrictDAO;
import com.itgrids.partyanalyst.dao.IGovtDepartmentDAO;
import com.itgrids.partyanalyst.dao.IGovtDepartmentDesignationDAO;
import com.itgrids.partyanalyst.dao.IGovtDepartmentDesignationOfficerDAO;
import com.itgrids.partyanalyst.dao.IGovtDepartmentDesignationOfficerDetailsDAO;
import com.itgrids.partyanalyst.dao.IGovtDepartmentLevelDAO;
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
			AlertVO alertVO = null;
			List<AlertVO> alertVOs = new ArrayList<AlertVO>();
			List<AlertVO> finalAlertVOs = new ArrayList<AlertVO>();
			Map<Long,Long> statusIdAndCountMap = new HashMap<Long,Long>();
			//get all the alert status and build the template
			List<Object[]> statusList = alertStatusDAO.getAllStatus();
			if(statusList != null && statusList.size() > 0){
				for(Object[] param : statusList){
					alertVO = new AlertVO();
					alertVO.setStatusId(commonMethodsUtilService.getLongValueForObject(param[0]));
					alertVO.setStatus(commonMethodsUtilService.getStringValueForObject(param[1]));
					alertVOs.add(alertVO);
				}
			}
			//get alert status count and and create a map of alertStatusId and its count
			List<Object[]> alertCountList = alertDAO.getTotalAlertGroupByStatusForGovt(fromDate,toDate,stateId,printIdList,electronicIdList,deptIdList);
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
			AlertVO alertVO = null;
			List<AlertVO> alertVOs = null;//new ArrayList<AlertVO>();
			Map<Long,Long> statusIdAndCountMap = new HashMap<Long,Long>();
			//get all the alert category for  building the template
			List<Object[]> deptList = govtDepartmentDAO.getAllDepartment(); 
			
			//get alert status count and and create a map of alertStatusId and its corresponding  alert count
			List<Object[]> alertCountList = alertDAO.getTotalAlertGroupByStatusForGovt(fromDate,toDate,stateId,printIdList,electronicIdList,deptIdList);
			if(alertCountList != null && alertCountList.size() > 0){
				for(Object[] param : alertCountList){
					statusIdAndCountMap.put(commonMethodsUtilService.getLongValueForObject(param[0]), commonMethodsUtilService.getLongValueForObject(param[2]));
				}
			}
			//get all the alert count group by status then category.
			Map<Long,String> statusIdAndNameMap = new HashMap<Long,String>();
			Map<Long,Long> deptIdAndCountMap = null;//new HashMap<Long, Long>();
			Map<Long,Map<Long,Long>> statusIdAndCategoryIdAndCountMap = new HashMap<Long,Map<Long,Long>>();
			List<Object[]> alertCountGrpByDeptList = alertDAO.getTotalAlertGroupByStatusThenDepartmentForGovt(fromDate,toDate,stateId,printIdList,electronicIdList,deptIdList);
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
			List<AlertVO> finalListNew = new ArrayList<AlertVO>();
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
			}
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
				if(levelId == 1l)							//State
					list = stateDAO.getTeluguStates();
				else if(levelId == 2l)						//District
					list = districtDAO.getDistrictsWithNewDistricts();
				else if(levelId == 3l)						//Constituenncy
					list = constituencyDAO.getConstituencyByStateDetails();
				else if(levelId == 4l){						//Mandal/Muncipality
					List<Object[]> mandalList = tehsilDAO.getAllTehsilListByState();
					List<Object[]> lebList = localElectionBodyDAO.getAllLocalElectionBodyListByState();
					
					list.addAll(mandalList);
					list.addAll(lebList);
				}
				
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
					
					//Comments Saving
					AlertDepartmentComment alertDepartmentComment = new AlertDepartmentComment();
					alertDepartmentComment.setComment(inputvo.getComment());
					alertDepartmentComment.setInsertedBy(inputvo.getUserId());
					alertDepartmentComment.setInsertedTime(new DateUtilService().getCurrentDateAndTime());
					alertDepartmentComment = alertDepartmentCommentDAO.save(alertDepartmentComment);
					
					//Documents Saving
					AlertDepartmentDocument alertDepartmentDocument = new AlertDepartmentDocument();
					alertDepartmentDocument.setDocument(inputvo.getDocument());
					alertDepartmentDocument.setInsertedBy(inputvo.getUserId());
					alertDepartmentDocument.setInsertedTime(new DateUtilService().getCurrentDateAndTime());
					alertDepartmentDocument = alertDepartmentDocumentDAO.save(alertDepartmentDocument);
					
					//Alert Status Updation
					Alert alert = alertDAO.get(inputvo.getAlertId());
					alert.setAlertStatusId(2l);
					alert.setUpdatedTime(new DateUtilService().getCurrentDateAndTime());
					alert = alertDAO.save(alert);
					
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
					AlertAssignedOfficerAction alertAssignedOfficerAction = new AlertAssignedOfficerAction();
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
					alertAssignedOfficerAction = alertAssignedOfficerActionDAO.save(alertAssignedOfficerAction);
					
					if(alertAssignedOfficerAction != null)
						return "success";
					else
						return "failure";
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
		List<GovtDepartmentVO> returnList = new ArrayList<GovtDepartmentVO>();
		try {
			List<Object[]> list = alertAssignedOfficerTrackingDAO.getStatusWiseTrackingComments(alertId);
			if(list != null && !list.isEmpty()){
				for (Object[] obj : list) {
					GovtDepartmentVO vo = new GovtDepartmentVO();
					
					vo.setId(Long.valueOf(obj[0] != null ? obj[0].toString():"0"));
					vo.setName(obj[1] != null ? obj[1].toString():"");
					vo.setCommentId(Long.valueOf(obj[2] != null ? obj[2].toString():"0"));
					vo.setComment(obj[3] != null ? obj[3].toString():"");
					returnList.add(vo);
				}
			}
		} catch (Exception e) {
			logger.error("Error occured getStatusWiseCommentsTracking() method of CccDashboardService",e);
		}
		return returnList;
	}
}
