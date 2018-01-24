package com.itgrids.service.impl;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeSet;
import java.util.Map.Entry;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.Set;
import java.util.TreeMap;

import org.apache.log4j.Logger;
import org.codehaus.jettison.json.JSONArray;
import org.codehaus.jettison.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.itgrids.dao.IConstituencyDAO;
import com.itgrids.dao.IDepartmentDAO;
import com.itgrids.dao.IDistrictDAO;
import com.itgrids.dao.IFinancialYearDAO;
import com.itgrids.dao.IFundSanctionDAO;
import com.itgrids.dao.IFundSanctionLocationDAO;
import com.itgrids.dao.IFundSanctionMatrixDetailsDAO;
import com.itgrids.dao.IFundSanctionMatrixRangeDAO;
import com.itgrids.dao.IGovtSchemeDAO;
import com.itgrids.dao.IGrantTypeDAO;
import com.itgrids.dao.IPageComponentDAO;
import com.itgrids.dao.IPanchayatDAO;
import com.itgrids.dao.IParliamentAssemblyDAO;
import com.itgrids.dao.IPrTehsilDAO;
import com.itgrids.dao.ISubProgramDAO;
import com.itgrids.dao.ITehsilConstituencyDAO;
import com.itgrids.dao.ITehsilDAO;
import com.itgrids.dao.impl.PageComponentDAO;
import com.itgrids.dto.AddressVO;
import com.itgrids.dto.FundMatrixVO;
import com.itgrids.dto.FundSchemeVO;
import com.itgrids.dto.FundVO;
import com.itgrids.dto.IdNameVO;
import com.itgrids.dto.InputVO;
import com.itgrids.dto.LocationFundDetailsVO;
import com.itgrids.dto.LocationVO;
import com.itgrids.dto.NregsFmsWorksVO;
import com.itgrids.dto.PageComponentVO;
import com.itgrids.dto.RangeVO;
import com.itgrids.dto.ResultVO;
import com.itgrids.model.FavouriteComponent;
import com.itgrids.model.GovtScheme;
import com.itgrids.model.GrantType;
import com.itgrids.model.PageComponent;
import com.itgrids.service.IFundManagementDashboardService;
import com.itgrids.service.integration.external.WebServiceUtilService;
import com.itgrids.utils.CommonMethodsUtilService;
import com.itgrids.utils.IConstants;
import com.itgrids.utils.NREGSCumulativeThread;
import com.itgrids.utils.SetterAndGetterUtilService;
import com.sun.jersey.api.client.ClientResponse;
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
	@Autowired
	private IFundSanctionLocationDAO fundSanctionLocationDAO;
	@Autowired
	private ITehsilDAO tehsilDAO;  
	@Autowired
	private IPanchayatDAO panchayatDAO; 
	@Autowired
	private ITehsilConstituencyDAO tehsilConstituencyDAO;
	@Autowired
	private IParliamentAssemblyDAO parliamentAssemblyDAO;
	@Autowired
	private IGovtSchemeDAO govtSchemeDAO;
	@Autowired
	private ISubProgramDAO subProgramDAO;
	@Autowired
	private WebServiceUtilService webServiceUtilService;
	@Autowired
	private IPrTehsilDAO prTehsilDAO;
	@Autowired
	private IPageComponentDAO pageComponentDAO;
	
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
	
	public List<FundSchemeVO> getFinancialYearWiseSchemeDetails(List<Long> financialYearIdsList,List<Long> deptIdsList,																	//BlockLevelId
			List<Long> sourceIdsList,List<Long> schemeIdsList,String startDateStr,String endDateStr,Long searchScopeId,List<Long> searchScopeValuesList,String order,String sortingType,Long searchLevelId,
			List<Long> govtSchmeIdsList,List<Long> subProgramIdsList,Long glSearchLevelId,List<Long> glSearchLevelValue,String viewType, List<Long> grantTypeIdsList,InputVO inputVO){
		List<FundSchemeVO> returnList = new ArrayList<FundSchemeVO>(0);
		Map<Long,FundSchemeVO> schemesMap = new HashMap<Long,FundSchemeVO>(0);
		try {
			Date startDate = commonMethodsUtilService.stringTODateConvertion(startDateStr,"dd/MM/yyyy","");
			Date endDate = commonMethodsUtilService.stringTODateConvertion(endDateStr,"dd/MM/yyyy","");
			
			financialYearIdsList = commonMethodsUtilService.makeEmptyListByZeroValue(financialYearIdsList);
			deptIdsList = commonMethodsUtilService.makeEmptyListByZeroValue(deptIdsList);
			sourceIdsList = commonMethodsUtilService.makeEmptyListByZeroValue(sourceIdsList);
			schemeIdsList = commonMethodsUtilService.makeEmptyListByZeroValue(schemeIdsList);
			searchScopeValuesList = commonMethodsUtilService.makeEmptyListByZeroValue(searchScopeValuesList);
			
			List<Object[]> result =  fundSanctionDAO.getFinancialYearWiseScheameDetails(financialYearIdsList,deptIdsList,sourceIdsList, schemeIdsList,startDate,endDate,searchScopeId,searchScopeValuesList,searchLevelId,govtSchmeIdsList,subProgramIdsList,glSearchLevelId,glSearchLevelValue, grantTypeIdsList);
			Map<Long,FundSchemeVO> locationMap = new HashMap<Long,FundSchemeVO>(0);
			Map<Long,FundSchemeVO> yearsMap = new TreeMap<Long,FundSchemeVO>();
			//Map<Long,FundSchemeVO> deptsMap = new HashMap<Long,FundSchemeVO>(0);
			
			if(commonMethodsUtilService.isListOrSetValid(result)){
				
				for (Object[] param : result) {
					if(param[6] == null){
						continue;
					}
					
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
					if(param[6] == null){
						continue;
					}
					FundSchemeVO fundLocationVO = new FundSchemeVO();
					
					AddressVO addressVO = new AddressVO();
					addressVO.setStateId(commonMethodsUtilService.getLongValueForObject(param[8]));
					addressVO.setStateName(commonMethodsUtilService.getStringValueForObject(param[9]));
					addressVO.setDistrictId(commonMethodsUtilService.getLongValueForObject(param[10]));
					addressVO.setDistrictName(commonMethodsUtilService.getStringValueForObject(param[11]));
					addressVO.setAssemblyId(commonMethodsUtilService.getLongValueForObject(param[12]));
					addressVO.setAssemblyName(commonMethodsUtilService.getStringValueForObject(param[13]));
					addressVO.setTehsilId(commonMethodsUtilService.getLongValueForObject(param[14]));
					addressVO.setTehsilName(commonMethodsUtilService.getStringValueForObject(param[15]));
					addressVO.setPanchayatId(commonMethodsUtilService.getLongValueForObject(param[16]));
					addressVO.setPanchayatName(commonMethodsUtilService.getStringValueForObject(param[17]));
					addressVO.setParliamentId(commonMethodsUtilService.getLongValueForObject(param[18]));
					addressVO.setParliamentName(commonMethodsUtilService.getStringValueForObject(param[19]));
					
					if(searchScopeId != null && searchScopeId.longValue() == IConstants.STATE_LEVEL_SCOPE_ID){						
						addressVO.setId(addressVO.getStateId());
						addressVO.setName(addressVO.getStateName());
					}else if(searchLevelId != null && searchLevelId.longValue() == IConstants.DISTRICT_LEVEL_SCOPE_ID){
						addressVO.setId(addressVO.getDistrictId());
						addressVO.setName(addressVO.getDistrictName());
					}else if(searchLevelId != null && searchLevelId.longValue() == IConstants.CONSTITUENCY_LEVEL_SCOPE_ID){
						addressVO.setId(addressVO.getAssemblyId());
						addressVO.setName(addressVO.getAssemblyName());
					}else if(searchLevelId != null && searchLevelId.longValue() == IConstants.TEMP_PARLIAMENT_CONSTITUENCY_LEVEL_SCOPE_ID){
						addressVO.setId(addressVO.getParliamentId());
						addressVO.setName(addressVO.getParliamentName());
					}else if(searchLevelId != null && searchLevelId.longValue() == IConstants.MANDAL_LEVEL_SCOPE_ID){						
						addressVO.setId(addressVO.getTehsilId());
						addressVO.setName(addressVO.getTehsilName());
					}
					else if(searchLevelId != null && searchLevelId.longValue() == IConstants.VILLAGE_LEVEL_SCOPE_ID){
						addressVO.setId(addressVO.getPanchayatId());
						addressVO.setName(addressVO.getPanchayatName());
					}
					
					Long keyId=addressVO.getId();
					
					if(locationMap.get(keyId) != null){
						fundLocationVO = locationMap.get(keyId);
						if(fundLocationVO.getTotalCount() != null && fundLocationVO.getTotalCount().longValue()>0L)
							fundLocationVO.setTotalCount(fundLocationVO.getTotalCount()+commonMethodsUtilService.getLongValueForObject(param[5]));
						else
							fundLocationVO.setTotalCount(commonMethodsUtilService.getLongValueForObject(param[5]));
						fundLocationVO.setAmount(commonMethodsUtilService.calculateAmountInWords(fundLocationVO.getTotalCount()));
					}else{
						List<FundSchemeVO> yearsList = buildData(yearsMap,schemesMap,null);
						fundLocationVO.setSubList(yearsList);
						fundLocationVO.setTotalCount(commonMethodsUtilService.getLongValueForObject(param[5]));
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
							schemeVO.setAmount(commonMethodsUtilService.calculateAmountInWords(schemeVO.getTotalCount()));
	
						schemeVO.setAddressVO(addressVO);							
						yearVO.setAddressVO(addressVO);
						yearVO.getSubList().add(schemeVO);

						if(yearVO.getTotalCount() != null && yearVO.getTotalCount().longValue()>0L)
							yearVO.setTotalCount(yearVO.getTotalCount()+commonMethodsUtilService.getLongValueForObject(param[5]));
						else
							yearVO.setTotalCount(commonMethodsUtilService.getLongValueForObject(param[5]));
						yearVO.setAmount(commonMethodsUtilService.calculateAmountInWords(yearVO.getTotalCount()));
						
						fundLocationVO.getSubList().add(yearVO);
						
					}else{
						
						if(yearVO.getTotalCount() != null && yearVO.getTotalCount().longValue()>0L)
							yearVO.setTotalCount(yearVO.getTotalCount()+commonMethodsUtilService.getLongValueForObject(param[5]));
						else
							yearVO.setTotalCount(commonMethodsUtilService.getLongValueForObject(param[5]));
						yearVO.setAmount(commonMethodsUtilService.calculateAmountInWords(yearVO.getTotalCount()));
						
						FundSchemeVO schemeVO = (FundSchemeVO) setterAndGetterUtilService.getMatchedVOfromList(yearVO.getSubList(), "id", commonMethodsUtilService.getLongValueForObject(param[0]).toString());
						if(schemeVO == null){
							schemeVO = new FundSchemeVO();					
							schemeVO.setId(commonMethodsUtilService.getLongValueForObject(param[0]));
							schemeVO.setName(commonMethodsUtilService.getStringValueForObject(param[1]));
							schemeVO.setCount(commonMethodsUtilService.getLongValueForObject(param[4]));
							schemeVO.setTotalCount(commonMethodsUtilService.getLongValueForObject(param[5]));
							
							schemeVO.setAmount(commonMethodsUtilService.calculateAmountInWords(schemeVO.getTotalCount()));
							
							schemeVO.setAddressVO(addressVO);
							yearVO.setAddressVO(addressVO);							
							yearVO.getSubList().add(schemeVO);
						}
						else{
							schemeVO.setCount(schemeVO.getCount()+commonMethodsUtilService.getLongValueForObject(param[4]));
							schemeVO.setTotalCount(schemeVO.getTotalCount()+commonMethodsUtilService.getLongValueForObject(param[5]));
							
							schemeVO.setAmount(commonMethodsUtilService.calculateAmountInWords(schemeVO.getTotalCount()));
							
							schemeVO.setAddressVO(addressVO);
							yearVO.setAddressVO(addressVO);
						}
					}
					fundLocationVO.setAddressVO(addressVO);
					locationMap.put(keyId, fundLocationVO);
				}
			}
			
			setNonFundedLocations( locationMap , searchLevelId, yearsMap, schemesMap,null,searchScopeId,searchScopeValuesList);
			
			if(commonMethodsUtilService.isMapValid(locationMap)){
				returnList.addAll(locationMap.values());
			}
			
		} catch (Exception e) {
			LOG.error(" Exception occured in FundManagementDashboardService ,getFinancialYearWiseScheameDetails() ",e);
		}
		if(viewType != null && viewType.trim().equalsIgnoreCase("cumulative")){
			if(returnList != null && returnList.size() > 0){
				returnList = prepareCumulativeView(returnList);
				returnList = updateAmountAndCount(returnList);
				
			}
		}else{
			//financial year wise update amount and count.
			updateAmountAndCountForYearWise(returnList);
		}
		 /* MEARGINING MGNREGS DATA LOCATION WISE START */
	     mergeMgnregsLocationWiseData(returnList,inputVO,schemesMap);
	  /* END */
	     try {
			  if(commonMethodsUtilService.isListOrSetValid(returnList)){
					//returnList.addAll(locationMap.values());
					
					if(returnList != null && returnList.size() > 0){
						if(sortingType.trim().equalsIgnoreCase("name")){
							if(order.trim().equalsIgnoreCase("asc")){
								Collections.sort(returnList, nameWiseAscendingOrder);
							}else{
								Collections.sort(returnList, nameWiseDescendingOrder);
							}
						}else if(sortingType.trim().equalsIgnoreCase("count")){
							if(order.trim().equalsIgnoreCase("asc")){
								Collections.sort(returnList, amountWiseAscendingOrder);
							}else{
								Collections.sort(returnList, amountWiseDescendingOrder);
							}
						}
					}
				}
		  } catch (Exception e ){
			  LOG.error(" Exception occured in FundManagementDashboardService ,getFinancialYearWiseScheameDetails() ",e);
		  }
		return returnList;
	}
	
	public List<FundSchemeVO> prepareCumulativeView(List<FundSchemeVO> inputList){
		try{
			List<FundSchemeVO> finalList = new ArrayList<FundSchemeVO>();
			List<FundSchemeVO> tempList = null;
			List<FundSchemeVO> innerList = null;
			FundSchemeVO fundSchemeVO = null;
			FundSchemeVO middlefundSchemeVO = null;
			FundSchemeVO innerfundSchemeVO = null;
			for(FundSchemeVO param : inputList){
				innerList = new ArrayList<FundSchemeVO>();
				fundSchemeVO = new FundSchemeVO();
				fundSchemeVO.setAddressVO(param.getAddressVO());
				fundSchemeVO.setTotalCount(param.getTotalCount());
				fundSchemeVO.setAmount(param.getAmount());
				// create custom year range e.i if year range is like this 14-15,15-16,16-17,17-18 
				// then take the first part of first pair and second part of last pair and create the year range as 14-18
				tempList = param.getSubList();
				if(tempList != null && tempList.size() > 0){
					String[] yearArrFirst = tempList.get(0).getYear().split("-");
					String[] yearArrLast = tempList.get(tempList.size()-1).getYear().split("-");
					middlefundSchemeVO = new FundSchemeVO();
					middlefundSchemeVO.setYearId(0L);
					middlefundSchemeVO.setYear(yearArrFirst[0]+"-"+yearArrLast[1]);
					middlefundSchemeVO.setAddressVO(param.getAddressVO());
					
					if(tempList.get(0) != null && tempList.get(0).getSubList() != null && tempList.get(0).getSubList().size() > 0){
						for(FundSchemeVO innerParam : tempList.get(0).getSubList()){
							innerfundSchemeVO = new FundSchemeVO();
							innerfundSchemeVO.setId(innerParam.getId());
							innerfundSchemeVO.setName(innerParam.getName());
							innerfundSchemeVO.setCount(0L);
							innerfundSchemeVO.setAmount("0.0");
							innerList.add(innerfundSchemeVO);
						}
					}
					
					double amountTotal = 0.0d;
					for(FundSchemeVO middleParam : tempList){
						middlefundSchemeVO.setTotalCount(middlefundSchemeVO.getTotalCount() + middleParam.getTotalCount());
						//String amountStr = middleParam.getAmount();
						//double amountDouble = Double.parseDouble(amountStr);
						double amountDouble = Double.parseDouble(middleParam.getTotalCount().toString());
						amountTotal = amountTotal + amountDouble;
						if(middleParam != null && middleParam.getSubList() != null && middleParam.getSubList().size() > 0){
							for(FundSchemeVO innerParam : middleParam.getSubList()){
								updateInnerList(innerList,innerParam);
							}
						}
					}
					middlefundSchemeVO.setAmount(new Double(amountTotal).toString());
					middlefundSchemeVO.setSubList(innerList);
					fundSchemeVO.getSubList().add(middlefundSchemeVO);
				}
				finalList.add(fundSchemeVO);
			}
			return finalList;
		}catch(Exception e){
			LOG.error(" Exception occured in FundManagementDashboardService ,prepareCumulativeView() ",e);
		}
		return null;
	}
	public void updateInnerList(List<FundSchemeVO> innerList,FundSchemeVO innerParam){
		try{
			FundSchemeVO fundSchemeVO = (FundSchemeVO) setterAndGetterUtilService.getMatchedVOfromList(innerList, "id", innerParam.getId().toString());
			if(fundSchemeVO != null){
				fundSchemeVO.setCount(fundSchemeVO.getCount()+innerParam.getCount());
				fundSchemeVO.setTotalCount(fundSchemeVO.getTotalCount()+innerParam.getTotalCount());
				String amountStr1 = fundSchemeVO.getAmount();
				double amountDouble1 = Double.parseDouble(amountStr1);
				String amountStr2 = innerParam.getAmount();
				double amountDouble2 = Double.parseDouble(amountStr2);
				fundSchemeVO.setAmount(commonMethodsUtilService.roundUptoTwoDecimalPoint(new Double(amountDouble1+amountDouble2)).toString());
			}
		}catch(Exception e){
			LOG.error(" Exception occured in FundManagementDashboardService ,updateInnerList() ",e);
		}
	}
	public List<FundSchemeVO> updateAmountAndCount(List<FundSchemeVO> inputList){
		try{
			List<FundSchemeVO> returnList = new ArrayList<FundSchemeVO>();
			for(FundSchemeVO param : inputList){
				param.setCount(0L);
				param.setAmount("0.0");
				if(param != null && param.getSubList() != null && param.getSubList().size() > 0 && param.getSubList().get(0) != null && param.getSubList().get(0).getSubList() != null && param.getSubList().get(0).getSubList().size() > 0){
					for(FundSchemeVO innerParam : param.getSubList().get(0).getSubList()){
						param.setCount(param.getCount()+innerParam.getCount());
						String amountStr1 = param.getAmount();
						double amountDouble1 = Double.parseDouble(amountStr1);
						String amountStr2 = innerParam.getAmount();
						double amountDouble2 = Double.parseDouble(amountStr2);
						param.setAmount(commonMethodsUtilService.roundUptoTwoDecimalPoint(new Double(amountDouble1+amountDouble2)).toString());
					}
				}
				if(param.getCount().longValue() != 0L){
					returnList.add(param);
				}
			}
			return returnList;
		}catch(Exception e){
			LOG.error(" Exception occured in FundManagementDashboardService ,updateAmountAndCount() ",e);
		}
		return null;
	}
	public void updateAmountAndCountForYearWise(List<FundSchemeVO> inputList){
		try{
			//List<FundSchemeVO> returnList = new ArrayList<FundSchemeVO>();
			for(FundSchemeVO param : inputList){
				if(param != null && param.getSubList() != null && param.getSubList().size() > 0 ){
					for(FundSchemeVO midParam : param.getSubList()){
						midParam.setCount(0L);
						midParam.setAmount("0.0");
						if(midParam != null && midParam.getSubList() != null && midParam.getSubList().size() > 0 ){
							for(FundSchemeVO innerParam : midParam.getSubList()){
								midParam.setCount(midParam.getCount()+innerParam.getCount());
								String amountStr1 = midParam.getAmount();
								double amountDouble1 = Double.parseDouble(amountStr1);
								String amountStr2 = innerParam.getAmount();
								double amountDouble2 = Double.parseDouble(amountStr2);
								midParam.setAmount(commonMethodsUtilService.roundUptoTwoDecimalPoint(new Double(amountDouble1+amountDouble2)).toString());
							}
						}
					}
				}
				/*if(param.getCount().longValue() != 0L){
					returnList.add(param);
				}*/
			}
			//return inputList;
		}catch(Exception e){
			LOG.error(" Exception occured in FundManagementDashboardService ,updateAmountAndCount() ",e);
		}
		//return null;
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
		public int compare(FundSchemeVO o1, FundSchemeVO o2) {
			if(o1.getTotalCount() != null && o2.getTotalCount() != null){
				return o1.getTotalCount().compareTo(o2.getTotalCount());
			}
			return 0;
		}
	};
	
	public static Comparator<FundSchemeVO> amountWiseDescendingOrder = new Comparator<FundSchemeVO>() {
		public int compare(FundSchemeVO o1, FundSchemeVO o2) {
		 try{
			if(o2.getTotalCount() != null && o1.getTotalCount() != null){
				return o2.getTotalCount().compareTo(o1.getTotalCount());
			}
		  }catch(Exception e){
			  LOG.error(" Exception occured in amountWiseDescendingOrder ",e);
			}
			return 0;
		}
	};
	
	public void setNonFundedLocations(Map<Long,FundSchemeVO> locationMap ,Long searchLevelId,Map<Long,FundSchemeVO> yearsMap,Map<Long,FundSchemeVO> schemesMap,Map<Long,FundSchemeVO> deptsMap,
			Long searchScopeId,List<Long> searchScopeValuesList){
		
		try{
			Set<Long> keysList = locationMap.keySet();
			//if(commonMethodsUtilService.isListOrSetValid(keysList)){
				List<Object[]> notFundedLocs = tehsilConstituencyDAO.getNonFundedLocations(keysList,searchLevelId,searchScopeId,searchScopeValuesList);
				
				for(Object[] param :notFundedLocs){
					
					AddressVO addressVO = new AddressVO();
					
					if(searchLevelId != null && searchLevelId.longValue() == IConstants.STATE_LEVEL_SCOPE_ID){
						addressVO.setStateId(commonMethodsUtilService.getLongValueForObject(param[0]));
						addressVO.setStateName(commonMethodsUtilService.getStringValueForObject(param[1]));
						
						addressVO.setId(addressVO.getStateId());
						addressVO.setName(addressVO.getStateName());
					}else if(searchLevelId != null && searchLevelId.longValue() == IConstants.DISTRICT_LEVEL_SCOPE_ID){
						addressVO.setDistrictId(commonMethodsUtilService.getLongValueForObject(param[2]));
						addressVO.setDistrictName(commonMethodsUtilService.getStringValueForObject(param[3]));
						
						addressVO.setStateId(commonMethodsUtilService.getLongValueForObject(param[0]));
						addressVO.setStateName(commonMethodsUtilService.getStringValueForObject(param[1]));
						
						addressVO.setId(addressVO.getDistrictId());
						addressVO.setName(addressVO.getDistrictName());
					}else if(searchLevelId != null && searchLevelId.longValue() == IConstants.CONSTITUENCY_LEVEL_SCOPE_ID){
						addressVO.setAssemblyId(commonMethodsUtilService.getLongValueForObject(param[6]));
						addressVO.setAssemblyName(commonMethodsUtilService.getStringValueForObject(param[7]));
						
						addressVO.setStateId(commonMethodsUtilService.getLongValueForObject(param[0]));
						addressVO.setStateName(commonMethodsUtilService.getStringValueForObject(param[1]));
						addressVO.setDistrictId(commonMethodsUtilService.getLongValueForObject(param[2]));
						addressVO.setDistrictName(commonMethodsUtilService.getStringValueForObject(param[3]));
						addressVO.setParliamentId(commonMethodsUtilService.getLongValueForObject(param[4]));
						addressVO.setParliamentName(commonMethodsUtilService.getStringValueForObject(param[5]));
						
						addressVO.setId(addressVO.getAssemblyId());
						addressVO.setName(addressVO.getAssemblyName());
					}else if(searchLevelId != null && searchLevelId.longValue() == IConstants.PARLIAMENT_CONSTITUENCY_LEVEL_SCOPE_ID){
						addressVO.setParliamentId(commonMethodsUtilService.getLongValueForObject(param[4]));
						addressVO.setParliamentName(commonMethodsUtilService.getStringValueForObject(param[5]));
						
						addressVO.setStateId(commonMethodsUtilService.getLongValueForObject(param[0]));
						addressVO.setStateName(commonMethodsUtilService.getStringValueForObject(param[1]));
						addressVO.setDistrictId(commonMethodsUtilService.getLongValueForObject(param[2]));
						addressVO.setDistrictName(commonMethodsUtilService.getStringValueForObject(param[3]));
						addressVO.setAssemblyId(commonMethodsUtilService.getLongValueForObject(param[6]));
						addressVO.setAssemblyName(commonMethodsUtilService.getStringValueForObject(param[7]));
						
						addressVO.setId(addressVO.getParliamentId());
						addressVO.setName(addressVO.getParliamentName());
					}else if(searchLevelId != null && searchLevelId.longValue() == IConstants.MANDAL_LEVEL_SCOPE_ID){
						addressVO.setTehsilId(commonMethodsUtilService.getLongValueForObject(param[8]));
						addressVO.setTehsilName(commonMethodsUtilService.getStringValueForObject(param[9]));
						
						addressVO.setStateId(commonMethodsUtilService.getLongValueForObject(param[0]));
						addressVO.setStateName(commonMethodsUtilService.getStringValueForObject(param[1]));
						addressVO.setDistrictId(commonMethodsUtilService.getLongValueForObject(param[2]));
						addressVO.setDistrictName(commonMethodsUtilService.getStringValueForObject(param[3]));
						addressVO.setParliamentId(commonMethodsUtilService.getLongValueForObject(param[4]));
						addressVO.setParliamentName(commonMethodsUtilService.getStringValueForObject(param[5]));
						addressVO.setAssemblyId(commonMethodsUtilService.getLongValueForObject(param[6]));
						addressVO.setAssemblyName(commonMethodsUtilService.getStringValueForObject(param[7]));
						
						addressVO.setId(addressVO.getTehsilId());
						addressVO.setName(addressVO.getTehsilName());
					}
					
					Long keyId=addressVO.getId();
					FundSchemeVO fundLocationVO = locationMap.get(keyId);
					
					if(fundLocationVO == null){
						fundLocationVO = new FundSchemeVO();
						List<FundSchemeVO> yearsList = null;
						if(commonMethodsUtilService.isMapValid(deptsMap)){
							 yearsList = buildData(yearsMap,deptsMap,schemesMap);
						}else{
							 yearsList = buildData(yearsMap,schemesMap,null);
						}
						fundLocationVO.setSubList(yearsList);
						fundLocationVO.setAddressVO(addressVO);
						locationMap.put(keyId, fundLocationVO);
					}
					
				}
			//}
		}catch(Exception e){
			
		}
	}
	
	/*
	 * Date : 08/06/2017
	 * Author :Srishailam Pittala
	 * @description : to get financial year ,  Department wise and scheme wise funds transaction details
	 */
	
	public List<FundSchemeVO> getFinancialYearWiseDeptsWiseSchemeDetails(List<Long> financialYearIdsList,List<Long> deptIdsList,
			List<Long> sourceIdsList,List<Long> schemeIdsList,String startDateStr,String endDateStr,Long searchScopeId,List<Long> searchScopeValuesList, String order, String sortingType,Long searchLevelId){
		List<FundSchemeVO> returnList = new ArrayList<FundSchemeVO>(0);
		try {
			Date startDate = commonMethodsUtilService.stringTODateConvertion(startDateStr,"dd/MM/yyyy","");
			Date endDate = commonMethodsUtilService.stringTODateConvertion(endDateStr,"dd/MM/yyyy","");
			
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
					
					addressVO.setStateId(commonMethodsUtilService.getLongValueForObject(param[10]));
					addressVO.setStateName(commonMethodsUtilService.getStringValueForObject(param[11]));
					addressVO.setDistrictId(commonMethodsUtilService.getLongValueForObject(param[12]));
					addressVO.setDistrictName(commonMethodsUtilService.getStringValueForObject(param[13]));
					addressVO.setAssemblyId(commonMethodsUtilService.getLongValueForObject(param[14]));
					addressVO.setAssemblyName(commonMethodsUtilService.getStringValueForObject(param[15]));
					addressVO.setTehsilId(commonMethodsUtilService.getLongValueForObject(param[16]));
					addressVO.setTehsilName(commonMethodsUtilService.getStringValueForObject(param[17]));
					addressVO.setParliamentId(commonMethodsUtilService.getLongValueForObject(param[20]));
					addressVO.setParliamentName(commonMethodsUtilService.getStringValueForObject(param[21]));
					addressVO.setPanchayatId(commonMethodsUtilService.getLongValueForObject(param[18]));
					addressVO.setPanchayatName(commonMethodsUtilService.getStringValueForObject(param[19]));
					
					if(searchScopeId != null && searchScopeId.longValue() == IConstants.STATE_LEVEL_SCOPE_ID){
						addressVO.setId(addressVO.getStateId());
						addressVO.setName(addressVO.getStateName());
					}else if(searchLevelId != null && searchLevelId.longValue() == IConstants.DISTRICT_LEVEL_SCOPE_ID){						
						addressVO.setId(addressVO.getDistrictId());
						addressVO.setName(addressVO.getDistrictName());
					}else if(searchLevelId != null && searchLevelId.longValue() == IConstants.CONSTITUENCY_LEVEL_SCOPE_ID){
						addressVO.setId(addressVO.getAssemblyId());
						addressVO.setName(addressVO.getAssemblyName());
					}else if(searchLevelId != null && searchLevelId.longValue() == IConstants.PARLIAMENT_CONSTITUENCY_LEVEL_SCOPE_ID){						
						addressVO.setId(addressVO.getAssemblyId());
						addressVO.setName(addressVO.getAssemblyName());
					}
					else if(searchLevelId != null && searchLevelId.longValue() == IConstants.MANDAL_LEVEL_SCOPE_ID){						
						addressVO.setId(addressVO.getTehsilId());
						addressVO.setName(addressVO.getTehsilName());
					}
					else if(searchLevelId != null && searchLevelId.longValue() == IConstants.VILLAGE_LEVEL_SCOPE_ID){						
						addressVO.setId(addressVO.getPanchayatId());
						addressVO.setName(addressVO.getPanchayatName());
					}
					
					Long keyId=addressVO.getId();
					
					if(locationMap.get(keyId) != null){
						fundLocationVO = locationMap.get(keyId);
						if(fundLocationVO.getTotalCount() != null && fundLocationVO.getTotalCount().longValue()>0L)
							fundLocationVO.setTotalCount(fundLocationVO.getTotalCount()+commonMethodsUtilService.getLongValueForObject(param[5]));
						else
							fundLocationVO.setTotalCount(commonMethodsUtilService.getLongValueForObject(param[5]));
						fundLocationVO.setAmount(commonMethodsUtilService.calculateAmountInWords(fundLocationVO.getTotalCount()));
					}else{
						List<FundSchemeVO> yearsList = buildData(yearsMap,deptsMap,schemesMap);
						fundLocationVO.setSubList(yearsList);
						fundLocationVO.setTotalCount(commonMethodsUtilService.getLongValueForObject(param[5]));
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
						
						schemeVO.setAmount(commonMethodsUtilService.calculateAmountInWords(schemeVO.getTotalCount()));
						
						deptsVO.getSubList().add(schemeVO);
						
						if(yearVO.getTotalCount() != null && yearVO.getTotalCount().longValue()>0L)
							yearVO.setTotalCount(yearVO.getTotalCount()+commonMethodsUtilService.getLongValueForObject(param[5]));
						else
							yearVO.setTotalCount(commonMethodsUtilService.getLongValueForObject(param[5]));
						yearVO.setAmount(commonMethodsUtilService.calculateAmountInWords(yearVO.getTotalCount()));
						
						yearVO.getSubList().add(deptsVO);
						fundLocationVO.getSubList().add(yearVO);
					}else{
						
						if(yearVO.getTotalCount() != null && yearVO.getTotalCount().longValue()>0L)
							yearVO.setTotalCount(yearVO.getTotalCount()+commonMethodsUtilService.getLongValueForObject(param[5]));
						else
							yearVO.setTotalCount(commonMethodsUtilService.getLongValueForObject(param[5]));
						yearVO.setAmount(commonMethodsUtilService.calculateAmountInWords(yearVO.getTotalCount()));
						
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
							
							schemeVO.setAmount(commonMethodsUtilService.calculateAmountInWords(schemeVO.getTotalCount()));
							
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
								
								schemeVO.setAmount(commonMethodsUtilService.calculateAmountInWords(schemeVO.getTotalCount()));
								
								deptsVO.getSubList().add(schemeVO);
								
							}
							else{
								
								schemeVO.setCount(schemeVO.getCount()+commonMethodsUtilService.getLongValueForObject(param[4]));
								schemeVO.setTotalCount(schemeVO.getTotalCount()+commonMethodsUtilService.getLongValueForObject(param[5]));
								
								schemeVO.setAmount(commonMethodsUtilService.calculateAmountInWords(schemeVO.getTotalCount()));
								
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
			
			setNonFundedLocations( locationMap , searchLevelId, yearsMap, schemesMap,deptsMap,null,null);
			if(commonMethodsUtilService.isMapValid(locationMap)){
				returnList.addAll(locationMap.values());
				
				if(returnList != null && returnList.size() > 0){
					if(sortingType.trim().equalsIgnoreCase("name")){
						if(order.trim().equalsIgnoreCase("asc")){
							Collections.sort(returnList, nameWiseAscendingOrder);
						}else{
							Collections.sort(returnList, nameWiseDescendingOrder);
						}
					}else if(sortingType.trim().equalsIgnoreCase("count")){
						if(order.trim().equalsIgnoreCase("asc")){
							Collections.sort(returnList, amountWiseAscendingOrder);
						}else{
							Collections.sort(returnList, amountWiseDescendingOrder);
						}
					}
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
					yearVO.setAmount("0");
					
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
							deptVO.setAmount("0");
							
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
									schemeVO.setAmount("0");
									
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
			
			Long levelId = inputVO.getBlockLevelId();
			
			Date fromDate = commonMethodsUtilService.stringTODateConvertion(inputVO.getFromDateStr(),"dd/MM/yyyy","");
			Date toDate = commonMethodsUtilService.stringTODateConvertion(inputVO.getToDateStr(),"dd/MM/yyyy","");
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
			Map<Long,String> locationIdAndNameMap = new HashMap<Long,String>(0);
			Map<Long,AddressVO> locationAddressMap = new HashMap<Long,AddressVO>(0);
			
			//create a map of financialYearId and map of locationId and amount
			Map<Long,Map<Long,Long>> financialYearIdAndLocationIdAndAmountMap = new LinkedHashMap<Long,Map<Long,Long>>();
			Map<Long,Long> locationIdAndAmountMap = null;
			if(amountList != null && amountList.size() > 0){
				for(Object[] param : amountList){
					
					AddressVO addressVO = new AddressVO();
					
					addressVO.setStateId(commonMethodsUtilService.getLongValueForObject(param[6]));
					addressVO.setStateName(commonMethodsUtilService.getStringValueForObject(param[7]));
					addressVO.setDistrictId(commonMethodsUtilService.getLongValueForObject(param[8]));
					addressVO.setDistrictName(commonMethodsUtilService.getStringValueForObject(param[9]));
					addressVO.setAssemblyId(commonMethodsUtilService.getLongValueForObject(param[10]));
					addressVO.setAssemblyName(commonMethodsUtilService.getStringValueForObject(param[11]));
					addressVO.setTehsilId(commonMethodsUtilService.getLongValueForObject(param[12]));
					addressVO.setTehsilName(commonMethodsUtilService.getStringValueForObject(param[13]));
					addressVO.setPanchayatId(commonMethodsUtilService.getLongValueForObject(param[14]));
					addressVO.setPanchayatName(commonMethodsUtilService.getStringValueForObject(param[15]));
					addressVO.setParliamentId(commonMethodsUtilService.getLongValueForObject(param[16]));
					addressVO.setParliamentName(commonMethodsUtilService.getStringValueForObject(param[17]));
					
					
					locationAddressMap.put(commonMethodsUtilService.getLongValueForObject(param[2]), addressVO);
					
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
					locationVO.setAddressVO(locationAddressMap.get(locId));
					//call this method to set the amount and count details.
					pushCountAndAmountDetails(locationVO,financialYearIdAndLocationIdAndAmountMap,financialYearIdAndLocationIdAndCountMap,financialYearIdAndFinancialYearMap);
					finalList.add(locationVO);
				}
			}
			List<Object[]> nonFundedLocs  = null;
			if(commonMethodsUtilService.isListOrSetValid(locationIdList)){
				  nonFundedLocs = tehsilConstituencyDAO.getNonFundedLocations(locationIdList, levelId,null,null);
			}
			if(nonFundedLocs != null && nonFundedLocs.size() > 0){
				for(Object[] param :nonFundedLocs){
					AddressVO addressVO = new AddressVO();
					
					addressVO.setStateId(commonMethodsUtilService.getLongValueForObject(param[0]));
					addressVO.setStateName(commonMethodsUtilService.getStringValueForObject(param[1]));
					addressVO.setDistrictId(commonMethodsUtilService.getLongValueForObject(param[2]));
					addressVO.setDistrictName(commonMethodsUtilService.getStringValueForObject(param[3]));
					addressVO.setParliamentId(commonMethodsUtilService.getLongValueForObject(param[4]));
					addressVO.setParliamentName(commonMethodsUtilService.getStringValueForObject(param[5]));
					addressVO.setAssemblyId(commonMethodsUtilService.getLongValueForObject(param[6]));
					addressVO.setAssemblyName(commonMethodsUtilService.getStringValueForObject(param[7]));
					addressVO.setTehsilId(commonMethodsUtilService.getLongValueForObject(param[8]));
					addressVO.setTehsilName(commonMethodsUtilService.getStringValueForObject(param[9]));
					//addressVO.setPanchayatId(commonMethodsUtilService.getLongValueForObject(param[14]));
					//addressVO.setPanchayatName(commonMethodsUtilService.getStringValueForObject(param[15]));
					
					locationVO = new LocationVO();
					locationVO.setLocationLevelId(levelId);
					locationVO.setAddressVO(addressVO);
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
				locVO.setAmunt(commonMethodsUtilService.calculateAmountInWords(totalAmount));
				locVO.setAmount(totalAmount);
				locVO.setCount(totalCount);
				locVoList.add(locVO);
				locationVO.getLocationList1().addAll(locVoList);
				locationVO.setAmount(totalAmount);
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
			Date startDate = commonMethodsUtilService.stringTODateConvertion(inputVO.getFromDateStr(),"dd/MM/yyyy","");
			Date endDate = commonMethodsUtilService.stringTODateConvertion(inputVO.getToDateStr(),"dd/MM/yyyy","");
		    
			inputVO.setFinancialYrIdList(commonMethodsUtilService.makeEmptyListByZeroValue(inputVO.getFinancialYrIdList()));
			inputVO.setDeptIdsList(commonMethodsUtilService.makeEmptyListByZeroValue(inputVO.getDeptIdsList()));
			inputVO.setSourceIdsList(commonMethodsUtilService.makeEmptyListByZeroValue(inputVO.getSourceIdsList()));
			inputVO.setSearchLvlVals(commonMethodsUtilService.makeEmptyListByZeroValue(inputVO.getSearchLvlVals()));
			
			
			Long totalfund = fundSanctionDAO.getTotalFund(inputVO.getFinancialYrIdList(),inputVO.getDeptIdsList(),inputVO.getSourceIdsList(),startDate,endDate,IConstants.CONSTITUENCY_LEVEL_SCOPE_ID,inputVO.getSearchLevelId(),inputVO.getSearchLvlVals(),null,null);
			
			List<Object[]> highFund = fundSanctionDAO.getLocationWiseFundHighAndLow(inputVO.getFinancialYrIdList(),inputVO.getDeptIdsList(),inputVO.getSourceIdsList(),startDate,endDate,inputVO.getBlockLevelId(),inputVO.getType(),inputVO.getSearchLevelId(),inputVO.getSearchLvlVals(),null,null);
			if(highFund != null && highFund.size() >0){
				setFundDetails(highFund,returnVO,inputVO.getType(),totalfund);
			}
			if(returnVO.getId() != null && returnVO.getId().longValue() >0l){
				List<Object[]> locWiseGrantTypes = fundSanctionDAO.getLocationWiseGrantTypesFund(inputVO.getFinancialYrIdList(),inputVO.getDeptIdsList(),inputVO.getSourceIdsList(),startDate,endDate,inputVO.getBlockLevelId(),returnVO.getId(),inputVO.getSearchLevelId(),inputVO.getSearchLvlVals(),null,null);
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
		Date startDate = commonMethodsUtilService.stringTODateConvertion(inputVO.getFromDateStr(),"dd/MM/yyyy","");
		Date endDate = commonMethodsUtilService.stringTODateConvertion(inputVO.getToDateStr(),"dd/MM/yyyy","");

		inputVO.setFinancialYrIdList(commonMethodsUtilService.makeEmptyListByZeroValue(inputVO.getFinancialYrIdList()));
		inputVO.setDeptIdsList(commonMethodsUtilService.makeEmptyListByZeroValue(inputVO.getDeptIdsList()));
		inputVO.setSourceIdsList(commonMethodsUtilService.makeEmptyListByZeroValue(inputVO.getSourceIdsList()));
		inputVO.setSearchLvlVals(commonMethodsUtilService.makeEmptyListByZeroValue(inputVO.getSearchLvlVals()));
		inputVO.setSchemeIdsList(commonMethodsUtilService.makeEmptyListByZeroValue(inputVO.getSchemeIdsList()));
		inputVO.setSubProgramIdsList(commonMethodsUtilService.makeEmptyListByZeroValue(inputVO.getSubProgramIdsList()));
		
		Long totalfund = fundSanctionDAO.getTotalFund(inputVO.getFinancialYrIdList(),inputVO.getDeptIdsList(),inputVO.getSourceIdsList(),startDate,endDate,IConstants.CONSTITUENCY_LEVEL_SCOPE_ID,inputVO.getSearchLevelId(),inputVO.getSearchLvlVals(),inputVO.getSchemeIdsList(),inputVO.getSubProgramIdsList());

		if(totalfund != null && totalfund.longValue() > 0l){
			retusnVo.setTotalAmt(commonMethodsUtilService.calculateAmountInWords(totalfund));
			//retusnVo.setTotalAmt(totalfund.toString());
			retusnVo.setTtlAmt(totalfund.toString());
		}
		if(retusnVo.getTotalAmt() != null){
			List<Object[]> locWiseGrantTypes = fundSanctionDAO.getLocationWiseGrantTypesFund(inputVO.getFinancialYrIdList(),inputVO.getDeptIdsList(),inputVO.getSourceIdsList(),startDate,endDate,IConstants.STATE_LEVEL_SCOPE_ID,1L,inputVO.getSearchLevelId(),inputVO.getSearchLvlVals(),inputVO.getSchemeIdsList(),inputVO.getSubProgramIdsList());
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
		
		Date startDate = commonMethodsUtilService.stringTODateConvertion(inputVO.getFromDateStr(),"dd/MM/yyyy","");
		Date endDate = commonMethodsUtilService.stringTODateConvertion(inputVO.getToDateStr(),"dd/MM/yyyy","");
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
		else if(inputVO.getBlockLevelId() != null && inputVO.getBlockLevelId().longValue() == IConstants.PARLIAMENT_CONSTITUENCY_LEVEL_SCOPE_ID)
			 totalLocations = 25L;
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
			Long avagecount = 0L;
			Long  avagecount1 = 0L;
			DecimalFormat df2 = new DecimalFormat(".##");
			Date sDate = commonMethodsUtilService.stringTODateConvertion(inputVO.getFromDateStr(),"dd/MM/yyyy","");
			Date eDate = commonMethodsUtilService.stringTODateConvertion(inputVO.getToDateStr(),"dd/MM/yyyy","");
			
			inputVO.setFinancialYrIdList(commonMethodsUtilService.makeEmptyListByZeroValue(inputVO.getFinancialYrIdList()));
			List<Long> deptIdsList = commonMethodsUtilService.makeEmptyListByZeroValue(inputVO.getDeptIdsList());
			List<Long> sourceIdsList = commonMethodsUtilService.makeEmptyListByZeroValue(inputVO.getSourceIdsList());
			inputVO.setSearchLvlVals(commonMethodsUtilService.makeEmptyListByZeroValue(inputVO.getSearchLvlVals()));
			//scope wise count and amount dtls
			List<Object[]> totalFundAndCountDtls= fundSanctionDAO.getTotalFundAndCountDtls(inputVO.getFinancialYrIdList(),deptIdsList,sourceIdsList,sDate,eDate,inputVO.getBlockLevelId(),"one",inputVO.getSearchLevelId(),inputVO.getSearchLvlVals());
			Long ttl = 0L;
			int len = 0;
			if(totalFundAndCountDtls != null && totalFundAndCountDtls.size() > 0){
				len = totalFundAndCountDtls.size();
				for(Object[] param : totalFundAndCountDtls){
					ttl = ttl + commonMethodsUtilService.getLongValueForObject(param[1]);
				}
			}
			
			Long totalfund = fundSanctionDAO.getTotalFund(inputVO.getFinancialYrIdList(),deptIdsList,sourceIdsList,sDate,eDate,IConstants.CONSTITUENCY_LEVEL_SCOPE_ID,inputVO.getSearchLevelId(),inputVO.getSearchLvlVals(),null,null);
			retusnVo.setTotalAmt(commonMethodsUtilService.getStringValueForObject(totalfund));
			if(totalfund != null ){
				retusnVo.setAverageAmt(commonMethodsUtilService.roundUptoTwoDecimalPoint((Double.valueOf(commonMethodsUtilService.getStringValueForObject(totalfund))/Double.valueOf(new Integer(len).toString()))));
				avagecount = commonMethodsUtilService.roundUptoTwoDecimalPoint((Double.valueOf(commonMethodsUtilService.getStringValueForObject(totalfund))/Double.valueOf(new Integer(len).toString()))).longValue();
			}
			if(avagecount != null){
				retusnVo.setAvrgeAmt(commonMethodsUtilService.calculateAmountInWords(avagecount));
			}
			if(retusnVo.getAverageAmt() != null && retusnVo.getTotalAmt() != null && retusnVo.getTotalAmt() != ""){
			retusnVo.setPerc(commonMethodsUtilService.calculatePercantage(retusnVo.getAverageAmt().longValue(),Long.parseLong(retusnVo.getTotalAmt())));
			}
			List<Object[]> grantTypeDtlsList = grantTypeDAO.getGrandTypeDtls();
			setGrantTypeToVo(retusnVo,grantTypeDtlsList);
			//grant wise then scope wise count and amount dtls
			List<Object[]> totalFundAndCountGrantWiseList = fundSanctionDAO.getTotalFundAndCountDtls(inputVO.getFinancialYrIdList(),deptIdsList,sourceIdsList,sDate,eDate,inputVO.getBlockLevelId(),"two",inputVO.getSearchLevelId(),inputVO.getSearchLvlVals());
			if(totalFundAndCountGrantWiseList != null && totalFundAndCountGrantWiseList.size() > 0){
				for(Object[] param : totalFundAndCountGrantWiseList){
					fundDetailsVO = (LocationFundDetailsVO)setterAndGetterUtilService.getMatchedVOfromList(retusnVo.getDetailsVOs(), "id", commonMethodsUtilService.getStringValueForObject(param[0]));
					fundDetailsVO.setTotalAmt(commonMethodsUtilService.getStringValueForObject(param[3]));
					fundDetailsVO.setAverageAmt(commonMethodsUtilService.roundUptoTwoDecimalPoint((Double.valueOf(commonMethodsUtilService.getStringValueForObject(param[3]))/Double.valueOf(new Integer(len).toString()))));
					avagecount1 = commonMethodsUtilService.roundUptoTwoDecimalPoint((Double.valueOf(commonMethodsUtilService.getStringValueForObject(param[3]))/Double.valueOf(new Integer(len).toString()))).longValue();
					fundDetailsVO.setFundAvageAmt(commonMethodsUtilService.calculateAmountInWords(avagecount1));
					fundDetailsVO.setPerc(commonMethodsUtilService.calculatePercantage(fundDetailsVO.getAverageAmt().longValue(),retusnVo.getAverageAmt().longValue()));
				}
			}
			setFundAndSchemeToVo(retusnVo);
			//for fund dtls
			if(totalFundAndCountGrantWiseList != null && totalFundAndCountGrantWiseList.size() > 0){
				for(Object[] param : totalFundAndCountGrantWiseList){
					fundDetailsVO = (LocationFundDetailsVO)setterAndGetterUtilService.getMatchedVOfromList(retusnVo.getFundDetailsVOs(), "id", commonMethodsUtilService.getStringValueForObject(param[0]));
					if(fundDetailsVO == null){
						continue;
					}
					fundDetailsVO.setTotalAmt(commonMethodsUtilService.getStringValueForObject(param[3]));
					fundDetailsVO.setAverageAmt(commonMethodsUtilService.roundUptoTwoDecimalPoint((Double.valueOf(commonMethodsUtilService.getStringValueForObject(param[3]))/Double.valueOf(commonMethodsUtilService.getStringValueForObject(param[2])))));
					avagecount1 = commonMethodsUtilService.roundUptoTwoDecimalPoint((Double.valueOf(commonMethodsUtilService.getStringValueForObject(param[3]))/Double.valueOf(commonMethodsUtilService.getStringValueForObject(param[2])))).longValue();
					fundDetailsVO.setFundAvageAmt(commonMethodsUtilService.calculateAmountInWords(avagecount1));
					fundDetailsVO.setPerc(commonMethodsUtilService.calculatePercantage(fundDetailsVO.getAverageAmt().longValue(),Long.parseLong(fundDetailsVO.getTotalAmt())));
				}
			}
			//for scheme dtls
			if(totalFundAndCountGrantWiseList != null && totalFundAndCountGrantWiseList.size() > 0){
				for(Object[] param : totalFundAndCountGrantWiseList){
					fundDetailsVO = (LocationFundDetailsVO)setterAndGetterUtilService.getMatchedVOfromList(retusnVo.getSchemeDetailsVOs(), "id", commonMethodsUtilService.getStringValueForObject(param[0]));
					if(fundDetailsVO == null){
						continue;
					}
					fundDetailsVO.setTotalAmt(commonMethodsUtilService.getStringValueForObject(param[3]));
					fundDetailsVO.setAverageAmt(commonMethodsUtilService.roundUptoTwoDecimalPoint((Double.valueOf(commonMethodsUtilService.getStringValueForObject(param[3]))/Double.valueOf(commonMethodsUtilService.getStringValueForObject(param[2])))));
					avagecount1 = commonMethodsUtilService.roundUptoTwoDecimalPoint((Double.valueOf(commonMethodsUtilService.getStringValueForObject(param[3]))/Double.valueOf(commonMethodsUtilService.getStringValueForObject(param[2])))).longValue();
					fundDetailsVO.setFundAvageAmt(commonMethodsUtilService.calculateAmountInWords(avagecount1));
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
			Date sDate = commonMethodsUtilService.stringTODateConvertion(inputVO.getFromDateStr(),"dd/MM/yyyy","");
			Date eDate = commonMethodsUtilService.stringTODateConvertion(inputVO.getToDateStr(),"dd/MM/yyyy","");
			Long avagecount =0L;
			
			inputVO.setFinancialYrIdList(commonMethodsUtilService.makeEmptyListByZeroValue(inputVO.getFinancialYrIdList()));
			List<Long> deptIdsList = commonMethodsUtilService.makeEmptyListByZeroValue(inputVO.getDeptIdsList());
			List<Long> sourceIdsList = commonMethodsUtilService.makeEmptyListByZeroValue(inputVO.getSourceIdsList());
			List<Long> schemeIdsList = commonMethodsUtilService.makeEmptyListByZeroValue(inputVO.getSchemeIdsList());
			List<Long> searchValList = commonMethodsUtilService.makeEmptyListByZeroValue(inputVO.getSearchLvlVals());
			List<Object[]> totalFundForSchemeList = fundSanctionDAO.getTotalFundForScheme(inputVO.getFinancialYrIdList(),deptIdsList,sourceIdsList,schemeIdsList,sDate,eDate,inputVO.getType(),inputVO.getSearchLevelId(),searchValList);
			
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
    		Long vo2 = obj2.getAmount();
    		Long vo1 = obj1.getAmount();
    		//descending order of percantages.
    		return vo2.compareTo(vo1);
    	}
	};
	public static Comparator<LocationVO> amountWiseDescOrder = new Comparator<LocationVO>() {
    	public int compare(LocationVO obj2, LocationVO obj1) {
    		Long vo2 = obj2.getAmount();
    		Long vo1 = obj1.getAmount();
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
		Date startDate = commonMethodsUtilService.stringTODateConvertion(inputVO.getFromDateStr(),"dd/MM/yyyy","");
		Date endDate = commonMethodsUtilService.stringTODateConvertion(inputVO.getToDateStr(),"dd/MM/yyyy","");
	     
		inputVO.setFinancialYrIdList(commonMethodsUtilService.makeEmptyListByZeroValue(inputVO.getFinancialYrIdList()));
		inputVO.setDeptIdsList(commonMethodsUtilService.makeEmptyListByZeroValue(inputVO.getDeptIdsList()));
		inputVO.setSourceIdsList(commonMethodsUtilService.makeEmptyListByZeroValue(inputVO.getSourceIdsList()));
		inputVO.setSearchLvlVals(commonMethodsUtilService.makeEmptyListByZeroValue(inputVO.getSearchLvlVals()));
		
		Long totalfund = fundSanctionDAO.getTotalFund(inputVO.getFinancialYrIdList(),inputVO.getDeptIdsList(),inputVO.getSourceIdsList(),startDate,endDate,IConstants.CONSTITUENCY_LEVEL_SCOPE_ID,inputVO.getSearchLevelId(),inputVO.getSearchLvlVals(),null,null);
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
		Date startDate = commonMethodsUtilService.stringTODateConvertion(inputVO.getFromDateStr(),"dd/MM/yyyy","");
		Date endDate = commonMethodsUtilService.stringTODateConvertion(inputVO.getToDateStr(),"dd/MM/yyyy","");
		
		inputVO.setFinancialYrIdList(commonMethodsUtilService.makeEmptyListByZeroValue(inputVO.getFinancialYrIdList()));
		inputVO.setDeptIdsList(commonMethodsUtilService.makeEmptyListByZeroValue(inputVO.getDeptIdsList()));
		inputVO.setSourceIdsList(commonMethodsUtilService.makeEmptyListByZeroValue(inputVO.getSourceIdsList()));
		inputVO.setSearchLvlVals(commonMethodsUtilService.makeEmptyListByZeroValue(inputVO.getSearchLvlVals()));
		
			Long totalSchemes = fundSanctionDAO.getTotalSchemes(inputVO.getFinancialYrIdList(),inputVO.getDeptIdsList(),inputVO.getSourceIdsList(), startDate, endDate,inputVO.getSearchLevelId(),inputVO.getSearchLvlVals(),inputVO.getType());
		 
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
	    List<Object[]> constiesObjs =constituencyDAO.getConstituencies(districtId,null);
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
 			Date sDate = commonMethodsUtilService.stringTODateConvertion(inputVO.getFromDateStr(),"dd/MM/yyyy","");
 			Date eDate = commonMethodsUtilService.stringTODateConvertion(inputVO.getToDateStr(),"dd/MM/yyyy","");
 			
 			inputVO.setFinancialYrIdList(commonMethodsUtilService.makeEmptyListByZeroValue(inputVO.getFinancialYrIdList()));
 			String superLocationLevelIdStr = "";
 			String superLocationIdStr = inputVO.getSuperLocationId().toString();
 			
 			superLocationLevelIdStr = superLocationIdStr.substring(0, 1);
 			
			superLocationIdStr = superLocationIdStr.substring(1);
			Long superLocationId = 0L;
			if(superLocationIdStr != null && superLocationIdStr.trim().length() > 0){
				superLocationId = Long.parseLong(superLocationIdStr);
			}
			
			Long superLocationLevelId = Long.parseLong(superLocationLevelIdStr);
			//String type = "";
			if(superLocationLevelId.longValue()== 9L){
				superLocationLevelId = 10L;
			}
			
			List<Long> deptIdsList = commonMethodsUtilService.makeEmptyListByZeroValue(inputVO.getDeptIdsList());
			List<Long> sourceIdsList = commonMethodsUtilService.makeEmptyListByZeroValue(inputVO.getSourceIdsList());
			if(superLocationLevelId != null && superLocationLevelId.longValue() == 1L){
				locationList = fundSanctionDAO.getAllParliamentByStateId(superLocationId,inputVO.getFinancialYrIdList(),deptIdsList,sourceIdsList,sDate,eDate,inputVO.getBlockLevelId());
				lvlIdStr = "9";
			}else if(superLocationLevelId != null && superLocationLevelId.longValue() == IConstants.STATE_LEVEL_SCOPE_ID){//get districtIds
				locationList = fundSanctionDAO.getAllDistrictByStateId(superLocationId,inputVO.getFinancialYrIdList(),deptIdsList,sourceIdsList,sDate,eDate,inputVO.getBlockLevelId());
				lvlIdStr = IConstants.DISTRICT_LEVEL_SCOPE_ID.toString();
			}else if(superLocationLevelId != null && superLocationLevelId.longValue() == IConstants.DISTRICT_LEVEL_SCOPE_ID){//get constituencyIds
				locationList = fundSanctionDAO.getAllConstituencyByDistrictId(superLocationId,inputVO.getFinancialYrIdList(),deptIdsList,sourceIdsList,sDate,eDate,inputVO.getBlockLevelId(),superLocationLevelId);
				lvlIdStr = IConstants.CONSTITUENCY_LEVEL_SCOPE_ID.toString();
			}else if(superLocationLevelId != null && superLocationLevelId.longValue() == IConstants.CONSTITUENCY_LEVEL_SCOPE_ID){//get tehsilIds
				locationList = fundSanctionDAO.getAllTehsilByConstituencyId(superLocationId,inputVO.getFinancialYrIdList(),deptIdsList,sourceIdsList,sDate,eDate,inputVO.getBlockLevelId());
				lvlIdStr = IConstants.MANDAL_LEVEL_SCOPE_ID.toString();
			}else if(superLocationLevelId != null && superLocationLevelId.longValue() == IConstants.MANDAL_LEVEL_SCOPE_ID){//get panchayatIds
				locationList = fundSanctionDAO.getAllPanchayatByTehsilId(superLocationId,inputVO.getFinancialYrIdList(),deptIdsList,sourceIdsList,sDate,eDate);
				lvlIdStr = IConstants.VILLAGE_LEVEL_SCOPE_ID.toString();
			}else if(superLocationLevelId != null && superLocationLevelId.longValue() == IConstants.PARLIAMENT_CONSTITUENCY_LEVEL_SCOPE_ID){//get constituencyIds
				locationList = fundSanctionDAO.getAllConstituencyByParliamentConstId(superLocationId,inputVO.getFinancialYrIdList(),deptIdsList,sourceIdsList,sDate,eDate,inputVO.getBlockLevelId(),superLocationLevelId);
				lvlIdStr = IConstants.CONSTITUENCY_LEVEL_SCOPE_ID.toString();  
			}	
			if(locationList != null && locationList.size() > 0){
				for(Object[] param : locationList){
					locationFundDetailsVO = new LocationFundDetailsVO();
					String locId = commonMethodsUtilService.getStringValueForObject(param[0]);
					locId = lvlIdStr.concat(locId);
					locationFundDetailsVO.setId(Long.parseLong(locId));
					 String nameStr=commonMethodsUtilService.getStringValueForObject(param[1]);
						locationFundDetailsVO.setName(commonMethodsUtilService.toConvertStringToTitleCase(nameStr));
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
	 * Date : 15/06/2017
	 * Author :Shrinu Pittala
	 * @description : to get FinancialYearId Sum
	 * @return  List<LocationFundDetailsVO> 
	 */
 public List<LocationFundDetailsVO> getFundSactionCount(List<Long> financialYrIdList){
	 
	 List<LocationFundDetailsVO> finalList=new ArrayList<LocationFundDetailsVO>();
	 LocationFundDetailsVO vo=null;
	 List<Object[]> result=fundSanctionDAO.getFundSactionCount(financialYrIdList);
	 
	 if(result !=null && result.size()>0 && !result.isEmpty()){
		 for(Object[] Obj: result){
		 vo=new LocationFundDetailsVO();
		 vo.setId(commonMethodsUtilService.getLongValueForObject(Obj[0]));
		 vo.setYear(commonMethodsUtilService.getStringValueForObject(Obj[1]));
		 vo.setSum(commonMethodsUtilService.getLongValueForObject(Obj[2]));
		 
		 finalList.add(vo);
		 }
		 return finalList;
	 }
	 
	 return finalList;
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
			if(commonMethodsUtilService.isListOrSetValid(inputVO.getFinancialYrIdList()))
				Collections.sort(inputVO.getFinancialYrIdList());
			if(inputVO.getFinancialYrIdList() != null && inputVO.getFinancialYrIdList().size() > 0){
				int len = inputVO.getFinancialYrIdList().size();
				for(int i=0; i < len-1 ; i++){
					if(i==0){
						//for two year comparison
						buildCompareFundsBetweenFinancialYears(finalList,inputVO.getBlockLevelId(),inputVO.getFinancialYrIdList().get(i),inputVO.getFinancialYrIdList().get(len-1),inputVO);
					}else{
						//for multiple year comparison	
						pushInFinalVOList(finalList,inputVO.getBlockLevelId(),inputVO.getFinancialYrIdList().get(i),inputVO.getFinancialYrIdList().get(len-1));
					}
				}
			}
			List<Long> collectedLocIds = null;
			if(finalList != null && finalList.size() > 0){
				for(FundMatrixVO param : finalList){
					Long totalLoc = 0L;
					List<Long> totalLocIds = null;
					Long collectedTotalLoc = 0L;
					
					collectedLocIds = new ArrayList<Long>();
					if(param.getRangeList() != null && param.getRangeList().size() > 0){
						totalLoc = Long.parseLong(param.getRangeList().get(0).getValue());
						totalLocIds = param.getRangeList().get(0).getNonFundLocIds();
						int i = 0;
						for(RangeVO innerParam : param.getRangeList()){
							if(i==0){
								i++;
								continue;
							}
							collectedTotalLoc = collectedTotalLoc + Long.parseLong(innerParam.getValue());
							if(innerParam.getLocationIds() != null){
								collectedLocIds.addAll(convertToList(innerParam.getLocationIds()));
							}
						}
						param.getRangeList().get(1).setValue(Long.toString((totalLoc-collectedTotalLoc)));
						param.getRangeList().get(1).setLocationIds(setLocationIds(getNonFundedLocIds( totalLocIds,  collectedLocIds)));
					}
				}
			}
			return finalList;
		}catch(Exception e){
			LOG.error("Exception Occurred in compareFundsBetweenFinancialYears() of FundManagementDashboardService ", e);
		}
		return null;
	}
	public List<Long> convertToList(String locationIds){
		try{
			List<Long> locList = new ArrayList<Long>();
			String[] locationIdsArr = {};
			if(locationIds != null && locationIds.split(",").length > 0){
				locationIdsArr = locationIds.split(",");
				for(String str : locationIdsArr){
					locList.add(Long.parseLong(str));
				}
			}
			return locList;
		}catch(Exception e){
			LOG.error("Exception Occurred in convertToList() of FundManagementDashboardService ", e);
		}
		return null;
	}
	public List<Long> getNonFundedLocIds(List<Long> totalLocIds,List<Long>  collectedLocIds){
		
		try{
			if(collectedLocIds != null && collectedLocIds.size() > 0 && totalLocIds != null){
				for(Long locId :collectedLocIds){
					if(totalLocIds.contains(locId)){
						totalLocIds.remove(locId);
					}
				}
			}
			
		}catch(Exception e){
			e.printStackTrace();
			LOG.error("Exception Occurred in getNonFundedLocIds() of FundManagementDashboardService ", e);
		}
		return totalLocIds;
		
	}
	
	public void buildCompareFundsBetweenFinancialYears(List<FundMatrixVO> finalList, Long scopeId,final Long previousYearId, final Long presentYearId,InputVO inputVO){
 		try{
 			List<Object[]> financialYearList = financialYearDAO.getAllFiniancialYearsByIds(new ArrayList<Long>(){{add(previousYearId);add(presentYearId);}});
 			//create a map for yearId and name
 			Map<Long,String> yearIdNameMap = new HashMap<Long,String>();
 			if(financialYearList != null && financialYearList.size() > 0){
 				for(Object[] param : financialYearList){
 					yearIdNameMap.put(commonMethodsUtilService.getLongValueForObject(param[0]), commonMethodsUtilService.getStringValueForObject(param[1]));
 				}
 			}
 			
 			inputVO.setDeptIdsList(commonMethodsUtilService.makeEmptyListByZeroValue(inputVO.getDeptIdsList()));
 			inputVO.setSourceIdsList(commonMethodsUtilService.makeEmptyListByZeroValue(inputVO.getSourceIdsList()));
 			inputVO.setSchemeIdsList(commonMethodsUtilService.makeEmptyListByZeroValue(inputVO.getSchemeIdsList()));
 			//All the funds taken by in this query
 			List<Object[]> result =  fundSanctionDAO.financialYearWiseFundDetails(new ArrayList<Long>(){{add(previousYearId);add(presentYearId);}},inputVO.getDeptIdsList(),inputVO.getDeptIdsList(),inputVO.getSchemeIdsList(),inputVO.getBlockLevelId(),inputVO.getSearchLevelId(),inputVO.getSearchLevelValue());
 			
 			//find out the max fund in the couple of years
 			double maxAmount =0.00d;
 			for (Object[] param : result) {
				if(maxAmount<Double.valueOf(commonMethodsUtilService.getStringValueForObject(param[2])))
					maxAmount = Double.valueOf(commonMethodsUtilService.getStringValueForObject(param[2]));
			}

			List<String> intervalsList = commonMethodsUtilService.buildIntervals(maxAmount, IConstants.MATRIX_REPORT_INTERVALS);
			int arrSize = intervalsList.size();
 			//create a template for ui
 			List<Object[]> fundSanctionRangeList = new ArrayList<Object[]>();//fundSanctionMatrixRangeDAO.getFundSanctionRangeList(scopeId);
 			Object[] objArr = null;
 			if(intervalsList != null && intervalsList.size() > 0){
 				int i = 1;
 				for(String param : intervalsList){
 					objArr = new Object[2];
 					objArr[0] = new Long(i);++i;
 					objArr[1] = param;
 					fundSanctionRangeList.add(objArr);
 				}
 			}
 			if(fundSanctionRangeList != null && fundSanctionRangeList.size() > 0){
 				buildTemplate(finalList,fundSanctionRangeList);
 			}
 			
 			//result => return from DAO contain fund amount as third parameter of each object array, here I will replace that with its correspond range
 			if(result != null && result.size() > 0){
 				for(Object[] param : result){
 					param[2] = getRangeForFundAmount(fundSanctionRangeList,param[2] != null ? commonMethodsUtilService.roundUptoThreeDecimalPoint(Double.parseDouble(param[2].toString())) : 0.0d);
 				}
 			}
 			
 			
 			//take all the location based on fund range for previous year
 			List<Object[]> previousYearDtls = new ArrayList<Object[]>();//fundSanctionMatrixDetailsDAO.getPreviousYearDtls(scopeId,previousYearId,"previous");
 			Object[] prevYear = null;
  			//now create one list of object array, each object[] contains rangeId and location id for previous year
 			if(result != null && result.size() > 0){
 				for(Object[] param : result){
 					if(commonMethodsUtilService.getLongValueForObject(param[0]).longValue() == previousYearId){
 						prevYear = new Object[2];
 	 					prevYear[0] = param[2];//rangeId
 	 					prevYear[1] = param[1];//location id
 	 					previousYearDtls.add(prevYear);
 					}
 				}
 			}
 			//create a map for rangeId and list of location for previous year
 			Map<Long,List<Long>> rangeIdAndLocListMapForPrevious = new LinkedHashMap<Long,List<Long>>();
 			List<Long> locationList = null;
 			if(previousYearDtls != null && previousYearDtls.size() > 0){
 				for(Object[] param : previousYearDtls){
 					locationList = rangeIdAndLocListMapForPrevious.get(commonMethodsUtilService.getLongValueForObject(param[0]));
 					if(locationList == null){
 						locationList = new ArrayList<Long>();
 						rangeIdAndLocListMapForPrevious.put(commonMethodsUtilService.getLongValueForObject(param[0]), locationList);
 					}
 					if(commonMethodsUtilService.getLongValueForObject(param[1]) > 0l)
 					locationList.add(commonMethodsUtilService.getLongValueForObject(param[1]));
 				}
 			}
 			
 			//push the total count in previous year range
 			RangeVO rangeVO = null;
 			if(finalList != null && finalList.size() > 0){
 				for(FundMatrixVO param : finalList){
 					rangeVO = (RangeVO)setterAndGetterUtilService.getMatchedVOfromList(param.getRangeList(), "id", "0");
 					if(rangeIdAndLocListMapForPrevious != null && rangeIdAndLocListMapForPrevious.get(param.getId()) != null && rangeIdAndLocListMapForPrevious.get(param.getId()).size() > 0){
 						rangeVO.setValue(new Integer(rangeIdAndLocListMapForPrevious.get(param.getId()).size()).toString());
 						rangeVO.setLocationIds(setLocationIds(rangeIdAndLocListMapForPrevious.get(param.getId())));
 						rangeVO.setNonFundLocIds(rangeIdAndLocListMapForPrevious.get(param.getId()));
 					}else{
 						rangeVO.setValue("0");
 						rangeVO.setLocationIds("");
 					}
 				}
 			}
 			
 			//take all the location based on fund range for present year
 			List<Object[]> presentYearDtls = new ArrayList<Object[]>();//fundSanctionMatrixDetailsDAO.getPreviousYearDtls(scopeId,presentYearId,"present");
 			Object[] presYear = null;
  			//now create one list of object array, each object[] contains rangeId and location id for previous year
 			if(result != null && result.size() > 0){
 				for(Object[] param : result){
 					if(commonMethodsUtilService.getLongValueForObject(param[0]).longValue() == presentYearId){
 						presYear = new Object[2];
 						presYear[0] = param[2];//rangeId
 						presYear[1] = param[1];//location id
 						presentYearDtls.add(presYear);
 					}
 				}
 			}
 			//create a map for rangeId and list of location for present year
 			Map<Long,List<Long>> rangeIdAndLocListMapForPresent = new LinkedHashMap<Long,List<Long>>();
 			if(presentYearDtls != null && presentYearDtls.size() > 0){
 				for(Object[] param : presentYearDtls){
 					locationList = rangeIdAndLocListMapForPresent.get(commonMethodsUtilService.getLongValueForObject(param[0]));
 					if(locationList == null){
 						locationList = new ArrayList<Long>();
 						rangeIdAndLocListMapForPresent.put(commonMethodsUtilService.getLongValueForObject(param[0]), locationList);
 					}
 					if(commonMethodsUtilService.getLongValueForObject(param[1]) > 0l)
 					locationList.add(commonMethodsUtilService.getLongValueForObject(param[1]));
 				}
 			}
 			List<Long> prevLoc = new ArrayList<Long>();
 			List<Long> presLoc = new ArrayList<Long>();
 			List<Long> commonLoc = null;
 			//push location count in present year
 			if(finalList != null && finalList.size() > 0){
 				for(FundMatrixVO param : finalList){
 					if(param.getRangeList() != null && param.getRangeList().size() > 0){
 						prevLoc = rangeIdAndLocListMapForPrevious.get(param.getId());
 						for(RangeVO innerParam : param.getRangeList()){
 							if(innerParam.getId().longValue() == 0L){
 								continue;
 							}
 							presLoc = rangeIdAndLocListMapForPresent.get(innerParam.getId());
 							commonLoc = getCommonLocIds(presLoc,prevLoc);
 							Set<Long> tempSet = null;
 							if(commonLoc != null && commonLoc.size() > 0){
 								tempSet = new HashSet<Long>(commonLoc);
 							}
 							if(tempSet != null && tempSet.size() > 0){
 								innerParam.setValue(new Integer(tempSet.size()).toString());
 								innerParam.setLocationIds(setLocationIds(commonLoc));
 								innerParam.setNonFundLocIds(commonLoc);
 							}
 						}
 					}
 				}
 			}
 		}catch(Exception e){
 			LOG.error("Exception Occurred in buildCompareFundsBetweenFinancialYears() of FundManagementDashboardService ", e);
 		}
 	}
 	public void pushInFinalVOList(List<FundMatrixVO> finalList, Long scopeId,final Long previousYearId, final Long presentYearId){
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
 			List<Object[]> fundSanctionRangeList = fundSanctionMatrixRangeDAO.getFundSanctionRangeList(scopeId);
 			if(fundSanctionRangeList != null && fundSanctionRangeList.size() > 0){
 				addOneMorePreviourYear(finalList,fundSanctionRangeList);
 			}
 			
 			//take all the location based on fund range for previous year
 			List<Object[]> previousYearDtls = fundSanctionMatrixDetailsDAO.getPreviousYearDtls(scopeId,previousYearId,"previous");
 			//create a map for rangeId and list of location for previous year
 			Map<Long,List<Long>> rangeIdAndLocListMapForPrevious = new LinkedHashMap<Long,List<Long>>();
 			List<Long> locationList = null;
 			if(previousYearDtls != null && previousYearDtls.size() > 0){
 				for(Object[] param : previousYearDtls){
 					locationList = rangeIdAndLocListMapForPrevious.get(commonMethodsUtilService.getLongValueForObject(param[0]));
 					if(locationList == null){
 						locationList = new ArrayList<Long>();
 						rangeIdAndLocListMapForPrevious.put(commonMethodsUtilService.getLongValueForObject(param[0]), locationList);
 					}
 					if(commonMethodsUtilService.getLongValueForObject(param[1]) > 0l)
 					locationList.add(commonMethodsUtilService.getLongValueForObject(param[1]));
 				}
 			}
 			
 			//push the total count in previous year range
 			RangeVO rangeVO = null;
 			if(finalList != null && finalList.size() > 0){
 				for(FundMatrixVO param : finalList){
 					rangeVO = (RangeVO)setterAndGetterUtilService.getMatchedVOfromList(param.getRangeList(), "id", "0");
 					if(rangeIdAndLocListMapForPrevious != null && rangeIdAndLocListMapForPrevious.get(param.getId()) != null && rangeIdAndLocListMapForPrevious.get(param.getId()).size() > 0){
 						rangeVO.setValue(rangeVO.getValue()+"/"+new Integer(rangeIdAndLocListMapForPrevious.get(param.getId()).size()).toString());
 						rangeVO.setLocationIds(rangeVO.getLocationIds()+"-"+setLocationIds(rangeIdAndLocListMapForPrevious.get(param.getId())));
 					}else{
 						rangeVO.setValue(rangeVO.getValue()+"/"+"0");
 						rangeVO.setLocationIds(rangeVO.getLocationIds()+"-"+"0");
 					}
 				}
 			}
 			
 			//take all the location based on fund range for present year
 			List<Object[]> presentYearDtls = fundSanctionMatrixDetailsDAO.getPreviousYearDtls(scopeId,presentYearId,"present");
 			//create a map for rangeId and list of location for present year
 			Map<Long,List<Long>> rangeIdAndLocListMapForPresent = new LinkedHashMap<Long,List<Long>>();
 			if(presentYearDtls != null && presentYearDtls.size() > 0){
 				for(Object[] param : presentYearDtls){
 					locationList = rangeIdAndLocListMapForPresent.get(commonMethodsUtilService.getLongValueForObject(param[0]));
 					if(locationList == null){
 						locationList = new ArrayList<Long>();
 						rangeIdAndLocListMapForPresent.put(commonMethodsUtilService.getLongValueForObject(param[0]), locationList);
 					}
 					if(commonMethodsUtilService.getLongValueForObject(param[1]) > 0l)
 					locationList.add(commonMethodsUtilService.getLongValueForObject(param[1]));
 				}
 			}
 			List<Long> prevLoc = new ArrayList<Long>();
 			List<Long> presLoc = new ArrayList<Long>();
 			List<Long> commonLoc = null;
 			//push location count in present year
 			if(finalList != null && finalList.size() > 0){
 				for(FundMatrixVO param : finalList){
 					if(param.getRangeList() != null && param.getRangeList().size() > 0){
 						prevLoc = rangeIdAndLocListMapForPrevious.get(param.getId());
 						for(RangeVO innerParam : param.getRangeList()){
 							if(innerParam.getId().longValue() == 0L){
 								continue;
 							}
 							presLoc = rangeIdAndLocListMapForPresent.get(innerParam.getId());
 							commonLoc = getCommonLocIds(presLoc,prevLoc);
 							Set<Long> tempSet = null;
 							if(commonLoc != null && commonLoc.size() > 0){
 								tempSet = new HashSet<Long>(commonLoc);
 							}
 							if(tempSet != null && tempSet.size() > 0){
 								innerParam.setValue(innerParam.getValue()+"/"+new Integer(tempSet.size()).toString());
 								innerParam.setLocationIds(innerParam.getLocationIds()+"-"+setLocationIds(commonLoc));
 							}else{
 								innerParam.setValue(innerParam.getValue()+"/"+"0");
 								innerParam.setLocationIds(innerParam.getLocationIds()+"-"+"0");
 							}
 						}
 					}
 				}
 			}
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
 	public void addOneMorePreviourYear(List<FundMatrixVO> finalList,List<Object[]> fundSanctionRangeList){
 		try{
 			if(finalList != null && finalList.size() > 0){
 				for(FundMatrixVO param : finalList){
 					param.setRange(param.getRange()+"/"+param.getRange());
 				}
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
 			rangeVO.setLocationIds("0");
 			rangeVoList.add(rangeVO);  
 			for(Object[] param : fundSanctionRangeList){
 				rangeVO = new RangeVO();
 				rangeVO.setId(commonMethodsUtilService.getLongValueForObject(param[0]));
 				rangeVO.setName(commonMethodsUtilService.getStringValueForObject(param[1]));
 				rangeVO.setValue("0");
 				rangeVO.setLocationIds("0");
 				rangeVoList.add(rangeVO);
 			}
 			fundMatrixVO.getRangeList().addAll(rangeVoList);
 		}catch(Exception e){
 			LOG.error("Exception Occurred in voidpushPresentYearRangeValue() of FundManagementDashboardService ", e);
 		}
 	}
 	public List<Long> getCommonLocIds( List<Long> presLoc, List<Long> prevLoc){
 		try{
 			List<Long> commonLoc = null;
 			if(presLoc != null && prevLoc != null){
 				commonLoc = new ArrayList<Long>(presLoc);
 	 			commonLoc.retainAll(prevLoc);
 			}
 			return commonLoc;
 		}catch(Exception e){
 			LOG.error("Exception Occurred in getCommonLocIds() of FundManagementDashboardService ", e);
 		}
 		return null;
 	}
 	public String setLocationIds(List<Long> commonLoc){
 		try{
 			StringBuilder sb = new StringBuilder();
 			if(commonLoc != null && commonLoc.size()>0){
	 			int len = commonLoc.size();
	 			for(int i = 0 ; i < len ; i++){
	 				if(i==0){
	 					sb.append(commonLoc.get(i).toString());
	 				}else{
	 					sb.append(",");
	 					sb.append(commonLoc.get(i).toString());
	 				}
	 			}
 			}
 			return sb.toString();
 		}catch(Exception e){
 			LOG.error("Exception Occurred in getCommonLocIds() of FundManagementDashboardService ", e);
 		}
 		return null;
 	}
 	/*
	 * srujana J
	 * (non-Javadoc)
	 * @see com.itgrids.service.IFundManagementDashboardService#getLocationWiseFundSanctionDetails(com.itgrids.dto.InputVO)
	 */
 	public List<LocationVO> getLocationWiseFundSanctionDetails(InputVO inputVO){
 		  List<LocationVO> finalReturnList= null;
 			
 			try{
 				Date startDate = commonMethodsUtilService.stringTODateConvertion(inputVO.getFromDateStr(),"dd/MM/yyyy","");
 				Date endDate = commonMethodsUtilService.stringTODateConvertion(inputVO.getToDateStr(),"dd/dd/yyyy","");
 				Map<Long,LocationVO> returnMap = new HashMap<Long,LocationVO>();
 			    
 				inputVO.setFinancialYrIdList(commonMethodsUtilService.makeEmptyListByZeroValue(inputVO.getFinancialYrIdList()));
 				inputVO.setDeptIdsList(commonMethodsUtilService.makeEmptyListByZeroValue(inputVO.getDeptIdsList()));
 				inputVO.setSearchLvlVals(commonMethodsUtilService.makeEmptyListByZeroValue(inputVO.getSearchLvlVals()));
 				inputVO.setSchemeIdsList(commonMethodsUtilService.makeEmptyListByZeroValue(inputVO.getSchemeIdsList()));
 				inputVO.setSubProgramIdsList(commonMethodsUtilService.makeEmptyListByZeroValue(inputVO.getSubProgramIdsList()));
 				List<Object[]> fundSanctionDetails = fundSanctionLocationDAO.getLocationWiseFundSanctionDetails(inputVO.getFinancialYrIdList(),inputVO.getDeptIdsList(),startDate,endDate,inputVO.getBlockLevelId(),inputVO.getSearchLvlVals(),inputVO.getSchemeIdsList(),inputVO.getSubProgramIdsList());
 				if(fundSanctionDetails != null && fundSanctionDetails.size()>0){
 					finalReturnList = new ArrayList<LocationVO>();
 					for(Object[] param : fundSanctionDetails){
 						LocationVO returnVO = returnMap.get(commonMethodsUtilService.getLongValueForObject(param[13]));
 						
 						if(returnVO == null){
 							returnVO = new LocationVO();
 							StringBuilder locs = new StringBuilder();
 							
 	 						if(param[12] != null && (Long)param[12] != 0l && (Long)param[12] == 3l){
 	 							locs.append(commonMethodsUtilService.getStringValueForObject(param[8]));
 	 							locs.append("  District");
 	 						}else if(param[12] != null && (Long)param[12] != 0l && (Long)param[12] == 4l){
 	 							locs.append(commonMethodsUtilService.getStringValueForObject(param[9]));
 	 							locs.append("  Constituency");
 	 						}else if(param[12] != null && (Long)param[12] != 0l && (Long)param[12] == 5l){
 	 							locs.append(commonMethodsUtilService.getStringValueForObject(param[10]));
 	 							locs.append("  Mandal");
 	 						}else if(param[12] != null && (Long)param[12] != 0l && (Long)param[12] == 6l){
 	 							locs.append(commonMethodsUtilService.getStringValueForObject(param[11]));
 	 							locs.append("  Panchayat");
 	 						}else if(param[12] != null && (Long)param[12] != 0l && (Long)param[12] == 10l){
 	 							locs.append(commonMethodsUtilService.getStringValueForObject(param[18]));
 	 							locs.append("  Parliament ");
 							}else if(param[12] != null && (Long)param[12] != 0l && (Long)param[12] == 11l){
 	 							locs.append(commonMethodsUtilService.getStringValueForObject(param[19]));
 	 							locs.append("  Hamlet ");
 							}else{
 	 							locs.append("");
 	 						}
 	 						returnVO.setFundSanctionId(commonMethodsUtilService.getLongValueForObject(param[13]));
 	 						returnVO.setLocName(locs);
 							returnMap.put(commonMethodsUtilService.getLongValueForObject(param[13]), returnVO);
 						}else{
 							
 							if(param[12] != null && (Long)param[12] != 0l && (Long)param[12] == 3l){
	 							returnVO.getLocName().append(","+commonMethodsUtilService.getStringValueForObject(param[8]));
	 							returnVO.getLocName().append("  District");
	 						}else if(param[12] != null && (Long)param[12] != 0l && (Long)param[12] == 4l){
	 							returnVO.getLocName().append(","+commonMethodsUtilService.getStringValueForObject(param[9]));
	 							returnVO.getLocName().append(" Constituency");
	 						}else if(param[12] != null && (Long)param[12] != 0l && (Long)param[12] == 5l){
	 							returnVO.getLocName().append(","+commonMethodsUtilService.getStringValueForObject(param[10]));
	 							returnVO.getLocName().append("  Mandal");
	 						}else if(param[12] != null && (Long)param[12] != 0l && (Long)param[12] == 6l){
	 							returnVO.getLocName().append(","+commonMethodsUtilService.getStringValueForObject(param[11]));
	 							returnVO.getLocName().append("  Panchayat");
	 						}else if(param[12] != null && (Long)param[12] != 0l && (Long)param[12] == 10l){
	 							returnVO.getLocName().append(","+commonMethodsUtilService.getStringValueForObject(param[18]));
	 							returnVO.getLocName().append("  Parliament");
	 						}else if(param[12] != null && (Long)param[12] != 0l && (Long)param[12] == 11l){
	 							returnVO.getLocName().append(","+commonMethodsUtilService.getStringValueForObject(param[19]));
	 							returnVO.getLocName().append("  Hamlet");
	 						}else{
	 							returnVO.getLocName().append("");
	 						}
 						}
 						
 						returnVO.setLocationName(commonMethodsUtilService.getStringValueForObject(param[2]));
 						returnVO.setWorkName(commonMethodsUtilService.getStringValueForObject(param[0]));
 						returnVO.setDepartmentName(commonMethodsUtilService.getStringValueForObject(param[3]));
 						returnVO.setSchemeName(commonMethodsUtilService.getStringValueForObject(param[4]));
 						returnVO.setGoNoDate(commonMethodsUtilService.getStringValueForObject(param[5]));
 					    returnVO.setAmount(commonMethodsUtilService.getLongValueForObject(param[6]));
 					    returnVO.setGovtOrderId(commonMethodsUtilService.getLongValueForObject(param[14]));
 					    returnVO.setGoNumber(commonMethodsUtilService.getStringValueForObject(param[15]));
 					    if(param[16] != null && commonMethodsUtilService.getStringValueForObject(param[16]).length() >= 10){
 					    	returnVO.setIssueDate(commonMethodsUtilService.getStringValueForObject(param[16]).substring(0, 10));
 					    }else{
 					    	returnVO.setIssueDate(" ");
 					    }
 					    returnVO.setFilePath(commonMethodsUtilService.getStringValueForObject(param[17]));
 						Long sanctionAmount= commonMethodsUtilService.getLongValueForObject(param[6]);  
 						returnVO.setSactionAmount(commonMethodsUtilService.calculateAmountInWords(sanctionAmount));
 					}
 				}
 				
 				if(commonMethodsUtilService.isMapValid(returnMap)){
	 				for(Entry<Long,LocationVO> entry : returnMap.entrySet()){
	 					finalReturnList.add(entry.getValue());
	 				}
 				}
 				
 			}catch(Exception e){
 				e.printStackTrace();
 				LOG.error(" Exception raised in getLocationWiseFundDetails (); ");
 			}
 			return finalReturnList;
 		}
 	
 	/*
	 *  Date : 14/06/2017
	 * Author :Hymavathi 
	 */
	@Override
	public List<LocationVO> getLocationWiseAmountAndCountDetails(InputVO inputVO){
		try{
			
			List<Long> levelValues = new ArrayList<Long>();
			Long levelId = inputVO.getBlockLevelId();
			
			Date fromDate = commonMethodsUtilService.stringTODateConvertion(inputVO.getFromDateStr(),"dd/MM/yyyy","");
			Date toDate = commonMethodsUtilService.stringTODateConvertion(inputVO.getToDateStr(),"dd/MM/yyyy","");
			/*Long locationId = null; 
			Long locationLevelId = null;
			if((inputVO.getLocationId() != null && inputVO.getLocationId().longValue() > 0L)){
				String locationIdStr = inputVO.getLocationId().toString();
				String locationLevelIdStr = locationIdStr.substring(0, 1);
				locationIdStr = locationIdStr.substring(1);
				locationId = Long.parseLong(locationIdStr);
				locationLevelId = Long.parseLong(locationLevelIdStr);
			}*/
			List<Object[]> amountList = null;
			
			List<Long> financialYearIdsList = commonMethodsUtilService.makeEmptyListByZeroValue(inputVO.getFinancialYrIdList());
			List<Long> deptIdsList = commonMethodsUtilService.makeEmptyListByZeroValue(inputVO.getDeptIdsList());
			List<Long> sourceIdsList = commonMethodsUtilService.makeEmptyListByZeroValue(inputVO.getSourceIdsList());
			List<Object[]> locations = null;
			if(inputVO.getBlockLevelId() != null && inputVO.getBlockLevelId().longValue() == IConstants.DISTRICT_LEVEL_SCOPE_ID){
				locations = districtDAO.getDistrictIdAndNameByDistrictIds(inputVO.getLevelValues());		
			}else if(inputVO.getBlockLevelId() != null && inputVO.getBlockLevelId().longValue() == IConstants.CONSTITUENCY_LEVEL_SCOPE_ID){
				locations = constituencyDAO.getConstIdAndNameByConstIds(inputVO.getLevelValues());	
			}else if(inputVO.getBlockLevelId() != null && inputVO.getBlockLevelId().longValue() == IConstants.MANDAL_LEVEL_SCOPE_ID){
				locations = tehsilDAO.getTehsilIdAndNameByIds(inputVO.getLevelValues());	
			}else if(inputVO.getBlockLevelId() != null && inputVO.getBlockLevelId().longValue() == IConstants.VILLAGE_LEVEL_SCOPE_ID){
				locations = panchayatDAO.getPanchayatIdAndNameByIds(inputVO.getLevelValues());	
			}
			amountList = fundSanctionDAO.getLocationWiseAmountAndCountDetails(fromDate,toDate,inputVO.getBlockLevelId(),financialYearIdsList,deptIdsList,sourceIdsList,inputVO.getLevelValues());
		
			
			//collect all the location ids(uses to create the final list)
			Set<Long> locationIdList = new HashSet<Long>();
			/*if(amountList != null && amountList.size() > 0){
				for(Object[] param : amountList){
					locationIdList.add(commonMethodsUtilService.getLongValueForObject(param[2]));
				}
			}*/
			Map<Long,String> locationIdAndNameMap = new HashMap<Long,String>();
			if(locations != null && locations.size() > 0){
				for(Object[] param : locations){
					locationIdList.add(commonMethodsUtilService.getLongValueForObject(param[0]));
					locationIdAndNameMap.put(commonMethodsUtilService.getLongValueForObject(param[0]), commonMethodsUtilService.getStringValueForObject(param[1]));
				}
			}
			
			//create a map for financialYearId and financialyear
			Map<Long,String> financialYearIdAndFinancialYearMap = new HashMap<Long,String>();
			List<Object[]> financialYearList = financialYearDAO.getAllFiniancialYearsByIds(financialYearIdsList);
			if(financialYearList != null && financialYearList.size() > 0){
				for(Object[] param : financialYearList){
					financialYearIdAndFinancialYearMap.put(commonMethodsUtilService.getLongValueForObject(param[0]), commonMethodsUtilService.getStringValueForObject(param[1]));
				}
			}
			
			//create a map for locationId and locationName
			
			
			//create a map of financialYearId and map of locationId and amount
			Map<Long,Map<Long,Long>> financialYearIdAndLocationIdAndAmountMap = new LinkedHashMap<Long,Map<Long,Long>>();
			Map<Long,Long> locationIdAndAmountMap = null;
			if(amountList != null && amountList.size() > 0){
				for(Object[] param : amountList){
					//locationIdAndNameMap.put(commonMethodsUtilService.getLongValueForObject(param[2]), commonMethodsUtilService.getStringValueForObject(param[3]));
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
			LOG.error("Exception Occurred in getLocationWiseAmountAndCountDetails() of FundManagementDashboardService ", e);
			return null;
		}
	}
	/*
	 * Date : 13/06/2017
	 * Author : kota.swathi@itgrids.com
	 * @description : to get min,max dates
	 * @return  IdNameVO
	 */
   public IdNameVO getMinMaxDates(){
	   IdNameVO dateVo = new IdNameVO();
	   Object[]  dates =fundSanctionDAO.getMinMaxDates();	
	   
	   SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");  
	    String minDate= formatter.format(dates[0]);  
	    String maxDate= formatter.format(dates[1]);  
	   if(dates != null && dates.length > 0){
		   //dateVo.setMinDate(dates[0] != null ? dates[0].toString().substring(0, 10) : "");
		   //dateVo.setMaxDate(dates[1] != null ? dates[1].toString().substring(0, 10) : "" );
		   
		   dateVo.setMinDate(dates[0] != null ? minDate : "");
		   dateVo.setMaxDate(dates[1] != null ? maxDate : "");
	   }   
			 
	   return dateVo;
   }
   
   /*
	 * Date : 15/06/2017
	 * Author :Swathi K
	 */
   public LocationFundDetailsVO getGrantTypeHighestAndLowestFund(InputVO inputVO ){
   	LocationFundDetailsVO returnVO = new LocationFundDetailsVO();
   	//SimpleDateFormat sf = new SimpleDateFormat("dd/MM/yyyy");
   	try{
   		Date startDate = commonMethodsUtilService.stringTODateConvertion(inputVO.getFromDateStr(),"dd/MM/yyyy","");
   		Date endDate = commonMethodsUtilService.stringTODateConvertion(inputVO.getToDateStr(),"dd/MM/yyyy","");
   	     
   		inputVO.setFinancialYrIdList(commonMethodsUtilService.makeEmptyListByZeroValue(inputVO.getFinancialYrIdList()));
   		inputVO.setDeptIdsList(commonMethodsUtilService.makeEmptyListByZeroValue(inputVO.getDeptIdsList()));
   		inputVO.setSourceIdsList(commonMethodsUtilService.makeEmptyListByZeroValue(inputVO.getSourceIdsList()));
   		inputVO.setSearchLvlVals(commonMethodsUtilService.makeEmptyListByZeroValue(inputVO.getSearchLvlVals()));
   		
   		Long totalfund = fundSanctionDAO.getTotalFund(inputVO.getFinancialYrIdList(),inputVO.getDeptIdsList(),inputVO.getSourceIdsList(),startDate,endDate,IConstants.CONSTITUENCY_LEVEL_SCOPE_ID,inputVO.getSearchLevelId(),inputVO.getSearchLvlVals(),null,null);
   		List<Object[]> grantFund = fundSanctionDAO.getGrantTypeHighestAndLowestFund(inputVO.getFinancialYrIdList(),inputVO.getDeptIdsList(),inputVO.getSourceIdsList(),startDate,endDate,inputVO.getType(),inputVO.getSearchLevelId(),inputVO.getSearchLvlVals());
   		if(grantFund != null && grantFund.size() >0){
   			setFundDetails(grantFund,returnVO,inputVO.getType(),totalfund);
   		}
   	}catch(Exception e){
   		LOG.error(" Exception raised in getGrantTypeHighestAndLowestFund (); ");
   	}
   	return returnVO;
   }
   public Object getRangeForFundAmount(List<Object[]> fundSanctionRangeList,Double fund){
	   try{
		   if(fundSanctionRangeList != null && fundSanctionRangeList.size() > 0 && fund != null){
			   for(Object[] param : fundSanctionRangeList){
				   if(commonMethodsUtilService.getStringValueForObject(param[1]).contains("-")){
					   String[] strArr = commonMethodsUtilService.getStringValueForObject(param[1]).split("-");
					   double leftVal = Double.parseDouble(strArr[0].trim());
					   double rightVal = Double.parseDouble(strArr[1].trim());
					   if(fund.doubleValue() >= leftVal && fund.doubleValue() <= rightVal){
						   return param[0];
					   }
				   }else{
					   double value = Double.parseDouble(param[1] != null ? param[1].toString() : "0.0d");
					   if(fund.doubleValue() > value){
						   return param[0];
					   }
				   }
				   
			   }
		   }
	   }catch(Exception e){
		   LOG.error("Exception Occurred in getRangeForFundAmount() of FundManagementDashboardService ", e);
	   }
	   return null;
   }
   public void setFundAndSchemeToVo(LocationFundDetailsVO retusnVo){
	   try{
		   List<Long> fundTypeIdList = Arrays.asList(IConstants.FUND_TYPE_IDS);
		   List<String> fundTypeList = Arrays.asList(IConstants.FUND_TYPE);
		   List<Long> schemeTypeIdList = Arrays.asList(IConstants.SCHEME_TYPE_IDS);
		   List<String> schemeTypeList = Arrays.asList(IConstants.SCHEME_TYPE);
		   
		   List<LocationFundDetailsVO> fundDetailsVOs = new ArrayList<LocationFundDetailsVO>();
		   LocationFundDetailsVO fundDetailsVO = null;
		   if(fundTypeIdList != null && fundTypeIdList.size() > 0){
			   for(int i = 0 ; i < fundTypeIdList.size() ; i++){
				   fundDetailsVO = new LocationFundDetailsVO();
				   fundDetailsVO.setId(commonMethodsUtilService.getLongValueForObject(fundTypeIdList.get(i)));
				   fundDetailsVO.setName(commonMethodsUtilService.getStringValueForObject(fundTypeList.get(i)));
				   fundDetailsVOs.add(fundDetailsVO);
			   }
		   }
		   retusnVo.getFundDetailsVOs().addAll(fundDetailsVOs);
		   
		   List<LocationFundDetailsVO> schemeDetailsVOs = new ArrayList<LocationFundDetailsVO>();
		   LocationFundDetailsVO schemeDetailsVO = null;
		   if(schemeTypeIdList != null && schemeTypeIdList.size() > 0){
			   for(int i = 0 ; i < schemeTypeIdList.size() ; i++){
				   schemeDetailsVO = new LocationFundDetailsVO();
				   schemeDetailsVO.setId(commonMethodsUtilService.getLongValueForObject(schemeTypeIdList.get(i)));
				   schemeDetailsVO.setName(commonMethodsUtilService.getStringValueForObject(schemeTypeList.get(i)));
				   schemeDetailsVOs.add(schemeDetailsVO);
			   }
		   }
		   retusnVo.getSchemeDetailsVOs().addAll(schemeDetailsVOs);
		   
	   }catch(Exception e){
		   LOG.error("Exception Occurred in setFundAndSchemeToVo() of FundManagementDashboardService ", e);
	   }
   }
   public List<LocationFundDetailsVO> getAllSubLocations(InputVO inputVO){
		try{
			List<LocationFundDetailsVO> detailsVOs = new ArrayList<LocationFundDetailsVO>();
			LocationFundDetailsVO locationFundDetailsVO = null;
			List<Object[]> locationList = null;
			Long levelId =inputVO.getSearchLevelId();
			Long locationId =inputVO.getSearchLevelValue();
			if(levelId != null && levelId == IConstants.STATE_LEVEL_SCOPE_ID){//get districtIds
				locationList = districtDAO.getDistrictIdName(locationId);
			}else if(levelId != null && levelId == IConstants.DISTRICT_LEVEL_SCOPE_ID){//get constituencyIds
				if(inputVO.getType() != null && inputVO.getType().equalsIgnoreCase("constituency"))
				 locationList= constituencyDAO.getConstituencies(locationId,null);
				else
				 locationList =parliamentAssemblyDAO.getParliamentIdAndName(locationId);
			}else if(levelId != null && levelId == IConstants.CONSTITUENCY_LEVEL_SCOPE_ID){//get tehsilIds
				locationList = tehsilConstituencyDAO.getTehsilIdAndName(locationId);
			}else if(levelId != null && levelId == IConstants.MANDAL_LEVEL_SCOPE_ID){//get panchayatIds
				locationList = panchayatDAO.getPanchayatIdAndName(locationId);
			}else if(levelId != null && levelId == IConstants.PARLIAMENT_CONSTITUENCY_LEVEL_SCOPE_ID){//get  parlaiamentIds
				//locationList =parliamentAssemblyDAO.getParliamentIdAndName(locationId);
				locationList =parliamentAssemblyDAO.getParliamentByConstIdAndName(locationId,null);
			}	
			if(locationList != null && locationList.size() > 0){
				for(Object[] param : locationList){
					locationFundDetailsVO = new LocationFundDetailsVO();
					    locationFundDetailsVO.setId(commonMethodsUtilService.getLongValueForObject(param[0]));
						locationFundDetailsVO.setName(commonMethodsUtilService.getStringValueForObject(param[1]));
						detailsVOs.add(locationFundDetailsVO);
				}
			}
			return detailsVOs;
		}catch(Exception e){
			LOG.error("Exception Occurred in getAllSubLocations() of FundManagementDashboardService ", e);
			return null;
		}
	}
   
   /*
	 * Date : 04/07/2017
	 * Author : Shrinu
	 * @description : To get GovtSchemesDetails
	 * @return  List<LocationFundDetailsVO>
	 */
   public List<LocationFundDetailsVO> getGovtSchemesDetails(){
		  List<LocationFundDetailsVO> finalReturnList= null;
		  try{
			finalReturnList=new ArrayList<LocationFundDetailsVO>();
		    List<Object[]> GovtSchemesObjs =govtSchemeDAO.getGovtSchemesDetails();
		    if(GovtSchemesObjs != null && GovtSchemesObjs.size()>0 && !GovtSchemesObjs.isEmpty()){
		    	for(Object[] Obj: GovtSchemesObjs){
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
	 * Date : 04/07/2017
	 * Author : Shrinu
	 * @description : To get GovtSubProgramsDetails
	 * @return  List<LocationFundDetailsVO>
	 */
   public List<LocationFundDetailsVO> getGovtSubProgramsDetails(Long govtSchemesId){
		  List<LocationFundDetailsVO> finalReturnList= null;
		  try{
			finalReturnList=new ArrayList<LocationFundDetailsVO>();
		    List<Object[]> govtGrantTypeObjs =subProgramDAO.getGovtSubProgramsDetails(govtSchemesId);
		    if(govtGrantTypeObjs != null && govtGrantTypeObjs.size()>0 && !govtGrantTypeObjs.isEmpty()){
		    	for(Object[] Obj: govtGrantTypeObjs){
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
	 * Date : 04/07/2017
	 * Author : Hymavathi
	 * @description : To get ALl Programes Amount Data
	 * @return  List<LocationFundDetailsVO>
	 */
   public List<LocationFundDetailsVO> getALlProgramesAmountDetails(InputVO inputVO){
	   List<LocationFundDetailsVO> returnList = new ArrayList<LocationFundDetailsVO>(0);
	   try{
		   List<GovtScheme> schemes = govtSchemeDAO.getAll();
		   List<GrantType>  grantTypes = grantTypeDAO.getAll();
		   List<IdNameVO> grantList = new ArrayList<IdNameVO>(0);
		   
		    inputVO.setFinancialYrIdList(commonMethodsUtilService.makeEmptyListByZeroValue(inputVO.getFinancialYrIdList()));
			inputVO.setDeptIdsList(commonMethodsUtilService.makeEmptyListByZeroValue(inputVO.getDeptIdsList()));
			inputVO.setSourceIdsList(commonMethodsUtilService.makeEmptyListByZeroValue(inputVO.getSourceIdsList()));
			inputVO.setSearchLvlVals(commonMethodsUtilService.makeEmptyListByZeroValue(inputVO.getSearchLvlVals()));
			
		   Map<Long,LocationFundDetailsVO> finalMap = new HashMap<Long,LocationFundDetailsVO>();
		   if(commonMethodsUtilService.isListOrSetValid(schemes)){
			   for (GovtScheme govtScheme : schemes) {
				   LocationFundDetailsVO schemeVO = new LocationFundDetailsVO();
				   schemeVO.setCount(0L);
				   schemeVO.setId(commonMethodsUtilService.getLongValueForObject(govtScheme.getGovtSchemeId()));
				   schemeVO.setName(commonMethodsUtilService.getStringValueForObject(govtScheme.getSchemeName()));
				   
				   schemeVO.setSubList(setGrantTypeList(grantTypes));
				   finalMap.put(govtScheme.getGovtSchemeId(),schemeVO);
				}
		   }
		   Date startDate = commonMethodsUtilService.stringTODateConvertion(inputVO.getFromDateStr(),"dd/MM/yyyy","");
			Date endDate = commonMethodsUtilService.stringTODateConvertion(inputVO.getToDateStr(),"dd/MM/yyyy","");
		   List<Object[]> objList = fundSanctionLocationDAO.getALlProgramesAmountDetails(inputVO,startDate,endDate);
		   
		   if(commonMethodsUtilService.isListOrSetValid(objList)){
			   for (Object[] param : objList) {
				   LocationFundDetailsVO schemeVO = finalMap.get(commonMethodsUtilService.getLongValueForObject(param[1]));
				   if(schemeVO != null){
					   IdNameVO grantVo = (IdNameVO) setterAndGetterUtilService.getMatchedVOfromList(schemeVO.getSubList(), "id", commonMethodsUtilService.getLongValueForObject(param[2]).toString());
					   if(grantVo != null){
						   Long  number = (long) (param[0] != null ? Double.valueOf(param[0].toString()) : 0l);
						   grantVo.setTotl(commonMethodsUtilService.calculateAmountInWords(number));//amount in crores for grantType
						   grantVo.setTotal(param[0] != null ? Double.valueOf(param[0].toString()) : 0l);
						   if(schemeVO.getCount() !=null && schemeVO.getCount().longValue() >0l){
							   schemeVO.setCount(schemeVO.getCount()+number);
						   }else{
							   schemeVO.setCount(number);
						   }
						   schemeVO.setTtlAmt(commonMethodsUtilService.calculateAmountInWords(schemeVO.getCount()));//amount in crores for schemeType
						}
					}
			   }
		   }
		  
		 if(commonMethodsUtilService.isMapValid(finalMap)) {
			 returnList.addAll(finalMap.values());
			 
			 Collections.sort(returnList,new Comparator<LocationFundDetailsVO>() {
				 public int compare(LocationFundDetailsVO o1, LocationFundDetailsVO o2) {
					if(o2.getCount() != null && o2.getCount().longValue() >= 0L && o1.getCount() != null && o1.getCount().longValue() >= 0L)
						return o2.getCount().compareTo(o1.getCount());
					else
						return 0;
				}
			});
		 }
		 /* MEARGINING MGNREGS DATA START */
		   if(inputVO.getDeptIdsList() == null || inputVO.getDeptIdsList().size() == 0 || inputVO.getDeptIdsList().contains(3l)){
			   LocationFundDetailsVO mgnresVO = getMgnregsOverviewData(inputVO);
			   returnList.add(mgnresVO);
		   }
		   /* END */
	   }catch(Exception e){
		   e.printStackTrace();
		   LOG.error("Exception Occurred in getALlProgramesAmountDetails() of FundManagementDashboardService ", e); 
	   }
	   return returnList;
  }
  
  public List<IdNameVO> setGrantTypeList(List<GrantType>  grantTypes){
	   List<IdNameVO> grantList = new ArrayList<IdNameVO>();
	   try{
		   if(commonMethodsUtilService.isListOrSetValid(grantTypes)){
			   for(GrantType grantType :grantTypes){
				   IdNameVO grantVO = new IdNameVO();
				   grantVO.setId(commonMethodsUtilService.getLongValueForObject(grantType.getGrantTypeId()));
				   grantVO.setName(commonMethodsUtilService.getStringValueForObject(grantType.getType()));
				   grantList.add(grantVO);
			   }
		   }
	   }catch(Exception e){
		   e.printStackTrace();
		   LOG.error("Exception Occurred in setGrantTypeList() of FundManagementDashboardService ", e); 
	   }
	   return grantList;
   }
  @Override
	public List<LocationFundDetailsVO> getAllSubLocationsOnsuperLocation(InputVO inputVO){
		try{
			List<LocationFundDetailsVO> detailsVOs = new ArrayList<LocationFundDetailsVO>();
			LocationFundDetailsVO locationFundDetailsVO = null;
			List<Object[]> locationList = null;
			String lvlIdStr = "";
			Date sDate = commonMethodsUtilService.stringTODateConvertion(inputVO.getFromDateStr(),"dd/MM/yyyy","");
			Date eDate = commonMethodsUtilService.stringTODateConvertion(inputVO.getToDateStr(),"dd/MM/yyyy","");
			
			inputVO.setFinancialYrIdList(commonMethodsUtilService.makeEmptyListByZeroValue(inputVO.getFinancialYrIdList()));
			String superLocationLevelIdStr = "";
			String superLocationIdStr = inputVO.getSuperLocationId().toString();
			
			superLocationLevelIdStr = superLocationIdStr.substring(0, 1);
			
			superLocationIdStr = superLocationIdStr.substring(1);
			Long superLocationId = Long.parseLong(superLocationIdStr);
			Long superLocationLevelId = Long.parseLong(superLocationLevelIdStr);
			//String type = "";
			
			List<Long> deptIdsList = commonMethodsUtilService.makeEmptyListByZeroValue(inputVO.getDeptIdsList());
			List<Long> sourceIdsList = commonMethodsUtilService.makeEmptyListByZeroValue(inputVO.getSourceIdsList());
			if(superLocationLevelId != null && superLocationLevelId.longValue() == IConstants.STATE_LEVEL_SCOPE_ID){//get districtIds
				locationList = fundSanctionDAO.getAllDistrictByStateId(superLocationId,inputVO.getFinancialYrIdList(),deptIdsList,sourceIdsList,sDate,eDate,inputVO.getBlockLevelId());
				lvlIdStr = IConstants.DISTRICT_LEVEL_SCOPE_ID.toString();
			}else if(superLocationLevelId != null && superLocationLevelId.longValue() == IConstants.DISTRICT_LEVEL_SCOPE_ID){//get parlaiamentIds
				locationList = fundSanctionDAO.getAllConstituencyByDistrictIds(superLocationId,inputVO.getFinancialYrIdList(),deptIdsList,sourceIdsList,sDate,eDate,inputVO.getBlockLevelId());
				lvlIdStr = IConstants.CONSTITUENCY_LEVEL_SCOPE_ID.toString();
			}else if(superLocationLevelId != null && superLocationLevelId.longValue() == IConstants.CONSTITUENCY_LEVEL_SCOPE_ID){//get tehsilIds
				locationList = fundSanctionDAO.getAllTehsilByConstituencyId(superLocationId,inputVO.getFinancialYrIdList(),deptIdsList,sourceIdsList,sDate,eDate,inputVO.getBlockLevelId());
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
					 String nameStr=commonMethodsUtilService.getStringValueForObject(param[1]);
						locationFundDetailsVO.setName(commonMethodsUtilService.toConvertStringToTitleCase(nameStr));
						detailsVOs.add(locationFundDetailsVO);
				}
			}
			return detailsVOs;
		}catch(Exception e){
			LOG.error("Exception Occurred in getAllSubLocationsBySuperLocationId() of FundManagementDashboardService ", e);
			return null;
		}
	}

  
  public LocationFundDetailsVO getSchemeWiseOverviewDetails(InputVO inputVO){
	   LocationFundDetailsVO returnVO = null;
	   try{
		   
		    Date startDate = commonMethodsUtilService.stringTODateConvertion(inputVO.getFromDateStr(),"dd/MM/yyyy","");
			Date endDate = commonMethodsUtilService.stringTODateConvertion(inputVO.getToDateStr(),"dd/MM/yyyy","");
			returnVO =getTotalFunds(inputVO);
		   Long avagecount = 0l;
		    
		   inputVO.setFinancialYrIdList(commonMethodsUtilService.makeEmptyListByZeroValue(inputVO.getFinancialYrIdList()));
		   inputVO.setDeptIdsList(commonMethodsUtilService.makeEmptyListByZeroValue(inputVO.getDeptIdsList()));
		   inputVO.setSourceIdsList(commonMethodsUtilService.makeEmptyListByZeroValue(inputVO.getSourceIdsList()));
		   inputVO.setSearchLvlVals(commonMethodsUtilService.makeEmptyListByZeroValue(inputVO.getSearchLvlVals()));
		   inputVO.setSchemeIdsList(commonMethodsUtilService.makeEmptyListByZeroValue(inputVO.getSchemeIdsList()));
		   inputVO.setSubProgramIdsList(commonMethodsUtilService.makeEmptyListByZeroValue(inputVO.getSubProgramIdsList()));
		   
		  List<Object[]> distFund = fundSanctionDAO.getLocationWiseFundHighAndLow(inputVO.getFinancialYrIdList(),inputVO.getDeptIdsList(),inputVO.getSourceIdsList(),startDate,endDate,3l,"highest",inputVO.getSearchLevelId(),inputVO.getSearchLvlVals(),inputVO.getSchemeIdsList(),inputVO.getSubProgramIdsList());
		   
		   String totalfund = returnVO.getTtlAmt().toString();
			if(distFund != null && distFund.size() >0){
				FundVO distVO = new FundVO();
				avagecount = 0l;
				if(totalfund != null ){
					avagecount = commonMethodsUtilService.roundUptoTwoDecimalPoint((Double.valueOf(commonMethodsUtilService.getStringValueForObject(totalfund))/Double.valueOf(IConstants.TOTAL_AP_TOTAL_DISTRICTS.toString()))).longValue();
				}
				setLocationWiseFund(distFund.get(0),distVO,"highest",avagecount);
				setLocationWiseFund(distFund.get(distFund.size() -1),distVO,"lowest",null);
				
				returnVO.getFundList().add(0, distVO);
			}
			List<Object[]> constFund = fundSanctionDAO.getLocationWiseFundHighAndLow(inputVO.getFinancialYrIdList(),inputVO.getDeptIdsList(),inputVO.getSourceIdsList(),startDate,endDate,4l,"highest",inputVO.getSearchLevelId(),inputVO.getSearchLvlVals(),inputVO.getSchemeIdsList(),inputVO.getSubProgramIdsList());
			if(constFund != null && constFund.size() >0){
				FundVO consVO = new FundVO();
				avagecount = 0l;
				if(totalfund != null ){
					avagecount = commonMethodsUtilService.roundUptoTwoDecimalPoint((Double.valueOf(commonMethodsUtilService.getStringValueForObject(totalfund))/Double.valueOf(IConstants.TOTAL_AP_TOTAL_CONSTITUENCIES.toString()))).longValue();
				}
				setLocationWiseFund(constFund.get(0),consVO,"highest",avagecount);
				setLocationWiseFund(constFund.get(constFund.size() -1),consVO,"lowest",null);
				returnVO.getFundList().add(1, consVO);
			}
			
			List<Object[]> mandalFund = fundSanctionDAO.getLocationWiseFundHighAndLow(inputVO.getFinancialYrIdList(),inputVO.getDeptIdsList(),inputVO.getSourceIdsList(),startDate,endDate,5l,"highest",inputVO.getSearchLevelId(),inputVO.getSearchLvlVals(),inputVO.getSchemeIdsList(),inputVO.getSubProgramIdsList());
			if(mandalFund != null && mandalFund.size() >0){
				FundVO mandalVO = new FundVO();
				avagecount = 0l;
				if(totalfund != null ){
					avagecount = commonMethodsUtilService.roundUptoTwoDecimalPoint((Double.valueOf(commonMethodsUtilService.getStringValueForObject(totalfund))/Double.valueOf(IConstants.TOTAL_AP_TOTAL_MANDALS.toString()))).longValue();
				}
				setLocationWiseFund(mandalFund.get(0),mandalVO,"highest",avagecount);
				setLocationWiseFund(mandalFund.get(mandalFund.size() -1),mandalVO,"lowest",null);
				returnVO.getFundList().add(2, mandalVO);
			}
			
			// For All Senariao Add Nrega Count
			if((inputVO.getDeptIdsList() == null ||  inputVO.getDeptIdsList().size() == 0) && (inputVO.getSchemeIdsList() == null || inputVO.getSchemeIdsList().size() == 0)){
				LocationFundDetailsVO AllVO = getMgnregsTopLowAverageLocation(inputVO);
				if(inputVO.getSchemeIdsList() == null || inputVO.getSchemeIdsList().size() == 0){
					if(AllVO != null){
						returnVO.setTotalAmt(String.valueOf(Double.valueOf(returnVO.getTotalAmt().replaceAll(",", ""))+Double.valueOf(AllVO.getTotalAmt())));
					}
				}
			}
			
			 /* MEARGINING MGNREGS DATA START */
			   if((inputVO.getDeptIdsList() == null ||  inputVO.getDeptIdsList().size() == 0 || inputVO.getDeptIdsList().contains(3l)) && (inputVO.getSchemeIdsList() != null && inputVO.getSchemeIdsList().contains(-1l))){//-1 we are sending scheme id for mgnregs from ui for identification purpose
				  returnVO = getMgnregsTopLowAverageLocation(inputVO);
				}
			   /* END */
			
			
	   }catch(Exception e){
		   e.printStackTrace();
		   LOG.error("Exception Occurred in getSchemeWiseOverviewDetails() of FundManagementDashboardService ", e); 
	   }
	   
	   return returnVO;
   }
   
   public void setLocationWiseFund(Object[] obj ,FundVO returnVO,String type,Long avagecount){
		try{
			
				Long  number = Long.valueOf(commonMethodsUtilService.getStringValueForObject(obj[0]));
			    
			    if(type != null && type.equalsIgnoreCase("highest")){
			    	returnVO.setHighLocId(commonMethodsUtilService.getLongValueForObject(obj[1]));
			    	returnVO.setHighLocName(commonMethodsUtilService.getStringValueForObject(obj[2]));
			    	returnVO.setHighCroreAmt(commonMethodsUtilService.calculateAmountInWords(number));
					returnVO.setHighTotalAmt(commonMethodsUtilService.getLongValueForObject(obj[0]));
			    }else if(type != null && type.equalsIgnoreCase("lowest")){
			    	returnVO.setLowLocId(commonMethodsUtilService.getLongValueForObject(obj[1]));
			    	returnVO.setLowLocName(commonMethodsUtilService.getStringValueForObject(obj[2]));
			    	returnVO.setLowCroreAmt(commonMethodsUtilService.calculateAmountInWords(number));
					returnVO.setLowTotalAmt(commonMethodsUtilService.getLongValueForObject(obj[0]));
			    } 
			    
			    if(avagecount != null){
			    	returnVO.setAvgAmt(avagecount);
			    	returnVO.setAvgCroreAmt(commonMethodsUtilService.calculateAmountInWords(avagecount));
				}
				
		}catch(Exception e){
			e.printStackTrace();
			LOG.error(" Exception raised in setLocationWiseFund (); ");
		}
	}
   
   /*
 	 * Date : 28/07/2017
 	 * Author : Shrinu
 	 * @description : To get GovtSubProgramsDetails
 	 * @return  List<LocationFundDetailsVO>
 	 */
    public List<LocationFundDetailsVO> getGovtGrantTypeDetails(Long programId, Long govtSchemesId){
 		  List<LocationFundDetailsVO> finalReturnList= null;
 		  try{
 			finalReturnList=new ArrayList<LocationFundDetailsVO>();
 		    List<Object[]> govtSubPrgrmObjs =grantTypeDAO.getGovtGrantTypeDetails(programId,govtSchemesId);
 		    if(govtSubPrgrmObjs != null && govtSubPrgrmObjs.size()>0 && !govtSubPrgrmObjs.isEmpty()){
 		    	for(Object[] Obj: govtSubPrgrmObjs){
 		    		LocationFundDetailsVO vo=new LocationFundDetailsVO();
 		    		vo.setId(commonMethodsUtilService.getLongValueForObject(Obj[0]));
 		    		vo.setName(commonMethodsUtilService.getStringValueForObject(Obj[1]));
 		    		finalReturnList.add(vo);
 		    	}
 		    }

 		  }catch(Exception e){
 		   // e.printStackTrace();
 		    LOG.error(" Exception raised in getGovtGrantTypeDetails (); ");
 		  }
 		return finalReturnList;  
 		}
    /*
 	 * Date : 02/08/2017
 	 * Author : Srishailam Pittala
 	 * @description : To get GovtSubProgramsDetails
 	 * @return  List<LocationFundDetailsVO>
 	 */
    public List<LocationFundDetailsVO> getGovtSchemsTypeDetails(Long programId, Long grantTypeId){
 		  List<LocationFundDetailsVO> finalReturnList= null;
 		  try{
 			finalReturnList=new ArrayList<LocationFundDetailsVO>();
 		    List<Object[]> govtSubPrgrmObjs =grantTypeDAO.getGovtSchemsTypeDetails(programId,grantTypeId);
 		    if(govtSubPrgrmObjs != null && govtSubPrgrmObjs.size()>0 && !govtSubPrgrmObjs.isEmpty()){
 		    	for(Object[] Obj: govtSubPrgrmObjs){
 		    		LocationFundDetailsVO vo=new LocationFundDetailsVO();
 		    		vo.setId(commonMethodsUtilService.getLongValueForObject(Obj[0]));
 		    		vo.setName(commonMethodsUtilService.getStringValueForObject(Obj[1]));
 		    		finalReturnList.add(vo);
 		    	}
 		    }
 		  }catch(Exception e){
 		    LOG.error(" Exception raised in getGovtSchemsTypeDetails (); ");
 		  }
 		return finalReturnList;  
 	}
    
    /* Author:Santosh */
    /* MGNEREGS Service Start */ 
    public void mergeMgnregsLocationWiseData(List<FundSchemeVO> returnList,InputVO inputVO,Map<Long,FundSchemeVO> subProgramMap) {
    	  if (inputVO.getViewType().trim().length() == 0) {
    		  inputVO.setViewType("yearwise");
    	  }
		  try {
			   if (inputVO.getFinancialYrIdList() != null && inputVO.getFinancialYrIdList().size()==1 && inputVO.getFinancialYrIdList().contains(0l)){
				   inputVO.getFinancialYrIdList().clear();
			   }
			   String finalcialYear = getFinancilalYear(inputVO).getRangeType();//getting financial year min and max year 
			  
			     boolean flag = false;
				List<Long> deptIdsList = commonMethodsUtilService.makeEmptyListByZeroValue(inputVO.getDeptIdsList());
				
				if (deptIdsList == null) {
					deptIdsList = new ArrayList<Long>(0);
				}
				flag = (deptIdsList.size() > 1 && deptIdsList.contains(3l));
				if (!flag) {
					flag = (deptIdsList.size() == 0);
				}
			if (flag) { /* Merging MGNEREGS data into FMS in the case of multiple department selected */
				Map<Long, String> mgneregsLocationMap = getMgneregslocationMappingIds(inputVO).getSubMap1();//getting mgregs location code
				List<FundSchemeVO> locationDtlsList = getLocationWiseMgnregsData(inputVO);
				if (returnList != null && returnList.size() > 0) {
					for (FundSchemeVO fundSchemeVO : returnList) {
						AddressVO addressVO = fundSchemeVO.getAddressVO();
						Long locationId = 0l;
						if (inputVO.getBlockLevelId() != null && inputVO.getBlockLevelId() == 2l) {// State
							locationId = addressVO.getStateId();
						} else {
							locationId = addressVO.getId();
						}
						addressVO.setLocationIdStr(mgneregsLocationMap.get(locationId));
						DecimalFormat f = new DecimalFormat("##.000");
						if (inputVO.getViewType() != null && inputVO.getViewType().trim().equalsIgnoreCase("cumulative")) {
							fundSchemeVO.getSubList().get(0).setYear(finalcialYear);//setting cumulative financial year
							FundSchemeVO mgnregsLocationVO = getMatchVO(locationDtlsList,addressVO.getLocationIdStr());
							if (mgnregsLocationVO != null) {
								 //setting mgnregs location id
								fundSchemeVO.setLocationIdStr(mgnregsLocationVO.getLocationIdStr());
								
								if (fundSchemeVO.getSubList().size() > 0) {
									FundSchemeVO mgneregsVO = mgnregsLocationVO.getSubList().get(0).getSubList().get(0);
									for (FundSchemeVO fundSchemeVO2 : fundSchemeVO.getSubList()) {
										fundSchemeVO2.getSubList().add(0,mgneregsVO);
										fundSchemeVO.setAmount(f.format(Double.valueOf(fundSchemeVO.getAmount())+ Double.valueOf(mgneregsVO.getAmount())));
										fundSchemeVO.setCount((fundSchemeVO.getCount() != null ? fundSchemeVO.getCount():0l)+ mgneregsVO.getCount());
									}
								}
								locationDtlsList.remove(mgnregsLocationVO);//removing object once added into result list
							} else {
								for (FundSchemeVO fundSchemeVO2 : fundSchemeVO.getSubList()) {
									fundSchemeVO2.getSubList().add(0,getStaticTemplate());
								}
							}
						} else {
							for (FundSchemeVO fundVO : fundSchemeVO.getSubList()) {
								FundSchemeVO mgnregsLocationVO = getLocationIdAndYearMatchVO(locationDtlsList,addressVO.getLocationIdStr(),fundVO.getYear());
								if (mgnregsLocationVO != null) {
									//setting mgnregs location id
									fundSchemeVO.setLocationIdStr(mgnregsLocationVO.getLocationIdStr());
									fundVO.getSubList().add(0,mgnregsLocationVO.getSubList().get(0).getSubList().get(0));
									fundSchemeVO.setAmount(f.format(Double.valueOf(fundVO.getAmount()) + Double.valueOf(mgnregsLocationVO.getSubList().get(0).getAmount())));
									
									FundSchemeVO mgneregsVO = mgnregsLocationVO.getSubList().get(0);
									fundVO.setAmount(f.format(Double.valueOf(fundVO.getAmount())+ Double.valueOf(mgneregsVO.getAmount())));
									fundVO.setCount((fundVO.getCount() != null ? fundVO.getCount():0l)+ mgneregsVO.getCount());
									
									locationDtlsList.remove(mgnregsLocationVO);//removing object once added into result list
								} else {
									fundVO.getSubList().add(0,getStaticTemplate());
								}
							}
						}
					}
				}
				//Adding Mgnregs location data into result list.Which location don't exist in fms result list but in mgnregs data is there. 
				if (locationDtlsList != null && locationDtlsList.size() > 0) {
					 Map<String, Long> locationMap = null;
					 Map<Long, Long> constituencyDistrictIdMap = null;
					 Map<Long, Long> mandalConstituenyMap = null;
					 
					  locationMap = getMgneregslocationMappingIds(inputVO).getSubMap2();//getting location based on UI selection
						
			         if(inputVO.getBlockLevelId().longValue() == IConstants.CONSTITUENCY_LEVEL_SCOPE_ID || inputVO.getBlockLevelId().longValue() == IConstants.MANDAL_LEVEL_SCOPE_ID){
	        			 constituencyDistrictIdMap = getLocationIdsDetails("constituency");
	        		 }
	    			 if(inputVO.getBlockLevelId().longValue() == IConstants.MANDAL_LEVEL_SCOPE_ID){		
	    				 mandalConstituenyMap = getLocationIdsDetails("mandal");
	    			 }
					
					
					for (FundSchemeVO fundSchemeVO : locationDtlsList) {
						
						fundSchemeVO.getSubList().get(0).getSubList().addAll(subProgramMap.values());
						 /* Mapping prrws location id*/
						 if(inputVO.getBlockLevelId().longValue() == IConstants.DISTRICT_LEVEL_SCOPE_ID) {
							
							 fundSchemeVO.getAddressVO().setDistrictId(locationMap.get(fundSchemeVO.getLocationIdStr()));
							 fundSchemeVO.getAddressVO().setId(fundSchemeVO.getAddressVO().getDistrictId());
							 
							 
				         } else if(inputVO.getBlockLevelId().longValue() == IConstants.CONSTITUENCY_LEVEL_SCOPE_ID){
		        			
				        	 fundSchemeVO.getAddressVO().setAssemblyId(locationMap.get(fundSchemeVO.getLocationIdStr()));
		        			 fundSchemeVO.getAddressVO().setId(fundSchemeVO.getAddressVO().getAssemblyId());
							 
		        			 fundSchemeVO.getAddressVO().setDistrictId(constituencyDistrictIdMap.get(fundSchemeVO.getAddressVO().getAssemblyId()));
		        		 } else if(inputVO.getBlockLevelId().longValue() == IConstants.MANDAL_LEVEL_SCOPE_ID){		
		    				
		        			 fundSchemeVO.getAddressVO().setTehsilId(locationMap.get(fundSchemeVO.getLocationIdStr()));
		    				 fundSchemeVO.getAddressVO().setId(fundSchemeVO.getAddressVO().getTehsilId());
							 
		    				 fundSchemeVO.getAddressVO().setAssemblyId(mandalConstituenyMap.get(fundSchemeVO.getAddressVO().getTehsilId()));
		    				 fundSchemeVO.getAddressVO().setDistrictId(constituencyDistrictIdMap.get(fundSchemeVO.getAddressVO().getAssemblyId()));
		    			 }
						returnList.add(fundSchemeVO);
					}
				}
			}
			
			/* Sending only MGNEREGS data once only MGNEREGS has selected */
		   if (deptIdsList != null && deptIdsList.size() == 1 && deptIdsList.contains(3l)) {
			    List<FundSchemeVO> finalList =  getLocationWiseMgnregsData(inputVO);
			    returnList.clear();
			   returnList.addAll(finalList);
		   }
		 } catch (Exception e) {
			  LOG.error(" Exception occured at mergeMgnregsLocationWiseData() in  FundManagementDashboardService class ",e);
		}
   }

     public Map<Long,Long> getLocationIdsDetails(String type) {
    	 Map<Long,Long> locationIdMap = new HashMap<>();
    	  try {
    		  List<Object[]> objList = null;
    		  if (type.equalsIgnoreCase("constituency")) {
    			  objList = constituencyDAO.getConstituencyDistrictId();
    		  } else if (type.equalsIgnoreCase("mandal")) {
    			  objList = prTehsilDAO.getTehsilConstituencyId();
    		  }
    		  if (objList != null && objList.size() > 0){
    			   for (Object[] param : objList) {
    				   locationIdMap.put(commonMethodsUtilService.getLongValueForObject(param[0]), commonMethodsUtilService.getLongValueForObject(param[1]));
				}
    		  }
    	  } catch (Exception e) {
    		  LOG.error(" Exception occured at getLocationIdsDetails() in FundManagementDashboardService class",e);  
    	  }
    	  return locationIdMap;
     }
     public FundSchemeVO getLocationIdAndYearMatchVO(List<FundSchemeVO> resultList,String locationIdStr,String year) {
    	  try {
    		   if (resultList == null || resultList.size() == 0 )
    			   return null;
    		   for (FundSchemeVO locationVO:resultList) {
    			    if (locationVO.getLocationIdStr().equalsIgnoreCase(locationIdStr) && locationVO.getYear().equalsIgnoreCase(year)) {
    			    	return locationVO;
    			    }
    		   }
    		  
    	  } catch (Exception e) {
    		  LOG.error(" Exception occured at getLocationIdAndYearMatchVO() in FundManagementDashboardService class ",e);
    	  }
    	  return null;
     }
	public List<FundSchemeVO> getLocationWiseMgnregsData(InputVO inputVO) {
		 List<FundSchemeVO> locationList = new ArrayList<>(0);
		try {
			prpareRquiredParameter(inputVO, "");
			String str = convertingInputVOToString(inputVO);
			ClientResponse response = webServiceUtilService.callWebService("http://dbtrd.ap.gov.in/NregaDashBoardService/rest/FMSExpenditureABSNewService/FMSExpenditureOverviewNew",str);

			Map<String, String> cnstuncyPrlmntIdNameMap = null;
			Map<String, String> nregaPanchayatMandalCodeMap = null;
			Map<String, String> nregaMandalConsituencyCodeMap = null;
			Map<String,Long> yearIdMap = getFinancilalYear(inputVO).getSubMap(); //getting financialYear and id

			/* taking location details in the case of parliament based on  requirement */
			 if (inputVO.getSubFilterType() !=  null && inputVO.getSubFilterType().equalsIgnoreCase("parliament")) {
				 
				if (inputVO.getSublocationType() != null && inputVO.getSublocationType().equalsIgnoreCase("constituency") || inputVO.getSublocationType().equalsIgnoreCase("mandal") || inputVO.getSublocationType().equalsIgnoreCase("panchayat")) {
					cnstuncyPrlmntIdNameMap = getConstituencyParliamentNameMapping(null);
				}  
				if (inputVO.getSublocationType() != null && inputVO.getSublocationType().equalsIgnoreCase("mandal") ) {
					nregaMandalConsituencyCodeMap = getLocationDetailsByLocationType(inputVO.getSublocationType());
				} 
				if (inputVO.getSublocationType() != null && inputVO.getSublocationType().equalsIgnoreCase("panchayat")) {
					nregaPanchayatMandalCodeMap = getLocationDetailsByLocationType(inputVO.getSublocationType());
					nregaMandalConsituencyCodeMap = getLocationDetailsByLocationType("mandal");
				}
			 }

			if (response.getStatus() != 200) {
				throw new RuntimeException("Failed : HTTP error code : " + response.getStatus());
			} else {
				String output = response.getEntity(String.class);
				DecimalFormat f = new DecimalFormat("##");
				if (output != null && !output.isEmpty()) {
					JSONArray finalArray = new JSONArray(output);
					if (finalArray != null && finalArray.length() > 0) {
						for (int i = 0; i < finalArray.length(); i++) {
							JSONObject jObj = (JSONObject) finalArray.get(i);
							String subLocationType = inputVO.getSublocationType() != null ? inputVO.getSublocationType():"";
							String year = "";
							if(inputVO.getViewType() != null && inputVO.getViewType().trim().equalsIgnoreCase("cumulative")) {
								year = inputVO.getRangeType();//this key contain cumulative year
							} else {
								year = jObj.getString("FIN_YEAR");
							}
							
							FundSchemeVO locationVO = new FundSchemeVO();
							if (subLocationType.equalsIgnoreCase("state")) {
								locationVO.setLocationIdStr("01");								
							} else {
								locationVO.setLocationIdStr(jObj.getString("UNIQUEID"));	
							}
							
							locationVO.setCount(jObj.getLong("WORKS"));
							locationVO.setAmount(cnvrtLakhIntoCrores(jObj.getString("MATERIAL")));
							locationVO.setTotalCount(Long.valueOf(f.format(Double.valueOf(jObj.getString("MATERIAL")))));
							locationVO.setYear(year);
							locationVO.setYearId((yearIdMap.get(year)) != null ? yearIdMap.get(year):0l);
							AddressVO addressVO = new AddressVO();

					
							
							if (subLocationType.equalsIgnoreCase("district") || subLocationType.equalsIgnoreCase("constituency") || subLocationType.equalsIgnoreCase("mandal") || subLocationType.equalsIgnoreCase("panchayat")) {
								addressVO.setDistrictName(jObj.getString("DISTRICT"));
								addressVO.setName(addressVO.getDistrictName());//this #Name key is only used for sorting purpose only 
	 	    				}
	 	    				if (subLocationType.equalsIgnoreCase("constituency") || subLocationType.equalsIgnoreCase("mandal") || subLocationType.equalsIgnoreCase("panchayat")) {
	 	    					addressVO.setAssemblyName(jObj.getString("CONSTITUENCY"));
	 	    					addressVO.setName(addressVO.getAssemblyName());
	 	    				}
	 	    				if (subLocationType.equalsIgnoreCase("mandal") || subLocationType.equalsIgnoreCase("panchayat")) {
	 	    					addressVO.setTehsilName(jObj.getString("MANDAL"));
	 	    					addressVO.setName(addressVO.getTehsilName());
	 	    				}
	 	    				if (subLocationType.equalsIgnoreCase("panchayat")) {
	 	    					addressVO.setPanchayatName(jObj.getString("PANCHAYAT"));
	 	    					addressVO.setName(addressVO.getPanchayatName());
	 	    				}
							
							/* Setting locationCode in the case of parliament to do some operation by locationCode.*/
							
							 if (inputVO.getSubFilterType() !=  null && inputVO.getSubFilterType().equalsIgnoreCase("parliament")){
								 
								if (inputVO.getSublocationType() != null && inputVO.getSublocationType() .equalsIgnoreCase("constituency")) {
									if (cnstuncyPrlmntIdNameMap != null) {
										addressVO.setParliamentName(cnstuncyPrlmntIdNameMap.get(locationVO.getLocationIdStr()));
									}
									addressVO.setAssemblyIdStr(locationVO.getLocationIdStr());
								} else if (inputVO.getSublocationType() != null && inputVO.getSublocationType().equalsIgnoreCase("mandal")) {
									if (nregaMandalConsituencyCodeMap != null) {
										addressVO.setAssemblyIdStr(nregaMandalConsituencyCodeMap.get(locationVO.getLocationIdStr()));
									}
									if (cnstuncyPrlmntIdNameMap != null) {
										addressVO.setParliamentName(cnstuncyPrlmntIdNameMap.get(addressVO.getAssemblyIdStr()));
									}
								} else if (inputVO.getSublocationType() != null && inputVO.getSublocationType().equalsIgnoreCase("panchayat")) {
									if (nregaPanchayatMandalCodeMap != null) {
										addressVO.setTehsilIdStr(nregaPanchayatMandalCodeMap.get(locationVO.getLocationIdStr()));
									}
									if (nregaMandalConsituencyCodeMap != null) {
										addressVO.setAssemblyIdStr(nregaMandalConsituencyCodeMap.get(addressVO.getTehsilIdStr()));
									}
									if (cnstuncyPrlmntIdNameMap != null) {
										addressVO.setParliamentName(cnstuncyPrlmntIdNameMap.get(addressVO.getAssemblyIdStr()));
									}
								}
								if(addressVO.getParliamentName() == null){
									addressVO.setParliamentName(" "); 
								}
							}

							locationVO.setAddressVO(addressVO);

							FundSchemeVO mgneregsTemplateVO = new FundSchemeVO();
							mgneregsTemplateVO.setYear(year);
							mgneregsTemplateVO.setYearId((yearIdMap.get(year)) != null ? yearIdMap.get(year):0l);
							mgneregsTemplateVO.setName("MGNREGS");
							mgneregsTemplateVO.setAmount(cnvrtLakhIntoCrores(jObj.getString("MATERIAL")));
							mgneregsTemplateVO.setTotalCount(Long.valueOf(f.format(Double.valueOf(jObj.getString("MATERIAL")))));
							mgneregsTemplateVO.setCount(jObj.getLong("WORKS"));
							mgneregsTemplateVO.setAddressVO(addressVO);

							FundSchemeVO deptVO = new FundSchemeVO();
							deptVO.setId(0l);
							deptVO.setName("MGNREGS");
							deptVO.setAmount(mgneregsTemplateVO.getAmount());
							deptVO.setTotalCount(mgneregsTemplateVO.getTotalCount());
							deptVO.setCount(mgneregsTemplateVO.getCount());

							mgneregsTemplateVO.getSubList().add(deptVO);

							locationVO.getSubList().add(mgneregsTemplateVO);
							locationList.add(locationVO);
						}
					}
				}
			}

			// Calculating Parliament Data
			if (inputVO.getSubFilterType() !=  null && inputVO.getSubFilterType().equalsIgnoreCase("parliament")) {
				
				boolean flag1 = (inputVO.getBlockLevelId() == IConstants.TEMP_PARLIAMENT_CONSTITUENCY_LEVEL_SCOPE_ID && inputVO .getSearchLevelId() == IConstants.TEMP_PARLIAMENT_CONSTITUENCY_LEVEL_SCOPE_ID);
				boolean flag2 = ((inputVO.getBlockLevelId() == IConstants.CONSTITUENCY_LEVEL_SCOPE_ID || inputVO.getBlockLevelId() == IConstants.MANDAL_LEVEL_SCOPE_ID || inputVO.getBlockLevelId() == IConstants.VILLAGE_LEVEL_SCOPE_ID) 
						        && (inputVO.getSearchLevelId() == IConstants.TEMP_PARLIAMENT_CONSTITUENCY_LEVEL_SCOPE_ID));
				if (flag1) {
					if(inputVO.getViewType() != null && inputVO.getViewType().trim().equalsIgnoreCase("cumulative")) {
						locationList = getParliamentWiseDataForDistrictLevelBlock(inputVO, locationList);
					} else {
						locationList = getParliamentWiseDataForDistrictLevelBlockYearWise(inputVO, locationList);
					}
					return locationList;
				} else if (flag2) {
					//if (inputVO.getDeptIdsList() != null && inputVO.getDeptIdsList().size() == 1 && inputVO.getDeptIdsList().contains(3l) && inputVO.getLevelValues().size() > 0) {
					if (inputVO.getLevelValues() != null && inputVO.getLevelValues().size()>0) {
						Map<String, String> cnstuncyCodeMap = getConstituencyParliamentNameMapping((inputVO.getLevelValues().get(0)));
						Iterator<FundSchemeVO> iter = locationList.iterator();
						/*
						 * in the case of parliament selection for we are removing
						 * those location which do not belong in that parliament
						 */
						while (iter.hasNext()) {
							FundSchemeVO locationVO = iter.next();
							AddressVO addressVO = locationVO.getAddressVO();
							if (!cnstuncyCodeMap.containsKey(addressVO.getAssemblyIdStr())) {
								iter.remove();
							}
						}
					}
					//}
				}
			}
		} catch (Exception e) {
			LOG.error(" Exception raised at getLocationWiseMgnregsData () in FundManagementDashboardService class ",e);
		}
		return locationList;
	}
    public List<FundSchemeVO> getParliamentWiseDataForDistrictLevelBlock(InputVO inputVO,List<FundSchemeVO> locationDtlsList) {
    	Map<String,FundSchemeVO> paraliamentMap = new HashMap<>(0);
    	try {
    		
    	Map<Long,String> paCnsttncyIdAndNrgCnsttuncyCodeMap = new HashMap<>(0); 
		List<Object[]>	constituencyobjLst = constituencyDAO.getMgnregsConstituencyMappingCode(null);
		 if (constituencyobjLst != null && constituencyobjLst.size() > 0){
			  for (Object[] param : constituencyobjLst) {
				  paCnsttncyIdAndNrgCnsttuncyCodeMap.put(commonMethodsUtilService.getLongValueForObject(param[0]), commonMethodsUtilService.getStringValueForObject(param[1]));
			}
		 }
		  Long locationId = 0l;
		  if (inputVO.getLevelValues().size() > 0){
			  locationId = inputVO.getLevelValues().get(0);
		  }
		  String filterType=null;
		  if (inputVO.getBlockLevelId().longValue() == IConstants.DISTRICT_LEVEL_SCOPE_ID) {
			  filterType = "district";
		  } else {
			  filterType = "parliament";
		  }
    	  List<Object[]>  parliamentConstituencyObjLst = parliamentAssemblyDAO.getParliamentWiseConstituency(locationId,filterType);
    	 if (parliamentConstituencyObjLst != null && parliamentConstituencyObjLst.size() > 0 ) {
    		 for (Object[] param : parliamentConstituencyObjLst) {
    			 String parliamentId = commonMethodsUtilService.getStringValueForObject(param[0]);
    			 Long constituencyId = commonMethodsUtilService.getLongValueForObject(param[2]);
    			 FundSchemeVO parliamentVO = paraliamentMap.get(parliamentId);
    			  if (parliamentVO == null ){//this Empty VO setting with location for parliament as template
    				    parliamentVO = new FundSchemeVO();
    				    parliamentVO.setLocationIdStr(parliamentId);
    				    parliamentVO.setCount(0l);
    				    parliamentVO.setAmount("0.0");
    				    parliamentVO.setTotalCount(0l);
    				   
    				    AddressVO addressVO = new AddressVO();
	    				addressVO.setDistrictName(commonMethodsUtilService.getStringValueForObject(param[5]));
	    				addressVO.setAssemblyName(commonMethodsUtilService.getStringValueForObject(param[3]));
	    				addressVO.setParliamentId(commonMethodsUtilService.getLongValueForObject(param[0]));
	    				addressVO.setParliamentName(commonMethodsUtilService.getStringValueForObject(param[1]));
	    				parliamentVO.setAddressVO(addressVO);
	    				
	    				FundSchemeVO mgneregsTemplateVO = new FundSchemeVO();
	    				mgneregsTemplateVO.setCount(0l);
	    				mgneregsTemplateVO.setAmount(parliamentVO.getAmount());
	    				mgneregsTemplateVO.setTotalCount(parliamentVO.getTotalCount());
	    				mgneregsTemplateVO.setAddressVO(addressVO);
	    				
	    				FundSchemeVO deptVO = new FundSchemeVO();
	    				deptVO.setId(0l);
	    				deptVO.setName("MGNREGS");
	    				deptVO.setAmount(parliamentVO.getAmount());
	    				deptVO.setTotalCount(parliamentVO.getTotalCount());
	    				deptVO.setCount(0l);
	    				mgneregsTemplateVO.getSubList().add(deptVO);
	    				parliamentVO.getSubList().add(mgneregsTemplateVO);
    				    paraliamentMap.put(parliamentId, parliamentVO);
    			  }
    			  if (paCnsttncyIdAndNrgCnsttuncyCodeMap.get(constituencyId) != null ){
    				   FundSchemeVO constituencyVO = getMatchVO(locationDtlsList,paCnsttncyIdAndNrgCnsttuncyCodeMap.get(constituencyId));
	       			   if (constituencyVO != null ){
	       				   parliamentVO.setYear(constituencyVO.getYear());
	       				   parliamentVO.setYearId(0l);
	       				   parliamentVO.getSubList().get(0).setYear(constituencyVO.getYear());
	       				   parliamentVO.getSubList().get(0).setYearId(0l);
	       				   setRequiredValueToVO(parliamentVO,constituencyVO);//setting parliamentwise value
	       				   locationDtlsList.remove(constituencyVO);//once data is push we are removing object from list to reduce loop in the case of match vo
	       			   }
    			  }
    		}
    	 }
    	 //we are replacing with parliament data
    	 locationDtlsList.clear();
    	 locationDtlsList.addAll(paraliamentMap.values());
    		
    	} catch (Exception e){
    		LOG.error(" Exception raised at getParliamentWiseDataForStateLevelBlock () in FundManagementDashboardService class ",e);
    	}
    	return locationDtlsList;
    }
    
    public List<FundSchemeVO> getParliamentWiseDataForDistrictLevelBlockYearWise(InputVO inputVO,List<FundSchemeVO> locationDtlsList) {
    	  Map<String,FundSchemeVO> paraliamentMap = new HashMap<>(0);
    	  List<FundSchemeVO> parliamentList = new ArrayList<>(0);
    	try {
    		
    	Map<Long,String> paCnsttncyIdAndNrgCnsttuncyCodeMap = new HashMap<>(0); 
		List<Object[]>	constituencyobjLst = constituencyDAO.getMgnregsConstituencyMappingCode(null);
		 if (constituencyobjLst != null && constituencyobjLst.size() > 0){
			  for (Object[] param : constituencyobjLst) {
				  paCnsttncyIdAndNrgCnsttuncyCodeMap.put(commonMethodsUtilService.getLongValueForObject(param[0]), commonMethodsUtilService.getStringValueForObject(param[1]));
			}
		 }
		  Long locationId = 0l;
		  if (inputVO.getLevelValues().size() > 0){
			  locationId = inputVO.getLevelValues().get(0);
		  }
		  String filterType=null;
		  if (inputVO.getBlockLevelId().longValue() == IConstants.DISTRICT_LEVEL_SCOPE_ID) {
			  filterType = "district";
		  } else {
			  filterType = "parliament";
		  }
    	  List<Object[]>  parliamentConstituencyObjLst = parliamentAssemblyDAO.getParliamentWiseConstituency(locationId,filterType);
    	 if (parliamentConstituencyObjLst != null && parliamentConstituencyObjLst.size() > 0 ) {
    		 for (Object[] param : parliamentConstituencyObjLst) {
    			 String parliamentId = commonMethodsUtilService.getStringValueForObject(param[0]);
    			 Long constituencyId = commonMethodsUtilService.getLongValueForObject(param[2]);
    			 FundSchemeVO parliamentVO = paraliamentMap.get(parliamentId);
    			  if (parliamentVO == null ){//this Empty VO setting with location for parliament as template
    				    parliamentVO = new FundSchemeVO();
    				    parliamentVO.setLocationIdStr(parliamentId);
    				    parliamentVO.setLocationIdSet(new HashSet<Long>());
    				   
    				    AddressVO addressVO = new AddressVO();
	    				addressVO.setDistrictName(commonMethodsUtilService.getStringValueForObject(param[5]));
	    				addressVO.setAssemblyName(commonMethodsUtilService.getStringValueForObject(param[3]));
	    				addressVO.setParliamentId(commonMethodsUtilService.getLongValueForObject(param[0]));
	    				addressVO.setParliamentName(commonMethodsUtilService.getStringValueForObject(param[1]));
	    				parliamentVO.setAddressVO(addressVO);
	    				
	    			   paraliamentMap.put(parliamentId, parliamentVO);
    			  }
    			  parliamentVO.getLocationIdSet().add(constituencyId);
    		}
    	 }
    	  Map<String,Long> yearIdMap = getFinancilalYear(inputVO).getSubMap(); 
    	
    	 
    	   if (yearIdMap != null && yearIdMap.size() > 0) {
    		  for(Entry<String,Long> entry:yearIdMap.entrySet()) {
    			  if (paraliamentMap != null && paraliamentMap.size() > 0){
    				  
    				   for(Entry<String, FundSchemeVO> parliamentEntry:paraliamentMap.entrySet()) {
    					   
    					   FundSchemeVO  parliamentYearVO = new FundSchemeVO();
    					     parliamentYearVO.setYear(entry.getKey());
    		    			 parliamentYearVO.setYearId(entry.getValue());
    		    			 parliamentYearVO.setLocationIdStr(parliamentEntry.getValue().getLocationIdStr());
    		    			 parliamentYearVO.setCount(0l);
    		    			 parliamentYearVO.setAmount("0.0");
    		    			 parliamentYearVO.setTotalCount(0l);
    		    			 parliamentYearVO.setAddressVO(new AddressVO());
    		    			 parliamentYearVO.getAddressVO().setDistrictName(parliamentEntry.getValue().getAddressVO().getDistrictName());
    		    			 parliamentYearVO.getAddressVO().setAssemblyName(parliamentEntry.getValue().getAddressVO().getAssemblyName());
    		    			 parliamentYearVO.getAddressVO().setParliamentId(parliamentEntry.getValue().getAddressVO().getParliamentId());
    		    			 parliamentYearVO.getAddressVO().setParliamentName(parliamentEntry.getValue().getAddressVO().getParliamentName());
    		    			
		    			    FundSchemeVO mgneregsTemplateVO = new FundSchemeVO();
		    				mgneregsTemplateVO.setCount(0l);
		    				mgneregsTemplateVO.setYear(entry.getKey());
		    				mgneregsTemplateVO.setYearId(entry.getValue());
		    				mgneregsTemplateVO.setAmount(parliamentEntry.getValue().getAmount());
		    				mgneregsTemplateVO.setTotalCount(parliamentEntry.getValue().getTotalCount());
		    				mgneregsTemplateVO.setAddressVO(parliamentYearVO.getAddressVO());
		    				
		    				FundSchemeVO deptVO = new FundSchemeVO();
		    				deptVO.setId(0l);
		    				deptVO.setName("MGNREGS");
		    				deptVO.setAmount(parliamentEntry.getValue().getAmount());
		    				deptVO.setTotalCount(parliamentEntry.getValue().getTotalCount());
		    				deptVO.setCount(0l);
		    				mgneregsTemplateVO.getSubList().add(deptVO);
		    				parliamentYearVO.getSubList().add(mgneregsTemplateVO);
		    				//setting year wise mgnregs values
		    				if (parliamentEntry.getValue().getLocationIdSet() != null && parliamentEntry.getValue().getLocationIdSet().size() > 0) {
		    					for (Long constituencyId : parliamentEntry.getValue().getLocationIdSet()) {
		    						FundSchemeVO constituencyVO = getLocationIdAndYearMatchVO(locationDtlsList,paCnsttncyIdAndNrgCnsttuncyCodeMap.get(constituencyId).toString(),parliamentYearVO.getYear());
		    						 if (constituencyVO != null ) {
		    							 setRequiredValueToVO(parliamentYearVO,constituencyVO);//setting parliamentwise value
		    							 locationDtlsList.remove(constituencyVO);//once data is push we are removing object from list to reduce loop in the case of match vo
		    						 }
								}
		    				}
		    				parliamentList.add(parliamentYearVO);	 
    				   }
    			  }
    		  }
    	   }
    	 locationDtlsList.clear();
    	} catch (Exception e){
    		LOG.error(" Exception raised at getParliamentWiseDataForStateLevelBlock() in FundManagementDashboardService class",e);
    	}
    	return parliamentList;
    }
    
    private void setRequiredValueToVO(FundSchemeVO parliamentVO,FundSchemeVO constituencyVO) {
    	 try {
    		       DecimalFormat f = new DecimalFormat("##");
    	 		   DecimalFormat f1 = new DecimalFormat("##.###");
    		       
    	 		   parliamentVO.setTotalCount(Long.valueOf(f.format(parliamentVO.getTotalCount()+constituencyVO.getTotalCount())));
				   parliamentVO.setCount(parliamentVO.getCount()+constituencyVO.getCount());
				   parliamentVO.setAmount(f1.format(Double.valueOf(parliamentVO.getAmount())+Double.valueOf(constituencyVO.getAmount())));
				 
				   parliamentVO.getSubList().get(0).setTotalCount(Long.valueOf(f.format(parliamentVO.getSubList().get(0).getTotalCount()+constituencyVO.getTotalCount())));
				   parliamentVO.getSubList().get(0).setAmount(f1.format(Double.valueOf(parliamentVO.getSubList().get(0).getAmount())+Double.valueOf(constituencyVO.getAmount())));
				   parliamentVO.getSubList().get(0).setCount(parliamentVO.getSubList().get(0).getCount()+constituencyVO.getCount());
				   
				   parliamentVO.getSubList().get(0).getSubList().get(0).setTotalCount(Long.valueOf(f.format(parliamentVO.getSubList().get(0).getSubList().get(0).getTotalCount()+constituencyVO.getTotalCount())));;
				   parliamentVO.getSubList().get(0).getSubList().get(0).setAmount(f1.format(Double.valueOf(parliamentVO.getSubList().get(0).getSubList().get(0).getAmount())+Double.valueOf(constituencyVO.getAmount())));
				   parliamentVO.getSubList().get(0).getSubList().get(0).setCount(parliamentVO.getSubList().get(0).getSubList().get(0).getCount()+constituencyVO.getCount());
				 
    		 
    	 } catch (Exception e) {
    		 LOG.error(" Exception raised at setRequiredValueToVO () in FundManagementDashboardService class ",e);
    	 }
    }
    
    private FundSchemeVO getMatchVO(List<FundSchemeVO> list,String locationIdStr) {
    	 try {
    		 if (list == null || list.size() == 0 )
    			 return null;
    		 for(FundSchemeVO locationVO:list) {
    			 if (locationVO.getLocationIdStr().equalsIgnoreCase(locationIdStr)) {
    				 return locationVO;
    			 }
    		 }
    	 } catch (Exception e) {
    		 LOG.error(" Exception raised at getMatchVO () in FundManagementDashboardService class  ",e); 
    	 }
    	 return null;
    }
    
	public Map<String, String> getConstituencyParliamentNameMapping(Long parliamentId) {// getting nrega constituencyCode and parliamentName
		Map<String, String> cnstuncyPrlmntIdNameMap = new HashMap<>();
		try {
			List<Object[]> parliamentConstituencyObjNameObjLst = parliamentAssemblyDAO.getParliamentWiseConstituency(parliamentId,"parliament");
			if (parliamentConstituencyObjNameObjLst != null && parliamentConstituencyObjNameObjLst.size() > 0) {
				for (Object[] param : parliamentConstituencyObjNameObjLst) {
					cnstuncyPrlmntIdNameMap.put(commonMethodsUtilService.getStringValueForObject(param[6]),commonMethodsUtilService.getStringValueForObject(param[1]));
				}
			}
		} catch (Exception e) {
			LOG.error(" Exception raised at getConstituencyParliamentNameMapping () in FundManagementDashboardService class",e);
		}
		return cnstuncyPrlmntIdNameMap;
	}

	public Map<String, String> getLocationDetailsByLocationType(String locationType) {
		Map<String, String> locationMap = new HashMap<>();
		try {
			List<Object[]> objList = null;
			if (locationType.equalsIgnoreCase("panchayat")) {
				objList = prTehsilDAO.getPrPanchayatTehsilName();
			} else if (locationType.equalsIgnoreCase("mandal")) {
				objList = prTehsilDAO.getPrTehsilConsituencyName();
			}
			if (objList != null && objList.size() > 0) {
				for (Object[] param : objList) {
					locationMap.put(commonMethodsUtilService.getStringValueForObject(param[0]),commonMethodsUtilService.getStringValueForObject(param[1]));
				}
			}
		} catch (Exception e) {
			LOG.error(" Exception raised at getLocationDetailsByLocationType() in FundManagementDashboardService class ",e);
		}
		return locationMap;
	}
    public FundSchemeVO getStaticTemplate() {
    	FundSchemeVO deptVO = new FundSchemeVO();
    	try {
    			deptVO.setId(0l);
				deptVO.setName("MGNREGS");
				deptVO.setAmount("0.0");
				deptVO.setTotalCount(0l);
				deptVO.setCount(0l);
		 } catch (Exception e) {
    		 LOG.error(" Exception raised at getStaticTemplate () in FundManagementDashboardService class ",e);
    	 }
    	return deptVO;
    }
    public void setYearWiseMgneresData(FundSchemeVO locationVO,FundSchemeVO yearVO,FundSchemeVO mgneresVO) {
		try {
			         DecimalFormat f = new DecimalFormat("##.000");
					 yearVO.getSubList().add(0,mgneresVO.getSubList().get(0));
					 locationVO.setAmount(f.format(Double.valueOf(yearVO.getAmount())+Double.valueOf(mgneresVO.getSubList().get(0).getAmount())));
		} catch (Exception e) {
			LOG.error(" Exception occured at setYearWiseMgneresData () in FundManagementDashboardService class ",e);
		}
	}
    public FundSchemeVO getMgneregslocationMappingIds(InputVO inputVO) {
    	FundSchemeVO resultVO = new FundSchemeVO();
    	Map<Long,String> prrwsLocationIdAndMgnregsLocationCodeMap = new HashMap<>(); 
    	Map<String,Long> MgnregsLocationCodeAndprrwsLocationIdMap = new HashMap<>();  
    	try {
    		List<Object[]> objList  = null;
    		if (inputVO.getBlockLevelId() != null ){
    			 if(inputVO.getBlockLevelId().longValue() == 0l || inputVO.getBlockLevelId().longValue() == IConstants.STATE_LEVEL_SCOPE_ID){ // FOR STATE
    				 prrwsLocationIdAndMgnregsLocationCodeMap.put(1l, "01");
    				 MgnregsLocationCodeAndprrwsLocationIdMap.put("01", 1l);
        		 }else if(inputVO.getBlockLevelId().longValue() == IConstants.DISTRICT_LEVEL_SCOPE_ID){
        			   objList = districtDAO.getMgnregsDistrictMappingCode(null);
    			 }else if(inputVO.getBlockLevelId().longValue() == IConstants.CONSTITUENCY_LEVEL_SCOPE_ID){
    				 objList = constituencyDAO.getMgnregsConstituencyMappingCode(null);
    			 }else if(inputVO.getBlockLevelId().longValue() == IConstants.TEMP_PARLIAMENT_CONSTITUENCY_LEVEL_SCOPE_ID){
    				 objList = parliamentAssemblyDAO.getParliamentIds(null);
    			 }else if(inputVO.getBlockLevelId().longValue() == IConstants.MANDAL_LEVEL_SCOPE_ID){						
    				 objList = tehsilDAO.getMgnregsTehsilMappingCode(null);
    			}else if(inputVO.getBlockLevelId().longValue() == IConstants.VILLAGE_LEVEL_SCOPE_ID){
    				objList = panchayatDAO.getMgnregsPanchayatMappingCode(null);
    			}
        	}
    		if (objList != null && objList.size() > 0) {
    			 for (Object[] param : objList) {
    				 MgnregsLocationCodeAndprrwsLocationIdMap.put(commonMethodsUtilService.getStringValueForObject(param[1]), commonMethodsUtilService.getLongValueForObject(param[0]));
    				 prrwsLocationIdAndMgnregsLocationCodeMap.put(commonMethodsUtilService.getLongValueForObject(param[0]), commonMethodsUtilService.getStringValueForObject(param[1]));
				}
    		 }
    		resultVO.setSubMap1(prrwsLocationIdAndMgnregsLocationCodeMap);
    		resultVO.setSubMap2(MgnregsLocationCodeAndprrwsLocationIdMap);
    	 } catch (Exception e) {
    		 LOG.error(" Exception raised at getMgneregslocationMappingIds () in FundManagementDashboardService class ",e);
    	 }
    	return resultVO;
    }
    public Map<Long,String> getLocationMappingBasedOnFilterSelection(InputVO inputVO) {
    	Map<Long,String> mgneregsLocationMap = new HashMap<>(); 
    	try {
    		List<Object[]> objList  = null;
    		Long locationId = 0l;
    		if (inputVO.getLevelValues() != null && inputVO.getLevelValues().size() > 0) {
    			locationId = inputVO.getLevelValues().get(0);
    		}
    		if (inputVO.getSearchLevelId() != null ){
    			 if(inputVO.getSearchLevelId() == 0l || inputVO.getSearchLevelId() == IConstants.STATE_LEVEL_SCOPE_ID){ // FOR STATE
        			 mgneregsLocationMap.put(1l, "01");
        		 }else if(inputVO.getSearchLevelId().longValue() == IConstants.DISTRICT_LEVEL_SCOPE_ID){
        			   objList = districtDAO.getMgnregsDistrictMappingCode(locationId);
    			 }else if(inputVO.getSearchLevelId().longValue() == IConstants.CONSTITUENCY_LEVEL_SCOPE_ID){
    				 objList = constituencyDAO.getMgnregsConstituencyMappingCode(locationId);
    			 }else if(inputVO.getSearchLevelId().longValue() == IConstants.TEMP_PARLIAMENT_CONSTITUENCY_LEVEL_SCOPE_ID){
    				 objList = constituencyDAO.getMgnregsConstituencyMappingCode(locationId);
    			 }else if(inputVO.getSearchLevelId().longValue() == IConstants.MANDAL_LEVEL_SCOPE_ID){						
    				 objList = tehsilDAO.getMgnregsTehsilMappingCode(locationId);
    			}else if(inputVO.getSearchLevelId().longValue() == IConstants.VILLAGE_LEVEL_SCOPE_ID){
    				objList = panchayatDAO.getMgnregsPanchayatMappingCode(locationId);
    			}
        	}
    		if (objList != null && objList.size() > 0) {
    			 for (Object[] param : objList) {
    				 mgneregsLocationMap.put(commonMethodsUtilService.getLongValueForObject(param[0]), commonMethodsUtilService.getStringValueForObject(param[1]));
				}
    		 }
    	 } catch (Exception e) {
    		 LOG.error(" Exception raised at getLocationMappingBasedOnFilterSelection () in in FundManagementDashboardService class ",e);
    	 }
    	return mgneregsLocationMap;
    }
    
    public LocationFundDetailsVO getMgnregsTopLowAverageLocation(InputVO inputVO) {
    	LocationFundDetailsVO  resultVO = new LocationFundDetailsVO();
    	 try {
    		    prpareRquiredParameter(inputVO,"overview");
    		    inputVO.setViewType("cumulative");
    		    String str = "";
    		    ClientResponse districtResponse = null;
    		    ClientResponse constituencyResponse = null;
    		    ClientResponse mandalResponceResponse = null;
    		    LocationFundDetailsVO topPerformancMandalVO = null;
			    LocationFundDetailsVO poorPerformancMandalVO = null;
			    List<FundVO> locationList = new ArrayList<FundVO>(0);
			    String URL = "http://dbtrd.ap.gov.in/NregaDashBoardService/rest/FMSExpenditureABSNewService/FMSExpenditureOverviewNew";
			    
				if(inputVO.getSearchLevelId() == 0l || inputVO.getSearchLevelId()==IConstants.DISTRICT_LEVEL_SCOPE_ID) //0-state or district 
				{
				   inputVO.setSublocationType("district");
				   str = convertingInputVOToString(inputVO);
				   districtResponse = webServiceUtilService.callWebService(URL, str);
				   List<LocationFundDetailsVO> list = getPerformanceWiseMgneregesLocationDtls(districtResponse,inputVO.getSublocationType());
				    if (list.size() > 0 ){
						 topPerformancMandalVO = list.get(0);
				    	 poorPerformancMandalVO = list.get(list.size()-1);
				    	 //overall
				    	 resultVO.getSubList().addAll(topPerformancMandalVO.getSubList());
				         setResultToFinalList(locationList,3l,"District",topPerformancMandalVO.getId(),topPerformancMandalVO.getDistrictName(),topPerformancMandalVO.getTotalExpenditure(),poorPerformancMandalVO.getId(),poorPerformancMandalVO.getDistrictName(),poorPerformancMandalVO.getTotalExpenditure(),topPerformancMandalVO.getAvrgeAmt());
					}
				}
				
				if(inputVO.getSearchLevelId() == 0l ||  inputVO.getSearchLevelId()==IConstants.DISTRICT_LEVEL_SCOPE_ID || inputVO.getSearchLevelId()==IConstants.CONSTITUENCY_LEVEL_SCOPE_ID)////state or district or Constituency 
				{
					 inputVO.setSublocationType("constituency");
					 str = convertingInputVOToString(inputVO);
					 constituencyResponse = webServiceUtilService.callWebService(URL, str);
					 List<LocationFundDetailsVO> list = getPerformanceWiseMgneregesLocationDtls(constituencyResponse,inputVO.getSublocationType());
					 if (list.size() > 0 ){
						 topPerformancMandalVO = list.get(0);
				    	 poorPerformancMandalVO = list.get(list.size()-1);
				    	 if(inputVO.getSearchLevelId() == IConstants.CONSTITUENCY_LEVEL_SCOPE_ID) { 
				    		 //overall
				    		 resultVO.getSubList().addAll(topPerformancMandalVO.getSubList());
					    	 setResultToFinalList(locationList,3l,"District",0l,topPerformancMandalVO.getDistrictName(),topPerformancMandalVO.getTotalExpenditure(),poorPerformancMandalVO.getId(),poorPerformancMandalVO.getDistrictName(),poorPerformancMandalVO.getTotalExpenditure(),topPerformancMandalVO.getAvrgeAmt());
					    	 setResultToFinalList(locationList,4l,"Constituency",topPerformancMandalVO.getId(),topPerformancMandalVO.getConstituencyName(),topPerformancMandalVO.getTotalExpenditure(),poorPerformancMandalVO.getId(),poorPerformancMandalVO.getConstituencyName(),poorPerformancMandalVO.getTotalExpenditure(),topPerformancMandalVO.getAvrgeAmt());
					     } else {
					    	 setResultToFinalList(locationList,4l,"Constituency",topPerformancMandalVO.getId(),topPerformancMandalVO.getConstituencyName(),topPerformancMandalVO.getTotalExpenditure(),poorPerformancMandalVO.getId(),poorPerformancMandalVO.getConstituencyName(),poorPerformancMandalVO.getTotalExpenditure(),topPerformancMandalVO.getAvrgeAmt());
					     }
					 }
				}
				
				if(inputVO.getSearchLevelId() == 0l || inputVO.getSearchLevelId()==IConstants.DISTRICT_LEVEL_SCOPE_ID  || inputVO.getSearchLevelId()==IConstants.CONSTITUENCY_LEVEL_SCOPE_ID || inputVO.getSearchLevelId()==IConstants.MANDAL_LEVEL_SCOPE_ID)//state or district or Constituency or Mandal 
				{
					 inputVO.setSublocationType("mandal");
					 str = convertingInputVOToString(inputVO);
					 mandalResponceResponse = webServiceUtilService.callWebService(URL, str);
				     List<LocationFundDetailsVO> list = getPerformanceWiseMgneregesLocationDtls(mandalResponceResponse,inputVO.getSublocationType());
				     if(list.size() > 0){
				    	 topPerformancMandalVO = list.get(0);
				    	 poorPerformancMandalVO = list.get(list.size()-1);
				    	 if(inputVO.getSearchLevelId() == IConstants.MANDAL_LEVEL_SCOPE_ID) { 
				    		 //overall
				    		 resultVO.getSubList().addAll(topPerformancMandalVO.getSubList());
					    	 setResultToFinalList(locationList,3l,"District",0l,topPerformancMandalVO.getDistrictName(),topPerformancMandalVO.getTotalExpenditure(),poorPerformancMandalVO.getId(),poorPerformancMandalVO.getDistrictName(),poorPerformancMandalVO.getTotalExpenditure(),topPerformancMandalVO.getAvrgeAmt());
					    	 setResultToFinalList(locationList,4l,"Constituency",0l,topPerformancMandalVO.getConstituencyName(),topPerformancMandalVO.getTotalExpenditure(),poorPerformancMandalVO.getId(),poorPerformancMandalVO.getConstituencyName(),poorPerformancMandalVO.getTotalExpenditure(),topPerformancMandalVO.getAvrgeAmt());
					    	 setResultToFinalList(locationList,5l,"Mandal",topPerformancMandalVO.getId(),topPerformancMandalVO.getMandalName(),topPerformancMandalVO.getTotalExpenditure(),poorPerformancMandalVO.getId(),poorPerformancMandalVO.getMandalName(),poorPerformancMandalVO.getTotalExpenditure(),topPerformancMandalVO.getAvrgeAmt());
					     } else {
					      	 setResultToFinalList(locationList,5l,"Mandal",topPerformancMandalVO.getId(),topPerformancMandalVO.getMandalName(),topPerformancMandalVO.getTotalExpenditure(),poorPerformancMandalVO.getId(),poorPerformancMandalVO.getMandalName(),poorPerformancMandalVO.getTotalExpenditure(),topPerformancMandalVO.getAvrgeAmt());
					     }
				     }
				}
				if (locationList .size() > 0 ) {
					Collections.sort(locationList, new Comparator<FundVO>() {
						@Override
						public int compare(FundVO o1, FundVO o2) {
							return o1.getLevelId().compareTo(o2.getLevelId());
						}
					});
					resultVO.getFundList().addAll(locationList);
				}
				if( resultVO.getSubList().size() > 0) {
					resultVO.setTotalAmt(resultVO.getSubList().get(0).getTotl());//total
					resultVO.setTtlAmt(resultVO.getSubList().get(0).getTotl());
				}
				
		 } catch (Exception e) {
    	    LOG.error(" Exception raised at getMgnregsTopLowAverageLocation () in FundManagementDashboardService class; ",e);
  		 
    	 }
    	 return resultVO;
    }
    public void setResultToFinalList(List<FundVO> resultList,Long levelId,String levelName,Long highLocationId,String highLocationName,Double highLocationAmount,Long lowLocationId,String lowLocationName,Double lowLocationAmount,String aveRageAmt) {
    	 FundVO locationVO = new FundVO();
    	try {
    		 locationVO.setLevelId(levelId);
	    	 locationVO.setName(levelName);
	    	 locationVO.setHighLocId(highLocationId);
	    	 locationVO.setHighLocName(highLocationName);
	    	 locationVO.setHighCroreAmt(cnvrtLakhIntoCrores(highLocationAmount.toString()));
	    	 locationVO.setLowLocId(lowLocationId);
	    	 locationVO.setLowLocName(lowLocationName);
	    	 locationVO.setLowCroreAmt(cnvrtLakhIntoCrores(lowLocationAmount.toString()));
	    	 locationVO.setAvgCroreAmt(cnvrtLakhIntoCrores(aveRageAmt));	 
	    	 resultList.add(locationVO);
    	} catch (Exception e ) {
    		LOG.error(" Exception raised at setResultToFinalList () in FundManagementDashboardService class; ",e);
    	}
    } 
    public List<LocationFundDetailsVO> getPerformanceWiseMgneregesLocationDtls( ClientResponse responce,String locationType) {
    	List<LocationFundDetailsVO> resultList = new ArrayList<>();
    	try {
    		   Double totalAmountExpenditur=0.0d;
    		   Double totalWageExpenditure=0.0d;
    		   Double totalMaterialExpenditure=0.0d;
    		   DecimalFormat f = new DecimalFormat("##.000");
    		   if (responce.getStatus() != 200) {
		 	    	  throw new RuntimeException("Failed : HTTP error code : "+ responce.getStatus());
		 	     } else {
		 	    	String output = responce.getEntity(String.class);
		 	    	if (output != null && !output.isEmpty()){
		 	    		JSONArray finalArray = new JSONArray(output);
		 	    		if (finalArray!=null && finalArray.length()>0){
		 	    			for (int i=0;i<finalArray.length();i++){
		 	    				JSONObject jObj = (JSONObject) finalArray.get(i);
		 	    				LocationFundDetailsVO vo = new LocationFundDetailsVO();
		 	    				vo.setId(jObj.getLong("UNIQUEID"));
		 	    				
		 	    				if (locationType.equalsIgnoreCase("district") || locationType.equalsIgnoreCase("constituency") || locationType.equalsIgnoreCase("mandal")) {
		 	    					vo.setDistrictName(jObj.getString("DISTRICT"));	
		 	    				}
		 	    				if (locationType.equalsIgnoreCase("constituency") || locationType.equalsIgnoreCase("mandal")) {
		 	    					vo.setConstituencyName(jObj.getString("CONSTITUENCY"));
		 	    				}
		 	    				if (locationType.equalsIgnoreCase("mandal")) {
		 	    					vo.setMandalName(jObj.getString("MANDAL"));	
		 	    				}
		 	    				
		 	    				vo.setTotalExpenditure(Double.valueOf(jObj.getString("MATERIAL")));
		 	    				vo.setTotalWorks(Double.valueOf(jObj.getString("WORKS")));
		 	    				totalAmountExpenditur = totalAmountExpenditur + vo.getTotalExpenditure();
		 	    				totalWageExpenditure = totalWageExpenditure + Double.valueOf(jObj.getString("WAGE"));
		 	    				totalMaterialExpenditure = totalMaterialExpenditure + Double.valueOf(jObj.getString("MATERIAL"));
		 	    				resultList.add(vo);
		 	    		}
		 	    	}
		 	    } 
		 	 }
    		if(resultList.size() > 0 ){
    			Collections.sort(resultList,totalExpenditureWiseDescendingOrder);
    			//calculating Average
    			resultList.get(0).setAvrgeAmt(f.format(totalAmountExpenditur/resultList.size()));
    			resultList.get(0).getSubList().add(new IdNameVO(1l, "TOTAL EXP", cnvrtLakhIntoCrores(totalAmountExpenditur.toString())));
    			resultList.get(0).getSubList().add(new IdNameVO(2l, "WAGE EXP", cnvrtLakhIntoCrores(totalWageExpenditure.toString())));
    			resultList.get(0).getSubList().add(new IdNameVO(3l, "MATERIAL EXP", cnvrtLakhIntoCrores(totalMaterialExpenditure.toString())));//MATERIALEXPENDITURE
    		}
    	} catch (Exception e) {
    		   LOG.error(" Exception raised at getPerformanceWiseMgneregesLocationDtls () in FundManagementDashboardService class; ",e);
    	}
    	return resultList;
    }
    public static Comparator<LocationFundDetailsVO> totalExpenditureWiseDescendingOrder = new Comparator<LocationFundDetailsVO>() {
	   		public int compare(LocationFundDetailsVO o1, LocationFundDetailsVO o2) {
	   		 try{
	   			if(o2.getTotalExpenditure() != null && o1.getTotalExpenditure() != null){
	   				return o2.getTotalExpenditure().compareTo(o1.getTotalExpenditure());
	   			}
	   		  }catch(Exception e){
	   			  LOG.error(" Exception occured at totalExpenditureWiseDescendingOrder ",e);
	   			}
	   			return 0;
	   		}
	 };

    public  LocationFundDetailsVO getMgnregsOverviewData(InputVO inputVO) {
    	 LocationFundDetailsVO mgnresDtlsVO = new LocationFundDetailsVO();
    	 try {
    		    inputVO.setBlockLevelId(inputVO.getSearchLevelId());
    		    inputVO.setViewType("cumulative");
    		    prpareRquiredParameter(inputVO,"overview");
    		    String str = convertingInputVOToString(inputVO);
				ClientResponse response = webServiceUtilService.callWebService("http://dbtrd.ap.gov.in/NregaDashBoardService/rest/FMSExpenditureABSNewService/FMSExpenditureOverviewNew", str);
		        
		        if (response.getStatus() != 200) {
		 	    	  throw new RuntimeException("Failed : HTTP error code : "+ response.getStatus());
		 	     } else {
		 	    	String output = response.getEntity(String.class);
		 	    	mgnresDtlsVO.setId(0l);
		 	    	mgnresDtlsVO.setName("MGNREGS");
		 	    	mgnresDtlsVO.setCount(0l);
		 	    	mgnresDtlsVO.setSubList(getMgnresTemplate());
		 	    	if (output != null && !output.isEmpty()){
		 	    		JSONArray finalArray = new JSONArray(output);
		 	    		if (finalArray!=null && finalArray.length()>0){
		 	    			for (int i=0;i<finalArray.length();i++){
		 	    				JSONObject jObj = (JSONObject) finalArray.get(i);
		 	    				mgnresDtlsVO.setTtlAmt(cnvrtLakhIntoCrores(String.valueOf(Double.valueOf(jObj.getString("TOT")))));
		 	    				mgnresDtlsVO.setTotalWorks(Double.valueOf(jObj.getString("WORKS")));
		 	    				Long count = Math.round(Double.valueOf(jObj.getString("TOT")));
		 	    				mgnresDtlsVO.setCount(mgnresDtlsVO.getCount()+count);
		 	    				for (IdNameVO typeVO : mgnresDtlsVO.getSubList()) {
									if (typeVO.getId().longValue() == 1l) {
										typeVO.setTotl(cnvrtLakhIntoCrores(String.valueOf(Double.valueOf(jObj.getString("WAGE")))));
									} else {
										typeVO.setTotl(cnvrtLakhIntoCrores(String.valueOf(Double.valueOf(jObj.getString("MATERIAL")))));
									}
								}
		 	    			}
		 	    		}
		 	    	}
		 	    } 
    	 } catch (Exception e ){
    		 LOG.error(" Exception occured at getMgnregsOverviewData() in FundManagementDashboardService class ", e);
    	 }
    	 return mgnresDtlsVO;
    }
    
	public List<IdNameVO> getMgnresTemplate() {
		List<IdNameVO> resultList = new ArrayList<IdNameVO>(0);
		try {
			resultList.add(new IdNameVO(1l, "WAGE EXP","0.0"));
			resultList.add(new IdNameVO(2l, "MATERIAL EXP","0.0"));
		} catch (Exception e) {
		  LOG.error(" Exception occured at getMgnresTemplate() in FundManagementDashboardService class ",e);
		}
		return resultList;
	}
    public String cnvrtLakhIntoCrores(String value){
		String returnVal = "0";
		try {
			if(value != null) {
				returnVal = new BigDecimal(Double.valueOf(value)/100.00d).setScale(3, BigDecimal.ROUND_HALF_UP).toString();
			}
		} catch (Exception e) {
			LOG.error("Exception raised at cnvrtLakhIntoCrores - FundManagementDashboardService service", e);
		}
		return returnVal.trim();
	}
    public void prpareRquiredParameter(InputVO inputVO, String type) {
		try {
			if (type.equalsIgnoreCase("overview")) {
				if (inputVO.getSearchLvlVals() != null && inputVO.getSearchLvlVals().size() > 0) {
					inputVO.getLevelValues().clear();
					inputVO.getLevelValues().addAll(inputVO.getSearchLvlVals());
				}
			}
			
			 Map<Long, String> locationIdMap = getLocationMappingBasedOnFilterSelection(inputVO);
			 
			/* Preparing LocationLevel And LocationId based on based on filter  selection to call web service.*/
			 
           if(inputVO.getSearchLevelId() != null ){
        		if (inputVO.getSearchLevelId() == 0l || inputVO.getSearchLevelId() == IConstants.STATE_LEVEL_SCOPE_ID) {
    				inputVO.setLocationType("state");
    				inputVO.setLocationIdStr("-1");
    			} else if (inputVO.getSearchLevelId().longValue() == IConstants.DISTRICT_LEVEL_SCOPE_ID) {
    				inputVO.setLocationType("district");
    			} else if (inputVO.getSearchLevelId().longValue() == IConstants.CONSTITUENCY_LEVEL_SCOPE_ID) {
    				inputVO.setLocationType("constituency");
    			} else if (inputVO.getSearchLevelId().longValue() == IConstants.TEMP_PARLIAMENT_CONSTITUENCY_LEVEL_SCOPE_ID) {
    				inputVO.setLocationType("state");
    				inputVO.setLocationIdStr("-1");
    			} else if (inputVO.getSearchLevelId().longValue() == IConstants.MANDAL_LEVEL_SCOPE_ID) {
    				inputVO.setLocationType("mandal");
    			} else if (inputVO.getSearchLevelId().longValue() == IConstants.VILLAGE_LEVEL_SCOPE_ID) {
    				inputVO.setLocationType("panchayat");
    			}
           }
		
			if (inputVO.getSearchLevelId() != null && inputVO.getSearchLevelId().longValue() != IConstants.TEMP_PARLIAMENT_CONSTITUENCY_LEVEL_SCOPE_ID) {
				if (inputVO.getLevelValues() != null && inputVO.getLevelValues().size() > 0) {
					if (locationIdMap.get(inputVO.getLevelValues().get(0)) != null) {
						inputVO.setLocationIdStr(locationIdMap.get(inputVO.getLevelValues().get(0)));
					}
				} else {
					inputVO.setLocationType("state");
					inputVO.setLocationIdStr("-1");
				}
			}

			/* Preparing SubLevel Parameter */
			if (inputVO.getBlockLevelId() != null) {
				if (inputVO.getBlockLevelId().longValue() == 0 || inputVO.getBlockLevelId() == IConstants.STATE_LEVEL_SCOPE_ID) {
					inputVO.setSublocationType("state");
				} else if (inputVO.getBlockLevelId().longValue() == IConstants.DISTRICT_LEVEL_SCOPE_ID) {
					inputVO.setSublocationType("district");
				} else if (inputVO.getBlockLevelId().longValue() == IConstants.CONSTITUENCY_LEVEL_SCOPE_ID) {
					inputVO.setSublocationType("constituency");
				} else if (inputVO.getBlockLevelId().longValue() == IConstants.TEMP_PARLIAMENT_CONSTITUENCY_LEVEL_SCOPE_ID || inputVO.getBlockLevelId().longValue() == IConstants.CONSTITUENCY_LEVEL_SCOPE_ID) {
					inputVO.setSublocationType("constituency");
				} else if (inputVO.getBlockLevelId().longValue() == IConstants.MANDAL_LEVEL_SCOPE_ID) {
					inputVO.setSublocationType("mandal");
				} else if (inputVO.getBlockLevelId().longValue() == IConstants.VILLAGE_LEVEL_SCOPE_ID) {
					inputVO.setSublocationType("panchayat");
				}
			}

		} catch (Exception e) {
			LOG.error(" Exception raised at prpareRquiredParameter () in FundManagementDashboardService class; ",e);
		}
	 }
     private InputVO getFinancilalYear(InputVO inputVO) {
    	 String financialYear="";
    	 InputVO finanCialYearVO = new InputVO();
    	  try {
    		  List<Long> financialYearIdsList = commonMethodsUtilService.makeEmptyListByZeroValue(inputVO.getFinancialYrIdList());
    		  List<Object[]> finalYearObjLst = financialYearDAO.getAllFiniancialYearsByIds(financialYearIdsList);
  	    	  Map<Long,String> financialYearMap = new HashMap<Long, String>();
  	    	  Map<String,Long> yearIdMap = new HashMap<String, Long>();
  	    	 if (finalYearObjLst != null && finalYearObjLst.size() > 0 ) {
  	    		 for (Object[] param : finalYearObjLst) {
  	    			 financialYearMap.put(commonMethodsUtilService.getLongValueForObject(param[0]), commonMethodsUtilService.getStringValueForObject(param[1]));
   	    			 yearIdMap.put(commonMethodsUtilService.getStringValueForObject(param[1]), commonMethodsUtilService.getLongValueForObject(param[0]));
  				}
  	    	 }
  	    	
  		    int count = 0;	
  		    TreeSet<String> financialYearSet = new TreeSet<String>();
  		    if (financialYearMap.size() > 0 ) {
  		    	for (Entry<Long, String> entry : financialYearMap.entrySet()) {
  		    		financialYearSet.add(entry.getValue().split("-")[0]);
  		    		financialYearSet.add(entry.getValue().split("-")[1]);
  					count += 1;
  					String tempStr ="\'"+entry.getValue()+"\'";
  					tempStr += ( count != financialYearMap.size() ? ",":"");
  					financialYear += tempStr;
  				}
  		    }
  		    if (financialYearSet.size() > 0 ) {
  		    	finanCialYearVO.setRangeType(financialYearSet.first()+"-"+financialYearSet.last());//contain cumulative year
  		    }
  		       finanCialYearVO.setYear(financialYear);
  		       finanCialYearVO.setSubMap(yearIdMap);
    	  } catch (Exception e) {
    		  LOG.error("Exception raised at convertingInputVOToString - getFinancilalYearMinAndMaxYear service", e);
    	  }
    	  return finanCialYearVO;
     }
    
    public String convertingInputVOToString(InputVO inputVO){
    	String str = "";
    	 try {
			
			InputVO finanCialYearVO = getFinancilalYear(inputVO);
			inputVO.setRangeType(finanCialYearVO.getRangeType());//min max year
			
			str = "{";
			if(finanCialYearVO.getYear().trim().length() > 0)
				str += "\"year\" : \""+finanCialYearVO.getYear()+"\",";
			if(inputVO.getLocationType() != null)
				str += "\"locationType\" : \""+inputVO.getLocationType()+"\",";
			if(inputVO.getLocationIdStr() != null)
				str += "\"locationId\" : \""+inputVO.getLocationIdStr()+"\",";
			else if(inputVO.getLocationId() != null)
				str += "\"locationId\" : \""+inputVO.getLocationId()+"\",";
			if(inputVO.getSublocationType() != null)
				str += "\"SublocationType\" : \""+inputVO.getSublocationType()+"\",";
			if(inputVO.getViewType() != null)
				str += "\"ReportType\" : \""+inputVO.getViewType()+"\",";
			if(inputVO.getReportType() != null)
				str += "\"ReportType\" : \""+inputVO.getReportType()+"\",";
			if(inputVO.getCategory() != null)
				str += "\"cat\" : \""+inputVO.getCategory()+"\",";
			
			if(str.length() > 1)
				str = str.substring(0,str.length()-1);
			
			str += "}";
			
		} catch (Exception e) {
			LOG.error("Exception raised at convertingInputVOToString - FundManagementDashboardService service", e);
		}
		return str;
	}
   
	public List<NregsFmsWorksVO> getMgnregsFMSWorksDetails(InputVO inputVO) {
		List<NregsFmsWorksVO> returnList = null;
		try {
			List<ClientResponse> clientResponseList = new ArrayList<>(0);
			String str = convertingInputVOToString(inputVO);
			String URL = "http://dbtrd.ap.gov.in/NregaDashBoardService/rest/FMSExpenditureService/FMSExpenditureData";
			if (inputVO.getLocationType() != null && inputVO.getLocationType().equalsIgnoreCase("parliament")) {
				Map<String, String> cnstuncyCodeMap = getConstituencyParliamentNameMapping(Long.valueOf(inputVO.getLocationIdStr()));
				if (cnstuncyCodeMap != null && cnstuncyCodeMap.size() > 0) {
					ExecutorService executor = Executors.newFixedThreadPool(10);
					for (Entry<String, String> entry : cnstuncyCodeMap.entrySet()) {
					        String strNew = "";
							 strNew = str.replace(inputVO.getLocationIdStr(), entry.getKey());
							 strNew = strNew.replace("parliament", "constituency");
						     Runnable worker = new NREGSCumulativeThread(URL,clientResponseList,strNew);
							 executor.execute(worker);
					}
					executor.shutdown();
					while(!executor.isTerminated()) {}
					
					System.out.println("All work has finished "+clientResponseList);
				}
				returnList = getMgnregsParliamentWiseCombinedDetails(clientResponseList);
			} else {
				ClientResponse response = webServiceUtilService.callWebService(URL, str);
				if (response.getStatus() != 200) {
					throw new RuntimeException("Failed : HTTP error code : "+ response.getStatus());
				} else {
					clientResponseList.add(response);
				}
				returnList = getMgnregsWorkDetailsLocationWise(clientResponseList,inputVO);
			}
			
		} catch (Exception e) {
			LOG.error(" Exception occured at getMgnregsFMSWorksDetails() in FundManagementDashboardService class ",e);
		}
		return returnList;
	}
	
	private String convertLakhsIntoCrores(String value){
		String returnVal = null;
		try {
			if(value != null){
				returnVal = new BigDecimal(Double.valueOf(value)/100.00).setScale(2, BigDecimal.ROUND_HALF_UP).toString();
			}
		} catch (Exception e) {
			LOG.error(" Exception occured at convertLakhsIntoCrores() in FundManagementDashboardService class ",e);
		}
		return returnVal;
	}
	
	private List<NregsFmsWorksVO> getMgnregsParliamentWiseCombinedDetails(List<ClientResponse> responseList){
		List<NregsFmsWorksVO> returnList = new ArrayList<NregsFmsWorksVO>();
		try {
			Map<String,NregsFmsWorksVO> workMap = new LinkedHashMap<String,NregsFmsWorksVO>(0);
			
			if (responseList != null && responseList.size() > 0) {
				for (ClientResponse response : responseList) {
					if(response == null || response.getStatus() != 200){
						throw new RuntimeException("Failed : HTTP error code : "+ response.getStatus());
					} else {
						String output = response.getEntity(String.class);
						if (output != null && !output.isEmpty()) {
							JSONArray finalArray = new JSONArray(output);
							if (finalArray != null && finalArray.length() > 0) {
								for (int i = 0; i < finalArray.length(); i++) {
									JSONObject jObj = (JSONObject) finalArray.get(i);
									
									NregsFmsWorksVO vo = workMap.get(jObj.getString("CAT_NAME").trim());
									if(vo == null){
										vo = new NregsFmsWorksVO();
										//vo.setUniqueId(jObj.getString("UNIQUE_ID"));
										//vo.setDistrict(jObj.getString("DNAME"));
										//vo.setConstituency(jObj.getString("ASSEMBLY_NAME"));
										vo.setCategory(jObj.getString("CAT_NAME"));
										vo.setWorks(jObj.getString("WORKS"));
										vo.setWage(jObj.getString("WAGE"));
										vo.setMaterial(jObj.getString("MATERIAL"));
										vo.setTotal(jObj.getString("TOTAL"));
										
										workMap.put(jObj.getString("CAT_NAME").trim(), vo);
									}
									else{
										vo.setWorks(new BigDecimal(Double.valueOf(vo.getWorks())+Double.valueOf(jObj.getString("WORKS"))).setScale(2, BigDecimal.ROUND_HALF_UP).toString());
										vo.setWage(new BigDecimal(Double.valueOf(vo.getWage())+Double.valueOf(jObj.getString("WAGE"))).setScale(2, BigDecimal.ROUND_HALF_UP).toString());
										vo.setMaterial(new BigDecimal(Double.valueOf(vo.getMaterial())+Double.valueOf(jObj.getString("MATERIAL"))).setScale(2, BigDecimal.ROUND_HALF_UP).toString());
										vo.setTotal(new BigDecimal(Double.valueOf(vo.getTotal())+Double.valueOf(jObj.getString("TOTAL"))).setScale(2, BigDecimal.ROUND_HALF_UP).toString());
									}
								}
							}
						}
					}
				}
			}
			if(workMap.size() > 0)
				returnList = new ArrayList<NregsFmsWorksVO>(workMap.values());
			
			if(returnList != null && !returnList.isEmpty()){
				for (NregsFmsWorksVO vo : returnList){
					vo.setWage(convertLakhsIntoCrores(vo.getWage()));
					vo.setMaterial(convertLakhsIntoCrores(vo.getMaterial()));
					vo.setTotal(convertLakhsIntoCrores(vo.getTotal()));
				}
			}
			
		} catch (Exception e) {
			LOG.error(" Exception occured at getMgnregsParliamentWiseCombinedDetails() in FundManagementDashboardService class ",e);
		}
		return returnList;
	}

	private List<NregsFmsWorksVO> getMgnregsWorkDetailsLocationWise(List<ClientResponse> responseList, InputVO inputVO) {
		List<NregsFmsWorksVO> returnList = new ArrayList<NregsFmsWorksVO>();
		try {
			if (responseList != null && responseList.size() > 0) {
				for (ClientResponse response : responseList) {
					String output = response.getEntity(String.class);
					if (output != null && !output.isEmpty()) {
						JSONArray finalArray = new JSONArray(output);
						if (finalArray != null && finalArray.length() > 0) {
							for (int i = 0; i < finalArray.length(); i++) {
								JSONObject jObj = (JSONObject) finalArray.get(i);
								NregsFmsWorksVO vo = new NregsFmsWorksVO();

								vo.setUniqueId(jObj.getString("UNIQUE_ID"));
								vo.setDistrict(jObj.getString("DNAME"));
								if (inputVO.getLocationType() != null && inputVO.getLocationType().trim().equalsIgnoreCase("constituency"))
									vo.setConstituency(jObj.getString("ASSEMBLY_NAME"));
								else if (inputVO.getLocationType() != null && inputVO.getLocationType().trim().equalsIgnoreCase("mandal")) {
									vo.setConstituency(jObj.getString("ASSEMBLY_NAME"));
									vo.setMandal(jObj.getString("MNAME"));
								} else if (inputVO.getLocationType() != null && inputVO.getLocationType().trim().equalsIgnoreCase("panchayat")) {
									vo.setConstituency(jObj.getString("ASSEMBLY_NAME"));
									vo.setMandal(jObj.getString("MNAME"));
									vo.setPanchayat(jObj.getString("PNAME"));
								}
								vo.setCategory(jObj.getString("CAT_NAME"));
								vo.setWorks(jObj.getString("WORKS"));
								vo.setWage(convertLakhsIntoCrores(jObj.getString("WAGE")));
								vo.setMaterial(convertLakhsIntoCrores(jObj.getString("MATERIAL")));
								vo.setTotal(convertLakhsIntoCrores(jObj.getString("TOTAL")));

								returnList.add(vo);
							}
						}
					}
				}
			}

		} catch (Exception e) {
			LOG.error(" Exception occured at getMgnregsWorkDetailsLocationWise() in FundManagementDashboardService class ",e);
		}
		return returnList;
	}
    
    public  List<NregsFmsWorksVO> getMgnregsFMSWorksDetailsByCategory(InputVO inputVO) {
    	List<NregsFmsWorksVO> returnList = new ArrayList<NregsFmsWorksVO>();
   	 try {
   		    
   		        String str = convertingInputVOToString(inputVO);
				ClientResponse response = webServiceUtilService.callWebService("http://dbtrd.ap.gov.in/NregaDashBoardService/rest/FMSExpenditureService/FMSExpenditureFinalData", str);
		        
		        if (response.getStatus() != 200) {
		 	    	  throw new RuntimeException("Failed : HTTP error code : "+ response.getStatus());
		 	     } else {
		 	    	String output = response.getEntity(String.class);
		 	    	if(output != null && !output.isEmpty()){
		 	    		JSONArray finalArray = new JSONArray(output);
		 	    		if(finalArray!=null && finalArray.length()>0){
		 	    			for(int i=0;i<finalArray.length();i++){
		 	    				JSONObject jObj = (JSONObject) finalArray.get(i);
		 	    				NregsFmsWorksVO vo = new NregsFmsWorksVO();
		 	    				
		 	    				vo.setProgram(jObj.getString("PROGRAM"));
		 	    				vo.setHabitationName(jObj.getString("HABITATION_NAME"));
		 	    				vo.setWorkCode(jObj.getString("WORK_CODE"));
		 	    				vo.setWorkName(jObj.getString("WORK_NAME"));
		 	    				vo.setDescription(jObj.getString("DESCRIPTION"));
		 	    				vo.setAmountUnSkilled(jObj.getString("AMOUNT_UNSKILLED"));
		 	    				vo.setAmountMaterial(jObj.getString("AMOUNT_MATERIAL"));
		 	    				vo.setTotalCost(jObj.getString("TOTAL_COST"));
		 	    				vo.setTotalManDays(jObj.getString("TOTAL_MANDAYS"));
		 	    				vo.setWage(jObj.getString("WAGE"));
		 	    				vo.setMaterial(jObj.getString("MAT"));
		 	    				vo.setTotal(jObj.getString("TOTAL"));
		 	    				vo.setDays(jObj.getString("DAYS"));
		 	    				
		 	    				returnList.add(vo);
		 	    			}
		 	    		}
		 	    	}
		 	    } 
   	 } catch (Exception e ){
   		 LOG.error(" Exception occured at getMgnregsFMSWorksDetailsByCategory() in FundManagementDashboardService class ", e);
   	 }
   	 return returnList;
   }
   /* End */ 
    
    public List<PageComponentVO> getPageWiseComponentDetails(InputVO inputVO){
    	List<PageComponentVO> returnList = new ArrayList<PageComponentVO>(0);
    	try {
			List<Object[]> list = pageComponentDAO.getPageWiseComponents(inputVO.getPageId());
			if(list != null && !list.isEmpty()){
				for (Object[] obj : list) {
					Long pageId = Long.valueOf(obj[0] != null ? obj[0].toString():"0");
					PageComponentVO vo = getMatchedVO(returnList, pageId);
					if(vo == null){
						vo = new PageComponentVO();
						vo.setId(pageId);
						vo.setName(obj[1] != null ? obj[1].toString():"");
						vo.setShortName(obj[5] != null ? obj[5].toString():"");
							PageComponentVO subvo = new PageComponentVO();
							subvo.setId(Long.valueOf(obj[2] != null ? obj[2].toString():"0"));
							subvo.setName(obj[3] != null ? obj[3].toString():"");
							subvo.setOrder(Long.valueOf(obj[4] != null ? obj[4].toString():"0"));
							subvo.setUrl(obj[6] != null ? obj[6].toString():"");
							vo.getSubList().add(subvo);
						returnList.add(vo);
					}else{
						PageComponentVO subvo = new PageComponentVO();
						subvo.setId(Long.valueOf(obj[2] != null ? obj[2].toString():"0"));
						subvo.setName(obj[3] != null ? obj[3].toString():"");
						subvo.setOrder(Long.valueOf(obj[4] != null ? obj[4].toString():"0"));
						subvo.setUrl(obj[6] != null ? obj[6].toString():"");
						vo.getSubList().add(subvo);
					}
				}
			}
		} catch (Exception e) {
			LOG.error(" Exception occured at getPageWiseComponentDetails() in FundManagementDashboardService class ", e);
		}
    	return returnList;
    }
    
    private PageComponentVO getMatchedVO(List<PageComponentVO> list,Long id) {
   	 try {
   		 if (list == null || list.size() == 0 )
   			 return null;
   		 for(PageComponentVO vo:list) {
   			 if (vo.getId() == id) {
   				 return vo;
   			 }
   		 }
   	 } catch (Exception e) {
   		 LOG.error(" Exception raised at getMatchedVO () in FundManagementDashboardService class  ",e); 
   	 }
   	 return null;
   }
    
    /**
	 * @Author : Sravanth
	 * @Date : 15-12-2017
	 * @param IdNameVO inputVO
	 * @return String  
	 */
	public ResultVO savePageWiseComponents(IdNameVO inputVO) {
		ResultVO statusVO = new ResultVO();
		try {
			if (inputVO.getComponentIds() != null && !inputVO.getComponentIds().isEmpty()) {
				Long pageId = inputVO.getId();
				Long orderNo = Long.valueOf(pageId.toString()+"01");
				for (Long id : inputVO.getComponentIds()) {
					PageComponent model = pageComponentDAO.get(id);
					model.setOrderNo(orderNo);
					pageComponentDAO.save(model);
					orderNo++;
				}
				statusVO.setMessage("success");
				statusVO.setStatusCode(0);
			}
		} catch (Exception e) {
			LOG.error("Exception occured in saveFavouriteComponentOrderDtls from UserServiceImpl ",e);
			statusVO.setMessage("fail");
			statusVO.setStatusCode(1);
		}
		return statusVO;
	}
	
}
