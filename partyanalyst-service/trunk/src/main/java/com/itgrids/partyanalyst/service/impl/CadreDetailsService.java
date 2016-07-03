package com.itgrids.partyanalyst.service.impl;


import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.log4j.Logger;
import org.codehaus.jettison.json.JSONArray;
import org.codehaus.jettison.json.JSONObject;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionCallbackWithoutResult;
import org.springframework.transaction.support.TransactionTemplate;

import com.itgrids.partyanalyst.dao.IActivityLocationInfoDAO;
import com.itgrids.partyanalyst.dao.IActivityScopeRequiredAttributesDAO;
import com.itgrids.partyanalyst.dao.IBoothConstituencyElectionDAO;
import com.itgrids.partyanalyst.dao.IBoothDAO;
import com.itgrids.partyanalyst.dao.IBoothPublicationVoterDAO;
import com.itgrids.partyanalyst.dao.ICadreHealthStatusDAO;
import com.itgrids.partyanalyst.dao.ICandidateBoothResultDAO;
import com.itgrids.partyanalyst.dao.ICandidateContestedLocationDAO;
import com.itgrids.partyanalyst.dao.ICandidateDAO;
import com.itgrids.partyanalyst.dao.ICandidateResultDAO;
import com.itgrids.partyanalyst.dao.ICommunicationMediaResponseDAO;
import com.itgrids.partyanalyst.dao.IConstituencyDAO;
import com.itgrids.partyanalyst.dao.IDelimitationConstituencyAssemblyDetailsDAO;
import com.itgrids.partyanalyst.dao.IDelimitationConstituencyDAO;
import com.itgrids.partyanalyst.dao.IDistrictDAO;
import com.itgrids.partyanalyst.dao.IEventAttendeeDAO;
import com.itgrids.partyanalyst.dao.IEventDAO;
import com.itgrids.partyanalyst.dao.IEventInviteeDAO;
import com.itgrids.partyanalyst.dao.IEventTypeDAO;
import com.itgrids.partyanalyst.dao.IInsuranceTypeDAO;
import com.itgrids.partyanalyst.dao.IIvrRespondentCadreDAO;
import com.itgrids.partyanalyst.dao.IIvrSurveyAnswerDAO;
import com.itgrids.partyanalyst.dao.IIvrSurveyCandidateQuestionDAO;
import com.itgrids.partyanalyst.dao.IIvrSurveyEntityDAO;
import com.itgrids.partyanalyst.dao.IIvrSurveyQuestionOptionDAO;
import com.itgrids.partyanalyst.dao.IIvrSurveyServiceDAO;
import com.itgrids.partyanalyst.dao.ILocalElectionBodyDAO;
import com.itgrids.partyanalyst.dao.IMobileNumbersDAO;
import com.itgrids.partyanalyst.dao.IPanchayatDAO;
import com.itgrids.partyanalyst.dao.IPanchayatHamletDAO;
import com.itgrids.partyanalyst.dao.IPartyMeetingDAO;
import com.itgrids.partyanalyst.dao.IPublicRepresentativeDAO;
import com.itgrids.partyanalyst.dao.IPublicRepresentativeTypeDAO;
import com.itgrids.partyanalyst.dao.IStateDAO;
import com.itgrids.partyanalyst.dao.IStudentAddressDAO;
import com.itgrids.partyanalyst.dao.IStudentCadreRelationDAO;
import com.itgrids.partyanalyst.dao.IStudentContactDAO;
import com.itgrids.partyanalyst.dao.IStudentCourseDAO;
import com.itgrids.partyanalyst.dao.IStudentParentDetailsDAO;
import com.itgrids.partyanalyst.dao.IStudentRecomendationDAO;
import com.itgrids.partyanalyst.dao.ITdpCadreCandidateDAO;
import com.itgrids.partyanalyst.dao.ITdpCadreContestedLocationDAO;
import com.itgrids.partyanalyst.dao.ITdpCadreDAO;
import com.itgrids.partyanalyst.dao.ITdpCadreEnrollmentYearDAO;
import com.itgrids.partyanalyst.dao.ITdpCadreFamilyInfoDAO;
import com.itgrids.partyanalyst.dao.ITdpCadreInsuranceInfoDAO;
import com.itgrids.partyanalyst.dao.ITdpCommitteeDAO;
import com.itgrids.partyanalyst.dao.ITdpCommitteeMemberDAO;
import com.itgrids.partyanalyst.dao.ITehsilDAO;
import com.itgrids.partyanalyst.dao.ITrainingCampAttendanceDAO;
import com.itgrids.partyanalyst.dao.ITrainingCampBatchAttendeeDAO;
import com.itgrids.partyanalyst.dao.IUserAddressDAO;
import com.itgrids.partyanalyst.dao.IUserVoterDetailsDAO;
import com.itgrids.partyanalyst.dao.IVoterDAO;
import com.itgrids.partyanalyst.dao.hibernate.AppointmentCandidateRelationDAO;
import com.itgrids.partyanalyst.dao.hibernate.PublicRepresentativeTypeDAO;
import com.itgrids.partyanalyst.dao.impl.IActivityAttendanceDAO;
import com.itgrids.partyanalyst.dao.impl.IActivityInviteeDAO;
import com.itgrids.partyanalyst.dto.ActivityVO;
import com.itgrids.partyanalyst.dto.AddressVO;
import com.itgrids.partyanalyst.dto.BasicVO;
import com.itgrids.partyanalyst.dto.CadreCommitteeMemberVO;
import com.itgrids.partyanalyst.dto.CadreDetailsVO;
import com.itgrids.partyanalyst.dto.CadreOverviewVO;
import com.itgrids.partyanalyst.dto.CandidateDetailsVO;
import com.itgrids.partyanalyst.dto.CommitteeBasicVO;
import com.itgrids.partyanalyst.dto.ComplaintStatusCountVO;
import com.itgrids.partyanalyst.dto.GrievanceAmountVO;
import com.itgrids.partyanalyst.dto.GrievanceDetailsVO;
import com.itgrids.partyanalyst.dto.GrievanceSimpleVO;
import com.itgrids.partyanalyst.dto.IVRResponseVO;
import com.itgrids.partyanalyst.dto.IdNameVO;
import com.itgrids.partyanalyst.dto.IvrOptionsVO;
import com.itgrids.partyanalyst.dto.LocationVO;
import com.itgrids.partyanalyst.dto.MobileDetailsVO;
import com.itgrids.partyanalyst.dto.NtrTrustStudentVO;
import com.itgrids.partyanalyst.dto.PartyMeetingVO;
import com.itgrids.partyanalyst.dto.QuestionAnswerVO;
import com.itgrids.partyanalyst.dto.RegisteredMembershipCountVO;
import com.itgrids.partyanalyst.dto.ResultStatus;
import com.itgrids.partyanalyst.dto.TdpCadreFamilyDetailsVO;
import com.itgrids.partyanalyst.dto.TdpCadreVO;
import com.itgrids.partyanalyst.dto.VerifierVO;
import com.itgrids.partyanalyst.dto.WebServiceResultVO;
import com.itgrids.partyanalyst.excel.booth.VoterVO;
import com.itgrids.partyanalyst.model.Booth;
import com.itgrids.partyanalyst.model.LocalElectionBody;
import com.itgrids.partyanalyst.model.PublicRepresentative;
import com.itgrids.partyanalyst.model.StudentAddress;
import com.itgrids.partyanalyst.model.TdpCadre;
import com.itgrids.partyanalyst.model.UserAddress;
import com.itgrids.partyanalyst.service.ICadreCommitteeService;
import com.itgrids.partyanalyst.service.ICadreDetailsService;
import com.itgrids.partyanalyst.service.ICadreRegistrationService;
import com.itgrids.partyanalyst.service.ICandidateDetailsService;
import com.itgrids.partyanalyst.service.IMahaNaduService;
import com.itgrids.partyanalyst.service.IPartyMeetingService;
import com.itgrids.partyanalyst.service.IWebServiceHandlerService;
import com.itgrids.partyanalyst.utils.CommonMethodsUtilService;
import com.itgrids.partyanalyst.utils.DateUtilService;
import com.itgrids.partyanalyst.utils.IConstants;
import com.itgrids.partyanalyst.utils.SetterAndGetterUtilService;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;


public class CadreDetailsService implements ICadreDetailsService{

	private final static Logger LOG =  Logger.getLogger(CadreDetailsService.class);
	public ITdpCadreDAO tdpCadreDAO;
	public IDelimitationConstituencyAssemblyDetailsDAO delimitationConstituencyAssemblyDetailsDAO;
	public IConstituencyDAO constituencyDAO;
	public ITdpCommitteeMemberDAO tdpCommitteeMemberDAO;
	public ITdpCadreCandidateDAO tdpCadreCandidateDAO;
	public IBoothPublicationVoterDAO boothPublicationVoterDAO;
	public ICandidateResultDAO candidateResultDAO;
	public IBoothDAO boothDAO;
	public ICadreCommitteeService cadreCommitteeService;
	public IEventAttendeeDAO eventAttendeeDAO;
	public IWebServiceHandlerService webServiceHandlerService;
	private ICandidateDetailsService				candidateDetailsService;
	private IEventDAO		eventDAO;
	private IBoothConstituencyElectionDAO boothConstituencyElectionDAO;
	private ICandidateBoothResultDAO candidateBoothResultDAO;
	private IPanchayatHamletDAO panchayatHamletDAO;
	private ICadreRegistrationService cadreRegistrationService;
	private ITdpCommitteeDAO tdpCommitteeDAO;
	private CommonMethodsUtilService commonMethodsUtilService = new CommonMethodsUtilService();
	private ITdpCadreInsuranceInfoDAO tdpCadreInsuranceInfoDAO;
	private IDelimitationConstituencyDAO delimitationConstituencyDAO;
	private IInsuranceTypeDAO insuranceTypeDAO;
	private ICandidateContestedLocationDAO candidateContestedLocationDAO; 
	private IEventTypeDAO eventTypeDAO;
	private IEventInviteeDAO eventInviteeDAO;
	private ITdpCadreEnrollmentYearDAO tdpCadreEnrollmentYearDAO;
	private IPartyMeetingService partyMeetingService;
	private IMahaNaduService mahaNaduService;
	private ITdpCadreContestedLocationDAO tdpCadreContestedLocationDAO;
	private IVoterDAO voterDAO;
	private IMobileNumbersDAO mobileNumbersDAO;
	private IUserVoterDetailsDAO userVoterDetailsDAO;
	private IPublicRepresentativeDAO publicRepresentativeDAO;
	private ITehsilDAO tehsilDAO;
	private ILocalElectionBodyDAO localElectionBodyDAO;
	private IPanchayatDAO panchayatDAO;
	private IDistrictDAO districtDAO;
	private IStateDAO stateDAO;
	private ICandidateDAO candidateDAO;
	private IStudentCadreRelationDAO studentCadreRelationDAO;
	private IStudentParentDetailsDAO studentParentDetailsDAO;
	private IStudentContactDAO studentContactDAO;
	private IStudentCourseDAO studentCourseDAO;
	private IStudentAddressDAO studentAddressDAO;
	private IStudentRecomendationDAO studentRecomendationDAO;
	private ITdpCadreFamilyInfoDAO  tdpCadreFamilyInfoDAO;
	private ICommunicationMediaResponseDAO communicationMediaResponseDAO;
	private IIvrSurveyEntityDAO ivrSurveyEntityDAO;
	private IIvrRespondentCadreDAO ivrRespondentCadreDAO;
	private IIvrSurveyAnswerDAO ivrSurveyAnswerDAO;
	private IPartyMeetingDAO partyMeetingDAO;
	private ITrainingCampAttendanceDAO trainingCampAttendanceDAO;
	private IIvrSurveyServiceDAO ivrSurveyServiceDAO;
	private IUserAddressDAO userAddressDAO;
	private ITrainingCampBatchAttendeeDAO trainingCampBatchAttendeeDAO;
	private ICadreHealthStatusDAO cadreHealthStatusDAO;
	private AppointmentCandidateRelationDAO 	appointmentCandidateRelationDAO;
	private IIvrSurveyCandidateQuestionDAO ivrSurveyCandidateQuestionDAO;
	private IIvrSurveyQuestionOptionDAO ivrSurveyQuestionOptionDAO;
	private IActivityScopeRequiredAttributesDAO activityScopeRequiredAttributesDAO;
	private IActivityInviteeDAO activityInviteeDAO;
	private IActivityAttendanceDAO activityAttendanceDAO;
	private SetterAndGetterUtilService setterAndGetterUtilService = new SetterAndGetterUtilService();
	private IActivityLocationInfoDAO activityLocationInfoDAO;
	private TransactionTemplate transactionTemplate = null;
	private IPublicRepresentativeTypeDAO publicRepresentativeTypeDAO;
	
	public IPublicRepresentativeTypeDAO getPublicRepresentativeTypeDAO() {
		return publicRepresentativeTypeDAO;
	}

	public void setPublicRepresentativeTypeDAO(
			IPublicRepresentativeTypeDAO publicRepresentativeTypeDAO) {
		this.publicRepresentativeTypeDAO = publicRepresentativeTypeDAO;
	}

	public TransactionTemplate getTransactionTemplate() {
		return transactionTemplate;
	}

	public void setTransactionTemplate(TransactionTemplate transactionTemplate) {
		this.transactionTemplate = transactionTemplate;
	}

	public IActivityLocationInfoDAO getActivityLocationInfoDAO() {
		return activityLocationInfoDAO;
	}

	public void setActivityLocationInfoDAO(
			IActivityLocationInfoDAO activityLocationInfoDAO) {
		this.activityLocationInfoDAO = activityLocationInfoDAO;
	}

	public SetterAndGetterUtilService getSetterAndGetterUtilService() {
		return setterAndGetterUtilService;
	}

	public void setSetterAndGetterUtilService(
			SetterAndGetterUtilService setterAndGetterUtilService) {
		this.setterAndGetterUtilService = setterAndGetterUtilService;
	}

	public IActivityAttendanceDAO getActivityAttendanceDAO() {
		return activityAttendanceDAO;
	}

	public void setActivityAttendanceDAO(
			IActivityAttendanceDAO activityAttendanceDAO) {
		this.activityAttendanceDAO = activityAttendanceDAO;
	}

	public IActivityInviteeDAO getActivityInviteeDAO() {
		return activityInviteeDAO;
	}

	public void setActivityInviteeDAO(IActivityInviteeDAO activityInviteeDAO) {
		this.activityInviteeDAO = activityInviteeDAO;
	}

	public IActivityScopeRequiredAttributesDAO getActivityScopeRequiredAttributesDAO() {
		return activityScopeRequiredAttributesDAO;
	}

	public void setActivityScopeRequiredAttributesDAO(
			IActivityScopeRequiredAttributesDAO activityScopeRequiredAttributesDAO) {
		this.activityScopeRequiredAttributesDAO = activityScopeRequiredAttributesDAO;
	}

	public IIvrSurveyQuestionOptionDAO getIvrSurveyQuestionOptionDAO() {
		return ivrSurveyQuestionOptionDAO;
	}

	public void setIvrSurveyQuestionOptionDAO(
			IIvrSurveyQuestionOptionDAO ivrSurveyQuestionOptionDAO) {
		this.ivrSurveyQuestionOptionDAO = ivrSurveyQuestionOptionDAO;
	}

	public IIvrSurveyCandidateQuestionDAO getIvrSurveyCandidateQuestionDAO() {
		return ivrSurveyCandidateQuestionDAO;
	}

	public void setIvrSurveyCandidateQuestionDAO(
			IIvrSurveyCandidateQuestionDAO ivrSurveyCandidateQuestionDAO) {
		this.ivrSurveyCandidateQuestionDAO = ivrSurveyCandidateQuestionDAO;
	}
	
	public ICadreHealthStatusDAO getCadreHealthStatusDAO() {
		return cadreHealthStatusDAO;
	}

	public void setCadreHealthStatusDAO(ICadreHealthStatusDAO cadreHealthStatusDAO) {
		this.cadreHealthStatusDAO = cadreHealthStatusDAO;
	}

	public AppointmentCandidateRelationDAO getAppointmentCandidateRelationDAO() {
		return appointmentCandidateRelationDAO;
	}

	public void setAppointmentCandidateRelationDAO(
			AppointmentCandidateRelationDAO appointmentCandidateRelationDAO) {
		this.appointmentCandidateRelationDAO = appointmentCandidateRelationDAO;
	}

	public ITrainingCampBatchAttendeeDAO getTrainingCampBatchAttendeeDAO() {
		return trainingCampBatchAttendeeDAO;
	}

	public void setTrainingCampBatchAttendeeDAO(
			ITrainingCampBatchAttendeeDAO trainingCampBatchAttendeeDAO) {
		this.trainingCampBatchAttendeeDAO = trainingCampBatchAttendeeDAO;
	}

	public IIvrSurveyServiceDAO getIvrSurveyServiceDAO() {
		return ivrSurveyServiceDAO;
	}

	public void setIvrSurveyServiceDAO(IIvrSurveyServiceDAO ivrSurveyServiceDAO) {
		this.ivrSurveyServiceDAO = ivrSurveyServiceDAO;
	}

	public ITrainingCampAttendanceDAO getTrainingCampAttendanceDAO() {
		return trainingCampAttendanceDAO;
	}

	public void setTrainingCampAttendanceDAO(
			ITrainingCampAttendanceDAO trainingCampAttendanceDAO) {
		this.trainingCampAttendanceDAO = trainingCampAttendanceDAO;
	}

	public IPartyMeetingDAO getPartyMeetingDAO() {
		return partyMeetingDAO;
	}

	public void setPartyMeetingDAO(IPartyMeetingDAO partyMeetingDAO) {
		this.partyMeetingDAO = partyMeetingDAO;
	}

	public IIvrSurveyEntityDAO getIvrSurveyEntityDAO() {
		return ivrSurveyEntityDAO;
	}

	public void setIvrSurveyEntityDAO(IIvrSurveyEntityDAO ivrSurveyEntityDAO) {
		this.ivrSurveyEntityDAO = ivrSurveyEntityDAO;
	}

	public IIvrRespondentCadreDAO getIvrRespondentCadreDAO() {
		return ivrRespondentCadreDAO;
	}

	public void setIvrRespondentCadreDAO(
			IIvrRespondentCadreDAO ivrRespondentCadreDAO) {
		this.ivrRespondentCadreDAO = ivrRespondentCadreDAO;
	}

	public IIvrSurveyAnswerDAO getIvrSurveyAnswerDAO() {
		return ivrSurveyAnswerDAO;
	}

	public void setIvrSurveyAnswerDAO(IIvrSurveyAnswerDAO ivrSurveyAnswerDAO) {
		this.ivrSurveyAnswerDAO = ivrSurveyAnswerDAO;
	}

	public void setTdpCadreFamilyInfoDAO(
			ITdpCadreFamilyInfoDAO tdpCadreFamilyInfoDAO) {
		this.tdpCadreFamilyInfoDAO = tdpCadreFamilyInfoDAO;
	}

	public IStudentRecomendationDAO getStudentRecomendationDAO() {
		return studentRecomendationDAO;
	}

	public void setStudentRecomendationDAO(
			IStudentRecomendationDAO studentRecomendationDAO) {
		this.studentRecomendationDAO = studentRecomendationDAO;
	}

	public IStudentParentDetailsDAO getStudentParentDetailsDAO() {
		return studentParentDetailsDAO;
	}

	public void setStudentParentDetailsDAO(
			IStudentParentDetailsDAO studentParentDetailsDAO) {
		this.studentParentDetailsDAO = studentParentDetailsDAO;
	}

	public IStudentContactDAO getStudentContactDAO() {
		return studentContactDAO;
	}

	public void setStudentContactDAO(IStudentContactDAO studentContactDAO) {
		this.studentContactDAO = studentContactDAO;
	}

	public IStudentCourseDAO getStudentCourseDAO() {
		return studentCourseDAO;
	}

	public void setStudentCourseDAO(IStudentCourseDAO studentCourseDAO) {
		this.studentCourseDAO = studentCourseDAO;
	}

	public IStudentAddressDAO getStudentAddressDAO() {
		return studentAddressDAO;
	}

	public void setStudentAddressDAO(IStudentAddressDAO studentAddressDAO) {
		this.studentAddressDAO = studentAddressDAO;
	}

	public ITehsilDAO getTehsilDAO() {
		return tehsilDAO;
	}

	public ILocalElectionBodyDAO getLocalElectionBodyDAO() {
		return localElectionBodyDAO;
	}

	public IPanchayatDAO getPanchayatDAO() {
		return panchayatDAO;
	}

	public IDistrictDAO getDistrictDAO() {
		return districtDAO;
	}

	public IStateDAO getStateDAO() {
		return stateDAO;
	}

	public void setTehsilDAO(ITehsilDAO tehsilDAO) {
		this.tehsilDAO = tehsilDAO;
	}

	public void setLocalElectionBodyDAO(ILocalElectionBodyDAO localElectionBodyDAO) {
		this.localElectionBodyDAO = localElectionBodyDAO;
	}

	public void setPanchayatDAO(IPanchayatDAO panchayatDAO) {
		this.panchayatDAO = panchayatDAO;
	}

	public void setDistrictDAO(IDistrictDAO districtDAO) {
		this.districtDAO = districtDAO;
	}

	public void setStateDAO(IStateDAO stateDAO) {
		this.stateDAO = stateDAO;
	}

	public IPublicRepresentativeDAO getPublicRepresentativeDAO() {
		return publicRepresentativeDAO;
	}

	public void setPublicRepresentativeDAO(
			IPublicRepresentativeDAO publicRepresentativeDAO) {
		this.publicRepresentativeDAO = publicRepresentativeDAO;
	}

	public IUserVoterDetailsDAO getUserVoterDetailsDAO() {
		return userVoterDetailsDAO;
	}

	public void setUserVoterDetailsDAO(IUserVoterDetailsDAO userVoterDetailsDAO) {
		this.userVoterDetailsDAO = userVoterDetailsDAO;
	}

	public IMobileNumbersDAO getMobileNumbersDAO() {
		return mobileNumbersDAO;
	}

	public void setMobileNumbersDAO(IMobileNumbersDAO mobileNumbersDAO) {
		this.mobileNumbersDAO = mobileNumbersDAO;
	}

	public IVoterDAO getVoterDAO() {
		return voterDAO;
	}

	public void setVoterDAO(IVoterDAO voterDAO) {
		this.voterDAO = voterDAO;
	}

	public IMahaNaduService getMahaNaduService() {
		return mahaNaduService;
	}

	public void setMahaNaduService(IMahaNaduService mahaNaduService) {
		this.mahaNaduService = mahaNaduService;
	}

	public IPartyMeetingService getPartyMeetingService() {
		return partyMeetingService;
	}

	public void setPartyMeetingService(IPartyMeetingService partyMeetingService) {
		this.partyMeetingService = partyMeetingService;
	}

	public IEventInviteeDAO getEventInviteeDAO() {
		return eventInviteeDAO;
	}

	public void setEventInviteeDAO(IEventInviteeDAO eventInviteeDAO) {
		this.eventInviteeDAO = eventInviteeDAO;
	}

	public IEventTypeDAO getEventTypeDAO() {
		return eventTypeDAO;
	}

	public void setEventTypeDAO(IEventTypeDAO eventTypeDAO) {
		this.eventTypeDAO = eventTypeDAO;
	}


	public CommonMethodsUtilService getCommonMethodsUtilService() {
		return commonMethodsUtilService;
	}


	public void setCommonMethodsUtilService(
			CommonMethodsUtilService commonMethodsUtilService) {
		this.commonMethodsUtilService = commonMethodsUtilService;
	}


	public IConstituencyDAO getConstituencyDAO() {
		return constituencyDAO;
	}


	public void setConstituencyDAO(IConstituencyDAO constituencyDAO) {
		this.constituencyDAO = constituencyDAO;
	}


	public IDelimitationConstituencyAssemblyDetailsDAO getDelimitationConstituencyAssemblyDetailsDAO() {
		return delimitationConstituencyAssemblyDetailsDAO;
	}


	public void setDelimitationConstituencyAssemblyDetailsDAO(
			IDelimitationConstituencyAssemblyDetailsDAO delimitationConstituencyAssemblyDetailsDAO) {
		this.delimitationConstituencyAssemblyDetailsDAO = delimitationConstituencyAssemblyDetailsDAO;
	}


	public ITdpCadreDAO getTdpCadreDAO() {
		return tdpCadreDAO;
	}


	public void setTdpCadreDAO(ITdpCadreDAO tdpCadreDAO) {
		this.tdpCadreDAO = tdpCadreDAO;
	}
	
	public ITdpCommitteeMemberDAO getTdpCommitteeMemberDAO() {
		return tdpCommitteeMemberDAO;
	}


	public void setTdpCommitteeMemberDAO(
			ITdpCommitteeMemberDAO tdpCommitteeMemberDAO) {
		this.tdpCommitteeMemberDAO = tdpCommitteeMemberDAO;
	}


	public ITdpCadreCandidateDAO getTdpCadreCandidateDAO() {
		return tdpCadreCandidateDAO;
	}


	public void setTdpCadreCandidateDAO(ITdpCadreCandidateDAO tdpCadreCandidateDAO) {
		this.tdpCadreCandidateDAO = tdpCadreCandidateDAO;
	}


	public IBoothPublicationVoterDAO getBoothPublicationVoterDAO() {
		return boothPublicationVoterDAO;
	}


	public void setBoothPublicationVoterDAO(
			IBoothPublicationVoterDAO boothPublicationVoterDAO) {
		this.boothPublicationVoterDAO = boothPublicationVoterDAO;
	}

	public ICandidateResultDAO getCandidateResultDAO() {
		return candidateResultDAO;
	}

	public void setCandidateResultDAO(ICandidateResultDAO candidateResultDAO) {
		this.candidateResultDAO = candidateResultDAO;
	}
	
	public IBoothDAO getBoothDAO() {
		return boothDAO;
	}

	public void setBoothDAO(IBoothDAO boothDAO) {
		this.boothDAO = boothDAO;
	}
	public ICadreCommitteeService getcadreCommitteeService() {
		return cadreCommitteeService;
	}


	public void setcadreCommitteeService(ICadreCommitteeService cadreCommitteeService) {
		this.cadreCommitteeService = cadreCommitteeService;
	}
	public IEventAttendeeDAO getEventAttendeeDAO() {
		return eventAttendeeDAO;
	}


	public void setEventAttendeeDAO(IEventAttendeeDAO eventAttendeeDAO) {
		this.eventAttendeeDAO = eventAttendeeDAO;
	}

	public IWebServiceHandlerService getWebServiceHandlerService() {
		return webServiceHandlerService;
	}


	public void setWebServiceHandlerService(
			IWebServiceHandlerService webServiceHandlerService) {
		this.webServiceHandlerService = webServiceHandlerService;
	}

	public ICandidateDetailsService getCandidateDetailsService() {
		return candidateDetailsService;
	}


	public void setCandidateDetailsService(
			ICandidateDetailsService candidateDetailsService) {
		this.candidateDetailsService = candidateDetailsService;
	}

	public IEventDAO getEventDAO() {
		return eventDAO;
	}


	public void setEventDAO(IEventDAO eventDAO) {
		this.eventDAO = eventDAO;
	}


	public IBoothConstituencyElectionDAO getBoothConstituencyElectionDAO() {
		return boothConstituencyElectionDAO;
	}


	public void setBoothConstituencyElectionDAO(
			IBoothConstituencyElectionDAO boothConstituencyElectionDAO) {
		this.boothConstituencyElectionDAO = boothConstituencyElectionDAO;
	}


	public ICandidateBoothResultDAO getCandidateBoothResultDAO() {
		return candidateBoothResultDAO;
	}


	public void setCandidateBoothResultDAO(
			ICandidateBoothResultDAO candidateBoothResultDAO) {
		this.candidateBoothResultDAO = candidateBoothResultDAO;
	}

	public IPanchayatHamletDAO getPanchayatHamletDAO() {
		return panchayatHamletDAO;
	}


	public void setPanchayatHamletDAO(IPanchayatHamletDAO panchayatHamletDAO) {
		this.panchayatHamletDAO = panchayatHamletDAO;
	}


	public ICadreRegistrationService getCadreRegistrationService() {
		return cadreRegistrationService;
	}


	public void setCadreRegistrationService(
			ICadreRegistrationService cadreRegistrationService) {
		this.cadreRegistrationService = cadreRegistrationService;
	}

	public ITdpCommitteeDAO getTdpCommitteeDAO() {
		return tdpCommitteeDAO;
	}
	public void setTdpCommitteeDAO(ITdpCommitteeDAO tdpCommitteeDAO) {
		this.tdpCommitteeDAO = tdpCommitteeDAO;
	}

	public ITdpCadreInsuranceInfoDAO getTdpCadreInsuranceInfoDAO() {
		return tdpCadreInsuranceInfoDAO;
	}

	public void setTdpCadreInsuranceInfoDAO(
			ITdpCadreInsuranceInfoDAO tdpCadreInsuranceInfoDAO) {
		this.tdpCadreInsuranceInfoDAO = tdpCadreInsuranceInfoDAO;
	}

	public IDelimitationConstituencyDAO getDelimitationConstituencyDAO() {
		return delimitationConstituencyDAO;
	}


	public void setDelimitationConstituencyDAO(
			IDelimitationConstituencyDAO delimitationConstituencyDAO) {
		this.delimitationConstituencyDAO = delimitationConstituencyDAO;
	}
	
	public IInsuranceTypeDAO getInsuranceTypeDAO() {
		return insuranceTypeDAO;
	}


	public void setInsuranceTypeDAO(IInsuranceTypeDAO insuranceTypeDAO) {
		this.insuranceTypeDAO = insuranceTypeDAO;
	}
	public ICandidateContestedLocationDAO getCandidateContestedLocationDAO() {
		return candidateContestedLocationDAO;
	}


	public void setCandidateContestedLocationDAO(
			ICandidateContestedLocationDAO candidateContestedLocationDAO) {
		this.candidateContestedLocationDAO = candidateContestedLocationDAO;
	}
	
	public ITdpCadreEnrollmentYearDAO getTdpCadreEnrollmentYearDAO() {
		return tdpCadreEnrollmentYearDAO;
	}

	public void setTdpCadreEnrollmentYearDAO(
			ITdpCadreEnrollmentYearDAO tdpCadreEnrollmentYearDAO) {
		this.tdpCadreEnrollmentYearDAO = tdpCadreEnrollmentYearDAO;
	}
	
	public ITdpCadreContestedLocationDAO getTdpCadreContestedLocationDAO() {
		return tdpCadreContestedLocationDAO;
	}

	public void setTdpCadreContestedLocationDAO(
			ITdpCadreContestedLocationDAO tdpCadreContestedLocationDAO) {
		this.tdpCadreContestedLocationDAO = tdpCadreContestedLocationDAO;
	}	
	
	public ICadreCommitteeService getCadreCommitteeService() {
		return cadreCommitteeService;
	}
	

	public IStudentCadreRelationDAO getStudentCadreRelationDAO() {
		return studentCadreRelationDAO;
	}

	public void setStudentCadreRelationDAO(
			IStudentCadreRelationDAO studentCadreRelationDAO) {
		this.studentCadreRelationDAO = studentCadreRelationDAO;
	}

	public void setCadreCommitteeService(
			ICadreCommitteeService cadreCommitteeService) {
		this.cadreCommitteeService = cadreCommitteeService;
	}

	public ICandidateDAO getCandidateDAO() {
		return candidateDAO;
	}

	public void setCandidateDAO(ICandidateDAO candidateDAO) {
		this.candidateDAO = candidateDAO;
	}
	public void setCommunicationMediaResponseDAO(
			ICommunicationMediaResponseDAO communicationMediaResponseDAO) {
		this.communicationMediaResponseDAO = communicationMediaResponseDAO;
	}
	
	 

	public IUserAddressDAO getUserAddressDAO() {
		return userAddressDAO;
	}

	public void setUserAddressDAO(IUserAddressDAO userAddressDAO) {
		this.userAddressDAO = userAddressDAO;
	}

	public TdpCadreVO searchTdpCadreDetailsBySearchCriteriaForCommitte(Long locationLevel,Long locationValue, String searchName,String memberShipCardNo, 
			String voterCardNo, String trNumber, String mobileNo,Long casteStateId,String casteCategory,Long fromAge,Long toAge,String houseNo,String gender,int startIndex,int maxIndex,boolean isRemoved)
	{
		TdpCadreVO returnVO = new TdpCadreVO();
		List<Long> ids = new ArrayList<Long>();
    	try {
    		
    		StringBuilder queryStr = new StringBuilder();
    		
    		if(locationLevel != null && locationLevel.longValue() != 0L && locationValue != null && locationValue.longValue() != 0L)
    		{
    			
    			if(locationLevel.longValue() == 2L) // State 
    			{
    				if(locationValue.longValue() == 1l)
    				queryStr.append(" and model.userAddress.district.districtId between 11 and 23 ");
    				else if(locationValue.longValue() == 2l)
    					queryStr.append(" and model.userAddress.district.districtId between 1 and 10 ");	
    				else{
    					if(memberShipCardNo.endsWith("HIDE") || mobileNo.endsWith("HIDE")){
    						queryStr.append(" and model.userAddress.district.districtId between 11 and 23 ");
    						
    					}else{
    						queryStr.append(" and model.userAddress.district.districtId between 1 and 23 ");
    					}
    				}
    				locationValue = 0l;
    			}
    			
    			else if(locationLevel.longValue() == 3L) //district
    			{
    				queryStr.append(" and model.userAddress.district.districtId =:locationValue ");
    			}
    			
    			else if(locationLevel.longValue() == 4L)//constituency
    			{
    				queryStr.append(" and model.userAddress.constituency.constituencyId =:locationValue ");
    			}
    			else if(locationLevel.longValue() == 5L)//tehsil
    			{
    				queryStr.append(" and model.userAddress.tehsil.tehsilId =:locationValue ");
    			}
    			else if(locationLevel.longValue() == 6L)//panchayat
    			{
    				queryStr.append(" and model.userAddress.panchayat.panchayatId =:locationValue ");
    			}
    			else if(locationLevel.longValue() == 7L)//localElectionBody
    			{
    				queryStr.append(" and model.userAddress.localElectionBody.localElectionBodyId =:locationValue ");
    			}
    			else if(locationLevel.longValue() == 8L)//greater cities
    			{
    				/*
    				Object[] localBodyDetails = constituencyDAO.getlocalbodyName(locationValue);
    				boolean isGreater = false;
    				if(localBodyDetails != null && localBodyDetails.length>0)
    				{
    					String localBody =  localBodyDetails[1] != null ? localBodyDetails[1].toString().trim():null;
    					if(localBody != null && localBody.trim().equalsIgnoreCase("Greater Municipal Corp"))
    					{
    						Long tehsilId =  localBodyDetails[3] != null ? Long.valueOf(localBodyDetails[3].toString().trim()):0L;
    						if(tehsilId != null && tehsilId.longValue()>0)
    						{
    							List<Long> constituencyList = boothDAO.getConstituencyDetailsByTehsilId(tehsilId);
    							if(constituencyList != null && constituencyList.size()>0)
    							{
    								locationValue = constituencyList.get(0);
    								isGreater = true;
    								queryStr.append(" and model.userAddress.constituency.constituencyId =:locationValue ");
    							}
    						}
    					}
    				}

    				if(!isGreater)
    				{
    					queryStr.append(" and model.userAddress.ward.constituencyId =:locationValue ");
    				}
    				
    			*/
    				boolean isGreater = false;
    				Object[] localBodyDetails = constituencyDAO.getlocalbodyName(locationValue);
    				if(localBodyDetails != null && localBodyDetails.length>0)
    				{
    					String localBody =  localBodyDetails[1] != null ? localBodyDetails[1].toString().trim():null;
    					if(localBody != null && localBody.trim().equalsIgnoreCase("Greater Municipal Corp"))
    					{
    						Long localBodyId =  localBodyDetails[2] != null ? Long.valueOf(localBodyDetails[2].toString().trim()):0L;
    						if(localBodyId != null && localBodyId.longValue()>0)
    						{
    							locationValue = localBodyId;
    							isGreater = true;
    							queryStr.append(" and model.userAddress.localElectionBody.localElectionBodyId =:locationValue ");
    						}
    					}
    				}
    				if(!isGreater)
    				{
    					queryStr.append(" and model.userAddress.ward.constituencyId =:locationValue ");
    				}
    			}
    			else if(locationLevel.longValue() == 9L)
    			{
    				queryStr.append(" and model.userAddress.booth.boothId =:locationValue ");
    			}
    			else if(locationLevel.longValue() == 10L) // MP
    			{
    				List<Object[]> assemblyList = delimitationConstituencyAssemblyDetailsDAO.getAllAssemblyDetailsOfParliament(locationValue,Long.valueOf(IConstants.PRESENT_ELECTION_YEAR));
    				
    				if(assemblyList != null && assemblyList.size() > 0)
    				{
    					for(Object[] params : assemblyList)
    						ids.add((Long)params[0]);
    					queryStr.append(" and model.userAddress.constituency.constituencyId in(:ids) ");
    				}
    				
    			}
    			
    		}
    		
    		if(searchName != null && searchName.trim().length()>0 && !searchName.trim().equalsIgnoreCase("0") && !searchName.equalsIgnoreCase("null"))
			{
				queryStr.append(" and model.firstname like '%"+searchName+"%' ");
			}
    		
			if(memberShipCardNo != null && memberShipCardNo.trim().length()>0  && !memberShipCardNo.trim().equalsIgnoreCase("0") && !memberShipCardNo.equalsIgnoreCase("null"))
			{
				if(memberShipCardNo.endsWith("HIDE")){
					String[] memberShipCardNoStrArr = memberShipCardNo.split("-");
					memberShipCardNo = memberShipCardNoStrArr[0].trim();
					queryStr.append(" and (model.memberShipNo = '"+memberShipCardNo.trim()+"') ");
				}else{
					queryStr.append(" and (model.memberShipNo = '"+memberShipCardNo.trim()+"') ");
				}
			}
			if(mobileNo != null && mobileNo.trim().length()>0  && !mobileNo.trim().equalsIgnoreCase("0") && !mobileNo.equalsIgnoreCase("null"))
			{	
				if(mobileNo.endsWith("HIDE")){
					String[] mobileNoStrArr = mobileNo.split("-");
					mobileNo = mobileNoStrArr[0].trim();
					queryStr.append(" and (model.mobileNo like '%"+mobileNo.trim()+"%') ");
				}else{
					queryStr.append(" and (model.mobileNo like '%"+mobileNo.trim()+"%') ");
				}
			}
			
			
			
			if(voterCardNo != null && voterCardNo.trim().length()>0  && !voterCardNo.trim().equalsIgnoreCase("0") && !voterCardNo.equalsIgnoreCase("null"))
			{
				if(voterCardNo.endsWith("HIDE")){
					String[] voterStrArr = voterCardNo.split("-");
					String voterId = voterStrArr[0].trim();
					queryStr.append(" and model.voter.voterIDCardNo like '%"+voterId+"%' ");
				}else{
					queryStr.append(" and (model.voter.voterIDCardNo like '%"+voterCardNo.trim()+"%' or (familyVoter.voterId is not null and familyVoter.voterIDCardNo like '%"+voterCardNo.trim()+"%'))  ");
				}
			}
			if(trNumber != null && trNumber.trim().length()>0 && !trNumber.trim().equalsIgnoreCase("0") && !trNumber.equalsIgnoreCase("null"))
			{
				queryStr.append(" and (model.refNo like '%"+trNumber.trim()+"%') ");
			}
			if(casteStateId != null && casteStateId.toString().trim().length()>0 && !casteStateId.toString().trim().equalsIgnoreCase("0") && !casteStateId.toString().equalsIgnoreCase("null"))
			{
				queryStr.append(" and  model.casteState.casteStateId = :casteStateId ");
			}
			if(casteCategory != null && casteCategory.trim().length()>0 && !casteCategory.trim().equalsIgnoreCase("0") && !casteCategory.equalsIgnoreCase("null"))
			{
				queryStr.append(" and  model.casteState.casteCategoryGroup.casteCategoryGroupName like '%"+casteCategory+"%'");
			}			
			if((fromAge != null && fromAge.longValue()!=0L ) && (toAge != null && toAge.longValue() !=0L))
			{
				queryStr.append(" and model.age >="+fromAge+" and model.age <= "+toAge+"");
			}
			if(gender != null && gender.trim().length()>0)
			{
				if(gender.trim().equalsIgnoreCase("Male") || gender.trim().equalsIgnoreCase("M"))
				{
					queryStr.append(" and (model.gender like 'Male' or model.gender like 'M')");
				}
				if(gender.trim().equalsIgnoreCase("Female") || gender.trim().equalsIgnoreCase("F"))
				{
					queryStr.append(" and (model.gender like 'Female' or model.gender like 'F')");
				}
			}
			if(queryStr != null && queryStr.toString().trim().length()>0)
			{
				List<Object[]> cadreList = tdpCadreDAO.searchTdpCadreDetailsBySearchCriteriaForCommitte(locationValue,Long.valueOf(casteStateId), queryStr.toString(),startIndex,maxIndex,ids,isRemoved);
				
				List<TdpCadreVO> returnLsit = new ArrayList<TdpCadreVO>();
				
				if(cadreList != null && cadreList.size()>0)
				{
					SimpleDateFormat format  = new SimpleDateFormat("yy-MM-dd");
					for (Object[] cadre : cadreList) 
					{
						TdpCadreVO cadreVO = new TdpCadreVO();

						cadreVO.setId(cadre[0] != null ? Long.valueOf(cadre[0].toString().trim()):0L);
						cadreVO.setCadreName(cadre[1] != null ? cadre[1].toString():"");
						cadreVO.setRelativeName(cadre[2] != null ? cadre[2].toString():"");
						cadreVO.setGender(cadre[3] != null ? cadre[3].toString():"");
						if(cadre[4] != null){
							if(cadre[4].toString().trim().length() > 8){
								cadreVO.setMemberShipNo(cadre[4].toString().trim().substring(cadre[4].toString().trim().length()-8));
							}else{
								cadreVO.setMemberShipNo(cadre[4].toString());
							}
						}else{
							cadreVO.setMemberShipNo("");
						}
						//cadreVO.setMemberShipNo(cadre[4] != null ? cadre[4].toString().substring(4):"");
						cadreVO.setTrNo(cadre[5] != null ? cadre[5].toString():"");
						cadreVO.setMobileNo(cadre[6] != null ? cadre[6].toString():"");
						cadreVO.setImageURL(cadre[7] != null ? cadre[7].toString():"");
						
						if(cadre[9] != null)
						{
							cadreVO.setAge(cadre[9] != null ? Long.valueOf(cadre[9].toString().trim()):0L);
						}
						else if((cadreVO.getAge() == null || cadreVO.getAge().toString().trim().length()<=0) && cadre[10]  != null)
						{
							String dateOfBirth = 	cadre[10] != null ? cadre[10].toString().substring(0,10):" "	;
							if(dateOfBirth != null && dateOfBirth.trim().length()>0)
							{
								Calendar startDate = new GregorianCalendar();
								Calendar endDate = new GregorianCalendar();
								
								startDate.setTime(format.parse(dateOfBirth.trim()));
								
								endDate.setTime(new Date());

								int diffYear = endDate.get(Calendar.YEAR) - startDate.get(Calendar.YEAR);
								
								cadreVO.setAge(Long.valueOf(String.valueOf(diffYear)));
							}
						}
						
						cadreVO.setConstituency(cadre[11] != null ? cadre[11].toString().trim():"");
						
						if(cadreVO.getAge() != null && cadreVO.getAge().toString().trim().length()==0)
						{
							cadreVO.setAge(cadre[12] != null ? Long.valueOf(cadre[12].toString().trim()):0L);
						}
						
						cadreVO.setOccupation(cadre[13] != null ? cadre[13].toString().trim():"");
						cadreVO.setTehsil(cadre[14] != null ? cadre[14].toString().trim()+" Mandal":"");
						cadreVO.setPanchayat(cadre[15] != null ? cadre[15].toString().trim():"");
						
						String electionType = cadre[20] != null ? cadre[20].toString().trim():""; // municipality/corporation/ghmc....
						cadreVO.setLocalElectionBody(cadre[16] != null ? cadre[16].toString().trim()+" "+electionType:"");
						
						cadreVO.setDistrict(cadre[17] != null ? cadre[17].toString().trim():"");
						cadreVO.setCasteName(cadre[18] != null ? cadre[18].toString().trim():"");
						cadreVO.setVoterCardNo(cadre[19] != null ? cadre[19].toString().trim():"");
						
						cadreVO.setHouseNo(cadre[21] != null ? cadre[21].toString().trim():"");
						cadreVO.setConstituencyId(cadre[22] != null ? Long.valueOf(cadre[22].toString().trim()):0L);
						cadreVO.setTehsilId(cadre[23] != null ? Long.valueOf(cadre[23].toString().trim()):0L);
						cadreVO.setPanchayatId(cadre[24] != null ? Long.valueOf(cadre[24].toString().trim()):0L);
						cadreVO.setLocalElectionBodyId(cadre[25] != null ? Long.valueOf(cadre[25].toString().trim()):0L);				
						cadreVO.setDistrictId(cadre[26] != null ? Long.valueOf(cadre[26].toString().trim()):0L);		
						cadreVO.setAadharNo(cadre[28] != null ? cadre[28].toString().trim():"");
						cadreVO.setDataSourceType(cadre[29] != null ? cadre[29].toString().trim():"");
						
						if(cadre[30] !=null){
							cadreVO.setDeletedStatus(cadre[30].toString());
							
							if(cadreVO.getDeletedStatus().equalsIgnoreCase("MD")){
								cadreVO.setDeleteReason(cadre[32] !=null ? cadre[32].toString():"");
							}
							else{
								cadreVO.setDeleteReason("");
							}
						}
						
						returnLsit.add(cadreVO);
					}
					
					returnVO.setResponseStatus("SUCCESS");					
					returnVO.setTotalCount(Long.valueOf(String.valueOf(returnLsit.size())));
					returnVO.setTdpCadreDetailsList(returnLsit);
					if(returnLsit != null && maxIndex != 0)
					{
					List<Object[]> cadreListCnt = tdpCadreDAO.searchTdpCadreDetailsBySearchCriteriaForCommitte(locationValue,Long.valueOf(casteStateId), queryStr.toString(),0,0,ids,isRemoved);
					returnLsit.get(0).setTotalCount(new Long(cadreListCnt.size()));
					}
				}
				else
				{
					if(memberShipCardNo != null && memberShipCardNo.trim().length()>0  && !memberShipCardNo.trim().equalsIgnoreCase("0") && !memberShipCardNo.equalsIgnoreCase("null"))
					{
						returnVO.setResponseStatus(" No Cadre information is available with this Search details...");
					}					
					else if(mobileNo != null && mobileNo.trim().length()>0  && !mobileNo.trim().equalsIgnoreCase("0") && !mobileNo.equalsIgnoreCase("null"))
					{	
						returnVO.setResponseStatus(mobileNo+" Mobile Number is not Registered for any Cadre...");
					}
				}
				returnVO.setResponseCode("");
			}
			else
			{
				returnVO.setResponseStatus("FAILURE");
				returnVO.setResponseCode("Atleast one Attribute is Required...");
			}    		
    	} catch (Exception e) {
			LOG.error("Exception raised in searchTdpCadreDetailsBySearchCriteriaForCommitte  method in CadreDetailsService.",e);
			returnVO.setResponseStatus("FAILURE");
			returnVO.setResponseCode("SERVER ISSUE");			
		}
    	
    	return returnVO;
	}
	
	public TdpCadreVO tdpCadreCastewiseCountDetailsBySearchCriteriaForCommitte(Long locationLevel,Long locationValue, String searchName,String memberShipCardNo, 
			String voterCardNo, String trNumber, String mobileNo,Long casteStateId,String casteCategory,Long fromAge,Long toAge,String houseNo,String gender)
	{
		TdpCadreVO returnVO = new TdpCadreVO();
    	try {
    		
    		StringBuilder queryStr = new StringBuilder();
    		
    		if(locationLevel != null && locationLevel.longValue() != 0L && locationValue != null && locationValue.longValue() != 0L)
    		{
    			if(locationLevel.longValue() == 2L || locationLevel.longValue() == 3L)
    			{
    				queryStr.append(" and model.userAddress.district.districtId =:locationValue ");
    			}
    			else if(locationLevel.longValue() == 4L)
    			{
    				queryStr.append(" and model.userAddress.constituency.constituencyId =:locationValue ");
    			}
    			else if(locationLevel.longValue() == 5L)
    			{
    				queryStr.append(" and model.userAddress.tehsil.tehsilId =:locationValue ");
    			}
    			else if(locationLevel.longValue() == 6L)
    			{
    				queryStr.append(" and model.userAddress.panchayat.panchayatId =:locationValue ");
    			}
    			else if(locationLevel.longValue() == 7L)
    			{
    				queryStr.append(" and model.userAddress.localElectionBody.localElectionBodyId =:locationValue ");
    			}
    			else if(locationLevel.longValue() == 8L)
    			{
    				queryStr.append(" and model.userAddress.ward.constituencyId =:locationValue ");
    			}
    			else if(locationLevel.longValue() == 9L)
    			{
    				queryStr.append(" and model.userAddress.booth.boothId =:locationValue ");
    			}
    		}
    		
    		if(searchName != null && searchName.trim().length()>0 && !searchName.trim().equalsIgnoreCase("0") && !searchName.equalsIgnoreCase("null"))
			{
				queryStr.append(" and model.firstname like '%"+searchName+"%' ");
			}						
			if(memberShipCardNo != null && memberShipCardNo.trim().length()>0  && !memberShipCardNo.trim().equalsIgnoreCase("0") && !memberShipCardNo.equalsIgnoreCase("null"))
			{
				queryStr.append(" and (model.memberShipNo = '"+memberShipCardNo.trim()+"') ");
			}
			if(mobileNo != null && mobileNo.trim().length()>0  && !mobileNo.trim().equalsIgnoreCase("0") && !mobileNo.equalsIgnoreCase("null"))
			{							
				queryStr.append(" and (model.mobileNo like '%"+mobileNo.trim()+"%') ");
			}
			if(voterCardNo != null && voterCardNo.trim().length()>0  && !voterCardNo.trim().equalsIgnoreCase("0") && !voterCardNo.equalsIgnoreCase("null"))
			{
				queryStr.append(" and (model.voter.voterIDCardNo like '%"+voterCardNo.trim()+"%' or (familyVoter.voterId is not null and familyVoter.voterIDCardNo like '%"+voterCardNo.trim()+"%'))  ");
			}
			if(trNumber != null && trNumber.trim().length()>0 && !trNumber.trim().equalsIgnoreCase("0") && !trNumber.equalsIgnoreCase("null"))
			{
				queryStr.append(" and (model.refNo like '%"+trNumber.trim()+"%') ");
			}
			if(casteStateId != null && casteStateId.toString().trim().length()>0 && !casteStateId.toString().trim().equalsIgnoreCase("0") && !casteStateId.toString().equalsIgnoreCase("null"))
			{
				queryStr.append(" and  model.casteState.casteStateId = :casteStateId ");
			}
			if(casteCategory != null && casteCategory.trim().length()>0 && !casteCategory.trim().equalsIgnoreCase("0") && !casteCategory.equalsIgnoreCase("null"))
			{
				queryStr.append(" and  model.casteState.casteCategoryGroup.casteCategoryGroupName like '%"+casteCategory+"%'");
			}			
			if((fromAge != null && fromAge.longValue()!=0L ) && (toAge != null && toAge.longValue() !=0L))
			{
				queryStr.append(" and model.age >="+fromAge+" and model.age <= "+toAge+"");
			}
			if(gender != null && gender.trim().length()>0)
			{
				if(gender.trim().equalsIgnoreCase("Male") || gender.trim().equalsIgnoreCase("M"))
				{
					queryStr.append(" and (model.gender like 'Male' or model.gender like 'M')");
				}
				if(gender.trim().equalsIgnoreCase("Female") || gender.trim().equalsIgnoreCase("F"))
				{
					queryStr.append(" and (model.gender like 'Female' or model.gender like 'F')");
				}
			}
			if(queryStr != null && queryStr.toString().trim().length()>0)
			{
				queryStr.append("  group by model.casteState.casteStateId ");
				List<Object[]> cadreList = tdpCadreDAO.tdpCadreCasteCountDetailsBySearchCriteriaForCommitte(locationValue,Long.valueOf(casteStateId), queryStr.toString());
				
				List<TdpCadreVO> returnLsit = new ArrayList<TdpCadreVO>();
				if(cadreList != null && cadreList.size()>0)
				{
					for (Object[] cadre : cadreList) 
					{
						TdpCadreVO cadreVO = new TdpCadreVO();
						cadreVO.setId(cadre[1] != null ? Long.valueOf(cadre[1].toString().trim()):0L);
						cadreVO.setCasteName(cadre[0] != null ? cadre[0].toString().trim():"");						
						cadreVO.setTotalCount(cadre[2] != null ? Long.valueOf(cadre[2].toString().trim()):0L);
						
						returnLsit.add(cadreVO);
					}
					
					returnVO.setResponseStatus("SUCCESS");					
					returnVO.setTotalCount(Long.valueOf(String.valueOf(returnLsit.size())));
					returnVO.setTdpCadreDetailsList(returnLsit);
				}
				else
				{
					if(memberShipCardNo != null && memberShipCardNo.trim().length()>0  && !memberShipCardNo.trim().equalsIgnoreCase("0") && !memberShipCardNo.equalsIgnoreCase("null"))
					{
						returnVO.setResponseStatus(" No Cadre information is available with this Search details...");
					}					
					else if(mobileNo != null && mobileNo.trim().length()>0  && !mobileNo.trim().equalsIgnoreCase("0") && !mobileNo.equalsIgnoreCase("null"))
					{	
						returnVO.setResponseStatus(mobileNo+" Mobile Number is not Registered for any Cadre...");
					}
				}
				returnVO.setResponseCode("");
			}
			else
			{
				returnVO.setResponseStatus("FAILURE");
				returnVO.setResponseCode("Atleast one Attribute is Required...");
			}    		
    	} catch (Exception e) {
			LOG.error("Exception raised in tdpCadreCastewiseCountDetailsBySearchCriteriaForCommitte  method in CadreDetailsService.",e);
			returnVO.setResponseStatus("FAILURE");
			returnVO.setResponseCode("SERVER ISSUE");			
		}
    	
    	return returnVO;
	}
	public CadreCommitteeMemberVO cadreFormalDetailedInformation(Long cadreId,Long enrollmentYear,Long memberTypeId){
		
		CadreCommitteeMemberVO cadreDetailsVO=new CadreCommitteeMemberVO();
		try {
			
			if(cadreId !=null){

					
				//0,1,2,3,5,7,9,10,11,12
				//0.tdpCadreId,1.firstname,2.dateOfBirth,3.age,4.eduQualificationId,5.qualification,
				//6.occupationId,7.occupation,8.voterId,9.panchayatName,10.tehsilName,11.constName,12.mobileNo,13.ConstituencyId
				//14.,18.districtName,19.stateName,20.casteName,21.insertedWebUserId(registeredOn),22.registeredTime,23.emailId,24.dataSourceType
				//25.panchayatId,26.tehsilId,27.districtId,28.stateId,29.pConstId,30.pName,35.areaType
				Object[] cadreFormalDetails=tdpCadreDAO.cadreFormalDetailedInformation(cadreId,enrollmentYear,memberTypeId);
				
				//0.tdpCommitteeLevel,1.role
				//List<Object[]> partyPositionDetails= tdpCommitteeMemberDAO.getPartyPositionBycadre(cadreId);  
				//0.publicRepresentativeTypeId,1.type
				List<Object[]> publicRepDertails=null;//tdpCadreCandidateDAO.getPublicRepresentativeDetailsByCadre(cadreId);
				
				List<Long> candidateList =null;//tdpCadreCandidateDAO.getTdpCadreCandidate(cadreId);
				
				if(memberTypeId == null || memberTypeId.longValue() <2)
				{
					publicRepDertails=tdpCadreCandidateDAO.getPublicRepresentativeDetailsByCadre(cadreId);
					candidateList =tdpCadreCandidateDAO.getTdpCadreCandidate(cadreId);
				}
				
				DateFormat dateFormat=null;
				Date convertedDate = null;
				//DateUtilService dateUtilService = new DateUtilService();
				
				if(cadreFormalDetails !=null){
					
					cadreDetailsVO.setId((Long)cadreFormalDetails[0]);
					cadreDetailsVO.setName(cadreFormalDetails[1] !=null ? cadreFormalDetails[1].toString() : "");
					
					if(cadreFormalDetails[2] !=null){
						dateFormat = new SimpleDateFormat("yyyy-MM-dd");
						convertedDate = (Date) dateFormat.parse(cadreFormalDetails[2].toString());
						 String lines[] = convertedDate.toString().split(" ");
						 cadreDetailsVO.setDateOfBirth(lines[1]+ " "+lines[2] +" " + lines[5]);
					}else{
						cadreDetailsVO.setDateOfBirth("");
					}
					//cadreDetailsVO.setDateOfBirth(cadreFormalDetails[2] !=null ? cadreFormalDetails[2].toString() : "");
					cadreDetailsVO.setAge(cadreFormalDetails[3] !=null ? cadreFormalDetails[3].toString() :"" );
					cadreDetailsVO.setQualification(cadreFormalDetails[5] !=null ? cadreFormalDetails[5].toString() : "");
					
					cadreDetailsVO.setOccupation(cadreFormalDetails[7] !=null ? cadreFormalDetails[7].toString() : "");
					
					cadreDetailsVO.setVoterId(cadreFormalDetails[8] !=null ? cadreFormalDetails[8].toString() :"" );
					
					cadreDetailsVO.setPanchayatName(cadreFormalDetails[9] !=null ? cadreFormalDetails[9].toString() : "-" );
					cadreDetailsVO.setTehsilName(cadreFormalDetails[10] !=null ? cadreFormalDetails[10].toString() : "" );
					
					cadreDetailsVO.setConstituencyName(cadreFormalDetails[11] !=null ? cadreFormalDetails[11].toString() : "" );
					
					cadreDetailsVO.setMobileNo(cadreFormalDetails[12] !=null ? cadreFormalDetails[12].toString() : "");
					cadreDetailsVO.setConstituencyId(cadreFormalDetails[13] !=null ? (Long)cadreFormalDetails[13] : 0l );
					cadreDetailsVO.setVoterIdCardNo(cadreFormalDetails[14] !=null ? cadreFormalDetails[14].toString() : "");
					
					cadreDetailsVO.setRelativeName(cadreFormalDetails[40] !=null ? cadreFormalDetails[40].toString() : "");
					cadreDetailsVO.setRelativeType(cadreFormalDetails[41] !=null ? cadreFormalDetails[41].toString() : "");
					cadreDetailsVO.setwName(cadreFormalDetails[44] != null ? cadreFormalDetails[44].toString() : "" );
					if(cadreFormalDetails[15] !=null){
						String image=cadreFormalDetails[15].toString();
						String imagePath="http://mytdp.com/images/"+IConstants.CADRE_IMAGES+"/"+image+"";
						
						cadreDetailsVO.setImagePath(imagePath);
					}
					else{
						cadreDetailsVO.setImagePath("");
					}
					
					cadreDetailsVO.setMembershipNo(cadreFormalDetails[16] !=null ? cadreFormalDetails[16].toString() :"");
					cadreDetailsVO.setHouseNo(cadreFormalDetails[17] !=null ? cadreFormalDetails[17].toString() :"");
					cadreDetailsVO.setDistrictName(cadreFormalDetails[18] !=null ? cadreFormalDetails[18].toString() : "" );
					cadreDetailsVO.setStateName(cadreFormalDetails[19] !=null ? cadreFormalDetails[19].toString() : "" );
					cadreDetailsVO.setCasteName(cadreFormalDetails[20] !=null ? cadreFormalDetails[20].toString():"");
					
					String dataSourceType="";
					if(cadreFormalDetails[24] !=null){
						dataSourceType=cadreFormalDetails[24].toString();
					}
					
					if(cadreFormalDetails[21] !=null){
						
						String webUserId=cadreFormalDetails[21].toString();
						String[] partyOfficeIds=IConstants.PARTY_OFFICE_USER_IDS.split(",");
						String[] mahanaduIds=IConstants.MAHANADU_USER_IDS.split(",");
					
						boolean partyOffice=Arrays.asList(partyOfficeIds).contains(webUserId);
						if(partyOffice){
							cadreDetailsVO.setRegisteredOn(partyOfficeIds[0]);
						}
						else if(partyOffice ==false){
							boolean mahandu=Arrays.asList(mahanaduIds).contains(webUserId);
							if(mahandu){
								cadreDetailsVO.setRegisteredOn(mahanaduIds[0]);
							}
							else{
								cadreDetailsVO.setRegisteredOn(dataSourceType);
							}
						}
						
					}//registered On
					else{
						cadreDetailsVO.setRegisteredOn(dataSourceType);
					}
					cadreDetailsVO.setEmailId(cadreFormalDetails[23] !=null ? cadreFormalDetails[23].toString(): "");
					if(cadreFormalDetails[22] !=null){
						dateFormat = new SimpleDateFormat("yyyy-MM-dd");
						convertedDate = (Date) dateFormat.parse(cadreFormalDetails[22].toString());
						 String lines[] = convertedDate.toString().split(" ");
						 cadreDetailsVO.setRegisteredTime(lines[1]+ " "+lines[2] +" " + lines[5]);
					}
					
					//Ids
					cadreDetailsVO.setPanchayatId(cadreFormalDetails[25] !=null ? Long.parseLong(cadreFormalDetails[25].toString()) :0l);
					cadreDetailsVO.setTehsilId(cadreFormalDetails[26] !=null ? Long.parseLong(cadreFormalDetails[26].toString()):0l);
					cadreDetailsVO.setDistrictId(cadreFormalDetails[27] !=null ? Long.parseLong(cadreFormalDetails[27].toString()):0l);
					cadreDetailsVO.setStateId(cadreFormalDetails[28] !=null ? Long.parseLong(cadreFormalDetails[28].toString()):0l);
					//cadreDetailsVO.setpConstituencyId(cadreFormalDetails[29] !=null ? Long.parseLong(cadreFormalDetails[29].toString()):0l);
					//cadreDetailsVO.setpConstituencyName(cadreFormalDetails[30] !=null ? cadreFormalDetails[30].toString(): "");
					cadreDetailsVO.setBoothId(cadreFormalDetails[31] !=null ? Long.parseLong(cadreFormalDetails[31].toString()):0l);
					cadreDetailsVO.setPartNo(cadreFormalDetails[32] !=null ? cadreFormalDetails[32].toString():"0");
					cadreDetailsVO.setIsFamilyVoterId("false");
					if(cadreDetailsVO.getVoterId() == null || cadreDetailsVO.getVoterId().trim().isEmpty())
					{
						cadreDetailsVO.setIsFamilyVoterId("true");
						cadreDetailsVO.setVoterIdCardNo(cadreFormalDetails[37] !=null ? cadreFormalDetails[37].toString() : "");
						cadreDetailsVO.setVoterId(cadreFormalDetails[36] !=null ? cadreFormalDetails[36].toString() :"" );
					}
					if(cadreDetailsVO.getPanchayatId() == null || cadreDetailsVO.getPanchayatId().longValue() == 0L)
						cadreDetailsVO.setPanchayatId(cadreFormalDetails[33] !=null ? Long.parseLong(cadreFormalDetails[33].toString()) :0l);
					
					List<Object[]> pList = delimitationConstituencyAssemblyDetailsDAO.findParliamentForAssemblyForTheGivenYear(cadreDetailsVO.getConstituencyId(),2009L);
					if(pList != null && pList.size()>0){
						Object[] parliament = pList.get(0);
						cadreDetailsVO.setpConstituencyId(commonMethodsUtilService.getLongValueForObject(parliament[0]));
						cadreDetailsVO.setpConstituencyName(commonMethodsUtilService.getStringValueForObject(parliament[1]));
					}
					
					//Deleted candidate Details
					
					if(cadreFormalDetails[38] !=null){
						cadreDetailsVO.setDeletedStatus(cadreFormalDetails[38].toString());
						
						if(cadreDetailsVO.getDeletedStatus().equalsIgnoreCase("MD")){
							cadreDetailsVO.setDeletedreason(cadreFormalDetails[40] !=null ? cadreFormalDetails[40].toString():"");
						}
						else{
							cadreDetailsVO.setDeletedreason("");
						}
					}
					cadreDetailsVO.setGender(cadreFormalDetails[41] != null ? cadreFormalDetails[41].toString() : "");
					
				}
				
				List<Long> cadreIdsList = new ArrayList<Long>(0);
				cadreIdsList.add(cadreId);
				 List<Object[]> partyPositionDetails= tdpCommitteeMemberDAO.getPartyPositionsBycadreIdsList(cadreIdsList);
				 if(partyPositionDetails !=null && partyPositionDetails.size()>0)
				 {
					 for (Object[] partyPosition : partyPositionDetails) {

						 String level=partyPosition[0] != null ? partyPosition[0].toString() : "" ;
						 String role=partyPosition[1] != null ? partyPosition[1].toString() : "";
						 String state = commonMethodsUtilService.getStringValueForObject(partyPosition[6]);
						 
						 String commiteestr=partyPosition[2] != null ? partyPosition[2].toString() : "";
						 
						 if(level != null && !level.isEmpty()&&level.equalsIgnoreCase("state"))
						 {
							 level = state+" "+level;
						 }
						 String partyPositionStr = level +" " +role+" ( "+commiteestr+" )";
						 cadreDetailsVO.setCommiteeName(commiteestr);
						 cadreDetailsVO.setRole(role);
						 cadreDetailsVO.setStatus(level);
						 
						 if(!partyPositionStr.isEmpty())
						 {
							 cadreDetailsVO.setPartyPosition(partyPositionStr);
						 }
						 else{
							cadreDetailsVO.setPartyPosition("N/A");
						 }
						 
						 if(commiteestr != null && commiteestr.equalsIgnoreCase("main"))
						 {
							 cadreDetailsVO.setMeetingTypeId(1L);
						 }
					 }
				 }
				
				/*Map<Long,String> cadrePartyPositionMap = new LinkedHashMap<Long, String>(0);
				List<Long> tdpCadreIDsList = new ArrayList<Long>(0);
				tdpCadreIDsList.add(cadreId);
				getPartyPositionForCadre(tdpCadreIDsList,cadrePartyPositionMap);
				
				if(cadrePartyPositionMap != null && cadrePartyPositionMap.size()>0)
				{
					for (Long tdpcadreId : cadrePartyPositionMap.keySet()) {
						String committeeLocation = cadrePartyPositionMap.get(tdpcadreId);
							if(!committeeLocation.isEmpty())
							{
								 cadreDetailsVO.setPartyPosition(committeeLocation);
							 }
							else{
								cadreDetailsVO.setPartyPosition("N/A");
							}
					}
				}
				*/
				/*if(partyPositionDetails !=null && partyPositionDetails.size()>0)
				 {
					 String partyPositionStr="";
					 int rounds = 0;
					 for (Object[] partyPosition : partyPositionDetails) {
						 if(rounds>0)
							 partyPositionStr = partyPositionStr+",";
						 String level=partyPosition[0] != null ? partyPosition[0].toString() : "" ;
						 String role=partyPosition[1] != null ? partyPosition[1].toString() : "";
						 
						 String commiteestr=partyPosition[2] != null ? partyPosition[2].toString() : "";
						 partyPositionStr = partyPositionStr + level +" " +role+" ( "+commiteestr+" )";
						 rounds = rounds+1;
					 }				 
					 cadreDetailsVO.setPartyPosition(partyPositionStr);
				 }
				else{
					cadreDetailsVO.setPartyPosition("N/A");
				}*/
				
				String publicRepresentTypeStr="N/A";
				if(publicRepDertails !=null && publicRepDertails.size()>0){
					publicRepresentTypeStr="";
					for(Object[] publicRepDertail:publicRepDertails){
						publicRepresentTypeStr=publicRepDertail[1].toString()+" , "+publicRepresentTypeStr;
					}
					if (publicRepresentTypeStr.endsWith(" , ")) {
						publicRepresentTypeStr = publicRepresentTypeStr.substring(0, publicRepresentTypeStr.length() - 2);
						}
				}
				
				cadreDetailsVO.setRepresentativeType(publicRepresentTypeStr);
				cadreDetailsVO.setCandidate(candidateList !=null && candidateList.size()>0? candidateList.get(0).longValue():0l);
					
				cadreDetailsVO.setCadreId(cadreId);
				cadreDetailsVO.setAreaType(cadreFormalDetails[35] !=null ? cadreFormalDetails[35].toString() :"");
				
					//cadreDetailsVO.setRepresentativeType(publicRepDertails[1] !=null ? publicRepDertails[1].toString() : "");
				}
			
			if(cadreDetailsVO !=null){
				
				String voterIdStr=cadreDetailsVO.getVoterId();
				Long constituencyId = cadreDetailsVO.getConstituencyId();
				if(voterIdStr !=null && voterIdStr !=""){
					
					Object boothPartNo=boothPublicationVoterDAO.getBoothPartNumberByVoterId(voterIdStr);
					String s=null;
					Long l=null;
					if(boothPartNo instanceof String){
						s=(String)boothPartNo;
					}
					if(boothPartNo instanceof Long){
						l=(Long)boothPartNo;
					}
					if(l!=null){
						s=l.toString();
					}
					//CadreCommitteeMemberVO vo=	getCadreInformationInHisOwnBooth(s,constituencyId);
					//cadreDetailsVO.setCcmVO(vo);
				}
			}
			
			if(cadreDetailsVO.getTehsilId() !=null && cadreDetailsVO.getTehsilId() !=0l){
				List<Long> localElctioniBodyIds=boothDAO.getLocalElectionBody(cadreDetailsVO.getTehsilId());
				if(localElctioniBodyIds != null && localElctioniBodyIds.size()>0)
				{
					Long localElctioniBodyId = localElctioniBodyIds.get(0);
					cadreDetailsVO.setLocalElectionBody(localElctioniBodyId !=null ? localElctioniBodyId.longValue() : 0l);
				}
				
			}
			
			//adding previous Enrollment Years
			
			//Long maxEnrollmentYear=tdpCadreEnrollmentYearDAO.getMaxRecordFromEnrollmentYear(cadreId);
			List<Long> enrollmentYears = tdpCadreEnrollmentYearDAO.getPreviousElectionYearsOfCadre(cadreId);
			
			String cadreEnrollmentYears = "";
			if(enrollmentYears !=null && enrollmentYears.size() >0 ){
				for (Long long1 : enrollmentYears) {
					cadreEnrollmentYears += long1.toString()+",";
				}
			}
			if(cadreEnrollmentYears !=null && !cadreEnrollmentYears.isEmpty()){
				cadreEnrollmentYears = cadreEnrollmentYears.substring(0,cadreEnrollmentYears.length()-1);
			}
			
			cadreDetailsVO.setEnrollmentYears(cadreEnrollmentYears);
			
			//adding whatsApp && fb Details 
			
					//0.id,1.wstatus,2.fbUrl
			List<Object[]> fbDetails = tdpCadreFamilyInfoDAO.getWhatsAppAndFbDetailsOfCadre(cadreId);
			
			if(fbDetails !=null && fbDetails.size()>0){
				Object[] details = fbDetails.get(0);
				
				if(details[1] !=null){
					cadreDetailsVO.setwAppStatus(details[1].toString());
				}
				else{
					cadreDetailsVO.setwAppStatus("-");
				}
				if(details[2] !=null){
					cadreDetailsVO.setFbUrl(details[2].toString());
				}else{
					cadreDetailsVO.setFbUrl("-");
				}
			}
			
			
			return cadreDetailsVO;
			
		} catch (Exception e) {
			LOG.error("Exception raised in cadreFormalDetailedInformation  method in CadreDetailsService.",e);
		}
		return cadreDetailsVO;
	}
	public CadreCommitteeMemberVO getCadreInformationInHisOwnBooth(String ownBoothId,Long constituencyId){
		
		CadreCommitteeMemberVO finalVo=new CadreCommitteeMemberVO();
		
	try{
		List<Long> naIds = new ArrayList<Long>();
		
		String constiPerc = null;
		Map<Long,String> constiPercMap =  new HashMap<Long,String>();
		
		naIds.add(103l);
		naIds.add(310l);
		
		Set<Long> ownMandalsList = new HashSet<Long>();
		Set<Long> ownMunicList = new HashSet<Long>();
		Set<Long> ownPanchayatList = new HashSet<Long>();
		Set<Long> ownWardList = new HashSet<Long>();
		Set<Long> assMandalsList = new HashSet<Long>();
		Set<Long> assMunicList = new HashSet<Long>();
		Set<Long> assPanchayatList = new HashSet<Long>();
		Set<Long> assWardList = new HashSet<Long>();
		
		
		Set<String> ownBoothsList = new HashSet<String>();
		Set<String> assigBoothsList = new HashSet<String>();
		Set<String> allBooths = new HashSet<String>();
		
		if(ownBoothId != null){
			ownBoothsList.add(ownBoothId);
		}
		
		allBooths.addAll(ownBoothsList);
		allBooths.addAll(assigBoothsList);
		constiPerc = constiPercMap.get(constituencyId);
		if(constiPerc == null){
			List<Long> partyIds = new ArrayList<Long>();
	    	partyIds.add(163l);
	    	partyIds.add(872l);
	    	//0 partyId,1 votesearnedPerc
	    	List<Object[]> candidateResult = candidateResultDAO.getResultPercByPartyIds(partyIds,constituencyId,258l);
	    	if(candidateResult.size() == 1){
	    		constiPerc = candidateResult.get(0)[1].toString();
	    	}else{
	    		for(Object[] result:candidateResult){
	    			if(((Long)result[0]).longValue() == 872l){
	    				constiPerc =result[1].toString();
	    			}
	    		}
	    	}
	    	
	    	constiPercMap.put(constituencyId, constiPerc);
		}
		
		String ownMandalPerc = "";
		String ownMunicPerc = "";
		String ownPancPerc = "";
		String ownWardPerc = "";
		String ownBoothPerc = "";
		
		
		String assMandalPerc = "";
		String assMunicPerc = "";
		String assPancPerc = "";
		String assWardPerc = "";
		String assBoothPerc = "";

		
		Map<String,VoterVO> boothsInfoMap = new HashMap<String,VoterVO>();
		
		List<Object[]> boothsInfo = new ArrayList<Object[]>();
		//0boothId, 1partNo, 2tehsilId, 3lclBodyId, 4panchayatId, 5wardId
		if(!(allBooths == null || allBooths.size() == 0))
		    boothsInfo = boothDAO.getBoothsInfo(constituencyId,allBooths);
		for(Object[] info:boothsInfo){
			VoterVO vo = new VoterVO();
			vo.setBoothId((Long)info[0]);
			vo.setPartNo(Long.valueOf(info[1].toString()));
			vo.setCandidateId((Long)info[2]);//tehsilId
			vo.setLocalAreaId((Long)info[3]);//lclBodyId
			vo.setPanchayatId((Long)info[4]);
			vo.setHamletId((Long)info[5]);//wardId
			boothsInfoMap.put(info[1].toString(), vo);
		}
		Long electionId = 258l;//2014 Assembly ElectionId
		List<Long> reqPartyIds = new ArrayList<Long>();
		if(!naIds.contains(constituencyId)){
			if(constituencyId.longValue() == 243l || constituencyId.longValue() == 229l || constituencyId.longValue() == 297l || constituencyId.longValue() == 301l){
				reqPartyIds.add( 872l);
			}else{
				reqPartyIds.add( 872l);
				reqPartyIds.add( 163l);
			}
		}else{
			reqPartyIds.add( 872l);
			reqPartyIds.add( 163l);
			electionId = 260l;
		}
		
		List<Long> assBtIds = new ArrayList<Long>();
			for(String partNo:assigBoothsList){
				VoterVO vo = boothsInfoMap.get(partNo);
				if(vo != null){
					assBtIds.add(vo.getBoothId());
					if(vo.getLocalAreaId() == null && vo.getCandidateId() != null){
						assMandalsList.add(vo.getCandidateId());
					
					}
					
					if(vo.getLocalAreaId() != null && vo.getHamletId() == null){
						assMunicList.add(vo.getLocalAreaId());
						
					}
					
					if(vo.getPanchayatId() != null){
						assPanchayatList.add(vo.getPanchayatId());
						
					}
					
					if(vo.getHamletId() != null){
						assWardList.add(vo.getHamletId());
						
					}
				}
			}
			if(assMandalsList.size() > 0){
				assMandalPerc = cadreCommitteeService.calculateMandalPerc(assMandalsList,constituencyId,reqPartyIds, electionId);			
			}if(assMunicList.size() > 0){
				assMunicPerc = cadreCommitteeService.calculateMunicPerc(assMunicList,constituencyId,reqPartyIds, electionId);
			}if(assPanchayatList.size() > 0){
				assPancPerc = cadreCommitteeService.calculatePancPerc(assPanchayatList,constituencyId,reqPartyIds, electionId);
			}if(assWardList.size() > 0){
				assWardPerc = cadreCommitteeService.calculateWardPerc(assWardList,constituencyId,reqPartyIds, electionId);
			}
			
			if(assBtIds.size() > 0){
			   assBoothPerc = cadreCommitteeService.calculateBootPerc(assBtIds,constituencyId,reqPartyIds, electionId);
			}
          if(constiPerc == null){
        	  List<Long> boothIds = boothDAO.getBoothsDetailIds("", constituencyId, null);
        	  constiPerc = cadreCommitteeService.calculateBootPerc(boothIds, constituencyId, reqPartyIds, electionId);
        	  constiPercMap.put(constituencyId, constiPerc);
	    	}
			List<Long> ownBtIds = new ArrayList<Long>();
			for(String partNo:ownBoothsList){
				VoterVO vo = boothsInfoMap.get(partNo);
				if(vo != null){
					ownBtIds.add(vo.getBoothId());
					if(vo.getLocalAreaId() == null && vo.getCandidateId() != null){
						ownMandalsList.add(vo.getCandidateId());
					}
					
					if(vo.getLocalAreaId() != null && vo.getHamletId() == null){
						ownMunicList.add(vo.getLocalAreaId());
					}
					
					if(vo.getPanchayatId() != null){
						ownPanchayatList.add(vo.getPanchayatId());
					}
					
					if(vo.getHamletId() != null){
						ownWardList.add(vo.getHamletId());
					}
				}
			}
			
			finalVo.setOwnConstiPerc(constiPerc);
			
			  if(ownMandalsList.size() > 0){
				  ownMandalPerc = cadreCommitteeService.calculateMandalPerc(ownMandalsList,constituencyId,reqPartyIds,electionId);
				  finalVo.setOwnMandalPerc(ownMandalPerc);
				}if(ownMunicList.size() > 0){
					ownMunicPerc = cadreCommitteeService.calculateMunicPerc(ownMunicList,constituencyId,reqPartyIds,electionId);
					finalVo.setOwnMunciPerc(ownMunicPerc);
				}if(ownWardList.size() > 0){
					ownWardPerc =cadreCommitteeService.calculateWardPerc(ownWardList,constituencyId,reqPartyIds,electionId);
					finalVo.setOwnWardPerc(ownWardPerc);
				}if(ownPanchayatList.size() > 0){
					ownPancPerc = cadreCommitteeService.calculatePancPerc(ownPanchayatList,constituencyId,reqPartyIds,electionId);
					finalVo.setOwnPanchPerc(ownPancPerc);
				}
				
			if(ownBtIds.size() > 0){
				ownBoothPerc = cadreCommitteeService.calculateBootPerc(ownBtIds,constituencyId,reqPartyIds,electionId);
				finalVo.setOwnBoothPerc(ownBoothPerc);
			}
			
			if(finalVo.getOwnMandalPerc()!=null && finalVo.getOwnBoothPerc()!=null){
				double boothPer = Double.parseDouble(finalVo.getOwnBoothPerc());
				double mandalPer = Double.parseDouble(finalVo.getOwnMandalPerc());
				double prfrmancePerc = boothPer-mandalPer;
				finalVo.setMandalBoothCmpr(String.valueOf(prfrmancePerc));
				if(prfrmancePerc<0){
					finalVo.setLowPerformance(true);
				}else{
					finalVo.setLowPerformance(false);
				}
			}
			if(finalVo.getOwnMunciPerc()!=null && finalVo.getOwnBoothPerc()!=null){
				double boothPer = Double.parseDouble(finalVo.getOwnBoothPerc());
				double munciPer = Double.parseDouble(finalVo.getOwnMunciPerc());
				double prfrmancePerc = boothPer-munciPer;
				finalVo.setMandalBoothCmpr(String.valueOf(prfrmancePerc));
				if(prfrmancePerc<0){
					finalVo.setLowPerformance(true);
				}else{
					finalVo.setLowPerformance(false);
				}
			}
		
			return finalVo;
		
		}catch (Exception e) {
			LOG.error("Exception raised in getCadreInformationInHisOwnBooth service  method in CadreDetailsService.",e);
		}
			return finalVo;
	}
	
	public CadreCommitteeMemberVO complaintDetailsOfCadre(Long cadreId,String memberShipId){
		
		
		CadreCommitteeMemberVO finalVo=new CadreCommitteeMemberVO();
		List<CadreCommitteeMemberVO> listForFinalVo=new ArrayList<CadreCommitteeMemberVO>();
		
		try{
			
			 DateFormat formatter = null;
		     Date convertedDate = null;
			//0.Complaint_id,1.Subject,2.Seviority,3.Raised_Date,4.Completed_Status,5.issue_type,6.type_of_grevience
			List<Object[]> complaintDetails=tdpCadreDAO.complaintDetailsOfCadre(memberShipId);
			
			if(complaintDetails !=null){
				
				for (Object[] objects : complaintDetails) {
					
					CadreCommitteeMemberVO eachComplaint=new CadreCommitteeMemberVO();
					
					eachComplaint.setId(Long.parseLong(objects[0].toString()));//Complaint_id
					eachComplaint.setName(objects[1]!=null  ? objects[1].toString():"");//Subject
					eachComplaint.setOccupation(objects[2] !=null ? objects[2].toString():"");//seviority
					
					if(objects[3] !=null){
						 String date = objects[3].toString();
						 String date1 = date.substring(0, 10);
						 formatter = new SimpleDateFormat("yyyy-MM-dd");
						 convertedDate = (Date) formatter.parse(date1);
						 String lines[] = convertedDate.toString().split(" ");
						 String time = date.substring(10);
						 eachComplaint.setDateOfBirth(lines[1]+ " "+lines[2] +" " + lines[5]+" ("+lines[0]+")");
						
					}else{
						eachComplaint.setDateOfBirth("");
					}
					
					//eachComplaint.setDateOfBirth(objects[3].toString());//Raised_Date
					eachComplaint.setStatus(objects[4] !=null ? objects[4].toString():"");//Completed_Status
					eachComplaint.setRepresentativeType(objects[5] !=null ? objects[5].toString() :"" );//issue_type
					eachComplaint.setType(objects[6] !=null ? objects[6].toString() :"");//type_of_grevience
					
					listForFinalVo.add(eachComplaint);
				}
				
				finalVo.setKnownList(listForFinalVo);
			}
			return finalVo;
			
		}catch (Exception e) {
			LOG.error("Exception raised in complaintDetailsOfCadre service  method in CadreDetailsService.",e);
		}
		return finalVo;
	}
	
	public List<CadreCommitteeMemberVO> getEventDetailsOfCadre(Long cadreId){
		
		List<CadreCommitteeMemberVO> finalList=new ArrayList<CadreCommitteeMemberVO>();
		
		try{  
			
			
			List<Object[]> eventTypeList = eventTypeDAO.getEventTypeDetails();
			Map<Long,String> evetTypeNamesMap = new LinkedHashMap<Long, String>(0);
			if(eventTypeList != null && eventTypeList.size()>0)
			{
				for (Object[] event : eventTypeList) {
					evetTypeNamesMap.put(event[0] !=null ? Long.parseLong(event[0].toString()) :0l, event[1] !=null ? event[1].toString():"");
				}
			}
			List<Object[]> eventInviteesList = eventInviteeDAO.getInvitationCountforCandidate(cadreId);
			Map<Long,Long> eventInviteeMap = new LinkedHashMap<Long, Long>(0);
			if(eventInviteesList != null && eventInviteesList.size()>0)
			{
				for (Object[] event : eventInviteesList) {
					eventInviteeMap.put(event[0] !=null ? Long.parseLong(event[0].toString()) :0l,event[2] !=null ? Long.parseLong(event[2].toString()) :0l);
				}
			}
			
			//0.tdpCadreId,1.eventId,2.name,3.description,4.parentEventId,5.count
			List<Object[]> eventDetails=eventAttendeeDAO.getEventDetailsOfCadre(cadreId);
			
			
			//parentId,eventId
			List<CadreCommitteeMemberVO> list=new ArrayList<CadreCommitteeMemberVO>();
			
			Map<Long,Map<Long,Map<Long,CadreCommitteeMemberVO>>> eventTypeWiseEventsMap = new LinkedHashMap<Long, Map<Long,Map<Long,CadreCommitteeMemberVO>>>();
			if(eventDetails !=null && eventDetails.size()>0){
				
				for (Object[] event : eventDetails) {
					CadreCommitteeMemberVO subEventDetails = null;
					Map<Long,CadreCommitteeMemberVO>  mainEventMap = null;
					Map<Long,Map<Long,CadreCommitteeMemberVO>> finalMap=new HashMap<Long, Map<Long,CadreCommitteeMemberVO>>();
					if(eventTypeWiseEventsMap.get(event[6] !=null ? Long.parseLong(event[6].toString()) :0l) != null)
					{
						finalMap = eventTypeWiseEventsMap.get(event[6] !=null ? Long.parseLong(event[6].toString()) :0l);
					}
					
					mainEventMap=finalMap.get(Long.parseLong(event[4].toString()));
					
					if(mainEventMap ==null){
						subEventDetails = new CadreCommitteeMemberVO();
						mainEventMap=new HashMap<Long, CadreCommitteeMemberVO>();
						finalMap.put(Long.parseLong(event[4].toString()),mainEventMap);//parentEventId,Map<EventId,CadreCommitteeMemberVO>
					}else{
						subEventDetails=mainEventMap.get(Long.parseLong(event[1].toString()));
						if(subEventDetails ==null){ 
							subEventDetails=new CadreCommitteeMemberVO();
						}
					}
							subEventDetails.setVtrId(Long.parseLong(event[0].toString()));//cadreId
							subEventDetails.setId(Long.parseLong(event[1].toString()));//eventId
							subEventDetails.setName(event[2] !=null ? event[2].toString():"");//eventName
							subEventDetails.setLevel(Long.parseLong(event[4].toString()));//parentEventId
							subEventDetails.setInvitationCount(eventInviteeMap.get(event[4] !=null ? Long.parseLong(event[4].toString()) :0l));
							String parentEventName=eventDAO.getEventName(subEventDetails.getLevel());
							
							if(parentEventName !=null){
								subEventDetails.setType(parentEventName);//parentEventName
							}
							
							subEventDetails.setTotal(event[5] !=null ? Long.parseLong(event[5].toString()) :0l);
							subEventDetails.setEventTypeId(event[6] !=null ? Long.parseLong(event[6].toString()) :0l);
							subEventDetails.setEventTypeStr(evetTypeNamesMap.get(subEventDetails.getEventTypeId()).trim());
							
							if(subEventDetails.getInvitationCount() != null && subEventDetails.getInvitationCount()>0L)
							{
								Long attendedCount = subEventDetails.getTotal();
								if(attendedCount != null && attendedCount.longValue() ==0L)
									subEventDetails.setAbsentCount(0L);
							}
							else
							{
								subEventDetails.setInvitationCount(0L);
							}
							mainEventMap.put(Long.parseLong(event[1].toString()), subEventDetails);
							
							finalMap.put(Long.parseLong(event[4].toString()), mainEventMap);
							eventTypeWiseEventsMap.put(subEventDetails.getEventTypeId(), finalMap);
					 }
				
			}
			 if(eventInviteeMap != null && eventInviteeMap.size()>0)
			{
				if(eventInviteesList != null && eventInviteesList.size()>0)
				{
					for (Object[] events : eventInviteesList) {
						CadreCommitteeMemberVO subEventDetails = null;
						Map<Long,CadreCommitteeMemberVO>  mainEventMap = null;
						Map<Long,Map<Long,CadreCommitteeMemberVO>> finalMap=new HashMap<Long, Map<Long,CadreCommitteeMemberVO>>();
						if(eventTypeWiseEventsMap.get(events[3] !=null ? Long.parseLong(events[3].toString()) :0l) != null)
						{
							finalMap = eventTypeWiseEventsMap.get(events[3] !=null ? Long.parseLong(events[3].toString()) :0l);
						}
						
						mainEventMap=finalMap.get(Long.parseLong(events[0].toString()));
						
						if(mainEventMap ==null){
							subEventDetails = new CadreCommitteeMemberVO();
							mainEventMap=new HashMap<Long, CadreCommitteeMemberVO>();
							finalMap.put(Long.parseLong(events[0].toString()),mainEventMap);//parentEventId,Map<EventId,CadreCommitteeMemberVO>
						}else{
							subEventDetails=mainEventMap.get(Long.parseLong(events[0].toString()));
							//if(subEventDetails ==null){ 
								//subEventDetails=new CadreCommitteeMemberVO();
								//subEventDetails.setName(" - ");//eventName
							//}
						}
						if(subEventDetails != null){
								subEventDetails.setVtrId(cadreId);//cadreId
								subEventDetails.setId(Long.parseLong(events[0].toString()));//eventId
								subEventDetails.setName(" - ");//eventName
								subEventDetails.setLevel(Long.parseLong(events[0].toString()));//parentEventId
								subEventDetails.setInvitationCount(eventInviteeMap.get(events[0] !=null ? Long.parseLong(events[0].toString()) :0l));
								String parentEventName=eventDAO.getEventName(subEventDetails.getLevel());
								
								if(parentEventName !=null){
									subEventDetails.setType(parentEventName);//parentEventName
								}
								
								subEventDetails.setTotal(0l);
								subEventDetails.setEventTypeId(events[3] !=null ? Long.parseLong(events[3].toString()) :0l);
								subEventDetails.setEventTypeStr(evetTypeNamesMap.get(subEventDetails.getEventTypeId()).trim());
								
								if(subEventDetails.getInvitationCount() != null && subEventDetails.getInvitationCount()>0L)
								{
									Long attendedCount = subEventDetails.getTotal();
									if(attendedCount != null && attendedCount.longValue() ==0L)
										subEventDetails.setAbsentCount(subEventDetails.getInvitationCount());
								}
								if(subEventDetails.getAbsentCount() != null && subEventDetails.getAbsentCount().longValue()>0L){
									subEventDetails.setCasteName(events[5] != null ? events[5].toString()  : ""  );//absentee Remarks
								}
								mainEventMap.put(Long.parseLong(events[0].toString()), subEventDetails);
								
								finalMap.put(Long.parseLong(events[0].toString()), mainEventMap);
								eventTypeWiseEventsMap.put(subEventDetails.getEventTypeId(), finalMap);
						}
						 }
				}
			}
			
			if(eventTypeWiseEventsMap != null && eventTypeWiseEventsMap.size()>0)
			{
				for (Long eventTypeId : eventTypeWiseEventsMap.keySet()) {
					CadreCommitteeMemberVO eventTypeWiseVO = new CadreCommitteeMemberVO();
					eventTypeWiseVO.setEventTypeStr(evetTypeNamesMap.get(eventTypeId));
					List<CadreCommitteeMemberVO> eventsList = new ArrayList<CadreCommitteeMemberVO>(0);
					Map<Long,Map<Long,CadreCommitteeMemberVO>> finalMap = eventTypeWiseEventsMap.get(eventTypeId);
					if(finalMap !=null && finalMap.size()>0){
						for (Map.Entry<Long,Map<Long,CadreCommitteeMemberVO>> entry : finalMap.entrySet())
						{
							CadreCommitteeMemberVO cadreCommitteeMemberVO = new CadreCommitteeMemberVO();
							
							cadreCommitteeMemberVO.setId(entry.getKey());//parentEventId
							
							String parentEventName=eventDAO.getEventName(cadreCommitteeMemberVO.getId());
							if(parentEventName !=null){
								cadreCommitteeMemberVO.setName(parentEventName);
							}
							
							Map<Long,CadreCommitteeMemberVO> subMap = entry.getValue();//subEventId,vo
							
							if(subMap !=null && subMap.size()>0){
								cadreCommitteeMemberVO.setKnownList(new ArrayList<CadreCommitteeMemberVO>(subMap.values()));//List Of SubEvents
							}
							eventsList.add(cadreCommitteeMemberVO);
						}
					}
					eventTypeWiseVO.setKnownList(eventsList);
					finalList.add(eventTypeWiseVO);
				}
			}

			return finalList;

		}catch (Exception e) {
			LOG.error("Exception raised in getEventDetailsOfCadre service  method in CadreDetailsService.",e);
		}
		
		return finalList;
	}
	public VerifierVO getTdpCadreSurveyDetails(Long cadreId,Long surveyId,String searchTypeStr,Long boothId,String isPriority,String voterCardNo,Long constituencyId,String constiTypeStr){
		VerifierVO verifierVO=new VerifierVO();
		try{
			verifierVO=webServiceHandlerService.getTdpCadreSurveyDetails(cadreId,surveyId,searchTypeStr,boothId,isPriority,voterCardNo,constituencyId,constiTypeStr);
			return verifierVO;
		}
		catch(Exception e){
			LOG.error("Exception raised in getTdpCadreSurveyDetails service  method in CadreDetailsService.",e);
		}
		
		return verifierVO;
	}
	public List<CandidateDetailsVO>  getCandidateElectDetatails(Long cadreId){
		
		List<CandidateDetailsVO> candidateElecDatails=new ArrayList<CandidateDetailsVO>();
		try{
			List<Long> candidateList=tdpCadreCandidateDAO.getTdpCadreCandidate(cadreId);
			if(candidateList !=null && candidateList.size()>0L){
				 candidateElecDatails=candidateDetailsService.getCandidateElectionDetails(candidateList.get(0));
			}
			
		}catch(Exception e){
			LOG.error("Exception raised in getCandidateElectDetatails() service  method in CadreDetailsService.",e);
		}
		
		return candidateElecDatails;
	}
	

	/*public RegisteredMembershipCountVO getTotalMemberShipRegistrationsInCadreLocation(Long tdpCadreId)
	{
		RegisteredMembershipCountVO countVO = new RegisteredMembershipCountVO();
		try{
			
			Long electionId = 258l;//2014 
			Long electionYear = 2014l;
			UserAddress userAddress = tdpCadreDAO.get(tdpCadreId).getUserAddress();
			if(userAddress != null)
			{
			Long constituencyId = userAddress.getConstituency().getConstituencyId();
			countVO.setAreaType(userAddress.getConstituency().getAreaType());
			
			
			countVO.setConsTotalVoters(getTotalVotersByLocationId(constituencyId, "Constituency", electionId, constituencyId,null));
			countVO.setConstituencyCount(getMemberShipCount("Constituency", constituencyId, electionYear, constituencyId,null));
		   
			String percentage = null;
			if(countVO.getConsTotalVoters() != null && countVO.getConsTotalVoters() > 0)
			   percentage = (new BigDecimal((countVO.getConstituencyCount() * 100.0)/countVO.getConsTotalVoters().doubleValue()).setScale(2, BigDecimal.ROUND_HALF_UP)).toString();
			countVO.setConstiPerc(percentage);
			
			
			
			if(userAddress.getPanchayat() != null && userAddress.getPanchayat().getPanchayatId() != null)
			{
				countVO.setPanchayatCount(getMemberShipCount("Panchayat", userAddress.getPanchayat().getPanchayatId(), electionYear, constituencyId,null));
				countVO.setPanchayatTotVoters(getTotalVotersByLocationId(userAddress.getPanchayat().getPanchayatId(), "Panchayat", electionId, constituencyId,null));
				String panPer = null;
				if(countVO.getPanchayatTotVoters() != null && countVO.getPanchayatTotVoters() > 0)
				{
				 panPer = (new BigDecimal((countVO.getPanchayatCount() * 100.0)/countVO.getPanchayatTotVoters().doubleValue()).setScale(2, BigDecimal.ROUND_HALF_UP)).toString();
			      countVO.setPanchPerc(panPer);
			      
			        if(userAddress.getTehsil() != null && userAddress.getTehsil().getTehsilId() != null)
					{
					  countVO.setMandalCount(getMemberShipCount("Mandal", userAddress.getTehsil().getTehsilId(), electionYear, constituencyId,null));	
					  countVO.setMandalTotVoters(getTotalVotersByLocationId(userAddress.getTehsil().getTehsilId(), "Mandal", electionId, constituencyId,null));
					  String manPer = null;
					  if(countVO.getMandalTotVoters() != null && countVO.getMandalTotVoters() > 0)
					   manPer = (new BigDecimal((countVO.getMandalCount() * 100.0)/countVO.getMandalTotVoters().doubleValue()).setScale(2, BigDecimal.ROUND_HALF_UP)).toString();
					   countVO.setMandalPerc(manPer);
					
					}
			      
				}
			}
			
			if(userAddress.getBooth() != null && userAddress.getBooth().getBoothId() != null)
			{
				countVO.setBoothCount(getMemberShipCount("Booth", userAddress.getBooth().getBoothId(), electionYear, constituencyId,null))	;
				countVO.setBoothTotVoters(getTotalVotersByLocationId(userAddress.getBooth().getBoothId(), "booth", electionId, constituencyId,null));
				String boothPer =null;
				if(countVO.getBoothTotVoters() != null && countVO.getBoothTotVoters() > 0)
				 boothPer = (new BigDecimal((countVO.getBoothCount() * 100.0)/countVO.getBoothTotVoters().doubleValue()).setScale(2, BigDecimal.ROUND_HALF_UP)).toString();
			     countVO.setBoothPerc(boothPer);
			
			}
			
			if(userAddress.getLocalElectionBody() != null && userAddress.getLocalElectionBody().getLocalElectionBodyId() != null)
			{
				countVO.setMunCount(getMemberShipCount("Muncipality", userAddress.getLocalElectionBody().getLocalElectionBodyId(), electionYear, constituencyId,null));
				countVO.setMunTotVoters(getTotalVotersByLocationId(userAddress.getLocalElectionBody().getLocalElectionBodyId(), "Muncipality", electionId, constituencyId,null));
				String  munPer =null;
				if(countVO.getMunTotVoters() != null && countVO.getMunTotVoters() > 0)
				 munPer = (new BigDecimal((countVO.getMunCount() * 100.0)/countVO.getMunTotVoters().doubleValue()).setScale(2, BigDecimal.ROUND_HALF_UP)).toString();
			     countVO.setMunPerc(munPer);
			
			}
			
			if(userAddress.getDistrict() != null && userAddress.getDistrict().getDistrictId() != null)
			{
				
				countVO.setDistrictCount(getMemberShipCount("District", userAddress.getDistrict().getDistrictId(), electionYear, constituencyId,null));
				countVO.setDistrictTotVoters(getTotalVotersByLocationId(userAddress.getDistrict().getDistrictId(), "District", electionId, constituencyId,null));
				String  disPer =null;
				if(countVO.getDistrictTotVoters() != null && countVO.getDistrictTotVoters() > 0)
				 disPer = (new BigDecimal((countVO.getDistrictCount() * 100.0)/countVO.getDistrictTotVoters().doubleValue()).setScale(2, BigDecimal.ROUND_HALF_UP)).toString();
			     countVO.setDistrictPerc(disPer);
				
			}
			
			Long parliamentConId = delimitationConstituencyAssemblyDetailsDAO.getParliamentConstituencyId(constituencyId);
			if(parliamentConId != null && parliamentConId > 0)
			{
				List<Long> assConstituencyIds = delimitationConstituencyAssemblyDetailsDAO.getAssemblyConstituencyIdsByPCId(parliamentConId);
				if(assConstituencyIds != null && assConstituencyIds.size() > 0)
				{
					countVO.setParConsCount(getMemberShipCount("Parliament", null, electionYear, null,assConstituencyIds));
					countVO.setParConsTotVoters(getTotalVotersByLocationId(null, "Parliament", electionId, null,assConstituencyIds));
					String  parConPer =null;
					if(countVO.getParConsTotVoters() != null && countVO.getParConsTotVoters() > 0)
						parConPer = (new BigDecimal((countVO.getParConsCount() * 100.0)/countVO.getParConsTotVoters().doubleValue()).setScale(2, BigDecimal.ROUND_HALF_UP)).toString();
				     countVO.setParConsPerc(parConPer);
				}
			}
			
		}	
			
		}catch (Exception e) {
			LOG.error("Exception Occured in getTotalMemberShipRegistrationsInCadreLocation() method, Exception - ",e);
		}
		return countVO;
	}*/
	
	
	
  public RegisteredMembershipCountVO getTotalMemberShipRegistrationsInCadreLocation(Long tdpCadreId,Long pcId,String pcType)
  {
	 RegisteredMembershipCountVO countVO = new RegisteredMembershipCountVO();
	  try{
			
			Long electionId = 258l;//2014
			Long electionYear = 2014l;
			
			if(pcType !=null && !pcType.isEmpty()){
				
				
				Long parliamentConId = 0l;
				Long districtId =0l;
				
				if(pcType.equalsIgnoreCase("Assembly")){
					List<Long> assembly = new ArrayList<Long>();
					assembly.add(pcId);
					
					
					List<Object[]> parlamentWithDistrict = delimitationConstituencyAssemblyDetailsDAO.findLatestParliamentsAndDistrictForAssembly(assembly);
					
					List<BasicVO> list=new ArrayList<BasicVO>();
					if(parlamentWithDistrict !=null){
						for(Object[] parliamentAndDistrict:parlamentWithDistrict){
							BasicVO vo=new BasicVO();
							vo.setId(parliamentAndDistrict[1] !=null ? (Long)parliamentAndDistrict[1]:0l);//parliamentId
							vo.setName(parliamentAndDistrict[2] !=null ? parliamentAndDistrict[2].toString():"");//parliamentName
							vo.setDistrictId(parliamentAndDistrict[3] !=null ? (Long)parliamentAndDistrict[3]:0l);
							vo.setDistrict(parliamentAndDistrict[4] !=null ? parliamentAndDistrict[4].toString() :"");
							
							list.add(vo);
						}
					}
					
					if(list !=null && list.size()>0){
						parliamentConId=list.get(0).getId();
						districtId=list.get(0).getDistrictId();
					}
					
				}else if(pcType.trim().equalsIgnoreCase("Parliament")){
					parliamentConId = pcId;
					
					List<Long> districts = delimitationConstituencyAssemblyDetailsDAO.findDistrictsBYParliament(parliamentConId);
					
					districtId = districts.get(0);
				}
				
				
				if(pcType.trim().equalsIgnoreCase("Assembly")){
					
					//Assembly Performance
					   countVO.setConsTotalVoters(getTotalAvailableVotersByLocationId(pcId, "Constituency", electionId, pcId,null));
					   countVO.setConstituencyCount(getMemberShipCount("Constituency", pcId, electionYear, pcId,null));
					   countVO.setConstiPerc(calculatePercentage(countVO.getConsTotalVoters(), countVO.getConstituencyCount()));
				}
				
				//parliament performance
				if(parliamentConId != null && parliamentConId > 0)
				{
					List<Long> assConstituencyIds = delimitationConstituencyAssemblyDetailsDAO.getAssemblyConstituencyIdsByPCId(parliamentConId);
					if(assConstituencyIds != null && assConstituencyIds.size() > 0)
					{
						countVO.setParConsCount(getMemberShipCount("Parliament", null, electionYear, null,assConstituencyIds));
						countVO.setParConsTotVoters(getTotalAvailableVotersByLocationId(null, "Parliament", electionId, null,assConstituencyIds));
						countVO.setParConsPerc(calculatePercentage(countVO.getParConsTotVoters(), countVO.getParConsCount()));
					}
				}
			   
				//  district performance
				if(districtId !=null && districtId > 0){
					countVO.setDistrictCount(getMemberShipCount("District", districtId, electionYear, pcId,null));
				    countVO.setDistrictTotVoters(getTotalAvailableVotersByLocationId(districtId, "District", electionId, pcId,null));
				    countVO.setDistrictPerc(calculatePercentage(countVO.getDistrictTotVoters(),countVO.getDistrictCount()));
				}
			   
			}
			else{
					//UserAddress userAddress = tdpCadreDAO.get(tdpCadreId).getUserAddress();
				List<Object[]> cadreAddressDetls  = tdpCadreDAO.getCadrAddressDetailsByCadred(tdpCadreId);
				Long ownVoterId = 0L;
				Long familyVoterId = 0L;
				AddressVO userAddress1 = new AddressVO();
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
						
						ownVoterId = commonMethodsUtilService.getLongValueForObject(address[18]);
						familyVoterId = commonMethodsUtilService.getLongValueForObject(address[19]);
						userAddress1.setId(commonMethodsUtilService.getLongValueForObject(address[20]));
					}
					
				}
				
				Long publicationDateId = userAddress1.getId();
				Long boothId_2014  =0L;
				if(publicationDateId != null && publicationDateId.longValue()>11){
					Long voterId = ownVoterId != null && ownVoterId.longValue()>0L?  ownVoterId : familyVoterId;
					if(voterId != null && voterId.longValue()>0L){
						Object[] boothObjectArr = boothPublicationVoterDAO.getBoothDetailsByVoterId(voterId.toString());//publication date id = 11
						boothId_2014 = commonMethodsUtilService.getLongValueForObject(boothObjectArr[1]);
					}
						
				}
				
					if(userAddress1 != null)
					{
					   Long constituencyId = userAddress1.getConstituencyId();
					   countVO.setAreaType(userAddress1.getAreaTypeStr());
				     
					   countVO.setConsTotalVoters(getTotalAvailableVotersByLocationId(constituencyId, "Constituency", electionId, constituencyId,null));
					   countVO.setConstituencyCount(getMemberShipCount("Constituency", constituencyId, electionYear, constituencyId,null));
					   countVO.setConstiPerc(calculatePercentage(countVO.getConsTotalVoters(), countVO.getConstituencyCount()));
					
					 if(userAddress1.getBoothId() != null && userAddress1.getBoothId() != null)
					 {
						if(boothId_2014 != null && boothId_2014.longValue()>0L){
							//if(countVO.getBoothCount() == null ) // only we are showing 11 publication booth cadre membership perc.
								countVO.setBoothCount(0L);
						//	if(countVO.getBoothTotVoters() == null )
								countVO.setBoothTotVoters(0L);
							
							Long membershipCount = getMemberShipCount("Booth", boothId_2014, electionYear, constituencyId,null);
							Long voterCount = getTotalAvailableVotersByLocationId(boothId_2014, "booth", electionId, constituencyId,null);
							if(membershipCount != null && membershipCount.longValue()>0L)
								countVO.setBoothCount(countVO.getBoothCount()+membershipCount);
							if(voterCount != null && voterCount.longValue()>0L)
								countVO.setBoothTotVoters(countVO.getBoothTotVoters()+voterCount);
						}else{
							countVO.setBoothCount(getMemberShipCount("Booth", userAddress1.getBoothId(), electionYear, constituencyId,null))	;
							countVO.setBoothTotVoters(getTotalAvailableVotersByLocationId(userAddress1.getBoothId(), "booth", electionId, constituencyId,null));
						}
						
						countVO.setBoothPerc(calculatePercentage(countVO.getBoothTotVoters(),countVO.getBoothCount()));
					 }
					
					if(userAddress1.getLocalElectionBodyId() != null && userAddress1.getLocalElectionBodyId().longValue()>0L)
					{
						countVO.setMandalCount(getMemberShipCount("Muncipality", userAddress1.getLocalElectionBodyId(), electionYear, constituencyId,null));
						countVO.setMandalTotVoters(getTotalAvailableVotersByLocationId(userAddress1.getLocalElectionBodyId(), "Muncipality", electionId, constituencyId,null));
						countVO.setMandalPerc(calculatePercentage(countVO.getMandalTotVoters(), countVO.getMandalCount()));
						countVO.setCadreLocation("Muncipality");
						
						LocalElectionBody localElectionBody = localElectionBodyDAO.get(userAddress1.getLocalElectionBodyId());
						countVO.setMandalNameStr(localElectionBody.getTehsil().getTehsilName());
						
					}
					else
					{
						 countVO.setCadreLocation("Mandal");
						if(userAddress1.getPanchaytId() != null && userAddress1.getPanchaytId() != null)
						{
							countVO.setPanchayatCount(getMemberShipCount("Panchayat", userAddress1.getPanchaytId(), electionYear, constituencyId,null));
							countVO.setPanchayatTotVoters(getTotalAvailableVotersByLocationId(userAddress1.getPanchaytId(), "Panchayat", electionId, constituencyId,null));
							countVO.setPanchPerc(calculatePercentage(countVO.getPanchayatTotVoters(), countVO.getPanchayatCount()));
							if(countVO.getPanchayatTotVoters() != null && countVO.getPanchayatTotVoters() > 0)
							{
						        if(userAddress1.getTehsilId() != null && userAddress1.getTehsilId() != null)
								{
								  countVO.setMandalCount(getMemberShipCount("Mandal", userAddress1.getTehsilId(), electionYear, constituencyId,null));	
								  countVO.setMandalTotVoters(getTotalAvailableVotersByLocationId(userAddress1.getTehsilId(), "Mandal", electionId, constituencyId,null));
								  countVO.setMandalPerc(calculatePercentage(countVO.getMandalTotVoters(), countVO.getMandalCount()));
								}
							}
						}
					}
					
					if(userAddress1.getDistrictId() != null && userAddress1.getDistrictId() != null)
					{
						
						countVO.setDistrictCount(getMemberShipCount("District", userAddress1.getDistrictId(), electionYear, constituencyId,null));
						countVO.setDistrictTotVoters(getTotalAvailableVotersByLocationId(userAddress1.getDistrictId(), "District", electionId, constituencyId,null));
						countVO.setDistrictPerc(calculatePercentage(countVO.getDistrictTotVoters(),countVO.getDistrictCount()));
					}
					
					Long parliamentConId = delimitationConstituencyAssemblyDetailsDAO.getParliamentConstituencyId(constituencyId);
					if(parliamentConId != null && parliamentConId > 0)
					{
						List<Long> assConstituencyIds = delimitationConstituencyAssemblyDetailsDAO.getAssemblyConstituencyIdsByPCId(parliamentConId);
						if(assConstituencyIds != null && assConstituencyIds.size() > 0)
						{
							countVO.setParConsCount(getMemberShipCount("Parliament", null, electionYear, null,assConstituencyIds));
							countVO.setParConsTotVoters(getTotalAvailableVotersByLocationId(null, "Parliament", electionId, null,assConstituencyIds));
							countVO.setParConsPerc(calculatePercentage(countVO.getParConsTotVoters(), countVO.getParConsCount()));
						}
					}
				}	
			}
			
			
		}catch (Exception e) {
			LOG.error("Exception Occured in getTotalMemberShipRegistrationsInCadreLocation() method, Exception - ",e);
		}
		return countVO;
	}
	
  public Long getTotalAvailableVotersByLocationId(Long locationId,String locationType,Long electionId,Long constituencyId,List<Long> constituencyIdsList)
	{
		try{
			  return boothPublicationVoterDAO.getTotalAvailableVotesByLocationId(locationId, locationType, constituencyId,constituencyIdsList);
		}catch (Exception e) {
			LOG.error("Exception Occured in getTotalAvailableVotesByLocationId() method, Exception - ",e);
			return null;
		}
	}
	
	public Long getTotalVotersByLocationId(Long locationId,String locationType,Long electionId,Long constituencyId,List<Long> constituencyIdsList)
	{
		try{
			if(electionId.longValue() == 38l && locationType.equalsIgnoreCase("Panchayat"))
			{
				List<Long> boothIdsList = panchayatHamletDAO.getBoothIdsByPanchayatId(locationId, electionId);
				if(boothIdsList != null && boothIdsList.size() > 0)
					return boothConstituencyElectionDAO.getTotalVotersByBoothIdsList(boothIdsList, electionId);
				return null;
			}
			else
			  return boothConstituencyElectionDAO.getTotalVotesByLocationId(locationId, locationType, electionId, constituencyId,constituencyIdsList);
			
			
		}catch (Exception e) {
			LOG.error("Exception Occured in getTotalVotersByLocationId() method, Exception - ",e);
			return null;
		}
	}
	
	
	public String calculatePercentage(Long totalVoters,Long count)
	{
		try{
			if(totalVoters != null && totalVoters.longValue() > 0l && count != null && count.longValue()>0L)
			  return (new BigDecimal((count * 100.0)/totalVoters.doubleValue()).setScale(2, BigDecimal.ROUND_HALF_UP)).toString();
			else{
				return "0";
			}
			
		}catch (Exception e) {
			LOG.error("Exception Occured in calculatePercentage() method, Exception - ",e);
		}
		return null;
	}
	
	public Long getMemberShipCount(String locationType, Long locationId,Long year,Long constituencyId,List<Long> constituencyIds)
	{
		Long count = 0l;
		try{
			count =  tdpCadreDAO.getMemberShipRegistrationsInCadreLocation(locationType, locationId, year,constituencyId,constituencyIds);
			return (count != null?count:0l);
		}catch (Exception e) {
			LOG.error("Exception Occured in setMemberShipCount() method, Exception - ",e);
			return count;
		}
		
	}
	
	public List<RegisteredMembershipCountVO> getElectionPerformanceInCadreLocation(Long tdpCadreId,String voterCardNo)
	{
		List<RegisteredMembershipCountVO> resultList = new ArrayList<RegisteredMembershipCountVO>();
		try{
			List<Object[]> voterDetails = null;
			if(voterCardNo != null && !voterCardNo.isEmpty())
			{
				voterDetails = boothPublicationVoterDAO.getConstyPublicationIdByVoterId(voterCardNo);
				if(voterDetails != null && voterDetails.size()>0)
				{
					for (Object[] voter : voterDetails) {
						Long publicationDateId = voter[2] != null ? Long.valueOf(voter[2].toString()):0L;
						if(publicationDateId != null && publicationDateId.longValue() == IConstants.VOTER_DATA_PUBLICATION_ID)
						{
							Long voterId = voter[3] != null ? Long.valueOf(voter[3].toString()):0L;
							List<TdpCadre> tdpCadreList = tdpCadreDAO.getVoterByVoterId(voterId,1L);
							if(tdpCadreList != null && tdpCadreList.size()>0)
							{
								TdpCadre tdpCadre =  (TdpCadre) tdpCadreList.get(0);
								
								if(tdpCadre != null)
								{
									tdpCadreId = tdpCadre.getTdpCadreId();
								}
							}
							break;
						}
					}
				}
			}
			
			
			if(tdpCadreId != null && tdpCadreId > 0L)
			{
				TdpCadre tdpCadre = tdpCadreDAO.get(tdpCadreId);
				UserAddress userAddress = tdpCadre.getUserAddress();
				if(userAddress != null)
				{
					List<Long> partyIds = new ArrayList<Long>();
					partyIds.add(872l);//TDP partyId
					partyIds.add(163l);//BJP partyId
					
					if(userAddress != null)
					{
						
						RegisteredMembershipCountVO countVO = null;
						Booth booth = userAddress.getBooth();
						Long publicationDateId = booth != null ?booth.getPublicationDate().getPublicationDateId():0L;
						if(publicationDateId != null && publicationDateId.longValue()>11){
							Long voterId = tdpCadre.getVoterId() != null ?  tdpCadre.getVoterId() :  tdpCadre.getFamilyVoterId();
							Object[] boothObjectArr = boothPublicationVoterDAO.getBoothDetailsByVoterId(voterId.toString());//publication date id = 11
							Long boothId = commonMethodsUtilService.getLongValueForObject(boothObjectArr[1]);
							if(boothId != null && boothId.longValue()>0L)
								countVO = setElectionPerformanceDetailsInCadreLocation(2014l, userAddress, partyIds,boothId);
						}
						else
							countVO = setElectionPerformanceDetailsInCadreLocation(2014l, userAddress, partyIds,null);
						if(countVO != null)
							resultList.add(countVO);
						
						countVO = setElectionPerformanceDetailsInCadreLocation(2009l, userAddress, partyIds,null);
						if(countVO != null)
							resultList.add(countVO);
					}
				}
			}
			else if(voterCardNo != null && !voterCardNo.isEmpty())
			{
				CadreOverviewVO returnVO = new CadreOverviewVO();
				
					for (Object[] voter : voterDetails) {
						Long publicationDateId = voter[2] != null ? Long.valueOf(voter[2].toString()):0L;
						if(publicationDateId != null && publicationDateId.longValue() == IConstants.VOTER_DATA_PUBLICATION_ID)
						{
							Long constituencyId = voter[0] != null ? Long.valueOf(voter[0].toString()):0L;
							Long boothId = voter[1] != null ? Long.valueOf(voter[1].toString()):0L;
							Long districtId = voter[7] != null ? Long.valueOf(voter[7].toString()):0L;
							String districtName= voter[12] != null ? voter[12].toString():"";
							String constituencyName= voter[11] != null ? voter[11].toString():"";
							
							Long voterId = voter[3] != null ? Long.valueOf(voter[3].toString()):0L;
							List<Long> voterIdsList = new ArrayList<Long>(0);
							voterIdsList.add(voterId);
							
							/*String voterName =  voter[4] != null ? voter[4].toString():"";
							String age =  voter[5] != null ? voter[5].toString():"";
							String gender =  voter[6] != null ? voter[6].toString():"";
							String relativeName =  voter[8] != null ? voter[8].toString():"";
							String relativeType =  voter[10] != null ? voter[10].toString():"";*/
							String hNo = voter[9] != null ? voter[9].toString():"";;
							String partNo= voter[13] != null ? voter[13].toString():"";
							LocalElectionBody localElectionBody = null;//voter[14] != null ? (LocalElectionBody) voter[14]:null;
							Long panchayatId = voter[15] != null ? Long.valueOf(voter[15].toString()):0L;
							String panchayatName= voter[16] != null ? voter[16].toString():"";
							Long tehsilId = voter[17] != null ? Long.valueOf(voter[17].toString()):0L;
							String tehsilName= voter[18] != null ? voter[18].toString():"";
							Long stateId = voter[19] != null ? Long.valueOf(voter[19].toString()):0L;
							String stateName= voter[20] != null ? voter[20].toString():"";
							
							List<Object[]> parliamentList = delimitationConstituencyAssemblyDetailsDAO.findLatestParliamentForAssembly(constituencyId);
							if(parliamentList != null && parliamentList.size()>0)
							{
								Object[] parliament = parliamentList.get(0);
								if(parliament == null)
								{
									if(parliamentList.size()>1)
										parliament = parliamentList.get(1);
								}
								else
								{
									Long parliamentId = parliament[0] != null ? Long.valueOf(parliament[0].toString()):0L;
									String parliamentName= parliament[1] != null ? parliament[1].toString():"";
									
									returnVO.setParliamentId(parliamentId);
									returnVO.setParlimentName(parliamentName);
								}
							}
						
							returnVO.setConstituencyId(constituencyId);									
							returnVO.setConstituencyName(constituencyName);
							returnVO.setBoothId(boothId);	
							returnVO.setPartNo(Long.valueOf(partNo));
							
							List<Object[]> voterCasteInfo = userVoterDetailsDAO.getCasteByVoterId(1L,voterIdsList);
							if(voterCasteInfo != null && voterCasteInfo.size()>0)
							{
								Object[] casteInfo = voterCasteInfo.get(0);
								String casteName = casteInfo[1] != null ? casteInfo[1].toString().trim():"";
								returnVO.setCasteName(casteName);
							}
															
							returnVO.setDistrictId(districtId);
							returnVO.setDistrictName(districtName);
							returnVO.setHouseNo(hNo);									
							//returnVO.setImagePath(filePath);
							returnVO.setPanchayatId(panchayatId);
							returnVO.setPanchayatName(panchayatName);
							returnVO.setTehsilId(tehsilId);
							returnVO.setTehsilName(tehsilName);
							returnVO.setStateId(stateId);
							returnVO.setStateName(stateName);
							if(localElectionBody != null)
								returnVO.setLocalElectionBodyId(localElectionBody.getLocalElectionBodyId().toString());
													
							returnVO.setVoterId(voterId);
							returnVO.setVoterCardNo(voterCardNo);
							
							}
							
							UserAddress userAddress = new UserAddress();
							if(returnVO.getBoothId() != null && returnVO.getBoothId() > 0)
							userAddress.setBooth(boothDAO.get(returnVO.getBoothId()));
							if(returnVO.getPanchayatId() != null && returnVO.getPanchayatId() > 0)
							userAddress.setPanchayat(panchayatDAO.get(returnVO.getPanchayatId()));
							if(returnVO.getTehsilId() != null && returnVO.getTehsilId() > 0)
							userAddress.setTehsil(tehsilDAO.get(returnVO.getTehsilId()));
							
							if(returnVO.getLocalElectionBodyId() != null && Long.valueOf(returnVO.getLocalElectionBodyId())>0L)
								userAddress.setLocalElectionBody(localElectionBodyDAO.get(Long.valueOf(returnVO.getLocalElectionBodyId())));
							if(returnVO.getConstituencyId() != null && returnVO.getConstituencyId() > 0)
							userAddress.setConstituency(constituencyDAO.get(returnVO.getConstituencyId()));
							if(returnVO.getDistrictId() != null && returnVO.getDistrictId() > 0)
							userAddress.setDistrict(districtDAO.get(returnVO.getDistrictId()));
							userAddress.setState(stateDAO.get(1L));
							
							if(userAddress != null)
							{
								List<Long> partyIds = new ArrayList<Long>();
								partyIds.add(872l);//TDP partyId
								partyIds.add(163l);//BJP partyId
								
								if(userAddress != null)
								{
									RegisteredMembershipCountVO countVO = null;
									 countVO = setElectionPerformanceDetailsInCadreLocation(2014l, userAddress, partyIds,null);
									if(countVO != null)
										resultList.add(countVO);
									
								}
						  }
							
							if(resultList != null && resultList.size()>0)
								returnVO.setElectionResultsPerfList(resultList);
							
							break; // iteration only once 
						}
					}
		}catch (Exception e) {
			LOG.error("Exception Occured in getElectionPerformanceInCadreLocation() method, Exception - ",e);
		}
		return resultList;
	}
	
	public Long getValidVotesforBoothByElectionId(Long boothId,Long electionId){
		
		try {
			return boothConstituencyElectionDAO.getValidVotesforBoothByElectionId(boothId,electionId);
		} catch (Exception e) {
			LOG.error("Exception Occured in getValidVotesforBoothByElectionId() method, Exception - ",e);
		}
		return null;
	}
	public RegisteredMembershipCountVO setElectionPerformanceDetailsInCadreLocation(Long year,UserAddress userAddress,List<Long> partyIds,Long boothId_2014)
	{
		RegisteredMembershipCountVO countVO = new RegisteredMembershipCountVO();
		try{
			Long electionId = 258l;//2014 Assembly
			if(year.longValue() == 2009l)
				electionId = 38l;
			
			Long constituencyId = userAddress.getConstituency().getConstituencyId();
			countVO.setAreaType(userAddress.getConstituency().getAreaType());
			
			countVO.setYear(year.toString());
			countVO.setConsTotalVoters(getTotalVotersByLocationId(constituencyId,"Constituency", electionId, constituencyId,null));
			countVO.setConstituencyCount(getTotalVotesEarnedForLocation(constituencyId, "Constituency", electionId, constituencyId, partyIds,null));
			countVO.setConstiPerc(calculatePercentage(countVO.getConsTotalVoters(),countVO.getConstituencyCount()));
			
			
			
			if(userAddress.getBooth() != null && userAddress.getBooth().getBoothId() != null)
			{
				Long boothId = userAddress.getBooth().getBoothId();
				if(boothId_2014 != null && boothId_2014.longValue()>0L)
					boothId = boothId_2014;
				Long validVotesCount = getValidVotesforBoothByElectionId(boothId,electionId);
				if(validVotesCount != null && validVotesCount.longValue()>0){
					countVO.setBoothCount(getTotalVotesEarnedForLocation(boothId, "booth", electionId, constituencyId, partyIds,null))	;
					countVO.setBoothTotVoters(getTotalVotersByLocationId(boothId, "booth", electionId, constituencyId,null));
					if(countVO.getBoothCount() != null && countVO.getBoothCount().longValue()>0L)
						countVO.setBoothPerc(calculatePercentage(countVO.getBoothTotVoters(), countVO.getBoothCount()));
					else{
						countVO.setBoothPerc("0"); 
						countVO.setReasonStr("TDP Not gain any votes.");
					}
				}
				else if(validVotesCount != null && validVotesCount.longValue() ==0L)
				{
					countVO.setBoothPerc("0");
					countVO.setReasonStr("Polling Boycotted.");
				}
				else{
					countVO.setBoothPerc("0");
					countVO.setReasonStr("Data Not Available.");
				}
			}
			
			
			boolean municpal2009=true;
			
			if(electionId == 38l){
				Long publicationDateId=3l;
				if(userAddress.getLocalElectionBody() != null && userAddress.getLocalElectionBody().getLocalElectionBodyId() != null){
					
					Long electionBodyId=boothDAO.getLocalElectionBodyDetails(userAddress.getLocalElectionBody().getLocalElectionBodyId(),constituencyId,publicationDateId);
					if(electionBodyId == null){
						municpal2009=false;
					}
					
				}
			}
			
			if(municpal2009){
				if(userAddress.getLocalElectionBody() != null && userAddress.getLocalElectionBody().getLocalElectionBodyId() != null)
				{
					countVO.setMandalCount(getTotalVotesEarnedForLocation(userAddress.getLocalElectionBody().getLocalElectionBodyId(), "Muncipality", electionId, constituencyId, partyIds,null));
					countVO.setMandalTotVoters(getTotalVotersByLocationId(userAddress.getLocalElectionBody().getLocalElectionBodyId(), "Muncipality", electionId, constituencyId,null));
					countVO.setMandalPerc(calculatePercentage(countVO.getMandalTotVoters(), countVO.getMandalCount()));
					countVO.setCadreLocation("Muncipality");
					LocalElectionBody localElectionBody = localElectionBodyDAO.get(userAddress.getLocalElectionBody().getLocalElectionBodyId());
					countVO.setMandalNameStr(localElectionBody.getTehsil().getTehsilName());
				}
				else
				{
					countVO.setCadreLocation("Mandal");
					if(userAddress.getPanchayat() != null && userAddress.getPanchayat().getPanchayatId() != null)
					{
						countVO.setPanchayatCount(getTotalVotesEarnedForLocation(userAddress.getPanchayat().getPanchayatId(), "Panchayat", electionId, constituencyId, partyIds,null));
						countVO.setPanchayatTotVoters(getTotalVotersByLocationId(userAddress.getPanchayat().getPanchayatId(), "Panchayat", electionId, constituencyId,null));
						if(countVO.getPanchayatCount() != null)
							countVO.setPanchPerc(calculatePercentage(countVO.getPanchayatTotVoters(), countVO.getPanchayatCount()));
						
						if(countVO.getPanchayatTotVoters() != null && countVO.getPanchayatTotVoters() > 0)
						{
					        if(userAddress.getTehsil() != null && userAddress.getTehsil().getTehsilId() != null)
							{
							  countVO.setMandalCount(getTotalVotesEarnedForLocation(userAddress.getTehsil().getTehsilId(), "Mandal", electionId, constituencyId, partyIds,null));	
							  countVO.setMandalTotVoters(getTotalVotersByLocationId(userAddress.getTehsil().getTehsilId(), "Mandal", electionId, constituencyId,null));
							  countVO.setMandalPerc(calculatePercentage(countVO.getMandalTotVoters(), countVO.getMandalCount()));
							}
						}
					}
					
				}
			}
			else{
				if(userAddress.getTehsil() != null && userAddress.getTehsil().getTehsilId() != null)
				{
				  countVO.setMandalCount(getTotalVotesEarnedForLocation(userAddress.getTehsil().getTehsilId(), "Mandal", electionId, constituencyId, partyIds,null));	
				  countVO.setMandalTotVoters(getTotalVotersByLocationId(userAddress.getTehsil().getTehsilId(), "Mandal", electionId, constituencyId,null));
				  countVO.setMandalPerc(calculatePercentage(countVO.getMandalTotVoters(), countVO.getMandalCount()));
				}
			}
			
			if(userAddress.getDistrict() != null && userAddress.getDistrict().getDistrictId() != null)
			{
				countVO.setDistrictCount(getTotalVotesEarnedForLocation(userAddress.getDistrict().getDistrictId(), "District", electionId, constituencyId, partyIds,null));
				countVO.setDistrictTotVoters(getTotalVotersByLocationId(userAddress.getDistrict().getDistrictId(), "District", electionId, constituencyId,null));
				countVO.setDistrictPerc(calculatePercentage(countVO.getDistrictTotVoters(), countVO.getDistrictCount()));
			}
			
			Long parliamentConId = delimitationConstituencyAssemblyDetailsDAO.getParliamentConstituencyId(constituencyId);
			if(parliamentConId != null && parliamentConId > 0)
			{
					List<Long> assConstituencyIds = delimitationConstituencyAssemblyDetailsDAO.getAssemblyConstituencyIdsByPCId(parliamentConId);
					if(assConstituencyIds != null && assConstituencyIds.size() > 0)
					{
						countVO.setParConsCount(getTotalVotesEarnedForLocation(null, "Parliament", electionId, null, partyIds,assConstituencyIds));
						countVO.setParConsTotVoters(getTotalVotersByLocationId(null, "Parliament", electionId, null,assConstituencyIds));
						countVO.setParConsPerc(calculatePercentage(countVO.getParConsTotVoters(), countVO.getParConsCount()));
					}
			}
			
			
		}catch (Exception e) {
			
			LOG.error(" Exception Occured in setElectionPerformanceDetailsInCadreLocation() method, Exception - ",e);
		}
		return countVO;
	}
	
	
	public Long getTotalVotesEarnedForLocation(Long locationId,String locationType,Long electionId,Long constituencyId,List<Long> partyIds,List<Long> constituencyIds)
	{
		Long count = 0l;
		try{
			if(electionId.longValue() == 38l && locationType.equalsIgnoreCase("Panchayat"))
			{
				List<Long> boothIdsList = panchayatHamletDAO.getBoothIdsByPanchayatId(locationId, electionId);
				if(boothIdsList != null && boothIdsList.size() > 0);
				  return candidateBoothResultDAO.getTotalEarnedVotesByBoothIdsList(boothIdsList, electionId,partyIds);
			}
			else
			{	
		       count = candidateBoothResultDAO.getTotalAndEarnedVotesForLocation(locationId, locationType, electionId, constituencyId, partyIds,constituencyIds);
		       return (count != null?count:0l);	
			}
		}catch (Exception e) {
			LOG.error(" Exception Occured in getTotalVotesEarnedForLocation() method, Exception - ",e);
		}
		return count;
	}
	
	
	public List<GrievanceAmountVO> getApprovedFinancialSupprotForCadre(Long tdpCadreId)
	{
		List<GrievanceAmountVO> resultList = new ArrayList<GrievanceAmountVO>();
		try{
			String memberShipId = tdpCadreDAO.getMemberShipIdByCadreId(tdpCadreId);
			if(memberShipId != null)
			{
				String regex = "[0-9]+";
			  List<Object[]> list = tdpCadreDAO.getPartyApprovedFundByMembershipId(memberShipId);
			  setGrievanceAmountVO(resultList, list);
			 
			  List<Object[]> list2 = tdpCadreDAO.getGovtApprovedFundByMembershipId(memberShipId);
			  setGrievanceAmountVO(resultList, list2);
			  
			  List<Object[]> expectedAmtList = tdpCadreDAO.getRequestedAmountByMembershipId(memberShipId);
			  Long amount = 0l;
			  Long count = 0l;
			  if(expectedAmtList != null && expectedAmtList.size() > 0)
			  {
				  for(Object[] params:expectedAmtList)
				  {
					  if(params[0] != null && params[0].toString().matches(regex))
						 amount = amount + (Long)params[0]; 
					  if(params[1] != null && params[1].toString().matches(regex))
						  amount = amount + (Long)params[1]; 
					  
					  count = params[2] != null?Long.parseLong(params[2].toString()):0l;
					  
					  
				  }
			  }
			  
			  List<Object[]> educationalAmt = tdpCadreDAO.getEducationalRequestedAmountByMembershipId(memberShipId);
			  if(educationalAmt != null && educationalAmt.size() > 0)
			  {
				  for(Object[] params:educationalAmt)
				  {
					  if(params[0] != null && params[0].toString().matches(regex))
						amount = amount + (Long)params[0]; 
					  
					  count = count + (params[1] != null?Long.parseLong(params[1].toString()):0l);
				  }
			  }
			  resultList.get(0).setTotalRequests(amount); 
			  resultList.get(0).setTotalComplaints(count);
			  
			}
			
		}catch (Exception e) {
			LOG.error("Exception Occured in getApprovedFinancialSupprotForCadre() method, Exception - ",e);
		}
		
		return resultList;
		
	}
	
	/*public void setGrievanceAmountVO(List<GrievanceAmountVO> resultList,List<Object[]> list,String name)
	{
		try{
			if(list != null && list.size() > 0)
			  {
				String regex = "[0-9]+";
				  GrievanceAmountVO amountVO = new GrievanceAmountVO();
				  amountVO.setName(name);
				  Object[] obj = list.get(0);
				  amountVO.setCount(obj[0] != null?Long.parseLong(obj[0].toString()):0l);
				  if(obj[1] != null && obj[1].toString().matches(regex))
					  amountVO.setDonationAmount((Long)obj[1]); 
				  resultList.add(amountVO);
			  }
			
		}catch (Exception e) {
			LOG.error(" Exception Occured in setGrievanceAmountVO() method, Exception - ",e);
		}
	}*/
	
	public void setGrievanceAmountVO(List<GrievanceAmountVO> resultList,List<Object[]> list)
	{
		try{
			if(list != null && list.size() > 0)
			  {
				String regex = "[0-9]+";
				for(Object[] obj: list)
				{
					  GrievanceAmountVO amountVO = new GrievanceAmountVO();
					  amountVO.setName(obj[2] != null ?obj[2].toString():"");
					  amountVO.setCount(obj[0] != null?Long.parseLong(obj[0].toString()):0l);
					  if(obj[1] != null && obj[1].toString().matches(regex))
						  amountVO.setDonationAmount((Long)obj[1]); 
					  resultList.add(amountVO);
				}
				
				  
			  }
			
		}catch (Exception e) {
			LOG.error(" Exception Occured in setGrievanceAmountVO() method, Exception - ",e);
		}
	}
	
	public void getPartyPositionForCadre(List<Long> cadreIdsList, Map<Long,String> cadrePartyPositionMap)
	 {
		
		 try {
			 List<Object[]> partyPositionDetails= tdpCommitteeMemberDAO.getPartyPositionsBycadreIdsList(cadreIdsList);
			 if(partyPositionDetails !=null && partyPositionDetails.size()>0)
			 {
				 for (Object[] partyPosition : partyPositionDetails) {

					 String level=partyPosition[0] != null ? partyPosition[0].toString() : "" ;
					 String role=partyPosition[1] != null ? partyPosition[1].toString() : "";
					 String state = commonMethodsUtilService.getStringValueForObject(partyPosition[6]);
					 
					 String commiteestr=partyPosition[2] != null ? partyPosition[2].toString() : "";
					 
					 if(level != null && !level.isEmpty()&&level.equalsIgnoreCase("state"))
					 {
						 level = state+" "+level;
					 }
					 String partyPositionStr = level +" " +role+" ( "+commiteestr+" )";

					 Long tdpCadreId = commonMethodsUtilService.getLongValueForObject(partyPosition[5]);
					
					 if(cadrePartyPositionMap.get(tdpCadreId) != null)
					 {
						String existingPartyPositionStr = cadrePartyPositionMap.get(tdpCadreId);
						 partyPositionStr = partyPositionStr+","+existingPartyPositionStr;
					 }
					 cadrePartyPositionMap.put(tdpCadreId, partyPositionStr);
				 }
			 }

		 } catch (Exception e) {
		 LOG.error("Exception raised in getPartyPositionForCadre method in CadreDetailsService.",e);
		 }

	 }
	
	public List<TdpCadreFamilyDetailsVO> getCadreFamilyDetails(Long tdpCadreId)
	{
		List<TdpCadreFamilyDetailsVO> resultList = null;
		try{
			resultList = cadreRegistrationService.getFamilyDetailsByCadreId(tdpCadreId);
			if(resultList != null && resultList.size() > 0)
			{
				List<String> voterIdCardNoList = new ArrayList<String>(0);
				for(TdpCadreFamilyDetailsVO detailsVO:resultList)
					if(detailsVO.getVotercardNo() != null)
						voterIdCardNoList.add(detailsVO.getVotercardNo());
				
				 List<Object[]> voterImages =  boothPublicationVoterDAO.getVoterImagesVoterIdcardNo(voterIdCardNoList);
				 Map<String,String> voterImagMap = new HashMap<String, String>();
				 if(voterImages != null && voterImages.size() > 0)
				 {
					 for(Object[] params : voterImages)
					 {
						 TdpCadreFamilyDetailsVO VO = getMatchedTdpCadreFamilyDetailsVO(resultList, params[0].toString());
						 VO.setImagePath("http://mytdp.com/"+IConstants.VOTER_IMG_FOLDER_PATH+"/"+(params[1] != null ? params[1].toString() : ""));
						 voterImagMap.put(params[0].toString(), params[1].toString());
					 }
				 }
				
				if(voterIdCardNoList != null && voterIdCardNoList.size() > 0)
				{
					List<Object[]> list = tdpCadreDAO.getSurveyPaticipatedCountByVoterIdcardNoList(voterIdCardNoList);
					if(list != null && list.size() > 0)
					{
						for(Object[] params: list)
						{
							if(params[1] != null)
							{
								TdpCadreFamilyDetailsVO VO =getMatchedTdpCadreFamilyDetailsVO(resultList, params[1].toString());
							    if(VO != null)
							    	VO.setCount(params[0] != null?Long.parseLong(params[0].toString()):0l);
							}
						}
					}
					
					//0.voterCardNo,1.cadreId,2.memberShipNo,3.image,4.isDeleted,5.delete ReasonId,6.reason
					List<Object[]> cadreMembersInFamilyList = tdpCadreDAO.checkVoterCardNosCadreNosOrNot(voterIdCardNoList);
					List<Long> tdpCadreIDsList = new ArrayList<Long>(0);
					if(cadreMembersInFamilyList != null && cadreMembersInFamilyList.size() > 0)
					{
						for(Object[] params: cadreMembersInFamilyList)
						{
							if(params[0] != null)
							{
								TdpCadreFamilyDetailsVO VO =getMatchedTdpCadreFamilyDetailsVO(resultList, params[0].toString());
							    if(VO != null){
							    	VO.setTdpCadreId(params[1] != null?Long.parseLong(params[1].toString()):0l);
							    	VO.setMembershipNo(params[2] != null?params[2].toString():"");
							    
							    	if(params[4] !=null){
							    		VO.setDeletedStatus(params[4].toString());
										
										if(VO.getDeletedStatus().equalsIgnoreCase("MD")){
											VO.setDeletedReason(params[6] !=null ? params[6].toString():"");
										}
										else{
											VO.setDeletedReason("");
										}
									}
							    	
							    	if(params[3]!=null){
										String image=params[3].toString();
										String imagePath="http://mytdp.com/images/"+IConstants.CADRE_IMAGES+"/"+image+"";
										
										VO.setImagePath(imagePath);
									}
									else{
										if(voterImagMap.get(VO.getVotercardNo()) != null)
										VO.setImagePath(IConstants.VOTER_IMG_FOLDER_PATH + "/" + voterImagMap.get(VO.getVotercardNo()));
										else
										VO.setImagePath("");
									}
							    	tdpCadreIDsList.add(params[1] != null?Long.parseLong(params[1].toString()):0l);
							    }
							}
							
						}
					}
					
					if(tdpCadreIDsList != null && tdpCadreIDsList.size()>0)
					{
						List<Object[]> cadrePublicRepresentativList = tdpCadreCandidateDAO.getPublicRepresentaativesDetailsForCadreIdsList(tdpCadreIDsList);
						if(cadrePublicRepresentativList != null && cadrePublicRepresentativList.size()>0)
						{
							for (Object[] publicRepresentativ : cadrePublicRepresentativList) {
								if(publicRepresentativ[1] != null)
								{
									TdpCadreFamilyDetailsVO VO =getMatchedTdpCadreFamilyDetailsVO(resultList, publicRepresentativ[1].toString());
								    if(VO != null)
								    	VO.setPublicRepresentativeStr(publicRepresentativ[3] != null ? publicRepresentativ[3].toString():"");
								}
							}
						}
						
						Map<Long,String> cadrePartyPositionMap = new LinkedHashMap<Long, String>(0);
						getPartyPositionForCadre(tdpCadreIDsList,cadrePartyPositionMap);
						
						if(cadrePartyPositionMap != null && cadrePartyPositionMap.size()>0)
						{
							for (Long cadreId : cadrePartyPositionMap.keySet()) {
								String committeeLocation = cadrePartyPositionMap.get(cadreId);
								TdpCadreFamilyDetailsVO vo =getMatchedTdpCadrFamilyDetailsVO(resultList, cadreId);
								if(vo != null){
									if(!committeeLocation.isEmpty())
							    		vo.setPartyPositionStr(committeeLocation);							    	
								}
							}
						}
						
						/*List<Object[]> cadrePartyPositionDetals =  tdpCommitteeMemberDAO.getMembersInfoByTdpCadreIdsList(tdpCadreIDsList);
						if(cadrePartyPositionDetals != null && cadrePartyPositionDetals.size()>0)
						{
							for (Object[] partyPosition : cadrePartyPositionDetals) {
								if(partyPosition[1] != null)
								{
									TdpCadreFamilyDetailsVO vo =getMatchedTdpCadreFamilyDetailsVO(resultList, partyPosition[1].toString());
								    if(vo != null)
								    {
								    	Long committeeLevelId = partyPosition[2] != null?Long.parseLong(partyPosition[2].toString()):0l;
								    	
								    	if(committeeLevelId!= null && committeeLevelId.longValue()>0L)
								    	{
								    		//Long committeeLevelValue = partyPosition[3] != null?Long.parseLong(partyPosition[3].toString()):0l;
									    	String committeeStr = partyPosition[5] != null?partyPosition[5].toString():"";
									    	String roleStr = partyPosition[7] != null?partyPosition[7].toString():"";
									    	String committeeLevelStr = partyPosition[8] != null?partyPosition[8].toString():"";
									    	String committeeLocation="";
									    	if(!committeeLocation.isEmpty())
									    		vo.setPartyPositionStr(committeeStr+" - "+roleStr+" ( "+committeeLocation+" )");
									    	else
									    		vo.setPartyPositionStr(committeeStr+" -"+committeeLevelStr+" "+roleStr);
								    	}
								    }
								}
							}
						}
						*/
						if(resultList != null && resultList.size()>0)
						{
							TdpCadreFamilyDetailsVO vo = resultList.get(0);
							vo.setFamilySurveyCount(Long.valueOf(String.valueOf(list.size())));
							vo.setPartyPositionsCount(Long.valueOf(String.valueOf(cadrePublicRepresentativList.size())));
							vo.setPartyPositionsCount(Long.valueOf(String.valueOf(cadrePartyPositionMap.size())));
						}
					}
				}
			}
			
		}catch (Exception e) {
			LOG.error(" Exception Occured in getCadreFamilyDetails() method, Exception - ",e);
		}
		return resultList;
	}
	
	public TdpCadreFamilyDetailsVO getMatchedTdpCadrFamilyDetailsVO(List<TdpCadreFamilyDetailsVO> resultList,Long tdpCadreId)
	{
		try{
			for(TdpCadreFamilyDetailsVO detailsVO:resultList)
				if(detailsVO.getTdpCadreId() != null && detailsVO.getTdpCadreId().longValue()>0L && detailsVO.getTdpCadreId().longValue() == tdpCadreId.longValue() )
					return detailsVO;
			return null;
			
		}catch (Exception e) {
			LOG.error(" Exception Occured in getMatchedTdpCadreFamilyDetailsVO() method, Exception - ",e);
			return null;
		}
	}
	
	public TdpCadreFamilyDetailsVO getMatchedTdpCadreFamilyDetailsVO(List<TdpCadreFamilyDetailsVO> resultList,String voterIdCardNO)
	{
		try{
			for(TdpCadreFamilyDetailsVO detailsVO:resultList)
				if(detailsVO.getVotercardNo() != null && detailsVO.getVotercardNo().equalsIgnoreCase(voterIdCardNO))
					return detailsVO;
			return null;
			
		}catch (Exception e) {
			LOG.error(" Exception Occured in getMatchedTdpCadreFamilyDetailsVO() method, Exception - ",e);
			return null;
		}
	}
	
	
	public ComplaintStatusCountVO getMatchedVO(String status,List<ComplaintStatusCountVO> statusList)
	{
		try{
			for(ComplaintStatusCountVO statusCountVO: statusList)
			{
				if(statusCountVO.getStatus().equalsIgnoreCase(status))
					return statusCountVO;
			}
			
			return null;
		}catch (Exception e) {
			LOG.error("Exception Occured in getMatchedVO() method, Exception -",e);
			return null;
		}
	}
	
	public void setStatusList1(List<ComplaintStatusCountVO> statusList)
	{
	   ComplaintStatusCountVO vo = null;
	
	    vo = new ComplaintStatusCountVO();
		vo.setStatus("Completed");
		vo.setColor("#00B17D");
		vo.setStatusOrder(1l);
		statusList.add(vo);
		
		vo = new ComplaintStatusCountVO();
		vo.setStatus("In Progress");
		vo.setColor("#66CDCC");
		vo.setStatusOrder(2l);
		statusList.add(vo);
		
		vo = new ComplaintStatusCountVO();
		vo.setStatus("Not Verified");
		vo.setColor("#D64D54");
		vo.setStatusOrder(3l);
		statusList.add(vo);
		
		/*vo = new ComplaintStatusCountVO();
		vo.setStatus("Verified");
		vo.setColor("#481557");
		statusList.add(vo);*/
		
		vo = new ComplaintStatusCountVO();
		vo.setStatus("Not Eligible");
		vo.setColor("#663300");
		vo.setStatusOrder(4l);
		statusList.add(vo);
		
		
		
		vo = new ComplaintStatusCountVO();
		vo.setStatus("Not Possible");
		vo.setColor("#FF9933");
		vo.setStatusOrder(5l);
		statusList.add(vo);
		
		/*vo = new ComplaintStatusCountVO();
		vo.setStatus("Approved");
		vo.setColor("#69A78F");
		statusList.add(vo);*/
		
		
	}
	 
	 public void setTypeOfIssueVO(List<ComplaintStatusCountVO> list)
	 {
		 try{
			 
			    ComplaintStatusCountVO vo = null;
			    
			    vo = new ComplaintStatusCountVO();
				vo.setStatus("Govt");
				list.add(vo);
				
				vo = new ComplaintStatusCountVO();
				vo.setStatus("Party");
				list.add(vo);
				
				vo = new ComplaintStatusCountVO();
				vo.setStatus("Welfare");
				list.add(vo);
			 
		 }catch (Exception e) {
			 LOG.error("Exception Occured in setTypeOfIssueVO() method, Exception - ",e);
		}
		 
	 }
	 
	 
	 public List<CommitteeBasicVO> getLocationwiseCommitteesCount(String locationType,Long tdpCadreId,String electionType,Long locationId)
	 {
		 List<CommitteeBasicVO> committeeBasicVOList = null;
		
		try{
			
			if(electionType !=null && !electionType.isEmpty()){
				
				Long parliamentConId=0l;
				Long districtId=0l;
				
					if(electionType.trim().equalsIgnoreCase("Assembly")){
						List<Long> assembly = new ArrayList<Long>();
						assembly.add(locationId);
						
						
						List<Object[]> parlamentWithDistrict = delimitationConstituencyAssemblyDetailsDAO.findLatestParliamentsAndDistrictForAssembly(assembly);
						
						List<BasicVO> list=new ArrayList<BasicVO>();
						if(parlamentWithDistrict !=null){
							for(Object[] parliamentAndDistrict:parlamentWithDistrict){
								BasicVO vo=new BasicVO();
								vo.setId(parliamentAndDistrict[1] !=null ? (Long)parliamentAndDistrict[1]:0l);//parliamentId
								vo.setName(parliamentAndDistrict[2] !=null ? parliamentAndDistrict[2].toString():"");//parliamentName
								vo.setDistrictId(parliamentAndDistrict[3] !=null ? (Long)parliamentAndDistrict[3]:0l);
								vo.setDistrict(parliamentAndDistrict[4] !=null ? parliamentAndDistrict[4].toString() :"");
								
								list.add(vo);
							}
						}
						
						if(list !=null && list.size()>0){
							parliamentConId=list.get(0).getId();
							districtId=list.get(0).getDistrictId();
						}
						
					}else if(electionType.trim().equalsIgnoreCase("Parliament")){
						parliamentConId = locationId;
						
						List<Long> districts = delimitationConstituencyAssemblyDetailsDAO.findDistrictsBYParliament(parliamentConId);
						
						districtId = districts.get(0);
					}
				
					//comman code
					
					if(electionType.trim().equalsIgnoreCase("Assembly")){
						if(locationType.trim().equalsIgnoreCase("assemblyConstituency")){
							return  getConstituencyCommittesCount(locationId,"assemblyConstituency");
						}
						else if(locationType.trim().equalsIgnoreCase("parliamentConstituency")){
							return getConstituencyCommittesCount(parliamentConId,"parliamentConstituency");
						}
						else if(locationType.trim().equalsIgnoreCase("district")){
							return getDistrictwiseCommitteCount(districtId,null);
						}
					}
					else{
						if(locationType.trim().equalsIgnoreCase("parliamentConstituency")){
							return getConstituencyCommittesCount(parliamentConId,"parliamentConstituency");
						}
						else if(locationType.trim().equalsIgnoreCase("district")){
							return getDistrictwiseCommitteCount(districtId,null);
						}
					}
				
				
			}else{
					
					UserAddress userAddress = tdpCadreDAO.get(tdpCadreId).getUserAddress();
					String areaType = userAddress.getConstituency().getAreaType();
					if(userAddress != null)
					{
						if(locationType.trim().equalsIgnoreCase("district"))
							return getDistrictwiseCommitteCount(userAddress.getDistrict().getDistrictId(),areaType);
							
						else if(locationType.trim().equalsIgnoreCase("mandal"))
						{
							return getMandalwiseCommitteCount(userAddress);
							
						}
						else if(locationType.trim().equalsIgnoreCase("panchayat") && (areaType != null && (areaType.trim().equalsIgnoreCase("RURAL") || areaType.trim().equalsIgnoreCase("RURAL-URBAN"))))
						{
							if(userAddress.getPanchayat() != null && userAddress.getPanchayat().getPanchayatId() != null)
							{
								committeeBasicVOList = new ArrayList<CommitteeBasicVO>();
								List<Long> levelIdsList = new ArrayList<Long>();
								levelIdsList.add(6l);
								//levelIdsList.add(8l);
								CommitteeBasicVO villageVO = new CommitteeBasicVO();
								getPanchayatlwiseCommitteCount(userAddress.getPanchayat().getPanchayatId(),null,villageVO,levelIdsList,"Village");
								committeeBasicVOList.add(villageVO);
								return committeeBasicVOList;
							}
							else if(userAddress.getWard() !=null && userAddress.getWard().getConstituencyId() !=null){
								committeeBasicVOList = new ArrayList<CommitteeBasicVO>();
								List<Long> levelIdsList = new ArrayList<Long>();
								levelIdsList.add(8l);
								CommitteeBasicVO wardVO = new CommitteeBasicVO();
								getWardWiseCommitteeCount(userAddress.getWard().getConstituencyId(),null,wardVO,levelIdsList,"Ward");
								committeeBasicVOList.add(wardVO);
								return committeeBasicVOList;
								
							}
							
						}
						else if(locationType.trim().equalsIgnoreCase("assemblyConstituency"))
							return  getConstituencyCommittesCount(userAddress.getConstituency().getConstituencyId(),"assemblyConstituency");
						
						else if(locationType.trim().equalsIgnoreCase("parliamentConstituency"))
							return getConstituencyCommittesCount(userAddress.getConstituency().getConstituencyId(),"parliamentConstituency");
						
						
					}
			}
			
				
			return committeeBasicVOList;
		}catch (Exception e) {
			LOG.error("Exception Occured in getLocationwiseCommitteesCount() method, Exception - ",e);
			return null;
		}
		
	 }
	 
	 
	 public List<CommitteeBasicVO> getDistrictwiseCommitteCount(Long districtId,String areaType)
	 {
		 List<CommitteeBasicVO> resultList = new ArrayList<CommitteeBasicVO>();
		 try{
			 
			 List<Long> levelIds = new ArrayList<Long>();
			 levelIds.add(11l);
			 
			 List<Long> districtIds = new ArrayList<Long>();
			 districtIds.add(districtId);
			 
			 setVillageCommitteesCount(districtIds, resultList);
			 setMandalCommitteesCount(districtIds,resultList);
			 setDistrictCommitteesCount(districtIds,resultList);
			 
			 if(resultList !=null && resultList.size()>0){
				 resultList.get(0).setAreaType(areaType);
			 }
			 
		 }catch (Exception e) {
				LOG.error("Exception Occured in getDistrictwiseCommitteCount() method, Exception - ",e);
			}
		 return resultList;
		 
	 }
	 
	 public void setDistrictCommitteesCount(List<Long> districtIds,List<CommitteeBasicVO> resultList)
	 {
		try{
			List<Long> levelIds = new ArrayList<Long>();
			 levelIds.add(11l);
			 
			 CommitteeBasicVO districtVO = new CommitteeBasicVO();
			 districtVO.setLocationType("District");
			
			 List<Object[]> list = tdpCommitteeMemberDAO.membersCountDistrictWise(levelIds, null, null, districtIds);//Total Committee Members Count
			  setTotalCommitteeMembersCount(list, districtVO);
			 
		     List<Object[]> list2 = tdpCommitteeDAO.getCommitteesCountByDistrictIdAndLevel(districtIds, levelIds);//total committes Count
		     setTotalCommitteesCount(list2, districtVO);
		     
		     //0committeeId,1committeeTypeid
		     List<Object[]> stResLst = tdpCommitteeDAO.getcommitteesCountByDistrict(levelIds, null, null, "started", districtIds); //changed
		     setStartedCommitteesCount(stResLst,districtVO);
		     
		   //0committeeId,1committeeTypeid
			 List<Object[]> endResLst = tdpCommitteeDAO.getcommitteesCountByDistrict(levelIds, null, null, "completed", districtIds);//changed
			 setCompletedCommitteesCount(endResLst, districtVO);
			 
			 resultList.add(districtVO);
			
		}catch (Exception e) {
			LOG.error("Exception Occured in setDistrictwiseCountToVO() method, Exception - ",e);
		}
	 }
	 
	 public void setMandalCommitteesCount(List<Long> districtIds,List<CommitteeBasicVO> resultList)
	 {
		try{
			CommitteeBasicVO mandalVO = new CommitteeBasicVO();
			mandalVO.setLocationType("Mandal");
			
			List<Long> mandalMunciDivisionIds = new ArrayList<Long>();
			mandalMunciDivisionIds.add(5l);
			mandalMunciDivisionIds.add(7l);
			mandalMunciDivisionIds.add(9l);
			
			List<Object[]> memResLst = tdpCommitteeMemberDAO.membersCountDistrictWise(mandalMunciDivisionIds, null, null, districtIds);
			setTotalCommitteeMembersCount(memResLst, mandalVO);
			
			List<Object[]> ttlList = tdpCommitteeDAO.getCommitteesCountByDistrictIdAndLevel(districtIds, mandalMunciDivisionIds);
			setTotalCommitteesCount(ttlList, mandalVO);
			
			//0committeeId,1committeeTypeid
			List<Object[]> stResLst = tdpCommitteeDAO.getcommitteesCountByDistrict(mandalMunciDivisionIds, null, null, "started", districtIds);//changed
			setStartedCommitteesCount(stResLst, mandalVO);
			
			//0committeeId,1committeeTypeid
			List<Object[]> endResLst = tdpCommitteeDAO.getcommitteesCountByDistrict(mandalMunciDivisionIds, null, null, "completed", districtIds);//changed
			setCompletedCommitteesCount(endResLst, mandalVO);
			
			resultList.add(mandalVO);
			
		}catch (Exception e) {
			LOG.error("Exception Occured in setMandalCommitteesCountToVO() method, Exception - ",e);
		}
	 }
	 
	 
	 public void setVillageCommitteesCount(List<Long> districtIds,List<CommitteeBasicVO> resultList)
	 {
		try{
			CommitteeBasicVO villageVO = new CommitteeBasicVO();
			villageVO.setLocationType("Village");
			
			List<Long> villageWardIds = new ArrayList<Long>();
			villageWardIds.add(6l);
			villageWardIds.add(8l);
			
			List<Object[]> memResLstVill = tdpCommitteeMemberDAO.membersCountDistrictWise(villageWardIds, null, null, districtIds);
			setTotalCommitteeMembersCount(memResLstVill, villageVO);
			
			List<Object[]> ttlListVill = tdpCommitteeDAO.getCommitteesCountByDistrictIdAndLevel(districtIds, villageWardIds);
			setTotalCommitteesCount(ttlListVill, villageVO);
			
			//0committeeId,1committeeTypeid
			List<Object[]> stResLstVill = tdpCommitteeDAO.getcommitteesCountByDistrict(villageWardIds, null, null, "started", districtIds);//changged
			setStartedCommitteesCount(stResLstVill, villageVO);
			
			//0committeeId,1committeeTypeid
			List<Object[]> endResLstVill = tdpCommitteeDAO.getcommitteesCountByDistrict(villageWardIds, null, null, "completed", districtIds);//changed
			setCompletedCommitteesCount(endResLstVill, villageVO);
			
			resultList.add(villageVO);
		}catch (Exception e) {
			LOG.error("Exception Occured in setVillageCommitteesCount() method, Exception - ",e);
		}
	 }
	 
	 
	 
	 public void setTotalCommitteeMembersCount(List<Object[]> list,CommitteeBasicVO basicVO)
	 {
		 try{
			 if(list != null && list.size() > 0)
			 {
				 for(Object[] params:list)
				 {
					 if(params[2] != null && new Long(params[2].toString()).equals(1l))
						basicVO.setMainCommTotalMembers(basicVO.getMainCommTotalMembers()+(Long)params[0]); //total Main Committee Members
					 else
					   basicVO.setAffiCommTotalMembers(basicVO.getAffiCommTotalMembers()+(Long)params[0]);//total Affiliated Committees Members
				 } 
			 }
			 
			 
		 }catch (Exception e) {
			 LOG.error("Exception Occured in setTotalCommitteeMembersCount() method, Exception - ",e);
		}
	 }
	 
	 
	 public void setTotalCommitteesCount(List<Object[]> list,CommitteeBasicVO basicVO)
	 {
		 try{
			 if(list != null && list.size() > 0)
			 {
				 for(Object[] params:list)
				 {
					 if(params[2] != null && new Long(params[2].toString()).equals(1l))
							basicVO.setMainCommTotalCount(basicVO.getMainCommTotalCount()+(Long)params[0]); //total Main Committees Count
					else
					  basicVO.setAffiCommTotalCount(basicVO.getAffiCommTotalCount()+(Long)params[0]);//total Affiliated Committees Count
				 }
			 }
			 
			 
		 }catch (Exception e) {
			 LOG.error("Exception Occured in setTotalCommitteesCount() method, Exception - ",e);
		}
	 }
	 
	 public void  setStartedCommitteesCount(List<Object[]> list,CommitteeBasicVO basicVO)
	 {
		 try{
			 if(list != null && list.size() > 0)
			 {
				 for(Object[] params:list)
				 {
					 if(params[1] != null && new Long(params[1].toString()).equals(1l))
							basicVO.setMainCommStartedCount(basicVO.getMainCommStartedCount()+(Long)params[0]); //total Main Committees Count
					else
					  basicVO.setAffiCommStartedCount(basicVO.getAffiCommStartedCount()+(Long)params[0]);//total Affiliated Committees Count
				 }
			 }
		 }catch (Exception e) {
			 LOG.error("Exception Occured in setStartedCommitteesCount() method, Exception - ",e);
		} 
	 }
	 
	 public void  setStartedCommitteesCount1(List<Object[]> list,CommitteeBasicVO basicVO)
	 {
		 try{
			 if(list != null && list.size() > 0)
			 {
				 for(Object[] params:list)
				 {
					 if(params[2] != null && new Long(params[2].toString()).equals(1l))
							basicVO.setMainCommStartedCount(basicVO.getMainCommStartedCount()+(Long)params[0]); //total Main Committees Count
					else
					  basicVO.setAffiCommStartedCount(basicVO.getAffiCommStartedCount()+(Long)params[0]);//total Affiliated Committees Count
				 }
			 }
		 }catch (Exception e) {
			 LOG.error("Exception Occured in setStartedCommitteesCount() method, Exception - ",e);
		} 
	 }
	 
	 public void  setCompletedCommitteesCount(List<Object[]> list,CommitteeBasicVO basicVO)
	 {
		 try{
			 if(list != null && list.size() > 0)
			 {
				 for(Object[] params:list)
				 {
					 if(params[1] != null && new Long(params[1].toString()).equals(1l))
							basicVO.setMainCommCompletedCount(basicVO.getMainCommCompletedCount()+(Long)params[0]); //total Main Committees Count
					else
					  basicVO.setAffiCommCompletedCount(basicVO.getAffiCommCompletedCount()+(Long)params[0]);//total Affiliated Committees Count
				 }
			 }
		 }catch (Exception e) {
			 LOG.error("Exception Occured in setStartedCommitteesCount() method, Exception - ",e);
		} 
	 }
	 
	 public void  setCompletedCommitteesCount1(List<Object[]> list,CommitteeBasicVO basicVO)
	 {
		 try{
			 if(list != null && list.size() > 0)
			 {
				 for(Object[] params:list)
				 {
					 if(params[2] != null && new Long(params[2].toString()).equals(1l))
							basicVO.setMainCommCompletedCount(basicVO.getMainCommCompletedCount()+(Long)params[0]); //total Main Committees Count
					else
					  basicVO.setAffiCommCompletedCount(basicVO.getAffiCommCompletedCount()+(Long)params[0]);//total Affiliated Committees Count
				 }
			 }
		 }catch (Exception e) {
			 LOG.error("Exception Occured in setStartedCommitteesCount() method, Exception - ",e);
		} 
	 }
	 
	 
	 public List<CommitteeBasicVO> getConstituencyCommittesCount(Long constituencyId ,String constituencyType)
	 {
		 List<CommitteeBasicVO> resultList = new ArrayList<CommitteeBasicVO>(); 
		 try{
			 
			 List<Long> constituenyIdsList = new ArrayList<Long>();
			 Long scopeId=0l;
			 if(constituencyType !=null && !constituencyType.equalsIgnoreCase("parliamentConstituency")){
				scopeId = constituencyDAO.getElectionScopeByConstituency(constituencyId);
			 }
			 
			 if(scopeId==1l){
				 
				 if(constituencyId != null && constituencyId > 0)
					  constituenyIdsList = delimitationConstituencyAssemblyDetailsDAO.getAssemblyConstituencyIdsByPCId(constituencyId);
				 
			 }else{
			
				 if(constituencyType.equalsIgnoreCase("assemblyConstituency"))
				   constituenyIdsList.add(constituencyId);
				 else
				 {
					 Long parliamentConId = delimitationConstituencyAssemblyDetailsDAO.getParliamentConstituencyId(constituencyId);
					   if(parliamentConId != null && parliamentConId > 0)
						  constituenyIdsList = delimitationConstituencyAssemblyDetailsDAO.getAssemblyConstituencyIdsByPCId(parliamentConId);
				 }
			 }
			 if(constituenyIdsList != null && constituenyIdsList.size() > 0)
			 {
				 setVillageCommitteesCountInConstituency(constituenyIdsList, resultList);
				 SetMandalCommitteesCountInConstituency(constituenyIdsList, resultList);
			 }
			 
			 
			
			 
			 
		 }catch (Exception e) {
			 LOG.error("Exception Occured in getConstituencyCommittesCount() method, Exception - ",e);
		}
		 return resultList;
	 }

	 
	 public void setVillageCommitteesCountInConstituency(List<Long> constituenyIdsList,List<CommitteeBasicVO> resultList)
	 {
		 try{
			 
			 CommitteeBasicVO villageCommitteeVO = new CommitteeBasicVO();
			 villageCommitteeVO.setLocationType("Village");
			 
			   List<Long> villageWardIds = new ArrayList<Long>();
				villageWardIds.add(6l);
				villageWardIds.add(8l);
				
				List<Object[]> memResLstVill = tdpCommitteeMemberDAO.membersCountConstituencyWise(villageWardIds, null, null, constituenyIdsList);
				setTotalCommitteeMembersCount(memResLstVill, villageCommitteeVO);
				
				List<Object[]> ttlV = tdpCommitteeDAO.getCommitteesCountByConstituencyIdAndLevel(constituenyIdsList, villageWardIds);
				setTotalCommitteesCount(ttlV, villageCommitteeVO);
				
				//0committeeId,1committeeTypeid
				List<Object[]> stResLstVill = tdpCommitteeDAO.getcommitteesCountByConstituency(villageWardIds, null, null, "started", constituenyIdsList);
				setStartedCommitteesCount(stResLstVill, villageCommitteeVO);
				
				//0committeeId,1committeeTypeid
				List<Object[]> endResLstVill = tdpCommitteeDAO.getcommitteesCountByConstituency(villageWardIds, null, null, "completed", constituenyIdsList);
				setCompletedCommitteesCount(endResLstVill, villageCommitteeVO);
				
				resultList.add(villageCommitteeVO);
			 
		 }catch (Exception e) {
			 LOG.error("Exception Occured in setVillageCommitteesCountInConstituency() method, Exception - ",e);
		}
	 }
	 
	 public void SetMandalCommitteesCountInConstituency(List<Long> constituencyIds,List<CommitteeBasicVO> resultList)
	 {
		 try{
			   
			    CommitteeBasicVO mandalVO = new CommitteeBasicVO();
			    mandalVO.setLocationType("Mandal");
			   
			    Map<Long,List<Long>> mandalIdsMap = new HashMap<Long,List<Long>>();//Map<id,List<1constituencyId>>
				Map<Long,List<Long>> localBodiesMap = new HashMap<Long,List<Long>>();//Map<id,List<1constituencyId>>
				List<Long> divisionLclIds = new ArrayList<Long>();//Map<id,List<1constituencyId>>
				Map<Long,List<Long>> divisionIdsMap = new HashMap<Long,List<Long>>();//Map<id,List<1constituencyId>>
				
				cadreCommitteeService.getLocationsInfo(constituencyIds, divisionLclIds, localBodiesMap, divisionIdsMap, mandalIdsMap);
				if(mandalIdsMap.size() > 0){
					List<Long> levelValues = new ArrayList<Long>(mandalIdsMap.keySet());
					
					List<Object[]> totalMandalMainMembers = tdpCommitteeMemberDAO.totalMainMembersCountLocationsWise(5l,null, null,levelValues);
					setTotalCommitteeMembersCount(totalMandalMainMembers, mandalVO);
					
					List<Object[]> totalMandalCommittees = tdpCommitteeDAO.totalCommitteesCountByLocationIds(5l,levelValues);
					setTotalCommitteesCount(totalMandalCommittees, mandalVO);
					
					//0count,1locationID,2typeId
					 List<Object[]> totalMandalStartedCommittees = tdpCommitteeDAO.committeesCountByLocationIds(5l,levelValues,null,null,"started");
					 setStartedCommitteesCount1(totalMandalStartedCommittees, mandalVO);
					 
					//0count,1locationID,2typeId
					 List<Object[]> totalMandalCompletedCommittees = tdpCommitteeDAO.committeesCountByLocationIds(5l,levelValues,null,null,"completed");
					 setCompletedCommitteesCount1(totalMandalCompletedCommittees,mandalVO);
					
				}
				 if(localBodiesMap.size() > 0){
					 
					 List<Long> levelValues = new ArrayList<Long>(localBodiesMap.keySet());
					//0 count,1levelId
					 List<Object[]> totalLocalBodMainMembers = tdpCommitteeMemberDAO.totalMainMembersCountLocationsWise(7l,null,null,levelValues);
					 setTotalCommitteeMembersCount(totalLocalBodMainMembers, mandalVO);
					 
					 //0count,1locationID
					 List<Object[]> totalLocalBodCommittees = tdpCommitteeDAO.totalCommitteesCountByLocationIds(7l,levelValues);
					 setTotalCommitteesCount(totalLocalBodCommittees, mandalVO);
					 
					//0count,1locationID,2typeId
					 List<Object[]> totalLocalBodStartedCommittees = tdpCommitteeDAO.committeesCountByLocationIds(7l,levelValues,null,null,"started");
					 setStartedCommitteesCount1(totalLocalBodStartedCommittees, mandalVO);
					 
					 //0count,1locationID,2typeId
					 List<Object[]> totalLocalBodCompletedCommittees = tdpCommitteeDAO.committeesCountByLocationIds(7l,levelValues,null,null,"completed");
					 setCompletedCommitteesCount1(totalLocalBodCompletedCommittees,mandalVO);
					 
				 }
				 
				 
				 if(divisionIdsMap.size() > 0){
					 List<Long> levelValues = new ArrayList<Long>(divisionIdsMap.keySet());
					 
					//0 count,1levelId
					 List<Object[]> totalDivisionMainMembers = tdpCommitteeMemberDAO.totalMainMembersCountLocationsWise(9l,null,null,levelValues);
					 setTotalCommitteeMembersCount(totalDivisionMainMembers, mandalVO);
					 
					 //0count,1locationID
					 List<Object[]> totalDivisionCommittees = tdpCommitteeDAO.totalCommitteesCountByLocationIds(9l,levelValues);
					 setTotalCommitteesCount(totalDivisionCommittees, mandalVO);
					 
					//0count,1locationID,2typeId
					 List<Object[]> totalDivisionStartedCommittees = tdpCommitteeDAO.committeesCountByLocationIds(9l,levelValues,null,null,"started");
					 setStartedCommitteesCount1(totalDivisionStartedCommittees, mandalVO);
					 
					 //0count,1locationID,2typeId
					 List<Object[]> totalDivisionCompletedCommittees = tdpCommitteeDAO.committeesCountByLocationIds(9l,levelValues,null,null,"completed");
					 setCompletedCommitteesCount1(totalDivisionCompletedCommittees,mandalVO);
					 
				 }
				 resultList.add(mandalVO);
			 
		 }catch (Exception e) {
			 LOG.error("Exception Occured in SetMandalCommitteesCountInConstituency() method, Exception - ",e);
		}
		 
	 }
	 
	 
	
	 
	 
	 public void getPanchayatlwiseCommitteCount(Long locationId,Long constituencyId,CommitteeBasicVO villageVO,List<Long> levelIdsList,String locationType)
	 {
		 try{
			 
				villageVO.setLocationType(locationType);
				
				List<Object[]> committeeMembersList = new ArrayList<Object[]>();//Total committee members count
				List<Object[]> committeesList = new ArrayList<Object[]>();//Total committees count
				List<Object[]> startedCommitteesList = new ArrayList<Object[]>();//Total started committees count
				List<Object[]> completedCommitteesList = new ArrayList<Object[]>();//Total completed committees count
				
				for(Long levelId: levelIdsList)
				{
					setTdpCommitteeMembersCount(levelId, locationId, null, committeeMembersList,locationType);	
					setTdpCommitteesCount(levelId, locationId, null, committeesList,locationType);
					setTdpStartedOrCompletedCommitteesCount(levelId, locationId, null, startedCommitteesList, "Started",locationType);
					setTdpStartedOrCompletedCommitteesCount(levelId, locationId, null, completedCommitteesList, "completed",locationType);
				}
				
				setTotalCommitteeMembersCount(committeeMembersList, villageVO);
				setTotalCommitteesCount(committeesList, villageVO);
				setStartedCommitteesCount(startedCommitteesList, villageVO);
				setCompletedCommitteesCount(completedCommitteesList, villageVO);
				
				
			 
		 }catch (Exception e) {
			 LOG.error("Exception Occured in getPanchayatlwiseCommitteCount() method, Exception - ",e);
		}
	 }
	 
	 public void getWardWiseCommitteeCount(Long locationId,Long constituencyId,CommitteeBasicVO wardVo,List<Long> levelIdsList,String locationType){
		 
		 try{
			 
			 	wardVo.setLocationType(locationType);
				
				List<Object[]> committeeMembersList = new ArrayList<Object[]>();//Total committee members count
				List<Object[]> committeesList = new ArrayList<Object[]>();//Total committees count//
				List<Object[]> startedCommitteesList = new ArrayList<Object[]>();//Total started committees count
				List<Object[]> completedCommitteesList = new ArrayList<Object[]>();//Total completed committees count
				
				for(Long levelId: levelIdsList)
				{
					setTdpCommitteeMembersCount(levelId, locationId, null, committeeMembersList,locationType);//clear	
					setTdpCommitteesCount(levelId, locationId, null, committeesList,locationType);
					setTdpStartedOrCompletedCommitteesCount(levelId, locationId, null, startedCommitteesList, "Started",locationType);
					setTdpStartedOrCompletedCommitteesCount(levelId, locationId, null, completedCommitteesList, "completed",locationType);
				}
				
				setTotalCommitteeMembersCount(committeeMembersList, wardVo);
				setTotalCommitteesCount(committeesList, wardVo);
				setStartedCommitteesCount(startedCommitteesList, wardVo);
				setCompletedCommitteesCount(completedCommitteesList, wardVo);
				
				
			 
		 }catch (Exception e) {
			 LOG.error("Exception Occured in getPanchayatlwiseCommitteCount() method, Exception - ",e);
		}
		 
		 
	 }
	 

	 
	
	public void setTdpCommitteeMembersCount(Long levelId,Long levelValue,Long constituencyId,List<Object[]> resultList,String locationType)
	{
		try{
			List<Object[]> list = null;
			if(locationType.equalsIgnoreCase("mandalLevelVillage"))
				 list = tdpCommitteeMemberDAO.getTotalCommittesCountByLevelIdAndLevelValueForVillage(levelId, levelValue,constituencyId);
			else if(locationType.equalsIgnoreCase("municipalWiseWards")){
				list=tdpCommitteeMemberDAO.getTotalCommittesCountByLevelIdAndLevelValueForWards(levelId,levelValue,constituencyId);
			}
			else
				list= tdpCommitteeMemberDAO.getTotalCommittesCountByLevelIdAndLevelValue(levelId, levelValue,constituencyId);
			 if(list != null && list.size() > 0)
				 resultList.addAll(list);
			
		}catch (Exception e) {
			 LOG.error("Exception Occured in setTdpCommitteeMembersCount() method, Exception - ",e);
		}
		
	}
	
	public void setTdpCommitteesCount(Long levelId,Long levelValue,Long constituencyId,List<Object[]> resultList,String locationType)
	{
		try{
			List<Object[]> list = null;
			if(locationType.equalsIgnoreCase("mandalLevelVillage"))
				 list = tdpCommitteeDAO.getCommitteesCountByLevelIdAndLevelValueForVillage(levelId, levelValue,constituencyId);
			else if(locationType.equalsIgnoreCase("municipalWiseWards")){
				list = tdpCommitteeDAO.getCommitteesCountByLevelIdAndLevelValueForVillageMunicipal(levelId, levelValue,constituencyId);
			}
			else
		     list = tdpCommitteeDAO.getCommitteesCountByLevelIdAndLevelValue(levelId, levelValue, null);
			 if(list != null && list.size() > 0)
				 resultList.addAll(list);
			
		}catch (Exception e) {
			 LOG.error("Exception Occured in setTdpCommitteesCount() method, Exception - ",e);
		}
		
	}
	
	public void setTdpStartedOrCompletedCommitteesCount(Long levelId,Long levelValue,Long constituencyId,List<Object[]> resultList,String status,String locationType)
	{
		try{
			List<Object[]> list = null;
			if(locationType.equalsIgnoreCase("mandalLevelVillage"))
				list = tdpCommitteeDAO.getStartedOrComplcommitteesCountByLevelIdAndLevelValueForVillage(levelId, levelValue, constituencyId, status);
			else if(locationType.equalsIgnoreCase("municipalWiseWards")){
				list = tdpCommitteeDAO.getStartedOrComplcommitteesCountByLevelIdAndLevelValueForVillageMunicipal(levelId, levelValue, constituencyId, status);
			}
			else
			list = tdpCommitteeDAO.getStartedOrComplcommitteesCountByLevelIdAndLevelValue(levelId, levelValue, constituencyId, status);
			 if(list != null && list.size() > 0)
				 resultList.addAll(list);
			
		}catch (Exception e) {
			 LOG.error("Exception Occured in setTdpStartedOrCompletedCommitteesCount() method, Exception - ",e);
		}
		
	}
	
	
	
	//Mandal wise Committees Count
	
	public List<CommitteeBasicVO> getMandalwiseCommitteCount(UserAddress userAddress)
	{
		List<CommitteeBasicVO> resultList = new ArrayList<CommitteeBasicVO>();
		try{
			
			Long constituencyId = userAddress.getConstituency().getConstituencyId();
			List<Long> localEleIdsList = new ArrayList<Long>();
			localEleIdsList.add(7l);
			
			List<Long> mandalIds = new ArrayList<Long>();
			mandalIds.add(5l);
			
			
			CommitteeBasicVO mandalMunciVO = new CommitteeBasicVO();
			
			if(userAddress.getLocalElectionBody() != null && userAddress.getLocalElectionBody().getLocalElectionBodyId() > 0)
			{
				getPanchayatlwiseCommitteCount(userAddress.getLocalElectionBody().getLocalElectionBodyId(), constituencyId, mandalMunciVO, localEleIdsList, "localElection");
				
				//ward committees count in municipality
				List<Long> levelIdsList = new ArrayList<Long>();
				levelIdsList.add(8l);
				CommitteeBasicVO villBasicVO = new CommitteeBasicVO();
			    getPanchayatlwiseCommitteCount(userAddress.getLocalElectionBody().getLocalElectionBodyId(), constituencyId, villBasicVO,levelIdsList,"municipalWiseWards");
			    
			    resultList.add(villBasicVO);
				
			}
			
			else if(userAddress.getTehsil() != null && userAddress.getTehsil().getTehsilId() > 0)
			{
				getPanchayatlwiseCommitteCount(userAddress.getTehsil().getTehsilId(), constituencyId, mandalMunciVO, mandalIds, "Mandal");
				
				
				//panchayat committees count in mandal
				List<Long> levelIdsList = new ArrayList<Long>();
				levelIdsList.add(6l);
				//levelIdsList.add(8l);
				CommitteeBasicVO villBasicVO = new CommitteeBasicVO();
			    getPanchayatlwiseCommitteCount(userAddress.getTehsil().getTehsilId(), constituencyId, villBasicVO,levelIdsList,"mandalLevelVillage");
			    
			    resultList.add(villBasicVO);
			   
			}
			
			
			resultList.add(mandalMunciVO);
			
		}catch (Exception e) {
			LOG.error("Exception Occured in getMandalwiseCommitteCount() method, Exception - ",e);
		}
		
		return resultList;
	}
/*	public List<CommitteeBasicVO> getLocalElectionBodyWiseCount(UserAddress userAddress){
		
		
		return null;
	}*/
	
	public void setInsuranceType(Map<String,VerifierVO> mapVo){
		
		List<Object[]> insuranceType=insuranceTypeDAO.getAllInsuranceType();
		
		
		if(insuranceType !=null && insuranceType.size()>0){
			
			for(Object[] object:insuranceType){
				
				VerifierVO verifierVo=new VerifierVO();
				verifierVo.setCount(0l);
				verifierVo.setId(Long.parseLong(object[0].toString()));//insurance typeId
				verifierVo.setName(object[1].toString());
				
				mapVo.put(verifierVo.getName(), verifierVo);
				
				
			}
			
		}
		
	}
	
	
	public VerifierVO getDeathsAndHospitalizationDetails(Long panchayatId,Long mandalId,Long constituencyId,Long parliamentId,Long districtId){
		
		VerifierVO finalVo=new VerifierVO();

		List<VerifierVO> mainList=new ArrayList<VerifierVO>();
		
		try{
			if(panchayatId !=0l){
				
				VerifierVO panchayatVo=new VerifierVO();
				Map<String,VerifierVO> mapVerifierVo=new LinkedHashMap<String, VerifierVO>();
				setInsuranceType(mapVerifierVo);
				panchayatVo.setVerifierVOMap(mapVerifierVo);
				
				List<Object[]> panchaytDeathDetails=tdpCadreInsuranceInfoDAO.getDeathsAndHospitalizationDetails(panchayatId,"panchayat");
				if(panchaytDeathDetails !=null){
					for (Object[] objects : panchaytDeathDetails) {
						
						if(objects[2]!=null){
							VerifierVO reqTypeVO=panchayatVo.getVerifierVOMap().get(objects[2].toString());
							reqTypeVO.setCount(objects[0] !=null ? (Long)objects[0]:0l);
						}
					}
					panchayatVo.setId(panchayatId);
					panchayatVo.setName("Panchayat");
					mainList.add(panchayatVo);
				}
				if(panchayatVo.getVerifierVOMap()!=null && panchayatVo.getVerifierVOMap().size()>0){
					panchayatVo.getVerifierVOList().addAll(panchayatVo.getVerifierVOMap().values());
				}
				panchayatVo.getVerifierVOMap().clear();
			}
			if(mandalId !=0l){
				
				VerifierVO tehsilVo=new VerifierVO();
				Map<String,VerifierVO> mapVerifierVo=new LinkedHashMap<String, VerifierVO>();
				setInsuranceType(mapVerifierVo);
				tehsilVo.setVerifierVOMap(mapVerifierVo);
				
				//List<VerifierVO> tehsilList=new ArrayList<VerifierVO>();
				List<Object[]> mandalDeathDetails=tdpCadreInsuranceInfoDAO.getDeathsAndHospitalizationDetails(mandalId,"mandal");
				if(mandalDeathDetails !=null){
					for (Object[] objects : mandalDeathDetails) {
							
						if(objects[2]!=null){
							VerifierVO reqTypeVO=tehsilVo.getVerifierVOMap().get(objects[2].toString());
							reqTypeVO.setCount(objects[0] !=null ? (Long)objects[0]:0l);
						}
					}
					//tehsilVo.setVerifierVOList(tehsilList);
					tehsilVo.setId(mandalId);
					tehsilVo.setName("Mandal");
					mainList.add(tehsilVo);
				}
				if(tehsilVo.getVerifierVOMap()!=null && tehsilVo.getVerifierVOMap().size()>0){
					tehsilVo.getVerifierVOList().addAll(tehsilVo.getVerifierVOMap().values());
				}
				tehsilVo.getVerifierVOMap().clear();
			}
			if(constituencyId !=0l){
				
				VerifierVO constituencyVo=new VerifierVO();
				Map<String,VerifierVO> mapVerifierVo=new LinkedHashMap<String, VerifierVO>();
				setInsuranceType(mapVerifierVo);
				constituencyVo.setVerifierVOMap(mapVerifierVo);
				
				//List<VerifierVO> constituencyList=new ArrayList<VerifierVO>();
				List<Object[]> constituencyDeathDetails=tdpCadreInsuranceInfoDAO.getDeathsAndHospitalizationDetails(constituencyId,"constituency");
				if(constituencyDeathDetails !=null){
					for (Object[] objects : constituencyDeathDetails) {
						
						if(objects[2]!=null){
							VerifierVO reqTypeVO=constituencyVo.getVerifierVOMap().get(objects[2].toString());
							reqTypeVO.setCount(objects[0] !=null ? (Long)objects[0]:0l);
						}
					}
					//constituencyVo.setVerifierVOList(constituencyList);
					constituencyVo.setId(constituencyId);
					constituencyVo.setName("Constituency");
					mainList.add(constituencyVo);
				}
				if(constituencyVo.getVerifierVOMap()!=null && constituencyVo.getVerifierVOMap().size()>0){
					constituencyVo.getVerifierVOList().addAll(constituencyVo.getVerifierVOMap().values());
				}
				constituencyVo.getVerifierVOMap().clear();
			}
			if(parliamentId !=0){
				VerifierVO parliamentVo=new VerifierVO();
				Map<String,VerifierVO> mapVerifierVo=new LinkedHashMap<String, VerifierVO>();
				setInsuranceType(mapVerifierVo);
				parliamentVo.setVerifierVOMap(mapVerifierVo);
				
				//List<VerifierVO> parliamentDeathList=new ArrayList<VerifierVO>();
				
				List<Long> asemConstIds=null;
				
				asemConstIds=delimitationConstituencyAssemblyDetailsDAO.getAssemblyConstituencyIdsByPCId(parliamentId);
				
				if(asemConstIds !=null && asemConstIds.size()>0){
					List<Object[]> parliamentDeathDetails=tdpCadreInsuranceInfoDAO.getDeathsAndHospitalizationDetailsForParliament(asemConstIds,"parliament");
					
					if(parliamentDeathDetails !=null){
						for (Object[] objects : parliamentDeathDetails) {
							if(objects[2]!=null){
								VerifierVO reqTypeVO=parliamentVo.getVerifierVOMap().get(objects[2].toString());
								reqTypeVO.setCount(objects[0] !=null ? (Long)objects[0]:0l);
							}
						}
						//parliamentVo.setVerifierVOList(parliamentDeathList);
						parliamentVo.setId(parliamentId);
						parliamentVo.setName("Parliament");
						mainList.add(parliamentVo);
					}
					if(parliamentVo.getVerifierVOMap()!=null && parliamentVo.getVerifierVOMap().size()>0){
						parliamentVo.getVerifierVOList().addAll(parliamentVo.getVerifierVOMap().values());
					}
					parliamentVo.getVerifierVOMap().clear();
				} 
				
			}
			
			
			if(districtId !=0l){
				
				VerifierVO districtVo=new VerifierVO();
				Map<String,VerifierVO> mapVerifierVo=new LinkedHashMap<String, VerifierVO>();
				setInsuranceType(mapVerifierVo);
				districtVo.setVerifierVOMap(mapVerifierVo);
				
				//List<VerifierVO> districtList=new ArrayList<VerifierVO>();
				List<Object[]> districtDeathDetails=tdpCadreInsuranceInfoDAO.getDeathsAndHospitalizationDetails(districtId,"district");
				if(districtDeathDetails !=null){
					for (Object[] objects : districtDeathDetails) {
						if(objects[2]!=null){
							VerifierVO reqTypeVO=districtVo.getVerifierVOMap().get(objects[2].toString());
							reqTypeVO.setCount(objects[0] !=null ? (Long)objects[0]:0l);
						}
						
					}
					districtVo.setId(districtId);
					districtVo.setName("District");
					mainList.add(districtVo);
				}
				if(districtVo.getVerifierVOMap()!=null && districtVo.getVerifierVOMap().size()>0){
					districtVo.getVerifierVOList().addAll(districtVo.getVerifierVOMap().values());
				}
				districtVo.getVerifierVOMap().clear();
			}
			if(mainList !=null && mainList.size()>0){
				finalVo.setVerifierVOList(mainList);
			}
			
		}catch (Exception e) {
			LOG.error("Exception Occured in getDeathsAndHospitalizationDetails() method, Exception - ",e);
		}
		
		return finalVo;
	}
	
	public Long getTdpCadreIdBymembershipId(String membershipId)
	{
		try{
			List<Long> list = tdpCadreDAO.getTdpCadreIdByMembershipId(membershipId);
			if(list != null && list.size() > 0)
				return list.get(0);
		}catch(Exception e)
		{
			LOG.error("Exception occured in getTdpCadreIdBymembershipId() Method - ",e);
		}
		return null;
	}
	public CadreCommitteeMemberVO getCadresDetailsOfDeathsAndHospitalization(Long locationId,String locationType,Long insuranceTypeId){
		
		CadreCommitteeMemberVO finalcadreVo=new CadreCommitteeMemberVO();
		try{
			List<Long> cadreIds=null;
			if(!locationType.equalsIgnoreCase("parliament")){
				cadreIds=tdpCadreInsuranceInfoDAO.getCadresIdsByInsuranceType(locationId,locationType,insuranceTypeId);
			}
			else if(locationType.equalsIgnoreCase("parliament")){
				
				List<Long> asemConstIds=null;
				
				asemConstIds=delimitationConstituencyAssemblyDetailsDAO.getAssemblyConstituencyIdsByPCId(locationId);
				if(asemConstIds !=null && asemConstIds.size()>0)
				cadreIds=tdpCadreInsuranceInfoDAO.getCadresIdsByParliament(asemConstIds,locationType,insuranceTypeId);
				
			}
			if(cadreIds !=null && cadreIds.size()>0){
				finalcadreVo=getCadresDetailsOfDeathsAndHospitalization(cadreIds);
			}
			
			
		}catch (Exception e) {
			LOG.error("Exception occured in getTdpCadreIdBymembershipId() Method - ",e);
		}
		return finalcadreVo;
	}
	public CadreCommitteeMemberVO getCadresDetailsOfDeathsAndHospitalization(List<Long> cadreIds){
		
		CadreCommitteeMemberVO finalcadreVo=new CadreCommitteeMemberVO();
		
		List<CadreCommitteeMemberVO> listOfCadreVO=new ArrayList<CadreCommitteeMemberVO>();
		try{
			
					if(cadreIds !=null && cadreIds.size()>0){
						List<Object[]> details=tdpCadreDAO.cadresInformationOfCandidate(cadreIds,2014l);
						
						DateFormat dateFormat=null;
						Date convertedDate = null;
						if(details !=null && details.size()>0){
						 for (Object[] cadreFormalDetails : details) {
							 
							 CadreCommitteeMemberVO cadreDetailsVO=new CadreCommitteeMemberVO();
							 
								cadreDetailsVO.setCadreId((Long)cadreFormalDetails[0]);//cadreId
								cadreDetailsVO.setName(cadreFormalDetails[1] !=null ? cadreFormalDetails[1].toString() : "");
								
								if(cadreFormalDetails[2] !=null){
									dateFormat = new SimpleDateFormat("yyyy-MM-dd");
									convertedDate = (Date) dateFormat.parse(cadreFormalDetails[2].toString());
									 String lines[] = convertedDate.toString().split(" ");
									 cadreDetailsVO.setDateOfBirth(lines[1]+ " "+lines[2] +" " + lines[5]);
								}else{
									cadreDetailsVO.setDateOfBirth("");
								}
								//cadreDetailsVO.setDateOfBirth(cadreFormalDetails[2] !=null ? cadreFormalDetails[2].toString() : "");
								cadreDetailsVO.setAge(cadreFormalDetails[3] !=null ? cadreFormalDetails[3].toString() :"" );
								cadreDetailsVO.setQualification(cadreFormalDetails[5] !=null ? cadreFormalDetails[5].toString() : "");
								
								cadreDetailsVO.setOccupation(cadreFormalDetails[7] !=null ? cadreFormalDetails[7].toString() : "");
								
								cadreDetailsVO.setVoterId(cadreFormalDetails[8] !=null ? cadreFormalDetails[8].toString() :"" );
								
								cadreDetailsVO.setPanchayatName(cadreFormalDetails[9] !=null ? cadreFormalDetails[9].toString() : "-" );
								
								cadreDetailsVO.setTehsilName(cadreFormalDetails[10] !=null ? cadreFormalDetails[10].toString() : "" );
								
								cadreDetailsVO.setConstituencyName(cadreFormalDetails[11] !=null ? cadreFormalDetails[11].toString() : "" );
								
								cadreDetailsVO.setMobileNo(cadreFormalDetails[12] !=null ? cadreFormalDetails[12].toString() : "");
								cadreDetailsVO.setConstituencyId(cadreFormalDetails[13] !=null ? (Long)cadreFormalDetails[13] : 0l );
								cadreDetailsVO.setVoterIdCardNo(cadreFormalDetails[14] !=null ? cadreFormalDetails[14].toString() : "");
								
								if(cadreFormalDetails[15] !=null){
									String image=cadreFormalDetails[15].toString();
									String imagePath="http://mytdp.com/images/"+IConstants.CADRE_IMAGES+"/"+image+"";
									
									cadreDetailsVO.setImagePath(imagePath);
								}
								else{
									cadreDetailsVO.setImagePath("");
								}
								
								cadreDetailsVO.setMembershipNo(cadreFormalDetails[16] !=null ? cadreFormalDetails[16].toString() :"");
								cadreDetailsVO.setHouseNo(cadreFormalDetails[17] !=null ? cadreFormalDetails[17].toString() :"");
								cadreDetailsVO.setDistrictName(cadreFormalDetails[18] !=null ? cadreFormalDetails[18].toString() : "" );
								cadreDetailsVO.setStateName(cadreFormalDetails[19] !=null ? cadreFormalDetails[19].toString() : "" );
								cadreDetailsVO.setCasteName(cadreFormalDetails[20] !=null ? cadreFormalDetails[20].toString():"");
								
								String dataSourceType="";
								if(cadreFormalDetails[24] !=null){
									dataSourceType=cadreFormalDetails[24].toString();
								}
								
								if(cadreFormalDetails[21] !=null){
									
									String webUserId=cadreFormalDetails[21].toString();
									String[] partyOfficeIds=IConstants.PARTY_OFFICE_USER_IDS.split(",");
									String[] mahanaduIds=IConstants.MAHANADU_USER_IDS.split(",");
								
									boolean partyOffice=Arrays.asList(partyOfficeIds).contains(webUserId);
									if(partyOffice){
										cadreDetailsVO.setRegisteredOn(partyOfficeIds[0]);
									}
									else if(partyOffice ==false){
										boolean mahandu=Arrays.asList(mahanaduIds).contains(webUserId);
										if(mahandu){
											cadreDetailsVO.setRegisteredOn(mahanaduIds[0]);
										}
										else{
											cadreDetailsVO.setRegisteredOn(dataSourceType);
										}
									}
									
								}//registered On
								else{
									cadreDetailsVO.setRegisteredOn(dataSourceType);
								}
								cadreDetailsVO.setEmailId(cadreFormalDetails[23] !=null ? cadreFormalDetails[23].toString(): "");
								if(cadreFormalDetails[22] !=null){
									dateFormat = new SimpleDateFormat("yyyy-MM-dd");
									convertedDate = (Date) dateFormat.parse(cadreFormalDetails[22].toString());
									 String lines[] = convertedDate.toString().split(" ");
									 cadreDetailsVO.setRegisteredTime(lines[1]+ " "+lines[2] +" " + lines[5]);
								}
								
								//Ids
								cadreDetailsVO.setPanchayatId(cadreFormalDetails[25] !=null ? Long.parseLong(cadreFormalDetails[25].toString()) :0l);
								cadreDetailsVO.setTehsilId(cadreFormalDetails[26] !=null ? Long.parseLong(cadreFormalDetails[26].toString()):0l);
								cadreDetailsVO.setDistrictId(cadreFormalDetails[27] !=null ? Long.parseLong(cadreFormalDetails[27].toString()):0l);
								cadreDetailsVO.setStateId(cadreFormalDetails[28] !=null ? Long.parseLong(cadreFormalDetails[28].toString()):0l);
								//cadreDetailsVO.setpConstituencyId(cadreFormalDetails[29] !=null ? Long.parseLong(cadreFormalDetails[29].toString()):0l);
								//cadreDetailsVO.setpConstituencyName(cadreFormalDetails[30] !=null ? cadreFormalDetails[30].toString(): "");
								cadreDetailsVO.setBoothId(cadreFormalDetails[31] !=null ? Long.parseLong(cadreFormalDetails[31].toString()):0l);
								
								if(cadreDetailsVO.getPanchayatId() == null || cadreDetailsVO.getPanchayatId().longValue() == 0L)
									cadreDetailsVO.setPanchayatId(cadreFormalDetails[33] !=null ? Long.parseLong(cadreFormalDetails[33].toString()) :0l);
								cadreDetailsVO.setRelativeName(cadreFormalDetails[35] !=null ? cadreFormalDetails[35].toString() :"");
								
								List<Object[]> pList = delimitationConstituencyAssemblyDetailsDAO.findParliamentForAssemblyForTheGivenYear(cadreDetailsVO.getConstituencyId(),2009L);
								if(pList != null && pList.size()>0){
									Object[] parliament = pList.get(0);
									cadreDetailsVO.setpConstituencyId(commonMethodsUtilService.getLongValueForObject(parliament[0]));
									cadreDetailsVO.setpConstituencyName(commonMethodsUtilService.getStringValueForObject(parliament[1]));
								}
							
								listOfCadreVO.add(cadreDetailsVO);
						}
						 finalcadreVo.setKnownList(listOfCadreVO);
					}
				}
			
		}catch(Exception e){
			LOG.error("Exception occured in getCadresDetailsOfDeathsAndHospitalization() Method - ",e);
		}
		
		return finalcadreVo;
	}
	public BasicVO getParticipatedConstituency(Long tdpCadreId){
		
		String name = "";
		BasicVO finalVo = new BasicVO(); 
		List<BasicVO> voList = new ArrayList<BasicVO>();
		try{
			
			
			List<Object[]> list = tdpCadreContestedLocationDAO.getParticipatedCandidateConstituency(tdpCadreId);
			
			if(list !=null && list.size()>0){
				for (Object[] objects : list) {
					BasicVO vo= new BasicVO();
					vo.setId((Long)objects[0]);//constituency or parlimentId
					name = objects[1].toString();
					vo.setName(objects[1].toString());
					vo.setElectionTypeId(objects[2] !=null ? (Long)objects[2]:0l);
					vo.setElectionType(objects[3] !=null ? objects[3].toString() : "");
					
					voList.add(vo);
				}
				
				Long parliamentConId=0l;
				Long districtId=0l;
				Long assemblyConstituencyId = 0l;
				String electionType = "";
				String parliament = "";
				String district = "";
				if(voList !=null && voList.size()>0){
					
					if(voList.get(0).getElectionType().equalsIgnoreCase("Assembly")){
						
						List<Long> constiList=new ArrayList<Long>();
						constiList.add(voList.get(0).getId());
						
						assemblyConstituencyId = voList.get(0).getId();
						electionType = voList.get(0).getElectionType();
						
						List<Object[]> parlamentWithDistrict = delimitationConstituencyAssemblyDetailsDAO.findLatestParliamentsAndDistrictForAssembly(constiList);
						
						List<BasicVO> basicList=new ArrayList<BasicVO>();
						if(parlamentWithDistrict !=null){
							for(Object[] parliamentAndDistrict:parlamentWithDistrict){
								BasicVO vo=new BasicVO();
								vo.setId(parliamentAndDistrict[1] !=null ? (Long)parliamentAndDistrict[1]:0l);//parliamentId
								vo.setName(parliamentAndDistrict[2] !=null ? parliamentAndDistrict[2].toString():"");//parliamentName
								parliament = parliamentAndDistrict[2] != null ? parliamentAndDistrict[2].toString():"";
								vo.setDistrictId(parliamentAndDistrict[3] !=null ? (Long)parliamentAndDistrict[3]:0l);
								vo.setDistrict(parliamentAndDistrict[4] !=null ? parliamentAndDistrict[4].toString() :"");
								district = parliamentAndDistrict[4] !=null ? parliamentAndDistrict[4].toString() :"";
								
								basicList.add(vo);
							}
						}
						
						if(basicList !=null && basicList.size()>0){
							parliamentConId=basicList.get(0).getId();
							districtId=basicList.get(0).getDistrictId();
						}
						
					}
					else if(voList.get(0).getElectionType().equalsIgnoreCase("Parliament")){
						parliamentConId = voList.get(0).getId();
						parliament = voList.get(0).getName();
						
						List<Long> districts = delimitationConstituencyAssemblyDetailsDAO.findDistrictsBYParliament(parliamentConId);
						
						districtId = districts.get(0);
						district = districtDAO.getDitrictNmaeById(districtId);
						
					}
					
					finalVo.setId(assemblyConstituencyId);
					finalVo.setElectionType(electionType);
					finalVo.setParlimentId(parliamentConId);
					finalVo.setDistrictId(districtId);
					finalVo.setName(name);
					finalVo.setParliament(parliament);
					finalVo.setDistrict(district);
				}
				
			}
			
			return finalVo;
		}catch (Exception e) {
			e.printStackTrace();
		}
		return finalVo;
	}
	
	
	public CadreOverviewVO getTdpcadreDetailsByTdpCadreId(Long tdpCadreId,String houseNo,String voterCardNo)
	{
		CadreOverviewVO returnVO = new CadreOverviewVO();
		try {
			List<Object[]> voterDetails = null;
			if(tdpCadreId != null && tdpCadreId.longValue() ==0L)
			{
				voterDetails = boothPublicationVoterDAO.getConstyPublicationIdByVoterId(voterCardNo);
				if(voterDetails != null && voterDetails.size()>0)
				{
					for (Object[] voter : voterDetails) {
						Long publicationDateId = voter[2] != null ? Long.valueOf(voter[2].toString()):0L;
						if(publicationDateId != null && publicationDateId.longValue() == IConstants.VOTER_DATA_PUBLICATION_ID)
						{
							Long voterId = voter[3] != null ? Long.valueOf(voter[3].toString()):0L;
							List<TdpCadre> tdpCadreList = tdpCadreDAO.getVoterByVoterId(voterId,1L);
							if(tdpCadreList != null && tdpCadreList.size()>0)
							{
								TdpCadre tdpCadre =  (TdpCadre) tdpCadreList.get(0);
								
								if(tdpCadre != null)
								{
									tdpCadreId = tdpCadre.getTdpCadreId();
								}
							}
							break;
						}
					}
				}
			}
			
			if(tdpCadreId != null && tdpCadreId.longValue()>0L)
			{
				CadreCommitteeMemberVO cadreVO = cadreFormalDetailedInformation(tdpCadreId,IConstants.CADRE_ENROLLMENT_NUMBER,1L);
				
				if(cadreVO != null )
				{
					returnVO.setAge(cadreVO.getAge());
					returnVO.setAreaType(cadreVO.getAreaType());
					returnVO.setBoothId(cadreVO.getBoothId());
					returnVO.setCadreId(cadreVO.getCadreId());
					returnVO.setCandidateId(cadreVO.getCandidate());
					returnVO.setConstituencyId(cadreVO.getConstituencyId());
					returnVO.setCasteName(cadreVO.getCasteName());
					returnVO.setConstituencyName(cadreVO.getConstituencyName());
					returnVO.setDob(cadreVO.getDateOfBirth());
					returnVO.setDistrictId(cadreVO.getDistrictId());
					returnVO.setDistrictName(cadreVO.getDistrictName());
					returnVO.setEmail(cadreVO.getEmailId());
					returnVO.setEnrollmentYears(cadreVO.getEnrollmentYears());
					returnVO.setHouseNo(cadreVO.getHouseNo());
					returnVO.setImagePath(cadreVO.getImagePath());
					returnVO.setLocalElectionBodyId(cadreVO.getLocalElectionBody()!=null?cadreVO.getLocalElectionBody().toString():"");
					returnVO.setMembershipNo(Long.valueOf(cadreVO.getMembershipNo()));
					returnVO.setMobileNo(cadreVO.getMobileNo());
					returnVO.setCandidateName(cadreVO.getName());
					returnVO.setOccupation(cadreVO.getOccupation());
					returnVO.setParliamentId(cadreVO.getpConstituencyId());
					returnVO.setParlimentName(cadreVO.getpConstituencyName());
					returnVO.setPanchayatId(cadreVO.getPanchayatId());
					returnVO.setPanchayatName(cadreVO.getPanchayatName());
					returnVO.setPartNo(Long.valueOf(cadreVO.getPartNo()));
					returnVO.setPartyPosition(cadreVO.getPartyPosition());
					returnVO.setRegisteredAt(cadreVO.getRegisteredOn());
					returnVO.setRegisteredTime(cadreVO.getRegisteredTime());
					returnVO.setRepresentativeType(cadreVO.getRepresentativeType());
					returnVO.setStateId(cadreVO.getStateId());
					returnVO.setStateName(cadreVO.getStateName());
					returnVO.setTehsilId(cadreVO.getTehsilId());
					returnVO.setTehsilName(cadreVO.getTehsilName());
					returnVO.setVoterId(cadreVO.getVoterId() != null && !cadreVO.getVoterId().trim().isEmpty()?Long.valueOf(cadreVO.getVoterId()):0L);
					returnVO.setVoterCardNo(cadreVO.getVoterIdCardNo());
					returnVO.setQualification(cadreVO.getQualification());
					returnVO.setDeletedStatus(cadreVO.getDeletedStatus());
					returnVO.setDeletedReason(cadreVO.getDeletedreason());
					
					List<TdpCadreFamilyDetailsVO> familyVOList = getCadreFamilyDetails(tdpCadreId);
					if(familyVOList != null && familyVOList.size()>0)
					{
						returnVO.getFamilyMembersList().addAll(familyVOList);
						
						TdpCadreFamilyDetailsVO vo = familyVOList.get(0);
						returnVO.setFamilyMembersSurveyCount(vo.getFamilySurveyCount());
						returnVO.setPartyPositionsCount(vo.getPartyPositionsCount());
						returnVO.setPublicRepresentativesCount(vo.getPartyRepresentativesCount());
					}
					
					//returnVO.getElectionResultsPerfList().addAll(getElectionPerformanceInCadreLocation(tdpCadreId));
					
					/*PartyMeetingVO partyMeetingVO = partyMeetingService.getPartyMeetingDetailsBySearchType(tdpCadreId);
					if(partyMeetingVO != null)
					{
						returnVO.setMeetingAttendenceCount(partyMeetingVO.getAttendedCount());
						returnVO.setMeetingInvitationCount(partyMeetingVO.getInvitedCount());
						returnVO.setMeetingAbsentCount(partyMeetingVO.getAbsentCount());
					}
					*/
					PartyMeetingVO eventsVO =  mahaNaduService.getParticipatedCandidateEventDetails(tdpCadreId);
					if(eventsVO != null)
					{
						returnVO.setEventAttendedCount(eventsVO.getAttendedCount());
						returnVO.setEventInvitationCount(eventsVO.getInvitedCount());
						returnVO.setEventAbsentCount(eventsVO.getAbsentCount());
					}
				}
				
			}
			else
			{
				//List<VoterInfoVO> getCandidateInfoBySearchCriteria(String searchType, Long candidateId,String staticContentLoc,String constituencyId)
				//List<Object[]> voterDetails = boothPublicationVoterDAO.getConstyPublicationIdByVoterId(voterCardNo);
				if(voterDetails != null && voterDetails.size()>0)
				{
					for (Object[] voter : voterDetails) {
						Long publicationDateId = voter[2] != null ? Long.valueOf(voter[2].toString()):0L;
						if(publicationDateId != null && publicationDateId.longValue() == IConstants.VOTER_DATA_PUBLICATION_ID)
						{
							Long constituencyId = voter[0] != null ? Long.valueOf(voter[0].toString()):0L;
							Long boothId = voter[1] != null ? Long.valueOf(voter[1].toString()):0L;
							Long districtId = voter[7] != null ? Long.valueOf(voter[7].toString()):0L;
							String districtName= voter[12] != null ? voter[12].toString():"";
							String constituencyName= voter[11] != null ? voter[11].toString():"";
							
							Long voterId = voter[3] != null ? Long.valueOf(voter[3].toString()):0L;
							List<Long> voterIdsList = new ArrayList<Long>(0);
							voterIdsList.add(voterId);
							
							String voterName =  voter[4] != null ? voter[4].toString():"";
							String age =  voter[5] != null ? voter[5].toString():"";
							String gender =  voter[6] != null ? voter[6].toString():"";
							String relativeName =  voter[8] != null ? voter[8].toString():"";
							String relativeType =  voter[10] != null ? voter[10].toString():"";
							String hNo = voter[9] != null ? voter[9].toString():"";;
							String partNo= voter[13] != null ? voter[13].toString():"";
							LocalElectionBody localElectionBody = null;//voter[14] != null ? (LocalElectionBody) voter[14]:null;
							Long panchayatId = voter[15] != null ? Long.valueOf(voter[15].toString()):0L;
							String panchayatName= voter[16] != null ? voter[16].toString():"";
							Long tehsilId = voter[17] != null ? Long.valueOf(voter[17].toString()):0L;
							String tehsilName= voter[18] != null ? voter[18].toString():"";
							Long stateId = voter[19] != null ? Long.valueOf(voter[19].toString()):0L;
							String stateName= voter[20] != null ? voter[20].toString():"";
							String imgPath = voter[21] != null ? voter[21].toString():"";
							
							List<Object[]> parliamentList = delimitationConstituencyAssemblyDetailsDAO.findLatestParliamentForAssembly(constituencyId);
							if(parliamentList != null && parliamentList.size()>0)
							{
								Object[] parliament = parliamentList.get(0);
								if(parliament == null)
								{
									if(parliamentList.size()>1)
										parliament = parliamentList.get(1);
								}
								else
								{
									Long parliamentId = parliament[0] != null ? Long.valueOf(parliament[0].toString()):0L;
									String parliamentName= parliament[1] != null ? parliament[1].toString():"";
									
									returnVO.setParliamentId(parliamentId);
									returnVO.setParlimentName(parliamentName);
								}
							}
							
							
							//String filePath = "voter_images"+IConstants.FILE_SEPARATOR+constituencyId+IConstants.FILE_SEPARATOR+"Part"+partNo.trim()+IConstants.FILE_SEPARATOR+voterCardNo+".jpg";
							//List<VoterInfoVO> voterDetaildInfo = cadreRegistrationService.getCandidateInfoBySearchCriteria("voter",voterId,"",constituencyId.toString());
							//System.out.println(voterDetaildInfo);

							returnVO.setCandidateName(voterName);									
							returnVO.setAge(age);
							returnVO.setGender(gender);
							returnVO.setRelativeName(relativeName);
							returnVO.setRelationType(relativeType);
							returnVO.setConstituencyId(constituencyId);									
							returnVO.setConstituencyName(constituencyName);
							returnVO.setBoothId(boothId);	
							returnVO.setPartNo(Long.valueOf(partNo));
							
							List<Object[]> voterCasteInfo = userVoterDetailsDAO.getCasteByVoterId(1L,voterIdsList);
							if(voterCasteInfo != null && voterCasteInfo.size()>0)
							{
								Object[] casteInfo = voterCasteInfo.get(0);
								String casteName = casteInfo[1] != null ? casteInfo[1].toString().trim():"";
								returnVO.setCasteName(casteName);
							}
															
							returnVO.setDistrictId(districtId);
							returnVO.setDistrictName(districtName);
							returnVO.setHouseNo(hNo);									
							returnVO.setImagePath(IConstants.VOTER_IMG_FOLDER_PATH+"/"+imgPath);
							returnVO.setPanchayatId(panchayatId);
							returnVO.setPanchayatName(panchayatName);
							returnVO.setTehsilId(tehsilId);
							returnVO.setTehsilName(tehsilName);
							returnVO.setStateId(stateId);
							returnVO.setStateName(stateName);
							if(localElectionBody != null)
								returnVO.setLocalElectionBodyId(localElectionBody.getLocalElectionBodyId().toString());
							
							
							List<Object[]> mobileNosList = mobileNumbersDAO.getVotersMobileNumberDetails(voterIdsList);
							
							if(mobileNosList != null && mobileNosList.size()>0)
							{
								String mobileNumber = "";
								for (Object[] mobile : mobileNosList) {
									String mobileNo = mobile[1] != null ? mobile[1].toString().trim():"";
									if(!mobileNo.isEmpty())
									{
										mobileNumber = mobileNumber+","+mobileNo;
									}
								}
								returnVO.setMobileNo(mobileNumber);
							}
															
							returnVO.setVoterId(voterId);
							returnVO.setVoterCardNo(voterCardNo);
							
							List<Object[]> familyInfo = boothPublicationVoterDAO.getFamilyDetaislByHouseNoAndBoothId(boothId,hNo);
							List<TdpCadreFamilyDetailsVO> familyVOList = new ArrayList<TdpCadreFamilyDetailsVO>(0);
							if(familyInfo != null && familyInfo.size()>0)
							{
								List<String> voterIdCardNoList = new ArrayList<String>(0);
								
								for (Object[] family : familyInfo) 
								{
									Long familyVoterID = family[0] != null ? Long.valueOf(family[0].toString().trim()):0L;
									
									if( familyVoterID.longValue() != voterId.longValue())
									{
										TdpCadreFamilyDetailsVO fmilyVO = new TdpCadreFamilyDetailsVO();
										fmilyVO.setVoterId(family[0] != null ? Long.valueOf(family[0].toString().trim()):0L);
										fmilyVO.setName(family[1] != null ? family[1].toString():"");
										fmilyVO.setGender(family[4] != null ? family[4].toString():"");
										fmilyVO.setAge(family[5] != null ? Long.valueOf(family[5].toString()):0L);								
										fmilyVO.setVotercardNo(family[6] != null ? family[6].toString():"");
										fmilyVO.setRelation(family[3] != null ? family[3].toString():"");
										fmilyVO.setRelativeName(family[7] != null ? family[7].toString():null);
										voterIdCardNoList.add(fmilyVO.getVotercardNo());
										familyVOList.add(fmilyVO);
										//familyMap.put(fmilyVO.getVotercardNo().trim(), fmilyVO);
									}								
								}
								
								if(voterIdCardNoList != null && voterIdCardNoList.size() > 0)
								{
									List<Object[]> list = tdpCadreDAO.getSurveyPaticipatedCountByVoterIdcardNoList(voterIdCardNoList);
									if(list != null && list.size() > 0)
									{
										Long familyParticipatedCount = 0L;
										Map<String,Map<Long,Long>> voterWiseSurveyWisecountMap  = new HashMap<String, Map<Long,Long>>(0); 
										for(Object[] params: list)
										{
											try {												
											
												/* start Date 23 rd june 2016 changed by srishailam */
												String voterrCardNo =commonMethodsUtilService.getStringValueForObject(params[1]);
												Map<Long,Long> survyeMap = new HashMap<Long, Long>(0);
												if(voterWiseSurveyWisecountMap.get(voterrCardNo) != null){
													survyeMap = voterWiseSurveyWisecountMap.get(voterrCardNo);
													survyeMap.put(commonMethodsUtilService.getLongValueForObject(params[2]), commonMethodsUtilService.getLongValueForObject(params[0]));
												}
												else
												{
													survyeMap.put(commonMethodsUtilService.getLongValueForObject(params[2]), commonMethodsUtilService.getLongValueForObject(params[0]));
													voterWiseSurveyWisecountMap.put(voterrCardNo, survyeMap);
												}
												/* end Date 23 rd june 2016 changed by srishailam */
												/*if(params[1] != null)
												{
													TdpCadreFamilyDetailsVO VO =getMatchedTdpCadreFamilyDetailsVO(familyVOList, params[1].toString());
												    if(VO != null)
												    	VO.setCount(params[0] != null?Long.parseLong(params[0].toString()):0l);
												}*/
											} catch (Exception e) {
												e.printStackTrace();
											}
											
										}
										/* start Date 23 rd june 2016 changed by srishailam */
										if(commonMethodsUtilService.isMapValid(voterWiseSurveyWisecountMap)){
											for (String voterCarddNo : voterWiseSurveyWisecountMap.keySet()) {
												TdpCadreFamilyDetailsVO VO =getMatchedTdpCadreFamilyDetailsVO(familyVOList, voterCarddNo.trim());
											    if(VO != null){
											    	if(VO.getCount() == null)
											    		VO.setCount(0L);
											    	Map<Long,Long> survyeMap = voterWiseSurveyWisecountMap.get(voterCarddNo);
											    	if(commonMethodsUtilService.isMapValid(survyeMap)){
											    		for (Long surveyId : survyeMap.keySet()) {
											    			Long count = survyeMap.get(surveyId);
											    			VO.setCount(VO.getCount()+count);
											    			familyParticipatedCount = familyParticipatedCount+count;
														}
											    	}
											    	//VO.setCount(params[0] != null?Long.parseLong(params[0].toString()):0l);
											    }
											}
										}
										returnVO.setFamilyMembersSurveyCount(familyParticipatedCount);
										/* end Date 23 rd june 2016 changed by srishailam */
										
										//returnVO.setFamilyMembersSurveyCount(Long.valueOf(String.valueOf(list.size())));
									}
									
									
									List<Object[]> cadreMembersInFamilyList = tdpCadreDAO.checkVoterCardNosCadreNosOrNot(voterIdCardNoList);
									List<Long> tdpCadreIDsList = new ArrayList<Long>(0);
									if(cadreMembersInFamilyList != null && cadreMembersInFamilyList.size() > 0)
									{
										for(Object[] params: cadreMembersInFamilyList)
										{
											if(params[0] != null)
											{
												TdpCadreFamilyDetailsVO VO =getMatchedTdpCadreFamilyDetailsVO(familyVOList, params[0].toString());
											    if(VO != null){
											    	VO.setTdpCadreId(params[1] != null?Long.parseLong(params[1].toString()):0l);
											    	VO.setMembershipNo(params[2] != null?params[2].toString():"");
											    	
											    	if(params[4] !=null){
											    		VO.setDeletedStatus(params[4].toString());
														
														if(VO.getDeletedStatus().equalsIgnoreCase("MD")){
															VO.setDeletedReason(params[6] !=null ? params[6].toString():"");
														}
														else{
															VO.setDeletedReason("");
														}
													}
											    	
											    	tdpCadreIDsList.add(VO.getTdpCadreId());
											    }
											}
										}
									}
									
									if(tdpCadreIDsList != null && tdpCadreIDsList.size()>0)
									{
										List<Object[]> cadrePublicRepresentativList = tdpCadreCandidateDAO.getPublicRepresentaativesDetailsForCadreIdsList(tdpCadreIDsList);
										if(cadrePublicRepresentativList != null && cadrePublicRepresentativList.size()>0)
										{
											for (Object[] publicRepresentativ : cadrePublicRepresentativList) {
												if(publicRepresentativ[1] != null)
												{
													TdpCadreFamilyDetailsVO VO =getMatchedTdpCadreFamilyDetailsVO(familyVOList, publicRepresentativ[1].toString());
												    if(VO != null)
												    	VO.setPublicRepresentativeStr(publicRepresentativ[3] != null ? publicRepresentativ[3].toString():"");
												}
											}
											
											returnVO.setPublicRepresentativesCount(Long.valueOf(String.valueOf(cadrePublicRepresentativList.size())));
										}
										
										List<Object[]> cadrePartyPositionDetals =  tdpCommitteeMemberDAO.getMembersInfoByTdpCadreIdsList(tdpCadreIDsList);
										if(cadrePartyPositionDetals != null && cadrePartyPositionDetals.size()>0)
										{
											for (Object[] partyPosition : cadrePartyPositionDetals) {
												if(partyPosition[1] != null)
												{
													TdpCadreFamilyDetailsVO vo =getMatchedTdpCadreFamilyDetailsVO(familyVOList, partyPosition[1].toString());
												    if(vo != null)
												    {
												    	Long committeeLevelId = partyPosition[2] != null?Long.parseLong(partyPosition[2].toString()):0l;
												    	
												    	if(committeeLevelId!= null && committeeLevelId.longValue()>0L)
												    	{
												    		//Long committeeLevelValue = partyPosition[3] != null?Long.parseLong(partyPosition[3].toString()):0l;
													    	String committeeStr = partyPosition[5] != null?partyPosition[5].toString():"";
													    	String roleStr = partyPosition[7] != null?partyPosition[7].toString():"";
													    	String committeeLevelStr = partyPosition[8] != null?partyPosition[8].toString():"";
													    	
													    	String committeeLocation ="";
													    	/*if(committeeLevelId == 5L)
													    	{
													    		committeeLocation = tehsilDAO.get(committeeLevelValue).getTehsilName()+" Mandal ";
													    	}
													    	else if(committeeLevelId == 6L)
													    	{
													    		committeeLocation = panchayatDAO.get(committeeLevelValue).getPanchayatName()+" Panchayat ";
													    	}
													    	else if(committeeLevelId == 7L || committeeLevelId == 9L) // town/division
													    	{
													    		LocalElectionBody localbody = localElectionBodyDAO.get(committeeLevelValue);
													    		if(localbody.getElectionType().getElectionTypeId() != 7L)
													    			committeeLocation = localbody.getName()+" "+localbody.getElectionType().getElectionType();
													    	}
													    	else if(committeeLevelId == 8L)
													    	{
													    		Constituency constituency = constituencyDAO.get(committeeLevelValue);
													    		committeeLocation = constituency.getName()+" ( "+constituency.getLocalElectionBody().getName()+" "+constituency.getLocalElectionBody().getElectionType().getElectionType()+" )";
													    	}
													    	else if(committeeLevelId == 10L)
													    	{
													    		committeeLocation = stateDAO.get(committeeLevelValue).getStateName();
													    	}
													    	else if(committeeLevelId == 11L)
													    	{
													    		committeeLocation = districtDAO.get(committeeLevelValue).getDistrictName();
													    	}
													    	*/
													    	if(!committeeLocation.isEmpty())
													    		vo.setPartyPositionStr(committeeStr+" - "+roleStr+" ( "+committeeLocation+" )");
													    	else
													    		vo.setPartyPositionStr(committeeStr+" -"+committeeLevelStr+" "+roleStr);
												    	}
												    }
												}
											}
											
											returnVO.setPartyPositionsCount(Long.valueOf(String.valueOf(cadrePartyPositionDetals.size())));
										}
									}
								}
							}
							returnVO.getFamilyMembersList().addAll(familyVOList);
							List<RegisteredMembershipCountVO> resultList = new ArrayList<RegisteredMembershipCountVO>();
							UserAddress userAddress = new UserAddress();
							if(returnVO.getBoothId() != null && returnVO.getBoothId() > 0)
							userAddress.setBooth(boothDAO.get(returnVO.getBoothId()));
							if(returnVO.getPanchayatId() != null && returnVO.getPanchayatId() > 0)
							userAddress.setPanchayat(panchayatDAO.get(returnVO.getPanchayatId()));
							if(returnVO.getTehsilId() != null && returnVO.getTehsilId() > 0)
							userAddress.setTehsil(tehsilDAO.get(returnVO.getTehsilId()));
							
							if(returnVO.getLocalElectionBodyId() != null && Long.valueOf(returnVO.getLocalElectionBodyId())>0L)
								userAddress.setLocalElectionBody(localElectionBodyDAO.get(Long.valueOf(returnVO.getLocalElectionBodyId())));
							
							userAddress.setConstituency(constituencyDAO.get(returnVO.getConstituencyId()));
							userAddress.setDistrict(districtDAO.get(returnVO.getDistrictId()));
							userAddress.setState(stateDAO.get(1L));
							
							if(userAddress != null)
							{
								List<Long> partyIds = new ArrayList<Long>();
								partyIds.add(872l);//TDP partyId
								partyIds.add(163l);//BJP partyId
								
								if(userAddress != null)
								{
									RegisteredMembershipCountVO countVO = null;
									 countVO = setElectionPerformanceDetailsInCadreLocation(2014l, userAddress, partyIds,null);
									if(countVO != null)
										resultList.add(countVO);
									
									/*
									   countVO = setElectionPerformanceDetailsInCadreLocation(2009l, userAddress, partyIds);
										if(countVO != null)
											resultList.add(countVO);
									*/
								}
						  }
							
							if(resultList != null && resultList.size()>0)
								returnVO.setElectionResultsPerfList(resultList);
							
							break; // iteration only once 
							
						}
					}
				}
				
			}
			if(returnVO.getLocalElectionBodyId() != null && !returnVO.getLocalElectionBodyId().isEmpty())
			{
				List boothLocation = boothDAO.getBoothLocations(new Long(returnVO.getLocalElectionBodyId()),returnVO.getVoterCardNo());
				if(boothLocation != null && boothLocation.size() > 0)
					returnVO.setBoothLocation(boothLocation.get(0) != null?boothLocation.get(0).toString():"");
				
			}
			
		} catch (Exception e) {
			LOG.error("Exception occured in getTdpcadreDetailsByTdpCadreId() Method - ",e);
		}
		
		return returnVO;
	}

	public WebServiceResultVO getCandidateAndLocationSummaryNews(String startDate,String endDate,String locationType,Long locationId,Long candidateId){
		WebServiceResultVO webServiceResultVO=new WebServiceResultVO();
		try{
			webServiceResultVO=webServiceHandlerService.getCandidateAndLocationSummaryNews(startDate,endDate,locationType,locationId,candidateId);
			
		}catch (Exception e) {
			LOG.error(" Exception Occured in getCandidateAndLocationSummaryNews() method, Exception - ",e);
			return null;
		}
		
		return webServiceResultVO;
	}
	
	

	public Long getCadreIdByMembershipId(String memberShipNo,Long constituencyId)
	{
		try{
			Long cadreId = null;
			List<Long> cadreIdsList =  tdpCadreDAO.getCadreIdByMembershipId(memberShipNo,constituencyId) ;
			if(cadreIdsList != null && cadreIdsList.size() > 0)
				cadreId = cadreIdsList.get(0);
			return cadreId;
		}catch (Exception e) {
			LOG.error(" Exception Occured in getCadreIdByMembershipId() method, Exception - ",e);
			return null;
		}
		
	}
	
	
  public ComplaintStatusCountVO getCategoryWiseStatusCount(Long tdpCadreId)
  {
	  ComplaintStatusCountVO returnVo = null;
		try{
			String memberShipNo = tdpCadreDAO.getMemberShipIdByCadreId(tdpCadreId);
			 if(memberShipNo != null)
			 {
				   List<Object[]> list = tdpCadreDAO.getCategorywiseStatusCount(memberShipNo);//0typeOfissue,1count,2 completedstatus
				    
				   if(list != null && list.size() > 0)
				   {
					   Long totalComplaints = 0l;
				    	 returnVo = new ComplaintStatusCountVO();
					     List<ComplaintStatusCountVO> statusList = new ArrayList<ComplaintStatusCountVO>();
					     returnVo.setSubList(statusList);
				    	
						 setStatusList1(statusList);//ProblemStatus
						 for(Object[] params: list)
						 {
							 ComplaintStatusCountVO statusVO = getMatchedVO(params[2].toString(),statusList);
							 totalComplaints += (params[1] != null?(Long)params[1]:0l);
							 if(statusVO != null)
							 {
								 statusVO.setCount(params[1] != null?statusVO.getCount()+(Long)params[1]:0l);
							 }
						 }
						 
						 for(ComplaintStatusCountVO vo : statusList)//typeOfissue
						 {
							 List<ComplaintStatusCountVO> typeOfIssueVOList = new ArrayList<ComplaintStatusCountVO>();
							 setTypeOfIssueVO(typeOfIssueVOList);
							 vo.setSubList(typeOfIssueVOList);
						 }
						 
						 for(Object[] params: list)
						 {
							 ComplaintStatusCountVO statusVO = getMatchedVO(params[2].toString(),statusList);
							 if(statusVO != null)
							 {
								 ComplaintStatusCountVO typeOfIssueVO = getMatchedVO(params[0].toString(),statusVO.getSubList());
								 if(typeOfIssueVO != null)
								 {
									 typeOfIssueVO.setCount(params[1] != null?(Long)params[1]:0l);
								 }
							 }
							 
					 }
					 returnVo.setCount(totalComplaints);
				}
			}
		 }
			catch (Exception e) {
				LOG.error("Exception Occured in getCategoryWiseStatusCount() method, Exception - ",e);
			}
			return returnVo;
	 }
  
  public List<QuestionAnswerVO> getCandidateAndConstituencySurveyResult(Long candidateId,Long constituencyId,Long surveyId)
	{	
		 List<QuestionAnswerVO> finalList=new ArrayList<QuestionAnswerVO>();
		  try{
			  
			  //Calling Request.
			  Client client = Client.create();
			  //client.addFilter(new HTTPBasicAuthFilter(IConstants.SURVEY_WEBSERVICE_USERNAME, IConstants.SURVEY_WEBSERVICE_PASSWORD));
			  WebResource webResource = client.resource("http://mytdp.com/Survey/WebService/getCandidateAndConstituencySurveyResult/"+candidateId+"/"+constituencyId+"/"+surveyId+"");
			  
			  //Response
			  ClientResponse response = webResource.accept("application/json").get(ClientResponse.class);
	 	      if(response.getStatus() != 200){
	 		    
	 		     throw new RuntimeException("Failed : HTTP error code : "+ response.getStatus());
	 	      }
	 	      else{
	 	       
	 	    	 String output = response.getEntity(String.class);
	 		     List<Long> userIds = new ArrayList<Long>();
	 	    	 //iterating data.
	 		     
	 	    	if(output != null && !output.isEmpty()){
	   	 	   
	 	    	  JSONArray finalArray=new JSONArray(output);
	 	    	  if(finalArray!=null && finalArray.length()>0){
	 	    		
	 	    		 for(int i=0;i<finalArray.length();i++)
	   	 			 {
	 	    			QuestionAnswerVO vo=new QuestionAnswerVO();
	 	    		    JSONObject srvyTmp = (JSONObject) finalArray.get(i);
	 	    		    vo.setSurveyId(srvyTmp.getLong("surveyId"));
	 	    		    vo.setSurveyName(srvyTmp.getString("surveyName"));
	 	    		    
	 	    		    List<QuestionAnswerVO> qstnsLst = new ArrayList<QuestionAnswerVO>();
	 	    		    org.codehaus.jettison.json.JSONArray questsArr = srvyTmp.getJSONArray("questions");
	 	    		    if(questsArr!=null && questsArr.length()>0){
	 	    		    	for(int j=0;j<questsArr.length();j++){
	 	    		    		QuestionAnswerVO qstn=new QuestionAnswerVO();
	 	    		    		JSONObject qstnTemp = (JSONObject) questsArr.get(j);
	 	    		    		qstn.setQuestionId(qstnTemp.getLong("questionId"));
	 	    		    		qstn.setQuestion(qstnTemp.getString("question"));
	 	    		    		
	 	    		    		List<QuestionAnswerVO> optnsLst = new ArrayList<QuestionAnswerVO>();
	 	    		    		org.codehaus.jettison.json.JSONArray optnsArr = qstnTemp.getJSONArray("options");
		  	 	    		    if(optnsArr!=null && optnsArr.length()>0){
		  	 	    		    	for(int k=0;k<optnsArr.length();k++){
		  	 	    		    		QuestionAnswerVO optn=new QuestionAnswerVO();
		  	 	    		    		JSONObject optnTemp = (JSONObject) optnsArr.get(k);
		  	 	    		    		optn.setOptionValue(optnTemp.getLong("optionValue"));
		  	 	    		    		optn.setOptionVal(optnTemp.getString("optionVal"));
		  	 	    		    		optn.setCount(optnTemp.getLong("count"));
		  	 	    		    		optn.setPercentage(optnTemp.getString("percentage"));
		  	 	    		    		optn.setUserId(optnTemp.getLong("userId"));
		  	 	    		    		optnsLst.add(optn);
		  	 	    		    		
		  	 	    		    		userIds.add(optnTemp.getLong("userId"));
		  	 	    		    	}
		  	 	    		   	}
		  	 	    		    
		  	 	    		    qstn.setOptions(optnsLst);
		  	 	    		    qstnsLst.add(qstn);
	 	    		    	}
	 	    		    }
	 	    		    vo.setQuestions(qstnsLst);
	 	    		    finalList.add(vo);
	 	    		 }
	 	    		 //getCandidate Names.
	 	    		 if(userIds!=null && userIds.size()>0){
	 	    			 
	 	    			Map<Long,String> candidatesmap=new HashMap<Long,String>();
	 	    			
	 	    			List<Object[]> candidatesList = candidateDAO.getCandidateNameByCandidateIds(userIds);
	 	    			if(candidatesList!=null && candidatesList.size()>0){
	 	    				for(Object[] obj:candidatesList){
	 	    					if(obj!=null){
	 	    						candidatesmap.put((Long)obj[0],obj[1]!=null?obj[1].toString():"");
	 	    					}
	 	    				}
	 	    			}
	 	    			
	 	    			if(finalList!=null && finalList.size()>0){
	 	    				
	 	    				for(QuestionAnswerVO survey:finalList){
	 	    					
	 	    					if(survey.getQuestions()!=null && survey.getQuestions().size()>0){
	 	    						
	 	    						for(QuestionAnswerVO question:survey.getQuestions()){
	 	    							  
	 	    							if(question.getOptions()!=null && question.getOptions().size()>0){
	 	    								
	 	    								for(QuestionAnswerVO option: question.getOptions()){
	 	    									
	 	    									if(option.getUserId()!=null){
	 	    										
	 	    										String name=candidatesmap.get(option.getUserId());
	 	    										option.setRemarks(name!=null?name:"");
	 	    									}
	 	    								} 
	 	    							}
	 	    						} 
	 	    					}
	 	    				}
	 	    			}
	 	    			
	 	    		 }//if
	 	    		 
	 	    		 
	 	    	  }
	 	    		 
	 	    	}
	 	     }
	 	 
		  }catch(Exception e){
			LOG.error("Exception occured in saveGrievenceIVRResponseDetails() in WebServiceHandlerService Class",e);
		  }
		  return finalList;
	  }
  
  public NtrTrustStudentVO getNtrTrustStudentDetailsInstitutionWise(List<Long> cadreIds){
	  
	  NtrTrustStudentVO finalVo = new NtrTrustStudentVO();
	  List<NtrTrustStudentVO> finalList = new ArrayList<NtrTrustStudentVO>();
	  try{
		  Long educationBefitCount=0l;
		  List<Object[]> studentCountList = studentCadreRelationDAO.getNtrTrustStudentDetailsInstitutionWise(cadreIds);
			if(studentCountList !=null && studentCountList.size()>0){
				for(Object[] studentCount:studentCountList){
					NtrTrustStudentVO vo = new NtrTrustStudentVO();
					vo.setId((Long)studentCount[0]);
					vo.setName(studentCount[1].toString());
					vo.setCount(studentCount[2] !=null ? (Long)studentCount[2]:0l);
					
					if(studentCount[2] !=null && (Long)studentCount[2]>0){
						educationBefitCount +=(Long)studentCount[2];
					}
					
					finalList.add(vo);
				}
				finalVo.setNtrTrustStudentVoList(finalList);
				finalVo.setCount(educationBefitCount);
			}
			
		return finalVo;
		  
	  }catch (Exception e) {
		  LOG.error("Exception Occured in getNtrTrustStudentDetailsInstitutionWise() method, Exception - ",e);
	}
	  return finalVo;
  }
  public List<NtrTrustStudentVO> getStudentFormalDetailsByCadre(List<Long> cadreIds,Long institutionId){
      
      List<NtrTrustStudentVO> studentsFormalDetails = new ArrayList<NtrTrustStudentVO>();
     
      try{
          //0.institutionId,1.studentid,2.studentName,3.dateOfbirth,4.year of joining,5.courseId,6.course code,7.casteId,
          //8.cadreId,9.membershipNo,10.guardian,11.parent alive Status,12.relation
          DateFormat dateFormat=null;
          Date convertedDate = null;
          List<Object[]> students = studentCadreRelationDAO.getStudentFormalDetailsByCadre(cadreIds,institutionId);
         
          if(students !=null){
              for(Object[] student:students){
                  NtrTrustStudentVO vo = new NtrTrustStudentVO();
                  vo.setInstitutionId(student[0] !=null ? (Long)student[0]:0l);
                  vo.setId(student[1] !=null ? (Long)student[1]:0l);
                  vo.setName(student[2] !=null ? student[2].toString():"");
                 
                  if(student[3] !=null){
                      dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                      convertedDate = (Date) dateFormat.parse(student[3].toString());
                       String lines[] = convertedDate.toString().split(" ");
                       vo.setDateStr(lines[1]+ " "+lines[2] +" " + lines[5]);//dateOfBirth
                  }else{
                       vo.setDateStr("");
                  }
                 
                  vo.setYearOfJoining(student[4] !=null ? student[4].toString():"");
                  vo.setCourseId(student[5] !=null ? (Long)student[5]:0l);
                  vo.setCourse(student[6] !=null ? student[6].toString():"");//course Code
                  vo.setCasteStr(student[7] !=null ? student[7].toString():"");
                  vo.setTdpCadreId(student[8] !=null ? (Long)student[8]:0l);
                  vo.setMembershipNo(student[9] !=null ? (Long)student[9]:0l);
                  vo.setGuardian(student[10] !=null ? student[10].toString():"");
                  vo.setStatus(student[11] !=null ? student[11].toString():"");//parent Alive Status
                  vo.setRelation(student[12] !=null ? student[12].toString():"");//relationWithCadre
                 
                  if(vo.getId() !=null && vo.getId() >0l){
                      //0.Parent Name,1.relation
                      List<Object[]> parentDetails = studentParentDetailsDAO.getParentDetails(vo.getId());
                      if(parentDetails !=null && parentDetails.size()>0){
                          for(Object[] parent:parentDetails){
                              if(parent[1] !=null && parent[1].toString().equalsIgnoreCase("father"))
                              {
                                  vo.setFatherName(parent[0] !=null ? parent[0].toString():"");
                              }
                              else if(parent[1] !=null && parent[1].toString().equalsIgnoreCase("mother")){
                                  vo.setMotherName(parent[0] !=null ? parent[0].toString():"");
                              }
                              else{
                                  vo.setParentName(parent[0] !=null ? parent[0].toString():"");
                              }
                          }
                      }
                     
                      //Contact Details
                      List<NtrTrustStudentVO> contactDetails =getContactDetailsOfStudent(vo.getId());
                     
                      if(contactDetails !=null && contactDetails.size()>0){
                          vo.setNtrTrustStudentVoList(contactDetails);
                      }
                     
                      //academic year details of Student
                      List<NtrTrustStudentVO> academicyearDetails=getAcademicYearDetailsOfStudent(vo.getId());
                     
                      if(academicyearDetails !=null && academicyearDetails.size()>0){
                          vo.setAcademicDetailsList(academicyearDetails);
                      }
                     
                      //student address Details
                      List<NtrTrustStudentVO> addressDetails = getStudentSpecificAddressDetails(vo.getId());
                     
                      if(addressDetails !=null && addressDetails.size()>0){
                          vo.setAddressDetailsList(addressDetails);
                      }
                     
                      //getReferalDetailsOfStudent
                     
                      List<NtrTrustStudentVO> recomendationDetails=getRecomendationDetailsOfStudent(vo.getId());
                      if(recomendationDetails !=null && recomendationDetails.size()>0){
                          vo.setRecomendationDetailsList(recomendationDetails);
                      }
                     
                  }
                 
                  studentsFormalDetails.add(vo);
              }
              return studentsFormalDetails;
          }
      }catch(Exception e){
          LOG.error("Exception Occured in getStudentFormalDetailsByCadre() method, Exception - ",e);
      }
      return studentsFormalDetails;
  }
	public List<NtrTrustStudentVO> getContactDetailsOfStudent(Long studentId){
		
		List<NtrTrustStudentVO> listOfContacts = new ArrayList<NtrTrustStudentVO>();
		try{
			//0.phone No,1.type
			List<Object[]> studentContacts =  studentContactDAO.getContactDetailsOfStudent(studentId);
			if(studentContacts !=null && studentContacts.size()>0){
				for(Object[] studentContact:studentContacts){
					NtrTrustStudentVO vo=new NtrTrustStudentVO();
					vo.setPhoneNo(studentContact[0] !=null ? studentContact[0].toString():"");
					vo.setPhoneType(studentContact[1] !=null ? studentContact[1].toString():"");
					
					listOfContacts.add(vo);
				}
			}
			
		}catch(Exception e){
			LOG.error("Exception Occured in getContactDetailsOfStudent() method, Exception - ",e);
		}
		
		return listOfContacts;
	}
	public List<NtrTrustStudentVO> getAcademicYearDetailsOfStudent(Long studentId){
		
		List<NtrTrustStudentVO> listOfAcademicdetails = new ArrayList<NtrTrustStudentVO>();
		try{
			//0.academicYearid,1.startYear,2.startMonth,3.endYear,4.endMonth,5.status(Like ongoing)
			List<Object[]>	acdemicDetails = studentCourseDAO.getAcademicYearDetailsOfStudent(studentId);
			
			if(acdemicDetails !=null && acdemicDetails.size()>0){
				for(Object[] details:acdemicDetails){
					NtrTrustStudentVO vo=new NtrTrustStudentVO();
					vo.setId((Long)details[0]);
					vo.setStartYear(details[1] !=null ? details[1].toString() :"");
					vo.setStartMonth(details[2] !=null ? details[2].toString() :"");
					vo.setEndYear(details[3] !=null ? details[3].toString() :"");
					vo.setEndMonth(details[4] !=null ? details[4].toString() :"");
					vo.setStatus(details[5] !=null ? details[5].toString() :"");//status Of Academic
					
					listOfAcademicdetails.add(vo);
				}
			}
			
		}catch(Exception e){
			LOG.error("Exception Occured in getAcademicYearDetailsOfStudent() method, Exception - ",e);
		}
		
		return listOfAcademicdetails;
		
	}
	
	public List<NtrTrustStudentVO> getStudentSpecificAddressDetails(Long studentId){
		
		List<NtrTrustStudentVO> addressList = new ArrayList<NtrTrustStudentVO>();
		try{
			
			List<StudentAddress> studentAddressDetails =  studentAddressDAO.getStudentSpecificAddressDetails(studentId);
			
			if(studentAddressDetails !=null && studentAddressDetails.size()>0){
				for(StudentAddress studentAddress:studentAddressDetails){
					NtrTrustStudentVO vo = new NtrTrustStudentVO();
					vo.setAddressId((Long)studentAddress.getAddressForStudent().getAddressId());
					vo.setStateStr(studentAddress.getAddressForStudent().getState() !=null ? studentAddress.getAddressForStudent().getState().toString() : "");
					vo.setDistrictStr(studentAddress.getAddressForStudent().getDistrict() !=null ? studentAddress.getAddressForStudent().getDistrict().toString() :"");
					vo.setConstituencyStr(studentAddress.getAddressForStudent().getConstituency() !=null ? studentAddress.getAddressForStudent().getConstituency().toString() : "");
					vo.setTehsilStr(studentAddress.getAddressForStudent().getTehsil()!=null ? studentAddress.getAddressForStudent().getTehsil().toString():"");
					vo.setLocalElectionBodyStr(studentAddress.getAddressForStudent().getLocalElectionBody() !=null ? studentAddress.getAddressForStudent().getLocalElectionBody().toString():"");
					vo.setPanchayatStr(studentAddress.getAddressForStudent().getPanchayat() !=null ? studentAddress.getAddressForStudent().getPanchayat().toString() :"");
					vo.setWardStr(studentAddress.getAddressForStudent().getWard() !=null ? studentAddress.getAddressForStudent().getWard().toString():"");
					vo.setLocationStr(studentAddress.getAddressForStudent().getLocation() !=null ? studentAddress.getAddressForStudent().getLocation().toString():"");
					vo.setHouseNoStr(studentAddress.getAddressForStudent().getHouseNo() !=null ? studentAddress.getAddressForStudent().getHouseNo().toString() : "");
					vo.setStreetStr(studentAddress.getAddressForStudent().getStreet() !=null ? studentAddress.getAddressForStudent().getStreet().toString() : "");
					vo.setPincodeLng(studentAddress.getAddressForStudent().getPinCode() !=null ? (Long)studentAddress.getAddressForStudent().getPinCode() : 0l);
					
					addressList.add(vo);
				}
				
				return addressList;
			}
			
		}catch (Exception e) {
			LOG.error("Exception Occured in getStudentSpecificAddressDetails() method, Exception - ",e);
		}
		return addressList;
	}
	public List<NtrTrustStudentVO> getRecomendationDetailsOfStudent(Long studentId){
		List<NtrTrustStudentVO> referalList = new ArrayList<NtrTrustStudentVO>();
		try{
			//0.studentRecomendationId,1.personName,2.referalDesignationId,3.designation,4.contactNo,5.tdpCadreId,6.membershipNo 
			List<Object[]> referalDetails = studentRecomendationDAO.getRecomendationDetailsOfStudent(studentId);
			
			if(referalDetails !=null && referalDetails.size()>0){
				for(Object[] refer:referalDetails){
					NtrTrustStudentVO vo  = new NtrTrustStudentVO();
					
					vo.setId((Long)refer[0]);//recomendationId
					vo.setName(refer[1] !=null ? refer[1].toString() :"");//personName
					vo.setDesignationId(refer[2] !=null ? (Long)refer[2]:0l);//referalDesignationId
					vo.setDesignation(refer[3] !=null ? refer[3].toString() :"");
					vo.setPhoneNo(refer[4] !=null ? refer[4].toString() : "" );//contactNo
					vo.setTdpCadreId(refer[5] !=null ? (Long)refer[5] :0l);
					vo.setMembershipNo(refer[6] !=null ? (Long)refer[6]:0l);
					
					referalList.add(vo);
				}
			}
		}catch (Exception e) {
			e.printStackTrace();
		}
		return referalList;
	}
     
	//IVR
	public IVRResponseVO getIVRSummaryByTdpCadreId(Long tdpCadreId){
		IVRResponseVO ivrResponseVO =new IVRResponseVO();
		try{
			
			List<Object[]> summaryList=communicationMediaResponseDAO.getIVRSummaryByTdpCadreId(tdpCadreId);
			if(summaryList!=null && summaryList.size()>0){
				for(Object[] obj:summaryList){
					
					if(ivrResponseVO.getTotalCount()==null){
						ivrResponseVO.setTotalCount(0l);
					}
					ivrResponseVO.setTotalCount(ivrResponseVO.getTotalCount()+1);
					
					if(obj[2]!=null || obj[3]!=null){//answered
						
						if(ivrResponseVO.getAnsweredcount()==null){
							ivrResponseVO.setAnsweredcount(0l);
						}
						ivrResponseVO.setAnsweredcount(ivrResponseVO.getAnsweredcount()+1);
						
					}else{//unanswerd
						
						if(ivrResponseVO.getUnAnsweredCount()==null){
							ivrResponseVO.setUnAnsweredCount(0l);
						}
						ivrResponseVO.setUnAnsweredCount(ivrResponseVO.getUnAnsweredCount()+1);
					}
					
				}
			}
			
		}catch(Exception e){
			LOG.error("Exception Occured in getIVRSummaryByTdpCadreId() method, Exception - ",e);
		}
		return ivrResponseVO;
	}
	public List<IVRResponseVO> getTotalIVRDetailsByTdpCadreId(Long tdpCadreId,int startIndex,int maxIndex){
		
		  List<IVRResponseVO> ivrResponseVOList =new ArrayList<IVRResponseVO>();
  	try{
  		SimpleDateFormat sdf=new SimpleDateFormat("dd MMM yyyy");
			List<Object[]> list=communicationMediaResponseDAO.getIVRDetailsByTdpCadreId1(tdpCadreId,startIndex,maxIndex);
			if(list!=null && list.size()>0){
				for(Object[] obj:list){
					IVRResponseVO ivrvo=new IVRResponseVO();
					ivrvo.setTdpCadreName(obj[2]!=null?obj[2].toString():"");
					ivrvo.setName(obj[3]!=null?obj[3].toString():"");
					ivrvo.setDateString(obj[4]!=null?sdf.format((Date)obj[4]):"");
					ivrvo.setQuestion(obj[5]!=null?obj[5].toString():"");
					
					if(obj[6]!=null){
						ivrvo.setIsAnswered(true);
						ivrvo.setOptionId((Long)obj[6]);
						ivrvo.setOption(obj[7]!=null?obj[7].toString():"");
					}else if(obj[8]!=null){
						ivrvo.setIsAnswered(true);
						ivrvo.setDescription(obj[8].toString());
					}else{
						ivrvo.setIsAnswered(false);
					}
					
					ivrvo.setRoundId((Long) (obj[9] != null ? obj[9]:0l));
					
					ivrResponseVOList.add(ivrvo);
				}
			}
			if(startIndex==0){
				List<Object[]> totalCountslist=communicationMediaResponseDAO.getIVRDetailsByTdpCadreId1(tdpCadreId,startIndex,0);
				if(ivrResponseVOList!=null && ivrResponseVOList.size()>0  && totalCountslist!=null && totalCountslist.size()>0){
					ivrResponseVOList.get(0).setTotalCount(Long.valueOf(totalCountslist.size()));
				}
			 }
		}catch (Exception e){
			LOG.error("Exception Occured in getIVRSummaryByTdpCadreId() method, Exception - ",e);
		}
  	return ivrResponseVOList;
  }
	// changes
	
	public CadreOverviewVO getVoterDetailsByVoterIdCardNum(String voterCardNo,String familyVoterCardNo,String memberType,Long memberTypeId)
	{
		CadreOverviewVO returnVO = new CadreOverviewVO();
		try {
			List<Object[]> voterDetails = null;
			Long tdpCadreId = 0l;
			
			if(voterCardNo != null && !voterCardNo.isEmpty())
			{
				List checkVoter = tdpCadreDAO.checkUnionMemberExists(voterCardNo,memberTypeId);
				if(checkVoter != null && checkVoter.size() > 0)
				{
					returnVO.setVoterExists(true);
					returnVO.setMessage("success");
					return returnVO;
				}
				else
				{
					returnVO.setVoterExists(false);
					voterDetails = boothPublicationVoterDAO.getConstyPublicationIdByVoterIdPublicationId(voterCardNo,IConstants.VOTER_PUBLICATION_ID);
				}
			}
			else 
			{
				voterDetails = boothPublicationVoterDAO.getConstyPublicationIdByVoterIdPublicationId(familyVoterCardNo,IConstants.VOTER_PUBLICATION_ID);
				
				if(voterDetails == null || voterDetails.size() == 0)
				{
					returnVO.setMessage("fail");
					return returnVO;
				}
				if(voterDetails != null && voterDetails.size()>0)
				{
					/*for (Object[] voter : voterDetails) {
						Long voterId = voter[3] != null ? Long.valueOf(voter[3].toString()):0L;
						returnVO.setFamilyVoterId(voterId.toString());
					}*/
					returnVO.setMessage("success");
				}
				return returnVO;
			}
				if(voterDetails == null || voterDetails.size() == 0)
				{
					returnVO.setMessage("fail");
					return returnVO;
				}
				if(voterDetails != null && voterDetails.size()>0)
				{
					returnVO.setMessage("success");
					
					for (Object[] voter : voterDetails) {
						//Long publicationDateId = voter[2] != null ? Long.valueOf(voter[2].toString()):0L;
						
							Long voterId = voter[3] != null ? Long.valueOf(voter[3].toString()):0L;
							List<TdpCadre> tdpCadreList = tdpCadreDAO.getVoterByVoterId(voterId,memberTypeId);
							if(tdpCadreList != null && tdpCadreList.size()>0)
							{
								TdpCadre tdpCadre =  (TdpCadre) tdpCadreList.get(0);
								
								if(tdpCadre != null)
								{
									tdpCadreId = tdpCadre.getTdpCadreId();
								}
							}
							break;
					}
				}
			
			
			if(tdpCadreId != null && tdpCadreId.longValue()>0L)
			{
				CadreCommitteeMemberVO cadreVO = cadreFormalDetailedInformation(tdpCadreId,IConstants.UNIONS_REGISTRATION_YEAR,memberTypeId);
				if(cadreVO != null )
				{
					returnVO.setAge(cadreVO.getAge());
					returnVO.setGender(cadreVO.getGender());
					returnVO.setAreaType(cadreVO.getAreaType());
					returnVO.setBoothId(cadreVO.getBoothId());
					returnVO.setRelationType(cadreVO.getRelativeType());
					returnVO.setRelativeName(cadreVO.getRelativeName());
					returnVO.setCadreId(cadreVO.getCadreId());
					returnVO.setCandidateId(cadreVO.getCandidate());
					returnVO.setConstituencyId(cadreVO.getConstituencyId());
					returnVO.setCasteName(cadreVO.getCasteName());
					returnVO.setConstituencyName(cadreVO.getConstituencyName());
					returnVO.setDob(cadreVO.getDateOfBirth());
					returnVO.setDistrictId(cadreVO.getDistrictId());
					returnVO.setDistrictName(cadreVO.getDistrictName());
					returnVO.setEmail(cadreVO.getEmailId());
					returnVO.setEnrollmentYears(cadreVO.getEnrollmentYears());
					returnVO.setHouseNo(cadreVO.getHouseNo());
					returnVO.setImagePath(cadreVO.getImagePath());
					returnVO.setLocalElectionBodyId(cadreVO.getLocalElectionBody()!=null?cadreVO.getLocalElectionBody().toString():"");
					returnVO.setMembershipNo(Long.valueOf(cadreVO.getMembershipNo()));
					returnVO.setMobileNo(cadreVO.getMobileNo());
					returnVO.setCandidateName(cadreVO.getName());
					returnVO.setOccupation(cadreVO.getOccupation());
					returnVO.setParliamentId(cadreVO.getpConstituencyId());
					returnVO.setParlimentName(cadreVO.getpConstituencyName());
					returnVO.setPanchayatId(cadreVO.getPanchayatId());
					returnVO.setPanchayatName(cadreVO.getPanchayatName());
					returnVO.setPartNo(Long.valueOf(cadreVO.getPartNo()));
					returnVO.setPartyPosition(cadreVO.getPartyPosition());
					returnVO.setRegisteredAt(cadreVO.getRegisteredOn());
					returnVO.setRegisteredTime(cadreVO.getRegisteredTime());
					returnVO.setRepresentativeType(cadreVO.getRepresentativeType());
					returnVO.setStateId(cadreVO.getStateId());
					returnVO.setStateName(cadreVO.getStateName());
					returnVO.setTehsilId(cadreVO.getTehsilId());
					returnVO.setTehsilName(cadreVO.getTehsilName());
					returnVO.setVoterId(cadreVO.getVoterId() != null && !cadreVO.getVoterId().trim().isEmpty()?Long.valueOf(cadreVO.getVoterId()):0L);
					returnVO.setVoterCardNo(cadreVO.getVoterIdCardNo());
					returnVO.setQualification(cadreVO.getQualification());
					returnVO.setDeletedStatus(cadreVO.getDeletedStatus());
					returnVO.setDeletedReason(cadreVO.getDeletedreason());
					
					if(memberType == null || !memberType.trim().equalsIgnoreCase("affiliated")){
						List<TdpCadreFamilyDetailsVO> familyVOList = getCadreFamilyDetails(tdpCadreId);
						if(familyVOList != null && familyVOList.size()>0)
						{
							returnVO.getFamilyMembersList().addAll(familyVOList);
						}
					}
				}
			}
			else
			{
				if(voterDetails != null && voterDetails.size()>0)
				{
					for (Object[] voter : voterDetails) {
						//Long publicationDateId = voter[2] != null ? Long.valueOf(voter[2].toString()):0L;
						
							Long constituencyId = voter[0] != null ? Long.valueOf(voter[0].toString()):0L;
							Long boothId = voter[1] != null ? Long.valueOf(voter[1].toString()):0L;
							Long districtId = voter[7] != null ? Long.valueOf(voter[7].toString()):0L;
							String districtName= voter[12] != null ? voter[12].toString():"";
							String constituencyName= voter[11] != null ? voter[11].toString():"";
							
							Long voterId = voter[3] != null ? Long.valueOf(voter[3].toString()):0L;
							List<Long> voterIdsList = new ArrayList<Long>(0);
							voterIdsList.add(voterId);
							
							String voterName =  voter[4] != null ? voter[4].toString():"";
							String age =  voter[5] != null ? voter[5].toString():"";
							String gender =  voter[6] != null ? voter[6].toString():"";
							String relativeName =  voter[8] != null ? voter[8].toString():"";
							String relativeType =  voter[10] != null ? voter[10].toString():"";
							String hNo = voter[9] != null ? voter[9].toString():"";;
							String partNo= voter[13] != null ? voter[13].toString():"";
							Long localElectionBodyId = voter[22] != null && voter[22].toString().trim().length()>0 ? Long.valueOf(voter[22].toString()):0L;
							String localElectionBodyName = voter[21] != null && voter[21].toString().trim().length()>0 ? voter[21].toString():"";
							Long panchayatId = voter[15] != null ? Long.valueOf(voter[15].toString()):0L;
							String panchayatName= voter[16] != null ? voter[16].toString():"";
							Long tehsilId = voter[17] != null ? Long.valueOf(voter[17].toString()):0L;
							String tehsilName= voter[18] != null ? voter[18].toString():"";
							Long stateId = voter[19] != null ? Long.valueOf(voter[19].toString()):0L;
							String stateName= voter[20] != null ? voter[20].toString():"";
							
							List<Object[]> parliamentList = delimitationConstituencyAssemblyDetailsDAO.findLatestParliamentForAssembly(constituencyId);
							if(parliamentList != null && parliamentList.size()>0)
							{
								Object[] parliament = parliamentList.get(0);
								if(parliament == null)
								{
									if(parliamentList.size()>1)
										parliament = parliamentList.get(1);
								}
								else
								{
									Long parliamentId = parliament[0] != null ? Long.valueOf(parliament[0].toString()):0L;
									String parliamentName= parliament[1] != null ? parliament[1].toString():"";
									
									returnVO.setParliamentId(parliamentId);
									returnVO.setParlimentName(parliamentName);
								}
							}
							
							
							//String filePath = "voter_images/"+constituencyId+"/Part"+partNo.trim()+"/"+voterCardNo+".jpg";
							String filePath = getVoterImageUrlByVoterId(voterId);
							
							returnVO.setCandidateName(voterName);									
							returnVO.setAge(age);
							returnVO.setGender(gender);
							returnVO.setRelativeName(relativeName);
							returnVO.setRelationType(relativeType);
							returnVO.setConstituencyId(constituencyId);									
							returnVO.setConstituencyName(constituencyName);
							returnVO.setBoothId(boothId);	
							returnVO.setPartNo(Long.valueOf(partNo));
							
							List<Object[]> voterCasteInfo = userVoterDetailsDAO.getCasteByVoterId(1L,voterIdsList);
							if(voterCasteInfo != null && voterCasteInfo.size()>0)
							{
								Object[] casteInfo = voterCasteInfo.get(0);
								String casteName = casteInfo[1] != null ? casteInfo[1].toString().trim():"";
								returnVO.setCasteName(casteName);
							}
															
							returnVO.setDistrictId(districtId);
							returnVO.setDistrictName(districtName);
							returnVO.setHouseNo(hNo);									
							returnVO.setImagePath("http://mytdp.com/"+IConstants.VOTER_IMG_FOLDER_PATH+"/"+filePath);
							returnVO.setPanchayatId(panchayatId);
							returnVO.setPanchayatName(panchayatName);
							returnVO.setTehsilId(tehsilId);
							returnVO.setTehsilName(tehsilName);
							returnVO.setStateId(stateId);
							returnVO.setStateName(stateName);
							if(localElectionBodyId != null && localElectionBodyId.longValue()>0L){
								returnVO.setLocalElectionBodyId(localElectionBodyId.toString());
								returnVO.setLocalElectionBodyName(localElectionBodyName.toString());
								
								Long wardId = voter[23] != null && voter[23].toString().trim().length()>0 ? Long.valueOf(voter[23].toString()):0L;
								if(wardId != null && wardId.longValue()>0)
								{
									String wardName = voter[24] != null && voter[24].toString().trim().length()>0 ? voter[24].toString():"";
									returnVO.setWardId(wardId.toString());
									returnVO.setWardName(wardName);
								}
							}
							List<Object[]> mobileNosList = mobileNumbersDAO.getVotersMobileNumberDetails(voterIdsList);
							
							if(mobileNosList != null && mobileNosList.size()>0)
							{
								String mobileNumber = "";
								for (Object[] mobile : mobileNosList) {
									String mobileNo = mobile[1] != null ? mobile[1].toString().trim():"";
									if(!mobileNo.isEmpty())
									{
										mobileNumber = mobileNumber+","+mobileNo;
									}
								}
								returnVO.setMobileNo(mobileNumber);
							}
															
							returnVO.setVoterId(voterId);
							returnVO.setVoterCardNo(voterCardNo);
							if(memberType == null || !memberType.trim().equalsIgnoreCase("affiliated")){
								
									List<Object[]> familyInfo =boothPublicationVoterDAO.getFamilyDetaislByHouseNoAndBoothId(boothId,hNo);
									List<TdpCadreFamilyDetailsVO> familyVOList = new ArrayList<TdpCadreFamilyDetailsVO>(0);
									if(familyInfo != null && familyInfo.size()>0)
									{
										List<String> voterIdCardNoList = new ArrayList<String>(0);
										
										for (Object[] family : familyInfo) 
										{
											Long familyVoterID = family[0] != null ? Long.valueOf(family[0].toString().trim()):0L;
											
											if( familyVoterID.longValue() != voterId.longValue())
											{
												TdpCadreFamilyDetailsVO fmilyVO = new TdpCadreFamilyDetailsVO();
												fmilyVO.setVoterId(family[0] != null ? Long.valueOf(family[0].toString().trim()):0L);
												fmilyVO.setName(family[1] != null ? family[1].toString():"");
												fmilyVO.setGender(family[4] != null ? family[4].toString():"");
												fmilyVO.setAge(family[5] != null ? Long.valueOf(family[5].toString()):0L);								
												fmilyVO.setVotercardNo(family[6] != null ? family[6].toString():"");
												fmilyVO.setRelation(family[3] != null ? family[3].toString():"");
												fmilyVO.setRelativeName(family[7] != null ? family[7].toString():null);
												voterIdCardNoList.add(fmilyVO.getVotercardNo());
												familyVOList.add(fmilyVO);
												
											}								
										}
										
										if(voterIdCardNoList != null && voterIdCardNoList.size() > 0)
										{
											
											List<Object[]> cadreMembersInFamilyList = tdpCadreDAO.checkVoterCardNosCadreNosOrNot(voterIdCardNoList);
											List<Long> tdpCadreIDsList = new ArrayList<Long>(0);
											if(cadreMembersInFamilyList != null && cadreMembersInFamilyList.size() > 0)
											{
												for(Object[] params: cadreMembersInFamilyList)
												{
													if(params[0] != null)
													{
														TdpCadreFamilyDetailsVO VO =getMatchedTdpCadreFamilyDetailsVO(familyVOList, params[0].toString());
													    if(VO != null){
													    	VO.setTdpCadreId(params[1] != null?Long.parseLong(params[1].toString()):0l);
													    	VO.setMembershipNo(params[2] != null?params[2].toString():"");
													    	
													    	if(params[4] !=null){
													    		VO.setDeletedStatus(params[4].toString());
																
																if(VO.getDeletedStatus().equalsIgnoreCase("MD")){
																	VO.setDeletedReason(params[6] !=null ? params[6].toString():"");
																}
																else{
																	VO.setDeletedReason("");
																}
															}
													    	
													    	tdpCadreIDsList.add(VO.getTdpCadreId());
													    }
													}
												}
											}
										}
									}
							}
							//returnVO.getFamilyMembersList().addAll(familyVOList);
							List<RegisteredMembershipCountVO> resultList = new ArrayList<RegisteredMembershipCountVO>();
							UserAddress userAddress = new UserAddress();
							if(returnVO.getBoothId() != null && returnVO.getBoothId() > 0)
							userAddress.setBooth(boothDAO.get(returnVO.getBoothId()));
							if(returnVO.getPanchayatId() != null && returnVO.getPanchayatId() > 0)
							userAddress.setPanchayat(panchayatDAO.get(returnVO.getPanchayatId()));
							if(returnVO.getTehsilId() != null && returnVO.getTehsilId() > 0)
							userAddress.setTehsil(tehsilDAO.get(returnVO.getTehsilId()));
							
							if(returnVO.getLocalElectionBodyId() != null && Long.valueOf(returnVO.getLocalElectionBodyId())>0L)
								userAddress.setLocalElectionBody(localElectionBodyDAO.get(Long.valueOf(returnVO.getLocalElectionBodyId())));
							
							userAddress.setConstituency(constituencyDAO.get(returnVO.getConstituencyId()));
							userAddress.setDistrict(districtDAO.get(returnVO.getDistrictId()));
							userAddress.setState(stateDAO.get(1L));
				
							
							break; // iteration only once 
							
						
					}
				}
				
			}
			
		} catch (Exception e) {
			LOG.error("Exception occured in getTdpcadreDetailsByTdpCadreId() Method - ",e);
		}
		
		return returnVO;
	}
	
	public List<IvrOptionsVO> getIvrSurveyInfoByTdpCadreId(Long tdpCadreId,Long entityTypeId,String searchType){
		List<IvrOptionsVO> finalVoList = new ArrayList<IvrOptionsVO>();
		
		try {
			
			List<Long> surveyIds = new ArrayList<Long>();
			List<Long> entityValuesList = new ArrayList<Long>(0);
			Map<Long,IvrOptionsVO> surveyMap = new LinkedHashMap<Long, IvrOptionsVO>();
			
			Long respondentId = ivrRespondentCadreDAO.getRespondentIdByTdpCadreId(tdpCadreId);
			
			//0.surveyId,1.surveyName,2.entityValue
			List<Object[]> surveyList = ivrSurveyEntityDAO.getSurveyListByEntityType(entityTypeId);
			if(surveyList != null && surveyList.size() > 0){
				for (Object[] obj : surveyList) {
					IvrOptionsVO vo = new IvrOptionsVO();
					
					Long surveyId = Long.valueOf(obj[0] != null ? obj[0].toString():"0");
					Long entityValue = Long.valueOf(obj[2] != null ? obj[2].toString():"0");
					
					vo.setSurveyId(surveyId);
					vo.setEventId(entityValue);
					
					surveyIds.add(surveyId);
					if(entityValue !=null && entityValue>0){
						entityValuesList.add(entityValue);
					}
					
					surveyMap.put(surveyId, vo);
				}
			}
			
			if(entityTypeId.longValue() == 4l){
				finalVoList = getSurveyAnswerDetailsForPartyMeetings(surveyMap, surveyIds, respondentId,searchType);
			}
			else if(entityTypeId.longValue()==2l){
				finalVoList = getSurveyAnswerDetailsForActivity(surveyMap,surveyIds,respondentId,searchType,entityValuesList);
			}
			else if(entityTypeId.longValue() == 3l){
				finalVoList = getSurveyAnswerDetailsForTrainingCamps(surveyMap, surveyIds, respondentId, searchType, tdpCadreId);
			}
			else if(entityTypeId.longValue() == 5l){
				finalVoList = getSurveyAnswerDetailsForSpecialSurveys(surveyMap, surveyIds, respondentId, searchType);
			}
			
		} catch (Exception e) {
			LOG.error("Exception occured in getIvrSurveyInfoByTdpCadreId() Method - ",e);
		}
		return finalVoList;
	}
	
	public List<IvrOptionsVO> getSurveyAnswerDetailsForSpecialSurveys(Map<Long,IvrOptionsVO> surveyMap,List<Long> surveyIds,Long respondentId,String searchType){
			List<IvrOptionsVO> returnList = new ArrayList<IvrOptionsVO>();
		
		try {
			
			//0.ivrSurveyId,1.surveyName,2.ivrSurveyRoundId,3.roundName,4.ivrSurveyQuestionId,5.ivrQuestionId,6.question,7.ivrOptionId,8.option
			List<Object[]> list = new ArrayList<Object[]>();
			
			if(searchType.trim().equalsIgnoreCase("total")){
				list = ivrSurveyAnswerDAO.getTotalIvrSurveyAnswerInfoDetailsBySurveyListAndRespondentId(surveyIds, respondentId);
			}
			else if(searchType.trim().equalsIgnoreCase("answered")){
				list = ivrSurveyAnswerDAO.getIvrSurveyAnswerInfoDetailsBySurveyListAndRespondentId(surveyIds, respondentId);
			}
			else if(searchType.trim().equalsIgnoreCase("unanswered")){
				list = ivrSurveyAnswerDAO.getUnAnsweredIvrSurveyAnswerInfoDetailsBySurveyListAndRespondentId(surveyIds, respondentId);
			}
			
			Map<Long,List<IvrOptionsVO>> surveyListMap = new LinkedHashMap<Long, List<IvrOptionsVO>>(0);
					
			if(list != null && list.size() > 0){
				for (Object[] obj : list) {
					Long surveyId = Long.valueOf(obj[0] != null ? obj[0].toString():"0");
					IvrOptionsVO vo = surveyMap.get(surveyId);
					List<IvrOptionsVO> ivrSurveysList = new ArrayList<IvrOptionsVO>(0);
					if(vo != null)
					{
						IvrOptionsVO surveyVO = new IvrOptionsVO();
						if(surveyListMap.get(vo.getSurveyId()) != null){
							ivrSurveysList = surveyListMap.get(vo.getSurveyId());
						}
						surveyVO.setSurveyId(vo.getSurveyId());
						surveyVO.setSurveyName(obj[1] != null ? obj[1].toString():"");
						surveyVO.setEventId(vo.getEventId());
						Object[] surveyDetails = ivrSurveyServiceDAO.getSurveyServiceBySurveyId(vo.getEventId());
						if(surveyDetails != null){
							surveyVO.setEventName(surveyDetails[1] != null ? surveyDetails[1].toString():"");
							surveyVO.setDateStr(surveyDetails[2] != null ? surveyDetails[2].toString():"");
						}
						//surveyVO.setEventName(meetingName);
						surveyVO.setRoundId(Long.valueOf(obj[2] != null ? obj[2].toString():"0"));
						surveyVO.setRound(obj[3] != null ? obj[3].toString():"0");
						surveyVO.setQuestionId(Long.valueOf(obj[5] != null ? obj[5].toString():"0"));
						surveyVO.setQuestion(obj[6] != null ? obj[6].toString():"0");
						surveyVO.setOptionId(Long.valueOf(obj[7] != null ? obj[7].toString():"0"));
						surveyVO.setOption(obj[8] != null ? obj[8].toString():"0");
						
						ivrSurveysList.add(surveyVO);
						surveyListMap.put(vo.getSurveyId(), ivrSurveysList);
					}
				}
			}
			
			for (Long surveyId : surveyListMap.keySet()) {
				returnList.addAll(surveyListMap.get(surveyId));
			}
			//returnList = (List<IvrOptionsVO>)surveyListMap.values();
			
		} catch (Exception e) {
			LOG.error("Exception occured in getSurveyAnswerDetailsForSpecialSurveys() Method - ",e);
		}
		return returnList;
	}
	
	public List<IvrOptionsVO> getSurveyAnswerDetailsForTrainingCamps(Map<Long,IvrOptionsVO> surveyMap,List<Long> surveyIds,Long respondentId,String searchType,Long tdpCadreId){
		List<IvrOptionsVO> returnList = new ArrayList<IvrOptionsVO>();
		
		try {
			
			List<Object[]> list = new ArrayList<Object[]>();
			
			if(searchType.trim().equalsIgnoreCase("total")){
				list = ivrSurveyAnswerDAO.getTotalIvrSurveyAnswerInfoDetailsBySurveyListAndRespondentId(surveyIds, respondentId);
			}
			else if(searchType.trim().equalsIgnoreCase("answered")){
				list = ivrSurveyAnswerDAO.getIvrSurveyAnswerInfoDetailsBySurveyListAndRespondentId(surveyIds, respondentId);
			}
			else if(searchType.trim().equalsIgnoreCase("unanswered")){
				list = ivrSurveyAnswerDAO.getUnAnsweredIvrSurveyAnswerInfoDetailsBySurveyListAndRespondentId(surveyIds, respondentId);
			}
			
			Map<Long,List<IvrOptionsVO>> surveyListMap = new LinkedHashMap<Long, List<IvrOptionsVO>>(0);
					
			if(list != null && list.size() > 0){
				for (Object[] obj : list) {
					Long surveyId = Long.valueOf(obj[0] != null ? obj[0].toString():"0");
					IvrOptionsVO vo = surveyMap.get(surveyId);
					List<IvrOptionsVO> ivrSurveysList = new ArrayList<IvrOptionsVO>(0);
					if(vo != null)
					{
						IvrOptionsVO surveyVO = new IvrOptionsVO();
						if(surveyListMap.get(vo.getSurveyId()) != null){
							ivrSurveysList = surveyListMap.get(vo.getSurveyId());
						}
						surveyVO.setSurveyId(vo.getSurveyId());
						surveyVO.setSurveyName(obj[1] != null ? obj[1].toString():"");
						surveyVO.setEventId(vo.getEventId());
						List<Object[]> attendedBatchList = trainingCampAttendanceDAO.getAttendedBatchDetailsByTdpCadreId(tdpCadreId,vo.getEventId()); 
						if(attendedBatchList != null && attendedBatchList.size() > 0){
							Object[] batchDetails = attendedBatchList.get(0);
							if(batchDetails != null){
								surveyVO.setEventName(batchDetails[1] != null ? batchDetails[1].toString():"");
								surveyVO.setName(batchDetails[3] != null ? batchDetails[3].toString():"");
							}
						}
						surveyVO.setRoundId(Long.valueOf(obj[2] != null ? obj[2].toString():"0"));
						surveyVO.setRound(obj[3] != null ? obj[3].toString():"0");
						surveyVO.setQuestionId(Long.valueOf(obj[5] != null ? obj[5].toString():"0"));
						surveyVO.setQuestion(obj[6] != null ? obj[6].toString():"0");
						surveyVO.setOptionId(Long.valueOf(obj[7] != null ? obj[7].toString():"0"));
						surveyVO.setOption(obj[8] != null ? obj[8].toString():"0");
						
						ivrSurveysList.add(surveyVO);
						surveyListMap.put(vo.getSurveyId(), ivrSurveysList);
					}
				}
			}
			
			for (Long surveyId : surveyListMap.keySet()) {
				returnList.addAll(surveyListMap.get(surveyId));
			}
			
		} catch (Exception e) {
			LOG.error("Exception occured in getSurveyAnswerDetailsForTrainingCamps() Method - ",e);
		}
		return returnList;
	}
	
	public List<IvrOptionsVO> getSurveyAnswerDetailsForPartyMeetings(Map<Long,IvrOptionsVO> surveyMap,List<Long> surveyIds,Long respondentId,String searchType){
		List<IvrOptionsVO> returnList = new ArrayList<IvrOptionsVO>();
		
		try {
			
			//0.ivrSurveyId,1.surveyName,2.ivrSurveyRoundId,3.roundName,4.ivrSurveyQuestionId,5.ivrQuestionId,6.question,7.ivrOptionId,8.option
			List<Object[]> list = new ArrayList<Object[]>();
			
			if(searchType.trim().equalsIgnoreCase("total")){
				list = ivrSurveyAnswerDAO.getTotalIvrSurveyAnswerInfoDetailsBySurveyListAndRespondentId(surveyIds, respondentId);
			}
			else if(searchType.trim().equalsIgnoreCase("answered")){
				list = ivrSurveyAnswerDAO.getIvrSurveyAnswerInfoDetailsBySurveyListAndRespondentId(surveyIds, respondentId);
			}
			else if(searchType.trim().equalsIgnoreCase("unanswered")){
				list = ivrSurveyAnswerDAO.getUnAnsweredIvrSurveyAnswerInfoDetailsBySurveyListAndRespondentId(surveyIds, respondentId);
			}
			
			Map<Long,List<IvrOptionsVO>> surveyListMap = new LinkedHashMap<Long, List<IvrOptionsVO>>(0);
					
			if(list != null && list.size() > 0){
				for (Object[] obj : list) {
					Long surveyId = Long.valueOf(obj[0] != null ? obj[0].toString():"0");
					IvrOptionsVO vo = surveyMap.get(surveyId);
					List<IvrOptionsVO> ivrSurveysList = new ArrayList<IvrOptionsVO>(0);
					if(vo != null)
					{
						IvrOptionsVO surveyVO = new IvrOptionsVO();
						if(surveyListMap.get(vo.getSurveyId()) != null){
							ivrSurveysList = surveyListMap.get(vo.getSurveyId());
						}
						surveyVO.setSurveyId(vo.getSurveyId());
						surveyVO.setSurveyName(obj[1] != null ? obj[1].toString():"");
						surveyVO.setEventId(vo.getEventId());
						Object[] meetingDetails = partyMeetingDAO.getMeetingNameByMeetingId(vo.getEventId());
						if(meetingDetails != null){
							surveyVO.setEventName(meetingDetails[0] != null ? meetingDetails[0].toString():"");
							surveyVO.setName(meetingDetails[1] != null ? meetingDetails[1].toString():"");
							surveyVO.setDateStr(meetingDetails[2] != null ? meetingDetails[2].toString():"");
						}
						//surveyVO.setEventName(meetingName);
						surveyVO.setRoundId(Long.valueOf(obj[2] != null ? obj[2].toString():"0"));
						surveyVO.setRound(obj[3] != null ? obj[3].toString():"0");
						surveyVO.setQuestionId(Long.valueOf(obj[5] != null ? obj[5].toString():"0"));
						surveyVO.setQuestion(obj[6] != null ? obj[6].toString():"0");
						surveyVO.setOptionId(Long.valueOf(obj[7] != null ? obj[7].toString():"0"));
						surveyVO.setOption(obj[8] != null ? obj[8].toString():"0");
						
						ivrSurveysList.add(surveyVO);
						surveyListMap.put(vo.getSurveyId(), ivrSurveysList);
					}
				}
			}
			
			for (Long surveyId : surveyListMap.keySet()) {
				returnList.addAll(surveyListMap.get(surveyId));
			}
			//returnList = (List<IvrOptionsVO>)surveyListMap.values();
			
		} catch (Exception e) {
			LOG.error("Exception occured in getSurveyAnswerDetailsForPartyMeetings() Method - ",e);
		}
		return returnList;
	}
	
	public List<IvrOptionsVO> getSurveyAnswerDetailsForActivity(Map<Long,IvrOptionsVO> surveyMap,List<Long> surveyIds,Long respondentId,
			String searchType,List<Long> entityValuesList){
			 List<IvrOptionsVO> returnList = new ArrayList<IvrOptionsVO>();
			
		try{
			
			//0.activityId,1.activityName,2.activityLevelId,3.level,4.startDate,5.endDate,6.ivrSurveyId,7.surveyName,8.ivrSurveyRoundId,9.roundName,10.ivrSurveyQuestionId,
			//11.ivrQuestionId,12.question,13.ivrOptionId,14.option
			List<Object[]> listObjectArr = ivrSurveyEntityDAO.getSurveyAnswerDetailsForActivity(surveyIds,respondentId,entityValuesList,searchType);
			
			if(listObjectArr !=null && listObjectArr.size()>0){
				
				for (Object[] objects : listObjectArr) {
					
					IvrOptionsVO surveyVO = new IvrOptionsVO();					
					surveyVO.setId(objects[0] !=null ? (Long)objects[0]:0l);
					surveyVO.setName(objects[1] !=null ? objects[1].toString():"");
					surveyVO.setLevelId(objects[2] !=null ? (Long)objects[2]:0l);
					surveyVO.setLevelValue(objects[3] !=null ? objects[3].toString():"");
					surveyVO.setStartDate(objects[4] !=null ? objects[4].toString():"");
					surveyVO.setEndDate(objects[5] !=null ? objects[5].toString():"");					
					surveyVO.setSurveyId(objects[6] !=null ? (Long)objects[6]:0l);
					surveyVO.setSurveyName(objects[7] !=null ?objects[7].toString():"");					
					surveyVO.setRoundId(objects[8] != null ? (Long)objects[8]:0l);
					surveyVO.setRound(objects[9] != null ? objects[9].toString():"");					
					surveyVO.setQuestionId(objects[11] !=null ? (Long)objects[11]:0l);
					surveyVO.setQuestion(objects[12] !=null ?objects[12].toString():"");					
					surveyVO.setOptionId(objects[13] !=null ? (Long)objects[13]:0l);
					surveyVO.setOption(objects[14] != null ? objects[14].toString():"");
					
					returnList.add(surveyVO);
				}				
			}
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		return returnList;
	}
	
	public List<IvrOptionsVO> getTypeWiseIvrDetailsOFCadre(Long tdpCadreId){
		
		List<IvrOptionsVO> fnlList = new ArrayList<IvrOptionsVO>(0);
		
		try{
		
		List<Long> ivrRespondantIdsObj =  ivrRespondentCadreDAO.getIvrRespndantDetails(tdpCadreId);
		
		Long ivrRespondantId = 0l;
		if(ivrRespondantIdsObj !=null){
			ivrRespondantId = ivrRespondantIdsObj.get(0);
		}
		
		List<Long> surveyIds =new ArrayList<Long>(0);
		if(ivrRespondantId !=null && ivrRespondantId>0){
			 surveyIds =  ivrSurveyAnswerDAO.getIvrSurveyIdsByRespondantId(ivrRespondantId);
		}
		
		//Default Values For Entity Types For Assigned Surveys		
		
		Map<Long,IvrOptionsVO> optionsMap = new HashMap<Long, IvrOptionsVO>();
			Map<Long,Long> surveyCnt = new HashMap<Long, Long>();
		if(surveyIds !=null && surveyIds.size()>0){
			 List<Object[]> list = ivrSurveyEntityDAO.getSurveyCountforEntityType(surveyIds, ivrRespondantId);
			 if(list != null && list.size() > 0)
			 {
				 for(Object[] params : list)
				 {
					 Long cnt =0l;
					 if(surveyCnt.get((Long)params[0]) != null)
					  cnt = surveyCnt.get((Long)params[0]);
					 surveyCnt.put((Long)params[0], (Long)params[2] + cnt);
				 }
			 }
			List<Object[]> entityTypeObjResult =  ivrSurveyEntityDAO.getSurveyEntityTypeAndCountDetails(surveyIds,ivrRespondantId);			
			if(entityTypeObjResult !=null && entityTypeObjResult.size()>0){				
				for (Object[] entityType : entityTypeObjResult) {
					
					IvrOptionsVO vo = optionsMap.get((Long)entityType[0]);
					
					if(vo ==null){
						vo = new IvrOptionsVO();
						vo.setCount(surveyCnt.get((Long)entityType[0]));
						optionsMap.put((Long)entityType[0], vo);
					}
					
					vo.setId(commonMethodsUtilService.getLongValueForObject(entityType[0]));
					vo.setName(commonMethodsUtilService.getStringValueForObject(entityType[1]));
					
					Long responseTypeId = entityType[3] !=null ? (Long)entityType[3]:0l;
					Long count = entityType[2] !=null ? (Long)entityType[2]:0l;
					
					if(responseTypeId !=null && responseTypeId.equals(1l)){
						vo.setAnsweredCount(count);
					}else if(responseTypeId !=null && responseTypeId.equals(2l)){
						vo.setUnAnsweredCount(count);
					}else if(responseTypeId !=null && responseTypeId>0){
						vo.setOthersCount(vo.getOthersCount()+count);
					}
					
				}				
			}			
		}
		
		if(optionsMap !=null && optionsMap.size()>0){
			fnlList.addAll(optionsMap.values());
		}
		
	}catch (Exception e) {
		e.printStackTrace();
	}
	return fnlList;
		
 }
	
	public String getVoterImageUrlByVoterId(Long voterId)
	{
		try{
			return voterDAO.getVoterImagePathByVoterId(voterId);
		}catch(Exception e)
		{
			LOG.error("Exception occured in getVoterImageUrlByVoterId() Method ",e);
		}
		return null;
	}
	
	public List<LocationVO> getCheckCandidateCadreDtls(Long tdpCadreId){
		List<LocationVO> resultList=new ArrayList<LocationVO>();
		try{
			Long  candidateIds=tdpCadreCandidateDAO.getCheckCadreIdExits(tdpCadreId);//candidateId
			if(candidateIds == null) {
				return null;
			}else {
				List<Long> cadreCheckIds=publicRepresentativeDAO.getCandidateCadreDetils(candidateIds);//userIds list
				
				if(cadreCheckIds!=null && cadreCheckIds.size()>0){
					List<Object[]> userDetils=userAddressDAO.getUserAddressDetails(cadreCheckIds);
					if(userDetils!=null && userDetils.size()>0){
						
						for(Object[] param:userDetils){
							LocationVO location=new LocationVO();
							location.setDistrictId(param[0]!=null?(Long)param[0]:0l);//districtId
							location.setConstituencyId(param[1]!=null?(Long)param[1]:0l);//constituencyId
							location.setTehsilId(param[2]!=null?(Long)param[2]:0l);//tehsilId
							location.setVillageId(param[3]!=null?(Long)param[3]:0l);//villageId
							location.setParliamentConstituencyId(param[4]!=null?(Long)param[4]:0l);//parliamentConstiId
							resultList.add(location);
						}
					}
				}
			}
		}catch(Exception e){
			LOG.error("Exception occured in getCheckCandidateCadreDtls() Method ",e);
		}
		return resultList;
	}
	
	public List<IdNameVO> getTrainingCampAttendenceInfoInCadreLocation(Long boothId,Long panchayatId,Long wardId,Long mandalId,Long lebId,Long constituencyId,Long parliamentId,Long districtId){
		List<IdNameVO> voList = new ArrayList<IdNameVO>();
		try {
			Long panchayatInvitedCount = 0l;
			Long panTotalCount = 0l;
			Long panInvAtndCount = 0l;
			Long panNonInvtAtdCount = 0l;
			String panPercentage = "0.00";
			List<Long> pancCareIds = new ArrayList<Long>();
			List<Long> panTotalCadrIds = new ArrayList<Long>();
			if(panchayatId != null && panchayatId.longValue() > 0l){
				panchayatInvitedCount = trainingCampBatchAttendeeDAO.getInvitedCountByLocation(panchayatId, "panchayat");
				pancCareIds = trainingCampBatchAttendeeDAO.getInvitedCadreIdsByLocation(panchayatId, "panchayat");
				panTotalCount = trainingCampAttendanceDAO.getAttendedCountByLocation(panchayatId, "panchayat");
				panTotalCadrIds = trainingCampAttendanceDAO.getAttendedCadreIdsByLocation(panchayatId, "panchayat");
				if(panchayatInvitedCount.longValue() > 0l && panTotalCount.longValue() > 0l){
					for (Long long1 : pancCareIds) {
						if(panTotalCadrIds.contains(long1)){
							panInvAtndCount = panInvAtndCount + 1l;
						}
					}
					panNonInvtAtdCount = panTotalCount - panInvAtndCount;
					panPercentage = (new BigDecimal((panInvAtndCount * 100.0)/panchayatInvitedCount.doubleValue()).setScale(2, BigDecimal.ROUND_HALF_UP)).toString();
				}
				
				IdNameVO vo = new IdNameVO();
				vo.setId(panchayatId);
				vo.setName("panchayat");
				vo.setCount(panchayatInvitedCount);
				vo.setActualCount(panInvAtndCount);
				vo.setAvailableCount(panNonInvtAtdCount);
				vo.setPercentage(panPercentage);
				
				voList.add(vo);
			}
			
			Long wardInvitedCount = 0l;
			Long wardTotalcount = 0l;
			Long wardInvAtdCount = 0l;
			Long wardNonInvAtdCount = 0l;
			String wardPercentage = "0.00";
			List<Long> wardInvCadreIds = new ArrayList<Long>();
			List<Long> wardTotalCadreIds = new ArrayList<Long>();
			if(wardId != null && wardId.longValue() > 0l){
				wardInvitedCount = trainingCampBatchAttendeeDAO.getInvitedCountByLocation(wardId, "ward");
				wardInvCadreIds = trainingCampBatchAttendeeDAO.getInvitedCadreIdsByLocation(wardId, "ward");
				wardTotalcount = trainingCampAttendanceDAO.getAttendedCountByLocation(wardId, "ward");
				wardTotalCadreIds = trainingCampAttendanceDAO.getAttendedCadreIdsByLocation(wardId, "ward");
				if(wardInvitedCount.longValue() > 0l && wardTotalcount.longValue() > 0l){
					for (Long long1 : wardInvCadreIds) {
						if(wardTotalCadreIds.contains(long1)){
							wardInvAtdCount = wardInvAtdCount + 1l;
						}
					}
					wardNonInvAtdCount = wardTotalcount - wardInvAtdCount;
					wardPercentage = (new BigDecimal((wardInvAtdCount * 100.0)/wardInvitedCount.doubleValue()).setScale(2, BigDecimal.ROUND_HALF_UP)).toString();
				}
				
				IdNameVO vo = new IdNameVO();
				vo.setId(wardId);
				vo.setName("ward");
				vo.setCount(wardInvitedCount);
				vo.setActualCount(wardInvAtdCount);
				vo.setAvailableCount(wardNonInvAtdCount);
				vo.setPercentage(wardPercentage);
				
				voList.add(vo);
			}
			
			Long mandalInvitedCount = 0l;
			Long mandalTotalcount = 0l;
			Long mandalInvAtdCount = 0l;
			Long mandalNonInvAtdCount = 0l;
			String mandPercentage = "0.00";
			List<Long> mandalInvCadreIds = new ArrayList<Long>();
			List<Long> mandalTotalCadreIds = new ArrayList<Long>();
			if(mandalId != null && mandalId.longValue() > 0l){
				mandalInvitedCount = trainingCampBatchAttendeeDAO.getInvitedCountByLocation(mandalId, "mandal");
				mandalInvCadreIds = trainingCampBatchAttendeeDAO.getInvitedCadreIdsByLocation(mandalId, "mandal");
				mandalTotalcount = trainingCampAttendanceDAO.getAttendedCountByLocation(mandalId, "mandal");
				mandalTotalCadreIds = trainingCampAttendanceDAO.getAttendedCadreIdsByLocation(mandalId, "mandal");
				if(mandalInvitedCount.longValue() > 0l && mandalTotalcount.longValue() > 0l){
					for (Long long1 : mandalInvCadreIds) {
						if(mandalTotalCadreIds.contains(long1)){
							mandalInvAtdCount = mandalInvAtdCount + 1l;
						}
					}
					mandalNonInvAtdCount = mandalTotalcount - mandalInvAtdCount;
					mandPercentage = (new BigDecimal((mandalInvAtdCount * 100.0)/mandalInvitedCount.doubleValue()).setScale(2, BigDecimal.ROUND_HALF_UP)).toString();
				}
				
				IdNameVO vo = new IdNameVO();
				vo.setId(mandalId);
				vo.setName("mandal");
				vo.setCount(mandalInvitedCount);
				vo.setActualCount(mandalInvAtdCount);
				vo.setAvailableCount(mandalNonInvAtdCount);
				vo.setPercentage(mandPercentage);
				
				voList.add(vo);
			}
			
			Long lebInvitedCount = 0l;
			Long lebTotalcount = 0l;
			Long lebInvAtdCount = 0l;
			Long lebNonInvAtdCount = 0l;
			String lebPercentage = "0.00";
			List<Long> lebInvCadreIds = new ArrayList<Long>();
			List<Long> lebTotalCadreIds = new ArrayList<Long>();
			if(lebId != null && lebId.longValue() > 0l){
				lebInvitedCount = trainingCampBatchAttendeeDAO.getInvitedCountByLocation(lebId, "leb");
				lebInvCadreIds = trainingCampBatchAttendeeDAO.getInvitedCadreIdsByLocation(lebId, "leb");
				lebTotalcount = trainingCampAttendanceDAO.getAttendedCountByLocation(lebId, "leb");
				lebTotalCadreIds = trainingCampAttendanceDAO.getAttendedCadreIdsByLocation(lebId, "leb");
				if(lebInvitedCount.longValue() > 0l && lebTotalcount.longValue() > 0l){
					for (Long long1 : lebInvCadreIds) {
						if(lebTotalCadreIds.contains(long1)){
							lebInvAtdCount = lebInvAtdCount + 1l;
						}
					}
					lebNonInvAtdCount = lebTotalcount - lebInvAtdCount;
					lebPercentage = (new BigDecimal((lebInvAtdCount * 100.0)/lebInvitedCount.doubleValue()).setScale(2, BigDecimal.ROUND_HALF_UP)).toString();
				}
				
				IdNameVO vo = new IdNameVO();
				vo.setId(lebId);
				vo.setName("muncipality");
				vo.setCount(lebInvitedCount);
				vo.setActualCount(lebInvAtdCount);
				vo.setAvailableCount(lebNonInvAtdCount);
				vo.setPercentage(lebPercentage);
				
				voList.add(vo);
			}
			
			Long constInvitedCount = 0l;
			Long constTotalcount = 0l;
			Long constInvAtdCount = 0l;
			Long constNonInvAtdCount = 0l;
			String consPercentage = "0.00";
			List<Long> constInvCadreIds = new ArrayList<Long>();
			List<Long> constTotalCadreIds = new ArrayList<Long>();
			if(constituencyId != null && constituencyId.longValue() > 0l){
				constInvitedCount = trainingCampBatchAttendeeDAO.getInvitedCountByLocation(constituencyId, "constituency");
				constInvCadreIds = trainingCampBatchAttendeeDAO.getInvitedCadreIdsByLocation(constituencyId, "constituency");
				constTotalcount = trainingCampAttendanceDAO.getAttendedCountByLocation(constituencyId, "constituency");
				constTotalCadreIds = trainingCampAttendanceDAO.getAttendedCadreIdsByLocation(constituencyId, "constituency");
				if(constInvitedCount.longValue() > 0l && constTotalcount.longValue() > 0l){
					for (Long long1 : constInvCadreIds) {
						if(constTotalCadreIds.contains(long1)){
							constInvAtdCount = constInvAtdCount + 1l;
						}
					}
					constNonInvAtdCount = constTotalcount - constInvAtdCount;
					consPercentage = (new BigDecimal((constInvAtdCount * 100.0)/constInvitedCount.doubleValue()).setScale(2, BigDecimal.ROUND_HALF_UP)).toString();
				}
				
				IdNameVO vo = new IdNameVO();
				vo.setId(constituencyId);
				vo.setName("constituency");
				vo.setCount(constInvitedCount);
				vo.setActualCount(constInvAtdCount);
				vo.setAvailableCount(constNonInvAtdCount);
				vo.setPercentage(consPercentage);
				
				voList.add(vo);
			}
			
			Long parliInvitedCount = 0l;
			Long parliTotalcount = 0l;
			Long parliInvAtdCount = 0l;
			Long parliNonInvAtdCount = 0l;
			String parlPercentage = "0.00";
			List<Long> parliInvCadreIds = new ArrayList<Long>();
			List<Long> parliTotalCadreIds = new ArrayList<Long>();
			if(parliamentId != null && parliamentId.longValue() > 0l){
				parliInvitedCount = trainingCampBatchAttendeeDAO.getInvitedCountByLocation(parliamentId, "parliament");
				parliInvCadreIds = trainingCampBatchAttendeeDAO.getInvitedCadreIdsByLocation(parliamentId, "parliament");
				parliTotalcount = trainingCampAttendanceDAO.getAttendedCountByLocation(parliamentId, "parliament");
				parliTotalCadreIds = trainingCampAttendanceDAO.getAttendedCadreIdsByLocation(parliamentId, "parliament");
				if(parliInvitedCount.longValue() > 0l && parliTotalcount.longValue() > 0l){
					for (Long long1 : parliInvCadreIds) {
						if(parliTotalCadreIds.contains(long1)){
							parliInvAtdCount = parliInvAtdCount + 1l;
						}
					}
					parliNonInvAtdCount = parliTotalcount - parliInvAtdCount;
					parlPercentage = (new BigDecimal((parliInvAtdCount * 100.0)/parliInvitedCount.doubleValue()).setScale(2, BigDecimal.ROUND_HALF_UP)).toString();
				}
				
				IdNameVO vo = new IdNameVO();
				vo.setId(parliamentId);
				vo.setName("parliament");
				vo.setCount(parliInvitedCount);
				vo.setActualCount(parliInvAtdCount);
				vo.setAvailableCount(parliNonInvAtdCount);
				vo.setPercentage(parlPercentage);
				
				voList.add(vo);
			}
				
			Long distInvitedCount = 0l;
			Long distTotalcount = 0l;
			Long distInvAtdCount = 0l;
			Long distNonInvAtdCount = 0l;
			String distPercentage = "0.00";
			List<Long> distInvCadreIds = new ArrayList<Long>();
			List<Long> distTotalCadreIds = new ArrayList<Long>();
			if(districtId != null && districtId.longValue() > 0l){
				distInvitedCount = trainingCampBatchAttendeeDAO.getInvitedCountByLocation(districtId, "district");
				distInvCadreIds = trainingCampBatchAttendeeDAO.getInvitedCadreIdsByLocation(districtId, "district");
				distTotalcount = trainingCampAttendanceDAO.getAttendedCountByLocation(districtId, "district");
				distTotalCadreIds = trainingCampAttendanceDAO.getAttendedCadreIdsByLocation(districtId, "district");
				if(distInvitedCount.longValue() > 0l && distTotalcount.longValue() > 0l){
					for (Long long1 : distInvCadreIds) {
						if(distTotalCadreIds.contains(long1)){
							distInvAtdCount = distInvAtdCount + 1l;
						}
					}
					distNonInvAtdCount = distTotalcount - distInvAtdCount;
					distPercentage = (new BigDecimal((distInvAtdCount * 100.0)/distInvitedCount.doubleValue()).setScale(2, BigDecimal.ROUND_HALF_UP)).toString();
				}
				
				IdNameVO vo = new IdNameVO();
				vo.setId(districtId);
				vo.setName("district");
				vo.setCount(distInvitedCount);
				vo.setActualCount(distInvAtdCount);
				vo.setAvailableCount(distNonInvAtdCount);
				vo.setPercentage(distPercentage);
				
				voList.add(vo);
			}
				
			
		} catch (Exception e) {
			
			LOG.error("Exception occured in getTrainingCampAttendenceInfoInCadreIdLocation() Method ",e);
		}
		return voList;
	}

	/*public List<IdNameVO> getDeathsAndHospitalizationStatusWiseDetails(Long locationValue,String searchType,String issueType){
		List<IdNameVO> returnList = new ArrayList<IdNameVO>();
		
		try {
			
			List<Object[]> statusList = cadreHealthStatusDAO.getAllGrievanceInsuranceStatus();
			Map<Long,IdNameVO> statusMap=new LinkedHashMap<Long, IdNameVO>(0);
			if(statusList != null && statusList.size() > 0){
				for (Object[] obj : statusList) {
					IdNameVO vo = new IdNameVO();
					
					Long id = Long.valueOf(obj[0] != null ? obj[0].toString():"0");
					vo.setId(id);
					vo.setName(obj[1] != null ? obj[1].toString():"");
					
					statusMap.put(id, vo);
				}
			}
			List<Object[]> list = cadreHealthStatusDAO.getDeathsAndHospitalizationStatusWiseDetails(locationValue, searchType, issueType);
			if(list != null && list.size() > 0){
				for (Object[] obj : list) {
					Long id = Long.valueOf(obj[1] != null ? obj[1].toString():"0");
					Long count = Long.valueOf(obj[3] != null ? obj[3].toString():"0");
					
					IdNameVO vo = statusMap.get(id);
					vo.setCount(count);
				}
			}
			
			returnList.addAll(statusMap.values());
			
		} catch (Exception e) {
			LOG.error("Exception occured in getDeathsAndHospitalizationStatusWiseDetails() Method ",e);
		}
		return returnList;
	}*/
	/*
	 * 
	 * public GrievanceDetailsVO getDeathsAndHospitalizationStatusWiseDetailsInCadreLocation(Long panchayatId,Long mandalId,Long lebId,Long constituencyId,Long parliamentId,Long districtId){
		GrievanceDetailsVO grievanDetailsVO = new GrievanceDetailsVO();
		try {
			List<Object[]> statusList = cadreHealthStatusDAO.getAllGrievanceInsuranceStatus();
			Map<String,GrievanceDetailsVO> statusMap=new LinkedHashMap<String, GrievanceDetailsVO>(0);
			if(statusList != null && statusList.size() > 0){
				for (Object[] obj : statusList) {
					GrievanceDetailsVO vo = new GrievanceDetailsVO();
					
					Long id = Long.valueOf(obj[0] != null ? obj[0].toString():"0");
					String idStr = "D-"+id.toString();
					vo.setIdStr(idStr);
					vo.setName(obj[1] != null ? obj[1].toString():"");
					
					statusMap.put(idStr, vo);
				}
				for (Object[] obj : statusList) {
					GrievanceDetailsVO vo = new GrievanceDetailsVO();
					
					Long id = Long.valueOf(obj[0] != null ? obj[0].toString():"0");
					String idStr = "H-"+id.toString();
					vo.setIdStr(idStr);
					vo.setName(obj[1] != null ? obj[1].toString():"");
					
					statusMap.put(idStr, vo);
				}
			}
			
			Map<String,Map<String,GrievanceDetailsVO>> issueTypeMap = new LinkedHashMap<String, Map<String,GrievanceDetailsVO>>();
			issueTypeMap.put("Death", statusMap);
			issueTypeMap.put("Hospitalization", statusMap);
			
			Map<String,Map<String,Map<String,GrievanceDetailsVO>>> locationsMap = new LinkedHashMap<String, Map<String,Map<String,GrievanceDetailsVO>>>();
			if(panchayatId != null && panchayatId.longValue() > 0l){
				locationsMap.put("panchayat", issueTypeMap);
				
				//0.issue_type,1.grievance_insurance_status_id,2.status,3.COUNT.
				List<Object[]> list = cadreHealthStatusDAO.getDeathsAndHospitalizationStatusWiseDetails(panchayatId, "panchayat");
				if(list != null && list.size() > 0){
					for (Object[] obj : list) {
						String issueType = obj[0] != null ? obj[0].toString():"";
						Long statusId = Long.valueOf(obj[1] != null ? obj[1].toString():"0");
						Long count = Long.valueOf(obj[3] != null ? obj[3].toString():"0");
						
						Map<String,Map<String,GrievanceDetailsVO>> panIssTypeMap = locationsMap.get("panchayat");
						Map<String,GrievanceDetailsVO> panStaMap = panIssTypeMap.get(issueType);
						GrievanceDetailsVO panvo = null;
						if(issueType.equalsIgnoreCase("Death"))
							panvo = panStaMap.get("D-"+statusId.toString());
						else if(issueType.equalsIgnoreCase("Hospitalization"))
							panvo = panStaMap.get("H-"+statusId.toString());
						//panvo = panStaMap.get(statusId);
						panvo.setCount(count);
					}
				}
			}
				
			if(mandalId != null && mandalId.longValue() > 0l){
				locationsMap.put("mandal", issueTypeMap);
				
				//0.issue_type,1.grievance_insurance_status_id,2.status,3.COUNT.
				List<Object[]> list = cadreHealthStatusDAO.getDeathsAndHospitalizationStatusWiseDetails(mandalId, "mandal");
				if(list != null && list.size() > 0){
					for (Object[] obj : list) {
						String issueType = obj[0] != null ? obj[0].toString():"";
						Long statusId = Long.valueOf(obj[1] != null ? obj[1].toString():"0");
						Long count = Long.valueOf(obj[3] != null ? obj[3].toString():"0");
						
						Map<String,Map<String,GrievanceDetailsVO>> manIssTypeMap = locationsMap.get("mandal");
						Map<String,GrievanceDetailsVO> manStaMap = manIssTypeMap.get(issueType);
						GrievanceDetailsVO panvo = null;
						if(issueType.equalsIgnoreCase("Death"))
							panvo = manStaMap.get("D-"+statusId.toString());
						else if(issueType.equalsIgnoreCase("Hospitalization"))
							panvo = manStaMap.get("H-"+statusId.toString());
						//panvo = panStaMap.get(statusId);
						panvo.setCount(count);
					}
				}
			}
			else if(lebId != null && lebId.longValue() > 0l){
				locationsMap.put("muncipality", issueTypeMap);
				
				//0.issue_type,1.grievance_insurance_status_id,2.status,3.COUNT.
				List<Object[]> list = cadreHealthStatusDAO.getDeathsAndHospitalizationStatusWiseDetails(lebId, "leb");
				if(list != null && list.size() > 0){
					for (Object[] obj : list) {
						String issueType = obj[0] != null ? obj[0].toString():"";
						Long statusId = Long.valueOf(obj[1] != null ? obj[1].toString():"0");
						Long count = Long.valueOf(obj[3] != null ? obj[3].toString():"0");
						
						Map<String,Map<String,GrievanceDetailsVO>> munIssTypeMap = locationsMap.get("muncipality");
						Map<String,GrievanceDetailsVO> munStaMap = munIssTypeMap.get(issueType);
						GrievanceDetailsVO panvo = null;
						if(issueType.equalsIgnoreCase("Death"))
							panvo = munStaMap.get("D-"+statusId.toString());
						else if(issueType.equalsIgnoreCase("Hospitalization"))
							panvo = munStaMap.get("H-"+statusId.toString());
						//panvo = panStaMap.get(statusId);
						panvo.setCount(count);
					}
				}
			}
				
			if(constituencyId != null && constituencyId.longValue() > 0l){
				locationsMap.put("assembly", issueTypeMap);
				
				//0.issue_type,1.grievance_insurance_status_id,2.status,3.COUNT.
				List<Object[]> list = cadreHealthStatusDAO.getDeathsAndHospitalizationStatusWiseDetails(constituencyId, "constituency");
				if(list != null && list.size() > 0){
					for (Object[] obj : list) {
						String issueType = obj[0] != null ? obj[0].toString():"";
						Long statusId = Long.valueOf(obj[1] != null ? obj[1].toString():"0");
						Long count = Long.valueOf(obj[3] != null ? obj[3].toString():"0");
						
						Map<String,Map<String,GrievanceDetailsVO>> assIssTypeMap = locationsMap.get("assembly");
						Map<String,GrievanceDetailsVO> assStaMap = assIssTypeMap.get(issueType);
						GrievanceDetailsVO panvo = null;
						if(issueType.equalsIgnoreCase("Death"))
							panvo = assStaMap.get("D-"+statusId.toString());
						else if(issueType.equalsIgnoreCase("Hospitalization"))
							panvo = assStaMap.get("H-"+statusId.toString());
						//panvo = panStaMap.get(statusId);
						panvo.setCount(count);
					}
				}
			}
				
			if(parliamentId != null && parliamentId.longValue() > 0l){
				locationsMap.put("parliament", issueTypeMap);
				
				//0.issue_type,1.grievance_insurance_status_id,2.status,3.COUNT.
				List<Object[]> list = cadreHealthStatusDAO.getDeathsAndHospitalizationStatusWiseDetails(parliamentId, "parliament");
				if(list != null && list.size() > 0){
					for (Object[] obj : list) {
						String issueType = obj[0] != null ? obj[0].toString():"";
						Long statusId = Long.valueOf(obj[1] != null ? obj[1].toString():"0");
						Long count = Long.valueOf(obj[3] != null ? obj[3].toString():"0");
						
						Map<String,Map<String,GrievanceDetailsVO>> parIssTypeMap = locationsMap.get("parliament");
						Map<String,GrievanceDetailsVO> parStaMap = parIssTypeMap.get(issueType);
						GrievanceDetailsVO panvo = null;
						if(issueType.equalsIgnoreCase("Death"))
							panvo = parStaMap.get("D-"+statusId.toString());
						else if(issueType.equalsIgnoreCase("Hospitalization"))
							panvo = parStaMap.get("H-"+statusId.toString());
						//panvo = panStaMap.get(statusId);
						panvo.setCount(count);
					}
				}
			}
				
			if(districtId != null && districtId.longValue() > 0l){
				locationsMap.put("district", issueTypeMap);
				
				//0.issue_type,1.grievance_insurance_status_id,2.status,3.COUNT.
				List<Object[]> list = cadreHealthStatusDAO.getDeathsAndHospitalizationStatusWiseDetails(districtId, "district");
				if(list != null && list.size() > 0){
					for (Object[] obj : list) {
						String issueType = obj[0] != null ? obj[0].toString():"";
						Long statusId = Long.valueOf(obj[1] != null ? obj[1].toString():"0");
						Long count = Long.valueOf(obj[3] != null ? obj[3].toString():"0");
						
						Map<String,Map<String,GrievanceDetailsVO>> disIssTypeMap = locationsMap.get("district");
						Map<String,GrievanceDetailsVO> disStaMap = disIssTypeMap.get(issueType);
						GrievanceDetailsVO panvo = null;
						if(issueType.equalsIgnoreCase("Death"))
							panvo = disStaMap.get("D-"+statusId.toString());
						else if(issueType.equalsIgnoreCase("Hospitalization"))
							panvo = disStaMap.get("H-"+statusId.toString());
						//panvo = panStaMap.get(statusId);
						panvo.setCount(count);
					}
				}
			}
			
			Map<String,GrievanceDetailsVO> returnstatussVOMap = new LinkedHashMap<String,GrievanceDetailsVO>(0); 
			if(statusList != null && statusList.size() > 0){
				for (Object[] obj : statusList) {
					GrievanceDetailsVO vo = new GrievanceDetailsVO();
					
					Long id = Long.valueOf(obj[0] != null ? obj[0].toString():"0");
					String idStr = "D-"+id.toString();
					vo.setIdStr(idStr);
					vo.setName(obj[1] != null ? obj[1].toString():"");
					
					returnstatussVOMap.put(idStr, vo);
				}
				for (Object[] obj : statusList) {
					GrievanceDetailsVO vo = new GrievanceDetailsVO();
					
					Long id = Long.valueOf(obj[0] != null ? obj[0].toString():"0");
					String idStr = "H-"+id.toString();
					vo.setIdStr(idStr);
					vo.setName(obj[1] != null ? obj[1].toString():"");
					
					returnstatussVOMap.put(idStr, vo);
				}
			}
			
			Map<String,GrievanceDetailsVO> returnLocMap = new LinkedHashMap<String, GrievanceDetailsVO>();
			if(locationsMap != null && locationsMap.size()>0)
				for (String locationStr : locationsMap.keySet()) {
					GrievanceDetailsVO locationVo = new GrievanceDetailsVO();
					locationVo.setLocationName(locationStr);
					Map<String,Map<String,GrievanceDetailsVO>> issTypeMap = locationsMap.get(locationStr);
					if(issTypeMap != null && issTypeMap.size()>0 ){
						for (String issueTypeStr : issTypeMap.keySet()) {
							Map<String, GrievanceDetailsVO> statusVOMap = issTypeMap.get(issueTypeStr);
							if(statusVOMap != null && statusVOMap.size()>0 ){
								for (String statusStr : statusVOMap.keySet()) {
									GrievanceDetailsVO mapStatvo = statusVOMap.get(statusStr);
									GrievanceDetailsVO vo = returnstatussVOMap.get(statusStr);
									/*if(issueTypeStr.equalsIgnoreCase("Death"))
										vo.setDeathCount(mapStatvo.getCount());
									else if(issueTypeStr.equalsIgnoreCase("Hospitalization"))
										vo.setHospitCount(mapStatvo.getCount());
									vo.setCount(mapStatvo.getCount());
								}
							}
						}
					}
					
					locationVo.getSubList().addAll(returnstatussVOMap.values());
					returnLocMap.put(locationStr, locationVo);
				}
			}
			
			if(returnLocMap != null && returnLocMap.size()>0){
				grievanDetailsVO.getSubList().addAll(returnLocMap.values());
			}
			
		} catch (Exception e) {
			LOG.error("Exception occured in getDeathsAndHospitalizationStatusWiseDetailsInCadreLocation() Method ",e);
		}
		return grievanDetailsVO;
	}
	*/
	
	public void updateStatusMapDetails(List<Object[]> statusList,Map<String,Map<Long,GrievanceDetailsVO>> issueTypeMap,String issueTypeStr){
		try {
			Map<Long,GrievanceDetailsVO> statusMap=new LinkedHashMap<Long, GrievanceDetailsVO>(0);
			if(statusList != null && statusList.size() > 0){
				for (Object[] obj : statusList) {
					GrievanceDetailsVO vo = new GrievanceDetailsVO();
					
					Long id = Long.valueOf(obj[0] != null ? obj[0].toString():"0");
					vo.setId(id);
					vo.setName(obj[1] != null ? obj[1].toString():"");
					
					statusMap.put(id, vo);
				}
				/*GrievanceDetailsVO totalVo =new GrievanceDetailsVO();
				
				totalVo.setId(10l);
				totalVo.setName("TOTAL");
				
				statusMap.put(10l, totalVo);*/
				if(statusMap != null && statusMap.size()>0)
					issueTypeMap.put(issueTypeStr, statusMap);
			}
		} catch (Exception e) {
			LOG.error("Exception occured in updateStatusMapDetails() Method ",e);
		}
	}
	public GrievanceDetailsVO getDeathsAndHospitalizationStatusWiseDetailsInCadreLocation(Long panchayatId,Long mandalId,Long lebId,Long constituencyId,Long parliamentId,Long districtId){
		GrievanceDetailsVO grievanDetailsVO = new GrievanceDetailsVO();
		try {
			List<IdNameVO> locationsList = new ArrayList<IdNameVO>();
			List<Object[]> statusList = cadreHealthStatusDAO.getAllGrievanceInsuranceStatus();
			Map<Long,GrievanceDetailsVO> statusMap = new LinkedHashMap<Long, GrievanceDetailsVO>();
			if(statusList != null && statusList.size() > 0){
				for (Object[] obj : statusList) {
					GrievanceDetailsVO vo = new GrievanceDetailsVO();
					
					Long id = Long.valueOf(obj[0] != null ? obj[0].toString():"0");
					vo.setId(id);
					vo.setName(obj[1] != null ? obj[1].toString():"");
					
					statusMap.put(id, vo);
				}
				
			}
			
			//Map<String,Map<Long,GrievanceDetailsVO>> issueTypeMap = new LinkedHashMap<String, Map<Long,GrievanceDetailsVO>>();
			//issueTypeMap.put("Death", statusMap);
			//issueTypeMap.put("Hospitalization", statusMap);
			
			
			Map<String,Map<String,Map<Long,GrievanceDetailsVO>>> locationsMap = new LinkedHashMap<String, Map<String,Map<Long,GrievanceDetailsVO>>>();
			if(panchayatId != null && panchayatId.longValue() > 0l){
				//locationsMap.put("panchayat", issueTypeMap);
				
				//0.issue_type,1.grievance_insurance_status_id,2.status,3.COUNT.
				List<Object[]> list = cadreHealthStatusDAO.getDeathsAndHospitalizationStatusWiseDetails(panchayatId, "panchayat");
				if(list != null && list.size() > 0){
					IdNameVO vo = new IdNameVO();
					vo.setId(panchayatId);
					vo.setName("Panchayat");
					locationsList.add(vo);
					for (Object[] obj : list) {
						String issueType = obj[0] != null ? obj[0].toString():"";
						Long statusId = Long.valueOf(obj[1] != null ? obj[1].toString():"0");
						Long count = Long.valueOf(obj[3] != null ? obj[3].toString():"0");
						Map<String,Map<Long,GrievanceDetailsVO>> panIssTypeMap = null;
						if(locationsMap.get("panchayat") == null){
							panIssTypeMap = new LinkedHashMap<String, Map<Long,GrievanceDetailsVO>>();
							updateStatusMapDetails(statusList,panIssTypeMap,"Death");
							updateStatusMapDetails(statusList,panIssTypeMap,"Hospitalization");
							locationsMap.put("panchayat", panIssTypeMap);
						}
						else
							panIssTypeMap = locationsMap.get("panchayat");
						
						Map<Long,GrievanceDetailsVO> panStaMap = panIssTypeMap.get(issueType);
						
						GrievanceDetailsVO panvo = panStaMap.get(statusId);
						panvo.setCount(count);
					}
				}
			}
				
			if(mandalId != null && mandalId.longValue() > 0l){
				//locationsMap.put("mandal", issueTypeMap);
				
				//0.issue_type,1.grievance_insurance_status_id,2.status,3.COUNT.
				List<Object[]> list = cadreHealthStatusDAO.getDeathsAndHospitalizationStatusWiseDetails(mandalId, "mandal");
				if(list != null && list.size() > 0){
					IdNameVO vo = new IdNameVO();
					vo.setId(mandalId);
					vo.setName("Mandal");
					locationsList.add(vo);
					for (Object[] obj : list) {
						String issueType = obj[0] != null ? obj[0].toString():"";
						Long statusId = Long.valueOf(obj[1] != null ? obj[1].toString():"0");
						Long count = Long.valueOf(obj[3] != null ? obj[3].toString():"0");
						
						Map<String,Map<Long,GrievanceDetailsVO>> manIssTypeMap = null;
						//Map<String,Map<Long,GrievanceDetailsVO>> manIssTypeMap = locationsMap.get("mandal");
						if(locationsMap.get("mandal") == null){
							manIssTypeMap = new LinkedHashMap<String, Map<Long,GrievanceDetailsVO>>();
							updateStatusMapDetails(statusList,manIssTypeMap,"Death");
							updateStatusMapDetails(statusList,manIssTypeMap,"Hospitalization");
							locationsMap.put("mandal", manIssTypeMap);
						}
						else
							manIssTypeMap = locationsMap.get("mandal");
						Map<Long,GrievanceDetailsVO> manStaMap = manIssTypeMap.get(issueType);
						GrievanceDetailsVO manvo = manStaMap.get(statusId);
						manvo.setCount(count);
					}
				}
			}
			else if(lebId != null && lebId.longValue() > 0l){
				//locationsMap.put("muncipality", issueTypeMap);
				
				//0.issue_type,1.grievance_insurance_status_id,2.status,3.COUNT.
				List<Object[]> list = cadreHealthStatusDAO.getDeathsAndHospitalizationStatusWiseDetails(lebId, "leb");
				if(list != null && list.size() > 0){
					IdNameVO vo = new IdNameVO();
					vo.setId(lebId);
					vo.setName("Muncipality");
					locationsList.add(vo);
					for (Object[] obj : list) {
						String issueType = obj[0] != null ? obj[0].toString():"";
						Long statusId = Long.valueOf(obj[1] != null ? obj[1].toString():"0");
						Long count = Long.valueOf(obj[3] != null ? obj[3].toString():"0");
						
						//Map<String,Map<Long,GrievanceDetailsVO>> munIssTypeMap = locationsMap.get("muncipality");
						Map<String,Map<Long,GrievanceDetailsVO>> munIssTypeMap = null;
						if(locationsMap.get("muncipality") == null){
							munIssTypeMap = new LinkedHashMap<String, Map<Long,GrievanceDetailsVO>>();
							updateStatusMapDetails(statusList,munIssTypeMap,"Death");
							updateStatusMapDetails(statusList,munIssTypeMap,"Hospitalization");
							locationsMap.put("muncipality", munIssTypeMap);
						}
						else
							munIssTypeMap = locationsMap.get("muncipality");
						Map<Long,GrievanceDetailsVO> munStaMap = munIssTypeMap.get(issueType);
						GrievanceDetailsVO munvo = munStaMap.get(statusId);
						munvo.setCount(count);
					}
				}
			}
				
			if(constituencyId != null && constituencyId.longValue() > 0l){
				//locationsMap.put("assembly", issueTypeMap);
				
				//0.issue_type,1.grievance_insurance_status_id,2.status,3.COUNT.
				List<Object[]> list = cadreHealthStatusDAO.getDeathsAndHospitalizationStatusWiseDetails(constituencyId, "constituency");
				if(list != null && list.size() > 0){
					IdNameVO vo = new IdNameVO();
					vo.setId(constituencyId);
					vo.setName("assembly");
					locationsList.add(vo);
					for (Object[] obj : list) {
						String issueType = obj[0] != null ? obj[0].toString():"";
						Long statusId = Long.valueOf(obj[1] != null ? obj[1].toString():"0");
						Long count = Long.valueOf(obj[3] != null ? obj[3].toString():"0");
						Map<String,Map<Long,GrievanceDetailsVO>> assIssTypeMap = null;
						//Map<String,Map<Long,GrievanceDetailsVO>> assIssTypeMap = locationsMap.get("assembly");
						if(locationsMap.get("assembly") == null){
						assIssTypeMap = new LinkedHashMap<String, Map<Long,GrievanceDetailsVO>>();
						updateStatusMapDetails(statusList,assIssTypeMap,"Death");
						updateStatusMapDetails(statusList,assIssTypeMap,"Hospitalization");
						locationsMap.put("assembly", assIssTypeMap);
						}
						else
							assIssTypeMap =locationsMap.get("assembly");
						Map<Long,GrievanceDetailsVO> assStaMap = assIssTypeMap.get(issueType);
						GrievanceDetailsVO assvo = assStaMap.get(statusId);
						assvo.setCount(count);
					}
				}
			}
				
			if(parliamentId != null && parliamentId.longValue() > 0l){
				//locationsMap.put("parliament", issueTypeMap);
				
				//0.issue_type,1.grievance_insurance_status_id,2.status,3.COUNT.
				List<Object[]> list = cadreHealthStatusDAO.getDeathsAndHospitalizationStatusWiseDetails(parliamentId, "parliament");
				
				if(list != null && list.size() > 0){
					IdNameVO vo = new IdNameVO();
					vo.setId(parliamentId);
					vo.setName("Paliament");
					locationsList.add(vo);
					for (Object[] obj : list) {
						String issueType = obj[0] != null ? obj[0].toString():"";
						Long statusId = Long.valueOf(obj[1] != null ? obj[1].toString():"0");
						Long count = Long.valueOf(obj[3] != null ? obj[3].toString():"0");
						Map<String,Map<Long,GrievanceDetailsVO>> parIssTypeMap = null;
						//Map<String,Map<Long,GrievanceDetailsVO>> parIssTypeMap = locationsMap.get("parliament");
						if(locationsMap.get("parliament") == null){
							parIssTypeMap = new LinkedHashMap<String, Map<Long,GrievanceDetailsVO>>();
							updateStatusMapDetails(statusList,parIssTypeMap,"Death");
							updateStatusMapDetails(statusList,parIssTypeMap,"Hospitalization");
							locationsMap.put("parliament", parIssTypeMap);
						}
						else
							 parIssTypeMap = locationsMap.get("parliament");
						Map<Long,GrievanceDetailsVO> parStaMap = parIssTypeMap.get(issueType);
						GrievanceDetailsVO parvo = parStaMap.get(statusId);
						parvo.setCount(count);
						//parvo.setWelfareCount(parvo.getWelfareCount() + parvo.getCount());
					}
				}
			}
				
			if(districtId != null && districtId.longValue() > 0l){
				//locationsMap.put("district", issueTypeMap);
				
				//0.issue_type,1.grievance_insurance_status_id,2.status,3.COUNT.
				List<Object[]> list = cadreHealthStatusDAO.getDeathsAndHospitalizationStatusWiseDetails(districtId, "district");
				if(list != null && list.size() > 0){
					IdNameVO vo = new IdNameVO();
					vo.setId(districtId);
					vo.setName("District");
					locationsList.add(vo);
					for (Object[] obj : list) {
						String issueType = obj[0] != null ? obj[0].toString():"";
						Long statusId = Long.valueOf(obj[1] != null ? obj[1].toString():"0");
						Long count = Long.valueOf(obj[3] != null ? obj[3].toString():"0");
						Map<String,Map<Long,GrievanceDetailsVO>> disIssTypeMap = null;
						//Map<String,Map<Long,GrievanceDetailsVO>> disIssTypeMap = locationsMap.get("district");
						if(locationsMap.get("district") == null ){
						disIssTypeMap = new LinkedHashMap<String, Map<Long,GrievanceDetailsVO>>();
						updateStatusMapDetails(statusList,disIssTypeMap,"Death");
						updateStatusMapDetails(statusList,disIssTypeMap,"Hospitalization");
						locationsMap.put("district", disIssTypeMap);
						}
						else
							disIssTypeMap = locationsMap.get("district");
						Map<Long,GrievanceDetailsVO> disStaMap = disIssTypeMap.get(issueType);
						GrievanceDetailsVO disvo = disStaMap.get(statusId);
						disvo.setCount(count);
					}
				}
			}
			
			System.out.println(statusMap.size());
			List<GrievanceDetailsVO> locationWiseVOList = segrigateInsuranceStatusDetails(locationsMap);
			//statusMap id vo
			if(locationWiseVOList != null && locationWiseVOList.size()>0){
				for (GrievanceDetailsVO locationVO : locationWiseVOList) {
					List<GrievanceDetailsVO> issueTypeVOList = locationVO.getSubList();
					if(issueTypeVOList != null && issueTypeVOList.size()>0){
						updateStatusDetails(statusMap, issueTypeVOList,locationVO.getLocationName());
					}
				}
			}
			
			if(locationsList != null && locationsList.size() > 0){
				grievanDetailsVO.getLocationList().addAll(locationsList);
			}
			
			if(statusMap != null && statusMap.size()>0){
				for (Long statusId : statusMap.keySet()) {
					grievanDetailsVO.getSubList().add(statusMap.get(statusId));
				}
			}
			
			if(grievanDetailsVO != null && grievanDetailsVO.getSubList() != null && grievanDetailsVO.getSubList().size()>0){
				Map<String,Map<String,Long>> locationWiseStatusMap = new LinkedHashMap<String, Map<String,Long>>(0);
				for (GrievanceDetailsVO grievanceDetailsVO : grievanDetailsVO.getSubList()) {
					List<GrievanceDetailsVO> statusVOList = grievanceDetailsVO.getSubList();
				
					if(statusVOList != null && statusVOList.size()>0){
						for (GrievanceDetailsVO statusVO : statusVOList) {
							Map<String,Long> insuranceTypeMap = new LinkedHashMap<String, Long>(0);
							Long totalCount =statusVO.getCount() != null ?statusVO.getCount():0L;
							Long existingCount =0L;
							if(locationWiseStatusMap.get(statusVO.getLocationName().trim()) != null){
								insuranceTypeMap = locationWiseStatusMap.get(statusVO.getLocationName().trim());
								existingCount = insuranceTypeMap.get(statusVO.getName().trim()) != null ? insuranceTypeMap.get(statusVO.getName().trim()) :0L;
							}
							totalCount = totalCount+existingCount;
							insuranceTypeMap.put(statusVO.getName().trim(), totalCount);
							locationWiseStatusMap.put(statusVO.getLocationName().trim(), insuranceTypeMap);
						}
					}
				}
				
				if(locationWiseStatusMap != null && locationWiseStatusMap.size()>0){
					GrievanceDetailsVO locationVO =new GrievanceDetailsVO();
					locationVO.setName("TOTAL");
					for (String locationName : locationWiseStatusMap.keySet()) {
						Map<String,Long> insuranceTypeMap = locationWiseStatusMap.get(locationName);
						
						if(insuranceTypeMap != null && insuranceTypeMap.size()>0){
							for (String insuranceTypeStr : insuranceTypeMap.keySet()) {
								GrievanceDetailsVO insuranceTypeVO =new GrievanceDetailsVO();
								insuranceTypeVO.setName(insuranceTypeStr);
								insuranceTypeVO.setLocationName(locationName);
								insuranceTypeVO.setId(0L);
								insuranceTypeVO.setCount(insuranceTypeMap.get(insuranceTypeStr));									
								locationVO.getSubList().add(insuranceTypeVO);
							}
						}
					}
					grievanDetailsVO.getSubList().add(locationVO);
				}
			}
		} catch (Exception e) {
			LOG.error("Exception occured in getDeathsAndHospitalizationStatusWiseDetailsInCadreLocation() Method ",e);
		}
		return grievanDetailsVO;
	}
	
	public void updateStatusDetails(Map<Long,GrievanceDetailsVO> statusMap,List<GrievanceDetailsVO> issueTypeVOList,String locationName){
		try {
			for (GrievanceDetailsVO issueTypeVO : issueTypeVOList) {
				List<GrievanceDetailsVO> statusVOList = issueTypeVO.getSubList();
				if(statusVOList != null && statusVOList.size()>0){
					for (GrievanceDetailsVO statusVO : statusVOList) {
						GrievanceDetailsVO returnStatusVO = statusMap.get(statusVO.getId());
						if(returnStatusVO != null){
							//returnStatusVO.setLocationName(locationName);
							GrievanceDetailsVO stusVO = new GrievanceDetailsVO();
							stusVO.setName(issueTypeVO.getName());
							stusVO.setCount(statusVO.getCount());
							stusVO.setLocationName(locationName);
							
							stusVO.setId(statusVO.getId());
							returnStatusVO.getSubList().add(stusVO);
						}
					}
				}
			}
		} catch (Exception e) {
			LOG.error("Exception occured in updateStatusDetails() Method ",e);
		}
	}
	public List<GrievanceDetailsVO> segrigateInsuranceStatusDetails(Map<String,Map<String,Map<Long,GrievanceDetailsVO>>> locationsMap){
		List<GrievanceDetailsVO> returnList = new LinkedList<GrievanceDetailsVO>();
		try {
			if(locationsMap != null && locationsMap.size()>0){
				List<GrievanceDetailsVO> locationWiseVOList = new LinkedList<GrievanceDetailsVO>();
				for (String locationStr : locationsMap.keySet()) {
					Map<String,Map<Long,GrievanceDetailsVO>> issueTypeMap = locationsMap.get(locationStr);
					
					GrievanceDetailsVO locationVO = new GrievanceDetailsVO();
					locationVO.setLocationName(locationStr);
					
					if(issueTypeMap != null && issueTypeMap.size()>0){
						List<GrievanceDetailsVO> issueTypeVOList = new LinkedList<GrievanceDetailsVO>();
						for (String issueTypeStr : issueTypeMap.keySet()) {// death/ Hospitalization
							
							GrievanceDetailsVO issueVO = new GrievanceDetailsVO(); 
							issueVO.setName(issueTypeStr);
							Map<Long, GrievanceDetailsVO> sttusMap = issueTypeMap.get(issueTypeStr);
							
							if(sttusMap != null && sttusMap.size()>0){
								List<GrievanceDetailsVO> statusVOList = new LinkedList<GrievanceDetailsVO>();
								for (Long statusId : sttusMap.keySet()) {//status list
									
									GrievanceDetailsVO statusObj = sttusMap.get(statusId);
									
									GrievanceDetailsVO statusVO = new GrievanceDetailsVO();
									statusVO.setCount(statusObj.getCount());
									statusVO.setName(statusObj.getName());
									statusVO.setId(statusObj.getId());
									statusVOList.add(statusVO);
								}
								issueVO.getSubList().addAll(statusVOList);
							}
							
							issueTypeVOList.add(issueVO);
						}
						locationVO.getSubList().addAll(issueTypeVOList);
					}
					locationWiseVOList.add(locationVO);
				}
				
				if(locationWiseVOList != null && locationWiseVOList.size()>0)
					returnList.addAll(locationWiseVOList);
			}
		} catch (Exception e) {
			LOG.error("Exception occured in segrigateInsuranceStatusDetails() Method ",e);
		}
		return returnList;
	}
	public List<GrievanceDetailsVO> getDeathAndHospitalizationDetails(Long panchayatId,Long mandalId,Long lebId,Long constituencyId,Long parliamentId,Long districtId){
		List<GrievanceDetailsVO> voList = new ArrayList<GrievanceDetailsVO>();
		try {
			
			if(panchayatId != null && panchayatId.longValue() > 0l){
				List<Object[]> list = tdpCadreInsuranceInfoDAO.getDeathAndHospitalizationDetails(panchayatId, "panchayat");
				if(list != null && list.size() > 0){
					GrievanceDetailsVO vo = new GrievanceDetailsVO();
					vo.setName("panchayat");
					for (Object[] obj : list) {
						String issueType = obj[1] != null ? obj[1].toString():"";
						if(issueType.equalsIgnoreCase("Death"))
							vo.setDeathCount(Long.valueOf(obj[0] != null ? obj[0].toString():"0"));
						else if(issueType.equalsIgnoreCase("Hospitalization"))
							vo.setHospitCount(Long.valueOf(obj[0] != null ? obj[0].toString():"0"));
					}
					voList.add(vo);
				}
			}
			
			if(mandalId != null && mandalId.longValue() > 0l){
				List<Object[]> list = tdpCadreInsuranceInfoDAO.getDeathAndHospitalizationDetails(mandalId, "mandal");
				if(list != null && list.size() > 0){
					GrievanceDetailsVO vo = new GrievanceDetailsVO();
					vo.setName("mandal");
					for (Object[] obj : list) {
						String issueType = obj[1] != null ? obj[1].toString():"";
						if(issueType.equalsIgnoreCase("Death"))
							vo.setDeathCount(Long.valueOf(obj[0] != null ? obj[0].toString():"0"));
						else if(issueType.equalsIgnoreCase("Hospitalization"))
							vo.setHospitCount(Long.valueOf(obj[0] != null ? obj[0].toString():"0"));
					}
					voList.add(vo);
				}
			}
			else if(lebId != null && lebId.longValue() > 0l){
				List<Object[]> list = tdpCadreInsuranceInfoDAO.getDeathAndHospitalizationDetails(lebId, "leb");
				if(list != null && list.size() > 0){
					GrievanceDetailsVO vo = new GrievanceDetailsVO();
					vo.setName("muncipality");
					for (Object[] obj : list) {
						String issueType = obj[1] != null ? obj[1].toString():"";
						if(issueType.equalsIgnoreCase("Death"))
							vo.setDeathCount(Long.valueOf(obj[0] != null ? obj[0].toString():"0"));
						else if(issueType.equalsIgnoreCase("Hospitalization"))
							vo.setHospitCount(Long.valueOf(obj[0] != null ? obj[0].toString():"0"));
					}
					voList.add(vo);
				}
			}
			
			if(constituencyId != null && constituencyId.longValue() > 0l){
				List<Object[]> list = tdpCadreInsuranceInfoDAO.getDeathAndHospitalizationDetails(constituencyId, "constituency");
				if(list != null && list.size() > 0){
					GrievanceDetailsVO vo = new GrievanceDetailsVO();
					vo.setName("constituency");
					for (Object[] obj : list) {
						String issueType = obj[1] != null ? obj[1].toString():"";
						if(issueType.equalsIgnoreCase("Death"))
							vo.setDeathCount(Long.valueOf(obj[0] != null ? obj[0].toString():"0"));
						else if(issueType.equalsIgnoreCase("Hospitalization"))
							vo.setHospitCount(Long.valueOf(obj[0] != null ? obj[0].toString():"0"));
					}
					voList.add(vo);
				}
			}
			
			if(parliamentId != null && parliamentId.longValue() > 0l){
				List<Object[]> list = tdpCadreInsuranceInfoDAO.getDeathAndHospitalizationDetails(parliamentId, "parliament");
				if(list != null && list.size() > 0){
					GrievanceDetailsVO vo = new GrievanceDetailsVO();
					vo.setName("parliament");
					for (Object[] obj : list) {
						String issueType = obj[1] != null ? obj[1].toString():"";
						if(issueType.equalsIgnoreCase("Death"))
							vo.setDeathCount(Long.valueOf(obj[0] != null ? obj[0].toString():"0"));
						else if(issueType.equalsIgnoreCase("Hospitalization"))
							vo.setHospitCount(Long.valueOf(obj[0] != null ? obj[0].toString():"0"));
					}
					voList.add(vo);
				}
			}
			
			if(districtId != null && districtId.longValue() > 0l){
				List<Object[]> list = tdpCadreInsuranceInfoDAO.getDeathAndHospitalizationDetails(districtId, "district");
				if(list != null && list.size() > 0){
					GrievanceDetailsVO vo = new GrievanceDetailsVO();
					vo.setName("district");
					for (Object[] obj : list) {
						String issueType = obj[1] != null ? obj[1].toString():"";
						if(issueType.equalsIgnoreCase("Death"))
							vo.setDeathCount(Long.valueOf(obj[0] != null ? obj[0].toString():"0"));
						else if(issueType.equalsIgnoreCase("Hospitalization"))
							vo.setHospitCount(Long.valueOf(obj[0] != null ? obj[0].toString():"0"));
					}
					voList.add(vo);
				}
			}
			
		} catch (Exception e) {
			LOG.error("Exception occured in getDeathAndHospitalizationDetails() Method ",e);
		}
		return voList;
	}
	
	public VerifierVO getTdpCadreIvrSurveyDetails(Long cadreId)
	{
		VerifierVO returnVo = new VerifierVO();
		try{
			Long ivrRespondentId = ivrRespondentCadreDAO.getIvrRespondentId(cadreId);
			List<Object[]> list = ivrSurveyAnswerDAO.getIvrSurveyAnswerInfoForTdpCadre(ivrRespondentId);
			if(list != null && list.size() > 0)
			{
				
				for(Object[] params : list)
				{
					//Survey List
						VerifierVO surveyVo =  getMatchedVerifierVO(returnVo.getVerifierVOList(),(Long)params[0]);
						if(surveyVo == null)
						{
							surveyVo = new VerifierVO();
							surveyVo.setId((Long)params[0]);
							surveyVo.setName(params[1] != null ? params[1].toString() : "");
							surveyVo.setAnswerType(params[10] != null ? params[10].toString() : "");
							returnVo.getVerifierVOList().add(surveyVo);
						}
						
						//question List	
						
						VerifierVO questionVo =  getMatchedVerifierVO(surveyVo.getVerifierVOList(),(Long)params[5]);
						if(questionVo == null)
						{
							questionVo = new VerifierVO();
							questionVo.setId((Long)params[5]);
							questionVo.setName(params[6] != null ? params[6].toString() : "");
							questionVo.setRound(params[3] != null ? params[3].toString() : "");
							surveyVo.getVerifierVOList().add(questionVo);
						}
						
						//Option List
						if(params[7] != null)
						{
							VerifierVO optionVo =  getMatchedVerifierVO(questionVo.getVerifierVOList(),(Long)params[7]);
							if(optionVo == null)
							{
								optionVo = new VerifierVO();
								optionVo.setId((Long)params[7]);
								optionVo.setName(params[8] != null ? params[8].toString() : "");
								questionVo.setAnswerType(params[10] != null ? params[10].toString() : "");
								questionVo.setRound(params[3] != null ? params[3].toString() : "");
								questionVo.getVerifierVOList().add(optionVo);
							}
						}
				}
			}
		}
		catch(Exception e)
		{
			LOG.error("Exception occured in getTdpCadreIvrSurveyDetails() Method ",e);	
		}
		return returnVo;
	}

	
	/**
	 * @author 		:Hymavathi
	 * @date   		:${03/05/2016}
	 * @param  		:Long 
	 * @returntype  :${VerifierVO}
	 * @description :${Getting Total Count of Candidate Participated in IVR Survey By Sending Input as TdpCadre Id}
	 */
	public VerifierVO getCandateParicipatedSurveyCount(Long tdpCadreId){
		VerifierVO verifierVO = new VerifierVO();
		try {
		Long ivrRespondantId = ivrRespondentCadreDAO.getIvrRespondentId(tdpCadreId);
		if(ivrRespondantId != null && ivrRespondantId.longValue() > 0){
			Long count = ivrSurveyAnswerDAO.getCandateParticipatedSurveyCnt(ivrRespondantId);
			if(count != null && count.longValue() > 0){
				verifierVO.setTotalCount(count);
			}
		}
		} catch (Exception e) {
			LOG.error(" Exception Occured in getCandateParicipatedSurveyCount() method, Exception - ",e);
		}			
		return verifierVO;
	}
	
	public List<GrievanceDetailsVO> getComplaintsDetailsByLocationAndStatus(Long locationId,String locationType,Long insuranceStatId,String issueType){
		List<GrievanceDetailsVO> returnList = new ArrayList<GrievanceDetailsVO>();
		try {
			
			List<Object[]> list = cadreHealthStatusDAO.getComplaintsDetailsByLocationAndStatus(locationId, locationType, insuranceStatId, issueType);
			if(list != null && list.size() > 0){
				for (Object[] obj : list) {
					GrievanceDetailsVO vo = new GrievanceDetailsVO();
					
					vo.setFirstName(obj[0] != null ? obj[0].toString():"");
					vo.setMembershipNo(obj[1] != null ? obj[1].toString():"");
					vo.setMobileNo(obj[2] != null ? obj[2].toString():"");
					//vo.setSubject(obj[3] != null ? obj[3].toString():"");
					//vo.setDescription(obj[4] != null ? obj[4].toString():"");
					vo.setComplaintId(Long.valueOf(obj[3] != null ? obj[3].toString():""));
					vo.setRaisedDate(obj[4] != null ? obj[4].toString():"");
					vo.setTypeOfIssue(obj[5] != null ? obj[5].toString():"");
					vo.setStatusId(Long.valueOf(obj[6] != null ? obj[6].toString():""));
					vo.setStatus(obj[7] != null ? obj[7].toString():"");
					vo.setUpdatedDate(obj[8] != null ? obj[8].toString():"");
					vo.setTdpCadreId(Long.valueOf(obj[9] != null ? obj[9].toString():"0"));
					vo.setSubject(obj[10] != null ? obj[10].toString():"");
					vo.setDescription(obj[11] != null ? obj[11].toString():"");
					vo.setLocationName(obj[12] != null ? obj[12].toString():"");
					vo.setConstituency(obj[13] != null ? obj[13].toString():"");
					vo.setVillageName(obj[14] != null ? obj[14].toString():"");
					vo.setMandalName(obj[15] != null ? obj[15].toString():"");
					returnList.add(vo);
				}
			}
			
		} catch (Exception e) {
			LOG.error(" Exception Occured in getComplaintsDetailsByLocationAndStatus() method, Exception - ",e);
		}
		return returnList;
	}
	
	public VerifierVO getSurveysOnCandidateCount(Long candidateId){
		VerifierVO verifierVO = new VerifierVO();
		
			Long count = ivrSurveyCandidateQuestionDAO.getIvrSurveyCountByCandiate(candidateId);
			if(count != null && count.longValue() > 0){
				verifierVO.setTotalCount(count);
			}
		return verifierVO;
	}
	
	public List<QuestionAnswerVO> getSurveysOnCandidateDetails(Long candidateId)
	{
		List<QuestionAnswerVO> returnList = new ArrayList<QuestionAnswerVO>();
		List<Long> questionIds = new ArrayList<Long>();
		try{
			questionIds =ivrSurveyCandidateQuestionDAO.getIvrSurveyQuestionsByCandiate(candidateId);
			if(questionIds != null && questionIds.size() > 0)
			{
				 List<Object[]> list = ivrSurveyAnswerDAO.getOptionsCountByQuestionIds(questionIds);
				 if(list != null && list.size() > 0)
				 {
					 for(Object[] params : list)
					 {
						 QuestionAnswerVO surveyVO = getMatchedVO(returnList,(Long)params[1]);
						 
						 if(surveyVO == null)
						 {
							 surveyVO = new QuestionAnswerVO();
							 surveyVO.setId((Long)params[1]);
							 surveyVO.setName(params[2] != null ? params[2].toString() :"");
							 surveyVO.setQuestions(new ArrayList<QuestionAnswerVO>());
							 returnList.add(surveyVO);
						 }
						 
						 QuestionAnswerVO questionVO = getMatchedVO(surveyVO.getQuestions(),(Long)params[3]);
						 
						 if(questionVO == null)
						 {
							 questionVO = new QuestionAnswerVO();
							 questionVO.setId((Long)params[3]);
							 questionVO.setName(params[4] != null ? params[4].toString() :"");
							 questionVO.setOptions(new ArrayList<QuestionAnswerVO>());
							 questionVO.setCount(params[0] != null ? (Long)params[0] + questionVO.getCount() : 0l);
							 surveyVO.getQuestions().add(questionVO);
						 }
						 
						 
						 QuestionAnswerVO optionVO = getMatchedVO(questionVO.getOptions(),(Long)params[5]);
						 
						 if(optionVO == null)
						 {
							 optionVO = new QuestionAnswerVO();
							 optionVO.setId((Long)params[5]);
							 optionVO.setName(params[6] != null ? params[6].toString() :"");
							 optionVO.setCount(params[0] != null ? (Long)params[0] + optionVO.getCount() : 0l);
							 optionVO.setPercentage("0.0"); 
							 questionVO.getOptions().add(optionVO);
						 }
					 }
				 }
				List<Object[]> list1 = ivrSurveyCandidateQuestionDAO.getIvrSurveyQuestionsOptionsByCandiate(questionIds);
				if(list1 != null && list1.size() > 0)
				{
					 for(Object[] params : list1)
					 {
						 QuestionAnswerVO surveyVO = getMatchedVO(returnList,(Long)params[0]);
						 if(surveyVO != null)
						 {
							 QuestionAnswerVO questionVO = getMatchedVO(surveyVO.getQuestions(),(Long)params[2]);
							if(questionVO != null)
							{
								QuestionAnswerVO optionVO = getMatchedVO(questionVO.getOptions(),(Long)params[4]);
						 
							 if(optionVO != null)
							 {
								 optionVO.setCandidateId(params[6] != null ? (Long)params[6] : 0l);
								 optionVO.setCandidateName(params[7] != null ? params[7].toString() :"");
								 if(questionVO.getCount() != null && questionVO.getCount() > 0)
								 {
								 String percentage = (new BigDecimal(optionVO.getCount()*(100.0)/questionVO.getCount())).setScale(2, BigDecimal.ROUND_HALF_UP).toString();
								 optionVO.setPercentage(percentage);
								 }
								 else
									 optionVO.setPercentage("0.0"); 
								
							 }
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
	
	public QuestionAnswerVO getMatchedVO(List<QuestionAnswerVO> returnList,Long id)
	{
		try{
			if(returnList == null || returnList.size() == 0)
				return null;
			for(QuestionAnswerVO vo : returnList)
			{
				if(vo.getId().longValue()== id.longValue())
					return vo;
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return null;
	}
	
	public List<CadreDetailsVO> getAppointmentsUserDetails(List<Long> appointmentUserIds, Long tdpcadreId){
		
		List<CadreDetailsVO> finalVo = new ArrayList<CadreDetailsVO>();
		
		try{
			
			List<Object[]>  apptUserDetails = appointmentCandidateRelationDAO.getCandidateAppointmentDetails(appointmentUserIds,tdpcadreId);
			if(apptUserDetails !=null && apptUserDetails.size()>0){
				for(Object[] obj : apptUserDetails){
					CadreDetailsVO userVO = new CadreDetailsVO();
					
					userVO.setApptUserId(obj[0]!=null?(Long)obj[0]:0l);
					userVO.setName(obj[1]!=null?obj[1].toString():"");
					userVO.setApptcandidateId(obj[2]!=null?(Long)obj[2]:0l);
					userVO.setApptCount(obj[3]!=null?(Long)obj[3]:0l);
					userVO.setApptcandidateName(obj[4]!=null?obj[4].toString():"");
					finalVo.add(userVO);
				}
			}
			
			
		}catch (Exception e) {
			LOG.error(" Exception Occured in getAppointmentsUserDetails() method, Exception - ",e);
		}
		
		return finalVo;
		
		
	}
	
	
	public List<GrievanceDetailsVO> getGrievanceStatusByTypeOfIssueLocation(Long districtId,Long assemblyId,Long parliamentId){	
		List<GrievanceDetailsVO> ListOfResult = new ArrayList<GrievanceDetailsVO>();
		try {
			LOG.error("Entered in to getGrievanceStatusByTypeOfIssueLocation() Method ");
			
			Long constTotalcount = 0l;
       if(assemblyId != null && assemblyId.longValue() > 0l){
			//	List<Object[]> constituencyList = userAddressDAO.getGrievanceStatusCountsByTypeOfIssue(constituencyId, "assembly");
    		List<Object[]> constituencyList = userAddressDAO.getGrievanceStatusCountsByTypeOfIssue(assemblyId, "assembly");
				if(constituencyList != null && constituencyList.size()>0){
					GrievanceDetailsVO vo = new GrievanceDetailsVO();
					for (Object[] obj : constituencyList) {
						String status = obj[0] != null ? obj[0].toString():"";
						
						vo.setName("assembly");
						if(status.equalsIgnoreCase("Party"))
							vo.setPartyCount(Long.valueOf(obj[1] != null ? obj[1].toString():""));
						else if(status.equalsIgnoreCase("Govt"))
							vo.setGovtCount(Long.valueOf(obj[1] != null ? obj[1].toString():""));
						else if(status.equalsIgnoreCase("Welfare"))
							vo.setWelfareCount(Long.valueOf(obj[1] != null ? obj[1].toString():""));
						constTotalcount = vo.getPartyCount()+vo.getGovtCount()+vo.getWelfareCount();
						vo.setCount(constTotalcount);
					}
					if(vo.getPartyCount() > 0l)
						vo.setPartyPerc((new BigDecimal((vo.getPartyCount() * 100.0)/vo.getCount().doubleValue()).setScale(2, BigDecimal.ROUND_HALF_UP)).toString());
					if(vo.getGovtCount() > 0l)
						vo.setGovtPerc((new BigDecimal((vo.getGovtCount() * 100.0)/vo.getCount().doubleValue()).setScale(2, BigDecimal.ROUND_HALF_UP)).toString());
					if(vo.getWelfareCount() > 0l)
						vo.setWelfarePerc((new BigDecimal((vo.getWelfareCount() * 100.0)/vo.getCount().doubleValue()).setScale(2, BigDecimal.ROUND_HALF_UP)).toString());
					ListOfResult.add(vo);
				}
			}
       Long parlTotalCount = 0l;
       if(parliamentId != null && parliamentId.longValue() > 0l){
				List<Object[]> parliamentList = userAddressDAO.getGrievanceStatusCountsByTypeOfIssue(parliamentId, "parliament");
				
				if(parliamentList!=null && parliamentList.size()>0){
					GrievanceDetailsVO vo = new GrievanceDetailsVO();
					for (Object[] obj : parliamentList) {
						String status = obj[0] != null ? obj[0].toString():"";
						vo.setName("parliament");
						if(status.equalsIgnoreCase("Party"))
							vo.setPartyCount(Long.valueOf(obj[1] != null ? obj[1].toString():""));
						else if(status.equalsIgnoreCase("Govt"))
							vo.setGovtCount(Long.valueOf(obj[1] != null ? obj[1].toString():""));
						else if(status.equalsIgnoreCase("Welfare"))
							vo.setWelfareCount(Long.valueOf(obj[1] != null ? obj[1].toString():""));
						parlTotalCount = vo.getPartyCount()+vo.getGovtCount()+vo.getWelfareCount();
						vo.setCount(parlTotalCount);
					}
					if(vo.getPartyCount() > 0l)
						vo.setPartyPerc((new BigDecimal((vo.getPartyCount() * 100.0)/vo.getCount().doubleValue()).setScale(2, BigDecimal.ROUND_HALF_UP)).toString());
					if(vo.getGovtCount() > 0l)
						vo.setGovtPerc((new BigDecimal((vo.getGovtCount() * 100.0)/vo.getCount().doubleValue()).setScale(2, BigDecimal.ROUND_HALF_UP)).toString());
					if(vo.getWelfareCount() > 0l)
						vo.setWelfarePerc((new BigDecimal((vo.getWelfareCount() * 100.0)/vo.getCount().doubleValue()).setScale(2, BigDecimal.ROUND_HALF_UP)).toString());
					ListOfResult.add(vo);		
				}
			}
       Long distTotalcount = 0l;
		if(districtId != null && districtId.longValue() > 0l){
			List<Object[]> districtList = userAddressDAO.getGrievanceStatusCountsByTypeOfIssue(districtId, "district");
			if(districtList != null && districtList.size()>0){
				GrievanceDetailsVO vo = new GrievanceDetailsVO();
				for (Object[] obj : districtList) {
					String status = obj[0] != null ? obj[0].toString():"";
					
						vo.setName("district");
						if(status.equalsIgnoreCase("Party"))
						    vo.setPartyCount(Long.valueOf(obj[1] != null ? obj[1].toString():""));
						else if(status.equalsIgnoreCase("Govt"))
							vo.setGovtCount(Long.valueOf(obj[1] != null ? obj[1].toString():""));
						else if(status.equalsIgnoreCase("Welfare"))
							vo.setWelfareCount(Long.valueOf(obj[1] != null ? obj[1].toString():""));
						distTotalcount = vo.getPartyCount()+vo.getGovtCount()+vo.getWelfareCount();
						vo.setCount(distTotalcount);
					}
				if(vo.getPartyCount() > 0l)
					vo.setPartyPerc((new BigDecimal((vo.getPartyCount() * 100.0)/vo.getCount().doubleValue()).setScale(2, BigDecimal.ROUND_HALF_UP)).toString());
				if(vo.getGovtCount() > 0l)
					vo.setGovtPerc((new BigDecimal((vo.getGovtCount() * 100.0)/vo.getCount().doubleValue()).setScale(2, BigDecimal.ROUND_HALF_UP)).toString());
				if(vo.getWelfareCount() > 0l)
					vo.setWelfarePerc((new BigDecimal((vo.getWelfareCount() * 100.0)/vo.getCount().doubleValue()).setScale(2, BigDecimal.ROUND_HALF_UP)).toString());
				
				ListOfResult.add(vo);
			}
		}
       
		} catch (Exception e) {
			LOG.error("Exception occured in getGrievanceStatusByTypeOfIssueLocation() Method ",e);
			
		}
		return ListOfResult;
		
	}
	
	public void updateStatusMapDetailsForGrievance(List<String> statusList,Map<String,Map<String,Long>> issueTypeMap,String issueTypeStr){
	    try {
	      Map<String,Long> statusMap=new LinkedHashMap<String, Long>();
	      if(statusList != null && statusList.size() > 0){
	        for (String status : statusList) {
	        	//String completeStatus = obj[0] != null? obj[0].toString():"";
	        	if(status != null)
	        		statusMap.put(status.toUpperCase(), 0l);
	        }
	        
	        if(statusMap != null && statusMap.size()>0)
	          issueTypeMap.put(issueTypeStr, statusMap);
	      }
	    } catch (Exception e) {
	      LOG.error("Exception occured in updateStatusMapDetails() Method ",e);
	    }
	  }
	
public GrievanceDetailsVO getGrievanceStatusByTypeOfIssueAndCompleteStatusDetails(Long districtId,Long assemblyId,Long parliamentId){//Teja
		GrievanceDetailsVO grievanDetailsVO = new GrievanceDetailsVO();
		try {
			LOG.error("Entered in to getGrievanceStatusByTypeOfIssueAndCompleteStatusDetails() Method ");
			
			Map<String, GrievanceDetailsVO> statusMap = new LinkedHashMap<String, GrievanceDetailsVO>(0);
			List<String> statusList = userAddressDAO.getCompletedStatus();
			if(statusList != null && statusList.size()>0){
				for (String status : statusList) {
					if(status !=null){
						GrievanceDetailsVO vo = new GrievanceDetailsVO();
						vo.setName(status.toUpperCase());
						vo.setCount(0l);
						statusMap.put(status.toUpperCase(), vo);
					}
				}
			}
			Map<String,GrievanceDetailsVO> stausMap = new LinkedHashMap<String, GrievanceDetailsVO>();
			String order = IConstants.GRIEVANCE_REQUESTS_STATUS_LIST;
			String[] statusStrArr = order.split(",");
			for (String string : statusStrArr) {
				GrievanceDetailsVO vo = statusMap.get(string.toUpperCase());
				stausMap.put(string.toUpperCase(), vo);
			}
			
			Map<String,Map<String,Map<String, Long>>> locationWiseMap = new LinkedHashMap<String, Map<String,Map<String,Long>>>(0);
			if(assemblyId != null && assemblyId.longValue() > 0l){
			List <Object[]> assembliList = userAddressDAO.getGrievanceStatusWiseCountsByTypeOfIssueAndStatus(assemblyId, "assembly");
				if(assembliList != null && assembliList.size()>0){
					for (Object[] obj : assembliList) {
						String typeOfIssue = obj[0] != null ? obj[0].toString():"";
				        String status = obj[1] != null ? obj[1].toString():"";
				        Long count = Long.valueOf(obj[2] != null ? obj[2].toString():"0");
				        Map<String,Map<String, Long>> assIssTypeMap = null;
				        if(locationWiseMap.get("assembly") == null){
				        	
				        	assIssTypeMap = new LinkedHashMap<String, Map<String,Long>>();
				        	updateStatusMapDetailsForGrievance(statusList,assIssTypeMap,"Party");
				        	updateStatusMapDetailsForGrievance(statusList,assIssTypeMap,"Govt");
				        	updateStatusMapDetailsForGrievance(statusList,assIssTypeMap,"Welfare");
				        	updateStatusMapDetailsForGrievance(statusList,assIssTypeMap,"Benefit");
				        	locationWiseMap.put("assembly",assIssTypeMap);
				        }
				        else{
				        	assIssTypeMap = locationWiseMap.get("assembly");
				        }
 				        Map<String, Long> staGetMap = assIssTypeMap.get(typeOfIssue);
 				       if(staGetMap != null && staGetMap.size()>0)
				        staGetMap.put(status.toUpperCase(),count);
				        }
				        }
					}
				
			if(parliamentId != null && parliamentId.longValue() > 0l){
				List<Object[]> parliamentList = userAddressDAO.getGrievanceStatusWiseCountsByTypeOfIssueAndStatus(parliamentId, "parliament");
				if(parliamentList != null && parliamentList.size()>0){
					for (Object[] obj : parliamentList) {
						String typeOfIssue = obj[0] != null ? obj[0].toString():"";
				        String status = obj[1] != null ? obj[1].toString():"";
				        Long count = Long.valueOf(obj[2] != null ? obj[2].toString():"0");
				        Map<String,Map<String, Long>> parIssTypeMap = null;
				        
				        if(locationWiseMap.get("parliament") == null){
				        	parIssTypeMap = new LinkedHashMap<String, Map<String,Long>>();
				        	updateStatusMapDetailsForGrievance(statusList,parIssTypeMap,"Party");
				        	updateStatusMapDetailsForGrievance(statusList,parIssTypeMap,"Govt");
				        	updateStatusMapDetailsForGrievance(statusList,parIssTypeMap,"Welfare");
				        	updateStatusMapDetailsForGrievance(statusList,parIssTypeMap,"Benefit");
				        	locationWiseMap.put("parliament",parIssTypeMap);
				        }
				        else{
				        	parIssTypeMap = locationWiseMap.get("parliament");
				        }
				        Map<String, Long> staGetMap = parIssTypeMap.get(typeOfIssue);
				        if(staGetMap != null && staGetMap.size()>0)
				        staGetMap.put(status.toUpperCase(), count);
					}
				}
			}
			
			if(districtId != null && districtId.longValue() > 0l){
				List<Object[]> districtList = userAddressDAO.getGrievanceStatusWiseCountsByTypeOfIssueAndStatus(districtId, "district");
				if(districtList != null && districtList.size()>0){
					for (Object[] obj : districtList) {
						String typeOfIssue = obj[0] != null ? obj[0].toString():"";
				        String status = obj[1] != null ? obj[1].toString():"";
				        Long count = Long.valueOf(obj[2] != null ? obj[2].toString():"0");
				        Map<String,Map<String, Long>> disIssTypeMap = null;
				        if(locationWiseMap.get("district") == null){
				        	disIssTypeMap = new LinkedHashMap<String, Map<String,Long>>();
				        	updateStatusMapDetailsForGrievance(statusList,disIssTypeMap,"Party");
				        	updateStatusMapDetailsForGrievance(statusList,disIssTypeMap,"Govt");
				        	updateStatusMapDetailsForGrievance(statusList,disIssTypeMap,"Welfare");
				        	updateStatusMapDetailsForGrievance(statusList,disIssTypeMap,"Benefit");
				        	locationWiseMap.put("district",disIssTypeMap);
				        }
				        else{
				        	disIssTypeMap = locationWiseMap.get("district");
				        }
				        Map<String, Long> staGetMap = disIssTypeMap.get(typeOfIssue);
				        if(staGetMap != null && staGetMap.size()>0)
				        staGetMap.put(status.toUpperCase(), count);
					}
				}
			}
			
			List<GrievanceDetailsVO> locationWiseVOList = segrigateGrievanceStatusDetails(locationWiseMap);
			
			if(locationWiseVOList != null && locationWiseVOList.size()>0){
		        for (GrievanceDetailsVO locationVO : locationWiseVOList) {
		          List<GrievanceDetailsVO> issueTypeVOList = locationVO.getSubList();
		          if(issueTypeVOList != null && issueTypeVOList.size()>0){
		            updateStatusDetailsForGrievance(stausMap, issueTypeVOList,locationVO.getLocationName());
		          }
		        }
		      }
			
			Map<String, GrievanceDetailsVO> benefitMap = getGrievanceStatusCountsBenifit(districtId,assemblyId,parliamentId);
		if(stausMap != null && stausMap.size()>0){
			for (String status : stausMap.keySet()) {
				GrievanceDetailsVO grievanceDetailsVo = statusMap.get(status);
				List<GrievanceDetailsVO> subList = grievanceDetailsVo.getSubList();
				GrievanceDetailsVO  benefitVO = benefitMap.get(status);
				int i = 0;
				GrievanceDetailsVO tempVO = null;
				if(benefitVO != null && benefitVO.getSubList() != null && benefitVO.getSubList().size()>0){
					
					do{
						tempVO = benefitVO.getSubList().get(i);
						if(subList != null && subList.size()>0)
						{
							for (int j=0;j<subList.size();j++)
							{
								if(subList.get(j).getLocationName() != null &&  
										subList.get(j).getLocationName().trim().equalsIgnoreCase(tempVO.getLocationName()))
									continue;
								else if(j>0 && j<subList.size()){
									subList.get(j-1).setCount(tempVO.getCount());
									i++;
									if(benefitVO.getSubList().size()>i)
										tempVO = benefitVO.getSubList().get(i);
								}
							}
							subList.get(subList.size()-1).setCount(tempVO.getCount());
						}
						
					}while(i>3);
				}
			}
		}
		
			if(grievanDetailsVO != null && stausMap != null && stausMap.size()>0){
		        for (String statusId : stausMap.keySet()) {
		          grievanDetailsVO.getSubList().add(statusMap.get(statusId));
		        }
		     }
		
			if(grievanDetailsVO != null && grievanDetailsVO.getSubList() != null && grievanDetailsVO.getSubList().size()>0){
				Map<String,Map<String,Long>> locationWiseStatusMap = new LinkedHashMap<String, Map<String,Long>>(0);
				for (GrievanceDetailsVO grievanceDetailsVO : grievanDetailsVO.getSubList()) {
					List<GrievanceDetailsVO> statusVOList = grievanceDetailsVO.getSubList();
				
					if(statusVOList != null && statusVOList.size()>0){
						for (GrievanceDetailsVO statusVO : statusVOList) {
							Map<String,Long> grievanceReqTypeMap = new LinkedHashMap<String, Long>(0);
							Long totalCount =statusVO.getCount() != null ?statusVO.getCount():0L;
							Long existingCount =0L;
							if(locationWiseStatusMap.get(statusVO.getLocationName().trim()) != null){
								grievanceReqTypeMap = locationWiseStatusMap.get(statusVO.getLocationName().trim());
								existingCount = grievanceReqTypeMap.get(statusVO.getName().trim()) != null ? grievanceReqTypeMap.get(statusVO.getName().trim()) :0L;
							}
							totalCount = totalCount+existingCount;
							grievanceReqTypeMap.put(statusVO.getName().trim(), totalCount);
							locationWiseStatusMap.put(statusVO.getLocationName().trim(), grievanceReqTypeMap);
						}
					}
				}
				
				if(locationWiseStatusMap != null && locationWiseStatusMap.size()>0){
					GrievanceDetailsVO locationVO =new GrievanceDetailsVO();
					locationVO.setName("TOTAL");
					for (String locationName : locationWiseStatusMap.keySet()) {
						Map<String,Long> grievanceReqTypeMap = locationWiseStatusMap.get(locationName);
						
						if(grievanceReqTypeMap != null && grievanceReqTypeMap.size()>0){
							for (String insuranceTypeStr : grievanceReqTypeMap.keySet()) {
								GrievanceDetailsVO insuranceTypeVO =new GrievanceDetailsVO();
								insuranceTypeVO.setName(insuranceTypeStr);
								insuranceTypeVO.setLocationName(locationName);
								insuranceTypeVO.setId(0L);
								insuranceTypeVO.setCount(grievanceReqTypeMap.get(insuranceTypeStr));									
								locationVO.getSubList().add(insuranceTypeVO);
							}
						}
					}
					grievanDetailsVO.getSubList().add(locationVO);
				}
			}
			
		} catch(Exception e){
			LOG.error("Exception occured in getGrievanceStatusByTypeOfIssueAndCompleteStatusDetails() Method ",e);
		}
		return grievanDetailsVO;
		
	}

	public List<GrievanceDetailsVO> segrigateGrievanceStatusDetails(Map<String,Map<String,Map<String,Long>>> locationsMap){
	    List<GrievanceDetailsVO> returnList = new LinkedList<GrievanceDetailsVO>();
	    try {
	      if(locationsMap != null && locationsMap.size()>0){
	        List<GrievanceDetailsVO> locationWiseVOList = new LinkedList<GrievanceDetailsVO>();
	        for (String locationStr : locationsMap.keySet()) {
	          Map<String,Map<String,Long>> issueTypeMap = locationsMap.get(locationStr);
	          
	          GrievanceDetailsVO locationVO = new GrievanceDetailsVO();
	          locationVO.setLocationName(locationStr);
	          
	          if(issueTypeMap != null && issueTypeMap.size()>0){
	            List<GrievanceDetailsVO> issueTypeVOList = new LinkedList<GrievanceDetailsVO>();
	            for (String issueTypeStr : issueTypeMap.keySet()) {// Party/Govt/Welfare
	              
	              GrievanceDetailsVO issueVO = new GrievanceDetailsVO(); 
	              issueVO.setName(issueTypeStr);
	              Map<String, Long> sttusMap = issueTypeMap.get(issueTypeStr);
	              
	              if(sttusMap != null && sttusMap.size()>0){
	                List<GrievanceDetailsVO> statusVOList = new LinkedList<GrievanceDetailsVO>();
	                for (String status : sttusMap.keySet()) {//status list
	                  
	                  Long count = sttusMap.get(status.toUpperCase());
	                  
	                  GrievanceDetailsVO statusVO = new GrievanceDetailsVO();
	                  statusVO.setCount(count);
	                  statusVO.setName(status.toUpperCase());
	                  statusVOList.add(statusVO);
	                }
	                issueVO.getSubList().addAll(statusVOList);
	              }
	              
	              issueTypeVOList.add(issueVO);
	            }
	            locationVO.getSubList().addAll(issueTypeVOList);
	          }
	          locationWiseVOList.add(locationVO);
	        }
	        
	        if(locationWiseVOList != null && locationWiseVOList.size()>0)
	          returnList.addAll(locationWiseVOList);
	      }
	    } catch (Exception e) {
	      LOG.error("Exception occured in segrigateInsuranceStatusDetails() Method ",e);
	    }
	    return returnList;
	  }
	
	public void updateStatusDetailsForGrievance(Map<String,GrievanceDetailsVO> statusMap,List<GrievanceDetailsVO> issueTypeVOList,String locationName){
	    try {
	      for (GrievanceDetailsVO issueTypeVO : issueTypeVOList) {
	        List<GrievanceDetailsVO> statusVOList = issueTypeVO.getSubList();

	        if(statusVOList != null && statusVOList.size()>0){
	          for (GrievanceDetailsVO statusVO : statusVOList) {
	            GrievanceDetailsVO returnStatusVO = statusMap.get(statusVO.getName());
	            if(returnStatusVO != null){
	              //returnStatusVO.setLocationName(locationName);
	              GrievanceDetailsVO stusVO = new GrievanceDetailsVO();
	              stusVO.setName(issueTypeVO.getName());
	              stusVO.setCount(statusVO.getCount());
	              stusVO.setLocationName(locationName);
	              returnStatusVO.getSubList().add(stusVO);
	            }
	          }
	        }
	      }
	    } catch (Exception e) {
	      LOG.error("Exception occured in updateStatusDetails() Method ",e);
	    }
	  }
	public Map<String, GrievanceDetailsVO> getGrievanceStatusCountsBenifit(Long districtId,Long assemblyId,Long parliamentId){
		Map<String, GrievanceDetailsVO> stausMap = new LinkedHashMap<String, GrievanceDetailsVO>(0);
		try {
			LOG.error("Entered in to getGrievanceStatusCountsBenifit() Method ");
			
			Map<String, GrievanceDetailsVO> statusMap = new LinkedHashMap<String, GrievanceDetailsVO>(0);
			List<String> statusList = userAddressDAO.getCompletedStatus();
			if(statusList != null && statusList.size()>0){
				for (String status : statusList) {
					if(status != null){
						GrievanceDetailsVO vo = new GrievanceDetailsVO();
						vo.setName(status.toUpperCase());
						vo.setCount(0l);
						statusMap.put(status.toUpperCase(), vo);
					}
				}
			}
			String order = IConstants.GRIEVANCE_REQUESTS_STATUS_LIST;
			String[] statusStrArr = order.split(",");
			for (String string : statusStrArr) {
				GrievanceDetailsVO vo = statusMap.get(string.toUpperCase());
				stausMap.put(string.toUpperCase(), vo);
			}
			
			Map<String,Map<String,Map<String, Long>>> locationWiseMap = new LinkedHashMap<String, Map<String,Map<String,Long>>>(0);
			if(assemblyId != null && assemblyId.longValue() > 0l){
			List <Object[]> assembliList = userAddressDAO.getGrievanceRequestCountsByTypeOfIssue(assemblyId, "assembly");
				if(assembliList != null && assembliList.size()>0){
					for (Object[] obj : assembliList) {
					
				        String status = obj[0] != null ? obj[0].toString():"";
				        Long count = Long.valueOf(obj[1] != null ? obj[1].toString():"0");
				        Map<String,Map<String, Long>> assIssTypeMap = null;
				        if(locationWiseMap.get("assembly") == null){
				        	
				        	assIssTypeMap = new LinkedHashMap<String, Map<String,Long>>();
				        	updateStatusMapDetailsForGrievance(statusList,assIssTypeMap,"Benifit");
				        	locationWiseMap.put("assembly",assIssTypeMap);
				        }
				        else{
				        	assIssTypeMap = locationWiseMap.get("assembly");
				        }
				        Map<String, Long> staGetMap = assIssTypeMap.get("Benifit");
				        staGetMap.put(status.toUpperCase(),count);
				        }
					}
				}
			if(parliamentId != null && parliamentId.longValue() > 0l){
				List<Object[]> parliamentList = userAddressDAO.getGrievanceRequestCountsByTypeOfIssue(parliamentId, "parliament");
				if(parliamentList != null && parliamentList.size()>0){
					for (Object[] obj : parliamentList) {
				        String status = obj[0] != null ? obj[0].toString():"";
				        Long count = Long.valueOf(obj[1] != null ? obj[1].toString():"0");
				        Map<String,Map<String, Long>> parIssTypeMap = null;
				        
				        if(locationWiseMap.get("parliament") == null){
				        	parIssTypeMap = new LinkedHashMap<String, Map<String,Long>>();
				        	updateStatusMapDetailsForGrievance(statusList,parIssTypeMap,"Benifit");
				        	locationWiseMap.put("parliament",parIssTypeMap);
				        }
				        else{
				        	parIssTypeMap = locationWiseMap.get("parliament");
				        }
				        Map<String, Long> staGetMap = parIssTypeMap.get("Benifit");
				        staGetMap.put(status.toUpperCase(), count);
					}
				}
			}
			
			if(districtId != null && districtId.longValue() > 0l){
				List<Object[]> districtList = userAddressDAO.getGrievanceRequestCountsByTypeOfIssue(districtId, "district");
				if(districtList != null && districtList.size()>0){
					for (Object[] obj : districtList) {
				        String status = obj[0] != null ? obj[0].toString():"";
				        Long count = Long.valueOf(obj[1] != null ? obj[1].toString():"0");
				        Map<String,Map<String, Long>> disIssTypeMap = null;
				        if(locationWiseMap.get("district") == null){
				        	disIssTypeMap = new LinkedHashMap<String, Map<String,Long>>();
				        	updateStatusMapDetailsForGrievance(statusList,disIssTypeMap,"Benifit");
				        	locationWiseMap.put("district",disIssTypeMap);
				        }
				        else{
				        	disIssTypeMap = locationWiseMap.get("district");
				        }
				        Map<String, Long> staGetMap = disIssTypeMap.get("Benifit");
				        staGetMap.put(status.toUpperCase(), count);
					}
				}
			}
			
			List<GrievanceDetailsVO> locationWiseVOList = segrigateGrievanceStatusDetails(locationWiseMap);
			
			if(locationWiseVOList != null && locationWiseVOList.size()>0){
		        for (GrievanceDetailsVO locationVO : locationWiseVOList) {
		          List<GrievanceDetailsVO> issueTypeVOList = locationVO.getSubList();
		          if(issueTypeVOList != null && issueTypeVOList.size()>0){
		            updateStatusDetailsForGrievance(stausMap, issueTypeVOList,locationVO.getLocationName());
		          }
		        }
		      }
			
		} catch (Exception e) {
			LOG.error("Exception occured in getGrievanceStatusCountsBenifit() Method ",e);
		}
		return stausMap;
		
	}

	/*public List<GrievanceDetailsVO> getAllStatusDetailsForComplaint(Long complaintId)
	{
		List<GrievanceDetailsVO> resultList = new ArrayList<GrievanceDetailsVO>(0);
		try{
			//0.complaintTrackingId, 1.date
			List<Object[]> reqRaisedTime = cadreHealthStatusDAO.getRequestRaisedTimeDetails(complaintId);
			//0.complaintTrackingId, 1.status, 2.date
			List<Object[]> allStatusDetails = cadreHealthStatusDAO.getAllStatusDetails(complaintId);
			
			if(reqRaisedTime != null && reqRaisedTime.size() > 0){
				for (Object[] objects : reqRaisedTime) {
					
					GrievanceDetailsVO reqRaisedvo = new GrievanceDetailsVO();
					Long complaintTrackingId = (Long)objects[0];
					String requestRaisedTime = objects[1] != null?objects[1].toString():"";
					reqRaisedvo.setDateStr(requestRaisedTime);
					resultList.add(reqRaisedvo);
				}
			}
			
			if(allStatusDetails != null && allStatusDetails.size() > 0){
				for (Object[] param : allStatusDetails) {
					
					GrievanceDetailsVO statsDtlsvo = new GrievanceDetailsVO();
					String status = param[1] != null ? param[1].toString():"";
					String dateStr = param[2] != null ? param[2].toString():"";
					statsDtlsvo.setComment(status);
					statsDtlsvo.setDateStr(dateStr);
					resultList.add(statsDtlsvo);
				}
			}
			
		}catch(Exception e){
			LOG.error(" Exception Occured in getAllStatusDetailsForComplaint() method, Exception - ",e);
		}
		return resultList;
	}*/
	
	public List<GrievanceSimpleVO> getGrievanceInsuranceStatusList(Map<String,GrievanceSimpleVO> insuranceStatusMap){
		
		List<GrievanceSimpleVO> finalList = new ArrayList<GrievanceSimpleVO>();
		try{
			
			List<Object[]> insurancestatusList = cadreHealthStatusDAO.getAllGrievanceInsuranceStatus();
			if(insurancestatusList != null && insurancestatusList.size() > 0)
			{
				for(Object[] obj :insurancestatusList )
				{
				GrievanceSimpleVO vo = new GrievanceSimpleVO();
			    
			    vo.setId(Long.valueOf(obj[0] !=null ? obj[0].toString():"0"));//statusId
				vo.setName(obj[1] !=null ?  obj[1].toString():"");//status
				
				insuranceStatusMap.put(vo.getName(),vo);
				finalList.add(vo);
				}
			}
			
			return finalList;
		}catch(Exception e){
			LOG.error("Exception Occured in () getGrievanceInsuranceStatusList method, Exception - ",e);
		}
		return finalList;
	}
	
	public GrievanceSimpleVO getStatusTrackingDetailsOfInsuranceByComplaint(Long complaintId){
		
		GrievanceSimpleVO finalVO = new GrievanceSimpleVO(); 
		try{
			//Long complaintId =21337l;
			
			SimpleDateFormat sdf=new SimpleDateFormat("dd-MM-yyyy");
			Map<String,GrievanceSimpleVO> firstMap = new LinkedHashMap<String, GrievanceSimpleVO>(); 
			List<GrievanceSimpleVO> statusList=getGrievanceInsuranceStatusList(firstMap);
			
			//0.id,1.statusId,2.comment(status),3.inserted time,4.name
			List<Object[]> complaintDetails=cadreHealthStatusDAO.getAllStatusDetailsByComplaint(complaintId,"insurance");
			if(complaintDetails !=null && complaintDetails.size()>0){
				
				//boolean multiStatus=checkMultiStatusForInsurance(complaintDetails);
				boolean multiStatus = true;
				if(multiStatus){
					
					finalVO.setSimpleVOList2(new ArrayList<GrievanceSimpleVO>());
					for(int i=0;i<complaintDetails.size();i++){
						
						Object[] obj=complaintDetails.get(i);
						
						
						if((Long)obj[1]==1l){
							//first map
							String dateString = "";
							//GrievanceSimpleVO notVerifiedVO=firstMap.get("Applied");
							GrievanceSimpleVO notVerifiedVO=firstMap.get("Waiting For Documents");
							if(obj[3] != null && !obj[3].toString().isEmpty())
							 dateString=sdf.format((Date)obj[3]);
							notVerifiedVO.setDateString(dateString);
							notVerifiedVO.setDate((Date)obj[3]);
							//total list
							GrievanceSimpleVO simpleVO=new GrievanceSimpleVO();
							//simpleVO.setName("Applied");
							simpleVO.setName("Waiting For Documents");
							simpleVO.setDate((Date)obj[3]);
							simpleVO.setDateString(dateString);
							simpleVO.setUsername(obj[4] !=null ? obj[4].toString():"");
							
							if(i==(complaintDetails.size()-1)){
								notVerifiedVO.setStatus("current");
								simpleVO.setStatus("current");
								//firstMap.get("Applied").setCurrent(obj[2].toString().toLowerCase());
								firstMap.get("Waiting For Documents").setCurrent(obj[2].toString().toLowerCase());
								
							}
							finalVO.getSimpleVOList2().add(simpleVO);
						}else{
							//first map
							GrievanceSimpleVO VO=firstMap.get(obj[2].toString());
							String dateString=sdf.format((Date)obj[3]);
							VO.setDateString(dateString);
							VO.setDate((Date)obj[3]);
							
							//total list
							GrievanceSimpleVO simpleVO=new GrievanceSimpleVO();
							simpleVO.setName(obj[2].toString());
							simpleVO.setDate((Date)obj[3]);
							simpleVO.setDateString(dateString);
							simpleVO.setUsername(obj[4] !=null ? obj[4].toString():"");
							
							if(i==(complaintDetails.size()-1)){
								VO.setStatus("current");//to show current Status
								simpleVO.setStatus("current");//to show current Status
								//firstMap.get("Applied").setCurrent(obj[2].toString());
								firstMap.get("Waiting For Documents").setCurrent(obj[2].toString());
								
							}
							finalVO.getSimpleVOList2().add(simpleVO);
						}
					}
					finalVO.setSimpleVOList1(new ArrayList<GrievanceSimpleVO>(firstMap.values()));
					//diffreneceBetweenDates(finalVO.getSimpleVOList2());
				}
				else{
					
					for(int i=0;i<complaintDetails.size();i++){  
						Object[] obj=complaintDetails.get(i);
						
						if((Long)obj[1]==1l){
							//GrievanceSimpleVO notVerifiedVO=firstMap.get("Applied");
							GrievanceSimpleVO notVerifiedVO=firstMap.get("Waiting For Documents");
							
							notVerifiedVO.setDateString(sdf.format((Date)obj[3]));
							notVerifiedVO.setDate((Date)obj[3]);
							if(i==(complaintDetails.size()-1)){
								notVerifiedVO.setStatus("current");
								//firstMap.get("Applied").setCurrent("Applied");
								firstMap.get("Waiting For Documents").setCurrent("Waiting For Documents");
								
							}
						}else{
							GrievanceSimpleVO VO=firstMap.get(obj[2].toString());
							if(obj[3] != null && !obj[3].toString().isEmpty())
							{
								VO.setDateString(sdf.format((Date)obj[3]));
								VO.setDate((Date)obj[3]);
							}
							if(i==(complaintDetails.size()-1)){
								VO.setStatus("current");
								//firstMap.get("Applied").setCurrent(obj[2].toString());
								firstMap.get("Waiting For Documents").setCurrent(obj[2].toString());
							}
						}
					}
					finalVO.setSimpleVOList1(new ArrayList<GrievanceSimpleVO>(firstMap.values()));
				}
				
				
				List<GrievanceSimpleVO> finalDateVos=new ArrayList<GrievanceSimpleVO>();
				
				//Applied,Intimated Status Scenario
				if(finalVO.getSimpleVOList1().get(0).getCurrent().equalsIgnoreCase("Waiting For Documents") || finalVO.getSimpleVOList1().get(0).getCurrent().equalsIgnoreCase("Documents Submitted In Party") || finalVO.getSimpleVOList1().get(0).getCurrent().equalsIgnoreCase("Forwarded to Insurance") || finalVO.getSimpleVOList1().get(0).getCurrent().equalsIgnoreCase("Approved - Compensated")){
					for(int i=0;i<finalVO.getSimpleVOList1().size();i++){
						 if( finalVO.getSimpleVOList1().get(i).getName().equalsIgnoreCase("Waiting For Documents") ||finalVO.getSimpleVOList1().get(i).getName().equalsIgnoreCase("Documents Submitted In Party")|| finalVO.getSimpleVOList1().get(i).getName().equalsIgnoreCase("Forwarded to Insurance") || finalVO.getSimpleVOList1().get(i).getName().equalsIgnoreCase("Approved - Compensated")){
							 finalDateVos.add(finalVO.getSimpleVOList1().get(i));
						 }
					}
				}
				// Document Status Scenario
				else if(finalVO.getSimpleVOList1().get(0).getCurrent().equalsIgnoreCase("Waiting for Documents") || finalVO.getSimpleVOList1().get(0).getCurrent().equalsIgnoreCase("Documents Submitted In Party") || finalVO.getSimpleVOList1().get(0).getCurrent().equalsIgnoreCase("Forwarded to Insurance")){
					for(int i=0;i<finalVO.getSimpleVOList1().size();i++){
						
						if(finalVO.getSimpleVOList1().get(0).getCurrent().equalsIgnoreCase("Waiting for Documents")){
							 if( finalVO.getSimpleVOList1().get(i).getName().equalsIgnoreCase("Waiting for Documents") ||finalVO.getSimpleVOList1().get(i).getName().equalsIgnoreCase("Documents Submitted In Party")|| finalVO.getSimpleVOList1().get(i).getName().equalsIgnoreCase("Forwarded to Insurance") || finalVO.getSimpleVOList1().get(i).getName().equalsIgnoreCase("Approved - Compensated")){
								 finalDateVos.add(finalVO.getSimpleVOList1().get(i));
							 }
						}
						else if(finalVO.getSimpleVOList1().get(0).getCurrent().equalsIgnoreCase("Documents Submitted In Party")){
							 if( finalVO.getSimpleVOList1().get(i).getName().equalsIgnoreCase("Waiting for Documents") ||finalVO.getSimpleVOList1().get(i).getName().equalsIgnoreCase("Documents Submitted In Party")|| finalVO.getSimpleVOList1().get(i).getName().equalsIgnoreCase("Forwarded to Insurance") || finalVO.getSimpleVOList1().get(i).getName().equalsIgnoreCase("Approved - Compensated")){
								 finalDateVos.add(finalVO.getSimpleVOList1().get(i));
							 }
						}else{
							 if( finalVO.getSimpleVOList1().get(i).getName().equalsIgnoreCase("Waiting for Documents") ||finalVO.getSimpleVOList1().get(i).getName().equalsIgnoreCase("Documents Submitted In Party")|| finalVO.getSimpleVOList1().get(i).getName().equalsIgnoreCase("Forwarded to Insurance") || finalVO.getSimpleVOList1().get(i).getName().equalsIgnoreCase("Approved - Compensated")){
								 finalDateVos.add(finalVO.getSimpleVOList1().get(i));
							 }
						}
						
						/* if( finalVO.getSimpleVOList1().get(i).getName().equalsIgnoreCase("Applied") ||finalVO.getSimpleVOList1().get(i).getName().equalsIgnoreCase("Intimated")|| finalVO.getSimpleVOList1().get(i).getName().equalsIgnoreCase("Documents Submitted") || finalVO.getSimpleVOList1().get(i).getName().equalsIgnoreCase("Approved") || finalVO.getSimpleVOList1().get(i).getName().equalsIgnoreCase("Cheque Received") ){
							 finalDateVos.add(finalVO.getSimpleVOList1().get(i));
						 }*/
					}
				}
				
				//Rejected and Not Eligible
				else if(finalVO.getSimpleVOList1().get(0).getCurrent().equalsIgnoreCase("Rejected") || finalVO.getSimpleVOList1().get(0).getCurrent().equalsIgnoreCase("Not Eligible")){
					for(int i=0;i<finalVO.getSimpleVOList1().size();i++){
						
						if(finalVO.getSimpleVOList1().get(0).getCurrent().equalsIgnoreCase("Rejected")){
							if( finalVO.getSimpleVOList1().get(i).getName().equalsIgnoreCase("Waiting for Documents") ||finalVO.getSimpleVOList1().get(i).getName().equalsIgnoreCase("Documents Submitted In Party") || finalVO.getSimpleVOList1().get(i).getName().equalsIgnoreCase("Rejected")){
								 finalDateVos.add(finalVO.getSimpleVOList1().get(i));
							 }
						}else if(finalVO.getSimpleVOList1().get(0).getCurrent().equalsIgnoreCase("Not Eligible")){
							if( finalVO.getSimpleVOList1().get(i).getName().equalsIgnoreCase("Waiting for Documents") ||finalVO.getSimpleVOList1().get(i).getName().equalsIgnoreCase("Documents Submitted In Party") || finalVO.getSimpleVOList1().get(i).getName().equalsIgnoreCase("Not Eligible")){
								 finalDateVos.add(finalVO.getSimpleVOList1().get(i));
							 }
						}
						
					}
				}
				finalVO.setSimpleVOList1(finalDateVos);
				
				List<GrievanceSimpleVO> newStatusList = new ArrayList<GrievanceSimpleVO>();
				if(finalVO.getSimpleVOList1() !=null && finalVO.getSimpleVOList1().size()>0){
					
					
					int index = getIndexByCurrent(finalVO.getSimpleVOList1());
					
					for(int i=0;i<finalVO.getSimpleVOList1().size();i++){
						if(i<index){
							GrievanceSimpleVO vo = finalVO.getSimpleVOList1().get(i);
							if(vo.getDateString() !=null){
								newStatusList.add(vo);
							}
						}
						else{
							GrievanceSimpleVO vo1 = finalVO.getSimpleVOList1().get(i);
							newStatusList.add(vo1);
						}
					}
				}
				
				if(newStatusList !=null && newStatusList.size()>0){
					finalVO.setSimpleVOList1(newStatusList);
				}
				
				diffreneceBetweenDates(finalVO.getSimpleVOList1());
			
			}
			else{
				List<String> status=cadreHealthStatusDAO.getStatusBycomplaintIdForInsurance(complaintId);
				if(status!=null && status.size()>0){
					finalVO.setOnlystatus(status.get(0));
				}
			}
			
			
		return finalVO;
		
		}catch (Exception e) {
			LOG.error("Exception Occured in () getStatusTrackingDetailsOfInsuranceByComplaint method, Exception - ",e);
		}
		
		return finalVO;
	
	}
	
	public int getIndexByCurrent(List<GrievanceSimpleVO> simpleVoList){
		
		int k=0;
		try{
			for(int i=0;i<simpleVoList.size();i++){
				GrievanceSimpleVO vo = simpleVoList.get(i);
				if(vo.getStatus() !=null && !vo.getStatus().isEmpty() && vo.getStatus().equalsIgnoreCase("current")){
					k=i;
				}
			}
			return k;
		}catch(Exception e){
			LOG.error("Exception Occured in () getIndexByCurrent method, Exception - ",e);
		}
		return k;
	}
	
	public GrievanceSimpleVO getAllStatusDetailsByComplaint(Long complaintId){
		
		GrievanceSimpleVO finalVO=new GrievanceSimpleVO();
		try{
			SimpleDateFormat sdf=new SimpleDateFormat("dd-MM-yyyy");
			Map<String,GrievanceSimpleVO> firstMap=new LinkedHashMap<String,GrievanceSimpleVO>();  
			
			
			GrievanceSimpleVO vo=new GrievanceSimpleVO();
			vo.setName("not verified");
			firstMap.put("not verified",vo);
			
			GrievanceSimpleVO vo1=new GrievanceSimpleVO();
			vo1.setName("in progress");
			firstMap.put("in progress",vo1);
			
			GrievanceSimpleVO vo2=new GrievanceSimpleVO();
			vo2.setName("not eligible");
			firstMap.put("not eligible",vo2);
			
			GrievanceSimpleVO vo3=new GrievanceSimpleVO();
			vo3.setName("not possible");
			firstMap.put("not possible",vo3);
			
			GrievanceSimpleVO vo4=new GrievanceSimpleVO();
			vo4.setName("approved");
			firstMap.put("approved",vo4);
			
			GrievanceSimpleVO vo5=new GrievanceSimpleVO();
			vo5.setName("completed");
			firstMap.put("completed",vo5);
			List<Object[]> complaintDetails=cadreHealthStatusDAO.getAllStatusDetailsByComplaint(complaintId,"grievance");
			if(complaintDetails!=null && complaintDetails.size()>0){
				//boolean multiStatus=checkMultiStatus(complaintDetails);
				boolean multiStatus= true;
				if(multiStatus){
					
					finalVO.setSimpleVOList2(new ArrayList<GrievanceSimpleVO>());
					for(int i=0;i<complaintDetails.size();i++){
						
						Object[] obj=complaintDetails.get(i);
						
						if((Long)obj[1]==1l){
							//first map
							String dateString = "";
							GrievanceSimpleVO notVerifiedVO=firstMap.get("not verified");
							if(obj[3] != null && !obj[3].toString().isEmpty())
							 dateString=sdf.format((Date)obj[3]);
							notVerifiedVO.setDateString(dateString);
							notVerifiedVO.setDate((Date)obj[3]);
							//total list
							GrievanceSimpleVO simpleVO=new GrievanceSimpleVO();
							simpleVO.setName("not verified");
							simpleVO.setDate((Date)obj[3]);
							simpleVO.setDateString(dateString);
							/*String firstName=obj[4] != null ? obj[4].toString() : "";
							String lastName=obj[5] != null ? obj[5].toString() : "";
							simpleVO.setUsername(firstName +" " + lastName);*/
							
							simpleVO.setUsername(obj[4] !=null ? obj[4].toString():"");
							
							if(i==(complaintDetails.size()-1)){
								notVerifiedVO.setStatus("current");
								simpleVO.setStatus("current");
								firstMap.get("not verified").setCurrent(obj[2]!= null?obj[2].toString().toLowerCase():"NOT VERIFIED");
							}
							finalVO.getSimpleVOList2().add(simpleVO);
						}else{
							//first map
							GrievanceSimpleVO VO=firstMap.get(obj[2].toString().toLowerCase());
							String dateString=sdf.format((Date)obj[3]);
							VO.setDateString(dateString);
							VO.setDate((Date)obj[3]);
							//total list
							GrievanceSimpleVO simpleVO=new GrievanceSimpleVO();
							simpleVO.setName(obj[2].toString());
							simpleVO.setDate((Date)obj[3]);
							simpleVO.setDateString(dateString);
							/*String firstName=obj[4] != null ? obj[4].toString() : "";
							String lastName=obj[5] != null ? obj[5].toString() : "";
							simpleVO.setUsername(firstName +" " + lastName);*/
							
							simpleVO.setUsername(obj[4] !=null ? obj[4].toString():"");
							
							if(i==(complaintDetails.size()-1)){
								VO.setStatus("current");
								simpleVO.setStatus("current");
								firstMap.get("not verified").setCurrent(obj[2]!= null?obj[2].toString().toLowerCase():"NOT VERIFIED");
							}
							finalVO.getSimpleVOList2().add(simpleVO);
						}
					}
					finalVO.setSimpleVOList1(new ArrayList<GrievanceSimpleVO>(firstMap.values()));
					diffreneceBetweenDates(finalVO.getSimpleVOList2());
				}else{
					
					for(int i=0;i<complaintDetails.size();i++){
						
						Object[] obj=complaintDetails.get(i);
						if((Long)obj[1]==1l){
							GrievanceSimpleVO notVerifiedVO=firstMap.get("not verified");
							notVerifiedVO.setDateString(sdf.format((Date)obj[3]));
							notVerifiedVO.setDate((Date)obj[3]);
							if(i==(complaintDetails.size()-1)){
								notVerifiedVO.setStatus("current");
								firstMap.get("not verified").setCurrent("not verified");
							}
						}else{
							GrievanceSimpleVO VO=firstMap.get(obj[2].toString().toLowerCase());
							if(obj[3] != null && !obj[3].toString().isEmpty())
							{
							VO.setDateString(sdf.format((Date)obj[3]));
							VO.setDate((Date)obj[3]);
							}
							if(i==(complaintDetails.size()-1)){
								VO.setStatus("current");
								firstMap.get("not verified").setCurrent(obj[2]!= null?obj[2].toString().toLowerCase():"NOT VERIFIED");
							}
						}
					}
					if(firstMap.get("not verified").getCurrent().equalsIgnoreCase("completed")){
						firstMap.remove("approved");   
					}
					finalVO.setSimpleVOList1(new ArrayList<GrievanceSimpleVO>(firstMap.values()));
				}	
					
					List<GrievanceSimpleVO> finalDateVos=new ArrayList<GrievanceSimpleVO>();
					
					if(finalVO.getSimpleVOList1().get(0).getCurrent().equalsIgnoreCase("not verified") || finalVO.getSimpleVOList1().get(0).getCurrent().equalsIgnoreCase("in progress")){
						for(int i=0;i<finalVO.getSimpleVOList1().size();i++){
							 if( finalVO.getSimpleVOList1().get(i).getName().equalsIgnoreCase("not verified") ||finalVO.getSimpleVOList1().get(i).getName().equalsIgnoreCase("in progress") || finalVO.getSimpleVOList1().get(i).getName().equalsIgnoreCase("approved") || finalVO.getSimpleVOList1().get(i).getName().equalsIgnoreCase("completed")){
								 finalDateVos.add(finalVO.getSimpleVOList1().get(i));
							 }
						}
					}
					else if(finalVO.getSimpleVOList1().get(0).getCurrent().equalsIgnoreCase("approved") || finalVO.getSimpleVOList1().get(0).getCurrent().equalsIgnoreCase("completed")){
						for(int i=0;i<finalVO.getSimpleVOList1().size();i++){
							if(finalVO.getSimpleVOList1().get(0).getCurrent().equalsIgnoreCase("approved")){
								 if(finalVO.getSimpleVOList1().get(i).dateString!=null){
									 finalDateVos.add(finalVO.getSimpleVOList1().get(i));
								 }
							}else if(finalVO.getSimpleVOList1().get(0).getCurrent().equalsIgnoreCase("completed")){
								 if(finalVO.getSimpleVOList1().get(i).dateString!=null){
									 finalDateVos.add(finalVO.getSimpleVOList1().get(i));
								 }
							}
						}
					}
					else if(finalVO.getSimpleVOList1().get(0).getCurrent().equalsIgnoreCase("not possible") || finalVO.getSimpleVOList1().get(0).getCurrent().equalsIgnoreCase("not eligible")){
						for(int i=0;i<finalVO.getSimpleVOList1().size();i++){
								 if(finalVO.getSimpleVOList1().get(i).dateString!=null){
									 finalDateVos.add(finalVO.getSimpleVOList1().get(i));
								 }
							}
					}
					finalVO.setSimpleVOList1(finalDateVos);
					//findinig differnce between dates.
					
					diffreneceBetweenDates(finalVO.getSimpleVOList1());
					
				}else{
					List<String> status=cadreHealthStatusDAO.getCompletedStatusBycomplaintId(complaintId);
					if(status!=null && status.size()>0){
						finalVO.setOnlystatus(status.get(0));
					}
				}
			
		
		}catch(Exception e){
			LOG.error(" Exception Occured in getAllStatusDetailsByComplaint() method, Exception - ",e);
		}
		return finalVO;
	}
	
	public void diffreneceBetweenDates(List<GrievanceSimpleVO> list){
		try{
			for(int i=0;i<list.size();i++){
				
				Date d2=null;
				Date d1=null;
				if(list.get(i).getStatus()!=null &&list.get(i).getStatus().equalsIgnoreCase("current")){
					d2=new DateUtilService().getCurrentDateAndTime();  
					d1=list.get(i).getDate();
				}else{
					if(i!=(list.size()-1)){
						d2=list.get(i+1).getDate();
						d1=list.get(i).getDate();
					}
				}
				if(d2!=null && d1!=null){
				 long diff = d2.getTime() - d1.getTime();
				 long diffDays = diff / (24 * 60 * 60 * 1000);
				 list.get(i).setType(diffDays+" days");
				}
		}
		}catch(Exception e){
			LOG.error(" Exception Occured in diffreneceBetweenDates() method, Exception - ",e);
		}
	}
	
	public List<GrievanceDetailsVO> getComplaintsDetailsForGrievanceByLocationAndStatus(Long locationId,String locationType,String status,String issueType){
		List<GrievanceDetailsVO> returnList = new ArrayList<GrievanceDetailsVO>();
		try {
			List<Object[]> list = null;
			
			if(issueType != null && issueType.equalsIgnoreCase("Benefit"))
				list = cadreHealthStatusDAO.getGrievanceRequestDetailsForBenifits(locationId, locationType, status);
			else
				list = cadreHealthStatusDAO.getComplaintsDetailsForGrievanceByLocationAndStatus(locationId, locationType, status, issueType);
			
			if(list != null && list.size() > 0){
				for (Object[] obj : list) {
					GrievanceDetailsVO vo = new GrievanceDetailsVO();
					
					vo.setFirstName(obj[0] != null ? obj[0].toString():"");
					//vo.setMembershipNo(obj[1] != null ? obj[1].toString():"");
					vo.setMobileNo(obj[1] != null ? obj[1].toString():"");
					//vo.setSubject(obj[3] != null ? obj[3].toString():"");
					//vo.setDescription(obj[4] != null ? obj[4].toString():"");
					vo.setComplaintId(Long.valueOf(obj[2] != null ? obj[2].toString():""));
					vo.setRaisedDate(obj[3] != null ? obj[3].toString():"");
					vo.setTypeOfIssue(obj[4] != null ? obj[4].toString():"");
					//vo.setStatusId(Long.valueOf(obj[6] != null ? obj[6].toString():""));
					vo.setStatus(obj[5] != null ? obj[5].toString():"");
					vo.setUpdatedDate(obj[6] != null ? obj[6].toString():"");
					//vo.setTdpCadreId(Long.valueOf(obj[9] != null ? obj[9].toString():"0"));
					vo.setSubject(obj[7] != null ? obj[7].toString():"");
					vo.setDescription(obj[8] != null ? obj[8].toString():"");
					if(issueType != null && issueType.equalsIgnoreCase("Benefit"))
						vo.setApprovedAmount(obj[9] != null ? obj[9].toString():"0");
					vo.setLocationName(obj[9] != null ? obj[9].toString():"");
					vo.setConstituency(obj[10] != null ? obj[10].toString():"");
					vo.setMandalName(obj[11] != null ? obj[11].toString():"");
					vo.setVillageName(obj[12] != null ? obj[12].toString():"");
					
					returnList.add(vo);
				}
			}
			
		} catch (Exception e) {
			LOG.error(" Exception Occured in getComplaintsDetailsByLocationAndStatus() method, Exception - ",e);
		}
		return returnList;
	}
	public VerifierVO getIVRSurveysOnCandidateAreaCount(Long districtId){
		VerifierVO verifierVO = new VerifierVO();
		
			Long count = ivrSurveyAnswerDAO.getIvrSurveyCountByDistrict(districtId);
			if(count != null && count.longValue() > 0){
				verifierVO.setTotalCount(count);
			}
		return verifierVO;
	}
	public VerifierVO getMatchedVerifierVO(List<VerifierVO> returnList,Long id)
	{
		try{
			if(returnList == null || returnList.size() == 0)
				return null;
			for(VerifierVO vo : returnList)
			{
				if(vo.getId().longValue()== id.longValue())
					return vo;
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return null;
	}
	
	public VerifierVO getIVRSurveysOnCandidateAreaDetails(Long districtId,Long constiId,Long parliamentId,Long boothId){
		
		VerifierVO verifierVO = new VerifierVO();
		Long mandalId = 0l;
		Long localEleId = 0l;
			
			List<Object[]> districtList = ivrSurveyAnswerDAO.getOptionsCountByQuestionIdsForLocation(IConstants.DISTRICT,districtId);
			if(districtList != null && districtList.size() > 0){
				setIVRSurveysOnCandidateAreaDetails(districtList,verifierVO,IConstants.DISTRICT,boothId);
			}
			
			List<Object[]> assemblyList = ivrSurveyAnswerDAO.getOptionsCountByQuestionIdsForLocation(IConstants.ASSEMBLY_CONSTITUENCY_TYPE,constiId);
			if(assemblyList != null && assemblyList.size() > 0){
				setIVRSurveysOnCandidateAreaDetails(assemblyList,verifierVO,IConstants.ASSEMBLY_CONSTITUENCY_TYPE,boothId);
			}
			
			List<Object[]> parlmentList = ivrSurveyAnswerDAO.getOptionsCountByQuestionIdsForLocation(IConstants.PARLIAMENT_CONSTITUENCY_TYPE,parliamentId);
			if(parlmentList != null && parlmentList.size() > 0){
				setIVRSurveysOnCandidateAreaDetails(parlmentList,verifierVO,IConstants.PARLIAMENT_CONSTITUENCY_TYPE,boothId);
			}
			if(boothId != null && boothId > 0)
			{
				
				List<Object[]> mandalMunciList = boothDAO.getMandalMuncipalityIds(boothId);
				if(mandalMunciList != null && mandalMunciList.size() > 0)
				{
					for(Object[] params : mandalMunciList)
					{
						if(params[0] != null)
							localEleId = params[0] != null ? (Long)params[0] : 0l;
						else
							mandalId = params[1] != null ? (Long)params[1] : 0l;
					}
				}
					List<Object[]> mandalList = ivrSurveyAnswerDAO.getOptionsCountByQuestionIdsForLocation(IConstants.MANDAL,mandalId);
					if(mandalList != null && mandalList.size() > 0){
						setIVRSurveysOnCandidateAreaDetails(mandalList,verifierVO,IConstants.MANDAL,boothId);
					}
					List<Object[]> localbodyList = ivrSurveyAnswerDAO.getOptionsCountByQuestionIdsForLocation(IConstants.LOCALELECTIONBODY,localEleId);
					if(localbodyList != null && localbodyList.size() > 0){
						setIVRSurveysOnCandidateAreaDetails(localbodyList,verifierVO,IConstants.MANDAL,boothId);
					}
			}
			if(verifierVO.getVerifierVOList() != null && verifierVO.getVerifierVOList().size() > 0)
			for(VerifierVO vo : verifierVO.getVerifierVOList())//Survey
			{
				/*if(vo.getVerifierVOList() != null && vo.getVerifierVOList().size() > 0)
				for(VerifierVO questionVO : vo.getVerifierVOList())//Round
				{*/
					if(vo.getVerifierVOList() != null && vo.getVerifierVOList().size() > 0)
					for(VerifierVO questionVO : vo.getVerifierVOList())//Question
					{
						if(questionVO.getVerifierVOList() != null && questionVO.getVerifierVOList().size() > 0)
						for(VerifierVO location : questionVO.getVerifierVOList())//location
						{
							if(location.getVerifierVOList() != null && location.getVerifierVOList().size() > 0)
							for(VerifierVO optionVO : location.getVerifierVOList())//option
							{
								if(location.getCount() != null && location.getCount() > 0)
								 {
								 String percentage = (new BigDecimal(optionVO.getCount()*(100.0)/location.getCount())).setScale(2, BigDecimal.ROUND_HALF_UP).toString();
								 optionVO.setPercentage(percentage);
								 }
								 else
									 optionVO.setPercentage("0.0"); 
							}
						}
					}
				//}
			}
		return verifierVO;
	}
	public void setIVRSurveysOnCandidateAreaDetails(List<Object[]> list,VerifierVO verifierVO,String locType,Long boothId)
	{
		
		try{
			if(list != null && list.size() > 0)
			{		
					SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
					for(Object[] params : list)
					 {
						 VerifierVO surveyVO = getMatchedVerifierVO(verifierVO.getVerifierVOList(),(Long)params[1]);
						 
						 if(surveyVO == null)
						 {
							 surveyVO = new VerifierVO();
							 surveyVO.setId((Long)params[1]);
							 surveyVO.setName(params[2] != null ? params[2].toString() :"");
							 surveyVO.setDate(params[10] != null ? sdf.format((Date)(params[10])) :"");
							 verifierVO.getVerifierVOList().add(surveyVO);
						 }
						 /*VerifierVO roundVO = getMatchedVerifierVO(surveyVO.getVerifierVOList(),(Long)params[3]);
						 
						 if(roundVO == null)
						 {
							 roundVO = new VerifierVO();
							 roundVO.setId((Long)params[3]);
							 roundVO.setName(params[4] != null ? params[4].toString() :"");
							 surveyVO.getVerifierVOList().add(roundVO);
						 }*/
						 
						 VerifierVO questionVO = getMatchedVerifierVO(surveyVO.getVerifierVOList(),(Long)params[5]);
						 
						 if(questionVO == null)
						 {
							 questionVO = new VerifierVO();
							 questionVO.setId((Long)params[5]);
							 questionVO.setName(params[6] != null ? params[6].toString() :"");
							
							 questionVO.setVerifierVOList(setStaticLocationTypes((Long)params[9], boothId));
							 surveyVO.getVerifierVOList().add(questionVO);
						 }
						 VerifierVO locTypeVo = getMatchedVerifierVOForType(questionVO.getVerifierVOList(),locType);
						 
						 if(locTypeVo != null)
						 {
							 locTypeVo.setCount(params[0] != null ? (Long)params[0] + locTypeVo.getCount() : 0l);
							 VerifierVO optionVO = getMatchedVerifierVO(locTypeVo.getVerifierVOList(),(Long)params[7]);
							 if(optionVO != null)
							 {
								 
								 optionVO.setCount(params[0] != null ? (Long)params[0] + optionVO.getCount() : 0l);
								 optionVO.setPercentage("0.0"); 
								
							 }
						 }
					 }
					
					
				}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	
	public List<VerifierVO> setOptionsForQuestion(List<Object[]> list)
	{
		List<VerifierVO> optionsList = new ArrayList<VerifierVO>();
		
		 if(list != null && list.size() > 0)
		 {
			 for(Object[] params : list)
			 {
				 VerifierVO vo = new VerifierVO();
				 vo.setId((Long)params[0]);
				 vo.setName(params[1] != null ? params[1].toString() : "");
				 optionsList.add(vo);
			 }
		 }
		return optionsList;
		 
		 
	}
	public List<VerifierVO> setStaticLocationTypes(Long SurveyquestionId,Long boothId)
	{
		List<VerifierVO> list = new ArrayList<VerifierVO>();
		 List<Object[]> list1 = ivrSurveyQuestionOptionDAO.getOptionsForSurveyQuestion(SurveyquestionId);
		if(boothId != null && boothId > 0)
		{
		VerifierVO vo = new VerifierVO();
		vo.setName(IConstants.MANDAL);
		vo.setVerifierVOList(setOptionsForQuestion(list1));
		list.add(vo);
		}
		
		VerifierVO vo1 = new VerifierVO();
		vo1.setName(IConstants.ASSEMBLY_CONSTITUENCY_TYPE);
		vo1.setVerifierVOList(setOptionsForQuestion(list1));
		list.add(vo1);
		VerifierVO vo2 = new VerifierVO();
		vo2.setName(IConstants.PARLIAMENT_CONSTITUENCY_TYPE);
		vo2.setVerifierVOList(setOptionsForQuestion(list1));
		list.add(vo2);
		VerifierVO vo3 = new VerifierVO();
		vo3.setVerifierVOList(setOptionsForQuestion(list1));
		vo3.setName(IConstants.DISTRICT);
		list.add(vo3);
		return list;
	}
	
	public VerifierVO getMatchedVerifierVOForType(List<VerifierVO> returnList,String type)
	{
		try{
			if(returnList == null || returnList.size() == 0)
				return null;
			for(VerifierVO vo : returnList)
			{
				if(type.equalsIgnoreCase(vo.getName()))
					return vo;
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return null;
	}
	
	public List<GrievanceSimpleVO> getApprovedAmountDetailsByLocation(Long assemblyId,Long parliamentId,Long districtId){
		List<GrievanceSimpleVO> returnvoList = new ArrayList<GrievanceSimpleVO>(0);
		
		try {
			if(assemblyId != null && assemblyId.longValue() > 0l){
				Map<String,GrievanceSimpleVO> typeOfIssueMap = new LinkedHashMap<String, GrievanceSimpleVO>();
				List<Object[]> list = cadreHealthStatusDAO.getApprovedAmountDetailsByLocation(assemblyId, "assembly");
				if(list != null && list.size() > 0){
					for (Object[] obj : list) {
						String typeOfIssue = obj[0] != null ? obj[0].toString():"";
						GrievanceSimpleVO vo = new GrievanceSimpleVO();
						
						vo.setTypeOfIssue(typeOfIssue);
						vo.setIssueType(obj[1] != null ? obj[1].toString():"");
						vo.setCount(Long.valueOf(obj[2] != null ? obj[2].toString():"0"));
						vo.setApprovedAmount(obj[3] != null ? obj[3].toString():"0");
						
						GrievanceSimpleVO issuevo = typeOfIssueMap.get(typeOfIssue);
						if(issuevo != null){
							issuevo.getSimpleVOList1().add(vo);
						}
						else{
							issuevo = new GrievanceSimpleVO();
							issuevo.setName(typeOfIssue);
							issuevo.getSimpleVOList1().add(vo);
							typeOfIssueMap.put(typeOfIssue, issuevo);
						}
					}
				}
				GrievanceSimpleVO assemblyvo = new GrievanceSimpleVO();
				assemblyvo.setName("assembly");
				assemblyvo.setId(assemblyId);
				assemblyvo.getSimpleVOList1().addAll(typeOfIssueMap.values());
				returnvoList.add(assemblyvo);
			}
			
			if(parliamentId != null && parliamentId.longValue() > 0l){
				Map<String,GrievanceSimpleVO> typeOfIssueMap = new LinkedHashMap<String, GrievanceSimpleVO>();
				List<Object[]> list = cadreHealthStatusDAO.getApprovedAmountDetailsByLocation(parliamentId, "parliament");
				if(list != null && list.size() > 0){
					for (Object[] obj : list) {
						String typeOfIssue = obj[0] != null ? obj[0].toString():"";
						GrievanceSimpleVO vo = new GrievanceSimpleVO();
						
						vo.setTypeOfIssue(typeOfIssue);
						vo.setIssueType(obj[1] != null ? obj[1].toString():"");
						vo.setCount(Long.valueOf(obj[2] != null ? obj[2].toString():"0"));
						vo.setApprovedAmount(obj[3] != null ? obj[3].toString():"0");
						
						GrievanceSimpleVO issuevo = typeOfIssueMap.get(typeOfIssue);
						if(issuevo != null){
							issuevo.getSimpleVOList1().add(vo);
						}
						else{
							issuevo = new GrievanceSimpleVO();
							issuevo.setName(typeOfIssue);
							issuevo.getSimpleVOList1().add(vo);
							typeOfIssueMap.put(typeOfIssue, issuevo);
						}
					}
				}
				GrievanceSimpleVO parlivo = new GrievanceSimpleVO();
				parlivo.setName("parliament");
				parlivo.setId(parliamentId);
				parlivo.getSimpleVOList1().addAll(typeOfIssueMap.values());
				returnvoList.add(parlivo);
			}
			
			if(districtId != null && districtId.longValue() > 0l){
				Map<String,GrievanceSimpleVO> typeOfIssueMap = new LinkedHashMap<String, GrievanceSimpleVO>();
				List<Object[]> list = cadreHealthStatusDAO.getApprovedAmountDetailsByLocation(districtId, "district");
				if(list != null && list.size() > 0){
					for (Object[] obj : list) {
						String typeOfIssue = obj[0] != null ? obj[0].toString():"";
						GrievanceSimpleVO vo = new GrievanceSimpleVO();
						
						vo.setTypeOfIssue(typeOfIssue);
						vo.setIssueType(obj[1] != null ? obj[1].toString():"");
						vo.setCount(Long.valueOf(obj[2] != null ? obj[2].toString():"0"));
						vo.setApprovedAmount(obj[3] != null ? obj[3].toString():"0");
						
						GrievanceSimpleVO issuevo = typeOfIssueMap.get(typeOfIssue);
						if(issuevo != null){
							issuevo.getSimpleVOList1().add(vo);
						}
						else{
							issuevo = new GrievanceSimpleVO();
							issuevo.setName(typeOfIssue);
							issuevo.getSimpleVOList1().add(vo);
							typeOfIssueMap.put(typeOfIssue, issuevo);
						}
					}
				}
				GrievanceSimpleVO distvo = new GrievanceSimpleVO();
				distvo.setName("district");
				distvo.setId(districtId);
				distvo.getSimpleVOList1().addAll(typeOfIssueMap.values());
				returnvoList.add(distvo);
			}
		} catch (Exception e) {
			LOG.error("Exception raised in getApprovedAmountDetailsByLocation  method in CadreDetailsService.",e);
		}
		return returnvoList;
	}
	
	public List<GrievanceSimpleVO> getApprovedAmountDetailsForGovtAndWilfareByLocation(Long assemblyId,Long parliamentId,Long districtId){
		List<GrievanceSimpleVO> returnList = new ArrayList<GrievanceSimpleVO>();
		try {
			if(assemblyId != null && assemblyId.longValue() > 0l){
				GrievanceSimpleVO assemblyVo = new GrievanceSimpleVO();
				assemblyVo.setName("assembly");
				assemblyVo.setId(assemblyId);
				
				Map<String,Long> otherBenifitMap = new LinkedHashMap<String, Long>();
				List<Object[]> otherBenifit = cadreHealthStatusDAO.getApprovedAmountDetailsForWilfareByLocation(assemblyId, "assembly");
				if(otherBenifit != null && otherBenifit.size() > 0){
					for (Object[] obj : otherBenifit) {
						String issueType = obj[0] != null ? obj[0].toString():"";
						Long count = Long.valueOf(obj[1] != null ? obj[1].toString():"0");
						otherBenifitMap.put(issueType, count);
					}
				}
				List<Object[]> list = cadreHealthStatusDAO.getApprovedAmountDetailsForGovtAndWilfareByLocation(assemblyId, "assembly");
				if(list != null && list.size() > 0){
					for (Object[] obj : list) {
						GrievanceSimpleVO vo = new GrievanceSimpleVO();
						String issType = obj[0] != null ? obj[0].toString():"";
						
						vo.setName(issType);
						vo.setCount(Long.valueOf(obj[1] != null ? obj[1].toString():"0"));
						vo.setApprovedAmount(obj[2] != null ? obj[2].toString():"");
						vo.setOtherBenifitCount(otherBenifitMap.get(issType));
						
						assemblyVo.getSimpleVOList1().add(vo);
					}
				}
				returnList.add(assemblyVo);
			}
			
			if(parliamentId != null && parliamentId.longValue() > 0l){
				GrievanceSimpleVO parlVo = new GrievanceSimpleVO();
				parlVo.setName("parliament");
				parlVo.setId(parliamentId);
				
				Map<String,Long> otherBenifitMap = new LinkedHashMap<String, Long>();
				List<Object[]> otherBenifit = cadreHealthStatusDAO.getApprovedAmountDetailsForWilfareByLocation(parliamentId, "parliament");
				if(otherBenifit != null && otherBenifit.size() > 0){
					for (Object[] obj : otherBenifit) {
						String issueType = obj[0] != null ? obj[0].toString():"";
						Long count = Long.valueOf(obj[1] != null ? obj[1].toString():"0");
						otherBenifitMap.put(issueType, count);
					}
				}
				List<Object[]> list = cadreHealthStatusDAO.getApprovedAmountDetailsForGovtAndWilfareByLocation(parliamentId, "parliament");
				if(list != null && list.size() > 0){
					for (Object[] obj : list) {
						GrievanceSimpleVO vo = new GrievanceSimpleVO();
						String issType = obj[0] != null ? obj[0].toString():"";
						
						vo.setName(issType);
						vo.setCount(Long.valueOf(obj[1] != null ? obj[1].toString():"0"));
						vo.setApprovedAmount(obj[2] != null ? obj[2].toString():"");
						vo.setOtherBenifitCount(otherBenifitMap.get(issType));
						
						parlVo.getSimpleVOList1().add(vo);
					}
				}
				returnList.add(parlVo);
			}
			
			if(districtId != null && districtId.longValue() > 0l){
				GrievanceSimpleVO distVo = new GrievanceSimpleVO();
				distVo.setName("district");
				distVo.setId(districtId);
				
				Map<String,Long> otherBenifitMap = new LinkedHashMap<String, Long>();
				List<Object[]> otherBenifit = cadreHealthStatusDAO.getApprovedAmountDetailsForWilfareByLocation(parliamentId, "parliament");
				if(otherBenifit != null && otherBenifit.size() > 0){
					for (Object[] obj : otherBenifit) {
						String issueType = obj[0] != null ? obj[0].toString():"";
						Long count = Long.valueOf(obj[1] != null ? obj[1].toString():"0");
						otherBenifitMap.put(issueType, count);
					}
				}
				List<Object[]> list = cadreHealthStatusDAO.getApprovedAmountDetailsForGovtAndWilfareByLocation(districtId, "district");
				if(list != null && list.size() > 0){
					for (Object[] obj : list) {
						GrievanceSimpleVO vo = new GrievanceSimpleVO();
						String issType = obj[0] != null ? obj[0].toString():"";
						
						vo.setName(issType);
						vo.setCount(Long.valueOf(obj[1] != null ? obj[1].toString():"0"));
						vo.setApprovedAmount(obj[2] != null ? obj[2].toString():"");
						vo.setOtherBenifitCount(otherBenifitMap.get(issType));
						
						distVo.getSimpleVOList1().add(vo);
					}
				}
				returnList.add(distVo);
			}
			
			
		} catch (Exception e) {
			LOG.error("Exception raised in getApprovedAmountDetailsForGovtAndWilfareByLocation  method in CadreDetailsService.",e);
		}
		return returnList;
	}
	
	public List<GrievanceDetailsVO> getGrievanceBenifitsComplaintsInfoByLocation(Long locationId,String locationType,String typeOfIssue,String otherBenifit){
		List<GrievanceDetailsVO> returnList = new ArrayList<GrievanceDetailsVO>();
		try {
			List<Object[]> list = cadreHealthStatusDAO.getGrievanceBenifitsDetailsByLocation(locationId, locationType, typeOfIssue, otherBenifit);
			if(list != null && list.size() > 0){
				for (Object[] obj : list) {
					GrievanceDetailsVO vo = new GrievanceDetailsVO();
					
					vo.setFirstName(obj[0] != null ? obj[0].toString():"");
					vo.setMobileNo(obj[1] != null ? obj[1].toString():"");
					vo.setComplaintId(Long.valueOf(obj[2] != null ? obj[2].toString():""));
					vo.setRaisedDate(obj[3] != null ? obj[3].toString():"");
					vo.setTypeOfIssue(obj[4] != null ? obj[4].toString():"");
					vo.setUpdatedDate(obj[5] != null ? obj[5].toString():"");
					vo.setSubject(obj[6] != null ? obj[6].toString():"");
					vo.setDescription(obj[7] != null ? obj[7].toString():"");
					vo.setLocationName(obj[8] != null ? obj[8].toString():"");
					vo.setConstituency(obj[9] != null ? obj[9].toString():"");
					vo.setVillageName(obj[10] != null ? obj[10].toString():"");
					vo.setMandalName(obj[11] != null ? obj[11].toString():"");
					vo.setSupportPurpose(obj[12] != null ? obj[12].toString():"");
					vo.setApprovedAmount(obj[13] != null ? obj[13].toString():"");
					vo.setIssueType(obj[14] != null ? obj[14].toString():"");
					
					returnList.add(vo);
				}
			}
		} catch (Exception e) {
			LOG.error("Exception raised in getGrievanceBenifitsComplaintsInfoByLocation  method in CadreDetailsService.",e);
		}
		return returnList;
	}
	
	public List<IdNameVO> getEventAttendanceOfCadre(Long cadreId,Long eventId){
		List<IdNameVO> returnList = null;
		try {
			Object[] eventDates = eventDAO.getEventDatesByEventId(eventId);
			Map<String,IdNameVO> dateMap = new LinkedHashMap<String, IdNameVO>();
			Map<String,IdNameVO> returnDateMap = new LinkedHashMap<String, IdNameVO>();
			Map<String,List<String>> dateWiseMap = new LinkedHashMap<String, List<String>>();
			Map<String,List<String>> returnMap = new LinkedHashMap<String, List<String>>();
			if(eventDates != null){
				List<Date> betweenDates= commonMethodsUtilService.getBetweenDates((Date)eventDates[0], (Date)eventDates[1]);
				if(betweenDates != null){
					for(Date date :betweenDates){
						SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
						IdNameVO vo1 = new IdNameVO();
						String dateStr = sdf.format(date);
						vo1.setName(dateStr);
						dateMap.put(dateStr, vo1);
					}
				}
				
				List<Object[]> datesList = eventAttendeeDAO.getEventAttendedInfoForCadre(cadreId, eventId);
				if(commonMethodsUtilService.isListOrSetValid(datesList)){
					for (Object[] obj : datesList) {
						String dateStr = obj[0] != null ? obj[0].toString():"";
						String dateTimeStr = obj[1] != null ? obj[1].toString():"";
						
						List<String> dateTimeLst = dateWiseMap.get(dateStr);
						if(dateTimeLst == null){
							dateTimeLst = new ArrayList<String>();
							dateTimeLst.add(dateTimeStr);
							returnMap.put(dateStr, dateTimeLst);
							dateWiseMap.put(dateStr, dateTimeLst);
						}
						else{
							dateTimeLst.add(dateTimeStr);
							dateWiseMap.put(dateStr, dateTimeLst);
						}
					}
				}
				
				List<Object[]> list = eventAttendeeDAO.getEventAttendedDetails(cadreId, eventId);
				if(list != null && list.size() > 0){
					for (Object[] obj : list) {
						String dateStr = obj[0] != null ? obj[0].toString():"";
						Long count = Long.valueOf(obj[1] != null ? obj[1].toString():"0");
						IdNameVO vo = dateMap.get(dateStr);
						if(vo != null){
							vo.setCount(count);
							vo.setDateStr(obj[2] != null ? obj[2].toString():"");
							vo.setSubList(returnMap.get(dateStr));
							returnDateMap.put(dateStr, vo);
						}
					}
				}
				
				returnList = new ArrayList<IdNameVO>(returnDateMap.values());
			}
			
		} catch (Exception e) {
			LOG.error("Exception raised in getEventAttendanceOfCadre  method in CadreDetailsService.",e);
		}
		return returnList;
	}

	/**
	 * @Author  Hyma
	 * @Version UserService.java  May 8, 2016 05:00:00 PM 
	 * @param cadreId
	 * @return List<ActivityVO>
	 * description  { Getting Activity Wise data for Candidate , As he Invitted, Attended, Abscent For activities ,By Sending Tdp Cadre Id}
	 */
public List<ActivityVO> getCandateActivityAttendance(Long cadreId,Long activityLevelId,Long panchayatId,Long mandalId,Long lebId,Long  assemblyId,Long districtId,Long stateId,Long participatedAssemblyId,String activeCode){
	List<ActivityVO> returnList = new ArrayList<ActivityVO>();
	List<ActivityVO> finalList = new ArrayList<ActivityVO>();
	try{
	List<Object[]> activityScopeIds = activityScopeRequiredAttributesDAO.getScopeIds(activityLevelId);
	if(activityScopeIds != null && activityScopeIds.size() >0){
		String[] setterPropertiesList = {"activityScopeId","attendendLocation","locationLevel","isLocation","activityNameId","attributeId"};//activityScopeId,activityName,activityLevelId,activityLevelName,activityId
		returnList = (List<ActivityVO>) setterAndGetterUtilService.setValuesToVO(activityScopeIds, setterPropertiesList, "com.itgrids.partyanalyst.dto.ActivityVO");
		//setTemplateData(returnList,activityScopeIds);
	}
	
	List<Object[]> invittes = activityInviteeDAO.getActivityScopeAndLevels(cadreId,activityLevelId);
	if(invittes != null && invittes.size() >0){
		
	for(Object[] obj : invittes){
		ActivityVO vo = (ActivityVO) setterAndGetterUtilService.getMatchedVOfromList(returnList, "activityScopeId", commonMethodsUtilService.getStringValueForObject(obj[0]));//getMatchedVOForScopeId((Long)obj[0],returnList);
		if(vo != null){
			vo.setInvitteeCnt(commonMethodsUtilService.getLongValueForObject(obj[1]));
		}
	}
	}
	List<Object[]> attendees = activityAttendanceDAO.getActivityScopeAndLevels(cadreId,activityLevelId);
	Set<Long> attendencHavingLocationInfoIdsList = new HashSet<Long>(0);
	
	if(attendees != null && attendees.size() >0){
		for(Object[] obj : attendees){
			
			Long locationInfoId = commonMethodsUtilService.getLongValueForObject(obj[4]);//4
			//Long activityLevlId =commonMethodsUtilService.getLongValueForObject(obj[5]) ;//5
			//Long activityLevelValue = commonMethodsUtilService.getLongValueForObject(obj[6]);//6
			attendencHavingLocationInfoIdsList.add(locationInfoId);
			ActivityVO vo = (ActivityVO) setterAndGetterUtilService.getMatchedVOfromList(returnList, "activityScopeId", commonMethodsUtilService.getStringValueForObject(obj[0]));//getMatchedVOForScopeId((Long)obj[0],returnList);//getMatchedVOForScopeId((Long)obj[0],returnList);
			if(vo != null){
				
				vo.setConductedDate("Conducted");
				
				if(vo.getAttendedCount() == null)
					vo.setAttendedCount(0L);
				if(vo.getAbscentCnt() == null)
					vo.setAbscentCnt(0L);
				String attendenceType = commonMethodsUtilService.getStringValueForObject(obj[3]);
				if(attendenceType != null && !attendenceType.isEmpty()){
					if(attendenceType.trim().equalsIgnoreCase("YES"))
						vo.setAttendedCount(vo.getAttendedCount()+1);
					else if(attendenceType.trim().equalsIgnoreCase("NO"))
						vo.setAbscentCnt(vo.getAbscentCnt()+1);
				}
				
			}
		}
	}
	
	List<Object[]> conductedLocationInfosLsit = activityLocationInfoDAO.getConductedActivityDetailsbyScopeAndLocationID(activityLevelId,panchayatId,mandalId,lebId,assemblyId,districtId,stateId,participatedAssemblyId);
	if(commonMethodsUtilService.isListOrSetValid(conductedLocationInfosLsit)){
		for (Object[] obj : conductedLocationInfosLsit) {
			Long lcoationINfoId = commonMethodsUtilService.getLongValueForObject(obj[5]);
			if(!attendencHavingLocationInfoIdsList.contains(lcoationINfoId)){
				Long scopeId = commonMethodsUtilService.getLongValueForObject(obj[0]);
				String activityName = commonMethodsUtilService.getStringValueForObject(obj[1]);
				Long activitylevelId = commonMethodsUtilService.getLongValueForObject(obj[2]);
				String levelStr =commonMethodsUtilService.getStringValueForObject(obj[3]) ;
				Long activityId = commonMethodsUtilService.getLongValueForObject(obj[4]);
				//String conducteDateStr =commonMethodsUtilService.getStringValueForObject(obj[6]) ;
				
				ActivityVO vo = new ActivityVO();
				vo.setActivityScopeId(scopeId);
				vo.setAttendendLocation(activityName);
				vo.setLocationLevel(activitylevelId);
				vo.setIsLocation(levelStr);
				vo.setActivityNameId(activityId);
				//if(conducteDateStr != null && conducteDateStr.trim().length()>0)
				//	vo.setConductedDate(conducteDateStr);
				//else
					vo.setConductedDate("Conducted");
				returnList.add(vo);
			}
			
			//"activityScopeId","attendendLocation","locationLevel","isLocation","activityNameId"
			//select distinct model.activityScope.activityScopeId,model.activityScope.activity.activityName, model.activityScope.activityLevel.activityLevelId,
			//model.activityScope.activityLevel.level,model.activityScope.activity.activityId
		}
	}
	if(commonMethodsUtilService.isListOrSetValid(returnList)){
		
		Map<Long,ActivityVO> activitiesMap = new HashMap<Long, ActivityVO>(0);
		for (ActivityVO vo : returnList) 
			activitiesMap.put(vo.getActivityScopeId(), vo);
		 
		if(commonMethodsUtilService.isMapValid(activitiesMap))
			for (Long activityScopeId : activitiesMap.keySet()) {
				ActivityVO vo = activitiesMap.get(activityScopeId);
				if(activeCode != null){
					if(activeCode.equalsIgnoreCase("t")) //total Activities
						finalList.add(vo);
					else if(activeCode.equalsIgnoreCase("hat"))  // has attendance total
					{
						if(vo.getAttributeId() != null && vo.getAttributeId() == 2L)
							finalList.add(vo);
					}
					else if(activeCode.equalsIgnoreCase("ha")) // has attended
					{
						if(vo.getAttributeId() != null && vo.getAttributeId() == 2L && vo.getConductedDate() != null &&  vo.getConductedDate().trim().equalsIgnoreCase("Conducted"))
							finalList.add(vo);
					}
					else if(activeCode.equalsIgnoreCase("hna")) // has not attended
					{
						if(vo.getAttributeId() != null && vo.getAttributeId() == 2L && (vo.getAttendedCount() == null || vo.getAttendedCount().longValue()==0L))
							finalList.add(vo);
					}
					else if(activeCode.equalsIgnoreCase("hct"))  // conducted total
					{
						if(vo.getAttributeId() != null && vo.getAttributeId() != 2L)
							finalList.add(vo);
					}
					else if(activeCode.equalsIgnoreCase("hc")) // has conducted
					{
						if(vo.getAttributeId() != null && vo.getAttributeId() != 2L && vo.getConductedDate() != null &&  vo.getConductedDate().trim().equalsIgnoreCase("Conducted"))
							finalList.add(vo);
					}
					else if(activeCode.equalsIgnoreCase("hnc")) // has not conducted
					{
						if(vo.getAttributeId() != null && vo.getAttributeId() != 2L && vo.getAttendedCount() == null )
							finalList.add(vo);
					}
					
				}
				else{
					if(vo.getConductedDate() != null && vo.getConductedDate().length()>0)
					finalList.add(vo);
				else if((vo.getInvitteeCnt() == null || vo.getInvitteeCnt().longValue() == 0L) && (vo.getAttendedCount() == null || vo.getAttendedCount().longValue() == 0L))
					;
				else
					finalList.add(vo);
				
				}
				
				
			}
	}
	}catch (Exception e) {
		LOG.error("Exception raised in getCandateActivityAttendance  method in CadreDetailsService.",e);
	}
	return finalList;
	
}

	/**
	 * @Author  SRAVANTH
	 * @Version CadreDetailsService.java  Jun 15, 2016 01:00:00 PM 
	 * @param cadreId
	 * @return MobileDetailsVO
	 * description  { Getting Mobile Number Details for Candidate, By Sending Tdp Cadre Id}
	 */
	public MobileDetailsVO getMobileNumberDetailsByTdpCadre(Long tdpCadreId){
		MobileDetailsVO returnvo = new MobileDetailsVO();
		try {
			List<Object[]> list = tdpCadreDAO.getMobileNumberDetailsByTdpCadre(tdpCadreId);
			List<MobileDetailsVO> returnList = new ArrayList<MobileDetailsVO>();
			if(commonMethodsUtilService.isListOrSetValid(list)){
				String[] setterPropertiesList = {"tdpCadreId","mobileNo","connectionTypeId","connectionType","networkProviderId","networkProvider","brandId","brand","brandModelId","brandModel","featureId","feature","networkTypeId","networkType"};
				returnList = setterAndGetterUtilService.setValuesToVO(list, setterPropertiesList, "com.itgrids.partyanalyst.dto.MobileDetailsVO");
			}
			returnvo.setSubList(returnList);
			
		} catch (Exception e) {
			LOG.error("Exception raised in getMobileNumberDetailsByTdpCadre  method in CadreDetailsService.",e);
		}
		return returnvo;
	}
	
	public ResultStatus saveNewPublicRepresentativeDetails(final Long tdpCadreId,final Long publicRepreTypeId){
		ResultStatus resultStatus = new ResultStatus();
		try {
			transactionTemplate.execute(new TransactionCallbackWithoutResult() {
				public void doInTransactionWithoutResult(TransactionStatus status) {
					
					List<Long> candidateIds = tdpCadreCandidateDAO.getTdpCadreCandidate(tdpCadreId);
					if(commonMethodsUtilService.isListOrSetValid(candidateIds)){
						Long candidateId = candidateIds.get(0);
						
						PublicRepresentative publicRepresentative = new PublicRepresentative();
						
						publicRepresentative.setPublicRepresentativeTypeId(publicRepreTypeId);
						publicRepresentative.setCandidateId(candidateId);
						
						publicRepresentative = publicRepresentativeDAO.save(publicRepresentative);
					}
				}
			});
			resultStatus.setResultCode(0);
		} catch (Exception e) {
			resultStatus.setResultCode(1);
			LOG.error("Exception raised in saveNewPublicRepresentativeDetails  method in CadreDetailsService.",e);
		}
		return resultStatus;
	}
public List<IdNameVO> getAllPublicRepresentatives(){
		
		List<IdNameVO> finalList = new ArrayList<IdNameVO>();
		try{
			
			List<Object[]> publicRepresentativesList = publicRepresentativeTypeDAO.getAllPublicRepresentativeList();
			if(publicRepresentativesList != null && publicRepresentativesList.size() > 0)
			{
				for(Object[] obj :publicRepresentativesList )
				{
					IdNameVO vo = new IdNameVO();
			    vo.setId(Long.valueOf(obj[0] !=null ? obj[0].toString():"0"));
			    vo.setName(obj[1] !=null ?  obj[1].toString():"");
				finalList.add(vo);
				}
			}
		}catch(Exception e){
			LOG.error("Exception Occured in () getAllPublicRepresentatives method, Exception - ",e);
		}
		return finalList;
	}
}
