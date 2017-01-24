package com.itgrids.partyanalyst.service.impl;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;

import org.apache.log4j.Logger;

import com.itgrids.partyanalyst.dao.IActivityMemberAccessLevelDAO;
import com.itgrids.partyanalyst.dao.IAlertCandidateDAO;
import com.itgrids.partyanalyst.dao.IAlertCategoryDAO;
import com.itgrids.partyanalyst.dao.IAlertCommentAssigneeDAO;
import com.itgrids.partyanalyst.dao.IAlertDAO;
import com.itgrids.partyanalyst.dao.IAlertImpactScopeDAO;
import com.itgrids.partyanalyst.dao.IAlertStatusDAO;
import com.itgrids.partyanalyst.dao.IAlertTrackingDAO;
import com.itgrids.partyanalyst.dao.IAlertTrackingDocumentsDAO;
import com.itgrids.partyanalyst.dao.IAlertTypeDAO;
import com.itgrids.partyanalyst.dao.IEditionTypeDAO;
import com.itgrids.partyanalyst.dao.IParliamentAssemblyDAO;
import com.itgrids.partyanalyst.dao.ITdpCadreDAO;
import com.itgrids.partyanalyst.dao.ITdpCommitteeMemberDAO;
import com.itgrids.partyanalyst.dao.IVerificationCommentsDAO;
import com.itgrids.partyanalyst.dao.IVerificationStatusDAO;
import com.itgrids.partyanalyst.dto.AlertCommentVO;
import com.itgrids.partyanalyst.dto.AlertCoreDashBoardVO;
import com.itgrids.partyanalyst.dto.AlertDataVO;
import com.itgrids.partyanalyst.dto.AlertOverviewVO;
import com.itgrids.partyanalyst.dto.AlertVO;
import com.itgrids.partyanalyst.dto.AlertVerificationVO;
import com.itgrids.partyanalyst.dto.KeyValueVO;
import com.itgrids.partyanalyst.dto.LocationVO;
import com.itgrids.partyanalyst.dto.StatusTrackingVO;
import com.itgrids.partyanalyst.service.IAlertsNewsPortalService;
import com.itgrids.partyanalyst.service.ICadreCommitteeService;
import com.itgrids.partyanalyst.utils.CommonMethodsUtilService;
import com.itgrids.partyanalyst.utils.DateUtilService;
import com.itgrids.partyanalyst.utils.SetterAndGetterUtilService;

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
	
	private IParliamentAssemblyDAO parliamentAssemblyDAO;
	private IAlertCandidateDAO alertCandidateDAO;
	
	private SetterAndGetterUtilService setterAndGetterUtilService;
	private ICadreCommitteeService cadreCommitteeService;
	private ITdpCommitteeMemberDAO tdpCommitteeMemberDAO;
	private IAlertCommentAssigneeDAO alertCommentAssigneeDAO;
	private IAlertTrackingDAO alertTrackingDAO;
	private ITdpCadreDAO tdpCadreDAO;
	private IAlertTrackingDocumentsDAO alertTrackingDocumentsDAO;
	private IVerificationStatusDAO verificationStatusDAO;
	private IVerificationCommentsDAO verificationCommentsDAO;
	
	
	public IVerificationCommentsDAO getVerificationCommentsDAO() {
		return verificationCommentsDAO;
	}
	public void setVerificationCommentsDAO(
			IVerificationCommentsDAO verificationCommentsDAO) {
		this.verificationCommentsDAO = verificationCommentsDAO;
	}
	public IVerificationStatusDAO getVerificationStatusDAO() {
		return verificationStatusDAO;
	}
	public void setVerificationStatusDAO(
			IVerificationStatusDAO verificationStatusDAO) {
		this.verificationStatusDAO = verificationStatusDAO;
	}
	public IAlertTrackingDocumentsDAO getAlertTrackingDocumentsDAO() {
		return alertTrackingDocumentsDAO;
	}
	public void setAlertTrackingDocumentsDAO(
			IAlertTrackingDocumentsDAO alertTrackingDocumentsDAO) {
		this.alertTrackingDocumentsDAO = alertTrackingDocumentsDAO;
	}
	public ITdpCadreDAO getTdpCadreDAO() {
		return tdpCadreDAO;
	}
	public void setTdpCadreDAO(ITdpCadreDAO tdpCadreDAO) {
		this.tdpCadreDAO = tdpCadreDAO;
	}
	public IAlertTrackingDAO getAlertTrackingDAO() {
		return alertTrackingDAO;
	}
	public void setAlertTrackingDAO(IAlertTrackingDAO alertTrackingDAO) {
		this.alertTrackingDAO = alertTrackingDAO;
	}
	public IAlertCommentAssigneeDAO getAlertCommentAssigneeDAO() {
		return alertCommentAssigneeDAO;
	}
	public void setAlertCommentAssigneeDAO(
			IAlertCommentAssigneeDAO alertCommentAssigneeDAO) {
		this.alertCommentAssigneeDAO = alertCommentAssigneeDAO;
	}
	public ITdpCommitteeMemberDAO getTdpCommitteeMemberDAO() {
		return tdpCommitteeMemberDAO;
	}
	public void setTdpCommitteeMemberDAO(
			ITdpCommitteeMemberDAO tdpCommitteeMemberDAO) {
		this.tdpCommitteeMemberDAO = tdpCommitteeMemberDAO;
	}
	public ICadreCommitteeService getCadreCommitteeService() {
		return cadreCommitteeService;
	}
	public void setCadreCommitteeService(
			ICadreCommitteeService cadreCommitteeService) {
		this.cadreCommitteeService = cadreCommitteeService;
	}
	public SetterAndGetterUtilService getSetterAndGetterUtilService() {
		return setterAndGetterUtilService;
	}
	public void setSetterAndGetterUtilService(
			SetterAndGetterUtilService setterAndGetterUtilService) {
		this.setterAndGetterUtilService = setterAndGetterUtilService;
	}
	public IAlertCandidateDAO getAlertCandidateDAO() {
		return alertCandidateDAO;
	}
	public void setAlertCandidateDAO(IAlertCandidateDAO alertCandidateDAO) {
		this.alertCandidateDAO = alertCandidateDAO;
	}
	public IParliamentAssemblyDAO getParliamentAssemblyDAO() {
		return parliamentAssemblyDAO;
	}
	public void setParliamentAssemblyDAO(
			IParliamentAssemblyDAO parliamentAssemblyDAO) {
		this.parliamentAssemblyDAO = parliamentAssemblyDAO;
	}
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
		   SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
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
			   LOG.error("Error occured getAlertOverviewDetails() method of AlertsNewsPortalService{}",e);
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
			   LOG.error("Error occured getMatchVO() method of AlertsNewsPortalService{}",e);  
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
		   LOG.error("Error occured prepareTemplateStatusWise() method of AlertsNewsPortalService{}",e);   
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
			   LOG.error("Error occured prepareAlertCategoryTemplate() method of AlertsNewsPortalService{}",e);  
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
			   LOG.error("Error occured getStatutList() method of AlertsNewsPortalService{}",e); 
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
			   LOG.error("Error occured getMatchVO() method of AlertsNewsPortalService{}",e);  
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
	     SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
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
			 LOG.error("Error occured getAlertCategoryDtlsLocationWise() method of AlertsNewsPortalService{}",e); 
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
			 LOG.error("Error occured prepareTemplate() method of AlertsNewsPortalService{}",e); 
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
			   LOG.error("Error occured getImpactLevelList() method of AlertsNewsPortalService{}",e); 
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
			 LOG.error("Error occured setAlertImpactLevelWiseAlertCnt() method of AlertsNewsPortalService{}",e); 
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
		 		 LOG.error("Error occured getImpactLevelMatchVO() method of AlertsNewsPortalService{}",e);
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
			 LOG.error("Error occured setStatusWiseAlertCnt() method of AlertsNewsPortalService{}",e); 
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
			 LOG.error("Error occured mergeRequiredData() method of AlertsNewsPortalService{}",e);
		 }
	 }
	 
	 public List<AlertCoreDashBoardVO> getDistrictAndStateImpactLevelWiseAlertDtls(String fromDateStr, String toDateStr, Long stateId,List<Long> impactLevelIds, Long activityMemberId,Long districtId,Long catId, Long alertTypeId, Long editionId){
			LOG.info("Entered in getDistrictAndStateImpactLevelWiseAlertDtls() method of AlertsNewsPortalService{}");
			try{  
				Date fromDate = null;          
				Date toDate = null; 
				SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
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
					LOG.error("Error occured getDistrictAndStateImpactLevelWiseAlertDtls() method of AlertsNewsPortalService{}");
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
			LOG.info("Entered in getAlertDtls() method of AlertsNewsPortalService{}");
			try{
				Date fromDate = null;      
				Date toDate = null;
				SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
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
				LOG.error("Error occured getAlertDtls() method of AlertsNewsPortalService{}");
			}
			return null;        
		}
	 
	 public List<Long> splitAndAssignValuesToList(String stringObj,List<Long> values){
			try{
				if(stringObj !=null && !stringObj.trim().isEmpty()){
					String s[] = stringObj.split(",");
					if(s != null && s.length > 0){
						for(int i=0;i<s.length;i++){
							if(s[i] != null && !s[i].isEmpty())
								values.add(Long.parseLong(s[i]));
						}
					}
				}
				
			}catch(Exception e){
				e.printStackTrace();
			}
			return values;
		}
	 
	 
	 public AlertOverviewVO getStateImpactLevelAlertDtlsCnt(Long activityMemberId,Long stateId,String fromDateStr,String toDateStr,String impactLevelIdsStr,Long alertTypeId, Long editionId){
		 AlertOverviewVO resultVO = new AlertOverviewVO();
		 Set<Long> locationValues = new HashSet<Long>(0);
	     Long locationAccessLevelId =0l;
	     SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
	     Date fromDate=null;
	     Date toDate = null;
	 	List<Long> alertTypeList = new ArrayList<Long>();
		List<Long> editionList = new ArrayList<Long>();
		if(alertTypeId != null){
			if(alertTypeId.longValue() == 0L){
				
			}else{
				alertTypeList.add(alertTypeId);
			}
		}
		
		if(editionId != null){
			if(editionId.longValue() == 0L){
				
			}else if(editionId.longValue() == 1L){
				editionList.add(editionId);
			}else if(editionId.longValue() == 2L){
				editionList.add(editionId);
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
				 List<Object[]> rtrnCategoryObjLst = alertCategoryDAO.getAllCategoryOrderBy();
				 prepareRquiredTemplate(rtrnCategoryObjLst,resultVO.getCategoryList());
				 List<Object[]> rtrnStatusObjLst = alertStatusDAO.getAllStatus();
				 prepareRquiredTemplate(rtrnStatusObjLst,resultVO.getStatusList());
				 
				 List<Long> impactLevelIds = new ArrayList<Long>();
				 impactLevelIds = splitAndAssignValuesToList(impactLevelIdsStr,impactLevelIds);
				 
				 List<Object[]> rtrnObjLst = alertDAO.getStateImpactLevelAlertCntForOrganization(locationAccessLevelId, locationValues, stateId, impactLevelIds, fromDate, toDate, "State",alertTypeList,editionList);
				 if(rtrnObjLst != null && rtrnObjLst.size() > 0){
					 for(Object[] param:rtrnObjLst){
						 AlertOverviewVO stateVO = new AlertOverviewVO();
						 stateVO.setId(commonMethodsUtilService.getLongValueForObject(param[0]));
						 stateVO.setName(commonMethodsUtilService.getStringValueForObject(param[1]));
						 stateVO.setAlertCount(commonMethodsUtilService.getLongValueForObject(param[2]));
						 resultVO.getSubList().add(stateVO);
					 }
				 }
				  List<Object[]> rtrnCtgryObjLst = alertDAO.getStateImpactLevelAlertCntForOrganization(locationAccessLevelId, locationValues, stateId, impactLevelIds, fromDate, toDate, "Category",alertTypeList,editionList);
				 	if(rtrnCtgryObjLst != null && rtrnCtgryObjLst.size() > 0){
				 		for(Object[] param:rtrnCtgryObjLst){
				 			Long categoryId = commonMethodsUtilService.getLongValueForObject(param[0]);
				 			AlertOverviewVO categoryVO = getRequiredMatchVO(resultVO.getCategoryList(),categoryId);
				 			if(categoryVO != null){
				 				categoryVO.setAlertCount(commonMethodsUtilService.getLongValueForObject(param[2]));
				 			}
				 		}
				 	}
				  List<Object[]> rtrnStatusWiseCntObjLst = alertDAO.getStateImpactLevelAlertCntForOrganization(locationAccessLevelId, locationValues, stateId, impactLevelIds, fromDate, toDate, "Status",alertTypeList,editionList);	
				  if(rtrnStatusWiseCntObjLst != null && rtrnStatusWiseCntObjLst.size() > 0){
					  if(rtrnStatusWiseCntObjLst != null && rtrnStatusWiseCntObjLst.size() > 0){
					 		for(Object[] param:rtrnStatusWiseCntObjLst){
					 			Long statusId = commonMethodsUtilService.getLongValueForObject(param[0]);
					 			AlertOverviewVO statusVO = getRequiredMatchVO(resultVO.getStatusList(),statusId);
					 			if(statusVO != null){
					 				statusVO.setAlertCount(commonMethodsUtilService.getLongValueForObject(param[2]));
					 			}
					 		}
					 	} 
				  }
		 }catch(Exception e){
			 LOG.error("Exception occured  in getStateImpactLevelAlertDtlsCnt() in AlertsNewsPortalService class ",e);  
		 }
		 return resultVO;
	 }
	 
	 public void prepareRquiredTemplate(List<Object[]> objList,List<AlertOverviewVO> list){
		 try{
			 if(objList != null && objList.size() > 0){
				 for(Object[] param:objList){
					 AlertOverviewVO VO = new AlertOverviewVO();
					 VO.setId(commonMethodsUtilService.getLongValueForObject(param[0]));
					 VO.setName(commonMethodsUtilService.getStringValueForObject(param[1]));
					 list.add(VO);
				 }
			 }
		 }catch(Exception e){
			 LOG.error("Exception occured  in getStateImpactLevelAlertDtlsCnt() in AlertsNewsPortalService class ",e);
		 }
	 }
	 
	 public AlertOverviewVO getRequiredMatchVO(List<AlertOverviewVO> list,Long id){
		 try{
			 if(list == null || list.size() == 0)
				 return null;
			 for(AlertOverviewVO VO:list){
				 if(VO.getId().equals(id)){
					 return VO;
				 }
			 }
		 }catch(Exception e){
			 LOG.error("Exception occured  in getRequiredMatchVO() in AlertsNewsPortalService class ",e); 
		 }
		 return null;
		 }
	 
	 public List<AlertVO> getTotalAlertGroupByLocationThenCategory(String fromDateStr, String toDateStr, Long stateId,String scopeIdListStr, Long activityMemberId, String group,Long alertTypeId, Long editionId){
			LOG.info("Entered in getTotalAlertGroupByLocationThenCategory() method of AlertsNewsPortalService{}");
			try{  
				Date fromDate = null;  
				Date toDate = null;
				SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
				if(fromDateStr != null && fromDateStr.trim().length() > 0 && toDateStr != null && toDateStr.trim().length() > 0){
					fromDate = sdf.parse(fromDateStr);
					toDate = sdf.parse(toDateStr);
				}
				List<Long> alertTypeList = new ArrayList<Long>();
				List<Long> editionList = new ArrayList<Long>();
				if(alertTypeId != null){
					if(alertTypeId.longValue() == 0L){
						
					}else{
						alertTypeList.add(alertTypeId);
					}
				}
				
				if(editionId != null){
					if(editionId.longValue() == 0L){
						
					}else if(editionId.longValue() == 1L){
						editionList.add(editionId);
					}else if(editionId.longValue() == 2L){
						editionList.add(editionId);
						editionList.add(3L);
					}
				}
				
				List<Long> scopeIdList = new ArrayList<Long>();
				scopeIdList = splitAndAssignValuesToList(scopeIdListStr,scopeIdList);
				
				AlertVO alertVO = null;
				List<AlertVO> alertVOs = null;//new ArrayList<AlertVO>();
				Map<Long,Long> locationIdAndCountMap = new HashMap<Long,Long>();
				//get all the alert category for  building the template
				List<Object[]> categoryList = alertCategoryDAO.getAllCategory(); 
				
				Long userAccessLevelId = null;
				List<Long> userAccessLevelValues = new ArrayList<Long>();
				List<Object[]> accessLvlIdAndValuesList = activityMemberAccessLevelDAO.getLocationLevelAndValuesByActivityMembersIdForOrganization(activityMemberId);  
				if(accessLvlIdAndValuesList != null && accessLvlIdAndValuesList.size() > 0){
					userAccessLevelId = accessLvlIdAndValuesList.get(0)[0] != null ? (Long)accessLvlIdAndValuesList.get(0)[0] : 0l;
					for(Object[] param : accessLvlIdAndValuesList){
						userAccessLevelValues.add(param[1] != null ? (Long)param[1] : 0l);
					}
				}
				
				//convert parliament into constituency.
				if(userAccessLevelId.longValue() == 4L){
					List<Long> parliamentAssemlyIds = parliamentAssemblyDAO.getAssemblyConstituencyforParliament(userAccessLevelValues);
					userAccessLevelId = 5L;
					userAccessLevelValues.clear();
					userAccessLevelValues.addAll(parliamentAssemlyIds);      
				}  

				//get alert status count and and create a map of LocationId and its corresponding  alert count
				List<Object[]> alertCountList = alertDAO.getTotalAlertGroupByLocationForOrganization(fromDate, toDate, stateId, scopeIdList, "One",userAccessLevelId, userAccessLevelValues,alertTypeList,editionList);
				if(alertCountList != null && alertCountList.size() > 0){
					for(Object[] param : alertCountList){
						if(param[0] != null){
							locationIdAndCountMap.put(commonMethodsUtilService.getLongValueForObject(param[0]), commonMethodsUtilService.getLongValueForObject(param[2]));
						}
					}
				}  
				//get all the alert count group by status then category.
				Map<Long,String> locationIdAndNameMap = new HashMap<Long,String>();
				Map<Long,Long> categoryIdAndCountMap = null;//new HashMap<Long, Long>();  
				Map<Long,Map<Long,Long>> locationIdAndCategoryIdAndCountMap = new HashMap<Long,Map<Long,Long>>();
				List<Object[]> alertCountGrpByLocList = alertDAO.getTotalAlertGroupByLocationForOrganization(fromDate, toDate, stateId, scopeIdList, "two",userAccessLevelId, userAccessLevelValues,alertTypeList,editionList);    
				if(alertCountGrpByLocList != null && alertCountGrpByLocList.size() > 0){
					for(Object[] param : alertCountGrpByLocList){
						if(param[0] != null){
							categoryIdAndCountMap = locationIdAndCategoryIdAndCountMap.get(commonMethodsUtilService.getLongValueForObject(param[0]));
							if(categoryIdAndCountMap != null){
								categoryIdAndCountMap.put(commonMethodsUtilService.getLongValueForObject(param[2]), commonMethodsUtilService.getLongValueForObject(param[4]));
							}else{
								categoryIdAndCountMap = new HashMap<Long, Long>();
								categoryIdAndCountMap.put(commonMethodsUtilService.getLongValueForObject(param[2]), commonMethodsUtilService.getLongValueForObject(param[4]));
								locationIdAndCategoryIdAndCountMap.put(commonMethodsUtilService.getLongValueForObject(param[0]),categoryIdAndCountMap);
							}
							locationIdAndNameMap.put(commonMethodsUtilService.getLongValueForObject(param[0]), commonMethodsUtilService.getStringValueForObject(param[1]));
						}  
					}  
				}
				//build final vo to sent to ui
				List<AlertVO> finalList = new ArrayList<AlertVO>();
				AlertVO innerListAlertVO = null;
				if(locationIdAndCategoryIdAndCountMap.size() > 0){
					for(Entry<Long,Map<Long,Long>> entry : locationIdAndCategoryIdAndCountMap.entrySet()){
						categoryIdAndCountMap = entry.getValue();
						if(categoryIdAndCountMap.size() > 0){
							if(categoryList != null && categoryList.size() > 0){
								alertVOs = new ArrayList<AlertVO>();
								innerListAlertVO = new AlertVO();
								for(Object[] param : categoryList){
									alertVO = new AlertVO();
									alertVO.setCategoryId(commonMethodsUtilService.getLongValueForObject(param[0]));
									alertVO.setCategory(commonMethodsUtilService.getStringValueForObject(param[1]));
									alertVOs.add(alertVO);  
								}
							}
							for(AlertVO param : alertVOs){
								if(categoryIdAndCountMap.get(param.getCategoryId()) != null){
									param.setCategoryCount(categoryIdAndCountMap.get(param.getCategoryId()));  
								}else{
									param.setCategoryCount(0l);
								}
							}
							innerListAlertVO.setSubList1(alertVOs);
							if(locationIdAndNameMap.get(entry.getKey()) != null){
								innerListAlertVO.setStatusId(entry.getKey());
								innerListAlertVO.setStatus(locationIdAndNameMap.get(entry.getKey()));
								
							}
							if(locationIdAndCountMap.get(entry.getKey()) != null){
								innerListAlertVO.setCount(locationIdAndCountMap.get(entry.getKey()));
							}
							finalList.add(innerListAlertVO);     
						}
					}
				}  
				return finalList; 
			}catch(Exception e){
				e.printStackTrace();
				LOG.error("Error occured getTotalAlertGroupByLocationThenCategory() method of AlertsNewsPortalService{}");
			}
			return null;
		}
	 
	 public List<AlertVO> getTotalAlertGroupByLocationThenStatus(String fromDateStr, String toDateStr, Long stateId,String scopeIdListStr, Long activityMemberId, String group,Long alertTypeId,Long editionId){
			LOG.info("Entered in getTotalAlertGroupByLocationThenStatus() method of AlertsNewsPortalService{}");
			try{  
				Date fromDate = null;        
				Date toDate = null; 
				SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
				if(fromDateStr != null && fromDateStr.trim().length() > 0 && toDateStr != null && toDateStr.trim().length() > 0){
					fromDate = sdf.parse(fromDateStr);
					toDate = sdf.parse(toDateStr);
				}
				List<Long> alertTypeList = new ArrayList<Long>();
				List<Long> editionList = new ArrayList<Long>();
				if(alertTypeId != null){
					if(alertTypeId.longValue() == 0L){
						
					}else{
						alertTypeList.add(alertTypeId);
					}
				}
				
				if(editionId != null){
					if(editionId.longValue() == 0L){
						
					}else if(editionId.longValue() == 1L){
						editionList.add(editionId);
					}else if(editionId.longValue() == 2L){
						editionList.add(editionId);
						editionList.add(3L);
					}
				}
				
				
				List<Long> scopeIdList = new ArrayList<Long>();
				scopeIdList = splitAndAssignValuesToList(scopeIdListStr,scopeIdList);
				
				AlertVO alertVO = null;    
				List<AlertVO> alertVOs = null;//new ArrayList<AlertVO>();
				Map<Long,Long> locationIdAndCountMap = new HashMap<Long,Long>();
				//get all the alert status for  building the template
				List<Object[]> statusList = alertStatusDAO.getAllStatus();
				
				Long userAccessLevelId = null;
				List<Long> userAccessLevelValues = new ArrayList<Long>();
				List<Object[]> accessLvlIdAndValuesList = activityMemberAccessLevelDAO.getLocationLevelAndValuesByActivityMembersIdForOrganization(activityMemberId);  
				if(accessLvlIdAndValuesList != null && accessLvlIdAndValuesList.size() > 0){
					userAccessLevelId = accessLvlIdAndValuesList.get(0)[0] != null ? (Long)accessLvlIdAndValuesList.get(0)[0] : 0l;
					for(Object[] param : accessLvlIdAndValuesList){
						userAccessLevelValues.add(param[1] != null ? (Long)param[1] : 0l);
					}
				}
				
				//convert parliament into constituency.
				if(userAccessLevelId.longValue() == 4L){
					List<Long> parliamentAssemlyIds = parliamentAssemblyDAO.getAssemblyConstituencyforParliament(userAccessLevelValues);
					userAccessLevelId = 5L;
					userAccessLevelValues.clear();
					userAccessLevelValues.addAll(parliamentAssemlyIds);      
				}
				    
				//get alert status count and and create a map of LocationId and its corresponding  alert count
				//Date fromDate, Date toDate, Long stateId, List<Long> scopeIdList, String step, Long userAccessLevelId, List<Long> userAccessLevelValues
				List<Object[]> alertCountList = alertDAO.getTotalAlertGroupByLocationThenStatusForOrganization(fromDate, toDate, stateId, scopeIdList, "One", userAccessLevelId, userAccessLevelValues,alertTypeList,editionList);
				if(alertCountList != null && alertCountList.size() > 0){
					for(Object[] param : alertCountList){
						if(param[0] != null)
							locationIdAndCountMap.put(commonMethodsUtilService.getLongValueForObject(param[0]), commonMethodsUtilService.getLongValueForObject(param[2]));
					}
				}  
				//get all the alert count group by status then category.
				Map<Long,String> locationIdAndNameMap = new HashMap<Long,String>();
				Map<Long,Long> statusIdAndCountMap = null;//new HashMap<Long, Long>();  
				Map<Long,Map<Long,Long>> locationIdAndStatusIdAndCountMap = new HashMap<Long,Map<Long,Long>>();
				List<Object[]> alertCountGrpByLocList = alertDAO.getTotalAlertGroupByLocationThenStatusForOrganization(fromDate, toDate, stateId, scopeIdList, "two", userAccessLevelId, userAccessLevelValues,alertTypeList,editionList);    
				if(alertCountGrpByLocList != null && alertCountGrpByLocList.size() > 0){
					for(Object[] param : alertCountGrpByLocList){  
						if(param[0] != null){
							statusIdAndCountMap = locationIdAndStatusIdAndCountMap.get(commonMethodsUtilService.getLongValueForObject(param[0]));
							if(statusIdAndCountMap != null){
								statusIdAndCountMap.put(commonMethodsUtilService.getLongValueForObject(param[2]), commonMethodsUtilService.getLongValueForObject(param[4]));
							}else{
								statusIdAndCountMap = new HashMap<Long, Long>();
								statusIdAndCountMap.put(commonMethodsUtilService.getLongValueForObject(param[2]), commonMethodsUtilService.getLongValueForObject(param[4]));
								locationIdAndStatusIdAndCountMap.put(commonMethodsUtilService.getLongValueForObject(param[0]),statusIdAndCountMap);
							}  
							locationIdAndNameMap.put(commonMethodsUtilService.getLongValueForObject(param[0]), commonMethodsUtilService.getStringValueForObject(param[1]));
						}
					}
				}
				//build final vo to sent to ui
				List<AlertVO> finalList = new ArrayList<AlertVO>();
				AlertVO innerListAlertVO = null;
				if(locationIdAndStatusIdAndCountMap.size() > 0){
					for(Entry<Long,Map<Long,Long>> entry : locationIdAndStatusIdAndCountMap.entrySet()){
						statusIdAndCountMap = entry.getValue();
						if(statusIdAndCountMap.size() > 0){
							if(statusList != null && statusList.size() > 0){
								alertVOs = new ArrayList<AlertVO>();
								innerListAlertVO = new AlertVO();
								for(Object[] param : statusList){
									alertVO = new AlertVO();
									alertVO.setCategoryId(commonMethodsUtilService.getLongValueForObject(param[0]));
									alertVO.setCategory(commonMethodsUtilService.getStringValueForObject(param[1]));
									alertVOs.add(alertVO);  
								}
							}
							for(AlertVO param : alertVOs){
								if(statusIdAndCountMap.get(param.getCategoryId()) != null){
									param.setCategoryCount(statusIdAndCountMap.get(param.getCategoryId()));  
								}else{
									param.setCategoryCount(0l);  
								}
							}
							innerListAlertVO.setSubList1(alertVOs);
							if(locationIdAndNameMap.get(entry.getKey()) != null){
								innerListAlertVO.setStatusId(entry.getKey());
								innerListAlertVO.setStatus(locationIdAndNameMap.get(entry.getKey()));
								
							}
							if(locationIdAndCountMap.get(entry.getKey()) != null){
								innerListAlertVO.setCount(locationIdAndCountMap.get(entry.getKey()));
							}
							finalList.add(innerListAlertVO);     
						}
					}
				}  
				return finalList; 
			}catch(Exception e){
				e.printStackTrace();
				LOG.error("Error occured getTotalAlertGroupByLocationThenStatus() method of AlertsNewsPortalService{}");
			}
			return null;
		}
	 
	 public List<AlertOverviewVO> getAssignGroupTypeAlertDtlsByImpactLevelWise(Long activityMemberId,Long stateId,String fromDateStr,String toDateStr,String impactLevelIdsStr,Long alertTypeId,Long editionTypeId){
		 List<AlertOverviewVO> resultList = new ArrayList<AlertOverviewVO>();
		 Set<Long> locationValues = new HashSet<Long>(0);
	     Long locationAccessLevelId =0l;
	     SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
	     Date fromDate=null;
	     Date toDate = null;
		 try{
			    if(fromDateStr != null && !fromDateStr.isEmpty() && fromDateStr.length() > 0l && toDateStr != null && !toDateStr.isEmpty() && toDateStr.length() > 0){
				   fromDate = sdf.parse(fromDateStr);
				   toDate = sdf.parse(toDateStr);
				 } 
			    
			    List<Long> impactLevelIds = new ArrayList<Long>();
			    impactLevelIds = splitAndAssignValuesToList(impactLevelIdsStr,impactLevelIds);
			    
			      List<Object[]> rtrnUsrAccssLvlIdAndVlusObjLst=activityMemberAccessLevelDAO.getLocationLevelAndValuesByActivityMembersIdForOrganization(activityMemberId);
				 if(rtrnUsrAccssLvlIdAndVlusObjLst != null && rtrnUsrAccssLvlIdAndVlusObjLst.size() > 0){
					 locationAccessLevelId=(Long) rtrnUsrAccssLvlIdAndVlusObjLst.get(0)[0];
					 for(Object[] param:rtrnUsrAccssLvlIdAndVlusObjLst){
						 locationValues.add(commonMethodsUtilService.getLongValueForObject(param[1]));
					 }
				 }
					List<Long> alertTypeList = new ArrayList<Long>();
					List<Long> editionList = new ArrayList<Long>();
					if(alertTypeId != null){
						if(alertTypeId.longValue() != 0L){
						   alertTypeList.add(alertTypeId);
						}
					}
					
					if(editionTypeId != null){
						if(editionTypeId.longValue() != 0L ){
							if(editionTypeId.longValue() == 1L){
								editionList.add(editionTypeId);
							}else if(editionTypeId.longValue() == 2L){
								editionList.add(editionTypeId);
								editionList.add(3L);
							}
						}
					}
				  Set<Long> allTypeTdpCadreIds = new HashSet<Long>(0);
				  Map<Long,Set<Long>> statusWiseAlertCntMap = new HashMap<Long, Set<Long>>();
				  Set<Long> totalAletCntSt = new HashSet<Long>(0);
				  //Calculating public representative type alert count  
				  List<Object[]> rtrnPblcRprsnttvTypAlrtDtlsObjLst = alertDAO.getPublicRepresentativeTypeAlertDtlsForOrganization(locationAccessLevelId,locationValues,stateId,impactLevelIds, fromDate, toDate,alertTypeList,editionList);
				  mergeStatusWiseAlertCnt(rtrnPblcRprsnttvTypAlrtDtlsObjLst,statusWiseAlertCntMap,allTypeTdpCadreIds,totalAletCntSt,null);
				  setDatatoFinalList(prepareTempalate(),statusWiseAlertCntMap,totalAletCntSt,resultList,"Public Representative");
				  //Calculating party committee type alert count
				  List<Object[]> rtrnPrtyCmmttAlrtDtlsObjLst = alertDAO.getPartyCommitteeTypeAlertDtlsForOrganization(locationAccessLevelId,locationValues,stateId,impactLevelIds, fromDate, toDate,alertTypeList,editionList);
				  mergeStatusWiseAlertCnt(rtrnPrtyCmmttAlrtDtlsObjLst,statusWiseAlertCntMap,allTypeTdpCadreIds,totalAletCntSt,null);
				  setDatatoFinalList(prepareTempalate(),statusWiseAlertCntMap,totalAletCntSt,resultList,"Party Committee");
				  //Calculating program type alert count
				  List<Object[]> rtrnPrgrmCmmttAlrtDtlsOblLst = alertDAO.getProgramCommitteeTypeAlertDtlsForOrganization(locationAccessLevelId,locationValues,stateId,impactLevelIds, fromDate, toDate,alertTypeList,editionList);
				  mergeStatusWiseAlertCnt(rtrnPrgrmCmmttAlrtDtlsOblLst,statusWiseAlertCntMap,allTypeTdpCadreIds,totalAletCntSt,null);
				  setDatatoFinalList(prepareTempalate(),statusWiseAlertCntMap,totalAletCntSt,resultList,"Program Committee");
				  //Calculating other type alert count
				  List<Object[]> rtrnAllAlertCntDls = alertDAO.getAllAlertDtlsForOrganization(locationAccessLevelId,locationValues,stateId,impactLevelIds, fromDate, toDate,alertTypeList,editionList);
				  mergeStatusWiseAlertCnt(rtrnAllAlertCntDls,statusWiseAlertCntMap,null,totalAletCntSt,allTypeTdpCadreIds);
				  setDatatoFinalList(prepareTempalate(),statusWiseAlertCntMap,totalAletCntSt,resultList,"Others");
				  
		 }catch(Exception e){
			 LOG.error("Exception in getAssignGroupTypeAlertDtlsByImpactLevelWise()",e);	 
		 }
		 return resultList;
	 }
	 
	 public void mergeStatusWiseAlertCnt(List<Object[]> objLst,Map<Long,Set<Long>> statusWiseAlertCntMap, Set<Long> allTypeTdpCadreIds,Set<Long> totalAletCntSt,Set<Long> allTdpCadreIds){
		 try{
			 if(objLst != null && objLst.size() > 0){
				 for(Object[] param:objLst){
					     Long tdpCadreId = commonMethodsUtilService.getLongValueForObject(param[2]);
					     if(allTdpCadreIds != null){ // merge other type alert 
					    	   if(!allTdpCadreIds.contains(tdpCadreId)){
					    		   Set<Long> alertCntSet = statusWiseAlertCntMap.get(commonMethodsUtilService.getLongValueForObject(param[0]));//statusId
								    if(alertCntSet == null){
								    	alertCntSet = new HashSet<Long>();
								    	statusWiseAlertCntMap.put(commonMethodsUtilService.getLongValueForObject(param[0]), alertCntSet);
								    }
								    alertCntSet.add(commonMethodsUtilService.getLongValueForObject(param[1]));//alert id
								    totalAletCntSt.add(commonMethodsUtilService.getLongValueForObject(param[1]));   
					    	   }
					     }else{
				    	    Set<Long> alertCntSet = statusWiseAlertCntMap.get(commonMethodsUtilService.getLongValueForObject(param[0]));//statusId
						    if(alertCntSet == null){
						    	alertCntSet = new HashSet<Long>();
						    	statusWiseAlertCntMap.put(commonMethodsUtilService.getLongValueForObject(param[0]), alertCntSet);
						    }
						    alertCntSet.add(commonMethodsUtilService.getLongValueForObject(param[1]));//alert id
						    allTypeTdpCadreIds.add(tdpCadreId);
						    totalAletCntSt.add(commonMethodsUtilService.getLongValueForObject(param[1])); 
					     }
				 }
			 }
		 }catch(Exception e){
			 LOG.error("Exception in mergeStatusWiseAlertCnt()",e); 
		 }
	 }
	 
	 public Map<Long,AlertOverviewVO> prepareTempalate(){
		 Map<Long,AlertOverviewVO> alertStatusMap = new HashMap<Long, AlertOverviewVO>();
		 try{
			 List<Object[]> rtrnStatusLst = alertStatusDAO.getAllStatus();
			 if(rtrnStatusLst != null && rtrnStatusLst.size() > 0){
				 for(Object[] param:rtrnStatusLst){
					 Long statusId = commonMethodsUtilService.getLongValueForObject(param[0]);
					 if(statusId != 1l && statusId != 6l && statusId != 7l){//except pending - 1 ,Action Not Required - 6  and Duplicate status - 7
						 AlertOverviewVO statusVO = new AlertOverviewVO();
						 statusVO.setStatusTypeId(statusId);
						 statusVO.setStatusType(commonMethodsUtilService.getStringValueForObject(param[1]));
						 alertStatusMap.put(statusVO.getStatusTypeId(),statusVO);
					 }
				 }
			 }
		 }catch(Exception e){
			 LOG.error("Exception in prepareTempalate()",e);  
		 }
		 return alertStatusMap;
	 }
	 
	 public void setDatatoFinalList(Map<Long,AlertOverviewVO> alertStatusMap,Map<Long,Set<Long>> statusWiseAlertCntMap,Set<Long> totalAletCntSt,List<AlertOverviewVO> resultList,String type){
		 try{
			 if(alertStatusMap != null && alertStatusMap.size() > 0){
				 for(Entry<Long,AlertOverviewVO> entry:alertStatusMap.entrySet()){
					 if(statusWiseAlertCntMap.get(entry.getKey()) != null && statusWiseAlertCntMap.get(entry.getKey()).size() > 0){
						 entry.getValue().setAlertCount(new Long(statusWiseAlertCntMap.get(entry.getKey()).size()));
						 entry.getValue().setAlertCountPer(calculatePercantage(entry.getValue().getAlertCount(), new Long(totalAletCntSt.size())));
					 }
				 }
			 }
			   AlertOverviewVO statusTypeVO = new AlertOverviewVO();
			   statusTypeVO.setName(type);
			   statusTypeVO.setTotalAlertCnt(new Long(totalAletCntSt.size()));
			   statusTypeVO.setSubList1(new ArrayList<AlertOverviewVO>(alertStatusMap.values()));
			   if(statusTypeVO.getSubList1() != null && statusTypeVO.getSubList1().size() > 0){
				   statusTypeVO.getSubList1().get(0).setTotalAlertCnt(statusTypeVO.getTotalAlertCnt());   
			   }
			   resultList.add(statusTypeVO);
			   //clear total alert set
			   totalAletCntSt.clear();
			   //clear status wise map
			   statusWiseAlertCntMap.clear();
		 }catch(Exception e){
			 LOG.error("Exception in setDatatoFinalList()",e); 
		 }
	 }
	 
	 public List<AlertCommentVO> getTotalAlertGroupByDist(String fromDateStr, String toDateStr, Long stateId,String scopeIdListStr, Long activityMemberId,Long alertTypeId, Long editionId){
			LOG.info("Entered in getTotalAlertGroupByDist() method of AlertsNewsPortalService{}");
			try{
				Date fromDate = null;           
				Date toDate = null;
				SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
				if(fromDateStr != null && fromDateStr.trim().length() > 0 && toDateStr != null && toDateStr.trim().length() > 0){
					fromDate = sdf.parse(fromDateStr);
					toDate = sdf.parse(toDateStr);
				}
				List<Long> alertTypeList = new ArrayList<Long>();
				List<Long> editionList = new ArrayList<Long>();
				if(alertTypeId != null){
					if(alertTypeId.longValue() == 0L){
						
					}else{
						alertTypeList.add(alertTypeId);
					}
				}
				
				if(editionId != null){
					if(editionId.longValue() == 0L){
						
					}else if(editionId.longValue() == 1L){
						editionList.add(editionId);
					}else if(editionId.longValue() == 2L){
						editionList.add(editionId);
						editionList.add(3L);
					}
				}
				
				List<Long> scopeIdList = new ArrayList<Long>();
				scopeIdList = splitAndAssignValuesToList(scopeIdListStr,scopeIdList);
				
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
				
				//convert parliament into constituency.
				if(userAccessLevelId.longValue() == 4L){
					List<Long> parliamentAssemlyIds = parliamentAssemblyDAO.getAssemblyConstituencyforParliament(userAccessLevelValues);
					userAccessLevelId = 5L;
					userAccessLevelValues.clear();
					userAccessLevelValues.addAll(parliamentAssemlyIds);      
				}
				
				List<AlertCommentVO> alertCountList = new ArrayList<AlertCommentVO>();
				Map<Long,AlertCommentVO> statusMap = new LinkedHashMap<Long, AlertCommentVO>();
				
				//get total alert group by district
				List<Object[]> alertList = alertDAO.getTotalAlertGroupByDistForOrganization(fromDate,toDate,stateId,scopeIdList,userAccessLevelId,userAccessLevelValues,alertTypeList,editionList);
				if(alertList != null && alertList.size() > 0){
					for(Object[] param : alertList){
						if(param[0] != null){
							Long locId = commonMethodsUtilService.getLongValueForObject(param[0]);
							Long stsId = commonMethodsUtilService.getLongValueForObject(param[2]);
							AlertCommentVO statusvo = statusMap.get(stsId);
							if(statusvo == null){
								statusvo = new AlertCommentVO();
								statusvo.setStatusId(stsId);
								statusvo.setStatus(commonMethodsUtilService.getStringValueForObject(param[3]));
								
								AlertCommentVO distvo = new AlertCommentVO();
								distvo.setLocationId(locId);
								distvo.setLocaitonName(commonMethodsUtilService.getStringValueForObject(param[1]));
								distvo.setCount(commonMethodsUtilService.getLongValueForObject(param[4]));
								statusvo.getSublist1().add(distvo);
								
								statusMap.put(stsId, statusvo);
							}
							else{
								AlertCommentVO distvo = new AlertCommentVO();
								distvo.setLocationId(locId);
								distvo.setLocaitonName(commonMethodsUtilService.getStringValueForObject(param[1]));
								distvo.setCount(commonMethodsUtilService.getLongValueForObject(param[4]));
								statusvo.getSublist1().add(distvo);
							}
						}
					}
				}
				
				if(statusMap != null){
					alertCountList = new ArrayList<AlertCommentVO>(statusMap.values());
				}
				return alertCountList;
			}catch(Exception e){  
				e.printStackTrace();
				LOG.error("Error occured getTotalAlertGroupByDist() method of AlertsNewsPortalService{}");
			}
			return null;
		}
	 
	 public List<AlertCommentVO> getDepartmentWiseStatusWiseCounts(String fromDateStr,String toDateStr,Long stateId,String scopeIdListStr){
		 List<AlertCommentVO> returnList = new ArrayList<AlertCommentVO>();
		 try {
			 	Map<Long,AlertCommentVO> deptmap = new LinkedHashMap<Long, AlertCommentVO>();
			 	Date fromDate = null;           
				Date toDate = null;
				SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
				
				if(fromDateStr != null && fromDateStr.trim().length() > 0 && toDateStr != null && toDateStr.trim().length() > 0){
					fromDate = sdf.parse(fromDateStr);
					toDate = sdf.parse(toDateStr);
				}
				
				List<Long> scopeIdList = new ArrayList<Long>();
				scopeIdList = splitAndAssignValuesToList(scopeIdListStr,scopeIdList);
				
				List<Object[]> statusList = alertStatusDAO.getAllStatus();
				
				List<Object[]> list = alertCandidateDAO.getDeptWiseStatusWiseAlerts(fromDate, toDate, stateId);
				if(list != null && !list.isEmpty()){
					for (Object[] obj : list) {
						Long deptId = Long.valueOf(obj[0] != null ? obj[0].toString():"0");
						Long statusId = Long.valueOf(obj[2] != null ? obj[2].toString():"0");
						AlertCommentVO vo = deptmap.get(deptId);
						if(vo == null){
							vo = new AlertCommentVO();
							
							vo.setStatusId(deptId);
							vo.setStatus(obj[1] != null ? obj[1].toString():"");
							vo.setSublist1(setStatusList(statusList));
							AlertCommentVO stsvo = getMatchedVO(vo.getSublist1(), statusId);
							stsvo.setStatus(obj[3] != null ? obj[3].toString():"");
							stsvo.setCount(Long.valueOf(obj[4] != null ? obj[4].toString():"0"));
							
							deptmap.put(deptId, vo);
						}
						else{
							AlertCommentVO stsvo = getMatchedVO(vo.getSublist1(), statusId);
							stsvo.setStatus(obj[3] != null ? obj[3].toString():"");
							stsvo.setCount(Long.valueOf(obj[4] != null ? obj[4].toString():"0"));
						}
					}
				}
				
				if(deptmap != null){
					returnList = new ArrayList<AlertCommentVO>(deptmap.values());
				}
			
		} catch (Exception e) {
			e.printStackTrace();
			LOG.error("Error occured getDepartmentWiseStatusWiseCounts() method of AlertsNewsPortalService{}");
		}
		 return returnList;
	 }
	 
	 public List<AlertCommentVO> setStatusList(List<Object[]> statusArr){
		 List<AlertCommentVO> returnList = new ArrayList<AlertCommentVO>();
		 try {
			if(statusArr != null && !statusArr.isEmpty()){
				for (Object[] obj : statusArr) {
					AlertCommentVO vo = new AlertCommentVO();
					vo.setStatusId(Long.valueOf(obj[0] != null ? obj[0].toString():"0"));
					vo.setStatus(obj[1] != null ? obj[1].toString():"");
					returnList.add(vo);
				}
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			LOG.error("Error occured setStatusList() method of AlertsNewsPortalService{}");
		}
		 return returnList;
	 }
	 
	 public AlertCommentVO getMatchedVO(List<AlertCommentVO> statusList,Long statusId){
		   try{
			   if(statusList == null || statusList.size() == 0)
				   return null;
			   for(AlertCommentVO vo:statusList){
				   if(vo.getStatusId().equals(statusId)){
					   return vo;
				   }
			   }
		   }catch(Exception e){
			   LOG.error("Error occured getMatchedVO() method of AlertsNewsPortalService{}",e);  
		   }
		   return null;
	   }
	 
	 
	 public List<AlertDataVO> getAlertsData(Long alertId)
		{
			List<AlertDataVO> returnList = new ArrayList<AlertDataVO>();
			
			List<Long> alertIds = new ArrayList<Long>();
			try{
				 List<Object[]> list = alertDAO.getAlertsData(alertId);
				 Object[] sourceDtls = alertDAO.getSourceDtlsByAlertId(alertId);
				 String alertSource = "";
				 if(sourceDtls != null){
					 if(commonMethodsUtilService.getLongValueForObject(sourceDtls[0]).longValue() == 1L){//manual
						alertSource = commonMethodsUtilService.getStringValueForObject(sourceDtls[2]);
					 }else if(commonMethodsUtilService.getLongValueForObject(sourceDtls[0]).longValue() == 2L){//print
						 if(sourceDtls[6] != null){
							 alertSource = commonMethodsUtilService.getStringValueForObject(sourceDtls[6]);
						 }else{
							 alertSource = commonMethodsUtilService.getStringValueForObject(sourceDtls[2]);
						 }
						 
					 }else if(commonMethodsUtilService.getLongValueForObject(sourceDtls[0]).longValue() == 3L){//electronic 
						 if(sourceDtls[8] != null){
							 alertSource = commonMethodsUtilService.getStringValueForObject(sourceDtls[8]);
						 }else{
							 alertSource = commonMethodsUtilService.getStringValueForObject(sourceDtls[2]);
						 }
						 
					 }  
				 }
				 if(list != null && list.size() > 0)
				 {
					 
					 Map<Long,Long> alertCategoryMap = new HashMap<Long, Long>();
					 
					 for(Object[] params : list)
					 {
						 AlertDataVO alertVO = (AlertDataVO) setterAndGetterUtilService.getMatchedVOfromList(returnList, "id",commonMethodsUtilService.getStringValueForObject( params[0]).toString());
						 if(alertVO == null)
						 {
							 alertVO = new AlertDataVO(); 
							 returnList.add(alertVO);
							 if(!alertIds.contains((Long)params[0]))
								 alertIds.add((Long)params[0]);
						 }
						 alertVO.setId((Long)params[0]);
						 alertVO.setTitle(params[25] != null ? params[25].toString() : "");
						 alertVO.setDesc(commonMethodsUtilService.getStringValueForObject(params[1]).toString());
						 alertVO.setAlertSource(alertSource);
						 alertVO.setDate(params[2] != null? params[2].toString():"");
						 alertVO.setAlertType(params[3] != null ? params[3].toString() : "");
						 alertVO.setUserType(params[4] != null ? params[4].toString() : "");
						 alertVO.setSeverity(params[5] != null ? params[5].toString() : "");
						 alertVO.setRegionScopeId(params[6] != null ? (Long)params[6] : null);
						 alertVO.setRegionScope(params[26] != null ?params[26].toString() : "");
						 alertVO.setStatusId(params[8] != null ? (Long)params[8] : null);
						 alertVO.setStatus(params[9] != null ?params[9].toString() : "");
						 LocationVO locationVO = new LocationVO();
						 locationVO.setWardId(params[23] != null ? (Long)params[23] : null);
						 locationVO.setWardName(params[24] != null ? params[24].toString() : "");
						 locationVO.setStateId(params[21] != null ? (Long)params[21] : null);
						 locationVO.setState(params[22] != null ? params[22].toString() : "");
						 locationVO.setDistrictId(params[16] != null ? (Long)params[16] : null);
						 locationVO.setDistrictName(params[17] != null ?params[17].toString() : "");
						 locationVO.setConstituencyId(params[19] != null ? (Long)params[19] : null);
						 locationVO.setConstituencyName(params[20] != null ? params[20].toString() : "");
						 locationVO.setTehsilId(params[10] != null ? (Long)params[10] : null);
						 locationVO.setTehsilName(params[11] != null ? params[11].toString() : "");
						 locationVO.setVillageId(params[12] != null ? (Long)params[12] : null);
						 locationVO.setVillageName(params[13] != null ? params[13].toString() : "");
						 locationVO.setLocalBodyId(params[14] != null ? (Long)params[14] : null);
						 
						 alertVO.setCategoryId(params[27] != null ? (Long)params[27] : null);
						 alertVO.setCategory(params[28] != null ? params[28].toString() : "");
						 alertVO.setImageUrl(params[29] != null ? params[29].toString() : "");
						 alertVO.setAlertCategoryTypeId(params[30] != null ? (Long)params[30] : null);
						 
						 String eleType = params[18] != null ? params[18].toString() : "";
						 locationVO.setLocalEleBodyName(params[15] != null ? params[15].toString() +" "+eleType : "");
						 
						 //category
						 alertCategoryMap.put((Long)params[0], alertVO.getCategoryId());
						 
						alertVO.setLocationVO(locationVO);
						 
						
					 }
					 if(alertIds != null && alertIds.size() > 0)
					 {
						 List<Object[]> candiateCnts = null;
						 if(alertCategoryMap.get(alertId) !=null && alertCategoryMap.get(alertId)>0l && alertCategoryMap.get(alertId) !=1l){
							
							 //0.alertId,1.candidateId,2.candidateName,3.designation,4.organization,5.impactId,6.impact,7.paCandidateId,
							 //8.membershipNo,9.image
							 List<Long> aleds = new ArrayList<Long>();
							 aleds.add(alertId);
							 List<Object[]> newsAlertCandidates = alertCandidateDAO.getInvolvedCandidateDetailsOfAlert(aleds);
							 setNewsAlertCandidateData(newsAlertCandidates,returnList);
							 
							 //total Involved Candidates
							 candiateCnts = alertCandidateDAO.getAlertNewsCandidateCount(alertIds);
							 
						 }else{						 
							 List<Object[]> alertCandidates = alertCandidateDAO.getAlertCandidatesData(alertIds);
							 setAlertCandidateData(alertCandidates,returnList);
							 
							 //total Involved Candidates
							 candiateCnts = alertCandidateDAO.getAlertCandidateCount(alertIds);
							 
						 }
						 	
						if(candiateCnts !=null && candiateCnts.size()>0){
							 for(Object[] params : candiateCnts)
							 {
								 AlertDataVO alertVO = (AlertDataVO) setterAndGetterUtilService.getMatchedVOfromList(returnList, "id", commonMethodsUtilService.getStringValueForObject(params[1]).toString());
									 if(alertVO != null)
									 {
										 alertVO.setCount((Long)params[0]);
									 }
							 }
						}					 
					 }
					 
					 
				 }
			}
			catch(Exception e)
			{
				e.printStackTrace();
			}
			return returnList;
			
		}
	 
	 //0.alertId,1.candidateId,2.candidateName,3.designation,4.organization,5.impactId,6.impact,7.paCandidateId
		public void setNewsAlertCandidateData(List<Object[]> list , List<AlertDataVO> dataList){
			try{
				
				if(list != null && list.size() > 0)
				{
					for(Object[] params : list)
					{
						AlertDataVO alertVo =(AlertDataVO) setterAndGetterUtilService.getMatchedVOfromList(dataList, "id", commonMethodsUtilService.getStringValueForObject(params[0]).toString());
						if(alertVo == null)
						{
							alertVo = new AlertDataVO();
							alertVo.setId((Long)params[0]);
							dataList.add(alertVo);
						}
						AlertDataVO candidateVO = null;
						if(params[1] !=null){
							candidateVO = (AlertDataVO) setterAndGetterUtilService.getMatchedVOfromList(alertVo.getSubList(), "id", commonMethodsUtilService.getStringValueForObject(params[1]).toString());
						}
						
						if(candidateVO == null)
						{
							candidateVO = new AlertDataVO();
							alertVo.getSubList().add(candidateVO);
						}
						
						candidateVO.setId(params[1] !=null ? (Long)params[1]:null);
						candidateVO.setName(params[2] !=null ? params[2].toString():"");
						candidateVO.setCommitteePosition(params[3] !=null ? params[3].toString():"");//designation
						
						candidateVO.setOrganization(params[4] !=null ? params[4].toString():"");
						candidateVO.setImpactId(params[5] !=null ? (Long)params[5]:null);
						candidateVO.setImpact(params[6] !=null ? params[6].toString():"");
						
						candidateVO.setCategoryId(params[7] !=null ? (Long)params[7]:null);//PaCandidateId
						candidateVO.setMembershipNo(params[8] !=null ? params[8].toString():"");
						candidateVO.setImage(params[9] !=null ? params[9].toString():"");
						candidateVO.setMobileNo(params[10] !=null ? params[10].toString():"");     
						
					}
				
				}
			}catch(Exception e){
				e.printStackTrace();
			}
		}
		
		public void setAlertCandidateData(List<Object[]> list,List<AlertDataVO> dataList)

		{
			List<Long> tdpCadreIdsList = new ArrayList<Long>();
			if(dataList == null)
				dataList = new ArrayList<AlertDataVO>();
			if(list != null && list.size() > 0)
			{
				for(Object[] params : list)
				{
					AlertDataVO alertVo =(AlertDataVO) setterAndGetterUtilService.getMatchedVOfromList(dataList, "id", commonMethodsUtilService.getStringValueForObject(params[0]).toString());
					if(alertVo == null)
					{
						alertVo = new AlertDataVO();
						alertVo.setId((Long)params[0]);
						dataList.add(alertVo);
					}
					AlertDataVO candidateVO = (AlertDataVO) setterAndGetterUtilService.getMatchedVOfromList(alertVo.getSubList(), "id", commonMethodsUtilService.getStringValueForObject(params[1]).toString());
					if(candidateVO == null)
					{
						candidateVO = new AlertDataVO();
						alertVo.getSubList().add(candidateVO);
					}
					if(!tdpCadreIdsList.contains((Long)params[1]))
						tdpCadreIdsList.add((Long)params[1]);
					candidateVO.setId((Long)params[1]);
					candidateVO.setName(params[2] != null ? params[2].toString() : "");
					 LocationVO locationVO = new LocationVO();
					 locationVO.setWardId(params[16] != null ? (Long)params[16] : null);
					 locationVO.setWardName(params[17] != null ? params[17].toString() : "");
					 locationVO.setStateId(params[14] != null ? (Long)params[14] : null);
					 locationVO.setState(params[15] != null ? params[15].toString() : "");
					 locationVO.setDistrictId(params[9] != null ? (Long)params[9] : null);
					 locationVO.setDistrictName(params[10] != null ?params[10].toString() : "");
					 locationVO.setConstituencyId(params[12] != null ? (Long)params[12] : null);
					 locationVO.setConstituencyName(params[13] != null ? params[13].toString() : "");
					 locationVO.setTehsilId(params[3] != null ? (Long)params[3] : null);
					 locationVO.setTehsilName(params[4] != null ? params[4].toString() : "");
					 locationVO.setVillageId(params[5] != null ? (Long)params[5] : null);
					 locationVO.setVillageName(params[6] != null ? params[6].toString() : "");
					 locationVO.setLocalBodyId(params[7] != null ? (Long)params[7] : null);
					 String eleType = params[11] != null ? params[11].toString() : "";
					 locationVO.setLocalEleBodyName(params[8] != null ? params[8].toString() +" "+eleType : "");
					 candidateVO.setLocationVO(locationVO);
					 
					 candidateVO.setImpactId(params[18] != null ? (Long)params[18] : null);
					 candidateVO.setImpact(params[19] != null ? params[19].toString() : "");
					 candidateVO.setImage(params[20] != null ? params[20].toString() : "");
					 candidateVO.setMobileNo(params[21] != null ? params[21].toString() : "");
					 candidateVO.setMembershipNo(params[22] != null ? params[22].toString() : "");
					 if(dataList != null && dataList.size() > 0)
					 setCurrentDesignationForCadre(dataList.get(0).getSubList(), tdpCadreIdsList);
				}
				
			}
			
		}
		
		public void setCurrentDesignationForCadre(List<AlertDataVO> cadreCommitteeList,List<Long> tdpCadreIdsList){
			List<Object[]> tdpCommitteeMemberList = tdpCommitteeMemberDAO.getTdpCommitteeMemberForTdpCadreIdList(tdpCadreIdsList);
			
			if(tdpCommitteeMemberList != null && tdpCommitteeMemberList.size()>0)
			{
				for (Object[] tdpCadre : tdpCommitteeMemberList) 
				{
					Long id = tdpCadre[0] != null ? Long.valueOf(tdpCadre[0].toString()):0L;
					String committeeName = tdpCadre[1] != null ? tdpCadre[1].toString():"";
					String positionName =  tdpCadre[2] != null ? tdpCadre[2].toString():"";
					Long LocationTypeId = tdpCadre[3] != null ? Long.valueOf(tdpCadre[3].toString()):0L;
					Long locationValue = tdpCadre[4] != null ? Long.valueOf(tdpCadre[4].toString()):0L;
					Long roleId = tdpCadre[5] != null ? Long.valueOf(String.valueOf(tdpCadre[5]).trim()):0L ;
					AlertDataVO cadreVO = (AlertDataVO) setterAndGetterUtilService.getMatchedVOfromList(cadreCommitteeList,"id",id.toString());
					if(cadreVO != null)
					{
						String location = null;
						if(locationValue.longValue() > 0L){
							//System.out.println("tdpCadreId :"+id+"  \t positionName  :"+positionName);
							location = cadreCommitteeService.getLocationName(LocationTypeId,locationValue);
							cadreVO.setCommitteeLocation(location);
						    cadreVO.setCommitteePosition(positionName);
						    cadreVO.setCommitteeName(committeeName);
						    cadreVO.setElectionType(tdpCadre[6] != null ? tdpCadre[6].toString():"");
						    if(cadreVO.getElectionType().trim().equalsIgnoreCase("Panchayat"))
						    {
						    	 cadreVO.setElectionType("Village/Ward ");
						    }
						    else if(cadreVO.getElectionType().trim().equalsIgnoreCase("Mandal"))
						    {
						    	 cadreVO.setElectionType("Mandal/Division/Town");
						    }
						    cadreVO.setVoterId(roleId);
					    }
				   }
			    }
			}
		}
		
		public List<AlertDataVO> getAlertAssignedCandidates(Long alertId)
		{
			List<AlertDataVO> dataList = new ArrayList<AlertDataVO>();
			try{
				List<Long> alertIds = new ArrayList<Long>();
				
				alertIds.add(alertId);
				List<Object[]> list = alertCandidateDAO.getAlertAssignedCandidates(alertIds);
				setAlertAssignedCandidateDataNew(list,dataList);
			
			}
			catch (Exception e) {
				e.printStackTrace();
				LOG.error("Exception in getAlertAssignedCandidates()",e);	
			}
			return dataList;
		}
		
		public void setAlertAssignedCandidateDataNew(List<Object[]> list,List<AlertDataVO> dataList)

		{
			List<Long> alertIds = new ArrayList<Long>();
			List<Long> tdpCadreIdsList = new ArrayList<Long>();
			if(dataList == null)
				dataList = new ArrayList<AlertDataVO>();
			if(list != null && list.size() > 0)
			{
				for(Object[] params : list)
				{
					AlertDataVO alertVo =(AlertDataVO) setterAndGetterUtilService.getMatchedVOfromList(dataList, "id", commonMethodsUtilService.getStringValueForObject(params[0]).toString());
					if(alertVo == null)
					{
						alertVo = new AlertDataVO();
						alertVo.setId((Long)params[0]);
						dataList.add(alertVo);
						alertIds.add(alertVo.getId());
					}
					AlertDataVO candidateVO = (AlertDataVO) setterAndGetterUtilService.getMatchedVOfromList(alertVo.getSubList(), "id", commonMethodsUtilService.getStringValueForObject(params[1]).toString());
					if(candidateVO == null)
					{
						candidateVO = new AlertDataVO();
						alertVo.getSubList().add(candidateVO);
					}
					if(!tdpCadreIdsList.add((Long)params[1]));
					tdpCadreIdsList.add((Long)params[1]);
					candidateVO.setId((Long)params[1]);
					candidateVO.setName(params[2] != null ? params[2].toString() : "");
					 LocationVO locationVO = new LocationVO();
					 locationVO.setWardId(params[16] != null ? (Long)params[16] : null);
					 locationVO.setWardName(params[17] != null ? params[17].toString() : "");
					 locationVO.setStateId(params[14] != null ? (Long)params[14] : null);
					 locationVO.setState(params[15] != null ? params[15].toString() : "");
					 locationVO.setDistrictId(params[9] != null ? (Long)params[9] : null);
					 locationVO.setDistrictName(params[10] != null ?params[10].toString() : "");
					 locationVO.setConstituencyId(params[12] != null ? (Long)params[12] : null);
					 locationVO.setConstituencyName(params[13] != null ? params[13].toString() : "");
					 locationVO.setTehsilId(params[3] != null ? (Long)params[3] : null);
					 locationVO.setTehsilName(params[4] != null ? params[4].toString() : "");
					 locationVO.setVillageId(params[5] != null ? (Long)params[5] : null);
					 locationVO.setVillageName(params[6] != null ? params[6].toString() : "");
					 locationVO.setLocalBodyId(params[7] != null ? (Long)params[7] : null);
					 String eleType = params[11] != null ? params[11].toString() : "";
					 locationVO.setLocalEleBodyName(params[8] != null ? params[8].toString() +" "+eleType : "");
					 candidateVO.setLocationVO(locationVO);
					 
					/* candidateVO.setImpactId(params[17] != null ? (Long)params[17] : null);
					 candidateVO.setImpact(params[18] != null ? params[18].toString() : "");*/
					 candidateVO.setImage(params[18] != null ? params[18].toString() : "");
					candidateVO.setMobileNo(params[19] != null ? params[19].toString() : "");
				}
				 if(dataList != null && dataList.size() > 0)
				setCurrentDesignationForCadre(dataList.get(0).getSubList(), tdpCadreIdsList);
				 
				 
				 //alertCommentDetails
				 
				 Map<Long,String> commentMap = new HashMap<Long, String>(); 
				 if(alertIds !=null && alertIds.size()>0 && alertIds.get(0) !=null && tdpCadreIdsList !=null && tdpCadreIdsList.size() >0){
					 //0.tdpCadreId,1.comment
					 List<Object[]> alertCommentObj = alertCommentAssigneeDAO.getAssignedCandidateAlertComment(alertIds.get(0), tdpCadreIdsList);
					 if(alertCommentObj !=null && alertCommentObj.size()>0){
						 for (Object[] obj : alertCommentObj) {						 
							 String comment = commentMap.get(obj[0] !=null ? (Long)obj[0]:null);						 
							 if(comment == null){
								 comment = new String();
								 comment=obj[1] !=null ? obj[1].toString():"";
								 commentMap.put((Long)obj[0], comment);							 
							 }else{
								 comment=comment+","+obj[1] !=null ? obj[1].toString():"";
							 }
						}
					 }
				 }
					 
				 if(commentMap !=null && commentMap.size()>0){
					 AlertDataVO alertVo =(AlertDataVO) setterAndGetterUtilService.getMatchedVOfromList(dataList, "id", alertIds.get(0).toString());
					 if(alertVo !=null){					 
						 for (Entry<Long, String> tdpCadre : commentMap.entrySet()) {
							 AlertDataVO candidateVO = (AlertDataVO) setterAndGetterUtilService.getMatchedVOfromList(alertVo.getSubList(), "id",tdpCadre.getKey().toString());						 
							 if(candidateVO !=null){
								 candidateVO.setComment(tdpCadre.getValue());
							 }						 
						}
					 }
				 }
			}
			
		}
		
		public List<AlertCommentVO> getAlertStatusCommentsTrackingDetails(Long alertId){
			LOG.info("Entered in getAlertStatusCommentsTrackingDetails() method");
			List<StatusTrackingVO> resultList = null;
			try{
				Map<Long,String> idAndNameMap = new HashMap<Long,String>();
				
				Map<Long,Set<String>> statusIdAndDateIdListMap = new LinkedHashMap<Long,Set<String>>();
				Set<String> dateIdList = null;//new HashSet<String>(); 
				
				Map<String,Set<Long>> dateIdAndCmtListMap = new HashMap<String,Set<Long>>();
				Set<Long> commentIdList = null;
				Map<Long,List<KeyValueVO>> alertTrackingDocumentsMap = new HashMap<Long, List<KeyValueVO>>();//alerttrackingid,docsList
				
				Map<Long,List<AlertCommentVO>> commentIdAndCommentDtlsMap = new HashMap<Long,List<AlertCommentVO>>();
				List<AlertCommentVO>  alertCommentDtlsList = null;
				AlertCommentVO alertCommentVO = null;
				List<Object[]> list = alertTrackingDAO.getAlertTrackingDetailsList(alertId,true);
				
				if(list != null && list.size() > 0){
					List<Long> alertTrackingIds = new ArrayList<Long>(0);
					List<Long> cadreIds = new ArrayList<Long>(0);
					for (Object[] objects : list) {
						if(objects[6] != null)
							cadreIds.add((Long)objects[6]);
						if(objects[11] != null)
							alertTrackingIds.add((Long)objects[11]);
					}
					 
					if(cadreIds != null && cadreIds.size() > 0){
						List<Object[]> objList = tdpCadreDAO.getCadreFormalDetails(cadreIds);
						if(objList != null && objList.size() > 0){
							for (Object[] objects : objList) {
								List<Object[]> matchedObjList = gatMatchedObject((Long)objects[0],list);
								if(matchedObjList != null && matchedObjList.size() > 0){
									for (Object[] objects2 : matchedObjList) {
										objects2[7]=objects[1].toString();
									}
								}
							}
						}
						
					}
					
					//get alert tracking documents
					if(alertTrackingIds != null && alertTrackingIds.size() > 0){
						List<Object[]> docsObjList = alertTrackingDocumentsDAO.getDocumentsForAlertTracking(alertTrackingIds);
						if(docsObjList != null && docsObjList.size() > 0){
							//0-trackingid,1-docid,2-path
							for (Object[] objects : docsObjList) {
								List<KeyValueVO> voList = null;
								if(alertTrackingDocumentsMap.get((Long)objects[0]) == null){
									voList = new ArrayList<KeyValueVO>(0);
									alertTrackingDocumentsMap.put((Long)objects[0], voList);
								}
								
								KeyValueVO vo = new KeyValueVO();
								vo.setId((Long)objects[1]);
								vo.setName(objects[2] != null?objects[2].toString():"");
								alertTrackingDocumentsMap.get((Long)objects[0]).add(vo);
								
							}
						}
					}
				}
				
				Map<Long,Long> statusOrderMap = new HashMap<Long, Long>(0);
				boolean noList = false;
				if(!commonMethodsUtilService.isListOrSetValid(list)){
					list = new ArrayList<Object[]>(0);
					noList = true;
				}
					
						List<Object[]> list1 = alertTrackingDAO.getAlertTrackingDetailsList(alertId,false);
					if(commonMethodsUtilService.isListOrSetValid(list1)){
						for (Object[] param : list1) {
							Long statusId= commonMethodsUtilService.getLongValueForObject(param[1]);
							if(noList){
								if(statusId == 1L)//for no assigned member alerts pending status
									list.add(param);
							}
							if(statusId == 8L)// verification status
								list.add(param);
						}					
					}
				//}
				SimpleDateFormat _24HourSDF = new SimpleDateFormat("HH:mm:ss");
				SimpleDateFormat _12HourSDF = new SimpleDateFormat("hh:mm a");
				if(list != null && list.size() > 0){   
					for(Object[] param : list){
						statusOrderMap.put(commonMethodsUtilService.getLongValueForObject(param[1]), commonMethodsUtilService.getLongValueForObject(param[10]));
						//for statusId and date list map
						dateIdList = statusIdAndDateIdListMap.get(commonMethodsUtilService.getLongValueForObject(param[1]));
						if(dateIdList != null){
							dateIdList.add(commonMethodsUtilService.getStringValueForObject(param[2])+":"+commonMethodsUtilService.getStringValueForObject(param[1]));
						}else{
							dateIdList = new LinkedHashSet<String>();  
							dateIdList.add(commonMethodsUtilService.getStringValueForObject(param[2])+":"+commonMethodsUtilService.getStringValueForObject(param[1]));
							statusIdAndDateIdListMap.put(commonMethodsUtilService.getLongValueForObject(param[1]), dateIdList);
						}
						//for dateId and list of comment id list
						commentIdList = dateIdAndCmtListMap.get(commonMethodsUtilService.getStringValueForObject(param[2])+":"+commonMethodsUtilService.getStringValueForObject(param[1]));
						if(commentIdList != null){
							commentIdList.add(commonMethodsUtilService.getLongValueForObject(param[4]));
						}else{
							commentIdList = new HashSet<Long>();
							commentIdList.add(commonMethodsUtilService.getLongValueForObject(param[4]));
							dateIdAndCmtListMap.put(commonMethodsUtilService.getStringValueForObject(param[2])+":"+commonMethodsUtilService.getStringValueForObject(param[1]), commentIdList);
						}  
						
						//for commentId and comment Dtls list map
						alertCommentDtlsList = commentIdAndCommentDtlsMap.get(commonMethodsUtilService.getLongValueForObject(param[4]));
						if(alertCommentDtlsList != null){   
							alertCommentVO = new AlertCommentVO();
							alertCommentVO.setCommentId(commonMethodsUtilService.getLongValueForObject(param[4]));
							alertCommentVO.setComment(commonMethodsUtilService.getStringValueForObject(param[5]));
							if(param[2] != null){
								Date _24HourDt = _24HourSDF.parse(commonMethodsUtilService.getStringValueForObject(param[3]));
								alertCommentVO.setTimeString(_12HourSDF.format(_24HourDt));
							}
							alertCommentVO.setCadreName(commonMethodsUtilService.getStringValueForObject(param[7]));
							alertCommentVO.setUserName(commonMethodsUtilService.getStringValueForObject(param[8]));
							alertCommentVO.setOrderNo(commonMethodsUtilService.getLongValueForObject(param[10]));
							if(alertTrackingDocumentsMap != null && alertTrackingDocumentsMap.size() > 0 && param[11] != null && alertTrackingDocumentsMap.get((Long)param[11]) != null){
								alertCommentVO.setDocList(alertTrackingDocumentsMap.get((Long)param[11]));
							}
							alertCommentDtlsList.add(alertCommentVO);
						}else{
							alertCommentVO = new AlertCommentVO();
							alertCommentVO.setCommentId(commonMethodsUtilService.getLongValueForObject(param[4]));
							alertCommentVO.setComment(commonMethodsUtilService.getStringValueForObject(param[5]));
							if(param[2] != null){
								Date _24HourDt = _24HourSDF.parse(commonMethodsUtilService.getStringValueForObject(param[3]));
								alertCommentVO.setTimeString(_12HourSDF.format(_24HourDt));
							}
							alertCommentVO.setCadreName(commonMethodsUtilService.getStringValueForObject(param[7]));
							alertCommentVO.setUserName(commonMethodsUtilService.getStringValueForObject(param[8]));
							alertCommentVO.setOrderNo(commonMethodsUtilService.getLongValueForObject(param[10]));
							if(alertTrackingDocumentsMap != null && alertTrackingDocumentsMap.size() > 0 && param[11] != null && alertTrackingDocumentsMap.get((Long)param[11]) != null){
								alertCommentVO.setDocList(alertTrackingDocumentsMap.get((Long)param[11]));
							}
							alertCommentDtlsList = new ArrayList<AlertCommentVO>();
							alertCommentDtlsList.add(alertCommentVO);
							commentIdAndCommentDtlsMap.put(commonMethodsUtilService.getLongValueForObject(param[4]), alertCommentDtlsList);
						}
						idAndNameMap.put(commonMethodsUtilService.getLongValueForObject(param[1]), commonMethodsUtilService.getStringValueForObject(param[9]));
					}
				}
				AlertCommentVO commentVO = null;
				//vo for each date
				AlertCommentVO commentVOForDate = null;
				List<AlertCommentVO> commentVOForDateList = null;
				//for multiuser involvement
				List<List<AlertCommentVO>> list2 = null;
				//final vo 
				List<AlertCommentVO> finalList = new CopyOnWriteArrayList<AlertCommentVO>();  
				if(statusIdAndDateIdListMap.size() > 0){  
					for(Entry<Long,Set<String>> entry : statusIdAndDateIdListMap.entrySet()){
						commentVO = new AlertCommentVO();
						commentVO.setStatusId(entry.getKey());
						commentVO.setStatus(idAndNameMap.get(entry.getKey()));
						commentVO.setOrderNo(statusOrderMap.get(commentVO.getStatusId()));
						dateIdList = (LinkedHashSet)entry.getValue();    
						if(dateIdList != null && dateIdList.size() > 0){
							commentVOForDateList = new ArrayList<AlertCommentVO>();
							for(String dateId : dateIdList){
								commentVOForDate = new AlertCommentVO();
								commentVOForDate.setDate(dateId.split(":")[0]);
								commentIdList = dateIdAndCmtListMap.get(dateId);
								if(commentIdList != null && commentIdList.size() > 0){
									list2 = new ArrayList<List<AlertCommentVO>>();
									for(Long cmtId : commentIdList){
										list2.add(commentIdAndCommentDtlsMap.get(cmtId));
									}
									//Collections.sort(list2, commentSort);
									commentVOForDate.setSublist(list2);  
								}
								commentVOForDateList.add(commentVOForDate);
							}
						}
						commentVO.setSublist2(commentVOForDateList);
						finalList.add(commentVO);
					}
				}
				if(finalList != null && finalList.size() > 0){
					List<AlertCommentVO> tempList = new ArrayList<AlertCommentVO>(0);
					List<Long> statusIdList = alertTrackingDAO.lastUpdatedstatus(alertId);
					for(AlertCommentVO param : finalList){  
						if(param.getStatusId().longValue() == statusIdList.get(0)){
							finalList.remove(param);
							finalList.add(param);  
						}
						tempList.add(param);
					}
					
					if(commonMethodsUtilService.isListOrSetValid(tempList)){
						Collections.sort(tempList,new Comparator<AlertCommentVO>() {
							public int compare(AlertCommentVO o1, AlertCommentVO o2) {
								if(o1.getOrderNo() != null && o1.getOrderNo()>0L && o2.getOrderNo() != null && o2.getOrderNo()>0L)
									return o1.getOrderNo().compareTo(o2.getOrderNo());
								else
									return 0;
							}
						});
						
						finalList.clear();
						finalList.addAll(tempList);
					}
					
				}  
				
				return finalList;   		
			}catch(Exception e){
				e.printStackTrace();
				LOG.error("Entered in getAppointmentStatusFlowTrackingDetails() method");
			}
			return null;
		}
		
		public List<Object[]> gatMatchedObject(Long cadreId,List<Object[]> objList){
	  		List<Object[]> returnObj = new ArrayList<Object[]>(0);
	  		if(objList != null && objList.size() > 0){
	  			for (Object[] objects : objList) {
					if(objects[6] != null && ((Long)objects[6]).equals(cadreId))
						returnObj.add(objects);
				}
	  		}
	  		return returnObj;
	  	}
		
		public AlertVerificationVO getAlertVerificationDtls(Long alertId){
		    AlertVerificationVO resultVO = new AlertVerificationVO();
		 	SimpleDateFormat sdf = new SimpleDateFormat("dd-MMM-yyyy");
			SimpleDateFormat _12HourSDF = new SimpleDateFormat("hh:mm a");
			SimpleDateFormat sdf1 = new SimpleDateFormat("hh:mm:ss");
			Map<Long,AlertVerificationVO> alertCommentsMap = new LinkedHashMap<Long, AlertVerificationVO>(0);
		  try{
			  Object[] alertStatusObj = verificationStatusDAO.getAertStausIdAndName(alertId);
			   if(alertStatusObj != null && alertStatusObj.length > 0){
				   resultVO.setAlertActionTypeStatusId((Long)alertStatusObj[0]);
				   resultVO.setActionTypeStatus(commonMethodsUtilService.getStringValueForObject(alertStatusObj[1]));
			   }
			
			   List<Object[]> rtnrObjList = verificationCommentsDAO.getAletConversationDtls(alertId);
			   
			    if(rtnrObjList != null && rtnrObjList.size() > 0){
			    	for(Object[] param:rtnrObjList){
			    		Long conversationId = commonMethodsUtilService.getLongValueForObject(param[0]);
			    		AlertVerificationVO commentVO = alertCommentsMap.get(conversationId);
			    		Long userTypeId = commonMethodsUtilService.getLongValueForObject(param[1]);
			    		if(commentVO == null){
			    			commentVO = new AlertVerificationVO();
			    			if(userTypeId == 1l){
			    				commentVO.setHeading("Program Committee Remarks");
			    			}else if(userTypeId == 2l){
			    				commentVO.setHeading("Info Cell Remarks");	
			    			}
			    			commentVO.setComments(commonMethodsUtilService.getStringValueForObject(param[2]));
			    			if(param[3] != null){
			    				commentVO.setUpdateTime(sdf.format(param[3]));	
			    			}
			    			if(param[4] != null){ 
			    				Date timeInDateFormat = sdf1.parse(param[4].toString());
			    				commentVO.setTime(_12HourSDF.format(timeInDateFormat));	
			    			}
			    			commentVO.setName(commonMethodsUtilService.getStringValueForObject(param[5])+" "+commonMethodsUtilService.getStringValueForObject(param[6]));//first and last name
			    			commentVO.setDocumentList(new ArrayList<String>());
			    			alertCommentsMap.put(conversationId, commentVO);
			    		}
			    		 String filePath = commonMethodsUtilService.getStringValueForObject(param[7]);
		    			 if(filePath.length() > 0){
		    				 commentVO.getDocumentList().add(filePath);
		    			 }
			    		
			    	}
			    }
			   if(alertCommentsMap != null && alertCommentsMap.size() > 0){
				   resultVO.setConversationList(new ArrayList<AlertVerificationVO>(alertCommentsMap.values()));  
			   }
		  }catch(Exception e){
			  LOG.error("Exception Occured in getAlertVerificationDtls() in ToursService", e);
		  }
		  return resultVO;
	  }
}
