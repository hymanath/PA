package com.itgrids.partyanalyst.service.impl;

import java.io.File;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionCallbackWithoutResult;
import org.springframework.transaction.support.TransactionTemplate;

import com.itgrids.partyanalyst.dao.IActivityAttributeDAO;
import com.itgrids.partyanalyst.dao.IActivityAttributeQuestionnaireInfoDAO;
import com.itgrids.partyanalyst.dao.IActivityDAO;
import com.itgrids.partyanalyst.dao.IActivityDocumentDAO;
import com.itgrids.partyanalyst.dao.IActivityInfoDocumentDAO;
import com.itgrids.partyanalyst.dao.IActivityLevelDAO;
import com.itgrids.partyanalyst.dao.IActivityLocationInfoDAO;
import com.itgrids.partyanalyst.dao.IActivityQuestionAnswerDAO;
import com.itgrids.partyanalyst.dao.IActivityQuestionnaireDAO;
import com.itgrids.partyanalyst.dao.IActivityQuestionnaireOptionDAO;
import com.itgrids.partyanalyst.dao.IActivityScopeDAO;
import com.itgrids.partyanalyst.dao.IActivitySubTypeDAO;
import com.itgrids.partyanalyst.dao.IActivityTypeDAO;
import com.itgrids.partyanalyst.dao.IAssemblyLocalElectionBodyDAO;
import com.itgrids.partyanalyst.dao.IAssemblyLocalElectionBodyWardDAO;
import com.itgrids.partyanalyst.dao.IBoothConstituencyElectionDAO;
import com.itgrids.partyanalyst.dao.IConstituencyDAO;
import com.itgrids.partyanalyst.dao.IDelimitationConstituencyAssemblyDetailsDAO;
import com.itgrids.partyanalyst.dao.IDelimitationConstituencyDAO;
import com.itgrids.partyanalyst.dao.IDelimitationConstituencyMandalDAO;
import com.itgrids.partyanalyst.dao.IDistrictDAO;
import com.itgrids.partyanalyst.dao.ILocalElectionBodyDAO;
import com.itgrids.partyanalyst.dao.ILocationInfoDAO;
import com.itgrids.partyanalyst.dao.IPanchayatDAO;
import com.itgrids.partyanalyst.dao.IStateDAO;
import com.itgrids.partyanalyst.dao.ITehsilDAO;
import com.itgrids.partyanalyst.dao.IUserAddressDAO;
import com.itgrids.partyanalyst.dto.ActivityVO;
import com.itgrids.partyanalyst.dto.BasicVO;
import com.itgrids.partyanalyst.dto.EventFileUploadVO;
import com.itgrids.partyanalyst.dto.IdNameVO;
import com.itgrids.partyanalyst.dto.LocationWiseBoothDetailsVO;
import com.itgrids.partyanalyst.dto.ResultCodeMapper;
import com.itgrids.partyanalyst.dto.ResultStatus;
import com.itgrids.partyanalyst.dto.SearchAttributeVO;
import com.itgrids.partyanalyst.model.Activity;
import com.itgrids.partyanalyst.model.ActivityDocument;
import com.itgrids.partyanalyst.model.ActivityInfoDocument;
import com.itgrids.partyanalyst.model.ActivityLevel;
import com.itgrids.partyanalyst.model.ActivityQuestionAnswer;
import com.itgrids.partyanalyst.model.ActivityScope;
import com.itgrids.partyanalyst.model.ActivitySubType;
import com.itgrids.partyanalyst.model.Constituency;
import com.itgrids.partyanalyst.model.LocalElectionBody;
import com.itgrids.partyanalyst.model.Panchayat;
import com.itgrids.partyanalyst.model.Tehsil;
import com.itgrids.partyanalyst.model.UserAddress;
import com.itgrids.partyanalyst.service.IActivityService;
import com.itgrids.partyanalyst.service.ICadreCommitteeService;
import com.itgrids.partyanalyst.service.IRegionServiceData;
import com.itgrids.partyanalyst.utils.CommonMethodsUtilService;
import com.itgrids.partyanalyst.utils.DateUtilService;
import com.itgrids.partyanalyst.utils.IConstants;
import com.itgrids.partyanalyst.utils.RandomNumberGeneraion;

public class ActivityService implements IActivityService{

	private static final Logger   LOG = Logger.getLogger(ActivityService.class);
	
	private IStateDAO stateDAO;
	private IDistrictDAO districtDAO;
	private IConstituencyDAO constituencyDAO;
	private IDelimitationConstituencyDAO delimitationConstituencyDAO;
	private IDelimitationConstituencyMandalDAO delimitationConstituencyMandalDAO;
	private IBoothConstituencyElectionDAO boothConstituencyElectionDAO;
	private IAssemblyLocalElectionBodyDAO assemblyLocalElectionBodyDAO;
	private IAssemblyLocalElectionBodyWardDAO assemblyLocalElectionBodyWardDAO;  
	private ILocalElectionBodyDAO localElectionBodyDAO;
	private IDelimitationConstituencyAssemblyDetailsDAO delimitationConstituencyAssemblyDetailsDAO;
	private ITehsilDAO tehsilDAO;
	private IPanchayatDAO panchayatDAO;
	private IRegionServiceData regionServiceDataImp;
	
	private IActivityTypeDAO activityTypeDAO;
	private IActivitySubTypeDAO activitySubTypeDAO;
	private IActivityDAO activityDAO;
	private IActivityLevelDAO activityLevelDAO;
	private IActivityScopeDAO activityScopeDAO;
	private IActivityLocationInfoDAO activityLocationInfoDAO;
	private ILocationInfoDAO locationInfoDAO;
	private ICadreCommitteeService cadreCommitteeService;
	private CommonMethodsUtilService commonMethodsUtilService = new CommonMethodsUtilService();
	private IActivityAttributeQuestionnaireInfoDAO activityAttributeQuestionnaireInfoDAO;
	private IActivityQuestionnaireDAO activityQuestionnaireDAO;
	private IActivityQuestionnaireOptionDAO activityQuestionnaireOptionDAO;
	private IActivityAttributeDAO activityAttributeDAO;
	private IActivityQuestionAnswerDAO activityQuestionAnswerDAO;
	private DateUtilService dateUtilService = new DateUtilService();
	private IActivityDocumentDAO activityDocumentDAO;
	private IActivityInfoDocumentDAO activityInfoDocumentDAO;
	private IUserAddressDAO userAddressDAO;
	private TransactionTemplate transactionTemplate = null;
	
	
	
	public DateUtilService getDateUtilService() {
		return dateUtilService;
	}
	public void setDateUtilService(DateUtilService dateUtilService) {
		this.dateUtilService = dateUtilService;
	}
	public IActivityDocumentDAO getActivityDocumentDAO() {
		return activityDocumentDAO;
	}
	public void setActivityDocumentDAO(IActivityDocumentDAO activityDocumentDAO) {
		this.activityDocumentDAO = activityDocumentDAO;
	}
	public IActivityInfoDocumentDAO getActivityInfoDocumentDAO() {
		return activityInfoDocumentDAO;
	}
	public void setActivityInfoDocumentDAO(
			IActivityInfoDocumentDAO activityInfoDocumentDAO) {
		this.activityInfoDocumentDAO = activityInfoDocumentDAO;
	}
	public IUserAddressDAO getUserAddressDAO() {
		return userAddressDAO;
	}
	public void setUserAddressDAO(IUserAddressDAO userAddressDAO) {
		this.userAddressDAO = userAddressDAO;
	}
	public IActivityQuestionAnswerDAO getActivityQuestionAnswerDAO() {
		return activityQuestionAnswerDAO;
	}
	public void setActivityQuestionAnswerDAO(
			IActivityQuestionAnswerDAO activityQuestionAnswerDAO) {
		this.activityQuestionAnswerDAO = activityQuestionAnswerDAO;
	}
	public IActivityQuestionnaireDAO getActivityQuestionnaireDAO() {
		return activityQuestionnaireDAO;
	}
	public void setActivityQuestionnaireDAO(
			IActivityQuestionnaireDAO activityQuestionnaireDAO) {
		this.activityQuestionnaireDAO = activityQuestionnaireDAO;
	}
	public IActivityQuestionnaireOptionDAO getActivityQuestionnaireOptionDAO() {
		return activityQuestionnaireOptionDAO;
	}
	public void setActivityQuestionnaireOptionDAO(
			IActivityQuestionnaireOptionDAO activityQuestionnaireOptionDAO) {
		this.activityQuestionnaireOptionDAO = activityQuestionnaireOptionDAO;
	}
	public IActivityAttributeDAO getActivityAttributeDAO() {
		return activityAttributeDAO;
	}
	public void setActivityAttributeDAO(IActivityAttributeDAO activityAttributeDAO) {
		this.activityAttributeDAO = activityAttributeDAO;
	}
	public IActivityAttributeQuestionnaireInfoDAO getActivityAttributeQuestionnaireInfoDAO() {
		return activityAttributeQuestionnaireInfoDAO;
	}
	public void setActivityAttributeQuestionnaireInfoDAO(
			IActivityAttributeQuestionnaireInfoDAO activityAttributeQuestionnaireInfoDAO) {
		this.activityAttributeQuestionnaireInfoDAO = activityAttributeQuestionnaireInfoDAO;
	}
	public CommonMethodsUtilService getCommonMethodsUtilService() {
		return commonMethodsUtilService;
	}
	public void setCommonMethodsUtilService(
			CommonMethodsUtilService commonMethodsUtilService) {
		this.commonMethodsUtilService = commonMethodsUtilService;
	}
	public IStateDAO getStateDAO() {
		return stateDAO;
	}
	public void setStateDAO(IStateDAO stateDAO) {
		this.stateDAO = stateDAO;
	}
	public IDistrictDAO getDistrictDAO() {
		return districtDAO;
	}
	public void setDistrictDAO(IDistrictDAO districtDAO) {
		this.districtDAO = districtDAO;
	}
	public IConstituencyDAO getConstituencyDAO() {
		return constituencyDAO;
	}
	public void setConstituencyDAO(IConstituencyDAO constituencyDAO) {
		this.constituencyDAO = constituencyDAO;
	}
	public IDelimitationConstituencyDAO getDelimitationConstituencyDAO() {
		return delimitationConstituencyDAO;
	}
	public void setDelimitationConstituencyDAO(
			IDelimitationConstituencyDAO delimitationConstituencyDAO) {
		this.delimitationConstituencyDAO = delimitationConstituencyDAO;
	}
	public IDelimitationConstituencyMandalDAO getDelimitationConstituencyMandalDAO() {
		return delimitationConstituencyMandalDAO;
	}
	public void setDelimitationConstituencyMandalDAO(
			IDelimitationConstituencyMandalDAO delimitationConstituencyMandalDAO) {
		this.delimitationConstituencyMandalDAO = delimitationConstituencyMandalDAO;
	}
	public IBoothConstituencyElectionDAO getBoothConstituencyElectionDAO() {
		return boothConstituencyElectionDAO;
	}
	public void setBoothConstituencyElectionDAO(
			IBoothConstituencyElectionDAO boothConstituencyElectionDAO) {
		this.boothConstituencyElectionDAO = boothConstituencyElectionDAO;
	}
	public IAssemblyLocalElectionBodyDAO getAssemblyLocalElectionBodyDAO() {
		return assemblyLocalElectionBodyDAO;
	}
	public void setAssemblyLocalElectionBodyDAO(
			IAssemblyLocalElectionBodyDAO assemblyLocalElectionBodyDAO) {
		this.assemblyLocalElectionBodyDAO = assemblyLocalElectionBodyDAO;
	}
	public IAssemblyLocalElectionBodyWardDAO getAssemblyLocalElectionBodyWardDAO() {
		return assemblyLocalElectionBodyWardDAO;
	}
	public void setAssemblyLocalElectionBodyWardDAO(
			IAssemblyLocalElectionBodyWardDAO assemblyLocalElectionBodyWardDAO) {
		this.assemblyLocalElectionBodyWardDAO = assemblyLocalElectionBodyWardDAO;
	}
	public ILocalElectionBodyDAO getLocalElectionBodyDAO() {
		return localElectionBodyDAO;
	}
	public void setLocalElectionBodyDAO(ILocalElectionBodyDAO localElectionBodyDAO) {
		this.localElectionBodyDAO = localElectionBodyDAO;
	}
	public IDelimitationConstituencyAssemblyDetailsDAO getDelimitationConstituencyAssemblyDetailsDAO() {
		return delimitationConstituencyAssemblyDetailsDAO;
	}
	public void setDelimitationConstituencyAssemblyDetailsDAO(
			IDelimitationConstituencyAssemblyDetailsDAO delimitationConstituencyAssemblyDetailsDAO) {
		this.delimitationConstituencyAssemblyDetailsDAO = delimitationConstituencyAssemblyDetailsDAO;
	}
	public ITehsilDAO getTehsilDAO() {
		return tehsilDAO;
	}
	public void setTehsilDAO(ITehsilDAO tehsilDAO) {
		this.tehsilDAO = tehsilDAO;
	}
	public IPanchayatDAO getPanchayatDAO() {
		return panchayatDAO;
	}
	public void setPanchayatDAO(IPanchayatDAO panchayatDAO) {
		this.panchayatDAO = panchayatDAO;
	}
	
	public IRegionServiceData getRegionServiceDataImp() {
		return regionServiceDataImp;
	}
	public void setRegionServiceDataImp(IRegionServiceData regionServiceDataImp) {
		this.regionServiceDataImp = regionServiceDataImp;
	}
	public IActivityTypeDAO getActivityTypeDAO() {
		return activityTypeDAO;
	}
	public void setActivityTypeDAO(IActivityTypeDAO activityTypeDAO) {
		this.activityTypeDAO = activityTypeDAO;
	}
	public IActivitySubTypeDAO getActivitySubTypeDAO() {
		return activitySubTypeDAO;
	}
	public void setActivitySubTypeDAO(IActivitySubTypeDAO activitySubTypeDAO) {
		this.activitySubTypeDAO = activitySubTypeDAO;
	}
	public IActivityDAO getActivityDAO() {
		return activityDAO;
	}
	public void setActivityDAO(IActivityDAO activityDAO) {
		this.activityDAO = activityDAO;
	}
	public IActivityLevelDAO getActivityLevelDAO() {
		return activityLevelDAO;
	}
	public void setActivityLevelDAO(IActivityLevelDAO activityLevelDAO) {
		this.activityLevelDAO = activityLevelDAO;
	}
	public IActivityScopeDAO getActivityScopeDAO() {
		return activityScopeDAO;
	}
	public void setActivityScopeDAO(IActivityScopeDAO activityScopeDAO) {
		this.activityScopeDAO = activityScopeDAO;
	}
	public IActivityLocationInfoDAO getActivityLocationInfoDAO() {
		return activityLocationInfoDAO;
	}
	public void setActivityLocationInfoDAO(
			IActivityLocationInfoDAO activityLocationInfoDAO) {
		this.activityLocationInfoDAO = activityLocationInfoDAO;
	}
	public ILocationInfoDAO getLocationInfoDAO() {
		return locationInfoDAO;
	}
	public void setLocationInfoDAO(ILocationInfoDAO locationInfoDAO) {
		this.locationInfoDAO = locationInfoDAO;
	}
	public ICadreCommitteeService getCadreCommitteeService() {
		return cadreCommitteeService;
	}
	public void setCadreCommitteeService(
			ICadreCommitteeService cadreCommitteeService) {
		this.cadreCommitteeService = cadreCommitteeService;
	}
	
