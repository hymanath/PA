package com.itgrids.service.impl;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.itgrids.dao.IFundSanctionDAO;
import com.itgrids.dto.LocationVO;
import com.itgrids.service.IFundManagementDashboardService;
import com.itgrids.utils.CommonMethodsUtilService;
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
	 * Date : 05/06/2017
	 * Author :Swadhin K Lenka
	 */
	public List<LocationVO> getLocationWiseAmountDetails(String fromDateStr, String toDateStr, Long levelId,List<Long> levelValues,Long financialYrId, Long deptId, Long sourceId){
		try{
			Date fromDate = null;        
			Date toDate = null; 
			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
			if(fromDateStr != null && fromDateStr.trim().length() > 0 && toDateStr != null && toDateStr.trim().length() > 0){
				fromDate = sdf.parse(fromDateStr);
				toDate = sdf.parse(toDateStr);
			}
			
			if(levelValues == null ){
				levelValues = fundSanctionDAO.getLocationValues(levelId);
				System.out.println("Hi");
			}
			
			
		}catch(Exception e){
			e.printStackTrace();
			LOG.error("Exception Occurred in getLocationWiseAmountDetails() of FundManagementDashboardService ", e);
			return null;
		}
		return null;
	}
	
}
