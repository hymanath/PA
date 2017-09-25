package com.itgrids.partyanalyst.service.impl;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;

import com.itgrids.partyanalyst.dao.IAlertAssignedOfficerNewDAO;
import com.itgrids.partyanalyst.dao.IAlertCategoryDAO;
import com.itgrids.partyanalyst.dao.IAlertDAO;
import com.itgrids.partyanalyst.dao.IAlertImpactScopeDAO;
import com.itgrids.partyanalyst.dto.LocationAlertVO;
import com.itgrids.partyanalyst.service.IAlertLocationDashboardService;
import com.itgrids.partyanalyst.utils.CommonMethodsUtilService;

public class AlertLocationDashboardService implements IAlertLocationDashboardService{

	private static Logger LOG = Logger.getLogger(AlertLocationDashboardService.class);
	
	private IAlertDAO alertDAO; 
	private CommonMethodsUtilService commonMethodsUtilService;
	private IAlertAssignedOfficerNewDAO alertAssignedOfficerNewDAO;
	private IAlertImpactScopeDAO alertImpactScopeDAO;
	private IAlertCategoryDAO alertCategoryDAO;
	
	
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
	   * Date : 23/06/2017
	   * Author :Teja
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
					//if(alertTypeId == 1l){
						vo = new LocationAlertVO();
						vo.setId(alertTypeId);
						//vo.setStatus("Party");
					//}
						alertTypeList.add(vo);
				}
			}
			//List<Object[]> alertStatusLst = alertDAO.getAlertStatusWiseDetailsForConstituencyInfo(fromDate, toDate, locationValues, alertTypeIds,locationTypeId,year);
			//finalVO.setSubList(getStatusWiseData(alertStatusLst));
			finalVO.setAlertTypeList(alertTypeList);
			List<Object[]> totalAlertCntObjList = alertDAO.getTotalAlertDetailsCount(fromDate, toDate, locationValues, alertTypeIds,locationTypeId,year);
			finalVO.setTotalAlertCount(getRequirdTotalCount(totalAlertCntObjList,alertTypeList));
			
			List<Object[]> alertImpactLevelLst  = alertDAO.getAlertImpactLevelWiseDetailsForConstituencyInfo(fromDate, toDate, locationValues, alertTypeIds,locationTypeId,year,"impactScope");
			finalVO.setImpactScopeList(getImpactLevelData(alertImpactLevelLst,"impactScope",finalVO.getTotalAlertCount()));
			
			List<Object[]> alertCategoryList  = alertDAO.getAlertImpactLevelWiseDetailsForConstituencyInfo(fromDate, toDate, locationValues, alertTypeIds,locationTypeId,year,"alertCategory");
			finalVO.setSubList(getImpactLevelData(alertCategoryList,"alertCategory",finalVO.getTotalAlertCount()));
			
			
			//List<Object[]> involvedMembersObjList = alertCandidateDAO.getInvolvedMemberAlertDetails(fromDate, toDate, locationValues, alertTypeIds,locationTypeId,year);
			//List<Object[]> assignMembersObjList = alertAssignedOfficerNewDAO.getAssignedMemberAlertDetails(fromDate, toDate, locationValues, alertTypeIds,locationTypeId,year);
			
			
			//finalVO.setInvolveMemberCount(getRequirdTotalCount(involvedMembersObjList));
			//finalVO.setAssignedMemberCount(getRequirdTotalCount(assignMembersObjList));
			
		} catch (Exception e) {
			LOG.error("Error occured getTotalAlertDetailsForConstituencyInfo() method of AlertLocationDashboardService",e);
		}
		return finalVO;
	}
	public List<LocationAlertVO> getImpactLevelData(List<Object[]> objList,String type,Long totalCount){
		 List<LocationAlertVO> impactScopeList = null;
		   try {
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
							LocationAlertVO vo = null;
							if(type != null && type.equalsIgnoreCase("impactScope")){
								if(impactLvlId == 5l || impactLvlId == 12l){
									vo = getImpactScopeMatchVO(impactScopeList,5l);
									/*vo.setId(5l);
									vo.setStatus("Mandal/Muncipality Level");*/
								}else if(impactLvlId == 7l || impactLvlId == 9l || impactLvlId == 6l) {
									vo = getImpactScopeMatchVO(impactScopeList,6l);
									/*vo.setId(6l);
									vo.setStatus("Village/Ward/Panchayat Level");*/
								}else if(impactLvlId == 3l || impactLvlId == 4l ) {
									vo = getImpactScopeMatchVO(impactScopeList,3l);
									/*vo.setId(3l);
									vo.setStatus("Constiyuency Level");*/
								}else if(impactLvlId == 13l || impactLvlId == 14l) {
									vo = getImpactScopeMatchVO(impactScopeList,13l);
									/*vo.setId(13l);
									vo.setStatus("Other Level");*/
								}else{
									vo = getImpactScopeMatchVO(impactScopeList,impactLvlId);
									/*vo.setId(impactLvlId);
									vo.setStatus(commonMethodsUtilService.getStringValueForObject(objects[1]));*/
								}
							}else if(type != null && type.equalsIgnoreCase("alertCategory")){
								if(impactLvlId == 6l || impactLvlId == 7l || impactLvlId == 8l || impactLvlId == 9l) {
									vo = getImpactScopeMatchVO(impactScopeList,6l);
									/*vo.setId(6l);
									vo.setStatus("Others");*/
								}else  {
									vo = getImpactScopeMatchVO(impactScopeList,impactLvlId);
									/*vo.setId(impactLvlId);
									vo.setStatus(commonMethodsUtilService.getStringValueForObject(objects[1]));*/
								}
							}
							
							 if (vo != null ){
								 vo.setCount(alertCount);
								 vo.setPercentage(calculatePercantage(vo.getCount(),totalCount));
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
	public List<LocationAlertVO> setImpactScopeSkeletonNew(List<Object[]> scopeDetlsLst,String type){
		List<LocationAlertVO> finalVOList = new ArrayList<LocationAlertVO>();
			if(scopeDetlsLst != null && scopeDetlsLst.size() > 0){
				for (Object[] objects : scopeDetlsLst){
					LocationAlertVO vo = null;
					Long impactLvlId = commonMethodsUtilService.getLongValueForObject(objects[0]);
					if(type != null && type.equalsIgnoreCase("impactScope")){
						if(impactLvlId == 5l || impactLvlId == 12l){
							 vo = getImpactScopeMatchVO(finalVOList,commonMethodsUtilService.getLongValueForObject(5l));
							if(vo == null){
							 vo = new LocationAlertVO();
							 finalVOList.add(vo);
							}
							vo.setId(5l);
							vo.setStatus("Mandal/Muncipality Level");
						}else if(impactLvlId == 7l || impactLvlId == 9l || impactLvlId == 6l) {
							 vo = getImpactScopeMatchVO(finalVOList,commonMethodsUtilService.getLongValueForObject(6l));
							 if(vo == null){
								 vo = new LocationAlertVO();
								 finalVOList.add(vo);
								}
							vo.setId(6l);
							vo.setStatus("Village/Ward/Panchayat Level");
						}else if(impactLvlId == 3l || impactLvlId == 4l ) {
							 vo = getImpactScopeMatchVO(finalVOList,commonMethodsUtilService.getLongValueForObject(3l));
							 if(vo == null){
								 vo = new LocationAlertVO();
								 finalVOList.add(vo);
								}
							vo.setId(3l);
							vo.setStatus("Constiyuency Level");
						}else if(impactLvlId == 13l || impactLvlId == 14l) {
							 vo = getImpactScopeMatchVO(finalVOList,commonMethodsUtilService.getLongValueForObject(13l));
							 if(vo == null){
								 vo = new LocationAlertVO();
								 finalVOList.add(vo);
								}
							vo.setId(13l);
							vo.setStatus("Other Level");
						}else{
							 vo = getImpactScopeMatchVO(finalVOList,commonMethodsUtilService.getLongValueForObject(impactLvlId));
							 if(vo == null){
								 vo = new LocationAlertVO();
								 finalVOList.add(vo);
								}
							vo.setId(impactLvlId);
							vo.setStatus(commonMethodsUtilService.getStringValueForObject(objects[1]));
						}
					}else if(type != null && type.equalsIgnoreCase("alertCategory")){
						if(impactLvlId == 6l || impactLvlId == 7l || impactLvlId == 8l || impactLvlId == 9l) {
							vo = getImpactScopeMatchVO(finalVOList,commonMethodsUtilService.getLongValueForObject(6l));
							if(vo == null){
								 vo = new LocationAlertVO();
								 finalVOList.add(vo);
								}
							vo.setId(6l);
							vo.setStatus("Others");
						}else  {
							vo = getImpactScopeMatchVO(finalVOList,commonMethodsUtilService.getLongValueForObject(impactLvlId));
							if(vo == null){
								 vo = new LocationAlertVO();
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
}