	public TransactionTemplate getTransactionTemplate() {
		return transactionTemplate;
	}
	public void setTransactionTemplate(TransactionTemplate transactionTemplate) {
		this.transactionTemplate = transactionTemplate;
	}
	public LocationWiseBoothDetailsVO getActivityLocationDetails(String isChecked,Long activityScopeId,Long activityLevelId,String searchBy,Long locationId,
			 String searchStartDateStr,String searchEndDateStr)
	{
		LocationWiseBoothDetailsVO returnVO = null;	
		try {
			List<LocationWiseBoothDetailsVO> returnList = null;
			List<Long> updatedLocationIdsList  = new ArrayList<Long>(0);
			List<LocationWiseBoothDetailsVO> reportList = null;
			List<Long> constituencyIds = new ArrayList<Long>(0);
			Map<Long,List<ActivityVO>> activityMap = new LinkedHashMap<Long, List<ActivityVO>>(0);
			//List<LocationWiseBoothDetailsVO> mandalList = new ArrayList<LocationWiseBoothDetailsVO>(0);
			//List<LocationWiseBoothDetailsVO> panchayatList=new ArrayList<LocationWiseBoothDetailsVO>(0);
			
			SimpleDateFormat format = new SimpleDateFormat("MM/dd/yyyy");
			SimpleDateFormat format1 = new SimpleDateFormat("yyyy-MM-dd");
			
			Date startDate = format.parse(searchStartDateStr);
			Date endDate = format.parse(searchEndDateStr);
			
			if(activityScopeId != null && activityScopeId.longValue()>0L)
			{
				 List<Object[]> updatedList= activityLocationInfoDAO.getUpdatedLocationsListForScope(activityScopeId,startDate,endDate);
				 if(updatedList != null && updatedList.size()>0)
				 {
					 for (Object[] locations : updatedList) {
						 Long locationValue = locations[0] != null ? Long.valueOf(locations[0].toString()):0L;
						 String planDate = locations[1] != null ? locations[1].toString():null;
						 String conductedDate = locations[2] != null ? locations[2].toString():null;
						 Long locationlevel = locations[3] != null ? Long.valueOf(locations[3].toString()):0L;
						 
						 Date planDateStr = planDate != null ? format1.parse(planDate):null;
						 Date conductedDateStr = conductedDate != null ? format1.parse(conductedDate):null;

						 Long locationLevelId = 0L;
						 if(locationlevel.longValue() == 6L || locationlevel.longValue() == 7L)
						 {
							 locationLevelId = 1L;
						 }
						 else if(locationlevel.longValue() == 8L || locationlevel.longValue() == 5L)
						 {
							 locationLevelId = 2L;
						 }
						 else if(locationlevel.longValue() == 9L)
						 {
							 locationLevelId = 3L;
						 }
						 String finalIdStr = locationLevelId+""+locationValue;
						 Long finalLocationId = Long.valueOf(finalIdStr);
						 List<ActivityVO> list = new ArrayList<ActivityVO>(0);
						 if(activityMap.get(finalLocationId) != null)
						 {
							 list = activityMap.get(finalLocationId);
						 }
						 ActivityVO vo = new ActivityVO();
						 if(planDateStr != null)
							 vo.setPlannedDate(format.format(planDateStr).toString());
						 if(conductedDateStr != null)
							 vo.setConductedDate(format.format(conductedDateStr).toString());
						 vo.setLocationValue(finalLocationId);
						 vo.setLocationLevel(locationlevel);
						 list.add(vo);
						 activityMap.put(finalLocationId, list);
						 updatedLocationIdsList.add(locationValue);
					}
				 }
				 
			}
			if(activityLevelId != null && activityLevelId.longValue()>0L)
			{
				if(activityLevelId.longValue() == 2L)
				{
					if(searchBy != null && searchBy.trim().equalsIgnoreCase(IConstants.DISTRICT))
					{
						constituencyIds = constituencyDAO.getConstituencyIdsByDistrictId(locationId,IConstants.ASSEMBLY_ELECTION_TYPE_ID);
						reportList = cadreCommitteeService.getMandalMunicCorpDetailsByConstituencyList(constituencyIds);
					}
					else if(searchBy != null && searchBy.trim().equalsIgnoreCase(IConstants.CONSTITUENCY))
					{
						constituencyIds.add(locationId);
						reportList = cadreCommitteeService.getMandalMunicCorpDetailsByConstituencyList(constituencyIds);
					}
					else if(searchBy != null && searchBy.trim().equalsIgnoreCase(IConstants.MANDAL))
					{
						String locationTypeflagId = locationId.toString().substring(0, 1);
						String locatonId = locationId.toString().substring(1);
						reportList = new ArrayList<LocationWiseBoothDetailsVO>(0);
						if(locationTypeflagId.equalsIgnoreCase("2")){
							Tehsil tehsil= tehsilDAO.get(Long.valueOf(locatonId));
							if(tehsil != null)
								reportList.add(new LocationWiseBoothDetailsVO(locationId,tehsil.getTehsilName()));
						}
						else if(locationTypeflagId.equalsIgnoreCase("1")){
							LocalElectionBody localbody= localElectionBodyDAO.get(Long.valueOf(locatonId));
							if(localbody != null)
								reportList.add(new LocationWiseBoothDetailsVO(locationId,localbody.getName()));
						}
					}
					//if(reportList != null && reportList.size()>0)
					//	mandalList.addAll(reportList);
				}
				else if(activityLevelId.longValue() == 1L)
				{
					if(searchBy != null && searchBy.trim().equalsIgnoreCase(IConstants.CONSTITUENCY))
					{
						reportList = cadreCommitteeService.getPanchayatWardDivisionDetailsNew(locationId);
					}
					else if(searchBy != null && searchBy.trim().equalsIgnoreCase(IConstants.MANDAL))
					{
						reportList = cadreCommitteeService.getPanchayatWardByMandalId(locationId.toString());
					}
					else if(searchBy != null && searchBy.trim().equalsIgnoreCase(IConstants.PANCHAYAT))
					{
						String locationTypeflagId = locationId.toString().substring(0, 1);
						String locatonId = locationId.toString().substring(1);
						reportList = new ArrayList<LocationWiseBoothDetailsVO>(0);
						if(locationTypeflagId.equalsIgnoreCase("2")){
							Constituency constituency= constituencyDAO.get(Long.valueOf(locatonId));
							if(constituency != null)
								reportList.add(new LocationWiseBoothDetailsVO(locationId,constituency.getName()));
						}
						else if(locationTypeflagId.equalsIgnoreCase("1")){
							Panchayat panchayat= panchayatDAO.get(Long.valueOf(locatonId));
							if(panchayat != null)
								reportList.add(new LocationWiseBoothDetailsVO(locationId,panchayat.getPanchayatName()));
						}
					}
				}
				if(reportList != null && reportList.size()>0)
				{
					returnVO = new LocationWiseBoothDetailsVO();
					returnList = new ArrayList<LocationWiseBoothDetailsVO>(0);
					if(isChecked != null && isChecked.equalsIgnoreCase("notConducted"))
					{
						for (LocationWiseBoothDetailsVO vo : reportList) {
							String locationIdStr = vo.getLocationId().toString().substring(1);
							if(!updatedLocationIdsList.contains(Long.valueOf(locationIdStr))){
								
								LocationWiseBoothDetailsVO finalVO = new LocationWiseBoothDetailsVO();
								finalVO = vo;
								List<ActivityVO> activityVOList = activityMap.get(vo.getLocationId());
								if(activityVOList != null && activityVOList.size()>0)
								{
									for (ActivityVO activityVO : activityVOList) {
										finalVO.setPlanedDate(activityVO.getPlannedDate());
										finalVO.setConductedDate(activityVO.getConductedDate());
										returnList.add(finalVO);
									}
								}else
								{
									returnList.add(finalVO);
								}
							}
						}
					}
					else if(isChecked != null && isChecked.equalsIgnoreCase("conducted")){
						for (LocationWiseBoothDetailsVO vo : reportList) {
							String locationIdStr = vo.getLocationId().toString().substring(1);
							if(updatedLocationIdsList.contains(Long.valueOf(locationIdStr))){
								LocationWiseBoothDetailsVO finalVO = new LocationWiseBoothDetailsVO();
								finalVO = vo;
								List<ActivityVO> activityVOList = activityMap.get(vo.getLocationId());
								if(activityVOList != null && activityVOList.size()>0)
								{
									for (ActivityVO activityVO : activityVOList) {
										finalVO.setPlanedDate(activityVO.getPlannedDate());
										finalVO.setConductedDate(activityVO.getConductedDate());
										returnList.add(finalVO);
									}
								}else
								{
									returnList.add(finalVO);
								}
							}
						}
					}
					else if(isChecked != null && isChecked.equalsIgnoreCase("all")){
						for (LocationWiseBoothDetailsVO vo : reportList) {

							LocationWiseBoothDetailsVO finalVO = new LocationWiseBoothDetailsVO();
							finalVO = vo;
							List<ActivityVO> activityVOList = activityMap.get(vo.getLocationId());
							if(activityVOList != null && activityVOList.size()>0)
							{
								for (ActivityVO activityVO : activityVOList) {
									finalVO.setPlanedDate(activityVO.getPlannedDate());
									finalVO.setConductedDate(activityVO.getConductedDate());
									returnList.add(finalVO);
								}
							}else
							{
								returnList.add(finalVO);
							}
						}
					}
						
					Collections.sort(returnList,new Comparator<LocationWiseBoothDetailsVO>() {
						public int compare(LocationWiseBoothDetailsVO o1,
								LocationWiseBoothDetailsVO o2) {
							return o1.getLocationName().compareTo(o2.getLocationName());
						}
					});
					
					returnVO.getResult().addAll(returnList);
				}
			}
		} catch (Exception e) {
			 LOG.error("Exception Occured in getActivityLocationDetails() method, Exception - ",e);
		}
		
		return returnVO;
	}
	public Long getTotalAreaCountByList(List<BasicVO> areaWiseCountLsit)
	{
		Long totalAreasCount = 0L;
		try {
			if(areaWiseCountLsit != null && areaWiseCountLsit.size()>0)
			{
				totalAreasCount = 0L;
				for (BasicVO basicVO : areaWiseCountLsit) {
					totalAreasCount = totalAreasCount+ basicVO.getTotalVoters().longValue();
				}
			}
		} catch (Exception e) {
			 LOG.error("Exception Occured in getActivityLocationDetails() method, Exception - ",e);
		}
		return totalAreasCount;
	}
	
	public void segrigatePlannedResult(List<Object[]> activitiesList,Map<Long,ActivityVO> locationsMap,String extentionNo)
	{
		try {
			if(activitiesList != null && activitiesList.size()>0)
			{
				for (Object[] activity : activitiesList) {
					ActivityVO aactivityVO = null;
					Long areaId = 0L;
					if(extentionNo != null && !extentionNo.isEmpty()){
						String locationId = extentionNo+""+commonMethodsUtilService.getStringValueForObject(activity[0]);
						areaId = Long.valueOf(locationId.trim());
					}
					else
					{
						areaId = commonMethodsUtilService.getLongValueForObject(activity[0]);
					}
					if(locationsMap.get(areaId) != null){
						aactivityVO = locationsMap.get(areaId);
						if(aactivityVO != null){
							//aactivityVO.setNotPlannedCount(commonMethodsUtilService.getLongValueForObject(activity[3]));
						}
					}
					else{
						aactivityVO = new ActivityVO();					
						aactivityVO.setId(areaId);
						aactivityVO.setName(commonMethodsUtilService.getStringValueForObject(activity[1]));
						aactivityVO.setLocationLevel(commonMethodsUtilService.getLongValueForObject(activity[2]));
						//aactivityVO.setPlannedCount(commonMethodsUtilService.getLongValueForObject(activity[3]));
						
						locationsMap.put(aactivityVO.getId(), aactivityVO);
					}
				}
			}
		} catch (Exception e) {
			 LOG.error("Exception Occured in segrigateFirstResult() method, Exception - ",e);
		}
	}
	
