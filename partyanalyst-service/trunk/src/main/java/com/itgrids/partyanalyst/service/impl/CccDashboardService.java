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

import com.itgrids.partyanalyst.dao.IAlertDAO;
import com.itgrids.partyanalyst.dao.IAlertStatusDAO;
import com.itgrids.partyanalyst.dao.IGovtDepartmentDAO;
import com.itgrids.partyanalyst.dto.AlertCoreDashBoardVO;
import com.itgrids.partyanalyst.dto.AlertVO;
import com.itgrids.partyanalyst.service.ICccDashboardService;
import com.itgrids.partyanalyst.utils.CommonMethodsUtilService;

public class CccDashboardService extends AlertService implements ICccDashboardService{
	private static final Logger logger = Logger.getLogger(CccDashboardService.class);
	private IAlertDAO alertDAO;
	private IAlertStatusDAO alertStatusDAO;
	private IGovtDepartmentDAO govtDepartmentDAO;
	private CommonMethodsUtilService commonMethodsUtilService;
	
	
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
	
	
}
