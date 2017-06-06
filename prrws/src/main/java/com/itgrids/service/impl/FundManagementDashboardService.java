package com.itgrids.service.impl;

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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.itgrids.dao.IFundSanctionDAO;
import com.itgrids.dto.IdNameVO;
import com.itgrids.dto.InputVO;
import com.itgrids.dto.LocationFundDetailsVO;
import com.itgrids.dto.LocationVO;
import com.itgrids.service.IFundManagementDashboardService;
import com.itgrids.utils.CommonMethodsUtilService;
/*
 * Author : Swadhin K Lenka
 * Date : 03/06/2017
 */
@Service
@Transactional
public class FundManagementDashboardService implements IFundManagementDashboardService {
	private static final Logger LOG = Logger.getLogger(FundManagementDashboardService.class);
	
	@Autowired
	private IFundSanctionDAO fundSanctionDAO;
	private CommonMethodsUtilService commonMethodsUtilService = new CommonMethodsUtilService();

	/*
	 * Date : 05/06/2017
	 * Author :Swadhin K Lenka
	 */
	public List<LocationVO> getLocationLevelInfo(){
		try{
			
		}catch(Exception e){
			e.printStackTrace();
			LOG.error("Exception Occurred in getLocationLevelInfo() of FundManagementDashboardService ", e);
			return null;
		}
		return null;
	}
	/*
	 * Date : 05/06/2017
	 * Author :Swadhin K Lenka
	 */
	public List<LocationVO> getLocationWiseAmountDetails(InputVO inputVO){
		try{
			Date fromDate = null;        
			Date toDate = null; 
			List<Long> levelValues = new ArrayList<Long>();
			Long levelId = inputVO.getLevelId();
			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
			if(inputVO.getFromDateStr() != null && inputVO.getFromDateStr().trim().length() > 0 && inputVO.getToDateStr() != null && inputVO.getToDateStr().trim().length() > 0){
				fromDate = sdf.parse(inputVO.getFromDateStr());
				toDate = sdf.parse(inputVO.getToDateStr());
			}
			if(inputVO.getLevelValues() == null || inputVO.getLevelValues().size() == 0){
				levelValues = fundSanctionDAO.getLocationValues(inputVO.getLevelId());
			}else{
				levelValues.addAll(inputVO.getLevelValues());
			}
			List<Object[]> amountList = null;
			List<Object[]> locationInfoList = null;
			if(levelValues != null && levelValues.size() > 0){
				amountList = fundSanctionDAO.getLocationWiseAmount(levelId,levelValues,fromDate,toDate,inputVO.getFinancialYrIdList());
				if(levelId != null && levelId.longValue() > 2L)
				locationInfoList = fundSanctionDAO.getLocationInfo(levelId, levelValues);
			}
			
			//collect all the location ids(user to create the final list)
			List<Long> locationIdList = new ArrayList<Long>();
			if(amountList != null && amountList.size() > 0){
				for(Object[] param : amountList){
					locationIdList.add(commonMethodsUtilService.getLongValueForObject(param[2]));
				}
			}
			
			
			//create a map for locationId and locationName
			Map<Long,String> locationIdAndNameMap = new HashMap<Long,String>();
			if(locationInfoList != null && locationInfoList.size() > 0){
				for(Object[] param : locationInfoList){
					locationIdAndNameMap.put(commonMethodsUtilService.getLongValueForObject(param[0]), commonMethodsUtilService.getStringValueForObject(param[1]));
				}
			}
			if(levelId != null && levelId.longValue() == 2L){
				locationIdAndNameMap.put(1L, "Andhra Pradesh");
			}
			
			//create a map for financialYearId and financialyear
			Map<Long,String> financialYearIdAndFinancialYearMap = new HashMap<Long,String>();
			
			//create a map of financialYearId and map of locationId and amount
			Map<Long,Map<Long,Long>> financialYearIdAndLocationIdAndAmountMap = new LinkedHashMap<Long,Map<Long,Long>>();
			Map<Long,Long> locationIdAndAmountMap = null;
			if(amountList != null && amountList.size() > 0){
				for(Object[] param : amountList){
					financialYearIdAndFinancialYearMap.put(commonMethodsUtilService.getLongValueForObject(param[0]), commonMethodsUtilService.getStringValueForObject(param[1]));
					locationIdAndAmountMap = financialYearIdAndLocationIdAndAmountMap.get(commonMethodsUtilService.getLongValueForObject(param[0]));
					if(locationIdAndAmountMap != null){
						locationIdAndAmountMap.put(commonMethodsUtilService.getLongValueForObject(param[2]), commonMethodsUtilService.getLongValueForObject(param[4]));
					}else{
						locationIdAndAmountMap = new HashMap<Long, Long>();
						locationIdAndAmountMap.put(commonMethodsUtilService.getLongValueForObject(param[2]), commonMethodsUtilService.getLongValueForObject(param[4]));
						financialYearIdAndLocationIdAndAmountMap.put(commonMethodsUtilService.getLongValueForObject(param[0]),locationIdAndAmountMap);
					}
				}
			}
			
			//create a map of financialYearId and map of locationId and count 
			Map<Long,Map<Long,Long>> financialYearIdAndLocationIdAndCountMap = new LinkedHashMap<Long,Map<Long,Long>>();
			Map<Long,Long> locationIdAndCountMap = null;
			if(amountList != null && amountList.size() > 0){
				for(Object[] param : amountList){
					locationIdAndCountMap = financialYearIdAndLocationIdAndCountMap.get(commonMethodsUtilService.getLongValueForObject(param[0]));
					if(locationIdAndCountMap != null){
						locationIdAndCountMap.put(commonMethodsUtilService.getLongValueForObject(param[2]), commonMethodsUtilService.getLongValueForObject(param[3]));
					}else{
						locationIdAndCountMap = new HashMap<Long, Long>();
						locationIdAndCountMap.put(commonMethodsUtilService.getLongValueForObject(param[2]), commonMethodsUtilService.getLongValueForObject(param[3]));
						financialYearIdAndLocationIdAndCountMap.put(commonMethodsUtilService.getLongValueForObject(param[0]),locationIdAndCountMap);
					}
				}
			}
			//Now Logic for final vo
			//iterate locationIdList and prepair final vo
			List<LocationVO> finalList = new ArrayList<LocationVO>();
			LocationVO locationVO = null;
			if(locationIdList != null && locationIdList.size() > 0){
				for(Long locId : locationIdList){
					locationVO = new LocationVO();
					locationVO.setLocationLevelId(levelId);
					locationVO.setLocationId(locId);
					locationVO.setLocationName(commonMethodsUtilService.getStringValueForObject(locationIdAndNameMap.get(locId)));
					//call this method to set the amount and count details.
					pushCountAndAmountDetails(locationVO,financialYearIdAndLocationIdAndAmountMap,financialYearIdAndLocationIdAndCountMap,financialYearIdAndFinancialYearMap);
					finalList.add(locationVO);
				}
			}
			return finalList;
		}catch(Exception e){
			e.printStackTrace();
			LOG.error("Exception Occurred in getLocationWiseAmountDetails() of FundManagementDashboardService ", e);
			return null;
		}
	}
	public void pushCountAndAmountDetails(LocationVO locationVO,Map<Long,Map<Long,Long>> financialYearIdAndLocationIdAndAmountMap,Map<Long,Map<Long,Long>> financialYearIdAndLocationIdAndCountMap,Map<Long,String> financialYearIdAndFinancialYearMap){
		try{
			List<LocationVO> locVoList = null;
			LocationVO locVO = null;
			//push amount and count into vo
			if(financialYearIdAndLocationIdAndAmountMap != null && financialYearIdAndLocationIdAndAmountMap.size() > 0){
				locVoList = new ArrayList<LocationVO>();
				for(Entry<Long,Map<Long,Long>> param : financialYearIdAndLocationIdAndAmountMap.entrySet()){
					//if(param.getValue().containsKey(locationVO.getLocationId())){
						locVO = new LocationVO();
						locVO.setFinancialYearId(param.getKey());
						locVO.setFinancialYear(commonMethodsUtilService.getStringValueForObject(financialYearIdAndFinancialYearMap.get(param.getKey())));
						locVO.setAmount(commonMethodsUtilService.getLongValueForObject(param.getValue().get(locationVO.getLocationId())));
						locVO.setCount(commonMethodsUtilService.getLongValueForObject(financialYearIdAndLocationIdAndCountMap.get(param.getKey()).get(locationVO.getLocationId())));
						locVoList.add(locVO);
					//}
				}
				locationVO.getLocationList1().addAll(locVoList);
			}
		}catch(Exception e){
			e.printStackTrace();
			LOG.error("Exception Occurred in pushCountAndAmountDetails() of FundManagementDashboardService ", e);
		}
	}
	
