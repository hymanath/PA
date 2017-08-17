package com.itgrids.partyanalyst.service.impl;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.math.BigInteger;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.ResourceBundle;
import java.util.Set;
import java.util.TimeZone;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.hibernate.exception.JDBCConnectionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessResourceFailureException;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionCallback;
import org.springframework.transaction.support.TransactionTemplate;

import com.itgrids.partyanalyst.dao.IAssemblyLocalElectionBodyDAO;
import com.itgrids.partyanalyst.dao.IAssemblyLocalElectionBodyWardDAO;
import com.itgrids.partyanalyst.dao.IBloodGroupDAO;
import com.itgrids.partyanalyst.dao.IBoothDAO;
import com.itgrids.partyanalyst.dao.IBoothPublicationVoterDAO;
import com.itgrids.partyanalyst.dao.ICadreDAO;
import com.itgrids.partyanalyst.dao.ICadreLevelDAO;
import com.itgrids.partyanalyst.dao.ICasteCategoryDAO;
import com.itgrids.partyanalyst.dao.ICasteCategoryGroupDAO;
import com.itgrids.partyanalyst.dao.ICasteDAO;
import com.itgrids.partyanalyst.dao.ICasteStateDAO;
import com.itgrids.partyanalyst.dao.IConstituencyDAO;
import com.itgrids.partyanalyst.dao.IConstituencyHierarchyInfoDAO;
import com.itgrids.partyanalyst.dao.IDelimitationConstituencyAssemblyDetailsDAO;
import com.itgrids.partyanalyst.dao.IDelimitationConstituencyMandalDAO;
import com.itgrids.partyanalyst.dao.IDistrictDAO;
import com.itgrids.partyanalyst.dao.IEducationalQualificationsDAO;
import com.itgrids.partyanalyst.dao.IElectionScopeDAO;
import com.itgrids.partyanalyst.dao.IElectionTypeDAO;
import com.itgrids.partyanalyst.dao.IGreaterMuncipalWardDAO;
import com.itgrids.partyanalyst.dao.IHamletDAO;
import com.itgrids.partyanalyst.dao.IInfluencingPeopleDAO;
import com.itgrids.partyanalyst.dao.IInfluencingPeoplePositionDAO;
import com.itgrids.partyanalyst.dao.IIvrMobileDAO;
import com.itgrids.partyanalyst.dao.ILocalElectionBodyDAO;
import com.itgrids.partyanalyst.dao.IMobileAppPingingDAO;
import com.itgrids.partyanalyst.dao.IMobileAppUserAccessDAO;
import com.itgrids.partyanalyst.dao.IMobileAppUserAccessKeyDAO;
import com.itgrids.partyanalyst.dao.IMobileAppUserDAO;
import com.itgrids.partyanalyst.dao.IMobileAppUserProfileDAO;
import com.itgrids.partyanalyst.dao.IMobileAppUserSmsStatusDAO;
import com.itgrids.partyanalyst.dao.IMobileAppUserVoterDAO;
import com.itgrids.partyanalyst.dao.IMobileNumbersDAO;
import com.itgrids.partyanalyst.dao.IOccupationDAO;
import com.itgrids.partyanalyst.dao.IPanchayatDAO;
import com.itgrids.partyanalyst.dao.IPanchayatHamletDAO;
import com.itgrids.partyanalyst.dao.IPartialBoothPanchayatDAO;
import com.itgrids.partyanalyst.dao.IPartyDAO;
import com.itgrids.partyanalyst.dao.IPublicationDateDAO;
import com.itgrids.partyanalyst.dao.IStateDAO;
import com.itgrids.partyanalyst.dao.ITdMemberDAO;
import com.itgrids.partyanalyst.dao.ITdpCadreDAO;
import com.itgrids.partyanalyst.dao.ITehsilDAO;
import com.itgrids.partyanalyst.dao.IUserAccessLevelValueDAO;
import com.itgrids.partyanalyst.dao.IUserDAO;
import com.itgrids.partyanalyst.dao.IUserLocationTrackingDAO;
import com.itgrids.partyanalyst.dao.IUserVoterDetailsDAO;
import com.itgrids.partyanalyst.dao.IVoterAgeInfoDAO;
import com.itgrids.partyanalyst.dao.IVoterAgeRangeDAO;
import com.itgrids.partyanalyst.dao.IVoterBasicInfoDAO;
import com.itgrids.partyanalyst.dao.IVoterCastBasicInfoDAO;
import com.itgrids.partyanalyst.dao.IVoterCastInfoDAO;
import com.itgrids.partyanalyst.dao.IVoterDataAvailableConstituenciesDAO;
import com.itgrids.partyanalyst.dao.IVoterFamilyInfoDAO;
import com.itgrids.partyanalyst.dao.IVoterFamilyRangeDAO;
import com.itgrids.partyanalyst.dao.IVoterInfoDAO;
import com.itgrids.partyanalyst.dao.IVoterModificationAgeInfoDAO;
import com.itgrids.partyanalyst.dao.IVoterModificationDAO;
import com.itgrids.partyanalyst.dao.IVoterModificationInfoDAO;
import com.itgrids.partyanalyst.dao.IVoterNamesDAO;
import com.itgrids.partyanalyst.dao.IVoterReportLevelDAO;
import com.itgrids.partyanalyst.dao.IVotingTrendzDAO;
import com.itgrids.partyanalyst.dao.IVotingTrendzPartiesResultDAO;
import com.itgrids.partyanalyst.dao.IWardBoothDAO;
import com.itgrids.partyanalyst.dao.IWebServiceBaseUrlDAO;
import com.itgrids.partyanalyst.dto.CadreVoterVO;
import com.itgrids.partyanalyst.dto.IdNameVO;
import com.itgrids.partyanalyst.dto.LocationWiseBoothDetailsVO;
import com.itgrids.partyanalyst.dto.MobileAppUserDetailsVO;
import com.itgrids.partyanalyst.dto.MobileAppUserVoterVO;
import com.itgrids.partyanalyst.dto.MobileUserVO;
import com.itgrids.partyanalyst.dto.MobileVO;
import com.itgrids.partyanalyst.dto.PollManagementSummaryVO;
import com.itgrids.partyanalyst.dto.PollManagementVO;
import com.itgrids.partyanalyst.dto.RegistrationVO;
import com.itgrids.partyanalyst.dto.ResultCodeMapper;
import com.itgrids.partyanalyst.dto.ResultStatus;
import com.itgrids.partyanalyst.dto.SelectOptionVO;
import com.itgrids.partyanalyst.dto.TabDetailsVO;
import com.itgrids.partyanalyst.dto.VoterDetailsVO;
import com.itgrids.partyanalyst.model.AssemblyLocalElectionBody;
import com.itgrids.partyanalyst.model.AssemblyLocalElectionBodyWard;
import com.itgrids.partyanalyst.model.BloodGroup;
import com.itgrids.partyanalyst.model.Booth;
import com.itgrids.partyanalyst.model.Cadre;
import com.itgrids.partyanalyst.model.CadreLevel;
import com.itgrids.partyanalyst.model.Caste;
import com.itgrids.partyanalyst.model.CasteCategory;
import com.itgrids.partyanalyst.model.CasteCategoryGroup;
import com.itgrids.partyanalyst.model.Constituency;
import com.itgrids.partyanalyst.model.ConstituencyHierarchyInfo;
import com.itgrids.partyanalyst.model.EducationalQualifications;
import com.itgrids.partyanalyst.model.ElectionScope;
import com.itgrids.partyanalyst.model.ElectionType;
import com.itgrids.partyanalyst.model.InfluencingPeople;
import com.itgrids.partyanalyst.model.InfluencingPeoplePosition;
import com.itgrids.partyanalyst.model.LocalElectionBody;
import com.itgrids.partyanalyst.model.Locality;
import com.itgrids.partyanalyst.model.MobileAppUser;
import com.itgrids.partyanalyst.model.MobileAppUserAccess;
import com.itgrids.partyanalyst.model.MobileAppUserAccessKey;
import com.itgrids.partyanalyst.model.MobileAppUserProfile;
import com.itgrids.partyanalyst.model.MobileAppUserSmsStatus;
import com.itgrids.partyanalyst.model.Occupation;
import com.itgrids.partyanalyst.model.Panchayat;
import com.itgrids.partyanalyst.model.PartialBoothPanchayat;
import com.itgrids.partyanalyst.model.Party;
import com.itgrids.partyanalyst.model.PublicationDate;
import com.itgrids.partyanalyst.model.UserLocationTracking;
import com.itgrids.partyanalyst.model.UserVoterDetails;
import com.itgrids.partyanalyst.model.VoterAgeInfo;
import com.itgrids.partyanalyst.model.VoterAgeRange;
import com.itgrids.partyanalyst.model.VoterBasicInfo;
import com.itgrids.partyanalyst.model.VoterCastBasicInfo;
import com.itgrids.partyanalyst.model.VoterCastInfo;
import com.itgrids.partyanalyst.model.VoterDataAvailableConstituencies;
import com.itgrids.partyanalyst.model.VoterFamilyInfo;
import com.itgrids.partyanalyst.model.VoterFamilyRange;
import com.itgrids.partyanalyst.model.VoterInfo;
import com.itgrids.partyanalyst.model.VoterReportLevel;
import com.itgrids.partyanalyst.model.VotingTrendz;
import com.itgrids.partyanalyst.model.VotingTrendzPartiesResult;
import com.itgrids.partyanalyst.model.WebServiceBaseUrl;
import com.itgrids.partyanalyst.service.ICadreCommitteeService;
import com.itgrids.partyanalyst.service.IMobileService;
import com.itgrids.partyanalyst.service.IRegionServiceData;
import com.itgrids.partyanalyst.service.ISmsService;
import com.itgrids.partyanalyst.service.IVotersAnalysisService;
import com.itgrids.partyanalyst.utils.CommonMethodsUtilService;
import com.itgrids.partyanalyst.utils.DateUtilService;
import com.itgrids.partyanalyst.utils.IConstants;
import com.itgrids.partyanalyst.webserviceutils.android.utilvos.UserLocationTrackingVo;

public class MobileService implements IMobileService{
	
 private static final Logger LOG = Logger.getLogger(MobileService.class);
 private IBloodGroupDAO bloodGroupDAO;
 private ICadreLevelDAO cadreLevelDAO;
 private ICasteCategoryDAO casteCategoryDAO;
 private ICasteCategoryGroupDAO casteCategoryGroupDAO;
 private IDistrictDAO districtDAO;
 private IEducationalQualificationsDAO educationalQualificationsDAO;
 private IElectionTypeDAO electionTypeDAO;
 private ILocalElectionBodyDAO localElectionBodyDAO;
 private IOccupationDAO occupationDAO;
 private IPartyDAO partyDAO;
 private IStateDAO stateDAO;
 private ITehsilDAO tehsilDAO;
 private IVoterAgeRangeDAO voterAgeRangeDAO;
 private IVoterFamilyRangeDAO voterFamilyRangeDAO;
 private IVoterReportLevelDAO voterReportLevelDAO;
 private ICasteDAO casteDAO;
 private IVoterInfoDAO voterInfoDAO; 
 private IConstituencyDAO constituencyDAO;  
 private IBoothDAO boothDAO;
 private ICasteStateDAO casteStateDAO; 
 private IPanchayatDAO panchayatDAO;
 private IVoterAgeInfoDAO voterAgeInfoDAO;
 private IVoterBasicInfoDAO voterBasicInfoDAO;
 private IVoterCastInfoDAO voterCastInfoDAO;
 private IVoterCastBasicInfoDAO voterCastBasicInfoDAO;
 private IVoterFamilyInfoDAO voterFamilyInfoDAO;
 private IVotingTrendzDAO votingTrendzDAO;
 private IVotingTrendzPartiesResultDAO votingTrendzPartiesResultDAO;
 private IConstituencyHierarchyInfoDAO constituencyHierarchyInfoDAO;
 private IHamletDAO hamletDAO;
 private IPanchayatHamletDAO panchayatHamletDAO;
 private IBoothPublicationVoterDAO boothPublicationVoterDAO;
 private IUserVoterDetailsDAO userVoterDetailsDAO;
 private IVoterModificationDAO voterModificationDAO;
 private IVoterModificationInfoDAO voterModificationInfoDAO;
 private IVoterModificationAgeInfoDAO voterModificationAgeInfoDAO;
 private IPublicationDateDAO publicationDateDAO;
 private ICadreDAO cadreDAO;
 private IInfluencingPeoplePositionDAO influencingPeoplePositionDAO;
 private IInfluencingPeopleDAO influencingPeopleDAO;
 private IMobileAppUserDAO mobileAppUserDAO;
 private IMobileAppUserAccessDAO mobileAppUserAccessDAO;
 private IMobileAppUserProfileDAO mobileAppUserProfileDAO;
 private TransactionTemplate transactionTemplate;
 private IElectionScopeDAO electionScopeDAO;
 private IMobileAppUserAccessKeyDAO mobileAppUserAccessKeyDAO  ;
 private IUserDAO userDAO;
 private IDelimitationConstituencyMandalDAO delimitationConstituencyMandalDAO;
 private ISmsService smsCountrySmsService;
 private IPartialBoothPanchayatDAO partialBoothPanchayatDAO;
 private IAssemblyLocalElectionBodyDAO assemblyLocalElectionBodyDAO;
 private IMobileAppPingingDAO mobileAppPingingDAO;
 private IWardBoothDAO wardBoothDAO;
 private IWebServiceBaseUrlDAO webServiceBaseUrlDAO;
 private IDelimitationConstituencyAssemblyDetailsDAO delimitationConstituencyAssemblyDetailsDAO;
 private IAssemblyLocalElectionBodyWardDAO assemblyLocalElectionBodyWardDAO;
 private IVoterDataAvailableConstituenciesDAO voterDataAvailableConstituenciesDAO;
 private IVotersAnalysisService votersAnalysisService;
 private IIvrMobileDAO ivrMobileDAO;
 private IMobileNumbersDAO mobileNumbersDAO;
 @Autowired
 private IVoterNamesDAO voterNamesDAO;
 @Autowired
 private ITdpCadreDAO tdpCadreDAO;
 private ITdMemberDAO tdMemberDAO;
 
 private IMobileAppUserVoterDAO  mobileAppUserVoterDAO;
 private IMobileAppUserSmsStatusDAO		mobileAppUserSmsStatusDAO;
 private IUserLocationTrackingDAO userLocationTrackingDAO;
 private IGreaterMuncipalWardDAO		greaterMuncipalWardDAO;
 DecimalFormat decimalFormat = new DecimalFormat("#.##");
 private IUserAccessLevelValueDAO		userAccessLevelValueDAO;
 private ICadreCommitteeService cadreCommitteeService;
 private IRegionServiceData regionServiceDataImp;
 
public IUserAccessLevelValueDAO getUserAccessLevelValueDAO() {
	return userAccessLevelValueDAO;
}

public void setUserAccessLevelValueDAO(
		IUserAccessLevelValueDAO userAccessLevelValueDAO) {
	this.userAccessLevelValueDAO = userAccessLevelValueDAO;
}

public IUserLocationTrackingDAO getUserLocationTrackingDAO() {
	return userLocationTrackingDAO;
}

public void setUserLocationTrackingDAO(
		IUserLocationTrackingDAO userLocationTrackingDAO) {
	this.userLocationTrackingDAO = userLocationTrackingDAO;
}

public IGreaterMuncipalWardDAO getGreaterMuncipalWardDAO() {
	return greaterMuncipalWardDAO;
}

public void setGreaterMuncipalWardDAO(
		IGreaterMuncipalWardDAO greaterMuncipalWardDAO) {
	this.greaterMuncipalWardDAO = greaterMuncipalWardDAO;
}

public IMobileAppUserSmsStatusDAO getMobileAppUserSmsStatusDAO() {
	return mobileAppUserSmsStatusDAO;
}

public void setMobileAppUserSmsStatusDAO(
		IMobileAppUserSmsStatusDAO mobileAppUserSmsStatusDAO) {
	this.mobileAppUserSmsStatusDAO = mobileAppUserSmsStatusDAO;
}

public ITdMemberDAO getTdMemberDAO() {
	return tdMemberDAO;
}

public void setTdMemberDAO(ITdMemberDAO tdMemberDAO) {
	this.tdMemberDAO = tdMemberDAO;
}

public IMobileNumbersDAO getMobileNumbersDAO() {
	return mobileNumbersDAO;
}

public void setMobileNumbersDAO(IMobileNumbersDAO mobileNumbersDAO) {
	this.mobileNumbersDAO = mobileNumbersDAO;
}

public IIvrMobileDAO getIvrMobileDAO() {
	return ivrMobileDAO;
}

public void setIvrMobileDAO(IIvrMobileDAO ivrMobileDAO) {
	this.ivrMobileDAO = ivrMobileDAO;
}

public IVotersAnalysisService getVotersAnalysisService() {
	return votersAnalysisService;
}

public void setVotersAnalysisService(
		IVotersAnalysisService votersAnalysisService) {
	this.votersAnalysisService = votersAnalysisService;
}

public IVoterDataAvailableConstituenciesDAO getVoterDataAvailableConstituenciesDAO() {
	return voterDataAvailableConstituenciesDAO;
}

public void setVoterDataAvailableConstituenciesDAO(
		IVoterDataAvailableConstituenciesDAO voterDataAvailableConstituenciesDAO) {
	this.voterDataAvailableConstituenciesDAO = voterDataAvailableConstituenciesDAO;
}

public IAssemblyLocalElectionBodyWardDAO getAssemblyLocalElectionBodyWardDAO() {
	return assemblyLocalElectionBodyWardDAO;
}

public void setAssemblyLocalElectionBodyWardDAO(
		IAssemblyLocalElectionBodyWardDAO assemblyLocalElectionBodyWardDAO) {
	this.assemblyLocalElectionBodyWardDAO = assemblyLocalElectionBodyWardDAO;
}

public IDelimitationConstituencyAssemblyDetailsDAO getDelimitationConstituencyAssemblyDetailsDAO() {
	return delimitationConstituencyAssemblyDetailsDAO;
}

public void setDelimitationConstituencyAssemblyDetailsDAO(
		IDelimitationConstituencyAssemblyDetailsDAO delimitationConstituencyAssemblyDetailsDAO) {
	this.delimitationConstituencyAssemblyDetailsDAO = delimitationConstituencyAssemblyDetailsDAO;
}

public IWebServiceBaseUrlDAO getWebServiceBaseUrlDAO() {
	return webServiceBaseUrlDAO;
}

public void setWebServiceBaseUrlDAO(IWebServiceBaseUrlDAO webServiceBaseUrlDAO) {
	this.webServiceBaseUrlDAO = webServiceBaseUrlDAO;
}

public IMobileAppPingingDAO getMobileAppPingingDAO() {
	return mobileAppPingingDAO;
}

public void setMobileAppPingingDAO(IMobileAppPingingDAO mobileAppPingingDAO) {
	this.mobileAppPingingDAO = mobileAppPingingDAO;
}

public IPartialBoothPanchayatDAO getPartialBoothPanchayatDAO() {
	return partialBoothPanchayatDAO;
}

public void setPartialBoothPanchayatDAO(
		IPartialBoothPanchayatDAO partialBoothPanchayatDAO) {
	this.partialBoothPanchayatDAO = partialBoothPanchayatDAO;
}

public IDelimitationConstituencyMandalDAO getDelimitationConstituencyMandalDAO() {
	return delimitationConstituencyMandalDAO;
}

public void setDelimitationConstituencyMandalDAO(
		IDelimitationConstituencyMandalDAO delimitationConstituencyMandalDAO) {
	this.delimitationConstituencyMandalDAO = delimitationConstituencyMandalDAO;
}

public IUserDAO getUserDAO() {
	return userDAO;
}

public void setUserDAO(IUserDAO userDAO) {
	this.userDAO = userDAO;
}

public ISmsService getSmsCountrySmsService() {
	return smsCountrySmsService;
}

public void setSmsCountrySmsService(ISmsService smsCountrySmsService) {
	this.smsCountrySmsService = smsCountrySmsService;
}

public IMobileAppUserAccessKeyDAO getMobileAppUserAccessKeyDAO() {
	return mobileAppUserAccessKeyDAO;
}

public void setMobileAppUserAccessKeyDAO(
		IMobileAppUserAccessKeyDAO mobileAppUserAccessKeyDAO) {
	this.mobileAppUserAccessKeyDAO = mobileAppUserAccessKeyDAO;
}

public IElectionScopeDAO getElectionScopeDAO() {
	return electionScopeDAO;
}

public void setElectionScopeDAO(IElectionScopeDAO electionScopeDAO) {
	this.electionScopeDAO = electionScopeDAO;
}

public TransactionTemplate getTransactionTemplate() {
	return transactionTemplate;
}

public void setTransactionTemplate(TransactionTemplate transactionTemplate) {
	this.transactionTemplate = transactionTemplate;
}

public IMobileAppUserDAO getMobileAppUserDAO() {
	return mobileAppUserDAO;
}

public void setMobileAppUserDAO(IMobileAppUserDAO mobileAppUserDAO) {
	this.mobileAppUserDAO = mobileAppUserDAO;
}

public IMobileAppUserAccessDAO getMobileAppUserAccessDAO() {
	return mobileAppUserAccessDAO;
}

public void setMobileAppUserAccessDAO(
		IMobileAppUserAccessDAO mobileAppUserAccessDAO) {
	this.mobileAppUserAccessDAO = mobileAppUserAccessDAO;
}

public IMobileAppUserProfileDAO getMobileAppUserProfileDAO() {
	return mobileAppUserProfileDAO;
}

public void setMobileAppUserProfileDAO(
		IMobileAppUserProfileDAO mobileAppUserProfileDAO) {
	this.mobileAppUserProfileDAO = mobileAppUserProfileDAO;
}

public IInfluencingPeopleDAO getInfluencingPeopleDAO() {
	return influencingPeopleDAO;
}

public void setInfluencingPeopleDAO(IInfluencingPeopleDAO influencingPeopleDAO) {
	this.influencingPeopleDAO = influencingPeopleDAO;
}

public IInfluencingPeoplePositionDAO getInfluencingPeoplePositionDAO() {
	return influencingPeoplePositionDAO;
}

public void setInfluencingPeoplePositionDAO(
		IInfluencingPeoplePositionDAO influencingPeoplePositionDAO) {
	this.influencingPeoplePositionDAO = influencingPeoplePositionDAO;
}

public ICadreDAO getCadreDAO() {
	return cadreDAO;
}

public void setCadreDAO(ICadreDAO cadreDAO) {
	this.cadreDAO = cadreDAO;
}

public IPublicationDateDAO getPublicationDateDAO() {
	return publicationDateDAO;
}

public void setPublicationDateDAO(IPublicationDateDAO publicationDateDAO) {
	this.publicationDateDAO = publicationDateDAO;
}

public IVoterModificationAgeInfoDAO getVoterModificationAgeInfoDAO() {
	return voterModificationAgeInfoDAO;
}

public void setVoterModificationAgeInfoDAO(
		IVoterModificationAgeInfoDAO voterModificationAgeInfoDAO) {
	this.voterModificationAgeInfoDAO = voterModificationAgeInfoDAO;
}

public IVoterModificationInfoDAO getVoterModificationInfoDAO() {
	return voterModificationInfoDAO;
}

public void setVoterModificationInfoDAO(
		IVoterModificationInfoDAO voterModificationInfoDAO) {
	this.voterModificationInfoDAO = voterModificationInfoDAO;
}

public IVoterModificationDAO getVoterModificationDAO() {
	return voterModificationDAO;
}

public void setVoterModificationDAO(IVoterModificationDAO voterModificationDAO) {
	this.voterModificationDAO = voterModificationDAO;
}

public IUserVoterDetailsDAO getUserVoterDetailsDAO() {
	return userVoterDetailsDAO;
}

public void setUserVoterDetailsDAO(IUserVoterDetailsDAO userVoterDetailsDAO) {
	this.userVoterDetailsDAO = userVoterDetailsDAO;
}

public IBoothPublicationVoterDAO getBoothPublicationVoterDAO() {
	return boothPublicationVoterDAO;
}

public void setBoothPublicationVoterDAO(
		IBoothPublicationVoterDAO boothPublicationVoterDAO) {
	this.boothPublicationVoterDAO = boothPublicationVoterDAO;
}

public IPanchayatHamletDAO getPanchayatHamletDAO() {
	return panchayatHamletDAO;
}

public void setPanchayatHamletDAO(IPanchayatHamletDAO panchayatHamletDAO) {
	this.panchayatHamletDAO = panchayatHamletDAO;
}

public IHamletDAO getHamletDAO() {
	return hamletDAO;
}

public void setHamletDAO(IHamletDAO hamletDAO) {
	this.hamletDAO = hamletDAO;
}

public IBloodGroupDAO getBloodGroupDAO() {
	return bloodGroupDAO;
  }

  public void setBloodGroupDAO(IBloodGroupDAO bloodGroupDAO) {
	this.bloodGroupDAO = bloodGroupDAO;
 }

  public ICadreLevelDAO getCadreLevelDAO() {
	return cadreLevelDAO;
 }

  public void setCadreLevelDAO(ICadreLevelDAO cadreLevelDAO) {
	this.cadreLevelDAO = cadreLevelDAO;
  }

  public ICasteCategoryDAO getCasteCategoryDAO() {
	return casteCategoryDAO;
  }

  public void setCasteCategoryDAO(ICasteCategoryDAO casteCategoryDAO) {
	this.casteCategoryDAO = casteCategoryDAO;
  }


  public ICasteCategoryGroupDAO getCasteCategoryGroupDAO() {
	return casteCategoryGroupDAO;
  }

  public void setCasteCategoryGroupDAO(
		ICasteCategoryGroupDAO casteCategoryGroupDAO) {
	this.casteCategoryGroupDAO = casteCategoryGroupDAO;
  }

  public IDistrictDAO getDistrictDAO() {
	return districtDAO;
  }

  public void setDistrictDAO(IDistrictDAO districtDAO) {
	this.districtDAO = districtDAO;
  }

  public IEducationalQualificationsDAO getEducationalQualificationsDAO() {
	return educationalQualificationsDAO;
  }

  public void setEducationalQualificationsDAO(
		IEducationalQualificationsDAO educationalQualificationsDAO) {
	this.educationalQualificationsDAO = educationalQualificationsDAO;
  }

  public IElectionTypeDAO getElectionTypeDAO() {
	return electionTypeDAO;
  }

  public void setElectionTypeDAO(IElectionTypeDAO electionTypeDAO) {
	this.electionTypeDAO = electionTypeDAO;
  }

  public ILocalElectionBodyDAO getLocalElectionBodyDAO() {
	return localElectionBodyDAO;
  }

  public void setLocalElectionBodyDAO(ILocalElectionBodyDAO localElectionBodyDAO) {
	this.localElectionBodyDAO = localElectionBodyDAO;
  }

  public IOccupationDAO getOccupationDAO() {
	return occupationDAO;
  }

  public void setOccupationDAO(IOccupationDAO occupationDAO) {
	this.occupationDAO = occupationDAO;
  }

  public IPartyDAO getPartyDAO() {
	return partyDAO;
  }

  public void setPartyDAO(IPartyDAO partyDAO) {
	this.partyDAO = partyDAO;
  }

  public IStateDAO getStateDAO() {
	return stateDAO;
  }

  public void setStateDAO(IStateDAO stateDAO) {
	this.stateDAO = stateDAO;
  }

  public ITehsilDAO getTehsilDAO() {
	return tehsilDAO;
  }

  public void setTehsilDAO(ITehsilDAO tehsilDAO) {
	this.tehsilDAO = tehsilDAO;
  }

  public IVoterAgeRangeDAO getVoterAgeRangeDAO() {
	return voterAgeRangeDAO;
  }

  public void setVoterAgeRangeDAO(IVoterAgeRangeDAO voterAgeRangeDAO) {
	this.voterAgeRangeDAO = voterAgeRangeDAO;
  }

  public IVoterFamilyRangeDAO getVoterFamilyRangeDAO() {
	return voterFamilyRangeDAO;
  }

  public void setVoterFamilyRangeDAO(IVoterFamilyRangeDAO voterFamilyRangeDAO) {
	this.voterFamilyRangeDAO = voterFamilyRangeDAO;
  }

  public IVoterReportLevelDAO getVoterReportLevelDAO() {
	return voterReportLevelDAO;
  }

  public void setVoterReportLevelDAO(IVoterReportLevelDAO voterReportLevelDAO) {
	this.voterReportLevelDAO = voterReportLevelDAO;
  }
  
  
  public IVoterInfoDAO getVoterInfoDAO() {
	return voterInfoDAO;
  }

  public void setVoterInfoDAO(IVoterInfoDAO voterInfoDAO) {
	this.voterInfoDAO = voterInfoDAO;
  }

  public ICasteDAO getCasteDAO() {
	return casteDAO;
 }

  public void setCasteDAO(ICasteDAO casteDAO) {
	this.casteDAO = casteDAO;
 }

  public IConstituencyDAO getConstituencyDAO() {
	return constituencyDAO;
  }

  public void setConstituencyDAO(IConstituencyDAO constituencyDAO) {
	this.constituencyDAO = constituencyDAO;
  }

  
  public IBoothDAO getBoothDAO() {
	return boothDAO;
  }

  public void setBoothDAO(IBoothDAO boothDAO) {
	this.boothDAO = boothDAO;
  }

  public ICasteStateDAO getCasteStateDAO() {
	return casteStateDAO;
  }

  public void setCasteStateDAO(ICasteStateDAO casteStateDAO) {
	this.casteStateDAO = casteStateDAO;
  }

  public IPanchayatDAO getPanchayatDAO() {
	return panchayatDAO;
  }

  public void setPanchayatDAO(IPanchayatDAO panchayatDAO) {
	this.panchayatDAO = panchayatDAO;
  }

  public IVoterAgeInfoDAO getVoterAgeInfoDAO() {
	return voterAgeInfoDAO;
  }

  public void setVoterAgeInfoDAO(IVoterAgeInfoDAO voterAgeInfoDAO) {
	this.voterAgeInfoDAO = voterAgeInfoDAO;
  }

  public IVoterBasicInfoDAO getVoterBasicInfoDAO() {
	return voterBasicInfoDAO;
  }

  public void setVoterBasicInfoDAO(IVoterBasicInfoDAO voterBasicInfoDAO) {
	this.voterBasicInfoDAO = voterBasicInfoDAO;
  }

  public IVoterCastInfoDAO getVoterCastInfoDAO() {
	return voterCastInfoDAO;
  }

  public void setVoterCastInfoDAO(IVoterCastInfoDAO voterCastInfoDAO) {
	this.voterCastInfoDAO = voterCastInfoDAO;
  }

  public IVoterCastBasicInfoDAO getVoterCastBasicInfoDAO() {
	return voterCastBasicInfoDAO;
  }

  public void setVoterCastBasicInfoDAO(
		IVoterCastBasicInfoDAO voterCastBasicInfoDAO) {
	this.voterCastBasicInfoDAO = voterCastBasicInfoDAO;
  }

  public IVoterFamilyInfoDAO getVoterFamilyInfoDAO() {
	return voterFamilyInfoDAO;
  }

  public void setVoterFamilyInfoDAO(IVoterFamilyInfoDAO voterFamilyInfoDAO) {
	this.voterFamilyInfoDAO = voterFamilyInfoDAO;
  }

  public IVotingTrendzDAO getVotingTrendzDAO() {
	return votingTrendzDAO;
  }

  public void setVotingTrendzDAO(IVotingTrendzDAO votingTrendzDAO) {
	this.votingTrendzDAO = votingTrendzDAO;
  }

  public IVotingTrendzPartiesResultDAO getVotingTrendzPartiesResultDAO() {
	return votingTrendzPartiesResultDAO;
  }

  public void setVotingTrendzPartiesResultDAO(
		IVotingTrendzPartiesResultDAO votingTrendzPartiesResultDAO) {
	this.votingTrendzPartiesResultDAO = votingTrendzPartiesResultDAO;
  }
  
  public IConstituencyHierarchyInfoDAO getConstituencyHierarchyInfoDAO() {
	return constituencyHierarchyInfoDAO;
  }

  public void setConstituencyHierarchyInfoDAO(
		IConstituencyHierarchyInfoDAO constituencyHierarchyInfoDAO) {
	this.constituencyHierarchyInfoDAO = constituencyHierarchyInfoDAO;
  }

	public IAssemblyLocalElectionBodyDAO getAssemblyLocalElectionBodyDAO() {
		return assemblyLocalElectionBodyDAO;
	}
	
	public void setAssemblyLocalElectionBodyDAO(
			IAssemblyLocalElectionBodyDAO assemblyLocalElectionBodyDAO) {
		this.assemblyLocalElectionBodyDAO = assemblyLocalElectionBodyDAO;
	}
    
    public IWardBoothDAO getWardBoothDAO() {
		return wardBoothDAO;
	}

	public void setWardBoothDAO(IWardBoothDAO wardBoothDAO) {
		this.wardBoothDAO = wardBoothDAO;
	}
    
	
public void setMobileAppUserVoterDAO(
			IMobileAppUserVoterDAO mobileAppUserVoterDAO) {
		this.mobileAppUserVoterDAO = mobileAppUserVoterDAO;
	}

	public ICadreCommitteeService getCadreCommitteeService() {
		return cadreCommitteeService;
	}
	
	public void setCadreCommitteeService(ICadreCommitteeService cadreCommitteeService) {
		this.cadreCommitteeService = cadreCommitteeService;
	}

	public IRegionServiceData getRegionServiceDataImp() {
		return regionServiceDataImp;
	}

	public void setRegionServiceDataImp(IRegionServiceData regionServiceDataImp) {
		this.regionServiceDataImp = regionServiceDataImp;
	}

public List<SelectOptionVO> getConstituencyList()
  {
	  List<SelectOptionVO> selectOptionVOList = new ArrayList<SelectOptionVO>();
	 try{
		 List<Object[]> list = null;
		List<Long> constituencyIds = voterInfoDAO.getConstituencyIds();
		if(constituencyIds != null && constituencyIds.size() > 0)
		 list = constituencyDAO.getConstityencyByConstituencyids(constituencyIds);
		if(list != null && list.size() > 0)
		 for(Object[] params:list)
		  selectOptionVOList.add(new SelectOptionVO((Long)params[0],params[1]!=null?params[1].toString():""));
		
	  return selectOptionVOList;
	 }catch (Exception e) {
	  e.printStackTrace();
	  LOG.error(" Exception Occured in getConstituencyList() method, Exception - "+e);
	  return selectOptionVOList;
	}
  }
	