	public void segrigatefinalResultsTypes(List<Object[]> activitiesList,Map<Long,ActivityVO> locationsMap,String type,String extentionNo)
	{
		try {

			if(locationsMap != null && locationsMap.size()>0)
			{
				for (Long locationsId : locationsMap.keySet()) {

						ActivityVO vo = locationsMap.get(locationsId);
						if(vo != null)
						{
							String[] typeArr = IConstants.ACTIVITY_REPORT_FIELDS;
							if(typeArr != null && typeArr.length>0)
							{
								for(int i=0;i<typeArr.length;i++)
								{
									type = typeArr[i];
									if(type != null)
									{

										Long count = 0L;
										if(type.trim().equalsIgnoreCase("PLANNED")){
											if(vo.getPlannedCount() != null)
												vo.setPlannedCount(count+vo.getPlannedCount());
											else
												vo.setPlannedCount(count);
										}
										else if(type.trim().equalsIgnoreCase("IVR COVERED")){
											if(vo.getIvrcovered() != null)
												vo.setIvrcovered(count+vo.getIvrcovered());
											else
												vo.setIvrcovered(count);
										}
										else if(type.trim().equalsIgnoreCase("IVR COVERED %") && vo.getIvrcovered() != null &&   vo.getIvrcovered().longValue()>0L && 
												 vo.getPlannedCount() != null  &&  vo.getPlannedCount().longValue()>0L ){
											double perc = (vo.getIvrcovered()*100.0)/vo.getPlannedCount();;
											String percentage = commonMethodsUtilService.roundTo2DigitsFloatValueAsString(Float.valueOf(String.valueOf(perc)));
											vo.setIvrcoveredPerc(percentage);
										}
										else if(type.trim().equalsIgnoreCase("IVR NOT PLANNED")){
											if(vo.getIvrNotPlanned() != null)
												vo.setIvrNotPlanned(count+vo.getIvrNotPlanned());
											else
												vo.setIvrNotPlanned(count);
										}
										else if(type.trim().equalsIgnoreCase("IVR TOTAL")){
											if(vo.getIvrcovered() != null && vo.getIvrNotPlanned() != null)
												vo.setIvrTotal(vo.getIvrcovered() + vo.getIvrNotPlanned());
											else if(vo.getIvrcovered() != null)
												vo.setIvrTotal(vo.getIvrcovered());
											else if(vo.getIvrNotPlanned() != null)
												vo.setIvrTotal(vo.getIvrNotPlanned());
											if(vo.getIvrTotal() != null && vo.getTotalCount() != null)
											{
												double perc = (vo.getIvrTotal()*100.0)/vo.getTotalCount();;
												String percentage = commonMethodsUtilService.roundTo2DigitsFloatValueAsString(Float.valueOf(String.valueOf(perc)));
												vo.setIvrTotalPerc(percentage);
											}
										}
										else if(type.trim().equalsIgnoreCase("INFO CELL COVERED")){
											if(vo.getInfoCellcovered() != null)
												vo.setInfoCellcovered(count+vo.getInfoCellcovered());
											else
												vo.setInfoCellcovered(count);
										}
										else if(type.trim().equalsIgnoreCase("INFO CELL COVERED %") && vo.getInfoCellcovered() != null && vo.getInfoCellcovered().longValue()>0L 
												&& vo.getPlannedCount() != null && vo.getPlannedCount().longValue()>0L){
											double perc = (vo.getInfoCellcovered()*100.0)/vo.getPlannedCount();;
											String percentage = commonMethodsUtilService.roundTo2DigitsFloatValueAsString(Float.valueOf(String.valueOf(perc)));
											vo.setInfoCellcoveredPerc(percentage);
										}
										else if(type.trim().equalsIgnoreCase("INFO CELL NOT PLANNED")){
											if(vo.getInfoCellNotPlanned() != null)
												vo.setInfoCellNotPlanned(count+vo.getInfoCellNotPlanned());
											else
												vo.setInfoCellNotPlanned(count);
										}
										else if(type.trim().equalsIgnoreCase("INFO CELL TOTAL") ){
											
											if(vo.getInfoCellNotPlanned() != null && vo.getInfoCellcovered() != null)
												vo.setInfoCellTotal(vo.getInfoCellcovered() + vo.getInfoCellNotPlanned());
											else if(vo.getInfoCellcovered() != null)
												vo.setInfoCellTotal(vo.getInfoCellcovered());
											else if(vo.getInfoCellNotPlanned() != null)
												vo.setInfoCellTotal(vo.getInfoCellNotPlanned());
											
											if(vo.getInfoCellTotal() != null && vo.getTotalCount() != null)
											{
												double perc = (vo.getInfoCellTotal()*100.0)/vo.getTotalCount();;
												String percentage = commonMethodsUtilService.roundTo2DigitsFloatValueAsString(Float.valueOf(String.valueOf(perc)));
												vo.setInfoCellTotalPerc(percentage);
											}
										}
										else if(type.trim().equalsIgnoreCase("WHATSAPP IMAGES COVERED")){
											if(vo.getWhatsAppCovered() != null)
												vo.setWhatsAppCovered(count+vo.getWhatsAppCovered());
											else
												vo.setWhatsAppCovered(count);
										}
										else if(type.trim().equalsIgnoreCase("WHATSAPP IMAGES COVERED %") && vo.getWhatsAppCovered() != null &&  vo.getWhatsAppCovered().longValue()>0L && 
												vo.getInfoCellTotal() != null  && vo.getInfoCellTotal().longValue()>0L){
											double perc = (vo.getWhatsAppCovered()*100.0)/vo.getInfoCellTotal();
											String percentage = commonMethodsUtilService.roundTo2DigitsFloatValueAsString(Float.valueOf(String.valueOf(perc)));
											vo.setWhatsAppCoveredPerc(percentage);
										}
										else if(type.trim().equalsIgnoreCase("NO OF WHATSAPP IMAGES RECIEVED")){
											if(vo.getImagesCount() != null)
												vo.setImagesCount(count+vo.getImagesCount());
											else
												vo.setImagesCount(count);
										}
									}
								}
							}
						}
				}
			}
		} catch (Exception e) {
			 LOG.error("Exception Occured in segrigateFirstResult() method, Exception - ",e);
		}
	
		
	}
	@SuppressWarnings("null")
	public void segrigateResultsTypes(List<Object[]> activitiesList,Map<Long,ActivityVO> locationsMap,String type,String extentionNo)
	{
		try {
			if(locationsMap == null || locationsMap.size() == 0)
			{
				segrigatePlannedResult(activitiesList, locationsMap, extentionNo);
			}
			
			if(activitiesList != null && activitiesList.size()>0)
			{
				for (Object[] activity : activitiesList) {
					String locationId = commonMethodsUtilService.getStringValueForObject(activity[0]);
					if(extentionNo != null && extentionNo.trim().length()>0)
						locationId = extentionNo+""+commonMethodsUtilService.getStringValueForObject(activity[0]);
					
					Long areaId = Long.valueOf(locationId.trim());
				
					ActivityVO vo = locationsMap.get(areaId);
					if(vo == null)
					{
						segrigatePlannedResult(activitiesList, locationsMap, extentionNo);
						vo = locationsMap.get(areaId);
					}
					if(vo != null)
					{
						if(type != null)
						{
							Long count = commonMethodsUtilService.getLongValueForObject(activity[3]);
							if(type.trim().equalsIgnoreCase("PLANNED")){
								if(vo.getPlannedCount() != null)
									vo.setPlannedCount(count+vo.getPlannedCount());
								else
									vo.setPlannedCount(count);
							}
							else if(type.trim().equalsIgnoreCase("IVR COVERED")){
								if(vo.getIvrcovered() != null)
									vo.setIvrcovered(count+vo.getIvrcovered());
								else
									vo.setIvrcovered(count);
							}
							else if(type.trim().equalsIgnoreCase("IVR COVERED %") && vo.getIvrcovered() != null &&  vo.getIvrcovered().longValue()>0L 
									&& vo.getPlannedCount() != null &&  vo.getPlannedCount().longValue()>0L){
								double perc = (vo.getIvrcovered()*100.0)/vo.getPlannedCount();;
								String percentage = commonMethodsUtilService.roundTo2DigitsFloatValueAsString(Float.valueOf(String.valueOf(perc)));
								vo.setIvrcoveredPerc(percentage);
							}
							else if(type.trim().equalsIgnoreCase("IVR NOT PLANNED")){
								if(vo.getIvrNotPlanned() != null)
									vo.setIvrNotPlanned(count+vo.getIvrNotPlanned());
								else
									vo.setIvrNotPlanned(count);
							}
							else if(type.trim().equalsIgnoreCase("IVR TOTAL")){
								if(vo.getIvrcovered() != null && vo.getIvrNotPlanned() != null)
									vo.setIvrTotal(vo.getIvrcovered() + vo.getIvrNotPlanned());
								else if(vo.getIvrcovered() != null)
									vo.setIvrTotal(vo.getIvrcovered());
								else if(vo.getIvrNotPlanned() != null)
									vo.setIvrTotal(vo.getIvrNotPlanned());
								if(vo.getIvrTotal() != null && vo.getTotalCount() != null)
								{
									double perc = (vo.getIvrTotal()*100.0)/vo.getTotalCount();;
									String percentage = commonMethodsUtilService.roundTo2DigitsFloatValueAsString(Float.valueOf(String.valueOf(perc)));
									vo.setIvrTotalPerc(percentage);
								}
							}
							else if(type.trim().equalsIgnoreCase("INFO CELL COVERED")){
								if(vo.getInfoCellcovered() != null)
									vo.setInfoCellcovered(count+vo.getInfoCellcovered());
								else
									vo.setInfoCellcovered(count);
							}
							else if(type.trim().equalsIgnoreCase("INFO CELL COVERED %") && vo.getInfoCellcovered() != null && vo.getInfoCellcovered().longValue()>0L && 
									 vo.getPlannedCount() != null && vo.getPlannedCount().longValue()>0L){
								double perc = (vo.getInfoCellcovered()*100.0)/vo.getPlannedCount();;
								String percentage = commonMethodsUtilService.roundTo2DigitsFloatValueAsString(Float.valueOf(String.valueOf(perc)));
								vo.setInfoCellcoveredPerc(percentage);
							}
							else if(type.trim().equalsIgnoreCase("INFO CELL NOT PLANNED")){
								if(vo.getInfoCellNotPlanned() != null)
									vo.setInfoCellNotPlanned(count+vo.getInfoCellNotPlanned());
								else
									vo.setInfoCellNotPlanned(count);
							}
							else if(type.trim().equalsIgnoreCase("INFO CELL TOTAL") ){
								
								if(vo.getInfoCellNotPlanned() != null && vo.getInfoCellcovered() != null)
									vo.setInfoCellTotal(vo.getInfoCellcovered() + vo.getInfoCellNotPlanned());
								else if(vo.getInfoCellcovered() != null)
									vo.setInfoCellTotal(vo.getInfoCellcovered());
								else if(vo.getInfoCellNotPlanned() != null)
									vo.setInfoCellTotal(vo.getInfoCellNotPlanned());
								
								if(vo.getInfoCellTotal() != null && vo.getTotalCount() != null)
								{
									double perc = (vo.getInfoCellTotal()*100.0)/vo.getTotalCount();;
									String percentage = commonMethodsUtilService.roundTo2DigitsFloatValueAsString(Float.valueOf(String.valueOf(perc)));
									vo.setInfoCellTotalPerc(percentage);
								}
							}
							else if(type.trim().equalsIgnoreCase("WHATSAPP IMAGES COVERED")){
								if(vo.getWhatsAppCovered() != null)
									vo.setWhatsAppCovered(count+vo.getWhatsAppCovered());
								else
									vo.setWhatsAppCovered(count);
							}
							else if(type.trim().equalsIgnoreCase("WHATSAPP IMAGES COVERED %") && vo.getWhatsAppCovered() != null &&  vo.getWhatsAppCovered().longValue()>0L && 
									 vo.getInfoCellTotal() != null &&  vo.getInfoCellTotal().longValue()>0L){
								double perc = (vo.getWhatsAppCovered()*100.0)/vo.getInfoCellTotal();
								String percentage = commonMethodsUtilService.roundTo2DigitsFloatValueAsString(Float.valueOf(String.valueOf(perc)));
								vo.setWhatsAppCoveredPerc(percentage);
							}
							else if(type.trim().equalsIgnoreCase("NO OF WHATSAPP IMAGES RECIEVED")){
								if(vo.getImagesCount() != null)
									vo.setImagesCount(count+vo.getImagesCount());
								else
									vo.setImagesCount(count);
							}
						}
					}
				}
				
			}
			else{
				if(locationsMap != null && locationsMap.size()>0)
				{
					for (Long locationsId : locationsMap.keySet()) {

							ActivityVO vo = locationsMap.get(locationsId);
							if(vo != null)
							{
								if(type != null)
								{
									Long count = 0L;
									if(type.trim().equalsIgnoreCase("PLANNED")){
										if(vo.getPlannedCount() != null)
											vo.setPlannedCount(count+vo.getPlannedCount());
										else
											vo.setPlannedCount(count);
									}
									else if(type.trim().equalsIgnoreCase("IVR COVERED")){
										if(vo.getIvrcovered() != null)
											vo.setIvrcovered(count+vo.getIvrcovered());
										else
											vo.setIvrcovered(count);
									}
									else if(type.trim().equalsIgnoreCase("IVR COVERED %") && vo.getIvrcovered() != null &&   vo.getIvrcovered().longValue()>0L && 
											 vo.getPlannedCount() != null &&   vo.getPlannedCount().longValue()>0L){
										double perc = (vo.getIvrcovered()*100.0)/vo.getPlannedCount();;
										String percentage = commonMethodsUtilService.roundTo2DigitsFloatValueAsString(Float.valueOf(String.valueOf(perc)));
										vo.setIvrcoveredPerc(percentage);
									}
									else if(type.trim().equalsIgnoreCase("IVR NOT PLANNED")){
										if(vo.getIvrNotPlanned() != null)
											vo.setIvrNotPlanned(count+vo.getIvrNotPlanned());
										else
											vo.setIvrNotPlanned(count);
									}
									else if(type.trim().equalsIgnoreCase("IVR TOTAL")){
										if(vo.getIvrcovered() != null && vo.getIvrNotPlanned() != null)
											vo.setIvrTotal(vo.getIvrcovered() + vo.getIvrNotPlanned());
										else if(vo.getIvrcovered() != null)
											vo.setIvrTotal(vo.getIvrcovered());
										else if(vo.getIvrNotPlanned() != null)
											vo.setIvrTotal(vo.getIvrNotPlanned());
										if(vo.getIvrTotal() != null && vo.getTotalCount() != null)
										{
											double perc = (vo.getIvrTotal()*100.0)/vo.getTotalCount();;
											String percentage = commonMethodsUtilService.roundTo2DigitsFloatValueAsString(Float.valueOf(String.valueOf(perc)));
											vo.setIvrTotalPerc(percentage);
										}
									}
									else if(type.trim().equalsIgnoreCase("INFO CELL COVERED")){
										if(vo.getInfoCellcovered() != null)
											vo.setInfoCellcovered(count+vo.getInfoCellcovered());
										else
											vo.setInfoCellcovered(count);
									}
									else if(type.trim().equalsIgnoreCase("INFO CELL COVERED %") && vo.getInfoCellcovered() != null &&  vo.getInfoCellcovered().longValue()>0L  && 
											 vo.getPlannedCount() != null  && vo.getPlannedCount().longValue()>0L ){
										double perc = (vo.getInfoCellcovered()*100.0)/vo.getPlannedCount();;
										String percentage = commonMethodsUtilService.roundTo2DigitsFloatValueAsString(Float.valueOf(String.valueOf(perc)));
										vo.setInfoCellcoveredPerc(percentage);
									}
									else if(type.trim().equalsIgnoreCase("INFO CELL NOT PLANNED")){
										if(vo.getInfoCellNotPlanned() != null)
											vo.setInfoCellNotPlanned(count+vo.getInfoCellNotPlanned());
										else
											vo.setInfoCellNotPlanned(count);
									}
									else if(type.trim().equalsIgnoreCase("INFO CELL TOTAL") ){
										
										if(vo.getInfoCellNotPlanned() != null && vo.getInfoCellcovered() != null)
											vo.setInfoCellTotal(vo.getInfoCellcovered() + vo.getInfoCellNotPlanned());
										else if(vo.getInfoCellcovered() != null)
											vo.setInfoCellTotal(vo.getInfoCellcovered());
										else if(vo.getInfoCellNotPlanned() != null)
											vo.setInfoCellTotal(vo.getInfoCellNotPlanned());
										
										if(vo.getInfoCellTotal() != null && vo.getTotalCount() != null)
										{
											double perc = (vo.getInfoCellTotal()*100.0)/vo.getTotalCount();;
											String percentage = commonMethodsUtilService.roundTo2DigitsFloatValueAsString(Float.valueOf(String.valueOf(perc)));
											vo.setInfoCellTotalPerc(percentage);
										}
									}
									else if(type.trim().equalsIgnoreCase("WHATSAPP IMAGES COVERED")){
										if(vo.getWhatsAppCovered() != null)
											vo.setWhatsAppCovered(count+vo.getWhatsAppCovered());
										else
											vo.setWhatsAppCovered(count);
									}
									else if(type.trim().equalsIgnoreCase("WHATSAPP IMAGES COVERED %") && vo.getWhatsAppCovered() != null &&  vo.getWhatsAppCovered().longValue()>0L  && 
											 vo.getInfoCellTotal() != null &&  vo.getInfoCellTotal().longValue()>0L ){
										double perc = (vo.getWhatsAppCovered()*100.0)/vo.getInfoCellTotal();
										String percentage = commonMethodsUtilService.roundTo2DigitsFloatValueAsString(Float.valueOf(String.valueOf(perc)));
										vo.setWhatsAppCoveredPerc(percentage);
									}
									else if(type.trim().equalsIgnoreCase("NO OF WHATSAPP IMAGES RECIEVED")){
										if(vo.getImagesCount() != null)
											vo.setImagesCount(count+vo.getImagesCount());
										else
											vo.setImagesCount(count);
									}
								}
							}
					}
				}
			}
			

		} catch (Exception e) {
			 LOG.error("Exception Occured in segrigateFirstResult() method, Exception - ",e);
		}
	}
	
