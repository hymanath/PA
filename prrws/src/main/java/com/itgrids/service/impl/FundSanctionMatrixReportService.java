package com.itgrids.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.itgrids.dao.IConstituencyDAO;
import com.itgrids.dao.IDistrictDAO;
import com.itgrids.dao.IFundSanctionDAO;
import com.itgrids.dao.IFundSanctionMatrixDetailsDAO;
import com.itgrids.dao.IFundSanctionMatrixRangeDAO;
import com.itgrids.dao.IGrantTypeDAO;
import com.itgrids.dao.impl.ConstituencyDAO;
import com.itgrids.dto.MatrixRangeVO;
import com.itgrids.model.FundSanctionMatrixDetails;
import com.itgrids.model.FundSanctionMatrixRange;
import com.itgrids.service.IFundSanctionMatrixReportService;
import com.itgrids.utils.CommonMethodsUtilService;
import com.itgrids.utils.DateUtilService;
import com.itgrids.utils.IConstants;
import com.itgrids.utils.SetterAndGetterUtilService;

@Service
public class FundSanctionMatrixReportService implements IFundSanctionMatrixReportService{
private static final Logger LOG = Logger.getLogger(FundSanctionMatrixReportService.class);
	
	@Autowired
	private IFundSanctionDAO fundSanctionDAO;
	@Autowired
	private CommonMethodsUtilService commonMethodsUtilService;
	@Autowired
	private IGrantTypeDAO grantTypeDAO;
	@Autowired
	private SetterAndGetterUtilService setterAndGetterUtilService;
	@Autowired
	private IFundSanctionMatrixRangeDAO fundSanctionMatrixRangeDAO;
	@Autowired
	private IFundSanctionMatrixDetailsDAO fundSanctionMatrixDetailsDAO;
	@Autowired
	private IConstituencyDAO constituencyDAO;
	@Autowired
	private IDistrictDAO districtDAO;
	/**
	 * @author Srishailam Pittala
	 * @Date 7th June,2017
	 * @description to get financial years wise matrix report 
	 */
	@SuppressWarnings("static-access")
	@Transactional
	public void calculateFinancialWiseMatrxReportDetail(List<Long> financialYearIdsListList ,List<Long> deptIdsList,
			List<Long> sourceIdsList,List<Long> schemeIdsList,Long searchScopeId){
		try {
			List<Long> presentFinancialYearIdsListmList = new ArrayList<Long>(0);
			List<Long> previousFinancialYearIdsList = new ArrayList<Long>(0);

			Long maxYearId=0L;
			for (Long yearId : financialYearIdsListList) {
				if(yearId.longValue()>maxYearId)
					maxYearId = yearId;
			}
			for (Long yearId : financialYearIdsListList) {
				if(yearId.longValue() == maxYearId.longValue()){
					presentFinancialYearIdsListmList.add(maxYearId);
				}
				else
					previousFinancialYearIdsList.add(yearId);
			}
			
			List<Long> totalLocatinIdsList = null;
			if(searchScopeId.longValue() == IConstants.CONSTITUENCY_LEVEL_SCOPE_ID)
				totalLocatinIdsList=constituencyDAO.getConstituencyList(IConstants.TOTAL_AP_DISTRICT_IDS);
			else if(searchScopeId.longValue() == IConstants.DISTRICT_LEVEL_SCOPE_ID)
				totalLocatinIdsList=districtDAO.getDistrictIdDetailsByDistrictIds(IConstants.TOTAL_AP_DISTRICT_IDS);
			
			List<Object[]> result =  fundSanctionDAO.getFinancialYearWiseFundDetails(financialYearIdsListList,deptIdsList,sourceIdsList,schemeIdsList,searchScopeId);
			Map<String,List<MatrixRangeVO>> rangeWiseAreasMap = new HashMap<String,List<MatrixRangeVO>>(0);

			double maxAmount =0.00d;
			if(commonMethodsUtilService.isListOrSetValid(result)){
				try {
					fundSanctionMatrixDetailsDAO.deleteAllRecordByScopeId(searchScopeId);
					fundSanctionMatrixRangeDAO.deleteAllRecordByScopeId(searchScopeId);
				} catch (Exception e) {
					e.printStackTrace();
				}

				for (Object[] param : result) {
					if(maxAmount<Double.valueOf(commonMethodsUtilService.getStringValueForObject(param[2])))
						maxAmount = Double.valueOf(commonMethodsUtilService.getStringValueForObject(param[2]));
				}

				List<String> intervalsList = commonMethodsUtilService.buildIntervals(maxAmount, IConstants.MATRIX_REPORT_INTERVALS);
				int i=0;
				for (String rangeValue : intervalsList) {
					i=i+1;
					FundSanctionMatrixRange range = new FundSanctionMatrixRange();
					range.setRangeValue(rangeValue);
					range.setMin(range.getRangeValue().split("-")[0].trim());
					range.setMax(range.getRangeValue().split("-")[1].trim());
					range.setOrderNo(String.valueOf(i));
					range.setScopeId(searchScopeId);
					range = fundSanctionMatrixRangeDAO.save(range);

					MatrixRangeVO vo = new MatrixRangeVO();
					vo.setId(range.getFundSanctionMatrixRangeId());
					vo.setName(range.getRangeValue());
					vo.setMin(range.getMin());
					vo.setMax(range.getMax());

					List<MatrixRangeVO> list = new ArrayList<MatrixRangeVO>(0);
					if(rangeWiseAreasMap.get(rangeValue) != null)
						list = rangeWiseAreasMap.get(rangeValue);

					list.add(vo);
					rangeWiseAreasMap.put(rangeValue, list);
				}

				Map<Long,List<Long>> locationIdMap = new HashMap<Long,List<Long>>(0);
				Long rangeId=0L;
				for (Object[] param : result) {
					Long yearId = commonMethodsUtilService.getLongValueForObject(param[0]);
					Long totalAmount = commonMethodsUtilService.getLongValueForObject(param[2]);
					Long scopeId = commonMethodsUtilService.getLongValueForObject(param[3]);
					Long locationId =  commonMethodsUtilService.getLongValueForObject(param[4]);


					List<Long> yearIdList = new ArrayList<Long>(0);
					if(locationIdMap.get(locationId) != null)
						yearIdList = locationIdMap.get(locationId);

					if(!yearIdList.contains(yearId)){
						yearIdList.add(yearId);
						for (String  rangeValue : rangeWiseAreasMap.keySet()) {
							String[] rangeArr = rangeValue.split("-");
							if(rangeArr != null && rangeArr.length>0){
								List<MatrixRangeVO> list = rangeWiseAreasMap.get(rangeValue);
								for (MatrixRangeVO vo : list) {
									Long min = Long.valueOf(vo.getMin().replaceAll(",", "").trim());
									Long max = Long.valueOf(vo.getMax().replaceAll(",", "").trim());
									if(totalAmount.longValue()>min.longValue() && totalAmount.longValue()<= max.longValue()){
										rangeId = vo.getId();
										break;
									}
								}
							}
						}

						if(rangeId != null && rangeId.longValue()>0L && searchScopeId != null && searchScopeId.longValue()>0L){
							FundSanctionMatrixDetails fundSanctionMatrixDetails = new FundSanctionMatrixDetails();

							if(presentFinancialYearIdsListmList.contains(yearId))
								fundSanctionMatrixDetails.setPresentYearId(yearId);
							else if(!presentFinancialYearIdsListmList.contains(yearId))
								fundSanctionMatrixDetails.setPreviousYearId(yearId);
							fundSanctionMatrixDetails.setScopeId(searchScopeId);
							fundSanctionMatrixDetails.setScopeValue(locationId.toString());
							fundSanctionMatrixDetails.setFundSanctionMatrixRangeId(rangeId);
							fundSanctionMatrixDetails.setIsDeleted("N");
							fundSanctionMatrixDetails.setInsertedTime(new DateUtilService().getCurrentDateAndTime());
							fundSanctionMatrixDetailsDAO.save(fundSanctionMatrixDetails);
						}
						locationIdMap.put(locationId, yearIdList);
					}
				}
				
					if(locationIdMap.size() < totalLocatinIdsList.size() && commonMethodsUtilService.isListOrSetValid(totalLocatinIdsList)){
						if(commonMethodsUtilService.isMapValid(locationIdMap)){
							List<MatrixRangeVO> list = rangeWiseAreasMap.get("0-0");
							Long totalAmount =0L;
							for (MatrixRangeVO vo : list) {
								Long min = Long.valueOf(vo.getMin().replaceAll(",", "").trim());
								Long max = Long.valueOf(vo.getMax().replaceAll(",", "").trim());
								if(totalAmount.longValue()== min.longValue() && totalAmount.longValue()== max.longValue()){
									rangeId = vo.getId();
									break;
								}
							}
							
							for (Long locationId : totalLocatinIdsList) {
								List<Long> addedYearIdList = locationIdMap.get(locationId);
								
								if(commonMethodsUtilService.isListOrSetValid(financialYearIdsListList)){
									if(commonMethodsUtilService.isListOrSetValid(addedYearIdList)){
										for (Long searchYearId : financialYearIdsListList) {
											if(!addedYearIdList.contains(searchYearId)){
												FundSanctionMatrixDetails fundSanctionMatrixDetails = new FundSanctionMatrixDetails();
												if(presentFinancialYearIdsListmList.contains(searchYearId))
													fundSanctionMatrixDetails.setPresentYearId(searchYearId);
												else if(!presentFinancialYearIdsListmList.contains(searchYearId))
													fundSanctionMatrixDetails.setPreviousYearId(searchYearId);
												fundSanctionMatrixDetails.setScopeId(searchScopeId);
												fundSanctionMatrixDetails.setScopeValue(locationId.toString());
												fundSanctionMatrixDetails.setFundSanctionMatrixRangeId(rangeId);
												fundSanctionMatrixDetails.setIsDeleted("N");
												fundSanctionMatrixDetails.setInsertedTime(new DateUtilService().getCurrentDateAndTime());
												fundSanctionMatrixDetailsDAO.save(fundSanctionMatrixDetails);
											}
										}
									}else{
										for (Long searchYearId : financialYearIdsListList) {											
											FundSanctionMatrixDetails fundSanctionMatrixDetails = new FundSanctionMatrixDetails();
											if(presentFinancialYearIdsListmList.contains(searchYearId))
												fundSanctionMatrixDetails.setPresentYearId(searchYearId);
											else if(!presentFinancialYearIdsListmList.contains(searchYearId))
												fundSanctionMatrixDetails.setPreviousYearId(searchYearId);
											fundSanctionMatrixDetails.setScopeId(searchScopeId);
											fundSanctionMatrixDetails.setScopeValue(locationId.toString());
											fundSanctionMatrixDetails.setFundSanctionMatrixRangeId(rangeId);
											fundSanctionMatrixDetails.setIsDeleted("N");
											fundSanctionMatrixDetails.setInsertedTime(new DateUtilService().getCurrentDateAndTime());
											fundSanctionMatrixDetailsDAO.save(fundSanctionMatrixDetails);
										}
									}
								}
							}
					}
				}
				
			}
		} catch (Exception e) {
			LOG.error(" Exception occured in FundSanctionMatrixReportService ,calculateFinancialWiseMatrxReportDetail()  ",e);
		}
	}
	
