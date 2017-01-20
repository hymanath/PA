package com.itgrids.partyanalyst.service.impl;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;

import org.apache.log4j.Logger;

import com.itgrids.partyanalyst.dao.IActivityMemberAccessLevelDAO;
import com.itgrids.partyanalyst.dao.IAlertCategoryDAO;
import com.itgrids.partyanalyst.dao.IAlertDAO;
import com.itgrids.partyanalyst.dao.IAlertImpactScopeDAO;
import com.itgrids.partyanalyst.dao.IAlertStatusDAO;
import com.itgrids.partyanalyst.dao.IAlertTypeDAO;
import com.itgrids.partyanalyst.dao.IEditionTypeDAO;
import com.itgrids.partyanalyst.dto.AlertCoreDashBoardVO;
import com.itgrids.partyanalyst.dto.AlertOverviewVO;
import com.itgrids.partyanalyst.service.IAlertsNewsPortalService;
import com.itgrids.partyanalyst.utils.CommonMethodsUtilService;
import com.itgrids.partyanalyst.utils.DateUtilService;

public class AlertsNewsPortalService implements IAlertsNewsPortalService{

	private static final Logger LOG = Logger.getLogger(AlertsNewsPortalService.class);
	
	private IActivityMemberAccessLevelDAO activityMemberAccessLevelDAO;
	private CommonMethodsUtilService commonMethodsUtilService = new CommonMethodsUtilService();
	private IAlertDAO alertDAO;
	private IAlertTypeDAO alertTypeDAO;
	private IEditionTypeDAO editionTypeDAO;
	private IAlertStatusDAO alertStatusDAO;
	private IAlertCategoryDAO alertCategoryDAO;
	private IAlertImpactScopeDAO alertImpactScopeDAO;
	private DateUtilService dateUtilService = new DateUtilService();
	
	
	public IActivityMemberAccessLevelDAO getActivityMemberAccessLevelDAO() {
		return activityMemberAccessLevelDAO;
	}

	public void setActivityMemberAccessLevelDAO(
			IActivityMemberAccessLevelDAO activityMemberAccessLevelDAO) {
		this.activityMemberAccessLevelDAO = activityMemberAccessLevelDAO;
	}

	public CommonMethodsUtilService getCommonMethodsUtilService() {
		return commonMethodsUtilService;
	}

	public void setCommonMethodsUtilService(
			CommonMethodsUtilService commonMethodsUtilService) {
		this.commonMethodsUtilService = commonMethodsUtilService;
	}

	public IAlertDAO getAlertDAO() {
		return alertDAO;
	}

	public void setAlertDAO(IAlertDAO alertDAO) {
		this.alertDAO = alertDAO;
	}

	public IAlertTypeDAO getAlertTypeDAO() {
		return alertTypeDAO;
	}

	public void setAlertTypeDAO(IAlertTypeDAO alertTypeDAO) {
		this.alertTypeDAO = alertTypeDAO;
	}

	public IEditionTypeDAO getEditionTypeDAO() {
		return editionTypeDAO;
	}