	public ActivityVO getMatchActivityVO(List<ActivityVO> list, String type)
	{
		ActivityVO returnVO = null;
		try {
			if(list != null && list.size()>0)
			{
				for (ActivityVO activityVO : list) {
					if(activityVO.getName().trim().equalsIgnoreCase(type.trim()))
						return activityVO;
				}
			}
		} catch (Exception e) {
			 LOG.error("Exception Occured in getMatchActivityVO() method, Exception - ",e);
		}
		return returnVO;
	}
	public BasicVO getActivityTypeList()
	{
		BasicVO basicVO = null;
		try {
			List<ActivitySubType> activityTypeLsit = activitySubTypeDAO.getAll();
			if(activityTypeLsit != null && activityTypeLsit.size()>0)
			{
				List<BasicVO> activitiesLsit = new ArrayList<BasicVO>();
				for (ActivitySubType activityType : activityTypeLsit) {
					if(activityType.getIsDeleted().equalsIgnoreCase("N")){
						BasicVO vo = new BasicVO();
						vo.setId(activityType.getActivitySubTypeId());
						vo.setName(activityType.getSubType());
						activitiesLsit.add(vo);
					}
				}
				
				if(activitiesLsit != null && activitiesLsit.size()>0)
				{
					basicVO = new BasicVO();
					basicVO.setPanchayatVoterInfo(activitiesLsit);
				}
			}
		} catch (Exception e) {
			LOG.error("Exception Occured in getActivityTypeList() method, Exception - ",e);
		}
		return basicVO;
	}
	
