package com.itgrids.service.impl;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.log4j.Logger;
import org.codehaus.jettison.json.JSONArray;
import org.codehaus.jettison.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.itgrids.dao.IAssemblyMlaDAO;
import com.itgrids.dao.IComponentTargetConfigurationDAO;
import com.itgrids.dao.IComponentTargetConfigurationTempDAO;
import com.itgrids.dao.IComponentTargetDAO;
import com.itgrids.dao.IComponentWiseAchievementConfigurationDAO;
import com.itgrids.dao.IComponentWiseAchievementConfigurationTempDAO;
import com.itgrids.dao.IConstituencyDAO;
import com.itgrids.dao.IDepartmentDAO;
import com.itgrids.dao.IFundSanctionDAO;
import com.itgrids.dao.INregaComponentCommentsDAO;
import com.itgrids.dao.INregaWorkExpenditureLocationDAO;
import com.itgrids.dto.InputVO;
import com.itgrids.dto.LocationVO;
import com.itgrids.dto.NregsDataVO;
import com.itgrids.dto.NregsFmsWorksVO;
import com.itgrids.dto.NregsOverviewVO;
import com.itgrids.dto.ResponseVO;
import com.itgrids.model.ComponentTarget;
import com.itgrids.model.ComponentTargetConfiguration;
import com.itgrids.model.ComponentTargetConfigurationTemp;
import com.itgrids.service.IConstituencyWiseWorkStatusService;
import com.itgrids.service.integration.external.WebServiceUtilService;
import com.itgrids.utils.CommonMethodsUtilService;
import com.itgrids.utils.DateUtilService;
import com.itgrids.utils.IConstants;
import com.sun.jersey.api.client.ClientResponse;

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
	@Autowired
	private INregaWorkExpenditureLocationDAO nregaWorkExpenditureLocationDAO;
	@Autowired
	private IDepartmentDAO departmentDAO;
	@Autowired
	private WebServiceUtilService webServiceUtilService;
	@Autowired
	private IComponentTargetDAO componentTargetDAO;
	@Autowired
	private IComponentTargetConfigurationDAO componentTargetConfigurationDAO;
	@Autowired
	private IComponentTargetConfigurationTempDAO componentTargetConfigurationTempDAO;
	@Autowired
	private INregaComponentCommentsDAO nregaComponentCommentsDAO;
	@Autowired
	private IComponentWiseAchievementConfigurationDAO componentWiseAchievementConfigurationDAO;
	@Autowired
	private IComponentWiseAchievementConfigurationTempDAO componentWiseAchievementConfigurationTempDAO;
	
	/*
	 * Swadhin K Lenka
	 * @see com.itgrids.service.IConstituencyWiseWorkStatusService#getFundManagementSystemWorkDetails(java.util.List, java.lang.Long, java.lang.String, java.lang.String)
	 */
	public LocationVO getFundManagementSystemWorkDetails(List<Long> financialYearIdsList, List<Long> departmentIdList, String startDateStr,String endDateStr,Long locationId){
		try{
			Map<Long,Map<Long,Long>> deptIdAndGrandTypeIdAndWorkCount = new HashMap<Long,Map<Long,Long>>();
			deptIdAndGrandTypeIdAndWorkCount.put(1L, new HashMap<Long,Long>());
			deptIdAndGrandTypeIdAndWorkCount.put(2L, new HashMap<Long,Long>());
			Map<Long,Map<Long,Long>> deptIdAndGrandTypeIdAndAmount = new HashMap<Long,Map<Long,Long>>();
			Map<Long,String> deptIdAndNameMap = new HashMap<Long,String>();
			deptIdAndNameMap.put(1L, "ENC");
			deptIdAndNameMap.put(2L, "RWS");
			deptIdAndNameMap.put(3L, "Mahatma Gandhi National Rural Employment Gurantee Scheme");
			DecimalFormat df = new DecimalFormat("0.000");
			LocationVO locationVO = new LocationVO();
			Date startDate = commonMethodsUtilService.stringTODateConvertion(startDateStr,"dd/MM/yyyy","");
			Date endDate = commonMethodsUtilService.stringTODateConvertion(endDateStr,"dd/MM/yyyy","");
			financialYearIdsList = commonMethodsUtilService.makeEmptyListByZeroValue(financialYearIdsList);
			departmentIdList = commonMethodsUtilService.makeEmptyListByZeroValue(departmentIdList);
			String type="";
			Long superLocationId = null;
			if(locationId.toString().trim().length() > 1){
				Long firstDigit = Long.parseLong(locationId.toString().substring(0,1));
				superLocationId = Long.parseLong(locationId.toString().substring(1));
				if(firstDigit.longValue() == 3L){
					type = "district";
				}else if(firstDigit.longValue() == 4L){
					type = "constituency";
				}
			}else{
				superLocationId = 0L;
			}
			
			if(departmentIdList.size() == 0 || departmentIdList.get(0) == 0){
				List<LocationVO> overviewList = new ArrayList<LocationVO>();
				LocationVO locationVO2 = null;
				
				List<Object[]> worksSummery = fundSanctionDAO.getFundSanstionLocationWise(financialYearIdsList,departmentIdList,startDate,endDate,superLocationId,type);
				buildDeptWiseMap(worksSummery,deptIdAndGrandTypeIdAndWorkCount,deptIdAndGrandTypeIdAndAmount);
				Long deptWiseTotalAmount = null;
				for(Entry<Long,Map<Long,Long>> param : deptIdAndGrandTypeIdAndWorkCount.entrySet()){
					deptWiseTotalAmount = new Long(0L);
					locationVO2 = new LocationVO();
					locationVO.setPlainWorkCount(0L);
					locationVO.setScpWorkCount(0L);
					locationVO.setTspWorkCount(0L);
					
					locationVO2.setDepartmentName(deptIdAndNameMap.get(param.getKey()));
					locationVO2.setPlainWorkCount(commonMethodsUtilService.getLongValueForObject(param.getValue().get(1L)));
					locationVO2.setScpWorkCount(commonMethodsUtilService.getLongValueForObject(param.getValue().get(2L)));
					locationVO2.setTspWorkCount(commonMethodsUtilService.getLongValueForObject(param.getValue().get(3L)));
					if(deptIdAndGrandTypeIdAndAmount.get(param.getKey())!= null){
						if(deptIdAndGrandTypeIdAndAmount.get(param.getKey()).get(1L)!= null){
							locationVO2.setPlainAmountInDecimal(df.format(commonMethodsUtilService.getLongValueForObject(deptIdAndGrandTypeIdAndAmount.get(param.getKey()).get(1L))/10000000D));
							deptWiseTotalAmount = deptWiseTotalAmount + deptIdAndGrandTypeIdAndAmount.get(param.getKey()).get(1L);
							}	
							if(deptIdAndGrandTypeIdAndAmount.get(param.getKey()).get(2L)!= null){
							locationVO2.setScpAmountInDecimal(df.format(commonMethodsUtilService.getLongValueForObject(deptIdAndGrandTypeIdAndAmount.get(param.getKey()).get(2L))/10000000D));	
							deptWiseTotalAmount = deptWiseTotalAmount + deptIdAndGrandTypeIdAndAmount.get(param.getKey()).get(2L);
							}
							if(deptIdAndGrandTypeIdAndAmount.get(param.getKey()).get(3L)!= null){
							locationVO2.setTspAmountInDecimal(df.format(commonMethodsUtilService.getLongValueForObject(deptIdAndGrandTypeIdAndAmount.get(param.getKey()).get(3L))/10000000D));
							deptWiseTotalAmount = deptWiseTotalAmount + deptIdAndGrandTypeIdAndAmount.get(param.getKey()).get(3L);
							}
					}else{
						locationVO2.setPlainAmountInDecimal("0.0");
					}
					locationVO2.setWorkNumber((locationVO2.getPlainWorkCount() + locationVO2.getScpWorkCount() + locationVO2.getTspWorkCount()));
					locationVO2.setAmount(deptWiseTotalAmount);
					locationVO2.setAmountInDecimal(df.format(locationVO2.getAmount()/10000000D));
					overviewList.add(locationVO2);
					
				}
				//call one more service for mgnrgs
				InputVO inputVO = new InputVO();
				inputVO.setFinancialYrIdList(financialYearIdsList);
				inputVO.setLocationId(locationId);
				NregsFmsWorksVO fmsWorksVO = getConstituencyWiseNregsWorksOverview(inputVO);
				locationVO2 = new LocationVO();
				locationVO2.setDepartmentName("Mahatma Gandhi National Rural Employment Gurantee Scheme");
				locationVO2.setWorkNumber(fmsWorksVO.getFinalWorks());
				locationVO2.setAmountInDecimal(fmsWorksVO.getFinalAmount());
				locationVO2.setWage(fmsWorksVO.getWage());
				locationVO2.setMaterial(fmsWorksVO.getMaterial());
				overviewList.add(locationVO2);
				locationVO.getLocationList1().addAll(overviewList);
				return locationVO;
				
			}else{
			
			List<Object[]> workList = fundSanctionDAO.getFundManagementSystemWorkDetails(financialYearIdsList,departmentIdList,startDate,endDate,superLocationId,type);
			//create a map of govtOrderId and govtOrderDtlsVO map
			Map<Long, LocationVO> govtOrderMap = new HashMap<Long,LocationVO>();
			LocationVO  locationVOForMap = null;
			//create a list of workDtlsVO
			List<LocationVO> locationVOs = new ArrayList<LocationVO>();
			LocationVO  locationVOForListForWork = null;
			
			locationVO.setPlainGoCount(0L);
			locationVO.setScpGoCount(0L);
			locationVO.setTspGoCount(0L);
			
			locationVO.setPlainWorkCount(0L);
			locationVO.setScpWorkCount(0L);
			locationVO.setTspWorkCount(0L);
			
			locationVO.setPlainAmount(0L);
			locationVO.setScpAmount(0L);
			locationVO.setTspAmount(0L);
			
			if(workList != null && workList.size() > 0){
				for(Object[] param : workList){
					locationVOForMap = govtOrderMap.get(commonMethodsUtilService.getLongValueForObject(param[6]));
					if(null == locationVOForMap){
						locationVOForMap = new LocationVO();
						govtOrderMap.put(commonMethodsUtilService.getLongValueForObject(param[6]),locationVOForMap);
					}
					locationVOForMap.setGovtOrderId(commonMethodsUtilService.getLongValueForObject(param[6]));
					locationVOForMap.setGoNoDate(commonMethodsUtilService.getStringValueForObject(param[9]).trim());
					locationVOForMap.setIssueDate(commonMethodsUtilService.getStringValueForObject(param[10]));
					locationVOForMap.setWorkName(commonMethodsUtilService.getStringValueForObject(param[12]).trim());
					locationVOForMap.setProgramId(commonMethodsUtilService.getLongValueForObject(param[15]));
					locationVOForMap.setProgramName(commonMethodsUtilService.getStringValueForObject(param[16]));
					locationVOForMap.setFilePath(commonMethodsUtilService.getStringValueForObject(param[17]));
					locationVOForMap.setGrantTypeId(commonMethodsUtilService.getLongValueForObject(param[18]));
					locationVOForMap.setWorkNumber(locationVOForMap.getWorkNumber() + 1L);
					locationVOForMap.setAmount(locationVOForMap.getAmount() + commonMethodsUtilService.getLongValueForObject(param[11]));
					
					
					locationVOForListForWork = new LocationVO();
					locationVOForListForWork.setGoNoDate(commonMethodsUtilService.getStringValueForObject(param[9]));
					locationVOForListForWork.setWorkName(commonMethodsUtilService.getStringValueForObject(param[8]));
					locationVOForListForWork.setTehsilName(commonMethodsUtilService.getStringValueForObject(param[3]));
					locationVOForListForWork.setPanchayatName(commonMethodsUtilService.getStringValueForObject(param[5]));
					locationVOForListForWork.setAmount(commonMethodsUtilService.getLongValueForObject(param[11]));
					locationVOForListForWork.setAmountInDecimal(df.format(commonMethodsUtilService.getLongValueForObject(param[11])/10000000D));
					locationVOs.add(locationVOForListForWork);   
				}
				
				List<LocationVO> locationVOListForGoNo = new ArrayList<LocationVO>(govtOrderMap.values());
				
				if(locationVOListForGoNo != null && locationVOListForGoNo.size() > 0){
					for(LocationVO param : locationVOListForGoNo){
						if(param.getGrantTypeId().longValue() == 1L){
							locationVO.setPlainGoCount(locationVO.getPlainGoCount()+1L);
							//locationVO.setPlainWorkCount(locationVO.getPlainWorkCount() + param.getWorkNumber());
							//locationVO.setPlainAmount(locationVO.getPlainAmount() + param.getAmount());
						}else if(param.getGrantTypeId().longValue() == 2L){
							locationVO.setScpGoCount(locationVO.getScpGoCount()+1L);
							//locationVO.setScpWorkCount(locationVO.getScpWorkCount() + param.getWorkNumber());
							//locationVO.setScpAmount(locationVO.getScpAmount() + param.getAmount());
						}else if(param.getGrantTypeId().longValue() == 3L){
							locationVO.setTspGoCount(locationVO.getTspGoCount()+1L);
							//locationVO.setTspWorkCount(locationVO.getTspWorkCount() + param.getWorkNumber());
							//locationVO.setTspAmount(locationVO.getTspAmount() + param.getAmount());
						}
					}
				}
				
				//finally convert the amount into lakh
				//locationVO.setPlainAmountInDecimal(df.format(locationVO.getPlainAmount()/10000000D));
				//locationVO.setScpAmountInDecimal(df.format(locationVO.getScpAmount()/10000000D));
				//locationVO.setTspAmountInDecimal(df.format(locationVO.getTspAmount()/10000000D));
				
				Map<Long,List<LocationVO>> programIdAndListOfGo = new HashMap<Long,List<LocationVO>>();
				List<LocationVO> goOrderVoList = null;
				if(locationVOListForGoNo != null && locationVOListForGoNo.size() > 0){
					for(LocationVO param : locationVOListForGoNo){
						param.setAmountInDecimal(df.format(param.getAmount()/10000000D));
						goOrderVoList = programIdAndListOfGo.get(param.getProgramId());
						if(null == goOrderVoList){
							goOrderVoList = new ArrayList<LocationVO>();
							programIdAndListOfGo.put(param.getProgramId(), goOrderVoList);
						}
						goOrderVoList.add(param);
					}
					
				}
				List<LocationVO> programWiseList = new ArrayList<LocationVO>();
				LocationVO vo = null;
				if(programIdAndListOfGo != null && programIdAndListOfGo.size() > 0 ){
					for(Entry<Long,List<LocationVO>> param : programIdAndListOfGo.entrySet()){
						vo = new LocationVO();
						vo.setProgramId(param.getKey());
						vo.setProgramName(param.getValue().get(0).getProgramName());
						calculateProgramWiseDtls(vo,param.getValue());
						vo.getLocationList1().addAll(param.getValue());
						programWiseList.add(vo);
					}
				}
				
				//calculate total no of go and total work and total amount
				
				
				
				locationVO.getLocationList1().addAll(programWiseList);
				//locationVO.getLocationList2().addAll(locationVOs);
			}
			List<Object[]> worksSummery = fundSanctionDAO.getFundSanstionLocationWise(financialYearIdsList,departmentIdList,startDate,endDate,superLocationId,type);
			buildDeptWiseMap(worksSummery,deptIdAndGrandTypeIdAndWorkCount,deptIdAndGrandTypeIdAndAmount);
			for(Entry<Long,Map<Long,Long>> outerParam : deptIdAndGrandTypeIdAndWorkCount.entrySet()){
				for(Entry<Long,Long> innerParam : outerParam.getValue().entrySet()){
					if(innerParam.getKey().longValue() == 1L){
						locationVO.setPlainWorkCount(innerParam.getValue());
						locationVO.setPlainAmount(deptIdAndGrandTypeIdAndAmount.get(outerParam.getKey()).get(innerParam.getKey()));
						locationVO.setPlainAmountInDecimal(df.format(locationVO.getPlainAmount()/10000000D));
					}else if(innerParam.getKey().longValue() == 2L){
						locationVO.setScpWorkCount(innerParam.getValue());
						locationVO.setScpAmount(deptIdAndGrandTypeIdAndAmount.get(outerParam.getKey()).get(innerParam.getKey()));
						locationVO.setScpAmountInDecimal(df.format(locationVO.getScpAmount()/10000000D));
					}else if(innerParam.getKey().longValue() == 3L){
						locationVO.setTspWorkCount(innerParam.getValue());
						locationVO.setTspAmount(deptIdAndGrandTypeIdAndAmount.get(outerParam.getKey()).get(innerParam.getKey()));
						locationVO.setTspAmountInDecimal(df.format(locationVO.getTspAmount()/10000000D));
					}
				}
			}
			locationVO.setGovtOrderCount(locationVO.getPlainGoCount()+locationVO.getScpGoCount()+locationVO.getTspGoCount());
			locationVO.setWorkNumber(locationVO.getPlainWorkCount()+locationVO.getScpWorkCount()+locationVO.getTspWorkCount());
			locationVO.setAmount(locationVO.getPlainAmount()+locationVO.getScpAmount()+locationVO.getTspAmount());
			locationVO.setAmountInDecimal(df.format(locationVO.getAmount()/10000000D));
			return locationVO;
			}
		}catch(Exception e){
			LOG.error(" Exception occured in FundManagementDashboardService ,getFundManagementSystemWorkDetails() ",e);
		}
		return null;
	}
	public void calculateProgramWiseDtls(LocationVO vo,List<LocationVO> locationVOs){
		DecimalFormat df = new DecimalFormat("0.000");
		Long totalWork = 0L;
		Long totalAmount = 0L;
		if(locationVOs != null && locationVOs.size() > 0){
			for(LocationVO param : locationVOs){
				totalWork = totalWork + param.getWorkNumber();
				totalAmount = totalAmount + param.getAmount();
			}
			vo.setWorkNumber(totalWork);
			vo.setAmount(totalAmount);
			vo.setAmountInDecimal(df.format(totalAmount/100000D));
		}
		
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
					vo.setLocationId(Long.parseLong("4"+commonMethodsUtilService.getStringValueForObject(param[0])));
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
		try{
			List<LocationVO> finalList = new ArrayList<LocationVO>(0);
			
			List<Object[]> objects=assemblyMlaDAO.getAllConstituencyDetails(Long.parseLong(inputVO.getLocationId().toString().substring(1)));
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
		   }catch(Exception e){
			   LOG.error(" Exception occured in FundManagementDashboardService ,getDistrictNameAndMlaNameByConsitutency() ",e);
				}
		 return null;
	    }
	
	public List<NregsFmsWorksVO> getProgramWiseWorksDetails(InputVO inputVO){
		List<NregsFmsWorksVO> returnList = new ArrayList<NregsFmsWorksVO>(0);
		try {
			List<Long> financialYearIds = new ArrayList<Long>(0);
			Long[] finanArr = IConstants.PRESENT_FINANCIAL_YEAR_IDS;
			if(finanArr != null && finanArr.length > 0){
				for (int i = 0; i < finanArr.length; i++) {
					financialYearIds.add(finanArr[0]);
				}
			}
			
			List<Object[]> list = nregaWorkExpenditureLocationDAO.getProgramExpenditureDetailsInConstituency(inputVO.getConstituencyId(), financialYearIds);
			if(list != null && !list.isEmpty()){
				for (Object[] obj : list) {
					NregsFmsWorksVO vo = new NregsFmsWorksVO();
					vo.setProgramId(Long.valueOf(obj[0] != null ? obj[0].toString():"0"));
					vo.setProgram(obj[1] != null ? obj[1].toString():"0");
					vo.setNoOfWorks(Long.valueOf(obj[2] != null ? obj[2].toString():"0"));
					vo.setLocationsCount(Long.valueOf(obj[3] != null ? obj[3].toString():"0"));
					vo.setWage(obj[4] != null ? obj[4].toString():"0");
					vo.setMaterial(obj[5] != null ? obj[5].toString():"0");
					vo.setTotal(obj[6] != null ? obj[6].toString():"0");
					vo.setGrounded(Long.valueOf(obj[7] != null ? obj[7].toString():"0"));
					vo.setInProgress(Long.valueOf(obj[8] != null ? obj[8].toString():"0"));
					vo.setCompleted(Long.valueOf(obj[9] != null ? obj[9].toString():"0"));
					
					returnList.add(vo);
				}
			}
		} catch (Exception e) {
			LOG.error(" Exception occured in getProgramWiseWorksDetails in ConstituencyWiseWorkStatusService ",e);
		}
		return returnList;
	}
	
	/*
	 * Date : 18/11/2017
	 * Author :Sravanth
	 * @description : getConstituencyWiseNregsWorksOverview
	 */
	public NregsFmsWorksVO getConstituencyWiseNregsWorksOverview(InputVO inputVO){
		NregsFmsWorksVO returnvo = new NregsFmsWorksVO();
		try {
			String type="";
			Long superLocationId = null;
			if(inputVO.getLocationId().toString().trim().length() > 1){
				Long firstDigit = Long.parseLong(inputVO.getLocationId().toString().substring(0,1));
				superLocationId = Long.parseLong(inputVO.getLocationId().toString().substring(1));
				if(firstDigit.longValue() == 3L){
					type = "district";
				}else if(firstDigit.longValue() == 4L){
					type = "constituency";
				}
			}else{
				superLocationId = 0L;
			}
			//financialYearIdsList = commonMethodsUtilService.makeEmptyListByZeroValue(inputVO.getFinancialYrIdList());
			List<Long> financialYearIds = new ArrayList<Long>(0);
			Long[] finanArr = IConstants.PRESENT_FINANCIAL_YEAR_IDS;
			if(finanArr != null && finanArr.length > 0){
				for (int i = 0; i < finanArr.length; i++) {
					financialYearIds.add(finanArr[i]);
				}
			}
			
			List<Object[]> list = nregaWorkExpenditureLocationDAO.getWorkWiseExpenditureOverviewInConstituency(superLocationId, financialYearIds, type);
			if(list != null && !list.isEmpty()){
				for (Object[] obj : list) {
					returnvo.setFinalWorks(Long.valueOf(obj[1] != null ? obj[1].toString():"0"));
					returnvo.setWage(convertRupeesIntoCrores(obj[5] != null ? obj[5].toString():"0"));
					returnvo.setMaterial(convertRupeesIntoCrores(obj[4] != null ? obj[4].toString():"0"));
					returnvo.setFinalAmount(convertRupeesIntoCrores(obj[0] != null ? obj[0].toString():"0"));
					returnvo.setTotalAmount(commonMethodsUtilService.getLongValueForObject(obj[0]));
				}
			}
		} catch (Exception e) {
			LOG.error(" Exception occured in getConstituencyWiseNregsWorksDetails in ConstituencyWiseWorkStatusService ",e);
		}
		return returnvo;
	}
	
	/*
	 * Date : 18/11/2017
	 * Author :Sravanth
	 * @description : getConstituencyWiseNregsWorksDetails
	 */
	public List<NregsFmsWorksVO> getConstituencyWiseNregsWorksDetails(InputVO inputVO){
		List<NregsFmsWorksVO> returnList = new ArrayList<NregsFmsWorksVO>(0);
		try {
			NregsFmsWorksVO othervo = new NregsFmsWorksVO();
			othervo.setId(999999L);
			othervo.setWorkName("Other Works (Less than 50Lakhs)");
			othervo.setWage("0");
			othervo.setMaterial("0");
			othervo.setTotal("0");
			othervo.setGrounded(0L);
			othervo.setInProgress(0L);
			othervo.setCompleted(0L);
			othervo.setTotalLong(0L);
			
			String type="";
			Long superLocationId = null;
			if(inputVO.getLocationId().toString().trim().length() > 1){
				Long firstDigit = Long.parseLong(inputVO.getLocationId().toString().substring(0,1));
				superLocationId = Long.parseLong(inputVO.getLocationId().toString().substring(1));
				if(firstDigit.longValue() == 3L){
					type = "district";
				}else if(firstDigit.longValue() == 4L){
					type = "constituency";
				}
			}else{
				superLocationId = 0L;
			}
			//financialYearIdsList = commonMethodsUtilService.makeEmptyListByZeroValue(inputVO.getFinancialYrIdList());
			List<Long> financialYearIds = new ArrayList<Long>(0);
			Long[] finanArr = IConstants.PRESENT_FINANCIAL_YEAR_IDS;
			if(finanArr != null && finanArr.length > 0){
				for (int i = 0; i < finanArr.length; i++) {
					financialYearIds.add(finanArr[i]);
				}
			}
			
			Long totalWorks = 0L;
			Long finalAmount = 0L;
			Long totalWageAmount = 0L;
			Long totalMaterialAmount = 0L;
			List<Object[]> list = nregaWorkExpenditureLocationDAO.getWorkWiseExpenditureDetailsInConstituency(superLocationId, financialYearIds, type);
			if(list != null && !list.isEmpty()){
				for (Object[] obj : list) {
					Long totalAmount = Long.valueOf(obj[5] != null ? obj[5].toString():"0");
					Long materialAmount = Long.valueOf(obj[4] != null ? obj[4].toString():"0");
					Long wageAmount = Long.valueOf(obj[3] != null ? obj[3].toString():"0");
					String workName = obj[1] != null ? obj[1].toString():"0";
					NregsFmsWorksVO vo = null;
					if(totalAmount != null && totalAmount.longValue() >= 5000000L){
						vo = new NregsFmsWorksVO();
						vo.setId(Long.valueOf(obj[0] != null ? obj[0].toString():"0"));
						vo.setWorkName(workName);
						vo.setWage(obj[3] != null ? obj[3].toString():"0");
						vo.setMaterial(obj[4] != null ? obj[4].toString():"0");
						vo.setTotal(obj[5] != null ? obj[5].toString():"0");
						vo.setTotalLong(Long.valueOf(obj[5] != null ? obj[5].toString():"0"));
						vo.setGrounded(Long.valueOf(obj[6] != null ? obj[6].toString():"0"));
						vo.setInProgress(Long.valueOf(obj[7] != null ? obj[7].toString():"0"));
						vo.setCompleted(Long.valueOf(obj[8] != null ? obj[8].toString():"0"));
						totalWorks = totalWorks+vo.getGrounded();
						returnList.add(vo);
					}else{
						/*vo = getMatchedVO(returnList, "Other Works");
						if(vo == null){
							vo = new NregsFmsWorksVO();
							vo.setId(999999L);
							vo.setWorkName("Other Works");
							vo.setWage(obj[3] != null ? obj[3].toString():"0");
							vo.setMaterial(obj[4] != null ? obj[4].toString():"0");
							vo.setTotal(obj[5] != null ? obj[5].toString():"0");
							vo.setGrounded(Long.valueOf(obj[6] != null ? obj[6].toString():"0"));
							vo.setInProgress(Long.valueOf(obj[7] != null ? obj[7].toString():"0"));
							vo.setCompleted(Long.valueOf(obj[8] != null ? obj[8].toString():"0"));
							totalWorks = totalWorks+vo.getGrounded();
							returnList.add(vo);
						}
						else{*/
							othervo.setWage(String.valueOf(Long.valueOf(othervo.getWage())+Long.valueOf(obj[3] != null ? obj[3].toString():"0")));
							othervo.setMaterial(String.valueOf(Long.valueOf(othervo.getMaterial())+Long.valueOf(obj[4] != null ? obj[4].toString():"0")));
							othervo.setTotal(String.valueOf(Long.valueOf(othervo.getTotal())+Long.valueOf(obj[5] != null ? obj[5].toString():"0")));
							othervo.setTotalLong(othervo.getTotalLong()+Long.valueOf(obj[5] != null ? obj[5].toString():"0"));
							othervo.setGrounded(othervo.getGrounded()+Long.valueOf(obj[6] != null ? obj[6].toString():"0"));
							othervo.setInProgress(othervo.getInProgress()+Long.valueOf(obj[7] != null ? obj[7].toString():"0"));
							othervo.setCompleted(othervo.getCompleted()+Long.valueOf(obj[8] != null ? obj[8].toString():"0"));
							totalWorks = totalWorks+Long.valueOf(obj[6] != null ? obj[6].toString():"0");
						//}
					}
					finalAmount = finalAmount+totalAmount;
					totalWageAmount = totalWageAmount+wageAmount;
					totalMaterialAmount = totalMaterialAmount+materialAmount;
				}
			}
			
			Collections.sort(returnList, countWiseDescendingOrder);
			
			returnList.add(returnList.size(), othervo);
			
			if(returnList != null && !returnList.isEmpty()){
				returnList.get(0).setFinalWorks(totalWorks);
				returnList.get(0).setFinalAmount(convertRupeesIntoCrores(finalAmount.toString()));
				returnList.get(0).setWageAmount(convertRupeesIntoCrores(totalWageAmount.toString()));
				returnList.get(0).setMaterialAmount(convertRupeesIntoCrores(totalMaterialAmount.toString()));
				for (NregsFmsWorksVO nregsFmsWorksVO : returnList) {
					nregsFmsWorksVO.setWage(convertRupeesIntoCrores(nregsFmsWorksVO.getWage()));
					nregsFmsWorksVO.setMaterial(convertRupeesIntoCrores(nregsFmsWorksVO.getMaterial()));
					nregsFmsWorksVO.setTotal(convertRupeesIntoCrores(nregsFmsWorksVO.getTotal()));
				}
			}
		} catch (Exception e) {
			LOG.error(" Exception occured in getConstituencyWiseNregsWorksDetails in ConstituencyWiseWorkStatusService ",e);
		}
		return returnList;
	}
	
	public static Comparator<NregsFmsWorksVO> countWiseDescendingOrder = new Comparator<NregsFmsWorksVO>() {
		public int compare(NregsFmsWorksVO o1, NregsFmsWorksVO o2) {
			if(o1.getTotalLong() != null && o2.getTotalLong() != null){
				return o2.getTotalLong().compareTo(o1.getTotalLong());
			}
			return 0;
		}
	};
	
	/*
	 * Date : 18/11/2017
	 * Author :Sravanth
	 * @description : getMatchedVO
	 */
	public NregsFmsWorksVO getMatchedVO(List<NregsFmsWorksVO> list,String workName){
		try{
			if(commonMethodsUtilService.isListOrSetValid(list)){
				for (NregsFmsWorksVO nregsFmsWorksVO : list) {
					if(nregsFmsWorksVO.getWorkName().trim().equalsIgnoreCase(workName)){
						return nregsFmsWorksVO;
					}
				}
			}
			
		}catch(Exception e){
			LOG.error("Exception raised at getMatchedVO - ConstituencyWiseWorkStatusService service", e);
		}
		return null;
	}
	
	public String convertRupeesIntoCrores(String value){
		String returnVal = null;
		try {
			if(value != null){
				returnVal = new BigDecimal(Long.valueOf(value)/10000000.00).setScale(2, BigDecimal.ROUND_HALF_UP).toString();
				//returnVal = returnVal+" Cr";
			}
		} catch (Exception e) {
			LOG.error("Exception raised at convertRupeesIntoCrores - NREGSTCSService service", e);
		}
		return returnVal;
	}
	public List<LocationVO> getDepartmentNames(){
		try{
			List<LocationVO> finalList = new ArrayList<LocationVO>(0);
			List<Object[]> objects=departmentDAO.getAllDepartments();
			 for(Object[] param:objects){
				LocationVO vo = new LocationVO();
				vo.setLocationId(commonMethodsUtilService.getLongValueForObject(param[0]));
				vo.setDepartmentName(commonMethodsUtilService.getStringValueForObject(param[1]));
				finalList.add(vo);
			   }
			 return finalList;
		   }catch(Exception e){
			   LOG.error(" Exception occured in FundManagementDashboardService ,getDepartmentNames() ",e);
		    }
		  return null;
	}
	public void buildDeptWiseMap(List<Object[]> worksSummery,Map<Long,Map<Long,Long>> deptIdAndGrandTypeIdAndWorkCount,Map<Long,Map<Long,Long>> deptIdAndGrandTypeIdAndAmount){
		try{
			Map<Long,Long> grantTypeIdAndworkCountMap = null;
			if(worksSummery != null && worksSummery.size() > 0){
				for(Object[] param : worksSummery){
					grantTypeIdAndworkCountMap = deptIdAndGrandTypeIdAndWorkCount.get(commonMethodsUtilService.getLongValueForObject(param[0]));
					if(null == grantTypeIdAndworkCountMap){
						grantTypeIdAndworkCountMap = new HashMap<Long,Long>();
						grantTypeIdAndworkCountMap.put(commonMethodsUtilService.getLongValueForObject(param[2]), commonMethodsUtilService.getLongValueForObject(param[4]));
						deptIdAndGrandTypeIdAndWorkCount.put(commonMethodsUtilService.getLongValueForObject(param[0]), grantTypeIdAndworkCountMap);
					}else{
						grantTypeIdAndworkCountMap.put(commonMethodsUtilService.getLongValueForObject(param[2]), commonMethodsUtilService.getLongValueForObject(param[4]));
					}
					
					grantTypeIdAndworkCountMap = deptIdAndGrandTypeIdAndAmount.get(commonMethodsUtilService.getLongValueForObject(param[0]));
					if(null == grantTypeIdAndworkCountMap){
						grantTypeIdAndworkCountMap = new HashMap<Long,Long>();
						grantTypeIdAndworkCountMap.put(commonMethodsUtilService.getLongValueForObject(param[2]), commonMethodsUtilService.getLongValueForObject(param[5]));
						deptIdAndGrandTypeIdAndAmount.put(commonMethodsUtilService.getLongValueForObject(param[0]), grantTypeIdAndworkCountMap);
					}else{
						grantTypeIdAndworkCountMap.put(commonMethodsUtilService.getLongValueForObject(param[2]), commonMethodsUtilService.getLongValueForObject(param[5]));
					}
					
					//grantTypeAndNameMap.put(commonMethodsUtilService.getLongValueForObject(param[0]), commonMethodsUtilService.getStringValueForObject(param[1]));
				}
			}
		}catch(Exception e){
			 LOG.error(" Exception occured in FundManagementDashboardService ,buildDeptWiseMap() ",e);
		}
	}
	
	public ResponseVO savingLabourBudgetRangeWiseExpenditureDetails(InputVO inputVO){
		ResponseVO responseVO = new ResponseVO();
		try {
			Map<String,String> wageExpMap = new LinkedHashMap<String,String>();
	    	Map<String,String> matExpMap = new LinkedHashMap<String,String>();
	    	Map<String,Long> componentTargetMap = new LinkedHashMap<String,Long>();
	    	List<ComponentTarget> list = componentTargetDAO.getAll();
	    	/*if(list != null && !list.isEmpty()){
	    		for (ComponentTarget componentTarget : list) {
					
				}
	    	}*/
	    	
			//InputVO inputVO = new InputVO();
			//inputVO.setFromDate("2017-04-01");inputVO.setToDate("2017-12-31");inputVO.setYear("2017");inputVO.setLocationType("state");inputVO.setLocationIdStr("-1");
			
			//{"fromDate" : "2017-04-31","toDate" : "2018-01-31","year" : "2017","locationType" : "state","locationId" : "-1","FromRange" : "300","ToRange" : "400","pType" : "TOT"}
			String[] rangeArr = {"0-0","0-1","1-5","5-10","10-20","20-30","30-50","50-100","100-200","200-300","300-400","400-5000"};
			String[] pTypeArr = {"WAGE","MAT"};
			for (int i = 0; i < pTypeArr.length; i++) {
				for (int j = 0; j < rangeArr.length; j++) {
					String[] rangeStr = rangeArr[j].split("-");
					String pType = pTypeArr[i];
					inputVO.setFromRange(Long.valueOf(rangeStr[0]));
					inputVO.setToRange(Long.valueOf(rangeStr[1]));
					inputVO.setpType(pType);
					
					String str = convertingInputVOToString(inputVO);
					ClientResponse response = webServiceUtilService.callWebService("http://dbtrd.ap.gov.in/NregaDashBoardService/rest/APLabourBudgetPanchayats/APLabourBdgtPanchayats", str);
					if(response.getStatus() != 200){
			 	    	  throw new RuntimeException("Failed : HTTP error code : "+ response.getStatus());
			 	      }else{
			 	    	  System.out.println("Completed for "+pType+" - "+rangeArr[j]);
			 	    	 String output = response.getEntity(String.class);
				 	    	if(output != null && !output.isEmpty()){
				 	    		JSONArray finalArray = new JSONArray(output);
				 	    		if(finalArray!=null && finalArray.length()>0){
				 	    			for(int k=0;k<finalArray.length();k++){
				 	    				JSONObject jObj = (JSONObject) finalArray.get(k);
				 	    				String uniqueCode = jObj.getString("UNIQUEID");
				 	    				String expenditure = jObj.getString("TOTALEXPENDITURE");
				 	    				if(i == 0)
				 	    					wageExpMap.put(uniqueCode, expenditure);
				 	    				else
				 	    					matExpMap.put(uniqueCode, expenditure);
				 	    			}
				 	    		}
				 	    	}
			 	      }
				 }
			}
			
			for (int j = 0; j < rangeArr.length; j++) {
				String[] rangeStr = rangeArr[j].split("-");
				inputVO.setFromRange(Long.valueOf(rangeStr[0]));
				inputVO.setToRange(Long.valueOf(rangeStr[1]));
				inputVO.setpType("TOT");
				
				String str = convertingInputVOToString(inputVO);
				ClientResponse response = webServiceUtilService.callWebService("http://dbtrd.ap.gov.in/NregaDashBoardService/rest/APLabourBudgetPanchayats/APLabourBdgtPanchayats", str);
				if(response.getStatus() != 200){
		 	    	  throw new RuntimeException("Failed : HTTP error code : "+ response.getStatus());
		 	      }else{
		 	    	 String output = response.getEntity(String.class);
			 	    	if(output != null && !output.isEmpty()){
			 	    		JSONArray finalArray = new JSONArray(output);
			 	    		if(finalArray!=null && finalArray.length()>0){
			 	    			for(int k=0;k<finalArray.length();k++){
			 	    				JSONObject jObj = (JSONObject) finalArray.get(k);
			 	    				
			 	    				ComponentTargetConfiguration model = new ComponentTargetConfiguration();
			 	    				model.setNregaComponentId(1L);
			 	    				model.setRegionScopesId(6L);
			 	    				model.setScopeValue(jObj.getString("UNIQUEID"));
			 	    				model.setComponentTargetId(j+1L);
			 	    				model.setTotalExpenditure(Double.valueOf(jObj.getString("TOTALEXPENDITURE")));
			 	    				model.setWage(Double.valueOf(wageExpMap.get(jObj.getString("UNIQUEID"))));
			 	    				model.setMaterial(Double.valueOf(matExpMap.get(jObj.getString("UNIQUEID"))));
			 	    				model.setYear("2017-2018");
			 	    				model.setIsDeleted("N");
			 	    				model.setInsertedTime(new DateUtilService().getCurrentDateAndTime());
			 	    				model = componentTargetConfigurationDAO.save(model);
			 	    			}
			 	    		}
			 	    	}
		 	      }
			 }
			responseVO.setMessage("Success");
			responseVO.setResponseCode("0");
		} catch (Exception e) {
			responseVO.setMessage("Fail");
			responseVO.setResponseCode("1");
			LOG.error(" Exception occured in ConstituencyWiseWorkStatusService ,savingLabourBudgetRangeWiseExpenditureDetails() ",e);
		}
		return responseVO;
	}
	
	public ResponseVO savingLabourBudgetRangeWiseExpenditureDetailsEveryDay(InputVO inputVO){
		ResponseVO responseVO = new ResponseVO();
		try {
			Map<String,String> wageExpMap = new LinkedHashMap<String,String>();
	    	Map<String,String> matExpMap = new LinkedHashMap<String,String>();
	    	
	    	String[] rangeArr = {"0-0","0-1","1-5","5-10","10-20","20-30","30-50","50-100","100-200","200-300","300-400","400-5000"};
			String[] pTypeArr = {"WAGE","MAT"};
			for (int i = 0; i < pTypeArr.length; i++) {
				for (int j = 0; j < rangeArr.length; j++) {
					String[] rangeStr = rangeArr[j].split("-");
					String pType = pTypeArr[i];
					inputVO.setFromRange(Long.valueOf(rangeStr[0]));
					inputVO.setToRange(Long.valueOf(rangeStr[1]));
					inputVO.setpType(pType);
					
					String str = convertingInputVOToString(inputVO);
					ClientResponse response = webServiceUtilService.callWebService("http://dbtrd.ap.gov.in/NregaDashBoardService/rest/APLabourBudgetPanchayats/APLabourBdgtPanchayats", str);
					if(response.getStatus() != 200){
			 	    	  throw new RuntimeException("Failed : HTTP error code : "+ response.getStatus());
			 	      }else{
			 	    	  System.out.println("Completed for "+pType+" - "+rangeArr[j]);
			 	    	 String output = response.getEntity(String.class);
				 	    	if(output != null && !output.isEmpty()){
				 	    		JSONArray finalArray = new JSONArray(output);
				 	    		if(finalArray!=null && finalArray.length()>0){
				 	    			for(int k=0;k<finalArray.length();k++){
				 	    				JSONObject jObj = (JSONObject) finalArray.get(k);
				 	    				String uniqueCode = jObj.getString("UNIQUEID");
				 	    				String expenditure = jObj.getString("TOTALEXPENDITURE");
				 	    				if(i == 0)
				 	    					wageExpMap.put(uniqueCode, expenditure);
				 	    				else
				 	    					matExpMap.put(uniqueCode, expenditure);
				 	    			}
				 	    		}
				 	    	}
			 	      }
				 }
			}
			int updateresult = componentTargetConfigurationTempDAO.updateoldData();
			System.out.println("Updated Count - "+updateresult);
			
			for (int j = 0; j < rangeArr.length; j++) {
				String[] rangeStr = rangeArr[j].split("-");
				inputVO.setFromRange(Long.valueOf(rangeStr[0]));
				inputVO.setToRange(Long.valueOf(rangeStr[1]));
				inputVO.setpType("TOT");
				
				String str = convertingInputVOToString(inputVO);
				ClientResponse response = webServiceUtilService.callWebService("http://dbtrd.ap.gov.in/NregaDashBoardService/rest/APLabourBudgetPanchayats/APLabourBdgtPanchayats", str);
				if(response.getStatus() != 200){
		 	    	  throw new RuntimeException("Failed : HTTP error code : "+ response.getStatus());
		 	      }else{
		 	    	 String output = response.getEntity(String.class);
			 	    	if(output != null && !output.isEmpty()){
			 	    		JSONArray finalArray = new JSONArray(output);
			 	    		if(finalArray!=null && finalArray.length()>0){
			 	    			for(int k=0;k<finalArray.length();k++){
			 	    				JSONObject jObj = (JSONObject) finalArray.get(k);
			 	    				
			 	    				ComponentTargetConfigurationTemp model = new ComponentTargetConfigurationTemp();
			 	    				model.setNregaComponentId(1L);
			 	    				model.setRegionScopesId(6L);
			 	    				model.setScopeValue(jObj.getString("UNIQUEID"));
			 	    				model.setTotalExpenditure(Double.valueOf(jObj.getString("TOTALEXPENDITURE")));
			 	    				model.setWage(Double.valueOf(wageExpMap.get(jObj.getString("UNIQUEID"))));
			 	    				model.setMaterial(Double.valueOf(matExpMap.get(jObj.getString("UNIQUEID"))));
			 	    				model.setYear("2017-2018");
			 	    				model.setIsDeleted("N");
			 	    				model.setInsertedTime(new DateUtilService().getCurrentDateAndTime());
			 	    				model = componentTargetConfigurationTempDAO.save(model);
			 	    			}
			 	    		}
			 	    	}
		 	      }
			 }
			responseVO.setMessage("Success");
			responseVO.setResponseCode("0");
		} catch (Exception e) {
			responseVO.setMessage("Fail");
			responseVO.setResponseCode("1");
			LOG.error(" Exception occured in ConstituencyWiseWorkStatusService ,savingLabourBudgetRangeWiseExpenditureDetails() ",e);
		}
		return responseVO;
	}
	
	public String convertingInputVOToString(InputVO inputVO){
		String str = "";
		try {
			//if(inputVO.getDivType() != null && !inputVO.getDivType().trim().equalsIgnoreCase("MonthWise Expenditure")){
				if(inputVO.getLocationId() != null){
					if(inputVO.getLocationType() != null && inputVO.getLocationType().trim().equalsIgnoreCase("district")){
						if(inputVO.getLocationId().longValue() > 0l && inputVO.getLocationId().longValue() <= 9l)
							inputVO.setLocationIdStr("0"+inputVO.getLocationId().toString());
					}else if(inputVO.getLocationType() != null && inputVO.getLocationType().trim().equalsIgnoreCase("constituency")){
						if(inputVO.getDistrictId().longValue() > 0l && inputVO.getDistrictId().longValue() <= 9l){
							if(inputVO.getLocationId().longValue() > 0l)
								inputVO.setLocationIdStr("0"+inputVO.getLocationId().toString());
						}
					}
				}
			//}
			
				
			str = "{";
			
			if(inputVO.getFromDate() != null )
				str += "\"fromDate\" : \""+inputVO.getFromDate()+"\",";
			if(inputVO.getToDate() != null)
				str += "\"toDate\" : \""+inputVO.getToDate()+"\",";
			if(inputVO.getYear() != null)
				str += "\"year\" : \""+inputVO.getYear()+"\",";
			if(inputVO.getLocationType() != null)
				str += "\"locationType\" : \""+inputVO.getLocationType()+"\",";
			if(inputVO.getLocationIdStr() != null)
				str += "\"locationId\" : \""+inputVO.getLocationIdStr()+"\",";
			else if(inputVO.getLocationId() != null)
				str += "\"locationId\" : \""+inputVO.getLocationId()+"\",";
			if(inputVO.getSublocaType() != null)
				str += "\"SublocationType\" : \""+inputVO.getSublocaType()+"\",";
			if(inputVO.getFromRange() != null)
				str += "\"FromRange\" : \""+inputVO.getFromRange()+"\",";
			if(inputVO.getToRange() != null)
				str += "\"ToRange\" : \""+inputVO.getToRange()+"\",";
			if(inputVO.getType() != null)
				str += "\"type\" : \""+inputVO.getType()+"\",";
			if(inputVO.getProgram() != null)
				str += "\"program\" : \""+inputVO.getProgram()+"\",";
			if(inputVO.getCategory() != null)
				str += "\"categoryName\" : \""+inputVO.getCategory()+"\",";
			if(inputVO.getGroupName() != null)
				str += "\"groupName\" : \""+inputVO.getGroupName()+"\",";
			if(inputVO.getMonth() != null)
				str += "\"month\" : \""+inputVO.getMonth()+"\",";
			if(inputVO.getpType() != null)
				str += "\"pType\" : \""+inputVO.getpType()+"\",";
			
			if(str.length() > 1)
				str = str.substring(0,str.length()-1);
			
			str += "}";
			
		} catch (Exception e) {
			LOG.error("Exception raised at convertingInputVOToString - ConstituencyWiseWorkStatusService service", e);
		}
		return str;
	}
	
	public NregsOverviewVO getComponentWiseOverview(InputVO inputVO,String locationId,Long levelId){
		NregsOverviewVO returnvo = new NregsOverviewVO();
		try {
			if(levelId != null && levelId.longValue() > 0L && levelId.longValue() == 1L){
				inputVO.setLocationType("state");
			}else if(levelId != null && levelId.longValue() > 0L && levelId.longValue() == 2L){
				inputVO.setLocationType("district");
			}else if(levelId != null && levelId.longValue() > 0L && levelId.longValue() == 3L){
				inputVO.setLocationType("constituency");
			}
			if(locationId != null){
				inputVO.setLocationIdStr(locationId);
			}
			
			String webServiceUrl = null;
			if(inputVO.getDivType() != null && inputVO.getDivType().trim().toString().equalsIgnoreCase("Farm Ponds"))
				webServiceUrl = "http://dbtrd.ap.gov.in/NregaDashBoardService/rest/FarmPondService_new/FarmPondOverview_new";
			else if(inputVO.getDivType() != null && inputVO.getDivType().trim().toString().equalsIgnoreCase("IHHL"))
				webServiceUrl = "http://dbtrd.ap.gov.in/NregaDashBoardService/rest/IHHLService_new/IHHLOverview_new";
			else if(inputVO.getDivType() != null && inputVO.getDivType().trim().toString().equalsIgnoreCase("Vermi Compost"))
				webServiceUrl = "http://dbtrd.ap.gov.in/NregaDashBoardService/rest/VermiService_new/VermiOverview_new";
			else if(inputVO.getDivType() != null && inputVO.getDivType().trim().toString().equalsIgnoreCase("Solid Waste Management"))
				webServiceUrl = "http://dbtrd.ap.gov.in/NregaDashBoardService/rest/SolidWasteManagementServices/SolidWasteManagementOverview";
			else if(inputVO.getDivType() != null && inputVO.getDivType().trim().toString().equalsIgnoreCase("Play Fields"))
				webServiceUrl = "http://dbtrd.ap.gov.in/NregaDashBoardService/rest/PlayFieldsServices/PlayFieldsOverview";
			else if(inputVO.getDivType() != null && inputVO.getDivType().trim().toString().equalsIgnoreCase("Burial Grounds"))
				webServiceUrl = "http://dbtrd.ap.gov.in/NregaDashBoardService/rest/BurialGroundsServices/BurialGroundsOverview";
			else if(inputVO.getDivType() != null && inputVO.getDivType().trim().toString().equalsIgnoreCase("Timely Payment"))
				webServiceUrl = "http://dbtrd.ap.gov.in/NregaDashBoardService/rest/TimePaymentsServicesNew/TimePaymentsOverviewNew";
			
			String str = convertingInputVOToString(inputVO);
			
			ClientResponse response = webServiceUtilService.callWebService(webServiceUrl.toString(), str);
	        
	        if(response.getStatus() != 200){
	 	    	  throw new RuntimeException("Failed : HTTP error code : "+ response.getStatus());
	 	      }else{
	 	    	 String output = response.getEntity(String.class);
	 	    	 
	 	    	 Map<String,String> mandPercMap = new LinkedHashMap<String,String>();
	 	    	 List<Object[]> previousList = componentWiseAchievementConfigurationDAO.getComponentWiseMandalAchievementPercentage(inputVO.getDivType(), inputVO.getLocationType(), inputVO.getLocationIdStr());
	 	    	 List<Object[]> presentList = componentWiseAchievementConfigurationTempDAO.getComponentWiseMandalAchievementPercentage(inputVO.getDivType());
	 	    	 
	 	    	if(output != null && !output.isEmpty()){
	 	    		JSONObject Obj = new JSONObject(output);
	 	    		if(Obj!=null && Obj.length()>0){
	 	    			returnvo.setDistrictsInRed(Obj.getLong("DISTRICTSINRED"));
	 	    			returnvo.setDistrictsInOrange(Obj.getLong("DISTRICTSINORANGE"));
	 	    			returnvo.setDistrictsInGreen(Obj.getLong("DISTRICTSINGREEN"));
	 	    			returnvo.setTotalDistricts(Obj.getLong("TOTALDISTRICTS"));
 	    				returnvo.setConstituenciesInRed(Obj.getLong("CONSTITUENCIESINRED"));
 	    				returnvo.setConstituenciesInOrange(Obj.getLong("CONSTITUENCIESINORANGE"));
 	    				returnvo.setConstituenciesInGreen(Obj.getLong("CONSTITUENCIESINGREEN"));
 	    				returnvo.setTotalConstituencies(Obj.getLong("TOTALCONSTITUENCIES"));
 	    				returnvo.setMandalsInRed(Obj.getLong("MANDALSINRED"));
 	    				returnvo.setMandalsInOrange(Obj.getLong("MANDALSINORANGE"));
 	    				returnvo.setMandalsInGreen(Obj.getLong("MANDALSINGREEN"));
 	    				returnvo.setTotalMandals(Obj.getLong("TOTALMANDALS"));
 	    				returnvo.setVillagesInRed(Obj.getLong("VILLAGESINRED"));
 	    				returnvo.setVillagesInOrange(Obj.getLong("VILLAGESINORANGE"));
 	    				returnvo.setVillagesInGreen(Obj.getLong("VILLAGESINGREEN"));
 	    				returnvo.setTotalVillages(Obj.getLong("TOTALVILLAGES"));
 	    				if(Obj.has("DISTRICTSINGOLD")){
 	    					returnvo.setDistrictsInGold(Obj.getLong("DISTRICTSINGOLD"));
 	    					returnvo.setConstituenciesInGold(Obj.getLong("CONSTITUENCIESINGOLD"));
 	    					returnvo.setMandalsInGold(Obj.getLong("MANDALSINGOLD"));
 	    					returnvo.setVillagesInGold(Obj.getLong("VILLAGESINGOLD"));
 	    				}
	 	    		}
	 	    	}
	 	    	
	 	    	if(previousList != null && !previousList.isEmpty()){
	 	    		for (Object[] obj : previousList) {
						String mandalStr = obj[0] != null ? obj[0].toString():"0";
						String percValue = obj[1] != null ? obj[1].toString():"0";
						if(Double.valueOf(percValue)  < 60){
    						returnvo.setPreviousRedMandals(returnvo.getPreviousRedMandals()+1L);
    						returnvo.getPreviousRedList().add(mandalStr);
    					}else if(Double.valueOf(percValue)  >=60 && Double.valueOf(percValue) <90){
    						returnvo.setPreviousOrangeMandals(returnvo.getPreviousOrangeMandals()+1L);
    						returnvo.getPreviousOrangeList().add(mandalStr);
						}else if(Double.valueOf(percValue)  >=90 && Double.valueOf(percValue) <100){
    						returnvo.setPreviousGreenMandals(returnvo.getPreviousGreenMandals()+1L);
    						returnvo.getPreviousGreenList().add(mandalStr);
    					}else if(Double.valueOf(percValue)  >=100){
    						returnvo.setPreviousGoldMandals(returnvo.getPreviousGoldMandals()+1L);
    						returnvo.getPreviousGoldList().add(mandalStr);
    					}
					}
	 	    	}
	 	    	
	 	    	if(presentList != null && !presentList.isEmpty()){
	 	    		for (Object[] obj : presentList) {
	 	    			String mandalStr = obj[0] != null ? obj[0].toString():"0";
						String percValue = obj[1] != null ? obj[1].toString():"0";
						mandPercMap.put(mandalStr, percValue);
					}
	 	    	}
	 	    	
	 	    	String[] colorsArr = {"Red","Orange","Green","Gold"};
	 	    	for (int i = 0; i < colorsArr.length; i++) {
	 	    		NregsOverviewVO vo = new NregsOverviewVO();
	 	    		vo.setName(colorsArr[i].toString());
	 	    		List<String> mandalsList = null;
	 	    		if(colorsArr[i].equalsIgnoreCase("Red")){
	 	    			mandalsList = returnvo.getPreviousRedList();
	 	    			vo.setPreviousCount(returnvo.getPreviousRedMandals());
	 	    			vo.setPresentCount(returnvo.getMandalsInRed());
	 	    		}else if(colorsArr[i].equalsIgnoreCase("Orange")){
	 	    			mandalsList = returnvo.getPreviousOrangeList();
	 	    			vo.setPreviousCount(returnvo.getPreviousOrangeMandals());
	 	    			vo.setPresentCount(returnvo.getMandalsInOrange());
	 	    		}else if(colorsArr[i].equalsIgnoreCase("Green")){
	 	    			mandalsList = returnvo.getPreviousGreenList();
	 	    			vo.setPreviousCount(returnvo.getPreviousGreenMandals());
	 	    			vo.setPresentCount(returnvo.getMandalsInGreen());
	 	    		}else if(colorsArr[i].equalsIgnoreCase("Gold")){
	 	    			mandalsList = returnvo.getPreviousGoldList();
	 	    			vo.setPreviousCount(returnvo.getPreviousGoldMandals());
	 	    			vo.setPresentCount(returnvo.getMandalsInGold());
	 	    		}
	 	    		if(mandalsList != null && !mandalsList.isEmpty()){
		 	    		for (String mandlStr : mandalsList) {
							 String percValue = mandPercMap.get(mandlStr);
							 if(Double.valueOf(percValue)  < 60)
								 vo.setMandalsInRed(vo.getMandalsInRed()+1L);
	    					 else if(Double.valueOf(percValue)  >=60 && Double.valueOf(percValue) <90)
	    						 vo.setMandalsInOrange(vo.getMandalsInOrange()+1L);
	    					 else if(Double.valueOf(percValue)  >=90 && Double.valueOf(percValue) <100)
	    						 vo.setMandalsInGreen(vo.getMandalsInGreen()+1L);
	    					 else if(Double.valueOf(percValue)  >=100)
	    						 vo.setMandalsInGold(vo.getMandalsInGold()+1L);
	    				}
		 	    	}
	 	    		returnvo.getSubList().add(vo);
				}
	 	    	
	 	    	if(returnvo.getSubList() != null && !returnvo.getSubList().isEmpty()){
	 	    		for (NregsOverviewVO vo : returnvo.getSubList()) {
						if(vo.getName().equalsIgnoreCase("Red")){
							vo.setChangedCount(vo.getPreviousCount() - vo.getMandalsInRed());
							vo.setTotalMandals(vo.getMandalsInRed());
							vo.setMandalsInRed(0L);
						}else if(vo.getName().equalsIgnoreCase("Orange")){
							vo.setChangedCount(vo.getPreviousCount() - vo.getMandalsInOrange());
							vo.setTotalMandals(vo.getMandalsInOrange());
							vo.setMandalsInOrange(0L);
						}else if(vo.getName().equalsIgnoreCase("Green")){
							vo.setChangedCount(vo.getPreviousCount() - vo.getMandalsInGreen());
							vo.setTotalMandals(vo.getMandalsInGreen());
							vo.setMandalsInGreen(0L);
						}else if(vo.getName().equalsIgnoreCase("Gold")){
							vo.setChangedCount(vo.getPreviousCount() - vo.getMandalsInGold());
							vo.setTotalMandals(vo.getMandalsInGold());
							vo.setMandalsInGold(0L);
						}
					}
	 	    	}
	 	    }
		} catch (Exception e) {
			LOG.error("Exception raised at getComponentWiseOverview - ConstituencyWiseWorkStatusService service", e);
		}
		return returnvo;
	}
	
	public List<NregsDataVO> getComponentWiseLocationData(InputVO inputVO,String locationId,Long levelId){
		List<NregsDataVO> returnList = new ArrayList<NregsDataVO>(0);
		try {
			if(levelId != null && levelId.longValue() > 0L && levelId.longValue() == 1L){
				inputVO.setLocationType("state");
			}else if(levelId != null && levelId.longValue() > 0L && levelId.longValue() == 2L){
				inputVO.setLocationType("district");
			}else if(levelId != null && levelId.longValue() > 0L && levelId.longValue() == 3L){
				inputVO.setLocationType("constituency");
			}
			if(locationId != null){
				inputVO.setLocationIdStr(locationId);
			}
			
			String webServiceUrl = null;
			if(inputVO.getDivType() != null && inputVO.getDivType().trim().toString().equalsIgnoreCase("Farm Ponds"))
				webServiceUrl = "http://dbtrd.ap.gov.in/NregaDashBoardService/rest/FarmPondService_new/FarmPondData_new";
			else if(inputVO.getDivType() != null && inputVO.getDivType().trim().toString().equalsIgnoreCase("IHHL"))
				webServiceUrl = "http://dbtrd.ap.gov.in/NregaDashBoardService/rest/IHHLService_new/IHHLData_new";
			else if(inputVO.getDivType() != null && inputVO.getDivType().trim().toString().equalsIgnoreCase("Vermi Compost"))
				webServiceUrl = "http://dbtrd.ap.gov.in/NregaDashBoardService/rest/VermiService_new/VermiData_new";
			else if(inputVO.getDivType() != null && inputVO.getDivType().trim().toString().equalsIgnoreCase("Solid Waste Management"))
				webServiceUrl = "http://dbtrd.ap.gov.in/NregaDashBoardService/rest/SolidWasteManagementServices/SolidWasteManagementData";
			else if(inputVO.getDivType() != null && inputVO.getDivType().trim().toString().equalsIgnoreCase("Play Fields"))
				webServiceUrl = "http://dbtrd.ap.gov.in/NregaDashBoardService/rest/PlayFieldsServices/PlayFieldsData";
			else if(inputVO.getDivType() != null && inputVO.getDivType().trim().toString().equalsIgnoreCase("Burial Grounds"))
				webServiceUrl = "http://dbtrd.ap.gov.in/NregaDashBoardService/rest/BurialGroundsServices/BurialGroundsData";
			else if(inputVO.getDivType() != null && inputVO.getDivType().trim().toString().equalsIgnoreCase("Timely Payment"))
				webServiceUrl = "http://dbtrd.ap.gov.in/NregaDashBoardService/rest/TimePaymentsServicesNew/TimePaymentsDataNew";
			
			String str = convertingInputVOToString(inputVO);
			
			ClientResponse response = webServiceUtilService.callWebService(webServiceUrl.toString(), str);
	        
	        if(response.getStatus() != 200){
	 	    	  throw new RuntimeException("Failed : HTTP error code : "+ response.getStatus());
	 	      }else{
	 	    	 String output = response.getEntity(String.class);
	 	    	 
	 	    	if(output != null && !output.isEmpty()){
	 	    		JSONArray finalArray = new JSONArray(output);
	 	    		if(finalArray!=null && finalArray.length()>0){
	 	    			if(inputVO.getDivType() != null && inputVO.getDivType().trim().equalsIgnoreCase("Timely Payment")){
	 	    				for(int i=0;i<finalArray.length();i++){
	 	    					NregsDataVO vo = new NregsDataVO();
		 	    				JSONObject jObj = (JSONObject) finalArray.get(i);
		 	    				vo.setUniqueCode(jObj.getString("UNIQUEID"));
		 	    				vo.setDistrict(jObj.getString("DISTRICT"));
		 	    				vo.setConstituency(jObj.getString("CONSTITUENCY"));
		 	    				vo.setMandal(jObj.getString("MANDAL"));
		 	    				vo.setPanchayat(jObj.getString("PANCHAYAT"));
		 	    				vo.setTarget(jObj.getLong("TARGET"));
		 	    				vo.setAchivement(jObj.getString("ACHIVEMENT"));
		 	    				vo.setPercentage(new BigDecimal(jObj.getString("PERCENTAGE")).setScale(2, BigDecimal.ROUND_HALF_UP).toString());
		 	    				if(Double.valueOf(vo.getPercentage())  < 60)
		 	    					returnList.add(vo);
	 	    				}
	 	    			}else{
	 	    				for(int i=0;i<finalArray.length();i++){
	 	    					NregsDataVO vo = new NregsDataVO();
		 	    				JSONObject jObj = (JSONObject) finalArray.get(i);
		 	    				vo.setUniqueCode(jObj.getString("UNIQUEID"));
		 	    				vo.setDistrict(jObj.getString("DISTRICT"));
		 	    				vo.setConstituency(jObj.getString("CONSTITUENCY"));
		 	    				vo.setMandal(jObj.getString("MANDAL"));
		 	    				vo.setPanchayat(jObj.getString("PANCHAYAT"));
		 	    				vo.setTarget(jObj.getLong("TARGET"));
		 	    				vo.setGrounded(jObj.getString("GROUNDED"));
		 	    				if(jObj.getString("NOTGROUNDED").trim().contains("-"))
		 	    					vo.setNotGrounded("0");
		 	    				else
		 	    					vo.setNotGrounded(jObj.getString("NOTGROUNDED"));
		 	    				vo.setInProgress(jObj.getLong("INPROGRESS"));
		 	    				vo.setCompleted(jObj.getLong("COMPLETED"));
		 	    				vo.setPercentage(jObj.getString("PERCENTAGE"));
		 	    				vo.setWageExpenditure(jObj.getString("WAGE_EXP"));
	 	    					vo.setMaterialExpenditure(jObj.getString("MAT_EXP"));
	 	    					vo.setTotalExpenditure(jObj.getString("TOT_EXP"));
	 	    					if(Double.valueOf(vo.getPercentage())  < 60)
	 	    						returnList.add(vo);
	 	    				}
	 	    			}
	 	    		}
	 	    	}
	 	    	
	 	    	List<Object[]> nregaComments= nregaComponentCommentsDAO.getNregaComponentCommentsByComponent(inputVO.getDivType());
				if(nregaComments != null && nregaComments.size()>0){
					for(Object[] param : nregaComments){
						NregsDataVO matchedVo= getMatchedVoForUniqueCode(returnList,commonMethodsUtilService.getStringValueForObject(param[3]));
						if(matchedVo != null){
							matchedVo.setStatus(commonMethodsUtilService.getStringValueForObject(param[0]));
							matchedVo.setComments(commonMethodsUtilService.getStringValueForObject(param[1]));
							matchedVo.setActionPlan(commonMethodsUtilService.getStringValueForObject(param[2]));
							matchedVo.setStatusId(commonMethodsUtilService.getLongValueForObject(param[4]));
							matchedVo.setComponentId(commonMethodsUtilService.getLongValueForObject(param[5]));
						}
					}
				}
	 	     }
		} catch (Exception e) {
			LOG.error("Exception raised at getComponentWiseLocationData - ConstituencyWiseWorkStatusService service", e);
		}
		return returnList;
	}
	
	public NregsDataVO getMatchedVoForUniqueCode(List<NregsDataVO> list,String uniqueCode){
		try{
			if(commonMethodsUtilService.isListOrSetValid(list)){
				for (NregsDataVO nregaPaymentsVO : list) {
					if(nregaPaymentsVO.getUniqueCode().equalsIgnoreCase(uniqueCode)){
						return nregaPaymentsVO;
					}
				}
			}
			
		}catch(Exception e){
			LOG.error("Exception raised at getMatchedVo - NREGSTCSService service", e);
		}
		return null;
	}
}