	public void setEditionTypeDAO(IEditionTypeDAO editionTypeDAO) {
		this.editionTypeDAO = editionTypeDAO;
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
	
	

	public AlertOverviewVO getAlertOverviewDetails(Long activityMemberId,Long stateId,String fromDateStr,String toDateStr, Long alertType,Long editionType){
		  
		   AlertOverviewVO resultVO = new AlertOverviewVO();
		   Set<Long> locationValues = new HashSet<Long>(0);
		   Long locationAccessLevelId =0l;
		   SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		   Date fromDate=null;
		   Date toDate = null;
		   List<Long> alertTypes = new ArrayList<Long>();
		   alertTypes.add(alertType);
		   List<Long> alertEditions = new ArrayList<Long>();
		   if(editionType.longValue() == 2L){
			   alertEditions.add(editionType);
			   alertEditions.add(3L);
		   }else{
			   alertEditions.add(editionType);
		   }
		   alertEditions.add(editionType);
		   try{
			   if(fromDateStr != null && !fromDateStr.isEmpty() && fromDateStr.length() > 0l && toDateStr != null && !toDateStr.isEmpty() && toDateStr.length() > 0){
				   fromDate = sdf.parse(fromDateStr);
				   toDate = sdf.parse(toDateStr);
				 }
			   	 List<Object[]> rtrnUsrAccssLvlIdAndVlusObjLst=activityMemberAccessLevelDAO.getLocationLevelAndValuesByActivityMembersIdForOrganization(activityMemberId);
				 if(rtrnUsrAccssLvlIdAndVlusObjLst != null && rtrnUsrAccssLvlIdAndVlusObjLst.size() > 0){
					 locationAccessLevelId=(Long) rtrnUsrAccssLvlIdAndVlusObjLst.get(0)[0];
					 for(Object[] param:rtrnUsrAccssLvlIdAndVlusObjLst){
						 locationValues.add(commonMethodsUtilService.getLongValueForObject(param[1]));
					 }
				 }
			   List<Object[]> rtrnTtlAlrtObjLst = alertDAO.getAlertCntByAlertTypeBasedOnUserAccessLevelForOrganization(locationAccessLevelId, locationValues, stateId, fromDate, toDate, "false",alertTypes,alertEditions);
			   Long totalAlertCnt = 0l;
			   AlertOverviewVO overViewVO = new AlertOverviewVO();
			   if(rtrnTtlAlrtObjLst != null && !rtrnTtlAlrtObjLst.isEmpty() ){
				   for(Object[] param:rtrnTtlAlrtObjLst){
					   Long statusId = commonMethodsUtilService.getLongValueForObject(param[0]);
					   Long alertCnt = commonMethodsUtilService.getLongValueForObject(param[2]); 
					   totalAlertCnt = totalAlertCnt + alertCnt;
					   if(statusId == 1l){//Party
						   overViewVO.setPartyAlertCnt(alertCnt);
					   }else if(statusId == 3l){//Other
						   overViewVO.setOtherAlertCnt(alertCnt);
					   }else if(statusId == 2l){
						   overViewVO.setGovtAlertCnt(alertCnt);
					   }
				   }
				   overViewVO.setTotalAlertCnt(totalAlertCnt);
			   }
			   overViewVO.setPartyAlertCntPer(calculatePercantage(overViewVO.getPartyAlertCnt(), totalAlertCnt));
			   overViewVO.setOtherAlertCntPer(calculatePercantage(overViewVO.getOtherAlertCnt(), totalAlertCnt));
			   overViewVO.setGovtAlertCntPer(calculatePercantage(overViewVO.getGovtAlertCnt(), totalAlertCnt));
			   //1
			   //for edition
			   Map<Long,AlertOverviewVO> alertTypeAndEditionDtlsVoMap = new HashMap<Long,AlertOverviewVO>();
			   List<Object[]> editionTypeList = editionTypeDAO.getEditionTypeList();
			   List<Object[]> alertTypeList = alertTypeDAO.getAlertTypeForOrganization();
			   prepareTempForAlertTypeAndEdition(editionTypeList,alertTypeList,alertTypeAndEditionDtlsVoMap);
			   
			   List<Object[]> rtrnTtlAlrtGrpByEditionObjLst = alertDAO.getAlertCntByAlertTypeBasedOnUserAccessLevelForOrganization(locationAccessLevelId, locationValues, stateId, fromDate, toDate, "true",alertTypes,alertEditions);
			   
			   if(rtrnTtlAlrtGrpByEditionObjLst != null && rtrnTtlAlrtGrpByEditionObjLst.size() > 0){
				   for(Object[] param : rtrnTtlAlrtGrpByEditionObjLst){
					   Long alertTypeId = commonMethodsUtilService.getLongValueForObject(param[0]);
					   Long editionId = commonMethodsUtilService.getLongValueForObject(param[2]);
					   Long alertcnt = commonMethodsUtilService.getLongValueForObject(param[4]);
					   if(alertTypeAndEditionDtlsVoMap.get(alertTypeId) != null ){
						   AlertOverviewVO alertTypeVO = getMatchVOForEdition(alertTypeAndEditionDtlsVoMap.get(alertTypeId).getEditionList(),editionId);
						   if(alertTypeVO != null ){
							   alertTypeVO.setEditionCnt(alertcnt);
							   alertTypeVO.setAlertTypeId(alertTypeId);  
						   }
					   } 
				   }  
			   }
			   //2  
			   Map<Long,AlertOverviewVO> alertStatusMap = new HashMap<Long, AlertOverviewVO>(0);
			   List<Object[]> rtrnAlrtStatusObjLst = alertStatusDAO.getAllStatus();
			   List<Object[]> rtrnAlrtSttsWsCntObjLst = alertDAO.getAlertCntByAlertStatusBasedOnUserAccessLevelForOrganization(locationAccessLevelId, locationValues, stateId, fromDate, toDate, "false",alertTypes,alertEditions);
			   prepareTemplateStatusWise(rtrnAlrtStatusObjLst,alertStatusMap);//Prepare Template 
			   if(rtrnAlrtSttsWsCntObjLst != null && rtrnAlrtSttsWsCntObjLst.size() > 0){
				   for(Object[] param:rtrnAlrtSttsWsCntObjLst){
					    if(alertStatusMap.get(commonMethodsUtilService.getLongValueForObject(param[0])) != null ){
					    	alertStatusMap.get(commonMethodsUtilService.getLongValueForObject(param[0])).setStatusCnt(commonMethodsUtilService.getLongValueForObject(param[2]));
					    	alertStatusMap.get(commonMethodsUtilService.getLongValueForObject(param[0])).setStatusCntPer(calculatePercantage(alertStatusMap.get(commonMethodsUtilService.getLongValueForObject(param[0])).getStatusCnt(), totalAlertCnt));
					    }
				   }  
			   } 
			   //modified code
			   Map<Long,AlertOverviewVO> statusTypeAndEditionDtlsVoMap = new HashMap<Long,AlertOverviewVO>();
			   prepareTempForAlertTypeAndEdition(editionTypeList,rtrnAlrtStatusObjLst,statusTypeAndEditionDtlsVoMap);
			   List<Object[]> rtrnAlrtSttsAndEditionWsCntObjLst = alertDAO.getAlertCntByAlertStatusBasedOnUserAccessLevelForOrganization(locationAccessLevelId, locationValues, stateId, fromDate, toDate, "true",alertTypes,alertEditions);
			   
			   if(rtrnAlrtSttsAndEditionWsCntObjLst != null && rtrnAlrtSttsAndEditionWsCntObjLst.size() > 0){
				   for(Object[] param : rtrnAlrtSttsAndEditionWsCntObjLst){
					   Long statusTypeId = commonMethodsUtilService.getLongValueForObject(param[0]);
					   Long editionId = commonMethodsUtilService.getLongValueForObject(param[2]);
					   Long alertcnt = commonMethodsUtilService.getLongValueForObject(param[4]);
					   if(statusTypeAndEditionDtlsVoMap.get(statusTypeId) != null ){
						   AlertOverviewVO alertTypeVO = getMatchVOForEdition(statusTypeAndEditionDtlsVoMap.get(statusTypeId).getEditionList(),editionId);
						   if(alertTypeVO != null ){
							   alertTypeVO.setEditionCnt(alertcnt);
						   }
					   }
				   }
			   }
			   
			   
			   
			   
			   Map<Long,AlertOverviewVO> alertCategoryMap = new ConcurrentHashMap<Long,AlertOverviewVO>();
			   
			   List<Object[]> rtrnAlertCategoryObjLst = alertCategoryDAO.getAllCategoryOrderBy();
			   prepareAlertCategoryTemplate(rtrnAlertCategoryObjLst,rtrnAlrtStatusObjLst,alertCategoryMap);//Prepare Template ddddd
			   
			   List<Object[]> rtrnAlrCtgryCntobjLst = alertDAO.getAlertCntByAlertCategoryBasedOnUserAccessLevelForOrganization(locationAccessLevelId, locationValues, stateId, fromDate, toDate,"false",alertTypes,alertEditions);
			 
			   if(rtrnAlrCtgryCntobjLst != null && !rtrnAlrCtgryCntobjLst.isEmpty() ){  
				  for(Object[] param:rtrnAlrCtgryCntobjLst)  {
					  if(alertCategoryMap.get(commonMethodsUtilService.getLongValueForObject(param[0])) != null ){
						  alertCategoryMap.get(commonMethodsUtilService.getLongValueForObject(param[0])).setStatusCnt(commonMethodsUtilService.getLongValueForObject(param[2]));
					    } 
				  }

			   }
			   List<Object[]> rtrnAlrtCtgryAndSttsWseCntObjLst = alertDAO.getAlertCntByAlertCategoryAndAlertStatusWiseBasedOnUserAccessLevelForOrganization(locationAccessLevelId, locationValues, stateId, fromDate, toDate,alertTypes,alertEditions);
			 
			   if(rtrnAlrtCtgryAndSttsWseCntObjLst != null && !rtrnAlrtCtgryAndSttsWseCntObjLst.isEmpty()){
				   for(Object[] param:rtrnAlrtCtgryAndSttsWseCntObjLst){  
					   Long categoryId = commonMethodsUtilService.getLongValueForObject(param[0]);
					   Long statusId = commonMethodsUtilService.getLongValueForObject(param[2]);
					   Long alertcnt = commonMethodsUtilService.getLongValueForObject(param[4]);
					   if(alertCategoryMap.get(categoryId) != null ){
						   AlertOverviewVO statusVO = getMatchVO(alertCategoryMap.get(categoryId).getStatusList(),statusId);
						   if(statusVO != null ){
							   statusVO.setStatusCnt(alertcnt);  
							   statusVO.setStatusCntPer(calculatePercantage(statusVO.getStatusCnt(), alertCategoryMap.get(categoryId).getStatusCnt()));
						   }
					   }
				   }
			   }
			   
			   //modified code
			   Map<Long,AlertOverviewVO> categoryTypeAndEditionDtlsVoMap = new HashMap<Long,AlertOverviewVO>();
			   prepareTempForAlertTypeAndEdition(editionTypeList,rtrnAlertCategoryObjLst,categoryTypeAndEditionDtlsVoMap);
			   List<Object[]> rtrnAlrCtgryCntAndEditionobjLst = alertDAO.getAlertCntByAlertCategoryBasedOnUserAccessLevelForOrganization(locationAccessLevelId, locationValues, stateId, fromDate, toDate,"true",alertTypes,alertEditions);
			   
			   if(rtrnAlrCtgryCntAndEditionobjLst != null && rtrnAlrCtgryCntAndEditionobjLst.size() > 0){
				   for(Object[] param : rtrnAlrCtgryCntAndEditionobjLst){
					   Long statusTypeId = commonMethodsUtilService.getLongValueForObject(param[0]);
					   Long editionId = commonMethodsUtilService.getLongValueForObject(param[2]);
					   Long alertcnt = commonMethodsUtilService.getLongValueForObject(param[4]);
					   if(categoryTypeAndEditionDtlsVoMap.get(statusTypeId) != null ){
						   AlertOverviewVO alertTypeVO = getMatchVOForEdition(categoryTypeAndEditionDtlsVoMap.get(statusTypeId).getEditionList(),editionId);
						   if(alertTypeVO != null ){
							   alertTypeVO.setEditionCnt(alertcnt);
						   }
					   }
				   }
			   }
			   
			   // preparing final result(1)
			   resultVO.setOverAllVO(overViewVO);  
			   //for party
			   if(alertTypeAndEditionDtlsVoMap.get(1L) != null){
				   resultVO.setTotalPartyList(alertTypeAndEditionDtlsVoMap.get(1L).getEditionList());
				   
			   }
			   mergeTwoEdition(resultVO.getTotalPartyList());
			   //for govt
			   if(alertTypeAndEditionDtlsVoMap.get(2L) != null){
				   resultVO.setTotalGovtList(alertTypeAndEditionDtlsVoMap.get(2L).getEditionList());
			   }
			   mergeTwoEdition(resultVO.getTotalGovtList());
			   //for others
			   if(alertTypeAndEditionDtlsVoMap.get(3L) != null){
				   resultVO.setTotalOtherList(alertTypeAndEditionDtlsVoMap.get(3L).getEditionList());
			   }
			   mergeTwoEdition(resultVO.getTotalOtherList());
			   
			   //(2)
			   if(alertStatusMap != null && alertStatusMap.size() > 0){
				   resultVO.getStatusList().addAll(alertStatusMap.values());
				   alertStatusMap.clear();
			   }
			   if(resultVO.getStatusList() != null && resultVO.getStatusList().size() > 0){
				   for(AlertOverviewVO param : resultVO.getStatusList()){
					   Long statusId = param.getStatusTypeId();
					   param.getEditionList().addAll(statusTypeAndEditionDtlsVoMap.get(statusId).getEditionList());
				   }
			   }
			   if(resultVO.getStatusList() != null && resultVO.getStatusList().size() > 0){
				   for(AlertOverviewVO param : resultVO.getStatusList()){
					   mergeTwoEdition(param.getEditionList());
				   }
			   }
			   
			   //remove alert category which does not contain alert count
	            if(alertCategoryMap != null && alertCategoryMap.size() > 0){
	            	for(Entry<Long,AlertOverviewVO> entry:alertCategoryMap.entrySet()){
	            		 if(entry.getValue().getStatusCnt() == 0l){
	            			 alertCategoryMap.remove(entry.getKey());
	            		 }
	            	}
	            }
			   //(3)
			   if(alertCategoryMap != null && alertCategoryMap.size() > 0){
				   resultVO.getCategoryList().addAll(alertCategoryMap.values());
				   alertCategoryMap.clear();  
			   }
			   
			   if(resultVO.getCategoryList() != null && resultVO.getCategoryList().size() > 0){
				   for(AlertOverviewVO param : resultVO.getCategoryList()){
					   Long statusId = param.getStatusTypeId();
					   param.getEditionList().addAll(categoryTypeAndEditionDtlsVoMap.get(statusId).getEditionList());
				   }
			   }
			   
			   
			   if(resultVO.getCategoryList() != null && resultVO.getCategoryList().size() > 0){
				   for(AlertOverviewVO param : resultVO.getCategoryList()){
					   mergeTwoEdition(param.getEditionList());
				   }  
			   }
		   }catch(Exception e){
			   LOG.error("Error occured getAlertOverviewDetails() method of AlertService{}",e);
		   }
		   return resultVO;
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
	 
	 public void prepareTempForAlertTypeAndEdition(List<Object[]> editionTypeList,List<Object[]> alertTypeList,Map<Long,AlertOverviewVO> alertTypeAndEditionDtlsVoMap){
		if(editionTypeList != null && editionTypeList.size() > 0){
			for(Object[] param : alertTypeList){
				AlertOverviewVO categoryVO = new AlertOverviewVO();
				categoryVO.setAlertTypeId(commonMethodsUtilService.getLongValueForObject(param[0]));
				categoryVO.setAlertType(commonMethodsUtilService.getStringValueForObject(param[1]));
				categoryVO.getEditionList().addAll(getEditionList(editionTypeList));
				alertTypeAndEditionDtlsVoMap.put(categoryVO.getAlertTypeId(), categoryVO);
			}
		}
	}
	 
	 public List<AlertOverviewVO> getEditionList(List<Object[]> editionTypeList){
		List<AlertOverviewVO> editionList = new ArrayList<AlertOverviewVO>();
		try{
			if(editionTypeList != null && editionTypeList.size() > 0){
				for(Object[] param : editionTypeList){
					AlertOverviewVO editionVO = new AlertOverviewVO();
					editionVO.setEditionId(commonMethodsUtilService.getLongValueForObject(param[0]));
					editionVO.setEdition(commonMethodsUtilService.getStringValueForObject(param[1]));
					editionList.add(editionVO);
				}
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return editionList;
	}
	 
	 public AlertOverviewVO getMatchVOForEdition(List<AlertOverviewVO> statusList,Long editionId){
		try{
			   if(statusList == null || statusList.size() == 0)
				   return null;
			   for(AlertOverviewVO vo:statusList){
				   if(vo.getEditionId().equals(editionId)){
					   return vo;
				   }
			   }
		   }catch(Exception e){
			   LOG.error("Error occured getMatchVO() method of AlertService{}",e);  
		   }
		   return null;
	}
	 
	 public void prepareTemplateStatusWise(List<Object[]> objList,Map<Long,AlertOverviewVO> alertStatusMap){
	   try{
		   if(objList != null && objList.size() > 0){
			   for(Object[] param:objList){
				   AlertOverviewVO statusVO = new AlertOverviewVO();
				   statusVO.setStatusTypeId(commonMethodsUtilService.getLongValueForObject(param[0]));
				   statusVO.setStatusType(commonMethodsUtilService.getStringValueForObject(param[1]));
				   alertStatusMap.put(statusVO.getStatusTypeId(), statusVO);
			   }
		   }   
	   }catch(Exception e){
		   LOG.error("Error occured prepareTemplateStatusWise() method of AlertService{}",e);   
	   }
   }
	 
	 public void prepareAlertCategoryTemplate(List<Object[]> alertCategoryObjList,List<Object[]> alertStatusObjLst,Map<Long,AlertOverviewVO> alertCategoryMap){
		   try{
			   if(alertCategoryObjList != null && alertCategoryObjList.size() > 0){
				   for(Object[] param:alertCategoryObjList){
					   AlertOverviewVO categoryVO = new AlertOverviewVO();
					   categoryVO.setStatusTypeId(commonMethodsUtilService.getLongValueForObject(param[0]));
					   categoryVO.setStatusType(commonMethodsUtilService.getStringValueForObject(param[1])+" Alerts");
					   categoryVO.getStatusList().addAll(getStatutList(alertStatusObjLst));
					   alertCategoryMap.put(categoryVO.getStatusTypeId(), categoryVO);
				   }
			   }
		   }catch(Exception e){
			   LOG.error("Error occured prepareAlertCategoryTemplate() method of AlertService{}",e);  
		   }
	   }
	 
	 public List<AlertOverviewVO> getStatutList(List<Object[]> alertObjList){
		   List<AlertOverviewVO> statusList = new ArrayList<AlertOverviewVO>();
		   try{
			   if(alertObjList != null  && !alertObjList.isEmpty() ){
				   for(Object[] param:alertObjList){
					   AlertOverviewVO statusVO = new AlertOverviewVO();
					   statusVO.setStatusTypeId(commonMethodsUtilService.getLongValueForObject(param[0]));
					   statusVO.setStatusType(commonMethodsUtilService.getStringValueForObject(param[1]));
					   statusList.add(statusVO);
				   }
			   }
		   }catch(Exception e){
			   LOG.error("Error occured getStatutList() method of AlertService{}",e); 
		   }
		   return statusList;
	   }
	 
	 public AlertOverviewVO getMatchVO(List<AlertOverviewVO> statusList,Long statusId){
		   try{
			   if(statusList == null || statusList.size() == 0)
				   return null;
			   for(AlertOverviewVO vo:statusList){
				   if(vo.getStatusTypeId().equals(statusId)){
					   return vo;
				   }
			   }
		   }catch(Exception e){
			   LOG.error("Error occured getMatchVO() method of AlertService{}",e);  
		   }
		   return null;
	   }
	 
	 public void mergeTwoEdition(List<AlertOverviewVO> editionVoList){
		  Long alertCnt = 0L;
		  if(editionVoList != null && editionVoList.size() > 0){
			  for(AlertOverviewVO param : editionVoList){
				  if(param.getEditionId().longValue() != 1){
					  alertCnt += param.getEditionCnt();
				  }
			  }
			  editionVoList.get(1).setEditionCnt(alertCnt);
			  editionVoList.remove(2);
			  editionVoList.remove(2);  
		  }
	  }
	 
	 
	 public List<AlertOverviewVO> getAlertCategoryDtlsLocationWise(Long activityMemberId,Long stateId,String fromDateStr,String toDateStr,Long alertType, Long editionTypeId) {
		 List<AlertOverviewVO> resultList = new ArrayList<AlertOverviewVO>();
		 Map<Long,AlertOverviewVO> categoryMap = new ConcurrentHashMap<Long, AlertOverviewVO>(0);
		 Set<Long> locationValues = new HashSet<Long>(0);
	     Long locationAccessLevelId =0l;
	     SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
	     Date fromDate=null;
	     Date toDate = null;
	     List<Long> alertTypeList = new ArrayList<Long>();
	     List<Long> editionList = new ArrayList<Long>();
	     if(alertType != null){
	    	 if(alertType.longValue() == 0L){
	    	 }else{
	    		 alertTypeList.add(alertType);
	    	 }
	     }
	     if(editionTypeId != null){
	    	 if(editionTypeId.longValue() == 0L){
	    	 }else if(editionTypeId.longValue() == 1L){
	    		 editionList.add(editionTypeId);
	    	 }else if(editionTypeId.longValue() == 2L){  
	    		 editionList.add(editionTypeId);
	    		 editionList.add(3L);
	    	 }
	     }
		 try{
			 if(fromDateStr != null && !fromDateStr.isEmpty() && fromDateStr.length() > 0l && toDateStr != null && !toDateStr.isEmpty() && toDateStr.length() > 0){
				   fromDate = sdf.parse(fromDateStr);
				   toDate = sdf.parse(toDateStr);
				 }
			   	 List<Object[]> rtrnUsrAccssLvlIdAndVlusObjLst=activityMemberAccessLevelDAO.getLocationLevelAndValuesByActivityMembersIdForOrganization(activityMemberId);
				 if(rtrnUsrAccssLvlIdAndVlusObjLst != null && rtrnUsrAccssLvlIdAndVlusObjLst.size() > 0){
					 locationAccessLevelId=(Long) rtrnUsrAccssLvlIdAndVlusObjLst.get(0)[0];
					 for(Object[] param:rtrnUsrAccssLvlIdAndVlusObjLst){
						 locationValues.add(commonMethodsUtilService.getLongValueForObject(param[1]));
					 }
				 }
				  List<Object[]> rtrnAlertCategoryObjLst = alertCategoryDAO.getAllCategoryOrderBy();
				  List<Object[]> rtrnAlertImpactLevelObjLst = alertImpactScopeDAO.getAllAlertImpactLevel();
				  prepareTemplate(rtrnAlertCategoryObjLst,rtrnAlertImpactLevelObjLst,categoryMap);////Prepare Template 
				  List<Object[]> rtrnImpactLevelCntObjLst = alertDAO.getAlertCntByAlertCategoryAndImpactLevelWiseBasedOnUserAccessLevelForOrganization(locationAccessLevelId,locationValues,stateId,fromDate, toDate,alertTypeList,editionList);
				  setAlertImpactLevelWiseAlertCnt(rtrnImpactLevelCntObjLst,categoryMap); 
				  List<Object[]> rtrnImpctLvlSttusWsCntObjLst = alertDAO.getAlertCntByAlertCategoryImpactLevelAndStatusWiseBasedOnUserAccessLevelForOrganization(locationAccessLevelId,locationValues,stateId,fromDate, toDate,alertTypeList,editionList);
				  setStatusWiseAlertCnt(rtrnImpctLvlSttusWsCntObjLst,categoryMap);
				  
				  // merge mandal,muncipality,Village And Ward data 
				  if(categoryMap != null && categoryMap.size() > 0){
					  for(Entry<Long, AlertOverviewVO> entry : categoryMap.entrySet()){
						  AlertOverviewVO mandalMuncipalityVO = new AlertOverviewVO();
						  AlertOverviewVO villageWardVO = new AlertOverviewVO();
						  AlertOverviewVO mandalVO = getImpactLevelMatchVO(entry.getValue().getSubList(), 5l);
						  AlertOverviewVO MuncipalityVO = getImpactLevelMatchVO(entry.getValue().getSubList(), 8l);
						  mergeRequiredData(mandalMuncipalityVO,mandalVO,4l,"Mandal/Muncipality");
						  mergeRequiredData(mandalMuncipalityVO,MuncipalityVO,4l,"Mandal/Muncipality");
						  AlertOverviewVO villageVO = getImpactLevelMatchVO(entry.getValue().getSubList(), 7l);
						  AlertOverviewVO wardVO = getImpactLevelMatchVO(entry.getValue().getSubList(),9l);
						  AlertOverviewVO panchayatVo = getImpactLevelMatchVO(entry.getValue().getSubList(),6l);
						  mergeRequiredData(villageWardVO,villageVO,5l,"Village/Ward");
						  mergeRequiredData(villageWardVO,wardVO,5l,"Village/Ward");
						  mergeRequiredData(villageWardVO,panchayatVo,5l,"Village/Ward");
						  entry.getValue().getSubList().remove(mandalVO);//removeMandalData
						  entry.getValue().getSubList().remove(MuncipalityVO);//removemuncipalityData
						  entry.getValue().getSubList().add(mandalMuncipalityVO);//adding mandalMucipality Merge Data
						  entry.getValue().getSubList().remove(villageVO);//removeVillageData
						  entry.getValue().getSubList().remove(wardVO);//removeWardData
						  entry.getValue().getSubList().remove(panchayatVo);//removePanchaytData
						  entry.getValue().getSubList().add(villageWardVO);//adding VillageWard Merge Data
				      }
				  }
				  //remove alert category which does not contain any alert count in all impact level.
				  if(categoryMap != null && categoryMap.size() > 0){
					  for(Entry<Long,AlertOverviewVO> entry:categoryMap.entrySet()){
						  boolean isAlertCount = false;
						  if(entry.getValue().getSubList() != null && entry.getValue().getSubList().size()> 0){
							  for(AlertOverviewVO locationVO:entry.getValue().getSubList()){
								  if(locationVO.getAlertCount() > 0l){
									  isAlertCount = true;
								  }
								  
							  }
						  }  
						  if(isAlertCount==false){
							  categoryMap.remove(entry.getKey());  
						  }
					 }
				  }
				  if(categoryMap != null && categoryMap.size() > 0){
					  resultList.addAll(categoryMap.values());  
				  }
				  //Calculating overall Category Data
				  Map<Long,AlertOverviewVO> overAllAlrtDtlsMap = new HashMap<Long, AlertOverviewVO>();
				  if(categoryMap != null && categoryMap.size() > 0){
					  for(Entry<Long, AlertOverviewVO> entry : categoryMap.entrySet()){
						  if(entry.getValue().getSubList() != null && entry.getValue().getSubList().size() > 0){
							  for(AlertOverviewVO locationVO:entry.getValue().getSubList()){
								  AlertOverviewVO overAllVO = overAllAlrtDtlsMap.get(locationVO.getLocationTypeId());
								   if(overAllVO == null){
									   overAllVO = new AlertOverviewVO();
									   overAllVO.setLocationTypeId(locationVO.getLocationTypeId());
									   overAllVO.setLocationType(locationVO.getLocationType());
									   overAllVO.setAlertCount(locationVO.getAlertCount());
									   overAllVO.setPendingCnt(locationVO.getPendingCnt());
									   overAllVO.setNotifiedCnt(locationVO.getNotifiedCnt());
									   overAllVO.setActionInProgessCnt(locationVO.getActionInProgessCnt());
									   overAllVO.setCompletedCnt(locationVO.getCompletedCnt());
									   overAllVO.setUnabletoResolveCnt(locationVO.getUnabletoResolveCnt());
									   overAllVO.setActionNotRequiredCnt(locationVO.getActionNotRequiredCnt());
									   overAllVO.setDuplicatesStatusCnt(locationVO.getDuplicatesStatusCnt());
									   overAllAlrtDtlsMap.put(overAllVO.getLocationTypeId(), overAllVO);
								   }else{
								   overAllVO.setAlertCount(overAllVO.getAlertCount()+locationVO.getAlertCount());
								   overAllVO.setPendingCnt(overAllVO.getPendingCnt()+locationVO.getPendingCnt());
								   overAllVO.setNotifiedCnt(overAllVO.getNotifiedCnt()+locationVO.getNotifiedCnt());
								   overAllVO.setActionInProgessCnt(overAllVO.getActionInProgessCnt()+locationVO.getActionInProgessCnt());
								   overAllVO.setCompletedCnt(overAllVO.getCompletedCnt()+locationVO.getCompletedCnt());
								   overAllVO.setUnabletoResolveCnt(overAllVO.getUnabletoResolveCnt()+locationVO.getUnabletoResolveCnt());
								   overAllVO.setActionNotRequiredCnt(overAllVO.getActionNotRequiredCnt()+locationVO.getActionNotRequiredCnt());
								   overAllVO.setDuplicatesStatusCnt(overAllVO.getDuplicatesStatusCnt()+locationVO.getDuplicatesStatusCnt());
							   }
							  }
						  }
					  }
				  }
				  
				  if(overAllAlrtDtlsMap != null && overAllAlrtDtlsMap.size() > 0){
					  AlertOverviewVO overAllAlertVO = new AlertOverviewVO();
					  overAllAlertVO.setName("All Categories Alerts");
					  List<AlertOverviewVO> overAllAlertCntList = new ArrayList<AlertOverviewVO>(overAllAlrtDtlsMap.values());
					  overAllAlertVO.getSubList().addAll(overAllAlertCntList);
					  resultList.add(0, overAllAlertVO);
					  overAllAlrtDtlsMap.clear();
				  }
				
		 }catch(Exception e){
			 LOG.error("Error occured getAlertCategoryDtlsLocationWise() method of AlertService{}",e); 
		 }
		 return resultList;
	 }
	 
	 public void prepareTemplate(List<Object[]> objList,List<Object[]> impactLevelLst,Map<Long,AlertOverviewVO> categoryMap) {
		 try{
			 if(objList != null && objList.size() > 0){
				 for(Object[] param:objList){
					 AlertOverviewVO categoryVO = new AlertOverviewVO();
					 categoryVO.setId(commonMethodsUtilService.getLongValueForObject(param[0]));
					 categoryVO.setName(commonMethodsUtilService.getStringValueForObject(param[1])+" Alerts");
					 categoryVO.getSubList().addAll(getImpactLevelList(impactLevelLst));
					 categoryMap.put(categoryVO.getId(), categoryVO);
				 }
			 }
		 }catch(Exception e){
			 LOG.error("Error occured prepareTemplate() method of AlertService{}",e); 
		 }
	 }
	 
	 public List<AlertOverviewVO> getImpactLevelList(List<Object[]> impactLevlLst){
		 List<AlertOverviewVO> locationList = new CopyOnWriteArrayList<AlertOverviewVO>();
		   try{
			   if(impactLevlLst != null  && !impactLevlLst.isEmpty() ){
				   for(Object[] param:impactLevlLst){
					   AlertOverviewVO locationVO = new AlertOverviewVO();
					   locationVO.setLocationTypeId(commonMethodsUtilService.getLongValueForObject(param[0]));
					   locationVO.setLocationType(commonMethodsUtilService.getStringValueForObject(param[1]));
					   locationList.add(locationVO);
				   }
			   }
		   }catch(Exception e){
			   LOG.error("Error occured getImpactLevelList() method of AlertService{}",e); 
		   }
		   return locationList; 
	 }
	 
	 public void setAlertImpactLevelWiseAlertCnt(List<Object[]> objList,Map<Long,AlertOverviewVO> categoryMap){
		 try{
			 if(objList != null && objList.size() > 0){
				 for(Object[] param:objList){
					 Long alrtCtgryId = commonMethodsUtilService.getLongValueForObject(param[0]);
					 Long impactLevelId = commonMethodsUtilService.getLongValueForObject(param[2]);
					 Long alertCnt = commonMethodsUtilService.getLongValueForObject(param[4]);
					 if(categoryMap.get(alrtCtgryId) != null){
						 AlertOverviewVO impactLevelVO = getImpactLevelMatchVO(categoryMap.get(alrtCtgryId).getSubList(),impactLevelId);
						 	if(impactLevelVO != null ){
						 		impactLevelVO.setAlertCount(alertCnt);
						 	}
					 }
				 }
			 }
		 }catch(Exception e){
			 LOG.error("Error occured setAlertImpactLevelWiseAlertCnt() method of AlertService{}",e); 
		 }
	 }
	 
	 public AlertOverviewVO getImpactLevelMatchVO(List<AlertOverviewVO> impactLevelList,Long impactLevelId){
		 try{
		 	 if(impactLevelList == null || impactLevelList.size() == 0)
		 		 return null;
		 	 for(AlertOverviewVO impactLevelVO:impactLevelList){
		 		if(impactLevelVO.getLocationTypeId().equals(impactLevelId)){
		 			return impactLevelVO;
		 		}
		 	 }
		 	}catch(Exception e){
		 		 LOG.error("Error occured getImpactLevelMatchVO() method of AlertService{}",e);
		 	}	 
		 return null;
	 }
	 
	 public void setStatusWiseAlertCnt(List<Object[]> statusWiseAlertCntObjLst,Map<Long,AlertOverviewVO> categoryMap){
		 try{
			 if(statusWiseAlertCntObjLst != null && !statusWiseAlertCntObjLst.isEmpty()){
				 for(Object[] param:statusWiseAlertCntObjLst){
					 Long alrtCtgryId = commonMethodsUtilService.getLongValueForObject(param[0]);
					 Long impactLevelId = commonMethodsUtilService.getLongValueForObject(param[2]);
					 Long statusId = commonMethodsUtilService.getLongValueForObject(param[4]);
					 Long alertCnt = commonMethodsUtilService.getLongValueForObject(param[6]);
					 if(categoryMap.get(alrtCtgryId) != null){
						 AlertOverviewVO impactLevelVO = getImpactLevelMatchVO(categoryMap.get(alrtCtgryId).getSubList(),impactLevelId);
						 	if(impactLevelVO != null ){
						 		if(statusId == 1l){ //Pending
						 			impactLevelVO.setPendingCnt(alertCnt);
						 		}else if(statusId == 2l){//Notified
						 			impactLevelVO.setNotifiedCnt(alertCnt);
						 		}else if(statusId == 3l){//Action In Progess
						 			impactLevelVO.setActionInProgessCnt(alertCnt);
						 		}else if(statusId == 4l){//Completed
						 			impactLevelVO.setCompletedCnt(alertCnt);
						 		}else if(statusId == 5l){//Unable to Resolve
						 			impactLevelVO.setUnabletoResolveCnt(alertCnt);
						 		}else if(statusId == 6l){//Action Not Required
						 			impactLevelVO.setActionNotRequiredCnt(alertCnt);
						 		}else if(statusId == 7l){
						 			impactLevelVO.setDuplicatesStatusCnt(alertCnt);
						 		}
						 }
					 }
				 }
			 }
		 }catch(Exception e){
			 LOG.error("Error occured setStatusWiseAlertCnt() method of AlertService{}",e); 
		 }
	 }
	 
	 public void mergeRequiredData(AlertOverviewVO resutlVO,AlertOverviewVO VO,Long impactLevelId,String impactLevel){
		 try{
			 if(VO != null){
				 if(resutlVO.getLocationTypeId() == null){
					 resutlVO.setLocationTypeId(impactLevelId);
					 resutlVO.setLocationType(impactLevel);
				 }
				 resutlVO.setAlertCount(resutlVO.getAlertCount()+VO.getAlertCount());
				 resutlVO.setPendingCnt(resutlVO.getPendingCnt()+VO.getPendingCnt());
				 resutlVO.setNotifiedCnt(resutlVO.getNotifiedCnt()+VO.getNotifiedCnt());
				 resutlVO.setActionInProgessCnt(resutlVO.getActionInProgessCnt()+VO.getActionInProgessCnt());
				 resutlVO.setCompletedCnt(resutlVO.getCompletedCnt()+VO.getCompletedCnt());
				 resutlVO.setUnabletoResolveCnt(resutlVO.getUnabletoResolveCnt()+VO.getUnabletoResolveCnt());
				 resutlVO.setActionNotRequiredCnt(resutlVO.getActionNotRequiredCnt()+VO.getActionNotRequiredCnt());
				 resutlVO.setDuplicatesStatusCnt(resutlVO.getDuplicatesStatusCnt()+VO.getDuplicatesStatusCnt());
		 }
		 }catch(Exception e){
			 LOG.error("Error occured mergeRequiredData() method of AlertService{}",e);
		 }
	 }
	 
	 public List<AlertCoreDashBoardVO> getDistrictAndStateImpactLevelWiseAlertDtls(String fromDateStr, String toDateStr, Long stateId,List<Long> impactLevelIds, Long activityMemberId,Long districtId,Long catId, Long alertTypeId, Long editionId){
			LOG.info("Entered in getDistrictAndStateImpactLevelWiseAlertDtls() method of AlertService{}");
			try{  
				Date fromDate = null;          
				Date toDate = null; 
				SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
				if(fromDateStr != null && fromDateStr.trim().length() > 0 && toDateStr != null && toDateStr.trim().length() > 0){
					fromDate = sdf.parse(fromDateStr);
					toDate = sdf.parse(toDateStr);
				}
				List<Long> alertTypeList = new ArrayList<Long>();
				List<Long> editionTypeList = new ArrayList<Long>();
				if(alertTypeId != null){
					if(alertTypeId.longValue() == 0L){
						
					}else{
						alertTypeList.add(alertTypeId);
					}
				}
				if(editionId != null){
					if(editionId.longValue() == 0L){
						
					}else if(editionId.longValue() == 1L){
						editionTypeList.add(editionId);
					}else if(editionId.longValue() == 2L){
						editionTypeList.add(editionId);
						editionTypeList.add(3L);
					}
				}
				Long userAccessLevelId = null;
				List<Long> userAccessLevelValues = new ArrayList<Long>();
				List<Object[]> accessLvlIdAndValuesList = activityMemberAccessLevelDAO.getLocationLevelAndValuesByActivityMembersIdForOrganization(activityMemberId);  
				if(accessLvlIdAndValuesList != null && accessLvlIdAndValuesList.size() > 0){
					userAccessLevelId = accessLvlIdAndValuesList.get(0)[0] != null ? (Long)accessLvlIdAndValuesList.get(0)[0] : 0l;
					for(Object[] param : accessLvlIdAndValuesList){
						userAccessLevelValues.add(param[1] != null ? (Long)param[1] : 0l);  
					}
				}  
				List<AlertCoreDashBoardVO> alertCoreDashBoardVOs = new ArrayList<AlertCoreDashBoardVO>();
				List<Object[]> alertList = alertDAO.getDistrictAndStateImpactLevelWiseAlertDtlsForOrganization(userAccessLevelId, userAccessLevelValues, fromDate, toDate, stateId, impactLevelIds, districtId,catId,alertTypeList,editionTypeList);
				setAlertDtls(alertCoreDashBoardVOs, alertList);
				return alertCoreDashBoardVOs;
				}catch(Exception e){  
					e.printStackTrace();
					LOG.error("Error occured getDistrictAndStateImpactLevelWiseAlertDtls() method of AlertService{}");
				}
			return null;
		}
	 
	 public void setAlertDtls(List<AlertCoreDashBoardVO> alertCoreDashBoardVOs, List<Object[]> alertList){//abcd
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
						if(param[10] == null){
							alertCoreDashBoardVO.setLocation(commonMethodsUtilService.getStringValueForObject(param[11]));
						}else{
							alertCoreDashBoardVO.setLocation(commonMethodsUtilService.getStringValueForObject(param[10]));    
						}  
						
							if(commonMethodsUtilService.getLongValueForObject(param[5]).longValue() == 1L){//manual
								alertSource = commonMethodsUtilService.getStringValueForObject(param[13]);
							}else if(commonMethodsUtilService.getLongValueForObject(param[5]).longValue() == 2L){//print
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
							}
							alertCoreDashBoardVO.setSource(alertSource);
						 
						alertCoreDashBoardVOs.add(alertCoreDashBoardVO);
						
					}  
				}
			}catch(Exception e){
				e.printStackTrace();
			}
		}
	 
	 public List<AlertCoreDashBoardVO> getAlertDtls(String fromDateStr, String toDateStr, Long stateId, Long alertTypeId, Long alertStatusId, Long alertCategoryId, Long activityMemberId, Long editionId,String isActionType,Long alertActionTypeId){
			LOG.info("Entered in getAlertDtls() method of AlertService{}");
			try{
				Date fromDate = null;      
				Date toDate = null;
				SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
				if(fromDateStr != null && fromDateStr.trim().length() > 0 && toDateStr != null && toDateStr.trim().length() > 0){
					fromDate = sdf.parse(fromDateStr);
					toDate = sdf.parse(toDateStr);
				}
				List<Long> editionList = new ArrayList<Long>();
				if(editionId != null){
					if(editionId.longValue() == 1L){
						editionList.add(editionId);
					}else if(editionId.longValue() == 2L){
						editionList.add(editionId);
						editionList.add(3L);
					}
				}
				//get access level id and access level value
				Long userAccessLevelId = null;
				List<Long> userAccessLevelValues = new ArrayList<Long>();
				List<Object[]> accessLvlIdAndValuesList = activityMemberAccessLevelDAO.getLocationLevelAndValuesByActivityMembersIdForOrganization(activityMemberId);  
				if(accessLvlIdAndValuesList != null && accessLvlIdAndValuesList.size() > 0){  
					userAccessLevelId = accessLvlIdAndValuesList.get(0)[0] != null ? (Long)accessLvlIdAndValuesList.get(0)[0] : 0l;
					for(Object[] param : accessLvlIdAndValuesList){
						userAccessLevelValues.add(param[1] != null ? (Long)param[1] : 0l);
					}
				}
				  
				//DateUtilService dateUtilService = new DateUtilService();
				List<AlertCoreDashBoardVO> alertCoreDashBoardVOs = new ArrayList<AlertCoreDashBoardVO>();
				List<Object[]> alertList = null;
				if(isActionType != null && isActionType.equalsIgnoreCase("Yes")){ //alertStatusId means alertActionTypeStatusId
				 // alertList = alertActionTypeDAO.getActionTypeAlertDetails(fromDate, toDate, stateId, alertTypeId, alertStatusId, userAccessLevelId, userAccessLevelValues, editionList,alertActionTypeId);
				}else{
				 alertList = alertDAO.getAlertDtlsForOrganization(fromDate, toDate, stateId, alertTypeId, alertStatusId, alertCategoryId, userAccessLevelId, userAccessLevelValues,editionList);	
				}
				setAlertDtls(alertCoreDashBoardVOs, alertList);
				
				return alertCoreDashBoardVOs;
			}catch(Exception e){
				e.printStackTrace();  
				LOG.error("Error occured getAlertDtls() method of AlertService{}");
			}
			return null;        
		}
}