	public List<IdNameVO> getActivityLevelsList()
	{
		List<IdNameVO> idNameVoList = null;
		try {
			List<ActivityLevel> activityTypeLsit = activityLevelDAO.getAll();
			if(activityTypeLsit != null && activityTypeLsit.size()>0)
			{
				idNameVoList = new ArrayList<IdNameVO>();
				for (ActivityLevel activityType : activityTypeLsit) {
					IdNameVO vo = new IdNameVO();
					vo.setId(activityType.getActivityLevelId());
					vo.setName(activityType.getLevel());
					idNameVoList.add(vo);
				}
				
			}
		} catch (Exception e) {
			LOG.error("Exception Occured in getActivityTypeList() method, Exception - ",e);
		}
		return idNameVoList;
	}
	
	
	public ActivityVO getActivityDetailsBySearchCriteria(SearchAttributeVO searchAttributeVO)
	{
		ActivityVO returnVO = new ActivityVO();
		try {
			Long activityLevelId =null;
			if(searchAttributeVO.getAttributesIdsList() != null && searchAttributeVO.getAttributesIdsList().size()>0)
			{
				
					ActivityScope activityScope = activityScopeDAO.get(searchAttributeVO.getAttributesIdsList().get(0));
					searchAttributeVO.setScopeId(activityScope.getScopeId());
					searchAttributeVO.setScopeValue(activityScope.getScopeValue());
					activityLevelId = activityScope.getActivityLevel().getActivityLevelId();
					
					if(activityLevelId != null && activityLevelId.longValue()>0L)
					{
						if(activityLevelId.longValue() == 1L)
						{
							searchAttributeVO.getLocationTypeIdsList().add(6L);
							searchAttributeVO.getLocationTypeIdsList().add(8L);
						}
						else if(activityLevelId.longValue() == 2L)
						{
							searchAttributeVO.getLocationTypeIdsList().add(5L);
							searchAttributeVO.getLocationTypeIdsList().add(7L);
							searchAttributeVO.getLocationTypeIdsList().add(9L);
						}
						else if(activityLevelId.longValue() == 3L)
						{
							searchAttributeVO.getLocationTypeIdsList().add(10L);
						}
						else if(activityLevelId.longValue() == 4L)
						{
							searchAttributeVO.getLocationTypeIdsList().add(11L);
						}
					}
					
				if(searchAttributeVO.getSearchType().equalsIgnoreCase(IConstants.STATE)){
					List<BasicVO> areaWiseCountLsit = regionServiceDataImp.areaCountListByAreaIdsOnScope(searchAttributeVO);
					if(areaWiseCountLsit != null && areaWiseCountLsit.size()>0)
					{
						Long totalAreasCount = getTotalAreaCountByList(areaWiseCountLsit);
						returnVO.setTotalCount(totalAreasCount);
					}
				}
				
				
			if(searchAttributeVO.getSearchType() != null && searchAttributeVO.getConditionType().trim().contains("daywiseResult")){
				List<ActivityVO> dayWiseResultList = getActivityDayWiseCountsByLocation(searchAttributeVO);
				returnVO.getActivityVoList().addAll(dayWiseResultList);
				return returnVO;
			}
				
				List<Object[]> plannedActivities = null;
				List<Object[]> infoCellconductedActivities = null;
				List<Object[]> ivrconductedActivities = null;
				List<Object[]> infoCellNotPlannedActivities = null;
				List<Object[]> ivrNotPlannedActivities = null;
				
				List<Long> questionnaireIds = activityQuestionnaireDAO.getQuestionnaireIdsListByScopeId(searchAttributeVO.getAttributesIdsList().get(0));
				searchAttributeVO.setQuestionnaireIdsList(questionnaireIds);
				List<Object[]> questionnairesCount = null;
				List<Object[]> yesCount = null;
				Map<Long,ActivityVO> locationsMap = new LinkedHashMap<Long, ActivityVO>(0);
				if(searchAttributeVO.getSearchType().equalsIgnoreCase(IConstants.STATE)){
					searchAttributeVO.getLocationIdsList().add(searchAttributeVO.getLocationValue());
					
					questionnairesCount = activityQuestionAnswerDAO.getActivityQuestionnairesCountsByLocation(searchAttributeVO);
					yesCount = activityQuestionAnswerDAO.getActivityQuestionnairesAttributeCountsByLocation(searchAttributeVO,1L);
					searchAttributeVO.setConditionType("planned");
						plannedActivities = activityLocationInfoDAO.getActivityPlannedInfoCellAndIVRCountsByLocation(searchAttributeVO);
					searchAttributeVO.setConditionType(" infocell ");
						infoCellconductedActivities = activityLocationInfoDAO.getActivityPlannedInfoCellAndIVRCountsByLocation(searchAttributeVO); 
					searchAttributeVO.setConditionType("ivr");
						ivrconductedActivities = activityLocationInfoDAO.getActivityPlannedInfoCellAndIVRCountsByLocation(searchAttributeVO); 

					searchAttributeVO.setConditionType("infocell");
						infoCellNotPlannedActivities = activityLocationInfoDAO.getActivityNotPlannedInfoCellAndIVRCountsByLocation(searchAttributeVO); 	
					searchAttributeVO.setConditionType("ivr");
						ivrNotPlannedActivities = activityLocationInfoDAO.getActivityNotPlannedInfoCellAndIVRCountsByLocation(searchAttributeVO); 
					
					segrigateResultsTypes(plannedActivities,locationsMap,"PLANNED",null);
					
					segrigateResultsTypes(ivrconductedActivities,locationsMap,"IVR COVERED",null);
					segrigateResultsTypes(ivrconductedActivities,locationsMap,"IVR COVERED %",null);
					segrigateResultsTypes(ivrNotPlannedActivities,locationsMap,"IVR NOT PLANNED",null);
					segrigateResultsTypes(ivrNotPlannedActivities,locationsMap,"IVR TOTAL",null);
					
					segrigateResultsTypes(infoCellconductedActivities,locationsMap,"INFO CELL COVERED",null);
					segrigateResultsTypes(infoCellconductedActivities,locationsMap,"INFO CELL COVERED %",null);
					segrigateResultsTypes(infoCellNotPlannedActivities,locationsMap,"INFO CELL NOT PLANNED",null);
					segrigateResultsTypes(ivrNotPlannedActivities,locationsMap,"INFO CELL TOTAL",null);
					
					segrigateResultsTypes(yesCount,locationsMap,"WHATSAPP IMAGES COVERED",null);					
					segrigateResultsTypes(yesCount,locationsMap,"WHATSAPP IMAGES COVERED %",null);	
					segrigateResultsTypes(questionnairesCount,locationsMap,"NO OF WHATSAPP IMAGES RECIEVED",null);	
					
				}
				else if(searchAttributeVO.getSearchType().equalsIgnoreCase(IConstants.DISTRICT)){
					
					List<Long> districtIds = districtDAO.getDistrictsInAState(searchAttributeVO.getTypeId());//AP/TS/ALL
					if(districtIds != null && districtIds.size()>0){
						searchAttributeVO.setLocationIdsList(districtIds);
						
						questionnairesCount = activityQuestionAnswerDAO.getActivityQuestionnairesCountsByLocation(searchAttributeVO);
						yesCount = activityQuestionAnswerDAO.getActivityQuestionnairesAttributeCountsByLocation(searchAttributeVO,1L);
					searchAttributeVO.setConditionType("planned");
						plannedActivities = activityLocationInfoDAO.getActivityPlannedInfoCellAndIVRCountsByLocation(searchAttributeVO);
					searchAttributeVO.setConditionType(" infocell ");
						infoCellconductedActivities = activityLocationInfoDAO.getActivityPlannedInfoCellAndIVRCountsByLocation(searchAttributeVO); 
					searchAttributeVO.setConditionType("ivr");
						ivrconductedActivities = activityLocationInfoDAO.getActivityPlannedInfoCellAndIVRCountsByLocation(searchAttributeVO); 

					searchAttributeVO.setConditionType("infocell");
						infoCellNotPlannedActivities = activityLocationInfoDAO.getActivityNotPlannedInfoCellAndIVRCountsByLocation(searchAttributeVO); 	
					searchAttributeVO.setConditionType("ivr");
						ivrNotPlannedActivities = activityLocationInfoDAO.getActivityNotPlannedInfoCellAndIVRCountsByLocation(searchAttributeVO); 
					
					segrigateResultsTypes(plannedActivities,locationsMap,"PLANNED",null);
					
					segrigateResultsTypes(ivrconductedActivities,locationsMap,"IVR COVERED",null);
					segrigateResultsTypes(ivrconductedActivities,locationsMap,"IVR COVERED %",null);
					segrigateResultsTypes(ivrNotPlannedActivities,locationsMap,"IVR NOT PLANNED",null);
					segrigateResultsTypes(ivrNotPlannedActivities,locationsMap,"IVR TOTAL",null);
					
					segrigateResultsTypes(infoCellconductedActivities,locationsMap,"INFO CELL COVERED",null);
					segrigateResultsTypes(infoCellconductedActivities,locationsMap,"INFO CELL COVERED %",null);
					segrigateResultsTypes(infoCellNotPlannedActivities,locationsMap,"INFO CELL NOT PLANNED",null);
					segrigateResultsTypes(ivrNotPlannedActivities,locationsMap,"INFO CELL TOTAL",null);
					
					segrigateResultsTypes(yesCount,locationsMap,"WHATSAPP IMAGES COVERED",null);					
					segrigateResultsTypes(yesCount,locationsMap,"WHATSAPP IMAGES COVERED %",null);	
					segrigateResultsTypes(questionnairesCount,locationsMap,"NO OF WHATSAPP IMAGES RECIEVED",null);		
					}
				}
				else if(searchAttributeVO.getSearchType().equalsIgnoreCase(IConstants.CONSTITUENCY)){
					List<Long> constituencyList = constituencyDAO.getConstituenciesInADistrict(searchAttributeVO.getLocationId());
					if(constituencyList != null && constituencyList.size()>0)
					{
						searchAttributeVO.setLocationIdsList(constituencyList);
						
						questionnairesCount = activityQuestionAnswerDAO.getActivityQuestionnairesCountsByLocation(searchAttributeVO);
						yesCount = activityQuestionAnswerDAO.getActivityQuestionnairesAttributeCountsByLocation(searchAttributeVO,1L);
						searchAttributeVO.setConditionType("planned");
							plannedActivities = activityLocationInfoDAO.getActivityPlannedInfoCellAndIVRCountsByLocation(searchAttributeVO);
						searchAttributeVO.setConditionType(" infocell ");
							infoCellconductedActivities = activityLocationInfoDAO.getActivityPlannedInfoCellAndIVRCountsByLocation(searchAttributeVO); 
						searchAttributeVO.setConditionType("ivr");
							ivrconductedActivities = activityLocationInfoDAO.getActivityPlannedInfoCellAndIVRCountsByLocation(searchAttributeVO); 
	
						searchAttributeVO.setConditionType("infocell");
							infoCellNotPlannedActivities = activityLocationInfoDAO.getActivityNotPlannedInfoCellAndIVRCountsByLocation(searchAttributeVO); 	
						searchAttributeVO.setConditionType("ivr");
							ivrNotPlannedActivities = activityLocationInfoDAO.getActivityNotPlannedInfoCellAndIVRCountsByLocation(searchAttributeVO); 
						
						segrigateResultsTypes(plannedActivities,locationsMap,"PLANNED",null);
						
						segrigateResultsTypes(ivrconductedActivities,locationsMap,"IVR COVERED",null);
						segrigateResultsTypes(ivrconductedActivities,locationsMap,"IVR COVERED %",null);
						segrigateResultsTypes(ivrNotPlannedActivities,locationsMap,"IVR NOT PLANNED",null);
						segrigateResultsTypes(ivrNotPlannedActivities,locationsMap,"IVR TOTAL",null);
						
						segrigateResultsTypes(infoCellconductedActivities,locationsMap,"INFO CELL COVERED",null);
						segrigateResultsTypes(infoCellconductedActivities,locationsMap,"INFO CELL COVERED %",null);
						segrigateResultsTypes(infoCellNotPlannedActivities,locationsMap,"INFO CELL NOT PLANNED",null);
						segrigateResultsTypes(ivrNotPlannedActivities,locationsMap,"INFO CELL TOTAL",null);
						
						segrigateResultsTypes(yesCount,locationsMap,"WHATSAPP IMAGES COVERED",null);					
						segrigateResultsTypes(yesCount,locationsMap,"WHATSAPP IMAGES COVERED %",null);	
						segrigateResultsTypes(questionnairesCount,locationsMap,"NO OF WHATSAPP IMAGES RECIEVED",null);	
					}
				}
				else if(searchAttributeVO.getSearchType().equalsIgnoreCase(IConstants.MANDAL)){
					Long locationId = searchAttributeVO.getLocationId();
					searchAttributeVO.getLocationIdsList().add(locationId);
					
					 
					List<LocationWiseBoothDetailsVO>  mandalMunciDivisionIdsList = cadreCommitteeService.getMandalMunicCorpDetailsByConstituencyList(searchAttributeVO.getLocationIdsList());
					if(mandalMunciDivisionIdsList != null && mandalMunciDivisionIdsList.size()>0)
					{
						List<Long> mandalsIdsList = new ArrayList<Long>(0);
						List<Long> munciORTownORCorpIdsList = new ArrayList<Long>(0);
						List<Long> divisionIdsList = new ArrayList<Long>(0);
						
						Map<Long,ActivityVO> mandalMap = new LinkedHashMap<Long, ActivityVO>(0);
						Map<Long,ActivityVO> MunciTownMap = new LinkedHashMap<Long, ActivityVO>(0);
						Map<Long,ActivityVO> divisionMap = new LinkedHashMap<Long, ActivityVO>(0);
						
						for (LocationWiseBoothDetailsVO detailsVO : mandalMunciDivisionIdsList) {
							String locationIdStr = detailsVO.getLocationId().toString().substring(0, 1);
							if(locationIdStr.equalsIgnoreCase("2"))
								mandalsIdsList.add(Long.valueOf(detailsVO.getLocationId().toString().substring(1)));
							else if(locationIdStr.equalsIgnoreCase("1"))
								munciORTownORCorpIdsList.add(Long.valueOf(detailsVO.getLocationId().toString().substring(1)));
							else if(locationIdStr.equalsIgnoreCase("3"))
								divisionIdsList.add(Long.valueOf(detailsVO.getLocationId().toString().substring(1)));
						}

						if(mandalsIdsList != null && mandalsIdsList.size()>0)
						{
							searchAttributeVO.getLocationTypeIdsList().clear();
							if(searchAttributeVO.getLevelId().longValue() == 1L)
								searchAttributeVO.getLocationTypeIdsList().add(IConstants.VILLAGE_COMMITTEE_LEVEL_ID);
							else if(searchAttributeVO.getLevelId().longValue() == 2L)
								searchAttributeVO.getLocationTypeIdsList().add(IConstants.MANDAL_COMMITTEE_LEVEL_ID);
							searchAttributeVO.getLocationIdsList().clear();
							searchAttributeVO.getLocationIdsList().addAll(mandalsIdsList);

							questionnairesCount = activityQuestionAnswerDAO.getActivityQuestionnairesCountsByLocation(searchAttributeVO);
							 yesCount = activityQuestionAnswerDAO.getActivityQuestionnairesAttributeCountsByLocation(searchAttributeVO,1L);
							 
							searchAttributeVO.setConditionType("planned");
								plannedActivities = activityLocationInfoDAO.getActivityPlannedInfoCellAndIVRCountsByLocation(searchAttributeVO);
							searchAttributeVO.setConditionType(" infocell ");
								infoCellconductedActivities = activityLocationInfoDAO.getActivityPlannedInfoCellAndIVRCountsByLocation(searchAttributeVO); 
							searchAttributeVO.setConditionType("ivr");
								ivrconductedActivities = activityLocationInfoDAO.getActivityPlannedInfoCellAndIVRCountsByLocation(searchAttributeVO); 
	
							searchAttributeVO.setConditionType("infocell");
								infoCellNotPlannedActivities = activityLocationInfoDAO.getActivityNotPlannedInfoCellAndIVRCountsByLocation(searchAttributeVO); 	
							searchAttributeVO.setConditionType("ivr");
								ivrNotPlannedActivities = activityLocationInfoDAO.getActivityNotPlannedInfoCellAndIVRCountsByLocation(searchAttributeVO); 
							
							segrigateResultsTypes(plannedActivities,locationsMap,"PLANNED","2");
							
							segrigateResultsTypes(ivrconductedActivities,locationsMap,"IVR COVERED","2");
							segrigateResultsTypes(ivrconductedActivities,locationsMap,"IVR COVERED %","2");
							segrigateResultsTypes(ivrNotPlannedActivities,locationsMap,"IVR NOT PLANNED","2");
							segrigateResultsTypes(ivrNotPlannedActivities,locationsMap,"IVR TOTAL","2");
							
							segrigateResultsTypes(infoCellconductedActivities,locationsMap,"INFO CELL COVERED","2");
							segrigateResultsTypes(infoCellconductedActivities,locationsMap,"INFO CELL COVERED %","2");
							segrigateResultsTypes(infoCellNotPlannedActivities,locationsMap,"INFO CELL NOT PLANNED","2");
							segrigateResultsTypes(ivrNotPlannedActivities,locationsMap,"INFO CELL TOTAL","2");
							
							segrigateResultsTypes(yesCount,locationsMap,"WHATSAPP IMAGES COVERED","2");					
							segrigateResultsTypes(yesCount,locationsMap,"WHATSAPP IMAGES COVERED %","2");	
							segrigateResultsTypes(questionnairesCount,locationsMap,"NO OF WHATSAPP IMAGES RECIEVED","2");	
						}
						
						if(munciORTownORCorpIdsList != null && munciORTownORCorpIdsList.size()>0)
						{
							searchAttributeVO.getLocationTypeIdsList().clear();
							if(searchAttributeVO.getLevelId().longValue() == 1L)
								searchAttributeVO.getLocationTypeIdsList().add(IConstants.WARD_COMMITTEE_LEVEL_ID);
							else if(searchAttributeVO.getLevelId().longValue() == 2L)
								searchAttributeVO.getLocationTypeIdsList().add(IConstants.TOWN_COMMITTEE_LEVEL_ID);
							
							searchAttributeVO.getLocationIdsList().clear();
							searchAttributeVO.getLocationIdsList().addAll(munciORTownORCorpIdsList);
							searchAttributeVO.setSearchType("URBAN");
							questionnairesCount = activityQuestionAnswerDAO.getActivityQuestionnairesCountsByLocation(searchAttributeVO);
							yesCount = activityQuestionAnswerDAO.getActivityQuestionnairesAttributeCountsByLocation(searchAttributeVO,1L);
							searchAttributeVO.setConditionType("planned");
							plannedActivities = activityLocationInfoDAO.getActivityPlannedInfoCellAndIVRCountsByLocation(searchAttributeVO);
						searchAttributeVO.setConditionType(" infocell ");
							infoCellconductedActivities = activityLocationInfoDAO.getActivityPlannedInfoCellAndIVRCountsByLocation(searchAttributeVO); 
						searchAttributeVO.setConditionType("ivr");
							ivrconductedActivities = activityLocationInfoDAO.getActivityPlannedInfoCellAndIVRCountsByLocation(searchAttributeVO); 

						searchAttributeVO.setConditionType("infocell");
							infoCellNotPlannedActivities = activityLocationInfoDAO.getActivityNotPlannedInfoCellAndIVRCountsByLocation(searchAttributeVO); 	
						searchAttributeVO.setConditionType("ivr");
							ivrNotPlannedActivities = activityLocationInfoDAO.getActivityNotPlannedInfoCellAndIVRCountsByLocation(searchAttributeVO); 
						
						segrigateResultsTypes(plannedActivities,locationsMap,"PLANNED","1");
						
						segrigateResultsTypes(ivrconductedActivities,locationsMap,"IVR COVERED","1");
						segrigateResultsTypes(ivrconductedActivities,locationsMap,"IVR COVERED %","1");
						segrigateResultsTypes(ivrNotPlannedActivities,locationsMap,"IVR NOT PLANNED","1");
						segrigateResultsTypes(ivrNotPlannedActivities,locationsMap,"IVR TOTAL","1");
						
						segrigateResultsTypes(infoCellconductedActivities,locationsMap,"INFO CELL COVERED","1");
						segrigateResultsTypes(infoCellconductedActivities,locationsMap,"INFO CELL COVERED %","1");
						segrigateResultsTypes(infoCellNotPlannedActivities,locationsMap,"INFO CELL NOT PLANNED","1");
						segrigateResultsTypes(ivrNotPlannedActivities,locationsMap,"INFO CELL TOTAL","1");
						
						segrigateResultsTypes(yesCount,locationsMap,"WHATSAPP IMAGES COVERED","1");					
						segrigateResultsTypes(yesCount,locationsMap,"WHATSAPP IMAGES COVERED %","1");	
						segrigateResultsTypes(questionnairesCount,locationsMap,"NO OF WHATSAPP IMAGES RECIEVED","1");	
						}
						
						if(divisionIdsList != null && divisionIdsList.size()>0)
						{
							searchAttributeVO.getLocationTypeIdsList().clear();
							if(searchAttributeVO.getLevelId().longValue() == 1L)
								searchAttributeVO.getLocationTypeIdsList().add(IConstants.WARD_COMMITTEE_LEVEL_ID);
							else if(searchAttributeVO.getLevelId().longValue() == 2L)
								searchAttributeVO.getLocationTypeIdsList().add(IConstants.DIVISION_COMMITTEE_LEVEL_ID);
							searchAttributeVO.getLocationIdsList().clear();
							searchAttributeVO.getLocationIdsList().addAll(divisionIdsList);
							searchAttributeVO.setSearchType("URBAN");
							questionnairesCount = activityQuestionAnswerDAO.getActivityQuestionnairesCountsByLocation(searchAttributeVO);
							yesCount = activityQuestionAnswerDAO.getActivityQuestionnairesAttributeCountsByLocation(searchAttributeVO,1L);
							searchAttributeVO.setConditionType("planned");
								plannedActivities = activityLocationInfoDAO.getActivityPlannedInfoCellAndIVRCountsByLocation(searchAttributeVO);
							searchAttributeVO.setConditionType(" infocell ");
								infoCellconductedActivities = activityLocationInfoDAO.getActivityPlannedInfoCellAndIVRCountsByLocation(searchAttributeVO); 
							searchAttributeVO.setConditionType("ivr");
								ivrconductedActivities = activityLocationInfoDAO.getActivityPlannedInfoCellAndIVRCountsByLocation(searchAttributeVO); 
	
							searchAttributeVO.setConditionType("infocell");
								infoCellNotPlannedActivities = activityLocationInfoDAO.getActivityNotPlannedInfoCellAndIVRCountsByLocation(searchAttributeVO); 	
							searchAttributeVO.setConditionType("ivr");
								ivrNotPlannedActivities = activityLocationInfoDAO.getActivityNotPlannedInfoCellAndIVRCountsByLocation(searchAttributeVO); 
							
							segrigateResultsTypes(plannedActivities,locationsMap,"PLANNED","3");
							
							segrigateResultsTypes(ivrconductedActivities,locationsMap,"IVR COVERED","3");
							segrigateResultsTypes(ivrconductedActivities,locationsMap,"IVR COVERED %","3");
							segrigateResultsTypes(ivrNotPlannedActivities,locationsMap,"IVR NOT PLANNED","3");
							segrigateResultsTypes(ivrNotPlannedActivities,locationsMap,"IVR TOTAL","3");
							
							segrigateResultsTypes(infoCellconductedActivities,locationsMap,"INFO CELL COVERED","3");
							segrigateResultsTypes(infoCellconductedActivities,locationsMap,"INFO CELL COVERED %","3");
							segrigateResultsTypes(infoCellNotPlannedActivities,locationsMap,"INFO CELL NOT PLANNED","3");
							segrigateResultsTypes(ivrNotPlannedActivities,locationsMap,"INFO CELL TOTAL","3");
							
							segrigateResultsTypes(yesCount,locationsMap,"WHATSAPP IMAGES COVERED","3");					
							segrigateResultsTypes(yesCount,locationsMap,"WHATSAPP IMAGES COVERED %","3");	
							segrigateResultsTypes(questionnairesCount,locationsMap,"NO OF WHATSAPP IMAGES RECIEVED","3");	
						
						}
						
						/*if(mandalMap != null && mandalMap.size()>0)
							locationsMap.putAll(mandalMap);
						else if(MunciTownMap != null && MunciTownMap.size()>0)
							locationsMap.putAll(MunciTownMap);
						else if(divisionMap != null && divisionMap.size()>0)
							locationsMap.putAll(divisionMap);*/
					}
				}
				else if(searchAttributeVO.getSearchType().equalsIgnoreCase(IConstants.VILLAGE)){
				
					List<LocationWiseBoothDetailsVO>  villagesORWardsList =  cadreCommitteeService.getPanchayatWardByMandalId(searchAttributeVO.getLocationId().toString());
					List<Long> locationIdsList = new ArrayList<Long>(0);
					
					if(villagesORWardsList != null && villagesORWardsList.size()>0)
					{
						searchAttributeVO.getLocationTypeIdsList().clear();
						Set<Long> locationTypeIds = new HashSet<Long>(0);
						for (LocationWiseBoothDetailsVO locationWiseBoothDetailsVO : villagesORWardsList) {
							String locationIdStr = locationWiseBoothDetailsVO.getLocationId().toString().substring(0, 1);
							locationIdsList.add(Long.valueOf(locationWiseBoothDetailsVO.getLocationId().toString().substring(1)));
							if(locationIdStr.equalsIgnoreCase("2")){//ward
								locationTypeIds.add(IConstants.WARD_COMMITTEE_LEVEL_ID);
							}
							else if(locationIdStr.equalsIgnoreCase("1")){ // panchayat
								locationTypeIds.add(IConstants.VILLAGE_COMMITTEE_LEVEL_ID);
							}
						}
						if(locationIdsList != null && locationIdsList.size()>0)
						{
							searchAttributeVO.getLocationIdsList().clear();
							searchAttributeVO.getLocationIdsList().addAll(locationIdsList);
							if(locationTypeIds.contains(IConstants.WARD_COMMITTEE_LEVEL_ID)){//ward
								searchAttributeVO.getLocationTypeIdsList().clear();
								searchAttributeVO.getLocationTypeIdsList().add(IConstants.WARD_COMMITTEE_LEVEL_ID);
								searchAttributeVO.setSearchType("WARD");
								questionnairesCount = activityQuestionAnswerDAO.getActivityQuestionnairesCountsByLocation(searchAttributeVO);
								yesCount = activityQuestionAnswerDAO.getActivityQuestionnairesAttributeCountsByLocation(searchAttributeVO,1L);
							searchAttributeVO.setConditionType("planned");
								plannedActivities = activityLocationInfoDAO.getActivityPlannedInfoCellAndIVRCountsByLocation(searchAttributeVO);
							searchAttributeVO.setConditionType(" infocell ");
								infoCellconductedActivities = activityLocationInfoDAO.getActivityPlannedInfoCellAndIVRCountsByLocation(searchAttributeVO); 
							searchAttributeVO.setConditionType("ivr");
								ivrconductedActivities = activityLocationInfoDAO.getActivityPlannedInfoCellAndIVRCountsByLocation(searchAttributeVO); 

							searchAttributeVO.setConditionType("infocell");
								infoCellNotPlannedActivities = activityLocationInfoDAO.getActivityNotPlannedInfoCellAndIVRCountsByLocation(searchAttributeVO); 	
							searchAttributeVO.setConditionType("ivr");
								ivrNotPlannedActivities = activityLocationInfoDAO.getActivityNotPlannedInfoCellAndIVRCountsByLocation(searchAttributeVO); 
							
							segrigateResultsTypes(plannedActivities,locationsMap,"PLANNED","2");
							
							segrigateResultsTypes(ivrconductedActivities,locationsMap,"IVR COVERED","2");
							segrigateResultsTypes(ivrconductedActivities,locationsMap,"IVR COVERED %","2");
							segrigateResultsTypes(ivrNotPlannedActivities,locationsMap,"IVR NOT PLANNED","2");
							segrigateResultsTypes(ivrNotPlannedActivities,locationsMap,"IVR TOTAL","2");
							
							segrigateResultsTypes(infoCellconductedActivities,locationsMap,"INFO CELL COVERED","2");
							segrigateResultsTypes(infoCellconductedActivities,locationsMap,"INFO CELL COVERED %","2");
							segrigateResultsTypes(infoCellNotPlannedActivities,locationsMap,"INFO CELL NOT PLANNED","2");
							segrigateResultsTypes(ivrNotPlannedActivities,locationsMap,"INFO CELL TOTAL","2");
							
							segrigateResultsTypes(yesCount,locationsMap,"WHATSAPP IMAGES COVERED","2");					
							segrigateResultsTypes(yesCount,locationsMap,"WHATSAPP IMAGES COVERED %","2");	
							segrigateResultsTypes(questionnairesCount,locationsMap,"NO OF WHATSAPP IMAGES RECIEVED","2");	
							}
							else if(locationTypeIds.contains(IConstants.VILLAGE_COMMITTEE_LEVEL_ID)){ // panchayat
								searchAttributeVO.getLocationTypeIdsList().clear();
								searchAttributeVO.getLocationTypeIdsList().add(IConstants.VILLAGE_COMMITTEE_LEVEL_ID);
								searchAttributeVO.setSearchType("VILLAGE");
								questionnairesCount = activityQuestionAnswerDAO.getActivityQuestionnairesCountsByLocation(searchAttributeVO);
								yesCount = activityQuestionAnswerDAO.getActivityQuestionnairesAttributeCountsByLocation(searchAttributeVO,1L);
								searchAttributeVO.setConditionType("planned");
								plannedActivities = activityLocationInfoDAO.getActivityPlannedInfoCellAndIVRCountsByLocation(searchAttributeVO);
							searchAttributeVO.setConditionType(" infocell ");
								infoCellconductedActivities = activityLocationInfoDAO.getActivityPlannedInfoCellAndIVRCountsByLocation(searchAttributeVO); 
							searchAttributeVO.setConditionType("ivr");
								ivrconductedActivities = activityLocationInfoDAO.getActivityPlannedInfoCellAndIVRCountsByLocation(searchAttributeVO); 

							searchAttributeVO.setConditionType("infocell");
								infoCellNotPlannedActivities = activityLocationInfoDAO.getActivityNotPlannedInfoCellAndIVRCountsByLocation(searchAttributeVO); 	
							searchAttributeVO.setConditionType("ivr");
								ivrNotPlannedActivities = activityLocationInfoDAO.getActivityNotPlannedInfoCellAndIVRCountsByLocation(searchAttributeVO); 
							
							segrigateResultsTypes(plannedActivities,locationsMap,"PLANNED","1");
							
							segrigateResultsTypes(ivrconductedActivities,locationsMap,"IVR COVERED","1");
							segrigateResultsTypes(ivrconductedActivities,locationsMap,"IVR COVERED %","1");
							segrigateResultsTypes(ivrNotPlannedActivities,locationsMap,"IVR NOT PLANNED","1");
							segrigateResultsTypes(ivrNotPlannedActivities,locationsMap,"IVR TOTAL","1");
							
							segrigateResultsTypes(infoCellconductedActivities,locationsMap,"INFO CELL COVERED","1");
							segrigateResultsTypes(infoCellconductedActivities,locationsMap,"INFO CELL COVERED %","1");
							segrigateResultsTypes(infoCellNotPlannedActivities,locationsMap,"INFO CELL NOT PLANNED","1");
							segrigateResultsTypes(ivrNotPlannedActivities,locationsMap,"INFO CELL TOTAL","1");
							
							segrigateResultsTypes(yesCount,locationsMap,"WHATSAPP IMAGES COVERED","1");					
							segrigateResultsTypes(yesCount,locationsMap,"WHATSAPP IMAGES COVERED %","1");	
							segrigateResultsTypes(questionnairesCount,locationsMap,"NO OF WHATSAPP IMAGES RECIEVED","1");	
							
							}
						}
					}
				}
				if(returnVO != null && locationsMap != null && locationsMap.size()>0)
				{
					segrigatefinalResultsTypes(null,locationsMap,"","");
					
					if(activityLevelId != null && activityLevelId.longValue()>0L)
					{
						if(activityLevelId.longValue() == 1L)
						{
							searchAttributeVO.getLocationTypeIdsList().add(6L);
							searchAttributeVO.getLocationTypeIdsList().add(8L);
						}
						else if(activityLevelId.longValue() == 2L)
						{
							searchAttributeVO.getLocationTypeIdsList().add(5L);
							searchAttributeVO.getLocationTypeIdsList().add(7L);
							searchAttributeVO.getLocationTypeIdsList().add(9L);
						}
						else if(activityLevelId.longValue() == 3L)
						{
							searchAttributeVO.getLocationTypeIdsList().add(10L);
						}
						else if(activityLevelId.longValue() == 4L)
						{
							searchAttributeVO.getLocationTypeIdsList().add(11L);
						}
					}
					
					Long totalAreasCount =0L;
					Long totalPlannedCount =0L;
					for (Long locationId : locationsMap.keySet()) {
						searchAttributeVO.setScopeValue(locationId);
						totalAreasCount =0L;
						totalPlannedCount =0L;
						
						ActivityVO activityVO = locationsMap.get(locationId);
						if(activityVO != null)
						{
							if(searchAttributeVO.getSearchType().equalsIgnoreCase(IConstants.STATE)){
								totalAreasCount = returnVO.getTotalCount();
								totalPlannedCount = activityVO.getPlannedCount();
							}
							if(searchAttributeVO.getSearchType().equalsIgnoreCase(IConstants.DISTRICT)){
								searchAttributeVO.setScopeId(3L);
								activityVO.setName(activityVO.getName()+" District");
								List<BasicVO> areaWiseCountLsit = regionServiceDataImp.areaCountListByAreaIdsOnScope(searchAttributeVO);
								if(areaWiseCountLsit != null && areaWiseCountLsit.size()>0)
								{
									totalAreasCount = totalAreasCount+getTotalAreaCountByList(areaWiseCountLsit);
									activityVO.setTotalCount(totalAreasCount);
									totalPlannedCount = activityVO.getPlannedCount();
								}
								
							}
							else if(searchAttributeVO.getSearchType().equalsIgnoreCase(IConstants.CONSTITUENCY)){
								searchAttributeVO.setScopeId(4L);
								activityVO.setName(activityVO.getName()+" Constituency");
								List<BasicVO> areaWiseCountLsit = regionServiceDataImp.areaCountListByAreaIdsOnScope(searchAttributeVO);
								if(areaWiseCountLsit != null && areaWiseCountLsit.size()>0)
								{
									totalAreasCount = totalAreasCount+getTotalAreaCountByList(areaWiseCountLsit);
									activityVO.setTotalCount(totalAreasCount);
								}
								totalPlannedCount = activityVO.getPlannedCount();
								
							}
							else if(searchAttributeVO.getSearchType().equalsIgnoreCase(IConstants.MANDAL) || searchAttributeVO.getSearchType().equalsIgnoreCase(IConstants.URBAN)){
								searchAttributeVO.setScopeId(5L);
								String firstCharId = locationId.toString().trim().substring(0,1);
								if(firstCharId.trim().equalsIgnoreCase("2")){
									String mandalTownORDivisionId = locationId.toString().trim().substring(1);
									searchAttributeVO.setScopeValue(Long.valueOf(mandalTownORDivisionId));
									activityVO.setName(activityVO.getName()+" Mandal");
									List<BasicVO> areaWiseCountLsit = regionServiceDataImp.areaCountListByAreaIdsOnScope(searchAttributeVO);
									if(areaWiseCountLsit != null && areaWiseCountLsit.size()>0)
									{
										totalAreasCount = totalAreasCount+getTotalAreaCountByList(areaWiseCountLsit);
										activityVO.setTotalCount(totalAreasCount);
									}
									totalPlannedCount = activityVO.getPlannedCount();
								}
								else{
									searchAttributeVO.setScopeId(7L);
									firstCharId = locationId.toString().trim().substring(0,1);
									if(firstCharId.trim().equalsIgnoreCase("1")){
									String mandalTownORDivisionId = locationId.toString().trim().substring(1);
									searchAttributeVO.setScopeValue(Long.valueOf(mandalTownORDivisionId));
									activityVO.setName(activityVO.getName());
									List<BasicVO> areaWiseCountLsit = regionServiceDataImp.areaCountListByAreaIdsOnScope(searchAttributeVO);
									if(areaWiseCountLsit != null && areaWiseCountLsit.size()>0)
									{
										totalAreasCount = totalAreasCount+getTotalAreaCountByList(areaWiseCountLsit);
										activityVO.setTotalCount(totalAreasCount);
									}
									totalPlannedCount = activityVO.getPlannedCount();
									}
								}
							}
						/*	else if(searchAttributeVO.getSearchType().equalsIgnoreCase(IConstants.URBAN)){
								searchAttributeVO.setScopeId(5L);
								String firstCharId = locationId.toString().trim().substring(0,1);
								if(firstCharId.trim().equalsIgnoreCase("2")){
									String mandalTownORDivisionId = locationId.toString().trim().substring(1);
									searchAttributeVO.setScopeValue(Long.valueOf(mandalTownORDivisionId));
									activityVO.setName(activityVO.getName()+" Mandal");
									List<BasicVO> areaWiseCountLsit = regionServiceDataImp.areaCountListByAreaIdsOnScope(searchAttributeVO);
									if(areaWiseCountLsit != null && areaWiseCountLsit.size()>0)
									{
										totalAreasCount = totalAreasCount+getTotalAreaCountByList(areaWiseCountLsit);
										activityVO.setTotalCount(totalAreasCount);
									}
									totalPlannedCount = activityVO.getPlannedCount();
								}
								else{
									searchAttributeVO.setScopeId(7L);
									firstCharId = locationId.toString().trim().substring(0,1);
									if(firstCharId.trim().equalsIgnoreCase("1")){
									String mandalTownORDivisionId = locationId.toString().trim().substring(1);
									searchAttributeVO.setScopeValue(Long.valueOf(mandalTownORDivisionId));
									activityVO.setName(activityVO.getName());
									List<BasicVO> areaWiseCountLsit = regionServiceDataImp.areaCountListByAreaIdsOnScope(searchAttributeVO);
									if(areaWiseCountLsit != null && areaWiseCountLsit.size()>0)
									{
										totalAreasCount = totalAreasCount+getTotalAreaCountByList(areaWiseCountLsit);
										activityVO.setTotalCount(totalAreasCount);
									}
									totalPlannedCount = activityVO.getPlannedCount();
									}
								}
							}*/
							else if(searchAttributeVO.getSearchType().equalsIgnoreCase(IConstants.VILLAGE) || searchAttributeVO.getSearchType().equalsIgnoreCase(IConstants.WARD)){
								activityVO.setTotalCount(1L);
							}
							if(!searchAttributeVO.getSearchType().equalsIgnoreCase(IConstants.VILLAGE) && totalPlannedCount != null && totalPlannedCount.longValue()>0L)
							{
								List<ActivityVO> activityVOList  = activityVO.getActivityVoList();
								if(activityVOList != null && activityVOList.size()>0)
								{
									for (ActivityVO vo : activityVOList) {
										if(vo.getConductedCount() != null && vo.getConductedCount().longValue()>0L)
										{
											double perc = (vo.getConductedCount() * 100.0)/totalPlannedCount;
											String percentage = commonMethodsUtilService.roundTo2DigitsFloatValueAsString(Float.valueOf(String.valueOf(perc)));
											vo.setPercentage(percentage);
										}
									}
								}
								if(activityVO.getPlannedCount() != null && activityVO.getPlannedCount().longValue()>0L && totalAreasCount.longValue()>0L)
								{
									double perc = (activityVO.getPlannedCount() * 100.0)/totalAreasCount;
									String percentage = commonMethodsUtilService.roundTo2DigitsFloatValueAsString(Float.valueOf(String.valueOf(perc)));
									activityVO.setPercentage(percentage);
								}
								if(activityVO.getNotPlannedCount() != null && activityVO.getNotPlannedCount().longValue()>0L && totalAreasCount.longValue()>0L)
								{
									double perc1 = (activityVO.getNotPlannedCount() * 100.0)/totalAreasCount;
									String percentage1 = commonMethodsUtilService.roundTo2DigitsFloatValueAsString(Float.valueOf(String.valueOf(perc1)));
									activityVO.setNotPlannedPerc(percentage1);
								}
								returnVO.getActivityVoList().add(locationsMap.get(locationId));
							}
							else{
								returnVO.getActivityVoList().add(locationsMap.get(locationId));
							}
						}
					}
				}
			}
			
			if(returnVO != null && returnVO.getActivityVoList() != null && returnVO.getActivityVoList().size()>0)
			{
				Long totalCount = 0L;
				if(searchAttributeVO.getSearchType().equalsIgnoreCase(IConstants.STATE)){
					totalCount = returnVO.getTotalCount();
					if(totalCount.longValue()>0L)
						for (ActivityVO activityVO : returnVO.getActivityVoList()) {
							
							double perc = (activityVO.getIvrTotal() * 100.0)/totalCount;
							String percentage = commonMethodsUtilService.roundTo2DigitsFloatValueAsString(Float.valueOf(String.valueOf(perc)));
							activityVO.setIvrTotalPerc(percentage);
							
							double perc1 = (activityVO.getInfoCellTotal() * 100.0)/totalCount;
							String percentage1 = commonMethodsUtilService.roundTo2DigitsFloatValueAsString(Float.valueOf(String.valueOf(perc1)));
							activityVO.setInfoCellTotalPerc(percentage1);
						}
					if(returnVO.getPercentage() == null)
						returnVO.setPercentage("0.00");
					if(returnVO.getIvrcoveredPerc() == null)
						returnVO.setIvrcoveredPerc("0.00");
					if(returnVO.getIvrTotalPerc() == null)
						returnVO.setIvrTotalPerc("0.00");
					
					if(returnVO.getInfoCellcoveredPerc() == null)
						returnVO.setInfoCellcoveredPerc("0.00");
					if(returnVO.getInfoCellTotalPerc() == null)
						returnVO.setInfoCellTotalPerc("0.00");
					
					if(returnVO.getWhatsAppCoveredPerc() == null)
						returnVO.setWhatsAppCoveredPerc("0.00");
				}
				else{
					for (ActivityVO activityVO : returnVO.getActivityVoList()) {
						
						if(activityVO.getIvrTotal() != null && activityVO.getTotalCount()!=null){
						double perc = (activityVO.getIvrTotal() * 100.0)/activityVO.getTotalCount();
						String percentage = commonMethodsUtilService.roundTo2DigitsFloatValueAsString(Float.valueOf(String.valueOf(perc)));
						activityVO.setIvrTotalPerc(percentage);
						}
						else if(activityVO.getIvrcovered() != null && activityVO.getTotalCount()!=null){
							double perc = (activityVO.getIvrcovered() * 100.0)/activityVO.getTotalCount();
							String percentage = commonMethodsUtilService.roundTo2DigitsFloatValueAsString(Float.valueOf(String.valueOf(perc)));
							activityVO.setIvrTotalPerc(percentage);
						}
						else if(activityVO.getIvrNotPlanned() != null && activityVO.getTotalCount()!=null){
							double perc = (activityVO.getIvrNotPlanned() * 100.0)/activityVO.getTotalCount();
							String percentage = commonMethodsUtilService.roundTo2DigitsFloatValueAsString(Float.valueOf(String.valueOf(perc)));
							activityVO.setIvrTotalPerc(percentage);
						}
						if(activityVO.getInfoCellTotal() != null && activityVO.getTotalCount() !=null){
						double perc1 = (activityVO.getInfoCellTotal() * 100.0)/activityVO.getTotalCount();
						String percentage1 = commonMethodsUtilService.roundTo2DigitsFloatValueAsString(Float.valueOf(String.valueOf(perc1)));
						activityVO.setInfoCellTotalPerc(percentage1);
						}
						else if(activityVO.getInfoCellcovered() != null && activityVO.getTotalCount() !=null){
							double perc1 = (activityVO.getInfoCellcovered() * 100.0)/activityVO.getTotalCount();
							String percentage1 = commonMethodsUtilService.roundTo2DigitsFloatValueAsString(Float.valueOf(String.valueOf(perc1)));
							activityVO.setInfoCellTotalPerc(percentage1);
							}
						else if(activityVO.getInfoCellNotPlanned() != null && activityVO.getTotalCount() !=null){
							double perc1 = (activityVO.getInfoCellNotPlanned() * 100.0)/activityVO.getTotalCount();
							String percentage1 = commonMethodsUtilService.roundTo2DigitsFloatValueAsString(Float.valueOf(String.valueOf(perc1)));
							activityVO.setInfoCellTotalPerc(percentage1);
							}
						
						if(activityVO.getPercentage() == null)
							activityVO.setPercentage("0.00");
						if(activityVO.getIvrcoveredPerc() == null)
							activityVO.setIvrcoveredPerc("0.00");
						if(activityVO.getIvrTotalPerc() == null)
							activityVO.setIvrTotalPerc("0.00");
						
						if(activityVO.getInfoCellcoveredPerc() == null)
							activityVO.setInfoCellcoveredPerc("0.00");
						if(activityVO.getInfoCellTotalPerc() == null)
							activityVO.setInfoCellTotalPerc("0.00");
						
						if(activityVO.getWhatsAppCoveredPerc() == null)
							activityVO.setWhatsAppCoveredPerc("0.00");
					}
				}
			}
		} catch (Exception e) {
			LOG.error(" Exception occured in getActivityDetailsBySearchCriteria() ActivityService Class... ",e);
		}
		return returnVO;
	}

