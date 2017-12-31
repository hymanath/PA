package com.itgrids.partyanalyst.service.impl;

import java.io.File;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;
import java.util.TimeZone;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.jfree.util.Log;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionCallback;
import org.springframework.transaction.support.TransactionCallbackWithoutResult;
import org.springframework.transaction.support.TransactionTemplate;

import com.itgrids.partyanalyst.dao.IActivityAttributeDAO;
import com.itgrids.partyanalyst.dao.IActivityAttributeQuestionnaireInfoDAO;
import com.itgrids.partyanalyst.dao.IActivityConductedInfoDAO;
import com.itgrids.partyanalyst.dao.IActivityDAO;
import com.itgrids.partyanalyst.dao.IActivityDocumentDAO;
import com.itgrids.partyanalyst.dao.IActivityInfoDocumentDAO;
import com.itgrids.partyanalyst.dao.IActivityLevelDAO;
import com.itgrids.partyanalyst.dao.IActivityLocationAttendanceDAO;
import com.itgrids.partyanalyst.dao.IActivityLocationInfoDAO;
import com.itgrids.partyanalyst.dao.IActivityQuestionAnswerDAO;
import com.itgrids.partyanalyst.dao.IActivityQuestionnaireDAO;
import com.itgrids.partyanalyst.dao.IActivityQuestionnaireOptionDAO;
import com.itgrids.partyanalyst.dao.IActivityRequiredAttributeDAO;
import com.itgrids.partyanalyst.dao.IActivityScopeDAO;
import com.itgrids.partyanalyst.dao.IActivityScopeRequiredAttributesDAO;
import com.itgrids.partyanalyst.dao.IActivityStatusQuestionnaireDAO;
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
import com.itgrids.partyanalyst.dao.ICadreCallingFeedbackDAO;
import com.itgrids.partyanalyst.dao.ICadreSurveyUserDAO;
import com.itgrids.partyanalyst.dao.ICallPurposeDAO;
import com.itgrids.partyanalyst.dao.ICallStatusDAO;
import com.itgrids.partyanalyst.dao.ICallSupportTypeDAO;
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
import com.itgrids.partyanalyst.dao.IUserAccessLevelValueDAO;
import com.itgrids.partyanalyst.dao.IUserActivityScopeDAO;
import com.itgrids.partyanalyst.dao.IUserAddressDAO;
import com.itgrids.partyanalyst.dao.IUserConstituencyAccessInfoDAO;
import com.itgrids.partyanalyst.dao.IUserDAO;
import com.itgrids.partyanalyst.dao.hibernate.BoothDAO;
import com.itgrids.partyanalyst.dao.impl.IActivityAttendanceDAO;
import com.itgrids.partyanalyst.dao.impl.IActivityDaywiseQuestionnaireDAO;
import com.itgrids.partyanalyst.dto.ActivityAttendanceInfoVO;
import com.itgrids.partyanalyst.dto.ActivityDetailsVO;
import com.itgrids.partyanalyst.dto.ActivityDocumentVO;
import com.itgrids.partyanalyst.dto.ActivityLocationVO;
import com.itgrids.partyanalyst.dto.ActivityLoginVO;
import com.itgrids.partyanalyst.dto.ActivityMainVO;
import com.itgrids.partyanalyst.dto.ActivityOptionVO;
import com.itgrids.partyanalyst.dto.ActivityQuestionVO;
import com.itgrids.partyanalyst.dto.ActivityQuestionnairOptionVO;
import com.itgrids.partyanalyst.dto.ActivityQuestionnairVO;
import com.itgrids.partyanalyst.dto.ActivityReqAttributesVO;
import com.itgrids.partyanalyst.dto.ActivityResponseVO;
import com.itgrids.partyanalyst.dto.ActivityScopeVO;
import com.itgrids.partyanalyst.dto.ActivityVO;
import com.itgrids.partyanalyst.dto.ActivityWSVO;
import com.itgrids.partyanalyst.dto.AddressVO;
import com.itgrids.partyanalyst.dto.BasicVO;
import com.itgrids.partyanalyst.dto.EventDocumentVO;
import com.itgrids.partyanalyst.dto.EventFileUploadVO;
import com.itgrids.partyanalyst.dto.IdNameVO;
import com.itgrids.partyanalyst.dto.LocationWiseBoothDetailsVO;
import com.itgrids.partyanalyst.dto.OptionsCountVo;
import com.itgrids.partyanalyst.dto.ResultCodeMapper;
import com.itgrids.partyanalyst.dto.ResultStatus;
import com.itgrids.partyanalyst.dto.SearchAttributeVO;
import com.itgrids.partyanalyst.dto.TabDetailsVO;
import com.itgrids.partyanalyst.dto.TdpCadreWSVO;
import com.itgrids.partyanalyst.model.ActivityConductedInfo;
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
import com.itgrids.partyanalyst.model.CadreCallingFeedback;
import com.itgrids.partyanalyst.model.Constituency;
import com.itgrids.partyanalyst.model.District;
import com.itgrids.partyanalyst.model.LocalElectionBody;
import com.itgrids.partyanalyst.model.Panchayat;
import com.itgrids.partyanalyst.model.TabDetails;
import com.itgrids.partyanalyst.model.Tehsil;
import com.itgrids.partyanalyst.model.UserAddress;
import com.itgrids.partyanalyst.service.IActivityAttendanceService;
import com.itgrids.partyanalyst.service.IActivityService;
import com.itgrids.partyanalyst.service.ICadreCommitteeService;
import com.itgrids.partyanalyst.service.IRegionServiceData;
import com.itgrids.partyanalyst.utils.CommonMethodsUtilService;
import com.itgrids.partyanalyst.utils.DateUtilService;
import com.itgrids.partyanalyst.utils.IConstants;
import com.itgrids.partyanalyst.utils.ImageAndStringConverter;
import com.itgrids.partyanalyst.utils.RandomNumberGeneraion;
import com.itgrids.partyanalyst.utils.SetterAndGetterUtilService;

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
	private IActivityAttendanceService activityAttendanceService;
	private IActivityLocationAttendanceDAO activityLocationAttendanceDAO;
	private IUserAccessLevelValueDAO userAccessLevelValueDAO;
	private IUserConstituencyAccessInfoDAO userConstituencyAccessInfoDAO;
	private IActivityStatusQuestionnaireDAO activityStatusQuestionnaireDAO;
	private IActivityDaywiseQuestionnaireDAO activityDaywiseQuestionnaireDAO;
	private IActivityAttendanceDAO activityAttendanceDAO;
	private SetterAndGetterUtilService setterAndGetterUtilService;
	private ICallPurposeDAO callPurposeDAO;	
	private ICallSupportTypeDAO callSupportTypeDAO;
	private ICallStatusDAO callStatusDAO;
	private ICadreCallingFeedbackDAO cadreCallingFeedbackDAO;
	private IActivityConductedInfoDAO activityConductedInfoDAO;
	private IActivityRequiredAttributeDAO activityRequiredAttributeDAO;
	
	
	public ICallStatusDAO getCallStatusDAO() {
		return callStatusDAO;
	}
	public void setCallStatusDAO(ICallStatusDAO callStatusDAO) {
		this.callStatusDAO = callStatusDAO;
	}
	public ICadreCallingFeedbackDAO getCadreCallingFeedbackDAO() {
		return cadreCallingFeedbackDAO;
	}
	public void setCadreCallingFeedbackDAO(
			ICadreCallingFeedbackDAO cadreCallingFeedbackDAO) {
		this.cadreCallingFeedbackDAO = cadreCallingFeedbackDAO;
	}
	public ICallSupportTypeDAO getCallSupportTypeDAO() {
		return callSupportTypeDAO;
	}
	public void setCallSupportTypeDAO(ICallSupportTypeDAO callSupportTypeDAO) {
		this.callSupportTypeDAO = callSupportTypeDAO;
	}
	public ICallPurposeDAO getCallPurposeDAO() {
		return callPurposeDAO;
	}
	public void setCallPurposeDAO(ICallPurposeDAO callPurposeDAO) {
		this.callPurposeDAO = callPurposeDAO;
	}
	public IActivityAttendanceDAO getActivityAttendanceDAO() {
		return activityAttendanceDAO;
	}
	public void setActivityAttendanceDAO(
			IActivityAttendanceDAO activityAttendanceDAO) {
		this.activityAttendanceDAO = activityAttendanceDAO;
	}
	public SetterAndGetterUtilService getSetterAndGetterUtilService() {
		return setterAndGetterUtilService;
	}
	public void setSetterAndGetterUtilService(
			SetterAndGetterUtilService setterAndGetterUtilService) {
		this.setterAndGetterUtilService = setterAndGetterUtilService;
	}
	
	
	public IActivityDaywiseQuestionnaireDAO getActivityDaywiseQuestionnaireDAO() {
		return activityDaywiseQuestionnaireDAO;
	}
	public void setActivityDaywiseQuestionnaireDAO(
			IActivityDaywiseQuestionnaireDAO activityDaywiseQuestionnaireDAO) {
		this.activityDaywiseQuestionnaireDAO = activityDaywiseQuestionnaireDAO;
	}
	public IActivityStatusQuestionnaireDAO getActivityStatusQuestionnaireDAO() {
		return activityStatusQuestionnaireDAO;
	}
	public void setActivityStatusQuestionnaireDAO(
			IActivityStatusQuestionnaireDAO activityStatusQuestionnaireDAO) {
		this.activityStatusQuestionnaireDAO = activityStatusQuestionnaireDAO;
	}
	public IUserConstituencyAccessInfoDAO getUserConstituencyAccessInfoDAO() {
		return userConstituencyAccessInfoDAO;
	}
	public void setUserConstituencyAccessInfoDAO(
			IUserConstituencyAccessInfoDAO userConstituencyAccessInfoDAO) {
		this.userConstituencyAccessInfoDAO = userConstituencyAccessInfoDAO;
	}
	public IUserAccessLevelValueDAO getUserAccessLevelValueDAO() {
		return userAccessLevelValueDAO;
	}
	public void setUserAccessLevelValueDAO(
			IUserAccessLevelValueDAO userAccessLevelValueDAO) {
		this.userAccessLevelValueDAO = userAccessLevelValueDAO;
	}
	public IActivityLocationAttendanceDAO getActivityLocationAttendanceDAO() {
		return activityLocationAttendanceDAO;
	}
	public void setActivityLocationAttendanceDAO(
			IActivityLocationAttendanceDAO activityLocationAttendanceDAO) {
		this.activityLocationAttendanceDAO = activityLocationAttendanceDAO;
	}
	public IActivityAttendanceService getActivityAttendanceService() {
		return activityAttendanceService;
	}
	public void setActivityAttendanceService(
			IActivityAttendanceService activityAttendanceService) {
		this.activityAttendanceService = activityAttendanceService;
	}
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
	
	public IActivityConductedInfoDAO getActivityConductedInfoDAO() {
		return activityConductedInfoDAO;
	}
	public void setActivityConductedInfoDAO(IActivityConductedInfoDAO activityConductedInfoDAO) {
		this.activityConductedInfoDAO = activityConductedInfoDAO;
	}
	public IActivityRequiredAttributeDAO getActivityRequiredAttributeDAO() {
		return activityRequiredAttributeDAO;
	}
	public void setActivityRequiredAttributeDAO(
			IActivityRequiredAttributeDAO activityRequiredAttributeDAO) {
		this.activityRequiredAttributeDAO = activityRequiredAttributeDAO;
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
						reportList = cadreCommitteeService.getPanchayatWardByMandalId(locationId.toString(),null);
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
	
	public ActivityVO getActivityDetailsBySearchCriteria(SearchAttributeVO searchAttributeVO1,Long stateId1,boolean isExecuted)
	{
		ActivityVO returnVO = new ActivityVO();
		try {
			SearchAttributeVO searchAttributeVO = new SearchAttributeVO();
			searchAttributeVO = searchAttributeVO1;
			
			Long activityLevelId =null;
			if(searchAttributeVO.getAttributesIdsList() != null && searchAttributeVO.getAttributesIdsList().size()>0)
			{
				
					ActivityScope activityScope = activityScopeDAO.get(searchAttributeVO.getAttributesIdsList().get(0));
					if(activityScope != null){
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
							searchAttributeVO.getLocationTypeIdsList().add(3L);
						}
						else if(activityLevelId.longValue() == 4L)
						{
							searchAttributeVO.getLocationTypeIdsList().add(2L);
						}
						else if(activityLevelId.longValue() == 5L)
						{
							searchAttributeVO.getLocationTypeIdsList().add(4L);
						}
					}
					
					
			if(searchAttributeVO.getSearchType().equalsIgnoreCase(IConstants.STATE) && searchAttributeVO.getLevelId().longValue() != 4L){
					List<BasicVO> areaWiseCountLsit = regionServiceDataImp.areaCountListByAreaIdsOnScope(searchAttributeVO,stateId1);
					if(areaWiseCountLsit != null && areaWiseCountLsit.size()>0)
					{
						Long totalAreasCount = getTotalAreaCountByList(areaWiseCountLsit);
						returnVO.setTotalCount(totalAreasCount);
					}
				}
				
				if(activityLevelId != null && activityLevelId.longValue()>0L)
		        {
		          searchAttributeVO.getLocationTypeIdsList().clear();
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
		            searchAttributeVO.getLocationTypeIdsList().add(11L);
		          }
		          else if(activityLevelId.longValue() == 4L)
		          {
		            searchAttributeVO.getLocationTypeIdsList().add(10L);
		          }
		          else if(activityLevelId.longValue() == 5L)
		          {
		            searchAttributeVO.getLocationTypeIdsList().add(13L);
		          }
		        }
				if(activityLevelId != null && activityLevelId.longValue()>0L)
				{
					searchAttributeVO.getLocationTypeIdsList().clear();
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
						searchAttributeVO.getLocationTypeIdsList().add(11L);
					}
					else if(activityLevelId.longValue() == 4L)
					{
						searchAttributeVO.getLocationTypeIdsList().add(10L);
					}
					else if(activityLevelId.longValue() == 5L)
					{
						searchAttributeVO.getLocationTypeIdsList().add(13L);
					}
				}
				Long stateId =0L;
			if(searchAttributeVO.getSearchType() != null && searchAttributeVO.getConditionType().trim().contains("daywiseResult")){
				if(searchAttributeVO.getCallFrom() != null && searchAttributeVO.getCallFrom().equalsIgnoreCase("BD"))
				{
					List<ActivityAttendanceInfoVO> dayWiseResultList = getActivityDayWiseCountsByLocationForAttendance(searchAttributeVO,stateId);
					returnVO.getActivityAttendanceInfoVOList().addAll(dayWiseResultList);	
				}
				else
				{
				List<ActivityVO> dayWiseResultList = getActivityDayWiseCountsByLocation(searchAttributeVO,stateId);
				returnVO.getActivityVoList().addAll(dayWiseResultList);
				}
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
				if(searchAttributeVO.getSearchType().equalsIgnoreCase(IConstants.STATE) && searchAttributeVO.getLevelId().longValue() == 4L ){
					searchAttributeVO.getLocationIdsList().add(searchAttributeVO.getLocationValue());
					 if(activityLevelId.longValue() > 2L)
						 searchAttributeVO.setTypeId(9999L);//dummy value
					questionnairesCount = activityQuestionAnswerDAO.getActivityQuestionnairesCountsByLocation(searchAttributeVO,stateId);
					yesCount = activityQuestionAnswerDAO.getActivityQuestionnairesAttributeCountsByLocation(searchAttributeVO,1L,stateId);
					searchAttributeVO.setConditionType("planned");
						plannedActivities = activityLocationInfoDAO.getActivityPlannedInfoCellAndIVRCountsByLocation(searchAttributeVO,stateId);
					searchAttributeVO.setConditionType(" infocell ");
						infoCellconductedActivities = activityLocationInfoDAO.getActivityPlannedInfoCellAndIVRCountsByLocation(searchAttributeVO,stateId); 
					searchAttributeVO.setConditionType("ivr");
						ivrconductedActivities = activityLocationInfoDAO.getActivityPlannedInfoCellAndIVRCountsByLocation(searchAttributeVO,stateId); 

					searchAttributeVO.setConditionType("infocell");
						infoCellNotPlannedActivities = activityLocationInfoDAO.getActivityNotPlannedInfoCellAndIVRCountsByLocation(searchAttributeVO,stateId); 	
					searchAttributeVO.setConditionType("ivr");
						ivrNotPlannedActivities = activityLocationInfoDAO.getActivityNotPlannedInfoCellAndIVRCountsByLocation(searchAttributeVO,stateId); 
					
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
				else if(searchAttributeVO.getSearchType().equalsIgnoreCase(IConstants.STATE)){
					searchAttributeVO.getLocationIdsList().add(searchAttributeVO.getLocationValue());
					 if(activityLevelId.longValue() > 2L)
						 searchAttributeVO.setTypeId(9999L);//dummy value
					questionnairesCount = activityQuestionAnswerDAO.getActivityQuestionnairesCountsByLocation(searchAttributeVO,stateId);
					yesCount = activityQuestionAnswerDAO.getActivityQuestionnairesAttributeCountsByLocation(searchAttributeVO,1L,stateId);
					searchAttributeVO.setConditionType("planned");
						plannedActivities = activityLocationInfoDAO.getActivityPlannedInfoCellAndIVRCountsByLocation(searchAttributeVO,stateId);
					searchAttributeVO.setConditionType(" infocell ");
						infoCellconductedActivities = activityLocationInfoDAO.getActivityPlannedInfoCellAndIVRCountsByLocation(searchAttributeVO,stateId); 
					searchAttributeVO.setConditionType("ivr");
						ivrconductedActivities = activityLocationInfoDAO.getActivityPlannedInfoCellAndIVRCountsByLocation(searchAttributeVO,stateId); 

					searchAttributeVO.setConditionType("infocell");
						infoCellNotPlannedActivities = activityLocationInfoDAO.getActivityNotPlannedInfoCellAndIVRCountsByLocation(searchAttributeVO,stateId); 	
					searchAttributeVO.setConditionType("ivr");
						ivrNotPlannedActivities = activityLocationInfoDAO.getActivityNotPlannedInfoCellAndIVRCountsByLocation(searchAttributeVO,stateId); 
					
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
						if(activityLevelId.longValue() > 2L)
							 searchAttributeVO.setTypeId(9999L);//dummy value
						questionnairesCount = activityQuestionAnswerDAO.getActivityQuestionnairesCountsByLocation(searchAttributeVO,stateId);
						yesCount = activityQuestionAnswerDAO.getActivityQuestionnairesAttributeCountsByLocation(searchAttributeVO,1L,stateId);
					searchAttributeVO.setConditionType("planned");
						plannedActivities = activityLocationInfoDAO.getActivityPlannedInfoCellAndIVRCountsByLocation(searchAttributeVO,stateId);
					searchAttributeVO.setConditionType(" infocell ");
						infoCellconductedActivities = activityLocationInfoDAO.getActivityPlannedInfoCellAndIVRCountsByLocation(searchAttributeVO,stateId); 
					searchAttributeVO.setConditionType("ivr");
						ivrconductedActivities = activityLocationInfoDAO.getActivityPlannedInfoCellAndIVRCountsByLocation(searchAttributeVO,stateId); 

					searchAttributeVO.setConditionType("infocell");
						infoCellNotPlannedActivities = activityLocationInfoDAO.getActivityNotPlannedInfoCellAndIVRCountsByLocation(searchAttributeVO,stateId); 	
					searchAttributeVO.setConditionType("ivr");
						ivrNotPlannedActivities = activityLocationInfoDAO.getActivityNotPlannedInfoCellAndIVRCountsByLocation(searchAttributeVO,stateId); 
					
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
						
						questionnairesCount = activityQuestionAnswerDAO.getActivityQuestionnairesCountsByLocation(searchAttributeVO,stateId);
						yesCount = activityQuestionAnswerDAO.getActivityQuestionnairesAttributeCountsByLocation(searchAttributeVO,1L,stateId);
						searchAttributeVO.setConditionType("planned");
							plannedActivities = activityLocationInfoDAO.getActivityPlannedInfoCellAndIVRCountsByLocation(searchAttributeVO,stateId);
						searchAttributeVO.setConditionType(" infocell ");
							infoCellconductedActivities = activityLocationInfoDAO.getActivityPlannedInfoCellAndIVRCountsByLocation(searchAttributeVO,stateId); 
						searchAttributeVO.setConditionType("ivr");
							ivrconductedActivities = activityLocationInfoDAO.getActivityPlannedInfoCellAndIVRCountsByLocation(searchAttributeVO,stateId); 
	
						searchAttributeVO.setConditionType("infocell");
							infoCellNotPlannedActivities = activityLocationInfoDAO.getActivityNotPlannedInfoCellAndIVRCountsByLocation(searchAttributeVO,stateId); 	
						searchAttributeVO.setConditionType("ivr");
							ivrNotPlannedActivities = activityLocationInfoDAO.getActivityNotPlannedInfoCellAndIVRCountsByLocation(searchAttributeVO,stateId); 
						
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

							questionnairesCount = activityQuestionAnswerDAO.getActivityQuestionnairesCountsByLocation(searchAttributeVO,stateId);
							 yesCount = activityQuestionAnswerDAO.getActivityQuestionnairesAttributeCountsByLocation(searchAttributeVO,1L,stateId);
							 
							searchAttributeVO.setConditionType("planned");
								plannedActivities = activityLocationInfoDAO.getActivityPlannedInfoCellAndIVRCountsByLocation(searchAttributeVO,stateId);
							searchAttributeVO.setConditionType(" infocell ");
								infoCellconductedActivities = activityLocationInfoDAO.getActivityPlannedInfoCellAndIVRCountsByLocation(searchAttributeVO,stateId); 
							searchAttributeVO.setConditionType("ivr");
								ivrconductedActivities = activityLocationInfoDAO.getActivityPlannedInfoCellAndIVRCountsByLocation(searchAttributeVO,stateId); 
	
							searchAttributeVO.setConditionType("infocell");
								infoCellNotPlannedActivities = activityLocationInfoDAO.getActivityNotPlannedInfoCellAndIVRCountsByLocation(searchAttributeVO,stateId); 	
							searchAttributeVO.setConditionType("ivr");
								ivrNotPlannedActivities = activityLocationInfoDAO.getActivityNotPlannedInfoCellAndIVRCountsByLocation(searchAttributeVO,stateId); 
							
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
							questionnairesCount = activityQuestionAnswerDAO.getActivityQuestionnairesCountsByLocation(searchAttributeVO,stateId);
							yesCount = activityQuestionAnswerDAO.getActivityQuestionnairesAttributeCountsByLocation(searchAttributeVO,1L,stateId);
							searchAttributeVO.setConditionType("planned");
							plannedActivities = activityLocationInfoDAO.getActivityPlannedInfoCellAndIVRCountsByLocation(searchAttributeVO,stateId);
						searchAttributeVO.setConditionType(" infocell ");
							infoCellconductedActivities = activityLocationInfoDAO.getActivityPlannedInfoCellAndIVRCountsByLocation(searchAttributeVO,stateId); 
						searchAttributeVO.setConditionType("ivr");
							ivrconductedActivities = activityLocationInfoDAO.getActivityPlannedInfoCellAndIVRCountsByLocation(searchAttributeVO,stateId); 

						searchAttributeVO.setConditionType("infocell");
							infoCellNotPlannedActivities = activityLocationInfoDAO.getActivityNotPlannedInfoCellAndIVRCountsByLocation(searchAttributeVO,stateId); 	
						searchAttributeVO.setConditionType("ivr");
							ivrNotPlannedActivities = activityLocationInfoDAO.getActivityNotPlannedInfoCellAndIVRCountsByLocation(searchAttributeVO,stateId); 
						
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
							questionnairesCount = activityQuestionAnswerDAO.getActivityQuestionnairesCountsByLocation(searchAttributeVO,stateId);
							yesCount = activityQuestionAnswerDAO.getActivityQuestionnairesAttributeCountsByLocation(searchAttributeVO,1L,stateId);
							searchAttributeVO.setConditionType("planned");
								plannedActivities = activityLocationInfoDAO.getActivityPlannedInfoCellAndIVRCountsByLocation(searchAttributeVO,stateId);
							searchAttributeVO.setConditionType(" infocell ");
								infoCellconductedActivities = activityLocationInfoDAO.getActivityPlannedInfoCellAndIVRCountsByLocation(searchAttributeVO,stateId); 
							searchAttributeVO.setConditionType("ivr");
								ivrconductedActivities = activityLocationInfoDAO.getActivityPlannedInfoCellAndIVRCountsByLocation(searchAttributeVO,stateId); 
	
							searchAttributeVO.setConditionType("infocell");
								infoCellNotPlannedActivities = activityLocationInfoDAO.getActivityNotPlannedInfoCellAndIVRCountsByLocation(searchAttributeVO,stateId); 	
							searchAttributeVO.setConditionType("ivr");
								ivrNotPlannedActivities = activityLocationInfoDAO.getActivityNotPlannedInfoCellAndIVRCountsByLocation(searchAttributeVO,stateId); 
							
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
				
					List<LocationWiseBoothDetailsVO>  villagesORWardsList =  cadreCommitteeService.getPanchayatWardByMandalId(searchAttributeVO.getLocationId().toString(),null);
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
								questionnairesCount = activityQuestionAnswerDAO.getActivityQuestionnairesCountsByLocation(searchAttributeVO,stateId);
								yesCount = activityQuestionAnswerDAO.getActivityQuestionnairesAttributeCountsByLocation(searchAttributeVO,1L,stateId);
							searchAttributeVO.setConditionType("planned");
								plannedActivities = activityLocationInfoDAO.getActivityPlannedInfoCellAndIVRCountsByLocation(searchAttributeVO,stateId);
							searchAttributeVO.setConditionType(" infocell ");
								infoCellconductedActivities = activityLocationInfoDAO.getActivityPlannedInfoCellAndIVRCountsByLocation(searchAttributeVO,stateId); 
							searchAttributeVO.setConditionType("ivr");
								ivrconductedActivities = activityLocationInfoDAO.getActivityPlannedInfoCellAndIVRCountsByLocation(searchAttributeVO,stateId); 

							searchAttributeVO.setConditionType("infocell");
								infoCellNotPlannedActivities = activityLocationInfoDAO.getActivityNotPlannedInfoCellAndIVRCountsByLocation(searchAttributeVO,stateId); 	
							searchAttributeVO.setConditionType("ivr");
								ivrNotPlannedActivities = activityLocationInfoDAO.getActivityNotPlannedInfoCellAndIVRCountsByLocation(searchAttributeVO,stateId); 
							
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
								questionnairesCount = activityQuestionAnswerDAO.getActivityQuestionnairesCountsByLocation(searchAttributeVO,stateId);
								yesCount = activityQuestionAnswerDAO.getActivityQuestionnairesAttributeCountsByLocation(searchAttributeVO,1L,stateId);
								searchAttributeVO.setConditionType("planned");
								plannedActivities = activityLocationInfoDAO.getActivityPlannedInfoCellAndIVRCountsByLocation(searchAttributeVO,stateId);
							searchAttributeVO.setConditionType(" infocell ");
								infoCellconductedActivities = activityLocationInfoDAO.getActivityPlannedInfoCellAndIVRCountsByLocation(searchAttributeVO,stateId); 
							searchAttributeVO.setConditionType("ivr");
								ivrconductedActivities = activityLocationInfoDAO.getActivityPlannedInfoCellAndIVRCountsByLocation(searchAttributeVO,stateId); 

							searchAttributeVO.setConditionType("infocell");
								infoCellNotPlannedActivities = activityLocationInfoDAO.getActivityNotPlannedInfoCellAndIVRCountsByLocation(searchAttributeVO,stateId); 	
							searchAttributeVO.setConditionType("ivr");
								ivrNotPlannedActivities = activityLocationInfoDAO.getActivityNotPlannedInfoCellAndIVRCountsByLocation(searchAttributeVO,stateId); 
							
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
							searchAttributeVO.getLocationTypeIdsList().add(11L);
						}
						else if(activityLevelId.longValue() == 5L)
						{
							searchAttributeVO.getLocationTypeIdsList().add(13L);
						}else if(activityLevelId.longValue() == 4L){
							searchAttributeVO.getLocationTypeIdsList().add(10L);
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
							if(searchAttributeVO.getSearchType().equalsIgnoreCase(IConstants.STATE) && searchAttributeVO.getLevelId()==4L){
								//totalAreasCount = returnVO.getTotalCount();
								//totalPlannedCount = activityVO.getPlannedCount();
							}
							else if(searchAttributeVO.getSearchType().equalsIgnoreCase(IConstants.STATE)){
								totalAreasCount = returnVO.getTotalCount();
								totalPlannedCount = activityVO.getPlannedCount();
							}
							else if(searchAttributeVO.getSearchType().equalsIgnoreCase(IConstants.DISTRICT)){
								searchAttributeVO.setScopeId(3L);
								activityVO.setName(activityVO.getName()+" District");
								List<Long> locationTypeIdsList = new ArrayList<Long>(0);
								locationTypeIdsList.addAll(searchAttributeVO.getLocationTypeIdsList());
								searchAttributeVO.getLocationTypeIdsList().clear();
								searchAttributeVO.getLocationTypeIdsList().add(4L);
								List<BasicVO> areaWiseCountLsit = regionServiceDataImp.areaCountListByAreaIdsOnScope(searchAttributeVO,stateId);
								if(areaWiseCountLsit != null && areaWiseCountLsit.size()>0)
								{
									totalAreasCount = totalAreasCount+getTotalAreaCountByList(areaWiseCountLsit);
									activityVO.setTotalCount(totalAreasCount);
									totalPlannedCount = activityVO.getPlannedCount();
								}
								
								searchAttributeVO.getLocationTypeIdsList().clear();
								searchAttributeVO.getLocationTypeIdsList().addAll(locationTypeIdsList);
							}
							else if(searchAttributeVO.getSearchType().equalsIgnoreCase(IConstants.CONSTITUENCY)){
								searchAttributeVO.setScopeId(5L);
								activityVO.setName(activityVO.getName()+" Constituency");
								List<BasicVO> areaWiseCountLsit = regionServiceDataImp.areaCountListByAreaIdsOnScope(searchAttributeVO,stateId);
								searchAttributeVO.setScopeId(7L);
								List<BasicVO> localBodyAreaWiseCountList = regionServiceDataImp.areaCountListByAreaIdsOnScope(searchAttributeVO,stateId);
								if(commonMethodsUtilService.isListOrSetValid(localBodyAreaWiseCountList))
									areaWiseCountLsit.addAll(localBodyAreaWiseCountList);
								
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
									List<BasicVO> areaWiseCountLsit = regionServiceDataImp.areaCountListByAreaIdsOnScope(searchAttributeVO,stateId);
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
									List<BasicVO> areaWiseCountLsit = regionServiceDataImp.areaCountListByAreaIdsOnScope(searchAttributeVO,stateId);
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
								if(activityVO.getPlannedCount() != null && activityVO.getPlannedCount().longValue()>0L && totalAreasCount != null && totalAreasCount.longValue()>0L)
								{
									double perc = (activityVO.getPlannedCount() * 100.0)/totalAreasCount;
									String percentage = commonMethodsUtilService.roundTo2DigitsFloatValueAsString(Float.valueOf(String.valueOf(perc)));
									activityVO.setPercentage(percentage);
								}
								if(activityVO.getNotPlannedCount() != null && activityVO.getNotPlannedCount().longValue()>0L && totalAreasCount != null &&totalAreasCount.longValue()>0L)
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
					if(totalCount != null && totalCount.longValue()>0L)
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
			
			ActivityAttendanceInfoVO apattendenceVO = getActivityAttendenceDetails(searchAttributeVO,stateId1);
			if(returnVO != null && returnVO.getActivityVoList() != null && returnVO.getActivityVoList().size()>0)
			{
				ActivityVO vo = returnVO.getActivityVoList().get(0);
				if(vo != null){
					if(!searchAttributeVO.getLocationTypeIdsList().contains(13L))//constituency Level
						vo.setTotalCount(returnVO.getTotalCount());
					if(apattendenceVO != null && apattendenceVO.getSubList() != null && apattendenceVO.getSubList().size()>0){
						vo.getActivityAttendanceInfoVOList().addAll(apattendenceVO.getSubList());
					}
				}
			}
				
			if(!isExecuted && searchAttributeVO1.getSearchType().equalsIgnoreCase(IConstants.STATE)){
				ActivityVO retirnVO1 =  getActivityDetailsBySearchCriteria(searchAttributeVO,36L,true);
				if(retirnVO1 != null && retirnVO1.getActivityVoList() != null && retirnVO1.getActivityVoList().size()>0){
					returnVO.getActivityVoList().addAll(retirnVO1.getActivityVoList());
				}
			}
		  }	
			EventDocumentVO locationVo = new EventDocumentVO();
			locationVo.setLocationScope(getInputSearchType(searchAttributeVO.getSearchType()));
			locationVo.setLocationValue(searchAttributeVO.getLocationId());
			locationVo.setActivityId(searchAttributeVO.getAttributesIdsList().get(0));	
			if(searchAttributeVO.getStartDate() != null)
			locationVo.setStrDate(searchAttributeVO.getStartDate().toString());	
			if(searchAttributeVO.getEndDate() != null)
			locationVo.setEndDate(searchAttributeVO.getEndDate().toString());
			if(searchAttributeVO.getTypeId() == 9999L)
				locationVo.setTypeId(9999L);
			BasicVO countVO = cadreCommitteeService.getLocationsHierarchyForEvent(locationVo,"locationWise");
			if(commonMethodsUtilService.isListOrSetValid(returnVO.getActivityVoList())){
				if(searchAttributeVO.getSearchType().equalsIgnoreCase(IConstants.STATE)){
					for(BasicVO vo1 : countVO.getLocationsList()){
						ActivityVO vo = returnVO.getActivityVoList().get(0);
						Long count = vo.getImagesCount() != null ? vo.getImagesCount():0L;
						vo.setImagesCount(count+vo1.getCount());
					}
				}else{
					setImagesCount(countVO.getLocationsList(),returnVO.getActivityVoList());
				}
			}
			ActivityVO stateTypevo = new ActivityVO();
			if(searchAttributeVO.getTypeId().longValue() == 9999L){
				if(returnVO.getActivityVoList() != null && returnVO.getActivityVoList().size() > 0){
					for (ActivityVO activityvo : returnVO.getActivityVoList()) {
						if(activityvo.getName() != null && activityvo.getName().equalsIgnoreCase("Andhra Pradesh") && activityvo.getImagesCount().longValue() > 0L){
							stateTypevo.getActivityVoList().add(activityvo);
							return stateTypevo;
						}
					}
				}
			}
		} catch (Exception e) {
			LOG.error(" Exception occured in getActivityDetailsBySearchCriteria() ActivityService Class... ",e);
		}
		return returnVO;
	}
	
	public String getInputSearchType(String type)
	{
		String returnType="";
		if(type.equalsIgnoreCase("district"))
			returnType = "state";
		else if(type.equalsIgnoreCase("constituency"))
			returnType = "district";
		else if(type.equalsIgnoreCase("mandal") || type.equalsIgnoreCase("URBAN"))
			returnType = "constituency";
		else if(type.equalsIgnoreCase("village"))
			returnType = "mandal";
		else
			returnType = type;
		return returnType;
	}
	
	public void setImagesCount(List<BasicVO> countVOList,List<ActivityVO> activityVoList)
	{
		try{
			if(activityVoList != null && activityVoList.size() > 0)
			{
				for(ActivityVO vo : activityVoList)
				{
					vo.setImagesCount(0L);
					boolean flag = false;
					if(countVOList != null && countVOList.size() > 0)
					for(BasicVO vo1 : countVOList)
					{
						if(vo1.getId().longValue() == vo.getId().longValue())
						{
							vo.setImagesCount(vo1.getCount());
							flag = true;
						}
						if(flag == true)
								break;
					}
				}
			}
		}
		catch(Exception e)
		{
			
			LOG.error(" Exception occured in setImagesCount() ActivityService Class... ",e);
		}
	}
//2222
	public ActivityAttendanceInfoVO getActivityAttendenceDetails(SearchAttributeVO searchAttributeVO,Long stateId)
	{
		ActivityAttendanceInfoVO returnVO = new ActivityAttendanceInfoVO();
		try {
				activityAttendanceService.getCadreAttendanceCount(searchAttributeVO, returnVO, stateId);
				activityAttendanceService.getPublicAttendanceCount(searchAttributeVO, returnVO, stateId);
				activityAttendanceService.getPhotosCount(searchAttributeVO, returnVO, stateId);
		} catch (Exception e) {
			LOG.error(" Exception occured in getActivityAttendenceDetails() ActivityService Class... ",e);
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
	
	
	
	public List<ActivityAttendanceInfoVO> getActivityDayWiseCountsByLocationForAttendance(SearchAttributeVO searchAttributeVO,Long stateId){
		
		List<ActivityAttendanceInfoVO> finalList = new ArrayList<ActivityAttendanceInfoVO>();
		try {
			Map<String,ActivityAttendanceInfoVO> datesMap = new LinkedHashMap<String, ActivityAttendanceInfoVO>();
			Map<String,ActivityAttendanceInfoVO> initialDateMap = new LinkedHashMap<String, ActivityAttendanceInfoVO>();
			//Activity activity = activityScopeDAO.get(searchAttributeVO.getAttributesIdsList().get(0)).getActivity();
			ActivityScope activity = activityScopeDAO.get(searchAttributeVO.getAttributesIdsList().get(0));
			if(searchAttributeVO.getStartDate() != null && searchAttributeVO.getEndDate() != null)
				initialDateMap = commonMethodsUtilService.getDatesWiseCountsForAttendance(searchAttributeVO.getStartDate(), searchAttributeVO.getEndDate(), "Day");
			else
				initialDateMap = commonMethodsUtilService.getDatesWiseCountsForAttendance(activity.getStartDate(), activity.getEndDate(), "Day");

			Set<String> datesList = initialDateMap.keySet();
			List<String> availableDatesList  =null;
			if(datesList != null && datesList.size() > 0){
				availableDatesList = commonMethodsUtilService.getAvailableDates(datesList, activity.getStartDate().toString(), activity.getEndDate().toString());
			}
			SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
			try {
				datesMap = commonMethodsUtilService.getDatesWiseCountsForAttendance(sdf.parse(availableDatesList.get(0)), sdf.parse(availableDatesList.get(availableDatesList.size()-1)), "Day");
			} catch (Exception e) {
				datesMap = commonMethodsUtilService.getDatesWiseCountsForAttendance(activity.getStartDate(), activity.getEndDate(), "Day");
			}
			
			searchAttributeVO.getLocationIdsList().add(searchAttributeVO.getLocationId());
			
			 List<Object[]> list = activityLocationAttendanceDAO.getDayWiseActivityLocationsCount(searchAttributeVO,"cadre",stateId);
			 buildResultForAttendance(list,datesMap,"cadre",null);
			 
			 List<Object[]> list1 = activityLocationAttendanceDAO.getDayWiseActivityLocationsCount(searchAttributeVO,"public",stateId);
			 buildResultForAttendance(list1,datesMap,"public",null);
			
			 
			 if(datesMap != null && datesMap.size()>0)
				{
					for (String dateStr : datesMap.keySet()) {
						ActivityAttendanceInfoVO vo = datesMap.get(dateStr);
						//if(vo.getIvrcovered() != null && vo.getIvrcovered().longValue() > 0)
							finalList.add(vo);
					}
					 // Photos Count
					List<Object[]> list2 = activityInfoDocumentDAO.getDayWiseActivityInfoImagesCount(searchAttributeVO,stateId);
					 buildResultForPhotos(list2,finalList,"photos",null);
				}
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return finalList;
}

	@SuppressWarnings("null")
	public void buildResultForPhotos(List<Object[]> activitiesList,List<ActivityAttendanceInfoVO> finalList,String type,String extentionNo)
	{
		try {
			if(activitiesList != null && activitiesList.size()>0)
			{
				Map<String,ActivityVO> activityMap1 = null;
				for (Object[] params : activitiesList) {
					
					String locationId = commonMethodsUtilService.getStringValueForObject(params[4]);
					//Long areaId = Long.valueOf(locationId.trim());
					ActivityAttendanceInfoVO VO = getMatchedDay(finalList,new Long(locationId));
					if(VO != null)
					{
						if(type != null)
						{
								Long count = commonMethodsUtilService.getLongValueForObject(params[2]);
								if(type.trim().equalsIgnoreCase("photos")){
								 if(params[3] != null && params[3].toString().equalsIgnoreCase("WS"))
									 VO.setTotalInfoCellPhotosAttendance((Long)params[2] + VO.getTotalInfoCellPhotosAttendance());
								 else
									 VO.setTotalWebPhotosAttendance((Long)params[2] + VO.getTotalWebPhotosAttendance());
							}
							
						}
					}
				}
			}
			
		} catch (Exception e) {
			 LOG.error("Exception Occured in buildResultForPhotos() method, Exception - ",e);
		}
	}
	
	public ActivityAttendanceInfoVO getMatchedDay(List<ActivityAttendanceInfoVO> datesList,Long day)
	{
		if(datesList == null || datesList.size() == 0)
			return null;
		for(ActivityAttendanceInfoVO vo : datesList)
		{
			String date = vo.getName().substring(vo.getName().indexOf("(")+1,vo.getName().indexOf(")"));
			Long day1 = new Long(date.replaceAll("\\D+",""));
			if(day1.longValue() == day.longValue())
			{
				return vo;
			}
				
		}
		return null;
	}
	
@SuppressWarnings("null")
public void buildResultForAttendance(List<Object[]> activitiesList,Map<String,ActivityAttendanceInfoVO> datesMap,String type,String extentionNo)
{
	try {
		if(activitiesList != null && activitiesList.size()>0)
		{
			Map<String,ActivityVO> activityMap1 = null;
			for (Object[] params : activitiesList) {
				
				String locationId = commonMethodsUtilService.getStringValueForObject(params[4]);
				//Long areaId = Long.valueOf(locationId.trim());
				ActivityAttendanceInfoVO VO = datesMap.get(locationId);
				if(VO != null)
				{
					if(type != null)
					{
						
						Long count = commonMethodsUtilService.getLongValueForObject(params[2]);
						if(type.trim().equalsIgnoreCase("cadre")){
							if(params[3] != null && params[3].toString().equalsIgnoreCase("WS"))
							 {
								 VO.setTotalInfoCellCadreAttendance((Long)params[2] + VO.getTotalInfoCellCadreAttendance());
							
							 }
							 else
							 {	
								 VO.setTotalWebCadreAttendance((Long)params[2] + VO.getTotalWebCadreAttendance());
								
							 }
							 VO.setTotalMembers((Long)params[2] + VO.getTotalMembers());
						}
						
						if(type.trim().equalsIgnoreCase("public")){
							 if(params[3] != null && params[3].toString().equalsIgnoreCase("WS"))
							 {
								
									 VO.setTotalInfoCellPublicAttendance((Long)params[2] + VO.getTotalInfoCellPublicAttendance());
							 }
							 else
							 {
								 VO.setTotalWebPublicAttendance((Long)params[2] + VO.getTotalWebPublicAttendance());
							 }
							 VO.setTotalMembers((Long)params[2] + VO.getTotalMembers());
						}
						
						if(type.trim().equalsIgnoreCase("photos")){
							 if(params[3] != null && params[3].toString().equalsIgnoreCase("WS"))
								 VO.setTotalInfoCellPhotosAttendance((Long)params[2] + VO.getTotalInfoCellPhotosAttendance());
							 else
								 VO.setTotalWebPhotosAttendance((Long)params[2] + VO.getTotalWebPhotosAttendance());
						}
						
					}
				}
			}
		}
		
	} catch (Exception e) {
		 LOG.error("Exception Occured in segrigateFirstResult() method, Exception - ",e);
	}
}
	
	
	public List<ActivityVO> getActivityDayWiseCountsByLocation(SearchAttributeVO searchAttributeVO,Long stateId){
		
		List<ActivityVO> finalList = new ArrayList<ActivityVO>();
		try {
			

			
			
			Map<String,ActivityVO> datesMap = new LinkedHashMap<String, ActivityVO>();
			Map<String,ActivityVO> initialDateMap = new LinkedHashMap<String, ActivityVO>();
			//Activity activity = activityScopeDAO.get(searchAttributeVO.getAttributesIdsList().get(0)).getActivity();
			ActivityScope activity = activityScopeDAO.get(searchAttributeVO.getAttributesIdsList().get(0));
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
			
			searchAttributeVO.setTypeId(9999L);//dummy value
			searchAttributeVO.setConditionType("planned");
				plannedActivities = activityLocationInfoDAO.getActivityDayWiseCountsByLocation(searchAttributeVO,stateId);
			searchAttributeVO.setConditionType(" infocell ");
				infoCellconductedActivities = activityLocationInfoDAO.getActivityDayWiseCountsByLocation(searchAttributeVO,stateId); 
			searchAttributeVO.setConditionType("ivr");
				ivrconductedActivities = activityLocationInfoDAO.getActivityDayWiseCountsByLocation(searchAttributeVO,stateId); 

			searchAttributeVO.setConditionType("infocell");
				infoCellNotPlannedActivities = activityLocationInfoDAO.getActivityNotPlannedDayWiseCountsByLocation(searchAttributeVO,stateId); 	
			searchAttributeVO.setConditionType("ivr");
				ivrNotPlannedActivities = activityLocationInfoDAO.getActivityNotPlannedDayWiseCountsByLocation(searchAttributeVO,stateId); 
				List<Object[]> questionnairesCount =activityQuestionAnswerDAO.getActivityQuestionnairesCountsByDayWise(searchAttributeVO,stateId);
				List<Object[]> yesCount = activityQuestionAnswerDAO.getActivityQuestionnairesAttributeCountsByDayWise(searchAttributeVO,1L,stateId);
				 
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
				//buildResult(questionnairesCount,datesMap,"NO OF WHATSAPP IMAGES RECIEVED",null);	

			
			
			EventDocumentVO locationVo = new EventDocumentVO();
			locationVo.setLocationScope(getInputSearchType(searchAttributeVO.getSearchType()));
			locationVo.setLocationValue(searchAttributeVO.getLocationId());
			locationVo.setActivityId(searchAttributeVO.getAttributesIdsList().get(0));	
			if(searchAttributeVO.getStartDate() != null)
			locationVo.setStrDate(searchAttributeVO.getStartDate().toString());	
			if(searchAttributeVO.getEndDate() != null)
			locationVo.setEndDate(searchAttributeVO.getEndDate().toString());
			if(searchAttributeVO.getTypeId() == 9999L)
				locationVo.setTypeId(9999L);
			BasicVO countVO = cadreCommitteeService.getLocationsHierarchyForEvent(locationVo,"dayWise");
			Map<String,Long> dateWiseImageCountMap = countVO.getDayWiseMap();
			if(datesMap != null && datesMap.size()>0)
			{
				for (String dateStr : datesMap.keySet()) {
					ActivityVO vo = datesMap.get(dateStr);
					if(vo.getInfoCellcoveredPerc() == null)
						vo.setInfoCellcoveredPerc("-");
					if(vo.getInfoCellcovered() != null && vo.getInfoCellcovered().longValue() > 0 || 
							vo.getInfoCellNotPlanned() != null && vo.getInfoCellNotPlanned().longValue()>0 ||
							vo.getInfoCellTotal() != null && vo.getInfoCellTotal().longValue()>0)
						
						if(commonMethodsUtilService.isMapValid(dateWiseImageCountMap))
							vo.setImagesCount(dateWiseImageCountMap.get(dateStr.trim()));
					
						finalList.add(vo);
				}
			}
			/*if(commonMethodsUtilService.isListOrSetValid(finalList)){
				if(searchAttributeVO.getSearchType().equalsIgnoreCase(IConstants.STATE)){
					for(BasicVO vo1 : countVO.getLocationsList()){
						ActivityVO vo = finalList;
						Long count = vo.getImagesCount() != null ? vo.getImagesCount():0L;
						vo.setImagesCount(count+vo1.getCount());
					}
				}else{
					setImagesCount(countVO.getLocationsList(),returnVO.getActivityVoList());
				}
			}*/
			
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
					if(locationId != null && locationId.trim().length()==0)
						locationId = commonMethodsUtilService.getStringValueForObject(activity[5]);
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
								if(vo.getIvrcovered() != null && vo.getIvrcovered().longValue()>0L &&  vo.getIvrNotPlanned() != null &&  vo.getIvrNotPlanned().longValue()>0L)
								vo.setIvrTotal(vo.getIvrcovered() + vo.getIvrNotPlanned());
							}
							else if(type.trim().equalsIgnoreCase("INFO CELL COVERED"))
								vo.setInfoCellcovered(count);
							else if(type.trim().equalsIgnoreCase("INFO CELL COVERED %") && vo.getInfoCellcovered() != null && vo.getInfoCellcovered().longValue()>0L
									 &&vo.getPlannedCount() != null && vo.getPlannedCount().longValue()>0L){
								double perc = (vo.getInfoCellcovered()*100.0)/vo.getPlannedCount();
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
								if(vo.getInfoCellcovered() != null && vo.getInfoCellcovered().longValue()>0L && vo.getInfoCellNotPlanned() != null && vo.getInfoCellNotPlanned().longValue()>0L )
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
			UserAddress userAddress = null;
			if(eventFileUploadVO.getInsertType() == null || !eventFileUploadVO.getInsertType().trim().equalsIgnoreCase("WS"))
			{
				userAddress = saveUserAddressByLevelIdAndLevelValue(eventFileUploadVO.getLevelId(), eventFileUploadVO.getLevelValue());
			}
			
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
			
			
			String folderName = folderCreation();
			 
			
			Calendar calendar = Calendar.getInstance();
			calendar.setTime(new Date());
			 int year = calendar.get(Calendar.YEAR);
			 int month = calendar.get(Calendar.MONTH);
			 int day = calendar.get(Calendar.DAY_OF_MONTH);
			 int temp = month+1;
			 
			 StringBuilder pathBuilder = new StringBuilder();
			 StringBuilder str = new StringBuilder();
			 Integer randomNumber = RandomNumberGeneraion.randomGenerator(8);
				
			String destPath = folderName+"/"+randomNumber+"."+eventFileUploadVO.getFileExtension();
			
			if(eventFileUploadVO.getInsertType() != null && eventFileUploadVO.getInsertType().trim().equalsIgnoreCase("WS"))
			{   
				destPath = folderName+"/"+randomNumber+".jpg";
				 pathBuilder.append(year).append("/").append(temp).append("-").append(day).append("/").append(randomNumber).append(".")
				 .append("jpg");
				 str.append(randomNumber).append(".").append("jpg");
				activityDocument.setActivityDate(eventFileUploadVO.getActivityDateFormat());
				activityDocument.setTabDetailsId(eventFileUploadVO.getTabDetailsId());
				ImageAndStringConverter imageAndStringConverter = new ImageAndStringConverter();
				imageAndStringConverter.convertBase64StringToImage(eventFileUploadVO.getImageBase64String(), destPath);
			}
			else
			{
				 pathBuilder.append(year).append("/").append(temp).append("-").append(day).append("/").append(randomNumber).append(".")
				 .append(eventFileUploadVO.getFileExtension());
				 str.append(randomNumber).append(".").append(eventFileUploadVO.getFileExtension());
				 
				String fileCpyStts = copyFile(eventFileUploadVO.getFile().getAbsolutePath(),destPath);
				 
					if(fileCpyStts.equalsIgnoreCase("error")){
						resultStatus.setResultCode(ResultCodeMapper.FAILURE);
						LOG.error(" Exception Raise in copying file");
						throw new ArithmeticException();
					}
				 
			}
			
			activityDocument.setDocumentName(str.toString());
			activityDocument.setPath(pathBuilder.toString());
			activityDocument.setInsertedBy(eventFileUploadVO.getUserId());
			activityDocument.setUpdatedBy(eventFileUploadVO.getUpdatedBy());
			activityDocument.setActivityScopeId(eventFileUploadVO.getActivityScopeId());
			activityDocument.setInsertedTime(dateUtilService.getCurrentDateAndTime());
			activityDocument.setUpdatedTime(dateUtilService.getCurrentDateAndTime());
			if(eventFileUploadVO.getInsertType() != null && eventFileUploadVO.getInsertType().trim().equalsIgnoreCase("WS")){
				activityDocument.setTabDetailsId(eventFileUploadVO.getTabDetailsId());
				
				if(eventFileUploadVO.getActivityLocationInfoId() != null && eventFileUploadVO.getActivityLocationInfoId()>0L){
					ActivityLocationInfo activityLocationInfo = activityLocationInfoDAO.get(eventFileUploadVO.getActivityLocationInfoId());
					if(activityLocationInfo != null){
						activityDocument.setActivityScopeId(activityLocationInfo.getActivityScopeId());
					}
					else{
						activityDocument.setActivityScopeId(eventFileUploadVO.getActivityScopeId());
					}
				}
			}
			
			activityDocument = activityDocumentDAO.save(activityDocument);
			
		    ActivityInfoDocument activityInfoDocument = new ActivityInfoDocument();
		    
			if(eventFileUploadVO.getInsertType() != null && eventFileUploadVO.getInsertType().trim().equalsIgnoreCase("WS")){
				if(eventFileUploadVO.getActivityLocationInfoId() != null && eventFileUploadVO.getActivityLocationInfoId()>0L){
					activityInfoDocument.setActivityLocationInfoId(eventFileUploadVO.getActivityLocationInfoId());
					ActivityLocationInfo activityLocationInfo = activityLocationInfoDAO.get(eventFileUploadVO.getActivityLocationInfoId());
					if(activityLocationInfo != null){
						//activityInfoDocument.setLocationScopeId(activityLocationInfo.getLocationLevel());
						//activityInfoDocument.setLocationValueAddress(activityLocationInfo.getLocationValue());
						userAddress = activityLocationInfo.getAddress();//saveUserAddressByLevelIdAndLevelValue(activityLocationInfo.getLocationLevel(),activityLocationInfo.getLocationValue());
					}
				}
			}
		    
		    activityInfoDocument.setActivityDocument(activityDocument);
		    activityInfoDocument.setInsertedBy(eventFileUploadVO.getUserId());
		    activityInfoDocument.setUpdatedBy(eventFileUploadVO.getUpdatedBy());
			
		    activityInfoDocument.setInsertedTime(dateUtilService.getCurrentDateAndTime());
		    activityInfoDocument.setUpdatedTime(dateUtilService.getCurrentDateAndTime());
		    //activityInfoDocument.setActivityAddressId(userAddress.getUserAddressId());
		    
		    activityInfoDocument.setLocationScopeId(eventFileUploadVO.getLevelId());
			
		
		    
		    if(eventFileUploadVO.getTemp() != null && eventFileUploadVO.getTemp().equalsIgnoreCase("dayCalCulationReq"))
		    {
		    	//conducted Date
		    	Object[] actvtyDts = activityScopeDAO.getDatesForActivityScopeId(eventFileUploadVO.getActivityScopeId());
		    	Date activityStartDate = (Date) actvtyDts[0];
		    	if(activityStartDate != null){
		    		if(activityDate.equals(activityStartDate) || activityDate.after(activityStartDate)){
			    		long diff = activityDate.getTime()-activityStartDate.getTime();
			    		long dayDiff = diff / (24 * 60 * 60 * 1000);
			    		activityInfoDocument.setDay(Math.abs(dayDiff));
		    		}else{
		    			throw new ArithmeticException();
		    		}
		    	}
		    }
		    else
		    {
		    	ActivityScope activityScope = activityScopeDAO.get(eventFileUploadVO.getActivityScopeId());
				List<Date> betweenDatesList = null;
				Date startDate = activityScope.getStartDate();
				Date activitDate = activityDocument.getActivityDate();
		    	try {
		    		if(activitDate.equals(startDate) || (activitDate.after(startDate) && activitDate.before(activityScope.getEndDate()))
		    				 || activitDate.equals(activityScope.getEndDate())){
		    			betweenDatesList = commonMethodsUtilService.getBetweenDates(startDate, activitDate);
						List<Date>  activityRunningDatesList = commonMethodsUtilService.getBetweenDates(startDate, activityScope.getEndDate());
						
						if(commonMethodsUtilService.isListOrSetValid(activityRunningDatesList)){
							if(activityRunningDatesList.size()>=betweenDatesList.size())//no of activityRunningDatesList  count >= selected uploading images betweenDatesList  
								activityInfoDocument.setDay(Long.valueOf(String.valueOf(betweenDatesList.size())));
							else if(activityRunningDatesList.size()<betweenDatesList.size())// never no of activityRunningDatesList count < selected uploading images betweenDatesList
								activityInfoDocument.setDay(Long.valueOf(String.valueOf(activityRunningDatesList.size())));
						}
		    		}
				} catch (Exception e) {
					activityInfoDocument.setDay(eventFileUploadVO.getDay());
				}
		    }
		    
		    if(activityInfoDocument.getDay() == null || activityInfoDocument.getDay().longValue()==0L)// setting default day
		    	activityInfoDocument.setDay(1l);
		    
		    activityInfoDocument.setIsDeleted("N");
		    activityInfoDocument.setInsertType(eventFileUploadVO.getInsertType());
		    
		    Long levelValue = 0L;
		    if(activityInfoDocument.getLocationScopeId() != null){
		    	Long scopeId = activityInfoDocument.getLocationScopeId();
		    	if(scopeId != null && scopeId.longValue()>0L && eventFileUploadVO.getLevelValue() != null && eventFileUploadVO.getLevelValue().longValue()>0L){
		    		if(scopeId.longValue() == 6L || scopeId.longValue()== 8L || scopeId.longValue() == 7L || scopeId.longValue()== 5L || scopeId.longValue()== 9L )
		    			levelValue = Long.valueOf(eventFileUploadVO.getLevelValue().toString().substring(1));
		    	}
		    }
		    if(levelValue.longValue() ==0L)
		    	levelValue = eventFileUploadVO.getLevelValue();
		    activityInfoDocument.setLocationValueAddress(levelValue);
		    
		    
		    if(eventFileUploadVO.getTableName() != null){
		    	if(eventFileUploadVO.getTableName().equalsIgnoreCase("LocationInfo")){
		    		 activityInfoDocument.setActivityLocationInfoId(eventFileUploadVO.getActivityLocationInfoId());
		    		 ActivityLocationInfo activityLocationInfo = activityLocationInfoDAO.get(eventFileUploadVO.getActivityLocationInfoId());
		    		 if(activityLocationInfo != null)
		    			 userAddress = activityLocationInfo.getAddress();
		    	}else if(eventFileUploadVO.getTableName().equalsIgnoreCase("ConductedInfo")){
		    		 activityInfoDocument.setActivityConductedInfoId(eventFileUploadVO.getActivityLocationInfoId());
		    		 ActivityConductedInfo activityConductedInfo = activityConductedInfoDAO.get(eventFileUploadVO.getActivityLocationInfoId());
		    		 if(activityConductedInfo != null)
		    			 userAddress = activityConductedInfo.getAddress();
		    	}else{
		    		 activityInfoDocument.setActivityConductedInfoId(eventFileUploadVO.getActivityLocationInfoId());
		    		 ActivityConductedInfo activityConductedInfo = activityConductedInfoDAO.get(eventFileUploadVO.getActivityLocationInfoId());
		    		 if(activityConductedInfo != null)
		    			 userAddress = activityConductedInfo.getAddress();
		    	}
		    }
		    
		    activityInfoDocument.setActivityAddressId(userAddress.getUserAddressId());
		  /* List<Long> ids  = activityLocationInfoDAO.getActivityLocationInfoIdByLocationLevelAndLocationValue(eventFileUploadVO.getActivityScopeId(),eventFileUploadVO.getLevelId(), levelValue);
			if(ids != null && ids.size()>0){
				try {
					 activityInfoDocument.setActivityLocationInfoId(ids.get(0));
				} catch (Exception e) {}
			}else{
				List<Long> conductedIds  = activityConductedInfoDAO.getActivityLocationInfoIdByLocationLevelAndLocationValue(eventFileUploadVO.getActivityScopeId(),eventFileUploadVO.getLevelId(), levelValue);
				if(conductedIds != null && conductedIds.size()>0){
					try {
						 activityInfoDocument.setActivityConductedInfoId(conductedIds.get(0));
					} catch (Exception e) {}
				}	
			}*/
			/*else
			{
				ActivityLocationInfo activityLocationInfo = new ActivityLocationInfo();
				 
				if(eventFileUploadVO.getLevelId() != null && eventFileUploadVO.getLevelId().longValue() <= 9L)
				{
					Long constituencyId = getConstituencyId(eventFileUploadVO.getLevelId(),  Long.valueOf(eventFileUploadVO.getLevelValue().toString().substring(1)));
					if(constituencyId != null && constituencyId.longValue()>0L)
						activityLocationInfo.setConstituencyId(constituencyId);
				}
				else if(eventFileUploadVO.getLevelId() != null && eventFileUploadVO.getLevelId().longValue() == 13L){
					activityLocationInfo.setConstituencyId( Long.valueOf(eventFileUploadVO.getLevelValue().toString()));
				}
				activityLocationInfo.setActivityScopeId(eventFileUploadVO.getActivityScopeId());
				activityLocationInfo.setLocationLevel(eventFileUploadVO.getLevelId());
				if(activityLocationInfo.getLocationLevel() !=null )
				if(activityLocationInfo.getLocationLevel().longValue() == 6L || activityLocationInfo.getLocationLevel().longValue()== 8L || 
				activityLocationInfo.getLocationLevel().longValue() == 7L || activityLocationInfo.getLocationLevel().longValue()== 5L || 
				activityLocationInfo.getLocationLevel().longValue()== 9L )
					activityLocationInfo.setLocationValue( Long.valueOf(eventFileUploadVO.getLevelValue().toString().substring(1)));
				else
					activityLocationInfo.setLocationValue( Long.valueOf(eventFileUploadVO.getLevelValue().toString().trim()));
				
				activityLocationInfo.setInsertedBy(eventFileUploadVO.getUpdatedBy());
				activityLocationInfo.setUpdatedBy(eventFileUploadVO.getUpdatedBy());
				activityLocationInfo.setInsertionTime(dateUtilService.getCurrentDateAndTime());
				activityLocationInfo.setUpdatedTime(dateUtilService.getCurrentDateAndTime());
				SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
				try {
					activityLocationInfo.setConductedDate(formatter.parse(eventFileUploadVO.getEventDateStr() != null && eventFileUploadVO.getEventDateStr().trim().length()>0? eventFileUploadVO.getEventDateStr().toString() : ""));
				} catch (ParseException e) {
					LOG.error("Exception rised in saveActivityQuestionnaireDetails()",e);
				}
				ActivityLocationInfo tempactivityLocationInfo = activityLocationInfoDAO.save(activityLocationInfo);
				activityInfoDocument.setActivityLocationInfoId(tempactivityLocationInfo.getActivityLocationInfoId());
			}*/
		    //activityInfoDocument.setActivityLocationInfoId(eventFileUploadVO.getActivityLocationInfoId());
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
			 String mnthDir = staticPath+IConstants.ACTIVITY_DOCUMENTS+"/"+yr+"/"+mnth;
			 String mnthDirSts = createFolder(mnthDir);
			 if(!mnthDirSts.equalsIgnoreCase("SUCCESS")){
				 return "FAILED";
			 }
			 
			 return staticPath+IConstants.ACTIVITY_DOCUMENTS+"/"+yr+"/"+mnth;
			 
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
			    	theDir.getParentFile().mkdirs();
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
			
			if(eventFileUploadVO.getLevelId().equals(2l) || eventFileUploadVO.getLevelId().equals(10l)){
				userAddress.setState(stateDAO.get(eventFileUploadVO.getStateId()));
				levelId = 2l;
				levelValue = eventFileUploadVO.getStateId();
			}
			else if(eventFileUploadVO.getLevelId().equals(3l) || eventFileUploadVO.getLevelId().equals(11l))
			{
				userAddress.setState(stateDAO.get(eventFileUploadVO.getStateId()));
				userAddress.setDistrict(districtDAO.get(eventFileUploadVO.getDistrictId()));
				levelId = 3l;
				levelValue = eventFileUploadVO.getDistrictId();
			}
			else if(eventFileUploadVO.getLevelId().equals(4l) || eventFileUploadVO.getLevelId().equals(13l))
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
			if(levelId == 2l || levelId == 10l){
				userAddress = new UserAddress();
				userAddress.setState(stateDAO.get(levelValue));
				userAddress = userAddressDAO.save(userAddress);
			}
			else if(levelId == 3l || levelId == 11l){
				userAddress = new UserAddress();
				District distinct = districtDAO.get(levelValue);
				userAddress.setState(stateDAO.get(distinct.getState().getStateId()));
				userAddress.setDistrict(distinct);
				userAddress = userAddressDAO.save(userAddress);
			}
			else if(levelId == 4l || levelId == 13l){
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
					
					Long constituencyId = getConstituencyId(locationLevel, locationValue);
					if(constituencyId != null && constituencyId.longValue()>0L){
						List<Long> answerIds = activityQuestionAnswerDAO.getActivityQuestionnaireAnswerIdsList(finalvo.getActivityTypeId(),locationValue,constituencyId);
						if(answerIds != null && answerIds.size()>0)
							for (Long activityQuestionAnswerId : answerIds) {
								ActivityQuestionAnswer temp = activityQuestionAnswerDAO.get(activityQuestionAnswerId);
								temp.setIsDeleted("Y");
								activityQuestionAnswerDAO.save(temp);
							}
					}
					
					List<Long> ids  = activityLocationInfoDAO.getActivityLocationInfoIdByLocationLevelAndLocationValue(finalvo.getActivityTypeId(),locationLevel, locationValue);
					ActivityLocationInfo tempactivityLocationInfo =null;
					if(ids == null || ids.size()==0L)
					{
						ActivityLocationInfo activityLocationInfo = new ActivityLocationInfo();
							 
						if(locationLevel != null && locationLevel.longValue() <= 9L)
						{
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
						SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
						try {
							if(finalvo.getConductedDate() != null && finalvo.getConductedDate().toString().trim().length()>0)
								activityLocationInfo.setConductedDate(formatter.parse(finalvo.getConductedDate() != null ? finalvo.getConductedDate().toString() : ""));
						} catch (ParseException e) {
							LOG.error("Exception rised in saveActivityQuestionnaireDetails()",e);
						}
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
							char ch=(finalvo.getDateStr().charAt(4));
							activityQuestionAnswer.setDay(Long.parseLong(ch+""));
							String[] s= finalvo.getDateStr().split("\\(");
							if(s != null && s.length > 0){
								s[1].replace(")", "");
								try {
									activityQuestionAnswer.setActivityDate(new SimpleDateFormat("yyyy-MM-dd").parse(s[1]));
								} catch (ParseException e) {
									//e.printStackTrace();
								}
							}
							
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
			 constituencyIds = boothDAO.getConstituencyIdByTehsilId(
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
		  }	else if(locationLevel == 13l){
			  if(constituencyIds == null)
				  constituencyIds = new ArrayList<Long>();
			  	constituencyIds.add(locationValue);
		  }
		}catch (Exception e) {
			LOG.error(" Exception Occured in getConstituencyId() method, Exception - ",e);
		}
		if(constituencyIds!= null && constituencyIds.size() >0)
			constituencyId = constituencyIds.get(0);
		return constituencyId;
	}
	public ActivityVO getQuestionnaireForScope(Long scopeId,Long requiredAttributeId,Long questionId,Long optionId,List<Long> seletedDays){
		ActivityVO finalVO = new ActivityVO(); 
		try {
			LOG.info("Entered into getQuestionnaireForScope");
			List<Object[]> objList = null;
			if(requiredAttributeId == null || requiredAttributeId == 0l){
			 List<Long> questionnaireIds = activityDaywiseQuestionnaireDAO.getSelectedDayWiseQuestionWithOptions(scopeId, seletedDays);
			 List<ActivityVO> voList = new ArrayList<ActivityVO>();
			 if(questionnaireIds != null && questionnaireIds.size()>0){
					objList = activityQuestionnaireOptionDAO.getQuestionnaireForScope(scopeId,questionId,optionId,questionnaireIds);	
					List<Object[]> textBoxObjList = activityQuestionnaireOptionDAO.getTextboxQuestionaireForScope(scopeId,questionId,optionId,questionnaireIds);	
					if(objList != null)
						objList.addAll(textBoxObjList);
				}else{
					objList = activityQuestionnaireOptionDAO.getQuestionnaireForScopeAndRespondentTypeIds(scopeId, requiredAttributeId,questionnaireIds);
					List<Object[]> textBoxObjList = activityQuestionnaireOptionDAO.getTextBoxQuestionnaireForScopeAndRespondentTypeIds(scopeId, requiredAttributeId,questionnaireIds);
					if(objList != null)
						objList.addAll(textBoxObjList);
				}
			}
			 
			if(objList != null && objList.size() > 0){
				int number=0;
				for (Object[] objects : objList) {
					ActivityVO matchedVO = getMatchedQuestionVo(finalVO,(Long)objects[0]);
					number = number+1;
					if(matchedVO == null){
						matchedVO = new ActivityVO();
						matchedVO.setQuestionId(commonMethodsUtilService.getLongValueForObject(objects[0]));
						matchedVO.setQuestion(number+") "+commonMethodsUtilService.getStringValueForObject(objects[1]));
						matchedVO.setOptionTypeId(commonMethodsUtilService.getLongValueForObject(objects[2]));
						matchedVO.setOptionType(commonMethodsUtilService.getStringValueForObject(objects[3]));
						matchedVO.setRemarks(objects[6]!=null ? objects[6].toString():" ");
						matchedVO.setOrderNo(commonMethodsUtilService.getLongValueForObject(objects[7]));
						finalVO.getActivityVoList().add(matchedVO);
					}
					
					ActivityVO optionVO = new ActivityVO();
					optionVO.setOptionId(commonMethodsUtilService.getLongValueForObject(objects[4]));
					optionVO.setOption(commonMethodsUtilService.getStringValueForObject(objects[5].toString()));
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
			if(levelId.longValue() >= 5l && levelId.longValue() <= 9l){
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
							//actvty.setActivityLevelId(obj[8] != null ? Long.valueOf(obj[8].toString()):null);
							actvty.setActivityLevelId(4l);
							actvty.setStartDate(obj[9] != null ? obj[9].toString():"");
							actvty.setEndDate(obj[10] != null ? obj[10].toString():"");
							if(obj[16]!=null){
								actvty.setMaxFilesCount(Long.valueOf(obj[16].toString()));
							}else{
								actvty.setMaxFilesCount(IConstants.ACTIVITY_MAX_FILES_COUNT);
							}
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
	
	public List<String> getActivityScopeDates(Long activityScopeId){
		List<String> dates = new ArrayList<String>();
		Object[] obj = activityScopeDAO.getDatesForActivityScopeId(activityScopeId);
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
					vo.setLocationInfoId(obj[0] != null ?Long.valueOf(obj[0].toString()):0l);
					vo.setId(locationVal);
					if(locationLvlId.longValue() == 13l){
						constIds.add(locationVal);
						constMap.put(locationVal, vo);
					}
					else if(locationLvlId.longValue() == 11l){
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
			
			//get members attended Counts and photos uploded counts constituency wise
			SearchAttributeVO searchVO = new SearchAttributeVO();
			searchVO.setSearchType("constituency");
			searchVO.getAttributesIdsList().add(scopeId);
			searchVO.getLocationTypeIdsList().add(13l);
			searchVO.setLocationValue(0l);
			List<Long> locationIds = new ArrayList<Long>(Arrays.asList(1l,2l,3l,4l,5l,6l,7l,
					8l,9l,10l,11l,12l,13l,14l,15l,16l,17l,18l,19l,20l,21l,22l,23l));
			searchVO.getLocationIdsList().addAll(locationIds);
			
			ActivityAttendanceInfoVO activityAttendanceInfoVO = activityAttendanceService.getLocationWiseActivityDetails(searchVO,0L);//stateId = 0 --> ap & ts
			
			if(activityAttendanceInfoVO != null && activityAttendanceInfoVO.getSubList() != null && activityAttendanceInfoVO.getSubList().size() > 0 && constMap != null && constMap.size() > 0){
				for(ActivityAttendanceInfoVO vo : activityAttendanceInfoVO.getSubList()){
					BasicVO basicVo = constMap.get(vo.getId());
					basicVo.setActivityAttendanceInfoVO(vo);
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
	
	public ActivityVO getCanditeActivtyAttendanceLocationsDtls(Long cadreId,Long activityLevelId,Long activityScopeId,String statusCode){
		ActivityVO finalvo = new ActivityVO();
		try {
			 
			
			//ActivityVO actvitiesInfoVO = getActivityDetailsForCadre(cadreId);
			List<ActivityVO> locationInfoList = new ArrayList<ActivityVO>(0);
			List<Object[]> attendanceDtls = activityAttendanceDAO.getCanditeActivtyAttendanceLocationsDtls(cadreId,activityLevelId,activityScopeId);
			if(commonMethodsUtilService.isListOrSetValid(attendanceDtls)){
				for (Object[] param : attendanceDtls) {
					String activityName =commonMethodsUtilService.getStringValueForObject(param[0]);
					String activityLevel =commonMethodsUtilService.getStringValueForObject(param[1]);
					//Long activityLocationInfoId =commonMethodsUtilService.getLongValueForObject(param[4]);
					Long locationLevel =commonMethodsUtilService.getLongValueForObject(param[2]);
					Long  locationValue =commonMethodsUtilService.getLongValueForObject(param[3]);
					String activityDateStr =commonMethodsUtilService.getStringValueForObject(param[5]);
					ActivityVO vo = new ActivityVO();
					vo.setOptionType(activityLevel);
					vo.setName(activityName);
					vo.setConductedDate(activityDateStr);
					/*if(locationLevel != null && locationLevel.longValue() == 5L){
						String locationName = tehsilDAO.get(locationValue).getTehsilName();
						vo.setAttendendLocation(locationName);
					}
					else if(locationLevel != null && locationLevel.longValue() == 6L){
						String locationName = panchayatDAO.get(locationValue).getPanchayatName();
						vo.setAttendendLocation(locationName);
					}
					else if(locationLevel != null && (locationLevel.longValue() == 7L || locationLevel.longValue() == 9L)){
						String locationName = localElectionBodyDAO.get(locationValue).getName();
						vo.setAttendendLocation(locationName );
					}
					else if(locationLevel != null && (locationLevel.longValue() == 8L || locationLevel.longValue() == 14L || locationLevel.longValue() == 13L)){
						String locationName = constituencyDAO.get(locationValue).getName();
						vo.setAttendendLocation(locationName + "("+activityLevel+")");
					}
					else if(locationLevel != null && locationLevel.longValue() == 10L){
						String locationName = stateDAO.get(locationValue).getStateName();
						vo.setAttendendLocation(locationName);
					}
					else if(locationLevel != null && locationLevel.longValue() == 11L){
						String locationName = districtDAO.get(locationValue).getDistrictName();
						vo.setAttendendLocation(locationName);
					}
					*/
					
					 if(activityLevelId != null && activityLevelId.longValue() == 1L){
						if(locationLevel != null && (locationLevel.longValue() == 8L || locationLevel.longValue() == 14L || locationLevel.longValue() == 13L)){
							String locationName = constituencyDAO.get(locationValue).getName();
							vo.setAttendendLocation(locationName + "("+activityLevel+")");
						}else{
							String locationName = panchayatDAO.get(locationValue).getPanchayatName();
							vo.setAttendendLocation(locationName);
						}
					}
					else if(activityLevelId != null && activityLevelId.longValue() == 2L ){
						if(locationLevel != null && (locationLevel.longValue() == 7L || locationLevel.longValue() == 9L)){
							String locationName = localElectionBodyDAO.get(locationValue).getName();
							vo.setAttendendLocation(locationName );
						}else{
							String locationName = tehsilDAO.get(locationValue).getTehsilName();
							vo.setAttendendLocation(locationName);
						}
					}
					else  if(activityLevelId != null  && activityLevelId.longValue() == 5L){
						String locationName = constituencyDAO.get(locationValue).getName();
						vo.setAttendendLocation(locationName + "("+activityLevel+")");
					}
					else if(activityLevelId != null && activityLevelId.longValue() == 3L){
						String locationName = districtDAO.get(locationValue).getDistrictName();
						vo.setAttendendLocation(locationName);
					}
					else if(activityLevelId != null && activityLevelId.longValue() == 4L){
						String locationName = stateDAO.get(locationValue).getStateName();
						vo.setAttendendLocation(locationName);
					}
					
					
					
					locationInfoList.add(vo);
				}
			}
			
			if(commonMethodsUtilService.isListOrSetValid(locationInfoList)){
				finalvo.setActivityVoList(locationInfoList);
			}
		} catch (Exception e) {
			Log.error("Exception Occured in getCanditeActivtyAttendanceLocationsDtls method in ActivityService ",e);
		}
		return finalvo;
	}
	
	public ActivityVO getActivityDetailsForCadre(Long cadreId){
		ActivityVO finalvo = new ActivityVO();
		try {
			
			Map<Long,ActivityVO> activityMap = new LinkedHashMap<Long, ActivityVO>();
			List<Object[]> list = activityLevelDAO.actvityLvlOrder();
			if(list != null && list.size() > 0){
				for (Object[] obj : list) {
					ActivityVO vo = new ActivityVO();
					Long lvlId = (Long) (obj[0] != null ? obj[0]:0l);
					String level = obj[1] != null ? obj[1].toString():"";
					vo.setId(lvlId);
					vo.setName(level);
					activityMap.put(lvlId, vo);
				}
			}
			List<Object[]> cadreAddressDetls = tdpCadreDAO.getCadrAddressDetailsByCadred(cadreId);
			AddressVO userAddress1 = null;
			if(commonMethodsUtilService.isListOrSetValid(cadreAddressDetls)){
				userAddress1 = new AddressVO();
				if(commonMethodsUtilService.isListOrSetValid(cadreAddressDetls)){
					for (Object[] address : cadreAddressDetls) {
						
						userAddress1.setConstituencyId(commonMethodsUtilService.getLongValueForObject(address[5]));
						userAddress1.setConstituencyName(commonMethodsUtilService.getStringValueForObject(address[6]));
						
						userAddress1.setDistrictId(commonMethodsUtilService.getLongValueForObject(address[3]));
						userAddress1.setDistrictName(commonMethodsUtilService.getStringValueForObject(address[4]));
						
						userAddress1.setTehsilId(commonMethodsUtilService.getLongValueForObject(address[7]));
						userAddress1.setTehsilName(commonMethodsUtilService.getStringValueForObject(address[8]));
						
						userAddress1.setLocalElectionBodyId(commonMethodsUtilService.getLongValueForObject(address[13]));
						userAddress1.setLocalElectionBodyName(commonMethodsUtilService.getStringValueForObject(address[14]));
						
						userAddress1.setPanchaytId(commonMethodsUtilService.getLongValueForObject(address[11]));
						userAddress1.setPanchayatName(commonMethodsUtilService.getStringValueForObject(address[12]));
						
						userAddress1.setWardId(commonMethodsUtilService.getLongValueForObject(address[9]));
						userAddress1.setWardName(commonMethodsUtilService.getStringValueForObject(address[10]));
						
						//userAddress1.setParliamentId(commonMethodsUtilService.getLongValueForObject(address[]));
						//userAddress1.setParliamentName(commonMethodsUtilService.getStringValueForObject(address[]));
						
						userAddress1.setBoothId(commonMethodsUtilService.getLongValueForObject(address[16]));
						userAddress1.setPartNo(commonMethodsUtilService.getStringValueForObject(address[17]));
						
						userAddress1.setAreaTypeStr(commonMethodsUtilService.getStringValueForObject(address[15]));
						userAddress1.setId(commonMethodsUtilService.getLongValueForObject(address[20]));
						
						userAddress1.setStateId(commonMethodsUtilService.getLongValueForObject(address[21]));
						userAddress1.setStateName(commonMethodsUtilService.getStringValueForObject(address[22]));
					}
				}
			}
			
			List<Object[]> hasAttendanceActivitiesInfo = activityScopeDAO.getActivityLevelsDetails(IConstants.ACTIVITY_REQUIRED_ATTRIBUTE_IDS,userAddress1);
			if(commonMethodsUtilService.isListOrSetValid(hasAttendanceActivitiesInfo)){
				for (Object[] param : hasAttendanceActivitiesInfo) {
					Long lvelId = commonMethodsUtilService.getLongValueForObject(param[0]);
					ActivityVO vo = activityMap.get(lvelId);
					if(vo != null){
						if(vo.getHasAttendenceCount() == null)
							vo.setHasAttendenceCount(0L);
						vo.setHasAttendenceCount(vo.getHasAttendenceCount()+1L);
						vo.getActivityMap().put( commonMethodsUtilService.getStringValueForObject(param[1]), null);
					}
				}
			}
			
			List<Object[]> list1 = activityScopeDAO.getActivitiesScopeDetailsByAddress(userAddress1);
			if(list1 != null && list1.size() > 0){
				for (Object[] obj : list1) {
					Long id = (Long) (obj[0] != null ? obj[0]:0l);
					ActivityVO vo = activityMap.get(id);
					if(vo != null){
						vo.setTotalCount((Long) (obj[1] != null ? obj[1]:0l));
					}
				}
			}
			
			
			List<Object[]> conductedActivities = activityLocationInfoDAO.getActivityDetailsByAddressDetails(userAddress1);
			if(commonMethodsUtilService.isListOrSetValid(conductedActivities)){
				for (Object[] param : conductedActivities) {
					Long lvelId = commonMethodsUtilService.getLongValueForObject(param[2]);
					ActivityVO vo = activityMap.get(lvelId);
					if(vo != null){
						if(commonMethodsUtilService.isMapValid(vo.getActivityMap())){
							if(!vo.getActivityMap().keySet().contains(commonMethodsUtilService.getStringValueForObject(param[0]))){
								if(vo.getHasConductedCount() == null)
									vo.setHasConductedCount(0L);
								vo.setHasConductedCount(vo.getHasConductedCount()+1L);
							}
						}else{
							if(vo.getHasConductedCount() == null)
								vo.setHasConductedCount(0L);
							vo.setHasConductedCount(vo.getHasConductedCount()+1L);
						}
					}
				}
			}
			
			List<Object[]> attendanceDtls = activityAttendanceDAO.getCadreAttendanceDetls(cadreId);
			Map<Long,Map<String,Long>> attendaceMap = new HashMap<Long, Map<String,Long>>(0); 
			
			if(commonMethodsUtilService.isListOrSetValid(attendanceDtls)){
				for (Object[] param : attendanceDtls) {
					Long levelId = commonMethodsUtilService.getLongValueForObject(param[0]);
					String attendanceType = commonMethodsUtilService.getStringValueForObject(param[1]);
					Long count = commonMethodsUtilService.getLongValueForObject(param[2]);
					
					Map<String,Long> attendanceTypemap = new HashMap<String, Long>(0);
						if(attendaceMap.get(levelId) != null)
							attendanceTypemap = attendaceMap.get(levelId);
						
						attendanceTypemap.put(attendanceType, count);
						attendaceMap.put(levelId, attendanceTypemap);
					}
			}
			Map<Long,Long> otherAttendanceMap  = new HashMap<Long,Long>(0); 
		/*	List<Object[]> list2 = activityLocationAttendanceDAO.getActivityDetailsByTdpCadreId(cadreId);
			
			if(list2 != null && list2.size() > 0){
				for (Object[] obj : list2) {
					Long id = Long.parseLong(obj[0] != null ? obj[0].toString():"");
					Long count =0L;
					if(otherAttendanceMap.get(id) != null)
						count = otherAttendanceMap.get(id);
					
					otherAttendanceMap.put(id, (count+commonMethodsUtilService.getLongValueForObject(obj[1])));*/
					/*ActivityVO vo = activityMap.get(id);
					if(vo != null){
						vo.setAttendedCount(Long.parseLong(obj[1] != null ? obj[1].toString():""));
					}*/
				//}
			//}
			
			if(commonMethodsUtilService.isMapValid(attendaceMap)){
				for (Long levelId : attendaceMap.keySet()) {
					Map<String,Long> attendanceTypemap = attendaceMap.get(levelId);
					ActivityVO vo = activityMap.get(levelId);
					if(vo !=null && commonMethodsUtilService.isMapValid(attendanceTypemap)){
						Long otherAttendenceCount = otherAttendanceMap.get(levelId);
						if(otherAttendenceCount == null || otherAttendenceCount.longValue() == 0L)
							otherAttendenceCount= 0L;
						for (String attendenceTypeStr : attendanceTypemap.keySet()) {
							if(attendenceTypeStr.trim().equalsIgnoreCase("YES"))
								vo.setAttendedCount(otherAttendenceCount+attendanceTypemap.get(attendenceTypeStr));
							else if(attendenceTypeStr.trim().equalsIgnoreCase("NO"))
								vo.setAbscentCnt(attendanceTypemap.get(attendenceTypeStr));
						}
					}
				}
			}
			
			List<ActivityVO> voList = new ArrayList<ActivityVO> (activityMap.values());
			finalvo.setActivityVoList(voList);
			
			
		} catch (Exception e) {
			LOG.error("Exception Occured in getActivityDetailsForCadre method in ActivityService ",e);
		}
		return finalvo;
	}
	
	public ActivityVO getActivityDetailsByActivityLevelIdAndCadreId(Long activityLevelId,Long tdpCadreId,Long locationlevel,Long boothId,Long panchayatId,Long mandalId,Long constituencyId,Long districtId,Long stateId){
		ActivityVO activityvo = new ActivityVO();
		try {
			Map<Long,ActivityVO> activityMap = new LinkedHashMap<Long, ActivityVO>();
			List<Long> scopeIds = new ArrayList<Long>();
			List<Long> locationInfoIds = new ArrayList<Long>();
			
			List<Object[]> list = activityScopeDAO.getActivitiesListByLevelId(activityLevelId,IConstants.ACTIVITY_REQUIRED_ATTRIBUTE_IDS);
			if(list != null && list.size() > 0){
				for (Object[] obj : list) {
					ActivityVO vo = new ActivityVO();
					
					Long activityScopeId = (Long) (obj[0] != null ? obj[0]:0l);
					vo.setActivityScopeId(activityScopeId);
					vo.setId((Long) (obj[1] != null ? obj[1]:0l));
					vo.setName(obj[2] != null ? obj[2].toString():"");
					vo.setActivityLevelId((Long) (obj[3] != null ? obj[3]:0l));
					
					scopeIds.add(activityScopeId);
					activityMap.put(activityScopeId, vo);
				}
			}
			
			Long locationValue = 0l;
			if(locationlevel == 6l || locationlevel == 8l){
				locationValue = panchayatId;
			}
			else if(locationlevel == 5l || locationlevel == 7l || locationlevel == 9l){
				locationValue = mandalId;
			}
			else if(locationlevel == 0l){
				if(activityLevelId == 5l){
					locationlevel = 13l;
					locationValue = constituencyId;
				}
				else if (activityLevelId == 3l) {
					locationlevel = 11l;
					locationValue = districtId;
				}
				else if(activityLevelId == 4l){
					locationlevel = 10l;
					locationValue = stateId;
				}
			}
			
			List<Object[]> list1 = activityLocationInfoDAO.getActivityDetailsInCadreLocation(scopeIds, locationlevel, locationValue);
		    if(list1 != null && list1.size() > 0){
		    	for (Object[] obj : list1) {
					Long scopeId = (Long) (obj[1] != null ? obj[1]:0l);
					ActivityVO vo = activityMap.get(scopeId);
					if(vo != null){
						vo.setActivityLocationInfoId((Long) (obj[0] != null ? obj[0]:0l));
						vo.setPlannedDate(obj[2] != null ? obj[2].toString():"");
						vo.setConductedDate(obj[3] != null ? obj[3].toString():"");
						vo.setIvrStatus(obj[4] != null ? obj[4].toString():"");
						vo.setIsLocation("Y");
						
						locationInfoIds.add((Long) (obj[0] != null ? obj[0]:0l));
					}
				}
		    }
		
		    String locatiionName = null;
		    if(locationInfoIds != null && locationInfoIds.size() > 0){
		    	List<Object[]> list2 = activityLocationAttendanceDAO.getCadreAttendedActivityDetails(locationInfoIds, tdpCadreId);
			    if(list2 != null && list2.size() > 0){
			    	for (Object[] obj : list2) {
						Long activityScpId = (Long) (obj[1] != null ? obj[1]:0l);
						ActivityVO vo = activityMap.get(activityScpId);
						if(vo != null){
							Long count = 1l;
							if(vo.getAttendedCount() != null){
								count = count+1l;
								vo.setAttendedCount(count);
							}
							else{
								vo.setAttendedCount(count);
							}
							vo.setIsAttended("Y");
						}
					}
			    }
			    else{
			    	List<Long> activityInfoIds = activityLocationAttendanceDAO.getActivityInfoIdsByCadreIdFromAttendence(locationlevel,tdpCadreId);
			    	if(activityInfoIds != null && activityInfoIds.size() > 0){
			    		List<Object[]> locationNames = activityLocationInfoDAO.getActivityLocationNames(activityInfoIds,locationlevel);
			    		if(locationNames != null && locationNames.size()>0)
			    		{
			    			for (Object[] location : locationNames) {
			    				if(locatiionName != null && !locatiionName.isEmpty())
			    					locatiionName = locatiionName+","+commonMethodsUtilService.getStringValueForObject(location[1]);
			    				else
			    					locatiionName = commonMethodsUtilService.getStringValueForObject(location[1]);
			    				
			    				Long activityScpId = commonMethodsUtilService.getLongValueForObject(location[0]);
			    				ActivityVO vo = activityMap.get(activityScpId);
								if(vo != null){
									vo.setAttendendLocation(locatiionName);
								}
							}
			    		}
			    	}
			    }
		    }
		    
		   List<ActivityVO> activitiesList = new ArrayList<ActivityVO>(activityMap.values());
		   activityvo.setActivityVoList(activitiesList);
		} catch (Exception e) {
			LOG.error("Exception Occured in getActivityDetailsByActivityLevelIdAndCadreId method in ActivityService ",e);
		}
		return activityvo;
	}
	
	public List<IdNameVO> getAccessValuesOfUserId(Long userId,String type)
	{
		List<IdNameVO> idNameVoList = null;
		try {  
			List<Object[]> list = null;
			List<Object[]> accessConstituencyList = userConstituencyAccessInfoDAO.findByElectionScopeUser(1L,userId);
			if(accessConstituencyList != null && accessConstituencyList.size()>0)
			{
				List<Long> accessConstiIdsList = new ArrayList<Long>();
				for (Object[] consttuency : accessConstituencyList) {
					accessConstiIdsList.add(consttuency[0] != null ? Long.valueOf(consttuency[0].toString().trim()):0L);
				}
				if(accessConstiIdsList != null && accessConstiIdsList.size()>0)
				{
					List<Object[]> values =boothDAO.getWardsByConstituencies(accessConstiIdsList,IConstants.LATEST_PUBLICATION_DATE_ID);
					if(values != null && values.size() > 0)
					{
						idNameVoList = new ArrayList<IdNameVO>();
						for (Object[] obj : values) {
							Long wardId = commonMethodsUtilService.getLongValueForObject(obj[0]);
							String wardName = commonMethodsUtilService.getStringValueForObject(obj[1]);
							//String constiTuencyName = commonMethodsUtilService.getStringValueForObject(obj[3]);
							
							IdNameVO vo = new IdNameVO();
							vo.setId(wardId);
							vo.setName(wardName);
							idNameVoList.add(vo);
						}
					}
				}
			}else
			{
				list = userAccessLevelValueDAO.getAccessValuesByUser(userId,type);
				if(list != null && list.size()>0)
				{
					idNameVoList = new ArrayList<IdNameVO>();
					for (Object[] obj : list) {
						IdNameVO vo = new IdNameVO();
						vo.setId(Long.valueOf(obj[0] != null ? obj[0].toString():"0"));
						vo.setDistrictid(Long.valueOf(obj[1] != null ? obj[1].toString():"0"));
						vo.setName(obj[2] != null ? obj[2].toString():"");
						idNameVoList.add(vo);
					}
				}
			}
		} catch (Exception e) {
			LOG.error("Exception Occured in getActivityTypeList() method, Exception - ",e);
		}
		return idNameVoList;
	}
	
	public List<IdNameVO> getActivityStatusDetailsByScopeId(Long activityScopeId,Long questionsId){
		 List<IdNameVO> returnList =new ArrayList<IdNameVO>(0);
		try {
		//	List<Long>activityStatusQuestionnaireIdsList = activityStatusQuestionnaireDAO.getActivityStatusQuestionsListByActivityScopeId(activityScopeId);
			List<Long> activityStatusQuestionnaireIdsList = activityQuestionnaireDAO.getActivityStatusQuestionnaireIdByQuestionId(activityScopeId, questionsId);
			if(activityStatusQuestionnaireIdsList != null && activityStatusQuestionnaireIdsList.size()>0){
				Long questionId = activityStatusQuestionnaireIdsList.get(0);
				List<Object[]>  statusList = activityQuestionnaireDAO.getQuestionnareOptionsDetails(questionId);
				Map<Long, IdNameVO> statusVOMap = new LinkedHashMap<Long, IdNameVO>(0);
				
				IdNameVO totalVo = new IdNameVO();
				totalVo.setId(100l);
				totalVo.setName("Total");
				totalVo.setActualCount(0l);
				
				statusVOMap.put(100l, totalVo);
				if(statusList != null && statusList.size()>0){
					for (Object[] status : statusList) {
							Long id = commonMethodsUtilService.getLongValueForObject(status[0]);
							String name = commonMethodsUtilService.getStringValueForObject(status[1]);
							IdNameVO vo = new IdNameVO();
							vo.setId(id);
							vo.setName(name);
							vo.setActualCount(0L);
							
							statusVOMap.put(id, vo);
					}
					Long totalCount = 0l;
					List<Long> questionIDsList = new ArrayList<Long>(0);
					questionIDsList.add(questionId);
					List<Object[]> answeredOptionsDetails = activityQuestionAnswerDAO.getActivityQuestionAnswerCountReasonWise(questionIDsList);
					if(answeredOptionsDetails != null && answeredOptionsDetails.size()>0){
						for (Object[] status : answeredOptionsDetails) {
							Long id = commonMethodsUtilService.getLongValueForObject(status[0]);
							Long count = commonMethodsUtilService.getLongValueForObject(status[2]);
							totalCount = totalCount+count;
							IdNameVO  vo = statusVOMap.get(id);
							if(vo != null){
								vo.setActualCount(count);
							}
						}
					}
					IdNameVO totalVo1 = statusVOMap.get(100l);
					if(totalVo1 != null){
						totalVo1.setActualCount(totalCount);
					}
					
					
					
					if(statusVOMap != null && statusVOMap.size()>0){
						returnList.addAll(statusVOMap.values());
					}
				}
			
			}
		} catch (Exception e) {
			LOG.error("Exception Occured in getActivityStatusDetailsByScopeId() method, Exception - ",e);
		}
		return returnList;
	}
	
	public List<IdNameVO> getActivityStatusDetailsByScopeIdAndLocationValue(Long activityScopeId,Long constituencyId,String mandalId,String villageId,Long activityQuestionId){
		 List<IdNameVO> returnList =new ArrayList<IdNameVO>(0);
		try {
			//List<Long> activityStatusQuestionnaireIdsList = activityStatusQuestionnaireDAO.getActivityStatusQuestionsListByActivityScopeId(activityScopeId);
			List<Long> activityStatusQuestionnaireIdsList = new ArrayList<Long>(0); //activityQuestionnaireDAO.getActivityQuestionnaireIdByQuestionId(activityScopeId,activityQuestionId);
			activityStatusQuestionnaireIdsList.add(activityQuestionId);
			if(activityStatusQuestionnaireIdsList != null && activityStatusQuestionnaireIdsList.size()>0){
				Long questionId = activityStatusQuestionnaireIdsList.get(0);
				List<Object[]>  statusList = activityQuestionnaireDAO.getQuestionnareOptionsDetails(questionId);
				Map<Long, IdNameVO> statusVOMap = new LinkedHashMap<Long, IdNameVO>(0);
				if(statusList != null && statusList.size()>0){
					for (Object[] status : statusList) {
							Long id = commonMethodsUtilService.getLongValueForObject(status[0]);
							String name = commonMethodsUtilService.getStringValueForObject(status[1]);
							IdNameVO vo = new IdNameVO();
							vo.setId(id);
							vo.setName(name);
							vo.setActualCount(0L);
							
							statusVOMap.put(id, vo);
					}
					
					String searchType = null;
					Long searchValue = 0l;
					
					if(villageId != null && villageId.trim().toString().length() > 1){
						if(villageId.substring(0, 1).trim().equalsIgnoreCase("1")){
							searchType = "village";
							searchValue = Long.valueOf(villageId.substring(1).trim());
						}
						else if(villageId.substring(0, 1).trim().equalsIgnoreCase("2")){
							searchType = "ward";
							searchValue = Long.valueOf(villageId.substring(1).trim());
						}
					}
					else if(mandalId != null && mandalId.trim().toString().length() > 1){
						if(mandalId.substring(0, 1).trim().equalsIgnoreCase("1")){
							searchType = "muncipality";
							searchValue = Long.valueOf(mandalId.substring(1).trim());
						}
						else if(mandalId.substring(0, 1).trim().equalsIgnoreCase("2")){
							searchType = "mandal";
							searchValue = Long.valueOf(mandalId.substring(1).trim());
						}
					}
					else if(constituencyId != null && constituencyId.longValue() > 0l){
						searchType = "constituency";
						searchValue = constituencyId;
					}
					Long totalCount = 0l;
					List<Object[]> answeredOptionsDetails = activityQuestionAnswerDAO.getActivityQuestionAnswerCountByQuestionAndLocation(questionId, searchType, searchValue,activityScopeId);
					if(answeredOptionsDetails != null && answeredOptionsDetails.size()>0){
						for (Object[] status : answeredOptionsDetails) {
							Long id = commonMethodsUtilService.getLongValueForObject(status[0]);
							Long count = commonMethodsUtilService.getLongValueForObject(status[2]);
							totalCount = totalCount+count;
							IdNameVO  vo = statusVOMap.get(id);
							if(vo != null){
								vo.setActualCount(count);
							}
						}
					}
					if(statusVOMap != null && statusVOMap.size()>0){
						returnList.addAll(statusVOMap.values());
					}
					IdNameVO totalVo = new IdNameVO();
					totalVo.setId(totalCount);
					totalVo.setName("Total");
					totalVo.setActualCount(totalCount);
					
					returnList.add(0,totalVo);
				}
			
			}
		} catch (Exception e) {
			LOG.error("Exception Occured in getActivityStatusDetailsByScopeId() method, Exception - ",e);
		}
		return returnList;
	}
	
	public List<IdNameVO> getQuestions(Long scopeId){
		List<IdNameVO> voList = new ArrayList<IdNameVO>();
		try {
			List<Object[]> list = activityQuestionnaireDAO.getQuestionnareForScopeId(scopeId);
			if(list != null && list.size() > 0){
				for (Object[] obj : list) {
					IdNameVO vo = new IdNameVO();
					vo.setId(obj[0] != null ? Long.valueOf(obj[0].toString()):0l);
					vo.setName(obj[1] != null ? obj[1].toString():"");
					voList.add(vo);
				}
			}
		} catch (Exception e) {
			Log.error("Exception Occured in getQuestions method in ActivityService ",e);
		}
		return voList;
	}
	
	public List<IdNameVO> getOptionsForQuestion(Long questionId){
		List<IdNameVO> voList = new ArrayList<IdNameVO>();
		try {
			List<Object[]> list = activityQuestionnaireOptionDAO.getOptionsForQuestions(questionId);
			if(list != null && list.size() > 0){
				for (Object[] obj : list) {
					IdNameVO vo = new IdNameVO();
					vo.setId(obj[0] != null ? Long.valueOf(obj[0].toString()):0l);
					vo.setName(obj[1] != null ? obj[1].toString():"");
					voList.add(vo);
				}
			}
		} catch (Exception e) {
			Log.error("Exception Occured in getOptionsForQuestion method in ActivityService ",e);
		}
		return voList;
	}
	
	/**
	 * @author 		:Hymavathi
	 * @date   		:${27/04/2016}
	 * @param  		:Long 
	 * @returntype  :${List<IdNameVO>}
	 * @description :${To get Question By Sending param like scopeId, Here we are getting questions for activityScope}
	 */
	public List<IdNameVO> getQuestionsForReportType(Long activityScopeId){
		List<IdNameVO> returnList =new ArrayList<IdNameVO>();
		try { 
			Long activityId = activityScopeDAO.get(activityScopeId).getActivityId();
			//List<Object[]> qstns = activityQuestionnaireDAO.getQuestionIdsByScopeId(activityScopeId);
			List<Object[]> qstns = activityQuestionnaireDAO.getQuestionIdsByActivityId(activityId);
			if(qstns != null && qstns.size() >0){
				for(Object[] astn : qstns){
					IdNameVO vo = new IdNameVO();
					vo.setId(astn[0] != null ? Long.valueOf((Long)astn[0]) : 0l);
					vo.setName(astn[1] != null ? astn[1].toString() : "");
					returnList.add(vo);
				}
		}
		} catch (Exception e) {
			Log.error("Exception Occured in getQuestionsForReportType method in ActivityService ",e);
		}
		return returnList;
	}
	
	/**
	 * @author 		:Hymavathi
	 * @date   		:${27/04/2016}
	 * @param  		:Long ,Long , Long 
	 * @returntype  :${List<OptionsCountVo>}
	 * @description :${To get Option with  Count for each Question By param like district/constituency & questionId & scopeId}
	 */
	public List<OptionsCountVo> getOptionDetailsForQuestion(Long activityScopeId,Long reportType, Long qstnId){
		List<OptionsCountVo> returnList =new ArrayList<OptionsCountVo>();
		
		List<Object[]> qstns = activityQuestionAnswerDAO.getOptionsCountByScopId(activityScopeId,reportType,qstnId);
		List<Long> qusetionareId = activityQuestionnaireDAO.getQuestionareId(activityScopeId, qstnId);
		List<Object[]> optns = null;
		if(qusetionareId != null && qusetionareId.size() > 0){
		 optns = activityQuestionnaireOptionDAO.getOptionsByQuesttionareIds(qusetionareId.get(0));
		}
		if(qstns != null && qstns.size() >0 && optns != null && optns.size() >0 ){
			for(Object[] astn : qstns){
				OptionsCountVo vo = getMatchedVOForReportType(returnList,Long.valueOf((Long)astn[3]));
				if(vo == null){
						vo = new OptionsCountVo();
						vo.setConstincyId(commonMethodsUtilService.getLongValueForObject(astn[3]));
						vo.setConstincyName(commonMethodsUtilService.getStringValueForObject(astn[2]));
						vo.setOptionsList(setOptionsCount(optns));
						returnList.add(vo);
				}
				OptionsCountVo vo2 = getMatchedVOForReportType(vo.getOptionsList(),Long.valueOf((Long)astn[0]));
				if(vo2 != null){
						Long count = commonMethodsUtilService.getLongValueForObject(astn[4]);
						vo2.setCount(count != null ? count.intValue() : 0);
						//vo2.setOptionTypeId(astn[5] != null ? commonMethodsUtilService.getLongValueForObject(astn[5]) : 0l);// option TextCount
				}
			}
		}
		List<Object[]> optnCmnts = activityQuestionAnswerDAO.getOptionsCountByScopIdForComments(activityScopeId,reportType,qstnId);
		if(optnCmnts != null && optnCmnts.size() >0){
			for(Object[] optnCmt : optnCmnts){
				OptionsCountVo vo = getMatchedVOForReportType(returnList,Long.valueOf((Long)optnCmt[3]));
				if(vo != null){
				OptionsCountVo vo2 = getMatchedVOForReportType(vo.getOptionsList(),Long.valueOf((Long)optnCmt[0]));
				if(vo2 != null){
						vo2.setOptionTypeId(optnCmt[4] != null ? commonMethodsUtilService.getLongValueForObject(optnCmt[4]) : 0l);// option TextCount
				}
				}
			}
		}
		return returnList;
	}
	
	/**
	 * @author 		:Hymavathi
	 * @date   		:${27/04/2016}
	 * @param  		:List<OptionsCountVo> returnList, Long reportTypeId
	 * @returntype  :${OptionsCountVo}
	 * @description :${By sending parameter as Unique Id we are getting matched Vo}
	 */
	public OptionsCountVo getMatchedVOForReportType(List<OptionsCountVo> returnList, Long reportTypeId){
		if(returnList != null && returnList.size() >0){
			for(OptionsCountVo vo : returnList){
				if(vo != null && vo.getConstincyId().longValue() == reportTypeId.longValue()){
					return vo;
				}
			}
		}
		return null;
	}
	
	/**
	 * @author 		:Hymavathi
	 * @date   		:${28/04/2016}
	 * @param  		:List<Object[]>
	 * @returntype  :${List<OptionsCountVo>}
	 * @description :${Maintaining All Options Of One Question in a List as a Static template with count 0 }
	 */
	public List<OptionsCountVo> setOptionsCount(List<Object[]> optns){
		List<OptionsCountVo> returnList = new ArrayList<OptionsCountVo>();
		if(optns != null && optns.size() >0){
			for(Object[] optn : optns){
				OptionsCountVo vo = new OptionsCountVo();
				vo.setConstincyId(commonMethodsUtilService.getLongValueForObject(optn[0]));//optionId
				vo.setConstincyName(commonMethodsUtilService.getStringValueForObject(optn[1]));//optionName
				vo.setCount(0);
				vo.setOptionTypeId(0l);//option text count
				returnList.add(vo);
			}
		}
		return returnList;
	}
	public List<OptionsCountVo> getCommentDetails(Long activityScopeId,Long reportType, Long qstnId, Long levelId, Long reportTypeId){
		List<OptionsCountVo> returnList = new ArrayList<OptionsCountVo>();
		List<Object[]> villgeList = null;
		List<Object[]> wardList = null;
		List<Object[]> mandalList = null;
		List<Object[]> TownList = null;
		List<Object[]> urbanList = null;
		List<Object[]> constncyList = null;
		
		if(levelId != null && levelId.longValue()>0L){
			if(levelId.longValue() ==1L){
				 villgeList = activityQuestionAnswerDAO.getCommentDetails(activityScopeId,reportType,qstnId,6l,reportTypeId,IConstants.VILLAGE);
				 wardList = activityQuestionAnswerDAO.getCommentDetails(activityScopeId,reportType,qstnId,2l,reportTypeId,IConstants.WARD);
			}else if(levelId.longValue() ==2L){
				 mandalList = activityQuestionAnswerDAO.getCommentDetails(activityScopeId,reportType,qstnId,5l,reportTypeId,IConstants.MANDAL);
				 TownList = activityQuestionAnswerDAO.getCommentDetails(activityScopeId,reportType,qstnId,7l,reportTypeId,IConstants.TOWN);
				 urbanList = activityQuestionAnswerDAO.getCommentDetails(activityScopeId,reportType,qstnId,9l,reportTypeId,IConstants.URBAN);
			}else if(levelId.longValue() ==5L){
				 constncyList = activityQuestionAnswerDAO.getCommentDetails(activityScopeId,reportType,qstnId,13l,reportTypeId,IConstants.CONSTITUENCY);
			}
		}
		if(villgeList != null && villgeList.size()>0){
		setCommentDetails( villgeList, returnList,"village");
		}else if(wardList != null && wardList.size()>0){
			setCommentDetails( wardList, returnList,"ward");
		}else if(mandalList != null && mandalList.size()>0){
			setCommentDetails( mandalList, returnList,"mandal");
		}else if(TownList != null && TownList.size()>0){
			setCommentDetails( TownList, returnList,"town");
		}else if(urbanList != null && urbanList.size()>0){
			setCommentDetails( urbanList, returnList, "urban");
		}else if(constncyList != null && constncyList.size()>0){
			setCommentDetails( constncyList, returnList, "constituency");
		}
		return returnList;
	}
	public void setCommentDetails(List<Object[]> commentDetails, List<OptionsCountVo> returnList, String region){
		if(commentDetails != null && commentDetails.size()>0){
			for(Object[] commnt : commentDetails){
			OptionsCountVo vo = new OptionsCountVo();
			vo.setDistrictId(commnt[0] !=null ?commonMethodsUtilService.getLongValueForObject(commnt[0]) : 0l);
			vo.setDistrictName(commnt[1] !=null ?commonMethodsUtilService.getStringValueForObject(commnt[1]) : "");
			vo.setConstincyId(commnt[2] !=null ?commonMethodsUtilService.getLongValueForObject(commnt[2]) : 0l);
			vo.setConstincyName(commnt[3] !=null ?commonMethodsUtilService.getStringValueForObject(commnt[3]) : "");
			vo.setTehsilId(commnt[4] !=null ?commonMethodsUtilService.getLongValueForObject(commnt[4]) : 0l);
			vo.setTehsilName(commnt[5] !=null ?commonMethodsUtilService.getStringValueForObject(commnt[5]) : "");
			if(region == "town" || region == "urban"){
			vo.setPanchayatId(0l);
			vo.setPanchayatName("");
			}else{
				vo.setPanchayatId(commnt[6] !=null ?commonMethodsUtilService.getLongValueForObject(commnt[6]) : 0l);
				vo.setPanchayatName(commnt[7] !=null ?commonMethodsUtilService.getStringValueForObject(commnt[7]) : "");
			}
			vo.setOptnCommnt(commnt[8] !=null ?commonMethodsUtilService.getStringValueForObject(commnt[8]) : "");
		    returnList.add(vo);
			}
		}
	}
	
	public void updateAreasList(String areasStr,List<ActivityResponseVO> list){
		try {
			if(!areasStr.isEmpty()){
				String[] areaArr = areasStr.split(",");
				if(areaArr.length>0){
					for(int i=0;i<areaArr.length;i++){
						ActivityResponseVO vo = new ActivityResponseVO();
						vo.setId(Long.valueOf(areaArr[i].toString().substring(0, 1)));
						vo.setName(areaArr[i].toString().substring(1));
						list.add(vo);
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public ActivityResponseVO getActivityQuestionnnaireWiseReport(SearchAttributeVO searchVO){
		ActivityResponseVO responseVO = new ActivityResponseVO();
		try {
			//List<Long> activityLevelIds = new ArrayList<Long>(0);
			Map<Long,Long> activityMap = new HashMap<Long, Long>(0);
			//if(commonMethodsUtilService.isListOrSetValid(searchVO.getAttributesIdsList())){
			//	activityLevelIds.add(searchVO.getScopeId());
				Long activityId = activityScopeDAO.get(searchVO.getScopeId()).getActivityId();
				List<Object[]> activityScopDtls = activityScopeDAO.getActivityScopeIdByActivityAndLevelId(activityId);
				if(commonMethodsUtilService.isListOrSetValid(activityScopDtls)){
					for (Object[] actibity : activityScopDtls) {
						activityMap.put(commonMethodsUtilService.getLongValueForObject(actibity[0]), commonMethodsUtilService.getLongValueForObject(actibity[1]));
					}
				}
			//}
			Long searchTypeId = 3L;

			List<Map<Long,ActivityResponseVO>> activityLevelwiseList = new ArrayList<Map<Long,ActivityResponseVO>>(0);
			if(commonMethodsUtilService.isMapValid(activityMap)){
				for (Long actibityScopeId : activityMap.keySet()) {
					searchVO.setScopeId(actibityScopeId);
					Long activityLevelId = activityMap.get(actibityScopeId);
					Map<Long,ActivityResponseVO> LocationWiseMap = new HashMap<Long, ActivityResponseVO>(0);
					List<Long> locationTypeIds =new ArrayList<Long>(0);
					if(activityLevelId.longValue() == 1L){
						locationTypeIds.add(6L);
						locationTypeIds.add(8L);
					}
					else if(activityLevelId.longValue() == 2L){
						locationTypeIds.add(5L);
						locationTypeIds.add(7L);
					}
					
					searchVO.setLocationTypeIdsList(locationTypeIds);
					List<Object[]> questionDetls = activityQuestionnaireDAO.getActivityQuestionListByScope(actibityScopeId);
					Map<Long,List<ActivityResponseVO>> questionnaireOptionWiseCountMap = getActvityQuestionOptionDetailsByScopeId(actibityScopeId);
					Map<Long,Long> optionWiseCountMap = getActvityQuestionOptionsCountByScopeId(new ArrayList<Long>(questionnaireOptionWiseCountMap.keySet()));
					
					List<Long> activityScopeQuestionIdsLsit = new ArrayList<Long>(0);
					
					//List<Object[]> questionDetls = activityQuestionnaireDAO.getQuestionnareDetails(searchVO.getQuestionnaireIdsList());
					if(searchVO.getSearchType() != null && searchVO.getSearchType().trim().equalsIgnoreCase(IConstants.DISTRICT)){
						 searchTypeId = 3L;
						List<Object[]> locationsdtls = districtDAO.getDistrictIdAndNameByStateForStateTypeId(1L, searchVO.getScopeValue());
						if(commonMethodsUtilService.isListOrSetValid(locationsdtls))
							for (Object[] location : locationsdtls) {
								ActivityResponseVO locationVO = new ActivityResponseVO();
								locationVO.setId(commonMethodsUtilService.getLongValueForObject(location[0]));
								locationVO.setName(commonMethodsUtilService.getStringValueForObject(location[1]));
								
								if(activityLevelId.longValue() == 2L){//responseVO.setName("MANDAL/TOWN/DIVISION");
									updateAreasList("5MANDAL,7TOWN,9DIVISION",locationVO.getSublist1());}
								else if(activityLevelId.longValue() == 1L){//responseVO.setName("VILLAGE/WARD");
									updateAreasList("6VILLAGE,8WARD",locationVO.getSublist1());}
								else if(activityLevelId.longValue() == 1L){updateAreasList("13CONSTITUENCY",locationVO.getSublist1());}
								if(commonMethodsUtilService.isListOrSetValid(questionDetls))
									for (Object[] question : questionDetls) {
										ActivityResponseVO questionVO = new ActivityResponseVO(commonMethodsUtilService.getLongValueForObject(question[0]),commonMethodsUtilService.getStringValueForObject(question[3]));
										if(searchVO.getQuestionnaireIdsList().contains(questionVO.getId())){
											locationVO.getSublist2().add(questionVO);
											activityScopeQuestionIdsLsit.add(questionVO.getId());
										}
										
										if(questionnaireOptionWiseCountMap.get(questionVO.getId()) != null){
											List<ActivityResponseVO> optionsList = questionnaireOptionWiseCountMap.get(questionVO.getId());
											List<ActivityResponseVO> qstionOptionsList = new ArrayList<ActivityResponseVO>(0);
											if(commonMethodsUtilService.isListOrSetValid(optionsList))
												for (ActivityResponseVO optionVO : optionsList) 
													qstionOptionsList.add(new ActivityResponseVO(optionVO.getId(),optionVO.getName(),optionWiseCountMap.get(optionVO.getId())));
											questionVO.getSublist().addAll(qstionOptionsList);
												
										}
									}
								LocationWiseMap.put(locationVO.getId(),locationVO);
							}
					}else if(searchVO.getSearchType() != null && searchVO.getSearchType().trim().equalsIgnoreCase(IConstants.CONSTITUENCY)){
						 searchTypeId = 4L;
						List<Object[]> locationsdtls = constituencyDAO.getConstituenciesByStateId(1L, searchVO.getScopeValue());
						if(commonMethodsUtilService.isListOrSetValid(locationsdtls))
							for (Object[] location : locationsdtls) {
								ActivityResponseVO locationVO = new ActivityResponseVO();
								locationVO.setId(commonMethodsUtilService.getLongValueForObject(location[0]));
								locationVO.setName(commonMethodsUtilService.getStringValueForObject(location[1]));
								
								if(activityLevelId.longValue() == 2L){//responseVO.setName("MANDAL/TOWN/DIVISION");
									updateAreasList("5MANDAL,7TOWN,9DIVISION",locationVO.getSublist1());}
								else if(activityLevelId.longValue() == 1L){//responseVO.setName("VILLAGE/WARD");
									updateAreasList("6VILLAGE,8WARD",locationVO.getSublist1());}
								
								if(commonMethodsUtilService.isListOrSetValid(questionDetls))
									for (Object[] question : questionDetls) {
										ActivityResponseVO questionVO = new ActivityResponseVO();
										questionVO.setId(commonMethodsUtilService.getLongValueForObject(question[0]));
										questionVO.setName(commonMethodsUtilService.getStringValueForObject(question[3]));
										if(searchVO.getQuestionnaireIdsList().contains(questionVO.getId())){
											locationVO.getSublist2().add(questionVO);
											activityScopeQuestionIdsLsit.add(questionVO.getId());
										}
										
										if(questionnaireOptionWiseCountMap.get(questionVO.getId()) != null){
											List<ActivityResponseVO> optionsList = questionnaireOptionWiseCountMap.get(questionVO.getId());
											List<ActivityResponseVO> qstionOptionsList = new ArrayList<ActivityResponseVO>(0);
											if(commonMethodsUtilService.isListOrSetValid(optionsList))
												for (ActivityResponseVO optionVO : optionsList) 
													qstionOptionsList.add(new ActivityResponseVO(optionVO.getId(),optionVO.getName(),optionWiseCountMap.get(optionVO.getId())));
											questionVO.getSublist().addAll(qstionOptionsList);
										}
									}
								LocationWiseMap.put(locationVO.getId(),locationVO);
							}
					}
					/*if(commonMethodsUtilService.isListOrSetValid(LocationWiseMap.keySet()))
						responseVO.getSublist1().addAll(LocationWiseMap.values());*/
					if(commonMethodsUtilService.isListOrSetValid(LocationWiseMap.keySet())){
						searchVO.setLocationIdsList(new ArrayList<Long>(LocationWiseMap.keySet()));
						List<Object[]> locatinsAreasCount = locationInfoDAO.getLocationWiseTotalCounts(searchVO.getLocationTypeIdsList(),searchVO.getLocationIdsList(),searchTypeId);
						if(commonMethodsUtilService.isListOrSetValid(locatinsAreasCount))
							for (Object[] areaObj : locatinsAreasCount){
								Long locationId = commonMethodsUtilService.getLongValueForObject(areaObj[0]);
								ActivityResponseVO vo = LocationWiseMap.get(locationId);
								if(vo != null){
									ActivityResponseVO areaVO = getActivityResponseVOById(vo.getSublist1(),commonMethodsUtilService.getLongValueForObject(areaObj[2]));
									if(areaVO != null){
										areaVO.setTotalCount(commonMethodsUtilService.getLongValueForObject(areaObj[1])); // total mandals counts in locationId (districtId)
										areaVO.setPending(areaVO.getTotalCount());
										vo.setTotalCount(vo.getTotalCount()+areaVO.getTotalCount());
									}
								}
							}
					}
						
						List<Object[]> areaWiseUpdatedLocationsList = activityLocationInfoDAO.getLocationWiseUpdatedCountDetails(searchVO);
						if(commonMethodsUtilService.isListOrSetValid(areaWiseUpdatedLocationsList))
							 for (Object[] updatdObj : areaWiseUpdatedLocationsList){
								 Long locationId = commonMethodsUtilService.getLongValueForObject(updatdObj[0]);
								 Long levelId = commonMethodsUtilService.getLongValueForObject(updatdObj[1]);
								 ActivityResponseVO vo = LocationWiseMap.get(locationId);
									if(vo != null && commonMethodsUtilService.isListOrSetValid(vo.getSublist1())){
										 ActivityResponseVO areaVO = getActivityResponseVOById(vo.getSublist1(),levelId);
										 if(areaVO != null){
											 areaVO.setCalled(commonMethodsUtilService.getLongValueForObject(updatdObj[2]));
											 areaVO.setPending(Math.abs(areaVO.getTotalCount() - areaVO.getCalled()));
										 }
									}
							}
						
						List<Object[]> responseInfoList = activityQuestionAnswerDAO.getLocationWiseResponseDetails(searchVO,activityScopeQuestionIdsLsit);
						if(commonMethodsUtilService.isListOrSetValid(responseInfoList)){
							for (Object[] updatdObj : responseInfoList){
								 Long locationId = commonMethodsUtilService.getLongValueForObject(updatdObj[0]);
								 Long questionId = commonMethodsUtilService.getLongValueForObject(updatdObj[1]);
								 ActivityResponseVO vo = LocationWiseMap.get(locationId);
									if(vo != null && commonMethodsUtilService.isListOrSetValid(vo.getSublist2())){
										 ActivityResponseVO questionVO = getActivityResponseVOById(vo.getSublist2(),questionId);
										 if(questionVO != null){
											 questionVO.setTotalCount(commonMethodsUtilService.getLongValueForObject(updatdObj[2]));
										 }
									}
							}
						}
						searchVO.setLevelId(activityLevelId);
						searchVO.setId(activityId);
						List<Object[]> questionWiseCountList = activityQuestionAnswerDAO.getActivityLocationInfoByScope(searchVO,"Count Description Box");
						if(commonMethodsUtilService.isListOrSetValid(questionWiseCountList)){
							for (Object[] param : questionWiseCountList) {
									Long locationId = commonMethodsUtilService.getLongValueForObject(param[1]);
									Long questionId = commonMethodsUtilService.getLongValueForObject(param[0]);
									Long count = commonMethodsUtilService.getLongValueForObject(param[4]);
									ActivityResponseVO vo = LocationWiseMap.get(locationId);
									if(vo != null && commonMethodsUtilService.isListOrSetValid(vo.getSublist2())){
										 ActivityResponseVO questionVO = getActivityResponseVOById(vo.getSublist2(),questionId);
										 if(questionVO != null){
											 questionVO.setSumcount(count);
										 }
									}
							}
						}
						activityLevelwiseList.add(LocationWiseMap);
				}
			}
			
			if(commonMethodsUtilService.isListOrSetValid(activityLevelwiseList)){
				Map<Long,List<ActivityResponseVO>> returnMap = new LinkedHashMap<Long, List<ActivityResponseVO>>(0);
				for (Map<Long, ActivityResponseVO> map : activityLevelwiseList) {
					if(commonMethodsUtilService.isMapValid(map)){
						for (Long locationId : map.keySet()) {
							List<ActivityResponseVO> list = new ArrayList<ActivityResponseVO>(0);
							if(returnMap.get(locationId) != null){
								list = returnMap.get(locationId);
								if(commonMethodsUtilService.isListOrSetValid(list)){
									list.add(map.get(locationId));
								}
							}else{
								list.add(map.get(locationId));
								returnMap.put(locationId, list);
							}
						}
					}
				}
				if(commonMethodsUtilService.isMapValid(returnMap)){
					Map<Long,ActivityResponseVO> finalMap = new LinkedHashMap<Long, ActivityResponseVO>(0);
					for (Long locationId : returnMap.keySet()) {
						ActivityResponseVO returnVO = new ActivityResponseVO();
						List<ActivityResponseVO> list = returnMap.get(locationId);
						if(commonMethodsUtilService.isListOrSetValid(list)){
							for (ActivityResponseVO activityResponseVO : list) {
								if(activityResponseVO.getSublist1().get(0).getName().equalsIgnoreCase("VILLAGE")){
									activityResponseVO.setLevelStr("VILLAGE/WARD");
								}
								else if(activityResponseVO.getSublist1().get(0).getName().equalsIgnoreCase("MANDAL")){
									activityResponseVO.setLevelStr("MANDAL/TOWN/DIVISION");
								}
							}
								returnVO.getSublist().addAll(list);
						}
						finalMap.put(locationId, returnVO);
					}
					responseVO.getSublist().addAll(finalMap.values());
				}
			}
		} catch (Exception e) {
			Log.error("Exception Occured in getActivityQuestionnnaireWiseReport method in ActivityService ",e);
		}
		return responseVO;
	}
	
	public ActivityResponseVO getActivityResponseVOById(List<ActivityResponseVO> list, Long id){
		try {
			if(commonMethodsUtilService.isListOrSetValid(list))
				for (ActivityResponseVO vo : list)
					if(vo.getId().longValue() == id.longValue())
						return vo;
		} catch (Exception e) {
			Log.error("Exception Occured in getActivityResponseVOById method in ActivityService ",e);
		}
		return null;
	}
	
	public Map<Long,Long> getActvityQuestionOptionsCountByScopeId(List<Long> questionIDsList){
		Map<Long,Long> optionWiseCountMap = new HashMap<Long, Long>(0);
		try {
			if(commonMethodsUtilService.isListOrSetValid(questionIDsList)){
				List<Object[]> questionnairOptionWiseCounsList = activityQuestionAnswerDAO.getActivityQuestionAnswerCountReasonWise(questionIDsList);			
				if(commonMethodsUtilService.isListOrSetValid(questionnairOptionWiseCounsList)){
					for (Object[] param : questionnairOptionWiseCounsList) {
						Long optionId = commonMethodsUtilService.getLongValueForObject(param[0]);
						Long count = commonMethodsUtilService.getLongValueForObject(param[2]);
						optionWiseCountMap.put(optionId, count);
					}
				}
			}
		} catch (Exception e) {
			Log.error("Exception Occured in getActvityQuestionOptionsCountByScopeId method in ActivityService ",e);
		}
		return optionWiseCountMap;
	}
	
	public Map<Long,List<ActivityResponseVO>> getActvityQuestionOptionDetailsByScopeId(Long actibityScopeId){
		Map<Long,List<ActivityResponseVO>> questionnaireOptionWiseCountMap = new HashMap<Long, List<ActivityResponseVO>>(0);
		try {
			List<Long> scopeIdsList = new ArrayList<Long>(0);
			scopeIdsList.add(actibityScopeId);
			List<Object[]> questionOptionsLsit = activityQuestionnaireOptionDAO.getQuestionnaireOptionsDetailsOfScope(scopeIdsList);
			if(commonMethodsUtilService.isListOrSetValid(questionOptionsLsit))
				for (Object[] param : questionOptionsLsit) {
					Long questionnaireId = commonMethodsUtilService.getLongValueForObject(param[11]);
					List<ActivityResponseVO> optionsList = new ArrayList<ActivityResponseVO>(0);
					 
					if(questionnaireOptionWiseCountMap.get(questionnaireId) != null)
						optionsList =questionnaireOptionWiseCountMap.get(questionnaireId);
					if(param[5] != null && param[6] != null){
						ActivityResponseVO optionVO = new ActivityResponseVO();
						optionVO.setId(commonMethodsUtilService.getLongValueForObject(param[5]));
						optionVO.setName(commonMethodsUtilService.getStringValueForObject(param[6]));
						optionsList.add(optionVO);
						questionnaireOptionWiseCountMap.put(questionnaireId, optionsList);
					}
				}
		} catch (Exception e) {
			Log.error("Exception Occured in getActvityQuestionOoptionDetailsByScopeId method in ActivityService ",e);
		}
		return questionnaireOptionWiseCountMap;
	}
	public List<ActivityResponseVO> getActivityLocationInfoDetailsByActivityScope(Long activityLevel,Long activityScope,List<Long> questionIds){
		List<ActivityResponseVO> returnList = null;
		try {
			//Map<Long,ActivityResponseVO> questionMap = new LinkedHashMap<Long, ActivityResponseVO>();
			List<Object[]> optionTypeList = activityQuestionnaireDAO.getActivityQuestionOptionTypeList(questionIds);
			
			//Setting Questions Template To Map
			Map<Long,ActivityResponseVO> questionMap = setQuestionsTemplate(optionTypeList);
			
			if(optionTypeList != null && optionTypeList.size() > 0){
				for (Object[] obje : optionTypeList) {
					Long questId = Long.valueOf(obje[0] != null ? obje[0].toString():"0");
					String optionType = obje[2] != null ? obje[2].toString():"";
					Long activityId = activityScopeDAO.get(activityScope).getActivityId();
					List<Object[]> list = activityQuestionAnswerDAO.getActivityLocationInfoByScope(activityLevel, activityId, questId, optionType);
					if(list != null && list.size() > 0){
						for (Object[] obj : list) {
							Long quesId = Long.valueOf(obj[0] != null ? obj[0].toString():"0");
							Long districtId = Long.valueOf(obj[1] != null ? obj[1].toString():"0");
							Long count = Long.valueOf(obj[2] != null ? obj[2].toString():"0");
							String questionStr = obj[3] != null ? obj[3].toString():"";
							Long sumCount = 0l;
							if(optionType.equalsIgnoreCase("Count Description Box"))
								sumCount = Long.valueOf(obj[4] != null ? obj[4].toString():"0");
							
							ActivityResponseVO vo = questionMap.get(quesId);
							if(vo != null){
								if(districtId.longValue() >= 1l && districtId.longValue() <= 10l){   //ts
									vo.setTSCount(vo.getTSCount()+count);
									vo.setTotalCount(vo.getTotalCount()+count);
									if(optionType.equalsIgnoreCase("Count Description Box")){
										vo.setDesTSCount(vo.getDesTSCount()+sumCount);
										vo.setDesTotalCount(vo.getDesTotalCount()+sumCount);
									}
								}
								else if(districtId.longValue() >= 11l && districtId.longValue() <= 23l){   //ap
									vo.setAPCount(vo.getAPCount()+count);
									vo.setTotalCount(vo.getTotalCount()+count);
									if(optionType.equalsIgnoreCase("Count Description Box")){
										vo.setDesAPCount(vo.getDesAPCount()+sumCount);
										vo.setDesTotalCount(vo.getDesTotalCount()+sumCount);
									}
								}
							}
							else{
								vo = new ActivityResponseVO();
								vo.setQuestionId(quesId);
								vo.setQuestion(questionStr);
								if(districtId.longValue() >= 1l && districtId.longValue() <= 10l){   //ts
									vo.setTSCount(vo.getTSCount()+count);
									vo.setTotalCount(vo.getTotalCount()+count);
									if(optionType.equalsIgnoreCase("Count Description Box")){
										vo.setDesTSCount(vo.getDesTSCount()+sumCount);
										vo.setDesTotalCount(vo.getDesTotalCount()+sumCount);
									}
								}
								else if(districtId.longValue() >= 11l && districtId.longValue() <= 23l){   //ap
									vo.setAPCount(vo.getAPCount()+count);
									vo.setTotalCount(vo.getTotalCount()+count);
									if(optionType.equalsIgnoreCase("Count Description Box")){
										vo.setDesAPCount(vo.getDesAPCount()+sumCount);
										vo.setDesTotalCount(vo.getDesTotalCount()+sumCount);
									}
								}
								//questionMap.put(quesId, vo);
							}
						}
					}
				}
			}
			if(questionMap != null)
				returnList = new ArrayList<ActivityResponseVO>(questionMap.values());
		} catch (Exception e) {
			Log.error("Exception Occured in getActivityLocationInfoDetailsByActivityScope method in ActivityService ",e);
		}
		return returnList;
	}
	
	public Map<Long,ActivityResponseVO> setQuestionsTemplate(List<Object[]> optionTypeList){
		Map<Long,ActivityResponseVO> questionMap = new LinkedHashMap<Long, ActivityResponseVO>();
		try{
		if(optionTypeList != null && optionTypeList.size() > 0){
			for (Object[] obje : optionTypeList) {
				ActivityResponseVO vo = new ActivityResponseVO();
				vo.setQuestionId(obje[0] != null ? (Long)obje[0]:0l);
				vo.setQuestion(obje[3] != null ? obje[3].toString():"");
				vo.setTSCount(0l);
				vo.setDesTSCount(0l);
				vo.setAPCount(0l);
				vo.setTotalCount(0l);
				vo.setDesAPCount(0l);
				vo.setDesTotalCount(0l);
				questionMap.put(vo.getQuestionId(), vo);
			}
		}
	} catch (Exception e) {
		Log.error("Exception Occured in setQuestionsTemplate method in ActivityService ",e);
	}
		return questionMap;
	}

	public List<IdNameVO> getAllActivities(){
		List<IdNameVO> finalList = new ArrayList<IdNameVO>();
		try{
			
			List<Object[]> allActivities = activityDAO.getAllActivities();
			if(commonMethodsUtilService.isListOrSetValid(allActivities)){
				String[] setterPropertiesList = {"id","name"};
				finalList = (List<IdNameVO>) setterAndGetterUtilService.setValuesToVO(allActivities, setterPropertiesList, "com.itgrids.partyanalyst.dto.IdNameVO");
			}
			
		}catch (Exception e) {
			Log.error("Exception Occured in getAllActivities method in ActivityService ",e);
		}
		return finalList;
	}
	
	public List<IdNameVO> getAllActivityLevelsByActivity(Long activityId){
		List<IdNameVO> finalList = new ArrayList<IdNameVO>();
		try{
			//0.scopeId,1.levelId,2.level
			List<Object[]> objLevelList = activityScopeDAO.getActivityLevelAndScopeIdByActivity(activityId);
			if(commonMethodsUtilService.isListOrSetValid(objLevelList)){
				String[] setterPropertiesList = {"orderId","id","name"};
				finalList = (List<IdNameVO>) setterAndGetterUtilService.setValuesToVO(objLevelList, setterPropertiesList, "com.itgrids.partyanalyst.dto.IdNameVO");
			}			
		}catch (Exception e) {
			Log.error("Exception Occured in getAllActivityLevelsByActivity method in ActivityService ",e);
		}
		return finalList;
	}
	public ActivityVO getActivityQuestionaryOptionsByActivityDate(String activityDate,Long day,Long activityScopeId)
	{
		ActivityVO finalVO = new ActivityVO(); 
		try{
			Date date = null;
			SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
			if(activityDate != null && !activityDate.isEmpty())
				date = format.parse(activityDate);
			 List<Long> activityQuestionnaireIds = activityDaywiseQuestionnaireDAO.getActivityQuestionIds(date, day,activityScopeId);
			if(activityQuestionnaireIds != null && activityQuestionnaireIds.size() > 0)
			{
			 List<Object[]> questionOptionsList = activityQuestionnaireOptionDAO.getActivityQuestionOptions(activityQuestionnaireIds);
			 if(questionOptionsList != null && questionOptionsList.size() > 0)
			 {
			
						int number=0;
						for (Object[] objects : questionOptionsList) {
							ActivityVO matchedVO = getMatchedQuestionVo(finalVO,(Long)objects[0]);
							number = number+1;
							if(matchedVO == null){
								matchedVO = new ActivityVO();
								matchedVO.setQuestionId(commonMethodsUtilService.getLongValueForObject(objects[0]));
								matchedVO.setQuestion(number+") "+commonMethodsUtilService.getStringValueForObject(objects[1]));
								matchedVO.setOptionTypeId(commonMethodsUtilService.getLongValueForObject(objects[2]));
								matchedVO.setOptionType(commonMethodsUtilService.getStringValueForObject(objects[3]));
								matchedVO.setRemarks(objects[6]!=null ? objects[6].toString():" ");
								matchedVO.setOrderNo(commonMethodsUtilService.getLongValueForObject(objects[7]));
								finalVO.getActivityVoList().add(matchedVO);
							}
							
							ActivityVO optionVO = new ActivityVO();
							optionVO.setOptionId(commonMethodsUtilService.getLongValueForObject(objects[4]));
							optionVO.setOption(commonMethodsUtilService.getStringValueForObject(objects[5].toString()));
							matchedVO.getOptionsList().add(optionVO);
							
						}
					}
			 }
			 
			
			
		}
		catch(Exception e) {
			Log.error("Exception Occured in getActivityQuestionaryOptionsByActivityDate method in ActivityService ",e);
		}
		return finalVO;
	}
		
public List<ActivityResponseVO> getquestinaireForRetrieving(Long day){
		List<ActivityResponseVO> finalList = new ArrayList<ActivityResponseVO>();
		try{
			//0.actQueAnsId,1.quesId,3.optId
			List<Object[]> queAndOptList = activityQuestionAnswerDAO.getActivityQuesAndOptionsByDayWise(day);
			if(commonMethodsUtilService.isListOrSetValid(queAndOptList)){
				for (Object[] obj : queAndOptList) {
					ActivityResponseVO VO = new ActivityResponseVO();
					VO.setQuestionId(commonMethodsUtilService.getLongValueForObject(obj[1]));
					VO.setQuestion(commonMethodsUtilService.getStringValueForObject(obj[2]));
					VO.setId(commonMethodsUtilService.getLongValueForObject(obj[3]));
					VO.setName(commonMethodsUtilService.getStringValueForObject(obj[4]));
					finalList.add(VO);
				}
			}			
		}catch (Exception e) {
			Log.error("Exception Occured in getquestinaireForRetrieving method in ActivityService ",e);
		}
		return finalList;
	}
public List<ActivityResponseVO> getAllCallingPurpose(){
	List<ActivityResponseVO> finalList = new ArrayList<ActivityResponseVO>();
	try{

		List<Object[]> callPurposeList = callPurposeDAO.getAllCallPurposes();
		if(commonMethodsUtilService.isListOrSetValid(callPurposeList)){
			for (Object[] obj : callPurposeList) {
				ActivityResponseVO VO = new ActivityResponseVO();
				VO.setId(commonMethodsUtilService.getLongValueForObject(obj[0]));
				VO.setName(commonMethodsUtilService.getStringValueForObject(obj[1]));
				finalList.add(VO);
			}
		}			
	}catch (Exception e) {
		Log.error("Exception Occured in getAllCallingPurpose method in ActivityService ",e);
	}
	return finalList;
}
public List<ActivityResponseVO> getCallSuportType(){
	List<ActivityResponseVO> finalList = new ArrayList<ActivityResponseVO>();
	try{

		List<Object[]> callSupportList = callSupportTypeDAO.getCallSupportType();
		if(commonMethodsUtilService.isListOrSetValid(callSupportList)){
			for (Object[] obj : callSupportList) {
				ActivityResponseVO VO = new ActivityResponseVO();
				VO.setId(commonMethodsUtilService.getLongValueForObject(obj[0]));
				VO.setName(commonMethodsUtilService.getStringValueForObject(obj[1]));
				finalList.add(VO);
			}
		}			
	}catch (Exception e) {
		Log.error("Exception Occured in getCallSuuportType method in ActivityService ",e);
	}
	return finalList;
}	
public List<ActivityResponseVO> getCallStatus(){
	List<ActivityResponseVO> finalList = new ArrayList<ActivityResponseVO>();
	try{

		List<Object[]> callStatusList = callStatusDAO.getCallStatus();
		if(commonMethodsUtilService.isListOrSetValid(callStatusList)){
			for (Object[] obj : callStatusList) {
				ActivityResponseVO VO = new ActivityResponseVO();
				VO.setId(commonMethodsUtilService.getLongValueForObject(obj[0]));
				VO.setName(commonMethodsUtilService.getStringValueForObject(obj[1]));
				finalList.add(VO);
			}
		}			
	}catch (Exception e) {
		Log.error("Exception Occured in getCallStatus method in ActivityService ",e);
	}
	return finalList;
}	

public ResultStatus saveCallerFeedBackDetailsForCadre(Long callPurposeId,Long callStatusId,Long callSupportId,String description,Long cadreId,Long calledBy){
	LOG.info("Entered into the updateCallerFeedBackDetailsForCadre service method");
	ResultStatus resultStatus = new ResultStatus();
	try {
		CadreCallingFeedback CadreCalFeedback = new CadreCallingFeedback();
		CadreCalFeedback.setCallDescription(description);
		CadreCalFeedback.setCalledBy(calledBy);
		CadreCalFeedback.setCallPurposeId(callPurposeId);
		CadreCalFeedback.setCallStatusId(callStatusId);
		CadreCalFeedback.setCallSupportTypeId(callSupportId);
		CadreCalFeedback.setTdpCadreId(cadreId);
		CadreCalFeedback.setCallTime(dateUtilService.getCurrentDateAndTime());
		cadreCallingFeedbackDAO.save(CadreCalFeedback);
		resultStatus.setMessage("Success");
	} catch (Exception e) {
		LOG.info("Entered into the updateCallerFeedBackDetailsForCadre service method");
		resultStatus.setMessage("Error");
	}
	return resultStatus;
	
}

public Map<Long,Map<Long,Long>> getTotalLocationsDetailByLevelId(Long activityScopeId, Long levelId,Long regionScopeId){
	Map<Long,Map<Long,Long>> totalScopeLocationsMap = new HashMap<Long,Map<Long,Long>>(0);
	try {
	      SearchAttributeVO searchAttributeVO = new SearchAttributeVO();
	            searchAttributeVO.setScopeId(regionScopeId);//3L,4L,2L
	            
	            if(!commonMethodsUtilService.isListOrSetValid(searchAttributeVO.getLocationTypeIdsList()))
	              searchAttributeVO.setLocationTypeIdsList(new ArrayList<Long>(0));
		            if(levelId != null){
		              if(levelId.longValue() == 1L){
		                searchAttributeVO.getLocationTypeIdsList().add(6L);
		                searchAttributeVO.getLocationTypeIdsList().add(8L);
		              }
		              else if(levelId.longValue() == 2L){
		                searchAttributeVO.getLocationTypeIdsList().add(5L);
		                searchAttributeVO.getLocationTypeIdsList().add(7L);
		              }
		              else if(levelId.longValue() == 3L){
		                searchAttributeVO.getLocationTypeIdsList().add(3L);
		              }
		              else if(levelId.longValue() == 4L)
		                searchAttributeVO.getLocationTypeIdsList().add(2L);
		              else if(levelId.longValue() == 5L)
		                searchAttributeVO.getLocationTypeIdsList().add(4L);          
		            }
		            ActivityScope activityScope = activityScopeDAO.get(activityScopeId);
		            Long publicationDateId = 0l;
					if(activityScope != null)
					   publicationDateId = activityScope.getPublicationDateId();
		            List<Object[]> areasList  = locationInfoDAO.areaCountDetailsListByAreaIdsOnScope(searchAttributeVO,null,publicationDateId);
		            if(commonMethodsUtilService.isListOrSetValid(areasList)){
		              for (Object[] param : areasList) {
		                
		                Map<Long,Long> totalLocationsMap = new HashMap<Long, Long>();
		                Long count = 0L;
		                if(totalScopeLocationsMap.get(activityScopeId) != null){
		                  totalLocationsMap = totalScopeLocationsMap.get(activityScopeId);
		                  count=totalLocationsMap.get(commonMethodsUtilService.getLongValueForObject(param[2]));
		                }
		                if(count == null)
		                  count = 0L;
		                totalLocationsMap.put(commonMethodsUtilService.getLongValueForObject(param[2]), count+commonMethodsUtilService.getLongValueForObject(param[1]));
		                totalScopeLocationsMap.put(activityScopeId, totalLocationsMap);
		              }
		            }
	} catch (Exception e) {
		LOG.info("Entered into the getTotalLocationsDetailByLevelId service method");
		totalScopeLocationsMap = null;
	}
	return totalScopeLocationsMap;
}

public ActivityVO getActivitiesQuesDetails(Long activityId,Long activityScopeId,String startDateStr,String endDateStr){
  ActivityVO finalVO = new ActivityVO();
  try{
    
    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
    Date startDate = null;
    Date endDate = null;
    if(startDateStr != null && startDateStr.length() > 0 && endDateStr != null && endDateStr.length() > 0){
      startDate = sdf.parse(startDateStr);
      endDate = sdf.parse(endDateStr);
    }
    
    Map<Long,Map<Long,Long>> quesPerMap = new HashMap<Long, Map<Long,Long>>();
    Map<Long,Long> questionWiseCountMap = new HashMap<Long, Long>();
    List<Object[]>  quesPrscList = activityQuestionAnswerDAO.getQuestionsPerc(activityId,activityScopeId);
    
    if(quesPrscList != null && quesPrscList.size() > 0l){
      for (Object[] objects : quesPrscList) {
        Long scopeId = commonMethodsUtilService.getLongValueForObject(objects[0]);
        Long questionarieId = commonMethodsUtilService.getLongValueForObject(objects[1]);
        Long optionId = commonMethodsUtilService.getLongValueForObject(objects[2]);
        Long count = commonMethodsUtilService.getLongValueForObject(objects[3]);
        Map<Long,Long> optionMap = quesPerMap.get(questionarieId);
        //Map<Long,Long> optisMap = new HashMap<Long, Long😠);
        if(optionMap == null){
          optionMap = new LinkedHashMap<Long, Long>();
          optionMap.put(optionId, count);
          quesPerMap.put(questionarieId,optionMap);
        }else{
          Long optCount = optionMap.get(optionId);
          if(optCount == null){
            optionMap.put(optionId, count);
          }
        }
        
        if(questionWiseCountMap.get(questionarieId) != null){
          count = count+(questionWiseCountMap.get(questionarieId) != null?questionWiseCountMap.get(questionarieId):0L);
        }
        questionWiseCountMap.put(questionarieId, count);
      }
      
      
      List<Object[]> list =activityScopeDAO.getScopeNameByActivity(activityId,activityScopeId, startDate, endDate) ;
      Map<Long,Map<Long,Long>> totalScopeLocationsMap = new HashMap<Long,Map<Long,Long>>(0);
      List<Long> scopeIdList = new ArrayList<Long>();
      
      if(list != null && list.size() > 0l){
        for (Object[] objects : list) {
          ActivityVO vo = new ActivityVO();
            vo.setActivityScopeId(commonMethodsUtilService.getLongValueForObject(objects[0]));
            vo.setActivityLevelId(commonMethodsUtilService.getLongValueForObject(objects[1]));
            vo.setActivityLevelName(commonMethodsUtilService.getStringValueForObject(objects[2]));
            vo.setPercentage("0.00");
            vo.setRemainingPerc("100.00");
            finalVO.setId(commonMethodsUtilService.getLongValueForObject(objects[3]));//activityId
            finalVO.setName(commonMethodsUtilService.getStringValueForObject(objects[4]));//ActivityName
            finalVO.setPercentage("0.00");
            finalVO.setRemainingPerc("100.00");
            finalVO.getActivityVoList().add(vo);
            scopeIdList.add(vo.getActivityScopeId());
            
            Map<Long,Map<Long,Long>> temptotalScopeLocationsMap =  getTotalLocationsDetailByLevelId(vo.getActivityScopeId(),vo.getActivityLevelId(),commonMethodsUtilService.getLongValueForObject(objects[5]));
            if(commonMethodsUtilService.isMapValid(temptotalScopeLocationsMap))
              totalScopeLocationsMap.putAll(temptotalScopeLocationsMap);
        }
      }
      
      Map<Long,List<ActivityVO>> quesOptsMap = new HashMap<Long,List<ActivityVO>>();
      List<Object[]> questionOptList = activityQuestionnaireOptionDAO.getQuesAndOptionsByScopeIds(scopeIdList);
      if(questionOptList != null && questionOptList.size() >0l ){
        for (Object[] objects : questionOptList) {
          Long scopeId = commonMethodsUtilService.getLongValueForObject(objects[0]);
          Long qustionId = commonMethodsUtilService.getLongValueForObject(objects[1]);
          String question = commonMethodsUtilService.getStringValueForObject(objects[2]);
          Long optinId  = commonMethodsUtilService.getLongValueForObject(objects[3]);
          String option = commonMethodsUtilService.getStringValueForObject(objects[4]);
          Long qustionarieId = commonMethodsUtilService.getLongValueForObject(objects[1]);
          List<ActivityVO> qustList = quesOptsMap.get(scopeId);
          if(qustList == null || qustList.isEmpty()){
            qustList = new ArrayList<ActivityVO>();
            ActivityVO vo = new ActivityVO();
              vo.setQuestionId(qustionId);
              vo.setQuestion(question);
              vo.setActivityQuenaryId(qustionarieId);
              vo.setPercentage("0.00");
              vo.setRemainingPerc("100.00"); 
               
               if(commonMethodsUtilService.isMapValid(totalScopeLocationsMap)){
                      Map<Long,Long> locatnContMap = totalScopeLocationsMap.get(scopeId);
                      if(commonMethodsUtilService.isMapValid(locatnContMap)){
                        vo.setTotalCount(locatnContMap.get(commonMethodsUtilService.getLongValueForObject(objects[6])));
                  }    
                      
                      if(vo.getTotalCount() == null)
                    vo.setTotalCount(0L);
                  
                  if(vo.getTotalCount() != null &&vo.getTotalCount().longValue()>0L){
                    Long totalAnswerdCount = questionWiseCountMap.get(qustionarieId);
                    if(totalAnswerdCount != null &&totalAnswerdCount.longValue()>0L){
                      Double perc = (Double) (vo.getTotalCount()*100.0/totalAnswerdCount);
                      Float perc1 =Float.valueOf(perc.toString());
                      vo.setPercentage(commonMethodsUtilService.roundTo2DigitsFloatValueAsString(perc1).toString());
                    }
                  }
                  
                    }
               
              ActivityVO optionVO = new ActivityVO(); 
              optionVO.setOptionId(optinId);
              optionVO.setOption(option);
              optionVO.setPercentage("0.00");
              optionVO.setRemainingPerc("100.00"); 
              
              if(commonMethodsUtilService.isMapValid(totalScopeLocationsMap)){
                      Map<Long,Long> locatnContMap = totalScopeLocationsMap.get(scopeId);
                      if(commonMethodsUtilService.isMapValid(locatnContMap)){
                        vo.setTotalCount(locatnContMap.get(commonMethodsUtilService.getLongValueForObject(objects[6])));
                  }    
                      
                      if(vo.getTotalCount() == null)
                    vo.setTotalCount(0L);
                  
                  if(vo.getTotalCount() != null &&vo.getTotalCount().longValue()>0L){
                    Long totalAnswerdCount = questionWiseCountMap.get(qustionarieId);
                    if(totalAnswerdCount != null &&totalAnswerdCount.longValue()>0L){
                      Double perc = (Double) (vo.getTotalCount()*100.0/totalAnswerdCount);
                      Float perc1 =Float.valueOf(perc.toString());
                      vo.setPercentage(commonMethodsUtilService.roundTo2DigitsFloatValueAsString(perc1).toString());
                    }
                  }
                    }
              
              Map<Long,Long> optionsMap = new HashMap<Long, Long>();
              if(quesPerMap.get(qustionId) != null){
                optionsMap =quesPerMap.get(qustionId);
                optionVO.setCount(optionsMap.get(optinId));
                if(optionVO.getCount() == null)
                  optionVO.setCount(0L);
                
                if(optionVO.getCount() != null &&optionVO.getCount().longValue()>0L){
                  Long totalAnswerdCount = questionWiseCountMap.get(qustionarieId);
                  if(totalAnswerdCount != null &&totalAnswerdCount.longValue()>0L){
                    Double perc = (Double) (optionVO.getCount()*100.0/totalAnswerdCount);
                    Float perc1 =Float.valueOf(perc.toString());
                    optionVO.setPercentage(commonMethodsUtilService.roundTo2DigitsFloatValueAsString(perc1).toString());
                  }
                }
              }
              vo.getOptionsList().add(optionVO);
            qustList.add(vo);
            quesOptsMap.put(scopeId, qustList);
          }else{
            ActivityVO vo = getMatchQuesActivityVO(qustList,qustionId);
            if(vo != null){
              ActivityVO optionVO = new ActivityVO(); 
              optionVO.setOptionId(optinId);
              optionVO.setOption(option);
              
              Map<Long,Long> optionsMap = new HashMap<Long, Long>();
              if(quesPerMap.get(qustionId) != null){
                optionsMap =quesPerMap.get(qustionId);
                optionVO.setCount(optionsMap.get(optinId));
                if(optionVO.getCount() == null)
                  optionVO.setCount(0L);
                
                if(optionVO.getCount() != null &&optionVO.getCount().longValue()>0L){
                  Long totalAnswerdCount = questionWiseCountMap.get(qustionarieId);
                  if(totalAnswerdCount != null &&totalAnswerdCount.longValue()>0L){
                    Double perc = (Double) (optionVO.getCount()*100.0/totalAnswerdCount);
                    Float perc1 =Float.valueOf(perc.toString());
                    optionVO.setPercentage(commonMethodsUtilService.roundTo2DigitsFloatValueAsString(perc1).toString());
                  }
                }
                
              }
              
              vo.getOptionsList().add(optionVO);
              optionVO.setPercentage("0.00");
              optionVO.setRemainingPerc("100.00");
              //qustList.add(vo);
            }else{
              //qustList = new ArrayList<ActivityVO😠);
              ActivityVO vo1 = new ActivityVO();
                vo1.setQuestionId(qustionId);
                vo1.setQuestion(question);
                vo1.setActivityQuenaryId(qustionarieId);
                vo1.setPercentage("0.00");
                vo1.setRemainingPerc("100.00"); 
                
                if(commonMethodsUtilService.isMapValid(totalScopeLocationsMap)){
                        Map<Long,Long> locatnContMap = totalScopeLocationsMap.get(scopeId);
                        if(commonMethodsUtilService.isMapValid(locatnContMap)){
                          vo1.setTotalCount(locatnContMap.get(commonMethodsUtilService.getLongValueForObject(objects[6])));
                    }    
                        
                        if(vo1.getTotalCount() == null)
                      vo1.setTotalCount(0L);
                    
                    if(vo1.getTotalCount() != null &&vo1.getTotalCount().longValue()>0L){
                      Long totalAnswerdCount = questionWiseCountMap.get(qustionarieId);
                      if(totalAnswerdCount != null &&totalAnswerdCount.longValue()>0L){
                        Double perc = (Double) (vo1.getTotalCount()*100.0/totalAnswerdCount);
                        Float perc1 =Float.valueOf(perc.toString());
                        vo1.setPercentage(commonMethodsUtilService.roundTo2DigitsFloatValueAsString(perc1).toString());
                      }
                    }
                    
                      }
                
                ActivityVO optionVO = new ActivityVO(); 
                optionVO.setOptionId(optinId);
                optionVO.setOption(option);
                optionVO.setPercentage("0.00");
                optionVO.setRemainingPerc("100.00");
                
                Map<Long,Long> optionsMap = new HashMap<Long, Long>();
                if(quesPerMap.get(qustionId) != null){
                  optionsMap =quesPerMap.get(qustionId);
                  optionVO.setCount(optionsMap.get(optinId));
                  if(optionVO.getCount() == null)
                    optionVO.setCount(0L);
                  
                  if(optionVO.getCount() != null &&optionVO.getCount().longValue()>0L){
                	  Long totalAnswerdCount = questionWiseCountMap.get(qustionarieId);
                    if(totalAnswerdCount != null &&totalAnswerdCount.longValue()>0L){
                      Double perc = (Double) (optionVO.getCount()*100.0/totalAnswerdCount);
                      Float perc1 =Float.valueOf(perc.toString());
                      optionVO.setPercentage(commonMethodsUtilService.roundTo2DigitsFloatValueAsString(perc1).toString());
                    }
                  }
                }
                
                vo1.getOptionsList().add(optionVO);
              qustList.add(vo1);
            }
          }
        }
      }
      
      if(finalVO.getActivityVoList() != null && !finalVO.getActivityVoList().isEmpty()){
        for (ActivityVO vo : finalVO.getActivityVoList()) {
          Long scopeId = vo.getActivityScopeId();
           vo.setActivityVoList(quesOptsMap.get(scopeId));
        }
      }
      
    }
    
  }catch(Exception e){
    LOG.info("Entered into the getActivitiesDetails service method");
  }
  return finalVO;
}
public ActivityVO getMatchQuesActivityVO(List<ActivityVO> questionList,Long queId)
{
	ActivityVO returnVO = null;
	try {
		if(questionList != null && questionList.size()>0)
		{
			for (ActivityVO activityVO : questionList) {
				if(activityVO.getQuestionId() == queId)
					return activityVO;
			}
		}
	} catch (Exception e) {
		 LOG.error("Exception Occured in getMatchQuesActivityVO() method, Exception - ",e);
	}
	return returnVO;
}

public List<ActivityVO> getDistrictNamesByScopeId(Long activityScopeId,Long activityMemberId,Long stateId,Long userTypeId,String startDate, String endDate){
	List<ActivityVO> returnList = new ArrayList<ActivityVO>();
	try{
		SimpleDateFormat format =  new SimpleDateFormat("dd-MM-yyyy");
		Date stDate = null;
		Date edDate = null;
		if(startDate != null && endDate != null && !startDate.isEmpty() && !endDate.isEmpty()  ){
			stDate = format.parse(startDate);
			edDate = (Date)format.parse(endDate);
		}
		
		
		List<Object[]> locationsInfodocsDetals = activityInfoDocumentDAO.getDistrictNamesLocationsInfocoveredLocationsByScopeId(activityScopeId,stateId,stDate,edDate);
		Map<Long,Long> imagescoverdMap = new HashMap<Long, Long>(0);
		
		if(commonMethodsUtilService.isListOrSetValid(locationsInfodocsDetals)){
			for (Object[] param : locationsInfodocsDetals) {
				imagescoverdMap.put(commonMethodsUtilService.getLongValueForObject(param[0]), commonMethodsUtilService.getLongValueForObject(param[2]));
			}
		}
		else{
			locationsInfodocsDetals = activityInfoDocumentDAO.getDistrictNamesConductedInfocoveredLocationsByScopeId(activityScopeId,stateId,stDate,edDate);
			if(commonMethodsUtilService.isListOrSetValid(locationsInfodocsDetals)){
				for (Object[] param : locationsInfodocsDetals) {
					imagescoverdMap.put(commonMethodsUtilService.getLongValueForObject(param[0]), commonMethodsUtilService.getLongValueForObject(param[2]));
				}
			}
		}
		List<Object[]> districtCountList = activityInfoDocumentDAO.getDistrictNamesByScopeId(activityScopeId,stateId,stDate,edDate);
		if(districtCountList != null && districtCountList.size() > 0l){
			for (Object[] objects : districtCountList) {
				ActivityVO vo = new ActivityVO();
				 vo.setDistrictId(commonMethodsUtilService.getLongValueForObject(objects[0]));
				 vo.setName(commonMethodsUtilService.getStringValueForObject(objects[1]));
				 vo.setCount(commonMethodsUtilService.getLongValueForObject(objects[2]));
				 vo.setImagesCnt(imagescoverdMap.get(vo.getDistrictId()));
				 returnList.add(vo);
			}
		}
	}catch(Exception e){
		 LOG.error("Exception Occured in getDistrictNamesByScopeId() method, Exception - ",e);
	}
	return returnList;
}

public List<ActivityVO> getConstByDistrictId(Long activityScopeId,Long districtId,String fromDate, String toDate){
	List<ActivityVO> returnList = new ArrayList<ActivityVO>();
	try{
		SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
		Date stDate = null;
		Date ndDate = null;
		if(fromDate != null && fromDate.length() > 0 && toDate != null && toDate.length() > 0){
			stDate = sdf.parse(fromDate);
			ndDate = sdf.parse(toDate);
		}
		
		List<Object[]> locationsInfodocsDetals = activityInfoDocumentDAO.getConstituencyNamesLocationsInfocoveredLocationsByScopeId(activityScopeId,districtId,stDate,ndDate);
		Map<Long,Long> imagescoverdMap = new HashMap<Long, Long>(0);
		
		if(commonMethodsUtilService.isListOrSetValid(locationsInfodocsDetals)){
			for (Object[] param : locationsInfodocsDetals) {
				imagescoverdMap.put(commonMethodsUtilService.getLongValueForObject(param[0]), commonMethodsUtilService.getLongValueForObject(param[2]));
			}
		}		
		
		
		List<Object[]> cntuencyCountList = activityInfoDocumentDAO.getConstituencyNamesByDistrictId(activityScopeId,districtId,stDate,ndDate);
		if(cntuencyCountList != null && cntuencyCountList.size() > 0l){
			for (Object[] objects : cntuencyCountList) {
				
				ActivityVO vo = new ActivityVO();
				 vo.setConstituencyId(commonMethodsUtilService.getLongValueForObject(objects[0]));
				 vo.setName(commonMethodsUtilService.getStringValueForObject(objects[1]));
				 vo.setCount(commonMethodsUtilService.getLongValueForObject(objects[2]));
				 
				 if(vo.getCount() == null || vo.getCount().longValue()==0L)
					 vo.setCount(commonMethodsUtilService.getLongValueForObject(objects[3]));
				 
				 vo.setImagesCnt(imagescoverdMap.get(commonMethodsUtilService.getLongValueForObject(objects[0])));
				 returnList.add(vo);
				 
			}
		}
	}catch(Exception e){
		 LOG.error("Exception Occured in getConstByDistrictId() method, Exception - ",e);
	}
	return returnList;
}
public List<ActivityVO> getMandOrMuncByconstituencyId(Long activityScopeId,Long constituencyId,String fromDate,String toDate){
	List<ActivityVO> returnList = new ArrayList<ActivityVO>();
	try{
		SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
		Date stDate = null;
		Date ndDate = null;
		if(fromDate != null && fromDate.length() > 0 && toDate != null && toDate.length() > 0){
			stDate = sdf.parse(fromDate);
			ndDate = sdf.parse(toDate);
		}
		
		List<Object[]> locationInfodocsDetals = activityInfoDocumentDAO.getMandalNamesLocationsInfocoveredLocationsByScopeId(activityScopeId,constituencyId,stDate,ndDate);
		Map<Long,Long> mandalImagescoverdMap = new HashMap<Long, Long>(0);
		
		if(commonMethodsUtilService.isListOrSetValid(locationInfodocsDetals)){
			for (Object[] param : locationInfodocsDetals) {
				mandalImagescoverdMap.put(commonMethodsUtilService.getLongValueForObject(param[0]), commonMethodsUtilService.getLongValueForObject(param[2]));
			}
		}
		
		List<Object[]> mandalCuntList = activityInfoDocumentDAO.getMandalNamesByConstiencyId(activityScopeId,constituencyId,stDate,ndDate);
		if(mandalCuntList != null && mandalCuntList.size() > 0l){
			for (Object[] objects : mandalCuntList) {
				ActivityVO vo = new ActivityVO();
				 vo.setMandalId(Long.valueOf("2"+commonMethodsUtilService.getLongValueForObject(objects[0])));
				 vo.setName(commonMethodsUtilService.getStringValueForObject(objects[1]));
				 vo.setCount(commonMethodsUtilService.getLongValueForObject(objects[2]));
				 vo.setImagesCount(mandalImagescoverdMap.get(commonMethodsUtilService.getLongValueForObject(objects[0])));
				 returnList.add(vo);
			}
		}
		
		List<Object[]> locationsInfodocsDetals = activityInfoDocumentDAO.getMuncipalityNamesLocationsInfocoveredLocationsByScopeId(activityScopeId,constituencyId,stDate,ndDate);
		Map<Long,Long> muncipalityimagescoverdMap = new HashMap<Long, Long>(0);
		
		if(commonMethodsUtilService.isListOrSetValid(locationsInfodocsDetals)){
			for (Object[] param : locationsInfodocsDetals) {
				muncipalityimagescoverdMap.put(commonMethodsUtilService.getLongValueForObject(param[0]), commonMethodsUtilService.getLongValueForObject(param[2]));
			}
		}
		
		List<Object[]> muncipuntList = activityInfoDocumentDAO.getMuncipalityNamesByConstiencyId(activityScopeId,constituencyId,stDate,ndDate);
		if(muncipuntList != null && muncipuntList.size() > 0l){
			for (Object[] objects : muncipuntList) {
				ActivityVO vo = new ActivityVO();
				 vo.setMandalId(Long.valueOf("1"+commonMethodsUtilService.getLongValueForObject(objects[0])));
				 vo.setName(commonMethodsUtilService.getStringValueForObject(objects[1])+ " " +commonMethodsUtilService.getStringValueForObject(objects[3]));
				 vo.setCount(commonMethodsUtilService.getLongValueForObject(objects[2]));
				 vo.setImagesCount(muncipalityimagescoverdMap.get(commonMethodsUtilService.getLongValueForObject(objects[0])));
				 returnList.add(vo);
			}
		}
	}catch(Exception e){
		 LOG.error("Exception Occured in getMandOrMuncByconstituencyId() method, Exception - ",e);
	}
	return returnList;
}

public List<ActivityVO> getPanchayatOrWardsByMandalOrMuncId(Long activityScopeId,Long mandalOrMuncId,String fromDate,String toDate){
	List<ActivityVO> returnList = new ArrayList<ActivityVO>();
	try{
		SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
		Date stDate = null;
		Date ndDate = null;
		if(fromDate != null && fromDate.length() > 0 && toDate != null && toDate.length() > 0){
			stDate = sdf.parse(fromDate);
			ndDate = sdf.parse(toDate);
		}
		
		List<Object[]> locationInfodocsDetals = activityInfoDocumentDAO.getPanchaytNamesLocationsInfocoveredLocationsByScopeId(activityScopeId,Long.valueOf(mandalOrMuncId.toString().substring(1)),stDate,ndDate);
		Map<Long,Long> panchayatimagescoverdMap = new HashMap<Long, Long>(0);
		
		if(commonMethodsUtilService.isListOrSetValid(locationInfodocsDetals)){
			for (Object[] param : locationInfodocsDetals) {
				panchayatimagescoverdMap.put(commonMethodsUtilService.getLongValueForObject(param[0]), commonMethodsUtilService.getLongValueForObject(param[2]));
			}
		}
		
		String subStrId=mandalOrMuncId.toString().substring(0,1);
		if(subStrId.trim().equalsIgnoreCase("2")){
		List<Object[]> panchayatCuntList = activityInfoDocumentDAO.getPanchaytNamesByMandalId(activityScopeId,Long.valueOf(mandalOrMuncId.toString().substring(1)),stDate,ndDate);
		if(panchayatCuntList != null && panchayatCuntList.size() > 0l){
			for (Object[] objects : panchayatCuntList) {
				ActivityVO vo = new ActivityVO();
				 vo.setPanchayatId(Long.valueOf("1"+commonMethodsUtilService.getLongValueForObject(objects[0])));
				 vo.setName(commonMethodsUtilService.getStringValueForObject(objects[1]));
				 vo.setCount(commonMethodsUtilService.getLongValueForObject(objects[2]));
				 vo.setImagesCount(panchayatimagescoverdMap.get(commonMethodsUtilService.getLongValueForObject(objects[0])));
				 returnList.add(vo);
				}
			}
		}
		
		List<Object[]> locationsInfodocsDetals = activityInfoDocumentDAO.getWardNamesLocationsInfocoveredLocationsByScopeId(activityScopeId,Long.valueOf(mandalOrMuncId.toString().substring(1)),stDate,ndDate);
		Map<Long,Long> wardimagescoverdMap = new HashMap<Long, Long>(0);
		
		if(commonMethodsUtilService.isListOrSetValid(locationsInfodocsDetals)){
			for (Object[] param : locationsInfodocsDetals) {
				wardimagescoverdMap.put(commonMethodsUtilService.getLongValueForObject(param[0]), commonMethodsUtilService.getLongValueForObject(param[2]));
			}
		}
		
		if(subStrId.trim().equalsIgnoreCase("1")){
			List<Object[]> panchayatCuntList = activityInfoDocumentDAO.getWardNamesByMuncipalityId(activityScopeId,Long.valueOf(mandalOrMuncId.toString().substring(1)),stDate,ndDate);
			if(panchayatCuntList != null && panchayatCuntList.size() > 0l){
				for (Object[] objects : panchayatCuntList) {
					ActivityVO vo = new ActivityVO();
					 vo.setPanchayatId(Long.valueOf("2"+commonMethodsUtilService.getLongValueForObject(objects[0])));
					 vo.setName(commonMethodsUtilService.getStringValueForObject(objects[1]));
					 vo.setCount(commonMethodsUtilService.getLongValueForObject(objects[2]));
					 vo.setImagesCount(wardimagescoverdMap.get(commonMethodsUtilService.getLongValueForObject(objects[0])));
					 returnList.add(vo);
					}
				}
			}
	}catch(Exception e){
		 LOG.error("Exception Occured in getPanchayatOrWardsByMandalOrMuncId() method, Exception - ",e);
	}
	return returnList;
}
	/**
	* @param  String locationType
	* @param  List<Long> locationValues
	* @param  Long activityId
	* @return List<ActivityDetailsVO>
	* @author Santosh Kumar Verma
	* @Description :This Service is used to get activities details based on location. 
	* @since 26-DECEMBER-2017
	*/
	 public List<ActivityDetailsVO> getActivityDetailsBasedOnLocation(String locationType,List<Long> locationValues,Long activityScopeId,Long constituencyId) {
		  List<ActivityDetailsVO> finalList = new ArrayList<ActivityDetailsVO>(0);
		  try {
			  
			 // List<Object[]> activityDtlsObjList1 = activityConductedInfoDAO.getActivityDetailsBasedOnLocation(getGroupType(locationType), locationType, locationValues, activityScopeId);
			  List<Object[]> activityDtlsObjList = activityLocationInfoDAO.getActivityDetailsBasedOnLocation(getGroupType(locationType), locationType, locationValues, activityScopeId,constituencyId);
			  finalList = getActivityDetails(activityDtlsObjList);
		  } catch (Exception e) {
			  LOG.error("Exception Occured in getActivityDetailsBasedOnLocation() method, Exception - ",e);
		  }
		  return finalList;
	 }

	public List<ActivityDetailsVO> getActivityDetails(List<Object[]> objList) {
		List<ActivityDetailsVO> finalList = new ArrayList<ActivityDetailsVO>(0);
		SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
		try {
			if (objList != null && objList.size() > 0) {
				for (Object[] param : objList) {
					ActivityDetailsVO activityDtlsVO = new ActivityDetailsVO();
					activityDtlsVO.setLocationId(commonMethodsUtilService.getLongValueForObject(param[0]));
					activityDtlsVO.setName(commonMethodsUtilService.getStringValueForObject(param[1]));
					if (param[2] != null && param[2].toString().trim().length() > 0) {
						activityDtlsVO.setConductedDate(sdf.format(param[2]));
					}
					if (param[3] != null && param[3].toString().trim().length() > 0) {
						activityDtlsVO.setPlannedDate(sdf.format(param[3]));
					}
					activityDtlsVO.setActivityScopeId(commonMethodsUtilService.getLongValueForObject(param[4]));
					if (commonMethodsUtilService.getStringValueForObject(param[5]) != null && commonMethodsUtilService.getStringValueForObject(param[5]).equalsIgnoreCase("Y")){
						activityDtlsVO.setStatus("UPDATED");
					} else {
						activityDtlsVO.setStatus("NOT UPDATED");
					}
					
					activityDtlsVO.setActivityLocationInfoId(commonMethodsUtilService.getLongValueForObject(param[6]));
					finalList.add(activityDtlsVO);
				}
			}

		} catch (Exception e) {
			LOG.error("Exception Occured in getActivityDetails() method, Exception - ",e);
		}
		return finalList;
	}
  public String getGroupType(String locationType) {
	 String groupType = " ";
	  try {
		   if (locationType != null ) {
			    if(locationType.equalsIgnoreCase("mandal")) {
				   groupType = "village";
			   } else if(locationType.equalsIgnoreCase("village")) {
				   groupType = "village";
			   } else if(locationType.equalsIgnoreCase("munciplity")) {
				   groupType = "ward";
			   } else if(locationType.equalsIgnoreCase("ward")) {
				   groupType = "ward";
			   }
		   }
		  
	  } catch (Exception e) {
		  LOG.error("Exception occured at getGroupType() in ActivityService class ",e);
	  }
	  return groupType;
  }
	 /**
	 * @param  String locationType
	 * @param  Long activityScopeId
	 * @param  Long locationValue
	 * @param  String conductedDate
	 * @param  String updateStatus
	 * @param  Long activityId
	 * @return String
	 * @author Santosh Kumar Verma
	 * @Description :This Service is used update activity info. 
	 * @since 27-DECEMBER-2017
	 */
  public ResultStatus updateActivityInfo(final ActivityDetailsVO tabDetailsVO,final String locationType,final Long activityScopeId,final Long locationValue,final String conductedDate,final String updateStatus) {
		ResultStatus resultVO = new ResultStatus();
		try {
			LOG.info("Entered into saveActivityAnswerDetails in ActivityService class");
			if (tabDetailsVO != null) {
				resultVO = (ResultStatus) transactionTemplate.execute(new TransactionCallback() {
					public Object doInTransaction(TransactionStatus status) {
						ResultStatus resultVO = new ResultStatus();
						
						String stts = saveTabDetails(tabDetailsVO);// saving tabDetails
						
						if (stts.equalsIgnoreCase("fail")) {
							throw new ArithmeticException();
						}
						
						//List<Long> activityConductedInfoId = activityConductedInfoDAO.getActivityConductedInfoId(activityScopeId, locationType,locationValue);
						List<Long> activityLocationInfoId = activityLocationInfoDAO.getActivityConductedInfoId(activityScopeId, locationType,locationValue);

						SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
						Long activityLocationId = 0l;
						if (activityLocationInfoId != null && activityLocationInfoId.size() > 0) {
							activityLocationId = activityLocationInfoId.get(0);
							ActivityLocationInfo model = activityLocationInfoDAO.get(activityLocationId);
							if (conductedDate != null && conductedDate.trim().length() > 0) {
								try {
									model.setConductedDate(sdf.parse(conductedDate));
								} catch (ParseException e) {
									e.printStackTrace();
								}
							}
							if (updateStatus != null && updateStatus.trim().length() > 0) {
								model.setTabUpdatedStatus(updateStatus);
							}
							model.setUpdatedTime(dateUtilService.getCurrentDateAndTime());
							model.setTabDetailsId((tabDetailsVO.getTabDetailsId() != null && tabDetailsVO.getTabDetailsId() > 0) ? tabDetailsVO.getTabDetailsId():null);
							model.setSourceType("TAB");
							activityLocationInfoDAO.save(model);
						}
						
						resultVO.setResultCode(1);
						resultVO.setMessage("success");
						return resultVO;
					}
				});
			}
		} catch (Exception e) {
			resultVO.setResultCode(2);
			resultVO.setExceptionMsg(e.getLocalizedMessage());
			resultVO.setMessage("fail");
			LOG.error("Exception occurred at saveActivityAnswerDetails() of PartyMeetingMOMService class ",e);
		}
		return resultVO;
	}
     /**
	 * @param  Long activityScopeId
	 * @return List<ActivityDetailsVO>
	 * @author Santosh Kumar Verma
	 * @Description :This Service is used to get activity question by activityScopeId. 
	 * @since 27-DECEMBER-2017
	 */
	public List<ActivityDetailsVO> getActivityQuestionOptionDtls(Long activityScopeId,Long activityLocationInfoId) {
		 List<ActivityDetailsVO> finalList = new ArrayList<ActivityDetailsVO>(0);
		 try {
			 Map<Long,List<ActivityDetailsVO>> answerMap = null;
			 List<Object[]> questionObjDtlsLst = activityQuestionnaireDAO.getActivityQuestionsOptionsDetails(activityScopeId);
			 List<Object[]> answerObjLst = activityQuestionAnswerDAO.getQuestionAnswerDetails(activityLocationInfoId);
			 if (activityLocationInfoId != null && activityLocationInfoId.longValue() > 0 ) {
				 answerMap = getQuestionAnswerDtls(answerObjLst);
			 }
			 Map<Long,ActivityDetailsVO> questionMap = getQuestionDetails(questionObjDtlsLst,answerMap,"NORMALQUESTION");
			 finalList.addAll(questionMap.values());
		 } catch (Exception e) {
			 LOG.error("Exception occured at getActivityQuestionOptionDtls() in ActivityService class ",e);
		 }
		 return finalList;
	}

	/**
	 * @param  Long activityScopeId
	 * @return List<ActivityDetailsVO>
	 * @author Santosh Kumar Verma
	 * @Description :This Service is used to get activity question by activityScopeId. 
	 * @since 27-DECEMBER-2017
	 */
	public List<ActivityDetailsVO> getActivityDayWiseAndNormalQuestionOptionDtls(Long activityScopeId,Long activityLocationInfoId,String activityDate) {
		 List<ActivityDetailsVO> finalList = new ArrayList<ActivityDetailsVO>(0);
		 try {
			 SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
			 Map<Long,List<ActivityDetailsVO>> answerMap = null;
			 List<Object[]> questionObjDtlsLst = activityQuestionnaireDAO.getActivityQuestionsOptionsDetails(activityScopeId);
			 List<Object[]> answerObjLst = activityQuestionAnswerDAO.getQuestionAnswerDetails(activityLocationInfoId);
			 
			 if (activityDate != null && activityDate.length() > 0) { //day wise question adding if exist
				 List<Object[]> dayWiseQuesObjList = activityQuestionnaireDAO.getActivityQuestionsOptionsDetailsDayWise(activityScopeId, sdf.parse(activityDate));
				 List<Object[]> dayWiseAnswerList = activityQuestionAnswerDAO.getDayWiseQuestionAnswerDetails(activityLocationInfoId, sdf.parse(activityDate));
				 if (activityLocationInfoId != null && activityLocationInfoId.longValue() > 0 ) {
					 answerMap = getQuestionAnswerDtls(dayWiseAnswerList);
				 }
				 Map<Long,ActivityDetailsVO> questionMap = getQuestionDetails(dayWiseQuesObjList,answerMap,"DAYWISEQUESTION");
				 finalList.addAll(questionMap.values());
			 }
			
			 //Normal question adding 
			 if (activityLocationInfoId != null && activityLocationInfoId.longValue() > 0 ) {
				 answerMap = getQuestionAnswerDtls(answerObjLst);
			 }
			 Map<Long,ActivityDetailsVO> questionMap = getQuestionDetails(questionObjDtlsLst,answerMap,"NORMALQUESTION");
			 finalList.addAll(questionMap.values());
			 //setting activity startDate and endDate
			   Object[] obj = activityScopeDAO.getDatesForActivityScopeId(activityScopeId);
				if(obj != null && obj.length > 0){
					finalList.get(0).setActivityStarteDate(sdf.format(obj[0]));
					finalList.get(0).setActivityEndDate(sdf.format(obj[1]));
				}
			 
		 } catch (Exception e) {
			 LOG.error("Exception occured at getActivityQuestionOptionDtls() in ActivityService class ",e);
		 }
		 return finalList;
	}
	public Map<Long, List<ActivityDetailsVO>> getQuestionAnswerDtls(List<Object[]> objList) {
		Map<Long, List<ActivityDetailsVO>> questionAnswerMap = new HashMap<Long, List<ActivityDetailsVO>>(0);
		try {
			if (objList != null && objList.size() > 0) {
				for (Object[] param : objList) {
					List<ActivityDetailsVO> optionList = questionAnswerMap.get(commonMethodsUtilService.getLongValueForObject(param[0]));
					if (optionList == null) {
						optionList = new ArrayList<ActivityDetailsVO>();
						questionAnswerMap.put(commonMethodsUtilService.getLongValueForObject(param[0]), optionList);
					}
					ActivityDetailsVO optionVO = new ActivityDetailsVO();
					optionVO.setAnswerOptionId(commonMethodsUtilService.getLongValueForObject(param[1]));
					optionVO.setAnswerText(commonMethodsUtilService.getStringValueForObject(param[2]));
					if (commonMethodsUtilService.getLongValueForObject(param[3]) != null && commonMethodsUtilService.getLongValueForObject(param[3]) > 0l) {
						optionVO.setAnswerText(commonMethodsUtilService.getStringValueForObject(param[3]));
					}
					optionList.add(optionVO);
				}
			}

		} catch (Exception e) {
			LOG.error("Exception occured at getQuestionAnswerDtls() in ActivityService class ",e);
		}
		return questionAnswerMap;
	}
	public Map<Long, ActivityDetailsVO> getQuestionDetails(List<Object[]> objList,Map<Long,List<ActivityDetailsVO>> answerMap,String questionType) {
		Map<Long, ActivityDetailsVO> questionMap = new LinkedHashMap<Long, ActivityDetailsVO>(0);
		try {
			if (objList != null && objList.size() > 0) {
				for (Object[] param : objList) {
					if (!questionMap.containsKey(commonMethodsUtilService.getLongValueForObject(param[0]))) {
						ActivityDetailsVO questionVO = new ActivityDetailsVO();
						questionVO.setQuestionId(commonMethodsUtilService.getLongValueForObject(param[0]));
						questionVO.setQuestion(commonMethodsUtilService.getStringValueForObject(param[1]));
						questionVO.setOptionTypeId(commonMethodsUtilService.getLongValueForObject(param[2]));
						questionVO.setOptionType(commonMethodsUtilService.getStringValueForObject(param[3]));
						questionVO.setHasRemarks(commonMethodsUtilService.getStringValueForObject(param[4]));
						questionVO.setActivityQuestionnaireId(commonMethodsUtilService.getLongValueForObject(param[7]));
						questionVO.setIsMandatory(commonMethodsUtilService.getStringValueForObject(param[8]));
						if (questionType.equalsIgnoreCase("DAYWISEQUESTION")) {
							questionVO.setQuestionType("DAYWISEQUESTION");
							questionVO.setActivityDate(commonMethodsUtilService.getStringValueForObject(param[9]));
							questionVO.setActivityDaywiseQuestionnaireId(commonMethodsUtilService.getLongValueForObject(param[10]));
						}
						questionVO.setOptionList(new ArrayList<ActivityOptionVO>(0));
						if (answerMap != null && answerMap.size() > 0 ) {
							questionVO.setSubList(answerMap.get(questionVO.getActivityQuestionnaireId()));//setting answer
						}
						questionMap.put(questionVO.getQuestionId(), questionVO);
					}
					ActivityDetailsVO questionVO = questionMap.get(commonMethodsUtilService.getLongValueForObject(param[0]));
					ActivityOptionVO optionVO = new ActivityOptionVO();
					optionVO.setOptionId(commonMethodsUtilService.getLongValueForObject(param[5]));
					optionVO.setOption(commonMethodsUtilService.getStringValueForObject(param[6]));
					questionVO.getOptionList().add(optionVO);
				}
			}

		} catch (Exception e) {
			LOG.error("Exception occured at getQuestionDetails() in ActivityService class ",e);
		}
		return questionMap;
	}
	 /**
	   * @param ActivityDetailsVO inputVO
	   * @return ResultStatus
	   * @author Santosh Kumar Verma
	   * @Description :This Service is used to saving activity answer details. 
	   * @since 27-DECEMBER-2017
	   */
	public ResultStatus saveActivityAnswerDetails(final ActivityDetailsVO inputVO) {
		ResultStatus resultVO = new ResultStatus();
		try {
			LOG.info("Entered into saveActivityAnswerDetails in ActivityService class");
			if (inputVO != null) {
				resultVO = (ResultStatus) transactionTemplate.execute(new TransactionCallback() {
					public Object doInTransaction(TransactionStatus status) {
						ResultStatus resultVO = new ResultStatus();

						String stts = saveTabDetails(inputVO);// saving tabDetails
						if (stts.equalsIgnoreCase("fail")) {
							throw new ArithmeticException();
						}
						if (inputVO.getSubList() != null && inputVO.getSubList().size() > 0) {//saving question answer
							for (ActivityDetailsVO optionVO : inputVO.getSubList()) {
								ActivityQuestionAnswer model = new ActivityQuestionAnswer();
								model.setActivityQuestionnaireId((optionVO.getActivityQuestionnaireId() != null && optionVO.getActivityQuestionnaireId() > 0) ? optionVO.getActivityQuestionnaireId() : null);
								model.setActivityLocationInfoId((optionVO.getActivityLocationInfoId() != null && optionVO.getActivityLocationInfoId() > 0) ? optionVO.getActivityLocationInfoId() : null);
								model.setActivityOptionId((optionVO.getOptionId() != null && optionVO.getOptionId() > 0) ? optionVO.getOptionId() : null);
								if (optionVO.getHasRemarks() != null && optionVO.getHasRemarks().length() > 0) {
									model.setOptionTxt(optionVO.getHasRemarks());
								}
								model.setSourceType("TAB");
								model.setIsDeleted("N");
								model.setInsertedTime(dateUtilService.getCurrentDateAndTime());
								model.setUpdatedTime(dateUtilService.getCurrentDateAndTime());
								model.setTabDetailsId((inputVO.getTabDetailsId() != null && inputVO.getTabDetailsId() > 0) ? inputVO.getTabDetailsId() : null);
								activityQuestionAnswerDAO.save(model);
							}

						}
						resultVO.setResultCode(1);
						resultVO.setMessage("success");
						return resultVO;
					}
				});
			}
		} catch (Exception e) {
			resultVO.setResultCode(2);
			resultVO.setExceptionMsg(e.getLocalizedMessage());
			resultVO.setMessage("fail");
			LOG.error("Exception occurred at saveActivityAnswerDetails() of PartyMeetingMOMService class ",e);
		}
		return resultVO;
	}
	private String saveTabDetails(ActivityDetailsVO inputVO) {
		String status = "";
		try {
			TabDetails model = new TabDetails();
			model.setImei(inputVO.getImei());
			model.setLatitude(inputVO.getLatitude());
			model.setLongitude(inputVO.getLongitude());
			model.setUniqueKey(inputVO.getUniqueKey());
			model.setInsertedTime(dateUtilService.getCurrentDateAndTime());
			model.setSyncSource(inputVO.getSyncSource());
			model.setItdpAppUserId(inputVO.getItdpAppUserId());
			model.setAppVersion(inputVO.getAppVersion());
			model = tabDetailsDAO.save(model);
			inputVO.setTabDetailsId(model.getTabDetailsId());
			status = "success";
		} catch (Exception e) {
			status = "fail";
			LOG.error("Exception occured at saveTabDetails() in ActivityService class ",e);
		}
		return status;
	}
	  /**
	   * @param ActivityDetailsVO inputVO
	   * @return ResultStatus
	   * @author Santosh Kumar Verma
	   * @Description :This Service is used to saving docuemnt details. 
	   * @since 28-DECEMBER-2017
	   */
	
	public ResultStatus uploadDocumentImage(final ActivityDetailsVO inputVO,final List<String> docuemntBase64List) {
		ResultStatus resultVO = new ResultStatus();
		try {
			LOG.info("Entered into saveActivityAnswerDetails in ActivityService class");
			if (inputVO != null) {
				resultVO = (ResultStatus) transactionTemplate.execute(new TransactionCallback() {
					public Object doInTransaction(TransactionStatus status) {
						ResultStatus resultVO = new ResultStatus();
						ImageAndStringConverter imageAndStringConverter = new ImageAndStringConverter();
						
						String stts = saveTabDetails(inputVO);// saving tabDetails
						
						if (stts.equalsIgnoreCase("fail")) {
							throw new ArithmeticException();
						}
						
						 Calendar calendar = Calendar.getInstance();
						 calendar.setTime(new Date());
						 int year = calendar.get(Calendar.YEAR);
						 int month = calendar.get(Calendar.MONTH);
						 int day = calendar.get(Calendar.DAY_OF_MONTH);
						 int temp = month+1;
						 
						if (docuemntBase64List != null && docuemntBase64List.size() > 0) {
							// Folder Creation
							String folderName = folderCreation();

							for (String base64Str : docuemntBase64List) {
								String randomNumberStr = "0";
								while (randomNumberStr.length() < 10) {
									int randomNumber = Math.abs(new Random().nextInt());
									if (String.valueOf(randomNumber).length() > 10){
										randomNumberStr = String.valueOf(randomNumber).substring(0, 10);
									} else {
										randomNumberStr = String.valueOf(randomNumber);
									}
								}

								StringBuilder pathBuilder = new StringBuilder();
								StringBuilder documentName = new StringBuilder();
								pathBuilder.append(year).append("/").append(temp).append("-").append(day).append("/").append(randomNumberStr).append(".").append("jpg");
								 documentName.append(randomNumberStr).append(".").append("jpg");
								String destPath = folderName + "/" + randomNumberStr + ".jpg";
								if (base64Str != null && base64Str.trim().length() > 0) {
									inputVO.setImageBase64String(base64Str.replace("_", "/"));
									inputVO.setImageBase64String(base64Str.replace("-", "+"));
									boolean imageStatus = imageAndStringConverter.convertBase64StringToImage(inputVO.getImageBase64String(), destPath);
									if (imageStatus) {
										// model.setImageBase64Str(base64Str);
									} else {
										resultVO.setResultCode(2);
										resultVO.setMessage("fail");
										resultVO.setUniqueKey(inputVO.getUniqueKey());
									}
								}
								
								ActivityDocument model = new ActivityDocument();
								model.setActivityScopeId((inputVO.getActivityScopeId() != null && inputVO.getActivityScopeId() > 0) ? inputVO.getActivityScopeId() : null);
								model.setDocumentName(documentName.toString());
								model.setPath(pathBuilder.toString());
								model.setTabDetailsId((inputVO.getTabDetailsId() != null && inputVO.getTabDetailsId() > 0) ? inputVO.getTabDetailsId() : null);
								model.setActivityDate(dateUtilService.getCurrentDateAndTime());
								model.setInsertedTime(dateUtilService.getCurrentDateAndTime());
								model.setUpdatedTime(dateUtilService.getCurrentDateAndTime());
								model = activityDocumentDAO.save(model);
								
								String sttus = saveActivityInfoDocumentDlts(model.getActivityDocumentId(), inputVO.getActivityLocationInfoId());//saving Activity Document info details
								 if (sttus.equalsIgnoreCase("fail")) {
									  throw new ArithmeticException();
								 }
								resultVO.setfPath(model.getPath());//setting file path as response
							}
						}
						resultVO.setResultCode(1);
						resultVO.setMessage("success");
						resultVO.setUniqueKey(inputVO.getUniqueKey());
						return resultVO;
					}
				});
			}
		} catch (Exception e) {
			resultVO.setResultCode(2);
			resultVO.setExceptionMsg(e.getLocalizedMessage());
			resultVO.setMessage("fail");
			resultVO.setUniqueKey(inputVO.getUniqueKey());
			LOG.error("Exception occurred at saveActivityAnswerDetails() of ActivityService class ",e);
		}
		return resultVO;
	}
	public String saveActivityInfoDocumentDlts(Long activityDocumentId,Long activityLocationInfoId) {
		 String status="";
		 try {
			  ActivityInfoDocument model = new ActivityInfoDocument();
			  ActivityLocationInfo activityLocationInfo = activityLocationInfoDAO.get(activityLocationInfoId);
			  model.setActivityDocumentId((activityDocumentId != null && activityDocumentId > 0 ) ? activityDocumentId:null);
			  model.setActivityAddressId(activityLocationInfo.getAddress().getUserAddressId());
			  model.setLocationScopeId(activityLocationInfo.getLocationLevel());
			  model.setLocationValueAddress(activityLocationInfo.getLocationValue());
			  model.setInsertedTime(dateUtilService.getCurrentDateAndTime());
			  model.setUpdatedTime(dateUtilService.getCurrentDateAndTime());
			  model.setInsertType("TAB");
			  model.setIsDeleted("N");
			  model.setActivityLocationInfoId((activityLocationInfoId != null && activityLocationInfoId > 0 ) ? activityLocationInfoId:null);
			  activityInfoDocumentDAO.save(model);
			  status = "success";
		 } catch (Exception e) {
			 status = "fail";
			 LOG.error("Exception occurred at saveActivityInfoDocumentDlts() of ActivityService class ",e);
		 }
		 return status;
	}
	  /**
	   * @param Long activityScopeId
	   * @param Long activityLocationInfoId
	   * @return List<ActivityDetailsVO>
	   * @author Santosh Kumar Verma
	   * @Description :This Service is used to saving docuemnt details. 
	   * @since 29-DECEMBER-2017
	   */
	public List<ActivityDetailsVO> getDocumentDtlsByLocation(Long activityScopeId, Long activityLocationInfoId) {
		List<ActivityDetailsVO> documentList = new ArrayList<ActivityDetailsVO>(0);
		try {
			List<Object[]> docObjList = activityInfoDocumentDAO.getDocuemntDtlsByLocation(activityScopeId,activityLocationInfoId);
			if (docObjList != null && docObjList.size() > 0) {
				for (Object[] param : docObjList) {
					ActivityDetailsVO documentVO = new ActivityDetailsVO();
					documentVO.setId(commonMethodsUtilService.getLongValueForObject(param[0]));
					documentVO.setName(commonMethodsUtilService.getStringValueForObject(param[1]));
					documentList.add(documentVO);
				}
			}
		} catch (Exception e) {
			LOG.error("Exception occured at getDocumentDtlsByLocation() of ActivityService class ",e);
		}
		return documentList;
	}
	public List<ActivityReqAttributesVO> getActivityAttribute(Long activityScopeId) {
		  List<ActivityReqAttributesVO> resultList = new ArrayList<ActivityReqAttributesVO>();
		 try {
			 List<Long> objList = activityRequiredAttributeDAO.getActivityScopeAttribue(activityScopeId);
			 if (objList != null && objList.size() > 0) {
				 for (Long attributeId : objList) {
					 ActivityReqAttributesVO attributeVO = new ActivityReqAttributesVO();
					 attributeVO.setActivityScopeId(activityScopeId);
					 attributeVO.setReqAttributeId(commonMethodsUtilService.getLongValueForObject(attributeId));
					 resultList.add(attributeVO);
				}
			 }
		 } catch (Exception e) {
			 LOG.error("Exception occured at getDocumentDtlsByLocation() of ActivityService class ",e);
		 }
		 return resultList;
	} 
	 /**
	   * @param ActivityDetailsVO inputVO
	   * @return ResultStatus
	   * @author Santosh Kumar Verma
	   * @Description :This Service is used to saving activity answer details. 
	   * @since 27-DECEMBER-2017
	   */
	public ResultStatus saveActivityAnswerDetailsByWeb(final List<ActivityDetailsVO> inputVOList,final Long loginUserId) {
		ResultStatus resultVO = new ResultStatus();
		try {
			LOG.info("Entered into saveActivityAnswerDetails in ActivityService class");
			if (inputVOList != null && inputVOList.size() > 0) {
				resultVO = (ResultStatus) transactionTemplate.execute(new TransactionCallback() {
					public Object doInTransaction(TransactionStatus status) {
						ResultStatus resultVO = new ResultStatus();
						 Long activityLocationId = inputVOList.get(0).getActivityLocationInfoId();
						 List<Long> answerIds = null;
						 if (activityLocationId != null && activityLocationId.longValue() > 0) { // check answer is already submitted
							 answerIds = activityQuestionAnswerDAO.checkIsAnswerIsSubmitted(activityLocationId);	 
						 }
						 
                         for(ActivityDetailsVO questionVO:inputVOList) {
                        	 if (questionVO.getSubList() != null && questionVO.getSubList().size() > 0) {//saving question answer
     							for (ActivityDetailsVO optionVO : questionVO.getSubList()) {
     								
     								if (optionVO != null ) {
     									//in the case of selectbox and checkbox if optionId is zero then we are not saving
     									if (questionVO.getOptionTypeId() != null && questionVO.getOptionTypeId().longValue() == 1l || questionVO.getOptionTypeId() != null && questionVO.getOptionTypeId().longValue()==2l || questionVO.getOptionTypeId() != null && questionVO.getOptionTypeId().longValue()==5) {
     										if (optionVO.getOptionId() == null || optionVO.getOptionId().longValue() == 0l) {
     											continue;
     										}
     									}
     									ActivityQuestionAnswer model = new ActivityQuestionAnswer();
     									
     									if (answerIds != null && answerIds.size() > 0) {//updating isDeleted Y if already submitted
     										 if (questionVO.getOptionTypeId() != null && questionVO.getOptionTypeId().longValue() == 1l || questionVO.getOptionTypeId() != null && questionVO.getOptionTypeId().longValue()==5) {
     											if (questionVO.getActivityQuestionnaireId() != null && questionVO.getActivityQuestionnaireId().longValue() > 0 && questionVO.getActivityLocationInfoId() != null && questionVO.getActivityLocationInfoId().longValue() > 0) {
         											Long updateCount = activityQuestionAnswerDAO.updateAnswerDlts(questionVO.getActivityLocationInfoId(), questionVO.getActivityQuestionnaireId(), null);
         										}
     										} else {
     											if (questionVO.getActivityQuestionnaireId() != null && questionVO.getActivityQuestionnaireId().longValue() > 0 && questionVO.getActivityLocationInfoId() != null && questionVO.getActivityLocationInfoId().longValue() > 0) {
         											Long updateCount = activityQuestionAnswerDAO.updateAnswerDlts(questionVO.getActivityLocationInfoId(), questionVO.getActivityQuestionnaireId(), optionVO.getOptionId());
         										}
     										}
     										
     									}
     									model.setActivityQuestionnaireId((questionVO.getActivityQuestionnaireId() != null && questionVO.getActivityQuestionnaireId() > 0) ? questionVO.getActivityQuestionnaireId() : null);
         								model.setActivityLocationInfoId((questionVO.getActivityLocationInfoId() != null && questionVO.getActivityLocationInfoId() > 0) ? questionVO.getActivityLocationInfoId() : null);
         								model.setActivityDaywiseQuestionnaireId((questionVO.getActivityDaywiseQuestionnaireId() != null && questionVO.getActivityDaywiseQuestionnaireId() > 0) ? questionVO.getActivityDaywiseQuestionnaireId() : null);
         								model.setActivityOptionId((optionVO.getOptionId() != null && optionVO.getOptionId() > 0) ? optionVO.getOptionId() : null);
         								
         								if (questionVO.getOptionTypeId() != null && questionVO.getOptionTypeId()==4l)//count Description
         								{
         									if (optionVO.getHasRemarks() != null && optionVO.getHasRemarks().length() > 0) {
         										if (StringUtils.isNumeric(optionVO.getHasRemarks())) {
         											model.setCount(Long.valueOf(optionVO.getHasRemarks()));
         										}
             								}
         									
         								} else { // Text Description
         									if (optionVO.getHasRemarks() != null && optionVO.getHasRemarks().length() > 0) {
             									model.setOptionTxt(optionVO.getHasRemarks());
             								}
         								}
         								model.setSourceType("WEB");
         								model.setIsDeleted("N");
         								model.setInsertedTime(dateUtilService.getCurrentDateAndTime());
         								model.setUpdatedTime(dateUtilService.getCurrentDateAndTime());
         								model.setInsertedBy((loginUserId != null && loginUserId > 0) ? loginUserId:null);
         								model.setUpdatedBy((loginUserId != null && loginUserId > 0) ? loginUserId:null);
         								activityQuestionAnswerDAO.save(model);
     								}
     							}

     						}
                         }
						
						resultVO.setResultCode(1);
						resultVO.setMessage("success");
						return resultVO;
					}
				});
			}
		} catch (Exception e) {
			resultVO.setResultCode(2);
			resultVO.setExceptionMsg(e.getLocalizedMessage());
			resultVO.setMessage("fail");
			LOG.error("Exception occurred at saveActivityAnswerDetails() of PartyMeetingMOMService class ",e);
		}
		return resultVO;
	}
}