	public ResultStatus createDataDumpFileForAParliamnetConstituency(RegistrationVO reVo)
	{
		LOG.info("Entered into createDataDumpFileForAParliamnetConstituency() Method ");
		ResultStatus resultStatus = new ResultStatus();
		try{
			Class.forName("org.sqlite.JDBC");
			Connection connection = null;
			Statement statement = null;
			
			Long pconstituencyId = reVo.getConstituencyId();
			Long publicationId = reVo.getPublicationDateId();
			String constituencyName = constituencyDAO.get(pconstituencyId).getName();
			String path = reVo.getPath();
			
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			String date = sdf.format(new Date());
			String pathSeperator = System.getProperty(IConstants.FILE_SEPARATOR);
			
			File destDir = new File(path+pathSeperator+constituencyName+"_"+date+"_CMS");
			destDir.mkdir();
			File baseFile = new File(path+pathSeperator+"Base"+pathSeperator+"base.sqlite");
			
			FileOutputStream fos = new FileOutputStream(path+pathSeperator+constituencyName+"_"+date+"_CMS.zip");
			ZipOutputStream zos = new ZipOutputStream(fos);
			
			List<Booth> boothsList = boothDAO.getBoothDataForAPublication(publicationId);
			List<Panchayat> panchayatsList = panchayatDAO.getAll();
			List<LocalElectionBody> localElectionBodysList = localElectionBodyDAO.getAll();
			List<AssemblyLocalElectionBody> alebList = assemblyLocalElectionBodyDAO.getAll();
			List<AssemblyLocalElectionBodyWard> alebwList = assemblyLocalElectionBodyWardDAO.getAll();
			List<Object[]> constituenyAreaTypesList = constituencyDAO.getAreaTypesOfAConstituencyByElectionScope(2l);
			
			List<Constituency> acList = delimitationConstituencyAssemblyDetailsDAO.findAssemblyConstituencies(pconstituencyId,2009l);
			
			for(Constituency ac : acList)
			{
				try{
				try{
				File acFile = new File(path+pathSeperator+constituencyName+"_"+date+"_CMS"+pathSeperator+ac.getName()+".sqlite");
				FileUtils.copyFile(baseFile, acFile);
				}catch(Exception e)
				{
					LOG.error("Exception is -",e);
				}
				
				List<Object[]> votersList = boothPublicationVoterDAO.getVoterDetailsOfAConstituencyAndPublication(ac.getConstituencyId(),publicationId);
				
				if(votersList != null && votersList.size() > 0)
				{
					connection = DriverManager.getConnection("jdbc:sqlite:"+path+pathSeperator+constituencyName+"_"+date+"_CMS"+pathSeperator+ac.getName()+".sqlite");
					connection.setAutoCommit(false);
					statement = connection.createStatement();
					int records = 0;
					for(Object[] params : votersList)
					{
						records++;
						try{
						statement.executeUpdate("INSERT INTO voter(voter_id,house_no,name,relationship_type,relative_name,gender,age,voter_id_card_no)" +
								" VALUES ('"+params[0].toString()+"','"+params[1].toString().trim()+"','"+replaceSpecialChars(params[2].toString().trim())+"','"+params[3].toString().trim()+"','"+replaceSpecialChars(params[4].toString().trim())+"'," +
										"'"+params[5].toString().trim()+"',"+params[6].toString().trim()+",'"+params[7].toString().trim()+"')");
						}catch(Exception e)
						{
							LOG.error(e);
						}
					}
					LOG.error(ac.getName()+" Constituency "+records+" Voter Records Inserted");
					connection.commit();
					statement.close();
					connection.close();
				}
				
				List<Object[]> votersAndSerialNosList = boothPublicationVoterDAO.getRecordsFromBoothPublicationVoter(ac.getConstituencyId(), publicationId);
				if(votersAndSerialNosList != null && votersAndSerialNosList.size() > 0)
				{
					connection = DriverManager.getConnection("jdbc:sqlite:"+path+pathSeperator+constituencyName+"_"+date+"_CMS"+pathSeperator+ac.getName()+".sqlite");
					connection.setAutoCommit(false);
					statement = connection.createStatement();
					int records = 0;
					for(Object[] params : votersAndSerialNosList)
					{
						records++;
						try{
						String serialNo = params[3]!= null ? params[3].toString() : "0";
						statement.executeUpdate("INSERT INTO booth_publication_voter(booth_publication_voter_id, booth_id, voter_id, serial_no) VALUES (" +
								"'"+params[0].toString()+"','"+params[1].toString()+"','"+params[2].toString()+"','"+serialNo+"')");
						}catch(Exception e)
						{
							LOG.error(e);
						}
					}
					LOG.error(ac.getName()+" Constituency "+records+" Booth Publication Voter Records Inserted");
					connection.commit();
					statement.close();
					connection.close();
				}
				
				if(boothsList != null && boothsList.size() > 0)
				{
					connection = DriverManager.getConnection("jdbc:sqlite:"+path+pathSeperator+constituencyName+"_"+date+"_CMS"+pathSeperator+ac.getName()+".sqlite");
					connection.setAutoCommit(false);
					statement = connection.createStatement();
					int records = 0;
					for(Booth booth : boothsList)
					{
						records++;
						try{
						statement.executeUpdate("INSERT INTO booth(booth_id,part_no,part_name,location,village_covered,tehsil_id,male_voters,female_voters,total_voters," +
								"constituency_id,year,publication_date_id) VALUES (" +
								"'"+booth.getBoothId()+"','"+booth.getPartNo()+"','','"+booth.getLocation()+"','"+booth.getVillagesCovered()+"','"+booth.getTehsil().getTehsilId()+"'" +
										",'"+booth.getMaleVoters()+"','"+booth.getFemaleVoters()+"','"+booth.getTotalVoters()+"','"+booth.getConstituency().getConstituencyId()+"','"+booth.getYear()+"','"+booth.getPublicationDate().getPublicationDateId()+"')");
						}catch(Exception e)
						{
							LOG.error(e);
						}
					}
					LOG.error(ac.getName()+" Constituency "+records+" Booth Publication Voter Records Inserted");
					connection.commit();
					statement.close();
					connection.close();
					
					connection = DriverManager.getConnection("jdbc:sqlite:"+path+pathSeperator+constituencyName+"_"+date+"_CMS"+pathSeperator+ac.getName()+".sqlite");
					connection.setAutoCommit(false);
					statement = connection.createStatement();
					
					for(Booth booth : boothsList)
					{
						if(booth.getPanchayat() != null)
						{
							try{
							statement.executeUpdate("UPDATE booth set panchayat_id = "+booth.getPanchayat().getPanchayatId()+" where booth_id = "+booth.getBoothId());
							}catch(Exception e)
							{
								LOG.error(e);
							}
						}
					}
					
					for(Booth booth : boothsList)
					{
						if(booth.getLocalBody() != null)
						{
							try{
							statement.executeUpdate("UPDATE booth set local_election_body_id = "+booth.getLocalBody().getLocalElectionBodyId()+" where booth_id = "+booth.getBoothId());
							}catch(Exception e)
							{
								LOG.error(e);
							}
						}
					}
					
					for(Booth booth : boothsList)
					{
						if(booth.getLocalBodyWard() != null)
						{
							try{
							statement.executeUpdate("UPDATE booth set ward_id = "+booth.getLocalBodyWard().getConstituencyId()+" where booth_id = "+booth.getBoothId());
							}catch(Exception e)
							{
								LOG.error(e);
							}
						}
					}
					
					for(Booth booth : boothsList)
					{
						if(booth.getRefBooth() != null)
						{
							try{
							statement.executeUpdate("UPDATE booth set ref_booth_id = "+booth.getRefBooth().getBoothId()+" where booth_id = "+booth.getBoothId());
							}catch(Exception e)
							{
								LOG.error(e);
							}
						}
					}
					
					connection.commit();
					statement.close();
					connection.close();
				}
				
				if(panchayatsList != null && panchayatsList.size() > 0)
				{
					connection = DriverManager.getConnection("jdbc:sqlite:"+path+pathSeperator+constituencyName+"_"+date+"_CMS"+pathSeperator+ac.getName()+".sqlite");
					connection.setAutoCommit(false);
					statement = connection.createStatement();
					int records = 0;
					for(Panchayat panchayat : panchayatsList)
					{
						records++;
						try{
						statement.executeUpdate("INSERT INTO panchayat(panchayat_id,panchayat_name,tehsil_id)" +
								" VALUES ('"+panchayat.getPanchayatId().toString()+"','"+panchayat.getPanchayatName().toString().trim()+"','"+panchayat.getTehsil().getTehsilId().toString().trim()+"')");
						}catch(Exception e)
						{
							LOG.error(e);
						}
					}
					LOG.error(ac.getName()+" Constituency "+records+" Panchayat Records Inserted");
					connection.commit();
					statement.close();
					connection.close();
				}
				
				if(localElectionBodysList != null && localElectionBodysList.size() > 0)
				{
					try{
					connection = DriverManager.getConnection("jdbc:sqlite:"+path+pathSeperator+constituencyName+"_"+date+"_CMS"+pathSeperator+ac.getName()+".sqlite");
					connection.setAutoCommit(false);
					statement = connection.createStatement();
					int records = 0;
					for(LocalElectionBody leb : localElectionBodysList)
					{
						records++;
						try{
						statement.executeUpdate("INSERT INTO local_election_body(local_election_body_id,name,election_type_id,tehsil_id,district_id)" +
								" VALUES ('"+leb.getLocalElectionBodyId().toString()+"','"+leb.getName().toString().trim()+"','"+leb.getElectionType().getElectionTypeId().toString().trim()+"','"+leb.getTehsil().getTehsilId().toString()+"','"+leb.getDistrict().getDistrictId().toString()+"')");
						}catch(Exception e)
						{
							LOG.error(e);
						}
					}
					LOG.error(ac.getName()+" Constituency "+records+" Local Election Body Records Inserted");
					connection.commit();
					statement.close();
					connection.close();
					}catch(Exception e)
					{
						LOG.error(e);
					}
				}
				
				if(alebList != null && alebList.size() > 0)
				{
					try{
					connection = DriverManager.getConnection("jdbc:sqlite:"+path+pathSeperator+constituencyName+"_"+date+"_CMS"+pathSeperator+ac.getName()+".sqlite");
					connection.setAutoCommit(false);
					statement = connection.createStatement();
					int records = 0;
					for(AssemblyLocalElectionBody aleb : alebList)
					{
						records++;
						try{
						statement.executeUpdate("INSERT INTO assembly_local_election_body(assembly_local_election_body_id,local_election_body_id,constituency_id)" +
								" VALUES ('"+aleb.getAssemblyLocalElectionBodyId().toString()+"','"+aleb.getLocalElectionBody().getLocalElectionBodyId().toString().trim()+"','"+aleb.getConstituency().getConstituencyId().toString().trim()+"')");
						}catch(Exception e)
						{
							LOG.error(e);
						}
					}
					LOG.error(ac.getName()+" Constituency "+records+" Assembly Local Election Body Records Inserted");
					connection.commit();
					statement.close();
					connection.close();
					}catch(Exception e)
					{
						LOG.error(e);
					}
				}
				
				if(alebwList != null && alebwList.size() > 0)
				{
					try{
					connection = DriverManager.getConnection("jdbc:sqlite:"+path+pathSeperator+constituencyName+"_"+date+"_CMS"+pathSeperator+ac.getName()+".sqlite");
					connection.setAutoCommit(false);
					statement = connection.createStatement();
					int records = 0;
					for(AssemblyLocalElectionBodyWard alebw : alebwList)
					{
						records++;
						try{
						statement.executeUpdate("INSERT INTO assembly_local_election_body_ward(assembly_local_election_body_ward_id,assembly_local_election_body_id,local_election_body_ward_id)" +
								" VALUES ('"+alebw.getAssemblyLocalElectionBodyWardId().toString()+"','"+alebw.getAssemblyLocalElectionBody().getAssemblyLocalElectionBodyId().toString()+"','"+alebw.getConstituency().getConstituencyId().toString()+"')");
						}catch(Exception e)
						{
							LOG.error(e);
						}
					}
					LOG.error(ac.getName()+" Constituency "+records+" Assembly Local Election Body Wards Records Inserted");
					connection.commit();
					statement.close();
					connection.close();
					}catch(Exception e)
					{
						LOG.error(e);
					}
				}
				
				if(constituenyAreaTypesList != null && constituenyAreaTypesList.size() > 0)
				{
					try{
					connection = DriverManager.getConnection("jdbc:sqlite:"+path+pathSeperator+constituencyName+"_"+date+"_CMS"+pathSeperator+ac.getName()+".sqlite");
					connection.setAutoCommit(false);
					statement = connection.createStatement();
					int records = 0;
					for(Object[] params : constituenyAreaTypesList)
					{
						records++;
						try{
						statement.executeUpdate("update constituency set area_type = '"+params[1].toString()+"' where constituency_id = '"+params[0].toString()+"'");
						}catch(Exception e)
						{
							LOG.error(e);
						}
					}
					LOG.error(ac.getName()+" Constituency "+records+" Area Types Records Updated");
					connection.commit();
					statement.close();
					connection.close();
					}catch(Exception e)
					{
						LOG.error(e);
					}
				}
				
				List<Object[]> hamletsList = panchayatHamletDAO.getHamletsListByConstituency(ac.getConstituencyId(),publicationId);
				
				if(hamletsList != null && hamletsList.size() > 0)
				{
					try{
					connection = DriverManager.getConnection("jdbc:sqlite:"+path+pathSeperator+constituencyName+"_"+date+"_CMS"+pathSeperator+ac.getName()+".sqlite");
					connection.setAutoCommit(false);
					statement = connection.createStatement();
					int records = 0;
					for(Object[] params : hamletsList)
					{
						records++;
						try{
						statement.executeUpdate("INSERT INTO hamlet(hamlet_id,hamlet_name,panchayat_id,tehsil_id)" +
								" VALUES ('"+params[0].toString()+"','"+params[1].toString().trim()+"','"+params[2].toString().trim()+"','"+params[3].toString()+"')");
						}catch(Exception e)
						{
							LOG.error(e);
						}
					}
					LOG.error(ac.getName()+" Constituency "+records+" hamlet Records Inserted");
					connection.commit();
					statement.close();
					connection.close();
					}catch(Exception e)
					{
						LOG.error(e);
					}
				}
				
				}catch(Exception e)
				{
					LOG.error("Exception Occured for "+ac.getName()+" Constituency, Exception is - ",e);
				}
				
			}
			
			try{
				 for(File rf : destDir.listFiles())
					 addToZipFile(rf.getAbsolutePath(), zos);
				 zos.close();
				 fos.close();
			 }catch(Exception e)
			 {
				 LOG.error("Exception Occured in Zipping Files");
			 }
			
			resultStatus.setMessage("/SQLITE_DB/"+constituencyName+"_"+date+"_CMS.zip");
			resultStatus.setResultCode(ResultCodeMapper.SUCCESS);
			return resultStatus;
		}catch(Exception e)
		{
			LOG.error("Exception Occured in createDataDumpFileForAConstituency() Method",e);
			resultStatus.setResultCode(ResultCodeMapper.FAILURE);
			return resultStatus;
		}
	}
	
	public ResultStatus createSurveySqliteFileForAParliamnetConstituency(RegistrationVO reVo)
	{
		LOG.info("Entered into createSurveySqliteFileForAParliamnetConstituency() Method ");
		ResultStatus resultStatus = new ResultStatus();
		try{
			Class.forName("org.sqlite.JDBC");
			Connection connection = null;
			Statement statement = null;
			
			Long pconstituencyId = reVo.getConstituencyId();
			Long publicationId = reVo.getPublicationDateId();
			String constituencyName = constituencyDAO.get(pconstituencyId).getName();
			String path = reVo.getPath();
			
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			String date = sdf.format(new Date());
			String pathSeperator = System.getProperty(IConstants.FILE_SEPARATOR);
			
			File destDir = new File(path+pathSeperator+constituencyName+"_"+date+"_SURVEY");
			destDir.mkdir();
			File baseFile = new File(path+pathSeperator+"Base"+pathSeperator+"survey_base.sqlite");
			
			FileOutputStream fos = new FileOutputStream(path+pathSeperator+constituencyName+"_"+date+"_SURVEY.zip");
			ZipOutputStream zos = new ZipOutputStream(fos);
			
			List<Booth> boothsList = boothDAO.getBoothDataForAPublication(publicationId);
			List<Constituency> acList = delimitationConstituencyAssemblyDetailsDAO.findAssemblyConstituencies(pconstituencyId,2009l);
			
			for(Constituency ac : acList)
			{
				try{
				String assemblyName = ac.getName();
				File sqliteFile = new File(path+pathSeperator+constituencyName+"_"+date+"_SURVEY"+pathSeperator+assemblyName+".sqlite");
				FileUtils.copyFile(baseFile,sqliteFile);
					
				List<Object[]> votersList = boothPublicationVoterDAO.getVoterDetailsOfAConstituencyAndPublication(ac.getConstituencyId(),publicationId);
				
				if(votersList != null && votersList.size() > 0)
				{
					connection = DriverManager.getConnection("jdbc:sqlite:"+path+pathSeperator+constituencyName+"_"+date+"_SURVEY"+pathSeperator+assemblyName+".sqlite");
					connection.setAutoCommit(false);
					statement = connection.createStatement();
					int records = 0;
					for(Object[] params : votersList)
					{
						records++;
						try{
						statement.executeUpdate("INSERT INTO voter(voter_id,house_no,name,relationship_type,relative_name,gender,age,voter_id_card_no)" +
								" VALUES ('"+params[0].toString()+"','"+params[1].toString().trim()+"','"+replaceSpecialChars(params[2].toString().trim())+"','"+params[3].toString().trim()+"','"+replaceSpecialChars(params[4].toString().trim())+"'," +
										"'"+params[5].toString().trim()+"',"+params[6].toString().trim()+",'"+params[7].toString().trim()+"')");
						}catch(Exception e)
						{
							LOG.error(e);
						}
					}
					LOG.error(ac.getName()+" Constituency "+records+" Voter Records Inserted");
					connection.commit();
					statement.close();
					connection.close();
				}
				
				List<Object[]> votersAndSerialNosList = boothPublicationVoterDAO.getRecordsFromBoothPublicationVoter(ac.getConstituencyId(), publicationId);
				if(votersAndSerialNosList != null && votersAndSerialNosList.size() > 0)
				{
					connection = DriverManager.getConnection("jdbc:sqlite:"+path+pathSeperator+constituencyName+"_"+date+"_SURVEY"+pathSeperator+assemblyName+".sqlite");
					connection.setAutoCommit(false);
					statement = connection.createStatement();
					int records = 0;
					for(Object[] params : votersAndSerialNosList)
					{
						records++;
						try{
						String serialNo = params[3]!= null ? params[3].toString() : "0";
						statement.executeUpdate("INSERT INTO booth_publication_voter(booth_publication_voter_id, booth_id, voter_id, serial_no) VALUES (" +
								"'"+params[0].toString()+"','"+params[1].toString()+"','"+params[2].toString()+"','"+serialNo+"')");
						}catch(Exception e)
						{
							LOG.error(e);
						}
					}
					LOG.error(ac.getName()+" Constituency "+records+" Booth Publication Voter Records Inserted");
					connection.commit();
					statement.close();
					connection.close();
				}
				
				if(boothsList != null && boothsList.size() > 0)
				{
					connection = DriverManager.getConnection("jdbc:sqlite:"+path+pathSeperator+constituencyName+"_"+date+"_SURVEY"+pathSeperator+assemblyName+".sqlite");
					connection.setAutoCommit(false);
					statement = connection.createStatement();
					int records = 0;
					SimpleDateFormat sdf2 = new SimpleDateFormat(IConstants.DATE_AND_TIME_FORMAT_24HRS);
					for(Booth booth : boothsList)
					{
						records++;
						try{
						statement.executeUpdate("INSERT INTO booth(booth_id,part_no,part_name,location,village_covered,tehsil_id,male_voters,female_voters,total_voters," +
								"constituency_id,year,publication_date_id,updated_time) VALUES (" +
								"'"+booth.getBoothId()+"','"+booth.getPartNo()+"','','"+booth.getLocation()+"','"+booth.getVillagesCovered()+"','"+booth.getTehsil().getTehsilId()+"'" +
										",'"+booth.getMaleVoters()+"','"+booth.getFemaleVoters()+"','"+booth.getTotalVoters()+"','"+booth.getConstituency().getConstituencyId()+"','"+booth.getYear()+"','"+booth.getPublicationDate().getPublicationDateId()+"','"+sdf2.format(booth.getUpdatedTime())+"')");
						}catch(Exception e)
						{
							LOG.error(e);
						}
					}
					
					LOG.error(records+" Booth Records Inserted");
					connection.commit();
					statement.close();
					connection.close();
					
					connection = DriverManager.getConnection("jdbc:sqlite:"+path+pathSeperator+constituencyName+"_"+date+"_SURVEY"+pathSeperator+assemblyName+".sqlite");
					connection.setAutoCommit(false);
					statement = connection.createStatement();
					
					for(Booth booth : boothsList)
					{
						if(booth.getPanchayat() != null)
						{
							try{
							statement.executeUpdate("UPDATE booth set panchayat_id = "+booth.getPanchayat().getPanchayatId()+" where booth_id = "+booth.getBoothId());
							}catch(Exception e)
							{
								LOG.error(e);
							}
						}
					}
					
					for(Booth booth : boothsList)
					{
						if(booth.getLocalBody() != null)
						{
							try{
							statement.executeUpdate("UPDATE booth set local_election_body_id = "+booth.getLocalBody().getLocalElectionBodyId()+" where booth_id = "+booth.getBoothId());
							}catch(Exception e)
							{
								LOG.error(e);
							}
						}
					}
					
					for(Booth booth : boothsList)
					{
						if(booth.getLocalBodyWard() != null)
						{
							try{
							statement.executeUpdate("UPDATE booth set ward_id = "+booth.getLocalBodyWard().getConstituencyId()+" where booth_id = "+booth.getBoothId());
							}catch(Exception e)
							{
								LOG.error(e);
							}
						}
					}
					
					for(Booth booth : boothsList)
					{
						if(booth.getRefBooth() != null)
						{
							try{
							statement.executeUpdate("UPDATE booth set ref_booth_id = "+booth.getRefBooth().getBoothId()+" where booth_id = "+booth.getBoothId());
							}catch(Exception e)
							{
								LOG.error(e);
							}
						}
					}
					
					connection.commit();
					statement.close();
					connection.close();
				}
				
				//Adding indexes
				connection = DriverManager.getConnection("jdbc:sqlite:"+path+pathSeperator+constituencyName+"_"+date+"_SURVEY"+pathSeperator+assemblyName+".sqlite");
				statement = connection.createStatement();
				
				try{
					LOG.error("Adding Indexes");
					statement.executeUpdate("CREATE INDEX idx_voter_voter_id_card_no ON voter(voter_id_card_no)");
					statement.executeUpdate("CREATE INDEX idx_voter_age ON voter(age)");
					statement.executeUpdate("CREATE INDEX idx_voter_gender ON voter(gender)");
					
					statement.executeUpdate("CREATE INDEX idx_voter_booth_id ON booth_publication_voter(booth_id)");
					statement.executeUpdate("CREATE INDEX idx_voter_voter_id ON booth_publication_voter(voter_id)");
					statement.executeUpdate("CREATE INDEX idx_voter_serial_no ON booth_publication_voter(serial_no)");
					
					statement.executeUpdate("CREATE INDEX idx_booth_part_no ON booth(part_no)");
					statement.executeUpdate("CREATE INDEX idx_booth_tehsil_id ON booth(tehsil_id)");
					statement.executeUpdate("CREATE INDEX idx_booth_year ON booth(year)");
					statement.executeUpdate("CREATE INDEX idx_booth_constituency_id ON booth(constituency_id);");
					statement.executeUpdate("CREATE INDEX idx_booth_local_election_body_id ON booth(local_election_body_id)");
					statement.executeUpdate("CREATE INDEX idx_booth_publication_date_id ON booth(publication_date_id)");
					statement.executeUpdate("CREATE INDEX idx_booth_panchayat_id ON booth(panchayat_id)");
					statement.executeUpdate("CREATE INDEX idx_booth_ward_id ON booth(ward_id)");
					statement.executeUpdate("CREATE INDEX idx_booth_updated_time ON booth(updated_time)");
					
					connection.commit();
					statement.close();
					connection.close();
					
				}catch(Exception e)
				{
					LOG.error(e);
				}
				
				}catch(Exception e)
				{
					LOG.error("Exception Occured for "+ac.getName()+" Constituency, Exception is - ",e);
				}
			}
			
			try{
				 for(File rf : destDir.listFiles())
					 addToZipFile(rf.getAbsolutePath(), zos);
				 zos.close();
				 fos.close();
			 }catch(Exception e)
			 {
				 LOG.error("Exception Occured in Zipping Files");
			 }
			
			resultStatus.setMessage("/SQLITE_DB_SURVEY/"+constituencyName+"_"+date+"_SURVEY.zip");
			resultStatus.setResultCode(ResultCodeMapper.SUCCESS);
			return resultStatus;
		}catch(Exception e)
		{
			LOG.error("Exception Occured in createDataDumpFileForAConstituency() Method",e);
			resultStatus.setResultCode(ResultCodeMapper.FAILURE);
			return resultStatus;
		}
	}
	