	public Map<Long,ActivityVO> getAttributeListByQuestionnaireList(List<Long> questionnairesList){
		
		Map<Long,ActivityVO> finalMap = new LinkedHashMap<Long, ActivityVO>();
		
		try {
			
			//0.questionnaireId,1.attributeId,2.attributeName
			List<Object[]> list = activityAttributeQuestionnaireInfoDAO.getAttributeListByQuestionnaires(questionnairesList);
			
			if(list != null && list.size() > 0){
				for (Object[] objects : list) {
					ActivityVO vo = new ActivityVO();
					
					Long questionnaireId = (Long) (objects[0] != null ? objects[0]:0l);
					vo.setId((Long) (objects[1] != null ? objects[1]:0l));
					vo.setName(objects[2] != null ? objects[2].toString():"");
					
					finalMap.put(questionnaireId, vo);
				}
			}
		} catch (Exception e) {
			LOG.error("Exception raised in getAttributeListByQuestionnaireList in ActivityService service", e);
		}
		return finalMap;
	}
	
	public List<ActivityVO> getActivityDayWiseCountsByLocation(SearchAttributeVO searchAttributeVO){
		
		List<ActivityVO> finalList = new ArrayList<ActivityVO>();
		try {
			
			Map<String,ActivityVO> datesMap = new LinkedHashMap<String, ActivityVO>();
			Map<String,ActivityVO> initialDateMap = new LinkedHashMap<String, ActivityVO>();
			Activity activity = activityScopeDAO.get(searchAttributeVO.getAttributesIdsList().get(0)).getActivity();
			if(searchAttributeVO.getStartDate() != null && searchAttributeVO.getEndDate() != null)
				initialDateMap = commonMethodsUtilService.getDatesWiseCounts(searchAttributeVO.getStartDate(), searchAttributeVO.getEndDate(), "Day");
			else
				initialDateMap = commonMethodsUtilService.getDatesWiseCounts(activity.getStartDate(), activity.getEndDate(), "Day");

			Set<String> datesList = initialDateMap.keySet();
			List<String> availableDatesList  =null;
			if(datesList != null && datesList.size() > 0){
				availableDatesList = commonMethodsUtilService.getAvailableDates(datesList, activity.getStartDate().toString(), activity.getEndDate().toString());
			}
			SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
			try {
				datesMap = commonMethodsUtilService.getDatesWiseCounts(sdf.parse(availableDatesList.get(0)), sdf.parse(availableDatesList.get(availableDatesList.size()-1)), "Day");
			} catch (Exception e) {
				datesMap = commonMethodsUtilService.getDatesWiseCounts(activity.getStartDate(), activity.getEndDate(), "Day");
			}
			
			searchAttributeVO.getLocationIdsList().add(searchAttributeVO.getLocationId());
			List<Object[]> plannedActivities = null;
			List<Object[]> infoCellconductedActivities = null;
			List<Object[]> ivrconductedActivities = null;
			
			List<Object[]> infoCellNotPlannedActivities = null;
			List<Object[]> ivrNotPlannedActivities = null;
			 
			if(searchAttributeVO.getSearchType() != null)
			{
				if(searchAttributeVO.getSearchType().trim().length()>0)
				{
					if(searchAttributeVO.getSearchType().equalsIgnoreCase(IConstants.VILLAGE) || searchAttributeVO.getSearchType().equalsIgnoreCase(IConstants.WARD)
							 || searchAttributeVO.getSearchType().equalsIgnoreCase(IConstants.MANDAL) || searchAttributeVO.getSearchType().equalsIgnoreCase(IConstants.URBAN))
					{
						searchAttributeVO.getLocationIdsList().add(Long.valueOf(searchAttributeVO.getLocationId().toString().substring(1)));
					}
				}
			}
			
			
			searchAttributeVO.setConditionType("planned");
				plannedActivities = activityLocationInfoDAO.getActivityDayWiseCountsByLocation(searchAttributeVO);
			searchAttributeVO.setConditionType(" infocell ");
				infoCellconductedActivities = activityLocationInfoDAO.getActivityDayWiseCountsByLocation(searchAttributeVO); 
			searchAttributeVO.setConditionType("ivr");
				ivrconductedActivities = activityLocationInfoDAO.getActivityDayWiseCountsByLocation(searchAttributeVO); 

			searchAttributeVO.setConditionType("infocell");
				infoCellNotPlannedActivities = activityLocationInfoDAO.getActivityDayWiseCountsByLocation(searchAttributeVO); 	
			searchAttributeVO.setConditionType("ivr");
				ivrNotPlannedActivities = activityLocationInfoDAO.getActivityDayWiseCountsByLocation(searchAttributeVO); 
				List<Object[]> questionnairesCount =activityQuestionAnswerDAO.getActivityQuestionnairesCountsByDayWise(searchAttributeVO);
				List<Object[]> yesCount = activityQuestionAnswerDAO.getActivityQuestionnairesAttributeCountsByDayWise(searchAttributeVO,1L);
				 
				buildResult(plannedActivities,datesMap,"PLANNED",null);
				
				buildResult(ivrconductedActivities,datesMap,"IVR COVERED",null);
				buildResult(ivrconductedActivities,datesMap,"IVR COVERED %",null);
				buildResult(ivrNotPlannedActivities,datesMap,"IVR NOT PLANNED",null);
				buildResult(ivrNotPlannedActivities,datesMap,"IVR TOTAL",null);
				
				buildResult(infoCellconductedActivities,datesMap,"INFO CELL COVERED",null);
				buildResult(infoCellconductedActivities,datesMap,"INFO CELL COVERED %",null);
				buildResult(infoCellNotPlannedActivities,datesMap,"INFO CELL NOT PLANNED",null);
				buildResult(ivrNotPlannedActivities,datesMap,"INFO CELL TOTAL",null);
				
				buildResult(yesCount,datesMap,"WHATSAPP IMAGES COVERED",null);					
				buildResult(yesCount,datesMap,"WHATSAPP IMAGES COVERED %",null);	
				buildResult(questionnairesCount,datesMap,"NO OF WHATSAPP IMAGES RECIEVED",null);	
				
			if(datesMap != null && datesMap.size()>0)
			{
				for (String dateStr : datesMap.keySet()) {
					ActivityVO vo = datesMap.get(dateStr);
					finalList.add(vo);
				}
			}
		} catch (Exception e) {
			LOG.error("Exception raised in getActivityDayWiseCountsByLocation in ActivityService service", e);
		}
		return finalList;
	}
	
