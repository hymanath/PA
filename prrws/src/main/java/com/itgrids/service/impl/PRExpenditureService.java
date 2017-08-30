package com.itgrids.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.itgrids.dao.IPredExpenditureLocationDAO;
import com.itgrids.dto.AddressVO;
import com.itgrids.dto.InputVO;
import com.itgrids.dto.PrExpenditureVO;
import com.itgrids.service.IPRExpenditureService;
import com.itgrids.utils.CommonMethodsUtilService;
@Service
@Transactional
public class PRExpenditureService implements IPRExpenditureService {
	private static final Logger LOG = Logger.getLogger(PRExpenditureService.class);
	
	@Autowired
	private IPredExpenditureLocationDAO predExpenditureLocationDAO;
	@Autowired
	private CommonMethodsUtilService commonMethodsUtilService;
	
	/*
	 * @Author: Santosh 
	 * @Date  :30-08-2017
	 * @Param :InputVO inputVO 
	 * @Return : List<PrExpenditureVO>
	 * @Description : This method is used to get location wise pr_expenditure details.
	 * 
	 */
	public List<PrExpenditureVO> getLocationWisePrExpenditureDtls(InputVO inputVO) {
		List<PrExpenditureVO> resultList = new ArrayList<PrExpenditureVO>(0);
		try {
			List<Object[]> expenditurObjLst = predExpenditureLocationDAO.getLocationWisePrExpenditureDtls(inputVO.getLocationType(), inputVO.getFilterType(),inputVO.getLocationIds());
			if (expenditurObjLst != null && expenditurObjLst.size() > 0) {
				for (Object[] param : expenditurObjLst) {
					PrExpenditureVO expenditureVO = getAddress(param);
					expenditureVO.setLocationId(commonMethodsUtilService.getLongValueForObject(param[3]));
					expenditureVO.setLocationName(commonMethodsUtilService.getStringValueForObject(param[4]));
					expenditureVO.setGrossAmount(commonMethodsUtilService.getStringValueForObject(param[0]));
					expenditureVO.setDeductions(commonMethodsUtilService.getStringValueForObject(param[1]));
					expenditureVO.setNetAmount(commonMethodsUtilService.getStringValueForObject(param[2]));
					resultList.add(expenditureVO);
				}
			}
		} catch (Exception e) {
			LOG.error("Exception occured at getLocationWisePrExpenditureDtls() in  PRExpenditureService class ",e);
		}
		return resultList;
	}

	private PrExpenditureVO getAddress(Object[] objArr) {
		PrExpenditureVO expenditureVO = new PrExpenditureVO();
		try {
			if (objArr != null && objArr.length > 0) {
				AddressVO addressVO = new AddressVO();
				addressVO.setStateId(commonMethodsUtilService.getLongValueForObject(objArr[5]));
				addressVO.setStateName(commonMethodsUtilService.getStringValueForObject(objArr[6]));
				addressVO.setDistrictId(commonMethodsUtilService.getLongValueForObject(objArr[7]));
				addressVO.setDistrictName(commonMethodsUtilService.getStringValueForObject(objArr[8]));
				addressVO.setAssemblyId(commonMethodsUtilService.getLongValueForObject(objArr[9]));
				addressVO.setAssemblyName(commonMethodsUtilService.getStringValueForObject(objArr[10]));
				addressVO.setTehsilId(commonMethodsUtilService.getLongValueForObject(objArr[11]));
				addressVO.setTehsilName(commonMethodsUtilService.getStringValueForObject(objArr[12]));
				addressVO.setParliamentId(commonMethodsUtilService.getLongValueForObject(objArr[13]));
				addressVO.setParliamentName(commonMethodsUtilService.getStringValueForObject(objArr[14]));
				addressVO.setPanchayatId(commonMethodsUtilService.getLongValueForObject(objArr[15]));
				addressVO.setPanchayatName(commonMethodsUtilService.getStringValueForObject(objArr[16]));
				addressVO.setDivisionId(commonMethodsUtilService.getLongValueForObject(objArr[17]));
				addressVO.setDivisionName(commonMethodsUtilService.getStringValueForObject(objArr[18]));
				expenditureVO.setAddressVO(addressVO);
			}
		} catch (Exception e) {
			LOG.error("Exception occured at getAddress() in  PRExpenditureService class ",e);
		}
		return expenditureVO;
	}
}
