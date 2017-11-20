package com.itgrids.service.impl;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.itgrids.dao.IAssemblyMlaDAO;
import com.itgrids.dao.IConstituencyDAO;
import com.itgrids.dao.IDepartmentDAO;
import com.itgrids.dao.IFundSanctionDAO;
import com.itgrids.dao.INregaWorkExpenditureLocationDAO;
import com.itgrids.dto.InputVO;
import com.itgrids.dto.LocationVO;
import com.itgrids.dto.NregsFmsWorksVO;
import com.itgrids.service.IConstituencyWiseWorkStatusService;
import com.itgrids.utils.CommonMethodsUtilService;
import com.itgrids.utils.IConstants;

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
	
	/*
	 * Swadhin K Lenka
	 * @see com.itgrids.service.IConstituencyWiseWorkStatusService#getFundManagementSystemWorkDetails(java.util.List, java.lang.Long, java.lang.String, java.lang.String)
	 */
	public LocationVO getFundManagementSystemWorkDetails(List<Long> financialYearIdsList, List<Long> departmentIdList, String startDateStr,String endDateStr,Long locationId){
		try{
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
			
			if(departmentIdList.size() == 0){
				List<LocationVO> overviewList = new ArrayList<LocationVO>();
				LocationVO locationVO2 = null;
				Map<Long,Map<Long,Long>> deptIdAndGrandTypeIdAndWorkCount = new HashMap<Long,Map<Long,Long>>();
				Map<Long,Map<Long,Long>> deptIdAndGrandTypeIdAndAmount = new HashMap<Long,Map<Long,Long>>();
				Map<Long,String> deptIdAndNameMap = new HashMap<Long,String>();
				
				List<Object[]> worksSummery = fundSanctionDAO.getFundSanstionLocationWise(financialYearIdsList,departmentIdList,startDate,endDate,superLocationId,type);
				buildDeptWiseMap(worksSummery,deptIdAndGrandTypeIdAndWorkCount,deptIdAndGrandTypeIdAndAmount,deptIdAndNameMap);
				for(Entry<Long,Map<Long,Long>> param : deptIdAndGrandTypeIdAndWorkCount.entrySet()){
					locationVO2 = new LocationVO();
					locationVO.setPlainWorkCount(0L);
					locationVO.setScpWorkCount(0L);
					locationVO.setTspWorkCount(0L);
					
					locationVO2.setDepartmentName(deptIdAndNameMap.get(param.getKey()));
					locationVO2.setPlainWorkCount(commonMethodsUtilService.getLongValueForObject(param.getValue().get(1L)));
					locationVO2.setScpWorkCount(commonMethodsUtilService.getLongValueForObject(param.getValue().get(2L)));
					locationVO2.setTspWorkCount(commonMethodsUtilService.getLongValueForObject(param.getValue().get(3L)));
					locationVO2.setPlainAmountInDecimal(df.format(commonMethodsUtilService.getLongValueForObject(deptIdAndGrandTypeIdAndAmount.get(param.getKey()).get(1L))/100000D));
					locationVO2.setScpAmountInDecimal(df.format(commonMethodsUtilService.getLongValueForObject(deptIdAndGrandTypeIdAndAmount.get(param.getKey()).get(2L))/100000D));
					locationVO2.setTspAmountInDecimal(df.format(commonMethodsUtilService.getLongValueForObject(deptIdAndGrandTypeIdAndAmount.get(param.getKey()).get(3L))/100000D));
					locationVO2.setWorkNumber((locationVO2.getPlainWorkCount() + locationVO2.getScpWorkCount() + locationVO2.getTspWorkCount()));
					locationVO2.setAmount(commonMethodsUtilService.getLongValueForObject(deptIdAndGrandTypeIdAndAmount.get(param.getKey()).get(1L))+commonMethodsUtilService.getLongValueForObject(deptIdAndGrandTypeIdAndAmount.get(param.getKey()).get(2L))+commonMethodsUtilService.getLongValueForObject(deptIdAndGrandTypeIdAndAmount.get(param.getKey()).get(3L)));
					locationVO2.setAmountInDecimal(df.format(locationVO2.getAmount()/100000D));
					overviewList.add(locationVO2);
					
				}
				//call one more service for mgnrgs
				InputVO inputVO = new InputVO();
				inputVO.setFinancialYrIdList(financialYearIdsList);
				inputVO.setConstituencyId(inputVO.getLocationId());
				NregsFmsWorksVO fmsWorksVO = getConstituencyWiseNregsWorksOverview(inputVO);
				locationVO2 = new LocationVO();
				locationVO2.setDepartmentName("Mahatma Gandhi National Rural Employment Gurantee Scheme");
				locationVO2.setWorkNumber(fmsWorksVO.getFinalWorks());
				locationVO2.setAmountInDecimal(df.format(fmsWorksVO.getTotalAmount()/100000D));
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
					locationVOForListForWork.setAmountInDecimal(df.format(commonMethodsUtilService.getLongValueForObject(param[11])/100000D));
					locationVOs.add(locationVOForListForWork);   
				}
				
				List<LocationVO> locationVOListForGoNo = new ArrayList<LocationVO>(govtOrderMap.values());
				
				if(locationVOListForGoNo != null && locationVOListForGoNo.size() > 0){
					for(LocationVO param : locationVOListForGoNo){
						if(param.getGrantTypeId().longValue() == 1L){
							locationVO.setPlainGoCount(locationVO.getPlainGoCount()+1L);
							locationVO.setPlainWorkCount(locationVO.getPlainWorkCount() + param.getWorkNumber());
							locationVO.setPlainAmount(locationVO.getPlainAmount() + param.getAmount());
						}else if(param.getGrantTypeId().longValue() == 2L){
							locationVO.setScpGoCount(locationVO.getScpGoCount()+1L);
							locationVO.setScpWorkCount(locationVO.getScpWorkCount() + param.getWorkNumber());
							locationVO.setScpAmount(locationVO.getScpAmount() + param.getAmount());
						}else if(param.getGrantTypeId().longValue() == 3L){
							locationVO.setTspGoCount(locationVO.getTspGoCount()+1L);
							locationVO.setTspWorkCount(locationVO.getTspWorkCount() + param.getWorkNumber());
							locationVO.setTspAmount(locationVO.getTspAmount() + param.getAmount());
						}
					}
				}
				
				//finally convert the amount into lakh
				locationVO.setPlainAmountInDecimal(df.format(locationVO.getPlainAmount()/100000D));
				locationVO.setScpAmountInDecimal(df.format(locationVO.getScpAmount()/100000D));
				locationVO.setTspAmountInDecimal(df.format(locationVO.getTspAmount()/100000D));
				
				Map<Long,List<LocationVO>> programIdAndListOfGo = new HashMap<Long,List<LocationVO>>();
				List<LocationVO> goOrderVoList = null;
				if(locationVOListForGoNo != null && locationVOListForGoNo.size() > 0){
					for(LocationVO param : locationVOListForGoNo){
						param.setAmountInDecimal(df.format(param.getAmount()/100000D));
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
				
				locationVO.setGovtOrderCount(locationVO.getPlainGoCount()+locationVO.getScpGoCount()+locationVO.getTspGoCount());
				locationVO.setWorkNumber(locationVO.getPlainWorkCount()+locationVO.getScpWorkCount()+locationVO.getTspWorkCount());
				locationVO.setAmount(locationVO.getPlainAmount()+locationVO.getScpAmount()+locationVO.getTspAmount());
				locationVO.setAmountInDecimal(df.format(locationVO.getAmount()/100000D));
				
				locationVO.getLocationList1().addAll(programWiseList);
				//locationVO.getLocationList2().addAll(locationVOs);
			}	
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
			//financialYearIdsList = commonMethodsUtilService.makeEmptyListByZeroValue(inputVO.getFinancialYrIdList());
			List<Long> financialYearIds = new ArrayList<Long>(0);
			Long[] finanArr = IConstants.PRESENT_FINANCIAL_YEAR_IDS;
			if(finanArr != null && finanArr.length > 0){
				for (int i = 0; i < finanArr.length; i++) {
					financialYearIds.add(finanArr[i]);
				}
			}
			
			List<Object[]> list = nregaWorkExpenditureLocationDAO.getWorkWiseExpenditureOverviewInConstituency(inputVO.getConstituencyId(), financialYearIds);
			if(list != null && !list.isEmpty()){
				for (Object[] obj : list) {
					returnvo.setFinalWorks(Long.valueOf(obj[3] != null ? obj[3].toString():"0"));
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
			List<Object[]> list = nregaWorkExpenditureLocationDAO.getWorkWiseExpenditureDetailsInConstituency(inputVO.getConstituencyId(), financialYearIds);
			if(list != null && !list.isEmpty()){
				for (Object[] obj : list) {
					Long totalAmount = Long.valueOf(obj[5] != null ? obj[5].toString():"0");
					String workName = obj[1] != null ? obj[1].toString():"0";
					NregsFmsWorksVO vo = null;
					if(totalAmount != null && totalAmount.longValue() >= 20000000L){
						vo = new NregsFmsWorksVO();
						vo.setId(Long.valueOf(obj[0] != null ? obj[0].toString():"0"));
						vo.setWorkName(workName);
						vo.setWage(obj[3] != null ? obj[3].toString():"0");
						vo.setMaterial(obj[4] != null ? obj[4].toString():"0");
						vo.setTotal(obj[5] != null ? obj[5].toString():"0");
						vo.setGrounded(Long.valueOf(obj[6] != null ? obj[6].toString():"0"));
						vo.setInProgress(Long.valueOf(obj[7] != null ? obj[7].toString():"0"));
						vo.setCompleted(Long.valueOf(obj[8] != null ? obj[8].toString():"0"));
						totalWorks = totalWorks+vo.getCompleted();
						returnList.add(vo);
					}else{
						vo = getMatchedVO(returnList, "Other Works");
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
							totalWorks = totalWorks+vo.getCompleted();
							returnList.add(vo);
						}
						else{
							vo.setWage(String.valueOf(Long.valueOf(vo.getWage())+Long.valueOf(obj[3] != null ? obj[3].toString():"0")));
							vo.setMaterial(String.valueOf(Long.valueOf(vo.getMaterial())+Long.valueOf(obj[4] != null ? obj[4].toString():"0")));
							vo.setTotal(String.valueOf(Long.valueOf(vo.getTotal())+Long.valueOf(obj[5] != null ? obj[5].toString():"0")));
							vo.setGrounded(vo.getGrounded()+Long.valueOf(obj[6] != null ? obj[6].toString():"0"));
							vo.setInProgress(vo.getInProgress()+Long.valueOf(obj[7] != null ? obj[7].toString():"0"));
							vo.setCompleted(vo.getCompleted()+Long.valueOf(obj[8] != null ? obj[8].toString():"0"));
							totalWorks = totalWorks+Long.valueOf(obj[8] != null ? obj[8].toString():"0");
						}
					}
					finalAmount = finalAmount+totalAmount;
				}
			}
			
			Collections.sort(returnList, idWiseAscendingOrder);
			
			if(returnList != null && !returnList.isEmpty()){
				returnList.get(0).setFinalWorks(totalWorks);
				returnList.get(0).setFinalAmount(convertRupeesIntoCrores(finalAmount.toString()));
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
	
	public static Comparator<NregsFmsWorksVO> idWiseAscendingOrder = new Comparator<NregsFmsWorksVO>() {
		public int compare(NregsFmsWorksVO o1, NregsFmsWorksVO o2) {
			if(o1.getId() != null && o2.getId() != null){
				return o1.getId().compareTo(o2.getId());
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
				returnVal = returnVal+" Cr";
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
	public void buildDeptWiseMap(List<Object[]> worksSummery,Map<Long,Map<Long,Long>> deptIdAndGrandTypeIdAndWorkCount,Map<Long,Map<Long,Long>> deptIdAndGrandTypeIdAndAmount,Map<Long,String> grantTypeAndNameMap){
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
					
					grantTypeAndNameMap.put(commonMethodsUtilService.getLongValueForObject(param[0]), commonMethodsUtilService.getStringValueForObject(param[1]));
				}
			}
		}catch(Exception e){
			 LOG.error(" Exception occured in FundManagementDashboardService ,buildDeptWiseMap() ",e);
		}
	}
}
