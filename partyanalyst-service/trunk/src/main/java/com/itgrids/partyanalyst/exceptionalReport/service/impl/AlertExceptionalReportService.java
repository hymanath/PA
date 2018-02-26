package com.itgrids.partyanalyst.exceptionalReport.service.impl;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.apache.log4j.Logger;

import com.itgrids.partyanalyst.dao.IAlertDAO;
import com.itgrids.partyanalyst.dto.AlertCoreDashBoardVO;
import com.itgrids.partyanalyst.dto.TrainingCampProgramVO;
import com.itgrids.partyanalyst.exceptionalReport.service.IAlertExceptionalReportService;
import com.itgrids.partyanalyst.utils.CommonMethodsUtilService;
import com.itgrids.partyanalyst.utils.DateUtilService;
import com.itgrids.partyanalyst.utils.SetterAndGetterUtilService;

public class AlertExceptionalReportService implements IAlertExceptionalReportService {
	private final static Logger LOG = Logger.getLogger(AlertExceptionalReportService.class);
	
	private DateUtilService dateUtilService;
	private CommonMethodsUtilService commonMethodsUtilService;
	private SetterAndGetterUtilService setterAndGetterUtilService;
	
	private IAlertDAO alertDAO;

	public void setDateUtilService(DateUtilService dateUtilService) {
		this.dateUtilService = dateUtilService;
	}

	public void setCommonMethodsUtilService(
			CommonMethodsUtilService commonMethodsUtilService) {
		this.commonMethodsUtilService = commonMethodsUtilService;
	}

	public void setSetterAndGetterUtilService(
			SetterAndGetterUtilService setterAndGetterUtilService) {
		this.setterAndGetterUtilService = setterAndGetterUtilService;
	}

	public void setAlertDAO(IAlertDAO alertDAO) {
		this.alertDAO = alertDAO;
	}
	
