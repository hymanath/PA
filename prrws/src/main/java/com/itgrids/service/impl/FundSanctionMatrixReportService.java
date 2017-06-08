package com.itgrids.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.itgrids.dao.IFundSanctionDAO;
import com.itgrids.dao.IGrantTypeDAO;
import com.itgrids.utils.CommonMethodsUtilService;
import com.itgrids.utils.SetterAndGetterUtilService;

@Service
public class FundSanctionMatrixReportService {
private static final Logger LOG = Logger.getLogger(FundSanctionMatrixReportService.class);
	
	@Autowired
	private IFundSanctionDAO fundSanctionDAO;
	@Autowired
	private CommonMethodsUtilService commonMethodsUtilService;
	@Autowired
	private IGrantTypeDAO grantTypeDAO;
	@Autowired
	private SetterAndGetterUtilService setterAndGetterUtilService;
	
	/**
	 * @author Srishailam Pittala
	 * @Date 7th June,2017
	 * @description to get financial years wise matrix report 
	 */
	public void calculateFinancialWiseMatrxReportDetail(List<Long> previousFinancialYearIdsList,List<Long> presentFinancialYearIdsListmList, List<Long> deptIdsList,
			List<Long> sourceIdsList,List<Long> schemeIdsList,Long searchScopeId){
		try {
			List<Long> yearIdsList = new ArrayList<Long>(0);
			if(commonMethodsUtilService.isListOrSetValid(previousFinancialYearIdsList))
				yearIdsList.addAll(previousFinancialYearIdsList);
			if(commonMethodsUtilService.isListOrSetValid(presentFinancialYearIdsListmList))
				yearIdsList.addAll(presentFinancialYearIdsListmList);

			List<Object[]> result =  fundSanctionDAO.getFinancialYearWiseFundDetails(yearIdsList,deptIdsList,sourceIdsList,schemeIdsList,searchScopeId);
			Map<Long,Double> yearWiseAmountMap = new TreeMap<>();
			Map<Long,Double> previousYearWiseMaxAmountMap = new TreeMap<>();
			Map<Long,Double> presentYearWiseMaxAmountMap = new TreeMap<>();
			Map<String,List<String>> financialYearWiseRangesMap = new HashMap<>();
			
			if(commonMethodsUtilService.isListOrSetValid(result)){
				for (Object[] param : result) {
					Long yearId = commonMethodsUtilService.getLongValueForObject(param[0]);
					if(yearWiseAmountMap.get(yearId) != null)
						yearWiseAmountMap.put(yearId,yearWiseAmountMap.get(yearId)+Double.valueOf(commonMethodsUtilService.getStringValueForObject(param[1])));
					else
						yearWiseAmountMap.put(yearId,Double.valueOf(commonMethodsUtilService.getStringValueForObject(param[1])));
				}
				
				if(commonMethodsUtilService.isListOrSetValid(presentFinancialYearIdsListmList)){
					for (Long presentYearId : presentFinancialYearIdsListmList) {
						presentYearWiseMaxAmountMap.put(presentYearId,yearWiseAmountMap.get(presentYearId));
					}
				}
				
				if(commonMethodsUtilService.isListOrSetValid(previousFinancialYearIdsList)){
					for (Long previousYearId : previousFinancialYearIdsList) {
						previousYearWiseMaxAmountMap.put(previousYearId,yearWiseAmountMap.get(previousYearId));
					}
				}
			}
			
			if(commonMethodsUtilService.isMapValid(previousYearWiseMaxAmountMap) && commonMethodsUtilService.isMapValid(presentYearWiseMaxAmountMap)){
				for (Long year : previousYearWiseMaxAmountMap.keySet()) {
					
				}
			}
		} catch (Exception e) {
			LOG.error(" Exception occured in FundSanctionMatrixReportService ,calculateFinancialWiseMatrxReportDetail()  ",e);
		}
	}
}
