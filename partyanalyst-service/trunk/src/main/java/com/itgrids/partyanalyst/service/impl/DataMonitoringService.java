package com.itgrids.partyanalyst.service.impl;

import java.io.File;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.UUID;

import org.apache.log4j.Logger;

import com.itgrids.partyanalyst.dao.ICadreRegUserDAO;
import com.itgrids.partyanalyst.dao.ICadreRegUserTabUserDAO;
import com.itgrids.partyanalyst.dao.IDataRejectReasonDAO;
import com.itgrids.partyanalyst.dao.ITabUserInfoDAO;
import com.itgrids.partyanalyst.dao.ITdpCadreDAO;
import com.itgrids.partyanalyst.dao.ITdpCadreDataVerificationDAO;
import com.itgrids.partyanalyst.dao.ITdpCadreEnrollmentYearDAO;
import com.itgrids.partyanalyst.dto.CadreRegUserVO;
import com.itgrids.partyanalyst.dto.DataMonitoringOverviewVO;
import com.itgrids.partyanalyst.dto.IdNameVO;
import com.itgrids.partyanalyst.dto.ResultCodeMapper;
import com.itgrids.partyanalyst.dto.ResultStatus;
import com.itgrids.partyanalyst.model.DataRejectReason;
import com.itgrids.partyanalyst.model.TdpCadreDataVerification;
import com.itgrids.partyanalyst.service.IDataMonitoringService;
import com.itgrids.partyanalyst.utils.CommonMethodsUtilService;
import com.itgrids.partyanalyst.utils.DateUtilService;
import com.itgrids.partyanalyst.utils.IConstants;
import com.itgrids.partyanalyst.utils.ImageAndStringConverter;

public class DataMonitoringService implements IDataMonitoringService {
	
	private static final Logger LOG = Logger.getLogger(DataMonitoringService.class);
	