	@SuppressWarnings("null")
	public void buildResult(List<Object[]> activitiesList,Map<String,ActivityVO> datesMap,String type,String extentionNo)
	{
		try {
			if(activitiesList != null && activitiesList.size()>0)
			{
				Map<String,ActivityVO> activityMap1 = null;
				for (Object[] activity : activitiesList) {
					String locationId = commonMethodsUtilService.getStringValueForObject(activity[3]);
					//Long areaId = Long.valueOf(locationId.trim());
				
					ActivityVO vo = datesMap.get(locationId);
					if(vo != null)
					{
						if(type != null)
						{
							Long count = commonMethodsUtilService.getLongValueForObject(activity[4]);
							if(type.trim().equalsIgnoreCase("PLANNED")){
								if(vo.getPlannedCount() != null)
									vo.setPlannedCount(count+vo.getPlannedCount());
								else
									vo.setPlannedCount(count);
							}
							else if(type.trim().equalsIgnoreCase("IVR COVERED")){
								if(vo.getIvrcovered() != null)
									vo.setIvrcovered(count+vo.getIvrcovered());
								else
									vo.setIvrcovered(count);
							}
							else if(type.trim().equalsIgnoreCase("IVR COVERED %") && vo.getIvrcovered() != null && vo.getIvrcovered().longValue()>0L
									 && vo.getPlannedCount() != null && vo.getPlannedCount().longValue()>0L){
								double perc = (vo.getIvrcovered()*100.0)/vo.getPlannedCount();;
								String percentage = commonMethodsUtilService.roundTo2DigitsFloatValueAsString(Float.valueOf(String.valueOf(perc)));
								vo.setIvrcoveredPerc(percentage);
							}
							else if(type.trim().equalsIgnoreCase("IVR NOT PLANNED")){
								if(vo.getIvrNotPlanned() != null)
									vo.setIvrNotPlanned(count+vo.getIvrNotPlanned());
								else
									vo.setIvrNotPlanned(count);
							}
							else if(type.trim().equalsIgnoreCase("IVR TOTAL")){
								vo.setIvrTotal(vo.getIvrcovered() + vo.getIvrNotPlanned());
							}
							else if(type.trim().equalsIgnoreCase("INFO CELL COVERED"))
								vo.setInfoCellcovered(count);
							else if(type.trim().equalsIgnoreCase("INFO CELL COVERED %") && vo.getInfoCellcovered() != null && vo.getInfoCellcovered().longValue()>0L 
									&& vo.getPlannedCount() != null && vo.getPlannedCount().longValue()>0L){
								double perc = (vo.getInfoCellcovered()*100.0)/vo.getPlannedCount();;
								String percentage = commonMethodsUtilService.roundTo2DigitsFloatValueAsString(Float.valueOf(String.valueOf(perc)));
								vo.setInfoCellcoveredPerc(percentage);
							}
							else if(type.trim().equalsIgnoreCase("INFO CELL NOT PLANNED")){
								if(vo.getInfoCellNotPlanned() != null)
									vo.setInfoCellNotPlanned(count+vo.getInfoCellNotPlanned());
								else
									vo.setInfoCellNotPlanned(count);
							}
							else if(type.trim().equalsIgnoreCase("INFO CELL TOTAL")){
								vo.setInfoCellTotal(vo.getInfoCellcovered() + vo.getInfoCellNotPlanned());
							}
							else if(type.trim().equalsIgnoreCase("WHATSAPP IMAGES COVERED")){
								if(vo.getWhatsAppCovered() != null)
									vo.setWhatsAppCovered(count+vo.getWhatsAppCovered());
								else
									vo.setWhatsAppCovered(count);
							}
							else if(type.trim().equalsIgnoreCase("WHATSAPP IMAGES COVERED %") && vo.getWhatsAppCovered() != null && vo.getWhatsAppCovered().longValue()>0L && 
									vo.getInfoCellTotal() != null && vo.getInfoCellTotal().longValue()>0L){
								double perc = (vo.getWhatsAppCovered()*100.0)/vo.getInfoCellTotal();
								String percentage = commonMethodsUtilService.roundTo2DigitsFloatValueAsString(Float.valueOf(String.valueOf(perc)));
								vo.setWhatsAppCoveredPerc(percentage);
							}
							else if(type.trim().equalsIgnoreCase("NO OF WHATSAPP IMAGES RECIEVED")){
								if(vo.getImagesCount() != null)
									vo.setImagesCount(count+vo.getImagesCount());
								else
									vo.setImagesCount(count);
							}
						}
					}
				}
			}
			
		} catch (Exception e) {
			 LOG.error("Exception Occured in segrigateFirstResult() method, Exception - ",e);
		}
	}
	

	
	
