package com.itgrids.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.itgrids.dao.IAssemblyMlaDAO;
import com.itgrids.dao.IConstituencyDAO;
import com.itgrids.dao.IFundSanctionDAO;
import com.itgrids.dto.InputVO;
import com.itgrids.dto.LocationVO;
import com.itgrids.service.IConstituencyWiseWorkStatusService;
import com.itgrids.utils.CommonMethodsUtilService;

@Service
@Transactional
public class ConstituencyWiseWorkStatusService implements IConstituencyWiseWorkStatusService {
	private static final Logger LOG = Logger.getLogger(ConstituencyWiseWorkStatusService.class);
	@Autowired
	private IConstituencyDAO constituencyDAO;
	@Autowired
	private IFundSanctionDAO fundSanctionDAO;
	@Autowired
	private CommonMethodsUtilService commonMethodsUtilService;
	@Autowired
	private IAssemblyMlaDAO assemblyMlaDAO;
	
	/*
	 * Swadhin K Lenka
	 * @see com.itgrids.service.IConstituencyWiseWorkStatusService#getFundManagementSystemWorkDetails(java.util.List, java.lang.Long, java.lang.String, java.lang.String)
	 */
	public LocationVO getFundManagementSystemWorkDetails(List<Long> financialYearIdsList, Long departmentId, String startDateStr,String endDateStr){
		try{
			LocationVO locationVO = new LocationVO();
			Date startDate = commonMethodsUtilService.stringTODateConvertion(startDateStr,"dd/MM/yyyy","");
			Date endDate = commonMethodsUtilService.stringTODateConvertion(endDateStr,"dd/MM/yyyy","");
			financialYearIdsList = commonMethodsUtilService.makeEmptyListByZeroValue(financialYearIdsList);
			
			List<Object[]> workList = fundSanctionDAO.getFundManagementSystemWorkDetails(financialYearIdsList,departmentId,startDate,endDate);
			//create a map of govtOrderId and govtOrderDtlsVO map
			Map<Long, LocationVO> govtOrderMap = new HashMap<Long,LocationVO>();
			LocationVO  locationVOForMap = null;
			//create a list of workDtlsVO
			List<LocationVO> locationVOs = new ArrayList<LocationVO>();
			LocationVO  locationVOForListForWork = null;
			
			
			if(workList != null && workList.size() > 0){
				for(Object[] param : workList){
					locationVOForMap = govtOrderMap.get(commonMethodsUtilService.getLongValueForObject(param[6]));
					if(null == locationVOForMap){
						locationVOForMap = new LocationVO();
						govtOrderMap.put(commonMethodsUtilService.getLongValueForObject(param[6]),locationVOForMap);
					}
					locationVOForMap.setGovtOrderId(commonMethodsUtilService.getLongValueForObject(param[6]));
					locationVOForMap.setGoNoDate(commonMethodsUtilService.getStringValueForObject(param[9]));
					locationVOForMap.setIssueDate(commonMethodsUtilService.getStringValueForObject(param[10]));
					locationVOForMap.setWorkNumber(locationVOForMap.getWorkNumber() + 1L);
					locationVOForMap.setAmount(locationVOForMap.getAmount() + commonMethodsUtilService.getLongValueForObject(param[11]));
					
					locationVOForListForWork = new LocationVO();
					locationVOForListForWork.setGoNoDate(commonMethodsUtilService.getStringValueForObject(param[9]));
					locationVOForListForWork.setWorkName(commonMethodsUtilService.getStringValueForObject(param[8]));
					locationVOForListForWork.setTehsilName(commonMethodsUtilService.getStringValueForObject(param[3]));
					locationVOForListForWork.setPanchayatName(commonMethodsUtilService.getStringValueForObject(param[5]));
					locationVOForListForWork.setAmount(commonMethodsUtilService.getLongValueForObject(param[11]));
					locationVOs.add(locationVOForListForWork);
				}
				List<LocationVO> locationVOListForGoNo = (List<LocationVO>) govtOrderMap.values();
				locationVO.getLocationList1().addAll(locationVOListForGoNo);
				locationVO.getLocationList2().addAll(locationVOs);
			}
			return locationVO;
		}catch(Exception e){
			LOG.error(" Exception occured in FundManagementDashboardService ,getFundManagementSystemWorkDetails() ",e);
		}
		return null;
	}
	/*
	 * Babu
	 * @see com.itgrids.service.IConstituencyWiseWorkStatusService#getLocationsNamesBySubLocation(com.itgrids.dto.InputVO)
	 */
	public List<LocationVO> getLocationsNamesBySubLocation( InputVO inputVO){
		try{
			List<LocationVO> finalList = new ArrayList<LocationVO>(0);
			List<Object[]> objects=constituencyDAO.getLocationsNamesBySubLocation(inputVO.getLocationId());
			if(objects != null && objects.size() > 0){
				for(Object[] param : objects){
					LocationVO vo = new LocationVO();
					vo.setLocationId(commonMethodsUtilService.getLongValueForObject(param[0]));
					vo.setLocationName(commonMethodsUtilService.getStringValueForObject(param[1]));
					finalList.add(vo);
				}
			}
			return finalList;
		}catch(Exception e){
			LOG.error(" Exception occured in FundManagementDashboardService ,getLocationsNamesBySubLocation() ",e);
		}
		return null;
	}
	public List<LocationVO> getDistrictNameAndMlaNameByConsitutency( InputVO inputVO){
		List<LocationVO> finalList = new ArrayList<LocationVO>(0);
		List<Object[]> objects=assemblyMlaDAO.getAllConstituencyDetails(inputVO.getLocationId());
		if(objects != null && objects.size() > 0){
			for(Object[] param : objects){
				LocationVO vo = new LocationVO();
				vo.setLocationId(commonMethodsUtilService.getLongValueForObject(param[0]));
				vo.setLocationName(commonMethodsUtilService.getStringValueForObject(param[1]));
				vo.setMlaName(commonMethodsUtilService.getStringValueForObject(param[2]));
				vo.setWorkName(commonMethodsUtilService.getStringValueForObject(param[3]));
				finalList.add(vo);
			}
		}
		return finalList;
	}
}