	public ResultStatus createDataDumpFileForAConstituency(RegistrationVO reVo)
	{
		LOG.info("Entered into createDataDumpFileForAConstituency() Method ");
		ResultStatus resultStatus = new ResultStatus();
		try{
			
			Long constituencyId = reVo.getConstituencyId();
			Long publicationId = reVo.getPublicationDateId();
			String constituencyName = constituencyDAO.get(constituencyId).getName();
			String path = reVo.getPath();
			
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			String date = sdf.format(new Date());
			String pathSeperator = System.getProperty(IConstants.FILE_SEPARATOR);
			File destDir = new File(path+pathSeperator+constituencyName+"_"+date+"_CMS");
			destDir.mkdir();
			
			File f1 = new File(path+pathSeperator+constituencyName+"_"+date+"_CMS"+pathSeperator+"1.sql");
			File f2 = new File(path+pathSeperator+constituencyName+"_"+date+"_CMS"+pathSeperator+"2.sql");
			
			BufferedWriter outPut1 = new BufferedWriter(new FileWriter(f1));
			BufferedWriter outPut2 = new BufferedWriter(new FileWriter(f2));
			
			FileOutputStream fos = new FileOutputStream(path+pathSeperator+constituencyName+"_"+date+"_CMS.zip");
			ZipOutputStream zos = new ZipOutputStream(fos);
			
			StringBuilder str = new StringBuilder();
			
			try{
				List<Object[]> votersList = boothPublicationVoterDAO.getVoterDetailsOfAConstituencyAndPublication(constituencyId,publicationId);
				
				if(votersList != null && votersList.size() > 0)
				{
					StringBuilder strTemp = null;
					for(Object[] params : votersList)
					{
						try{
							strTemp = new StringBuilder();
							strTemp.append("INSERT INTO voter(voter_id,house_no,name,relationship_type,relative_name,gender,age,voter_id_card_no) VALUES (");
							strTemp.append(params[0].toString().trim()+",'"+params[1].toString().trim()+"','"+replaceSpecialChars(params[2].toString().trim())+"','"+params[3].toString().trim()+"','"+replaceSpecialChars(params[4].toString().trim())+"','");
							strTemp.append(params[5].toString().trim()+"',"+params[6].toString().trim()+",'"+params[7].toString().trim()+"'");
							strTemp.append(");\n");
							str.append(strTemp);
						}catch(Exception e)
						{
							LOG.error("Exception occured in inserting voters Data with voter ID - "+params[0]+" Exception - ",e);
						}
					}
					str.append("\n");
				}
			}catch(Exception e){}
			
			LOG.info("voter table data Completed...");
			outPut1.write(str.toString());
			outPut1.close();
			
			str = new StringBuilder();
			try{
				List<Object[]> votersAndSerialNosList = boothPublicationVoterDAO.getRecordsFromBoothPublicationVoter(constituencyId, publicationId);
				
				if(votersAndSerialNosList != null && votersAndSerialNosList.size() > 0)
				{
					for(Object[] params : votersAndSerialNosList)
					{
						try{
							str.append("INSERT INTO booth_publication_voter(booth_publication_voter_id, booth_id, voter_id, serial_no) VALUES (");
							str.append(params[0].toString()+","+params[1].toString()+","+params[2].toString()+",");
							str.append(params[3] != null ? params[3].toString() : "0");
							str.append(");\n");
						}catch(Exception e){
							LOG.error("Error Occured in inserting records in BoothPublicationVoter - "+e);
						}
					}
					str.append("\n");
				}
			}catch(Exception e){}
			
			LOG.info("booth punlication voter table data Completed...");
			
			outPut2.write(str.toString());
			outPut2.close();
			str = new StringBuilder();
			try{
				
					List<Object[]> list = panchayatHamletDAO.getHamletsListByConstituency(constituencyId,publicationId);
					
					if(list != null && list.size() > 0)
					{
						
						for(Object[] params : list)
						{
							try{
							str.append("INSERT INTO hamlet(hamlet_id,hamlet_name,panchayat_id,tehsil_id) VALUES (");
							str.append(params[0].toString()+","+params[1].toString()+","+params[2].toString()+",");
							str.append(params[3].toString());
							str.append(");\n");
							
						}
							catch (Exception e) {
								LOG.error("Error Occured in inserting records in hamlet - "+e);
							}
						}
						str.append("\n");
					}
				
			}
			
			catch (Exception e) {
				LOG.info(" hamlet table completed......");
			}
			
			
			
			
			
			try{
				 for(File rf : destDir.listFiles())
					 addToZipFile(rf.getAbsolutePath(), zos);
				 zos.close();
				 fos.close();
			 }catch(Exception e)
			 {
				 LOG.error("Exception Occured in Zipping Files");
			 }
			
			resultStatus.setMessage("/SQLITE_DB/"+constituencyName+"_"+date+"_CMS.zip");
			resultStatus.setResultCode(ResultCodeMapper.SUCCESS);
			return resultStatus;
		}catch(Exception e)
		{
			LOG.error("Exception occured in createDataDumpFileForAConstituency() Method, Exception is - ",e);
			resultStatus.setResultCode(ResultCodeMapper.FAILURE);
			return resultStatus;
		}
	}
	
	
  public ResultStatus createDataDumpFileForSelectedConstituency(Long constituencyId,String path,final RegistrationVO reVo)
  {
	 LOG.info("Entered into createDataDumpFileForSelectedConstituency Method ");
	 ResultStatus resultStatus = new ResultStatus();
	try{
	saveUserData(reVo);
	
	String constituencyName = constituencyDAO.get(constituencyId).getName();
	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
	String date = sdf.format(new Date());
	String pathSeperator = System.getProperty(IConstants.FILE_SEPARATOR);
	File destDir = new File(path+pathSeperator+constituencyName+"_"+date);
	destDir.mkdir();
	
	File f1 = new File(path+pathSeperator+constituencyName+"_"+date+pathSeperator+"1.sql");
	File f2 = new File(path+pathSeperator+constituencyName+"_"+date+pathSeperator+"2.sql");
	File f3 = new File(path+pathSeperator+constituencyName+"_"+date+pathSeperator+"3.sql");
	File f4 = new File(path+pathSeperator+constituencyName+"_"+date+pathSeperator+"4.sql");
	
	BufferedWriter outPut1 = new BufferedWriter(new FileWriter(f1));
	BufferedWriter outPut2 = new BufferedWriter(new FileWriter(f2));
	BufferedWriter outPut3 = new BufferedWriter(new FileWriter(f3));
	BufferedWriter outPut4 = new BufferedWriter(new FileWriter(f4));
	
	FileOutputStream fos = new FileOutputStream(path+pathSeperator+constituencyName+"_"+date+".zip");
	ZipOutputStream zos = new ZipOutputStream(fos);
	
	StringBuilder str = new StringBuilder();
	ResourceBundle rb = ResourceBundle.getBundle("mobileDBScripts");
	Enumeration<String> keysList =   rb.getKeys();
	Long publicationId = reVo.getPublicationDateId();
	
	while(keysList.hasMoreElements())
	{
		str.append(rb.getString(keysList.nextElement()));
		str.append("\n");
	}
	
	str.append("\n \n");
	
	LOG.info("Properties File is read ");
	
	List<BloodGroup> bloodGroupList = bloodGroupDAO.getBloodGroupList();
	if(bloodGroupList != null && bloodGroupList.size()>0)
	{
	  for(BloodGroup bloodGroup:bloodGroupList)
	    str.append("INSERT INTO blood_group(blood_group_id, blood_group) VALUES ('"+bloodGroup.getBloodGroupId()+"','"+bloodGroup.getBloodGroup()+"');\n");
	}
	
	str.append("\n\n");
	
	LOG.info("Bloo Group Data Completed.");
	//List<Booth> boothList = boothDAO.getBoothsListByConstituencyId(constituencyId);
	List<Booth> boothList = boothDAO.getBoothOfAConstituencyInAPublication(constituencyId, publicationId);
			
	if(boothList != null && boothList.size() > 0)
	{
	  for(Booth booth:boothList)
	  {
		  try{
		  str.append("INSERT INTO booth(booth_id,part_no,part_name,location,village_covered,tehsil_id,male_voters,female_voters,total_voters,constituency_id,year,local_election_body_id,publication_date_id,panchayat_id,ward_id) VALUES (");
		  str.append(booth.getBoothId()+",'"+booth.getPartNo()+"','"+booth.getPartName()+"','"+booth.getLocation()+"','"+booth.getVillagesCovered()+"',");
		  str.append(booth.getTehsil() != null ? booth.getTehsil().getTehsilId()+"," : "NULL,");
		  str.append(booth.getMaleVoters() != null ? booth.getMaleVoters()+"," : "NULL,");
		  str.append(booth.getFemaleVoters() != null ? booth.getFemaleVoters()+"," : "NULL,");
		  str.append(booth.getTotalVoters() != null ? booth.getTotalVoters()+"," : "NULL,");
		  str.append(booth.getConstituency() != null ? booth.getConstituency().getConstituencyId()+"," : "NULL,");
		  str.append(booth.getYear() != null ? booth.getYear()+"," : "NULL,");
		  str.append(booth.getLocalBody() != null ? booth.getLocalBody().getLocalElectionBodyId()+"," : "NULL,");
		  str.append(booth.getPublicationDate() != null ? booth.getPublicationDate().getPublicationDateId()+"," : "NULL,");
		  str.append(booth.getPanchayat() != null ? booth.getPanchayat().getPanchayatId()+"," : "NULL,");
		  str.append(booth.getLocalBodyWard() != null ? booth.getLocalBodyWard().getConstituencyId() : "NULL");
		  str.append(");\n");
		  }catch(Exception e)
		  {
			  LOG.error("Exception Occured in Booth Inserts - "+e);
		  }
	  }
	  str.append("\n");
	}
	LOG.info("Booth Data Completed...");
	
	List<CadreLevel> cadreLevelList = cadreLevelDAO.getCadreLevelList();
	if(cadreLevelList != null && cadreLevelList.size() > 0)
	{
	 for(CadreLevel cadreLevel:cadreLevelList)
	 {
	  str.append("INSERT INTO cadre_level(cadre_level_id,level,order_no) VALUES ('"+cadreLevel.getCadreLevelID()+"','"+cadreLevel.getLevel()+"',");
	  str.append(cadreLevel.getOrderNo() != null ? cadreLevel.getOrderNo() : "NULL");
	  str.append(");\n");
	 }
	}
    str.append("\n");
	
    LOG.info("Cadre Level data Completed...");
    
	List<Caste> casteList = casteDAO.getCasteList();
	if(casteList != null && casteList.size() > 0)
	{
	  for(Caste caste:casteList)
		str.append("INSERT INTO caste(caste_id,caste_name) VALUES ('"+caste.getCasteId()+"','"+caste.getCasteName()+"');\n");
	}
	str.append("\n");
	
	LOG.info("Caste data Completed...");
	
	List<Object[]> casteStateList = casteStateDAO.getCasteStateList();
	if(casteStateList != null && casteStateList.size() > 0)
	{
	 for(Object[] params:casteStateList)
		str.append("INSERT INTO caste_state(caste_state_id,state_id,caste_category_group_id,caste_id) VALUES ('"+(Long)params[0]+"','"+(Long)params[1]+"','"+(Long)params[2]+"','"+(Long)params[3]+"');\n");
	}
		
	 str.append("\n");
	 
	 LOG.info("Caste State data Completed...");
	 
	 List<CasteCategory> casteCategoryList = casteCategoryDAO.getCasteCategoryList();
	 if(casteCategoryList != null && casteCategoryList.size() > 0)
	 {
	  for(CasteCategory casteCategory:casteCategoryList)
		str.append("INSERT INTO caste_category (caste_category_id,category_name,description) VALUES ('"+casteCategory.getCasteCategoryId()+"','"+casteCategory.getCategoryName()+"','"+casteCategory.getDescription()+"');\n");
	 }
	 str.append("\n");
	 
	 LOG.info("Caste Category data Completed...");
	 
	 List<ConstituencyHierarchyInfo> constituencyHierarchyInfoList = constituencyHierarchyInfoDAO.getConstituencyHierarchyInfoList(constituencyId,publicationId,1L);
	 if(constituencyHierarchyInfoList != null && constituencyHierarchyInfoList.size() > 0)
	 {
		for(ConstituencyHierarchyInfo hierarchyInfo:constituencyHierarchyInfoList)
		 str.append("INSERT INTO constituency_hierarchy_info(constituency_hierarchy_info_id,constituency_id,mandals,municipalities,panchayats,hamlets,wards,booths,publication_date_id,report_level_id,report_level_value) " +
		 		"VALUES ('"+hierarchyInfo.getConstituencyHierarchyInfoId()+"','"+hierarchyInfo.getConstituencyId()+"','"+hierarchyInfo.getMandals()+"','"+hierarchyInfo.getMunicipalities()+"','"+hierarchyInfo.getPanchayats()+"'" +
		 				",'"+hierarchyInfo.getHamlets()+"','"+hierarchyInfo.getWards()+"','"+hierarchyInfo.getBooths()+"','"+hierarchyInfo.getPublicationDateId()+"','"+hierarchyInfo.getReportLevelId()+"','"+hierarchyInfo.getReportLevelValue()+"');\n");
	 }
	 
	 str.append("\n");
	 
	 LOG.info("Constituency Hierarchy Info data Completed...");
	 
	 List<Constituency> constituencyList = constituencyDAO.getConstituencyDetails();
	 if(constituencyList != null && constituencyList.size() > 0)
	 {
		 for(Constituency constituency : constituencyList)
		 {
			 str.append("INSERT INTO constituency(constituency_id,name,election_scope_id,state_id,district_id,tehsil_id,local_election_body_id,area_type) VALUES (");
			 str.append(" "+constituency.getConstituencyId()+",'"+constituency.getName()+"',"+constituency.getElectionScope().getElectionScopeId()+",");
			 str.append(constituency.getState() != null ? constituency.getState().getStateId()+"," : "NULL,");
			 str.append(constituency.getDistrict() != null ? constituency.getDistrict().getDistrictId()+"," : "NULL,");
			 str.append(constituency.getTehsil() != null ? constituency.getTehsil().getTehsilId()+"," : "NULL,");
			 str.append(constituency.getLocalElectionBody() != null ? constituency.getLocalElectionBody().getLocalElectionBodyId()+"," : "NULL,");
			 str.append("'"+constituency.getAreaType()+"');\n");
		 }
	 }
	 
	 str.append("\n");
	 
	 LOG.info("Constituency table data Completed...");
	 
	 List<CasteCategoryGroup> list = casteCategoryGroupDAO.getCasteCategoryGroupList();
	 if(list != null && list.size() > 0)
	 {
		for(CasteCategoryGroup casteCategoryGroup:list)
		 str.append("INSERT INTO caste_category_group(caste_category_group_id,caste_category_id,caste_category_group_name) " +
		 		" VALUES ('"+casteCategoryGroup.getCasteCategoryGroupId()+"','"+casteCategoryGroup.getCasteCategory().getCasteCategoryId()+"','"+casteCategoryGroup.getCasteCategoryGroupName()+"');\n");
	 }
	 str.append("\n");
	
	 LOG.info("caste Category Group table data Completed...");
	 
	List<Object[]> districtList = districtDAO.getDistrictList();
	if(districtList != null && districtList.size() > 0)
	{
	 for(Object[] params:districtList)
	  str.append("INSERT INTO district(district_id,district_name,state_id) VALUES ('"+(Long)params[0]+"','"+params[1].toString()+"','"+(Long)params[2]+"');\n");
	}
	str.append("\n");
	
	LOG.info("District data Completed...");
	
	List<EducationalQualifications> eduQualifList = educationalQualificationsDAO.getEducationalQualificationsList();
	if(eduQualifList != null && eduQualifList.size() > 0)
	{
	  for(EducationalQualifications eList:eduQualifList)
		str.append("INSERT INTO educational_qualifications(educational_qualification_id,qualification) VALUES ('"+eList.getEduQualificationId()+"','"+eList.getQualification()+"');\n");
	}
	str.append("\n");
	
	LOG.info("educational_qualifications data Completed...");
	
	List<ElectionType> electionTypeList = electionTypeDAO.getElectionTypeList();
	if(electionTypeList != null && electionTypeList.size() > 0)
	{
	  for(ElectionType electionType:electionTypeList)
		str.append("INSERT INTO election_type(election_type_id,election_type) VALUES ('"+electionType.getElectionTypeId()+"','"+electionType.getElectionType()+"');\n");
	}
	str.append("\n");
	
	LOG.info("Election Type data Completed...");
	
	try{
		
	List<ElectionScope> electionScopeList = electionScopeDAO.getAllElectionScopes();
	if(electionScopeList != null && electionScopeList.size() > 0)
	{
	  for(ElectionScope electionScope : electionScopeList)
	  {
		str.append("INSERT INTO election_scope(election_scope_id,election_type_id,state_id,country_id) VALUES (");
		str.append(electionScope.getElectionScopeId()+",");
		str.append(electionScope.getElectionType() != null ? electionScope.getElectionType().getElectionTypeId()+"," : "NULL,");
		str.append(electionScope.getState() != null ? electionScope.getState().getStateId()+"," : "NULL,");
		str.append(electionScope.getCountry().getCountryId());
		str.append(");\n");
	  }
	}
	str.append("\n");
	LOG.info("Election Scope data Completed...");
	}catch(Exception e)
	{
		LOG.info("Exception Ocuured in Election Scope data inserting...");
	}
		
	/*List<Object[]> hamletsAndMandalsList = hamletDAO.gethamletsInAState(constituencyDAO.get(constituencyId).getState().getStateId());
	List<Object[]> hamletsAndPanchayatsList = panchayatHamletDAO.gethamletsInAState(constituencyDAO.get(constituencyId).getState().getStateId());
	Map<Long,Long> hamletsAndPanchayatsMap = new LinkedHashMap<Long,Long>();
	*/
	List<Object[]> hamletsList = boothDAO.getHamletsForAConstituencyForAPublication(constituencyId,publicationId);
	
	/*if(hamletsAndPanchayatsList != null && hamletsAndPanchayatsList.size() > 0)
	{
		for(Object[] params : hamletsAndPanchayatsList)
		{
			hamletsAndPanchayatsMap.put((Long)params[0],(Long)params[1]);
		}
	}*/
	
	LOG.info("Booth data Completed...");
	
	if(hamletsList != null && hamletsList.size() > 0)
	{
		for(Object[] params : hamletsList)
		{
			try{
			str.append("INSERT INTO hamlet(hamlet_id, hamlet_name, panchayat_id, tehsil_id) VALUES (");	
			str.append(""+params[0].toString()+",'"+params[1].toString()+"',"+params[2].toString()+","+params[3].toString()+");\n");
			}catch(Exception e)
			{
				LOG.error("Exceprtion Occured in Hamlets inserts - "+e);
			}
		}
		str.append("\n");
	}
	
	LOG.info("hamlet table data Completed...");
	
	List<Object[]> localElectionBodieList = localElectionBodyDAO.getLocationElectionBodyList();
	if(localElectionBodieList != null && localElectionBodieList.size() > 0)
	{
	  for(Object[] params:localElectionBodieList)
		 str.append("INSERT INTO local_election_body(local_election_body_id,name,election_type_id,tehsil_id,district_id,no_of_wards) " +
		 		"VALUES ('"+(Long)params[0]+"','"+params[1].toString()+"','"+(Long)params[2]+"','"+(Long)params[3]+"','"+(Long)params[4]+"',"+params[4].toString()+");\n");
	}
	str.append("\n");
	
	LOG.info("Local Election Body data Completed...");

	List<Occupation> occupationList = occupationDAO.getOccupationList();
	if(occupationList != null && occupationList.size() > 0)
	{
	  for(Occupation occupation:occupationList)
	  {
		 str.append("INSERT INTO occupation(occupation_id,occupation) VALUES ('"+occupation.getOccupationId()+"','"+occupation.getOccupation()+"');");
		 str.append("\n");
	  }
	}
	str.append("\n");
	
	LOG.info("Occupation data Completed...");
	
	List<Object[]> panchayatList = boothDAO.getPanchayatsForAConstituencyForAPublication(constituencyId,publicationId);
	if(panchayatList != null && panchayatList.size() > 0)
	{
	  for(Object[] params:panchayatList)
		str.append("INSERT INTO panchayat(panchayat_id,panchayat_name,tehsil_id) VALUES ('"+(Long)params[0]+"','"+params[1].toString()+"','"+(Long)params[2]+"');\n");
	}
	
	str.append("\n");
	
	LOG.info("Panchayat table data Completed...");
	
	List<Party> partyList = partyDAO.getAll();
	if(partyList != null && partyList.size() > 0)
	{
	 for(Party party : partyList)
	 {
	  str.append("INSERT INTO party(party_id,short_name,party_recognization,state_id) VALUES ('"+party.getPartyId()+"','"+party.getShortName()+"',");
	  str.append(party.getPartyRecognization() != null ? "'"+party.getPartyRecognization()+"'," : "'',");
	  str.append(party.getState() != null ? party.getState().getStateId().toString() : "null");
	  str.append(");\n");
	 }
	}
	
	str.append("\n");
	
	LOG.info("party table data Completed...");
	
	List<Object[]> stateList = stateDAO.getStateNames();
	if(stateList != null && stateList.size() > 0)
	{
	  for(Object[] params:stateList)
		 str.append("INSERT INTO state(state_id,state_name) VALUES ('"+(Long)params[0]+"','"+params[1].toString()+"');\n");
	}
	str.append("\n");
	
	LOG.info("state table data Completed...");
	
	List<Object[]> tehsilList = boothDAO.getTehsilsForAConstituencyForAPublication(constituencyId,publicationId);
	if(tehsilList != null && tehsilList.size() > 0)
	{
	  for(Object[] params:tehsilList)
		str.append("INSERT INTO tehsil(tehsil_id,tehsil_name,district_id) VALUES ('"+(Long)params[0]+"','"+params[1].toString()+"','"+(Long)params[2]+"');\n");
	}
	
	str.append("\n");
	
	LOG.info("tehsil table data Completed...");
	
	List<VoterAgeInfo> voterAgeInfoList = voterAgeInfoDAO.getVoterAgeInfoList(constituencyId);
	if(voterAgeInfoList != null && voterAgeInfoList.size() > 0)
	{
	 for(VoterAgeInfo ageInfo:voterAgeInfoList)
		str.append("INSERT INTO voter_age_info(voter_age_info_id,report_level_id,report_level_value,age_range_id,total_voters,total_voters_percentage,male_voters,male_voters_percentage,female_voters,female_voters_percentage,publication_date_id,constituency_id) " +
				"VALUES ('"+ageInfo.getVoterAgeInfoId()+"','"+ageInfo.getVoterReportLevel().getVoterReportLevelId()+"','"+ageInfo.getReportLevelValue()+"','"+ageInfo.getVoterAgeRange().getVoterAgeRangeId()+"','"+ageInfo.getTotalVoters()+"','"+ageInfo.getTotalVotersPercentage()+"'" +
						",'"+ageInfo.getMaleVoters()+"','"+ageInfo.getMaleVotersPercentage()+"','"+ageInfo.getFemaleVoters()+"','"+ageInfo.getFemaleVotersPercentage()+"','"+ageInfo.getPublicationDate().getPublicationDateId()+"','"+ageInfo.getConstituencyId()+"');\n");
	}
	
	str.append("\n");
	
	LOG.info("voter age info table data Completed...");
	
	List<VoterAgeRange> voterAgeRangeList = voterAgeRangeDAO.getVoterAgeRangeList();
	if(voterAgeRangeList != null && voterAgeRangeList.size() > 0)
	{
	  for(VoterAgeRange voterAgeRange:voterAgeRangeList)
		str.append("INSERT INTO voter_age_range(voter_age_range_id,age_range) VALUES ('"+voterAgeRange.getVoterAgeRangeId()+"','"+voterAgeRange.getAgeRange()+"');\n");
	}
	str.append("\n");
	
	LOG.info("voter age range table data Completed...");
	
	List<VoterBasicInfo> voterBasicInfoList = voterBasicInfoDAO.getVoterBasicInfoList(constituencyId);
	if(voterBasicInfoList != null && voterBasicInfoList.size() > 0)
	{
	  for(VoterBasicInfo basicInfo:voterBasicInfoList)
		str.append("INSERT INTO voter_basic_info(voter_basic_info_id,constituency_id,report_level_id,report_level_value,year,booths,total,male,female,total_diff,male_diff,female_diff,type,order_no)" +
				" VALUES ('"+basicInfo.getVoterBasicInfoId()+"','"+basicInfo.getConstituency().getConstituencyId()+"','"+basicInfo.getVoterReportLevel().getVoterReportLevelId()+"','"+basicInfo.getReportLevelValue()+"'" +
						",'"+basicInfo.getYear()+"','"+basicInfo.getBooths()+"','"+basicInfo.getTotalVoters()+"','"+basicInfo.getMaleVoters()+"','"+basicInfo.getFemaleVoters()+"','"+basicInfo.getTotalDiff()+"','"+basicInfo.getMaleDiff()+"'" +
								",'"+basicInfo.getFemaleDiff()+"','"+basicInfo.getType()+"','"+basicInfo.getOrderNo()+"');\n");
	}
	str.append("\n");
	
	LOG.info("voter basic info table data Completed...");
	
	List<VoterCastInfo> voterCasteInfoList = voterCastInfoDAO.getVoterCasteInfoList(constituencyId,publicationId,1L);
	if(voterCasteInfoList != null && voterCasteInfoList.size() > 0)
	{
	  for(VoterCastInfo castInfo:voterCasteInfoList)
	  {
		  try{
			str.append("INSERT INTO voter_caste_info(voter_cast_info_id,report_level_id,report_level_value,user_id,caste_state_id,caste_voters,caste_male_voters,caste_female_voters,caste_percentage,publication_date_id,constituency_id,sub_leve_caste_percentage) " +
					"VALUES ('"+castInfo.getVoterCastInfoId()+"','"+castInfo.getVoterReportLevel().getVoterReportLevelId()+"','"+castInfo.getReportLevelValue()+"','"+castInfo.getUserId()+"','"+castInfo.getCasteState().getCasteStateId()+"','"+castInfo.getCasteVoters()+"'" +
							",'"+castInfo.getCasteMaleVoters()+"','"+castInfo.getCasteFemaleVoters()+"','"+castInfo.getCastePercentage()+"','"+castInfo.getPublicationDateId()+"','"+castInfo.getConstituency().getConstituencyId()+"'");
			if(castInfo.getSubLeveCastePercentage() != null)
				str.append(",'"+castInfo.getSubLeveCastePercentage()+"');");
			else
				str.append(",null);");
			
			str.append("\n");
		  }catch(Exception e)
			{
				LOG.error("Exception occured in voter_caste_info with voter_caste_info_id = "+castInfo.getVoterCastInfoId()+" Exception is - ",e);
			}
	  }
	
	}
	str.append("\n");
	
	LOG.info("voter caste info table data Completed...");
	
	List<VoterCastBasicInfo> voterCasteList = voterCastBasicInfoDAO.getVoterCastBasicInfoList(constituencyId,publicationId,1L);
	if(voterCasteList != null && voterCasteList.size() > 0)
	{
		for(VoterCastBasicInfo basicInfo:voterCasteList)
		{
		  str.append("INSERT INTO voter_caste_basic_info(voter_cast_basic_info_id,report_level_id,report_level_value,user_id,total_castes,caste_assigned_voters,caste_not_assigned_voters,oc_voters,bc_voters,sc_voters,st_voters,publication_date_id,constituency_id) " +
		  		"VALUES ('"+basicInfo.getVoterCastBasicInfoId()+"','"+basicInfo.getVoterReportLevel().getVoterReportLevelId()+"','"+basicInfo.getReportLevelValue()+"','"+basicInfo.getUserId()+"','"+basicInfo.getTotalCastes()+"','"+basicInfo.getCasteAssignedVoters()+"','"+basicInfo.getCasteNotAssignedVoters()+"'" +
		  				",'"+basicInfo.getOcVoters()+"','"+basicInfo.getBcVoters()+"','"+basicInfo.getScVoters()+"','"+basicInfo.getStVoters()+"','"+basicInfo.getPublicationDateId()+"','"+basicInfo.getConstituency().getConstituencyId()+"');" );
		  str.append("\n"); 
		}
	}
	str.append("\n");
	
	List<VoterFamilyInfo> voterFamilyInfoList = voterFamilyInfoDAO.getVoterFamilyInfoList(constituencyId);
	if(voterFamilyInfoList != null && voterFamilyInfoList.size() > 0)
	{
	  for(VoterFamilyInfo familyInfo:voterFamilyInfoList)
		str.append("INSERT INTO voter_family_info(voter_family_info_id,report_level_id,report_level_value,family_range_id,total_families,families_percentage,publication_date_id,constituency_id) " +
				"VALUES ('"+familyInfo.getVoterFamilyInfoId()+"','"+familyInfo.getVoterReportLevel().getVoterReportLevelId()+"','"+familyInfo.getReportLevelValue()+"','"+familyInfo.getVoterFamilyRange().getVoterFamilyRangeId()+"'" +
						",'"+familyInfo.getTotalFamilies()+"','"+familyInfo.getFamiliesPercentage()+"','"+familyInfo.getPublicationDate().getPublicationDateId()+"','"+familyInfo.getConstituencyId()+"');\n");
	}
	str.append("\n");
	
	LOG.info("voter family info table data Completed...");
	
	List<VoterFamilyRange> familyRangeList = voterFamilyRangeDAO.getVoterFamilyRangeList();
	if(familyRangeList != null && familyRangeList.size() > 0)
	{
	  for(VoterFamilyRange familyRange:familyRangeList)
		str.append("INSERT INTO voter_family_range(voter_family_range_id,family_range) VALUES ('"+familyRange.getVoterFamilyRangeId()+"','"+familyRange.getFamilyRange()+"');\n");
	}
	str.append("\n");
	
	LOG.info("voter family range table data Completed...");
	
	List<VoterInfo> voterInfoList = voterInfoDAO.getVoterInfoList(constituencyId,publicationId);
	if(voterInfoList != null && voterInfoList.size() > 0)
	{
	 for(VoterInfo voterInfo:voterInfoList)
	 {
		str.append("INSERT INTO voter_info(voter_info_id,report_level_id,report_level_value,total_voters,total_voters_percentage,male_voters,male_voters_percentage,female_voters,female_voters_percentage,total_families,families_percentage,publication_date_id,constituency_id) " +
				"VALUES ('"+voterInfo.getVoterInfoId()+"','"+voterInfo.getVoterReportLevel().getVoterReportLevelId()+"','"+voterInfo.getReportLevelValue()+"','"+voterInfo.getTotalVoters()+"',");
		if(voterInfo.getTotalVotersPercentage() != null)
		str.append("'"+voterInfo.getTotalVotersPercentage()+"'");
		else
		 str.append("null");
		str.append(",'"+voterInfo.getMaleVoters()+"','"+voterInfo.getMaleVotersPercentage()+"'" +
						",'"+voterInfo.getFemaleVoters()+"','"+voterInfo.getFemaleVotersPercentage()+"','"+voterInfo.getTotalFamilies()+"','"+voterInfo.getFamiliesPercentage()+"','"+voterInfo.getPublicationDate().getPublicationDateId()+"','"+voterInfo.getConstituencyId()+"');");
		str.append("\n"); 
	 }
	}
	
	str.append("\n");
	
	LOG.info("voter info table data Completed...");
	
	List<VoterReportLevel> reportLevelList = voterReportLevelDAO.getVoterReportLevelList();
	if(reportLevelList != null && reportLevelList.size() > 0)
	{
	  for(VoterReportLevel reportLevel:reportLevelList)
		str.append("INSERT INTO voter_report_level(voter_report_level_id,report_level,order_no) VALUES ('"+reportLevel.getVoterReportLevelId()+"','"+reportLevel.getReportLevel()+"','"+reportLevel.getOrderNo()+"');\n");
	}
	
	str.append("\n");
	
	LOG.info("voter report level table data Completed...");
	
	List<VotingTrendz> votingTrendZList = votingTrendzDAO.getVotingTrendzList(constituencyId);
	if(votingTrendZList != null && votingTrendZList.size() > 0)
	{
	  for(VotingTrendz trendz:votingTrendZList)
		str.append(" INSERT INTO voting_trendz(voting_trendz_id,constituency_id,report_level_id,report_level_value,election_type,year,total_booths,total_votes,votes_polled,election_type_id,order_no) " +
				"VALUES ('"+trendz.getVotingTrendzId()+"','"+trendz.getConstituency().getConstituencyId()+"','"+trendz.getVoterReportLevel().getVoterReportLevelId()+"','"+trendz.getReportLevelValue()+"','"+trendz.getElectionType().getElectionType()+"'" +
						",'"+trendz.getYear()+"','"+trendz.getTotalBooths()+"','"+trendz.getTotalVotes()+"','"+trendz.getVotesPolled()+"','"+trendz.getElectionType().getElectionTypeId()+"','"+trendz.getOrderNo()+"');\n");
	}
	str.append("\n");
	
	LOG.info("voting trendz table data Completed...");
	
	try{
	List<VotingTrendzPartiesResult> list1 = votingTrendzPartiesResultDAO.getVotingTrendzPartiesResultList(constituencyId);
	if(list1 != null && list1.size() > 0)
	{
	 for(VotingTrendzPartiesResult partiesResult:list1)
		str.append(" INSERT INTO voting_trendz_parties_result(voting_trendz_parties_result_id,voting_trendz_id,party_id,votes_gained) " +
				"VALUES ('"+partiesResult.getVotingTrendzPartiesResultId()+"','"+partiesResult.getVotingTrendz().getVotingTrendzId()+"','"+partiesResult.getParty().getPartyId()+"','"+partiesResult.getVotesGained()+"');\n");
	}
	}catch(Exception e){
		LOG.error("Exception occured in VotingTrendzPartiesResult inserting - "+e);
	}
	str.append("\n");
	
	LOG.info("voting trendz parties result table data Completed...");
	
	try{
		List<Object[]> votersAndSerialNosList = boothPublicationVoterDAO.getRecordsFromBoothPublicationVoter(constituencyId, publicationId);
		
		if(votersAndSerialNosList != null && votersAndSerialNosList.size() > 0)
		{
			for(Object[] params : votersAndSerialNosList)
			{
				try{
					str.append("INSERT INTO booth_publication_voter(booth_publication_voter_id, booth_id, voter_id, serial_no) VALUES (");
					str.append(params[0].toString()+","+params[1].toString()+","+params[2].toString()+",");
					str.append(params[3] != null ? params[3].toString() : "0");
					str.append(");\n");
				}catch(Exception e){
					LOG.error("Error Occured in inserting records in BoothPublicationVoter - "+e);
				}
			}
			str.append("\n");
		}
	}catch(Exception e){}
	
	LOG.info("booth punlication voter table data Completed...");
	
	outPut1.write(str.toString());
	outPut1.close();
	
	str = new StringBuilder();
	
	try{
		List<Object[]> votersList = boothPublicationVoterDAO.getVoterDetailsOfAConstituency(constituencyId,publicationId,1L);
		
		if(votersList != null && votersList.size() > 0)
		{
			StringBuilder strTemp = null;
			for(Object[] params : votersList)
			{
				try{
					strTemp = new StringBuilder();
					strTemp.append("INSERT INTO voter(voter_id,house_no,name,relationship_type,relative_name,gender,age,voter_id_card_no,mobile_no) VALUES (");
					strTemp.append(params[0].toString()+",'"+params[1].toString()+"','"+replaceSpecialChars(params[2].toString().trim())+"','"+params[3].toString()+"','"+replaceSpecialChars(params[4].toString())+"','");
					strTemp.append(params[5].toString()+"',"+params[6].toString()+",'"+params[7].toString()+"',");
					strTemp.append(params[8] != null ? "'"+params[8].toString()+"'" : "NULL");
					strTemp.append(");\n");
					str.append(strTemp);
				}catch(Exception e)
				{
					LOG.error("Exception occured in inserting voters Data with voter ID - "+params[0]+" Exception - ",e);
				}
			}
			str.append("\n");
		}
	}catch(Exception e){}
	
	LOG.info("voter table data Completed...");
	outPut2.write(str.toString());
	outPut2.close();
	
	str = new StringBuilder();
	
	try{
		List<UserVoterDetails> userVoterList = userVoterDetailsDAO.getUserVoterDetailsOfAConstituencyForAPublication(constituencyId,publicationId,1L);
		
		if(userVoterList != null && userVoterList.size() > 0)
		{
			StringBuilder strTemp = null;
			for(UserVoterDetails userVoter : userVoterList)
			{
				try{
					strTemp = new StringBuilder();
					strTemp.append("INSERT INTO user_voter_details(user_voter_details_id,voter_id,caste_state_id,locality_id,hamlet_id,ward_id,constituency_id) VALUES (");
					strTemp.append(userVoter.getUserVoterDetailsId()+","+userVoter.getVoter().getVoterId()+",");
					strTemp.append(userVoter.getCasteState() != null ? userVoter.getCasteState().getCasteStateId()+"," : "NULL,");
					strTemp.append(userVoter.getLocality() != null ? userVoter.getLocality().getLocalityId()+"," : "NULL,");
					strTemp.append(userVoter.getHamlet() != null ? userVoter.getHamlet().getHamletId()+"," : "NULL,");
					strTemp.append(userVoter.getWard() != null ? userVoter.getWard().getConstituencyId()+"," : "NULL,");
					strTemp.append(userVoter.getConstituency() != null ? userVoter.getConstituency().getConstituencyId() : "NULL");
					strTemp.append(");\n");
					str.append(strTemp);
				}catch(Exception e)
				{
					LOG.error("Exception occured in inserting user_voter_details Data with user_voter_details ID - "+userVoter.getUserVoterDetailsId()+" Exception - "+e);
				}
			}
			str.append("\n");
		}
	}catch(Exception e){}
	
	LOG.info("user voter details table data Completed...");
	
	try{
		List<Object[]> voterModificationList = voterModificationDAO.getVoterModificationDetailsOfAConstituencyForAPublication(constituencyId,publicationId);
		
		if(voterModificationList != null && voterModificationList.size() > 0)
		{
			StringBuilder strTemp = null;
			for(Object[] params : voterModificationList)
			{
				try{
					strTemp = new StringBuilder();
					strTemp.append("INSERT INTO voter_modification(voter_modification_id,voter_id,status,part_no,constituency_id,publication_date_id,voter_status_id) VALUES (");
					strTemp.append(params[0]+","+params[1]+",'"+params[2]+"',"+params[3]+","+params[4]+","+params[5]+","+params[6]+");\n");
					str.append(strTemp);
				}catch(Exception e)
				{
					LOG.error("Exceprtion occured in VoterModification insert with voter_modification_id - "+params[0]+" Exception is -"+e);
				}
			}
			str.append("\n");
		}
	}catch(Exception e)
	{
		LOG.error("Exception Occured in Voter Modification Inserts");
	}
	
	LOG.info("voter modification table data Completed...");
	
	try{
		List<Object[]> voterModificationInfoList = voterModificationInfoDAO.getVoterModificationInfoOfAConstituencyForAPublication(constituencyId,publicationId);
		
		if(voterModificationInfoList != null && voterModificationInfoList.size() > 0)
		{
			StringBuilder strTemp = null;
			for(Object[] params : voterModificationInfoList)
			{
				try{
					strTemp = new StringBuilder();
					strTemp.append("INSERT INTO voter_modification_info(voter_modification_info_id,report_level_id,report_level_value,publication_date_id,constituency_id,voter_status_id,total_voters,male_voters,female_voters) VALUES (");
					strTemp.append(params[0]+","+params[1]+","+params[2]+","+params[3]+","+params[4]+","+params[5]+","+params[6]+","+params[7]+","+params[8]+");\n");
					str.append(strTemp);
				}catch(Exception e)
				{
					LOG.error("Exceprtion occured in VoterModificationInfo insert with voter_modification_info_id - "+params[0]+" Exception is -"+e);
				}
			}
			str.append("\n");
		}
	}catch(Exception e)
	{
		LOG.error("Exception Occured in Voter Modification Info Inserts");
	}
	
	LOG.info("voter modification info table data Completed...");
	
	try{
		List<Object[]> voterModificationAgeInfoList = voterModificationAgeInfoDAO.getVoterModificationAgeInfoDetailsOfAConstituencyForAPublication(constituencyId,publicationId);
		
		if(voterModificationAgeInfoList != null && voterModificationAgeInfoList.size() > 0)
		{
			StringBuilder strTemp = null;
			for(Object[] params : voterModificationAgeInfoList)
			{
				try{
					strTemp = new StringBuilder();
					strTemp.append("INSERT INTO voter_modification_age_info(voter_modification_age_info_id,voter_modification_info_id,voter_age_range_id,total_voters,male_voters,female_voters) VALUES (");
					strTemp.append(params[0]+","+params[1]+","+params[2]+","+params[3]+","+params[4]+","+params[5]+");\n");
					str.append(strTemp);
				}catch(Exception e)
				{
					LOG.error("Exceprtion occured in VoterModificationAgeInfo insert with voter_modification_age_info_id - "+params[0]+" Exception is -"+e);
				}
			}
			str.append("\n");
		}
	}catch(Exception e)
	{
		LOG.error("Exception Occured in Voter Modification Age Info Inserts");
	}
	
	LOG.info("voter modification age info table data Completed...");
	
	try{
		List<Object[]> partiesListForVT = votingTrendzPartiesResultDAO.getpartiesListForVotingTrendz(constituencyId);
		
		if(partiesListForVT != null && partiesListForVT.size() > 0)
		{
			StringBuilder strTemp = new StringBuilder();
			List<String> partiesList = new ArrayList<String>(0);
			
			for(Object[] params : partiesListForVT)
			{
				partiesList.add(params[1].toString());
				strTemp.append("INSERT INTO voting_trendz_party(party_id,party) VALUES ("+params[0]+",'"+params[1]+"');\n");
			}
			strTemp.append("\n");
			
			List<VotingTrendz> VTList = votingTrendzDAO.getVotingTrendzList(constituencyId);
			List<VotingTrendzPartiesResult> VTPRList = votingTrendzPartiesResultDAO.getVotingTrendzPartiesResultList(constituencyId);
			
			strTemp.append("CREATE TABLE parties_voting_trendz('parties_voting_trendz_id' INTEGER PRIMARY KEY  NOT NULL,'constituency_id' INTEGER, 'report_level_id' INTEGER, 'report_level_value' INTEGER, 'year' INTEGER, 'total_booths' INTEGER, 'total_votes' INTEGER, 'votes_polled' INTEGER, 'election_type_id' INTEGER, 'order_no' INTEGER");
			for(String pstr : partiesList)
				strTemp.append(",'"+pstr+"' INTEGER");
			strTemp.append(");\n");
			
			for(VotingTrendz votingTrendz : VTList)
			{
				try{
				strTemp.append("INSERT INTO parties_voting_trendz(parties_voting_trendz_id,constituency_id,report_level_id,report_level_value,year,total_booths,total_votes,votes_polled,election_type_id,order_no");
				for(String pstr : partiesList)
					strTemp.append(",'"+pstr+"'");
				strTemp.append(") VALUES (");
				strTemp.append(votingTrendz.getVotingTrendzId()+","+votingTrendz.getConstituency().getConstituencyId()+","+votingTrendz.getVoterReportLevel().getVoterReportLevelId()+","+votingTrendz.getReportLevelValue()+","+votingTrendz.getYear()+",");
				strTemp.append(votingTrendz.getTotalBooths()+","+votingTrendz.getTotalVotes()+","+votingTrendz.getVotesPolled()+","+votingTrendz.getElectionType().getElectionTypeId()+","+votingTrendz.getOrderNo());
				for(String pstr : partiesList)
					strTemp.append(","+getPartiesResultFromVTPRList(VTPRList,votingTrendz.getVotingTrendzId(),pstr));
				strTemp.append(");\n");
				}catch(Exception e)
				{
					LOG.error("Exception occured in inserting parties_voting_trendz with - "+votingTrendz.getVotingTrendzId());
					LOG.error("Exception is - "+e);
				}
			}
			str.append(strTemp);
		}
		
	}catch(Exception e)
	{
		LOG.error("Exception Occured in Parties Voting Trendz Inserts."+e);
	}
	
	LOG.info("parties voting trendz table data Completed...");
	
	try{
		List<PublicationDate> publicationsList = publicationDateDAO.getAll();
		
		if(publicationsList != null && publicationsList.size() > 0)
		{
			StringBuilder strTemp = new StringBuilder();
			for(PublicationDate publicationDate : publicationsList)
			{
				try{
					strTemp.append("INSERT INTO publication_date(publication_date_id,name,month,year) VALUES (");
					strTemp.append(publicationDate.getPublicationDateId()+",'"+publicationDate.getName()+"',"+publicationDate.getMonth()+","+publicationDate.getYear()+");\n");
				}catch(Exception e){	LOG.error(e);	}
			}
			strTemp.append("\n");
			str.append(strTemp);
		}
	}catch(Exception e)
	{
		LOG.error("Exception Occured in Publication Date Inserts."+e);
	}
	
	LOG.info("publication date table data Completed...");
	
	try{
		List<Cadre> cadreList = cadreDAO.getCadreDetailsInAConstituency(1L, constituencyId);
		
		if(cadreList != null && cadreList.size() > 0)
		{
			List<Long> cadreIdsList = new ArrayList<Long>(0);
			for(Cadre cadre : cadreList)
				cadreIdsList.add(cadre.getCadreId());
			Map<Long,Long> cadreCasteMap = getCadreCasteMap(cadreIdsList);
			for(Cadre cadre : cadreList)
			{
				try{
					StringBuilder strTemp = new StringBuilder();
					strTemp.append("INSERT INTO user_address(user_address_id,state_id,district_id,constituency_id,tehsil_id,hamlet_id,local_election_body_id,ward_id,booth_id,booth_part_no) VALUES (");
					strTemp.append(cadre.getCurrentAddress().getUserAddressId()+",");
					strTemp.append(cadre.getCurrentAddress().getState() != null ? cadre.getCurrentAddress().getState().getStateId()+"," : "null,");
					strTemp.append(cadre.getCurrentAddress().getDistrict() != null ? cadre.getCurrentAddress().getDistrict().getDistrictId()+"," : "null,");
					strTemp.append(cadre.getCurrentAddress().getConstituency() != null ? cadre.getCurrentAddress().getConstituency().getConstituencyId()+"," : "null,");
					strTemp.append(cadre.getCurrentAddress().getTehsil() != null ? cadre.getCurrentAddress().getTehsil().getTehsilId()+"," : "null,");
					strTemp.append(cadre.getCurrentAddress().getHamlet() != null ? cadre.getCurrentAddress().getHamlet().getHamletId()+"," : "null,");
					strTemp.append(cadre.getCurrentAddress().getLocalElectionBody() != null ? cadre.getCurrentAddress().getLocalElectionBody().getLocalElectionBodyId()+"," : "null,");
					strTemp.append(cadre.getCurrentAddress().getWard() != null ? cadre.getCurrentAddress().getWard().getConstituencyId()+"," : "null,");
					strTemp.append(cadre.getCurrentAddress().getBooth() != null ? cadre.getCurrentAddress().getBooth().getBoothId()+"," : "null,");
					strTemp.append(cadre.getCurrentAddress().getBooth() != null ? cadre.getCurrentAddress().getBooth().getPartNo() : "null");
					strTemp.append(");\n");
					str.append(strTemp);
				}catch(Exception e)
				{
					LOG.error("Exception Occured in inserting user_address table",e);
				}
			}
			str.append("\n");
			
			LOG.info("User Address For Cadre table data Completed...");
			
			for(Cadre cadre : cadreList)
			{
				try{
					StringBuilder strTemp = new StringBuilder();
					strTemp.append("INSERT INTO cadre(cadre_id,firstname,lastname,relative_name,gender,date_of_birth,age,blood_group_id,no_of_family_members,no_of_voters,mobile_no,email,house_no,street_name,user_address_id,education_id,occupation_id,");
					strTemp.append("caste_state_id,member_type,voter_id,image_path,cadre_level_id,cadre_level_value,pincode,annual_income) VALUES (");
					strTemp.append(cadre.getCadreId()+",");
					strTemp.append("'"+cadre.getFirstName()+"',");
					strTemp.append("'"+cadre.getLastName()+"',");
					strTemp.append("'"+cadre.getFatherOrSpouseName()+"',");
					strTemp.append("'"+cadre.getGender()+"',");
					strTemp.append(cadre.getDateOfBirth() != null ? "'"+cadre.getDateOfBirth().toString()+"'," : "null,");
					strTemp.append("null,");
					strTemp.append(cadre.getBloodGroup() != null ? cadre.getBloodGroup().getBloodGroupId()+"," : "null,");
					strTemp.append(cadre.getNoOfFamilyMembers() != null ? cadre.getNoOfFamilyMembers()+"," : "0,");
					strTemp.append(cadre.getNoOfVoters() != null ? cadre.getNoOfVoters()+"," : "0,");
					strTemp.append(cadre.getMobile() != null ? "'"+cadre.getMobile().toString()+"'," : "null,");
					strTemp.append(cadre.getEmail() != null ? "'"+cadre.getEmail()+"'," : "null,");
					strTemp.append(cadre.getCurrentAddress().getHouseNo() != null ? "'#"+cadre.getCurrentAddress().getHouseNo()+"'," : "null,");
					strTemp.append(cadre.getCurrentAddress().getStreet() != null ? "'"+cadre.getCurrentAddress().getStreet()+"'," : "null,");
					strTemp.append(cadre.getCurrentAddress().getUserAddressId()+",");
					strTemp.append(cadre.getEducation() != null ? cadre.getEducation().getEduQualificationId()+"," : "null,");
					strTemp.append(cadre.getOccupation() != null ? cadre.getOccupation().getOccupationId()+"," : "null,");
					strTemp.append(cadreCasteMap.get(cadre.getCadreId()) != null ? cadreCasteMap.get(cadre.getCadreId()).toString()+"," :"null,");
					strTemp.append("'"+cadre.getMemberType()+"',");
					strTemp.append(cadre.getVoter() != null ? cadre.getVoter().getVoterId()+"," : "null,");
					strTemp.append(cadre.getImage() != null && cadre.getImage().trim().length() > 0 ? cadre.getImage()+"," : "null,");
					strTemp.append(cadre.getCadreLevel() != null ? cadre.getCadreLevel().getCadreLevelID()+"," : "null,");
					strTemp.append(cadre.getCadreLevelValue() != null ? cadre.getCadreLevelValue()+"," : "null,");
					strTemp.append(cadre.getCurrentAddress().getPinCode() != null ? "'"+cadre.getCurrentAddress().getPinCode()+"'," : "'',");
					strTemp.append(cadre.getAnnualIncome() != null ? cadre.getAnnualIncome() : "null");
					strTemp.append(");\n");
					str.append(strTemp);
				}catch(Exception e)
				{
					LOG.error("Exception Occured in inserting cadre table",e);
				}
			}
			str.append("\n");
			
			LOG.info("Cadre table data Completed...");
		}
	}catch(Exception e)
	{
		LOG.error("Exception Occured in Cadre Tables Inserts");
	}
	
	try{
		List<InfluencingPeoplePosition> influencingPeoplePostionsList = influencingPeoplePositionDAO.getPositionNameByUserId(1L);
		
		if(influencingPeoplePostionsList != null && influencingPeoplePostionsList.size() > 0)
		{
			for(InfluencingPeoplePosition position : influencingPeoplePostionsList)
			{
				try{
					StringBuilder strTemp = new StringBuilder();
					strTemp.append("INSERT INTO influencing_people_position(influencing_people_position_id,position_type) VALUES (");
					strTemp.append(position.getInfluencingPeoplePositionId()+",");
					strTemp.append(position.getPosition() != null ? "'"+position.getPosition()+"'" : "NULL");
					strTemp.append(");\n");
					str.append(strTemp);
				}catch(Exception e)
				{
					LOG.error("Exception Occured in Creating Influencing People Position Table Data");
					LOG.error("Exception is - ",e);
				}
			}
			str.append("\n");
			LOG.info("Influencing People Position Table Data Completed...");
		}
	}catch(Exception e)
	{
		LOG.error("Exception Ocuured in Inserting Influencing People Position Table");
	}
	
	try{
		List<InfluencingPeople> influencingPeopleList = influencingPeopleDAO.getInfluencingPeopleInAConstituencyForAUser(1L,constituencyId);
		
		if(influencingPeopleList != null && influencingPeopleList.size() > 0)
		{
			Map<String,Long> levelsMap = getCadreValuesMap();
			for(InfluencingPeople people : influencingPeopleList)
			{
				try{
					StringBuilder strTemp = new StringBuilder();
					strTemp.append("INSERT INTO user_address(user_address_id,state_id,district_id,constituency_id,tehsil_id,hamlet_id,local_election_body_id,ward_id,booth_id,booth_part_no) VALUES (");
					strTemp.append(people.getUserAddress().getUserAddressId()+",");
					strTemp.append(people.getUserAddress().getState() != null ? people.getUserAddress().getState().getStateId()+"," : "null,");
					strTemp.append(people.getUserAddress().getDistrict() != null ? people.getUserAddress().getDistrict().getDistrictId()+"," : "null,");
					strTemp.append(people.getUserAddress().getConstituency() != null ? people.getUserAddress().getConstituency().getConstituencyId()+"," : "null,");
					strTemp.append(people.getUserAddress().getTehsil() != null ? people.getUserAddress().getTehsil().getTehsilId()+"," : "null,");
					strTemp.append(people.getUserAddress().getHamlet() != null ? people.getUserAddress().getHamlet().getHamletId()+"," : "null,");
					strTemp.append(people.getUserAddress().getLocalElectionBody() != null ? people.getUserAddress().getLocalElectionBody().getLocalElectionBodyId()+"," : "null,");
					strTemp.append(people.getUserAddress().getWard() != null ? people.getUserAddress().getWard().getConstituencyId()+"," : "null,");
					strTemp.append(people.getUserAddress().getBooth() != null ? people.getUserAddress().getBooth().getBoothId()+"," : "null,");
					strTemp.append(people.getUserAddress().getBooth() != null ? people.getUserAddress().getBooth().getPartNo(): "null");
					strTemp.append(");\n");
					str.append(strTemp);
					
				}catch(Exception e)
				{
					LOG.error("Exception occured in Creating User Address table for Influencing People, Exception is - ",e);
				}
			}
			
			str.append("\n");
			LOG.info("User Address table for Influencing people data Completed...");
			
			for(InfluencingPeople people : influencingPeopleList)
			{
				try{
					StringBuilder strTemp = new StringBuilder();
					strTemp.append("INSERT INTO influencing_people(influencing_people_id,firstname,lastname,relative_name,gender,mobile,email,house_no,street_name,pincode,user_address_id,occupation_id,party_id,influencing_position_id,caste_state_id,influencing_scope_id,influencing_scope_value,voter_id) VALUES (");
					strTemp.append(people.getInfluencingPeopleId()+",");
					strTemp.append(people.getFirstName() != null ? "'"+people.getFirstName()+"'," : "NULL,");
					strTemp.append(people.getLastName() != null ? "'"+people.getLastName()+"'," : "NULL,");
					strTemp.append(people.getFatherOrSpouseName() != null ? "'"+people.getFatherOrSpouseName()+"'," : "NULL,");
					strTemp.append(people.getGender() != null ? "'"+people.getGender()+"'," : "NULL,");
					strTemp.append(people.getPhoneNo() != null ? "'"+people.getPhoneNo()+"'," : "NULL,");
					strTemp.append(people.getEmail() != null ? "'"+people.getEmail()+"'," : "NULL,");
					strTemp.append(people.getUserAddress() != null && people.getUserAddress().getHouseNo() != null ? "'"+people.getUserAddress().getHouseNo() +"'," : "NULL,");
					strTemp.append(people.getUserAddress() != null && people.getUserAddress().getStreet() != null ? "'"+people.getUserAddress().getStreet() +"'," : "NULL,");
					strTemp.append(people.getUserAddress() != null && people.getUserAddress().getPinCode() != null ? "'"+people.getUserAddress().getPinCode() +"'," : "NULL,");
					strTemp.append(people.getUserAddress() != null ? people.getUserAddress().getUserAddressId()+"," : "NULL,");
					strTemp.append(people.getOccupation() != null ? people.getOccupation()+"," : "NULL,");
					strTemp.append(people.getParty() != null ? people.getParty().getPartyId()+"," : "NULL,");
					strTemp.append(people.getInfluencingPeoplePosition() != null ? people.getInfluencingPeoplePosition().getInfluencingPeoplePositionId()+"," : "NULL,");
					strTemp.append("null,");
					strTemp.append(people.getInfluencingScope() != null ? levelsMap.get(people.getInfluencingScope())+"," : "NULL,");
					strTemp.append(people.getInfluencingScopeValue() != null ? people.getInfluencingScopeValue()+"," : "NULL,");
					strTemp.append(people.getVoter() != null ? people.getVoter().getVoterId()+"" : "NULL");
					strTemp.append(");\n");
					str.append(strTemp);
					
				}catch(Exception e)
				{
					LOG.error("Exception occured in Creating User Address table for Influencing People, Exception is - ",e);
				}
			}
			str.append("\n");
			LOG.info("Influencing people table data Completed...");
			
			try{
				StringBuilder strTemp = new StringBuilder();
				strTemp.append("INSERT INTO user_notes_status(user_notes_status_id,status) VALUES (1,'New');\n");
				strTemp.append("INSERT INTO user_notes_status(user_notes_status_id,status) VALUES (2,'Progress');\n"); 
				strTemp.append("INSERT INTO user_notes_status(user_notes_status_id,status) VALUES (3,'Pending');\n"); 
				strTemp.append("INSERT INTO user_notes_status(user_notes_status_id,status) VALUES (4,'Completed');\n"); 
				
				str.append(strTemp);
			}catch(Exception e)
			{
				LOG.error("Exception Occured in inserting User notes status records.");
				LOG.error("Exception is - ",e);
			}
			
			str.append("\n");
			LOG.info("User notes status table data Completed...");
			
			try{
				LOG.info("Hamlet Booth table data started...");
				List<Object[]> hamletBoothList = userVoterDetailsDAO.getHamletBoothInfo(constituencyId,publicationId,1L);
				if(hamletBoothList != null && hamletBoothList.size() > 0)
				{
					int hbIndex = 0;
					StringBuilder strTemp = new StringBuilder();
					for(Object[] params : hamletBoothList)
					{
						try{
						strTemp.append("INSERT INTO hamlet_booth(hamlet_booth_id, hamlet_id, booth_id, publication_date_id) VALUES (");
						strTemp.append(++hbIndex+",");
						strTemp.append(params[0].toString()+",");
						strTemp.append(params[1].toString()+",");
						strTemp.append(params[2].toString()+");\n");
						}catch(Exception e)
						{
							LOG.error(e);
						}
					}
					str.append(strTemp);
					str.append("\n");
					LOG.info("Hamlet Booth table data Completed...");
				}
			}catch(Exception e)
			{
				LOG.error("Exception Occured in inserting Hamlet Booth Table.");
				LOG.error("Exception is - ",e);
			}
			
			str.append("\n");
			LOG.info("Hamlet Booth table data Completed...");
		}
	}catch(Exception e)
	{
		LOG.error("Exception Occured in User Notes Status Table Inserting");
		LOG.error("Exception is - ",e);
	}
	
	outPut3.write(str.toString());
	outPut3.close();
	
	str = new StringBuilder();
	
	try{
		LOG.debug("Tehsil Constituency Table Inserting Started");
		List<Object[]> tehsilConstituencyList = delimitationConstituencyMandalDAO.getAssemblyConstituencyAndMandalsInAState(constituencyDAO.get(constituencyId).getState().getStateId());
		
		if(tehsilConstituencyList != null && tehsilConstituencyList.size() > 0)
		{
			StringBuilder strTemp = new StringBuilder();
			int tcIndex = 0;
			for(Object[] params : tehsilConstituencyList)
			{
			try{
				strTemp.append("INSERT INTO tehsil_constituency(tehsil_constituency_id,constituency_id,tehsil_id,is_partial) VALUES (");
				strTemp.append(++tcIndex+",");
				strTemp.append(params[0].toString()+",");
				strTemp.append(params[1].toString()+",");
				strTemp.append(params[1].toString().equalsIgnoreCase("1") ? "'N'" : "'Y'");
				strTemp.append(");\n");
				}catch(Exception e){
					LOG.error(e);
				}
			}
			str.append(strTemp);
			str.append("\n");
			LOG.info("Tehsil Constituency Table data Completed...");
		}
	}catch(Exception e)
	{
		LOG.error("Exception Occured in Tehsil Constituency Table Inserting");
		LOG.error("Exception is - ",e);
	}
	
	try{
		LOG.debug("Partial Booth Panchayat Table Inserting Started");
		List<PartialBoothPanchayat> pbpList = partialBoothPanchayatDAO.getPartialBoothsInAConstituency(constituencyId);
		
		if(pbpList !=null && pbpList.size() > 0)
		{
			StringBuilder strTemp = new StringBuilder();
			for(PartialBoothPanchayat partialBoothPanchayat : pbpList)
			{
				try{
					strTemp.append("INSERT INTO partial_booth_panchayat(partial_booth_panchayat_id,panchayat_id,booth_id,description,hamlet_id) VALUES (");
					strTemp.append(partialBoothPanchayat.getPartialBoothPanchayatId()+",");
					strTemp.append(partialBoothPanchayat.getPanchayat() != null ? partialBoothPanchayat.getPanchayat().getPanchayatId().toString()+"," : "NULL,");
					strTemp.append(partialBoothPanchayat.getBooth() != null ? partialBoothPanchayat.getBooth().getBoothId().toString()+"," : "NULL,");
					strTemp.append(partialBoothPanchayat.getDescription() != null ? "'"+partialBoothPanchayat.getDescription()+"'," : "'',");
					strTemp.append(partialBoothPanchayat.getHamlet() != null ? partialBoothPanchayat.getHamlet().getHamletId() : "NULL");
					strTemp.append(");\n");
				}catch(Exception e)
				{
					LOG.error(e);
				}
			}
			str.append(strTemp);
			str.append("\n");
			LOG.info("Partial Booth Panchayat Table Inserting Completed...");
		}
		
	}catch(Exception e)
	{
		LOG.error("Exception Occured in Partial Booth Panchayat Table Inserting");
		LOG.error("Exception is - ",e);
	}
	//Added by mahesh
	try{
		
		String constituencyType = constituencyDAO.getConstituencyAreaType(constituencyId);
		if(constituencyType != null && !constituencyType.equalsIgnoreCase("RURAL")){
			LOG.debug("Assembly Local Election Body,ward,ward_booth Table Inserting Started");
			List<Object[]> assemblyLocalElectionBodieList = assemblyLocalElectionBodyDAO.getAssemblyLocationElectionBodyList(constituencyId);
			if(assemblyLocalElectionBodieList != null && assemblyLocalElectionBodieList.size() > 0)
			{
				//int id = 0;
				Set<Long> wardIds = new HashSet<Long>();
			  for(Object[] params:assemblyLocalElectionBodieList){
				 str.append("INSERT INTO assembly_local_election_body(assembly_local_election_body_id,local_election_body_id,constituency_id) " +
				 		"VALUES ('"+(Long)params[0]+"','"+(Long)params[1]+"','"+(Long)params[2]+"');\n");
				 Long electionTypeId = (Long)params[3];
				 if(electionTypeId.longValue() == 7){
					 Map<Long,String> wards = new HashMap<Long,String>();
					 List<Object[]> wardDetails = boothDAO.getWardDetailsByLocalEleBodyId((Long)params[1], publicationId, constituencyId);
					 for(Object[] ward:wardDetails){
						 //id=id+1;
						 wards.put((Long)ward[0], ward[1].toString()+"("+ward[2].toString()+")");
						 //str.append("INSERT INTO ward_booth(ward_booth_id,ward_id,booth_id,publication_date_id) " +
							 		//"VALUES ('"+id+"','"+(Long)ward[0]+"','"+(Long)ward[4]+"','"+latestPublicationId+"');\n");
					 
					 }
					 if(wards.size() > 0){
						for(Long key:wards.keySet()){
						 str.append("INSERT INTO ward(ward_id,name,local_election_body_id) " +
							 		"VALUES ('"+key+"','"+wards.get(key)+"','"+(Long)params[1]+"');\n");
						}
					}
				 }else{
					 List<Object[]> wardDetails = userVoterDetailsDAO.getWardIdsByLocalEleBodyIdPublicationId(constituencyId,1l,publicationId,(Long)params[1]);
					 
					 for(Object[] ward:wardDetails){
						 wardIds.add((Long)ward[0]);
						 str.append("INSERT INTO ward(ward_id,name,local_election_body_id) " +
							 		"VALUES ('"+(Long)ward[0]+"','"+ward[1].toString()+"','"+(Long)ward[2]+"');\n");
					 }
				 }
			  }
			  /*if(wardIds.size() > 0){
				  List<Object[]> wardBooths = userVoterDetailsDAO.getBoothsForCustomWardIdsList(new ArrayList<Long>(wardIds),constituencyId,latestPublicationId,1l);
				  for(Object[] booth:wardBooths){
					  id=id+1;
					  str.append("INSERT INTO ward_booth(ward_booth_id,ward_id,booth_id,publication_date_id) " +
						 		"VALUES ('"+id+"','"+(Long)booth[0]+"','"+(Long)booth[1]+"','"+latestPublicationId+"');\n");
				  
				  }
			  }*/
			  List<Object[]> wardBoothDatails = wardBoothDAO.getWardBothData(publicationId,constituencyId);
			  if(wardBoothDatails != null && wardBoothDatails.size() >0){
				  for(Object[] ward:wardBoothDatails){
				  str.append("INSERT INTO ward_booth(ward_booth_id,ward_id,booth_id,publication_date_id) " +
					 		"VALUES ('"+(Long)ward[0]+"','"+(Long)ward[1]+"','"+(Long)ward[2]+"','"+publicationId+"');\n");
				  }
			  }
			}
			str.append("\n");
			
			LOG.info("Assembly Local Election Body,ward,ward_booth data Completed...");
			
		}
		
	}catch(Exception e)
	{
		LOG.error("Exception Occured in Assembly Local Election Body,ward,ward_booth Table Inserting ",e);
	}
	//End 

	/*user,user_profile,user_access DB Scripts Added*/
	try{
		DateUtilService dateUtilService = new DateUtilService();
		int id=1;
		 str.append("INSERT INTO user(user_id,username,password,unique_code) VALUES('"+id+"','"+reVo.getUserName()+"','"+reVo.getPassword()+"','"+reVo.getUniqueCode()+"');\n");
		 str.append("INSERT INTO user_profile(user_profile_id,first_name,last_name,gender) VALUES('"+id+"','"+reVo.getFirstName()+"','"+reVo.getLastName()+"','"+reVo.getGender().toString()+"');\n");
		 str.append("INSERT INTO user_access(user_access_id,user_id,is_authorised,app_id,mac_address,device_id,last_authorised_time) VALUES('"+id+"','"+id+"','"+IConstants.TRUE+"','"+reVo.getAppId()+"','"+reVo.getAddress()+"','"+reVo.getMobile()+"','"+dateUtilService.getCurrentDateAndTime()+"');\n");
		 
		 MobileAppUser superAdmin = mobileAppUserDAO.get(reVo.getSuperAdminId());
		 if(superAdmin != null)
		 {
			 str.append("INSERT INTO user(user_id,username,password,unique_code) VALUES(-1,'"+superAdmin.getUserName()+"','"+superAdmin.getPassword()+"','"+superAdmin.getUniqueCode()+"');\n");
		 }
		 str.append("\n");
		LOG.info(" user, user_profile,user_access Table data Completed... ");
	}
	catch (Exception e) {
		LOG.error(" Exception Occured in user user_profile,user_access Table Inserting ",e);
	}
	/*user,user_profile,user_access  End*/
	
	try{
		
		List<Locality> localityList = userVoterDetailsDAO.getAllLocatiesInAConstituency(constituencyId,publicationId,1l);
		
		if(localityList != null && localityList.size() > 0)
		{
			StringBuilder strTemp = new StringBuilder();
			LOG.info("Loaclity Table Inserting Started...");
			for(Locality locality : localityList)
			{
				try{
					strTemp.append("INSERT INTO locality(locality_id,name,hamlet_id,local_election_body_id,ward_id,constituency_id) VALUES (");
					strTemp.append(locality.getLocalityId()+",");
					strTemp.append(locality.getName() != null ? "'"+locality.getName()+"'," : "'',");
					strTemp.append(locality.getHamlet() != null ? locality.getHamlet().getHamletId()+"," : "NULL,");
					strTemp.append(locality.getLocalElectionBody() != null ? locality.getLocalElectionBody().getLocalElectionBodyId()+"," : "NULL,");
					strTemp.append(locality.getWard() != null ? locality.getWard().getConstituencyId()+"," : "NULL,");
					strTemp.append(constituencyId.toString()+");\n");
					
				}catch(Exception e)
				{
					LOG.error("Exception occured in inserting Locality Table");
					LOG.error("Exception is - "+e);
				}
			}
			str.append(strTemp);
			str.append("\n");
			LOG.info("Loaclity Table Inserting Completed...");
		}
	}catch(Exception e)
	{
		LOG.error("Exception occured in inserting Locality Table");
		LOG.error("Exception is - "+e);
	}
	
	try{
		
		WebServiceBaseUrl url = webServiceBaseUrlDAO.getBaseUrlDataForAnApp("IPAD");
		
		if(url != null)
		{
			LOG.info("Web Service Base URL Table Data Inserting Started...");
			str.append("INSERT INTO webservice_base_url(webservice_base_url_id,url) VALUES (");
			str.append(url.getWebServiceBaseUrlId()+",'");
			str.append(url.getUrl()+"');\n");
			str.append("\n");
			LOG.info("Web Service Base URL Table Data Inserting Completed...");
		}
		}catch(Exception e)
		{
			LOG.error("Exception occured in Web Service Base URL Table Data Inserting");
			LOG.error("Exception is - "+e);
		}
	
	try
	{	
		outPut4.write(str.toString());
		outPut4.close();
		resultStatus.setResultCode(0);
		System.gc();
	}catch(Exception e)
	{
		LOG.error("Exception ocuured in writing output - exception is ",e);
		System.gc();
	}
	
	 try{
		 
		 for(File rf : destDir.listFiles())
			 addToZipFile(rf.getAbsolutePath(), zos);
		 zos.close();
		 fos.close();
	 }catch(Exception e)
	 {
		 LOG.error("Exception Occured in Zipping Files");
	 }
	 resultStatus.setMessage("/SQLITE_DB/"+constituencyName+"_"+date+".zip");
	 return resultStatus;
	}catch (Exception e) {
		System.gc();
	 LOG.error("Exception Occured in createDataDumpFileForSelectedConstituency() method, Exception - ",e);
	 resultStatus.setResultCode(1);
	 return resultStatus;
	}
 }
 
  	
  	public Long getPartiesResultFromVTPRList(List<VotingTrendzPartiesResult> VTPRList, Long votingTrendzId, String partyName)
  	{
  		try{
  			for(VotingTrendzPartiesResult votingTrendzPartiesResult : VTPRList)
  			{
  				try{
  				if(votingTrendzPartiesResult.getVotingTrendz().getVotingTrendzId().equals(votingTrendzId) &&
  					votingTrendzPartiesResult.getParty().getShortName().equalsIgnoreCase(partyName))
  					return votingTrendzPartiesResult.getVotesGained();
  				}catch(Exception e){}
  			}
  			return 0L;
  		}catch(Exception e)
  		{
  			LOG.error("Exception occured in getPartiesResultFromVTPRList Method, with votingTrendzId = "+votingTrendzId);
  			LOG.error("Exception is",e);
  			return 0L;
  		}
  	}
  	
