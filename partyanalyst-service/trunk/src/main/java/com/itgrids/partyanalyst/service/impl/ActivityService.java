package com.itgrids.partyanalyst.service.impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.log4j.Logger;

import com.itgrids.partyanalyst.dao.IActivityAttributeQuestionnaireInfoDAO;
import com.itgrids.partyanalyst.dao.IActivityDAO;
import com.itgrids.partyanalyst.dao.IActivityLevelDAO;
import com.itgrids.partyanalyst.dao.IActivityLocationInfoDAO;
import com.itgrids.partyanalyst.dao.IActivityQuestionAnswerDAO;
import com.itgrids.partyanalyst.dao.IActivityQuestionnaireDAO;
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
import com.itgrids.partyanalyst.dto.ActivityVO;
import com.itgrids.partyanalyst.dto.BasicVO;
import com.itgrids.partyanalyst.dto.IdNameVO;
import com.itgrids.partyanalyst.dto.LocationWiseBoothDetailsVO;
import com.itgrids.partyanalyst.dto.SearchAttributeVO;
import com.itgrids.partyanalyst.model.ActivityLevel;
import com.itgrids.partyanalyst.model.ActivityScope;
import com.itgrids.partyanalyst.model.ActivitySubType;
import com.itgrids.partyanalyst.model.Constituency;
import com.itgrids.partyanalyst.model.LocalElectionBody;
import com.itgrids.partyanalyst.model.Panchayat;
import com.itgrids.partyanalyst.model.Tehsil;
import com.itgrids.partyanalyst.service.IActivityService;
import com.itgrids.partyanalyst.service.ICadreCommitteeService;
import com.itgrids.partyanalyst.service.IRegionServiceData;
import com.itgrids.partyanalyst.utils.CommonMethodsUtilService;
import com.itgrids.partyanalyst.utils.IConstants;

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
	private IActivityQuestionAnswerDAO activityQuestionAnswerDAO;
	
	
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
				Map<String,ActivityVO> activityMap1 = null;
				for (Object[] activity : activitiesList) {
					Long areaId = 0L;
					if(extentionNo != null && !extentionNo.isEmpty()){
						String locationId = extentionNo+""+commonMethodsUtilService.getStringValueForObject(activity[0]);
						areaId = Long.valueOf(locationId.trim());
					}
					else
					{
						areaId = commonMethodsUtilService.getLongValueForObject(activity[0]);
					}
					ActivityVO aactivityVO = locationsMap.get(areaId);
					if(aactivityVO != null){
						Map<String,ActivityVO> activityMap = aactivityVO.getActivityMap();
						ActivityVO infoCellVO = null;
						if(activityMap == null || activityMap.size()==0)
						{
							String[] attributesArr = IConstants.ACTIVITY_REPORT_FIELDS;
							activityMap1 = new LinkedHashMap<String, ActivityVO>(0);
							if(attributesArr != null && attributesArr.length>0)
							{
								for(int i=0;i< attributesArr.length;i++){
									ActivityVO infoCellVO1 = new ActivityVO();
									infoCellVO1.setName(attributesArr[i].toString());
									infoCellVO1.setConductedCount(0L);
									infoCellVO1.setPercentage("0.00");
									infoCellVO1.setNotPlannedPerc("0.00");
									activityMap1.put(infoCellVO1.getName(), infoCellVO1);
								}
								aactivityVO.setActivityMap(activityMap1);
							}
							infoCellVO = activityMap1.get(type.trim());
						}else{
							infoCellVO = activityMap.get(type.trim());
						}
						//ActivityVO infoCellVO = activityMap.get(type.trim());
						Long totalCount = commonMethodsUtilService.getLongValueForObject(activity[3]);
						if(infoCellVO == null){
							infoCellVO = new ActivityVO();
						}
						else{
							totalCount = totalCount+(infoCellVO.getConductedCount() != null ? infoCellVO.getConductedCount():0L);
						}
						infoCellVO.setName(type.trim());
						infoCellVO.setConductedCount(totalCount);
						infoCellVO.setPercentage(totalCount.toString());
					}
				}
			}
			
			if(locationsMap != null && locationsMap.size()>0)
			{
				for (Long locationId : locationsMap.keySet()) {
					ActivityVO aactivityVO = locationsMap.get(locationId);
					if(aactivityVO != null)
					{
						Map<String,ActivityVO> activityMap = aactivityVO.getActivityMap();
						if(aactivityVO.getActivityVoList() == null || aactivityVO.getActivityVoList().size()== 0)
						{
							if(activityMap != null && activityMap.size()>0){
								if(activityMap != null && activityMap.size()>0){
									for (String typeStr  : activityMap.keySet()){
										ActivityVO infoCellVO = activityMap.get(typeStr);
										infoCellVO.setPercentage("0.00");
											aactivityVO.getActivityVoList().add(infoCellVO);
									}
								}
							}
						}else
						{
							ActivityVO infoCellVO = getMatchActivityVO(aactivityVO.getActivityVoList(), type.trim());
							Long totalCount = 0L;
							if(infoCellVO != null)
							{
								if(type.contains("%"))
								{
									String tempType = type.replace("%", "");
									ActivityVO tempInfoCellVO = getMatchActivityVO(aactivityVO.getActivityVoList(), tempType.trim());
									Long plannedCount = getMatchActivityVO(aactivityVO.getActivityVoList(), "PLANNED".trim()).getConductedCount();
									if(tempInfoCellVO != null)
									{
										Long count = tempInfoCellVO.getConductedCount();
										if(count != null && count.longValue()>0L){
											double perc = (count*100.0)/plannedCount;;
											String percentage = commonMethodsUtilService.roundTo2DigitsFloatValueAsString(Float.valueOf(String.valueOf(perc)));
											infoCellVO.setPercentage(percentage);
										}
									}
								}
								else
								{
									totalCount = totalCount+(infoCellVO.getConductedCount() != null ? infoCellVO.getConductedCount():0L);
									infoCellVO.setPercentage(totalCount.toString());
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
					BasicVO vo = new BasicVO();
					vo.setId(activityType.getActivitySubTypeId());
					vo.setName(activityType.getSubType());
					activitiesLsit.add(vo);
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
				if(searchAttributeVO.getSearchType().equalsIgnoreCase(IConstants.STATE)){
					List<BasicVO> areaWiseCountLsit = regionServiceDataImp.areaCountListByAreaIdsOnScope(searchAttributeVO);
					if(areaWiseCountLsit != null && areaWiseCountLsit.size()>0)
					{
						Long totalAreasCount = getTotalAreaCountByList(areaWiseCountLsit);
						returnVO.setTotalCount(totalAreasCount);
					}
				}
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
				
			List<Long> questionnaireIds = activityQuestionnaireDAO.getQuestionnaireIdsListByScopeId(searchAttributeVO.getAttributesIdsList().get(0));
			searchAttributeVO.setQuestionnaireIdsList(questionnaireIds);
			
			List<Object[]> questionnairesCount = activityQuestionAnswerDAO.getActivityQuestionnairesCountsByLocation(searchAttributeVO);
				
			if(searchAttributeVO.getSearchType() != null && searchAttributeVO.getConditionType().trim().contains("daywiseResult")){
				List<ActivityVO> dayWiseResultList = getActivityDayWiseCountsByLocation(searchAttributeVO);
				returnVO.getActivityVoList().addAll(dayWiseResultList);
				return returnVO;
			}
				
				//locationId, locationname, tdpcommittelevelId, count
				List<Object[]> plannedActivities = null;
				List<Object[]> infoCellconductedActivities = null;
				List<Object[]> ivrconductedActivities = null;
				//List<Object[]> notPlannedActivities = null;
				List<Object[]> infoCellNotPlannedActivities = null;
				List<Object[]> ivrNotPlannedActivities = null;
				
				Map<Long,ActivityVO> locationsMap = new LinkedHashMap<Long, ActivityVO>(0);
				if(searchAttributeVO.getSearchType().equalsIgnoreCase(IConstants.STATE)){
					searchAttributeVO.getLocationIdsList().add(searchAttributeVO.getLocationValue());
					
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
					
					segrigateResultsTypes(infoCellconductedActivities,locationsMap,"INFO CELL COVERED",null);
					segrigateResultsTypes(infoCellconductedActivities,locationsMap,"INFO CELL COVERED %",null);
					segrigateResultsTypes(infoCellNotPlannedActivities,locationsMap,"INFO CELL NOT PLANNED",null);
					
					segrigateResultsTypes(null,locationsMap,"WHATSAPP IMAGES COVERED",null);					
					segrigateResultsTypes(null,locationsMap,"WHATSAPP IMAGES COVERED %",null);	
					segrigateResultsTypes(null,locationsMap,"NO OF WHATSAPP IMAGES RECIEVED",null);	
					
					
					
				}
				else if(searchAttributeVO.getSearchType().equalsIgnoreCase(IConstants.DISTRICT)){
					
					List<Long> districtIds = districtDAO.getDistrictsInAState(searchAttributeVO.getTypeId());//AP/TS/ALL
					if(districtIds != null && districtIds.size()>0){
						searchAttributeVO.setLocationIdsList(districtIds);
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
					
					segrigateResultsTypes(infoCellconductedActivities,locationsMap,"INFO CELL COVERED",null);
					segrigateResultsTypes(infoCellconductedActivities,locationsMap,"INFO CELL COVERED %",null);
					segrigateResultsTypes(infoCellNotPlannedActivities,locationsMap,"INFO CELL NOT PLANNED",null);
					
					segrigateResultsTypes(null,locationsMap,"WHATSAPP IMAGES COVERED",null);					
					segrigateResultsTypes(null,locationsMap,"WHATSAPP IMAGES COVERED %",null);	
					segrigateResultsTypes(null,locationsMap,"NO OF WHATSAPP IMAGES RECIEVED",null);	
					}
				}
				else if(searchAttributeVO.getSearchType().equalsIgnoreCase(IConstants.CONSTITUENCY)){
					List<Long> constituencyList = constituencyDAO.getConstituenciesInADistrict(searchAttributeVO.getLocationId());
					if(constituencyList != null && constituencyList.size()>0)
					{
						searchAttributeVO.setLocationIdsList(constituencyList);
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
						
						segrigateResultsTypes(infoCellconductedActivities,locationsMap,"INFO CELL COVERED",null);
						segrigateResultsTypes(infoCellconductedActivities,locationsMap,"INFO CELL COVERED %",null);
						segrigateResultsTypes(infoCellNotPlannedActivities,locationsMap,"INFO CELL NOT PLANNED",null);
						
						segrigateResultsTypes(null,locationsMap,"WHATSAPP IMAGES COVERED",null);					
						segrigateResultsTypes(null,locationsMap,"WHATSAPP IMAGES COVERED %",null);	
						segrigateResultsTypes(null,locationsMap,"NO OF WHATSAPP IMAGES RECIEVED",null);
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
							
							segrigateResultsTypes(ivrconductedActivities,mandalMap,"IVR COVERED","2");
							segrigateResultsTypes(ivrconductedActivities,mandalMap,"IVR COVERED %","2");
							segrigateResultsTypes(ivrNotPlannedActivities,mandalMap,"IVR NOT PLANNED","2");
							
							segrigateResultsTypes(infoCellconductedActivities,mandalMap,"INFO CELL COVERED","2");
							segrigateResultsTypes(infoCellconductedActivities,mandalMap,"INFO CELL COVERED %","2");
							segrigateResultsTypes(infoCellNotPlannedActivities,mandalMap,"INFO CELL NOT PLANNED","2");
							
							segrigateResultsTypes(null,mandalMap,"WHATSAPP IMAGES COVERED","2");					
							segrigateResultsTypes(null,mandalMap,"WHATSAPP IMAGES COVERED %","2");	
							segrigateResultsTypes(null,mandalMap,"NO OF WHATSAPP IMAGES RECIEVED","2");
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
						
						segrigateResultsTypes(plannedActivities,MunciTownMap,"PLANNED","1");
						
						segrigateResultsTypes(ivrconductedActivities,MunciTownMap,"IVR COVERED","1");
						segrigateResultsTypes(ivrconductedActivities,MunciTownMap,"IVR COVERED %","1");
						segrigateResultsTypes(ivrNotPlannedActivities,MunciTownMap,"IVR NOT PLANNED","1");
						
						segrigateResultsTypes(infoCellconductedActivities,MunciTownMap,"INFO CELL COVERED","1");
						segrigateResultsTypes(infoCellconductedActivities,MunciTownMap,"INFO CELL COVERED %","1");
						segrigateResultsTypes(infoCellNotPlannedActivities,MunciTownMap,"INFO CELL NOT PLANNED","1");
						
						segrigateResultsTypes(null,MunciTownMap,"WHATSAPP IMAGES COVERED","1");					
						segrigateResultsTypes(null,MunciTownMap,"WHATSAPP IMAGES COVERED %","1");	
						segrigateResultsTypes(null,MunciTownMap,"NO OF WHATSAPP IMAGES RECIEVED","1");
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
							
							segrigateResultsTypes(plannedActivities,divisionMap,"PLANNED","3");
							
							segrigateResultsTypes(ivrconductedActivities,divisionMap,"IVR COVERED","3");
							segrigateResultsTypes(ivrconductedActivities,divisionMap,"IVR COVERED %","3");
							segrigateResultsTypes(ivrNotPlannedActivities,divisionMap,"IVR NOT PLANNED","3");
							
							segrigateResultsTypes(infoCellconductedActivities,divisionMap,"INFO CELL COVERED","3");
							segrigateResultsTypes(infoCellconductedActivities,divisionMap,"INFO CELL COVERED %","3");
							segrigateResultsTypes(infoCellNotPlannedActivities,divisionMap,"INFO CELL NOT PLANNED","3");
							
							segrigateResultsTypes(null,divisionMap,"WHATSAPP IMAGES COVERED","3");					
							segrigateResultsTypes(null,divisionMap,"WHATSAPP IMAGES COVERED %","3");	
							segrigateResultsTypes(null,divisionMap,"NO OF WHATSAPP IMAGES RECIEVED","3");
						
						}
						
						if(mandalMap != null && mandalMap.size()>0)
							locationsMap.putAll(mandalMap);
						else if(MunciTownMap != null && MunciTownMap.size()>0)
							locationsMap.putAll(MunciTownMap);
						else if(divisionMap != null && divisionMap.size()>0)
							locationsMap.putAll(divisionMap);
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
							
							segrigateResultsTypes(infoCellconductedActivities,locationsMap,"INFO CELL COVERED","2");
							segrigateResultsTypes(infoCellconductedActivities,locationsMap,"INFO CELL COVERED %","2");
							segrigateResultsTypes(infoCellNotPlannedActivities,locationsMap,"INFO CELL NOT PLANNED","2");
							
							segrigateResultsTypes(null,locationsMap,"WHATSAPP IMAGES COVERED","2");					
							segrigateResultsTypes(null,locationsMap,"WHATSAPP IMAGES COVERED %","2");	
							segrigateResultsTypes(null,locationsMap,"NO OF WHATSAPP IMAGES RECIEVED","2");
							}
							else if(locationTypeIds.contains(IConstants.VILLAGE_COMMITTEE_LEVEL_ID)){ // panchayat
								searchAttributeVO.getLocationTypeIdsList().clear();
								searchAttributeVO.getLocationTypeIdsList().add(IConstants.VILLAGE_COMMITTEE_LEVEL_ID);
								
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
							
							segrigateResultsTypes(infoCellconductedActivities,locationsMap,"INFO CELL COVERED","1");
							segrigateResultsTypes(infoCellconductedActivities,locationsMap,"INFO CELL COVERED %","1");
							segrigateResultsTypes(infoCellNotPlannedActivities,locationsMap,"INFO CELL NOT PLANNED","1");
							
							segrigateResultsTypes(null,locationsMap,"WHATSAPP IMAGES COVERED","1");					
							segrigateResultsTypes(null,locationsMap,"WHATSAPP IMAGES COVERED %","1");	
							segrigateResultsTypes(null,locationsMap,"NO OF WHATSAPP IMAGES RECIEVED","1");
							
							}
						}
					}
				}
				if(returnVO != null && locationsMap != null && locationsMap.size()>0)
				{
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
							else if(searchAttributeVO.getSearchType().equalsIgnoreCase(IConstants.MANDAL)){
								searchAttributeVO.setScopeId(5L);
								String mandalTownORDivisionId = searchAttributeVO.getScopeValue().toString().trim().substring(1);
								searchAttributeVO.setScopeValue(Long.valueOf(mandalTownORDivisionId));
								activityVO.setName(activityVO.getName()+" Mandal");
								List<BasicVO> areaWiseCountLsit = regionServiceDataImp.areaCountListByAreaIdsOnScope(searchAttributeVO);
								if(areaWiseCountLsit != null && areaWiseCountLsit.size()>0)
								{
									totalAreasCount = totalAreasCount+getTotalAreaCountByList(areaWiseCountLsit);
									activityVO.setTotalCount(totalAreasCount);
								}
								totalPlannedCount = activityVO.getPlannedCount();
								searchAttributeVO.setScopeId(7L);
								areaWiseCountLsit = regionServiceDataImp.areaCountListByAreaIdsOnScope(searchAttributeVO);
								if(areaWiseCountLsit != null && areaWiseCountLsit.size()>0)
								{
									totalAreasCount = totalAreasCount+getTotalAreaCountByList(areaWiseCountLsit);
									activityVO.setTotalCount(totalAreasCount);
								}
								
							}/*
							else if(searchAttributeVO.getSearchType().equalsIgnoreCase(IConstants.VILLAGE)){
								searchAttributeVO.setScopeId(6L);
								activityVO.setName(activityVO.getName()+" Village");
								List<BasicVO> areaWiseCountLsit = regionServiceDataImp.areaCountListByAreaIdsOnScope(searchAttributeVO);
								if(areaWiseCountLsit != null && areaWiseCountLsit.size()>0)
								{
									totalAreasCount = totalAreasCount+getTotalAreaCountByList(areaWiseCountLsit);
									activityVO.setTotalCount(totalAreasCount);
								}
								
								searchAttributeVO.setScopeId(8L);
								areaWiseCountLsit = regionServiceDataImp.areaCountListByAreaIdsOnScope(searchAttributeVO);
								if(areaWiseCountLsit != null && areaWiseCountLsit.size()>0)
								{
									totalAreasCount = totalAreasCount+getTotalAreaCountByList(areaWiseCountLsit);
									activityVO.setTotalCount(totalAreasCount);
								}
								
							}*/
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
								if(activityVO.getPlannedCount() != null && activityVO.getPlannedCount().longValue()>0L)
								{
									double perc = (activityVO.getPlannedCount() * 100.0)/totalPlannedCount;
									String percentage = commonMethodsUtilService.roundTo2DigitsFloatValueAsString(Float.valueOf(String.valueOf(perc)));
									activityVO.setPercentage(percentage);
								}
								if(activityVO.getNotPlannedCount() != null && activityVO.getNotPlannedCount().longValue()>0L)
								{
									double perc1 = (activityVO.getNotPlannedCount() * 100.0)/totalPlannedCount;
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
			
			datesMap = commonMethodsUtilService.getDatesWiseCounts(searchAttributeVO.getStartDate(), searchAttributeVO.getEndDate(), "Day");
			searchAttributeVO.getLocationIdsList().add(searchAttributeVO.getLocationId());
			List<Object[]> plannedActivities = null;
			List<Object[]> infoCellconductedActivities = null;
			List<Object[]> ivrconductedActivities = null;
			
			List<Object[]> infoCellNotPlannedActivities = null;
			List<Object[]> ivrNotPlannedActivities = null;
			
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
			
			if(datesMap != null && datesMap.size()>0)
			{
				for (String dateStr : datesMap.keySet()) {
					ActivityVO aactivityVO =datesMap.get(dateStr);
					if(aactivityVO != null)
					{
						aactivityVO.setTotalCount(0L);
						String[] attributesArr = IConstants.ACTIVITY_REPORT_FIELDS;
						Map<String,ActivityVO> activityMap1 = new LinkedHashMap<String, ActivityVO>(0);
						if(attributesArr != null && attributesArr.length>0)
						{
							for(int i=0;i< attributesArr.length;i++){
								ActivityVO infoCellVO1 = new ActivityVO();
								infoCellVO1.setName(attributesArr[i].toString());
								infoCellVO1.setConductedCount(0L);
								infoCellVO1.setPercentage("0.00");
								infoCellVO1.setNotPlannedPerc("0.00");
								activityMap1.put(infoCellVO1.getName(), infoCellVO1);
							}
						}
						SimpleDateFormat foramt = new SimpleDateFormat("yyyy-MM-dd");
						searchAttributeVO.setStartDate(foramt.parse(dateStr));
						searchAttributeVO.setEndDate(foramt.parse(dateStr));
						
						aactivityVO.setActivityMap(activityMap1);
						
						if(plannedActivities != null && plannedActivities.size()>0)
						{
							for (Object[] activity : plannedActivities) {
								String dateStr1 = commonMethodsUtilService.getStringValueForObject(activity[3]);
								if(dateStr.trim().equalsIgnoreCase(dateStr1.trim())){
									aactivityVO.setPlannedCount(commonMethodsUtilService.getLongValueForObject(activity[4]));
								}
							}
						}
					}
				}
			}
			
				buildResult(infoCellconductedActivities,datesMap,"INFO CELL PLANNED",null);
				buildResult(ivrconductedActivities,datesMap,"IVR PLANNED",null);
				buildResult(infoCellNotPlannedActivities,datesMap,"INFO CELL NOT PLANNED",null);
				buildResult(ivrNotPlannedActivities,datesMap,"INFO CELL NOT PLANNED",null);
				
			//0.locationId,1.locationName,2.locationLevelId,3.date,4.count
			//List<Object[]> dayWiseList = activityLocationInfoDAO.getActivityDayWiseCountsByLocation(searchAttributeVO);
			
			
			/*
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
			
				buildResult(infoCellconductedActivities,datesMap,"INFO CELL PLANNED",null);
				buildResult(ivrconductedActivities,datesMap,"IVR PLANNED",null);
				buildResult(infoCellNotPlannedActivities,datesMap,"INFO CELL NOT PLANNED",null);
				buildResult(ivrNotPlannedActivities,datesMap,"INFO CELL NOT PLANNED",null);
				*/
			/*if(dayWiseList != null && dayWiseList.size() > 0){
				for (Object[] objects : dayWiseList) {
					ActivityVO mapvo = new ActivityVO();
					
					String date = objects[3] != null ? objects[3].toString():"";
					if(date != null && date.length() > 0){
						mapvo = datesMap.get(date);
						if(mapvo != null){
							mapvo.setId((Long) (objects[0] != null ? objects[0]:0l));
							mapvo.setAttendedCount((Long) (objects[4] != null ? objects[4]:0l));
						}
					}
					finalList.add(mapvo);
				}
			}*/
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
					String dateStr = commonMethodsUtilService.getStringValueForObject(activity[3]);
					ActivityVO aactivityVO = datesMap.get(dateStr);
					if(aactivityVO != null){
						Map<String,ActivityVO> activityMap = aactivityVO.getActivityMap();
						ActivityVO infoCellVO = null;
						if(activityMap == null || activityMap.size()==0)
						{
							String[] attributesArr = IConstants.ACTIVITY_REPORT_FIELDS;
							activityMap1 = new LinkedHashMap<String, ActivityVO>(0);
							if(attributesArr != null && attributesArr.length>0)
							{
								for(int i=0;i< attributesArr.length;i++){
									ActivityVO infoCellVO1 = new ActivityVO();
									infoCellVO1.setName(attributesArr[i].toString());
									infoCellVO1.setConductedCount(0L);
									infoCellVO1.setPercentage("0.00");
									infoCellVO1.setNotPlannedPerc("0.00");
									activityMap1.put(infoCellVO1.getName(), infoCellVO1);
								}
								aactivityVO.setActivityMap(activityMap1);
							}
							infoCellVO = activityMap1.get(type.trim());
						}else{
							infoCellVO = activityMap.get(type.trim());
						}
						//ActivityVO infoCellVO = activityMap.get(type.trim());
						Long totalCount = commonMethodsUtilService.getLongValueForObject(activity[4]);
						if(infoCellVO == null){
							infoCellVO = new ActivityVO();
						}
						else{
							totalCount = totalCount+(infoCellVO.getConductedCount() != null ? infoCellVO.getConductedCount():0L);
						}
						infoCellVO.setName(type.trim());
						infoCellVO.setConductedCount(totalCount);
						if(type != null && type.trim().equalsIgnoreCase("INFO CELL PLANNED")){
							aactivityVO.setTotalCount(infoCellVO.getConductedCount());
						}
					}
				}
			}
			
			if(datesMap != null && datesMap.size()>0)
			{
				for (String dateStr : datesMap.keySet()) {
					ActivityVO aactivityVO = datesMap.get(dateStr);
					if(aactivityVO != null)
					{
						Map<String,ActivityVO> activityMap = aactivityVO.getActivityMap();
						if(aactivityVO.getActivityVoList() == null || aactivityVO.getActivityVoList().size()== 0)
						{
							if(activityMap != null && activityMap.size()>0){
								if(activityMap != null && activityMap.size()>0){
									for (String typeStr  : activityMap.keySet()){
										ActivityVO infoCellVO = activityMap.get(typeStr);
										infoCellVO.setPercentage("0.00");
											aactivityVO.getActivityVoList().add(infoCellVO);
									}
								}
							}
						}else
						{
							ActivityVO infoCellVO = getMatchActivityVO(aactivityVO.getActivityVoList(), type.trim());
							Long totalCount = 0L;
							if(infoCellVO != null)
							{
								totalCount = totalCount+(infoCellVO.getConductedCount() != null ? infoCellVO.getConductedCount():0L);
								infoCellVO.setConductedCount(totalCount);
							}
						}
						
					}
				}
			}
		} catch (Exception e) {
			 LOG.error("Exception Occured in segrigateFirstResult() method, Exception - ",e);
		}
	}
	
}
