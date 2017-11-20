package com.itgrids.service.impl;

import java.text.DecimalFormat;
import java.util.ArrayList;
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
	@Autowired
	private IDepartmentDAO departmentDAO;
	
	/*
	 * Swadhin K Lenka
	 * @see com.itgrids.service.IConstituencyWiseWorkStatusService#getFundManagementSystemWorkDetails(java.util.List, java.lang.Long, java.lang.String, java.lang.String)
	 */
	public LocationVO getFundManagementSystemWorkDetails(List<Long> financialYearIdsList, Long departmentId, String startDateStr,String endDateStr,Long locationId){
		try{
			LocationVO locationVO = new LocationVO();
			Date startDate = commonMethodsUtilService.stringTODateConvertion(startDateStr,"dd/MM/yyyy","");
			Date endDate = commonMethodsUtilService.stringTODateConvertion(endDateStr,"dd/MM/yyyy","");
			financialYearIdsList = commonMethodsUtilService.makeEmptyListByZeroValue(financialYearIdsList);
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
			List<Object[]> workList = fundSanctionDAO.getFundManagementSystemWorkDetails(financialYearIdsList,departmentId,startDate,endDate,superLocationId,type);
			//create a map of govtOrderId and govtOrderDtlsVO map
			Map<Long, LocationVO> govtOrderMap = new HashMap<Long,LocationVO>();
			LocationVO  locationVOForMap = null;
			//create a list of workDtlsVO
			List<LocationVO> locationVOs = new ArrayList<LocationVO>();
			LocationVO  locationVOForListForWork = null;
			
			DecimalFormat df = new DecimalFormat("0.000");
			
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
					locationVOForMap.setGoNoDate(commonMethodsUtilService.getStringValueForObject(param[9]));
					locationVOForMap.setIssueDate(commonMethodsUtilService.getStringValueForObject(param[10]));
					locationVOForMap.setWorkName(commonMethodsUtilService.getStringValueForObject(param[12]));
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
				locationVO.setScpAmountInDecimal(df.format(locationVO.getScpWorkCount()/100000D));
				locationVO.setTspAmountInDecimal(df.format(locationVO.getTspAmount()/100000D));
				
				Map<Long,List<LocationVO>> programIdAndListOfGo = new HashMap<Long,List<LocationVO>>();
				List<LocationVO> goOrderVoList = null;
				if(locationVOListForGoNo != null && locationVOListForGoNo.size() > 0){
					for(LocationVO param : locationVOListForGoNo){
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
				locationVO.getLocationList2().addAll(locationVOs);
			}	
			return locationVO;
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
}