  	public Map<String,Long> getCadreValuesMap()
  	{
  		Map<String,Long> map = new HashMap<String, Long>(0);
  		try{
  			List<CadreLevel> levelsList = cadreLevelDAO.getCadreLevelList();
  			
  			if(levelsList != null && levelsList.size() > 0)
  			{
  				for(CadreLevel level : levelsList)
  				map.put(level.getLevel(),level.getCadreLevelID());
  			}
  			return map;
  		}catch(Exception e)
  		{
  			LOG.error("Exception Occured in getCadreValuesMap(), Exception is - ",e);
  			return map;
  		}
  	}
  	
  	public ResultStatus saveUserData(final RegistrationVO registrationVO)
  	{
  		final DateUtilService dateUtilService = new DateUtilService();
  		ResultStatus rs = new ResultStatus();
  		ResultStatus resultStatus = (ResultStatus) transactionTemplate.execute(new TransactionCallback() {
			public Object doInTransaction(TransactionStatus status) {
				ResultStatus rs = new ResultStatus();
		try{
			MobileAppUser mobileAppUser = null;
  			MobileAppUserAccess mobileAppUserAccess = null;
  			MobileAppUserProfile mobileAppUserProfile = null;
			List<Object> list = mobileAppUserDAO.checkUniqueCode(registrationVO.getUniqueCode());
			if(list!= null && list.size() > 0)
			{
				mobileAppUser = mobileAppUserDAO.get((Long) list.get(0));
				List<Object> accessID = mobileAppUserAccessDAO.getMobileAppUserAccesId((Long) list.get(0));
				mobileAppUserAccess = mobileAppUserAccessDAO.get((Long) accessID.get(0));
				List<Object> profileID = mobileAppUserProfileDAO.getMobileAppUserProfileId((Long) list.get(0));
				mobileAppUserProfile = mobileAppUserProfileDAO.get((Long)profileID.get(0));
			
			}
			else
			{
				mobileAppUser = new MobileAppUser();
				mobileAppUserAccess = new MobileAppUserAccess();
				mobileAppUserProfile = new MobileAppUserProfile();
			}
  			mobileAppUser.setUserName(registrationVO.getUserName());
  			mobileAppUser.setPassword(registrationVO.getPassword());
  			mobileAppUser.setUniqueCode(registrationVO.getUniqueCode());
  			mobileAppUser.setUser(userDAO.get(registrationVO.getRegistrationID()));
  			mobileAppUser.setEmail(registrationVO.getEmail());
  			mobileAppUser.setMobileNo(registrationVO.getMobile());
  			if(registrationVO.getSuperAdminId() > 0)
  			{
  				mobileAppUser.setType(IConstants.MOBILE_APP_USER_TYPE);
  			mobileAppUser.setMobileAppUser(mobileAppUserDAO.get(registrationVO.getSuperAdminId()));
  			}
  			else
  				mobileAppUser.setType("USER");	
  			mobileAppUser = mobileAppUserDAO.save(mobileAppUser);
  			mobileAppUserAccess.setMobileAppUser(mobileAppUser);
  			mobileAppUserAccess.setIsAuthorised("true");
  			mobileAppUserAccess.setAppId(registrationVO.getAppId());
  			mobileAppUserAccess.setMacAddress(registrationVO.getAddress());
  			mobileAppUserAccess.setDeviceId(registrationVO.getMobile());
  			mobileAppUserAccess.setLastAuthorisedTime(dateUtilService.getCurrentDateAndTime());
  			mobileAppUserAccessDAO.save(mobileAppUserAccess);
  			
  			
  			mobileAppUserProfile.setFirstName(registrationVO.getFirstName());
  			mobileAppUserProfile.setLastName(registrationVO.getLastName());
  			mobileAppUserProfile.setMobileAppUser(mobileAppUser);
  			mobileAppUserProfile.setGender(registrationVO.getGender().toString());
  			mobileAppUserProfileDAO.save(mobileAppUserProfile);
			
			
  			rs.setResultCode(ResultCodeMapper.SUCCESS);
  			}
  		catch (Exception e) {
  			LOG.error("Exception Occured in saveUserData(), Exception is - ",e);
			e.printStackTrace();
			rs.setResultCode(ResultCodeMapper.FAILURE);
		}
		return rs;
		}
		});
  		return resultStatus;
  	}
  	
  	
  	public ResultStatus checkAuthenticateUserAndUpdateLastAuthorisedTime(String userId,String macAddressId)
  	{
  		ResultStatus resultStatus = new ResultStatus();
  		try{
  			DateUtilService dateUtilService = new DateUtilService();
  			List<Object> list = mobileAppUserAccessDAO.getAuthorisedRecords(userId,macAddressId);
  			if(list != null && list.size() > 0)
  			{
  				
  				MobileAppUserAccess mobileAppUserAccess = mobileAppUserAccessDAO.get((Long)list.get(0));
  				mobileAppUserAccess.setLastAuthorisedTime(dateUtilService.getCurrentDateAndTime());
  				mobileAppUserAccessDAO.save(mobileAppUserAccess);
  				resultStatus.setResultCode(ResultCodeMapper.SUCCESS);
  			
  			}
  			else
  				resultStatus.setResultCode(ResultCodeMapper.FAILURE);
  				
  		}
  		catch(Exception e) {
			e.printStackTrace();
			resultStatus.setResultCode(ResultCodeMapper.FAILURE);
		}
		return resultStatus;
  	}
  	
  	public ResultStatus sendSmsToMobileAppUser(String mobileNo,Long mobileAppuserId,String accessKey,Long userID,String type)
	{
		ResultStatus resultStatus = new ResultStatus();
		DateUtilService date = new DateUtilService();
		String message ="";
		try{
			if(type.equalsIgnoreCase(IConstants.Request_For_Forget_Pwd_Access_Key))
		     message = "your Access key for your App : "+accessKey+"";
			else if(type.equalsIgnoreCase(IConstants.Authorisation_Access_Key))
				  message = "your Access key for your App Authorisation: "+accessKey+"";
			String [] phoneNumbers = {mobileNo.toString()};
			smsCountrySmsService.sendSmsFromAdmin(message, true, phoneNumbers);
			MobileAppUserAccessKey mobileAppUserAccessKey = new MobileAppUserAccessKey();
			mobileAppUserAccessKey.setCreatedBy(userID);
			mobileAppUserAccessKey.setCreationTime(date.getCurrentDateAndTime());
			mobileAppUserAccessKey.setIsUsed("false");
			mobileAppUserAccessKey.setMobileAppUser(mobileAppUserDAO.get(mobileAppuserId));
			mobileAppUserAccessKey.setAccessKey(accessKey);
			mobileAppUserAccessKey.setType(type);
			mobileAppUserAccessKeyDAO.save(mobileAppUserAccessKey);
			
			resultStatus.setResultCode(ResultCodeMapper.SUCCESS);
		}
		catch (Exception e) {
			resultStatus.setResultCode(ResultCodeMapper.FAILURE);
		e.printStackTrace();
		} 
		return resultStatus;
	}
	public List<SelectOptionVO> getMobileAppUsers()
	
