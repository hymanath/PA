package com.itgrids.service.impl;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.itgrids.dao.IFundSanctionDAO;
import com.itgrids.dto.IdNameVO;
import com.itgrids.dto.LocationFundDetailsVO;
import com.itgrids.dto.LocationVO;
import com.itgrids.service.IFundManagementDashboardService;
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
	/*@Autowired
	private CommonMethodsUtilService commonMethodsUtilService;*/

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
	
	/*
	 * Author : Hymavathi G
	 * Date : 05/06/2017
	 * Description : { Location, Scheme & Sourse Wise Funds like Highest & Lowest & Avg Funds Details }
	 */
	public LocationFundDetailsVO getLocationWiseFundDetails(Long financialYrId,Long departmentId,Long sourceId,String startDate,String endDate,Long locationScopeId,String type ){
		LocationFundDetailsVO returnVO = new LocationFundDetailsVO();
		SimpleDateFormat sf = new SimpleDateFormat("dd/MM/yyyy");
		try{
			Date sDate =null;
			Date eDate = null;
			
			if(startDate != null && endDate != null){
				sDate = sf.parse(startDate);
				eDate = sf.parse(endDate);
			}
			Long totalfund = fundSanctionDAO.getTotalFund(financialYrId,departmentId,sourceId,sDate,eDate,null);
			List<Object[]> highFund = fundSanctionDAO.getLocationWiseFundHighAndLow(financialYrId,departmentId,sourceId,sDate,eDate,locationScopeId,type);
			if(highFund != null && highFund.size() >0){
				setFundDetails(highFund,returnVO,type,totalfund);
			}
			
			if(returnVO.getId() != null && returnVO.getId().longValue() >0l){
				List<Object[]> locWiseGrantTypes = fundSanctionDAO.getLocationWiseGrantTypesFund(financialYrId,departmentId,sourceId,sDate,eDate,locationScopeId,returnVO.getId());
				setGrantTypesToVO(locWiseGrantTypes,returnVO);
			}
			
			/*Long distHighId =0l;
			List<Object[]> highConstFund = fundSanctionDAO.getLocationWiseFundHighAndLow(financialYrId,departmentId,sourceId,sDate,eDate,4l,"highest");
			
			List<Object[]> highMandalFund = fundSanctionDAO.getLocationWiseFundHighAndLow(financialYrId,departmentId,sourceId,sDate,eDate,5l,"highest");
			
			List<Object[]> highVillgFund = fundSanctionDAO.getLocationWiseFundHighAndLow(financialYrId,departmentId,sourceId,sDate,eDate,6l,"highest");
			
			List<Object[]> lowDistFund = fundSanctionDAO.getLocationWiseFundHighAndLow(financialYrId,departmentId,sourceId,sDate,eDate,3l,"lowest");
			
			List<Object[]> lowConstFund = fundSanctionDAO.getLocationWiseFundHighAndLow(financialYrId,departmentId,sourceId,sDate,eDate,4l,"lowest");
			
			List<Object[]> lowMandalFund = fundSanctionDAO.getLocationWiseFundHighAndLow(financialYrId,departmentId,sourceId,sDate,eDate,5l,"lowest");
			
			List<Object[]> lowVillgFund = fundSanctionDAO.getLocationWiseFundHighAndLow(financialYrId,departmentId,sourceId,sDate,eDate,6l,"lowest");
			
			List<Object[]> highSchemeFund = fundSanctionDAO.getSchemeWiseFundHighAndLow(financialYrId,departmentId,sourceId,sDate,eDate,"highest");
			
			List<Object[]> lowSchemeFund = fundSanctionDAO.getSchemeWiseFundHighAndLow(financialYrId,departmentId,sourceId,sDate,eDate,"lowest");
			
			Long totalfund = fundSanctionDAO.getTotalFund(financialYrId,departmentId,sourceId,sDate,eDate,null);
			
			Long totalDistFund = fundSanctionDAO.getTotalFund(financialYrId,departmentId,sourceId,sDate,eDate,3l);
			Long totalContFund = fundSanctionDAO.getTotalFund(financialYrId,departmentId,sourceId,sDate,eDate,4l);
			Long totalMandalFund = fundSanctionDAO.getTotalFund(financialYrId,departmentId,sourceId,sDate,eDate,5l);
			Long totalVillgFund = fundSanctionDAO.getTotalFund(financialYrId,departmentId,sourceId,sDate,eDate,6l);
			
			List<Object[]> locWiseGrantTypes = fundSanctionDAO.getLocationWiseGrantTypesFund(financialYrId,departmentId,sourceId,sDate,eDate,3l,distHighId);*/
			
		}catch(Exception e){
			LOG.error(" Exception raised in getLocationWiseFundDetails (); ");
		}
		return returnVO;
	}
	
	public void setFundDetails(List<Object[]> list ,LocationFundDetailsVO returnVO,String type,Long totalfund){
		
		try{
			
			Object[] obj =list.get(0);
				returnVO.setTotalAmt(obj[0] != null ? Double.valueOf(obj[0].toString()) : 0l);
				returnVO.setId(obj[1] != null ? (Long)obj[1] : 0l);
				returnVO.setName(obj[2] != null ? (String)obj[2] : "");
				returnVO.setType(type);
				if(totalfund != null && totalfund.longValue() >0l)
				returnVO.setPerc(calculatePercantage(returnVO.getTotalAmt().longValue(),totalfund));
			
			
		}catch(Exception e){
			e.printStackTrace();
			LOG.error(" Exception raised in setFundDetails (); ");
		}
	}
	
	public Double calculatePercantage(Long subCount,Long totalCount){
	    Double d=0.0d;
	    if(subCount.longValue()>0l && totalCount.longValue()==0l)
	      LOG.error("Sub Count Value is "+subCount+" And Total Count Value  "+totalCount+" .So, Unable to calculate percentage.");

	    if(totalCount.longValue() > 0l){
	       d = new BigDecimal(subCount * 100.0/totalCount).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();   
	    }
	    return d;
	  }
	