	/*
	 * Author : Hymavathi G
	 * Date : 05/06/2017
	 * Description : { Location, Scheme & Sourse Wise Funds like Highest & Lowest & Avg Funds Details }
	 */
	public LocationFundDetailsVO getLocationWiseFundDetails(Long financialYrId,Long departmentId,Long sourceId,String startDateStr,String endDateStr,Long locationScopeId,String type ){
		LocationFundDetailsVO returnVO = new LocationFundDetailsVO();
		
		try{
			Date startDate = commonMethodsUtilService.stringTODateConvertion(startDateStr,"dd-MM-yyyy","");
		     Date endDate = commonMethodsUtilService.stringTODateConvertion(endDateStr,"dd-MM-yyyy","");
		     
			Long totalfund = fundSanctionDAO.getTotalFund(financialYrId,departmentId,sourceId,startDate,endDate,null);
			
			List<Object[]> highFund = fundSanctionDAO.getLocationWiseFundHighAndLow(financialYrId,departmentId,sourceId,startDate,endDate,locationScopeId,type);
			if(highFund != null && highFund.size() >0){
				setFundDetails(highFund,returnVO,type,totalfund);
			}
			
			if(returnVO.getId() != null && returnVO.getId().longValue() >0l){
				List<Object[]> locWiseGrantTypes = fundSanctionDAO.getLocationWiseGrantTypesFund(financialYrId,departmentId,sourceId,startDate,endDate,locationScopeId,returnVO.getId());
				setGrantTypesToVO(locWiseGrantTypes,returnVO);
			}
			
		}catch(Exception e){
			LOG.error(" Exception raised in getLocationWiseFundDetails (); ");
		}
		return returnVO;
	}
	