	private ITdpCadreDataVerificationDAO tdpCadreDataVerificationDAO;
    private ITdpCadreEnrollmentYearDAO tdpCadreEnrollmentYearDAO;
	private CommonMethodsUtilService commonMethodsUtilService ;
	private DateUtilService dateUtilService;
	private ITdpCadreDAO tdpCadreDAO;
	private IDataRejectReasonDAO dataRejectReasonDAO;
	private ICadreRegUserDAO cadreRegUserDAO;
	private ICadreRegUserTabUserDAO cadreRegUserTabUserDAO;
	private ITabUserInfoDAO tabUserInfoDAO;
	
	
	public ITabUserInfoDAO getTabUserInfoDAO() {
		return tabUserInfoDAO;
	}
	public void setTabUserInfoDAO(ITabUserInfoDAO tabUserInfoDAO) {
		this.tabUserInfoDAO = tabUserInfoDAO;
	}
	public ICadreRegUserTabUserDAO getCadreRegUserTabUserDAO() {
		return cadreRegUserTabUserDAO;
	}
	public void setCadreRegUserTabUserDAO(
			ICadreRegUserTabUserDAO cadreRegUserTabUserDAO) {
		this.cadreRegUserTabUserDAO = cadreRegUserTabUserDAO;
	}
	public ICadreRegUserDAO getCadreRegUserDAO() {
		return cadreRegUserDAO;
	}
	public void setCadreRegUserDAO(ICadreRegUserDAO cadreRegUserDAO) {
		this.cadreRegUserDAO = cadreRegUserDAO;
	}
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
	public void setDateUtilService(DateUtilService dateUtilService) {
		this.dateUtilService = dateUtilService;
	}
	public void setDataRejectReasonDAO(IDataRejectReasonDAO dataRejectReasonDAO) {
		this.dataRejectReasonDAO = dataRejectReasonDAO;
	}
	/**
	* @param  Stirng fromDateStr
	* @param  String toDateStr
	* @author Santosh 
	* @Description :This Service Method is used to get Data Monitoring Verification Overview details. 
	* @since 19-OCT-2016
	*/
   public DataMonitoringOverviewVO getDataMonitoringOverViewDetails(String fromDateStr,String toDateStr,Long stateId){
	  
	   DataMonitoringOverviewVO resultVO = new DataMonitoringOverviewVO();
	   SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
	   Date toDate=null;
	   Date fromDate=null;
	   try{
		     if(fromDateStr != null && fromDateStr.trim().length()>0 && toDateStr!= null && toDateStr.trim().length()>0){
				 toDate = sdf.parse(toDateStr);
				 fromDate = sdf.parse(fromDateStr);
			 }
		   
		     List<Object[]> rtrnTtlRgstrtnCntObjLst = tdpCadreEnrollmentYearDAO.getCadreRegistrationCountByDataSourceType(fromDate, toDate,stateId);
		     List<Object[]> rtrnVerifyRegCntObjLst = tdpCadreEnrollmentYearDAO.getCadreRegistrationCountByCadreVerificationStatus(fromDate, toDate,stateId);
		     Long activyTeamMemberCnt = tdpCadreDataVerificationDAO.getActiveTeamMemberCnt(stateId);
		     
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
	
	public IdNameVO getTotalRegCdrVendorWiseNew(Long loginUserId, Long userId, Long constId, String startDate, String endDate){
		LOG.info("Entered into getTotalRegCdrVendorWise() of DataMonitoringService");
		try{
			
			Long cadreRegUserId = cadreRegUserDAO.getCadreRegUserByUserForDataMonitoring(userId);
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
			List<Object[]> totalCnt = tdpCadreDAO.getTotalRegCdrVendorWiseNew(cadreRegUserId, userId, constId, stDate, ndDate);
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
			//SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
			//String dt = "2016-10-19 18:00:00";
			//lastOneHourTime = sdf1.parse(dt);              
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
	
	public List<IdNameVO> getTotalRegCdrVendorAndTabUserWiseNew(Long loginUserId, Long userId, Long constId, String startDate, String endDate){
		LOG.info("Entered into getTotalRegCdrVendorAndTabUserWise() of DataMonitoringService");  
		try{
			Long cadreRegUserId = cadreRegUserDAO.getCadreRegUserByUserForDataMonitoring(userId);
			
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
			List<Object[]> totalCountList = tdpCadreDAO.getTotalRegCdrVendorAndTabUserWiseNew(cadreRegUserId, userId, constId, stDate, ndDate, "");
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
			
			List<Object[]> totalApprovedList = tdpCadreDAO.getTotalRegCdrVendorAndTabUserWiseNew(cadreRegUserId, userId, constId, stDate, ndDate, "approved");
			if(totalApprovedList != null && totalApprovedList.size() > 0){
				for(Object[] param : totalApprovedList){
					pushApprovedCount(idNameVOs,(Long)param[0],(Long)param[2],(Long)param[5]);
				}
			} 
			//for rejected count
			List<Object[]> totalRejectedList = tdpCadreDAO.getTotalRegCdrVendorAndTabUserWiseNew(cadreRegUserId, userId, constId, stDate, ndDate, "rejected");
			if(totalRejectedList != null && totalRejectedList.size() > 0){
				for(Object[] param : totalRejectedList){
					pushRejectedCount(idNameVOs,(Long)param[0],(Long)param[2],(Long)param[5]);
				}
			} 
			//for pending count
			List<Object[]> totalPendingList = tdpCadreDAO.getTotalRegCdrVendorAndTabUserWiseNew(cadreRegUserId, userId, constId, stDate, ndDate, "pending");
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
			//SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
			//String dt = "2016-10-19 18:00:00";
			//lastOneHourTime = sdf1.parse(dt);              
			List<Object[]> activeUserList = tdpCadreDAO.getActiveUserListNew(cadreRegUserId, userId, constId, stDate, ndDate, lastOneHourTime);
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
	
	public List<List<IdNameVO>> getVerifiedDtls(Long surveyUserId, Long tabUserId, Long webUserId, String startDate, String endDate,Integer minValue,Integer maxValue,String resultType,String verificationStatus,String dataSourceType,Long stateId){
		LOG.info("Entered into getVerifiedDtls() of DataMonitoringService");  
		try{
			DateUtilService dateUtilService = new DateUtilService();
			IdNameVO idNameVO = null;
			List<IdNameVO> ownVoterDtls = new ArrayList<IdNameVO>();
			List<IdNameVO> familyVoterDtls = new ArrayList<IdNameVO>();
			List<List<IdNameVO>> finalList = new ArrayList<List<IdNameVO>>();
			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");  
			SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd");
			Date stDate = null;
			Date ndDate = null;
			if(startDate != null && startDate.length() > 0 && endDate != null && endDate.length() > 0){
				stDate = sdf.parse(startDate);
				ndDate = sdf.parse(endDate);
				startDate = sdf1.format(stDate);
				endDate = sdf1.format(ndDate);
			}  
			//get verified dtls  
			if(resultType != null && resultType.equalsIgnoreCase("All") || resultType.equalsIgnoreCase("Self")){
				List<Object[]> ownVoterVerifiedDtlsList = tdpCadreDAO.getVoterCardDtlsList(surveyUserId,tabUserId,webUserId,startDate,endDate,"own",minValue,maxValue,verificationStatus,dataSourceType,stateId);
				if(ownVoterVerifiedDtlsList != null && ownVoterVerifiedDtlsList.size() > 0){
					for(Object[] param : ownVoterVerifiedDtlsList){
						idNameVO = new IdNameVO();
						idNameVO.setCadreId(param[0] != null ? (Long)param[0] : 0l);
						idNameVO.setName(param[1] != null ? param[1].toString() : "");
						idNameVO.setMobileNo(param[2] != null ? param[2].toString() : "");
						idNameVO.setGender(param[3] != null ? param[3].toString() : "");
						idNameVO.setImage(param[4] != null ? param[4].toString() : "noImage");
						idNameVO.setVoterImage(param[6] != null ? param[6].toString() : "noImage");  
						idNameVO.setStatusId(param[7] != null ? (Long)param[7] : 0l);
						idNameVO.setStatus(param[8] != null ? param[8].toString() : "noStatus");
						idNameVO.setId(param[9] != null ? (Long)param[9] : 0l);//rejected reason id
						idNameVO.setWish(param[10] != null ? param[10].toString() : "");//reason
						idNameVO.setConstitunecyId(param[11] != null ? (Long)param[11] : 0l);//constituencyId
						idNameVO.setDistrictid(param[12] != null ? (Long)param[12] : 0l);//districtId
						idNameVO.setCadreUserId(param[13] != null ? (Long)param[13] : 0l);//cadreSurveyUserId
						idNameVO.setTabUserId(param[14] != null ? (Long)param[14] : 0l);//tabUserInfoId
						ownVoterDtls.add(idNameVO);
					}
				  if(minValue == 0){
					  List<Object[]> allOwnVoterVerifiedDtlsList = tdpCadreDAO.getVoterCardDtlsList(surveyUserId,tabUserId,webUserId,startDate,endDate,"own",0,0,verificationStatus,dataSourceType,stateId);
					    if(ownVoterDtls != null && ownVoterDtls.size() > 0){
					    	ownVoterDtls.get(0).setTotalCount(Long.valueOf(allOwnVoterVerifiedDtlsList.size()));	
					    }
				  }
					ownVoterDtls.get(0).setPublicRepr("own");
					finalList.add(ownVoterDtls);
				}
			}
			if(resultType != null && resultType.equalsIgnoreCase("All") || resultType.equalsIgnoreCase("Relative")){
				List<Object[]> familyVoterVerifiedDtlsList = tdpCadreDAO.getVoterCardDtlsList(surveyUserId,tabUserId,webUserId,startDate,endDate,"family",minValue,maxValue,verificationStatus,dataSourceType,stateId);
				if(familyVoterVerifiedDtlsList != null && familyVoterVerifiedDtlsList.size() > 0){
					for(Object[] param : familyVoterVerifiedDtlsList){
						idNameVO = new IdNameVO();
						idNameVO.setCadreId(param[0] != null ? (Long)param[0] : 0l);
						idNameVO.setName(param[1] != null ? param[1].toString() : "");
						idNameVO.setMobileNo(param[2] != null ? param[2].toString() : "");
						idNameVO.setGender(param[3] != null ? param[3].toString() : "");
						idNameVO.setImage(param[4] != null ? param[4].toString() : "noImage");
						idNameVO.setVoterImage(param[6] != null ? param[6].toString() : "noImage");  
						idNameVO.setStatusId(param[7] != null ? (Long)param[7] : 0l);
						idNameVO.setStatus(param[8] != null ? param[8].toString() : "noStatus");
						idNameVO.setId(param[9] != null ? (Long)param[9] : 0l);//rejected reason id
						idNameVO.setWish(param[10] != null ? param[10].toString() : "");//reason
						idNameVO.setConstitunecyId(param[11] != null ? (Long)param[11] : 0l);//constituencyId
						idNameVO.setDistrictid(param[12] != null ? (Long)param[12] : 0l);//districtId
						idNameVO.setCadreUserId(param[13] != null ? (Long)param[13] : 0l);//cadreSurveyUserId
						idNameVO.setTabUserId(param[14] != null ? (Long)param[14] : 0l);//tabUserInfoId
						familyVoterDtls.add(idNameVO);
					}
					 if(minValue == 0){
						   List<Object[]> allFamilyVoterVerifiedDtlsList = tdpCadreDAO.getVoterCardDtlsList(surveyUserId,tabUserId,webUserId,startDate,endDate,"family",0,0,verificationStatus,dataSourceType,stateId);
						    if(familyVoterDtls != null && familyVoterDtls.size() > 0){
						    	familyVoterDtls.get(0).setTotalCount(Long.valueOf(allFamilyVoterVerifiedDtlsList.size()));	
						    }
					  }
					familyVoterDtls.get(0).setPublicRepr("family");
					if(resultType != null && resultType.equalsIgnoreCase("Relative")){
						List<IdNameVO> ownVterDtls = new ArrayList<IdNameVO>();
						finalList.add(0, ownVterDtls);
						finalList.add(1, familyVoterDtls);
					}else{
						finalList.add(familyVoterDtls);	
					}
				}
			}
			return finalList;
		}catch(Exception e){  
			e.printStackTrace();
			LOG.error("Exception raised in getVerifiedDtls() of DataMonitoringService", e); 
		}
		return null;
	}
	
	/**
	* @param  Stirng fromDateStr
	* @param  String toDateStr
	* @param String dataSourceType
	* @param String verificationStatus
	* @author Santosh 
	* @Description :This Service Method is used to get Data Monitoring User Wise Registration Count details. 
	* @since 20-OCT-2016
	*/
	public List<DataMonitoringOverviewVO> getRegistrationDetailsUserWise(String fromDateStr,String toDateStr,String dataSourceType,String verificationStatus,Long stateId){
		
		List<DataMonitoringOverviewVO> resultList = new ArrayList<DataMonitoringOverviewVO>();
		Map<Long,DataMonitoringOverviewVO> webAndOnlineDetailsMap = new HashMap<Long, DataMonitoringOverviewVO>();
		Map<Long,Map<Long,Long>> tabActiveMemberCntMap = new HashMap<Long, Map<Long,Long>>();
		Map<Long,Map<Long,DataMonitoringOverviewVO>> cadreSurveyUserDtlsMap = new HashMap<Long, Map<Long,DataMonitoringOverviewVO>>();
		Map<Long,String> surveryUserIdNameMap =new HashMap<Long, String>();
		Map<Long,Long> webAndOnlineActiveUserMap = new HashMap<Long, Long>();
		SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
	    Date toDate=null;
		Date fromDate=null;
		
		try{
			if(fromDateStr != null && fromDateStr.trim().length()>0 && toDateStr!= null && toDateStr.trim().length()>0){
				 toDate = sdf.parse(toDateStr);
				 fromDate = sdf.parse(fromDateStr);
			 }
		   
			Date currentTime = dateUtilService.getCurrentDateAndTime();
			Calendar cal = Calendar.getInstance();
			cal.setTime(currentTime);
			cal.set(Calendar.HOUR, cal.get(Calendar.HOUR) - 1);
			Date lastOneHourTime = cal.getTime();
			
			
			if(dataSourceType != null && dataSourceType.equalsIgnoreCase("WEB") || dataSourceType.equalsIgnoreCase("ONLINE")){
			  List<Object[]> rtrnActiveMemberObjList = tdpCadreEnrollmentYearDAO.getWebAndOnlineCadreRegistrationCountLastOneHoursUserWise(lastOneHourTime, dataSourceType, verificationStatus,stateId);
			    setWebAndOnlineActiveMembersCntDetails(rtrnActiveMemberObjList,webAndOnlineActiveUserMap);
			  List<Object[]> rtrnObjList = tdpCadreEnrollmentYearDAO.getWebAndOnlineCadreRegistrationCountUserWise(fromDate, toDate, dataSourceType, verificationStatus,stateId);
			    setWebAndOnlineMemberDetails(rtrnObjList,webAndOnlineDetailsMap,webAndOnlineActiveUserMap);
			   if(webAndOnlineDetailsMap != null && webAndOnlineDetailsMap.size() > 0){
				 for(Entry<Long,DataMonitoringOverviewVO> entry:webAndOnlineDetailsMap.entrySet()){
					 resultList.add(entry.getValue());  
				 }
			   }
 			}else if(dataSourceType != null && dataSourceType.equalsIgnoreCase("TAB")){
 			  List<Object[]> rtrnTabActiveMemberObjList = tdpCadreEnrollmentYearDAO.getTabCadreRegistrationCountLastOneHoursUserWise(lastOneHourTime, dataSourceType, verificationStatus,stateId);
 			   setTabActiveMemberDetailsCnt(rtrnTabActiveMemberObjList,tabActiveMemberCntMap);
 			  List<Object[]> rtrnObjList = tdpCadreEnrollmentYearDAO.getTabCadreRegistrationCountUserWise(fromDate, toDate, dataSourceType, verificationStatus,stateId);
 			   setTabMembersCntDetails(rtrnObjList,cadreSurveyUserDtlsMap,tabActiveMemberCntMap,surveryUserIdNameMap);
 			   if(cadreSurveyUserDtlsMap != null && cadreSurveyUserDtlsMap.size() > 0){
 				   for(Entry<Long,Map<Long,DataMonitoringOverviewVO>> entry:cadreSurveyUserDtlsMap.entrySet()){
 					   DataMonitoringOverviewVO VO = new DataMonitoringOverviewVO();
 					     VO.setSurveryUserId(entry.getKey());
 					     VO.setSurveryUserName(surveryUserIdNameMap.get(entry.getKey()));
 					     VO.setSubList(new ArrayList<DataMonitoringOverviewVO>(entry.getValue().values()));
 					     resultList.add(VO);
 				   }
 			   }
 	 		}
		}catch(Exception e){
			LOG.error("Exception raised in getRegistrationDetailsUserWise() of DataMonitoringService", e);	
			return null;
		}
	 return resultList;	
	}
  public void setWebAndOnlineActiveMembersCntDetails(List<Object[]> objList,Map<Long,Long> webAndOnlineActiveUserMap){
		try{
			if(objList != null && objList.size() > 0){
				for(Object[] param:objList){
					webAndOnlineActiveUserMap.put(commonMethodsUtilService.getLongValueForObject(param[0]),commonMethodsUtilService.getLongValueForObject(param[1])); 	
				}
			}
		}catch(Exception e){
			LOG.error("Exception raised in setWebAndOnlineActiveMembersCntDetails() of DataMonitoringService", e);		
		}
	}
  public void setWebAndOnlineMemberDetails(List<Object[]> objList,Map<Long,DataMonitoringOverviewVO> webAndOnlineDetailsMap,Map<Long,Long> webAndOnlineActiveUserMap){
	  try{
		  if(objList != null && objList.size() > 0){
			  for(Object[] param:objList){
				  DataMonitoringOverviewVO webOnlineDtlsVO = webAndOnlineDetailsMap.get(commonMethodsUtilService.getLongValueForObject(param[0]));
				  Long verificationStatusId = param[3] != null ? (Long)param[3]:null;
				   if(webOnlineDtlsVO == null){
					       webOnlineDtlsVO = new DataMonitoringOverviewVO();  
						   webOnlineDtlsVO.setSurveryUserId(commonMethodsUtilService.getLongValueForObject(param[0]));
						   webOnlineDtlsVO.setSurveryUserName(commonMethodsUtilService.getStringValueForObject(param[1]));
						   webOnlineDtlsVO.setMobileNo(commonMethodsUtilService.getStringValueForObject(param[2]));
						   if(webAndOnlineActiveUserMap.get(webOnlineDtlsVO.getSurveryUserId()) != null && webAndOnlineActiveUserMap.get(webOnlineDtlsVO.getSurveryUserId()) >0){
							   webOnlineDtlsVO.setIsActive("Active");   
						   }
						   webAndOnlineDetailsMap.put(commonMethodsUtilService.getLongValueForObject(param[0]), webOnlineDtlsVO);
				   }
				       webOnlineDtlsVO.setTotalRegCnt(webOnlineDtlsVO.getTotalRegCnt()+commonMethodsUtilService.getLongValueForObject(param[4]));
				   if(verificationStatusId == null ){//pending
					   webOnlineDtlsVO.setTotalPendingCnt(commonMethodsUtilService.getLongValueForObject(param[4]));
				   }else if(verificationStatusId.longValue() == 1l){//approved
					   webOnlineDtlsVO.setTotalVerifyRegCnt(commonMethodsUtilService.getLongValueForObject(param[4]));
				   }else if(verificationStatusId.longValue() == 2l){//rejected
					   webOnlineDtlsVO.setTotalVerifyRejectCnt(commonMethodsUtilService.getLongValueForObject(param[4]));
				   }
			  }
		  }
	  }catch(Exception e){
		  LOG.error("Exception raised in setWebAndOnlineMemberDetails() of DataMonitoringService", e);	  
	  }
  }
  public void setTabActiveMemberDetailsCnt(List<Object[]> objList,Map<Long,Map<Long,Long>> tabActiveMemberCntMap){
	  try{
		  if(objList != null && objList.size() > 0){
			  for(Object[] param:objList){
				       Map<Long,Long> tabUserMap = tabActiveMemberCntMap.get(commonMethodsUtilService.getLongValueForObject(param[0]));
				    if(tabUserMap == null){
				    	tabUserMap = new HashMap<Long, Long>();	
				    	tabActiveMemberCntMap.put(commonMethodsUtilService.getLongValueForObject(param[0]), tabUserMap);
				    }
				     tabUserMap.put(commonMethodsUtilService.getLongValueForObject(param[1]),commonMethodsUtilService.getLongValueForObject(param[2]));
			  }
		  }
	  }catch(Exception e){
		  LOG.error("Exception raised in setTabActiveMemberDetailsCnt() of DataMonitoringService", e);  
	  }
  }
  public void setTabMembersCntDetails(List<Object[]> objList,Map<Long,Map<Long,DataMonitoringOverviewVO>> cadreSurveyUserDtlsMap,Map<Long,Map<Long,Long>> tabActiveMemberCntMap,Map<Long,String> surveryUserIdNameMap){
	  try{
		  if(objList!= null && objList.size() > 0){
			  for(Object[] param:objList){
					  Long cadreSurveryUserId = commonMethodsUtilService.getLongValueForObject(param[0]);
					  Long tabUserInfoId = commonMethodsUtilService.getLongValueForObject(param[2]);
					  Long cadreVerificationStatusId = param[5] != null ? (Long)param[5]:null;
					  Map<Long,DataMonitoringOverviewVO> tabUserDetailMap = cadreSurveyUserDtlsMap.get(cadreSurveryUserId);
					   if(tabUserDetailMap == null ){
						   tabUserDetailMap = new HashMap<Long, DataMonitoringOverviewVO>();  
						   surveryUserIdNameMap.put(cadreSurveryUserId,commonMethodsUtilService.getStringValueForObject(param[1]));
						   cadreSurveyUserDtlsMap.put(cadreSurveryUserId, tabUserDetailMap);
					   }
					   DataMonitoringOverviewVO tabDtlsVO = tabUserDetailMap.get(tabUserInfoId);
					    if(tabDtlsVO == null ){
					    	tabDtlsVO = new DataMonitoringOverviewVO();
					    	tabDtlsVO.setTabUserId(tabUserInfoId);
					    	tabDtlsVO.setTabUserName(commonMethodsUtilService.getStringValueForObject(param[3]));
					    	tabDtlsVO.setMobileNo(commonMethodsUtilService.getStringValueForObject(param[4]));
					    	if(tabActiveMemberCntMap.get(cadreSurveryUserId) != null ){
					        	if(tabActiveMemberCntMap.get(cadreSurveryUserId).get(tabUserInfoId) != null && tabActiveMemberCntMap.get(cadreSurveryUserId).get(tabUserInfoId) > 0){
						    		tabDtlsVO.setIsActive("Active");	
						    	}
					    	}
					    	tabUserDetailMap.put(tabDtlsVO.getTabUserId(), tabDtlsVO);	
					    }
				       tabDtlsVO.setTotalRegCnt(tabDtlsVO.getTotalRegCnt()+commonMethodsUtilService.getLongValueForObject(param[6]));
					   if(cadreVerificationStatusId == null ){//pending
						   tabDtlsVO.setTotalPendingCnt(commonMethodsUtilService.getLongValueForObject(param[6]));
					   }else if(cadreVerificationStatusId.longValue() == 1l){//approved
						   tabDtlsVO.setTotalVerifyRegCnt(commonMethodsUtilService.getLongValueForObject(param[6]));
					   }else if(cadreVerificationStatusId.longValue() == 2l){//rejected
						   tabDtlsVO.setTotalVerifyRejectCnt(commonMethodsUtilService.getLongValueForObject(param[6]));
					   }
			  }
		  }
	  }catch(Exception e){
		  LOG.error("Exception raised in setTabMembersCntDetails() of DataMonitoringService", e); 
	  }
  }
  	public List<IdNameVO> getDataRejectReason(){
  		LOG.info("Entered into getDataRejectReason() of DataMonitoringService");
  		try{
  			IdNameVO idNameVO = null;
  			List<IdNameVO> idNameVOs = new ArrayList<IdNameVO>();
  			List<DataRejectReason> dataRejectReasons = dataRejectReasonDAO.getAll();
  			if(dataRejectReasons != null && dataRejectReasons.size() > 0){
  				for(DataRejectReason dataRejectReason : dataRejectReasons){
  					idNameVO = new IdNameVO();
  					idNameVO.setId(dataRejectReason.getDataRejectReasonId());
  					idNameVO.setName(dataRejectReason.getRejectReason());
  					idNameVOs.add(idNameVO);
  				}
  			}
  			return idNameVOs;
  		}catch(Exception e){  
  			e.printStackTrace();
  			LOG.error("Exception raised in getDataRejectReason() of DataMonitoringService", e); 
  		}
  		return null;  
  	}
  	public ResultStatus updateRejectDtls(List<IdNameVO> idNameVOs){   
  		ResultStatus resultStatus = new ResultStatus();
  		try{
  			
  			DateUtilService dateUtilService = new DateUtilService();
  			TdpCadreDataVerification tdpCadreDataVerification = new TdpCadreDataVerification();
  			if(idNameVOs.size() > 0){  
  				for(IdNameVO idNameVO : idNameVOs){
  					Long cadreRegUserId = cadreRegUserDAO.getCadreRegUserByUserForDataMonitoring(idNameVO.getId());
  					tdpCadreDataVerification.setTdpCadreId(idNameVO.getCadreId());
  					tdpCadreDataVerification.setVerifiedBy(cadreRegUserId);
  					tdpCadreDataVerification.setDataRejectReasonId(idNameVO.getRejectedCount());
  					tdpCadreDataVerification.setVerifiedTime(dateUtilService.getCurrentDateAndTime());
  					tdpCadreDataVerification.setConstituencyId(idNameVO.getConstitunecyId());
  					tdpCadreDataVerification.setDistrictId(idNameVO.getDistrictId());
  					tdpCadreDataVerification.setCadreSurveyUserId(idNameVO.getCadreUserId());
  					tdpCadreDataVerification.setTabUserInfoId(idNameVO.getTabUserId());
  					Integer count = tdpCadreDAO.updateApprovedCadre(idNameVO.getCadreId(),2l);   
  					tdpCadreDataVerificationDAO.save(tdpCadreDataVerification);     
  				}
  				resultStatus.setResultCode(1);
  	  			resultStatus.setMessage("Updated Successfully");
  			}
  			
  			return resultStatus;  
  		}catch(Exception e){
  			e.printStackTrace();
  			LOG.error("Exception raised in updateRejectDtls() of DataMonitoringService", e); 
  			resultStatus.setResultCode(0);
  			resultStatus.setMessage("Updation Failed");
  			return resultStatus;  
  		}
  		
  	}
  	public ResultStatus updateApproveDtls(List<IdNameVO> idNameVOs){   
  		ResultStatus resultStatus = new ResultStatus();
  		try{
  			
  			DateUtilService dateUtilService = new DateUtilService();
  			TdpCadreDataVerification tdpCadreDataVerification = new TdpCadreDataVerification();
  			if(idNameVOs.size() > 0){
  				for(IdNameVO idNameVO : idNameVOs){
  					Long cadreRegUserId = cadreRegUserDAO.getCadreRegUserByUserForDataMonitoring(idNameVO.getId());
  					tdpCadreDataVerification.setTdpCadreId(idNameVO.getCadreId());
  					tdpCadreDataVerification.setVerifiedBy(cadreRegUserId);
  					tdpCadreDataVerification.setVerifiedTime(dateUtilService.getCurrentDateAndTime());
  					tdpCadreDataVerification.setConstituencyId(idNameVO.getConstitunecyId());
  					tdpCadreDataVerification.setDistrictId(idNameVO.getDistrictId());
  					tdpCadreDataVerification.setCadreSurveyUserId(idNameVO.getCadreUserId());
  					tdpCadreDataVerification.setTabUserInfoId(idNameVO.getTabUserId());
  					Integer count = tdpCadreDAO.updateApprovedCadre(idNameVO.getCadreId(),1l);   
  					tdpCadreDataVerificationDAO.save(tdpCadreDataVerification);      
  				}
  				resultStatus.setResultCode(1);
  	  			resultStatus.setMessage("Updated Successfully");    
  			}
  			
  			return resultStatus;
  		}catch(Exception e){
  			e.printStackTrace();
  			LOG.error("Exception raised in updateRejectDtls() of DataMonitoringService", e); 
  			resultStatus.setResultCode(0);
  			resultStatus.setMessage("Updation Failed");
  			return resultStatus;  
  		}  
  		
  	}
  	
  	public List<CadreRegUserVO> getCadreRegUserAssignedConstituencies(Long userId,String userType,Long districtId){
  		List<CadreRegUserVO> returnList = new ArrayList<CadreRegUserVO>();
  		try {
  			Long cadreRegUserId = cadreRegUserDAO.getCadreRegUserByUserForDataMonitoring(userId);
  			if(cadreRegUserId != null && cadreRegUserId.longValue() > 0l){
  				List<Object[]> list = cadreRegUserTabUserDAO.getUserAssignedConstituencies(cadreRegUserId,districtId);
  				if((list == null || list.isEmpty()) && userType.equalsIgnoreCase("dashboard")){
  					list = cadreRegUserTabUserDAO.getAllAssignedConstituencies(districtId,"DV");
  				}
  				if(list != null && list.size() > 0){
  					for (Object[] obj : list) {
  						CadreRegUserVO vo = new CadreRegUserVO();
  						vo.setId(Long.valueOf(obj[0] != null ? obj[0].toString():"0"));
  						vo.setName(obj[1] != null ? obj[1].toString():"");
  						returnList.add(vo);
  					}
  				}
  			}
  			
  		} catch (Exception e) {
  			LOG.error("Exception occurred at getCadreRegUserAssignedConstituencies() of FieldMonitoringService", e);
  		}
  		return returnList;
  	}

  	public List<CadreRegUserVO> getCadreRegUserAssignedUsers(Long userId,Long constituencyId,String userType){
  		List<CadreRegUserVO> returnList = new ArrayList<CadreRegUserVO>();
  		try {
  			Long cadreRegUserId = cadreRegUserDAO.getCadreRegUserByUserForDataMonitoring(userId);
  			if(cadreRegUserId != null && cadreRegUserId.longValue() > 0l){
  				List<Object[]> list = cadreRegUserTabUserDAO.getUserAssignedUsers(cadreRegUserId,constituencyId);
  				if((list == null || list.isEmpty()) && userType.equalsIgnoreCase("dashboard")){
  					list = cadreRegUserTabUserDAO.getAllUserAssignedUsers(constituencyId,"DV");
  				}
  				if(list != null && list.size() > 0){
  					for (Object[] obj : list) {
  						CadreRegUserVO vo = new CadreRegUserVO();
  						vo.setId(Long.valueOf(obj[0] != null ? obj[0].toString():"0"));
  						vo.setName(obj[1] != null ? obj[1].toString():"");
  						returnList.add(vo);
  					}
  				}
  			}
  			
  		} catch (Exception e) {
  			LOG.error("Exception occurred at getCadreRegUserAssignedConstituencies() of FieldMonitoringService", e);
  		}
  		return returnList;
  	}
  	
  	public List<CadreRegUserVO> getCadreRegUserAssignedDistricts(Long userId,String userType){
  		List<CadreRegUserVO> returnList = new ArrayList<CadreRegUserVO>();
  		try {
  			Long cadreRegUserId = cadreRegUserDAO.getCadreRegUserByUserForDataMonitoring(userId);
  			if(cadreRegUserId != null && cadreRegUserId.longValue() > 0l){
  				List<Object[]> list = cadreRegUserTabUserDAO.getUserAssignedDistricts(cadreRegUserId);
  				if((list == null || list.isEmpty()) && userType.equalsIgnoreCase("dashboard")){
  					list = cadreRegUserTabUserDAO.getAllAssignedDistricts("DV");
  				}
  				if(list != null && list.size() > 0){
  					for (Object[] obj : list) {
  						CadreRegUserVO vo = new CadreRegUserVO();
  						vo.setId(Long.valueOf(obj[0] != null ? obj[0].toString():"0"));
  						vo.setName(obj[1] != null ? obj[1].toString():"");
  						returnList.add(vo);
  					}
  				}
  			}
  			
  		} catch (Exception e) {
  			LOG.error("Exception occurred at getCadreRegUserAssignedConstituencies() of FieldMonitoringService", e);
  		}
  		return returnList;
  	}
  	
  	public String getTabUserImages(){
  		try {
			List<Object[]> list = tabUserInfoDAO.getTotalTabUserImages();
			if(list != null && !list.isEmpty()){
				for (Object[] obj : list) {
					Long id = Long.valueOf(obj[0] != null ? obj[0].toString():"0");
					String imageStr = obj[1] != null ? obj[1].toString():"";
					String imagePath = obj[2] !=null ? obj[2].toString():"";
					String imageName = imagePath.split("/")[2];
					Boolean image = new ImageAndStringConverter().convertBase64StringToImage(imageStr, "D:\\TabUserImages\\"+imageName+"");
					/*if(image)
						return "success";*/
					
				}
			}
			return "success";
		} catch (Exception e) {
			LOG.error("Exception occurred at getTabUserImages() of FieldMonitoringService", e);
			return "failure";
		}
  	}
	public ResultStatus changeImageByVoterImage(List<IdNameVO> idNameVOs){   
  		ResultStatus resultStatus = new ResultStatus();
  		try{
  			
  			DateUtilService dateUtilService = new DateUtilService();
  			if(idNameVOs.size() > 0){  
  				for(IdNameVO idNameVO : idNameVOs){
  					Long cadreRegUserId = cadreRegUserDAO.getCadreRegUserByUserForDataMonitoring(idNameVO.getId());
  					if(idNameVO.getStatus().trim().equalsIgnoreCase("Rejected")){
  						tdpCadreDataVerificationDAO.updateApprovedCadre(idNameVO.getCadreId(),null);
  						Integer count = tdpCadreDAO.updateApprovedCadre(idNameVO.getCadreId(),1l);
  					}else if(idNameVO.getStatus().trim().equalsIgnoreCase("noStatus")){
  						TdpCadreDataVerification tdpCadreDataVerification = new TdpCadreDataVerification();
  						tdpCadreDataVerification.setTdpCadreId(idNameVO.getCadreId());
  	  					tdpCadreDataVerification.setVerifiedBy(cadreRegUserId);
  	  					//tdpCadreDataVerification.setDataRejectReasonId(idNameVO.getRejectedCount());
  	  					tdpCadreDataVerification.setVerifiedTime(dateUtilService.getCurrentDateAndTime());
  	  					tdpCadreDataVerification.setConstituencyId(idNameVO.getConstitunecyId());
  	  					tdpCadreDataVerification.setDistrictId(idNameVO.getDistrictId());
  	  					tdpCadreDataVerification.setCadreSurveyUserId(idNameVO.getCadreUserId());
  	  					tdpCadreDataVerification.setTabUserInfoId(idNameVO.getTabUserId());
  	  					Integer count = tdpCadreDAO.updateApprovedCadre(idNameVO.getCadreId(),1l);   
  	  					tdpCadreDataVerificationDAO.save(tdpCadreDataVerification);  
  					}
  				}
  				resultStatus.setResultCode(1);
  	  			resultStatus.setMessage("Updated Successfully");
  			}
  			
  			return resultStatus;  
  		}catch(Exception e){
  			e.printStackTrace();
  			LOG.error("Exception raised in updateRejectDtls() of DataMonitoringService", e); 
  			resultStatus.setResultCode(0);
  			resultStatus.setMessage("Updation Failed");
  			return resultStatus;  
  		}
  		
  	}
	
	public ResultStatus changeCadreImageByVoterImage(Long tdpCadreId)
	{
		ResultStatus result = new ResultStatus();
		try
		{
			LOG.fatal("Entered into changeCadreImageByVoterImage");
			
			List<Object[]> list = tdpCadreDAO.getCadreImagesByCadreId(tdpCadreId);
			
			if(list != null && list.size() > 0)
			{
				String cadreImage = list.get(0)[0].toString();
				String voterImage = list.get(0)[1].toString();
				
				LOG.fatal("cadre Image - "+cadreImage+"\tVoter Iamge - "+voterImage);
				
				String backupImg = cadreImage.replace(".jpg","");
				backupImg = backupImg+"_"+UUID.randomUUID().toString()+".jpg";
				
				backupImg = "/mnt/tdp-img/cadre_images/2014/backup/"+backupImg;
				cadreImage = "/mnt/tdp-img/cadre_images/2014/"+cadreImage;
				voterImage = "/mnt/tdp-img/voter_images/"+voterImage;
				
				LOG.fatal("backupImg --> "+backupImg);
				LOG.fatal("cadreImage --> "+cadreImage);
				LOG.fatal("voterImage --> "+voterImage);
				
				File cadreImageFile = new File(cadreImage);
				File backupImgFile = new File(backupImg);
				
				/* boolean parentFileStatus = backupImgFile.mkdirs();
				LOG.fatal("parentFileStatus --> "+parentFileStatus);
				
				boolean Copyflag = commonMethodsUtilService.fileCopy(cadreImageFile.getAbsolutePath(),backupImgFile.getAbsolutePath());
				LOG.fatal("Copy to backup --> "+Copyflag);
				
				boolean deleteFlag = cadreImageFile.delete();
				LOG.fatal("Delete Status --> "+deleteFlag);*/
				
				boolean fileMoveStatus = cadreImageFile.renameTo(backupImgFile);
				LOG.fatal("fileMoveStatus --> "+fileMoveStatus);
				
				boolean flag = commonMethodsUtilService.fileCopy(voterImage,cadreImage);
				
				LOG.fatal("File Copy Status --> "+flag);
				
				if(flag)
					result.setResultCode(ResultCodeMapper.SUCCESS);
				else
					result.setResultCode(ResultCodeMapper.FAILURE);
			}
		}catch(Exception e)
		{
			LOG.error("Exception oocured in changeCadreImageByVoterImage Method",e);
			result.setResultCode(ResultCodeMapper.FAILURE);
			result.setExceptionEncountered(e);
		}
		return result;
	}
	
	/*public ResultStatus changeCadreImageByVoterImage(Long tdpCadreId)
	{
		ResultStatus result = new ResultStatus();
		try
		{
			LOG.fatal("Entered into changeCadreImageByVoterImage");
			
			List<Object[]> list = tdpCadreDAO.getCadreImagesByCadreId(tdpCadreId);
			
			if(list != null && list.size() > 0)
			{
				String cadreImage = list.get(0)[0].toString();
				String voterImage = list.get(0)[1].toString();
				
				LOG.fatal("cadre Image - "+cadreImage+"\tVoter Iamge"+voterImage);
				
				String newcadreImg = cadreImage.replace(".jpg","");
				newcadreImg = newcadreImg+"2.jpg";
				
				cadreImage = "/mnt/tdp-img/cadre_images/2014/"+newcadreImg;
				voterImage = "/mnt/tdp-img/voter_images/"+voterImage;
				
				LOG.fatal("cadreImage --> "+cadreImage);
				LOG.fatal("voterImage --> "+voterImage);
				
				boolean flag = commonMethodsUtilService.fileCopy(voterImage,cadreImage);
				
				LOG.fatal("File Copy Status --> "+flag);
				
				if(flag)
				{
					result.setResultCode(ResultCodeMapper.SUCCESS);
					int records = tdpCadreDAO.updateTdpCadreImage(tdpCadreId,newcadreImg);
					LOG.error("Updated Records --> "+records);
				}
				else
					result.setResultCode(ResultCodeMapper.FAILURE);
			}
		}catch(Exception e)
		{
			LOG.error("Exception oocured in changeCadreImageByVoterImage Method",e);
			result.setResultCode(ResultCodeMapper.FAILURE);
			result.setExceptionEncountered(e);
		}
		return result;
	}*/
	
	public String updateRejectedImages(List<IdNameVO> idNameVOs){   
  		try{
  			if(idNameVOs.size() > 0){  
  				for(IdNameVO idNameVO : idNameVOs){
  					tdpCadreDataVerificationDAO.updateApprovedCadre(idNameVO.getCadreId(),null);
  					Integer count = tdpCadreDAO.updateApprovedCadre(idNameVO.getCadreId(),1l);
  				}
  			}
  			return "success";  
  		}catch(Exception e){
  			LOG.error("Exception raised in updateRejectDtls() of DataMonitoringService", e); 
  			return "failure";  
  		}
  	}
}
