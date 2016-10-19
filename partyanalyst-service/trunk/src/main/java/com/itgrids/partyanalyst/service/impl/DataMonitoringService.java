package com.itgrids.partyanalyst.service.impl;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;

import com.itgrids.partyanalyst.dao.ITdpCadreDAO;
import com.itgrids.partyanalyst.dao.ITdpCadreDataVerificationDAO;
import com.itgrids.partyanalyst.dao.ITdpCadreEnrollmentYearDAO;
import com.itgrids.partyanalyst.dto.DataMonitoringOverviewVO;
import com.itgrids.partyanalyst.dto.IdNameVO;
import com.itgrids.partyanalyst.service.IDataMonitoringService;
import com.itgrids.partyanalyst.utils.CommonMethodsUtilService;

public class DataMonitoringService implements IDataMonitoringService {
	
	private static final Logger LOG = Logger.getLogger(DataMonitoringService.class);
	
	private ITdpCadreDataVerificationDAO tdpCadreDataVerificationDAO;
    private ITdpCadreEnrollmentYearDAO tdpCadreEnrollmentYearDAO;
	private CommonMethodsUtilService commonMethodsUtilService ;
	private ITdpCadreDAO tdpCadreDAO;
	
	public void setTdpCadreDataVerificationDAO(
			ITdpCadreDataVerificationDAO tdpCadreDataVerificationDAO) {
		this.tdpCadreDataVerificationDAO = tdpCadreDataVerificationDAO;
	}
	public void setTdpCadreEnrollmentYearDAO(
			ITdpCadreEnrollmentYearDAO tdpCadreEnrollmentYearDAO) {
		this.tdpCadreEnrollmentYearDAO = tdpCadreEnrollmentYearDAO;
	}
	public void setCommonMethodsUtilService(
			CommonMethodsUtilService commonMethodsUtilService) {
		this.commonMethodsUtilService = commonMethodsUtilService;
	}
	public void setTdpCadreDAO(ITdpCadreDAO tdpCadreDAO) {
		this.tdpCadreDAO = tdpCadreDAO;
	}    
	/**
	* @param  Stirng fromDateStr
	* @param  String toDateStr
	* @author Santosh 
	* @Description :This Service Method is used to Data Monitoring Verification Overview details. 
	* @since 19-OCT-2016
	*/
   public DataMonitoringOverviewVO getDataMonitoringOverViewDetails(String fromDateStr,String toDateStr){
	  
	   DataMonitoringOverviewVO resultVO = new DataMonitoringOverviewVO();
	   SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
	   Date toDate=null;
	   Date fromDate=null;
	   try{
		     if(fromDateStr != null && fromDateStr.trim().length()>0 && toDateStr!= null && toDateStr.trim().length()>0){
				 toDate = sdf.parse(toDateStr);
				 fromDate = sdf.parse(fromDateStr);
			 }
		   
		     List<Object[]> rtrnTtlRgstrtnCntObjLst = tdpCadreEnrollmentYearDAO.getCadreRegistrationCountByDataSourceType(fromDate, toDate);
		     List<Object[]> rtrnVerifyRegCntObjLst = tdpCadreEnrollmentYearDAO.getCadreRegistrationCountByCadreVerificationStatus(fromDate, toDate);
		     Long activyTeamMemberCnt = tdpCadreDataVerificationDAO.getActiveTeamMemberCnt(fromDate, toDate);
		     
		     resultVO.setActivyTeamMemberCnt(commonMethodsUtilService.getLongValueForObject(activyTeamMemberCnt));
			 if(rtrnTtlRgstrtnCntObjLst != null && rtrnTtlRgstrtnCntObjLst.size() > 0){
		    	  for(Object[] param:rtrnTtlRgstrtnCntObjLst){
		    		    String dataSourceType = param[0] != null ? param[0].toString().trim():" ";
		    		    resultVO.setTotalRegCnt(resultVO.getTotalRegCnt()+commonMethodsUtilService.getLongValueForObject(param[1]));
		    		  if(dataSourceType.equalsIgnoreCase("WEB")){
		    		    resultVO.setTotalWebRegCnt(commonMethodsUtilService.getLongValueForObject(param[1]));  
		    		  }else if(dataSourceType.equalsIgnoreCase("TAB")){
		    			resultVO.setTotalTabRegCnt(commonMethodsUtilService.getLongValueForObject(param[1]));  
		    		  }else if(dataSourceType.equalsIgnoreCase("ONLINE")){
		    			resultVO.setTotalOnlineRegCnt(commonMethodsUtilService.getLongValueForObject(param[1]));  
		    		  }
		    	  }
		      }
		      if(rtrnVerifyRegCntObjLst != null && rtrnVerifyRegCntObjLst.size() > 0){
		    	  for(Object[] param:rtrnVerifyRegCntObjLst){
		    		   String dataSourceType = commonMethodsUtilService.getStringValueForObject(param[1]);//data source type
			   	        if(param[0]==null){ // pending status
			   	        	  resultVO.setTotalPendingCnt(resultVO.getTotalPendingCnt()+commonMethodsUtilService.getLongValueForObject(param[2]));
				   	              if(dataSourceType.equalsIgnoreCase("WEB")){
					    		    resultVO.setTotalWebPendingCnt(commonMethodsUtilService.getLongValueForObject(param[2]));  
					    		    resultVO.setTotalPendingWebPer(calculatePercantage(resultVO.getTotalWebPendingCnt(), resultVO.getTotalWebRegCnt()));
					    		  }else if(dataSourceType.equalsIgnoreCase("TAB")){
					    			resultVO.setTotalTabPendingCnt(commonMethodsUtilService.getLongValueForObject(param[2]));  
					    			resultVO.setTotalPendingTabPer(calculatePercantage(resultVO.getTotalTabPendingCnt(),resultVO.getTotalTabRegCnt()));
					    		  }else if(dataSourceType.equalsIgnoreCase("ONLINE")){
					    			resultVO.setTotalOnlinePendingCnt(commonMethodsUtilService.getLongValueForObject(param[2]));  
					    			resultVO.setTotalPendingOnlinePer(calculatePercantage(resultVO.getTotalOnlinePendingCnt(), resultVO.getTotalOnlineRegCnt()));
					    		  }	
		    		    }else if(commonMethodsUtilService.getLongValueForObject(param[0])==1l){//approved status
		    		    	  resultVO.setTotalVerifyRegCnt(resultVO.getTotalVerifyRegCnt()+commonMethodsUtilService.getLongValueForObject(param[2]));
		    		    	      if(dataSourceType.equalsIgnoreCase("WEB")){
					    		    resultVO.setTotalWebVerifyCnt(commonMethodsUtilService.getLongValueForObject(param[2]));
					    		    resultVO.setTotalVerifyWebPer(calculatePercantage(resultVO.getTotalWebVerifyCnt(), resultVO.getTotalWebRegCnt()));
					    		  }else if(dataSourceType.equalsIgnoreCase("TAB")){
					    			resultVO.setTotalTabVerifyCnt(commonMethodsUtilService.getLongValueForObject(param[2]));  
					    			resultVO.setTotalVerifyTabPer(calculatePercantage(resultVO.getTotalTabVerifyCnt(),resultVO.getTotalTabRegCnt()));
					    		  }else if(dataSourceType.equalsIgnoreCase("ONLINE")){
					    			resultVO.setTotalOnlineVerifyCnt(commonMethodsUtilService.getLongValueForObject(param[2]));  
					    			resultVO.setTotalVerifyOnlinePer(calculatePercantage(resultVO.getTotalOnlineVerifyCnt(),resultVO.getTotalOnlineRegCnt()));
					    		  }
		    		    }else if(commonMethodsUtilService.getLongValueForObject(param[0])==2l){//rejected status
		    		    	    resultVO.setTotalVerifyRejectCnt(resultVO.getTotalVerifyRejectCnt()+commonMethodsUtilService.getLongValueForObject(param[2]));
		    		    	      if(dataSourceType.equalsIgnoreCase("WEB")){
					    		    resultVO.setTotalWebVerifyRejectedCnt(commonMethodsUtilService.getLongValueForObject(param[2])); 
					    		    resultVO.setTotalRejectedWebPer(calculatePercantage(resultVO.getTotalWebVerifyRejectedCnt(), resultVO.getTotalWebRegCnt()));
					    		  }else if(dataSourceType.equalsIgnoreCase("TAB")){
					    			resultVO.setTotalTabVerifyRejectedCnt(commonMethodsUtilService.getLongValueForObject(param[2]));  
					    			resultVO.setTotalRejectedTabPer(calculatePercantage(resultVO.getTotalTabVerifyRejectedCnt(), resultVO.getTotalTabRegCnt()));
					    		  }else if(dataSourceType.equalsIgnoreCase("ONLINE")){
					    			resultVO.setTotalOnlineVerifyRejectedCnt(commonMethodsUtilService.getLongValueForObject(param[2]));  
					    			resultVO.setTotalRejectedOnlinePer(calculatePercantage(resultVO.getTotalOnlineVerifyRejectedCnt(), resultVO.getTotalOnlineRegCnt()));
					    		  }
		    		    }
		    	  }
		      }
	   }catch(Exception e){
		  LOG.error("Error occured at getDataMonitoringOverViewDetails() in DataMonitoringService class",e);
		  return null;
	   }
	  return  resultVO;
   }
       public Double calculatePercantage(Long subCount,Long totalCount){
		Double d=0.0d;
		if(subCount.longValue()>0l && totalCount.longValue()==0l)
		LOG.error("Sub Count Value is "+subCount+" And Total Count Value  "+totalCount);

		if(totalCount.longValue() > 0l){
			 d = new BigDecimal(subCount * 100.0/totalCount).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();	 
		}
		return d;
		} 
 
