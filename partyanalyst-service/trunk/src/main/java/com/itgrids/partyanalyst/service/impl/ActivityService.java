package com.itgrids.partyanalyst.service.impl;

import java.io.File;
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
import java.util.TimeZone;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.jfree.util.Log;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionCallback;
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
import com.itgrids.partyanalyst.dao.IActivityScopeRequiredAttributesDAO;
import com.itgrids.partyanalyst.dao.IActivitySubTypeDAO;
import com.itgrids.partyanalyst.dao.IActivityTabRequestBackupDAO;
import com.itgrids.partyanalyst.dao.IActivityTabUserDAO;
import com.itgrids.partyanalyst.dao.IActivityTabUserLocationDAO;
import com.itgrids.partyanalyst.dao.IActivityTeamLocationDAO;
import com.itgrids.partyanalyst.dao.IActivityTeamMemberDAO;
import com.itgrids.partyanalyst.dao.IActivityTypeDAO;
import com.itgrids.partyanalyst.dao.IAssemblyLocalElectionBodyDAO;
import com.itgrids.partyanalyst.dao.IAssemblyLocalElectionBodyWardDAO;
import com.itgrids.partyanalyst.dao.IAttendenceQuestionAnswerDAO;
import com.itgrids.partyanalyst.dao.IBoothConstituencyElectionDAO;
import com.itgrids.partyanalyst.dao.ICadreSurveyUserDAO;
import com.itgrids.partyanalyst.dao.IConstituencyDAO;
import com.itgrids.partyanalyst.dao.IDelimitationConstituencyAssemblyDetailsDAO;
import com.itgrids.partyanalyst.dao.IDelimitationConstituencyDAO;
import com.itgrids.partyanalyst.dao.IDelimitationConstituencyMandalDAO;
import com.itgrids.partyanalyst.dao.IDistrictDAO;
import com.itgrids.partyanalyst.dao.ILocalElectionBodyDAO;
import com.itgrids.partyanalyst.dao.ILocationInfoDAO;
import com.itgrids.partyanalyst.dao.IPanchayatDAO;
import com.itgrids.partyanalyst.dao.IStateDAO;
import com.itgrids.partyanalyst.dao.ITabDetailsDAO;
import com.itgrids.partyanalyst.dao.ITdpCadreDAO;
import com.itgrids.partyanalyst.dao.ITehsilDAO;
import com.itgrids.partyanalyst.dao.IUserActivityScopeDAO;
import com.itgrids.partyanalyst.dao.IUserAddressDAO;
import com.itgrids.partyanalyst.dao.IUserDAO;
import com.itgrids.partyanalyst.dao.hibernate.BoothDAO;
import com.itgrids.partyanalyst.dto.ActivityDocumentVO;
import com.itgrids.partyanalyst.dto.ActivityLocationVO;
import com.itgrids.partyanalyst.dto.ActivityLoginVO;
import com.itgrids.partyanalyst.dto.ActivityMainVO;
import com.itgrids.partyanalyst.dto.ActivityOptionVO;
import com.itgrids.partyanalyst.dto.ActivityQuestionVO;
import com.itgrids.partyanalyst.dto.ActivityQuestionnairOptionVO;
import com.itgrids.partyanalyst.dto.ActivityQuestionnairVO;
import com.itgrids.partyanalyst.dto.ActivityReqAttributesVO;
import com.itgrids.partyanalyst.dto.ActivityScopeVO;
import com.itgrids.partyanalyst.dto.ActivityVO;
import com.itgrids.partyanalyst.dto.ActivityWSVO;
import com.itgrids.partyanalyst.dto.BasicVO;
import com.itgrids.partyanalyst.dto.EventFileUploadVO;
import com.itgrids.partyanalyst.dto.IdNameVO;
import com.itgrids.partyanalyst.dto.LocationWiseBoothDetailsVO;
import com.itgrids.partyanalyst.dto.ResultCodeMapper;
import com.itgrids.partyanalyst.dto.ResultStatus;
import com.itgrids.partyanalyst.dto.SearchAttributeVO;
import com.itgrids.partyanalyst.dto.TabDetailsVO;
import com.itgrids.partyanalyst.dto.TdpCadreWSVO;
import com.itgrids.partyanalyst.model.Activity;
import com.itgrids.partyanalyst.model.ActivityDocument;
import com.itgrids.partyanalyst.model.ActivityInfoDocument;
import com.itgrids.partyanalyst.model.ActivityLevel;
import com.itgrids.partyanalyst.model.ActivityLocationInfo;
import com.itgrids.partyanalyst.model.ActivityQuestionAnswer;
import com.itgrids.partyanalyst.model.ActivityScope;
import com.itgrids.partyanalyst.model.ActivitySubType;
import com.itgrids.partyanalyst.model.ActivityTabRequestBackup;
import com.itgrids.partyanalyst.model.ActivityTabUser;
import com.itgrids.partyanalyst.model.AttendenceQuestionAnswer;
import com.itgrids.partyanalyst.model.Constituency;
import com.itgrids.partyanalyst.model.District;
import com.itgrids.partyanalyst.model.LocalElectionBody;
import com.itgrids.partyanalyst.model.Panchayat;
import com.itgrids.partyanalyst.model.TabDetails;
import com.itgrids.partyanalyst.model.Tehsil;
import com.itgrids.partyanalyst.model.UserAddress;
import com.itgrids.partyanalyst.service.IActivityService;
import com.itgrids.partyanalyst.service.ICadreCommitteeService;
import com.itgrids.partyanalyst.service.IRegionServiceData;
import com.itgrids.partyanalyst.utils.CommonMethodsUtilService;
import com.itgrids.partyanalyst.utils.DateUtilService;
import com.itgrids.partyanalyst.utils.IConstants;
import com.itgrids.partyanalyst.utils.ImageAndStringConverter;
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
	private IActivityTeamMemberDAO activityTeamMemberDAO;
	private IActivityTeamLocationDAO activityTeamLocationDAO;
	private BoothDAO boothDAO;
	private IUserActivityScopeDAO userActivityScopeDAO;
	private IUserDAO  				userDAO;
	private ICadreSurveyUserDAO		cadreSurveyUserDAO;
	private IActivityScopeRequiredAttributesDAO activityScopeRequiredAttributesDAO;
	private ITabDetailsDAO 			tabDetailsDAO;
	private IAttendenceQuestionAnswerDAO 	attendenceQuestionAnswerDAO;
	private IActivityTabUserLocationDAO activityTabUserLocationDAO;
	private IActivityTabUserDAO activityTabUserDAO;
	private IActivityTabRequestBackupDAO activityTabRequestBackupDAO;
	private ITdpCadreDAO tdpCadreDAO;
	
	
	public ITdpCadreDAO getTdpCadreDAO() {
		return tdpCadreDAO;
	}
	public void setTdpCadreDAO(ITdpCadreDAO tdpCadreDAO) {
		this.tdpCadreDAO = tdpCadreDAO;
	}
	public IActivityTabRequestBackupDAO getActivityTabRequestBackupDAO() {
		return activityTabRequestBackupDAO;
	}
	public void setActivityTabRequestBackupDAO(
			IActivityTabRequestBackupDAO activityTabRequestBackupDAO) {
		this.activityTabRequestBackupDAO = activityTabRequestBackupDAO;
	}
	public IActivityTabUserDAO getActivityTabUserDAO() {
		return activityTabUserDAO;
	}
	public void setActivityTabUserDAO(IActivityTabUserDAO activityTabUserDAO) {
		this.activityTabUserDAO = activityTabUserDAO;
	}
	public IActivityTabUserLocationDAO getActivityTabUserLocationDAO() {
		return activityTabUserLocationDAO;
	}
	public void setActivityTabUserLocationDAO(
			IActivityTabUserLocationDAO activityTabUserLocationDAO) {
		this.activityTabUserLocationDAO = activityTabUserLocationDAO;
	}
	public IAttendenceQuestionAnswerDAO getAttendenceQuestionAnswerDAO() {
		return attendenceQuestionAnswerDAO;
	}
	public void setAttendenceQuestionAnswerDAO(
			IAttendenceQuestionAnswerDAO attendenceQuestionAnswerDAO) {
		this.attendenceQuestionAnswerDAO = attendenceQuestionAnswerDAO;
	}
	public ITabDetailsDAO getTabDetailsDAO() {
		return tabDetailsDAO;
	}
	public void setTabDetailsDAO(ITabDetailsDAO tabDetailsDAO) {
		this.tabDetailsDAO = tabDetailsDAO;
	}
	public IActivityScopeRequiredAttributesDAO getActivityScopeRequiredAttributesDAO() {
		return activityScopeRequiredAttributesDAO;
	}
	public void setActivityScopeRequiredAttributesDAO(
			IActivityScopeRequiredAttributesDAO activityScopeRequiredAttributesDAO) {
		this.activityScopeRequiredAttributesDAO = activityScopeRequiredAttributesDAO;
	}
	public ICadreSurveyUserDAO getCadreSurveyUserDAO() {
		return cadreSurveyUserDAO;
	}
	public void setCadreSurveyUserDAO(ICadreSurveyUserDAO cadreSurveyUserDAO) {
		this.cadreSurveyUserDAO = cadreSurveyUserDAO;
	}
	public IUserDAO getUserDAO() {
		return userDAO;
	}
	public void setUserDAO(IUserDAO userDAO) {
		this.userDAO = userDAO;
	}
	public IUserActivityScopeDAO getUserActivityScopeDAO() {
		return userActivityScopeDAO;
	}
	public void setUserActivityScopeDAO(IUserActivityScopeDAO userActivityScopeDAO) {
		this.userActivityScopeDAO = userActivityScopeDAO;
	}
	public BoothDAO getBoothDAO() {
		return boothDAO;
	}
	public void setBoothDAO(BoothDAO boothDAO) {
		this.boothDAO = boothDAO;
	}
	public IActivityTeamMemberDAO getActivityTeamMemberDAO() {
		return activityTeamMemberDAO;
	}
	public void setActivityTeamMemberDAO(
			IActivityTeamMemberDAO activityTeamMemberDAO) {
		this.activityTeamMemberDAO = activityTeamMemberDAO;
	}
	public IActivityTeamLocationDAO getActivityTeamLocationDAO() {
		return activityTeamLocationDAO;
	}
	public void setActivityTeamLocationDAO(
			IActivityTeamLocationDAO activityTeamLocationDAO) {
		this.activityTeamLocationDAO = activityTeamLocationDAO;
	}
	public void setUserAddressDAO(IUserAddressDAO userAddressDAO) {
		this.userAddressDAO = userAddressDAO;
	}
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
	//1111
	
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
							//searchAttributeVO.getLocationTypeIdsList().add(9L);
						}
						else if(activityLevelId.longValue() == 3L)
						{
							searchAttributeVO.getLocationTypeIdsList().add(3L);
						}
						else if(activityLevelId.longValue() == 4L)
						{
							searchAttributeVO.getLocationTypeIdsList().add(4L);
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
				
				List<LocationWiseBoothDetailsVO>  locationsList = new ArrayList<LocationWiseBoothDetailsVO>(0);
				
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
					
					List<Long> districtIds = null;
					if(searchAttributeVO.getTeamSearchType().trim().equalsIgnoreCase("organizersWiseId") && searchAttributeVO.getRadioSearch().equalsIgnoreCase("location")){
						//List<Long> ids = new ArrayList<Long>(0);
						//ids.add(searchAttributeVO.getTeamLeaderId());
						districtIds = activityTeamLocationDAO.getAssignedLocationsForTeamMembers(searchAttributeVO.getTeamLeaderId());
					}
					else
						districtIds = districtDAO.getDistrictsInAState(searchAttributeVO.getTypeId());//AP/TS/ALL
					
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
				else if(searchAttributeVO.getSearchType().equalsIgnoreCase(IConstants.CONSTITUENCY) && searchAttributeVO.getRadioSearch().equalsIgnoreCase("location")){
					
					List<Long> constituencyList = null;
					if(searchAttributeVO.getTeamSearchType().trim().equalsIgnoreCase("organizersWiseId")){
						//List<Long> ids = new ArrayList<Long>(0);
						//ids.add(searchAttributeVO.getId());
						//constituencyList = constituencyDAO.getConstituencyIdsByStateIdForAElectionType(searchAttributeVO.getTypeId(),2l);
						if(searchAttributeVO.getTeamLeaderId().longValue() != 0)
							constituencyList = activityTeamLocationDAO.getAssignedLocationsForTeamMembersByTeamLeaderId(searchAttributeVO.getTeamLeaderId());
						else if(searchAttributeVO.getTeamMemberId().longValue() != 0)
							constituencyList = activityTeamLocationDAO.getAssignedConstituenciesForTeamMembers(searchAttributeVO.getTeamMemberId());
					}
					else
						constituencyList = constituencyDAO.getConstituenciesInADistrict(searchAttributeVO.getLocationId());//AP/TS/ALL
					
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
						locationsList.addAll(mandalMunciDivisionIdsList);
						List<Long> mandalsIdsList = new ArrayList<Long>(0);
						List<Long> munciORTownORCorpIdsList = new ArrayList<Long>(0);
						List<Long> divisionIdsList = new ArrayList<Long>(0);
				
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
					}
				}
				else if(searchAttributeVO.getSearchType().equalsIgnoreCase(IConstants.VILLAGE)){
				
					List<LocationWiseBoothDetailsVO>  villagesORWardsList =  cadreCommitteeService.getPanchayatWardByMandalId(searchAttributeVO.getLocationId().toString());
					List<Long> locationIdsList = new ArrayList<Long>(0);
					
					if(villagesORWardsList != null && villagesORWardsList.size()>0)
					{
						locationsList.addAll(villagesORWardsList);
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
							
							if(locationTypeIds.contains(IConstants.WARD_COMMITTEE_LEVEL_ID))
							{//ward
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
							else if(locationTypeIds.contains(IConstants.VILLAGE_COMMITTEE_LEVEL_ID))
							{ // panchayat
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
					
					if(locationsMap != null && locationsMap.size()>0)
	                {
	                  Set<Long> ids = locationsMap.keySet();
	                  if(locationsList != null && locationsList.size()>0)
	                  	{
	                	  for (LocationWiseBoothDetailsVO vo : locationsList) 
	                	  {
							if(!(ids.contains(vo.getLocationId())))
		                	  {
		                      ActivityVO Vo = new ActivityVO();
		                      Vo .setId(vo.getLocationId());
		                      Vo .setName(vo.getLocationName());
		                      locationsMap.put(vo.getLocationId(), Vo);
		                    }
						}
	                  }
	                }
					
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
				Collections.sort(returnVO.getActivityVoList());				
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
			
			if(searchAttributeVO.getTeamSearchType().equalsIgnoreCase("organizersWiseId")){
				
				Map<Long,ActivityVO> leadersMap = new LinkedHashMap<Long, ActivityVO>();
				Map<Long,ActivityVO> membersMap = new LinkedHashMap<Long, ActivityVO>();
				List<Long> leadersIds = new ArrayList<Long>();
				List<ActivityVO> allLctnsList = returnVO.getActivityVoList();
				if(searchAttributeVO.getAttributesIdsList() != null && searchAttributeVO.getAttributesIdsList().size() > 0){
					if(searchAttributeVO.getTeamLeaderId().longValue() == 0 && searchAttributeVO.getTeamMemberId().longValue() == 0){
						
						List<Object[]> list = activityTeamMemberDAO.getTeamLeadersByActivityScope(searchAttributeVO.getAttributesIdsList());
						if(list != null && list.size() > 0){
							for (Object[] obj : list) {
								ActivityVO vo = new ActivityVO();
								
								Long leaderId = obj[0] != null ? (Long) obj[0]:0l;
								String leaderName = obj[1] != null ? obj[1].toString():"";
								leadersIds.add(leaderId);
								
								List<Long> assignedLcnts = activityTeamLocationDAO.getAssignedLocationsForTeamMembers(leaderId);
								
								vo.setId(leaderId);
								vo.setName(leaderName+" (Gen.Secretary) ");
								vo.setLocationIds(assignedLcnts);
								
								leadersMap.put(leaderId, vo);
							}
						}
						
						List<ActivityVO> leadersList = new ArrayList<ActivityVO> (leadersMap.values());
						//List<ActivityVO> leadersList = (List<ActivityVO>) leadersMap.values();
						if(leadersList != null && leadersList.size() > 0){
							for (ActivityVO activityvo : leadersList) {
								List<Long> locIds = activityvo.getLocationIds();
								for (ActivityVO vo : allLctnsList) {
									Long id = vo.getId();
									if(locIds.contains(id)){
										if(activityvo.getImagesCount() != null)
											activityvo.setImagesCount(activityvo.getImagesCount()+vo.getImagesCount());
										else
											activityvo.setImagesCount(vo.getImagesCount());
										if(activityvo.getInfoCellNotPlanned() != null)
											activityvo.setInfoCellNotPlanned(activityvo.getInfoCellNotPlanned()+vo.getInfoCellNotPlanned());
										else
											activityvo.setInfoCellNotPlanned(vo.getInfoCellNotPlanned());
										if(activityvo.getInfoCellTotal() != null)
											activityvo.setInfoCellTotal(activityvo.getInfoCellTotal()+vo.getInfoCellTotal());
										else
											activityvo.setInfoCellTotal(vo.getInfoCellTotal());
										if(activityvo.getInfoCellcovered() != null)
											activityvo.setInfoCellcovered(activityvo.getInfoCellcovered()+vo.getInfoCellcovered());
										else
											activityvo.setInfoCellcovered(vo.getInfoCellcovered());
										if(activityvo.getIvrNotPlanned() != null)
											activityvo.setIvrNotPlanned(activityvo.getIvrNotPlanned()+vo.getIvrNotPlanned());
										else
											activityvo.setIvrNotPlanned(vo.getIvrNotPlanned());
										if(activityvo.getIvrTotal() != null)
											activityvo.setIvrTotal(activityvo.getIvrTotal()+vo.getIvrTotal());
										else
											activityvo.setIvrTotal(vo.getIvrTotal());
										if(activityvo.getIvrcovered() != null)
											activityvo.setIvrcovered(activityvo.getIvrcovered()+vo.getIvrcovered());
										else
											activityvo.setIvrcovered(vo.getIvrcovered());
										if(activityvo.getPlannedCount() != null)
											activityvo.setPlannedCount(activityvo.getPlannedCount()+vo.getPlannedCount());
										else
											activityvo.setPlannedCount(vo.getPlannedCount());
										if(activityvo.getTotalCount() != null)
											activityvo.setTotalCount(activityvo.getTotalCount()+vo.getTotalCount());
										else
											activityvo.setTotalCount(vo.getTotalCount());
										if(activityvo.getWhatsAppCovered() != null)
											activityvo.setWhatsAppCovered(activityvo.getWhatsAppCovered()+vo.getWhatsAppCovered());
										else
											activityvo.setWhatsAppCovered(vo.getWhatsAppCovered());
									}
								}
								if(activityvo.getPlannedCount() != null && activityvo.getPlannedCount().longValue() > 0L && activityvo.getIvrcovered().longValue() > 0L)
								{
									double perc = (activityvo.getIvrcovered() * 100.0)/activityvo.getPlannedCount();
									String percentage = commonMethodsUtilService.roundTo2DigitsFloatValueAsString(Float.valueOf(String.valueOf(perc)));
									activityvo.setIvrcoveredPerc(percentage);   //ivr covered percentage
								}
								if(activityvo.getTotalCount() != null && activityvo.getTotalCount().longValue() > 0l && activityvo.getIvrTotal() != null && activityvo.getIvrTotal().longValue() > 0l){
									double perc = (activityvo.getIvrTotal() * 100.0)/activityvo.getTotalCount();
									String percentage = commonMethodsUtilService.roundTo2DigitsFloatValueAsString(Float.valueOf(String.valueOf(perc)));
									activityvo.setIvrTotalPerc(percentage);
								}
								if(activityvo.getTotalCount() != null && activityvo.getTotalCount().longValue() > 0l && activityvo.getInfoCellTotal() != null && activityvo.getInfoCellTotal().longValue() > 0l){
									double perc = (activityvo.getInfoCellTotal() * 100.0)/activityvo.getTotalCount();
									String percentage = commonMethodsUtilService.roundTo2DigitsFloatValueAsString(Float.valueOf(String.valueOf(perc)));
									activityvo.setInfoCellTotalPerc(percentage);
								}
								if(activityvo.getPlannedCount() != null && activityvo.getPlannedCount().longValue() > 0l && activityvo.getInfoCellcovered() != null && activityvo.getInfoCellcovered().longValue() > 0l){
									double perc = (activityvo.getInfoCellcovered() * 100.0)/activityvo.getPlannedCount();
									String percentage = commonMethodsUtilService.roundTo2DigitsFloatValueAsString(Float.valueOf(String.valueOf(perc)));
									activityvo.setInfoCellcoveredPerc(percentage);
								}
								if(activityvo.getInfoCellTotal() != null && activityvo.getInfoCellTotal().longValue() > 0l && activityvo.getWhatsAppCovered() != null && activityvo.getWhatsAppCovered().longValue() > 0l){
									double perc = (activityvo.getWhatsAppCovered() * 100.0)/activityvo.getInfoCellTotal();
									String percentage = commonMethodsUtilService.roundTo2DigitsFloatValueAsString(Float.valueOf(String.valueOf(perc)));
									activityvo.setWhatsAppCoveredPerc(percentage);
								}
								
								if(activityvo.getPercentage() == null)
									activityvo.setPercentage("0.00");
								if(activityvo.getIvrcoveredPerc()  == null)
									activityvo.setIvrcoveredPerc("0.00");
								if(activityvo.getIvrTotalPerc() == null)
									activityvo.setIvrTotalPerc("0.00");
								if(activityvo.getInfoCellTotalPerc() == null)
									activityvo.setInfoCellTotalPerc("0.00");
								if(activityvo.getInfoCellcoveredPerc() == null)
									activityvo.setInfoCellcoveredPerc("0.00");
								if(activityvo.getWhatsAppCoveredPerc() == null)
									activityvo.setWhatsAppCoveredPerc("0.00");
							}
						}
						
						returnVO.getActivityVoList().clear();
						returnVO.setActivityVoList(leadersList);
					}
					/*else if (searchAttributeVO.getTeamLeaderId().longValue() > 0 && searchAttributeVO.getDistrictId().longValue() == 0){ 
						
						List<Long> assigLctnIds = new ArrayList<Long>();
						ActivityVO vo1 = new ActivityVO();
						List<ActivityVO> locationsList = new ArrayList<ActivityVO>();
						
						List<Object[]> list = activityTeamLocationDAO.getTeamLeaderDetailsAndAssignedLocationsByLeaderId(searchAttributeVO.getTeamLeaderId());
						if(list != null && list.size() > 0){
							for (Object[] obj : list) {
								
								vo1.setId(searchAttributeVO.getTeamLeaderId());
								vo1.setName(obj[0] != null ? obj[0].toString():"");
								Long lctnId = obj[1] != null ? (Long) obj[1]:0l;
								assigLctnIds.add(lctnId);
							}
							vo1.setLocationIds(assigLctnIds);
						}
						leadersMap.put(searchAttributeVO.getTeamLeaderId(), vo1);
						
						if(allLctnsList != null && allLctnsList.size() > 0){
							for (int i = 0; i < allLctnsList.size(); i++) {
								ActivityVO vo = allLctnsList.get(i);
								Long id = vo.getId();
								if(assigLctnIds.contains(id)){
									locationsList.add(vo);
								}
							}
							returnVO.getActivityVoList().clear();
							returnVO.setActivityVoList(locationsList);
						}
					}*/
					else if(searchAttributeVO.getTeamLeaderId().longValue() > 0 && searchAttributeVO.getTeamMemberId().longValue() == 0){
						
						if(searchAttributeVO.getTeamLeaderId().longValue() > 0){
							List<Object[]> teamMembers = activityTeamMemberDAO.getTeamMembersByTeamLeaderId(searchAttributeVO.getTeamLeaderId());
							if(teamMembers != null && teamMembers.size() > 0){
								for (Object[] obj : teamMembers) {
									ActivityVO vo = new ActivityVO();
									
									Long memberId = (Long) (obj[0] != null ? obj[0]:0l);
									List<Long> constituencyIds = activityTeamLocationDAO.getAssignedConstituenciesForTeamMembers(memberId);
									
									vo.setId(memberId);
									vo.setName(obj[1] != null ? obj[1].toString()+" (Org.Secretary / Secretary) ":"");
									vo.setLocationIds(constituencyIds);
									
									membersMap.put(memberId, vo);
								}
							}
							
							List<ActivityVO> membersList = new ArrayList<ActivityVO> (membersMap.values());
							
							if(membersList != null && membersList.size() > 0){
								for (ActivityVO activtyvo : membersList) {
									List<Long> locationIds = activtyvo.getLocationIds();
									for (ActivityVO vo : allLctnsList) {
										Long id = vo.getId();
										if(locationIds.contains(id)){
											if(activtyvo.getImagesCount() != null)
												activtyvo.setImagesCount(activtyvo.getImagesCount()+vo.getImagesCount());
											else
												activtyvo.setImagesCount(vo.getImagesCount());
											if(activtyvo.getInfoCellNotPlanned() != null)
												activtyvo.setInfoCellNotPlanned(activtyvo.getInfoCellNotPlanned()+vo.getInfoCellNotPlanned());
											else
												activtyvo.setInfoCellNotPlanned(vo.getInfoCellNotPlanned());
											if(activtyvo.getInfoCellTotal() != null)
												activtyvo.setInfoCellTotal(activtyvo.getInfoCellTotal()+vo.getInfoCellTotal());
											else
												activtyvo.setInfoCellTotal(vo.getInfoCellTotal());
											if(activtyvo.getInfoCellcovered() != null)
												activtyvo.setInfoCellcovered(activtyvo.getInfoCellcovered()+vo.getInfoCellcovered());
											else
												activtyvo.setInfoCellcovered(vo.getInfoCellcovered());
											if(activtyvo.getIvrNotPlanned() != null)
												activtyvo.setIvrNotPlanned(activtyvo.getIvrNotPlanned()+vo.getIvrNotPlanned());
											else
												activtyvo.setIvrNotPlanned(vo.getIvrNotPlanned());
											if(activtyvo.getIvrTotal() != null)
												activtyvo.setIvrTotal(activtyvo.getIvrTotal()+vo.getIvrTotal());
											else
												activtyvo.setIvrTotal(vo.getIvrTotal());
											if(activtyvo.getIvrcovered() != null)
												activtyvo.setIvrcovered(activtyvo.getIvrcovered()+vo.getIvrcovered());
											else
												activtyvo.setIvrcovered(vo.getIvrcovered());
											if(activtyvo.getPlannedCount() != null)
												activtyvo.setPlannedCount(activtyvo.getPlannedCount()+vo.getPlannedCount());
											else
												activtyvo.setPlannedCount(vo.getPlannedCount());
											if(activtyvo.getTotalCount() != null)
												activtyvo.setTotalCount(activtyvo.getTotalCount()+vo.getTotalCount());
											else
												activtyvo.setTotalCount(vo.getTotalCount());
											if(activtyvo.getWhatsAppCovered() != null)
												activtyvo.setWhatsAppCovered(activtyvo.getWhatsAppCovered()+vo.getWhatsAppCovered());
											else
												activtyvo.setWhatsAppCovered(vo.getWhatsAppCovered());
										}
									}
									
									if(activtyvo.getPlannedCount() != null && activtyvo.getPlannedCount().longValue() > 0L && activtyvo.getIvrcovered().longValue() > 0L)
									{
										double perc = (activtyvo.getIvrcovered() * 100.0)/activtyvo.getPlannedCount();
										String percentage = commonMethodsUtilService.roundTo2DigitsFloatValueAsString(Float.valueOf(String.valueOf(perc)));
										activtyvo.setIvrcoveredPerc(percentage);   //ivr covered percentage
									}
									if(activtyvo.getTotalCount() != null && activtyvo.getTotalCount().longValue() > 0l && activtyvo.getIvrTotal() != null && activtyvo.getIvrTotal().longValue() > 0l){
										double perc = (activtyvo.getIvrTotal() * 100.0)/activtyvo.getTotalCount();
										String percentage = commonMethodsUtilService.roundTo2DigitsFloatValueAsString(Float.valueOf(String.valueOf(perc)));
										activtyvo.setIvrTotalPerc(percentage);
									}
									if(activtyvo.getTotalCount() != null && activtyvo.getTotalCount().longValue() > 0l && activtyvo.getInfoCellTotal() != null && activtyvo.getInfoCellTotal().longValue() > 0l){
										double perc = (activtyvo.getInfoCellTotal() * 100.0)/activtyvo.getTotalCount();
										String percentage = commonMethodsUtilService.roundTo2DigitsFloatValueAsString(Float.valueOf(String.valueOf(perc)));
										activtyvo.setInfoCellTotalPerc(percentage);
									}
									if(activtyvo.getPlannedCount() != null && activtyvo.getPlannedCount().longValue() > 0l && activtyvo.getInfoCellcovered() != null && activtyvo.getInfoCellcovered().longValue() > 0l){
										double perc = (activtyvo.getInfoCellcovered() * 100.0)/activtyvo.getPlannedCount();
										String percentage = commonMethodsUtilService.roundTo2DigitsFloatValueAsString(Float.valueOf(String.valueOf(perc)));
										activtyvo.setInfoCellcoveredPerc(percentage);
									}
									if(activtyvo.getInfoCellTotal() != null && activtyvo.getInfoCellTotal().longValue() > 0l && activtyvo.getWhatsAppCovered() != null && activtyvo.getWhatsAppCovered().longValue() > 0l){
										double perc = (activtyvo.getWhatsAppCovered() * 100.0)/activtyvo.getInfoCellTotal();
										String percentage = commonMethodsUtilService.roundTo2DigitsFloatValueAsString(Float.valueOf(String.valueOf(perc)));
										activtyvo.setWhatsAppCoveredPerc(percentage);
									}
									
									if(activtyvo.getPercentage() == null)
										activtyvo.setPercentage("0.00");
									if(activtyvo.getIvrcoveredPerc()  == null)
										activtyvo.setIvrcoveredPerc("0.00");
									if(activtyvo.getIvrTotalPerc() == null)
										activtyvo.setIvrTotalPerc("0.00");
									if(activtyvo.getInfoCellTotalPerc() == null)
										activtyvo.setInfoCellTotalPerc("0.00");
									if(activtyvo.getInfoCellcoveredPerc() == null)
										activtyvo.setInfoCellcoveredPerc("0.00");
									if(activtyvo.getWhatsAppCoveredPerc() == null)
										activtyvo.setWhatsAppCoveredPerc("0.00");
								}
							}
							
							returnVO.getActivityVoList().clear();
							returnVO.setActivityVoList(membersList);
						}
					}
					else if (searchAttributeVO.getTeamLeaderId().longValue() == 0 && searchAttributeVO.getTeamMemberId().longValue() > 0){
						
						List<Long> assigLctnIds = new ArrayList<Long>();
						ActivityVO vo1 = new ActivityVO();
						List<ActivityVO> locationsList = new ArrayList<ActivityVO>();
						
						List<Object[]> list = activityTeamLocationDAO.getTeamLeaderDetailsAndAssignedLocationsByLeaderId(searchAttributeVO.getTeamMemberId());
						if(list != null && list.size() > 0){
							for (Object[] obj : list) {
								
								//vo1.setId(obj[1] != null ? (Long) obj[1]:0l);
								vo1.setName(obj[0] != null ? obj[0].toString():"");
								Long lctnId = obj[1] != null ? (Long) obj[1]:0l;
								assigLctnIds.add(lctnId);
							}
							vo1.setLocationIds(assigLctnIds);
						}
						membersMap.put(searchAttributeVO.getTeamMemberId(), vo1);
						
						if(allLctnsList != null && allLctnsList.size() > 0){
							for (int i = 0; i < allLctnsList.size(); i++) {
								ActivityVO vo = allLctnsList.get(i);
								Long id = vo.getId();
								if(assigLctnIds.contains(id)){
									locationsList.add(vo);
								}
							}
							returnVO.getActivityVoList().clear();
							returnVO.setActivityVoList(locationsList);
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
					if(vo.getIvrcovered() != null && vo.getIvrcovered().longValue() > 0)
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
					
					
			//UserAddress userAddress = saveUserAddress(eventFileUploadVO);
				
			Date activityDate = null;		
			UserAddress userAddress = saveUserAddressByLevelIdAndLevelValue(eventFileUploadVO.getLevelId(), eventFileUploadVO.getLevelValue());
			
			ActivityDocument activityDocument = new ActivityDocument();
			if(eventFileUploadVO.getActivityDate() != null && !eventFileUploadVO.getActivityDate().equals("undefined"))
			{
				SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
			    try {
			    	 activityDate = dateFormat.parse(eventFileUploadVO.getActivityDate());
					 activityDocument.setActivityDate(activityDate);
				} catch (Exception e) {
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
			
			String destPath = folderName+"/"+randomNumber+"."+eventFileUploadVO.getFileExtension();
			
			if(eventFileUploadVO.getInsertType() != null && eventFileUploadVO.getInsertType().trim().equalsIgnoreCase("WS"))
			{   
				destPath = folderName+"/"+randomNumber+".jpg";
				 pathBuilder.append(year).append("/").append(temp).append("-").append(day).append("/").append(randomNumber).append(".")
				 .append("jpg");
				 
				activityDocument.setActivityDate(eventFileUploadVO.getActivityDateFormat());
				ImageAndStringConverter imageAndStringConverter = new ImageAndStringConverter();
				imageAndStringConverter.convertBase64StringToImage(eventFileUploadVO.getImageBase64String(), destPath);
			}
			else
			{
				 pathBuilder.append(year).append("/").append(temp).append("-").append(day).append("/").append(randomNumber).append(".")
				 .append(eventFileUploadVO.getFileExtension());
				 
				copyFile(eventFileUploadVO.getFile().getAbsolutePath(),destPath);
			}
			
			activityDocument.setPath(pathBuilder.toString());
			activityDocument.setInsertedBy(eventFileUploadVO.getUserId());
			activityDocument.setUpdatedBy(eventFileUploadVO.getUpdatedBy());
			
			activityDocument.setInsertedTime(dateUtilService.getCurrentDateAndTime());
			activityDocument.setUpdatedTime(dateUtilService.getCurrentDateAndTime());
			activityDocument.setActivityScopeId(eventFileUploadVO.getActivityScopeId());
			
			activityDocument = activityDocumentDAO.save(activityDocument);
			
		    ActivityInfoDocument activityInfoDocument = new ActivityInfoDocument();
		    
			if(eventFileUploadVO.getInsertType() != null && eventFileUploadVO.getInsertType().trim().equalsIgnoreCase("WS"))
				if(eventFileUploadVO.getActivityLocationInfoId() != null && eventFileUploadVO.getActivityLocationInfoId()>0L)
					activityInfoDocument.setActivityLocationInfoId(eventFileUploadVO.getActivityLocationInfoId());
		    
		    activityInfoDocument.setActivityDocument(activityDocument);
		    activityInfoDocument.setInsertedBy(eventFileUploadVO.getUserId());
		    activityInfoDocument.setUpdatedBy(eventFileUploadVO.getUpdatedBy());
			
		    activityInfoDocument.setInsertedTime(dateUtilService.getCurrentDateAndTime());
		    activityInfoDocument.setUpdatedTime(dateUtilService.getCurrentDateAndTime());
		    activityInfoDocument.setUserAddress(userAddress);
		    
			if(eventFileUploadVO.getInsertType() != null && eventFileUploadVO.getInsertType().trim().equalsIgnoreCase("WS"))
		    {
	    		activityInfoDocument.setLocationScopeId(eventFileUploadVO.getLevelId());
			    if(eventFileUploadVO.getLevelId() < 5l)
			     activityInfoDocument.setLocationValueAddress(eventFileUploadVO.getLevelValue());
			    else
			     activityInfoDocument.setLocationValueAddress(Long.parseLong(eventFileUploadVO.getLevelValue().toString().substring(1).toString()));
		    }
		   
		    
		    if(eventFileUploadVO.getTemp() != null && eventFileUploadVO.getTemp().equalsIgnoreCase("dayCalCulationReq"))
		    {
		    	//conducted Date
		    	Date activityStartDate = activityScopeDAO.getActivityStartDateByActivityScopeId(eventFileUploadVO.getActivityScopeId());
		    	if(activityStartDate != null){
		    		long diff = activityStartDate.getTime() - activityDate.getTime();
		    		long dayDiff = diff / (24 * 60 * 60 * 1000);
		    		
		    		activityInfoDocument.setDay(Math.abs(dayDiff));
		    	}
		    }
		    else
		    {
		      activityInfoDocument.setDay(eventFileUploadVO.getDay());
		    }
		    
		    
		    
		    activityInfoDocument.setIsDeleted("N");
		    activityInfoDocument.setInsertType(eventFileUploadVO.getInsertType());
		    
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
	
	
	public UserAddress saveUserAddressByLevelIdAndLevelValue(Long levelId,Long levelValue)
	{
		try{
			UserAddress userAddress = null;
			if(levelId == 2l){
				userAddress = new UserAddress();
				userAddress.setState(stateDAO.get(levelValue));
				userAddress = userAddressDAO.save(userAddress);
			}
			else if(levelId == 3l){
				userAddress = new UserAddress();
				District distinct = districtDAO.get(levelValue);
				userAddress.setState(stateDAO.get(distinct.getState().getStateId()));
				userAddress.setDistrict(distinct);
				userAddress = userAddressDAO.save(userAddress);
			}
			else if(levelId == 4l){
				userAddress = new UserAddress();
				Constituency constituency = constituencyDAO.get(levelValue);
				District distinct = districtDAO.get(constituency.getDistrict().getDistrictId());
				
				userAddress.setState(stateDAO.get(distinct.getState().getStateId()));
				userAddress.setDistrict(distinct);
				userAddress.setConstituency(constituency);
				userAddress = userAddressDAO.save(userAddress);
			}
			else if(levelId == 5l){//mandal
				userAddress = new UserAddress();
				Long mandalId = Long.parseLong(levelValue.toString().substring(1));
				Tehsil tehsil = tehsilDAO.get(mandalId);
				District distinct = districtDAO.get(tehsil.getDistrict().getDistrictId());
				List<Long> constituencyIds = delimitationConstituencyMandalDAO.getConstituencyIdByMandalID(mandalId);
				
				userAddress.setState(stateDAO.get(distinct.getState().getStateId()));
				userAddress.setDistrict(distinct);
				if(constituencyIds != null && constituencyIds.size() > 0)
				  userAddress.setConstituency(constituencyDAO.get(constituencyIds.get(0)));
				userAddress.setTehsil(tehsil);
				userAddress = userAddressDAO.save(userAddress);
			}
			
			else if(levelId == 6l){//panchayat
				userAddress = new UserAddress();
				Long panchayatId = Long.parseLong(levelValue.toString().substring(1));
				Panchayat panchayat = panchayatDAO.get(panchayatId);
				
				Tehsil tehsil = tehsilDAO.get(panchayat.getTehsil().getTehsilId());
				District distinct = districtDAO.get(tehsil.getDistrict().getDistrictId());
				List<Long> constituencyIds = delimitationConstituencyMandalDAO.getConstituencyIdByMandalID(tehsil.getTehsilId());
				
				userAddress.setState(stateDAO.get(distinct.getState().getStateId()));
				userAddress.setDistrict(distinct);
				if(constituencyIds != null && constituencyIds.size() > 0)
				  userAddress.setConstituency(constituencyDAO.get(constituencyIds.get(0)));
				userAddress.setTehsil(tehsil);
				userAddress.setPanchayatId(panchayat.getPanchayatId());
				userAddress = userAddressDAO.save(userAddress);
			}
			
			else if(levelId == 7l){//muncipality
				userAddress = new UserAddress();
				Long locationBodyId = Long.parseLong(levelValue.toString().substring(1));
				List<Long> constituencyIdsList = assemblyLocalElectionBodyDAO.getConstituencyIdByAssemblyLocalEleBodyId(locationBodyId);
				Long constituencyId = Long.parseLong(constituencyIdsList.get(0).toString());
				
				Constituency constituency = constituencyDAO.get(constituencyId);
				District distinct = districtDAO.get(constituency.getDistrict().getDistrictId());
					
				userAddress.setState(stateDAO.get(distinct.getState().getStateId()));
				userAddress.setDistrict(distinct);
				userAddress.setConstituency(constituency);
				userAddress.setLocalElectionBody(localElectionBodyDAO.get(locationBodyId));
				userAddress = userAddressDAO.save(userAddress);
			}
			
			else if(levelId == 8l ){//ward
				userAddress = new UserAddress();
				Long wardConstituencyId = Long.parseLong(levelValue.toString().substring(1));
				Long localEleBodyId = constituencyDAO.get(wardConstituencyId).getLocalElectionBody().getLocalElectionBodyId();
				
				List<Long> constituencyIdsList = assemblyLocalElectionBodyDAO.getConstituencyIdByAssemblyLocalEleBodyId(localEleBodyId);
				Long constituencyId = Long.parseLong(constituencyIdsList.get(0).toString());
				Constituency constituency = constituencyDAO.get(constituencyId);
				
				District distinct = districtDAO.get(constituency.getDistrict().getDistrictId());
				
				userAddress.setState(stateDAO.get(distinct.getState().getStateId()));
				userAddress.setDistrict(distinct);
				userAddress.setConstituency(constituency);
				userAddress.setWard(constituencyDAO.get(wardConstituencyId));
				userAddress.setLocalElectionBody(localElectionBodyDAO.get(localEleBodyId));
				
				userAddress = userAddressDAO.save(userAddress);
			}
			
			
			return userAddress;
			
		}catch (Exception e) {
			LOG.error(" Exception Occured in saveUserAddress() method, Exception - ",e);
		}
		return null;
	}
	
	public ResultStatus deleteEventUploadFilebyActivityInfoDocId(String acitivityInfoDocId)
	{
		ResultStatus resultStatus = new ResultStatus();
		try{
			List<Long> activityInfoDocIdList = new ArrayList<Long>();
			String[] idStr = acitivityInfoDocId.split(",");
			for(String id: idStr){
				activityInfoDocIdList.add(Long.parseLong(id));
			}
			
			activityInfoDocumentDAO.deleteEventUploadFilebyActivityInfoDocId(activityInfoDocIdList);
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
					locationValue = finalvo.getLocationValue();
					if(activityLevelId == 1l){
						activityLevelValueStr = finalvo.getLocationValue().toString().substring(0, 1);
						locationIdStr = finalvo.getLocationValue().toString().substring(1);
						locationLevelId = Long.parseLong(activityLevelValueStr);
						locationValue = Long.parseLong(locationIdStr);
								
						if(locationLevelId.longValue() == 1l){
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
						
						if(locationLevelId == 1l){
							locationLevel = 7l;
						}
						else if(locationLevelId == 2l){
							locationLevel = 5l;
						}
						else if(locationLevelId == 3l){
							locationLevel = 9l;
						}
					}
					else if(activityLevelId.longValue() == 3L)
						locationLevel = 11L;
					 else if(activityLevelId.longValue() == 4L)
						 locationLevel = 10L;
					 else if(activityLevelId.longValue() == 5L)
						 locationLevel = 13L;
					
					List<Long> ids  = activityLocationInfoDAO.getActivityLocationInfoIdByLocationLevelAndLocationValue(finalvo.getActivityTypeId(),locationLevel, locationValue);
					ActivityLocationInfo tempactivityLocationInfo =null;
					if(ids == null || ids.size()==0L)
					{
						ActivityLocationInfo activityLocationInfo = new ActivityLocationInfo();
							 
						if(locationLevel != null && locationLevel.longValue() <= 9L)
						{
							Long constituencyId = getConstituencyId(locationLevel, locationValue);
							if(constituencyId != null && constituencyId.longValue()>0L)
								activityLocationInfo.setConstituencyId(constituencyId);
						}
						else if(locationLevel != null && locationLevel.longValue() == 13L)
							activityLocationInfo.setConstituencyId(locationValue);
						
						activityLocationInfo.setActivityScopeId(finalvo.getActivityTypeId());
						activityLocationInfo.setLocationLevel(locationLevel);
						activityLocationInfo.setLocationValue(locationValue);
						activityLocationInfo.setInsertedBy(finalvo.getId());
						activityLocationInfo.setUpdatedBy(finalvo.getId());
						activityLocationInfo.setInsertionTime(dateUtilService.getCurrentDateAndTime());
						activityLocationInfo.setUpdatedTime(dateUtilService.getCurrentDateAndTime());
						tempactivityLocationInfo = activityLocationInfoDAO.save(activityLocationInfo);
					}
					
					if(tempactivityLocationInfo != null){
						ids = new ArrayList<Long>(0);
						ids.add(tempactivityLocationInfo.getActivityLocationInfoId());
					}
					
					List<ActivityVO> voList = finalvo.getActivityVoList();
					Long activityLocationInfoId = ids.get(0);
					if(voList != null && voList.size() > 0){
						for (ActivityVO activityVO : voList) {
							ActivityQuestionAnswer activityQuestionAnswer = new ActivityQuestionAnswer();
							
							activityQuestionAnswer.setActivityQuestionnaireId(activityVO.getQuestionId());
							activityQuestionAnswer.setActivityLocationInfoId(activityLocationInfoId);
							activityQuestionAnswer.setActivityOptionId(activityVO.getOptionId());
							if(activityVO.getCount() != null && activityVO.getCount().longValue()>0L)
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
	
	public Long getConstituencyId(Long locationLevel, Long locationValue){
		List<Long> constituencyIds = null;
		Long constituencyId = 0l;
		try{
		Long localElectionBody = 0l;
		if(locationLevel == 5l){//For Mandal
			 constituencyIds = boothDAO.getConstituencyIdByPanchayatId(
					locationValue, new Long(IConstants.VOTER_DATA_PUBLICATION_ID));	
			
			}
		else if(locationLevel == 6l){//For Village
			constituencyIds = boothDAO.getConstituencyIdByPanchayatId(
				locationValue, new Long(IConstants.VOTER_DATA_PUBLICATION_ID));	
		}
		else if(locationLevel == 7l){// For Town
			 localElectionBody = boothDAO.get(locationValue).getLocalBody().getLocalElectionBodyId();
			if(localElectionBody != null){
				constituencyIds = boothDAO.getConstituencyIdByLocalElectionBody(
						 localElectionBody, new Long(IConstants.VOTER_DATA_PUBLICATION_ID));
			}
		}
		else if(locationLevel == 8l){//For Ward
			localElectionBody = constituencyDAO.get(locationValue).getLocalElectionBody().getLocalElectionBodyId();
			if(localElectionBody != null){
				constituencyIds = boothDAO.getConstituencyIdByLocalElectionBody(
						localElectionBody, new Long(IConstants.VOTER_DATA_PUBLICATION_ID));	
			}
		}
		else if(locationLevel == 9l){//For Division
			 constituencyId = boothDAO.get(locationValue).getConstituency().getConstituencyId();
			 if(constituencyId == null){
				 localElectionBody = constituencyDAO.get(locationValue).getLocalElectionBody().getLocalElectionBodyId();
					if(localElectionBody != null){
						constituencyIds = boothDAO.getConstituencyIdByLocalElectionBody(
								localElectionBody, new Long(IConstants.VOTER_DATA_PUBLICATION_ID));	
					}
			 }
		  }
		}catch (Exception e) {
			LOG.error(" Exception Occured in getConstituencyId() method, Exception - ",e);
		}
		if(constituencyIds!= null && constituencyIds.size() >0)
			constituencyId = constituencyIds.get(0);
		return constituencyId;
	}
	public ActivityVO getQuestionnaireForScope(Long scopeId,Long requiredAttributeId){
		ActivityVO finalVO = new ActivityVO(); 
		try {
			LOG.info("Entered into getQuestionnaireForScope");
			List<Object[]> objList = null;
			if(requiredAttributeId == null || requiredAttributeId == 0l){
				objList = activityQuestionnaireOptionDAO.getQuestionnaireForScope(scopeId);	
			}else{
				objList = activityQuestionnaireOptionDAO.getQuestionnaireForScopeAndRespondentTypeIds(scopeId, requiredAttributeId);
			}
			if(objList != null && objList.size() > 0){
				int number=0;
				for (Object[] objects : objList) {
					ActivityVO matchedVO = getMatchedQuestionVo(finalVO,(Long)objects[0]);
					number = number+1;
					if(matchedVO == null){
						matchedVO = new ActivityVO();
						matchedVO.setQuestionId((Long)objects[0]);
						matchedVO.setQuestion(number+") "+objects[1].toString());
						matchedVO.setOptionTypeId((Long)objects[2]);
						matchedVO.setOptionType(objects[3].toString());
						finalVO.getActivityVoList().add(matchedVO);
					}
					
					ActivityVO optionVO = new ActivityVO();
					optionVO.setOptionId((Long)objects[4]);
					optionVO.setOption(objects[5].toString());
					matchedVO.getOptionsList().add(optionVO);
					
				}
			}
				
				
		} catch (Exception e) {
			LOG.error("Exception raised at getQuestionnaireForScope", e);
		}
		return finalVO;
	}
	
	public ActivityVO getMatchedQuestionVo(ActivityVO vo,Long qid){
		try {
			LOG.info("Entered into getMatchedQuestionVo");
			if(vo!=null && vo.getActivityVoList()!=null && vo.getActivityVoList().size()>0){
				for(ActivityVO voIn : vo.getActivityVoList()){
					if(voIn.getQuestionId().equals(qid)){
						return voIn;
					}
				}
			}
		} catch (Exception e) {
			LOG.error("Exception raised at getMatchedQuestionVo", e);
		}
		return null;
	}
	
	@SuppressWarnings("unchecked")
	public ResultStatus uploadActivityDocuments(Long activityId,String sourcePath,ActivityDocumentVO activityLocation)
	{
		ResultStatus resultStatus = new ResultStatus();
		try{
			File sourceFolder = new File(IConstants.STATIC_CONTENT_FOLDER_URL+"/"+IConstants.ACTIVITY_DOCUMENT_UPLOAD+"/"+sourcePath);
			String[] arr = sourceFolder.getName().split("_"); 
			String level = arr[0];
			String levelName = arr[1];
			String destPath = createMainFolderForActivityDocuments();
			
			if(activityLocation == null)
				activityLocation = new ActivityDocumentVO();
			
			if(level.equalsIgnoreCase("State"))
			{
				activityLocation.setStateId(stateDAO.getStateIdByStateName(levelName));
			}
			else if(level.equalsIgnoreCase("District"))
			{
				Long districtId = districtDAO.getDistrictByStateIdAndDistrictName(activityLocation.getStateId(),levelName);
				if(districtId == null)
					return null;
				activityLocation.setDistrictId(districtId);
			}
			else if(level.equalsIgnoreCase("Constituency"))
			{
				Long constituencyId = constituencyDAO.getConstituencyIdByDistrictIdAndConstituencyName(activityLocation.getDistrictId(),levelName);
				if(constituencyId == null)
					return null;
				activityLocation.setConstituencyId(constituencyId);
			}
			else if(level.equalsIgnoreCase("Mandal"))
			{
				List<Object> list = tehsilDAO.findTehsilIdByTehsilNameAndDistrict(levelName, activityLocation.getDistrictId());
				if(list.size() == 0 || list.get(0) == null)
					return null;
				activityLocation.setMandalId((Long)list.get(0));
			}
			else if(level.equalsIgnoreCase("Town"))
			{
				List<Object> list = localElectionBodyDAO.getLocalElectionBodyIdByNameAndDistrictId(levelName, activityLocation.getDistrictId());
				if(list.size() == 0 || list.get(0) == null)
					return null;
				activityLocation.setTownId((Long)list.get(0));
			}
			else if(level.equalsIgnoreCase("Panchayat"))
			{
				Long panchayatId = panchayatDAO.getPanchayatIdByTehsilIdAndPanchayatName(activityLocation.getMandalId(),levelName);
				if(panchayatId == null)
					return null;
				activityLocation.setPanchayatId(panchayatId);
			}
			else if(level.equalsIgnoreCase("Ward"))
			{
				Long wardId = constituencyDAO.getWardIdByTownIdAndWardName(activityLocation.getTownId(),levelName);
				if(wardId == null)
					return null;
				activityLocation.setWardId(wardId);
			}
			for(File subFolder : sourceFolder.listFiles())
			{
				try{
					if(subFolder.isDirectory())
					{
						String subFolderStr1 = subFolder.getName().split("_")[0];
						if(subFolderStr1.equalsIgnoreCase("Day"))
						{
							File destFolder = new File(IConstants.STATIC_CONTENT_FOLDER_URL+IConstants.ACTIVITY_DOCUMENT_FOLDER+"/"+destPath+"/"+sourcePath+"/"+subFolder.getName());
							if(!destFolder.exists())
								destFolder.mkdir();
							
							ActivityDocumentVO activityDocumentVO = new ActivityDocumentVO();
							activityDocumentVO.setActivityId(activityId);
							activityDocumentVO.setDay(Long.valueOf(subFolder.getName().split("_")[1]));
							activityDocumentVO.setPath(destPath+"/"+sourcePath+"/"+subFolder.getName()+"/");
							activityDocumentVO.setActivityDate(new DateUtilService().getDateByStringAndFormat(subFolder.getName().split("_")[2],"yyyy-MM-dd"));
							activityDocumentVO.setLevel(level);
							activityDocumentVO.setLevelValue(levelName);
							
							List<String> docList = new ArrayList<String>(0);
							for(File document : subFolder.listFiles())
							{
								if(!document.isDirectory())
								{
									String docName = Integer.valueOf(RandomNumberGeneraion.randomGenerator(6)).toString()+".jpg";
									FileUtils.copyFile(document,new File(destFolder.getAbsolutePath()+"/"+docName));
									docList.add(docName);
									activityLocation.setCount(activityLocation.getCount()+1);
								}
							}
							activityDocumentVO.setDocList(docList);
							saveActivityDocumentsUpload(activityDocumentVO,activityLocation);
						}
						else if(getLevelsList().contains(subFolderStr1))
						{
							uploadActivityDocuments(activityId,sourcePath+"/"+subFolder.getName(),activityLocation);
						}
					}
				}catch(Exception e)
				{
					LOG.error("Exception occured in subfolder - "+subFolder.getName()+" - ",e);
				}
			}
			resultStatus.setResultCode(ResultCodeMapper.SUCCESS);
			resultStatus.setResultState(activityLocation.getCount());
		}catch(Exception e)
		{
			LOG.error("Exception occured in uploadActivityDocuments() Method - ",e);
			resultStatus.setResultCode(ResultCodeMapper.FAILURE);
			resultStatus.setExceptionEncountered(e);
		}
		return resultStatus;
	}
	
	public ResultStatus saveActivityDocumentsUpload(ActivityDocumentVO activityDocumentVO,ActivityDocumentVO activityLocation)
	{
		LOG.error((new Date()).toString()+" - Enter into saveActivityDocumentsUpload() Method, with Level - "+activityDocumentVO.getLevel()+" and Path - "+activityDocumentVO.getPath());
		ResultStatus resultStatus = new ResultStatus();
		try{
			UserAddress userAddress = new UserAddress();
			Long locationScopeId = null;
			Long locationScopeValue = null;
			
			if(activityDocumentVO.getLevel().equalsIgnoreCase("State"))
			{
				userAddress.setState(stateDAO.get(activityLocation.getStateId()));
				locationScopeId = 2L;
				locationScopeValue = activityLocation.getStateId();
			}
			else if(activityDocumentVO.getLevel().equalsIgnoreCase("District"))
			{
				userAddress.setState(stateDAO.get(activityLocation.getStateId()));
				userAddress.setDistrict(districtDAO.get(activityLocation.getDistrictId()));
				
				locationScopeId = 3L;
				locationScopeValue = activityLocation.getDistrictId();
			}
			else if(activityDocumentVO.getLevel().equalsIgnoreCase("Constituency"))
			{
				userAddress.setState(stateDAO.get(activityLocation.getStateId()));
				userAddress.setDistrict(districtDAO.get(activityLocation.getDistrictId()));
				userAddress.setConstituency(constituencyDAO.get(activityLocation.getConstituencyId()));
				
				locationScopeId = 4L;
				locationScopeValue = activityLocation.getConstituencyId();
			}
			else if(activityDocumentVO.getLevel().equalsIgnoreCase("Mandal"))
			{
				userAddress.setState(stateDAO.get(activityLocation.getStateId()));
				userAddress.setDistrict(districtDAO.get(activityLocation.getDistrictId()));
				userAddress.setConstituency(constituencyDAO.get(activityLocation.getConstituencyId()));
				userAddress.setTehsil(tehsilDAO.get(activityLocation.getMandalId()));
				
				locationScopeId = 5L;
				locationScopeValue = activityLocation.getMandalId();
			}
			else if(activityDocumentVO.getLevel().equalsIgnoreCase("Town"))
			{
				userAddress.setState(stateDAO.get(activityLocation.getStateId()));
				userAddress.setDistrict(districtDAO.get(activityLocation.getDistrictId()));
				userAddress.setConstituency(constituencyDAO.get(activityLocation.getConstituencyId()));
				userAddress.setLocalElectionBody(localElectionBodyDAO.get(activityLocation.getTownId()));
				
				locationScopeId = 7L;
				locationScopeValue = activityLocation.getTownId();
			}
			else if(activityDocumentVO.getLevel().equalsIgnoreCase("Panchayat"))
			{
				userAddress.setState(stateDAO.get(activityLocation.getStateId()));
				userAddress.setDistrict(districtDAO.get(activityLocation.getDistrictId()));
				userAddress.setConstituency(constituencyDAO.get(activityLocation.getConstituencyId()));
				userAddress.setTehsil(tehsilDAO.get(activityLocation.getMandalId()));
				userAddress.setPanchayatId(activityLocation.getPanchayatId());
				
				locationScopeId = 6L;
				locationScopeValue = activityLocation.getPanchayatId();
			}
			else if(activityDocumentVO.getLevel().equalsIgnoreCase("Ward"))
			{
				userAddress.setState(stateDAO.get(activityLocation.getStateId()));
				userAddress.setDistrict(districtDAO.get(activityLocation.getDistrictId()));
				userAddress.setConstituency(constituencyDAO.get(activityLocation.getConstituencyId()));
				userAddress.setLocalElectionBody(localElectionBodyDAO.get(activityLocation.getTownId()));
				userAddress.setWard(constituencyDAO.get(activityLocation.getWardId()));
				
				locationScopeId = 8L;
				locationScopeValue = activityLocation.getWardId();
			}
			userAddress = userAddressDAO.save(userAddress);
			
			for(String docName : activityDocumentVO.getDocList())
			{
				ActivityDocument activityDocument = new ActivityDocument();
				activityDocument.setDocumentName(docName);
				activityDocument.setPath(activityDocumentVO.getPath()+docName);
				activityDocument.setActivityDate(activityDocumentVO.getActivityDate());
				activityDocument.setInsertedBy(1L);
				activityDocument.setUpdatedBy(1L);
				activityDocument.setInsertedTime(dateUtilService.getCurrentDateAndTime());
				activityDocument.setUpdatedTime(dateUtilService.getCurrentDateAndTime());
				activityDocument.setActivityScopeId(activityDocumentVO.getActivityId());
				activityDocument = activityDocumentDAO.save(activityDocument);
				
				ActivityInfoDocument activityInfoDocument = new ActivityInfoDocument();
				
				activityInfoDocument.setActivityDocument(activityDocument);
				activityInfoDocument.setUserAddress(userAddress);
				activityInfoDocument.setLocationScopeId(locationScopeId);
				activityInfoDocument.setLocationValueAddress(locationScopeValue);
				activityInfoDocument.setDay(activityDocumentVO.getDay());
				activityInfoDocument.setInsertedBy(1L);
				activityInfoDocument.setUpdatedBy(1L);
				activityInfoDocument.setInsertedTime(dateUtilService.getCurrentDateAndTime());
				activityInfoDocument.setUpdatedTime(dateUtilService.getCurrentDateAndTime());
				activityInfoDocumentDAO.save(activityInfoDocument);
			}
			LOG.error((new Date()).toString()+" - Saving Completed ");	
		}catch(Exception e)
		{
			LOG.error("Exception occured in saveActivityDocumentsUpload () Method ",e);
		}
		return resultStatus;
	}
	
	public static List<String> getLevelsList()
	{
		List<String> list = new ArrayList<String>(0);
		list.add("State");
		list.add("District");
		list.add("Constituency");
		list.add("Mandal");
		list.add("Town");
		list.add("Panchayat");
		list.add("Ward");
		return list;
	}
	
	public String createMainFolderForActivityDocuments()
	{
		try{
			Date date = new Date();
			Calendar calendar = Calendar.getInstance(TimeZone.getTimeZone(IConstants.TIME_ZONE_INDIA));
			calendar.setTime(date);
			
			int year = calendar.get(Calendar.YEAR);
		    int month = calendar.get(Calendar.MONTH);
		    int day = calendar.get(Calendar.DAY_OF_MONTH);
		    
		    File yearDir = new File(IConstants.STATIC_CONTENT_FOLDER_URL+IConstants.ACTIVITY_DOCUMENT_FOLDER+"/"+year);
		    if(!yearDir.exists())
		    		yearDir.mkdir();
		    
		    File monthDir = new File(IConstants.STATIC_CONTENT_FOLDER_URL+IConstants.ACTIVITY_DOCUMENT_FOLDER+"/"+year+"/"+(month+1)+"_"+day);
		    if(!monthDir.exists())
		    	monthDir.mkdir();
		    return year+"/"+(month+1)+"_"+day;
		}catch(Exception e)
		{
			LOG.error(e);
		}
		return null;
	}
	
	public List<IdNameVO> getActivityLeadersDetailsByActivityScope(List<Long> activityScopeIds,boolean showCounts){
		
		List<IdNameVO> idNameVOList = new ArrayList<IdNameVO>();
		List<Long>  leaderIds = new ArrayList<Long>();
		Map<Long,IdNameVO> leadersMap = new LinkedHashMap<Long, IdNameVO>();
		
		try {
			
			List<Object[]> list = activityTeamMemberDAO.getTeamLeadersByActivityScope(activityScopeIds);
			
			if(list != null && list.size() > 0){
				for (Object[] obj : list) {
					IdNameVO vo = new IdNameVO();
					
					Long leaderId = (Long) (obj[0] != null ? obj[0]:0l);
					leaderIds.add(leaderId);
					vo.setId(leaderId);
					vo.setName(obj[1] != null ? obj[1].toString():"");
					
					leadersMap.put(leaderId, vo);
				}
			}
			
			if(showCounts){
				
				List<Object[]> list1 = activityTeamLocationDAO.getActivityLocationsForTeamLeders(leaderIds);
				
				if(list1 != null && list1.size() > 0){
					for (Object[] obj : list1) {
						
						Long tlId = (Long) (obj[1] != null ? obj[1]:0l);
						IdNameVO idNamevo = leadersMap.get(tlId);
						if(idNamevo != null){
							idNamevo.setDistrictid((Long) (obj[2] != null ? obj[2]:0l));
							idNamevo.setAvailableCount((Long) (obj[3] != null ? obj[3]:0l));
						}
					}
				}
			}
			
			idNameVOList.addAll(leadersMap.values());
			
		} catch (Exception e) {
			LOG.error("Exception occured in getActivityLeadersDetailsByActivityScope () Method ",e);
		}
		
		return idNameVOList;
	}
	
	public List<IdNameVO> getTeamMembersByLeaderAndActivityScope(List<Long> teamLeaderIds,List<Long> activityScopeIds){
		
		List<IdNameVO> idNameVOList = new ArrayList<IdNameVO>();
		
		try {
			
			List<Object[]> list = activityTeamMemberDAO.getTeamMembersByTeamLeaderAndActivityScope(teamLeaderIds, activityScopeIds);
			
			if(list != null && list.size() > 0){
				for (Object[] obj : list) {
					IdNameVO vo = new IdNameVO();
					
					vo.setId((Long) (obj[0] != null ? obj[0]:0l));
					vo.setName(obj[1] != null ? obj[1].toString():"");
					
					idNameVOList.add(vo);
				}
			}
			
		} catch (Exception e) {
			LOG.error("Exception occured in getActivityLeadersDetailsByActivityScope () Method ",e);
		}
		
		return idNameVOList;
	}
	
	
	public List<BasicVO> getActivityDocumentsImages(Long levelId,Long levelValue,Long day,Integer startIndex,Integer maxIndex,Long activityScopeId,String activityDate,String tempVar)
	{
		List<BasicVO> resultList = null;
		try{
			if(levelId >= 5l){
				levelValue = Long.parseLong(levelValue.toString().substring(1).toString());
			}
			Date actDate = null;
			if(activityDate != null){
				SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
				actDate = dateFormat.parse(activityDate);
			}
			if(tempVar != null && tempVar.equalsIgnoreCase("dayCalCulationReq")){//conductedDate
				Date activityStartDate = activityScopeDAO.getActivityStartDateByActivityScopeId(activityScopeId);
		    	if(activityStartDate != null){
		    		long diff = activityStartDate.getTime() - actDate.getTime();
		    		long dayDiff = diff / (24 * 60 * 60 * 1000);
		    		day = Math.abs(dayDiff);
		    	}
			}
			
			List<Object[]> list = activityInfoDocumentDAO.getActivityDocumentsImagesByLevelIdAndLevelValue(levelId, levelValue,day,activityScopeId,actDate,startIndex,maxIndex);
			if(list != null && list.size() > 0)
			{
				resultList = new ArrayList<BasicVO>();
				for(Object[] params: list){
					BasicVO basicVO = new BasicVO();
					basicVO.setId((Long)params[0]);
					basicVO.setName(params[1] != null?params[1].toString():"");
					resultList.add(basicVO);
				}
				
				resultList.get(0).setCount(activityInfoDocumentDAO.getActivityDocumentsImagesCountByLevelIdAndLevelValue(levelId, levelValue,day,activityScopeId,actDate));
			}
		}catch (Exception e) {
			LOG.error("Exception occured in getActivityDocumentsImages() Method ",e);
		}
		return resultList;
	}
	
	public ActivityWSVO getUserActivityDetailsByUserId(Long userId){
		
		ActivityWSVO finalVO = new ActivityWSVO();
		
		try {
			List<Object[]> list = activityTabUserLocationDAO.getUserActivityDetailsByUserId(userId);
			Set<Long> scpIds = new HashSet<Long>();
			List<Long> actvtyIds = new ArrayList<Long>();
			List<ActivityMainVO> activites = new ArrayList<ActivityMainVO>();
			List<ActivityScopeVO> actvtyScpes = new ArrayList<ActivityScopeVO>();
			List<ActivityLocationVO> actvtyLctns = new ArrayList<ActivityLocationVO>();
			
			if(list != null && list.size() > 0){
				for (Object[] obj : list) {
						Long scopeId = (Long) (obj[2] != null ? obj[2]:0l);
						
						
						if(!actvtyIds.contains(Long.valueOf(obj[3].toString()))){
							ActivityMainVO actvty = new ActivityMainVO();
							actvty.setActivityId(obj[3] != null ? Long.valueOf(obj[3].toString()):null);
							actvty.setActivityName(obj[4] != null ? obj[4].toString():"");
							actvtyIds.add(obj[3] != null ? Long.valueOf(obj[3].toString()):null);
							activites.add(actvty);
						}
						
						if(!scpIds.contains(scopeId)){
							ActivityScopeVO actvty = new ActivityScopeVO();
							actvty.setActivityId(obj[3] != null ? Long.valueOf(obj[3].toString()):null);
							actvty.setActivityScopeId(obj[2] != null ? Long.valueOf(obj[2].toString()):null);
							actvty.setActivityLevelId(obj[8] != null ? Long.valueOf(obj[8].toString()):null);
							actvty.setStartDate(obj[9] != null ? obj[9].toString():"");
							actvty.setEndDate(obj[10] != null ? obj[10].toString():"");
							actvtyScpes.add(actvty);
							
							scpIds.add(scopeId);
						}
						
						ActivityLocationVO actvtyLctn = new ActivityLocationVO();
						actvtyLctn.setActivityScopeId(obj[2] != null ? Long.valueOf(obj[2].toString()):null);
						actvtyLctn.setLocationLevel(obj[5] != null ? Long.valueOf(obj[5].toString()):null);
						actvtyLctn.setLocationValue(obj[6] != null ? Long.valueOf(obj[6].toString()):null);
						actvtyLctn.setActivityLocationInfoId(obj[11] != null ? Long.valueOf(obj[11].toString()):null);
						actvtyLctn.setPlannedStartDate(obj[12] != null ? obj[12].toString():"");
						actvtyLctn.setPlannedEndDate(obj[14] != null ? obj[14].toString():"");
						actvtyLctn.setConductedStartDate(obj[13] != null ? obj[13].toString():"");
						actvtyLctn.setConductedEndDate(obj[15] != null ? obj[15].toString():"");
						actvtyLctns.add(actvtyLctn);
						
				}
			}
			
			finalVO.setActivities(activites);
			finalVO.setActivityScopeList(actvtyScpes);
			finalVO.setActivityLocationsList(actvtyLctns);
			
			if(!scpIds.isEmpty()){
				List<Long> scpIdsLst = new ArrayList<Long>(scpIds);
				ActivityWSVO fnlVO = getQuestionsListForScopeId(scpIdsLst);
				
				finalVO.setQuestionList(fnlVO.getQuestionList());
				finalVO.setOptionsList(fnlVO.getOptionsList());
				finalVO.setQuestnairList(fnlVO.getQuestnairList());
				finalVO.setQuestnairOptnsList(fnlVO.getQuestnairOptnsList());
				
				List<ActivityReqAttributesVO> reqAttrs = getRequiredAttributes(scpIdsLst);
				finalVO.setAttributes(reqAttrs);
			}
		} catch (Exception e) {
			LOG.error("Exception occured in getUserActivityDetailsByUserId() Method ",e);
		}
		return finalVO;
	}
	
	public ActivityWSVO getQuestionsListForScopeId(List<Long> scopeIds){
		
		ActivityWSVO finalVO = new ActivityWSVO();
		try {
			List<Object[]> objList = activityQuestionnaireOptionDAO.getQuestionnaireOfScope(scopeIds);
			finalVO = new ActivityWSVO(); 
			List<ActivityQuestionVO> 						 questsList 		= new ArrayList<ActivityQuestionVO>();
			List<ActivityOptionVO> 		 					 optnsList 			= new ArrayList<ActivityOptionVO>();
			List<ActivityQuestionnairVO>					 actvtyQustnr 		= new ArrayList<ActivityQuestionnairVO>();
			List<ActivityQuestionnairOptionVO> 				 actvtyQustnrOptn 	= new ArrayList<ActivityQuestionnairOptionVO>();
			
			List<Long> qlist =  new ArrayList<Long>();
			List<Long> oplist =  new ArrayList<Long>();
			List<Long> actvtyQstnrList =  new ArrayList<Long>();
			if(objList != null && objList.size() > 0){
				for(Object[] obj:objList){
					if(!qlist.contains(Long.valueOf(obj[0].toString()))){
						ActivityQuestionVO qvo = new ActivityQuestionVO();
						qvo.setQuestionId(obj[0]!=null?Long.valueOf(obj[0].toString()):null);
						qvo.setQuestion(obj[1]!=null?obj[1].toString():"");
						qlist.add(obj[0]!=null?Long.valueOf(obj[0].toString()):null);
						questsList.add(qvo);
					}
					if(!oplist.contains(Long.valueOf(obj[5].toString()))){
						ActivityOptionVO optnVO = new ActivityOptionVO();
						optnVO.setOptionId(obj[5]!=null?Long.valueOf(obj[5].toString()):null);
						optnVO.setOption(obj[6]!=null?obj[6].toString():"");
						oplist.add(obj[5]!=null?Long.valueOf(obj[5].toString()):null);
						optnsList.add(optnVO);
					}
					
					if(!actvtyQstnrList.contains(Long.valueOf(obj[11].toString()))){
						ActivityQuestionnairVO	actvtyQustnrVO 			= new ActivityQuestionnairVO();
						actvtyQustnrVO.setQuestionId(obj[0]!=null?Long.valueOf(obj[0].toString()):null); 
						actvtyQustnrVO.setOptionTypeId(obj[3]!=null?Long.valueOf(obj[3].toString()):null);
						actvtyQustnrVO.setOrderNo(obj[2]!=null?Long.valueOf(obj[2].toString()):null);
						actvtyQustnrVO.setParentQuestionnairId(obj[12]!=null?Long.valueOf(obj[12].toString()):null);
						actvtyQustnrVO.setRespondentTypeId(obj[8]!=null?Long.valueOf(obj[8].toString()):null);
						actvtyQustnrVO.setQuestionnairId(obj[11]!=null?Long.valueOf(obj[11].toString()):null);
						actvtyQustnrVO.setActivityScopeId(obj[10]!=null?Long.valueOf(obj[10].toString()):null);
						actvtyQstnrList.add(obj[11]!=null?Long.valueOf(obj[11].toString()):null);
						actvtyQustnr.add(actvtyQustnrVO);
					}
					
					ActivityQuestionnairOptionVO actvtyQustnrOptnVO = new ActivityQuestionnairOptionVO();
					actvtyQustnrOptnVO.setActivityQuestionnairId(obj[11]!=null?Long.valueOf(obj[11].toString()):null);
					actvtyQustnrOptnVO.setOptionId(obj[5]!=null?Long.valueOf(obj[5].toString()):null);
					actvtyQustnrOptnVO.setOrderNo(obj[7]!=null?Long.valueOf(obj[7].toString()):null);
					actvtyQustnrOptn.add(actvtyQustnrOptnVO);
				}
			}
			finalVO.setQuestionList(questsList);
			finalVO.setOptionsList(optnsList);
			finalVO.setQuestnairList(actvtyQustnr);
			finalVO.setQuestnairOptnsList(actvtyQustnrOptn);
		} catch (Exception e) {
			LOG.error("Exception Raised ",e);
			
		}
		return finalVO;
	}
	
	
	public List<String> getActivityDates(Long activityScopeId)
	{
		List<String> dates = new ArrayList<String>();
		Object[] obj = activityScopeDAO.getDatesForActivityByActivityScopeId(activityScopeId);
		if(obj != null)
		{
			String Date = obj[0] != null ? obj[0].toString():"";
			String Date1 = obj[1] != null ? obj[1].toString():"";
			if(obj[0] != null)
			{
			dates.add(Date.substring(5, 7)+"/"+ Date.substring(8, 10)+"/"+Date.substring(0, 4));
			}
			if(obj[1] != null)
				dates.add(Date1.substring(5, 7)+"/"+ Date1.substring(8, 10)+"/"+Date1.substring(0, 4));
		}
		return dates;
	}
	
	
	public List<ActivityReqAttributesVO> getRequiredAttributes(List<Long> scopeIds){
		List<ActivityReqAttributesVO> voList = new ArrayList<ActivityReqAttributesVO>();
		try {
			List<Object[]> list = activityScopeRequiredAttributesDAO.getActivityRequiredAttributes(scopeIds);
			if(list != null && list.size() > 0){
				for (Object[] obj : list) {
					ActivityReqAttributesVO vo = new ActivityReqAttributesVO();
					vo.setActivityScopeId(obj[0] != null ? Long.valueOf(obj[0].toString()):0l);
					vo.setReqAttributeId(obj[2] != null ? Long.valueOf(obj[2].toString()):0l);
					voList.add(vo);
				}
			}
		} catch (Exception e) {
			Log.error("Exception Occured in getRequiredAttributesListForScope method in ActivityService ",e);
		}
		return voList;
	}
	
	public Long savingTabDetails(final TabDetailsVO tabDetailsVO){
		Long tabDetailsId = 0l;
		try {
			
			Long tabDetailsPrimaryKeyId = (Long) transactionTemplate.execute(new TransactionCallback() {
				public Object doInTransaction(TransactionStatus arg0) {
					
					TabDetails tabDetails = new TabDetails();
					
					tabDetails.setAttendedTime(tabDetailsVO.getAttendedTime());
					tabDetails.setImei(tabDetailsVO.getImei());
					tabDetails.setUniqueKey(tabDetailsVO.getUniqueKey());
					tabDetails.setInsertedTime(tabDetailsVO.getInsertedTime());
					tabDetails.setLatitude(tabDetailsVO.getLatitude());
					tabDetails.setLongitude(tabDetailsVO.getLongitude());
					tabDetails.setTabUserId(tabDetailsVO.getTabUserId());
					tabDetails.setSyncSource("WS");
					tabDetails.setTabPrimaryKey(tabDetailsVO.getTabPrimaryKey());
					tabDetails.setInsertedTime(new DateUtilService().getCurrentDateAndTime());
					
					tabDetails = tabDetailsDAO.save(tabDetails);
					
					if(tabDetails != null)
						return  tabDetails.getTabDetailsId();
					else
						return 0;
				}
			});
			
			tabDetailsId = tabDetailsPrimaryKeyId;
				
		} catch (Exception e) {
			LOG.error(" Exception Occured in saveTabDetails method in ActivityService ",e);
		}
		return tabDetailsId;
	}
	
	public ResultStatus savingAttendenceQuestionAnswer(final Long activityQuestionAnswerId,final Long attendenceId){
		ResultStatus resultStatus = new ResultStatus();
		try {
			
			transactionTemplate.execute(new TransactionCallbackWithoutResult() {
				public void doInTransactionWithoutResult(TransactionStatus status) {
					
					AttendenceQuestionAnswer attendenceQuestionAnswer = new AttendenceQuestionAnswer();
					
					attendenceQuestionAnswer.setActivityQuestionAnswerId(activityQuestionAnswerId);
					attendenceQuestionAnswer.setAttendanceId(attendenceId);
					attendenceQuestionAnswer.setInsertedTime(dateUtilService.getCurrentDateAndTime());
					
					attendenceQuestionAnswer = attendenceQuestionAnswerDAO.save(attendenceQuestionAnswer);
				}
			});
			resultStatus.setResultCode(0);
			resultStatus.setMessage("Details Saved Successfully...");
		} catch (Exception e) {
			resultStatus.setResultCode(1);
			resultStatus.setMessage("Exception Occured...");
			LOG.error("Exception Occured in savingAttendenceQuestionAnswer method in ActivityService",e);
		}
		return resultStatus;
	}
	
	public String savingActivityTabDetails(){
		
		try {
			ActivityTabRequestBackup activityTabRequestBackup = new ActivityTabRequestBackup();
			
			activityTabRequestBackup.setUserId(1l);
			activityTabRequestBackup.setImeiNo("");
			activityTabRequestBackup.setUniqueCode("");
			activityTabRequestBackup.setInsertedTime(dateUtilService.getCurrentDateAndTime());
			activityTabRequestBackup.setJsonArr("");
			activityTabRequestBackup.setApkPrimaryKey(1l);
			
			activityTabRequestBackupDAO.save(activityTabRequestBackup);
			
		} catch (Exception e) {
			LOG.error("Exception Occured in savingActivityTabDetails method in ActivityService",e);
		}
		return "success";
	}
	
	public ActivityLoginVO checkActivityTabUserLogin(String userName,String password){
		ActivityLoginVO loginvo = new ActivityLoginVO();
		
		try {
			ActivityTabUser activityTabUser = activityTabUserDAO.checkActivityTabUserLogin(userName, password);
			if(activityTabUser != null){
				loginvo.setUserId(activityTabUser.getActivityTabUserId());
				loginvo.setUserName(activityTabUser.getUserName());
				loginvo.setPassword(activityTabUser.getPassword());
				loginvo.setMobileNo(activityTabUser.getMobileNo());
				loginvo.setStatus("Success");
			}
			else if(activityTabUser == null){
				loginvo.setStatus("Failure");
			}
		} catch (Exception e) {
			LOG.error("Exception Occured in checkActivityTabUserLogin method in ActivityService ",e);
		}
		return loginvo;
	}
	
	public TdpCadreWSVO getCadreDetailsByVoterIdorMemberShipNo(String memberShipNo,String voterCardNo){
		TdpCadreWSVO tdpCadreWSVO = new TdpCadreWSVO();
		try {
			List<Object[]> list = tdpCadreDAO.getCadreDetailsByMembershipNoOrVoterId(memberShipNo, voterCardNo);
			if(list != null && list.size() > 0){
				Object[] obj = list.get(0);
				if(obj != null){
					tdpCadreWSVO.setTdpCadreId((Long) (obj[0] != null ? obj[0]:0l));
					tdpCadreWSVO.setMemberShipNo(obj[1] != null ? obj[1].toString():"");
					tdpCadreWSVO.setStatus("Success");
				}
			}
		} catch (Exception e) {
			tdpCadreWSVO.setStatus("Failure");
			LOG.error("Exception Occured in getCadreDetailsByVoterIdorMemberShipNo method in ActivityService",e);
		}
		return tdpCadreWSVO;
	}
	
	public List<BasicVO> getRequiredAttributesListForScope(Long scopeId){
		List<BasicVO> voList = new ArrayList<BasicVO>();
		try {
			List<Object[]> list = activityScopeRequiredAttributesDAO.getActivityRequiredAttributesForScope(scopeId);
			if(list != null && list.size() > 0){
				for (Object[] obj : list) {
					BasicVO vo = new BasicVO();
					vo.setId(obj[0] != null ? Long.valueOf(obj[0].toString()):0l);
					vo.setName(obj[1] != null ? obj[1].toString():"");
					voList.add(vo);
				}
			}
		} catch (Exception e) {
			Log.error("Exception Occured in getRequiredAttributesListForScope method in ActivityService ",e);
		}
		return voList;
	}
	
	public BasicVO getActivityLocationWiseDetailsByScopeId(Long scopeId){
		BasicVO finalvo = new BasicVO();
		List<Long> constIds = new ArrayList<Long>();
		List<Long> distIds = new ArrayList<Long>();
		Map<Long,BasicVO> constMap = new LinkedHashMap<Long, BasicVO>();
		Map<Long,BasicVO> distMap  = new LinkedHashMap<Long, BasicVO>();
		try {
			List<Object[]> list = activityLocationInfoDAO.getActivityLocationDetailsByScopeId(scopeId);
			if(list != null && list.size() > 0){
				for (Object[] obj : list) {
					BasicVO vo = new BasicVO();
					Long locationLvlId = (Long) (obj[2] != null ? obj[2]:0l);
					Long locationVal = (Long) (obj[3] != null ? obj[3]:0l);
					vo.setId(locationVal);
					if(locationLvlId.longValue() == 9l){
						constIds.add(locationVal);
						constMap.put(locationVal, vo);
					}
					else if(locationLvlId.longValue() == 7l){
						distIds.add(locationVal);
						distMap.put(locationVal, vo);
					}
				}
			}
			if(constIds != null && constIds.size() > 0){
				List<Object[]> list1 = constituencyDAO.getConstituencyInfoByConstituencyIdList(constIds);
				if(list1 != null && list1.size() > 0){
					for (Object[] obj : list1) {
						Long constId = (Long) (obj[0] != null ? obj[0]:0l);
						BasicVO vo1 = constMap.get(constId);
						vo1.setName(obj[1] != null ? obj[1].toString():"");
					}
				}
			}
			if(distIds != null && distIds.size() > 0){
				List<Object[]> list2 = districtDAO.getDistrictDetailsByDistrictIds(distIds);
				if(list2 != null && list2.size() > 0){
					for (Object[] obj : list2) {
						Long distId = (Long) (obj[0] != null ? obj[0]:0l);
						BasicVO vo2 = distMap.get(distId);
						vo2.setName(obj[1] != null ? obj[1].toString():"");
					}
				}
			}
			finalvo.setConstituencyList(new ArrayList<BasicVO>(constMap.values()));
			finalvo.setDistrictList(new ArrayList<BasicVO>(distMap.values()));
			//finalvo.setConstituencyList((List<BasicVO>) constMap.values());
			//finalvo.setDistrictList((List<BasicVO>) distMap.values());
		} catch (Exception e) {
			Log.error("Exception Occured in getActivityLocationWiseDetailsByScopeId method in ActivityService ",e);
		}
		return finalvo;
	}
}
