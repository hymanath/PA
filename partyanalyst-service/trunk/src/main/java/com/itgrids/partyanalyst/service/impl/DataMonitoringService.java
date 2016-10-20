package com.itgrids.partyanalyst.service.impl;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
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
import com.itgrids.partyanalyst.utils.DateUtilService;

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
					if(param[0] != null){
						if((Long)param[0] == 1){
							approved = (Long)param[1];
						}else{
							rejected = (Long)param[1];
						}
					}else{
						pending = (Long)param[1];    
					}
					
				}
			}
			total = approved + rejected + pending;  
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
	public List<IdNameVO> getTotalRegCdrVendorAndTabUserWise(Long stateId, Long vendorId, Long distId, Long constId, String startDate, String endDate){
		LOG.info("Entered into getTotalRegCdrVendorAndTabUserWise() of DataMonitoringService");  
		try{
			DateUtilService dateUtilService = new DateUtilService();
			IdNameVO idNameVO = null;
			List<IdNameVO> idNameVOs = new ArrayList<IdNameVO>();
			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
			Date stDate = null;
			Date ndDate = null;
			if(startDate != null && startDate.length() > 0 && endDate != null && endDate.length() > 0){
				stDate = sdf.parse(startDate);
				ndDate = sdf.parse(endDate);
			}
			//for total count
			List<Object[]> totalCountList = tdpCadreDAO.getTotalRegCdrVendorAndTabUserWise(stateId,vendorId,distId,constId,stDate,ndDate,"");
			if(totalCountList != null && totalCountList.size() > 0){
				for(Object[] param : totalCountList){
					idNameVO = new IdNameVO();
					idNameVO.setId(param[0] != null ? (Long)param[0] : 0l);
					idNameVO.setName(param[1] != null ? param[1].toString() : "");
					idNameVO.setTabUserId(param[2] != null ? (Long)param[2] : 0l);
					idNameVO.setTabUserName(param[3] != null ? param[3].toString() : "");
					idNameVO.setMobileNo(param[4] != null ? param[4].toString() : "");
					idNameVO.setTotalCount(param[5] != null ? (Long)param[5] : 0l);
					idNameVOs.add(idNameVO);
				}
			}
			//for approved count
			
			List<Object[]> totalApprovedList = tdpCadreDAO.getTotalRegCdrVendorAndTabUserWise(stateId,vendorId,distId,constId,stDate,ndDate,"approved");
			if(totalApprovedList != null && totalApprovedList.size() > 0){
				for(Object[] param : totalApprovedList){
					pushApprovedCount(idNameVOs,(Long)param[0],(Long)param[2],(Long)param[5]);
				}
			} 
			//for rejected count
			List<Object[]> totalRejectedList = tdpCadreDAO.getTotalRegCdrVendorAndTabUserWise(stateId,vendorId,distId,constId,stDate,ndDate,"rejected");
			if(totalRejectedList != null && totalRejectedList.size() > 0){
				for(Object[] param : totalRejectedList){
					pushRejectedCount(idNameVOs,(Long)param[0],(Long)param[2],(Long)param[5]);
				}
			} 
			//for pending count
			List<Object[]> totalPendingList = tdpCadreDAO.getTotalRegCdrVendorAndTabUserWise(stateId,vendorId,distId,constId,stDate,ndDate,"pending");
			if(totalPendingList != null && totalPendingList.size() > 0){
				for(Object[] param : totalPendingList){
					pushPendingCount(idNameVOs,(Long)param[0],(Long)param[2],(Long)param[5]);
				}
			} 
			Date today = dateUtilService.getCurrentDateAndTime();
			Calendar cal = Calendar.getInstance();
			cal.setTime(today);
			cal.set(Calendar.HOUR, cal.get(Calendar.HOUR) - 1);
			Date lastOneHourTime = cal.getTime();
			//push active status
			SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
			String dt = "2016-10-19 18:00:00";
			lastOneHourTime = sdf1.parse(dt);         
			List<Object[]> activeUserList = tdpCadreDAO.getActiveUserList(stateId,vendorId,distId,constId,stDate,ndDate,lastOneHourTime);
			if(activeUserList != null && activeUserList.size() > 0){
				for(Object[] param : activeUserList){
					pushActiveStatus(idNameVOs,(Long)param[0],(Long)param[1]); 
				}
			} 
			
			return idNameVOs;  
		}catch(Exception e){
			e.printStackTrace();
			LOG.error("Exception raised in getTotalRegCdrVendorAndTabUserWise() of DataMonitoringService", e); 
		}
		return null;
	}
	public void pushApprovedCount(List<IdNameVO> idNameVOs,Long surveyUserId,Long tabUserId,Long count){
		if(idNameVOs.size() > 0){
			for(IdNameVO idNameVO : idNameVOs){
				if(idNameVO.getId().equals(surveyUserId) && idNameVO.getTabUserId().equals(tabUserId)){
					idNameVO.setApprovedCount(count);
				}
			}
		}
	}
	public void pushRejectedCount(List<IdNameVO> idNameVOs,Long surveyUserId,Long tabUserId,Long count){
		if(idNameVOs.size() > 0){
			for(IdNameVO idNameVO : idNameVOs){
				if(idNameVO.getId().equals(surveyUserId) && idNameVO.getTabUserId().equals(tabUserId)){
					idNameVO.setRejectedCount(count);
				}
			}
		}
	}
	public void pushPendingCount(List<IdNameVO> idNameVOs,Long surveyUserId,Long tabUserId,Long count){  
		if(idNameVOs.size() > 0){
			for(IdNameVO idNameVO : idNameVOs){
				if(idNameVO.getId().equals(surveyUserId) && idNameVO.getTabUserId().equals(tabUserId)){
					idNameVO.setPendingCount(count);  
				}
			}
		}
	}
	public void pushActiveStatus(List<IdNameVO> idNameVOs,Long surveyUserId,Long tabUserId){
		if(idNameVOs.size() > 0){
			for(IdNameVO idNameVO : idNameVOs){
				if(idNameVO.getId().equals(surveyUserId) && idNameVO.getTabUserId().equals(tabUserId)){
					idNameVO.setStatus("active");    
				}
			}
		}
	}
	
}  