public void setGrantTypesToVO(List<Object[]> list ,LocationFundDetailsVO returnVO){
		
		try{
			if(list != null && list.size()>0){
				Object[] obj =list.get(0);
					IdNameVO grantVO = new IdNameVO();
					grantVO.setTotal(obj[0] != null ? Double.valueOf(obj[0].toString()) : 0l);
					grantVO.setId(obj[1] != null ? (Long)obj[1] : 0l);
					grantVO.setName(obj[2] != null ? (String)obj[2] : "");
					
					if(grantVO.getTotal() != null && grantVO.getTotal().longValue() >0l)
					returnVO.setPerc(calculatePercantage(grantVO.getTotal().longValue(),Long.valueOf(returnVO.getTotalAmt().toString())));
					
					returnVO.getSubList().add(grantVO);
				
			}
			
		}catch(Exception e){
			e.printStackTrace();
			LOG.error(" Exception raised in setGrantTypesToVO (); ");
		}
	}
	
public LocationFundDetailsVO getTotalFunds(Long financialYrId,Long departmentId,Long sourceId,String startDate,String endDate){
	LocationFundDetailsVO retusnVo =new LocationFundDetailsVO();
	SimpleDateFormat sf = new SimpleDateFormat("dd/MM/yyyy");
	try{
		Date sDate =null;
		Date eDate = null;
		
		if(startDate != null && endDate != null){
			sDate = sf.parse(startDate);
			eDate = sf.parse(endDate);
		}
		Long totalfund = fundSanctionDAO.getTotalFund(financialYrId,departmentId,sourceId,sDate,eDate,null);
		if(totalfund != null && totalfund.longValue() > 0l){
			retusnVo.setTotalAmt(Double.valueOf(totalfund.toString()));
		}
		if(retusnVo.getTotalAmt() != null && retusnVo.getTotalAmt().longValue() >0l){
			List<Object[]> locWiseGrantTypes = fundSanctionDAO.getLocationWiseGrantTypesFund(financialYrId,departmentId,sourceId,sDate,eDate,null,null);
			setGrantTypesToVO(locWiseGrantTypes,retusnVo);
		}
	}catch(Exception e){
		e.printStackTrace();
		LOG.error(" Exception raised in getTotalFunds (); ");
	}
	return retusnVo;
}

}
