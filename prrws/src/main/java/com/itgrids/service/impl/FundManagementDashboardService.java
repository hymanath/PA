package com.itgrids.service.impl;

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
import com.itgrids.dao.IGrantTypeDAO;
import com.itgrids.dto.AddressVO;
import com.itgrids.dto.FundSchemeVO;
import com.itgrids.dto.IdNameVO;
import com.itgrids.dto.InputVO;
import com.itgrids.dto.LocationFundDetailsVO;
import com.itgrids.dto.LocationVO;
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
			List<Long> sourceIdsList,List<Long> schemeIdsList,String startDateStr,String endDateStr,Long searchScopeId,List<Long> searchScopeValuesList){
		List<FundSchemeVO> returnList = new ArrayList<FundSchemeVO>(0);
		try {
			Date startDate = commonMethodsUtilService.stringTODateConvertion(startDateStr,"MM/dd/yyyy","");
			Date endDate = commonMethodsUtilService.stringTODateConvertion(endDateStr,"MM/dd/yyyy","");
			
			financialYearIdsList = commonMethodsUtilService.makeEmptyListByZeroValue(financialYearIdsList);
			deptIdsList = commonMethodsUtilService.makeEmptyListByZeroValue(deptIdsList);
			sourceIdsList = commonMethodsUtilService.makeEmptyListByZeroValue(sourceIdsList);
			schemeIdsList = commonMethodsUtilService.makeEmptyListByZeroValue(schemeIdsList);
			searchScopeValuesList = commonMethodsUtilService.makeEmptyListByZeroValue(searchScopeValuesList);
			
			List<Object[]> result =  fundSanctionDAO.getFinancialYearWiseScheameDetails(financialYearIdsList,deptIdsList,sourceIdsList, schemeIdsList,startDate,endDate,searchScopeId,searchScopeValuesList);
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
					addressVO.setDistrictId(commonMethodsUtilService.getLongValueForObject(param[8]));
					addressVO.setDistrictName(commonMethodsUtilService.getStringValueForObject(param[9]));
					addressVO.setAssemblyId(commonMethodsUtilService.getLongValueForObject(param[10]));
					addressVO.setAssemblyName(commonMethodsUtilService.getStringValueForObject(param[11]));
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
			}
			
		} catch (Exception e) {
			LOG.error(" Exception occured in FundManagementDashboardService ,getFinancialYearWiseScheameDetails() ",e);
		}
		return returnList;
	}
	
	/*
	 public List<FundSchemeVO> getFinancialYearWiseSchemeDetails(List<Long> financialYearIdsList,List<Long> deptIdsList,
			List<Long> sourceIdsList,List<Long> schemeIdsList,String startDateStr,String endDateStr,Long searchScopeId,List<Long> searchScopeValuesList){
		List<FundSchemeVO> returnList = new ArrayList<FundSchemeVO>(0);
		try {
			Date startDate = commonMethodsUtilService.stringTODateConvertion(startDateStr,"MM/dd/yyyy","");
			Date endDate = commonMethodsUtilService.stringTODateConvertion(endDateStr,"MM/dd/yyyy","");
			
			financialYearIdsList = commonMethodsUtilService.makeEmptyListByZeroValue(financialYearIdsList);
			deptIdsList = commonMethodsUtilService.makeEmptyListByZeroValue(deptIdsList);
			sourceIdsList = commonMethodsUtilService.makeEmptyListByZeroValue(sourceIdsList);
			schemeIdsList = commonMethodsUtilService.makeEmptyListByZeroValue(schemeIdsList);
			searchScopeValuesList = commonMethodsUtilService.makeEmptyListByZeroValue(searchScopeValuesList);
			
			List<Object[]> result =  fundSanctionDAO.getFinancialYearWiseScheameDetails(financialYearIdsList,deptIdsList,sourceIdsList, schemeIdsList,startDate,endDate,searchScopeId,searchScopeValuesList);
			Map<Long,FundSchemeVO> locationMap = new HashMap<Long,FundSchemeVO>(0);
			Map<Long,FundSchemeVO> yearsMap = new HashMap<Long,FundSchemeVO>(0);
			//Map<Long,FundSchemeVO> deptsMap = new HashMap<Long,FundSchemeVO>(0);
			Map<Long,FundSchemeVO> schemesMap = new HashMap<Long,FundSchemeVO>(0);
			
			if(commonMethodsUtilService.isListOrSetValid(result)){
				for (Object[] param : result) {
					FundSchemeVO fundLocationVO = new FundSchemeVO();
					
					AddressVO addressVO = new AddressVO();
					addressVO.setDistrictId(commonMethodsUtilService.getLongValueForObject(param[8]));
					addressVO.setDistrictName(commonMethodsUtilService.getStringValueForObject(param[9]));
					addressVO.setAssemblyId(commonMethodsUtilService.getLongValueForObject(param[10]));
					addressVO.setAssemblyName(commonMethodsUtilService.getStringValueForObject(param[11]));
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
							
						yearVO.getSubList().add(schemeVO);
						fundLocationVO.getSubList().add(yearVO);
						
						yearsMap.put(yearVO.getYearId(), yearVO);
						schemesMap.put(schemeVO.getId(), schemeVO);
					}else{
						FundSchemeVO schemeVO = (FundSchemeVO) setterAndGetterUtilService.getMatchedVOfromList(yearVO.getSubList(), "id", commonMethodsUtilService.getLongValueForObject(param[0]).toString());
						if(schemeVO == null){
							schemeVO = new FundSchemeVO();					
							schemeVO.setId(commonMethodsUtilService.getLongValueForObject(param[0]));
							schemeVO.setName(commonMethodsUtilService.getStringValueForObject(param[1]));
							schemeVO.setCount(commonMethodsUtilService.getLongValueForObject(param[4]));
							schemeVO.setTotalCount(commonMethodsUtilService.getLongValueForObject(param[5]));
							yearVO.getSubList().add(schemeVO);
							
							schemesMap.put(schemeVO.getId(), schemeVO);
						}
						else{
							schemeVO.setCount(schemeVO.getCount()+commonMethodsUtilService.getLongValueForObject(param[4]));
							schemeVO.setTotalCount(schemeVO.getTotalCount()+commonMethodsUtilService.getLongValueForObject(param[5]));
						}
					}
					
					fundLocationVO.setAddressVO(addressVO);
					locationMap.put(keyId, fundLocationVO);
				}
			}
			
			if(commonMethodsUtilService.isMapValid(locationMap)){
				for (Long keyId : locationMap.keySet()) {
					FundSchemeVO fundLocationVO = locationMap.get(keyId);
					if(commonMethodsUtilService.isListOrSetValid(fundLocationVO.getSubList()) && fundLocationVO.getSubList().size() == yearsMap.size()){
						for (FundSchemeVO yearVO : fundLocationVO.getSubList()) {
							if(commonMethodsUtilService.isListOrSetValid(yearVO.getSubList()) && yearVO.getSubList().size() < schemesMap.size()){
								Set<Long> availableSchemeIds = new HashSet<Long>(0);
								for (FundSchemeVO schemeVO : yearVO.getSubList()) {
									availableSchemeIds.add(schemeVO.getId());
								}
								
								for (Long schemeId : schemesMap.keySet()) {
									if(!availableSchemeIds.contains(schemeId)){
										FundSchemeVO tempSchemeVO = schemesMap.get(schemeId);
										FundSchemeVO schemeVO = new FundSchemeVO();					
										schemeVO.setId(tempSchemeVO.getId());
										schemeVO.setName(tempSchemeVO.getName());
										schemeVO.setCount(0L);
										schemeVO.setTotalCount(0L);
										yearVO.getSubList().add(schemeVO);
									}
								}
							}else if(!commonMethodsUtilService.isListOrSetValid(yearVO.getSubList())){
								for (Long schemeId : schemesMap.keySet()) {
									FundSchemeVO tempSchemeVO = schemesMap.get(schemeId);
									FundSchemeVO schemeVO = new FundSchemeVO();					
									schemeVO.setId(tempSchemeVO.getId());
									schemeVO.setName(tempSchemeVO.getName());
									schemeVO.setCount(0L);
									schemeVO.setTotalCount(0L);
									yearVO.getSubList().add(schemeVO);
								}
							}
							yearVO.setAddressVO(fundLocationVO.getAddressVO());
						}
						
					} else if(commonMethodsUtilService.isListOrSetValid(fundLocationVO.getSubList()) && fundLocationVO.getSubList().size() < yearsMap.size()){
						Set<Long> availableYearIds = new HashSet<Long>(0);
						for (FundSchemeVO yearVO : fundLocationVO.getSubList()) {
							availableYearIds.add(yearVO.getYearId());
							yearVO.setAddressVO(fundLocationVO.getAddressVO());
							
							Set<Long> availableSchemeIds = new HashSet<Long>(0);
							for (FundSchemeVO schemeVO : yearVO.getSubList()) {
								availableSchemeIds.add(schemeVO.getId());
							}
							
							for (Long schemeId : schemesMap.keySet()) {
								if(!availableSchemeIds.contains(schemeId)){
									FundSchemeVO tempSchemeVO = schemesMap.get(schemeId);
									FundSchemeVO schemeVO = new FundSchemeVO();					
									schemeVO.setId(tempSchemeVO.getId());
									schemeVO.setName(tempSchemeVO.getName());
									schemeVO.setCount(0L);
									schemeVO.setTotalCount(0L);
									yearVO.getSubList().add(schemeVO);
								}
							}
						}
						
						for (Long yearId : yearsMap.keySet()) {
							if(!availableYearIds.contains(yearId)){
								FundSchemeVO tempYearVO = yearsMap.get(yearId);
								
								FundSchemeVO yearVO = new FundSchemeVO();
								yearVO.setYearId(tempYearVO.getYearId());
								yearVO.setYear(tempYearVO.getYear());
								yearVO.setAddressVO(fundLocationVO.getAddressVO());
								
								for (Long schemeId : schemesMap.keySet()) {
									FundSchemeVO tempSchemeVO = schemesMap.get(schemeId);
									FundSchemeVO schemeVO = new FundSchemeVO();					
									schemeVO.setId(tempSchemeVO.getId());
									schemeVO.setName(tempSchemeVO.getName());
									schemeVO.setCount(0L);
									schemeVO.setTotalCount(0L);
									yearVO.getSubList().add(schemeVO);
								}
								fundLocationVO.getSubList().add(yearVO);
							}
						}
						
					}else if(!commonMethodsUtilService.isListOrSetValid(fundLocationVO.getSubList())){
						for (Long yearId : yearsMap.keySet()) {
								FundSchemeVO tempYearVO = yearsMap.get(yearId);
								FundSchemeVO yearVO = new FundSchemeVO();
								yearVO.setYearId(tempYearVO.getYearId());
								yearVO.setYear(tempYearVO.getYear());
								
								for (Long schemeId : schemesMap.keySet()) {
									FundSchemeVO tempSchemeVO = schemesMap.get(schemeId);
									FundSchemeVO schemeVO = new FundSchemeVO();					
									schemeVO.setId(tempSchemeVO.getId());
									schemeVO.setName(tempSchemeVO.getName());
									schemeVO.setCount(0L);
									schemeVO.setTotalCount(0L);
									yearVO.getSubList().add(schemeVO);
								}
								yearVO.setAddressVO(fundLocationVO.getAddressVO());
								fundLocationVO.getSubList().add(yearVO);
							}
					}
					returnList.add(fundLocationVO);
				}
			}
			
		} catch (Exception e) {
			LOG.error(" Exception occured in FundManagementDashboardService ,getFinancialYearWiseScheameDetails() ",e);
		}
		return returnList;
	}
	  */
	
	
	/*
	 * Date : 08/06/2017
	 * Author :Srishailam Pittala
	 * @description : to get financial year ,  Department wise and scheme wise funds transaction details
	 */
	
	public List<FundSchemeVO> getFinancialYearWiseDeptsWiseSchemeDetails(List<Long> financialYearIdsList,List<Long> deptIdsList,
			List<Long> sourceIdsList,List<Long> schemeIdsList,String startDateStr,String endDateStr,Long searchScopeId,List<Long> searchScopeValuesList){
		List<FundSchemeVO> returnList = new ArrayList<FundSchemeVO>(0);
		try {
			Date startDate = commonMethodsUtilService.stringTODateConvertion(startDateStr,"MM/dd/yyyy","");
			Date endDate = commonMethodsUtilService.stringTODateConvertion(endDateStr,"MM/dd/yyyy","");
			
			financialYearIdsList = commonMethodsUtilService.makeEmptyListByZeroValue(financialYearIdsList);
			deptIdsList = commonMethodsUtilService.makeEmptyListByZeroValue(deptIdsList);
			sourceIdsList = commonMethodsUtilService.makeEmptyListByZeroValue(sourceIdsList);
			schemeIdsList = commonMethodsUtilService.makeEmptyListByZeroValue(schemeIdsList);
			searchScopeValuesList = commonMethodsUtilService.makeEmptyListByZeroValue(searchScopeValuesList);
			
			List<Object[]> result =  fundSanctionDAO.getFinancialYearWiseDeptsWiseScheameDetails(financialYearIdsList,deptIdsList,sourceIdsList, schemeIdsList,startDate,endDate,searchScopeId,searchScopeValuesList);
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
					
					if(deptsMap.get(commonMethodsUtilService.getLongValueForObject(param[12])) == null){
						FundSchemeVO deptsVO = new FundSchemeVO();					
						deptsVO.setId(commonMethodsUtilService.getLongValueForObject(param[12]));
						deptsVO.setName(commonMethodsUtilService.getStringValueForObject(param[13]));
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
					addressVO.setDistrictId(commonMethodsUtilService.getLongValueForObject(param[8]));
					addressVO.setDistrictName(commonMethodsUtilService.getStringValueForObject(param[9]));
					addressVO.setAssemblyId(commonMethodsUtilService.getLongValueForObject(param[10]));
					addressVO.setAssemblyName(commonMethodsUtilService.getStringValueForObject(param[11]));
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
						deptsVO.setId(commonMethodsUtilService.getLongValueForObject(param[12]));
						deptsVO.setName(commonMethodsUtilService.getStringValueForObject(param[13]));
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
						FundSchemeVO deptsVO = (FundSchemeVO) setterAndGetterUtilService.getMatchedVOfromList(yearVO.getSubList(), "id", commonMethodsUtilService.getLongValueForObject(param[12]).toString());
						if(deptsVO == null){
							deptsVO = new FundSchemeVO();					
							deptsVO.setId(commonMethodsUtilService.getLongValueForObject(param[12]));
							deptsVO.setName(commonMethodsUtilService.getStringValueForObject(param[13]));
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
	
/*	public List<FundSchemeVO> getFinancialYearWiseDeptsWiseSchemeDetails(List<Long> financialYearIdsList,List<Long> deptIdsList,
			List<Long> sourceIdsList,List<Long> schemeIdsList,String startDateStr,String endDateStr,Long searchScopeId,List<Long> searchScopeValuesList){
		List<FundSchemeVO> returnList = new ArrayList<FundSchemeVO>(0);
		try {
			Date startDate = commonMethodsUtilService.stringTODateConvertion(startDateStr,"MM/dd/yyyy","");
			Date endDate = commonMethodsUtilService.stringTODateConvertion(endDateStr,"MM/dd/yyyy","");
			
			financialYearIdsList = commonMethodsUtilService.makeEmptyListByZeroValue(financialYearIdsList);
			deptIdsList = commonMethodsUtilService.makeEmptyListByZeroValue(deptIdsList);
			sourceIdsList = commonMethodsUtilService.makeEmptyListByZeroValue(sourceIdsList);
			schemeIdsList = commonMethodsUtilService.makeEmptyListByZeroValue(schemeIdsList);
			searchScopeValuesList = commonMethodsUtilService.makeEmptyListByZeroValue(searchScopeValuesList);
			
			List<Object[]> result =  fundSanctionDAO.getFinancialYearWiseDeptsWiseScheameDetails(financialYearIdsList,deptIdsList,sourceIdsList, schemeIdsList,startDate,endDate,searchScopeId,searchScopeValuesList);
			Map<Long,FundSchemeVO> locationMap = new HashMap<Long,FundSchemeVO>(0);
			Map<Long,FundSchemeVO> yearsMap = new HashMap<Long,FundSchemeVO>(0);
			Map<Long,FundSchemeVO> deptsMap = new HashMap<Long,FundSchemeVO>(0);
			Map<Long,FundSchemeVO> schemesMap = new HashMap<Long,FundSchemeVO>(0);
			
			if(commonMethodsUtilService.isListOrSetValid(result)){
				for (Object[] param : result) {
					FundSchemeVO fundLocationVO = new FundSchemeVO();
					
					AddressVO addressVO = new AddressVO();
					addressVO.setDistrictId(commonMethodsUtilService.getLongValueForObject(param[8]));
					addressVO.setDistrictName(commonMethodsUtilService.getStringValueForObject(param[9]));
					addressVO.setAssemblyId(commonMethodsUtilService.getLongValueForObject(param[10]));
					addressVO.setAssemblyName(commonMethodsUtilService.getStringValueForObject(param[11]));
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
					}
					
					FundSchemeVO yearVO = (FundSchemeVO) setterAndGetterUtilService.getMatchedVOfromList(fundLocationVO.getSubList(), "yearId", commonMethodsUtilService.getLongValueForObject(param[2]).toString());
					if(yearVO == null){
						yearVO = new FundSchemeVO();
						yearVO.setYearId(commonMethodsUtilService.getLongValueForObject(param[2]));
						yearVO.setYear(commonMethodsUtilService.getStringValueForObject(param[3]));
						
						FundSchemeVO deptsVO = new FundSchemeVO();					
						deptsVO.setId(commonMethodsUtilService.getLongValueForObject(param[12]));
						deptsVO.setName(commonMethodsUtilService.getStringValueForObject(param[13]));
						deptsVO.setYearId(commonMethodsUtilService.getLongValueForObject(param[2]));
						deptsVO.setYear(commonMethodsUtilService.getStringValueForObject(param[3]));
						deptsVO.setAddressVO(addressVO);
						
						FundSchemeVO schemeVO = new FundSchemeVO();					
						schemeVO.setId(commonMethodsUtilService.getLongValueForObject(param[0]));
						schemeVO.setName(commonMethodsUtilService.getStringValueForObject(param[1]));
						schemeVO.setCount(commonMethodsUtilService.getLongValueForObject(param[4]));
						schemeVO.setTotalCount(commonMethodsUtilService.getLongValueForObject(param[5]));
							
						deptsVO.getSubList().add(schemeVO);
						yearVO.getSubList().add(deptsVO);
						fundLocationVO.getSubList().add(yearVO);
						
						deptsMap.put(deptsVO.getId(), deptsVO);
						yearsMap.put(yearVO.getYearId(), yearVO);
						schemesMap.put(schemeVO.getId(), schemeVO);
					}else{
						FundSchemeVO deptsVO = (FundSchemeVO) setterAndGetterUtilService.getMatchedVOfromList(yearVO.getSubList(), "id", commonMethodsUtilService.getLongValueForObject(param[12]).toString());
						if(deptsVO == null){
							deptsVO = new FundSchemeVO();					
							deptsVO.setId(commonMethodsUtilService.getLongValueForObject(param[12]));
							deptsVO.setName(commonMethodsUtilService.getStringValueForObject(param[13]));
							deptsVO.setYearId(commonMethodsUtilService.getLongValueForObject(param[2]));
							deptsVO.setYear(commonMethodsUtilService.getStringValueForObject(param[3]));
							deptsVO.setAddressVO(addressVO);
							
							
							FundSchemeVO schemeVO = new FundSchemeVO();					
							schemeVO.setId(commonMethodsUtilService.getLongValueForObject(param[0]));
							schemeVO.setName(commonMethodsUtilService.getStringValueForObject(param[1]));
							schemeVO.setCount(commonMethodsUtilService.getLongValueForObject(param[4]));
							schemeVO.setTotalCount(commonMethodsUtilService.getLongValueForObject(param[5]));
								
							deptsVO.getSubList().add(schemeVO);
							yearVO.getSubList().add(deptsVO);
							
							
							deptsMap.put(deptsVO.getId(), deptsVO);
							yearsMap.put(yearVO.getYearId(), yearVO);
							schemesMap.put(schemeVO.getId(), schemeVO);
							
						}else{
							FundSchemeVO schemeVO = (FundSchemeVO) setterAndGetterUtilService.getMatchedVOfromList(deptsVO.getSubList(), "id", commonMethodsUtilService.getLongValueForObject(param[0]).toString());
							if(schemeVO == null){
								schemeVO = new FundSchemeVO();					
								schemeVO.setId(commonMethodsUtilService.getLongValueForObject(param[0]));
								schemeVO.setName(commonMethodsUtilService.getStringValueForObject(param[1]));
								schemeVO.setCount(commonMethodsUtilService.getLongValueForObject(param[4]));
								schemeVO.setTotalCount(commonMethodsUtilService.getLongValueForObject(param[5]));
								deptsVO.getSubList().add(schemeVO);
								
								deptsMap.put(deptsVO.getId(), deptsVO);
								yearsMap.put(yearVO.getYearId(), yearVO);
								schemesMap.put(schemeVO.getId(), schemeVO);
							}
							else{
								schemeVO.setCount(schemeVO.getCount()+commonMethodsUtilService.getLongValueForObject(param[4]));
								schemeVO.setTotalCount(schemeVO.getTotalCount()+commonMethodsUtilService.getLongValueForObject(param[5]));
							}
						}
					}
					
					fundLocationVO.setAddressVO(addressVO);
					locationMap.put(keyId, fundLocationVO);
				}
			}
			
			if(commonMethodsUtilService.isMapValid(locationMap)){
				for (Long keyId : locationMap.keySet()) {
					FundSchemeVO fundLocationVO = locationMap.get(keyId);
					if(commonMethodsUtilService.isListOrSetValid(fundLocationVO.getSubList()) && fundLocationVO.getSubList().size() == yearsMap.size()){
						for (FundSchemeVO yearVO : fundLocationVO.getSubList()) {
							
							if(commonMethodsUtilService.isListOrSetValid(yearVO.getSubList()) && yearVO.getSubList().size() == deptsMap.size()){
								for (FundSchemeVO deptsVO : yearVO.getSubList()) {
									if(commonMethodsUtilService.isListOrSetValid(deptsVO.getSubList()) && deptsVO.getSubList().size() < schemesMap.size()){
										Set<Long> availableSchemeIds = new HashSet<Long>(0);
										for (FundSchemeVO schemeVO : deptsVO.getSubList()) {
											availableSchemeIds.add(schemeVO.getId());
										}
										
										for (Long schemeId : schemesMap.keySet()) {
											if(!availableSchemeIds.contains(schemeId)){
												FundSchemeVO tempSchemeVO = schemesMap.get(schemeId);
												FundSchemeVO schemeVO = new FundSchemeVO();					
												schemeVO.setId(tempSchemeVO.getId());
												schemeVO.setName(tempSchemeVO.getName());
												schemeVO.setCount(0L);
												schemeVO.setTotalCount(0L);
												deptsVO.getSubList().add(schemeVO);
											}
										}
									}else if(!commonMethodsUtilService.isListOrSetValid(deptsVO.getSubList())){
										for (Long schemeId : schemesMap.keySet()) {
											FundSchemeVO tempSchemeVO = schemesMap.get(schemeId);
											FundSchemeVO schemeVO = new FundSchemeVO();					
											schemeVO.setId(tempSchemeVO.getId());
											schemeVO.setName(tempSchemeVO.getName());
											schemeVO.setCount(0L);
											schemeVO.setTotalCount(0L);
											deptsVO.getSubList().add(schemeVO);
										}
									}
									deptsVO.setYearId(yearVO.getYearId());
									deptsVO.setYear(yearVO.getYear());
									deptsVO.setAddressVO(fundLocationVO.getAddressVO());
									yearVO.setAddressVO(fundLocationVO.getAddressVO());
								}
							}
							else if(commonMethodsUtilService.isListOrSetValid(yearVO.getSubList()) && yearVO.getSubList().size() < deptsMap.size()){
								Set<Long> availableDeptIds = new HashSet<Long>(0);
								for (FundSchemeVO deptVO : yearVO.getSubList()) {
									availableDeptIds.add(deptVO.getId());
									
									Set<Long> availableSchemeIds = new HashSet<Long>(0);
									for (FundSchemeVO schemeVO : deptVO.getSubList()) {
										availableSchemeIds.add(schemeVO.getId());
									}
									
									for (Long schemeId : schemesMap.keySet()) {
										if(!availableSchemeIds.contains(schemeId)){
											FundSchemeVO tempSchemeVO = schemesMap.get(schemeId);
											FundSchemeVO schemeVO = new FundSchemeVO();					
											schemeVO.setId(tempSchemeVO.getId());
											schemeVO.setName(tempSchemeVO.getName());
											schemeVO.setCount(0L);
											schemeVO.setTotalCount(0L);
											deptVO.getSubList().add(schemeVO);
										}
									}
									deptVO.setYearId(yearVO.getYearId());
									deptVO.setYear(yearVO.getYear());
									deptVO.setAddressVO(fundLocationVO.getAddressVO());
								}
								
								for (Long deptId : deptsMap.keySet()) {
									if(!availableDeptIds.contains(deptId)){
										FundSchemeVO tempDeptVO = deptsMap.get(deptId);
										FundSchemeVO deptsVO = new FundSchemeVO();					
										deptsVO.setId(tempDeptVO.getId());
										deptsVO.setName(tempDeptVO.getName());
										deptsVO.setYearId(yearVO.getYearId());
										deptsVO.setYear(yearVO.getYear());
										
										for (Long schemeId : schemesMap.keySet()) {
											FundSchemeVO tempSchemeVO = schemesMap.get(schemeId);
											FundSchemeVO schemeVO = new FundSchemeVO();					
											schemeVO.setId(tempSchemeVO.getId());
											schemeVO.setName(tempSchemeVO.getName());
											schemeVO.setCount(0L);
											schemeVO.setTotalCount(0L);
											deptsVO.getSubList().add(schemeVO);
										}
										yearVO.getSubList().add(deptsVO);
										deptsVO.setAddressVO(fundLocationVO.getAddressVO());
									}
								}
								
							}else if(!commonMethodsUtilService.isListOrSetValid(yearVO.getSubList())){
								for (Long deptId : deptsMap.keySet()) {
										FundSchemeVO tempDeptVO = deptsMap.get(deptId);
										FundSchemeVO deptsVO = new FundSchemeVO();					
										deptsVO.setId(tempDeptVO.getId());
										deptsVO.setName(tempDeptVO.getName());
										deptsVO.setYearId(yearVO.getYearId());
										deptsVO.setYear(yearVO.getYear());
										
										for (Long schemeId : schemesMap.keySet()) {
											FundSchemeVO tempSchemeVO = schemesMap.get(schemeId);
											FundSchemeVO schemeVO = new FundSchemeVO();					
											schemeVO.setId(tempSchemeVO.getId());
											schemeVO.setName(tempSchemeVO.getName());
											schemeVO.setCount(0L);
											schemeVO.setTotalCount(0L);
											deptsVO.getSubList().add(schemeVO);
										}
										deptsVO.setAddressVO(fundLocationVO.getAddressVO());
										yearVO.getSubList().add(deptsVO);
								}
							}
							yearVO.setAddressVO(fundLocationVO.getAddressVO());
						}
					} else if(commonMethodsUtilService.isListOrSetValid(fundLocationVO.getSubList()) && fundLocationVO.getSubList().size() < yearsMap.size()){
						Set<Long> availableYearIds = new HashSet<Long>(0);
						for (FundSchemeVO yearVO : fundLocationVO.getSubList()) {
							availableYearIds.add(yearVO.getYearId());
							yearVO.setAddressVO(fundLocationVO.getAddressVO());
						}
						
						for (Long yearId : yearsMap.keySet()) {
							if(!availableYearIds.contains(yearId)){
								FundSchemeVO tempYearVO = yearsMap.get(yearId);
								
								FundSchemeVO yearVO = new FundSchemeVO();
								yearVO.setYearId(tempYearVO.getYearId());
								yearVO.setYear(tempYearVO.getYear());
								
								
								for (Long deptId : deptsMap.keySet()) {
									FundSchemeVO tempDeptVO = deptsMap.get(deptId);
									FundSchemeVO deptsVO = new FundSchemeVO();					
									deptsVO.setId(tempDeptVO.getId());
									deptsVO.setName(tempDeptVO.getName());
									deptsVO.setYearId(yearVO.getYearId());
									deptsVO.setYear(yearVO.getYear());
									
									for (Long schemeId : schemesMap.keySet()) {
										FundSchemeVO tempSchemeVO = schemesMap.get(schemeId);
										FundSchemeVO schemeVO = new FundSchemeVO();					
										schemeVO.setId(tempSchemeVO.getId());
										schemeVO.setName(tempSchemeVO.getName());
										schemeVO.setCount(0L);
										schemeVO.setTotalCount(0L);
										deptsVO.getSubList().add(schemeVO);
									}
									deptsVO.setAddressVO(fundLocationVO.getAddressVO());
									yearVO.getSubList().add(deptsVO);
								}
								
								fundLocationVO.getSubList().add(yearVO);
							}
						}
						
						for (Long yearId : yearsMap.keySet()) {
							FundSchemeVO yearVO = (FundSchemeVO) setterAndGetterUtilService.getMatchedVOfromList(fundLocationVO.getSubList(), "yearId", yearId.toString());
							if(yearVO != null){
								Set<Long> availableDeptIds = new HashSet<Long>(0);
								for (FundSchemeVO deptVO : yearVO.getSubList()) {
									availableDeptIds.add(deptVO.getId());
								}
								
								for (Long deptId : deptsMap.keySet()) {
									if(!availableDeptIds.contains(deptId)){
										FundSchemeVO tempDeptVO = deptsMap.get(deptId);
										FundSchemeVO deptsVO = new FundSchemeVO();					
										deptsVO.setId(tempDeptVO.getId());
										deptsVO.setName(tempDeptVO.getName());
										deptsVO.setYearId(yearVO.getYearId());
										deptsVO.setYear(yearVO.getYear());
										
										for (Long schemeId : schemesMap.keySet()) {
											FundSchemeVO tempSchemeVO = schemesMap.get(schemeId);
											FundSchemeVO schemeVO = new FundSchemeVO();					
											schemeVO.setId(tempSchemeVO.getId());
											schemeVO.setName(tempSchemeVO.getName());
											schemeVO.setCount(0L);
											schemeVO.setTotalCount(0L);
											deptsVO.getSubList().add(schemeVO);
										}
										deptsVO.setAddressVO(fundLocationVO.getAddressVO());
										yearVO.getSubList().add(deptsVO);
									}
								}
								
								for (Long deptId : deptsMap.keySet()) {
									FundSchemeVO deptVO = (FundSchemeVO) setterAndGetterUtilService.getMatchedVOfromList(yearVO.getSubList(), "id", deptId.toString());
									if(deptVO != null){
										Set<Long> availableSchemeIds = new HashSet<Long>(0);
										for (FundSchemeVO schemeVO : deptVO.getSubList()) {
											availableSchemeIds.add(schemeVO.getId());
										}
										
										for (Long schemeId : schemesMap.keySet()) {
											if(!availableSchemeIds.contains(schemeId)){
												FundSchemeVO tempSchemeVO = schemesMap.get(schemeId);
												FundSchemeVO schemeVO = new FundSchemeVO();					
												schemeVO.setId(tempSchemeVO.getId());
												schemeVO.setName(tempSchemeVO.getName());
												schemeVO.setCount(0L);
												schemeVO.setTotalCount(0L);
												deptVO.getSubList().add(schemeVO);
											}
										}
									}
								}
							}
						}
										
					}else if(!commonMethodsUtilService.isListOrSetValid(fundLocationVO.getSubList())){
						for (Long yearId : yearsMap.keySet()) {
								FundSchemeVO tempYearVO = yearsMap.get(yearId);
								
								FundSchemeVO yearVO = new FundSchemeVO();
								yearVO.setYearId(tempYearVO.getYearId());
								yearVO.setYear(tempYearVO.getYear());
								
								
								for (Long deptId : deptsMap.keySet()) {
									FundSchemeVO tempDeptVO = deptsMap.get(deptId);
									FundSchemeVO deptsVO = new FundSchemeVO();					
									deptsVO.setId(tempDeptVO.getId());
									deptsVO.setName(tempDeptVO.getName());
									
									for (Long schemeId : schemesMap.keySet()) {
										FundSchemeVO tempSchemeVO = schemesMap.get(schemeId);
										FundSchemeVO schemeVO = new FundSchemeVO();					
										schemeVO.setId(tempSchemeVO.getId());
										schemeVO.setName(tempSchemeVO.getName());
										schemeVO.setCount(0L);
										schemeVO.setTotalCount(0L);
										deptsVO.getSubList().add(schemeVO);
									}
									
									deptsVO.setYearId(tempYearVO.getYearId());
									deptsVO.setYear(tempYearVO.getYear());
									deptsVO.setAddressVO(fundLocationVO.getAddressVO());
									yearVO.getSubList().add(deptsVO);
								}
								
								fundLocationVO.getSubList().add(yearVO);
						}
					}
					returnList.add(fundLocationVO);
				}
			}
			
		} catch (Exception e) {
			LOG.error(" Exception occured in FundManagementDashboardService ,getFinancialYearWiseDeptsWiseSchemeDetails() ",e);
		}
		return returnList;
	}
	*/
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
			
			if(inputVO.getLocationId() == null || inputVO.getLocationId().longValue() == 0L){
				levelValues = fundSanctionDAO.getLocationValues(inputVO.getBlockLevelId());
			}else{
				//get the locationId here 
				String locationIdStr = inputVO.getLocationId().toString();
				String locationLevelIdStr = locationIdStr.substring(0, 1);
				locationIdStr = locationIdStr.substring(1);
				Long locationId = Long.parseLong(locationIdStr);
				Long locationLevelId = Long.parseLong(locationLevelIdStr);
				levelValues = fundSanctionDAO.getLocationBlockLevelIds(locationId,locationLevelId,inputVO.getBlockLevelId());
			}
			List<Object[]> amountList = null;
			List<Object[]> locationInfoList = null;
			
			List<Long> financialYearIdsList = commonMethodsUtilService.makeEmptyListByZeroValue(inputVO.getFinancialYrIdList());
			List<Long> deptIdsList = commonMethodsUtilService.makeEmptyListByZeroValue(inputVO.getDeptIdsList());
			List<Long> sourceIdsList = commonMethodsUtilService.makeEmptyListByZeroValue(inputVO.getSourceIdsList());
			
			if(levelValues != null && levelValues.size() > 0){//[1, 2014-2015, 109, 5, 19520000]
				amountList = fundSanctionDAO.getLocationWiseAmount(levelId,levelValues,fromDate,toDate,financialYearIdsList,deptIdsList,sourceIdsList);
				if(levelId != null && levelId.longValue() > IConstants.STATE_LEVEL_SCOPE_ID)
				locationInfoList = fundSanctionDAO.getLocationInfo(levelId, levelValues);
			}
			
			//collect all the location ids(uses to create the final list)
			Set<Long> locationIdList = new HashSet<Long>();
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
			if(levelId != null && levelId.longValue() == IConstants.STATE_LEVEL_SCOPE_ID){
				locationIdAndNameMap.put(1L, "Andhra Pradesh");
			}
			
			//create a map for financialYearId and financialyear
			Map<Long,String> financialYearIdAndFinancialYearMap = new HashMap<Long,String>();
			List<Object[]> financialYearList = financialYearDAO.getAllFiniancialYearsByIds(inputVO.getFinancialYrIdList());
			if(financialYearList != null && financialYearList.size() > 0){
				for(Object[] param : financialYearList){
					financialYearIdAndFinancialYearMap.put(commonMethodsUtilService.getLongValueForObject(param[0]), commonMethodsUtilService.getStringValueForObject(param[1]));
				}
			}
			
			
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
				returnVO.setTotalAmt(commonMethodsUtilService.getStringValueForObject(obj[0]));
				returnVO.setId(commonMethodsUtilService.getLongValueForObject(obj[1]));
				returnVO.setName(commonMethodsUtilService.getStringValueForObject(obj[2]));
				returnVO.setType(type);
				if(totalfund != null && totalfund.longValue() >0l)
				returnVO.setPerc(commonMethodsUtilService.calculatePercantage(Long.valueOf(returnVO.getTotalAmt().toString()),totalfund));
			
			
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
					grantVO.setTotal(obj[0] != null ? Double.valueOf(obj[0].toString()) : 0l);
					grantVO.setId(commonMethodsUtilService.getLongValueForObject(obj[1]));
					grantVO.setName(commonMethodsUtilService.getStringValueForObject(obj[2]));
					
					if(grantVO.getTotal() != null && grantVO.getTotal().longValue() >0l)
						grantVO.setPercentage(commonMethodsUtilService.calculatePercantage(grantVO.getTotal().longValue(),Long.valueOf(returnVO.getTotalAmt().toString())));
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
			retusnVo.setTotalAmt(totalfund.toString());
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
		
		inputVO.setFinancialYrIdList(commonMethodsUtilService.makeEmptyListByZeroValue(inputVO.getFinancialYrIdList()));
		inputVO.setDeptIdsList(commonMethodsUtilService.makeEmptyListByZeroValue(inputVO.getDeptIdsList()));
		inputVO.setSourceIdsList(commonMethodsUtilService.makeEmptyListByZeroValue(inputVO.getSourceIdsList()));
		inputVO.setSearchLvlVals(commonMethodsUtilService.makeEmptyListByZeroValue(inputVO.getSearchLvlVals()));
		
		 List<Object[]>  locations= fundSanctionDAO.getLocationsCountDetails(inputVO.getBlockLevelId(),inputVO.getFinancialYrIdList(),inputVO.getDeptIdsList(),inputVO.getSourceIdsList(),inputVO.getSearchLevelId(),inputVO.getSearchLvlVals());
		 
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
			
			Date sDate = commonMethodsUtilService.stringTODateConvertion(inputVO.getFromDateStr(),"MM/dd/yyyy","");
			Date eDate = commonMethodsUtilService.stringTODateConvertion(inputVO.getToDateStr(),"MM/dd/yyyy","");
			
			inputVO.setFinancialYrIdList(commonMethodsUtilService.makeEmptyListByZeroValue(inputVO.getFinancialYrIdList()));
			List<Long> deptIdsList = commonMethodsUtilService.makeEmptyListByZeroValue(inputVO.getDeptIdsList());
			List<Long> sourceIdsList = commonMethodsUtilService.makeEmptyListByZeroValue(inputVO.getSourceIdsList());
			//scope wise count and amount dtls
			List<Object[]> totalFundAndCountDtls= fundSanctionDAO.getTotalFundAndCountDtls(inputVO.getFinancialYrIdList(),deptIdsList,sourceIdsList,sDate,eDate,inputVO.getBlockLevelId(),"one");
			if(totalFundAndCountDtls != null && totalFundAndCountDtls.size() > 0){
				retusnVo.setTotalAmt(commonMethodsUtilService.getStringValueForObject(totalFundAndCountDtls.get(0)[1]));
				//retusnVo.setAverageAmt((Double.valueOf(commonMethodsUtilService.getStringValueForObject(totalFundAndCountDtls.get(0)[1]))/Double.valueOf(commonMethodsUtilService.getStringValueForObject(totalFundAndCountDtls.get(0)[0]))));
				
				if(inputVO.getBlockLevelId() != null && inputVO.getBlockLevelId().longValue() == IConstants.DISTRICT_LEVEL_SCOPE_ID)
					retusnVo.setAverageAmt((Double.valueOf(commonMethodsUtilService.getStringValueForObject(totalFundAndCountDtls.get(0)[1]))/IConstants.TOTAL_AP_TOTAL_DISTRICTS));
				else if(inputVO.getBlockLevelId() != null && inputVO.getBlockLevelId().longValue() == IConstants.CONSTITUENCY_LEVEL_SCOPE_ID)
					retusnVo.setAverageAmt((Double.valueOf(commonMethodsUtilService.getStringValueForObject(totalFundAndCountDtls.get(0)[1]))/IConstants.TOTAL_AP_TOTAL_CONSTITUENCIES));
				else if(inputVO.getBlockLevelId() != null && inputVO.getBlockLevelId().longValue() == IConstants.MANDAL_LEVEL_SCOPE_ID)
					retusnVo.setAverageAmt((Double.valueOf(commonMethodsUtilService.getStringValueForObject(totalFundAndCountDtls.get(0)[1]))/IConstants.TOTAL_AP_TOTAL_MANDALS));
				else if(inputVO.getBlockLevelId() != null && inputVO.getBlockLevelId().longValue() == IConstants.VILLAGE_LEVEL_SCOPE_ID)
					retusnVo.setAverageAmt((Double.valueOf(commonMethodsUtilService.getStringValueForObject(totalFundAndCountDtls.get(0)[1]))/IConstants.TOTAL_AP_TOTAL_VILLAGES));
				
				retusnVo.setPerc(commonMethodsUtilService.calculatePercantage(retusnVo.getAverageAmt().longValue(),Long.parseLong(retusnVo.getTotalAmt())));
				
			}//[2, 1900000]
			List<Object[]> grantTypeDtlsList = grantTypeDAO.getGrandTypeDtls();
			setGrantTypeToVo(retusnVo,grantTypeDtlsList);
			//grant wise then scope wise count and amount dtls
			List<Object[]> totalFundAndCountGrantWiseList = fundSanctionDAO.getTotalFundAndCountDtls(inputVO.getFinancialYrIdList(),deptIdsList,sourceIdsList,sDate,eDate,inputVO.getBlockLevelId(),"two");
			if(totalFundAndCountGrantWiseList != null && totalFundAndCountGrantWiseList.size() > 0){
				for(Object[] param : totalFundAndCountGrantWiseList){
					fundDetailsVO = (LocationFundDetailsVO)setterAndGetterUtilService.getMatchedVOfromList(retusnVo.getDetailsVOs(), "id", commonMethodsUtilService.getStringValueForObject(param[0]));
					fundDetailsVO.setTotalAmt(commonMethodsUtilService.getStringValueForObject(param[3]));
					//retusnVo.setAverageAmt((Double.valueOf(commonMethodsUtilService.getStringValueForObject(totalFundAndCountDtls.get(0)[1]))/Double.valueOf(commonMethodsUtilService.getStringValueForObject(totalFundAndCountDtls.get(0)[0]))));
					
					if(inputVO.getBlockLevelId() != null && inputVO.getBlockLevelId().longValue() == IConstants.DISTRICT_LEVEL_SCOPE_ID)
						fundDetailsVO.setAverageAmt((Double.valueOf(commonMethodsUtilService.getStringValueForObject(param[3]))/IConstants.TOTAL_AP_TOTAL_DISTRICTS));
					else if(inputVO.getBlockLevelId() != null && inputVO.getBlockLevelId().longValue() == IConstants.CONSTITUENCY_LEVEL_SCOPE_ID)
						fundDetailsVO.setAverageAmt((Double.valueOf(commonMethodsUtilService.getStringValueForObject(param[3]))/IConstants.TOTAL_AP_TOTAL_CONSTITUENCIES));
					else if(inputVO.getBlockLevelId() != null && inputVO.getBlockLevelId().longValue() == IConstants.MANDAL_LEVEL_SCOPE_ID)
						fundDetailsVO.setAverageAmt((Double.valueOf(commonMethodsUtilService.getStringValueForObject(param[3]))/IConstants.TOTAL_AP_TOTAL_MANDALS));
					else if(inputVO.getBlockLevelId() != null && inputVO.getBlockLevelId().longValue() == IConstants.VILLAGE_LEVEL_SCOPE_ID)
						fundDetailsVO.setAverageAmt((Double.valueOf(commonMethodsUtilService.getStringValueForObject(param[3]))/IConstants.TOTAL_AP_TOTAL_VILLAGES));
					
					fundDetailsVO.setPerc(commonMethodsUtilService.calculatePercantage(fundDetailsVO.getAverageAmt().longValue(),Long.parseLong(fundDetailsVO.getTotalAmt())));
					
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
			
			inputVO.setFinancialYrIdList(commonMethodsUtilService.makeEmptyListByZeroValue(inputVO.getFinancialYrIdList()));
			List<Long> deptIdsList = commonMethodsUtilService.makeEmptyListByZeroValue(inputVO.getDeptIdsList());
			List<Long> sourceIdsList = commonMethodsUtilService.makeEmptyListByZeroValue(inputVO.getSourceIdsList());
			List<Long> schemeIdsList = commonMethodsUtilService.makeEmptyListByZeroValue(inputVO.getSchemeIdsList());
			List<Object[]> totalFundForSchemeList = fundSanctionDAO.getTotalFundForScheme(inputVO.getFinancialYrIdList(),deptIdsList,sourceIdsList,schemeIdsList,sDate,eDate);
			
			if(totalFundForSchemeList != null && totalFundForSchemeList.size() > 0){
				if(!commonMethodsUtilService.getStringValueForObject(totalFundForSchemeList.get(0)[1]).equalsIgnoreCase("0")){
					retusnVo.setTotalAmt(commonMethodsUtilService.getStringValueForObject(totalFundForSchemeList.get(0)[1]));
					retusnVo.setAverageAmt((Double.valueOf(commonMethodsUtilService.getStringValueForObject(totalFundForSchemeList.get(0)[1]))/Double.valueOf(commonMethodsUtilService.getStringValueForObject(totalFundForSchemeList.get(0)[0]))));
					retusnVo.setPerc(commonMethodsUtilService.calculatePercantage(retusnVo.getAverageAmt().longValue(),Long.parseLong(retusnVo.getTotalAmt())));
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
				locationList = fundSanctionDAO.getAllDistrictByStateId(superLocationId,inputVO.getFinancialYrIdList(),deptIdsList,sourceIdsList,IConstants.DISTRICT_LEVEL_SCOPE_ID,sDate,eDate);
				lvlIdStr = IConstants.DISTRICT_LEVEL_SCOPE_ID.toString();
			}else if(superLocationLevelId != null && superLocationLevelId.longValue() == IConstants.DISTRICT_LEVEL_SCOPE_ID){//get constituencyIds
				locationList = fundSanctionDAO.getAllConstituencyByDistrictId(superLocationId,inputVO.getFinancialYrIdList(),deptIdsList,sourceIdsList,IConstants.CONSTITUENCY_LEVEL_SCOPE_ID,sDate,eDate);
				lvlIdStr = IConstants.CONSTITUENCY_LEVEL_SCOPE_ID.toString();
			}else if(superLocationLevelId != null && superLocationLevelId.longValue() == IConstants.CONSTITUENCY_LEVEL_SCOPE_ID){//get tehsilIds
				
			}else if(superLocationLevelId != null && superLocationLevelId.longValue() == IConstants.MANDAL_LEVEL_SCOPE_ID){//get panchayatIds
				
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

}