	public List<AlertCoreDashBoardVO> getAssignedCandidateWisePendingAlerts(String startDate,String endDate, Long stateId,int size,List<Long> alertTypeIds){
		try{
			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
			Date stDate = null;
			Date ndDate = null;
			if(startDate != null && startDate.trim().length() == 10 && endDate != null && endDate.trim().length() == 10){
				stDate = sdf.parse(startDate.trim());
				ndDate = sdf.parse(endDate.trim());
			}
			List<AlertCoreDashBoardVO> alertCoreDashBoardVOs = new ArrayList<AlertCoreDashBoardVO>();
			List<Object[]> candidateList = alertDAO.getAssignedCandidateWisePendingAlerts(stDate,ndDate,stateId,alertTypeIds);
			//create map of caderId and AlertCoreDashBoardVO
			Map<Long,AlertCoreDashBoardVO> cadreIdAndAlertDtlsMap = new HashMap<Long,AlertCoreDashBoardVO>();
			AlertCoreDashBoardVO coreDashBoardVO = null;
			//totalCount
			if(candidateList != null && candidateList.size() > 0){
				for(Object[] param : candidateList){
					coreDashBoardVO = cadreIdAndAlertDtlsMap.get(commonMethodsUtilService.getLongValueForObject(param[0]));
					if(null == coreDashBoardVO){
						coreDashBoardVO = new AlertCoreDashBoardVO();
						coreDashBoardVO.setId(commonMethodsUtilService.getLongValueForObject(param[0]));
						coreDashBoardVO.setName(commonMethodsUtilService.getStringValueForObject(param[1]));
						if(commonMethodsUtilService.getLongValueForObject(param[2]).longValue() == 2L || commonMethodsUtilService.getLongValueForObject(param[2]).longValue() == 3L || commonMethodsUtilService.getLongValueForObject(param[2]).longValue() == 4L){
							coreDashBoardVO.setTotalCount(coreDashBoardVO.getTotalCount()+commonMethodsUtilService.getLongValueForObject(param[5]));
						}
						if(commonMethodsUtilService.getLongValueForObject(param[2]).longValue() == 3L){
							coreDashBoardVO.setPendingCount(coreDashBoardVO.getPendingCount()+commonMethodsUtilService.getLongValueForObject(param[5]));
						}
						coreDashBoardVO.setConstituencyId(commonMethodsUtilService.getLongValueForObject(param[6]));
						coreDashBoardVO.setConstituency(commonMethodsUtilService.getStringValueForObject(param[4]));
						coreDashBoardVO.setParliament(commonMethodsUtilService.getStringValueForObject(param[8]));
						cadreIdAndAlertDtlsMap.put(commonMethodsUtilService.getLongValueForObject(param[0]),coreDashBoardVO);
					}else{
						if(commonMethodsUtilService.getLongValueForObject(param[2]).longValue() == 2L || commonMethodsUtilService.getLongValueForObject(param[2]).longValue() == 3L || commonMethodsUtilService.getLongValueForObject(param[2]).longValue() == 4L){
							coreDashBoardVO.setTotalCount(coreDashBoardVO.getTotalCount()+commonMethodsUtilService.getLongValueForObject(param[5]));
						}
						if(commonMethodsUtilService.getLongValueForObject(param[2]).longValue() == 3L){
							coreDashBoardVO.setPendingCount(coreDashBoardVO.getPendingCount()+commonMethodsUtilService.getLongValueForObject(param[5]));
						}
					}
				}
			}
			
			if(cadreIdAndAlertDtlsMap != null && cadreIdAndAlertDtlsMap.size() > 0){
				alertCoreDashBoardVOs = new ArrayList<AlertCoreDashBoardVO>(cadreIdAndAlertDtlsMap.values());
			}
			
			Collections.sort(alertCoreDashBoardVOs, new Comparator<AlertCoreDashBoardVO>(){
				@Override
				public int compare(AlertCoreDashBoardVO obj1,AlertCoreDashBoardVO obj2) {
					Long value1 = obj1.getPendingCount();
					Long value2 = obj2.getPendingCount();
					return value2.compareTo(value1);
				}
			});
			
			if(size >= 0){
				if(alertCoreDashBoardVOs.size() > size){
					alertCoreDashBoardVOs = alertCoreDashBoardVOs.subList(0, size);
				}
			}
			//push designation
			//first collect the cadre ids
			Set<Long> cadreIds = new HashSet<Long>();
			if(alertCoreDashBoardVOs != null && alertCoreDashBoardVOs.size() > 0){
				for(AlertCoreDashBoardVO param : alertCoreDashBoardVOs){
					cadreIds.add(param.getId());
				}
			}
			
			Map<Long,String> cadreIdAndDesignationStr = new HashMap<Long,String>();
			String designation = null;
			
			
			List<Object[]> disignationList1 = null;
			List<Object[]> disignationList2 = null;
			if(cadreIds != null && cadreIds.size() > 0){
				disignationList1 = alertDAO.getDesignationOfCadre(new ArrayList<Long>(cadreIds));
				disignationList2 = alertDAO.getPositionOfCadre(new ArrayList<Long>(cadreIds));
				if(disignationList1 != null && disignationList1.size() > 0){
					for(Object[] param : disignationList1){
						designation = cadreIdAndDesignationStr.get(commonMethodsUtilService.getLongValueForObject(param[0]));
						if(designation == null){
							if(commonMethodsUtilService.getStringValueForObject(param[1]).trim().length() > 1){
								cadreIdAndDesignationStr.put(commonMethodsUtilService.getLongValueForObject(param[0]), commonMethodsUtilService.getStringValueForObject(param[1]).trim());
							}
						}else{
							if(commonMethodsUtilService.getStringValueForObject(param[1]).trim().length() > 1){
								designation = designation + ",";
								designation = designation + commonMethodsUtilService.getStringValueForObject(param[1]).trim();
								cadreIdAndDesignationStr.put(commonMethodsUtilService.getLongValueForObject(param[0]), designation);
							}
						}
					}
				}
				
				
				if(disignationList2 != null && disignationList2.size() > 0){
					for(Object[] param : disignationList2){
						designation = cadreIdAndDesignationStr.get(commonMethodsUtilService.getLongValueForObject(param[0]));
						if(designation == null){
							if(commonMethodsUtilService.getStringValueForObject(param[1]).trim().length() > 1){
								cadreIdAndDesignationStr.put(commonMethodsUtilService.getLongValueForObject(param[0]), commonMethodsUtilService.getStringValueForObject(param[1]).trim());
							}
						}else{
							if(commonMethodsUtilService.getStringValueForObject(param[1]).trim().length() > 1){
								designation = designation + ",";
								designation = designation + commonMethodsUtilService.getStringValueForObject(param[1]).trim();
								cadreIdAndDesignationStr.put(commonMethodsUtilService.getLongValueForObject(param[0]), designation);
							}
						}
					}
				}
				
			}
			
			if(alertCoreDashBoardVOs != null && alertCoreDashBoardVOs.size() > 0){
				for(AlertCoreDashBoardVO param : alertCoreDashBoardVOs){
					param.setDesignation(cadreIdAndDesignationStr.get(param.getId()));
				}
			}
			
			return alertCoreDashBoardVOs;
		}catch(Exception e){
			LOG.error("Error occured getAssignedCandidateWisePendingAlerts() method of AlertExceptionalReportService{}");
		}
		return null;
	}
	public List<AlertCoreDashBoardVO> getOverAllAlertsDetails(String startDate,String endDate, Long stateId,int size,List<Long> alertTypeIds){
		try{
			List<AlertCoreDashBoardVO> alertCoreDashBoardVOs = new ArrayList<AlertCoreDashBoardVO>();
			AlertCoreDashBoardVO alertCoreDashBoardVO1 = null;
			AlertCoreDashBoardVO alertCoreDashBoardVO2 = null;
			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
			Date stDate = null;
			Date ndDate = null;
			if(startDate != null && startDate.trim().length() == 10 && endDate != null && endDate.trim().length() == 10){
				stDate = sdf.parse(startDate.trim());
				ndDate = sdf.parse(endDate.trim());
			}
			
			List<Object[]> overAllAlertDtls = alertDAO.getOverAllAlertDtls(null,null,stateId,alertTypeIds);
			Calendar calendar = Calendar.getInstance();
			calendar.add(Calendar.MONTH, -1);
			calendar.set(Calendar.DAY_OF_MONTH, calendar.getMinimalDaysInFirstWeek());	
			Date pastDate = calendar.getTime();
			calendar.setTime(pastDate);
			calendar.set(Calendar.DAY_OF_MONTH, calendar.getActualMaximum(Calendar.DAY_OF_MONTH));
			Date eendDate = calendar.getTime();
			Long overAllLastMonthCompleted = alertDAO.getOverAllLastMonthComp(pastDate,eendDate,stateId,alertTypeIds,"overAll");
			//1
			alertCoreDashBoardVO1 = new AlertCoreDashBoardVO();
			alertCoreDashBoardVO1.setName("Total Alerts");
			Long totalAlert = 0L;
			if(overAllAlertDtls != null && overAllAlertDtls.size() > 0){
				for(Object[] param : overAllAlertDtls){
					totalAlert = totalAlert + commonMethodsUtilService.getLongValueForObject(param[2]);
				}
				alertCoreDashBoardVO1.setTotalAlert(totalAlert);
			}
			
			AlertCoreDashBoardVO tempVO = null;
			List<AlertCoreDashBoardVO> tempList = null;
			if(overAllAlertDtls != null && overAllAlertDtls.size() > 0){
				tempList = new ArrayList<AlertCoreDashBoardVO>();
				for(Object[] param : overAllAlertDtls){
					tempVO = new AlertCoreDashBoardVO();
					tempVO.setId(commonMethodsUtilService.getLongValueForObject(param[0]));
					tempVO.setName(commonMethodsUtilService.getStringValueForObject(param[1]));
					tempVO.setCount(commonMethodsUtilService.getLongValueForObject(param[2]));
					tempVO.setCountPerc(calculatePercantage(tempVO.getCount(),totalAlert));
					tempList.add(tempVO);
				}
				tempVO.setId(0L);
				tempVO.setName("Last Month Completed");
				tempVO.setCount(overAllLastMonthCompleted);
				tempVO.setCountPerc(calculatePercantage(overAllLastMonthCompleted,totalAlert));
				tempList.add(tempVO);
				alertCoreDashBoardVO1.getSubList().addAll(tempList);
			}
			
			List<Object[]> lastMonthAllAlertDtls = alertDAO.getOverAllAlertDtls(pastDate,eendDate,stateId,alertTypeIds);
			Long lastMonthCompleted = alertDAO.getOverAllLastMonthComp(pastDate,eendDate,stateId,alertTypeIds,"lastMonth");
			
			//2
			alertCoreDashBoardVO2 = new AlertCoreDashBoardVO();
			alertCoreDashBoardVO2.setName("Last Month Alerts");
			Long totalAlertLastMonth = 0L;
			if(lastMonthAllAlertDtls != null && lastMonthAllAlertDtls.size() > 0){
				for(Object[] param : lastMonthAllAlertDtls){
					totalAlertLastMonth = totalAlertLastMonth + commonMethodsUtilService.getLongValueForObject(param[2]);
				}
				alertCoreDashBoardVO2.setTotalAlert(totalAlertLastMonth);
			}
			
			
			if(lastMonthAllAlertDtls != null && lastMonthAllAlertDtls.size() > 0){
				tempList = new ArrayList<AlertCoreDashBoardVO>();
				for(Object[] param : lastMonthAllAlertDtls){
					tempVO = new AlertCoreDashBoardVO();
					tempVO.setId(commonMethodsUtilService.getLongValueForObject(param[0]));
					tempVO.setName(commonMethodsUtilService.getStringValueForObject(param[1]));
					tempVO.setCount(commonMethodsUtilService.getLongValueForObject(param[2]));
					tempVO.setCountPerc(calculatePercantage(tempVO.getCount(),totalAlertLastMonth));
					tempList.add(tempVO);
				}
				tempVO.setId(0L);
				tempVO.setName("Last Month Completed");
				tempVO.setCount(lastMonthCompleted);
				tempVO.setCountPerc(calculatePercantage(lastMonthCompleted,totalAlertLastMonth));
				tempList.add(tempVO);
				alertCoreDashBoardVO2.getSubList().addAll(tempList);
			}
			alertCoreDashBoardVOs.add(alertCoreDashBoardVO1);
			alertCoreDashBoardVOs.add(alertCoreDashBoardVO2);
			return alertCoreDashBoardVOs;
		}catch(Exception e){
			LOG.error("Error occured getOverAllAlertsDetails() method of AlertExceptionalReportService{}");
		}
		return null;
	}
	public List<AlertCoreDashBoardVO> getDistrictWiseAlertsDetails(String startDate,String endDate, Long stateId,int size,List<Long> alertTypeIds){
		try{
			
			
			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
			Date stDate = null;
			Date ndDate = null;
			if(startDate != null && startDate.trim().length() == 10 && endDate != null && endDate.trim().length() == 10){
				stDate = sdf.parse(startDate.trim());
				ndDate = sdf.parse(endDate.trim());
			}
			
			List<Object[]> districtWiseAlertCount = alertDAO.getDistrictWiseTotalAlerts(stDate,ndDate,stateId,alertTypeIds);
			//create map for distId and statusId and count map
			Map<Long,Map<Long,Long>> distIdAndStatusIdAndCountMap = new HashMap<Long,Map<Long,Long>>();
			Map<Long,Long> statusIdAndCountMap = null;
			
			Map<Long,String> distIdAndNameMap = new HashMap<Long,String>();
			Map<Long,String> statusIdAndNameMap = new HashMap<Long,String>();
			
			if(districtWiseAlertCount != null && districtWiseAlertCount.size() > 0){
				for(Object[] param : districtWiseAlertCount){
					statusIdAndCountMap = distIdAndStatusIdAndCountMap.get(commonMethodsUtilService.getLongValueForObject(param[0]));
					if(statusIdAndCountMap == null){
						statusIdAndCountMap = new HashMap<Long,Long>();
						distIdAndStatusIdAndCountMap.put(commonMethodsUtilService.getLongValueForObject(param[0]), statusIdAndCountMap);
					}
					statusIdAndCountMap.put(commonMethodsUtilService.getLongValueForObject(param[2]), commonMethodsUtilService.getLongValueForObject(param[4]));
					distIdAndNameMap.put(commonMethodsUtilService.getLongValueForObject(param[0]), commonMethodsUtilService.getStringValueForObject(param[1]));
					statusIdAndNameMap.put(commonMethodsUtilService.getLongValueForObject(param[2]), commonMethodsUtilService.getStringValueForObject(param[3]));
				}
			}
			
			List<AlertCoreDashBoardVO> alertCoreDashBoardVOs = new ArrayList<AlertCoreDashBoardVO>();
			AlertCoreDashBoardVO alertCoreDashBoardVO = null;
			
			
			
			if(distIdAndStatusIdAndCountMap != null && distIdAndStatusIdAndCountMap.size() > 0){
				for(Entry<Long,Map<Long,Long>> outerParam : distIdAndStatusIdAndCountMap.entrySet()){
					alertCoreDashBoardVO = new AlertCoreDashBoardVO();
					alertCoreDashBoardVO.setId(outerParam.getKey());
					alertCoreDashBoardVO.setName(distIdAndNameMap.get(outerParam.getKey()));
					if(outerParam != null && outerParam.getValue() != null && outerParam.getValue().size() > 0){
						Long total = new Long(0L);
						Long pending = 0L;
						Long actionNotRequired = new Long(0L);
						for(Entry<Long,Long> innerParam : outerParam.getValue().entrySet()){
							total = total + commonMethodsUtilService.getLongValueForObject(innerParam.getValue());
							if(innerParam != null && innerParam.getKey() != null && innerParam.getKey().longValue() == 1L){
								pending = innerParam.getKey();
							}
							if(innerParam != null && innerParam.getKey() != null && (innerParam.getKey().longValue() == 6L || innerParam.getKey().longValue() == 7L)){
								actionNotRequired = actionNotRequired + commonMethodsUtilService.getLongValueForObject(innerParam.getValue());
							}
							
						}
						alertCoreDashBoardVO.setTotalAlert(total);
						alertCoreDashBoardVO.setPendingCount(pending);
						alertCoreDashBoardVO.setActionRequired(total-actionNotRequired);
					}
					alertCoreDashBoardVOs.add(alertCoreDashBoardVO);
				}
			}
			Collections.sort(alertCoreDashBoardVOs, new Comparator<AlertCoreDashBoardVO>(){
				@Override
				public int compare(AlertCoreDashBoardVO obj1,AlertCoreDashBoardVO obj2) {
					Long value1 = obj1.getActionRequired();
					Long value2 = obj2.getActionRequired();
					return value2.compareTo(value1);
				}
			});
			return alertCoreDashBoardVOs;
		}catch(Exception e){
			LOG.error("Error occured getOverAllAlertsDetails() method of AlertExceptionalReportService{}");
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