	public ResultStatus eventsUploadForm(final EventFileUploadVO eventFileUploadVO)
	{
		final ResultStatus resultStatus = new ResultStatus();
		try{
		
			transactionTemplate.execute(new TransactionCallbackWithoutResult() {
				public void doInTransactionWithoutResult(TransactionStatus status) {
					
				
			
			
			UserAddress userAddress = saveUserAddress(eventFileUploadVO);
			
			ActivityDocument activityDocument = new ActivityDocument();
			if(eventFileUploadVO.getActivityDate() != null && !eventFileUploadVO.getActivityDate().equals("undefined"))
			{
				SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
			    try {
					activityDocument.setActivityDate(dateFormat.parse(eventFileUploadVO.getActivityDate()));
				} catch (ParseException e) {
					LOG.error(" Exception Occured in eventsUploadForm() method, Exception - ",e);	
				}
			}
			
			StringBuilder str = new StringBuilder();
			Integer randomNumber = RandomNumberGeneraion.randomGenerator(8);
			str.append(randomNumber).append(".").append(eventFileUploadVO.getFileExtension());
			activityDocument.setDocumentName(str.toString());
			
			String folderName = folderCreation();
			
			
			Calendar calendar = Calendar.getInstance();
			calendar.setTime(new Date());
			 int year = calendar.get(Calendar.YEAR);
			 int month = calendar.get(Calendar.MONTH);
			 int day = calendar.get(Calendar.DAY_OF_MONTH);
			 int temp = month+1;
			 
			 StringBuilder pathBuilder = new StringBuilder();
			 pathBuilder.append(year).append("/").append(temp).append("_").append(day).append("/").append(randomNumber).append(".")
			 .append(eventFileUploadVO.getFileExtension());
			 
			activityDocument.setPath(pathBuilder.toString());
			
			String destPath = folderName+"/"+randomNumber+"."+eventFileUploadVO.getFileExtension();
			copyFile(eventFileUploadVO.getFile().getAbsolutePath(),destPath);
			
			activityDocument.setInsertedBy(eventFileUploadVO.getUserId());
			activityDocument.setUpdatedBy(eventFileUploadVO.getUpdatedBy());
			
			activityDocument.setInsertedTime(dateUtilService.getCurrentDateAndTime());
			activityDocument.setUpdatedTime(dateUtilService.getCurrentDateAndTime());
			activityDocument.setActivityScopeId(eventFileUploadVO.getActivityScopeId());
			
			activityDocument = activityDocumentDAO.save(activityDocument);
			
		    ActivityInfoDocument activityInfoDocument = new ActivityInfoDocument();
		    activityInfoDocument.setActivityDocument(activityDocument);
		    activityInfoDocument.setInsertedBy(eventFileUploadVO.getUserId());
		    activityInfoDocument.setUpdatedBy(eventFileUploadVO.getUpdatedBy());
			
		    activityInfoDocument.setInsertedTime(dateUtilService.getCurrentDateAndTime());
		    activityInfoDocument.setUpdatedTime(dateUtilService.getCurrentDateAndTime());
		    activityInfoDocument.setActivityAddressId(userAddress.getUserAddressId());
		    
		    activityInfoDocument.setLocationScopeId(eventFileUploadVO.getLocationScopeId());
		    activityInfoDocument.setLocationValueAddress(eventFileUploadVO.getLaocationValueAddress());
			
		    activityInfoDocument.setDay(eventFileUploadVO.getDay());
		    
		    activityInfoDocument.setIsDeleted("N");
		    
		    activityInfoDocument = activityInfoDocumentDAO.save(activityInfoDocument);
		    
		    resultStatus.setResultCode(ResultCodeMapper.SUCCESS);
		    resultStatus.setResultState(activityInfoDocument.getActivityInfoDocumentId());
		    
			}	
	      });
			
		}catch (Exception e) {
			resultStatus.setResultCode(ResultCodeMapper.FAILURE);
			LOG.error(" Exception Occured in eventsUploadForm() method, Exception - ",e);
		}
		return resultStatus;
	}
	
	
	public String copyFile(String sourcePath,String destinationPath){
		 try{
			File destFile = new File(destinationPath);
			 if (!destFile.exists()) 
				 destFile.createNewFile();
			 File file = new File(sourcePath);
			if(file.exists()){
				FileUtils.copyFile(file,destFile);
				LOG.error("Copy success");
				return "success";
			}
		  }catch(Exception e){
			  LOG.error("Exception raised in copyFile ", e);
			  LOG.error("Copy error");
			  return "error";
		  }
		 return "failure";
		}
	
	public static String folderCreation(){
	  	 try {
	  		 LOG.debug(" in FolderForArticle ");
	  		
	  		Calendar calendar = Calendar.getInstance();
			calendar.setTime(new Date());
			 int year = calendar.get(Calendar.YEAR);
			 int month = calendar.get(Calendar.MONTH);
			 int day = calendar.get(Calendar.DAY_OF_MONTH);
			
			 String staticPath = IConstants.STATIC_CONTENT_FOLDER_URL;
			 String activityDocDir = createFolder(staticPath+IConstants.ACTIVITY_DOCUMENTS);
			 
			 String yr = String.valueOf(year); // YEAR YYYY
			 String yrDir = staticPath+IConstants.ACTIVITY_DOCUMENTS+"/"+yr;
			 
			 String yrFldrSts = createFolder(yrDir);
			 if(!yrFldrSts.equalsIgnoreCase("SUCCESS")){
				 return "FAILED";
			 }
			 
			 StringBuilder str = new StringBuilder();
			 int temp = month+1;
			 str.append(temp).append("-").append(day);
			 
			 
			 String mnth = str.toString();
			 String mnthDir = staticPath+"/"+IConstants.ACTIVITY_DOCUMENTS+"/"+yr+"/"+mnth;
			 String mnthDirSts = createFolder(mnthDir);
			 if(!mnthDirSts.equalsIgnoreCase("SUCCESS")){
				 return "FAILED";
			 }
			 
			 return staticPath+"/"+IConstants.ACTIVITY_DOCUMENTS+"/"+yr+"/"+mnth;
			 
		} catch (Exception e) {
			LOG.error(" Failed to Create");
			return "FAILED";
		}
		 
		 
		 
}
	
	public static String createFolder(String dir){
	 	try {
			File theDir = new File(dir);
			  // if the directory does not exist, create it
			  if (!theDir.exists()) {
			    boolean result = false;
			    try{
			        theDir.mkdir();
			        result = true;
			     } catch(SecurityException se){
			        //handle it
			     }        
			     if(result) {    
			      LOG.debug("DIR With Name "+dir+" created");  
			     }
			  }else{
				  LOG.debug("DIR With Name "+dir+" EXISTS");
			  }
			  return "SUCCESS";
		} catch (Exception e) {
			LOG.error(dir+" Failed to Create");
			return "FAILED";
		}
	}
	
	
	public UserAddress saveUserAddress(EventFileUploadVO eventFileUploadVO)
	{
		try{
			UserAddress userAddress = new UserAddress();
			
			Long levelId = null;
			Long levelValue = null;
			
			if(eventFileUploadVO.getLevelId().equals(2l)){
				userAddress.setState(stateDAO.get(eventFileUploadVO.getStateId()));
				levelId = 2l;
				levelValue = eventFileUploadVO.getStateId();
			}
			else if(eventFileUploadVO.getLevelId().equals(3l))
			{
				userAddress.setState(stateDAO.get(eventFileUploadVO.getStateId()));
				userAddress.setDistrict(districtDAO.get(eventFileUploadVO.getDistrictId()));
				levelId = 3l;
				levelValue = eventFileUploadVO.getDistrictId();
			}
			else if(eventFileUploadVO.getLevelId().equals(4l))
			{
				userAddress.setState(stateDAO.get(eventFileUploadVO.getStateId()));
				userAddress.setDistrict(districtDAO.get(eventFileUploadVO.getDistrictId()));
				userAddress.setConstituency(constituencyDAO.get(eventFileUploadVO.getConstituencyId()));
				levelId = 4l;
				levelValue = eventFileUploadVO.getConstituencyId();
			}
			else if(eventFileUploadVO.getLevelId().equals(5l))
			{
				userAddress.setState(stateDAO.get(eventFileUploadVO.getStateId()));
				userAddress.setDistrict(districtDAO.get(eventFileUploadVO.getDistrictId()));
				userAddress.setConstituency(constituencyDAO.get(eventFileUploadVO.getConstituencyId()));
				
				Long id = eventFileUploadVO.getMandalOrMuncipalityId();
				String idStr = id.toString().substring(0,1);
				
				levelValue = Long.parseLong(id.toString().substring(1,id.toString().length()));
				
				if(idStr.equalsIgnoreCase("1")){//Muncipality
					userAddress.setLocalElectionBody(localElectionBodyDAO.get(levelValue));
					levelId = 6l;
				}
				else{
					userAddress.setTehsil(tehsilDAO.get(levelValue));
					levelId = 5l;	
				}
			}
			else if(eventFileUploadVO.getLevelId().equals(6l))
			{
				userAddress.setState(stateDAO.get(eventFileUploadVO.getStateId()));
				userAddress.setDistrict(districtDAO.get(eventFileUploadVO.getDistrictId()));
				userAddress.setConstituency(constituencyDAO.get(eventFileUploadVO.getConstituencyId()));
				
				Long id = eventFileUploadVO.getPanchayatId();
				String idStr = id.toString().substring(0,1);
				
				levelValue = Long.parseLong(id.toString().substring(1,id.toString().length()));//panchayatOrWard
				
				Long mandalOrMunId = eventFileUploadVO.getMandalOrMuncipalityId();
				Long manMunValue = Long.parseLong(mandalOrMunId.toString().substring(1,mandalOrMunId.toString().length()));
				
				//Panchayat
				if(idStr.equalsIgnoreCase("1"))
				{
					levelId = 7l;
					userAddress.setTehsil(tehsilDAO.get(manMunValue));
					userAddress.setPanchayatId(levelValue);
				}
				else{//ward
					levelId = 8l;
					userAddress.setLocalElectionBody(localElectionBodyDAO.get(manMunValue));
					userAddress.setWard(constituencyDAO.get(levelValue));
				}
				
			}
			
			userAddress = userAddressDAO.save(userAddress);
			
			eventFileUploadVO.setLocationScopeId(levelId);
			eventFileUploadVO.setLaocationValueAddress(levelValue);
			return userAddress;
			
		}catch (Exception e) {
			LOG.error(" Exception Occured in saveUserAddress() method, Exception - ",e);
		}
		return null;
	}
	
	
	
	public ResultStatus deleteEventUploadFilebyActivityInfoDocId(Long acitivityInfoDocId)
	{
		ResultStatus resultStatus = new ResultStatus();
		try{
			
			activityInfoDocumentDAO.deleteEventUploadFilebyActivityInfoDocId(acitivityInfoDocId);
			resultStatus.setResultCode(0);
		}catch (Exception e) {
			resultStatus.setResultCode(1);
			LOG.error(" Exception Occured in deleteEventUploadFilebyActivityInfoDocId() method, Exception - ",e);
		}
		return resultStatus;
	}
	
	public ResultStatus saveActivityQuestionnaireDetails(final ActivityVO finalvo)
	{
		ResultStatus resultStatus = new ResultStatus();
		try{
			
			transactionTemplate.execute(new TransactionCallbackWithoutResult() {
				public void doInTransactionWithoutResult(TransactionStatus status) {
					
					Long activityLevelId = finalvo.getActivityLevelId();
					
					String activityLevelValueStr = "";
					String locationIdStr = "";
					Long locationLevelId = 0l;
					Long locationValue = 0l;
					Long locationLevel = 0l;
					
					if(activityLevelId == 1l){
						activityLevelValueStr = finalvo.getLocationValue().toString().substring(0, 1);
						locationIdStr = finalvo.getLocationValue().toString().substring(1);
						locationLevelId = Long.parseLong(activityLevelValueStr);
						locationValue = Long.parseLong(locationIdStr);
								
						if(locationLevelId == 11){
							locationLevel = 6l;
						}
						else if(locationLevelId == 2l){
							locationLevel = 8l;
						}
					}
					else if(activityLevelId == 2l){
						activityLevelValueStr = finalvo.getLocationValue().toString().substring(0, 1);
						locationIdStr = finalvo.getLocationValue().toString().substring(1);
						locationLevelId = Long.parseLong(activityLevelValueStr);
						locationValue = Long.parseLong(locationIdStr);
						
						if(locationLevelId == 11){
							locationLevel = 7l;
						}
						else if(locationLevelId == 2l){
							locationLevel = 5l;
						}
						else if(locationLevelId == 3l){
							locationLevel = 9l;
						}
					}
					
					Long activityLocationInfoId = activityLocationInfoDAO.getActivityLocationInfoIdByLocationLevelAndLocationValue(locationLevel, locationValue);
					
					List<ActivityVO> voList = finalvo.getActivityVoList();
					
					if(voList != null && voList.size() > 0){
						for (ActivityVO activityVO : voList) {
							ActivityQuestionAnswer activityQuestionAnswer = new ActivityQuestionAnswer();
							
							activityQuestionAnswer.setActivityQuestionnaireId(activityVO.getQuestionId());
							activityQuestionAnswer.setActivityLocationInfoId(activityLocationInfoId);
							activityQuestionAnswer.setActivityOptionId(activityVO.getOptionId());
							activityQuestionAnswer.setCount(activityVO.getCount());
							activityQuestionAnswer.setOptionTxt(activityVO.getRemarks());
							activityQuestionAnswer.setIsDeleted("N");
							activityQuestionAnswer.setInsertedBy(finalvo.getId());
							activityQuestionAnswer.setUpdatedBy(finalvo.getId());
							activityQuestionAnswer.setInsertedTime(dateUtilService.getCurrentDateAndTime());
							activityQuestionAnswer.setUpdatedTime(dateUtilService.getCurrentDateAndTime());
							
							activityQuestionAnswerDAO.save(activityQuestionAnswer);
						}
					}
				}
			});
			
			resultStatus.setResultCode(0);
		}catch (Exception e) {
			resultStatus.setResultCode(1);
			LOG.error(" Exception Occured in saveActivityQuestionnaireDetails() method, Exception - ",e);
		}
		return resultStatus;
	}
	
}
