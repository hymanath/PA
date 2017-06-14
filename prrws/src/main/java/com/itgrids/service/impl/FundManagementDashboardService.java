package com.itgrids.service.impl;

import java.text.DecimalFormat;
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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.itgrids.dao.IConstituencyDAO;
import com.itgrids.dao.IDepartmentDAO;
import com.itgrids.dao.IDistrictDAO;
import com.itgrids.dao.IFinancialYearDAO;
import com.itgrids.dao.IFundSanctionDAO;
import com.itgrids.dao.IFundSanctionMatrixDetailsDAO;
import com.itgrids.dao.IFundSanctionMatrixRangeDAO;
import com.itgrids.dao.IGrantTypeDAO;
import com.itgrids.dto.AddressVO;
import com.itgrids.dto.FundMatrixVO;
import com.itgrids.dto.FundSchemeVO;
import com.itgrids.dto.IdNameVO;
import com.itgrids.dto.InputVO;
import com.itgrids.dto.LocationFundDetailsVO;
import com.itgrids.dto.LocationVO;
import com.itgrids.dto.RangeVO;
import com.itgrids.service.IFundManagementDashboardService;
import com.itgrids.utils.CommonMethodsUtilService;
import com.itgrids.utils.IConstants;
import com.itgrids.utils.SetterAndGetterUtilService;
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
	@Autowired
	private IDistrictDAO districtDAO;
	@Autowired
	private IConstituencyDAO constituencyDAO;
	@Autowired
	private CommonMethodsUtilService commonMethodsUtilService;
	@Autowired
	private IGrantTypeDAO grantTypeDAO;
	@Autowired
	private SetterAndGetterUtilService setterAndGetterUtilService;
	@Autowired
	private IFinancialYearDAO financialYearDAO;
	@Autowired
	private IDepartmentDAO departmentDAO;
	@Autowired
	private IFundSanctionMatrixRangeDAO fundSanctionMatrixRangeDAO;
	@Autowired
	private IFundSanctionMatrixDetailsDAO fundSanctionMatrixDetailsDAO;

	@Override
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
	 * Date : 06/06/2017
	 * Author :Srishailam Pittala
	 * @description : to get scheme wise funds transaction details
	 */
	
	public List<FundSchemeVO> getFinancialYearWiseSchemeDetails(List<Long> financialYearIdsList,List<Long> deptIdsList,
			List<Long> sourceIdsList,List<Long> schemeIdsList,String startDateStr,String endDateStr,Long searchScopeId,List<Long> searchScopeValuesList,String order,String sortingType,Long searchLevelId){
		List<FundSchemeVO> returnList = new ArrayList<FundSchemeVO>(0);
		try {
			Date startDate = commonMethodsUtilService.stringTODateConvertion(startDateStr,"MM/dd/yyyy","");
			Date endDate = commonMethodsUtilService.stringTODateConvertion(endDateStr,"MM/dd/yyyy","");
			
			financialYearIdsList = commonMethodsUtilService.makeEmptyListByZeroValue(financialYearIdsList);
			deptIdsList = commonMethodsUtilService.makeEmptyListByZeroValue(deptIdsList);
			sourceIdsList = commonMethodsUtilService.makeEmptyListByZeroValue(sourceIdsList);
			schemeIdsList = commonMethodsUtilService.makeEmptyListByZeroValue(schemeIdsList);
			searchScopeValuesList = commonMethodsUtilService.makeEmptyListByZeroValue(searchScopeValuesList);
			
			List<Object[]> result =  fundSanctionDAO.getFinancialYearWiseScheameDetails(financialYearIdsList,deptIdsList,sourceIdsList, schemeIdsList,startDate,endDate,searchScopeId,searchScopeValuesList,searchLevelId);
			Map<Long,FundSchemeVO> locationMap = new HashMap<Long,FundSchemeVO>(0);
			Map<Long,FundSchemeVO> yearsMap = new HashMap<Long,FundSchemeVO>(0);
			//Map<Long,FundSchemeVO> deptsMap = new HashMap<Long,FundSchemeVO>(0);
			Map<Long,FundSchemeVO> schemesMap = new HashMap<Long,FundSchemeVO>(0);
			
			if(commonMethodsUtilService.isListOrSetValid(result)){
				
				for (Object[] param : result) {
					
					if(yearsMap.get(commonMethodsUtilService.getLongValueForObject(param[2])) == null){
						FundSchemeVO yearVO = new FundSchemeVO();
						yearVO.setYearId(commonMethodsUtilService.getLongValueForObject(param[2]));
						yearVO.setYear(commonMethodsUtilService.getStringValueForObject(param[3]));
						
						yearsMap.put(yearVO.getYearId(), yearVO);
					}
					
					if(schemesMap.get(commonMethodsUtilService.getLongValueForObject(param[0])) == null){
						FundSchemeVO schemeVO = new FundSchemeVO();					
						schemeVO.setId(commonMethodsUtilService.getLongValueForObject(param[0]));
						schemeVO.setName(commonMethodsUtilService.getStringValueForObject(param[1]));
						schemeVO.setCount(0L);
						schemeVO.setTotalCount(0L);
						schemesMap.put(schemeVO.getId(), schemeVO);
					}
				}

				for (Object[] param : result) {
					FundSchemeVO fundLocationVO = new FundSchemeVO();
					
					AddressVO addressVO = new AddressVO();
					if(searchScopeId != null && searchScopeId.longValue() == IConstants.STATE_LEVEL_SCOPE_ID){
						addressVO.setStateId(commonMethodsUtilService.getLongValueForObject(param[6]));
						addressVO.setStateName(commonMethodsUtilService.getStringValueForObject(param[7]));
						
						addressVO.setId(addressVO.getStateId());
						addressVO.setName(addressVO.getStateName());
					}else if(searchScopeId != null && searchScopeId.longValue() == IConstants.DISTRICT_LEVEL_SCOPE_ID){
						addressVO.setDistrictId(commonMethodsUtilService.getLongValueForObject(param[6]));
						addressVO.setDistrictName(commonMethodsUtilService.getStringValueForObject(param[7]));
						
						addressVO.setId(addressVO.getDistrictId());
						addressVO.setName(addressVO.getDistrictName());
					}else if(searchScopeId != null && searchScopeId.longValue() == IConstants.CONSTITUENCY_LEVEL_SCOPE_ID){
						addressVO.setAssemblyId(commonMethodsUtilService.getLongValueForObject(param[6]));
						addressVO.setAssemblyName(commonMethodsUtilService.getStringValueForObject(param[7]));
						
						addressVO.setId(addressVO.getAssemblyId());
						addressVO.setName(addressVO.getAssemblyName());
					}
					else if(searchScopeId != null && searchScopeId.longValue() == IConstants.MANDAL_LEVEL_SCOPE_ID){
						addressVO.setTehsilId(commonMethodsUtilService.getLongValueForObject(param[6]));
						addressVO.setTehsilName(commonMethodsUtilService.getStringValueForObject(param[7]));
						
						addressVO.setId(addressVO.getTehsilId());
						addressVO.setName(addressVO.getTehsilName());
					}
					else if(searchScopeId != null && searchScopeId.longValue() == IConstants.VILLAGE_LEVEL_SCOPE_ID){
						addressVO.setPanchayatId(commonMethodsUtilService.getLongValueForObject(param[6]));
						addressVO.setPanchayatName(commonMethodsUtilService.getStringValueForObject(param[7]));
						
						addressVO.setId(addressVO.getAssemblyId());
						addressVO.setName(addressVO.getAssemblyName());
					}
					
					addressVO.setTehsilId(0L);
					addressVO.setTehsilName("");
					addressVO.setLocalElectionBodyId(0L);
					addressVO.setLocalElectionBodyName("");
					
					Long keyId=0L;
					if(searchScopeId != null){
						if(searchScopeId.longValue() ==IConstants.STATE_LEVEL_SCOPE_ID)
							keyId = 1L;
						else if(searchScopeId.longValue() ==IConstants.DISTRICT_LEVEL_SCOPE_ID)
							keyId = addressVO.getDistrictId();
						else if(searchScopeId.longValue() ==IConstants.CONSTITUENCY_LEVEL_SCOPE_ID)
							keyId = addressVO.getAssemblyId();
						else if(searchScopeId.longValue() ==IConstants.MANDAL_LEVEL_SCOPE_ID)
							keyId = addressVO.getTehsilId();
						else if(searchScopeId.longValue() ==IConstants.VILLAGE_LEVEL_SCOPE_ID)
							keyId = addressVO.getPanchayatId();
					}
					
					if(locationMap.get(keyId) != null){
						fundLocationVO = locationMap.get(keyId);
					}else{
						List<FundSchemeVO> yearsList = buildData(yearsMap,schemesMap,null);
						fundLocationVO.setSubList(yearsList);;
					}
					
					for (FundSchemeVO yearVO : fundLocationVO.getSubList()){
						yearVO.setAddressVO(addressVO);
					}
					
					FundSchemeVO yearVO = (FundSchemeVO) setterAndGetterUtilService.getMatchedVOfromList(fundLocationVO.getSubList(), "yearId", commonMethodsUtilService.getLongValueForObject(param[2]).toString());
					if(yearVO == null){
						yearVO = new FundSchemeVO();
						yearVO.setYearId(commonMethodsUtilService.getLongValueForObject(param[2]));
						yearVO.setYear(commonMethodsUtilService.getStringValueForObject(param[3]));
						
						FundSchemeVO schemeVO = new FundSchemeVO();					
							schemeVO.setId(commonMethodsUtilService.getLongValueForObject(param[0]));
							schemeVO.setName(commonMethodsUtilService.getStringValueForObject(param[1]));
							schemeVO.setCount(commonMethodsUtilService.getLongValueForObject(param[4]));
							schemeVO.setTotalCount(commonMethodsUtilService.getLongValueForObject(param[5]));
							
						schemeVO.setAddressVO(addressVO);							
						yearVO.setAddressVO(addressVO);
						yearVO.getSubList().add(schemeVO);
						fundLocationVO.getSubList().add(yearVO);
						
					}else{
						FundSchemeVO schemeVO = (FundSchemeVO) setterAndGetterUtilService.getMatchedVOfromList(yearVO.getSubList(), "id", commonMethodsUtilService.getLongValueForObject(param[0]).toString());
						if(schemeVO == null){
							schemeVO = new FundSchemeVO();					
							schemeVO.setId(commonMethodsUtilService.getLongValueForObject(param[0]));
							schemeVO.setName(commonMethodsUtilService.getStringValueForObject(param[1]));
							schemeVO.setCount(commonMethodsUtilService.getLongValueForObject(param[4]));
							schemeVO.setTotalCount(commonMethodsUtilService.getLongValueForObject(param[5]));
							
							schemeVO.setAddressVO(addressVO);
							yearVO.setAddressVO(addressVO);							
							yearVO.getSubList().add(schemeVO);
						}
						else{
							schemeVO.setCount(schemeVO.getCount()+commonMethodsUtilService.getLongValueForObject(param[4]));
							schemeVO.setTotalCount(schemeVO.getTotalCount()+commonMethodsUtilService.getLongValueForObject(param[5]));
							
							schemeVO.setAddressVO(addressVO);
							yearVO.setAddressVO(addressVO);
						}
					}
					fundLocationVO.setAddressVO(addressVO);
					locationMap.put(keyId, fundLocationVO);
				}
			}
			
			if(commonMethodsUtilService.isMapValid(locationMap)){
				returnList.addAll(locationMap.values());
				
				if(returnList != null && returnList.size() > 0){
					if(sortingType.trim().equalsIgnoreCase("name")){
						if(order.trim().equalsIgnoreCase("asc")){
							Collections.sort(returnList, nameWiseAscendingOrder);
						}else{
							Collections.sort(returnList, nameWiseDescendingOrder);
						}
					}/*else if(sortingType.trim().equalsIgnoreCase("count")){
						if(order.trim().equalsIgnoreCase("asc")){
							Collections.sort(returnList, amountWiseAscendingOrder);
						}else{
							Collections.sort(returnList, amountWiseDescendingOrder);
						}
					}*/
				}
			}
			
		} catch (Exception e) {
			LOG.error(" Exception occured in FundManagementDashboardService ,getFinancialYearWiseScheameDetails() ",e);
		}
		return returnList;
	}
	
	
	public static Comparator<FundSchemeVO> nameWiseAscendingOrder = new Comparator<FundSchemeVO>() {
    	public int compare(FundSchemeVO o1, FundSchemeVO o2) {
    		//descending order of percantages.
    		return o1.getAddressVO().getName().compareTo(o2.getAddressVO().getName());
    	}
	};
	
	public static Comparator<FundSchemeVO> nameWiseDescendingOrder = new Comparator<FundSchemeVO>() {
    	public int compare(FundSchemeVO o1, FundSchemeVO o2) {
    		return o2.getAddressVO().getName().compareTo(o1.getAddressVO().getName());
    	}
	};
	
	
	public static Comparator<FundSchemeVO> amountWiseAscendingOrder = new Comparator<FundSchemeVO>() {
		public int compare(FundSchemeVO location2, FundSchemeVO location1) {
			return 0;
		}
	};
	
	public static Comparator<FundSchemeVO> amountWiseDescendingOrder = new Comparator<FundSchemeVO>() {
      @Override
		public int compare(FundSchemeVO o1, FundSchemeVO o2) {
			// TODO Auto-generated method stub
			return 0;
		}
	};
	
	/*
	 * Date : 08/06/2017
	 * Author :Srishailam Pittala
	 * @description : to get financial year ,  Department wise and scheme wise funds transaction details
	 */
	
	public List<FundSchemeVO> getFinancialYearWiseDeptsWiseSchemeDetails(List<Long> financialYearIdsList,List<Long> deptIdsList,
			List<Long> sourceIdsList,List<Long> schemeIdsList,String startDateStr,String endDateStr,Long searchScopeId,List<Long> searchScopeValuesList, String order, String sortingType,Long searchLevelId){
		List<FundSchemeVO> returnList = new ArrayList<FundSchemeVO>(0);
		try {
			Date startDate = commonMethodsUtilService.stringTODateConvertion(startDateStr,"MM/dd/yyyy","");
			Date endDate = commonMethodsUtilService.stringTODateConvertion(endDateStr,"MM/dd/yyyy","");
			
			financialYearIdsList = commonMethodsUtilService.makeEmptyListByZeroValue(financialYearIdsList);
			deptIdsList = commonMethodsUtilService.makeEmptyListByZeroValue(deptIdsList);
			sourceIdsList = commonMethodsUtilService.makeEmptyListByZeroValue(sourceIdsList);
			schemeIdsList = commonMethodsUtilService.makeEmptyListByZeroValue(schemeIdsList);
			searchScopeValuesList = commonMethodsUtilService.makeEmptyListByZeroValue(searchScopeValuesList);
			
			List<Object[]> result =  fundSanctionDAO.getFinancialYearWiseDeptsWiseScheameDetails(financialYearIdsList,deptIdsList,sourceIdsList, schemeIdsList,startDate,endDate,searchScopeId,searchScopeValuesList,searchLevelId);
			Map<Long,FundSchemeVO> locationMap = new HashMap<Long,FundSchemeVO>(0);
			Map<Long,FundSchemeVO> yearsMap = new HashMap<Long,FundSchemeVO>(0);
			Map<Long,FundSchemeVO> deptsMap = new HashMap<Long,FundSchemeVO>(0);
			Map<Long,FundSchemeVO> schemesMap = new HashMap<Long,FundSchemeVO>(0);
			
			if(commonMethodsUtilService.isListOrSetValid(result)){
				for (Object[] param : result) {
					
					if(yearsMap.get(commonMethodsUtilService.getLongValueForObject(param[2])) == null){
						FundSchemeVO yearVO = new FundSchemeVO();
						yearVO.setYearId(commonMethodsUtilService.getLongValueForObject(param[2]));
						yearVO.setYear(commonMethodsUtilService.getStringValueForObject(param[3]));
						
						yearsMap.put(yearVO.getYearId(), yearVO);
					}
					
					if(deptsMap.get(commonMethodsUtilService.getLongValueForObject(param[8])) == null){
						FundSchemeVO deptsVO = new FundSchemeVO();					
						deptsVO.setId(commonMethodsUtilService.getLongValueForObject(param[8]));
						deptsVO.setName(commonMethodsUtilService.getStringValueForObject(param[9]));
						deptsVO.setYearId(commonMethodsUtilService.getLongValueForObject(param[2]));
						deptsVO.setYear(commonMethodsUtilService.getStringValueForObject(param[3]));
						
						deptsMap.put(deptsVO.getId(), deptsVO);
					}
					
					if(schemesMap.get(commonMethodsUtilService.getLongValueForObject(param[0])) == null){
						FundSchemeVO schemeVO = new FundSchemeVO();					
						schemeVO.setId(commonMethodsUtilService.getLongValueForObject(param[0]));
						schemeVO.setName(commonMethodsUtilService.getStringValueForObject(param[1]));
						schemeVO.setCount(0L);
						schemeVO.setTotalCount(0L);
						schemesMap.put(schemeVO.getId(), schemeVO);
					}
				}
			
				for (Object[] param : result) {
					FundSchemeVO fundLocationVO = new FundSchemeVO();
					
					AddressVO addressVO = new AddressVO();
					if(searchScopeId != null && searchScopeId.longValue() == IConstants.STATE_LEVEL_SCOPE_ID){
						addressVO.setStateId(commonMethodsUtilService.getLongValueForObject(param[6]));
						addressVO.setStateName(commonMethodsUtilService.getStringValueForObject(param[7]));
						
						addressVO.setId(addressVO.getStateId());
						addressVO.setName(addressVO.getStateName());
					}else if(searchScopeId != null && searchScopeId.longValue() == IConstants.DISTRICT_LEVEL_SCOPE_ID){
						addressVO.setDistrictId(commonMethodsUtilService.getLongValueForObject(param[6]));
						addressVO.setDistrictName(commonMethodsUtilService.getStringValueForObject(param[7]));
						
						addressVO.setId(addressVO.getDistrictId());
						addressVO.setName(addressVO.getDistrictName());
					}else if(searchScopeId != null && searchScopeId.longValue() == IConstants.CONSTITUENCY_LEVEL_SCOPE_ID){
						addressVO.setAssemblyId(commonMethodsUtilService.getLongValueForObject(param[6]));
						addressVO.setAssemblyName(commonMethodsUtilService.getStringValueForObject(param[7]));
						
						addressVO.setId(addressVO.getAssemblyId());
						addressVO.setName(addressVO.getAssemblyName());
					}
					else if(searchScopeId != null && searchScopeId.longValue() == IConstants.MANDAL_LEVEL_SCOPE_ID){
						addressVO.setTehsilId(commonMethodsUtilService.getLongValueForObject(param[6]));
						addressVO.setTehsilName(commonMethodsUtilService.getStringValueForObject(param[7]));
						
						addressVO.setId(addressVO.getTehsilId());
						addressVO.setName(addressVO.getTehsilName());
					}
					else if(searchScopeId != null && searchScopeId.longValue() == IConstants.VILLAGE_LEVEL_SCOPE_ID){
						addressVO.setPanchayatId(commonMethodsUtilService.getLongValueForObject(param[6]));
						addressVO.setPanchayatName(commonMethodsUtilService.getStringValueForObject(param[7]));
						
						addressVO.setId(addressVO.getAssemblyId());
						addressVO.setName(addressVO.getAssemblyName());
					}
					
					addressVO.setTehsilId(0L);
					addressVO.setTehsilName("");
					addressVO.setLocalElectionBodyId(0L);
					addressVO.setLocalElectionBodyName("");
					
					Long keyId=0L;
					if(searchScopeId != null){
						if(searchScopeId.longValue() ==IConstants.STATE_LEVEL_SCOPE_ID)
							keyId = 1L;
						else if(searchScopeId.longValue() ==IConstants.DISTRICT_LEVEL_SCOPE_ID)
							keyId = addressVO.getDistrictId();
						else if(searchScopeId.longValue() ==IConstants.CONSTITUENCY_LEVEL_SCOPE_ID)
							keyId = addressVO.getAssemblyId();
						else if(searchScopeId.longValue() ==IConstants.MANDAL_LEVEL_SCOPE_ID)
							keyId = addressVO.getTehsilId();
						else if(searchScopeId.longValue() ==IConstants.VILLAGE_LEVEL_SCOPE_ID)
							keyId = addressVO.getPanchayatId();
					}
					
					if(locationMap.get(keyId) != null){
						fundLocationVO = locationMap.get(keyId);
					}else{
						List<FundSchemeVO> yearsList = buildData(yearsMap,deptsMap,schemesMap);
						fundLocationVO.setSubList(yearsList);;
					}
					
					for (FundSchemeVO yearVO : fundLocationVO.getSubList()){
							yearVO.setAddressVO(addressVO);
						for (FundSchemeVO deptsVO : yearVO.getSubList()){
							deptsVO.setAddressVO(addressVO);
							for (FundSchemeVO schemeVO : deptsVO.getSubList())
								schemeVO.setAddressVO(addressVO);
						}
					}
					
					FundSchemeVO yearVO = (FundSchemeVO) setterAndGetterUtilService.getMatchedVOfromList(fundLocationVO.getSubList(), "yearId", commonMethodsUtilService.getLongValueForObject(param[2]).toString());
					if(yearVO == null){
						yearVO = new FundSchemeVO();
						yearVO.setYearId(commonMethodsUtilService.getLongValueForObject(param[2]));
						yearVO.setYear(commonMethodsUtilService.getStringValueForObject(param[3]));
						yearVO.setAddressVO(addressVO);
						
						FundSchemeVO deptsVO = new FundSchemeVO();					
						deptsVO.setId(commonMethodsUtilService.getLongValueForObject(param[8]));
						deptsVO.setName(commonMethodsUtilService.getStringValueForObject(param[9]));
						deptsVO.setYearId(commonMethodsUtilService.getLongValueForObject(param[2]));
						deptsVO.setYear(commonMethodsUtilService.getStringValueForObject(param[3]));
						deptsVO.setAddressVO(addressVO);
						
						FundSchemeVO schemeVO = new FundSchemeVO();					
						schemeVO.setId(commonMethodsUtilService.getLongValueForObject(param[0]));
						schemeVO.setName(commonMethodsUtilService.getStringValueForObject(param[1]));
						schemeVO.setCount(commonMethodsUtilService.getLongValueForObject(param[4]));
						schemeVO.setTotalCount(commonMethodsUtilService.getLongValueForObject(param[5]));
						schemeVO.setAddressVO(addressVO);
						
						deptsVO.getSubList().add(schemeVO);
						yearVO.getSubList().add(deptsVO);
						fundLocationVO.getSubList().add(yearVO);
					}else{
						FundSchemeVO deptsVO = (FundSchemeVO) setterAndGetterUtilService.getMatchedVOfromList(yearVO.getSubList(), "id", commonMethodsUtilService.getLongValueForObject(param[8]).toString());
						if(deptsVO == null){
							deptsVO = new FundSchemeVO();					
							deptsVO.setId(commonMethodsUtilService.getLongValueForObject(param[8]));
							deptsVO.setName(commonMethodsUtilService.getStringValueForObject(param[9]));
							deptsVO.setYearId(commonMethodsUtilService.getLongValueForObject(param[2]));
							deptsVO.setYear(commonMethodsUtilService.getStringValueForObject(param[3]));
							deptsVO.setAddressVO(addressVO);
							yearVO.setAddressVO(addressVO);
							
							FundSchemeVO schemeVO = new FundSchemeVO();					
							schemeVO.setId(commonMethodsUtilService.getLongValueForObject(param[0]));
							schemeVO.setName(commonMethodsUtilService.getStringValueForObject(param[1]));
							schemeVO.setCount(commonMethodsUtilService.getLongValueForObject(param[4]));
							schemeVO.setTotalCount(commonMethodsUtilService.getLongValueForObject(param[5]));
							schemeVO.setAddressVO(addressVO);
							
							deptsVO.getSubList().add(schemeVO);
							yearVO.getSubList().add(deptsVO);
							
							
						}else{
							FundSchemeVO schemeVO = (FundSchemeVO) setterAndGetterUtilService.getMatchedVOfromList(deptsVO.getSubList(), "id", commonMethodsUtilService.getLongValueForObject(param[0]).toString());
							if(schemeVO == null){
								schemeVO = new FundSchemeVO();					
								schemeVO.setId(commonMethodsUtilService.getLongValueForObject(param[0]));
								schemeVO.setName(commonMethodsUtilService.getStringValueForObject(param[1]));
								schemeVO.setCount(commonMethodsUtilService.getLongValueForObject(param[4]));
								schemeVO.setTotalCount(commonMethodsUtilService.getLongValueForObject(param[5]));
								schemeVO.setAddressVO(addressVO);
								yearVO.setAddressVO(addressVO);
								deptsVO.setAddressVO(addressVO);
								
								deptsVO.getSubList().add(schemeVO);
								
							}
							else{
								
								schemeVO.setCount(schemeVO.getCount()+commonMethodsUtilService.getLongValueForObject(param[4]));
								schemeVO.setTotalCount(schemeVO.getTotalCount()+commonMethodsUtilService.getLongValueForObject(param[5]));
								yearVO.setAddressVO(addressVO);
							}
							yearVO.setAddressVO(addressVO);
							deptsVO.setAddressVO(addressVO);
						}
					}
					
					fundLocationVO.setAddressVO(addressVO);
					locationMap.put(keyId, fundLocationVO);
				}
			}
			
			if(commonMethodsUtilService.isMapValid(locationMap)){
				returnList.addAll(locationMap.values());
				
				if(returnList != null && returnList.size() > 0){
					if(sortingType.trim().equalsIgnoreCase("name")){
						if(order.trim().equalsIgnoreCase("asc")){
							Collections.sort(returnList, nameWiseAscendingOrder);
						}else{
							Collections.sort(returnList, nameWiseDescendingOrder);
						}
					}/*else if(sortingType.trim().equalsIgnoreCase("count")){
						if(order.trim().equalsIgnoreCase("asc")){
							Collections.sort(returnList, amountWiseAscendingOrder);
						}else{
							Collections.sort(returnList, amountWiseDescendingOrder);
						}
					}*/
				}
			}
			
		} catch (Exception e) {
			LOG.error(" Exception occured in FundManagementDashboardService ,getFinancialYearWiseDeptsWiseSchemeDetails() ",e);
		}
		return returnList;
	}
	
	public List<FundSchemeVO> buildData(Map<Long,FundSchemeVO> YearDataMap,Map<Long,FundSchemeVO> deptsDataMap,Map<Long,FundSchemeVO> schemeDataMap){
		List<FundSchemeVO> returnList = new ArrayList<FundSchemeVO>(0);
		try {
			if(commonMethodsUtilService.isMapValid(YearDataMap)){
				for (Long yearId : YearDataMap.keySet()) {
					FundSchemeVO tempVO = YearDataMap.get(yearId);

					FundSchemeVO yearVO = new FundSchemeVO();					
					yearVO.setId(tempVO.getId());
					yearVO.setName(tempVO.getName());
					yearVO.setYearId(tempVO.getYearId());
					yearVO.setYear(tempVO.getYear());
					yearVO.setCount(tempVO.getCount());
					yearVO.setTotalCount(tempVO.getTotalCount());
					
					if(commonMethodsUtilService.isMapValid(deptsDataMap)){
						List<FundSchemeVO> deptList = new ArrayList<FundSchemeVO>(0);
						for (Long deptId : deptsDataMap.keySet()) {
							
							FundSchemeVO tempVO1 = deptsDataMap.get(deptId);

							FundSchemeVO deptVO = new FundSchemeVO();					
							deptVO.setId(tempVO1.getId());
							deptVO.setName(tempVO1.getName());
							deptVO.setYearId(tempVO1.getYearId());
							deptVO.setYear(tempVO1.getYear());
							deptVO.setCount(tempVO1.getCount());
							deptVO.setTotalCount(tempVO1.getTotalCount());
							
							if(commonMethodsUtilService.isMapValid(schemeDataMap)){
								List<FundSchemeVO> schemeList = new ArrayList<FundSchemeVO>(0);
								for (Long schemeId : schemeDataMap.keySet()) {
									FundSchemeVO tempVO2 = schemeDataMap.get(schemeId);

									FundSchemeVO schemeVO = new FundSchemeVO();					
									schemeVO.setId(tempVO2.getId());
									schemeVO.setName(tempVO2.getName());
									schemeVO.setYearId(tempVO2.getYearId());
									schemeVO.setYear(tempVO2.getYear());
									schemeVO.setCount(tempVO2.getCount());
									schemeVO.setTotalCount(tempVO2.getTotalCount());
									
									schemeList.add(schemeVO);
									deptVO.setSubList(schemeList);
								}
							}
							deptList.add(deptVO);
							yearVO.setSubList(deptList);
						}
					}
					returnList.add(yearVO);
				}
			}
		} catch (Exception e) {
			LOG.error(" Exception occured in FundManagementDashboardService ,buildData() ",e);
		}
		return returnList;
	}
	
	
	/*
	 * Date : 05/06/2017
	 * Author :Swadhin K Lenka
	 */
	@Override
	public List<LocationVO> getLocationWiseAmountDetails(InputVO inputVO){
		try{
			
			List<Long> levelValues = new ArrayList<Long>();
			Long levelId = inputVO.getBlockLevelId();
			
			Date fromDate = commonMethodsUtilService.stringTODateConvertion(inputVO.getFromDateStr(),"MM/dd/yyyy","");
			Date toDate = commonMethodsUtilService.stringTODateConvertion(inputVO.getToDateStr(),"MM/dd/yyyy","");
			Long locationId = null; 
			Long locationLevelId = null;
			if((inputVO.getLocationId() != null && inputVO.getLocationId().longValue() > 0L)){
				String locationIdStr = inputVO.getLocationId().toString();
				String locationLevelIdStr = locationIdStr.substring(0, 1);
				locationIdStr = locationIdStr.substring(1);
				locationId = Long.parseLong(locationIdStr);
				locationLevelId = Long.parseLong(locationLevelIdStr);
			}
			List<Object[]> amountList = null;
			
			List<Long> financialYearIdsList = commonMethodsUtilService.makeEmptyListByZeroValue(inputVO.getFinancialYrIdList());
			List<Long> deptIdsList = commonMethodsUtilService.makeEmptyListByZeroValue(inputVO.getDeptIdsList());
			List<Long> sourceIdsList = commonMethodsUtilService.makeEmptyListByZeroValue(inputVO.getSourceIdsList());
			
			
			amountList = fundSanctionDAO.getLocationWiseAmount(levelId,locationId,locationLevelId,fromDate,toDate,financialYearIdsList,deptIdsList,sourceIdsList);
		
			
			//collect all the location ids(uses to create the final list)
			Set<Long> locationIdList = new HashSet<Long>();
			if(amountList != null && amountList.size() > 0){
				for(Object[] param : amountList){
					locationIdList.add(commonMethodsUtilService.getLongValueForObject(param[2]));
				}
			}
			
			//create a map for financialYearId and financialyear
			Map<Long,String> financialYearIdAndFinancialYearMap = new HashMap<Long,String>();
			List<Object[]> financialYearList = financialYearDAO.getAllFiniancialYearsByIds(inputVO.getFinancialYrIdList());
			if(financialYearList != null && financialYearList.size() > 0){
				for(Object[] param : financialYearList){
					financialYearIdAndFinancialYearMap.put(commonMethodsUtilService.getLongValueForObject(param[0]), commonMethodsUtilService.getStringValueForObject(param[1]));
				}
			}
			
			//create a map for locationId and locationName
			Map<Long,String> locationIdAndNameMap = new HashMap<Long,String>();
			
			//create a map of financialYearId and map of locationId and amount
			Map<Long,Map<Long,Long>> financialYearIdAndLocationIdAndAmountMap = new LinkedHashMap<Long,Map<Long,Long>>();
			Map<Long,Long> locationIdAndAmountMap = null;
			if(amountList != null && amountList.size() > 0){
				for(Object[] param : amountList){
					locationIdAndNameMap.put(commonMethodsUtilService.getLongValueForObject(param[2]), commonMethodsUtilService.getStringValueForObject(param[3]));
					financialYearIdAndFinancialYearMap.put(commonMethodsUtilService.getLongValueForObject(param[0]), commonMethodsUtilService.getStringValueForObject(param[1]));
					locationIdAndAmountMap = financialYearIdAndLocationIdAndAmountMap.get(commonMethodsUtilService.getLongValueForObject(param[0]));
					if(locationIdAndAmountMap != null){
						locationIdAndAmountMap.put(commonMethodsUtilService.getLongValueForObject(param[2]), commonMethodsUtilService.getLongValueForObject(param[5]));
					}else{
						locationIdAndAmountMap = new HashMap<Long, Long>();
						locationIdAndAmountMap.put(commonMethodsUtilService.getLongValueForObject(param[2]), commonMethodsUtilService.getLongValueForObject(param[5]));
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
						locationIdAndCountMap.put(commonMethodsUtilService.getLongValueForObject(param[2]), commonMethodsUtilService.getLongValueForObject(param[4]));
					}else{
						locationIdAndCountMap = new HashMap<Long, Long>();
						locationIdAndCountMap.put(commonMethodsUtilService.getLongValueForObject(param[2]), commonMethodsUtilService.getLongValueForObject(param[4]));
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
			if(finalList != null && finalList.size() > 0){
				if(inputVO.getSortingType().trim().equalsIgnoreCase("name")){
					if(inputVO.getOrder().trim().equalsIgnoreCase("asc")){
						Collections.sort(finalList, nameWiseAscOrder);
					}else{
						Collections.sort(finalList, nameWiseDescOrder);
					}
				}else if(inputVO.getSortingType().trim().equalsIgnoreCase("count")){
					if(inputVO.getOrder().trim().equalsIgnoreCase("asc")){
						Collections.sort(finalList, amountWiseAscOrder);
					}else{
						Collections.sort(finalList, amountWiseDescOrder);
					}
				}
			}
			
			return finalList;
		}catch(Exception e){
			//e.printStackTrace();
			LOG.error("Exception Occurred in getLocationWiseAmountDetails() of FundManagementDashboardService ", e);
			return null;
		}
	}
	public void pushCountAndAmountDetails(LocationVO locationVO,Map<Long,Map<Long,Long>> financialYearIdAndLocationIdAndAmountMap,Map<Long,Map<Long,Long>> financialYearIdAndLocationIdAndCountMap,Map<Long,String> financialYearIdAndFinancialYearMap){
		try{
			List<LocationVO> locVoList = null;
			LocationVO locVO = null;
			Long totalAmount = 0L;
			Long totalCount = 0L;
			
			//push amount and count into vo
			if(financialYearIdAndFinancialYearMap != null && financialYearIdAndFinancialYearMap.size() > 0){
				locVoList = new ArrayList<LocationVO>();
				for(Entry<Long,String> param : financialYearIdAndFinancialYearMap.entrySet()){
					locVO = new LocationVO();
					locVO.setFinancialYearId(param.getKey());
					locVO.setFinancialYear(commonMethodsUtilService.getStringValueForObject(financialYearIdAndFinancialYearMap.get(param.getKey())));
					if(financialYearIdAndLocationIdAndAmountMap.get(param.getKey()) != null){
						Long amount = commonMethodsUtilService.getLongValueForObject(financialYearIdAndLocationIdAndAmountMap.get(param.getKey()).get(locationVO.getLocationId()));
						locVO.setAmunt(commonMethodsUtilService.calculateAmountInWords(amount));
						locVO.setAmount(commonMethodsUtilService.getLongValueForObject(financialYearIdAndLocationIdAndAmountMap.get(param.getKey()).get(locationVO.getLocationId())));
						totalAmount = totalAmount + commonMethodsUtilService.getLongValueForObject(financialYearIdAndLocationIdAndAmountMap.get(param.getKey()).get(locationVO.getLocationId()));
					}else{
						locVO.setAmount(0L);
					}
					if(financialYearIdAndLocationIdAndCountMap.get(param.getKey()) != null){
						locVO.setCount(commonMethodsUtilService.getLongValueForObject(financialYearIdAndLocationIdAndCountMap.get(param.getKey()).get(locationVO.getLocationId())));
						totalCount = totalCount + commonMethodsUtilService.getLongValueForObject(financialYearIdAndLocationIdAndCountMap.get(param.getKey()).get(locationVO.getLocationId()));
					}else{
						locVO.setCount(0L);
					}
					locVoList.add(locVO);
				}
				locVO = new LocationVO();
				locVO.setFinancialYearId(0L);
				locVO.setFinancialYear("All Financial Year");
				//locVO.setAmunt(commonMethodsUtilService.calculateAmountInWords(totalCount));
				locVO.setAmount(totalAmount);
				locVO.setCount(totalCount);
				locVoList.add(locVO);
				locationVO.getLocationList1().addAll(locVoList);
			}
		}catch(Exception e){
			//e.printStackTrace();
			LOG.error("Exception Occurred in pushCountAndAmountDetails() of FundManagementDashboardService ", e);
		}
	}
	
	/*
	 * Author : Hymavathi G
	 * Date : 05/06/2017
	 * Description : { Location, Scheme & Sourse Wise Funds like Highest & Lowest & Avg Funds Details }
	 */
	public LocationFundDetailsVO getLocationWiseFundDetails(InputVO inputVO){
		LocationFundDetailsVO returnVO = new LocationFundDetailsVO();
		
		try{
			Date startDate = commonMethodsUtilService.stringTODateConvertion(inputVO.getFromDateStr(),"MM/dd/yyyy","");
			Date endDate = commonMethodsUtilService.stringTODateConvertion(inputVO.getToDateStr(),"MM/dd/yyyy","");
		    
			inputVO.setFinancialYrIdList(commonMethodsUtilService.makeEmptyListByZeroValue(inputVO.getFinancialYrIdList()));
			inputVO.setDeptIdsList(commonMethodsUtilService.makeEmptyListByZeroValue(inputVO.getDeptIdsList()));
			inputVO.setSourceIdsList(commonMethodsUtilService.makeEmptyListByZeroValue(inputVO.getSourceIdsList()));
			inputVO.setSearchLvlVals(commonMethodsUtilService.makeEmptyListByZeroValue(inputVO.getSearchLvlVals()));
			
			
			Long totalfund = fundSanctionDAO.getTotalFund(inputVO.getFinancialYrIdList(),inputVO.getDeptIdsList(),inputVO.getSourceIdsList(),startDate,endDate,IConstants.CONSTITUENCY_LEVEL_SCOPE_ID,inputVO.getSearchLevelId(),inputVO.getSearchLvlVals());
			
			List<Object[]> highFund = fundSanctionDAO.getLocationWiseFundHighAndLow(inputVO.getFinancialYrIdList(),inputVO.getDeptIdsList(),inputVO.getSourceIdsList(),startDate,endDate,inputVO.getBlockLevelId(),inputVO.getType(),inputVO.getSearchLevelId(),inputVO.getSearchLvlVals());
			if(highFund != null && highFund.size() >0){
				setFundDetails(highFund,returnVO,inputVO.getType(),totalfund);
			}
			if(returnVO.getId() != null && returnVO.getId().longValue() >0l){
				List<Object[]> locWiseGrantTypes = fundSanctionDAO.getLocationWiseGrantTypesFund(inputVO.getFinancialYrIdList(),inputVO.getDeptIdsList(),inputVO.getSourceIdsList(),startDate,endDate,inputVO.getBlockLevelId(),returnVO.getId(),inputVO.getSearchLevelId(),inputVO.getSearchLvlVals());
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
			//Long  number = commonMethodsUtilService.getLongValueForObject(obj[0]);
			Long  number = Long.valueOf(commonMethodsUtilService.getStringValueForObject(obj[0]));
			    returnVO.setTotalAmt(commonMethodsUtilService.calculateAmountInWords(number));
				//returnVO.setTotalAmt(commonMethodsUtilService.getStringValueForObject(obj[0]));
			    returnVO.setTtlAmt(commonMethodsUtilService.getStringValueForObject(obj[0]));
				returnVO.setId(commonMethodsUtilService.getLongValueForObject(obj[1]));
				returnVO.setName(commonMethodsUtilService.getStringValueForObject(obj[2]));
				returnVO.setType(type);
				//Long value = Long.valueOf(returnVO.getTotalAmt().toString().substring(0, 8));
				if(totalfund != null && totalfund.longValue() >0l)
				returnVO.setPerc(commonMethodsUtilService.calculatePercantage(Long.valueOf(returnVO.getTtlAmt().toString()),totalfund));
			
			
		}catch(Exception e){
			e.printStackTrace();
			LOG.error(" Exception raised in setFundDetails (); ");
		}
	}
	
public void setGrantTypesToVO(List<Object[]> list ,LocationFundDetailsVO returnVO){
		try{
			if(list != null && list.size()>0){
				for(Object[] obj :list){
					IdNameVO grantVO = new IdNameVO();
					Long  number = (long) (obj[0] != null ? Double.valueOf(obj[0].toString()) : 0l);
					//grantVO.setTotal(commonMethodsUtilService.calculateAmountInWords(number));
					grantVO.setTotl(commonMethodsUtilService.calculateAmountInWords(number));
					grantVO.setTotal(obj[0] != null ? Double.valueOf(obj[0].toString()) : 0l);
					grantVO.setId(commonMethodsUtilService.getLongValueForObject(obj[1]));
					grantVO.setName(commonMethodsUtilService.getStringValueForObject(obj[2]));
					
					if(grantVO.getTotal() != null && grantVO.getTotal().longValue() >0l)
						grantVO.setPercentage(commonMethodsUtilService.calculatePercantage(grantVO.getTotal().longValue(),Long.valueOf(returnVO.getTtlAmt().toString())));
					returnVO.getSubList().add(grantVO);
				}
			}
		}catch(Exception e){
			//e.printStackTrace();
			LOG.error(" Exception raised in setGrantTypesToVO (); ");
		}
	}

/*
 * Author : Hymavathi G
 * Date : 06/06/2017
 * Description : { Total Funds Allocating }
 */
public LocationFundDetailsVO getTotalFunds(InputVO inputVO){
	LocationFundDetailsVO retusnVo =new LocationFundDetailsVO();
	//SimpleDateFormat sf = new SimpleDateFormat("dd/MM/yyyy");
	try{
		Date startDate = commonMethodsUtilService.stringTODateConvertion(inputVO.getFromDateStr(),"MM/dd/yyyy","");
		Date endDate = commonMethodsUtilService.stringTODateConvertion(inputVO.getToDateStr(),"MM/dd/yyyy","");

		inputVO.setFinancialYrIdList(commonMethodsUtilService.makeEmptyListByZeroValue(inputVO.getFinancialYrIdList()));
		inputVO.setDeptIdsList(commonMethodsUtilService.makeEmptyListByZeroValue(inputVO.getDeptIdsList()));
		inputVO.setSourceIdsList(commonMethodsUtilService.makeEmptyListByZeroValue(inputVO.getSourceIdsList()));
		inputVO.setSearchLvlVals(commonMethodsUtilService.makeEmptyListByZeroValue(inputVO.getSearchLvlVals()));
		
		Long totalfund = fundSanctionDAO.getTotalFund(inputVO.getFinancialYrIdList(),inputVO.getDeptIdsList(),inputVO.getSourceIdsList(),startDate,endDate,IConstants.CONSTITUENCY_LEVEL_SCOPE_ID,inputVO.getSearchLevelId(),inputVO.getSearchLvlVals());

		if(totalfund != null && totalfund.longValue() > 0l){
			retusnVo.setTotalAmt(commonMethodsUtilService.calculateAmountInWords(totalfund));
			//retusnVo.setTotalAmt(totalfund.toString());
		}
		if(retusnVo.getTotalAmt() != null){
			List<Object[]> locWiseGrantTypes = fundSanctionDAO.getLocationWiseGrantTypesFund(inputVO.getFinancialYrIdList(),inputVO.getDeptIdsList(),inputVO.getSourceIdsList(),startDate,endDate,IConstants.CONSTITUENCY_LEVEL_SCOPE_ID,null,inputVO.getSearchLevelId(),inputVO.getSearchLvlVals());
			setGrantTypesToVO(locWiseGrantTypes,retusnVo);
		}
	}catch(Exception e){
		//e.printStackTrace();
		LOG.error(" Exception raised in getTotalFunds (); ");
	}
	return retusnVo;
}

/*
 * Author : Hymavathi G
 * Date : 06/06/2017
 * Description : { Getting locations count By Sending scopeId }
 */
public LocationFundDetailsVO getTotalLocationsByScopeId(InputVO inputVO){
	LocationFundDetailsVO retusnVo =new LocationFundDetailsVO();
	//SimpleDateFormat sf = new SimpleDateFormat("dd/MM/yyyy");
	try{
		
		Date startDate = commonMethodsUtilService.stringTODateConvertion(inputVO.getFromDateStr(),"MM/dd/yyyy","");
		Date endDate = commonMethodsUtilService.stringTODateConvertion(inputVO.getToDateStr(),"MM/dd/yyyy","");
		inputVO.setFinancialYrIdList(commonMethodsUtilService.makeEmptyListByZeroValue(inputVO.getFinancialYrIdList()));
		inputVO.setDeptIdsList(commonMethodsUtilService.makeEmptyListByZeroValue(inputVO.getDeptIdsList()));
		inputVO.setSourceIdsList(commonMethodsUtilService.makeEmptyListByZeroValue(inputVO.getSourceIdsList()));
		inputVO.setSearchLvlVals(commonMethodsUtilService.makeEmptyListByZeroValue(inputVO.getSearchLvlVals()));
		
		 List<Object[]>  locations= fundSanctionDAO.getLocationsCountDetails(inputVO.getBlockLevelId(),inputVO.getFinancialYrIdList(),inputVO.getDeptIdsList(),inputVO.getSourceIdsList(),inputVO.getSearchLevelId(),inputVO.getSearchLvlVals(),startDate,endDate);
		 
		 Long totalLocations=0l;
		 		 
		 if(inputVO.getBlockLevelId() != null && inputVO.getBlockLevelId().longValue() == IConstants.DISTRICT_LEVEL_SCOPE_ID)
			 totalLocations = IConstants.TOTAL_AP_TOTAL_DISTRICTS;
		else if(inputVO.getBlockLevelId() != null && inputVO.getBlockLevelId().longValue() == IConstants.CONSTITUENCY_LEVEL_SCOPE_ID)
			 totalLocations = IConstants.TOTAL_AP_TOTAL_CONSTITUENCIES;
		else if(inputVO.getBlockLevelId() != null && inputVO.getBlockLevelId().longValue() == IConstants.MANDAL_LEVEL_SCOPE_ID)
			 totalLocations =IConstants.TOTAL_AP_TOTAL_MANDALS;
		else if(inputVO.getBlockLevelId() != null && inputVO.getBlockLevelId().longValue() == IConstants.VILLAGE_LEVEL_SCOPE_ID)
			 totalLocations =IConstants.TOTAL_AP_TOTAL_VILLAGES;
		 
		 if(commonMethodsUtilService.isListOrSetValid(locations)){
			 for(Object[] obj :locations){
				 retusnVo.setId(commonMethodsUtilService.getLongValueForObject(obj[0]));
				 retusnVo.setFundedLoc(commonMethodsUtilService.getLongValueForObject(obj[1]));
				 Long nonFunded = totalLocations-retusnVo.getFundedLoc();
				 retusnVo.setNotFundedLoc(nonFunded.longValue());
				 retusnVo.setFundedPerc(commonMethodsUtilService.calculatePercantage(retusnVo.getFundedLoc(),totalLocations));
				 retusnVo.setNonFundedPerc(commonMethodsUtilService.calculatePercantage(retusnVo.getNotFundedLoc(),totalLocations));
				 retusnVo.setTotSchemes(totalLocations);//totalLocatiopns
			 }
		 }
		 
	}catch(Exception e){
		//e.printStackTrace();
		LOG.error(" Exception raised in getTotalFunds (); ");
	}
	return retusnVo;
}
	/*
	 * Swadhin K Lenka
	 * (non-Javadoc)
	 * @see com.itgrids.service.IFundManagementDashboardService#getAverageFundForDistrict(com.itgrids.dto.InputVO)
	 */
	@Override
	public LocationFundDetailsVO getAverageFundForAnyLevel(InputVO inputVO){
		try{
			LocationFundDetailsVO retusnVo =new LocationFundDetailsVO();
			LocationFundDetailsVO fundDetailsVO = null;
			DecimalFormat df2 = new DecimalFormat(".##");
			Date sDate = commonMethodsUtilService.stringTODateConvertion(inputVO.getFromDateStr(),"MM/dd/yyyy","");
			Date eDate = commonMethodsUtilService.stringTODateConvertion(inputVO.getToDateStr(),"MM/dd/yyyy","");
			
			inputVO.setFinancialYrIdList(commonMethodsUtilService.makeEmptyListByZeroValue(inputVO.getFinancialYrIdList()));
			List<Long> deptIdsList = commonMethodsUtilService.makeEmptyListByZeroValue(inputVO.getDeptIdsList());
			List<Long> sourceIdsList = commonMethodsUtilService.makeEmptyListByZeroValue(inputVO.getSourceIdsList());
			//scope wise count and amount dtls
			List<Object[]> totalFundAndCountDtls= fundSanctionDAO.getTotalFundAndCountDtls(inputVO.getFinancialYrIdList(),deptIdsList,sourceIdsList,sDate,eDate,inputVO.getBlockLevelId());
			Long totalAmount = 0L;
			Long totalLocCount = 0L;
			Long avagecount =0L;
			Long  avagecount1 =0L;
			if(totalFundAndCountDtls != null && totalFundAndCountDtls.size() > 0){
				for(Object[] param : totalFundAndCountDtls){
					totalAmount = totalAmount + commonMethodsUtilService.getLongValueForObject(param[3]);
					totalLocCount = totalLocCount + commonMethodsUtilService.getLongValueForObject(param[2]);
				}
				retusnVo.setTotalAmt(totalAmount.toString());//srujana
				retusnVo.setAverageAmt(commonMethodsUtilService.roundUptoTwoDecimalPoint(Double.valueOf(totalAmount.toString())/(Double.valueOf(totalLocCount.toString()))));
				 avagecount = commonMethodsUtilService.roundUptoTwoDecimalPoint(Double.valueOf(totalAmount.toString())/(Double.valueOf(totalLocCount.toString()))).longValue();
				retusnVo.setAvrgeAmt(commonMethodsUtilService.calculateAmountInWords(avagecount));
				retusnVo.setPerc(commonMethodsUtilService.roundUptoTwoDecimalPoint(commonMethodsUtilService.calculatePercantage(retusnVo.getAverageAmt().longValue(),Long.parseLong(retusnVo.getTotalAmt()))));
			}
			List<Object[]> grantTypeDtlsList = grantTypeDAO.getGrandTypeDtls();
			setGrantTypeToVo(retusnVo,grantTypeDtlsList);
			//grant wise then scope wise count and amount dtls
			if(totalFundAndCountDtls != null && totalFundAndCountDtls.size() > 0){
				for(Object[] param : totalFundAndCountDtls){
					fundDetailsVO = (LocationFundDetailsVO)setterAndGetterUtilService.getMatchedVOfromList(retusnVo.getDetailsVOs(), "id", commonMethodsUtilService.getStringValueForObject(param[0]));
					fundDetailsVO.setTotalAmt(commonMethodsUtilService.getStringValueForObject(param[3]));
					fundDetailsVO.setAverageAmt(commonMethodsUtilService.roundUptoTwoDecimalPoint((Double.valueOf(commonMethodsUtilService.getStringValueForObject(param[3]))/Double.valueOf(commonMethodsUtilService.getStringValueForObject(param[2])))));
					  avagecount1 = commonMethodsUtilService.roundUptoTwoDecimalPoint((Double.valueOf(commonMethodsUtilService.getStringValueForObject(param[3]))/Double.valueOf(commonMethodsUtilService.getStringValueForObject(param[2])))).longValue();
					 fundDetailsVO.setFundAvageAmt(commonMethodsUtilService.calculateAmountInWords(avagecount1));
					fundDetailsVO.setPerc(commonMethodsUtilService.roundUptoTwoDecimalPoint(commonMethodsUtilService.calculatePercantage(fundDetailsVO.getAverageAmt().longValue(),Long.parseLong(fundDetailsVO.getTotalAmt()))));
					
				}
			}
			
			return retusnVo;
		}catch(Exception e){
			e.printStackTrace();
			LOG.error("Exception Occurred in getAverageFundForDistrict() of FundManagementDashboardService ", e);
			return null;
		}
	}
	/*
	 * Swadhin K Lenka
	 * (non-Javadoc)
	 * @see com.itgrids.service.IFundManagementDashboardService#getAverageFundForScheme(com.itgrids.dto.InputVO)
	 */
	@Override
	public LocationFundDetailsVO getAverageFundForScheme(InputVO inputVO){
		try{
			LocationFundDetailsVO retusnVo =new LocationFundDetailsVO();
			SimpleDateFormat sf = new SimpleDateFormat("dd/MM/yyyy");
			Date sDate = commonMethodsUtilService.stringTODateConvertion(inputVO.getFromDateStr(),"MM/dd/yyyy","");
			Date eDate = commonMethodsUtilService.stringTODateConvertion(inputVO.getToDateStr(),"MM/dd/yyyy","");
			Long avagecount =0L;
			
			inputVO.setFinancialYrIdList(commonMethodsUtilService.makeEmptyListByZeroValue(inputVO.getFinancialYrIdList()));
			List<Long> deptIdsList = commonMethodsUtilService.makeEmptyListByZeroValue(inputVO.getDeptIdsList());
			List<Long> sourceIdsList = commonMethodsUtilService.makeEmptyListByZeroValue(inputVO.getSourceIdsList());
			List<Long> schemeIdsList = commonMethodsUtilService.makeEmptyListByZeroValue(inputVO.getSchemeIdsList());
			List<Object[]> totalFundForSchemeList = fundSanctionDAO.getTotalFundForScheme(inputVO.getFinancialYrIdList(),deptIdsList,sourceIdsList,schemeIdsList,sDate,eDate);
			
			if(totalFundForSchemeList != null && totalFundForSchemeList.size() > 0){
				if(!commonMethodsUtilService.getStringValueForObject(totalFundForSchemeList.get(0)[1]).equalsIgnoreCase("0")){
					Long totalCount =  commonMethodsUtilService.getLongValueForObject(totalFundForSchemeList.get(0)[1]);
					retusnVo.setTotalAmt(commonMethodsUtilService.getStringValueForObject(totalFundForSchemeList.get(0)[1]));
					retusnVo.setTtlAmt(commonMethodsUtilService.calculateAmountInWords(totalCount));
					
					retusnVo.setAverageAmt(commonMethodsUtilService.roundUptoTwoDecimalPoint((Double.valueOf(commonMethodsUtilService.getStringValueForObject(totalFundForSchemeList.get(0)[1]))/Double.valueOf(commonMethodsUtilService.getStringValueForObject(totalFundForSchemeList.get(0)[0])))));
					avagecount = commonMethodsUtilService.roundUptoTwoDecimalPoint((Double.valueOf(commonMethodsUtilService.getStringValueForObject(totalFundForSchemeList.get(0)[1]))/Double.valueOf(commonMethodsUtilService.getStringValueForObject(totalFundForSchemeList.get(0)[0])))).longValue();
					retusnVo.setAvrgeAmt(commonMethodsUtilService.calculateAmountInWords(avagecount));
					retusnVo.setPerc(commonMethodsUtilService.roundUptoTwoDecimalPoint(commonMethodsUtilService.calculatePercantage(retusnVo.getAverageAmt().longValue(),Long.parseLong(retusnVo.getTotalAmt()))));
				}
			}//[4, 4428551000][4, 4428551000]
			return retusnVo;
		}catch(Exception e){
			e.printStackTrace();
			LOG.error("Exception Occurred in getAverageFundForConstituency() of FundManagementDashboardService ", e);
			return null;
		}
	}
	public void setGrantTypeToVo(LocationFundDetailsVO retusnVo, List<Object[]> grantTypeDtlsList){
		try{
			List<LocationFundDetailsVO> detailsVOs = new ArrayList<LocationFundDetailsVO>();
			LocationFundDetailsVO fundDetailsVO = null;
			if(grantTypeDtlsList != null && grantTypeDtlsList.size() > 0){
				for(Object[] param : grantTypeDtlsList){
					fundDetailsVO = new LocationFundDetailsVO();
					fundDetailsVO.setId(commonMethodsUtilService.getLongValueForObject(param[0]));
					fundDetailsVO.setName(commonMethodsUtilService.getStringValueForObject(param[1]));
					detailsVOs.add(fundDetailsVO);
				}
				retusnVo.getDetailsVOs().addAll(detailsVOs);
			}
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	public static Comparator<LocationVO> amountWiseAscOrder = new Comparator<LocationVO>() {
    	public int compare(LocationVO obj2, LocationVO obj1) {
    		Long vo2 = obj2.getLocationId();
    		Long vo1 = obj1.getLocationId();
    		//descending order of percantages.
    		return vo2.compareTo(vo1);
    	}
	};
	public static Comparator<LocationVO> amountWiseDescOrder = new Comparator<LocationVO>() {
    	public int compare(LocationVO obj2, LocationVO obj1) {
    		Long vo2 = obj2.getLocationId();
    		Long vo1 = obj1.getLocationId();
    		//descending order of percantages.
    		return vo1.compareTo(vo2);
    	}
	};
	public static Comparator<LocationVO> nameWiseAscOrder = new Comparator<LocationVO>() {
		public int compare(LocationVO location2, LocationVO location1) {
			String name2 = location2.getLocationName();
          	String name1 = location1.getLocationName();
          	//ascending order of percantages.
          	 return name2.compareTo(name1);
		}
	};
	public static Comparator<LocationVO> nameWiseDescOrder = new Comparator<LocationVO>() {
       	public int compare(LocationVO location2, LocationVO location1) {
        	String name2 = location2.getLocationName();
       	    String name1 = location1.getLocationName();
       	    //descending order of percantages.
       	    return name1.compareTo(name2);
       	}
	};
	
	
/*
 * Author : Hymavathi G
 * Date : 06/06/2017
 */
public LocationFundDetailsVO getSchemeWiseHighestAndLowestFund(InputVO inputVO ){
	LocationFundDetailsVO returnVO = new LocationFundDetailsVO();
	//SimpleDateFormat sf = new SimpleDateFormat("dd/MM/yyyy");
	try{
		Date startDate = commonMethodsUtilService.stringTODateConvertion(inputVO.getFromDateStr(),"MM/dd/yyyy","");
		Date endDate = commonMethodsUtilService.stringTODateConvertion(inputVO.getToDateStr(),"MM/dd/yyyy","");
	     
		inputVO.setFinancialYrIdList(commonMethodsUtilService.makeEmptyListByZeroValue(inputVO.getFinancialYrIdList()));
		inputVO.setDeptIdsList(commonMethodsUtilService.makeEmptyListByZeroValue(inputVO.getDeptIdsList()));
		inputVO.setSourceIdsList(commonMethodsUtilService.makeEmptyListByZeroValue(inputVO.getSourceIdsList()));
		inputVO.setSearchLvlVals(commonMethodsUtilService.makeEmptyListByZeroValue(inputVO.getSearchLvlVals()));
		
		Long totalfund = fundSanctionDAO.getTotalFund(inputVO.getFinancialYrIdList(),inputVO.getDeptIdsList(),inputVO.getSourceIdsList(),startDate,endDate,IConstants.CONSTITUENCY_LEVEL_SCOPE_ID,inputVO.getSearchLevelId(),inputVO.getSearchLvlVals());
		List<Object[]> schemeFund = fundSanctionDAO.getSchemeWiseFundHighAndLow(inputVO.getFinancialYrIdList(),inputVO.getDeptIdsList(),inputVO.getSourceIdsList(),startDate,endDate,inputVO.getType(),inputVO.getSearchLevelId(),inputVO.getSearchLvlVals());
		if(schemeFund != null && schemeFund.size() >0){
			setFundDetails(schemeFund,returnVO,inputVO.getType(),totalfund);
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
public LocationFundDetailsVO getTotalSchemes(InputVO inputVO){
	LocationFundDetailsVO retusnVo =new LocationFundDetailsVO();
	//SimpleDateFormat sf = new SimpleDateFormat("dd/MM/yyyy");
	try{
		Date startDate = commonMethodsUtilService.stringTODateConvertion(inputVO.getFromDateStr(),"MM/dd/yyyy","");
		Date endDate = commonMethodsUtilService.stringTODateConvertion(inputVO.getToDateStr(),"MM/dd/yyyy","");
		
		inputVO.setFinancialYrIdList(commonMethodsUtilService.makeEmptyListByZeroValue(inputVO.getFinancialYrIdList()));
		inputVO.setDeptIdsList(commonMethodsUtilService.makeEmptyListByZeroValue(inputVO.getDeptIdsList()));
		inputVO.setSourceIdsList(commonMethodsUtilService.makeEmptyListByZeroValue(inputVO.getSourceIdsList()));
		inputVO.setSearchLvlVals(commonMethodsUtilService.makeEmptyListByZeroValue(inputVO.getSearchLvlVals()));
		
		Long totalSchemes = fundSanctionDAO.getTotalSchemes(inputVO.getFinancialYrIdList(),inputVO.getDeptIdsList(),inputVO.getSourceIdsList(), startDate, endDate,inputVO.getSearchLevelId(),inputVO.getSearchLvlVals());
		 
		 if(totalSchemes != null && totalSchemes.longValue() >0l){
			 retusnVo.setTotSchemes(totalSchemes);
		 }
	}catch(Exception e){
		e.printStackTrace();
		LOG.error(" Exception raised in getTotalFunds (); ");
	}
	return retusnVo;
}
/*
 * Date : 06/06/2017
 * Author :raghupathi.tirumala@itgrids.com
 * Description : { to get district id and name based on state id }
 * @param : district Id
 * @return  List<LocationFundDetailsVO> 
 */
 public List<LocationFundDetailsVO>getAllDistrictByStateId(Long stateId){
	 List<LocationFundDetailsVO> voList = null;
	 
	 try{
		 if(stateId != null){
			 List<Object[]> objList = districtDAO.getDistrictIdName(stateId);
			 if(objList != null && objList.size() > 0){
				 voList = new ArrayList<LocationFundDetailsVO>();
				 for (Object[] objects : objList) {
					 LocationFundDetailsVO vo = new LocationFundDetailsVO();
					 vo.setId(commonMethodsUtilService.getLongValueForObject(objects[0]));
					 vo.setName(commonMethodsUtilService.getStringValueForObject(objects[1]));
					 voList.add(vo);
				 }
			 }
		 }
	 }catch(Exception e){
		// e.printStackTrace();
		LOG.error(" Exception raised at getdistrictidandname(); "); 
	 }
	return voList;
	
 }
 
 /*
	 * Date : 06/06/2017
	 * Author :kondababu kurakula
	 * @description : to get constituencies details based on districts
	 * @param : district Id
	 * @return  List<LocationFundDetailsVO> 
	 */
	
 	public List<LocationFundDetailsVO> getAllConstituenciesByDistrictId(Long districtId){
	  List<LocationFundDetailsVO> constincyList= null;
	  try{
	    List<Object[]> constiesObjs =constituencyDAO.getConstituencies( districtId);
	    if(constiesObjs != null && constiesObjs.size() > 0l){
	      constincyList= new ArrayList<LocationFundDetailsVO>();
	      LocationFundDetailsVO locationFundDetailsVO=null;
	      for(Object[] obj : constiesObjs){
	        locationFundDetailsVO =new LocationFundDetailsVO();
	        locationFundDetailsVO.setId(commonMethodsUtilService.getLongValueForObject(obj[0]));
	        locationFundDetailsVO.setName(commonMethodsUtilService.getStringValueForObject(obj[1]));
	        constincyList.add(locationFundDetailsVO);
	      }
	    }
	  }catch(Exception e){
	   // e.printStackTrace();
	    LOG.error(" Exception raised in getConstituencies (); ");
	  }
	return constincyList;  
	}
 	/*
 	 * Swadhin K Lenka
 	 * (non-Javadoc)
 	 * @see com.itgrids.service.IFundManagementDashboardService#getAllSubLocationsBySuperLocationId(com.itgrids.dto.InputVO)
 	 */
 	@Override
 	public List<LocationFundDetailsVO> getAllSubLocationsBySuperLocationId(InputVO inputVO){
 		try{
 			List<LocationFundDetailsVO> detailsVOs = new ArrayList<LocationFundDetailsVO>();
 			LocationFundDetailsVO locationFundDetailsVO = null;
 			List<Object[]> locationList = null;
 			String lvlIdStr = "";
 			Date sDate = commonMethodsUtilService.stringTODateConvertion(inputVO.getFromDateStr(),"MM/dd/yyyy","");
 			Date eDate = commonMethodsUtilService.stringTODateConvertion(inputVO.getToDateStr(),"MM/dd/yyyy","");
 			
 			inputVO.setFinancialYrIdList(commonMethodsUtilService.makeEmptyListByZeroValue(inputVO.getFinancialYrIdList()));
			
 			String superLocationIdStr = inputVO.getSuperLocationId().toString();
			String superLocationLevelIdStr = superLocationIdStr.substring(0, 1);
			superLocationIdStr = superLocationIdStr.substring(1);
			Long superLocationId = Long.parseLong(superLocationIdStr);
			Long superLocationLevelId = Long.parseLong(superLocationLevelIdStr);
			
			List<Long> deptIdsList = commonMethodsUtilService.makeEmptyListByZeroValue(inputVO.getDeptIdsList());
			List<Long> sourceIdsList = commonMethodsUtilService.makeEmptyListByZeroValue(inputVO.getSourceIdsList());
			if(superLocationLevelId != null && superLocationLevelId.longValue() == IConstants.STATE_LEVEL_SCOPE_ID){//get districtIds
				locationList = fundSanctionDAO.getAllDistrictByStateId(superLocationId,inputVO.getFinancialYrIdList(),deptIdsList,sourceIdsList,sDate,eDate);
				lvlIdStr = IConstants.DISTRICT_LEVEL_SCOPE_ID.toString();
			}else if(superLocationLevelId != null && superLocationLevelId.longValue() == IConstants.DISTRICT_LEVEL_SCOPE_ID){//get constituencyIds
				locationList = fundSanctionDAO.getAllConstituencyByDistrictId(superLocationId,inputVO.getFinancialYrIdList(),deptIdsList,sourceIdsList,sDate,eDate);
				lvlIdStr = IConstants.CONSTITUENCY_LEVEL_SCOPE_ID.toString();
			}else if(superLocationLevelId != null && superLocationLevelId.longValue() == IConstants.CONSTITUENCY_LEVEL_SCOPE_ID){//get tehsilIds
				locationList = fundSanctionDAO.getAllTehsilByConstituencyId(superLocationId,inputVO.getFinancialYrIdList(),deptIdsList,sourceIdsList,sDate,eDate);
				lvlIdStr = IConstants.MANDAL_LEVEL_SCOPE_ID.toString();
			}else if(superLocationLevelId != null && superLocationLevelId.longValue() == IConstants.MANDAL_LEVEL_SCOPE_ID){//get panchayatIds
				locationList = fundSanctionDAO.getAllPanchayatByTehsilId(superLocationId,inputVO.getFinancialYrIdList(),deptIdsList,sourceIdsList,sDate,eDate);
				lvlIdStr = IConstants.VILLAGE_LEVEL_SCOPE_ID.toString();
			}
			if(locationList != null && locationList.size() > 0){
				for(Object[] param : locationList){
					locationFundDetailsVO = new LocationFundDetailsVO();
					String locId = commonMethodsUtilService.getStringValueForObject(param[0]);
					locId = lvlIdStr.concat(locId);
					locationFundDetailsVO.setId(Long.parseLong(locId));
					locationFundDetailsVO.setName(commonMethodsUtilService.getStringValueForObject(param[1]));
					detailsVOs.add(locationFundDetailsVO);
				}
			}
			return detailsVOs;
 		}catch(Exception e){
 			LOG.error("Exception Occurred in getAllSubLocationsBySuperLocationId() of FundManagementDashboardService ", e);
 			return null;
 		}
 	}
 	
 
 /*
	 * Date : 06/06/2017
	 * Author :raghupathiraju.tirumala@itgrids.com
	 * @description : to get All Finiancial Years
	 * @return  List<LocationFundDetailsVO> 
	 */
 public List<LocationVO> getAllFiniancialYears(){
	  List<LocationVO> financialYearList= null;
	  try{
	    List<Object[]> financialYearObjs =financialYearDAO.getAllFiniancialYears();
	    if(financialYearObjs != null && financialYearObjs.size() > 0l){
	    	financialYearList= new ArrayList<LocationVO>();
	    	LocationVO locationVO=null;
	      for(Object[] obj : financialYearObjs){
	    	  locationVO =new LocationVO();
	    	  locationVO.setFinancialYearId(commonMethodsUtilService.getLongValueForObject(obj[0]));
	    	  locationVO.setFinancialYear(commonMethodsUtilService.getStringValueForObject(obj[1]));
	    	  financialYearList.add(locationVO);
	      }
	    }
	  }catch(Exception e){
	   // e.printStackTrace();
	    LOG.error(" Exception raised in getAllFiniancialYears (); ");
	  }
	return financialYearList;  
	}
 
 

 /*
	 * Date : 07/06/2017
	 * Author :Shrinu Pittala
	 * @description : to get All Departments
	 * @return  List<LocationFundDetailsVO> 
	 */
	
 public List<LocationFundDetailsVO> getAllDepartments(){
	  List<LocationFundDetailsVO> finalReturnList= null;
	  try{
		finalReturnList=new ArrayList<LocationFundDetailsVO>();
	    List<Object[]> DepartmentObjs =departmentDAO.getAllDepartments();
	    if(DepartmentObjs != null && DepartmentObjs.size()>0 && !DepartmentObjs.isEmpty()){
	    	for(Object[] Obj: DepartmentObjs){
	    		LocationFundDetailsVO vo=new LocationFundDetailsVO();
	    		vo.setId(commonMethodsUtilService.getLongValueForObject(Obj[0]));
	    		vo.setName(commonMethodsUtilService.getStringValueForObject(Obj[1]));
	    		finalReturnList.add(vo);
	    	}
	    }

	  }catch(Exception e){
	   // e.printStackTrace();
	    LOG.error(" Exception raised in getConstituencies (); ");
	  }
	return finalReturnList;  
	}
 	/*
	 * Swadhin K Lenka
	 * (non-Javadoc)
	 * @see com.itgrids.service.IFundManagementDashboardService#compareFundsBetweenFinancialYears(com.itgrids.dto.InputVO)
	 */
	@Override
 	public List<FundMatrixVO> compareFundsBetweenFinancialYears(InputVO inputVO){
 		try{
 			List<FundMatrixVO> finalList = new ArrayList<FundMatrixVO>();
 			//sort financial year id in ascending order.
			Collections.sort(inputVO.getFinancialYrIdList());
			if(inputVO.getFinancialYrIdList() != null && inputVO.getFinancialYrIdList().size() > 0){
				int len = inputVO.getFinancialYrIdList().size();
				for(int i=0; i < len-1 ; i++){
					if(i==0){
						//for two year comparison
						buildCompareFundsBetweenFinancialYears(finalList,inputVO.getBlockLevelId(),inputVO.getFinancialYrIdList().get(i),inputVO.getFinancialYrIdList().get(i+1));
					}else{
						//for multiple year comparison						
					}
				}
			}
			return finalList;
 		}catch(Exception e){
 			LOG.error("Exception Occurred in compareFundsBetweenFinancialYears() of FundManagementDashboardService ", e);
 		}
 		return null;
 	}
	
 	public void buildCompareFundsBetweenFinancialYears(List<FundMatrixVO> finalList, Long scopeId,Long previousYearId, Long presentYearId){
 		try{
 			List<Object[]> financialYearList = financialYearDAO.getAllFiniancialYearsByIds(new ArrayList<Long>(){{add(previousYearId);add(presentYearId);}});
 			//create a map for yearId and name
 			Map<Long,String> yearIdNameMap = new HashMap<Long,String>();
 			if(financialYearList != null && financialYearList.size() > 0){
 				for(Object[] param : financialYearList){
 					yearIdNameMap.put(commonMethodsUtilService.getLongValueForObject(param[0]), commonMethodsUtilService.getStringValueForObject(param[1]));
 				}
 			}
 			//create a template for ui
 			List<Object[]> fundSanctionRangeList = fundSanctionMatrixRangeDAO.getFundSanctionRangeList();
 			if(fundSanctionRangeList != null && fundSanctionRangeList.size() > 0){
 				buildTemplate(finalList,fundSanctionRangeList);
 			}
 			
 			//take all the location based on fund range for previous year
 			List<Object[]> previousYearDtls = fundSanctionMatrixDetailsDAO.getPreviousYearDtls(scopeId,previousYearId);
 			//create a map for rangeId and list of location
 			Map<Long,List<Long>> rangeIdAndLocListMap = new LinkedHashMap<Long,List<Long>>();
 			List<Long> locationList = null;
 			if(previousYearDtls != null && previousYearDtls.size() > 0){
 				for(Object[] param : previousYearDtls){
 					locationList = rangeIdAndLocListMap.get(commonMethodsUtilService.getLongValueForObject(param[0]));
 					if(locationList == null){
 						locationList = new ArrayList<Long>();
 						rangeIdAndLocListMap.put(commonMethodsUtilService.getLongValueForObject(param[0]), locationList);
 					}
 					locationList.add(commonMethodsUtilService.getLongValueForObject(param[1]));
 				}
 			}
 			
 			//push the total count in previous year range
 			RangeVO rangeVO = null;
 			if(finalList != null && finalList.size() > 0){
 				for(FundMatrixVO param : finalList){
 					rangeVO = (RangeVO)setterAndGetterUtilService.getMatchedVOfromList(param.getRangeList(), "id", "0");
 					if(rangeIdAndLocListMap != null && rangeIdAndLocListMap.get(param.getId()) != null && rangeIdAndLocListMap.get(param.getId()).size() > 0){
 						rangeVO.setValue(new Integer(rangeIdAndLocListMap.get(param.getId()).size()).toString());
 					}
 				}
 			}
 			System.out.println("hi");
 		}catch(Exception e){
 			LOG.error("Exception Occurred in buildCompareFundsBetweenFinancialYears() of FundManagementDashboardService ", e);
 		}
 	}
 	public void buildTemplate(List<FundMatrixVO> finalList,List<Object[]> fundSanctionRangeList){
 		try{
 			FundMatrixVO fundMatrixVO = null;
 			
 			for(Object[] param : fundSanctionRangeList){
 				fundMatrixVO = new FundMatrixVO();
 				fundMatrixVO.setId(commonMethodsUtilService.getLongValueForObject(param[0]));
 				fundMatrixVO.setRange(commonMethodsUtilService.getStringValueForObject(param[1]));
 				pushPresentYearRangeValue(fundMatrixVO,fundSanctionRangeList);
 				finalList.add(fundMatrixVO);
 			}
 		}catch(Exception e){
 			LOG.error("Exception Occurred in buildTemplate() of FundManagementDashboardService ", e);
 		}
 	}
 	public void pushPresentYearRangeValue(FundMatrixVO fundMatrixVO,List<Object[]> fundSanctionRangeList){
 		try{
 			List<RangeVO> rangeVoList = new ArrayList<RangeVO>();
 			RangeVO rangeVO = null;
 			rangeVO = new RangeVO();
 			rangeVO.setId(0L);
 			rangeVO.setName("");
 			rangeVO.setValue("0");
 			rangeVoList.add(rangeVO);
 			for(Object[] param : fundSanctionRangeList){
 				rangeVO = new RangeVO();
 				rangeVO.setId(commonMethodsUtilService.getLongValueForObject(param[0]));
 				rangeVO.setName(commonMethodsUtilService.getStringValueForObject(param[1]));
 				rangeVO.setValue("0");
 				rangeVoList.add(rangeVO);
 			}
 			fundMatrixVO.getRangeList().addAll(rangeVoList);
 		}catch(Exception e){
 			LOG.error("Exception Occurred in voidpushPresentYearRangeValue() of FundManagementDashboardService ", e);
 		}
 	}
}