	public void setFundDetails(List<Object[]> list ,LocationFundDetailsVO returnVO,String type,Long totalfund){
		
		try{
			
			Object[] obj =list.get(0);
				returnVO.setTotalAmt(obj[0] != null ? Double.valueOf(obj[0].toString()) : 0l);
				returnVO.setId(obj[1] != null ? (Long)obj[1] : 0l);
				returnVO.setName(obj[2] != null ? (String)obj[2] : "");
				returnVO.setType(type);
				if(totalfund != null && totalfund.longValue() >0l)
				returnVO.setPerc(calculatePercantage(returnVO.getTotalAmt().longValue(),totalfund));
			
			
		}catch(Exception e){
			e.printStackTrace();
			LOG.error(" Exception raised in setFundDetails (); ");
		}
	}
	
	public Double calculatePercantage(Long subCount,Long totalCount){
	    Double d=0.0d;
	    if(subCount.longValue()>0l && totalCount.longValue()==0l)
	      LOG.error("Sub Count Value is "+subCount+" And Total Count Value  "+totalCount+" .So, Unable to calculate percentage.");

	    if(totalCount.longValue() > 0l){
	       d = new BigDecimal(subCount * 100.0/totalCount).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();   
	    }
	    return d;
	  }
	
public void setGrantTypesToVO(List<Object[]> list ,LocationFundDetailsVO returnVO){
		
		try{
			if(list != null && list.size()>0){
				Object[] obj =list.get(0);
					IdNameVO grantVO = new IdNameVO();
					grantVO.setTotal(obj[0] != null ? Double.valueOf(obj[0].toString()) : 0l);
					grantVO.setId(obj[1] != null ? (Long)obj[1] : 0l);
					grantVO.setName(obj[2] != null ? (String)obj[2] : "");
					
					if(grantVO.getTotal() != null && grantVO.getTotal().longValue() >0l)
					returnVO.setPerc(calculatePercantage(grantVO.getTotal().longValue(),returnVO.getTotalAmt().longValue()));
					
					returnVO.getSubList().add(grantVO);
				
			}
			
		}catch(Exception e){
			e.printStackTrace();
			LOG.error(" Exception raised in setGrantTypesToVO (); ");
		}
	}
	

/*
 * Author : Hymavathi G
 * Date : 06/06/2017
 * Description : { Total Funds Allocating }
 */
public LocationFundDetailsVO getTotalFunds(Long financialYrId,Long departmentId,Long sourceId,String startDateStr,String endDateStr){
	LocationFundDetailsVO retusnVo =new LocationFundDetailsVO();
	//SimpleDateFormat sf = new SimpleDateFormat("dd/MM/yyyy");
	try{
		Date startDate = commonMethodsUtilService.stringTODateConvertion(startDateStr,"dd-MM-yyyy","");
	     Date endDate = commonMethodsUtilService.stringTODateConvertion(endDateStr,"dd-MM-yyyy","");
		Long totalfund = fundSanctionDAO.getTotalFund(financialYrId,departmentId,sourceId,startDate,endDate,null);
		if(totalfund != null && totalfund.longValue() > 0l){
			retusnVo.setTotalAmt(Double.valueOf(totalfund.toString()));
		}
		if(retusnVo.getTotalAmt() != null && retusnVo.getTotalAmt().longValue() >0l){
			List<Object[]> locWiseGrantTypes = fundSanctionDAO.getLocationWiseGrantTypesFund(financialYrId,departmentId,sourceId,startDate,endDate,null,null);
			setGrantTypesToVO(locWiseGrantTypes,retusnVo);
		}
	}catch(Exception e){
		e.printStackTrace();
		LOG.error(" Exception raised in getTotalFunds (); ");
	}
	return retusnVo;
}


/*
 * Author : Hymavathi G
 * Date : 06/06/2017
 * Description : { Getting locations count By Sending scopeId }
 */
public LocationFundDetailsVO getTotalLocationsByScopeId(Long financialYrId,Long departmentId,Long sourceId,String startDateStr,String endDateStr,Long locationScopeId){
	LocationFundDetailsVO retusnVo =new LocationFundDetailsVO();
	//SimpleDateFormat sf = new SimpleDateFormat("dd/MM/yyyy");
	try{
		
		Date startDate = commonMethodsUtilService.stringTODateConvertion(startDateStr,"dd-MM-yyyy","");
	     Date endDate = commonMethodsUtilService.stringTODateConvertion(endDateStr,"dd-MM-yyyy","");
		
		 List<Object[]>  locations= fundSanctionDAO.getLocationsCountDetails(locationScopeId,financialYrId);
		 
		 Long totalLocations=0l;
		 
		 if(locationScopeId == 4l){
			 totalLocations = 175l;
		 }else  if(locationScopeId == 5l){
			 totalLocations = 700l;
		 } if(locationScopeId == 6l){
			 totalLocations = 7000l;
		 } 
		 if(commonMethodsUtilService.isListOrSetValid(locations)){
			 for(Object[] obj :locations){
				 retusnVo.setId(commonMethodsUtilService.getLongValueForObject(obj[0]));
				 retusnVo.setFundedLoc(commonMethodsUtilService.getLongValueForObject(obj[1]));
				 Long nonFunded = totalLocations-retusnVo.getFundedLoc();
				 retusnVo.setNotFundedLoc(nonFunded.longValue());
				 retusnVo.setFundedPerc(calculatePercantage(retusnVo.getFundedLoc(),totalLocations));
				 retusnVo.setNonFundedPerc(calculatePercantage(retusnVo.getNotFundedLoc(),totalLocations));
			 }
		 }
		 
	}catch(Exception e){
		e.printStackTrace();
		LOG.error(" Exception raised in getTotalFunds (); ");
	}
	return retusnVo;
}

/*
 * Author : Hymavathi G
 * Date : 06/06/2017
 */
public LocationFundDetailsVO getSchemeWiseHighestAndLowestFund(Long financialYrId,Long departmentId,Long sourceId,String startDateStr,String endDateStr,String type ){
	LocationFundDetailsVO returnVO = new LocationFundDetailsVO();
	//SimpleDateFormat sf = new SimpleDateFormat("dd/MM/yyyy");
	try{
		Date startDate = commonMethodsUtilService.stringTODateConvertion(startDateStr,"dd-MM-yyyy","");
	     Date endDate = commonMethodsUtilService.stringTODateConvertion(endDateStr,"dd-MM-yyyy","");
	     
		Long totalfund = fundSanctionDAO.getTotalFund(financialYrId,departmentId,sourceId,startDate,endDate,null);
		List<Object[]> schemeFund = fundSanctionDAO.getSchemeWiseFundHighAndLow(financialYrId,departmentId,sourceId,startDate,endDate,type);
		if(schemeFund != null && schemeFund.size() >0){
			setFundDetails(schemeFund,returnVO,type,totalfund);
		}
		
	}catch(Exception e){
		LOG.error(" Exception raised in getSchemeWiseHighestAndLowestFund (); ");
	}
	return returnVO;
}

/*
 * Author : Hymavathi G
 * Date : 06/06/2017
 */
public LocationFundDetailsVO getTotalSchemes(Long financialYrId,Long departmentId,Long sourceId,String startDateStr,String endDateStr){
	LocationFundDetailsVO retusnVo =new LocationFundDetailsVO();
	//SimpleDateFormat sf = new SimpleDateFormat("dd/MM/yyyy");
	try{
		
		Date startDate = commonMethodsUtilService.stringTODateConvertion(startDateStr,"dd-MM-yyyy","");
	     Date endDate = commonMethodsUtilService.stringTODateConvertion(endDateStr,"dd-MM-yyyy","");
		Long totalSchemes = fundSanctionDAO.getTotalSchemes(financialYrId, departmentId, sourceId, startDate, endDate);
		 
		 if(totalSchemes != null && totalSchemes.longValue() >0l){
			 retusnVo.setTotSchemes(totalSchemes);
		 }
	}catch(Exception e){
		e.printStackTrace();
		LOG.error(" Exception raised in getTotalFunds (); ");
	}
	return retusnVo;
}
}