	{
		List<SelectOptionVO> resultList = new ArrayList<SelectOptionVO>();
		try{
			List<Object[]> list = mobileAppUserDAO.getUserList();
			if(list != null && list.size() > 0)
			{
				for(Object[] params : list)
				resultList.add(new SelectOptionVO((Long)params[0],params[1].toString()));	
			}
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return resultList;
	}

  	public Map<Long,Long> getCadreCasteMap(List<Long> cadreIdsList)
  	{
  		Map<Long,Long> cadreCasteMap = new HashMap<Long, Long>(0);
  		try{
  			List<Object[]> list = userVoterDetailsDAO.getCadreCaste(cadreIdsList);
  			if(list != null && list.size() > 0)
  			{
  				for(Object[] params : list)
  				{
  					cadreCasteMap.put((Long)params[0],(Long)params[1]);
  				}
  			}
  			return cadreCasteMap;
  		}catch(Exception e)
  		{
  			LOG.error("Exception Occured in getCadreCasteMap Method");
  			LOG.error("Exception is ",e);
  			return cadreCasteMap;
  		}
  	}
  	
  	
  	public List<RegistrationVO> getMobileAppUserDetails()
  	{
  		List<RegistrationVO> result = new ArrayList<RegistrationVO>();
  		try{
  			/*firstName
  			lastName
  			userId
  			user.firstName
  			user.lastName
  			uniqueCode
  			isAuthorised
  			lastAuthorisedTime
  			mobileAppUserId*/
  			DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
  			DateUtilService date = new DateUtilService();
  			
  			List<Object[]> list = mobileAppUserAccessDAO.getMobileAppUserdetails();
  			if(list != null && list.size() > 0)
  			{
  				for(Object[] params : list)
  				{
  					long sec = 0;
  					long min = 0;
  					long hr = 0;
  					long day = 0;
  					
  					RegistrationVO registrationVO = new RegistrationVO();
  					String firstName = params[0]!=null ?params[0].toString():"";
  					String lastName = params[1]!=null ?params[1].toString():"";
  					registrationVO.setName(firstName +" "+lastName);
  					registrationVO.setRegistrationID((Long)params[2]);
  					String ufirstName = params[3]!=null ?params[3].toString():"";
  					String ulastName = params[4]!=null ?params[4].toString():"";
  					registrationVO.setUserName(ufirstName +" "+ulastName);
  					registrationVO.setUniqueCode(params[5]!=null?params[5].toString():"");
  					if(params[6].toString().equalsIgnoreCase("true"))
  					registrationVO.setAccessValue("YES");
  					else
  						registrationVO.setAccessValue("Denied");
  					Date pastTime = (Date)formatter.parse(params[7].toString());;
  					Date currentTime =date.getCurrentDateAndTime();
  					String lastAuthorisedTime = getLastAuthorisedTime(pastTime,currentTime,params[7].toString());
  					registrationVO.setDateOfBirth(lastAuthorisedTime);
  					registrationVO.setAppId(params[8]!=null?params[8].toString():"");
  					result.add(registrationVO);
  					
  					
  				}
  			}
  		}
  		catch (Exception e) {
  			LOG.error("Exception Occured in getMobileAppUserDetails Method");
  			LOG.error("Exception is ",e);
		}
		return result;
  	}
  	/** send sms to mobile App user for authorisation **/
  	public ResultStatus getMobileAppLastAuthorisedTime()
  	{
  		ResultStatus resultStatus = new ResultStatus();
  		DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		DateUtilService date = new DateUtilService();
			
  		try{
  		
  			List<Object[]> list = mobileAppUserAccessDAO.getMobileAppUserAuthorisedTime();
  			for(Object[] params : list)
				{
  				RegistrationVO registrationVO = new RegistrationVO();
				String firstName = params[0]!=null ?params[0].toString():"";
				String lastName = params[1]!=null ?params[1].toString():"";
				Date pastTime = (Date)formatter.parse(params[2].toString());;
				Date currentTime = date.getCurrentDateAndTime();
				String lastAuthorisedTime = getLastAuthorisedTime(pastTime,currentTime,params[2].toString());
			    if(lastAuthorisedTime.indexOf("days") > 0)
				     {
				    	String days = lastAuthorisedTime.substring(lastAuthorisedTime.indexOf("(")+1,lastAuthorisedTime.indexOf(")")).replaceAll("[a-zA-Z]", "");
				    	if(Long.valueOf(days.toString().trim()) >= 10)
				    	sendSmsToMobileAppUsers(firstName +" "+lastName,params[4].toString());
				     }
				}
  			
  		}
  		catch(Exception e)
  		{
  			LOG.error("Exception Occured in getMobileAppLastAuthorisedTime Method");	
  		}
		return resultStatus;
  	}
	public ResultStatus sendSmsToMobileAppUsers(String uname,String mobileNo)
  	{
  		ResultStatus resultStatus = new ResultStatus();
  		try{
  		
  			String message = "Dear "+uname+" ,your IPAD authorised 11 days ago ,Please authorised your IPAD now.Other wise you can't use the app after 15 days. ";
  			String[] mobilenoarr = new String [] {mobileNo};
  			resultStatus = smsCountrySmsService.sendSmsFromAdmin(message, true, mobilenoarr);
  		}
  		catch(Exception e)
  		{
  			LOG.error("Exception Occured in sendSmsToMobileAppUser Method");	
  		}
		return resultStatus;
  	}
  	public String getLastAuthorisedTime(Date pastTime,Date currentTime,String time)
  	{
	  		String result = "";
	  		Calendar cal = Calendar.getInstance();
	        cal.setTimeZone(TimeZone.getTimeZone("UTC"));
  		    long sec = 0;
			long min = 0;
			long hr = 0;
			long day = 0;
  		    cal.setTime(pastTime);
			long t1 =cal.getTimeInMillis();
			cal.setTime(currentTime);
			long diff = Math.abs(cal.getTimeInMillis() - t1);
			long diffInsec = diff/1000;
			if(diffInsec < 60)
			{
				sec = diffInsec;
				if(sec == 1)
					result = time+"("+sec+" second ago)";
				else
					result = time+"("+sec+" seconds ago)";
			}
			else if(diffInsec > 60 && diffInsec < 3600)	
			{
				min = diffInsec/60;
				if(min == 1)
					result = time+"("+min+" minute ago)";
				else 
					result = time+"("+min+" minutes ago)";
			}
			else if(diffInsec > 3600 && diffInsec < 86400)	
			{
				hr = diffInsec/3600;
				if(hr == 1)
					result = time+"("+hr+" hour ago)";
				else
					result = time+"("+hr+" hours ago)";	
			}
			else if(diff > 86400)	
			{
				day = diffInsec/86400;
				if(day == 1)
					result = time+"("+day+" day ago)";
				else
					result = time+"("+day+" days ago)";	
			}
			return result;
  	}
  	public ResultStatus enableOrdisableAccessByUniqueCode(List<Long> uniqueCodes,String type)
  	{
  		ResultStatus resultStatus = new ResultStatus();
  		try{
  			DateUtilService date = new DateUtilService();
  			MobileAppUserAccess mobileAppUserAccess	= null;
  			List<Long> mobileAppUserAccessIds = mobileAppUserAccessDAO.getMobileAppUserAccessIds(uniqueCodes);
  			
  			if(mobileAppUserAccessIds != null && mobileAppUserAccessIds.size() > 0)
  				for(Long id : mobileAppUserAccessIds)
  				{
  					mobileAppUserAccess = mobileAppUserAccessDAO.get(id);
  					if(type.equalsIgnoreCase("enable"))
  						mobileAppUserAccess.setIsAuthorised("true");
  					else
  					 mobileAppUserAccess.setIsAuthorised("false");
  					 mobileAppUserAccess.setLastAuthorisedTime(date.getCurrentDateAndTime());
  					 mobileAppUserAccessDAO.save(mobileAppUserAccess);
  					resultStatus.setResultCode(ResultCodeMapper.SUCCESS);
  				}
  		
  		}
  		catch (Exception e) {
  			LOG.error("Exception Occured in enableOrdisableAccessByUniqueCode Method");
  			LOG.error("Exception is ",e);
  			resultStatus.setResultCode(ResultCodeMapper.FAILURE);
			}
		return resultStatus;
  	}
  	
	public List<RegistrationVO> getMobileAppUserDetailInfo(Long mobileAppuserId)
  	{
  		List<RegistrationVO> result = new ArrayList<RegistrationVO>();
  		DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		DateUtilService date = new DateUtilService();
  		try{
  		List<Object[]> dataList = mobileAppUserAccessDAO.getMobileAppUserdetailsByMobileAppUserId(mobileAppuserId);	
  		for(Object[] params : dataList)
  		{
  			RegistrationVO registrationVO = new RegistrationVO();
  			String mobileUserFirstName = params[0]!=null? params[0].toString() : "";
  			String mobileUserLastName = params[1]!=null? params[1].toString() : "";
  			
  			registrationVO.setName(mobileUserFirstName+" "+mobileUserLastName);
  			registrationVO.setMobile(params[2]!=null?params[2].toString() : "");
  			registrationVO.setEmail(params[3]!=null?params[3].toString() : "");
  		
  			registrationVO.setUserName(params[4]!=null?params[4].toString() : "");
  			registrationVO.setPassword(params[5]!=null?params[5].toString() : "");
  			registrationVO.setUniqueCode(params[6]!=null?params[6].toString() : "");
  			if(params[7].toString().equalsIgnoreCase("true"))
				registrationVO.setAccessValue("YES");
  			else
  				registrationVO.setAccessValue("Denied");
  			    Date pastTime = (Date)formatter.parse(params[8].toString());;
				Date currentTime =date.getCurrentDateAndTime();
				String lastAuthorisedTime = getLastAuthorisedTime(pastTime,currentTime,params[8].toString());
				registrationVO.setDateOfBirth(lastAuthorisedTime);
				registrationVO.setAppId(params[10] !=null ?params[10].toString():"");
				registrationVO.setDeviceId(params[11] !=null ?params[11].toString():"");
				registrationVO.setAddress(params[12] !=null ?params[12].toString():"");
				registrationVO.setGender(params[13] !=null ?params[13].toString():"");
				result.add(registrationVO);
			}
  		List<Object[]> dataList1 = mobileAppUserAccessDAO.getSuperAdminDetailsByMobileAppUserId(mobileAppuserId);	
  		List<RegistrationVO> superAdminList = new ArrayList<RegistrationVO>();
  		if(dataList1 != null && dataList1.size() > 0)
  		{
  			for(Object[] params1 : dataList1)
  			{
  			RegistrationVO regVo = new RegistrationVO();
  			regVo.setUserName(params1[1] !=null ?params1[1].toString():"");
  			regVo.setPassword(params1[2] !=null ?params1[2].toString():"");
  			regVo.setUniqueCode(params1[3] !=null ?params1[3].toString():"");
  			
  			superAdminList.add(regVo);
  			}
  			result.get(0).setRegisteredUsersList(superAdminList);
  		}
  			
  		}
  		catch(Exception e)
  		{
  			LOG.error("Exception Occured in getMobileAppUserDetailInfo() Method in mobile Service", e);
  			
  		}
		return result;
  	}
  	
  	public Long saveSuperAdminInfoInMobileAppUser(final String uname,final String pwd,final String uniqueCode)
  	{
  		Long mobileAppUserId = 0l;
  		
  		final DateUtilService dateUtilService = new DateUtilService();
  		
  		Long value = (Long) transactionTemplate.execute(new TransactionCallback() {
			public Object doInTransaction(TransactionStatus status) {
				Long val = 0l;
		try{
			List<Object> list = mobileAppUserDAO.checkUniqueCode(uniqueCode);
			if(list != null && list.size() > 0)
			{
				val = -1l;
			}
			else
			{
  			MobileAppUser mobileAppUser = new MobileAppUser();
  			mobileAppUser.setUserName(uname);
  			mobileAppUser.setPassword(pwd);
  			mobileAppUser.setUniqueCode(uniqueCode);
  			mobileAppUser.setType(IConstants.MOBILE_APP_USER_TYPE);
  			mobileAppUser = mobileAppUserDAO.save(mobileAppUser);
  			mobileAppUser.setMobileAppUser(mobileAppUser);
  			mobileAppUser = mobileAppUserDAO.save(mobileAppUser);
  			
			}
			}
		catch (Exception e) {
			LOG.error("Exception Occured in saveUserData(), Exception is - ",e);
		e.printStackTrace();
		
	}
	return val;
	}
	});
  		if(value == 0)
  		{
  		mobileAppUserId = (Long)mobileAppUserDAO.getMobileAppUserId(uniqueCode).get(0);	
  		return mobileAppUserId;	
  		}
  		else
  		
  			return -1l;	
  		
  	}
  	public List<SelectOptionVO> getSuperAdminMobileAppUsers()
	
	{
		List<SelectOptionVO> resultList = new ArrayList<SelectOptionVO>();
		try{
			
			List<Object[]> list = mobileAppUserDAO.getSuperAdminList();
			  resultList.add(0,new SelectOptionVO(0l,"Select user"));
			if(list != null && list.size() > 0)
			{
				for(Object[] params : list)
				{
					String uname = params[1] !=null?params[1] .toString():"";
					
				    resultList.add(new SelectOptionVO((Long)params[0],uname));	
				  
				}
		
		}
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return resultList;
	}
  	
  	public List<SelectOptionVO> getPingDetails(Long mobileAppUserId)
  	{
  		List<SelectOptionVO> resultList = new ArrayList<SelectOptionVO>();
  		try{
  			List<Object[]> list = mobileAppPingingDAO.getPingingTypeIdByType(mobileAppUserId);
  			if(list != null && list.size() > 0)
  				for(Object[] params : list)
  				{
  					SelectOptionVO selectOptionVO = new SelectOptionVO();
  					selectOptionVO.setType(params[0] != null ?params[0].toString():"");
  					selectOptionVO.setValue(params[1] != null ?params[1].toString():"");
  					resultList.add(selectOptionVO);
  				}
  			
  		}
  		catch (Exception e) {
			e.printStackTrace();
		}
		return resultList;
  	}
  	
  	public List<RegistrationVO> getMobileAppUserPopulateData(Long userId)
  	{
  		
  		List<RegistrationVO> result = new ArrayList<RegistrationVO>();
  		try{
  		List list = mobileAppUserAccessDAO.getMobileAppUserdetailsByUserId(userId);
  		if(list != null && list.size() > 0)
  		{
  		Object[] params =(Object[]) list.get(0);	
  		RegistrationVO registrationVO = new RegistrationVO();
  		registrationVO.setFirstName(params[0].toString());
  		registrationVO.setLastName(params[1].toString());
  		registrationVO.setMobile(params[2].toString());
  		registrationVO.setEmail(params[3].toString());
  		registrationVO.setUserName(params[4].toString());
  		registrationVO.setPassword(params[5].toString());
  		registrationVO.setUniqueCode(params[6].toString());
  		registrationVO.setAppId(params[7].toString());
  		registrationVO.setDeviceId(params[8].toString());
  		registrationVO.setAddress(params[9].toString());
  		registrationVO.setGender(params[10].toString());
  		registrationVO.setSuperAdminId((Long)params[11]);
  		result.add(registrationVO);	
  		}
  		
  		}
  		catch(Exception e)
  		{
  			LOG.error("Exception Occured in getMobileAppUserPopulateData()", e);
  		}
		return result;
  	}
  	
  	 public  void addToZipFile(String fileName, ZipOutputStream zos)
  	 {
			try {
				File file = new File(fileName);
				FileInputStream fis = new FileInputStream(file);
				ZipEntry zipEntry = new ZipEntry(fileName);
				zos.putNextEntry(zipEntry);

				byte[] bytes = new byte[1024];
				int length;
				while ((length = fis.read(bytes)) >= 0){
					zos.write(bytes, 0, length);
				}
				zos.closeEntry();
				fis.close();
			} catch (Exception e) {
				LOG.error("Exception Occured in addToZipFile() Method ");
			}
			
		}
  	 
  	public VoterDetailsVO getVoterDetailsBasedOnVoterId(String voterCardNo){
 		List<Object[]> dtlsList=boothPublicationVoterDAO.getConstyPublicationIdByVoterId(voterCardNo);
 		//Gives ConstyId,BoothId,PublicationId,VoterId,Name,Age,Gender
 		
 		VoterDetailsVO vo=new VoterDetailsVO();
 		
 		Object[] obj=dtlsList.get(0);
 		
 		vo.setConstituencyId(Long.valueOf(obj[0].toString()));
 		vo.setBoothId(Long.valueOf(obj[1].toString()));
 		vo.setPublicationDateId(Long.valueOf(obj[2].toString()));
 		vo.setVoterId(Long.valueOf(obj[3].toString()));	
 		vo.setVoterName(obj[4].toString());
 		vo.setAge(obj[5].toString());
 		
 		if(obj[6].toString().equalsIgnoreCase("M")){
 			vo.setGender("Male");
 		}else{
 			vo.setGender("Female");
 		}
 		
 		vo.setDistrictId(Long.valueOf(obj[7].toString()));
 		if(obj[8].toString()!=null){
 			vo.setRelativeName(obj[8].toString());
 		}else{
 			vo.setRelativeName("");
 		}
 		if(obj[9].toString()!=null){
 			vo.setHouseNo(obj[9].toString());
 		}else{
 			vo.setHouseNo("");
 		}
 		
 	
 		List<Booth> boothDtls=null;
 		if(vo.getBoothId()!=null){
 			boothDtls=boothDAO.getBoothDetailsByBoothId(vo.getBoothId());
 		}
 		
 		if(boothDtls!=null && boothDtls.size()>0){
 			if(boothDtls.get(0).getTehsil()!=null){
 				vo.setTehsilId(boothDtls.get(0).getTehsil().getTehsilId());
 			}
 			
 			if(boothDtls.get(0).getLocalBody()!=null){
 				vo.setLocalElectionBodyId(boothDtls.get(0).getLocalBody().getLocalElectionBodyId());
 			}
 			
 			//IF THE SELECTED CONSTITUENCY UNDER GHMC -- GETTING WARD ID AND NAME
 			if(boothDtls.get(0).getLocalBody() != null && boothDtls.get(0).getLocalBody().getElectionType().getElectionTypeId()==7){
 				vo.setWardId(boothDtls.get(0).getLocalBodyWard().getLocalElectionBodyWard().getLocalElectionBodyWardId());
 				vo.setWardName(boothDtls.get(0).getLocalBodyWard().getLocalElectionBodyWard().getWardName());
 			}
 		}
 		
 		
 		List<UserVoterDetails> uvDtls=userVoterDetailsDAO.getVoterDetailsByVoterId(vo.getVoterId(),1l);
 		
 		if(uvDtls!=null && uvDtls.size()>0){
 			UserVoterDetails uv=uvDtls.get(0);
 			
 			if(uv.getCasteState()!=null){
 				Long casteCategoryId=uv.getCasteState().getCasteCategoryGroup().getCasteCategory().getCasteCategoryId();
 				Long socialCategoryId=0l;
 				if(casteCategoryId==1l){
 					socialCategoryId=5l;
 				}else if(casteCategoryId==2l){
 					socialCategoryId=3l;
 				}else if(casteCategoryId==3l){
 					socialCategoryId=2l;
 				}else if(casteCategoryId==4l){
 					socialCategoryId=1l;
 				}
 				vo.setCasteGroupId(socialCategoryId);
 				vo.setCaste(uv.getCasteState().getCaste().getCasteName());
 			}else{
 				vo.setCaste("");
 				vo.setCasteGroupId(0l);
 			}
 			
 			if(uv.getHamlet()!=null){
 				vo.setHamletId(uv.getHamlet().getHamletId());
 			}
 			
 			if(uv.getWard()!=null){
 				vo.setWardId(uv.getWard().getConstituencyId());
 			}
 			if(uv.getMobileNo()!=null){
 				vo.setMobileNo(uv.getMobileNo());
 			}
 			
 		}
 		return vo;
 	}
  	
  	public String replaceSpecialChars(String str)
  	{
  		try{
  			String newStr = "";
  			
  			char[] charArray = str.toCharArray();
  			for(Character C : charArray)
			{
				if(Character.isLetter(C) || C.toString().equals(" "))
					newStr = newStr+C.toString();
			}
  			return newStr.trim();
  		}catch(Exception e)
  		{
  			return str;
  		}
  	}
  	public List<SelectOptionVO> getPCConstituencyList()
    {
  	  List<SelectOptionVO> selectOptionVOList = new ArrayList<SelectOptionVO>();
  	 try{
  		 List<Object[]> list = null;
  		
  		 list = constituencyDAO.getParliamentConstituencies();
  		if(list != null && list.size() > 0)
  		 for(Object[] params:list)
  		  selectOptionVOList.add(new SelectOptionVO((Long)params[0],params[1]!=null?params[1].toString():""));
  		
  	  return selectOptionVOList;
  	 }catch (Exception e) {
  	  e.printStackTrace();
  	  LOG.error(" Exception Occured in getPCConstituencyList() method, Exception - "+e);
  	  return selectOptionVOList;
  	}
    }
  	
	public ResultStatus populateVoterData()
	{
	 ResultStatus resultStatus = new ResultStatus();
		try{
			Map<Long,List<Long>> constituencyMap = new HashMap<Long, List<Long>>();
			List<VoterDataAvailableConstituencies> list = voterDataAvailableConstituenciesDAO.getPublicationDatesBasedOnConstituency();
			if(list != null && list.size() > 0)
			for(VoterDataAvailableConstituencies params : list)
			{
				List<Long> publicationIds = constituencyMap.get(params.getConstituency().getConstituencyId());
				if(publicationIds == null)
				{
					publicationIds = new ArrayList<Long>();
					
					constituencyMap.put(params.getConstituency().getConstituencyId(), publicationIds);
				}
				
				if(!publicationIds.contains(params.getPublicationDate().getPublicationDateId()))
					publicationIds.add(params.getPublicationDate().getPublicationDateId());
				
			}
			
			for(Long id : constituencyMap.keySet())
			{
				List<Long> publicationIds = constituencyMap.get(id);
				for(Long publicationId : publicationIds)
				{
			 votersAnalysisService.deleteVoterInfoFromIntermediateTablesByConstituencyId(id, publicationId);
			 votersAnalysisService.insertVotersDataInIntermediateTables(id, publicationId,1l,false,false,false);
				}
			}
			
		}
		catch (Exception e) {
			 e.printStackTrace();
		  	  LOG.error(" Exception Occured in getPCConstituencyList() method, Exception - "+e);
		  	  
		}
		return resultStatus;
	}
	 public List<SelectOptionVO> getDistrictsList(Long stateId)
	  {
		  List<SelectOptionVO> returnList = new ArrayList<SelectOptionVO>();
		  try {
			List<Object[]> districtsList = districtDAO.getDistrictIdAndNameByState(stateId);
			if(districtsList != null&& districtsList.size() > 0)
			{
				for(Object[] params : districtsList)
					returnList.add(new SelectOptionVO((Long)params[0],params[1].toString()));
			}
		} catch (Exception e) {
			LOG.error("Exception Occured in getDistrictsList(), Exception is -",e);
		}
		  return returnList;
	  }
	 public List<SelectOptionVO> getConstituencyList(List<Long> distictIds)
	  {
		  List<SelectOptionVO> returnList = new ArrayList<SelectOptionVO>();
		  try {
			List<Object[]> list = constituencyDAO.getConstituencies(distictIds);
			if(list != null&& list.size() > 0)
			{
				for(Object[] params : list)
					returnList.add(new SelectOptionVO((Long)params[0],params[1].toString()));
			}
		} catch (Exception e) {
			LOG.error("Exception Occured in getDistrictsList(), Exception is -",e);
		}
		  return returnList;
	  }
/*	 public MobileVO getIvrMobileNumbers(Long scopeId,List<Long> locationIDs,Long fileFormatVal,int maxIndex,boolean multipleFiles,int noOfFile)
	{
		
		MobileVO result =new MobileVO();
		List<MobileVO> resultList = new ArrayList<MobileVO>();
		String limit = "";
		if(maxIndex == 0)
			limit  = "ALL";
		try{
			boolean flag = false;
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			String date = sdf.format(new Date());
			String pathSeperator = System.getProperty(IConstants.FILE_SEPARATOR);
			String path = IConstants.STATIC_CONTENT_FOLDER_URL+pathSeperator+"mobile_numbers";
			int splitFileCnt = 0;
			StringBuilder str = null;
			File f1 = null;
				int i = 0;
				int maxCount =0 ;
				File destDir = new File(path + pathSeperator+date);
				destDir.mkdir();
			    List<String> totlaMobileNos = new ArrayList<String>();	
			    for(Long location :locationIDs)
					{
			    	 MobileVO vo = new MobileVO();
					 vo.setId(location);
					 if(scopeId == 1)
						vo.setName(districtDAO.get(location).getDistrictName());
					 else
						vo.setName(constituencyDAO.get(location).getName()); 
			    	if(!multipleFiles)
			    	{
			    		i++;
				    	
				        str = new StringBuilder();
						 if(fileFormatVal == 1)
						f1 = new File(path + pathSeperator+date+pathSeperator+i+".csv");//i is file number
						else
						f1 = new File(path + pathSeperator+date+pathSeperator+i+".txt");	
			    	}
				    int inc = 0;
			    	
					 Set<String> resultNumbers = new HashSet<String>();
						 get mobile no for each location  
						int tempMaxIndex = 0;
						 Set<String> mobilenos = mobileNumbersDAO.getVotersMobilenos(scopeId,location,maxIndex);
						 if((mobilenos != null && mobilenos.size() > 0))
						 resultNumbers.addAll(mobilenos);
										 
						 if(resultNumbers.size() < maxIndex)
						 tempMaxIndex = maxIndex -  resultNumbers.size();
											 
						 else if(resultNumbers.size() ==  maxIndex)
						  tempMaxIndex = 0; 
						 else
						 tempMaxIndex = maxIndex;
										 
						  IVR Panchayat Numbers 
							 if(tempMaxIndex > 0 || limit.equalsIgnoreCase("ALL"))
							 {
								Set<String> panchayatMNos = mobileNumbersDAO.getIvrMobilenosBasedOnPriority(scopeId,location,tempMaxIndex,IConstants.PANCHAYAT);
								if((panchayatMNos != null && panchayatMNos.size() > 0))
									 resultNumbers.addAll(panchayatMNos);
									     
									 if(resultNumbers.size() < maxIndex)
									tempMaxIndex = maxIndex -  resultNumbers.size(); 
									else if(resultNumbers.size() ==  maxIndex)
									 tempMaxIndex = 0; 
										      
									    
							}
									 
									 
									  IVR Tehsil Numbers 
							 if(tempMaxIndex > 0 || limit.equalsIgnoreCase("ALL"))
								 {
							Set<String> tehsilMNos = mobileNumbersDAO.getIvrMobilenosBasedOnPriority(scopeId,location,tempMaxIndex,IConstants.TEHSIL);
								if((tehsilMNos != null && tehsilMNos.size() > 0))
								resultNumbers.addAll(tehsilMNos);
									     
								if(resultNumbers.size() < maxIndex)
								tempMaxIndex = maxIndex -  resultNumbers.size(); 
							 else if(resultNumbers.size() ==  maxIndex)
							tempMaxIndex = 0; 
										      
									    
						    }
									 
							 IVR Constituency Numbers 
						 if(tempMaxIndex > 0 || limit.equalsIgnoreCase("ALL"))
						 {
						 Set<String> constituencyMNos = mobileNumbersDAO.getIvrMobilenosBasedOnPriority(scopeId,location,tempMaxIndex,IConstants.CONSTITUENCY);
						 if((constituencyMNos != null && constituencyMNos.size() > 0))
							resultNumbers.addAll(constituencyMNos);
									     
							 if(resultNumbers.size() < maxIndex)
							tempMaxIndex = maxIndex -  resultNumbers.size(); 
							else if(resultNumbers.size() ==  maxIndex)
							tempMaxIndex = 0; 
										      
						 }
									 
						 if(resultNumbers != null && resultNumbers.size() > 0)
					      {
										  
							 flag = true;
							 vo.setCount(new Long(resultNumbers.size()));
							for(String l : resultNumbers)
							{
							 if(!totlaMobileNos.contains(l.toString()))
							 totlaMobileNos.add(l.toString());
							 if(!multipleFiles)
								 {
								str.append(l.toString());
								str.append( "\r\n");
								 }
							}
						}
						
						resultList.add(vo);
						 if(!multipleFiles)
						 {
						 BufferedWriter outPut1 = new BufferedWriter(new FileWriter(f1));
						    outPut1.write(str.toString());
							outPut1.close();
						 }
				}
			      multiple file split 
			if(multipleFiles && totlaMobileNos.size() > 0)
			 {
				splitFileCnt =  totlaMobileNos.size() / noOfFile;
				for(int j=0;j<noOfFile;j++)
				{
					str = new StringBuilder();
					if(fileFormatVal == 1)
					f1 = new File(path + pathSeperator+date+pathSeperator+j+".csv");//i is file number
					else
					f1 = new File(path + pathSeperator+date+pathSeperator+j+".txt");
					int inc = 0;
					for(String no : totlaMobileNos.subList(maxCount,  totlaMobileNos.size()- 0))
					{
						inc ++;
						str.append(no.toString());
						str.append( "\r\n");
						if(inc >= splitFileCnt && j < noOfFile - 1)
						 break;
							
					}
					 BufferedWriter outPut1 = new BufferedWriter(new FileWriter(f1));
					    outPut1.write(str.toString());
						outPut1.close();
					maxCount = maxCount + splitFileCnt;
				}
			 }
			
			try{
				if(flag == true)
				{
					
					 FileOutputStream fos = new FileOutputStream(path + pathSeperator+date+".zip");
					 ZipOutputStream zos = new ZipOutputStream(fos);
				     System.gc();
					 for(File rf : destDir.listFiles())
					 addToZipFile(rf.getAbsolutePath(), zos);
					 zos.close();
					 fos.close();
					result.setResultCode(0);
					result.setStatus("/mobile_numbers/"+date+".zip");
					result.setList(resultList);
			}
				else
				{
					
					result.setStatus("no data");
					result.setResultCode(1);
				}
			 }catch(Exception e)
			 {
				 LOG.error("Exception Occured in Zipping Files");
				 result.setResultCode(2);
				 result.setStatus("Exception");
			 }
		}
		catch (Exception e) {
			 e.printStackTrace();
		  	    LOG.error(" Exception Occured in getIvrMobileNumbers() method, Exception - "+e);
		  	  	result.setResultCode(2);
				result.setStatus("Exception");
		}
		return result;
	}*/
	 
	 public MobileVO getMobileNumbersByLocations(Long scopeId,List<Long> locationIds,Long fileFormatVal,int maxIndex,int checkedTypeVal,int noOfFile,List<String>checkedLevels)
		{
		    MobileVO result =new MobileVO();
		    List<MobileVO> resultList = new ArrayList<MobileVO>();
		try{
			if(scopeId == 3) // Parliament
			{
				for(Long locationId :  locationIds)
				{
					List<Long> acIds = delimitationConstituencyAssemblyDetailsDAO.findAssembliesConstituenciesByParliament(locationId);
					scopeId = 2l;	
					locationIds = new ArrayList<Long>();
						if(acIds != null && acIds.size() > 0)
						{
						locationIds.addAll(acIds);
						getMobileNumbersForParliament(resultList,locationIds,fileFormatVal,maxIndex,checkedTypeVal,noOfFile,locationId,checkedLevels);
						}
				}
				result = fileSplitForParlaiment(resultList,checkedTypeVal,noOfFile,fileFormatVal,maxIndex);
				result.setList(resultList);
			}
				else
				{
				getMobileNumbers(result,scopeId,locationIds,fileFormatVal,maxIndex,checkedTypeVal,noOfFile,checkedLevels);
				}
		}
		catch (Exception e) {
			e.printStackTrace();
		}
			
			return result;
		}
	 public List<MobileVO> getMobileNumbersForParliament(List<MobileVO> resultList,List<Long> locationIds,Long fileFormatVal,int maxIndex,int checkedTypeVal,int noOfFile,Long pcId,List<String> checkedLevels)
	 {
		 try{
			 	MobileVO vo = new MobileVO();
				vo.setId(pcId);
				vo.setName(constituencyDAO.get(pcId).getName()); 
				
				    	 Set<String> resultNumbers = new HashSet<String>();
				    	 Long Total  = 0l;
				    	 Long constiTotal = 0l;
				    	 Long mandalTotal = 0l;
				    	 Long panchayatTotal = 0l;
				    	 String firstEle  = checkedLevels.get(0); 
				    	// Total = mobileNumbersDAO.getMobileNosTotalForLocationIDs(locationIds,IConstants.CONSTITUENCY,2l);	
				    	 if(checkedLevels.contains(IConstants.CONSTITUENCY))
				    	 constiTotal =  mobileNumbersDAO.getMobileNosCountByIdsForLocationIDs(locationIds,IConstants.CONSTITUENCY,2l);
				    	 else if(checkedLevels.contains(IConstants.TEHSIL))
				    	 mandalTotal = mobileNumbersDAO.getMobileNosCountByIdsForLocationIDs(locationIds,IConstants.TEHSIL,2l);
				    	 else if(checkedLevels.contains(IConstants.PANCHAYAT))
					      panchayatTotal = mobileNumbersDAO.getMobileNosCountByIdsForLocationIDs(locationIds,IConstants.PANCHAYAT,2l);
				    	 Total = constiTotal + mandalTotal + panchayatTotal;
					    if(Total > 0)
				    	{
					    	if(Total < maxIndex)
					    		maxIndex = Total.intValue();
					    	
					    	int constiMaxIndex = (int) ((constiTotal * maxIndex)/Total);
					    	int mandalMaxIndex =  (int) ((mandalTotal * maxIndex)/Total);
					    	int panchayatMaxIndex = (int) ((panchayatTotal * maxIndex)/Total.longValue());
					    	int sum = constiMaxIndex + mandalMaxIndex + panchayatMaxIndex;
					    	
					    	if(sum > maxIndex)
					    	{
					    		if(firstEle.equalsIgnoreCase(IConstants.CONSTITUENCY))
					    		constiMaxIndex = constiMaxIndex - (sum - maxIndex);
					    		else if(firstEle.equalsIgnoreCase(IConstants.TEHSIL))
					    			mandalMaxIndex = mandalMaxIndex - (sum - maxIndex);
					    		else if(firstEle.equalsIgnoreCase(IConstants.PANCHAYAT))
					    			panchayatMaxIndex = panchayatMaxIndex - (sum - maxIndex);
					    			
					    	}
					    	if(sum < maxIndex)
					    	{
					    		if(firstEle.equalsIgnoreCase(IConstants.CONSTITUENCY))
					    		constiMaxIndex = constiMaxIndex + (maxIndex - sum);	
					    		else if(firstEle.equalsIgnoreCase(IConstants.TEHSIL))
					    			mandalMaxIndex = mandalMaxIndex + (maxIndex - sum);	
					    		else if(firstEle.equalsIgnoreCase(IConstants.PANCHAYAT))
					    			panchayatMaxIndex = panchayatMaxIndex + (maxIndex - sum);	
					    	}
					    	 if(checkedLevels.contains(IConstants.PANCHAYAT))
					    	 {
						    	Set<String> panchayatNos = mobileNumbersDAO.getMobilenosBasedOnPriorityForLocationIDs(2l,locationIds,panchayatMaxIndex,IConstants.PANCHAYAT);
							        if(panchayatNos != null && panchayatNos.size() > 0)
							        	resultNumbers.addAll(panchayatNos);
					    	 }
					    	 if(checkedLevels.contains(IConstants.TEHSIL))
					    	 {
							        Set<String> mandalNos = mobileNumbersDAO.getMobilenosBasedOnPriorityForLocationIDs(2l,locationIds,mandalMaxIndex,IConstants.TEHSIL);
							        if(mandalNos != null && mandalNos.size() > 0)
							        	resultNumbers.addAll(mandalNos);
					    	 }
					    	 if(checkedLevels.contains(IConstants.CONSTITUENCY))
					    	 {
						        Set<String> constituencyNos = mobileNumbersDAO.getMobilenosBasedOnPriorityForLocationIDs(2l,locationIds,constiMaxIndex,IConstants.CONSTITUENCY);
						        if(constituencyNos != null && constituencyNos.size() > 0)
						        	resultNumbers.addAll(constituencyNos);
				    	     }
					     
				    	}
					    if(resultNumbers != null && resultNumbers.size() > 0)
					      {
				    		  List<String> totlaMobileNos = new ArrayList<String>(resultNumbers);
				    		  vo.setCount(new Long(totlaMobileNos.size()));
				    		  vo.setTotalMobileNos(totlaMobileNos);
					      }
					    if(resultNumbers != null && resultNumbers.size() > 0)
					    {
					    	List<String> nos = new ArrayList<String>(resultNumbers);
					    	int size = resultNumbers.size();
					    	  int fromIndex = 0;
							  int toIndex = 2000;
								if(size >=2000)
								{
									 while(fromIndex <= toIndex)
									  {
										  mobileNumbersDAO.updateUsedMobileNos(nos.subList(fromIndex, toIndex));
										  fromIndex += 2000;
										  toIndex += 2000;
										  if(toIndex >= size)
											toIndex = size;
									  }
								}
								else
									mobileNumbersDAO.updateUsedMobileNos(nos);
									
					    }// update is_used status 'Y'
						resultList.add(vo);
		 				}
				catch (Exception e) {
					 e.printStackTrace();
				  	    LOG.error(" Exception Occured in getIvrMobileNumbers() method, Exception - "+e);
					}
		
				return resultList; 
		 }
	 

public MobileVO fileSplitForParlaiment(List<MobileVO> resultList,int checkedTypeVal,int noOfFile,Long fileFormatVal,int maxIndex)
{
	MobileVO result = new MobileVO();
	try{
		 Random rand = new Random();
			int randNO = rand.nextInt(4);
	int splitFileCnt = 0;
	boolean flag = false;
	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
	String date = sdf.format(new Date());
	String pathSeperator = System.getProperty(IConstants.FILE_SEPARATOR);
	String path = IConstants.STATIC_CONTENT_FOLDER_URL+pathSeperator+"mobile_numbers";
	StringBuilder str = null;
	File f1 = null;
	int splitmaxCount =0 ;
	File destDir = new File(path + pathSeperator+date+randNO);
	destDir.mkdir();
	List<String> totalNos = new ArrayList<String>();
	if(resultList != null && resultList.size() > 0)
	for(MobileVO vo : resultList)
	{
		str = new StringBuilder();
		if(checkedTypeVal == 2) //each file for location
    	{
			str = new StringBuilder();
			 if(fileFormatVal == 1)
			f1 = new File(path + pathSeperator+date+randNO+pathSeperator+vo.getName()+".csv");
			else
			f1 = new File(path + pathSeperator+date+randNO+pathSeperator+vo.getName()+".txt");	
    	}
		if(vo.getTotalMobileNos() != null && vo.getTotalMobileNos().size() > 0)
		{
			flag = true;
			for(String l : vo.getTotalMobileNos())
			{
				if(!totalNos.contains(l.toString()))
					totalNos.add(l.toString());
				 if(checkedTypeVal == 2) //each file for location
				 {
				str.append(l.toString());
				str.append( "\r\n");
				 }
			}
		}
		if(checkedTypeVal == 2) //each file for location
		 {
		    BufferedWriter outPut1 = new BufferedWriter(new FileWriter(f1));
		    outPut1.write(str.toString());
			outPut1.close();
		 }
		
	}
	    /*  multiple file split based on totalMobileNumbers in all locations*/ 
		if(checkedTypeVal == 3 && totalNos.size() > 0)
		 {
			splitFileCnt =  totalNos.size() / noOfFile;
			for(int j=0;j<noOfFile;j++)
			{
				str = new StringBuilder();
				if(fileFormatVal == 1)
				f1 = new File(path + pathSeperator+date+randNO+pathSeperator+j+".csv");//j is file number
				else
				f1 = new File(path + pathSeperator+date+randNO+pathSeperator+j+".txt");
				int inc = 0;
				for(String no : totalNos.subList(splitmaxCount,  totalNos.size()- 0))
				{
					inc ++;
					str.append(no.toString());
					str.append( "\r\n");
					if(inc >= splitFileCnt && j < noOfFile - 1)
					 break;
						
				}
				 BufferedWriter outPut1 = new BufferedWriter(new FileWriter(f1));
				    outPut1.write(str.toString());
					outPut1.close();
					splitmaxCount = splitmaxCount + splitFileCnt;
			}
		 }
		
		else if(checkedTypeVal == 1 && totalNos.size() > 0)//Single file
		{
			str = new StringBuilder();
			Random rand1 = new Random();
			int x = rand1.nextInt(4);
			if(fileFormatVal == 1)
			f1 = new File(path + pathSeperator+date+randNO+pathSeperator+x+".csv");
			else
			f1 = new File(path + pathSeperator+date+randNO+pathSeperator+x+".txt");	
			for(String no : totalNos)
			{
				str.append(no.toString());
				str.append( "\r\n");
			}
			 BufferedWriter outPut1 = new BufferedWriter(new FileWriter(f1));
			    outPut1.write(str.toString());
				outPut1.close();
				if(fileFormatVal == 1)
				result.setOptionFilePath(path + pathSeperator+date+randNO+pathSeperator+x+".csv");
				else
				result.setOptionFilePath(path + pathSeperator+date+randNO+pathSeperator+x+".txt");	
		}
		try{
			if(flag == true)
			{
				
				 FileOutputStream fos = new FileOutputStream(path + pathSeperator+date+randNO+".zip");
				 ZipOutputStream zos = new ZipOutputStream(fos);
			     System.gc();
				 for(File rf : destDir.listFiles())
				 addToZipFile(rf.getAbsolutePath(), zos);
				 zos.close();
				 fos.close();
				result.setResultCode(0);
				result.setStatus("/mobile_numbers/"+date+randNO+".zip");
				result.setList(resultList);
		}
			else
			{
				
				result.setStatus("no data");
				result.setResultCode(1);
			}
		 }catch(Exception e)
		 {
			 LOG.error("Exception Occured in Zipping Files");
			 result.setResultCode(2);
			 result.setStatus("Exception");
		 }
	}
	catch (Exception e) {
		e.printStackTrace();
	}
	return result;
}
	 public MobileVO getMobileNumbers(MobileVO result,Long scopeId,List<Long> locationIds,Long fileFormatVal,int maxIndex,int checkedTypeVal,int noOfFile,List<String> checkedLevels)
	 {
		 List<MobileVO> resultList = new ArrayList<MobileVO>();
			String limit = "";
			if(maxIndex == 0)
				limit  = "ALL";
		 try{
				boolean flag = false;
				Random rand = new Random();
				int randNO = rand.nextInt(4);
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
				String date = sdf.format(new Date());
				String pathSeperator = System.getProperty(IConstants.FILE_SEPARATOR);
				String path = IConstants.STATIC_CONTENT_FOLDER_URL+pathSeperator+"mobile_numbers";
				int splitFileCnt = 0;
				StringBuilder str = null;
				File f1 = null;
				int splitmaxCount =0 ;
				File destDir = new File(path + pathSeperator+date+randNO);
				destDir.mkdir();
				List<String> totlaMobileNos = new ArrayList<String>();
				   int i=0; 
				   if(locationIds != null && locationIds.size() > 0)
				    for(Long location : locationIds)
				    {
				    	 MobileVO vo = new MobileVO();
				    	 vo.setId(location);
						 if(scopeId == 1)
							vo.setName(districtDAO.get(location).getDistrictName());
						 if(scopeId == 2)
							vo.setName(constituencyDAO.get(location).getName()); 
						 if(scopeId == 4)
								vo.setName(tehsilDAO.get(location).getTehsilName()); 
				    	if(checkedTypeVal == 2) //each file for location
				    	{
				    		i++;
					    	str = new StringBuilder();
							 if(fileFormatVal == 1)
							f1 = new File(path + pathSeperator+date+randNO+pathSeperator+vo.getName()+".csv");//i is file number
							else
							f1 = new File(path + pathSeperator+date+randNO+pathSeperator+vo.getName()+".txt");	
				    	}
				    	 Set<String> resultNumbers = new HashSet<String>();
				    	 Long Total  = 0l;
				    	 Long districtTotal = 0l;
				    	 Long constiTotal = 0l;
				    	 Long mandalTotal = 0l;
				    	 Long panchayatTotal = 0l;
				    	 String firstElement = checkedLevels.get(0);
					     if(scopeId == 1) // District
					     {
					    	 if(checkedLevels.contains(IConstants.DISTRICT)) 
					    	districtTotal =  mobileNumbersDAO.getMobileNosCountByIds(location,IConstants.DISTRICT,scopeId);	
					    	else if(checkedLevels.contains(IConstants.CONSTITUENCY))
					    	constiTotal =  mobileNumbersDAO.getMobileNosCountByIds(location,IConstants.CONSTITUENCY,scopeId);
					    	else if(checkedLevels.contains(IConstants.TEHSIL))
					    		mandalTotal = mobileNumbersDAO.getMobileNosCountByIds(location,IConstants.TEHSIL,scopeId);	
					    	else if(checkedLevels.contains(IConstants.PANCHAYAT))
					    		panchayatTotal = mobileNumbersDAO.getMobileNosCountByIds(location,IConstants.PANCHAYAT,scopeId);;	
					    		Total = districtTotal + constiTotal + mandalTotal + panchayatTotal;
					     }
				    	else if(scopeId == 2) //Constituency
				    	{
				    			if(checkedLevels.contains(IConstants.CONSTITUENCY))
						    	constiTotal =  mobileNumbersDAO.getMobileNosCountByIds(location,IConstants.CONSTITUENCY,scopeId);
						    	else if(checkedLevels.contains(IConstants.TEHSIL))
						    		mandalTotal = mobileNumbersDAO.getMobileNosCountByIds(location,IConstants.TEHSIL,scopeId);	
						    	else if(checkedLevels.contains(IConstants.PANCHAYAT))
						    		panchayatTotal = mobileNumbersDAO.getMobileNosCountByIds(location,IConstants.PANCHAYAT,scopeId);;	
						    		Total = constiTotal + mandalTotal + panchayatTotal;
				    	}
				    	else if(scopeId == 4) // Mandal
				    	{
				    		if(checkedLevels.contains(IConstants.TEHSIL))
					    		mandalTotal = mobileNumbersDAO.getMobileNosCountByIds(location,IConstants.TEHSIL,scopeId);	
					    	else if(checkedLevels.contains(IConstants.PANCHAYAT))
					    		panchayatTotal = mobileNumbersDAO.getMobileNosCountByIds(location,IConstants.PANCHAYAT,scopeId);;	
					    		Total = mandalTotal + panchayatTotal;
					    }
					    if(Total > 0)
				    	{
				    		
				    		if(Total < maxIndex)
				    		maxIndex = Total.intValue();
				    		int districtMaxIndex = (int) ((districtTotal * maxIndex)/Total);
				    		int constiMaxIndex = (int) ((constiTotal * maxIndex)/Total);
					    	int mandalMaxIndex =  (int) ((mandalTotal * maxIndex)/Total);
					    	int panchayatMaxIndex = (int) ((panchayatTotal * maxIndex)/Total.longValue());
					    	int sum = districtMaxIndex + constiMaxIndex + mandalMaxIndex + panchayatMaxIndex;
					    	
					    	if(sum > maxIndex)
					    	{
					    		if(firstElement.equalsIgnoreCase(IConstants.DISTRICT))
					    			districtMaxIndex = districtMaxIndex - (sum - maxIndex);
						    		else if(firstElement.equalsIgnoreCase(IConstants.CONSTITUENCY))
						    		constiMaxIndex = constiMaxIndex - (sum - maxIndex);
						    		else if(firstElement.equalsIgnoreCase(IConstants.TEHSIL))
						    			mandalMaxIndex = mandalMaxIndex - (sum - maxIndex);
						    		else if(firstElement.equalsIgnoreCase(IConstants.PANCHAYAT))
						    			panchayatMaxIndex = panchayatMaxIndex - (sum - maxIndex);
					    		
					    		
					    	}
					    	if(sum < maxIndex)
					    	{
					    		if(firstElement.equalsIgnoreCase(IConstants.DISTRICT))
					    			districtMaxIndex = districtMaxIndex + (maxIndex - sum);
						    	else if(firstElement.equalsIgnoreCase(IConstants.CONSTITUENCY))
						    		constiMaxIndex = constiMaxIndex + (maxIndex - sum);	
						    	else if(firstElement.equalsIgnoreCase(IConstants.TEHSIL))
					    			mandalMaxIndex = mandalMaxIndex + (maxIndex - sum);
					    		else if(firstElement.equalsIgnoreCase(IConstants.PANCHAYAT))
					    			panchayatMaxIndex = panchayatMaxIndex + (maxIndex - sum);
					    	}
					    	
					    	
					    	 
					    	if(checkedLevels.contains(IConstants.PANCHAYAT))
					    	{
						        Set<String> panchayatNos = mobileNumbersDAO.getMobilenosBasedOnPriority(scopeId,location,panchayatMaxIndex,IConstants.PANCHAYAT);
							        if(panchayatNos != null && panchayatNos.size() > 0)
							        	resultNumbers.addAll(panchayatNos);
					    	}
					    	if(checkedLevels.contains(IConstants.TEHSIL))
					    	{
						        Set<String> mandalNos = mobileNumbersDAO.getMobilenosBasedOnPriority(scopeId,location,mandalMaxIndex,IConstants.TEHSIL);
						        if(mandalNos != null && mandalNos.size() > 0)
						        	resultNumbers.addAll(mandalNos);
					    	}
					        if(scopeId == 1 || scopeId == 2)//District ,constituency
					        {
					        	if(checkedLevels.contains(IConstants.CONSTITUENCY))	
					        	{
						        Set<String> constituencyNos = mobileNumbersDAO.getMobilenosBasedOnPriority(scopeId,location,constiMaxIndex,IConstants.CONSTITUENCY);
						        if(constituencyNos != null && constituencyNos.size() > 0)
						        	resultNumbers.addAll(constituencyNos);
					        	}
					        }
					        
					        if(scopeId == 1)//district
						     {
						        	if(checkedLevels.contains(IConstants.DISTRICT))
						        	{
						        	Set<String> districtNos = mobileNumbersDAO.getMobilenosBasedOnPriority(scopeId,location,districtMaxIndex,IConstants.DISTRICT);
							        if(districtNos != null && districtNos.size() > 0)
							        	resultNumbers.addAll(districtNos);
						        	}
						     }
				    	
				    	}
					     if(resultNumbers != null && resultNumbers.size() > 0)
					      {
										  
							 flag = true;
							 vo.setCount(new Long(resultNumbers.size()));
							for(String l : resultNumbers)
							{
							 if(!totlaMobileNos.contains(l.toString()))
							 totlaMobileNos.add(l.toString());
							 if(checkedTypeVal == 2) //each file for location
								 {
								str.append(l.toString());
								str.append( "\r\n");
								 }
							}
							List<String> nos = new ArrayList<String>(resultNumbers);
					    	int size = resultNumbers.size();
					    	  int fromIndex = 0;
							  int toIndex = 2000;
							if(size >=2000)
							{
								 while(fromIndex <= toIndex)
								  {
									  mobileNumbersDAO.updateUsedMobileNos(nos.subList(fromIndex, toIndex));
									  fromIndex += 2000;
									  toIndex += 2000;
									  if(toIndex >= size)
										toIndex = size;
								  }
							}
							else
								mobileNumbersDAO.updateUsedMobileNos(nos);
							if(checkedTypeVal == 2) //each file for location
							 {
							    BufferedWriter outPut1 = new BufferedWriter(new FileWriter(f1));
							    outPut1.write(str.toString());
								outPut1.close();
							 }
						}
						
						resultList.add(vo);
						
						  // update is_used status 'Y'
				   }
				  
				    /*  multiple file split based on totalMobileNumbers in all locations*/ 
					if(checkedTypeVal == 3 && totlaMobileNos.size() > 0)
					 {
						splitFileCnt =  totlaMobileNos.size() / noOfFile;
						for(int j=0;j<noOfFile;j++)
						{
							str = new StringBuilder();
							if(fileFormatVal == 1)
							f1 = new File(path + pathSeperator+date+randNO+pathSeperator+j+".csv");//j is file number
							else
							f1 = new File(path + pathSeperator+date+randNO+pathSeperator+j+".txt");
							int inc = 0;
							for(String no : totlaMobileNos.subList(splitmaxCount,  totlaMobileNos.size()- 0))
							{
								inc ++;
								str.append(no.toString());
								str.append( "\r\n");
								if(inc >= splitFileCnt && j < noOfFile - 1)
								 break;
									
							}
							 BufferedWriter outPut1 = new BufferedWriter(new FileWriter(f1));
							    outPut1.write(str.toString());
								outPut1.close();
								splitmaxCount = splitmaxCount + splitFileCnt;
						}
					 }
					
					else if(checkedTypeVal == 1 && totlaMobileNos.size() > 0)//Single file
					{
						str = new StringBuilder();
						Random rand1 = new Random();
						int x = rand1.nextInt(4);
						if(fileFormatVal == 1)
						f1 = new File(path + pathSeperator+date+randNO+pathSeperator+x+".csv");
						else
						f1 = new File(path + pathSeperator+date+randNO+pathSeperator+x+".txt");	
						for(String no : totlaMobileNos)
						{
							str.append(no.toString());
							str.append( "\r\n");
						}
						 BufferedWriter outPut1 = new BufferedWriter(new FileWriter(f1));
						    outPut1.write(str.toString());
							outPut1.close();
							if(fileFormatVal == 1)
							result.setOptionFilePath(path + pathSeperator+date+randNO+pathSeperator+x+".csv");
							else
							result.setOptionFilePath(path + pathSeperator+date+randNO+pathSeperator+x+".txt");	
					}
					
					try{
						if(flag == true)
						{
							
							 FileOutputStream fos = new FileOutputStream(path + pathSeperator+date+randNO+".zip");
							 ZipOutputStream zos = new ZipOutputStream(fos);
						     System.gc();
							 for(File rf : destDir.listFiles())
							 addToZipFile(rf.getAbsolutePath(), zos);
							 zos.close();
							 fos.close();
							result.setResultCode(0);
							result.setStatus("/mobile_numbers/"+date+randNO+".zip");
							result.setList(resultList);
					}
						else
						{
							
							result.setStatus("no data");
							result.setResultCode(1);
						}
					 }catch(Exception e)
					 {
						 LOG.error("Exception Occured in Zipping Files");
						 result.setResultCode(2);
						 result.setStatus("Exception");
					 }
				}
				catch (Exception e) {
					 e.printStackTrace();
				  	    LOG.error(" Exception Occured in getIvrMobileNumbers() method, Exception - "+e);
				  	  	result.setResultCode(2);
						result.setStatus("Exception");
				}
				return result; 
		 }
		
	 
	 public List<SelectOptionVO> getTehsilList(List<Long> distictIds)
	  {
		  List<SelectOptionVO> returnList = new ArrayList<SelectOptionVO>();
		  try {
			List<Object[]> list = tehsilDAO.getMandalsByDistricts(distictIds);
			if(list != null&& list.size() > 0)
			{
				for(Object[] params : list)
					returnList.add(new SelectOptionVO((Long)params[0],params[1].toString()));
			}
		} catch (Exception e) {
			LOG.error("Exception Occured in getTehsilList(), Exception is -",e);
		}
		  return returnList;
	  }
	 public List<SelectOptionVO> getpcconstituencyList(Long regionId)
	  {
		  List<SelectOptionVO> returnList = new ArrayList<SelectOptionVO>();
		  try {
			List<Object[]> list = delimitationConstituencyAssemblyDetailsDAO.getPcListByRegion(regionId);
			if(list != null&& list.size() > 0)
			{
				for(Object[] params : list)
					returnList.add(new SelectOptionVO(new BigInteger(params[0].toString()).longValue(),params[1].toString()));
			}
		} catch (Exception e) {
			LOG.error("Exception Occured in getpcconstituencyList(), Exception is -",e);
		}
		  return returnList;
	  }
	 
	 public MobileVO getLocationWiseMobileNumbersCountByRegionAndScope(Long scopeId,String regionType)
	 {
		 
		 MobileVO result = new MobileVO();
		 List<MobileVO> resultList = new ArrayList<MobileVO>();
		 List<Long> locationIds = new ArrayList<Long>();
		 List<Object[]> list = null;
		 try{
			if(scopeId == 2)
			{
				list = districtDAO.getDistrictIdAndNameByStateForRegion(1l,regionType); // District
			}
			else if(scopeId == 3)
			{
				list = constituencyDAO.getConstituenciesForRegion(regionType);	//Constituency
			}
			else if(scopeId == 4)
			{
				
				if(regionType.equalsIgnoreCase("Andhra Pradesh"))
				 list = delimitationConstituencyAssemblyDetailsDAO.getPcListByRegion(1l); //Parliament
				 else
				list = delimitationConstituencyAssemblyDetailsDAO.getPcListByRegion(2l);
				
			}
			else if(scopeId == 5)
			{
				list = tehsilDAO.getMandalsForRegion(regionType);	//Tehsil
			}
			if(list != null && list.size() > 0)
			{
				for(Object[] params : list)
				{
					Long id = 0l;
					if(scopeId == 4)
					id = new BigInteger(params[0].toString()).longValue();
					else
					id = (Long)params[0];
					
					if(!locationIds.contains(id))
					{
						MobileVO vo = new MobileVO();
						vo.setId(id);
						vo.setName(params[1] != null ? params[1].toString() : "");
						resultList.add(vo);
						locationIds.add(id);
					}
				
				}
			}
			if(scopeId == 4)
			{
				scopeId = 3l;
				setCountsForParliamnet(resultList,scopeId);
			}
			else
				setCounts(resultList,locationIds,scopeId);	
			
		 }
		 catch (Exception e) {
				LOG.error("Exception Occured in getLocationWiseMobileNumbersCountByRegionAndScope(), Exception is - ",e);
		}
		 result.setList(resultList);
		return result;
	 }
	 public List<MobileVO> setCounts(List<MobileVO> resultList,List<Long>locationIds,Long scopeId)
	 {
		 try{
			 if(scopeId == 2)
			 {
			 List<Object[]> list1 = mobileNumbersDAO.getMobileNosCountForLocation(locationIds,IConstants.DISTRICT,scopeId);	
				if(list1 != null && list1.size() > 0)
				{
					for(Object[] params : list1)
					{
					MobileVO vo = getMatchedVo(resultList,(Long)params[1]);
					vo.setDistictWiseCount((Long)params[0]);
					}
					
				}
			 }
			 if(scopeId == 2 || scopeId == 3)
			 {
				List<Object[]> list2 = mobileNumbersDAO.getMobileNosCountForLocation(locationIds,IConstants.CONSTITUENCY,scopeId);
				if(list2 != null && list2.size() > 0)
				{
					for(Object[] params : list2)
					{
					MobileVO vo = getMatchedVo(resultList,(Long)params[1]);
					vo.setConstituencyWiseCount((Long)params[0]);
					}
					
				}
			 }
				List<Object[]> list3 = mobileNumbersDAO.getMobileNosCountForLocation(locationIds,IConstants.PANCHAYAT,scopeId);
				if(list3 != null && list3.size() > 0)
				{
					for(Object[] params : list3)
					{
					MobileVO vo = getMatchedVo(resultList,(Long)params[1]);
					vo.setPanchayatWiseCount((Long)params[0]);
					}
					
				}
				List<Object[]> list4 = mobileNumbersDAO.getMobileNosCountForLocation(locationIds,IConstants.TEHSIL,scopeId);
				if(list4 != null && list4.size() > 0)
				{
					for(Object[] params : list4)
					{
					MobileVO vo = getMatchedVo(resultList,(Long)params[1]);
					vo.setTehsilWiseCount((Long)params[0]);
					}
					
				}
		 }
		 catch (Exception e) {
			 LOG.error("Exception Occured in setCounts(), Exception is - ",e);
		}
		return resultList;
	 }
	 
	 public List<MobileVO> setCountsForParliamnet(List<MobileVO> resultList,Long scopeId)
	 {
		 try{
			 for(MobileVO pcVo : resultList)
			 {
			 List<Long> locationIds = new ArrayList<Long>();
			
			 locationIds = delimitationConstituencyAssemblyDetailsDAO.findAssembliesConstituenciesByParliament(pcVo.getId()) ;
			 List<Object[]> list2 = mobileNumbersDAO.getMobileNosCountForLocation(locationIds,IConstants.CONSTITUENCY,scopeId);
				if(list2 != null && list2.size() > 0)
				{
					
					for(Object[] params : list2)
					{
						
						pcVo.setConstituencyWiseCount(pcVo.getConstituencyWiseCount() + (Long)params[0]);
					}
					
				}
				List<Object[]> list3 = mobileNumbersDAO.getMobileNosCountForLocation(locationIds,IConstants.PANCHAYAT,scopeId);
				if(list3 != null && list3.size() > 0)
				{
					
					for(Object[] params : list3)
					{
						pcVo.setPanchayatWiseCount(pcVo.getPanchayatWiseCount() + (Long)params[0]);
					}
					
				}
				List<Object[]> list4 = mobileNumbersDAO.getMobileNosCountForLocation(locationIds,IConstants.TEHSIL,scopeId);
				if(list4 != null && list4.size() > 0)
				{
					
					for(Object[] params : list4)
					{
						pcVo.setTehsilWiseCount(pcVo.getTehsilWiseCount() + (Long)params[0]);
					}
					
				}
			 }
		 }
		 catch (Exception e) {
			 LOG.error("Exception Occured in setCountsForParliamnet(), Exception is - ",e);
		}
		return resultList;
	 }
		 public MobileVO getMatchedVo(List<MobileVO> resultList,Long Id)
		 {
			try{
				if(resultList != null && resultList.size() > 0)
					for(MobileVO vo : resultList)
					{
						if(vo.getId().longValue() == Id.longValue())
							return vo;
					}
				}
			catch (Exception e) {
				LOG.error("Exception Occured in getMatchedVo(), Exception is - ",e);
			}
			return null;
		
		 }
		 
		 public void updateUsedMobileNos(List<String> mobileNos)
		 {
			 
			 try{
				 if(mobileNos != null && mobileNos.size() > 0)
				 mobileNumbersDAO.updateUsedMobileNos(mobileNos);
				
			 }
			 catch (Exception e) {
				 LOG.error("Exception Occured in updateUsedMobileNos(), Exception is - ",e);
				
			}
			
		 }
		 public ResultStatus resetAllMobileNos()
		 {
			 ResultStatus rs = new ResultStatus();
			 try{
				 
				 int count = mobileNumbersDAO.updateMobileNos();
				 if(count > 0)
				 rs.setResultCode(ResultCodeMapper.SUCCESS);
			 }
			 catch (Exception e) {
				 LOG.error("Exception Occured in resetAllMobileNos(), Exception is - ",e);
				 rs.setResultCode(ResultCodeMapper.FAILURE);
			}
			return rs;
		 }
		 
		 
		 public ResultStatus createCadreDataSqliteFileForAParliamnetConstituency(RegistrationVO reVo)
		 {
			 LOG.info("Entered into createCadreDataDumpFileForAParliamnetConstituency() Method ");
			 ResultStatus resultStatus = new ResultStatus();
			try{
				Class.forName("org.sqlite.JDBC");
				Connection connection = null;
				Statement statement = null;
				
				Long pconstituencyId = reVo.getConstituencyId();
				Long publicationId = reVo.getPublicationDateId();
				String constituencyName = constituencyDAO.get(pconstituencyId).getName();
				String path = reVo.getPath();
				
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
				String date = sdf.format(new Date());
				String pathSeperator = System.getProperty(IConstants.FILE_SEPARATOR);
				
				File destDir = new File(path+pathSeperator+constituencyName+"_"+date+"_Cadre");
				destDir.mkdir();
				File baseFile = new File(path+pathSeperator+"Base"+pathSeperator+"base.sqlite");
				
				FileOutputStream fos = new FileOutputStream(path+pathSeperator+constituencyName+"_"+date+"_Cadre.zip");
				ZipOutputStream zos = new ZipOutputStream(fos);
				
				List<Constituency> acList = delimitationConstituencyAssemblyDetailsDAO.findAssemblyConstituencies(pconstituencyId,2009l);
				
				for(Constituency ac : acList)
				{
					try{
					try{
					File acFile = new File(path+pathSeperator+constituencyName+"_"+date+"_Cadre"+pathSeperator+ac.getName()+".sqlite");
					FileUtils.copyFile(baseFile, acFile);
					}catch(Exception e)
					{
						LOG.error("Exception is -",e);
					}
					
					List<Object[]> votersList = boothPublicationVoterDAO.getVoterDetailsOfAConstituencyAndPublication(ac.getConstituencyId(),publicationId);
					
					if(votersList != null && votersList.size() > 0)
					{
						connection = DriverManager.getConnection("jdbc:sqlite:"+path+pathSeperator+constituencyName+"_"+date+"_Cadre"+pathSeperator+ac.getName()+".sqlite");
						connection.setAutoCommit(false);
						statement = connection.createStatement();
						int records = 0;
						for(Object[] params : votersList)
						{
							records++;
							try{
							statement.executeUpdate("INSERT INTO voter(voter_id,house_no,name,relationship_type,relative_name,gender,age,voter_id_card_no)" +
									" VALUES ('"+params[0].toString()+"','"+params[1].toString().trim()+"','"+replaceSpecialChars(params[2].toString().trim())+"','"+params[3].toString().trim()+"','"+replaceSpecialChars(params[4].toString().trim())+"'," +
											"'"+params[5].toString().trim()+"',"+params[6].toString().trim()+",'"+params[7].toString().trim()+"')");
							}catch(Exception e)
							{
								LOG.error(e);
							}
						}
						LOG.error(ac.getName()+" Constituency "+records+" Voter Records Inserted");
						connection.commit();
						statement.close();
						connection.close();
					}
					
					List<Object[]> votersAndSerialNosList = boothPublicationVoterDAO.getRecordsFromBoothPublicationVoter(ac.getConstituencyId(), publicationId);
					if(votersAndSerialNosList != null && votersAndSerialNosList.size() > 0)
					{
						connection = DriverManager.getConnection("jdbc:sqlite:"+path+pathSeperator+constituencyName+"_"+date+"_Cadre"+pathSeperator+ac.getName()+".sqlite");
						connection.setAutoCommit(false);
						statement = connection.createStatement();
						int records = 0;
						for(Object[] params : votersAndSerialNosList)
						{
							records++;
							try{
							String serialNo = params[3]!= null ? params[3].toString() : "0";
							statement.executeUpdate("INSERT INTO booth_publication_voter(booth_publication_voter_id, booth_id, voter_id, serial_no) VALUES (" +
									"'"+params[0].toString()+"','"+params[1].toString()+"','"+params[2].toString()+"','"+serialNo+"')");
							}catch(Exception e)
							{
								LOG.error(e);
							}
						}
						LOG.error(ac.getName()+" Constituency "+records+" Booth Publication Voter Records Inserted");
						connection.commit();
						statement.close();
						connection.close();
					}
					
					List<Object[]> hamletsList = panchayatHamletDAO.getHamletsListByConstituency(ac.getConstituencyId(),publicationId);
					
					if(hamletsList != null && hamletsList.size() > 0)
					{
						try{
						connection = DriverManager.getConnection("jdbc:sqlite:"+path+pathSeperator+constituencyName+"_"+date+"_Cadre"+pathSeperator+ac.getName()+".sqlite");
						connection.setAutoCommit(false);
						statement = connection.createStatement();
						int records = 0;
						for(Object[] params : hamletsList)
						{
							records++;
							try{
							statement.executeUpdate("INSERT INTO hamlet(hamlet_id,hamlet_name,panchayat_id,tehsil_id)" +
									" VALUES ('"+params[0].toString()+"','"+params[1].toString().trim()+"','"+params[2].toString().trim()+"','"+params[3].toString()+"')");
							}catch(Exception e)
							{
								LOG.error(e);
							}
						}
						LOG.error(ac.getName()+" Constituency "+records+" hamlet Records Inserted");
						connection.commit();
						statement.close();
						connection.close();
						}catch(Exception e)
						{
							LOG.error(e);
						}
					}
					
					List<Object[]> voterNames = voterNamesDAO.getVoterNames(ac.getConstituencyId(),publicationId);
					if(voterNames != null && voterNames.size() > 0)
					{
						try{
					
						connection = DriverManager.getConnection("jdbc:sqlite:"+path+pathSeperator+constituencyName+"_"+date+"_Cadre"+pathSeperator+ac.getName()+".sqlite");
						connection.setAutoCommit(false);
						statement = connection.createStatement();
						int records = 0;
						for(Object[] params : voterNames)	
						{
							records++;
							try{
								String vName = "";
								if(params[1] != null){
								   vName =  params[1].toString().trim();
								}
								if(params[2] != null){
								   vName = vName+" "+params[2].toString().trim();
								}
								String RelativeName = "";
								if(params[3] != null){
									RelativeName =  params[3].toString().trim();
								}
								if(params[4] != null){
									RelativeName = RelativeName+" "+params[4].toString().trim();
								}
								statement.executeUpdate("INSERT INTO voter_telugu_names(voter_id,voter_name,relative_name)" +
										" VALUES ('"+(Long)params[0]+"','"+vName+"','"+RelativeName+"')");
								}catch(Exception e)
								{
									LOG.error(e);
								}
							
						}
						LOG.error(ac.getName()+" Constituency "+records+" voterNames Records Inserted");
						connection.commit();
						statement.close();
						connection.close();
						}catch(Exception e)
						{
							LOG.error(e);
							
						}
					}
					
					
					// member table 
					List<Object[]> cadres = tdMemberDAO.getCadreDataByYear(ac.getConstituencyId());
					if(cadres != null && cadres.size() > 0)
					{
						try{
						connection = DriverManager.getConnection("jdbc:sqlite:"+path+pathSeperator+constituencyName+"_"+date+"_Cadre"+pathSeperator+ac.getName()+".sqlite");
						connection.setAutoCommit(false);
						statement = connection.createStatement();
						int records = 0;
						//0 cadre.memberId,1cadre.memberShipNo,2cadre.firstname,3cadre.relativename,4cadre.gender,5cadre.mobileNo,
						//6 cadre.dateOfBirth,7cadre.educationId,8 cadre.userAddress.panchayat,9 cadre.userAddress.constituency.constituencyId,
						//10 cadre.userAddress().tehsil().tehsilId,11 cadre.userAddress.localElectionBody,12 cadre.occupationId,
						//13 cadre.casteState.casteStateId, 14cadre.enrollmentYear,15cadre.image,16cadre.nameLocal
						 
						 for(Object[] cadre : cadres)
						 {
							 StringBuilder str = new StringBuilder();		
							 try{
					  str.append("INSERT INTO member(member_id,membership_no,member_name," +
										"relative_name,gender,mobile,date_of_birth,education_id,panchayat_id,constituency_id,tehsil_id,local_election_body_id,occupation_id," +
										"caste_state_id,year,image,member_telugu_name)" +
													" VALUES (");
					  if(cadre[0] != null){
						  str.append(((Long)cadre[0])+",");
					  }else{
						  str.append("NULL,");
					  }
					  if(cadre[1] != null){
						  str.append("'"+cadre[1].toString().trim()+"',");
					  }else{
						  str.append("NULL,");
					  }
					  if(cadre[2] != null)
					  str.append("'"+cadre[2].toString().trim()+"',");
					  else
						  str.append("NULL,");
					  if(cadre[3] != null)
					  str.append("'"+cadre[3].toString().trim()+"',");
					  else
						  str.append("NULL,");  
					 if(cadre[4] != null)
					  str.append("'"+cadre[4].toString().trim()+"',");
					 else
						 str.append("NULL,");   
					 if(cadre[5] != null)
					  str.append("'"+cadre[5].toString().trim()+"',");
					 else
						 str.append("NULL,");   
					  if(cadre[6] != null)
					  str.append("'"+cadre[6].toString().substring(0,10)+"',");
					  else
						  str.append("NULL,");    
					 if(cadre[7] != null)
					  str.append("'"+(Long)cadre[7]+"',");
					 else
						 str.append("NULL,"); 
					
			
						  if(cadre[8] != null)
							  str.append("'"+(Long)cadre[8]+"',");
						  else
							  str.append("NULL,"); 	  
						  if(cadre[9] != null )
							  str.append("'"+(Long)cadre[9]+"',");
						  else
						  str.append("NULL,");  
						  if(cadre[10] != null)
							  str.append("'"+(Long)cadre[10]+"',");
						  else
						  str.append("NULL,");  
					 
						  if(cadre[11] != null)
						 str.append("'"+(Long)cadre[11]+"',"); 
						  else
						 str.append("NULL,"); 	 
					 
					  if(cadre[12] != null)
					  str.append("'"+(Long)cadre[12]+"',");
					  else
						  str.append("NULL,");    
					  if(cadre[13] != null)
					  str.append("'"+(Long)cadre[13]+"',");
					   else
					  str.append("NULL,");     
					 
					  str.append((Long)cadre[14]);
					  if(cadre[15] != null)
					  str.append(",'"+cadre[15].toString().trim()+"',");
					   else
					  str.append(",NULL,");  
					  if(cadre[16] != null)
						 str.append("'"+cadre[16].toString().trim()+"'");
					  else
					     str.append("NULL");  
					  str.append(")");
					  statement.executeUpdate(str.toString());
								}catch(Exception e)
									{
									System.out.println(str.toString());
												LOG.error(e);
									}
					 		}
					 
						LOG.error(ac.getName()+" Constituency 2012 cadre Data Records Inserted");
						connection.commit();
						statement.close();
						connection.close();
						}
						catch (Exception e) {
							
						}
						
					}
					}catch(Exception e)
					{
						LOG.error("Exception Occured for "+ac.getName()+" Constituency, Exception is - ",e);
					}
					
					try{/*
						//0nMemberId member_id,1sMemberTName member_telugu_name,2sMemberShipNo membership_no,3sPhoto photo
						List<Object[]> cadreUpdates = tdMemberDAO.getConstituencyDetails(ac.getConstituencyId());
						if(cadreUpdates != null && cadreUpdates.size() > 0)
						{
							connection = DriverManager.getConnection("jdbc:sqlite:"+path+pathSeperator+constituencyName+"_"+date+"_Cadre"+pathSeperator+ac.getName()+".sqlite");
							connection.setAutoCommit(false);
							statement = connection.createStatement();
							
							for(Object[] update : cadreUpdates)
							{
								
									try{
										StringBuilder quertStr= new StringBuilder();
										 quertStr.append("UPDATE member set member_id = ");
										if(update[0] == null){
										  quertStr.append("''");
										}else{
											quertStr.append("'"+update[0]+"'");
										}
										 quertStr.append(",member_telugu_name= ");
										 if(update[1] == null){
											  quertStr.append("''");
										}else{
											quertStr.append("'"+update[1].toString().trim()+"'");
										}
										 quertStr.append(",image= ");
										 if(update[3] == null){
											  quertStr.append("''");
										}else{
											quertStr.append("'"+update[3].toString().trim()+"'");
										}
										 quertStr.append("where membership_no = '"+update[2]+"' and year='2012'");
									statement.executeUpdate(quertStr.toString());
									}catch(Exception e)
									{
										LOG.error(e);
									}
							}
							connection.commit();
							statement.close();
							connection.close();
						}
					*/}catch(Exception e)
					{
						LOG.error("Exception Occured for "+ac.getName()+" Constituency, while updating cadre info Exception is - ",e);
					}
				}
				
				try{
					 for(File rf : destDir.listFiles())
						 addToZipFile(rf.getAbsolutePath(), zos);
					 zos.close();
					 fos.close();
				 }catch(Exception e)
				 {
					 LOG.error("Exception Occured in Zipping Files");
				 }
				
				resultStatus.setMessage("/SQLITE_DB_CADRE/"+constituencyName+"_"+date+"_Cadre.zip");
				resultStatus.setResultCode(ResultCodeMapper.SUCCESS);
				return resultStatus;
			}
			catch (Exception e) {
				 LOG.error("Exception Occured in createCadreDataDumpFileForAParliamnetConstituency(), Exception is - ",e);
			}
			return resultStatus;
		 }
		 
		 public ResultStatus create2016CadreDataSqliteFileForAParliamnetConstituency(RegistrationVO reVo)
		 {
			 LOG.info("Entered into createCadreDataDumpFileForAParliamnetConstituency() Method ");
			 ResultStatus resultStatus = new ResultStatus();
			try{
				Class.forName("org.sqlite.JDBC");
				Connection connection = null;
				Statement statement = null;
				
				Long pconstituencyId = reVo.getConstituencyId();
				Long publicationId = reVo.getPublicationDateId();
				String constituencyName = constituencyDAO.get(pconstituencyId).getName();
				String path = reVo.getPath();
				
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
				String date = sdf.format(new Date());
				String pathSeperator = System.getProperty(IConstants.FILE_SEPARATOR);
				
				File destDir = new File(path+pathSeperator+constituencyName+"_"+date+"_Cadre2016");
				destDir.mkdir();
				File baseFile = new File(path+pathSeperator+"Base"+pathSeperator+"base.sqlite");
				
				FileOutputStream fos = new FileOutputStream(path+pathSeperator+constituencyName+"_"+date+"_Cadre2016.zip");
				ZipOutputStream zos = new ZipOutputStream(fos);
				
				List<Constituency> acList = delimitationConstituencyAssemblyDetailsDAO.findAssemblyConstituencies(pconstituencyId,2009l);
				
				for(Constituency ac : acList)
				{
					try{
						try{
						File acFile = new File(path+pathSeperator+constituencyName+"_"+date+"_Cadre2016"+pathSeperator+ac.getName()+".sqlite");
						FileUtils.copyFile(baseFile, acFile);
						}catch(Exception e)
						{
							LOG.error("Exception is -",e);
						}
					
						List<Object[]> votersList = boothPublicationVoterDAO.getVoterDetailsOfAConstituencyAndPublication(ac.getConstituencyId(),publicationId);
					
						if(votersList != null && votersList.size() > 0)
						{
							connection = DriverManager.getConnection("jdbc:sqlite:"+path+pathSeperator+constituencyName+"_"+date+"_Cadre2016"+pathSeperator+ac.getName()+".sqlite");
							connection.setAutoCommit(false);
							statement = connection.createStatement();
							int records = 0;
							for(Object[] params : votersList)
							{
								records++;
								try{
								statement.executeUpdate("INSERT INTO voter(voter_id,house_no,name,relationship_type,relative_name,gender,age,voter_id_card_no)" +
										" VALUES ('"+params[0].toString()+"','"+params[1].toString().trim()+"','"+replaceSpecialChars(params[2].toString().trim())+"','"+params[3].toString().trim()+"','"+replaceSpecialChars(params[4].toString().trim())+"'," +
												"'"+params[5].toString().trim()+"',"+params[6].toString().trim()+",'"+params[7].toString().trim()+"')");
								}catch(Exception e)
								{
									LOG.error(e);
								}
							}
							LOG.error(ac.getName()+" Constituency "+records+" Voter Records Inserted");
							connection.commit();
							statement.close();
							connection.close();
						}
					
						List<Object[]> votersAndSerialNosList = boothPublicationVoterDAO.getRecordsFromBoothPublicationVoter(ac.getConstituencyId(), publicationId);
						
						if(votersAndSerialNosList != null && votersAndSerialNosList.size() > 0)
						{
							connection = DriverManager.getConnection("jdbc:sqlite:"+path+pathSeperator+constituencyName+"_"+date+"_Cadre2016"+pathSeperator+ac.getName()+".sqlite");
							connection.setAutoCommit(false);
							statement = connection.createStatement();
							int records = 0;
							for(Object[] params : votersAndSerialNosList)
							{
								records++;
								try{
								String serialNo = params[3]!= null ? params[3].toString() : "0";
								statement.executeUpdate("INSERT INTO booth_publication_voter(booth_publication_voter_id, booth_id, voter_id, serial_no) VALUES (" +
										"'"+params[0].toString()+"','"+params[1].toString()+"','"+params[2].toString()+"','"+serialNo+"')");
								}catch(Exception e)
								{
									LOG.error(e);
								}
							}
							LOG.error(ac.getName()+" Constituency "+records+" Booth Publication Voter Records Inserted");
							connection.commit();
							statement.close();
							connection.close();
						}
						
						List<Object[]> cadreList = tdpCadreDAO.getCadreDataForSqlite(ac.getConstituencyId());
						
						if(cadreList != null && cadreList.size() > 0)
						{
							connection = DriverManager.getConnection("jdbc:sqlite:"+path+pathSeperator+constituencyName+"_"+date+"_Cadre2016"+pathSeperator+ac.getName()+".sqlite");
							connection.setAutoCommit(false);
							statement = connection.createStatement();
							int records = 0;
							for(Object[] params : cadreList)
							{
								records++;
								try{
								 StringBuilder str = new StringBuilder();
								 str.append("INSERT INTO cadre_data(tdp_cadre_id,voter_id,membership_id,cadre_name,relative_name,relation,house_no," +
								 		"image,mobile_no,land_phone_no,blood_group_id,gender,education_id,occupation_id,date_of_birth,age,caste_state_id," +
								 		"inserted_time,update_time,card_number,nominee_name,nominee_aadhar,nominee_relation_id,nominee_age,nominee_gender,photo_type," +
								 		"cadre_aadher_no,family_voterId,card_no,email_id,state_id,district_id,constituency_id,tehsil_id,address_lane1,address_lane2," +
								 		"street,pincode,local_area,local_election_body_id,ward_id,parliament_constituency_id,booth_id,panchayat_id,is_deleted_voter) VALUES (");
								 
								 if(params[0] != null)
									 str.append(params[0].toString()+",");
								 else
									 str.append("NULL,");
								 
								 if(params[1] != null)
									 str.append(params[1].toString()+",");
								 else
									 str.append("NULL,");
								 
								 if(params[2] != null)
									 str.append("'"+params[2].toString()+"',");
								 else
									 str.append("'',");
								 
								 if(params[3] != null)
									 str.append("'"+params[3].toString()+"',");
								 else
									 str.append("'',");
								 
								 if(params[4] != null)
									 str.append("'"+params[4].toString()+"',");
								 else
									 str.append("'',");
								 
								 if(params[5] != null)
									 str.append("'"+params[5].toString()+"',");
								 else
									 str.append("'',");
								 
								 if(params[6] != null)
									 str.append("'"+params[6].toString()+"',");
								 else
									 str.append("'',");
								 
								 if(params[7] != null)
								 {
									 String image = params[7].toString();
									 int ind = image.indexOf("/");
									 
									 if(ind != -1)
										 image = image.substring(ind+1);
									 str.append("'"+image+"',");
								 }
								 else
									 str.append("'',");
								 
								 if(params[8] != null)
									 str.append("'"+params[8].toString()+"',");
								 else
									 str.append("'',");
								 
								 if(params[9] != null)
									 str.append("'"+params[9].toString()+"',");
								 else
									 str.append("'',");
								 
								 if(params[10] != null)
									 str.append(params[10].toString()+",");
								 else
									 str.append("NULL,");
								 
								 if(params[11] != null)
									 str.append("'"+params[11].toString()+"',");
								 else
									 str.append("'',");
								 
								 if(params[12] != null)
									 str.append(params[12].toString()+",");
								 else
									 str.append("NULL,");
								 
								 if(params[13] != null)
									 str.append(params[13].toString()+",");
								 else
									 str.append("NULL,");
								 
								 if(params[14] != null)
									 str.append("'"+params[14].toString()+"',");
								 else
									 str.append("NULL,");
								 
								 if(params[15] != null)
									 str.append(params[15].toString()+",");
								 else
									 str.append("NULL,");
								 
								 if(params[16] != null)
									 str.append(params[16].toString()+",");
								 else
									 str.append("NULL,");
								 
								 if(params[17] != null)
									 str.append("'"+params[17].toString()+"',");
								 else
									 str.append("NULL,");
								 
								 if(params[18] != null)
									 str.append("'"+params[18].toString()+"',");
								 else
									 str.append("NULL,");
								 
								 if(params[19] != null)
									 str.append("'"+params[19].toString()+"',");
								 else
									 str.append("'',");
								 
								 if(params[20] != null)
									 str.append("'"+params[20].toString()+"',");
								 else
									 str.append("'',");
								 
								 if(params[21] != null)
									 str.append("'"+params[21].toString()+"',");
								 else
									 str.append("'',");
								 
								 if(params[22] != null)
									 str.append("'"+params[22].toString()+"',");
								 else
									 str.append("NULL,");
								 
								 if(params[23] != null)
									 str.append("'"+params[23].toString()+"',");
								 else
									 str.append("NULL,");
								 
								 if(params[24] != null)
									 str.append("'"+params[24].toString()+"',");
								 else
									 str.append("'',");
								 
								 if(params[25] != null)
								 {
									 String photoType = params[25].toString();
									 
									 if(photoType.equalsIgnoreCase("V"))
										 photoType = "VOTER";
									 else if(photoType.equalsIgnoreCase("C"))
									 {
										 photoType = "CADRE";
									 }
									 else if(photoType.equalsIgnoreCase("N"))
									 {
										 photoType = "NEW";
									 }
									 
									 str.append("'"+photoType+"',");
								 }
								 else
									 str.append("'',");
								 
								 if(params[26] != null)
									 str.append("'"+params[26].toString()+"',");
								 else
									 str.append("'',");
								 
								 if(params[27] != null)
									 str.append("'"+params[27].toString()+"',");
								 else
									 str.append("NULL,");
								 
								 if(params[28] != null)
									 str.append("'"+params[28].toString()+"',");
								 else
									 str.append("'',");
								 
								 if(params[29] != null)
									 str.append("'"+params[29].toString()+"',");
								 else
									 str.append("'',");
								 
								 if(params[30] != null)
									 str.append("'"+params[30].toString()+"',");
								 else
									 str.append("NULL,");
								 
								 if(params[31] != null)
									 str.append("'"+params[31].toString()+"',");
								 else
									 str.append("NULL,");
								 
								 if(params[32] != null)
									 str.append("'"+params[32].toString()+"',");
								 else
									 str.append("NULL,");
								 
								 if(params[33] != null)
									 str.append("'"+params[33].toString()+"',");
								 else
									 str.append("NULL,");
								 
								 if(params[34] != null)
									 str.append("'"+params[34].toString()+"',");
								 else
									 str.append("'',");
								 
								 if(params[35] != null)
									 str.append("'"+params[35].toString()+"',");
								 else
									 str.append("'',");
								 
								 if(params[36] != null)
									 str.append("'"+params[36].toString()+"',");
								 else
									 str.append("'',");
								 
								 if(params[37] != null)
									 str.append("'"+params[37].toString()+"',");
								 else
									 str.append("'',");
								 
								 if(params[38] != null)
									 str.append("'"+params[38].toString()+"',");
								 else
									 str.append("'',");
								 
								 if(params[39] != null)
									 str.append("'"+params[39].toString()+"',");
								 else
									 str.append("NULL,");
								 
								 if(params[40] != null)
									 str.append("'"+params[40].toString()+"',");
								 else
									 str.append("NULL,");
								 
								 if(params[41] != null)
									 str.append("'"+params[41].toString()+"',");
								 else
									 str.append("NULL,");
								 
								 if(params[42] != null)
									 str.append("'"+params[42].toString()+"',");
								 else
									 str.append("NULL,");
								 
								 if(params[43] != null)
									 str.append("'"+params[43].toString()+"',");
								 else
									 str.append("NULL,");
								 
								 if(params[44] != null)
									 str.append("'"+params[44].toString()+"'");
								 else
									 str.append("''");
								 
								 str.append(")");
								 
								 statement.executeUpdate(str.toString());
								 
							}catch(Exception e)
							{
								LOG.error(e);
							}
								 
							}//For
							
							LOG.error(ac.getName()+" Constituency "+records+" Cadre Records Inserted");
							connection.commit();
							statement.close();
							connection.close();
							
							connection = DriverManager.getConnection("jdbc:sqlite:"+path+pathSeperator+constituencyName+"_"+date+"_Cadre2016"+pathSeperator+ac.getName()+".sqlite");
							statement = connection.createStatement();
							
							try{
								statement.executeUpdate("CREATE INDEX idx_voter_voter_id_card_no ON voter(voter_id_card_no)");
								statement.executeUpdate("CREATE INDEX idx_voter_age ON voter(age)");
								statement.executeUpdate("CREATE INDEX idx_voter_gender ON voter(gender)");
								statement.executeUpdate("CREATE INDEX idx_voter_voter_id ON voter(voter_id)");
								statement.executeUpdate("CREATE INDEX idx_voter_name ON voter(name)");
								statement.executeUpdate("CREATE INDEX idx_voter_house_no ON voter(house_no)");
								
								statement.executeUpdate("CREATE INDEX idx_voter_booth_id ON booth_publication_voter(booth_id)");
								statement.executeUpdate("CREATE INDEX idx_voter_voter_id ON booth_publication_voter(voter_id)");
								statement.executeUpdate("CREATE INDEX idx_voter_serial_no ON booth_publication_voter(serial_no)");
								
								statement.executeUpdate("CREATE INDEX idx_cadre_data_tdp_cadre_id ON cadre_data(tdp_cadre_id)");
								statement.executeUpdate("CREATE INDEX idx_cadre_data_voter_id ON cadre_data(voter_id)");
								statement.executeUpdate("CREATE INDEX idx_cadre_data_membership_id ON cadre_data(membership_id)");
								statement.executeUpdate("CREATE INDEX idx_cadre_data_cadre_name ON cadre_data(cadre_name");
								statement.executeUpdate("CREATE INDEX idx_cadre_data_mobile_no ON cadre_data(mobile_no)");
								statement.executeUpdate("CREATE INDEX idx_cadre_data_gender ON cadre_data(gender)");
								statement.executeUpdate("CREATE INDEX idx_cadre_data_age ON cadre_data(age)");
								statement.executeUpdate("CREATE INDEX idx_cadre_data_caste_state_id ON cadre_data(caste_state_id)");
								statement.executeUpdate("CREATE INDEX idx_cadre_data_district_id ON cadre_data(district_id)");
								statement.executeUpdate("CREATE INDEX idx_cadre_data_constituency_id ON cadre_data(constituency_id)");
								statement.executeUpdate("CREATE INDEX idx_cadre_data_tehsil_id ON cadre_data(tehsil_id)");
								statement.executeUpdate("CREATE INDEX idx_cadre_data_local_election_body ON cadre_data(local_election_body)");
								statement.executeUpdate("CREATE INDEX idx_cadre_data_ward ON cadre_data(ward)");
								statement.executeUpdate("CREATE INDEX idx_cadre_data_booth_id ON cadre_data(booth_id)");
								statement.executeUpdate("CREATE INDEX idx_cadre_data_panchayat_id ON cadre_data(panchayat_id)");
								statement.executeUpdate("VACUUM");
								
								connection.commit();
								statement.close();
								connection.close();
								
							}catch(Exception e)
							{
								LOG.error(e);
							}
						}
					}catch(Exception e)
					{
						LOG.error(e);
					}
					
				}
				
				try{
					for(File rf : destDir.listFiles())
						addToZipFile(rf.getAbsolutePath(), zos);
					zos.close();
					fos.close();
				}catch(Exception e)
				{
					LOG.error("Exception Occured in Zipping Files");
				}
				resultStatus.setMessage("/SQLITE_DB_CADRE/"+constituencyName+"_"+date+"_Cadre.zip");
				resultStatus.setResultCode(ResultCodeMapper.SUCCESS);
				return resultStatus;
			}catch(Exception e)
			{
				LOG.error("Exception Occured in create2016CadreDataDumpFileForAParliamnetConstituency(), Exception is - ",e);
			}
			return resultStatus;
		}
	 
			public ResultStatus sendSmsToUserForUpdations(String message,String mobileNo)
		  	{
		  		ResultStatus resultStatus = new ResultStatus();
		  		try{
		  			String[] mobilenoarr = new String [] {mobileNo};
		  			resultStatus = smsCountrySmsService.sendSmsFromAdmin(message, true, mobilenoarr);
		  		}
		  		catch(Exception e)
		  		{
		  			LOG.error("Exception Occured in sendSmsToMobileAppUser Method");	
		  		}
				return resultStatus;
		  	}
			
			/////////////////////////////////////
			public List<MobileUserVO> locationWiseOverView(String startDateString,String endDateString,List<Long> locationIds,String locationType,List<String> userTypes){
		    	
				SimpleDateFormat sdf=new SimpleDateFormat("MM/dd/yyyy");
				List<MobileUserVO> finalList=null;
				try{
					
					Date startDate=null;
					Date endDate=null;
					if(startDateString!=null && startDateString.trim().length()>0){
						startDate=sdf.parse(startDateString);
					}
					if(endDateString!=null && endDateString.trim().length()>0){
						endDate=sdf.parse(endDateString);
					}
					
					List<Object[]> list=mobileAppUserVoterDAO.locationWiseOverView(startDate,endDate,locationIds,locationType,userTypes);
					
					if(list!=null && list.size()>0){
						finalList=new ArrayList<MobileUserVO>();
						
						for(Object[]  obj:list){
							 
							Long wardId = obj[0]!=null?(Long)obj[0]:null;
							if(wardId!=null){
								boolean isDivisionExist=true;
								MobileUserVO divisionVO=getMatchingVO(finalList,wardId,"","division");
								
								if(divisionVO==null){
									isDivisionExist=false;
									divisionVO=new MobileUserVO();
									divisionVO.setWardId(wardId);
									divisionVO.setDivisionNo(obj[1]!=null?(String)obj[1]:"");
									divisionVO.setDivisionName(obj[2]!=null?(String)obj[2]:"");
									if(divisionVO.getDateList()==null){
										divisionVO.setDateList(new ArrayList<MobileUserVO>());
									}
								}
								boolean isDateExist=true;
								String dateString=obj[3]!=null?obj[3].toString():null;
								
								if(dateString!=null){
									MobileUserVO dateVO=getMatchingVO(divisionVO.getDateList(),null,dateString,"date");
									if(dateVO==null){
										isDateExist=false;
										dateVO=new MobileUserVO();
										dateVO.setDateString(obj[3]!=null?obj[3].toString():"");
										dateVO.setFormatDate(obj[3]!=null?sdf.format((Date)obj[3]):"");
										if(dateVO.getRatingList()==null){
											List<MobileUserVO> ratingsList=setRatings();
											dateVO.setRatingList(ratingsList);
										}
									}
									dateVO.setUsersCount(obj[4]!=null?(Long)obj[4]:0l);
									dateVO.setVoterscount(obj[5]!=null?(Long)obj[5]:0l);
									dateVO.setMobilescount(obj[6]!=null?(Long)obj[6]:0l);
									dateVO.setTdpCadreCount(obj[7]!=null?(Long)obj[7]:0l);
									dateVO.setPublicCount(dateVO.getVoterscount() - dateVO.getTdpCadreCount());
									if(!isDateExist){
										divisionVO.getDateList().add(dateVO);
									}
								}
								
								if(!isDivisionExist){
									finalList.add(divisionVO);
								}
							}
						}
					}
					
					List<Object[]> ratingsList=mobileAppUserVoterDAO.voterRatings(startDate,endDate,locationIds,userTypes);
					if(ratingsList!=null && ratingsList.size()>0){
						for(Object[] data:ratingsList){
							Long wardId=data[0]!=null?(Long)data[0]:null;
							if(wardId!=null){
								MobileUserVO divisionVO=getMatchingVO(finalList,wardId,"","division");
								if(divisionVO!=null){
									String dateString=data[1]!=null?data[1].toString():null;
									if(dateString!=null){
										MobileUserVO dateVO=getMatchingVO(divisionVO.getDateList(),null,dateString,"date");	
										if(dateVO!=null){
											Long ratingId=data[2]!=null?(Long)data[2]:null;
											if(ratingId!=null){
												MobileUserVO ratingVO=getMatchingVO(dateVO.getRatingList(),ratingId,"","rating");
												if(ratingVO!=null){
													ratingVO.setRatingCount(data[3]!=null?(Long)data[3]:0l);
												}
											}
											
										}
									}
								}
							}
						}
					}
					
				}catch(Exception e){
					LOG.error("Exception Occured in locationWiseOverView Method",e);
				}
				return finalList;
			}
			
			
			public MobileUserVO getMatchingVO(List<MobileUserVO> resultList,Long Id,String name,String type)
			{
				try{
					 if(resultList != null && resultList.size() > 0){
						 
						for(MobileUserVO vo : resultList)
						{
							if(type.equalsIgnoreCase("division")){
								if(vo.getWardId().longValue() == Id.longValue())
									return vo;
							}else if(type.equalsIgnoreCase("date")){
								if(vo.getDateString().equalsIgnoreCase(name.toString()))
									return vo;
							}else if(type.equalsIgnoreCase("rating")){
								if(vo.getRatingId().longValue() == Id.longValue())
									return vo;
							}
						}
						
					  }
					}
				catch (Exception e) {
					LOG.error("Exception Occured in getMatchingVO Method",e);
				}
				return null;
			
			 }
			
			public List<MobileUserVO> setRatings(){
				List<MobileUserVO> ratingsList=new ArrayList<MobileUserVO>();
				for(Long i=0l;i<=5l;i++){
					MobileUserVO vo=new MobileUserVO();
					vo.setRatingId(i);
					vo.setRatingCount(0l);
					ratingsList.add(vo);
				}
				return ratingsList;
			}
			
			/*public MobileAppUserDetailsVO getUserWiseDivisionSummary(Long locationId, String locationType, String startDateString, String endDateString,List<String> userType){
				MobileAppUserDetailsVO finalVO = new MobileAppUserDetailsVO();
				try {
					SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
					
					Date fromDate=null;
					Date toDate=null;
					if(startDateString!=null && startDateString.trim().length()>0){
						fromDate=sdf.parse(startDateString);
					}
					if(endDateString!=null && endDateString.trim().length()>0){
						toDate=sdf.parse(endDateString);
					}

					  
					finalVO.setRatings(getBasicRatings());
					
					List<MobileAppUserDetailsVO> fnlLst  = new ArrayList<MobileAppUserDetailsVO>(); 
					List<Object[]> list = mobileAppUserVoterDAO.getUserStartEndTime(locationId, locationType, fromDate, toDate,userType);
					List<Object[]> list1 = mobileAppUserVoterDAO.getUserCollectedDetails(locationId, locationType, fromDate, toDate,userType);
					List<Long> divisionIds = new ArrayList<Long>(0);
					divisionIds.add(locationId);
					
					List<Object[]> wardwiseVoters = greaterMuncipalWardDAO.getDivisionWiseVoters(divisionIds);
					if(wardwiseVoters != null && wardwiseVoters.size()>0)
					{
						Object[] wardVoters = wardwiseVoters.get(0);
						if(wardVoters != null && wardVoters.length>0)
						{
							int total = wardwiseVoters.get(0)[1] != null ? Integer.valueOf(wardwiseVoters.get(0)[1].toString().trim()):0;
							if(total > 0)
								finalVO.setTotalVoters(total);
						}
					}
					
					List<Long> usrIds = new ArrayList<Long>();
					if(list!=null && !list.isEmpty()){
						for(Object[] obj:list){
							MobileAppUserDetailsVO temp = new MobileAppUserDetailsVO();
							temp.setMobileAppUserId(obj[0]!=null?Long.valueOf(obj[0].toString()):null);
							temp.setName(obj[1]!=null?obj[1].toString():"");
							temp.setUserName(obj[8] != null ? obj[8].toString():"");
							temp.setMobileNo(obj[2]!=null?obj[2].toString():"");
							temp.setUniqueCode(obj[4]!=null?obj[4].toString():"");
							temp.setDate(obj[5]!=null?obj[5].toString():"");
							temp.setRatings(getBasicRatings());
							SimpleDateFormat innrSdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.S");
							SimpleDateFormat reqSdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
							String stDate = "";
							String edDate = "";
							
							if(obj[6]!=null){
								Date reqDate1 = innrSdf.parse(obj[6].toString());
								stDate = reqSdf.format(reqDate1);
							}
							
							if(obj[7]!=null){
								Date reqDate2 = innrSdf.parse(obj[7].toString());
								edDate = reqSdf.format(reqDate2);
							}
							
							temp.setStartTime(stDate);
							temp.setEndtime(edDate);
							
							if(obj[0]!=null && !usrIds.contains(Long.valueOf(obj[0].toString()))){
								usrIds.add(Long.valueOf(obj[0].toString()));
							}
							
							fnlLst.add(temp);
						}
					}
					
					finalVO.setUsersCount(usrIds.size());
					
					List<Object[]> list3 = mobileAppUserVoterDAO.getUserCollectedRatingDetails(locationId, locationType, fromDate, toDate,userType);
					if(list3!=null && !list3.isEmpty()){
						for(Object[] obj:list3){
							MobileAppUserDetailsVO mv = getMatchedMobileAppUserDetailsVO(fnlLst, Long.valueOf(obj[0].toString()), obj[1].toString());
							if(mv!=null){
								List<MobileAppUserDetailsVO> ratings = mv.getRatings();
								if(ratings!=null && !ratings.isEmpty()){
									int ratingId = obj[2]!=null?Integer.parseInt(obj[2].toString()):0;
									MobileAppUserDetailsVO ratingVO = getMatchedRatingVO(ratings, ratingId);
									if(ratingVO!=null){
										int count = obj[3]!=null?Integer.parseInt(obj[3].toString()):0;
										ratingVO.setRatingCount(count);
										MobileAppUserDetailsVO mainRtngVO = getMatchedRatingVO(finalVO.getRatings(), ratingId);
										if(mainRtngVO==null){
											mainRtngVO.setRatingCount(count);
										}else{
											mainRtngVO.setRatingCount(mainRtngVO.getRatingCount()+count);
										}
									}
									
								}
							}
						}
					}
					
					int ttlMbls = 0;
					int ttlVtrs = 0;
					int ttlUnqeVtrs = 0;
					
					if(list1!=null && !list1.isEmpty()){
						for(Object[] obj:list1){
							MobileAppUserDetailsVO mv = getMatchedMobileAppUserDetailsVO(fnlLst, Long.valueOf(obj[0].toString()), obj[1].toString());
							if(mv!=null){
								int mbls = obj[2]!=null?Integer.parseInt(obj[2].toString()):0;
								int vtrs = obj[3]!=null?Integer.parseInt(obj[3].toString()):0;
								int unqVtrs = obj[4]!=null?Integer.parseInt(obj[4].toString()):0;
								
								mv.setNoOfMobiles(mbls);
								mv.setVoterIdsCollected(vtrs);
								mv.setUniqueVoters(unqVtrs);
								
								ttlMbls += mbls;
								ttlVtrs += vtrs;
								ttlUnqeVtrs += unqVtrs;
							}
						}
					}
					
					finalVO.setNoOfMobiles(ttlMbls);
					finalVO.setVoterIdsCollected(ttlVtrs);
					finalVO.setUniqueVoters(ttlUnqeVtrs);
					
					List<MobileAppUserSmsStatus> list2 = mobileAppUserSmsStatusDAO.getUsersLatestData(usrIds, fromDate, toDate,userType);
					if(list2!=null && !list2.isEmpty()){
						List<Long> updtdUsrs = new ArrayList<Long>();
						for(MobileAppUserSmsStatus obj:list2){
							SimpleDateFormat innrSdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.S");
							SimpleDateFormat reqSdf = new SimpleDateFormat("yyyy-MM-dd");
							
							Long userId = Long.valueOf(obj.getMobileAppUserId().toString());
							Date reqDate = innrSdf.parse(obj.getStatusDate().toString());
							String date = reqSdf.format(reqDate);
							MobileAppUserDetailsVO mv = getMatchedMobileAppUserDetailsVO(fnlLst, userId, date);
							if(mv!=null && !updtdUsrs.contains(userId)){
								mv.setNoOfSmsSent(Integer.parseInt(obj.getSentSms().toString()));
								updtdUsrs.add(userId);
							}
						}
					}
					
					finalVO.setUserRslt(fnlLst);
				} catch (Exception e) {
					LOG.error("Exception Raised in getUserWiseDivisionSummary",e);
				}
			
				return finalVO;
			}*/
			
			public List<MobileAppUserDetailsVO> getBasicRatings(){
				List<MobileAppUserDetailsVO> ratings = new ArrayList<MobileAppUserDetailsVO>();
				for(int i=0;i<=5;i++){
					MobileAppUserDetailsVO mv = new MobileAppUserDetailsVO();
					mv.setRating(i);
					mv.setRatingCount(0);
					ratings.add(mv);
				}
				return ratings;
			}
			
			public MobileAppUserDetailsVO getMatchedMobileAppUserDetailsVO(List<MobileAppUserDetailsVO> list, Long id, String date){
				if(id!=null && !list.isEmpty()){
					if(date.length()<=0){
						for(MobileAppUserDetailsVO mv:list){
							if(mv.getMobileAppUserId().equals(id)){
								return mv;
							}
						}
					}else{
						try {
							SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
							Date inputDate = sdf.parse(date);
							
							for(MobileAppUserDetailsVO mv:list){
								if(mv.getDate().length()>0){
									Date mvDate = sdf.parse(mv.getDate());
									if(mv.getMobileAppUserId().equals(id) && mvDate.equals(inputDate)){
										return mv;
									}
								}
							}
						} catch (Exception e) {
							LOG.error("Exception Raised in getMatchedMobileAppUserDetailsVO",e);
						}
					}
				}
				return null;
			}
			
			public MobileAppUserDetailsVO getMatchedRatingVO(List<MobileAppUserDetailsVO> list, int ratingId){
				if(!list.isEmpty()){
					for(MobileAppUserDetailsVO mv:list){
						if(mv.getRating()==ratingId){
							return mv;
						}
					}
				}
				return null;
			}
			
			public MobileUserVO overAllDivisionsSummary(String startDateString,String endDateString,List<Long> locationIds,List<String> userTypes){
		    	
		    	SimpleDateFormat sdf=new SimpleDateFormat("MM/dd/yyyy");
		    	MobileUserVO finalVO=new MobileUserVO();
		    	try{
		    		
		    		Date startDate=null;
					Date endDate=null;
					if(startDateString!=null && startDateString.trim().length()>0){
						startDate=sdf.parse(startDateString);
					}
					if(endDateString!=null && endDateString.trim().length()>0){
						endDate=sdf.parse(endDateString);
					}
					if(finalVO.getRatingList()==null){
						finalVO.setRatingList(setRatings());
					}
					
					List<Object[]> list=mobileAppUserVoterDAO.overAllDivisionsSummary(startDate,endDate,locationIds,userTypes);
					if(list!=null && list.size()>0){
						Object[] obj=list.get(0);
						if(obj!=null){
							
							finalVO.setWardsCount(obj[0]!=null?(Long)obj[0]:0l);
							finalVO.setUsersCount(obj[1]!=null?(Long)obj[1]:0l);
							finalVO.setVoterscount(obj[2]!=null?(Long)obj[2]:0l);
							finalVO.setMobilescount(obj[3]!=null?(Long)obj[3]:0l);
						}
					}
					List<Object[]> ratings=mobileAppUserVoterDAO.overallVoterRatings(startDate,endDate,locationIds,userTypes);
					if(ratings!=null && ratings.size()>0){
						for(Object[] obj:ratings){
							Long ratingId=obj[0]!=null?(Long)obj[0]:null;
							if(ratingId!=null){
								MobileUserVO ratingVO=getMatchingVO(finalVO.getRatingList(),ratingId,"","rating");
							    if(ratingVO!=null){
							    	ratingVO.setRatingCount(obj[1]!=null?(Long)obj[1]:0l);
							    }
							}
						}
					}
					
				}catch(Exception e){
					LOG.error("Exception Occured in overAllDivisionsSummary Method",e);
				}
		    	return finalVO;
		    }
			
	public List<TabDetailsVO> showMapForMobileAppUserVoter(Long userId,Long divisonId,List<String> dateStrList){
		List<TabDetailsVO> tabDetailsVoList = new ArrayList<TabDetailsVO>(0);
		try {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			
			List<Date> datesList = new ArrayList<Date>(0);
			
			if(dateStrList != null && dateStrList.size()>0){
				for (String string : dateStrList) {
					datesList.add(sdf.parse(string));
				}
			}
			
			//0-latitude,1-longitude,2-date,3-costNameid,4-constname,5-votername,6-relativename,7-votercardnum,8-mobileno,9-ration,10-boothpartno
			List<Object[]> latiLongiObjList = mobileAppUserVoterDAO.getLatiLongi(userId,divisonId,datesList);
			
			if(latiLongiObjList != null && latiLongiObjList.size()>0){
				for (Object[] objects : latiLongiObjList) {
					TabDetailsVO vo = new TabDetailsVO();
					vo.setLatitude(objects[0]!=null?objects[0].toString():"");
					vo.setLongitude(objects[1]!=null?objects[1].toString():"");
					vo.setSurveyDate(objects[2]!=null?objects[2].toString():"");
					vo.setSyncSource(objects[3]!=null?objects[3].toString():"");//constnameid
					vo.setUniqueKey(objects[4]!=null?objects[4].toString():"");//constname
					vo.setVotername(objects[5]!=null?objects[5].toString():"");
					vo.setRelativename(objects[6]!=null?objects[6].toString():"");
					vo.setVotercardnum(objects[7]!=null?objects[7].toString():"");
					vo.setVoterMobileNo(objects[8]!=null?objects[8].toString():"");
					vo.setRating(objects[9]!=null?objects[9].toString():"");
					vo.setBoothpartno(objects[10]!=null?objects[10].toString():"");
					tabDetailsVoList.add(vo);
				}
			}
			
			if(tabDetailsVoList!=null && tabDetailsVoList.size()>0){
				//get all available for user and divison
				List<Object> availDates = mobileAppUserVoterDAO.getAllAvailableForUser(userId,divisonId);
				
				if(availDates!=null && availDates.size()>0){
					for (Object object : availDates) {
						tabDetailsVoList.get(0).getAvailableDates().add(object.toString());
					}
				}
				
				//get user details(user id, name, mobile num)
				MobileAppUser mobileAppUser = mobileAppUserDAO.get(userId);
				if(mobileAppUser!=null){
					tabDetailsVoList.get(0).setCurrentTabUserId(mobileAppUser.getMobileAppUserId());
					tabDetailsVoList.get(0).setName(mobileAppUser.getUserName());
					tabDetailsVoList.get(0).setMobileNo(mobileAppUser.getMobileNo());
					tabDetailsVoList.get(0).setUserName(mobileAppUser.getName());
				}
				
				//get no of sms set
				List<Long> noSmsList = mobileAppUserSmsStatusDAO.getNoOfSmsCountOfUser(userId,datesList);
				if(noSmsList!=null && noSmsList.size()>0){
					tabDetailsVoList.get(0).setNoofSmsSent(noSmsList.get(0));
				}
				
				//get number of nums collected
				Long mobNumCount = mobileAppUserVoterDAO.getNumberOfNumsCollected(userId,divisonId,datesList);
				tabDetailsVoList.get(0).setMobileNosCount(mobNumCount);
			}
			
		} catch (Exception e) {
			LOG.error("Exception raised at showMapForMobileAppUserVoter", e);
		}
		
		return tabDetailsVoList;
	}
	
	 public String saveUserLocationData(List<UserLocationTrackingVo> userLocationDetails)
	   {
		   try
		   {
			   for(UserLocationTrackingVo locationDetails:userLocationDetails)
			   {
				   List<UserLocationTracking>  userLocationDtls = null;	
							try
							{
							 boolean jdbcExceptionOccured = false;
							 
								for(int count = 0 ; count<10 ; count++){
									jdbcExceptionOccured = false;
									
									try
									{
										  userLocationDtls = userLocationTrackingDAO.getUserLocationByUserIdAndUniqueId(locationDetails.getUserId(),
										  locationDetails.getUniqueId());									 
									}catch(DataAccessResourceFailureException jdbcExe){
										 jdbcExceptionOccured = true;
										 LOG.error("DataAccessResourceFailureException occured while saving  surveyAnswerInfo details for "+count+" time : ", jdbcExe);
										 Thread.sleep(1000);
									 }
									 catch(JDBCConnectionException jdbcExe){
										 jdbcExceptionOccured = true;
										 LOG.error("JDBCConnectionException occured while saving  surveyAnswerInfo details "+count+" time : ", jdbcExe);
										 Thread.sleep(1000);
									 }catch(Exception e){
										 LOG.error("Exception occured while saving  surveyAnswerInfo details : ", e);
										 throw new Exception();
									 }
									 if(!jdbcExceptionOccured){
										 break;
									 }
								}
							}catch(Exception e)
							{
								e.printStackTrace();						
							}
						     
					if(userLocationDtls == null || userLocationDtls.size()  == 0)
					{
						UserLocationTracking locationDtls = new UserLocationTracking();
						
						locationDtls.setUserId(locationDetails.getUserId());
						locationDtls.setLongitude(locationDetails.getLongitude());
						locationDtls.setLatitude(locationDetails.getLatitude());
						locationDtls.setUniqueId(locationDetails.getUniqueId());
						locationDtls.setImeiNo(locationDetails.getImeiNo());
						
					     SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");	
					     if(locationDetails.getDateTime() != null && locationDetails.getDateTime().length()>0)
					    	 locationDtls.setSurveyTime(format.parse(locationDetails.getDateTime()));
					     if(locationDetails.getGpsTIME() != null && locationDetails.getGpsTIME().length()>0)
					    	 locationDtls.setGpsTime(format.parse(locationDetails.getGpsTIME()));
					     
					    locationDtls.setInsertedTime(new DateUtilService().getCurrentDateAndTime());
					    try
						{
						 boolean jdbcExceptionOccured = false;
						 
							for(int count = 0 ; count<10 ; count++){
								jdbcExceptionOccured = false;
								try
								{
									userLocationTrackingDAO.save(locationDtls);
									 
								}catch(DataAccessResourceFailureException jdbcExe){
									 jdbcExceptionOccured = true;
									 LOG.error("DataAccessResourceFailureException occured while saving  surveyAnswerInfo details "+count+" time : ", jdbcExe);
									 Thread.sleep(1000);
								 }
								 catch(JDBCConnectionException jdbcExe){
									 jdbcExceptionOccured = true;
									 LOG.error("JDBCConnectionException occured while saving  surveyAnswerInfo details "+count+" time : ", jdbcExe);
									 Thread.sleep(1000);
								 }catch(Exception e){
									 LOG.error("Exception occured while saving  surveyAnswerInfo details : ", e);
									 throw new Exception();
								 }
								 if(!jdbcExceptionOccured){
									 break;
								 }
							}
						}catch(Exception e)
						{
							e.printStackTrace();						
						}
					}
			   }
		   }
		   catch(Exception e)
		   {
			  e.printStackTrace();
			  return "error";
			   
		   }
		   return "success";
		   
	   }
	 
	 public List<TabDetailsVO> getUserTrackingDetails(Long userId){
		 List<TabDetailsVO> voList = new ArrayList<TabDetailsVO>(0);
		 try {
			 List<Object[]> trackingDetailsObjList = userLocationTrackingDAO.getUserTrackingDetails(userId);
			 
			 if(trackingDetailsObjList != null && trackingDetailsObjList.size() > 0){
				 for (Object[] objects : trackingDetailsObjList) {
					 TabDetailsVO vo = new TabDetailsVO();
					 vo.setLatitude(objects[0]!=null?objects[0].toString():"");
					 vo.setLongitude(objects[1]!=null?objects[1].toString():"");
					 vo.setSurveyDate(objects[2]!=null?objects[2].toString():"");
					 voList.add(vo);
				}
			 }
				 
		} catch (Exception e) {
			LOG.error("Exception raised at getUserTrackingDetails", e);
		}
		 
		 return voList;
	 }
	 
	 public List<IdNameVO> getAssignedWardsByUser(Long userId){
		 List<IdNameVO> finalList = new ArrayList<IdNameVO>();
		 try {
			
			 List<Object[]> list = userAccessLevelValueDAO.getAssignedWardsByUser(userId);
			 if(list != null && list.size() > 0){
				 for (Object[] obj : list) {
					IdNameVO vo = new IdNameVO();
					
					vo.setId((Long) (obj[0] != null ? obj[0]:0l));
					String wardName = obj[1] != null ? obj[1].toString():"";
					String divisionName = obj[2] != null ? obj[2].toString():"";
					vo.setName(divisionName+" - ("+wardName+") ");
					
					finalList.add(vo);
				}
			 }
		} catch (Exception e) {
			LOG.error("Exception raised at getAssignedWardsByUser", e);
		}
		return finalList;
	 }
	 
	 public PollManagementVO overAllPollManagementSummary( List<Long> locationIds){
	    	
	    	PollManagementVO  finalVO=new PollManagementVO();
	    	try{
				   List<Long> divisionIds = mobileAppUserVoterDAO.getTrackingDivisionIds(locationIds);
	    		   if(divisionIds!=null && divisionIds.size()>0){
	    			   
	    			   finalVO.setDivisions((long) divisionIds.size());
	    			   finalVO.setBooths(boothDAO.getBoothsCountByDivisionIds(divisionIds,17l));
	    			   finalVO.setTotalCadre(tdpCadreDAO.getTdpCadreCountsForDivisions(divisionIds));
	    			   
	    			   Object totalVotersObj=greaterMuncipalWardDAO.getTotalVotersByDivisionIds(divisionIds);
	    			   if(totalVotersObj!=null){
	    				   finalVO.setTotalVoters(Long.parseLong((String)totalVotersObj)); 
	    			   }
	    			   
	    			   Object[] capturedVoters=mobileAppUserVoterDAO.getTrackingDivisionSummaryCounts(divisionIds);
	    			   if(capturedVoters!=null && capturedVoters.length>0){
	    				   finalVO.setCapturedVoters(capturedVoters[0]!=null?(Long)capturedVoters[0]:0l);
	    				   finalVO.setCapturedCadre(capturedVoters[1]!=null?(Long)capturedVoters[1]:0l);
	    			   }
	    			   
	    			   List<Object[]> capturedVoterRatings=mobileAppUserVoterDAO.getCapturedVoterRatings(divisionIds);  
	    			   if(capturedVoterRatings!=null && capturedVoterRatings.size()>0){
	    				   for(Object[] obj:capturedVoterRatings){
	    					   Long rating=obj[0]!=null?(Long)obj[0]:null;
	    					   Long ratingsCount=obj[1]!=null?(Long)obj[1]:null;
	    					   if(rating!=null){
	    						    if(rating>3l){
	    						    	finalVO.setInclinedVoters(finalVO.getInclinedVoters()+ratingsCount);
	    						    }else if(rating<3){
	    						    	if(rating==0l){
	    						    		finalVO.setNonOptedVoters(ratingsCount);
	    						    	}else{
	    						    		finalVO.setOtherPartyVoters(finalVO.getOtherPartyVoters()+ratingsCount);
	    						    	}
	    						    }else{
	    						    	finalVO.setUnDecidedVoters(ratingsCount);
	    						    }
	    					   }
	    				   }
	    			   }
	    			    
	    		   }
			}catch(Exception e){
				LOG.error("Exception raised at overAllPollManagementSummary", e);
			}
	    	return finalVO;
	    }
	 
	   public List<PollManagementSummaryVO> divisionWiseVotingActivity(List<Long> locationIds){
			List<PollManagementSummaryVO> finalList=null;
	    	try{
	    		
	    		
	    		List<Object[]> divisionDetails=mobileAppUserVoterDAO.divisionWiseTotalVotersAndCapturedCadre(locationIds);
	    		
	    		List<Long> divisionIds=null;
	    		if(divisionDetails!=null && divisionDetails.size()>0){
	    			finalList=new ArrayList<PollManagementSummaryVO>();
	    			divisionIds=new ArrayList<Long>();
	    			
	    		    for(Object[] obj :divisionDetails){
	    		    	
	    		    	Long divisionId=obj[0]!=null?(Long)obj[0]:0l;
	    		    	PollManagementSummaryVO wardVO=new PollManagementSummaryVO();
	    		    	wardVO.setId(divisionId);
	    		    	wardVO.setName(obj[1]!=null?obj[1].toString():"");
	    		    	wardVO.setTotalVoters(obj[2]!=null?Long.valueOf(obj[2].toString()):0l);
	    		    	Long capturedVotersCount=obj[3]!=null?(Long)obj[3]:0l;
	    		    	wardVO.setNonCapVoters(wardVO.getTotalVoters()-capturedVotersCount);
	    		    	wardVO.setCapCadreCount(obj[4]!=null?(Long)obj[4]:0l);
	    		    	wardVO.setSubList(getVoterRatingTypes());
	    		    	finalList.add(wardVO);
	    		    	
	    		    	//add divisions.
	    		    	divisionIds.add(divisionId);
	    		    }
	    		    
	    		    List<Object[]> totalCadreCounts=tdpCadreDAO.getDivisionWiseCadresCount(divisionIds);
	    		    gettingMatchedWardVO(totalCadreCounts,finalList,"totalCadreCounts");
	    		    
	    		    List<Object[]> totalPolledVotersAndCadres=mobileAppUserVoterDAO.getPolledVotersAndPolledCadre(divisionIds);
	    		    gettingMatchedWardVO(totalPolledVotersAndCadres,finalList,"totalPolledVotersAndCadres");
	    		     
	    		    List<Object[]> capturedCadrepolled=mobileAppUserVoterDAO.getCapturedCadrePolled(divisionIds);
	    		    gettingMatchedWardVO(capturedCadrepolled,finalList,"capturedCadrepolled");
	    		     
	    		    List<Object[]> nonCapVoterspolled=mobileAppUserVoterDAO.getNonCapturedVotersPolled(divisionIds);
	    		    gettingMatchedWardVO(nonCapVoterspolled,finalList,"nonCapVoterspolled");
	    		    
	    		    List<Object[]> ratingVotersTracked=mobileAppUserVoterDAO.gettrackedAndPolledratingVoters(divisionIds,"tracked");
	    		    List<Object[]> ratingVotersPolled=mobileAppUserVoterDAO.gettrackedAndPolledratingVoters(divisionIds,"polled");
	    		    gettingMatchedWardVO(ratingVotersTracked,finalList,"ratingVoters");
	    		    gettingMatchedWardVO(ratingVotersPolled,finalList,"ratingVotersPolled");
	    		    
	    		    //calc percantages.
	    		    if(finalList!=null && finalList.size()>0){
	    		    	for(PollManagementSummaryVO wardVO:finalList){
	    		    		
	    		    		//total voters.
	    		    		wardVO.setTotalVotersYetToBePolled(wardVO.getTotalVoters()-wardVO.getTotalVotersPolled());
	    		    		wardVO.setPollPercent(calcPercantage(wardVO.getTotalVoters(),wardVO.getTotalVotersPolled()));
	    		    		wardVO.setYetToPollPercent(calcPercantage(wardVO.getTotalVoters(),wardVO.getTotalVotersYetToBePolled()));
	    		    		//for total cadre.
	    		    		wardVO.setCadreCountYetToBePolled(wardVO.getCadreCount()-wardVO.getCadreCountPolled());
		    				wardVO.setCadrepollPercent(calcPercantage(wardVO.getCadreCount(),wardVO.getCadreCountPolled()));
		    				wardVO.setCadreYetToPollPercent( calcPercantage(wardVO.getCadreCount(),wardVO.getCadreCountYetToBePolled()));
	    		    		//captured cadre
		    				 wardVO.setCapCadreCountYetToBePolled(wardVO.getCapCadreCount()-wardVO.getCapCadreCountPolled());
	    		    		 wardVO.setCapCadrePollPercent((calcPercantage(wardVO.getCapCadreCount(),wardVO.getCapCadreCountPolled())));
	    		    		 wardVO.setCapCadreYetTopollPercent((calcPercantage(wardVO.getCapCadreCount(),wardVO.getCapCadreCountYetToBePolled())) );
	    		    		//nonCapVoterspolled
	    		    		 wardVO.setNonCapVotersYetToBePolled(wardVO.getNonCapVoters()-wardVO.getNonCapVotersPolled());
	    		    		 wardVO.setNonCapVotersPollPercent((calcPercantage(wardVO.getNonCapVoters(),wardVO.getNonCapVotersPolled())));
	    		    		 wardVO.setNonCapVotersYetToPollPercent((calcPercantage(wardVO.getNonCapVoters(),wardVO.getNonCapVotersYetToBePolled())) );
	    		    		 
	    		    		//rating voters
	    		    		for(PollManagementSummaryVO ratingVO: wardVO.getSubList()){
	    		    			 ratingVO.setTotalVotersYetToBePolled(ratingVO.getTotalVoters()-ratingVO.getTotalVotersPolled());
	    		    			 ratingVO.setPollPercent(calcPercantage(ratingVO.getTotalVoters(),ratingVO.getTotalVotersPolled()));
	    		    			 ratingVO.setYetToPollPercent(calcPercantage(ratingVO.getTotalVoters(),ratingVO.getTotalVotersYetToBePolled()));
	    		    		}
	    		    		
	    		    		
	    		    	}
	    		    }
	    		    
	    		}
	    		
	    		
			}catch(Exception e){
				LOG.error("Exception raised at divisionWiseVotingActivity", e);
			}
	    	return finalList;
	    }
	    public void gettingMatchedWardVO(List<Object[]> list,List<PollManagementSummaryVO> finalList,String type){
	    	
	    	try{
				  if(list!=null && list.size()>0){
					  for(Object[] obj:list){
						  if(obj[0]!=null){
							  PollManagementSummaryVO wardVO=getMatchingward(finalList,(Long)obj[0],"division","");
							  if(wardVO!=null){
								  
								  if(type.equalsIgnoreCase("totalCadreCounts")){
									  wardVO.setCadreCount(obj[1]!=null?(Long)obj[1]:0l);
								  }
								  else if(type.equalsIgnoreCase("totalPolledVotersAndCadres")){
								     wardVO.setTotalVotersPolled(obj[1]!=null?(Long)obj[1]:0l); 
	    		    				 wardVO.setCadreCountPolled(obj[2]!=null?(Long)obj[2]:0l); 
								  }
								  else if(type.equalsIgnoreCase("capturedCadrepolled")){
									  wardVO.setCapCadreCountPolled(obj[1]!=null?(Long)obj[1]:0l);
	     		    				
								  }else if(type.equalsIgnoreCase("nonCapVoterspolled")){
									  wardVO.setNonCapVotersPolled(obj[1]!=null?(Long)obj[1]:0l);
	     		    				
								  }else if(type.equalsIgnoreCase("ratingVoters") || type.equalsIgnoreCase("ratingVotersPolled")){
									  
									   Long rating=obj[1]!=null?(Long)obj[1]:null;
			    					   Long ratingsCount=obj[2]!=null?(Long)obj[2]:null;
			    					   PollManagementSummaryVO ratingVO=null;
			    					   if(rating!=null){
			    						    if(rating>3l){
			    						    	  ratingVO=getMatchingward(wardVO.getSubList(),null,"rating","INCLINED VOTERS");
			    						    }else if(rating<3){
			    						    	if(rating==0l){
			    						    		ratingVO=getMatchingward(wardVO.getSubList(),null,"rating","NON OPTED VOTERS");
			    						    	}else{
			    						    		ratingVO=getMatchingward(wardVO.getSubList(),null,"rating","OTHER PARTY VOTERS");
			    						    	}
			    						    }else{
			    						    	    ratingVO=getMatchingward(wardVO.getSubList(),null,"rating","UNDECIDED VOTERS");
			    						    }
			    						    if(type.equalsIgnoreCase("ratingVoters")){
			    						    	ratingVO.setTotalVoters(ratingVO.getTotalVoters()+ratingsCount);
			    						    }else{
			    						    	ratingVO.setTotalVotersPolled(ratingVO.getTotalVotersPolled()+ratingsCount);
			    						    }
			    						    
			    					   }
								  }
								  
							  }
						  }
					  }
				  }
			}catch(Exception e) {
				LOG.error("Exception raised at gettingMatchedWardVO", e);
			}
	    	
	    }
		
		public PollManagementSummaryVO getMatchingward(List<PollManagementSummaryVO> resultList,Long Id,String type,String name)
		{
			try{
				 if(resultList != null && resultList.size() > 0){
					 
					for(PollManagementSummaryVO vo : resultList)
					{
						if(type.equalsIgnoreCase("division")){
							if(vo.getId().longValue() == Id.longValue())
								return vo;
						}else if(type.equalsIgnoreCase("rating")){
							if(vo.getName().equalsIgnoreCase(name)){
								return vo;
							}
						}
					}
					
				  }
				}
			catch (Exception e) {
				LOG.error("Exception raised at getMatchingward", e);
			}
			return null;
		
		 }
	    public String calcPercantage(Long totalValue,Long subValue){
			
			String percentage=null;
			if( (totalValue!=null && totalValue>0l) && (subValue!=null && subValue>0l)){
				
					Double percent=(Double)(subValue*100.00)/totalValue;
					percentage=decimalFormat .format(percent);
			}
			return percentage;
		}
	    
		public List<PollManagementSummaryVO> getVoterRatingTypes(){
			List<PollManagementSummaryVO> ratingVotersList=new ArrayList<PollManagementSummaryVO>();
			String ratingsArray[] = {"INCLINED VOTERS","UNDECIDED VOTERS","OTHER PARTY VOTERS","NON OPTED VOTERS"};
			for(String rating:ratingsArray){
				PollManagementSummaryVO vo=new PollManagementSummaryVO();
				
				vo.setName(rating);
				vo.setTotalVoters(0l);
				vo.setTotalVotersPolled(0l);
				ratingVotersList.add(vo);
			}	
			return ratingVotersList;
		}
	 
	 
	 
	 
	 public PollManagementVO overAllPollManagementSummaryByDivisionOrWard(Long wardId)
	 {
		 PollManagementVO  finalVO=new PollManagementVO();
			try{
				
				List<Long> wardIds = new ArrayList<Long>();
				
				wardIds.add(wardId);
				
				Long boothsCount =	boothDAO.getBoothsCountByDivisionIds(wardIds,17l);
				
				finalVO.setBooths(boothsCount);
				
				Object totalVotersCount = greaterMuncipalWardDAO.getTotalVotersByDivisionIds(wardIds);
				
				if(totalVotersCount !=null){
					finalVO.setTotalVoters(Long.parseLong(totalVotersCount.toString()));
				}
				
				finalVO.setTotalCadre(tdpCadreDAO.getTdpCadreCountsForDivisions(wardIds));
				
				Object[] capturedVotersAndCadre = mobileAppUserVoterDAO.getTrackingDivisionSummaryCounts(wardIds);
				
				if(capturedVotersAndCadre!=null && capturedVotersAndCadre.length>0){
					   finalVO.setCapturedVoters(capturedVotersAndCadre[0]!=null?(Long)capturedVotersAndCadre[0]:0l);
					   finalVO.setCapturedCadre(capturedVotersAndCadre[1]!=null?(Long)capturedVotersAndCadre[1]:0l);
				}
				
				List<Object[]> capturedVoterRatings = mobileAppUserVoterDAO.getCapturedVoterRatings(wardIds);
				
				if(capturedVoterRatings!=null && capturedVoterRatings.size()>0){
					   for(Object[] obj:capturedVoterRatings){
						   Long rating=obj[0]!=null?(Long)obj[0]:null;
						   Long ratingsCount=obj[1]!=null?(Long)obj[1]:null;
						   if(rating!=null){
							    if(rating>3l){
							    	finalVO.setInclinedVoters(finalVO.getInclinedVoters()+ratingsCount);
							    }else if(rating<3){
							    	if(rating==0l){
							    		finalVO.setNonOptedVoters(ratingsCount);
							    	}else{
							    		finalVO.setOtherPartyVoters(finalVO.getOtherPartyVoters()+ratingsCount);
							    	}
							    }else{
							    	finalVO.setUnDecidedVoters(ratingsCount);
							    }
						   }
					   }
				   }
				
			   
			}catch (Exception e) {
				LOG.error("Exception raised at overAllPollManagementSummaryByDivisionOrWard", e);
			}
			return finalVO;
	 }
 public List<PollManagementVO> getNotYetPolledMembers(String resultType,Long locationId){
		 
		 List<PollManagementVO> fnlList = new ArrayList<PollManagementVO>();
		 
		 try{
			 
			 List<Object[]> notYetPolledMembers = mobileAppUserVoterDAO.getNotYetPolledMembers(resultType, locationId);			 
			 if(notYetPolledMembers !=null && notYetPolledMembers.size()>0){
				 for (Object[] members : notYetPolledMembers) {
					
					 PollManagementVO mainvo = new PollManagementVO();
					 
					 mainvo.setId(members[0] !=null ? (Long)members[0]:0l);
					 
					 mainvo.setVoterCardNo(members[1] !=null ? members[1].toString():"");
					 mainvo.setName(members[2] !=null ? members[2].toString():"");
					 mainvo.setMobileNo(members[3] !=null ? members[3].toString():"");
					 mainvo.setSmsStatus(members[4] !=null ? members[4].toString():"");
					 mainvo.setCalledStatus(members[5] !=null ? members[5].toString():"");
					 
					 fnlList.add(mainvo);
				}
			 }
			 
			 
		 }catch (Exception e) {
			 LOG.error("Exception raised at getNotYetPolledMembers", e);
		}
		 return fnlList;
	 }
 
 public List<PollManagementSummaryVO> boothWiseVotingActivity(Long wardId){
	 List<PollManagementSummaryVO> finalList = new ArrayList<PollManagementSummaryVO>();
	 
	 try{
		 
		 List<Long> wardIds = new ArrayList<Long>();
		 
		 wardIds.add(wardId);
		 
		 
		 List<Long> totalBooths = boothDAO.getAllBoothIdsByWard(wardId, 17l);
		 
		 List<Object[]> totalVotersList = boothDAO.getBoothWiseTotalVoters(totalBooths,wardId);
		 
		 List<Long> boothIds=null;
		 if(totalVotersList!=null && totalVotersList.size()>0){					 
			 finalList=new ArrayList<PollManagementSummaryVO>();
			 boothIds=new ArrayList<Long>();
			 
			 for (Object[] obj : totalVotersList) {
				
				 Long boothId=obj[0]!=null?(Long)obj[0]:0l;
				 
				 PollManagementSummaryVO boothVo=new PollManagementSummaryVO();
				 boothVo.setId(boothId);
				 boothVo.setName(obj[1]!=null?"Booth - "+Long.valueOf(obj[1].toString()):"Booth - "+0l);
				 
				 boothVo.setTotalVoters(obj[2]!=null?Long.valueOf(obj[2].toString()):0l);//total Voters
				 boothVo.setCadreCount(obj[3] !=null ? Long.valueOf(obj[3].toString()):0l);//total cadre Count
				 
				 boothVo.setSubList(getVoterRatingTypes());
				 
				 finalList.add(boothVo);
				 
				 boothIds.add(boothId);
			}					 
		 }
		 
		 List<Object[]>  capturedVoters = mobileAppUserVoterDAO.getCapturedVotersForBooth(boothIds,"capturedVoters");				 
		 gettingMatchedBoothVO(capturedVoters,finalList,"capturedVoter");
		 
		 List<Object[]> capturedCadre = mobileAppUserVoterDAO.getCapturedCadrePolledForBooth(boothIds,"total");				 
		 gettingMatchedBoothVO(capturedCadre,finalList,"capturedCadre");
		 
		 List<Object[]> totalPolledVotersAndCadres=mobileAppUserVoterDAO.getPolledVotersAndPolledCadreForBooth(boothIds);				 
		 gettingMatchedBoothVO(totalPolledVotersAndCadres,finalList,"totalPolledVotersAndCadres");
		    
		    
	    List<Object[]> capturedCadrepolled=mobileAppUserVoterDAO.getCapturedCadrePolledForBooth(boothIds,"polled");
	    gettingMatchedBoothVO(capturedCadrepolled,finalList,"capturedCadrepolled");
	    
	    
	    List<Object[]> nonCapVoterspolled=mobileAppUserVoterDAO.getCapturedVotersForBooth(boothIds,"nonCapturedVotersPolled");
	    gettingMatchedBoothVO(nonCapVoterspolled,finalList,"nonCapVoterspolled");
	    
	    List<Object[]> ratingVotersTracked=mobileAppUserVoterDAO.getTrackedAndPolledratingVotersForBooth(boothIds,"tracked");
	    List<Object[]> ratingVotersPolled=mobileAppUserVoterDAO.gettrackedAndPolledratingVoters(boothIds,"polled");
	    
	    gettingMatchedBoothVO(ratingVotersTracked,finalList,"ratingVoters");
	    gettingMatchedBoothVO(ratingVotersPolled,finalList,"ratingVotersPolled");
	    
	    
	  //calc percantages.
	    if(finalList!=null && finalList.size()>0){
	    	for(PollManagementSummaryVO boothVO:finalList){
	    		
	    		//total voters.
	    		boothVO.setTotalVotersYetToBePolled(boothVO.getTotalVoters()-boothVO.getTotalVotersPolled());
	    		boothVO.setPollPercent(calcPercantage(boothVO.getTotalVoters(),boothVO.getTotalVotersPolled()));
	    		boothVO.setYetToPollPercent(calcPercantage(boothVO.getTotalVoters(),boothVO.getTotalVotersYetToBePolled()));
	    		//for total cadre.
	    		boothVO.setCadreCountYetToBePolled(boothVO.getCadreCount()-boothVO.getCadreCountPolled());
	    		boothVO.setCadrepollPercent(calcPercantage(boothVO.getCadreCount(),boothVO.getCadreCountPolled()));
	    		boothVO.setCadreYetToPollPercent( calcPercantage(boothVO.getCadreCount(),boothVO.getCadreCountYetToBePolled()));
	    		//captured cadre
	    		boothVO.setCapCadreCountYetToBePolled(boothVO.getCapCadreCount()-boothVO.getCapCadreCountPolled());
	    		boothVO.setCapCadrePollPercent((calcPercantage(boothVO.getCapCadreCount(),boothVO.getCapCadreCountPolled())));
	    		boothVO.setCapCadreYetTopollPercent((calcPercantage(boothVO.getCapCadreCount(),boothVO.getCapCadreCountYetToBePolled())) );
	    		//nonCapVoterspolled
	    		boothVO.setNonCapVotersYetToBePolled(boothVO.getNonCapVoters()-boothVO.getNonCapVotersPolled());
	    		boothVO.setNonCapVotersPollPercent((calcPercantage(boothVO.getNonCapVoters(),boothVO.getNonCapVotersPolled())));
	    		boothVO.setNonCapVotersYetToPollPercent((calcPercantage(boothVO.getNonCapVoters(),boothVO.getNonCapVotersYetToBePolled())) );
	    		 
	    		//rating voters
	    		for(PollManagementSummaryVO ratingVO: boothVO.getSubList()){
	    			 ratingVO.setTotalVotersYetToBePolled(ratingVO.getTotalVoters()-ratingVO.getTotalVotersPolled());
	    			 ratingVO.setPollPercent(calcPercantage(ratingVO.getTotalVoters(),ratingVO.getTotalVotersPolled()));
	    			 ratingVO.setYetToPollPercent(calcPercantage(ratingVO.getTotalVoters(),ratingVO.getTotalVotersYetToBePolled()));
	    		}
	    		
	    		
	    	}
	    }
	    
	 }catch (Exception e) {
		e.printStackTrace();
	}
	 return finalList;
 }
 public void gettingMatchedBoothVO(List<Object[]> list,List<PollManagementSummaryVO> finalList,String type){
	 try{
		 
		 if(list!=null && list.size()>0){
			  for(Object[] obj:list){
				  if(obj[0]!=null){		
					  PollManagementSummaryVO boothVo=getMatchingward1(finalList,(Long)obj[0],"booth","");						  
					  if(type !=null && type.equalsIgnoreCase("totalPolledVotersAndCadres")){
						  
						  //total Voters Block
						  boothVo.setTotalVotersPolled(obj[1]!=null?(Long)obj[1]:0l);
						  
						  
						  //Cadre Details Block
						  boothVo.setCadreCountPolled(obj[2]!=null?(Long)obj[2]:0l);							  
						 						  
					  }
					  //total captured cadre
					  else if(type.equalsIgnoreCase("capturedCadre")){							  
						  boothVo.setCapCadreCount(obj[1]!=null?(Long)obj[1]:0l);							  
					  }						
					  //cap cadre details block
					  else if(type.equalsIgnoreCase("capturedCadrepolled")){
						  
						  boothVo.setCapCadreCountPolled(obj[1]!=null?(Long)obj[1]:0l);
						 
						 
					  }//setting non captured voters
					  else if(type.equalsIgnoreCase("capturedVoter")){							  
						  boothVo.setNonCapVoters(boothVo.getTotalVoters() - (obj[1]!=null?(Long)obj[1]:0l));
					  }else if(type.equalsIgnoreCase("nonCapVoterspolled")){	
						  
						  boothVo.setNonCapVotersPolled(obj[1]!=null?(Long)obj[1]:0l);
						 
						  
					  }else if(type.equalsIgnoreCase("ratingVoters") || type.equalsIgnoreCase("ratingVotersPolled")){
						  
						   Long rating=obj[1]!=null?(Long)obj[1]:null;
    					   Long ratingsCount=obj[2]!=null?(Long)obj[2]:null;
    					   PollManagementSummaryVO ratingVO=null;
    					   if(rating!=null){
    						    if(rating>3l){
    						    	  ratingVO=getMatchingward1(boothVo.getSubList(),null,"rating","INCLINED VOTERS");
    						    }else if(rating<3){
    						    	if(rating==0l){
    						    		ratingVO=getMatchingward1(boothVo.getSubList(),null,"rating","NON OPTED VOTERS");
    						    	}else{
    						    		ratingVO=getMatchingward1(boothVo.getSubList(),null,"rating","OTHER PARTY VOTERS");
    						    	}
    						    }else{
    						    	    ratingVO=getMatchingward1(boothVo.getSubList(),null,"rating","UNDECIDED VOTERS");
    						    }
    						    if(type.equalsIgnoreCase("ratingVoters")){
    						    	ratingVO.setTotalVoters(ratingVO.getTotalVoters()+ratingsCount);
    						    }else{
    						    	ratingVO.setTotalVotersPolled(ratingVO.getTotalVotersPolled()+ratingsCount);
    						    }
    						    
    					   }
					  }
					  
				  }
				 }
			  }
		 
		 
	 }catch (Exception e) {
		e.printStackTrace();
	}
 }
 public PollManagementSummaryVO getMatchingward1(List<PollManagementSummaryVO> resultList,Long Id,String type,String name)
	{
		try{
			 if(resultList != null && resultList.size() > 0){
				 
				for(PollManagementSummaryVO vo : resultList)
				{
					if(type.equalsIgnoreCase("booth")){
						if(vo.getId().longValue() == Id.longValue())
							return vo;
					}else if(type.equalsIgnoreCase("rating")){
						if(vo.getName().equalsIgnoreCase(name)){
							return vo;
						}
					}
				}
				
			  }
			}
		catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	
	 }
	 public PollManagementVO getDivisonOverView(MobileAppUserVoterVO inputVO)
	 {
		 PollManagementVO  finalVO=new PollManagementVO();
			try{
				
				finalVO = overAllPollManagementSummaryByDivisionOrWard(inputVO.getDivisonId());
				
				//List<Object[]> booths = boothDAO.getBoothsDataByDivisionId(inputVO.getDivisonId());
				List<Object[]> cadreBooths = tdpCadreDAO.getTdpCadreBoothsForDivision(inputVO.getDivisonId());
				setBoothWiseData(finalVO.getCadreBoothsList(),cadreBooths);
				List<Long> ratings = new ArrayList<Long>();
				ratings.add(4l);ratings.add(5l);
				List<Object[]>  inclinedBooths = mobileAppUserVoterDAO.getBoothsBasedOnRating(ratings);
				setBoothWiseData(finalVO.getInclinedBoothsList(),inclinedBooths);
				List<Long> ratings1 = new ArrayList<Long>();
				ratings1.add(3l);;
				List<Object[]>  undecidedBooths = mobileAppUserVoterDAO.getBoothsBasedOnRating(ratings1);
				setBoothWiseData(finalVO.getUnDecidedBoothsList(),undecidedBooths);
				
				List<Long> ratings2 = new ArrayList<Long>();
				ratings2.add(1l);ratings2.add(2l);;
				List<Object[]>  otherPartyBooths = mobileAppUserVoterDAO.getBoothsBasedOnRating(ratings2);
				setBoothWiseData(finalVO.getOherPartyBoothsList(),otherPartyBooths);
				
				List<Long> ratings3= new ArrayList<Long>();
				ratings3.add(0l);;
				List<Object[]>  notCaptured = mobileAppUserVoterDAO.getBoothsBasedOnRating(ratings3);
				setBoothWiseData(finalVO.getNotCapturedBoothsList(),notCaptured);
				List<Long> list = new ArrayList<Long>(0);
				list.add(inputVO.getDivisonId());
				finalVO.setPollManagementSummaryVOList(divisionWiseVotingActivity(list));
				
				
			}catch (Exception e) {
				LOG.error("Exception raised at getDivisonOverView", e);
			}
			return finalVO;
			
	 }
	 
	 public void setBoothWiseData(List<PollManagementVO> returnList,List<Object[]> list)
	 {
		 List<Long> boothIds = new ArrayList<Long>();
		 if(list != null && list.size() > 0)
		 {
			 for(Object[] params : list)
			 {
				 if(!boothIds.contains((Long)params[0]))
				 {
				 PollManagementVO boothVO = new PollManagementVO();
				 boothVO.setBoothId((Long)params[0]);
				 boothVO.setPartNo(params[1] != null ? params[1].toString() : "");
				 returnList.add(boothVO);
				 boothIds.add((Long)params[0]);
				 }
			 }
			 
				List<Object[]> totalVotersList = boothDAO.getTotalVotersByBooths(boothIds);
				if(totalVotersList != null && totalVotersList.size() > 0)
				{
						for(Object[] obj : totalVotersList)
						{
								PollManagementVO vo = getMatchedVO(returnList,(Long)obj[0]);
								if(vo != null)
								{
									vo.setTotalVoters((Long)obj[2]);
								}
						}
				}
				
				
				List<Object[]> votedList = mobileAppUserVoterDAO.getBoothWisePolledVoters(boothIds);
				
				if(votedList != null && votedList.size() > 0)
				{
					for(Object[] obj : votedList)
					{
							PollManagementVO vo = getMatchedVO(returnList,(Long)obj[0]);
							if(vo != null)
							{
								if(obj[2] != null && obj[2].toString().equalsIgnoreCase("Y"))
								{
									if(obj[1] != null)
								vo.setPolledVotes((Long)obj[1] + vo.getPolledVotes());
								}
								else
								{
									if(obj[1] != null)
									vo.setYetToPollVotes(vo.getYetToPollVotes() + (Long)obj[1]);
								}
							}
							
					}
				}
		 }
	 }
	 
	 
	 public PollManagementVO getMatchedVO(List<PollManagementVO> returnList,Long boothId)
	 {
		 if(returnList == null || returnList.size() == 0)
			 return null;
		 for(PollManagementVO vo : returnList)
		 {
			 if(vo.getBoothId().longValue() == boothId.longValue())
				 return vo;
		 }
		return null;
	 }
	 public List<CadreVoterVO> getVoterInfoForBooth(MobileAppUserVoterVO inputVO)
	 {
		 List<CadreVoterVO>  returnList = new ArrayList<CadreVoterVO>();
		try{ 
		 List<Object[]> list = mobileAppUserVoterDAO.getVotersInfo(inputVO.getBoothId(),inputVO.getWardId(),inputVO.getIsVoted(),inputVO.getReqType());
		 if(list != null && list.size() >0 )
		 {
			 for(Object[] params : list)
			 {
				 CadreVoterVO vo = new CadreVoterVO();
				 vo.setPartNo(params[0] != null ? params[0].toString() : "");
				 vo.setSerialNo(params[1] != null ? params[1].toString() : "");
				 vo.setMobileNum(params[4] != null ? params[4].toString() : "");
				 vo.setName(params[2] != null ? params[2].toString() : "");
				 vo.setVoterIdCardNo(params[3] != null ? params[3].toString() : "");
				 returnList.add(vo);
			 }
		 }
		}
		catch (Exception e) {
			LOG.error("Exception raised at getVoterInfoForBooth()", e);
		}
		return returnList;
		 
	 }
	 public MobileAppUserDetailsVO getUserWiseDivisionSummary(Long locationId,Long levelId,String startDateString, String endDateString,Long publicationDateId,Long electionYearId,List<String> userType){
			MobileAppUserDetailsVO finalVO = new MobileAppUserDetailsVO();
			try {
				SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
				
				Date fromDate=null;
				Date toDate=null;
				if(startDateString!=null && startDateString.trim().length()>0){
					fromDate=sdf.parse(startDateString);
				}
				if(endDateString!=null && endDateString.trim().length()>0){
					toDate=sdf.parse(endDateString);
				}

				  
				finalVO.setRatings(getBasicRatings());
				
				List<MobileAppUserDetailsVO> fnlLst  = new ArrayList<MobileAppUserDetailsVO>(); 
				List<Object[]> list = mobileAppUserVoterDAO.getUserStartEndTimeByLevelId(locationId, levelId, fromDate, toDate,publicationDateId,userType);
				List<Object[]> list1 = mobileAppUserVoterDAO.getUserCollectedDetailsByLvelId(locationId, levelId, fromDate, toDate,publicationDateId,userType);
				List<Long> divisionIds = new ArrayList<Long>(0);
				divisionIds.add(locationId);
				
				Long totalVoters = boothPublicationVoterDAO.getDivisionWiseVoters(locationId,levelId,publicationDateId);
				if(totalVoters != null && totalVoters.longValue()>0l)
				{
					finalVO.setTotalVoters(Integer.valueOf(String.valueOf(totalVoters)));
					
				}
				
				List<Long> usrIds = new ArrayList<Long>();
				if(list!=null && !list.isEmpty()){
					for(Object[] obj:list){
						MobileAppUserDetailsVO temp = new MobileAppUserDetailsVO();
						temp.setMobileAppUserId(obj[0]!=null?Long.valueOf(obj[0].toString()):null);
						temp.setName(obj[1]!=null?obj[1].toString():"");
						temp.setUserName(obj[8] != null ? obj[8].toString():"");
						temp.setMobileNo(obj[2]!=null?obj[2].toString():"");
						temp.setUniqueCode(obj[4]!=null?obj[4].toString():"");
						temp.setDate(obj[5]!=null?obj[5].toString():"");
						temp.setRatings(getBasicRatings());
						SimpleDateFormat innrSdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.S");
						SimpleDateFormat reqSdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
						String stDate = "";
						String edDate = "";
						
						if(obj[6]!=null){
							Date reqDate1 = innrSdf.parse(obj[6].toString());
							stDate = reqSdf.format(reqDate1);
						}
						
						if(obj[7]!=null){
							Date reqDate2 = innrSdf.parse(obj[7].toString());
							edDate = reqSdf.format(reqDate2);
						}
						
						temp.setStartTime(stDate);
						temp.setEndtime(edDate);
						
						if(obj[0]!=null && !usrIds.contains(Long.valueOf(obj[0].toString()))){
							usrIds.add(Long.valueOf(obj[0].toString()));
						}
						
						fnlLst.add(temp);
					}
				}
				
				finalVO.setUsersCount(usrIds.size());
				
				List<Object[]> list3 = mobileAppUserVoterDAO.getUserCollectedRatingDetailsByLvelId(locationId, levelId, fromDate, toDate,publicationDateId,userType);
				if(list3!=null && !list3.isEmpty()){
					for(Object[] obj:list3){
						MobileAppUserDetailsVO mv = getMatchedMobileAppUserDetailsVO(fnlLst, Long.valueOf(obj[0].toString()), obj[1].toString());
						if(mv!=null){
							List<MobileAppUserDetailsVO> ratings = mv.getRatings();
							if(ratings!=null && !ratings.isEmpty()){
								int ratingId = obj[2]!=null?Integer.parseInt(obj[2].toString()):0;
								MobileAppUserDetailsVO ratingVO = getMatchedRatingVO(ratings, ratingId);
								if(ratingVO!=null){
									int count = obj[3]!=null?Integer.parseInt(obj[3].toString()):0;
									ratingVO.setRatingCount(count);
									MobileAppUserDetailsVO mainRtngVO = getMatchedRatingVO(finalVO.getRatings(), ratingId);
									if(mainRtngVO==null){
										mainRtngVO.setRatingCount(count);
									}else{
										mainRtngVO.setRatingCount(mainRtngVO.getRatingCount()+count);
									}
								}
								
							}
						}
					}
				}
				
				int ttlMbls = 0;
				int ttlVtrs = 0;
				int ttlUnqeVtrs = 0;
				
				if(list1!=null && !list1.isEmpty()){
					for(Object[] obj:list1){
						MobileAppUserDetailsVO mv = getMatchedMobileAppUserDetailsVO(fnlLst, Long.valueOf(obj[0].toString()), obj[1].toString());
						if(mv!=null){
							int mbls = obj[2]!=null?Integer.parseInt(obj[2].toString()):0;
							int vtrs = obj[3]!=null?Integer.parseInt(obj[3].toString()):0;
							int unqVtrs = obj[4]!=null?Integer.parseInt(obj[4].toString()):0;
							
							mv.setNoOfMobiles(mbls);
							mv.setVoterIdsCollected(vtrs);
							mv.setUniqueVoters(unqVtrs);
							
							ttlMbls += mbls;
							ttlVtrs += vtrs;
							ttlUnqeVtrs += unqVtrs;
						}
					}
				}
				
				finalVO.setNoOfMobiles(ttlMbls);
				finalVO.setVoterIdsCollected(ttlVtrs);
				finalVO.setUniqueVoters(ttlUnqeVtrs);
				
				List<MobileAppUserSmsStatus> list2 = null;//mobileAppUserSmsStatusDAO.getUsersLatestData(usrIds, fromDate, toDate,userType);
				if(list2!=null && !list2.isEmpty()){
					List<Long> updtdUsrs = new ArrayList<Long>();
					for(MobileAppUserSmsStatus obj:list2){
						SimpleDateFormat innrSdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.S");
						SimpleDateFormat reqSdf = new SimpleDateFormat("yyyy-MM-dd");
						
						Long userId = Long.valueOf(obj.getMobileAppUserId().toString());
						Date reqDate = innrSdf.parse(obj.getStatusDate().toString());
						String date = reqSdf.format(reqDate);
						MobileAppUserDetailsVO mv = getMatchedMobileAppUserDetailsVO(fnlLst, userId, date);
						if(mv!=null && !updtdUsrs.contains(userId)){
							mv.setNoOfSmsSent(Integer.parseInt(obj.getSentSms().toString()));
							updtdUsrs.add(userId);
						}
					}
				}
				
				finalVO.setUserRslt(fnlLst);
			} catch (Exception e) {
				LOG.error("Exception Raised in getUserWiseDivisionSummary",e);
			}
		
			return finalVO;
		}
	 public List<LocationWiseBoothDetailsVO> getAssignedWardsByUser(String accessValue,Long userId){
		 List<LocationWiseBoothDetailsVO> finalList = new ArrayList<LocationWiseBoothDetailsVO>();
		 try {
			 Long constituencyId = Long.valueOf(accessValue);
			String areaType = constituencyDAO.getLocalBodyElectionTypeByConstituencyId(constituencyId);
			if(areaType != null && areaType.trim().equalsIgnoreCase("RURAL-URBAN")){
				finalList = cadreCommitteeService.getMandalMunicCorpDetailsNew(constituencyId);
			}else if(areaType != null && areaType.trim().equalsIgnoreCase("URBAN")){
				//finalList = cadreCommitteeService.getPanchayatWardDivisionDetailsNew(constituencyId);
				List<Object[]> wardList = userAccessLevelValueDAO.getAssignedWardsByUser(userId);
				if(wardList != null && wardList.size() > 0){
					 for (Object[] obj : wardList) {
						 LocationWiseBoothDetailsVO vo = new LocationWiseBoothDetailsVO();
						vo.setId((Long) (obj[0] != null ? obj[0]:0l));
							String wardName = obj[1] != null ? obj[1].toString():"";
							String divisionName = obj[2] != null ? obj[2].toString():"";
						vo.setName(divisionName+" - ("+wardName+") ");
						finalList.add(vo);
					}
				 }
			}else{
				List<SelectOptionVO> locations = regionServiceDataImp.getAllMandalsByConstituencyID(constituencyId);
				for(SelectOptionVO location:locations){
					LocationWiseBoothDetailsVO vo = new LocationWiseBoothDetailsVO();
		        	vo.setLocationId(Long.valueOf("2"+location.getId()));
		        	vo.setLocationName(location.getName()+" Mandal");
		        	finalList.add(vo);
		        }
			}
			
		} catch (Exception e) {
			LOG.error("Exception raised at getAssignedWardsByUser", e);
		}
		return finalList;
	 }
}
