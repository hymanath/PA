package com.itgrids.core.api.service.impl;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;

import com.itgrids.core.api.service.IAlertLocationDashboardService;
import com.itgrids.partyanalyst.dao.IAlertAssignedDAO;
import com.itgrids.partyanalyst.dao.IAlertAssignedOfficerNewDAO;
import com.itgrids.partyanalyst.dao.IAlertCandidateDAO;
import com.itgrids.partyanalyst.dao.IAlertCategoryDAO;
import com.itgrids.partyanalyst.dao.IAlertDAO;
import com.itgrids.partyanalyst.dao.IAlertImpactScopeDAO;
import com.itgrids.partyanalyst.dao.IAlertStatusDAO;
import com.itgrids.partyanalyst.dto.AlertCoreDashBoardVO;
import com.itgrids.partyanalyst.dto.LocationAlertVO;
import com.itgrids.partyanalyst.model.AlertStatus;
import com.itgrids.partyanalyst.utils.CommonMethodsUtilService;
import com.itgrids.partyanalyst.utils.DateUtilService;

public class AlertLocationDashboardService implements IAlertLocationDashboardService{

	private static Logger LOG = Logger.getLogger(AlertLocationDashboardService.class);
	
	private IAlertDAO alertDAO; 
	private CommonMethodsUtilService commonMethodsUtilService;
	private IAlertAssignedOfficerNewDAO alertAssignedOfficerNewDAO;
	private IAlertImpactScopeDAO alertImpactScopeDAO;
	private IAlertCategoryDAO alertCategoryDAO;
	private IAlertStatusDAO alertStatusDAO;
	private IAlertAssignedDAO alertAssignedDAO;
	private IAlertCandidateDAO alertCandidateDAO;
	private DateUtilService dateUtilService = new DateUtilService();
	
	
	
	public IAlertCandidateDAO getAlertCandidateDAO() {
		return alertCandidateDAO;
	}
	public void setAlertCandidateDAO(IAlertCandidateDAO alertCandidateDAO) {
		this.alertCandidateDAO = alertCandidateDAO;
	}
	public IAlertAssignedDAO getAlertAssignedDAO() {
		return alertAssignedDAO;
	}
	public void setAlertAssignedDAO(IAlertAssignedDAO alertAssignedDAO) {
		this.alertAssignedDAO = alertAssignedDAO;
	}
	public IAlertStatusDAO getAlertStatusDAO() {
		return alertStatusDAO;
	}
	public void setAlertStatusDAO(IAlertStatusDAO alertStatusDAO) {
		this.alertStatusDAO = alertStatusDAO;
	}
	public IAlertCategoryDAO getAlertCategoryDAO() {
		return alertCategoryDAO;
	}
	public void setAlertCategoryDAO(IAlertCategoryDAO alertCategoryDAO) {
		this.alertCategoryDAO = alertCategoryDAO;
	}
	public IAlertImpactScopeDAO getAlertImpactScopeDAO() {
		return alertImpactScopeDAO;
	}
	public void setAlertImpactScopeDAO(IAlertImpactScopeDAO alertImpactScopeDAO) {
		this.alertImpactScopeDAO = alertImpactScopeDAO;
	}
	public IAlertAssignedOfficerNewDAO getAlertAssignedOfficerNewDAO() {
		return alertAssignedOfficerNewDAO;
	}
	public void setAlertAssignedOfficerNewDAO(
			IAlertAssignedOfficerNewDAO alertAssignedOfficerNewDAO) {
		this.alertAssignedOfficerNewDAO = alertAssignedOfficerNewDAO;
	}
	public IAlertDAO getAlertDAO() {
		return alertDAO;
	}
	public void setAlertDAO(IAlertDAO alertDAO) {
		this.alertDAO = alertDAO;
	}
	public CommonMethodsUtilService getCommonMethodsUtilService() {
		return commonMethodsUtilService;
	}
	public void setCommonMethodsUtilService(
			CommonMethodsUtilService commonMethodsUtilService) {
		this.commonMethodsUtilService = commonMethodsUtilService;
	}
	