	public void insertNoDataAvailableLocationDetails(Long rangeId, Long searchScopeId, Map<Long,List<Long>> locationIdMap,List<Long> totalConstituencyIdsList,List<Long> financialYearIdsListList){
		try {
			//totalConstituencyIdsList
			if(commonMethodsUtilService.isMapValid(locationIdMap)){
				for (Long locationId : locationIdMap.keySet()) {
					List<Long> addedYearIdList = locationIdMap.get(locationId);
					if(commonMethodsUtilService.isListOrSetValid(financialYearIdsListList)){
						for (Long searchYearId : financialYearIdsListList) {
							if(!addedYearIdList.contains(searchYearId)){
								FundSanctionMatrixDetails fundSanctionMatrixDetails = new FundSanctionMatrixDetails();

								//if(presentFinancialYearIdsListmList.contains(searchYearId))
									//fundSanctionMatrixDetails.setPresentYearId(searchYearId);
								//else if(!presentFinancialYearIdsListmList.contains(searchYearId))
								//	fundSanctionMatrixDetails.setPreviousYearId(searchYearId);
								fundSanctionMatrixDetails.setScopeId(searchScopeId);
								fundSanctionMatrixDetails.setScopeValue(locationId.toString());
								fundSanctionMatrixDetails.setFundSanctionMatrixRangeId(rangeId);
								fundSanctionMatrixDetails.setIsDeleted("N");
								fundSanctionMatrixDetailsDAO.save(fundSanctionMatrixDetails);
							}
						}
					}
				}
			}
		} catch (Exception e) {
			LOG.error(" Exception occured in FundSanctionMatrixReportService ,insertNoDataAvailableLocationDetails()  ",e);
		}
	}
	public String getRangeValueByAmount(Long amount,Map<String,List<MatrixRangeVO>> rangeWiseAreasMap,Long yearId){
		String rangeValue ="";
		try {
			if(amount != null && commonMethodsUtilService.isMapValid(rangeWiseAreasMap)){
				for (String range : rangeWiseAreasMap.keySet()) {
					String[] rangeArr = range.split("-");
					if(rangeArr != null && rangeArr.length>0){
						Long min = commonMethodsUtilService.getLongValueForObject(rangeArr[0]);
						Long max = commonMethodsUtilService.getLongValueForObject(rangeArr[1]);
						List<MatrixRangeVO> list = rangeWiseAreasMap.get(range);
						
						/*if(amount.longValue()>=min.longValue() && amount.longValue()<= max.longValue())
							return range;*/
					}
				}
			}
		} catch (Exception e) {
			LOG.error(" Exception occured in FundSanctionMatrixReportService ,getRangeValueByAmount()  ",e);
		}
		return rangeValue;
	}
}