	public IdNameVO getTotalRegCdrVendorWise(Long stateId, Long vendorId, Long distId, Long constId, String startDate, String endDate){
		LOG.info("Entered into getTotalRegCdrVendorWise() of DataMonitoringService");
		try{
			IdNameVO idNameVO = new IdNameVO();
			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
			Date stDate = null;
			Date ndDate = null;
			if(startDate != null && startDate.length() > 0 && endDate != null && endDate.length() > 0){
				stDate = sdf.parse(startDate);
				ndDate = sdf.parse(endDate);
			}
			Long pending = 0l;
			Long approved = 0l;
			Long rejected = 0l;
			Long total = 0l;
			//tab user dtls
			List<Object[]> totalCnt = tdpCadreDAO.getTotalRegCdrVendorWise(stateId,vendorId,distId,constId,stDate,ndDate);
			if(totalCnt != null && totalCnt.size() > 0){
				for(Object[] param : totalCnt){
					if((Long)param[0] == 1){
						approved = (Long)param[1];
					}else if((Long)param[0] == 2){
						rejected = (Long)param[1];
					}else{
						pending = (Long)param[1];
					}
				}
			}
			total = approved + rejected + rejected;
			idNameVO.setCount(total);//total
			idNameVO.setActualCount(approved);//approved
			idNameVO.setAvailableCount(pending);//pending
			idNameVO.setOrderId(rejected);//rejected
			//web and online user dtls
			return idNameVO;
		}catch(Exception e){
			e.printStackTrace();
			LOG.error("Exception raised in getTotalRegCdrVendorWise() of DataMonitoringService", e); 
		}
		return null;  
	}
	
	
}