	/*
	   * Date : 09/10/2017
	   * Author :Hymavathi
	   * @description : getTotalAlertDetailsForConstituencyInfo(Getting Alert details for constituency page)
	   */
	public  LocationAlertVO getTotalAlertDetailsForConstituencyInfo(String fromDateStr ,String toDateStr,List<Long> locationValues,List<Long> alertTypeIds,Long locationTypeId,String year){
		LocationAlertVO finalVO = new LocationAlertVO();
		try {
			Date fromDate = null;
			Date toDate = null;
			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
			if(fromDateStr != null && fromDateStr.trim().length() > 0 && toDateStr != null && toDateStr.trim().length() > 0){
				fromDate = sdf.parse(fromDateStr);
				toDate = sdf.parse(toDateStr);
			}
			List<LocationAlertVO> alertTypeList = new ArrayList<LocationAlertVO>();
			if(alertTypeIds != null && alertTypeIds.size() >0){
				for(Long alertTypeId :alertTypeIds){
					LocationAlertVO vo =null;
					vo = new LocationAlertVO();
						vo.setId(alertTypeId);
						if(alertTypeId == 3l){
							vo.setStatus("Others");
							vo.setColour("#80DFFF");
						}
					alertTypeList.add(vo);
				}
			}
			
			finalVO.setAlertTypeList(alertTypeList);
			List<Object[]> totalAlertCntObjList = alertDAO.getTotalAlertDetailsCount(fromDate, toDate, locationValues, alertTypeIds,locationTypeId,year);
			finalVO.setTotalAlertCount(getRequirdTotalCount(totalAlertCntObjList,alertTypeList));
			
			if(commonMethodsUtilService.isListOrSetValid(finalVO.getAlertTypeList())){
				for (LocationAlertVO alertTypeVO : finalVO.getAlertTypeList()) {
					alertTypeVO.setPercentage(calculatePercantage(alertTypeVO.getCount(),finalVO.getTotalAlertCount()));
				}
			}
			List<Object[]> alertImpactLevelLst  = alertDAO.getAlertImpactLevelWiseDetailsForConstituencyInfo(fromDate, toDate, locationValues, alertTypeIds,locationTypeId,year,"impactScope");
			finalVO.setImpactScopeList(getImpactLevelData(alertImpactLevelLst,"impactScope",finalVO.getTotalAlertCount()));
			
			List<Object[]> alertCategoryList  = alertDAO.getAlertImpactLevelWiseDetailsForConstituencyInfo(fromDate, toDate, locationValues, alertTypeIds,locationTypeId,year,"alertCategory");
			finalVO.setSubList(getImpactLevelData(alertCategoryList,"alertCategory",finalVO.getTotalAlertCount()));
			finalVO.setSubList1(setAlertStatusList(""));
			if(commonMethodsUtilService.isListOrSetValid(finalVO.getSubList())){
				for (LocationAlertVO categoryVO : finalVO.getSubList()) {
					categoryVO.setPercentage(calculatePercantage(categoryVO.getCount(),finalVO.getTotalAlertCount()));
					for (LocationAlertVO statusVO : categoryVO.getSubList()) {
						statusVO.setPercentage(calculatePercantage(statusVO.getCount(),categoryVO.getCount()));
						LocationAlertVO finalStatusVO = getImpactScopeMatchVO(finalVO.getSubList1(),statusVO.getId());
						if(finalStatusVO == null){
							finalStatusVO = new  LocationAlertVO();
							finalStatusVO.setId(0l);
							finalStatusVO.setStatus("OTHERS");
							finalStatusVO.setColour("#80DFFF");
							finalVO.getSubList1().add(finalStatusVO);
						 }
						
							finalStatusVO.setCount(finalStatusVO.getCount()+statusVO.getCount());
							finalStatusVO.setPercentage(calculatePercantage(finalStatusVO.getCount(),finalVO.getTotalAlertCount()));
						
					}
				}
			}
			if(commonMethodsUtilService.isListOrSetValid(finalVO.getImpactScopeList())){
				for (LocationAlertVO impactLevelVO : finalVO.getImpactScopeList()) {
					impactLevelVO.setPercentage(calculatePercantage(impactLevelVO.getCount(),finalVO.getTotalAlertCount()));
					for (LocationAlertVO statusVO : impactLevelVO.getSubList()) {
						statusVO.setPercentage(calculatePercantage(statusVO.getCount(),impactLevelVO.getCount()));
					}
				}
			}
			
		} catch (Exception e) {
			LOG.error("Error occured getTotalAlertDetailsForConstituencyInfo() method of AlertLocationDashboardService",e);
		}
		return finalVO;
	}
	public List<LocationAlertVO> getImpactLevelData(List<Object[]> objList,String type,Long totalCount){
		 List<LocationAlertVO> impactScopeList = null;
		   try {
			   List<Long> alertIds = new ArrayList<Long>();
			   if(type != null && (type.equalsIgnoreCase("alertCategory") || type.equalsIgnoreCase(""))){
					alertIds.add(1l);
					alertIds.add(3l);
					alertIds.add(6l);
					alertIds.add(7l);
					alertIds.add(4l);
				}else if(type != null && type.equalsIgnoreCase("impactScope")){
					alertIds.add(3l);
					alertIds.add(4l);
				}
			   List<Object[]> scopeList = null;
			   if(type != null && type.equalsIgnoreCase("impactScope")){
			      scopeList = alertImpactScopeDAO.getAlertImpactScope();
			   }else if(type != null && type.equalsIgnoreCase("alertCategory")){
				  scopeList = alertCategoryDAO.getAllCategoryOrderBy();
			   }
			    
				 impactScopeList = setImpactScopeSkeletonNew(scopeList,type);
				 if (objList != null && objList.size() > 0 ){
					    for (Object[] param : objList) {
							Long impactLvlId = commonMethodsUtilService.getLongValueForObject(param[0]);
							Long alertCount = commonMethodsUtilService.getLongValueForObject(param[4]);
							Long statusId = commonMethodsUtilService.getLongValueForObject(param[5]);
							LocationAlertVO vo = null;
							if(type != null && type.equalsIgnoreCase("impactScope")){
								if(impactLvlId == 5l || impactLvlId == 12l){
									vo = getImpactScopeMatchVO(impactScopeList,5l);
								}else if(impactLvlId == 7l || impactLvlId == 9l || impactLvlId == 6l) {
									vo = getImpactScopeMatchVO(impactScopeList,6l);
								}else if(impactLvlId == 3l || impactLvlId == 4l ) {
									vo = getImpactScopeMatchVO(impactScopeList,3l);
								}else if(impactLvlId == 13l || impactLvlId == 14l) {
									vo = getImpactScopeMatchVO(impactScopeList,13l);
								}else{
									vo = getImpactScopeMatchVO(impactScopeList,impactLvlId);
								}
							}else if(type != null && type.equalsIgnoreCase("alertCategory")){
								if(impactLvlId == 6l || impactLvlId == 7l || impactLvlId == 8l || impactLvlId == 9l) {
									vo = getImpactScopeMatchVO(impactScopeList,6l);
								}else  {
									vo = getImpactScopeMatchVO(impactScopeList,impactLvlId);
								}
							}
							
							 if (vo != null ){
								 vo.setCount(vo.getCount()+alertCount);
								 LocationAlertVO statusVO = null;
								 if(alertIds.contains(statusId)){
									 statusVO = getImpactScopeMatchVO(vo.getSubList(),statusId);
								 }else{
									 statusVO = getImpactScopeMatchVO(vo.getSubList(),0l);
								 }
								 
								 if(statusVO == null){
									 statusVO = new  LocationAlertVO();
									 statusVO.setId(0l);
									 statusVO.setStatus("OTHERS");
									 statusVO.setColour("#80DFFF");
									 vo.getSubList().add(statusVO);
								 }
								 statusVO.setCount(statusVO.getCount()+alertCount);
								 //vo.setPercentage(calculatePercantage(vo.getCount(),totalCount));
							 }
						}
				   }
			   
		   }catch(Exception e ){
			   LOG.error("Exception occured at getImpactLevelData( )",e);
		   }
		return impactScopeList;
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
	public Long getRequirdTotalCount(List<Object[]> objList ,List<LocationAlertVO> alertTypeIdList){
		Long totalCount = 0l;
		 try {
			  if (objList != null && objList.size() > 0){
				  for (Object[] param : objList) {
					  LocationAlertVO matchVO = getImpactScopeMatchVO(alertTypeIdList,commonMethodsUtilService.getLongValueForObject(param[0]));
						 if (matchVO != null ){
							 	totalCount = totalCount + commonMethodsUtilService.getLongValueForObject(param[2]);
							 	matchVO.setStatus(commonMethodsUtilService.getStringValueForObject(param[1]));
							 	matchVO.setCount(matchVO.getCount()+commonMethodsUtilService.getLongValueForObject(param[2]));
						 }
				}
			  }
		 } catch (Exception e){
			 LOG.error("Error occured getTotalCount() method of AlertLocationDashboardService",e);
		 }
		 return totalCount;
	}
	public  List<LocationAlertVO> setAlertStatusList(String type){
		List<LocationAlertVO> statusVos = new ArrayList<LocationAlertVO>();
		try{
			List<AlertStatus> statusList = alertStatusDAO.getAll();
			List<Long> alertIds = new ArrayList<Long>();
			if(type != null && (type.equalsIgnoreCase("alertCategory") || type.equalsIgnoreCase(""))){
				alertIds.add(1l);
				alertIds.add(3l);
				alertIds.add(6l);
				alertIds.add(7l);
				alertIds.add(4l);
			}else if(type != null && type.equalsIgnoreCase("impactScope")){
				alertIds.add(3l);
				alertIds.add(4l);
			}
			
			for (AlertStatus alertStatus : statusList) {
				
				if(alertIds.contains(alertStatus.getAlertStatusId())){
					LocationAlertVO statusVO = new LocationAlertVO();
					statusVO.setId(alertStatus.getAlertStatusId());
					statusVO.setStatus(alertStatus.getAlertStatus());
					statusVO.setColour(alertStatus.getColor());
					statusVos.add(statusVO);
				}/*else{
					statusVO.setId(0l);
					statusVO.setStatus("OTHERS");
				}*/
					
				}
			
		}catch (Exception e) {
			LOG.error("Error occured setAlertStatusList() method of AlertLocationDashboardService",e);
		}
		return statusVos;
	}
	public List<LocationAlertVO> setImpactScopeSkeletonNew(List<Object[]> scopeDetlsLst,String type){
		List<LocationAlertVO> finalVOList = new ArrayList<LocationAlertVO>();
		
		if(scopeDetlsLst != null && scopeDetlsLst.size() > 0){
			
				for (Object[] objects : scopeDetlsLst){
					LocationAlertVO otherStatusVO = new LocationAlertVO();
					otherStatusVO.setId(0l);
					otherStatusVO.setStatus("OTHERS");
					otherStatusVO.setColour("#80DFFF");
					LocationAlertVO vo = null;
					Long impactLvlId = commonMethodsUtilService.getLongValueForObject(objects[0]);
					if(type != null && type.equalsIgnoreCase("impactScope")){
						
						if(impactLvlId == 5l || impactLvlId == 12l){
							 vo = getImpactScopeMatchVO(finalVOList,commonMethodsUtilService.getLongValueForObject(5l));
							if(vo == null){
							 vo = new LocationAlertVO();
							 vo.setSubList(setAlertStatusList(type));
							 vo.getSubList().add(otherStatusVO);
							 finalVOList.add(vo);
							}
							vo.setId(5l);
							vo.setStatus("Mandal/Muncipality Level");
						}else if(impactLvlId == 7l || impactLvlId == 9l || impactLvlId == 6l) {
							 vo = getImpactScopeMatchVO(finalVOList,commonMethodsUtilService.getLongValueForObject(6l));
							 if(vo == null){
								 vo = new LocationAlertVO();
								 vo.setSubList(setAlertStatusList(type));
								 vo.getSubList().add(otherStatusVO);
								 finalVOList.add(vo);
								}
							vo.setId(6l);
							vo.setStatus("Village/Ward/Panchayat Level");
						}else if(impactLvlId == 3l || impactLvlId == 4l ) {
							 vo = getImpactScopeMatchVO(finalVOList,commonMethodsUtilService.getLongValueForObject(3l));
							 if(vo == null){
								 vo = new LocationAlertVO();
								 vo.setSubList(setAlertStatusList(type));
								 vo.getSubList().add(otherStatusVO);
								 finalVOList.add(vo);
								}
							vo.setId(3l);
							vo.setStatus("Constituency Level");
						}else if(impactLvlId == 13l || impactLvlId == 14l) {
							 vo = getImpactScopeMatchVO(finalVOList,commonMethodsUtilService.getLongValueForObject(13l));
							 if(vo == null){
								 vo = new LocationAlertVO();
								 vo.setSubList(setAlertStatusList(type));
								 vo.getSubList().add(otherStatusVO);
								 finalVOList.add(vo);
								}
							vo.setId(13l);
							vo.setStatus("Other Level");
						}else if(impactLvlId != 11l ){
							 vo = getImpactScopeMatchVO(finalVOList,commonMethodsUtilService.getLongValueForObject(impactLvlId));
							 if(vo == null){
								 vo = new LocationAlertVO();
								 vo.setSubList(setAlertStatusList(type));
								 vo.getSubList().add(otherStatusVO);
								 finalVOList.add(vo);
								}
							vo.setId(impactLvlId);
							vo.setStatus(commonMethodsUtilService.getStringValueForObject(objects[1]));
						}
					}else if(type != null && type.equalsIgnoreCase("alertCategory")){
						if(impactLvlId.longValue() == 6l || impactLvlId.longValue() == 7l || impactLvlId.longValue() == 8l || impactLvlId.longValue() == 9l
								 || impactLvlId.longValue() == 4l || impactLvlId.longValue() == 5l ) {
							vo = getImpactScopeMatchVO(finalVOList,commonMethodsUtilService.getLongValueForObject(6l));
							if(vo == null){
								 vo = new LocationAlertVO();
								 vo.setSubList(setAlertStatusList(type));
								 vo.getSubList().add(otherStatusVO);
								 finalVOList.add(vo);
								}
							vo.setId(6l);
							vo.setStatus("Others");
						}else  {
							vo = getImpactScopeMatchVO(finalVOList,commonMethodsUtilService.getLongValueForObject(impactLvlId));
							if(vo == null){
								 vo = new LocationAlertVO();
								 vo.setSubList(setAlertStatusList(type));
								 vo.getSubList().add(otherStatusVO);
								 finalVOList.add(vo);
								}
							vo.setId(impactLvlId);
							vo.setStatus(commonMethodsUtilService.getStringValueForObject(objects[1]));
						}
					}
						
						
			   }
			}
		return finalVOList;
	}
	public LocationAlertVO getImpactScopeMatchVO(List<LocationAlertVO> impactScopeList,Long impactScopeId ){
		try {
			 if (impactScopeList == null && impactScopeList.size() == 0 )
				 return null;
			 for (LocationAlertVO locationAlertVO : impactScopeList) {
				  if (locationAlertVO.getId().equals(impactScopeId)){
					  return locationAlertVO;
				  }
			}
		} catch (Exception e){
			LOG.error("Exception occured at getImpactScopeMatchVO( )",e);
		}
		return null;
	}
	
	/*
	   * Date : 09/10/2017
	   * Author :Hymavathi
	   * @description : getDesignationWiseAlertsOverview(Showing Designation Wise Alert details for constituency page)
	   */
	public  List<LocationAlertVO> getDesignationWiseAlertsOverview(String fromDateStr ,String toDateStr,List<Long> locationValues,List<Long> alertTypeIds,Long locationTypeId,String year){
		List<LocationAlertVO> finalVOs = new ArrayList<LocationAlertVO>();
		try {
			Date fromDate = null;
			Date toDate = null;
			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
			if(fromDateStr != null && fromDateStr.trim().length() > 0 && toDateStr != null && toDateStr.trim().length() > 0){
				fromDate = sdf.parse(fromDateStr);
				toDate = sdf.parse(toDateStr);
			}
			
			List<Object[]> assignedAlerts = alertAssignedDAO.getDesignationWiseAssignedAlertys(fromDate, toDate, locationValues, alertTypeIds, locationTypeId, year);
			List<Object[]> involvedAlerts = alertCandidateDAO.getDesignationWiseInvolvedAlertys(fromDate, toDate, locationValues, alertTypeIds, locationTypeId, year);
			setDesignationWiseAlerts(assignedAlerts,finalVOs,"assigned");
			setDesignationWiseAlerts(involvedAlerts,finalVOs,"involved");
			
			if(commonMethodsUtilService.isListOrSetValid(finalVOs)){
				for (LocationAlertVO desigVO : finalVOs) {
					desigVO.setTotalAlertCount(desigVO.getCount()+desigVO.getAlertCount());
					if(commonMethodsUtilService.isListOrSetValid(desigVO.getSubList())){
						for(LocationAlertVO statusVO : desigVO.getSubList()){
							statusVO.setPercentage(calculatePercantage(statusVO.getCount(), desigVO.getAlertCount()));
						}
					}
					
					if(commonMethodsUtilService.isListOrSetValid(desigVO.getSubList1())){
						for(LocationAlertVO statusVO : desigVO.getSubList1()){
							statusVO.setPercentage(calculatePercantage(statusVO.getCount(), desigVO.getCount()));
						}
					}
					desigVO.setPercentage(calculatePercantage(desigVO.getCount(), desigVO.getTotalAlertCount()));//involvedPerc
					desigVO.setPercentage1(calculatePercantage(desigVO.getAlertCount(), desigVO.getTotalAlertCount()));//assigndPerc
				}
			}
			
		}catch (Exception e) {
			e.printStackTrace();
			LOG.error("Exception occured at getDesignationWiseAlertsOverview( )",e);
		}
		return finalVOs;
	}
	/*
	   * Date : 10/10/2017
	   * Author :Hymavathi
	   * @description : setDesignationWiseAlerts(Showing Designation Wise Alert details for constituency page)
	   */
	public void setDesignationWiseAlerts(List<Object[]> assignedAlerts,List<LocationAlertVO> finalVOs,String type){
		try{
			if(commonMethodsUtilService.isListOrSetValid(assignedAlerts)){
				for (Object[] param : assignedAlerts) {
					
					LocationAlertVO statusVO = null;
					Long statusId =commonMethodsUtilService.getLongValueForObject(param[0]);
					LocationAlertVO designationVO = getImpactScopeMatchVO(finalVOs,commonMethodsUtilService.getLongValueForObject(param[3]));
					if(designationVO == null){
							designationVO = new LocationAlertVO();
							designationVO.setId(commonMethodsUtilService.getLongValueForObject(param[3]));
							designationVO.setStatus(commonMethodsUtilService.getStringValueForObject(param[4]));
							LocationAlertVO otherStatusVO = new LocationAlertVO();
								otherStatusVO.setId(0l);
								otherStatusVO.setStatus("OTHERS");
								otherStatusVO.setColour("#80DFFF");
							LocationAlertVO otherStatusVO1 = new LocationAlertVO();
								otherStatusVO1.setId(0l);
								otherStatusVO1.setStatus("OTHERS");
								otherStatusVO1.setColour("#80DFFF");
							designationVO.setSubList(setAlertStatusList("impactScope"));
								designationVO.getSubList().add(otherStatusVO);
							designationVO.setSubList1(setAlertStatusList("impactScope"));
								designationVO.getSubList1().add(otherStatusVO1);
							finalVOs.add(designationVO);
					}
					
					if(statusId.longValue() != 3l && statusId.longValue() != 4l){
						statusId =0l;
					}
					if(type != null && type.equalsIgnoreCase("assigned")){
						designationVO.setAlertCount(designationVO.getAlertCount()+commonMethodsUtilService.getLongValueForObject(param[5]));
						statusVO = getImpactScopeMatchVO(designationVO.getSubList(),statusId);
					}else if(type != null && type.equalsIgnoreCase("involved")){
						designationVO.setCount(designationVO.getCount()+commonMethodsUtilService.getLongValueForObject(param[5]));
						statusVO = getImpactScopeMatchVO(designationVO.getSubList1(),statusId);
					}
					statusVO.setCount(statusVO.getCount()+commonMethodsUtilService.getLongValueForObject(param[5]));
				}
			}
		}catch (Exception e) {
			e.printStackTrace();
			LOG.error("Exception occured at setDesignationWiseAlerts( )",e);
		}
	}
	public  List<AlertCoreDashBoardVO> getAlertOverviewClick(String fromDateStr,String toDateStr,List<Long> locationValues,List<Long> alertTypeIds,Long locationTypeId,String year,
			List<Long> statusIdsList,List<Long> impactIdsList,String type,Long designationId,List<Long> alertCategeryIdsList,String otherCategory){
		List<AlertCoreDashBoardVO> finalVOs = new ArrayList<AlertCoreDashBoardVO>();
		try {
			Date fromDate = null;
			Date toDate = null;
			List<Object[]> alertList=null;
			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
			if(fromDateStr != null && fromDateStr.trim().length() > 0 && toDateStr != null && toDateStr.trim().length() > 0){
				fromDate = sdf.parse(fromDateStr);
				toDate = sdf.parse(toDateStr);
			}
			
			if(impactIdsList != null && impactIdsList.size() > 0){
				if(impactIdsList.get(0).longValue() == 5l){
					impactIdsList.add(12l);
				}else if(impactIdsList.get(0).longValue() == 6l){
					impactIdsList.add(7l);
					impactIdsList.add(9l);
				}else if(impactIdsList.get(0).longValue() == 3l){
					impactIdsList.add(4l);
				}else if(impactIdsList.get(0).longValue() == 13l){
					impactIdsList.add(14l);
				}
					
			}
			if(type != null && type.equalsIgnoreCase("total")){
				alertList = alertAssignedDAO.getDesignationWiseAssignedAlerts(fromDate, toDate, locationValues, alertTypeIds, locationTypeId, year,otherCategory,statusIdsList,designationId);
			List<Object[]>	alertCandidateList = alertCandidateDAO.getDesignationWiseInvolvedAlerts(fromDate, toDate, locationValues, alertTypeIds, locationTypeId, year,otherCategory,statusIdsList,designationId);
				alertList.addAll(alertCandidateList);
			}else if(otherCategory != null && otherCategory.equalsIgnoreCase("candidateAssignedOthers") || (type != null && type.equalsIgnoreCase("assigned"))){
			 alertList = alertAssignedDAO.getDesignationWiseAssignedAlerts(fromDate, toDate, locationValues, alertTypeIds, locationTypeId, year,otherCategory,statusIdsList,designationId);
			}else if(otherCategory != null && otherCategory.equalsIgnoreCase("candidateInvolvedOthers") || (type != null && type.equalsIgnoreCase("involved"))){
			 alertList = alertCandidateDAO.getDesignationWiseInvolvedAlerts(fromDate, toDate, locationValues, alertTypeIds, locationTypeId, year,otherCategory,statusIdsList,designationId);
			}else{
			 alertList = alertDAO.getAlertCategaryAndImpactLevelWiseDetailsOverView(fromDate, toDate, locationValues, alertTypeIds,locationTypeId,year,type,alertCategeryIdsList,statusIdsList,impactIdsList,otherCategory);
			}
			if(alertList != null && alertList.size()>0){
			    setAlertDtls(finalVOs, alertList);
			}
			
			
		}catch (Exception e) {
			e.printStackTrace();
			LOG.error("Exception occured at getAlertOverviewClick( )",e);
		}
		return finalVOs;
	}
	public void setAlertDtls(List<AlertCoreDashBoardVO> finalVOs, List<Object[]> alertList){
		try{
			SimpleDateFormat myFormat = new SimpleDateFormat("yyyy-MM-dd");
			Date today = dateUtilService.getCurrentDateAndTime();
			String td = myFormat.format(today);
			Long dist = 0l;
			Long statusId = 0L;  
			AlertCoreDashBoardVO alertCoreDashBoardVO = null;  
			String alertSource = "";
			if(alertList != null && alertList.size() > 0){  
				for(Object[] param : alertList ){
					alertCoreDashBoardVO = new AlertCoreDashBoardVO();
					alertCoreDashBoardVO.setId(commonMethodsUtilService.getLongValueForObject(param[0]));
					alertCoreDashBoardVO.setCreatedDate(commonMethodsUtilService.getStringValueForObject(param[1]).substring(0, 10));
					alertCoreDashBoardVO.setUpdatedDate(commonMethodsUtilService.getStringValueForObject(param[2]).substring(0, 10));
					alertCoreDashBoardVO.setStatusId(commonMethodsUtilService.getLongValueForObject(param[3]));
					alertCoreDashBoardVO.setStatus(commonMethodsUtilService.getStringValueForObject(param[4]));
					alertCoreDashBoardVO.setSevertyColor(commonMethodsUtilService.getStringValueForObject(param[24]));
					alertCoreDashBoardVO.setStatusColor(commonMethodsUtilService.getStringValueForObject(param[25]));
					if(param.length > 26){
						alertCoreDashBoardVO.setProblem(commonMethodsUtilService.getStringValueForObject(param[26]));
						alertCoreDashBoardVO.setRelatedTo(commonMethodsUtilService.getStringValueForObject(param[27]));
					}
					statusId = commonMethodsUtilService.getLongValueForObject(param[3]);
					if(param[1] != null && param[2] != null){
						if(statusId == 4L || statusId == 5L || statusId == 6L || statusId == 7L){
							dist = dateUtilService.noOfDayBetweenDates(commonMethodsUtilService.getStringValueForObject(param[1]).substring(0, 10),commonMethodsUtilService.getStringValueForObject(param[2]).substring(0, 10));
						}else{
							dist = dateUtilService.noOfDayBetweenDates(commonMethodsUtilService.getStringValueForObject(param[1]).substring(0, 10),td);
						}  
						alertCoreDashBoardVO.setInterval(dist);
					}
					alertCoreDashBoardVO.setAlertLevel(commonMethodsUtilService.getStringValueForObject(param[8]));
					alertCoreDashBoardVO.setTitle(commonMethodsUtilService.getStringValueForObject(param[9]));    
					
					if(param[23] != null){
						alertCoreDashBoardVO.setLocation(commonMethodsUtilService.getStringValueForObject(param[23]));	
					}else if(param[22] != null){
						alertCoreDashBoardVO.setLocation(commonMethodsUtilService.getStringValueForObject(param[22]));	
					}else if(param[10] != null){
						alertCoreDashBoardVO.setLocation(commonMethodsUtilService.getStringValueForObject(param[10]));	
					}else if(param[11] != null){
						alertCoreDashBoardVO.setLocation(commonMethodsUtilService.getStringValueForObject(param[11]));	
					}else if(param[20] != null){
						alertCoreDashBoardVO.setLocation(commonMethodsUtilService.getStringValueForObject(param[20]));
					}
					//hiii
					   if(commonMethodsUtilService.getLongValueForObject(param[5]).longValue() == 2L){//print
							if(param[17] != null){
								alertSource = commonMethodsUtilService.getStringValueForObject(param[17]);
							}else{
								alertSource = commonMethodsUtilService.getStringValueForObject(param[13]);
							}
							 
						}else if(commonMethodsUtilService.getLongValueForObject(param[5]).longValue() == 3L){//electronic 
							if(param[19] != null){
								alertSource = commonMethodsUtilService.getStringValueForObject(param[19]);
							}else{
								alertSource = commonMethodsUtilService.getStringValueForObject(param[13]);
							}
						}else{
							alertSource = commonMethodsUtilService.getStringValueForObject(param[13]);//for social media,call center and other category
						}
						alertCoreDashBoardVO.setSource(alertSource);
					 
						finalVOs.add(alertCoreDashBoardVO);
					
				}  
			}
		}catch(Exception e){
			e.printStackTrace();
		}
	}
}
